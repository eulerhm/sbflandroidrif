package fr.free.nrw.commons.utils;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AbstractTextWatcher implements TextWatcher {

    private final TextChange textChange;

    public AbstractTextWatcher(@NonNull TextChange textChange) {
        this.textChange = textChange;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!ListenerUtil.mutListener.listen(2079)) {
            textChange.onTextChanged(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public interface TextChange {

        void onTextChanged(String value);
    }
}
