package com.github.pockethub.android.markwon;

import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import io.noties.markwon.AbstractMarkwonPlugin;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AsyncDrawableSchedulerPlugin extends AbstractMarkwonPlugin {

    @Override
    public void beforeSetText(@NonNull TextView textView, @NonNull Spanned markdown) {
        if (!ListenerUtil.mutListener.listen(562)) {
            textView.removeOnLayoutChangeListener(this::onLayoutChange);
        }
    }

    @Override
    public void afterSetText(@NonNull TextView textView) {
        if (!ListenerUtil.mutListener.listen(563)) {
            textView.addOnLayoutChangeListener(this::onLayoutChange);
        }
    }

    private void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (!ListenerUtil.mutListener.listen(565)) {
            if (v instanceof TextView) {
                if (!ListenerUtil.mutListener.listen(564)) {
                    io.noties.markwon.image.AsyncDrawableScheduler.schedule((TextView) v);
                }
            }
        }
    }
}
