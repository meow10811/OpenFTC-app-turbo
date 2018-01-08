package org.firstinspires.ftc.teamcode.lift

/**
 * Abstract class that all Lifts should extend from.
 */

abstract class AbstractLift {
    abstract fun raise()
    abstract fun raise(ms: Long)

    abstract fun lower()
    abstract fun lower(ms: Long)

    abstract fun stop()
}
