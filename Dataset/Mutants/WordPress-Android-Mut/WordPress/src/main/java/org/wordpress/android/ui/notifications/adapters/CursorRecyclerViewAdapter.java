package org.wordpress.android.ui.notifications.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class CursorRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Cursor mCursor;

    private boolean mDataValid;

    private int mRowIdColumn;

    private DataSetObserver mDataSetObserver;

    public CursorRecyclerViewAdapter(Context context, Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(8058)) {
            mCursor = cursor;
        }
        if (!ListenerUtil.mutListener.listen(8059)) {
            mDataValid = cursor != null;
        }
        if (!ListenerUtil.mutListener.listen(8060)) {
            mRowIdColumn = mDataValid ? mCursor.getColumnIndex("_id") : -1;
        }
        if (!ListenerUtil.mutListener.listen(8061)) {
            mDataSetObserver = new NotifyingDataSetObserver();
        }
        if (!ListenerUtil.mutListener.listen(8063)) {
            if (mCursor != null) {
                if (!ListenerUtil.mutListener.listen(8062)) {
                    mCursor.registerDataSetObserver(mDataSetObserver);
                }
            }
        }
    }

    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public int getItemCount() {
        if (!ListenerUtil.mutListener.listen(8065)) {
            if ((ListenerUtil.mutListener.listen(8064) ? (mDataValid || mCursor != null) : (mDataValid && mCursor != null))) {
                return mCursor.getCount();
            }
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (!ListenerUtil.mutListener.listen(8068)) {
            if ((ListenerUtil.mutListener.listen(8067) ? ((ListenerUtil.mutListener.listen(8066) ? (mDataValid || mCursor != null) : (mDataValid && mCursor != null)) || mCursor.moveToPosition(position)) : ((ListenerUtil.mutListener.listen(8066) ? (mDataValid || mCursor != null) : (mDataValid && mCursor != null)) && mCursor.moveToPosition(position)))) {
                return mCursor.getLong(mRowIdColumn);
            }
        }
        return 0;
    }

    public abstract void onBindViewHolder(VH viewHolder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if (!ListenerUtil.mutListener.listen(8069)) {
            if (!mDataValid) {
                throw new IllegalStateException("this should only be called when the cursor is valid");
            }
        }
        if (!ListenerUtil.mutListener.listen(8070)) {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("couldn't move cursor to position " + position);
            }
        }
        if (!ListenerUtil.mutListener.listen(8071)) {
            onBindViewHolder(viewHolder, mCursor);
        }
    }

    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.
     */
    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (!ListenerUtil.mutListener.listen(8073)) {
            if (old != null) {
                if (!ListenerUtil.mutListener.listen(8072)) {
                    old.close();
                }
            }
        }
    }

    /**
     * Swap in a new Cursor, returning the old Cursor. Unlike
     * {@link #changeCursor(Cursor)}, the returned old Cursor is <em>not</em>
     * closed.
     */
    private Cursor swapCursor(Cursor newCursor) {
        if (!ListenerUtil.mutListener.listen(8074)) {
            if (newCursor == mCursor) {
                return null;
            }
        }
        final Cursor oldCursor = mCursor;
        if (!ListenerUtil.mutListener.listen(8077)) {
            if ((ListenerUtil.mutListener.listen(8075) ? (oldCursor != null || mDataSetObserver != null) : (oldCursor != null && mDataSetObserver != null))) {
                if (!ListenerUtil.mutListener.listen(8076)) {
                    oldCursor.unregisterDataSetObserver(mDataSetObserver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8078)) {
            mCursor = newCursor;
        }
        if (!ListenerUtil.mutListener.listen(8087)) {
            if (mCursor != null) {
                if (!ListenerUtil.mutListener.listen(8083)) {
                    if (mDataSetObserver != null) {
                        if (!ListenerUtil.mutListener.listen(8082)) {
                            mCursor.registerDataSetObserver(mDataSetObserver);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8084)) {
                    mRowIdColumn = newCursor.getColumnIndexOrThrow("_id");
                }
                if (!ListenerUtil.mutListener.listen(8085)) {
                    mDataValid = true;
                }
                if (!ListenerUtil.mutListener.listen(8086)) {
                    notifyDataSetChanged();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8079)) {
                    mRowIdColumn = -1;
                }
                if (!ListenerUtil.mutListener.listen(8080)) {
                    mDataValid = false;
                }
                if (!ListenerUtil.mutListener.listen(8081)) {
                    notifyDataSetChanged();
                }
            }
        }
        return oldCursor;
    }

    private class NotifyingDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            if (!ListenerUtil.mutListener.listen(8088)) {
                super.onChanged();
            }
            if (!ListenerUtil.mutListener.listen(8089)) {
                mDataValid = true;
            }
            if (!ListenerUtil.mutListener.listen(8090)) {
                notifyDataSetChanged();
            }
        }

        @Override
        public void onInvalidated() {
            if (!ListenerUtil.mutListener.listen(8091)) {
                super.onInvalidated();
            }
            if (!ListenerUtil.mutListener.listen(8092)) {
                mDataValid = false;
            }
            if (!ListenerUtil.mutListener.listen(8093)) {
                notifyDataSetChanged();
            }
        }
    }
}
