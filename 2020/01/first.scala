for (tuple <- io.Source.stdin.getLines.map(_.toInt).toList.combinations(2)
     if (tuple.foldLeft(0)(_ + _) == 2020))
  println (tuple.foldLeft(1)(_ * _))
