val rows = io.Source.stdin.getLines.toList

def split_binary(lower: Int, upper: Int,  take_upper: List[Boolean]): Int =
{
  if (take_upper.isEmpty)
    lower
  else if (take_upper.head) {
    split_binary((upper + lower) /  2 + (upper - lower) % 2,  upper, take_upper.tail)
  } else
    split_binary(lower , (upper + lower) / 2, take_upper.tail)
}

def calculate_row(row: String): Int = {
  val row_seat = split_binary(0, 127, row.slice(0,7).map(x => x == 'B').toList)
  val column_seat = split_binary(0, 7, row.slice(7, 10).map(x => x == 'R').toList)
  row_seat * 8 + column_seat
}

val res = for (row <- rows) yield calculate_row(row)

val range = (res.min to res.max).toSet


println((res.min to res.max).toSet.diff(res.toSet))