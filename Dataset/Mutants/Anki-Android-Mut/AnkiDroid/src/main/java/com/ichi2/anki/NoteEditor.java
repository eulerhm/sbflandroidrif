/**
 * ************************************************************************************
 *                                                                                       *
 *  Copyright (c) 2012 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>                          *
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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.PopupMenu;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.dialogs.ConfirmationDialog;
import com.ichi2.anki.dialogs.DiscardChangesDialog;
import com.ichi2.anki.dialogs.IntegerDialog;
import com.ichi2.anki.dialogs.LocaleSelectionDialog;
import com.ichi2.anki.dialogs.TagsDialog;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.multimediacard.IMultimediaEditableNote;
import com.ichi2.anki.multimediacard.activity.MultimediaEditFieldActivity;
import com.ichi2.anki.multimediacard.fields.AudioClipField;
import com.ichi2.anki.multimediacard.fields.AudioRecordingField;
import com.ichi2.anki.multimediacard.fields.EFieldType;
import com.ichi2.anki.multimediacard.fields.IField;
import com.ichi2.anki.multimediacard.fields.ImageField;
import com.ichi2.anki.multimediacard.fields.TextField;
import com.ichi2.anki.multimediacard.impl.MultimediaEditableNote;
import com.ichi2.anki.noteeditor.FieldState;
import com.ichi2.anki.noteeditor.FieldState.FieldChangeType;
import com.ichi2.anki.noteeditor.CustomToolbarButton;
import com.ichi2.anki.noteeditor.Toolbar;
import com.ichi2.anki.receiver.SdCardReceiver;
import com.ichi2.anki.servicelayer.NoteService;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListenerWithContext;
import com.ichi2.async.TaskManager;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Models;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.Note.ClozeUtils;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.Deck;
import com.ichi2.themes.StyledProgressDialog;
import com.ichi2.themes.Themes;
import com.ichi2.anki.widgets.PopupMenuWithIcons;
import com.ichi2.utils.AdaptionUtil;
import com.ichi2.utils.ContentResolverUtil;
import com.ichi2.utils.DeckComparator;
import com.ichi2.utils.FileUtil;
import com.ichi2.utils.FunctionalInterfaces.Consumer;
import com.ichi2.utils.KeyUtils;
import com.ichi2.utils.MapUtil;
import com.ichi2.utils.NamedJSONComparator;
import com.ichi2.utils.NoteFieldDecorator;
import com.ichi2.utils.TextViewUtil;
import com.ichi2.widget.WidgetStatus;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;
import timber.log.Timber;
import static com.ichi2.compat.Compat.ACTION_PROCESS_TEXT;
import static com.ichi2.compat.Compat.EXTRA_PROCESS_TEXT;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import static com.ichi2.libanki.Models.NOT_FOUND_NOTE_TYPE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Allows the user to edit a note, for instance if there is a typo. A card is a presentation of a note, and has two
 * sides: a question and an answer. Any number of fields can appear on each side. When you add a note to Anki, cards
 * which show that note are generated. Some models generate one card, others generate more than one.
 *
 * @see <a href="http://ankisrs.net/docs/manual.html#cards">the Anki Desktop manual</a>
 */
public class NoteEditor extends AnkiActivity {

    // public static final String TARGET_LANGUAGE = "TARGET_LANGUAGE";
    public static final String SOURCE_TEXT = "SOURCE_TEXT";

    public static final String TARGET_TEXT = "TARGET_TEXT";

    public static final String EXTRA_CALLER = "CALLER";

    public static final String EXTRA_CARD_ID = "CARD_ID";

    public static final String EXTRA_CONTENTS = "CONTENTS";

    public static final String EXTRA_TAGS = "TAGS";

    public static final String EXTRA_ID = "ID";

    public static final String EXTRA_DID = "DECK_ID";

    private static final String ACTION_CREATE_FLASHCARD = "org.openintents.action.CREATE_FLASHCARD";

    private static final String ACTION_CREATE_FLASHCARD_SEND = "android.intent.action.SEND";

    // calling activity
    public static final int CALLER_NOCALLER = 0;

    public static final int CALLER_REVIEWER = 1;

    public static final int CALLER_STUDYOPTIONS = 2;

    public static final int CALLER_DECKPICKER = 3;

    public static final int CALLER_REVIEWER_ADD = 11;

    public static final int CALLER_CARDBROWSER_EDIT = 6;

    public static final int CALLER_CARDBROWSER_ADD = 7;

    public static final int CALLER_CARDEDITOR = 8;

    public static final int CALLER_CARDEDITOR_INTENT_ADD = 10;

    public static final int REQUEST_ADD = 0;

    public static final int REQUEST_MULTIMEDIA_EDIT = 2;

    public static final int REQUEST_TEMPLATE_EDIT = 3;

    public static final int REQUEST_PREVIEW = 4;

    /**
     * Whether any change are saved. E.g. multimedia, new card added, field changed and saved.
     */
    private boolean mChanged = false;

    private boolean mTagsEdited = false;

    private boolean mFieldEdited = false;

    /**
     * Flag which forces the calling activity to rebuild it's definition of current card from scratch
     */
    private boolean mReloadRequired = false;

    /**
     * Broadcast that informs us when the sd card is about to be unmounted
     */
    private BroadcastReceiver mUnmountReceiver = null;

    private LinearLayout mFieldsLayoutContainer;

    private TextView mTagsButton;

    private TextView mCardsButton;

    private Spinner mNoteTypeSpinner;

    private Spinner mNoteDeckSpinner;

    private Note mEditorNote;

    @Nullable
    private Card /* Null if adding a new card. Presently NonNull if editing an existing note - but this is subject to change */
    mCurrentEditedCard;

    private ArrayList<String> mSelectedTags;

    private long mCurrentDid;

    private ArrayList<Long> mAllDeckIds;

    private ArrayList<Long> mAllModelIds;

    private Map<Integer, Integer> mModelChangeFieldMap;

    private HashMap<Integer, Integer> mModelChangeCardMap;

    private ArrayList<Integer> mCustomViewIds = new ArrayList<>();

    /* indicates if a new note is added or a card is edited */
    private boolean mAddNote;

    private boolean mAedictIntent;

    /* indicates which activity called Note Editor */
    private int mCaller;

    private LinkedList<FieldEditText> mEditFields;

    private MaterialDialog mProgressDialog;

    private String[] mSourceText;

    private FieldState mFieldState = FieldState.fromEditor(this);

    private Toolbar mToolbar;

    // Use the same HTML if the same image is pasted multiple times.
    private HashMap<String, String> mPastedImageCache = new HashMap<>();

    private SaveNoteHandler saveNoteHandler() {
        return new SaveNoteHandler(this);
    }

    private enum AddClozeType {

        SAME_NUMBER, INCREMENT_NUMBER
    }

    private static class SaveNoteHandler extends TaskListenerWithContext<NoteEditor, Integer, Boolean> {

        private boolean mCloseAfter = false;

        private Intent mIntent;

        private SaveNoteHandler(NoteEditor noteEditor) {
            super(noteEditor);
        }

        @Override
        public void actualOnPreExecute(@NonNull NoteEditor noteEditor) {
            Resources res = noteEditor.getResources();
            if (!ListenerUtil.mutListener.listen(9337)) {
                noteEditor.mProgressDialog = StyledProgressDialog.show(noteEditor, "", res.getString(R.string.saving_facts), false);
            }
        }

        @Override
        public void actualOnProgressUpdate(@NonNull NoteEditor noteEditor, Integer count) {
            if (!ListenerUtil.mutListener.listen(9348)) {
                if ((ListenerUtil.mutListener.listen(9342) ? (count >= 0) : (ListenerUtil.mutListener.listen(9341) ? (count <= 0) : (ListenerUtil.mutListener.listen(9340) ? (count < 0) : (ListenerUtil.mutListener.listen(9339) ? (count != 0) : (ListenerUtil.mutListener.listen(9338) ? (count == 0) : (count > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(9344)) {
                        noteEditor.mChanged = true;
                    }
                    if (!ListenerUtil.mutListener.listen(9345)) {
                        noteEditor.mSourceText = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9346)) {
                        noteEditor.refreshNoteData(FieldChangeType.refreshWithStickyFields(shouldReplaceNewlines()));
                    }
                    if (!ListenerUtil.mutListener.listen(9347)) {
                        UIUtils.showThemedToast(noteEditor, noteEditor.getResources().getQuantityString(R.plurals.factadder_cards_added, count, count), true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9343)) {
                        noteEditor.displayErrorSavingNote();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9374)) {
                if ((ListenerUtil.mutListener.listen(9355) ? ((ListenerUtil.mutListener.listen(9354) ? (!noteEditor.mAddNote && (ListenerUtil.mutListener.listen(9353) ? (noteEditor.mCaller >= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9352) ? (noteEditor.mCaller <= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9351) ? (noteEditor.mCaller > CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9350) ? (noteEditor.mCaller < CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9349) ? (noteEditor.mCaller != CALLER_CARDEDITOR) : (noteEditor.mCaller == CALLER_CARDEDITOR))))))) : (!noteEditor.mAddNote || (ListenerUtil.mutListener.listen(9353) ? (noteEditor.mCaller >= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9352) ? (noteEditor.mCaller <= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9351) ? (noteEditor.mCaller > CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9350) ? (noteEditor.mCaller < CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9349) ? (noteEditor.mCaller != CALLER_CARDEDITOR) : (noteEditor.mCaller == CALLER_CARDEDITOR)))))))) && noteEditor.mAedictIntent) : ((ListenerUtil.mutListener.listen(9354) ? (!noteEditor.mAddNote && (ListenerUtil.mutListener.listen(9353) ? (noteEditor.mCaller >= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9352) ? (noteEditor.mCaller <= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9351) ? (noteEditor.mCaller > CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9350) ? (noteEditor.mCaller < CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9349) ? (noteEditor.mCaller != CALLER_CARDEDITOR) : (noteEditor.mCaller == CALLER_CARDEDITOR))))))) : (!noteEditor.mAddNote || (ListenerUtil.mutListener.listen(9353) ? (noteEditor.mCaller >= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9352) ? (noteEditor.mCaller <= CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9351) ? (noteEditor.mCaller > CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9350) ? (noteEditor.mCaller < CALLER_CARDEDITOR) : (ListenerUtil.mutListener.listen(9349) ? (noteEditor.mCaller != CALLER_CARDEDITOR) : (noteEditor.mCaller == CALLER_CARDEDITOR)))))))) || noteEditor.mAedictIntent))) {
                    if (!ListenerUtil.mutListener.listen(9372)) {
                        noteEditor.mChanged = true;
                    }
                    if (!ListenerUtil.mutListener.listen(9373)) {
                        mCloseAfter = true;
                    }
                } else if ((ListenerUtil.mutListener.listen(9360) ? (noteEditor.mCaller >= CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9359) ? (noteEditor.mCaller <= CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9358) ? (noteEditor.mCaller > CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9357) ? (noteEditor.mCaller < CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9356) ? (noteEditor.mCaller != CALLER_CARDEDITOR_INTENT_ADD) : (noteEditor.mCaller == CALLER_CARDEDITOR_INTENT_ADD))))))) {
                    if (!ListenerUtil.mutListener.listen(9368)) {
                        if ((ListenerUtil.mutListener.listen(9366) ? (count >= 0) : (ListenerUtil.mutListener.listen(9365) ? (count <= 0) : (ListenerUtil.mutListener.listen(9364) ? (count < 0) : (ListenerUtil.mutListener.listen(9363) ? (count != 0) : (ListenerUtil.mutListener.listen(9362) ? (count == 0) : (count > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(9367)) {
                                noteEditor.mChanged = true;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9369)) {
                        mCloseAfter = true;
                    }
                    if (!ListenerUtil.mutListener.listen(9370)) {
                        mIntent = new Intent();
                    }
                    if (!ListenerUtil.mutListener.listen(9371)) {
                        mIntent.putExtra(EXTRA_ID, noteEditor.getIntent().getStringExtra(EXTRA_ID));
                    }
                } else if (!noteEditor.mEditFields.isEmpty()) {
                    FieldEditText firstEditField = noteEditor.mEditFields.getFirst();
                    if (!ListenerUtil.mutListener.listen(9361)) {
                        // Required on my Android 9 Phone to show keyboard: https://stackoverflow.com/a/7784904
                        firstEditField.postDelayed(() -> {
                            firstEditField.requestFocus();
                            InputMethodManager imm = (InputMethodManager) noteEditor.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(firstEditField, InputMethodManager.SHOW_IMPLICIT);
                        }, 200);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9379)) {
                if ((ListenerUtil.mutListener.listen(9376) ? ((ListenerUtil.mutListener.listen(9375) ? (!mCloseAfter || (noteEditor.mProgressDialog != null)) : (!mCloseAfter && (noteEditor.mProgressDialog != null))) || noteEditor.mProgressDialog.isShowing()) : ((ListenerUtil.mutListener.listen(9375) ? (!mCloseAfter || (noteEditor.mProgressDialog != null)) : (!mCloseAfter && (noteEditor.mProgressDialog != null))) && noteEditor.mProgressDialog.isShowing()))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9378)) {
                            noteEditor.mProgressDialog.dismiss();
                        }
                    } catch (IllegalArgumentException e) {
                        if (!ListenerUtil.mutListener.listen(9377)) {
                            Timber.e(e, "Note Editor: Error on dismissing progress dialog");
                        }
                    }
                }
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull NoteEditor noteEditor, Boolean noException) {
            if (!ListenerUtil.mutListener.listen(9391)) {
                if (noException) {
                    if (!ListenerUtil.mutListener.listen(9384)) {
                        if ((ListenerUtil.mutListener.listen(9381) ? (noteEditor.mProgressDialog != null || noteEditor.mProgressDialog.isShowing()) : (noteEditor.mProgressDialog != null && noteEditor.mProgressDialog.isShowing()))) {
                            try {
                                if (!ListenerUtil.mutListener.listen(9383)) {
                                    noteEditor.mProgressDialog.dismiss();
                                }
                            } catch (IllegalArgumentException e) {
                                if (!ListenerUtil.mutListener.listen(9382)) {
                                    Timber.e(e, "Note Editor: Error on dismissing progress dialog");
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9390)) {
                        if (mCloseAfter) {
                            if (!ListenerUtil.mutListener.listen(9389)) {
                                if (mIntent != null) {
                                    if (!ListenerUtil.mutListener.listen(9388)) {
                                        noteEditor.closeNoteEditor(mIntent);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9387)) {
                                        noteEditor.closeNoteEditor();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9385)) {
                                // Reset check for changes to fields
                                noteEditor.mFieldEdited = false;
                            }
                            if (!ListenerUtil.mutListener.listen(9386)) {
                                noteEditor.mTagsEdited = false;
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9380)) {
                        // RuntimeException occurred on adding note
                        noteEditor.closeNoteEditor(DeckPicker.RESULT_DB_ERROR, null);
                    }
                }
            }
        }
    }

    private void displayErrorSavingNote() {
        int errorMessageId = getAddNoteErrorResource();
        if (!ListenerUtil.mutListener.listen(9392)) {
            UIUtils.showThemedToast(this, getResources().getString(errorMessageId), false);
        }
    }

    @StringRes
    protected int getAddNoteErrorResource() {
        if (!ListenerUtil.mutListener.listen(9393)) {
            // COULD_BE_BETTER: We currently don't perform edits inside this class (wat), so we only handle adds.
            if (this.isClozeType()) {
                return R.string.note_editor_no_cloze_delations;
            }
        }
        if (!ListenerUtil.mutListener.listen(9394)) {
            if (TextUtils.isEmpty(getCurrentFieldText(0))) {
                return R.string.note_editor_no_first_field;
            }
        }
        if (!ListenerUtil.mutListener.listen(9395)) {
            if (allFieldsHaveContent()) {
                return R.string.note_editor_no_cards_created_all_fields;
            }
        }
        // Otherwise, display "no cards created".
        return R.string.note_editor_no_cards_created;
    }

    private boolean allFieldsHaveContent() {
        if (!ListenerUtil.mutListener.listen(9397)) {
            {
                long _loopCounter148 = 0;
                for (String s : this.getCurrentFieldStrings()) {
                    ListenerUtil.loopListener.listen("_loopCounter148", ++_loopCounter148);
                    if (!ListenerUtil.mutListener.listen(9396)) {
                        if (TextUtils.isEmpty(s)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9398)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9399)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(9400)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9401)) {
            mFieldState.setInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9402)) {
            setContentView(R.layout.note_editor);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(9421)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(9414)) {
                    mCaller = savedInstanceState.getInt("caller");
                }
                if (!ListenerUtil.mutListener.listen(9415)) {
                    mAddNote = savedInstanceState.getBoolean("addNote");
                }
                if (!ListenerUtil.mutListener.listen(9416)) {
                    mCurrentDid = savedInstanceState.getLong("did");
                }
                if (!ListenerUtil.mutListener.listen(9417)) {
                    mSelectedTags = savedInstanceState.getStringArrayList("tags");
                }
                if (!ListenerUtil.mutListener.listen(9418)) {
                    mReloadRequired = savedInstanceState.getBoolean("reloadRequired");
                }
                if (!ListenerUtil.mutListener.listen(9419)) {
                    mPastedImageCache = (HashMap<String, String>) savedInstanceState.getSerializable("imageCache");
                }
                if (!ListenerUtil.mutListener.listen(9420)) {
                    mChanged = savedInstanceState.getBoolean("changed");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9403)) {
                    mCaller = intent.getIntExtra(EXTRA_CALLER, CALLER_NOCALLER);
                }
                if (!ListenerUtil.mutListener.listen(9413)) {
                    if ((ListenerUtil.mutListener.listen(9408) ? (mCaller >= CALLER_NOCALLER) : (ListenerUtil.mutListener.listen(9407) ? (mCaller <= CALLER_NOCALLER) : (ListenerUtil.mutListener.listen(9406) ? (mCaller > CALLER_NOCALLER) : (ListenerUtil.mutListener.listen(9405) ? (mCaller < CALLER_NOCALLER) : (ListenerUtil.mutListener.listen(9404) ? (mCaller != CALLER_NOCALLER) : (mCaller == CALLER_NOCALLER))))))) {
                        String action = intent.getAction();
                        if (!ListenerUtil.mutListener.listen(9412)) {
                            if (((ListenerUtil.mutListener.listen(9410) ? ((ListenerUtil.mutListener.listen(9409) ? (ACTION_CREATE_FLASHCARD.equals(action) && ACTION_CREATE_FLASHCARD_SEND.equals(action)) : (ACTION_CREATE_FLASHCARD.equals(action) || ACTION_CREATE_FLASHCARD_SEND.equals(action))) && ACTION_PROCESS_TEXT.equals(action)) : ((ListenerUtil.mutListener.listen(9409) ? (ACTION_CREATE_FLASHCARD.equals(action) && ACTION_CREATE_FLASHCARD_SEND.equals(action)) : (ACTION_CREATE_FLASHCARD.equals(action) || ACTION_CREATE_FLASHCARD_SEND.equals(action))) || ACTION_PROCESS_TEXT.equals(action))))) {
                                if (!ListenerUtil.mutListener.listen(9411)) {
                                    mCaller = CALLER_CARDEDITOR_INTENT_ADD;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9422)) {
            startLoadingCollection();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9423)) {
            addInstanceStateToBundle(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9424)) {
            super.onSaveInstanceState(savedInstanceState);
        }
    }

    private void addInstanceStateToBundle(@NonNull Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9425)) {
            Timber.i("Saving instance");
        }
        if (!ListenerUtil.mutListener.listen(9426)) {
            savedInstanceState.putInt("caller", mCaller);
        }
        if (!ListenerUtil.mutListener.listen(9427)) {
            savedInstanceState.putBoolean("addNote", mAddNote);
        }
        if (!ListenerUtil.mutListener.listen(9428)) {
            savedInstanceState.putLong("did", mCurrentDid);
        }
        if (!ListenerUtil.mutListener.listen(9429)) {
            savedInstanceState.putBoolean("changed", mChanged);
        }
        if (!ListenerUtil.mutListener.listen(9430)) {
            savedInstanceState.putBoolean("reloadRequired", mReloadRequired);
        }
        if (!ListenerUtil.mutListener.listen(9431)) {
            savedInstanceState.putIntegerArrayList("customViewIds", mCustomViewIds);
        }
        if (!ListenerUtil.mutListener.listen(9432)) {
            savedInstanceState.putSerializable("imageCache", mPastedImageCache);
        }
        if (!ListenerUtil.mutListener.listen(9434)) {
            if (mSelectedTags == null) {
                if (!ListenerUtil.mutListener.listen(9433)) {
                    mSelectedTags = new ArrayList<>(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9435)) {
            savedInstanceState.putStringArrayList("tags", mSelectedTags);
        }
    }

    /**
     * Converts field values should to HTML linebreaks
     */
    private Bundle getFieldsAsBundleForPreview() {
        Bundle fields = new Bundle();
        if (!ListenerUtil.mutListener.listen(9437)) {
            // easily map the fields correctly later.
            if (mEditFields == null) {
                if (!ListenerUtil.mutListener.listen(9436)) {
                    // DA - I don't believe that this is required. Needs testing
                    mEditFields = new LinkedList<>();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9441)) {
            {
                long _loopCounter149 = 0;
                for (FieldEditText e : mEditFields) {
                    ListenerUtil.loopListener.listen("_loopCounter149", ++_loopCounter149);
                    if (!ListenerUtil.mutListener.listen(9439)) {
                        if ((ListenerUtil.mutListener.listen(9438) ? (e == null && e.getText() == null) : (e == null || e.getText() == null))) {
                            continue;
                        }
                    }
                    String fieldValue = convertToHtmlNewline(e.getText().toString());
                    if (!ListenerUtil.mutListener.listen(9440)) {
                        fields.putString(Integer.toString(e.getOrd()), fieldValue);
                    }
                }
            }
        }
        return fields;
    }

    // Finish initializing the activity after the collection has been correctly loaded
    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(9442)) {
            super.onCollectionLoaded(col);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(9443)) {
            Timber.d("NoteEditor() onCollectionLoaded: caller: %d", mCaller);
        }
        if (!ListenerUtil.mutListener.listen(9444)) {
            registerExternalStorageListener();
        }
        View mainView = findViewById(android.R.id.content);
        if (!ListenerUtil.mutListener.listen(9445)) {
            mToolbar = findViewById(R.id.editor_toolbar);
        }
        if (!ListenerUtil.mutListener.listen(9446)) {
            mToolbar.setFormatListener(formatter -> {
                View currentFocus = getCurrentFocus();
                if (!(currentFocus instanceof FieldEditText)) {
                    return;
                }
                modifyCurrentSelection(formatter, (FieldEditText) currentFocus);
            });
        }
        if (!ListenerUtil.mutListener.listen(9447)) {
            enableToolbar(mainView);
        }
        if (!ListenerUtil.mutListener.listen(9448)) {
            mFieldsLayoutContainer = findViewById(R.id.CardEditorEditFieldsLayout);
        }
        if (!ListenerUtil.mutListener.listen(9449)) {
            mTagsButton = findViewById(R.id.CardEditorTagText);
        }
        if (!ListenerUtil.mutListener.listen(9450)) {
            mCardsButton = findViewById(R.id.CardEditorCardsText);
        }
        if (!ListenerUtil.mutListener.listen(9451)) {
            mCardsButton.setOnClickListener(v -> {
                Timber.i("NoteEditor:: Cards button pressed. Opening template editor");
                showCardTemplateEditor();
            });
        }
        if (!ListenerUtil.mutListener.listen(9452)) {
            mAedictIntent = false;
        }
        if (!ListenerUtil.mutListener.listen(9453)) {
            mCurrentEditedCard = null;
        }
        if (!ListenerUtil.mutListener.listen(9474)) {
            switch(mCaller) {
                case CALLER_NOCALLER:
                    if (!ListenerUtil.mutListener.listen(9454)) {
                        Timber.e("no caller could be identified, closing");
                    }
                    if (!ListenerUtil.mutListener.listen(9455)) {
                        finishWithoutAnimation();
                    }
                    return;
                case CALLER_REVIEWER:
                    if (!ListenerUtil.mutListener.listen(9456)) {
                        mCurrentEditedCard = AbstractFlashcardViewer.getEditorCard();
                    }
                    if (!ListenerUtil.mutListener.listen(9458)) {
                        if (mCurrentEditedCard == null) {
                            if (!ListenerUtil.mutListener.listen(9457)) {
                                finishWithoutAnimation();
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9459)) {
                        mEditorNote = mCurrentEditedCard.note();
                    }
                    if (!ListenerUtil.mutListener.listen(9460)) {
                        mAddNote = false;
                    }
                    break;
                case CALLER_STUDYOPTIONS:
                case CALLER_DECKPICKER:
                case CALLER_REVIEWER_ADD:
                case CALLER_CARDBROWSER_ADD:
                case CALLER_CARDEDITOR:
                    if (!ListenerUtil.mutListener.listen(9461)) {
                        mAddNote = true;
                    }
                    break;
                case CALLER_CARDBROWSER_EDIT:
                    if (!ListenerUtil.mutListener.listen(9462)) {
                        mCurrentEditedCard = CardBrowser.sCardBrowserCard;
                    }
                    if (!ListenerUtil.mutListener.listen(9464)) {
                        if (mCurrentEditedCard == null) {
                            if (!ListenerUtil.mutListener.listen(9463)) {
                                finishWithoutAnimation();
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9465)) {
                        mEditorNote = mCurrentEditedCard.note();
                    }
                    if (!ListenerUtil.mutListener.listen(9466)) {
                        mAddNote = false;
                    }
                    break;
                case CALLER_CARDEDITOR_INTENT_ADD:
                    {
                        if (!ListenerUtil.mutListener.listen(9467)) {
                            fetchIntentInformation(intent);
                        }
                        if (!ListenerUtil.mutListener.listen(9469)) {
                            if (mSourceText == null) {
                                if (!ListenerUtil.mutListener.listen(9468)) {
                                    finishWithoutAnimation();
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9472)) {
                            if ((ListenerUtil.mutListener.listen(9470) ? ("Aedict Notepad".equals(mSourceText[0]) || addFromAedict(mSourceText[1])) : ("Aedict Notepad".equals(mSourceText[0]) && addFromAedict(mSourceText[1])))) {
                                if (!ListenerUtil.mutListener.listen(9471)) {
                                    finishWithoutAnimation();
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9473)) {
                            mAddNote = true;
                        }
                        break;
                    }
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(9475)) {
            // Note type Selector
            mNoteTypeSpinner = findViewById(R.id.note_type_spinner);
        }
        ArrayList<Model> models = getCol().getModels().all();
        if (!ListenerUtil.mutListener.listen(9476)) {
            Collections.sort(models, NamedJSONComparator.instance);
        }
        final ArrayList<String> modelNames = new ArrayList<>(models.size());
        if (!ListenerUtil.mutListener.listen(9477)) {
            mAllModelIds = new ArrayList<>(models.size());
        }
        if (!ListenerUtil.mutListener.listen(9480)) {
            {
                long _loopCounter150 = 0;
                for (JSONObject m : models) {
                    ListenerUtil.loopListener.listen("_loopCounter150", ++_loopCounter150);
                    if (!ListenerUtil.mutListener.listen(9478)) {
                        modelNames.add(m.getString("name"));
                    }
                    if (!ListenerUtil.mutListener.listen(9479)) {
                        mAllModelIds.add(m.getLong("id"));
                    }
                }
            }
        }
        ArrayAdapter<String> noteTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modelNames);
        if (!ListenerUtil.mutListener.listen(9481)) {
            mNoteTypeSpinner.setAdapter(noteTypeAdapter);
        }
        if (!ListenerUtil.mutListener.listen(9482)) {
            noteTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        // Deck Selector
        TextView deckTextView = findViewById(R.id.CardEditorDeckText);
        if (!ListenerUtil.mutListener.listen(9490)) {
            // If edit mode and more than one card template distinguish between "Deck" and "Card deck"
            if ((ListenerUtil.mutListener.listen(9488) ? (!mAddNote || (ListenerUtil.mutListener.listen(9487) ? (mEditorNote.model().getJSONArray("tmpls").length() >= 1) : (ListenerUtil.mutListener.listen(9486) ? (mEditorNote.model().getJSONArray("tmpls").length() <= 1) : (ListenerUtil.mutListener.listen(9485) ? (mEditorNote.model().getJSONArray("tmpls").length() < 1) : (ListenerUtil.mutListener.listen(9484) ? (mEditorNote.model().getJSONArray("tmpls").length() != 1) : (ListenerUtil.mutListener.listen(9483) ? (mEditorNote.model().getJSONArray("tmpls").length() == 1) : (mEditorNote.model().getJSONArray("tmpls").length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(9487) ? (mEditorNote.model().getJSONArray("tmpls").length() >= 1) : (ListenerUtil.mutListener.listen(9486) ? (mEditorNote.model().getJSONArray("tmpls").length() <= 1) : (ListenerUtil.mutListener.listen(9485) ? (mEditorNote.model().getJSONArray("tmpls").length() < 1) : (ListenerUtil.mutListener.listen(9484) ? (mEditorNote.model().getJSONArray("tmpls").length() != 1) : (ListenerUtil.mutListener.listen(9483) ? (mEditorNote.model().getJSONArray("tmpls").length() == 1) : (mEditorNote.model().getJSONArray("tmpls").length() > 1))))))))) {
                if (!ListenerUtil.mutListener.listen(9489)) {
                    deckTextView.setText(R.string.CardEditorCardDeck);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9491)) {
            mNoteDeckSpinner = findViewById(R.id.note_deck_spinner);
        }
        ArrayList<Deck> decks = getCol().getDecks().all();
        if (!ListenerUtil.mutListener.listen(9492)) {
            Collections.sort(decks, DeckComparator.instance);
        }
        final ArrayList<String> deckNames = new ArrayList<>(decks.size());
        if (!ListenerUtil.mutListener.listen(9493)) {
            mAllDeckIds = new ArrayList<>(decks.size());
        }
        if (!ListenerUtil.mutListener.listen(9506)) {
            {
                long _loopCounter151 = 0;
                for (Deck d : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter151", ++_loopCounter151);
                    // add current deck and all other non-filtered decks to deck list
                    long thisDid = d.getLong("id");
                    String currentName = d.getString("name");
                    String lineContent = null;
                    if (!ListenerUtil.mutListener.listen(9503)) {
                        if (d.isStd()) {
                            if (!ListenerUtil.mutListener.listen(9502)) {
                                lineContent = currentName;
                            }
                        } else if ((ListenerUtil.mutListener.listen(9500) ? ((ListenerUtil.mutListener.listen(9494) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null)) || (ListenerUtil.mutListener.listen(9499) ? (mCurrentEditedCard.getDid() >= thisDid) : (ListenerUtil.mutListener.listen(9498) ? (mCurrentEditedCard.getDid() <= thisDid) : (ListenerUtil.mutListener.listen(9497) ? (mCurrentEditedCard.getDid() > thisDid) : (ListenerUtil.mutListener.listen(9496) ? (mCurrentEditedCard.getDid() < thisDid) : (ListenerUtil.mutListener.listen(9495) ? (mCurrentEditedCard.getDid() != thisDid) : (mCurrentEditedCard.getDid() == thisDid))))))) : ((ListenerUtil.mutListener.listen(9494) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null)) && (ListenerUtil.mutListener.listen(9499) ? (mCurrentEditedCard.getDid() >= thisDid) : (ListenerUtil.mutListener.listen(9498) ? (mCurrentEditedCard.getDid() <= thisDid) : (ListenerUtil.mutListener.listen(9497) ? (mCurrentEditedCard.getDid() > thisDid) : (ListenerUtil.mutListener.listen(9496) ? (mCurrentEditedCard.getDid() < thisDid) : (ListenerUtil.mutListener.listen(9495) ? (mCurrentEditedCard.getDid() != thisDid) : (mCurrentEditedCard.getDid() == thisDid))))))))) {
                            if (!ListenerUtil.mutListener.listen(9501)) {
                                lineContent = getApplicationContext().getString(R.string.current_and_default_deck, currentName, col.getDecks().name(mCurrentEditedCard.getODid()));
                            }
                        } else {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9504)) {
                        deckNames.add(lineContent);
                    }
                    if (!ListenerUtil.mutListener.listen(9505)) {
                        mAllDeckIds.add(thisDid);
                    }
                }
            }
        }
        ArrayAdapter<String> noteDeckAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deckNames);
        if (!ListenerUtil.mutListener.listen(9507)) {
            mNoteDeckSpinner.setAdapter(noteDeckAdapter);
        }
        if (!ListenerUtil.mutListener.listen(9508)) {
            noteDeckAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(9510)) {
            mNoteDeckSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (!ListenerUtil.mutListener.listen(9509)) {
                        // Timber.i("NoteEditor:: onItemSelected() fired on mNoteDeckSpinner with pos = %d", pos);
                        mCurrentDid = mAllDeckIds.get(pos);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9511)) {
            mCurrentDid = intent.getLongExtra(EXTRA_DID, mCurrentDid);
        }
        if (!ListenerUtil.mutListener.listen(9512)) {
            setDid(mEditorNote);
        }
        if (!ListenerUtil.mutListener.listen(9513)) {
            setNote(mEditorNote, FieldChangeType.onActivityCreation(shouldReplaceNewlines()));
        }
        if (!ListenerUtil.mutListener.listen(9551)) {
            if (mAddNote) {
                if (!ListenerUtil.mutListener.listen(9516)) {
                    mNoteTypeSpinner.setOnItemSelectedListener(new SetNoteTypeListener());
                }
                if (!ListenerUtil.mutListener.listen(9517)) {
                    setTitle(R.string.menu_add_note);
                }
                // set information transferred by intent
                String contents = null;
                String[] tags = intent.getStringArrayExtra(EXTRA_TAGS);
                if (!ListenerUtil.mutListener.listen(9546)) {
                    if (mSourceText != null) {
                        if (!ListenerUtil.mutListener.listen(9545)) {
                            if ((ListenerUtil.mutListener.listen(9525) ? ((ListenerUtil.mutListener.listen(9524) ? (mAedictIntent || ((ListenerUtil.mutListener.listen(9523) ? (mEditFields.size() >= 3) : (ListenerUtil.mutListener.listen(9522) ? (mEditFields.size() <= 3) : (ListenerUtil.mutListener.listen(9521) ? (mEditFields.size() > 3) : (ListenerUtil.mutListener.listen(9520) ? (mEditFields.size() < 3) : (ListenerUtil.mutListener.listen(9519) ? (mEditFields.size() != 3) : (mEditFields.size() == 3)))))))) : (mAedictIntent && ((ListenerUtil.mutListener.listen(9523) ? (mEditFields.size() >= 3) : (ListenerUtil.mutListener.listen(9522) ? (mEditFields.size() <= 3) : (ListenerUtil.mutListener.listen(9521) ? (mEditFields.size() > 3) : (ListenerUtil.mutListener.listen(9520) ? (mEditFields.size() < 3) : (ListenerUtil.mutListener.listen(9519) ? (mEditFields.size() != 3) : (mEditFields.size() == 3))))))))) || mSourceText[1].contains("[")) : ((ListenerUtil.mutListener.listen(9524) ? (mAedictIntent || ((ListenerUtil.mutListener.listen(9523) ? (mEditFields.size() >= 3) : (ListenerUtil.mutListener.listen(9522) ? (mEditFields.size() <= 3) : (ListenerUtil.mutListener.listen(9521) ? (mEditFields.size() > 3) : (ListenerUtil.mutListener.listen(9520) ? (mEditFields.size() < 3) : (ListenerUtil.mutListener.listen(9519) ? (mEditFields.size() != 3) : (mEditFields.size() == 3)))))))) : (mAedictIntent && ((ListenerUtil.mutListener.listen(9523) ? (mEditFields.size() >= 3) : (ListenerUtil.mutListener.listen(9522) ? (mEditFields.size() <= 3) : (ListenerUtil.mutListener.listen(9521) ? (mEditFields.size() > 3) : (ListenerUtil.mutListener.listen(9520) ? (mEditFields.size() < 3) : (ListenerUtil.mutListener.listen(9519) ? (mEditFields.size() != 3) : (mEditFields.size() == 3))))))))) && mSourceText[1].contains("[")))) {
                                if (!ListenerUtil.mutListener.listen(9539)) {
                                    contents = mSourceText[1].replaceFirst("\\[", "\u001f" + mSourceText[0] + "\u001f");
                                }
                                if (!ListenerUtil.mutListener.listen(9544)) {
                                    contents = contents.substring(0, (ListenerUtil.mutListener.listen(9543) ? (contents.length() % 1) : (ListenerUtil.mutListener.listen(9542) ? (contents.length() / 1) : (ListenerUtil.mutListener.listen(9541) ? (contents.length() * 1) : (ListenerUtil.mutListener.listen(9540) ? (contents.length() + 1) : (contents.length() - 1))))));
                                }
                            } else if ((ListenerUtil.mutListener.listen(9530) ? (mEditFields.size() >= 0) : (ListenerUtil.mutListener.listen(9529) ? (mEditFields.size() <= 0) : (ListenerUtil.mutListener.listen(9528) ? (mEditFields.size() < 0) : (ListenerUtil.mutListener.listen(9527) ? (mEditFields.size() != 0) : (ListenerUtil.mutListener.listen(9526) ? (mEditFields.size() == 0) : (mEditFields.size() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(9531)) {
                                    mEditFields.get(0).setText(mSourceText[0]);
                                }
                                if (!ListenerUtil.mutListener.listen(9538)) {
                                    if ((ListenerUtil.mutListener.listen(9536) ? (mEditFields.size() >= 1) : (ListenerUtil.mutListener.listen(9535) ? (mEditFields.size() <= 1) : (ListenerUtil.mutListener.listen(9534) ? (mEditFields.size() < 1) : (ListenerUtil.mutListener.listen(9533) ? (mEditFields.size() != 1) : (ListenerUtil.mutListener.listen(9532) ? (mEditFields.size() == 1) : (mEditFields.size() > 1))))))) {
                                        if (!ListenerUtil.mutListener.listen(9537)) {
                                            mEditFields.get(1).setText(mSourceText[1]);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9518)) {
                            contents = intent.getStringExtra(EXTRA_CONTENTS);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9548)) {
                    if (contents != null) {
                        if (!ListenerUtil.mutListener.listen(9547)) {
                            setEditFieldTexts(contents);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9550)) {
                    if (tags != null) {
                        if (!ListenerUtil.mutListener.listen(9549)) {
                            setTags(tags);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9514)) {
                    mNoteTypeSpinner.setOnItemSelectedListener(new EditNoteTypeListener());
                }
                if (!ListenerUtil.mutListener.listen(9515)) {
                    setTitle(R.string.cardeditor_title_edit_card);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9552)) {
            findViewById(R.id.CardEditorTagButton).setOnClickListener(v -> {
                Timber.i("NoteEditor:: Tags button pressed... opening tags editor");
                showTagsDialog();
            });
        }
        if (!ListenerUtil.mutListener.listen(9555)) {
            if ((ListenerUtil.mutListener.listen(9553) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null))) {
                if (!ListenerUtil.mutListener.listen(9554)) {
                    Timber.i("onCollectionLoaded() Edit note activity successfully started with card id %d", mCurrentEditedCard.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9557)) {
            if (mAddNote) {
                if (!ListenerUtil.mutListener.listen(9556)) {
                    Timber.i("onCollectionLoaded() Edit note activity successfully started in add card mode with node id %d", mEditorNote.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9559)) {
            // don't open keyboard if not adding note
            if (!mAddNote) {
                if (!ListenerUtil.mutListener.listen(9558)) {
                    this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9562)) {
            // set focus to FieldEditText 'first' on startup like Anki desktop
            if ((ListenerUtil.mutListener.listen(9560) ? (mEditFields != null || !mEditFields.isEmpty()) : (mEditFields != null && !mEditFields.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(9561)) {
                    mEditFields.getFirst().requestFocus();
                }
            }
        }
    }

    private void modifyCurrentSelection(Toolbar.TextFormatter formatter, FieldEditText textBox) {
        // get the current text and selection locations
        int selectionStart = textBox.getSelectionStart();
        int selectionEnd = textBox.getSelectionEnd();
        // #6762 values are reversed if using a keyboard and pressing Ctrl+Shift+LeftArrow
        int start = Math.min(selectionStart, selectionEnd);
        int end = Math.max(selectionStart, selectionEnd);
        String text = "";
        if (!ListenerUtil.mutListener.listen(9564)) {
            if (textBox.getText() != null) {
                if (!ListenerUtil.mutListener.listen(9563)) {
                    text = textBox.getText().toString();
                }
            }
        }
        // Split the text in the places where the formatting will take place
        String beforeText = text.substring(0, start);
        String selectedText = text.substring(start, end);
        String afterText = text.substring(end);
        Toolbar.TextWrapper.StringFormat formatResult = formatter.format(selectedText);
        String newText = formatResult.result;
        // Update text field with updated text and selection
        int length = (ListenerUtil.mutListener.listen(9572) ? ((ListenerUtil.mutListener.listen(9568) ? (beforeText.length() % newText.length()) : (ListenerUtil.mutListener.listen(9567) ? (beforeText.length() / newText.length()) : (ListenerUtil.mutListener.listen(9566) ? (beforeText.length() * newText.length()) : (ListenerUtil.mutListener.listen(9565) ? (beforeText.length() - newText.length()) : (beforeText.length() + newText.length()))))) % afterText.length()) : (ListenerUtil.mutListener.listen(9571) ? ((ListenerUtil.mutListener.listen(9568) ? (beforeText.length() % newText.length()) : (ListenerUtil.mutListener.listen(9567) ? (beforeText.length() / newText.length()) : (ListenerUtil.mutListener.listen(9566) ? (beforeText.length() * newText.length()) : (ListenerUtil.mutListener.listen(9565) ? (beforeText.length() - newText.length()) : (beforeText.length() + newText.length()))))) / afterText.length()) : (ListenerUtil.mutListener.listen(9570) ? ((ListenerUtil.mutListener.listen(9568) ? (beforeText.length() % newText.length()) : (ListenerUtil.mutListener.listen(9567) ? (beforeText.length() / newText.length()) : (ListenerUtil.mutListener.listen(9566) ? (beforeText.length() * newText.length()) : (ListenerUtil.mutListener.listen(9565) ? (beforeText.length() - newText.length()) : (beforeText.length() + newText.length()))))) * afterText.length()) : (ListenerUtil.mutListener.listen(9569) ? ((ListenerUtil.mutListener.listen(9568) ? (beforeText.length() % newText.length()) : (ListenerUtil.mutListener.listen(9567) ? (beforeText.length() / newText.length()) : (ListenerUtil.mutListener.listen(9566) ? (beforeText.length() * newText.length()) : (ListenerUtil.mutListener.listen(9565) ? (beforeText.length() - newText.length()) : (beforeText.length() + newText.length()))))) - afterText.length()) : ((ListenerUtil.mutListener.listen(9568) ? (beforeText.length() % newText.length()) : (ListenerUtil.mutListener.listen(9567) ? (beforeText.length() / newText.length()) : (ListenerUtil.mutListener.listen(9566) ? (beforeText.length() * newText.length()) : (ListenerUtil.mutListener.listen(9565) ? (beforeText.length() - newText.length()) : (beforeText.length() + newText.length()))))) + afterText.length())))));
        StringBuilder newValue = new StringBuilder(length).append(beforeText).append(newText).append(afterText);
        if (!ListenerUtil.mutListener.listen(9573)) {
            textBox.setText(newValue);
        }
        int newStart = formatResult.start;
        int newEnd = formatResult.end;
        if (!ListenerUtil.mutListener.listen(9582)) {
            textBox.setSelection((ListenerUtil.mutListener.listen(9577) ? (start % newStart) : (ListenerUtil.mutListener.listen(9576) ? (start / newStart) : (ListenerUtil.mutListener.listen(9575) ? (start * newStart) : (ListenerUtil.mutListener.listen(9574) ? (start - newStart) : (start + newStart))))), (ListenerUtil.mutListener.listen(9581) ? (start % newEnd) : (ListenerUtil.mutListener.listen(9580) ? (start / newEnd) : (ListenerUtil.mutListener.listen(9579) ? (start * newEnd) : (ListenerUtil.mutListener.listen(9578) ? (start - newEnd) : (start + newEnd))))));
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(9583)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(9586)) {
            if (!isFinishing()) {
                if (!ListenerUtil.mutListener.listen(9584)) {
                    WidgetStatus.update(this);
                }
                if (!ListenerUtil.mutListener.listen(9585)) {
                    UIUtils.saveCollectionInBackground();
                }
            }
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(9588)) {
            if ((ListenerUtil.mutListener.listen(9587) ? (mToolbar != null || mToolbar.onKeyUp(keyCode, event)) : (mToolbar != null && mToolbar.onKeyUp(keyCode, event)))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(9610)) {
            switch(keyCode) {
                // both need to be captured for desktop keyboards
                case KeyEvent.KEYCODE_NUMPAD_ENTER:
                case KeyEvent.KEYCODE_ENTER:
                    if (!ListenerUtil.mutListener.listen(9590)) {
                        if (event.isCtrlPressed()) {
                            if (!ListenerUtil.mutListener.listen(9589)) {
                                saveNote();
                            }
                        }
                    }
                    break;
                case KeyEvent.KEYCODE_D:
                    if (!ListenerUtil.mutListener.listen(9593)) {
                        // null check in case Spinner is moved into options menu in the future
                        if ((ListenerUtil.mutListener.listen(9591) ? (event.isCtrlPressed() || (mNoteDeckSpinner != null)) : (event.isCtrlPressed() && (mNoteDeckSpinner != null)))) {
                            if (!ListenerUtil.mutListener.listen(9592)) {
                                mNoteDeckSpinner.performClick();
                            }
                        }
                    }
                    break;
                case KeyEvent.KEYCODE_L:
                    if (!ListenerUtil.mutListener.listen(9595)) {
                        if (event.isCtrlPressed()) {
                            if (!ListenerUtil.mutListener.listen(9594)) {
                                showCardTemplateEditor();
                            }
                        }
                    }
                    break;
                case KeyEvent.KEYCODE_N:
                    if (!ListenerUtil.mutListener.listen(9598)) {
                        if ((ListenerUtil.mutListener.listen(9596) ? (event.isCtrlPressed() || (mNoteTypeSpinner != null)) : (event.isCtrlPressed() && (mNoteTypeSpinner != null)))) {
                            if (!ListenerUtil.mutListener.listen(9597)) {
                                mNoteTypeSpinner.performClick();
                            }
                        }
                    }
                    break;
                case KeyEvent.KEYCODE_T:
                    if (!ListenerUtil.mutListener.listen(9601)) {
                        if ((ListenerUtil.mutListener.listen(9599) ? (event.isCtrlPressed() || event.isShiftPressed()) : (event.isCtrlPressed() && event.isShiftPressed()))) {
                            if (!ListenerUtil.mutListener.listen(9600)) {
                                showTagsDialog();
                            }
                        }
                    }
                    break;
                case KeyEvent.KEYCODE_C:
                    {
                        if (!ListenerUtil.mutListener.listen(9606)) {
                            if ((ListenerUtil.mutListener.listen(9602) ? (event.isCtrlPressed() || event.isShiftPressed()) : (event.isCtrlPressed() && event.isShiftPressed()))) {
                                if (!ListenerUtil.mutListener.listen(9603)) {
                                    insertCloze(event.isAltPressed() ? AddClozeType.SAME_NUMBER : AddClozeType.INCREMENT_NUMBER);
                                }
                                if (!ListenerUtil.mutListener.listen(9605)) {
                                    // Anki Desktop warns, but still inserts the cloze
                                    if (!isClozeType()) {
                                        if (!ListenerUtil.mutListener.listen(9604)) {
                                            UIUtils.showSimpleSnackbar(this, R.string.note_editor_insert_cloze_no_cloze_note_type, false);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                case KeyEvent.KEYCODE_P:
                    {
                        if (!ListenerUtil.mutListener.listen(9609)) {
                            if (event.isCtrlPressed()) {
                                if (!ListenerUtil.mutListener.listen(9607)) {
                                    Timber.i("Ctrl+P: Preview Pressed");
                                }
                                if (!ListenerUtil.mutListener.listen(9608)) {
                                    performPreview();
                                }
                            }
                        }
                        break;
                    }
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(9630)) {
            // 7573: Ctrl+Shift+[Num] to select a field
            if ((ListenerUtil.mutListener.listen(9612) ? ((ListenerUtil.mutListener.listen(9611) ? (event.isCtrlPressed() || event.isShiftPressed()) : (event.isCtrlPressed() && event.isShiftPressed())) || KeyUtils.isDigit(event)) : ((ListenerUtil.mutListener.listen(9611) ? (event.isCtrlPressed() || event.isShiftPressed()) : (event.isCtrlPressed() && event.isShiftPressed())) && KeyUtils.isDigit(event)))) {
                int digit = KeyUtils.getDigit(event);
                // map: '0' -> 9; '1' to 0
                int indexBase10 = (ListenerUtil.mutListener.listen(9628) ? (((ListenerUtil.mutListener.listen(9624) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) % 10) : (ListenerUtil.mutListener.listen(9623) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) / 10) : (ListenerUtil.mutListener.listen(9622) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) * 10) : (ListenerUtil.mutListener.listen(9621) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) - 10) : ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) + 10)))))) / 10) : (ListenerUtil.mutListener.listen(9627) ? (((ListenerUtil.mutListener.listen(9624) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) % 10) : (ListenerUtil.mutListener.listen(9623) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) / 10) : (ListenerUtil.mutListener.listen(9622) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) * 10) : (ListenerUtil.mutListener.listen(9621) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) - 10) : ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) + 10)))))) * 10) : (ListenerUtil.mutListener.listen(9626) ? (((ListenerUtil.mutListener.listen(9624) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) % 10) : (ListenerUtil.mutListener.listen(9623) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) / 10) : (ListenerUtil.mutListener.listen(9622) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) * 10) : (ListenerUtil.mutListener.listen(9621) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) - 10) : ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) + 10)))))) - 10) : (ListenerUtil.mutListener.listen(9625) ? (((ListenerUtil.mutListener.listen(9624) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) % 10) : (ListenerUtil.mutListener.listen(9623) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) / 10) : (ListenerUtil.mutListener.listen(9622) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) * 10) : (ListenerUtil.mutListener.listen(9621) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) - 10) : ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) + 10)))))) + 10) : (((ListenerUtil.mutListener.listen(9624) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) % 10) : (ListenerUtil.mutListener.listen(9623) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) / 10) : (ListenerUtil.mutListener.listen(9622) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) * 10) : (ListenerUtil.mutListener.listen(9621) ? ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) - 10) : ((ListenerUtil.mutListener.listen(9620) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) / 10) : (ListenerUtil.mutListener.listen(9619) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) * 10) : (ListenerUtil.mutListener.listen(9618) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) - 10) : (ListenerUtil.mutListener.listen(9617) ? (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) + 10) : (((ListenerUtil.mutListener.listen(9616) ? (digit % 1) : (ListenerUtil.mutListener.listen(9615) ? (digit / 1) : (ListenerUtil.mutListener.listen(9614) ? (digit * 1) : (ListenerUtil.mutListener.listen(9613) ? (digit + 1) : (digit - 1)))))) % 10))))) + 10)))))) % 10)))));
                if (!ListenerUtil.mutListener.listen(9629)) {
                    selectFieldIndex(indexBase10);
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private void selectFieldIndex(int index) {
        if (!ListenerUtil.mutListener.listen(9631)) {
            Timber.i("Selecting field index %d", index);
        }
        if (!ListenerUtil.mutListener.listen(9644)) {
            if ((ListenerUtil.mutListener.listen(9642) ? ((ListenerUtil.mutListener.listen(9636) ? (mEditFields.size() >= index) : (ListenerUtil.mutListener.listen(9635) ? (mEditFields.size() > index) : (ListenerUtil.mutListener.listen(9634) ? (mEditFields.size() < index) : (ListenerUtil.mutListener.listen(9633) ? (mEditFields.size() != index) : (ListenerUtil.mutListener.listen(9632) ? (mEditFields.size() == index) : (mEditFields.size() <= index)))))) && (ListenerUtil.mutListener.listen(9641) ? (index >= 0) : (ListenerUtil.mutListener.listen(9640) ? (index <= 0) : (ListenerUtil.mutListener.listen(9639) ? (index > 0) : (ListenerUtil.mutListener.listen(9638) ? (index != 0) : (ListenerUtil.mutListener.listen(9637) ? (index == 0) : (index < 0))))))) : ((ListenerUtil.mutListener.listen(9636) ? (mEditFields.size() >= index) : (ListenerUtil.mutListener.listen(9635) ? (mEditFields.size() > index) : (ListenerUtil.mutListener.listen(9634) ? (mEditFields.size() < index) : (ListenerUtil.mutListener.listen(9633) ? (mEditFields.size() != index) : (ListenerUtil.mutListener.listen(9632) ? (mEditFields.size() == index) : (mEditFields.size() <= index)))))) || (ListenerUtil.mutListener.listen(9641) ? (index >= 0) : (ListenerUtil.mutListener.listen(9640) ? (index <= 0) : (ListenerUtil.mutListener.listen(9639) ? (index > 0) : (ListenerUtil.mutListener.listen(9638) ? (index != 0) : (ListenerUtil.mutListener.listen(9637) ? (index == 0) : (index < 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9643)) {
                    Timber.i("Index out of range: %d", index);
                }
                return;
            }
        }
        FieldEditText field;
        try {
            field = mEditFields.get(index);
        } catch (IndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(9645)) {
                Timber.w(e, "Error selecting index %d", index);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(9646)) {
            field.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(9647)) {
            Timber.d("Selected field");
        }
    }

    private void insertCloze(AddClozeType addClozeType) {
        View v = getCurrentFocus();
        if (!ListenerUtil.mutListener.listen(9648)) {
            if (!(v instanceof FieldEditText)) {
                return;
            }
        }
        FieldEditText editText = (FieldEditText) v;
        if (!ListenerUtil.mutListener.listen(9649)) {
            convertSelectedTextToCloze(editText, addClozeType);
        }
    }

    private void fetchIntentInformation(Intent intent) {
        Bundle extras = intent.getExtras();
        if (!ListenerUtil.mutListener.listen(9650)) {
            if (extras == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9651)) {
            mSourceText = new String[2];
        }
        if (!ListenerUtil.mutListener.listen(9659)) {
            if (ACTION_PROCESS_TEXT.equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra(EXTRA_PROCESS_TEXT);
                if (!ListenerUtil.mutListener.listen(9656)) {
                    Timber.d("Obtained %s from intent: %s", stringExtra, EXTRA_PROCESS_TEXT);
                }
                if (!ListenerUtil.mutListener.listen(9657)) {
                    mSourceText[0] = stringExtra != null ? stringExtra : "";
                }
                if (!ListenerUtil.mutListener.listen(9658)) {
                    mSourceText[1] = "";
                }
            } else if (ACTION_CREATE_FLASHCARD.equals(intent.getAction())) {
                if (!ListenerUtil.mutListener.listen(9654)) {
                    // mTargetLanguage = extras.getString(TARGET_LANGUAGE);
                    mSourceText[0] = extras.getString(SOURCE_TEXT);
                }
                if (!ListenerUtil.mutListener.listen(9655)) {
                    mSourceText[1] = extras.getString(TARGET_TEXT);
                }
            } else {
                String first;
                String second;
                if (extras.getString(Intent.EXTRA_SUBJECT) != null) {
                    first = extras.getString(Intent.EXTRA_SUBJECT);
                } else {
                    first = "";
                }
                if (extras.getString(Intent.EXTRA_TEXT) != null) {
                    second = extras.getString(Intent.EXTRA_TEXT);
                } else {
                    second = "";
                }
                // Some users add cards via SEND intent from clipboard. In this case SUBJECT is empty
                if ("".equals(first)) {
                    // Assume that if only one field was sent then it should be the front
                    first = second;
                    second = "";
                }
                Pair<String, String> messages = new Pair<>(first, second);
                if (!ListenerUtil.mutListener.listen(9652)) {
                    mSourceText[0] = messages.first;
                }
                if (!ListenerUtil.mutListener.listen(9653)) {
                    mSourceText[1] = messages.second;
                }
            }
        }
    }

    private boolean addFromAedict(String extra_text) {
        String category;
        String[] notepad_lines = extra_text.split("\n");
        {
            long _loopCounter152 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(9694) ? (i >= notepad_lines.length) : (ListenerUtil.mutListener.listen(9693) ? (i <= notepad_lines.length) : (ListenerUtil.mutListener.listen(9692) ? (i > notepad_lines.length) : (ListenerUtil.mutListener.listen(9691) ? (i != notepad_lines.length) : (ListenerUtil.mutListener.listen(9690) ? (i == notepad_lines.length) : (i < notepad_lines.length)))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter152", ++_loopCounter152);
                if ((ListenerUtil.mutListener.listen(9660) ? (notepad_lines[i].startsWith("[") || notepad_lines[i].endsWith("]")) : (notepad_lines[i].startsWith("[") && notepad_lines[i].endsWith("]")))) {
                    category = notepad_lines[i].substring(1, (ListenerUtil.mutListener.listen(9664) ? (notepad_lines[i].length() % 1) : (ListenerUtil.mutListener.listen(9663) ? (notepad_lines[i].length() / 1) : (ListenerUtil.mutListener.listen(9662) ? (notepad_lines[i].length() * 1) : (ListenerUtil.mutListener.listen(9661) ? (notepad_lines[i].length() + 1) : (notepad_lines[i].length() - 1))))));
                    if (!ListenerUtil.mutListener.listen(9689)) {
                        if ("default".equals(category)) {
                            if (!ListenerUtil.mutListener.listen(9687)) {
                                if ((ListenerUtil.mutListener.listen(9673) ? (notepad_lines.length >= (ListenerUtil.mutListener.listen(9668) ? (i % 1) : (ListenerUtil.mutListener.listen(9667) ? (i / 1) : (ListenerUtil.mutListener.listen(9666) ? (i * 1) : (ListenerUtil.mutListener.listen(9665) ? (i - 1) : (i + 1)))))) : (ListenerUtil.mutListener.listen(9672) ? (notepad_lines.length <= (ListenerUtil.mutListener.listen(9668) ? (i % 1) : (ListenerUtil.mutListener.listen(9667) ? (i / 1) : (ListenerUtil.mutListener.listen(9666) ? (i * 1) : (ListenerUtil.mutListener.listen(9665) ? (i - 1) : (i + 1)))))) : (ListenerUtil.mutListener.listen(9671) ? (notepad_lines.length < (ListenerUtil.mutListener.listen(9668) ? (i % 1) : (ListenerUtil.mutListener.listen(9667) ? (i / 1) : (ListenerUtil.mutListener.listen(9666) ? (i * 1) : (ListenerUtil.mutListener.listen(9665) ? (i - 1) : (i + 1)))))) : (ListenerUtil.mutListener.listen(9670) ? (notepad_lines.length != (ListenerUtil.mutListener.listen(9668) ? (i % 1) : (ListenerUtil.mutListener.listen(9667) ? (i / 1) : (ListenerUtil.mutListener.listen(9666) ? (i * 1) : (ListenerUtil.mutListener.listen(9665) ? (i - 1) : (i + 1)))))) : (ListenerUtil.mutListener.listen(9669) ? (notepad_lines.length == (ListenerUtil.mutListener.listen(9668) ? (i % 1) : (ListenerUtil.mutListener.listen(9667) ? (i / 1) : (ListenerUtil.mutListener.listen(9666) ? (i * 1) : (ListenerUtil.mutListener.listen(9665) ? (i - 1) : (i + 1)))))) : (notepad_lines.length > (ListenerUtil.mutListener.listen(9668) ? (i % 1) : (ListenerUtil.mutListener.listen(9667) ? (i / 1) : (ListenerUtil.mutListener.listen(9666) ? (i * 1) : (ListenerUtil.mutListener.listen(9665) ? (i - 1) : (i + 1)))))))))))) {
                                    String[] entry_lines = notepad_lines[(ListenerUtil.mutListener.listen(9677) ? (i % 1) : (ListenerUtil.mutListener.listen(9676) ? (i / 1) : (ListenerUtil.mutListener.listen(9675) ? (i * 1) : (ListenerUtil.mutListener.listen(9674) ? (i - 1) : (i + 1)))))].split(":");
                                    if (!ListenerUtil.mutListener.listen(9686)) {
                                        if ((ListenerUtil.mutListener.listen(9682) ? (entry_lines.length >= 1) : (ListenerUtil.mutListener.listen(9681) ? (entry_lines.length <= 1) : (ListenerUtil.mutListener.listen(9680) ? (entry_lines.length < 1) : (ListenerUtil.mutListener.listen(9679) ? (entry_lines.length != 1) : (ListenerUtil.mutListener.listen(9678) ? (entry_lines.length == 1) : (entry_lines.length > 1))))))) {
                                            if (!ListenerUtil.mutListener.listen(9683)) {
                                                mSourceText[0] = entry_lines[1];
                                            }
                                            if (!ListenerUtil.mutListener.listen(9684)) {
                                                mSourceText[1] = entry_lines[0];
                                            }
                                            if (!ListenerUtil.mutListener.listen(9685)) {
                                                mAedictIntent = true;
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9688)) {
                                UIUtils.showThemedToast(NoteEditor.this, getResources().getString(R.string.intent_aedict_empty), false);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9695)) {
            UIUtils.showThemedToast(NoteEditor.this, getResources().getString(R.string.intent_aedict_category), false);
        }
        return true;
    }

    private boolean hasUnsavedChanges() {
        if (!ListenerUtil.mutListener.listen(9696)) {
            if (!collectionHasLoaded()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9699)) {
            // changed note type?
            if ((ListenerUtil.mutListener.listen(9697) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null))) {
                final JSONObject newModel = getCurrentlySelectedModel();
                final JSONObject oldModel = mCurrentEditedCard.model();
                if (!ListenerUtil.mutListener.listen(9698)) {
                    if (!newModel.equals(oldModel)) {
                        return true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9707)) {
            // changed deck?
            if ((ListenerUtil.mutListener.listen(9706) ? ((ListenerUtil.mutListener.listen(9700) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null)) || (ListenerUtil.mutListener.listen(9705) ? (mCurrentEditedCard.getDid() >= mCurrentDid) : (ListenerUtil.mutListener.listen(9704) ? (mCurrentEditedCard.getDid() <= mCurrentDid) : (ListenerUtil.mutListener.listen(9703) ? (mCurrentEditedCard.getDid() > mCurrentDid) : (ListenerUtil.mutListener.listen(9702) ? (mCurrentEditedCard.getDid() < mCurrentDid) : (ListenerUtil.mutListener.listen(9701) ? (mCurrentEditedCard.getDid() == mCurrentDid) : (mCurrentEditedCard.getDid() != mCurrentDid))))))) : ((ListenerUtil.mutListener.listen(9700) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null)) && (ListenerUtil.mutListener.listen(9705) ? (mCurrentEditedCard.getDid() >= mCurrentDid) : (ListenerUtil.mutListener.listen(9704) ? (mCurrentEditedCard.getDid() <= mCurrentDid) : (ListenerUtil.mutListener.listen(9703) ? (mCurrentEditedCard.getDid() > mCurrentDid) : (ListenerUtil.mutListener.listen(9702) ? (mCurrentEditedCard.getDid() < mCurrentDid) : (ListenerUtil.mutListener.listen(9701) ? (mCurrentEditedCard.getDid() == mCurrentDid) : (mCurrentEditedCard.getDid() != mCurrentDid))))))))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(9708)) {
            // changed fields?
            if (mFieldEdited) {
                return true;
            }
        }
        // changed tags?
        return mTagsEdited;
    }

    private boolean collectionHasLoaded() {
        return mAllModelIds != null;
    }

    @VisibleForTesting
    void saveNote() {
        final Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(9710)) {
            if (mSelectedTags == null) {
                if (!ListenerUtil.mutListener.listen(9709)) {
                    mSelectedTags = new ArrayList<>(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9766)) {
            // treat add new note and edit existing note independently
            if (mAddNote) {
                if (!ListenerUtil.mutListener.listen(9755)) {
                    // DEFECT: This does not block addition if cloze transpositions are in non-cloze fields.
                    if ((ListenerUtil.mutListener.listen(9753) ? (isClozeType() || !hasClozeDeletions()) : (isClozeType() && !hasClozeDeletions()))) {
                        if (!ListenerUtil.mutListener.listen(9754)) {
                            displayErrorSavingNote();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(9757)) {
                    {
                        long _loopCounter155 = 0;
                        // load all of the fields into the note
                        for (FieldEditText f : mEditFields) {
                            ListenerUtil.loopListener.listen("_loopCounter155", ++_loopCounter155);
                            if (!ListenerUtil.mutListener.listen(9756)) {
                                updateField(f);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9758)) {
                    // Save deck to model
                    mEditorNote.model().put("did", mCurrentDid);
                }
                if (!ListenerUtil.mutListener.listen(9759)) {
                    // Save tags to model
                    mEditorNote.setTagsFromStr(tagsAsString(mSelectedTags));
                }
                JSONArray tags = new JSONArray();
                if (!ListenerUtil.mutListener.listen(9761)) {
                    {
                        long _loopCounter156 = 0;
                        for (String t : mSelectedTags) {
                            ListenerUtil.loopListener.listen("_loopCounter156", ++_loopCounter156);
                            if (!ListenerUtil.mutListener.listen(9760)) {
                                tags.put(t);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9762)) {
                    getCol().getModels().current().put("tags", tags);
                }
                if (!ListenerUtil.mutListener.listen(9763)) {
                    getCol().getModels().setChanged();
                }
                if (!ListenerUtil.mutListener.listen(9764)) {
                    mReloadRequired = true;
                }
                if (!ListenerUtil.mutListener.listen(9765)) {
                    TaskManager.launchCollectionTask(new CollectionTask.AddNote(mEditorNote), saveNoteHandler());
                }
            } else {
                // Check whether note type has been changed
                final Model newModel = getCurrentlySelectedModel();
                final Model oldModel = (mCurrentEditedCard == null) ? null : mCurrentEditedCard.model();
                if (!ListenerUtil.mutListener.listen(9723)) {
                    if (!newModel.equals(oldModel)) {
                        if (!ListenerUtil.mutListener.listen(9711)) {
                            mReloadRequired = true;
                        }
                        if (!ListenerUtil.mutListener.listen(9722)) {
                            if ((ListenerUtil.mutListener.listen(9717) ? ((ListenerUtil.mutListener.listen(9716) ? (mModelChangeCardMap.size() >= mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9715) ? (mModelChangeCardMap.size() <= mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9714) ? (mModelChangeCardMap.size() > mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9713) ? (mModelChangeCardMap.size() != mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9712) ? (mModelChangeCardMap.size() == mEditorNote.numberOfCards()) : (mModelChangeCardMap.size() < mEditorNote.numberOfCards())))))) && mModelChangeCardMap.containsValue(null)) : ((ListenerUtil.mutListener.listen(9716) ? (mModelChangeCardMap.size() >= mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9715) ? (mModelChangeCardMap.size() <= mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9714) ? (mModelChangeCardMap.size() > mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9713) ? (mModelChangeCardMap.size() != mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(9712) ? (mModelChangeCardMap.size() == mEditorNote.numberOfCards()) : (mModelChangeCardMap.size() < mEditorNote.numberOfCards())))))) || mModelChangeCardMap.containsValue(null)))) {
                                // If cards will be lost via the new mapping then show a confirmation dialog before proceeding with the change
                                ConfirmationDialog dialog = new ConfirmationDialog();
                                if (!ListenerUtil.mutListener.listen(9719)) {
                                    dialog.setArgs(res.getString(R.string.confirm_map_cards_to_nothing));
                                }
                                Runnable confirm = () -> {
                                    // Bypass the check once the user confirms
                                    changeNoteTypeWithErrorHandling(oldModel, newModel);
                                };
                                if (!ListenerUtil.mutListener.listen(9720)) {
                                    dialog.setConfirm(confirm);
                                }
                                if (!ListenerUtil.mutListener.listen(9721)) {
                                    showDialogFragment(dialog);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9718)) {
                                    // Otherwise go straight to changing note type
                                    changeNoteTypeWithErrorHandling(oldModel, newModel);
                                }
                            }
                        }
                        return;
                    }
                }
                // Regular changes in note content
                boolean modified = false;
                if (!ListenerUtil.mutListener.listen(9736)) {
                    // changed did? this has to be done first as remFromDyn() involves a direct write to the database
                    if ((ListenerUtil.mutListener.listen(9729) ? (mCurrentEditedCard != null || (ListenerUtil.mutListener.listen(9728) ? (mCurrentEditedCard.getDid() >= mCurrentDid) : (ListenerUtil.mutListener.listen(9727) ? (mCurrentEditedCard.getDid() <= mCurrentDid) : (ListenerUtil.mutListener.listen(9726) ? (mCurrentEditedCard.getDid() > mCurrentDid) : (ListenerUtil.mutListener.listen(9725) ? (mCurrentEditedCard.getDid() < mCurrentDid) : (ListenerUtil.mutListener.listen(9724) ? (mCurrentEditedCard.getDid() == mCurrentDid) : (mCurrentEditedCard.getDid() != mCurrentDid))))))) : (mCurrentEditedCard != null && (ListenerUtil.mutListener.listen(9728) ? (mCurrentEditedCard.getDid() >= mCurrentDid) : (ListenerUtil.mutListener.listen(9727) ? (mCurrentEditedCard.getDid() <= mCurrentDid) : (ListenerUtil.mutListener.listen(9726) ? (mCurrentEditedCard.getDid() > mCurrentDid) : (ListenerUtil.mutListener.listen(9725) ? (mCurrentEditedCard.getDid() < mCurrentDid) : (ListenerUtil.mutListener.listen(9724) ? (mCurrentEditedCard.getDid() == mCurrentDid) : (mCurrentEditedCard.getDid() != mCurrentDid))))))))) {
                        if (!ListenerUtil.mutListener.listen(9730)) {
                            mReloadRequired = true;
                        }
                        if (!ListenerUtil.mutListener.listen(9731)) {
                            // remove card from filtered deck first (if relevant)
                            getCol().getSched().remFromDyn(new long[] { mCurrentEditedCard.getId() });
                        }
                        if (!ListenerUtil.mutListener.listen(9732)) {
                            // refresh the card object to reflect the database changes in remFromDyn()
                            mCurrentEditedCard.load();
                        }
                        if (!ListenerUtil.mutListener.listen(9733)) {
                            // also reload the note object
                            mEditorNote = mCurrentEditedCard.note();
                        }
                        if (!ListenerUtil.mutListener.listen(9734)) {
                            // then set the card ID to the new deck
                            mCurrentEditedCard.setDid(mCurrentDid);
                        }
                        if (!ListenerUtil.mutListener.listen(9735)) {
                            modified = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9738)) {
                    {
                        long _loopCounter153 = 0;
                        // now load any changes to the fields from the form
                        for (FieldEditText f : mEditFields) {
                            ListenerUtil.loopListener.listen("_loopCounter153", ++_loopCounter153);
                            if (!ListenerUtil.mutListener.listen(9737)) {
                                modified = modified | updateField(f);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9741)) {
                    {
                        long _loopCounter154 = 0;
                        // added tag?
                        for (String t : mSelectedTags) {
                            ListenerUtil.loopListener.listen("_loopCounter154", ++_loopCounter154);
                            if (!ListenerUtil.mutListener.listen(9740)) {
                                modified = (ListenerUtil.mutListener.listen(9739) ? (modified && !mEditorNote.hasTag(t)) : (modified || !mEditorNote.hasTag(t)));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9748)) {
                    // removed tag?
                    modified = (ListenerUtil.mutListener.listen(9747) ? (modified && (ListenerUtil.mutListener.listen(9746) ? (mEditorNote.getTags().size() >= mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9745) ? (mEditorNote.getTags().size() <= mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9744) ? (mEditorNote.getTags().size() < mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9743) ? (mEditorNote.getTags().size() != mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9742) ? (mEditorNote.getTags().size() == mSelectedTags.size()) : (mEditorNote.getTags().size() > mSelectedTags.size()))))))) : (modified || (ListenerUtil.mutListener.listen(9746) ? (mEditorNote.getTags().size() >= mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9745) ? (mEditorNote.getTags().size() <= mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9744) ? (mEditorNote.getTags().size() < mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9743) ? (mEditorNote.getTags().size() != mSelectedTags.size()) : (ListenerUtil.mutListener.listen(9742) ? (mEditorNote.getTags().size() == mSelectedTags.size()) : (mEditorNote.getTags().size() > mSelectedTags.size()))))))));
                }
                if (!ListenerUtil.mutListener.listen(9751)) {
                    if (modified) {
                        if (!ListenerUtil.mutListener.listen(9749)) {
                            mEditorNote.setTagsFromStr(tagsAsString(mSelectedTags));
                        }
                        if (!ListenerUtil.mutListener.listen(9750)) {
                            mChanged = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9752)) {
                    closeNoteEditor();
                }
            }
        }
    }

    /**
     * Change the note type from oldModel to newModel, handling the case where a full sync will be required
     */
    private void changeNoteTypeWithErrorHandling(final Model oldModel, final Model newModel) {
        Resources res = getResources();
        try {
            if (!ListenerUtil.mutListener.listen(9770)) {
                changeNoteType(oldModel, newModel);
            }
        } catch (ConfirmModSchemaException e) {
            // Libanki has determined we should ask the user to confirm first
            ConfirmationDialog dialog = new ConfirmationDialog();
            if (!ListenerUtil.mutListener.listen(9767)) {
                dialog.setArgs(res.getString(R.string.full_sync_confirmation));
            }
            Runnable confirm = () -> {
                // Bypass the check once the user confirms
                getCol().modSchemaNoCheck();
                try {
                    changeNoteType(oldModel, newModel);
                } catch (ConfirmModSchemaException e2) {
                    // This should never be reached as we explicitly called modSchemaNoCheck()
                    throw new RuntimeException(e2);
                }
            };
            if (!ListenerUtil.mutListener.listen(9768)) {
                dialog.setConfirm(confirm);
            }
            if (!ListenerUtil.mutListener.listen(9769)) {
                showDialogFragment(dialog);
            }
        }
    }

    /**
     * Change the note type from oldModel to newModel
     * @throws ConfirmModSchemaException If a full sync will be required
     */
    private void changeNoteType(Model oldModel, Model newModel) throws ConfirmModSchemaException {
        final long noteId = mEditorNote.getId();
        if (!ListenerUtil.mutListener.listen(9771)) {
            getCol().getModels().change(oldModel, noteId, newModel, mModelChangeFieldMap, mModelChangeCardMap);
        }
        if (!ListenerUtil.mutListener.listen(9772)) {
            // refresh the note object to reflect the database changes
            mEditorNote.load();
        }
        if (!ListenerUtil.mutListener.listen(9773)) {
            // close note editor
            closeNoteEditor();
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(9774)) {
            Timber.i("NoteEditor:: onBackPressed()");
        }
        if (!ListenerUtil.mutListener.listen(9775)) {
            closeCardEditorWithCheck();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(9776)) {
            // remove the "field language" as it can't be reshown without a field reference
            dismissAllDialogFragments();
        }
        if (!ListenerUtil.mutListener.listen(9777)) {
            super.onPause();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(9778)) {
            // dismiss "tags" as it may have been attached after onPause is called
            dismissAllDialogFragments();
        }
        if (!ListenerUtil.mutListener.listen(9779)) {
            super.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(9780)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(9782)) {
            if (mUnmountReceiver != null) {
                if (!ListenerUtil.mutListener.listen(9781)) {
                    unregisterReceiver(mUnmountReceiver);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(9783)) {
            getMenuInflater().inflate(R.menu.note_editor, menu);
        }
        if (!ListenerUtil.mutListener.listen(9786)) {
            if (mAddNote) {
                if (!ListenerUtil.mutListener.listen(9785)) {
                    menu.findItem(R.id.action_copy_note).setVisible(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9784)) {
                    menu.findItem(R.id.action_add_note_from_note_editor).setVisible(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9811)) {
            if (mEditFields != null) {
                if (!ListenerUtil.mutListener.listen(9810)) {
                    {
                        long _loopCounter157 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(9809) ? (i >= mEditFields.size()) : (ListenerUtil.mutListener.listen(9808) ? (i <= mEditFields.size()) : (ListenerUtil.mutListener.listen(9807) ? (i > mEditFields.size()) : (ListenerUtil.mutListener.listen(9806) ? (i != mEditFields.size()) : (ListenerUtil.mutListener.listen(9805) ? (i == mEditFields.size()) : (i < mEditFields.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter157", ++_loopCounter157);
                            Editable fieldText = mEditFields.get(i).getText();
                            if (!ListenerUtil.mutListener.listen(9804)) {
                                if ((ListenerUtil.mutListener.listen(9792) ? (fieldText != null || (ListenerUtil.mutListener.listen(9791) ? (fieldText.length() >= 0) : (ListenerUtil.mutListener.listen(9790) ? (fieldText.length() <= 0) : (ListenerUtil.mutListener.listen(9789) ? (fieldText.length() < 0) : (ListenerUtil.mutListener.listen(9788) ? (fieldText.length() != 0) : (ListenerUtil.mutListener.listen(9787) ? (fieldText.length() == 0) : (fieldText.length() > 0))))))) : (fieldText != null && (ListenerUtil.mutListener.listen(9791) ? (fieldText.length() >= 0) : (ListenerUtil.mutListener.listen(9790) ? (fieldText.length() <= 0) : (ListenerUtil.mutListener.listen(9789) ? (fieldText.length() < 0) : (ListenerUtil.mutListener.listen(9788) ? (fieldText.length() != 0) : (ListenerUtil.mutListener.listen(9787) ? (fieldText.length() == 0) : (fieldText.length() > 0))))))))) {
                                    if (!ListenerUtil.mutListener.listen(9803)) {
                                        menu.findItem(R.id.action_copy_note).setEnabled(true);
                                    }
                                    break;
                                } else if ((ListenerUtil.mutListener.listen(9801) ? (i >= (ListenerUtil.mutListener.listen(9796) ? (mEditFields.size() % 1) : (ListenerUtil.mutListener.listen(9795) ? (mEditFields.size() / 1) : (ListenerUtil.mutListener.listen(9794) ? (mEditFields.size() * 1) : (ListenerUtil.mutListener.listen(9793) ? (mEditFields.size() + 1) : (mEditFields.size() - 1)))))) : (ListenerUtil.mutListener.listen(9800) ? (i <= (ListenerUtil.mutListener.listen(9796) ? (mEditFields.size() % 1) : (ListenerUtil.mutListener.listen(9795) ? (mEditFields.size() / 1) : (ListenerUtil.mutListener.listen(9794) ? (mEditFields.size() * 1) : (ListenerUtil.mutListener.listen(9793) ? (mEditFields.size() + 1) : (mEditFields.size() - 1)))))) : (ListenerUtil.mutListener.listen(9799) ? (i > (ListenerUtil.mutListener.listen(9796) ? (mEditFields.size() % 1) : (ListenerUtil.mutListener.listen(9795) ? (mEditFields.size() / 1) : (ListenerUtil.mutListener.listen(9794) ? (mEditFields.size() * 1) : (ListenerUtil.mutListener.listen(9793) ? (mEditFields.size() + 1) : (mEditFields.size() - 1)))))) : (ListenerUtil.mutListener.listen(9798) ? (i < (ListenerUtil.mutListener.listen(9796) ? (mEditFields.size() % 1) : (ListenerUtil.mutListener.listen(9795) ? (mEditFields.size() / 1) : (ListenerUtil.mutListener.listen(9794) ? (mEditFields.size() * 1) : (ListenerUtil.mutListener.listen(9793) ? (mEditFields.size() + 1) : (mEditFields.size() - 1)))))) : (ListenerUtil.mutListener.listen(9797) ? (i != (ListenerUtil.mutListener.listen(9796) ? (mEditFields.size() % 1) : (ListenerUtil.mutListener.listen(9795) ? (mEditFields.size() / 1) : (ListenerUtil.mutListener.listen(9794) ? (mEditFields.size() * 1) : (ListenerUtil.mutListener.listen(9793) ? (mEditFields.size() + 1) : (mEditFields.size() - 1)))))) : (i == (ListenerUtil.mutListener.listen(9796) ? (mEditFields.size() % 1) : (ListenerUtil.mutListener.listen(9795) ? (mEditFields.size() / 1) : (ListenerUtil.mutListener.listen(9794) ? (mEditFields.size() * 1) : (ListenerUtil.mutListener.listen(9793) ? (mEditFields.size() + 1) : (mEditFields.size() - 1)))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(9802)) {
                                        menu.findItem(R.id.action_copy_note).setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9812)) {
            menu.findItem(R.id.action_show_toolbar).setChecked(!shouldHideToolbar());
        }
        if (!ListenerUtil.mutListener.listen(9813)) {
            menu.findItem(R.id.action_capitalize).setChecked(AnkiDroidApp.getSharedPrefs(this).getBoolean("note_editor_capitalize", true));
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected static boolean shouldReplaceNewlines() {
        return AnkiDroidApp.getSharedPrefs(AnkiDroidApp.getInstance()).getBoolean("noteEditorNewlineReplace", true);
    }

    protected static boolean shouldHideToolbar() {
        return !AnkiDroidApp.getSharedPrefs(AnkiDroidApp.getInstance()).getBoolean("noteEditorShowToolbar", true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(9834)) {
            if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(9832)) {
                    Timber.i("NoteEditor:: Home button pressed");
                }
                if (!ListenerUtil.mutListener.listen(9833)) {
                    closeCardEditorWithCheck();
                }
                return true;
            } else if (itemId == R.id.action_preview) {
                if (!ListenerUtil.mutListener.listen(9830)) {
                    Timber.i("NoteEditor:: Preview button pressed");
                }
                if (!ListenerUtil.mutListener.listen(9831)) {
                    performPreview();
                }
                return true;
            } else if (itemId == R.id.action_save) {
                if (!ListenerUtil.mutListener.listen(9828)) {
                    Timber.i("NoteEditor:: Save note button pressed");
                }
                if (!ListenerUtil.mutListener.listen(9829)) {
                    saveNote();
                }
                return true;
            } else if (itemId == R.id.action_add_note_from_note_editor) {
                if (!ListenerUtil.mutListener.listen(9826)) {
                    Timber.i("NoteEditor:: Add Note button pressed");
                }
                if (!ListenerUtil.mutListener.listen(9827)) {
                    addNewNote();
                }
                return true;
            } else if (itemId == R.id.action_copy_note) {
                if (!ListenerUtil.mutListener.listen(9824)) {
                    Timber.i("NoteEditor:: Copy Note button pressed");
                }
                if (!ListenerUtil.mutListener.listen(9825)) {
                    copyNote();
                }
                return true;
            } else if (itemId == R.id.action_font_size) {
                if (!ListenerUtil.mutListener.listen(9820)) {
                    Timber.i("NoteEditor:: Font Size button pressed");
                }
                IntegerDialog repositionDialog = new IntegerDialog();
                if (!ListenerUtil.mutListener.listen(9821)) {
                    repositionDialog.setArgs(getString(R.string.menu_font_size), getEditTextFontSize(), 2);
                }
                if (!ListenerUtil.mutListener.listen(9822)) {
                    repositionDialog.setCallbackRunnable(this::setFontSize);
                }
                if (!ListenerUtil.mutListener.listen(9823)) {
                    showDialogFragment(repositionDialog);
                }
                return true;
            } else if (itemId == R.id.action_show_toolbar) {
                if (!ListenerUtil.mutListener.listen(9817)) {
                    item.setChecked(!item.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(9818)) {
                    AnkiDroidApp.getSharedPrefs(this).edit().putBoolean("noteEditorShowToolbar", item.isChecked()).apply();
                }
                if (!ListenerUtil.mutListener.listen(9819)) {
                    updateToolbar();
                }
            } else if (itemId == R.id.action_capitalize) {
                if (!ListenerUtil.mutListener.listen(9814)) {
                    Timber.i("NoteEditor:: Capitalize button pressed. New State: %b", !item.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(9815)) {
                    // Needed for Android 9
                    item.setChecked(!item.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(9816)) {
                    toggleCapitalize(item.isChecked());
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleCapitalize(boolean value) {
        if (!ListenerUtil.mutListener.listen(9835)) {
            AnkiDroidApp.getSharedPrefs(this).edit().putBoolean("note_editor_capitalize", value).apply();
        }
        if (!ListenerUtil.mutListener.listen(9837)) {
            {
                long _loopCounter158 = 0;
                for (FieldEditText f : mEditFields) {
                    ListenerUtil.loopListener.listen("_loopCounter158", ++_loopCounter158);
                    if (!ListenerUtil.mutListener.listen(9836)) {
                        f.setCapitalize(value);
                    }
                }
            }
        }
    }

    private void setFontSize(Integer fontSizeSp) {
        if (!ListenerUtil.mutListener.listen(9844)) {
            if ((ListenerUtil.mutListener.listen(9843) ? (fontSizeSp == null && (ListenerUtil.mutListener.listen(9842) ? (fontSizeSp >= 0) : (ListenerUtil.mutListener.listen(9841) ? (fontSizeSp > 0) : (ListenerUtil.mutListener.listen(9840) ? (fontSizeSp < 0) : (ListenerUtil.mutListener.listen(9839) ? (fontSizeSp != 0) : (ListenerUtil.mutListener.listen(9838) ? (fontSizeSp == 0) : (fontSizeSp <= 0))))))) : (fontSizeSp == null || (ListenerUtil.mutListener.listen(9842) ? (fontSizeSp >= 0) : (ListenerUtil.mutListener.listen(9841) ? (fontSizeSp > 0) : (ListenerUtil.mutListener.listen(9840) ? (fontSizeSp < 0) : (ListenerUtil.mutListener.listen(9839) ? (fontSizeSp != 0) : (ListenerUtil.mutListener.listen(9838) ? (fontSizeSp == 0) : (fontSizeSp <= 0))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9845)) {
            Timber.i("Setting font size to %d", fontSizeSp);
        }
        if (!ListenerUtil.mutListener.listen(9846)) {
            AnkiDroidApp.getSharedPrefs(this).edit().putInt("note_editor_font_size", fontSizeSp).apply();
        }
        if (!ListenerUtil.mutListener.listen(9848)) {
            {
                long _loopCounter159 = 0;
                for (FieldEditText f : mEditFields) {
                    ListenerUtil.loopListener.listen("_loopCounter159", ++_loopCounter159);
                    if (!ListenerUtil.mutListener.listen(9847)) {
                        f.setTextSize(fontSizeSp);
                    }
                }
            }
        }
    }

    private String getEditTextFontSize() {
        // Values are setFontSize are whole when returned.
        float sp = TextViewUtil.getTextSizeSp(mEditFields.getFirst());
        return Integer.toString(Math.round(sp));
    }

    public void addNewNote() {
        if (!ListenerUtil.mutListener.listen(9849)) {
            openNewNoteEditor(intent -> {
            });
        }
    }

    public void copyNote() {
        if (!ListenerUtil.mutListener.listen(9850)) {
            openNewNoteEditor(intent -> {
                intent.putExtra(EXTRA_CONTENTS, getFieldsText());
                if (mSelectedTags != null) {
                    intent.putExtra(EXTRA_TAGS, mSelectedTags.toArray(new String[0]));
                }
            });
        }
    }

    private void openNewNoteEditor(Consumer<Intent> intentEnricher) {
        Intent intent = new Intent(NoteEditor.this, NoteEditor.class);
        if (!ListenerUtil.mutListener.listen(9851)) {
            intent.putExtra(EXTRA_CALLER, CALLER_CARDEDITOR);
        }
        if (!ListenerUtil.mutListener.listen(9852)) {
            intent.putExtra(EXTRA_DID, mCurrentDid);
        }
        if (!ListenerUtil.mutListener.listen(9853)) {
            // mutate event with additional properties
            intentEnricher.consume(intent);
        }
        if (!ListenerUtil.mutListener.listen(9854)) {
            startActivityForResultWithAnimation(intent, REQUEST_ADD, LEFT);
        }
    }

    @VisibleForTesting
    void performPreview() {
        Intent previewer = new Intent(NoteEditor.this, CardTemplatePreviewer.class);
        if (!ListenerUtil.mutListener.listen(9856)) {
            if (mCurrentEditedCard != null) {
                if (!ListenerUtil.mutListener.listen(9855)) {
                    previewer.putExtra("ordinal", mCurrentEditedCard.getOrd());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9857)) {
            previewer.putExtra(TemporaryModel.INTENT_MODEL_FILENAME, TemporaryModel.saveTempModel(this, mEditorNote.model()));
        }
        // Send the previewer all our current editing information
        Bundle noteEditorBundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(9858)) {
            addInstanceStateToBundle(noteEditorBundle);
        }
        if (!ListenerUtil.mutListener.listen(9859)) {
            noteEditorBundle.putBundle("editFields", getFieldsAsBundleForPreview());
        }
        if (!ListenerUtil.mutListener.listen(9860)) {
            previewer.putExtra("noteEditorBundle", noteEditorBundle);
        }
        if (!ListenerUtil.mutListener.listen(9861)) {
            startActivityForResultWithoutAnimation(previewer, REQUEST_PREVIEW);
        }
    }

    /**
     * finish when sd card is ejected
     */
    private void registerExternalStorageListener() {
        if (!ListenerUtil.mutListener.listen(9868)) {
            if (mUnmountReceiver == null) {
                if (!ListenerUtil.mutListener.listen(9865)) {
                    mUnmountReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if (!ListenerUtil.mutListener.listen(9864)) {
                                if ((ListenerUtil.mutListener.listen(9862) ? (intent.getAction() != null || intent.getAction().equals(SdCardReceiver.MEDIA_EJECT)) : (intent.getAction() != null && intent.getAction().equals(SdCardReceiver.MEDIA_EJECT)))) {
                                    if (!ListenerUtil.mutListener.listen(9863)) {
                                        finishWithoutAnimation();
                                    }
                                }
                            }
                        }
                    };
                }
                IntentFilter iFilter = new IntentFilter();
                if (!ListenerUtil.mutListener.listen(9866)) {
                    iFilter.addAction(SdCardReceiver.MEDIA_EJECT);
                }
                if (!ListenerUtil.mutListener.listen(9867)) {
                    registerReceiver(mUnmountReceiver, iFilter);
                }
            }
        }
    }

    private void setTags(@NonNull String[] tags) {
        if (!ListenerUtil.mutListener.listen(9869)) {
            mSelectedTags = new ArrayList<>(Arrays.asList(tags));
        }
        if (!ListenerUtil.mutListener.listen(9870)) {
            updateTags();
        }
    }

    private void closeCardEditorWithCheck() {
        if (!ListenerUtil.mutListener.listen(9873)) {
            if (hasUnsavedChanges()) {
                if (!ListenerUtil.mutListener.listen(9872)) {
                    showDiscardChangesDialog();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9871)) {
                    closeNoteEditor();
                }
            }
        }
    }

    private void showDiscardChangesDialog() {
        if (!ListenerUtil.mutListener.listen(9874)) {
            DiscardChangesDialog.getDefault(this).onPositive((dialog, which) -> {
                Timber.i("NoteEditor:: OK button pressed to confirm discard changes");
                closeNoteEditor();
            }).build().show();
        }
    }

    private void closeNoteEditor() {
        if (!ListenerUtil.mutListener.listen(9875)) {
            closeNoteEditor(null);
        }
    }

    private void closeNoteEditor(Intent intent) {
        int result;
        if (mChanged) {
            result = RESULT_OK;
        } else {
            result = RESULT_CANCELED;
        }
        if (!ListenerUtil.mutListener.listen(9877)) {
            if (intent == null) {
                if (!ListenerUtil.mutListener.listen(9876)) {
                    intent = new Intent();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9879)) {
            if (mReloadRequired) {
                if (!ListenerUtil.mutListener.listen(9878)) {
                    intent.putExtra("reloadRequired", true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9881)) {
            if (mChanged) {
                if (!ListenerUtil.mutListener.listen(9880)) {
                    intent.putExtra("noteChanged", true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9882)) {
            closeNoteEditor(result, intent);
        }
    }

    private void closeNoteEditor(int result, @Nullable Intent intent) {
        if (!ListenerUtil.mutListener.listen(9885)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(9884)) {
                    setResult(result, intent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9883)) {
                    setResult(result);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9886)) {
            // ensure there are no orphans from possible edit previews
            TemporaryModel.clearTempModelFiles();
        }
        if (!ListenerUtil.mutListener.listen(9894)) {
            if ((ListenerUtil.mutListener.listen(9891) ? (mCaller >= CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9890) ? (mCaller <= CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9889) ? (mCaller > CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9888) ? (mCaller < CALLER_CARDEDITOR_INTENT_ADD) : (ListenerUtil.mutListener.listen(9887) ? (mCaller != CALLER_CARDEDITOR_INTENT_ADD) : (mCaller == CALLER_CARDEDITOR_INTENT_ADD))))))) {
                if (!ListenerUtil.mutListener.listen(9893)) {
                    finishWithAnimation(NONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9892)) {
                    finishWithAnimation(RIGHT);
                }
            }
        }
    }

    private void showTagsDialog() {
        if (!ListenerUtil.mutListener.listen(9896)) {
            if (mSelectedTags == null) {
                if (!ListenerUtil.mutListener.listen(9895)) {
                    mSelectedTags = new ArrayList<>(0);
                }
            }
        }
        ArrayList<String> tags = new ArrayList<>(getCol().getTags().all());
        ArrayList<String> selTags = new ArrayList<>(mSelectedTags);
        TagsDialog.TagsDialogListener tagsDialogListener = (selectedTags, option) -> {
            if (!mSelectedTags.equals(selectedTags)) {
                mTagsEdited = true;
            }
            mSelectedTags = selectedTags;
            updateTags();
        };
        TagsDialog dialog = TagsDialog.newInstance(TagsDialog.TYPE_ADD_TAG, selTags, tags);
        if (!ListenerUtil.mutListener.listen(9897)) {
            dialog.setTagsDialogListener(tagsDialogListener);
        }
        if (!ListenerUtil.mutListener.listen(9898)) {
            showDialogFragment(dialog);
        }
    }

    private void showCardTemplateEditor() {
        Intent intent = new Intent(this, CardTemplateEditor.class);
        if (!ListenerUtil.mutListener.listen(9899)) {
            // Pass the model ID
            intent.putExtra("modelId", getCurrentlySelectedModel().getLong("id"));
        }
        if (!ListenerUtil.mutListener.listen(9900)) {
            Timber.d("showCardTemplateEditor() for model %s", intent.getLongExtra("modelId", NOT_FOUND_NOTE_TYPE));
        }
        if (!ListenerUtil.mutListener.listen(9906)) {
            // Also pass the note id and ord if not adding new note
            if ((ListenerUtil.mutListener.listen(9901) ? (!mAddNote || mCurrentEditedCard != null) : (!mAddNote && mCurrentEditedCard != null))) {
                if (!ListenerUtil.mutListener.listen(9902)) {
                    intent.putExtra("noteId", mCurrentEditedCard.note().getId());
                }
                if (!ListenerUtil.mutListener.listen(9903)) {
                    Timber.d("showCardTemplateEditor() with note %s", mCurrentEditedCard.note().getId());
                }
                if (!ListenerUtil.mutListener.listen(9904)) {
                    intent.putExtra("ordId", mCurrentEditedCard.getOrd());
                }
                if (!ListenerUtil.mutListener.listen(9905)) {
                    Timber.d("showCardTemplateEditor() with ord %s", mCurrentEditedCard.getOrd());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9907)) {
            startActivityForResultWithAnimation(intent, REQUEST_TEMPLATE_EDIT, LEFT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(9908)) {
            Timber.d("onActivityResult() with request/result: %s/%s", requestCode, resultCode);
        }
        if (!ListenerUtil.mutListener.listen(9909)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(9916)) {
            if ((ListenerUtil.mutListener.listen(9914) ? (resultCode >= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(9913) ? (resultCode <= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(9912) ? (resultCode > DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(9911) ? (resultCode < DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(9910) ? (resultCode != DeckPicker.RESULT_DB_ERROR) : (resultCode == DeckPicker.RESULT_DB_ERROR))))))) {
                if (!ListenerUtil.mutListener.listen(9915)) {
                    closeNoteEditor(DeckPicker.RESULT_DB_ERROR, null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9940)) {
            switch(requestCode) {
                case REQUEST_ADD:
                    {
                        if (!ListenerUtil.mutListener.listen(9918)) {
                            if (resultCode != RESULT_CANCELED) {
                                if (!ListenerUtil.mutListener.listen(9917)) {
                                    mChanged = true;
                                }
                            }
                        }
                        break;
                    }
                case REQUEST_MULTIMEDIA_EDIT:
                    {
                        if (!ListenerUtil.mutListener.listen(9927)) {
                            if (resultCode != RESULT_CANCELED) {
                                Collection col = getCol();
                                Bundle extras = data.getExtras();
                                if (!ListenerUtil.mutListener.listen(9919)) {
                                    if (extras == null) {
                                        break;
                                    }
                                }
                                int index = extras.getInt(MultimediaEditFieldActivity.EXTRA_RESULT_FIELD_INDEX);
                                IField field = (IField) extras.get(MultimediaEditFieldActivity.EXTRA_RESULT_FIELD);
                                if (!ListenerUtil.mutListener.listen(9920)) {
                                    if (field == null) {
                                        break;
                                    }
                                }
                                MultimediaEditableNote mNote = getCurrentMultimediaEditableNote(col);
                                if (!ListenerUtil.mutListener.listen(9921)) {
                                    mNote.setField(index, field);
                                }
                                FieldEditText fieldEditText = mEditFields.get(index);
                                // Completely replace text for text fields (because current text was passed in)
                                String formattedValue = field.getFormattedValue();
                                if (!ListenerUtil.mutListener.listen(9924)) {
                                    if (field.getType() == EFieldType.TEXT) {
                                        if (!ListenerUtil.mutListener.listen(9923)) {
                                            fieldEditText.setText(formattedValue);
                                        }
                                    } else // Insert text at cursor position if the field has focus
                                    if (fieldEditText.getText() != null) {
                                        if (!ListenerUtil.mutListener.listen(9922)) {
                                            insertStringInField(fieldEditText, formattedValue);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(9925)) {
                                    // DA - I think we only want to save the field here, not the note.
                                    NoteService.saveMedia(col, mNote);
                                }
                                if (!ListenerUtil.mutListener.listen(9926)) {
                                    mChanged = true;
                                }
                            }
                        }
                        break;
                    }
                case REQUEST_TEMPLATE_EDIT:
                    {
                        if (!ListenerUtil.mutListener.listen(9928)) {
                            // Model can change regardless of exit type - update ourselves and CardBrowser
                            mReloadRequired = true;
                        }
                        if (!ListenerUtil.mutListener.listen(9929)) {
                            mEditorNote.reloadModel();
                        }
                        if (!ListenerUtil.mutListener.listen(9939)) {
                            if ((ListenerUtil.mutListener.listen(9930) ? (mCurrentEditedCard == null && !mEditorNote.cids().contains(mCurrentEditedCard.getId())) : (mCurrentEditedCard == null || !mEditorNote.cids().contains(mCurrentEditedCard.getId())))) {
                                if (!ListenerUtil.mutListener.listen(9938)) {
                                    if (!mAddNote) {
                                        if (!ListenerUtil.mutListener.listen(9935)) {
                                            /* This can occur, for example, if the
                             * card type was deleted or if the note
                             * type was changed without moving this
                             * card to another type. */
                                            Timber.d("onActivityResult() template edit return - current card is gone, close note editor");
                                        }
                                        if (!ListenerUtil.mutListener.listen(9936)) {
                                            UIUtils.showThemedToast(this, getString(R.string.template_for_current_card_deleted), false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9937)) {
                                            closeNoteEditor();
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(9934)) {
                                            Timber.d("onActivityResult() template edit return, in add mode, just re-display");
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9931)) {
                                    Timber.d("onActivityResult() template edit return - current card exists");
                                }
                                if (!ListenerUtil.mutListener.listen(9932)) {
                                    // reload current card - the template ordinals are possibly different post-edit
                                    mCurrentEditedCard = getCol().getCard(mCurrentEditedCard.getId());
                                }
                                if (!ListenerUtil.mutListener.listen(9933)) {
                                    updateCards(mEditorNote.model());
                                }
                            }
                        }
                        break;
                    }
            }
        }
    }

    /**
     * Appends a string at the selection point, or appends to the end if not in focus
     */
    @VisibleForTesting
    void insertStringInField(EditText fieldEditText, String formattedValue) {
        if (!ListenerUtil.mutListener.listen(9943)) {
            if (fieldEditText.hasFocus()) {
                // Crashes if start > end, although this is fine for a selection via keyboard.
                int start = fieldEditText.getSelectionStart();
                int end = fieldEditText.getSelectionEnd();
                if (!ListenerUtil.mutListener.listen(9942)) {
                    fieldEditText.getText().replace(Math.min(start, end), Math.max(start, end), formattedValue);
                }
            } else // Append text if the field doesn't have focus
            {
                if (!ListenerUtil.mutListener.listen(9941)) {
                    fieldEditText.getText().append(formattedValue);
                }
            }
        }
    }

    /**
     * @param col Readonly variable to get cache dir
     */
    private MultimediaEditableNote getCurrentMultimediaEditableNote(Collection col) {
        MultimediaEditableNote mNote = NoteService.createEmptyNote(mEditorNote.model());
        String[] fields = getCurrentFieldStrings();
        if (!ListenerUtil.mutListener.listen(9944)) {
            NoteService.updateMultimediaNoteFromFields(col, fields, mEditorNote.getMid(), mNote);
        }
        return mNote;
    }

    public JSONArray getCurrentFields() {
        return mEditorNote.model().getJSONArray("flds");
    }

    @CheckResult
    public String[] getCurrentFieldStrings() {
        if (!ListenerUtil.mutListener.listen(9945)) {
            if (mEditFields == null) {
                return new String[0];
            }
        }
        String[] ret = new String[mEditFields.size()];
        if (!ListenerUtil.mutListener.listen(9952)) {
            {
                long _loopCounter160 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(9951) ? (i >= mEditFields.size()) : (ListenerUtil.mutListener.listen(9950) ? (i <= mEditFields.size()) : (ListenerUtil.mutListener.listen(9949) ? (i > mEditFields.size()) : (ListenerUtil.mutListener.listen(9948) ? (i != mEditFields.size()) : (ListenerUtil.mutListener.listen(9947) ? (i == mEditFields.size()) : (i < mEditFields.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter160", ++_loopCounter160);
                    if (!ListenerUtil.mutListener.listen(9946)) {
                        ret[i] = getCurrentFieldText(i);
                    }
                }
            }
        }
        return ret;
    }

    private void populateEditFields(FieldChangeType type, boolean editModelMode) {
        List<FieldEditLine> editLines = mFieldState.loadFieldEditLines(type);
        if (!ListenerUtil.mutListener.listen(9953)) {
            mFieldsLayoutContainer.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(9954)) {
            mCustomViewIds.clear();
        }
        if (!ListenerUtil.mutListener.listen(9955)) {
            mEditFields = new LinkedList<>();
        }
        // Use custom font if selected from preferences
        Typeface mCustomTypeface = null;
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        String customFont = preferences.getString("browserEditorFont", "");
        if (!ListenerUtil.mutListener.listen(9957)) {
            if (!"".equals(customFont)) {
                if (!ListenerUtil.mutListener.listen(9956)) {
                    mCustomTypeface = AnkiFont.getTypeface(this, customFont);
                }
            }
        }
        ClipboardManager clipboard = ContextCompat.getSystemService(this, ClipboardManager.class);
        FieldEditLine previous = null;
        if (!ListenerUtil.mutListener.listen(9958)) {
            mCustomViewIds.ensureCapacity(editLines.size());
        }
        if (!ListenerUtil.mutListener.listen(10020)) {
            {
                long _loopCounter161 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10019) ? (i >= editLines.size()) : (ListenerUtil.mutListener.listen(10018) ? (i <= editLines.size()) : (ListenerUtil.mutListener.listen(10017) ? (i > editLines.size()) : (ListenerUtil.mutListener.listen(10016) ? (i != editLines.size()) : (ListenerUtil.mutListener.listen(10015) ? (i == editLines.size()) : (i < editLines.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter161", ++_loopCounter161);
                    FieldEditLine edit_line_view = editLines.get(i);
                    if (!ListenerUtil.mutListener.listen(9959)) {
                        mCustomViewIds.add(edit_line_view.getId());
                    }
                    FieldEditText newTextbox = edit_line_view.getEditText();
                    if (!ListenerUtil.mutListener.listen(9960)) {
                        newTextbox.setImagePasteListener(this::onImagePaste);
                    }
                    if (!ListenerUtil.mutListener.listen(9975)) {
                        if ((ListenerUtil.mutListener.listen(9965) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9964) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9963) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9962) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9961) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                            if (!ListenerUtil.mutListener.listen(9972)) {
                                if ((ListenerUtil.mutListener.listen(9970) ? (i >= 0) : (ListenerUtil.mutListener.listen(9969) ? (i <= 0) : (ListenerUtil.mutListener.listen(9968) ? (i > 0) : (ListenerUtil.mutListener.listen(9967) ? (i < 0) : (ListenerUtil.mutListener.listen(9966) ? (i != 0) : (i == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(9971)) {
                                        findViewById(R.id.note_deck_spinner).setNextFocusForwardId(newTextbox.getId());
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9974)) {
                                if (previous != null) {
                                    if (!ListenerUtil.mutListener.listen(9973)) {
                                        previous.getLastViewInTabOrder().setNextFocusForwardId(newTextbox.getId());
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9976)) {
                        previous = edit_line_view;
                    }
                    if (!ListenerUtil.mutListener.listen(9977)) {
                        edit_line_view.setEnableAnimation(animationEnabled());
                    }
                    if (!ListenerUtil.mutListener.listen(9984)) {
                        // TODO: Remove the >= M check - one callback works on API 11.
                        if ((ListenerUtil.mutListener.listen(9982) ? (CompatHelper.getSdkVersion() <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9981) ? (CompatHelper.getSdkVersion() > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9980) ? (CompatHelper.getSdkVersion() < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9979) ? (CompatHelper.getSdkVersion() != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9978) ? (CompatHelper.getSdkVersion() == Build.VERSION_CODES.M) : (CompatHelper.getSdkVersion() >= Build.VERSION_CODES.M))))))) {
                            // Use custom implementation of ActionMode.Callback customize selection and insert menus
                            Field f = new Field(getFieldByIndex(i), getCol());
                            ActionModeCallback actionModeCallback = new ActionModeCallback(newTextbox, f);
                            if (!ListenerUtil.mutListener.listen(9983)) {
                                edit_line_view.setActionModeCallbacks(actionModeCallback);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9985)) {
                        edit_line_view.setTypeface(mCustomTypeface);
                    }
                    if (!ListenerUtil.mutListener.listen(9986)) {
                        edit_line_view.setHintLocale(getHintLocaleForField(edit_line_view.getName()));
                    }
                    if (!ListenerUtil.mutListener.listen(9987)) {
                        initFieldEditText(newTextbox, i, !editModelMode);
                    }
                    if (!ListenerUtil.mutListener.listen(9988)) {
                        mEditFields.add(newTextbox);
                    }
                    SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(this);
                    if (!ListenerUtil.mutListener.listen(9995)) {
                        if ((ListenerUtil.mutListener.listen(9993) ? (prefs.getInt("note_editor_font_size", -1) >= 0) : (ListenerUtil.mutListener.listen(9992) ? (prefs.getInt("note_editor_font_size", -1) <= 0) : (ListenerUtil.mutListener.listen(9991) ? (prefs.getInt("note_editor_font_size", -1) < 0) : (ListenerUtil.mutListener.listen(9990) ? (prefs.getInt("note_editor_font_size", -1) != 0) : (ListenerUtil.mutListener.listen(9989) ? (prefs.getInt("note_editor_font_size", -1) == 0) : (prefs.getInt("note_editor_font_size", -1) > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(9994)) {
                                newTextbox.setTextSize(prefs.getInt("note_editor_font_size", -1));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9996)) {
                        newTextbox.setCapitalize(prefs.getBoolean("note_editor_capitalize", true));
                    }
                    ImageButton mediaButton = edit_line_view.getMediaButton();
                    // Load icons from attributes
                    int[] icons = Themes.getResFromAttr(this, new int[] { R.attr.attachFileImage, R.attr.upDownImage });
                    if (!ListenerUtil.mutListener.listen(10004)) {
                        // Make the icon change between media icon and switch field icon depending on whether editing note type
                        if ((ListenerUtil.mutListener.listen(9997) ? (editModelMode || allowFieldRemapping()) : (editModelMode && allowFieldRemapping()))) {
                            if (!ListenerUtil.mutListener.listen(10002)) {
                                // Allow remapping if originally more than two fields
                                mediaButton.setBackgroundResource(icons[1]);
                            }
                            if (!ListenerUtil.mutListener.listen(10003)) {
                                setRemapButtonListener(mediaButton, i);
                            }
                        } else if ((ListenerUtil.mutListener.listen(9998) ? (editModelMode || !allowFieldRemapping()) : (editModelMode && !allowFieldRemapping()))) {
                            if (!ListenerUtil.mutListener.listen(10001)) {
                                mediaButton.setBackgroundResource(0);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9999)) {
                                // Use media editor button if not changing note type
                                mediaButton.setBackgroundResource(icons[0]);
                            }
                            if (!ListenerUtil.mutListener.listen(10000)) {
                                setMMButtonListener(mediaButton, i);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10012)) {
                        if ((ListenerUtil.mutListener.listen(10010) ? ((ListenerUtil.mutListener.listen(10009) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10008) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10007) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10006) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10005) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)))))) || previous != null) : ((ListenerUtil.mutListener.listen(10009) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10008) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10007) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10006) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10005) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)))))) && previous != null))) {
                            if (!ListenerUtil.mutListener.listen(10011)) {
                                previous.getLastViewInTabOrder().setNextFocusForwardId(R.id.CardEditorTagButton);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10013)) {
                        mediaButton.setContentDescription(getString(R.string.multimedia_editor_attach_mm_content, edit_line_view.getName()));
                    }
                    if (!ListenerUtil.mutListener.listen(10014)) {
                        mFieldsLayoutContainer.addView(edit_line_view);
                    }
                }
            }
        }
    }

    private boolean onImagePaste(EditText editText, Uri uri) {
        try {
            if (!ListenerUtil.mutListener.listen(10026)) {
                if (!mPastedImageCache.containsKey(uri.toString())) {
                    if (!ListenerUtil.mutListener.listen(10025)) {
                        mPastedImageCache.put(uri.toString(), loadImageIntoCollection(uri));
                    }
                }
            }
            String imageTag = mPastedImageCache.get(uri.toString());
            if (imageTag == null) {
                return false;
            }
            if (!ListenerUtil.mutListener.listen(10027)) {
                insertStringInField(editText, imageTag);
            }
            return true;
        } catch (SecurityException ex) {
            if (!ListenerUtil.mutListener.listen(10021)) {
                // (pid=11262, uid=10455) that is not exported from UID 10057
                Timber.w(ex, "Failed to paste image");
            }
            return false;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10022)) {
                // NOTE: This is happy path coding which works on Android 9.
                AnkiDroidApp.sendExceptionReport(e, "NoteEditor:onImagePaste");
            }
            if (!ListenerUtil.mutListener.listen(10023)) {
                Timber.w(e, "Failed to paste image");
            }
            if (!ListenerUtil.mutListener.listen(10024)) {
                UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_something_wrong), false);
            }
            return false;
        }
    }

    /**
     * Loads an image into the collection.media folder and returns a HTML reference
     * @param uri The uri of the image to load
     * @return HTML referring to the loaded image
     */
    @Nullable
    private String loadImageIntoCollection(Uri uri) throws IOException {
        // noinspection PointlessArithmeticExpression
        final int oneMegabyte = (ListenerUtil.mutListener.listen(10035) ? ((ListenerUtil.mutListener.listen(10031) ? (1 % 1000) : (ListenerUtil.mutListener.listen(10030) ? (1 / 1000) : (ListenerUtil.mutListener.listen(10029) ? (1 - 1000) : (ListenerUtil.mutListener.listen(10028) ? (1 + 1000) : (1 * 1000))))) % 1000) : (ListenerUtil.mutListener.listen(10034) ? ((ListenerUtil.mutListener.listen(10031) ? (1 % 1000) : (ListenerUtil.mutListener.listen(10030) ? (1 / 1000) : (ListenerUtil.mutListener.listen(10029) ? (1 - 1000) : (ListenerUtil.mutListener.listen(10028) ? (1 + 1000) : (1 * 1000))))) / 1000) : (ListenerUtil.mutListener.listen(10033) ? ((ListenerUtil.mutListener.listen(10031) ? (1 % 1000) : (ListenerUtil.mutListener.listen(10030) ? (1 / 1000) : (ListenerUtil.mutListener.listen(10029) ? (1 - 1000) : (ListenerUtil.mutListener.listen(10028) ? (1 + 1000) : (1 * 1000))))) - 1000) : (ListenerUtil.mutListener.listen(10032) ? ((ListenerUtil.mutListener.listen(10031) ? (1 % 1000) : (ListenerUtil.mutListener.listen(10030) ? (1 / 1000) : (ListenerUtil.mutListener.listen(10029) ? (1 - 1000) : (ListenerUtil.mutListener.listen(10028) ? (1 + 1000) : (1 * 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(10031) ? (1 % 1000) : (ListenerUtil.mutListener.listen(10030) ? (1 / 1000) : (ListenerUtil.mutListener.listen(10029) ? (1 - 1000) : (ListenerUtil.mutListener.listen(10028) ? (1 + 1000) : (1 * 1000))))) * 1000)))));
        String filename = ContentResolverUtil.getFileName(getContentResolver(), uri);
        InputStream fd = getContentResolver().openInputStream(uri);
        Map.Entry<String, String> fileNameAndExtension = FileUtil.getFileNameAndExtension(filename);
        File clipCopy = File.createTempFile(fileNameAndExtension.getKey(), fileNameAndExtension.getValue());
        String tempFilePath = clipCopy.getAbsolutePath();
        long bytesWritten = CompatHelper.getCompat().copyFile(fd, tempFilePath);
        if (!ListenerUtil.mutListener.listen(10036)) {
            Timber.d("File was %d bytes", bytesWritten);
        }
        if (!ListenerUtil.mutListener.listen(10045)) {
            if ((ListenerUtil.mutListener.listen(10041) ? (bytesWritten >= oneMegabyte) : (ListenerUtil.mutListener.listen(10040) ? (bytesWritten <= oneMegabyte) : (ListenerUtil.mutListener.listen(10039) ? (bytesWritten < oneMegabyte) : (ListenerUtil.mutListener.listen(10038) ? (bytesWritten != oneMegabyte) : (ListenerUtil.mutListener.listen(10037) ? (bytesWritten == oneMegabyte) : (bytesWritten > oneMegabyte))))))) {
                if (!ListenerUtil.mutListener.listen(10042)) {
                    Timber.w("File was too large: %d bytes", bytesWritten);
                }
                if (!ListenerUtil.mutListener.listen(10043)) {
                    UIUtils.showThemedToast(this, getString(R.string.note_editor_paste_too_large), false);
                }
                if (!ListenerUtil.mutListener.listen(10044)) {
                    new File(tempFilePath).delete();
                }
                return null;
            }
        }
        MultimediaEditableNote noteNew = new MultimediaEditableNote();
        if (!ListenerUtil.mutListener.listen(10046)) {
            noteNew.setNumFields(1);
        }
        ImageField field = new ImageField();
        if (!ListenerUtil.mutListener.listen(10047)) {
            field.setHasTemporaryMedia(true);
        }
        if (!ListenerUtil.mutListener.listen(10048)) {
            field.setImagePath(tempFilePath);
        }
        if (!ListenerUtil.mutListener.listen(10049)) {
            noteNew.setField(0, field);
        }
        if (!ListenerUtil.mutListener.listen(10050)) {
            NoteService.saveMedia(getCol(), noteNew);
        }
        return field.getFormattedValue();
    }

    private void setMMButtonListener(ImageButton mediaButton, final int index) {
        if (!ListenerUtil.mutListener.listen(10051)) {
            mediaButton.setOnClickListener(v -> {
                Timber.i("NoteEditor:: Multimedia button pressed for field %d", index);
                if (mEditorNote.items()[index][1].length() > 0) {
                    final Collection col = CollectionHelper.getInstance().getCol(NoteEditor.this);
                    // automatically
                    IMultimediaEditableNote mNote = getCurrentMultimediaEditableNote(col);
                    startMultimediaFieldEditor(index, mNote);
                } else {
                    // TODO: Update the icons for dark material theme, then can set 3rd argument to true
                    PopupMenuWithIcons popup = new PopupMenuWithIcons(NoteEditor.this, v, false);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.popupmenu_multimedia_options, popup.getMenu());
                    popup.setOnMenuItemClickListener(item -> {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menu_multimedia_audio) {
                            Timber.i("NoteEditor:: Record audio button pressed");
                            startMultimediaFieldEditorForField(index, new AudioRecordingField());
                            return true;
                        } else if (itemId == R.id.menu_multimedia_audio_clip) {
                            Timber.i("NoteEditor:: Add audio clip button pressed");
                            startMultimediaFieldEditorForField(index, new AudioClipField());
                            return true;
                        } else if (itemId == R.id.menu_multimedia_photo) {
                            Timber.i("NoteEditor:: Add image button pressed");
                            startMultimediaFieldEditorForField(index, new ImageField());
                            return true;
                        } else if (itemId == R.id.menu_multimedia_text) {
                            Timber.i("NoteEditor:: Advanced editor button pressed");
                            startAdvancedTextEditor(index);
                            return true;
                        } else if (itemId == R.id.menu_multimedia_clear_field) {
                            Timber.i("NoteEditor:: Clear field button pressed");
                            clearField(index);
                        }
                        return false;
                    });
                    if (AdaptionUtil.isRestrictedLearningDevice()) {
                        popup.getMenu().findItem(R.id.menu_multimedia_photo).setVisible(false);
                        popup.getMenu().findItem(R.id.menu_multimedia_text).setVisible(false);
                    }
                    popup.show();
                }
            });
        }
    }

    @VisibleForTesting
    void clearField(int index) {
        if (!ListenerUtil.mutListener.listen(10052)) {
            setFieldValueFromUi(index, "");
        }
    }

    private void startMultimediaFieldEditorForField(int index, IField field) {
        Collection col = CollectionHelper.getInstance().getCol(NoteEditor.this);
        IMultimediaEditableNote mNote = getCurrentMultimediaEditableNote(col);
        if (!ListenerUtil.mutListener.listen(10053)) {
            mNote.setField(index, field);
        }
        if (!ListenerUtil.mutListener.listen(10054)) {
            startMultimediaFieldEditor(index, mNote);
        }
    }

    private void setRemapButtonListener(ImageButton remapButton, final int newFieldIndex) {
        if (!ListenerUtil.mutListener.listen(10055)) {
            remapButton.setOnClickListener(v -> {
                Timber.i("NoteEditor:: Remap button pressed for new field %d", newFieldIndex);
                // Show list of fields from the original note which we can map to
                PopupMenu popup = new PopupMenu(NoteEditor.this, v);
                final String[][] items = mEditorNote.items();
                {
                    long _loopCounter162 = 0;
                    for (int i = 0; i < items.length; i++) {
                        ListenerUtil.loopListener.listen("_loopCounter162", ++_loopCounter162);
                        popup.getMenu().add(Menu.NONE, i, Menu.NONE, items[i][0]);
                    }
                }
                // Add "nothing" at the end of the list
                popup.getMenu().add(Menu.NONE, items.length, Menu.NONE, R.string.nothing);
                popup.setOnMenuItemClickListener(item -> {
                    // Get menu item id
                    int idx = item.getItemId();
                    Timber.i("NoteEditor:: User chose to remap to old field %d", idx);
                    // Retrieve any existing mappings between newFieldIndex and idx
                    Integer previousMapping = MapUtil.getKeyByValue(mModelChangeFieldMap, newFieldIndex);
                    Integer mappingConflict = mModelChangeFieldMap.get(idx);
                    // Update the mapping depending on any conflicts
                    if (idx == items.length && previousMapping != null) {
                        // Remove the previous mapping if None selected
                        mModelChangeFieldMap.remove(previousMapping);
                    } else if (idx < items.length && mappingConflict != null && previousMapping != null && newFieldIndex != mappingConflict) {
                        // Swap the two mappings if there was a conflict and previous mapping
                        mModelChangeFieldMap.put(previousMapping, mappingConflict);
                        mModelChangeFieldMap.put(idx, newFieldIndex);
                    } else if (idx < items.length && mappingConflict != null) {
                        // Set the conflicting field to None if no previous mapping to swap into it
                        mModelChangeFieldMap.remove(previousMapping);
                        mModelChangeFieldMap.put(idx, newFieldIndex);
                    } else if (idx < items.length) {
                        // Can simply set the new mapping if no conflicts
                        mModelChangeFieldMap.put(idx, newFieldIndex);
                    }
                    // Reload the fields
                    updateFieldsFromMap(getCurrentlySelectedModel());
                    return true;
                });
                popup.show();
            });
        }
    }

    private void startMultimediaFieldEditor(final int index, IMultimediaEditableNote mNote) {
        IField field = mNote.getField(index);
        Intent editCard = new Intent(NoteEditor.this, MultimediaEditFieldActivity.class);
        if (!ListenerUtil.mutListener.listen(10056)) {
            editCard.putExtra(MultimediaEditFieldActivity.EXTRA_FIELD_INDEX, index);
        }
        if (!ListenerUtil.mutListener.listen(10057)) {
            editCard.putExtra(MultimediaEditFieldActivity.EXTRA_FIELD, field);
        }
        if (!ListenerUtil.mutListener.listen(10058)) {
            editCard.putExtra(MultimediaEditFieldActivity.EXTRA_WHOLE_NOTE, mNote);
        }
        if (!ListenerUtil.mutListener.listen(10059)) {
            startActivityForResultWithoutAnimation(editCard, REQUEST_MULTIMEDIA_EDIT);
        }
    }

    private void initFieldEditText(FieldEditText editText, final int index, boolean enabled) {
        if (!ListenerUtil.mutListener.listen(10060)) {
            // Listen for changes in the first field so we can re-check duplicate status.
            editText.addTextChangedListener(new EditFieldTextWatcher(index));
        }
        if (!ListenerUtil.mutListener.listen(10067)) {
            if ((ListenerUtil.mutListener.listen(10065) ? (index >= 0) : (ListenerUtil.mutListener.listen(10064) ? (index <= 0) : (ListenerUtil.mutListener.listen(10063) ? (index > 0) : (ListenerUtil.mutListener.listen(10062) ? (index < 0) : (ListenerUtil.mutListener.listen(10061) ? (index != 0) : (index == 0))))))) {
                if (!ListenerUtil.mutListener.listen(10066)) {
                    editText.setOnFocusChangeListener((v, hasFocus) -> {
                        try {
                            if (hasFocus) {
                                // we only want to decorate when we lose focus
                                return;
                            }
                            String[] currentFieldStrings = getCurrentFieldStrings();
                            if (currentFieldStrings.length != 2 || currentFieldStrings[1].length() > 0) {
                                // we only decorate on 2-field cards while second field is still empty
                                return;
                            }
                            String firstField = currentFieldStrings[0];
                            String decoratedText = NoteFieldDecorator.aplicaHuevo(firstField);
                            if (!decoratedText.equals(firstField)) {
                                // we only apply the decoration if it is actually different from the first field
                                setFieldValueFromUi(1, decoratedText);
                            }
                        } catch (Exception e) {
                            Timber.w(e, "Unable to decorate text field");
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10068)) {
            editText.setEnabled(enabled);
        }
    }

    private Locale getHintLocaleForField(String name) {
        JSONObject field = getFieldByName(name);
        if (!ListenerUtil.mutListener.listen(10069)) {
            if (field == null) {
                return null;
            }
        }
        String languageTag = field.optString("ad-hint-locale", null);
        if (!ListenerUtil.mutListener.listen(10070)) {
            if (languageTag == null) {
                return null;
            }
        }
        return Locale.forLanguageTag(languageTag);
    }

    @NonNull
    private JSONObject getFieldByIndex(int index) {
        return this.getCurrentlySelectedModel().getJSONArray("flds").getJSONObject(index);
    }

    @Nullable
    private JSONObject getFieldByName(String name) {
        Pair<Integer, JSONObject> pair;
        try {
            pair = Models.fieldMap(this.getCurrentlySelectedModel()).get(name);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10071)) {
                Timber.w("Failed to obtain field '%s'", name);
            }
            return null;
        }
        if (!ListenerUtil.mutListener.listen(10072)) {
            if (pair == null) {
                return null;
            }
        }
        return pair.second;
    }

    private void setEditFieldTexts(String contents) {
        String[] fields = null;
        int len;
        if (contents == null) {
            len = 0;
        } else {
            if (!ListenerUtil.mutListener.listen(10073)) {
                fields = Utils.splitFields(contents);
            }
            len = fields.length;
        }
        if (!ListenerUtil.mutListener.listen(10087)) {
            {
                long _loopCounter163 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10086) ? (i >= mEditFields.size()) : (ListenerUtil.mutListener.listen(10085) ? (i <= mEditFields.size()) : (ListenerUtil.mutListener.listen(10084) ? (i > mEditFields.size()) : (ListenerUtil.mutListener.listen(10083) ? (i != mEditFields.size()) : (ListenerUtil.mutListener.listen(10082) ? (i == mEditFields.size()) : (i < mEditFields.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter163", ++_loopCounter163);
                    if (!ListenerUtil.mutListener.listen(10081)) {
                        if ((ListenerUtil.mutListener.listen(10078) ? (i >= len) : (ListenerUtil.mutListener.listen(10077) ? (i <= len) : (ListenerUtil.mutListener.listen(10076) ? (i > len) : (ListenerUtil.mutListener.listen(10075) ? (i != len) : (ListenerUtil.mutListener.listen(10074) ? (i == len) : (i < len))))))) {
                            if (!ListenerUtil.mutListener.listen(10080)) {
                                mEditFields.get(i).setText(fields[i]);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10079)) {
                                mEditFields.get(i).setText("");
                            }
                        }
                    }
                }
            }
        }
    }

    private void setDuplicateFieldStyles() {
        FieldEditText field = mEditFields.get(0);
        // Keep copy of current internal value for this field.
        String oldValue = mEditorNote.getFields()[0];
        if (!ListenerUtil.mutListener.listen(10088)) {
            // Update the field in the Note so we can run a dupe check on it.
            updateField(field);
        }
        // 1 is empty, 2 is dupe, null is neither.
        Note.DupeOrEmpty dupeCode = mEditorNote.dupeOrEmpty();
        if (!ListenerUtil.mutListener.listen(10091)) {
            // Change bottom line color of text field
            if (dupeCode == Note.DupeOrEmpty.DUPE) {
                if (!ListenerUtil.mutListener.listen(10090)) {
                    field.setDupeStyle();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10089)) {
                    field.setDefaultStyle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10092)) {
            // Put back the old value so we don't interfere with modification detection
            mEditorNote.values()[0] = oldValue;
        }
    }

    private String getFieldsText() {
        String[] fields = new String[mEditFields.size()];
        if (!ListenerUtil.mutListener.listen(10099)) {
            {
                long _loopCounter164 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10098) ? (i >= mEditFields.size()) : (ListenerUtil.mutListener.listen(10097) ? (i <= mEditFields.size()) : (ListenerUtil.mutListener.listen(10096) ? (i > mEditFields.size()) : (ListenerUtil.mutListener.listen(10095) ? (i != mEditFields.size()) : (ListenerUtil.mutListener.listen(10094) ? (i == mEditFields.size()) : (i < mEditFields.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter164", ++_loopCounter164);
                    if (!ListenerUtil.mutListener.listen(10093)) {
                        fields[i] = getCurrentFieldText(i);
                    }
                }
            }
        }
        return Utils.joinFields(fields);
    }

    /**
     * Returns the value of the field at the given index
     */
    private String getCurrentFieldText(int index) {
        Editable fieldText = mEditFields.get(index).getText();
        if (!ListenerUtil.mutListener.listen(10100)) {
            if (fieldText == null) {
                return "";
            }
        }
        return fieldText.toString();
    }

    private void setDid(Note note) {
        if (!ListenerUtil.mutListener.listen(10106)) {
            // where the target deck was already decided by the user.
            if ((ListenerUtil.mutListener.listen(10105) ? (mCurrentDid >= 0) : (ListenerUtil.mutListener.listen(10104) ? (mCurrentDid <= 0) : (ListenerUtil.mutListener.listen(10103) ? (mCurrentDid > 0) : (ListenerUtil.mutListener.listen(10102) ? (mCurrentDid < 0) : (ListenerUtil.mutListener.listen(10101) ? (mCurrentDid == 0) : (mCurrentDid != 0))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10115)) {
            if ((ListenerUtil.mutListener.listen(10108) ? ((ListenerUtil.mutListener.listen(10107) ? (note == null && mAddNote) : (note == null || mAddNote)) && mCurrentEditedCard == null) : ((ListenerUtil.mutListener.listen(10107) ? (note == null && mAddNote) : (note == null || mAddNote)) || mCurrentEditedCard == null))) {
                JSONObject conf = getCol().getConf();
                JSONObject model = getCol().getModels().current();
                if (!ListenerUtil.mutListener.listen(10114)) {
                    if (conf.optBoolean("addToCur", true)) {
                        if (!ListenerUtil.mutListener.listen(10111)) {
                            mCurrentDid = conf.getLong("curDeck");
                        }
                        if (!ListenerUtil.mutListener.listen(10113)) {
                            if (getCol().getDecks().isDyn(mCurrentDid)) {
                                if (!ListenerUtil.mutListener.listen(10112)) {
                                    /*
                     * If the deck in mCurrentDid is a filtered (dynamic) deck, then we can't create cards in it,
                     * and we set mCurrentDid to the Default deck. Otherwise, we keep the number that had been
                     * selected previously in the activity.
                     */
                                    mCurrentDid = 1;
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10110)) {
                            mCurrentDid = model.getLong("did");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10109)) {
                    mCurrentDid = mCurrentEditedCard.getDid();
                }
            }
        }
    }

    /**
     * Refreshes the UI using the currently selected model as a template
     */
    private void refreshNoteData(@NonNull FieldChangeType changeType) {
        if (!ListenerUtil.mutListener.listen(10116)) {
            setNote(null, changeType);
        }
    }

    /**
     * Handles setting the current note (non-null afterwards) and rebuilding the UI based on this note
     */
    private void setNote(Note note, @NonNull FieldChangeType changeType) {
        if (!ListenerUtil.mutListener.listen(10120)) {
            if ((ListenerUtil.mutListener.listen(10117) ? (note == null && mAddNote) : (note == null || mAddNote))) {
                Model model = getCol().getModels().current();
                if (!ListenerUtil.mutListener.listen(10119)) {
                    mEditorNote = new Note(getCol(), model);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10118)) {
                    mEditorNote = note;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10122)) {
            if (mSelectedTags == null) {
                if (!ListenerUtil.mutListener.listen(10121)) {
                    mSelectedTags = mEditorNote.getTags();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10123)) {
            // nb: setOnItemSelectedListener and populateEditFields need to occur after this
            setNoteTypePosition();
        }
        if (!ListenerUtil.mutListener.listen(10124)) {
            updateDeckPosition();
        }
        if (!ListenerUtil.mutListener.listen(10125)) {
            updateTags();
        }
        if (!ListenerUtil.mutListener.listen(10126)) {
            updateCards(mEditorNote.model());
        }
        if (!ListenerUtil.mutListener.listen(10127)) {
            updateToolbar();
        }
        if (!ListenerUtil.mutListener.listen(10128)) {
            populateEditFields(changeType, false);
        }
    }

    private void updateToolbar() {
        if (!ListenerUtil.mutListener.listen(10129)) {
            if (mToolbar == null) {
                return;
            }
        }
        View editorLayout = findViewById(R.id.note_editor_layout);
        int bottomMargin = shouldHideToolbar() ? 0 : (int) getResources().getDimension(R.dimen.note_editor_toolbar_height);
        MarginLayoutParams params = (MarginLayoutParams) editorLayout.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(10130)) {
            params.bottomMargin = bottomMargin;
        }
        if (!ListenerUtil.mutListener.listen(10131)) {
            editorLayout.setLayoutParams(params);
        }
        if (!ListenerUtil.mutListener.listen(10134)) {
            if (shouldHideToolbar()) {
                if (!ListenerUtil.mutListener.listen(10133)) {
                    mToolbar.setVisibility(View.GONE);
                }
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(10132)) {
                    mToolbar.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10135)) {
            mToolbar.clearCustomItems();
        }
        View clozeIcon = mToolbar.getClozeIcon();
        if (!ListenerUtil.mutListener.listen(10139)) {
            if (mEditorNote.model().isCloze()) {
                Toolbar.TextFormatter clozeFormatter = s -> {
                    Toolbar.TextWrapper.StringFormat stringFormat = new Toolbar.TextWrapper.StringFormat();
                    String prefix = "{{c" + getNextClozeIndex() + "::";
                    stringFormat.result = prefix + s + "}}";
                    if (s.length() == 0) {
                        stringFormat.start = prefix.length();
                        stringFormat.end = prefix.length();
                    } else {
                        stringFormat.start = 0;
                        stringFormat.end = stringFormat.result.length();
                    }
                    return stringFormat;
                };
                if (!ListenerUtil.mutListener.listen(10137)) {
                    clozeIcon.setOnClickListener(l -> mToolbar.onFormat(clozeFormatter));
                }
                if (!ListenerUtil.mutListener.listen(10138)) {
                    clozeIcon.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10136)) {
                    clozeIcon.setVisibility(View.GONE);
                }
            }
        }
        ArrayList<CustomToolbarButton> buttons = getToolbarButtons();
        if (!ListenerUtil.mutListener.listen(10150)) {
            {
                long _loopCounter165 = 0;
                for (CustomToolbarButton b : buttons) {
                    ListenerUtil.loopListener.listen("_loopCounter165", ++_loopCounter165);
                    // 0th button shows as '1' and is Ctrl + 1
                    int visualIndex = (ListenerUtil.mutListener.listen(10143) ? (b.getIndex() % 1) : (ListenerUtil.mutListener.listen(10142) ? (b.getIndex() / 1) : (ListenerUtil.mutListener.listen(10141) ? (b.getIndex() * 1) : (ListenerUtil.mutListener.listen(10140) ? (b.getIndex() - 1) : (b.getIndex() + 1)))));
                    String text = Integer.toString(visualIndex);
                    Drawable bmp = mToolbar.createDrawableForString(text);
                    View v = mToolbar.insertItem(0, bmp, b.toFormatter());
                    if (!ListenerUtil.mutListener.listen(10148)) {
                        // Allow Ctrl + 1...Ctrl + 0 for item 10.
                        v.setTag(Integer.toString((ListenerUtil.mutListener.listen(10147) ? (visualIndex / 10) : (ListenerUtil.mutListener.listen(10146) ? (visualIndex * 10) : (ListenerUtil.mutListener.listen(10145) ? (visualIndex - 10) : (ListenerUtil.mutListener.listen(10144) ? (visualIndex + 10) : (visualIndex % 10)))))));
                    }
                    if (!ListenerUtil.mutListener.listen(10149)) {
                        v.setOnLongClickListener(discard -> {
                            suggestRemoveButton(b);
                            return true;
                        });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10151)) {
            // Let the user add more buttons (always at the end).
            mToolbar.insertItem(0, R.drawable.ic_add_toolbar_icon, this::displayAddToolbarDialog);
        }
    }

    @NonNull
    private ArrayList<CustomToolbarButton> getToolbarButtons() {
        Set<String> set = AnkiDroidApp.getSharedPrefs(this).getStringSet("note_editor_custom_buttons", new HashSet<>(0));
        return CustomToolbarButton.fromStringSet(set);
    }

    private void saveToolbarButtons(ArrayList<CustomToolbarButton> buttons) {
        if (!ListenerUtil.mutListener.listen(10152)) {
            AnkiDroidApp.getSharedPrefs(this).edit().putStringSet("note_editor_custom_buttons", CustomToolbarButton.toStringSet(buttons)).apply();
        }
    }

    private void addToolbarButton(String prefix, String suffix) {
        if (!ListenerUtil.mutListener.listen(10154)) {
            if ((ListenerUtil.mutListener.listen(10153) ? (TextUtils.isEmpty(prefix) || TextUtils.isEmpty(suffix)) : (TextUtils.isEmpty(prefix) && TextUtils.isEmpty(suffix)))) {
                return;
            }
        }
        ArrayList<CustomToolbarButton> toolbarButtons = getToolbarButtons();
        if (!ListenerUtil.mutListener.listen(10155)) {
            toolbarButtons.add(new CustomToolbarButton(toolbarButtons.size(), prefix, suffix));
        }
        if (!ListenerUtil.mutListener.listen(10156)) {
            saveToolbarButtons(toolbarButtons);
        }
        if (!ListenerUtil.mutListener.listen(10157)) {
            updateToolbar();
        }
    }

    private void suggestRemoveButton(CustomToolbarButton button) {
        if (!ListenerUtil.mutListener.listen(10158)) {
            new MaterialDialog.Builder(this).title(R.string.remove_toolbar_item).positiveText(R.string.dialog_positive_delete).negativeText(R.string.dialog_cancel).onPositive((dialog, action) -> removeButton(button)).show();
        }
    }

    private void removeButton(CustomToolbarButton button) {
        ArrayList<CustomToolbarButton> toolbarButtons = getToolbarButtons();
        if (!ListenerUtil.mutListener.listen(10159)) {
            toolbarButtons.remove(button.getIndex());
        }
        if (!ListenerUtil.mutListener.listen(10160)) {
            saveToolbarButtons(toolbarButtons);
        }
        if (!ListenerUtil.mutListener.listen(10161)) {
            updateToolbar();
        }
    }

    private void displayAddToolbarDialog() {
        if (!ListenerUtil.mutListener.listen(10162)) {
            new MaterialDialog.Builder(this).title(R.string.add_toolbar_item).customView(R.layout.note_editor_toolbar_add_custom_item, true).positiveText(R.string.dialog_positive_create).neutralText(R.string.help).negativeText(R.string.dialog_cancel).onNeutral((m, v) -> openUrl(Uri.parse(getString(R.string.link_manual_note_format_toolbar)))).onPositive((m, v) -> {
                View view = m.getView();
                EditText et = view.findViewById(R.id.note_editor_toolbar_before);
                EditText et2 = view.findViewById(R.id.note_editor_toolbar_after);
                addToolbarButton(et.getText().toString(), et2.getText().toString());
            }).show();
        }
    }

    private void setNoteTypePosition() {
        // Set current note type and deck positions in spinners
        int position = mAllModelIds.indexOf(mEditorNote.model().getLong("id"));
        if (!ListenerUtil.mutListener.listen(10163)) {
            // set selection without firing selectionChanged event
            mNoteTypeSpinner.setSelection(position, false);
        }
    }

    private void updateDeckPosition() {
        int position = mAllDeckIds.indexOf(mCurrentDid);
        if (!ListenerUtil.mutListener.listen(10171)) {
            if ((ListenerUtil.mutListener.listen(10168) ? (position >= -1) : (ListenerUtil.mutListener.listen(10167) ? (position <= -1) : (ListenerUtil.mutListener.listen(10166) ? (position > -1) : (ListenerUtil.mutListener.listen(10165) ? (position < -1) : (ListenerUtil.mutListener.listen(10164) ? (position == -1) : (position != -1))))))) {
                if (!ListenerUtil.mutListener.listen(10170)) {
                    mNoteDeckSpinner.setSelection(position, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10169)) {
                    Timber.e("updateDeckPosition() error :: mCurrentDid=%d, position=%d", mCurrentDid, position);
                }
            }
        }
    }

    private void updateTags() {
        if (!ListenerUtil.mutListener.listen(10173)) {
            if (mSelectedTags == null) {
                if (!ListenerUtil.mutListener.listen(10172)) {
                    mSelectedTags = new ArrayList<>(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10174)) {
            mTagsButton.setText(getResources().getString(R.string.CardEditorTags, getCol().getTags().join(getCol().getTags().canonify(mSelectedTags)).trim().replace(" ", ", ")));
        }
    }

    /**
     * Update the list of card templates for current note type
     */
    private void updateCards(JSONObject model) {
        if (!ListenerUtil.mutListener.listen(10175)) {
            Timber.d("updateCards()");
        }
        JSONArray tmpls = model.getJSONArray("tmpls");
        StringBuilder cardsList = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(10176)) {
            // Build comma separated list of card names
            Timber.d("updateCards() template count is %s", tmpls.length());
        }
        if (!ListenerUtil.mutListener.listen(10205)) {
            {
                long _loopCounter166 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10204) ? (i >= tmpls.length()) : (ListenerUtil.mutListener.listen(10203) ? (i <= tmpls.length()) : (ListenerUtil.mutListener.listen(10202) ? (i > tmpls.length()) : (ListenerUtil.mutListener.listen(10201) ? (i != tmpls.length()) : (ListenerUtil.mutListener.listen(10200) ? (i == tmpls.length()) : (i < tmpls.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter166", ++_loopCounter166);
                    String name = tmpls.getJSONObject(i).optString("name");
                    if (!ListenerUtil.mutListener.listen(10187)) {
                        // If more than one card, and we have an existing card, underline existing card
                        if ((ListenerUtil.mutListener.listen(10185) ? ((ListenerUtil.mutListener.listen(10184) ? ((ListenerUtil.mutListener.listen(10183) ? ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) || model == mEditorNote.model()) : ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) && model == mEditorNote.model())) || mCurrentEditedCard != null) : ((ListenerUtil.mutListener.listen(10183) ? ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) || model == mEditorNote.model()) : ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) && model == mEditorNote.model())) && mCurrentEditedCard != null)) || mCurrentEditedCard.template().optString("name").equals(name)) : ((ListenerUtil.mutListener.listen(10184) ? ((ListenerUtil.mutListener.listen(10183) ? ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) || model == mEditorNote.model()) : ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) && model == mEditorNote.model())) || mCurrentEditedCard != null) : ((ListenerUtil.mutListener.listen(10183) ? ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) || model == mEditorNote.model()) : ((ListenerUtil.mutListener.listen(10182) ? (!mAddNote || (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10181) ? (tmpls.length() >= 1) : (ListenerUtil.mutListener.listen(10180) ? (tmpls.length() <= 1) : (ListenerUtil.mutListener.listen(10179) ? (tmpls.length() < 1) : (ListenerUtil.mutListener.listen(10178) ? (tmpls.length() != 1) : (ListenerUtil.mutListener.listen(10177) ? (tmpls.length() == 1) : (tmpls.length() > 1)))))))) && model == mEditorNote.model())) && mCurrentEditedCard != null)) && mCurrentEditedCard.template().optString("name").equals(name)))) {
                            if (!ListenerUtil.mutListener.listen(10186)) {
                                name = "<u>" + name + "</u>";
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10188)) {
                        cardsList.append(name);
                    }
                    if (!ListenerUtil.mutListener.listen(10199)) {
                        if ((ListenerUtil.mutListener.listen(10197) ? (i >= (ListenerUtil.mutListener.listen(10192) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(10191) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(10190) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(10189) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))) : (ListenerUtil.mutListener.listen(10196) ? (i <= (ListenerUtil.mutListener.listen(10192) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(10191) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(10190) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(10189) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))) : (ListenerUtil.mutListener.listen(10195) ? (i > (ListenerUtil.mutListener.listen(10192) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(10191) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(10190) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(10189) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))) : (ListenerUtil.mutListener.listen(10194) ? (i != (ListenerUtil.mutListener.listen(10192) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(10191) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(10190) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(10189) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))) : (ListenerUtil.mutListener.listen(10193) ? (i == (ListenerUtil.mutListener.listen(10192) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(10191) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(10190) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(10189) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))) : (i < (ListenerUtil.mutListener.listen(10192) ? (tmpls.length() % 1) : (ListenerUtil.mutListener.listen(10191) ? (tmpls.length() / 1) : (ListenerUtil.mutListener.listen(10190) ? (tmpls.length() * 1) : (ListenerUtil.mutListener.listen(10189) ? (tmpls.length() + 1) : (tmpls.length() - 1)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(10198)) {
                                cardsList.append(", ");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10213)) {
            // Make cards list red if the number of cards is being reduced
            if ((ListenerUtil.mutListener.listen(10211) ? (!mAddNote || (ListenerUtil.mutListener.listen(10210) ? (tmpls.length() >= mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10209) ? (tmpls.length() <= mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10208) ? (tmpls.length() > mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10207) ? (tmpls.length() != mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10206) ? (tmpls.length() == mEditorNote.model().getJSONArray("tmpls").length()) : (tmpls.length() < mEditorNote.model().getJSONArray("tmpls").length()))))))) : (!mAddNote && (ListenerUtil.mutListener.listen(10210) ? (tmpls.length() >= mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10209) ? (tmpls.length() <= mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10208) ? (tmpls.length() > mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10207) ? (tmpls.length() != mEditorNote.model().getJSONArray("tmpls").length()) : (ListenerUtil.mutListener.listen(10206) ? (tmpls.length() == mEditorNote.model().getJSONArray("tmpls").length()) : (tmpls.length() < mEditorNote.model().getJSONArray("tmpls").length()))))))))) {
                if (!ListenerUtil.mutListener.listen(10212)) {
                    cardsList = new StringBuilder("<font color='red'>" + cardsList + "</font>");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10214)) {
            mCardsButton.setText(HtmlCompat.fromHtml(getResources().getString(R.string.CardEditorCards, cardsList.toString()), HtmlCompat.FROM_HTML_MODE_LEGACY));
        }
    }

    private boolean updateField(FieldEditText field) {
        String currentValue = "";
        Editable fieldText = field.getText();
        if (!ListenerUtil.mutListener.listen(10216)) {
            if (fieldText != null) {
                if (!ListenerUtil.mutListener.listen(10215)) {
                    currentValue = fieldText.toString();
                }
            }
        }
        String newValue = convertToHtmlNewline(currentValue);
        if (!ListenerUtil.mutListener.listen(10218)) {
            if (!mEditorNote.values()[field.getOrd()].equals(newValue)) {
                if (!ListenerUtil.mutListener.listen(10217)) {
                    mEditorNote.values()[field.getOrd()] = newValue;
                }
                return true;
            }
        }
        return false;
    }

    private String convertToHtmlNewline(@NonNull String fieldData) {
        if (!ListenerUtil.mutListener.listen(10219)) {
            if (!shouldReplaceNewlines()) {
                return fieldData;
            }
        }
        return fieldData.replace(FieldEditText.NEW_LINE, "<br>");
    }

    private String tagsAsString(List<String> tags) {
        return TextUtils.join(" ", tags);
    }

    private Model getCurrentlySelectedModel() {
        return getCol().getModels().get(mAllModelIds.get(mNoteTypeSpinner.getSelectedItemPosition()));
    }

    /**
     * Update all the field EditText views based on the currently selected note type and the mModelChangeFieldMap
     */
    private void updateFieldsFromMap(Model newModel) {
        FieldChangeType type = FieldChangeType.refreshWithMap(newModel, mModelChangeFieldMap, shouldReplaceNewlines());
        if (!ListenerUtil.mutListener.listen(10220)) {
            populateEditFields(type, true);
        }
        if (!ListenerUtil.mutListener.listen(10221)) {
            updateCards(newModel);
        }
    }

    /**
     * @return whether or not to allow remapping of fields for current model
     */
    private boolean allowFieldRemapping() {
        // Map<String, Pair<Integer, JSONObject>> fMapNew = getCol().getModels().fieldMap(getCurrentlySelectedModel())
        return (ListenerUtil.mutListener.listen(10226) ? (mEditorNote.items().length >= 2) : (ListenerUtil.mutListener.listen(10225) ? (mEditorNote.items().length <= 2) : (ListenerUtil.mutListener.listen(10224) ? (mEditorNote.items().length < 2) : (ListenerUtil.mutListener.listen(10223) ? (mEditorNote.items().length != 2) : (ListenerUtil.mutListener.listen(10222) ? (mEditorNote.items().length == 2) : (mEditorNote.items().length > 2))))));
    }

    public String[][] getFieldsFromSelectedNote() {
        return mEditorNote.items();
    }

    private class SetNoteTypeListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // Timber.i("NoteEditor:: onItemSelected() fired on mNoteTypeSpinner");
            long oldModelId = getCol().getModels().current().getLong("id");
            @NonNull
            Long newId = mAllModelIds.get(pos);
            if (!ListenerUtil.mutListener.listen(10227)) {
                Timber.i("Changing note type to '%d", newId);
            }
            if (!ListenerUtil.mutListener.listen(10243)) {
                if ((ListenerUtil.mutListener.listen(10232) ? (oldModelId >= newId) : (ListenerUtil.mutListener.listen(10231) ? (oldModelId <= newId) : (ListenerUtil.mutListener.listen(10230) ? (oldModelId > newId) : (ListenerUtil.mutListener.listen(10229) ? (oldModelId < newId) : (ListenerUtil.mutListener.listen(10228) ? (oldModelId == newId) : (oldModelId != newId))))))) {
                    Model model = getCol().getModels().get(newId);
                    if (!ListenerUtil.mutListener.listen(10234)) {
                        if (model == null) {
                            if (!ListenerUtil.mutListener.listen(10233)) {
                                Timber.w("New model %s not found, not changing note type", newId);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10235)) {
                        getCol().getModels().setCurrent(model);
                    }
                    Deck currentDeck = getCol().getDecks().current();
                    if (!ListenerUtil.mutListener.listen(10236)) {
                        currentDeck.put("mid", newId);
                    }
                    if (!ListenerUtil.mutListener.listen(10237)) {
                        getCol().getDecks().save(currentDeck);
                    }
                    if (!ListenerUtil.mutListener.listen(10240)) {
                        // Update deck
                        if (!getCol().getConf().optBoolean("addToCur", true)) {
                            if (!ListenerUtil.mutListener.listen(10238)) {
                                mCurrentDid = model.getLong("did");
                            }
                            if (!ListenerUtil.mutListener.listen(10239)) {
                                updateDeckPosition();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10241)) {
                        refreshNoteData(FieldChangeType.changeFieldCount(shouldReplaceNewlines()));
                    }
                    if (!ListenerUtil.mutListener.listen(10242)) {
                        setDuplicateFieldStyles();
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    /* Uses only if mCurrentEditedCard is set, so from reviewer or card browser.*/
    private class EditNoteTypeListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // Get the current model
            long noteModelId = mCurrentEditedCard.model().getLong("id");
            // Get new model
            Model newModel = getCol().getModels().get(mAllModelIds.get(pos));
            if (!ListenerUtil.mutListener.listen(10246)) {
                if ((ListenerUtil.mutListener.listen(10244) ? (newModel == null && newModel.getJSONArray("tmpls") == null) : (newModel == null || newModel.getJSONArray("tmpls") == null))) {
                    if (!ListenerUtil.mutListener.listen(10245)) {
                        Timber.w("newModel %s not found", mAllModelIds.get(pos));
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(10291)) {
                // Configure the interface according to whether note type is getting changed or not
                if ((ListenerUtil.mutListener.listen(10251) ? (mAllModelIds.get(pos) >= noteModelId) : (ListenerUtil.mutListener.listen(10250) ? (mAllModelIds.get(pos) <= noteModelId) : (ListenerUtil.mutListener.listen(10249) ? (mAllModelIds.get(pos) > noteModelId) : (ListenerUtil.mutListener.listen(10248) ? (mAllModelIds.get(pos) < noteModelId) : (ListenerUtil.mutListener.listen(10247) ? (mAllModelIds.get(pos) == noteModelId) : (mAllModelIds.get(pos) != noteModelId))))))) {
                    // Initialize mapping between fields of old model -> new model
                    int itemsLength = mEditorNote.items().length;
                    if (!ListenerUtil.mutListener.listen(10256)) {
                        mModelChangeFieldMap = new HashMap<>(itemsLength);
                    }
                    if (!ListenerUtil.mutListener.listen(10263)) {
                        {
                            long _loopCounter167 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(10262) ? (i >= itemsLength) : (ListenerUtil.mutListener.listen(10261) ? (i <= itemsLength) : (ListenerUtil.mutListener.listen(10260) ? (i > itemsLength) : (ListenerUtil.mutListener.listen(10259) ? (i != itemsLength) : (ListenerUtil.mutListener.listen(10258) ? (i == itemsLength) : (i < itemsLength)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter167", ++_loopCounter167);
                                if (!ListenerUtil.mutListener.listen(10257)) {
                                    mModelChangeFieldMap.put(i, i);
                                }
                            }
                        }
                    }
                    // Initialize mapping between cards new model -> old model
                    int templatesLength = newModel.getJSONArray("tmpls").length();
                    if (!ListenerUtil.mutListener.listen(10264)) {
                        mModelChangeCardMap = new HashMap<>(templatesLength);
                    }
                    if (!ListenerUtil.mutListener.listen(10278)) {
                        {
                            long _loopCounter168 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(10277) ? (i >= templatesLength) : (ListenerUtil.mutListener.listen(10276) ? (i <= templatesLength) : (ListenerUtil.mutListener.listen(10275) ? (i > templatesLength) : (ListenerUtil.mutListener.listen(10274) ? (i != templatesLength) : (ListenerUtil.mutListener.listen(10273) ? (i == templatesLength) : (i < templatesLength)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter168", ++_loopCounter168);
                                if (!ListenerUtil.mutListener.listen(10272)) {
                                    if ((ListenerUtil.mutListener.listen(10269) ? (i >= mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(10268) ? (i <= mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(10267) ? (i > mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(10266) ? (i != mEditorNote.numberOfCards()) : (ListenerUtil.mutListener.listen(10265) ? (i == mEditorNote.numberOfCards()) : (i < mEditorNote.numberOfCards()))))))) {
                                        if (!ListenerUtil.mutListener.listen(10271)) {
                                            mModelChangeCardMap.put(i, i);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(10270)) {
                                            mModelChangeCardMap.put(i, null);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10279)) {
                        // Update the field text edits based on the default mapping just assigned
                        updateFieldsFromMap(newModel);
                    }
                    if (!ListenerUtil.mutListener.listen(10280)) {
                        // Don't let the user change any other values at the same time as changing note type
                        mSelectedTags = mEditorNote.getTags();
                    }
                    if (!ListenerUtil.mutListener.listen(10281)) {
                        updateTags();
                    }
                    if (!ListenerUtil.mutListener.listen(10282)) {
                        findViewById(R.id.CardEditorTagButton).setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(10283)) {
                        // ((LinearLayout) findViewById(R.id.CardEditorCardsButton)).setEnabled(false);
                        mNoteDeckSpinner.setEnabled(false);
                    }
                    int position = mAllDeckIds.indexOf(mCurrentEditedCard.getDid());
                    if (!ListenerUtil.mutListener.listen(10290)) {
                        if ((ListenerUtil.mutListener.listen(10288) ? (position >= -1) : (ListenerUtil.mutListener.listen(10287) ? (position <= -1) : (ListenerUtil.mutListener.listen(10286) ? (position > -1) : (ListenerUtil.mutListener.listen(10285) ? (position < -1) : (ListenerUtil.mutListener.listen(10284) ? (position == -1) : (position != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(10289)) {
                                mNoteDeckSpinner.setSelection(position, false);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(10252)) {
                        populateEditFields(FieldChangeType.refresh(shouldReplaceNewlines()), false);
                    }
                    if (!ListenerUtil.mutListener.listen(10253)) {
                        updateCards(mCurrentEditedCard.model());
                    }
                    if (!ListenerUtil.mutListener.listen(10254)) {
                        findViewById(R.id.CardEditorTagButton).setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(10255)) {
                        // ((LinearLayout) findViewById(R.id.CardEditorCardsButton)).setEnabled(false);
                        mNoteDeckSpinner.setEnabled(true);
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    /**
     * Custom ActionMode.Callback implementation for adding and handling cloze deletion action
     * button in the text selection menu.
     */
    @TargetApi(23)
    private class ActionModeCallback implements ActionMode.Callback, LocaleSelectionDialog.LocaleSelectionDialogHandler {

        private final FieldEditText mTextBox;

        private final Field mField;

        private final int mClozeMenuId = View.generateViewId();

        @RequiresApi(Build.VERSION_CODES.N)
        private final int mSetLanguageId = View.generateViewId();

        private ActionModeCallback(FieldEditText textBox, Field field) {
            super();
            mTextBox = textBox;
            mField = field;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(10292)) {
                // Adding the cloze deletion floating context menu item, but only once.
                if (menu.findItem(mClozeMenuId) != null) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(10299)) {
                if ((ListenerUtil.mutListener.listen(10298) ? ((ListenerUtil.mutListener.listen(10297) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10296) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10295) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10294) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10293) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) || menu.findItem(mSetLanguageId) != null) : ((ListenerUtil.mutListener.listen(10297) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10296) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10295) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10294) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10293) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) && menu.findItem(mSetLanguageId) != null))) {
                    return false;
                }
            }
            int initialSize = menu.size();
            if (!ListenerUtil.mutListener.listen(10301)) {
                if (isClozeType()) {
                    if (!ListenerUtil.mutListener.listen(10300)) {
                        menu.add(Menu.NONE, mClozeMenuId, 0, R.string.multimedia_editor_popup_cloze);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10308)) {
                if ((ListenerUtil.mutListener.listen(10306) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10305) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10304) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10303) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10302) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                    if (!ListenerUtil.mutListener.listen(10307)) {
                        // This should be after "Paste as Plain Text"
                        menu.add(Menu.NONE, mSetLanguageId, 99, R.string.note_editor_set_field_language);
                    }
                }
            }
            return initialSize != menu.size();
        }

        @SuppressLint("SetTextI18n")
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            if ((ListenerUtil.mutListener.listen(10313) ? (itemId >= mClozeMenuId) : (ListenerUtil.mutListener.listen(10312) ? (itemId <= mClozeMenuId) : (ListenerUtil.mutListener.listen(10311) ? (itemId > mClozeMenuId) : (ListenerUtil.mutListener.listen(10310) ? (itemId < mClozeMenuId) : (ListenerUtil.mutListener.listen(10309) ? (itemId != mClozeMenuId) : (itemId == mClozeMenuId))))))) {
                if (!ListenerUtil.mutListener.listen(10327)) {
                    convertSelectedTextToCloze(mTextBox, AddClozeType.INCREMENT_NUMBER);
                }
                if (!ListenerUtil.mutListener.listen(10328)) {
                    mode.finish();
                }
                return true;
            } else if ((ListenerUtil.mutListener.listen(10324) ? ((ListenerUtil.mutListener.listen(10318) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10317) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10316) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10315) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10314) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) || (ListenerUtil.mutListener.listen(10323) ? (itemId >= mSetLanguageId) : (ListenerUtil.mutListener.listen(10322) ? (itemId <= mSetLanguageId) : (ListenerUtil.mutListener.listen(10321) ? (itemId > mSetLanguageId) : (ListenerUtil.mutListener.listen(10320) ? (itemId < mSetLanguageId) : (ListenerUtil.mutListener.listen(10319) ? (itemId != mSetLanguageId) : (itemId == mSetLanguageId))))))) : ((ListenerUtil.mutListener.listen(10318) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10317) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10316) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10315) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(10314) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) && (ListenerUtil.mutListener.listen(10323) ? (itemId >= mSetLanguageId) : (ListenerUtil.mutListener.listen(10322) ? (itemId <= mSetLanguageId) : (ListenerUtil.mutListener.listen(10321) ? (itemId > mSetLanguageId) : (ListenerUtil.mutListener.listen(10320) ? (itemId < mSetLanguageId) : (ListenerUtil.mutListener.listen(10319) ? (itemId != mSetLanguageId) : (itemId == mSetLanguageId))))))))) {
                if (!ListenerUtil.mutListener.listen(10325)) {
                    displaySelectInputLanguage();
                }
                if (!ListenerUtil.mutListener.listen(10326)) {
                    mode.finish();
                }
                return true;
            } else {
                return false;
            }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private void displaySelectInputLanguage() {
            DialogFragment dialogFragment = LocaleSelectionDialog.newInstance(this);
            if (!ListenerUtil.mutListener.listen(10329)) {
                showDialogFragment(dialogFragment);
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }

        @Override
        @RequiresApi(Build.VERSION_CODES.N)
        public void onSelectedLocale(@NonNull Locale selectedLocale) {
            if (!ListenerUtil.mutListener.listen(10330)) {
                mField.setHintLocale(selectedLocale);
            }
            if (!ListenerUtil.mutListener.listen(10331)) {
                mTextBox.setHintLocale(selectedLocale);
            }
            if (!ListenerUtil.mutListener.listen(10332)) {
                dismissAllDialogFragments();
            }
        }

        @Override
        @RequiresApi(Build.VERSION_CODES.N)
        public void onLocaleSelectionCancelled() {
            if (!ListenerUtil.mutListener.listen(10333)) {
                dismissAllDialogFragments();
            }
        }
    }

    private void convertSelectedTextToCloze(FieldEditText textBox, AddClozeType addClozeType) {
        int nextClozeIndex = getNextClozeIndex();
        if (!ListenerUtil.mutListener.listen(10339)) {
            if (addClozeType == AddClozeType.SAME_NUMBER) {
                if (!ListenerUtil.mutListener.listen(10338)) {
                    nextClozeIndex = (ListenerUtil.mutListener.listen(10337) ? (nextClozeIndex % 1) : (ListenerUtil.mutListener.listen(10336) ? (nextClozeIndex / 1) : (ListenerUtil.mutListener.listen(10335) ? (nextClozeIndex * 1) : (ListenerUtil.mutListener.listen(10334) ? (nextClozeIndex + 1) : (nextClozeIndex - 1)))));
                }
            }
        }
        String prefix = "{{c" + Math.max(1, nextClozeIndex) + "::";
        String suffix = "}}";
        if (!ListenerUtil.mutListener.listen(10340)) {
            modifyCurrentSelection(new Toolbar.TextWrapper(prefix, suffix), textBox);
        }
    }

    @NonNull
    private String previewNextClozeDeletion(int start, int end, CharSequence text) {
        CharSequence selectedText = text.subSequence(start, end);
        int nextClozeIndex = getNextClozeIndex();
        if (!ListenerUtil.mutListener.listen(10341)) {
            nextClozeIndex = Math.max(1, nextClozeIndex);
        }
        // Update text field with updated text and selection
        return String.format("{{c%s::%s}}", nextClozeIndex, selectedText);
    }

    private boolean hasClozeDeletions() {
        return (ListenerUtil.mutListener.listen(10346) ? (getNextClozeIndex() >= 1) : (ListenerUtil.mutListener.listen(10345) ? (getNextClozeIndex() <= 1) : (ListenerUtil.mutListener.listen(10344) ? (getNextClozeIndex() < 1) : (ListenerUtil.mutListener.listen(10343) ? (getNextClozeIndex() != 1) : (ListenerUtil.mutListener.listen(10342) ? (getNextClozeIndex() == 1) : (getNextClozeIndex() > 1))))));
    }

    private int getNextClozeIndex() {
        // BUG: This assumes all fields are inserted as: {{cloze:Text}}
        List<String> fieldValues = new ArrayList<>(mEditFields.size());
        if (!ListenerUtil.mutListener.listen(10348)) {
            {
                long _loopCounter169 = 0;
                for (FieldEditText e : mEditFields) {
                    ListenerUtil.loopListener.listen("_loopCounter169", ++_loopCounter169);
                    Editable editable = e.getText();
                    String fieldValue = editable == null ? "" : editable.toString();
                    if (!ListenerUtil.mutListener.listen(10347)) {
                        fieldValues.add(fieldValue);
                    }
                }
            }
        }
        return ClozeUtils.getNextClozeIndex(fieldValues);
    }

    private boolean isClozeType() {
        return getCurrentlySelectedModel().isCloze();
    }

    @VisibleForTesting
    void startAdvancedTextEditor(int index) {
        TextField field = new TextField();
        if (!ListenerUtil.mutListener.listen(10349)) {
            field.setText(getCurrentFieldText(index));
        }
        if (!ListenerUtil.mutListener.listen(10350)) {
            startMultimediaFieldEditorForField(index, field);
        }
    }

    @VisibleForTesting
    void setFieldValueFromUi(int i, String newText) {
        FieldEditText editText = mEditFields.get(i);
        if (!ListenerUtil.mutListener.listen(10351)) {
            editText.setText(newText);
        }
        if (!ListenerUtil.mutListener.listen(10352)) {
            new EditFieldTextWatcher(i).afterTextChanged(editText.getText());
        }
    }

    @VisibleForTesting
    long getDeckId() {
        return mCurrentDid;
    }

    @SuppressWarnings("SameParameterValue")
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    FieldEditText getFieldForTest(int index) {
        return mEditFields.get(index);
    }

    private class EditFieldTextWatcher implements TextWatcher {

        private final int mIndex;

        public EditFieldTextWatcher(int index) {
            this.mIndex = index;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            if (!ListenerUtil.mutListener.listen(10353)) {
                mFieldEdited = true;
            }
            if (!ListenerUtil.mutListener.listen(10360)) {
                if ((ListenerUtil.mutListener.listen(10358) ? (mIndex >= 0) : (ListenerUtil.mutListener.listen(10357) ? (mIndex <= 0) : (ListenerUtil.mutListener.listen(10356) ? (mIndex > 0) : (ListenerUtil.mutListener.listen(10355) ? (mIndex < 0) : (ListenerUtil.mutListener.listen(10354) ? (mIndex != 0) : (mIndex == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(10359)) {
                        setDuplicateFieldStyles();
                    }
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    }

    private static class Field {

        private final JSONObject mField;

        private final Collection mCol;

        public Field(JSONObject fieldObject, Collection collection) {
            this.mField = fieldObject;
            this.mCol = collection;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void setHintLocale(@NonNull Locale selectedLocale) {
            String input = selectedLocale.toLanguageTag();
            if (!ListenerUtil.mutListener.listen(10361)) {
                mField.put("ad-hint-locale", input);
            }
            try {
                if (!ListenerUtil.mutListener.listen(10363)) {
                    mCol.getModels().save();
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(10362)) {
                    Timber.w(e, "Failed to save hint locale");
                }
            }
        }
    }
}
