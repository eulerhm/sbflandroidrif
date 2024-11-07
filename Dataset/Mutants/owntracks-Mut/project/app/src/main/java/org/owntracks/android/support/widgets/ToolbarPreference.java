package org.owntracks.android.support.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import org.owntracks.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// This is a prefrence that fakes a toolbar for a preference screen until the Android Support library supports toolbars in preferences_private screens
public class ToolbarPreference extends Preference {

    private String title;

    private Toolbar toolbar;

    private PreferenceScreen screen;

    public ToolbarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolbarPreference(Context context, PreferenceScreen parent) {
        super(context);
        if (!ListenerUtil.mutListener.listen(1179)) {
            this.screen = parent;
        }
        // parent.setPadding(0, 0, 0, 0);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(1180)) {
            this.setLayoutResource(R.layout.settings_toolbar);
        }
    }

    private void goUp() {
        if (!ListenerUtil.mutListener.listen(1182)) {
            if (screen == null)
                if (!ListenerUtil.mutListener.listen(1181)) {
                    screen = findPreferenceInHierarchy(getKey() + "Screen");
                }
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
