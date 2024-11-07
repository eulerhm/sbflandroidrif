package org.wordpress.android.ui.reader.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.models.ReaderBlog;
import org.wordpress.android.models.ReaderBlogList;
import org.wordpress.android.ui.reader.ReaderInterfaces;
import org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener;
import org.wordpress.android.ui.reader.actions.ReaderBlogActions;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.UpdateTask;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.views.ReaderFollowButton;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Locale;
import javax.inject.Inject;
import static android.view.View.VISIBLE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * adapter which shows followed blogs - used by ReaderBlogFragment
 */
public class ReaderBlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;

    public enum ReaderBlogType {

        FOLLOWED
    }

    public interface BlogClickListener {

        void onBlogClicked(Object blog);
    }

    private final ReaderBlogType mBlogType;

    private BlogClickListener mClickListener;

    private ReaderInterfaces.DataLoadedListener mDataLoadedListener;

    private ReaderBlogList mFollowedBlogs = new ReaderBlogList();

    private String mSearchFilter;

    private final String mSource;

    @Inject
    protected ImageManager mImageManager;

    @Inject
    ReaderTracker mReaderTracker;

    public ReaderBlogAdapter(Context context, ReaderBlogType blogType, String searchFilter, String source) {
        super();
        if (!ListenerUtil.mutListener.listen(18243)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(18244)) {
            setHasStableIds(false);
        }
        mBlogType = blogType;
        if (!ListenerUtil.mutListener.listen(18245)) {
            mSearchFilter = searchFilter;
        }
        mSource = source;
    }

    public void setDataLoadedListener(ReaderInterfaces.DataLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(18246)) {
            mDataLoadedListener = listener;
        }
    }

    public void setBlogClickListener(BlogClickListener listener) {
        if (!ListenerUtil.mutListener.listen(18247)) {
            mClickListener = listener;
        }
    }

    public void refresh() {
        if (!ListenerUtil.mutListener.listen(18249)) {
            if (mIsTaskRunning) {
                if (!ListenerUtil.mutListener.listen(18248)) {
                    AppLog.w(T.READER, "load blogs task is already running");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18250)) {
            new LoadBlogsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private ReaderBlogType getBlogType() {
        return mBlogType;
    }

    public boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(18255) ? (getItemCount() >= 0) : (ListenerUtil.mutListener.listen(18254) ? (getItemCount() <= 0) : (ListenerUtil.mutListener.listen(18253) ? (getItemCount() > 0) : (ListenerUtil.mutListener.listen(18252) ? (getItemCount() < 0) : (ListenerUtil.mutListener.listen(18251) ? (getItemCount() != 0) : (getItemCount() == 0)))))));
    }

    @Override
    public int getItemCount() {
        switch(getBlogType()) {
            case FOLLOWED:
                return mFollowedBlogs.size();
            default:
                return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType) {
            case VIEW_TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reader_listitem_blog, parent, false);
                return new BlogViewHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(18272)) {
            if (holder instanceof BlogViewHolder) {
                final BlogViewHolder blogHolder = (BlogViewHolder) holder;
                if (!ListenerUtil.mutListener.listen(18266)) {
                    switch(getBlogType()) {
                        case FOLLOWED:
                            final ReaderBlog blogInfo = mFollowedBlogs.get(position);
                            if (!ListenerUtil.mutListener.listen(18258)) {
                                if (blogInfo.hasName()) {
                                    if (!ListenerUtil.mutListener.listen(18257)) {
                                        blogHolder.mTxtTitle.setText(blogInfo.getName());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(18256)) {
                                        blogHolder.mTxtTitle.setText(R.string.reader_untitled_post);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(18262)) {
                                if (blogInfo.hasUrl()) {
                                    if (!ListenerUtil.mutListener.listen(18261)) {
                                        blogHolder.mTxtUrl.setText(UrlUtils.getHost(blogInfo.getUrl()));
                                    }
                                } else if (blogInfo.hasFeedUrl()) {
                                    if (!ListenerUtil.mutListener.listen(18260)) {
                                        blogHolder.mTxtUrl.setText(UrlUtils.getHost(blogInfo.getFeedUrl()));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(18259)) {
                                        blogHolder.mTxtUrl.setText("");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(18263)) {
                                mImageManager.load(blogHolder.mImgBlog, ImageType.BLAVATAR, blogInfo.getImageUrl());
                            }
                            if (!ListenerUtil.mutListener.listen(18264)) {
                                blogHolder.mFollowButton.setIsFollowed(blogInfo.isFollowing);
                            }
                            if (!ListenerUtil.mutListener.listen(18265)) {
                                blogHolder.mFollowButton.setOnClickListener(v -> toggleFollow(blogHolder.itemView.getContext(), blogHolder.mFollowButton, blogInfo));
                            }
                            break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(18271)) {
                    if (mClickListener != null) {
                        if (!ListenerUtil.mutListener.listen(18270)) {
                            blogHolder.itemView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    int clickedPosition = blogHolder.getAdapterPosition();
                                    if (!ListenerUtil.mutListener.listen(18267)) {
                                        if (clickedPosition == RecyclerView.NO_POSITION) {
                                            return;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(18269)) {
                                        switch(getBlogType()) {
                                            case FOLLOWED:
                                                if (!ListenerUtil.mutListener.listen(18268)) {
                                                    mClickListener.onBlogClicked(mFollowedBlogs.get(clickedPosition));
                                                }
                                                break;
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    /*
     * holder used for followed blogs
     */
    class BlogViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtTitle;

        private final TextView mTxtUrl;

        private final ImageView mImgBlog;

        private final ReaderFollowButton mFollowButton;

        BlogViewHolder(View view) {
            super(view);
            mTxtTitle = view.findViewById(R.id.text_title);
            mTxtUrl = view.findViewById(R.id.text_url);
            mImgBlog = view.findViewById(R.id.image_blog);
            mFollowButton = view.findViewById(R.id.follow_button);
            if (!ListenerUtil.mutListener.listen(18274)) {
                switch(getBlogType()) {
                    case FOLLOWED:
                        if (!ListenerUtil.mutListener.listen(18273)) {
                            mFollowButton.setVisibility(VISIBLE);
                        }
                        break;
                }
            }
        }
    }

    private boolean mIsTaskRunning = false;

    private void toggleFollow(Context context, ReaderFollowButton followButton, ReaderBlog blog) {
        if (!ListenerUtil.mutListener.listen(18275)) {
            if (!NetworkUtils.checkConnection(context)) {
                return;
            }
        }
        final boolean isAskingToFollow = !blog.isFollowing;
        if (!ListenerUtil.mutListener.listen(18276)) {
            // disable follow button until API call returns
            followButton.setEnabled(false);
        }
        final ActionListener listener = succeeded -> {
            followButton.setEnabled(true);
            if (!succeeded) {
                int errResId = isAskingToFollow ? R.string.reader_toast_err_follow_blog : R.string.reader_toast_err_unfollow_blog;
                ToastUtils.showToast(context, errResId);
                followButton.setIsFollowed(!isAskingToFollow);
                blog.isFollowing = !isAskingToFollow;
            } else {
                ReaderUpdateServiceStarter.startService(followButton.getContext(), EnumSet.of(UpdateTask.TAGS));
            }
        };
        final boolean result;
        if (blog.feedId != 0) {
            result = ReaderBlogActions.followFeedById(blog.blogId, blog.feedId, isAskingToFollow, listener, mSource, mReaderTracker);
        } else {
            result = ReaderBlogActions.followBlogById(blog.blogId, blog.feedId, isAskingToFollow, listener, mSource, mReaderTracker);
        }
        if (!ListenerUtil.mutListener.listen(18279)) {
            if (result) {
                if (!ListenerUtil.mutListener.listen(18277)) {
                    followButton.setIsFollowed(isAskingToFollow);
                }
                if (!ListenerUtil.mutListener.listen(18278)) {
                    blog.isFollowing = isAskingToFollow;
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadBlogsTask extends AsyncTask<Void, Void, Boolean> {

        private ReaderBlogList mTmpFollowedBlogs;

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(18280)) {
                mIsTaskRunning = true;
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(18281)) {
                mIsTaskRunning = false;
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            switch(getBlogType()) {
                case FOLLOWED:
                    if (!ListenerUtil.mutListener.listen(18282)) {
                        mTmpFollowedBlogs = new ReaderBlogList();
                    }
                    ReaderBlogList allFollowedBlogs = ReaderBlogTable.getFollowedBlogs();
                    if (!ListenerUtil.mutListener.listen(18288)) {
                        if (hasSearchFilter()) {
                            String query = mSearchFilter.toLowerCase(Locale.getDefault());
                            if (!ListenerUtil.mutListener.listen(18287)) {
                                {
                                    long _loopCounter299 = 0;
                                    for (ReaderBlog blog : allFollowedBlogs) {
                                        ListenerUtil.loopListener.listen("_loopCounter299", ++_loopCounter299);
                                        if (!ListenerUtil.mutListener.listen(18286)) {
                                            if (blog.getName().toLowerCase(Locale.getDefault()).contains(query)) {
                                                if (!ListenerUtil.mutListener.listen(18285)) {
                                                    mTmpFollowedBlogs.add(blog);
                                                }
                                            } else if (UrlUtils.getHost(blog.getUrl()).toLowerCase(Locale.ROOT).contains(query)) {
                                                if (!ListenerUtil.mutListener.listen(18284)) {
                                                    mTmpFollowedBlogs.add(blog);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(18283)) {
                                mTmpFollowedBlogs.addAll(allFollowedBlogs);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(18289)) {
                        // sort followed blogs by name/domain to match display
                        Collections.sort(mTmpFollowedBlogs, new Comparator<ReaderBlog>() {

                            @Override
                            public int compare(ReaderBlog thisBlog, ReaderBlog thatBlog) {
                                String thisName = getBlogNameForComparison(thisBlog);
                                String thatName = getBlogNameForComparison(thatBlog);
                                return thisName.compareToIgnoreCase(thatName);
                            }
                        });
                    }
                    return !mFollowedBlogs.isSameList(mTmpFollowedBlogs);
                default:
                    return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!ListenerUtil.mutListener.listen(18293)) {
                if (result) {
                    if (!ListenerUtil.mutListener.listen(18291)) {
                        switch(getBlogType()) {
                            case FOLLOWED:
                                if (!ListenerUtil.mutListener.listen(18290)) {
                                    mFollowedBlogs = (ReaderBlogList) mTmpFollowedBlogs.clone();
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(18292)) {
                        notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18294)) {
                mIsTaskRunning = false;
            }
            if (!ListenerUtil.mutListener.listen(18296)) {
                if (mDataLoadedListener != null) {
                    if (!ListenerUtil.mutListener.listen(18295)) {
                        mDataLoadedListener.onDataLoaded(isEmpty());
                    }
                }
            }
        }

        private String getBlogNameForComparison(ReaderBlog blog) {
            if (blog == null) {
                return "";
            } else if (blog.hasName()) {
                return blog.getName();
            } else if (blog.hasUrl()) {
                return StringUtils.notNullStr(UrlUtils.getHost(blog.getUrl()));
            } else {
                return "";
            }
        }
    }

    public String getSearchFilter() {
        return mSearchFilter;
    }

    /*
     * filters the list of followed sites - pass null to show all
     */
    public void setSearchFilter(String constraint) {
        if (!ListenerUtil.mutListener.listen(18299)) {
            if (!StringUtils.equals(constraint, mSearchFilter)) {
                if (!ListenerUtil.mutListener.listen(18297)) {
                    mSearchFilter = constraint;
                }
                if (!ListenerUtil.mutListener.listen(18298)) {
                    refresh();
                }
            }
        }
    }

    public boolean hasSearchFilter() {
        return !TextUtils.isEmpty(mSearchFilter);
    }
}
