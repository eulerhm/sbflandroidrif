package com.ichi2.anki.widgets;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import android.content.Context;
import androidx.appcompat.widget.PopupMenu;
import android.view.View;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A simple little hack to force the icons to display in the PopupMenu
 */
public class PopupMenuWithIcons extends PopupMenu {

    public PopupMenuWithIcons(Context context, View anchor, boolean showIcons) {
        super(context, anchor);
        if (!ListenerUtil.mutListener.listen(4167)) {
            if (showIcons) {
                try {
                    Field[] fields = PopupMenu.class.getDeclaredFields();
                    if (!ListenerUtil.mutListener.listen(4166)) {
                        {
                            long _loopCounter98 = 0;
                            for (Field field : fields) {
                                ListenerUtil.loopListener.listen("_loopCounter98", ++_loopCounter98);
                                if (!ListenerUtil.mutListener.listen(4165)) {
                                    if ("mPopup".equals(field.getName())) {
                                        if (!ListenerUtil.mutListener.listen(4163)) {
                                            field.setAccessible(true);
                                        }
                                        Object menuPopupHelper = field.get(this);
                                        Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                                        Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                                        if (!ListenerUtil.mutListener.listen(4164)) {
                                            setForceIcons.invoke(menuPopupHelper, true);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
