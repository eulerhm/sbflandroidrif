/**
 * *************************************************************************************
 *  Copyright (c) 2009 Andrew Dubya <andrewdubya@gmail.com>                              *
 *  Copyright (c) 2009 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2009 Daniel Svard <daniel.svard@gmail.com>                             *
 *  Copyright (c) 2010 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import com.afollestad.materialdialogs.GravityEnum;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.app.ShareCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.ichi2.anki.CollectionHelper.CollectionIntegrityStorageCheck;
import com.ichi2.anki.StudyOptionsFragment.StudyOptionsListener;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.anki.dialogs.AsyncDialogFragment;
import com.ichi2.anki.dialogs.ConfirmationDialog;
import com.ichi2.anki.dialogs.CustomStudyDialog;
import com.ichi2.anki.dialogs.DatabaseErrorDialog;
import com.ichi2.anki.dialogs.DeckPickerAnalyticsOptInDialog;
import com.ichi2.anki.dialogs.DeckPickerBackupNoSpaceLeftDialog;
import com.ichi2.anki.dialogs.DeckPickerConfirmDeleteDeckDialog;
import com.ichi2.anki.dialogs.DeckPickerContextMenu;
import com.ichi2.anki.dialogs.DeckPickerExportCompleteDialog;
import com.ichi2.anki.dialogs.DeckPickerNoSpaceLeftDialog;
import com.ichi2.anki.dialogs.DialogHandler;
import com.ichi2.anki.dialogs.ExportDialog;
import com.ichi2.anki.dialogs.ImportDialog;
import com.ichi2.anki.dialogs.MediaCheckDialog;
import com.ichi2.anki.dialogs.SyncErrorDialog;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.exception.DeckRenameException;
import com.ichi2.anki.receiver.SdCardReceiver;
import com.ichi2.anki.stats.AnkiStatsTaskHandler;
import com.ichi2.anki.web.HostNumFactory;
import com.ichi2.anki.widgets.DeckAdapter;
import com.ichi2.async.Connection;
import com.ichi2.async.Connection.Payload;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListener;
import com.ichi2.async.TaskListenerWithContext;
import com.ichi2.async.TaskManager;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Models;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.importer.AnkiPackageImporter;
import com.ichi2.libanki.sched.AbstractDeckTreeNode;
import com.ichi2.libanki.sched.DeckTreeNode;
import com.ichi2.libanki.sync.CustomSyncServerUrlException;
import com.ichi2.libanki.sync.Syncer;
import com.ichi2.libanki.utils.TimeUtils;
import com.ichi2.themes.StyledProgressDialog;
import com.ichi2.ui.BadgeDrawableBuilder;
import com.ichi2.ui.FixedEditText;
import com.ichi2.utils.AdaptionUtil;
import com.ichi2.utils.BooleanGetter;
import com.ichi2.utils.ImportUtils;
import com.ichi2.utils.PairWithBoolean;
import com.ichi2.utils.Permissions;
import com.ichi2.utils.SyncStatus;
import com.ichi2.utils.Triple;
import com.ichi2.utils.VersionUtils;
import com.ichi2.widget.WidgetStatus;
import com.ichi2.utils.JSONException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import timber.log.Timber;
import static com.ichi2.async.Connection.ConflictResolution.FULL_DOWNLOAD;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckPicker extends NavigationDrawerActivity implements StudyOptionsListener, SyncErrorDialog.SyncErrorDialogListener, ImportDialog.ImportDialogListener, MediaCheckDialog.MediaCheckDialogListener, ExportDialog.ExportDialogListener, ActivityCompat.OnRequestPermissionsResultCallback, CustomStudyDialog.CustomStudyListener {

    /**
     * Result codes from other activities
     */
    public static final int RESULT_MEDIA_EJECTED = 202;

    public static final int RESULT_DB_ERROR = 203;

    protected static final String UPGRADE_VERSION_KEY = "lastUpgradeVersion";

    /**
     * Available options performed by other activities (request codes for onActivityResult())
     */
    private static final int REQUEST_STORAGE_PERMISSION = 0;

    private static final int REQUEST_PATH_UPDATE = 1;

    public static final int REPORT_FEEDBACK = 4;

    private static final int LOG_IN_FOR_SYNC = 6;

    private static final int SHOW_INFO_NEW_VERSION = 9;

    public static final int SHOW_STUDYOPTIONS = 11;

    private static final int ADD_NOTE = 12;

    private static final int PICK_APKG_FILE = 13;

    private static final int PICK_EXPORT_FILE = 14;

    // 10 minutes in milliseconds.
    public static final long AUTOMATIC_SYNC_MIN_INTERVAL = 600000;

    private static final int SWIPE_TO_SYNC_TRIGGER_DISTANCE = 400;

    // Short animation duration from system
    private int mShortAnimDuration;

    private RelativeLayout mDeckPickerContent;

    private MaterialDialog mProgressDialog;

    private View mStudyoptionsFrame;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mRecyclerViewLayoutManager;

    private DeckAdapter mDeckListAdapter;

    private FloatingActionsMenu mActionsMenu;

    private final Snackbar.Callback mSnackbarShowHideCallback = new Snackbar.Callback();

    private LinearLayout mNoDecksPlaceholder;

    private SwipeRefreshLayout mPullToSyncWrapper;

    private TextView mReviewSummaryTextView;

    private BroadcastReceiver mUnmountReceiver = null;

    private long mContextMenuDid;

    private EditText mDialogEditText;

    // flag asking user to do a full sync which is used in upgrade path
    private boolean mRecommendFullSync = false;

    // flag keeping track of when the app has been paused
    private boolean mActivityPaused = false;

    private String mExportFileName;

    @Nullable
    private CollectionTask<?, ?, ?, ?> mEmptyCardTask = null;

    @VisibleForTesting
    public List<? extends AbstractDeckTreeNode<?>> mDueTree;

    /**
     * Flag to indicate whether the activity will perform a sync in its onResume.
     * Since syncing closes the database, this flag allows us to avoid doing any
     * work in onResume that might use the database and go straight to syncing.
     */
    private boolean mSyncOnResume = false;

    /**
     * Keep track of which deck was last given focus in the deck list. If we find that this value
     * has changed between deck list refreshes, we need to recenter the deck list to the new current
     * deck.
     */
    private long mFocusedDeck;

    /**
     * If we have accepted the "We will show you permissions" dialog, don't show it again on activity rebirth
     */
    private boolean mClosedWelcomeMessage;

    private SearchView mToolbarSearchView;

    private final OnClickListener mDeckExpanderClickListener = view -> {
        Long did = (Long) view.getTag();
        if (getCol().getDecks().children(did).size() > 0) {
            getCol().getDecks().collapse(did);
            __renderPage();
            dismissAllDialogFragments();
        }
    };

    private final OnClickListener mDeckClickListener = v -> onDeckClick(v, DeckSelectionType.DEFAULT);

    private final OnClickListener mCountsClickListener = v -> onDeckClick(v, DeckSelectionType.SHOW_STUDY_OPTIONS);

    private void onDeckClick(View v, DeckSelectionType selectionType) {
        long deckId = (long) v.getTag();
        if (!ListenerUtil.mutListener.listen(7104)) {
            Timber.i("DeckPicker:: Selected deck with id %d", deckId);
        }
        if (!ListenerUtil.mutListener.listen(7107)) {
            if ((ListenerUtil.mutListener.listen(7105) ? (mActionsMenu != null || mActionsMenu.isExpanded()) : (mActionsMenu != null && mActionsMenu.isExpanded()))) {
                if (!ListenerUtil.mutListener.listen(7106)) {
                    mActionsMenu.collapse();
                }
            }
        }
        boolean collectionIsOpen = false;
        try {
            if (!ListenerUtil.mutListener.listen(7110)) {
                collectionIsOpen = colIsOpen();
            }
            if (!ListenerUtil.mutListener.listen(7111)) {
                handleDeckSelection(deckId, selectionType);
            }
            if (!ListenerUtil.mutListener.listen(7113)) {
                if (mFragmented) {
                    if (!ListenerUtil.mutListener.listen(7112)) {
                        // This interferes with the ripple effect, so we don't do it if lollipop and not tablet view
                        mDeckListAdapter.notifyDataSetChanged();
                    }
                }
            }
        } catch (Exception e) {
            // Maybe later don't report if collectionIsOpen is false?
            String info = deckId + " colOpen:" + collectionIsOpen;
            if (!ListenerUtil.mutListener.listen(7108)) {
                AnkiDroidApp.sendExceptionReport(e, "deckPicker::onDeckClick", info);
            }
            if (!ListenerUtil.mutListener.listen(7109)) {
                displayFailedToOpenDeck(deckId);
            }
        }
    }

    private void displayFailedToOpenDeck(long deckId) {
        // We use the Deck ID as the deck likely doesn't exist any more.
        String message = getString(R.string.deck_picker_failed_deck_load, Long.toString(deckId));
        if (!ListenerUtil.mutListener.listen(7114)) {
            UIUtils.showThemedToast(this, message, false);
        }
        if (!ListenerUtil.mutListener.listen(7115)) {
            Timber.w(message);
        }
    }

    private final View.OnLongClickListener mDeckLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            long deckId = (long) v.getTag();
            if (!ListenerUtil.mutListener.listen(7116)) {
                Timber.i("DeckPicker:: Long tapped on deck with id %d", deckId);
            }
            if (!ListenerUtil.mutListener.listen(7117)) {
                mContextMenuDid = deckId;
            }
            if (!ListenerUtil.mutListener.listen(7118)) {
                showDialogFragment(DeckPickerContextMenu.newInstance(deckId));
            }
            return true;
        }
    };

    private final ImportAddListener mImportAddListener = new ImportAddListener(this);

    private static class ImportAddListener extends TaskListenerWithContext<DeckPicker, String, Triple<AnkiPackageImporter, Boolean, String>> {

        public ImportAddListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, Triple<AnkiPackageImporter, Boolean, String> result) {
            if (!ListenerUtil.mutListener.listen(7121)) {
                if ((ListenerUtil.mutListener.listen(7119) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7120)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7128)) {
                // instead of a successful result.
                if ((ListenerUtil.mutListener.listen(7122) ? (result.second || result.third != null) : (result.second && result.third != null))) {
                    if (!ListenerUtil.mutListener.listen(7126)) {
                        Timber.w("Import: Add Failed: %s", result.third);
                    }
                    if (!ListenerUtil.mutListener.listen(7127)) {
                        deckPicker.showSimpleMessageDialog(result.third);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7123)) {
                        Timber.i("Import: Add succeeded");
                    }
                    AnkiPackageImporter imp = result.first;
                    if (!ListenerUtil.mutListener.listen(7124)) {
                        deckPicker.showSimpleMessageDialog(TextUtils.join("\n", imp.getLog()));
                    }
                    if (!ListenerUtil.mutListener.listen(7125)) {
                        deckPicker.updateDeckList();
                    }
                }
            }
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7131)) {
                if ((ListenerUtil.mutListener.listen(7129) ? (deckPicker.mProgressDialog == null && !deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog == null || !deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7130)) {
                        deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, deckPicker.getResources().getString(R.string.import_title), null, false);
                    }
                }
            }
        }

        @Override
        public void actualOnProgressUpdate(@NonNull DeckPicker deckPicker, String content) {
            if (!ListenerUtil.mutListener.listen(7132)) {
                deckPicker.mProgressDialog.setContent(content);
            }
        }
    }

    private ImportReplaceListener importReplaceListener() {
        return new ImportReplaceListener(this);
    }

    private static class ImportReplaceListener extends TaskListenerWithContext<DeckPicker, String, BooleanGetter> {

        public ImportReplaceListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, BooleanGetter result) {
            if (!ListenerUtil.mutListener.listen(7133)) {
                Timber.i("Import: Replace Task Completed");
            }
            if (!ListenerUtil.mutListener.listen(7136)) {
                if ((ListenerUtil.mutListener.listen(7134) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7135)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
            Resources res = deckPicker.getResources();
            if (!ListenerUtil.mutListener.listen(7139)) {
                if (result.getBoolean()) {
                    if (!ListenerUtil.mutListener.listen(7138)) {
                        deckPicker.updateDeckList();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7137)) {
                        deckPicker.showSimpleMessageDialog(res.getString(R.string.import_log_no_apkg), true);
                    }
                }
            }
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7142)) {
                if ((ListenerUtil.mutListener.listen(7140) ? (deckPicker.mProgressDialog == null && !deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog == null || !deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7141)) {
                        deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, deckPicker.getResources().getString(R.string.import_title), deckPicker.getResources().getString(R.string.import_replacing), false);
                    }
                }
            }
        }

        @Override
        public void actualOnProgressUpdate(@NonNull DeckPicker deckPicker, String message) {
            if (!ListenerUtil.mutListener.listen(7143)) {
                deckPicker.mProgressDialog.setContent(message);
            }
        }
    }

    private ExportListener exportListener() {
        return new ExportListener(this);
    }

    private static class ExportListener extends TaskListenerWithContext<DeckPicker, Void, Pair<Boolean, String>> {

        public ExportListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7144)) {
                deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, "", deckPicker.getResources().getString(R.string.export_in_progress), false);
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, Pair<Boolean, String> result) {
            if (!ListenerUtil.mutListener.listen(7147)) {
                if ((ListenerUtil.mutListener.listen(7145) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7146)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7155)) {
                // instead of a successful result.
                if ((ListenerUtil.mutListener.listen(7148) ? (result.first || result.second != null) : (result.first && result.second != null))) {
                    if (!ListenerUtil.mutListener.listen(7153)) {
                        Timber.w("Export Failed: %s", result.second);
                    }
                    if (!ListenerUtil.mutListener.listen(7154)) {
                        deckPicker.showSimpleMessageDialog(result.second);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7149)) {
                        Timber.i("Export successful");
                    }
                    String exportPath = result.second;
                    if (!ListenerUtil.mutListener.listen(7152)) {
                        if (exportPath != null) {
                            if (!ListenerUtil.mutListener.listen(7151)) {
                                deckPicker.showAsyncDialogFragment(DeckPickerExportCompleteDialog.newInstance(exportPath));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7150)) {
                                UIUtils.showThemedToast(deckPicker, deckPicker.getResources().getString(R.string.export_unsuccessful), true);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) throws SQLException {
        if (!ListenerUtil.mutListener.listen(7156)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(7157)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        if (!ListenerUtil.mutListener.listen(7158)) {
            // we need to restore here, as we need it before super.onCreate() is called.
            restoreWelcomeMessage(savedInstanceState);
        }
        // Open Collection on UI thread while splash screen is showing
        boolean colOpen = firstCollectionOpen();
        if (!ListenerUtil.mutListener.listen(7159)) {
            // Then set theme and content view
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7160)) {
            setContentView(R.layout.homescreen);
        }
        View mainView = findViewById(android.R.id.content);
        if (!ListenerUtil.mutListener.listen(7161)) {
            // check, if tablet layout
            mStudyoptionsFrame = findViewById(R.id.studyoptions_fragment);
        }
        if (!ListenerUtil.mutListener.listen(7163)) {
            // set protected variable from NavigationDrawerActivity
            mFragmented = (ListenerUtil.mutListener.listen(7162) ? (mStudyoptionsFrame != null || mStudyoptionsFrame.getVisibility() == View.VISIBLE) : (mStudyoptionsFrame != null && mStudyoptionsFrame.getVisibility() == View.VISIBLE));
        }
        if (!ListenerUtil.mutListener.listen(7164)) {
            registerExternalStorageListener();
        }
        if (!ListenerUtil.mutListener.listen(7165)) {
            // create inherited navigation drawer layout here so that it can be used by parent class
            initNavigationDrawer(mainView);
        }
        if (!ListenerUtil.mutListener.listen(7166)) {
            setTitle(getResources().getString(R.string.app_name));
        }
        if (!ListenerUtil.mutListener.listen(7167)) {
            mDeckPickerContent = findViewById(R.id.deck_picker_content);
        }
        if (!ListenerUtil.mutListener.listen(7168)) {
            mRecyclerView = findViewById(R.id.files);
        }
        if (!ListenerUtil.mutListener.listen(7169)) {
            mNoDecksPlaceholder = findViewById(R.id.no_decks_placeholder);
        }
        if (!ListenerUtil.mutListener.listen(7170)) {
            mDeckPickerContent.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7171)) {
            mNoDecksPlaceholder.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7172)) {
            // specify a LinearLayoutManager and set up item dividers for the RecyclerView
            mRecyclerViewLayoutManager = new LinearLayoutManager(this);
        }
        if (!ListenerUtil.mutListener.listen(7173)) {
            mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        }
        TypedArray ta = this.obtainStyledAttributes(new int[] { R.attr.deckDivider });
        Drawable divider = ta.getDrawable(0);
        if (!ListenerUtil.mutListener.listen(7174)) {
            ta.recycle();
        }
        DividerItemDecoration dividerDecorator = new DividerItemDecoration(this, mRecyclerViewLayoutManager.getOrientation());
        if (!ListenerUtil.mutListener.listen(7175)) {
            dividerDecorator.setDrawable(divider);
        }
        if (!ListenerUtil.mutListener.listen(7176)) {
            mRecyclerView.addItemDecoration(dividerDecorator);
        }
        // Add background to Deckpicker activity
        View view = mFragmented ? findViewById(R.id.deckpicker_view) : findViewById(R.id.root_layout);
        boolean hasDeckPickerBackground = false;
        try {
            if (!ListenerUtil.mutListener.listen(7181)) {
                hasDeckPickerBackground = applyDeckPickerBackground(view);
            }
        } catch (OutOfMemoryError e) {
            if (!ListenerUtil.mutListener.listen(7177)) {
                // 6608 - OOM should be catchable here.
                Timber.w(e, "Failed to apply background - OOM");
            }
            if (!ListenerUtil.mutListener.listen(7178)) {
                UIUtils.showThemedToast(this, getString(R.string.background_image_too_large), false);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7179)) {
                Timber.w(e, "Failed to apply background");
            }
            if (!ListenerUtil.mutListener.listen(7180)) {
                UIUtils.showThemedToast(this, getString(R.string.failed_to_apply_background_image, e.getLocalizedMessage()), false);
            }
        }
        if (!ListenerUtil.mutListener.listen(7182)) {
            // create and set an adapter for the RecyclerView
            mDeckListAdapter = new DeckAdapter(getLayoutInflater(), this);
        }
        if (!ListenerUtil.mutListener.listen(7183)) {
            mDeckListAdapter.setDeckClickListener(mDeckClickListener);
        }
        if (!ListenerUtil.mutListener.listen(7184)) {
            mDeckListAdapter.setCountsClickListener(mCountsClickListener);
        }
        if (!ListenerUtil.mutListener.listen(7185)) {
            mDeckListAdapter.setDeckExpanderClickListener(mDeckExpanderClickListener);
        }
        if (!ListenerUtil.mutListener.listen(7186)) {
            mDeckListAdapter.setDeckLongClickListener(mDeckLongClickListener);
        }
        if (!ListenerUtil.mutListener.listen(7187)) {
            mDeckListAdapter.enablePartialTransparencyForBackground(hasDeckPickerBackground);
        }
        if (!ListenerUtil.mutListener.listen(7188)) {
            mRecyclerView.setAdapter(mDeckListAdapter);
        }
        if (!ListenerUtil.mutListener.listen(7189)) {
            mPullToSyncWrapper = findViewById(R.id.pull_to_sync_wrapper);
        }
        if (!ListenerUtil.mutListener.listen(7190)) {
            mPullToSyncWrapper.setDistanceToTriggerSync(SWIPE_TO_SYNC_TRIGGER_DISTANCE);
        }
        if (!ListenerUtil.mutListener.listen(7191)) {
            mPullToSyncWrapper.setOnRefreshListener(() -> {
                Timber.i("Pull to Sync: Syncing");
                mPullToSyncWrapper.setRefreshing(false);
                sync();
            });
        }
        if (!ListenerUtil.mutListener.listen(7192)) {
            mPullToSyncWrapper.getViewTreeObserver().addOnScrollChangedListener(() -> mPullToSyncWrapper.setEnabled(mRecyclerViewLayoutManager.findFirstCompletelyVisibleItemPosition() == 0));
        }
        if (!ListenerUtil.mutListener.listen(7193)) {
            // Setup the FloatingActionButtons, should work everywhere with min API >= 15
            mActionsMenu = findViewById(R.id.add_content_menu);
        }
        if (!ListenerUtil.mutListener.listen(7194)) {
            mActionsMenu.findViewById(R.id.fab_expand_menu_button).setContentDescription(getString(R.string.menu_add));
        }
        if (!ListenerUtil.mutListener.listen(7195)) {
            configureFloatingActionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(7196)) {
            mReviewSummaryTextView = findViewById(R.id.today_stats_text_view);
        }
        if (!ListenerUtil.mutListener.listen(7197)) {
            Timber.i("colOpen: %b", colOpen);
        }
        if (!ListenerUtil.mutListener.listen(7210)) {
            if (colOpen) {
                if (!ListenerUtil.mutListener.listen(7209)) {
                    // Show any necessary dialogs (e.g. changelog, special messages, etc)
                    showStartupScreensAndDialogs(preferences, 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7208)) {
                    // Show error dialogs
                    if (Permissions.hasStorageAccessPermission(this)) {
                        if (!ListenerUtil.mutListener.listen(7207)) {
                            if (!AnkiDroidApp.isSdCardMounted()) {
                                if (!ListenerUtil.mutListener.listen(7205)) {
                                    Timber.i("SD card not mounted");
                                }
                                if (!ListenerUtil.mutListener.listen(7206)) {
                                    onSdCardNotMounted();
                                }
                            } else if (!CollectionHelper.isCurrentAnkiDroidDirAccessible(this)) {
                                if (!ListenerUtil.mutListener.listen(7202)) {
                                    Timber.i("AnkiDroid directory inaccessible");
                                }
                                Intent i = Preferences.getPreferenceSubscreenIntent(this, "com.ichi2.anki.prefs.advanced");
                                if (!ListenerUtil.mutListener.listen(7203)) {
                                    startActivityForResultWithoutAnimation(i, REQUEST_PATH_UPDATE);
                                }
                                if (!ListenerUtil.mutListener.listen(7204)) {
                                    Toast.makeText(this, R.string.directory_inaccessible, Toast.LENGTH_LONG).show();
                                }
                            } else if (isFutureAnkiDroidVersion()) {
                                if (!ListenerUtil.mutListener.listen(7200)) {
                                    Timber.i("Displaying database versioning");
                                }
                                if (!ListenerUtil.mutListener.listen(7201)) {
                                    showDatabaseErrorDialog(DatabaseErrorDialog.INCOMPATIBLE_DB_VERSION);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7198)) {
                                    Timber.i("Displaying database error");
                                }
                                if (!ListenerUtil.mutListener.listen(7199)) {
                                    showDatabaseErrorDialog(DatabaseErrorDialog.DIALOG_LOAD_FAILED);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7211)) {
            mShortAnimDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        }
    }

    private boolean isFutureAnkiDroidVersion() {
        try {
            return CollectionHelper.isFutureAnkiDroidVersion(this);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7212)) {
                Timber.w(e, "Could not determine if future AnkiDroid version - assuming not");
            }
            return false;
        }
    }

    // throws doesn't seem to be checked by the compiler - consider it to be documentation
    private boolean applyDeckPickerBackground(View view) throws OutOfMemoryError {
        // Allow the user to clear data and get back to a good state if they provide an invalid background.
        if (!AnkiDroidApp.getSharedPrefs(this).getBoolean("deckPickerBackground", false)) {
            if (!ListenerUtil.mutListener.listen(7213)) {
                Timber.d("No DeckPicker background preference");
            }
            if (!ListenerUtil.mutListener.listen(7214)) {
                view.setBackgroundResource(0);
            }
            return false;
        }
        String currentAnkiDroidDirectory = CollectionHelper.getCurrentAnkiDroidDirectory(this);
        File imgFile = new File(currentAnkiDroidDirectory, "DeckPickerBackground.png");
        if (!imgFile.exists()) {
            if (!ListenerUtil.mutListener.listen(7217)) {
                Timber.d("No DeckPicker background image");
            }
            if (!ListenerUtil.mutListener.listen(7218)) {
                view.setBackgroundResource(0);
            }
            return false;
        } else {
            if (!ListenerUtil.mutListener.listen(7215)) {
                Timber.i("Applying background");
            }
            Drawable drawable = Drawable.createFromPath(imgFile.getAbsolutePath());
            if (!ListenerUtil.mutListener.listen(7216)) {
                view.setBackground(drawable);
            }
            return true;
        }
    }

    /**
     * Try to open the Collection for the first time, and do some error handling if it wasn't successful
     * @return whether or not we were successful
     */
    private boolean firstCollectionOpen() {
        if (AnkiDroidApp.webViewFailedToLoad()) {
            if (!ListenerUtil.mutListener.listen(7219)) {
                new MaterialDialog.Builder(this).title(R.string.ankidroid_init_failed_webview_title).content(getString(R.string.ankidroid_init_failed_webview, AnkiDroidApp.getWebViewErrorMessage())).positiveText(R.string.close).onPositive((d, w) -> exit()).cancelable(false).show();
            }
            return false;
        }
        if (Permissions.hasStorageAccessPermission(this)) {
            if (!ListenerUtil.mutListener.listen(7223)) {
                Timber.i("User has permissions to access collection");
            }
            // Show error dialog if collection could not be opened
            return CollectionHelper.getInstance().getColSafe(this) != null;
        } else if (mClosedWelcomeMessage) {
            if (!ListenerUtil.mutListener.listen(7222)) {
                // Even if the dialog is showing, we want to show it again.
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_STORAGE_PERMISSION);
            }
            return false;
        } else {
            if (!ListenerUtil.mutListener.listen(7220)) {
                Timber.i("Displaying initial permission request dialog");
            }
            if (!ListenerUtil.mutListener.listen(7221)) {
                // Request storage permission if we don't have it (e.g. on Android 6.0+)
                new MaterialDialog.Builder(this).title(R.string.collection_load_welcome_request_permissions_title).titleGravity(GravityEnum.CENTER).content(R.string.collection_load_welcome_request_permissions_details).positiveText(R.string.dialog_ok).onPositive((innerDialog, innerWhich) -> {
                    this.mClosedWelcomeMessage = true;
                    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_STORAGE_PERMISSION);
                }).cancelable(false).canceledOnTouchOutside(false).show();
            }
            return false;
        }
    }

    private void configureFloatingActionsMenu() {
        final FloatingActionButton addDeckButton = findViewById(R.id.add_deck_action);
        final FloatingActionButton addSharedButton = findViewById(R.id.add_shared_action);
        final FloatingActionButton addNoteButton = findViewById(R.id.add_note_action);
        if (!ListenerUtil.mutListener.listen(7224)) {
            addDeckButton.setOnClickListener(view -> {
                if (mActionsMenu == null) {
                    return;
                }
                mActionsMenu.collapse();
                mDialogEditText = new FixedEditText(DeckPicker.this);
                mDialogEditText.setSingleLine(true);
                // mDialogEditText.setFilters(new InputFilter[] { mDeckNameFilter });
                new MaterialDialog.Builder(DeckPicker.this).title(R.string.new_deck).positiveText(R.string.dialog_ok).customView(mDialogEditText, true).onPositive((dialog, which) -> {
                    String deckName = mDialogEditText.getText().toString();
                    if (Decks.isValidDeckName(deckName)) {
                        createNewDeck(deckName);
                    } else {
                        Timber.i("configureFloatingActionsMenu::addDeckButton::onPositiveListener - Not creating invalid deck name '%s'", deckName);
                        UIUtils.showThemedToast(this, getString(R.string.invalid_deck_name), false);
                    }
                }).negativeText(R.string.dialog_cancel).show();
            });
        }
        if (!ListenerUtil.mutListener.listen(7225)) {
            addSharedButton.setOnClickListener(view -> {
                Timber.i("Adding Shared Deck");
                mActionsMenu.collapse();
                addSharedDeck();
            });
        }
        if (!ListenerUtil.mutListener.listen(7226)) {
            addNoteButton.setOnClickListener(view -> {
                Timber.i("Adding Note");
                mActionsMenu.collapse();
                addNote();
            });
        }
    }

    private void createNewDeck(String deckName) {
        if (!ListenerUtil.mutListener.listen(7227)) {
            Timber.i("DeckPicker:: Creating new deck...");
        }
        if (!ListenerUtil.mutListener.listen(7228)) {
            getCol().getDecks().id(deckName, true);
        }
        if (!ListenerUtil.mutListener.listen(7229)) {
            updateDeckList();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(7230)) {
            // Null check to prevent crash when col inaccessible
            if (CollectionHelper.getInstance().getColSafe(this) == null) {
                return false;
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(7231)) {
            Timber.d("onCreateOptionsMenu()");
        }
        if (!ListenerUtil.mutListener.listen(7232)) {
            getMenuInflater().inflate(R.menu.deck_picker, menu);
        }
        boolean sdCardAvailable = AnkiDroidApp.isSdCardMounted();
        if (!ListenerUtil.mutListener.listen(7233)) {
            menu.findItem(R.id.action_sync).setEnabled(sdCardAvailable);
        }
        if (!ListenerUtil.mutListener.listen(7234)) {
            menu.findItem(R.id.action_new_filtered_deck).setEnabled(sdCardAvailable);
        }
        if (!ListenerUtil.mutListener.listen(7235)) {
            menu.findItem(R.id.action_check_database).setEnabled(sdCardAvailable);
        }
        if (!ListenerUtil.mutListener.listen(7236)) {
            menu.findItem(R.id.action_check_media).setEnabled(sdCardAvailable);
        }
        if (!ListenerUtil.mutListener.listen(7237)) {
            menu.findItem(R.id.action_empty_cards).setEnabled(sdCardAvailable);
        }
        // I haven't had an exception here, but it feels this may be flaky
        try {
            if (!ListenerUtil.mutListener.listen(7239)) {
                displaySyncBadge(menu);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7238)) {
                Timber.w(e, "Error Displaying Sync Badge");
            }
        }
        MenuItem toolbarSearchItem = menu.findItem(R.id.deck_picker_action_filter);
        if (!ListenerUtil.mutListener.listen(7240)) {
            mToolbarSearchView = (SearchView) toolbarSearchItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(7241)) {
            mToolbarSearchView.setQueryHint(getString(R.string.search_decks));
        }
        if (!ListenerUtil.mutListener.listen(7244)) {
            mToolbarSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!ListenerUtil.mutListener.listen(7242)) {
                        mToolbarSearchView.clearFocus();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Filterable adapter = (Filterable) mRecyclerView.getAdapter();
                    if (!ListenerUtil.mutListener.listen(7243)) {
                        adapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7256)) {
            if (colIsOpen()) {
                if (!ListenerUtil.mutListener.listen(7249)) {
                    // Show / hide undo
                    if ((ListenerUtil.mutListener.listen(7245) ? (mFragmented && !getCol().undoAvailable()) : (mFragmented || !getCol().undoAvailable()))) {
                        if (!ListenerUtil.mutListener.listen(7248)) {
                            menu.findItem(R.id.action_undo).setVisible(false);
                        }
                    } else {
                        Resources res = getResources();
                        if (!ListenerUtil.mutListener.listen(7246)) {
                            menu.findItem(R.id.action_undo).setVisible(true);
                        }
                        String undo = res.getString(R.string.studyoptions_congrats_undo, getCol().undoName(res));
                        if (!ListenerUtil.mutListener.listen(7247)) {
                            menu.findItem(R.id.action_undo).setTitle(undo);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7255)) {
                    // Remove the filter - not necessary and search has other implications for new users.
                    menu.findItem(R.id.deck_picker_action_filter).setVisible((ListenerUtil.mutListener.listen(7254) ? (getCol().getDecks().count() <= 10) : (ListenerUtil.mutListener.listen(7253) ? (getCol().getDecks().count() > 10) : (ListenerUtil.mutListener.listen(7252) ? (getCol().getDecks().count() < 10) : (ListenerUtil.mutListener.listen(7251) ? (getCol().getDecks().count() != 10) : (ListenerUtil.mutListener.listen(7250) ? (getCol().getDecks().count() == 10) : (getCol().getDecks().count() >= 10)))))));
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void displaySyncBadge(Menu menu) {
        MenuItem syncMenu = menu.findItem(R.id.action_sync);
        SyncStatus syncStatus = SyncStatus.getSyncStatus(this::getCol);
        if (!ListenerUtil.mutListener.listen(7267)) {
            switch(syncStatus) {
                case BADGE_DISABLED:
                case NO_CHANGES:
                case INCONCLUSIVE:
                    if (!ListenerUtil.mutListener.listen(7257)) {
                        BadgeDrawableBuilder.removeBadge(syncMenu);
                    }
                    if (!ListenerUtil.mutListener.listen(7258)) {
                        syncMenu.setTitle(R.string.button_sync);
                    }
                    break;
                case HAS_CHANGES:
                    if (!ListenerUtil.mutListener.listen(7259)) {
                        // Light orange icon
                        new BadgeDrawableBuilder(getResources()).withColor(ContextCompat.getColor(this, R.color.badge_warning)).replaceBadge(syncMenu);
                    }
                    if (!ListenerUtil.mutListener.listen(7260)) {
                        syncMenu.setTitle(R.string.button_sync);
                    }
                    break;
                case NO_ACCOUNT:
                case FULL_SYNC:
                    if (!ListenerUtil.mutListener.listen(7263)) {
                        if (syncStatus == SyncStatus.NO_ACCOUNT) {
                            if (!ListenerUtil.mutListener.listen(7262)) {
                                syncMenu.setTitle(R.string.sync_menu_title_no_account);
                            }
                        } else if (syncStatus == SyncStatus.FULL_SYNC) {
                            if (!ListenerUtil.mutListener.listen(7261)) {
                                syncMenu.setTitle(R.string.sync_menu_title_full_sync);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7264)) {
                        // Orange-red icon with exclamation mark
                        new BadgeDrawableBuilder(getResources()).withText('!').withColor(ContextCompat.getColor(this, R.color.badge_error)).replaceBadge(syncMenu);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(7265)) {
                        Timber.w("Unhandled sync status: %s", syncStatus);
                    }
                    if (!ListenerUtil.mutListener.listen(7266)) {
                        syncMenu.setTitle(R.string.sync_title);
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(7268)) {
            if (getDrawerToggle().onOptionsItemSelected(item)) {
                return true;
            }
        }
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(7294)) {
            if (itemId == R.id.action_undo) {
                if (!ListenerUtil.mutListener.listen(7292)) {
                    Timber.i("DeckPicker:: Undo button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7293)) {
                    undo();
                }
                return true;
            } else if (itemId == R.id.action_sync) {
                if (!ListenerUtil.mutListener.listen(7290)) {
                    Timber.i("DeckPicker:: Sync button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7291)) {
                    sync();
                }
                return true;
            } else if (itemId == R.id.action_import) {
                if (!ListenerUtil.mutListener.listen(7288)) {
                    Timber.i("DeckPicker:: Import button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7289)) {
                    showImportDialog(ImportDialog.DIALOG_IMPORT_HINT);
                }
                return true;
            } else if (itemId == R.id.action_new_filtered_deck) {
                if (!ListenerUtil.mutListener.listen(7281)) {
                    Timber.i("DeckPicker:: New filtered deck button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7282)) {
                    mDialogEditText = new FixedEditText(DeckPicker.this);
                }
                ArrayList<String> names = getCol().getDecks().allNames();
                int n = 1;
                String name = String.format(Locale.getDefault(), "%s %d", res.getString(R.string.filtered_deck_name), n);
                if (!ListenerUtil.mutListener.listen(7285)) {
                    {
                        long _loopCounter130 = 0;
                        while (names.contains(name)) {
                            ListenerUtil.loopListener.listen("_loopCounter130", ++_loopCounter130);
                            if (!ListenerUtil.mutListener.listen(7283)) {
                                n++;
                            }
                            if (!ListenerUtil.mutListener.listen(7284)) {
                                name = String.format(Locale.getDefault(), "%s %d", res.getString(R.string.filtered_deck_name), n);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7286)) {
                    mDialogEditText.setText(name);
                }
                if (!ListenerUtil.mutListener.listen(7287)) {
                    // mDialogEditText.setFilters(new InputFilter[] { mDeckNameFilter });
                    new MaterialDialog.Builder(DeckPicker.this).title(res.getString(R.string.new_deck)).customView(mDialogEditText, true).positiveText(R.string.create).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                        String filteredDeckName = mDialogEditText.getText().toString();
                        if (!Decks.isValidDeckName(filteredDeckName)) {
                            Timber.i("Not creating deck with invalid name '%s'", filteredDeckName);
                            UIUtils.showThemedToast(this, getString(R.string.invalid_deck_name), false);
                            return;
                        }
                        Timber.i("DeckPicker:: Creating filtered deck...");
                        getCol().getDecks().newDyn(filteredDeckName);
                        openStudyOptions(true);
                    }).show();
                }
                return true;
            } else if (itemId == R.id.action_check_database) {
                if (!ListenerUtil.mutListener.listen(7279)) {
                    Timber.i("DeckPicker:: Check database button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7280)) {
                    showDatabaseErrorDialog(DatabaseErrorDialog.DIALOG_CONFIRM_DATABASE_CHECK);
                }
                return true;
            } else if (itemId == R.id.action_check_media) {
                if (!ListenerUtil.mutListener.listen(7277)) {
                    Timber.i("DeckPicker:: Check media button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7278)) {
                    showMediaCheckDialog(MediaCheckDialog.DIALOG_CONFIRM_MEDIA_CHECK);
                }
                return true;
            } else if (itemId == R.id.action_empty_cards) {
                if (!ListenerUtil.mutListener.listen(7275)) {
                    Timber.i("DeckPicker:: Empty cards button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7276)) {
                    handleEmptyCards();
                }
                return true;
            } else if (itemId == R.id.action_model_browser_open) {
                if (!ListenerUtil.mutListener.listen(7273)) {
                    Timber.i("DeckPicker:: Model browser button pressed");
                }
                Intent noteTypeBrowser = new Intent(this, ModelBrowser.class);
                if (!ListenerUtil.mutListener.listen(7274)) {
                    startActivityForResultWithAnimation(noteTypeBrowser, 0, LEFT);
                }
                return true;
            } else if (itemId == R.id.action_restore_backup) {
                if (!ListenerUtil.mutListener.listen(7271)) {
                    Timber.i("DeckPicker:: Restore from backup button pressed");
                }
                if (!ListenerUtil.mutListener.listen(7272)) {
                    showDatabaseErrorDialog(DatabaseErrorDialog.DIALOG_CONFIRM_RESTORE_BACKUP);
                }
                return true;
            } else if (itemId == R.id.action_export) {
                if (!ListenerUtil.mutListener.listen(7269)) {
                    Timber.i("DeckPicker:: Export collection button pressed");
                }
                String msg = getResources().getString(R.string.confirm_apkg_export);
                if (!ListenerUtil.mutListener.listen(7270)) {
                    showDialogFragment(ExportDialog.newInstance(msg));
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(7295)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(7308)) {
            if ((ListenerUtil.mutListener.listen(7300) ? (resultCode >= RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(7299) ? (resultCode <= RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(7298) ? (resultCode > RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(7297) ? (resultCode < RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(7296) ? (resultCode != RESULT_MEDIA_EJECTED) : (resultCode == RESULT_MEDIA_EJECTED))))))) {
                if (!ListenerUtil.mutListener.listen(7307)) {
                    onSdCardNotMounted();
                }
                return;
            } else if ((ListenerUtil.mutListener.listen(7305) ? (resultCode >= RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(7304) ? (resultCode <= RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(7303) ? (resultCode > RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(7302) ? (resultCode < RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(7301) ? (resultCode != RESULT_DB_ERROR) : (resultCode == RESULT_DB_ERROR))))))) {
                if (!ListenerUtil.mutListener.listen(7306)) {
                    handleDbError();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7382)) {
            if ((ListenerUtil.mutListener.listen(7313) ? (requestCode >= SHOW_INFO_NEW_VERSION) : (ListenerUtil.mutListener.listen(7312) ? (requestCode <= SHOW_INFO_NEW_VERSION) : (ListenerUtil.mutListener.listen(7311) ? (requestCode > SHOW_INFO_NEW_VERSION) : (ListenerUtil.mutListener.listen(7310) ? (requestCode < SHOW_INFO_NEW_VERSION) : (ListenerUtil.mutListener.listen(7309) ? (requestCode != SHOW_INFO_NEW_VERSION) : (requestCode == SHOW_INFO_NEW_VERSION))))))) {
                if (!ListenerUtil.mutListener.listen(7381)) {
                    showStartupScreensAndDialogs(AnkiDroidApp.getSharedPrefs(getBaseContext()), 3);
                }
            } else if ((ListenerUtil.mutListener.listen(7319) ? ((ListenerUtil.mutListener.listen(7318) ? (requestCode >= LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7317) ? (requestCode <= LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7316) ? (requestCode > LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7315) ? (requestCode < LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7314) ? (requestCode != LOG_IN_FOR_SYNC) : (requestCode == LOG_IN_FOR_SYNC)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(7318) ? (requestCode >= LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7317) ? (requestCode <= LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7316) ? (requestCode > LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7315) ? (requestCode < LOG_IN_FOR_SYNC) : (ListenerUtil.mutListener.listen(7314) ? (requestCode != LOG_IN_FOR_SYNC) : (requestCode == LOG_IN_FOR_SYNC)))))) && resultCode == RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(7380)) {
                    mSyncOnResume = true;
                }
            } else if ((ListenerUtil.mutListener.listen(7330) ? ((ListenerUtil.mutListener.listen(7324) ? (requestCode >= REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7323) ? (requestCode <= REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7322) ? (requestCode > REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7321) ? (requestCode < REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7320) ? (requestCode != REQUEST_REVIEW) : (requestCode == REQUEST_REVIEW)))))) && (ListenerUtil.mutListener.listen(7329) ? (requestCode >= SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7328) ? (requestCode <= SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7327) ? (requestCode > SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7326) ? (requestCode < SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7325) ? (requestCode != SHOW_STUDYOPTIONS) : (requestCode == SHOW_STUDYOPTIONS))))))) : ((ListenerUtil.mutListener.listen(7324) ? (requestCode >= REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7323) ? (requestCode <= REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7322) ? (requestCode > REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7321) ? (requestCode < REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(7320) ? (requestCode != REQUEST_REVIEW) : (requestCode == REQUEST_REVIEW)))))) || (ListenerUtil.mutListener.listen(7329) ? (requestCode >= SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7328) ? (requestCode <= SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7327) ? (requestCode > SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7326) ? (requestCode < SHOW_STUDYOPTIONS) : (ListenerUtil.mutListener.listen(7325) ? (requestCode != SHOW_STUDYOPTIONS) : (requestCode == SHOW_STUDYOPTIONS))))))))) {
                if (!ListenerUtil.mutListener.listen(7379)) {
                    if ((ListenerUtil.mutListener.listen(7367) ? (resultCode >= Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(7366) ? (resultCode <= Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(7365) ? (resultCode > Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(7364) ? (resultCode < Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(7363) ? (resultCode != Reviewer.RESULT_NO_MORE_CARDS) : (resultCode == Reviewer.RESULT_NO_MORE_CARDS))))))) {
                        if (!ListenerUtil.mutListener.listen(7378)) {
                            // Show a message when reviewing has finished
                            if (getCol().getSched().count() == 0) {
                                if (!ListenerUtil.mutListener.listen(7377)) {
                                    UIUtils.showSimpleSnackbar(this, R.string.studyoptions_congrats_finished, false);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7376)) {
                                    UIUtils.showSimpleSnackbar(this, R.string.studyoptions_no_cards_due, false);
                                }
                            }
                        }
                    } else if ((ListenerUtil.mutListener.listen(7372) ? (resultCode >= Reviewer.RESULT_ABORT_AND_SYNC) : (ListenerUtil.mutListener.listen(7371) ? (resultCode <= Reviewer.RESULT_ABORT_AND_SYNC) : (ListenerUtil.mutListener.listen(7370) ? (resultCode > Reviewer.RESULT_ABORT_AND_SYNC) : (ListenerUtil.mutListener.listen(7369) ? (resultCode < Reviewer.RESULT_ABORT_AND_SYNC) : (ListenerUtil.mutListener.listen(7368) ? (resultCode != Reviewer.RESULT_ABORT_AND_SYNC) : (resultCode == Reviewer.RESULT_ABORT_AND_SYNC))))))) {
                        if (!ListenerUtil.mutListener.listen(7373)) {
                            Timber.i("Obtained Abort and Sync result");
                        }
                        if (!ListenerUtil.mutListener.listen(7374)) {
                            CollectionTask.waitForAllToFinish(4);
                        }
                        if (!ListenerUtil.mutListener.listen(7375)) {
                            sync();
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(7335) ? (requestCode >= REQUEST_BROWSE_CARDS) : (ListenerUtil.mutListener.listen(7334) ? (requestCode <= REQUEST_BROWSE_CARDS) : (ListenerUtil.mutListener.listen(7333) ? (requestCode > REQUEST_BROWSE_CARDS) : (ListenerUtil.mutListener.listen(7332) ? (requestCode < REQUEST_BROWSE_CARDS) : (ListenerUtil.mutListener.listen(7331) ? (requestCode != REQUEST_BROWSE_CARDS) : (requestCode == REQUEST_BROWSE_CARDS))))))) {
                if (!ListenerUtil.mutListener.listen(7362)) {
                    // Store the selected deck after opening browser
                    if ((ListenerUtil.mutListener.listen(7359) ? (intent != null || intent.getBooleanExtra("allDecksSelected", false)) : (intent != null && intent.getBooleanExtra("allDecksSelected", false)))) {
                        if (!ListenerUtil.mutListener.listen(7361)) {
                            AnkiDroidApp.getSharedPrefs(this).edit().putLong("browserDeckIdFromDeckPicker", Decks.NOT_FOUND_DECK_ID).apply();
                        }
                    } else {
                        long selectedDeck = getCol().getDecks().selected();
                        if (!ListenerUtil.mutListener.listen(7360)) {
                            AnkiDroidApp.getSharedPrefs(this).edit().putLong("browserDeckIdFromDeckPicker", selectedDeck).apply();
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(7340) ? (requestCode >= REQUEST_PATH_UPDATE) : (ListenerUtil.mutListener.listen(7339) ? (requestCode <= REQUEST_PATH_UPDATE) : (ListenerUtil.mutListener.listen(7338) ? (requestCode > REQUEST_PATH_UPDATE) : (ListenerUtil.mutListener.listen(7337) ? (requestCode < REQUEST_PATH_UPDATE) : (ListenerUtil.mutListener.listen(7336) ? (requestCode != REQUEST_PATH_UPDATE) : (requestCode == REQUEST_PATH_UPDATE))))))) {
                if (!ListenerUtil.mutListener.listen(7358)) {
                    // The collection path was inaccessible on startup so just close the activity and let user restart
                    finishWithoutAnimation();
                }
            } else if ((ListenerUtil.mutListener.listen(7346) ? (((ListenerUtil.mutListener.listen(7345) ? (requestCode >= PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7344) ? (requestCode <= PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7343) ? (requestCode > PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7342) ? (requestCode < PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7341) ? (requestCode != PICK_APKG_FILE) : (requestCode == PICK_APKG_FILE))))))) || (resultCode == RESULT_OK)) : (((ListenerUtil.mutListener.listen(7345) ? (requestCode >= PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7344) ? (requestCode <= PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7343) ? (requestCode > PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7342) ? (requestCode < PICK_APKG_FILE) : (ListenerUtil.mutListener.listen(7341) ? (requestCode != PICK_APKG_FILE) : (requestCode == PICK_APKG_FILE))))))) && (resultCode == RESULT_OK)))) {
                ImportUtils.ImportResult importResult = ImportUtils.handleFileImport(this, intent);
                if (!ListenerUtil.mutListener.listen(7357)) {
                    if (!importResult.isSuccess()) {
                        if (!ListenerUtil.mutListener.listen(7356)) {
                            ImportUtils.showImportUnsuccessfulDialog(this, importResult.getHumanReadableMessage(), false);
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(7352) ? (((ListenerUtil.mutListener.listen(7351) ? (requestCode >= PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7350) ? (requestCode <= PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7349) ? (requestCode > PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7348) ? (requestCode < PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7347) ? (requestCode != PICK_EXPORT_FILE) : (requestCode == PICK_EXPORT_FILE))))))) || (resultCode == RESULT_OK)) : (((ListenerUtil.mutListener.listen(7351) ? (requestCode >= PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7350) ? (requestCode <= PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7349) ? (requestCode > PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7348) ? (requestCode < PICK_EXPORT_FILE) : (ListenerUtil.mutListener.listen(7347) ? (requestCode != PICK_EXPORT_FILE) : (requestCode == PICK_EXPORT_FILE))))))) && (resultCode == RESULT_OK)))) {
                if (!ListenerUtil.mutListener.listen(7355)) {
                    if (exportToProvider(intent, true)) {
                        if (!ListenerUtil.mutListener.listen(7354)) {
                            UIUtils.showSimpleSnackbar(this, getString(R.string.export_save_apkg_successful), true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7353)) {
                            UIUtils.showSimpleSnackbar(this, getString(R.string.export_save_apkg_unsuccessful), false);
                        }
                    }
                }
            }
        }
    }

    private boolean exportToProvider(Intent intent, boolean deleteAfterExport) {
        if (!ListenerUtil.mutListener.listen(7385)) {
            if ((ListenerUtil.mutListener.listen(7383) ? ((intent == null) && (intent.getData() == null)) : ((intent == null) || (intent.getData() == null)))) {
                if (!ListenerUtil.mutListener.listen(7384)) {
                    Timber.e("exportToProvider() provided with insufficient intent data %s", intent);
                }
                return false;
            }
        }
        Uri uri = intent.getData();
        if (!ListenerUtil.mutListener.listen(7386)) {
            Timber.d("Exporting from file to ContentProvider URI: %s/%s", mExportFileName, uri.toString());
        }
        FileOutputStream fileOutputStream;
        ParcelFileDescriptor pfd;
        try {
            pfd = getContentResolver().openFileDescriptor(uri, "w");
            if (pfd != null) {
                fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());
                if (!ListenerUtil.mutListener.listen(7389)) {
                    CompatHelper.getCompat().copyFile(mExportFileName, fileOutputStream);
                }
                if (!ListenerUtil.mutListener.listen(7390)) {
                    fileOutputStream.close();
                }
                if (!ListenerUtil.mutListener.listen(7391)) {
                    pfd.close();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7388)) {
                    Timber.w("exportToProvider() failed - ContentProvider returned null file descriptor for %s", uri);
                }
                return false;
            }
            if (!ListenerUtil.mutListener.listen(7394)) {
                if ((ListenerUtil.mutListener.listen(7392) ? (deleteAfterExport || !new File(mExportFileName).delete()) : (deleteAfterExport && !new File(mExportFileName).delete()))) {
                    if (!ListenerUtil.mutListener.listen(7393)) {
                        Timber.w("Failed to delete temporary export file %s", mExportFileName);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7387)) {
                Timber.e(e, "Unable to export file to Uri: %s/%s", mExportFileName, uri.toString());
            }
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(7414)) {
            if ((ListenerUtil.mutListener.listen(7405) ? ((ListenerUtil.mutListener.listen(7399) ? (requestCode >= REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7398) ? (requestCode <= REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7397) ? (requestCode > REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7396) ? (requestCode < REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7395) ? (requestCode != REQUEST_STORAGE_PERMISSION) : (requestCode == REQUEST_STORAGE_PERMISSION)))))) || (ListenerUtil.mutListener.listen(7404) ? (permissions.length >= 1) : (ListenerUtil.mutListener.listen(7403) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(7402) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(7401) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(7400) ? (permissions.length != 1) : (permissions.length == 1))))))) : ((ListenerUtil.mutListener.listen(7399) ? (requestCode >= REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7398) ? (requestCode <= REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7397) ? (requestCode > REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7396) ? (requestCode < REQUEST_STORAGE_PERMISSION) : (ListenerUtil.mutListener.listen(7395) ? (requestCode != REQUEST_STORAGE_PERMISSION) : (requestCode == REQUEST_STORAGE_PERMISSION)))))) && (ListenerUtil.mutListener.listen(7404) ? (permissions.length >= 1) : (ListenerUtil.mutListener.listen(7403) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(7402) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(7401) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(7400) ? (permissions.length != 1) : (permissions.length == 1))))))))) {
                if (!ListenerUtil.mutListener.listen(7413)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (!ListenerUtil.mutListener.listen(7411)) {
                            invalidateOptionsMenu();
                        }
                        if (!ListenerUtil.mutListener.listen(7412)) {
                            showStartupScreensAndDialogs(AnkiDroidApp.getSharedPrefs(this), 0);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7406)) {
                            // User denied access to file storage  so show error toast and display "App Info"
                            Toast.makeText(this, R.string.startup_no_storage_permission, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(7407)) {
                            finishWithoutAnimation();
                        }
                        // Open the Android settings page for our app so that the user can grant the missing permission
                        Intent intent = new Intent();
                        if (!ListenerUtil.mutListener.listen(7408)) {
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        }
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        if (!ListenerUtil.mutListener.listen(7409)) {
                            intent.setData(uri);
                        }
                        if (!ListenerUtil.mutListener.listen(7410)) {
                            startActivityWithoutAnimation(intent);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(7415)) {
            Timber.d("onResume()");
        }
        if (!ListenerUtil.mutListener.listen(7416)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(7417)) {
            mActivityPaused = false;
        }
        if (!ListenerUtil.mutListener.listen(7426)) {
            if (mSyncOnResume) {
                if (!ListenerUtil.mutListener.listen(7423)) {
                    Timber.i("Performing Sync on Resume");
                }
                if (!ListenerUtil.mutListener.listen(7424)) {
                    sync();
                }
                if (!ListenerUtil.mutListener.listen(7425)) {
                    mSyncOnResume = false;
                }
            } else if (colIsOpen()) {
                if (!ListenerUtil.mutListener.listen(7418)) {
                    selectNavigationItem(R.id.nav_decks);
                }
                if (!ListenerUtil.mutListener.listen(7420)) {
                    if (mDueTree == null) {
                        if (!ListenerUtil.mutListener.listen(7419)) {
                            updateDeckList(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7421)) {
                    updateDeckList();
                }
                if (!ListenerUtil.mutListener.listen(7422)) {
                    setTitle(getResources().getString(R.string.app_name));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7427)) {
            /* Complete task and enqueue fetching nonessential data for
          startup. */
            TaskManager.launchCollectionTask(new CollectionTask.LoadCollectionComplete());
        }
        if (!ListenerUtil.mutListener.listen(7428)) {
            // Update sync status (if we've come back from a screen)
            supportInvalidateOptionsMenu();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7429)) {
            super.onSaveInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7430)) {
            savedInstanceState.putLong("mContextMenuDid", mContextMenuDid);
        }
        if (!ListenerUtil.mutListener.listen(7431)) {
            savedInstanceState.putBoolean("mClosedWelcomeMessage", mClosedWelcomeMessage);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7432)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7433)) {
            mContextMenuDid = savedInstanceState.getLong("mContextMenuDid");
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(7434)) {
            Timber.d("onPause()");
        }
        if (!ListenerUtil.mutListener.listen(7435)) {
            mActivityPaused = true;
        }
        if (!ListenerUtil.mutListener.listen(7436)) {
            // The deck count will be computed on resume. No need to compute it now
            TaskManager.cancelAllTasks(CollectionTask.LoadDeckCounts.class);
        }
        if (!ListenerUtil.mutListener.listen(7437)) {
            super.onPause();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(7438)) {
            Timber.d("onStop()");
        }
        if (!ListenerUtil.mutListener.listen(7439)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(7442)) {
            if (colIsOpen()) {
                if (!ListenerUtil.mutListener.listen(7440)) {
                    WidgetStatus.update(this);
                }
                if (!ListenerUtil.mutListener.listen(7441)) {
                    // Ignore the modification - a change in deck shouldn't trigger the icon for "pending changes".
                    UIUtils.saveCollectionInBackground(true);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(7443)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(7445)) {
            if (mUnmountReceiver != null) {
                if (!ListenerUtil.mutListener.listen(7444)) {
                    unregisterReceiver(mUnmountReceiver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7448)) {
            if ((ListenerUtil.mutListener.listen(7446) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(7447)) {
                    mProgressDialog.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7449)) {
            Timber.d("onDestroy()");
        }
    }

    private void automaticSync() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        // (currently 10 minutes)
        String hkey = preferences.getString("hkey", "");
        long lastSyncTime = preferences.getLong("lastSyncTime", 0);
        if (!ListenerUtil.mutListener.listen(7469)) {
            if ((ListenerUtil.mutListener.listen(7466) ? ((ListenerUtil.mutListener.listen(7456) ? ((ListenerUtil.mutListener.listen(7455) ? ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) || preferences.getBoolean("automaticSyncMode", false)) : ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) && preferences.getBoolean("automaticSyncMode", false))) || Connection.isOnline()) : ((ListenerUtil.mutListener.listen(7455) ? ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) || preferences.getBoolean("automaticSyncMode", false)) : ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) && preferences.getBoolean("automaticSyncMode", false))) && Connection.isOnline())) || (ListenerUtil.mutListener.listen(7465) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) >= AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7464) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) <= AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7463) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) < AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7462) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) != AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7461) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) == AUTOMATIC_SYNC_MIN_INTERVAL) : ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) > AUTOMATIC_SYNC_MIN_INTERVAL))))))) : ((ListenerUtil.mutListener.listen(7456) ? ((ListenerUtil.mutListener.listen(7455) ? ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) || preferences.getBoolean("automaticSyncMode", false)) : ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) && preferences.getBoolean("automaticSyncMode", false))) || Connection.isOnline()) : ((ListenerUtil.mutListener.listen(7455) ? ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) || preferences.getBoolean("automaticSyncMode", false)) : ((ListenerUtil.mutListener.listen(7454) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7453) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7452) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7451) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7450) ? (hkey.length() == 0) : (hkey.length() != 0)))))) && preferences.getBoolean("automaticSyncMode", false))) && Connection.isOnline())) && (ListenerUtil.mutListener.listen(7465) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) >= AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7464) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) <= AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7463) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) < AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7462) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) != AUTOMATIC_SYNC_MIN_INTERVAL) : (ListenerUtil.mutListener.listen(7461) ? ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) == AUTOMATIC_SYNC_MIN_INTERVAL) : ((ListenerUtil.mutListener.listen(7460) ? (getCol().getTime().intTimeMS() % lastSyncTime) : (ListenerUtil.mutListener.listen(7459) ? (getCol().getTime().intTimeMS() / lastSyncTime) : (ListenerUtil.mutListener.listen(7458) ? (getCol().getTime().intTimeMS() * lastSyncTime) : (ListenerUtil.mutListener.listen(7457) ? (getCol().getTime().intTimeMS() + lastSyncTime) : (getCol().getTime().intTimeMS() - lastSyncTime))))) > AUTOMATIC_SYNC_MIN_INTERVAL))))))))) {
                if (!ListenerUtil.mutListener.listen(7467)) {
                    Timber.i("Triggering Automatic Sync");
                }
                if (!ListenerUtil.mutListener.listen(7468)) {
                    sync();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(7477)) {
            if (isDrawerOpen()) {
                if (!ListenerUtil.mutListener.listen(7476)) {
                    super.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7470)) {
                    Timber.i("Back key pressed");
                }
                if (!ListenerUtil.mutListener.listen(7475)) {
                    if ((ListenerUtil.mutListener.listen(7471) ? (mActionsMenu != null || mActionsMenu.isExpanded()) : (mActionsMenu != null && mActionsMenu.isExpanded()))) {
                        if (!ListenerUtil.mutListener.listen(7474)) {
                            mActionsMenu.collapse();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7472)) {
                            automaticSync();
                        }
                        if (!ListenerUtil.mutListener.listen(7473)) {
                            finishWithAnimation();
                        }
                    }
                }
            }
        }
    }

    private void finishWithAnimation() {
        if (!ListenerUtil.mutListener.listen(7478)) {
            super.finishWithAnimation(DOWN);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(7481)) {
            if ((ListenerUtil.mutListener.listen(7479) ? (mToolbarSearchView != null || mToolbarSearchView.hasFocus()) : (mToolbarSearchView != null && mToolbarSearchView.hasFocus()))) {
                if (!ListenerUtil.mutListener.listen(7480)) {
                    Timber.d("Skipping keypress: search action bar is focused");
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(7490)) {
            switch(keyCode) {
                case KeyEvent.KEYCODE_A:
                    if (!ListenerUtil.mutListener.listen(7482)) {
                        Timber.i("Adding Note from keypress");
                    }
                    if (!ListenerUtil.mutListener.listen(7483)) {
                        addNote();
                    }
                    break;
                case KeyEvent.KEYCODE_B:
                    if (!ListenerUtil.mutListener.listen(7484)) {
                        Timber.i("Open Browser from keypress");
                    }
                    if (!ListenerUtil.mutListener.listen(7485)) {
                        openCardBrowser();
                    }
                    break;
                case KeyEvent.KEYCODE_Y:
                    if (!ListenerUtil.mutListener.listen(7486)) {
                        Timber.i("Sync from keypress");
                    }
                    if (!ListenerUtil.mutListener.listen(7487)) {
                        sync();
                    }
                    break;
                case KeyEvent.KEYCODE_SLASH:
                case KeyEvent.KEYCODE_S:
                    if (!ListenerUtil.mutListener.listen(7488)) {
                        Timber.i("Study from keypress");
                    }
                    if (!ListenerUtil.mutListener.listen(7489)) {
                        handleDeckSelection(getCol().getDecks().selected(), DeckSelectionType.SKIP_STUDY_OPTIONS);
                    }
                    break;
                default:
                    break;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private void restoreWelcomeMessage(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7491)) {
            if (savedInstanceState == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7492)) {
            mClosedWelcomeMessage = savedInstanceState.getBoolean("mClosedWelcomeMessage");
        }
    }

    /**
     * Perform the following tasks:
     * Automatic backup
     * loadStudyOptionsFragment() if tablet
     * Automatic sync
     */
    private void onFinishedStartup() {
        if (!ListenerUtil.mutListener.listen(7493)) {
            // create backup in background if needed
            BackupManager.performBackupInBackground(getCol().getPath(), getCol().getTime());
        }
        if (!ListenerUtil.mutListener.listen(7501)) {
            // Force a full sync if flag was set in upgrade path, asking the user to confirm if necessary
            if (mRecommendFullSync) {
                if (!ListenerUtil.mutListener.listen(7494)) {
                    mRecommendFullSync = false;
                }
                try {
                    if (!ListenerUtil.mutListener.listen(7500)) {
                        getCol().modSchema();
                    }
                } catch (ConfirmModSchemaException e) {
                    if (!ListenerUtil.mutListener.listen(7495)) {
                        Timber.w("Forcing full sync");
                    }
                    // We have to show the dialog via the DialogHandler since this method is called via an async task
                    Resources res = getResources();
                    Message handlerMessage = Message.obtain();
                    if (!ListenerUtil.mutListener.listen(7496)) {
                        handlerMessage.what = DialogHandler.MSG_SHOW_FORCE_FULL_SYNC_DIALOG;
                    }
                    Bundle handlerMessageData = new Bundle();
                    if (!ListenerUtil.mutListener.listen(7497)) {
                        handlerMessageData.putString("message", res.getString(R.string.full_sync_confirmation_upgrade) + "\n\n" + res.getString(R.string.full_sync_confirmation));
                    }
                    if (!ListenerUtil.mutListener.listen(7498)) {
                        handlerMessage.setData(handlerMessageData);
                    }
                    if (!ListenerUtil.mutListener.listen(7499)) {
                        getDialogHandler().sendMessage(handlerMessage);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7503)) {
            // Open StudyOptionsFragment if in fragmented mode
            if (mFragmented) {
                if (!ListenerUtil.mutListener.listen(7502)) {
                    loadStudyOptionsFragment(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7504)) {
            automaticSync();
        }
    }

    private void showCollectionErrorDialog() {
        if (!ListenerUtil.mutListener.listen(7505)) {
            getDialogHandler().sendEmptyMessage(DialogHandler.MSG_SHOW_COLLECTION_LOADING_ERROR_DIALOG);
        }
    }

    public void addNote() {
        Intent intent = new Intent(DeckPicker.this, NoteEditor.class);
        if (!ListenerUtil.mutListener.listen(7506)) {
            intent.putExtra(NoteEditor.EXTRA_CALLER, NoteEditor.CALLER_DECKPICKER);
        }
        if (!ListenerUtil.mutListener.listen(7507)) {
            startActivityForResultWithAnimation(intent, ADD_NOTE, LEFT);
        }
    }

    private void showStartupScreensAndDialogs(SharedPreferences preferences, int skip) {
        if (!ListenerUtil.mutListener.listen(7629)) {
            if (!BackupManager.enoughDiscSpace(CollectionHelper.getCurrentAnkiDroidDirectory(this))) {
                if (!ListenerUtil.mutListener.listen(7627)) {
                    Timber.i("Not enough space to do backup");
                }
                if (!ListenerUtil.mutListener.listen(7628)) {
                    showDialogFragment(DeckPickerNoSpaceLeftDialog.newInstance());
                }
            } else if (preferences.getBoolean("noSpaceLeft", false)) {
                if (!ListenerUtil.mutListener.listen(7624)) {
                    Timber.i("No space left");
                }
                if (!ListenerUtil.mutListener.listen(7625)) {
                    showDialogFragment(DeckPickerBackupNoSpaceLeftDialog.newInstance());
                }
                if (!ListenerUtil.mutListener.listen(7626)) {
                    preferences.edit().remove("noSpaceLeft").apply();
                }
            } else if ("".equals(preferences.getString("lastVersion", ""))) {
                if (!ListenerUtil.mutListener.listen(7621)) {
                    Timber.i("Fresh install");
                }
                if (!ListenerUtil.mutListener.listen(7622)) {
                    preferences.edit().putString("lastVersion", VersionUtils.getPkgVersionName()).apply();
                }
                if (!ListenerUtil.mutListener.listen(7623)) {
                    onFinishedStartup();
                }
            } else if ((ListenerUtil.mutListener.listen(7513) ? ((ListenerUtil.mutListener.listen(7512) ? (skip >= 2) : (ListenerUtil.mutListener.listen(7511) ? (skip <= 2) : (ListenerUtil.mutListener.listen(7510) ? (skip > 2) : (ListenerUtil.mutListener.listen(7509) ? (skip != 2) : (ListenerUtil.mutListener.listen(7508) ? (skip == 2) : (skip < 2)))))) || !preferences.getString("lastVersion", "").equals(VersionUtils.getPkgVersionName())) : ((ListenerUtil.mutListener.listen(7512) ? (skip >= 2) : (ListenerUtil.mutListener.listen(7511) ? (skip <= 2) : (ListenerUtil.mutListener.listen(7510) ? (skip > 2) : (ListenerUtil.mutListener.listen(7509) ? (skip != 2) : (ListenerUtil.mutListener.listen(7508) ? (skip == 2) : (skip < 2)))))) && !preferences.getString("lastVersion", "").equals(VersionUtils.getPkgVersionName())))) {
                if (!ListenerUtil.mutListener.listen(7516)) {
                    Timber.i("AnkiDroid is being updated and a collection already exists.");
                }
                if (!ListenerUtil.mutListener.listen(7518)) {
                    // The user might appreciate us now, see if they will help us get better?
                    if (!preferences.contains(UsageAnalytics.ANALYTICS_OPTIN_KEY)) {
                        if (!ListenerUtil.mutListener.listen(7517)) {
                            showDialogFragment(DeckPickerAnalyticsOptInDialog.newInstance());
                        }
                    }
                }
                // installation of AnkiDroid and we don't run the check.
                long current = VersionUtils.getPkgVersionCode();
                if (!ListenerUtil.mutListener.listen(7519)) {
                    Timber.i("Current AnkiDroid version: %s", current);
                }
                long previous;
                if (preferences.contains(UPGRADE_VERSION_KEY)) {
                    // Upgrading currently installed app
                    previous = getPreviousVersion(preferences, current);
                } else {
                    // Fresh install
                    previous = current;
                }
                if (!ListenerUtil.mutListener.listen(7520)) {
                    preferences.edit().putLong(UPGRADE_VERSION_KEY, current).apply();
                }
                if (!ListenerUtil.mutListener.listen(7529)) {
                    // It is rebuilt on the next sync or media check
                    if ((ListenerUtil.mutListener.listen(7525) ? (previous >= 20300200) : (ListenerUtil.mutListener.listen(7524) ? (previous <= 20300200) : (ListenerUtil.mutListener.listen(7523) ? (previous > 20300200) : (ListenerUtil.mutListener.listen(7522) ? (previous != 20300200) : (ListenerUtil.mutListener.listen(7521) ? (previous == 20300200) : (previous < 20300200))))))) {
                        if (!ListenerUtil.mutListener.listen(7526)) {
                            Timber.i("Deleting media database");
                        }
                        File mediaDb = new File(CollectionHelper.getCurrentAnkiDroidDirectory(this), "collection.media.ad.db2");
                        if (!ListenerUtil.mutListener.listen(7528)) {
                            if (mediaDb.exists()) {
                                if (!ListenerUtil.mutListener.listen(7527)) {
                                    mediaDb.delete();
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7537)) {
                    // Recommend the user to do a full-sync if they're upgrading from before 2.3.1beta8
                    if ((ListenerUtil.mutListener.listen(7534) ? (previous >= 20301208) : (ListenerUtil.mutListener.listen(7533) ? (previous <= 20301208) : (ListenerUtil.mutListener.listen(7532) ? (previous > 20301208) : (ListenerUtil.mutListener.listen(7531) ? (previous != 20301208) : (ListenerUtil.mutListener.listen(7530) ? (previous == 20301208) : (previous < 20301208))))))) {
                        if (!ListenerUtil.mutListener.listen(7535)) {
                            Timber.i("Recommend the user to do a full-sync");
                        }
                        if (!ListenerUtil.mutListener.listen(7536)) {
                            mRecommendFullSync = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7550)) {
                    // Fix "font-family" definition in templates created by AnkiDroid before 2.6alhpa23
                    if ((ListenerUtil.mutListener.listen(7542) ? (previous >= 20600123) : (ListenerUtil.mutListener.listen(7541) ? (previous <= 20600123) : (ListenerUtil.mutListener.listen(7540) ? (previous > 20600123) : (ListenerUtil.mutListener.listen(7539) ? (previous != 20600123) : (ListenerUtil.mutListener.listen(7538) ? (previous == 20600123) : (previous < 20600123))))))) {
                        if (!ListenerUtil.mutListener.listen(7543)) {
                            Timber.i("Fixing font-family definition in templates");
                        }
                        try {
                            Models models = getCol().getModels();
                            if (!ListenerUtil.mutListener.listen(7548)) {
                                {
                                    long _loopCounter131 = 0;
                                    for (Model m : models.all()) {
                                        ListenerUtil.loopListener.listen("_loopCounter131", ++_loopCounter131);
                                        String css = m.getString("css");
                                        if (!ListenerUtil.mutListener.listen(7547)) {
                                            if (css.contains("font-familiy")) {
                                                if (!ListenerUtil.mutListener.listen(7545)) {
                                                    m.put("css", css.replace("font-familiy", "font-family"));
                                                }
                                                if (!ListenerUtil.mutListener.listen(7546)) {
                                                    models.save(m);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7549)) {
                                models.flush();
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(7544)) {
                                Timber.e(e, "Failed to upgrade css definitions.");
                            }
                        }
                    }
                }
                // Check if preference upgrade or database check required, otherwise go to new feature screen
                int upgradePrefsVersion = AnkiDroidApp.CHECK_PREFERENCES_AT_VERSION;
                int upgradeDbVersion = AnkiDroidApp.CHECK_DB_AT_VERSION;
                if (!ListenerUtil.mutListener.listen(7559)) {
                    // Specifying a checkpoint in the future is not supported, please don't do it!
                    if ((ListenerUtil.mutListener.listen(7555) ? (current >= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7554) ? (current <= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7553) ? (current > upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7552) ? (current != upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7551) ? (current == upgradePrefsVersion) : (current < upgradePrefsVersion))))))) {
                        if (!ListenerUtil.mutListener.listen(7556)) {
                            Timber.e("Checkpoint in future produced.");
                        }
                        if (!ListenerUtil.mutListener.listen(7557)) {
                            UIUtils.showSimpleSnackbar(this, "Invalid value for CHECK_PREFERENCES_AT_VERSION", false);
                        }
                        if (!ListenerUtil.mutListener.listen(7558)) {
                            onFinishedStartup();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(7568)) {
                    if ((ListenerUtil.mutListener.listen(7564) ? (current >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7563) ? (current <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7562) ? (current > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7561) ? (current != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7560) ? (current == upgradeDbVersion) : (current < upgradeDbVersion))))))) {
                        if (!ListenerUtil.mutListener.listen(7565)) {
                            Timber.e("Invalid value for CHECK_DB_AT_VERSION");
                        }
                        if (!ListenerUtil.mutListener.listen(7566)) {
                            UIUtils.showSimpleSnackbar(this, "Invalid value for CHECK_DB_AT_VERSION", false);
                        }
                        if (!ListenerUtil.mutListener.listen(7567)) {
                            onFinishedStartup();
                        }
                        return;
                    }
                }
                // TODO: remove this variable if we really want to do the full db check on every user
                boolean skipDbCheck = false;
                if (!ListenerUtil.mutListener.listen(7620)) {
                    // noinspection ConstantConditions
                    if ((ListenerUtil.mutListener.listen(7580) ? (((ListenerUtil.mutListener.listen(7574) ? (!skipDbCheck || (ListenerUtil.mutListener.listen(7573) ? (previous >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7572) ? (previous <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7571) ? (previous > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7570) ? (previous != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7569) ? (previous == upgradeDbVersion) : (previous < upgradeDbVersion))))))) : (!skipDbCheck && (ListenerUtil.mutListener.listen(7573) ? (previous >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7572) ? (previous <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7571) ? (previous > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7570) ? (previous != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7569) ? (previous == upgradeDbVersion) : (previous < upgradeDbVersion))))))))) && (ListenerUtil.mutListener.listen(7579) ? (previous >= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7578) ? (previous <= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7577) ? (previous > upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7576) ? (previous != upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7575) ? (previous == upgradePrefsVersion) : (previous < upgradePrefsVersion))))))) : (((ListenerUtil.mutListener.listen(7574) ? (!skipDbCheck || (ListenerUtil.mutListener.listen(7573) ? (previous >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7572) ? (previous <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7571) ? (previous > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7570) ? (previous != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7569) ? (previous == upgradeDbVersion) : (previous < upgradeDbVersion))))))) : (!skipDbCheck && (ListenerUtil.mutListener.listen(7573) ? (previous >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7572) ? (previous <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7571) ? (previous > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7570) ? (previous != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7569) ? (previous == upgradeDbVersion) : (previous < upgradeDbVersion))))))))) || (ListenerUtil.mutListener.listen(7579) ? (previous >= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7578) ? (previous <= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7577) ? (previous > upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7576) ? (previous != upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7575) ? (previous == upgradePrefsVersion) : (previous < upgradePrefsVersion))))))))) {
                        if (!ListenerUtil.mutListener.listen(7603)) {
                            if ((ListenerUtil.mutListener.listen(7600) ? (previous >= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7599) ? (previous <= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7598) ? (previous > upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7597) ? (previous != upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7596) ? (previous == upgradePrefsVersion) : (previous < upgradePrefsVersion))))))) {
                                if (!ListenerUtil.mutListener.listen(7601)) {
                                    Timber.i("showStartupScreensAndDialogs() running upgradePreferences()");
                                }
                                if (!ListenerUtil.mutListener.listen(7602)) {
                                    upgradePreferences(previous);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7619)) {
                            // noinspection ConstantConditions
                            if ((ListenerUtil.mutListener.listen(7609) ? (!skipDbCheck || (ListenerUtil.mutListener.listen(7608) ? (previous >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7607) ? (previous <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7606) ? (previous > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7605) ? (previous != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7604) ? (previous == upgradeDbVersion) : (previous < upgradeDbVersion))))))) : (!skipDbCheck && (ListenerUtil.mutListener.listen(7608) ? (previous >= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7607) ? (previous <= upgradeDbVersion) : (ListenerUtil.mutListener.listen(7606) ? (previous > upgradeDbVersion) : (ListenerUtil.mutListener.listen(7605) ? (previous != upgradeDbVersion) : (ListenerUtil.mutListener.listen(7604) ? (previous == upgradeDbVersion) : (previous < upgradeDbVersion))))))))) {
                                if (!ListenerUtil.mutListener.listen(7617)) {
                                    Timber.i("showStartupScreensAndDialogs() running integrityCheck()");
                                }
                                if (!ListenerUtil.mutListener.listen(7618)) {
                                    // and show a warning before the user knows what is happening.
                                    new MaterialDialog.Builder(this).title(R.string.integrity_check_startup_title).content(R.string.integrity_check_startup_content).positiveText(R.string.check_db).negativeText(R.string.close).onPositive((materialDialog, dialogAction) -> integrityCheck()).onNeutral((materialDialog, dialogAction) -> restartActivity()).onNegative((materialDialog, dialogAction) -> restartActivity()).canceledOnTouchOutside(false).cancelable(false).build().show();
                                }
                            } else if ((ListenerUtil.mutListener.listen(7614) ? (previous >= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7613) ? (previous <= upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7612) ? (previous > upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7611) ? (previous != upgradePrefsVersion) : (ListenerUtil.mutListener.listen(7610) ? (previous == upgradePrefsVersion) : (previous < upgradePrefsVersion))))))) {
                                if (!ListenerUtil.mutListener.listen(7615)) {
                                    Timber.i("Updated preferences with no integrity check - restarting activity");
                                }
                                if (!ListenerUtil.mutListener.listen(7616)) {
                                    // proceed
                                    restartActivity();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7595)) {
                            // There the "lastVersion" is set, so that this code is not reached again
                            if (VersionUtils.isReleaseVersion()) {
                                if (!ListenerUtil.mutListener.listen(7585)) {
                                    Timber.i("Displaying new features");
                                }
                                Intent infoIntent = new Intent(this, Info.class);
                                if (!ListenerUtil.mutListener.listen(7586)) {
                                    infoIntent.putExtra(Info.TYPE_EXTRA, Info.TYPE_NEW_VERSION);
                                }
                                if (!ListenerUtil.mutListener.listen(7594)) {
                                    if ((ListenerUtil.mutListener.listen(7591) ? (skip >= 0) : (ListenerUtil.mutListener.listen(7590) ? (skip <= 0) : (ListenerUtil.mutListener.listen(7589) ? (skip > 0) : (ListenerUtil.mutListener.listen(7588) ? (skip < 0) : (ListenerUtil.mutListener.listen(7587) ? (skip == 0) : (skip != 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(7593)) {
                                            startActivityForResultWithAnimation(infoIntent, SHOW_INFO_NEW_VERSION, LEFT);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7592)) {
                                            startActivityForResultWithoutAnimation(infoIntent, SHOW_INFO_NEW_VERSION);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7581)) {
                                    Timber.i("Dev Build - not showing 'new features'");
                                }
                                if (!ListenerUtil.mutListener.listen(7582)) {
                                    // Don't show new features dialog for development builds
                                    preferences.edit().putString("lastVersion", VersionUtils.getPkgVersionName()).apply();
                                }
                                String ver = getResources().getString(R.string.updated_version, VersionUtils.getPkgVersionName());
                                if (!ListenerUtil.mutListener.listen(7583)) {
                                    UIUtils.showSnackbar(this, ver, true, -1, null, findViewById(R.id.root_layout), null);
                                }
                                if (!ListenerUtil.mutListener.listen(7584)) {
                                    showStartupScreensAndDialogs(preferences, 2);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7514)) {
                    // This is the main call when there is nothing special required
                    Timber.i("No startup screens required");
                }
                if (!ListenerUtil.mutListener.listen(7515)) {
                    onFinishedStartup();
                }
            }
        }
    }

    protected long getPreviousVersion(SharedPreferences preferences, long current) {
        long previous;
        try {
            previous = preferences.getLong(UPGRADE_VERSION_KEY, current);
        } catch (ClassCastException e) {
            try {
                // set 20900203 to default value, as it's the latest version that stores integer in shared prefs
                previous = preferences.getInt(UPGRADE_VERSION_KEY, 20900203);
            } catch (ClassCastException cce) {
                // Previous versions stored this as a string.
                String s = preferences.getString(UPGRADE_VERSION_KEY, "");
                // We manually set the version here, but anything older will force a DB check.
                if ("2.0.2".equals(s)) {
                    previous = 40;
                } else {
                    previous = 0;
                }
            }
            if (!ListenerUtil.mutListener.listen(7630)) {
                Timber.d("Updating shared preferences stored key %s type to long", UPGRADE_VERSION_KEY);
            }
            if (!ListenerUtil.mutListener.listen(7631)) {
                // Expected Editor.putLong to be called later to update the value in shared prefs
                preferences.edit().remove(UPGRADE_VERSION_KEY).apply();
            }
        }
        if (!ListenerUtil.mutListener.listen(7632)) {
            Timber.i("Previous AnkiDroid version: %s", previous);
        }
        return previous;
    }

    private void upgradePreferences(long previousVersionCode) {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        if (!ListenerUtil.mutListener.listen(7640)) {
            // clear all prefs if super old version to prevent any errors
            if ((ListenerUtil.mutListener.listen(7637) ? (previousVersionCode >= 20300130) : (ListenerUtil.mutListener.listen(7636) ? (previousVersionCode <= 20300130) : (ListenerUtil.mutListener.listen(7635) ? (previousVersionCode > 20300130) : (ListenerUtil.mutListener.listen(7634) ? (previousVersionCode != 20300130) : (ListenerUtil.mutListener.listen(7633) ? (previousVersionCode == 20300130) : (previousVersionCode < 20300130))))))) {
                if (!ListenerUtil.mutListener.listen(7638)) {
                    Timber.i("Old version of Anki - Clearing preferences");
                }
                if (!ListenerUtil.mutListener.listen(7639)) {
                    preferences.edit().clear().apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7653)) {
            // when upgrading from before 2.5alpha35
            if ((ListenerUtil.mutListener.listen(7645) ? (previousVersionCode >= 20500135) : (ListenerUtil.mutListener.listen(7644) ? (previousVersionCode <= 20500135) : (ListenerUtil.mutListener.listen(7643) ? (previousVersionCode > 20500135) : (ListenerUtil.mutListener.listen(7642) ? (previousVersionCode != 20500135) : (ListenerUtil.mutListener.listen(7641) ? (previousVersionCode == 20500135) : (previousVersionCode < 20500135))))))) {
                if (!ListenerUtil.mutListener.listen(7646)) {
                    Timber.i("Old version of Anki - Fixing Zoom");
                }
                // Card zooming behaviour was changed the preferences renamed
                int oldCardZoom = preferences.getInt("relativeDisplayFontSize", 100);
                int oldImageZoom = preferences.getInt("relativeImageSize", 100);
                if (!ListenerUtil.mutListener.listen(7647)) {
                    preferences.edit().putInt("cardZoom", oldCardZoom).apply();
                }
                if (!ListenerUtil.mutListener.listen(7648)) {
                    preferences.edit().putInt("imageZoom", oldImageZoom).apply();
                }
                if (!ListenerUtil.mutListener.listen(7650)) {
                    if (!preferences.getBoolean("useBackup", true)) {
                        if (!ListenerUtil.mutListener.listen(7649)) {
                            preferences.edit().putInt("backupMax", 0).apply();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7651)) {
                    preferences.edit().remove("useBackup").apply();
                }
                if (!ListenerUtil.mutListener.listen(7652)) {
                    preferences.edit().remove("intentAdditionInstantAdd").apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7658)) {
            if (preferences.contains("fullscreenReview")) {
                if (!ListenerUtil.mutListener.listen(7654)) {
                    Timber.i("Old version of Anki - Fixing Fullscreen");
                }
                // clear fullscreen flag as we use a integer
                try {
                    boolean old = preferences.getBoolean("fullscreenReview", false);
                    if (!ListenerUtil.mutListener.listen(7656)) {
                        preferences.edit().putString("fullscreenMode", old ? "1" : "0").apply();
                    }
                } catch (ClassCastException e) {
                    if (!ListenerUtil.mutListener.listen(7655)) {
                        // TODO:  can remove this catch as it was only here to fix an error in the betas
                        preferences.edit().remove("fullscreenMode").apply();
                    }
                }
                if (!ListenerUtil.mutListener.listen(7657)) {
                    preferences.edit().remove("fullscreenReview").apply();
                }
            }
        }
    }

    private UndoTaskListener undoTaskListener(boolean isReview) {
        return new UndoTaskListener(isReview, this);
    }

    private static class UndoTaskListener extends TaskListenerWithContext<DeckPicker, Card, BooleanGetter> {

        private final boolean isReview;

        public UndoTaskListener(boolean isReview, DeckPicker deckPicker) {
            super(deckPicker);
            this.isReview = isReview;
        }

        @Override
        public void actualOnCancelled(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7659)) {
                deckPicker.hideProgressBar();
            }
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7660)) {
                deckPicker.showProgressBar();
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, BooleanGetter voi) {
            if (!ListenerUtil.mutListener.listen(7661)) {
                deckPicker.hideProgressBar();
            }
            if (!ListenerUtil.mutListener.listen(7662)) {
                Timber.i("Undo completed");
            }
            if (!ListenerUtil.mutListener.listen(7665)) {
                if (isReview) {
                    if (!ListenerUtil.mutListener.listen(7663)) {
                        Timber.i("Review undone - opening reviewer.");
                    }
                    if (!ListenerUtil.mutListener.listen(7664)) {
                        deckPicker.openReviewer();
                    }
                }
            }
        }
    }

    private void undo() {
        if (!ListenerUtil.mutListener.listen(7666)) {
            Timber.i("undo()");
        }
        String undoReviewString = getResources().getString(R.string.undo_action_review);
        final boolean isReview = undoReviewString.equals(getCol().undoName(getResources()));
        if (!ListenerUtil.mutListener.listen(7667)) {
            TaskManager.launchCollectionTask(new CollectionTask.Undo(), undoTaskListener(isReview));
        }
    }

    // Show dialogs to deal with database loading issues etc
    public void showDatabaseErrorDialog(int id) {
        AsyncDialogFragment newFragment = DatabaseErrorDialog.newInstance(id);
        if (!ListenerUtil.mutListener.listen(7668)) {
            showAsyncDialogFragment(newFragment);
        }
    }

    @Override
    public void showMediaCheckDialog(int id) {
        if (!ListenerUtil.mutListener.listen(7669)) {
            showAsyncDialogFragment(MediaCheckDialog.newInstance(id));
        }
    }

    @Override
    public void showMediaCheckDialog(int id, List<List<String>> checkList) {
        if (!ListenerUtil.mutListener.listen(7670)) {
            showAsyncDialogFragment(MediaCheckDialog.newInstance(id, checkList));
        }
    }

    /**
     * Show a specific sync error dialog
     * @param id id of dialog to show
     */
    @Override
    public void showSyncErrorDialog(int id) {
        if (!ListenerUtil.mutListener.listen(7671)) {
            showSyncErrorDialog(id, "");
        }
    }

    /**
     * Show a specific sync error dialog
     * @param id id of dialog to show
     * @param message text to show
     */
    @Override
    public void showSyncErrorDialog(int id, String message) {
        AsyncDialogFragment newFragment = SyncErrorDialog.newInstance(id, message);
        if (!ListenerUtil.mutListener.listen(7672)) {
            showAsyncDialogFragment(newFragment, NotificationChannels.Channel.SYNC);
        }
    }

    /**
     *  Show simple error dialog with just the message and OK button. Reload the activity when dialog closed.
     */
    private void showSyncErrorMessage(@Nullable String message) {
        String title = getResources().getString(R.string.sync_error);
        if (!ListenerUtil.mutListener.listen(7673)) {
            showSimpleMessageDialog(title, message, true);
        }
    }

    /**
     *  Show a simple snackbar message or notification if the activity is not in foreground
     * @param messageResource String resource for message
     */
    private void showSyncLogMessage(@StringRes int messageResource, String syncMessage) {
        if (!ListenerUtil.mutListener.listen(7687)) {
            if (mActivityPaused) {
                Resources res = AnkiDroidApp.getAppResources();
                if (!ListenerUtil.mutListener.listen(7686)) {
                    showSimpleNotification(res.getString(R.string.app_name), res.getString(messageResource), NotificationChannels.Channel.SYNC);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7685)) {
                    if ((ListenerUtil.mutListener.listen(7679) ? (syncMessage == null && (ListenerUtil.mutListener.listen(7678) ? (syncMessage.length() >= 0) : (ListenerUtil.mutListener.listen(7677) ? (syncMessage.length() <= 0) : (ListenerUtil.mutListener.listen(7676) ? (syncMessage.length() > 0) : (ListenerUtil.mutListener.listen(7675) ? (syncMessage.length() < 0) : (ListenerUtil.mutListener.listen(7674) ? (syncMessage.length() != 0) : (syncMessage.length() == 0))))))) : (syncMessage == null || (ListenerUtil.mutListener.listen(7678) ? (syncMessage.length() >= 0) : (ListenerUtil.mutListener.listen(7677) ? (syncMessage.length() <= 0) : (ListenerUtil.mutListener.listen(7676) ? (syncMessage.length() > 0) : (ListenerUtil.mutListener.listen(7675) ? (syncMessage.length() < 0) : (ListenerUtil.mutListener.listen(7674) ? (syncMessage.length() != 0) : (syncMessage.length() == 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(7684)) {
                            if ((ListenerUtil.mutListener.listen(7681) ? (messageResource == R.string.youre_offline || !Connection.getAllowSyncOnNoConnection()) : (messageResource == R.string.youre_offline && !Connection.getAllowSyncOnNoConnection()))) {
                                // #6396 - Add a temporary "Try Anyway" button until we sort out `isOnline`
                                View root = this.findViewById(R.id.root_layout);
                                if (!ListenerUtil.mutListener.listen(7683)) {
                                    UIUtils.showSnackbar(this, messageResource, false, R.string.sync_even_if_offline, (v) -> {
                                        Connection.setAllowSyncOnNoConnection(true);
                                        sync();
                                    }, null);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7682)) {
                                    UIUtils.showSimpleSnackbar(this, messageResource, false);
                                }
                            }
                        }
                    } else {
                        Resources res = AnkiDroidApp.getAppResources();
                        if (!ListenerUtil.mutListener.listen(7680)) {
                            showSimpleMessageDialog(res.getString(messageResource), syncMessage, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void showImportDialog(int id) {
        if (!ListenerUtil.mutListener.listen(7688)) {
            showImportDialog(id, "");
        }
    }

    @Override
    public void showImportDialog(int id, String message) {
        if (!ListenerUtil.mutListener.listen(7709)) {
            // On API19+ we only use import dialog to confirm, otherwise we use it the whole time
            if ((ListenerUtil.mutListener.listen(7699) ? (((ListenerUtil.mutListener.listen(7693) ? (id >= ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7692) ? (id <= ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7691) ? (id > ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7690) ? (id < ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7689) ? (id != ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (id == ImportDialog.DIALOG_IMPORT_ADD_CONFIRM))))))) && ((ListenerUtil.mutListener.listen(7698) ? (id >= ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7697) ? (id <= ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7696) ? (id > ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7695) ? (id < ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7694) ? (id != ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (id == ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM)))))))) : (((ListenerUtil.mutListener.listen(7693) ? (id >= ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7692) ? (id <= ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7691) ? (id > ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7690) ? (id < ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (ListenerUtil.mutListener.listen(7689) ? (id != ImportDialog.DIALOG_IMPORT_ADD_CONFIRM) : (id == ImportDialog.DIALOG_IMPORT_ADD_CONFIRM))))))) || ((ListenerUtil.mutListener.listen(7698) ? (id >= ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7697) ? (id <= ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7696) ? (id > ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7695) ? (id < ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (ListenerUtil.mutListener.listen(7694) ? (id != ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM) : (id == ImportDialog.DIALOG_IMPORT_REPLACE_CONFIRM)))))))))) {
                if (!ListenerUtil.mutListener.listen(7707)) {
                    Timber.d("showImportDialog() delegating to ImportDialog");
                }
                AsyncDialogFragment newFragment = ImportDialog.newInstance(id, message);
                if (!ListenerUtil.mutListener.listen(7708)) {
                    showAsyncDialogFragment(newFragment);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7700)) {
                    Timber.d("showImportDialog() delegating to file picker intent");
                }
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                if (!ListenerUtil.mutListener.listen(7701)) {
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                if (!ListenerUtil.mutListener.listen(7702)) {
                    intent.setType("*/*");
                }
                if (!ListenerUtil.mutListener.listen(7703)) {
                    intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
                }
                if (!ListenerUtil.mutListener.listen(7704)) {
                    intent.putExtra("android.content.extra.FANCY", true);
                }
                if (!ListenerUtil.mutListener.listen(7705)) {
                    intent.putExtra("android.content.extra.SHOW_FILESIZE", true);
                }
                if (!ListenerUtil.mutListener.listen(7706)) {
                    startActivityForResultWithoutAnimation(intent, PICK_APKG_FILE);
                }
            }
        }
    }

    public void onSdCardNotMounted() {
        if (!ListenerUtil.mutListener.listen(7710)) {
            UIUtils.showThemedToast(this, getResources().getString(R.string.sd_card_not_mounted), false);
        }
        if (!ListenerUtil.mutListener.listen(7711)) {
            finishWithoutAnimation();
        }
    }

    // Callback method to submit error report
    public void sendErrorReport() {
        if (!ListenerUtil.mutListener.listen(7712)) {
            AnkiDroidApp.sendExceptionReport(new RuntimeException(), "DeckPicker.sendErrorReport");
        }
    }

    private RepairCollectionTask repairCollectionTask() {
        return new RepairCollectionTask(this);
    }

    private static class RepairCollectionTask extends TaskListenerWithContext<DeckPicker, Void, Boolean> {

        public RepairCollectionTask(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7713)) {
                deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, "", deckPicker.getResources().getString(R.string.backup_repair_deck_progress), false);
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, Boolean result) {
            if (!ListenerUtil.mutListener.listen(7716)) {
                if ((ListenerUtil.mutListener.listen(7714) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7715)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7719)) {
                if (!result) {
                    if (!ListenerUtil.mutListener.listen(7717)) {
                        UIUtils.showThemedToast(deckPicker, deckPicker.getResources().getString(R.string.deck_repair_error), true);
                    }
                    if (!ListenerUtil.mutListener.listen(7718)) {
                        deckPicker.showCollectionErrorDialog();
                    }
                }
            }
        }
    }

    // Callback method to handle repairing deck
    public void repairCollection() {
        if (!ListenerUtil.mutListener.listen(7720)) {
            Timber.i("Repairing the Collection");
        }
        if (!ListenerUtil.mutListener.listen(7721)) {
            TaskManager.launchCollectionTask(new CollectionTask.RepairCollectionn(), repairCollectionTask());
        }
    }

    // Callback method to handle database integrity check
    public void integrityCheck() {
        // display a dialog box if we don't have the space
        CollectionIntegrityStorageCheck status = CollectionIntegrityStorageCheck.createInstance(this);
        if (!ListenerUtil.mutListener.listen(7725)) {
            if (status.shouldWarnOnIntegrityCheck()) {
                if (!ListenerUtil.mutListener.listen(7723)) {
                    Timber.d("Displaying File Size confirmation");
                }
                if (!ListenerUtil.mutListener.listen(7724)) {
                    new MaterialDialog.Builder(this).title(R.string.check_db_title).content(status.getWarningDetails(this)).positiveText(R.string.integrity_check_continue_anyway).onPositive((dialog, which) -> performIntegrityCheck()).negativeText(R.string.dialog_cancel).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7722)) {
                    performIntegrityCheck();
                }
            }
        }
    }

    private void performIntegrityCheck() {
        if (!ListenerUtil.mutListener.listen(7726)) {
            Timber.i("performIntegrityCheck()");
        }
        if (!ListenerUtil.mutListener.listen(7727)) {
            TaskManager.launchCollectionTask(new CollectionTask.CheckDatabase(), new CheckDatabaseListener());
        }
    }

    private MediaCheckListener mediaCheckListener() {
        return new MediaCheckListener(this);
    }

    private static class MediaCheckListener extends TaskListenerWithContext<DeckPicker, Void, PairWithBoolean<List<List<String>>>> {

        public MediaCheckListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7728)) {
                deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, "", deckPicker.getResources().getString(R.string.check_media_message), false);
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, PairWithBoolean<List<List<String>>> result) {
            if (!ListenerUtil.mutListener.listen(7731)) {
                if ((ListenerUtil.mutListener.listen(7729) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7730)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7734)) {
                if (result.bool) {
                    @SuppressWarnings("unchecked")
                    List<List<String>> checkList = result.other;
                    if (!ListenerUtil.mutListener.listen(7733)) {
                        deckPicker.showMediaCheckDialog(MediaCheckDialog.DIALOG_MEDIA_CHECK_RESULTS, checkList);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7732)) {
                        deckPicker.showSimpleMessageDialog(deckPicker.getResources().getString(R.string.check_media_failed));
                    }
                }
            }
        }
    }

    @Override
    public void mediaCheck() {
        if (!ListenerUtil.mutListener.listen(7735)) {
            TaskManager.launchCollectionTask(new CollectionTask.CheckMedia(), mediaCheckListener());
        }
    }

    private MediaDeleteListener mediaDeleteListener() {
        return new MediaDeleteListener(this);
    }

    private static class MediaDeleteListener extends TaskListenerWithContext<DeckPicker, Void, Integer> {

        public MediaDeleteListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(7736)) {
                deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, "", deckPicker.getResources().getString(R.string.delete_media_message), false);
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, Integer deletedFiles) {
            if (!ListenerUtil.mutListener.listen(7739)) {
                if ((ListenerUtil.mutListener.listen(7737) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7738)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7740)) {
                deckPicker.showSimpleMessageDialog(deckPicker.getResources().getString(R.string.delete_media_result_title), deckPicker.getResources().getQuantityString(R.plurals.delete_media_result_message, deletedFiles, deletedFiles));
            }
        }
    }

    @Override
    public void deleteUnused(List<String> unused) {
        if (!ListenerUtil.mutListener.listen(7741)) {
            TaskManager.launchCollectionTask(new CollectionTask.DeleteMedia(unused), mediaDeleteListener());
        }
    }

    public void exit() {
        if (!ListenerUtil.mutListener.listen(7742)) {
            CollectionHelper.getInstance().closeCollection(false, "DeckPicker:exit()");
        }
        if (!ListenerUtil.mutListener.listen(7743)) {
            finishWithoutAnimation();
        }
    }

    public void handleDbError() {
        if (!ListenerUtil.mutListener.listen(7744)) {
            Timber.i("Displaying Database Error");
        }
        if (!ListenerUtil.mutListener.listen(7745)) {
            showDatabaseErrorDialog(DatabaseErrorDialog.DIALOG_LOAD_FAILED);
        }
    }

    public void handleDbLocked() {
        if (!ListenerUtil.mutListener.listen(7746)) {
            Timber.i("Displaying Database Locked");
        }
        if (!ListenerUtil.mutListener.listen(7747)) {
            showDatabaseErrorDialog(DatabaseErrorDialog.DIALOG_DB_LOCKED);
        }
    }

    public void restoreFromBackup(String path) {
        if (!ListenerUtil.mutListener.listen(7748)) {
            importReplace(path);
        }
    }

    // Helper function to check if there are any saved stacktraces
    public boolean hasErrorFiles() {
        if (!ListenerUtil.mutListener.listen(7750)) {
            {
                long _loopCounter132 = 0;
                for (String file : this.fileList()) {
                    ListenerUtil.loopListener.listen("_loopCounter132", ++_loopCounter132);
                    if (!ListenerUtil.mutListener.listen(7749)) {
                        if (file.endsWith(".stacktrace")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Sync with Anki Web
    @Override
    public void sync() {
        if (!ListenerUtil.mutListener.listen(7751)) {
            sync(null);
        }
    }

    /**
     * The mother of all syncing attempts. This might be called from sync() as first attempt to sync a collection OR
     * from the mSyncConflictResolutionListener if the first attempt determines that a full-sync is required.
     */
    @Override
    public void sync(Connection.ConflictResolution syncConflictResolution) {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        String hkey = preferences.getString("hkey", "");
        if (!ListenerUtil.mutListener.listen(7761)) {
            if ((ListenerUtil.mutListener.listen(7756) ? (hkey.length() >= 0) : (ListenerUtil.mutListener.listen(7755) ? (hkey.length() <= 0) : (ListenerUtil.mutListener.listen(7754) ? (hkey.length() > 0) : (ListenerUtil.mutListener.listen(7753) ? (hkey.length() < 0) : (ListenerUtil.mutListener.listen(7752) ? (hkey.length() != 0) : (hkey.length() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(7758)) {
                    Timber.w("User not logged in");
                }
                if (!ListenerUtil.mutListener.listen(7759)) {
                    mPullToSyncWrapper.setRefreshing(false);
                }
                if (!ListenerUtil.mutListener.listen(7760)) {
                    showSyncErrorDialog(SyncErrorDialog.DIALOG_USER_NOT_LOGGED_IN_SYNC);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7757)) {
                    Connection.sync(mSyncListener, new Connection.Payload(new Object[] { hkey, preferences.getBoolean("syncFetchesMedia", true), syncConflictResolution, HostNumFactory.getInstance(getBaseContext()) }));
                }
            }
        }
    }

    private final Connection.TaskListener mSyncListener = new Connection.CancellableTaskListener() {

        private String currentMessage;

        private long countUp;

        private long countDown;

        private boolean dialogDisplayFailure = false;

        @Override
        public void onDisconnected() {
            if (!ListenerUtil.mutListener.listen(7762)) {
                showSyncLogMessage(R.string.youre_offline, "");
            }
        }

        @Override
        public void onCancelled() {
            if (!ListenerUtil.mutListener.listen(7763)) {
                showSyncLogMessage(R.string.sync_cancelled, "");
            }
            if (!ListenerUtil.mutListener.listen(7766)) {
                if (!dialogDisplayFailure) {
                    if (!ListenerUtil.mutListener.listen(7764)) {
                        mProgressDialog.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(7765)) {
                        // update deck list in case sync was cancelled during media sync and main sync was actually successful
                        updateDeckList();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7767)) {
                // reset our display failure fate, just in case it is re-used
                dialogDisplayFailure = false;
            }
        }

        @Override
        public void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(7768)) {
                countUp = 0;
            }
            if (!ListenerUtil.mutListener.listen(7769)) {
                countDown = 0;
            }
            final long syncStartTime = getCol().getTime().intTimeMS();
            if (!ListenerUtil.mutListener.listen(7776)) {
                if ((ListenerUtil.mutListener.listen(7770) ? (mProgressDialog == null && !mProgressDialog.isShowing()) : (mProgressDialog == null || !mProgressDialog.isShowing()))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(7774)) {
                            mProgressDialog = StyledProgressDialog.show(DeckPicker.this, getResources().getString(R.string.sync_title), getResources().getString(R.string.sync_title) + "\n" + getResources().getString(R.string.sync_up_down_size, countUp, countDown), false);
                        }
                    } catch (WindowManager.BadTokenException e) {
                        if (!ListenerUtil.mutListener.listen(7771)) {
                            // If we could not show the progress dialog to start even, bail out - user will get a message
                            Timber.w(e, "Unable to display Sync progress dialog, Activity not valid?");
                        }
                        if (!ListenerUtil.mutListener.listen(7772)) {
                            dialogDisplayFailure = true;
                        }
                        if (!ListenerUtil.mutListener.listen(7773)) {
                            Connection.cancel();
                        }
                        return;
                    }
                    if (!ListenerUtil.mutListener.listen(7775)) {
                        // Override the back key so that the user can cancel a sync which is in progress
                        mProgressDialog.setOnKeyListener((dialog, keyCode, event) -> {
                            // Make sure our method doesn't get called twice
                            if (event.getAction() != KeyEvent.ACTION_DOWN) {
                                return true;
                            }
                            if (keyCode == KeyEvent.KEYCODE_BACK && Connection.isCancellable() && !Connection.getIsCancelled()) {
                                // If less than 2s has elapsed since sync started then don't ask for confirmation
                                if (getCol().getTime().intTimeMS() - syncStartTime < 2000) {
                                    Connection.cancel();
                                    mProgressDialog.setContent(R.string.sync_cancel_message);
                                    return true;
                                }
                                // Show confirmation dialog to check if the user wants to cancel the sync
                                MaterialDialog.Builder builder = new MaterialDialog.Builder(mProgressDialog.getContext());
                                builder.content(R.string.cancel_sync_confirm).cancelable(false).positiveText(R.string.dialog_ok).negativeText(R.string.continue_sync).onPositive((inner_dialog, which) -> {
                                    mProgressDialog.setContent(R.string.sync_cancel_message);
                                    Connection.cancel();
                                });
                                builder.show();
                                return true;
                            } else {
                                return false;
                            }
                        });
                    }
                }
            }
            // Note: getLs() in Libanki doesn't take into account the case when no changes were found, or sync cancelled
            SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
            if (!ListenerUtil.mutListener.listen(7777)) {
                preferences.edit().putLong("lastSyncTime", syncStartTime).apply();
            }
        }

        @Override
        public void onProgressUpdate(Object... values) {
            Resources res = getResources();
            if (!ListenerUtil.mutListener.listen(7804)) {
                if (values[0] instanceof Boolean) {
                    // This is the part Download missing media of syncing
                    int total = (Integer) values[1];
                    int done = (Integer) values[2];
                    if (!ListenerUtil.mutListener.listen(7802)) {
                        values[0] = (values[3]);
                    }
                    if (!ListenerUtil.mutListener.listen(7803)) {
                        values[1] = res.getString(R.string.sync_downloading_media, done, total);
                    }
                } else if (values[0] instanceof Integer) {
                    int id = (Integer) values[0];
                    if (!ListenerUtil.mutListener.listen(7793)) {
                        if ((ListenerUtil.mutListener.listen(7791) ? (id >= 0) : (ListenerUtil.mutListener.listen(7790) ? (id <= 0) : (ListenerUtil.mutListener.listen(7789) ? (id > 0) : (ListenerUtil.mutListener.listen(7788) ? (id < 0) : (ListenerUtil.mutListener.listen(7787) ? (id == 0) : (id != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(7792)) {
                                currentMessage = res.getString(id);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7801)) {
                        if ((ListenerUtil.mutListener.listen(7798) ? (values.length <= 3) : (ListenerUtil.mutListener.listen(7797) ? (values.length > 3) : (ListenerUtil.mutListener.listen(7796) ? (values.length < 3) : (ListenerUtil.mutListener.listen(7795) ? (values.length != 3) : (ListenerUtil.mutListener.listen(7794) ? (values.length == 3) : (values.length >= 3))))))) {
                            if (!ListenerUtil.mutListener.listen(7799)) {
                                countUp = (Long) values[1];
                            }
                            if (!ListenerUtil.mutListener.listen(7800)) {
                                countDown = (Long) values[2];
                            }
                        }
                    }
                } else if (values[0] instanceof String) {
                    if (!ListenerUtil.mutListener.listen(7778)) {
                        currentMessage = (String) values[0];
                    }
                    if (!ListenerUtil.mutListener.listen(7786)) {
                        if ((ListenerUtil.mutListener.listen(7783) ? (values.length <= 3) : (ListenerUtil.mutListener.listen(7782) ? (values.length > 3) : (ListenerUtil.mutListener.listen(7781) ? (values.length < 3) : (ListenerUtil.mutListener.listen(7780) ? (values.length != 3) : (ListenerUtil.mutListener.listen(7779) ? (values.length == 3) : (values.length >= 3))))))) {
                            if (!ListenerUtil.mutListener.listen(7784)) {
                                countUp = (Long) values[1];
                            }
                            if (!ListenerUtil.mutListener.listen(7785)) {
                                countDown = (Long) values[2];
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7815)) {
                if ((ListenerUtil.mutListener.listen(7805) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(7814)) {
                        // mProgressDialog.setTitle((String) values[0]);
                        mProgressDialog.setContent(currentMessage + "\n" + res.getString(R.string.sync_up_down_size, (ListenerUtil.mutListener.listen(7809) ? (countUp % 1024) : (ListenerUtil.mutListener.listen(7808) ? (countUp * 1024) : (ListenerUtil.mutListener.listen(7807) ? (countUp - 1024) : (ListenerUtil.mutListener.listen(7806) ? (countUp + 1024) : (countUp / 1024))))), (ListenerUtil.mutListener.listen(7813) ? (countDown % 1024) : (ListenerUtil.mutListener.listen(7812) ? (countDown * 1024) : (ListenerUtil.mutListener.listen(7811) ? (countDown - 1024) : (ListenerUtil.mutListener.listen(7810) ? (countDown + 1024) : (countDown / 1024)))))));
                    }
                }
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onPostExecute(Payload data) {
            if (!ListenerUtil.mutListener.listen(7816)) {
                mPullToSyncWrapper.setRefreshing(false);
            }
            String dialogMessage = "";
            if (!ListenerUtil.mutListener.listen(7817)) {
                Timber.d("Sync Listener onPostExecute()");
            }
            Resources res = getResources();
            try {
                if (!ListenerUtil.mutListener.listen(7822)) {
                    if ((ListenerUtil.mutListener.listen(7820) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(7821)) {
                            mProgressDialog.dismiss();
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                if (!ListenerUtil.mutListener.listen(7818)) {
                    Timber.e(e, "Could not dismiss mProgressDialog. The Activity must have been destroyed while the AsyncTask was running");
                }
                if (!ListenerUtil.mutListener.listen(7819)) {
                    AnkiDroidApp.sendExceptionReport(e, "DeckPicker.onPostExecute", "Could not dismiss mProgressDialog");
                }
            }
            String syncMessage = data.message;
            if (!ListenerUtil.mutListener.listen(7937)) {
                if (!data.success) {
                    Object[] result = data.result;
                    Syncer.ConnectionResultType resultType = data.resultType;
                    if (!ListenerUtil.mutListener.listen(7936)) {
                        if (resultType != null) {
                            if (!ListenerUtil.mutListener.listen(7935)) {
                                switch(resultType) {
                                    case BAD_AUTH:
                                        // delete old auth information
                                        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
                                        Editor editor = preferences.edit();
                                        if (!ListenerUtil.mutListener.listen(7852)) {
                                            editor.putString("username", "");
                                        }
                                        if (!ListenerUtil.mutListener.listen(7853)) {
                                            editor.putString("hkey", "");
                                        }
                                        if (!ListenerUtil.mutListener.listen(7854)) {
                                            editor.apply();
                                        }
                                        if (!ListenerUtil.mutListener.listen(7855)) {
                                            // then show not logged in dialog
                                            showSyncErrorDialog(SyncErrorDialog.DIALOG_USER_NOT_LOGGED_IN_SYNC);
                                        }
                                        break;
                                    case NO_CHANGES:
                                        if (!ListenerUtil.mutListener.listen(7856)) {
                                            SyncStatus.markSyncCompleted();
                                        }
                                        if (!ListenerUtil.mutListener.listen(7857)) {
                                            // show no changes message, use false flag so we don't show "sync error" as the Dialog title
                                            showSyncLogMessage(R.string.sync_no_changes_message, "");
                                        }
                                        break;
                                    case CLOCK_OFF:
                                        long diff = (Long) result[0];
                                        if (!ListenerUtil.mutListener.listen(7879)) {
                                            if ((ListenerUtil.mutListener.listen(7862) ? (diff <= 86100) : (ListenerUtil.mutListener.listen(7861) ? (diff > 86100) : (ListenerUtil.mutListener.listen(7860) ? (diff < 86100) : (ListenerUtil.mutListener.listen(7859) ? (diff != 86100) : (ListenerUtil.mutListener.listen(7858) ? (diff == 86100) : (diff >= 86100))))))) {
                                                if (!ListenerUtil.mutListener.listen(7878)) {
                                                    // The difference if more than a day minus 5 minutes acceptable by ankiweb error
                                                    dialogMessage = res.getString(R.string.sync_log_clocks_unsynchronized, diff, res.getString(R.string.sync_log_clocks_unsynchronized_date));
                                                }
                                            } else if ((ListenerUtil.mutListener.listen(7875) ? (Math.abs((ListenerUtil.mutListener.listen(7870) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) % 1800.0) : (ListenerUtil.mutListener.listen(7869) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) / 1800.0) : (ListenerUtil.mutListener.listen(7868) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) * 1800.0) : (ListenerUtil.mutListener.listen(7867) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) + 1800.0) : (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) - 1800.0)))))) <= 1500.0) : (ListenerUtil.mutListener.listen(7874) ? (Math.abs((ListenerUtil.mutListener.listen(7870) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) % 1800.0) : (ListenerUtil.mutListener.listen(7869) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) / 1800.0) : (ListenerUtil.mutListener.listen(7868) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) * 1800.0) : (ListenerUtil.mutListener.listen(7867) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) + 1800.0) : (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) - 1800.0)))))) > 1500.0) : (ListenerUtil.mutListener.listen(7873) ? (Math.abs((ListenerUtil.mutListener.listen(7870) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) % 1800.0) : (ListenerUtil.mutListener.listen(7869) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) / 1800.0) : (ListenerUtil.mutListener.listen(7868) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) * 1800.0) : (ListenerUtil.mutListener.listen(7867) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) + 1800.0) : (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) - 1800.0)))))) < 1500.0) : (ListenerUtil.mutListener.listen(7872) ? (Math.abs((ListenerUtil.mutListener.listen(7870) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) % 1800.0) : (ListenerUtil.mutListener.listen(7869) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) / 1800.0) : (ListenerUtil.mutListener.listen(7868) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) * 1800.0) : (ListenerUtil.mutListener.listen(7867) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) + 1800.0) : (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) - 1800.0)))))) != 1500.0) : (ListenerUtil.mutListener.listen(7871) ? (Math.abs((ListenerUtil.mutListener.listen(7870) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) % 1800.0) : (ListenerUtil.mutListener.listen(7869) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) / 1800.0) : (ListenerUtil.mutListener.listen(7868) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) * 1800.0) : (ListenerUtil.mutListener.listen(7867) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) + 1800.0) : (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) - 1800.0)))))) == 1500.0) : (Math.abs((ListenerUtil.mutListener.listen(7870) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) % 1800.0) : (ListenerUtil.mutListener.listen(7869) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) / 1800.0) : (ListenerUtil.mutListener.listen(7868) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) * 1800.0) : (ListenerUtil.mutListener.listen(7867) ? (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) + 1800.0) : (((ListenerUtil.mutListener.listen(7866) ? (diff / 3600.0) : (ListenerUtil.mutListener.listen(7865) ? (diff * 3600.0) : (ListenerUtil.mutListener.listen(7864) ? (diff - 3600.0) : (ListenerUtil.mutListener.listen(7863) ? (diff + 3600.0) : (diff % 3600.0)))))) - 1800.0)))))) >= 1500.0))))))) {
                                                if (!ListenerUtil.mutListener.listen(7877)) {
                                                    // It doesn't work for all timezones, but it covers most and it's a guess anyway
                                                    dialogMessage = res.getString(R.string.sync_log_clocks_unsynchronized, diff, res.getString(R.string.sync_log_clocks_unsynchronized_tz));
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7876)) {
                                                    dialogMessage = res.getString(R.string.sync_log_clocks_unsynchronized, diff, "");
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(7880)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case FULL_SYNC:
                                        if (!ListenerUtil.mutListener.listen(7883)) {
                                            if (getCol().isEmpty()) {
                                                if (!ListenerUtil.mutListener.listen(7882)) {
                                                    // don't prompt user to resolve sync conflict if local collection empty
                                                    sync(FULL_DOWNLOAD);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7881)) {
                                                    // If can't be resolved then automatically then show conflict resolution dialog
                                                    showSyncErrorDialog(SyncErrorDialog.DIALOG_SYNC_CONFLICT_RESOLUTION);
                                                }
                                            }
                                        }
                                        break;
                                    case BASIC_CHECK_FAILED:
                                        if (!ListenerUtil.mutListener.listen(7884)) {
                                            dialogMessage = res.getString(R.string.sync_basic_check_failed, res.getString(R.string.check_db));
                                        }
                                        if (!ListenerUtil.mutListener.listen(7885)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case DB_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7886)) {
                                            showSyncErrorDialog(SyncErrorDialog.DIALOG_SYNC_CORRUPT_COLLECTION, syncMessage);
                                        }
                                        break;
                                    case OVERWRITE_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7887)) {
                                            dialogMessage = res.getString(R.string.sync_overwrite_error);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7888)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case REMOTE_DB_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7889)) {
                                            dialogMessage = res.getString(R.string.sync_remote_db_error);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7890)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case SD_ACCESS_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7891)) {
                                            dialogMessage = res.getString(R.string.sync_write_access_error);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7892)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case FINISH_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7893)) {
                                            dialogMessage = res.getString(R.string.sync_log_finish_error);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7894)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case CONNECTION_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7895)) {
                                            dialogMessage = res.getString(R.string.sync_connection_error);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7903)) {
                                            if ((ListenerUtil.mutListener.listen(7901) ? ((ListenerUtil.mutListener.listen(7900) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(7899) ? (result.length > 0) : (ListenerUtil.mutListener.listen(7898) ? (result.length < 0) : (ListenerUtil.mutListener.listen(7897) ? (result.length != 0) : (ListenerUtil.mutListener.listen(7896) ? (result.length == 0) : (result.length >= 0)))))) || result[0] instanceof Exception) : ((ListenerUtil.mutListener.listen(7900) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(7899) ? (result.length > 0) : (ListenerUtil.mutListener.listen(7898) ? (result.length < 0) : (ListenerUtil.mutListener.listen(7897) ? (result.length != 0) : (ListenerUtil.mutListener.listen(7896) ? (result.length == 0) : (result.length >= 0)))))) && result[0] instanceof Exception))) {
                                                if (!ListenerUtil.mutListener.listen(7902)) {
                                                    dialogMessage += "\n\n" + ((Exception) result[0]).getLocalizedMessage();
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(7904)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case IO_EXCEPTION:
                                        if (!ListenerUtil.mutListener.listen(7905)) {
                                            handleDbError();
                                        }
                                        break;
                                    case GENERIC_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7906)) {
                                            dialogMessage = res.getString(R.string.sync_generic_error);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7907)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case OUT_OF_MEMORY_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7908)) {
                                            dialogMessage = res.getString(R.string.error_insufficient_memory);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7909)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case SANITY_CHECK_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7910)) {
                                            dialogMessage = res.getString(R.string.sync_sanity_failed);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7911)) {
                                            showSyncErrorDialog(SyncErrorDialog.DIALOG_SYNC_SANITY_ERROR, joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case SERVER_ABORT:
                                        if (!ListenerUtil.mutListener.listen(7912)) {
                                            // syncMsg has already been set above, no need to fetch it here.
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case MEDIA_SYNC_SERVER_ERROR:
                                        if (!ListenerUtil.mutListener.listen(7913)) {
                                            dialogMessage = res.getString(R.string.sync_media_error_check);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7914)) {
                                            showSyncErrorDialog(SyncErrorDialog.DIALOG_MEDIA_SYNC_ERROR, joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    case CUSTOM_SYNC_SERVER_URL:
                                        String url = (ListenerUtil.mutListener.listen(7920) ? ((ListenerUtil.mutListener.listen(7919) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(7918) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(7917) ? (result.length < 0) : (ListenerUtil.mutListener.listen(7916) ? (result.length != 0) : (ListenerUtil.mutListener.listen(7915) ? (result.length == 0) : (result.length > 0)))))) || result[0] instanceof CustomSyncServerUrlException) : ((ListenerUtil.mutListener.listen(7919) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(7918) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(7917) ? (result.length < 0) : (ListenerUtil.mutListener.listen(7916) ? (result.length != 0) : (ListenerUtil.mutListener.listen(7915) ? (result.length == 0) : (result.length > 0)))))) && result[0] instanceof CustomSyncServerUrlException)) ? ((CustomSyncServerUrlException) result[0]).getUrl() : "unknown";
                                        if (!ListenerUtil.mutListener.listen(7921)) {
                                            dialogMessage = res.getString(R.string.sync_error_invalid_sync_server, url);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7922)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(7933)) {
                                            if ((ListenerUtil.mutListener.listen(7928) ? ((ListenerUtil.mutListener.listen(7927) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(7926) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(7925) ? (result.length < 0) : (ListenerUtil.mutListener.listen(7924) ? (result.length != 0) : (ListenerUtil.mutListener.listen(7923) ? (result.length == 0) : (result.length > 0)))))) || result[0] instanceof Integer) : ((ListenerUtil.mutListener.listen(7927) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(7926) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(7925) ? (result.length < 0) : (ListenerUtil.mutListener.listen(7924) ? (result.length != 0) : (ListenerUtil.mutListener.listen(7923) ? (result.length == 0) : (result.length > 0)))))) && result[0] instanceof Integer))) {
                                                int code = (Integer) result[0];
                                                if (!ListenerUtil.mutListener.listen(7930)) {
                                                    dialogMessage = rewriteError(code);
                                                }
                                                if (!ListenerUtil.mutListener.listen(7932)) {
                                                    if (dialogMessage == null) {
                                                        if (!ListenerUtil.mutListener.listen(7931)) {
                                                            dialogMessage = res.getString(R.string.sync_log_error_specific, Integer.toString(code), result[1]);
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7929)) {
                                                    dialogMessage = res.getString(R.string.sync_log_error_specific, Integer.toString(-1), resultType);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(7934)) {
                                            showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                                        }
                                        break;
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7850)) {
                                dialogMessage = res.getString(R.string.sync_generic_error);
                            }
                            if (!ListenerUtil.mutListener.listen(7851)) {
                                showSyncErrorMessage(joinSyncMessages(dialogMessage, syncMessage));
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7823)) {
                        Timber.i("Sync was successful");
                    }
                    if (!ListenerUtil.mutListener.listen(7842)) {
                        if ((ListenerUtil.mutListener.listen(7824) ? (data.data[2] != null || !"".equals(data.data[2])) : (data.data[2] != null && !"".equals(data.data[2])))) {
                            if (!ListenerUtil.mutListener.listen(7840)) {
                                Timber.i("Syncing had additional information");
                            }
                            // Note: Do not log this data. May contain user email.
                            String message = res.getString(R.string.sync_database_acknowledge) + "\n\n" + data.data[2];
                            if (!ListenerUtil.mutListener.listen(7841)) {
                                showSimpleMessageDialog(message);
                            }
                        } else if ((ListenerUtil.mutListener.listen(7830) ? ((ListenerUtil.mutListener.listen(7829) ? (data.data.length >= 0) : (ListenerUtil.mutListener.listen(7828) ? (data.data.length <= 0) : (ListenerUtil.mutListener.listen(7827) ? (data.data.length < 0) : (ListenerUtil.mutListener.listen(7826) ? (data.data.length != 0) : (ListenerUtil.mutListener.listen(7825) ? (data.data.length == 0) : (data.data.length > 0)))))) || data.data[0] instanceof Connection.ConflictResolution) : ((ListenerUtil.mutListener.listen(7829) ? (data.data.length >= 0) : (ListenerUtil.mutListener.listen(7828) ? (data.data.length <= 0) : (ListenerUtil.mutListener.listen(7827) ? (data.data.length < 0) : (ListenerUtil.mutListener.listen(7826) ? (data.data.length != 0) : (ListenerUtil.mutListener.listen(7825) ? (data.data.length == 0) : (data.data.length > 0)))))) && data.data[0] instanceof Connection.ConflictResolution))) {
                            // A full sync occurred
                            Connection.ConflictResolution dataString = (Connection.ConflictResolution) data.data[0];
                            if (!ListenerUtil.mutListener.listen(7839)) {
                                switch(dataString) {
                                    case FULL_UPLOAD:
                                        if (!ListenerUtil.mutListener.listen(7833)) {
                                            Timber.i("Full Upload Completed");
                                        }
                                        if (!ListenerUtil.mutListener.listen(7834)) {
                                            showSyncLogMessage(R.string.sync_log_uploading_message, syncMessage);
                                        }
                                        break;
                                    case FULL_DOWNLOAD:
                                        if (!ListenerUtil.mutListener.listen(7835)) {
                                            Timber.i("Full Download Completed");
                                        }
                                        if (!ListenerUtil.mutListener.listen(7836)) {
                                            showSyncLogMessage(R.string.backup_full_sync_from_server, syncMessage);
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(7837)) {
                                            // should not be possible
                                            Timber.i("Full Sync Completed (Unknown Direction)");
                                        }
                                        if (!ListenerUtil.mutListener.listen(7838)) {
                                            showSyncLogMessage(R.string.sync_database_acknowledge, syncMessage);
                                        }
                                        break;
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7831)) {
                                Timber.i("Regular sync completed successfully");
                            }
                            if (!ListenerUtil.mutListener.listen(7832)) {
                                showSyncLogMessage(R.string.sync_database_acknowledge, syncMessage);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7843)) {
                        // Mark sync as completed - then refresh the sync icon
                        SyncStatus.markSyncCompleted();
                    }
                    if (!ListenerUtil.mutListener.listen(7844)) {
                        supportInvalidateOptionsMenu();
                    }
                    if (!ListenerUtil.mutListener.listen(7845)) {
                        updateDeckList();
                    }
                    if (!ListenerUtil.mutListener.listen(7846)) {
                        WidgetStatus.update(DeckPicker.this);
                    }
                    if (!ListenerUtil.mutListener.listen(7849)) {
                        if (mFragmented) {
                            try {
                                if (!ListenerUtil.mutListener.listen(7848)) {
                                    loadStudyOptionsFragment(false);
                                }
                            } catch (IllegalStateException e) {
                                if (!ListenerUtil.mutListener.listen(7847)) {
                                    // fragment here is fine since we build a fresh fragment on resume anyway.
                                    Timber.w(e, "Failed to load StudyOptionsFragment after sync.");
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    @VisibleForTesting()
    @Nullable
    public String rewriteError(int code) {
        String msg;
        Resources res = getResources();
        switch(code) {
            case 407:
                msg = res.getString(R.string.sync_error_407_proxy_required);
                break;
            case 409:
                msg = res.getString(R.string.sync_error_409);
                break;
            case 413:
                msg = res.getString(R.string.sync_error_413_collection_size);
                break;
            case 500:
                msg = res.getString(R.string.sync_error_500_unknown);
                break;
            case 501:
                msg = res.getString(R.string.sync_error_501_upgrade_required);
                break;
            case 502:
                msg = res.getString(R.string.sync_error_502_maintenance);
                break;
            case 503:
                msg = res.getString(R.string.sync_too_busy);
                break;
            case 504:
                msg = res.getString(R.string.sync_error_504_gateway_timeout);
                break;
            default:
                msg = null;
                break;
        }
        return msg;
    }

    @Nullable
    public static String joinSyncMessages(@Nullable String dialogMessage, @Nullable String syncMessage) {
        // If both strings have text, separate them by a new line, otherwise return whichever has text
        if ((ListenerUtil.mutListener.listen(7938) ? (!TextUtils.isEmpty(dialogMessage) || !TextUtils.isEmpty(syncMessage)) : (!TextUtils.isEmpty(dialogMessage) && !TextUtils.isEmpty(syncMessage)))) {
            return dialogMessage + "\n\n" + syncMessage;
        } else if (!TextUtils.isEmpty(dialogMessage)) {
            return dialogMessage;
        } else {
            return syncMessage;
        }
    }

    @Override
    public void loginToSyncServer() {
        Intent myAccount = new Intent(this, MyAccount.class);
        if (!ListenerUtil.mutListener.listen(7939)) {
            myAccount.putExtra("notLoggedIn", true);
        }
        if (!ListenerUtil.mutListener.listen(7940)) {
            startActivityForResultWithAnimation(myAccount, LOG_IN_FOR_SYNC, FADE);
        }
    }

    // Callback to import a file -- adding it to existing collection
    @Override
    public void importAdd(String importPath) {
        if (!ListenerUtil.mutListener.listen(7941)) {
            Timber.d("importAdd() for file %s", importPath);
        }
        if (!ListenerUtil.mutListener.listen(7942)) {
            TaskManager.launchCollectionTask(new CollectionTask.ImportAdd(importPath), mImportAddListener);
        }
    }

    // Callback to import a file -- replacing the existing collection
    @Override
    public void importReplace(String importPath) {
        if (!ListenerUtil.mutListener.listen(7943)) {
            TaskManager.launchCollectionTask(new CollectionTask.ImportReplace(importPath), importReplaceListener());
        }
    }

    @Override
    public void exportApkg(String filename, Long did, boolean includeSched, boolean includeMedia) {
        File exportDir = new File(getExternalCacheDir(), "export");
        if (!ListenerUtil.mutListener.listen(7944)) {
            exportDir.mkdirs();
        }
        File exportPath;
        String timeStampSuffix = "-" + TimeUtils.getTimestamp(getCol().getTime());
        if (filename != null) {
            // filename has been explicitly specified
            exportPath = new File(exportDir, filename);
        } else if (did != null) {
            // filename not explicitly specified, but a deck has been specified so use deck name
            exportPath = new File(exportDir, getCol().getDecks().get(did).getString("name").replaceAll("\\W+", "_") + timeStampSuffix + ".apkg");
        } else if (!includeSched) {
            // full export without scheduling is assumed to be shared with someone else -- use "All Decks.apkg"
            exportPath = new File(exportDir, "All Decks" + timeStampSuffix + ".apkg");
        } else {
            // full collection export -- use "collection.colpkg"
            File colPath = new File(getCol().getPath());
            String newFileName = colPath.getName().replace(".anki2", timeStampSuffix + ".colpkg");
            exportPath = new File(exportDir, newFileName);
        }
        if (!ListenerUtil.mutListener.listen(7945)) {
            TaskManager.launchCollectionTask(new CollectionTask.ExportApkg(exportPath.getPath(), did, includeSched, includeMedia), exportListener());
        }
    }

    public void emailFile(String path) {
        // Make sure the file actually exists
        File attachment = new File(path);
        if (!ListenerUtil.mutListener.listen(7948)) {
            if (!attachment.exists()) {
                if (!ListenerUtil.mutListener.listen(7946)) {
                    Timber.e("Specified apkg file %s does not exist", path);
                }
                if (!ListenerUtil.mutListener.listen(7947)) {
                    UIUtils.showThemedToast(this, getResources().getString(R.string.apk_share_error), false);
                }
                return;
            }
        }
        // Get a URI for the file to be shared via the FileProvider API
        Uri uri;
        try {
            uri = FileProvider.getUriForFile(DeckPicker.this, "com.ichi2.anki.apkgfileprovider", attachment);
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(7949)) {
                Timber.e("Could not generate a valid URI for the apkg file");
            }
            if (!ListenerUtil.mutListener.listen(7950)) {
                UIUtils.showThemedToast(this, getResources().getString(R.string.apk_share_error), false);
            }
            return;
        }
        Intent shareIntent = ShareCompat.IntentBuilder.from(DeckPicker.this).setType("application/apkg").setStream(uri).setSubject(getString(R.string.export_email_subject, attachment.getName())).setHtmlText(getString(R.string.export_email_text)).getIntent();
        if (!ListenerUtil.mutListener.listen(7954)) {
            if (shareIntent.resolveActivity(getPackageManager()) != null) {
                if (!ListenerUtil.mutListener.listen(7953)) {
                    startActivityWithoutAnimation(shareIntent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7951)) {
                    // Try to save it?
                    UIUtils.showSimpleSnackbar(this, R.string.export_send_no_handlers, false);
                }
                if (!ListenerUtil.mutListener.listen(7952)) {
                    saveExportFile(path);
                }
            }
        }
    }

    public void saveExportFile(String path) {
        // Make sure the file actually exists
        File attachment = new File(path);
        if (!ListenerUtil.mutListener.listen(7957)) {
            if (!attachment.exists()) {
                if (!ListenerUtil.mutListener.listen(7955)) {
                    Timber.e("saveExportFile() Specified apkg file %s does not exist", path);
                }
                if (!ListenerUtil.mutListener.listen(7956)) {
                    UIUtils.showSimpleSnackbar(this, R.string.export_save_apkg_unsuccessful, false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7958)) {
            // Send the user to the standard Android file picker via Intent
            mExportFileName = path;
        }
        Intent saveIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        if (!ListenerUtil.mutListener.listen(7959)) {
            saveIntent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        if (!ListenerUtil.mutListener.listen(7960)) {
            saveIntent.setType("application/apkg");
        }
        if (!ListenerUtil.mutListener.listen(7961)) {
            saveIntent.putExtra(Intent.EXTRA_TITLE, attachment.getName());
        }
        if (!ListenerUtil.mutListener.listen(7962)) {
            saveIntent.putExtra("android.content.extra.SHOW_ADVANCED", true);
        }
        if (!ListenerUtil.mutListener.listen(7963)) {
            saveIntent.putExtra("android.content.extra.FANCY", true);
        }
        if (!ListenerUtil.mutListener.listen(7964)) {
            saveIntent.putExtra("android.content.extra.SHOW_FILESIZE", true);
        }
        if (!ListenerUtil.mutListener.listen(7965)) {
            startActivityForResultWithoutAnimation(saveIntent, PICK_EXPORT_FILE);
        }
    }

    /**
     * Load a new studyOptionsFragment. If withDeckOptions is true, the deck options activity will
     * be loaded on top of it. Use this flag when creating a new filtered deck to allow the user to
     * modify the filter settings before being shown the fragment. The fragment itself will handle
     * rebuilding the deck if the settings change.
     */
    private void loadStudyOptionsFragment(boolean withDeckOptions) {
        StudyOptionsFragment details = StudyOptionsFragment.newInstance(withDeckOptions);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(7966)) {
            ft.replace(R.id.studyoptions_fragment, details);
        }
        if (!ListenerUtil.mutListener.listen(7967)) {
            ft.commit();
        }
    }

    public StudyOptionsFragment getFragment() {
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.studyoptions_fragment);
        if (!ListenerUtil.mutListener.listen(7968)) {
            if ((frag instanceof StudyOptionsFragment)) {
                return (StudyOptionsFragment) frag;
            }
        }
        return null;
    }

    /**
     * Show a message when the SD card is ejected
     */
    private void registerExternalStorageListener() {
        if (!ListenerUtil.mutListener.listen(7976)) {
            if (mUnmountReceiver == null) {
                if (!ListenerUtil.mutListener.listen(7972)) {
                    mUnmountReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if (!ListenerUtil.mutListener.listen(7971)) {
                                if (intent.getAction().equals(SdCardReceiver.MEDIA_EJECT)) {
                                    if (!ListenerUtil.mutListener.listen(7970)) {
                                        onSdCardNotMounted();
                                    }
                                } else if (intent.getAction().equals(SdCardReceiver.MEDIA_MOUNT)) {
                                    if (!ListenerUtil.mutListener.listen(7969)) {
                                        restartActivity();
                                    }
                                }
                            }
                        }
                    };
                }
                IntentFilter iFilter = new IntentFilter();
                if (!ListenerUtil.mutListener.listen(7973)) {
                    iFilter.addAction(SdCardReceiver.MEDIA_EJECT);
                }
                if (!ListenerUtil.mutListener.listen(7974)) {
                    iFilter.addAction(SdCardReceiver.MEDIA_MOUNT);
                }
                if (!ListenerUtil.mutListener.listen(7975)) {
                    registerReceiver(mUnmountReceiver, iFilter);
                }
            }
        }
    }

    public void addSharedDeck() {
        if (!ListenerUtil.mutListener.listen(7977)) {
            openUrl(Uri.parse(getResources().getString(R.string.shared_decks_url)));
        }
    }

    private void openStudyOptions(boolean withDeckOptions) {
        if (!ListenerUtil.mutListener.listen(7982)) {
            if (mFragmented) {
                if (!ListenerUtil.mutListener.listen(7981)) {
                    // The fragment will show the study options screen instead of launching a new activity.
                    loadStudyOptionsFragment(withDeckOptions);
                }
            } else {
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(7978)) {
                    intent.putExtra("withDeckOptions", withDeckOptions);
                }
                if (!ListenerUtil.mutListener.listen(7979)) {
                    intent.setClass(this, StudyOptionsActivity.class);
                }
                if (!ListenerUtil.mutListener.listen(7980)) {
                    startActivityForResultWithAnimation(intent, SHOW_STUDYOPTIONS, LEFT);
                }
            }
        }
    }

    private void openReviewerOrStudyOptions(DeckSelectionType selectionType) {
        if (!ListenerUtil.mutListener.listen(7989)) {
            switch(selectionType) {
                case DEFAULT:
                    if (!ListenerUtil.mutListener.listen(7985)) {
                        if (mFragmented) {
                            if (!ListenerUtil.mutListener.listen(7984)) {
                                openStudyOptions(false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7983)) {
                                openReviewer();
                            }
                        }
                    }
                    return;
                case SHOW_STUDY_OPTIONS:
                    if (!ListenerUtil.mutListener.listen(7986)) {
                        openStudyOptions(false);
                    }
                    return;
                case SKIP_STUDY_OPTIONS:
                    if (!ListenerUtil.mutListener.listen(7987)) {
                        openReviewer();
                    }
                    return;
                default:
                    if (!ListenerUtil.mutListener.listen(7988)) {
                        Timber.w("openReviewerOrStudyOptions: Unknown selection: %s", selectionType);
                    }
            }
        }
    }

    private void handleDeckSelection(long did, DeckSelectionType selectionType) {
        if (!ListenerUtil.mutListener.listen(7991)) {
            // Clear the undo history when selecting a new deck
            if (getCol().getDecks().selected() != did) {
                if (!ListenerUtil.mutListener.listen(7990)) {
                    getCol().clearUndo();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7992)) {
            // Select the deck
            getCol().getDecks().select(did);
        }
        if (!ListenerUtil.mutListener.listen(7993)) {
            // Also forget the last deck used by the Browser
            CardBrowser.clearLastDeckId();
        }
        if (!ListenerUtil.mutListener.listen(7994)) {
            // Reset the schedule so that we get the counts for the currently selected deck
            mFocusedDeck = did;
        }
        // Get some info about the deck to handle special cases
        AbstractDeckTreeNode<?> deckDueTreeNode = mDeckListAdapter.getNodeByDid(did);
        if (!ListenerUtil.mutListener.listen(7997)) {
            if ((ListenerUtil.mutListener.listen(7995) ? (!deckDueTreeNode.shouldDisplayCounts() && deckDueTreeNode.knownToHaveRep()) : (!deckDueTreeNode.shouldDisplayCounts() || deckDueTreeNode.knownToHaveRep()))) {
                if (!ListenerUtil.mutListener.listen(7996)) {
                    // If there is nothing to review, it'll come back to deck picker.
                    openReviewerOrStudyOptions(selectionType);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8014)) {
            // Figure out what action to take
            if (getCol().getSched().hasCardsTodayAfterStudyAheadLimit()) {
                if (!ListenerUtil.mutListener.listen(8013)) {
                    // If there are cards due that can't be studied yet (due to the learn ahead limit) then go to study options
                    openStudyOptions(false);
                }
            } else if ((ListenerUtil.mutListener.listen(7998) ? (getCol().getSched().newDue() && getCol().getSched().revDue()) : (getCol().getSched().newDue() || getCol().getSched().revDue()))) {
                if (!ListenerUtil.mutListener.listen(8009)) {
                    // If there are no cards to review because of the daily study limit then give "Study more" option
                    UIUtils.showSnackbar(this, R.string.studyoptions_limit_reached, false, R.string.study_more, v -> {
                        CustomStudyDialog d = CustomStudyDialog.newInstance(CustomStudyDialog.CONTEXT_MENU_LIMITS, getCol().getDecks().selected(), true);
                        showDialogFragment(d);
                    }, findViewById(R.id.root_layout), mSnackbarShowHideCallback);
                }
                if (!ListenerUtil.mutListener.listen(8012)) {
                    // are required for all snackbars below.
                    if (mFragmented) {
                        if (!ListenerUtil.mutListener.listen(8011)) {
                            // regardless of whether the deck is currently reviewable or not.
                            openStudyOptions(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8010)) {
                            // highlighted correctly.
                            updateDeckList();
                        }
                    }
                }
            } else if (getCol().getDecks().isDyn(did)) {
                if (!ListenerUtil.mutListener.listen(8008)) {
                    // Go to the study options screen if filtered deck with no cards to study
                    openStudyOptions(false);
                }
            } else if ((ListenerUtil.mutListener.listen(7999) ? (!deckDueTreeNode.hasChildren() || getCol().isEmptyDeck(did)) : (!deckDueTreeNode.hasChildren() && getCol().isEmptyDeck(did)))) {
                if (!ListenerUtil.mutListener.listen(8004)) {
                    // If the deck is empty and has no children then show a message saying it's empty
                    UIUtils.showSnackbar(this, R.string.empty_deck, false, R.string.empty_deck_add_note, v -> addNote(), findViewById(R.id.root_layout), mSnackbarShowHideCallback);
                }
                if (!ListenerUtil.mutListener.listen(8007)) {
                    if (mFragmented) {
                        if (!ListenerUtil.mutListener.listen(8006)) {
                            openStudyOptions(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8005)) {
                            updateDeckList();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8000)) {
                    // Otherwise say there are no cards scheduled to study, and give option to do custom study
                    UIUtils.showSnackbar(this, R.string.studyoptions_empty_schedule, false, R.string.custom_study, v -> {
                        CustomStudyDialog d = CustomStudyDialog.newInstance(CustomStudyDialog.CONTEXT_MENU_EMPTY_SCHEDULE, getCol().getDecks().selected(), true);
                        showDialogFragment(d);
                    }, findViewById(R.id.root_layout), mSnackbarShowHideCallback);
                }
                if (!ListenerUtil.mutListener.listen(8003)) {
                    if (mFragmented) {
                        if (!ListenerUtil.mutListener.listen(8002)) {
                            openStudyOptions(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8001)) {
                            updateDeckList();
                        }
                    }
                }
            }
        }
    }

    private void openHelpUrl(Uri helpUrl) {
        if (!ListenerUtil.mutListener.listen(8015)) {
            openUrl(helpUrl);
        }
    }

    /**
     * Scroll the deck list so that it is centered on the current deck.
     *
     * @param did The deck ID of the deck to select.
     */
    private void scrollDecklistToDeck(long did) {
        int position = mDeckListAdapter.findDeckPosition(did);
        if (!ListenerUtil.mutListener.listen(8020)) {
            mRecyclerViewLayoutManager.scrollToPositionWithOffset(position, ((ListenerUtil.mutListener.listen(8019) ? (mRecyclerView.getHeight() % 2) : (ListenerUtil.mutListener.listen(8018) ? (mRecyclerView.getHeight() * 2) : (ListenerUtil.mutListener.listen(8017) ? (mRecyclerView.getHeight() - 2) : (ListenerUtil.mutListener.listen(8016) ? (mRecyclerView.getHeight() + 2) : (mRecyclerView.getHeight() / 2)))))));
        }
    }

    private <T extends AbstractDeckTreeNode<T>> UpdateDeckListListener<T> updateDeckListListener() {
        return new UpdateDeckListListener<T>(this);
    }

    private static class UpdateDeckListListener<T extends AbstractDeckTreeNode<T>> extends TaskListenerWithContext<DeckPicker, Void, List<T>> {

        public UpdateDeckListListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(8022)) {
                if (!deckPicker.colIsOpen()) {
                    if (!ListenerUtil.mutListener.listen(8021)) {
                        deckPicker.showProgressBar();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8023)) {
                Timber.d("Refreshing deck list");
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, List<T> dueTree) {
            if (!ListenerUtil.mutListener.listen(8024)) {
                Timber.i("Updating deck list UI");
            }
            if (!ListenerUtil.mutListener.listen(8025)) {
                deckPicker.hideProgressBar();
            }
            if (!ListenerUtil.mutListener.listen(8027)) {
                // Make sure the fragment is visible
                if (deckPicker.mFragmented) {
                    if (!ListenerUtil.mutListener.listen(8026)) {
                        deckPicker.mStudyoptionsFrame.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8030)) {
                if (dueTree == null) {
                    if (!ListenerUtil.mutListener.listen(8028)) {
                        Timber.e("null result loading deck counts");
                    }
                    if (!ListenerUtil.mutListener.listen(8029)) {
                        deckPicker.showCollectionErrorDialog();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(8031)) {
                deckPicker.mDueTree = dueTree;
            }
            if (!ListenerUtil.mutListener.listen(8032)) {
                deckPicker.__renderPage();
            }
            if (!ListenerUtil.mutListener.listen(8033)) {
                // Update the mini statistics bar as well
                AnkiStatsTaskHandler.createReviewSummaryStatistics(deckPicker.getCol(), deckPicker.mReviewSummaryTextView);
            }
            if (!ListenerUtil.mutListener.listen(8034)) {
                Timber.d("Startup - Deck List UI Completed");
            }
        }
    }

    /**
     * Launch an asynchronous task to rebuild the deck list and recalculate the deck counts. Use this
     * after any change to a deck (e.g., rename, importing, add/delete) that needs to be reflected
     * in the deck list.
     *
     * This method also triggers an update for the widget to reflect the newly calculated counts.
     */
    private void updateDeckList() {
        if (!ListenerUtil.mutListener.listen(8035)) {
            updateDeckList(false);
        }
    }

    private void updateDeckList(boolean quick) {
        if (!ListenerUtil.mutListener.listen(8038)) {
            if (quick) {
                if (!ListenerUtil.mutListener.listen(8037)) {
                    TaskManager.launchCollectionTask(new CollectionTask.LoadDeck(), updateDeckListListener());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8036)) {
                    TaskManager.launchCollectionTask(new CollectionTask.LoadDeckCounts(), updateDeckListListener());
                }
            }
        }
    }

    public void __renderPage() {
        if (!ListenerUtil.mutListener.listen(8040)) {
            if (mDueTree == null) {
                if (!ListenerUtil.mutListener.listen(8039)) {
                    // We may need to recompute it.
                    updateDeckList();
                }
                return;
            }
        }
        // Check if default deck is the only available and there are no cards
        boolean isEmpty = (ListenerUtil.mutListener.listen(8047) ? ((ListenerUtil.mutListener.listen(8046) ? ((ListenerUtil.mutListener.listen(8045) ? (mDueTree.size() >= 1) : (ListenerUtil.mutListener.listen(8044) ? (mDueTree.size() <= 1) : (ListenerUtil.mutListener.listen(8043) ? (mDueTree.size() > 1) : (ListenerUtil.mutListener.listen(8042) ? (mDueTree.size() < 1) : (ListenerUtil.mutListener.listen(8041) ? (mDueTree.size() != 1) : (mDueTree.size() == 1)))))) || mDueTree.get(0).getDid() == 1) : ((ListenerUtil.mutListener.listen(8045) ? (mDueTree.size() >= 1) : (ListenerUtil.mutListener.listen(8044) ? (mDueTree.size() <= 1) : (ListenerUtil.mutListener.listen(8043) ? (mDueTree.size() > 1) : (ListenerUtil.mutListener.listen(8042) ? (mDueTree.size() < 1) : (ListenerUtil.mutListener.listen(8041) ? (mDueTree.size() != 1) : (mDueTree.size() == 1)))))) && mDueTree.get(0).getDid() == 1)) || getCol().isEmpty()) : ((ListenerUtil.mutListener.listen(8046) ? ((ListenerUtil.mutListener.listen(8045) ? (mDueTree.size() >= 1) : (ListenerUtil.mutListener.listen(8044) ? (mDueTree.size() <= 1) : (ListenerUtil.mutListener.listen(8043) ? (mDueTree.size() > 1) : (ListenerUtil.mutListener.listen(8042) ? (mDueTree.size() < 1) : (ListenerUtil.mutListener.listen(8041) ? (mDueTree.size() != 1) : (mDueTree.size() == 1)))))) || mDueTree.get(0).getDid() == 1) : ((ListenerUtil.mutListener.listen(8045) ? (mDueTree.size() >= 1) : (ListenerUtil.mutListener.listen(8044) ? (mDueTree.size() <= 1) : (ListenerUtil.mutListener.listen(8043) ? (mDueTree.size() > 1) : (ListenerUtil.mutListener.listen(8042) ? (mDueTree.size() < 1) : (ListenerUtil.mutListener.listen(8041) ? (mDueTree.size() != 1) : (mDueTree.size() == 1)))))) && mDueTree.get(0).getDid() == 1)) && getCol().isEmpty()));
        if (!ListenerUtil.mutListener.listen(8067)) {
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(8065)) {
                    mDeckPickerContent.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8066)) {
                    mNoDecksPlaceholder.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
                }
            } else {
                float translation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
                boolean decksListShown = mDeckPickerContent.getVisibility() == View.VISIBLE;
                boolean placeholderShown = mNoDecksPlaceholder.getVisibility() == View.VISIBLE;
                if (!ListenerUtil.mutListener.listen(8064)) {
                    if (isEmpty) {
                        if (!ListenerUtil.mutListener.listen(8057)) {
                            if (decksListShown) {
                                if (!ListenerUtil.mutListener.listen(8056)) {
                                    fadeOut(mDeckPickerContent, mShortAnimDuration, translation);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8063)) {
                            if (!placeholderShown) {
                                if (!ListenerUtil.mutListener.listen(8062)) {
                                    fadeIn(mNoDecksPlaceholder, mShortAnimDuration, translation).setStartDelay(decksListShown ? (ListenerUtil.mutListener.listen(8061) ? (mShortAnimDuration % 2) : (ListenerUtil.mutListener.listen(8060) ? (mShortAnimDuration / 2) : (ListenerUtil.mutListener.listen(8059) ? (mShortAnimDuration - 2) : (ListenerUtil.mutListener.listen(8058) ? (mShortAnimDuration + 2) : (mShortAnimDuration * 2))))) : 0);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8053)) {
                            if (!decksListShown) {
                                if (!ListenerUtil.mutListener.listen(8052)) {
                                    fadeIn(mDeckPickerContent, mShortAnimDuration, translation).setStartDelay(placeholderShown ? (ListenerUtil.mutListener.listen(8051) ? (mShortAnimDuration % 2) : (ListenerUtil.mutListener.listen(8050) ? (mShortAnimDuration / 2) : (ListenerUtil.mutListener.listen(8049) ? (mShortAnimDuration - 2) : (ListenerUtil.mutListener.listen(8048) ? (mShortAnimDuration + 2) : (mShortAnimDuration * 2))))) : 0);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8055)) {
                            if (placeholderShown) {
                                if (!ListenerUtil.mutListener.listen(8054)) {
                                    fadeOut(mNoDecksPlaceholder, mShortAnimDuration, translation);
                                }
                            }
                        }
                    }
                }
            }
        }
        CharSequence currentFilter = mToolbarSearchView != null ? mToolbarSearchView.getQuery() : null;
        if (!ListenerUtil.mutListener.listen(8072)) {
            if (isEmpty) {
                if (!ListenerUtil.mutListener.listen(8069)) {
                    if (getSupportActionBar() != null) {
                        if (!ListenerUtil.mutListener.listen(8068)) {
                            getSupportActionBar().setSubtitle(null);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8071)) {
                    if (mToolbarSearchView != null) {
                        if (!ListenerUtil.mutListener.listen(8070)) {
                            mDeckListAdapter.getFilter().filter(currentFilter);
                        }
                    }
                }
                // We're done here
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8073)) {
            mDeckListAdapter.buildDeckList(mDueTree, getCol(), currentFilter);
        }
        // Set the "x due in y minutes" subtitle
        try {
            Integer eta = mDeckListAdapter.getEta();
            Integer due = mDeckListAdapter.getDue();
            Resources res = getResources();
            if (!ListenerUtil.mutListener.listen(8090)) {
                if (getCol().cardCount() != -1) {
                    String time = "-";
                    if (!ListenerUtil.mutListener.listen(8086)) {
                        if ((ListenerUtil.mutListener.listen(8080) ? ((ListenerUtil.mutListener.listen(8079) ? (eta >= -1) : (ListenerUtil.mutListener.listen(8078) ? (eta <= -1) : (ListenerUtil.mutListener.listen(8077) ? (eta > -1) : (ListenerUtil.mutListener.listen(8076) ? (eta < -1) : (ListenerUtil.mutListener.listen(8075) ? (eta == -1) : (eta != -1)))))) || eta != null) : ((ListenerUtil.mutListener.listen(8079) ? (eta >= -1) : (ListenerUtil.mutListener.listen(8078) ? (eta <= -1) : (ListenerUtil.mutListener.listen(8077) ? (eta > -1) : (ListenerUtil.mutListener.listen(8076) ? (eta < -1) : (ListenerUtil.mutListener.listen(8075) ? (eta == -1) : (eta != -1)))))) && eta != null))) {
                            if (!ListenerUtil.mutListener.listen(8085)) {
                                time = Utils.timeQuantityTopDeckPicker(AnkiDroidApp.getInstance(), (ListenerUtil.mutListener.listen(8084) ? (eta % 60) : (ListenerUtil.mutListener.listen(8083) ? (eta / 60) : (ListenerUtil.mutListener.listen(8082) ? (eta - 60) : (ListenerUtil.mutListener.listen(8081) ? (eta + 60) : (eta * 60))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8089)) {
                        if ((ListenerUtil.mutListener.listen(8087) ? (due != null || getSupportActionBar() != null) : (due != null && getSupportActionBar() != null))) {
                            if (!ListenerUtil.mutListener.listen(8088)) {
                                getSupportActionBar().setSubtitle(res.getQuantityString(R.plurals.deckpicker_title, due, due, time));
                            }
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(8074)) {
                Timber.e(e, "RuntimeException setting time remaining");
            }
        }
        long current = getCol().getDecks().current().optLong("id");
        if (!ListenerUtil.mutListener.listen(8098)) {
            if ((ListenerUtil.mutListener.listen(8095) ? (mFocusedDeck >= current) : (ListenerUtil.mutListener.listen(8094) ? (mFocusedDeck <= current) : (ListenerUtil.mutListener.listen(8093) ? (mFocusedDeck > current) : (ListenerUtil.mutListener.listen(8092) ? (mFocusedDeck < current) : (ListenerUtil.mutListener.listen(8091) ? (mFocusedDeck == current) : (mFocusedDeck != current))))))) {
                if (!ListenerUtil.mutListener.listen(8096)) {
                    scrollDecklistToDeck(current);
                }
                if (!ListenerUtil.mutListener.listen(8097)) {
                    mFocusedDeck = current;
                }
            }
        }
    }

    public static ViewPropertyAnimator fadeIn(View view, int duration) {
        return fadeIn(view, duration, 0);
    }

    public static ViewPropertyAnimator fadeIn(View view, int duration, float translation) {
        return fadeIn(view, duration, translation, () -> view.setVisibility(View.VISIBLE));
    }

    public static ViewPropertyAnimator fadeIn(View view, int duration, float translation, Runnable startAction) {
        if (!ListenerUtil.mutListener.listen(8099)) {
            view.setAlpha(0);
        }
        if (!ListenerUtil.mutListener.listen(8100)) {
            view.setTranslationY(translation);
        }
        return view.animate().alpha(1).translationY(0).setDuration(duration).withStartAction(startAction);
    }

    public static ViewPropertyAnimator fadeOut(View view, int duration) {
        return fadeOut(view, duration, 0);
    }

    public static ViewPropertyAnimator fadeOut(View view, int duration, float translation) {
        return fadeOut(view, duration, translation, () -> view.setVisibility(View.GONE));
    }

    public static ViewPropertyAnimator fadeOut(View view, int duration, float translation, Runnable endAction) {
        if (!ListenerUtil.mutListener.listen(8101)) {
            view.setAlpha(1);
        }
        if (!ListenerUtil.mutListener.listen(8102)) {
            view.setTranslationY(0);
        }
        return view.animate().alpha(0).translationY(translation).setDuration(duration).withEndAction(endAction);
    }

    // Callback to show study options for currently selected deck
    public void showContextMenuDeckOptions() {
        if (!ListenerUtil.mutListener.listen(8107)) {
            // open deck options
            if (getCol().getDecks().isDyn(mContextMenuDid)) {
                // open cram options if filtered deck
                Intent i = new Intent(DeckPicker.this, FilteredDeckOptions.class);
                if (!ListenerUtil.mutListener.listen(8105)) {
                    i.putExtra("did", mContextMenuDid);
                }
                if (!ListenerUtil.mutListener.listen(8106)) {
                    startActivityWithAnimation(i, FADE);
                }
            } else {
                // otherwise open regular options
                Intent i = new Intent(DeckPicker.this, DeckOptions.class);
                if (!ListenerUtil.mutListener.listen(8103)) {
                    i.putExtra("did", mContextMenuDid);
                }
                if (!ListenerUtil.mutListener.listen(8104)) {
                    startActivityWithAnimation(i, FADE);
                }
            }
        }
    }

    // Callback to show export dialog for currently selected deck
    public void showContextMenuExportDialog() {
        if (!ListenerUtil.mutListener.listen(8108)) {
            exportDeck(mContextMenuDid);
        }
    }

    public void exportDeck(long did) {
        String msg = getResources().getString(R.string.confirm_apkg_export_deck, getCol().getDecks().get(did).getString("name"));
        if (!ListenerUtil.mutListener.listen(8109)) {
            showDialogFragment(ExportDialog.newInstance(msg, did));
        }
    }

    public void createIcon(Context context) {
        // This code should not be reachable with lower versions
        ShortcutInfoCompat shortcut = new ShortcutInfoCompat.Builder(this, Long.toString(mContextMenuDid)).setIntent(new Intent(context, Reviewer.class).setAction(Intent.ACTION_VIEW).putExtra("deckId", mContextMenuDid)).setIcon(IconCompat.createWithResource(context, R.mipmap.ic_launcher)).setShortLabel(Decks.basename(getCol().getDecks().name(mContextMenuDid))).setLongLabel(getCol().getDecks().name(mContextMenuDid)).build();
        try {
            boolean success = ShortcutManagerCompat.requestPinShortcut(this, shortcut, null);
            if (!ListenerUtil.mutListener.listen(8112)) {
                // User report: "success" is true even if Vivo does not have permission
                if (AdaptionUtil.isVivo()) {
                    if (!ListenerUtil.mutListener.listen(8111)) {
                        UIUtils.showThemedToast(this, getString(R.string.create_shortcut_error_vivo), false);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8114)) {
                if (!success) {
                    if (!ListenerUtil.mutListener.listen(8113)) {
                        UIUtils.showThemedToast(this, getString(R.string.create_shortcut_failed), false);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8110)) {
                UIUtils.showThemedToast(this, getString(R.string.create_shortcut_error, e.getLocalizedMessage()), false);
            }
        }
    }

    // Callback to show dialog to rename the current deck
    public void renameDeckDialog() {
        if (!ListenerUtil.mutListener.listen(8115)) {
            renameDeckDialog(mContextMenuDid);
        }
    }

    public void renameDeckDialog(final long did) {
        final Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(8116)) {
            mDialogEditText = new FixedEditText(DeckPicker.this);
        }
        if (!ListenerUtil.mutListener.listen(8117)) {
            mDialogEditText.setSingleLine();
        }
        final String currentName = getCol().getDecks().name(did);
        if (!ListenerUtil.mutListener.listen(8118)) {
            mDialogEditText.setText(currentName);
        }
        if (!ListenerUtil.mutListener.listen(8119)) {
            mDialogEditText.setSelection(mDialogEditText.getText().length());
        }
        if (!ListenerUtil.mutListener.listen(8120)) {
            new MaterialDialog.Builder(DeckPicker.this).title(res.getString(R.string.rename_deck)).customView(mDialogEditText, true).positiveText(R.string.rename).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                String newName = mDialogEditText.getText().toString().replaceAll("\"", "");
                Collection col = getCol();
                if (!Decks.isValidDeckName(newName)) {
                    Timber.i("renameDeckDialog not renaming deck to invalid name '%s'", newName);
                    UIUtils.showThemedToast(this, getString(R.string.invalid_deck_name), false);
                } else if (!newName.equals(currentName)) {
                    try {
                        col.getDecks().rename(col.getDecks().get(did), newName);
                    } catch (DeckRenameException e) {
                        // We get a localized string from libanki to explain the error
                        UIUtils.showThemedToast(DeckPicker.this, e.getLocalizedMessage(res), false);
                    }
                }
                dismissAllDialogFragments();
                mDeckListAdapter.notifyDataSetChanged();
                updateDeckList();
                if (mFragmented) {
                    loadStudyOptionsFragment(false);
                }
            }).onNegative((dialog, which) -> dismissAllDialogFragments()).build().show();
        }
    }

    // Callback to show confirm deck deletion dialog before deleting currently selected deck
    public void confirmDeckDeletion() {
        if (!ListenerUtil.mutListener.listen(8121)) {
            confirmDeckDeletion(mContextMenuDid);
        }
    }

    public void confirmDeckDeletion(long did) {
        Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(8122)) {
            if (!colIsOpen()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8130)) {
            if ((ListenerUtil.mutListener.listen(8127) ? (did >= 1) : (ListenerUtil.mutListener.listen(8126) ? (did <= 1) : (ListenerUtil.mutListener.listen(8125) ? (did > 1) : (ListenerUtil.mutListener.listen(8124) ? (did < 1) : (ListenerUtil.mutListener.listen(8123) ? (did != 1) : (did == 1))))))) {
                if (!ListenerUtil.mutListener.listen(8128)) {
                    UIUtils.showSimpleSnackbar(this, R.string.delete_deck_default_deck, true);
                }
                if (!ListenerUtil.mutListener.listen(8129)) {
                    dismissAllDialogFragments();
                }
                return;
            }
        }
        // Get the number of cards contained in this deck and its subdecks
        TreeMap<String, Long> children = getCol().getDecks().children(did);
        long[] dids = new long[(ListenerUtil.mutListener.listen(8134) ? (children.size() % 1) : (ListenerUtil.mutListener.listen(8133) ? (children.size() / 1) : (ListenerUtil.mutListener.listen(8132) ? (children.size() * 1) : (ListenerUtil.mutListener.listen(8131) ? (children.size() - 1) : (children.size() + 1)))))];
        if (!ListenerUtil.mutListener.listen(8135)) {
            dids[0] = did;
        }
        int i = 1;
        if (!ListenerUtil.mutListener.listen(8137)) {
            {
                long _loopCounter133 = 0;
                for (Long l : children.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter133", ++_loopCounter133);
                    if (!ListenerUtil.mutListener.listen(8136)) {
                        dids[i++] = l;
                    }
                }
            }
        }
        String ids = Utils.ids2str(dids);
        int cnt = getCol().getDb().queryScalar("select count() from cards where did in " + ids + " or odid in " + ids);
        if (!ListenerUtil.mutListener.listen(8145)) {
            // Delete empty decks without warning
            if ((ListenerUtil.mutListener.listen(8142) ? (cnt >= 0) : (ListenerUtil.mutListener.listen(8141) ? (cnt <= 0) : (ListenerUtil.mutListener.listen(8140) ? (cnt > 0) : (ListenerUtil.mutListener.listen(8139) ? (cnt < 0) : (ListenerUtil.mutListener.listen(8138) ? (cnt != 0) : (cnt == 0))))))) {
                if (!ListenerUtil.mutListener.listen(8143)) {
                    deleteDeck(did);
                }
                if (!ListenerUtil.mutListener.listen(8144)) {
                    dismissAllDialogFragments();
                }
                return;
            }
        }
        // Otherwise we show a warning and require confirmation
        String msg;
        String deckName = "'" + getCol().getDecks().name(did) + "'";
        boolean isDyn = getCol().getDecks().isDyn(did);
        if (isDyn) {
            msg = res.getString(R.string.delete_cram_deck_message, deckName);
        } else {
            msg = res.getQuantityString(R.plurals.delete_deck_message, cnt, deckName, cnt);
        }
        if (!ListenerUtil.mutListener.listen(8146)) {
            showDialogFragment(DeckPickerConfirmDeleteDeckDialog.newInstance(msg));
        }
    }

    // Callback to delete currently selected deck
    public void deleteContextMenuDeck() {
        if (!ListenerUtil.mutListener.listen(8147)) {
            deleteDeck(mContextMenuDid);
        }
    }

    public void deleteDeck(final long did) {
        if (!ListenerUtil.mutListener.listen(8148)) {
            TaskManager.launchCollectionTask(new CollectionTask.DeleteDeck(did), deleteDeckListener(did));
        }
    }

    private DeleteDeckListener deleteDeckListener(long did) {
        return new DeleteDeckListener(did, this);
    }

    private static class DeleteDeckListener extends TaskListenerWithContext<DeckPicker, Void, int[]> {

        private final long did;

        // Flag to indicate if the deck being deleted is the current deck.
        private boolean removingCurrent;

        public DeleteDeckListener(long did, DeckPicker deckPicker) {
            super(deckPicker);
            this.did = did;
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(8149)) {
                deckPicker.mProgressDialog = StyledProgressDialog.show(deckPicker, "", deckPicker.getResources().getString(R.string.delete_deck), false);
            }
            if (!ListenerUtil.mutListener.listen(8151)) {
                if (did == deckPicker.getCol().getDecks().current().optLong("id")) {
                    if (!ListenerUtil.mutListener.listen(8150)) {
                        removingCurrent = true;
                    }
                }
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, @Nullable int[] v) {
            if (!ListenerUtil.mutListener.listen(8156)) {
                // new current deck. Otherwise we just update the list normally.
                if ((ListenerUtil.mutListener.listen(8152) ? (deckPicker.mFragmented || removingCurrent) : (deckPicker.mFragmented && removingCurrent))) {
                    if (!ListenerUtil.mutListener.listen(8154)) {
                        deckPicker.updateDeckList();
                    }
                    if (!ListenerUtil.mutListener.listen(8155)) {
                        deckPicker.openStudyOptions(false);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8153)) {
                        deckPicker.updateDeckList();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8160)) {
                if ((ListenerUtil.mutListener.listen(8157) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(8159)) {
                            deckPicker.mProgressDialog.dismiss();
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(8158)) {
                            Timber.e(e, "onPostExecute - Exception dismissing dialog");
                        }
                    }
                }
            }
        }
    }

    /**
     * Show progress bars and rebuild deck list on completion
     */
    private SimpleProgressListener simpleProgressListener() {
        return new SimpleProgressListener(this);
    }

    private static class SimpleProgressListener extends TaskListenerWithContext<DeckPicker, Void, int[]> {

        public SimpleProgressListener(DeckPicker deckPicker) {
            super(deckPicker);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(8161)) {
                deckPicker.showProgressBar();
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, int[] stats) {
            if (!ListenerUtil.mutListener.listen(8162)) {
                deckPicker.updateDeckList();
            }
            if (!ListenerUtil.mutListener.listen(8164)) {
                if (deckPicker.mFragmented) {
                    if (!ListenerUtil.mutListener.listen(8163)) {
                        deckPicker.loadStudyOptionsFragment(false);
                    }
                }
            }
        }
    }

    public void rebuildFiltered() {
        if (!ListenerUtil.mutListener.listen(8165)) {
            getCol().getDecks().select(mContextMenuDid);
        }
        if (!ListenerUtil.mutListener.listen(8166)) {
            TaskManager.launchCollectionTask(new CollectionTask.RebuildCram(), simpleProgressListener());
        }
    }

    public void emptyFiltered() {
        if (!ListenerUtil.mutListener.listen(8167)) {
            getCol().getDecks().select(mContextMenuDid);
        }
        if (!ListenerUtil.mutListener.listen(8168)) {
            TaskManager.launchCollectionTask(new CollectionTask.EmptyCram(), simpleProgressListener());
        }
    }

    @Override
    public void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(8170)) {
            if (!mFragmented) {
                Window window = getWindow();
                if (!ListenerUtil.mutListener.listen(8169)) {
                    window.setFormat(PixelFormat.RGBA_8888);
                }
            }
        }
    }

    @Override
    public void onRequireDeckListUpdate() {
        if (!ListenerUtil.mutListener.listen(8171)) {
            updateDeckList();
        }
    }

    private void openReviewer() {
        Intent reviewer = new Intent(this, Reviewer.class);
        if (!ListenerUtil.mutListener.listen(8172)) {
            startActivityForResultWithAnimation(reviewer, REQUEST_REVIEW, LEFT);
        }
    }

    @Override
    public void onCreateCustomStudySession() {
        if (!ListenerUtil.mutListener.listen(8173)) {
            updateDeckList();
        }
        if (!ListenerUtil.mutListener.listen(8174)) {
            openStudyOptions(false);
        }
    }

    @Override
    public void onExtendStudyLimits() {
        if (!ListenerUtil.mutListener.listen(8176)) {
            if (mFragmented) {
                if (!ListenerUtil.mutListener.listen(8175)) {
                    getFragment().refreshInterface(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8177)) {
            updateDeckList();
        }
    }

    public void handleEmptyCards() {
        if (!ListenerUtil.mutListener.listen(8178)) {
            mEmptyCardTask = TaskManager.launchCollectionTask(new CollectionTask.FindEmptyCards(), handlerEmptyCardListener());
        }
    }

    private HandleEmptyCardListener handlerEmptyCardListener() {
        return new HandleEmptyCardListener(this);
    }

    private static class HandleEmptyCardListener extends TaskListenerWithContext<DeckPicker, Integer, List<Long>> {

        private final int mNumberOfCards;

        private final int mOnePercent;

        private int mIncreaseSinceLastUpdate = 0;

        public HandleEmptyCardListener(DeckPicker deckPicker) {
            super(deckPicker);
            mNumberOfCards = deckPicker.getCol().cardCount();
            mOnePercent = (ListenerUtil.mutListener.listen(8182) ? (mNumberOfCards % 100) : (ListenerUtil.mutListener.listen(8181) ? (mNumberOfCards * 100) : (ListenerUtil.mutListener.listen(8180) ? (mNumberOfCards - 100) : (ListenerUtil.mutListener.listen(8179) ? (mNumberOfCards + 100) : (mNumberOfCards / 100)))));
        }

        private void confirmCancel(@NonNull DeckPicker deckPicker, @NonNull CollectionTask<?, ?, ?, ?> task) {
            if (!ListenerUtil.mutListener.listen(8183)) {
                new MaterialDialog.Builder(deckPicker).content(R.string.confirm_cancel).positiveText(deckPicker.getResources().getString(R.string.yes)).negativeText(deckPicker.getResources().getString(R.string.dialog_no)).onNegative((x, y) -> actualOnPreExecute(deckPicker)).onPositive((x, y) -> task.safeCancel()).show();
            }
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPicker deckPicker) {
            DialogInterface.OnCancelListener onCancel = (dialogInterface) -> {
                CollectionTask<?, ?, ?, ?> emptyCardTask = deckPicker.mEmptyCardTask;
                if (emptyCardTask != null) {
                    confirmCancel(deckPicker, emptyCardTask);
                }
            };
            if (!ListenerUtil.mutListener.listen(8184)) {
                deckPicker.mProgressDialog = new MaterialDialog.Builder(deckPicker).progress(false, mNumberOfCards).title(R.string.emtpy_cards_finding).cancelable(true).show();
            }
            if (!ListenerUtil.mutListener.listen(8185)) {
                deckPicker.mProgressDialog.setOnCancelListener(onCancel);
            }
            if (!ListenerUtil.mutListener.listen(8186)) {
                deckPicker.mProgressDialog.setCanceledOnTouchOutside(false);
            }
        }

        @Override
        public void actualOnProgressUpdate(@NonNull DeckPicker deckPicker, @NonNull Integer progress) {
            if (!ListenerUtil.mutListener.listen(8187)) {
                mIncreaseSinceLastUpdate += progress;
            }
            if (!ListenerUtil.mutListener.listen(8195)) {
                // Increase each time at least a percent of card has been processed since last update
                if ((ListenerUtil.mutListener.listen(8192) ? (mIncreaseSinceLastUpdate >= mOnePercent) : (ListenerUtil.mutListener.listen(8191) ? (mIncreaseSinceLastUpdate <= mOnePercent) : (ListenerUtil.mutListener.listen(8190) ? (mIncreaseSinceLastUpdate < mOnePercent) : (ListenerUtil.mutListener.listen(8189) ? (mIncreaseSinceLastUpdate != mOnePercent) : (ListenerUtil.mutListener.listen(8188) ? (mIncreaseSinceLastUpdate == mOnePercent) : (mIncreaseSinceLastUpdate > mOnePercent))))))) {
                    if (!ListenerUtil.mutListener.listen(8193)) {
                        deckPicker.mProgressDialog.incrementProgress(mIncreaseSinceLastUpdate);
                    }
                    if (!ListenerUtil.mutListener.listen(8194)) {
                        mIncreaseSinceLastUpdate = 0;
                    }
                }
            }
        }

        @Override
        public void actualOnCancelled(@NonNull DeckPicker deckPicker) {
            if (!ListenerUtil.mutListener.listen(8196)) {
                deckPicker.mEmptyCardTask = null;
            }
        }

        /**
         * @param deckPicker
         * @param cids Null if it is cancelled (in this case we should not have called this method) or a list of cids
         */
        @Override
        public void actualOnPostExecute(@NonNull DeckPicker deckPicker, @Nullable List<Long> cids) {
            if (!ListenerUtil.mutListener.listen(8197)) {
                deckPicker.mEmptyCardTask = null;
            }
            if (!ListenerUtil.mutListener.listen(8198)) {
                if (cids == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(8208)) {
                if ((ListenerUtil.mutListener.listen(8203) ? (cids.size() >= 0) : (ListenerUtil.mutListener.listen(8202) ? (cids.size() <= 0) : (ListenerUtil.mutListener.listen(8201) ? (cids.size() > 0) : (ListenerUtil.mutListener.listen(8200) ? (cids.size() < 0) : (ListenerUtil.mutListener.listen(8199) ? (cids.size() != 0) : (cids.size() == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(8207)) {
                        deckPicker.showSimpleMessageDialog(deckPicker.getResources().getString(R.string.empty_cards_none));
                    }
                } else {
                    String msg = String.format(deckPicker.getResources().getString(R.string.empty_cards_count), cids.size());
                    ConfirmationDialog dialog = new ConfirmationDialog();
                    if (!ListenerUtil.mutListener.listen(8204)) {
                        dialog.setArgs(msg);
                    }
                    Runnable confirm = () -> {
                        deckPicker.getCol().remCards(cids);
                        UIUtils.showSimpleSnackbar(deckPicker, String.format(deckPicker.getResources().getString(R.string.empty_cards_deleted), cids.size()), false);
                    };
                    if (!ListenerUtil.mutListener.listen(8205)) {
                        dialog.setConfirm(confirm);
                    }
                    if (!ListenerUtil.mutListener.listen(8206)) {
                        deckPicker.showDialogFragment(dialog);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8211)) {
                if ((ListenerUtil.mutListener.listen(8209) ? (deckPicker.mProgressDialog != null || deckPicker.mProgressDialog.isShowing()) : (deckPicker.mProgressDialog != null && deckPicker.mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(8210)) {
                        deckPicker.mProgressDialog.dismiss();
                    }
                }
            }
        }
    }

    public void createSubdeckDialog() {
        if (!ListenerUtil.mutListener.listen(8212)) {
            createSubDeckDialog(mContextMenuDid);
        }
    }

    private void createSubDeckDialog(long did) {
        final Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(8213)) {
            mDialogEditText = new FixedEditText(this);
        }
        if (!ListenerUtil.mutListener.listen(8214)) {
            mDialogEditText.setSingleLine();
        }
        if (!ListenerUtil.mutListener.listen(8215)) {
            mDialogEditText.setSelection(mDialogEditText.getText().length());
        }
        if (!ListenerUtil.mutListener.listen(8216)) {
            new MaterialDialog.Builder(DeckPicker.this).title(R.string.create_subdeck).customView(mDialogEditText, true).positiveText(R.string.dialog_ok).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                String textValue = mDialogEditText.getText().toString();
                String newName = getCol().getDecks().getSubdeckName(did, textValue);
                if (Decks.isValidDeckName(newName)) {
                    createNewDeck(newName);
                } else {
                    Timber.i("createSubDeckDialog - not creating invalid subdeck name '%s'", newName);
                    UIUtils.showThemedToast(this, getString(R.string.invalid_deck_name), false);
                }
                dismissAllDialogFragments();
                mDeckListAdapter.notifyDataSetChanged();
                updateDeckList();
                if (mFragmented) {
                    loadStudyOptionsFragment(false);
                }
            }).onNegative((dialog, which) -> dismissAllDialogFragments()).build().show();
        }
    }

    @VisibleForTesting
    class CheckDatabaseListener extends TaskListener<String, Pair<Boolean, Collection.CheckDatabaseResult>> {

        @Override
        public void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(8217)) {
                mProgressDialog = StyledProgressDialog.show(DeckPicker.this, AnkiDroidApp.getAppResources().getString(R.string.app_name), getResources().getString(R.string.check_db_message), false);
            }
        }

        @Override
        public void onPostExecute(Pair<Boolean, Collection.CheckDatabaseResult> result) {
            if (!ListenerUtil.mutListener.listen(8220)) {
                if ((ListenerUtil.mutListener.listen(8218) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(8219)) {
                        mProgressDialog.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8222)) {
                if (result == null) {
                    if (!ListenerUtil.mutListener.listen(8221)) {
                        handleDbError();
                    }
                    return;
                }
            }
            Collection.CheckDatabaseResult databaseResult = result.second;
            if (!ListenerUtil.mutListener.listen(8226)) {
                if (databaseResult == null) {
                    if (!ListenerUtil.mutListener.listen(8225)) {
                        if (result.first) {
                            if (!ListenerUtil.mutListener.listen(8224)) {
                                Timber.w("Expected result data, got nothing");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8223)) {
                                handleDbError();
                            }
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(8231)) {
                if ((ListenerUtil.mutListener.listen(8227) ? (!result.first && databaseResult.getFailed()) : (!result.first || databaseResult.getFailed()))) {
                    if (!ListenerUtil.mutListener.listen(8230)) {
                        if (databaseResult.getDatabaseLocked()) {
                            if (!ListenerUtil.mutListener.listen(8229)) {
                                handleDbLocked();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8228)) {
                                handleDbError();
                            }
                        }
                    }
                    return;
                }
            }
            int count = databaseResult.getCardsWithFixedHomeDeckCount();
            if (!ListenerUtil.mutListener.listen(8238)) {
                if ((ListenerUtil.mutListener.listen(8236) ? (count >= 0) : (ListenerUtil.mutListener.listen(8235) ? (count <= 0) : (ListenerUtil.mutListener.listen(8234) ? (count > 0) : (ListenerUtil.mutListener.listen(8233) ? (count < 0) : (ListenerUtil.mutListener.listen(8232) ? (count == 0) : (count != 0))))))) {
                    String message = getResources().getString(R.string.integrity_check_fixed_no_home_deck, count);
                    if (!ListenerUtil.mutListener.listen(8237)) {
                        UIUtils.showThemedToast(DeckPicker.this, message, false);
                    }
                }
            }
            String msg;
            long shrunkInMb = Math.round((ListenerUtil.mutListener.listen(8242) ? (databaseResult.getSizeChangeInKb() % 1024.0) : (ListenerUtil.mutListener.listen(8241) ? (databaseResult.getSizeChangeInKb() * 1024.0) : (ListenerUtil.mutListener.listen(8240) ? (databaseResult.getSizeChangeInKb() - 1024.0) : (ListenerUtil.mutListener.listen(8239) ? (databaseResult.getSizeChangeInKb() + 1024.0) : (databaseResult.getSizeChangeInKb() / 1024.0))))));
            if ((ListenerUtil.mutListener.listen(8247) ? (shrunkInMb >= 0.0) : (ListenerUtil.mutListener.listen(8246) ? (shrunkInMb <= 0.0) : (ListenerUtil.mutListener.listen(8245) ? (shrunkInMb < 0.0) : (ListenerUtil.mutListener.listen(8244) ? (shrunkInMb != 0.0) : (ListenerUtil.mutListener.listen(8243) ? (shrunkInMb == 0.0) : (shrunkInMb > 0.0))))))) {
                msg = getResources().getString(R.string.check_db_acknowledge_shrunk, (int) shrunkInMb);
            } else {
                msg = getResources().getString(R.string.check_db_acknowledge);
            }
            if (!ListenerUtil.mutListener.listen(8248)) {
                // Show result of database check and restart the app
                showSimpleMessageDialog(msg, true);
            }
        }

        @Override
        public void onProgressUpdate(String message) {
            if (!ListenerUtil.mutListener.listen(8249)) {
                mProgressDialog.setContent(message);
            }
        }
    }

    private enum DeckSelectionType {

        /**
         * Show study options if fragmented, otherwise, review
         */
        DEFAULT,
        /**
         * Always show study options (if the deck counts are clicked)
         */
        SHOW_STUDY_OPTIONS,
        /**
         * Always open reviewer (keyboard shortcut)
         */
        SKIP_STUDY_OPTIONS
    }
}
