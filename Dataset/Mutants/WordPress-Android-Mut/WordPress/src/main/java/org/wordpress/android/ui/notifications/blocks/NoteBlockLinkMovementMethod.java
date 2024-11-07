package org.wordpress.android.ui.notifications.blocks;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Allows links to be highlighted when tapped on note blocks.
 * See: http://stackoverflow.com/a/20905824/309558
 */
public class NoteBlockLinkMovementMethod extends LinkMovementMethod {

    private NoteBlockClickableSpan mPressedSpan;

    @Override
    public boolean onTouchEvent(@NonNull TextView textView, @NonNull Spannable spannable, @NonNull MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(8551)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (!ListenerUtil.mutListener.listen(8547)) {
                    mPressedSpan = getPressedSpan(textView, spannable, event);
                }
                if (!ListenerUtil.mutListener.listen(8550)) {
                    if (mPressedSpan != null) {
                        if (!ListenerUtil.mutListener.listen(8548)) {
                            mPressedSpan.setPressed(true);
                        }
                        if (!ListenerUtil.mutListener.listen(8549)) {
                            Selection.setSelection(spannable, spannable.getSpanStart(mPressedSpan), spannable.getSpanEnd(mPressedSpan));
                        }
                    }
                }
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                NoteBlockClickableSpan touchedSpan = getPressedSpan(textView, spannable, event);
                if (!ListenerUtil.mutListener.listen(8546)) {
                    if ((ListenerUtil.mutListener.listen(8542) ? (mPressedSpan != null || touchedSpan != mPressedSpan) : (mPressedSpan != null && touchedSpan != mPressedSpan))) {
                        if (!ListenerUtil.mutListener.listen(8543)) {
                            mPressedSpan.setPressed(false);
                        }
                        if (!ListenerUtil.mutListener.listen(8544)) {
                            mPressedSpan = null;
                        }
                        if (!ListenerUtil.mutListener.listen(8545)) {
                            Selection.removeSelection(spannable);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8539)) {
                    if (mPressedSpan != null) {
                        if (!ListenerUtil.mutListener.listen(8537)) {
                            mPressedSpan.setPressed(false);
                        }
                        if (!ListenerUtil.mutListener.listen(8538)) {
                            super.onTouchEvent(textView, spannable, event);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8540)) {
                    mPressedSpan = null;
                }
                if (!ListenerUtil.mutListener.listen(8541)) {
                    Selection.removeSelection(spannable);
                }
            }
        }
        return true;
    }

    private NoteBlockClickableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (!ListenerUtil.mutListener.listen(8552)) {
            x -= textView.getTotalPaddingLeft();
        }
        if (!ListenerUtil.mutListener.listen(8553)) {
            y -= textView.getTotalPaddingTop();
        }
        if (!ListenerUtil.mutListener.listen(8554)) {
            x += textView.getScrollX();
        }
        if (!ListenerUtil.mutListener.listen(8555)) {
            y += textView.getScrollY();
        }
        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);
        NoteBlockClickableSpan[] link = spannable.getSpans(off, off, NoteBlockClickableSpan.class);
        NoteBlockClickableSpan touchedSpan = null;
        if (!ListenerUtil.mutListener.listen(8562)) {
            if ((ListenerUtil.mutListener.listen(8560) ? (link.length >= 0) : (ListenerUtil.mutListener.listen(8559) ? (link.length <= 0) : (ListenerUtil.mutListener.listen(8558) ? (link.length < 0) : (ListenerUtil.mutListener.listen(8557) ? (link.length != 0) : (ListenerUtil.mutListener.listen(8556) ? (link.length == 0) : (link.length > 0))))))) {
                if (!ListenerUtil.mutListener.listen(8561)) {
                    touchedSpan = link[0];
                }
            }
        }
        return touchedSpan;
    }
}
