package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp

/**
 * Drone Op Teleop code. Main teleop class.
 */

@TeleOp(name = "Drone Op", group = "teleop")
class DroneOp : AbstractRevbotDriveMethod() {
    override fun setCurrentDirection(direction: DoubleArray) {
        var left = (-gamepad1.left_stick_y).toDouble()
        var right = (-gamepad1.left_stick_y).toDouble()
        val turn = gamepad1.right_stick_x.toDouble()
        val strafe = (-gamepad1.left_stick_x).toDouble()

        left += turn
        right -= turn

        direction[0] = left
        direction[1] = right
        direction[2] = strafe

        this.currentDirection = direction
    }
}
