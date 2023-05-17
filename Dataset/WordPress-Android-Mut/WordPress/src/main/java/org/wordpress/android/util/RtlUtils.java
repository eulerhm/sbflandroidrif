package org.wordpress.android.util;

import android.content.Context;
import android.content.res.Configuration;
import androidx.core.view.ViewCompat;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RtlUtils {

    private Context mContext;

    @Inject
    public RtlUtils(Context context) {
        if (!ListenerUtil.mutListener.listen(27779)) {
            mContext = context;
        }
    }

    public static boolean isRtl(Context ctx) {
        Configuration configuration = ctx.getResources().getConfiguration();
        return configuration.getLayoutDirection() == ViewCompat.LAYOUT_DIRECTION_RTL;
    }

    public boolean isRtl() {
        return isRtl(mContext);
    }
}
