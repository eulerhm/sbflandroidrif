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
package com.github.pockethub.android.core.issue;

import android.content.Context;
import androidx.annotation.StringRes;
import android.widget.Toast;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.ItemStore;
import com.github.pockethub.android.util.InfoUtils;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.IssueState;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.request.issue.IssueRequest;
import com.meisolsson.githubsdk.service.issues.IssueService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;
import retrofit2.Response;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Store of loaded issues
 */
@Singleton
public class IssueStore extends ItemStore {

    private final Map<String, ItemReferences<Issue>> repos = new HashMap<>();

    @Inject
    protected Context context;

    @Inject
    protected IssueService service;

    /**
     * Create issue store.
     */
    @Inject
    public IssueStore() {
    }

    /**
     * Get issue
     *
     * @param repository
     * @param number
     * @return issue or null if not in store
     */
    public Issue getIssue(Repository repository, int number) {
        ItemReferences<Issue> repoIssues = repos.get(InfoUtils.createRepoId(repository));
        return repoIssues != null ? repoIssues.get(number) : null;
    }

    /**
     * Add issue to store
     *
     * @param issue
     * @return issue
     */
    public Issue addIssue(Issue issue) {
        Repository repo = null;
        if (!ListenerUtil.mutListener.listen(453)) {
            if (issue != null) {
                if (!ListenerUtil.mutListener.listen(450)) {
                    repo = issue.repository();
                }
                if (!ListenerUtil.mutListener.listen(452)) {
                    if (repo == null) {
                        if (!ListenerUtil.mutListener.listen(451)) {
                            repo = repoFromUrl(issue.htmlUrl());
                        }
                    }
                }
            }
        }
        return addIssue(repo, issue);
    }

    private Repository repoFromUrl(String url) {
        if ((ListenerUtil.mutListener.listen(459) ? (url == null && (ListenerUtil.mutListener.listen(458) ? (url.length() >= 0) : (ListenerUtil.mutListener.listen(457) ? (url.length() <= 0) : (ListenerUtil.mutListener.listen(456) ? (url.length() > 0) : (ListenerUtil.mutListener.listen(455) ? (url.length() < 0) : (ListenerUtil.mutListener.listen(454) ? (url.length() != 0) : (url.length() == 0))))))) : (url == null || (ListenerUtil.mutListener.listen(458) ? (url.length() >= 0) : (ListenerUtil.mutListener.listen(457) ? (url.length() <= 0) : (ListenerUtil.mutListener.listen(456) ? (url.length() > 0) : (ListenerUtil.mutListener.listen(455) ? (url.length() < 0) : (ListenerUtil.mutListener.listen(454) ? (url.length() != 0) : (url.length() == 0))))))))) {
            return null;
        }
        String owner = null;
        String name = null;
        if (!ListenerUtil.mutListener.listen(469)) {
            {
                long _loopCounter12 = 0;
                for (// $NON-NLS-1$
                String segment : // $NON-NLS-1$
                url.split("/")) {
                    ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                    if (!ListenerUtil.mutListener.listen(468)) {
                        if ((ListenerUtil.mutListener.listen(464) ? (segment.length() >= 0) : (ListenerUtil.mutListener.listen(463) ? (segment.length() <= 0) : (ListenerUtil.mutListener.listen(462) ? (segment.length() < 0) : (ListenerUtil.mutListener.listen(461) ? (segment.length() != 0) : (ListenerUtil.mutListener.listen(460) ? (segment.length() == 0) : (segment.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(467)) {
                                if (owner == null) {
                                    if (!ListenerUtil.mutListener.listen(466)) {
                                        owner = segment;
                                    }
                                } else if (name == null) {
                                    if (!ListenerUtil.mutListener.listen(465)) {
                                        name = segment;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(482) ? ((ListenerUtil.mutListener.listen(476) ? ((ListenerUtil.mutListener.listen(475) ? (owner != null || (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0)))))))) || name != null) : ((ListenerUtil.mutListener.listen(475) ? (owner != null || (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0)))))))) && name != null)) || (ListenerUtil.mutListener.listen(481) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(480) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(479) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(478) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(477) ? (name.length() == 0) : (name.length() > 0))))))) : ((ListenerUtil.mutListener.listen(476) ? ((ListenerUtil.mutListener.listen(475) ? (owner != null || (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0)))))))) || name != null) : ((ListenerUtil.mutListener.listen(475) ? (owner != null || (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(474) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(473) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(472) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(471) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(470) ? (owner.length() == 0) : (owner.length() > 0)))))))) && name != null)) && (ListenerUtil.mutListener.listen(481) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(480) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(479) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(478) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(477) ? (name.length() == 0) : (name.length() > 0))))))))) {
            return InfoUtils.createRepoFromData(owner, name);
        } else {
            return null;
        }
    }

    /**
     * Add issue to store
     *
     * @param repository
     * @param issue
     * @return issue
     */
    public Issue addIssue(Repository repository, Issue issue) {
        Issue current = getIssue(repository, issue.number());
        if (!ListenerUtil.mutListener.listen(484)) {
            if ((ListenerUtil.mutListener.listen(483) ? (current != null || current.equals(issue)) : (current != null && current.equals(issue)))) {
                return current;
            }
        }
        String repoId = InfoUtils.createRepoId(repository);
        ItemReferences<Issue> repoIssues = repos.get(repoId);
        if (!ListenerUtil.mutListener.listen(487)) {
            if (repoIssues == null) {
                if (!ListenerUtil.mutListener.listen(485)) {
                    repoIssues = new ItemReferences<>();
                }
                if (!ListenerUtil.mutListener.listen(486)) {
                    repos.put(repoId, repoIssues);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(488)) {
            repoIssues.put(issue.number(), issue);
        }
        return issue;
    }

    /**
     * Refresh issue.
     *
     * @param repository The issues repository
     * @param issueNumber The issue number
     * @return A {@link Single} representing the  issues
     */
    public Single<Issue> refreshIssue(Repository repository, int issueNumber) {
        return service.getIssue(repository.owner().login(), repository.name(), issueNumber).map(response -> addIssueOrThrow(repository, response, R.string.error_issue_load));
    }

    /**
     * Edit issue.
     *
     * @param repository The issues repository
     * @param issueNumber The issues number to change
     * @return A {@link Single} representing the changed issues
     */
    public Single<Issue> editIssue(Repository repository, int issueNumber, IssueRequest request) {
        return service.editIssue(repository.owner().login(), repository.name(), issueNumber, request).map(response -> addIssueOrThrow(repository, response, R.string.error_edit_issue));
    }

    /**
     * Change the issue state.
     *
     * @param repository The issues repository
     * @param issueNumber The issue number to change
     * @param state What state to change to
     * @return A {@link Single} representing the changed issue
     */
    public Single<Issue> changeState(Repository repository, int issueNumber, IssueState state) {
        IssueRequest editIssue = IssueRequest.builder().state(state).build();
        return service.editIssue(repository.owner().login(), repository.name(), issueNumber, editIssue).map(response -> addIssueOrThrow(repository, response, R.string.error_issue_state));
    }

    /**
     * Adds the issue from the response or throws an error if the request was unsuccessful.
     *
     * @param repository The issues repository
     * @param response The issue response to add
     * @param error String to print if unsuccessful
     * @return The added issue
     */
    private Issue addIssueOrThrow(Repository repository, Response<Issue> response, @StringRes int error) {
        if (response.isSuccessful()) {
            return addIssue(repository, response.body());
        } else {
            if (!ListenerUtil.mutListener.listen(489)) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
            return Issue.builder().build();
        }
    }
}
