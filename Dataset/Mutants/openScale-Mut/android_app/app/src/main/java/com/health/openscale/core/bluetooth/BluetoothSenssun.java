/* Copyright (C) 2018  Marco Gittler <marco@gitma.de>
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
import com.welie.blessed.BluetoothPeripheral;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothSenssun extends BluetoothCommunication {

    private final UUID MODEL_A_MEASUREMENT_SERVICE = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb");

    private final UUID MODEL_A_NOTIFICATION_CHARACTERISTIC = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");

    private final UUID MODEL_A_WRITE_CHARACTERISTIC = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");

    private final UUID MODEL_B_MEASUREMENT_SERVICE = UUID.fromString("0000ffb0-0000-1000-8000-00805f9b34fb");

    private final UUID MODEL_B_NOTIFICATION_CHARACTERISTIC = UUID.fromString("0000ffb2-0000-1000-8000-00805f9b34fb");

    private final UUID MODEL_B_WRITE_CHARACTERISTIC = UUID.fromString("0000ffb2-0000-1000-8000-00805f9b34fb");

    private UUID writeService;

    private UUID writeCharacteristic;

    private int lastWeight, lastFat, lastHydration, lastMuscle, lastBone, lastKcal;

    private boolean weightStabilized, stepMessageDisplayed;

    private int values;

    public BluetoothSenssun(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Senssun Fat";
    }

    @Override
    protected void onBluetoothDiscovery(BluetoothPeripheral peripheral) {
        if (!ListenerUtil.mutListener.listen(3863)) {
            if (peripheral.getService(MODEL_A_MEASUREMENT_SERVICE) != null) {
                if (!ListenerUtil.mutListener.listen(3859)) {
                    writeService = MODEL_A_MEASUREMENT_SERVICE;
                }
                if (!ListenerUtil.mutListener.listen(3860)) {
                    writeCharacteristic = MODEL_A_WRITE_CHARACTERISTIC;
                }
                if (!ListenerUtil.mutListener.listen(3861)) {
                    setNotificationOn(MODEL_A_MEASUREMENT_SERVICE, MODEL_A_NOTIFICATION_CHARACTERISTIC);
                }
                if (!ListenerUtil.mutListener.listen(3862)) {
                    Timber.d("Found a Model A");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3868)) {
            if (peripheral.getService(MODEL_B_MEASUREMENT_SERVICE) != null) {
                if (!ListenerUtil.mutListener.listen(3864)) {
                    writeService = MODEL_B_MEASUREMENT_SERVICE;
                }
                if (!ListenerUtil.mutListener.listen(3865)) {
                    writeCharacteristic = MODEL_B_WRITE_CHARACTERISTIC;
                }
                if (!ListenerUtil.mutListener.listen(3866)) {
                    setNotificationOn(MODEL_B_MEASUREMENT_SERVICE, MODEL_B_NOTIFICATION_CHARACTERISTIC);
                }
                if (!ListenerUtil.mutListener.listen(3867)) {
                    Timber.d("Found a Model B");
                }
            }
        }
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(3876)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(3869)) {
                        weightStabilized = false;
                    }
                    if (!ListenerUtil.mutListener.listen(3870)) {
                        stepMessageDisplayed = false;
                    }
                    if (!ListenerUtil.mutListener.listen(3871)) {
                        values = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(3872)) {
                        Timber.d("Sync Date");
                    }
                    if (!ListenerUtil.mutListener.listen(3873)) {
                        synchroniseDate();
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(3874)) {
                        Timber.d("Sync Time");
                    }
                    if (!ListenerUtil.mutListener.listen(3875)) {
                        synchroniseTime();
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
        if (!ListenerUtil.mutListener.listen(3883)) {
            if ((ListenerUtil.mutListener.listen(3882) ? (value == null && (ListenerUtil.mutListener.listen(3881) ? (value[0] >= (byte) 0xFF) : (ListenerUtil.mutListener.listen(3880) ? (value[0] <= (byte) 0xFF) : (ListenerUtil.mutListener.listen(3879) ? (value[0] > (byte) 0xFF) : (ListenerUtil.mutListener.listen(3878) ? (value[0] < (byte) 0xFF) : (ListenerUtil.mutListener.listen(3877) ? (value[0] == (byte) 0xFF) : (value[0] != (byte) 0xFF))))))) : (value == null || (ListenerUtil.mutListener.listen(3881) ? (value[0] >= (byte) 0xFF) : (ListenerUtil.mutListener.listen(3880) ? (value[0] <= (byte) 0xFF) : (ListenerUtil.mutListener.listen(3879) ? (value[0] > (byte) 0xFF) : (ListenerUtil.mutListener.listen(3878) ? (value[0] < (byte) 0xFF) : (ListenerUtil.mutListener.listen(3877) ? (value[0] == (byte) 0xFF) : (value[0] != (byte) 0xFF))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3888)) {
            System.arraycopy(value, 1, value, 0, (ListenerUtil.mutListener.listen(3887) ? (value.length % 1) : (ListenerUtil.mutListener.listen(3886) ? (value.length / 1) : (ListenerUtil.mutListener.listen(3885) ? (value.length * 1) : (ListenerUtil.mutListener.listen(3884) ? (value.length + 1) : (value.length - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(3890)) {
            switch(value[0]) {
                case (byte) 0xA5:
                    if (!ListenerUtil.mutListener.listen(3889)) {
                        parseMeasurement(value);
                    }
                    break;
            }
        }
    }

    private void parseMeasurement(byte[] data) {
        if (!ListenerUtil.mutListener.listen(3935)) {
            switch(data[5]) {
                case (byte) 0xAA:
                case (byte) 0xA0:
                    if (!ListenerUtil.mutListener.listen(3896)) {
                        if ((ListenerUtil.mutListener.listen(3895) ? (values >= 1) : (ListenerUtil.mutListener.listen(3894) ? (values <= 1) : (ListenerUtil.mutListener.listen(3893) ? (values < 1) : (ListenerUtil.mutListener.listen(3892) ? (values != 1) : (ListenerUtil.mutListener.listen(3891) ? (values == 1) : (values > 1))))))) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3899)) {
                        if (!stepMessageDisplayed) {
                            if (!ListenerUtil.mutListener.listen(3897)) {
                                sendMessage(R.string.info_step_on_scale, 0);
                            }
                            if (!ListenerUtil.mutListener.listen(3898)) {
                                stepMessageDisplayed = true;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3905)) {
                        weightStabilized = (ListenerUtil.mutListener.listen(3904) ? (data[5] >= (byte) 0xAA) : (ListenerUtil.mutListener.listen(3903) ? (data[5] <= (byte) 0xAA) : (ListenerUtil.mutListener.listen(3902) ? (data[5] > (byte) 0xAA) : (ListenerUtil.mutListener.listen(3901) ? (data[5] < (byte) 0xAA) : (ListenerUtil.mutListener.listen(3900) ? (data[5] != (byte) 0xAA) : (data[5] == (byte) 0xAA))))));
                    }
                    if (!ListenerUtil.mutListener.listen(3906)) {
                        Timber.d("the byte is %d stable is %s", (data[5] & 0xff), weightStabilized ? "true" : "false");
                    }
                    if (!ListenerUtil.mutListener.listen(3907)) {
                        lastWeight = ((data[1] & 0xff) << 8) | (data[2] & 0xff);
                    }
                    if (!ListenerUtil.mutListener.listen(3918)) {
                        if ((ListenerUtil.mutListener.listen(3912) ? (lastWeight >= 0) : (ListenerUtil.mutListener.listen(3911) ? (lastWeight <= 0) : (ListenerUtil.mutListener.listen(3910) ? (lastWeight < 0) : (ListenerUtil.mutListener.listen(3909) ? (lastWeight != 0) : (ListenerUtil.mutListener.listen(3908) ? (lastWeight == 0) : (lastWeight > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3917)) {
                                sendMessage(R.string.info_measuring, (ListenerUtil.mutListener.listen(3916) ? (lastWeight % 10.0f) : (ListenerUtil.mutListener.listen(3915) ? (lastWeight * 10.0f) : (ListenerUtil.mutListener.listen(3914) ? (lastWeight - 10.0f) : (ListenerUtil.mutListener.listen(3913) ? (lastWeight + 10.0f) : (lastWeight / 10.0f))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3921)) {
                        if (weightStabilized) {
                            if (!ListenerUtil.mutListener.listen(3919)) {
                                values |= 1;
                            }
                            if (!ListenerUtil.mutListener.listen(3920)) {
                                synchroniseUser();
                            }
                        }
                    }
                    break;
                case (byte) 0xBE:
                    if (!ListenerUtil.mutListener.listen(3922)) {
                        setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Fat Test Error");
                    }
                    if (!ListenerUtil.mutListener.listen(3923)) {
                        disconnect();
                    }
                    break;
                case (byte) 0xB0:
                    if (!ListenerUtil.mutListener.listen(3924)) {
                        lastFat = ((data[1] & 0xff) << 8) | (data[2] & 0xff);
                    }
                    if (!ListenerUtil.mutListener.listen(3925)) {
                        lastHydration = ((data[3] & 0xff) << 8) | (data[4] & 0xff);
                    }
                    if (!ListenerUtil.mutListener.listen(3926)) {
                        values |= 2;
                    }
                    if (!ListenerUtil.mutListener.listen(3927)) {
                        Timber.d("got fat %d", values);
                    }
                    break;
                case (byte) 0xC0:
                    if (!ListenerUtil.mutListener.listen(3928)) {
                        lastMuscle = ((data[1] & 0xff) << 8) | (data[2] & 0xff);
                    }
                    if (!ListenerUtil.mutListener.listen(3929)) {
                        lastBone = ((data[4] & 0xff) << 8) | (data[3] & 0xff);
                    }
                    if (!ListenerUtil.mutListener.listen(3930)) {
                        values |= 4;
                    }
                    if (!ListenerUtil.mutListener.listen(3931)) {
                        Timber.d("got muscle %d", values);
                    }
                    break;
                case (byte) 0xD0:
                    if (!ListenerUtil.mutListener.listen(3932)) {
                        lastKcal = ((data[1] & 0xff) << 8) | (data[2] & 0xff);
                    }
                    int unknown = ((data[3] & 0xff) << 8) | (data[4] & 0xff);
                    if (!ListenerUtil.mutListener.listen(3933)) {
                        values |= 8;
                    }
                    if (!ListenerUtil.mutListener.listen(3934)) {
                        Timber.d("got kal %d", values);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(3969)) {
            if ((ListenerUtil.mutListener.listen(3940) ? (values >= 15) : (ListenerUtil.mutListener.listen(3939) ? (values <= 15) : (ListenerUtil.mutListener.listen(3938) ? (values > 15) : (ListenerUtil.mutListener.listen(3937) ? (values < 15) : (ListenerUtil.mutListener.listen(3936) ? (values != 15) : (values == 15))))))) {
                ScaleMeasurement scaleBtData = new ScaleMeasurement();
                if (!ListenerUtil.mutListener.listen(3945)) {
                    scaleBtData.setWeight((ListenerUtil.mutListener.listen(3944) ? ((float) lastWeight % 10.0f) : (ListenerUtil.mutListener.listen(3943) ? ((float) lastWeight * 10.0f) : (ListenerUtil.mutListener.listen(3942) ? ((float) lastWeight - 10.0f) : (ListenerUtil.mutListener.listen(3941) ? ((float) lastWeight + 10.0f) : ((float) lastWeight / 10.0f))))));
                }
                if (!ListenerUtil.mutListener.listen(3950)) {
                    scaleBtData.setFat((ListenerUtil.mutListener.listen(3949) ? ((float) lastFat % 10.0f) : (ListenerUtil.mutListener.listen(3948) ? ((float) lastFat * 10.0f) : (ListenerUtil.mutListener.listen(3947) ? ((float) lastFat - 10.0f) : (ListenerUtil.mutListener.listen(3946) ? ((float) lastFat + 10.0f) : ((float) lastFat / 10.0f))))));
                }
                if (!ListenerUtil.mutListener.listen(3955)) {
                    scaleBtData.setWater((ListenerUtil.mutListener.listen(3954) ? ((float) lastHydration % 10.0f) : (ListenerUtil.mutListener.listen(3953) ? ((float) lastHydration * 10.0f) : (ListenerUtil.mutListener.listen(3952) ? ((float) lastHydration - 10.0f) : (ListenerUtil.mutListener.listen(3951) ? ((float) lastHydration + 10.0f) : ((float) lastHydration / 10.0f))))));
                }
                if (!ListenerUtil.mutListener.listen(3960)) {
                    scaleBtData.setBone((ListenerUtil.mutListener.listen(3959) ? ((float) lastBone % 10.0f) : (ListenerUtil.mutListener.listen(3958) ? ((float) lastBone * 10.0f) : (ListenerUtil.mutListener.listen(3957) ? ((float) lastBone - 10.0f) : (ListenerUtil.mutListener.listen(3956) ? ((float) lastBone + 10.0f) : ((float) lastBone / 10.0f))))));
                }
                if (!ListenerUtil.mutListener.listen(3965)) {
                    scaleBtData.setMuscle((ListenerUtil.mutListener.listen(3964) ? ((float) lastMuscle % 10.0f) : (ListenerUtil.mutListener.listen(3963) ? ((float) lastMuscle * 10.0f) : (ListenerUtil.mutListener.listen(3962) ? ((float) lastMuscle - 10.0f) : (ListenerUtil.mutListener.listen(3961) ? ((float) lastMuscle + 10.0f) : ((float) lastMuscle / 10.0f))))));
                }
                if (!ListenerUtil.mutListener.listen(3966)) {
                    scaleBtData.setDateTime(new Date());
                }
                if (!ListenerUtil.mutListener.listen(3967)) {
                    addScaleMeasurement(scaleBtData);
                }
                if (!ListenerUtil.mutListener.listen(3968)) {
                    disconnect();
                }
            }
        }
    }

    private void synchroniseDate() {
        Calendar cal = Calendar.getInstance();
        byte[] message = new byte[] { (byte) 0xA5, (byte) 0x30, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        if (!ListenerUtil.mutListener.listen(3970)) {
            message[2] = (byte) Integer.parseInt(Long.toHexString(Integer.valueOf(String.valueOf(cal.get(Calendar.YEAR)).substring(2))), 16);
        }
        String DayLength = Long.toHexString(cal.get(Calendar.DAY_OF_YEAR));
        if (!ListenerUtil.mutListener.listen(3986)) {
            DayLength = (ListenerUtil.mutListener.listen(3975) ? (DayLength.length() >= 1) : (ListenerUtil.mutListener.listen(3974) ? (DayLength.length() <= 1) : (ListenerUtil.mutListener.listen(3973) ? (DayLength.length() > 1) : (ListenerUtil.mutListener.listen(3972) ? (DayLength.length() < 1) : (ListenerUtil.mutListener.listen(3971) ? (DayLength.length() != 1) : (DayLength.length() == 1)))))) ? "000" + DayLength : (ListenerUtil.mutListener.listen(3980) ? (DayLength.length() >= 2) : (ListenerUtil.mutListener.listen(3979) ? (DayLength.length() <= 2) : (ListenerUtil.mutListener.listen(3978) ? (DayLength.length() > 2) : (ListenerUtil.mutListener.listen(3977) ? (DayLength.length() < 2) : (ListenerUtil.mutListener.listen(3976) ? (DayLength.length() != 2) : (DayLength.length() == 2)))))) ? "00" + DayLength : (ListenerUtil.mutListener.listen(3985) ? (DayLength.length() >= 3) : (ListenerUtil.mutListener.listen(3984) ? (DayLength.length() <= 3) : (ListenerUtil.mutListener.listen(3983) ? (DayLength.length() > 3) : (ListenerUtil.mutListener.listen(3982) ? (DayLength.length() < 3) : (ListenerUtil.mutListener.listen(3981) ? (DayLength.length() != 3) : (DayLength.length() == 3)))))) ? "0" + DayLength : DayLength;
        }
        if (!ListenerUtil.mutListener.listen(3987)) {
            message[3] = (byte) Integer.parseInt(DayLength.substring(0, 2), 16);
        }
        if (!ListenerUtil.mutListener.listen(3988)) {
            message[4] = (byte) Integer.parseInt(DayLength.substring(2, 4), 16);
        }
        if (!ListenerUtil.mutListener.listen(3989)) {
            addChecksum(message);
        }
        if (!ListenerUtil.mutListener.listen(3990)) {
            writeBytes(writeService, writeCharacteristic, message);
        }
    }

    private void synchroniseTime() {
        Calendar cal = Calendar.getInstance();
        byte[] message = new byte[] { (byte) 0xA5, (byte) 0x31, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        if (!ListenerUtil.mutListener.listen(3991)) {
            message[2] = (byte) Integer.parseInt(Long.toHexString(cal.get(Calendar.HOUR_OF_DAY)), 16);
        }
        if (!ListenerUtil.mutListener.listen(3992)) {
            message[3] = (byte) Integer.parseInt(Long.toHexString(cal.get(Calendar.MINUTE)), 16);
        }
        if (!ListenerUtil.mutListener.listen(3993)) {
            message[4] = (byte) Integer.parseInt(Long.toHexString(cal.get(Calendar.SECOND)), 16);
        }
        if (!ListenerUtil.mutListener.listen(3994)) {
            addChecksum(message);
        }
        if (!ListenerUtil.mutListener.listen(3995)) {
            writeBytes(writeService, writeCharacteristic, message);
        }
    }

    private void addChecksum(byte[] message) {
        byte verify = 0;
        if (!ListenerUtil.mutListener.listen(4010)) {
            {
                long _loopCounter35 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(4009) ? (i >= (ListenerUtil.mutListener.listen(4004) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4003) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4002) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4001) ? (message.length + 2) : (message.length - 2)))))) : (ListenerUtil.mutListener.listen(4008) ? (i <= (ListenerUtil.mutListener.listen(4004) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4003) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4002) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4001) ? (message.length + 2) : (message.length - 2)))))) : (ListenerUtil.mutListener.listen(4007) ? (i > (ListenerUtil.mutListener.listen(4004) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4003) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4002) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4001) ? (message.length + 2) : (message.length - 2)))))) : (ListenerUtil.mutListener.listen(4006) ? (i != (ListenerUtil.mutListener.listen(4004) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4003) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4002) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4001) ? (message.length + 2) : (message.length - 2)))))) : (ListenerUtil.mutListener.listen(4005) ? (i == (ListenerUtil.mutListener.listen(4004) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4003) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4002) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4001) ? (message.length + 2) : (message.length - 2)))))) : (i < (ListenerUtil.mutListener.listen(4004) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4003) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4002) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4001) ? (message.length + 2) : (message.length - 2))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                    if (!ListenerUtil.mutListener.listen(4000)) {
                        verify = (byte) ((ListenerUtil.mutListener.listen(3999) ? (verify % message[i]) : (ListenerUtil.mutListener.listen(3998) ? (verify / message[i]) : (ListenerUtil.mutListener.listen(3997) ? (verify * message[i]) : (ListenerUtil.mutListener.listen(3996) ? (verify - message[i]) : (verify + message[i]))))) & 0xff);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4015)) {
            message[(ListenerUtil.mutListener.listen(4014) ? (message.length % 2) : (ListenerUtil.mutListener.listen(4013) ? (message.length / 2) : (ListenerUtil.mutListener.listen(4012) ? (message.length * 2) : (ListenerUtil.mutListener.listen(4011) ? (message.length + 2) : (message.length - 2)))))] = verify;
        }
    }

    private void synchroniseUser() {
        final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
        byte[] message = new byte[] { (byte) 0xA5, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        if (!ListenerUtil.mutListener.listen(4020)) {
            // message[2] = (byte)((selectedUser.getGender().isMale() ? (byte)0x80: (byte)0x00) + 1+selectedUser.getId());
            message[2] = (byte) ((ListenerUtil.mutListener.listen(4019) ? ((selectedUser.getGender().isMale() ? 15 : 0) % 16) : (ListenerUtil.mutListener.listen(4018) ? ((selectedUser.getGender().isMale() ? 15 : 0) / 16) : (ListenerUtil.mutListener.listen(4017) ? ((selectedUser.getGender().isMale() ? 15 : 0) - 16) : (ListenerUtil.mutListener.listen(4016) ? ((selectedUser.getGender().isMale() ? 15 : 0) + 16) : ((selectedUser.getGender().isMale() ? 15 : 0) * 16))))) + selectedUser.getId());
        }
        if (!ListenerUtil.mutListener.listen(4021)) {
            message[3] = (byte) selectedUser.getAge();
        }
        if (!ListenerUtil.mutListener.listen(4022)) {
            message[4] = (byte) selectedUser.getBodyHeight();
        }
        if (!ListenerUtil.mutListener.listen(4023)) {
            addChecksum(message);
        }
        if (!ListenerUtil.mutListener.listen(4024)) {
            writeBytes(writeService, writeCharacteristic, message);
        }
    }
}
