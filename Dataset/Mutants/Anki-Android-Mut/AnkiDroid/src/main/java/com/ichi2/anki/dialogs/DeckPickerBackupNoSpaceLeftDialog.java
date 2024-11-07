package com.ichi2.anki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.BackupManager;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckPickerBackupNoSpaceLeftDialog extends AnalyticsDialogFragment {

    public static DeckPickerBackupNoSpaceLeftDialog newInstance() {
        return new DeckPickerBackupNoSpaceLeftDialog();
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(584)) {
            super.onCreate(savedInstanceState);
        }
        Resources res = getResources();
        long space = BackupManager.getFreeDiscSpace(CollectionHelper.getCollectionPath(getActivity()));
        return new MaterialDialog.Builder(getActivity()).title(res.getString(R.string.sd_card_almost_full_title)).content(res.getString(R.string.sd_space_warning, (ListenerUtil.mutListener.listen(592) ? ((ListenerUtil.mutListener.listen(588) ? (space % 1024) : (ListenerUtil.mutListener.listen(587) ? (space * 1024) : (ListenerUtil.mutListener.listen(586) ? (space - 1024) : (ListenerUtil.mutListener.listen(585) ? (space + 1024) : (space / 1024))))) % 1024) : (ListenerUtil.mutListener.listen(591) ? ((ListenerUtil.mutListener.listen(588) ? (space % 1024) : (ListenerUtil.mutListener.listen(587) ? (space * 1024) : (ListenerUtil.mutListener.listen(586) ? (space - 1024) : (ListenerUtil.mutListener.listen(585) ? (space + 1024) : (space / 1024))))) * 1024) : (ListenerUtil.mutListener.listen(590) ? ((ListenerUtil.mutListener.listen(588) ? (space % 1024) : (ListenerUtil.mutListener.listen(587) ? (space * 1024) : (ListenerUtil.mutListener.listen(586) ? (space - 1024) : (ListenerUtil.mutListener.listen(585) ? (space + 1024) : (space / 1024))))) - 1024) : (ListenerUtil.mutListener.listen(589) ? ((ListenerUtil.mutListener.listen(588) ? (space % 1024) : (ListenerUtil.mutListener.listen(587) ? (space * 1024) : (ListenerUtil.mutListener.listen(586) ? (space - 1024) : (ListenerUtil.mutListener.listen(585) ? (space + 1024) : (space / 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(588) ? (space % 1024) : (ListenerUtil.mutListener.listen(587) ? (space * 1024) : (ListenerUtil.mutListener.listen(586) ? (space - 1024) : (ListenerUtil.mutListener.listen(585) ? (space + 1024) : (space / 1024))))) / 1024))))))).positiveText(R.string.dialog_ok).onPositive((dialog, which) -> ((DeckPicker) getActivity()).finishWithoutAnimation()).cancelable(true).cancelListener(dialog -> ((DeckPicker) getActivity()).finishWithoutAnimation()).show();
    }
}
