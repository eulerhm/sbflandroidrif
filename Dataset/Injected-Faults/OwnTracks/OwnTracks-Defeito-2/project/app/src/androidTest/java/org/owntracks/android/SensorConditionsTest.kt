package org.owntracks.android

import android.Manifest
import android.app.NotificationManager
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.PowerManager
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.BeforeClass
import org.junit.Rule
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.io.Writer

open class SensorConditionsTest {
    @get:Rule
    var mRuntimePermissionRule = GrantPermissionRule.grant(
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    companion object {
        private const val LOCATION = "Location"
        private const val WIFION = "ParcTE_v2"
        private const val WIFIOFF = "Wi-Fi"
        private const val MOBILEDATA = "Mobile data"
        private const val BLUETOOTH = "Bluetooth"
        private const val BATTERYSAVER = "Battery Saver"
        private const val AUTOROTATE = "Auto-rotate"
        private const val DONOTDISTURB = "Do Not Disturb"
        private const val CAMERAON = "Disable Camera"
        private const val CAMERAOFF = "Enable Camera"
        private var lastAccelerometerStatus = false
        private var lastGyroscopeStatus = false
        private var lastLightStatus = false
        private var lastMagneticFieldStatus = false
        private var lastOrientationStatus = false
        private var lastProximityStatus = false
        private var lastCameraStatus = false
        private var lastLocationStatus = false
        private var lastWifiStatus = false
        private var lastMobileDataStatus = false
        private var lastBluetoothStatus = false
        private var lastBatterySaverStatus = false
        private var lastAutoRotateStatus = false
        private var lastDoNotDisturbStatus = false
        @BeforeClass @JvmStatic
        @Throws(UiObjectNotFoundException::class)
        fun setUpSensorConditions() {
            val mDevice: UiDevice
            val accelerometerEnabled: Boolean
            val gyroscopeEnabled: Boolean
            val lightEnabled: Boolean
            val magneticFieldEnabled: Boolean
            val orientationEnabled: Boolean
            val proximityEnabled: Boolean
            val cameraEnabled: Boolean
            val locationEnabled: Boolean
            val wifiEnabled: Boolean
            val mobileDataEnabled: Boolean
            val bluetoothEnabled: Boolean
            val batterySaverEnabled: Boolean
            val autoRotateEnabled: Boolean
            val doNotDisturbEnabled: Boolean
            val UIObjectLocation: UiObject
            var UIObjectWifi: UiObject
            var UIObjectMobiledata: UiObject
            var UIObjectBluetooth: UiObject
            val UIObjectBatterySaver: UiObject
            val UIObjectAutoRotate: UiObject
            var UIObjectDoNotDisturb: UiObject
            val UIObjectCamera: UiObject
            accelerometerEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("accelerometerEnabled")
            )
            gyroscopeEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("gyroscopeEnabled")
            )
            lightEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("lightEnabled")
            )
            magneticFieldEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("magneticFieldEnabled")
            )
            orientationEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("orientationEnabled")
            )
            proximityEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("proximityEnabled")
            )
            cameraEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("cameraEnabled")
            )
            locationEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("locationEnabled")
            )
            wifiEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("wifiEnabled")
            )
            mobileDataEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("mobileDataEnabled")
            )
            bluetoothEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("bluetoothEnabled")
            )
            batterySaverEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("batterySaverEnabled")
            )
            autoRotateEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("autoRotateEnabled")
            )
            doNotDisturbEnabled = java.lang.Boolean.parseBoolean(
                InstrumentationRegistry.getArguments().getString("doNotDisturbEnabled")
            )
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            mDevice.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)), 1000)
            verifyAllSensors(mDevice)
            mDevice.pressBack()
            lastCameraStatus = verifyCamera(mDevice)
            mDevice.pressBack()
            lastLocationStatus = verifyLocation()
            lastWifiStatus = verifyWifi()
            lastMobileDataStatus = verifyMobileData()
            lastBluetoothStatus = verifyBluetooth()
            lastBatterySaverStatus = verifyBatterySaver()
            lastAutoRotateStatus = verifyAutoRotate()
            lastDoNotDisturbStatus = verifyDoNotDisturb()

            //Location
            if (locationEnabled) {
                if (!lastLocationStatus) {
                    mDevice.openQuickSettings()
                    UIObjectLocation =
                        mDevice.findObject(UiSelector().text(LOCATION)) //For Location (GPS)
                    UIObjectLocation.click()
                    lastLocationStatus = true
                }
            } else {
                if (lastLocationStatus) {
                    mDevice.openQuickSettings()
                    UIObjectLocation =
                        mDevice.findObject(UiSelector().text(LOCATION)) //For Location (GPS)
                    UIObjectLocation.click()
                    lastLocationStatus = false
                }
            }

            //Wi-Fi
            if (wifiEnabled) {
                if (!lastWifiStatus) {
                    mDevice.openQuickSettings()
                    UIObjectWifi = mDevice.findObject(UiSelector().text(WIFIOFF))
                    UIObjectWifi.click()
                    UIObjectWifi = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectWifi.click()
                    lastWifiStatus = true
                }
            } else {
                if (lastWifiStatus) {
                    mDevice.openQuickSettings()
                    UIObjectWifi = mDevice.findObject(UiSelector().text(WIFION))
                    UIObjectWifi.click()
                    UIObjectWifi = mDevice.findObject(UiSelector().text("ON"))
                    UIObjectWifi.click()
                    UIObjectWifi = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectWifi.click()
                    lastWifiStatus = false
                }
            }

            //Mobile Data
            if (mobileDataEnabled) {
                if (!lastMobileDataStatus) {
                    mDevice.openQuickSettings()
                    UIObjectMobiledata = mDevice.findObject(UiSelector().text(MOBILEDATA))
                    UIObjectMobiledata.click()
                    UIObjectMobiledata = mDevice.findObject(UiSelector().text("OFF"))
                    UIObjectMobiledata.click()
                    UIObjectMobiledata = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectMobiledata.click()
                    lastMobileDataStatus = true
                }
            } else {
                if (lastMobileDataStatus) {
                    mDevice.openQuickSettings()
                    UIObjectMobiledata = mDevice.findObject(UiSelector().text(MOBILEDATA))
                    UIObjectMobiledata.click()
                    UIObjectMobiledata = mDevice.findObject(UiSelector().text("ON"))
                    UIObjectMobiledata.click()
                    UIObjectMobiledata = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectMobiledata.click()
                    lastMobileDataStatus = false
                }
            }

            //Bluetooth
            if (bluetoothEnabled) {
                if (!lastBluetoothStatus) {
                    mDevice.openQuickSettings()
                    UIObjectBluetooth = mDevice.findObject(UiSelector().text(BLUETOOTH))
                    UIObjectBluetooth.click()
                    UIObjectBluetooth = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectBluetooth.click()
                    lastBluetoothStatus = true
                }
            } else {
                if (lastBluetoothStatus) {
                    mDevice.openQuickSettings()
                    UIObjectBluetooth = mDevice.findObject(UiSelector().text(BLUETOOTH))
                    UIObjectBluetooth.click()
                    UIObjectBluetooth = mDevice.findObject(UiSelector().text("ON"))
                    UIObjectBluetooth.click()
                    UIObjectBluetooth = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectBluetooth.click()
                    lastBluetoothStatus = false
                }
            }

            //Battery Saver
            if (batterySaverEnabled) {
                if (!lastBatterySaverStatus) {
                    mDevice.openQuickSettings()
                    UIObjectBatterySaver = mDevice.findObject(UiSelector().text(BATTERYSAVER))
                    UIObjectBatterySaver.click()
                    lastBatterySaverStatus = true
                }
            } else {
                if (lastBatterySaverStatus) {
                    mDevice.openQuickSettings()
                    UIObjectBatterySaver = mDevice.findObject(UiSelector().text(BATTERYSAVER))
                    UIObjectBatterySaver.click()
                    lastBatterySaverStatus = false
                }
            }

            //Auto Rotate
            if (autoRotateEnabled) {
                if (!lastAutoRotateStatus) {
                    mDevice.openQuickSettings()
                    UIObjectAutoRotate = mDevice.findObject(UiSelector().text(AUTOROTATE))
                    UIObjectAutoRotate.click()
                    lastAutoRotateStatus = true
                }
            } else {
                if (lastAutoRotateStatus) {
                    mDevice.openQuickSettings()
                    UIObjectAutoRotate = mDevice.findObject(UiSelector().text(AUTOROTATE))
                    UIObjectAutoRotate.click()
                    lastAutoRotateStatus = false
                }
            }

            //Do Not Disturb
            if (doNotDisturbEnabled) {
                if (!lastDoNotDisturbStatus) {
                    mDevice.openQuickSettings()
                    UIObjectDoNotDisturb = mDevice.findObject(UiSelector().text(DONOTDISTURB))
                    UIObjectDoNotDisturb.click()
                    UIObjectDoNotDisturb = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectDoNotDisturb.click()
                    lastDoNotDisturbStatus = true
                }
            } else {
                if (lastDoNotDisturbStatus) {
                    mDevice.openQuickSettings()
                    UIObjectDoNotDisturb = mDevice.findObject(UiSelector().text(DONOTDISTURB))
                    UIObjectDoNotDisturb.click()
                    UIObjectDoNotDisturb = mDevice.findObject(UiSelector().text("ON"))
                    UIObjectDoNotDisturb.click()
                    UIObjectDoNotDisturb = mDevice.findObject(UiSelector().text("Done"))
                    UIObjectDoNotDisturb.click()
                    lastDoNotDisturbStatus = false
                }
            }

            //Camera
            if (cameraEnabled) {
                if (!lastCameraStatus) {
                    mDevice.openQuickSettings()
                    UIObjectCamera = mDevice.findObject(UiSelector().text(CAMERAOFF))
                    UIObjectCamera.click()
                    lastCameraStatus = true
                }
            } else {
                if (lastCameraStatus) {
                    mDevice.openQuickSettings()
                    UIObjectCamera = mDevice.findObject(UiSelector().text(CAMERAON))
                    UIObjectCamera.click()
                    lastCameraStatus = true
                }
            }
            mDevice.pressHome()
            val UIObjectSensorDisablerApp = mDevice.findObject(UiSelector().text("Sensor Disabler"))
            UIObjectSensorDisablerApp.click()
            enableDisableSensor(mDevice, "Accelerometer", accelerometerEnabled)
            enableDisableSensor(mDevice, "Gyroscope", gyroscopeEnabled)
            enableDisableSensor(mDevice, "Light", lightEnabled)
            enableDisableSensor(mDevice, "Magnetic Field", magneticFieldEnabled)
            enableDisableSensor(mDevice, "Orientation", orientationEnabled)
            enableDisableSensor(mDevice, "Proximity", proximityEnabled)
            mDevice.pressHome()
        }

        private fun enableDisableSensor(
            mDevice: UiDevice,
            sensorName: String,
            sensorEnabled: Boolean
        ) {
            if (sensorEnabled) {
                if (!getLastSensorStatus(sensorName)) {
                    val UIObjectSensorDisAppMenu =
                        mDevice.findObject(UiSelector().description("OK"))
                    changeSpecificSensor(
                        mDevice,
                        UIObjectSensorDisAppMenu,
                        sensorName,
                        "Do nothing"
                    )
                    updateLastSensorStatus(sensorName, true)
                }
            } else {
                if (getLastSensorStatus(sensorName)) {
                    val UIObjectSensorDisAppMenu =
                        mDevice.findObject(UiSelector().description("OK"))
                    changeSpecificSensor(
                        mDevice,
                        UIObjectSensorDisAppMenu,
                        sensorName,
                        "Remove sensor"
                    )
                    updateLastSensorStatus(sensorName, false)
                }
            }
        }

        private fun runExternalCommand(command: String) {
            var tempScript: File? = null
            try {
                tempScript = createTempScript(command)
            } catch (e: IOException) {
                println("Passou aq")
                e.printStackTrace()
            }
            try {
                val pb = ProcessBuilder("bash", tempScript.toString())
                pb.inheritIO()
                val process = pb.start()
                process.waitFor()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                tempScript!!.delete()
            }
        }

        @Throws(IOException::class)
        fun createTempScript(command: String?): File {
            val tempScript = File.createTempFile("script", null)
            val streamWriter: Writer = OutputStreamWriter(
                FileOutputStream(
                    tempScript
                )
            )
            val printWriter = PrintWriter(streamWriter)
            printWriter.println("#!/bin/bash")
            printWriter.println(command)
            printWriter.println("exit")
            printWriter.close()
            return tempScript
        }

        private fun changeSpecificSensor(
            mDevice: UiDevice, UIObjectSensorDisAppMenu: UiObject,
            sensorName: String, changeOption: String
        ) {
            val UIObjectSensorDisAppMenuItem: List<UiObject2>
            val UIObjectSensorDisablerOptions: UiObject
            val UIObjectSaveOptions: UiObject
            var scrolledFirstMenuItem = false
            try {
                UIObjectSensorDisAppMenu.click()
                if (sensorName.equals("Orientation", ignoreCase = true) ||
                    sensorName.equals("Proximity", ignoreCase = true) ||
                    sensorName.equals("Light", ignoreCase = true) ||   //For Moto X2
                    sensorName.equals("Magnetic Field", ignoreCase = true) //For Moto X2
                ) {
                    val appView = UiScrollable(UiSelector().scrollable(true))
                    appView.scrollIntoView(UiSelector().text(sensorName))
                    scrolledFirstMenuItem = true
                }
                UIObjectSensorDisAppMenuItem = mDevice.findObjects(By.text(sensorName))
                for (obj in UIObjectSensorDisAppMenuItem) {
                    if (obj.isClickable) obj.click()
                }
                UIObjectSensorDisablerOptions = mDevice.findObject(UiSelector().text(changeOption))
                UIObjectSensorDisablerOptions.click()
                UIObjectSaveOptions = mDevice.findObject(
                    UiSelector().className("android.widget.ImageButton").instance(2)
                )
                UIObjectSaveOptions.click()

                //Need to scroll to the first menu item
                if (scrolledFirstMenuItem) {
                    UIObjectSensorDisAppMenu.click()
                    val appView = UiScrollable(UiSelector().scrollable(true))
                    appView.scrollIntoView(UiSelector().text("Accelerometer"))
                    val UIObjectSensorDisAppCancel =
                        mDevice.findObject(UiSelector().description("Cancel"))
                    UIObjectSensorDisAppCancel.click()
                }
            } catch (e: UiObjectNotFoundException) {
                e.printStackTrace()
            }
        }

        private fun getLastSensorStatus(sensorName: String): Boolean {
            var status = false
            if (sensorName.equals("Accelerometer", ignoreCase = true)) status =
                lastAccelerometerStatus else if (sensorName.equals(
                    "Gyroscope",
                    ignoreCase = true
                )
            ) status = lastGyroscopeStatus else if (sensorName.equals(
                    "Light",
                    ignoreCase = true
                )
            ) status = lastLightStatus else if (sensorName.equals(
                    "Magnetic Field",
                    ignoreCase = true
                )
            ) status = lastMagneticFieldStatus else if (sensorName.equals(
                    "Orientation",
                    ignoreCase = true
                )
            ) status = lastOrientationStatus else if (sensorName.equals(
                    "Proximity",
                    ignoreCase = true
                )
            ) status = lastProximityStatus
            return status
        }

        private fun updateLastSensorStatus(sensorName: String, status: Boolean) {
            if (sensorName.equals("Accelerometer", ignoreCase = true)) lastAccelerometerStatus =
                status else if (sensorName.equals(
                    "Gyroscope",
                    ignoreCase = true
                )
            ) lastGyroscopeStatus = status else if (sensorName.equals(
                    "Light",
                    ignoreCase = true
                )
            ) lastLightStatus = status else if (sensorName.equals(
                    "Magnetic Field",
                    ignoreCase = true
                )
            ) lastMagneticFieldStatus = status else if (sensorName.equals(
                    "Orientation",
                    ignoreCase = true
                )
            ) lastOrientationStatus = status else if (sensorName.equals(
                    "Proximity",
                    ignoreCase = true
                )
            ) lastProximityStatus = status
        }

        private val launcherPackageName: String
            get() {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                val context = ApplicationProvider.getApplicationContext<Context>()
                val pm = context.packageManager
                val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
                return resolveInfo!!.activityInfo.packageName
            }

        /*
    private static boolean verifySensors(UiDevice mDevice) {
        boolean res = false;

        //UiObject UIObjectCamera = mDevice.findObject(new UiSelector().description("Camera"));

        //For Galaxy M30
        UiObject UIObjectCamera = mDevice.findObject(new UiSelector().text("Camera"));

        try {
            UIObjectCamera.click();

            UiObject UIObjectPhoto = mDevice.findObject(new UiSelector().text("PHOTO"));

            if (UIObjectPhoto.exists())
                res = true;
            
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }


        / *
        UiObject UIObjectDismissButton = mDevice.findObject(new UiSelector().text("DISMISS"));

        try {
            UIObjectDismissButton.click();
            res = false;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        */
        //mDevice.pressHome();
        //return res;
        //}
        private fun verifyAllSensors(mDevice: UiDevice) {
            val UIObjectSensorDisablerApp = mDevice.findObject(UiSelector().text("Sensor Disabler"))
            lastAccelerometerStatus = true
            lastGyroscopeStatus = true
            lastLightStatus = true
            lastMagneticFieldStatus = true
            lastOrientationStatus = true
            lastProximityStatus = true
            lastCameraStatus = true
            try {
                UIObjectSensorDisablerApp.click()
                val UIObjectSensorDisAppMenu = mDevice.findObject(UiSelector().description("OK"))

                //Accelerometer------------------------------------------------------------------------
                lastAccelerometerStatus =
                    checkSpecificSensor(mDevice, UIObjectSensorDisAppMenu, "Accelerometer")

                //Gyroscope----------------------------------------------------------------------------
                lastGyroscopeStatus =
                    checkSpecificSensor(mDevice, UIObjectSensorDisAppMenu, "Gyroscope")

                //Light--------------------------------------------------------------------------------
                lastLightStatus = checkSpecificSensor(mDevice, UIObjectSensorDisAppMenu, "Light")

                //Magnetic Field-----------------------------------------------------------------------
                lastMagneticFieldStatus =
                    checkSpecificSensor(mDevice, UIObjectSensorDisAppMenu, "Magnetic Field")

                //Orientation---------------------------------------------------------------------------
                lastOrientationStatus =
                    checkSpecificSensor(mDevice, UIObjectSensorDisAppMenu, "Orientation")

                //Proximity-----------------------------------------------------------------------------
                lastProximityStatus =
                    checkSpecificSensor(mDevice, UIObjectSensorDisAppMenu, "Proximity")
                UIObjectSensorDisAppMenu.click()
                val appView = UiScrollable(UiSelector().scrollable(true))
                appView.scrollIntoView(UiSelector().text("Accelerometer"))
                val UIObjectSensorDisAppCancel =
                    mDevice.findObject(UiSelector().description("Cancel"))
                UIObjectSensorDisAppCancel.click()
            } catch (e: UiObjectNotFoundException) {
                e.printStackTrace()
            }
        }

        private fun checkSpecificSensor(
            mDevice: UiDevice,
            UIObjectSensorDisAppMenu: UiObject,
            sensorName: String
        ): Boolean {
            var res = true
            val UIObjectSensorDisAppMenuItem: List<UiObject2>
            val UIObjectSensorDisablerOptions: UiObject
            try {
                UIObjectSensorDisAppMenu.click()
                if (sensorName.equals("Orientation", ignoreCase = true) ||
                    sensorName.equals("Proximity", ignoreCase = true) ||
                    sensorName.equals("Light", ignoreCase = true) ||   //For Moto X2
                    sensorName.equals("Magnetic Field", ignoreCase = true) //For Moto X2
                ) {
                    val appView = UiScrollable(UiSelector().scrollable(true))
                    appView.scrollIntoView(UiSelector().text(sensorName))
                }
                UIObjectSensorDisAppMenuItem = mDevice.findObjects(By.text(sensorName))
                for (obj in UIObjectSensorDisAppMenuItem) {
                    if (obj.isClickable) obj.click()
                }
                UIObjectSensorDisablerOptions =
                    mDevice.findObject(UiSelector().text("Remove sensor"))
                if (UIObjectSensorDisablerOptions.isChecked) res = false
            } catch (e: UiObjectNotFoundException) {
                e.printStackTrace()
            }
            println("$sensorName sensor enabled: $res")
            return res
        }

        private fun verifyCamera(mDevice: UiDevice): Boolean {
            val UIObjectLensCapApp = mDevice.findObject(UiSelector().text("Lens Cap"))
            val UIObjectLensCapOptions: UiObject
            var res = false
            try {
                UIObjectLensCapApp.click()
                UIObjectLensCapOptions =
                    mDevice.findObject(UiSelector().textContains("Camera Enabled"))
                if (UIObjectLensCapOptions.exists()) res = true

                //UIObjectLensCapOptions = mDevice.findObject(new UiSelector().text("PUT CAP ON"));
                //UIObjectLensCapOptions.click();

                //UIObjectLensCapOptions = mDevice.findObject(new UiSelector().text("TAKE CAP OFF"));
                //UIObjectLensCapOptions.click();
            } catch (e: UiObjectNotFoundException) {
                e.printStackTrace()
            }
            println("Camera enabled: $res")
            return res
        }

        private fun verifyLocation(): Boolean {
            val service = InstrumentationRegistry.getInstrumentation().context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return service.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

        private fun verifyWifi(): Boolean {
            val service = InstrumentationRegistry.getInstrumentation().context
                .getSystemService(Context.WIFI_SERVICE) as WifiManager
            return service.isWifiEnabled
        }

        /*
    private static boolean verifyMobileData() {
        boolean res = false;
        ConnectivityManager connectivityMgr = (ConnectivityManager) InstrumentationRegistry.getInstrumentation().getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        if (connectivityMgr != null) {
            NetworkCapabilities nc = connectivityMgr.getNetworkCapabilities(connectivityMgr.getActiveNetwork());

            if (nc != null) {
                if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    // connected to mobile data
                    res = true;
                }
            }
            else
                res = false;
        }
        else
            res = false;

        return res;
    }
    */
        private fun verifyMobileData(): Boolean {
            var mobileYN = false
            val context = InstrumentationRegistry.getInstrumentation().context
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (tm.simState == TelephonyManager.SIM_STATE_READY) {
                val tel = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (tel.isDataEnabled) {
                    mobileYN = true
                }
            }
            return mobileYN
        }

        private fun verifyBluetooth(): Boolean {
            val btMgr = InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(
                Context.BLUETOOTH_SERVICE
            ) as BluetoothManager
            return btMgr.adapter.isEnabled
        }

        private fun verifyBatterySaver(): Boolean {
            val pwMgr = InstrumentationRegistry.getInstrumentation().context
                .getSystemService(Context.POWER_SERVICE) as PowerManager
            return pwMgr.isPowerSaveMode
        }

        private fun verifyAutoRotate(): Boolean {
            return Settings.System.getInt(
                InstrumentationRegistry.getInstrumentation().context.contentResolver,
                Settings.System.ACCELEROMETER_ROTATION,
                0
            ) == 1
        }

        private fun verifyDoNotDisturb(): Boolean {
            val notifManager =
                InstrumentationRegistry.getInstrumentation().context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            //Sem bloqueio: INTERRUPTION_FILTER_ALL
            //Algum bloqueio: INTERRUPTION_FILTER_PRIORITY
            //Bloqueio total: INTERRUPTION_FILTER_NONE
            return notifManager.currentInterruptionFilter == NotificationManager.INTERRUPTION_FILTER_PRIORITY
        }
    }
}
