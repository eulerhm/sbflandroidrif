/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.preference.SettingsActivity;
import ch.threema.app.utils.AnimationUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AboutActivity extends ThreemaToolbarActivity {

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1647)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(1650)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(1648)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(1649)) {
                    actionBar.setTitle(R.string.menu_about);
                }
            }
        }
        ImageView threemaLogo = findViewById(R.id.threema_logo);
        if (!ListenerUtil.mutListener.listen(1651)) {
            AnimationUtil.bubbleAnimate(threemaLogo, 200);
        }
        if (!ListenerUtil.mutListener.listen(1654)) {
            // Enable developer menu
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(1652)) {
                    this.preferenceService.setShowDeveloperMenu(true);
                }
                if (!ListenerUtil.mutListener.listen(1653)) {
                    Toast.makeText(this, "You are now a craaazy developer!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public int getLayoutResource() {
        return R.layout.activity_about;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1656)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(1655)) {
                        finish();
                    }
                    break;
            }
        }
        return false;
    }
}
