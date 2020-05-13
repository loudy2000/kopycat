package ru.inforion.lab403.kopycat.cores.ppc.instructions.cpu.base.arithmInt

import ru.inforion.lab403.common.extensions.toBool
import ru.inforion.lab403.common.extensions.toInt
import ru.inforion.lab403.common.extensions.toLong
import ru.inforion.lab403.kopycat.cores.base.enums.Datatype
import ru.inforion.lab403.kopycat.cores.base.operands.AOperand
import ru.inforion.lab403.kopycat.cores.ppc.flags.FlagProcessor
import ru.inforion.lab403.kopycat.cores.ppc.instructions.APPCInstruction
import ru.inforion.lab403.kopycat.cores.ppc.operands.PPCVariable
import ru.inforion.lab403.kopycat.modules.cores.PPCCore



//Add extended
class addex(core: PPCCore, val overflow: Boolean, val record: Boolean, vararg operands: AOperand<PPCCore>):
        APPCInstruction(core, Type.VOID, *operands) {
    override val mnem = "adde${if (overflow) "o" else ""}${if (record) "." else ""}"
    override fun toString(): String = "$mnem $op1, $op2, $op3"

    private val result = PPCVariable(Datatype.DWORD)

    override fun execute() {
        val res = op2.value(core) + op3.value(core) + core.cpu.xerBits.CA.toLong()
        result.value(core, res)

        op1.value(core, result)

        FlagProcessor.processCarry(core, result)

        if (record)
            FlagProcessor.processCR0(core, result)

        if (overflow)
            FlagProcessor.processOverflow(core, result)
    }
}