package com.ichi2.anki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import com.ichi2.anki.dialogs.DialogHandler;
import com.ichi2.anki.services.ReminderService;
import com.ichi2.utils.FunctionalInterfaces.Consumer;
import com.ichi2.utils.ImportUtils;
import com.ichi2.utils.ImportUtils.ImportResult;
import com.ichi2.utils.Permissions;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IntentHandler extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8589)) {
            // Note: This is our entry point from the launcher with intent: android.intent.action.MAIN
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(8590)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(8591)) {
            setContentView(R.layout.progress_bar);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(8592)) {
            Timber.v(intent.toString());
        }
        Intent reloadIntent = new Intent(this, DeckPicker.class);
        if (!ListenerUtil.mutListener.listen(8593)) {
            reloadIntent.setDataAndType(getIntent().getData(), getIntent().getType());
        }
        String action = intent.getAction();
        // as this requires nothing
        Consumer<Runnable> runIfStoragePermissions = (runnable) -> performActionIfStoragePermission(runnable, reloadIntent, action);
        LaunchType launchType = getLaunchType(intent);
        if (!ListenerUtil.mutListener.listen(8601)) {
            switch(launchType) {
                case FILE_IMPORT:
                    if (!ListenerUtil.mutListener.listen(8594)) {
                        runIfStoragePermissions.consume(() -> handleFileImport(intent, reloadIntent, action));
                    }
                    break;
                case SYNC:
                    if (!ListenerUtil.mutListener.listen(8595)) {
                        runIfStoragePermissions.consume(() -> handleSyncIntent(reloadIntent, action));
                    }
                    break;
                case REVIEW:
                    if (!ListenerUtil.mutListener.listen(8596)) {
                        runIfStoragePermissions.consume(() -> handleReviewIntent(intent));
                    }
                    break;
                case DEFAULT_START_APP_IF_NEW:
                    if (!ListenerUtil.mutListener.listen(8597)) {
                        Timber.d("onCreate() performing default action");
                    }
                    if (!ListenerUtil.mutListener.listen(8598)) {
                        launchDeckPickerIfNoOtherTasks(reloadIntent);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(8599)) {
                        Timber.w("Unknown launch type: %s. Performing default action", launchType);
                    }
                    if (!ListenerUtil.mutListener.listen(8600)) {
                        launchDeckPickerIfNoOtherTasks(reloadIntent);
                    }
            }
        }
    }

    private static boolean isValidViewIntent(@NonNull Intent intent) {
        // #6312 - Smart Launcher provided an empty ACTION_VIEW, no point in importing here.
        return !ImportUtils.isInvalidViewIntent(intent);
    }

    @VisibleForTesting
    @CheckResult
    static LaunchType getLaunchType(@NonNull Intent intent) {
        String action = intent.getAction();
        if ((ListenerUtil.mutListener.listen(8602) ? (Intent.ACTION_VIEW.equals(action) || isValidViewIntent(intent)) : (Intent.ACTION_VIEW.equals(action) && isValidViewIntent(intent)))) {
            return LaunchType.FILE_IMPORT;
        } else if ("com.ichi2.anki.DO_SYNC".equals(action)) {
            return LaunchType.SYNC;
        } else if (intent.hasExtra(ReminderService.EXTRA_DECK_ID)) {
            return LaunchType.REVIEW;
        } else {
            return LaunchType.DEFAULT_START_APP_IF_NEW;
        }
    }

    private void performActionIfStoragePermission(Runnable runnable, Intent reloadIntent, String action) {
        if (!ListenerUtil.mutListener.listen(8608)) {
            if (Permissions.hasStorageAccessPermission(this)) {
                if (!ListenerUtil.mutListener.listen(8606)) {
                    Timber.i("User has storage permissions. Running intent: %s", action);
                }
                if (!ListenerUtil.mutListener.listen(8607)) {
                    runnable.run();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8603)) {
                    // we get permission
                    Timber.i("No Storage Permission, cancelling intent '%s'", action);
                }
                if (!ListenerUtil.mutListener.listen(8604)) {
                    UIUtils.showThemedToast(this, getString(R.string.intent_handler_failed_no_storage_permission), false);
                }
                if (!ListenerUtil.mutListener.listen(8605)) {
                    launchDeckPickerIfNoOtherTasks(reloadIntent);
                }
            }
        }
    }

    private void handleReviewIntent(Intent intent) {
        long deckId = intent.getLongExtra(ReminderService.EXTRA_DECK_ID, 0);
        if (!ListenerUtil.mutListener.listen(8609)) {
            Timber.i("Handling intent to review deck '%d'", deckId);
        }
        final Intent reviewIntent = new Intent(this, Reviewer.class);
        if (!ListenerUtil.mutListener.listen(8610)) {
            CollectionHelper.getInstance().getCol(this).getDecks().select(deckId);
        }
        if (!ListenerUtil.mutListener.listen(8611)) {
            startActivity(reviewIntent);
        }
        if (!ListenerUtil.mutListener.listen(8612)) {
            AnkiActivity.finishActivityWithFade(this);
        }
    }

    private void handleSyncIntent(Intent reloadIntent, String action) {
        if (!ListenerUtil.mutListener.listen(8613)) {
            Timber.i("Handling Sync Intent");
        }
        if (!ListenerUtil.mutListener.listen(8614)) {
            sendDoSyncMsg();
        }
        if (!ListenerUtil.mutListener.listen(8615)) {
            reloadIntent.setAction(action);
        }
        if (!ListenerUtil.mutListener.listen(8616)) {
            reloadIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(8617)) {
            startActivity(reloadIntent);
        }
        if (!ListenerUtil.mutListener.listen(8618)) {
            AnkiActivity.finishActivityWithFade(this);
        }
    }

    private void handleFileImport(Intent intent, Intent reloadIntent, String action) {
        if (!ListenerUtil.mutListener.listen(8619)) {
            Timber.i("Handling file import");
        }
        ImportResult importResult = ImportUtils.handleFileImport(this, intent);
        if (!ListenerUtil.mutListener.listen(8627)) {
            // Start DeckPicker if we correctly processed ACTION_VIEW
            if (importResult.isSuccess()) {
                if (!ListenerUtil.mutListener.listen(8622)) {
                    Timber.d("onCreate() import successful");
                }
                if (!ListenerUtil.mutListener.listen(8623)) {
                    reloadIntent.setAction(action);
                }
                if (!ListenerUtil.mutListener.listen(8624)) {
                    reloadIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                if (!ListenerUtil.mutListener.listen(8625)) {
                    startActivity(reloadIntent);
                }
                if (!ListenerUtil.mutListener.listen(8626)) {
                    AnkiActivity.finishActivityWithFade(this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8620)) {
                    Timber.i("File import failed");
                }
                if (!ListenerUtil.mutListener.listen(8621)) {
                    // Don't import the file if it didn't load properly or doesn't have apkg extension
                    ImportUtils.showImportUnsuccessfulDialog(this, importResult.getHumanReadableMessage(), true);
                }
            }
        }
    }

    private void launchDeckPickerIfNoOtherTasks(Intent reloadIntent) {
        if (!ListenerUtil.mutListener.listen(8628)) {
            // otherwise go to previous task
            Timber.i("Launching DeckPicker");
        }
        if (!ListenerUtil.mutListener.listen(8629)) {
            reloadIntent.setAction(Intent.ACTION_MAIN);
        }
        if (!ListenerUtil.mutListener.listen(8630)) {
            reloadIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        }
        if (!ListenerUtil.mutListener.listen(8631)) {
            reloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(8632)) {
            startActivityIfNeeded(reloadIntent, 0);
        }
        if (!ListenerUtil.mutListener.listen(8633)) {
            finish();
        }
    }

    /**
     * Send a Message to AnkiDroidApp so that the DialogMessageHandler forces a sync
     */
    public static void sendDoSyncMsg() {
        // Create a new message for DialogHandler
        Message handlerMessage = Message.obtain();
        if (!ListenerUtil.mutListener.listen(8634)) {
            handlerMessage.what = DialogHandler.MSG_DO_SYNC;
        }
        if (!ListenerUtil.mutListener.listen(8635)) {
            // Store the message in AnkiDroidApp message holder, which is loaded later in AnkiActivity.onResume
            DialogHandler.storeMessage(handlerMessage);
        }
    }

    // COULD_BE_BETTER: Also extract the parameters into here to reduce coupling
    @VisibleForTesting
    enum LaunchType {

        DEFAULT_START_APP_IF_NEW, FILE_IMPORT, SYNC, REVIEW
    }
}
