/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CheckableRelativeLayout extends RelativeLayout implements Checkable {

    private boolean checked = false;

    private CheckableRelativeLayout.OnCheckedChangeListener onCheckedChangeListener;

    private static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };

    public interface OnCheckedChangeListener {

        /**
         *  Called when the checked state of the checkable has changed.
         *
         *  @param checkableView The view whose state has changed.
         *  @param isChecked     The new checked state of checkableView.
         */
        void onCheckedChanged(CheckableRelativeLayout checkableView, boolean isChecked);
    }

    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState((ListenerUtil.mutListener.listen(44753) ? (extraSpace % 1) : (ListenerUtil.mutListener.listen(44752) ? (extraSpace / 1) : (ListenerUtil.mutListener.listen(44751) ? (extraSpace * 1) : (ListenerUtil.mutListener.listen(44750) ? (extraSpace - 1) : (extraSpace + 1))))));
        if (!ListenerUtil.mutListener.listen(44755)) {
            if (isChecked())
                if (!ListenerUtil.mutListener.listen(44754)) {
                    mergeDrawableStates(drawableState, CHECKED_STATE_SET);
                }
        }
        return drawableState;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean _checked) {
        if (!ListenerUtil.mutListener.listen(44756)) {
            checked = _checked;
        }
        if (!ListenerUtil.mutListener.listen(44757)) {
            refreshDrawableState();
        }
        if (!ListenerUtil.mutListener.listen(44759)) {
            if (onCheckedChangeListener != null) {
                if (!ListenerUtil.mutListener.listen(44758)) {
                    onCheckedChangeListener.onCheckedChanged(this, checked);
                }
            }
        }
    }

    @Override
    public void toggle() {
        if (!ListenerUtil.mutListener.listen(44760)) {
            setChecked(!checked);
        }
    }

    /**
     *  Register a callback to be invoked when the checked state of this view changes.
     *
     *  @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(CheckableRelativeLayout.OnCheckedChangeListener listener) {
        if (!ListenerUtil.mutListener.listen(44761)) {
            onCheckedChangeListener = listener;
        }
    }
}
