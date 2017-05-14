package graph.compiler

import graph.{GraphParser, GraphTranslator}
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.Assert.assertEquals
import org.junit.{Assert, Before, Test}
import org.junit.runner.RunWith
import runner.Runner

@RunWith(classOf[Runner])
class SemanticAnalyzerTest {
  var translator: GraphTranslator = null
  var analyzer: SemanticAnalyzer = null

  var source: String = null
  var expected: String = null
  var file: GraphParser.FileContext = null
  var visitor: Visitor = null

  @Before
  def setUp() {
    translator = new GraphTranslator()
    analyzer = new SemanticAnalyzer
    val walker = new ParseTreeWalker
    val parser = translator.translateGraphToJava(source)
    file = parser.file()
    analyzer.visit(file)
    val map = analyzer.formsType
    visitor = new Visitor(map.map(t => t._1 -> t._2.entityType).toMap)

  }

  @Test
  def voidFunction(): Unit ={
    val result = visitor.visit(file)
    assertEquals(expected, result)
  }
}
