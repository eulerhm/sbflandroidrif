package com.ichi2.anki.dialogs;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.ichi2.anki.AnkiActivity;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.NotificationChannels;
import com.ichi2.anki.R;
import com.ichi2.async.Connection;
import com.ichi2.libanki.Collection;
import com.ichi2.anki.analytics.UsageAnalytics;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * We're not allowed to commit fragment transactions from Loader.onLoadCompleted(),
 * and it's unsafe to commit them from an AsyncTask onComplete event, so we work
 * around this by using a message handler.
 */
public class DialogHandler extends Handler {

    // 2min minimum sync interval
    public static final long INTENT_SYNC_MIN_INTERVAL = 2 * 60000;

    /**
     * Handler messages
     */
    public static final int MSG_SHOW_COLLECTION_LOADING_ERROR_DIALOG = 0;

    public static final int MSG_SHOW_COLLECTION_IMPORT_REPLACE_DIALOG = 1;

    public static final int MSG_SHOW_COLLECTION_IMPORT_ADD_DIALOG = 2;

    public static final int MSG_SHOW_SYNC_ERROR_DIALOG = 3;

    public static final int MSG_SHOW_EXPORT_COMPLETE_DIALOG = 4;

    public static final int MSG_SHOW_MEDIA_CHECK_COMPLETE_DIALOG = 5;

    public static final int MSG_SHOW_DATABASE_ERROR_DIALOG = 6;

    public static final int MSG_SHOW_FORCE_FULL_SYNC_DIALOG = 7;

    public static final int MSG_DO_SYNC = 8;

    public static final String[] sMessageNameList = { "CollectionLoadErrorDialog", "ImportReplaceDialog", "ImportAddDialog", "SyncErrorDialog", "ExportCompleteDialog", "MediaCheckCompleteDialog", "DatabaseErrorDialog", "ForceFullSyncDialog", "DoSyncDialog" };

    final WeakReference<AnkiActivity> mActivity;

    private static Message sStoredMessage;

    public DialogHandler(AnkiActivity activity) {
        // Use weak reference to main activity to prevent leaking the activity when it's closed
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle msgData = msg.getData();
        String messageName = sMessageNameList[msg.what];
        if (!ListenerUtil.mutListener.listen(683)) {
            UsageAnalytics.sendAnalyticsScreenView(messageName);
        }
        if (!ListenerUtil.mutListener.listen(684)) {
            Timber.i("Handling Message: %s", messageName);
        }
        if (!ListenerUtil.mutListener.listen(734)) {
            if (msg.what == MSG_SHOW_COLLECTION_LOADING_ERROR_DIALOG) {
                if (!ListenerUtil.mutListener.listen(733)) {
                    // Collection could not be opened
                    ((DeckPicker) mActivity.get()).showDatabaseErrorDialog(DatabaseErrorDialog.DIALOG_LOAD_FAILED);
                }
            } else if (msg.what == MSG_SHOW_COLLECTION_IMPORT_REPLACE_DIALOG) {
                if (!ListenerUtil.mutListener.listen(732)) {
                    // Handle import of collection package APKG
                    ((DeckPicker) mActivity.get()).showImportDialog(ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM, msgData.getString("importPath"));
                }
            } else if (msg.what == MSG_SHOW_COLLECTION_IMPORT_ADD_DIALOG) {
                if (!ListenerUtil.mutListener.listen(731)) {
                    // Handle import of deck package APKG
                    ((DeckPicker) mActivity.get()).showImportDialog(ImportDialog.DIALOG_IMPORT_ADD_CONFIRM, msgData.getString("importPath"));
                }
            } else if (msg.what == MSG_SHOW_SYNC_ERROR_DIALOG) {
                int id = msgData.getInt("dialogType");
                String message = msgData.getString("dialogMessage");
                if (!ListenerUtil.mutListener.listen(730)) {
                    ((DeckPicker) mActivity.get()).showSyncErrorDialog(id, message);
                }
            } else if (msg.what == MSG_SHOW_EXPORT_COMPLETE_DIALOG) {
                // Export complete
                AsyncDialogFragment f = DeckPickerExportCompleteDialog.newInstance(msgData.getString("exportPath"));
                if (!ListenerUtil.mutListener.listen(729)) {
                    mActivity.get().showAsyncDialogFragment(f);
                }
            } else if (msg.what == MSG_SHOW_MEDIA_CHECK_COMPLETE_DIALOG) {
                // Media check results
                int id = msgData.getInt("dialogType");
                if (!ListenerUtil.mutListener.listen(728)) {
                    if ((ListenerUtil.mutListener.listen(723) ? (id >= MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK) : (ListenerUtil.mutListener.listen(722) ? (id <= MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK) : (ListenerUtil.mutListener.listen(721) ? (id > MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK) : (ListenerUtil.mutListener.listen(720) ? (id < MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK) : (ListenerUtil.mutListener.listen(719) ? (id == MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK) : (id != MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK))))))) {
                        List<List<String>> checkList = new ArrayList<>(3);
                        if (!ListenerUtil.mutListener.listen(724)) {
                            checkList.add(msgData.getStringArrayList("nohave"));
                        }
                        if (!ListenerUtil.mutListener.listen(725)) {
                            checkList.add(msgData.getStringArrayList("unused"));
                        }
                        if (!ListenerUtil.mutListener.listen(726)) {
                            checkList.add(msgData.getStringArrayList("invalid"));
                        }
                        if (!ListenerUtil.mutListener.listen(727)) {
                            ((DeckPicker) mActivity.get()).showMediaCheckDialog(id, checkList);
                        }
                    }
                }
            } else if (msg.what == MSG_SHOW_DATABASE_ERROR_DIALOG) {
                if (!ListenerUtil.mutListener.listen(718)) {
                    // Database error dialog
                    ((DeckPicker) mActivity.get()).showDatabaseErrorDialog(msgData.getInt("dialogType"));
                }
            } else if (msg.what == MSG_SHOW_FORCE_FULL_SYNC_DIALOG) {
                // Confirmation dialog for forcing full sync
                ConfirmationDialog dialog = new ConfirmationDialog();
                Runnable confirm = () -> {
                    // Bypass the check once the user confirms
                    CollectionHelper.getInstance().getCol(AnkiDroidApp.getInstance()).modSchemaNoCheck();
                };
                if (!ListenerUtil.mutListener.listen(715)) {
                    dialog.setConfirm(confirm);
                }
                if (!ListenerUtil.mutListener.listen(716)) {
                    dialog.setArgs(msgData.getString("message"));
                }
                if (!ListenerUtil.mutListener.listen(717)) {
                    (mActivity.get()).showDialogFragment(dialog);
                }
            } else if (msg.what == MSG_DO_SYNC) {
                SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(mActivity.get());
                Resources res = mActivity.get().getResources();
                Collection col = mActivity.get().getCol();
                String hkey = preferences.getString("hkey", "");
                long millisecondsSinceLastSync = (ListenerUtil.mutListener.listen(688) ? (col.getTime().intTimeMS() % preferences.getLong("lastSyncTime", 0)) : (ListenerUtil.mutListener.listen(687) ? (col.getTime().intTimeMS() / preferences.getLong("lastSyncTime", 0)) : (ListenerUtil.mutListener.listen(686) ? (col.getTime().intTimeMS() * preferences.getLong("lastSyncTime", 0)) : (ListenerUtil.mutListener.listen(685) ? (col.getTime().intTimeMS() + preferences.getLong("lastSyncTime", 0)) : (col.getTime().intTimeMS() - preferences.getLong("lastSyncTime", 0))))));
                boolean limited = (ListenerUtil.mutListener.listen(693) ? (millisecondsSinceLastSync >= INTENT_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(692) ? (millisecondsSinceLastSync <= INTENT_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(691) ? (millisecondsSinceLastSync > INTENT_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(690) ? (millisecondsSinceLastSync != INTENT_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(689) ? (millisecondsSinceLastSync == INTENT_SYNC_MIN_INTERVAL) : (millisecondsSinceLastSync < INTENT_SYNC_MIN_INTERVAL))))));
                if (!ListenerUtil.mutListener.listen(713)) {
                    if ((ListenerUtil.mutListener.listen(700) ? ((ListenerUtil.mutListener.listen(699) ? (!limited || (ListenerUtil.mutListener.listen(698) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(697) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(696) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(695) ? (hkey.length() != 0) : (ListenerUtil.mutListener.listen(694) ? (hkey.length() == 0) : (hkey.length() > 0))))))) : (!limited && (ListenerUtil.mutListener.listen(698) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(697) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(696) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(695) ? (hkey.length() != 0) : (ListenerUtil.mutListener.listen(694) ? (hkey.length() == 0) : (hkey.length() > 0)))))))) || Connection.isOnline()) : ((ListenerUtil.mutListener.listen(699) ? (!limited || (ListenerUtil.mutListener.listen(698) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(697) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(696) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(695) ? (hkey.length() != 0) : (ListenerUtil.mutListener.listen(694) ? (hkey.length() == 0) : (hkey.length() > 0))))))) : (!limited && (ListenerUtil.mutListener.listen(698) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(697) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(696) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(695) ? (hkey.length() != 0) : (ListenerUtil.mutListener.listen(694) ? (hkey.length() == 0) : (hkey.length() > 0)))))))) && Connection.isOnline()))) {
                        if (!ListenerUtil.mutListener.listen(712)) {
                            ((DeckPicker) mActivity.get()).sync();
                        }
                    } else {
                        String err = res.getString(R.string.sync_error);
                        if (!ListenerUtil.mutListener.listen(711)) {
                            if (limited) {
                                long remainingTimeInSeconds = Math.max((ListenerUtil.mutListener.listen(709) ? (((ListenerUtil.mutListener.listen(705) ? (INTENT_SYNC_MIN_INTERVAL % millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(704) ? (INTENT_SYNC_MIN_INTERVAL / millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(703) ? (INTENT_SYNC_MIN_INTERVAL * millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(702) ? (INTENT_SYNC_MIN_INTERVAL + millisecondsSinceLastSync) : (INTENT_SYNC_MIN_INTERVAL - millisecondsSinceLastSync)))))) % 1000) : (ListenerUtil.mutListener.listen(708) ? (((ListenerUtil.mutListener.listen(705) ? (INTENT_SYNC_MIN_INTERVAL % millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(704) ? (INTENT_SYNC_MIN_INTERVAL / millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(703) ? (INTENT_SYNC_MIN_INTERVAL * millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(702) ? (INTENT_SYNC_MIN_INTERVAL + millisecondsSinceLastSync) : (INTENT_SYNC_MIN_INTERVAL - millisecondsSinceLastSync)))))) * 1000) : (ListenerUtil.mutListener.listen(707) ? (((ListenerUtil.mutListener.listen(705) ? (INTENT_SYNC_MIN_INTERVAL % millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(704) ? (INTENT_SYNC_MIN_INTERVAL / millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(703) ? (INTENT_SYNC_MIN_INTERVAL * millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(702) ? (INTENT_SYNC_MIN_INTERVAL + millisecondsSinceLastSync) : (INTENT_SYNC_MIN_INTERVAL - millisecondsSinceLastSync)))))) - 1000) : (ListenerUtil.mutListener.listen(706) ? (((ListenerUtil.mutListener.listen(705) ? (INTENT_SYNC_MIN_INTERVAL % millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(704) ? (INTENT_SYNC_MIN_INTERVAL / millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(703) ? (INTENT_SYNC_MIN_INTERVAL * millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(702) ? (INTENT_SYNC_MIN_INTERVAL + millisecondsSinceLastSync) : (INTENT_SYNC_MIN_INTERVAL - millisecondsSinceLastSync)))))) + 1000) : (((ListenerUtil.mutListener.listen(705) ? (INTENT_SYNC_MIN_INTERVAL % millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(704) ? (INTENT_SYNC_MIN_INTERVAL / millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(703) ? (INTENT_SYNC_MIN_INTERVAL * millisecondsSinceLastSync) : (ListenerUtil.mutListener.listen(702) ? (INTENT_SYNC_MIN_INTERVAL + millisecondsSinceLastSync) : (INTENT_SYNC_MIN_INTERVAL - millisecondsSinceLastSync)))))) / 1000))))), 1);
                                // getQuantityString needs an int
                                int remaining = (int) Math.min(Integer.MAX_VALUE, remainingTimeInSeconds);
                                String message = res.getQuantityString(R.plurals.sync_automatic_sync_needs_more_time, remaining, remaining);
                                if (!ListenerUtil.mutListener.listen(710)) {
                                    mActivity.get().showSimpleNotification(err, message, NotificationChannels.Channel.SYNC);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(701)) {
                                    mActivity.get().showSimpleNotification(err, res.getString(R.string.youre_offline), NotificationChannels.Channel.SYNC);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(714)) {
                    mActivity.get().finishWithoutAnimation();
                }
            }
        }
    }

    /**
     * Store a persistent message to static variable
     * @param message Message to store
     */
    public static void storeMessage(Message message) {
        if (!ListenerUtil.mutListener.listen(735)) {
            Timber.d("Storing persistent message");
        }
        if (!ListenerUtil.mutListener.listen(736)) {
            sStoredMessage = message;
        }
    }

    /**
     * Read and handle Message which was stored via storeMessage()
     */
    public void readMessage() {
        if (!ListenerUtil.mutListener.listen(737)) {
            Timber.d("Reading persistent message");
        }
        if (!ListenerUtil.mutListener.listen(740)) {
            if (sStoredMessage != null) {
                if (!ListenerUtil.mutListener.listen(738)) {
                    Timber.i("Dispatching persistent message: %d", sStoredMessage.what);
                }
                if (!ListenerUtil.mutListener.listen(739)) {
                    sendMessage(sStoredMessage);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(741)) {
            sStoredMessage = null;
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void discardMessage() {
        if (!ListenerUtil.mutListener.listen(742)) {
            sStoredMessage = null;
        }
    }
}
