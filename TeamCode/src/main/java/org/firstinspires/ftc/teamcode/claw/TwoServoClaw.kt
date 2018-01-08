package org.firstinspires.ftc.teamcode.claw

import com.qualcomm.robotcore.hardware.Servo

/**
 * An abstract 2-servo claw.
 */

open class TwoServoClaw @JvmOverloads internal constructor(servo1: Servo, servo2: Servo, closedPosition: Double = 0.0, openPosition: Double = 1.0) : Claw() {
    internal var claw1: OneServoClaw
    internal var claw2: OneServoClaw

    override var position: Double
        get() = claw1.position
        set(position) {
            claw1.position = position
            claw2.position = position
        }

    init {
        this.CLOSED_POSITION = closedPosition
        this.OPEN_POSITION = openPosition
        claw1 = OneServoClaw(servo1, closedPosition, openPosition)
        claw2 = OneServoClaw(servo2, closedPosition, openPosition)
    }

    override fun open() {
        claw1.open()
        claw2.open()
    }

    override fun close() {
        claw1.close()
        claw2.close()
    }
}
