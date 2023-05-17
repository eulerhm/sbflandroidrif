/* Copyright (C) 2017  olie.xdev <olie.xdev@googlemail.com>
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
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.util.Arrays;
import java.util.UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothExcelvanCF36xBLE extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0xfff0);

    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff1);

    private final UUID WEIGHT_CUSTOM0_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff4);

    private byte[] receivedData = new byte[] {};

    public BluetoothExcelvanCF36xBLE(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Excelvan CF36xBLE";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(2560)) {
            switch(stepNr) {
                case 0:
                    final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                    byte userId = (byte) 0x01;
                    byte sex = selectedUser.getGender().isMale() ? (byte) 0x01 : (byte) 0x00;
                    // 0x00 = ordinary, 0x01 = amateur, 0x02 = professional
                    byte exerciseLevel = (byte) 0x01;
                    if (!ListenerUtil.mutListener.listen(2544)) {
                        switch(selectedUser.getActivityLevel()) {
                            case SEDENTARY:
                            case MILD:
                                if (!ListenerUtil.mutListener.listen(2541)) {
                                    exerciseLevel = (byte) 0x00;
                                }
                                break;
                            case MODERATE:
                                if (!ListenerUtil.mutListener.listen(2542)) {
                                    exerciseLevel = (byte) 0x01;
                                }
                                break;
                            case HEAVY:
                            case EXTREME:
                                if (!ListenerUtil.mutListener.listen(2543)) {
                                    exerciseLevel = (byte) 0x02;
                                }
                                break;
                        }
                    }
                    byte height = (byte) selectedUser.getBodyHeight();
                    byte age = (byte) selectedUser.getAge();
                    // kg
                    byte unit = 0x01;
                    if (!ListenerUtil.mutListener.listen(2547)) {
                        switch(selectedUser.getScaleUnit()) {
                            case LB:
                                if (!ListenerUtil.mutListener.listen(2545)) {
                                    unit = 0x02;
                                }
                                break;
                            case ST:
                                if (!ListenerUtil.mutListener.listen(2546)) {
                                    unit = 0x04;
                                }
                                break;
                        }
                    }
                    byte[] configBytes = { (byte) 0xfe, userId, sex, exerciseLevel, height, age, unit, (byte) 0x00 };
                    if (!ListenerUtil.mutListener.listen(2556)) {
                        configBytes[(ListenerUtil.mutListener.listen(2551) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2550) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2549) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2548) ? (configBytes.length + 1) : (configBytes.length - 1)))))] = xorChecksum(configBytes, 1, (ListenerUtil.mutListener.listen(2555) ? (configBytes.length % 2) : (ListenerUtil.mutListener.listen(2554) ? (configBytes.length / 2) : (ListenerUtil.mutListener.listen(2553) ? (configBytes.length * 2) : (ListenerUtil.mutListener.listen(2552) ? (configBytes.length + 2) : (configBytes.length - 2))))));
                    }
                    if (!ListenerUtil.mutListener.listen(2557)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC, configBytes);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(2558)) {
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_CUSTOM0_CHARACTERISTIC);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(2559)) {
                        sendMessage(R.string.info_step_on_scale, 0);
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
        final byte[] data = value;
        if (!ListenerUtil.mutListener.listen(2588)) {
            if ((ListenerUtil.mutListener.listen(2566) ? (data != null || (ListenerUtil.mutListener.listen(2565) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2564) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2563) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2562) ? (data.length != 0) : (ListenerUtil.mutListener.listen(2561) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(2565) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2564) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2563) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2562) ? (data.length != 0) : (ListenerUtil.mutListener.listen(2561) ? (data.length == 0) : (data.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2587)) {
                    // to support those variants.
                    if ((ListenerUtil.mutListener.listen(2583) ? (((ListenerUtil.mutListener.listen(2577) ? ((ListenerUtil.mutListener.listen(2571) ? (data.length <= 16) : (ListenerUtil.mutListener.listen(2570) ? (data.length > 16) : (ListenerUtil.mutListener.listen(2569) ? (data.length < 16) : (ListenerUtil.mutListener.listen(2568) ? (data.length != 16) : (ListenerUtil.mutListener.listen(2567) ? (data.length == 16) : (data.length >= 16)))))) || (ListenerUtil.mutListener.listen(2576) ? (data.length >= 17) : (ListenerUtil.mutListener.listen(2575) ? (data.length > 17) : (ListenerUtil.mutListener.listen(2574) ? (data.length < 17) : (ListenerUtil.mutListener.listen(2573) ? (data.length != 17) : (ListenerUtil.mutListener.listen(2572) ? (data.length == 17) : (data.length <= 17))))))) : ((ListenerUtil.mutListener.listen(2571) ? (data.length <= 16) : (ListenerUtil.mutListener.listen(2570) ? (data.length > 16) : (ListenerUtil.mutListener.listen(2569) ? (data.length < 16) : (ListenerUtil.mutListener.listen(2568) ? (data.length != 16) : (ListenerUtil.mutListener.listen(2567) ? (data.length == 16) : (data.length >= 16)))))) && (ListenerUtil.mutListener.listen(2576) ? (data.length >= 17) : (ListenerUtil.mutListener.listen(2575) ? (data.length > 17) : (ListenerUtil.mutListener.listen(2574) ? (data.length < 17) : (ListenerUtil.mutListener.listen(2573) ? (data.length != 17) : (ListenerUtil.mutListener.listen(2572) ? (data.length == 17) : (data.length <= 17))))))))) || (ListenerUtil.mutListener.listen(2582) ? (data[0] >= (byte) 0xcf) : (ListenerUtil.mutListener.listen(2581) ? (data[0] <= (byte) 0xcf) : (ListenerUtil.mutListener.listen(2580) ? (data[0] > (byte) 0xcf) : (ListenerUtil.mutListener.listen(2579) ? (data[0] < (byte) 0xcf) : (ListenerUtil.mutListener.listen(2578) ? (data[0] != (byte) 0xcf) : (data[0] == (byte) 0xcf))))))) : (((ListenerUtil.mutListener.listen(2577) ? ((ListenerUtil.mutListener.listen(2571) ? (data.length <= 16) : (ListenerUtil.mutListener.listen(2570) ? (data.length > 16) : (ListenerUtil.mutListener.listen(2569) ? (data.length < 16) : (ListenerUtil.mutListener.listen(2568) ? (data.length != 16) : (ListenerUtil.mutListener.listen(2567) ? (data.length == 16) : (data.length >= 16)))))) || (ListenerUtil.mutListener.listen(2576) ? (data.length >= 17) : (ListenerUtil.mutListener.listen(2575) ? (data.length > 17) : (ListenerUtil.mutListener.listen(2574) ? (data.length < 17) : (ListenerUtil.mutListener.listen(2573) ? (data.length != 17) : (ListenerUtil.mutListener.listen(2572) ? (data.length == 17) : (data.length <= 17))))))) : ((ListenerUtil.mutListener.listen(2571) ? (data.length <= 16) : (ListenerUtil.mutListener.listen(2570) ? (data.length > 16) : (ListenerUtil.mutListener.listen(2569) ? (data.length < 16) : (ListenerUtil.mutListener.listen(2568) ? (data.length != 16) : (ListenerUtil.mutListener.listen(2567) ? (data.length == 16) : (data.length >= 16)))))) && (ListenerUtil.mutListener.listen(2576) ? (data.length >= 17) : (ListenerUtil.mutListener.listen(2575) ? (data.length > 17) : (ListenerUtil.mutListener.listen(2574) ? (data.length < 17) : (ListenerUtil.mutListener.listen(2573) ? (data.length != 17) : (ListenerUtil.mutListener.listen(2572) ? (data.length == 17) : (data.length <= 17))))))))) && (ListenerUtil.mutListener.listen(2582) ? (data[0] >= (byte) 0xcf) : (ListenerUtil.mutListener.listen(2581) ? (data[0] <= (byte) 0xcf) : (ListenerUtil.mutListener.listen(2580) ? (data[0] > (byte) 0xcf) : (ListenerUtil.mutListener.listen(2579) ? (data[0] < (byte) 0xcf) : (ListenerUtil.mutListener.listen(2578) ? (data[0] != (byte) 0xcf) : (data[0] == (byte) 0xcf))))))))) {
                        if (!ListenerUtil.mutListener.listen(2586)) {
                            if (!Arrays.equals(data, receivedData)) {
                                if (!ListenerUtil.mutListener.listen(2584)) {
                                    // accepts only one data of the same content
                                    receivedData = data;
                                }
                                if (!ListenerUtil.mutListener.listen(2585)) {
                                    parseBytes(data);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void parseBytes(byte[] weightBytes) {
        float weight = (ListenerUtil.mutListener.listen(2592) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) % 10.0f) : (ListenerUtil.mutListener.listen(2591) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) * 10.0f) : (ListenerUtil.mutListener.listen(2590) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) - 10.0f) : (ListenerUtil.mutListener.listen(2589) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 4) / 10.0f)))));
        float fat = (ListenerUtil.mutListener.listen(2596) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) % 10.0f) : (ListenerUtil.mutListener.listen(2595) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) * 10.0f) : (ListenerUtil.mutListener.listen(2594) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) - 10.0f) : (ListenerUtil.mutListener.listen(2593) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 6) / 10.0f)))));
        float bone = (ListenerUtil.mutListener.listen(2600) ? ((weightBytes[8] & 0xFF) % 10.0f) : (ListenerUtil.mutListener.listen(2599) ? ((weightBytes[8] & 0xFF) * 10.0f) : (ListenerUtil.mutListener.listen(2598) ? ((weightBytes[8] & 0xFF) - 10.0f) : (ListenerUtil.mutListener.listen(2597) ? ((weightBytes[8] & 0xFF) + 10.0f) : ((weightBytes[8] & 0xFF) / 10.0f)))));
        float muscle = (ListenerUtil.mutListener.listen(2604) ? (Converters.fromUnsignedInt16Be(weightBytes, 9) % 10.0f) : (ListenerUtil.mutListener.listen(2603) ? (Converters.fromUnsignedInt16Be(weightBytes, 9) * 10.0f) : (ListenerUtil.mutListener.listen(2602) ? (Converters.fromUnsignedInt16Be(weightBytes, 9) - 10.0f) : (ListenerUtil.mutListener.listen(2601) ? (Converters.fromUnsignedInt16Be(weightBytes, 9) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 9) / 10.0f)))));
        float visceralFat = weightBytes[11] & 0xFF;
        float water = (ListenerUtil.mutListener.listen(2608) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) % 10.0f) : (ListenerUtil.mutListener.listen(2607) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) * 10.0f) : (ListenerUtil.mutListener.listen(2606) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) - 10.0f) : (ListenerUtil.mutListener.listen(2605) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 12) / 10.0f)))));
        float bmr = Converters.fromUnsignedInt16Be(weightBytes, 14);
        ScaleMeasurement scaleBtData = new ScaleMeasurement();
        final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
        if (!ListenerUtil.mutListener.listen(2609)) {
            scaleBtData.setWeight(Converters.toKilogram(weight, selectedUser.getScaleUnit()));
        }
        if (!ListenerUtil.mutListener.listen(2610)) {
            scaleBtData.setFat(fat);
        }
        if (!ListenerUtil.mutListener.listen(2611)) {
            scaleBtData.setMuscle(muscle);
        }
        if (!ListenerUtil.mutListener.listen(2612)) {
            scaleBtData.setWater(water);
        }
        if (!ListenerUtil.mutListener.listen(2613)) {
            scaleBtData.setBone(bone);
        }
        if (!ListenerUtil.mutListener.listen(2614)) {
            scaleBtData.setVisceralFat(visceralFat);
        }
        if (!ListenerUtil.mutListener.listen(2615)) {
            addScaleMeasurement(scaleBtData);
        }
    }
}
