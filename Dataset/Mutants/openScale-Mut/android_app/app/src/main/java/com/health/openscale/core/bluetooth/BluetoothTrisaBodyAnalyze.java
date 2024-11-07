/*  Copyright (C) 2018  Maks Verver <maks@verver.ch>
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
import androidx.annotation.Nullable;
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

/**
 * Driver for Trisa Body Analyze 4.0.
 *
 * @see <a href="https://github.com/maksverver/trisa-body-analyze">Protocol details</a>
 */
public class BluetoothTrisaBodyAnalyze extends BluetoothCommunication {

    // GATT service UUID
    private static final UUID WEIGHT_SCALE_SERVICE_UUID = BluetoothGattUuid.fromShortCode(0x7802);

    // GATT service characteristics.
    private static final UUID MEASUREMENT_CHARACTERISTIC_UUID = BluetoothGattUuid.fromShortCode(0x8a21);

    private static final UUID DOWNLOAD_COMMAND_CHARACTERISTIC_UUID = BluetoothGattUuid.fromShortCode(0x8a81);

    private static final UUID UPLOAD_COMMAND_CHARACTERISTIC_UUID = BluetoothGattUuid.fromShortCode(0x8a82);

    // Commands sent from device to host.
    private static final byte UPLOAD_PASSWORD = (byte) 0xa0;

    private static final byte UPLOAD_CHALLENGE = (byte) 0xa1;

    // Commands sent from host to device.
    private static final byte DOWNLOAD_INFORMATION_UTC_COMMAND = 0x02;

    private static final byte DOWNLOAD_INFORMATION_RESULT_COMMAND = 0x20;

    private static final byte DOWNLOAD_INFORMATION_BROADCAST_ID_COMMAND = 0x21;

    private static final byte DOWNLOAD_INFORMATION_ENABLE_DISCONNECT_COMMAND = 0x22;

    /**
     * Broadcast id, which the scale will include in its Bluetooth alias. This must be set to some
     * value to complete the pairing process (though the actual value doesn't seem to matter).
     */
    private static final int BROADCAST_ID = 0;

    /**
     * Prefix for {@link SharedPreferences} keys that store device passwords.
     *
     * @see #loadDevicePassword
     * @see #saveDevicePassword
     */
    private static final String SHARED_PREFERENCES_PASSWORD_KEY_PREFIX = "trisa_body_analyze_password_for_device_";

    /**
     * ASCII string that identifies the connected device (i.e. the hex-encoded Bluetooth MAC
     * address). Used in shared preference keys to store per-device settings.
     */
    @Nullable
    private String deviceId;

    /**
     * Device password as a 32-bit integer, or {@code null} if the device password is unknown.
     */
    @Nullable
    private static Integer password;

    /**
     * Indicates whether we are pairing. If this is {@code true} then we have written the
     * set-broadcast-id command, and should disconnect after the write succeeds.
     *
     * @see #onPasswordReceived
     * @see #onNextStep
     */
    private boolean pairing = false;

    /**
     *  Timestamp of 2010-01-01 00:00:00 UTC (or local time?)
     */
    private static final long TIMESTAMP_OFFSET_SECONDS = 1262304000L;

    public BluetoothTrisaBodyAnalyze(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Trisa Body Analyze 4.0";
    }

    @Override
    public void connect(String hwAddress) {
        if (!ListenerUtil.mutListener.listen(4403)) {
            Timber.i("connect(\"%s\")", hwAddress);
        }
        if (!ListenerUtil.mutListener.listen(4404)) {
            super.connect(hwAddress);
        }
        if (!ListenerUtil.mutListener.listen(4405)) {
            this.deviceId = hwAddress;
        }
        if (!ListenerUtil.mutListener.listen(4406)) {
            this.password = loadDevicePassword(context, hwAddress);
        }
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(4407)) {
            Timber.i("onNextStep(%d)", stepNr);
        }
        if (!ListenerUtil.mutListener.listen(4414)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(4408)) {
                        // Register for notifications of the measurement characteristic.
                        setIndicationOn(WEIGHT_SCALE_SERVICE_UUID, MEASUREMENT_CHARACTERISTIC_UUID);
                    }
                    // more commands follow
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(4409)) {
                        // to handle pairing correctly.
                        setIndicationOn(WEIGHT_SCALE_SERVICE_UUID, UPLOAD_COMMAND_CHARACTERISTIC_UUID);
                    }
                    break;
                case 2:
                    if (!ListenerUtil.mutListener.listen(4412)) {
                        // This state is triggered by the write in onPasswordReceived()
                        if (pairing) {
                            if (!ListenerUtil.mutListener.listen(4410)) {
                                pairing = false;
                            }
                            if (!ListenerUtil.mutListener.listen(4411)) {
                                disconnect();
                            }
                        }
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(4413)) {
                        writeCommand(DOWNLOAD_INFORMATION_ENABLE_DISCONNECT_COMMAND);
                    }
                    break;
                default:
                    // no more commands
                    return false;
            }
        }
        return true;
    }

    @Override
    protected void onBluetoothNotify(UUID characteristic, byte[] value) {
        if (!ListenerUtil.mutListener.listen(4415)) {
            Timber.i("onBluetoothdataChange() characteristic=%s value=%s", characteristic, byteInHex(value));
        }
        if (!ListenerUtil.mutListener.listen(4427)) {
            if (UPLOAD_COMMAND_CHARACTERISTIC_UUID.equals(characteristic)) {
                if (!ListenerUtil.mutListener.listen(4422)) {
                    if ((ListenerUtil.mutListener.listen(4420) ? (value.length >= 0) : (ListenerUtil.mutListener.listen(4419) ? (value.length <= 0) : (ListenerUtil.mutListener.listen(4418) ? (value.length > 0) : (ListenerUtil.mutListener.listen(4417) ? (value.length < 0) : (ListenerUtil.mutListener.listen(4416) ? (value.length != 0) : (value.length == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(4421)) {
                            Timber.e("Missing command byte!");
                        }
                        return;
                    }
                }
                byte command = value[0];
                if (!ListenerUtil.mutListener.listen(4426)) {
                    switch(command) {
                        case UPLOAD_PASSWORD:
                            if (!ListenerUtil.mutListener.listen(4423)) {
                                onPasswordReceived(value);
                            }
                            break;
                        case UPLOAD_CHALLENGE:
                            if (!ListenerUtil.mutListener.listen(4424)) {
                                onChallengeReceived(value);
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(4425)) {
                                Timber.e("Unknown command byte received: %d", command);
                            }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4429)) {
            if (MEASUREMENT_CHARACTERISTIC_UUID.equals(characteristic)) {
                if (!ListenerUtil.mutListener.listen(4428)) {
                    onScaleMeasurumentReceived(value);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4430)) {
            Timber.e("Unknown characteristic changed: %s", characteristic);
        }
    }

    private void onPasswordReceived(byte[] data) {
        if (!ListenerUtil.mutListener.listen(4437)) {
            if ((ListenerUtil.mutListener.listen(4435) ? (data.length >= 5) : (ListenerUtil.mutListener.listen(4434) ? (data.length <= 5) : (ListenerUtil.mutListener.listen(4433) ? (data.length > 5) : (ListenerUtil.mutListener.listen(4432) ? (data.length != 5) : (ListenerUtil.mutListener.listen(4431) ? (data.length == 5) : (data.length < 5))))))) {
                if (!ListenerUtil.mutListener.listen(4436)) {
                    Timber.e("Password data too short");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4438)) {
            password = Converters.fromSignedInt32Le(data, 1);
        }
        if (!ListenerUtil.mutListener.listen(4442)) {
            if (deviceId == null) {
                if (!ListenerUtil.mutListener.listen(4441)) {
                    Timber.e("Can't save password: device id not set!");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4439)) {
                    Timber.i("Saving password '%08x' for device id '%s'", password, deviceId);
                }
                if (!ListenerUtil.mutListener.listen(4440)) {
                    saveDevicePassword(context, deviceId, password);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4443)) {
            sendMessage(R.string.trisa_scale_pairing_succeeded, null);
        }
        if (!ListenerUtil.mutListener.listen(4444)) {
            // which will disconnect when `pairing == true`.
            pairing = true;
        }
        if (!ListenerUtil.mutListener.listen(4445)) {
            writeCommand(DOWNLOAD_INFORMATION_BROADCAST_ID_COMMAND, BROADCAST_ID);
        }
    }

    private void onChallengeReceived(byte[] data) {
        if (!ListenerUtil.mutListener.listen(4452)) {
            if ((ListenerUtil.mutListener.listen(4450) ? (data.length >= 5) : (ListenerUtil.mutListener.listen(4449) ? (data.length <= 5) : (ListenerUtil.mutListener.listen(4448) ? (data.length > 5) : (ListenerUtil.mutListener.listen(4447) ? (data.length != 5) : (ListenerUtil.mutListener.listen(4446) ? (data.length == 5) : (data.length < 5))))))) {
                if (!ListenerUtil.mutListener.listen(4451)) {
                    Timber.e("Challenge data too short");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4456)) {
            if (password == null) {
                if (!ListenerUtil.mutListener.listen(4453)) {
                    Timber.w("Received challenge, but password is unknown.");
                }
                if (!ListenerUtil.mutListener.listen(4454)) {
                    sendMessage(R.string.trisa_scale_not_paired, null);
                }
                if (!ListenerUtil.mutListener.listen(4455)) {
                    disconnect();
                }
                return;
            }
        }
        int challenge = Converters.fromSignedInt32Le(data, 1);
        int response = challenge ^ password;
        if (!ListenerUtil.mutListener.listen(4457)) {
            writeCommand(DOWNLOAD_INFORMATION_RESULT_COMMAND, response);
        }
        int deviceTimestamp = convertJavaTimestampToDevice(System.currentTimeMillis());
        if (!ListenerUtil.mutListener.listen(4458)) {
            writeCommand(DOWNLOAD_INFORMATION_UTC_COMMAND, deviceTimestamp);
        }
    }

    private void onScaleMeasurumentReceived(byte[] data) {
        ScaleUser user = OpenScale.getInstance().getSelectedScaleUser();
        ScaleMeasurement measurement = parseScaleMeasurementData(data, user);
        if (!ListenerUtil.mutListener.listen(4460)) {
            if (measurement == null) {
                if (!ListenerUtil.mutListener.listen(4459)) {
                    Timber.e("Failed to parse scale measure measurement data: %s", byteInHex(data));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4461)) {
            addScaleMeasurement(measurement);
        }
    }

    public ScaleMeasurement parseScaleMeasurementData(byte[] data, ScaleUser user) {
        if (!ListenerUtil.mutListener.listen(4467)) {
            // ScaleMeasurement needs.
            if ((ListenerUtil.mutListener.listen(4466) ? (data.length >= 9) : (ListenerUtil.mutListener.listen(4465) ? (data.length <= 9) : (ListenerUtil.mutListener.listen(4464) ? (data.length > 9) : (ListenerUtil.mutListener.listen(4463) ? (data.length != 9) : (ListenerUtil.mutListener.listen(4462) ? (data.length == 9) : (data.length < 9))))))) {
                // data is too short
                return null;
            }
        }
        byte infoByte = data[0];
        boolean hasTimestamp = (ListenerUtil.mutListener.listen(4472) ? ((infoByte & 1) >= 1) : (ListenerUtil.mutListener.listen(4471) ? ((infoByte & 1) <= 1) : (ListenerUtil.mutListener.listen(4470) ? ((infoByte & 1) > 1) : (ListenerUtil.mutListener.listen(4469) ? ((infoByte & 1) < 1) : (ListenerUtil.mutListener.listen(4468) ? ((infoByte & 1) != 1) : ((infoByte & 1) == 1))))));
        boolean hasResistance1 = (ListenerUtil.mutListener.listen(4477) ? ((infoByte & 2) >= 2) : (ListenerUtil.mutListener.listen(4476) ? ((infoByte & 2) <= 2) : (ListenerUtil.mutListener.listen(4475) ? ((infoByte & 2) > 2) : (ListenerUtil.mutListener.listen(4474) ? ((infoByte & 2) < 2) : (ListenerUtil.mutListener.listen(4473) ? ((infoByte & 2) != 2) : ((infoByte & 2) == 2))))));
        boolean hasResistance2 = (ListenerUtil.mutListener.listen(4482) ? ((infoByte & 4) >= 4) : (ListenerUtil.mutListener.listen(4481) ? ((infoByte & 4) <= 4) : (ListenerUtil.mutListener.listen(4480) ? ((infoByte & 4) > 4) : (ListenerUtil.mutListener.listen(4479) ? ((infoByte & 4) < 4) : (ListenerUtil.mutListener.listen(4478) ? ((infoByte & 4) != 4) : ((infoByte & 4) == 4))))));
        if (!ListenerUtil.mutListener.listen(4483)) {
            if (!hasTimestamp) {
                return null;
            }
        }
        float weightKg = getBase10Float(data, 1);
        int deviceTimestamp = Converters.fromSignedInt32Le(data, 5);
        ScaleMeasurement measurement = new ScaleMeasurement();
        if (!ListenerUtil.mutListener.listen(4484)) {
            measurement.setDateTime(new Date(convertDeviceTimestampToJava(deviceTimestamp)));
        }
        if (!ListenerUtil.mutListener.listen(4485)) {
            measurement.setWeight((float) weightKg);
        }
        // Only resistance 2 is used; resistance 1 is 0, even if it is present.
        int resistance2Offset = (ListenerUtil.mutListener.listen(4489) ? (9 % (hasResistance1 ? 4 : 0)) : (ListenerUtil.mutListener.listen(4488) ? (9 / (hasResistance1 ? 4 : 0)) : (ListenerUtil.mutListener.listen(4487) ? (9 * (hasResistance1 ? 4 : 0)) : (ListenerUtil.mutListener.listen(4486) ? (9 - (hasResistance1 ? 4 : 0)) : (9 + (hasResistance1 ? 4 : 0))))));
        if (!ListenerUtil.mutListener.listen(4518)) {
            if ((ListenerUtil.mutListener.listen(4500) ? ((ListenerUtil.mutListener.listen(4499) ? (hasResistance2 || (ListenerUtil.mutListener.listen(4498) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) >= data.length) : (ListenerUtil.mutListener.listen(4497) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) > data.length) : (ListenerUtil.mutListener.listen(4496) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) < data.length) : (ListenerUtil.mutListener.listen(4495) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) != data.length) : (ListenerUtil.mutListener.listen(4494) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) == data.length) : ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) <= data.length))))))) : (hasResistance2 && (ListenerUtil.mutListener.listen(4498) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) >= data.length) : (ListenerUtil.mutListener.listen(4497) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) > data.length) : (ListenerUtil.mutListener.listen(4496) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) < data.length) : (ListenerUtil.mutListener.listen(4495) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) != data.length) : (ListenerUtil.mutListener.listen(4494) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) == data.length) : ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) <= data.length)))))))) || isValidUser(user)) : ((ListenerUtil.mutListener.listen(4499) ? (hasResistance2 || (ListenerUtil.mutListener.listen(4498) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) >= data.length) : (ListenerUtil.mutListener.listen(4497) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) > data.length) : (ListenerUtil.mutListener.listen(4496) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) < data.length) : (ListenerUtil.mutListener.listen(4495) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) != data.length) : (ListenerUtil.mutListener.listen(4494) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) == data.length) : ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) <= data.length))))))) : (hasResistance2 && (ListenerUtil.mutListener.listen(4498) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) >= data.length) : (ListenerUtil.mutListener.listen(4497) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) > data.length) : (ListenerUtil.mutListener.listen(4496) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) < data.length) : (ListenerUtil.mutListener.listen(4495) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) != data.length) : (ListenerUtil.mutListener.listen(4494) ? ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) == data.length) : ((ListenerUtil.mutListener.listen(4493) ? (resistance2Offset % 4) : (ListenerUtil.mutListener.listen(4492) ? (resistance2Offset / 4) : (ListenerUtil.mutListener.listen(4491) ? (resistance2Offset * 4) : (ListenerUtil.mutListener.listen(4490) ? (resistance2Offset - 4) : (resistance2Offset + 4))))) <= data.length)))))))) && isValidUser(user)))) {
                // very meaningful, it might still be useful to track changes over time.
                float resistance2 = getBase10Float(data, resistance2Offset);
                float impedance = (ListenerUtil.mutListener.listen(4505) ? (resistance2 >= 410f) : (ListenerUtil.mutListener.listen(4504) ? (resistance2 <= 410f) : (ListenerUtil.mutListener.listen(4503) ? (resistance2 > 410f) : (ListenerUtil.mutListener.listen(4502) ? (resistance2 != 410f) : (ListenerUtil.mutListener.listen(4501) ? (resistance2 == 410f) : (resistance2 < 410f)))))) ? 3.0f : (ListenerUtil.mutListener.listen(4513) ? (0.3f % ((ListenerUtil.mutListener.listen(4509) ? (resistance2 % 400f) : (ListenerUtil.mutListener.listen(4508) ? (resistance2 / 400f) : (ListenerUtil.mutListener.listen(4507) ? (resistance2 * 400f) : (ListenerUtil.mutListener.listen(4506) ? (resistance2 + 400f) : (resistance2 - 400f))))))) : (ListenerUtil.mutListener.listen(4512) ? (0.3f / ((ListenerUtil.mutListener.listen(4509) ? (resistance2 % 400f) : (ListenerUtil.mutListener.listen(4508) ? (resistance2 / 400f) : (ListenerUtil.mutListener.listen(4507) ? (resistance2 * 400f) : (ListenerUtil.mutListener.listen(4506) ? (resistance2 + 400f) : (resistance2 - 400f))))))) : (ListenerUtil.mutListener.listen(4511) ? (0.3f - ((ListenerUtil.mutListener.listen(4509) ? (resistance2 % 400f) : (ListenerUtil.mutListener.listen(4508) ? (resistance2 / 400f) : (ListenerUtil.mutListener.listen(4507) ? (resistance2 * 400f) : (ListenerUtil.mutListener.listen(4506) ? (resistance2 + 400f) : (resistance2 - 400f))))))) : (ListenerUtil.mutListener.listen(4510) ? (0.3f + ((ListenerUtil.mutListener.listen(4509) ? (resistance2 % 400f) : (ListenerUtil.mutListener.listen(4508) ? (resistance2 / 400f) : (ListenerUtil.mutListener.listen(4507) ? (resistance2 * 400f) : (ListenerUtil.mutListener.listen(4506) ? (resistance2 + 400f) : (resistance2 - 400f))))))) : (0.3f * ((ListenerUtil.mutListener.listen(4509) ? (resistance2 % 400f) : (ListenerUtil.mutListener.listen(4508) ? (resistance2 / 400f) : (ListenerUtil.mutListener.listen(4507) ? (resistance2 * 400f) : (ListenerUtil.mutListener.listen(4506) ? (resistance2 + 400f) : (resistance2 - 400f)))))))))));
                TrisaBodyAnalyzeLib trisaBodyAnalyzeLib = new TrisaBodyAnalyzeLib(user.getGender().isMale() ? 1 : 0, user.getAge(), user.getBodyHeight());
                if (!ListenerUtil.mutListener.listen(4514)) {
                    measurement.setFat(trisaBodyAnalyzeLib.getFat(weightKg, impedance));
                }
                if (!ListenerUtil.mutListener.listen(4515)) {
                    measurement.setWater(trisaBodyAnalyzeLib.getWater(weightKg, impedance));
                }
                if (!ListenerUtil.mutListener.listen(4516)) {
                    measurement.setMuscle(trisaBodyAnalyzeLib.getMuscle(weightKg, impedance));
                }
                if (!ListenerUtil.mutListener.listen(4517)) {
                    measurement.setBone(trisaBodyAnalyzeLib.getBone(weightKg, impedance));
                }
            }
        }
        return measurement;
    }

    /**
     * Write a single command byte, without any arguments.
     */
    private void writeCommand(byte commandByte) {
        if (!ListenerUtil.mutListener.listen(4519)) {
            writeCommandBytes(new byte[] { commandByte });
        }
    }

    /**
     * Write a command with a 32-bit integer argument.
     *
     * <p>The command string consists of the command byte followed by 4 bytes: the argument
     * encoded in little-endian byte order.</p>
     */
    private void writeCommand(byte commandByte, int argument) {
        byte[] bytes = new byte[5];
        if (!ListenerUtil.mutListener.listen(4520)) {
            bytes[0] = commandByte;
        }
        if (!ListenerUtil.mutListener.listen(4521)) {
            Converters.toInt32Le(bytes, 1, argument);
        }
        if (!ListenerUtil.mutListener.listen(4522)) {
            writeCommandBytes(bytes);
        }
    }

    private void writeCommandBytes(byte[] bytes) {
        if (!ListenerUtil.mutListener.listen(4523)) {
            Timber.d("writeCommand bytes=%s", byteInHex(bytes));
        }
        if (!ListenerUtil.mutListener.listen(4524)) {
            writeBytes(WEIGHT_SCALE_SERVICE_UUID, DOWNLOAD_COMMAND_CHARACTERISTIC_UUID, bytes);
        }
    }

    private static String getDevicePasswordKey(String deviceId) {
        return SHARED_PREFERENCES_PASSWORD_KEY_PREFIX + deviceId;
    }

    @Nullable
    private static Integer loadDevicePassword(Context context, String deviceId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String key = getDevicePasswordKey(deviceId);
        try {
            // getInt(), but it's not a problem because we never delete passwords.
            return prefs.contains(key) ? Integer.valueOf(prefs.getInt(key, 0)) : null;
        } catch (ClassCastException e) {
            if (!ListenerUtil.mutListener.listen(4525)) {
                Timber.e(e, "Password preference value is not an integer.");
            }
            return null;
        }
    }

    private static void saveDevicePassword(Context context, String deviceId, int password) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(4526)) {
            prefs.edit().putInt(getDevicePasswordKey(deviceId), password).apply();
        }
    }

    /**
     * Converts 4 bytes to a floating point number, starting from  {@code offset}.
     *
     * <p>The first three little-endian bytes form the 24-bit mantissa. The last byte contains the
     * signed exponent, applied in base 10.
     *
     * @throws IndexOutOfBoundsException if {@code offset < 0} or {@code offset + 4> data.length}
     */
    public float getBase10Float(byte[] data, int offset) {
        int mantissa = Converters.fromUnsignedInt24Le(data, offset);
        // note: byte is signed.
        int exponent = data[(ListenerUtil.mutListener.listen(4530) ? (offset % 3) : (ListenerUtil.mutListener.listen(4529) ? (offset / 3) : (ListenerUtil.mutListener.listen(4528) ? (offset * 3) : (ListenerUtil.mutListener.listen(4527) ? (offset - 3) : (offset + 3)))))];
        return (float) ((ListenerUtil.mutListener.listen(4534) ? (mantissa % Math.pow(10, exponent)) : (ListenerUtil.mutListener.listen(4533) ? (mantissa / Math.pow(10, exponent)) : (ListenerUtil.mutListener.listen(4532) ? (mantissa - Math.pow(10, exponent)) : (ListenerUtil.mutListener.listen(4531) ? (mantissa + Math.pow(10, exponent)) : (mantissa * Math.pow(10, exponent)))))));
    }

    public int convertJavaTimestampToDevice(long javaTimestampMillis) {
        return (int) ((ListenerUtil.mutListener.listen(4546) ? ((ListenerUtil.mutListener.listen(4542) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) % 1000) : (ListenerUtil.mutListener.listen(4541) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) * 1000) : (ListenerUtil.mutListener.listen(4540) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) - 1000) : (ListenerUtil.mutListener.listen(4539) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) + 1000) : (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) / 1000))))) % TIMESTAMP_OFFSET_SECONDS) : (ListenerUtil.mutListener.listen(4545) ? ((ListenerUtil.mutListener.listen(4542) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) % 1000) : (ListenerUtil.mutListener.listen(4541) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) * 1000) : (ListenerUtil.mutListener.listen(4540) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) - 1000) : (ListenerUtil.mutListener.listen(4539) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) + 1000) : (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) / 1000))))) / TIMESTAMP_OFFSET_SECONDS) : (ListenerUtil.mutListener.listen(4544) ? ((ListenerUtil.mutListener.listen(4542) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) % 1000) : (ListenerUtil.mutListener.listen(4541) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) * 1000) : (ListenerUtil.mutListener.listen(4540) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) - 1000) : (ListenerUtil.mutListener.listen(4539) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) + 1000) : (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) / 1000))))) * TIMESTAMP_OFFSET_SECONDS) : (ListenerUtil.mutListener.listen(4543) ? ((ListenerUtil.mutListener.listen(4542) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) % 1000) : (ListenerUtil.mutListener.listen(4541) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) * 1000) : (ListenerUtil.mutListener.listen(4540) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) - 1000) : (ListenerUtil.mutListener.listen(4539) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) + 1000) : (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) / 1000))))) + TIMESTAMP_OFFSET_SECONDS) : ((ListenerUtil.mutListener.listen(4542) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) % 1000) : (ListenerUtil.mutListener.listen(4541) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) * 1000) : (ListenerUtil.mutListener.listen(4540) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) - 1000) : (ListenerUtil.mutListener.listen(4539) ? (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) + 1000) : (((ListenerUtil.mutListener.listen(4538) ? (javaTimestampMillis % 500) : (ListenerUtil.mutListener.listen(4537) ? (javaTimestampMillis / 500) : (ListenerUtil.mutListener.listen(4536) ? (javaTimestampMillis * 500) : (ListenerUtil.mutListener.listen(4535) ? (javaTimestampMillis - 500) : (javaTimestampMillis + 500)))))) / 1000))))) - TIMESTAMP_OFFSET_SECONDS))))));
    }

    public long convertDeviceTimestampToJava(int deviceTimestampSeconds) {
        return (ListenerUtil.mutListener.listen(4554) ? (1000 % ((ListenerUtil.mutListener.listen(4550) ? (TIMESTAMP_OFFSET_SECONDS % (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4549) ? (TIMESTAMP_OFFSET_SECONDS / (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4548) ? (TIMESTAMP_OFFSET_SECONDS * (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4547) ? (TIMESTAMP_OFFSET_SECONDS - (long) deviceTimestampSeconds) : (TIMESTAMP_OFFSET_SECONDS + (long) deviceTimestampSeconds))))))) : (ListenerUtil.mutListener.listen(4553) ? (1000 / ((ListenerUtil.mutListener.listen(4550) ? (TIMESTAMP_OFFSET_SECONDS % (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4549) ? (TIMESTAMP_OFFSET_SECONDS / (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4548) ? (TIMESTAMP_OFFSET_SECONDS * (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4547) ? (TIMESTAMP_OFFSET_SECONDS - (long) deviceTimestampSeconds) : (TIMESTAMP_OFFSET_SECONDS + (long) deviceTimestampSeconds))))))) : (ListenerUtil.mutListener.listen(4552) ? (1000 - ((ListenerUtil.mutListener.listen(4550) ? (TIMESTAMP_OFFSET_SECONDS % (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4549) ? (TIMESTAMP_OFFSET_SECONDS / (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4548) ? (TIMESTAMP_OFFSET_SECONDS * (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4547) ? (TIMESTAMP_OFFSET_SECONDS - (long) deviceTimestampSeconds) : (TIMESTAMP_OFFSET_SECONDS + (long) deviceTimestampSeconds))))))) : (ListenerUtil.mutListener.listen(4551) ? (1000 + ((ListenerUtil.mutListener.listen(4550) ? (TIMESTAMP_OFFSET_SECONDS % (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4549) ? (TIMESTAMP_OFFSET_SECONDS / (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4548) ? (TIMESTAMP_OFFSET_SECONDS * (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4547) ? (TIMESTAMP_OFFSET_SECONDS - (long) deviceTimestampSeconds) : (TIMESTAMP_OFFSET_SECONDS + (long) deviceTimestampSeconds))))))) : (1000 * ((ListenerUtil.mutListener.listen(4550) ? (TIMESTAMP_OFFSET_SECONDS % (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4549) ? (TIMESTAMP_OFFSET_SECONDS / (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4548) ? (TIMESTAMP_OFFSET_SECONDS * (long) deviceTimestampSeconds) : (ListenerUtil.mutListener.listen(4547) ? (TIMESTAMP_OFFSET_SECONDS - (long) deviceTimestampSeconds) : (TIMESTAMP_OFFSET_SECONDS + (long) deviceTimestampSeconds)))))))))));
    }

    private boolean isValidUser(@Nullable ScaleUser user) {
        return (ListenerUtil.mutListener.listen(4566) ? ((ListenerUtil.mutListener.listen(4560) ? (user != null || (ListenerUtil.mutListener.listen(4559) ? (user.getAge() >= 0) : (ListenerUtil.mutListener.listen(4558) ? (user.getAge() <= 0) : (ListenerUtil.mutListener.listen(4557) ? (user.getAge() < 0) : (ListenerUtil.mutListener.listen(4556) ? (user.getAge() != 0) : (ListenerUtil.mutListener.listen(4555) ? (user.getAge() == 0) : (user.getAge() > 0))))))) : (user != null && (ListenerUtil.mutListener.listen(4559) ? (user.getAge() >= 0) : (ListenerUtil.mutListener.listen(4558) ? (user.getAge() <= 0) : (ListenerUtil.mutListener.listen(4557) ? (user.getAge() < 0) : (ListenerUtil.mutListener.listen(4556) ? (user.getAge() != 0) : (ListenerUtil.mutListener.listen(4555) ? (user.getAge() == 0) : (user.getAge() > 0)))))))) || (ListenerUtil.mutListener.listen(4565) ? (user.getBodyHeight() >= 0) : (ListenerUtil.mutListener.listen(4564) ? (user.getBodyHeight() <= 0) : (ListenerUtil.mutListener.listen(4563) ? (user.getBodyHeight() < 0) : (ListenerUtil.mutListener.listen(4562) ? (user.getBodyHeight() != 0) : (ListenerUtil.mutListener.listen(4561) ? (user.getBodyHeight() == 0) : (user.getBodyHeight() > 0))))))) : ((ListenerUtil.mutListener.listen(4560) ? (user != null || (ListenerUtil.mutListener.listen(4559) ? (user.getAge() >= 0) : (ListenerUtil.mutListener.listen(4558) ? (user.getAge() <= 0) : (ListenerUtil.mutListener.listen(4557) ? (user.getAge() < 0) : (ListenerUtil.mutListener.listen(4556) ? (user.getAge() != 0) : (ListenerUtil.mutListener.listen(4555) ? (user.getAge() == 0) : (user.getAge() > 0))))))) : (user != null && (ListenerUtil.mutListener.listen(4559) ? (user.getAge() >= 0) : (ListenerUtil.mutListener.listen(4558) ? (user.getAge() <= 0) : (ListenerUtil.mutListener.listen(4557) ? (user.getAge() < 0) : (ListenerUtil.mutListener.listen(4556) ? (user.getAge() != 0) : (ListenerUtil.mutListener.listen(4555) ? (user.getAge() == 0) : (user.getAge() > 0)))))))) && (ListenerUtil.mutListener.listen(4565) ? (user.getBodyHeight() >= 0) : (ListenerUtil.mutListener.listen(4564) ? (user.getBodyHeight() <= 0) : (ListenerUtil.mutListener.listen(4563) ? (user.getBodyHeight() < 0) : (ListenerUtil.mutListener.listen(4562) ? (user.getBodyHeight() != 0) : (ListenerUtil.mutListener.listen(4561) ? (user.getBodyHeight() == 0) : (user.getBodyHeight() > 0))))))));
    }
}
