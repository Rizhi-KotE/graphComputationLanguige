def loop(a: String, b: String): Void = {
  if(==(a, "AA"))
    loop("AA", "BB")
  else
    {
      println(a)
      println(b)
    }
}
loop("A", "B")