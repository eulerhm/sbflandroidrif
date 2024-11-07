/*
 * Copyright (C) 2010-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South  Florida (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import org.onebusaway.android.R;
import org.onebusaway.android.util.UIUtils;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyRecentStopsActivity extends AppCompatActivity {

    // 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3069)) {
            super.onCreate(savedInstanceState);
        }
        Intent myIntent = getIntent();
        if (!ListenerUtil.mutListener.listen(3072)) {
            if (Intent.ACTION_CREATE_SHORTCUT.equals(myIntent.getAction())) {
                ShortcutInfoCompat shortcut = getShortcut();
                if (!ListenerUtil.mutListener.listen(3070)) {
                    ShortcutManagerCompat.requestPinShortcut(this, shortcut, null);
                }
                if (!ListenerUtil.mutListener.listen(3071)) {
                    setResult(RESULT_OK, shortcut.getIntent());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3073)) {
            finish();
        }
    }

    private ShortcutInfoCompat getShortcut() {
        final Uri uri = MyTabActivityBase.getDefaultTabUri(MyRecentStopsFragment.TAB_NAME);
        return UIUtils.makeShortcutInfo(this, getString(R.string.recent_stops_shortcut), new Intent(this, MyStopsActivity.class).setData(uri), R.drawable.ic_history);
    }
}
