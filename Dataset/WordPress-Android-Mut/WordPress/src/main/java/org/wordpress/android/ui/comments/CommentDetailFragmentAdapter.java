package org.wordpress.android.ui.comments;

import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.models.CommentList;
import org.wordpress.android.ui.comments.unified.OnLoadMoreListener;
import org.wordpress.android.util.AppLog;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @deprecated
 * Comments are being refactored as part of Comments Unification project. If you are adding any
 * features or modifying this class, please ping develric or klymyam
 */
@Deprecated
public class CommentDetailFragmentAdapter extends FragmentStatePagerAdapter {

    private final SiteModel mSite;

    private final OnLoadMoreListener mOnLoadMoreListener;

    private final CommentList mCommentList;

    CommentDetailFragmentAdapter(FragmentManager fm, CommentList commentList, SiteModel site, OnLoadMoreListener onLoadMoreListener) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mSite = site;
        this.mOnLoadMoreListener = onLoadMoreListener;
        this.mCommentList = commentList;
    }

    @Override
    public Fragment getItem(int position) {
        final CommentModel comment = getComment(position);
        return CommentDetailFragment.newInstance(mSite, comment);
    }

    void onNewItems(CommentList commentList) {
        if (!ListenerUtil.mutListener.listen(4718)) {
            mCommentList.clear();
        }
        if (!ListenerUtil.mutListener.listen(4719)) {
            mCommentList.addAll(commentList);
        }
        if (!ListenerUtil.mutListener.listen(4720)) {
            notifyDataSetChanged();
        }
    }

    boolean isAddingNewComments(CommentList newComments) {
        if (!ListenerUtil.mutListener.listen(4733)) {
            {
                long _loopCounter121 = 0;
                for (int index = 0; (ListenerUtil.mutListener.listen(4732) ? (index >= mCommentList.size()) : (ListenerUtil.mutListener.listen(4731) ? (index <= mCommentList.size()) : (ListenerUtil.mutListener.listen(4730) ? (index > mCommentList.size()) : (ListenerUtil.mutListener.listen(4729) ? (index != mCommentList.size()) : (ListenerUtil.mutListener.listen(4728) ? (index == mCommentList.size()) : (index < mCommentList.size())))))); index++) {
                    ListenerUtil.loopListener.listen("_loopCounter121", ++_loopCounter121);
                    if (!ListenerUtil.mutListener.listen(4727)) {
                        if ((ListenerUtil.mutListener.listen(4726) ? ((ListenerUtil.mutListener.listen(4725) ? (newComments.size() >= index) : (ListenerUtil.mutListener.listen(4724) ? (newComments.size() > index) : (ListenerUtil.mutListener.listen(4723) ? (newComments.size() < index) : (ListenerUtil.mutListener.listen(4722) ? (newComments.size() != index) : (ListenerUtil.mutListener.listen(4721) ? (newComments.size() == index) : (newComments.size() <= index)))))) && mCommentList.get(index).getRemoteCommentId() != newComments.get(index).getRemoteCommentId()) : ((ListenerUtil.mutListener.listen(4725) ? (newComments.size() >= index) : (ListenerUtil.mutListener.listen(4724) ? (newComments.size() > index) : (ListenerUtil.mutListener.listen(4723) ? (newComments.size() < index) : (ListenerUtil.mutListener.listen(4722) ? (newComments.size() != index) : (ListenerUtil.mutListener.listen(4721) ? (newComments.size() == index) : (newComments.size() <= index)))))) || mCommentList.get(index).getRemoteCommentId() != newComments.get(index).getRemoteCommentId()))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    int commentIndex(long commentId) {
        return mCommentList.indexOfCommentId(commentId);
    }

    CommentModel getCommentAtPosition(int position) {
        if (isValidPosition(position)) {
            return mCommentList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return mCommentList.size();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        // https://code.google.com/p/android/issues/detail?id=42601
        try {
            if (!ListenerUtil.mutListener.listen(4735)) {
                AppLog.d(AppLog.T.COMMENTS, "comments pager > adapter restoreState");
            }
            if (!ListenerUtil.mutListener.listen(4736)) {
                super.restoreState(state, loader);
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(4734)) {
                AppLog.e(AppLog.T.COMMENTS, e);
            }
        }
    }

    private CommentModel getComment(int position) {
        if (!ListenerUtil.mutListener.listen(4748)) {
            if ((ListenerUtil.mutListener.listen(4746) ? ((ListenerUtil.mutListener.listen(4745) ? (position >= (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4744) ? (position <= (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4743) ? (position > (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4742) ? (position < (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4741) ? (position != (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1))))))))))) || mOnLoadMoreListener != null) : ((ListenerUtil.mutListener.listen(4745) ? (position >= (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4744) ? (position <= (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4743) ? (position > (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4742) ? (position < (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(4741) ? (position != (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(4740) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(4739) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(4738) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(4737) ? (getCount() + 1) : (getCount() - 1))))))))))) && mOnLoadMoreListener != null))) {
                if (!ListenerUtil.mutListener.listen(4747)) {
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        }
        return mCommentList.get(position);
    }

    private boolean isValidPosition(int position) {
        return ((ListenerUtil.mutListener.listen(4759) ? ((ListenerUtil.mutListener.listen(4753) ? (position <= 0) : (ListenerUtil.mutListener.listen(4752) ? (position > 0) : (ListenerUtil.mutListener.listen(4751) ? (position < 0) : (ListenerUtil.mutListener.listen(4750) ? (position != 0) : (ListenerUtil.mutListener.listen(4749) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(4758) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(4757) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(4756) ? (position > getCount()) : (ListenerUtil.mutListener.listen(4755) ? (position != getCount()) : (ListenerUtil.mutListener.listen(4754) ? (position == getCount()) : (position < getCount()))))))) : ((ListenerUtil.mutListener.listen(4753) ? (position <= 0) : (ListenerUtil.mutListener.listen(4752) ? (position > 0) : (ListenerUtil.mutListener.listen(4751) ? (position < 0) : (ListenerUtil.mutListener.listen(4750) ? (position != 0) : (ListenerUtil.mutListener.listen(4749) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(4758) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(4757) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(4756) ? (position > getCount()) : (ListenerUtil.mutListener.listen(4755) ? (position != getCount()) : (ListenerUtil.mutListener.listen(4754) ? (position == getCount()) : (position < getCount())))))))));
    }
}
