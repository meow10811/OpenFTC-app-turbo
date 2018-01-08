package org.firstinspires.ftc.teamcode.claw

import com.qualcomm.robotcore.hardware.Servo

/**
 * A cube claw.
 */

class CubeClaw : TwoServoClaw {
    constructor(leftClaw: Servo, rightClaw: Servo) : super(leftClaw, rightClaw) {}

    constructor(leftClaw: Servo, rightClaw: Servo, closedPosition: Double, openPosition: Double) : super(leftClaw, rightClaw, closedPosition, openPosition) {}

    fun openLeft() {
        claw1.open()
    }

    fun closeLeft() {
        claw1.close()
    }

    fun openRight() {
        claw2.open()
    }

    fun closeRight() {
        claw2.close()
    }
}
