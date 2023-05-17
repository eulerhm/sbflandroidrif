/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ichi2.utils.ViewGroupUtils;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// java.lang.ClassCastException: com.ichi2.ui.AutoSizeCheckBoxPreference cannot be cast to android.preference.Preference
// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public class AutoSizeCheckBoxPreference extends android.preference.CheckBoxPreference {

    @SuppressWarnings("unused")
    public AutoSizeCheckBoxPreference(Context context) {
        super(context);
    }

    @SuppressWarnings("unused")
    public AutoSizeCheckBoxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressWarnings("unused")
    public AutoSizeCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressWarnings("unused")
    public AutoSizeCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onBindView(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(24965)) {
            makeMultiline(view);
        }
        if (!ListenerUtil.mutListener.listen(24966)) {
            super.onBindView(view);
        }
    }

    protected void makeMultiline(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(24971)) {
            // https://stackoverflow.com/q/4267939/13121290
            if (view instanceof ViewGroup) {
                if (!ListenerUtil.mutListener.listen(24970)) {
                    {
                        long _loopCounter669 = 0;
                        for (View child : ViewGroupUtils.getAllChildren((ViewGroup) view)) {
                            ListenerUtil.loopListener.listen("_loopCounter669", ++_loopCounter669);
                            if (!ListenerUtil.mutListener.listen(24969)) {
                                makeMultiline(child);
                            }
                        }
                    }
                }
            } else if (view instanceof TextView) {
                TextView t = (TextView) view;
                if (!ListenerUtil.mutListener.listen(24967)) {
                    t.setSingleLine(false);
                }
                if (!ListenerUtil.mutListener.listen(24968)) {
                    t.setEllipsize(null);
                }
            }
        }
    }
}
