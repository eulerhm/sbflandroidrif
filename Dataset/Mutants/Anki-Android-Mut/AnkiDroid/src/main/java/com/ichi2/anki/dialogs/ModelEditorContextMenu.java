package com.ichi2.anki.dialogs;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ModelEditorContextMenu extends AnalyticsDialogFragment {

    public static final int FIELD_REPOSITION = 0;

    public static final int SORT_FIELD = 1;

    public static final int FIELD_RENAME = 2;

    public static final int FIELD_DELETE = 3;

    public static final int FIELD_TOGGLE_STICKY = 4;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static final int FIELD_ADD_LANGUAGE_HINT = 5;

    private static MaterialDialog.ListCallback mContextMenuListener;

    public static ModelEditorContextMenu newInstance(String label, MaterialDialog.ListCallback contextMenuListener) {
        ModelEditorContextMenu n = new ModelEditorContextMenu();
        if (!ListenerUtil.mutListener.listen(915)) {
            mContextMenuListener = contextMenuListener;
        }
        Bundle b = new Bundle();
        if (!ListenerUtil.mutListener.listen(916)) {
            b.putString("label", label);
        }
        if (!ListenerUtil.mutListener.listen(917)) {
            mContextMenuListener = contextMenuListener;
        }
        if (!ListenerUtil.mutListener.listen(918)) {
            n.setArguments(b);
        }
        return n;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(919)) {
            super.onCreate(savedInstanceState);
        }
        String[] entries = new String[getEntryCount()];
        if (!ListenerUtil.mutListener.listen(920)) {
            entries[FIELD_REPOSITION] = getResources().getString(R.string.model_field_editor_reposition_menu);
        }
        if (!ListenerUtil.mutListener.listen(921)) {
            entries[SORT_FIELD] = getResources().getString(R.string.model_field_editor_sort_field);
        }
        if (!ListenerUtil.mutListener.listen(922)) {
            entries[FIELD_RENAME] = getResources().getString(R.string.model_field_editor_rename);
        }
        if (!ListenerUtil.mutListener.listen(923)) {
            entries[FIELD_DELETE] = getResources().getString(R.string.model_field_editor_delete);
        }
        if (!ListenerUtil.mutListener.listen(924)) {
            entries[FIELD_TOGGLE_STICKY] = getResources().getString(R.string.model_field_editor_toggle_sticky);
        }
        if (!ListenerUtil.mutListener.listen(931)) {
            if ((ListenerUtil.mutListener.listen(929) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(928) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(927) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(926) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(925) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                if (!ListenerUtil.mutListener.listen(930)) {
                    entries[FIELD_ADD_LANGUAGE_HINT] = getResources().getString(R.string.model_field_editor_language_hint);
                }
            }
        }
        return new MaterialDialog.Builder(getActivity()).title(getArguments().getString("label")).items(entries).itemsCallback(mContextMenuListener).build();
    }

    private int getEntryCount() {
        int entryCount = 5;
        if (!ListenerUtil.mutListener.listen(938)) {
            if ((ListenerUtil.mutListener.listen(936) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(935) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(934) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(933) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(932) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                if (!ListenerUtil.mutListener.listen(937)) {
                    entryCount++;
                }
            }
        }
        return entryCount;
    }
}
