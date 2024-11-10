package com.ichi2;

import android.Manifest;
import android.app.NotificationManager;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class SensorConditionsTestJ {
    private static final String LOCATION = "Location";
    private static final String WIFION = "ParcTE_v2";
    private static final String WIFIOFF = "Wi-Fi";
    private static final String MOBILEDATA = "Mobile data";
    private static final String BLUETOOTH = "Bluetooth";
    private static final String BATTERYSAVER = "Battery Saver";
    private static final String AUTOROTATE = "Auto-rotate";
    private static final String DONOTDISTURB = "Do Not Disturb";
    private static final String CAMERAON = "Disable Camera";
    private static final String CAMERAOFF = "Enable Camera";

    private static boolean lastAccelerometerStatus = false;
    private static boolean lastGyroscopeStatus = false;
    private static boolean lastLightStatus = false;
    private static boolean lastMagneticFieldStatus = false;
    private static boolean lastOrientationStatus = false;
    private static boolean lastProximityStatus = false;
    private static boolean lastCameraStatus = false;

    private static boolean lastLocationStatus = false;
    private static boolean lastWifiStatus = false;
    private static boolean lastMobileDataStatus = false;
    private static boolean lastBluetoothStatus = false;
    private static boolean lastBatterySaverStatus = false;
    private static boolean lastAutoRotateStatus = false;
    private static boolean lastDoNotDisturbStatus = false;

    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE);

    @BeforeClass
    public static void setUpSensorConditions() throws UiObjectNotFoundException {
        UiDevice mDevice;
        boolean accelerometerEnabled;
        boolean gyroscopeEnabled;
        boolean lightEnabled;
        boolean magneticFieldEnabled;
        boolean orientationEnabled;
        boolean proximityEnabled;
        boolean cameraEnabled;

        boolean locationEnabled;
        boolean wifiEnabled;
        boolean mobileDataEnabled;
        boolean bluetoothEnabled;
        boolean batterySaverEnabled;
        boolean autoRotateEnabled;
        boolean doNotDisturbEnabled;

        UiObject UIObjectLocation;
        UiObject UIObjectWifi;
        UiObject UIObjectMobiledata;
        UiObject UIObjectBluetooth;
        UiObject UIObjectBatterySaver;
        UiObject UIObjectAutoRotate;
        UiObject UIObjectDoNotDisturb;
        UiObject UIObjectCamera;

        accelerometerEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("accelerometerEnabled"));
        gyroscopeEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("gyroscopeEnabled"));
        lightEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("lightEnabled"));
        magneticFieldEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("magneticFieldEnabled"));
        orientationEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("orientationEnabled"));
        proximityEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("proximityEnabled"));
        cameraEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("cameraEnabled"));

        locationEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("locationEnabled"));
        wifiEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("wifiEnabled"));
        mobileDataEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("mobileDataEnabled"));
        bluetoothEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("bluetoothEnabled"));
        batterySaverEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("batterySaverEnabled"));
        autoRotateEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("autoRotateEnabled"));
        doNotDisturbEnabled = Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("doNotDisturbEnabled"));

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.wait(Until.hasObject(By.pkg(getLauncherPackageName()).depth(0)), 1000);

        verifyAllSensors(mDevice);
        mDevice.pressBack();
        lastCameraStatus = verifyCamera(mDevice);
        mDevice.pressBack();
        lastLocationStatus = verifyLocation();
        lastWifiStatus = verifyWifi();
        lastMobileDataStatus = verifyMobileData();
        lastBluetoothStatus = verifyBluetooth();
        lastBatterySaverStatus = verifyBatterySaver();
        lastAutoRotateStatus = verifyAutoRotate();
        lastDoNotDisturbStatus = verifyDoNotDisturb();

        //Location
        if (locationEnabled) {
            if (!lastLocationStatus) {
                mDevice.openQuickSettings();
                UIObjectLocation = mDevice.findObject(new UiSelector().text(LOCATION)); //For Location (GPS)
                UIObjectLocation.click();
                lastLocationStatus = true;
            }
        } else {
            if (lastLocationStatus) {
                mDevice.openQuickSettings();
                UIObjectLocation = mDevice.findObject(new UiSelector().text(LOCATION)); //For Location (GPS)
                UIObjectLocation.click();
                lastLocationStatus = false;
            }
        }

        //Wi-Fi
        if (wifiEnabled) {
            if (!lastWifiStatus) {
                mDevice.openQuickSettings();
                UIObjectWifi = mDevice.findObject(new UiSelector().text(WIFIOFF));
                UIObjectWifi.click();
                UIObjectWifi = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectWifi.click();
                lastWifiStatus = true;
            }
        } else {
            if (lastWifiStatus) {
                mDevice.openQuickSettings();
                UIObjectWifi = mDevice.findObject(new UiSelector().text(WIFION));
                UIObjectWifi.click();
                UIObjectWifi = mDevice.findObject(new UiSelector().text("ON"));
                UIObjectWifi.click();
                UIObjectWifi = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectWifi.click();
                lastWifiStatus = false;
            }
        }

        //Mobile Data
        if (mobileDataEnabled) {
            if (!lastMobileDataStatus) {
                mDevice.openQuickSettings();
                UIObjectMobiledata = mDevice.findObject(new UiSelector().text(MOBILEDATA));
                UIObjectMobiledata.click();
                UIObjectMobiledata = mDevice.findObject(new UiSelector().text("OFF"));
                UIObjectMobiledata.click();
                UIObjectMobiledata = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectMobiledata.click();
                lastMobileDataStatus = true;
            }
        } else {
            if (lastMobileDataStatus) {
                mDevice.openQuickSettings();
                UIObjectMobiledata = mDevice.findObject(new UiSelector().text(MOBILEDATA));
                UIObjectMobiledata.click();
                UIObjectMobiledata = mDevice.findObject(new UiSelector().text("ON"));
                UIObjectMobiledata.click();
                UIObjectMobiledata = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectMobiledata.click();
                lastMobileDataStatus = false;
            }
        }

        //Bluetooth
        if (bluetoothEnabled) {
            if (!lastBluetoothStatus) {
                mDevice.openQuickSettings();
                UIObjectBluetooth = mDevice.findObject(new UiSelector().text(BLUETOOTH));
                UIObjectBluetooth.click();
                UIObjectBluetooth = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectBluetooth.click();
                lastBluetoothStatus = true;
            }
        } else {
            if (lastBluetoothStatus) {
                mDevice.openQuickSettings();
                UIObjectBluetooth = mDevice.findObject(new UiSelector().text(BLUETOOTH));
                UIObjectBluetooth.click();
                UIObjectBluetooth = mDevice.findObject(new UiSelector().text("ON"));
                UIObjectBluetooth.click();
                UIObjectBluetooth = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectBluetooth.click();
                lastBluetoothStatus = false;
            }
        }

        //Battery Saver
        if (batterySaverEnabled) {
            if (!lastBatterySaverStatus) {
                mDevice.openQuickSettings();
                UIObjectBatterySaver = mDevice.findObject(new UiSelector().text(BATTERYSAVER));
                UIObjectBatterySaver.click();
                lastBatterySaverStatus = true;
            }
        } else {
            if (lastBatterySaverStatus) {
                mDevice.openQuickSettings();
                UIObjectBatterySaver = mDevice.findObject(new UiSelector().text(BATTERYSAVER));
                UIObjectBatterySaver.click();
                lastBatterySaverStatus = false;
            }
        }

        //Auto Rotate
        if (autoRotateEnabled) {
            if (!lastAutoRotateStatus) {
                mDevice.openQuickSettings();
                UIObjectAutoRotate = mDevice.findObject(new UiSelector().text(AUTOROTATE));
                UIObjectAutoRotate.click();
                lastAutoRotateStatus = true;
            }
        } else {
            if (lastAutoRotateStatus) {
                mDevice.openQuickSettings();
                UIObjectAutoRotate = mDevice.findObject(new UiSelector().text(AUTOROTATE));
                UIObjectAutoRotate.click();
                lastAutoRotateStatus = false;
            }
        }

        //Do Not Disturb
        if (doNotDisturbEnabled) {
            if (!lastDoNotDisturbStatus) {
                mDevice.openQuickSettings();
                UIObjectDoNotDisturb = mDevice.findObject(new UiSelector().text(DONOTDISTURB));
                UIObjectDoNotDisturb.click();
                UIObjectDoNotDisturb = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectDoNotDisturb.click();
                lastDoNotDisturbStatus = true;
            }
        } else {
            if (lastDoNotDisturbStatus) {
                mDevice.openQuickSettings();
                UIObjectDoNotDisturb = mDevice.findObject(new UiSelector().text(DONOTDISTURB));
                UIObjectDoNotDisturb.click();
                UIObjectDoNotDisturb = mDevice.findObject(new UiSelector().text("ON"));
                UIObjectDoNotDisturb.click();
                UIObjectDoNotDisturb = mDevice.findObject(new UiSelector().text("Done"));
                UIObjectDoNotDisturb.click();
                lastDoNotDisturbStatus = false;
            }
        }

        //Camera
        if (cameraEnabled) {
            if (!lastCameraStatus) {
                mDevice.openQuickSettings();
                UIObjectCamera = mDevice.findObject(new UiSelector().text(CAMERAOFF));
                UIObjectCamera.click();
                lastCameraStatus = true;
            }
        } else {
            if (lastCameraStatus) {
                mDevice.openQuickSettings();
                UIObjectCamera = mDevice.findObject(new UiSelector().text(CAMERAON));
                UIObjectCamera.click();
                lastCameraStatus = true;
            }
        }

        mDevice.pressHome();

        UiObject UIObjectSensorDisablerApp = mDevice.findObject(new UiSelector().text("Sensor Disabler"));
        UIObjectSensorDisablerApp.click();
        enableDisableSensor(mDevice, "Accelerometer", accelerometerEnabled);
        enableDisableSensor(mDevice, "Gyroscope", gyroscopeEnabled);
        enableDisableSensor(mDevice, "Light", lightEnabled);
        enableDisableSensor(mDevice, "Magnetic Field", magneticFieldEnabled);
        enableDisableSensor(mDevice, "Orientation", orientationEnabled);
        enableDisableSensor(mDevice, "Proximity", proximityEnabled);


        mDevice.pressHome();

    }

    private static void enableDisableSensor(UiDevice mDevice, String sensorName, boolean sensorEnabled) {
        if (sensorEnabled) {
            if (!getLastSensorStatus(sensorName)) {
                UiObject UIObjectSensorDisAppMenu = mDevice.findObject(new UiSelector().description("OK"));

                changeSpecificSensor(mDevice, UIObjectSensorDisAppMenu, sensorName, "Do nothing");

                updateLastSensorStatus(sensorName,true);
            }
        } else {
            if (getLastSensorStatus(sensorName)) {
                UiObject UIObjectSensorDisAppMenu = mDevice.findObject(new UiSelector().description("OK"));

                changeSpecificSensor(mDevice, UIObjectSensorDisAppMenu, sensorName, "Remove sensor");

                updateLastSensorStatus(sensorName,false);
            }
        }
    }

    private static void runExternalCommand(String command) {
        File tempScript = null;

        try {
            tempScript = createTempScript(command);
        } catch (IOException e) {
            System.out.println("Passou aq");
            e.printStackTrace();
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tempScript.delete();
        }

    }

    public static File createTempScript(String command) throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
                tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        printWriter.println(command);
        printWriter.println("exit");

        printWriter.close();

        return tempScript;
    }

    private static void changeSpecificSensor(UiDevice mDevice, UiObject UIObjectSensorDisAppMenu,
                                                String sensorName, String changeOption) {
        List<UiObject2> UIObjectSensorDisAppMenuItem;
        UiObject UIObjectSensorDisablerOptions;
        UiObject UIObjectSaveOptions;
        boolean scrolledFirstMenuItem = false;
        boolean inspectSensorOption = false;

        try {
            UIObjectSensorDisAppMenu.click();

            if (sensorName.equalsIgnoreCase("Orientation") ||
                    sensorName.equalsIgnoreCase("Proximity")) {

                UiScrollable appView = new UiScrollable(new UiSelector().scrollable(true));
                appView.scrollIntoView(new UiSelector().text(sensorName));
                scrolledFirstMenuItem = true;
            }

            UIObjectSensorDisAppMenuItem = mDevice.findObjects(By.text(sensorName));

            for (UiObject2 obj: UIObjectSensorDisAppMenuItem) {
                if (!inspectSensorOption && obj.isClickable()) {
                    obj.click();

                    UIObjectSensorDisablerOptions = mDevice.findObject(new UiSelector().text(changeOption));
                    if (UIObjectSensorDisablerOptions.exists()) {
                        UIObjectSensorDisablerOptions.click();
                        UIObjectSaveOptions = mDevice.findObject(new UiSelector().className("android.widget.ImageButton").instance(2));
                        UIObjectSaveOptions.click();

                        inspectSensorOption = true;
                    }
                }
            }

            //Need to scroll to the first menu item
            if (scrolledFirstMenuItem) {
                UIObjectSensorDisAppMenu.click();
                UiScrollable appView = new UiScrollable(new UiSelector().scrollable(true));
                appView.scrollIntoView(new UiSelector().text("Accelerometer"));
                UiObject UIObjectSensorDisAppCancel = mDevice.findObject(new UiSelector().description("Cancel"));
                UIObjectSensorDisAppCancel.click();
            }

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static boolean getLastSensorStatus(String sensorName) {
        boolean status = false;

        if (sensorName.equalsIgnoreCase("Accelerometer"))
            status = lastAccelerometerStatus;
        else
        if (sensorName.equalsIgnoreCase("Gyroscope"))
            status = lastGyroscopeStatus;
        else
        if (sensorName.equalsIgnoreCase("Light"))
            status = lastLightStatus;
        else
        if (sensorName.equalsIgnoreCase("Magnetic Field"))
            status = lastMagneticFieldStatus;
        else
        if (sensorName.equalsIgnoreCase("Orientation"))
            status = lastOrientationStatus;
        else
        if (sensorName.equalsIgnoreCase("Proximity"))
            status = lastProximityStatus;

        return status;
    }

    private static void updateLastSensorStatus(String sensorName, boolean status) {
        if (sensorName.equalsIgnoreCase("Accelerometer"))
            lastAccelerometerStatus = status;
        else
            if (sensorName.equalsIgnoreCase("Gyroscope"))
                lastGyroscopeStatus = status;
            else
                if (sensorName.equalsIgnoreCase("Light"))
                    lastLightStatus = status;
                else
                    if (sensorName.equalsIgnoreCase("Magnetic Field"))
                        lastMagneticFieldStatus = status;
                    else
                        if (sensorName.equalsIgnoreCase("Orientation"))
                            lastOrientationStatus = status;
                        else
                            if (sensorName.equalsIgnoreCase("Proximity"))
                                lastProximityStatus = status;

    }

    private static String getLauncherPackageName() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        Context context = ApplicationProvider.getApplicationContext();
        PackageManager pm = context.getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
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


        /*
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



    private static void verifyAllSensors(UiDevice mDevice) {
        UiObject UIObjectSensorDisablerApp = mDevice.findObject(new UiSelector().text("Sensor Disabler"));

        lastAccelerometerStatus = true;
        lastGyroscopeStatus = true;
        lastLightStatus = true;
        lastMagneticFieldStatus = true;
        lastOrientationStatus = true;
        lastProximityStatus = true;
        lastCameraStatus = true;

        try {
            UIObjectSensorDisablerApp.click();

            UiObject UIObjectSensorDisAppMenu = mDevice.findObject(new UiSelector().description("OK"));

            //Accelerometer------------------------------------------------------------------------
            lastAccelerometerStatus = checkSpecificSensor(mDevice,UIObjectSensorDisAppMenu,"Accelerometer");

            //Gyroscope----------------------------------------------------------------------------
            lastGyroscopeStatus = checkSpecificSensor(mDevice,UIObjectSensorDisAppMenu,"Gyroscope");

            //Light--------------------------------------------------------------------------------
            lastLightStatus = checkSpecificSensor(mDevice,UIObjectSensorDisAppMenu,"Light");

            //Magnetic Field-----------------------------------------------------------------------
            lastMagneticFieldStatus = checkSpecificSensor(mDevice,UIObjectSensorDisAppMenu,"Magnetic Field");

            //Orientation---------------------------------------------------------------------------
            lastOrientationStatus = checkSpecificSensor(mDevice,UIObjectSensorDisAppMenu,"Orientation");

            //Proximity-----------------------------------------------------------------------------
            lastProximityStatus = checkSpecificSensor(mDevice,UIObjectSensorDisAppMenu,"Proximity");

            UIObjectSensorDisAppMenu.click();
            UiScrollable appView = new UiScrollable(new UiSelector().scrollable(true));
            appView.scrollIntoView(new UiSelector().text("Accelerometer"));
            UiObject UIObjectSensorDisAppCancel = mDevice.findObject(new UiSelector().description("Cancel"));
            UIObjectSensorDisAppCancel.click();

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkSpecificSensor(UiDevice mDevice, UiObject UIObjectSensorDisAppMenu, String sensorName) {
        boolean res = true;
        boolean inspectSensorOption = false;

        List<UiObject2> UIObjectSensorDisAppMenuItem;
        UiObject UIObjectSensorDisablerOptions;

        try {
            UIObjectSensorDisAppMenu.click();

            if (sensorName.equalsIgnoreCase("Orientation") ||
                    sensorName.equalsIgnoreCase("Proximity")) {
                UiScrollable appView = new UiScrollable(new UiSelector().scrollable(true));
                appView.scrollIntoView(new UiSelector().text(sensorName));
            }

            UIObjectSensorDisAppMenuItem = mDevice.findObjects(By.text(sensorName));

            for (UiObject2 obj: UIObjectSensorDisAppMenuItem) {
                if (!inspectSensorOption && obj.isClickable()) {
                    obj.click();

                    UIObjectSensorDisablerOptions = mDevice.findObject(new UiSelector().text("Remove sensor"));
                    if (UIObjectSensorDisablerOptions.exists()) {
                        if (UIObjectSensorDisablerOptions.isChecked()) {
                            res = false;
                        }
                        inspectSensorOption = true;
                    }
                }
            }

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(sensorName + " sensor enabled: " + res);

        return res;
    }

    private static boolean verifyCamera(UiDevice mDevice) {
        UiObject UIObjectLensCapApp = mDevice.findObject(new UiSelector().text("Lens Cap"));
        UiObject UIObjectLensCapOptions;

        boolean res = false;

        try {
            UIObjectLensCapApp.click();

            UIObjectLensCapOptions = mDevice.findObject(new UiSelector().textContains("Camera Enabled"));

            if (UIObjectLensCapOptions.exists())
                res = true;

            //UIObjectLensCapOptions = mDevice.findObject(new UiSelector().text("PUT CAP ON"));
            //UIObjectLensCapOptions.click();

            //UIObjectLensCapOptions = mDevice.findObject(new UiSelector().text("TAKE CAP OFF"));
            //UIObjectLensCapOptions.click();

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Camera enabled: " + res);

        return res;
    }

    private static boolean verifyLocation() {
        LocationManager service = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext()
                .getSystemService(Context.LOCATION_SERVICE);

        return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private static boolean verifyWifi() {
        WifiManager service = (WifiManager) InstrumentationRegistry.getInstrumentation().getContext()
                .getSystemService(Context.WIFI_SERVICE);

        return service.isWifiEnabled();
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

    private static boolean verifyMobileData(){
        boolean mobileYN = false;
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
            TelephonyManager tel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            if(tel.isDataEnabled()){
                mobileYN = true;
            }

        }

        return mobileYN;
    }

    private static boolean verifyBluetooth() {
        BluetoothManager btMgr = (BluetoothManager) InstrumentationRegistry.getInstrumentation().getTargetContext().
                getSystemService(Context.BLUETOOTH_SERVICE);

        return btMgr.getAdapter().isEnabled();
    }

    private static boolean verifyBatterySaver() {
        PowerManager pwMgr = (PowerManager) InstrumentationRegistry.getInstrumentation().getContext()
                .getSystemService(Context.POWER_SERVICE);

        return pwMgr.isPowerSaveMode();
    }

    private static boolean verifyAutoRotate() {
        return Settings.System.getInt(InstrumentationRegistry.getInstrumentation().getContext().getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
    }

    private static boolean verifyDoNotDisturb() {
        NotificationManager notifManager = (NotificationManager) InstrumentationRegistry.getInstrumentation().getContext().
                getSystemService(Context.NOTIFICATION_SERVICE);

        boolean res = notifManager.getCurrentInterruptionFilter() == NotificationManager.INTERRUPTION_FILTER_PRIORITY;

        //Sem bloqueio: INTERRUPTION_FILTER_ALL
        //Algum bloqueio: INTERRUPTION_FILTER_PRIORITY
        //Bloqueio total: INTERRUPTION_FILTER_NONE
        return res;
    }

}
