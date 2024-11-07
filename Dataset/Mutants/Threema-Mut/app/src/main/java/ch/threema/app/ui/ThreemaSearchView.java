/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import androidx.appcompat.widget.SearchView;
import android.util.AttributeSet;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSearchView extends SearchView {

    public ThreemaSearchView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47497)) {
            init(context);
        }
    }

    public ThreemaSearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(47498)) {
            init(context);
        }
    }

    public ThreemaSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(47499)) {
            init(context);
        }
    }

    private void init(Context context) {
        // PreferenceService may not yet be available at this time
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(47502)) {
            if ((ListenerUtil.mutListener.listen(47500) ? (sharedPreferences != null || sharedPreferences.getBoolean(getResources().getString(R.string.preferences__incognito_keyboard), false)) : (sharedPreferences != null && sharedPreferences.getBoolean(getResources().getString(R.string.preferences__incognito_keyboard), false)))) {
                if (!ListenerUtil.mutListener.listen(47501)) {
                    setImeOptions(getImeOptions() | 0x1000000);
                }
            }
        }
    }
}
