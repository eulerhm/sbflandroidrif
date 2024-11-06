package fr.free.nrw.commons.review;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import org.wikipedia.dataclient.mwapi.MwQueryPage;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.actions.PageEditClient;
import fr.free.nrw.commons.actions.ThanksClient;
import fr.free.nrw.commons.delete.DeleteHelper;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class ReviewController {

    private static final int NOTIFICATION_SEND_THANK = 0x102;

    private static final int NOTIFICATION_CHECK_CATEGORY = 0x101;

    protected static ArrayList<String> categories;

    @Inject
    ThanksClient thanksClient;

    private final DeleteHelper deleteHelper;

    @Nullable
    MwQueryPage.Revision // TODO: maybe we can expand this class to include fileName
    firstRevision;

    @Inject
    @Named("commons-page-edit")
    PageEditClient pageEditClient;

    private NotificationManager notificationManager;

    private NotificationCompat.Builder notificationBuilder;

    private Media media;

    ReviewController(DeleteHelper deleteHelper, Context context) {
        this.deleteHelper = deleteHelper;
        if (!ListenerUtil.mutListener.listen(5819)) {
            CommonsApplication.createNotificationChannel(context.getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(5820)) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(5821)) {
            notificationBuilder = new NotificationCompat.Builder(context, CommonsApplication.NOTIFICATION_CHANNEL_ID_ALL);
        }
    }

    void onImageRefreshed(Media media) {
        if (!ListenerUtil.mutListener.listen(5822)) {
            this.media = media;
        }
    }

    public Media getMedia() {
        return media;
    }

    public enum DeleteReason {

        SPAM, COPYRIGHT_VIOLATION
    }

    void reportSpam(@NonNull Activity activity, ReviewCallback reviewCallback) {
        if (!ListenerUtil.mutListener.listen(5823)) {
            Timber.d("Report spam for %s", media.getFilename());
        }
        if (!ListenerUtil.mutListener.listen(5824)) {
            deleteHelper.askReasonAndExecute(media, activity, activity.getResources().getString(R.string.review_spam_report_question), DeleteReason.SPAM, reviewCallback);
        }
    }

    void reportPossibleCopyRightViolation(@NonNull Activity activity, ReviewCallback reviewCallback) {
        if (!ListenerUtil.mutListener.listen(5825)) {
            Timber.d("Report spam for %s", media.getFilename());
        }
        if (!ListenerUtil.mutListener.listen(5826)) {
            deleteHelper.askReasonAndExecute(media, activity, activity.getResources().getString(R.string.review_c_violation_report_question), DeleteReason.COPYRIGHT_VIOLATION, reviewCallback);
        }
    }

    @SuppressLint("CheckResult")
    void reportWrongCategory(@NonNull Activity activity, ReviewCallback reviewCallback) {
        Context context = activity.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(5827)) {
            ApplicationlessInjection.getInstance(context).getCommonsApplicationComponent().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5828)) {
            ViewUtil.showShortToast(context, context.getString(R.string.check_category_toast, media.getDisplayTitle()));
        }
        if (!ListenerUtil.mutListener.listen(5829)) {
            publishProgress(context, 0);
        }
        String summary = context.getString(R.string.check_category_edit_summary);
        if (!ListenerUtil.mutListener.listen(5830)) {
            Observable.defer((Callable<ObservableSource<Boolean>>) () -> pageEditClient.appendEdit(media.getFilename(), "\n{{subst:chc}}\n", summary)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((result) -> {
                publishProgress(context, 2);
                String message;
                String title;
                if (result) {
                    title = context.getString(R.string.check_category_success_title);
                    message = context.getString(R.string.check_category_success_message, media.getDisplayTitle());
                    reviewCallback.onSuccess();
                } else {
                    title = context.getString(R.string.check_category_failure_title);
                    message = context.getString(R.string.check_category_failure_message, media.getDisplayTitle());
                    reviewCallback.onFailure();
                }
                showNotification(title, message);
            }, Timber::e);
        }
    }

    private void publishProgress(@NonNull Context context, int i) {
        int[] messages = new int[] { R.string.getting_edit_token, R.string.check_category_adding_template };
        String message = "";
        if (!ListenerUtil.mutListener.listen(5843)) {
            if ((ListenerUtil.mutListener.listen(5841) ? ((ListenerUtil.mutListener.listen(5835) ? (0 >= i) : (ListenerUtil.mutListener.listen(5834) ? (0 <= i) : (ListenerUtil.mutListener.listen(5833) ? (0 > i) : (ListenerUtil.mutListener.listen(5832) ? (0 != i) : (ListenerUtil.mutListener.listen(5831) ? (0 == i) : (0 < i)))))) || (ListenerUtil.mutListener.listen(5840) ? (i >= messages.length) : (ListenerUtil.mutListener.listen(5839) ? (i <= messages.length) : (ListenerUtil.mutListener.listen(5838) ? (i > messages.length) : (ListenerUtil.mutListener.listen(5837) ? (i != messages.length) : (ListenerUtil.mutListener.listen(5836) ? (i == messages.length) : (i < messages.length))))))) : ((ListenerUtil.mutListener.listen(5835) ? (0 >= i) : (ListenerUtil.mutListener.listen(5834) ? (0 <= i) : (ListenerUtil.mutListener.listen(5833) ? (0 > i) : (ListenerUtil.mutListener.listen(5832) ? (0 != i) : (ListenerUtil.mutListener.listen(5831) ? (0 == i) : (0 < i)))))) && (ListenerUtil.mutListener.listen(5840) ? (i >= messages.length) : (ListenerUtil.mutListener.listen(5839) ? (i <= messages.length) : (ListenerUtil.mutListener.listen(5838) ? (i > messages.length) : (ListenerUtil.mutListener.listen(5837) ? (i != messages.length) : (ListenerUtil.mutListener.listen(5836) ? (i == messages.length) : (i < messages.length))))))))) {
                if (!ListenerUtil.mutListener.listen(5842)) {
                    message = context.getString(messages[i]);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5844)) {
            notificationBuilder.setContentTitle(context.getString(R.string.check_category_notification_title, media.getDisplayTitle())).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setSmallIcon(R.drawable.ic_launcher).setProgress(messages.length, i, false).setOngoing(true);
        }
        if (!ListenerUtil.mutListener.listen(5845)) {
            notificationManager.notify(NOTIFICATION_CHECK_CATEGORY, notificationBuilder.build());
        }
    }

    @SuppressLint({ "CheckResult", "StringFormatInvalid" })
    void sendThanks(@NonNull Activity activity) {
        Context context = activity.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(5846)) {
            ApplicationlessInjection.getInstance(context).getCommonsApplicationComponent().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5847)) {
            ViewUtil.showShortToast(context, context.getString(R.string.send_thank_toast, media.getDisplayTitle()));
        }
        if (!ListenerUtil.mutListener.listen(5848)) {
            if (firstRevision == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5849)) {
            Observable.defer((Callable<ObservableSource<Boolean>>) () -> thanksClient.thank(firstRevision.getRevisionId())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((result) -> {
                displayThanksToast(context, result);
            }, Timber::e);
        }
    }

    @SuppressLint("StringFormatInvalid")
    private void displayThanksToast(final Context context, final boolean result) {
        final String message;
        final String title;
        if (result) {
            title = context.getString(R.string.send_thank_success_title);
            message = context.getString(R.string.send_thank_success_message, media.getDisplayTitle());
        } else {
            title = context.getString(R.string.send_thank_failure_title);
            message = context.getString(R.string.send_thank_failure_message, media.getDisplayTitle());
        }
        if (!ListenerUtil.mutListener.listen(5850)) {
            ViewUtil.showShortToast(context, message);
        }
    }

    private void showNotification(String title, String message) {
        if (!ListenerUtil.mutListener.listen(5851)) {
            notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL).setContentTitle(title).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setSmallIcon(R.drawable.ic_launcher).setProgress(0, 0, false).setOngoing(false).setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        if (!ListenerUtil.mutListener.listen(5852)) {
            notificationManager.notify(NOTIFICATION_SEND_THANK, notificationBuilder.build());
        }
    }

    public interface ReviewCallback {

        void onSuccess();

        void onFailure();
    }
}
