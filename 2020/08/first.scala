val rows = io.Source.stdin.getLines.toList

val instr_regex = raw"(nop|acc|jmp) ([+-]\d+)".r

def buildInstructions(
    v: Vector[(String, Int)],
    instructions: List[String]
): Vector[(String, Int)] = {
  if (instructions.isEmpty) {
    v
  } else {
    val instr = instructions.head match {
      case instr_regex(i, value) => (i, value.toInt)
    }
    buildInstructions(v :+ (instr), instructions.tail)
  }
}
def runVM(
    instructions: Vector[(String, Int)],
    pc: Int,
    acc: Int,
    visited: Set[Int]
): Int = {
  if (visited.contains(pc)) {
    acc
  } else {
    instructions(pc) match {
      case ("nop", value) => runVM(instructions, pc + 1, acc, visited + pc)
      case ("jmp", value) =>
        runVM(instructions, pc + value, acc, visited + pc)
      case ("acc", value) =>
        runVM(instructions, pc + 1, acc + value, visited + pc)
    }
  }
}

println(
  runVM(buildInstructions(Vector.empty[(String, Int)], rows), 0, 0, Set())
)