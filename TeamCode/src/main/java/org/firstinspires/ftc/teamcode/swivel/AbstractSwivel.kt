package org.firstinspires.ftc.teamcode.swivel

/**
 * Created by josh on 1/5/18.
 */

abstract class AbstractSwivel {
    internal var left: Double = 0.toDouble()
    internal var center: Double = 0.toDouble()
    internal var right: Double = 0.toDouble()
    abstract var position: Double

    internal constructor() {
        this.left = 0.2
        this.center = 0.5
        this.right = 0.8
    }

    internal constructor(left: Double, center: Double, right: Double) {
        this.left = left
        this.center = center
        this.right = right
    }

    abstract fun swivelLeft()
    abstract fun swivelCenter()
    abstract fun swivelRight()
}
