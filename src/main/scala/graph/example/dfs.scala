package graph.example

import graph.core._


object dfs {
  def apply(g: Graph, v: Vertex): Graph = {
    def loop1(queue: Seq[Vertex], used: Graph): Graph = {
      if (notempty(queue)) {
        {
          def v = head(queue)

          def loop2(adjacment: Seq[Vertex], used: Graph): Graph = {
            if (notempty(adjacment)) {
              {
                def to: Vertex = head(adjacment)

                loop2(tail(adjacment), plus(used, to, edge(g, v, to)))
              }
            } else {
              used
            }
          }

          loop2(adjacment(g, v), used)
        }
        loop1(list(v), graph(v))
      } else {
        used
      }
    }

    loop1(list(v), Graph(v))
  }
}
