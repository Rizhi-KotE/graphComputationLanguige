package graph.core

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Graph() {
  def contain(edge: Edge): Boolean = findEdge(edge.from, edge.to) != null

  def contain(vertex: Vertex): Boolean = vertexes.contains(vertex)

  def vertexes = nodes.keys.toArray

  var nodes: mutable.Map[Vertex, mutable.Set[Vertex]] = mutable.Map()
  var edges: ArrayBuffer[Edge] = mutable.ArrayBuffer()

  def add(edge: Edge): Graph = {
    if (edge == null) return this
    if (findEdge(edge.from, edge.to) != null) return this
    edges += edge
    add(edge.to)
    add(edge.from)
    nodes(edge.to) += edge.from
    nodes(edge.from) += edge.to
    this
  }

  def add(vertex: Vertex): Graph = {
    if (nodes.getOrElse(vertex, null) == null)
      nodes += vertex -> mutable.Set()
    this
  }

  def findEdge(from: Vertex, to: Vertex): Edge = {
    val existEdge = edges.filter(e => (e.from == from && e.to == to) ||
      (e.to == from && e.from == to))
    if (existEdge.isEmpty) null
    else existEdge.head
  }

  def adjacment(v: Vertex): Array[Vertex] = {
    nodes(v).toArray
  }

  override def toString: String = {
    val nod = nodes.keys.map(n => s"#${n.name}").mkString("["," ", "]")
    val edg =edges.map(e => s"`(#${e.to.name} #${e.from.name})").mkString("["," ", "]")
    s"{${nod} ${edg}}"
  }
}

