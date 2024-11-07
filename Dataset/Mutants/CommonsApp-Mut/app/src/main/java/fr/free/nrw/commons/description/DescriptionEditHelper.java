package fr.free.nrw.commons.description;

import static fr.free.nrw.commons.notification.NotificationHelper.NOTIFICATION_EDIT_DESCRIPTION;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.actions.PageEditClient;
import fr.free.nrw.commons.notification.NotificationHelper;
import io.reactivex.Single;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class for edit and update given descriptions and showing notification upgradation
 */
public class DescriptionEditHelper {

    /**
     * notificationHelper: helps creating notification
     */
    private final NotificationHelper notificationHelper;

    /**
     * * pageEditClient: methods provided by this member posts the edited descriptions
     * to the Media wiki api
     */
    public final PageEditClient pageEditClient;

    @Inject
    public DescriptionEditHelper(final NotificationHelper notificationHelper, @Named("commons-page-edit") final PageEditClient pageEditClient) {
        this.notificationHelper = notificationHelper;
        this.pageEditClient = pageEditClient;
    }

    /**
     * Replaces new descriptions
     *
     * @param context context
     * @param media to be added
     * @param appendText to be added
     * @return Observable<Boolean>
     */
    public Single<Boolean> addDescription(final Context context, final Media media, final String appendText) {
        if (!ListenerUtil.mutListener.listen(5184)) {
            Timber.d("thread is description adding %s", Thread.currentThread().getName());
        }
        final String summary = "Updating Description";
        return pageEditClient.edit(Objects.requireNonNull(media.getFilename()), appendText, summary).flatMapSingle(result -> Single.just(showDescriptionEditNotification(context, media, result))).firstOrError();
    }

    /**
     * Adds new captions
     *
     * @param context context
     * @param media to be added
     * @param language to be added
     * @param value to be added
     * @return Observable<Boolean>
     */
    public Single<Boolean> addCaption(final Context context, final Media media, final String language, final String value) {
        if (!ListenerUtil.mutListener.listen(5185)) {
            Timber.d("thread is caption adding %s", Thread.currentThread().getName());
        }
        final String summary = "Updating Caption";
        return pageEditClient.setCaptions(summary, Objects.requireNonNull(media.getFilename()), language, value).flatMapSingle(result -> Single.just(showCaptionEditNotification(context, media, result))).firstOrError();
    }

    /**
     * Update captions and shows notification about captions update
     * @param context to be added
     * @param media to be added
     * @param result to be added
     * @return boolean
     */
    private boolean showCaptionEditNotification(final Context context, final Media media, final int result) {
        final String message;
        String title = context.getString(R.string.caption_edit_helper_show_edit_title);
        if ((ListenerUtil.mutListener.listen(5190) ? (result >= 1) : (ListenerUtil.mutListener.listen(5189) ? (result <= 1) : (ListenerUtil.mutListener.listen(5188) ? (result > 1) : (ListenerUtil.mutListener.listen(5187) ? (result < 1) : (ListenerUtil.mutListener.listen(5186) ? (result != 1) : (result == 1))))))) {
            if (!ListenerUtil.mutListener.listen(5192)) {
                title += ": " + context.getString(R.string.coordinates_edit_helper_show_edit_title_success);
            }
            message = context.getString(R.string.caption_edit_helper_show_edit_message);
        } else {
            if (!ListenerUtil.mutListener.listen(5191)) {
                title += ": " + context.getString(R.string.caption_edit_helper_show_edit_title);
            }
            message = context.getString(R.string.caption_edit_helper_edit_message_else);
        }
        final String urlForFile = BuildConfig.COMMONS_URL + "/wiki/" + media.getFilename();
        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForFile));
        if (!ListenerUtil.mutListener.listen(5193)) {
            notificationHelper.showNotification(context, title, message, NOTIFICATION_EDIT_DESCRIPTION, browserIntent);
        }
        return (ListenerUtil.mutListener.listen(5198) ? (result >= 1) : (ListenerUtil.mutListener.listen(5197) ? (result <= 1) : (ListenerUtil.mutListener.listen(5196) ? (result > 1) : (ListenerUtil.mutListener.listen(5195) ? (result < 1) : (ListenerUtil.mutListener.listen(5194) ? (result != 1) : (result == 1))))));
    }

    /**
     * Update descriptions and shows notification about descriptions update
     * @param context to be added
     * @param media to be added
     * @param result to be added
     * @return boolean
     */
    private boolean showDescriptionEditNotification(final Context context, final Media media, final boolean result) {
        final String message;
        String title = context.getString(R.string.description_edit_helper_show_edit_title);
        if (result) {
            if (!ListenerUtil.mutListener.listen(5200)) {
                title += ": " + context.getString(R.string.coordinates_edit_helper_show_edit_title_success);
            }
            message = context.getString(R.string.description_edit_helper_show_edit_message);
        } else {
            if (!ListenerUtil.mutListener.listen(5199)) {
                title += ": " + context.getString(R.string.description_edit_helper_show_edit_title);
            }
            message = context.getString(R.string.description_edit_helper_edit_message_else);
        }
        final String urlForFile = BuildConfig.COMMONS_URL + "/wiki/" + media.getFilename();
        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForFile));
        if (!ListenerUtil.mutListener.listen(5201)) {
            notificationHelper.showNotification(context, title, message, NOTIFICATION_EDIT_DESCRIPTION, browserIntent);
        }
        return result;
    }
}
