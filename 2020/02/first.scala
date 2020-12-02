val regex = raw"(\d+)-(\d+)\s(\w): (\w+)".r

val words = for {
  regex(min_cnt, max_cnt, character, word) <- io.Source.stdin.getLines
  char_cnt = word.filter(x => x == character(0)).length
  if (char_cnt <= max_cnt.toInt && char_cnt >= min_cnt.toInt)
} yield word

println(words.length)