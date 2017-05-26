package graph.core


class Edge(val from: Vertex, val to: Vertex) {
  def contain(vertex: Vertex): Boolean = from == vertex || to == vertex


  def canEqual(other: Any): Boolean = other.isInstanceOf[Edge]

  override def equals(other: Any): Boolean = other match {
    case that: Edge =>
      (that canEqual this) &&
        from == that.from &&
        to == that.to
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(from, to)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString: String = s"`(${to.toString} ${from.toString})"
}
