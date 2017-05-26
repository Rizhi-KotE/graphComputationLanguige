package graph.compiler

import java.lang.String.format

import graph.GraphParser._
import graph.{GraphBaseVisitor, GraphParser, GraphVisitor}
import org.antlr.v4.runtime.ParserRuleContext
import org.stringtemplate.v4.STGroupDir

import scala.collection.JavaConverters._

class Visitor(formsTypes: Map[ParserRuleContext, String]) extends GraphBaseVisitor[String] with GraphVisitor[String] {

  lazy val operatorsMap = Map("=" -> "equal",
    "+" -> "plus",
    "-" -> "minus",
    "/" -> "divise",
    "*" -> "multiply",
    "?" -> "contain")

  override def visitEdge(ctx: EdgeContext): String = {
    val forms = ctx.form.asScala
      .groupBy(formsTypes)
      .map(tuple => tuple._1 -> tuple._2.map(visit))
    stGroup.getInstanceOf("edge")
      .add("vertexes", forms("Vertex").asJava)
      .add("number", forms.getOrElse("Number", List()).asJava)
      .render()
  }

  override def visitVertex(ctx: GraphParser.VertexContext): String = stGroup
    .getInstanceOf("vertex")
    .add("name", ctx.character().getText)
    .render()

  override def visitNumber(ctx: NumberContext): String = ctx.getText

  override def visitVector(ctx: VectorContext): String = stGroup.getInstanceOf("vector")
    .add("forms", ctx.form().asScala.map(visit).asJava)
    .render()

  override def visitType(ctx: TypeContext): String = stGroup.getInstanceOf("type")
    .add("name", ctx.idtf().getText)
    .add("generic", ctx.`type`() match {
      case null => null
      case e => visit(e)
    })
    .render()

  override def visitTest(ctx: GraphParser.TestContext): String = {
    val test = visit(ctx.form(0))
    val than = visit(ctx.form(1))
    val otherwise = visit(ctx.form(2))
    stGroup.getInstanceOf("if")
      .add("test", test)
      .add("than", than)
      .add("otherwise", otherwise)
      .render()
  }

  def stGroup = new STGroupDir("templates")

  override def visitLoop(ctx: LoopContext): String = {
    val forms = ctx.form().asScala
    val bindings = ctx.binding().asScala.map(_.param).map(visitParam)
    val values = ctx.binding.asScala.map(_.form()).map(visitForm)
    val returnType = formsTypes(ctx)

    stGroup.getInstanceOf("loop")
      .add("loop", "loop")
      .add("forms", forms.map(visitForm).asJava)
      .add("bindings", bindings.asJava)
      .add("values", values.asJava)
      .add("return", returnType)
      .render()
  }

  override def visitParam(ctx: GraphParser.ParamContext): String = {
    val name = visit(ctx.idtf())
    val `type` = visit(ctx.`type`())
    format("%s: %s", name, `type`)
  }

  override def visitIdtf(ctx: IdtfContext): String = ctx.children.get(0) match {
    case e: OperatorContext => visit(e)
    case e => e.getText
  }

  override def visitString(ctx: StringContext): String = ctx.getText

  override def visitLet(ctx: GraphParser.LetContext): String = {
    val bindings = ctx.binding().asScala.map(visitBinding).asJava
    val forms = ctx.form().asScala.map(visitForm).asJava
    val letStatement = stGroup.getInstanceOf("let")
    letStatement.add("bindings", bindings).add("forms", forms)
    letStatement.render
  }

  override def visitForm(ctx: FormContext): String =
    ctx.children.asScala.head match {
      case c@(_: GraphParser.LoopContext)
      => stGroup.getInstanceOf("form")
        .add("body", visit(c))
        .render()
      case c => visit(c)
    }

  override def visitBinding(ctx: GraphParser.BindingContext): String = {
    val form = visit(ctx.form)
    stGroup.getInstanceOf("binding")
      .add("name", visit(ctx.param()))
      .add("form", form)
      .render()
  }

  override def visitLogical(ctx: GraphParser.LogicalContext): String = ctx.getText

  override def visitCharacter(ctx: GraphParser.CharacterContext): String = format("%s", ctx.getText)

  override def visitAction(ctx: GraphParser.ActionContext): String = {
    val functor = visit(ctx.idtf)
    val array = ctx.form.asScala.map(visit)
    stGroup.getInstanceOf("action")
      .add("functor", functor)
      .add("params", array.asJava)
      .render
  }

  override def visitFn(ctx: FnContext): String = {
    val param = ctx.param().asScala
    val returnType = formsTypes(ctx).replace("<", "[").replace(">", "]")
    val forms = ctx.form().asScala.map(visit)

    stGroup.getInstanceOf("fn")
      .add("name", param.head.idtf.accept(this))
      .add("params", param.tail.map(visit).asJava)
      .add("forms", forms.asJava)
      .add("returnType", returnType)
      .render()
  }

  override def visitRecur(ctx: RecurContext): String = {
    val forms = ctx.form.asScala.map(visit).asJava
    stGroup.getInstanceOf("action")
      .add("functor", "loop")
      .add("params", forms)
      .render
  }

  override def visitOperator(ctx: OperatorContext): String = operatorsMap.getOrElse(ctx.getText, ctx.getText)

  override protected def aggregateResult(aggregate: String, nextResult: String): String = {
    List(Option(aggregate), Option(nextResult)).flatten.mkString
  }
}
