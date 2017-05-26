import graph.core._

object test3
{
  def main(args: Array[String]): Unit = {
    {
      val v1: Vertex = new Vertex("v1")
      val v2: Vertex = new Vertex("v2")
      val v3: Vertex = new Vertex("v3")
      val e1: Edge = new Edge(v1, v3)
      val e2: Edge = new Edge(new Vertex("v4"), v3)
      val e3: Edge = new Edge(new Vertex("v4"), new Vertex("v5"))
      val g: Graph = graph(Array(v1, v2, v3), Array(e1, e2, e3))

      {
        def loop(vs: Array[Vertex]): Unit = {
          if (notempty(vs)) {
            println(head(vs))
            loop(tail(vs))
          }
          else
            println(false)
        }

        loop(vertexes(g))
      }
      {
        def loop(es: Array[Edge]): Unit = {
          if(notempty(es))
          {
            println(head(es))
            loop(tail(es))
          }
          else
            println(false)
        }
        loop(edges(g))
      }
    }
  }
}