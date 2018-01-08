package org.firstinspires.ftc.teamcode.robot

import android.media.AudioManager
import android.media.ToneGenerator

import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo

/**
 * This is the hardware map for the 2017-2018 robot.
 */
class Revbot {

    private var hardwareMap: HardwareMap? = null

    // Define hardware
    var leftDrive: DcMotor
    var rightDrive: DcMotor
    var strafeDrive: DcMotor
    var cubeLift: DcMotor

    var clawRight: Servo
    var clawLeft: Servo
    var relicClaw: Servo
    var fondler: Servo
    var armWinch: CRServo
    var relicSlide: CRServo

    var color: ColorSensor

    private var tone: ToneGenerator? = null

    /**
     * void init() Initializes the hardware, call this method first
     * @param aHwMap HardwareMap to pass in
     */
    fun init(aHwMap: HardwareMap) {
        // Save reference to HardwareMap
        this.hardwareMap = aHwMap

        // Initalize drive motors
        leftDrive = hardwareMap!!.get(DcMotor::class.java, "leftDrive")
        rightDrive = hardwareMap!!.get(DcMotor::class.java, "rightDrive")
        strafeDrive = hardwareMap!!.get(DcMotor::class.java, "strafe")

        // Initialize other motors
        cubeLift = hardwareMap!!.get(DcMotor::class.java, "cubeLift")

        // Initialize servos
        clawRight = hardwareMap!!.get(Servo::class.java, "clawRight")
        clawLeft = hardwareMap!!.get(Servo::class.java, "clawLeft")
        relicClaw = hardwareMap!!.get(Servo::class.java, "relicClaw")

        fondler = hardwareMap!!.get(Servo::class.java, "fondler")

        armWinch = hardwareMap!!.get(CRServo::class.java, "winch")
        relicSlide = hardwareMap!!.get(CRServo::class.java, "relicSlide")

        // Initialize sensors
        color = hardwareMap!!.get(ColorSensor::class.java, "color")

        //Create a tone
        tone = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)

        // Set motor directions
        leftDrive.direction = DcMotor.Direction.REVERSE
        rightDrive.direction = DcMotor.Direction.FORWARD
        strafeDrive.direction = DcMotor.Direction.FORWARD
        cubeLift.direction = DcMotor.Direction.REVERSE

        // Initialize the motors to run without encoders.
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER)

        clawLeft.direction = Servo.Direction.REVERSE
    }

    /**
     * void beep() Make the robot play a tone.
     */
    fun beep() {
        tone!!.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE)
    }

    /**
     * void setMode(DcMotor.RunMode mode) Set all drive motors to the same mode.
     * @param mode desired motor mode.
     */
    private fun setMode(mode: DcMotor.RunMode) {
        leftDrive.mode = mode
        rightDrive.mode = mode
        strafeDrive.mode = mode
    }

    companion object {

        /**
         * This method puts the current thread to sleep for the given time in msec.
         *
         * @param sleepTime specifies sleep time in msec.
         */
        fun sleep(sleepTime: Long) {
            try {
                Thread.sleep(sleepTime)
            } catch (e: InterruptedException) {
                println("got interrupted!")
            }

        }   //sleep
    }
}
