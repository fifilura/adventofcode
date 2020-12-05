val rows = io.Source.stdin.getLines.toList

def split_passport_iter(rows:List[String], cur_passport:String) : List[String] =
{
  if (rows.isEmpty)
    List(cur_passport)
  else if(rows.head.length == 0)
    cur_passport :: split_passport_iter(rows.tail, "")
  else
    split_passport_iter(rows.tail, cur_passport + rows.head + " ")
}

val required = List("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

val valid_passports = split_passport_iter(rows, "")
  .map(x => x.split(" ")
    .map(x => x.slice(0,3))
    .filter(x => required.contains(x))
    .distinct
    .length == 7)


println(valid_passports.count(_ == true))
