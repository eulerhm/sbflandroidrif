package org.wordpress.android.util;

import android.content.Context;
import android.content.pm.ShortcutManager;
import android.os.Build.VERSION_CODES;
import org.wordpress.android.ui.Shortcut;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ShortcutUtils {

    private final Context mContext;

    @Inject
    public ShortcutUtils(Context context) {
        mContext = context;
    }

    public void reportShortcutUsed(Shortcut shortcut) {
        if (!ListenerUtil.mutListener.listen(27801)) {
            if ((ListenerUtil.mutListener.listen(27798) ? (android.os.Build.VERSION.SDK_INT <= VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27797) ? (android.os.Build.VERSION.SDK_INT > VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27796) ? (android.os.Build.VERSION.SDK_INT < VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27795) ? (android.os.Build.VERSION.SDK_INT != VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27794) ? (android.os.Build.VERSION.SDK_INT == VERSION_CODES.N_MR1) : (android.os.Build.VERSION.SDK_INT >= VERSION_CODES.N_MR1))))))) {
                ShortcutManager shortcutManager = mContext.getSystemService(ShortcutManager.class);
                if (!ListenerUtil.mutListener.listen(27800)) {
                    if (shortcutManager != null) {
                        if (!ListenerUtil.mutListener.listen(27799)) {
                            shortcutManager.reportShortcutUsed(shortcut.mId);
                        }
                    }
                }
            }
        }
    }
}
