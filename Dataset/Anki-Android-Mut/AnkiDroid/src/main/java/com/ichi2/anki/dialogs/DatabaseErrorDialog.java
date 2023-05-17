package com.ichi2.anki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.AnkiActivity;
import com.ichi2.anki.BackupManager;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;
import com.ichi2.libanki.Consts;
import com.ichi2.async.Connection;
import com.ichi2.libanki.utils.Time;
import com.ichi2.utils.UiUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DatabaseErrorDialog extends AsyncDialogFragment {

    private int[] mRepairValues;

    private File[] mBackups;

    public static final int DIALOG_LOAD_FAILED = 0;

    public static final int DIALOG_DB_ERROR = 1;

    public static final int DIALOG_ERROR_HANDLING = 2;

    public static final int DIALOG_REPAIR_COLLECTION = 3;

    public static final int DIALOG_RESTORE_BACKUP = 4;

    public static final int DIALOG_NEW_COLLECTION = 5;

    public static final int DIALOG_CONFIRM_DATABASE_CHECK = 6;

    public static final int DIALOG_CONFIRM_RESTORE_BACKUP = 7;

    public static final int DIALOG_FULL_SYNC_FROM_SERVER = 8;

    /**
     * If the database is locked, all we can do is reset the app
     */
    public static final int DIALOG_DB_LOCKED = 9;

    /**
     * If the datbase is at a version higher than what we can currently handle
     */
    public static final int INCOMPATIBLE_DB_VERSION = 10;

    // public flag which lets us distinguish between inaccessible and corrupt database
    public static boolean databaseCorruptFlag = false;

    /**
     * A set of dialogs which deal with problems with the database when it can't load
     *
     * @param dialogType An integer which specifies which of the sub-dialogs to show
     */
    public static DatabaseErrorDialog newInstance(int dialogType) {
        DatabaseErrorDialog f = new DatabaseErrorDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(508)) {
            args.putInt("dialogType", dialogType);
        }
        if (!ListenerUtil.mutListener.listen(509)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(510)) {
            super.onCreate(savedInstanceState);
        }
        int mType = getArguments().getInt("dialogType");
        Resources res = getResources();
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(511)) {
            builder.cancelable(true).title(getTitle());
        }
        boolean sqliteInstalled = false;
        try {
            if (!ListenerUtil.mutListener.listen(518)) {
                sqliteInstalled = (ListenerUtil.mutListener.listen(517) ? (Runtime.getRuntime().exec("sqlite3 --version").waitFor() >= 0) : (ListenerUtil.mutListener.listen(516) ? (Runtime.getRuntime().exec("sqlite3 --version").waitFor() <= 0) : (ListenerUtil.mutListener.listen(515) ? (Runtime.getRuntime().exec("sqlite3 --version").waitFor() > 0) : (ListenerUtil.mutListener.listen(514) ? (Runtime.getRuntime().exec("sqlite3 --version").waitFor() < 0) : (ListenerUtil.mutListener.listen(513) ? (Runtime.getRuntime().exec("sqlite3 --version").waitFor() != 0) : (Runtime.getRuntime().exec("sqlite3 --version").waitFor() == 0))))));
            }
        } catch (IOException | InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(512)) {
                e.printStackTrace();
            }
        }
        switch(mType) {
            case DIALOG_LOAD_FAILED:
                {
                    // the activity
                    return builder.cancelable(false).content(getMessage()).iconAttr(R.attr.dialogErrorIcon).positiveText(R.string.error_handling_options).negativeText(R.string.close).onPositive((inner_dialog, which) -> ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_ERROR_HANDLING)).onNegative((inner_dialog, which) -> exit()).show();
                }
            case DIALOG_DB_ERROR:
                {
                    // options, submitting an error report, or closing the activity
                    MaterialDialog dialog = builder.cancelable(false).content(getMessage()).iconAttr(R.attr.dialogErrorIcon).positiveText(R.string.error_handling_options).negativeText(R.string.answering_error_report).neutralText(res.getString(R.string.close)).onPositive((inner_dialog, which) -> ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_ERROR_HANDLING)).onNegative((inner_dialog, which) -> {
                        ((DeckPicker) getActivity()).sendErrorReport();
                        dismissAllDialogFragments();
                    }).onNeutral((inner_dialog, which) -> exit()).show();
                    if (!ListenerUtil.mutListener.listen(519)) {
                        dialog.getCustomView().findViewById(R.id.md_buttonDefaultNegative).setEnabled(((DeckPicker) getActivity()).hasErrorFiles());
                    }
                    return dialog;
                }
            case DIALOG_ERROR_HANDLING:
                {
                    // to the previous dialog
                    ArrayList<String> options = new ArrayList<>(6);
                    ArrayList<Integer> values = new ArrayList<>(6);
                    if (!ListenerUtil.mutListener.listen(524)) {
                        if (!((AnkiActivity) getActivity()).colIsOpen()) {
                            if (!ListenerUtil.mutListener.listen(522)) {
                                // retry
                                options.add(res.getString(R.string.backup_retry_opening));
                            }
                            if (!ListenerUtil.mutListener.listen(523)) {
                                values.add(0);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(520)) {
                                // fix integrity
                                options.add(res.getString(R.string.check_db));
                            }
                            if (!ListenerUtil.mutListener.listen(521)) {
                                values.add(1);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(527)) {
                        // repair db with sqlite
                        if (sqliteInstalled) {
                            if (!ListenerUtil.mutListener.listen(525)) {
                                options.add(res.getString(R.string.backup_error_menu_repair));
                            }
                            if (!ListenerUtil.mutListener.listen(526)) {
                                values.add(2);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(528)) {
                        // // restore from backup
                        options.add(res.getString(R.string.backup_restore));
                    }
                    if (!ListenerUtil.mutListener.listen(529)) {
                        values.add(3);
                    }
                    if (!ListenerUtil.mutListener.listen(530)) {
                        // delete old collection and build new one
                        options.add(res.getString(R.string.backup_full_sync_from_server));
                    }
                    if (!ListenerUtil.mutListener.listen(531)) {
                        values.add(4);
                    }
                    if (!ListenerUtil.mutListener.listen(532)) {
                        // delete old collection and build new one
                        options.add(res.getString(R.string.backup_del_collection));
                    }
                    if (!ListenerUtil.mutListener.listen(533)) {
                        values.add(5);
                    }
                    String[] titles = new String[options.size()];
                    if (!ListenerUtil.mutListener.listen(534)) {
                        mRepairValues = new int[options.size()];
                    }
                    if (!ListenerUtil.mutListener.listen(542)) {
                        {
                            long _loopCounter5 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(541) ? (i >= options.size()) : (ListenerUtil.mutListener.listen(540) ? (i <= options.size()) : (ListenerUtil.mutListener.listen(539) ? (i > options.size()) : (ListenerUtil.mutListener.listen(538) ? (i != options.size()) : (ListenerUtil.mutListener.listen(537) ? (i == options.size()) : (i < options.size())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                                if (!ListenerUtil.mutListener.listen(535)) {
                                    titles[i] = options.get(i);
                                }
                                if (!ListenerUtil.mutListener.listen(536)) {
                                    mRepairValues[i] = values.get(i);
                                }
                            }
                        }
                    }
                    return builder.iconAttr(R.attr.dialogErrorIcon).negativeText(R.string.dialog_cancel).items(titles).itemsCallback((materialDialog, view, which, charSequence) -> {
                        switch(mRepairValues[which]) {
                            case 0:
                                ((DeckPicker) getActivity()).restartActivity();
                                return;
                            case 1:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_CONFIRM_DATABASE_CHECK);
                                return;
                            case 2:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_REPAIR_COLLECTION);
                                return;
                            case 3:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_RESTORE_BACKUP);
                                return;
                            case 4:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_FULL_SYNC_FROM_SERVER);
                                return;
                            case 5:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_NEW_COLLECTION);
                                return;
                            default:
                                throw new RuntimeException("Unknown dialog selection: " + mRepairValues[which]);
                        }
                    }).show();
                }
            case DIALOG_REPAIR_COLLECTION:
                {
                    // Allow user to run BackupManager.repairCollection()
                    return builder.content(getMessage()).iconAttr(R.attr.dialogErrorIcon).positiveText(R.string.dialog_positive_repair).negativeText(R.string.dialog_cancel).onPositive((inner_dialog, which) -> {
                        ((DeckPicker) getActivity()).repairCollection();
                        dismissAllDialogFragments();
                    }).show();
                }
            case DIALOG_RESTORE_BACKUP:
                {
                    // Allow user to restore one of the backups
                    String path = CollectionHelper.getCollectionPath(getActivity());
                    File[] files = BackupManager.getBackups(new File(path));
                    if (!ListenerUtil.mutListener.listen(543)) {
                        mBackups = new File[files.length];
                    }
                    if (!ListenerUtil.mutListener.listen(558)) {
                        {
                            long _loopCounter6 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(557) ? (i >= files.length) : (ListenerUtil.mutListener.listen(556) ? (i <= files.length) : (ListenerUtil.mutListener.listen(555) ? (i > files.length) : (ListenerUtil.mutListener.listen(554) ? (i != files.length) : (ListenerUtil.mutListener.listen(553) ? (i == files.length) : (i < files.length)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                                if (!ListenerUtil.mutListener.listen(552)) {
                                    mBackups[i] = files[(ListenerUtil.mutListener.listen(551) ? ((ListenerUtil.mutListener.listen(547) ? (files.length % 1) : (ListenerUtil.mutListener.listen(546) ? (files.length / 1) : (ListenerUtil.mutListener.listen(545) ? (files.length * 1) : (ListenerUtil.mutListener.listen(544) ? (files.length + 1) : (files.length - 1))))) % i) : (ListenerUtil.mutListener.listen(550) ? ((ListenerUtil.mutListener.listen(547) ? (files.length % 1) : (ListenerUtil.mutListener.listen(546) ? (files.length / 1) : (ListenerUtil.mutListener.listen(545) ? (files.length * 1) : (ListenerUtil.mutListener.listen(544) ? (files.length + 1) : (files.length - 1))))) / i) : (ListenerUtil.mutListener.listen(549) ? ((ListenerUtil.mutListener.listen(547) ? (files.length % 1) : (ListenerUtil.mutListener.listen(546) ? (files.length / 1) : (ListenerUtil.mutListener.listen(545) ? (files.length * 1) : (ListenerUtil.mutListener.listen(544) ? (files.length + 1) : (files.length - 1))))) * i) : (ListenerUtil.mutListener.listen(548) ? ((ListenerUtil.mutListener.listen(547) ? (files.length % 1) : (ListenerUtil.mutListener.listen(546) ? (files.length / 1) : (ListenerUtil.mutListener.listen(545) ? (files.length * 1) : (ListenerUtil.mutListener.listen(544) ? (files.length + 1) : (files.length - 1))))) + i) : ((ListenerUtil.mutListener.listen(547) ? (files.length % 1) : (ListenerUtil.mutListener.listen(546) ? (files.length / 1) : (ListenerUtil.mutListener.listen(545) ? (files.length * 1) : (ListenerUtil.mutListener.listen(544) ? (files.length + 1) : (files.length - 1))))) - i)))))];
                                }
                            }
                        }
                    }
                    if ((ListenerUtil.mutListener.listen(563) ? (mBackups.length >= 0) : (ListenerUtil.mutListener.listen(562) ? (mBackups.length <= 0) : (ListenerUtil.mutListener.listen(561) ? (mBackups.length > 0) : (ListenerUtil.mutListener.listen(560) ? (mBackups.length < 0) : (ListenerUtil.mutListener.listen(559) ? (mBackups.length != 0) : (mBackups.length == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(572)) {
                            builder.title(res.getString(R.string.backup_restore)).content(getMessage()).positiveText(R.string.dialog_ok).onPositive((inner_dialog, which) -> ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_ERROR_HANDLING));
                        }
                    } else {
                        String[] dates = new String[mBackups.length];
                        if (!ListenerUtil.mutListener.listen(570)) {
                            {
                                long _loopCounter7 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(569) ? (i >= mBackups.length) : (ListenerUtil.mutListener.listen(568) ? (i <= mBackups.length) : (ListenerUtil.mutListener.listen(567) ? (i > mBackups.length) : (ListenerUtil.mutListener.listen(566) ? (i != mBackups.length) : (ListenerUtil.mutListener.listen(565) ? (i == mBackups.length) : (i < mBackups.length)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                                    if (!ListenerUtil.mutListener.listen(564)) {
                                        dates[i] = mBackups[i].getName().replaceAll(".*-(\\d{4}-\\d{2}-\\d{2})-(\\d{2})-(\\d{2}).apkg", "$1 ($2:$3 h)");
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(571)) {
                            builder.title(res.getString(R.string.backup_restore_select_title)).negativeText(R.string.dialog_cancel).items(dates).itemsCallbackSingleChoice(dates.length, (materialDialog, view, which, charSequence) -> {
                                if (mBackups[which].length() > 0) {
                                    // restore the backup if it's valid
                                    ((DeckPicker) getActivity()).restoreFromBackup(mBackups[which].getPath());
                                    dismissAllDialogFragments();
                                } else {
                                    // otherwise show an error dialog
                                    new MaterialDialog.Builder(getActivity()).title(R.string.vague_error).content(R.string.backup_invalid_file_error).positiveText(R.string.dialog_ok).build().show();
                                }
                                return true;
                            });
                        }
                    }
                    MaterialDialog materialDialog = builder.build();
                    if (!ListenerUtil.mutListener.listen(573)) {
                        materialDialog.setOnKeyListener((dialog, keyCode, event) -> {
                            if (keyCode == KeyEvent.KEYCODE_BACK) {
                                Timber.i("DIALOG_RESTORE_BACKUP caught hardware back button");
                                dismissAllDialogFragments();
                                return true;
                            }
                            return false;
                        });
                    }
                    return materialDialog;
                }
            case DIALOG_NEW_COLLECTION:
                {
                    // Allow user to create a new empty collection
                    return builder.content(getMessage()).positiveText(R.string.dialog_positive_create).negativeText(R.string.dialog_cancel).onPositive((inner_dialog, which) -> {
                        CollectionHelper ch = CollectionHelper.getInstance();
                        Time time = ch.getTimeSafe(getContext());
                        ch.closeCollection(false, "DatabaseErrorDialog: Before Create New Collection");
                        String path1 = CollectionHelper.getCollectionPath(getActivity());
                        if (BackupManager.moveDatabaseToBrokenFolder(path1, false, time)) {
                            ((DeckPicker) getActivity()).restartActivity();
                        } else {
                            ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_LOAD_FAILED);
                        }
                    }).show();
                }
            case DIALOG_CONFIRM_DATABASE_CHECK:
                {
                    // Confirmation dialog for database check
                    return builder.content(getMessage()).positiveText(R.string.dialog_ok).negativeText(R.string.dialog_cancel).onPositive((inner_dialog, which) -> {
                        ((DeckPicker) getActivity()).integrityCheck();
                        dismissAllDialogFragments();
                    }).show();
                }
            case DIALOG_CONFIRM_RESTORE_BACKUP:
                {
                    // Confirmation dialog for backup restore
                    return builder.content(getMessage()).positiveText(R.string.dialog_continue).negativeText(R.string.dialog_cancel).onPositive((inner_dialog, which) -> ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_RESTORE_BACKUP)).show();
                }
            case DIALOG_FULL_SYNC_FROM_SERVER:
                {
                    // Allow user to do a full-sync from the server
                    return builder.content(getMessage()).positiveText(R.string.dialog_positive_overwrite).negativeText(R.string.dialog_cancel).onPositive((inner_dialog, which) -> {
                        ((DeckPicker) getActivity()).sync(Connection.ConflictResolution.FULL_DOWNLOAD);
                        dismissAllDialogFragments();
                    }).show();
                }
            case DIALOG_DB_LOCKED:
                {
                    // If the database is locked, all we can do is ask the user to exit.
                    return builder.content(getMessage()).positiveText(R.string.close).cancelable(false).onPositive((inner_dialog, which) -> exit()).show();
                }
            case INCOMPATIBLE_DB_VERSION:
                {
                    List<Integer> values = new ArrayList<>(2);
                    CharSequence[] options = new CharSequence[] { UiUtil.makeBold(res.getString(R.string.backup_restore)), UiUtil.makeBold(res.getString(R.string.backup_full_sync_from_server)) };
                    if (!ListenerUtil.mutListener.listen(574)) {
                        values.add(0);
                    }
                    if (!ListenerUtil.mutListener.listen(575)) {
                        values.add(1);
                    }
                    return builder.cancelable(false).content(getMessage()).iconAttr(R.attr.dialogErrorIcon).positiveText(R.string.close).onPositive((inner_dialog, which) -> exit()).items(options).itemsCallback((dialog, itemView, position, text) -> {
                        switch(values.get(position)) {
                            case 0:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_RESTORE_BACKUP);
                                break;
                            case 1:
                                ((DeckPicker) getActivity()).showDatabaseErrorDialog(DIALOG_FULL_SYNC_FROM_SERVER);
                                break;
                        }
                    }).show();
                }
            default:
                return null;
        }
    }

    private void exit() {
        if (!ListenerUtil.mutListener.listen(576)) {
            ((DeckPicker) getActivity()).exit();
        }
    }

    private String getMessage() {
        switch(getArguments().getInt("dialogType")) {
            case DIALOG_LOAD_FAILED:
                if (databaseCorruptFlag) {
                    // Show a specific message appropriate for the situation
                    return res().getString(R.string.corrupt_db_message, res().getString(R.string.repair_deck));
                } else {
                    // Generic message shown when a libanki task failed
                    return res().getString(R.string.access_collection_failed_message, res().getString(R.string.link_help));
                }
            case DIALOG_DB_ERROR:
                return res().getString(R.string.answering_error_message);
            case DIALOG_REPAIR_COLLECTION:
                return res().getString(R.string.repair_deck_dialog, BackupManager.BROKEN_DECKS_SUFFIX);
            case DIALOG_RESTORE_BACKUP:
                return res().getString(R.string.backup_restore_no_backups);
            case DIALOG_NEW_COLLECTION:
                return res().getString(R.string.backup_del_collection_question);
            case DIALOG_CONFIRM_DATABASE_CHECK:
                return res().getString(R.string.check_db_warning);
            case DIALOG_CONFIRM_RESTORE_BACKUP:
                return res().getString(R.string.restore_backup);
            case DIALOG_FULL_SYNC_FROM_SERVER:
                return res().getString(R.string.backup_full_sync_from_server_question);
            case DIALOG_DB_LOCKED:
                return res().getString(R.string.database_locked_summary);
            case INCOMPATIBLE_DB_VERSION:
                int databaseVersion = -1;
                try {
                    if (!ListenerUtil.mutListener.listen(578)) {
                        databaseVersion = CollectionHelper.getDatabaseVersion(requireContext());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(577)) {
                        Timber.w(e, "Failed to get database version, using -1");
                    }
                }
                return res().getString(R.string.incompatible_database_version_summary, Consts.SCHEMA_VERSION, databaseVersion);
            default:
                return getArguments().getString("dialogMessage");
        }
    }

    private String getTitle() {
        switch(getArguments().getInt("dialogType")) {
            case DIALOG_LOAD_FAILED:
                return res().getString(R.string.open_collection_failed_title);
            case DIALOG_ERROR_HANDLING:
                return res().getString(R.string.error_handling_title);
            case DIALOG_REPAIR_COLLECTION:
                return res().getString(R.string.dialog_positive_repair);
            case DIALOG_RESTORE_BACKUP:
                return res().getString(R.string.backup_restore);
            case DIALOG_NEW_COLLECTION:
                return res().getString(R.string.backup_new_collection);
            case DIALOG_CONFIRM_DATABASE_CHECK:
                return res().getString(R.string.check_db_title);
            case DIALOG_CONFIRM_RESTORE_BACKUP:
                return res().getString(R.string.restore_backup_title);
            case DIALOG_FULL_SYNC_FROM_SERVER:
                return res().getString(R.string.backup_full_sync_from_server);
            case DIALOG_DB_LOCKED:
                return res().getString(R.string.database_locked_title);
            case INCOMPATIBLE_DB_VERSION:
                return res().getString(R.string.incompatible_database_version_title);
            case DIALOG_DB_ERROR:
            default:
                return res().getString(R.string.answering_error_title);
        }
    }

    @Override
    public String getNotificationMessage() {
        return getMessage();
    }

    @Override
    public String getNotificationTitle() {
        return res().getString(R.string.answering_error_title);
    }

    @Override
    public Message getDialogHandlerMessage() {
        Message msg = Message.obtain();
        if (!ListenerUtil.mutListener.listen(579)) {
            msg.what = DialogHandler.MSG_SHOW_DATABASE_ERROR_DIALOG;
        }
        Bundle b = new Bundle();
        if (!ListenerUtil.mutListener.listen(580)) {
            b.putInt("dialogType", getArguments().getInt("dialogType"));
        }
        if (!ListenerUtil.mutListener.listen(581)) {
            msg.setData(b);
        }
        return msg;
    }

    public void dismissAllDialogFragments() {
        if (!ListenerUtil.mutListener.listen(582)) {
            ((DeckPicker) getActivity()).dismissAllDialogFragments();
        }
    }
}
