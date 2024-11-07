/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.adapters.decorators;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.mapbox.mapboxsdk.geometry.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import ch.threema.app.R;
import ch.threema.app.activities.MapActivity;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.GeoLocationUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.data.LocationDataModel;
import static android.view.View.GONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LocationChatAdapterDecorator.class);

    public LocationChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        final LocationDataModel location = this.getMessageModel().getLocationData();
        TextView addressLine = holder.bodyTextView;
        if (!ListenerUtil.mutListener.listen(7836)) {
            this.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7835)) {
                        if (!isInChoiceMode()) {
                            if (!ListenerUtil.mutListener.listen(7834)) {
                                viewLocation(getMessageModel(), v);
                            }
                        }
                    }
                }
            }, holder.messageBlockView);
        }
        if (!ListenerUtil.mutListener.listen(7838)) {
            // clear texts
            if (holder.bodyTextView != null) {
                if (!ListenerUtil.mutListener.listen(7837)) {
                    holder.bodyTextView.setText("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7841)) {
            if (holder.secondaryTextView != null) {
                if (!ListenerUtil.mutListener.listen(7839)) {
                    holder.secondaryTextView.setText("");
                }
                if (!ListenerUtil.mutListener.listen(7840)) {
                    holder.secondaryTextView.setVisibility(GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7847)) {
            if (!TestUtil.empty(location.getPoi())) {
                if (!ListenerUtil.mutListener.listen(7845)) {
                    if (holder.bodyTextView != null) {
                        if (!ListenerUtil.mutListener.listen(7842)) {
                            holder.bodyTextView.setText(highlightMatches(location.getPoi(), filterString));
                        }
                        if (!ListenerUtil.mutListener.listen(7843)) {
                            holder.bodyTextView.setWidth(this.getThumbnailWidth());
                        }
                        if (!ListenerUtil.mutListener.listen(7844)) {
                            holder.bodyTextView.setVisibility(View.VISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7846)) {
                    addressLine = holder.secondaryTextView;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7857)) {
            if (addressLine != null) {
                String address = location.getAddress();
                if (!ListenerUtil.mutListener.listen(7852)) {
                    if (address == null) {
                        GeoLocationUtil geoLocation = new GeoLocationUtil(addressLine);
                        Location l = new Location("X");
                        if (!ListenerUtil.mutListener.listen(7848)) {
                            l.setLatitude(location.getLatitude());
                        }
                        if (!ListenerUtil.mutListener.listen(7849)) {
                            l.setLongitude(location.getLongitude());
                        }
                        if (!ListenerUtil.mutListener.listen(7850)) {
                            l.setAccuracy(location.getAccuracy());
                        }
                        if (!ListenerUtil.mutListener.listen(7851)) {
                            geoLocation.updateAddressAndModel(getContext(), l);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7856)) {
                    if (address != null) {
                        if (!ListenerUtil.mutListener.listen(7853)) {
                            addressLine.setText(highlightMatches(address, filterString));
                        }
                        if (!ListenerUtil.mutListener.listen(7854)) {
                            addressLine.setWidth(this.getThumbnailWidth());
                        }
                        if (!ListenerUtil.mutListener.listen(7855)) {
                            addressLine.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7868)) {
            new AsyncTask<ComposeMessageHolder, Void, RoundedBitmapDrawable>() {

                private ComposeMessageHolder holder;

                @Override
                protected RoundedBitmapDrawable doInBackground(ComposeMessageHolder... params) {
                    if (!ListenerUtil.mutListener.listen(7858)) {
                        this.holder = params[0];
                    }
                    try {
                        Bitmap locationBitmap = getFileService().getMessageThumbnailBitmap(getMessageModel(), getThumbnailCache());
                        if (!ListenerUtil.mutListener.listen(7861)) {
                            if (locationBitmap != null) {
                                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), BitmapUtil.cropToSquare(locationBitmap));
                                if (!ListenerUtil.mutListener.listen(7860)) {
                                    drawable.setCircular(true);
                                }
                                return drawable;
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(7859)) {
                            logger.error("Exception", e);
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(RoundedBitmapDrawable drawable) {
                    if (!ListenerUtil.mutListener.listen(7867)) {
                        if (position == holder.position) {
                            if (!ListenerUtil.mutListener.listen(7866)) {
                                if (drawable == null) {
                                    if (!ListenerUtil.mutListener.listen(7864)) {
                                        holder.controller.setBackgroundImage(null);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7865)) {
                                        holder.controller.setImageResource(R.drawable.ic_map_marker_outline);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7862)) {
                                        holder.controller.setNeutral();
                                    }
                                    if (!ListenerUtil.mutListener.listen(7863)) {
                                        holder.controller.setBackgroundDrawable(drawable);
                                    }
                                }
                            }
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, holder);
        }
    }

    private void viewLocation(AbstractMessageModel messageModel, final View v) {
        if (!ListenerUtil.mutListener.listen(7875)) {
            if (!ConfigUtils.hasNoMapboxSupport()) {
                if (!ListenerUtil.mutListener.listen(7874)) {
                    if (messageModel != null) {
                        LocationDataModel locationData = messageModel.getLocationData();
                        if (!ListenerUtil.mutListener.listen(7873)) {
                            if (locationData != null) {
                                Intent intent = new Intent(getContext(), MapActivity.class);
                                if (!ListenerUtil.mutListener.listen(7871)) {
                                    IntentDataUtil.append(new LatLng(messageModel.getLocationData().getLatitude(), messageModel.getLocationData().getLongitude()), getContext().getString(R.string.app_name), messageModel.getLocationData().getPoi(), messageModel.getLocationData().getAddress(), intent);
                                }
                                if (!ListenerUtil.mutListener.listen(7872)) {
                                    getContext().startActivity(intent);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7870)) {
                    RuntimeUtil.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(7869)) {
                                Toast.makeText(getContext(), "Feature not available due to firmware error", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        }
    }
}
