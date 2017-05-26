import graph.GraphTranslator
import graph.compiler._
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.stringtemplate.v4.STGroupDir

object Compiler {
  def main(args: Array[String]): Unit = {
    try {
      if (args.isEmpty) return
      val source = io.Source.fromFile(args(0) + ".src").mkString
      val translator = new GraphTranslator()
      val analyzer = new SemanticAnalyzer
      val walker = new ParseTreeWalker
      val parser = translator.translateGraphToJava(source)
      val file = parser.file()
      analyzer.visit(file)
      val map = analyzer.formsType.map({
        case (e, f: TypedFunction) => e -> f.returnType.entityType
        case (e, f: AnyType) => e -> "any"
        case (e, f: TypedEntity) => e -> f.entityType
      }).toMap
      val visitor = new Visitor(map)
      val translated = visitor.visit(file)
      val scalaFile = new STGroupDir("templates")
        .getInstanceOf("scalaFile")
        .add("fileName", args(0))
        .add("body", translated)
        .render()
      println(scalaFile)
    } catch {
      case e: Throwable => {
//        e.printStackTrace()
        System.err.println(e.getMessage)
        System.exit(1)
      }
    }
  }
}
