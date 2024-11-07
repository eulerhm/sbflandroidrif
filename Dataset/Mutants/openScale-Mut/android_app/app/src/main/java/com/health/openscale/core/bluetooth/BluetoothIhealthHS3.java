/*  Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*   Copyright (C) 2018  John Lines <john+openscale@paladyn.org>
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

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothIhealthHS3 extends BluetoothCommunication {

    // Standard SerialPortService ID
    private final UUID uuid = BluetoothGattUuid.fromShortCode(0x1101);

    private BluetoothSocket btSocket = null;

    private BluetoothDevice btDevice = null;

    private BluetoothConnectedThread btConnectThread = null;

    private byte[] lastWeight = new byte[2];

    private Date lastWeighed = new Date();

    // maximum time interval we will consider two identical
    private final long maxTimeDiff = 60000;

    public BluetoothIhealthHS3(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "iHealth HS33FA4A";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(2772)) {
            Timber.w("ihealthHS3 - onNextStep - returning false");
        }
        return false;
    }

    @Override
    public void connect(String hwAddress) {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!ListenerUtil.mutListener.listen(2774)) {
            if (btAdapter == null) {
                if (!ListenerUtil.mutListener.listen(2773)) {
                    setBluetoothStatus(BT_STATUS.NO_DEVICE_FOUND);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2775)) {
            btDevice = btAdapter.getRemoteDevice(hwAddress);
        }
        try {
            if (!ListenerUtil.mutListener.listen(2778)) {
                // Get a BluetoothSocket to connect with the given BluetoothDevice
                btSocket = btDevice.createRfcommSocketToServiceRecord(uuid);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(2776)) {
                setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Can't get a bluetooth socket");
            }
            if (!ListenerUtil.mutListener.listen(2777)) {
                btDevice = null;
            }
            return;
        }
        Thread socketThread = new Thread() {

            @Override
            public void run() {
                try {
                    if (!ListenerUtil.mutListener.listen(2785)) {
                        if (!btSocket.isConnected()) {
                            if (!ListenerUtil.mutListener.listen(2781)) {
                                // until it succeeds or throws an exception
                                btSocket.connect();
                            }
                            if (!ListenerUtil.mutListener.listen(2782)) {
                                // Bluetooth connection was successful
                                setBluetoothStatus(BT_STATUS.CONNECTION_ESTABLISHED);
                            }
                            if (!ListenerUtil.mutListener.listen(2783)) {
                                btConnectThread = new BluetoothConnectedThread();
                            }
                            if (!ListenerUtil.mutListener.listen(2784)) {
                                btConnectThread.start();
                            }
                        }
                    }
                } catch (IOException connectException) {
                    if (!ListenerUtil.mutListener.listen(2779)) {
                        // Unable to connect; close the socket and get out
                        disconnect();
                    }
                    if (!ListenerUtil.mutListener.listen(2780)) {
                        setBluetoothStatus(BT_STATUS.NO_DEVICE_FOUND);
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(2786)) {
            socketThread.start();
        }
    }

    @Override
    public void disconnect() {
        if (!ListenerUtil.mutListener.listen(2787)) {
            Timber.w("HS3 - disconnect");
        }
        if (!ListenerUtil.mutListener.listen(2792)) {
            if (btSocket != null) {
                if (!ListenerUtil.mutListener.listen(2791)) {
                    if (btSocket.isConnected()) {
                        try {
                            if (!ListenerUtil.mutListener.listen(2789)) {
                                btSocket.close();
                            }
                            if (!ListenerUtil.mutListener.listen(2790)) {
                                btSocket = null;
                            }
                        } catch (IOException closeException) {
                            if (!ListenerUtil.mutListener.listen(2788)) {
                                setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Can't close bluetooth socket");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2795)) {
            if (btConnectThread != null) {
                if (!ListenerUtil.mutListener.listen(2793)) {
                    btConnectThread.cancel();
                }
                if (!ListenerUtil.mutListener.listen(2794)) {
                    btConnectThread = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2796)) {
            btDevice = null;
        }
    }

    private boolean sendBtData(String data) {
        if (!ListenerUtil.mutListener.listen(2797)) {
            Timber.w("ihealthHS3 - sendBtData %s", data);
        }
        if (!ListenerUtil.mutListener.listen(2801)) {
            if (btSocket.isConnected()) {
                if (!ListenerUtil.mutListener.listen(2798)) {
                    btConnectThread = new BluetoothConnectedThread();
                }
                if (!ListenerUtil.mutListener.listen(2799)) {
                    btConnectThread.write(data.getBytes());
                }
                if (!ListenerUtil.mutListener.listen(2800)) {
                    btConnectThread.cancel();
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(2802)) {
            Timber.w("ihealthHS3 - sendBtData - socket is not connected");
        }
        return false;
    }

    private class BluetoothConnectedThread extends Thread {

        private InputStream btInStream;

        private OutputStream btOutStream;

        private volatile boolean isCancel;

        public BluetoothConnectedThread() {
            if (!ListenerUtil.mutListener.listen(2803)) {
                // Timber.w("ihealthHS3 - BluetoothConnectedThread");
                isCancel = false;
            }
            // Get the input and output bluetooth streams
            try {
                if (!ListenerUtil.mutListener.listen(2805)) {
                    btInStream = btSocket.getInputStream();
                }
                if (!ListenerUtil.mutListener.listen(2806)) {
                    btOutStream = btSocket.getOutputStream();
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(2804)) {
                    setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Can't get bluetooth input or output stream " + e.getMessage());
                }
            }
        }

        public void run() {
            byte btByte;
            byte[] weightBytes = new byte[2];
            {
                long _loopCounter31 = 0;
                // Keep listening to the InputStream until an exception occurs (e.g. device partner goes offline)
                while (!isCancel) {
                    ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                    try {
                        btByte = (byte) btInStream.read();
                        if ((ListenerUtil.mutListener.listen(2813) ? (btByte >= (byte) 0xA0) : (ListenerUtil.mutListener.listen(2812) ? (btByte <= (byte) 0xA0) : (ListenerUtil.mutListener.listen(2811) ? (btByte > (byte) 0xA0) : (ListenerUtil.mutListener.listen(2810) ? (btByte < (byte) 0xA0) : (ListenerUtil.mutListener.listen(2809) ? (btByte != (byte) 0xA0) : (btByte == (byte) 0xA0))))))) {
                            btByte = (byte) btInStream.read();
                            if ((ListenerUtil.mutListener.listen(2818) ? (btByte >= (byte) 0x09) : (ListenerUtil.mutListener.listen(2817) ? (btByte <= (byte) 0x09) : (ListenerUtil.mutListener.listen(2816) ? (btByte > (byte) 0x09) : (ListenerUtil.mutListener.listen(2815) ? (btByte < (byte) 0x09) : (ListenerUtil.mutListener.listen(2814) ? (btByte != (byte) 0x09) : (btByte == (byte) 0x09))))))) {
                                btByte = (byte) btInStream.read();
                                if ((ListenerUtil.mutListener.listen(2823) ? (btByte >= (byte) 0xa6) : (ListenerUtil.mutListener.listen(2822) ? (btByte <= (byte) 0xa6) : (ListenerUtil.mutListener.listen(2821) ? (btByte > (byte) 0xa6) : (ListenerUtil.mutListener.listen(2820) ? (btByte < (byte) 0xa6) : (ListenerUtil.mutListener.listen(2819) ? (btByte != (byte) 0xa6) : (btByte == (byte) 0xa6))))))) {
                                    btByte = (byte) btInStream.read();
                                    if ((ListenerUtil.mutListener.listen(2828) ? (btByte >= (byte) 0x28) : (ListenerUtil.mutListener.listen(2827) ? (btByte <= (byte) 0x28) : (ListenerUtil.mutListener.listen(2826) ? (btByte > (byte) 0x28) : (ListenerUtil.mutListener.listen(2825) ? (btByte < (byte) 0x28) : (ListenerUtil.mutListener.listen(2824) ? (btByte != (byte) 0x28) : (btByte == (byte) 0x28))))))) {
                                        // deal with a weight packet - read 5 bytes we dont care about
                                        btByte = (byte) btInStream.read();
                                        btByte = (byte) btInStream.read();
                                        btByte = (byte) btInStream.read();
                                        btByte = (byte) btInStream.read();
                                        btByte = (byte) btInStream.read();
                                        if (!ListenerUtil.mutListener.listen(2836)) {
                                            // and the weight - which should follow
                                            weightBytes[0] = (byte) btInStream.read();
                                        }
                                        if (!ListenerUtil.mutListener.listen(2837)) {
                                            weightBytes[1] = (byte) btInStream.read();
                                        }
                                        ScaleMeasurement scaleMeasurement = parseWeightArray(weightBytes);
                                        if (!ListenerUtil.mutListener.listen(2839)) {
                                            if (scaleMeasurement != null) {
                                                if (!ListenerUtil.mutListener.listen(2838)) {
                                                    addScaleMeasurement(scaleMeasurement);
                                                }
                                            }
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(2833) ? (btByte >= (byte) 0x33) : (ListenerUtil.mutListener.listen(2832) ? (btByte <= (byte) 0x33) : (ListenerUtil.mutListener.listen(2831) ? (btByte > (byte) 0x33) : (ListenerUtil.mutListener.listen(2830) ? (btByte < (byte) 0x33) : (ListenerUtil.mutListener.listen(2829) ? (btByte != (byte) 0x33) : (btByte == (byte) 0x33))))))) {
                                        if (!ListenerUtil.mutListener.listen(2835)) {
                                            Timber.w("seen 0xa009a633 - time packet");
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(2834)) {
                                            Timber.w("iHealthHS3 - seen byte after control leader %02X", btByte);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(2807)) {
                            cancel();
                        }
                        if (!ListenerUtil.mutListener.listen(2808)) {
                            setBluetoothStatus(BT_STATUS.CONNECTION_LOST);
                        }
                    }
                }
            }
        }

        private ScaleMeasurement parseWeightArray(byte[] weightBytes) throws IOException {
            ScaleMeasurement scaleBtData = new ScaleMeasurement();
            String ws = String.format("%02X", weightBytes[0]) + String.format("%02X", weightBytes[1]);
            StringBuilder ws1 = new StringBuilder(ws);
            if (!ListenerUtil.mutListener.listen(2844)) {
                ws1.insert((ListenerUtil.mutListener.listen(2843) ? (ws.length() % 1) : (ListenerUtil.mutListener.listen(2842) ? (ws.length() / 1) : (ListenerUtil.mutListener.listen(2841) ? (ws.length() * 1) : (ListenerUtil.mutListener.listen(2840) ? (ws.length() + 1) : (ws.length() - 1))))), ".");
            }
            float weight = Float.parseFloat(ws1.toString());
            Date now = new Date();
            if (!ListenerUtil.mutListener.listen(2855)) {
                // If the weight is the same as the lastWeight, and the time since the last reading is less than maxTimeDiff then return null
                if ((ListenerUtil.mutListener.listen(2854) ? (Arrays.equals(weightBytes, lastWeight) || ((ListenerUtil.mutListener.listen(2853) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) >= maxTimeDiff) : (ListenerUtil.mutListener.listen(2852) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) <= maxTimeDiff) : (ListenerUtil.mutListener.listen(2851) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) > maxTimeDiff) : (ListenerUtil.mutListener.listen(2850) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) != maxTimeDiff) : (ListenerUtil.mutListener.listen(2849) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) == maxTimeDiff) : ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) < maxTimeDiff)))))))) : (Arrays.equals(weightBytes, lastWeight) && ((ListenerUtil.mutListener.listen(2853) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) >= maxTimeDiff) : (ListenerUtil.mutListener.listen(2852) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) <= maxTimeDiff) : (ListenerUtil.mutListener.listen(2851) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) > maxTimeDiff) : (ListenerUtil.mutListener.listen(2850) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) != maxTimeDiff) : (ListenerUtil.mutListener.listen(2849) ? ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) == maxTimeDiff) : ((ListenerUtil.mutListener.listen(2848) ? (now.getTime() % lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2847) ? (now.getTime() / lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2846) ? (now.getTime() * lastWeighed.getTime()) : (ListenerUtil.mutListener.listen(2845) ? (now.getTime() + lastWeighed.getTime()) : (now.getTime() - lastWeighed.getTime()))))) < maxTimeDiff)))))))))) {
                    // Timber.w("iHealthHS3 - parseWeightArray returning null");
                    return null;
                }
            }
            if (!ListenerUtil.mutListener.listen(2856)) {
                scaleBtData.setDateTime(now);
            }
            if (!ListenerUtil.mutListener.listen(2857)) {
                scaleBtData.setWeight(weight);
            }
            if (!ListenerUtil.mutListener.listen(2858)) {
                lastWeighed = now;
            }
            if (!ListenerUtil.mutListener.listen(2859)) {
                System.arraycopy(weightBytes, 0, lastWeight, 0, lastWeight.length);
            }
            return scaleBtData;
        }

        public void write(byte[] bytes) {
            try {
                if (!ListenerUtil.mutListener.listen(2861)) {
                    btOutStream.write(bytes);
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(2860)) {
                    setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Error while writing to bluetooth socket " + e.getMessage());
                }
            }
        }

        public void cancel() {
            if (!ListenerUtil.mutListener.listen(2862)) {
                isCancel = true;
            }
        }
    }
}
