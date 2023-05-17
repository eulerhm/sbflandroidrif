/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
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
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.lib.MiScaleLib;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import timber.log.Timber;
import static com.health.openscale.core.bluetooth.BluetoothCommunication.BT_STATUS.UNEXPECTED_ERROR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothMiScale2 extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC = UUID.fromString("00002a2f-0000-3512-2118-0009af100700");

    private final UUID WEIGHT_CUSTOM_SERVICE = UUID.fromString("00001530-0000-3512-2118-0009af100700");

    private final UUID WEIGHT_CUSTOM_CONFIG = UUID.fromString("00001542-0000-3512-2118-0009af100700");

    public BluetoothMiScale2(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Xiaomi Mi Scale v2";
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        final byte[] data = value;
        if (!ListenerUtil.mutListener.listen(3483)) {
            if ((ListenerUtil.mutListener.listen(3456) ? (data != null || (ListenerUtil.mutListener.listen(3455) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(3454) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(3453) ? (data.length < 0) : (ListenerUtil.mutListener.listen(3452) ? (data.length != 0) : (ListenerUtil.mutListener.listen(3451) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(3455) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(3454) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(3453) ? (data.length < 0) : (ListenerUtil.mutListener.listen(3452) ? (data.length != 0) : (ListenerUtil.mutListener.listen(3451) ? (data.length == 0) : (data.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(3457)) {
                    Timber.d("DataChange hex data: %s", byteInHex(data));
                }
                if (!ListenerUtil.mutListener.listen(3467)) {
                    // Stop command from mi scale received
                    if ((ListenerUtil.mutListener.listen(3462) ? (data[0] >= 0x03) : (ListenerUtil.mutListener.listen(3461) ? (data[0] <= 0x03) : (ListenerUtil.mutListener.listen(3460) ? (data[0] > 0x03) : (ListenerUtil.mutListener.listen(3459) ? (data[0] < 0x03) : (ListenerUtil.mutListener.listen(3458) ? (data[0] != 0x03) : (data[0] == 0x03))))))) {
                        if (!ListenerUtil.mutListener.listen(3463)) {
                            Timber.d("Scale stop byte received");
                        }
                        if (!ListenerUtil.mutListener.listen(3464)) {
                            // send stop command to mi scale
                            writeBytes(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, new byte[] { 0x03 });
                        }
                        // acknowledge that you received the last history data
                        int uniqueNumber = getUniqueNumber();
                        byte[] userIdentifier = new byte[] { (byte) 0x04, (byte) 0xFF, (byte) 0xFF, (byte) ((uniqueNumber & 0xFF00) >> 8), (byte) ((uniqueNumber & 0xFF) >> 0) };
                        if (!ListenerUtil.mutListener.listen(3465)) {
                            writeBytes(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, userIdentifier);
                        }
                        if (!ListenerUtil.mutListener.listen(3466)) {
                            resumeMachineState();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3475)) {
                    if ((ListenerUtil.mutListener.listen(3472) ? (data.length >= 26) : (ListenerUtil.mutListener.listen(3471) ? (data.length <= 26) : (ListenerUtil.mutListener.listen(3470) ? (data.length > 26) : (ListenerUtil.mutListener.listen(3469) ? (data.length < 26) : (ListenerUtil.mutListener.listen(3468) ? (data.length != 26) : (data.length == 26))))))) {
                        final byte[] firstWeight = Arrays.copyOfRange(data, 0, 10);
                        final byte[] secondWeight = Arrays.copyOfRange(data, 10, 20);
                        if (!ListenerUtil.mutListener.listen(3473)) {
                            parseBytes(firstWeight);
                        }
                        if (!ListenerUtil.mutListener.listen(3474)) {
                            parseBytes(secondWeight);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3482)) {
                    if ((ListenerUtil.mutListener.listen(3480) ? (data.length >= 13) : (ListenerUtil.mutListener.listen(3479) ? (data.length <= 13) : (ListenerUtil.mutListener.listen(3478) ? (data.length > 13) : (ListenerUtil.mutListener.listen(3477) ? (data.length < 13) : (ListenerUtil.mutListener.listen(3476) ? (data.length != 13) : (data.length == 13))))))) {
                        if (!ListenerUtil.mutListener.listen(3481)) {
                            parseBytes(data);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(3494)) {
            switch(stepNr) {
                case 0:
                    // set scale units
                    final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                    byte[] setUnitCmd = new byte[] { (byte) 0x06, (byte) 0x04, (byte) 0x00, (byte) selectedUser.getScaleUnit().toInt() };
                    if (!ListenerUtil.mutListener.listen(3484)) {
                        writeBytes(WEIGHT_CUSTOM_SERVICE, WEIGHT_CUSTOM_CONFIG, setUnitCmd);
                    }
                    break;
                case 1:
                    // set current time
                    Calendar currentDateTime = Calendar.getInstance();
                    int year = currentDateTime.get(Calendar.YEAR);
                    byte month = (byte) ((ListenerUtil.mutListener.listen(3488) ? (currentDateTime.get(Calendar.MONTH) % 1) : (ListenerUtil.mutListener.listen(3487) ? (currentDateTime.get(Calendar.MONTH) / 1) : (ListenerUtil.mutListener.listen(3486) ? (currentDateTime.get(Calendar.MONTH) * 1) : (ListenerUtil.mutListener.listen(3485) ? (currentDateTime.get(Calendar.MONTH) - 1) : (currentDateTime.get(Calendar.MONTH) + 1))))));
                    byte day = (byte) currentDateTime.get(Calendar.DAY_OF_MONTH);
                    byte hour = (byte) currentDateTime.get(Calendar.HOUR_OF_DAY);
                    byte min = (byte) currentDateTime.get(Calendar.MINUTE);
                    byte sec = (byte) currentDateTime.get(Calendar.SECOND);
                    byte[] dateTimeByte = { (byte) (year), (byte) (year >> 8), month, day, hour, min, sec, 0x03, 0x00, 0x00 };
                    if (!ListenerUtil.mutListener.listen(3489)) {
                        writeBytes(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME, dateTimeByte);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(3490)) {
                        // set notification on for weight measurement history
                        setNotificationOn(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC);
                    }
                    break;
                case 3:
                    // configure scale to get only last measurements
                    int uniqueNumber = getUniqueNumber();
                    byte[] userIdentifier = new byte[] { (byte) 0x01, (byte) 0xFF, (byte) 0xFF, (byte) ((uniqueNumber & 0xFF00) >> 8), (byte) ((uniqueNumber & 0xFF) >> 0) };
                    if (!ListenerUtil.mutListener.listen(3491)) {
                        writeBytes(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, userIdentifier);
                    }
                    break;
                case 4:
                    if (!ListenerUtil.mutListener.listen(3492)) {
                        // invoke receiving history data
                        writeBytes(BluetoothGattUuid.SERVICE_BODY_COMPOSITION, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, new byte[] { 0x02 });
                    }
                    if (!ListenerUtil.mutListener.listen(3493)) {
                        stopMachineState();
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void parseBytes(byte[] data) {
        try {
            final byte ctrlByte0 = data[0];
            final byte ctrlByte1 = data[1];
            final boolean isWeightRemoved = isBitSet(ctrlByte1, 7);
            final boolean isDateInvalid = isBitSet(ctrlByte1, 6);
            final boolean isStabilized = isBitSet(ctrlByte1, 5);
            final boolean isLBSUnit = isBitSet(ctrlByte0, 0);
            final boolean isCattyUnit = isBitSet(ctrlByte1, 6);
            final boolean isImpedance = isBitSet(ctrlByte1, 1);
            if (!ListenerUtil.mutListener.listen(3535)) {
                if ((ListenerUtil.mutListener.listen(3497) ? ((ListenerUtil.mutListener.listen(3496) ? (isStabilized || !isWeightRemoved) : (isStabilized && !isWeightRemoved)) || !isDateInvalid) : ((ListenerUtil.mutListener.listen(3496) ? (isStabilized || !isWeightRemoved) : (isStabilized && !isWeightRemoved)) && !isDateInvalid))) {
                    final int year = ((data[3] & 0xFF) << 8) | (data[2] & 0xFF);
                    final int month = (int) data[4];
                    final int day = (int) data[5];
                    final int hours = (int) data[6];
                    final int min = (int) data[7];
                    final int sec = (int) data[8];
                    float weight;
                    float impedance = 0.0f;
                    if ((ListenerUtil.mutListener.listen(3498) ? (isLBSUnit && isCattyUnit) : (isLBSUnit || isCattyUnit))) {
                        weight = (ListenerUtil.mutListener.listen(3506) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) % 100.0f) : (ListenerUtil.mutListener.listen(3505) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) * 100.0f) : (ListenerUtil.mutListener.listen(3504) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) - 100.0f) : (ListenerUtil.mutListener.listen(3503) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) + 100.0f) : ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) / 100.0f)))));
                    } else {
                        weight = (ListenerUtil.mutListener.listen(3502) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) % 200.0f) : (ListenerUtil.mutListener.listen(3501) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) * 200.0f) : (ListenerUtil.mutListener.listen(3500) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) - 200.0f) : (ListenerUtil.mutListener.listen(3499) ? ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) + 200.0f) : ((float) (((data[12] & 0xFF) << 8) | (data[11] & 0xFF)) / 200.0f)))));
                    }
                    if (!ListenerUtil.mutListener.listen(3509)) {
                        if (isImpedance) {
                            if (!ListenerUtil.mutListener.listen(3507)) {
                                impedance = ((data[10] & 0xFF) << 8) | (data[9] & 0xFF);
                            }
                            if (!ListenerUtil.mutListener.listen(3508)) {
                                Timber.d("impedance value is " + impedance);
                            }
                        }
                    }
                    String date_string = year + "/" + month + "/" + day + "/" + hours + "/" + min;
                    Date date_time = new SimpleDateFormat("yyyy/MM/dd/HH/mm").parse(date_string);
                    if (!ListenerUtil.mutListener.listen(3534)) {
                        // Is the year plausible? Check if the year is in the range of 20 years...
                        if (validateDate(date_time, 20)) {
                            final ScaleUser scaleUser = OpenScale.getInstance().getSelectedScaleUser();
                            ScaleMeasurement scaleBtData = new ScaleMeasurement();
                            if (!ListenerUtil.mutListener.listen(3511)) {
                                scaleBtData.setWeight(Converters.toKilogram(weight, scaleUser.getScaleUnit()));
                            }
                            if (!ListenerUtil.mutListener.listen(3512)) {
                                scaleBtData.setDateTime(date_time);
                            }
                            int sex;
                            if (scaleUser.getGender() == Converters.Gender.MALE) {
                                sex = 1;
                            } else {
                                sex = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(3532)) {
                                if ((ListenerUtil.mutListener.listen(3517) ? (impedance >= 0.0f) : (ListenerUtil.mutListener.listen(3516) ? (impedance <= 0.0f) : (ListenerUtil.mutListener.listen(3515) ? (impedance > 0.0f) : (ListenerUtil.mutListener.listen(3514) ? (impedance < 0.0f) : (ListenerUtil.mutListener.listen(3513) ? (impedance == 0.0f) : (impedance != 0.0f))))))) {
                                    MiScaleLib miScaleLib = new MiScaleLib(sex, scaleUser.getAge(), scaleUser.getBodyHeight());
                                    if (!ListenerUtil.mutListener.listen(3519)) {
                                        scaleBtData.setWater(miScaleLib.getWater(weight, impedance));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3520)) {
                                        scaleBtData.setVisceralFat(miScaleLib.getVisceralFat(weight));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3521)) {
                                        scaleBtData.setFat(miScaleLib.getBodyFat(weight, impedance));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3530)) {
                                        // convert muscle in kg to percent
                                        scaleBtData.setMuscle((ListenerUtil.mutListener.listen(3529) ? (((ListenerUtil.mutListener.listen(3525) ? (100.0f % scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3524) ? (100.0f * scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3523) ? (100.0f - scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3522) ? (100.0f + scaleBtData.getWeight()) : (100.0f / scaleBtData.getWeight())))))) % miScaleLib.getMuscle(weight, impedance)) : (ListenerUtil.mutListener.listen(3528) ? (((ListenerUtil.mutListener.listen(3525) ? (100.0f % scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3524) ? (100.0f * scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3523) ? (100.0f - scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3522) ? (100.0f + scaleBtData.getWeight()) : (100.0f / scaleBtData.getWeight())))))) / miScaleLib.getMuscle(weight, impedance)) : (ListenerUtil.mutListener.listen(3527) ? (((ListenerUtil.mutListener.listen(3525) ? (100.0f % scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3524) ? (100.0f * scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3523) ? (100.0f - scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3522) ? (100.0f + scaleBtData.getWeight()) : (100.0f / scaleBtData.getWeight())))))) - miScaleLib.getMuscle(weight, impedance)) : (ListenerUtil.mutListener.listen(3526) ? (((ListenerUtil.mutListener.listen(3525) ? (100.0f % scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3524) ? (100.0f * scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3523) ? (100.0f - scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3522) ? (100.0f + scaleBtData.getWeight()) : (100.0f / scaleBtData.getWeight())))))) + miScaleLib.getMuscle(weight, impedance)) : (((ListenerUtil.mutListener.listen(3525) ? (100.0f % scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3524) ? (100.0f * scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3523) ? (100.0f - scaleBtData.getWeight()) : (ListenerUtil.mutListener.listen(3522) ? (100.0f + scaleBtData.getWeight()) : (100.0f / scaleBtData.getWeight())))))) * miScaleLib.getMuscle(weight, impedance)))))));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3531)) {
                                        scaleBtData.setBone(miScaleLib.getBoneMass(weight, impedance));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3518)) {
                                        Timber.d("Impedance value is zero");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3533)) {
                                addScaleMeasurement(scaleBtData);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3510)) {
                                Timber.e("Invalid Mi scale weight year %d", year);
                            }
                        }
                    }
                }
            }
        } catch (ParseException e) {
            if (!ListenerUtil.mutListener.listen(3495)) {
                setBluetoothStatus(UNEXPECTED_ERROR, "Error while decoding bluetooth date string (" + e.getMessage() + ")");
            }
        }
    }

    private boolean validateDate(Date weightDate, int range) {
        Calendar currentDatePos = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(3536)) {
            currentDatePos.add(Calendar.YEAR, range);
        }
        Calendar currentDateNeg = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(3537)) {
            currentDateNeg.add(Calendar.YEAR, -range);
        }
        if (!ListenerUtil.mutListener.listen(3539)) {
            if ((ListenerUtil.mutListener.listen(3538) ? (weightDate.before(currentDatePos.getTime()) || weightDate.after(currentDateNeg.getTime())) : (weightDate.before(currentDatePos.getTime()) && weightDate.after(currentDateNeg.getTime())))) {
                return true;
            }
        }
        return false;
    }

    private int getUniqueNumber() {
        int uniqueNumber;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        uniqueNumber = prefs.getInt("uniqueNumber", 0x00);
        if ((ListenerUtil.mutListener.listen(3544) ? (uniqueNumber >= 0x00) : (ListenerUtil.mutListener.listen(3543) ? (uniqueNumber <= 0x00) : (ListenerUtil.mutListener.listen(3542) ? (uniqueNumber > 0x00) : (ListenerUtil.mutListener.listen(3541) ? (uniqueNumber < 0x00) : (ListenerUtil.mutListener.listen(3540) ? (uniqueNumber != 0x00) : (uniqueNumber == 0x00))))))) {
            Random r = new Random();
            uniqueNumber = (ListenerUtil.mutListener.listen(3556) ? (r.nextInt((ListenerUtil.mutListener.listen(3552) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3551) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3550) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3549) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) + 1)))))) % 100) : (ListenerUtil.mutListener.listen(3555) ? (r.nextInt((ListenerUtil.mutListener.listen(3552) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3551) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3550) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3549) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) + 1)))))) / 100) : (ListenerUtil.mutListener.listen(3554) ? (r.nextInt((ListenerUtil.mutListener.listen(3552) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3551) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3550) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3549) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) + 1)))))) * 100) : (ListenerUtil.mutListener.listen(3553) ? (r.nextInt((ListenerUtil.mutListener.listen(3552) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3551) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3550) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3549) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) + 1)))))) - 100) : (r.nextInt((ListenerUtil.mutListener.listen(3552) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3551) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3550) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3549) ? ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3548) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3547) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3546) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3545) ? (65535 + 100) : (65535 - 100))))) + 1)))))) + 100)))));
            if (!ListenerUtil.mutListener.listen(3557)) {
                prefs.edit().putInt("uniqueNumber", uniqueNumber).apply();
            }
        }
        int userId = OpenScale.getInstance().getSelectedScaleUserId();
        return (ListenerUtil.mutListener.listen(3561) ? (uniqueNumber % userId) : (ListenerUtil.mutListener.listen(3560) ? (uniqueNumber / userId) : (ListenerUtil.mutListener.listen(3559) ? (uniqueNumber * userId) : (ListenerUtil.mutListener.listen(3558) ? (uniqueNumber - userId) : (uniqueNumber + userId)))));
    }
}
