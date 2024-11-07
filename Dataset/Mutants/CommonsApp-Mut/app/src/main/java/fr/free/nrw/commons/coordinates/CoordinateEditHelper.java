package fr.free.nrw.commons.coordinates;

import static fr.free.nrw.commons.notification.NotificationHelper.NOTIFICATION_EDIT_COORDINATES;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.actions.PageEditClient;
import fr.free.nrw.commons.notification.NotificationHelper;
import fr.free.nrw.commons.utils.ViewUtilWrapper;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class for edit and update given coordinates and showing notification about new coordinates
 * upgradation
 */
public class CoordinateEditHelper {

    /**
     * notificationHelper: helps creating notification
     */
    private final NotificationHelper notificationHelper;

    /**
     * * pageEditClient: methods provided by this member posts the edited coordinates
     * to the Media wiki api
     */
    public final PageEditClient pageEditClient;

    /**
     * viewUtil: helps to show Toast
     */
    private final ViewUtilWrapper viewUtil;

    @Inject
    public CoordinateEditHelper(final NotificationHelper notificationHelper, @Named("commons-page-edit") final PageEditClient pageEditClient, final ViewUtilWrapper viewUtil) {
        this.notificationHelper = notificationHelper;
        this.pageEditClient = pageEditClient;
        this.viewUtil = viewUtil;
    }

    /**
     * Public interface to edit coordinates
     * @param context to be added
     * @param media to be added
     * @param Accuracy to be added
     * @return Single<Boolean>
     */
    public Single<Boolean> makeCoordinatesEdit(final Context context, final Media media, final String Latitude, final String Longitude, final String Accuracy) {
        if (!ListenerUtil.mutListener.listen(529)) {
            viewUtil.showShortToast(context, context.getString(R.string.coordinates_edit_helper_make_edit_toast));
        }
        return addCoordinates(media, Latitude, Longitude, Accuracy).flatMapSingle(result -> Single.just(showCoordinatesEditNotification(context, media, Latitude, Longitude, Accuracy, result))).firstOrError();
    }

    /**
     * Replaces new coordinates
     * @param media to be added
     * @param Latitude to be added
     * @param Longitude to be added
     * @param Accuracy to be added
     * @return Observable<Boolean>
     */
    private Observable<Boolean> addCoordinates(final Media media, final String Latitude, final String Longitude, final String Accuracy) {
        if (!ListenerUtil.mutListener.listen(530)) {
            Timber.d("thread is coordinates adding %s", Thread.currentThread().getName());
        }
        final String summary = "Adding Coordinates";
        final StringBuilder buffer = new StringBuilder();
        final String wikiText = pageEditClient.getCurrentWikiText(media.getFilename()).subscribeOn(Schedulers.io()).blockingGet();
        if (!ListenerUtil.mutListener.listen(532)) {
            if (Latitude != null) {
                if (!ListenerUtil.mutListener.listen(531)) {
                    buffer.append("\n{{Location|").append(Latitude).append("|").append(Longitude).append("|").append(Accuracy).append("}}");
                }
            }
        }
        final String editedLocation = buffer.toString();
        final String appendText = getFormattedWikiText(wikiText, editedLocation);
        return pageEditClient.edit(Objects.requireNonNull(media.getFilename()), appendText, summary);
    }

    /**
     * Helps to get formatted wikitext with upgraded location
     * @param wikiText current wikitext
     * @param editedLocation new location
     * @return String
     */
    private String getFormattedWikiText(final String wikiText, final String editedLocation) {
        if (!ListenerUtil.mutListener.listen(557)) {
            if ((ListenerUtil.mutListener.listen(533) ? (wikiText.contains("filedesc") || wikiText.contains("Location")) : (wikiText.contains("filedesc") && wikiText.contains("Location")))) {
                final String fromLocationToEnd = wikiText.substring(wikiText.indexOf("{{Location"));
                final String firstHalf = wikiText.substring(0, wikiText.indexOf("{{Location"));
                final String lastHalf = fromLocationToEnd.substring((ListenerUtil.mutListener.listen(537) ? (fromLocationToEnd.indexOf("}}") % 2) : (ListenerUtil.mutListener.listen(536) ? (fromLocationToEnd.indexOf("}}") / 2) : (ListenerUtil.mutListener.listen(535) ? (fromLocationToEnd.indexOf("}}") * 2) : (ListenerUtil.mutListener.listen(534) ? (fromLocationToEnd.indexOf("}}") - 2) : (fromLocationToEnd.indexOf("}}") + 2))))));
                final int startOfSecondSection = StringUtils.ordinalIndexOf(wikiText, "==", 3);
                final StringBuilder buffer = new StringBuilder();
                if (!ListenerUtil.mutListener.listen(544)) {
                    if (wikiText.charAt((ListenerUtil.mutListener.listen(541) ? (wikiText.indexOf("{{Location") % 1) : (ListenerUtil.mutListener.listen(540) ? (wikiText.indexOf("{{Location") / 1) : (ListenerUtil.mutListener.listen(539) ? (wikiText.indexOf("{{Location") * 1) : (ListenerUtil.mutListener.listen(538) ? (wikiText.indexOf("{{Location") + 1) : (wikiText.indexOf("{{Location") - 1)))))) == '\n') {
                        if (!ListenerUtil.mutListener.listen(543)) {
                            buffer.append(editedLocation.substring(1));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(542)) {
                            buffer.append(editedLocation);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(556)) {
                    if ((ListenerUtil.mutListener.listen(554) ? ((ListenerUtil.mutListener.listen(549) ? (startOfSecondSection >= -1) : (ListenerUtil.mutListener.listen(548) ? (startOfSecondSection <= -1) : (ListenerUtil.mutListener.listen(547) ? (startOfSecondSection > -1) : (ListenerUtil.mutListener.listen(546) ? (startOfSecondSection < -1) : (ListenerUtil.mutListener.listen(545) ? (startOfSecondSection == -1) : (startOfSecondSection != -1)))))) || wikiText.charAt((ListenerUtil.mutListener.listen(553) ? (startOfSecondSection % 1) : (ListenerUtil.mutListener.listen(552) ? (startOfSecondSection / 1) : (ListenerUtil.mutListener.listen(551) ? (startOfSecondSection * 1) : (ListenerUtil.mutListener.listen(550) ? (startOfSecondSection + 1) : (startOfSecondSection - 1)))))) != '\n') : ((ListenerUtil.mutListener.listen(549) ? (startOfSecondSection >= -1) : (ListenerUtil.mutListener.listen(548) ? (startOfSecondSection <= -1) : (ListenerUtil.mutListener.listen(547) ? (startOfSecondSection > -1) : (ListenerUtil.mutListener.listen(546) ? (startOfSecondSection < -1) : (ListenerUtil.mutListener.listen(545) ? (startOfSecondSection == -1) : (startOfSecondSection != -1)))))) && wikiText.charAt((ListenerUtil.mutListener.listen(553) ? (startOfSecondSection % 1) : (ListenerUtil.mutListener.listen(552) ? (startOfSecondSection / 1) : (ListenerUtil.mutListener.listen(551) ? (startOfSecondSection * 1) : (ListenerUtil.mutListener.listen(550) ? (startOfSecondSection + 1) : (startOfSecondSection - 1)))))) != '\n'))) {
                        if (!ListenerUtil.mutListener.listen(555)) {
                            buffer.append("\n");
                        }
                    }
                }
                return firstHalf + buffer + lastHalf;
            }
        }
        if (!ListenerUtil.mutListener.listen(565)) {
            if ((ListenerUtil.mutListener.listen(558) ? (wikiText.contains("filedesc") || !wikiText.contains("Location")) : (wikiText.contains("filedesc") && !wikiText.contains("Location")))) {
                final int startOfSecondSection = StringUtils.ordinalIndexOf(wikiText, "==", 3);
                if (!ListenerUtil.mutListener.listen(564)) {
                    if ((ListenerUtil.mutListener.listen(563) ? (startOfSecondSection >= -1) : (ListenerUtil.mutListener.listen(562) ? (startOfSecondSection <= -1) : (ListenerUtil.mutListener.listen(561) ? (startOfSecondSection > -1) : (ListenerUtil.mutListener.listen(560) ? (startOfSecondSection < -1) : (ListenerUtil.mutListener.listen(559) ? (startOfSecondSection == -1) : (startOfSecondSection != -1))))))) {
                        final String firstHalf = wikiText.substring(0, startOfSecondSection);
                        final String lastHalf = wikiText.substring(startOfSecondSection);
                        final String buffer = editedLocation.substring(1) + "\n";
                        return firstHalf + buffer + lastHalf;
                    }
                }
                return wikiText + editedLocation;
            }
        }
        return "== {{int:filedesc}} ==" + editedLocation + wikiText;
    }

    /**
     * Update coordinates and shows notification about coordinates update
     * @param context to be added
     * @param media to be added
     * @param latitude to be added
     * @param longitude to be added
     * @param Accuracy to be added
     * @param result to be added
     * @return boolean
     */
    private boolean showCoordinatesEditNotification(final Context context, final Media media, final String latitude, final String longitude, final String Accuracy, final boolean result) {
        final String message;
        String title = context.getString(R.string.coordinates_edit_helper_show_edit_title);
        if (result) {
            if (!ListenerUtil.mutListener.listen(567)) {
                media.setCoordinates(new fr.free.nrw.commons.location.LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude), Float.parseFloat(Accuracy)));
            }
            if (!ListenerUtil.mutListener.listen(568)) {
                title += ": " + context.getString(R.string.coordinates_edit_helper_show_edit_title_success);
            }
            final StringBuilder coordinatesInMessage = new StringBuilder();
            final String mediaCoordinate = String.valueOf(media.getCoordinates());
            if (!ListenerUtil.mutListener.listen(569)) {
                coordinatesInMessage.append(mediaCoordinate);
            }
            message = context.getString(R.string.coordinates_edit_helper_show_edit_message, coordinatesInMessage.toString());
        } else {
            if (!ListenerUtil.mutListener.listen(566)) {
                title += ": " + context.getString(R.string.coordinates_edit_helper_show_edit_title);
            }
            message = context.getString(R.string.coordinates_edit_helper_edit_message_else);
        }
        final String urlForFile = BuildConfig.COMMONS_URL + "/wiki/" + media.getFilename();
        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForFile));
        if (!ListenerUtil.mutListener.listen(570)) {
            notificationHelper.showNotification(context, title, message, NOTIFICATION_EDIT_COORDINATES, browserIntent);
        }
        return result;
    }
}
