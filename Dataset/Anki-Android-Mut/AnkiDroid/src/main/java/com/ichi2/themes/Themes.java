/**
 * ************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.themes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import androidx.core.content.ContextCompat;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Themes {

    // 100%
    public static final int ALPHA_ICON_ENABLED_LIGHT = 255;

    // 31%
    public static final int ALPHA_ICON_DISABLED_LIGHT = 76;

    // 54%
    public static final int ALPHA_ICON_ENABLED_DARK = 138;

    // Day themes
    public static final int THEME_DAY_LIGHT = 0;

    public static final int THEME_DAY_PLAIN = 1;

    // Night themes
    public static final int THEME_NIGHT_BLACK = 0;

    public static final int THEME_NIGHT_DARK = 1;

    public static void setTheme(Context context) {
        SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(context.getApplicationContext());
        if (!ListenerUtil.mutListener.listen(24874)) {
            if (prefs.getBoolean("invertedColors", false)) {
                int theme = Integer.parseInt(prefs.getString("nightTheme", "0"));
                if (!ListenerUtil.mutListener.listen(24873)) {
                    switch(theme) {
                        case THEME_NIGHT_DARK:
                            if (!ListenerUtil.mutListener.listen(24871)) {
                                context.setTheme(R.style.Theme_Dark_Compat);
                            }
                            break;
                        case THEME_NIGHT_BLACK:
                            if (!ListenerUtil.mutListener.listen(24872)) {
                                context.setTheme(R.style.Theme_Black_Compat);
                            }
                            break;
                    }
                }
            } else {
                int theme = Integer.parseInt(prefs.getString("dayTheme", "0"));
                if (!ListenerUtil.mutListener.listen(24870)) {
                    switch(theme) {
                        case THEME_DAY_LIGHT:
                            if (!ListenerUtil.mutListener.listen(24868)) {
                                context.setTheme(R.style.Theme_Light_Compat);
                            }
                            break;
                        case THEME_DAY_PLAIN:
                            if (!ListenerUtil.mutListener.listen(24869)) {
                                context.setTheme(R.style.Theme_Plain_Compat);
                            }
                            break;
                    }
                }
            }
        }
    }

    public static void setThemeLegacy(Context context) {
        SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(context.getApplicationContext());
        if (!ListenerUtil.mutListener.listen(24881)) {
            if (prefs.getBoolean("invertedColors", false)) {
                int theme = Integer.parseInt(prefs.getString("nightTheme", "0"));
                if (!ListenerUtil.mutListener.listen(24880)) {
                    switch(theme) {
                        case THEME_NIGHT_DARK:
                            if (!ListenerUtil.mutListener.listen(24878)) {
                                context.setTheme(R.style.LegacyActionBarDark);
                            }
                            break;
                        case THEME_NIGHT_BLACK:
                            if (!ListenerUtil.mutListener.listen(24879)) {
                                context.setTheme(R.style.LegacyActionBarBlack);
                            }
                            break;
                    }
                }
            } else {
                int theme = Integer.parseInt(prefs.getString("dayTheme", "0"));
                if (!ListenerUtil.mutListener.listen(24877)) {
                    switch(theme) {
                        case THEME_DAY_LIGHT:
                            if (!ListenerUtil.mutListener.listen(24875)) {
                                context.setTheme(R.style.LegacyActionBarLight);
                            }
                            break;
                        case THEME_DAY_PLAIN:
                            if (!ListenerUtil.mutListener.listen(24876)) {
                                context.setTheme(R.style.LegacyActionBarPlain);
                            }
                            break;
                    }
                }
            }
        }
    }

    public static int getResFromAttr(Context context, int resAttr) {
        int[] attrs = new int[] { resAttr };
        return getResFromAttr(context, attrs)[0];
    }

    public static int[] getResFromAttr(Context context, int[] attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs);
        if (!ListenerUtil.mutListener.listen(24888)) {
            {
                long _loopCounter667 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24887) ? (i >= attrs.length) : (ListenerUtil.mutListener.listen(24886) ? (i <= attrs.length) : (ListenerUtil.mutListener.listen(24885) ? (i > attrs.length) : (ListenerUtil.mutListener.listen(24884) ? (i != attrs.length) : (ListenerUtil.mutListener.listen(24883) ? (i == attrs.length) : (i < attrs.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter667", ++_loopCounter667);
                    if (!ListenerUtil.mutListener.listen(24882)) {
                        attrs[i] = ta.getResourceId(i, 0);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24889)) {
            ta.recycle();
        }
        return attrs;
    }

    public static int getColorFromAttr(Context context, int colorAttr) {
        int[] attrs = new int[] { colorAttr };
        return getColorFromAttr(context, attrs)[0];
    }

    public static int[] getColorFromAttr(Context context, int[] attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs);
        if (!ListenerUtil.mutListener.listen(24896)) {
            {
                long _loopCounter668 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24895) ? (i >= attrs.length) : (ListenerUtil.mutListener.listen(24894) ? (i <= attrs.length) : (ListenerUtil.mutListener.listen(24893) ? (i > attrs.length) : (ListenerUtil.mutListener.listen(24892) ? (i != attrs.length) : (ListenerUtil.mutListener.listen(24891) ? (i == attrs.length) : (i < attrs.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter668", ++_loopCounter668);
                    if (!ListenerUtil.mutListener.listen(24890)) {
                        attrs[i] = ta.getColor(i, ContextCompat.getColor(context, R.color.white));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24897)) {
            ta.recycle();
        }
        return attrs;
    }

    /**
     * Return the current integer code of the theme being used, taking into account
     * whether we are in day mode or night mode.
     */
    public static int getCurrentTheme(Context context) {
        SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(context);
        if (prefs.getBoolean("invertedColors", false)) {
            return Integer.parseInt(prefs.getString("nightTheme", "0"));
        } else {
            return Integer.parseInt(prefs.getString("dayTheme", "0"));
        }
    }
}
