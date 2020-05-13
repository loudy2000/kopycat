package ru.inforion.lab403.kopycat.cores.arm.hardware.systemdc.arm.loadstore

import ru.inforion.lab403.common.extensions.asInt
import ru.inforion.lab403.common.extensions.find
import ru.inforion.lab403.common.extensions.get
import ru.inforion.lab403.kopycat.cores.arm.enums.Condition
import ru.inforion.lab403.kopycat.cores.arm.exceptions.ARMHardwareException
import ru.inforion.lab403.kopycat.cores.arm.hardware.registers.GPRBank
import ru.inforion.lab403.kopycat.cores.arm.hardware.systemdc.decoders.ADecoder
import ru.inforion.lab403.kopycat.cores.arm.instructions.AARMInstruction
import ru.inforion.lab403.kopycat.cores.arm.operands.ARMImmediate
import ru.inforion.lab403.kopycat.cores.arm.operands.ARMRegister
import ru.inforion.lab403.kopycat.cores.base.operands.Immediate
import ru.inforion.lab403.kopycat.modules.cores.AARMCore



// See A8.8.75
class LoadExclusiveDecoder(
        cpu: AARMCore,
        val constructor: (
                cpu: AARMCore,
                opcode: Long,
                cond: Condition,
                rn: ARMRegister,
                rt: ARMRegister,
                imm32: Immediate<AARMCore>) -> AARMInstruction) : ADecoder<AARMInstruction>(cpu) {

    // A1
    override fun decode(data: Long): AARMInstruction {
        val cond = find<Condition> { it.opcode == data[31..28].asInt } ?: Condition.AL

        val rn = GPRBank.Operand(data[19..16].asInt)
        val rt = GPRBank.Operand(data[15..12].asInt)
        val imm32 = Immediate<AARMCore>(0L)

        val pc = core.cpu.regs.pc.reg

        if (rt.reg == pc || rn.reg == pc) throw ARMHardwareException.Unpredictable

        return constructor(core, data, cond, rn, rt, imm32)
    }
}