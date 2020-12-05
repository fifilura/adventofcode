val res = io.Source.stdin.getLines
  .map(row => Integer.parseInt(row.map(x => if (x == 'B' || x == 'R') "1" else "0").mkString,2))
  .toList

println((res.min to res.max).toSet.diff(res.toSet))