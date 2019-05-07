package ru.inforion.lab403.kopycat.cores.v850es.instructions.cpu.arithm

import ru.inforion.lab403.kopycat.cores.base.enums.Datatype
import ru.inforion.lab403.kopycat.cores.base.operands.AOperand
import ru.inforion.lab403.kopycat.cores.v850es.exceptions.v850ESHardwareException
import ru.inforion.lab403.kopycat.cores.v850es.hardware.flags.FlagProcessor
import ru.inforion.lab403.kopycat.cores.v850es.instructions.AV850ESInstruction
import ru.inforion.lab403.kopycat.cores.v850es.operands.v850esVariable
import ru.inforion.lab403.kopycat.modules.cores.v850ESCore

/**
 * Created by user on 29.05.17.
 */

class Divu(core: v850ESCore, size: Int, vararg operands: AOperand<v850ESCore>):
        AV850ESInstruction(core, Type.VOID, size, *operands) {
    override val mnem = "divu"

    override val ovChg = true
    override val sChg = true
    override val zChg = true


    private val result = v850esVariable(Datatype.DWORD)

    // Format XI - reg1, reg2, reg3
    override fun execute() {
        val a1 = op1.value(core)
        if (a1 == 0L) throw v850ESHardwareException.DivisionByZero
        result.value(core, op2.zext(core) / a1)
        val res2 = op2.zext(core) % a1
        FlagProcessor.processDivFlag(core, result, op1, op2)
        op2.value(core, result)
        op3.value(core, res2)
    }
}