/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import ch.threema.app.R;
import ch.threema.app.services.MessageService;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.data.LocationDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GeoLocationUtil {

    private static final Logger logger = LoggerFactory.getLogger(GeoLocationUtil.class);

    private TextView targetView;

    private static final Map<String, String> addressCache = new HashMap<String, String>();

    public GeoLocationUtil(TextView targetView) {
        if (!ListenerUtil.mutListener.listen(54130)) {
            this.targetView = targetView;
        }
    }

    public static String getAddressFromLocation(Context context, double latitude, double longitude) throws IOException {
        String addressString = context.getString(R.string.unknown_address);
        String key = String.valueOf(latitude) + '|' + String.valueOf(longitude);
        if (!ListenerUtil.mutListener.listen(54145)) {
            if (Geocoder.isPresent()) {
                synchronized (addressCache) {
                    if (!ListenerUtil.mutListener.listen(54144)) {
                        if (addressCache.containsKey(key)) {
                            if (!ListenerUtil.mutListener.listen(54143)) {
                                addressString = addressCache.get(key);
                            }
                        } else {
                            List<Address> addresses = null;
                            try {
                                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                if (!ListenerUtil.mutListener.listen(54132)) {
                                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(54131)) {
                                    logger.error("Exception", e);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(54142)) {
                                if ((ListenerUtil.mutListener.listen(54138) ? (addresses != null || (ListenerUtil.mutListener.listen(54137) ? (addresses.size() >= 0) : (ListenerUtil.mutListener.listen(54136) ? (addresses.size() <= 0) : (ListenerUtil.mutListener.listen(54135) ? (addresses.size() < 0) : (ListenerUtil.mutListener.listen(54134) ? (addresses.size() != 0) : (ListenerUtil.mutListener.listen(54133) ? (addresses.size() == 0) : (addresses.size() > 0))))))) : (addresses != null && (ListenerUtil.mutListener.listen(54137) ? (addresses.size() >= 0) : (ListenerUtil.mutListener.listen(54136) ? (addresses.size() <= 0) : (ListenerUtil.mutListener.listen(54135) ? (addresses.size() < 0) : (ListenerUtil.mutListener.listen(54134) ? (addresses.size() != 0) : (ListenerUtil.mutListener.listen(54133) ? (addresses.size() == 0) : (addresses.size() > 0))))))))) {
                                    Address address = addresses.get(0);
                                    if (!ListenerUtil.mutListener.listen(54141)) {
                                        if (address != null) {
                                            if (!ListenerUtil.mutListener.listen(54139)) {
                                                addressString = StringConversionUtil.join(", ", address.getAddressLine(0), address.getLocality());
                                            }
                                            if (!ListenerUtil.mutListener.listen(54140)) {
                                                addressCache.put(key, addressString);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return addressString;
    }

    public void updateAddressAndModel(Context context, Location location) {
        if (!ListenerUtil.mutListener.listen(54146)) {
            if (location == null)
                return;
        }
        GeocoderHandler handler = new GeocoderHandler();
        if (!ListenerUtil.mutListener.listen(54147)) {
            getAddress(context, location.getLatitude(), location.getLongitude(), handler);
        }
    }

    public void getAddress(final Context context, final double latitude, final double longitude, final Handler handler) {
        if (!ListenerUtil.mutListener.listen(54157)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    String addressText = null;
                    try {
                        if (!ListenerUtil.mutListener.listen(54156)) {
                            addressText = GeoLocationUtil.getAddressFromLocation(context, latitude, longitude);
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(54148)) {
                            logger.error("Exception", e);
                        }
                    } finally {
                        Message msg = Message.obtain();
                        if (!ListenerUtil.mutListener.listen(54149)) {
                            msg.setTarget(handler);
                        }
                        if (!ListenerUtil.mutListener.listen(54154)) {
                            if (addressText != null) {
                                if (!ListenerUtil.mutListener.listen(54151)) {
                                    msg.what = 1;
                                }
                                Bundle bundle = new Bundle();
                                if (!ListenerUtil.mutListener.listen(54152)) {
                                    bundle.putString("address", addressText);
                                }
                                if (!ListenerUtil.mutListener.listen(54153)) {
                                    msg.setData(bundle);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(54150)) {
                                    msg.what = 0;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(54155)) {
                            msg.sendToTarget();
                        }
                    }
                }
            }).start();
        }
    }

    public class GeocoderHandler extends Handler {

        private MessageService messageService;

        private AbstractMessageModel messageModel;

        public GeocoderHandler() {
        }

        public GeocoderHandler(MessageService messageService, AbstractMessageModel messageModel) {
            if (!ListenerUtil.mutListener.listen(54158)) {
                this.messageService = messageService;
            }
            if (!ListenerUtil.mutListener.listen(54159)) {
                this.messageModel = messageModel;
            }
        }

        @Override
        public void handleMessage(Message message) {
            String result;
            switch(message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    result = bundle.getString("address");
                    if (!ListenerUtil.mutListener.listen(54160)) {
                        targetView.setText(result);
                    }
                    if (!ListenerUtil.mutListener.listen(54164)) {
                        if (TestUtil.required(this.messageModel, this.messageService)) {
                            LocationDataModel l = messageModel.getLocationData();
                            if (!ListenerUtil.mutListener.listen(54163)) {
                                if (l != null) {
                                    if (!ListenerUtil.mutListener.listen(54161)) {
                                        l.setAddress(result);
                                    }
                                    if (!ListenerUtil.mutListener.listen(54162)) {
                                        this.messageService.save(messageModel);
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
                    result = null;
            }
        }
    }

    public static Uri getLocationUri(AbstractMessageModel model) {
        double latitude = model.getLocationData().getLatitude();
        double longitude = model.getLocationData().getLongitude();
        String locationName = model.getLocationData().getPoi();
        String address = model.getLocationData().getAddress();
        return getLocationUri(latitude, longitude, locationName, address);
    }

    public static Uri getLocationUri(double latitude, double longitude, String locationName, String address) {
        String geoString = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        if (!ListenerUtil.mutListener.listen(54166)) {
            if (TestUtil.empty(locationName)) {
                if (!ListenerUtil.mutListener.listen(54165)) {
                    locationName = address;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54169)) {
            if (!TestUtil.empty(locationName)) {
                try {
                    if (!ListenerUtil.mutListener.listen(54168)) {
                        locationName = URLEncoder.encode(locationName, "utf-8");
                    }
                    return Uri.parse(geoString + "(" + locationName + ")");
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(54167)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return Uri.parse(geoString);
    }
}
