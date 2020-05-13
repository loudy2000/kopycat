package ru.inforion.lab403.kopycat.modules.elanSC520

import ru.inforion.lab403.common.logging.logger
import ru.inforion.lab403.kopycat.cores.base.bit
import ru.inforion.lab403.kopycat.cores.base.common.Module
import ru.inforion.lab403.kopycat.cores.base.common.ModulePorts
import ru.inforion.lab403.kopycat.cores.base.enums.Datatype.BYTE
import ru.inforion.lab403.kopycat.cores.base.exceptions.GeneralException
import ru.inforion.lab403.kopycat.cores.base.field
import ru.inforion.lab403.kopycat.modules.BUS12
import java.util.logging.Level
import java.util.logging.Level.*

/**
 *
 * SDRAM Controller
 */
class SDRAM(parent: Module, name: String) : Module(parent, name) {

    companion object {
        val log = logger(FINER)
    }

    inner class Ports : ModulePorts(this) {
        val mmcr = Slave("mmcr", BUS12)
    }

    override val ports = Ports()

    val DBCTL = object : Register(ports.mmcr, 0x40, BYTE, "DBCTL") {
        var WB_ENB by bit(0)
        var WB_FLUSH by bit(1)
        var WB_WM by field(3..2)
        var RAB_ENB by bit(4)
    }

    val ECCCTL = object : Register(ports.mmcr, 0x20, BYTE, "ECCCTL") {
        override fun write(ea: Long, ss: Int, size: Int, value: Long) {
            if (value != 0L)
                throw GeneralException("not implemented for this case!")
            super.write(ea, ss, size, value)
        }
    }

    val DRCCTL = Register(ports.mmcr, 0x10, BYTE, "DRCCTL")
    val DRCTMCTL = Register(ports.mmcr, 0x12, BYTE, "DRCTMCTL")
    val DRCCFG = Register(ports.mmcr, 0x14, BYTE, "DRCCFG")

    val DRCBENDADR0 = Register(ports.mmcr, 0x18, BYTE, "DRCBENDADR0")
    val DRCBENDADR1 = Register(ports.mmcr, 0x19, BYTE, "DRCBENDADR1")
    val DRCBENDADR2 = Register(ports.mmcr, 0x1A, BYTE, "DRCBENDADR2")
    val DRCBENDADR3 = Register(ports.mmcr, 0x1B, BYTE, "DRCBENDADR3")
}