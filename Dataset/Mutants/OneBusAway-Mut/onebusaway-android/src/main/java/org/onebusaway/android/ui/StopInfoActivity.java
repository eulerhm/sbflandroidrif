/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This activity is deprecated, but we need to keep it around
 * because it is used by existing shortcuts.
 *
 * @author paulw
 */
@Deprecated
public class StopInfoActivity extends AppCompatActivity {

    private static final String TAG = "StopInfoActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1748)) {
            super.onCreate(savedInstanceState);
        }
        final Intent intent = getIntent();
        final Uri data = intent.getData();
        if (!ListenerUtil.mutListener.listen(1752)) {
            if (data != null) {
                if (!ListenerUtil.mutListener.listen(1751)) {
                    ArrivalsListActivity.start(this, data.getLastPathSegment());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1749)) {
                    Log.e(TAG, "No stop ID!");
                }
                if (!ListenerUtil.mutListener.listen(1750)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1753)) {
            finish();
        }
    }
}
