package fr.free.nrw.commons.delete;

import static fr.free.nrw.commons.notification.NotificationHelper.NOTIFICATION_DELETE;
import android.annotation.SuppressLint;
import android.content.Context;
import static fr.free.nrw.commons.utils.LangCodeUtils.getLocalizedResources;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.actions.PageEditClient;
import fr.free.nrw.commons.notification.NotificationHelper;
import fr.free.nrw.commons.review.ReviewController;
import fr.free.nrw.commons.utils.ViewUtilWrapper;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Refactored async task to Rx
 */
@Singleton
public class DeleteHelper {

    private final NotificationHelper notificationHelper;

    private final PageEditClient pageEditClient;

    private final ViewUtilWrapper viewUtil;

    private final String username;

    private AlertDialog d;

    private DialogInterface.OnMultiChoiceClickListener listener;

    @Inject
    public DeleteHelper(NotificationHelper notificationHelper, @Named("commons-page-edit") PageEditClient pageEditClient, ViewUtilWrapper viewUtil, @Named("username") String username) {
        this.notificationHelper = notificationHelper;
        this.pageEditClient = pageEditClient;
        this.viewUtil = viewUtil;
        this.username = username;
    }

    /**
     * Public interface to nominate a particular media file for deletion
     * @param context
     * @param media
     * @param reason
     * @return
     */
    public Single<Boolean> makeDeletion(Context context, Media media, String reason) {
        if (!ListenerUtil.mutListener.listen(501)) {
            viewUtil.showShortToast(context, "Trying to nominate " + media.getDisplayTitle() + " for deletion");
        }
        return delete(media, reason).flatMapSingle(result -> Single.just(showDeletionNotification(context, media, result))).firstOrError();
    }

    /**
     * Makes several API calls to nominate the file for deletion
     * @param media
     * @param reason
     * @return
     */
    private Observable<Boolean> delete(Media media, String reason) {
        if (!ListenerUtil.mutListener.listen(502)) {
            Timber.d("thread is delete %s", Thread.currentThread().getName());
        }
        String summary = "Nominating " + media.getFilename() + " for deletion.";
        Calendar calendar = Calendar.getInstance();
        String fileDeleteString = "{{delete|reason=" + reason + "|subpage=" + media.getFilename() + "|day=" + calendar.get(Calendar.DAY_OF_MONTH) + "|month=" + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + "|year=" + calendar.get(Calendar.YEAR) + "}}";
        String subpageString = "=== [[:" + media.getFilename() + "]] ===\n" + reason + " ~~~~";
        String logPageString = "\n{{Commons:Deletion requests/" + media.getFilename() + "}}\n";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String date = sdf.format(calendar.getTime());
        String userPageString = "\n{{subst:idw|" + media.getFilename() + "}} ~~~~";
        String creator = media.getAuthor();
        if (!ListenerUtil.mutListener.listen(504)) {
            if ((ListenerUtil.mutListener.listen(503) ? (creator == null && creator.isEmpty()) : (creator == null || creator.isEmpty()))) {
                throw new RuntimeException("Failed to nominate for deletion");
            }
        }
        return pageEditClient.prependEdit(media.getFilename(), fileDeleteString + "\n", summary).flatMap(result -> {
            if (result) {
                return pageEditClient.edit("Commons:Deletion_requests/" + media.getFilename(), subpageString + "\n", summary);
            }
            throw new RuntimeException("Failed to nominate for deletion");
        }).flatMap(result -> {
            if (result) {
                return pageEditClient.appendEdit("Commons:Deletion_requests/" + date, logPageString + "\n", summary);
            }
            throw new RuntimeException("Failed to nominate for deletion");
        }).flatMap(result -> {
            if (result) {
                return pageEditClient.appendEdit("User_Talk:" + creator, userPageString + "\n", summary);
            }
            throw new RuntimeException("Failed to nominate for deletion");
        });
    }

    private boolean showDeletionNotification(Context context, Media media, boolean result) {
        String message;
        String title = context.getString(R.string.delete_helper_show_deletion_title);
        if (result) {
            if (!ListenerUtil.mutListener.listen(506)) {
                title += ": " + context.getString(R.string.delete_helper_show_deletion_title_success);
            }
            message = context.getString((R.string.delete_helper_show_deletion_message_if), media.getDisplayTitle());
        } else {
            if (!ListenerUtil.mutListener.listen(505)) {
                title += ": " + context.getString(R.string.delete_helper_show_deletion_title_failed);
            }
            message = context.getString(R.string.delete_helper_show_deletion_message_else);
        }
        String urlForDelete = BuildConfig.COMMONS_URL + "/wiki/Commons:Deletion_requests/" + media.getFilename();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForDelete));
        if (!ListenerUtil.mutListener.listen(507)) {
            notificationHelper.showNotification(context, title, message, NOTIFICATION_DELETE, browserIntent);
        }
        return result;
    }

    /**
     * Invoked when a reason needs to be asked before nominating for deletion
     * @param media
     * @param context
     * @param question
     * @param problem
     */
    @SuppressLint("CheckResult")
    public void askReasonAndExecute(Media media, Context context, String question, ReviewController.DeleteReason problem, ReviewController.ReviewCallback reviewCallback) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        if (!ListenerUtil.mutListener.listen(508)) {
            alert.setTitle(question);
        }
        boolean[] checkedItems = { false, false, false, false };
        ArrayList<Integer> mUserReason = new ArrayList<>();
        final String[] reasonList;
        final String[] reasonListEnglish;
        if (problem == ReviewController.DeleteReason.SPAM) {
            reasonList = new String[] { context.getString(R.string.delete_helper_ask_spam_selfie), context.getString(R.string.delete_helper_ask_spam_blurry), context.getString(R.string.delete_helper_ask_spam_nonsense) };
            reasonListEnglish = new String[] { getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_spam_selfie), getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_spam_blurry), getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_spam_nonsense) };
        } else if (problem == ReviewController.DeleteReason.COPYRIGHT_VIOLATION) {
            reasonList = new String[] { context.getString(R.string.delete_helper_ask_reason_copyright_press_photo), context.getString(R.string.delete_helper_ask_reason_copyright_internet_photo), context.getString(R.string.delete_helper_ask_reason_copyright_logo), context.getString(R.string.delete_helper_ask_reason_copyright_no_freedom_of_panorama) };
            reasonListEnglish = new String[] { getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_reason_copyright_press_photo), getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_reason_copyright_internet_photo), getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_reason_copyright_logo), getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_reason_copyright_no_freedom_of_panorama) };
        } else {
            reasonList = new String[] {};
            reasonListEnglish = new String[] {};
        }
        if (!ListenerUtil.mutListener.listen(509)) {
            alert.setMultiChoiceItems(reasonList, checkedItems, listener = (dialogInterface, position, isChecked) -> {
                if (isChecked) {
                    mUserReason.add(position);
                } else {
                    mUserReason.remove((Integer.valueOf(position)));
                }
                // disable the OK button if no reason selected
                ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!mUserReason.isEmpty());
            });
        }
        if (!ListenerUtil.mutListener.listen(510)) {
            alert.setPositiveButton(context.getString(R.string.ok), (dialogInterface, i) -> {
                String reason = getLocalizedResources(context, Locale.ENGLISH).getString(R.string.delete_helper_ask_alert_set_positive_button_reason) + " ";
                {
                    long _loopCounter11 = 0;
                    for (int j = 0; j < mUserReason.size(); j++) {
                        ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                        reason = reason + reasonListEnglish[mUserReason.get(j)];
                        if (j != mUserReason.size() - 1) {
                            reason = reason + ", ";
                        }
                    }
                }
                Timber.d("thread is askReasonAndExecute %s", Thread.currentThread().getName());
                String finalReason = reason;
                Single.defer((Callable<SingleSource<Boolean>>) () -> makeDeletion(context, media, finalReason)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> {
                    if (aBoolean) {
                        reviewCallback.onSuccess();
                    } else {
                        reviewCallback.onFailure();
                    }
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(511)) {
            alert.setNegativeButton(context.getString(R.string.cancel), (dialog, which) -> reviewCallback.onFailure());
        }
        if (!ListenerUtil.mutListener.listen(512)) {
            d = alert.create();
        }
        if (!ListenerUtil.mutListener.listen(513)) {
            d.show();
        }
        if (!ListenerUtil.mutListener.listen(514)) {
            // disable the OK button by default
            d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
    }

    /**
     * returns the instance of shown AlertDialog,
     * used for taking reference during unit test
     */
    public AlertDialog getDialog() {
        return d;
    }

    /**
     * returns the instance of shown DialogInterface.OnMultiChoiceClickListener,
     * used for taking reference during unit test
     */
    public DialogInterface.OnMultiChoiceClickListener getListener() {
        return listener;
    }
}
