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
package ch.threema.app.utils;

import android.text.Spannable;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ViewUtil {

    /**
     *  show the view and return true if exist
     *  @param view
     *  @return
     */
    public static boolean show(View view) {
        return show(view, true);
    }

    /**
     *  show or hide the view and return true if exist
     *  @param view
     *  @return
     */
    public static boolean show(View view, boolean show) {
        if (!ListenerUtil.mutListener.listen(55851)) {
            if (view == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55852)) {
            view.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        return true;
    }

    public static boolean show(MenuItem menuItem, boolean show) {
        if (!ListenerUtil.mutListener.listen(55853)) {
            if (menuItem == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55854)) {
            menuItem.setVisible(show);
        }
        return true;
    }

    /**
     *  show the first view and hide the second, return true if the views exist
     *  @param viewToHide
     *  @param viewToShow
     *  @return
     */
    public static boolean showAndHide(View viewToShow, View viewToHide) {
        if (!ListenerUtil.mutListener.listen(55856)) {
            if ((ListenerUtil.mutListener.listen(55855) ? (viewToShow == null && viewToHide == null) : (viewToShow == null || viewToHide == null))) {
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(55857) ? (show(viewToShow, true) || show(viewToHide, false)) : (show(viewToShow, true) && show(viewToHide, false)));
    }

    public static boolean showAndSet(ImageView view, int imageResourceId) {
        if (!ListenerUtil.mutListener.listen(55858)) {
            if (!show(view)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55859)) {
            view.setImageResource(imageResourceId);
        }
        return true;
    }

    /**
     *  show a text view and set the text, return true if the view exist
     *  @param view
     *  @param text
     *  @return
     */
    public static boolean showAndSet(TextView view, String text) {
        if (!ListenerUtil.mutListener.listen(55860)) {
            if (!show(view)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55861)) {
            view.setText(text);
        }
        return true;
    }

    public static boolean showAndSet(TextView view, Spannable text) {
        if (!ListenerUtil.mutListener.listen(55862)) {
            if (!show(view)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55863)) {
            view.setText(text);
        }
        return true;
    }

    /**
     *  show a checkbox view and set the check state, return true if the view exist
     *  @param view
     *  @param checked
     *  @return
     */
    public static boolean showAndSet(CheckBox view, boolean checked) {
        if (!ListenerUtil.mutListener.listen(55864)) {
            if (!show(view)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55865)) {
            view.setChecked(checked);
        }
        return true;
    }
}
