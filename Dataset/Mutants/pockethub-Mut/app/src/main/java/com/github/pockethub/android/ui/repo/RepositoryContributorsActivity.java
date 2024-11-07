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

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.github.pockethub.android.Intents;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static com.github.pockethub.android.Intents.EXTRA_REPOSITORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to view repository contributors
 */
public class RepositoryContributorsActivity extends BaseActivity {

    /**
     * Create intent for this activity
     *
     * @param repository
     * @return intent
     */
    public static Intent createIntent(Repository repository) {
        return new Intents.Builder("repo.contributors.VIEW").repo(repository).toIntent();
    }

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1088)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1089)) {
            setContentView(R.layout.activity_repo_contributors);
        }
        if (!ListenerUtil.mutListener.listen(1090)) {
            repository = getIntent().getParcelableExtra(EXTRA_REPOSITORY);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(1091)) {
            actionBar.setTitle(repository.name());
        }
        if (!ListenerUtil.mutListener.listen(1092)) {
            actionBar.setSubtitle(R.string.contributors);
        }
        if (!ListenerUtil.mutListener.listen(1093)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        User owner = repository.owner();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = RepositoryViewActivity.Companion.createIntent(repository);
                if (!ListenerUtil.mutListener.listen(1094)) {
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(1095)) {
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
