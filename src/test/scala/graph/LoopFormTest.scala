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
class LoopFormTest {
  var translator: GraphTranslator = null
  var visitor: Visitor = null

  var source: String = null
  var expected: String = null
  var loop: GraphParser.LoopContext = null
  var map: Map[ParserRuleContext, String] = null

  @Before
  def setUp() {
    translator = new GraphTranslator()
    map = mock(classOf[Map[ParserRuleContext, String]])
    visitor = new Visitor(map)
    val parser = translator.translateGraphToJava(source)
    loop = parser.loop()
  }

  @Test
  def simpleLoopTest() {
    when(map(any(classOf[ParserRuleContext]))).thenReturn("Void")
    assertEquals(expected, visitor.visit(loop))
  }

  @Test
  def loopWithTwoBindings() {
    when(map(any(classOf[ParserRuleContext]))).thenReturn("Void")
    assertEquals(expected, visitor.visit(loop))
  }

  @Test
  def loopWithRecursion(): Unit = {
    when(map(any(classOf[ParserRuleContext]))).thenReturn("Void")
    assertEquals(expected, visitor.visit(loop))
  }
}
