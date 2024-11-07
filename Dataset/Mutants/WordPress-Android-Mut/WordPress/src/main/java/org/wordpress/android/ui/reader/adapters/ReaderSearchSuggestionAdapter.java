package org.wordpress.android.ui.reader.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cursoradapter.widget.CursorAdapter;
import com.google.android.material.elevation.ElevationOverlayProvider;
import org.wordpress.android.R;
import org.wordpress.android.datasets.ReaderSearchTable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSearchSuggestionAdapter extends CursorAdapter {

    private static final int MAX_SUGGESTIONS = 5;

    private static final int CLEAR_ALL_ROW_ID = -1;

    private static final int NUM_VIEW_TYPES = 2;

    private static final int VIEW_TYPE_QUERY = 0;

    private static final int VIEW_TYPE_CLEAR = 1;

    private String mCurrentFilter;

    private final Object[] mClearAllRow;

    private final int mClearAllBgColor;

    private final int mSuggestionBgColor;

    private OnSuggestionDeleteClickListener mOnSuggestionDeleteClickListener;

    private OnSuggestionClearClickListener mOnSuggestionClearClickListener;

    public ReaderSearchSuggestionAdapter(Context context) {
        super(context, null, false);
        String clearAllText = context.getString(R.string.label_clear_search_history);
        mClearAllRow = new Object[] { CLEAR_ALL_ROW_ID, clearAllText };
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(context);
        float appbarElevation = context.getResources().getDimension(R.dimen.appbar_elevation);
        int elevatedSurfaceColor = elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(appbarElevation);
        mClearAllBgColor = elevatedSurfaceColor;
        mSuggestionBgColor = elevatedSurfaceColor;
    }

    public synchronized void setFilter(String filter) {
        if (!ListenerUtil.mutListener.listen(18823)) {
            // skip if unchanged
            if ((ListenerUtil.mutListener.listen(18822) ? (isCurrentFilter(filter) || getCursor() != null) : (isCurrentFilter(filter) && getCursor() != null))) {
                return;
            }
        }
        // get db cursor containing matching query strings
        Cursor sqlCursor = ReaderSearchTable.getQueryStringCursor(filter, MAX_SUGGESTIONS);
        // create a MatrixCursor which will be the actual cursor behind this adapter
        MatrixCursor matrixCursor = new MatrixCursor(new String[] { ReaderSearchTable.COL_ID, ReaderSearchTable.COL_QUERY });
        if (!ListenerUtil.mutListener.listen(18827)) {
            if (sqlCursor.moveToFirst()) {
                if (!ListenerUtil.mutListener.listen(18825)) {
                    {
                        long _loopCounter301 = 0;
                        // first populate the matrix from the db cursor...
                        do {
                            ListenerUtil.loopListener.listen("_loopCounter301", ++_loopCounter301);
                            long id = sqlCursor.getLong(sqlCursor.getColumnIndexOrThrow(ReaderSearchTable.COL_ID));
                            String query = sqlCursor.getString(sqlCursor.getColumnIndexOrThrow(ReaderSearchTable.COL_QUERY));
                            if (!ListenerUtil.mutListener.listen(18824)) {
                                matrixCursor.addRow(new Object[] { id, query });
                            }
                        } while (sqlCursor.moveToNext());
                    }
                }
                if (!ListenerUtil.mutListener.listen(18826)) {
                    // ...then add our custom item
                    matrixCursor.addRow(mClearAllRow);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18828)) {
            mCurrentFilter = filter;
        }
        if (!ListenerUtil.mutListener.listen(18829)) {
            swapCursor(matrixCursor);
        }
    }

    /*
     * forces setFilter() to always repopulate by skipping the isCurrentFilter() check
     */
    public synchronized void reload() {
        String newFilter = mCurrentFilter;
        if (!ListenerUtil.mutListener.listen(18830)) {
            mCurrentFilter = null;
        }
        if (!ListenerUtil.mutListener.listen(18831)) {
            setFilter(newFilter);
        }
    }

    private boolean isCurrentFilter(String filter) {
        if (!ListenerUtil.mutListener.listen(18833)) {
            if ((ListenerUtil.mutListener.listen(18832) ? (TextUtils.isEmpty(filter) || TextUtils.isEmpty(mCurrentFilter)) : (TextUtils.isEmpty(filter) && TextUtils.isEmpty(mCurrentFilter)))) {
                return true;
            }
        }
        return (ListenerUtil.mutListener.listen(18834) ? (filter != null || filter.equalsIgnoreCase(mCurrentFilter)) : (filter != null && filter.equalsIgnoreCase(mCurrentFilter)));
    }

    public String getSuggestion(int position) {
        Cursor cursor = (Cursor) getItem(position);
        if (cursor != null) {
            return cursor.getString(cursor.getColumnIndexOrThrow(ReaderSearchTable.COL_QUERY));
        } else {
            return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!ListenerUtil.mutListener.listen(18835)) {
            // as a query row
            if (getItemId(position) == CLEAR_ALL_ROW_ID) {
                return VIEW_TYPE_CLEAR;
            }
        }
        return VIEW_TYPE_QUERY;
    }

    @Override
    public int getViewTypeCount() {
        return NUM_VIEW_TYPES;
    }

    private class SuggestionViewHolder {

        private final TextView mTxtSuggestion;

        private final ImageView mImgDelete;

        SuggestionViewHolder(View view) {
            mTxtSuggestion = view.findViewById(R.id.text_suggestion);
            mImgDelete = view.findViewById(R.id.image_delete);
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.reader_listitem_suggestion, parent, false);
        SuggestionViewHolder holder = new SuggestionViewHolder(view);
        if (!ListenerUtil.mutListener.listen(18836)) {
            view.setTag(holder);
        }
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ReaderSearchTable.COL_ID));
        if (!ListenerUtil.mutListener.listen(18846)) {
            if ((ListenerUtil.mutListener.listen(18841) ? (id >= CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18840) ? (id <= CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18839) ? (id > CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18838) ? (id < CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18837) ? (id != CLEAR_ALL_ROW_ID) : (id == CLEAR_ALL_ROW_ID))))))) {
                if (!ListenerUtil.mutListener.listen(18843)) {
                    view.setBackgroundColor(mClearAllBgColor);
                }
                if (!ListenerUtil.mutListener.listen(18844)) {
                    view.setOnClickListener(v -> {
                        if (mOnSuggestionClearClickListener != null) {
                            mOnSuggestionClearClickListener.onClearClicked();
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(18845)) {
                    holder.mImgDelete.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18842)) {
                    view.setBackgroundColor(mSuggestionBgColor);
                }
            }
        }
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        SuggestionViewHolder holder = (SuggestionViewHolder) view.getTag();
        final String query = cursor.getString(cursor.getColumnIndexOrThrow(ReaderSearchTable.COL_QUERY));
        if (!ListenerUtil.mutListener.listen(18847)) {
            holder.mTxtSuggestion.setText(query);
        }
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ReaderSearchTable.COL_ID));
        if (!ListenerUtil.mutListener.listen(18854)) {
            if ((ListenerUtil.mutListener.listen(18852) ? (id >= CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18851) ? (id <= CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18850) ? (id > CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18849) ? (id < CLEAR_ALL_ROW_ID) : (ListenerUtil.mutListener.listen(18848) ? (id == CLEAR_ALL_ROW_ID) : (id != CLEAR_ALL_ROW_ID))))))) {
                if (!ListenerUtil.mutListener.listen(18853)) {
                    holder.mImgDelete.setOnClickListener(v -> {
                        if (mOnSuggestionDeleteClickListener != null) {
                            mOnSuggestionDeleteClickListener.onDeleteClicked(query);
                        }
                    });
                }
            }
        }
    }

    public void setOnSuggestionDeleteClickListener(OnSuggestionDeleteClickListener onSuggestionDeleteClickListener) {
        if (!ListenerUtil.mutListener.listen(18855)) {
            mOnSuggestionDeleteClickListener = onSuggestionDeleteClickListener;
        }
    }

    public void setOnSuggestionClearClickListener(OnSuggestionClearClickListener onSuggestionClearClickListener) {
        if (!ListenerUtil.mutListener.listen(18856)) {
            mOnSuggestionClearClickListener = onSuggestionClearClickListener;
        }
    }
}
