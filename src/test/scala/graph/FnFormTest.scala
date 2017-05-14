package graph

import graph.compiler.Visitor
import org.antlr.v4.runtime.ParserRuleContext
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Matchers.any
import org.mockito.Mockito._
import runner.Runner

@RunWith(classOf[Runner])
class FnFormTest {
  var translator: GraphTranslator = null
  var visitor: Visitor = null

  var source: String = null
  var expected: String = null
  var fn: GraphParser.FnContext = null
  var map: Map[ParserRuleContext, String] = null

  @Before
  def setUp() {
    translator = new GraphTranslator()
    map = mock(classOf[Map[ParserRuleContext, String]])
    visitor = new Visitor(map)
    val parser = translator.translateGraphToJava(source)
    fn = parser.fn()
  }

  @Test
  def emptyFunction() {
    when(map(any(classOf[ParserRuleContext]))).thenReturn("Void")
    assertEquals(expected, visitor.visit(fn))
  }

}
