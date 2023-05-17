package org.wordpress.android.util;

import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.util.TypedValue;
import android.widget.TextView;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPPrefUtils {

    /**
     * Gets a preference and sets the {@link android.preference.Preference.OnPreferenceChangeListener}.
     */
    public static Preference getPrefAndSetClickListener(PreferenceFragment prefFrag, int id, Preference.OnPreferenceClickListener listener) {
        Preference pref = prefFrag.findPreference(prefFrag.getString(id));
        if (!ListenerUtil.mutListener.listen(28268)) {
            if (pref != null) {
                if (!ListenerUtil.mutListener.listen(28267)) {
                    pref.setOnPreferenceClickListener(listener);
                }
            }
        }
        return pref;
    }

    /**
     * Gets a preference and sets the {@link android.preference.Preference.OnPreferenceChangeListener}.
     */
    public static Preference getPrefAndSetChangeListener(PreferenceFragment prefFrag, int id, Preference.OnPreferenceChangeListener listener) {
        Preference pref = prefFrag.findPreference(prefFrag.getString(id));
        if (!ListenerUtil.mutListener.listen(28270)) {
            if (pref != null) {
                if (!ListenerUtil.mutListener.listen(28269)) {
                    pref.setOnPreferenceChangeListener(listener);
                }
            }
        }
        return pref;
    }

    /**
     * Removes a {@link Preference} from the {@link PreferenceCategory} with the given key.
     */
    public static void removePreference(PreferenceFragment prefFrag, int parentKey, int prefKey) {
        String parentName = prefFrag.getString(parentKey);
        String prefName = prefFrag.getString(prefKey);
        PreferenceGroup parent = (PreferenceGroup) prefFrag.findPreference(parentName);
        Preference child = prefFrag.findPreference(prefName);
        if (!ListenerUtil.mutListener.listen(28273)) {
            if ((ListenerUtil.mutListener.listen(28271) ? (parent != null || child != null) : (parent != null && child != null))) {
                if (!ListenerUtil.mutListener.listen(28272)) {
                    parent.removePreference(child);
                }
            }
        }
    }

    /**
     * Styles a {@link TextView} to display text in a button.
     */
    public static void layoutAsFlatButton(TextView view) {
        int size = view.getResources().getDimensionPixelSize(R.dimen.text_sz_medium);
        if (!ListenerUtil.mutListener.listen(28274)) {
            setTextViewAttributes(view, size, R.color.primary_40);
        }
    }

    public static void setTextViewAttributes(TextView textView, int size, int colorRes) {
        if (!ListenerUtil.mutListener.listen(28275)) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
        if (!ListenerUtil.mutListener.listen(28276)) {
            textView.setTextColor(textView.getResources().getColor(colorRes));
        }
    }
}
