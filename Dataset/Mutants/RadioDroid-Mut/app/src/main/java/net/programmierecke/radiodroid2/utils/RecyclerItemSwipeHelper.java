package net.programmierecke.radiodroid2.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecyclerItemSwipeHelper<ViewHolderType extends SwipeableViewHolder> extends ItemTouchHelper.SimpleCallback {

    public interface SwipeCallback<ViewHolderType> {

        void onSwiped(ViewHolderType viewHolder, int direction);
    }

    private SwipeCallback<ViewHolderType> swipeListener;

    private boolean swipeToDeleteIsEnabled;

    private IconicsDrawable icon;

    private final ColorDrawable background;

    public RecyclerItemSwipeHelper(Context context, int dragDirs, int swipeDirs, SwipeCallback<ViewHolderType> swipeListener) {
        super(dragDirs, swipeDirs);
        if (!ListenerUtil.mutListener.listen(3330)) {
            this.swipeListener = swipeListener;
        }
        if (!ListenerUtil.mutListener.listen(3342)) {
            swipeToDeleteIsEnabled = (ListenerUtil.mutListener.listen(3341) ? (((ListenerUtil.mutListener.listen(3335) ? ((swipeDirs & ItemTouchHelper.LEFT) >= 0) : (ListenerUtil.mutListener.listen(3334) ? ((swipeDirs & ItemTouchHelper.LEFT) <= 0) : (ListenerUtil.mutListener.listen(3333) ? ((swipeDirs & ItemTouchHelper.LEFT) < 0) : (ListenerUtil.mutListener.listen(3332) ? ((swipeDirs & ItemTouchHelper.LEFT) != 0) : (ListenerUtil.mutListener.listen(3331) ? ((swipeDirs & ItemTouchHelper.LEFT) == 0) : ((swipeDirs & ItemTouchHelper.LEFT) > 0))))))) && ((ListenerUtil.mutListener.listen(3340) ? ((swipeDirs & ItemTouchHelper.RIGHT) >= 0) : (ListenerUtil.mutListener.listen(3339) ? ((swipeDirs & ItemTouchHelper.RIGHT) <= 0) : (ListenerUtil.mutListener.listen(3338) ? ((swipeDirs & ItemTouchHelper.RIGHT) < 0) : (ListenerUtil.mutListener.listen(3337) ? ((swipeDirs & ItemTouchHelper.RIGHT) != 0) : (ListenerUtil.mutListener.listen(3336) ? ((swipeDirs & ItemTouchHelper.RIGHT) == 0) : ((swipeDirs & ItemTouchHelper.RIGHT) > 0)))))))) : (((ListenerUtil.mutListener.listen(3335) ? ((swipeDirs & ItemTouchHelper.LEFT) >= 0) : (ListenerUtil.mutListener.listen(3334) ? ((swipeDirs & ItemTouchHelper.LEFT) <= 0) : (ListenerUtil.mutListener.listen(3333) ? ((swipeDirs & ItemTouchHelper.LEFT) < 0) : (ListenerUtil.mutListener.listen(3332) ? ((swipeDirs & ItemTouchHelper.LEFT) != 0) : (ListenerUtil.mutListener.listen(3331) ? ((swipeDirs & ItemTouchHelper.LEFT) == 0) : ((swipeDirs & ItemTouchHelper.LEFT) > 0))))))) || ((ListenerUtil.mutListener.listen(3340) ? ((swipeDirs & ItemTouchHelper.RIGHT) >= 0) : (ListenerUtil.mutListener.listen(3339) ? ((swipeDirs & ItemTouchHelper.RIGHT) <= 0) : (ListenerUtil.mutListener.listen(3338) ? ((swipeDirs & ItemTouchHelper.RIGHT) < 0) : (ListenerUtil.mutListener.listen(3337) ? ((swipeDirs & ItemTouchHelper.RIGHT) != 0) : (ListenerUtil.mutListener.listen(3336) ? ((swipeDirs & ItemTouchHelper.RIGHT) == 0) : ((swipeDirs & ItemTouchHelper.RIGHT) > 0)))))))));
        }
        background = new ColorDrawable(Utils.themeAttributeToColor(R.attr.swipeDeleteBackgroundColor, context, Color.RED));
        if (!ListenerUtil.mutListener.listen(3344)) {
            if (swipeToDeleteIsEnabled) {
                if (!ListenerUtil.mutListener.listen(3343)) {
                    icon = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_delete_sweep).size(IconicsSize.dp(48)).color(IconicsColor.colorInt(Utils.themeAttributeToColor(R.attr.swipeDeleteIconColor, context, Color.WHITE)));
                }
            }
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (!ListenerUtil.mutListener.listen(3346)) {
            if (viewHolder != null) {
                final View foregroundView = ((SwipeableViewHolder) viewHolder).getForegroundView();
                if (!ListenerUtil.mutListener.listen(3345)) {
                    getDefaultUIUtil().onSelected(foregroundView);
                }
            }
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((SwipeableViewHolder) viewHolder).getForegroundView();
        if (!ListenerUtil.mutListener.listen(3347)) {
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((SwipeableViewHolder) viewHolder).getForegroundView();
        if (!ListenerUtil.mutListener.listen(3348)) {
            getDefaultUIUtil().clearView(foregroundView);
        }
    }

    private void drawSwipeToDeleteBackground(Canvas c, View itemView, float dX, float dY) {
        int backgroundCornerOffset = 20;
        int iconMargin = (ListenerUtil.mutListener.listen(3356) ? (((ListenerUtil.mutListener.listen(3352) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3351) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3350) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3349) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) % 2) : (ListenerUtil.mutListener.listen(3355) ? (((ListenerUtil.mutListener.listen(3352) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3351) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3350) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3349) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) * 2) : (ListenerUtil.mutListener.listen(3354) ? (((ListenerUtil.mutListener.listen(3352) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3351) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3350) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3349) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) - 2) : (ListenerUtil.mutListener.listen(3353) ? (((ListenerUtil.mutListener.listen(3352) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3351) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3350) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3349) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) + 2) : (((ListenerUtil.mutListener.listen(3352) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3351) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3350) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3349) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) / 2)))));
        int iconTop = itemView.getTop() + (ListenerUtil.mutListener.listen(3364) ? (((ListenerUtil.mutListener.listen(3360) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3359) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3358) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3357) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) % 2) : (ListenerUtil.mutListener.listen(3363) ? (((ListenerUtil.mutListener.listen(3360) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3359) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3358) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3357) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) * 2) : (ListenerUtil.mutListener.listen(3362) ? (((ListenerUtil.mutListener.listen(3360) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3359) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3358) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3357) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) - 2) : (ListenerUtil.mutListener.listen(3361) ? (((ListenerUtil.mutListener.listen(3360) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3359) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3358) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3357) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) + 2) : (((ListenerUtil.mutListener.listen(3360) ? (itemView.getHeight() % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3359) ? (itemView.getHeight() / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3358) ? (itemView.getHeight() * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(3357) ? (itemView.getHeight() + icon.getIntrinsicHeight()) : (itemView.getHeight() - icon.getIntrinsicHeight())))))) / 2)))));
        int iconBottom = iconTop + icon.getIntrinsicHeight();
        if (!ListenerUtil.mutListener.listen(3431)) {
            if ((ListenerUtil.mutListener.listen(3369) ? (dX >= 0) : (ListenerUtil.mutListener.listen(3368) ? (dX <= 0) : (ListenerUtil.mutListener.listen(3367) ? (dX < 0) : (ListenerUtil.mutListener.listen(3366) ? (dX != 0) : (ListenerUtil.mutListener.listen(3365) ? (dX == 0) : (dX > 0))))))) {
                // Swiping to the right
                int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
                int iconLeft = itemView.getLeft() + iconMargin;
                int magicConstraint = ((ListenerUtil.mutListener.listen(3414) ? (itemView.getLeft() + ((int) dX) >= (ListenerUtil.mutListener.listen(3409) ? (iconRight % iconMargin) : (ListenerUtil.mutListener.listen(3408) ? (iconRight / iconMargin) : (ListenerUtil.mutListener.listen(3407) ? (iconRight * iconMargin) : (ListenerUtil.mutListener.listen(3406) ? (iconRight - iconMargin) : (iconRight + iconMargin)))))) : (ListenerUtil.mutListener.listen(3413) ? (itemView.getLeft() + ((int) dX) <= (ListenerUtil.mutListener.listen(3409) ? (iconRight % iconMargin) : (ListenerUtil.mutListener.listen(3408) ? (iconRight / iconMargin) : (ListenerUtil.mutListener.listen(3407) ? (iconRight * iconMargin) : (ListenerUtil.mutListener.listen(3406) ? (iconRight - iconMargin) : (iconRight + iconMargin)))))) : (ListenerUtil.mutListener.listen(3412) ? (itemView.getLeft() + ((int) dX) > (ListenerUtil.mutListener.listen(3409) ? (iconRight % iconMargin) : (ListenerUtil.mutListener.listen(3408) ? (iconRight / iconMargin) : (ListenerUtil.mutListener.listen(3407) ? (iconRight * iconMargin) : (ListenerUtil.mutListener.listen(3406) ? (iconRight - iconMargin) : (iconRight + iconMargin)))))) : (ListenerUtil.mutListener.listen(3411) ? (itemView.getLeft() + ((int) dX) != (ListenerUtil.mutListener.listen(3409) ? (iconRight % iconMargin) : (ListenerUtil.mutListener.listen(3408) ? (iconRight / iconMargin) : (ListenerUtil.mutListener.listen(3407) ? (iconRight * iconMargin) : (ListenerUtil.mutListener.listen(3406) ? (iconRight - iconMargin) : (iconRight + iconMargin)))))) : (ListenerUtil.mutListener.listen(3410) ? (itemView.getLeft() + ((int) dX) == (ListenerUtil.mutListener.listen(3409) ? (iconRight % iconMargin) : (ListenerUtil.mutListener.listen(3408) ? (iconRight / iconMargin) : (ListenerUtil.mutListener.listen(3407) ? (iconRight * iconMargin) : (ListenerUtil.mutListener.listen(3406) ? (iconRight - iconMargin) : (iconRight + iconMargin)))))) : (itemView.getLeft() + ((int) dX) < (ListenerUtil.mutListener.listen(3409) ? (iconRight % iconMargin) : (ListenerUtil.mutListener.listen(3408) ? (iconRight / iconMargin) : (ListenerUtil.mutListener.listen(3407) ? (iconRight * iconMargin) : (ListenerUtil.mutListener.listen(3406) ? (iconRight - iconMargin) : (iconRight + iconMargin)))))))))))) ? (ListenerUtil.mutListener.listen(3426) ? ((ListenerUtil.mutListener.listen(3418) ? ((int) dX % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3417) ? ((int) dX / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3416) ? ((int) dX * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3415) ? ((int) dX + icon.getIntrinsicWidth()) : ((int) dX - icon.getIntrinsicWidth()))))) % ((ListenerUtil.mutListener.listen(3422) ? (iconMargin % 2) : (ListenerUtil.mutListener.listen(3421) ? (iconMargin / 2) : (ListenerUtil.mutListener.listen(3420) ? (iconMargin - 2) : (ListenerUtil.mutListener.listen(3419) ? (iconMargin + 2) : (iconMargin * 2))))))) : (ListenerUtil.mutListener.listen(3425) ? ((ListenerUtil.mutListener.listen(3418) ? ((int) dX % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3417) ? ((int) dX / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3416) ? ((int) dX * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3415) ? ((int) dX + icon.getIntrinsicWidth()) : ((int) dX - icon.getIntrinsicWidth()))))) / ((ListenerUtil.mutListener.listen(3422) ? (iconMargin % 2) : (ListenerUtil.mutListener.listen(3421) ? (iconMargin / 2) : (ListenerUtil.mutListener.listen(3420) ? (iconMargin - 2) : (ListenerUtil.mutListener.listen(3419) ? (iconMargin + 2) : (iconMargin * 2))))))) : (ListenerUtil.mutListener.listen(3424) ? ((ListenerUtil.mutListener.listen(3418) ? ((int) dX % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3417) ? ((int) dX / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3416) ? ((int) dX * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3415) ? ((int) dX + icon.getIntrinsicWidth()) : ((int) dX - icon.getIntrinsicWidth()))))) * ((ListenerUtil.mutListener.listen(3422) ? (iconMargin % 2) : (ListenerUtil.mutListener.listen(3421) ? (iconMargin / 2) : (ListenerUtil.mutListener.listen(3420) ? (iconMargin - 2) : (ListenerUtil.mutListener.listen(3419) ? (iconMargin + 2) : (iconMargin * 2))))))) : (ListenerUtil.mutListener.listen(3423) ? ((ListenerUtil.mutListener.listen(3418) ? ((int) dX % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3417) ? ((int) dX / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3416) ? ((int) dX * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3415) ? ((int) dX + icon.getIntrinsicWidth()) : ((int) dX - icon.getIntrinsicWidth()))))) + ((ListenerUtil.mutListener.listen(3422) ? (iconMargin % 2) : (ListenerUtil.mutListener.listen(3421) ? (iconMargin / 2) : (ListenerUtil.mutListener.listen(3420) ? (iconMargin - 2) : (ListenerUtil.mutListener.listen(3419) ? (iconMargin + 2) : (iconMargin * 2))))))) : ((ListenerUtil.mutListener.listen(3418) ? ((int) dX % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3417) ? ((int) dX / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3416) ? ((int) dX * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3415) ? ((int) dX + icon.getIntrinsicWidth()) : ((int) dX - icon.getIntrinsicWidth()))))) - ((ListenerUtil.mutListener.listen(3422) ? (iconMargin % 2) : (ListenerUtil.mutListener.listen(3421) ? (iconMargin / 2) : (ListenerUtil.mutListener.listen(3420) ? (iconMargin - 2) : (ListenerUtil.mutListener.listen(3419) ? (iconMargin + 2) : (iconMargin * 2))))))))))) : 0;
                if (!ListenerUtil.mutListener.listen(3427)) {
                    iconLeft += magicConstraint;
                }
                if (!ListenerUtil.mutListener.listen(3428)) {
                    iconRight += magicConstraint;
                }
                if (!ListenerUtil.mutListener.listen(3429)) {
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                }
                if (!ListenerUtil.mutListener.listen(3430)) {
                    background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX), itemView.getBottom());
                }
            } else if ((ListenerUtil.mutListener.listen(3374) ? (dX >= 0) : (ListenerUtil.mutListener.listen(3373) ? (dX <= 0) : (ListenerUtil.mutListener.listen(3372) ? (dX > 0) : (ListenerUtil.mutListener.listen(3371) ? (dX != 0) : (ListenerUtil.mutListener.listen(3370) ? (dX == 0) : (dX < 0))))))) {
                // Swiping to the left
                int iconRight = (ListenerUtil.mutListener.listen(3380) ? (itemView.getRight() % iconMargin) : (ListenerUtil.mutListener.listen(3379) ? (itemView.getRight() / iconMargin) : (ListenerUtil.mutListener.listen(3378) ? (itemView.getRight() * iconMargin) : (ListenerUtil.mutListener.listen(3377) ? (itemView.getRight() + iconMargin) : (itemView.getRight() - iconMargin)))));
                int iconLeft = (ListenerUtil.mutListener.listen(3388) ? ((ListenerUtil.mutListener.listen(3384) ? (itemView.getRight() % iconMargin) : (ListenerUtil.mutListener.listen(3383) ? (itemView.getRight() / iconMargin) : (ListenerUtil.mutListener.listen(3382) ? (itemView.getRight() * iconMargin) : (ListenerUtil.mutListener.listen(3381) ? (itemView.getRight() + iconMargin) : (itemView.getRight() - iconMargin))))) % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3387) ? ((ListenerUtil.mutListener.listen(3384) ? (itemView.getRight() % iconMargin) : (ListenerUtil.mutListener.listen(3383) ? (itemView.getRight() / iconMargin) : (ListenerUtil.mutListener.listen(3382) ? (itemView.getRight() * iconMargin) : (ListenerUtil.mutListener.listen(3381) ? (itemView.getRight() + iconMargin) : (itemView.getRight() - iconMargin))))) / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3386) ? ((ListenerUtil.mutListener.listen(3384) ? (itemView.getRight() % iconMargin) : (ListenerUtil.mutListener.listen(3383) ? (itemView.getRight() / iconMargin) : (ListenerUtil.mutListener.listen(3382) ? (itemView.getRight() * iconMargin) : (ListenerUtil.mutListener.listen(3381) ? (itemView.getRight() + iconMargin) : (itemView.getRight() - iconMargin))))) * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(3385) ? ((ListenerUtil.mutListener.listen(3384) ? (itemView.getRight() % iconMargin) : (ListenerUtil.mutListener.listen(3383) ? (itemView.getRight() / iconMargin) : (ListenerUtil.mutListener.listen(3382) ? (itemView.getRight() * iconMargin) : (ListenerUtil.mutListener.listen(3381) ? (itemView.getRight() + iconMargin) : (itemView.getRight() - iconMargin))))) + icon.getIntrinsicWidth()) : ((ListenerUtil.mutListener.listen(3384) ? (itemView.getRight() % iconMargin) : (ListenerUtil.mutListener.listen(3383) ? (itemView.getRight() / iconMargin) : (ListenerUtil.mutListener.listen(3382) ? (itemView.getRight() * iconMargin) : (ListenerUtil.mutListener.listen(3381) ? (itemView.getRight() + iconMargin) : (itemView.getRight() - iconMargin))))) - icon.getIntrinsicWidth())))));
                int magicConstraint = ((ListenerUtil.mutListener.listen(3397) ? (itemView.getRight() + ((int) dX) >= (ListenerUtil.mutListener.listen(3392) ? (iconLeft % iconMargin) : (ListenerUtil.mutListener.listen(3391) ? (iconLeft / iconMargin) : (ListenerUtil.mutListener.listen(3390) ? (iconLeft * iconMargin) : (ListenerUtil.mutListener.listen(3389) ? (iconLeft + iconMargin) : (iconLeft - iconMargin)))))) : (ListenerUtil.mutListener.listen(3396) ? (itemView.getRight() + ((int) dX) <= (ListenerUtil.mutListener.listen(3392) ? (iconLeft % iconMargin) : (ListenerUtil.mutListener.listen(3391) ? (iconLeft / iconMargin) : (ListenerUtil.mutListener.listen(3390) ? (iconLeft * iconMargin) : (ListenerUtil.mutListener.listen(3389) ? (iconLeft + iconMargin) : (iconLeft - iconMargin)))))) : (ListenerUtil.mutListener.listen(3395) ? (itemView.getRight() + ((int) dX) < (ListenerUtil.mutListener.listen(3392) ? (iconLeft % iconMargin) : (ListenerUtil.mutListener.listen(3391) ? (iconLeft / iconMargin) : (ListenerUtil.mutListener.listen(3390) ? (iconLeft * iconMargin) : (ListenerUtil.mutListener.listen(3389) ? (iconLeft + iconMargin) : (iconLeft - iconMargin)))))) : (ListenerUtil.mutListener.listen(3394) ? (itemView.getRight() + ((int) dX) != (ListenerUtil.mutListener.listen(3392) ? (iconLeft % iconMargin) : (ListenerUtil.mutListener.listen(3391) ? (iconLeft / iconMargin) : (ListenerUtil.mutListener.listen(3390) ? (iconLeft * iconMargin) : (ListenerUtil.mutListener.listen(3389) ? (iconLeft + iconMargin) : (iconLeft - iconMargin)))))) : (ListenerUtil.mutListener.listen(3393) ? (itemView.getRight() + ((int) dX) == (ListenerUtil.mutListener.listen(3392) ? (iconLeft % iconMargin) : (ListenerUtil.mutListener.listen(3391) ? (iconLeft / iconMargin) : (ListenerUtil.mutListener.listen(3390) ? (iconLeft * iconMargin) : (ListenerUtil.mutListener.listen(3389) ? (iconLeft + iconMargin) : (iconLeft - iconMargin)))))) : (itemView.getRight() + ((int) dX) > (ListenerUtil.mutListener.listen(3392) ? (iconLeft % iconMargin) : (ListenerUtil.mutListener.listen(3391) ? (iconLeft / iconMargin) : (ListenerUtil.mutListener.listen(3390) ? (iconLeft * iconMargin) : (ListenerUtil.mutListener.listen(3389) ? (iconLeft + iconMargin) : (iconLeft - iconMargin)))))))))))) ? icon.getIntrinsicWidth() + ((ListenerUtil.mutListener.listen(3401) ? (iconMargin % 2) : (ListenerUtil.mutListener.listen(3400) ? (iconMargin / 2) : (ListenerUtil.mutListener.listen(3399) ? (iconMargin - 2) : (ListenerUtil.mutListener.listen(3398) ? (iconMargin + 2) : (iconMargin * 2)))))) + (int) dX : 0;
                if (!ListenerUtil.mutListener.listen(3402)) {
                    iconLeft += magicConstraint;
                }
                if (!ListenerUtil.mutListener.listen(3403)) {
                    iconRight += magicConstraint;
                }
                if (!ListenerUtil.mutListener.listen(3404)) {
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                }
                if (!ListenerUtil.mutListener.listen(3405)) {
                    background.setBounds(itemView.getRight(), itemView.getTop(), itemView.getRight() + ((int) dX), itemView.getBottom());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3375)) {
                    // view is unSwiped
                    icon.setBounds(0, 0, 0, 0);
                }
                if (!ListenerUtil.mutListener.listen(3376)) {
                    background.setBounds(0, 0, 0, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3432)) {
            background.draw(c);
        }
        if (!ListenerUtil.mutListener.listen(3433)) {
            icon.draw(c);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((SwipeableViewHolder) viewHolder).getForegroundView();
        if (!ListenerUtil.mutListener.listen(3435)) {
            if (swipeToDeleteIsEnabled) {
                if (!ListenerUtil.mutListener.listen(3434)) {
                    drawSwipeToDeleteBackground(c, viewHolder.itemView, dX, dY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3436)) {
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        @SuppressWarnings("unchecked")
        ViewHolderType viewHolderType = (ViewHolderType) viewHolder;
        if (!ListenerUtil.mutListener.listen(3437)) {
            swipeListener.onSwiped(viewHolderType, direction);
        }
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        // Effectively disable flinging because it's too easy to accidentally perform it.
        return 1;
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        // Since flinging is disabled we reduce swipe threshold.
        return 0.35f;
    }
}
