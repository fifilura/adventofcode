val regex = raw"(\d+)-(\d+)\s(\w): (\w+)".r

println(io.Source.stdin.getLines.flatMap
{
  case regex(pos_one, pos_two, character, word)
      if word(pos_one.toInt - 1) == character(0) ^ word(pos_two.toInt - 1) == character(0) => Some(1)
  case x => None
}.length)
