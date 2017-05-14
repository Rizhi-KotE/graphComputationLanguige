package graph

package object core {


  def notempty[T](seq: List[T]): Boolean = seq.nonEmpty

  def head[T](seq: List[T]): T = seq.head

  def tail[T](seq: List[T]): List[T] = seq.tail

  def plus(seq: Graph, obj: Object*): Graph = ???

  def edge(graph: Graph, from: Vertex, to: Vertex): Edge = ???

  def graph(obj: Vertex): Graph = ???

  def graph(obj: Edge): Graph = ???

  def adjacment(graph: Graph, vertex: Vertex): List[Vertex] = ???

  def plus(graph: Graph, vertex: Vertex, c: Vertex): Graph = ???
}
