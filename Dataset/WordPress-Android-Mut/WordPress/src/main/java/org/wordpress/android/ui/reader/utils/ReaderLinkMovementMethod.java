package org.wordpress.android.ui.reader.utils;

import android.content.ActivityNotFoundException;
import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderActivityLauncher.PhotoViewerOption;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.StringUtils;
import java.util.EnumSet;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * custom LinkMovementMethod which shows photo viewer when an image span is tapped
 */
public class ReaderLinkMovementMethod extends LinkMovementMethod {

    private static ReaderLinkMovementMethod mMovementMethod;

    private static ReaderLinkMovementMethod mMovementMethodPrivate;

    private final boolean mIsPrivate;

    /*
     * note that separate instances are returned depending on whether we're showing
     * content from a private blog
     */
    public static ReaderLinkMovementMethod getInstance(boolean isPrivate) {
        if (isPrivate) {
            if (!ListenerUtil.mutListener.listen(19743)) {
                if (mMovementMethodPrivate == null) {
                    if (!ListenerUtil.mutListener.listen(19742)) {
                        mMovementMethodPrivate = new ReaderLinkMovementMethod(true);
                    }
                }
            }
            return mMovementMethodPrivate;
        } else {
            if (!ListenerUtil.mutListener.listen(19741)) {
                if (mMovementMethod == null) {
                    if (!ListenerUtil.mutListener.listen(19740)) {
                        mMovementMethod = new ReaderLinkMovementMethod(false);
                    }
                }
            }
            return mMovementMethod;
        }
    }

    /*
     * override MovementMethod.getInstance() to ensure our getInstance(false) is used
     */
    @SuppressWarnings("unused")
    public static ReaderLinkMovementMethod getInstance() {
        return getInstance(false);
    }

    private ReaderLinkMovementMethod(boolean isPrivate) {
        super();
        mIsPrivate = isPrivate;
    }

    @Override
    public boolean onTouchEvent(@NonNull TextView textView, @NonNull Spannable buffer, @NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (!ListenerUtil.mutListener.listen(19744)) {
                x -= textView.getTotalPaddingLeft();
            }
            if (!ListenerUtil.mutListener.listen(19745)) {
                y -= textView.getTotalPaddingTop();
            }
            if (!ListenerUtil.mutListener.listen(19746)) {
                x += textView.getScrollX();
            }
            if (!ListenerUtil.mutListener.listen(19747)) {
                y += textView.getScrollY();
            }
            Layout layout = textView.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);
            ImageSpan[] images = buffer.getSpans(off, off, ImageSpan.class);
            if ((ListenerUtil.mutListener.listen(19753) ? (images != null || (ListenerUtil.mutListener.listen(19752) ? (images.length >= 0) : (ListenerUtil.mutListener.listen(19751) ? (images.length <= 0) : (ListenerUtil.mutListener.listen(19750) ? (images.length < 0) : (ListenerUtil.mutListener.listen(19749) ? (images.length != 0) : (ListenerUtil.mutListener.listen(19748) ? (images.length == 0) : (images.length > 0))))))) : (images != null && (ListenerUtil.mutListener.listen(19752) ? (images.length >= 0) : (ListenerUtil.mutListener.listen(19751) ? (images.length <= 0) : (ListenerUtil.mutListener.listen(19750) ? (images.length < 0) : (ListenerUtil.mutListener.listen(19749) ? (images.length != 0) : (ListenerUtil.mutListener.listen(19748) ? (images.length == 0) : (images.length > 0))))))))) {
                EnumSet<PhotoViewerOption> options = EnumSet.noneOf(PhotoViewerOption.class);
                if (!ListenerUtil.mutListener.listen(19755)) {
                    if (mIsPrivate) {
                        if (!ListenerUtil.mutListener.listen(19754)) {
                            options.add(ReaderActivityLauncher.PhotoViewerOption.IS_PRIVATE_IMAGE);
                        }
                    }
                }
                String imageUrl = StringUtils.notNullStr(images[0].getSource());
                if (!ListenerUtil.mutListener.listen(19756)) {
                    ReaderActivityLauncher.showReaderPhotoViewer(textView.getContext(), imageUrl, null, textView, options, (int) event.getX(), (int) event.getY());
                }
                return true;
            }
        }
        try {
            return super.onTouchEvent(textView, buffer, event);
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(19757)) {
                AppLog.e(AppLog.T.UTILS, e);
            }
            return false;
        }
    }
}
