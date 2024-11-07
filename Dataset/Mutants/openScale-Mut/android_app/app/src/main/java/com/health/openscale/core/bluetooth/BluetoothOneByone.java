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
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.lib.OneByoneLib;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.util.Calendar;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothOneByone extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0xfff0);

    // notify
    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC_BODY_COMPOSITION = BluetoothGattUuid.fromShortCode(0xfff4);

    // write only
    private final UUID CMD_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xfff1);

    // if true, resume after receiving acknowledgement
    private boolean waitAck = false;

    // processing real-time vs historic measurement
    private boolean historicMeasurement = false;

    // number of historic measurements received
    private int noHistoric = 0;

    // don't save any measurements closer than 3 seconds to each other
    private Calendar lastDateTime;

    private final int DATE_TIME_THRESHOLD = 3000;

    public BluetoothOneByone(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3562)) {
            lastDateTime = Calendar.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(3563)) {
            lastDateTime.set(2000, 1, 1);
        }
    }

    @Override
    public String driverName() {
        return "1byone";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(3588)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(3564)) {
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC_BODY_COMPOSITION);
                    }
                    break;
                case 1:
                    ScaleUser currentUser = OpenScale.getInstance().getSelectedScaleUser();
                    // kg
                    byte unit = 0x00;
                    if (!ListenerUtil.mutListener.listen(3567)) {
                        switch(currentUser.getScaleUnit()) {
                            case LB:
                                if (!ListenerUtil.mutListener.listen(3565)) {
                                    unit = 0x01;
                                }
                                break;
                            case ST:
                                if (!ListenerUtil.mutListener.listen(3566)) {
                                    unit = 0x02;
                                }
                                break;
                        }
                    }
                    byte group = 0x01;
                    byte[] magicBytes = { (byte) 0xfd, (byte) 0x37, unit, group, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
                    if (!ListenerUtil.mutListener.listen(3576)) {
                        magicBytes[(ListenerUtil.mutListener.listen(3571) ? (magicBytes.length % 1) : (ListenerUtil.mutListener.listen(3570) ? (magicBytes.length / 1) : (ListenerUtil.mutListener.listen(3569) ? (magicBytes.length * 1) : (ListenerUtil.mutListener.listen(3568) ? (magicBytes.length + 1) : (magicBytes.length - 1)))))] = xorChecksum(magicBytes, 0, (ListenerUtil.mutListener.listen(3575) ? (magicBytes.length % 1) : (ListenerUtil.mutListener.listen(3574) ? (magicBytes.length / 1) : (ListenerUtil.mutListener.listen(3573) ? (magicBytes.length * 1) : (ListenerUtil.mutListener.listen(3572) ? (magicBytes.length + 1) : (magicBytes.length - 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(3577)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, magicBytes);
                    }
                    break;
                case 2:
                    Calendar dt = Calendar.getInstance();
                    final byte[] setClockCmd = { (byte) 0xf1, (byte) (dt.get(Calendar.YEAR) >> 8), (byte) (dt.get(Calendar.YEAR) & 255), (byte) ((ListenerUtil.mutListener.listen(3581) ? (dt.get(Calendar.MONTH) % 1) : (ListenerUtil.mutListener.listen(3580) ? (dt.get(Calendar.MONTH) / 1) : (ListenerUtil.mutListener.listen(3579) ? (dt.get(Calendar.MONTH) * 1) : (ListenerUtil.mutListener.listen(3578) ? (dt.get(Calendar.MONTH) - 1) : (dt.get(Calendar.MONTH) + 1)))))), (byte) dt.get(Calendar.DAY_OF_MONTH), (byte) dt.get(Calendar.HOUR_OF_DAY), (byte) dt.get(Calendar.MINUTE), (byte) dt.get(Calendar.SECOND) };
                    if (!ListenerUtil.mutListener.listen(3582)) {
                        waitAck = true;
                    }
                    if (!ListenerUtil.mutListener.listen(3583)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, setClockCmd);
                    }
                    if (!ListenerUtil.mutListener.listen(3584)) {
                        // we will resume after receiving acknowledgement f1 00
                        stopMachineState();
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(3585)) {
                        // request historic measurements; they are followed by real-time measurements
                        historicMeasurement = true;
                    }
                    final byte[] getHistoryCmd = { (byte) 0xf2, (byte) 0x00 };
                    if (!ListenerUtil.mutListener.listen(3586)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, getHistoryCmd);
                    }
                    // 2-byte notification value f2 00 follows last historic measurement
                    break;
                case 4:
                    if (!ListenerUtil.mutListener.listen(3587)) {
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
        if (!ListenerUtil.mutListener.listen(3589)) {
            if (data == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3651)) {
            // if data is valid data
            if ((ListenerUtil.mutListener.listen(3600) ? ((ListenerUtil.mutListener.listen(3594) ? (data.length <= 11) : (ListenerUtil.mutListener.listen(3593) ? (data.length > 11) : (ListenerUtil.mutListener.listen(3592) ? (data.length < 11) : (ListenerUtil.mutListener.listen(3591) ? (data.length != 11) : (ListenerUtil.mutListener.listen(3590) ? (data.length == 11) : (data.length >= 11)))))) || (ListenerUtil.mutListener.listen(3599) ? (data[0] >= (byte) 0xcf) : (ListenerUtil.mutListener.listen(3598) ? (data[0] <= (byte) 0xcf) : (ListenerUtil.mutListener.listen(3597) ? (data[0] > (byte) 0xcf) : (ListenerUtil.mutListener.listen(3596) ? (data[0] < (byte) 0xcf) : (ListenerUtil.mutListener.listen(3595) ? (data[0] != (byte) 0xcf) : (data[0] == (byte) 0xcf))))))) : ((ListenerUtil.mutListener.listen(3594) ? (data.length <= 11) : (ListenerUtil.mutListener.listen(3593) ? (data.length > 11) : (ListenerUtil.mutListener.listen(3592) ? (data.length < 11) : (ListenerUtil.mutListener.listen(3591) ? (data.length != 11) : (ListenerUtil.mutListener.listen(3590) ? (data.length == 11) : (data.length >= 11)))))) && (ListenerUtil.mutListener.listen(3599) ? (data[0] >= (byte) 0xcf) : (ListenerUtil.mutListener.listen(3598) ? (data[0] <= (byte) 0xcf) : (ListenerUtil.mutListener.listen(3597) ? (data[0] > (byte) 0xcf) : (ListenerUtil.mutListener.listen(3596) ? (data[0] < (byte) 0xcf) : (ListenerUtil.mutListener.listen(3595) ? (data[0] != (byte) 0xcf) : (data[0] == (byte) 0xcf))))))))) {
                if (!ListenerUtil.mutListener.listen(3649)) {
                    if (historicMeasurement) {
                        if (!ListenerUtil.mutListener.listen(3648)) {
                            ++noHistoric;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3650)) {
                    parseBytes(data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3601)) {
                    // f2 01 clearHistoryCmd acknowledgement
                    Timber.d("received bytes [%s]", byteInHex(data));
                }
                if (!ListenerUtil.mutListener.listen(3647)) {
                    if ((ListenerUtil.mutListener.listen(3619) ? ((ListenerUtil.mutListener.listen(3613) ? ((ListenerUtil.mutListener.listen(3607) ? (waitAck || (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2))))))) : (waitAck && (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2)))))))) || (ListenerUtil.mutListener.listen(3612) ? (data[0] >= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3611) ? (data[0] <= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3610) ? (data[0] > (byte) 0xf1) : (ListenerUtil.mutListener.listen(3609) ? (data[0] < (byte) 0xf1) : (ListenerUtil.mutListener.listen(3608) ? (data[0] != (byte) 0xf1) : (data[0] == (byte) 0xf1))))))) : ((ListenerUtil.mutListener.listen(3607) ? (waitAck || (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2))))))) : (waitAck && (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2)))))))) && (ListenerUtil.mutListener.listen(3612) ? (data[0] >= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3611) ? (data[0] <= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3610) ? (data[0] > (byte) 0xf1) : (ListenerUtil.mutListener.listen(3609) ? (data[0] < (byte) 0xf1) : (ListenerUtil.mutListener.listen(3608) ? (data[0] != (byte) 0xf1) : (data[0] == (byte) 0xf1)))))))) || (ListenerUtil.mutListener.listen(3618) ? (data[1] >= 0) : (ListenerUtil.mutListener.listen(3617) ? (data[1] <= 0) : (ListenerUtil.mutListener.listen(3616) ? (data[1] > 0) : (ListenerUtil.mutListener.listen(3615) ? (data[1] < 0) : (ListenerUtil.mutListener.listen(3614) ? (data[1] != 0) : (data[1] == 0))))))) : ((ListenerUtil.mutListener.listen(3613) ? ((ListenerUtil.mutListener.listen(3607) ? (waitAck || (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2))))))) : (waitAck && (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2)))))))) || (ListenerUtil.mutListener.listen(3612) ? (data[0] >= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3611) ? (data[0] <= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3610) ? (data[0] > (byte) 0xf1) : (ListenerUtil.mutListener.listen(3609) ? (data[0] < (byte) 0xf1) : (ListenerUtil.mutListener.listen(3608) ? (data[0] != (byte) 0xf1) : (data[0] == (byte) 0xf1))))))) : ((ListenerUtil.mutListener.listen(3607) ? (waitAck || (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2))))))) : (waitAck && (ListenerUtil.mutListener.listen(3606) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3605) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3604) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3603) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3602) ? (data.length != 2) : (data.length == 2)))))))) && (ListenerUtil.mutListener.listen(3612) ? (data[0] >= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3611) ? (data[0] <= (byte) 0xf1) : (ListenerUtil.mutListener.listen(3610) ? (data[0] > (byte) 0xf1) : (ListenerUtil.mutListener.listen(3609) ? (data[0] < (byte) 0xf1) : (ListenerUtil.mutListener.listen(3608) ? (data[0] != (byte) 0xf1) : (data[0] == (byte) 0xf1)))))))) && (ListenerUtil.mutListener.listen(3618) ? (data[1] >= 0) : (ListenerUtil.mutListener.listen(3617) ? (data[1] <= 0) : (ListenerUtil.mutListener.listen(3616) ? (data[1] > 0) : (ListenerUtil.mutListener.listen(3615) ? (data[1] < 0) : (ListenerUtil.mutListener.listen(3614) ? (data[1] != 0) : (data[1] == 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(3645)) {
                            waitAck = false;
                        }
                        if (!ListenerUtil.mutListener.listen(3646)) {
                            resumeMachineState();
                        }
                    } else if ((ListenerUtil.mutListener.listen(3636) ? ((ListenerUtil.mutListener.listen(3630) ? ((ListenerUtil.mutListener.listen(3624) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3623) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3622) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3621) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3620) ? (data.length != 2) : (data.length == 2)))))) || (ListenerUtil.mutListener.listen(3629) ? (data[0] >= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3628) ? (data[0] <= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3627) ? (data[0] > (byte) 0xf2) : (ListenerUtil.mutListener.listen(3626) ? (data[0] < (byte) 0xf2) : (ListenerUtil.mutListener.listen(3625) ? (data[0] != (byte) 0xf2) : (data[0] == (byte) 0xf2))))))) : ((ListenerUtil.mutListener.listen(3624) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3623) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3622) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3621) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3620) ? (data.length != 2) : (data.length == 2)))))) && (ListenerUtil.mutListener.listen(3629) ? (data[0] >= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3628) ? (data[0] <= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3627) ? (data[0] > (byte) 0xf2) : (ListenerUtil.mutListener.listen(3626) ? (data[0] < (byte) 0xf2) : (ListenerUtil.mutListener.listen(3625) ? (data[0] != (byte) 0xf2) : (data[0] == (byte) 0xf2)))))))) || (ListenerUtil.mutListener.listen(3635) ? (data[1] >= 0) : (ListenerUtil.mutListener.listen(3634) ? (data[1] <= 0) : (ListenerUtil.mutListener.listen(3633) ? (data[1] > 0) : (ListenerUtil.mutListener.listen(3632) ? (data[1] < 0) : (ListenerUtil.mutListener.listen(3631) ? (data[1] != 0) : (data[1] == 0))))))) : ((ListenerUtil.mutListener.listen(3630) ? ((ListenerUtil.mutListener.listen(3624) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3623) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3622) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3621) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3620) ? (data.length != 2) : (data.length == 2)))))) || (ListenerUtil.mutListener.listen(3629) ? (data[0] >= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3628) ? (data[0] <= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3627) ? (data[0] > (byte) 0xf2) : (ListenerUtil.mutListener.listen(3626) ? (data[0] < (byte) 0xf2) : (ListenerUtil.mutListener.listen(3625) ? (data[0] != (byte) 0xf2) : (data[0] == (byte) 0xf2))))))) : ((ListenerUtil.mutListener.listen(3624) ? (data.length >= 2) : (ListenerUtil.mutListener.listen(3623) ? (data.length <= 2) : (ListenerUtil.mutListener.listen(3622) ? (data.length > 2) : (ListenerUtil.mutListener.listen(3621) ? (data.length < 2) : (ListenerUtil.mutListener.listen(3620) ? (data.length != 2) : (data.length == 2)))))) && (ListenerUtil.mutListener.listen(3629) ? (data[0] >= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3628) ? (data[0] <= (byte) 0xf2) : (ListenerUtil.mutListener.listen(3627) ? (data[0] > (byte) 0xf2) : (ListenerUtil.mutListener.listen(3626) ? (data[0] < (byte) 0xf2) : (ListenerUtil.mutListener.listen(3625) ? (data[0] != (byte) 0xf2) : (data[0] == (byte) 0xf2)))))))) && (ListenerUtil.mutListener.listen(3635) ? (data[1] >= 0) : (ListenerUtil.mutListener.listen(3634) ? (data[1] <= 0) : (ListenerUtil.mutListener.listen(3633) ? (data[1] > 0) : (ListenerUtil.mutListener.listen(3632) ? (data[1] < 0) : (ListenerUtil.mutListener.listen(3631) ? (data[1] != 0) : (data[1] == 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(3637)) {
                            historicMeasurement = false;
                        }
                        if (!ListenerUtil.mutListener.listen(3644)) {
                            if ((ListenerUtil.mutListener.listen(3642) ? (noHistoric >= 0) : (ListenerUtil.mutListener.listen(3641) ? (noHistoric <= 0) : (ListenerUtil.mutListener.listen(3640) ? (noHistoric < 0) : (ListenerUtil.mutListener.listen(3639) ? (noHistoric != 0) : (ListenerUtil.mutListener.listen(3638) ? (noHistoric == 0) : (noHistoric > 0))))))) {
                                final byte[] clearHistoryCmd = { (byte) 0xf2, (byte) 0x01 };
                                if (!ListenerUtil.mutListener.listen(3643)) {
                                    writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, clearHistoryCmd);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void parseBytes(byte[] weightBytes) {
        float weight = (ListenerUtil.mutListener.listen(3655) ? (Converters.fromUnsignedInt16Le(weightBytes, 3) % 100.0f) : (ListenerUtil.mutListener.listen(3654) ? (Converters.fromUnsignedInt16Le(weightBytes, 3) * 100.0f) : (ListenerUtil.mutListener.listen(3653) ? (Converters.fromUnsignedInt16Le(weightBytes, 3) - 100.0f) : (ListenerUtil.mutListener.listen(3652) ? (Converters.fromUnsignedInt16Le(weightBytes, 3) + 100.0f) : (Converters.fromUnsignedInt16Le(weightBytes, 3) / 100.0f)))));
        int impedanceCoeff = Converters.fromUnsignedInt24Le(weightBytes, 5);
        int impedanceValue = (ListenerUtil.mutListener.listen(3663) ? ((ListenerUtil.mutListener.listen(3659) ? (weightBytes[5] % weightBytes[6]) : (ListenerUtil.mutListener.listen(3658) ? (weightBytes[5] / weightBytes[6]) : (ListenerUtil.mutListener.listen(3657) ? (weightBytes[5] * weightBytes[6]) : (ListenerUtil.mutListener.listen(3656) ? (weightBytes[5] - weightBytes[6]) : (weightBytes[5] + weightBytes[6]))))) % weightBytes[7]) : (ListenerUtil.mutListener.listen(3662) ? ((ListenerUtil.mutListener.listen(3659) ? (weightBytes[5] % weightBytes[6]) : (ListenerUtil.mutListener.listen(3658) ? (weightBytes[5] / weightBytes[6]) : (ListenerUtil.mutListener.listen(3657) ? (weightBytes[5] * weightBytes[6]) : (ListenerUtil.mutListener.listen(3656) ? (weightBytes[5] - weightBytes[6]) : (weightBytes[5] + weightBytes[6]))))) / weightBytes[7]) : (ListenerUtil.mutListener.listen(3661) ? ((ListenerUtil.mutListener.listen(3659) ? (weightBytes[5] % weightBytes[6]) : (ListenerUtil.mutListener.listen(3658) ? (weightBytes[5] / weightBytes[6]) : (ListenerUtil.mutListener.listen(3657) ? (weightBytes[5] * weightBytes[6]) : (ListenerUtil.mutListener.listen(3656) ? (weightBytes[5] - weightBytes[6]) : (weightBytes[5] + weightBytes[6]))))) * weightBytes[7]) : (ListenerUtil.mutListener.listen(3660) ? ((ListenerUtil.mutListener.listen(3659) ? (weightBytes[5] % weightBytes[6]) : (ListenerUtil.mutListener.listen(3658) ? (weightBytes[5] / weightBytes[6]) : (ListenerUtil.mutListener.listen(3657) ? (weightBytes[5] * weightBytes[6]) : (ListenerUtil.mutListener.listen(3656) ? (weightBytes[5] - weightBytes[6]) : (weightBytes[5] + weightBytes[6]))))) - weightBytes[7]) : ((ListenerUtil.mutListener.listen(3659) ? (weightBytes[5] % weightBytes[6]) : (ListenerUtil.mutListener.listen(3658) ? (weightBytes[5] / weightBytes[6]) : (ListenerUtil.mutListener.listen(3657) ? (weightBytes[5] * weightBytes[6]) : (ListenerUtil.mutListener.listen(3656) ? (weightBytes[5] - weightBytes[6]) : (weightBytes[5] + weightBytes[6]))))) + weightBytes[7])))));
        boolean impedancePresent = (ListenerUtil.mutListener.listen(3674) ? (((ListenerUtil.mutListener.listen(3668) ? (weightBytes[9] >= 1) : (ListenerUtil.mutListener.listen(3667) ? (weightBytes[9] <= 1) : (ListenerUtil.mutListener.listen(3666) ? (weightBytes[9] > 1) : (ListenerUtil.mutListener.listen(3665) ? (weightBytes[9] < 1) : (ListenerUtil.mutListener.listen(3664) ? (weightBytes[9] == 1) : (weightBytes[9] != 1))))))) || ((ListenerUtil.mutListener.listen(3673) ? (impedanceCoeff >= 0) : (ListenerUtil.mutListener.listen(3672) ? (impedanceCoeff <= 0) : (ListenerUtil.mutListener.listen(3671) ? (impedanceCoeff > 0) : (ListenerUtil.mutListener.listen(3670) ? (impedanceCoeff < 0) : (ListenerUtil.mutListener.listen(3669) ? (impedanceCoeff == 0) : (impedanceCoeff != 0)))))))) : (((ListenerUtil.mutListener.listen(3668) ? (weightBytes[9] >= 1) : (ListenerUtil.mutListener.listen(3667) ? (weightBytes[9] <= 1) : (ListenerUtil.mutListener.listen(3666) ? (weightBytes[9] > 1) : (ListenerUtil.mutListener.listen(3665) ? (weightBytes[9] < 1) : (ListenerUtil.mutListener.listen(3664) ? (weightBytes[9] == 1) : (weightBytes[9] != 1))))))) && ((ListenerUtil.mutListener.listen(3673) ? (impedanceCoeff >= 0) : (ListenerUtil.mutListener.listen(3672) ? (impedanceCoeff <= 0) : (ListenerUtil.mutListener.listen(3671) ? (impedanceCoeff > 0) : (ListenerUtil.mutListener.listen(3670) ? (impedanceCoeff < 0) : (ListenerUtil.mutListener.listen(3669) ? (impedanceCoeff == 0) : (impedanceCoeff != 0)))))))));
        boolean dateTimePresent = (ListenerUtil.mutListener.listen(3679) ? (weightBytes.length <= 18) : (ListenerUtil.mutListener.listen(3678) ? (weightBytes.length > 18) : (ListenerUtil.mutListener.listen(3677) ? (weightBytes.length < 18) : (ListenerUtil.mutListener.listen(3676) ? (weightBytes.length != 18) : (ListenerUtil.mutListener.listen(3675) ? (weightBytes.length == 18) : (weightBytes.length >= 18))))));
        if (!ListenerUtil.mutListener.listen(3682)) {
            if ((ListenerUtil.mutListener.listen(3681) ? (!impedancePresent && ((ListenerUtil.mutListener.listen(3680) ? (!dateTimePresent || historicMeasurement) : (!dateTimePresent && historicMeasurement)))) : (!impedancePresent || ((ListenerUtil.mutListener.listen(3680) ? (!dateTimePresent || historicMeasurement) : (!dateTimePresent && historicMeasurement)))))) {
                // unwanted, no impedance or historic measurement w/o time-stamp
                return;
            }
        }
        Calendar dateTime = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(3688)) {
            if (dateTimePresent) {
                if (!ListenerUtil.mutListener.listen(3687)) {
                    // 18-byte or longer measurements contain date and time, used in history
                    dateTime.set(Converters.fromUnsignedInt16Be(weightBytes, 11), (ListenerUtil.mutListener.listen(3686) ? (weightBytes[13] % 1) : (ListenerUtil.mutListener.listen(3685) ? (weightBytes[13] / 1) : (ListenerUtil.mutListener.listen(3684) ? (weightBytes[13] * 1) : (ListenerUtil.mutListener.listen(3683) ? (weightBytes[13] + 1) : (weightBytes[13] - 1))))), weightBytes[14], weightBytes[15], weightBytes[16], weightBytes[17]);
                }
            }
        }
        final ScaleUser scaleUser = OpenScale.getInstance().getSelectedScaleUser();
        if (!ListenerUtil.mutListener.listen(3689)) {
            Timber.d("received bytes [%s]", byteInHex(weightBytes));
        }
        if (!ListenerUtil.mutListener.listen(3690)) {
            Timber.d("received decrypted bytes [weight: %.2f, impedanceCoeff: %d, impedanceValue: %d]", weight, impedanceCoeff, impedanceValue);
        }
        if (!ListenerUtil.mutListener.listen(3691)) {
            Timber.d("user [%s]", scaleUser);
        }
        int sex = 0, peopleType = 0;
        if (!ListenerUtil.mutListener.listen(3694)) {
            if (scaleUser.getGender() == Converters.Gender.MALE) {
                if (!ListenerUtil.mutListener.listen(3693)) {
                    sex = 1;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3692)) {
                    sex = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3700)) {
            switch(scaleUser.getActivityLevel()) {
                case SEDENTARY:
                    if (!ListenerUtil.mutListener.listen(3695)) {
                        peopleType = 0;
                    }
                    break;
                case MILD:
                    if (!ListenerUtil.mutListener.listen(3696)) {
                        peopleType = 0;
                    }
                    break;
                case MODERATE:
                    if (!ListenerUtil.mutListener.listen(3697)) {
                        peopleType = 1;
                    }
                    break;
                case HEAVY:
                    if (!ListenerUtil.mutListener.listen(3698)) {
                        peopleType = 2;
                    }
                    break;
                case EXTREME:
                    if (!ListenerUtil.mutListener.listen(3699)) {
                        peopleType = 2;
                    }
                    break;
            }
        }
        OneByoneLib oneByoneLib = new OneByoneLib(sex, scaleUser.getAge(), scaleUser.getBodyHeight(), peopleType);
        ScaleMeasurement scaleBtData = new ScaleMeasurement();
        if (!ListenerUtil.mutListener.listen(3701)) {
            scaleBtData.setWeight(weight);
        }
        try {
            if (!ListenerUtil.mutListener.listen(3704)) {
                dateTime.setLenient(false);
            }
            if (!ListenerUtil.mutListener.listen(3705)) {
                scaleBtData.setDateTime(dateTime.getTime());
            }
            if (!ListenerUtil.mutListener.listen(3706)) {
                scaleBtData.setFat(oneByoneLib.getBodyFat(weight, impedanceCoeff));
            }
            if (!ListenerUtil.mutListener.listen(3707)) {
                scaleBtData.setWater(oneByoneLib.getWater(scaleBtData.getFat()));
            }
            if (!ListenerUtil.mutListener.listen(3708)) {
                scaleBtData.setBone(oneByoneLib.getBoneMass(weight, impedanceValue));
            }
            if (!ListenerUtil.mutListener.listen(3709)) {
                scaleBtData.setVisceralFat(oneByoneLib.getVisceralFat(weight));
            }
            if (!ListenerUtil.mutListener.listen(3710)) {
                scaleBtData.setMuscle(oneByoneLib.getMuscle(weight, scaleBtData.getFat(), scaleBtData.getBone()));
            }
            if (!ListenerUtil.mutListener.listen(3711)) {
                Timber.d("scale measurement [%s]", scaleBtData);
            }
            if (!ListenerUtil.mutListener.listen(3721)) {
                if ((ListenerUtil.mutListener.listen(3720) ? ((ListenerUtil.mutListener.listen(3715) ? (dateTime.getTimeInMillis() % lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3714) ? (dateTime.getTimeInMillis() / lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3713) ? (dateTime.getTimeInMillis() * lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3712) ? (dateTime.getTimeInMillis() + lastDateTime.getTimeInMillis()) : (dateTime.getTimeInMillis() - lastDateTime.getTimeInMillis()))))) >= DATE_TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(3719) ? ((ListenerUtil.mutListener.listen(3715) ? (dateTime.getTimeInMillis() % lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3714) ? (dateTime.getTimeInMillis() / lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3713) ? (dateTime.getTimeInMillis() * lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3712) ? (dateTime.getTimeInMillis() + lastDateTime.getTimeInMillis()) : (dateTime.getTimeInMillis() - lastDateTime.getTimeInMillis()))))) <= DATE_TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(3718) ? ((ListenerUtil.mutListener.listen(3715) ? (dateTime.getTimeInMillis() % lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3714) ? (dateTime.getTimeInMillis() / lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3713) ? (dateTime.getTimeInMillis() * lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3712) ? (dateTime.getTimeInMillis() + lastDateTime.getTimeInMillis()) : (dateTime.getTimeInMillis() - lastDateTime.getTimeInMillis()))))) > DATE_TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(3717) ? ((ListenerUtil.mutListener.listen(3715) ? (dateTime.getTimeInMillis() % lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3714) ? (dateTime.getTimeInMillis() / lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3713) ? (dateTime.getTimeInMillis() * lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3712) ? (dateTime.getTimeInMillis() + lastDateTime.getTimeInMillis()) : (dateTime.getTimeInMillis() - lastDateTime.getTimeInMillis()))))) != DATE_TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(3716) ? ((ListenerUtil.mutListener.listen(3715) ? (dateTime.getTimeInMillis() % lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3714) ? (dateTime.getTimeInMillis() / lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3713) ? (dateTime.getTimeInMillis() * lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3712) ? (dateTime.getTimeInMillis() + lastDateTime.getTimeInMillis()) : (dateTime.getTimeInMillis() - lastDateTime.getTimeInMillis()))))) == DATE_TIME_THRESHOLD) : ((ListenerUtil.mutListener.listen(3715) ? (dateTime.getTimeInMillis() % lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3714) ? (dateTime.getTimeInMillis() / lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3713) ? (dateTime.getTimeInMillis() * lastDateTime.getTimeInMillis()) : (ListenerUtil.mutListener.listen(3712) ? (dateTime.getTimeInMillis() + lastDateTime.getTimeInMillis()) : (dateTime.getTimeInMillis() - lastDateTime.getTimeInMillis()))))) < DATE_TIME_THRESHOLD))))))) {
                    // don't save measurements too close to each other
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(3722)) {
                lastDateTime = dateTime;
            }
            if (!ListenerUtil.mutListener.listen(3723)) {
                addScaleMeasurement(scaleBtData);
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(3703)) {
                if (historicMeasurement) {
                    if (!ListenerUtil.mutListener.listen(3702)) {
                        Timber.d("invalid time-stamp: year %d, month %d, day %d, hour %d, minute %d, second %d", Converters.fromUnsignedInt16Be(weightBytes, 11), weightBytes[13], weightBytes[14], weightBytes[15], weightBytes[16], weightBytes[17]);
                    }
                    // discard historic measurement with invalid time-stamp
                    return;
                }
            }
        }
    }
}
