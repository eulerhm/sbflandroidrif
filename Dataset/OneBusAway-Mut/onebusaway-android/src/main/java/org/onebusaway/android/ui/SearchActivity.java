/*
 * Copyright (C) 2010-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida
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

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.util.UIUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SearchActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4939)) {
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(4940)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4941)) {
            UIUtils.setupActionBar(this);
        }
        if (!ListenerUtil.mutListener.listen(4942)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        if (!ListenerUtil.mutListener.listen(4943)) {
            handleIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(4944)) {
            handleIntent(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4946)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(4945)) {
                    NavHelp.goHome(this, false);
                }
                return true;
            }
        }
        return false;
    }

    private void handleIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(4949)) {
            if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                // handles a search query
                String query = intent.getStringExtra(SearchManager.QUERY);
                if (!ListenerUtil.mutListener.listen(4947)) {
                    ObaAnalytics.reportSearchEvent(mFirebaseAnalytics, query);
                }
                if (!ListenerUtil.mutListener.listen(4948)) {
                    doSearch(query);
                }
            }
        }
    }

    private void doSearch(String query) {
        // Find both tabs and start a search for them...
        FragmentManager fm = getSupportFragmentManager();
        SearchResultsFragment list = (SearchResultsFragment) fm.findFragmentById(android.R.id.content);
        FragmentTransaction ft = fm.beginTransaction();
        if (!ListenerUtil.mutListener.listen(4951)) {
            // Create the list fragment and add it as our sole content.
            if (list != null) {
                if (!ListenerUtil.mutListener.listen(4950)) {
                    // The only thing we can do is remove this fragment
                    ft.remove(list);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4952)) {
            // Create a new fragment
            list = new SearchResultsFragment();
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(4953)) {
            args.putString(SearchResultsFragment.QUERY_TEXT, query);
        }
        if (!ListenerUtil.mutListener.listen(4954)) {
            list.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(4955)) {
            ft.add(android.R.id.content, list);
        }
        if (!ListenerUtil.mutListener.listen(4956)) {
            ft.commit();
        }
    }
}
