/* Copyright (C) 2019  olie.xdev <olie.xdev@googlemail.com>
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
/*
 * Based on source-code by weliem/blessed-android
 */
package com.health.openscale.core.bluetooth;

import android.content.Context;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.welie.blessed.BluetoothBytesParser;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import timber.log.Timber;
import static com.welie.blessed.BluetoothBytesParser.FORMAT_UINT16;
import static com.welie.blessed.BluetoothBytesParser.FORMAT_UINT8;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothStandardWeightProfile extends BluetoothCommunication {

    private int CURRENT_USER_CONSENT = 3289;

    // UDS control point codes
    private static final byte UDS_CP_REGISTER_NEW_USER = 0x01;

    private static final byte UDS_CP_CONSENT = 0x02;

    private static final byte UDS_CP_DELETE_USER_DATA = 0x03;

    private static final byte UDS_CP_LIST_ALL_USERS = 0x04;

    private static final byte UDS_CP_DELETE_USERS = 0x05;

    private static final byte UDS_CP_RESPONSE = 0x20;

    // UDS response codes
    private static final byte UDS_CP_RESP_VALUE_SUCCESS = 0x01;

    private static final byte UDS_CP_RESP_OP_CODE_NOT_SUPPORTED = 0x02;

    private static final byte UDS_CP_RESP_INVALID_PARAMETER = 0x03;

    private static final byte UDS_CP_RESP_OPERATION_FAILED = 0x04;

    private static final byte UDS_CP_RESP_USER_NOT_AUTHORIZED = 0x05;

    public BluetoothStandardWeightProfile(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Bluetooth Standard Weight Profile";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(4188)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(4177)) {
                        // Read manufacturer and model number from the Device Information Service
                        readBytes(BluetoothGattUuid.SERVICE_DEVICE_INFORMATION, BluetoothGattUuid.CHARACTERISTIC_MANUFACTURER_NAME_STRING);
                    }
                    if (!ListenerUtil.mutListener.listen(4178)) {
                        readBytes(BluetoothGattUuid.SERVICE_DEVICE_INFORMATION, BluetoothGattUuid.CHARACTERISTIC_MODEL_NUMBER_STRING);
                    }
                    break;
                case 1:
                    // Write the current time
                    BluetoothBytesParser parser = new BluetoothBytesParser();
                    if (!ListenerUtil.mutListener.listen(4179)) {
                        parser.setCurrentTime(Calendar.getInstance());
                    }
                    if (!ListenerUtil.mutListener.listen(4180)) {
                        writeBytes(BluetoothGattUuid.SERVICE_CURRENT_TIME, BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME, parser.getValue());
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(4181)) {
                        // Turn on notification for Weight Service
                        setNotificationOn(BluetoothGattUuid.SERVICE_WEIGHT_SCALE, BluetoothGattUuid.CHARACTERISTIC_WEIGHT_MEASUREMENT);
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(4182)) {
                        // Turn on notification for Body Composition Service
                        setNotificationOn(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, BluetoothGattUuid.CHARACTERISTIC_BODY_COMPOSITION_MEASUREMENT);
                    }
                    break;
                case 4:
                    if (!ListenerUtil.mutListener.listen(4183)) {
                        // Turn on notification for User Data Service
                        setNotificationOn(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_CHANGE_INCREMENT);
                    }
                    if (!ListenerUtil.mutListener.listen(4184)) {
                        setNotificationOn(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT);
                    }
                    break;
                case 5:
                    if (!ListenerUtil.mutListener.listen(4185)) {
                        // Turn on notifications for Battery Service
                        setNotificationOn(BluetoothGattUuid.SERVICE_BATTERY_LEVEL, BluetoothGattUuid.CHARACTERISTIC_BATTERY_LEVEL);
                    }
                    break;
                case 6:
                    final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                    if (!ListenerUtil.mutListener.listen(4186)) {
                        registerUser(CURRENT_USER_CONSENT);
                    }
                    if (!ListenerUtil.mutListener.listen(4187)) {
                        setUser(selectedUser.getId(), CURRENT_USER_CONSENT);
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
        BluetoothBytesParser parser = new BluetoothBytesParser(value);
        if (!ListenerUtil.mutListener.listen(4225)) {
            if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME)) {
                Date currentTime = parser.getDateTime();
                if (!ListenerUtil.mutListener.listen(4224)) {
                    Timber.d(String.format("Received device time: %s", currentTime));
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_WEIGHT_MEASUREMENT)) {
                if (!ListenerUtil.mutListener.listen(4223)) {
                    handleWeightMeasurement(value);
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_BODY_COMPOSITION_MEASUREMENT)) {
                if (!ListenerUtil.mutListener.listen(4222)) {
                    handleBodyCompositionMeasurement(value);
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_BATTERY_LEVEL)) {
                int batteryLevel = parser.getIntValue(FORMAT_UINT8);
                if (!ListenerUtil.mutListener.listen(4221)) {
                    Timber.d(String.format("Received battery level %d%%", batteryLevel));
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_MANUFACTURER_NAME_STRING)) {
                String manufacturer = parser.getStringValue(0);
                if (!ListenerUtil.mutListener.listen(4220)) {
                    Timber.d(String.format("Received manufacturer: %s", manufacturer));
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_MODEL_NUMBER_STRING)) {
                String modelNumber = parser.getStringValue(0);
                if (!ListenerUtil.mutListener.listen(4219)) {
                    Timber.d(String.format("Received modelnumber: %s", modelNumber));
                }
            } else if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT)) {
                if (!ListenerUtil.mutListener.listen(4218)) {
                    if ((ListenerUtil.mutListener.listen(4194) ? (value[0] >= UDS_CP_RESPONSE) : (ListenerUtil.mutListener.listen(4193) ? (value[0] <= UDS_CP_RESPONSE) : (ListenerUtil.mutListener.listen(4192) ? (value[0] > UDS_CP_RESPONSE) : (ListenerUtil.mutListener.listen(4191) ? (value[0] < UDS_CP_RESPONSE) : (ListenerUtil.mutListener.listen(4190) ? (value[0] != UDS_CP_RESPONSE) : (value[0] == UDS_CP_RESPONSE))))))) {
                        if (!ListenerUtil.mutListener.listen(4217)) {
                            switch(value[1]) {
                                case UDS_CP_REGISTER_NEW_USER:
                                    if (!ListenerUtil.mutListener.listen(4202)) {
                                        if ((ListenerUtil.mutListener.listen(4199) ? (value[2] >= UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4198) ? (value[2] <= UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4197) ? (value[2] > UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4196) ? (value[2] < UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4195) ? (value[2] != UDS_CP_RESP_VALUE_SUCCESS) : (value[2] == UDS_CP_RESP_VALUE_SUCCESS))))))) {
                                            int userIndex = value[3];
                                            if (!ListenerUtil.mutListener.listen(4201)) {
                                                Timber.d(String.format("Created user %d", userIndex));
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(4200)) {
                                                Timber.e("ERROR: could not register new user");
                                            }
                                        }
                                    }
                                    break;
                                case UDS_CP_CONSENT:
                                    if (!ListenerUtil.mutListener.listen(4215)) {
                                        if ((ListenerUtil.mutListener.listen(4207) ? (value[2] >= UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4206) ? (value[2] <= UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4205) ? (value[2] > UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4204) ? (value[2] < UDS_CP_RESP_VALUE_SUCCESS) : (ListenerUtil.mutListener.listen(4203) ? (value[2] != UDS_CP_RESP_VALUE_SUCCESS) : (value[2] == UDS_CP_RESP_VALUE_SUCCESS))))))) {
                                            if (!ListenerUtil.mutListener.listen(4214)) {
                                                Timber.d("Success user consent");
                                            }
                                        } else if ((ListenerUtil.mutListener.listen(4212) ? (value[2] >= UDS_CP_RESP_USER_NOT_AUTHORIZED) : (ListenerUtil.mutListener.listen(4211) ? (value[2] <= UDS_CP_RESP_USER_NOT_AUTHORIZED) : (ListenerUtil.mutListener.listen(4210) ? (value[2] > UDS_CP_RESP_USER_NOT_AUTHORIZED) : (ListenerUtil.mutListener.listen(4209) ? (value[2] < UDS_CP_RESP_USER_NOT_AUTHORIZED) : (ListenerUtil.mutListener.listen(4208) ? (value[2] != UDS_CP_RESP_USER_NOT_AUTHORIZED) : (value[2] == UDS_CP_RESP_USER_NOT_AUTHORIZED))))))) {
                                            if (!ListenerUtil.mutListener.listen(4213)) {
                                                Timber.e("Not authorized");
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    if (!ListenerUtil.mutListener.listen(4216)) {
                                        Timber.e("Unhandled response");
                                    }
                                    break;
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4189)) {
                    Timber.d(String.format("Got data: <%s>", byteInHex(value)));
                }
            }
        }
    }

    private void handleWeightMeasurement(byte[] value) {
        BluetoothBytesParser parser = new BluetoothBytesParser(value);
        final int flags = parser.getIntValue(BluetoothBytesParser.FORMAT_UINT8);
        boolean isKg = (ListenerUtil.mutListener.listen(4230) ? ((flags & 0x01) >= 0) : (ListenerUtil.mutListener.listen(4229) ? ((flags & 0x01) <= 0) : (ListenerUtil.mutListener.listen(4228) ? ((flags & 0x01) > 0) : (ListenerUtil.mutListener.listen(4227) ? ((flags & 0x01) < 0) : (ListenerUtil.mutListener.listen(4226) ? ((flags & 0x01) != 0) : ((flags & 0x01) == 0))))));
        final boolean timestampPresent = (ListenerUtil.mutListener.listen(4235) ? ((flags & 0x02) >= 0) : (ListenerUtil.mutListener.listen(4234) ? ((flags & 0x02) <= 0) : (ListenerUtil.mutListener.listen(4233) ? ((flags & 0x02) < 0) : (ListenerUtil.mutListener.listen(4232) ? ((flags & 0x02) != 0) : (ListenerUtil.mutListener.listen(4231) ? ((flags & 0x02) == 0) : ((flags & 0x02) > 0))))));
        final boolean userIDPresent = (ListenerUtil.mutListener.listen(4240) ? ((flags & 0x04) >= 0) : (ListenerUtil.mutListener.listen(4239) ? ((flags & 0x04) <= 0) : (ListenerUtil.mutListener.listen(4238) ? ((flags & 0x04) < 0) : (ListenerUtil.mutListener.listen(4237) ? ((flags & 0x04) != 0) : (ListenerUtil.mutListener.listen(4236) ? ((flags & 0x04) == 0) : ((flags & 0x04) > 0))))));
        final boolean bmiAndHeightPresent = (ListenerUtil.mutListener.listen(4245) ? ((flags & 0x08) >= 0) : (ListenerUtil.mutListener.listen(4244) ? ((flags & 0x08) <= 0) : (ListenerUtil.mutListener.listen(4243) ? ((flags & 0x08) < 0) : (ListenerUtil.mutListener.listen(4242) ? ((flags & 0x08) != 0) : (ListenerUtil.mutListener.listen(4241) ? ((flags & 0x08) == 0) : ((flags & 0x08) > 0))))));
        ScaleMeasurement scaleMeasurement = new ScaleMeasurement();
        // Determine the right weight multiplier
        float weightMultiplier = isKg ? 0.005f : 0.01f;
        // Get weight
        float weightValue = (ListenerUtil.mutListener.listen(4249) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % weightMultiplier) : (ListenerUtil.mutListener.listen(4248) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / weightMultiplier) : (ListenerUtil.mutListener.listen(4247) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - weightMultiplier) : (ListenerUtil.mutListener.listen(4246) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + weightMultiplier) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * weightMultiplier)))));
        if (!ListenerUtil.mutListener.listen(4250)) {
            scaleMeasurement.setWeight(weightValue);
        }
        if (!ListenerUtil.mutListener.listen(4252)) {
            if (timestampPresent) {
                Date timestamp = parser.getDateTime();
                if (!ListenerUtil.mutListener.listen(4251)) {
                    scaleMeasurement.setDateTime(timestamp);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4254)) {
            if (userIDPresent) {
                int userID = parser.getIntValue(BluetoothBytesParser.FORMAT_UINT8);
                if (!ListenerUtil.mutListener.listen(4253)) {
                    Timber.d(String.format("User id: %d", userID));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4263)) {
            if (bmiAndHeightPresent) {
                float BMI = (ListenerUtil.mutListener.listen(4258) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % 0.1f) : (ListenerUtil.mutListener.listen(4257) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / 0.1f) : (ListenerUtil.mutListener.listen(4256) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - 0.1f) : (ListenerUtil.mutListener.listen(4255) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + 0.1f) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * 0.1f)))));
                float heightInMeters = (ListenerUtil.mutListener.listen(4262) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % 0.001f) : (ListenerUtil.mutListener.listen(4261) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / 0.001f) : (ListenerUtil.mutListener.listen(4260) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - 0.001f) : (ListenerUtil.mutListener.listen(4259) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + 0.001f) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * 0.001f)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(4264)) {
            Timber.d(String.format("Got weight: %s", weightValue));
        }
        if (!ListenerUtil.mutListener.listen(4265)) {
            addScaleMeasurement(scaleMeasurement);
        }
    }

    private void handleBodyCompositionMeasurement(byte[] value) {
        BluetoothBytesParser parser = new BluetoothBytesParser(value);
        final int flags = parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16);
        boolean isKg = (ListenerUtil.mutListener.listen(4270) ? ((flags & 0x0001) >= 0) : (ListenerUtil.mutListener.listen(4269) ? ((flags & 0x0001) <= 0) : (ListenerUtil.mutListener.listen(4268) ? ((flags & 0x0001) > 0) : (ListenerUtil.mutListener.listen(4267) ? ((flags & 0x0001) < 0) : (ListenerUtil.mutListener.listen(4266) ? ((flags & 0x0001) != 0) : ((flags & 0x0001) == 0))))));
        float massMultiplier = (float) (isKg ? 0.005 : 0.01);
        boolean timestampPresent = (ListenerUtil.mutListener.listen(4275) ? ((flags & 0x0002) >= 0) : (ListenerUtil.mutListener.listen(4274) ? ((flags & 0x0002) <= 0) : (ListenerUtil.mutListener.listen(4273) ? ((flags & 0x0002) < 0) : (ListenerUtil.mutListener.listen(4272) ? ((flags & 0x0002) != 0) : (ListenerUtil.mutListener.listen(4271) ? ((flags & 0x0002) == 0) : ((flags & 0x0002) > 0))))));
        boolean userIDPresent = (ListenerUtil.mutListener.listen(4280) ? ((flags & 0x0004) >= 0) : (ListenerUtil.mutListener.listen(4279) ? ((flags & 0x0004) <= 0) : (ListenerUtil.mutListener.listen(4278) ? ((flags & 0x0004) < 0) : (ListenerUtil.mutListener.listen(4277) ? ((flags & 0x0004) != 0) : (ListenerUtil.mutListener.listen(4276) ? ((flags & 0x0004) == 0) : ((flags & 0x0004) > 0))))));
        boolean bmrPresent = (ListenerUtil.mutListener.listen(4285) ? ((flags & 0x0008) >= 0) : (ListenerUtil.mutListener.listen(4284) ? ((flags & 0x0008) <= 0) : (ListenerUtil.mutListener.listen(4283) ? ((flags & 0x0008) < 0) : (ListenerUtil.mutListener.listen(4282) ? ((flags & 0x0008) != 0) : (ListenerUtil.mutListener.listen(4281) ? ((flags & 0x0008) == 0) : ((flags & 0x0008) > 0))))));
        boolean musclePercentagePresent = (ListenerUtil.mutListener.listen(4290) ? ((flags & 0x0010) >= 0) : (ListenerUtil.mutListener.listen(4289) ? ((flags & 0x0010) <= 0) : (ListenerUtil.mutListener.listen(4288) ? ((flags & 0x0010) < 0) : (ListenerUtil.mutListener.listen(4287) ? ((flags & 0x0010) != 0) : (ListenerUtil.mutListener.listen(4286) ? ((flags & 0x0010) == 0) : ((flags & 0x0010) > 0))))));
        boolean muscleMassPresent = (ListenerUtil.mutListener.listen(4295) ? ((flags & 0x0020) >= 0) : (ListenerUtil.mutListener.listen(4294) ? ((flags & 0x0020) <= 0) : (ListenerUtil.mutListener.listen(4293) ? ((flags & 0x0020) < 0) : (ListenerUtil.mutListener.listen(4292) ? ((flags & 0x0020) != 0) : (ListenerUtil.mutListener.listen(4291) ? ((flags & 0x0020) == 0) : ((flags & 0x0020) > 0))))));
        boolean fatFreeMassPresent = (ListenerUtil.mutListener.listen(4300) ? ((flags & 0x0040) >= 0) : (ListenerUtil.mutListener.listen(4299) ? ((flags & 0x0040) <= 0) : (ListenerUtil.mutListener.listen(4298) ? ((flags & 0x0040) < 0) : (ListenerUtil.mutListener.listen(4297) ? ((flags & 0x0040) != 0) : (ListenerUtil.mutListener.listen(4296) ? ((flags & 0x0040) == 0) : ((flags & 0x0040) > 0))))));
        boolean softLeanMassPresent = (ListenerUtil.mutListener.listen(4305) ? ((flags & 0x0080) >= 0) : (ListenerUtil.mutListener.listen(4304) ? ((flags & 0x0080) <= 0) : (ListenerUtil.mutListener.listen(4303) ? ((flags & 0x0080) < 0) : (ListenerUtil.mutListener.listen(4302) ? ((flags & 0x0080) != 0) : (ListenerUtil.mutListener.listen(4301) ? ((flags & 0x0080) == 0) : ((flags & 0x0080) > 0))))));
        boolean bodyWaterMassPresent = (ListenerUtil.mutListener.listen(4310) ? ((flags & 0x0100) >= 0) : (ListenerUtil.mutListener.listen(4309) ? ((flags & 0x0100) <= 0) : (ListenerUtil.mutListener.listen(4308) ? ((flags & 0x0100) < 0) : (ListenerUtil.mutListener.listen(4307) ? ((flags & 0x0100) != 0) : (ListenerUtil.mutListener.listen(4306) ? ((flags & 0x0100) == 0) : ((flags & 0x0100) > 0))))));
        boolean impedancePresent = (ListenerUtil.mutListener.listen(4315) ? ((flags & 0x0200) >= 0) : (ListenerUtil.mutListener.listen(4314) ? ((flags & 0x0200) <= 0) : (ListenerUtil.mutListener.listen(4313) ? ((flags & 0x0200) < 0) : (ListenerUtil.mutListener.listen(4312) ? ((flags & 0x0200) != 0) : (ListenerUtil.mutListener.listen(4311) ? ((flags & 0x0200) == 0) : ((flags & 0x0200) > 0))))));
        boolean weightPresent = (ListenerUtil.mutListener.listen(4320) ? ((flags & 0x0400) >= 0) : (ListenerUtil.mutListener.listen(4319) ? ((flags & 0x0400) <= 0) : (ListenerUtil.mutListener.listen(4318) ? ((flags & 0x0400) < 0) : (ListenerUtil.mutListener.listen(4317) ? ((flags & 0x0400) != 0) : (ListenerUtil.mutListener.listen(4316) ? ((flags & 0x0400) == 0) : ((flags & 0x0400) > 0))))));
        boolean heightPresent = (ListenerUtil.mutListener.listen(4325) ? ((flags & 0x0800) >= 0) : (ListenerUtil.mutListener.listen(4324) ? ((flags & 0x0800) <= 0) : (ListenerUtil.mutListener.listen(4323) ? ((flags & 0x0800) < 0) : (ListenerUtil.mutListener.listen(4322) ? ((flags & 0x0800) != 0) : (ListenerUtil.mutListener.listen(4321) ? ((flags & 0x0800) == 0) : ((flags & 0x0800) > 0))))));
        boolean multiPacketMeasurement = (ListenerUtil.mutListener.listen(4330) ? ((flags & 0x1000) >= 0) : (ListenerUtil.mutListener.listen(4329) ? ((flags & 0x1000) <= 0) : (ListenerUtil.mutListener.listen(4328) ? ((flags & 0x1000) < 0) : (ListenerUtil.mutListener.listen(4327) ? ((flags & 0x1000) != 0) : (ListenerUtil.mutListener.listen(4326) ? ((flags & 0x1000) == 0) : ((flags & 0x1000) > 0))))));
        ScaleMeasurement scaleMeasurement = new ScaleMeasurement();
        float bodyFatPercentage = (ListenerUtil.mutListener.listen(4334) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % 0.1f) : (ListenerUtil.mutListener.listen(4333) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / 0.1f) : (ListenerUtil.mutListener.listen(4332) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - 0.1f) : (ListenerUtil.mutListener.listen(4331) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + 0.1f) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * 0.1f)))));
        if (!ListenerUtil.mutListener.listen(4335)) {
            scaleMeasurement.setFat(bodyFatPercentage);
        }
        if (!ListenerUtil.mutListener.listen(4337)) {
            // Read timestamp if present
            if (timestampPresent) {
                Date timestamp = parser.getDateTime();
                if (!ListenerUtil.mutListener.listen(4336)) {
                    scaleMeasurement.setDateTime(timestamp);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4339)) {
            // Read userID if present
            if (userIDPresent) {
                int userID = parser.getIntValue(BluetoothBytesParser.FORMAT_UINT8);
                if (!ListenerUtil.mutListener.listen(4338)) {
                    Timber.d(String.format("user id: %d", userID));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4352)) {
            // Read bmr if present
            if (bmrPresent) {
                int bmrInJoules = parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16);
                int bmrInKcal = Math.round((ListenerUtil.mutListener.listen(4351) ? (((ListenerUtil.mutListener.listen(4347) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) % 10.0f) : (ListenerUtil.mutListener.listen(4346) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) / 10.0f) : (ListenerUtil.mutListener.listen(4345) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) - 10.0f) : (ListenerUtil.mutListener.listen(4344) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) + 10.0f) : (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) * 10.0f)))))) % 10.0f) : (ListenerUtil.mutListener.listen(4350) ? (((ListenerUtil.mutListener.listen(4347) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) % 10.0f) : (ListenerUtil.mutListener.listen(4346) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) / 10.0f) : (ListenerUtil.mutListener.listen(4345) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) - 10.0f) : (ListenerUtil.mutListener.listen(4344) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) + 10.0f) : (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) * 10.0f)))))) * 10.0f) : (ListenerUtil.mutListener.listen(4349) ? (((ListenerUtil.mutListener.listen(4347) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) % 10.0f) : (ListenerUtil.mutListener.listen(4346) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) / 10.0f) : (ListenerUtil.mutListener.listen(4345) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) - 10.0f) : (ListenerUtil.mutListener.listen(4344) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) + 10.0f) : (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) * 10.0f)))))) - 10.0f) : (ListenerUtil.mutListener.listen(4348) ? (((ListenerUtil.mutListener.listen(4347) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) % 10.0f) : (ListenerUtil.mutListener.listen(4346) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) / 10.0f) : (ListenerUtil.mutListener.listen(4345) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) - 10.0f) : (ListenerUtil.mutListener.listen(4344) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) + 10.0f) : (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) * 10.0f)))))) + 10.0f) : (((ListenerUtil.mutListener.listen(4347) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) % 10.0f) : (ListenerUtil.mutListener.listen(4346) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) / 10.0f) : (ListenerUtil.mutListener.listen(4345) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) - 10.0f) : (ListenerUtil.mutListener.listen(4344) ? (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) + 10.0f) : (((ListenerUtil.mutListener.listen(4343) ? (bmrInJoules % 4.1868f) : (ListenerUtil.mutListener.listen(4342) ? (bmrInJoules * 4.1868f) : (ListenerUtil.mutListener.listen(4341) ? (bmrInJoules - 4.1868f) : (ListenerUtil.mutListener.listen(4340) ? (bmrInJoules + 4.1868f) : (bmrInJoules / 4.1868f)))))) * 10.0f)))))) / 10.0f))))));
            }
        }
        if (!ListenerUtil.mutListener.listen(4358)) {
            // Read musclePercentage if present
            if (musclePercentagePresent) {
                float musclePercentage = (ListenerUtil.mutListener.listen(4356) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % 0.1f) : (ListenerUtil.mutListener.listen(4355) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / 0.1f) : (ListenerUtil.mutListener.listen(4354) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - 0.1f) : (ListenerUtil.mutListener.listen(4353) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + 0.1f) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * 0.1f)))));
                if (!ListenerUtil.mutListener.listen(4357)) {
                    scaleMeasurement.setMuscle(musclePercentage);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4363)) {
            // Read muscleMass if present
            if (muscleMassPresent) {
                float muscleMass = (ListenerUtil.mutListener.listen(4362) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % massMultiplier) : (ListenerUtil.mutListener.listen(4361) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / massMultiplier) : (ListenerUtil.mutListener.listen(4360) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - massMultiplier) : (ListenerUtil.mutListener.listen(4359) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + massMultiplier) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * massMultiplier)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(4368)) {
            // Read fatFreeMassPresent if present
            if (fatFreeMassPresent) {
                float fatFreeMass = (ListenerUtil.mutListener.listen(4367) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % massMultiplier) : (ListenerUtil.mutListener.listen(4366) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / massMultiplier) : (ListenerUtil.mutListener.listen(4365) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - massMultiplier) : (ListenerUtil.mutListener.listen(4364) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + massMultiplier) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * massMultiplier)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(4373)) {
            // Read softleanMass if present
            if (softLeanMassPresent) {
                float softLeanMass = (ListenerUtil.mutListener.listen(4372) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % massMultiplier) : (ListenerUtil.mutListener.listen(4371) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / massMultiplier) : (ListenerUtil.mutListener.listen(4370) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - massMultiplier) : (ListenerUtil.mutListener.listen(4369) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + massMultiplier) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * massMultiplier)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(4379)) {
            // Read bodyWaterMass if present
            if (bodyWaterMassPresent) {
                float bodyWaterMass = (ListenerUtil.mutListener.listen(4377) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % massMultiplier) : (ListenerUtil.mutListener.listen(4376) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / massMultiplier) : (ListenerUtil.mutListener.listen(4375) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - massMultiplier) : (ListenerUtil.mutListener.listen(4374) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + massMultiplier) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * massMultiplier)))));
                if (!ListenerUtil.mutListener.listen(4378)) {
                    scaleMeasurement.setWater(bodyWaterMass);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4384)) {
            // Read impedance if present
            if (impedancePresent) {
                float impedance = (ListenerUtil.mutListener.listen(4383) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % 0.1f) : (ListenerUtil.mutListener.listen(4382) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / 0.1f) : (ListenerUtil.mutListener.listen(4381) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - 0.1f) : (ListenerUtil.mutListener.listen(4380) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + 0.1f) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * 0.1f)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(4390)) {
            // Read weight if present
            if (weightPresent) {
                float weightValue = (ListenerUtil.mutListener.listen(4388) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) % massMultiplier) : (ListenerUtil.mutListener.listen(4387) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) / massMultiplier) : (ListenerUtil.mutListener.listen(4386) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) - massMultiplier) : (ListenerUtil.mutListener.listen(4385) ? (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) + massMultiplier) : (parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16) * massMultiplier)))));
                if (!ListenerUtil.mutListener.listen(4389)) {
                    scaleMeasurement.setWeight(weightValue);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4391)) {
            // Read height if present
            if (heightPresent) {
                float heightValue = parser.getIntValue(BluetoothBytesParser.FORMAT_UINT16);
            }
        }
        if (!ListenerUtil.mutListener.listen(4392)) {
            Timber.d(String.format("Got body composition: %s", byteInHex(value)));
        }
        if (!ListenerUtil.mutListener.listen(4393)) {
            addScaleMeasurement(scaleMeasurement);
        }
    }

    private void registerUser(int consentCode) {
        BluetoothBytesParser parser = new BluetoothBytesParser(new byte[] { 0, 0, 0 });
        if (!ListenerUtil.mutListener.listen(4394)) {
            parser.setIntValue(UDS_CP_REGISTER_NEW_USER, FORMAT_UINT8, 0);
        }
        if (!ListenerUtil.mutListener.listen(4395)) {
            parser.setIntValue(consentCode, FORMAT_UINT16, 1);
        }
        if (!ListenerUtil.mutListener.listen(4396)) {
            Timber.d(String.format("registerUser consentCode: %d", consentCode));
        }
        if (!ListenerUtil.mutListener.listen(4397)) {
            writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT, parser.getValue());
        }
    }

    private void setUser(int userIndex, int consentCode) {
        BluetoothBytesParser parser = new BluetoothBytesParser(new byte[] { 0, 0, 0, 0 });
        if (!ListenerUtil.mutListener.listen(4398)) {
            parser.setIntValue(UDS_CP_CONSENT, FORMAT_UINT8, 0);
        }
        if (!ListenerUtil.mutListener.listen(4399)) {
            parser.setIntValue(userIndex, FORMAT_UINT8, 1);
        }
        if (!ListenerUtil.mutListener.listen(4400)) {
            parser.setIntValue(consentCode, FORMAT_UINT16, 2);
        }
        if (!ListenerUtil.mutListener.listen(4401)) {
            Timber.d(String.format("setUser userIndex: %d, consentCode: %d", userIndex, consentCode));
        }
        if (!ListenerUtil.mutListener.listen(4402)) {
            writeBytes(BluetoothGattUuid.SERVICE_USER_DATA, BluetoothGattUuid.CHARACTERISTIC_USER_CONTROL_POINT, parser.getValue());
        }
    }
}
