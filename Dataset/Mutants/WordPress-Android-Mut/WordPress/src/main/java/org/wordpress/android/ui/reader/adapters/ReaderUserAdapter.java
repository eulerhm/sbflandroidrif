package org.wordpress.android.ui.reader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.ReaderUser;
import org.wordpress.android.models.ReaderUserList;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderInterfaces.DataLoadedListener;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * owner must call setUsers() with the list of
 * users to display
 */
public class ReaderUserAdapter extends RecyclerView.Adapter<ReaderUserAdapter.UserViewHolder> {

    @Nullable
    private Boolean mIsFollowed;

    private final ReaderUserList mUsers = new ReaderUserList();

    private DataLoadedListener mDataLoadedListener;

    private final int mAvatarSz;

    @Inject
    ImageManager mImageManager;

    @Inject
    ReaderTracker mReaderTracker;

    public ReaderUserAdapter(Context context) {
        super();
        if (!ListenerUtil.mutListener.listen(19009)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        mAvatarSz = context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_small);
        if (!ListenerUtil.mutListener.listen(19010)) {
            setHasStableIds(true);
        }
    }

    public void setDataLoadedListener(DataLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(19011)) {
            mDataLoadedListener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    private boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(19016) ? (getItemCount() >= 0) : (ListenerUtil.mutListener.listen(19015) ? (getItemCount() <= 0) : (ListenerUtil.mutListener.listen(19014) ? (getItemCount() > 0) : (ListenerUtil.mutListener.listen(19013) ? (getItemCount() < 0) : (ListenerUtil.mutListener.listen(19012) ? (getItemCount() != 0) : (getItemCount() == 0)))))));
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reader_listitem_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final ReaderUser user = mUsers.get(position);
        if (!ListenerUtil.mutListener.listen(19017)) {
            holder.mTxtName.setText(user.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(19029)) {
            if (user.hasUrl()) {
                if (!ListenerUtil.mutListener.listen(19021)) {
                    holder.mTxtUrl.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(19022)) {
                    holder.mTxtUrl.setText(user.getUrlDomain());
                }
                if (!ListenerUtil.mutListener.listen(19028)) {
                    if (user.hasBlogId()) {
                        if (!ListenerUtil.mutListener.listen(19026)) {
                            holder.itemView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(19025)) {
                                        ReaderActivityLauncher.showReaderBlogPreview(v.getContext(), user.blogId, mIsFollowed, ReaderTracker.SOURCE_USER, mReaderTracker);
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(19027)) {
                            holder.mRootView.setEnabled(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(19023)) {
                            holder.itemView.setOnClickListener(null);
                        }
                        if (!ListenerUtil.mutListener.listen(19024)) {
                            holder.mRootView.setEnabled(false);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(19018)) {
                    holder.mRootView.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(19019)) {
                    holder.mTxtUrl.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(19020)) {
                    holder.itemView.setOnClickListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19030)) {
            mImageManager.loadIntoCircle(holder.mImgAvatar, ImageType.AVATAR, GravatarUtils.fixGravatarUrl(user.getAvatarUrl(), mAvatarSz));
        }
    }

    @Override
    public long getItemId(int position) {
        return mUsers.get(position).userId;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtName;

        private final TextView mTxtUrl;

        private final ImageView mImgAvatar;

        private final View mRootView;

        UserViewHolder(View view) {
            super(view);
            mRootView = view;
            mTxtName = view.findViewById(R.id.text_name);
            mTxtUrl = view.findViewById(R.id.text_url);
            mImgAvatar = view.findViewById(R.id.image_avatar);
        }
    }

    public void setIsFollowed(@Nullable Boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(19031)) {
            mIsFollowed = isFollowed;
        }
    }

    public void setUsers(final ReaderUserList users) {
        if (!ListenerUtil.mutListener.listen(19032)) {
            mUsers.clear();
        }
        if (!ListenerUtil.mutListener.listen(19040)) {
            if ((ListenerUtil.mutListener.listen(19038) ? (users != null || (ListenerUtil.mutListener.listen(19037) ? (users.size() >= 0) : (ListenerUtil.mutListener.listen(19036) ? (users.size() <= 0) : (ListenerUtil.mutListener.listen(19035) ? (users.size() < 0) : (ListenerUtil.mutListener.listen(19034) ? (users.size() != 0) : (ListenerUtil.mutListener.listen(19033) ? (users.size() == 0) : (users.size() > 0))))))) : (users != null && (ListenerUtil.mutListener.listen(19037) ? (users.size() >= 0) : (ListenerUtil.mutListener.listen(19036) ? (users.size() <= 0) : (ListenerUtil.mutListener.listen(19035) ? (users.size() < 0) : (ListenerUtil.mutListener.listen(19034) ? (users.size() != 0) : (ListenerUtil.mutListener.listen(19033) ? (users.size() == 0) : (users.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(19039)) {
                    mUsers.addAll(users);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19041)) {
            notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(19043)) {
            if (mDataLoadedListener != null) {
                if (!ListenerUtil.mutListener.listen(19042)) {
                    mDataLoadedListener.onDataLoaded(isEmpty());
                }
            }
        }
    }
}
