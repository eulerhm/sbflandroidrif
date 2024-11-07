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
package com.github.pockethub.android.ui.commit;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import com.meisolsson.githubsdk.model.Repository;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.util.InfoUtils;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static com.github.pockethub.android.Intents.EXTRA_BASE;
import static com.github.pockethub.android.Intents.EXTRA_HEAD;
import static com.github.pockethub.android.Intents.EXTRA_REPOSITORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to display a comparison between two commits
 */
public class CommitCompareViewActivity extends BaseActivity {

    /**
     * Create intent for this activity
     *
     * @param repository
     * @param base
     * @param head
     * @return intent
     */
    public static Intent createIntent(final Repository repository, final String base, final String head) {
        Builder builder = new Builder("commits.compare.VIEW");
        if (!ListenerUtil.mutListener.listen(744)) {
            builder.add(EXTRA_BASE, base);
        }
        if (!ListenerUtil.mutListener.listen(745)) {
            builder.add(EXTRA_HEAD, head);
        }
        if (!ListenerUtil.mutListener.listen(746)) {
            builder.repo(repository);
        }
        return builder.toIntent();
    }

    private Repository repository;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(747)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(748)) {
            setContentView(R.layout.commit_compare);
        }
        if (!ListenerUtil.mutListener.listen(749)) {
            repository = getIntent().getParcelableExtra(EXTRA_REPOSITORY);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(750)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(751)) {
            actionBar.setSubtitle(InfoUtils.createRepoId(repository));
        }
        if (!ListenerUtil.mutListener.listen(752)) {
            fragment = getSupportFragmentManager().findFragmentById(R.id.list);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionsMenu) {
        if (!ListenerUtil.mutListener.listen(754)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(753)) {
                    fragment.onCreateOptionsMenu(optionsMenu, getMenuInflater());
                }
            }
        }
        return super.onCreateOptionsMenu(optionsMenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = RepositoryViewActivity.Companion.createIntent(repository);
                if (!ListenerUtil.mutListener.listen(755)) {
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(756)) {
                    startActivity(intent);
                }
                return true;
            default:
                if (fragment != null) {
                    return fragment.onOptionsItemSelected(item);
                } else {
                    return super.onOptionsItemSelected(item);
                }
        }
    }
}
