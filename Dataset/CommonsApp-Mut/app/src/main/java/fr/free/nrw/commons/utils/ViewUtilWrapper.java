package fr.free.nrw.commons.utils;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class ViewUtilWrapper {

    @Inject
    public ViewUtilWrapper() {
    }

    public void showShortToast(Context context, String text) {
        if (!ListenerUtil.mutListener.listen(2080)) {
            ViewUtil.showShortToast(context, text);
        }
    }

    public void showLongToast(Context context, String text) {
        if (!ListenerUtil.mutListener.listen(2081)) {
            ViewUtil.showLongToast(context, text);
        }
    }
}
