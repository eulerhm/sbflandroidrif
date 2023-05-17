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
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.lib.YunmaiLib;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothYunmaiSE_Mini extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0xffe0);

    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xffe4);

    private final UUID WEIGHT_CMD_SERVICE = BluetoothGattUuid.fromShortCode(0xffe5);

    private final UUID WEIGHT_CMD_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xffe9);

    private boolean isMini;

    public BluetoothYunmaiSE_Mini(Context context, boolean isMini) {
        super(context);
        if (!ListenerUtil.mutListener.listen(4567)) {
            this.isMini = isMini;
        }
    }

    @Override
    public String driverName() {
        return isMini ? "Yunmai Mini" : "Yunmai SE";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(4595)) {
            switch(stepNr) {
                case 0:
                    byte[] userId = Converters.toInt16Be(getUniqueNumber());
                    final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                    byte sex = selectedUser.getGender().isMale() ? (byte) 0x01 : (byte) 0x02;
                    byte display_unit = selectedUser.getScaleUnit() == Converters.WeightUnit.KG ? (byte) 0x01 : (byte) 0x02;
                    byte body_type = (byte) YunmaiLib.toYunmaiActivityLevel(selectedUser.getActivityLevel());
                    byte[] user_add_or_query = new byte[] { (byte) 0x0d, (byte) 0x12, (byte) 0x10, (byte) 0x01, (byte) 0x00, (byte) 0x00, userId[0], userId[1], (byte) selectedUser.getBodyHeight(), sex, (byte) selectedUser.getAge(), (byte) 0x55, (byte) 0x5a, (byte) 0x00, (byte) 0x00, display_unit, body_type, (byte) 0x00 };
                    if (!ListenerUtil.mutListener.listen(4576)) {
                        user_add_or_query[(ListenerUtil.mutListener.listen(4571) ? (user_add_or_query.length % 1) : (ListenerUtil.mutListener.listen(4570) ? (user_add_or_query.length / 1) : (ListenerUtil.mutListener.listen(4569) ? (user_add_or_query.length * 1) : (ListenerUtil.mutListener.listen(4568) ? (user_add_or_query.length + 1) : (user_add_or_query.length - 1)))))] = xorChecksum(user_add_or_query, 1, (ListenerUtil.mutListener.listen(4575) ? (user_add_or_query.length % 1) : (ListenerUtil.mutListener.listen(4574) ? (user_add_or_query.length / 1) : (ListenerUtil.mutListener.listen(4573) ? (user_add_or_query.length * 1) : (ListenerUtil.mutListener.listen(4572) ? (user_add_or_query.length + 1) : (user_add_or_query.length - 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(4577)) {
                        writeBytes(WEIGHT_CMD_SERVICE, WEIGHT_CMD_CHARACTERISTIC, user_add_or_query);
                    }
                    break;
                case 1:
                    byte[] unixTime = Converters.toInt32Be((ListenerUtil.mutListener.listen(4581) ? (new Date().getTime() % 1000) : (ListenerUtil.mutListener.listen(4580) ? (new Date().getTime() * 1000) : (ListenerUtil.mutListener.listen(4579) ? (new Date().getTime() - 1000) : (ListenerUtil.mutListener.listen(4578) ? (new Date().getTime() + 1000) : (new Date().getTime() / 1000))))));
                    byte[] set_time = new byte[] { (byte) 0x0d, (byte) 0x0d, (byte) 0x11, unixTime[0], unixTime[1], unixTime[2], unixTime[3], (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
                    if (!ListenerUtil.mutListener.listen(4590)) {
                        set_time[(ListenerUtil.mutListener.listen(4585) ? (set_time.length % 1) : (ListenerUtil.mutListener.listen(4584) ? (set_time.length / 1) : (ListenerUtil.mutListener.listen(4583) ? (set_time.length * 1) : (ListenerUtil.mutListener.listen(4582) ? (set_time.length + 1) : (set_time.length - 1)))))] = xorChecksum(set_time, 1, (ListenerUtil.mutListener.listen(4589) ? (set_time.length % 1) : (ListenerUtil.mutListener.listen(4588) ? (set_time.length / 1) : (ListenerUtil.mutListener.listen(4587) ? (set_time.length * 1) : (ListenerUtil.mutListener.listen(4586) ? (set_time.length + 1) : (set_time.length - 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(4591)) {
                        writeBytes(WEIGHT_CMD_SERVICE, WEIGHT_CMD_CHARACTERISTIC, set_time);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(4592)) {
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 3:
                    byte[] magic_bytes = new byte[] { (byte) 0x0d, (byte) 0x05, (byte) 0x13, (byte) 0x00, (byte) 0x16 };
                    if (!ListenerUtil.mutListener.listen(4593)) {
                        writeBytes(WEIGHT_CMD_SERVICE, WEIGHT_CMD_CHARACTERISTIC, magic_bytes);
                    }
                    if (!ListenerUtil.mutListener.listen(4594)) {
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
        if (!ListenerUtil.mutListener.listen(4609)) {
            if ((ListenerUtil.mutListener.listen(4601) ? (data != null || (ListenerUtil.mutListener.listen(4600) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(4599) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(4598) ? (data.length < 0) : (ListenerUtil.mutListener.listen(4597) ? (data.length != 0) : (ListenerUtil.mutListener.listen(4596) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(4600) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(4599) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(4598) ? (data.length < 0) : (ListenerUtil.mutListener.listen(4597) ? (data.length != 0) : (ListenerUtil.mutListener.listen(4596) ? (data.length == 0) : (data.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(4608)) {
                    // if finished weighting?
                    if ((ListenerUtil.mutListener.listen(4606) ? (data[3] >= 0x02) : (ListenerUtil.mutListener.listen(4605) ? (data[3] <= 0x02) : (ListenerUtil.mutListener.listen(4604) ? (data[3] > 0x02) : (ListenerUtil.mutListener.listen(4603) ? (data[3] < 0x02) : (ListenerUtil.mutListener.listen(4602) ? (data[3] != 0x02) : (data[3] == 0x02))))))) {
                        if (!ListenerUtil.mutListener.listen(4607)) {
                            parseBytes(data);
                        }
                    }
                }
            }
        }
    }

    private void parseBytes(byte[] weightBytes) {
        final ScaleUser scaleUser = OpenScale.getInstance().getSelectedScaleUser();
        ScaleMeasurement scaleBtData = new ScaleMeasurement();
        long timestamp = (ListenerUtil.mutListener.listen(4613) ? (Converters.fromUnsignedInt32Be(weightBytes, 5) % 1000) : (ListenerUtil.mutListener.listen(4612) ? (Converters.fromUnsignedInt32Be(weightBytes, 5) / 1000) : (ListenerUtil.mutListener.listen(4611) ? (Converters.fromUnsignedInt32Be(weightBytes, 5) - 1000) : (ListenerUtil.mutListener.listen(4610) ? (Converters.fromUnsignedInt32Be(weightBytes, 5) + 1000) : (Converters.fromUnsignedInt32Be(weightBytes, 5) * 1000)))));
        if (!ListenerUtil.mutListener.listen(4614)) {
            scaleBtData.setDateTime(new Date(timestamp));
        }
        float weight = (ListenerUtil.mutListener.listen(4618) ? (Converters.fromUnsignedInt16Be(weightBytes, 13) % 100.0f) : (ListenerUtil.mutListener.listen(4617) ? (Converters.fromUnsignedInt16Be(weightBytes, 13) * 100.0f) : (ListenerUtil.mutListener.listen(4616) ? (Converters.fromUnsignedInt16Be(weightBytes, 13) - 100.0f) : (ListenerUtil.mutListener.listen(4615) ? (Converters.fromUnsignedInt16Be(weightBytes, 13) + 100.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 13) / 100.0f)))));
        if (!ListenerUtil.mutListener.listen(4619)) {
            scaleBtData.setWeight(weight);
        }
        if (!ListenerUtil.mutListener.listen(4648)) {
            if (isMini) {
                int sex;
                if (scaleUser.getGender() == Converters.Gender.MALE) {
                    sex = 1;
                } else {
                    sex = 0;
                }
                YunmaiLib yunmaiLib = new YunmaiLib(sex, scaleUser.getBodyHeight(), scaleUser.getActivityLevel());
                float bodyFat;
                int resistance = Converters.fromUnsignedInt16Be(weightBytes, 15);
                if ((ListenerUtil.mutListener.listen(4624) ? (weightBytes[1] <= (byte) 0x1E) : (ListenerUtil.mutListener.listen(4623) ? (weightBytes[1] > (byte) 0x1E) : (ListenerUtil.mutListener.listen(4622) ? (weightBytes[1] < (byte) 0x1E) : (ListenerUtil.mutListener.listen(4621) ? (weightBytes[1] != (byte) 0x1E) : (ListenerUtil.mutListener.listen(4620) ? (weightBytes[1] == (byte) 0x1E) : (weightBytes[1] >= (byte) 0x1E))))))) {
                    if (!ListenerUtil.mutListener.listen(4626)) {
                        Timber.d("Extract the fat value from received bytes");
                    }
                    bodyFat = (ListenerUtil.mutListener.listen(4630) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) % 100.0f) : (ListenerUtil.mutListener.listen(4629) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) * 100.0f) : (ListenerUtil.mutListener.listen(4628) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) - 100.0f) : (ListenerUtil.mutListener.listen(4627) ? (Converters.fromUnsignedInt16Be(weightBytes, 17) + 100.0f) : (Converters.fromUnsignedInt16Be(weightBytes, 17) / 100.0f)))));
                } else {
                    if (!ListenerUtil.mutListener.listen(4625)) {
                        Timber.d("Calculate the fat value using the Yunmai lib");
                    }
                    bodyFat = yunmaiLib.getFat(scaleUser.getAge(), weight, resistance);
                }
                if (!ListenerUtil.mutListener.listen(4643)) {
                    if ((ListenerUtil.mutListener.listen(4635) ? (bodyFat >= 0) : (ListenerUtil.mutListener.listen(4634) ? (bodyFat <= 0) : (ListenerUtil.mutListener.listen(4633) ? (bodyFat > 0) : (ListenerUtil.mutListener.listen(4632) ? (bodyFat < 0) : (ListenerUtil.mutListener.listen(4631) ? (bodyFat == 0) : (bodyFat != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(4637)) {
                            scaleBtData.setFat(bodyFat);
                        }
                        if (!ListenerUtil.mutListener.listen(4638)) {
                            scaleBtData.setMuscle(yunmaiLib.getMuscle(bodyFat));
                        }
                        if (!ListenerUtil.mutListener.listen(4639)) {
                            scaleBtData.setWater(yunmaiLib.getWater(bodyFat));
                        }
                        if (!ListenerUtil.mutListener.listen(4640)) {
                            scaleBtData.setBone(yunmaiLib.getBoneMass(scaleBtData.getMuscle(), weight));
                        }
                        if (!ListenerUtil.mutListener.listen(4641)) {
                            scaleBtData.setLbm(yunmaiLib.getLeanBodyMass(weight, bodyFat));
                        }
                        if (!ListenerUtil.mutListener.listen(4642)) {
                            scaleBtData.setVisceralFat(yunmaiLib.getVisceralFat(bodyFat, scaleUser.getAge()));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4636)) {
                            Timber.e("body fat is zero");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4644)) {
                    Timber.d("received bytes [%s]", byteInHex(weightBytes));
                }
                if (!ListenerUtil.mutListener.listen(4645)) {
                    Timber.d("received decrypted bytes [weight: %.2f, fat: %.2f, resistance: %d]", weight, bodyFat, resistance);
                }
                if (!ListenerUtil.mutListener.listen(4646)) {
                    Timber.d("user [%s]", scaleUser);
                }
                if (!ListenerUtil.mutListener.listen(4647)) {
                    Timber.d("scale measurement [%s]", scaleBtData);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4649)) {
            addScaleMeasurement(scaleBtData);
        }
    }

    private int getUniqueNumber() {
        int uniqueNumber;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        uniqueNumber = prefs.getInt("uniqueNumber", 0x00);
        if ((ListenerUtil.mutListener.listen(4654) ? (uniqueNumber >= 0x00) : (ListenerUtil.mutListener.listen(4653) ? (uniqueNumber <= 0x00) : (ListenerUtil.mutListener.listen(4652) ? (uniqueNumber > 0x00) : (ListenerUtil.mutListener.listen(4651) ? (uniqueNumber < 0x00) : (ListenerUtil.mutListener.listen(4650) ? (uniqueNumber != 0x00) : (uniqueNumber == 0x00))))))) {
            Random r = new Random();
            uniqueNumber = (ListenerUtil.mutListener.listen(4666) ? (r.nextInt((ListenerUtil.mutListener.listen(4662) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(4661) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(4660) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(4659) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) + 1)))))) % 100) : (ListenerUtil.mutListener.listen(4665) ? (r.nextInt((ListenerUtil.mutListener.listen(4662) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(4661) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(4660) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(4659) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) + 1)))))) / 100) : (ListenerUtil.mutListener.listen(4664) ? (r.nextInt((ListenerUtil.mutListener.listen(4662) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(4661) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(4660) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(4659) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) + 1)))))) * 100) : (ListenerUtil.mutListener.listen(4663) ? (r.nextInt((ListenerUtil.mutListener.listen(4662) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(4661) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(4660) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(4659) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) + 1)))))) - 100) : (r.nextInt((ListenerUtil.mutListener.listen(4662) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) % 1) : (ListenerUtil.mutListener.listen(4661) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) / 1) : (ListenerUtil.mutListener.listen(4660) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) * 1) : (ListenerUtil.mutListener.listen(4659) ? ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) - 1) : ((ListenerUtil.mutListener.listen(4658) ? (65535 % 100) : (ListenerUtil.mutListener.listen(4657) ? (65535 / 100) : (ListenerUtil.mutListener.listen(4656) ? (65535 * 100) : (ListenerUtil.mutListener.listen(4655) ? (65535 + 100) : (65535 - 100))))) + 1)))))) + 100)))));
            if (!ListenerUtil.mutListener.listen(4667)) {
                prefs.edit().putInt("uniqueNumber", uniqueNumber).apply();
            }
        }
        int userId = OpenScale.getInstance().getSelectedScaleUserId();
        return (ListenerUtil.mutListener.listen(4671) ? (uniqueNumber % userId) : (ListenerUtil.mutListener.listen(4670) ? (uniqueNumber / userId) : (ListenerUtil.mutListener.listen(4669) ? (uniqueNumber * userId) : (ListenerUtil.mutListener.listen(4668) ? (uniqueNumber - userId) : (uniqueNumber + userId)))));
    }
}
