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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class MyTabActivityBase extends AppCompatActivity {

    public static final String EXTRA_SHORTCUTMODE = ".ShortcutMode";

    // int[]
    public static final String EXTRA_SEARCHCENTER = ".SearchCenter";

    public static final String RESULT_ROUTE_ID = ".RouteId";

    protected boolean mShortcutMode;

    protected Location mSearchCenter;

    protected String mDefaultTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3753)) {
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(3754)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3755)) {
            UIUtils.setupActionBar(this);
        }
        if (!ListenerUtil.mutListener.listen(3756)) {
            setSupportProgressBarIndeterminateVisibility(false);
        }
        final Intent intent = getIntent();
        final String action = intent.getAction();
        if (!ListenerUtil.mutListener.listen(3757)) {
            mShortcutMode = Intent.ACTION_CREATE_SHORTCUT.equals(action);
        }
        if (!ListenerUtil.mutListener.listen(3759)) {
            if (!mShortcutMode) {
                if (!ListenerUtil.mutListener.listen(3758)) {
                    setTitle(R.string.app_name);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3760)) {
            mSearchCenter = getSearchCenter(intent);
        }
        if (!ListenerUtil.mutListener.listen(3762)) {
            // Determine what tab we're supposed to show by default
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(3761)) {
                    mDefaultTab = savedInstanceState.getString("tab");
                }
            }
        }
        final Uri data = intent.getData();
        if (!ListenerUtil.mutListener.listen(3765)) {
            if ((ListenerUtil.mutListener.listen(3763) ? (data != null || mDefaultTab == null) : (data != null && mDefaultTab == null))) {
                if (!ListenerUtil.mutListener.listen(3764)) {
                    mDefaultTab = getDefaultTabFromUri(data);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3767)) {
            // If there was a tab in the intent, don't save it
            if (mDefaultTab == null) {
                final ActionBar bar = getSupportActionBar();
                final ActionBar.Tab tab = bar.getSelectedTab();
                if (!ListenerUtil.mutListener.listen(3766)) {
                    PreferenceUtils.saveString(getLastTabPref(), (String) tab.getTag());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3768)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(3770)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(3769)) {
                    NavHelp.goHome(this, false);
                }
                return true;
            }
        }
        return false;
    }

    protected void restoreDefaultTab() {
        final String def;
        if (mDefaultTab != null) {
            def = mDefaultTab;
        } else {
            SharedPreferences settings = Application.getPrefs();
            def = settings.getString(getLastTabPref(), null);
        }
        if (!ListenerUtil.mutListener.listen(3779)) {
            if (def != null) {
                // Find this tab...
                final ActionBar bar = getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(3778)) {
                    {
                        long _loopCounter32 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(3777) ? (i >= bar.getTabCount()) : (ListenerUtil.mutListener.listen(3776) ? (i <= bar.getTabCount()) : (ListenerUtil.mutListener.listen(3775) ? (i > bar.getTabCount()) : (ListenerUtil.mutListener.listen(3774) ? (i != bar.getTabCount()) : (ListenerUtil.mutListener.listen(3773) ? (i == bar.getTabCount()) : (i < bar.getTabCount())))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                            ActionBar.Tab tab = bar.getTabAt(i);
                            if (!ListenerUtil.mutListener.listen(3772)) {
                                if (def.equals(tab.getTag())) {
                                    if (!ListenerUtil.mutListener.listen(3771)) {
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

    // 
    boolean isShortcutMode() {
        return mShortcutMode;
    }

    Location getSearchCenter() {
        return mSearchCenter;
    }

    // 
    private static final String TAB_SCHEME = "tab";

    public static final Uri getDefaultTabUri(String tab) {
        return Uri.fromParts(TAB_SCHEME, tab, null);
    }

    public static String getDefaultTabFromUri(Uri uri) {
        if (!ListenerUtil.mutListener.listen(3780)) {
            if (TAB_SCHEME.equals(uri.getScheme())) {
                return uri.getSchemeSpecificPart();
            }
        }
        return null;
    }

    protected abstract String getLastTabPref();

    // 
    public static final Intent putSearchCenter(Intent intent, Location pt) {
        if (!ListenerUtil.mutListener.listen(3782)) {
            if (pt != null) {
                double[] p = { pt.getLatitude(), pt.getLongitude() };
                if (!ListenerUtil.mutListener.listen(3781)) {
                    intent.putExtra(EXTRA_SEARCHCENTER, p);
                }
            }
        }
        return intent;
    }

    private static final Location getSearchCenter(Intent intent) {
        double[] p = intent.getDoubleArrayExtra(EXTRA_SEARCHCENTER);
        if (!ListenerUtil.mutListener.listen(3789)) {
            if ((ListenerUtil.mutListener.listen(3788) ? (p != null || (ListenerUtil.mutListener.listen(3787) ? (p.length >= 2) : (ListenerUtil.mutListener.listen(3786) ? (p.length <= 2) : (ListenerUtil.mutListener.listen(3785) ? (p.length > 2) : (ListenerUtil.mutListener.listen(3784) ? (p.length < 2) : (ListenerUtil.mutListener.listen(3783) ? (p.length != 2) : (p.length == 2))))))) : (p != null && (ListenerUtil.mutListener.listen(3787) ? (p.length >= 2) : (ListenerUtil.mutListener.listen(3786) ? (p.length <= 2) : (ListenerUtil.mutListener.listen(3785) ? (p.length > 2) : (ListenerUtil.mutListener.listen(3784) ? (p.length < 2) : (ListenerUtil.mutListener.listen(3783) ? (p.length != 2) : (p.length == 2))))))))) {
                return LocationUtils.makeLocation(p[0], p[1]);
            }
        }
        return null;
    }
}
