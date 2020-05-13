package ru.inforion.lab403.kopycat.cores.mips.instructions.fpu.move

import ru.inforion.lab403.kopycat.cores.mips.instructions.RtRdSelInsn
import ru.inforion.lab403.kopycat.cores.mips.operands.GPR
import ru.inforion.lab403.kopycat.cores.mips.operands.MipsRegister
import ru.inforion.lab403.kopycat.modules.cores.MipsCore

/**
 *
 * CFC1 rt, fs
 */
class cfc1(
        core: MipsCore,
        data: Long,
        rt: GPR,
        rd: MipsRegister<*>
) : RtRdSelInsn(core, data, Type.VOID, rt, rd) {

    override val mnem = "cfc1"

    override fun execute() {
        vrt = vrd
    }
}