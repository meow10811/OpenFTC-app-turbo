package org.firstinspires.ftc.teamcode.lift

import com.qualcomm.robotcore.hardware.CRServo

import org.firstinspires.ftc.teamcode.robot.Revbot

/**
 * Created by josh on 1/5/18.
 */

class RelicSlide(internal var servo: CRServo) : AbstractLift() {

    override fun raise() {
        servo.power = 1.0
    }

    override fun raise(ms: Long) {
        servo.power = 1.0
        Revbot.sleep(ms)
        stop()
    }

    override fun lower() {
        servo.power = -1.0
    }

    override fun lower(ms: Long) {
        servo.power = -1.0
        Revbot.sleep(ms)
        stop()
    }

    override fun stop() {
        servo.power = 0.0
    }
}
