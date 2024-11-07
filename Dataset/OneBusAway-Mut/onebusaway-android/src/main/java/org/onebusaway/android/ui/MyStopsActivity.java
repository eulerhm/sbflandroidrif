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

public class MyStopsActivity extends MyTabActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1932)) {
            super.onCreate(savedInstanceState);
        }
        final Resources res = getResources();
        final ActionBar bar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(1933)) {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        if (!ListenerUtil.mutListener.listen(1934)) {
            bar.setTitle(R.string.my_recent_stops);
        }
        if (!ListenerUtil.mutListener.listen(1935)) {
            bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(1936)) {
            bar.addTab(bar.newTab().setTag(MyRecentStopsFragment.TAB_NAME).setText(res.getString(R.string.my_recent_title)).setIcon(res.getDrawable(R.drawable.ic_tab_recent)).setTabListener(new TabListener<MyRecentStopsFragment>(this, MyRecentStopsFragment.TAB_NAME, MyRecentStopsFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(1937)) {
            bar.addTab(bar.newTab().setTag(MyStarredStopsFragment.TAB_NAME).setText(res.getString(R.string.my_starred_title)).setIcon(res.getDrawable(R.drawable.ic_tab_starred)).setTabListener(new TabListener<MyStarredStopsFragment>(this, MyStarredStopsFragment.TAB_NAME, MyStarredStopsFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(1938)) {
            bar.addTab(bar.newTab().setTag(MySearchStopsFragment.TAB_NAME).setText(res.getString(R.string.my_search_title)).setIcon(res.getDrawable(R.drawable.ic_tab_search)).setTabListener(new TabListener<MySearchStopsFragment>(this, MySearchStopsFragment.TAB_NAME, MySearchStopsFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(1939)) {
            restoreDefaultTab();
        }
        if (!ListenerUtil.mutListener.listen(1940)) {
            UIUtils.setupActionBar(this);
        }
    }

    @Override
    protected String getLastTabPref() {
        return "MyStopsActivity.LastTab";
    }
}
