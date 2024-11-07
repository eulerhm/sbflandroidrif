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

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import com.github.pockethub.android.ui.MainActivity;
import com.meisolsson.githubsdk.model.Repository;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueFilter;
import com.github.pockethub.android.ui.base.BaseActivity;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static com.github.pockethub.android.Intents.EXTRA_ISSUE_FILTER;
import static com.github.pockethub.android.Intents.EXTRA_REPOSITORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity for browsing a list of issues scoped to a single {@link IssueFilter}
 */
public class IssueBrowseActivity extends BaseActivity {

    /**
     * Create intent to browse the filtered issues
     *
     * @param filter
     * @return intent
     */
    public static Intent createIntent(IssueFilter filter) {
        return new Builder("repo.issues.VIEW").repo(filter.getRepository()).add(EXTRA_ISSUE_FILTER, filter).toIntent();
    }

    private Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(936)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(937)) {
            setContentView(R.layout.activity_repo_issue_list);
        }
        if (!ListenerUtil.mutListener.listen(938)) {
            repo = getIntent().getParcelableExtra(EXTRA_REPOSITORY);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(939)) {
            actionBar.setTitle(repo.name());
        }
        if (!ListenerUtil.mutListener.listen(940)) {
            actionBar.setSubtitle(repo.owner().login());
        }
        if (!ListenerUtil.mutListener.listen(941)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(942)) {
                    finish();
                }
                Intent intent = new Intent(this, MainActivity.class);
                if (!ListenerUtil.mutListener.listen(943)) {
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(944)) {
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
