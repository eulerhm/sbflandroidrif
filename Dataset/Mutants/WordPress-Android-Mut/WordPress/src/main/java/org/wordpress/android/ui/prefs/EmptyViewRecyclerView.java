package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * RecyclerView with setEmptyView method which displays a view when RecyclerView adapter is empty.
 */
public class EmptyViewRecyclerView extends RecyclerView {

    private View mEmptyView;

    private final AdapterDataObserver mObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            if (!ListenerUtil.mutListener.listen(14737)) {
                toggleEmptyView();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (!ListenerUtil.mutListener.listen(14738)) {
                toggleEmptyView();
            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (!ListenerUtil.mutListener.listen(14739)) {
                toggleEmptyView();
            }
        }
    };

    public EmptyViewRecyclerView(Context context) {
        super(context);
    }

    public EmptyViewRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapterNew) {
        final RecyclerView.Adapter adapterOld = getAdapter();
        if (!ListenerUtil.mutListener.listen(14741)) {
            if (adapterOld != null) {
                if (!ListenerUtil.mutListener.listen(14740)) {
                    adapterOld.unregisterAdapterDataObserver(mObserver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14742)) {
            super.setAdapter(adapterNew);
        }
        if (!ListenerUtil.mutListener.listen(14744)) {
            if (adapterNew != null) {
                if (!ListenerUtil.mutListener.listen(14743)) {
                    adapterNew.registerAdapterDataObserver(mObserver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14745)) {
            toggleEmptyView();
        }
    }

    @Override
    public void swapAdapter(Adapter adapter, boolean removeAndRecycleExistingViews) {
        final RecyclerView.Adapter adapterOld = getAdapter();
        if (!ListenerUtil.mutListener.listen(14747)) {
            if (adapterOld != null) {
                if (!ListenerUtil.mutListener.listen(14746)) {
                    adapterOld.unregisterAdapterDataObserver(mObserver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14748)) {
            super.swapAdapter(adapter, removeAndRecycleExistingViews);
        }
        final RecyclerView.Adapter adapterNew = getAdapter();
        if (!ListenerUtil.mutListener.listen(14750)) {
            if (adapterNew != null) {
                if (!ListenerUtil.mutListener.listen(14749)) {
                    adapterNew.registerAdapterDataObserver(mObserver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14751)) {
            toggleEmptyView();
        }
    }

    public void setEmptyView(View emptyView) {
        if (!ListenerUtil.mutListener.listen(14752)) {
            mEmptyView = emptyView;
        }
        if (!ListenerUtil.mutListener.listen(14753)) {
            toggleEmptyView();
        }
    }

    public void setEmptyViewIfNull(View emptyView) {
        if (!ListenerUtil.mutListener.listen(14754)) {
            if (null != mEmptyView)
                return;
        }
        if (!ListenerUtil.mutListener.listen(14755)) {
            setEmptyView(emptyView);
        }
    }

    private void toggleEmptyView() {
        if (!ListenerUtil.mutListener.listen(14759)) {
            if ((ListenerUtil.mutListener.listen(14756) ? (mEmptyView != null || getAdapter() != null) : (mEmptyView != null && getAdapter() != null))) {
                final boolean empty = getAdapter().getItemCount() == 0;
                if (!ListenerUtil.mutListener.listen(14757)) {
                    mEmptyView.setVisibility(empty ? VISIBLE : GONE);
                }
                if (!ListenerUtil.mutListener.listen(14758)) {
                    this.setVisibility(empty ? GONE : VISIBLE);
                }
            }
        }
    }
}
