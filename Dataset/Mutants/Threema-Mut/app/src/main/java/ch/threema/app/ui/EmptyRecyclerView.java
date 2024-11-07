/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import org.msgpack.core.annotations.Nullable;
import java.lang.ref.WeakReference;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Taken from http://stackoverflow.com/questions/27414173/equivalent-of-listview-setemptyview-in-recyclerview/27801394#27801394
 */
public class EmptyRecyclerView extends RecyclerView {

    private int numHeadersAndFooters = 0;

    private WeakReference<View> emptyViewReference;

    private final AdapterDataObserver observer = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            if (!ListenerUtil.mutListener.listen(45083)) {
                super.onChanged();
            }
            if (!ListenerUtil.mutListener.listen(45084)) {
                checkIfEmpty();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (!ListenerUtil.mutListener.listen(45085)) {
                super.onItemRangeInserted(positionStart, itemCount);
            }
            if (!ListenerUtil.mutListener.listen(45086)) {
                checkIfEmpty();
            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (!ListenerUtil.mutListener.listen(45087)) {
                super.onItemRangeRemoved(positionStart, itemCount);
            }
            if (!ListenerUtil.mutListener.listen(45088)) {
                checkIfEmpty();
            }
        }
    };

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void checkIfEmpty() {
        if (!ListenerUtil.mutListener.listen(45093)) {
            if ((ListenerUtil.mutListener.listen(45090) ? ((ListenerUtil.mutListener.listen(45089) ? (emptyViewReference != null || emptyViewReference.get() != null) : (emptyViewReference != null && emptyViewReference.get() != null)) || getAdapter() != null) : ((ListenerUtil.mutListener.listen(45089) ? (emptyViewReference != null || emptyViewReference.get() != null) : (emptyViewReference != null && emptyViewReference.get() != null)) && getAdapter() != null))) {
                final boolean emptyViewVisible = getAdapter().getItemCount() == numHeadersAndFooters;
                if (!ListenerUtil.mutListener.listen(45091)) {
                    emptyViewReference.get().setVisibility(emptyViewVisible ? VISIBLE : GONE);
                }
                if (!ListenerUtil.mutListener.listen(45092)) {
                    setVisibility(emptyViewVisible ? INVISIBLE : VISIBLE);
                }
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (!ListenerUtil.mutListener.listen(45095)) {
            if (oldAdapter != null) {
                if (!ListenerUtil.mutListener.listen(45094)) {
                    oldAdapter.unregisterAdapterDataObserver(observer);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45097)) {
            if (adapter != null) {
                if (!ListenerUtil.mutListener.listen(45096)) {
                    adapter.registerAdapterDataObserver(observer);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45098)) {
            super.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(45099)) {
            checkIfEmpty();
        }
    }

    public void setEmptyView(View emptyView) {
        if (!ListenerUtil.mutListener.listen(45100)) {
            this.emptyViewReference = new WeakReference<>(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(45101)) {
            checkIfEmpty();
        }
    }

    /**
     *  Specify how many header or footer views this recyclerview has. This number will be considered when determining the "empty" status of the list
     *  @param numHeadersAndFooters Number of headers and / or footers
     */
    public void setNumHeadersAndFooters(int numHeadersAndFooters) {
        if (!ListenerUtil.mutListener.listen(45102)) {
            this.numHeadersAndFooters = numHeadersAndFooters;
        }
        if (!ListenerUtil.mutListener.listen(45103)) {
            checkIfEmpty();
        }
    }

    @Nullable
    public View getEmptyView() {
        return this.emptyViewReference.get();
    }
}
