package org.wordpress.android.ui.reader;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.appbar.AppBarLayout;
import org.wordpress.android.R;
import org.wordpress.android.datasets.ReaderCommentTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderUserTable;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderUserList;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.reader.adapters.ReaderUserAdapter;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.ui.reader.views.ReaderRecyclerView;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.widgets.RecyclerItemDecoration;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * displays a list of users who like a specific reader post
 */
public class ReaderUserListActivity extends LocaleAwareActivity {

    private ReaderRecyclerView mRecyclerView;

    private ReaderUserAdapter mAdapter;

    private AppBarLayout mAppBarLayout;

    private int mRestorePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22660)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22661)) {
            setContentView(R.layout.reader_activity_userlist);
        }
        if (!ListenerUtil.mutListener.listen(22662)) {
            setTitle(null);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(22666)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(22663)) {
                    setSupportActionBar(toolbar);
                }
                if (!ListenerUtil.mutListener.listen(22665)) {
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(22664)) {
                                onBackPressed();
                            }
                        }
                    });
                }
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(22669)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(22667)) {
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(22668)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22671)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(22670)) {
                    mRestorePosition = savedInstanceState.getInt(ReaderConstants.KEY_RESTORE_POSITION);
                }
            }
        }
        int spacingHorizontal = 0;
        int spacingVertical = DisplayUtils.dpToPx(this, 1);
        if (!ListenerUtil.mutListener.listen(22672)) {
            mRecyclerView = findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(22673)) {
            mRecyclerView.addItemDecoration(new RecyclerItemDecoration(spacingHorizontal, spacingVertical));
        }
        if (!ListenerUtil.mutListener.listen(22674)) {
            mAppBarLayout = findViewById(R.id.appbar_main);
        }
        long blogId = getIntent().getLongExtra(ReaderConstants.ARG_BLOG_ID, 0);
        long postId = getIntent().getLongExtra(ReaderConstants.ARG_POST_ID, 0);
        long commentId = getIntent().getLongExtra(ReaderConstants.ARG_COMMENT_ID, 0);
        if (!ListenerUtil.mutListener.listen(22675)) {
            loadUsers(blogId, postId, commentId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int position = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (!ListenerUtil.mutListener.listen(22682)) {
            if ((ListenerUtil.mutListener.listen(22680) ? (position >= 0) : (ListenerUtil.mutListener.listen(22679) ? (position <= 0) : (ListenerUtil.mutListener.listen(22678) ? (position < 0) : (ListenerUtil.mutListener.listen(22677) ? (position != 0) : (ListenerUtil.mutListener.listen(22676) ? (position == 0) : (position > 0))))))) {
                if (!ListenerUtil.mutListener.listen(22681)) {
                    outState.putInt(ReaderConstants.KEY_RESTORE_POSITION, position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22683)) {
            super.onSaveInstanceState(outState);
        }
    }

    private ReaderUserAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(22697)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(22684)) {
                    mAdapter = new ReaderUserAdapter(this);
                }
                if (!ListenerUtil.mutListener.listen(22695)) {
                    mAdapter.setDataLoadedListener(new ReaderInterfaces.DataLoadedListener() {

                        @Override
                        public void onDataLoaded(boolean isEmpty) {
                            if (!ListenerUtil.mutListener.listen(22693)) {
                                if ((ListenerUtil.mutListener.listen(22690) ? (!isEmpty || (ListenerUtil.mutListener.listen(22689) ? (mRestorePosition >= 0) : (ListenerUtil.mutListener.listen(22688) ? (mRestorePosition <= 0) : (ListenerUtil.mutListener.listen(22687) ? (mRestorePosition < 0) : (ListenerUtil.mutListener.listen(22686) ? (mRestorePosition != 0) : (ListenerUtil.mutListener.listen(22685) ? (mRestorePosition == 0) : (mRestorePosition > 0))))))) : (!isEmpty && (ListenerUtil.mutListener.listen(22689) ? (mRestorePosition >= 0) : (ListenerUtil.mutListener.listen(22688) ? (mRestorePosition <= 0) : (ListenerUtil.mutListener.listen(22687) ? (mRestorePosition < 0) : (ListenerUtil.mutListener.listen(22686) ? (mRestorePosition != 0) : (ListenerUtil.mutListener.listen(22685) ? (mRestorePosition == 0) : (mRestorePosition > 0))))))))) {
                                    if (!ListenerUtil.mutListener.listen(22691)) {
                                        mRecyclerView.scrollToPosition(mRestorePosition);
                                    }
                                    if (!ListenerUtil.mutListener.listen(22692)) {
                                        mAppBarLayout.post(mAppBarLayout::requestLayout);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(22694)) {
                                mRestorePosition = 0;
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(22696)) {
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        }
        return mAdapter;
    }

    private void loadUsers(final long blogId, final long postId, final long commentId) {
        if (!ListenerUtil.mutListener.listen(22709)) {
            new Thread() {

                @Override
                public void run() {
                    final String title = getTitleString(blogId, postId, commentId);
                    final ReaderUserList users;
                    if ((ListenerUtil.mutListener.listen(22702) ? (commentId >= 0) : (ListenerUtil.mutListener.listen(22701) ? (commentId <= 0) : (ListenerUtil.mutListener.listen(22700) ? (commentId > 0) : (ListenerUtil.mutListener.listen(22699) ? (commentId < 0) : (ListenerUtil.mutListener.listen(22698) ? (commentId != 0) : (commentId == 0))))))) {
                        // commentId is empty (not passed), so we're showing users who like a post
                        users = ReaderUserTable.getUsersWhoLikePost(blogId, postId, ReaderConstants.READER_MAX_USERS_TO_DISPLAY);
                    } else {
                        // commentId is non-empty, so we're showing users who like a comment
                        users = ReaderUserTable.getUsersWhoLikeComment(blogId, commentId, ReaderConstants.READER_MAX_USERS_TO_DISPLAY);
                    }
                    if (!ListenerUtil.mutListener.listen(22708)) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(22707)) {
                                    if (!isFinishing()) {
                                        if (!ListenerUtil.mutListener.listen(22703)) {
                                            setTitle(title);
                                        }
                                        ReaderPost post = ReaderPostTable.getBlogPost(blogId, postId, true);
                                        if (!ListenerUtil.mutListener.listen(22705)) {
                                            if (post != null) {
                                                if (!ListenerUtil.mutListener.listen(22704)) {
                                                    getAdapter().setIsFollowed(post.isFollowedByCurrentUser);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(22706)) {
                                            getAdapter().setUsers(users);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }.start();
        }
    }

    private String getTitleString(final long blogId, final long postId, final long commentId) {
        final int numLikes;
        final boolean isLikedByCurrentUser;
        if ((ListenerUtil.mutListener.listen(22714) ? (commentId >= 0) : (ListenerUtil.mutListener.listen(22713) ? (commentId <= 0) : (ListenerUtil.mutListener.listen(22712) ? (commentId > 0) : (ListenerUtil.mutListener.listen(22711) ? (commentId < 0) : (ListenerUtil.mutListener.listen(22710) ? (commentId != 0) : (commentId == 0))))))) {
            numLikes = ReaderPostTable.getNumLikesForPost(blogId, postId);
            isLikedByCurrentUser = ReaderPostTable.isPostLikedByCurrentUser(blogId, postId);
        } else {
            numLikes = ReaderCommentTable.getNumLikesForComment(blogId, postId, commentId);
            isLikedByCurrentUser = ReaderCommentTable.isCommentLikedByCurrentUser(blogId, postId, commentId);
        }
        return ReaderUtils.getLongLikeLabelText(this, numLikes, isLikedByCurrentUser);
    }
}
