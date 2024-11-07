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

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Repository;
import static android.app.SearchManager.APP_DATA;
import static android.app.SearchManager.QUERY;
import static android.content.Intent.*;
import static com.github.pockethub.android.Intents.EXTRA_REPOSITORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to search issues
 */
public class IssueSearchActivity extends BaseActivity {

    private Repository repository;

    private SearchIssueListFragment issueFragment;

    private String lastQuery;

    private SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu options) {
        if (!ListenerUtil.mutListener.listen(950)) {
            getMenuInflater().inflate(R.menu.activity_search, options);
        }
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = options.findItem(R.id.m_search);
        if (!ListenerUtil.mutListener.listen(951)) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        }
        if (!ListenerUtil.mutListener.listen(952)) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(953)) {
            args.putParcelable(EXTRA_REPOSITORY, repository);
        }
        if (!ListenerUtil.mutListener.listen(954)) {
            searchView.setAppSearchData(args);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.m_search:
                if (!ListenerUtil.mutListener.listen(955)) {
                    searchView.post(() -> searchView.setQuery(lastQuery, false));
                }
                return true;
            case R.id.m_clear:
                if (!ListenerUtil.mutListener.listen(956)) {
                    IssueSearchSuggestionsProvider.clear(this);
                }
                if (!ListenerUtil.mutListener.listen(957)) {
                    ToastUtils.show(this, R.string.search_history_cleared);
                }
                return true;
            case android.R.id.home:
                Intent intent = RepositoryViewActivity.Companion.createIntent(repository);
                if (!ListenerUtil.mutListener.listen(958)) {
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(959)) {
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(960)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(961)) {
            setContentView(R.layout.activity_issue_search);
        }
        ActionBar actionBar = getSupportActionBar();
        Bundle appData = getIntent().getBundleExtra(APP_DATA);
        if (!ListenerUtil.mutListener.listen(966)) {
            if (appData != null) {
                if (!ListenerUtil.mutListener.listen(962)) {
                    repository = appData.getParcelable(EXTRA_REPOSITORY);
                }
                if (!ListenerUtil.mutListener.listen(965)) {
                    if (repository != null) {
                        if (!ListenerUtil.mutListener.listen(963)) {
                            actionBar.setSubtitle(InfoUtils.createRepoId(repository));
                        }
                        if (!ListenerUtil.mutListener.listen(964)) {
                            actionBar.setDisplayHomeAsUpEnabled(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(967)) {
            issueFragment = (SearchIssueListFragment) getSupportFragmentManager().findFragmentById(R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(968)) {
            handleIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(969)) {
            setIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(970)) {
            handleIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(971)) {
            issueFragment.pagedListFetcher.refresh();
        }
    }

    private void handleIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(973)) {
            if (ACTION_SEARCH.equals(intent.getAction())) {
                if (!ListenerUtil.mutListener.listen(972)) {
                    search(intent.getStringExtra(QUERY));
                }
            }
        }
    }

    private void search(final String query) {
        if (!ListenerUtil.mutListener.listen(974)) {
            lastQuery = query;
        }
        if (!ListenerUtil.mutListener.listen(975)) {
            getSupportActionBar().setTitle(query);
        }
        if (!ListenerUtil.mutListener.listen(976)) {
            IssueSearchSuggestionsProvider.save(this, query);
        }
        if (!ListenerUtil.mutListener.listen(977)) {
            issueFragment.setQuery(query);
        }
    }
}
