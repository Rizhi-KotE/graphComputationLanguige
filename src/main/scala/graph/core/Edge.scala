package graph.core


class Edge(from: Vertex, to: Vertex) {

}

object Edge {
  def apply(from: Vertex, to: Vertex): Edge = new Edge(from, to)
}
