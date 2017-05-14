{
  def a: Vertex = new Vertex("a")
  def b: Vertex = new Vertex("c")
  def n: Number = 100
  new Edge(a, b)
  new Edge(a, b, n)
  new Edge(new Vertex("asd"), new Vertex("b"), 10)
}