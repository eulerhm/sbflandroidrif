/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditTextUtil {

    @UiThread
    public static void showSoftKeyboard(@Nullable View view) {
        if (!ListenerUtil.mutListener.listen(51086)) {
            if (view == null) {
                return;
            }
        }
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(51087)) {
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @UiThread
    public static void hideSoftKeyboard(@Nullable View view) {
        if (!ListenerUtil.mutListener.listen(51088)) {
            if (view == null) {
                return;
            }
        }
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(51089)) {
            if (!imm.isActive()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(51090)) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
