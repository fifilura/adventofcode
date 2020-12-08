val rows = io.Source.stdin.getLines.toList

val instr_regex = raw"(nop|acc|jmp) ([+-]\d+)".r

def buildInstructions(
    v: Vector[(String, Int)],
    cnt: Int,
    instructions: List[String]
): Vector[(String, Int)] = {
  if (instructions.isEmpty) {
    v
  } else {
    val instr = instructions.head match {
      case instr_regex(i, value) => (i, value.toInt)
    }
    buildInstructions(v :+ (instr), cnt + 1, instructions.tail)
  }
}
def runVM(
    instructions: Vector[(String, Int)],
    pc: Int,
    acc: Int,
    visited: Set[Int],
    changed: Boolean
): Option[Int] = {

  if (visited.contains(pc)) {
    None
  } else {
    instructions(pc) match {
      case ("fin", value) => Some(acc)
      case ("nop", value) =>
        if (!changed) {
          runVM(instructions, pc + value, acc, visited + pc, true)
            .orElse(runVM(instructions, pc + 1, acc, visited + pc, changed))
        } else
          runVM(instructions, pc + 1, acc, visited + pc, changed)
      case ("jmp", value) =>
        if (!changed) {
          runVM(instructions, pc + 1, acc, visited + pc, true).orElse(
            runVM(instructions, pc + value, acc, visited + pc, changed)
          )
        } else
          runVM(instructions, pc + value, acc, visited + pc, changed)
      case ("acc", value) =>
        runVM(instructions, pc + 1, acc + value, visited + pc, changed)
    }
  }
}

println(
  runVM(
    buildInstructions(Vector.empty[(String, Int)], 0, rows) :+ ("fin", 0),
    0,
    0,
    Set(),
    false
  ).getOrElse(-1)
)