package ru.inforion.lab403.kopycat.cores.x86.instructions.cpu.bitwise

import ru.inforion.lab403.common.extensions.get
import ru.inforion.lab403.common.extensions.insert
import ru.inforion.lab403.kopycat.cores.base.operands.AOperand
import ru.inforion.lab403.kopycat.cores.base.operands.Variable
import ru.inforion.lab403.kopycat.cores.x86.hardware.flags.FlagProcessor
import ru.inforion.lab403.kopycat.cores.x86.hardware.systemdc.Prefixes
import ru.inforion.lab403.kopycat.cores.x86.instructions.AX86Instruction
import ru.inforion.lab403.kopycat.modules.cores.x86Core

/**
 * Created by davydov_vn on 26.09.16.
 */
class Ror(core: x86Core, opcode: ByteArray, prefs: Prefixes, vararg operands: AOperand<x86Core>):
        AX86Instruction(core, Type.VOID, opcode, prefs, *operands) {
    override val mnem = "ror"

    override val cfChg = true
    override val ofChg = true

    override fun execute() {
        // http://stackoverflow.com/questions/10395071/what-is-the-difference-between-rcr-and-ror
        val a1 = op1.value(core)
        val a2 = (op2.value(core) % op1.dtyp.bits).toInt()
        val lowPart = a1[a2 - 1..0]
        val msb = op1.dtyp.bits
        val lsb = op1.dtyp.bits - a2
        val res = a1.shr(a2).insert(lowPart, msb..lsb)
        val result = Variable<x86Core>(0, op1.dtyp)
        result.value(core, res)
        FlagProcessor.processRotateFlag(core, result, op2, false, res[op1.dtyp.bits - 1] == 1L)
        op1.value(core, res)
    }
}