package net.programmierecke.radiodroid2.service;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;
import androidx.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import static net.programmierecke.radiodroid2.Utils.resourceToUri;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioDroidBrowser {

    private static final String MEDIA_ID_ROOT = "__ROOT__";

    private static final String MEDIA_ID_MUSICS_FAVORITE = "__FAVORITE__";

    private static final String MEDIA_ID_MUSICS_HISTORY = "__HISTORY__";

    private static final String MEDIA_ID_MUSICS_TOP = "__TOP__";

    private static final String MEDIA_ID_MUSICS_TOP_TAGS = "__TOP_TAGS__";

    private static final char LEAF_SEPARATOR = '|';

    private static final int IMAGE_LOAD_TIMEOUT_MS = 2000;

    private RadioDroidApp radioDroidApp;

    private Map<String, DataRadioStation> stationIdToStation = new HashMap<>();

    private static class RetrieveStationsIconAndSendResult extends AsyncTask<Void, Void, Void> {

        private MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result;

        private List<DataRadioStation> stations;

        private WeakReference<Context> contextRef;

        private Map<String, Bitmap> stationIdToIcon = new HashMap<>();

        private CountDownLatch countDownLatch;

        private Resources resources;

        // Picasso stores weak references to targets
        List<Target> imageLoadTargets = new ArrayList<>();

        RetrieveStationsIconAndSendResult(MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, List<DataRadioStation> stations, Context context) {
            if (!ListenerUtil.mutListener.listen(2298)) {
                this.result = result;
            }
            if (!ListenerUtil.mutListener.listen(2299)) {
                this.stations = stations;
            }
            if (!ListenerUtil.mutListener.listen(2300)) {
                this.contextRef = new WeakReference<>(context);
            }
            if (!ListenerUtil.mutListener.listen(2301)) {
                resources = context.getApplicationContext().getResources();
            }
        }

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(2302)) {
                countDownLatch = new CountDownLatch(stations.size());
            }
            if (!ListenerUtil.mutListener.listen(2310)) {
                {
                    long _loopCounter33 = 0;
                    for (final DataRadioStation station : stations) {
                        ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                        Context context = contextRef.get();
                        if (!ListenerUtil.mutListener.listen(2303)) {
                            if (context == null) {
                                break;
                            }
                        }
                        Target imageLoadTarget = new Target() {

                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                if (!ListenerUtil.mutListener.listen(2304)) {
                                    stationIdToIcon.put(station.StationUuid, bitmap);
                                }
                                if (!ListenerUtil.mutListener.listen(2305)) {
                                    countDownLatch.countDown();
                                }
                            }

                            @Override
                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                if (!ListenerUtil.mutListener.listen(2306)) {
                                    onBitmapLoaded(((BitmapDrawable) errorDrawable).getBitmap(), null);
                                }
                                if (!ListenerUtil.mutListener.listen(2307)) {
                                    countDownLatch.countDown();
                                }
                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {
                            }
                        };
                        if (!ListenerUtil.mutListener.listen(2308)) {
                            imageLoadTargets.add(imageLoadTarget);
                        }
                        if (!ListenerUtil.mutListener.listen(2309)) {
                            Picasso.get().load((!station.hasIcon() ? resourceToUri(resources, R.drawable.ic_launcher).toString() : station.IconUrl)).transform(new CropSquareTransformation()).error(R.drawable.ic_launcher).transform(Utils.useCircularIcons(context) ? new CropCircleTransformation() : new CropSquareTransformation()).transform(new RoundedCornersTransformation(12, 2, RoundedCornersTransformation.CornerType.ALL)).resize(128, 128).into(imageLoadTarget);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2311)) {
                super.onPreExecute();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (!ListenerUtil.mutListener.listen(2313)) {
                    countDownLatch.await(IMAGE_LOAD_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                }
            } catch (InterruptedException e) {
                if (!ListenerUtil.mutListener.listen(2312)) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Context context = contextRef.get();
            if (!ListenerUtil.mutListener.listen(2316)) {
                if (context != null) {
                    if (!ListenerUtil.mutListener.listen(2315)) {
                        {
                            long _loopCounter34 = 0;
                            for (Target target : imageLoadTargets) {
                                ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                                if (!ListenerUtil.mutListener.listen(2314)) {
                                    Picasso.get().cancelRequest(target);
                                }
                            }
                        }
                    }
                }
            }
            List<MediaBrowserCompat.MediaItem> mediaItems = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(2322)) {
                {
                    long _loopCounter35 = 0;
                    for (DataRadioStation station : stations) {
                        ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                        Bitmap stationIcon = stationIdToIcon.get(station.StationUuid);
                        if (!ListenerUtil.mutListener.listen(2318)) {
                            if (stationIcon == null)
                                if (!ListenerUtil.mutListener.listen(2317)) {
                                    stationIcon = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_launcher);
                                }
                        }
                        Bundle extras = new Bundle();
                        if (!ListenerUtil.mutListener.listen(2319)) {
                            extras.putParcelable(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, stationIcon);
                        }
                        if (!ListenerUtil.mutListener.listen(2320)) {
                            extras.putParcelable(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, stationIcon);
                        }
                        if (!ListenerUtil.mutListener.listen(2321)) {
                            mediaItems.add(new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(MEDIA_ID_MUSICS_HISTORY + LEAF_SEPARATOR + station.StationUuid).setTitle(station.Name).setIconBitmap(stationIcon).setExtras(extras).build(), MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2323)) {
                result.sendResult(mediaItems);
            }
            if (!ListenerUtil.mutListener.listen(2324)) {
                super.onPostExecute(aVoid);
            }
        }
    }

    public RadioDroidBrowser(RadioDroidApp radioDroidApp) {
        if (!ListenerUtil.mutListener.listen(2325)) {
            this.radioDroidApp = radioDroidApp;
        }
    }

    @Nullable
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return new MediaBrowserServiceCompat.BrowserRoot(MEDIA_ID_ROOT, null);
    }

    public void onLoadChildren(@NonNull String parentId, @NonNull MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Resources resources = radioDroidApp.getResources();
        if (!ListenerUtil.mutListener.listen(2327)) {
            if (MEDIA_ID_ROOT.equals(parentId)) {
                if (!ListenerUtil.mutListener.listen(2326)) {
                    result.sendResult(createBrowsableMediaItemsForRoot(resources));
                }
                return;
            }
        }
        List<MediaBrowserCompat.MediaItem> mediaItems = new ArrayList<>();
        List<DataRadioStation> stations = null;
        if (!ListenerUtil.mutListener.listen(2330)) {
            switch(parentId) {
                case MEDIA_ID_MUSICS_FAVORITE:
                    {
                        if (!ListenerUtil.mutListener.listen(2328)) {
                            stations = radioDroidApp.getFavouriteManager().getList();
                        }
                        break;
                    }
                case MEDIA_ID_MUSICS_HISTORY:
                    {
                        if (!ListenerUtil.mutListener.listen(2329)) {
                            stations = radioDroidApp.getHistoryManager().getList();
                        }
                        break;
                    }
                case MEDIA_ID_MUSICS_TOP:
                    {
                        break;
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(2338)) {
            if ((ListenerUtil.mutListener.listen(2331) ? (stations != null || !stations.isEmpty()) : (stations != null && !stations.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(2333)) {
                    stationIdToStation.clear();
                }
                if (!ListenerUtil.mutListener.listen(2335)) {
                    {
                        long _loopCounter36 = 0;
                        for (DataRadioStation station : stations) {
                            ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                            if (!ListenerUtil.mutListener.listen(2334)) {
                                stationIdToStation.put(station.StationUuid, station);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2336)) {
                    result.detach();
                }
                if (!ListenerUtil.mutListener.listen(2337)) {
                    new RetrieveStationsIconAndSendResult(result, stations, radioDroidApp).execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2332)) {
                    result.sendResult(mediaItems);
                }
            }
        }
    }

    @Nullable
    public DataRadioStation getStationById(@NonNull String stationId) {
        return stationIdToStation.get(stationId);
    }

    private List<MediaBrowserCompat.MediaItem> createBrowsableMediaItemsForRoot(Resources resources) {
        List<MediaBrowserCompat.MediaItem> mediaItems = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2339)) {
            mediaItems.add(new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(MEDIA_ID_MUSICS_FAVORITE).setTitle(resources.getString(R.string.nav_item_starred)).setIconUri(resourceToUri(resources, R.drawable.ic_star_black_24dp)).build(), MediaBrowserCompat.MediaItem.FLAG_BROWSABLE));
        }
        if (!ListenerUtil.mutListener.listen(2340)) {
            mediaItems.add(new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(MEDIA_ID_MUSICS_HISTORY).setTitle(resources.getString(R.string.nav_item_history)).setIconUri(resourceToUri(resources, R.drawable.ic_restore_black_24dp)).build(), MediaBrowserCompat.MediaItem.FLAG_BROWSABLE));
        }
        if (!ListenerUtil.mutListener.listen(2341)) {
            mediaItems.add(new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(MEDIA_ID_MUSICS_TOP).setTitle(resources.getString(R.string.action_top_click)).setIconUri(resourceToUri(resources, R.drawable.ic_restore_black_24dp)).build(), MediaBrowserCompat.MediaItem.FLAG_BROWSABLE));
        }
        return mediaItems;
    }

    public static String stationIdFromMediaId(final String mediaId) {
        if (!ListenerUtil.mutListener.listen(2342)) {
            if (mediaId == null) {
                return "";
            }
        }
        final int separatorIdx = mediaId.indexOf(LEAF_SEPARATOR);
        if (!ListenerUtil.mutListener.listen(2348)) {
            if ((ListenerUtil.mutListener.listen(2347) ? (separatorIdx >= 0) : (ListenerUtil.mutListener.listen(2346) ? (separatorIdx > 0) : (ListenerUtil.mutListener.listen(2345) ? (separatorIdx < 0) : (ListenerUtil.mutListener.listen(2344) ? (separatorIdx != 0) : (ListenerUtil.mutListener.listen(2343) ? (separatorIdx == 0) : (separatorIdx <= 0))))))) {
                return mediaId;
            }
        }
        return mediaId.substring((ListenerUtil.mutListener.listen(2352) ? (separatorIdx % 1) : (ListenerUtil.mutListener.listen(2351) ? (separatorIdx / 1) : (ListenerUtil.mutListener.listen(2350) ? (separatorIdx * 1) : (ListenerUtil.mutListener.listen(2349) ? (separatorIdx - 1) : (separatorIdx + 1))))));
    }
}
