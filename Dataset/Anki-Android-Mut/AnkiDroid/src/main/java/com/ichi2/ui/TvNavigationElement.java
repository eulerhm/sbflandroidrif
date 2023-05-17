/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.ichi2.anki.NavigationDrawerActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Hack to allow the navigation and options menu to appear when on a TV
 *  This is a view to handle dispatchUnhandledMove without using onKeyUp/Down
 *  (which interferes with other view events)
 */
public class TvNavigationElement extends View {

    public TvNavigationElement(Context context) {
        super(context);
    }

    public TvNavigationElement(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TvNavigationElement(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressWarnings({ "unused", "RedundantSuppression" })
    public TvNavigationElement(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        if (!ListenerUtil.mutListener.listen(25507)) {
            Timber.d("onFocusChanged %d", direction);
        }
        if (!ListenerUtil.mutListener.listen(25508)) {
            super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        }
    }

    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        if (!ListenerUtil.mutListener.listen(25509)) {
            Timber.d("dispatchUnhandledMove %d", direction);
        }
        AppCompatActivity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(25510)) {
            if (activity == null) {
                return super.dispatchUnhandledMove(focused, direction);
            }
        }
        if (!ListenerUtil.mutListener.listen(25514)) {
            if ((ListenerUtil.mutListener.listen(25511) ? (direction == FOCUS_LEFT || activity instanceof NavigationDrawerActivity) : (direction == FOCUS_LEFT && activity instanceof NavigationDrawerActivity))) {
                // COULD_BE_BETTER: This leaves focus on the top item when navigation occurs.
                NavigationDrawerActivity navigationDrawerActivity = (NavigationDrawerActivity) activity;
                if (!ListenerUtil.mutListener.listen(25512)) {
                    navigationDrawerActivity.toggleDrawer();
                }
                if (!ListenerUtil.mutListener.listen(25513)) {
                    navigationDrawerActivity.focusNavigation();
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(25517)) {
            if (direction == FOCUS_RIGHT) {
                if (!ListenerUtil.mutListener.listen(25515)) {
                    Timber.d("Opening options menu");
                }
                if (!ListenerUtil.mutListener.listen(25516)) {
                    // COULD_BE_BETTER: This crashes inside the framework if right is pressed on the
                    openOptionsMenu(activity);
                }
                return true;
            }
        }
        return super.dispatchUnhandledMove(focused, direction);
    }

    @SuppressLint("RestrictedApi")
    private void openOptionsMenu(AppCompatActivity activity) {
        // This occasionally glitches graphically on my emulator
        ActionBar supportActionBar = activity.getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(25519)) {
            if (supportActionBar != null) {
                if (!ListenerUtil.mutListener.listen(25518)) {
                    supportActionBar.openOptionsMenu();
                }
            }
        }
    }

    private AppCompatActivity getActivity() {
        Context context = getContext();
        if (!ListenerUtil.mutListener.listen(25522)) {
            {
                long _loopCounter673 = 0;
                while (context instanceof ContextWrapper) {
                    ListenerUtil.loopListener.listen("_loopCounter673", ++_loopCounter673);
                    if (!ListenerUtil.mutListener.listen(25520)) {
                        if (context instanceof AppCompatActivity) {
                            return (AppCompatActivity) context;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25521)) {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
            }
        }
        return null;
    }
}
