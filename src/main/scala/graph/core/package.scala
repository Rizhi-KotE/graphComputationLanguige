package graph

/**
  * Created by rizhi-kote on 23.03.17.
  */
package object core {
  def list[T](args: T*): Seq[T] = args

  def notempty[T](seq: Seq[T]): Boolean = seq.nonEmpty

  def head[T](seq: Seq[T]): T = seq.head

  def tail[T](seq: Seq[T]): Seq[T] = seq.tail

  def plus(seq: Graph, obj: Object*): Graph = ???

  def edge(graph: Graph, from: Vertex, to: Vertex): Edge = ???

  def graph(obj: Object*): Graph = ???

  def adjacment(graph: Graph, vertex: Vertex): Seq[Vertex] = ???

}
