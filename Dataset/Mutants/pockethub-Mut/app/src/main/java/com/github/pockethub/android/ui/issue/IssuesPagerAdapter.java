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
package com.github.pockethub.android.ui.issue;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.ui.base.FragmentStatePagerAdapter;
import java.util.List;
import static com.github.pockethub.android.Intents.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Adapter to page through an {@link Issue} array
 */
public class IssuesPagerAdapter extends FragmentStatePagerAdapter {

    private final Repository repo;

    private final List<Repository> repos;

    private final int[] issues;

    private final SparseArray<IssueFragment> fragments = new SparseArray<>();

    private final IssueStore store;

    private boolean canWrite;

    /**
     * @param activity
     * @param repoIds
     * @param issueNumbers
     * @param issueStore
     * @param canWrite
     */
    public IssuesPagerAdapter(AppCompatActivity activity, List<Repository> repoIds, int[] issueNumbers, IssueStore issueStore, boolean canWrite) {
        super(activity);
        repos = repoIds;
        repo = null;
        issues = issueNumbers;
        store = issueStore;
        if (!ListenerUtil.mutListener.listen(981)) {
            this.canWrite = canWrite;
        }
    }

    /**
     * @param activity
     * @param repository
     * @param issueNumbers
     * @param canWrite
     */
    public IssuesPagerAdapter(AppCompatActivity activity, Repository repository, int[] issueNumbers, boolean canWrite) {
        super(activity);
        repos = null;
        repo = repository;
        issues = issueNumbers;
        store = null;
        if (!ListenerUtil.mutListener.listen(982)) {
            this.canWrite = canWrite;
        }
    }

    @Override
    public Fragment getItem(int position) {
        IssueFragment fragment = new IssueFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(993)) {
            if (repo != null) {
                if (!ListenerUtil.mutListener.listen(990)) {
                    args.putString(EXTRA_REPOSITORY_NAME, repo.name());
                }
                User owner = repo.owner();
                if (!ListenerUtil.mutListener.listen(991)) {
                    args.putString(EXTRA_REPOSITORY_OWNER, owner.login());
                }
                if (!ListenerUtil.mutListener.listen(992)) {
                    args.putParcelable(EXTRA_USER, owner);
                }
            } else {
                Repository repo = repos.get(position);
                if (!ListenerUtil.mutListener.listen(983)) {
                    args.putString(EXTRA_REPOSITORY_NAME, repo.name());
                }
                if (!ListenerUtil.mutListener.listen(984)) {
                    args.putString(EXTRA_REPOSITORY_OWNER, repo.owner().login());
                }
                Issue issue = store.getIssue(repo, issues[position]);
                if (!ListenerUtil.mutListener.listen(989)) {
                    if ((ListenerUtil.mutListener.listen(985) ? (issue != null || issue.user() != null) : (issue != null && issue.user() != null))) {
                        Repository fullRepo = issue.repository();
                        if (!ListenerUtil.mutListener.listen(988)) {
                            if ((ListenerUtil.mutListener.listen(986) ? (fullRepo != null || fullRepo.owner() != null) : (fullRepo != null && fullRepo.owner() != null))) {
                                if (!ListenerUtil.mutListener.listen(987)) {
                                    args.putParcelable(EXTRA_USER, fullRepo.owner());
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(994)) {
            args.putInt(EXTRA_ISSUE_NUMBER, issues[position]);
        }
        if (!ListenerUtil.mutListener.listen(995)) {
            args.putBoolean(EXTRA_CAN_WRITE_REPO, canWrite);
        }
        if (!ListenerUtil.mutListener.listen(996)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (!ListenerUtil.mutListener.listen(997)) {
            super.destroyItem(container, position, object);
        }
        if (!ListenerUtil.mutListener.listen(998)) {
            fragments.remove(position);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        if (!ListenerUtil.mutListener.listen(1000)) {
            if (fragment instanceof IssueFragment) {
                if (!ListenerUtil.mutListener.listen(999)) {
                    fragments.put(position, (IssueFragment) fragment);
                }
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return issues.length;
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
    public IssuesPagerAdapter onDialogResult(int position, int requestCode, int resultCode, Bundle arguments) {
        IssueFragment fragment = fragments.get(position);
        if (!ListenerUtil.mutListener.listen(1002)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(1001)) {
                    fragment.onDialogResult(requestCode, resultCode, arguments);
                }
            }
        }
        return this;
    }
}
