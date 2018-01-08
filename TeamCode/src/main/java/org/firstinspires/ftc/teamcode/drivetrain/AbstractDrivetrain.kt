package org.firstinspires.ftc.teamcode.drivetrain

import org.firstinspires.ftc.teamcode.enums.Direction
import org.firstinspires.ftc.teamcode.robot.Revbot

/**
 * Created by josh on 1/5/18.
 */

abstract class AbstractDrivetrain {
    abstract fun move(direction: DoubleArray)

    abstract fun strafe(direction: Direction, power: Double)
    abstract fun stopStrafing()
    fun strafe(power: Double) {
        strafe(Direction.LEFT, power)
    }

    fun strafe(direction: Direction, power: Double, ms: Long) {
        strafe(direction, power)
        Revbot.sleep(ms)
        stopStrafing()
    }

    abstract fun drive(direction: Direction, power: Double)
    abstract fun stopDriving()
    fun drive(power: Double) {
        drive(Direction.BACKWARD, power)
    }

    fun drive(direction: Direction, power: Double, ms: Long) {
        drive(direction, power)
        Revbot.sleep(ms)
        stopDriving()
    }

    abstract fun turn(direction: Direction, power: Double)
    abstract fun stopTurning()
    fun turn(power: Double) {
        turn(Direction.LEFT, power)
    }

    fun turn(direction: Direction, power: Double, ms: Long) {
        turn(direction, power)
        Revbot.sleep(ms)
        stopTurning()
    }

    fun stopAllMovement() {
        stopStrafing()
        stopDriving()
        stopTurning()
    }
}
