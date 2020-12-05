val rows = io.Source.stdin.getLines.toList
val width = rows(0).length
val configs = Seq((1,1), (3,1), (5,1), (7,1), (1,2))

val tmp = for (c <- configs;
               i <- 0 until rows.length / c._2
               if rows(i*c._2).toList((i* c._1) % width) == '#'
) yield c

println(tmp.groupBy(identity).mapValues(_.size).foldLeft(1.toLong)(_ * _._2.toLong))

