package net.programmierecke.radiodroid2.utils;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecyclerItemMoveAndSwipeHelper<ViewHolderType extends SwipeableViewHolder> extends RecyclerItemSwipeHelper {

    public interface MoveAndSwipeCallback<ViewHolderType> extends SwipeCallback<ViewHolderType> {

        void onDragged(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, double dX, double dY);

        void onMoved(ViewHolderType viewHolder, int from, int to);

        void onMoveEnded(ViewHolderType viewHolder);
    }

    private MoveAndSwipeCallback<ViewHolderType> moveAndSwipeListener;

    @SuppressWarnings("unchecked")
    public RecyclerItemMoveAndSwipeHelper(Context context, int dragDirs, int swipeDirs, MoveAndSwipeCallback<ViewHolderType> moveAndSwipeListener) {
        super(context, dragDirs, swipeDirs, moveAndSwipeListener);
        if (!ListenerUtil.mutListener.listen(3324)) {
            this.moveAndSwipeListener = moveAndSwipeListener;
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
        @SuppressWarnings("unchecked")
        ViewHolderType viewHolderType = (ViewHolderType) viewHolder;
        if (!ListenerUtil.mutListener.listen(3325)) {
            moveAndSwipeListener.onMoved(viewHolderType, fromPos, toPos);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        @SuppressWarnings("unchecked")
        ViewHolderType viewHolderType = (ViewHolderType) viewHolder;
        if (!ListenerUtil.mutListener.listen(3326)) {
            super.clearView(recyclerView, viewHolder);
        }
        if (!ListenerUtil.mutListener.listen(3327)) {
            moveAndSwipeListener.onMoveEnded(viewHolderType);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (!ListenerUtil.mutListener.listen(3328)) {
            moveAndSwipeListener.onDragged(recyclerView, viewHolder, dX, dY);
        }
        if (!ListenerUtil.mutListener.listen(3329)) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
