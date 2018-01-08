package org.firstinspires.ftc.teamcode.swivel

import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.Servo

import org.firstinspires.ftc.teamcode.enums.Alliance
import org.firstinspires.ftc.teamcode.robot.Revbot
import org.firstinspires.ftc.teamcode.sensor.RevbotSensors

/**
 * BallKnock class. Has knockBalls(Alliance alliance)
 */

class BallKnock : AbstractSwivel {
    private var servo: Servo? = null
    private val sensors: RevbotSensors

    override var position: Double
        get() = servo!!.position
        set(position) {
            servo!!.position = position
        }

    constructor(servo: Servo, color: ColorSensor) : super() {
        this.servo = servo
        this.sensors = RevbotSensors(color)
    }

    constructor(servo: Servo, color: ColorSensor, left: Double, center: Double, right: Double) : super(left, center, right) {
        this.servo = servo
    }

    override fun swivelLeft() {
        servo!!.position = left
    }

    override fun swivelCenter() {
        servo!!.position = center
    }

    override fun swivelRight() {
        servo!!.position = right
    }

    fun knockBalls(alliance: Alliance) {

        if (alliance == Alliance.BLUE && sensors.isBlue || alliance == Alliance.RED && sensors.isRed) {
            swivelRight()
        } else if (alliance == Alliance.BLUE && sensors.isRed || alliance == Alliance.RED && sensors.isBlue) {
            swivelLeft()
        } else {
            swivelRight()
            swivelLeft()
        }

        Revbot.sleep(1000)

    }
}
