package graph

import graph.compiler._
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import runner.Runner

@RunWith(classOf[Runner])
class EdgeLiteralTest {

  var translator: GraphTranslator = null
  var visitor: Visitor = null

  var source: String = null
  var expected: String = null
  var file: GraphParser.FileContext = null
  var map: Map[ParserRuleContext, String] = null
  var analyzer: SemanticAnalyzer = null

  @Before
  def setUp() {
    translator = new GraphTranslator()
    analyzer = new SemanticAnalyzer
    val walker = new ParseTreeWalker
    val parser = translator.translateGraphToJava(source)
    file = parser.file()
    analyzer.visit(file)
    map = analyzer.formsType.map({
      case (e, f: TypedFunction) => e -> f.returnType.entityType
      case (e, f: AnyType) => println(e.getText); e -> "any"
      case (e, f: TypedEntity) => e -> f.entityType
    }).toMap
    visitor = new Visitor(map)

  }

  @Test
  def simpleEdgeLiteral(): Unit = {
    assertEquals(expected, visitor.visit(file))
  }
}
