/* Copyright (C) 2019 olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.core.bluetooth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.lib.SoehnleLib;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import com.welie.blessed.BluetoothBytesParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothSoehnle extends BluetoothCommunication {

    private final UUID WEIGHT_CUSTOM_SERVICE = UUID.fromString("352e3000-28e9-40b8-a361-6db4cca4147c");

    // notify, read
    private final UUID WEIGHT_CUSTOM_A_CHARACTERISTIC = UUID.fromString("352e3001-28e9-40b8-a361-6db4cca4147c");

    // notify, read
    private final UUID WEIGHT_CUSTOM_B_CHARACTERISTIC = UUID.fromString("352e3004-28e9-40b8-a361-6db4cca4147c");

    // write
    private final UUID WEIGHT_CUSTOM_CMD_CHARACTERISTIC = UUID.fromString("352e3002-28e9-40b8-a361-6db4cca4147c");

    SharedPreferences prefs;

    public BluetoothSoehnle(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(4025)) {
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    @Override
    public String driverName() {
        return "Soehnle Scale";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(4068)) {
            switch(stepNr) {
                case 0:
                    List<ScaleUser> openScaleUserList = OpenScale.getInstance().getScaleUserList();
                    int index = -1;
                    if (!ListenerUtil.mutListener.listen(4033)) {
                        {
                            long _loopCounter36 = 0;
                            // check if an openScale user is stored as a Soehnle user otherwise do a factory reset
                            for (ScaleUser openScaleUser : openScaleUserList) {
                                ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                                if (!ListenerUtil.mutListener.listen(4026)) {
                                    index = getSoehnleUserIndex(openScaleUser.getId());
                                }
                                if (!ListenerUtil.mutListener.listen(4032)) {
                                    if ((ListenerUtil.mutListener.listen(4031) ? (index >= -1) : (ListenerUtil.mutListener.listen(4030) ? (index <= -1) : (ListenerUtil.mutListener.listen(4029) ? (index > -1) : (ListenerUtil.mutListener.listen(4028) ? (index < -1) : (ListenerUtil.mutListener.listen(4027) ? (index == -1) : (index != -1))))))) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4040)) {
                        if ((ListenerUtil.mutListener.listen(4038) ? (index >= -1) : (ListenerUtil.mutListener.listen(4037) ? (index <= -1) : (ListenerUtil.mutListener.listen(4036) ? (index > -1) : (ListenerUtil.mutListener.listen(4035) ? (index < -1) : (ListenerUtil.mutListener.listen(4034) ? (index != -1) : (index == -1))))))) {
                            if (!ListenerUtil.mutListener.listen(4039)) {
                                invokeScaleFactoryReset();
                            }
                        }
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(4041)) {
                        setNotificationOn(BluetoothGattUuid.SERVICE_BATTERY_LEVEL, BluetoothGattUuid.CHARACTERISTIC_BATTERY_LEVEL);
                    }
                    if (!ListenerUtil.mutListener.listen(4042)) {
                        readBytes(BluetoothGattUuid.SERVICE_BATTERY_LEVEL, BluetoothGattUuid.CHARACTERISTIC_BATTERY_LEVEL);
                    }
                    break;
                case 2:
                    // Write the current time
                    BluetoothBytesParser parser = new BluetoothBytesParser();
                    if (!ListenerUtil.mutListener.listen(4043)) {
                        parser.setCurrentTime(Calendar.getInstance());
                    }
                    if (!ListenerUtil.mutListener.listen(4044)) {
                        writeBytes(BluetoothGattUuid.SERVICE_CURRENT_TIME, BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME, parser.getValue());
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(4045)) {
                        // Turn on notification for User Data Service
                        setNotificationOn(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT);
                    }
                    break;
                case 4:
                    int openScaleUserId = OpenScale.getInstance().getSelectedScaleUserId();
                    int soehnleUserIndex = getSoehnleUserIndex(openScaleUserId);
                    if (!ListenerUtil.mutListener.listen(4055)) {
                        if ((ListenerUtil.mutListener.listen(4050) ? (soehnleUserIndex >= -1) : (ListenerUtil.mutListener.listen(4049) ? (soehnleUserIndex <= -1) : (ListenerUtil.mutListener.listen(4048) ? (soehnleUserIndex > -1) : (ListenerUtil.mutListener.listen(4047) ? (soehnleUserIndex < -1) : (ListenerUtil.mutListener.listen(4046) ? (soehnleUserIndex != -1) : (soehnleUserIndex == -1))))))) {
                            if (!ListenerUtil.mutListener.listen(4053)) {
                                // create new user
                                Timber.d("create new Soehnle scale user");
                            }
                            if (!ListenerUtil.mutListener.listen(4054)) {
                                writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT, new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x00 });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4051)) {
                                // select user
                                Timber.d("select Soehnle scale user with index " + soehnleUserIndex);
                            }
                            if (!ListenerUtil.mutListener.listen(4052)) {
                                writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT, new byte[] { (byte) 0x02, (byte) soehnleUserIndex, (byte) 0x00, (byte) 0x00 });
                            }
                        }
                    }
                    break;
                case 5:
                    if (!ListenerUtil.mutListener.listen(4056)) {
                        // set age
                        writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_AGE, new byte[] { (byte) OpenScale.getInstance().getSelectedScaleUser().getAge() });
                    }
                    break;
                case 6:
                    if (!ListenerUtil.mutListener.listen(4057)) {
                        // set gender
                        writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_GENDER, new byte[] { OpenScale.getInstance().getSelectedScaleUser().getGender().isMale() ? (byte) 0x00 : (byte) 0x01 });
                    }
                    break;
                case 7:
                    if (!ListenerUtil.mutListener.listen(4058)) {
                        // set height
                        writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_HEIGHT, Converters.toInt16Le((int) OpenScale.getInstance().getSelectedScaleUser().getBodyHeight()));
                    }
                    break;
                case 8:
                    if (!ListenerUtil.mutListener.listen(4059)) {
                        setNotificationOn(WEIGHT_CUSTOM_SERVICE, WEIGHT_CUSTOM_A_CHARACTERISTIC);
                    }
                    if (!ListenerUtil.mutListener.listen(4060)) {
                        setNotificationOn(WEIGHT_CUSTOM_SERVICE, WEIGHT_CUSTOM_B_CHARACTERISTIC);
                    }
                    // writeBytes(WEIGHT_CUSTOM_SERVICE, WEIGHT_CUSTOM_CMD_CHARACTERISTIC, new byte[] {(byte)0x0c, (byte)0xff});
                    break;
                case 9:
                    if (!ListenerUtil.mutListener.listen(4067)) {
                        {
                            long _loopCounter37 = 0;
                            for (int i = 1; (ListenerUtil.mutListener.listen(4066) ? (i >= 8) : (ListenerUtil.mutListener.listen(4065) ? (i <= 8) : (ListenerUtil.mutListener.listen(4064) ? (i > 8) : (ListenerUtil.mutListener.listen(4063) ? (i != 8) : (ListenerUtil.mutListener.listen(4062) ? (i == 8) : (i < 8)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                                if (!ListenerUtil.mutListener.listen(4061)) {
                                    // get history data for soehnle user index i
                                    writeBytes(WEIGHT_CUSTOM_SERVICE, WEIGHT_CUSTOM_CMD_CHARACTERISTIC, new byte[] { (byte) 0x09, (byte) i });
                                }
                            }
                        }
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        if (!ListenerUtil.mutListener.listen(4069)) {
            Timber.d("on bluetooth notify change " + byteInHex(value) + " on " + characteristic.toString());
        }
        if (!ListenerUtil.mutListener.listen(4070)) {
            if (value == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4093)) {
            if ((ListenerUtil.mutListener.listen(4076) ? (characteristic.equals(WEIGHT_CUSTOM_A_CHARACTERISTIC) || (ListenerUtil.mutListener.listen(4075) ? (value.length >= 15) : (ListenerUtil.mutListener.listen(4074) ? (value.length <= 15) : (ListenerUtil.mutListener.listen(4073) ? (value.length > 15) : (ListenerUtil.mutListener.listen(4072) ? (value.length < 15) : (ListenerUtil.mutListener.listen(4071) ? (value.length != 15) : (value.length == 15))))))) : (characteristic.equals(WEIGHT_CUSTOM_A_CHARACTERISTIC) && (ListenerUtil.mutListener.listen(4075) ? (value.length >= 15) : (ListenerUtil.mutListener.listen(4074) ? (value.length <= 15) : (ListenerUtil.mutListener.listen(4073) ? (value.length > 15) : (ListenerUtil.mutListener.listen(4072) ? (value.length < 15) : (ListenerUtil.mutListener.listen(4071) ? (value.length != 15) : (value.length == 15))))))))) {
                if (!ListenerUtil.mutListener.listen(4092)) {
                    if ((ListenerUtil.mutListener.listen(4090) ? (value[0] >= (byte) 0x09) : (ListenerUtil.mutListener.listen(4089) ? (value[0] <= (byte) 0x09) : (ListenerUtil.mutListener.listen(4088) ? (value[0] > (byte) 0x09) : (ListenerUtil.mutListener.listen(4087) ? (value[0] < (byte) 0x09) : (ListenerUtil.mutListener.listen(4086) ? (value[0] != (byte) 0x09) : (value[0] == (byte) 0x09))))))) {
                        if (!ListenerUtil.mutListener.listen(4091)) {
                            handleWeightMeasurement(value);
                        }
                    }
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT)) {
                if (!ListenerUtil.mutListener.listen(4085)) {
                    handleUserControlPoint(value);
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_BATTERY_LEVEL)) {
                int batteryLevel = value[0];
                if (!ListenerUtil.mutListener.listen(4077)) {
                    Timber.d("Soehnle scale battery level is " + batteryLevel);
                }
                if (!ListenerUtil.mutListener.listen(4084)) {
                    if ((ListenerUtil.mutListener.listen(4082) ? (batteryLevel >= 10) : (ListenerUtil.mutListener.listen(4081) ? (batteryLevel > 10) : (ListenerUtil.mutListener.listen(4080) ? (batteryLevel < 10) : (ListenerUtil.mutListener.listen(4079) ? (batteryLevel != 10) : (ListenerUtil.mutListener.listen(4078) ? (batteryLevel == 10) : (batteryLevel <= 10))))))) {
                        if (!ListenerUtil.mutListener.listen(4083)) {
                            sendMessage(R.string.info_scale_low_battery, batteryLevel);
                        }
                    }
                }
            }
        }
    }

    private void handleUserControlPoint(byte[] value) {
        if (!ListenerUtil.mutListener.listen(4129)) {
            if ((ListenerUtil.mutListener.listen(4098) ? (value[0] >= (byte) 0x20) : (ListenerUtil.mutListener.listen(4097) ? (value[0] <= (byte) 0x20) : (ListenerUtil.mutListener.listen(4096) ? (value[0] > (byte) 0x20) : (ListenerUtil.mutListener.listen(4095) ? (value[0] < (byte) 0x20) : (ListenerUtil.mutListener.listen(4094) ? (value[0] != (byte) 0x20) : (value[0] == (byte) 0x20))))))) {
                int cmd = value[1];
                if (!ListenerUtil.mutListener.listen(4128)) {
                    if ((ListenerUtil.mutListener.listen(4103) ? (cmd >= (byte) 0x01) : (ListenerUtil.mutListener.listen(4102) ? (cmd <= (byte) 0x01) : (ListenerUtil.mutListener.listen(4101) ? (cmd > (byte) 0x01) : (ListenerUtil.mutListener.listen(4100) ? (cmd < (byte) 0x01) : (ListenerUtil.mutListener.listen(4099) ? (cmd != (byte) 0x01) : (cmd == (byte) 0x01))))))) {
                        // user create
                        int userId = OpenScale.getInstance().getSelectedScaleUserId();
                        int success = value[2];
                        int soehnleUserIndex = value[3];
                        if (!ListenerUtil.mutListener.listen(4127)) {
                            if ((ListenerUtil.mutListener.listen(4122) ? (success >= (byte) 0x01) : (ListenerUtil.mutListener.listen(4121) ? (success <= (byte) 0x01) : (ListenerUtil.mutListener.listen(4120) ? (success > (byte) 0x01) : (ListenerUtil.mutListener.listen(4119) ? (success < (byte) 0x01) : (ListenerUtil.mutListener.listen(4118) ? (success != (byte) 0x01) : (success == (byte) 0x01))))))) {
                                if (!ListenerUtil.mutListener.listen(4124)) {
                                    Timber.d("User control point index is " + soehnleUserIndex + " for user id " + userId);
                                }
                                if (!ListenerUtil.mutListener.listen(4125)) {
                                    prefs.edit().putInt("userScaleIndex" + soehnleUserIndex, userId).apply();
                                }
                                if (!ListenerUtil.mutListener.listen(4126)) {
                                    sendMessage(R.string.info_step_on_scale_for_reference, 0);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(4123)) {
                                    Timber.e("Error creating new Sohnle user");
                                }
                            }
                        }
                    } else if ((ListenerUtil.mutListener.listen(4108) ? (cmd >= (byte) 0x02) : (ListenerUtil.mutListener.listen(4107) ? (cmd <= (byte) 0x02) : (ListenerUtil.mutListener.listen(4106) ? (cmd > (byte) 0x02) : (ListenerUtil.mutListener.listen(4105) ? (cmd < (byte) 0x02) : (ListenerUtil.mutListener.listen(4104) ? (cmd != (byte) 0x02) : (cmd == (byte) 0x02))))))) {
                        // user select
                        int success = value[2];
                        if (!ListenerUtil.mutListener.listen(4117)) {
                            if ((ListenerUtil.mutListener.listen(4113) ? (success >= (byte) 0x01) : (ListenerUtil.mutListener.listen(4112) ? (success <= (byte) 0x01) : (ListenerUtil.mutListener.listen(4111) ? (success > (byte) 0x01) : (ListenerUtil.mutListener.listen(4110) ? (success < (byte) 0x01) : (ListenerUtil.mutListener.listen(4109) ? (success == (byte) 0x01) : (success != (byte) 0x01))))))) {
                                if (!ListenerUtil.mutListener.listen(4114)) {
                                    Timber.e("Error selecting Soehnle user");
                                }
                                if (!ListenerUtil.mutListener.listen(4115)) {
                                    invokeScaleFactoryReset();
                                }
                                if (!ListenerUtil.mutListener.listen(4116)) {
                                    jumpNextToStepNr(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private int getSoehnleUserIndex(int openScaleUserId) {
        if (!ListenerUtil.mutListener.listen(4141)) {
            {
                long _loopCounter38 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(4140) ? (i >= 8) : (ListenerUtil.mutListener.listen(4139) ? (i <= 8) : (ListenerUtil.mutListener.listen(4138) ? (i > 8) : (ListenerUtil.mutListener.listen(4137) ? (i != 8) : (ListenerUtil.mutListener.listen(4136) ? (i == 8) : (i < 8)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                    int prefOpenScaleUserId = prefs.getInt("userScaleIndex" + i, -1);
                    if (!ListenerUtil.mutListener.listen(4135)) {
                        if ((ListenerUtil.mutListener.listen(4134) ? (openScaleUserId >= prefOpenScaleUserId) : (ListenerUtil.mutListener.listen(4133) ? (openScaleUserId <= prefOpenScaleUserId) : (ListenerUtil.mutListener.listen(4132) ? (openScaleUserId > prefOpenScaleUserId) : (ListenerUtil.mutListener.listen(4131) ? (openScaleUserId < prefOpenScaleUserId) : (ListenerUtil.mutListener.listen(4130) ? (openScaleUserId != prefOpenScaleUserId) : (openScaleUserId == prefOpenScaleUserId))))))) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private void invokeScaleFactoryReset() {
        if (!ListenerUtil.mutListener.listen(4142)) {
            Timber.d("Do a factory reset on Soehnle scale to swipe old users");
        }
        if (!ListenerUtil.mutListener.listen(4143)) {
            // factory reset
            writeBytes(WEIGHT_CUSTOM_SERVICE, WEIGHT_CUSTOM_CMD_CHARACTERISTIC, new byte[] { (byte) 0x0b, (byte) 0xff });
        }
        if (!ListenerUtil.mutListener.listen(4150)) {
            {
                long _loopCounter39 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(4149) ? (i >= 8) : (ListenerUtil.mutListener.listen(4148) ? (i <= 8) : (ListenerUtil.mutListener.listen(4147) ? (i > 8) : (ListenerUtil.mutListener.listen(4146) ? (i != 8) : (ListenerUtil.mutListener.listen(4145) ? (i == 8) : (i < 8)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                    if (!ListenerUtil.mutListener.listen(4144)) {
                        prefs.edit().putInt("userScaleIndex" + i, -1).apply();
                    }
                }
            }
        }
    }

    private void handleWeightMeasurement(byte[] value) {
        // kg
        float weight = (ListenerUtil.mutListener.listen(4154) ? (Converters.fromUnsignedInt16Be(value, 9) % 10.0f) : (ListenerUtil.mutListener.listen(4153) ? (Converters.fromUnsignedInt16Be(value, 9) * 10.0f) : (ListenerUtil.mutListener.listen(4152) ? (Converters.fromUnsignedInt16Be(value, 9) - 10.0f) : (ListenerUtil.mutListener.listen(4151) ? (Converters.fromUnsignedInt16Be(value, 9) + 10.0f) : (Converters.fromUnsignedInt16Be(value, 9) / 10.0f)))));
        int soehnleUserIndex = (int) value[1];
        final int year = Converters.fromUnsignedInt16Be(value, 2);
        final int month = (int) value[4];
        final int day = (int) value[5];
        final int hours = (int) value[6];
        final int min = (int) value[7];
        final int sec = (int) value[8];
        final int imp5 = Converters.fromUnsignedInt16Be(value, 11);
        final int imp50 = Converters.fromUnsignedInt16Be(value, 13);
        String date_string = year + "/" + month + "/" + day + "/" + hours + "/" + min;
        Date date_time = new Date();
        try {
            if (!ListenerUtil.mutListener.listen(4156)) {
                date_time = new SimpleDateFormat("yyyy/MM/dd/HH/mm").parse(date_string);
            }
        } catch (ParseException e) {
            if (!ListenerUtil.mutListener.listen(4155)) {
                Timber.e("parse error " + e.getMessage());
            }
        }
        final ScaleUser scaleUser = OpenScale.getInstance().getSelectedScaleUser();
        int activityLevel = 0;
        if (!ListenerUtil.mutListener.listen(4162)) {
            switch(scaleUser.getActivityLevel()) {
                case SEDENTARY:
                    if (!ListenerUtil.mutListener.listen(4157)) {
                        activityLevel = 0;
                    }
                    break;
                case MILD:
                    if (!ListenerUtil.mutListener.listen(4158)) {
                        activityLevel = 1;
                    }
                    break;
                case MODERATE:
                    if (!ListenerUtil.mutListener.listen(4159)) {
                        activityLevel = 2;
                    }
                    break;
                case HEAVY:
                    if (!ListenerUtil.mutListener.listen(4160)) {
                        activityLevel = 4;
                    }
                    break;
                case EXTREME:
                    if (!ListenerUtil.mutListener.listen(4161)) {
                        activityLevel = 5;
                    }
                    break;
            }
        }
        int openScaleUserId = prefs.getInt("userScaleIndex" + soehnleUserIndex, -1);
        if (!ListenerUtil.mutListener.listen(4176)) {
            if ((ListenerUtil.mutListener.listen(4167) ? (openScaleUserId >= -1) : (ListenerUtil.mutListener.listen(4166) ? (openScaleUserId <= -1) : (ListenerUtil.mutListener.listen(4165) ? (openScaleUserId > -1) : (ListenerUtil.mutListener.listen(4164) ? (openScaleUserId < -1) : (ListenerUtil.mutListener.listen(4163) ? (openScaleUserId != -1) : (openScaleUserId == -1))))))) {
                if (!ListenerUtil.mutListener.listen(4175)) {
                    Timber.e("Unknown Soehnle user index " + soehnleUserIndex);
                }
            } else {
                SoehnleLib soehnleLib = new SoehnleLib(scaleUser.getGender().isMale(), scaleUser.getAge(), scaleUser.getBodyHeight(), activityLevel);
                ScaleMeasurement scaleMeasurement = new ScaleMeasurement();
                if (!ListenerUtil.mutListener.listen(4168)) {
                    scaleMeasurement.setUserId(openScaleUserId);
                }
                if (!ListenerUtil.mutListener.listen(4169)) {
                    scaleMeasurement.setWeight(weight);
                }
                if (!ListenerUtil.mutListener.listen(4170)) {
                    scaleMeasurement.setDateTime(date_time);
                }
                if (!ListenerUtil.mutListener.listen(4171)) {
                    scaleMeasurement.setWater(soehnleLib.getWater(weight, imp50));
                }
                if (!ListenerUtil.mutListener.listen(4172)) {
                    scaleMeasurement.setFat(soehnleLib.getFat(weight, imp50));
                }
                if (!ListenerUtil.mutListener.listen(4173)) {
                    scaleMeasurement.setMuscle(soehnleLib.getMuscle(weight, imp50, imp5));
                }
                if (!ListenerUtil.mutListener.listen(4174)) {
                    addScaleMeasurement(scaleMeasurement);
                }
            }
        }
    }
}
