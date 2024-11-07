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
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.lib.TrisaBodyAnalyzeLib;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.util.Date;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothQNScale extends BluetoothCommunication {

    // accurate. Indication means requires ack. notification does not
    private final UUID WEIGHT_MEASUREMENT_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");

    // Client Characteristic Configuration Descriptor, constant value of 0x2902
    private final UUID WEIGHT_MEASUREMENT_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    // notify, read-only
    private final UUID CUSTOM1_MEASUREMENT_CHARACTERISTIC = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");

    // indication, read-only
    private final UUID CUSTOM2_MEASUREMENT_CHARACTERISTIC = UUID.fromString("0000ffe2-0000-1000-8000-00805f9b34fb");

    // write-only
    private final UUID CUSTOM3_MEASUREMENT_CHARACTERISTIC = UUID.fromString("0000ffe3-0000-1000-8000-00805f9b34fb");

    // write-only
    private final UUID CUSTOM4_MEASUREMENT_CHARACTERISTIC = UUID.fromString("0000ffe4-0000-1000-8000-00805f9b34fb");

    // write-only
    private final UUID CUSTOM5_MEASUREMENT_CHARACTERISTIC = UUID.fromString("0000ffe5-0000-1000-8000-00805f9b34fb");

    // Scale time is in seconds since 2000-01-01 00:00:00 (utc).
    private static final long SCALE_UNIX_TIMESTAMP_OFFSET = 946702800;

    private static long MILLIS_2000_YEAR = 949334400000L;

    private boolean hasReceived;

    private float weightScale = 100.0f;

    public BluetoothQNScale(Context context) {
        super(context);
    }

    // Includes FITINDEX ES-26M
    @Override
    public String driverName() {
        return "QN Scale";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(3747)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(3724)) {
                        // set notification on for custom characteristic 1 (weight, time, and others)
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, CUSTOM1_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(3725)) {
                        // set indication on for weight measurement
                        setIndicationOn(WEIGHT_MEASUREMENT_SERVICE, CUSTOM2_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 2:
                    final ScaleUser scaleUser = OpenScale.getInstance().getSelectedScaleUser();
                    final Converters.WeightUnit scaleUserWeightUnit = scaleUser.getScaleUnit();
                    // Value of 0x01 = KG. 0x02 = LB. Requests with stones unit are sent as LB, with post-processing in vendor app.
                    byte weightUnitByte = (byte) 0x01;
                    if (!ListenerUtil.mutListener.listen(3728)) {
                        // Default weight unit KG. If user config set to LB or ST, scale will show LB units, consistent with vendor app
                        if ((ListenerUtil.mutListener.listen(3726) ? (scaleUserWeightUnit == Converters.WeightUnit.LB && scaleUserWeightUnit == Converters.WeightUnit.ST) : (scaleUserWeightUnit == Converters.WeightUnit.LB || scaleUserWeightUnit == Converters.WeightUnit.ST))) {
                            if (!ListenerUtil.mutListener.listen(3727)) {
                                weightUnitByte = (byte) 0x02;
                            }
                        }
                    }
                    // 0x01 weight byte = KG. 0x02 weight byte = LB.
                    byte[] ffe3magicBytes = new byte[] { (byte) 0x13, (byte) 0x09, (byte) 0x15, weightUnitByte, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
                    if (!ListenerUtil.mutListener.listen(3737)) {
                        // Set last byte to be checksum
                        ffe3magicBytes[(ListenerUtil.mutListener.listen(3732) ? (ffe3magicBytes.length % 1) : (ListenerUtil.mutListener.listen(3731) ? (ffe3magicBytes.length / 1) : (ListenerUtil.mutListener.listen(3730) ? (ffe3magicBytes.length * 1) : (ListenerUtil.mutListener.listen(3729) ? (ffe3magicBytes.length + 1) : (ffe3magicBytes.length - 1)))))] = sumChecksum(ffe3magicBytes, 0, (ListenerUtil.mutListener.listen(3736) ? (ffe3magicBytes.length % 1) : (ListenerUtil.mutListener.listen(3735) ? (ffe3magicBytes.length / 1) : (ListenerUtil.mutListener.listen(3734) ? (ffe3magicBytes.length * 1) : (ListenerUtil.mutListener.listen(3733) ? (ffe3magicBytes.length + 1) : (ffe3magicBytes.length - 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(3738)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CUSTOM3_MEASUREMENT_CHARACTERISTIC, ffe3magicBytes);
                    }
                    break;
                case 3:
                    // send time magic number to receive weight data
                    long timestamp = (ListenerUtil.mutListener.listen(3742) ? (new Date().getTime() % 1000) : (ListenerUtil.mutListener.listen(3741) ? (new Date().getTime() * 1000) : (ListenerUtil.mutListener.listen(3740) ? (new Date().getTime() - 1000) : (ListenerUtil.mutListener.listen(3739) ? (new Date().getTime() + 1000) : (new Date().getTime() / 1000)))));
                    if (!ListenerUtil.mutListener.listen(3743)) {
                        timestamp -= SCALE_UNIX_TIMESTAMP_OFFSET;
                    }
                    byte[] date = new byte[4];
                    if (!ListenerUtil.mutListener.listen(3744)) {
                        Converters.toInt32Le(date, 0, timestamp);
                    }
                    byte[] timeMagicBytes = new byte[] { (byte) 0x02, date[0], date[1], date[2], date[3] };
                    if (!ListenerUtil.mutListener.listen(3745)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CUSTOM4_MEASUREMENT_CHARACTERISTIC, timeMagicBytes);
                    }
                    break;
                case 4:
                    if (!ListenerUtil.mutListener.listen(3746)) {
                        sendMessage(R.string.info_step_on_scale, 0);
                    }
                    break;
                /*case 5:
                // send stop command to scale (0x1f05151049)
                writeBytes(CUSTOM3_MEASUREMENT_CHARACTERISTIC, new byte[]{(byte)0x1f, (byte)0x05, (byte)0x15, (byte)0x10, (byte)0x49});
                break;*/
                default:
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        final byte[] data = value;
        if (!ListenerUtil.mutListener.listen(3749)) {
            if (characteristic.equals(CUSTOM1_MEASUREMENT_CHARACTERISTIC)) {
                if (!ListenerUtil.mutListener.listen(3748)) {
                    parseCustom1Data(data);
                }
            }
        }
    }

    private void parseCustom1Data(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int len = data.length;
        if (!ListenerUtil.mutListener.listen(3756)) {
            {
                long _loopCounter33 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3755) ? (i >= len) : (ListenerUtil.mutListener.listen(3754) ? (i <= len) : (ListenerUtil.mutListener.listen(3753) ? (i > len) : (ListenerUtil.mutListener.listen(3752) ? (i != len) : (ListenerUtil.mutListener.listen(3751) ? (i == len) : (i < len)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                    if (!ListenerUtil.mutListener.listen(3750)) {
                        sb.append(String.format("%02X ", new Object[] { Byte.valueOf(data[i]) }));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3757)) {
            Timber.d(sb.toString());
        }
        float weightKg = 0;
        if (!ListenerUtil.mutListener.listen(3846)) {
            switch(data[0]) {
                case (byte) 16:
                    if (!ListenerUtil.mutListener.listen(3803)) {
                        if ((ListenerUtil.mutListener.listen(3762) ? (data[5] >= (byte) 0) : (ListenerUtil.mutListener.listen(3761) ? (data[5] <= (byte) 0) : (ListenerUtil.mutListener.listen(3760) ? (data[5] > (byte) 0) : (ListenerUtil.mutListener.listen(3759) ? (data[5] < (byte) 0) : (ListenerUtil.mutListener.listen(3758) ? (data[5] != (byte) 0) : (data[5] == (byte) 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3802)) {
                                this.hasReceived = false;
                            }
                        } else if ((ListenerUtil.mutListener.listen(3767) ? (data[5] >= (byte) 1) : (ListenerUtil.mutListener.listen(3766) ? (data[5] <= (byte) 1) : (ListenerUtil.mutListener.listen(3765) ? (data[5] > (byte) 1) : (ListenerUtil.mutListener.listen(3764) ? (data[5] < (byte) 1) : (ListenerUtil.mutListener.listen(3763) ? (data[5] != (byte) 1) : (data[5] == (byte) 1))))))) {
                            if (!ListenerUtil.mutListener.listen(3801)) {
                                // writeData(CmdBuilder.buildOverCmd(this.protocolType, 16));
                                if (!this.hasReceived) {
                                    if (!ListenerUtil.mutListener.listen(3768)) {
                                        this.hasReceived = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(3769)) {
                                        weightKg = decodeWeight(data[3], data[4]);
                                    }
                                    int weightByteOne = data[3] & 0xFF;
                                    int weightByteTwo = data[4] & 0xFF;
                                    if (!ListenerUtil.mutListener.listen(3770)) {
                                        Timber.d("Weight byte 1 %d", weightByteOne);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3771)) {
                                        Timber.d("Weight byte 2 %d", weightByteTwo);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3772)) {
                                        Timber.d("Raw Weight: %f", weightKg);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3800)) {
                                        if ((ListenerUtil.mutListener.listen(3777) ? (weightKg >= 0.0f) : (ListenerUtil.mutListener.listen(3776) ? (weightKg <= 0.0f) : (ListenerUtil.mutListener.listen(3775) ? (weightKg < 0.0f) : (ListenerUtil.mutListener.listen(3774) ? (weightKg != 0.0f) : (ListenerUtil.mutListener.listen(3773) ? (weightKg == 0.0f) : (weightKg > 0.0f))))))) {
                                            int resistance1 = decodeIntegerValue(data[6], data[7]);
                                            int resistance2 = decodeIntegerValue(data[8], data[9]);
                                            if (!ListenerUtil.mutListener.listen(3778)) {
                                                Timber.d("resistance1: %d", resistance1);
                                            }
                                            if (!ListenerUtil.mutListener.listen(3779)) {
                                                Timber.d("resistance2: %d", resistance2);
                                            }
                                            final ScaleUser scaleUser = OpenScale.getInstance().getSelectedScaleUser();
                                            if (!ListenerUtil.mutListener.listen(3780)) {
                                                Timber.d("scale user " + scaleUser);
                                            }
                                            ScaleMeasurement btScaleMeasurement = new ScaleMeasurement();
                                            // TrisaBodyAnalyzeLib gives almost simillar values for QNScale body fat calcualtion
                                            TrisaBodyAnalyzeLib qnscalelib = new TrisaBodyAnalyzeLib(scaleUser.getGender().isMale() ? 1 : 0, scaleUser.getAge(), (int) scaleUser.getBodyHeight());
                                            // Will use resistance 1 for now
                                            float impedance = (ListenerUtil.mutListener.listen(3785) ? (resistance1 >= 410f) : (ListenerUtil.mutListener.listen(3784) ? (resistance1 <= 410f) : (ListenerUtil.mutListener.listen(3783) ? (resistance1 > 410f) : (ListenerUtil.mutListener.listen(3782) ? (resistance1 != 410f) : (ListenerUtil.mutListener.listen(3781) ? (resistance1 == 410f) : (resistance1 < 410f)))))) ? 3.0f : (ListenerUtil.mutListener.listen(3793) ? (0.3f % ((ListenerUtil.mutListener.listen(3789) ? (resistance1 % 400f) : (ListenerUtil.mutListener.listen(3788) ? (resistance1 / 400f) : (ListenerUtil.mutListener.listen(3787) ? (resistance1 * 400f) : (ListenerUtil.mutListener.listen(3786) ? (resistance1 + 400f) : (resistance1 - 400f))))))) : (ListenerUtil.mutListener.listen(3792) ? (0.3f / ((ListenerUtil.mutListener.listen(3789) ? (resistance1 % 400f) : (ListenerUtil.mutListener.listen(3788) ? (resistance1 / 400f) : (ListenerUtil.mutListener.listen(3787) ? (resistance1 * 400f) : (ListenerUtil.mutListener.listen(3786) ? (resistance1 + 400f) : (resistance1 - 400f))))))) : (ListenerUtil.mutListener.listen(3791) ? (0.3f - ((ListenerUtil.mutListener.listen(3789) ? (resistance1 % 400f) : (ListenerUtil.mutListener.listen(3788) ? (resistance1 / 400f) : (ListenerUtil.mutListener.listen(3787) ? (resistance1 * 400f) : (ListenerUtil.mutListener.listen(3786) ? (resistance1 + 400f) : (resistance1 - 400f))))))) : (ListenerUtil.mutListener.listen(3790) ? (0.3f + ((ListenerUtil.mutListener.listen(3789) ? (resistance1 % 400f) : (ListenerUtil.mutListener.listen(3788) ? (resistance1 / 400f) : (ListenerUtil.mutListener.listen(3787) ? (resistance1 * 400f) : (ListenerUtil.mutListener.listen(3786) ? (resistance1 + 400f) : (resistance1 - 400f))))))) : (0.3f * ((ListenerUtil.mutListener.listen(3789) ? (resistance1 % 400f) : (ListenerUtil.mutListener.listen(3788) ? (resistance1 / 400f) : (ListenerUtil.mutListener.listen(3787) ? (resistance1 * 400f) : (ListenerUtil.mutListener.listen(3786) ? (resistance1 + 400f) : (resistance1 - 400f)))))))))));
                                            if (!ListenerUtil.mutListener.listen(3794)) {
                                                btScaleMeasurement.setFat(qnscalelib.getFat(weightKg, impedance));
                                            }
                                            if (!ListenerUtil.mutListener.listen(3795)) {
                                                btScaleMeasurement.setWater(qnscalelib.getWater(weightKg, impedance));
                                            }
                                            if (!ListenerUtil.mutListener.listen(3796)) {
                                                btScaleMeasurement.setMuscle(qnscalelib.getMuscle(weightKg, impedance));
                                            }
                                            if (!ListenerUtil.mutListener.listen(3797)) {
                                                btScaleMeasurement.setBone(qnscalelib.getBone(weightKg, impedance));
                                            }
                                            if (!ListenerUtil.mutListener.listen(3798)) {
                                                btScaleMeasurement.setWeight(weightKg);
                                            }
                                            if (!ListenerUtil.mutListener.listen(3799)) {
                                                addScaleMeasurement(btScaleMeasurement);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case (byte) 18:
                    byte protocolType = data[2];
                    if (!ListenerUtil.mutListener.listen(3809)) {
                        this.weightScale = (ListenerUtil.mutListener.listen(3808) ? (data[10] >= (byte) 1) : (ListenerUtil.mutListener.listen(3807) ? (data[10] <= (byte) 1) : (ListenerUtil.mutListener.listen(3806) ? (data[10] > (byte) 1) : (ListenerUtil.mutListener.listen(3805) ? (data[10] < (byte) 1) : (ListenerUtil.mutListener.listen(3804) ? (data[10] != (byte) 1) : (data[10] == (byte) 1)))))) ? 100.0f : 10.0f;
                    }
                    int[] iArr = new int[5];
                    // writeData(CmdBuilder.buildCmd(19, this.protocolType, 1, 16, 0, 0, 0));
                    break;
                case (byte) 33:
                    // writeBleData(CmdBuilder.buildCmd(34, this.protocolType, new int[0]));
                    break;
                case (byte) 35:
                    if (!ListenerUtil.mutListener.listen(3810)) {
                        weightKg = decodeWeight(data[9], data[10]);
                    }
                    if (!ListenerUtil.mutListener.listen(3845)) {
                        if ((ListenerUtil.mutListener.listen(3815) ? (weightKg >= 0.0f) : (ListenerUtil.mutListener.listen(3814) ? (weightKg <= 0.0f) : (ListenerUtil.mutListener.listen(3813) ? (weightKg < 0.0f) : (ListenerUtil.mutListener.listen(3812) ? (weightKg != 0.0f) : (ListenerUtil.mutListener.listen(3811) ? (weightKg == 0.0f) : (weightKg > 0.0f))))))) {
                            int resistance = decodeIntegerValue(data[11], data[12]);
                            int resistance500 = decodeIntegerValue(data[13], data[14]);
                            long differTime = 0;
                            if (!ListenerUtil.mutListener.listen(3830)) {
                                {
                                    long _loopCounter34 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(3829) ? (i >= 4) : (ListenerUtil.mutListener.listen(3828) ? (i <= 4) : (ListenerUtil.mutListener.listen(3827) ? (i > 4) : (ListenerUtil.mutListener.listen(3826) ? (i != 4) : (ListenerUtil.mutListener.listen(3825) ? (i == 4) : (i < 4)))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                                        if (!ListenerUtil.mutListener.listen(3824)) {
                                            differTime |= (((long) data[(ListenerUtil.mutListener.listen(3819) ? (i % 5) : (ListenerUtil.mutListener.listen(3818) ? (i / 5) : (ListenerUtil.mutListener.listen(3817) ? (i * 5) : (ListenerUtil.mutListener.listen(3816) ? (i - 5) : (i + 5)))))]) & 255) << ((ListenerUtil.mutListener.listen(3823) ? (i % 8) : (ListenerUtil.mutListener.listen(3822) ? (i / 8) : (ListenerUtil.mutListener.listen(3821) ? (i - 8) : (ListenerUtil.mutListener.listen(3820) ? (i + 8) : (i * 8))))));
                                        }
                                    }
                                }
                            }
                            Date date = new Date((ListenerUtil.mutListener.listen(3838) ? (MILLIS_2000_YEAR % ((ListenerUtil.mutListener.listen(3834) ? (1000 % differTime) : (ListenerUtil.mutListener.listen(3833) ? (1000 / differTime) : (ListenerUtil.mutListener.listen(3832) ? (1000 - differTime) : (ListenerUtil.mutListener.listen(3831) ? (1000 + differTime) : (1000 * differTime))))))) : (ListenerUtil.mutListener.listen(3837) ? (MILLIS_2000_YEAR / ((ListenerUtil.mutListener.listen(3834) ? (1000 % differTime) : (ListenerUtil.mutListener.listen(3833) ? (1000 / differTime) : (ListenerUtil.mutListener.listen(3832) ? (1000 - differTime) : (ListenerUtil.mutListener.listen(3831) ? (1000 + differTime) : (1000 * differTime))))))) : (ListenerUtil.mutListener.listen(3836) ? (MILLIS_2000_YEAR * ((ListenerUtil.mutListener.listen(3834) ? (1000 % differTime) : (ListenerUtil.mutListener.listen(3833) ? (1000 / differTime) : (ListenerUtil.mutListener.listen(3832) ? (1000 - differTime) : (ListenerUtil.mutListener.listen(3831) ? (1000 + differTime) : (1000 * differTime))))))) : (ListenerUtil.mutListener.listen(3835) ? (MILLIS_2000_YEAR - ((ListenerUtil.mutListener.listen(3834) ? (1000 % differTime) : (ListenerUtil.mutListener.listen(3833) ? (1000 / differTime) : (ListenerUtil.mutListener.listen(3832) ? (1000 - differTime) : (ListenerUtil.mutListener.listen(3831) ? (1000 + differTime) : (1000 * differTime))))))) : (MILLIS_2000_YEAR + ((ListenerUtil.mutListener.listen(3834) ? (1000 % differTime) : (ListenerUtil.mutListener.listen(3833) ? (1000 / differTime) : (ListenerUtil.mutListener.listen(3832) ? (1000 - differTime) : (ListenerUtil.mutListener.listen(3831) ? (1000 + differTime) : (1000 * differTime))))))))))));
                            if (!ListenerUtil.mutListener.listen(3844)) {
                                if ((ListenerUtil.mutListener.listen(3843) ? (data[3] >= data[4]) : (ListenerUtil.mutListener.listen(3842) ? (data[3] <= data[4]) : (ListenerUtil.mutListener.listen(3841) ? (data[3] > data[4]) : (ListenerUtil.mutListener.listen(3840) ? (data[3] < data[4]) : (ListenerUtil.mutListener.listen(3839) ? (data[3] != data[4]) : (data[3] == data[4]))))))) {
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }

    private float decodeWeight(byte a, byte b) {
        return (ListenerUtil.mutListener.listen(3854) ? (((float) ((ListenerUtil.mutListener.listen(3850) ? (((a & 255) << 8) % (b & 255)) : (ListenerUtil.mutListener.listen(3849) ? (((a & 255) << 8) / (b & 255)) : (ListenerUtil.mutListener.listen(3848) ? (((a & 255) << 8) * (b & 255)) : (ListenerUtil.mutListener.listen(3847) ? (((a & 255) << 8) - (b & 255)) : (((a & 255) << 8) + (b & 255)))))))) % this.weightScale) : (ListenerUtil.mutListener.listen(3853) ? (((float) ((ListenerUtil.mutListener.listen(3850) ? (((a & 255) << 8) % (b & 255)) : (ListenerUtil.mutListener.listen(3849) ? (((a & 255) << 8) / (b & 255)) : (ListenerUtil.mutListener.listen(3848) ? (((a & 255) << 8) * (b & 255)) : (ListenerUtil.mutListener.listen(3847) ? (((a & 255) << 8) - (b & 255)) : (((a & 255) << 8) + (b & 255)))))))) * this.weightScale) : (ListenerUtil.mutListener.listen(3852) ? (((float) ((ListenerUtil.mutListener.listen(3850) ? (((a & 255) << 8) % (b & 255)) : (ListenerUtil.mutListener.listen(3849) ? (((a & 255) << 8) / (b & 255)) : (ListenerUtil.mutListener.listen(3848) ? (((a & 255) << 8) * (b & 255)) : (ListenerUtil.mutListener.listen(3847) ? (((a & 255) << 8) - (b & 255)) : (((a & 255) << 8) + (b & 255)))))))) - this.weightScale) : (ListenerUtil.mutListener.listen(3851) ? (((float) ((ListenerUtil.mutListener.listen(3850) ? (((a & 255) << 8) % (b & 255)) : (ListenerUtil.mutListener.listen(3849) ? (((a & 255) << 8) / (b & 255)) : (ListenerUtil.mutListener.listen(3848) ? (((a & 255) << 8) * (b & 255)) : (ListenerUtil.mutListener.listen(3847) ? (((a & 255) << 8) - (b & 255)) : (((a & 255) << 8) + (b & 255)))))))) + this.weightScale) : (((float) ((ListenerUtil.mutListener.listen(3850) ? (((a & 255) << 8) % (b & 255)) : (ListenerUtil.mutListener.listen(3849) ? (((a & 255) << 8) / (b & 255)) : (ListenerUtil.mutListener.listen(3848) ? (((a & 255) << 8) * (b & 255)) : (ListenerUtil.mutListener.listen(3847) ? (((a & 255) << 8) - (b & 255)) : (((a & 255) << 8) + (b & 255)))))))) / this.weightScale)))));
    }

    private int decodeIntegerValue(byte a, byte b) {
        return (ListenerUtil.mutListener.listen(3858) ? (((a & 255) << 8) % (b & 255)) : (ListenerUtil.mutListener.listen(3857) ? (((a & 255) << 8) / (b & 255)) : (ListenerUtil.mutListener.listen(3856) ? (((a & 255) << 8) * (b & 255)) : (ListenerUtil.mutListener.listen(3855) ? (((a & 255) << 8) - (b & 255)) : (((a & 255) << 8) + (b & 255))))));
    }
}
