package fr.free.nrw.commons.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import fr.free.nrw.commons.media.MediaClient;
import javax.inject.Inject;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import static android.content.Intent.ACTION_VIEW;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Implementation of App Widget functionality.
 */
public class PicOfDayAppWidget extends AppWidgetProvider {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    MediaClient mediaClient;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.pic_of_day_app_widget);
        // Launch App on Button Click
        Intent viewIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, viewIntent, PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(274)) {
            views.setOnClickPendingIntent(R.id.camera_button, pendingIntent);
        }
        if (!ListenerUtil.mutListener.listen(275)) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        if (!ListenerUtil.mutListener.listen(276)) {
            loadPictureOfTheDay(context, views, appWidgetManager, appWidgetId);
        }
    }

    /**
     * Loads the picture of the day using media wiki API
     * @param context
     * @param views
     * @param appWidgetManager
     * @param appWidgetId
     */
    private void loadPictureOfTheDay(Context context, RemoteViews views, AppWidgetManager appWidgetManager, int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(277)) {
            compositeDisposable.add(mediaClient.getPictureOfTheDay().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                if (response != null) {
                    views.setTextViewText(R.id.appwidget_title, response.getDisplayTitle());
                    // View in browser
                    Intent viewIntent = new Intent();
                    viewIntent.setAction(ACTION_VIEW);
                    viewIntent.setData(Uri.parse(response.getPageTitle().getMobileUri()));
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, viewIntent, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.appwidget_image, pendingIntent);
                    loadImageFromUrl(response.getThumbUrl(), context, views, appWidgetManager, appWidgetId);
                }
            }, t -> Timber.e(t, "Fetching picture of the day failed")));
        }
    }

    /**
     * Uses Fresco to load an image from Url
     * @param imageUrl
     * @param context
     * @param views
     * @param appWidgetManager
     * @param appWidgetId
     */
    private void loadImageFromUrl(String imageUrl, Context context, RemoteViews views, AppWidgetManager appWidgetManager, int appWidgetId) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl)).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(request, context);
        if (!ListenerUtil.mutListener.listen(283)) {
            dataSource.subscribe(new BaseBitmapDataSubscriber() {

                @Override
                protected void onNewResultImpl(@Nullable Bitmap tempBitmap) {
                    Bitmap bitmap = null;
                    if (!ListenerUtil.mutListener.listen(280)) {
                        if (tempBitmap != null) {
                            if (!ListenerUtil.mutListener.listen(278)) {
                                bitmap = Bitmap.createBitmap(tempBitmap.getWidth(), tempBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                            }
                            Canvas canvas = new Canvas(bitmap);
                            if (!ListenerUtil.mutListener.listen(279)) {
                                canvas.drawBitmap(tempBitmap, 0f, 0f, new Paint());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(281)) {
                        views.setImageViewBitmap(R.id.appwidget_image, bitmap);
                    }
                    if (!ListenerUtil.mutListener.listen(282)) {
                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    }
                }

                @Override
                protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                }
            }, CallerThreadExecutor.getInstance());
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (!ListenerUtil.mutListener.listen(284)) {
            ApplicationlessInjection.getInstance(context.getApplicationContext()).getCommonsApplicationComponent().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(286)) {
            {
                long _loopCounter6 = 0;
                // There may be multiple widgets active, so update all of them
                for (int appWidgetId : appWidgetIds) {
                    ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                    if (!ListenerUtil.mutListener.listen(285)) {
                        updateAppWidget(context, appWidgetManager, appWidgetId);
                    }
                }
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}
