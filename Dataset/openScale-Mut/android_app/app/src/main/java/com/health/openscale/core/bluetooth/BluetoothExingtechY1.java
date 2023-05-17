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
import java.util.Date;
import java.util.UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothExingtechY1 extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = UUID.fromString("f433bd80-75b8-11e2-97d9-0002a5d5c51b");

    // read, notify
    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = UUID.fromString("1a2ea400-75b9-11e2-be05-0002a5d5c51b");

    // write only
    private final UUID CMD_MEASUREMENT_CHARACTERISTIC = UUID.fromString("29f11080-75b9-11e2-8bf6-0002a5d5c51b");

    public BluetoothExingtechY1(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Exingtech Y1";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(2619)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(2616)) {
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 1:
                    final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                    // 00 - male; 01 - female
                    byte gender = selectedUser.getGender().isMale() ? (byte) 0x00 : (byte) 0x01;
                    // cm
                    byte height = (byte) (((int) selectedUser.getBodyHeight()) & 0xff);
                    byte age = (byte) (selectedUser.getAge() & 0xff);
                    int userId = selectedUser.getId();
                    byte[] cmdByte = { (byte) 0x10, (byte) userId, gender, age, height };
                    if (!ListenerUtil.mutListener.listen(2617)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, cmdByte);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(2618)) {
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
        if (!ListenerUtil.mutListener.listen(2633)) {
            // either 0x00 (user info) or 0xff (fat, water, etc.)
            if ((ListenerUtil.mutListener.listen(2631) ? ((ListenerUtil.mutListener.listen(2625) ? (data != null || (ListenerUtil.mutListener.listen(2624) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(2623) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(2622) ? (data.length > 20) : (ListenerUtil.mutListener.listen(2621) ? (data.length < 20) : (ListenerUtil.mutListener.listen(2620) ? (data.length != 20) : (data.length == 20))))))) : (data != null && (ListenerUtil.mutListener.listen(2624) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(2623) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(2622) ? (data.length > 20) : (ListenerUtil.mutListener.listen(2621) ? (data.length < 20) : (ListenerUtil.mutListener.listen(2620) ? (data.length != 20) : (data.length == 20)))))))) || (ListenerUtil.mutListener.listen(2630) ? (data[6] >= (byte) 0xff) : (ListenerUtil.mutListener.listen(2629) ? (data[6] <= (byte) 0xff) : (ListenerUtil.mutListener.listen(2628) ? (data[6] > (byte) 0xff) : (ListenerUtil.mutListener.listen(2627) ? (data[6] < (byte) 0xff) : (ListenerUtil.mutListener.listen(2626) ? (data[6] == (byte) 0xff) : (data[6] != (byte) 0xff))))))) : ((ListenerUtil.mutListener.listen(2625) ? (data != null || (ListenerUtil.mutListener.listen(2624) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(2623) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(2622) ? (data.length > 20) : (ListenerUtil.mutListener.listen(2621) ? (data.length < 20) : (ListenerUtil.mutListener.listen(2620) ? (data.length != 20) : (data.length == 20))))))) : (data != null && (ListenerUtil.mutListener.listen(2624) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(2623) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(2622) ? (data.length > 20) : (ListenerUtil.mutListener.listen(2621) ? (data.length < 20) : (ListenerUtil.mutListener.listen(2620) ? (data.length != 20) : (data.length == 20)))))))) && (ListenerUtil.mutListener.listen(2630) ? (data[6] >= (byte) 0xff) : (ListenerUtil.mutListener.listen(2629) ? (data[6] <= (byte) 0xff) : (ListenerUtil.mutListener.listen(2628) ? (data[6] > (byte) 0xff) : (ListenerUtil.mutListener.listen(2627) ? (data[6] < (byte) 0xff) : (ListenerUtil.mutListener.listen(2626) ? (data[6] == (byte) 0xff) : (data[6] != (byte) 0xff))))))))) {
                if (!ListenerUtil.mutListener.listen(2632)) {
                    parseBytes(data);
                }
            }
        }
    }

    private void parseBytes(byte[] weightBytes) {
        int userId = weightBytes[0] & 0xFF;
        // 0x00 male; 0x01 female
        int gender = weightBytes[1] & 0xFF;
        // 10 ~ 99
        int age = weightBytes[2] & 0xFF;
        // 0 ~ 255
        int height = weightBytes[3] & 0xFF;
        // kg
        float weight = (ListenerUtil.mutListener.listen(2637) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) % 10.0f) : (ListenerUtil.mutListener.listen(2636) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) * 10.0f) : (ListenerUtil.mutListener.listen(2635) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) - 10.0f) : (ListenerUtil.mutListener.listen(2634) ? (Converters.fromUnsignedInt16Be(weightBytes, 4) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 4) / 10.0f)))));
        // %
        float fat = (ListenerUtil.mutListener.listen(2641) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) % 10.0f) : (ListenerUtil.mutListener.listen(2640) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) * 10.0f) : (ListenerUtil.mutListener.listen(2639) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) - 10.0f) : (ListenerUtil.mutListener.listen(2638) ? (Converters.fromUnsignedInt16Be(weightBytes, 6) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 6) / 10.0f)))));
        // %
        float water = (ListenerUtil.mutListener.listen(2645) ? (Converters.fromUnsignedInt16Be(weightBytes, 8) % 10.0f) : (ListenerUtil.mutListener.listen(2644) ? (Converters.fromUnsignedInt16Be(weightBytes, 8) * 10.0f) : (ListenerUtil.mutListener.listen(2643) ? (Converters.fromUnsignedInt16Be(weightBytes, 8) - 10.0f) : (ListenerUtil.mutListener.listen(2642) ? (Converters.fromUnsignedInt16Be(weightBytes, 8) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 8) / 10.0f)))));
        // kg
        float bone = (ListenerUtil.mutListener.listen(2649) ? (Converters.fromUnsignedInt16Be(weightBytes, 10) % 10.0f) : (ListenerUtil.mutListener.listen(2648) ? (Converters.fromUnsignedInt16Be(weightBytes, 10) * 10.0f) : (ListenerUtil.mutListener.listen(2647) ? (Converters.fromUnsignedInt16Be(weightBytes, 10) - 10.0f) : (ListenerUtil.mutListener.listen(2646) ? (Converters.fromUnsignedInt16Be(weightBytes, 10) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 10) / 10.0f)))));
        // %
        float muscle = (ListenerUtil.mutListener.listen(2653) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) % 10.0f) : (ListenerUtil.mutListener.listen(2652) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) * 10.0f) : (ListenerUtil.mutListener.listen(2651) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) - 10.0f) : (ListenerUtil.mutListener.listen(2650) ? (Converters.fromUnsignedInt16Be(weightBytes, 12) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 12) / 10.0f)))));
        // index
        float visc_fat = weightBytes[14] & 0xFF;
        float calorie = Converters.fromUnsignedInt16Be(weightBytes, 15);
        float bmi = (ListenerUtil.mutListener.listen(2657) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) % 10.0f) : (ListenerUtil.mutListener.listen(2656) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) * 10.0f) : (ListenerUtil.mutListener.listen(2655) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) - 10.0f) : (ListenerUtil.mutListener.listen(2654) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) + 10.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 17) / 10.0f)))));
        ScaleMeasurement scaleBtData = new ScaleMeasurement();
        final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
        if (!ListenerUtil.mutListener.listen(2658)) {
            scaleBtData.setWeight(weight);
        }
        if (!ListenerUtil.mutListener.listen(2659)) {
            scaleBtData.setFat(fat);
        }
        if (!ListenerUtil.mutListener.listen(2660)) {
            scaleBtData.setMuscle(muscle);
        }
        if (!ListenerUtil.mutListener.listen(2661)) {
            scaleBtData.setWater(water);
        }
        if (!ListenerUtil.mutListener.listen(2662)) {
            scaleBtData.setBone(bone);
        }
        if (!ListenerUtil.mutListener.listen(2663)) {
            scaleBtData.setVisceralFat(visc_fat);
        }
        if (!ListenerUtil.mutListener.listen(2664)) {
            scaleBtData.setDateTime(new Date());
        }
        if (!ListenerUtil.mutListener.listen(2665)) {
            addScaleMeasurement(scaleBtData);
        }
    }
}
