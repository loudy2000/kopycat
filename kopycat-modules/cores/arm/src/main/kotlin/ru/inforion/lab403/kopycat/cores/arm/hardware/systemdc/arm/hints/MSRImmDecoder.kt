package ru.inforion.lab403.kopycat.cores.arm.hardware.systemdc.arm.hints

import ru.inforion.lab403.common.extensions.asInt
import ru.inforion.lab403.common.extensions.find
import ru.inforion.lab403.common.extensions.get
import ru.inforion.lab403.common.extensions.toBool
import ru.inforion.lab403.kopycat.cores.arm.ARMExpandImm
import ru.inforion.lab403.kopycat.cores.arm.enums.Condition
import ru.inforion.lab403.kopycat.cores.arm.hardware.systemdc.decoders.ADecoder
import ru.inforion.lab403.kopycat.cores.arm.instructions.AARMInstruction
import ru.inforion.lab403.kopycat.cores.arm.operands.ARMImmediate
import ru.inforion.lab403.kopycat.cores.base.operands.Immediate
import ru.inforion.lab403.kopycat.modules.cores.AARMCore



// See B9.3.11/A8.8.111
class MSRImmDecoder(
        cpu: AARMCore,
        val constructor: (
                cpu: AARMCore,
                opcode: Long,
                cond: Condition,
                imm32: Immediate<AARMCore>,
                mask: Int,
                write_cpsr: Boolean) -> AARMInstruction) : ADecoder<AARMInstruction>(cpu) {
    override fun decode(data: Long): AARMInstruction {
        val cond = find<Condition> { it.opcode == data[31..28].asInt }?: Condition.AL
        val imm32 = ARMImmediate(ARMExpandImm(data[11..0]), true)
        val mask = data[19..16].toInt()
        val write_cpsr = data[22].toBool()
        return constructor(core, data, cond, imm32, mask, write_cpsr)
    }
}