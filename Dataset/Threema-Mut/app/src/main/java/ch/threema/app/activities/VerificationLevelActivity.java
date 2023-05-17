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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ch.threema.app.R;
import ch.threema.app.adapters.ContactDetailAdapter;
import ch.threema.storage.models.GroupModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VerificationLevelActivity extends ThreemaToolbarActivity {

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7204)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(7207)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(7205)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(7206)) {
                    actionBar.setTitle(R.string.verification_levels_title);
                }
            }
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_verification_level;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(7209)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(7208)) {
                        finish();
                    }
                    break;
            }
        }
        return false;
    }
}
