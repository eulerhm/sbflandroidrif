package fr.free.nrw.commons.delete;

import android.content.Context;
import org.wikipedia.util.DateUtil;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.profile.achievements.FeedbackResponse;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.utils.ViewUtilWrapper;
import io.reactivex.Single;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class handles the reason for deleting a Media object
 */
@Singleton
public class ReasonBuilder {

    private SessionManager sessionManager;

    private OkHttpJsonApiClient okHttpJsonApiClient;

    private Context context;

    private ViewUtilWrapper viewUtilWrapper;

    @Inject
    public ReasonBuilder(Context context, SessionManager sessionManager, OkHttpJsonApiClient okHttpJsonApiClient, ViewUtilWrapper viewUtilWrapper) {
        if (!ListenerUtil.mutListener.listen(515)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(516)) {
            this.sessionManager = sessionManager;
        }
        if (!ListenerUtil.mutListener.listen(517)) {
            this.okHttpJsonApiClient = okHttpJsonApiClient;
        }
        if (!ListenerUtil.mutListener.listen(518)) {
            this.viewUtilWrapper = viewUtilWrapper;
        }
    }

    /**
     * To process the reason and append the media's upload date and uploaded_by_me string
     * @param media
     * @param reason
     * @return
     */
    public Single<String> getReason(Media media, String reason) {
        return fetchArticleNumber(media, reason);
    }

    /**
     * get upload date for the passed Media
     */
    private String prettyUploadedDate(Media media) {
        Date date = media.getDateUploaded();
        if (!ListenerUtil.mutListener.listen(521)) {
            if ((ListenerUtil.mutListener.listen(520) ? ((ListenerUtil.mutListener.listen(519) ? (date == null && date.toString() == null) : (date == null || date.toString() == null)) && date.toString().isEmpty()) : ((ListenerUtil.mutListener.listen(519) ? (date == null && date.toString() == null) : (date == null || date.toString() == null)) || date.toString().isEmpty()))) {
                return "Uploaded date not available";
            }
        }
        return DateUtil.getDateStringWithSkeletonPattern(date, "dd MMM yyyy");
    }

    private Single<String> fetchArticleNumber(Media media, String reason) {
        if (!ListenerUtil.mutListener.listen(522)) {
            if (checkAccount()) {
                return okHttpJsonApiClient.getAchievements(sessionManager.getUserName()).map(feedbackResponse -> appendArticlesUsed(feedbackResponse, media, reason));
            }
        }
        return Single.just("");
    }

    /**
     * Takes the uploaded_by_me string, the upload date, name of articles using images
     * and appends it to the received reason
     * @param feedBack object
     * @param media whose upload data is to be fetched
     * @param reason
     */
    private String appendArticlesUsed(FeedbackResponse feedBack, Media media, String reason) {
        String reason1Template = context.getString(R.string.uploaded_by_myself);
        if (!ListenerUtil.mutListener.listen(523)) {
            reason += String.format(Locale.getDefault(), reason1Template, prettyUploadedDate(media), feedBack.getArticlesUsingImages());
        }
        if (!ListenerUtil.mutListener.listen(524)) {
            Timber.i("New Reason %s", reason);
        }
        return reason;
    }

    /**
     * check to ensure that user is logged in
     * @return
     */
    private boolean checkAccount() {
        if (!ListenerUtil.mutListener.listen(528)) {
            if (!sessionManager.doesAccountExist()) {
                if (!ListenerUtil.mutListener.listen(525)) {
                    Timber.d("Current account is null");
                }
                if (!ListenerUtil.mutListener.listen(526)) {
                    viewUtilWrapper.showLongToast(context, context.getResources().getString(R.string.user_not_logged_in));
                }
                if (!ListenerUtil.mutListener.listen(527)) {
                    sessionManager.forceLogin(context);
                }
                return false;
            }
        }
        return true;
    }
}
