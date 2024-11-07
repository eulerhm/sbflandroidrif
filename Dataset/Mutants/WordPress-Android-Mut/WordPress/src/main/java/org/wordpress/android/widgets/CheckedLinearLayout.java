package org.wordpress.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CheckedLinearLayout extends LinearLayout implements Checkable {

    private CheckedTextView mCheckbox;

    public CheckedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(28402)) {
            super.onFinishInflate();
        }
        int childCount = getChildCount();
        if (!ListenerUtil.mutListener.listen(28410)) {
            {
                long _loopCounter428 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(28409) ? (i >= childCount) : (ListenerUtil.mutListener.listen(28408) ? (i <= childCount) : (ListenerUtil.mutListener.listen(28407) ? (i > childCount) : (ListenerUtil.mutListener.listen(28406) ? (i != childCount) : (ListenerUtil.mutListener.listen(28405) ? (i == childCount) : (i < childCount)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter428", ++_loopCounter428);
                    View v = getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(28404)) {
                        if (v instanceof CheckedTextView) {
                            if (!ListenerUtil.mutListener.listen(28403)) {
                                mCheckbox = (CheckedTextView) v;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mCheckbox != null ? mCheckbox.isChecked() : false;
    }

    @Override
    public void setChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(28412)) {
            if (mCheckbox != null) {
                if (!ListenerUtil.mutListener.listen(28411)) {
                    mCheckbox.setChecked(checked);
                }
            }
        }
    }

    @Override
    public void toggle() {
        if (!ListenerUtil.mutListener.listen(28414)) {
            if (mCheckbox != null) {
                if (!ListenerUtil.mutListener.listen(28413)) {
                    mCheckbox.toggle();
                }
            }
        }
    }
}
