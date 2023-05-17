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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmptyView extends LinearLayout {

    private TextView emptyText;

    public EmptyView(Context context) {
        this(context, null, 0);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, int parentOffset) {
        this(context, null, parentOffset);
    }

    public EmptyView(Context context, AttributeSet attrs, int parentOffset) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(45104)) {
            setOrientation(LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(45105)) {
            setGravity(Gravity.CENTER);
        }
        int paddingPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());
        if (!ListenerUtil.mutListener.listen(45106)) {
            setPadding(paddingPx, parentOffset, paddingPx, 0);
        }
        if (!ListenerUtil.mutListener.listen(45107)) {
            setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if (!ListenerUtil.mutListener.listen(45108)) {
            LayoutInflater.from(context).inflate(R.layout.view_empty, this, true);
        }
        if (!ListenerUtil.mutListener.listen(45109)) {
            setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(45110)) {
            this.emptyText = (TextView) getChildAt(0);
        }
    }

    public void setup(int label) {
        if (!ListenerUtil.mutListener.listen(45111)) {
            this.emptyText.setText(label);
        }
    }

    public void setup(String label) {
        if (!ListenerUtil.mutListener.listen(45112)) {
            this.emptyText.setText(label);
        }
    }

    public void setColors(@ColorRes int background, @ColorRes int foreground) {
        if (!ListenerUtil.mutListener.listen(45113)) {
            this.setBackgroundColor(getResources().getColor(background));
        }
        if (!ListenerUtil.mutListener.listen(45114)) {
            this.emptyText.setTextColor(getResources().getColor(foreground));
        }
    }

    public void setColorsInt(@ColorInt int background, @ColorInt int foreground) {
        if (!ListenerUtil.mutListener.listen(45115)) {
            this.setBackgroundColor(background);
        }
        if (!ListenerUtil.mutListener.listen(45116)) {
            this.emptyText.setTextColor(foreground);
        }
    }
}
