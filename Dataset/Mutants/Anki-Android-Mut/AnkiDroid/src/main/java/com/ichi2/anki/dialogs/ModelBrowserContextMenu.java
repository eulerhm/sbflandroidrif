package com.ichi2.anki.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ModelBrowserContextMenu extends AnalyticsDialogFragment {

    public static final int MODEL_TEMPLATE = 0;

    public static final int MODEL_RENAME = 1;

    public static final int MODEL_DELETE = 2;

    private static MaterialDialog.ListCallback mContextMenuListener;

    public static ModelBrowserContextMenu newInstance(String label, MaterialDialog.ListCallback contextMenuListener) {
        if (!ListenerUtil.mutListener.listen(908)) {
            mContextMenuListener = contextMenuListener;
        }
        ModelBrowserContextMenu n = new ModelBrowserContextMenu();
        Bundle b = new Bundle();
        if (!ListenerUtil.mutListener.listen(909)) {
            b.putString("label", label);
        }
        if (!ListenerUtil.mutListener.listen(910)) {
            n.setArguments(b);
        }
        return n;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(911)) {
            super.onCreate(savedInstanceState);
        }
        String[] entries = new String[3];
        if (!ListenerUtil.mutListener.listen(912)) {
            entries[MODEL_TEMPLATE] = getResources().getString(R.string.model_browser_template);
        }
        if (!ListenerUtil.mutListener.listen(913)) {
            entries[MODEL_RENAME] = getResources().getString(R.string.model_browser_rename);
        }
        if (!ListenerUtil.mutListener.listen(914)) {
            entries[MODEL_DELETE] = getResources().getString(R.string.model_browser_delete);
        }
        return new MaterialDialog.Builder(getActivity()).title(getArguments().getString("label")).items(entries).itemsCallback(mContextMenuListener).build();
    }
}
