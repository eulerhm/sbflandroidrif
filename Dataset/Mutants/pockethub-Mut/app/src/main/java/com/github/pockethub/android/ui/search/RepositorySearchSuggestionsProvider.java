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
package com.github.pockethub.android.ui.search;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.provider.SearchRecentSuggestions;
import com.github.pockethub.android.BuildConfig;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Suggestions provider for recently searched for repository queries
 */
public class RepositorySearchSuggestionsProvider extends SearchRecentSuggestionsProvider {

    private static final String AUTHORITY = BuildConfig.PROVIDER_AUTHORITY_SEARCH_SUGGEST_REPOS;

    /**
     * Save query to history
     *
     * @param context
     * @param query
     */
    public static void save(Context context, String query) {
        if (!ListenerUtil.mutListener.listen(1138)) {
            suggestions(context).saveRecentQuery(query, null);
        }
    }

    /**
     * Clear query history
     *
     * @param context
     */
    public static void clear(Context context) {
        if (!ListenerUtil.mutListener.listen(1139)) {
            suggestions(context).clearHistory();
        }
    }

    private static SearchRecentSuggestions suggestions(Context context) {
        return new SearchRecentSuggestions(context, AUTHORITY, DATABASE_MODE_QUERIES);
    }

    /**
     * Create suggestions provider for searched for repository queries
     */
    public RepositorySearchSuggestionsProvider() {
        if (!ListenerUtil.mutListener.listen(1140)) {
            setupSuggestions(AUTHORITY, DATABASE_MODE_QUERIES);
        }
    }
}
