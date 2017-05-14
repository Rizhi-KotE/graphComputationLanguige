package graph.compiler

import graph.GraphParser.{EdgeContext, LogicalContext, NumberContext, VectorContext, VertexContext}
import graph.compiler.constants._
import graph.{GraphBaseVisitor, GraphParser}
import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.JavaConverters._
import scala.collection.mutable

class SemanticAnalyzer extends GraphBaseVisitor[Unit] {
  val formsType: mutable.Map[ParserRuleContext, TypedEntity] = mutable.Map()

  val contextMap: ContextMap = ContextMap()

  override def visitFn(ctx: GraphParser.FnContext): Unit = {
    val functionLevel = contextMap.currentLevel
    contextMap.addLevel()
    val params = ctx.param.asScala
    params.foreach(visit)
    val paramsTypes = params.map(formsType)
    val funcDefinition = paramsTypes.head
    formsType(ctx) = new TypedFunction(funcDefinition, paramsTypes.tail)
    functionLevel.addEntity(params.head.idtf().getText, formsType(ctx))
    ctx.form.asScala.foreach(visit)
    contextMap.removeLevel()
  }

  override def visitParam(ctx: GraphParser.ParamContext): Unit = {
    formsType(ctx) = new TypedStructure(ctx.`type`().getText)
    contextMap(ctx.idtf().getText) = formsType(ctx)
  }

  override def visitBinding(ctx: GraphParser.BindingContext): Unit = {
    visit(ctx.param())
    visit(ctx.form())

    if (formsType(ctx.form()) match {
      case f: TypedFunction => !f.returnType.matchType(formsType(ctx.param()))
      case e: TypedEntity => !e.matchType(formsType(ctx.param()))
    }) {
      throw new TypeMismatchException(ctx.getText)
    }
    else {
      formsType(ctx) = formsType(ctx.param())
    }
  }

  override def visitIdtf(ctx: GraphParser.IdtfContext): Unit = {
    try {
      formsType(ctx) = contextMap.findEntity(ctx.getText, new AnyType)
    } catch {
      case e: CannotChooseVariableValueException => {
        println(ctx.getText)
        e.printStackTrace()
      }
    }
  }

  override def visitForm(ctx: GraphParser.FormContext): Unit = {
    val children = ctx.children.asScala
    children.foreach(visit)
    formsType(ctx) = formsType(children.head.asInstanceOf[ParserRuleContext])
    match {
      case e: TypedFunction => e.returnType
      case e => e
    }
  }

  override def visitType(ctx: GraphParser.TypeContext): Unit = {

  }

  override def visitTest(ctx: GraphParser.TestContext): Unit = {
    val forms = ctx.form.asScala
      .map(e => {
        visit(e)
        e
      })
      .map(formsType)
      .map {
        case e: TypedFunction => e.returnType
        case e => e
      }
    if (!forms.head.matchType(new TypedStructure("Boolean"))) {
      throw new TypeMismatchException(s"first form of test form must has type Boolean, but has\n${forms.head.entityType}")
    }
    if (!forms(1).matchType(forms(2))) {
      throw new TypeMismatchException(s"second and third form of test form must be the same type, but was\n${forms.tail}")
    }
    formsType(ctx) = forms.tail.filter {
      case e: AnyType => false
      case _ => true
    } match {
      case e if e.nonEmpty => e.head
      case _ => throw new TypeMismatchException(s"cannot infer type of test form\n${forms.tail}")
    }
  }

  override def visitRecur(ctx: GraphParser.RecurContext): Unit = {
    formsType(ctx) = contextMap.findEntity("loop",
      new TypedFunction(
        new AnyType,
        ctx.form.asScala
          .map { e => visit(e); e }
          .map(formsType)))
  }

  override def visitAction(ctx: GraphParser.ActionContext): Unit = {
    try {
      ctx.form.asScala.foreach(visit)
      formsType(ctx) = contextMap
        .findEntity(ctx.idtf().getText, TypedFunction(new AnyType(), ctx.form
          .asScala
          .map(formsType)
          .map({
            case f: TypedFunction => f.returnType
            case e: TypedEntity => e
          })))
    } catch {
      case e: CannotFindEnetityException => {
        println(ctx.getText)
        e.printStackTrace()
      }
    }
  }

  override def visitLoop(ctx: GraphParser.LoopContext): Unit = {
    contextMap.addLevel()
    contextMap.addEntity("loop", new AnyType)
    ctx.children.asScala.foreach(visit)
    formsType(ctx) = formsType(ctx.form.asScala.last)
    contextMap.removeLevel()
  }

  override def visitLet(ctx: GraphParser.LetContext): Unit = {
    contextMap.addLevel()
    ctx.children.asScala.foreach(visit)
    formsType(ctx) = formsType(ctx.form.asScala.last)
    contextMap.removeLevel()
  }

  override def visitVector(ctx: GraphParser.VectorContext): Unit = {
    val children = ctx.form.asScala
    children.foreach(visit)
    val childrenTypes = children.map(formsType)
    val listType = if (children.nonEmpty) {
      childrenTypes.foldLeft(childrenTypes.head)((`type`, elementType) =>
        if (elementType.matchType(`type`)) `type`
        else
          throw new TypeMismatchException(s"List should has elements of one type, but has $childrenTypes"))
    }
    formsType(ctx) = new TypedStructure(s"List<${
      listType match {
        case f: TypedFunction => f.returnType.toString
        case e => e.toString
      }
    }>")
  }

  override def visitLiteral(ctx: GraphParser.LiteralContext): Unit = {
    formsType(ctx) = ctx.children.asScala.head match {
      case _: VertexContext => TypedStructure("Vertex")
      case e: EdgeContext => visit(e); EDGE
      case _: StringContext => TypedStructure("String")
      case _: LogicalContext => TypedStructure("Boolean")
      case _: NumberContext => TypedStructure("Number")
      case l: VectorContext => visit(l); formsType(l)
    }
  }

  override def visitEdge(ctx: EdgeContext): Unit = {
    val types = ctx.form.asScala.map { e => visit(e); e }.map(formsType)
    formsType(ctx) = types match {
      case t if t.size == 2 => if (VERTEX.matchType(t(0)) && VERTEX.matchType(t(1)))
        EDGE
      else throw new TypeMismatchException("edge literal should has only forms with type of Vertex")
      case t if t.size == 3 => if (VERTEX.matchType(t(0)) && VERTEX.matchType(t(2)) && NUMBER.matchType(t(1)))
        EDGE
      else throw new TypeMismatchException("edge literal should has forms types [Vertex Number Vertex]")
    }
  }
}
