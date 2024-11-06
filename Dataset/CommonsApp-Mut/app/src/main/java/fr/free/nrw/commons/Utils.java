package fr.free.nrw.commons;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import java.util.Calendar;
import java.util.Date;
import org.wikipedia.dataclient.WikiSite;
import org.wikipedia.page.PageTitle;
import java.util.Locale;
import java.util.regex.Pattern;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.utils.ViewUtil;
import timber.log.Timber;
import static android.widget.Toast.LENGTH_SHORT;
import static fr.free.nrw.commons.campaigns.CampaignView.CAMPAIGNS_DEFAULT_PREFERENCE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Utils {

    public static PageTitle getPageTitle(@NonNull String title) {
        return new PageTitle(title, new WikiSite(BuildConfig.COMMONS_URL));
    }

    /**
     * Generates licence name with given ID
     * @param license License ID
     * @return Name of license
     */
    public static int licenseNameFor(String license) {
        switch(license) {
            case Prefs.Licenses.CC_BY_3:
                return R.string.license_name_cc_by;
            case Prefs.Licenses.CC_BY_4:
                return R.string.license_name_cc_by_four;
            case Prefs.Licenses.CC_BY_SA_3:
                return R.string.license_name_cc_by_sa;
            case Prefs.Licenses.CC_BY_SA_4:
                return R.string.license_name_cc_by_sa_four;
            case Prefs.Licenses.CC0:
                return R.string.license_name_cc0;
        }
        throw new IllegalStateException("Unrecognized license value: " + license);
    }

    @NonNull
    public static String licenseUrlFor(String license) {
        switch(license) {
            case Prefs.Licenses.CC_BY_3:
                return "https://creativecommons.org/licenses/by/3.0/";
            case Prefs.Licenses.CC_BY_4:
                return "https://creativecommons.org/licenses/by/4.0/";
            case Prefs.Licenses.CC_BY_SA_3:
                return "https://creativecommons.org/licenses/by-sa/3.0/";
            case Prefs.Licenses.CC_BY_SA_4:
                return "https://creativecommons.org/licenses/by-sa/4.0/";
            case Prefs.Licenses.CC0:
                return "https://creativecommons.org/publicdomain/zero/1.0/";
            default:
                throw new IllegalStateException("Unrecognized license value: " + license);
        }
    }

    /**
     * Adds extension to filename. Converts to .jpg if system provides .jpeg, adds .jpg if no extension detected
     * @param title File name
     * @param extension Correct extension
     * @return File with correct extension
     */
    public static String fixExtension(String title, String extension) {
        Pattern jpegPattern = Pattern.compile("\\.jpeg$", Pattern.CASE_INSENSITIVE);
        if (!ListenerUtil.mutListener.listen(9613)) {
            // People are used to ".jpg" more than ".jpeg" which the system gives us.
            if ((ListenerUtil.mutListener.listen(9611) ? (extension != null || extension.toLowerCase(Locale.ENGLISH).equals("jpeg")) : (extension != null && extension.toLowerCase(Locale.ENGLISH).equals("jpeg")))) {
                if (!ListenerUtil.mutListener.listen(9612)) {
                    extension = "jpg";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9614)) {
            title = jpegPattern.matcher(title).replaceFirst(".jpg");
        }
        if (!ListenerUtil.mutListener.listen(9617)) {
            if ((ListenerUtil.mutListener.listen(9615) ? (extension != null || !title.toLowerCase(Locale.getDefault()).endsWith("." + extension.toLowerCase(Locale.ENGLISH))) : (extension != null && !title.toLowerCase(Locale.getDefault()).endsWith("." + extension.toLowerCase(Locale.ENGLISH))))) {
                if (!ListenerUtil.mutListener.listen(9616)) {
                    title += "." + extension;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9626)) {
            // If title has an extension in it, if won't be true
            if ((ListenerUtil.mutListener.listen(9623) ? (extension == null || (ListenerUtil.mutListener.listen(9622) ? (title.lastIndexOf(".") >= 0) : (ListenerUtil.mutListener.listen(9621) ? (title.lastIndexOf(".") > 0) : (ListenerUtil.mutListener.listen(9620) ? (title.lastIndexOf(".") < 0) : (ListenerUtil.mutListener.listen(9619) ? (title.lastIndexOf(".") != 0) : (ListenerUtil.mutListener.listen(9618) ? (title.lastIndexOf(".") == 0) : (title.lastIndexOf(".") <= 0))))))) : (extension == null && (ListenerUtil.mutListener.listen(9622) ? (title.lastIndexOf(".") >= 0) : (ListenerUtil.mutListener.listen(9621) ? (title.lastIndexOf(".") > 0) : (ListenerUtil.mutListener.listen(9620) ? (title.lastIndexOf(".") < 0) : (ListenerUtil.mutListener.listen(9619) ? (title.lastIndexOf(".") != 0) : (ListenerUtil.mutListener.listen(9618) ? (title.lastIndexOf(".") == 0) : (title.lastIndexOf(".") <= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9624)) {
                    extension = "jpg";
                }
                if (!ListenerUtil.mutListener.listen(9625)) {
                    title += "." + extension;
                }
            }
        }
        return title;
    }

    /**
     * Launches intent to rate app
     * @param context
     */
    public static void rateApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            if (!ListenerUtil.mutListener.listen(9628)) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Urls.PLAY_STORE_PREFIX + appPackageName)));
            }
        } catch (android.content.ActivityNotFoundException anfe) {
            if (!ListenerUtil.mutListener.listen(9627)) {
                handleWebUrl(context, Uri.parse(Urls.PLAY_STORE_URL_PREFIX + appPackageName));
            }
        }
    }

    /**
     * Opens Custom Tab Activity with in-app browser for the specified URL.
     * Launches intent for web URL
     * @param context
     * @param url
     */
    public static void handleWebUrl(Context context, Uri url) {
        if (!ListenerUtil.mutListener.listen(9629)) {
            Timber.d("Launching web url %s", url.toString());
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, url);
        if (!ListenerUtil.mutListener.listen(9631)) {
            if (browserIntent.resolveActivity(context.getPackageManager()) == null) {
                Toast toast = Toast.makeText(context, context.getString(R.string.no_web_browser), LENGTH_SHORT);
                if (!ListenerUtil.mutListener.listen(9630)) {
                    toast.show();
                }
                return;
            }
        }
        final CustomTabColorSchemeParams color = new CustomTabColorSchemeParams.Builder().setToolbarColor(ContextCompat.getColor(context, R.color.primaryColor)).setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.primaryDarkColor)).build();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        if (!ListenerUtil.mutListener.listen(9632)) {
            builder.setDefaultColorSchemeParams(color);
        }
        if (!ListenerUtil.mutListener.listen(9633)) {
            builder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
        CustomTabsIntent customTabsIntent = builder.build();
        if (!ListenerUtil.mutListener.listen(9634)) {
            // Clear previous browser tasks, so that back/exit buttons work as intended.
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(9635)) {
            customTabsIntent.launchUrl(context, url);
        }
    }

    /**
     * Util function to handle geo coordinates
     * It no longer depends on google maps and any app capable of handling the map intent can handle it
     * @param context
     * @param latLng
     */
    public static void handleGeoCoordinates(Context context, LatLng latLng) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, latLng.getGmmIntentUri());
        if (!ListenerUtil.mutListener.listen(9638)) {
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                if (!ListenerUtil.mutListener.listen(9637)) {
                    context.startActivity(mapIntent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9636)) {
                    ViewUtil.showShortToast(context, context.getString(R.string.map_application_missing));
                }
            }
        }
    }

    /**
     * To take screenshot of the screen and return it in Bitmap format
     *
     * @param view
     * @return
     */
    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        if (!ListenerUtil.mutListener.listen(9639)) {
            screenView.setDrawingCacheEnabled(true);
        }
        Bitmap drawingCache = screenView.getDrawingCache();
        if (!ListenerUtil.mutListener.listen(9641)) {
            if (drawingCache != null) {
                Bitmap bitmap = Bitmap.createBitmap(drawingCache);
                if (!ListenerUtil.mutListener.listen(9640)) {
                    screenView.setDrawingCacheEnabled(false);
                }
                return bitmap;
            }
        }
        return null;
    }

    /*
    *Copies the content to the clipboard
    *
    */
    public static void copy(String label, String text, Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        if (!ListenerUtil.mutListener.listen(9642)) {
            clipboard.setPrimaryClip(clip);
        }
    }

    /**
     * This method sets underlined string text to a TextView
     *
     * @param textView TextView associated with string resource
     * @param stringResourceName string resource name
     * @param context
     */
    public static void setUnderlinedText(TextView textView, int stringResourceName, Context context) {
        SpannableString content = new SpannableString(context.getString(stringResourceName));
        if (!ListenerUtil.mutListener.listen(9643)) {
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        }
        if (!ListenerUtil.mutListener.listen(9644)) {
            textView.setText(content);
        }
    }

    /**
     * For now we are enabling the monuments only when the date lies between 1 Sept & 31 OCt
     * @param date
     * @return
     */
    public static boolean isMonumentsEnabled(final Date date) {
        if (!ListenerUtil.mutListener.listen(9650)) {
            if ((ListenerUtil.mutListener.listen(9649) ? (date.getMonth() >= 8) : (ListenerUtil.mutListener.listen(9648) ? (date.getMonth() <= 8) : (ListenerUtil.mutListener.listen(9647) ? (date.getMonth() > 8) : (ListenerUtil.mutListener.listen(9646) ? (date.getMonth() < 8) : (ListenerUtil.mutListener.listen(9645) ? (date.getMonth() != 8) : (date.getMonth() == 8))))))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Util function to get the start date of wlm monument
     * For this release we are hardcoding it to be 1st September
     * @return
     */
    public static String getWLMStartDate() {
        return "1 Sep";
    }

    /**
     *  Util function to get the end date of wlm monument
     *  For this release we are hardcoding it to be 31st October
     *  @return
     */
    public static String getWLMEndDate() {
        return "30 Sep";
    }

    /**
     *  Function to get the current WLM year
     *  It increments at the start of September in line with the other WLM functions
     *  (No consideration of locales for now)
     *  @param calendar
     *  @return
     */
    public static int getWikiLovesMonumentsYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        if (!ListenerUtil.mutListener.listen(9657)) {
            if ((ListenerUtil.mutListener.listen(9655) ? (calendar.get(Calendar.MONTH) >= Calendar.SEPTEMBER) : (ListenerUtil.mutListener.listen(9654) ? (calendar.get(Calendar.MONTH) <= Calendar.SEPTEMBER) : (ListenerUtil.mutListener.listen(9653) ? (calendar.get(Calendar.MONTH) > Calendar.SEPTEMBER) : (ListenerUtil.mutListener.listen(9652) ? (calendar.get(Calendar.MONTH) != Calendar.SEPTEMBER) : (ListenerUtil.mutListener.listen(9651) ? (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER) : (calendar.get(Calendar.MONTH) < Calendar.SEPTEMBER))))))) {
                if (!ListenerUtil.mutListener.listen(9656)) {
                    year -= 1;
                }
            }
        }
        return year;
    }
}
