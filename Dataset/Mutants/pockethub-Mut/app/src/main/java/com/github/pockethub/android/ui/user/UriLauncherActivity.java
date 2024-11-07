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
package com.github.pockethub.android.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.afollestad.materialdialogs.MaterialDialog;
import com.meisolsson.githubsdk.model.Gist;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.commit.CommitMatch;
import com.github.pockethub.android.core.commit.CommitUriMatcher;
import com.github.pockethub.android.core.gist.GistUriMatcher;
import com.github.pockethub.android.core.issue.IssueUriMatcher;
import com.github.pockethub.android.core.repo.RepositoryUriMatcher;
import com.github.pockethub.android.core.user.UserUriMatcher;
import com.github.pockethub.android.ui.commit.CommitViewActivity;
import com.github.pockethub.android.ui.gist.GistsViewActivity;
import com.github.pockethub.android.ui.issue.IssuesViewActivity;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import java.net.URI;
import java.text.MessageFormat;
import static android.content.Intent.ACTION_VIEW;
import static android.content.Intent.CATEGORY_BROWSABLE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to launch other activities based on the intent's data {@link URI}
 */
public class UriLauncherActivity extends Activity {

    private static final String HOST_DEFAULT = "github.com";

    private static final String HOST_GISTS = "gist.github.com";

    private static final String PROTOCOL_HTTPS = "https";

    public static void launchUri(Context context, Uri data) {
        Intent intent = getIntentForURI(data);
        if (!ListenerUtil.mutListener.listen(1143)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(1142)) {
                    context.startActivity(intent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1141)) {
                    context.startActivity(new Intent(ACTION_VIEW, data).addCategory(CATEGORY_BROWSABLE));
                }
            }
        }
    }

    /**
     * Convert global view intent one into one that can be possibly opened
     * inside the current application.
     *
     * @param intent
     * @return converted intent or null if non-application specific
     */
    public static Intent convert(final Intent intent) {
        if (!ListenerUtil.mutListener.listen(1144)) {
            if (intent == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(1145)) {
            if (!ACTION_VIEW.equals(intent.getAction())) {
                return null;
            }
        }
        Uri data = intent.getData();
        if (!ListenerUtil.mutListener.listen(1146)) {
            if (data == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(1158)) {
            if ((ListenerUtil.mutListener.listen(1147) ? (TextUtils.isEmpty(data.getHost()) && TextUtils.isEmpty(data.getScheme())) : (TextUtils.isEmpty(data.getHost()) || TextUtils.isEmpty(data.getScheme())))) {
                String host = data.getHost();
                if (!ListenerUtil.mutListener.listen(1149)) {
                    if (TextUtils.isEmpty(host)) {
                        if (!ListenerUtil.mutListener.listen(1148)) {
                            host = HOST_DEFAULT;
                        }
                    }
                }
                String scheme = data.getScheme();
                if (!ListenerUtil.mutListener.listen(1151)) {
                    if (TextUtils.isEmpty(scheme)) {
                        if (!ListenerUtil.mutListener.listen(1150)) {
                            scheme = PROTOCOL_HTTPS;
                        }
                    }
                }
                String prefix = scheme + "://" + host;
                String path = data.getPath();
                if (!ListenerUtil.mutListener.listen(1156)) {
                    if (!TextUtils.isEmpty(path)) {
                        if (!ListenerUtil.mutListener.listen(1155)) {
                            if (path.charAt(0) == '/') {
                                if (!ListenerUtil.mutListener.listen(1154)) {
                                    data = Uri.parse(prefix + path);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1153)) {
                                    data = Uri.parse(prefix + '/' + path);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1152)) {
                            data = Uri.parse(prefix);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1157)) {
                    intent.setData(data);
                }
            }
        }
        return getIntentForURI(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1159)) {
            super.onCreate(savedInstanceState);
        }
        final Intent intent = getIntent();
        final Uri data = intent.getData();
        final Intent newIntent = getIntentForURI(data);
        if (!ListenerUtil.mutListener.listen(1162)) {
            if (newIntent != null) {
                if (!ListenerUtil.mutListener.listen(1160)) {
                    startActivity(newIntent);
                }
                if (!ListenerUtil.mutListener.listen(1161)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1166)) {
            if (!intent.hasCategory(CATEGORY_BROWSABLE)) {
                if (!ListenerUtil.mutListener.listen(1164)) {
                    startActivity(new Intent(ACTION_VIEW, data).addCategory(CATEGORY_BROWSABLE));
                }
                if (!ListenerUtil.mutListener.listen(1165)) {
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1163)) {
                    showParseError(data.toString());
                }
            }
        }
    }

    private static Intent getIntentForURI(Uri data) {
        if (!ListenerUtil.mutListener.listen(1172)) {
            if (HOST_GISTS.equals(data.getHost())) {
                Gist gist = GistUriMatcher.getGist(data);
                if (!ListenerUtil.mutListener.listen(1171)) {
                    if (gist != null) {
                        return GistsViewActivity.Companion.createIntent(gist);
                    }
                }
            } else if (HOST_DEFAULT.equals(data.getHost())) {
                CommitMatch commit = CommitUriMatcher.getCommit(data);
                if (!ListenerUtil.mutListener.listen(1167)) {
                    if (commit != null) {
                        return CommitViewActivity.Companion.createIntent(commit.getRepository(), commit.getCommit());
                    }
                }
                Issue issue = IssueUriMatcher.getIssue(data);
                if (!ListenerUtil.mutListener.listen(1168)) {
                    if (issue != null) {
                        return IssuesViewActivity.Companion.createIntent(issue, issue.repository());
                    }
                }
                Repository repository = RepositoryUriMatcher.getRepository(data);
                if (!ListenerUtil.mutListener.listen(1169)) {
                    if (repository != null) {
                        return RepositoryViewActivity.Companion.createIntent(repository);
                    }
                }
                User user = UserUriMatcher.getUser(data);
                if (!ListenerUtil.mutListener.listen(1170)) {
                    if (user != null) {
                        return UserViewActivity.Companion.createIntent(user);
                    }
                }
            }
        }
        return null;
    }

    private void showParseError(String url) {
        if (!ListenerUtil.mutListener.listen(1173)) {
            new MaterialDialog.Builder(this).title(R.string.title_invalid_github_url).content(MessageFormat.format(getString(R.string.message_invalid_github_url), url)).cancelListener(dialog -> finish()).positiveText(android.R.string.ok).onPositive((dialog, which) -> finish()).show();
        }
    }
}
