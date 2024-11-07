package org.owntracks.android.ui.preferences.connection.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.databinding.BaseObservable;
import org.owntracks.android.support.Preferences;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BaseDialogViewModel extends BaseObservable implements DialogInterface.OnClickListener {

    Preferences preferences;

    BaseDialogViewModel(Preferences preferences) {
        if (!ListenerUtil.mutListener.listen(1832)) {
            this.preferences = preferences;
        }
        if (!ListenerUtil.mutListener.listen(1833)) {
            load();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(1836)) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (!ListenerUtil.mutListener.listen(1835)) {
                    save();
                }
            } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                if (!ListenerUtil.mutListener.listen(1834)) {
                    dialog.cancel();
                }
            }
        }
    }

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    abstract void load();

    abstract void save();
}
