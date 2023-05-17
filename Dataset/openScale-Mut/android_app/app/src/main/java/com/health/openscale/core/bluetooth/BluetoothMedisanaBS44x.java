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
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.utils.Converters;
import java.util.Date;
import java.util.UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothMedisanaBS44x extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0x78b2);

    // indication, read-only
    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0x8a21);

    // indication, read-only
    private final UUID FEATURE_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0x8a22);

    // write-only
    private final UUID CMD_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0x8a81);

    // indication, read-only
    private final UUID CUSTOM5_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0x8a82);

    private ScaleMeasurement btScaleMeasurement;

    private boolean applyOffset;

    // Scale time is in seconds since 2010-01-01
    private static final long SCALE_UNIX_TIMESTAMP_OFFSET = 1262304000;

    public BluetoothMedisanaBS44x(Context context, boolean applyOffset) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3301)) {
            btScaleMeasurement = new ScaleMeasurement();
        }
        if (!ListenerUtil.mutListener.listen(3302)) {
            this.applyOffset = applyOffset;
        }
    }

    @Override
    public String driverName() {
        return "Medisana BS44x";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(3314)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(3303)) {
                        // set indication on for feature characteristic
                        setIndicationOn(WEIGHT_MEASUREMENT_SERVICE, FEATURE_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(3304)) {
                        // set indication on for weight measurement
                        setIndicationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(3305)) {
                        // set indication on for custom5 measurement
                        setIndicationOn(WEIGHT_MEASUREMENT_SERVICE, CUSTOM5_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 3:
                    // send magic number to receive weight data
                    long timestamp = (ListenerUtil.mutListener.listen(3309) ? (new Date().getTime() % 1000) : (ListenerUtil.mutListener.listen(3308) ? (new Date().getTime() * 1000) : (ListenerUtil.mutListener.listen(3307) ? (new Date().getTime() - 1000) : (ListenerUtil.mutListener.listen(3306) ? (new Date().getTime() + 1000) : (new Date().getTime() / 1000)))));
                    if (!ListenerUtil.mutListener.listen(3311)) {
                        if (applyOffset) {
                            if (!ListenerUtil.mutListener.listen(3310)) {
                                timestamp -= SCALE_UNIX_TIMESTAMP_OFFSET;
                            }
                        }
                    }
                    byte[] date = Converters.toInt32Le(timestamp);
                    byte[] magicBytes = new byte[] { (byte) 0x02, date[0], date[1], date[2], date[3] };
                    if (!ListenerUtil.mutListener.listen(3312)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, CMD_MEASUREMENT_CHARACTERISTIC, magicBytes);
                    }
                    break;
                case 4:
                    if (!ListenerUtil.mutListener.listen(3313)) {
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
        if (!ListenerUtil.mutListener.listen(3316)) {
            if (characteristic.equals(WEIGHT_MEASUREMENT_CHARACTERISTIC)) {
                if (!ListenerUtil.mutListener.listen(3315)) {
                    parseWeightData(data);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3319)) {
            if (characteristic.equals(FEATURE_MEASUREMENT_CHARACTERISTIC)) {
                if (!ListenerUtil.mutListener.listen(3317)) {
                    parseFeatureData(data);
                }
                if (!ListenerUtil.mutListener.listen(3318)) {
                    addScaleMeasurement(btScaleMeasurement);
                }
            }
        }
    }

    private void parseWeightData(byte[] weightData) {
        float weight = (ListenerUtil.mutListener.listen(3323) ? (Converters.fromUnsignedInt16Le(weightData, 1) % 100.0f) : (ListenerUtil.mutListener.listen(3322) ? (Converters.fromUnsignedInt16Le(weightData, 1) * 100.0f) : (ListenerUtil.mutListener.listen(3321) ? (Converters.fromUnsignedInt16Le(weightData, 1) - 100.0f) : (ListenerUtil.mutListener.listen(3320) ? (Converters.fromUnsignedInt16Le(weightData, 1) + 100.0f) : (Converters.fromUnsignedInt16Le(weightData, 1) / 100.0f)))));
        long timestamp = Converters.fromUnsignedInt32Le(weightData, 5);
        if (!ListenerUtil.mutListener.listen(3325)) {
            if (applyOffset) {
                if (!ListenerUtil.mutListener.listen(3324)) {
                    timestamp += SCALE_UNIX_TIMESTAMP_OFFSET;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3330)) {
            btScaleMeasurement.setDateTime(new Date((ListenerUtil.mutListener.listen(3329) ? (timestamp % 1000) : (ListenerUtil.mutListener.listen(3328) ? (timestamp / 1000) : (ListenerUtil.mutListener.listen(3327) ? (timestamp - 1000) : (ListenerUtil.mutListener.listen(3326) ? (timestamp + 1000) : (timestamp * 1000)))))));
        }
        if (!ListenerUtil.mutListener.listen(3331)) {
            btScaleMeasurement.setWeight(weight);
        }
    }

    private void parseFeatureData(byte[] featureData) {
        if (!ListenerUtil.mutListener.listen(3332)) {
            // btScaleData.setKCal(Converters.fromUnsignedInt16Le(featureData, 6));
            btScaleMeasurement.setFat(decodeFeature(featureData, 8));
        }
        if (!ListenerUtil.mutListener.listen(3333)) {
            btScaleMeasurement.setWater(decodeFeature(featureData, 10));
        }
        if (!ListenerUtil.mutListener.listen(3334)) {
            btScaleMeasurement.setMuscle(decodeFeature(featureData, 12));
        }
        if (!ListenerUtil.mutListener.listen(3335)) {
            btScaleMeasurement.setBone(decodeFeature(featureData, 14));
        }
    }

    private float decodeFeature(byte[] featureData, int offset) {
        return (ListenerUtil.mutListener.listen(3339) ? ((Converters.fromUnsignedInt16Le(featureData, offset) & 0x0FFF) % 10.0f) : (ListenerUtil.mutListener.listen(3338) ? ((Converters.fromUnsignedInt16Le(featureData, offset) & 0x0FFF) * 10.0f) : (ListenerUtil.mutListener.listen(3337) ? ((Converters.fromUnsignedInt16Le(featureData, offset) & 0x0FFF) - 10.0f) : (ListenerUtil.mutListener.listen(3336) ? ((Converters.fromUnsignedInt16Le(featureData, offset) & 0x0FFF) + 10.0f) : ((Converters.fromUnsignedInt16Le(featureData, offset) & 0x0FFF) / 10.0f)))));
    }
}
