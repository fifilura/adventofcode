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

val hgt_regex = raw"hgt:(\d+)(in|cm)".r
val hcl_regex = raw"^hcl:#[a-f0-9]{6}".r
val ecl_regex = raw"ecl:(?:amb|blu|brn|gry|grn|hzl|oth)".r
val pid_regex = raw"pid:\d{9}".r
val yr_regex = raw"(\wyr):(\d{4})".r

def validate(entry: String): String = {
  entry match {
    case hcl_regex() => "hcl"
    case yr_regex(code, value) =>
      code match {
        case "eyr" if (2020 to 2030).contains(value.toInt) => "eyr"
        case "iyr" if (2010 to 2020).contains(value.toInt) => "iyr"
        case "byr" if (1920 to 2002).contains(value.toInt) => "byr"
        case x                                             => "invalid"
      }
    case hgt_regex(value, unit_type) =>
      unit_type match {
        case "cm" if (150 to 193).contains(value.toInt) => "hgt"
        case "in" if (59 to 76).contains(value.toInt)   => "hgt"
        case x                                          => "invalid"
      }
    case ecl_regex() => "ecl"
    case pid_regex() => "pid"

    case x => "invalid"
  }
}

val validPassports = splitPassportIter(rows, "")
  .map(x =>
    x.split(" ")
      .map(x => validate(x))
      .filter(x => required.contains(x))
      .distinct
      .length == 7
  )

println(validPassports.count(_ == true))
