val rows = io.Source.stdin.getLines.toList

/* There is a small bug in this code - it requires the input to have two
blanklines at the very end in order to commit the last group.
*/

def addCharsInString(m: Map[Char, Int], s: String): Map[Char, Int] = {
  if (s.isEmpty)
    m
  else {
    addCharsInString(m + (s.head -> (m(s.head) + 1)), s.tail)
  }
}

def groupInput(
    rows: List[(String)],
    personCnt: Int,
    curMap: Map[Char, Int]
): List[Int] = {
  if (rows.isEmpty)
    Nil
  else if (rows.head.length == 0) {
    val numberOfValidCodes = curMap.filter(_._2 == personCnt).keys.size
    numberOfValidCodes :: groupInput(
      rows.tail,
      0,
      Map.empty[Char, Int].withDefault(_ => 0)
    )
  } else {
    groupInput(rows.tail, personCnt + 1, addCharsInString(curMap, rows.head))
  }
}

val emptyMap: Map[Char, Int] = Map.empty[Char, Int].withDefault(_ => 0)
val res = groupInput(rows, 0, emptyMap)
println(res.sum)









