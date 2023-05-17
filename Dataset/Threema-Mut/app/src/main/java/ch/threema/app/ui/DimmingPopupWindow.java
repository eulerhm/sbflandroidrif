/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class DimmingPopupWindow extends PopupWindow {

    private Context context;

    DimmingPopupWindow(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(44993)) {
            this.context = context;
        }
    }

    void dimBackground() {
        View container;
        if ((ListenerUtil.mutListener.listen(44998) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44997) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44996) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44995) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(44994) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
            container = (View) getContentView().getParent().getParent();
        } else {
            container = (View) getContentView().getParent();
        }
        if (!ListenerUtil.mutListener.listen(45004)) {
            if (container != null) {
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(45003)) {
                    if ((ListenerUtil.mutListener.listen(44999) ? (p != null || wm != null) : (p != null && wm != null))) {
                        if (!ListenerUtil.mutListener.listen(45000)) {
                            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        }
                        if (!ListenerUtil.mutListener.listen(45001)) {
                            p.dimAmount = 0.6f;
                        }
                        if (!ListenerUtil.mutListener.listen(45002)) {
                            wm.updateViewLayout(container, p);
                        }
                    }
                }
            }
        }
    }

    protected Context getContext() {
        return this.context;
    }
}
