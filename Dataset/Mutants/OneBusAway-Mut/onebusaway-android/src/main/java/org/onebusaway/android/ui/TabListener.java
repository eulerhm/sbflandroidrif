/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
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

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Copied from APIDemos and ported to the Sherlock
 *
 * @author paulw
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private final FragmentActivity mActivity;

    private final String mTag;

    private final Class<T> mClass;

    private final Bundle mArgs;

    private Fragment mFragment;

    public TabListener(FragmentActivity activity, String tag, Class<T> clz) {
        this(activity, tag, clz, null);
    }

    public TabListener(FragmentActivity activity, String tag, Class<T> clz, Bundle args) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mArgs = args;
        // initial state is that a tab isn't shown.
        FragmentManager fm = mActivity.getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(989)) {
            mFragment = fm.findFragmentByTag(mTag);
        }
        if (!ListenerUtil.mutListener.listen(993)) {
            if ((ListenerUtil.mutListener.listen(990) ? (mFragment != null || !mFragment.isDetached()) : (mFragment != null && !mFragment.isDetached()))) {
                FragmentTransaction ft = fm.beginTransaction();
                if (!ListenerUtil.mutListener.listen(991)) {
                    ft.detach(mFragment);
                }
                if (!ListenerUtil.mutListener.listen(992)) {
                    ft.commit();
                }
            }
        }
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction unused) {
        // See: http://groups.google.com/group/actionbarsherlock/browse_thread/thread/89eac58c13fe1ae0/8d6db0ba248e53d9?show_docid=8d6db0ba248e53d9
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!ListenerUtil.mutListener.listen(997)) {
            if (mFragment == null) {
                if (!ListenerUtil.mutListener.listen(995)) {
                    mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
                }
                if (!ListenerUtil.mutListener.listen(996)) {
                    ft.add(android.R.id.content, mFragment, mTag);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(994)) {
                    ft.attach(mFragment);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(998)) {
            ft.commit();
        }
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction unused) {
        if (!ListenerUtil.mutListener.listen(1001)) {
            if (mFragment != null) {
                FragmentManager fm = mActivity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (!ListenerUtil.mutListener.listen(999)) {
                    ft.detach(mFragment);
                }
                if (!ListenerUtil.mutListener.listen(1000)) {
                    ft.commit();
                }
            }
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
}
