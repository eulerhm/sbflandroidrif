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

import android.content.res.Resources;
import android.os.Bundle;
import org.onebusaway.android.R;
import org.onebusaway.android.util.UIUtils;
import androidx.appcompat.app.ActionBar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyRoutesActivity extends MyTabActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4924)) {
            super.onCreate(savedInstanceState);
        }
        // ensureSupportActionBarAttached();
        final Resources res = getResources();
        final ActionBar bar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4925)) {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        if (!ListenerUtil.mutListener.listen(4926)) {
            bar.setTitle(R.string.my_recent_routes);
        }
        if (!ListenerUtil.mutListener.listen(4927)) {
            bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(4928)) {
            bar.addTab(bar.newTab().setTag(MyRecentRoutesFragment.TAB_NAME).setText(res.getString(R.string.my_recent_title)).setIcon(res.getDrawable(R.drawable.ic_tab_recent)).setTabListener(new TabListener<MyRecentRoutesFragment>(this, MyRecentRoutesFragment.TAB_NAME, MyRecentRoutesFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(4929)) {
            bar.addTab(bar.newTab().setTag(MySearchRoutesFragment.TAB_NAME).setText(res.getString(R.string.my_search_title)).setIcon(res.getDrawable(R.drawable.ic_tab_search)).setTabListener(new TabListener<MySearchRoutesFragment>(this, MySearchRoutesFragment.TAB_NAME, MySearchRoutesFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(4930)) {
            restoreDefaultTab();
        }
        if (!ListenerUtil.mutListener.listen(4931)) {
            UIUtils.setupActionBar(this);
        }
    }

    @Override
    protected String getLastTabPref() {
        return "MyRoutesActivity.LastTab";
    }
}
