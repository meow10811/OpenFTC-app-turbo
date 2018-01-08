package org.firstinspires.ftc.teamcode.auto

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.claw.CubeClaw
import org.firstinspires.ftc.teamcode.claw.OneServoClaw
import org.firstinspires.ftc.teamcode.claw.RelicClaw
import org.firstinspires.ftc.teamcode.drivetrain.AbstractDrivetrain
import org.firstinspires.ftc.teamcode.drivetrain.RevbotDrivetrain
import org.firstinspires.ftc.teamcode.enums.Alliance
import org.firstinspires.ftc.teamcode.enums.Column
import org.firstinspires.ftc.teamcode.enums.Direction
import org.firstinspires.ftc.teamcode.enums.Location
import org.firstinspires.ftc.teamcode.lift.AbstractLift
import org.firstinspires.ftc.teamcode.lift.ArmWinch
import org.firstinspires.ftc.teamcode.lift.CubeLift
import org.firstinspires.ftc.teamcode.lift.RelicSlide
import org.firstinspires.ftc.teamcode.robot.Revbot
import org.firstinspires.ftc.teamcode.swivel.BallKnock
import org.firstinspires.ftc.teamcode.vuforia.Vuforia

/**
 * Abstract Autonomous class from which all Auto classes extend from.
 */

abstract class AbstractAuto internal constructor(private val alliance: Alliance, private val location: Location) : LinearOpMode() {
    private val robot = Revbot()

    private var relicClaw: OneServoClaw? = null
    private var cubeClaw: CubeClaw? = null
    private var ballKnock: BallKnock? = null
    private var drivetrain: AbstractDrivetrain? = null
    private var armWinch: AbstractLift? = null
    private var cubeLift: AbstractLift? = null
    private var relicSlide: AbstractLift? = null
    private var boxLocation: Column? = null

    @Throws(InterruptedException::class)
    override fun runOpMode() {
        robot.init(hardwareMap)
        Vuforia.init()

        relicClaw = RelicClaw(robot.relicClaw)
        cubeClaw = CubeClaw(robot.clawLeft, robot.clawRight, 0.2, 0.8)
        ballKnock = BallKnock(robot.fondler, robot.color)
        drivetrain = RevbotDrivetrain(robot)
        armWinch = ArmWinch(robot.armWinch)
        cubeLift = CubeLift(robot.cubeLift)
        relicSlide = RelicSlide(robot.relicSlide)


        cubeClaw!!.close()
        telemetry.addData("Cube Claw", cubeClaw!!.position)
        ballKnock!!.swivelCenter()

        armWinch!!.lower(4000)
        cubeLift!!.raise(125)

        robot.beep()
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        boxLocation = Vuforia.readImage()

        drivetrain!!.strafe(Direction.LEFT, 0.75, 750)
        sleep(500)

        ballKnock!!.knockBalls(alliance)
        sleep(500)

        drivetrain!!.strafe(Direction.RIGHT, 0.25, 1000)
        ballKnock!!.swivelCenter()
        armWinch!!.raise(4500)
        drivetrain!!.stopStrafing()
        sleep(500)

        drivetrain!!.drive(if (alliance == Alliance.RED) Direction.BACKWARD else Direction.FORWARD, 0.5, 750)
        sleep(500)

        drivetrain!!.strafe(Direction.RIGHT, 0.5, 750)
        sleep(500)

        cubeIntoCrypotbox()
        sleep(500)

        drivetrain!!.turn(Direction.LEFT, 0.5, 1500)
        sleep(500)

        drivetrain!!.drive(Direction.FORWARD, 0.5, 1000)
        sleep(500)

        cubeLift!!.lower(125)

        cubeClaw!!.open()

        for (i in 0..2) {
            robot.beep()
        }
    }

    private fun cubeIntoCrypotbox() {
        val direction = if (alliance == Alliance.RED) Direction.BACKWARD else Direction.BACKWARD
        when (boxLocation) {
            Column.RIGHT -> drivetrain!!.drive(direction, 0.5, 500)

            Column.CENTER -> drivetrain!!.drive(direction, 0.5, 1000)

            Column.LEFT -> drivetrain!!.drive(direction, 0.5, 1500)
        }
    }
}
