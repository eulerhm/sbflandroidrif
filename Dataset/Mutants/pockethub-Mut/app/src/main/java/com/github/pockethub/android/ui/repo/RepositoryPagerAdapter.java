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
package com.github.pockethub.android.ui.repo;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.FragmentPagerAdapter;
import com.github.pockethub.android.ui.code.RepositoryCodeFragment;
import com.github.pockethub.android.ui.commit.CommitListFragment;
import com.github.pockethub.android.ui.issue.IssuesFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Adapter to view a repository's various pages
 */
public class RepositoryPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    private final boolean hasIssues;

    private final boolean hasReadme;

    private RepositoryCodeFragment codeFragment;

    private CommitListFragment commitsFragment;

    /**
     * Create repository pager adapter
     *
     * @param activity
     * @param hasIssues
     */
    public RepositoryPagerAdapter(AppCompatActivity activity, boolean hasIssues, boolean hasReadme) {
        super(activity);
        resources = activity.getResources();
        this.hasReadme = hasReadme;
        this.hasIssues = hasIssues;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (!ListenerUtil.mutListener.listen(1109)) {
            position = hasReadme ? position : (ListenerUtil.mutListener.listen(1108) ? (position % 1) : (ListenerUtil.mutListener.listen(1107) ? (position / 1) : (ListenerUtil.mutListener.listen(1106) ? (position * 1) : (ListenerUtil.mutListener.listen(1105) ? (position - 1) : (position + 1)))));
        }
        switch(position) {
            case 0:
                return resources.getString(R.string.tab_readme);
            case 1:
                return resources.getString(R.string.tab_news);
            case 2:
                return resources.getString(R.string.tab_code);
            case 3:
                return resources.getString(R.string.tab_commits);
            case 4:
                return resources.getString(R.string.tab_issues);
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (!ListenerUtil.mutListener.listen(1114)) {
            position = hasReadme ? position : (ListenerUtil.mutListener.listen(1113) ? (position % 1) : (ListenerUtil.mutListener.listen(1112) ? (position / 1) : (ListenerUtil.mutListener.listen(1111) ? (position * 1) : (ListenerUtil.mutListener.listen(1110) ? (position - 1) : (position + 1)))));
        }
        switch(position) {
            case 0:
                return new RepositoryReadmeFragment();
            case 1:
                return new RepositoryNewsFragment();
            case 2:
                if (!ListenerUtil.mutListener.listen(1115)) {
                    codeFragment = new RepositoryCodeFragment();
                }
                return codeFragment;
            case 3:
                if (!ListenerUtil.mutListener.listen(1116)) {
                    commitsFragment = new CommitListFragment();
                }
                return commitsFragment;
            case 4:
                return new IssuesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        int count = hasIssues ? 5 : 4;
        if (!ListenerUtil.mutListener.listen(1121)) {
            count = hasReadme ? count : (ListenerUtil.mutListener.listen(1120) ? (count % 1) : (ListenerUtil.mutListener.listen(1119) ? (count / 1) : (ListenerUtil.mutListener.listen(1118) ? (count * 1) : (ListenerUtil.mutListener.listen(1117) ? (count + 1) : (count - 1)))));
        }
        return count;
    }

    /**
     * Returns index of code page
     */
    public int getItemCode() {
        return hasReadme ? 2 : 1;
    }

    /**
     * Returns index of commits page
     */
    public int getItemCommits() {
        return hasReadme ? 3 : 2;
    }

    /**
     * Pass back button pressed event down to fragments
     *
     * @return true if handled, false otherwise
     */
    public boolean onBackPressed() {
        return (ListenerUtil.mutListener.listen(1122) ? (codeFragment != null || codeFragment.onBackPressed()) : (codeFragment != null && codeFragment.onBackPressed()));
    }

    /**
     * Deliver dialog result to fragment at given position
     *
     * @param position
     * @param requestCode
     * @param resultCode
     * @param arguments
     * @return this adapter
     */
    public RepositoryPagerAdapter onDialogResult(int position, int requestCode, int resultCode, Bundle arguments) {
        if (!ListenerUtil.mutListener.listen(1137)) {
            if ((ListenerUtil.mutListener.listen(1128) ? ((ListenerUtil.mutListener.listen(1127) ? (position >= getItemCode()) : (ListenerUtil.mutListener.listen(1126) ? (position <= getItemCode()) : (ListenerUtil.mutListener.listen(1125) ? (position > getItemCode()) : (ListenerUtil.mutListener.listen(1124) ? (position < getItemCode()) : (ListenerUtil.mutListener.listen(1123) ? (position != getItemCode()) : (position == getItemCode())))))) || codeFragment != null) : ((ListenerUtil.mutListener.listen(1127) ? (position >= getItemCode()) : (ListenerUtil.mutListener.listen(1126) ? (position <= getItemCode()) : (ListenerUtil.mutListener.listen(1125) ? (position > getItemCode()) : (ListenerUtil.mutListener.listen(1124) ? (position < getItemCode()) : (ListenerUtil.mutListener.listen(1123) ? (position != getItemCode()) : (position == getItemCode())))))) && codeFragment != null))) {
                if (!ListenerUtil.mutListener.listen(1136)) {
                    codeFragment.onDialogResult(requestCode, resultCode, arguments);
                }
            } else if ((ListenerUtil.mutListener.listen(1134) ? ((ListenerUtil.mutListener.listen(1133) ? (position >= getItemCommits()) : (ListenerUtil.mutListener.listen(1132) ? (position <= getItemCommits()) : (ListenerUtil.mutListener.listen(1131) ? (position > getItemCommits()) : (ListenerUtil.mutListener.listen(1130) ? (position < getItemCommits()) : (ListenerUtil.mutListener.listen(1129) ? (position != getItemCommits()) : (position == getItemCommits())))))) || commitsFragment != null) : ((ListenerUtil.mutListener.listen(1133) ? (position >= getItemCommits()) : (ListenerUtil.mutListener.listen(1132) ? (position <= getItemCommits()) : (ListenerUtil.mutListener.listen(1131) ? (position > getItemCommits()) : (ListenerUtil.mutListener.listen(1130) ? (position < getItemCommits()) : (ListenerUtil.mutListener.listen(1129) ? (position != getItemCommits()) : (position == getItemCommits())))))) && commitsFragment != null))) {
                if (!ListenerUtil.mutListener.listen(1135)) {
                    commitsFragment.onDialogResult(requestCode, resultCode, arguments);
                }
            }
        }
        return this;
    }
}
