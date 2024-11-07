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

import android.Manifest;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.welie.blessed.BluetoothCentral;
import com.welie.blessed.BluetoothCentralCallback;
import com.welie.blessed.BluetoothPeripheral;
import com.welie.blessed.BluetoothPeripheralCallback;
import java.util.UUID;
import timber.log.Timber;
import static android.bluetooth.BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT;
import static android.bluetooth.BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE;
import static android.content.Context.LOCATION_SERVICE;
import static com.welie.blessed.BluetoothPeripheral.GATT_SUCCESS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BluetoothCommunication {

    public enum BT_STATUS {

        RETRIEVE_SCALE_DATA,
        INIT_PROCESS,
        CONNECTION_RETRYING,
        CONNECTION_ESTABLISHED,
        CONNECTION_DISCONNECT,
        CONNECTION_LOST,
        NO_DEVICE_FOUND,
        UNEXPECTED_ERROR,
        SCALE_MESSAGE
    }

    private int stepNr;

    private boolean stopped;

    protected Context context;

    private Handler callbackBtHandler;

    private Handler disconnectHandler;

    private BluetoothCentral central;

    private BluetoothPeripheral btPeripheral;

    public BluetoothCommunication(Context context) {
        if (!ListenerUtil.mutListener.listen(2156)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(2157)) {
            this.disconnectHandler = new Handler();
        }
        if (!ListenerUtil.mutListener.listen(2158)) {
            this.stepNr = 0;
        }
        if (!ListenerUtil.mutListener.listen(2159)) {
            this.stopped = false;
        }
        if (!ListenerUtil.mutListener.listen(2160)) {
            this.central = new BluetoothCentral(context, bluetoothCentralCallback, new Handler(Looper.getMainLooper()));
        }
    }

    /**
     * Register a callback Bluetooth handler that notify any BT_STATUS changes for GUI/CORE.
     *
     * @param cbBtHandler a handler that is registered
     */
    public void registerCallbackHandler(Handler cbBtHandler) {
        if (!ListenerUtil.mutListener.listen(2161)) {
            callbackBtHandler = cbBtHandler;
        }
    }

    /**
     * Set for the openScale GUI/CORE the Bluetooth status code.
     *
     * @param status the status code that should be set
     */
    protected void setBluetoothStatus(BT_STATUS status) {
        if (!ListenerUtil.mutListener.listen(2162)) {
            setBluetoothStatus(status, "");
        }
    }

    /**
     * Set for the openScale GUI/CORE the Bluetooth status code.
     *
     * @param statusCode the status code that should be set
     * @param infoText the information text that is displayed to the status code.
     */
    protected void setBluetoothStatus(BT_STATUS statusCode, String infoText) {
        if (!ListenerUtil.mutListener.listen(2164)) {
            if (callbackBtHandler != null) {
                if (!ListenerUtil.mutListener.listen(2163)) {
                    callbackBtHandler.obtainMessage(statusCode.ordinal(), infoText).sendToTarget();
                }
            }
        }
    }

    /**
     * Add a new scale data to openScale
     *
     * @param scaleMeasurement the scale data that should be added to openScale
     */
    protected void addScaleMeasurement(ScaleMeasurement scaleMeasurement) {
        if (!ListenerUtil.mutListener.listen(2166)) {
            if (callbackBtHandler != null) {
                if (!ListenerUtil.mutListener.listen(2165)) {
                    callbackBtHandler.obtainMessage(BT_STATUS.RETRIEVE_SCALE_DATA.ordinal(), scaleMeasurement).sendToTarget();
                }
            }
        }
    }

    /**
     * Send message to openScale user
     *
     * @param msg the string id to be send
     * @param value the value to be used
     */
    protected void sendMessage(int msg, Object value) {
        if (!ListenerUtil.mutListener.listen(2168)) {
            if (callbackBtHandler != null) {
                if (!ListenerUtil.mutListener.listen(2167)) {
                    callbackBtHandler.obtainMessage(BT_STATUS.SCALE_MESSAGE.ordinal(), msg, 0, value).sendToTarget();
                }
            }
        }
    }

    /**
     * Return the Bluetooth driver name
     *
     * @return a string in a human readable name
     */
    public abstract String driverName();

    /**
     * State machine for the initialization process of the Bluetooth device.
     *
     * @param stepNr the current step number
     * @return false if no next step is available otherwise true
     */
    protected abstract boolean onNextStep(int stepNr);

    /**
     * Method is triggered if a Bluetooth data from a device is notified or indicated.
     *
     * @param characteristic
     * @param value the Bluetooth characteristic
     */
    protected void onBluetoothNotify(UUID characteristic, byte[] value) {
    }

    /**
     * Method is triggered if a Bluetooth services from a device is discovered.
     *
     * @param peripheral
     */
    protected void onBluetoothDiscovery(BluetoothPeripheral peripheral) {
    }

    protected synchronized void stopMachineState() {
        if (!ListenerUtil.mutListener.listen(2169)) {
            Timber.d("Stop machine state");
        }
        if (!ListenerUtil.mutListener.listen(2170)) {
            stopped = true;
        }
    }

    protected synchronized void resumeMachineState() {
        if (!ListenerUtil.mutListener.listen(2171)) {
            Timber.d("Resume machine state");
        }
        if (!ListenerUtil.mutListener.listen(2172)) {
            stopped = false;
        }
        if (!ListenerUtil.mutListener.listen(2173)) {
            nextMachineStep();
        }
    }

    protected synchronized void jumpNextToStepNr(int nr) {
        if (!ListenerUtil.mutListener.listen(2174)) {
            Timber.d("Jump next to step nr " + nr);
        }
        if (!ListenerUtil.mutListener.listen(2175)) {
            stepNr = nr;
        }
    }

    /**
     * Write a byte array to a Bluetooth device.
     *
     * @param characteristic the Bluetooth UUID characteristic
     * @param bytes          the bytes that should be write
     */
    protected void writeBytes(UUID service, UUID characteristic, byte[] bytes) {
        if (!ListenerUtil.mutListener.listen(2176)) {
            writeBytes(service, characteristic, bytes, false);
        }
    }

    /**
     * Write a byte array to a Bluetooth device.
     *
     * @param characteristic the Bluetooth UUID characteristic
     * @param bytes          the bytes that should be write
     * @param noResponse     true if no response is required
     */
    protected void writeBytes(UUID service, UUID characteristic, byte[] bytes, boolean noResponse) {
        if (!ListenerUtil.mutListener.listen(2177)) {
            Timber.d("Invoke write bytes [" + byteInHex(bytes) + "] on " + BluetoothGattUuid.prettyPrint(characteristic));
        }
        if (!ListenerUtil.mutListener.listen(2178)) {
            btPeripheral.writeCharacteristic(btPeripheral.getCharacteristic(service, characteristic), bytes, noResponse ? WRITE_TYPE_NO_RESPONSE : WRITE_TYPE_DEFAULT);
        }
    }

    /**
     * Read bytes from a Bluetooth device.
     *
     * @note onBluetoothRead() will be triggered if read command was successful. nextMachineStep() needs to manually called!
     *@param characteristic the Bluetooth UUID characteristic
     */
    void readBytes(UUID service, UUID characteristic) {
        if (!ListenerUtil.mutListener.listen(2179)) {
            Timber.d("Invoke read bytes on " + BluetoothGattUuid.prettyPrint(characteristic));
        }
        if (!ListenerUtil.mutListener.listen(2180)) {
            btPeripheral.readCharacteristic(btPeripheral.getCharacteristic(service, characteristic));
        }
    }

    /**
     * Set indication flag on for the Bluetooth device.
     *
     * @param characteristic the Bluetooth UUID characteristic
     */
    protected void setIndicationOn(UUID service, UUID characteristic) {
        if (!ListenerUtil.mutListener.listen(2181)) {
            Timber.d("Invoke set indication on " + BluetoothGattUuid.prettyPrint(characteristic));
        }
        if (!ListenerUtil.mutListener.listen(2184)) {
            if (btPeripheral.getService(service) != null) {
                if (!ListenerUtil.mutListener.listen(2182)) {
                    stopMachineState();
                }
                BluetoothGattCharacteristic currentTimeCharacteristic = btPeripheral.getCharacteristic(service, characteristic);
                if (!ListenerUtil.mutListener.listen(2183)) {
                    btPeripheral.setNotify(currentTimeCharacteristic, true);
                }
            }
        }
    }

    /**
     * Set notification flag on for the Bluetooth device.
     *
     * @param characteristic the Bluetooth UUID characteristic
     */
    protected void setNotificationOn(UUID service, UUID characteristic) {
        if (!ListenerUtil.mutListener.listen(2185)) {
            Timber.d("Invoke set notification on " + BluetoothGattUuid.prettyPrint(characteristic));
        }
        if (!ListenerUtil.mutListener.listen(2188)) {
            if (btPeripheral.getService(service) != null) {
                if (!ListenerUtil.mutListener.listen(2186)) {
                    stopMachineState();
                }
                BluetoothGattCharacteristic currentTimeCharacteristic = btPeripheral.getCharacteristic(service, characteristic);
                if (!ListenerUtil.mutListener.listen(2187)) {
                    btPeripheral.setNotify(currentTimeCharacteristic, true);
                }
            }
        }
    }

    /**
     * Disconnect from a Bluetooth device
     */
    public void disconnect() {
        if (!ListenerUtil.mutListener.listen(2189)) {
            Timber.d("Bluetooth disconnect");
        }
        if (!ListenerUtil.mutListener.listen(2190)) {
            setBluetoothStatus(BT_STATUS.CONNECTION_DISCONNECT);
        }
        try {
            if (!ListenerUtil.mutListener.listen(2192)) {
                central.stopScan();
            }
        } catch (Exception ex) {
            if (!ListenerUtil.mutListener.listen(2191)) {
                Timber.e("Error on Bluetooth disconnecting " + ex.getMessage());
            }
        }
        if (!ListenerUtil.mutListener.listen(2194)) {
            if (btPeripheral != null) {
                if (!ListenerUtil.mutListener.listen(2193)) {
                    central.cancelConnection(btPeripheral);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2195)) {
            callbackBtHandler = null;
        }
        if (!ListenerUtil.mutListener.listen(2196)) {
            disconnectHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * Convert a byte array to hex for debugging purpose
     *
     * @param data data we want to make human-readable (hex)
     * @return a human-readable string representing the content of 'data'
     */
    protected String byteInHex(byte[] data) {
        if (!ListenerUtil.mutListener.listen(2198)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(2197)) {
                    Timber.e("Data is null");
                }
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(2204)) {
            if ((ListenerUtil.mutListener.listen(2203) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(2202) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(2201) ? (data.length > 0) : (ListenerUtil.mutListener.listen(2200) ? (data.length < 0) : (ListenerUtil.mutListener.listen(2199) ? (data.length != 0) : (data.length == 0))))))) {
                return "";
            }
        }
        final StringBuilder stringBuilder = new StringBuilder((ListenerUtil.mutListener.listen(2208) ? (3 % data.length) : (ListenerUtil.mutListener.listen(2207) ? (3 / data.length) : (ListenerUtil.mutListener.listen(2206) ? (3 - data.length) : (ListenerUtil.mutListener.listen(2205) ? (3 + data.length) : (3 * data.length))))));
        if (!ListenerUtil.mutListener.listen(2210)) {
            {
                long _loopCounter16 = 0;
                for (byte byteChar : data) {
                    ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                    if (!ListenerUtil.mutListener.listen(2209)) {
                        stringBuilder.append(String.format("%02X ", byteChar));
                    }
                }
            }
        }
        return stringBuilder.substring(0, (ListenerUtil.mutListener.listen(2214) ? (stringBuilder.length() % 1) : (ListenerUtil.mutListener.listen(2213) ? (stringBuilder.length() / 1) : (ListenerUtil.mutListener.listen(2212) ? (stringBuilder.length() * 1) : (ListenerUtil.mutListener.listen(2211) ? (stringBuilder.length() + 1) : (stringBuilder.length() - 1))))));
    }

    protected float clamp(double value, double min, double max) {
        if (!ListenerUtil.mutListener.listen(2220)) {
            if ((ListenerUtil.mutListener.listen(2219) ? (value >= min) : (ListenerUtil.mutListener.listen(2218) ? (value <= min) : (ListenerUtil.mutListener.listen(2217) ? (value > min) : (ListenerUtil.mutListener.listen(2216) ? (value != min) : (ListenerUtil.mutListener.listen(2215) ? (value == min) : (value < min))))))) {
                return (float) min;
            }
        }
        if (!ListenerUtil.mutListener.listen(2226)) {
            if ((ListenerUtil.mutListener.listen(2225) ? (value >= max) : (ListenerUtil.mutListener.listen(2224) ? (value <= max) : (ListenerUtil.mutListener.listen(2223) ? (value < max) : (ListenerUtil.mutListener.listen(2222) ? (value != max) : (ListenerUtil.mutListener.listen(2221) ? (value == max) : (value > max))))))) {
                return (float) max;
            }
        }
        return (float) value;
    }

    protected byte xorChecksum(byte[] data, int offset, int length) {
        byte checksum = 0;
        if (!ListenerUtil.mutListener.listen(2237)) {
            {
                long _loopCounter17 = 0;
                for (int i = offset; (ListenerUtil.mutListener.listen(2236) ? (i >= (ListenerUtil.mutListener.listen(2231) ? (offset % length) : (ListenerUtil.mutListener.listen(2230) ? (offset / length) : (ListenerUtil.mutListener.listen(2229) ? (offset * length) : (ListenerUtil.mutListener.listen(2228) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2235) ? (i <= (ListenerUtil.mutListener.listen(2231) ? (offset % length) : (ListenerUtil.mutListener.listen(2230) ? (offset / length) : (ListenerUtil.mutListener.listen(2229) ? (offset * length) : (ListenerUtil.mutListener.listen(2228) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2234) ? (i > (ListenerUtil.mutListener.listen(2231) ? (offset % length) : (ListenerUtil.mutListener.listen(2230) ? (offset / length) : (ListenerUtil.mutListener.listen(2229) ? (offset * length) : (ListenerUtil.mutListener.listen(2228) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2233) ? (i != (ListenerUtil.mutListener.listen(2231) ? (offset % length) : (ListenerUtil.mutListener.listen(2230) ? (offset / length) : (ListenerUtil.mutListener.listen(2229) ? (offset * length) : (ListenerUtil.mutListener.listen(2228) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2232) ? (i == (ListenerUtil.mutListener.listen(2231) ? (offset % length) : (ListenerUtil.mutListener.listen(2230) ? (offset / length) : (ListenerUtil.mutListener.listen(2229) ? (offset * length) : (ListenerUtil.mutListener.listen(2228) ? (offset - length) : (offset + length)))))) : (i < (ListenerUtil.mutListener.listen(2231) ? (offset % length) : (ListenerUtil.mutListener.listen(2230) ? (offset / length) : (ListenerUtil.mutListener.listen(2229) ? (offset * length) : (ListenerUtil.mutListener.listen(2228) ? (offset - length) : (offset + length))))))))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                    if (!ListenerUtil.mutListener.listen(2227)) {
                        checksum ^= data[i];
                    }
                }
            }
        }
        return checksum;
    }

    protected byte sumChecksum(byte[] data, int offset, int length) {
        byte checksum = 0;
        if (!ListenerUtil.mutListener.listen(2248)) {
            {
                long _loopCounter18 = 0;
                for (int i = offset; (ListenerUtil.mutListener.listen(2247) ? (i >= (ListenerUtil.mutListener.listen(2242) ? (offset % length) : (ListenerUtil.mutListener.listen(2241) ? (offset / length) : (ListenerUtil.mutListener.listen(2240) ? (offset * length) : (ListenerUtil.mutListener.listen(2239) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2246) ? (i <= (ListenerUtil.mutListener.listen(2242) ? (offset % length) : (ListenerUtil.mutListener.listen(2241) ? (offset / length) : (ListenerUtil.mutListener.listen(2240) ? (offset * length) : (ListenerUtil.mutListener.listen(2239) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2245) ? (i > (ListenerUtil.mutListener.listen(2242) ? (offset % length) : (ListenerUtil.mutListener.listen(2241) ? (offset / length) : (ListenerUtil.mutListener.listen(2240) ? (offset * length) : (ListenerUtil.mutListener.listen(2239) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2244) ? (i != (ListenerUtil.mutListener.listen(2242) ? (offset % length) : (ListenerUtil.mutListener.listen(2241) ? (offset / length) : (ListenerUtil.mutListener.listen(2240) ? (offset * length) : (ListenerUtil.mutListener.listen(2239) ? (offset - length) : (offset + length)))))) : (ListenerUtil.mutListener.listen(2243) ? (i == (ListenerUtil.mutListener.listen(2242) ? (offset % length) : (ListenerUtil.mutListener.listen(2241) ? (offset / length) : (ListenerUtil.mutListener.listen(2240) ? (offset * length) : (ListenerUtil.mutListener.listen(2239) ? (offset - length) : (offset + length)))))) : (i < (ListenerUtil.mutListener.listen(2242) ? (offset % length) : (ListenerUtil.mutListener.listen(2241) ? (offset / length) : (ListenerUtil.mutListener.listen(2240) ? (offset * length) : (ListenerUtil.mutListener.listen(2239) ? (offset - length) : (offset + length))))))))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                    if (!ListenerUtil.mutListener.listen(2238)) {
                        checksum += data[i];
                    }
                }
            }
        }
        return checksum;
    }

    /**
     * Test in a byte if a bit is set (1) or not (0)
     *
     * @param value byte which is tested
     * @param bit bit position which is tested
     * @return true if bit is set (1) otherwise false (0)
     */
    protected boolean isBitSet(byte value, int bit) {
        return (ListenerUtil.mutListener.listen(2253) ? ((value & (1 << bit)) >= 0) : (ListenerUtil.mutListener.listen(2252) ? ((value & (1 << bit)) <= 0) : (ListenerUtil.mutListener.listen(2251) ? ((value & (1 << bit)) > 0) : (ListenerUtil.mutListener.listen(2250) ? ((value & (1 << bit)) < 0) : (ListenerUtil.mutListener.listen(2249) ? ((value & (1 << bit)) == 0) : ((value & (1 << bit)) != 0))))));
    }

    private final BluetoothPeripheralCallback peripheralCallback = new BluetoothPeripheralCallback() {

        @Override
        public void onServicesDiscovered(BluetoothPeripheral peripheral) {
            if (!ListenerUtil.mutListener.listen(2254)) {
                Timber.d("Successful Bluetooth services discovered");
            }
            if (!ListenerUtil.mutListener.listen(2255)) {
                onBluetoothDiscovery(peripheral);
            }
            if (!ListenerUtil.mutListener.listen(2256)) {
                resumeMachineState();
            }
        }

        @Override
        public void onNotificationStateUpdate(BluetoothPeripheral peripheral, BluetoothGattCharacteristic characteristic, int status) {
            if (!ListenerUtil.mutListener.listen(2261)) {
                if (status == GATT_SUCCESS) {
                    if (!ListenerUtil.mutListener.listen(2260)) {
                        if (peripheral.isNotifying(characteristic)) {
                            if (!ListenerUtil.mutListener.listen(2258)) {
                                Timber.d(String.format("SUCCESS: Notify set for %s", characteristic.getUuid()));
                            }
                            if (!ListenerUtil.mutListener.listen(2259)) {
                                resumeMachineState();
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2257)) {
                        Timber.e(String.format("ERROR: Changing notification state failed for %s", characteristic.getUuid()));
                    }
                }
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothPeripheral peripheral, byte[] value, BluetoothGattCharacteristic characteristic, int status) {
            if (!ListenerUtil.mutListener.listen(2265)) {
                if (status == GATT_SUCCESS) {
                    if (!ListenerUtil.mutListener.listen(2263)) {
                        Timber.d(String.format("SUCCESS: Writing <%s> to <%s>", byteInHex(value), characteristic.getUuid().toString()));
                    }
                    if (!ListenerUtil.mutListener.listen(2264)) {
                        nextMachineStep();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2262)) {
                        Timber.e(String.format("ERROR: Failed writing <%s> to <%s>", byteInHex(value), characteristic.getUuid().toString()));
                    }
                }
            }
        }

        @Override
        public void onCharacteristicUpdate(final BluetoothPeripheral peripheral, byte[] value, final BluetoothGattCharacteristic characteristic, final int status) {
            if (!ListenerUtil.mutListener.listen(2266)) {
                resetDisconnectTimer();
            }
            if (!ListenerUtil.mutListener.listen(2267)) {
                onBluetoothNotify(characteristic.getUuid(), value);
            }
        }
    };

    // Callback for central
    private final BluetoothCentralCallback bluetoothCentralCallback = new BluetoothCentralCallback() {

        @Override
        public void onConnectedPeripheral(BluetoothPeripheral peripheral) {
            if (!ListenerUtil.mutListener.listen(2268)) {
                Timber.d(String.format("connected to '%s'", peripheral.getName()));
            }
            if (!ListenerUtil.mutListener.listen(2269)) {
                setBluetoothStatus(BT_STATUS.CONNECTION_ESTABLISHED);
            }
            if (!ListenerUtil.mutListener.listen(2270)) {
                btPeripheral = peripheral;
            }
            if (!ListenerUtil.mutListener.listen(2271)) {
                nextMachineStep();
            }
            if (!ListenerUtil.mutListener.listen(2272)) {
                resetDisconnectTimer();
            }
        }

        @Override
        public void onConnectionFailed(BluetoothPeripheral peripheral, final int status) {
            if (!ListenerUtil.mutListener.listen(2273)) {
                Timber.e(String.format("connection '%s' failed with status %d", peripheral.getName(), status));
            }
            if (!ListenerUtil.mutListener.listen(2274)) {
                setBluetoothStatus(BT_STATUS.CONNECTION_LOST);
            }
            if (!ListenerUtil.mutListener.listen(2281)) {
                if ((ListenerUtil.mutListener.listen(2279) ? (status >= 8) : (ListenerUtil.mutListener.listen(2278) ? (status <= 8) : (ListenerUtil.mutListener.listen(2277) ? (status > 8) : (ListenerUtil.mutListener.listen(2276) ? (status < 8) : (ListenerUtil.mutListener.listen(2275) ? (status != 8) : (status == 8))))))) {
                    if (!ListenerUtil.mutListener.listen(2280)) {
                        sendMessage(R.string.info_bluetooth_connection_error_scale_offline, 0);
                    }
                }
            }
        }

        @Override
        public void onDisconnectedPeripheral(final BluetoothPeripheral peripheral, final int status) {
            if (!ListenerUtil.mutListener.listen(2282)) {
                Timber.d(String.format("disconnected '%s' with status %d", peripheral.getName(), status));
            }
        }

        @Override
        public void onDiscoveredPeripheral(BluetoothPeripheral peripheral, ScanResult scanResult) {
            if (!ListenerUtil.mutListener.listen(2283)) {
                Timber.d(String.format("Found peripheral '%s'", peripheral.getName()));
            }
            if (!ListenerUtil.mutListener.listen(2284)) {
                central.stopScan();
            }
            if (!ListenerUtil.mutListener.listen(2285)) {
                connectToDevice(peripheral);
            }
        }
    };

    /**
     * Connect to a Bluetooth device.
     *
     * On successfully connection Bluetooth machine state is automatically triggered.
     * If the device is not found the process is automatically stopped.
     *
     * @param macAddress the Bluetooth address to connect to
     */
    public void connect(String macAddress) {
        // Otherwise the connection almost never succeeds.
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(2293)) {
            if ((ListenerUtil.mutListener.listen(2287) ? (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ((ListenerUtil.mutListener.listen(2286) ? (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) : (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))))) : (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ((ListenerUtil.mutListener.listen(2286) ? (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) : (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))))))) {
                if (!ListenerUtil.mutListener.listen(2290)) {
                    Timber.d("Do LE scan before connecting to device");
                }
                if (!ListenerUtil.mutListener.listen(2291)) {
                    central.scanForPeripheralsWithAddresses(new String[] { macAddress });
                }
                if (!ListenerUtil.mutListener.listen(2292)) {
                    stopMachineState();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2288)) {
                    Timber.d("No location permission, connecting without LE scan");
                }
                BluetoothPeripheral peripheral = central.getPeripheral(macAddress);
                if (!ListenerUtil.mutListener.listen(2289)) {
                    connectToDevice(peripheral);
                }
            }
        }
    }

    private void connectToDevice(BluetoothPeripheral peripheral) {
        Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(2297)) {
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(2294)) {
                        Timber.d("Try to connect to BLE device " + peripheral.getAddress());
                    }
                    if (!ListenerUtil.mutListener.listen(2295)) {
                        stepNr = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(2296)) {
                        central.connectPeripheral(peripheral, peripheralCallback);
                    }
                }
            }, 1000);
        }
    }

    private void resetDisconnectTimer() {
        if (!ListenerUtil.mutListener.listen(2298)) {
            disconnectHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(2299)) {
            disconnectWithDelay();
        }
    }

    private void disconnectWithDelay() {
        if (!ListenerUtil.mutListener.listen(2302)) {
            disconnectHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(2300)) {
                        Timber.d("Timeout Bluetooth disconnect");
                    }
                    if (!ListenerUtil.mutListener.listen(2301)) {
                        disconnect();
                    }
                }
            }, // 60s timeout
            60000);
        }
    }

    private synchronized void nextMachineStep() {
        if (!ListenerUtil.mutListener.listen(2309)) {
            if (!stopped) {
                if (!ListenerUtil.mutListener.listen(2303)) {
                    Timber.d("Step Nr " + stepNr);
                }
                if (!ListenerUtil.mutListener.listen(2308)) {
                    if (onNextStep(stepNr)) {
                        if (!ListenerUtil.mutListener.listen(2306)) {
                            stepNr++;
                        }
                        if (!ListenerUtil.mutListener.listen(2307)) {
                            nextMachineStep();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2304)) {
                            Timber.d("Invoke delayed disconnect in 60s");
                        }
                        if (!ListenerUtil.mutListener.listen(2305)) {
                            disconnectWithDelay();
                        }
                    }
                }
            }
        }
    }
}
