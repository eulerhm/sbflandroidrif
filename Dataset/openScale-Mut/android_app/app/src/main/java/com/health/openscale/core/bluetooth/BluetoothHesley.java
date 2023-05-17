/* Copyright (C) 2018  olie.xdev <olie.xdev@googlemail.com>
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
import com.health.openscale.core.datatypes.ScaleMeasurement;
import java.util.Date;
import java.util.UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothHesley extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0xfff0);

    // read, notify
    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff4);

    // write only
    private final UUID CMD_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff1);

    public BluetoothHesley(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Hesley scale";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(2730)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(2727)) {
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 1:
                    byte[] magicBytes = { (byte) 0xa5, (byte) 0x01, (byte) 0x2c, (byte) 0xab, (byte) 0x50, (byte) 0x5a, (byte) 0x29 };
                    if (!ListenerUtil.mutListener.listen(2728)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, magicBytes);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(2729)) {
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
        if (!ListenerUtil.mutListener.listen(2744)) {
            if ((ListenerUtil.mutListener.listen(2736) ? (data != null || (ListenerUtil.mutListener.listen(2735) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2734) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2733) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2732) ? (data.length != 0) : (ListenerUtil.mutListener.listen(2731) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(2735) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2734) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2733) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2732) ? (data.length != 0) : (ListenerUtil.mutListener.listen(2731) ? (data.length == 0) : (data.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2743)) {
                    if ((ListenerUtil.mutListener.listen(2741) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(2740) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(2739) ? (data.length > 20) : (ListenerUtil.mutListener.listen(2738) ? (data.length < 20) : (ListenerUtil.mutListener.listen(2737) ? (data.length != 20) : (data.length == 20))))))) {
                        if (!ListenerUtil.mutListener.listen(2742)) {
                            parseBytes(data);
                        }
                    }
                }
            }
        }
    }

    private void parseBytes(byte[] weightBytes) {
        // 10 ~ 99
        int bodyage = (int) (weightBytes[17]);
        // kg
        float weight = (ListenerUtil.mutListener.listen(2748) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[3] & 0xFF)) % 100.0f) : (ListenerUtil.mutListener.listen(2747) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[3] & 0xFF)) * 100.0f) : (ListenerUtil.mutListener.listen(2746) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[3] & 0xFF)) - 100.0f) : (ListenerUtil.mutListener.listen(2745) ? ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[3] & 0xFF)) + 100.0f) : ((float) (((weightBytes[2] & 0xFF) << 8) | (weightBytes[3] & 0xFF)) / 100.0f)))));
        // %
        float fat = (ListenerUtil.mutListener.listen(2752) ? ((float) (((weightBytes[4] & 0xFF) << 8) | (weightBytes[5] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2751) ? ((float) (((weightBytes[4] & 0xFF) << 8) | (weightBytes[5] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2750) ? ((float) (((weightBytes[4] & 0xFF) << 8) | (weightBytes[5] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2749) ? ((float) (((weightBytes[4] & 0xFF) << 8) | (weightBytes[5] & 0xFF)) + 10.0f) : ((float) (((weightBytes[4] & 0xFF) << 8) | (weightBytes[5] & 0xFF)) / 10.0f)))));
        // %
        float water = (ListenerUtil.mutListener.listen(2756) ? ((float) (((weightBytes[8] & 0xFF) << 8) | (weightBytes[9] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2755) ? ((float) (((weightBytes[8] & 0xFF) << 8) | (weightBytes[9] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2754) ? ((float) (((weightBytes[8] & 0xFF) << 8) | (weightBytes[9] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2753) ? ((float) (((weightBytes[8] & 0xFF) << 8) | (weightBytes[9] & 0xFF)) + 10.0f) : ((float) (((weightBytes[8] & 0xFF) << 8) | (weightBytes[9] & 0xFF)) / 10.0f)))));
        // %
        float muscle = (ListenerUtil.mutListener.listen(2760) ? ((float) (((weightBytes[10] & 0xFF) << 8) | (weightBytes[11] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2759) ? ((float) (((weightBytes[10] & 0xFF) << 8) | (weightBytes[11] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2758) ? ((float) (((weightBytes[10] & 0xFF) << 8) | (weightBytes[11] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2757) ? ((float) (((weightBytes[10] & 0xFF) << 8) | (weightBytes[11] & 0xFF)) + 10.0f) : ((float) (((weightBytes[10] & 0xFF) << 8) | (weightBytes[11] & 0xFF)) / 10.0f)))));
        // %
        float bone = (ListenerUtil.mutListener.listen(2764) ? ((float) (((weightBytes[12] & 0xFF) << 8) | (weightBytes[13] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2763) ? ((float) (((weightBytes[12] & 0xFF) << 8) | (weightBytes[13] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2762) ? ((float) (((weightBytes[12] & 0xFF) << 8) | (weightBytes[13] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2761) ? ((float) (((weightBytes[12] & 0xFF) << 8) | (weightBytes[13] & 0xFF)) + 10.0f) : ((float) (((weightBytes[12] & 0xFF) << 8) | (weightBytes[13] & 0xFF)) / 10.0f)))));
        // kcal
        float calorie = (float) (((weightBytes[14] & 0xFF) << 8) | (weightBytes[15] & 0xFF));
        ScaleMeasurement scaleBtData = new ScaleMeasurement();
        if (!ListenerUtil.mutListener.listen(2765)) {
            scaleBtData.setWeight(weight);
        }
        if (!ListenerUtil.mutListener.listen(2766)) {
            scaleBtData.setFat(fat);
        }
        if (!ListenerUtil.mutListener.listen(2767)) {
            scaleBtData.setMuscle(muscle);
        }
        if (!ListenerUtil.mutListener.listen(2768)) {
            scaleBtData.setWater(water);
        }
        if (!ListenerUtil.mutListener.listen(2769)) {
            scaleBtData.setBone(bone);
        }
        if (!ListenerUtil.mutListener.listen(2770)) {
            scaleBtData.setDateTime(new Date());
        }
        if (!ListenerUtil.mutListener.listen(2771)) {
            addScaleMeasurement(scaleBtData);
        }
    }
}
