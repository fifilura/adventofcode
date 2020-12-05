val regex = raw"(\d+)-(\d+)\s(\w): (\w+)".r

val words = for {
  regex(pos_one, pos_two, character, word) <- io.Source.stdin.getLines
  if (word(pos_one.toInt - 1) == character(0) ^ word(
    pos_two.toInt - 1
  ) == character(0))
} yield word

println(words.length)