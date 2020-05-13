package ru.inforion.lab403.kopycat.cores.mips.instructions.cop.branch

import ru.inforion.lab403.kopycat.cores.base.exceptions.GeneralException
import ru.inforion.lab403.kopycat.cores.mips.instructions.CcOffsetInsn
import ru.inforion.lab403.kopycat.cores.mips.operands.MipsImmediate
import ru.inforion.lab403.kopycat.cores.mips.operands.MipsNear
import ru.inforion.lab403.kopycat.modules.cores.MipsCore

/**
 *
 * BC2F cc, offset
 */
class bc2f(
        core: MipsCore,
        data: Long,
        cc: MipsImmediate,
        off: MipsNear) : CcOffsetInsn(core, data, Type.VOID, cc, off) {

    override val mnem = "bc2f"

    override fun execute() {
        throw GeneralException("Sorry, but I don't know how to execute this instruction!")
    }
}