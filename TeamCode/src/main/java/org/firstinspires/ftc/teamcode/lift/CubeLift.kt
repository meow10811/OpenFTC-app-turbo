package org.firstinspires.ftc.teamcode.lift

import com.qualcomm.robotcore.hardware.DcMotor

import org.firstinspires.ftc.teamcode.robot.Revbot

/**
 * Created by josh on 1/5/18.
 */

class CubeLift(internal var motor: DcMotor) : AbstractLift() {

    override fun raise() {
        motor.power = 1.0
    }

    override fun raise(ms: Long) {
        motor.power = 1.0
        Revbot.sleep(ms)
        stop()
    }

    override fun lower() {
        motor.power = -1.0
    }

    override fun lower(ms: Long) {
        motor.power = -1.0
        Revbot.sleep(ms)
        stop()
    }

    override fun stop() {
        motor.power = 0.0
    }
}
