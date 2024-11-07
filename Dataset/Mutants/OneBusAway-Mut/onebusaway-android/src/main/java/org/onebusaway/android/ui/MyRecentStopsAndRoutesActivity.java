/*
 * Copyright (C) 2010-2015 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com)
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
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyRecentStopsAndRoutesActivity extends MyTabActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(157)) {
            super.onCreate(savedInstanceState);
        }
        Intent myIntent = getIntent();
        if (!ListenerUtil.mutListener.listen(161)) {
            if (Intent.ACTION_CREATE_SHORTCUT.equals(myIntent.getAction())) {
                ShortcutInfoCompat shortcut = getShortcut();
                if (!ListenerUtil.mutListener.listen(158)) {
                    ShortcutManagerCompat.requestPinShortcut(this, shortcut, null);
                }
                if (!ListenerUtil.mutListener.listen(159)) {
                    setResult(RESULT_OK, shortcut.getIntent());
                }
                if (!ListenerUtil.mutListener.listen(160)) {
                    finish();
                }
            }
        }
        final Resources res = getResources();
        final ActionBar bar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(162)) {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        if (!ListenerUtil.mutListener.listen(163)) {
            bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(164)) {
            bar.addTab(bar.newTab().setTag(MyRecentStopsFragment.TAB_NAME).setText(res.getString(R.string.my_recent_stops)).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_tab_recent)).setTabListener(new TabListener<MyRecentStopsFragment>(this, MyRecentStopsFragment.TAB_NAME, MyRecentStopsFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(165)) {
            bar.addTab(bar.newTab().setTag(MyRecentRoutesFragment.TAB_NAME).setText(res.getString(R.string.my_recent_routes)).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_tab_recent)).setTabListener(new TabListener<MyRecentRoutesFragment>(this, MyRecentRoutesFragment.TAB_NAME, MyRecentRoutesFragment.class)));
        }
        if (!ListenerUtil.mutListener.listen(166)) {
            restoreDefaultTab();
        }
        if (!ListenerUtil.mutListener.listen(167)) {
            UIUtils.setupActionBar(this);
        }
        if (!ListenerUtil.mutListener.listen(168)) {
            setTitle(R.string.my_recent_title);
        }
    }

    @Override
    protected String getLastTabPref() {
        return "RecentRoutesStopsActivity.LastTab";
    }

    /**
     * Override default tab handling behavior of MyTabActivityBase - use tab text instead of tag
     * for saving to preference. See #585.
     */
    @Override
    protected void restoreDefaultTab() {
        final String def;
        if (mDefaultTab != null) {
            def = mDefaultTab;
            if (!ListenerUtil.mutListener.listen(186)) {
                if (def != null) {
                    // Find this tab...
                    final ActionBar bar = getSupportActionBar();
                    if (!ListenerUtil.mutListener.listen(185)) {
                        {
                            long _loopCounter1 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(184) ? (i >= bar.getTabCount()) : (ListenerUtil.mutListener.listen(183) ? (i <= bar.getTabCount()) : (ListenerUtil.mutListener.listen(182) ? (i > bar.getTabCount()) : (ListenerUtil.mutListener.listen(181) ? (i != bar.getTabCount()) : (ListenerUtil.mutListener.listen(180) ? (i == bar.getTabCount()) : (i < bar.getTabCount())))))); ++i) {
                                ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                                ActionBar.Tab tab = bar.getTabAt(i);
                                if (!ListenerUtil.mutListener.listen(179)) {
                                    // Still use tab.getTag() here, as its driven by intent or saved instance state
                                    if (def.equals(tab.getTag())) {
                                        if (!ListenerUtil.mutListener.listen(178)) {
                                            tab.select();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            SharedPreferences settings = Application.getPrefs();
            def = settings.getString(getLastTabPref(), null);
            if (!ListenerUtil.mutListener.listen(177)) {
                if (def != null) {
                    // Find this tab...
                    final ActionBar bar = getSupportActionBar();
                    if (!ListenerUtil.mutListener.listen(176)) {
                        {
                            long _loopCounter0 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(175) ? (i >= bar.getTabCount()) : (ListenerUtil.mutListener.listen(174) ? (i <= bar.getTabCount()) : (ListenerUtil.mutListener.listen(173) ? (i > bar.getTabCount()) : (ListenerUtil.mutListener.listen(172) ? (i != bar.getTabCount()) : (ListenerUtil.mutListener.listen(171) ? (i == bar.getTabCount()) : (i < bar.getTabCount())))))); ++i) {
                                ListenerUtil.loopListener.listen("_loopCounter0", ++_loopCounter0);
                                ActionBar.Tab tab = bar.getTabAt(i);
                                if (!ListenerUtil.mutListener.listen(170)) {
                                    if (def.equals(tab.getText())) {
                                        if (!ListenerUtil.mutListener.listen(169)) {
                                            tab.select();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Override default tab handling behavior of MyTabActivityBase - use tab text instead of tag
     * for saving to preference. See #585.
     */
    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(189)) {
            // If there was a tab in the intent, don't save it
            if (mDefaultTab == null) {
                final ActionBar bar = getSupportActionBar();
                final ActionBar.Tab tab = bar.getSelectedTab();
                if (!ListenerUtil.mutListener.listen(187)) {
                    PreferenceUtils.saveString(getLastTabPref(), (String) tab.getText());
                }
                if (!ListenerUtil.mutListener.listen(188)) {
                    // preference we set above.  FIXME - this is a total hack.
                    mDefaultTab = "hack";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(190)) {
            super.onDestroy();
        }
    }

    private ShortcutInfoCompat getShortcut() {
        return UIUtils.makeShortcutInfo(this, getString(R.string.my_recent_title), new Intent(this, MyRecentStopsAndRoutesActivity.class), R.drawable.ic_history);
    }
}
