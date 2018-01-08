package org.firstinspires.ftc.teamcode.drivetrain

import org.firstinspires.ftc.teamcode.enums.Direction
import org.firstinspires.ftc.teamcode.robot.Revbot

/**
 * Created by josh on 1/5/18.
 */

class RevbotDrivetrain(internal var robot: Revbot) : AbstractDrivetrain() {

    override fun move(direction: DoubleArray) {
        robot.leftDrive.power = direction[0]
        robot.rightDrive.power = direction[1]
        robot.strafeDrive.power = direction[2]
    }

    override fun strafe(direction: Direction, power: Double) {
        when (direction) {
            Direction.LEFT -> robot.strafeDrive.power = power
            Direction.RIGHT -> robot.strafeDrive.power = -power
            else -> robot.beep()
        }
    }

    override fun stopStrafing() {
        robot.strafeDrive.power = 0.0
    }

    override fun drive(direction: Direction, power: Double) {
        when (direction) {
            Direction.FORWARD -> {
                robot.leftDrive.power = power
                robot.rightDrive.power = power
            }
            Direction.BACKWARD -> {
                robot.leftDrive.power = -power
                robot.rightDrive.power = -power
            }
            else -> robot.beep()
        }
    }

    override fun stopDriving() {
        robot.leftDrive.power = 0.0
        robot.rightDrive.power = 0.0
    }

    override fun turn(direction: Direction, power: Double) {
        when (direction) {
            Direction.LEFT -> {
                robot.leftDrive.power = -power
                robot.rightDrive.power = power
            }
            Direction.RIGHT -> {
                robot.leftDrive.power = power
                robot.rightDrive.power = -power
            }
            else -> robot.beep()
        }
    }

    override fun stopTurning() {
        robot.leftDrive.power = 0.0
        robot.rightDrive.power = 0.0
    }
}
