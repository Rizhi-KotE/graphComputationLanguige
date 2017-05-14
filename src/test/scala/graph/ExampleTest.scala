package graph

import _root_.graph.compiler._
import graph.core._
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import runner.Runner

@RunWith(classOf[Runner])
class ExampleTest {

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
  def dfsTest() {
    assertEquals(expected, visitor.visit(file))
  }

  {
    def +(g: Graph, v: List[Vertex], e: List[Edge]): Graph = {
      if (notempty(v))
        plus(plus(g, head(v)), tail(v), e)
      else if (notempty(e))
        plus(plus(g, head(e)), v, tail(e))
      else
        g
    }

    def dfs(g: Graph, v: Vertex): Graph = {
      {
        def loop(queue: List[Vertex], used: Graph): Graph = {
          if (notempty(queue)) {
            def v: Vertex = head(queue)

            loop(tail(queue), {
              def loop(adj: List[Vertex], used: Graph): Graph = {
                if (notempty(adj))
                  loop(tail(adj), {
                    def to: Vertex = head(adj)
                    plus(used, List(to), List(edge(g, v, to)))
                  })
                else
                  used
              }

              loop(adjacment(g, v), used)
            })
          }
          else
            used
        }

        loop(List(v), graph(v))
      }
    }
  }
}
