loop(loop, bindings, values, return, forms) ::= <<
def <loop>(<bindings; separator=", ">)<return: {x| : <x>}> = {
  <forms; separator="\n">
}
<loop>(<values; separator=", ">)
>>