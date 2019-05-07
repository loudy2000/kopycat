package ru.inforion.lab403.kopycat.cores.mips.instructions.fpu.arith

import ru.inforion.lab403.common.extensions.ieee754
import ru.inforion.lab403.kopycat.cores.mips.instructions.FdFsFtInsn
import ru.inforion.lab403.kopycat.cores.mips.operands.FPR
import ru.inforion.lab403.kopycat.modules.cores.MipsCore

/**
 * Created by batman on 11.04.2017.
 */
class div_d(core: MipsCore,
            data: Long,
            fd: FPR,
            fs: FPR,
            ft: FPR) : FdFsFtInsn(core, data, Type.VOID, fd, fs, ft) {

    override val mnem = "div.d"

    override fun execute() {
//        log.warning { "[%08X] $mnem".format(cpu.pc) }
        dfd = (dfs.ieee754() / dft.ieee754()).ieee754()
    }
}