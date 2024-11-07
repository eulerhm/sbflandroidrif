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
package com.github.pockethub.android.ui.comment;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.FragmentPagerAdapter;
import com.meisolsson.githubsdk.model.Repository;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Pager of a raw and rendered comment text
 */
public class CommentPreviewPagerAdapter extends FragmentPagerAdapter {

    private final Repository repo;

    private RawCommentFragment textFragment;

    private RenderedCommentFragment htmlFragment;

    /**
     * Text to populate comment window.
     */
    private String initComment;

    private Context context;

    /**
     * @param activity
     * @param repo
     */
    public CommentPreviewPagerAdapter(AppCompatActivity activity, Repository repo) {
        super(activity);
        if (!ListenerUtil.mutListener.listen(727)) {
            this.context = activity.getApplicationContext();
        }
        this.repo = repo;
    }

    @Override
    public Fragment getItem(final int position) {
        switch(position) {
            case 0:
                if (!ListenerUtil.mutListener.listen(728)) {
                    textFragment = new RawCommentFragment();
                }
                if (!ListenerUtil.mutListener.listen(729)) {
                    textFragment.setText(initComment);
                }
                return textFragment;
            case 1:
                if (!ListenerUtil.mutListener.listen(730)) {
                    htmlFragment = new RenderedCommentFragment();
                }
                return htmlFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Get comment text
     *
     * @return text
     */
    public String getCommentText() {
        if (!ListenerUtil.mutListener.listen(732)) {
            if (textFragment == null) {
                if (!ListenerUtil.mutListener.listen(731)) {
                    textFragment = (RawCommentFragment) getFragmentByPosition(0);
                }
            }
        }
        return textFragment != null ? textFragment.getText() : null;
    }

    /**
     * Set comment text
     *
     * @return text
     */
    public void setCommentText(String comment) {
        if (!ListenerUtil.mutListener.listen(734)) {
            if (textFragment != null) {
                if (!ListenerUtil.mutListener.listen(733)) {
                    textFragment.setText(comment);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(735)) {
            initComment = comment;
        }
    }

    /**
     * Set current item
     *
     * @param position
     * @return this adapter
     */
    public CommentPreviewPagerAdapter setCurrentItem(int position) {
        if (!ListenerUtil.mutListener.listen(743)) {
            if ((ListenerUtil.mutListener.listen(741) ? ((ListenerUtil.mutListener.listen(740) ? (position >= 1) : (ListenerUtil.mutListener.listen(739) ? (position <= 1) : (ListenerUtil.mutListener.listen(738) ? (position > 1) : (ListenerUtil.mutListener.listen(737) ? (position < 1) : (ListenerUtil.mutListener.listen(736) ? (position != 1) : (position == 1)))))) || htmlFragment != null) : ((ListenerUtil.mutListener.listen(740) ? (position >= 1) : (ListenerUtil.mutListener.listen(739) ? (position <= 1) : (ListenerUtil.mutListener.listen(738) ? (position > 1) : (ListenerUtil.mutListener.listen(737) ? (position < 1) : (ListenerUtil.mutListener.listen(736) ? (position != 1) : (position == 1)))))) && htmlFragment != null))) {
                if (!ListenerUtil.mutListener.listen(742)) {
                    htmlFragment.setText(getCommentText(), repo);
                }
            }
        }
        return this;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return context.getResources().getString(R.string.write);
            case 1:
                return context.getResources().getString(R.string.preview);
            default:
                return "";
        }
    }
}
