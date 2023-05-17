package org.wordpress.android.widgets;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * simple implementation of RecyclerView dividers
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpacingHorizontal;

    private final int mSpacingVertical;

    private final boolean mSkipFirstItem;

    public RecyclerItemDecoration(int spacingHorizontal, int spacingVertical) {
        super();
        mSpacingHorizontal = spacingHorizontal;
        mSpacingVertical = spacingVertical;
        mSkipFirstItem = false;
    }

    public RecyclerItemDecoration(int spacingHorizontal, int spacingVertical, boolean skipFirstItem) {
        super();
        mSpacingHorizontal = spacingHorizontal;
        mSpacingVertical = spacingVertical;
        mSkipFirstItem = skipFirstItem;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!ListenerUtil.mutListener.listen(28789)) {
            super.getItemOffsets(outRect, view, parent, state);
        }
        if (!ListenerUtil.mutListener.listen(28791)) {
            if ((ListenerUtil.mutListener.listen(28790) ? (mSkipFirstItem || parent.getChildAdapterPosition(view) == 0) : (mSkipFirstItem && parent.getChildAdapterPosition(view) == 0))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28792)) {
            // left
            outRect.set(// left
            mSpacingHorizontal, // top
            0, // right
            mSpacingHorizontal, // bottom
            mSpacingVertical);
        }
    }
}
