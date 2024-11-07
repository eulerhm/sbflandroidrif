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

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.widget.Toast;
import androidx.annotation.UiThread;
import ch.threema.app.ThreemaApplication;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SingleToast {

    private Toast toast = null;

    private static SingleToast sInstance = null;

    public static synchronized SingleToast getInstance() {
        if (!ListenerUtil.mutListener.listen(47266)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(47265)) {
                    sInstance = new SingleToast();
                }
            }
        }
        return sInstance;
    }

    private SingleToast() {
    }

    public SingleToast text(String text, int length) {
        return this.text(text, length, Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @SuppressLint("ShowToast")
    public synchronized SingleToast text(String text, int length, int gravity, int x, int y) {
        if (!ListenerUtil.mutListener.listen(47293)) {
            if (this.toast == null) {
                if (!ListenerUtil.mutListener.listen(47273)) {
                    this.toast = Toast.makeText(ThreemaApplication.getAppContext(), text, length);
                }
                if (!ListenerUtil.mutListener.listen(47292)) {
                    if ((ListenerUtil.mutListener.listen(47290) ? ((ListenerUtil.mutListener.listen(47284) ? ((ListenerUtil.mutListener.listen(47278) ? (gravity >= 0) : (ListenerUtil.mutListener.listen(47277) ? (gravity <= 0) : (ListenerUtil.mutListener.listen(47276) ? (gravity > 0) : (ListenerUtil.mutListener.listen(47275) ? (gravity < 0) : (ListenerUtil.mutListener.listen(47274) ? (gravity == 0) : (gravity != 0)))))) && (ListenerUtil.mutListener.listen(47283) ? (x >= 0) : (ListenerUtil.mutListener.listen(47282) ? (x <= 0) : (ListenerUtil.mutListener.listen(47281) ? (x > 0) : (ListenerUtil.mutListener.listen(47280) ? (x < 0) : (ListenerUtil.mutListener.listen(47279) ? (x == 0) : (x != 0))))))) : ((ListenerUtil.mutListener.listen(47278) ? (gravity >= 0) : (ListenerUtil.mutListener.listen(47277) ? (gravity <= 0) : (ListenerUtil.mutListener.listen(47276) ? (gravity > 0) : (ListenerUtil.mutListener.listen(47275) ? (gravity < 0) : (ListenerUtil.mutListener.listen(47274) ? (gravity == 0) : (gravity != 0)))))) || (ListenerUtil.mutListener.listen(47283) ? (x >= 0) : (ListenerUtil.mutListener.listen(47282) ? (x <= 0) : (ListenerUtil.mutListener.listen(47281) ? (x > 0) : (ListenerUtil.mutListener.listen(47280) ? (x < 0) : (ListenerUtil.mutListener.listen(47279) ? (x == 0) : (x != 0)))))))) && (ListenerUtil.mutListener.listen(47289) ? (y >= 0) : (ListenerUtil.mutListener.listen(47288) ? (y <= 0) : (ListenerUtil.mutListener.listen(47287) ? (y > 0) : (ListenerUtil.mutListener.listen(47286) ? (y < 0) : (ListenerUtil.mutListener.listen(47285) ? (y == 0) : (y != 0))))))) : ((ListenerUtil.mutListener.listen(47284) ? ((ListenerUtil.mutListener.listen(47278) ? (gravity >= 0) : (ListenerUtil.mutListener.listen(47277) ? (gravity <= 0) : (ListenerUtil.mutListener.listen(47276) ? (gravity > 0) : (ListenerUtil.mutListener.listen(47275) ? (gravity < 0) : (ListenerUtil.mutListener.listen(47274) ? (gravity == 0) : (gravity != 0)))))) && (ListenerUtil.mutListener.listen(47283) ? (x >= 0) : (ListenerUtil.mutListener.listen(47282) ? (x <= 0) : (ListenerUtil.mutListener.listen(47281) ? (x > 0) : (ListenerUtil.mutListener.listen(47280) ? (x < 0) : (ListenerUtil.mutListener.listen(47279) ? (x == 0) : (x != 0))))))) : ((ListenerUtil.mutListener.listen(47278) ? (gravity >= 0) : (ListenerUtil.mutListener.listen(47277) ? (gravity <= 0) : (ListenerUtil.mutListener.listen(47276) ? (gravity > 0) : (ListenerUtil.mutListener.listen(47275) ? (gravity < 0) : (ListenerUtil.mutListener.listen(47274) ? (gravity == 0) : (gravity != 0)))))) || (ListenerUtil.mutListener.listen(47283) ? (x >= 0) : (ListenerUtil.mutListener.listen(47282) ? (x <= 0) : (ListenerUtil.mutListener.listen(47281) ? (x > 0) : (ListenerUtil.mutListener.listen(47280) ? (x < 0) : (ListenerUtil.mutListener.listen(47279) ? (x == 0) : (x != 0)))))))) || (ListenerUtil.mutListener.listen(47289) ? (y >= 0) : (ListenerUtil.mutListener.listen(47288) ? (y <= 0) : (ListenerUtil.mutListener.listen(47287) ? (y > 0) : (ListenerUtil.mutListener.listen(47286) ? (y < 0) : (ListenerUtil.mutListener.listen(47285) ? (y == 0) : (y != 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(47291)) {
                            this.toast.setGravity(gravity, x, y);
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(47268) ? ((ListenerUtil.mutListener.listen(47267) ? (this.toast.getGravity() != gravity && this.toast.getXOffset() != x) : (this.toast.getGravity() != gravity || this.toast.getXOffset() != x)) && this.toast.getYOffset() != y) : ((ListenerUtil.mutListener.listen(47267) ? (this.toast.getGravity() != gravity && this.toast.getXOffset() != x) : (this.toast.getGravity() != gravity || this.toast.getXOffset() != x)) || this.toast.getYOffset() != y))) {
                if (!ListenerUtil.mutListener.listen(47270)) {
                    // close toast to reset gravity
                    this.toast.cancel();
                }
                if (!ListenerUtil.mutListener.listen(47271)) {
                    this.toast = null;
                }
                if (!ListenerUtil.mutListener.listen(47272)) {
                    this.text(text, length, gravity, x, y);
                }
                return this;
            } else {
                if (!ListenerUtil.mutListener.listen(47269)) {
                    this.toast.setText(text);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47294)) {
            this.toast.show();
        }
        return this;
    }

    public SingleToast close() {
        if (!ListenerUtil.mutListener.listen(47297)) {
            if (this.toast != null) {
                if (!ListenerUtil.mutListener.listen(47295)) {
                    this.toast.cancel();
                }
                if (!ListenerUtil.mutListener.listen(47296)) {
                    this.toast = null;
                }
            }
        }
        return this;
    }

    @UiThread
    public void showShortText(String text) {
        if (!ListenerUtil.mutListener.listen(47298)) {
            showText(text, Toast.LENGTH_SHORT);
        }
    }

    @UiThread
    public void showLongText(String text) {
        if (!ListenerUtil.mutListener.listen(47299)) {
            showText(text, Toast.LENGTH_LONG);
        }
    }

    @UiThread
    private void showText(String text, int length) {
        if (!ListenerUtil.mutListener.listen(47300)) {
            text(text, length);
        }
    }

    @UiThread
    public void showBottom(String text, int length) {
        if (!ListenerUtil.mutListener.listen(47301)) {
            text(text, length, 0, 0, 0);
        }
    }
}
