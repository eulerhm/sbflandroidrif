package org.owntracks.android.support.widgets;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.StringRes;
import androidx.preference.EditTextPreference;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditStringPreference extends EditTextPreference {

    EditStringPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditStringPreference(Context context) {
        super(context);
    }

    private String hint;

    public void setHint(String hint) {
        if (!ListenerUtil.mutListener.listen(1046)) {
            this.hint = hint;
        }
    }

    public void setText(String text) {
        if (!ListenerUtil.mutListener.listen(1047)) {
            if (!shouldPersist())
                return;
        }
        if (!ListenerUtil.mutListener.listen(1048)) {
            super.setText(text);
        }
    }

    public EditStringPreference withPreferencesSummary(@StringRes int res) {
        if (!ListenerUtil.mutListener.listen(1049)) {
            setSummary(res);
        }
        return this;
    }

    public EditStringPreference withDialogMessage(@StringRes int res) {
        if (!ListenerUtil.mutListener.listen(1050)) {
            setDialogMessage(res);
        }
        return this;
    }
}
