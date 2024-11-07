package fr.free.nrw.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LayoutUtils {

    /**
     * Can be used for keeping aspect radios suggested by material guidelines. See:
     * https://material.io/design/layout/spacing-methods.html#containers-aspect-ratios
     * In some cases we don't know exact width, for such cases this method measures
     * width and sets height by multiplying the width with height.
     * @param rate Aspect ratios, ie 1 for 1:1. (width * rate = height)
     * @param view view to change height
     */
    public static void setLayoutHeightAllignedToWidth(double rate, View view) {
        ViewTreeObserver vto = view.getViewTreeObserver();
        if (!ListenerUtil.mutListener.listen(2590)) {
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(2583)) {
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(2588)) {
                        layoutParams.height = (int) ((ListenerUtil.mutListener.listen(2587) ? (view.getWidth() % rate) : (ListenerUtil.mutListener.listen(2586) ? (view.getWidth() / rate) : (ListenerUtil.mutListener.listen(2585) ? (view.getWidth() - rate) : (ListenerUtil.mutListener.listen(2584) ? (view.getWidth() + rate) : (view.getWidth() * rate))))));
                    }
                    if (!ListenerUtil.mutListener.listen(2589)) {
                        view.setLayoutParams(layoutParams);
                    }
                }
            });
        }
    }

    public static double getScreenWidth(Context context, double rate) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(2591)) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return (ListenerUtil.mutListener.listen(2595) ? (displayMetrics.widthPixels % rate) : (ListenerUtil.mutListener.listen(2594) ? (displayMetrics.widthPixels / rate) : (ListenerUtil.mutListener.listen(2593) ? (displayMetrics.widthPixels - rate) : (ListenerUtil.mutListener.listen(2592) ? (displayMetrics.widthPixels + rate) : (displayMetrics.widthPixels * rate)))));
    }
}
