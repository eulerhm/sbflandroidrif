package com.ichi2.anki.dialogs;

import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SimpleMessageDialog extends AsyncDialogFragment {

    public interface SimpleMessageDialogListener {

        void dismissSimpleMessageDialog(boolean reload);
    }

    public static SimpleMessageDialog newInstance(String message, boolean reload) {
        return newInstance("", message, reload);
    }

    public static SimpleMessageDialog newInstance(String title, @Nullable String message, boolean reload) {
        SimpleMessageDialog f = new SimpleMessageDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(979)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(980)) {
            args.putString("message", message);
        }
        if (!ListenerUtil.mutListener.listen(981)) {
            args.putBoolean("reload", reload);
        }
        if (!ListenerUtil.mutListener.listen(982)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(983)) {
            // FIXME this should be super.onCreateDialog(Bundle), no?
            super.onCreate(savedInstanceState);
        }
        return new MaterialDialog.Builder(getActivity()).title(getNotificationTitle()).content(getNotificationMessage()).positiveText(res().getString(R.string.dialog_ok)).onPositive((dialog, which) -> ((SimpleMessageDialogListener) getActivity()).dismissSimpleMessageDialog(getArguments().getBoolean("reload"))).show();
    }

    public String getNotificationTitle() {
        String title = getArguments().getString("title");
        if (!"".equals(title)) {
            return title;
        } else {
            return AnkiDroidApp.getAppResources().getString(R.string.app_name);
        }
    }

    public String getNotificationMessage() {
        return getArguments().getString("message");
    }
}
