package ru.inforion.lab403.kopycat.cores.arm.instructions.cpu.rload

import ru.inforion.lab403.common.extensions.get
import ru.inforion.lab403.kopycat.cores.arm.HaveLPAE
import ru.inforion.lab403.kopycat.cores.arm.enums.Condition
import ru.inforion.lab403.kopycat.cores.arm.instructions.AARMInstruction
import ru.inforion.lab403.kopycat.cores.arm.operands.ARMRegister
import ru.inforion.lab403.kopycat.cores.base.exceptions.GeneralException
import ru.inforion.lab403.kopycat.cores.base.operands.Immediate
import ru.inforion.lab403.kopycat.modules.cores.AARMCore

class LDRDi(cpu: AARMCore,
            opcode: Long,
            cond: Condition,
            val index: Boolean,
            val add: Boolean,
            val wback: Boolean,
            val rn: ARMRegister,
            val rt1: ARMRegister,
            private val rt2: ARMRegister,
            val imm32: Immediate<AARMCore>):
        AARMInstruction(cpu, Type.VOID, cond, opcode, rn, rt1, rt2, imm32) {
    override val mnem = "LDRD$mcnd"

    override fun execute() {
        val offsetAddress = rn.value(core) + if (add) imm32.value else -imm32.value
        val address = if (index) offsetAddress else rn.value(core)

        if(HaveLPAE() && address[2..0] == 0L)
            throw GeneralException("Not implemented!")
        else {
            rt1.value(core, core.inl(address + 0))
            rt2.value(core, core.inl(address + 4))
        }
        if (wback) rn.value(core, offsetAddress)
    }
}