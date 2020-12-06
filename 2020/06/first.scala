val rows = io.Source.stdin.getLines.toList

def groupInput(
    rows: List[String],
    curGroup: String
): List[String] = {
  if (rows.isEmpty)
    List(curGroup)
  else if (rows.head.length == 0)
    curGroup :: groupInput(rows.tail, "")
  else
    groupInput(rows.tail, curGroup + rows.head)
}

val input = groupInput(rows, "")
println(input.map(x => x.distinct.length).sum)