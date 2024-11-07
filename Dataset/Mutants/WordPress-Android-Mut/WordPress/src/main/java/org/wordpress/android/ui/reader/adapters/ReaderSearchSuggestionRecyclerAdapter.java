package org.wordpress.android.ui.reader.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.datasets.ReaderSearchTable;
import org.wordpress.android.ui.reader.adapters.ReaderSearchSuggestionRecyclerAdapter.SearchSuggestionHolder;
import org.wordpress.android.util.SqlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSearchSuggestionRecyclerAdapter extends RecyclerView.Adapter<SearchSuggestionHolder> {

    private static final int MAX_SUGGESTIONS = 5;

    private static final int MAX_SUGGESTIONS_WHEN_EMPTY = 10;

    private static final int CLEAR_ALL_ROW_ID = -1;

    private Cursor mCursor;

    private String mCurrentQuery;

    private OnSuggestionClickListener mOnSuggestionClickListener;

    private OnSuggestionDeleteClickListener mOnSuggestionDeleteClickListener;

    private OnSuggestionClearClickListener mOnSuggestionClearClickListener;

    public ReaderSearchSuggestionRecyclerAdapter() {
        if (!ListenerUtil.mutListener.listen(18857)) {
            setHasStableIds(true);
        }
        if (!ListenerUtil.mutListener.listen(18858)) {
            swapCursor(null);
        }
    }

    @Override
    @NotNull
    public SearchSuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.reader_listitem_suggestion_recycler, parent, false);
        return new SearchSuggestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull SearchSuggestionHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(18861)) {
            if (isLast(position)) {
                if (!ListenerUtil.mutListener.listen(18860)) {
                    onBindClearAllViewHolder(holder);
                }
            } else if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("couldn't move cursor to position " + position);
            } else {
                if (!ListenerUtil.mutListener.listen(18859)) {
                    onBindSuggestionViewHolder(holder);
                }
            }
        }
    }

    private void onBindClearAllViewHolder(final SearchSuggestionHolder holder) {
        final Context context = holder.itemView.getContext();
        final String text = context.getString(R.string.label_clear_search_history);
        if (!ListenerUtil.mutListener.listen(18862)) {
            holder.mHistoryImageView.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(18863)) {
            holder.mSuggestionTextView.setText(text);
        }
        if (!ListenerUtil.mutListener.listen(18864)) {
            holder.mDeleteImageView.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(18865)) {
            holder.itemView.setOnClickListener(v -> {
                if (mOnSuggestionClearClickListener != null) {
                    mOnSuggestionClearClickListener.onClearClicked();
                }
            });
        }
    }

    private void onBindSuggestionViewHolder(final SearchSuggestionHolder holder) {
        final String query = mCursor.getString(mCursor.getColumnIndexOrThrow(ReaderSearchTable.COL_QUERY));
        if (!ListenerUtil.mutListener.listen(18866)) {
            holder.mHistoryImageView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(18867)) {
            holder.mSuggestionTextView.setText(query);
        }
        if (!ListenerUtil.mutListener.listen(18868)) {
            holder.mDeleteImageView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(18869)) {
            holder.mDeleteImageView.setOnClickListener(v -> {
                if (mOnSuggestionDeleteClickListener != null) {
                    mOnSuggestionDeleteClickListener.onDeleteClicked(query);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(18870)) {
            holder.itemView.setOnClickListener(v -> {
                if (mOnSuggestionClickListener != null) {
                    mOnSuggestionClickListener.onSuggestionClicked(query);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        final int count = mCursor == null ? 0 : mCursor.getCount();
        // we add an extra row at the end to show the "Clear search history" button
        return (ListenerUtil.mutListener.listen(18875) ? (count >= 0) : (ListenerUtil.mutListener.listen(18874) ? (count <= 0) : (ListenerUtil.mutListener.listen(18873) ? (count < 0) : (ListenerUtil.mutListener.listen(18872) ? (count != 0) : (ListenerUtil.mutListener.listen(18871) ? (count == 0) : (count > 0)))))) ? (ListenerUtil.mutListener.listen(18879) ? (count % 1) : (ListenerUtil.mutListener.listen(18878) ? (count / 1) : (ListenerUtil.mutListener.listen(18877) ? (count * 1) : (ListenerUtil.mutListener.listen(18876) ? (count - 1) : (count + 1))))) : 0;
    }

    @Override
    public long getItemId(int position) {
        if (!ListenerUtil.mutListener.listen(18880)) {
            if (isLast(position)) {
                return CLEAR_ALL_ROW_ID;
            } else if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("couldn't move cursor to position " + position);
            }
        }
        return mCursor.getLong(mCursor.getColumnIndexOrThrow(ReaderSearchTable.COL_ID));
    }

    private boolean isLast(final int position) {
        return position == mCursor.getCount();
    }

    public void swapCursor(final Cursor newCursor) {
        if (!ListenerUtil.mutListener.listen(18881)) {
            if (newCursor == mCursor)
                return;
        }
        if (!ListenerUtil.mutListener.listen(18882)) {
            SqlUtils.closeCursor(mCursor);
        }
        if (!ListenerUtil.mutListener.listen(18883)) {
            mCursor = newCursor;
        }
        if (!ListenerUtil.mutListener.listen(18884)) {
            notifyDataSetChanged();
        }
    }

    public void setOnSuggestionClickListener(OnSuggestionClickListener onSuggestionClickListener) {
        if (!ListenerUtil.mutListener.listen(18885)) {
            mOnSuggestionClickListener = onSuggestionClickListener;
        }
    }

    public void setOnSuggestionDeleteClickListener(OnSuggestionDeleteClickListener onSuggestionDeleteClickListener) {
        if (!ListenerUtil.mutListener.listen(18886)) {
            mOnSuggestionDeleteClickListener = onSuggestionDeleteClickListener;
        }
    }

    public void setOnSuggestionClearClickListener(OnSuggestionClearClickListener onSuggestionClearClickListener) {
        if (!ListenerUtil.mutListener.listen(18887)) {
            mOnSuggestionClearClickListener = onSuggestionClearClickListener;
        }
    }

    public void reload() {
        if (!ListenerUtil.mutListener.listen(18888)) {
            setQuery(mCurrentQuery, true);
        }
    }

    public void setQuery(final String newQuery) {
        if (!ListenerUtil.mutListener.listen(18889)) {
            setQuery(newQuery, false);
        }
    }

    private void setQuery(final String newQuery, final boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(18893)) {
            if ((ListenerUtil.mutListener.listen(18892) ? ((ListenerUtil.mutListener.listen(18891) ? ((ListenerUtil.mutListener.listen(18890) ? (!forceUpdate || newQuery != null) : (!forceUpdate && newQuery != null)) || newQuery.equalsIgnoreCase(mCurrentQuery)) : ((ListenerUtil.mutListener.listen(18890) ? (!forceUpdate || newQuery != null) : (!forceUpdate && newQuery != null)) && newQuery.equalsIgnoreCase(mCurrentQuery))) || mCursor != null) : ((ListenerUtil.mutListener.listen(18891) ? ((ListenerUtil.mutListener.listen(18890) ? (!forceUpdate || newQuery != null) : (!forceUpdate && newQuery != null)) || newQuery.equalsIgnoreCase(mCurrentQuery)) : ((ListenerUtil.mutListener.listen(18890) ? (!forceUpdate || newQuery != null) : (!forceUpdate && newQuery != null)) && newQuery.equalsIgnoreCase(mCurrentQuery))) && mCursor != null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18894)) {
            mCurrentQuery = newQuery;
        }
        final int maxSuggestions = newQuery == null ? MAX_SUGGESTIONS_WHEN_EMPTY : MAX_SUGGESTIONS;
        if (!ListenerUtil.mutListener.listen(18895)) {
            swapCursor(ReaderSearchTable.getQueryStringCursor(newQuery, maxSuggestions));
        }
    }

    class SearchSuggestionHolder extends RecyclerView.ViewHolder {

        private final ImageView mHistoryImageView;

        private final TextView mSuggestionTextView;

        private final ImageView mDeleteImageView;

        SearchSuggestionHolder(final View itemView) {
            super(itemView);
            mHistoryImageView = itemView.findViewById(R.id.image_history);
            mSuggestionTextView = itemView.findViewById(R.id.text_suggestion);
            mDeleteImageView = itemView.findViewById(R.id.image_delete);
        }
    }
}
