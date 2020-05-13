package ru.inforion.lab403.kopycat.cores.mips.instructions.fpu.arith

import ru.inforion.lab403.common.extensions.ieee754
import ru.inforion.lab403.kopycat.cores.mips.instructions.FdFsFtInsn
import ru.inforion.lab403.kopycat.cores.mips.operands.FPR
import ru.inforion.lab403.kopycat.modules.cores.MipsCore


class add_s(core: MipsCore,
            data: Long,
            fd: FPR,
            fs: FPR,
            ft: FPR) : FdFsFtInsn(core, data, Type.VOID, fd, fs, ft) {

    override val mnem = "add.s"

    override fun execute() {
//        log.warning { "[%08X] $mnem".format(cpu.pc) }
        vfd = (vfs.ieee754() + vft.ieee754()).ieee754()
    }
}