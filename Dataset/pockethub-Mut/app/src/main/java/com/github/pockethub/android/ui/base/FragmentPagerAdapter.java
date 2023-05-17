/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.ui.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import com.github.pockethub.android.ui.FragmentProvider;
import java.util.HashSet;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Pager adapter that provides the current fragment
 */
public abstract class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter implements FragmentProvider {

    private final AppCompatActivity activity;

    private final FragmentManager fragmentManager;

    private Fragment selected;

    private final Set<String> tags = new HashSet<>();

    private int containerId;

    /**
     * @param activity
     */
    public FragmentPagerAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        fragmentManager = activity.getSupportFragmentManager();
        this.activity = activity;
    }

    public FragmentPagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
        fragmentManager = fragment.getChildFragmentManager();
        this.activity = (AppCompatActivity) fragment.getActivity();
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    /**
     * This methods clears any fragments that may not apply to the newly
     * selected org.
     *
     * @return this adapter
     */
    public FragmentPagerAdapter clearAdapter() {
        if (!ListenerUtil.mutListener.listen(702)) {
            if (tags.isEmpty()) {
                return this;
            }
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!ListenerUtil.mutListener.listen(705)) {
            {
                long _loopCounter20 = 0;
                for (String tag : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                    Fragment fragment = fragmentManager.findFragmentByTag(tag);
                    if (!ListenerUtil.mutListener.listen(704)) {
                        if (fragment != null) {
                            if (!ListenerUtil.mutListener.listen(703)) {
                                transaction.remove(fragment);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(706)) {
            transaction.commit();
        }
        if (!ListenerUtil.mutListener.listen(707)) {
            tags.clear();
        }
        return this;
    }

    @Override
    public Fragment getSelected() {
        return selected;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        if (!ListenerUtil.mutListener.listen(708)) {
            containerId = container.getId();
        }
        if (!ListenerUtil.mutListener.listen(710)) {
            if (fragment instanceof Fragment) {
                if (!ListenerUtil.mutListener.listen(709)) {
                    tags.add(((Fragment) fragment).getTag());
                }
            }
        }
        return fragment;
    }

    /**
     * This method is used to get a reference to created fragments in the adapter.
     *
     * @param fragmentPosition
     * position of the fragment in the pager.
     *
     * @return corresponding fragment that is created
     * during {@link #instantiateItem(ViewGroup, int)}
     */
    public Fragment getFragmentByPosition(int fragmentPosition) {
        String fragmentTag = getFragmentTag(containerId, fragmentPosition);
        return fragmentManager.findFragmentByTag(fragmentTag);
    }

    @Override
    public void setPrimaryItem(final ViewGroup container, final int position, final Object object) {
        if (!ListenerUtil.mutListener.listen(711)) {
            super.setPrimaryItem(container, position, object);
        }
        boolean changed = false;
        if (!ListenerUtil.mutListener.listen(716)) {
            if (object instanceof Fragment) {
                if (!ListenerUtil.mutListener.listen(714)) {
                    changed = object != selected;
                }
                if (!ListenerUtil.mutListener.listen(715)) {
                    selected = (Fragment) object;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(712)) {
                    changed = object != null;
                }
                if (!ListenerUtil.mutListener.listen(713)) {
                    selected = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(718)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(717)) {
                    activity.invalidateOptionsMenu();
                }
            }
        }
    }

    private String getFragmentTag(int viewPagerId, int fragmentPosition) {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }
}
