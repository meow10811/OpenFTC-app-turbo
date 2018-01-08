package org.firstinspires.ftc.teamcode.claw

import com.qualcomm.robotcore.hardware.Servo

/**
 * Created by josh on 1/5/18.
 */

open class OneServoClaw @JvmOverloads internal constructor(private val clawServo: Servo, closedPosition: Double = 0.0, openPosition: Double = 1.0) : Claw() {
    override var position: Double
        get() = clawServo.position
        set(position) {
            clawServo.position = position
        }

    init {
        this.CLOSED_POSITION = closedPosition
        this.OPEN_POSITION = openPosition
    }

    override fun open() {
        clawServo.position = OPEN_POSITION
    }

    override fun close() {
        clawServo.position = CLOSED_POSITION
    }
}
