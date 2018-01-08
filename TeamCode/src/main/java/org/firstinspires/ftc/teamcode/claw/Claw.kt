package org.firstinspires.ftc.teamcode.claw

/**
 * Abstract Claw
 */

abstract class Claw {
    internal var CLOSED_POSITION: Double = 0.toDouble()
    internal var OPEN_POSITION: Double = 0.toDouble()
    abstract var position: Double

    abstract fun open()
    abstract fun close()
}
