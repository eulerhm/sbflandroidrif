package org.wordpress.android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.TextView;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPLinkMovementMethod extends LinkMovementMethod {

    protected static WPLinkMovementMethod mMovementMethod;

    public static WPLinkMovementMethod getInstance() {
        if (!ListenerUtil.mutListener.listen(28024)) {
            if (mMovementMethod == null) {
                if (!ListenerUtil.mutListener.listen(28023)) {
                    mMovementMethod = new WPLinkMovementMethod();
                }
            }
        }
        return mMovementMethod;
    }

    @Override
    public boolean onTouchEvent(TextView textView, Spannable buffer, MotionEvent event) {
        try {
            return super.onTouchEvent(textView, buffer, event);
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(28025)) {
                AppLog.e(AppLog.T.UTILS, e);
            }
            if (!ListenerUtil.mutListener.listen(28026)) {
                // attempt to correct the tapped url then launch the intent to display it
                showTappedUrl(textView.getContext(), fixTappedUrl(buffer));
            }
            return true;
        }
    }

    private static String fixTappedUrl(Spannable buffer) {
        if (!ListenerUtil.mutListener.listen(28027)) {
            if (buffer == null) {
                return null;
            }
        }
        URLSpan[] urlSpans = buffer.getSpans(0, buffer.length(), URLSpan.class);
        if (!ListenerUtil.mutListener.listen(28028)) {
            if (urlSpans.length == 0) {
                return null;
            }
        }
        // note that there will be only one URLSpan (the one that was tapped)
        String url = StringUtils.notNullStr(urlSpans[0].getURL());
        if (!ListenerUtil.mutListener.listen(28029)) {
            if (Uri.parse(url).getScheme() == null) {
                return "http://" + url.trim();
            }
        }
        return url.trim();
    }

    private static void showTappedUrl(Context context, String url) {
        if (!ListenerUtil.mutListener.listen(28031)) {
            if ((ListenerUtil.mutListener.listen(28030) ? (context == null && TextUtils.isEmpty(url)) : (context == null || TextUtils.isEmpty(url)))) {
                return;
            }
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (!ListenerUtil.mutListener.listen(28033)) {
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            String readerToastUrlErrorIntent = context.getString(R.string.reader_toast_err_url_intent);
            if (!ListenerUtil.mutListener.listen(28032)) {
                ToastUtils.showToast(context, String.format(readerToastUrlErrorIntent, url), ToastUtils.Duration.LONG);
            }
        }
    }
}
