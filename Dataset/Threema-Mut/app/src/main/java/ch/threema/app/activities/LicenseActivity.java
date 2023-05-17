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
import android.webkit.WebView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LicenseActivity extends ThreemaToolbarActivity {

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4301)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4304)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(4302)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(4303)) {
                    actionBar.setTitle(R.string.os_licenses);
                }
            }
        }
        final WebView webView = findViewById(R.id.license_webview);
        if (!ListenerUtil.mutListener.listen(4305)) {
            webView.loadUrl("file:///android_asset/license.html");
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_license;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4307)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(4306)) {
                        finish();
                    }
                    break;
            }
        }
        return false;
    }
}
