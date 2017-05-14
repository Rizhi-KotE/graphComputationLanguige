package graph.core

class Graph(args: Object*) {
  def +(vertex: Vertex): Graph = this
}

object Graph {
  def apply(args: Object*): Graph = new Graph(args)

  def adjacment(vertex: Vertex): List[Vertex] = ???
}

