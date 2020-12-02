for (tuple <- io.Source.stdin.getLines.map(_.toInt).toList.combinations(3)
     if (tuple.sum == 2020))
  println (tuple.product)
