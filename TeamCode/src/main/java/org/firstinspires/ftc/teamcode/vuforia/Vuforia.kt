package org.firstinspires.ftc.teamcode.vuforia

import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables
import org.firstinspires.ftc.teamcode.enums.Column

/**
 * Class to hold Vuforia methods
 */

object Vuforia {
    internal var vuforia: VuforiaLocalizer
    private var relicTemplate: VuforiaTrackable? = null

    // Vuforia License Key
    internal val VUFORIA_LICENSE_KEY = "AV+GL7P/////AAAAGV7nYsIVuU1VqFIOfsYp0KQh9xxfhpv8vYZhVm2dOSNCK0IZ89FNdUqXUDb6FTmwosSwYv2iGyNNaeH8OGd+EYA+URkJXmtxYXTSjSxlfL7ijgu118//656cnaSAP9MIVR/y49UXnlSr9iRk2N9zUunYC4EJUpPNn6cLW4wV1t4lHtxdKHu5OQ3n7hiJVkJw+5ax0SvQ9QW6H2XcR6BpNQgN0v15zs8anuqiaRoWzV5wIqBc2NWMnmNDCuRy9de9uJPRZFglQXX5Kq1wuVH7N/B+nVRpVmJ8jnIKpEVO+nM8l7HiCfOpwdteALuWimYChVWCms06HjOZ58U3UjEHjXjELlqS9w2iYMWPOvA17HMx"

    fun init() {
        val parameters = VuforiaLocalizer.Parameters()

        parameters.vuforiaLicenseKey = VUFORIA_LICENSE_KEY
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK
        vuforia = ClassFactory.createVuforiaLocalizer(parameters)

        val relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark")
        relicTemplate = relicTrackables[0]
        relicTemplate!!.name = "relicVuMarkTemplate"
    }

    fun readImage(): Column {
        val vuMark = RelicRecoveryVuMark.from(relicTemplate)
        return if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            Column.valueOf(vuMark.name)
        } else {
            Column.CENTER
        }
    }
}
