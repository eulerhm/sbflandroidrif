package com.ichi2.anki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import static com.ichi2.libanki.Decks.NOT_FOUND_DECK_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExportDialog extends AnalyticsDialogFragment {

    public interface ExportDialogListener {

        void exportApkg(String path, Long did, boolean includeSched, boolean includeMedia);

        void dismissAllDialogFragments();
    }

    private final int INCLUDE_SCHED = 0;

    private final int INCLUDE_MEDIA = 1;

    private boolean mIncludeSched = false;

    private boolean mIncludeMedia = false;

    /**
     * A set of dialogs which deal with importing a file
     *
     * @param did An integer which specifies which of the sub-dialogs to show
     * @param dialogMessage An optional string which can be used to show a custom message or specify import path
     */
    public static ExportDialog newInstance(@NonNull String dialogMessage, Long did) {
        ExportDialog f = new ExportDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(743)) {
            args.putLong("did", did);
        }
        if (!ListenerUtil.mutListener.listen(744)) {
            args.putString("dialogMessage", dialogMessage);
        }
        if (!ListenerUtil.mutListener.listen(745)) {
            f.setArguments(args);
        }
        return f;
    }

    public static ExportDialog newInstance(@NonNull String dialogMessage) {
        ExportDialog f = new ExportDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(746)) {
            args.putString("dialogMessage", dialogMessage);
        }
        if (!ListenerUtil.mutListener.listen(747)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(748)) {
            super.onCreate(savedInstanceState);
        }
        Resources res = getResources();
        final long did = getArguments().getLong("did", NOT_FOUND_DECK_ID);
        Integer[] checked;
        if ((ListenerUtil.mutListener.listen(753) ? (did >= NOT_FOUND_DECK_ID) : (ListenerUtil.mutListener.listen(752) ? (did <= NOT_FOUND_DECK_ID) : (ListenerUtil.mutListener.listen(751) ? (did > NOT_FOUND_DECK_ID) : (ListenerUtil.mutListener.listen(750) ? (did < NOT_FOUND_DECK_ID) : (ListenerUtil.mutListener.listen(749) ? (did == NOT_FOUND_DECK_ID) : (did != NOT_FOUND_DECK_ID))))))) {
            if (!ListenerUtil.mutListener.listen(755)) {
                mIncludeSched = false;
            }
            checked = new Integer[] {};
        } else {
            if (!ListenerUtil.mutListener.listen(754)) {
                mIncludeSched = true;
            }
            checked = new Integer[] { INCLUDE_SCHED };
        }
        final String[] items = { res.getString(R.string.export_include_schedule), res.getString(R.string.export_include_media) };
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity()).title(R.string.export).content(getArguments().getString("dialogMessage")).positiveText(android.R.string.ok).negativeText(android.R.string.cancel).cancelable(true).items(items).alwaysCallMultiChoiceCallback().itemsCallbackMultiChoice(checked, (materialDialog, integers, charSequences) -> {
            mIncludeMedia = false;
            mIncludeSched = false;
            {
                long _loopCounter11 = 0;
                for (Integer integer : integers) {
                    ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                    switch(integer) {
                        case INCLUDE_SCHED:
                            mIncludeSched = true;
                            break;
                        case INCLUDE_MEDIA:
                            mIncludeMedia = true;
                            break;
                    }
                }
            }
            return true;
        }).onPositive((dialog, which) -> {
            ((ExportDialogListener) getActivity()).exportApkg(null, did != NOT_FOUND_DECK_ID ? did : null, mIncludeSched, mIncludeMedia);
            dismissAllDialogFragments();
        }).onNegative((dialog, which) -> dismissAllDialogFragments());
        return builder.show();
    }

    public void dismissAllDialogFragments() {
        if (!ListenerUtil.mutListener.listen(756)) {
            ((ExportDialogListener) getActivity()).dismissAllDialogFragments();
        }
    }
}
