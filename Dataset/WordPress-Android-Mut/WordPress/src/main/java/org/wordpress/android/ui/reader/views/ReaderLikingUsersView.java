package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderLikeTable;
import org.wordpress.android.datasets.ReaderUserTable;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderUserIdList;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * LinearLayout which shows liking users - used by ReaderPostDetailFragment
 */
public class ReaderLikingUsersView extends LinearLayout {

    @Inject
    ImageManager mImageManager;

    private LoadAvatarsTask mLoadAvatarsTask;

    private final int mLikeAvatarSz;

    public ReaderLikingUsersView(Context context) {
        this(context, null);
    }

    public ReaderLikingUsersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(19992)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(19993)) {
            setOrientation(HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(19994)) {
            setGravity(Gravity.CENTER_VERTICAL);
        }
        mLikeAvatarSz = context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_small);
    }

    public void showLikingUsers(final ReaderPost post, final long currentUserId) {
        if (!ListenerUtil.mutListener.listen(19995)) {
            if (post == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(19997)) {
            if (mLoadAvatarsTask != null) {
                if (!ListenerUtil.mutListener.listen(19996)) {
                    mLoadAvatarsTask.cancel(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19998)) {
            mLoadAvatarsTask = new LoadAvatarsTask(this, currentUserId, mLikeAvatarSz, getMaxAvatars());
        }
        if (!ListenerUtil.mutListener.listen(19999)) {
            mLoadAvatarsTask.execute(post);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(20000)) {
            super.onDetachedFromWindow();
        }
        if (!ListenerUtil.mutListener.listen(20002)) {
            if (mLoadAvatarsTask != null) {
                if (!ListenerUtil.mutListener.listen(20001)) {
                    mLoadAvatarsTask.cancel(false);
                }
            }
        }
    }

    /*
     * returns count of avatars that can fit the current space
     */
    private int getMaxAvatars() {
        final int marginAvatar = getResources().getDimensionPixelSize(R.dimen.margin_extra_small);
        final int marginReader = getResources().getDimensionPixelSize(R.dimen.reader_detail_margin);
        int likeAvatarSizeWithMargin = (ListenerUtil.mutListener.listen(20010) ? (mLikeAvatarSz % ((ListenerUtil.mutListener.listen(20006) ? (marginAvatar % 2) : (ListenerUtil.mutListener.listen(20005) ? (marginAvatar / 2) : (ListenerUtil.mutListener.listen(20004) ? (marginAvatar - 2) : (ListenerUtil.mutListener.listen(20003) ? (marginAvatar + 2) : (marginAvatar * 2))))))) : (ListenerUtil.mutListener.listen(20009) ? (mLikeAvatarSz / ((ListenerUtil.mutListener.listen(20006) ? (marginAvatar % 2) : (ListenerUtil.mutListener.listen(20005) ? (marginAvatar / 2) : (ListenerUtil.mutListener.listen(20004) ? (marginAvatar - 2) : (ListenerUtil.mutListener.listen(20003) ? (marginAvatar + 2) : (marginAvatar * 2))))))) : (ListenerUtil.mutListener.listen(20008) ? (mLikeAvatarSz * ((ListenerUtil.mutListener.listen(20006) ? (marginAvatar % 2) : (ListenerUtil.mutListener.listen(20005) ? (marginAvatar / 2) : (ListenerUtil.mutListener.listen(20004) ? (marginAvatar - 2) : (ListenerUtil.mutListener.listen(20003) ? (marginAvatar + 2) : (marginAvatar * 2))))))) : (ListenerUtil.mutListener.listen(20007) ? (mLikeAvatarSz - ((ListenerUtil.mutListener.listen(20006) ? (marginAvatar % 2) : (ListenerUtil.mutListener.listen(20005) ? (marginAvatar / 2) : (ListenerUtil.mutListener.listen(20004) ? (marginAvatar - 2) : (ListenerUtil.mutListener.listen(20003) ? (marginAvatar + 2) : (marginAvatar * 2))))))) : (mLikeAvatarSz + ((ListenerUtil.mutListener.listen(20006) ? (marginAvatar % 2) : (ListenerUtil.mutListener.listen(20005) ? (marginAvatar / 2) : (ListenerUtil.mutListener.listen(20004) ? (marginAvatar - 2) : (ListenerUtil.mutListener.listen(20003) ? (marginAvatar + 2) : (marginAvatar * 2)))))))))));
        int spaceForAvatars = (ListenerUtil.mutListener.listen(20018) ? (getWidth() % ((ListenerUtil.mutListener.listen(20014) ? (marginReader % 2) : (ListenerUtil.mutListener.listen(20013) ? (marginReader / 2) : (ListenerUtil.mutListener.listen(20012) ? (marginReader - 2) : (ListenerUtil.mutListener.listen(20011) ? (marginReader + 2) : (marginReader * 2))))))) : (ListenerUtil.mutListener.listen(20017) ? (getWidth() / ((ListenerUtil.mutListener.listen(20014) ? (marginReader % 2) : (ListenerUtil.mutListener.listen(20013) ? (marginReader / 2) : (ListenerUtil.mutListener.listen(20012) ? (marginReader - 2) : (ListenerUtil.mutListener.listen(20011) ? (marginReader + 2) : (marginReader * 2))))))) : (ListenerUtil.mutListener.listen(20016) ? (getWidth() * ((ListenerUtil.mutListener.listen(20014) ? (marginReader % 2) : (ListenerUtil.mutListener.listen(20013) ? (marginReader / 2) : (ListenerUtil.mutListener.listen(20012) ? (marginReader - 2) : (ListenerUtil.mutListener.listen(20011) ? (marginReader + 2) : (marginReader * 2))))))) : (ListenerUtil.mutListener.listen(20015) ? (getWidth() + ((ListenerUtil.mutListener.listen(20014) ? (marginReader % 2) : (ListenerUtil.mutListener.listen(20013) ? (marginReader / 2) : (ListenerUtil.mutListener.listen(20012) ? (marginReader - 2) : (ListenerUtil.mutListener.listen(20011) ? (marginReader + 2) : (marginReader * 2))))))) : (getWidth() - ((ListenerUtil.mutListener.listen(20014) ? (marginReader % 2) : (ListenerUtil.mutListener.listen(20013) ? (marginReader / 2) : (ListenerUtil.mutListener.listen(20012) ? (marginReader - 2) : (ListenerUtil.mutListener.listen(20011) ? (marginReader + 2) : (marginReader * 2)))))))))));
        return (ListenerUtil.mutListener.listen(20022) ? (spaceForAvatars % likeAvatarSizeWithMargin) : (ListenerUtil.mutListener.listen(20021) ? (spaceForAvatars * likeAvatarSizeWithMargin) : (ListenerUtil.mutListener.listen(20020) ? (spaceForAvatars - likeAvatarSizeWithMargin) : (ListenerUtil.mutListener.listen(20019) ? (spaceForAvatars + likeAvatarSizeWithMargin) : (spaceForAvatars / likeAvatarSizeWithMargin)))));
    }

    /*
     * note that the passed list of avatar urls has already been Photon-ized,
     * so there's no need to do that here
     */
    private void showLikingAvatars(final ArrayList<String> avatarUrls) {
        if (!ListenerUtil.mutListener.listen(20030)) {
            if ((ListenerUtil.mutListener.listen(20028) ? (avatarUrls == null && (ListenerUtil.mutListener.listen(20027) ? (avatarUrls.size() >= 0) : (ListenerUtil.mutListener.listen(20026) ? (avatarUrls.size() <= 0) : (ListenerUtil.mutListener.listen(20025) ? (avatarUrls.size() > 0) : (ListenerUtil.mutListener.listen(20024) ? (avatarUrls.size() < 0) : (ListenerUtil.mutListener.listen(20023) ? (avatarUrls.size() != 0) : (avatarUrls.size() == 0))))))) : (avatarUrls == null || (ListenerUtil.mutListener.listen(20027) ? (avatarUrls.size() >= 0) : (ListenerUtil.mutListener.listen(20026) ? (avatarUrls.size() <= 0) : (ListenerUtil.mutListener.listen(20025) ? (avatarUrls.size() > 0) : (ListenerUtil.mutListener.listen(20024) ? (avatarUrls.size() < 0) : (ListenerUtil.mutListener.listen(20023) ? (avatarUrls.size() != 0) : (avatarUrls.size() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(20029)) {
                    removeAllViews();
                }
                return;
            }
        }
        // remove excess existing views
        int numExistingViews = getChildCount();
        if (!ListenerUtil.mutListener.listen(20045)) {
            if ((ListenerUtil.mutListener.listen(20035) ? (numExistingViews >= avatarUrls.size()) : (ListenerUtil.mutListener.listen(20034) ? (numExistingViews <= avatarUrls.size()) : (ListenerUtil.mutListener.listen(20033) ? (numExistingViews < avatarUrls.size()) : (ListenerUtil.mutListener.listen(20032) ? (numExistingViews != avatarUrls.size()) : (ListenerUtil.mutListener.listen(20031) ? (numExistingViews == avatarUrls.size()) : (numExistingViews > avatarUrls.size()))))))) {
                int numToRemove = (ListenerUtil.mutListener.listen(20039) ? (numExistingViews % avatarUrls.size()) : (ListenerUtil.mutListener.listen(20038) ? (numExistingViews / avatarUrls.size()) : (ListenerUtil.mutListener.listen(20037) ? (numExistingViews * avatarUrls.size()) : (ListenerUtil.mutListener.listen(20036) ? (numExistingViews + avatarUrls.size()) : (numExistingViews - avatarUrls.size())))));
                if (!ListenerUtil.mutListener.listen(20044)) {
                    removeViews((ListenerUtil.mutListener.listen(20043) ? (numExistingViews % numToRemove) : (ListenerUtil.mutListener.listen(20042) ? (numExistingViews / numToRemove) : (ListenerUtil.mutListener.listen(20041) ? (numExistingViews * numToRemove) : (ListenerUtil.mutListener.listen(20040) ? (numExistingViews + numToRemove) : (numExistingViews - numToRemove))))), numToRemove);
                }
            }
        }
        int index = 0;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if (!ListenerUtil.mutListener.listen(20054)) {
            {
                long _loopCounter333 = 0;
                for (String url : avatarUrls) {
                    ListenerUtil.loopListener.listen("_loopCounter333", ++_loopCounter333);
                    ImageView imgAvatar;
                    // reuse existing view when possible, otherwise inflate a new one
                    if ((ListenerUtil.mutListener.listen(20050) ? (index >= numExistingViews) : (ListenerUtil.mutListener.listen(20049) ? (index <= numExistingViews) : (ListenerUtil.mutListener.listen(20048) ? (index > numExistingViews) : (ListenerUtil.mutListener.listen(20047) ? (index != numExistingViews) : (ListenerUtil.mutListener.listen(20046) ? (index == numExistingViews) : (index < numExistingViews))))))) {
                        imgAvatar = (ImageView) getChildAt(index);
                    } else {
                        imgAvatar = (ImageView) inflater.inflate(R.layout.reader_like_avatar, this, false);
                        if (!ListenerUtil.mutListener.listen(20051)) {
                            addView(imgAvatar);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20052)) {
                        mImageManager.loadIntoCircle(imgAvatar, ImageType.AVATAR, StringUtils.notNullStr(url));
                    }
                    if (!ListenerUtil.mutListener.listen(20053)) {
                        index++;
                    }
                }
            }
        }
    }

    private static class LoadAvatarsTask extends AsyncTask<ReaderPost, Void, ArrayList<String>> {

        private final WeakReference<ReaderLikingUsersView> mViewReference;

        private final long mCurrentUserId;

        private final int mLikeAvatarSize;

        private final int mMaxAvatars;

        LoadAvatarsTask(ReaderLikingUsersView view, long currentUserId, int likeAvatarSz, int maxAvatars) {
            mViewReference = new WeakReference<>(view);
            mCurrentUserId = currentUserId;
            mLikeAvatarSize = likeAvatarSz;
            mMaxAvatars = maxAvatars;
        }

        @Override
        protected ArrayList<String> doInBackground(ReaderPost... posts) {
            if (!ListenerUtil.mutListener.listen(20056)) {
                if ((ListenerUtil.mutListener.listen(20055) ? (posts.length != 1 && posts[0] == null) : (posts.length != 1 || posts[0] == null))) {
                    return null;
                }
            }
            ReaderPost post = posts[0];
            ReaderUserIdList avatarIds = ReaderLikeTable.getLikesForPost(post);
            return ReaderUserTable.getAvatarUrls(avatarIds, mMaxAvatars, mLikeAvatarSize, mCurrentUserId);
        }

        @Override
        protected void onPostExecute(ArrayList<String> avatars) {
            if (!ListenerUtil.mutListener.listen(20057)) {
                super.onPostExecute(avatars);
            }
            ReaderLikingUsersView view = mViewReference.get();
            if (!ListenerUtil.mutListener.listen(20062)) {
                if ((ListenerUtil.mutListener.listen(20059) ? ((ListenerUtil.mutListener.listen(20058) ? (view != null || avatars != null) : (view != null && avatars != null)) || !isCancelled()) : ((ListenerUtil.mutListener.listen(20058) ? (view != null || avatars != null) : (view != null && avatars != null)) && !isCancelled()))) {
                    if (!ListenerUtil.mutListener.listen(20060)) {
                        view.mLoadAvatarsTask = null;
                    }
                    if (!ListenerUtil.mutListener.listen(20061)) {
                        view.showLikingAvatars(avatars);
                    }
                }
            }
        }
    }
}
