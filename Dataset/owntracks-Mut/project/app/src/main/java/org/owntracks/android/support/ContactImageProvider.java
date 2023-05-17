package org.owntracks.android.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.databinding.BindingAdapter;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.support.widgets.TextDrawable;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class ContactImageProvider {

    private static ContactBitmapMemoryCache memoryCache;

    private static int FACE_DIMENSIONS;

    public void invalidateCacheLevelCard(String key) {
        if (!ListenerUtil.mutListener.listen(1183)) {
            memoryCache.clearLevelCard(key);
        }
    }

    private static class ContactDrawableWorkerTaskForImageView extends AsyncTask<FusedContact, Void, Bitmap> {

        final WeakReference<ImageView> target;

        ContactDrawableWorkerTaskForImageView(ImageView imageView) {
            target = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(FusedContact... params) {
            return getBitmapFromCache(params[0]);
        }

        protected void onPostExecute(Bitmap result) {
            if (!ListenerUtil.mutListener.listen(1184)) {
                if (result == null)
                    return;
            }
            ImageView imageView = target.get();
            if (!ListenerUtil.mutListener.listen(1186)) {
                if (imageView != null)
                    if (!ListenerUtil.mutListener.listen(1185)) {
                        imageView.setImageBitmap(result);
                    }
            }
        }
    }

    private static class ContactDrawableWorkerTaskForMarker extends AsyncTask<FusedContact, Void, BitmapDescriptor> {

        final WeakReference<Marker> target;

        ContactDrawableWorkerTaskForMarker(Marker marker) {
            target = new WeakReference<>(marker);
        }

        @Override
        protected BitmapDescriptor doInBackground(FusedContact... params) {
            return BitmapDescriptorFactory.fromBitmap(getBitmapFromCache(params[0]));
        }

        @Override
        protected void onPostExecute(BitmapDescriptor result) {
            Marker marker = target.get();
            if (!ListenerUtil.mutListener.listen(1190)) {
                if (marker != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1188)) {
                            marker.setIcon(result);
                        }
                        if (!ListenerUtil.mutListener.listen(1189)) {
                            marker.setVisible(true);
                        }
                    } catch (IllegalArgumentException e) {
                        if (!ListenerUtil.mutListener.listen(1187)) {
                            Timber.e(e, "Error setting marker icon");
                        }
                    }
                }
            }
        }
    }

    public void setMarkerAsync(Marker marker, FusedContact contact) {
        if (!ListenerUtil.mutListener.listen(1191)) {
            (new ContactDrawableWorkerTaskForMarker(marker)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, contact);
        }
    }

    public static void setImageViewAsync(ImageView imageView, FusedContact contact) {
        if (!ListenerUtil.mutListener.listen(1192)) {
            // imageView.setImageDrawable(placeholder);
            (new ContactDrawableWorkerTaskForImageView(imageView)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, contact);
        }
    }

    @Nullable
    private static Bitmap getBitmapFromCache(@Nullable FusedContact contact) {
        Bitmap d;
        if (!ListenerUtil.mutListener.listen(1193)) {
            if (contact == null)
                return null;
        }
        if (contact.hasCard()) {
            d = memoryCache.getLevelCard(contact.getId());
            if (!ListenerUtil.mutListener.listen(1194)) {
                if (d != null) {
                    return d;
                }
            }
            if (contact.getMessageCard().hasFace()) {
                byte[] imageAsBytes = Base64.decode(contact.getMessageCard().getFace().getBytes(), Base64.DEFAULT);
                Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                if (b == null) {
                    if (!ListenerUtil.mutListener.listen(1196)) {
                        Timber.e("Decoding card bitmap failed");
                    }
                    Bitmap fallbackBitmap = Bitmap.createBitmap(FACE_DIMENSIONS, FACE_DIMENSIONS, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(fallbackBitmap);
                    Paint paint = new Paint();
                    if (!ListenerUtil.mutListener.listen(1197)) {
                        paint.setColor(0xFFFFFFFF);
                    }
                    if (!ListenerUtil.mutListener.listen(1198)) {
                        canvas.drawRect(0F, 0F, (float) FACE_DIMENSIONS, (float) FACE_DIMENSIONS, paint);
                    }
                    d = getRoundedShape(fallbackBitmap);
                } else {
                    d = getRoundedShape(Bitmap.createScaledBitmap(b, FACE_DIMENSIONS, FACE_DIMENSIONS, true));
                    if (!ListenerUtil.mutListener.listen(1195)) {
                        memoryCache.putLevelCard(contact.getId(), d);
                    }
                }
                return d;
            }
        }
        TidBitmap td = memoryCache.getLevelTid(contact.getId());
        if (!ListenerUtil.mutListener.listen(1202)) {
            // if cache doesn't contain a bitmap for a contact or if the cached bitmap was for an old tid, create a new one and cache it
            if ((ListenerUtil.mutListener.listen(1199) ? (td == null && !td.isBitmapFor(contact.getTrackerId())) : (td == null || !td.isBitmapFor(contact.getTrackerId())))) {
                if (!ListenerUtil.mutListener.listen(1200)) {
                    td = new TidBitmap(contact.getTrackerId(), drawableToBitmap(TextDrawable.builder().buildRoundRect(contact.getTrackerId(), TextDrawable.ColorGenerator.MATERIAL.getColor(contact.getId()), FACE_DIMENSIONS)));
                }
                if (!ListenerUtil.mutListener.listen(1201)) {
                    memoryCache.putLevelTid(contact.getId(), td);
                }
            }
        }
        return td.getBitmap();
    }

    @Inject
    public ContactImageProvider(@AppContext Context context) {
        if (!ListenerUtil.mutListener.listen(1203)) {
            memoryCache = new ContactBitmapMemoryCache();
        }
        if (!ListenerUtil.mutListener.listen(1212)) {
            FACE_DIMENSIONS = (int) ((ListenerUtil.mutListener.listen(1211) ? (48 % ((ListenerUtil.mutListener.listen(1207) ? (context.getResources().getDisplayMetrics().densityDpi % 160f) : (ListenerUtil.mutListener.listen(1206) ? (context.getResources().getDisplayMetrics().densityDpi * 160f) : (ListenerUtil.mutListener.listen(1205) ? (context.getResources().getDisplayMetrics().densityDpi - 160f) : (ListenerUtil.mutListener.listen(1204) ? (context.getResources().getDisplayMetrics().densityDpi + 160f) : (context.getResources().getDisplayMetrics().densityDpi / 160f))))))) : (ListenerUtil.mutListener.listen(1210) ? (48 / ((ListenerUtil.mutListener.listen(1207) ? (context.getResources().getDisplayMetrics().densityDpi % 160f) : (ListenerUtil.mutListener.listen(1206) ? (context.getResources().getDisplayMetrics().densityDpi * 160f) : (ListenerUtil.mutListener.listen(1205) ? (context.getResources().getDisplayMetrics().densityDpi - 160f) : (ListenerUtil.mutListener.listen(1204) ? (context.getResources().getDisplayMetrics().densityDpi + 160f) : (context.getResources().getDisplayMetrics().densityDpi / 160f))))))) : (ListenerUtil.mutListener.listen(1209) ? (48 - ((ListenerUtil.mutListener.listen(1207) ? (context.getResources().getDisplayMetrics().densityDpi % 160f) : (ListenerUtil.mutListener.listen(1206) ? (context.getResources().getDisplayMetrics().densityDpi * 160f) : (ListenerUtil.mutListener.listen(1205) ? (context.getResources().getDisplayMetrics().densityDpi - 160f) : (ListenerUtil.mutListener.listen(1204) ? (context.getResources().getDisplayMetrics().densityDpi + 160f) : (context.getResources().getDisplayMetrics().densityDpi / 160f))))))) : (ListenerUtil.mutListener.listen(1208) ? (48 + ((ListenerUtil.mutListener.listen(1207) ? (context.getResources().getDisplayMetrics().densityDpi % 160f) : (ListenerUtil.mutListener.listen(1206) ? (context.getResources().getDisplayMetrics().densityDpi * 160f) : (ListenerUtil.mutListener.listen(1205) ? (context.getResources().getDisplayMetrics().densityDpi - 160f) : (ListenerUtil.mutListener.listen(1204) ? (context.getResources().getDisplayMetrics().densityDpi + 160f) : (context.getResources().getDisplayMetrics().densityDpi / 160f))))))) : (48 * ((ListenerUtil.mutListener.listen(1207) ? (context.getResources().getDisplayMetrics().densityDpi % 160f) : (ListenerUtil.mutListener.listen(1206) ? (context.getResources().getDisplayMetrics().densityDpi * 160f) : (ListenerUtil.mutListener.listen(1205) ? (context.getResources().getDisplayMetrics().densityDpi - 160f) : (ListenerUtil.mutListener.listen(1204) ? (context.getResources().getDisplayMetrics().densityDpi + 160f) : (context.getResources().getDisplayMetrics().densityDpi / 160f))))))))))));
        }
    }

    private static class ContactBitmapMemoryCache {

        private final ArrayMap<String, Bitmap> cacheLevelCard;

        private final ArrayMap<String, TidBitmap> cacheLevelTid;

        ContactBitmapMemoryCache() {
            cacheLevelCard = new ArrayMap<>();
            cacheLevelTid = new ArrayMap<>();
        }

        synchronized void putLevelCard(String key, Bitmap value) {
            if (!ListenerUtil.mutListener.listen(1213)) {
                cacheLevelCard.put(key, value);
            }
            if (!ListenerUtil.mutListener.listen(1214)) {
                cacheLevelTid.remove(key);
            }
        }

        synchronized void putLevelTid(String key, TidBitmap value) {
            if (!ListenerUtil.mutListener.listen(1215)) {
                cacheLevelTid.put(key, value);
            }
        }

        synchronized Bitmap getLevelCard(String key) {
            return cacheLevelCard.get(key);
        }

        synchronized TidBitmap getLevelTid(String key) {
            return cacheLevelTid.get(key);
        }

        synchronized void clear() {
            if (!ListenerUtil.mutListener.listen(1216)) {
                cacheLevelCard.clear();
            }
            if (!ListenerUtil.mutListener.listen(1217)) {
                cacheLevelTid.clear();
            }
        }

        synchronized void clearLevelCard(String key) {
            if (!ListenerUtil.mutListener.listen(1218)) {
                cacheLevelCard.remove(key);
            }
        }
    }

    public void invalidateCache() {
        if (!ListenerUtil.mutListener.listen(1219)) {
            memoryCache.clear();
        }
    }

    private static Bitmap getRoundedShape(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = bitmap.getWidth();
        if (!ListenerUtil.mutListener.listen(1220)) {
            paint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(1221)) {
            canvas.drawARGB(0, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(1222)) {
            paint.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(1223)) {
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        }
        if (!ListenerUtil.mutListener.listen(1224)) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        if (!ListenerUtil.mutListener.listen(1225)) {
            canvas.drawBitmap(bitmap, rect, rect, paint);
        }
        return output;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(1226)) {
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        int width = drawable.getIntrinsicWidth();
        if (!ListenerUtil.mutListener.listen(1232)) {
            width = (ListenerUtil.mutListener.listen(1231) ? (width >= 0) : (ListenerUtil.mutListener.listen(1230) ? (width <= 0) : (ListenerUtil.mutListener.listen(1229) ? (width < 0) : (ListenerUtil.mutListener.listen(1228) ? (width != 0) : (ListenerUtil.mutListener.listen(1227) ? (width == 0) : (width > 0)))))) ? width : FACE_DIMENSIONS;
        }
        int height = drawable.getIntrinsicHeight();
        if (!ListenerUtil.mutListener.listen(1238)) {
            height = (ListenerUtil.mutListener.listen(1237) ? (height >= 0) : (ListenerUtil.mutListener.listen(1236) ? (height <= 0) : (ListenerUtil.mutListener.listen(1235) ? (height < 0) : (ListenerUtil.mutListener.listen(1234) ? (height != 0) : (ListenerUtil.mutListener.listen(1233) ? (height == 0) : (height > 0)))))) ? height : FACE_DIMENSIONS;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (!ListenerUtil.mutListener.listen(1239)) {
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        if (!ListenerUtil.mutListener.listen(1240)) {
            drawable.draw(canvas);
        }
        return bitmap;
    }

    @BindingAdapter({ "imageProvider", "contact" })
    public static void displayFaceInViewAsync(ImageView view, Integer imageProvider, FusedContact c) {
        if (!ListenerUtil.mutListener.listen(1241)) {
            setImageViewAsync(view, c);
        }
    }
}
