package graph.core

class Vertex(val name: String) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Vertex]

  override def equals(other: Any): Boolean = other match {
    case that: Vertex =>
      (that canEqual this) &&
        name == that.name
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(name)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString: String = s"#${name}"
}