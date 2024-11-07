package com.ichi2.anki.dialogs;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.libanki.Utils;
import com.ichi2.utils.ImportUtils;
import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImportDialog extends AsyncDialogFragment {

    public static final int DIALOG_IMPORT_HINT = 0;

    public static final int DIALOG_IMPORT_SELECT = 1;

    public static final int DIALOG_IMPORT_ADD_CONFIRM = 2;

    public static final int DIALOG_IMPORT_REPLACE_CONFIRM = 3;

    public interface ImportDialogListener {

        void showImportDialog(int id, String message);

        void showImportDialog(int id);

        void importAdd(String importPath);

        void importReplace(String importPath);

        void dismissAllDialogFragments();
    }

    /**
     * A set of dialogs which deal with importing a file
     *
     * @param dialogType An integer which specifies which of the sub-dialogs to show
     * @param dialogMessage An optional string which can be used to show a custom message
     * or specify import path
     */
    public static ImportDialog newInstance(int dialogType, String dialogMessage) {
        ImportDialog f = new ImportDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(768)) {
            args.putInt("dialogType", dialogType);
        }
        if (!ListenerUtil.mutListener.listen(769)) {
            args.putString("dialogMessage", dialogMessage);
        }
        if (!ListenerUtil.mutListener.listen(770)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(771)) {
            super.onCreate(savedInstanceState);
        }
        int mType = getArguments().getInt("dialogType");
        Resources res = getResources();
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(772)) {
            builder.cancelable(true);
        }
        switch(mType) {
            case DIALOG_IMPORT_HINT:
                {
                    // Instruct the user that they need to put their APKG files into the AnkiDroid directory
                    return builder.title(res.getString(R.string.import_title)).content(res.getString(R.string.import_hint, CollectionHelper.getCurrentAnkiDroidDirectory(getActivity()))).positiveText(R.string.dialog_ok).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> ((ImportDialogListener) getActivity()).showImportDialog(DIALOG_IMPORT_SELECT)).onNegative((dialog, which) -> dismissAllDialogFragments()).show();
                }
            case DIALOG_IMPORT_SELECT:
                {
                    // Allow user to choose from the list of available APKG files
                    List<File> fileList = Utils.getImportableDecks(getActivity());
                    if ((ListenerUtil.mutListener.listen(777) ? (fileList.size() >= 0) : (ListenerUtil.mutListener.listen(776) ? (fileList.size() <= 0) : (ListenerUtil.mutListener.listen(775) ? (fileList.size() > 0) : (ListenerUtil.mutListener.listen(774) ? (fileList.size() < 0) : (ListenerUtil.mutListener.listen(773) ? (fileList.size() != 0) : (fileList.size() == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(786)) {
                            UIUtils.showThemedToast(getActivity(), getResources().getString(R.string.upgrade_import_no_file_found, "'.apkg'"), false);
                        }
                        return builder.showListener(DialogInterface::cancel).show();
                    } else {
                        String[] tts = new String[fileList.size()];
                        final String[] importValues = new String[fileList.size()];
                        if (!ListenerUtil.mutListener.listen(785)) {
                            {
                                long _loopCounter12 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(784) ? (i >= tts.length) : (ListenerUtil.mutListener.listen(783) ? (i <= tts.length) : (ListenerUtil.mutListener.listen(782) ? (i > tts.length) : (ListenerUtil.mutListener.listen(781) ? (i != tts.length) : (ListenerUtil.mutListener.listen(780) ? (i == tts.length) : (i < tts.length)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                                    if (!ListenerUtil.mutListener.listen(778)) {
                                        tts[i] = fileList.get(i).getName();
                                    }
                                    if (!ListenerUtil.mutListener.listen(779)) {
                                        importValues[i] = fileList.get(i).getAbsolutePath();
                                    }
                                }
                            }
                        }
                        return builder.title(res.getString(R.string.import_select_title)).items(tts).itemsCallback((materialDialog, view, i, charSequence) -> {
                            String importPath = importValues[i];
                            // If collection package, we assume the collection will be replaced
                            if (ImportUtils.isCollectionPackage(filenameFromPath(importPath))) {
                                ((ImportDialogListener) getActivity()).showImportDialog(DIALOG_IMPORT_REPLACE_CONFIRM, importPath);
                            } else {
                                ((ImportDialogListener) getActivity()).showImportDialog(DIALOG_IMPORT_ADD_CONFIRM, importPath);
                            }
                        }).show();
                    }
                }
            case DIALOG_IMPORT_ADD_CONFIRM:
                {
                    String displayFileName = convertToDisplayName(getArguments().getString("dialogMessage"));
                    return builder.title(res.getString(R.string.import_title)).content(res.getString(R.string.import_message_add_confirm, filenameFromPath(displayFileName))).positiveText(R.string.import_message_add).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                        ((ImportDialogListener) getActivity()).importAdd(getArguments().getString("dialogMessage"));
                        dismissAllDialogFragments();
                    }).show();
                }
            case DIALOG_IMPORT_REPLACE_CONFIRM:
                {
                    String displayFileName = convertToDisplayName(getArguments().getString("dialogMessage"));
                    return builder.title(res.getString(R.string.import_title)).content(res.getString(R.string.import_message_replace_confirm, displayFileName)).positiveText(R.string.dialog_positive_replace).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                        ((ImportDialogListener) getActivity()).importReplace(getArguments().getString("dialogMessage"));
                        dismissAllDialogFragments();
                    }).show();
                }
            default:
                return null;
        }
    }

    private String convertToDisplayName(String name) {
        // NICE_TO_HAVE: Pass in the DisplayFileName closer to the source of the bad data, rather than fixing it here.
        try {
            return URLDecoder.decode(name, "UTF-8");
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(787)) {
                Timber.w("Failed to convert filename to displayable string");
            }
            return name;
        }
    }

    @Override
    public String getNotificationMessage() {
        return res().getString(R.string.import_interrupted);
    }

    @Override
    public String getNotificationTitle() {
        return res().getString(R.string.import_title);
    }

    public void dismissAllDialogFragments() {
        if (!ListenerUtil.mutListener.listen(788)) {
            ((ImportDialogListener) getActivity()).dismissAllDialogFragments();
        }
    }

    private static String filenameFromPath(String path) {
        return path.split("/")[(ListenerUtil.mutListener.listen(792) ? (path.split("/").length % 1) : (ListenerUtil.mutListener.listen(791) ? (path.split("/").length / 1) : (ListenerUtil.mutListener.listen(790) ? (path.split("/").length * 1) : (ListenerUtil.mutListener.listen(789) ? (path.split("/").length + 1) : (path.split("/").length - 1)))))];
    }
}
