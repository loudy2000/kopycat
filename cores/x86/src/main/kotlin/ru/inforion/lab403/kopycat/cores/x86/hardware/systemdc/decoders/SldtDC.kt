package ru.inforion.lab403.kopycat.cores.x86.hardware.systemdc.decoders

import ru.inforion.lab403.kopycat.cores.x86.hardware.systemdc.Prefixes
import ru.inforion.lab403.kopycat.cores.x86.hardware.systemdc.RMDC
import ru.inforion.lab403.kopycat.cores.x86.hardware.x86OperandStream
import ru.inforion.lab403.kopycat.cores.x86.instructions.AX86Instruction
import ru.inforion.lab403.kopycat.cores.x86.instructions.cpu.gdt.Sldt
import ru.inforion.lab403.kopycat.modules.cores.x86Core

/**
 * Created by davydov_vn on 28.12.16.
 */

class SldtDC(core: x86Core) : ADecoder<AX86Instruction>(core) {
    override fun decode(s: x86OperandStream, prefs: Prefixes): AX86Instruction {
        s.readByte()
        val rm = RMDC(s, prefs)
        val op = rm.m16
        return Sldt(core, s.data, prefs, op)
    }
}