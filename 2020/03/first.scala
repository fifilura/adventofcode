val rows = io.Source.stdin.getLines.toList

val width = rows(0).length

val tmp =
  for (
    row <- rows.zip(0 to rows.length)
    if (row._1.toList((row._2 * 3) % width) == '#')
  ) yield 1

println(tmp.length)
