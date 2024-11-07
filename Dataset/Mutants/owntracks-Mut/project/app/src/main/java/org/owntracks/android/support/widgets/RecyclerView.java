package org.owntracks.android.support.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Recycler view that dynamically shows/hides an empty placeholder view
public class RecyclerView extends androidx.recyclerview.widget.RecyclerView {

    private View emptyView;

    private final AdapterDataObserver emptyObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (!ListenerUtil.mutListener.listen(1057)) {
                if ((ListenerUtil.mutListener.listen(1051) ? (adapter != null || emptyView != null) : (adapter != null && emptyView != null))) {
                    if (!ListenerUtil.mutListener.listen(1056)) {
                        if (adapter.getItemCount() == 0) {
                            if (!ListenerUtil.mutListener.listen(1054)) {
                                emptyView.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(1055)) {
                                RecyclerView.this.setVisibility(View.GONE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1052)) {
                                emptyView.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(1053)) {
                                RecyclerView.this.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }
        }
    };

    public RecyclerView(Context context) {
        super(context);
    }

    public RecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!ListenerUtil.mutListener.listen(1058)) {
            super.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(1060)) {
            if (adapter != null) {
                if (!ListenerUtil.mutListener.listen(1059)) {
                    adapter.registerAdapterDataObserver(emptyObserver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1061)) {
            emptyObserver.onChanged();
        }
    }

    public void setEmptyView(View emptyView) {
        if (!ListenerUtil.mutListener.listen(1062)) {
            this.emptyView = emptyView;
        }
    }
}
