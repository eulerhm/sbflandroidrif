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

public class BluetoothMiScale extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = UUID.fromString("0000181d-0000-1000-8000-00805f9b34fb");

    private final UUID WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC = UUID.fromString("00002a2f-0000-3512-2118-0009af100700");

    public BluetoothMiScale(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Xiaomi Mi Scale v1";
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        if (!ListenerUtil.mutListener.listen(3399)) {
            if (characteristic.equals(BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME)) {
                byte[] data = value;
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int currentMonth = (ListenerUtil.mutListener.listen(3374) ? (Calendar.getInstance().get(Calendar.MONTH) % 1) : (ListenerUtil.mutListener.listen(3373) ? (Calendar.getInstance().get(Calendar.MONTH) / 1) : (ListenerUtil.mutListener.listen(3372) ? (Calendar.getInstance().get(Calendar.MONTH) * 1) : (ListenerUtil.mutListener.listen(3371) ? (Calendar.getInstance().get(Calendar.MONTH) - 1) : (Calendar.getInstance().get(Calendar.MONTH) + 1)))));
                int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                int scaleYear = ((data[1] & 0xFF) << 8) | (data[0] & 0xFF);
                int scaleMonth = (int) data[2];
                int scaleDay = (int) data[3];
                if (!ListenerUtil.mutListener.listen(3398)) {
                    if (!((ListenerUtil.mutListener.listen(3391) ? ((ListenerUtil.mutListener.listen(3385) ? ((ListenerUtil.mutListener.listen(3379) ? (currentYear >= scaleYear) : (ListenerUtil.mutListener.listen(3378) ? (currentYear <= scaleYear) : (ListenerUtil.mutListener.listen(3377) ? (currentYear > scaleYear) : (ListenerUtil.mutListener.listen(3376) ? (currentYear < scaleYear) : (ListenerUtil.mutListener.listen(3375) ? (currentYear != scaleYear) : (currentYear == scaleYear)))))) || (ListenerUtil.mutListener.listen(3384) ? (currentMonth >= scaleMonth) : (ListenerUtil.mutListener.listen(3383) ? (currentMonth <= scaleMonth) : (ListenerUtil.mutListener.listen(3382) ? (currentMonth > scaleMonth) : (ListenerUtil.mutListener.listen(3381) ? (currentMonth < scaleMonth) : (ListenerUtil.mutListener.listen(3380) ? (currentMonth != scaleMonth) : (currentMonth == scaleMonth))))))) : ((ListenerUtil.mutListener.listen(3379) ? (currentYear >= scaleYear) : (ListenerUtil.mutListener.listen(3378) ? (currentYear <= scaleYear) : (ListenerUtil.mutListener.listen(3377) ? (currentYear > scaleYear) : (ListenerUtil.mutListener.listen(3376) ? (currentYear < scaleYear) : (ListenerUtil.mutListener.listen(3375) ? (currentYear != scaleYear) : (currentYear == scaleYear)))))) && (ListenerUtil.mutListener.listen(3384) ? (currentMonth >= scaleMonth) : (ListenerUtil.mutListener.listen(3383) ? (currentMonth <= scaleMonth) : (ListenerUtil.mutListener.listen(3382) ? (currentMonth > scaleMonth) : (ListenerUtil.mutListener.listen(3381) ? (currentMonth < scaleMonth) : (ListenerUtil.mutListener.listen(3380) ? (currentMonth != scaleMonth) : (currentMonth == scaleMonth)))))))) || (ListenerUtil.mutListener.listen(3390) ? (currentDay >= scaleDay) : (ListenerUtil.mutListener.listen(3389) ? (currentDay <= scaleDay) : (ListenerUtil.mutListener.listen(3388) ? (currentDay > scaleDay) : (ListenerUtil.mutListener.listen(3387) ? (currentDay < scaleDay) : (ListenerUtil.mutListener.listen(3386) ? (currentDay != scaleDay) : (currentDay == scaleDay))))))) : ((ListenerUtil.mutListener.listen(3385) ? ((ListenerUtil.mutListener.listen(3379) ? (currentYear >= scaleYear) : (ListenerUtil.mutListener.listen(3378) ? (currentYear <= scaleYear) : (ListenerUtil.mutListener.listen(3377) ? (currentYear > scaleYear) : (ListenerUtil.mutListener.listen(3376) ? (currentYear < scaleYear) : (ListenerUtil.mutListener.listen(3375) ? (currentYear != scaleYear) : (currentYear == scaleYear)))))) || (ListenerUtil.mutListener.listen(3384) ? (currentMonth >= scaleMonth) : (ListenerUtil.mutListener.listen(3383) ? (currentMonth <= scaleMonth) : (ListenerUtil.mutListener.listen(3382) ? (currentMonth > scaleMonth) : (ListenerUtil.mutListener.listen(3381) ? (currentMonth < scaleMonth) : (ListenerUtil.mutListener.listen(3380) ? (currentMonth != scaleMonth) : (currentMonth == scaleMonth))))))) : ((ListenerUtil.mutListener.listen(3379) ? (currentYear >= scaleYear) : (ListenerUtil.mutListener.listen(3378) ? (currentYear <= scaleYear) : (ListenerUtil.mutListener.listen(3377) ? (currentYear > scaleYear) : (ListenerUtil.mutListener.listen(3376) ? (currentYear < scaleYear) : (ListenerUtil.mutListener.listen(3375) ? (currentYear != scaleYear) : (currentYear == scaleYear)))))) && (ListenerUtil.mutListener.listen(3384) ? (currentMonth >= scaleMonth) : (ListenerUtil.mutListener.listen(3383) ? (currentMonth <= scaleMonth) : (ListenerUtil.mutListener.listen(3382) ? (currentMonth > scaleMonth) : (ListenerUtil.mutListener.listen(3381) ? (currentMonth < scaleMonth) : (ListenerUtil.mutListener.listen(3380) ? (currentMonth != scaleMonth) : (currentMonth == scaleMonth)))))))) && (ListenerUtil.mutListener.listen(3390) ? (currentDay >= scaleDay) : (ListenerUtil.mutListener.listen(3389) ? (currentDay <= scaleDay) : (ListenerUtil.mutListener.listen(3388) ? (currentDay > scaleDay) : (ListenerUtil.mutListener.listen(3387) ? (currentDay < scaleDay) : (ListenerUtil.mutListener.listen(3386) ? (currentDay != scaleDay) : (currentDay == scaleDay)))))))))) {
                        if (!ListenerUtil.mutListener.listen(3392)) {
                            Timber.d("Current year and scale year is different");
                        }
                        // set current time
                        Calendar currentDateTime = Calendar.getInstance();
                        int year = currentDateTime.get(Calendar.YEAR);
                        byte month = (byte) ((ListenerUtil.mutListener.listen(3396) ? (currentDateTime.get(Calendar.MONTH) % 1) : (ListenerUtil.mutListener.listen(3395) ? (currentDateTime.get(Calendar.MONTH) / 1) : (ListenerUtil.mutListener.listen(3394) ? (currentDateTime.get(Calendar.MONTH) * 1) : (ListenerUtil.mutListener.listen(3393) ? (currentDateTime.get(Calendar.MONTH) - 1) : (currentDateTime.get(Calendar.MONTH) + 1))))));
                        byte day = (byte) currentDateTime.get(Calendar.DAY_OF_MONTH);
                        byte hour = (byte) currentDateTime.get(Calendar.HOUR_OF_DAY);
                        byte min = (byte) currentDateTime.get(Calendar.MINUTE);
                        byte sec = (byte) currentDateTime.get(Calendar.SECOND);
                        byte[] dateTimeByte = { (byte) (year), (byte) (year >> 8), month, day, hour, min, sec, 0x03, 0x00, 0x00 };
                        if (!ListenerUtil.mutListener.listen(3397)) {
                            writeBytes(WEIGHT_MEASUREMENT_SERVICE, BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME, dateTimeByte);
                        }
                    }
                }
            } else {
                final byte[] data = value;
                if (!ListenerUtil.mutListener.listen(3370)) {
                    if ((ListenerUtil.mutListener.listen(3345) ? (data != null || (ListenerUtil.mutListener.listen(3344) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(3343) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(3342) ? (data.length < 0) : (ListenerUtil.mutListener.listen(3341) ? (data.length != 0) : (ListenerUtil.mutListener.listen(3340) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(3344) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(3343) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(3342) ? (data.length < 0) : (ListenerUtil.mutListener.listen(3341) ? (data.length != 0) : (ListenerUtil.mutListener.listen(3340) ? (data.length == 0) : (data.length > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(3354)) {
                            // Stop command from mi scale received
                            if ((ListenerUtil.mutListener.listen(3350) ? (data[0] >= 0x03) : (ListenerUtil.mutListener.listen(3349) ? (data[0] <= 0x03) : (ListenerUtil.mutListener.listen(3348) ? (data[0] > 0x03) : (ListenerUtil.mutListener.listen(3347) ? (data[0] < 0x03) : (ListenerUtil.mutListener.listen(3346) ? (data[0] != 0x03) : (data[0] == 0x03))))))) {
                                if (!ListenerUtil.mutListener.listen(3351)) {
                                    // send stop command to mi scale
                                    writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, new byte[] { 0x03 });
                                }
                                // acknowledge that you received the last history data
                                int uniqueNumber = getUniqueNumber();
                                byte[] userIdentifier = new byte[] { (byte) 0x04, (byte) 0xFF, (byte) 0xFF, (byte) ((uniqueNumber & 0xFF00) >> 8), (byte) ((uniqueNumber & 0xFF) >> 0) };
                                if (!ListenerUtil.mutListener.listen(3352)) {
                                    writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, userIdentifier);
                                }
                                if (!ListenerUtil.mutListener.listen(3353)) {
                                    resumeMachineState();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3362)) {
                            if ((ListenerUtil.mutListener.listen(3359) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(3358) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(3357) ? (data.length > 20) : (ListenerUtil.mutListener.listen(3356) ? (data.length < 20) : (ListenerUtil.mutListener.listen(3355) ? (data.length != 20) : (data.length == 20))))))) {
                                final byte[] firstWeight = Arrays.copyOfRange(data, 0, 10);
                                final byte[] secondWeight = Arrays.copyOfRange(data, 10, 20);
                                if (!ListenerUtil.mutListener.listen(3360)) {
                                    parseBytes(firstWeight);
                                }
                                if (!ListenerUtil.mutListener.listen(3361)) {
                                    parseBytes(secondWeight);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3369)) {
                            if ((ListenerUtil.mutListener.listen(3367) ? (data.length >= 10) : (ListenerUtil.mutListener.listen(3366) ? (data.length <= 10) : (ListenerUtil.mutListener.listen(3365) ? (data.length > 10) : (ListenerUtil.mutListener.listen(3364) ? (data.length < 10) : (ListenerUtil.mutListener.listen(3363) ? (data.length != 10) : (data.length == 10))))))) {
                                if (!ListenerUtil.mutListener.listen(3368)) {
                                    parseBytes(data);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(3407)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(3400)) {
                        // read device time
                        readBytes(WEIGHT_MEASUREMENT_SERVICE, BluetoothGattUuid.CHARACTERISTIC_CURRENT_TIME);
                    }
                    break;
                case 1:
                    // Set on history weight measurement
                    byte[] magicBytes = new byte[] { (byte) 0x01, (byte) 0x96, (byte) 0x8a, (byte) 0xbd, (byte) 0x62 };
                    if (!ListenerUtil.mutListener.listen(3401)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, magicBytes);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(3402)) {
                        // set notification on for weight measurement history
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC);
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(3403)) {
                        // set notification on for weight measurement
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, BluetoothGattUuid.CHARACTERISTIC_WEIGHT_MEASUREMENT);
                    }
                    break;
                case 4:
                    // configure scale to get only last measurements
                    int uniqueNumber = getUniqueNumber();
                    byte[] userIdentifier = new byte[] { (byte) 0x01, (byte) 0xFF, (byte) 0xFF, (byte) ((uniqueNumber & 0xFF00) >> 8), (byte) ((uniqueNumber & 0xFF) >> 0) };
                    if (!ListenerUtil.mutListener.listen(3404)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, userIdentifier);
                    }
                    break;
                case 5:
                    if (!ListenerUtil.mutListener.listen(3405)) {
                        // invoke receiving history data
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_HISTORY_CHARACTERISTIC, new byte[] { 0x02 });
                    }
                    if (!ListenerUtil.mutListener.listen(3406)) {
                        stopMachineState();
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void parseBytes(byte[] weightBytes) {
        try {
            final byte ctrlByte = weightBytes[0];
            final boolean isWeightRemoved = isBitSet(ctrlByte, 7);
            final boolean isStabilized = isBitSet(ctrlByte, 5);
            final boolean isLBSUnit = isBitSet(ctrlByte, 0);
            final boolean isCattyUnit = isBitSet(ctrlByte, 4);
            if (!ListenerUtil.mutListener.listen(3424)) {
                // Only if the value is stabilized and the weight is *not* removed, the date is valid
                if ((ListenerUtil.mutListener.listen(3409) ? (isStabilized || !isWeightRemoved) : (isStabilized && !isWeightRemoved))) {
                    final int year = ((weightBytes[4] & 0xFF) << 8) | (weightBytes[3] & 0xFF);
                    final int month = (int) weightBytes[5];
                    final int day = (int) weightBytes[6];
                    final int hours = (int) weightBytes[7];
                    final int min = (int) weightBytes[8];
                    final int sec = (int) weightBytes[9];
                    float weight;
                    if ((ListenerUtil.mutListener.listen(3410) ? (isLBSUnit && isCattyUnit) : (isLBSUnit || isCattyUnit))) {
                        weight = (ListenerUtil.mutListener.listen(3418) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) % 100.0f) : (ListenerUtil.mutListener.listen(3417) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) * 100.0f) : (ListenerUtil.mutListener.listen(3416) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) - 100.0f) : (ListenerUtil.mutListener.listen(3415) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) + 100.0f) : ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) / 100.0f)))));
                    } else {
                        weight = (ListenerUtil.mutListener.listen(3414) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) % 200.0f) : (ListenerUtil.mutListener.listen(3413) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) * 200.0f) : (ListenerUtil.mutListener.listen(3412) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) - 200.0f) : (ListenerUtil.mutListener.listen(3411) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) + 200.0f) : ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[1] & 0xFF)) / 200.0f)))));
                    }
                    String date_string = year + "/" + month + "/" + day + "/" + hours + "/" + min;
                    Date date_time = new SimpleDateFormat("yyyy/MM/dd/HH/mm").parse(date_string);
                    if (!ListenerUtil.mutListener.listen(3423)) {
                        // Is the year plausible? Check if the year is in the range of 20 years...
                        if (validateDate(date_time, 20)) {
                            final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                            ScaleMeasurement scaleBtData = new ScaleMeasurement();
                            if (!ListenerUtil.mutListener.listen(3420)) {
                                scaleBtData.setWeight(Converters.toKilogram(weight, selectedUser.getScaleUnit()));
                            }
                            if (!ListenerUtil.mutListener.listen(3421)) {
                                scaleBtData.setDateTime(date_time);
                            }
                            if (!ListenerUtil.mutListener.listen(3422)) {
                                addScaleMeasurement(scaleBtData);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3419)) {
                                Timber.e("Invalid Mi scale weight year %d", year);
                            }
                        }
                    }
                }
            }
        } catch (ParseException e) {
            if (!ListenerUtil.mutListener.listen(3408)) {
                setBluetoothStatus(UNEXPECTED_ERROR, "Error while decoding bluetooth date string (" + e.getMessage() + ")");
            }
        }
    }

    private boolean validateDate(Date weightDate, int range) {
        Calendar currentDatePos = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(3425)) {
            currentDatePos.add(Calendar.YEAR, range);
        }
        Calendar currentDateNeg = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(3426)) {
            currentDateNeg.add(Calendar.YEAR, -range);
        }
        if (!ListenerUtil.mutListener.listen(3428)) {
            if ((ListenerUtil.mutListener.listen(3427) ? (weightDate.before(currentDatePos.getTime()) || weightDate.after(currentDateNeg.getTime())) : (weightDate.before(currentDatePos.getTime()) && weightDate.after(currentDateNeg.getTime())))) {
                return true;
            }
        }
        return false;
    }

    private int getUniqueNumber() {
        int uniqueNumber;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        uniqueNumber = prefs.getInt("uniqueNumber", 0x00);
        if ((ListenerUtil.mutListener.listen(3433) ? (uniqueNumber >= 0x00) : (ListenerUtil.mutListener.listen(3432) ? (uniqueNumber <= 0x00) : (ListenerUtil.mutListener.listen(3431) ? (uniqueNumber > 0x00) : (ListenerUtil.mutListener.listen(3430) ? (uniqueNumber < 0x00) : (ListenerUtil.mutListener.listen(3429) ? (uniqueNumber != 0x00) : (uniqueNumber == 0x00))))))) {
            Random r = new Random();
            uniqueNumber = (ListenerUtil.mutListener.listen(3445) ? (r.nextInt((ListenerUtil.mutListener.listen(3441) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) + 1)))))) % 100) : (ListenerUtil.mutListener.listen(3444) ? (r.nextInt((ListenerUtil.mutListener.listen(3441) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) + 1)))))) / 100) : (ListenerUtil.mutListener.listen(3443) ? (r.nextInt((ListenerUtil.mutListener.listen(3441) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) + 1)))))) * 100) : (ListenerUtil.mutListener.listen(3442) ? (r.nextInt((ListenerUtil.mutListener.listen(3441) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) + 1)))))) - 100) : (r.nextInt((ListenerUtil.mutListener.listen(3441) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(3437) ? (65535 % 100) : (ListenerUtil.mutListener.listen(3436) ? (65535 / 100) : (ListenerUtil.mutListener.listen(3435) ? (65535 * 100) : (ListenerUtil.mutListener.listen(3434) ? (65535 + 100) : (65535 - 100))))) + 1)))))) + 100)))));
            if (!ListenerUtil.mutListener.listen(3446)) {
                prefs.edit().putInt("uniqueNumber", uniqueNumber).apply();
            }
        }
        int userId = OpenScale.getInstance().getSelectedScaleUserId();
        return (ListenerUtil.mutListener.listen(3450) ? (uniqueNumber % userId) : (ListenerUtil.mutListener.listen(3449) ? (uniqueNumber / userId) : (ListenerUtil.mutListener.listen(3448) ? (uniqueNumber * userId) : (ListenerUtil.mutListener.listen(3447) ? (uniqueNumber - userId) : (uniqueNumber + userId)))));
    }
}
