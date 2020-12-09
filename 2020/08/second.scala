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
    visited: Set[Int],
    changed: Boolean
): Set[Int] = {

  if (visited.contains(pc)) {
    Set()
  } else {
    instructions(pc) match {
      case ("fin", value) => Set(acc)
      case ("nop", value) =>
        if (!changed) {
          runVM(instructions, pc + value, acc, visited + pc, true) ++
            runVM(instructions, pc + 1, acc, visited + pc, changed)
        } else
          runVM(instructions, pc + 1, acc, visited + pc, changed)
      case ("jmp", value) =>
        if (!changed) {
          runVM(instructions, pc + 1, acc, visited + pc, true) ++
            runVM(instructions, pc + value, acc, visited + pc, changed)
        } else
          runVM(instructions, pc + value, acc, visited + pc, changed)
      case ("acc", value) =>
        runVM(instructions, pc + 1, acc + value, visited + pc, changed)
    }
  }
}

runVM(
  buildInstructions(Vector.empty[(String, Int)], rows) :+ ("fin", 0),
  0,
  0,
  Set(),
  false
).map(println)