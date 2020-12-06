val rows = io.Source.stdin.getLines.toList

def splitPassportIter(
    rows: List[String],
    curPassport: String
): List[String] = {
  if (rows.isEmpty)
    List(curPassport)
  else if (rows.head.length == 0)
    curPassport :: splitPassportIter(rows.tail, "")
  else
    splitPassportIter(rows.tail, curPassport + rows.head + " ")
}

val required = List("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

val validPassports = splitPassportIter(rows, "")
  .map(x =>
    x.split(" ")
      .map(x => x.slice(0, 3))
      .filter(x => required.contains(x))
      .distinct
      .length == 7
  )

println(validPassports.count(_ == true))
