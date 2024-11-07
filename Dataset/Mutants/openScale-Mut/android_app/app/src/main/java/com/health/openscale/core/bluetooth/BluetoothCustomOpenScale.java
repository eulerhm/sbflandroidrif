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
import com.health.openscale.core.datatypes.ScaleMeasurement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BluetoothCustomOpenScale extends BluetoothCommunication {

    private final UUID WEIGHT_MEASUREMENT_SERVICE = BluetoothGattUuid.fromShortCode(0xffe0);

    // Bluetooth Modul HM-10
    private final UUID WEIGHT_MEASUREMENT_CHARACTERISTIC = BluetoothGattUuid.fromShortCode(0xffe1);

    private String string_data = new String();

    public BluetoothCustomOpenScale(Context context) {
        super(context);
    }

    @Override
    public String driverName() {
        return "Custom openScale";
    }

    @Override
    protected boolean onNextStep(int stepNr) {
        if (!ListenerUtil.mutListener.listen(2320)) {
            switch(stepNr) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(2310)) {
                        setNotificationOn(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC);
                    }
                    break;
                case 1:
                    Calendar cal = Calendar.getInstance();
                    String date_time = String.format(Locale.US, "2%1d,%1d,%1d,%1d,%1d,%1d,", (ListenerUtil.mutListener.listen(2314) ? (cal.get(Calendar.YEAR) % 2000) : (ListenerUtil.mutListener.listen(2313) ? (cal.get(Calendar.YEAR) / 2000) : (ListenerUtil.mutListener.listen(2312) ? (cal.get(Calendar.YEAR) * 2000) : (ListenerUtil.mutListener.listen(2311) ? (cal.get(Calendar.YEAR) + 2000) : (cal.get(Calendar.YEAR) - 2000))))), (ListenerUtil.mutListener.listen(2318) ? (cal.get(Calendar.MONTH) % 1) : (ListenerUtil.mutListener.listen(2317) ? (cal.get(Calendar.MONTH) / 1) : (ListenerUtil.mutListener.listen(2316) ? (cal.get(Calendar.MONTH) * 1) : (ListenerUtil.mutListener.listen(2315) ? (cal.get(Calendar.MONTH) - 1) : (cal.get(Calendar.MONTH) + 1))))), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
                    if (!ListenerUtil.mutListener.listen(2319)) {
                        writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC, date_time.getBytes());
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public void clearEEPROM() {
        byte[] cmd = { (byte) '9' };
        if (!ListenerUtil.mutListener.listen(2321)) {
            writeBytes(WEIGHT_MEASUREMENT_SERVICE, WEIGHT_MEASUREMENT_CHARACTERISTIC, cmd);
        }
    }

    @Override
    public void onBluetoothNotify(UUID characteristic, byte[] value) {
        final byte[] data = value;
        if (!ListenerUtil.mutListener.listen(2327)) {
            if (data != null) {
                if (!ListenerUtil.mutListener.listen(2326)) {
                    {
                        long _loopCounter19 = 0;
                        for (byte character : data) {
                            ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                            if (!ListenerUtil.mutListener.listen(2322)) {
                                string_data += (char) (character & 0xFF);
                            }
                            if (!ListenerUtil.mutListener.listen(2325)) {
                                if (character == '\n') {
                                    if (!ListenerUtil.mutListener.listen(2323)) {
                                        parseBtString(string_data);
                                    }
                                    if (!ListenerUtil.mutListener.listen(2324)) {
                                        string_data = new String();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void parseBtString(String btString) {
        if (!ListenerUtil.mutListener.listen(2332)) {
            // delete newline '\n' of the string
            btString = btString.substring(0, (ListenerUtil.mutListener.listen(2331) ? (btString.length() % 1) : (ListenerUtil.mutListener.listen(2330) ? (btString.length() / 1) : (ListenerUtil.mutListener.listen(2329) ? (btString.length() * 1) : (ListenerUtil.mutListener.listen(2328) ? (btString.length() + 1) : (btString.length() - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(2335)) {
            if ((ListenerUtil.mutListener.listen(2333) ? (btString.charAt(0) != '$' || btString.charAt(2) != '$') : (btString.charAt(0) != '$' && btString.charAt(2) != '$'))) {
                if (!ListenerUtil.mutListener.listen(2334)) {
                    setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Parse error of bluetooth string. String has not a valid format: " + btString);
                }
            }
        }
        // message string
        String btMsg = btString.substring(3, btString.length());
        if (!ListenerUtil.mutListener.listen(2368)) {
            switch(btString.charAt(1)) {
                case 'I':
                    if (!ListenerUtil.mutListener.listen(2336)) {
                        Timber.d("MCU Information: %s", btMsg);
                    }
                    break;
                case 'E':
                    if (!ListenerUtil.mutListener.listen(2337)) {
                        Timber.e("MCU Error: %s", btMsg);
                    }
                    break;
                case 'S':
                    if (!ListenerUtil.mutListener.listen(2338)) {
                        Timber.d("MCU stored data size: %s", btMsg);
                    }
                    break;
                case 'F':
                    if (!ListenerUtil.mutListener.listen(2339)) {
                        Timber.d("All data sent");
                    }
                    if (!ListenerUtil.mutListener.listen(2340)) {
                        clearEEPROM();
                    }
                    if (!ListenerUtil.mutListener.listen(2341)) {
                        disconnect();
                    }
                    break;
                case 'D':
                    String[] csvField = btMsg.split(",");
                    try {
                        int checksum = 0;
                        if (!ListenerUtil.mutListener.listen(2344)) {
                            checksum ^= Integer.parseInt(csvField[0]);
                        }
                        if (!ListenerUtil.mutListener.listen(2345)) {
                            checksum ^= Integer.parseInt(csvField[1]);
                        }
                        if (!ListenerUtil.mutListener.listen(2346)) {
                            checksum ^= Integer.parseInt(csvField[2]);
                        }
                        if (!ListenerUtil.mutListener.listen(2347)) {
                            checksum ^= Integer.parseInt(csvField[3]);
                        }
                        if (!ListenerUtil.mutListener.listen(2348)) {
                            checksum ^= Integer.parseInt(csvField[4]);
                        }
                        if (!ListenerUtil.mutListener.listen(2349)) {
                            checksum ^= Integer.parseInt(csvField[5]);
                        }
                        if (!ListenerUtil.mutListener.listen(2350)) {
                            checksum ^= (int) Float.parseFloat(csvField[6]);
                        }
                        if (!ListenerUtil.mutListener.listen(2351)) {
                            checksum ^= (int) Float.parseFloat(csvField[7]);
                        }
                        if (!ListenerUtil.mutListener.listen(2352)) {
                            checksum ^= (int) Float.parseFloat(csvField[8]);
                        }
                        if (!ListenerUtil.mutListener.listen(2353)) {
                            checksum ^= (int) Float.parseFloat(csvField[9]);
                        }
                        int btChecksum = Integer.parseInt(csvField[10]);
                        if (!ListenerUtil.mutListener.listen(2366)) {
                            if ((ListenerUtil.mutListener.listen(2358) ? (checksum >= btChecksum) : (ListenerUtil.mutListener.listen(2357) ? (checksum <= btChecksum) : (ListenerUtil.mutListener.listen(2356) ? (checksum > btChecksum) : (ListenerUtil.mutListener.listen(2355) ? (checksum < btChecksum) : (ListenerUtil.mutListener.listen(2354) ? (checksum != btChecksum) : (checksum == btChecksum))))))) {
                                ScaleMeasurement scaleBtData = new ScaleMeasurement();
                                String date_string = csvField[1] + "/" + csvField[2] + "/" + csvField[3] + "/" + csvField[4] + "/" + csvField[5];
                                if (!ListenerUtil.mutListener.listen(2360)) {
                                    scaleBtData.setDateTime(new SimpleDateFormat("yyyy/MM/dd/HH/mm").parse(date_string));
                                }
                                if (!ListenerUtil.mutListener.listen(2361)) {
                                    scaleBtData.setWeight(Float.parseFloat(csvField[6]));
                                }
                                if (!ListenerUtil.mutListener.listen(2362)) {
                                    scaleBtData.setFat(Float.parseFloat(csvField[7]));
                                }
                                if (!ListenerUtil.mutListener.listen(2363)) {
                                    scaleBtData.setWater(Float.parseFloat(csvField[8]));
                                }
                                if (!ListenerUtil.mutListener.listen(2364)) {
                                    scaleBtData.setMuscle(Float.parseFloat(csvField[9]));
                                }
                                if (!ListenerUtil.mutListener.listen(2365)) {
                                    addScaleMeasurement(scaleBtData);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2359)) {
                                    setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Error calculated checksum (" + checksum + ") and received checksum (" + btChecksum + ") is different");
                                }
                            }
                        }
                    } catch (ParseException e) {
                        if (!ListenerUtil.mutListener.listen(2342)) {
                            setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Error while decoding bluetooth date string (" + e.getMessage() + ")");
                        }
                    } catch (NumberFormatException e) {
                        if (!ListenerUtil.mutListener.listen(2343)) {
                            setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Error while decoding a number of bluetooth string (" + e.getMessage() + ")");
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(2367)) {
                        setBluetoothStatus(BT_STATUS.UNEXPECTED_ERROR, "Error unknown MCU command : " + btString);
                    }
            }
        }
    }
}
