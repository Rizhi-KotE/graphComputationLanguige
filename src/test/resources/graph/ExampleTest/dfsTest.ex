{
  def plus(g: Graph, v: List[Vertex], e: List[Edge]): Graph = {
    if(notempty(v))
      plus(plus(g, head(v)), tail(v), e)
    else
      if(notempty(e))
        plus(plus(g, head(e)), v, tail(e))
      else
        g
  }
  def graph(v: Vertex): Graph = {
    graph(List(v))
  }
  def dfs(g: Graph, v: Vertex): Graph = {
    {
      def loop(queue: List[Vertex], used: Graph): Graph = {
        if(notempty(queue))
          {
            def v: Vertex = head(queue)
            loop(tail(queue), {
              def loop(adj: List[Vertex], used: Graph): Graph = {
                if(notempty(adj))
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