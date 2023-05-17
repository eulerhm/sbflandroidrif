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
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueFilter;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.MainActivity;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static com.github.pockethub.android.ui.issue.FilterListFragment.ARG_FILTER;
import static com.github.pockethub.android.ui.issue.FilterListFragment.REQUEST_DELETE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to display a list of saved {@link IssueFilter} objects
 */
public class FiltersViewActivity extends BaseActivity implements DialogResultListener {

    private CompositeDisposable disposables;

    /**
     * Create intent to browse issue filters
     *
     * @return intent
     */
    public static Intent createIntent() {
        return new Builder("repo.issues.filters.VIEW").toIntent();
    }

    @Inject
    protected AccountDataManager cache;

    private FilterListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(923)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(924)) {
            setContentView(R.layout.issues_filter_list);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(925)) {
            actionBar.setTitle(R.string.bookmarks);
        }
        if (!ListenerUtil.mutListener.listen(926)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(927)) {
            fragment = (FilterListFragment) getSupportFragmentManager().findFragmentById(R.id.list);
        }
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Bundle arguments) {
        if (!ListenerUtil.mutListener.listen(930)) {
            if ((ListenerUtil.mutListener.listen(928) ? (requestCode == REQUEST_DELETE || resultCode == RESULT_OK) : (requestCode == REQUEST_DELETE && resultCode == RESULT_OK))) {
                IssueFilter filter = arguments.getParcelable(ARG_FILTER);
                if (!ListenerUtil.mutListener.listen(929)) {
                    disposables.add(cache.removeIssueFilter(filter).subscribe(response -> {
                        if (fragment != null) {
                            fragment.listFetcher.forceRefresh();
                        }
                    }));
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(931)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(933)) {
            if (disposables != null) {
                if (!ListenerUtil.mutListener.listen(932)) {
                    disposables.dispose();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                if (!ListenerUtil.mutListener.listen(934)) {
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(935)) {
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
