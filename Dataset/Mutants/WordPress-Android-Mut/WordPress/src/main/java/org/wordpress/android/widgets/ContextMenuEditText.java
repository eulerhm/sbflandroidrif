package org.wordpress.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * EditText which notifies when text is cut, copied, or pasted.
 */
public class ContextMenuEditText extends AppCompatEditText {

    public interface OnContextMenuListener {

        void onCut();

        void onCopy();

        void onPaste();
    }

    private OnContextMenuListener mOnContextMenuListener;

    /**
     * Set a listener to interface with activity or fragment.
     *
     * @param listener object listening for cut, copy, and paste events
     */
    public void setOnContextMenuListener(OnContextMenuListener listener) {
        if (!ListenerUtil.mutListener.listen(28415)) {
            mOnContextMenuListener = listener;
        }
    }

    public ContextMenuEditText(Context context) {
        super(context);
    }

    public ContextMenuEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContextMenuEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * ContextMenu used to cut, copy, or paste which overwrites the consuming method.
     */
    @Override
    public boolean onTextContextMenuItem(int id) {
        boolean consumed = super.onTextContextMenuItem(id);
        if (!ListenerUtil.mutListener.listen(28419)) {
            switch(id) {
                case android.R.id.cut:
                    if (!ListenerUtil.mutListener.listen(28416)) {
                        onCut();
                    }
                    break;
                case android.R.id.copy:
                    if (!ListenerUtil.mutListener.listen(28417)) {
                        onCopy();
                    }
                    break;
                case android.R.id.paste:
                    if (!ListenerUtil.mutListener.listen(28418)) {
                        onPaste();
                    }
                    break;
            }
        }
        return consumed;
    }

    /**
     * Text cut from EditText.
     */
    public void onCut() {
        if (!ListenerUtil.mutListener.listen(28421)) {
            if (mOnContextMenuListener != null) {
                if (!ListenerUtil.mutListener.listen(28420)) {
                    mOnContextMenuListener.onCut();
                }
            }
        }
    }

    /**
     * Text copied from EditText.
     */
    public void onCopy() {
        if (!ListenerUtil.mutListener.listen(28423)) {
            if (mOnContextMenuListener != null) {
                if (!ListenerUtil.mutListener.listen(28422)) {
                    mOnContextMenuListener.onCopy();
                }
            }
        }
    }

    /**
     * Text pasted into EditText.
     */
    public void onPaste() {
        if (!ListenerUtil.mutListener.listen(28425)) {
            if (mOnContextMenuListener != null) {
                if (!ListenerUtil.mutListener.listen(28424)) {
                    mOnContextMenuListener.onPaste();
                }
            }
        }
    }
}
