package fr.free.nrw.commons.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created by Ilgaz Er on 8/7/2018.
 */
public class HeightLimitedRecyclerView extends RecyclerView {

    int height;

    public HeightLimitedRecyclerView(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(287)) {
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        if (!ListenerUtil.mutListener.listen(288)) {
            height = displayMetrics.heightPixels;
        }
    }

    public HeightLimitedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(289)) {
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        if (!ListenerUtil.mutListener.listen(290)) {
            height = displayMetrics.heightPixels;
        }
    }

    public HeightLimitedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(291)) {
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        if (!ListenerUtil.mutListener.listen(292)) {
            height = displayMetrics.heightPixels;
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (!ListenerUtil.mutListener.listen(297)) {
            heightSpec = MeasureSpec.makeMeasureSpec((int) ((ListenerUtil.mutListener.listen(296) ? (height % 0.3) : (ListenerUtil.mutListener.listen(295) ? (height / 0.3) : (ListenerUtil.mutListener.listen(294) ? (height - 0.3) : (ListenerUtil.mutListener.listen(293) ? (height + 0.3) : (height * 0.3)))))), MeasureSpec.AT_MOST);
        }
        if (!ListenerUtil.mutListener.listen(298)) {
            super.onMeasure(widthSpec, heightSpec);
        }
    }
}
