package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.claw.CubeClaw
import org.firstinspires.ftc.teamcode.claw.OneServoClaw
import org.firstinspires.ftc.teamcode.claw.RelicClaw
import org.firstinspires.ftc.teamcode.drivetrain.AbstractDrivetrain
import org.firstinspires.ftc.teamcode.drivetrain.RevbotDrivetrain
import org.firstinspires.ftc.teamcode.lift.AbstractLift
import org.firstinspires.ftc.teamcode.lift.ArmWinch
import org.firstinspires.ftc.teamcode.lift.CubeLift
import org.firstinspires.ftc.teamcode.lift.RelicSlide
import org.firstinspires.ftc.teamcode.robot.Revbot
import org.firstinspires.ftc.teamcode.swivel.BallKnock

/**
 * Drive method (TeleOp) from which all methods extend from.
 */

abstract class AbstractRevbotDriveMethod : LinearOpMode() {
    private val robot = Revbot()
    private val inputHandler = InputHandler()

    // Double[] for saving joystick position and replicating direction/power
    // [0] is left value, [1] is right value, [2] is strafe value
    private var savedDirection = DoubleArray(2)
    internal var currentDirection = DoubleArray(2)
    private var gearValue = 1.0
        set(gearValue) = if (gearValue <= 1.0 || gearValue >= 0.0) {
            field = gearValue
        } else {
            field = Math.round(gearValue).toDouble()
        }

    private fun gear(direction: DoubleArray): DoubleArray {
        for (i in 1 until direction.size)
            direction[i] *= this.gearValue
        return direction
    }

    abstract fun setCurrentDirection(direction: DoubleArray)
    private fun getCurrentDirection(): DoubleArray {
        return currentDirection
    }

    private fun lockFourAxis(direction: DoubleArray): DoubleArray {
        if (Math.abs(direction[0]) >= Math.abs(direction[2])) {
            direction[2] = 0.0
        } else {
            direction[0] = 0.0
            direction[1] = 0.0
        }

        return direction
    }

    @Throws(InterruptedException::class)
    override fun runOpMode() {
        robot.init(hardwareMap)
        inputHandler.init(robot)

        val drivetrain = RevbotDrivetrain(robot)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            setCurrentDirection(getCurrentDirection())
            inputHandler.handleInput()

            if (inputHandler.useSavedDirection) {
                setCurrentDirection(savedDirection)
            }

            if (inputHandler.lockFourAxis) {
                setCurrentDirection(lockFourAxis(getCurrentDirection()))
            }

            setCurrentDirection(gear(getCurrentDirection()))

            drivetrain.move(getCurrentDirection())
        }
    }

    /**
     * Controls:
     *
     * Gamepad 1:
     * LB: Activate smartDirect
     * LT: Set hyperPrecision
     *
     * RB: Save current drive/strafeDrivePower to directSave
     * RT: (directSave) Control power
     *
     * LStick-x: Set strafeDrivePower
     * LStick-y: Set left/rightPower
     *
     * RStick-x: Set turnPower
     *
     * Up: Raise cube lift
     * Down: Lower cube lift
     * Left: Close claw
     * Right: Open claw
     */

    internal inner class InputHandler {
        var relicClaw: OneServoClaw
        var cubeClaw: CubeClaw
        var ballKnock: BallKnock
        var armWinch: AbstractLift
        var cubeLift: AbstractLift
        var relicSlide: AbstractLift

        var lockFourAxis = false
        var useSavedDirection = false

        fun init(robot: Revbot) {
            relicClaw = RelicClaw(robot.relicClaw)
            cubeClaw = CubeClaw(robot.clawLeft, robot.clawRight, 0.2, 0.8)
            ballKnock = BallKnock(robot.fondler, robot.color)
            armWinch = ArmWinch(robot.armWinch)
            cubeLift = CubeLift(robot.cubeLift)
            relicSlide = RelicSlide(robot.relicSlide)
        }

        fun handleInput() {
            if (gamepad1.x) {
                armWinch.raise()
            } else if (gamepad1.a) {
                armWinch.lower()
            } else {
                armWinch.stop()
            }

            if (gamepad1.dpad_up) {
                cubeLift.raise()
            } else if (gamepad1.dpad_down) {
                cubeLift.lower()
            } else {
                cubeLift.stop()
            }

            if (gamepad1.dpad_left) {
                cubeClaw.open()
            } else if (gamepad1.dpad_right) {
                cubeClaw.close()
            }

            if (gamepad1.left_bumper) {
                gearValue = 0.5
            }

            if (gamepad1.right_bumper) {
                lockFourAxis(currentDirection)
            }

            if (gamepad1.left_trigger > MIN_TRIGGER_VALUE)
                savedDirection = getCurrentDirection()

            useSavedDirection = gamepad1.right_trigger > MIN_TRIGGER_VALUE


            if (gamepad2.x) {
                ballKnock.swivelLeft()
            } else if (gamepad2.y) {
                ballKnock.swivelCenter()
            } else if (gamepad2.b) {
                ballKnock.swivelRight()
            }

            if (gamepad2.a) {
                if (gamepad2.left_trigger > MIN_TRIGGER_VALUE) {
                    cubeClaw.openLeft()
                } else {
                    cubeClaw.closeLeft()
                }

                if (gamepad2.right_trigger > MIN_TRIGGER_VALUE) {
                    cubeClaw.openRight()
                } else {
                    cubeClaw.closeRight()
                }
            }

            if (gamepad2.dpad_up) {
                relicSlide.raise()
            } else if (gamepad2.dpad_down) {
                relicSlide.lower()
            } else {
                relicSlide.stop()
            }

            if (gamepad2.dpad_left) {
                relicClaw.open()
            } else if (gamepad2.dpad_right) {
                relicClaw.close()
            }

            if (gamepad2.left_bumper) {
                gearValue = gearValue - 0.1
            } else if (gamepad2.right_bumper) {
                gearValue = gearValue + 0.1
            }
        }

        companion object {

            val MIN_TRIGGER_VALUE = 0.1
        }
    }
}