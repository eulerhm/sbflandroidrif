package org.owntracks.android.support;

import android.text.Editable;
import android.text.TextWatcher;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class SimpleTextChangeListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!ListenerUtil.mutListener.listen(1320)) {
            onChanged(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public abstract void onChanged(String s);
}
