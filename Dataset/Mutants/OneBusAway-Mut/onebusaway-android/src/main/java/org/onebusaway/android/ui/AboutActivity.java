/*
 * Copyright (C) 2015-2017 University of South Florida (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import org.onebusaway.android.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An Activity that displays version, license, and contributor information
 */
public class AboutActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        if (!ListenerUtil.mutListener.listen(1311)) {
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1312)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1313)) {
            setContentView(R.layout.activity_about);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(1314)) {
            setSupportActionBar(toolbar);
        }
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (!ListenerUtil.mutListener.listen(1315)) {
            toolBarLayout.setTitle(getTitle());
        }
        if (!ListenerUtil.mutListener.listen(1316)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView tv = (TextView) findViewById(R.id.about_text);
        String versionString = "";
        int versionCode = 0;
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            if (!ListenerUtil.mutListener.listen(1318)) {
                versionString = info.versionName;
            }
            if (!ListenerUtil.mutListener.listen(1319)) {
                versionCode = info.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(1317)) {
                e.printStackTrace();
            }
        }
        StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(1320)) {
            // Version info
            builder.append("v").append(versionString).append(" (").append(versionCode).append(")\n\n");
        }
        if (!ListenerUtil.mutListener.listen(1321)) {
            // Majority of content from string resource
            builder.append(getString(R.string.about_text));
        }
        if (!ListenerUtil.mutListener.listen(1322)) {
            builder.append("\n\n");
        }
        if (!ListenerUtil.mutListener.listen(1323)) {
            tv.setText(builder.toString());
        }
    }
}
