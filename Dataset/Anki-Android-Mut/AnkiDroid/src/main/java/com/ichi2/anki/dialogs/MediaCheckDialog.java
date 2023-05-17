package com.ichi2.anki.dialogs;

import android.os.Bundle;
import android.os.Message;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaCheckDialog extends AsyncDialogFragment {

    public static final int DIALOG_CONFIRM_MEDIA_CHECK = 0;

    public static final int DIALOG_MEDIA_CHECK_RESULTS = 1;

    public interface MediaCheckDialogListener {

        void showMediaCheckDialog(int dialogType);

        void showMediaCheckDialog(int dialogType, List<List<String>> checkList);

        void mediaCheck();

        void deleteUnused(List<String> unused);

        void dismissAllDialogFragments();
    }

    public static MediaCheckDialog newInstance(int dialogType) {
        MediaCheckDialog f = new MediaCheckDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(839)) {
            args.putInt("dialogType", dialogType);
        }
        if (!ListenerUtil.mutListener.listen(840)) {
            f.setArguments(args);
        }
        return f;
    }

    public static MediaCheckDialog newInstance(int dialogType, List<List<String>> checkList) {
        MediaCheckDialog f = new MediaCheckDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(841)) {
            args.putStringArrayList("nohave", new ArrayList<>(checkList.get(0)));
        }
        if (!ListenerUtil.mutListener.listen(842)) {
            args.putStringArrayList("unused", new ArrayList<>(checkList.get(1)));
        }
        if (!ListenerUtil.mutListener.listen(843)) {
            args.putStringArrayList("invalid", new ArrayList<>(checkList.get(2)));
        }
        if (!ListenerUtil.mutListener.listen(844)) {
            args.putInt("dialogType", dialogType);
        }
        if (!ListenerUtil.mutListener.listen(845)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(846)) {
            super.onCreate(savedInstanceState);
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(847)) {
            builder.title(getNotificationTitle());
        }
        switch(getArguments().getInt("dialogType")) {
            case DIALOG_CONFIRM_MEDIA_CHECK:
                {
                    return builder.content(getNotificationMessage()).positiveText(res().getString(R.string.dialog_ok)).negativeText(res().getString(R.string.dialog_cancel)).cancelable(true).onPositive((dialog, which) -> {
                        ((MediaCheckDialogListener) getActivity()).mediaCheck();
                        ((MediaCheckDialogListener) getActivity()).dismissAllDialogFragments();
                    }).onNegative((dialog, which) -> ((MediaCheckDialogListener) getActivity()).dismissAllDialogFragments()).show();
                }
            case DIALOG_MEDIA_CHECK_RESULTS:
                {
                    final ArrayList<String> nohave = getArguments().getStringArrayList("nohave");
                    final ArrayList<String> unused = getArguments().getStringArrayList("unused");
                    final ArrayList<String> invalid = getArguments().getStringArrayList("invalid");
                    // Generate report
                    String report = "";
                    if (!ListenerUtil.mutListener.listen(854)) {
                        if ((ListenerUtil.mutListener.listen(852) ? (invalid.size() >= 0) : (ListenerUtil.mutListener.listen(851) ? (invalid.size() <= 0) : (ListenerUtil.mutListener.listen(850) ? (invalid.size() < 0) : (ListenerUtil.mutListener.listen(849) ? (invalid.size() != 0) : (ListenerUtil.mutListener.listen(848) ? (invalid.size() == 0) : (invalid.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(853)) {
                                report += String.format(res().getString(R.string.check_media_invalid), invalid.size());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(868)) {
                        if ((ListenerUtil.mutListener.listen(859) ? (unused.size() >= 0) : (ListenerUtil.mutListener.listen(858) ? (unused.size() <= 0) : (ListenerUtil.mutListener.listen(857) ? (unused.size() < 0) : (ListenerUtil.mutListener.listen(856) ? (unused.size() != 0) : (ListenerUtil.mutListener.listen(855) ? (unused.size() == 0) : (unused.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(866)) {
                                if ((ListenerUtil.mutListener.listen(864) ? (report.length() >= 0) : (ListenerUtil.mutListener.listen(863) ? (report.length() <= 0) : (ListenerUtil.mutListener.listen(862) ? (report.length() < 0) : (ListenerUtil.mutListener.listen(861) ? (report.length() != 0) : (ListenerUtil.mutListener.listen(860) ? (report.length() == 0) : (report.length() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(865)) {
                                        report += "\n";
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(867)) {
                                report += String.format(res().getString(R.string.check_media_unused), unused.size());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(882)) {
                        if ((ListenerUtil.mutListener.listen(873) ? (nohave.size() >= 0) : (ListenerUtil.mutListener.listen(872) ? (nohave.size() <= 0) : (ListenerUtil.mutListener.listen(871) ? (nohave.size() < 0) : (ListenerUtil.mutListener.listen(870) ? (nohave.size() != 0) : (ListenerUtil.mutListener.listen(869) ? (nohave.size() == 0) : (nohave.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(880)) {
                                if ((ListenerUtil.mutListener.listen(878) ? (report.length() >= 0) : (ListenerUtil.mutListener.listen(877) ? (report.length() <= 0) : (ListenerUtil.mutListener.listen(876) ? (report.length() < 0) : (ListenerUtil.mutListener.listen(875) ? (report.length() != 0) : (ListenerUtil.mutListener.listen(874) ? (report.length() == 0) : (report.length() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(879)) {
                                        report += "\n";
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(881)) {
                                report += String.format(res().getString(R.string.check_media_nohave), nohave.size());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(889)) {
                        if ((ListenerUtil.mutListener.listen(887) ? (report.length() >= 0) : (ListenerUtil.mutListener.listen(886) ? (report.length() <= 0) : (ListenerUtil.mutListener.listen(885) ? (report.length() > 0) : (ListenerUtil.mutListener.listen(884) ? (report.length() < 0) : (ListenerUtil.mutListener.listen(883) ? (report.length() != 0) : (report.length() == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(888)) {
                                report = res().getString(R.string.check_media_no_unused_missing);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(890)) {
                        // we do a full media scan and update the db on each media check on AnkiDroid.
                        report = res().getString(R.string.check_media_db_updated) + "\n\n" + report;
                    }
                    if (!ListenerUtil.mutListener.listen(891)) {
                        builder.content(report).cancelable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(899)) {
                        // needs to acknowledge the results, so show only an OK dialog.
                        if ((ListenerUtil.mutListener.listen(896) ? (unused.size() >= 0) : (ListenerUtil.mutListener.listen(895) ? (unused.size() <= 0) : (ListenerUtil.mutListener.listen(894) ? (unused.size() < 0) : (ListenerUtil.mutListener.listen(893) ? (unused.size() != 0) : (ListenerUtil.mutListener.listen(892) ? (unused.size() == 0) : (unused.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(898)) {
                                builder.positiveText(res().getString(R.string.dialog_ok)).negativeText(res().getString(R.string.check_media_delete_unused)).onPositive((dialog, which) -> ((MediaCheckDialogListener) getActivity()).dismissAllDialogFragments()).onNegative((dialog, which) -> {
                                    ((MediaCheckDialogListener) getActivity()).deleteUnused(unused);
                                    dismissAllDialogFragments();
                                });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(897)) {
                                builder.positiveText(res().getString(R.string.dialog_ok)).onPositive((dialog, which) -> ((MediaCheckDialogListener) getActivity()).dismissAllDialogFragments());
                            }
                        }
                    }
                    return builder.show();
                }
            default:
                return null;
        }
    }

    public void dismissAllDialogFragments() {
        if (!ListenerUtil.mutListener.listen(900)) {
            ((MediaCheckDialogListener) getActivity()).dismissAllDialogFragments();
        }
    }

    @Override
    public String getNotificationMessage() {
        switch(getArguments().getInt("dialogType")) {
            case DIALOG_CONFIRM_MEDIA_CHECK:
                return res().getString(R.string.check_media_warning);
            case DIALOG_MEDIA_CHECK_RESULTS:
                return res().getString(R.string.check_media_acknowledge);
            default:
                return res().getString(R.string.app_name);
        }
    }

    @Override
    public String getNotificationTitle() {
        if (!ListenerUtil.mutListener.listen(901)) {
            if (getArguments().getInt("dialogType") == DIALOG_CONFIRM_MEDIA_CHECK) {
                return res().getString(R.string.check_media_title);
            }
        }
        return res().getString(R.string.app_name);
    }

    @Override
    public Message getDialogHandlerMessage() {
        Message msg = Message.obtain();
        if (!ListenerUtil.mutListener.listen(902)) {
            msg.what = DialogHandler.MSG_SHOW_MEDIA_CHECK_COMPLETE_DIALOG;
        }
        Bundle b = new Bundle();
        if (!ListenerUtil.mutListener.listen(903)) {
            b.putStringArrayList("nohave", getArguments().getStringArrayList("nohave"));
        }
        if (!ListenerUtil.mutListener.listen(904)) {
            b.putStringArrayList("unused", getArguments().getStringArrayList("unused"));
        }
        if (!ListenerUtil.mutListener.listen(905)) {
            b.putStringArrayList("invalid", getArguments().getStringArrayList("invalid"));
        }
        if (!ListenerUtil.mutListener.listen(906)) {
            b.putInt("dialogType", getArguments().getInt("dialogType"));
        }
        if (!ListenerUtil.mutListener.listen(907)) {
            msg.setData(b);
        }
        return msg;
    }
}
