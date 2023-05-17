package org.wordpress.android.ui.reader.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.ui.reader.ReaderInterfaces;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderTagActions;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderTagAdapter extends RecyclerView.Adapter<ReaderTagAdapter.TagViewHolder> {

    public interface TagDeletedListener {

        void onTagDeleted(ReaderTag tag);
    }

    @Inject
    AccountStore mAccountStore;

    private final WeakReference<Context> mWeakContext;

    private final ReaderTagList mTags = new ReaderTagList();

    private TagDeletedListener mTagDeletedListener;

    private ReaderInterfaces.DataLoadedListener mDataLoadedListener;

    public ReaderTagAdapter(Context context) {
        super();
        if (!ListenerUtil.mutListener.listen(18966)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(18967)) {
            setHasStableIds(true);
        }
        mWeakContext = new WeakReference<>(context);
    }

    public void setTagDeletedListener(TagDeletedListener listener) {
        if (!ListenerUtil.mutListener.listen(18968)) {
            mTagDeletedListener = listener;
        }
    }

    public void setDataLoadedListener(ReaderInterfaces.DataLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(18969)) {
            mDataLoadedListener = listener;
        }
    }

    private boolean hasContext() {
        return (getContext() != null);
    }

    private Context getContext() {
        return mWeakContext.get();
    }

    public void refresh() {
        if (!ListenerUtil.mutListener.listen(18971)) {
            if (mIsTaskRunning) {
                if (!ListenerUtil.mutListener.listen(18970)) {
                    AppLog.w(T.READER, "tag task is already running");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18972)) {
            new LoadTagsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(18977) ? (getItemCount() >= 0) : (ListenerUtil.mutListener.listen(18976) ? (getItemCount() <= 0) : (ListenerUtil.mutListener.listen(18975) ? (getItemCount() > 0) : (ListenerUtil.mutListener.listen(18974) ? (getItemCount() < 0) : (ListenerUtil.mutListener.listen(18973) ? (getItemCount() != 0) : (getItemCount() == 0)))))));
    }

    @Override
    public long getItemId(int position) {
        return mTags.get(position).getTagSlug().hashCode();
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reader_listitem_tag, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        final ReaderTag tag = mTags.get(position);
        if (!ListenerUtil.mutListener.listen(18978)) {
            holder.mTxtTagName.setText(tag.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(18980)) {
            holder.mBtnRemove.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(18979)) {
                        performDeleteTag(tag);
                    }
                }
            });
        }
    }

    private void performDeleteTag(@NonNull ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(18981)) {
            if (!NetworkUtils.checkConnection(getContext())) {
                return;
            }
        }
        ReaderActions.ActionListener actionListener = new ReaderActions.ActionListener() {

            @Override
            public void onActionResult(boolean succeeded) {
                if (!ListenerUtil.mutListener.listen(18985)) {
                    if ((ListenerUtil.mutListener.listen(18982) ? (!succeeded || hasContext()) : (!succeeded && hasContext()))) {
                        if (!ListenerUtil.mutListener.listen(18983)) {
                            ToastUtils.showToast(getContext(), R.string.reader_toast_err_remove_tag);
                        }
                        if (!ListenerUtil.mutListener.listen(18984)) {
                            refresh();
                        }
                    }
                }
            }
        };
        boolean success = ReaderTagActions.deleteTag(tag, actionListener, mAccountStore.hasAccessToken());
        if (!ListenerUtil.mutListener.listen(18996)) {
            if (success) {
                int index = mTags.indexOfTagName(tag.getTagSlug());
                if (!ListenerUtil.mutListener.listen(18993)) {
                    if ((ListenerUtil.mutListener.listen(18990) ? (index >= -1) : (ListenerUtil.mutListener.listen(18989) ? (index <= -1) : (ListenerUtil.mutListener.listen(18988) ? (index < -1) : (ListenerUtil.mutListener.listen(18987) ? (index != -1) : (ListenerUtil.mutListener.listen(18986) ? (index == -1) : (index > -1))))))) {
                        if (!ListenerUtil.mutListener.listen(18991)) {
                            mTags.remove(index);
                        }
                        if (!ListenerUtil.mutListener.listen(18992)) {
                            notifyItemRemoved(index);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(18995)) {
                    if (mTagDeletedListener != null) {
                        if (!ListenerUtil.mutListener.listen(18994)) {
                            mTagDeletedListener.onTagDeleted(tag);
                        }
                    }
                }
            }
        }
    }

    class TagViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtTagName;

        private final ImageButton mBtnRemove;

        TagViewHolder(View view) {
            super(view);
            mTxtTagName = (TextView) view.findViewById(R.id.text_topic);
            mBtnRemove = (ImageButton) view.findViewById(R.id.btn_remove);
            if (!ListenerUtil.mutListener.listen(18997)) {
                ReaderUtils.setBackgroundToRoundRipple(mBtnRemove);
            }
            if (!ListenerUtil.mutListener.listen(18998)) {
                ViewExtensionsKt.expandTouchTargetArea(mBtnRemove, R.dimen.reader_remove_button_extra_padding, false);
            }
        }
    }

    /*
     * AsyncTask to load tags
     */
    private boolean mIsTaskRunning = false;

    @SuppressLint("StaticFieldLeak")
    private class LoadTagsTask extends AsyncTask<Void, Void, ReaderTagList> {

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(18999)) {
                mIsTaskRunning = true;
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(19000)) {
                mIsTaskRunning = false;
            }
        }

        @Override
        protected ReaderTagList doInBackground(Void... params) {
            return ReaderTagTable.getFollowedTags();
        }

        @Override
        protected void onPostExecute(ReaderTagList tagList) {
            if (!ListenerUtil.mutListener.listen(19005)) {
                if ((ListenerUtil.mutListener.listen(19001) ? (tagList != null || !tagList.isSameList(mTags)) : (tagList != null && !tagList.isSameList(mTags)))) {
                    if (!ListenerUtil.mutListener.listen(19002)) {
                        mTags.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(19003)) {
                        mTags.addAll(tagList);
                    }
                    if (!ListenerUtil.mutListener.listen(19004)) {
                        notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(19006)) {
                mIsTaskRunning = false;
            }
            if (!ListenerUtil.mutListener.listen(19008)) {
                if (mDataLoadedListener != null) {
                    if (!ListenerUtil.mutListener.listen(19007)) {
                        mDataLoadedListener.onDataLoaded(isEmpty());
                    }
                }
            }
        }
    }
}
