/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*                2017  jflesch <jflesch@kwain.net>
*                2017  Martin Nowack
*                2017  linuxlurak with help of Dododappere, see: https://github.com/oliexdev/openScale/issues/111
*                2018  Erik Johansson <erik@ejohansson.se>
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
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothBeurerSanitas extends BluetoothCommunication {

    enum DeviceType {

        BEURER_BF700_800_RT_LIBRA, BEURER_BF710, SANITAS_SBF70_70
    }

    private static final UUID CUSTOM_SERVICE_1 = BluetoothGattUuid.fromShortCode(0xffe0);

    private static final UUID CUSTOM_CHARACTERISTIC_WEIGHT = BluetoothGattUuid.fromShortCode(0xffe1);

    private final DeviceType deviceType;

    private byte startByte;

    private class RemoteUser {

        public final long remoteUserId;

        public final String name;

        public final int year;

        public int localUserId = -1;

        public boolean isNew = false;

        RemoteUser(long uid, String name, int year) {
            this.remoteUserId = uid;
            this.name = name;
            this.year = year;
        }
    }

    private ArrayList<RemoteUser> remoteUsers = new ArrayList<>();

    private RemoteUser currentRemoteUser;

    private byte[] measurementData;

    private final int ID_START_NIBBLE_INIT = 6;

    private final int ID_START_NIBBLE_CMD = 7;

    private final int ID_START_NIBBLE_SET_TIME = 9;

    private final int ID_START_NIBBLE_DISCONNECT = 10;

    private final byte CMD_SET_UNIT = (byte) 0x4d;

    private final byte CMD_SCALE_STATUS = (byte) 0x4f;

    private final byte CMD_USER_ADD = (byte) 0x31;

    private final byte CMD_USER_DELETE = (byte) 0x32;

    private final byte CMD_USER_LIST = (byte) 0x33;

    private final byte CMD_USER_INFO = (byte) 0x34;

    private final byte CMD_USER_UPDATE = (byte) 0x35;

    private final byte CMD_USER_DETAILS = (byte) 0x36;

    private final byte CMD_DO_MEASUREMENT = (byte) 0x40;

    private final byte CMD_GET_SAVED_MEASUREMENTS = (byte) 0x41;

    private final byte CMD_SAVED_MEASUREMENT = (byte) 0x42;

    private final byte CMD_DELETE_SAVED_MEASUREMENTS = (byte) 0x43;

    private final byte CMD_GET_UNKNOWN_MEASUREMENTS = (byte) 0x46;

    private final byte CMD_UNKNOWN_MEASUREMENT_INFO = (byte) 0x47;

    private final byte CMD_ASSIGN_UNKNOWN_MEASUREMENT = (byte) 0x4b;

    private final byte CMD_UNKNOWN_MEASUREMENT = (byte) 0x4c;

    private final byte CMD_DELETE_UNKNOWN_MEASUREMENT = (byte) 0x49;

    private final byte CMD_WEIGHT_MEASUREMENT = (byte) 0x58;

    private final byte CMD_MEASUREMENT = (byte) 0x59;

    private final byte CMD_SCALE_ACK = (byte) 0xf0;

    private final byte CMD_APP_ACK = (byte) 0xf1;

    private byte getAlternativeStartByte(int startNibble) {
        return (byte) ((startByte & 0xF0) | startNibble);
    }

    private long decodeUserId(byte[] data, int offset) {
        long high = Converters.fromUnsignedInt32Be(data, offset);
        long low = Converters.fromUnsignedInt32Be(data, (ListenerUtil.mutListener.listen(1746) ? (offset % 4) : (ListenerUtil.mutListener.listen(1745) ? (offset / 4) : (ListenerUtil.mutListener.listen(1744) ? (offset * 4) : (ListenerUtil.mutListener.listen(1743) ? (offset - 4) : (offset + 4))))));
        return (high << 32) | low;
    }

    private byte[] encodeUserId(RemoteUser remoteUser) {
        long uid = remoteUser != null ? remoteUser.remoteUserId : 0;
        byte[] data = new byte[8];
        if (!ListenerUtil.mutListener.listen(1747)) {
            Converters.toInt32Be(data, 0, uid >> 32);
        }
        if (!ListenerUtil.mutListener.listen(1748)) {
            Converters.toInt32Be(data, 4, uid & 0xFFFFFFFF);
        }
        return data;
    }

    private String decodeString(byte[] data, int offset, int maxLength) {
        int length = 0;
        if (!ListenerUtil.mutListener.listen(1764)) {
            {
                long _loopCounter7 = 0;
                for (; (ListenerUtil.mutListener.listen(1763) ? (length >= maxLength) : (ListenerUtil.mutListener.listen(1762) ? (length <= maxLength) : (ListenerUtil.mutListener.listen(1761) ? (length > maxLength) : (ListenerUtil.mutListener.listen(1760) ? (length != maxLength) : (ListenerUtil.mutListener.listen(1759) ? (length == maxLength) : (length < maxLength)))))); ++length) {
                    ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                    if (!ListenerUtil.mutListener.listen(1758)) {
                        if ((ListenerUtil.mutListener.listen(1757) ? (data[(ListenerUtil.mutListener.listen(1752) ? (offset % length) : (ListenerUtil.mutListener.listen(1751) ? (offset / length) : (ListenerUtil.mutListener.listen(1750) ? (offset * length) : (ListenerUtil.mutListener.listen(1749) ? (offset - length) : (offset + length)))))] >= 0) : (ListenerUtil.mutListener.listen(1756) ? (data[(ListenerUtil.mutListener.listen(1752) ? (offset % length) : (ListenerUtil.mutListener.listen(1751) ? (offset / length) : (ListenerUtil.mutListener.listen(1750) ? (offset * length) : (ListenerUtil.mutListener.listen(1749) ? (offset - length) : (offset + length)))))] <= 0) : (ListenerUtil.mutListener.listen(1755) ? (data[(ListenerUtil.mutListener.listen(1752) ? (offset % length) : (ListenerUtil.mutListener.listen(1751) ? (offset / length) : (ListenerUtil.mutListener.listen(1750) ? (offset * length) : (ListenerUtil.mutListener.listen(1749) ? (offset - length) : (offset + length)))))] > 0) : (ListenerUtil.mutListener.listen(1754) ? (data[(ListenerUtil.mutListener.listen(1752) ? (offset % length) : (ListenerUtil.mutListener.listen(1751) ? (offset / length) : (ListenerUtil.mutListener.listen(1750) ? (offset * length) : (ListenerUtil.mutListener.listen(1749) ? (offset - length) : (offset + length)))))] < 0) : (ListenerUtil.mutListener.listen(1753) ? (data[(ListenerUtil.mutListener.listen(1752) ? (offset % length) : (ListenerUtil.mutListener.listen(1751) ? (offset / length) : (ListenerUtil.mutListener.listen(1750) ? (offset * length) : (ListenerUtil.mutListener.listen(1749) ? (offset - length) : (offset + length)))))] != 0) : (data[(ListenerUtil.mutListener.listen(1752) ? (offset % length) : (ListenerUtil.mutListener.listen(1751) ? (offset / length) : (ListenerUtil.mutListener.listen(1750) ? (offset * length) : (ListenerUtil.mutListener.listen(1749) ? (offset - length) : (offset + length)))))] == 0))))))) {
                            break;
                        }
                    }
                }
            }
        }
        return new String(data, offset, length);
    }

    private String normalizeString(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("[^A-Za-z0-9]", "");
    }

    private String convertUserNameToScale(ScaleUser user) {
        String normalized = normalizeString(user.getUserName());
        if (!ListenerUtil.mutListener.listen(1765)) {
            if (normalized.isEmpty()) {
                return String.valueOf(user.getId());
            }
        }
        return normalized.toUpperCase(Locale.US);
    }

    public BluetoothBeurerSanitas(Context context, DeviceType deviceType) {
        super(context);
        this.deviceType = deviceType;
        if (!ListenerUtil.mutListener.listen(1768)) {
            switch(deviceType) {
                case BEURER_BF700_800_RT_LIBRA:
                    if (!ListenerUtil.mutListener.listen(1766)) {
                        startByte = (byte) (0xf0 | ID_START_NIBBLE_CMD);
                    }
                    break;
                case BEURER_BF710:
                case SANITAS_SBF70_70:
                    if (!ListenerUtil.mutListener.listen(1767)) {
                        startByte = (byte) (0xe0 | ID_START_NIBBLE_CMD);
                    }
                    break;
            }
        }
    }

    @Override
    public String driverName() {
        if (!ListenerUtil.mutListener.listen(1769)) {
            switch(deviceType) {
                case BEURER_BF700_800_RT_LIBRA:
                    return "Beurer BF700/800 / Runtastic Libra";
                case BEURER_BF710:
                    return "Beurer BF710";
                case SANITAS_SBF70_70:
                    return "Sanitas SBF70/SilverCrest SBF75";
            }
        }
        return "Unknown device type";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(1817)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(1770)) {
                        // Setup notification
                        setNotificationOn(CUSTOM_SERVICE_1, CUSTOM_CHARACTERISTIC_WEIGHT);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(1771)) {
                        // Say "Hello" to the scale and wait for ack
                        sendAlternativeStartCode(ID_START_NIBBLE_INIT, (byte) 0x01);
                    }
                    if (!ListenerUtil.mutListener.listen(1772)) {
                        stopMachineState();
                    }
                    break;
                case 2:
                    // Update time on the scale (no ack)
                    long unixTime = (ListenerUtil.mutListener.listen(1776) ? (System.currentTimeMillis() % 1000L) : (ListenerUtil.mutListener.listen(1775) ? (System.currentTimeMillis() * 1000L) : (ListenerUtil.mutListener.listen(1774) ? (System.currentTimeMillis() - 1000L) : (ListenerUtil.mutListener.listen(1773) ? (System.currentTimeMillis() + 1000L) : (System.currentTimeMillis() / 1000L)))));
                    if (!ListenerUtil.mutListener.listen(1777)) {
                        sendAlternativeStartCode(ID_START_NIBBLE_SET_TIME, Converters.toInt32Be(unixTime));
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(1778)) {
                        // Request scale status and wait for ack
                        sendCommand(CMD_SCALE_STATUS, encodeUserId(null));
                    }
                    if (!ListenerUtil.mutListener.listen(1779)) {
                        stopMachineState();
                    }
                    break;
                case 4:
                    if (!ListenerUtil.mutListener.listen(1780)) {
                        // Request list of all users and wait until all have been received
                        sendCommand(CMD_USER_LIST);
                    }
                    if (!ListenerUtil.mutListener.listen(1781)) {
                        stopMachineState();
                    }
                    break;
                case 5:
                    // If currentRemoteUser is null, indexOf returns -1 and index will be 0
                    int index = (ListenerUtil.mutListener.listen(1785) ? (remoteUsers.indexOf(currentRemoteUser) % 1) : (ListenerUtil.mutListener.listen(1784) ? (remoteUsers.indexOf(currentRemoteUser) / 1) : (ListenerUtil.mutListener.listen(1783) ? (remoteUsers.indexOf(currentRemoteUser) * 1) : (ListenerUtil.mutListener.listen(1782) ? (remoteUsers.indexOf(currentRemoteUser) - 1) : (remoteUsers.indexOf(currentRemoteUser) + 1)))));
                    if (!ListenerUtil.mutListener.listen(1786)) {
                        currentRemoteUser = null;
                    }
                    if (!ListenerUtil.mutListener.listen(1799)) {
                        {
                            long _loopCounter8 = 0;
                            // Find the next remote user that exists locally
                            for (; (ListenerUtil.mutListener.listen(1798) ? (index >= remoteUsers.size()) : (ListenerUtil.mutListener.listen(1797) ? (index <= remoteUsers.size()) : (ListenerUtil.mutListener.listen(1796) ? (index > remoteUsers.size()) : (ListenerUtil.mutListener.listen(1795) ? (index != remoteUsers.size()) : (ListenerUtil.mutListener.listen(1794) ? (index == remoteUsers.size()) : (index < remoteUsers.size())))))); ++index) {
                                ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                                if (!ListenerUtil.mutListener.listen(1793)) {
                                    if ((ListenerUtil.mutListener.listen(1791) ? (remoteUsers.get(index).localUserId >= -1) : (ListenerUtil.mutListener.listen(1790) ? (remoteUsers.get(index).localUserId <= -1) : (ListenerUtil.mutListener.listen(1789) ? (remoteUsers.get(index).localUserId > -1) : (ListenerUtil.mutListener.listen(1788) ? (remoteUsers.get(index).localUserId < -1) : (ListenerUtil.mutListener.listen(1787) ? (remoteUsers.get(index).localUserId == -1) : (remoteUsers.get(index).localUserId != -1))))))) {
                                        if (!ListenerUtil.mutListener.listen(1792)) {
                                            currentRemoteUser = remoteUsers.get(index);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1803)) {
                        // Fetch saved measurements
                        if (currentRemoteUser != null) {
                            if (!ListenerUtil.mutListener.listen(1800)) {
                                Timber.d("Request saved measurements for %s", currentRemoteUser.name);
                            }
                            if (!ListenerUtil.mutListener.listen(1801)) {
                                sendCommand(CMD_GET_SAVED_MEASUREMENTS, encodeUserId(currentRemoteUser));
                            }
                            if (!ListenerUtil.mutListener.listen(1802)) {
                                stopMachineState();
                            }
                        }
                    }
                    break;
                case 6:
                    if (!ListenerUtil.mutListener.listen(1804)) {
                        // Create a remote user for selected openScale user if needed
                        currentRemoteUser = null;
                    }
                    final ScaleUser selectedUser = OpenScale.getInstance().getSelectedScaleUser();
                    if (!ListenerUtil.mutListener.listen(1807)) {
                        {
                            long _loopCounter9 = 0;
                            for (RemoteUser remoteUser : remoteUsers) {
                                ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                                if (!ListenerUtil.mutListener.listen(1806)) {
                                    if (remoteUser.localUserId == selectedUser.getId()) {
                                        if (!ListenerUtil.mutListener.listen(1805)) {
                                            currentRemoteUser = remoteUser;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1810)) {
                        if (currentRemoteUser == null) {
                            if (!ListenerUtil.mutListener.listen(1808)) {
                                createRemoteUser(selectedUser);
                            }
                            if (!ListenerUtil.mutListener.listen(1809)) {
                                stopMachineState();
                            }
                        }
                    }
                    break;
                case 7:
                    if (!ListenerUtil.mutListener.listen(1811)) {
                        sendCommand(CMD_USER_DETAILS, encodeUserId(currentRemoteUser));
                    }
                    if (!ListenerUtil.mutListener.listen(1812)) {
                        stopMachineState();
                    }
                    break;
                case 8:
                    if (!ListenerUtil.mutListener.listen(1816)) {
                        if ((ListenerUtil.mutListener.listen(1813) ? (currentRemoteUser != null || !currentRemoteUser.isNew) : (currentRemoteUser != null && !currentRemoteUser.isNew))) {
                            if (!ListenerUtil.mutListener.listen(1814)) {
                                sendCommand(CMD_DO_MEASUREMENT, encodeUserId(currentRemoteUser));
                            }
                            if (!ListenerUtil.mutListener.listen(1815)) {
                                stopMachineState();
                            }
                        } else {
                            return false;
                        }
                    }
                    break;
                default:
                    // Finish init if everything is done
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        byte[] data = value;
        if (!ListenerUtil.mutListener.listen(1824)) {
            if ((ListenerUtil.mutListener.listen(1823) ? (data == null && (ListenerUtil.mutListener.listen(1822) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(1821) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(1820) ? (data.length > 0) : (ListenerUtil.mutListener.listen(1819) ? (data.length < 0) : (ListenerUtil.mutListener.listen(1818) ? (data.length != 0) : (data.length == 0))))))) : (data == null || (ListenerUtil.mutListener.listen(1822) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(1821) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(1820) ? (data.length > 0) : (ListenerUtil.mutListener.listen(1819) ? (data.length < 0) : (ListenerUtil.mutListener.listen(1818) ? (data.length != 0) : (data.length == 0))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1832)) {
            if ((ListenerUtil.mutListener.listen(1829) ? (data[0] >= getAlternativeStartByte(ID_START_NIBBLE_INIT)) : (ListenerUtil.mutListener.listen(1828) ? (data[0] <= getAlternativeStartByte(ID_START_NIBBLE_INIT)) : (ListenerUtil.mutListener.listen(1827) ? (data[0] > getAlternativeStartByte(ID_START_NIBBLE_INIT)) : (ListenerUtil.mutListener.listen(1826) ? (data[0] < getAlternativeStartByte(ID_START_NIBBLE_INIT)) : (ListenerUtil.mutListener.listen(1825) ? (data[0] != getAlternativeStartByte(ID_START_NIBBLE_INIT)) : (data[0] == getAlternativeStartByte(ID_START_NIBBLE_INIT)))))))) {
                if (!ListenerUtil.mutListener.listen(1830)) {
                    Timber.d("Got init ack from scale; scale is ready");
                }
                if (!ListenerUtil.mutListener.listen(1831)) {
                    resumeMachineState();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1839)) {
            if ((ListenerUtil.mutListener.listen(1837) ? (data[0] >= startByte) : (ListenerUtil.mutListener.listen(1836) ? (data[0] <= startByte) : (ListenerUtil.mutListener.listen(1835) ? (data[0] > startByte) : (ListenerUtil.mutListener.listen(1834) ? (data[0] < startByte) : (ListenerUtil.mutListener.listen(1833) ? (data[0] == startByte) : (data[0] != startByte))))))) {
                if (!ListenerUtil.mutListener.listen(1838)) {
                    Timber.e("Got unknown start byte 0x%02x", data[0]);
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(1847)) {
                switch(data[1]) {
                    case CMD_USER_INFO:
                        if (!ListenerUtil.mutListener.listen(1841)) {
                            processUserInfo(data);
                        }
                        break;
                    case CMD_SAVED_MEASUREMENT:
                        if (!ListenerUtil.mutListener.listen(1842)) {
                            processSavedMeasurement(data);
                        }
                        break;
                    case CMD_WEIGHT_MEASUREMENT:
                        if (!ListenerUtil.mutListener.listen(1843)) {
                            processWeightMeasurement(data);
                        }
                        break;
                    case CMD_MEASUREMENT:
                        if (!ListenerUtil.mutListener.listen(1844)) {
                            processMeasurement(data);
                        }
                        break;
                    case CMD_SCALE_ACK:
                        if (!ListenerUtil.mutListener.listen(1845)) {
                            processScaleAck(data);
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(1846)) {
                            Timber.d("Unknown command 0x%02x", data[1]);
                        }
                        break;
                }
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            if (!ListenerUtil.mutListener.listen(1840)) {
                Timber.e(e);
            }
        }
    }

    private void processUserInfo(byte[] data) {
        final int count = data[2] & 0xFF;
        final int current = data[3] & 0xFF;
        if (!ListenerUtil.mutListener.listen(1863)) {
            if ((ListenerUtil.mutListener.listen(1856) ? (remoteUsers.size() >= (ListenerUtil.mutListener.listen(1851) ? (current % 1) : (ListenerUtil.mutListener.listen(1850) ? (current / 1) : (ListenerUtil.mutListener.listen(1849) ? (current * 1) : (ListenerUtil.mutListener.listen(1848) ? (current + 1) : (current - 1)))))) : (ListenerUtil.mutListener.listen(1855) ? (remoteUsers.size() <= (ListenerUtil.mutListener.listen(1851) ? (current % 1) : (ListenerUtil.mutListener.listen(1850) ? (current / 1) : (ListenerUtil.mutListener.listen(1849) ? (current * 1) : (ListenerUtil.mutListener.listen(1848) ? (current + 1) : (current - 1)))))) : (ListenerUtil.mutListener.listen(1854) ? (remoteUsers.size() > (ListenerUtil.mutListener.listen(1851) ? (current % 1) : (ListenerUtil.mutListener.listen(1850) ? (current / 1) : (ListenerUtil.mutListener.listen(1849) ? (current * 1) : (ListenerUtil.mutListener.listen(1848) ? (current + 1) : (current - 1)))))) : (ListenerUtil.mutListener.listen(1853) ? (remoteUsers.size() < (ListenerUtil.mutListener.listen(1851) ? (current % 1) : (ListenerUtil.mutListener.listen(1850) ? (current / 1) : (ListenerUtil.mutListener.listen(1849) ? (current * 1) : (ListenerUtil.mutListener.listen(1848) ? (current + 1) : (current - 1)))))) : (ListenerUtil.mutListener.listen(1852) ? (remoteUsers.size() != (ListenerUtil.mutListener.listen(1851) ? (current % 1) : (ListenerUtil.mutListener.listen(1850) ? (current / 1) : (ListenerUtil.mutListener.listen(1849) ? (current * 1) : (ListenerUtil.mutListener.listen(1848) ? (current + 1) : (current - 1)))))) : (remoteUsers.size() == (ListenerUtil.mutListener.listen(1851) ? (current % 1) : (ListenerUtil.mutListener.listen(1850) ? (current / 1) : (ListenerUtil.mutListener.listen(1849) ? (current * 1) : (ListenerUtil.mutListener.listen(1848) ? (current + 1) : (current - 1)))))))))))) {
                String name = decodeString(data, 12, 3);
                int year = (ListenerUtil.mutListener.listen(1860) ? (1900 % (data[15] & 0xFF)) : (ListenerUtil.mutListener.listen(1859) ? (1900 / (data[15] & 0xFF)) : (ListenerUtil.mutListener.listen(1858) ? (1900 * (data[15] & 0xFF)) : (ListenerUtil.mutListener.listen(1857) ? (1900 - (data[15] & 0xFF)) : (1900 + (data[15] & 0xFF))))));
                if (!ListenerUtil.mutListener.listen(1861)) {
                    remoteUsers.add(new RemoteUser(decodeUserId(data, 4), name, year));
                }
                if (!ListenerUtil.mutListener.listen(1862)) {
                    Timber.d("Received user %d/%d: %s (%d)", current, count, name, year);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1864)) {
            sendAck(data);
        }
        if (!ListenerUtil.mutListener.listen(1870)) {
            if ((ListenerUtil.mutListener.listen(1869) ? (current >= count) : (ListenerUtil.mutListener.listen(1868) ? (current <= count) : (ListenerUtil.mutListener.listen(1867) ? (current > count) : (ListenerUtil.mutListener.listen(1866) ? (current < count) : (ListenerUtil.mutListener.listen(1865) ? (current == count) : (current != count))))))) {
                return;
            }
        }
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(1882)) {
            {
                long _loopCounter11 = 0;
                for (ScaleUser scaleUser : OpenScale.getInstance().getScaleUserList()) {
                    ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                    final String localName = convertUserNameToScale(scaleUser);
                    if (!ListenerUtil.mutListener.listen(1871)) {
                        cal.setTime(scaleUser.getBirthday());
                    }
                    final int year = cal.get(Calendar.YEAR);
                    if (!ListenerUtil.mutListener.listen(1881)) {
                        {
                            long _loopCounter10 = 0;
                            for (RemoteUser remoteUser : remoteUsers) {
                                ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                                if (!ListenerUtil.mutListener.listen(1880)) {
                                    if ((ListenerUtil.mutListener.listen(1877) ? (localName.startsWith(remoteUser.name) || (ListenerUtil.mutListener.listen(1876) ? (year >= remoteUser.year) : (ListenerUtil.mutListener.listen(1875) ? (year <= remoteUser.year) : (ListenerUtil.mutListener.listen(1874) ? (year > remoteUser.year) : (ListenerUtil.mutListener.listen(1873) ? (year < remoteUser.year) : (ListenerUtil.mutListener.listen(1872) ? (year != remoteUser.year) : (year == remoteUser.year))))))) : (localName.startsWith(remoteUser.name) && (ListenerUtil.mutListener.listen(1876) ? (year >= remoteUser.year) : (ListenerUtil.mutListener.listen(1875) ? (year <= remoteUser.year) : (ListenerUtil.mutListener.listen(1874) ? (year > remoteUser.year) : (ListenerUtil.mutListener.listen(1873) ? (year < remoteUser.year) : (ListenerUtil.mutListener.listen(1872) ? (year != remoteUser.year) : (year == remoteUser.year))))))))) {
                                        if (!ListenerUtil.mutListener.listen(1878)) {
                                            remoteUser.localUserId = scaleUser.getId();
                                        }
                                        if (!ListenerUtil.mutListener.listen(1879)) {
                                            Timber.d("Remote user %s (0x%x) is local user %s (%d)", remoteUser.name, remoteUser.remoteUserId, scaleUser.getUserName(), remoteUser.localUserId);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1883)) {
            // All users received
            resumeMachineState();
        }
    }

    private void processMeasurementData(byte[] data, int offset, boolean firstPart) {
        if (!ListenerUtil.mutListener.listen(1885)) {
            if (firstPart) {
                if (!ListenerUtil.mutListener.listen(1884)) {
                    measurementData = Arrays.copyOfRange(data, offset, data.length);
                }
                return;
            }
        }
        int oldEnd = measurementData.length;
        int toCopy = (ListenerUtil.mutListener.listen(1889) ? (data.length % offset) : (ListenerUtil.mutListener.listen(1888) ? (data.length / offset) : (ListenerUtil.mutListener.listen(1887) ? (data.length * offset) : (ListenerUtil.mutListener.listen(1886) ? (data.length + offset) : (data.length - offset)))));
        if (!ListenerUtil.mutListener.listen(1894)) {
            measurementData = Arrays.copyOf(measurementData, (ListenerUtil.mutListener.listen(1893) ? (oldEnd % toCopy) : (ListenerUtil.mutListener.listen(1892) ? (oldEnd / toCopy) : (ListenerUtil.mutListener.listen(1891) ? (oldEnd * toCopy) : (ListenerUtil.mutListener.listen(1890) ? (oldEnd - toCopy) : (oldEnd + toCopy))))));
        }
        if (!ListenerUtil.mutListener.listen(1895)) {
            System.arraycopy(data, offset, measurementData, oldEnd, toCopy);
        }
        if (!ListenerUtil.mutListener.listen(1896)) {
            addMeasurement(measurementData, currentRemoteUser.localUserId);
        }
        if (!ListenerUtil.mutListener.listen(1897)) {
            measurementData = null;
        }
    }

    private void processSavedMeasurement(byte[] data) {
        int count = data[2] & 0xFF;
        int current = data[3] & 0xFF;
        if (!ListenerUtil.mutListener.listen(1907)) {
            processMeasurementData(data, 4, (ListenerUtil.mutListener.listen(1906) ? ((ListenerUtil.mutListener.listen(1901) ? (current / 2) : (ListenerUtil.mutListener.listen(1900) ? (current * 2) : (ListenerUtil.mutListener.listen(1899) ? (current - 2) : (ListenerUtil.mutListener.listen(1898) ? (current + 2) : (current % 2))))) >= 1) : (ListenerUtil.mutListener.listen(1905) ? ((ListenerUtil.mutListener.listen(1901) ? (current / 2) : (ListenerUtil.mutListener.listen(1900) ? (current * 2) : (ListenerUtil.mutListener.listen(1899) ? (current - 2) : (ListenerUtil.mutListener.listen(1898) ? (current + 2) : (current % 2))))) <= 1) : (ListenerUtil.mutListener.listen(1904) ? ((ListenerUtil.mutListener.listen(1901) ? (current / 2) : (ListenerUtil.mutListener.listen(1900) ? (current * 2) : (ListenerUtil.mutListener.listen(1899) ? (current - 2) : (ListenerUtil.mutListener.listen(1898) ? (current + 2) : (current % 2))))) > 1) : (ListenerUtil.mutListener.listen(1903) ? ((ListenerUtil.mutListener.listen(1901) ? (current / 2) : (ListenerUtil.mutListener.listen(1900) ? (current * 2) : (ListenerUtil.mutListener.listen(1899) ? (current - 2) : (ListenerUtil.mutListener.listen(1898) ? (current + 2) : (current % 2))))) < 1) : (ListenerUtil.mutListener.listen(1902) ? ((ListenerUtil.mutListener.listen(1901) ? (current / 2) : (ListenerUtil.mutListener.listen(1900) ? (current * 2) : (ListenerUtil.mutListener.listen(1899) ? (current - 2) : (ListenerUtil.mutListener.listen(1898) ? (current + 2) : (current % 2))))) != 1) : ((ListenerUtil.mutListener.listen(1901) ? (current / 2) : (ListenerUtil.mutListener.listen(1900) ? (current * 2) : (ListenerUtil.mutListener.listen(1899) ? (current - 2) : (ListenerUtil.mutListener.listen(1898) ? (current + 2) : (current % 2))))) == 1)))))));
        }
        if (!ListenerUtil.mutListener.listen(1908)) {
            sendAck(data);
        }
        if (!ListenerUtil.mutListener.listen(1928)) {
            if ((ListenerUtil.mutListener.listen(1913) ? (current >= count) : (ListenerUtil.mutListener.listen(1912) ? (current <= count) : (ListenerUtil.mutListener.listen(1911) ? (current > count) : (ListenerUtil.mutListener.listen(1910) ? (current < count) : (ListenerUtil.mutListener.listen(1909) ? (current != count) : (current == count))))))) {
                if (!ListenerUtil.mutListener.listen(1914)) {
                    Timber.d("Deleting saved measurements for %s", currentRemoteUser.name);
                }
                if (!ListenerUtil.mutListener.listen(1915)) {
                    sendCommand(CMD_DELETE_SAVED_MEASUREMENTS, encodeUserId(currentRemoteUser));
                }
                if (!ListenerUtil.mutListener.listen(1927)) {
                    if ((ListenerUtil.mutListener.listen(1924) ? (currentRemoteUser.remoteUserId >= remoteUsers.get((ListenerUtil.mutListener.listen(1919) ? (remoteUsers.size() % 1) : (ListenerUtil.mutListener.listen(1918) ? (remoteUsers.size() / 1) : (ListenerUtil.mutListener.listen(1917) ? (remoteUsers.size() * 1) : (ListenerUtil.mutListener.listen(1916) ? (remoteUsers.size() + 1) : (remoteUsers.size() - 1)))))).remoteUserId) : (ListenerUtil.mutListener.listen(1923) ? (currentRemoteUser.remoteUserId <= remoteUsers.get((ListenerUtil.mutListener.listen(1919) ? (remoteUsers.size() % 1) : (ListenerUtil.mutListener.listen(1918) ? (remoteUsers.size() / 1) : (ListenerUtil.mutListener.listen(1917) ? (remoteUsers.size() * 1) : (ListenerUtil.mutListener.listen(1916) ? (remoteUsers.size() + 1) : (remoteUsers.size() - 1)))))).remoteUserId) : (ListenerUtil.mutListener.listen(1922) ? (currentRemoteUser.remoteUserId > remoteUsers.get((ListenerUtil.mutListener.listen(1919) ? (remoteUsers.size() % 1) : (ListenerUtil.mutListener.listen(1918) ? (remoteUsers.size() / 1) : (ListenerUtil.mutListener.listen(1917) ? (remoteUsers.size() * 1) : (ListenerUtil.mutListener.listen(1916) ? (remoteUsers.size() + 1) : (remoteUsers.size() - 1)))))).remoteUserId) : (ListenerUtil.mutListener.listen(1921) ? (currentRemoteUser.remoteUserId < remoteUsers.get((ListenerUtil.mutListener.listen(1919) ? (remoteUsers.size() % 1) : (ListenerUtil.mutListener.listen(1918) ? (remoteUsers.size() / 1) : (ListenerUtil.mutListener.listen(1917) ? (remoteUsers.size() * 1) : (ListenerUtil.mutListener.listen(1916) ? (remoteUsers.size() + 1) : (remoteUsers.size() - 1)))))).remoteUserId) : (ListenerUtil.mutListener.listen(1920) ? (currentRemoteUser.remoteUserId == remoteUsers.get((ListenerUtil.mutListener.listen(1919) ? (remoteUsers.size() % 1) : (ListenerUtil.mutListener.listen(1918) ? (remoteUsers.size() / 1) : (ListenerUtil.mutListener.listen(1917) ? (remoteUsers.size() * 1) : (ListenerUtil.mutListener.listen(1916) ? (remoteUsers.size() + 1) : (remoteUsers.size() - 1)))))).remoteUserId) : (currentRemoteUser.remoteUserId != remoteUsers.get((ListenerUtil.mutListener.listen(1919) ? (remoteUsers.size() % 1) : (ListenerUtil.mutListener.listen(1918) ? (remoteUsers.size() / 1) : (ListenerUtil.mutListener.listen(1917) ? (remoteUsers.size() * 1) : (ListenerUtil.mutListener.listen(1916) ? (remoteUsers.size() + 1) : (remoteUsers.size() - 1)))))).remoteUserId))))))) {
                        if (!ListenerUtil.mutListener.listen(1925)) {
                            jumpNextToStepNr(5);
                        }
                        if (!ListenerUtil.mutListener.listen(1926)) {
                            resumeMachineState();
                        }
                    }
                }
            }
        }
    }

    private void processWeightMeasurement(byte[] data) {
        boolean stableMeasurement = (ListenerUtil.mutListener.listen(1933) ? (data[2] >= 0) : (ListenerUtil.mutListener.listen(1932) ? (data[2] <= 0) : (ListenerUtil.mutListener.listen(1931) ? (data[2] > 0) : (ListenerUtil.mutListener.listen(1930) ? (data[2] < 0) : (ListenerUtil.mutListener.listen(1929) ? (data[2] != 0) : (data[2] == 0))))));
        float weight = getKiloGram(data, 3);
        if (!ListenerUtil.mutListener.listen(1936)) {
            if (!stableMeasurement) {
                if (!ListenerUtil.mutListener.listen(1934)) {
                    Timber.d("Active measurement, weight: %.2f", weight);
                }
                if (!ListenerUtil.mutListener.listen(1935)) {
                    sendMessage(R.string.info_measuring, weight);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1937)) {
            Timber.i("Active measurement, stable weight: %.2f", weight);
        }
    }

    private void processMeasurement(byte[] data) {
        int count = data[2] & 0xFF;
        int current = data[3] & 0xFF;
        if (!ListenerUtil.mutListener.listen(1958)) {
            if ((ListenerUtil.mutListener.listen(1942) ? (current >= 1) : (ListenerUtil.mutListener.listen(1941) ? (current <= 1) : (ListenerUtil.mutListener.listen(1940) ? (current > 1) : (ListenerUtil.mutListener.listen(1939) ? (current < 1) : (ListenerUtil.mutListener.listen(1938) ? (current != 1) : (current == 1))))))) {
                long uid = decodeUserId(data, 5);
                if (!ListenerUtil.mutListener.listen(1949)) {
                    currentRemoteUser = null;
                }
                if (!ListenerUtil.mutListener.listen(1957)) {
                    {
                        long _loopCounter12 = 0;
                        for (RemoteUser remoteUser : remoteUsers) {
                            ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                            if (!ListenerUtil.mutListener.listen(1956)) {
                                if ((ListenerUtil.mutListener.listen(1954) ? (remoteUser.remoteUserId >= uid) : (ListenerUtil.mutListener.listen(1953) ? (remoteUser.remoteUserId <= uid) : (ListenerUtil.mutListener.listen(1952) ? (remoteUser.remoteUserId > uid) : (ListenerUtil.mutListener.listen(1951) ? (remoteUser.remoteUserId < uid) : (ListenerUtil.mutListener.listen(1950) ? (remoteUser.remoteUserId != uid) : (remoteUser.remoteUserId == uid))))))) {
                                    if (!ListenerUtil.mutListener.listen(1955)) {
                                        currentRemoteUser = remoteUser;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1948)) {
                    processMeasurementData(data, 4, (ListenerUtil.mutListener.listen(1947) ? (current >= 2) : (ListenerUtil.mutListener.listen(1946) ? (current <= 2) : (ListenerUtil.mutListener.listen(1945) ? (current > 2) : (ListenerUtil.mutListener.listen(1944) ? (current < 2) : (ListenerUtil.mutListener.listen(1943) ? (current != 2) : (current == 2)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1959)) {
            sendAck(data);
        }
        if (!ListenerUtil.mutListener.listen(1966)) {
            if ((ListenerUtil.mutListener.listen(1964) ? (current >= count) : (ListenerUtil.mutListener.listen(1963) ? (current <= count) : (ListenerUtil.mutListener.listen(1962) ? (current > count) : (ListenerUtil.mutListener.listen(1961) ? (current < count) : (ListenerUtil.mutListener.listen(1960) ? (current != count) : (current == count))))))) {
                if (!ListenerUtil.mutListener.listen(1965)) {
                    sendCommand(CMD_DELETE_SAVED_MEASUREMENTS, encodeUserId(currentRemoteUser));
                }
            }
        }
    }

    private void processScaleAck(byte[] data) {
        if (!ListenerUtil.mutListener.listen(2088)) {
            switch(data[2]) {
                case CMD_SCALE_STATUS:
                    // but it still provides some useful information (e.g. current unit).
                    final int batteryLevel = data[4] & 0xFF;
                    final float weightThreshold = (ListenerUtil.mutListener.listen(1970) ? ((data[5] & 0xFF) % 10f) : (ListenerUtil.mutListener.listen(1969) ? ((data[5] & 0xFF) * 10f) : (ListenerUtil.mutListener.listen(1968) ? ((data[5] & 0xFF) - 10f) : (ListenerUtil.mutListener.listen(1967) ? ((data[5] & 0xFF) + 10f) : ((data[5] & 0xFF) / 10f)))));
                    final float bodyFatThreshold = (ListenerUtil.mutListener.listen(1974) ? ((data[6] & 0xFF) % 10f) : (ListenerUtil.mutListener.listen(1973) ? ((data[6] & 0xFF) * 10f) : (ListenerUtil.mutListener.listen(1972) ? ((data[6] & 0xFF) - 10f) : (ListenerUtil.mutListener.listen(1971) ? ((data[6] & 0xFF) + 10f) : ((data[6] & 0xFF) / 10f)))));
                    final int currentUnit = data[7] & 0xFF;
                    final boolean userExists = (ListenerUtil.mutListener.listen(1979) ? (data[8] >= 0) : (ListenerUtil.mutListener.listen(1978) ? (data[8] <= 0) : (ListenerUtil.mutListener.listen(1977) ? (data[8] > 0) : (ListenerUtil.mutListener.listen(1976) ? (data[8] < 0) : (ListenerUtil.mutListener.listen(1975) ? (data[8] != 0) : (data[8] == 0))))));
                    final boolean userReferWeightExists = (ListenerUtil.mutListener.listen(1984) ? (data[9] >= 0) : (ListenerUtil.mutListener.listen(1983) ? (data[9] <= 0) : (ListenerUtil.mutListener.listen(1982) ? (data[9] > 0) : (ListenerUtil.mutListener.listen(1981) ? (data[9] < 0) : (ListenerUtil.mutListener.listen(1980) ? (data[9] != 0) : (data[9] == 0))))));
                    final boolean userMeasurementExist = (ListenerUtil.mutListener.listen(1989) ? (data[10] >= 0) : (ListenerUtil.mutListener.listen(1988) ? (data[10] <= 0) : (ListenerUtil.mutListener.listen(1987) ? (data[10] > 0) : (ListenerUtil.mutListener.listen(1986) ? (data[10] < 0) : (ListenerUtil.mutListener.listen(1985) ? (data[10] != 0) : (data[10] == 0))))));
                    final int scaleVersion = data[11] & 0xFF;
                    if (!ListenerUtil.mutListener.listen(1990)) {
                        Timber.d("Battery level: %d; threshold: weight=%.2f, body fat=%.2f;" + " unit: %d; requested user: exists=%b, has reference weight=%b," + " has measurement=%b; scale version: %d", batteryLevel, weightThreshold, bodyFatThreshold, currentUnit, userExists, userReferWeightExists, userMeasurementExist, scaleVersion);
                    }
                    if (!ListenerUtil.mutListener.listen(1997)) {
                        if ((ListenerUtil.mutListener.listen(1995) ? (batteryLevel >= 10) : (ListenerUtil.mutListener.listen(1994) ? (batteryLevel > 10) : (ListenerUtil.mutListener.listen(1993) ? (batteryLevel < 10) : (ListenerUtil.mutListener.listen(1992) ? (batteryLevel != 10) : (ListenerUtil.mutListener.listen(1991) ? (batteryLevel == 10) : (batteryLevel <= 10))))))) {
                            if (!ListenerUtil.mutListener.listen(1996)) {
                                sendMessage(R.string.info_scale_low_battery, batteryLevel);
                            }
                        }
                    }
                    byte requestedUnit = (byte) currentUnit;
                    ScaleUser user = OpenScale.getInstance().getSelectedScaleUser();
                    if (!ListenerUtil.mutListener.listen(2001)) {
                        switch(user.getScaleUnit()) {
                            case KG:
                                if (!ListenerUtil.mutListener.listen(1998)) {
                                    requestedUnit = 1;
                                }
                                break;
                            case LB:
                                if (!ListenerUtil.mutListener.listen(1999)) {
                                    requestedUnit = 2;
                                }
                                break;
                            case ST:
                                if (!ListenerUtil.mutListener.listen(2000)) {
                                    requestedUnit = 4;
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2010)) {
                        if ((ListenerUtil.mutListener.listen(2006) ? (requestedUnit >= currentUnit) : (ListenerUtil.mutListener.listen(2005) ? (requestedUnit <= currentUnit) : (ListenerUtil.mutListener.listen(2004) ? (requestedUnit > currentUnit) : (ListenerUtil.mutListener.listen(2003) ? (requestedUnit < currentUnit) : (ListenerUtil.mutListener.listen(2002) ? (requestedUnit == currentUnit) : (requestedUnit != currentUnit))))))) {
                            if (!ListenerUtil.mutListener.listen(2008)) {
                                Timber.d("Set scale unit to %s (%d)", user.getScaleUnit(), requestedUnit);
                            }
                            if (!ListenerUtil.mutListener.listen(2009)) {
                                sendCommand(CMD_SET_UNIT, requestedUnit);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2007)) {
                                resumeMachineState();
                            }
                        }
                    }
                    break;
                case CMD_SET_UNIT:
                    if (!ListenerUtil.mutListener.listen(2017)) {
                        if ((ListenerUtil.mutListener.listen(2015) ? (data[3] >= 0) : (ListenerUtil.mutListener.listen(2014) ? (data[3] <= 0) : (ListenerUtil.mutListener.listen(2013) ? (data[3] > 0) : (ListenerUtil.mutListener.listen(2012) ? (data[3] < 0) : (ListenerUtil.mutListener.listen(2011) ? (data[3] != 0) : (data[3] == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2016)) {
                                Timber.d("Scale unit successfully set");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2018)) {
                        resumeMachineState();
                    }
                    break;
                case CMD_USER_LIST:
                    int userCount = data[4] & 0xFF;
                    int maxUserCount = data[5] & 0xFF;
                    if (!ListenerUtil.mutListener.listen(2019)) {
                        Timber.d("Have %d users (max is %d)", userCount, maxUserCount);
                    }
                    if (!ListenerUtil.mutListener.listen(2026)) {
                        if ((ListenerUtil.mutListener.listen(2024) ? (userCount >= 0) : (ListenerUtil.mutListener.listen(2023) ? (userCount <= 0) : (ListenerUtil.mutListener.listen(2022) ? (userCount > 0) : (ListenerUtil.mutListener.listen(2021) ? (userCount < 0) : (ListenerUtil.mutListener.listen(2020) ? (userCount != 0) : (userCount == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2025)) {
                                resumeMachineState();
                            }
                        }
                    }
                    // Otherwise wait for CMD_USER_INFO notifications
                    break;
                case CMD_GET_SAVED_MEASUREMENTS:
                    int measurementCount = data[3] & 0xFF;
                    if (!ListenerUtil.mutListener.listen(2035)) {
                        if ((ListenerUtil.mutListener.listen(2031) ? (measurementCount >= 0) : (ListenerUtil.mutListener.listen(2030) ? (measurementCount <= 0) : (ListenerUtil.mutListener.listen(2029) ? (measurementCount > 0) : (ListenerUtil.mutListener.listen(2028) ? (measurementCount < 0) : (ListenerUtil.mutListener.listen(2027) ? (measurementCount != 0) : (measurementCount == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2032)) {
                                // Skip delete all measurements step (since there are no measurements to delete)
                                Timber.d("No saved measurements found for user " + currentRemoteUser.name);
                            }
                            if (!ListenerUtil.mutListener.listen(2033)) {
                                jumpNextToStepNr(5);
                            }
                            if (!ListenerUtil.mutListener.listen(2034)) {
                                resumeMachineState();
                            }
                        }
                    }
                    // once all measurements have been received, resume the state machine.
                    break;
                case CMD_DELETE_SAVED_MEASUREMENTS:
                    if (!ListenerUtil.mutListener.listen(2042)) {
                        if ((ListenerUtil.mutListener.listen(2040) ? (data[3] >= 0) : (ListenerUtil.mutListener.listen(2039) ? (data[3] <= 0) : (ListenerUtil.mutListener.listen(2038) ? (data[3] > 0) : (ListenerUtil.mutListener.listen(2037) ? (data[3] < 0) : (ListenerUtil.mutListener.listen(2036) ? (data[3] != 0) : (data[3] == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2041)) {
                                Timber.d("Saved measurements successfully deleted for user " + currentRemoteUser.name);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2043)) {
                        resumeMachineState();
                    }
                    break;
                case CMD_USER_ADD:
                    if (!ListenerUtil.mutListener.listen(2053)) {
                        if ((ListenerUtil.mutListener.listen(2048) ? (data[3] >= 0) : (ListenerUtil.mutListener.listen(2047) ? (data[3] <= 0) : (ListenerUtil.mutListener.listen(2046) ? (data[3] > 0) : (ListenerUtil.mutListener.listen(2045) ? (data[3] < 0) : (ListenerUtil.mutListener.listen(2044) ? (data[3] != 0) : (data[3] == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2049)) {
                                Timber.d("New user successfully added; time to step on scale");
                            }
                            if (!ListenerUtil.mutListener.listen(2050)) {
                                sendMessage(R.string.info_step_on_scale_for_reference, 0);
                            }
                            if (!ListenerUtil.mutListener.listen(2051)) {
                                remoteUsers.add(currentRemoteUser);
                            }
                            if (!ListenerUtil.mutListener.listen(2052)) {
                                sendCommand(CMD_DO_MEASUREMENT, encodeUserId(currentRemoteUser));
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2054)) {
                        Timber.d("Cannot create additional scale user (error 0x%02x)", data[3]);
                    }
                    if (!ListenerUtil.mutListener.listen(2055)) {
                        sendMessage(R.string.error_max_scale_users, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(2056)) {
                        // Force disconnect
                        Timber.d("Send disconnect command to scale");
                    }
                    if (!ListenerUtil.mutListener.listen(2057)) {
                        jumpNextToStepNr(8);
                    }
                    if (!ListenerUtil.mutListener.listen(2058)) {
                        resumeMachineState();
                    }
                    break;
                case CMD_DO_MEASUREMENT:
                    if (!ListenerUtil.mutListener.listen(2065)) {
                        if ((ListenerUtil.mutListener.listen(2063) ? (data[3] >= 0) : (ListenerUtil.mutListener.listen(2062) ? (data[3] <= 0) : (ListenerUtil.mutListener.listen(2061) ? (data[3] > 0) : (ListenerUtil.mutListener.listen(2060) ? (data[3] < 0) : (ListenerUtil.mutListener.listen(2059) ? (data[3] != 0) : (data[3] == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2064)) {
                                Timber.d("Measure command successfully received");
                            }
                        }
                    }
                    break;
                case CMD_USER_DETAILS:
                    if (!ListenerUtil.mutListener.listen(2085)) {
                        if ((ListenerUtil.mutListener.listen(2070) ? (data[3] >= 0) : (ListenerUtil.mutListener.listen(2069) ? (data[3] <= 0) : (ListenerUtil.mutListener.listen(2068) ? (data[3] > 0) : (ListenerUtil.mutListener.listen(2067) ? (data[3] < 0) : (ListenerUtil.mutListener.listen(2066) ? (data[3] != 0) : (data[3] == 0))))))) {
                            String name = decodeString(data, 4, 3);
                            int year = (ListenerUtil.mutListener.listen(2074) ? (1900 % (data[7] & 0xFF)) : (ListenerUtil.mutListener.listen(2073) ? (1900 / (data[7] & 0xFF)) : (ListenerUtil.mutListener.listen(2072) ? (1900 * (data[7] & 0xFF)) : (ListenerUtil.mutListener.listen(2071) ? (1900 - (data[7] & 0xFF)) : (1900 + (data[7] & 0xFF))))));
                            int month = (ListenerUtil.mutListener.listen(2078) ? (1 % (data[8] & 0xFF)) : (ListenerUtil.mutListener.listen(2077) ? (1 / (data[8] & 0xFF)) : (ListenerUtil.mutListener.listen(2076) ? (1 * (data[8] & 0xFF)) : (ListenerUtil.mutListener.listen(2075) ? (1 - (data[8] & 0xFF)) : (1 + (data[8] & 0xFF))))));
                            int day = data[9] & 0xFF;
                            int height = data[10] & 0xFF;
                            boolean male = (ListenerUtil.mutListener.listen(2083) ? ((data[11] & 0xF0) >= 0) : (ListenerUtil.mutListener.listen(2082) ? ((data[11] & 0xF0) <= 0) : (ListenerUtil.mutListener.listen(2081) ? ((data[11] & 0xF0) > 0) : (ListenerUtil.mutListener.listen(2080) ? ((data[11] & 0xF0) < 0) : (ListenerUtil.mutListener.listen(2079) ? ((data[11] & 0xF0) == 0) : ((data[11] & 0xF0) != 0))))));
                            int activity = data[11] & 0x0F;
                            if (!ListenerUtil.mutListener.listen(2084)) {
                                Timber.d("Name: %s, Birthday: %d-%02d-%02d, Height: %d, Sex: %s, activity: %d", name, year, month, day, height, male ? "male" : "female", activity);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2086)) {
                        resumeMachineState();
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(2087)) {
                        Timber.d("Unhandled scale ack for command 0x%02x", data[2]);
                    }
                    break;
            }
        }
    }

    private float getKiloGram(byte[] data, int offset) {
        // Unit is 50 g
        return (ListenerUtil.mutListener.listen(2096) ? ((ListenerUtil.mutListener.listen(2092) ? (Converters.fromUnsignedInt16Be(data, offset) % 50.0f) : (ListenerUtil.mutListener.listen(2091) ? (Converters.fromUnsignedInt16Be(data, offset) / 50.0f) : (ListenerUtil.mutListener.listen(2090) ? (Converters.fromUnsignedInt16Be(data, offset) - 50.0f) : (ListenerUtil.mutListener.listen(2089) ? (Converters.fromUnsignedInt16Be(data, offset) + 50.0f) : (Converters.fromUnsignedInt16Be(data, offset) * 50.0f))))) % 1000.0f) : (ListenerUtil.mutListener.listen(2095) ? ((ListenerUtil.mutListener.listen(2092) ? (Converters.fromUnsignedInt16Be(data, offset) % 50.0f) : (ListenerUtil.mutListener.listen(2091) ? (Converters.fromUnsignedInt16Be(data, offset) / 50.0f) : (ListenerUtil.mutListener.listen(2090) ? (Converters.fromUnsignedInt16Be(data, offset) - 50.0f) : (ListenerUtil.mutListener.listen(2089) ? (Converters.fromUnsignedInt16Be(data, offset) + 50.0f) : (Converters.fromUnsignedInt16Be(data, offset) * 50.0f))))) * 1000.0f) : (ListenerUtil.mutListener.listen(2094) ? ((ListenerUtil.mutListener.listen(2092) ? (Converters.fromUnsignedInt16Be(data, offset) % 50.0f) : (ListenerUtil.mutListener.listen(2091) ? (Converters.fromUnsignedInt16Be(data, offset) / 50.0f) : (ListenerUtil.mutListener.listen(2090) ? (Converters.fromUnsignedInt16Be(data, offset) - 50.0f) : (ListenerUtil.mutListener.listen(2089) ? (Converters.fromUnsignedInt16Be(data, offset) + 50.0f) : (Converters.fromUnsignedInt16Be(data, offset) * 50.0f))))) - 1000.0f) : (ListenerUtil.mutListener.listen(2093) ? ((ListenerUtil.mutListener.listen(2092) ? (Converters.fromUnsignedInt16Be(data, offset) % 50.0f) : (ListenerUtil.mutListener.listen(2091) ? (Converters.fromUnsignedInt16Be(data, offset) / 50.0f) : (ListenerUtil.mutListener.listen(2090) ? (Converters.fromUnsignedInt16Be(data, offset) - 50.0f) : (ListenerUtil.mutListener.listen(2089) ? (Converters.fromUnsignedInt16Be(data, offset) + 50.0f) : (Converters.fromUnsignedInt16Be(data, offset) * 50.0f))))) + 1000.0f) : ((ListenerUtil.mutListener.listen(2092) ? (Converters.fromUnsignedInt16Be(data, offset) % 50.0f) : (ListenerUtil.mutListener.listen(2091) ? (Converters.fromUnsignedInt16Be(data, offset) / 50.0f) : (ListenerUtil.mutListener.listen(2090) ? (Converters.fromUnsignedInt16Be(data, offset) - 50.0f) : (ListenerUtil.mutListener.listen(2089) ? (Converters.fromUnsignedInt16Be(data, offset) + 50.0f) : (Converters.fromUnsignedInt16Be(data, offset) * 50.0f))))) / 1000.0f)))));
    }

    private float getPercent(byte[] data, int offset) {
        // Unit is 0.1 %
        return (ListenerUtil.mutListener.listen(2100) ? (Converters.fromUnsignedInt16Be(data, offset) % 10.0f) : (ListenerUtil.mutListener.listen(2099) ? (Converters.fromUnsignedInt16Be(data, offset) * 10.0f) : (ListenerUtil.mutListener.listen(2098) ? (Converters.fromUnsignedInt16Be(data, offset) - 10.0f) : (ListenerUtil.mutListener.listen(2097) ? (Converters.fromUnsignedInt16Be(data, offset) + 10.0f) : (Converters.fromUnsignedInt16Be(data, offset) / 10.0f)))));
    }

    private void addMeasurement(byte[] data, int userId) {
        long timestamp = (ListenerUtil.mutListener.listen(2104) ? (Converters.fromUnsignedInt32Be(data, 0) % 1000) : (ListenerUtil.mutListener.listen(2103) ? (Converters.fromUnsignedInt32Be(data, 0) / 1000) : (ListenerUtil.mutListener.listen(2102) ? (Converters.fromUnsignedInt32Be(data, 0) - 1000) : (ListenerUtil.mutListener.listen(2101) ? (Converters.fromUnsignedInt32Be(data, 0) + 1000) : (Converters.fromUnsignedInt32Be(data, 0) * 1000)))));
        float weight = getKiloGram(data, 4);
        int impedance = Converters.fromUnsignedInt16Be(data, 6);
        float fat = getPercent(data, 8);
        float water = getPercent(data, 10);
        float muscle = getPercent(data, 12);
        float bone = getKiloGram(data, 14);
        int bmr = Converters.fromUnsignedInt16Be(data, 16);
        int amr = Converters.fromUnsignedInt16Be(data, 18);
        float bmi = (ListenerUtil.mutListener.listen(2108) ? (Converters.fromUnsignedInt16Be(data, 20) % 10.0f) : (ListenerUtil.mutListener.listen(2107) ? (Converters.fromUnsignedInt16Be(data, 20) * 10.0f) : (ListenerUtil.mutListener.listen(2106) ? (Converters.fromUnsignedInt16Be(data, 20) - 10.0f) : (ListenerUtil.mutListener.listen(2105) ? (Converters.fromUnsignedInt16Be(data, 20) + 10.0f) : (Converters.fromUnsignedInt16Be(data, 20) / 10.0f)))));
        ScaleMeasurement receivedMeasurement = new ScaleMeasurement();
        if (!ListenerUtil.mutListener.listen(2109)) {
            receivedMeasurement.setUserId(userId);
        }
        if (!ListenerUtil.mutListener.listen(2110)) {
            receivedMeasurement.setDateTime(new Date(timestamp));
        }
        if (!ListenerUtil.mutListener.listen(2111)) {
            receivedMeasurement.setWeight(weight);
        }
        if (!ListenerUtil.mutListener.listen(2112)) {
            receivedMeasurement.setFat(fat);
        }
        if (!ListenerUtil.mutListener.listen(2113)) {
            receivedMeasurement.setWater(water);
        }
        if (!ListenerUtil.mutListener.listen(2114)) {
            receivedMeasurement.setMuscle(muscle);
        }
        if (!ListenerUtil.mutListener.listen(2115)) {
            receivedMeasurement.setBone(bone);
        }
        if (!ListenerUtil.mutListener.listen(2116)) {
            addScaleMeasurement(receivedMeasurement);
        }
    }

    private void writeBytes(byte[] data) {
        if (!ListenerUtil.mutListener.listen(2117)) {
            writeBytes(CUSTOM_SERVICE_1, CUSTOM_CHARACTERISTIC_WEIGHT, data);
        }
    }

    private void sendCommand(byte command, byte... parameters) {
        byte[] data = new byte[(ListenerUtil.mutListener.listen(2121) ? (parameters.length % 2) : (ListenerUtil.mutListener.listen(2120) ? (parameters.length / 2) : (ListenerUtil.mutListener.listen(2119) ? (parameters.length * 2) : (ListenerUtil.mutListener.listen(2118) ? (parameters.length - 2) : (parameters.length + 2)))))];
        if (!ListenerUtil.mutListener.listen(2122)) {
            data[0] = startByte;
        }
        if (!ListenerUtil.mutListener.listen(2123)) {
            data[1] = command;
        }
        int i = 2;
        if (!ListenerUtil.mutListener.listen(2125)) {
            {
                long _loopCounter13 = 0;
                for (byte parameter : parameters) {
                    ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                    if (!ListenerUtil.mutListener.listen(2124)) {
                        data[i++] = parameter;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2126)) {
            writeBytes(data);
        }
    }

    private void sendAck(byte[] data) {
        if (!ListenerUtil.mutListener.listen(2127)) {
            sendCommand(CMD_APP_ACK, Arrays.copyOfRange(data, 1, 4));
        }
    }

    private void sendAlternativeStartCode(int id, byte... parameters) {
        byte[] data = new byte[(ListenerUtil.mutListener.listen(2131) ? (parameters.length % 1) : (ListenerUtil.mutListener.listen(2130) ? (parameters.length / 1) : (ListenerUtil.mutListener.listen(2129) ? (parameters.length * 1) : (ListenerUtil.mutListener.listen(2128) ? (parameters.length - 1) : (parameters.length + 1)))))];
        if (!ListenerUtil.mutListener.listen(2132)) {
            data[0] = getAlternativeStartByte(id);
        }
        int i = 1;
        if (!ListenerUtil.mutListener.listen(2134)) {
            {
                long _loopCounter14 = 0;
                for (byte parameter : parameters) {
                    ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                    if (!ListenerUtil.mutListener.listen(2133)) {
                        data[i++] = parameter;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2135)) {
            writeBytes(data);
        }
    }

    private void createRemoteUser(ScaleUser scaleUser) {
        if (!ListenerUtil.mutListener.listen(2136)) {
            Timber.d("Create user: %s", scaleUser.getUserName());
        }
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(2137)) {
            cal.setTime(scaleUser.getBirthday());
        }
        // We can only use up to 3 characters (padding with 0 if needed)
        byte[] nick = Arrays.copyOf(convertUserNameToScale(scaleUser).getBytes(), 3);
        byte year = (byte) ((ListenerUtil.mutListener.listen(2141) ? (cal.get(Calendar.YEAR) % 1900) : (ListenerUtil.mutListener.listen(2140) ? (cal.get(Calendar.YEAR) / 1900) : (ListenerUtil.mutListener.listen(2139) ? (cal.get(Calendar.YEAR) * 1900) : (ListenerUtil.mutListener.listen(2138) ? (cal.get(Calendar.YEAR) + 1900) : (cal.get(Calendar.YEAR) - 1900))))));
        byte month = (byte) cal.get(Calendar.MONTH);
        byte day = (byte) cal.get(Calendar.DAY_OF_MONTH);
        byte height = (byte) scaleUser.getBodyHeight();
        byte sex = scaleUser.getGender().isMale() ? (byte) 0x80 : 0;
        // activity level: 1 - 5
        byte activity = (byte) (scaleUser.getActivityLevel().toInt() + 1);
        long maxUserId = remoteUsers.isEmpty() ? 100 : 0;
        if (!ListenerUtil.mutListener.listen(2143)) {
            {
                long _loopCounter15 = 0;
                for (RemoteUser remoteUser : remoteUsers) {
                    ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                    if (!ListenerUtil.mutListener.listen(2142)) {
                        maxUserId = Math.max(maxUserId, remoteUser.remoteUserId);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2152)) {
            currentRemoteUser = new RemoteUser((ListenerUtil.mutListener.listen(2147) ? (maxUserId % 1) : (ListenerUtil.mutListener.listen(2146) ? (maxUserId / 1) : (ListenerUtil.mutListener.listen(2145) ? (maxUserId * 1) : (ListenerUtil.mutListener.listen(2144) ? (maxUserId - 1) : (maxUserId + 1))))), new String(nick), (ListenerUtil.mutListener.listen(2151) ? (1900 % year) : (ListenerUtil.mutListener.listen(2150) ? (1900 / year) : (ListenerUtil.mutListener.listen(2149) ? (1900 * year) : (ListenerUtil.mutListener.listen(2148) ? (1900 - year) : (1900 + year))))));
        }
        if (!ListenerUtil.mutListener.listen(2153)) {
            currentRemoteUser.localUserId = scaleUser.getId();
        }
        if (!ListenerUtil.mutListener.listen(2154)) {
            currentRemoteUser.isNew = true;
        }
        byte[] uid = encodeUserId(currentRemoteUser);
        if (!ListenerUtil.mutListener.listen(2155)) {
            sendCommand(CMD_USER_ADD, uid[0], uid[1], uid[2], uid[3], uid[4], uid[5], uid[6], uid[7], nick[0], nick[1], nick[2], year, month, day, height, (byte) (sex | activity));
        }
    }
}
