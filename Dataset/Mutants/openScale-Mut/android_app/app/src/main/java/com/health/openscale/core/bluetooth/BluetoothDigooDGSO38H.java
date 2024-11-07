/* Copyright (C) 2017  Murgi <fabian@murgi.de>
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
import java.util.Date;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothDigooDGSO38H extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0xfff0);

    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff1);

    private final UUID EXTRA_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff2);

    public BluetoothDigooDGSO38H(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Digoo DG-SO38H";
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        final byte[] data = value;
        if (!ListenerUtil.mutListener.listen(2479)) {
            if ((ListenerUtil.mutListener.listen(2471) ? (data != null || (ListenerUtil.mutListener.listen(2470) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2469) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2468) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2467) ? (data.length != 0) : (ListenerUtil.mutListener.listen(2466) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(2470) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2469) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2468) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2467) ? (data.length != 0) : (ListenerUtil.mutListener.listen(2466) ? (data.length == 0) : (data.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2478)) {
                    if ((ListenerUtil.mutListener.listen(2476) ? (data.length >= 20) : (ListenerUtil.mutListener.listen(2475) ? (data.length <= 20) : (ListenerUtil.mutListener.listen(2474) ? (data.length > 20) : (ListenerUtil.mutListener.listen(2473) ? (data.length < 20) : (ListenerUtil.mutListener.listen(2472) ? (data.length != 20) : (data.length == 20))))))) {
                        if (!ListenerUtil.mutListener.listen(2477)) {
                            parseBytes(data);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(2482)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(2480)) {
                        // Tell device to send us weight measurements
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(2481)) {
                        sendMessage(R.string.info_step_on_scale, 0);
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void parseBytes(byte[] weightBytes) {
        float weight, fat, water, muscle, boneWeight, visceralFat;
        final byte ctrlByte = weightBytes[5];
        final boolean allValues = isBitSet(ctrlByte, 1);
        final boolean weightStabilized = isBitSet(ctrlByte, 0);
        final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
        if (weightStabilized) {
            // The weight is stabilized, now we want to measure all available values
            byte gender = selectedUser.getGender().isMale() ? (byte) 0x00 : (byte) 0x01;
            byte height = (byte) (((int) selectedUser.getBodyHeight()) & 0xFF);
            byte age = (byte) (selectedUser.getAge() & 0xff);
            // kg
            byte unit = 0x01;
            if (!ListenerUtil.mutListener.listen(2527)) {
                switch(selectedUser.getScaleUnit()) {
                    case LB:
                        if (!ListenerUtil.mutListener.listen(2525)) {
                            unit = 0x02;
                        }
                        break;
                    case ST:
                        if (!ListenerUtil.mutListener.listen(2526)) {
                            unit = 0x8;
                        }
                        break;
                }
            }
            byte[] configBytes = new byte[] { (byte) 0x09, (byte) 0x10, (byte) 0x12, (byte) 0x11, (byte) 0x0d, (byte) 0x01, height, age, gender, unit, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
            // Write checksum is sum of all bytes % 256
            int checksum = 0x00;
            if (!ListenerUtil.mutListener.listen(2538)) {
                {
                    long _loopCounter29 = 0;
                    for (int i = 3; (ListenerUtil.mutListener.listen(2537) ? (i >= (ListenerUtil.mutListener.listen(2532) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2531) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2530) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2529) ? (configBytes.length + 1) : (configBytes.length - 1)))))) : (ListenerUtil.mutListener.listen(2536) ? (i <= (ListenerUtil.mutListener.listen(2532) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2531) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2530) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2529) ? (configBytes.length + 1) : (configBytes.length - 1)))))) : (ListenerUtil.mutListener.listen(2535) ? (i > (ListenerUtil.mutListener.listen(2532) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2531) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2530) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2529) ? (configBytes.length + 1) : (configBytes.length - 1)))))) : (ListenerUtil.mutListener.listen(2534) ? (i != (ListenerUtil.mutListener.listen(2532) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2531) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2530) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2529) ? (configBytes.length + 1) : (configBytes.length - 1)))))) : (ListenerUtil.mutListener.listen(2533) ? (i == (ListenerUtil.mutListener.listen(2532) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2531) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2530) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2529) ? (configBytes.length + 1) : (configBytes.length - 1)))))) : (i < (ListenerUtil.mutListener.listen(2532) ? (configBytes.length % 1) : (ListenerUtil.mutListener.listen(2531) ? (configBytes.length / 1) : (ListenerUtil.mutListener.listen(2530) ? (configBytes.length * 1) : (ListenerUtil.mutListener.listen(2529) ? (configBytes.length + 1) : (configBytes.length - 1))))))))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                        if (!ListenerUtil.mutListener.listen(2528)) {
                            checksum += configBytes[i];
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2539)) {
                configBytes[15] = (byte) (checksum & 0xFF);
            }
            if (!ListenerUtil.mutListener.listen(2540)) {
                writeBytes(WEIGHT_MEASUREMENT_SERVICE, EXTRA_MEASUREMENT_CHARACTERISTIC, configBytes);
            }
        } else if (allValues) {
            ScaleMeasurement scaleBtData = new ScaleMeasurement();
            weight = (ListenerUtil.mutListener.listen(2486) ? ((float) (((weightBytes[3] & 0xFF) << 8) | (weightBytes[4] & 0xFF)) % 100.0f) : (ListenerUtil.mutListener.listen(2485) ? ((float) (((weightBytes[3] & 0xFF) << 8) | (weightBytes[4] & 0xFF)) * 100.0f) : (ListenerUtil.mutListener.listen(2484) ? ((float) (((weightBytes[3] & 0xFF) << 8) | (weightBytes[4] & 0xFF)) - 100.0f) : (ListenerUtil.mutListener.listen(2483) ? ((float) (((weightBytes[3] & 0xFF) << 8) | (weightBytes[4] & 0xFF)) + 100.0f) : ((float) (((weightBytes[3] & 0xFF) << 8) | (weightBytes[4] & 0xFF)) / 100.0f)))));
            fat = (ListenerUtil.mutListener.listen(2490) ? ((float) (((weightBytes[6] & 0xFF) << 8) | (weightBytes[7] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2489) ? ((float) (((weightBytes[6] & 0xFF) << 8) | (weightBytes[7] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2488) ? ((float) (((weightBytes[6] & 0xFF) << 8) | (weightBytes[7] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2487) ? ((float) (((weightBytes[6] & 0xFF) << 8) | (weightBytes[7] & 0xFF)) + 10.0f) : ((float) (((weightBytes[6] & 0xFF) << 8) | (weightBytes[7] & 0xFF)) / 10.0f)))));
            if ((ListenerUtil.mutListener.listen(2499) ? (Math.abs((ListenerUtil.mutListener.listen(2494) ? (fat % 0.0) : (ListenerUtil.mutListener.listen(2493) ? (fat / 0.0) : (ListenerUtil.mutListener.listen(2492) ? (fat * 0.0) : (ListenerUtil.mutListener.listen(2491) ? (fat + 0.0) : (fat - 0.0)))))) >= 0.00001) : (ListenerUtil.mutListener.listen(2498) ? (Math.abs((ListenerUtil.mutListener.listen(2494) ? (fat % 0.0) : (ListenerUtil.mutListener.listen(2493) ? (fat / 0.0) : (ListenerUtil.mutListener.listen(2492) ? (fat * 0.0) : (ListenerUtil.mutListener.listen(2491) ? (fat + 0.0) : (fat - 0.0)))))) <= 0.00001) : (ListenerUtil.mutListener.listen(2497) ? (Math.abs((ListenerUtil.mutListener.listen(2494) ? (fat % 0.0) : (ListenerUtil.mutListener.listen(2493) ? (fat / 0.0) : (ListenerUtil.mutListener.listen(2492) ? (fat * 0.0) : (ListenerUtil.mutListener.listen(2491) ? (fat + 0.0) : (fat - 0.0)))))) > 0.00001) : (ListenerUtil.mutListener.listen(2496) ? (Math.abs((ListenerUtil.mutListener.listen(2494) ? (fat % 0.0) : (ListenerUtil.mutListener.listen(2493) ? (fat / 0.0) : (ListenerUtil.mutListener.listen(2492) ? (fat * 0.0) : (ListenerUtil.mutListener.listen(2491) ? (fat + 0.0) : (fat - 0.0)))))) != 0.00001) : (ListenerUtil.mutListener.listen(2495) ? (Math.abs((ListenerUtil.mutListener.listen(2494) ? (fat % 0.0) : (ListenerUtil.mutListener.listen(2493) ? (fat / 0.0) : (ListenerUtil.mutListener.listen(2492) ? (fat * 0.0) : (ListenerUtil.mutListener.listen(2491) ? (fat + 0.0) : (fat - 0.0)))))) == 0.00001) : (Math.abs((ListenerUtil.mutListener.listen(2494) ? (fat % 0.0) : (ListenerUtil.mutListener.listen(2493) ? (fat / 0.0) : (ListenerUtil.mutListener.listen(2492) ? (fat * 0.0) : (ListenerUtil.mutListener.listen(2491) ? (fat + 0.0) : (fat - 0.0)))))) < 0.00001))))))) {
                if (!ListenerUtil.mutListener.listen(2522)) {
                    Timber.d("Scale signaled that measurement of all data " + "is done, but fat is still zero. Settling for just adding weight.");
                }
            } else {
                // subcutaneousFat = (float) (((weightBytes[8] & 0xFF) << 8) | (weightBytes[9] & 0xFF)) / 10.0f;
                visceralFat = (ListenerUtil.mutListener.listen(2503) ? ((float) (weightBytes[10] & 0xFF) % 10.0f) : (ListenerUtil.mutListener.listen(2502) ? ((float) (weightBytes[10] & 0xFF) * 10.0f) : (ListenerUtil.mutListener.listen(2501) ? ((float) (weightBytes[10] & 0xFF) - 10.0f) : (ListenerUtil.mutListener.listen(2500) ? ((float) (weightBytes[10] & 0xFF) + 10.0f) : ((float) (weightBytes[10] & 0xFF) / 10.0f)))));
                water = (ListenerUtil.mutListener.listen(2507) ? ((float) (((weightBytes[11] & 0xFF) << 8) | (weightBytes[12] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2506) ? ((float) (((weightBytes[11] & 0xFF) << 8) | (weightBytes[12] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2505) ? ((float) (((weightBytes[11] & 0xFF) << 8) | (weightBytes[12] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2504) ? ((float) (((weightBytes[11] & 0xFF) << 8) | (weightBytes[12] & 0xFF)) + 10.0f) : ((float) (((weightBytes[11] & 0xFF) << 8) | (weightBytes[12] & 0xFF)) / 10.0f)))));
                // biologicalAge = (float) (weightBytes[15] & 0xFF) + 1;
                muscle = (ListenerUtil.mutListener.listen(2511) ? ((float) (((weightBytes[16] & 0xFF) << 8) | (weightBytes[17] & 0xFF)) % 10.0f) : (ListenerUtil.mutListener.listen(2510) ? ((float) (((weightBytes[16] & 0xFF) << 8) | (weightBytes[17] & 0xFF)) * 10.0f) : (ListenerUtil.mutListener.listen(2509) ? ((float) (((weightBytes[16] & 0xFF) << 8) | (weightBytes[17] & 0xFF)) - 10.0f) : (ListenerUtil.mutListener.listen(2508) ? ((float) (((weightBytes[16] & 0xFF) << 8) | (weightBytes[17] & 0xFF)) + 10.0f) : ((float) (((weightBytes[16] & 0xFF) << 8) | (weightBytes[17] & 0xFF)) / 10.0f)))));
                boneWeight = (ListenerUtil.mutListener.listen(2515) ? ((float) (weightBytes[18] & 0xFF) % 10.0f) : (ListenerUtil.mutListener.listen(2514) ? ((float) (weightBytes[18] & 0xFF) * 10.0f) : (ListenerUtil.mutListener.listen(2513) ? ((float) (weightBytes[18] & 0xFF) - 10.0f) : (ListenerUtil.mutListener.listen(2512) ? ((float) (weightBytes[18] & 0xFF) + 10.0f) : ((float) (weightBytes[18] & 0xFF) / 10.0f)))));
                if (!ListenerUtil.mutListener.listen(2516)) {
                    scaleBtData.setDateTime(new Date());
                }
                if (!ListenerUtil.mutListener.listen(2517)) {
                    scaleBtData.setFat(fat);
                }
                if (!ListenerUtil.mutListener.listen(2518)) {
                    scaleBtData.setMuscle(muscle);
                }
                if (!ListenerUtil.mutListener.listen(2519)) {
                    scaleBtData.setWater(water);
                }
                if (!ListenerUtil.mutListener.listen(2520)) {
                    scaleBtData.setBone(boneWeight);
                }
                if (!ListenerUtil.mutListener.listen(2521)) {
                    scaleBtData.setVisceralFat(visceralFat);
                }
            }
            if (!ListenerUtil.mutListener.listen(2523)) {
                scaleBtData.setWeight(weight);
            }
            if (!ListenerUtil.mutListener.listen(2524)) {
                addScaleMeasurement(scaleBtData);
            }
        }
    }
}
