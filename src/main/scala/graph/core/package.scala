package graph

import scala.reflect.ClassTag

package object core {


  def notempty[T](Array: Array[T]): Boolean = Array.nonEmpty

  def plus[T: ClassTag](array: Array[T], obj: T): Array[T] = array ++ Array.fill[T](1)(obj)

  def head[T](Array: Array[T]): T = Array.head

  def tail[T](Array: Array[T]): Array[T] = Array.tail

  def edge(graph: Graph, from: Vertex, to: Vertex): Edge = graph.findEdge(from, to)

  def graph(obj: Vertex): Graph = {
    val graph = new Graph()
    graph.add(obj)
  }

  def graph(obj: Edge): Graph = {
    val graph = new Graph()
    graph.add(obj)
  }

  def graph(vertex: Array[Vertex], edge: Array[Edge]): Graph = plus(new Graph(), vertex, edge)

  def graph(vertex: Array[Vertex]): Graph = plus(new Graph(), vertex, Array())

  def adjacment(graph: Graph, vertex: Vertex): Array[Vertex] = graph.adjacment(vertex)

  def plus(graph: Graph, edge: Edge): Graph = {
    graph.add(edge)
  }

  def equal[T](obj1: T, obj2: T): Boolean = obj1.equals(obj2)

  def vertexes(graph: Graph): Array[Vertex] = graph.nodes.keys.toArray

  def edges(graph: Graph): Array[Edge] = graph.edges.toArray

  def plus(graph: Graph, vertex: Vertex): Graph = {
    graph.add(vertex)
  }

  def plus(g: Graph, v: Array[Vertex], e: Array[Edge]): Graph = {
    if (notempty(v))
      plus(plus(g, head(v)), tail(v), e)
    else if (notempty(e))
      plus(plus(g, head(e)), v, tail(e))
    else
      g
  }

  def minus(array: Array[Vertex], graph: Graph) = {
    array.diff(graph.vertexes)
  }

  def contain(graph: Graph, vertex: Vertex): Boolean = graph.contain(vertex)

  def contain(graph: Graph, edge: Edge): Boolean = graph.contain(edge)

  def contain(edge: Edge, vertex: Vertex): Boolean = edge.contain(vertex)
}
