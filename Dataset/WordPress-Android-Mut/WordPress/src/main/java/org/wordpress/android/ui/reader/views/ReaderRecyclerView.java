package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderRecyclerView extends RecyclerView {

    public ReaderRecyclerView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(20114)) {
            initialize(context);
        }
    }

    public ReaderRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(20115)) {
            initialize(context);
        }
    }

    public ReaderRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(20116)) {
            initialize(context);
        }
    }

    private void initialize(Context context) {
        if (!ListenerUtil.mutListener.listen(20118)) {
            if (!isInEditMode()) {
                if (!ListenerUtil.mutListener.listen(20117)) {
                    setLayoutManager(new LinearLayoutManager(context));
                }
            }
        }
    }
}
