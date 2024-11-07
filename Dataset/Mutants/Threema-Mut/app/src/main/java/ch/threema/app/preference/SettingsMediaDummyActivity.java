/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.preference;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import androidx.appcompat.app.AppCompatActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Frontend to call the app's media settings directly from notification or system settings
public class SettingsMediaDummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32411)) {
            super.onCreate(savedInstanceState);
        }
        Intent intent = new Intent(this, SettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(32412)) {
            intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsMediaFragment.class.getName());
        }
        if (!ListenerUtil.mutListener.listen(32413)) {
            intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
        }
        if (!ListenerUtil.mutListener.listen(32414)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(32415)) {
            finish();
        }
    }
}
