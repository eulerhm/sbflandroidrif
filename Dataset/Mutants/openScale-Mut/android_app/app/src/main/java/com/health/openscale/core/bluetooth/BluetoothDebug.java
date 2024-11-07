/* Copyright (C) 2018 Erik Johansson <erik@ejohansson.se>
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

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import com.welie.blessed.BluetoothPeripheral;
import java.util.HashMap;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothDebug extends BluetoothCommunication {

    HashMap<Integer, String> propertyString;

    BluetoothDebug(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(2369)) {
            propertyString = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(2370)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_BROADCAST, "BROADCAST");
        }
        if (!ListenerUtil.mutListener.listen(2371)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_READ, "READ");
        }
        if (!ListenerUtil.mutListener.listen(2372)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE, "WRITE_NO_RESPONSE");
        }
        if (!ListenerUtil.mutListener.listen(2373)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_WRITE, "WRITE");
        }
        if (!ListenerUtil.mutListener.listen(2374)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_NOTIFY, "NOTIFY");
        }
        if (!ListenerUtil.mutListener.listen(2375)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_INDICATE, "INDICATE");
        }
        if (!ListenerUtil.mutListener.listen(2376)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE, "SIGNED_WRITE");
        }
        if (!ListenerUtil.mutListener.listen(2377)) {
            propertyString.put(BluetoothGattCharacteristic.PROPERTY_EXTENDED_PROPS, "EXTENDED_PROPS");
        }
    }

    @Override
    public String driverName() {
        return "Debug";
    }

    private boolean isBlacklisted(BluetoothGattService service, BluetoothGattCharacteristic characteristic) {
        if (!ListenerUtil.mutListener.listen(2379)) {
            // Reading this triggers a pairing request on Beurer BF710
            if ((ListenerUtil.mutListener.listen(2378) ? (service.getUuid().equals(BluetoothGattUuid.fromShortCode(0xffe0)) || characteristic.getUuid().equals(BluetoothGattUuid.fromShortCode(0xffe5))) : (service.getUuid().equals(BluetoothGattUuid.fromShortCode(0xffe0)) && characteristic.getUuid().equals(BluetoothGattUuid.fromShortCode(0xffe5))))) {
                return true;
            }
        }
        return false;
    }

    private boolean isWriteType(int property, int writeType) {
        if (!ListenerUtil.mutListener.listen(2380)) {
            switch(property) {
                case BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE:
                    return writeType == BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE;
                case BluetoothGattCharacteristic.PROPERTY_WRITE:
                    return writeType == BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT;
                case BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE:
                    return writeType == BluetoothGattCharacteristic.WRITE_TYPE_SIGNED;
            }
        }
        return false;
    }

    private String propertiesToString(int properties, int writeType) {
        StringBuilder names = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(2391)) {
            {
                long _loopCounter20 = 0;
                for (int property : propertyString.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                    if (!ListenerUtil.mutListener.listen(2390)) {
                        if ((ListenerUtil.mutListener.listen(2385) ? ((properties & property) >= 0) : (ListenerUtil.mutListener.listen(2384) ? ((properties & property) <= 0) : (ListenerUtil.mutListener.listen(2383) ? ((properties & property) > 0) : (ListenerUtil.mutListener.listen(2382) ? ((properties & property) < 0) : (ListenerUtil.mutListener.listen(2381) ? ((properties & property) == 0) : ((properties & property) != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2386)) {
                                names.append(propertyString.get(property));
                            }
                            if (!ListenerUtil.mutListener.listen(2388)) {
                                if (isWriteType(property, writeType)) {
                                    if (!ListenerUtil.mutListener.listen(2387)) {
                                        names.append('*');
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2389)) {
                                names.append(", ");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2397)) {
            if ((ListenerUtil.mutListener.listen(2396) ? (names.length() >= 0) : (ListenerUtil.mutListener.listen(2395) ? (names.length() <= 0) : (ListenerUtil.mutListener.listen(2394) ? (names.length() > 0) : (ListenerUtil.mutListener.listen(2393) ? (names.length() < 0) : (ListenerUtil.mutListener.listen(2392) ? (names.length() != 0) : (names.length() == 0))))))) {
                return "<none>";
            }
        }
        return names.substring(0, (ListenerUtil.mutListener.listen(2401) ? (names.length() % 2) : (ListenerUtil.mutListener.listen(2400) ? (names.length() / 2) : (ListenerUtil.mutListener.listen(2399) ? (names.length() * 2) : (ListenerUtil.mutListener.listen(2398) ? (names.length() + 2) : (names.length() - 2))))));
    }

    private String permissionsToString(int permissions) {
        if (!ListenerUtil.mutListener.listen(2407)) {
            if ((ListenerUtil.mutListener.listen(2406) ? (permissions >= 0) : (ListenerUtil.mutListener.listen(2405) ? (permissions <= 0) : (ListenerUtil.mutListener.listen(2404) ? (permissions > 0) : (ListenerUtil.mutListener.listen(2403) ? (permissions < 0) : (ListenerUtil.mutListener.listen(2402) ? (permissions != 0) : (permissions == 0))))))) {
                return "";
            }
        }
        return String.format(" (permissions=0x%x)", permissions);
    }

    private String byteToString(byte[] value) {
        return new String(value).replaceAll("\\p{Cntrl}", "?");
    }

    private void logService(BluetoothGattService service, boolean included) {
        if (!ListenerUtil.mutListener.listen(2408)) {
            Timber.d("Service %s%s", BluetoothGattUuid.prettyPrint(service.getUuid()), included ? " (included)" : "");
        }
        if (!ListenerUtil.mutListener.listen(2429)) {
            {
                long _loopCounter22 = 0;
                for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    if (!ListenerUtil.mutListener.listen(2409)) {
                        Timber.d("|- characteristic %s (#%d): %s%s", BluetoothGattUuid.prettyPrint(characteristic.getUuid()), characteristic.getInstanceId(), propertiesToString(characteristic.getProperties(), characteristic.getWriteType()), permissionsToString(characteristic.getPermissions()));
                    }
                    byte[] value = characteristic.getValue();
                    if (!ListenerUtil.mutListener.listen(2417)) {
                        if ((ListenerUtil.mutListener.listen(2415) ? (value != null || (ListenerUtil.mutListener.listen(2414) ? (value.length >= 0) : (ListenerUtil.mutListener.listen(2413) ? (value.length <= 0) : (ListenerUtil.mutListener.listen(2412) ? (value.length < 0) : (ListenerUtil.mutListener.listen(2411) ? (value.length != 0) : (ListenerUtil.mutListener.listen(2410) ? (value.length == 0) : (value.length > 0))))))) : (value != null && (ListenerUtil.mutListener.listen(2414) ? (value.length >= 0) : (ListenerUtil.mutListener.listen(2413) ? (value.length <= 0) : (ListenerUtil.mutListener.listen(2412) ? (value.length < 0) : (ListenerUtil.mutListener.listen(2411) ? (value.length != 0) : (ListenerUtil.mutListener.listen(2410) ? (value.length == 0) : (value.length > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(2416)) {
                                Timber.d("|--> value: %s (%s)", byteInHex(value), byteToString(value));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2428)) {
                        {
                            long _loopCounter21 = 0;
                            for (BluetoothGattDescriptor descriptor : characteristic.getDescriptors()) {
                                ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                                if (!ListenerUtil.mutListener.listen(2418)) {
                                    Timber.d("|--- descriptor %s%s", BluetoothGattUuid.prettyPrint(descriptor.getUuid()), permissionsToString(descriptor.getPermissions()));
                                }
                                if (!ListenerUtil.mutListener.listen(2419)) {
                                    value = descriptor.getValue();
                                }
                                if (!ListenerUtil.mutListener.listen(2427)) {
                                    if ((ListenerUtil.mutListener.listen(2425) ? (value != null || (ListenerUtil.mutListener.listen(2424) ? (value.length >= 0) : (ListenerUtil.mutListener.listen(2423) ? (value.length <= 0) : (ListenerUtil.mutListener.listen(2422) ? (value.length < 0) : (ListenerUtil.mutListener.listen(2421) ? (value.length != 0) : (ListenerUtil.mutListener.listen(2420) ? (value.length == 0) : (value.length > 0))))))) : (value != null && (ListenerUtil.mutListener.listen(2424) ? (value.length >= 0) : (ListenerUtil.mutListener.listen(2423) ? (value.length <= 0) : (ListenerUtil.mutListener.listen(2422) ? (value.length < 0) : (ListenerUtil.mutListener.listen(2421) ? (value.length != 0) : (ListenerUtil.mutListener.listen(2420) ? (value.length == 0) : (value.length > 0))))))))) {
                                        if (!ListenerUtil.mutListener.listen(2426)) {
                                            Timber.d("|-----> value: %s (%s)", byteInHex(value), byteToString(value));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2431)) {
            {
                long _loopCounter23 = 0;
                for (BluetoothGattService includedService : service.getIncludedServices()) {
                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                    if (!ListenerUtil.mutListener.listen(2430)) {
                        logService(includedService, true);
                    }
                }
            }
        }
    }

    private int readServiceCharacteristics(BluetoothGattService service, int offset) {
        if (!ListenerUtil.mutListener.listen(2451)) {
            {
                long _loopCounter25 = 0;
                for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
                    ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                    if (!ListenerUtil.mutListener.listen(2441)) {
                        if ((ListenerUtil.mutListener.listen(2432) ? ((characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_READ) != 0 || !isBlacklisted(service, characteristic)) : ((characteristic.getProperties() & BluetoothGattCharacteristic.PROPERTY_READ) != 0 && !isBlacklisted(service, characteristic)))) {
                            if (!ListenerUtil.mutListener.listen(2439)) {
                                if ((ListenerUtil.mutListener.listen(2437) ? (offset >= 0) : (ListenerUtil.mutListener.listen(2436) ? (offset <= 0) : (ListenerUtil.mutListener.listen(2435) ? (offset > 0) : (ListenerUtil.mutListener.listen(2434) ? (offset < 0) : (ListenerUtil.mutListener.listen(2433) ? (offset != 0) : (offset == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(2438)) {
                                        readBytes(service.getUuid(), characteristic.getUuid());
                                    }
                                    return -1;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2440)) {
                                offset -= 1;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2450)) {
                        {
                            long _loopCounter24 = 0;
                            for (BluetoothGattDescriptor descriptor : characteristic.getDescriptors()) {
                                ListenerUtil.loopListener.listen("_loopCounter24", ++_loopCounter24);
                                if (!ListenerUtil.mutListener.listen(2448)) {
                                    if ((ListenerUtil.mutListener.listen(2446) ? (offset >= 0) : (ListenerUtil.mutListener.listen(2445) ? (offset <= 0) : (ListenerUtil.mutListener.listen(2444) ? (offset > 0) : (ListenerUtil.mutListener.listen(2443) ? (offset < 0) : (ListenerUtil.mutListener.listen(2442) ? (offset != 0) : (offset == 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(2447)) {
                                            readBytes(service.getUuid(), characteristic.getUuid());
                                        }
                                        return -1;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2449)) {
                                    offset -= 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2459)) {
            {
                long _loopCounter26 = 0;
                for (BluetoothGattService included : service.getIncludedServices()) {
                    ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                    if (!ListenerUtil.mutListener.listen(2452)) {
                        offset = readServiceCharacteristics(included, offset);
                    }
                    if (!ListenerUtil.mutListener.listen(2458)) {
                        if ((ListenerUtil.mutListener.listen(2457) ? (offset >= -1) : (ListenerUtil.mutListener.listen(2456) ? (offset <= -1) : (ListenerUtil.mutListener.listen(2455) ? (offset > -1) : (ListenerUtil.mutListener.listen(2454) ? (offset < -1) : (ListenerUtil.mutListener.listen(2453) ? (offset != -1) : (offset == -1))))))) {
                            return offset;
                        }
                    }
                }
            }
        }
        return offset;
    }

    @Override
    protected void onBluetoothDiscovery(BluetoothPeripheral peripheral) {
        int offset = 0;
        if (!ListenerUtil.mutListener.listen(2461)) {
            {
                long _loopCounter27 = 0;
                for (BluetoothGattService service : peripheral.getServices()) {
                    ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                    if (!ListenerUtil.mutListener.listen(2460)) {
                        offset = readServiceCharacteristics(service, offset);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2463)) {
            {
                long _loopCounter28 = 0;
                for (BluetoothGattService service : peripheral.getServices()) {
                    ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                    if (!ListenerUtil.mutListener.listen(2462)) {
                        logService(service, false);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2464)) {
            setBluetoothStatus(BT_STATUS.CONNECTION_LOST);
        }
        if (!ListenerUtil.mutListener.listen(2465)) {
            disconnect();
        }
    }

    @Override
    protected boolean onNextStep(int stateNr) {
        return false;
    }
}
