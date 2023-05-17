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
package com.github.pockethub.android.ui.gist;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import android.util.Log;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Gist;
import com.meisolsson.githubsdk.model.User;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.request.CommentRequest;
import com.meisolsson.githubsdk.service.gists.GistCommentService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import static com.github.pockethub.android.Intents.EXTRA_GIST;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to create a comment on a {@link Gist}
 */
public class CreateCommentActivity extends com.github.pockethub.android.ui.comment.CreateCommentActivity {

    /**
     * Create intent to create a comment
     *
     * @param gist
     * @return intent
     */
    public static Intent createIntent(Gist gist) {
        Builder builder = new Builder("gist.comment.create.VIEW");
        if (!ListenerUtil.mutListener.listen(840)) {
            builder.gist(gist);
        }
        return builder.toIntent();
    }

    private Gist gist;

    private String TAG = "CreateCommentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(841)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(842)) {
            setContentView(R.layout.pager_with_tabs);
        }
        if (!ListenerUtil.mutListener.listen(843)) {
            gist = getIntent().getParcelableExtra(EXTRA_GIST);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(844)) {
            actionBar.setTitle(getString(R.string.gist_title) + gist.id());
        }
        User user = gist.owner();
        if (!ListenerUtil.mutListener.listen(846)) {
            if (user != null) {
                if (!ListenerUtil.mutListener.listen(845)) {
                    actionBar.setSubtitle(user.login());
                }
            }
        }
    }

    @Override
    protected void createComment(final String comment) {
        CommentRequest commentRequest = CommentRequest.builder().body(comment).build();
        if (!ListenerUtil.mutListener.listen(847)) {
            ServiceGenerator.createService(this, GistCommentService.class).createGistComment(gist.id(), commentRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(AutoDisposeUtils.bindToLifecycle(this)).subscribe(response -> finish(response.body()), error -> {
                Log.e(TAG, "Exception creating comment on gist", error);
                ToastUtils.show(this, error.getMessage());
            });
        }
    }
}
