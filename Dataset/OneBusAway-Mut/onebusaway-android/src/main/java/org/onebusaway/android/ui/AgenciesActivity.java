/*
 * Copyright (C) 2012-2013 Paul Watts (paulcwatts@gmail.com) and individual contributors.
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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import org.onebusaway.android.util.UIUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AgenciesActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, AgenciesActivity.class);
        if (!ListenerUtil.mutListener.listen(4932)) {
            context.startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4933)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4934)) {
            UIUtils.setupActionBar(this);
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4936)) {
            // Create the list fragment and add it as our sole content.
            if (fm.findFragmentById(android.R.id.content) == null) {
                AgenciesFragment list = new AgenciesFragment();
                if (!ListenerUtil.mutListener.listen(4935)) {
                    fm.beginTransaction().add(android.R.id.content, list).commit();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4938)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(4937)) {
                    NavHelp.goHome(this, false);
                }
                return true;
            }
        }
        return false;
    }
}
