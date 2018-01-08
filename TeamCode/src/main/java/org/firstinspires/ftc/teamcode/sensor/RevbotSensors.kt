package org.firstinspires.ftc.teamcode.sensor

import com.qualcomm.robotcore.hardware.ColorSensor

/**
 * Created by josh on 1/6/18.
 */

class RevbotSensors(internal var color: ColorSensor) {

    val isBlue: Boolean
        get() = color.blue() > color.red()

    val isRed: Boolean
        get() = color.red() > color.blue()
}
