package org.wordpress.android.ui.notifications.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationManagerCompat;
import com.android.volley.VolleyError;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.wordpress.rest.RestRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.fluxc.tools.FormattableContent;
import org.wordpress.android.fluxc.tools.FormattableContentMapper;
import org.wordpress.android.fluxc.tools.FormattableMedia;
import org.wordpress.android.fluxc.tools.FormattableRange;
import org.wordpress.android.models.Note;
import org.wordpress.android.push.GCMMessageService;
import org.wordpress.android.ui.notifications.blocks.NoteBlock;
import org.wordpress.android.ui.notifications.blocks.NoteBlockClickableSpan;
import org.wordpress.android.ui.notifications.blocks.NoteBlockLinkMovementMethod;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DeviceUtils;
import org.wordpress.android.util.PackageUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.image.getters.WPCustomImageGetter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsUtils {

    public static final String ARG_PUSH_AUTH_TOKEN = "arg_push_auth_token";

    public static final String ARG_PUSH_AUTH_TITLE = "arg_push_auth_title";

    public static final String ARG_PUSH_AUTH_MESSAGE = "arg_push_auth_message";

    public static final String ARG_PUSH_AUTH_EXPIRES = "arg_push_auth_expires";

    public static final String WPCOM_PUSH_DEVICE_NOTIFICATION_SETTINGS = "wp_pref_notification_settings";

    public static final String WPCOM_PUSH_DEVICE_UUID = "wp_pref_notifications_uuid";

    public static final String WPCOM_PUSH_DEVICE_TOKEN = "wp_pref_notifications_token";

    public static final String WPCOM_PUSH_DEVICE_SERVER_ID = "wp_pref_notifications_server_id";

    public static final String PUSH_AUTH_ENDPOINT = "me/two-step/push-authentication";

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";

    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    private static final String WPCOM_SETTINGS_ENDPOINT = "/me/notifications/settings/";

    public interface TwoFactorAuthCallback {

        void onTokenValid(String token, String title, String message);

        void onTokenInvalid();
    }

    public static void getPushNotificationSettings(Context context, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String deviceID = settings.getString(WPCOM_PUSH_DEVICE_SERVER_ID, null);
        String settingsEndpoint = WPCOM_SETTINGS_ENDPOINT;
        if (!ListenerUtil.mutListener.listen(8854)) {
            if (!TextUtils.isEmpty(deviceID)) {
                if (!ListenerUtil.mutListener.listen(8853)) {
                    settingsEndpoint += "?device_id=" + deviceID;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8855)) {
            WordPress.getRestClientUtilsV1_1().get(settingsEndpoint, listener, errorListener);
        }
    }

    public static void registerDeviceForPushNotifications(final Context ctx, String token) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        String uuid = settings.getString(WPCOM_PUSH_DEVICE_UUID, null);
        if (!ListenerUtil.mutListener.listen(8856)) {
            if (uuid == null) {
                return;
            }
        }
        String deviceName = DeviceUtils.getInstance().getDeviceName(ctx);
        Map<String, String> contentStruct = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(8857)) {
            contentStruct.put("app_secret_key", BuildConfig.PUSH_NOTIFICATIONS_APP_KEY);
        }
        if (!ListenerUtil.mutListener.listen(8858)) {
            contentStruct.put("device_token", token);
        }
        if (!ListenerUtil.mutListener.listen(8859)) {
            contentStruct.put("device_family", "android");
        }
        if (!ListenerUtil.mutListener.listen(8860)) {
            contentStruct.put("device_name", deviceName);
        }
        if (!ListenerUtil.mutListener.listen(8861)) {
            contentStruct.put("device_model", Build.MANUFACTURER + " " + Build.MODEL);
        }
        if (!ListenerUtil.mutListener.listen(8862)) {
            contentStruct.put("app_version", WordPress.versionName);
        }
        if (!ListenerUtil.mutListener.listen(8863)) {
            contentStruct.put("version_code", String.valueOf(PackageUtils.getVersionCode(ctx)));
        }
        if (!ListenerUtil.mutListener.listen(8864)) {
            contentStruct.put("os_version", Build.VERSION.RELEASE);
        }
        if (!ListenerUtil.mutListener.listen(8865)) {
            contentStruct.put("device_uuid", uuid);
        }
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(8866)) {
                    AppLog.d(T.NOTIFS, "Register token action succeeded");
                }
                try {
                    String deviceID = jsonObject.getString("ID");
                    if (!ListenerUtil.mutListener.listen(8869)) {
                        if (deviceID == null) {
                            if (!ListenerUtil.mutListener.listen(8868)) {
                                AppLog.e(T.NOTIFS, "Server response is missing of the device_id. Registration skipped!!");
                            }
                            return;
                        }
                    }
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
                    SharedPreferences.Editor editor = settings.edit();
                    if (!ListenerUtil.mutListener.listen(8870)) {
                        editor.putString(WPCOM_PUSH_DEVICE_SERVER_ID, deviceID);
                    }
                    if (!ListenerUtil.mutListener.listen(8871)) {
                        editor.apply();
                    }
                    if (!ListenerUtil.mutListener.listen(8872)) {
                        AppLog.d(T.NOTIFS, "Server response OK. The device_id: " + deviceID);
                    }
                } catch (JSONException e1) {
                    if (!ListenerUtil.mutListener.listen(8867)) {
                        AppLog.e(T.NOTIFS, "Server response is NOT ok, registration skipped.", e1);
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(8873)) {
                    AppLog.e(T.NOTIFS, "Register token action failed", volleyError);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(8874)) {
            WordPress.getRestClientUtils().post("/devices/new", contentStruct, null, listener, errorListener);
        }
    }

    public static void unregisterDevicePushNotifications(final Context ctx) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(8875)) {
                    AppLog.d(T.NOTIFS, "Unregister token action succeeded");
                }
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
                if (!ListenerUtil.mutListener.listen(8876)) {
                    editor.remove(WPCOM_PUSH_DEVICE_SERVER_ID);
                }
                if (!ListenerUtil.mutListener.listen(8877)) {
                    editor.remove(WPCOM_PUSH_DEVICE_UUID);
                }
                if (!ListenerUtil.mutListener.listen(8878)) {
                    editor.remove(WPCOM_PUSH_DEVICE_TOKEN);
                }
                if (!ListenerUtil.mutListener.listen(8879)) {
                    editor.apply();
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(8880)) {
                    AppLog.e(T.NOTIFS, "Unregister token action failed", volleyError);
                }
            }
        };
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        String deviceID = settings.getString(WPCOM_PUSH_DEVICE_SERVER_ID, null);
        if (!ListenerUtil.mutListener.listen(8881)) {
            if (TextUtils.isEmpty(deviceID)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8882)) {
            WordPress.getRestClientUtils().post("/devices/" + deviceID + "/delete", listener, errorListener);
        }
    }

    static FormattableContent mapJsonToFormattableContent(FormattableContentMapper mapper, JSONObject blockObject) {
        return mapper.mapToFormattableContent(blockObject.toString());
    }

    public static void cancelAllNotifications(Context context) {
        if (!ListenerUtil.mutListener.listen(8883)) {
            NotificationManagerCompat.from(context).cancelAll();
        }
    }

    static SpannableStringBuilder getSpannableContentForRanges(FormattableContentMapper formattableContentMapper, JSONObject blockObject, TextView textView, final NoteBlock.OnNoteBlockTextClickListener onNoteBlockTextClickListener, boolean isFooter) {
        return getSpannableContentForRanges(mapJsonToFormattableContent(formattableContentMapper, blockObject), textView, onNoteBlockTextClickListener, isFooter);
    }

    /**
     * Returns a spannable with formatted content based on WP.com note content 'range' data
     *
     * @param formattableContent the data
     * @param textView the TextView that will display the spannnable
     * @param onNoteBlockTextClickListener - click listener for ClickableSpans in the spannable
     * @param isFooter - Set if spannable should apply special formatting
     * @return Spannable string with formatted content
     */
    static SpannableStringBuilder getSpannableContentForRanges(FormattableContent formattableContent, TextView textView, final NoteBlock.OnNoteBlockTextClickListener onNoteBlockTextClickListener, boolean isFooter) {
        Function1<NoteBlockClickableSpan, Unit> clickListener = onNoteBlockTextClickListener != null ? new Function1<NoteBlockClickableSpan, Unit>() {

            @Override
            public Unit invoke(NoteBlockClickableSpan noteBlockClickableSpan) {
                if (!ListenerUtil.mutListener.listen(8884)) {
                    onNoteBlockTextClickListener.onNoteBlockTextClicked(noteBlockClickableSpan);
                }
                return null;
            }
        } : null;
        return getSpannableContentForRanges(formattableContent, textView, isFooter, clickListener);
    }

    /**
     * Returns a spannable with formatted content based on WP.com note content 'range' data
     *
     * @param formattableContent the data
     * @param textView the TextView that will display the spannnable
     * @param clickHandler - click listener for ClickableSpans in the spannable
     * @param isFooter - Set if spannable should apply special formatting
     * @return Spannable string with formatted content
     */
    static SpannableStringBuilder getSpannableContentForRanges(FormattableContent formattableContent, TextView textView, final Function1<FormattableRange, Unit> clickHandler, boolean isFooter) {
        Function1<NoteBlockClickableSpan, Unit> clickListener = clickHandler != null ? new Function1<NoteBlockClickableSpan, Unit>() {

            @Override
            public Unit invoke(NoteBlockClickableSpan noteBlockClickableSpan) {
                if (!ListenerUtil.mutListener.listen(8885)) {
                    clickHandler.invoke(noteBlockClickableSpan.getFormattableRange());
                }
                return null;
            }
        } : null;
        return getSpannableContentForRanges(formattableContent, textView, isFooter, clickListener);
    }

    /**
     * Returns a spannable with formatted content based on WP.com note content 'range' data
     *
     * @param formattableContent the data
     * @param textView the TextView that will display the spannnable
     * @param onNoteBlockTextClickListener - click listener for ClickableSpans in the spannable
     * @param isFooter - Set if spannable should apply special formatting
     * @return Spannable string with formatted content
     */
    private static SpannableStringBuilder getSpannableContentForRanges(FormattableContent formattableContent, TextView textView, boolean isFooter, final Function1<NoteBlockClickableSpan, Unit> onNoteBlockTextClickListener) {
        if (!ListenerUtil.mutListener.listen(8886)) {
            if (formattableContent == null) {
                return new SpannableStringBuilder();
            }
        }
        String text = formattableContent.getText();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        boolean shouldLink = onNoteBlockTextClickListener != null;
        if (!ListenerUtil.mutListener.listen(8887)) {
            // Add ImageSpans for note media
            addImageSpansForBlockMedia(textView, formattableContent, spannableStringBuilder);
        }
        // Process Ranges to add links and text formatting
        List<FormattableRange> rangesArray = formattableContent.getRanges();
        if (!ListenerUtil.mutListener.listen(8917)) {
            if (rangesArray != null) {
                if (!ListenerUtil.mutListener.listen(8916)) {
                    {
                        long _loopCounter176 = 0;
                        for (FormattableRange range : rangesArray) {
                            ListenerUtil.loopListener.listen("_loopCounter176", ++_loopCounter176);
                            NoteBlockClickableSpan clickableSpan = new NoteBlockClickableSpan(range, shouldLink, isFooter) {

                                @Override
                                public void onClick(View widget) {
                                    if (!ListenerUtil.mutListener.listen(8889)) {
                                        if (onNoteBlockTextClickListener != null) {
                                            if (!ListenerUtil.mutListener.listen(8888)) {
                                                onNoteBlockTextClickListener.invoke(this);
                                            }
                                        }
                                    }
                                }
                            };
                            List<Integer> indices = clickableSpan.getIndices();
                            if (!ListenerUtil.mutListener.listen(8915)) {
                                if ((ListenerUtil.mutListener.listen(8907) ? ((ListenerUtil.mutListener.listen(8901) ? ((ListenerUtil.mutListener.listen(8895) ? (indices != null || (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2))))))) : (indices != null && (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2)))))))) || (ListenerUtil.mutListener.listen(8900) ? (indices.get(0) >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8899) ? (indices.get(0) > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8898) ? (indices.get(0) < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8897) ? (indices.get(0) != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8896) ? (indices.get(0) == spannableStringBuilder.length()) : (indices.get(0) <= spannableStringBuilder.length()))))))) : ((ListenerUtil.mutListener.listen(8895) ? (indices != null || (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2))))))) : (indices != null && (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2)))))))) && (ListenerUtil.mutListener.listen(8900) ? (indices.get(0) >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8899) ? (indices.get(0) > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8898) ? (indices.get(0) < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8897) ? (indices.get(0) != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8896) ? (indices.get(0) == spannableStringBuilder.length()) : (indices.get(0) <= spannableStringBuilder.length())))))))) || (ListenerUtil.mutListener.listen(8906) ? (indices.get(1) >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8905) ? (indices.get(1) > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8904) ? (indices.get(1) < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8903) ? (indices.get(1) != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8902) ? (indices.get(1) == spannableStringBuilder.length()) : (indices.get(1) <= spannableStringBuilder.length()))))))) : ((ListenerUtil.mutListener.listen(8901) ? ((ListenerUtil.mutListener.listen(8895) ? (indices != null || (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2))))))) : (indices != null && (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2)))))))) || (ListenerUtil.mutListener.listen(8900) ? (indices.get(0) >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8899) ? (indices.get(0) > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8898) ? (indices.get(0) < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8897) ? (indices.get(0) != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8896) ? (indices.get(0) == spannableStringBuilder.length()) : (indices.get(0) <= spannableStringBuilder.length()))))))) : ((ListenerUtil.mutListener.listen(8895) ? (indices != null || (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2))))))) : (indices != null && (ListenerUtil.mutListener.listen(8894) ? (indices.size() >= 2) : (ListenerUtil.mutListener.listen(8893) ? (indices.size() <= 2) : (ListenerUtil.mutListener.listen(8892) ? (indices.size() > 2) : (ListenerUtil.mutListener.listen(8891) ? (indices.size() < 2) : (ListenerUtil.mutListener.listen(8890) ? (indices.size() != 2) : (indices.size() == 2)))))))) && (ListenerUtil.mutListener.listen(8900) ? (indices.get(0) >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8899) ? (indices.get(0) > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8898) ? (indices.get(0) < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8897) ? (indices.get(0) != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8896) ? (indices.get(0) == spannableStringBuilder.length()) : (indices.get(0) <= spannableStringBuilder.length())))))))) && (ListenerUtil.mutListener.listen(8906) ? (indices.get(1) >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8905) ? (indices.get(1) > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8904) ? (indices.get(1) < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8903) ? (indices.get(1) != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8902) ? (indices.get(1) == spannableStringBuilder.length()) : (indices.get(1) <= spannableStringBuilder.length()))))))))) {
                                    if (!ListenerUtil.mutListener.listen(8908)) {
                                        spannableStringBuilder.setSpan(clickableSpan, indices.get(0), indices.get(1), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(8910)) {
                                        // Add additional styling if the range wants it
                                        if (clickableSpan.getSpanStyle() != Typeface.NORMAL) {
                                            StyleSpan styleSpan = new StyleSpan(clickableSpan.getSpanStyle());
                                            if (!ListenerUtil.mutListener.listen(8909)) {
                                                spannableStringBuilder.setSpan(styleSpan, indices.get(0), indices.get(1), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(8914)) {
                                        if ((ListenerUtil.mutListener.listen(8911) ? (onNoteBlockTextClickListener != null || textView != null) : (onNoteBlockTextClickListener != null && textView != null))) {
                                            if (!ListenerUtil.mutListener.listen(8912)) {
                                                textView.setLinksClickable(true);
                                            }
                                            if (!ListenerUtil.mutListener.listen(8913)) {
                                                textView.setMovementMethod(new NoteBlockLinkMovementMethod());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return spannableStringBuilder;
    }

    public static int[] getIndicesForRange(JSONObject rangeObject) {
        int[] indices = new int[] { 0, 0 };
        if (!ListenerUtil.mutListener.listen(8918)) {
            if (rangeObject == null) {
                return indices;
            }
        }
        JSONArray indicesArray = rangeObject.optJSONArray("indices");
        if (!ListenerUtil.mutListener.listen(8927)) {
            if ((ListenerUtil.mutListener.listen(8924) ? (indicesArray != null || (ListenerUtil.mutListener.listen(8923) ? (indicesArray.length() <= 2) : (ListenerUtil.mutListener.listen(8922) ? (indicesArray.length() > 2) : (ListenerUtil.mutListener.listen(8921) ? (indicesArray.length() < 2) : (ListenerUtil.mutListener.listen(8920) ? (indicesArray.length() != 2) : (ListenerUtil.mutListener.listen(8919) ? (indicesArray.length() == 2) : (indicesArray.length() >= 2))))))) : (indicesArray != null && (ListenerUtil.mutListener.listen(8923) ? (indicesArray.length() <= 2) : (ListenerUtil.mutListener.listen(8922) ? (indicesArray.length() > 2) : (ListenerUtil.mutListener.listen(8921) ? (indicesArray.length() < 2) : (ListenerUtil.mutListener.listen(8920) ? (indicesArray.length() != 2) : (ListenerUtil.mutListener.listen(8919) ? (indicesArray.length() == 2) : (indicesArray.length() >= 2))))))))) {
                if (!ListenerUtil.mutListener.listen(8925)) {
                    indices[0] = indicesArray.optInt(0);
                }
                if (!ListenerUtil.mutListener.listen(8926)) {
                    indices[1] = indicesArray.optInt(1);
                }
            }
        }
        return indices;
    }

    /**
     * Adds ImageSpans to the passed SpannableStringBuilder
     */
    private static void addImageSpansForBlockMedia(TextView textView, FormattableContent subject, SpannableStringBuilder spannableStringBuilder) {
        if (!ListenerUtil.mutListener.listen(8930)) {
            if ((ListenerUtil.mutListener.listen(8929) ? ((ListenerUtil.mutListener.listen(8928) ? (textView == null && subject == null) : (textView == null || subject == null)) && spannableStringBuilder == null) : ((ListenerUtil.mutListener.listen(8928) ? (textView == null && subject == null) : (textView == null || subject == null)) || spannableStringBuilder == null))) {
                return;
            }
        }
        Context context = textView.getContext();
        List<FormattableMedia> mediaArray = subject.getMedia();
        if (!ListenerUtil.mutListener.listen(8932)) {
            if ((ListenerUtil.mutListener.listen(8931) ? (context == null && mediaArray == null) : (context == null || mediaArray == null))) {
                return;
            }
        }
        // otherwise it would load blank white
        WPCustomImageGetter imageGetter = new WPCustomImageGetter(textView, context.getResources().getDimensionPixelSize(R.dimen.notifications_max_image_size), textView.getLineHeight());
        int indexAdjustment = 0;
        String imagePlaceholder;
        {
            long _loopCounter177 = 0;
            for (FormattableMedia mediaObject : mediaArray) {
                ListenerUtil.loopListener.listen("_loopCounter177", ++_loopCounter177);
                if (!ListenerUtil.mutListener.listen(8933)) {
                    if (mediaObject == null) {
                        continue;
                    }
                }
                final Drawable remoteDrawable = imageGetter.getDrawable(StringUtils.notNullStr(mediaObject.getUrl()));
                ImageSpan noteImageSpan = new ImageSpan(remoteDrawable, StringUtils.notNullStr(mediaObject.getUrl()));
                int startIndex = -1;
                int endIndex = -1;
                List<Integer> indices = ((ListenerUtil.mutListener.listen(8934) ? (mediaObject.getIndices() != null || mediaObject.getIndices().size() == 2) : (mediaObject.getIndices() != null && mediaObject.getIndices().size() == 2))) ? mediaObject.getIndices() : null;
                if (!ListenerUtil.mutListener.listen(8937)) {
                    if (indices != null) {
                        if (!ListenerUtil.mutListener.listen(8935)) {
                            startIndex = indices.get(0);
                        }
                        if (!ListenerUtil.mutListener.listen(8936)) {
                            endIndex = indices.get(1);
                        }
                    }
                }
                if ((ListenerUtil.mutListener.listen(8942) ? (startIndex <= 0) : (ListenerUtil.mutListener.listen(8941) ? (startIndex > 0) : (ListenerUtil.mutListener.listen(8940) ? (startIndex < 0) : (ListenerUtil.mutListener.listen(8939) ? (startIndex != 0) : (ListenerUtil.mutListener.listen(8938) ? (startIndex == 0) : (startIndex >= 0))))))) {
                    if (!ListenerUtil.mutListener.listen(8943)) {
                        startIndex += indexAdjustment;
                    }
                    if (!ListenerUtil.mutListener.listen(8944)) {
                        endIndex += indexAdjustment;
                    }
                    if (!ListenerUtil.mutListener.listen(8950)) {
                        if ((ListenerUtil.mutListener.listen(8949) ? (startIndex >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8948) ? (startIndex <= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8947) ? (startIndex < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8946) ? (startIndex != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8945) ? (startIndex == spannableStringBuilder.length()) : (startIndex > spannableStringBuilder.length()))))))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8963)) {
                        // If we have a range, it means there is alt text that should be removed
                        if ((ListenerUtil.mutListener.listen(8961) ? ((ListenerUtil.mutListener.listen(8955) ? (endIndex >= startIndex) : (ListenerUtil.mutListener.listen(8954) ? (endIndex <= startIndex) : (ListenerUtil.mutListener.listen(8953) ? (endIndex < startIndex) : (ListenerUtil.mutListener.listen(8952) ? (endIndex != startIndex) : (ListenerUtil.mutListener.listen(8951) ? (endIndex == startIndex) : (endIndex > startIndex)))))) || (ListenerUtil.mutListener.listen(8960) ? (endIndex >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8959) ? (endIndex > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8958) ? (endIndex < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8957) ? (endIndex != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8956) ? (endIndex == spannableStringBuilder.length()) : (endIndex <= spannableStringBuilder.length()))))))) : ((ListenerUtil.mutListener.listen(8955) ? (endIndex >= startIndex) : (ListenerUtil.mutListener.listen(8954) ? (endIndex <= startIndex) : (ListenerUtil.mutListener.listen(8953) ? (endIndex < startIndex) : (ListenerUtil.mutListener.listen(8952) ? (endIndex != startIndex) : (ListenerUtil.mutListener.listen(8951) ? (endIndex == startIndex) : (endIndex > startIndex)))))) && (ListenerUtil.mutListener.listen(8960) ? (endIndex >= spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8959) ? (endIndex > spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8958) ? (endIndex < spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8957) ? (endIndex != spannableStringBuilder.length()) : (ListenerUtil.mutListener.listen(8956) ? (endIndex == spannableStringBuilder.length()) : (endIndex <= spannableStringBuilder.length()))))))))) {
                            if (!ListenerUtil.mutListener.listen(8962)) {
                                spannableStringBuilder.replace(startIndex, endIndex, "");
                            }
                        }
                    }
                    // We need an empty space to insert the ImageSpan into
                    imagePlaceholder = " ";
                    // Move the image to a new line if needed
                    int previousCharIndex = ((ListenerUtil.mutListener.listen(8968) ? (startIndex >= 0) : (ListenerUtil.mutListener.listen(8967) ? (startIndex <= 0) : (ListenerUtil.mutListener.listen(8966) ? (startIndex < 0) : (ListenerUtil.mutListener.listen(8965) ? (startIndex != 0) : (ListenerUtil.mutListener.listen(8964) ? (startIndex == 0) : (startIndex > 0))))))) ? (ListenerUtil.mutListener.listen(8972) ? (startIndex % 1) : (ListenerUtil.mutListener.listen(8971) ? (startIndex / 1) : (ListenerUtil.mutListener.listen(8970) ? (startIndex * 1) : (ListenerUtil.mutListener.listen(8969) ? (startIndex + 1) : (startIndex - 1))))) : 0;
                    if ((ListenerUtil.mutListener.listen(8978) ? (!spannableHasCharacterAtIndex(spannableStringBuilder, '\n', previousCharIndex) && (ListenerUtil.mutListener.listen(8977) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length >= 0) : (ListenerUtil.mutListener.listen(8976) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length <= 0) : (ListenerUtil.mutListener.listen(8975) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length < 0) : (ListenerUtil.mutListener.listen(8974) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length != 0) : (ListenerUtil.mutListener.listen(8973) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length == 0) : (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length > 0))))))) : (!spannableHasCharacterAtIndex(spannableStringBuilder, '\n', previousCharIndex) || (ListenerUtil.mutListener.listen(8977) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length >= 0) : (ListenerUtil.mutListener.listen(8976) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length <= 0) : (ListenerUtil.mutListener.listen(8975) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length < 0) : (ListenerUtil.mutListener.listen(8974) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length != 0) : (ListenerUtil.mutListener.listen(8973) ? (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length == 0) : (spannableStringBuilder.getSpans(startIndex, startIndex, ImageSpan.class).length > 0))))))))) {
                        imagePlaceholder = "\n ";
                    }
                    int spanIndex = (ListenerUtil.mutListener.listen(8986) ? ((ListenerUtil.mutListener.listen(8982) ? (startIndex % imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8981) ? (startIndex / imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8980) ? (startIndex * imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8979) ? (startIndex - imagePlaceholder.length()) : (startIndex + imagePlaceholder.length()))))) % 1) : (ListenerUtil.mutListener.listen(8985) ? ((ListenerUtil.mutListener.listen(8982) ? (startIndex % imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8981) ? (startIndex / imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8980) ? (startIndex * imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8979) ? (startIndex - imagePlaceholder.length()) : (startIndex + imagePlaceholder.length()))))) / 1) : (ListenerUtil.mutListener.listen(8984) ? ((ListenerUtil.mutListener.listen(8982) ? (startIndex % imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8981) ? (startIndex / imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8980) ? (startIndex * imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8979) ? (startIndex - imagePlaceholder.length()) : (startIndex + imagePlaceholder.length()))))) * 1) : (ListenerUtil.mutListener.listen(8983) ? ((ListenerUtil.mutListener.listen(8982) ? (startIndex % imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8981) ? (startIndex / imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8980) ? (startIndex * imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8979) ? (startIndex - imagePlaceholder.length()) : (startIndex + imagePlaceholder.length()))))) + 1) : ((ListenerUtil.mutListener.listen(8982) ? (startIndex % imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8981) ? (startIndex / imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8980) ? (startIndex * imagePlaceholder.length()) : (ListenerUtil.mutListener.listen(8979) ? (startIndex - imagePlaceholder.length()) : (startIndex + imagePlaceholder.length()))))) - 1)))));
                    if (!ListenerUtil.mutListener.listen(8989)) {
                        // Add a newline after the image if needed
                        if ((ListenerUtil.mutListener.listen(8987) ? (!spannableHasCharacterAtIndex(spannableStringBuilder, '\n', startIndex) || !spannableHasCharacterAtIndex(spannableStringBuilder, '\r', startIndex)) : (!spannableHasCharacterAtIndex(spannableStringBuilder, '\n', startIndex) && !spannableHasCharacterAtIndex(spannableStringBuilder, '\r', startIndex)))) {
                            if (!ListenerUtil.mutListener.listen(8988)) {
                                imagePlaceholder += "\n";
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8990)) {
                        spannableStringBuilder.insert(startIndex, imagePlaceholder);
                    }
                    if (!ListenerUtil.mutListener.listen(8995)) {
                        // Add the image span
                        spannableStringBuilder.setSpan(noteImageSpan, spanIndex, (ListenerUtil.mutListener.listen(8994) ? (spanIndex % 1) : (ListenerUtil.mutListener.listen(8993) ? (spanIndex / 1) : (ListenerUtil.mutListener.listen(8992) ? (spanIndex * 1) : (ListenerUtil.mutListener.listen(8991) ? (spanIndex - 1) : (spanIndex + 1))))), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    if (!ListenerUtil.mutListener.listen(9000)) {
                        // Add an AlignmentSpan to center the image
                        spannableStringBuilder.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), spanIndex, (ListenerUtil.mutListener.listen(8999) ? (spanIndex % 1) : (ListenerUtil.mutListener.listen(8998) ? (spanIndex / 1) : (ListenerUtil.mutListener.listen(8997) ? (spanIndex * 1) : (ListenerUtil.mutListener.listen(8996) ? (spanIndex - 1) : (spanIndex + 1))))), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    if (!ListenerUtil.mutListener.listen(9001)) {
                        indexAdjustment += imagePlaceholder.length();
                    }
                }
            }
        }
    }

    public static boolean spannableHasCharacterAtIndex(Spannable spannable, char character, int index) {
        return (ListenerUtil.mutListener.listen(9008) ? ((ListenerUtil.mutListener.listen(9007) ? (spannable != null || (ListenerUtil.mutListener.listen(9006) ? (index >= spannable.length()) : (ListenerUtil.mutListener.listen(9005) ? (index <= spannable.length()) : (ListenerUtil.mutListener.listen(9004) ? (index > spannable.length()) : (ListenerUtil.mutListener.listen(9003) ? (index != spannable.length()) : (ListenerUtil.mutListener.listen(9002) ? (index == spannable.length()) : (index < spannable.length()))))))) : (spannable != null && (ListenerUtil.mutListener.listen(9006) ? (index >= spannable.length()) : (ListenerUtil.mutListener.listen(9005) ? (index <= spannable.length()) : (ListenerUtil.mutListener.listen(9004) ? (index > spannable.length()) : (ListenerUtil.mutListener.listen(9003) ? (index != spannable.length()) : (ListenerUtil.mutListener.listen(9002) ? (index == spannable.length()) : (index < spannable.length())))))))) || spannable.charAt(index) == character) : ((ListenerUtil.mutListener.listen(9007) ? (spannable != null || (ListenerUtil.mutListener.listen(9006) ? (index >= spannable.length()) : (ListenerUtil.mutListener.listen(9005) ? (index <= spannable.length()) : (ListenerUtil.mutListener.listen(9004) ? (index > spannable.length()) : (ListenerUtil.mutListener.listen(9003) ? (index != spannable.length()) : (ListenerUtil.mutListener.listen(9002) ? (index == spannable.length()) : (index < spannable.length()))))))) : (spannable != null && (ListenerUtil.mutListener.listen(9006) ? (index >= spannable.length()) : (ListenerUtil.mutListener.listen(9005) ? (index <= spannable.length()) : (ListenerUtil.mutListener.listen(9004) ? (index > spannable.length()) : (ListenerUtil.mutListener.listen(9003) ? (index != spannable.length()) : (ListenerUtil.mutListener.listen(9002) ? (index == spannable.length()) : (index < spannable.length())))))))) && spannable.charAt(index) == character));
    }

    public static boolean validate2FAuthorizationTokenFromIntentExtras(Intent intent, TwoFactorAuthCallback callback) {
        if (!ListenerUtil.mutListener.listen(9028)) {
            // Check for push authorization request
            if ((ListenerUtil.mutListener.listen(9009) ? (intent != null || intent.hasExtra(NotificationsUtils.ARG_PUSH_AUTH_TOKEN)) : (intent != null && intent.hasExtra(NotificationsUtils.ARG_PUSH_AUTH_TOKEN)))) {
                Bundle extras = intent.getExtras();
                String token = extras.getString(NotificationsUtils.ARG_PUSH_AUTH_TOKEN, "");
                String title = extras.getString(NotificationsUtils.ARG_PUSH_AUTH_TITLE, "");
                String message = extras.getString(NotificationsUtils.ARG_PUSH_AUTH_MESSAGE, "");
                long expires = extras.getLong(NotificationsUtils.ARG_PUSH_AUTH_EXPIRES, 0);
                long now = (ListenerUtil.mutListener.listen(9013) ? (System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(9012) ? (System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(9011) ? (System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(9010) ? (System.currentTimeMillis() + 1000) : (System.currentTimeMillis() / 1000)))));
                if (!ListenerUtil.mutListener.listen(9027)) {
                    if ((ListenerUtil.mutListener.listen(9024) ? ((ListenerUtil.mutListener.listen(9018) ? (expires >= 0) : (ListenerUtil.mutListener.listen(9017) ? (expires <= 0) : (ListenerUtil.mutListener.listen(9016) ? (expires < 0) : (ListenerUtil.mutListener.listen(9015) ? (expires != 0) : (ListenerUtil.mutListener.listen(9014) ? (expires == 0) : (expires > 0)))))) || (ListenerUtil.mutListener.listen(9023) ? (now >= expires) : (ListenerUtil.mutListener.listen(9022) ? (now <= expires) : (ListenerUtil.mutListener.listen(9021) ? (now < expires) : (ListenerUtil.mutListener.listen(9020) ? (now != expires) : (ListenerUtil.mutListener.listen(9019) ? (now == expires) : (now > expires))))))) : ((ListenerUtil.mutListener.listen(9018) ? (expires >= 0) : (ListenerUtil.mutListener.listen(9017) ? (expires <= 0) : (ListenerUtil.mutListener.listen(9016) ? (expires < 0) : (ListenerUtil.mutListener.listen(9015) ? (expires != 0) : (ListenerUtil.mutListener.listen(9014) ? (expires == 0) : (expires > 0)))))) && (ListenerUtil.mutListener.listen(9023) ? (now >= expires) : (ListenerUtil.mutListener.listen(9022) ? (now <= expires) : (ListenerUtil.mutListener.listen(9021) ? (now < expires) : (ListenerUtil.mutListener.listen(9020) ? (now != expires) : (ListenerUtil.mutListener.listen(9019) ? (now == expires) : (now > expires))))))))) {
                        if (!ListenerUtil.mutListener.listen(9026)) {
                            callback.onTokenInvalid();
                        }
                        return false;
                    } else {
                        if (!ListenerUtil.mutListener.listen(9025)) {
                            callback.onTokenValid(token, title, message);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void showPushAuthAlert(Context context, final String token, String title, String message) {
        if (!ListenerUtil.mutListener.listen(9032)) {
            if ((ListenerUtil.mutListener.listen(9031) ? ((ListenerUtil.mutListener.listen(9030) ? ((ListenerUtil.mutListener.listen(9029) ? (context == null && TextUtils.isEmpty(token)) : (context == null || TextUtils.isEmpty(token))) && TextUtils.isEmpty(title)) : ((ListenerUtil.mutListener.listen(9029) ? (context == null && TextUtils.isEmpty(token)) : (context == null || TextUtils.isEmpty(token))) || TextUtils.isEmpty(title))) && TextUtils.isEmpty(message)) : ((ListenerUtil.mutListener.listen(9030) ? ((ListenerUtil.mutListener.listen(9029) ? (context == null && TextUtils.isEmpty(token)) : (context == null || TextUtils.isEmpty(token))) && TextUtils.isEmpty(title)) : ((ListenerUtil.mutListener.listen(9029) ? (context == null && TextUtils.isEmpty(token)) : (context == null || TextUtils.isEmpty(token))) || TextUtils.isEmpty(title))) || TextUtils.isEmpty(message)))) {
                return;
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(context);
        if (!ListenerUtil.mutListener.listen(9033)) {
            builder.setTitle(title).setMessage(message);
        }
        if (!ListenerUtil.mutListener.listen(9035)) {
            builder.setPositiveButton(R.string.mnu_comment_approve, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(9034)) {
                        sendTwoFactorAuthToken(token);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9037)) {
            builder.setNegativeButton(R.string.ignore, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(9036)) {
                        AnalyticsTracker.track(AnalyticsTracker.Stat.PUSH_AUTHENTICATION_IGNORED);
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(9038)) {
            dialog.show();
        }
    }

    public static void sendTwoFactorAuthToken(String token) {
        // ping the push auth endpoint with the token, wp.com will take care of the rest!
        Map<String, String> tokenMap = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9039)) {
            tokenMap.put("action", "authorize_login");
        }
        if (!ListenerUtil.mutListener.listen(9040)) {
            tokenMap.put("push_token", token);
        }
        if (!ListenerUtil.mutListener.listen(9042)) {
            WordPress.getRestClientUtilsV1_1().post(PUSH_AUTH_ENDPOINT, tokenMap, null, null, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(9041)) {
                        AnalyticsTracker.track(AnalyticsTracker.Stat.PUSH_AUTHENTICATION_FAILED);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9043)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.PUSH_AUTHENTICATION_APPROVED);
        }
    }

    /**
     * Checks if global notifications toggle is enabled in the Android app settings
     */
    public static boolean isNotificationsEnabled(Context context) {
        return NotificationManagerCompat.from(context.getApplicationContext()).areNotificationsEnabled();
    }

    public static boolean buildNoteObjectFromBundleAndSaveIt(Bundle data) {
        Note note = buildNoteObjectFromBundle(data);
        if (!ListenerUtil.mutListener.listen(9044)) {
            if (note != null) {
                return NotificationsTable.saveNote(note);
            }
        }
        return false;
    }

    public static Note buildNoteObjectFromBundle(Bundle data) {
        if (!ListenerUtil.mutListener.listen(9046)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(9045)) {
                    AppLog.e(T.NOTIFS, "Bundle is null! Cannot read '" + GCMMessageService.PUSH_ARG_NOTE_ID + "'.");
                }
                return null;
            }
        }
        Note note;
        String noteId = data.getString(GCMMessageService.PUSH_ARG_NOTE_ID, "");
        String base64FullData = data.getString(GCMMessageService.PUSH_ARG_NOTE_FULL_DATA);
        note = Note.buildFromBase64EncodedData(noteId, base64FullData);
        if (!ListenerUtil.mutListener.listen(9048)) {
            if (note == null) {
                if (!ListenerUtil.mutListener.listen(9047)) {
                    // At this point we don't have the note :(
                    AppLog.w(T.NOTIFS, "Cannot build the Note object by using info available in the PN payload. Please see " + "previous log messages for detailed information about the error.");
                }
            }
        }
        return note;
    }

    public static int findNoteInNoteArray(List<Note> notes, String noteIdToSearchFor) {
        if (!ListenerUtil.mutListener.listen(9050)) {
            if ((ListenerUtil.mutListener.listen(9049) ? (notes == null && TextUtils.isEmpty(noteIdToSearchFor)) : (notes == null || TextUtils.isEmpty(noteIdToSearchFor)))) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(9057)) {
            {
                long _loopCounter178 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(9056) ? (i >= notes.size()) : (ListenerUtil.mutListener.listen(9055) ? (i <= notes.size()) : (ListenerUtil.mutListener.listen(9054) ? (i > notes.size()) : (ListenerUtil.mutListener.listen(9053) ? (i != notes.size()) : (ListenerUtil.mutListener.listen(9052) ? (i == notes.size()) : (i < notes.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter178", ++_loopCounter178);
                    Note note = notes.get(i);
                    if (!ListenerUtil.mutListener.listen(9051)) {
                        if (noteIdToSearchFor.equals(note.getId())) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
