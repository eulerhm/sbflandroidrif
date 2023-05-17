package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CountryFlagsLoader {

    private static final CountryFlagsLoader ourInstance = new CountryFlagsLoader();

    public static CountryFlagsLoader getInstance() {
        return ourInstance;
    }

    private CountryFlagsLoader() {
    }

    public Drawable getFlag(Context context, String countryCode) {
        if (!ListenerUtil.mutListener.listen(4205)) {
            if (countryCode != null) {
                Resources resources = context.getResources();
                final String resourceName = "flag_" + countryCode.toLowerCase();
                final int resourceId = resources.getIdentifier(resourceName, "drawable", context.getPackageName());
                if (!ListenerUtil.mutListener.listen(4204)) {
                    if ((ListenerUtil.mutListener.listen(4203) ? (resourceId >= 0) : (ListenerUtil.mutListener.listen(4202) ? (resourceId <= 0) : (ListenerUtil.mutListener.listen(4201) ? (resourceId > 0) : (ListenerUtil.mutListener.listen(4200) ? (resourceId < 0) : (ListenerUtil.mutListener.listen(4199) ? (resourceId == 0) : (resourceId != 0))))))) {
                        return resources.getDrawable(resourceId);
                    }
                }
            }
        }
        return null;
    }
}
