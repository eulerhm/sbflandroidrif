/**
 * ************************************************************************************
 *  Copyright (c) 2020 Mike Hardy <mike@mikehardy.net>                                   *
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

import android.os.Bundle;
import android.view.View;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.utils.NoteUtils;
import com.ichi2.themes.Themes;
import com.ichi2.utils.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.RIGHT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The card template previewer intent must supply one or more cards to show and the index in the list from where
 * to begin showing them. Special rules are applied if the list size is 1 (i.e., no scrolling
 * buttons will be shown).
 */
public class CardTemplatePreviewer extends AbstractFlashcardViewer {

    private String mEditedModelFileName = null;

    private Model mEditedModel = null;

    private int mOrdinal;

    @Nullable
    private long[] mCardList;

    private Bundle mNoteEditorBundle = null;

    private boolean mShowingAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6590)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6591)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(6592)) {
            super.onCreate(savedInstanceState);
        }
        Bundle parameters = savedInstanceState;
        if (!ListenerUtil.mutListener.listen(6594)) {
            if (parameters == null) {
                if (!ListenerUtil.mutListener.listen(6593)) {
                    parameters = getIntent().getExtras();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6600)) {
            if (parameters != null) {
                if (!ListenerUtil.mutListener.listen(6595)) {
                    mNoteEditorBundle = parameters.getBundle("noteEditorBundle");
                }
                if (!ListenerUtil.mutListener.listen(6596)) {
                    mEditedModelFileName = parameters.getString(TemporaryModel.INTENT_MODEL_FILENAME);
                }
                if (!ListenerUtil.mutListener.listen(6597)) {
                    mCardList = parameters.getLongArray("cardList");
                }
                if (!ListenerUtil.mutListener.listen(6598)) {
                    mOrdinal = parameters.getInt("ordinal");
                }
                if (!ListenerUtil.mutListener.listen(6599)) {
                    mShowingAnswer = parameters.getBoolean("showingAnswer", mShowingAnswer);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6605)) {
            if (mEditedModelFileName != null) {
                if (!ListenerUtil.mutListener.listen(6601)) {
                    Timber.d("onCreate() loading edited model from %s", mEditedModelFileName);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(6604)) {
                        mEditedModel = TemporaryModel.getTempModel(mEditedModelFileName);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(6602)) {
                        Timber.w(e, "Unable to load temp model from file %s", mEditedModelFileName);
                    }
                    if (!ListenerUtil.mutListener.listen(6603)) {
                        closeCardTemplatePreviewer();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6617)) {
            if ((ListenerUtil.mutListener.listen(6611) ? (mEditedModel != null || (ListenerUtil.mutListener.listen(6610) ? (mOrdinal >= -1) : (ListenerUtil.mutListener.listen(6609) ? (mOrdinal <= -1) : (ListenerUtil.mutListener.listen(6608) ? (mOrdinal > -1) : (ListenerUtil.mutListener.listen(6607) ? (mOrdinal < -1) : (ListenerUtil.mutListener.listen(6606) ? (mOrdinal == -1) : (mOrdinal != -1))))))) : (mEditedModel != null && (ListenerUtil.mutListener.listen(6610) ? (mOrdinal >= -1) : (ListenerUtil.mutListener.listen(6609) ? (mOrdinal <= -1) : (ListenerUtil.mutListener.listen(6608) ? (mOrdinal > -1) : (ListenerUtil.mutListener.listen(6607) ? (mOrdinal < -1) : (ListenerUtil.mutListener.listen(6606) ? (mOrdinal == -1) : (mOrdinal != -1))))))))) {
                if (!ListenerUtil.mutListener.listen(6612)) {
                    Timber.d("onCreate() CardTemplatePreviewer started with edited model and template index, displaying blank to preview formatting");
                }
                if (!ListenerUtil.mutListener.listen(6613)) {
                    mCurrentCard = getDummyCard(mEditedModel, mOrdinal);
                }
                if (!ListenerUtil.mutListener.listen(6616)) {
                    if (mCurrentCard == null) {
                        if (!ListenerUtil.mutListener.listen(6614)) {
                            UIUtils.showThemedToast(getApplicationContext(), getString(R.string.invalid_template), false);
                        }
                        if (!ListenerUtil.mutListener.listen(6615)) {
                            closeCardTemplatePreviewer();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6618)) {
            showBackIcon();
        }
        if (!ListenerUtil.mutListener.listen(6619)) {
            // Ensure navigation drawer can't be opened. Various actions in the drawer cause crashes.
            disableDrawerSwipe();
        }
        if (!ListenerUtil.mutListener.listen(6620)) {
            startLoadingCollection();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(6621)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(6630)) {
            if ((ListenerUtil.mutListener.listen(6627) ? (mCurrentCard == null && (ListenerUtil.mutListener.listen(6626) ? (mOrdinal >= 0) : (ListenerUtil.mutListener.listen(6625) ? (mOrdinal <= 0) : (ListenerUtil.mutListener.listen(6624) ? (mOrdinal > 0) : (ListenerUtil.mutListener.listen(6623) ? (mOrdinal != 0) : (ListenerUtil.mutListener.listen(6622) ? (mOrdinal == 0) : (mOrdinal < 0))))))) : (mCurrentCard == null || (ListenerUtil.mutListener.listen(6626) ? (mOrdinal >= 0) : (ListenerUtil.mutListener.listen(6625) ? (mOrdinal <= 0) : (ListenerUtil.mutListener.listen(6624) ? (mOrdinal > 0) : (ListenerUtil.mutListener.listen(6623) ? (mOrdinal != 0) : (ListenerUtil.mutListener.listen(6622) ? (mOrdinal == 0) : (mOrdinal < 0))))))))) {
                if (!ListenerUtil.mutListener.listen(6628)) {
                    Timber.e("CardTemplatePreviewer started with empty card list or invalid index");
                }
                if (!ListenerUtil.mutListener.listen(6629)) {
                    closeCardTemplatePreviewer();
                }
            }
        }
    }

    private void closeCardTemplatePreviewer() {
        if (!ListenerUtil.mutListener.listen(6631)) {
            Timber.d("CardTemplatePreviewer:: closeCardTemplatePreviewer()");
        }
        if (!ListenerUtil.mutListener.listen(6632)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(6633)) {
            TemporaryModel.clearTempModelFiles();
        }
        if (!ListenerUtil.mutListener.listen(6634)) {
            finishWithAnimation(RIGHT);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(6635)) {
            Timber.i("CardTemplatePreviewer:: onBackPressed()");
        }
        if (!ListenerUtil.mutListener.listen(6636)) {
            closeCardTemplatePreviewer();
        }
    }

    @Override
    protected void performReload() {
        if (!ListenerUtil.mutListener.listen(6637)) {
            // This should not happen.
            finishWithAnimation(RIGHT);
        }
    }

    @Override
    protected void onNavigationPressed() {
        if (!ListenerUtil.mutListener.listen(6638)) {
            Timber.i("CardTemplatePreviewer:: Navigation button pressed");
        }
        if (!ListenerUtil.mutListener.listen(6639)) {
            closeCardTemplatePreviewer();
        }
    }

    @Override
    protected void setTitle() {
        if (!ListenerUtil.mutListener.listen(6641)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(6640)) {
                    getSupportActionBar().setTitle(R.string.preview_title);
                }
            }
        }
    }

    @Override
    protected void initLayout() {
        if (!ListenerUtil.mutListener.listen(6642)) {
            super.initLayout();
        }
        if (!ListenerUtil.mutListener.listen(6643)) {
            mTopBarLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6644)) {
            findViewById(R.id.answer_options_layout).setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6645)) {
            mPreviewButtonsLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(6646)) {
            mPreviewButtonsLayout.setOnClickListener(mToggleAnswerHandler);
        }
        if (!ListenerUtil.mutListener.listen(6647)) {
            mPreviewPrevCard.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6648)) {
            mPreviewNextCard.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6652)) {
            if (animationEnabled()) {
                int resId = Themes.getResFromAttr(this, R.attr.hardButtonRippleRef);
                if (!ListenerUtil.mutListener.listen(6649)) {
                    mPreviewButtonsLayout.setBackgroundResource(resId);
                }
                if (!ListenerUtil.mutListener.listen(6650)) {
                    mPreviewPrevCard.setBackgroundResource(resId);
                }
                if (!ListenerUtil.mutListener.listen(6651)) {
                    mPreviewNextCard.setBackgroundResource(resId);
                }
            }
        }
    }

    @Override
    protected void displayCardQuestion() {
        if (!ListenerUtil.mutListener.listen(6653)) {
            super.displayCardQuestion();
        }
        if (!ListenerUtil.mutListener.listen(6654)) {
            mShowingAnswer = false;
        }
        if (!ListenerUtil.mutListener.listen(6655)) {
            updateButtonsState();
        }
    }

    @Override
    protected void displayCardAnswer() {
        if (!ListenerUtil.mutListener.listen(6656)) {
            super.displayCardAnswer();
        }
        if (!ListenerUtil.mutListener.listen(6657)) {
            mShowingAnswer = true;
        }
        if (!ListenerUtil.mutListener.listen(6658)) {
            updateButtonsState();
        }
    }

    @Override
    protected void hideEaseButtons() {
    }

    @Override
    protected void displayAnswerBottomBar() {
    }

    private final View.OnClickListener mToggleAnswerHandler = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(6661)) {
                if (mShowingAnswer) {
                    if (!ListenerUtil.mutListener.listen(6660)) {
                        displayCardQuestion();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(6659)) {
                        displayCardAnswer();
                    }
                }
            }
        }
    };

    private void updateButtonsState() {
        if (!ListenerUtil.mutListener.listen(6662)) {
            mPreviewToggleAnswerText.setText(mShowingAnswer ? R.string.hide_answer : R.string.show_answer);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6663)) {
            outState.putString(TemporaryModel.INTENT_MODEL_FILENAME, mEditedModelFileName);
        }
        if (!ListenerUtil.mutListener.listen(6664)) {
            outState.putLongArray("cardList", mCardList);
        }
        if (!ListenerUtil.mutListener.listen(6665)) {
            outState.putInt("ordinal", mOrdinal);
        }
        if (!ListenerUtil.mutListener.listen(6666)) {
            outState.putBundle("noteEditorBundle", mNoteEditorBundle);
        }
        if (!ListenerUtil.mutListener.listen(6667)) {
            outState.putBoolean("showingAnswer", mShowingAnswer);
        }
        if (!ListenerUtil.mutListener.listen(6668)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(6669)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(6673)) {
            if ((ListenerUtil.mutListener.listen(6670) ? ((mCurrentCard == null) || (mCardList == null)) : ((mCurrentCard == null) && (mCardList == null)))) {
                if (!ListenerUtil.mutListener.listen(6671)) {
                    Timber.d("onCollectionLoaded - incorrect state to load, closing");
                }
                if (!ListenerUtil.mutListener.listen(6672)) {
                    closeCardTemplatePreviewer();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6687)) {
            if ((ListenerUtil.mutListener.listen(6685) ? ((ListenerUtil.mutListener.listen(6679) ? (mCardList != null || (ListenerUtil.mutListener.listen(6678) ? (mOrdinal <= 0) : (ListenerUtil.mutListener.listen(6677) ? (mOrdinal > 0) : (ListenerUtil.mutListener.listen(6676) ? (mOrdinal < 0) : (ListenerUtil.mutListener.listen(6675) ? (mOrdinal != 0) : (ListenerUtil.mutListener.listen(6674) ? (mOrdinal == 0) : (mOrdinal >= 0))))))) : (mCardList != null && (ListenerUtil.mutListener.listen(6678) ? (mOrdinal <= 0) : (ListenerUtil.mutListener.listen(6677) ? (mOrdinal > 0) : (ListenerUtil.mutListener.listen(6676) ? (mOrdinal < 0) : (ListenerUtil.mutListener.listen(6675) ? (mOrdinal != 0) : (ListenerUtil.mutListener.listen(6674) ? (mOrdinal == 0) : (mOrdinal >= 0)))))))) || (ListenerUtil.mutListener.listen(6684) ? (mOrdinal >= mCardList.length) : (ListenerUtil.mutListener.listen(6683) ? (mOrdinal <= mCardList.length) : (ListenerUtil.mutListener.listen(6682) ? (mOrdinal > mCardList.length) : (ListenerUtil.mutListener.listen(6681) ? (mOrdinal != mCardList.length) : (ListenerUtil.mutListener.listen(6680) ? (mOrdinal == mCardList.length) : (mOrdinal < mCardList.length))))))) : ((ListenerUtil.mutListener.listen(6679) ? (mCardList != null || (ListenerUtil.mutListener.listen(6678) ? (mOrdinal <= 0) : (ListenerUtil.mutListener.listen(6677) ? (mOrdinal > 0) : (ListenerUtil.mutListener.listen(6676) ? (mOrdinal < 0) : (ListenerUtil.mutListener.listen(6675) ? (mOrdinal != 0) : (ListenerUtil.mutListener.listen(6674) ? (mOrdinal == 0) : (mOrdinal >= 0))))))) : (mCardList != null && (ListenerUtil.mutListener.listen(6678) ? (mOrdinal <= 0) : (ListenerUtil.mutListener.listen(6677) ? (mOrdinal > 0) : (ListenerUtil.mutListener.listen(6676) ? (mOrdinal < 0) : (ListenerUtil.mutListener.listen(6675) ? (mOrdinal != 0) : (ListenerUtil.mutListener.listen(6674) ? (mOrdinal == 0) : (mOrdinal >= 0)))))))) && (ListenerUtil.mutListener.listen(6684) ? (mOrdinal >= mCardList.length) : (ListenerUtil.mutListener.listen(6683) ? (mOrdinal <= mCardList.length) : (ListenerUtil.mutListener.listen(6682) ? (mOrdinal > mCardList.length) : (ListenerUtil.mutListener.listen(6681) ? (mOrdinal != mCardList.length) : (ListenerUtil.mutListener.listen(6680) ? (mOrdinal == mCardList.length) : (mOrdinal < mCardList.length))))))))) {
                if (!ListenerUtil.mutListener.listen(6686)) {
                    mCurrentCard = new PreviewerCard(col, mCardList[mOrdinal]);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6701)) {
            if (mNoteEditorBundle != null) {
                long newDid = mNoteEditorBundle.getLong("did");
                if (!ListenerUtil.mutListener.listen(6689)) {
                    if (col.getDecks().isDyn(newDid)) {
                        if (!ListenerUtil.mutListener.listen(6688)) {
                            mCurrentCard.setODid(mCurrentCard.getDid());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6690)) {
                    mCurrentCard.setDid(newDid);
                }
                Note currentNote = mCurrentCard.note();
                ArrayList<String> tagsList = mNoteEditorBundle.getStringArrayList("tags");
                if (!ListenerUtil.mutListener.listen(6691)) {
                    NoteUtils.setTags(currentNote, tagsList);
                }
                Bundle noteFields = mNoteEditorBundle.getBundle("editFields");
                if (!ListenerUtil.mutListener.listen(6700)) {
                    if (noteFields != null) {
                        if (!ListenerUtil.mutListener.listen(6699)) {
                            {
                                long _loopCounter118 = 0;
                                for (String fieldOrd : noteFields.keySet()) {
                                    ListenerUtil.loopListener.listen("_loopCounter118", ++_loopCounter118);
                                    // In case the fields on the card are out of sync with the bundle
                                    int fieldOrdInt = Integer.parseInt(fieldOrd);
                                    if (!ListenerUtil.mutListener.listen(6698)) {
                                        if ((ListenerUtil.mutListener.listen(6696) ? (fieldOrdInt >= currentNote.getFields().length) : (ListenerUtil.mutListener.listen(6695) ? (fieldOrdInt <= currentNote.getFields().length) : (ListenerUtil.mutListener.listen(6694) ? (fieldOrdInt > currentNote.getFields().length) : (ListenerUtil.mutListener.listen(6693) ? (fieldOrdInt != currentNote.getFields().length) : (ListenerUtil.mutListener.listen(6692) ? (fieldOrdInt == currentNote.getFields().length) : (fieldOrdInt < currentNote.getFields().length))))))) {
                                            if (!ListenerUtil.mutListener.listen(6697)) {
                                                currentNote.setField(fieldOrdInt, noteFields.getString(fieldOrd));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6702)) {
            displayCardQuestion();
        }
        if (!ListenerUtil.mutListener.listen(6704)) {
            if (mShowingAnswer) {
                if (!ListenerUtil.mutListener.listen(6703)) {
                    displayCardAnswer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6705)) {
            showBackIcon();
        }
    }

    protected Card getCard(Collection col, long cardListIndex) {
        return new PreviewerCard(col, cardListIndex);
    }

    /**
     * Get a dummy card
     */
    @Nullable
    protected Card getDummyCard(Model model, int ordinal) {
        if (!ListenerUtil.mutListener.listen(6706)) {
            Timber.d("getDummyCard() Creating dummy note for ordinal %s", ordinal);
        }
        if (!ListenerUtil.mutListener.listen(6707)) {
            if (model == null) {
                return null;
            }
        }
        Note n = getCol().newNote(model);
        List<String> fieldNames = model.getFieldsNames();
        if (!ListenerUtil.mutListener.listen(6720)) {
            {
                long _loopCounter119 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(6719) ? ((ListenerUtil.mutListener.listen(6713) ? (i >= fieldNames.size()) : (ListenerUtil.mutListener.listen(6712) ? (i <= fieldNames.size()) : (ListenerUtil.mutListener.listen(6711) ? (i > fieldNames.size()) : (ListenerUtil.mutListener.listen(6710) ? (i != fieldNames.size()) : (ListenerUtil.mutListener.listen(6709) ? (i == fieldNames.size()) : (i < fieldNames.size())))))) || (ListenerUtil.mutListener.listen(6718) ? (i >= n.getFields().length) : (ListenerUtil.mutListener.listen(6717) ? (i <= n.getFields().length) : (ListenerUtil.mutListener.listen(6716) ? (i > n.getFields().length) : (ListenerUtil.mutListener.listen(6715) ? (i != n.getFields().length) : (ListenerUtil.mutListener.listen(6714) ? (i == n.getFields().length) : (i < n.getFields().length))))))) : ((ListenerUtil.mutListener.listen(6713) ? (i >= fieldNames.size()) : (ListenerUtil.mutListener.listen(6712) ? (i <= fieldNames.size()) : (ListenerUtil.mutListener.listen(6711) ? (i > fieldNames.size()) : (ListenerUtil.mutListener.listen(6710) ? (i != fieldNames.size()) : (ListenerUtil.mutListener.listen(6709) ? (i == fieldNames.size()) : (i < fieldNames.size())))))) && (ListenerUtil.mutListener.listen(6718) ? (i >= n.getFields().length) : (ListenerUtil.mutListener.listen(6717) ? (i <= n.getFields().length) : (ListenerUtil.mutListener.listen(6716) ? (i > n.getFields().length) : (ListenerUtil.mutListener.listen(6715) ? (i != n.getFields().length) : (ListenerUtil.mutListener.listen(6714) ? (i == n.getFields().length) : (i < n.getFields().length)))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter119", ++_loopCounter119);
                    if (!ListenerUtil.mutListener.listen(6708)) {
                        n.setField(i, fieldNames.get(i));
                    }
                }
            }
        }
        try {
            JSONObject template = model.getJSONArray("tmpls").getJSONObject(ordinal);
            PreviewerCard card = (PreviewerCard) getCol().getNewLinkedCard(new PreviewerCard(getCol()), n, template, 1, 0L, false);
            if (!ListenerUtil.mutListener.listen(6722)) {
                card.setNote(n);
            }
            return card;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6721)) {
                Timber.e("getDummyCard() unable to create card");
            }
        }
        return null;
    }

    /**
     * Override certain aspects of Card behavior so we may display unsaved data
     */
    public class PreviewerCard extends Card {

        private Note mNote;

        private PreviewerCard(Collection col) {
            super(col);
        }

        private PreviewerCard(Collection col, long id) {
            super(col, id);
        }

        @Override
        public /* if we have an unsaved note saved, use it instead of a collection lookup */
        Note note(boolean reload) {
            if (!ListenerUtil.mutListener.listen(6723)) {
                if (mNote != null) {
                    return mNote;
                }
            }
            return super.note(reload);
        }

        /**
         * if we have an unsaved note saved, use it instead of a collection lookup
         */
        @Override
        public Note note() {
            if (!ListenerUtil.mutListener.listen(6724)) {
                if (mNote != null) {
                    return mNote;
                }
            }
            return super.note();
        }

        /**
         * set an unsaved note to use for rendering
         */
        public void setNote(Note note) {
            if (!ListenerUtil.mutListener.listen(6725)) {
                mNote = note;
            }
        }

        /**
         * if we have an unsaved note, never return empty
         */
        @Override
        public boolean isEmpty() {
            if (!ListenerUtil.mutListener.listen(6726)) {
                if (mNote != null) {
                    return false;
                }
            }
            return super.isEmpty();
        }

        /**
         * Override the method that fetches the model so we can render unsaved models
         */
        @Override
        public Model model() {
            if (!ListenerUtil.mutListener.listen(6727)) {
                if (mEditedModel != null) {
                    return mEditedModel;
                }
            }
            return super.model();
        }
    }
}
