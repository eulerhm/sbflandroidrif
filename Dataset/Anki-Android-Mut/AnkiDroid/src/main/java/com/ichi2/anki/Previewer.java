/**
 * ************************************************************************************
 *  Copyright (c) 2011 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2013 Jolta Technologies                                                *
 *  Copyright (c) 2014 Bruno Romero de Azevedo <brunodea@inf.ufsm.br>                    *
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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Utils;
import com.ichi2.themes.Themes;
import java.util.HashSet;
import java.util.List;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The previewer intent must supply an array of cards to show and the index in the list from where
 * to begin showing them. Special rules are applied if the list size is 1 (i.e., no scrolling
 * buttons will be shown).
 */
public class Previewer extends AbstractFlashcardViewer {

    private long[] mCardList;

    private int mIndex;

    private boolean mShowingAnswer;

    /**
     * Communication with Browser
     */
    private boolean mReloadRequired;

    private boolean mNoteChanged;

    @CheckResult
    @NonNull
    public static Intent getPreviewIntent(Context context, int index, long[] cardList) {
        Intent intent = new Intent(context, Previewer.class);
        if (!ListenerUtil.mutListener.listen(10727)) {
            intent.putExtra("index", index);
        }
        if (!ListenerUtil.mutListener.listen(10728)) {
            intent.putExtra("cardList", cardList);
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10729)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10730)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(10731)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10732)) {
            mCardList = getIntent().getLongArrayExtra("cardList");
        }
        if (!ListenerUtil.mutListener.listen(10733)) {
            mIndex = getIntent().getIntExtra("index", -1);
        }
        if (!ListenerUtil.mutListener.listen(10738)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(10734)) {
                    mIndex = savedInstanceState.getInt("index", mIndex);
                }
                if (!ListenerUtil.mutListener.listen(10735)) {
                    mShowingAnswer = savedInstanceState.getBoolean("showingAnswer", mShowingAnswer);
                }
                if (!ListenerUtil.mutListener.listen(10736)) {
                    mReloadRequired = savedInstanceState.getBoolean("reloadRequired");
                }
                if (!ListenerUtil.mutListener.listen(10737)) {
                    mNoteChanged = savedInstanceState.getBoolean("noteChanged");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10762)) {
            if ((ListenerUtil.mutListener.listen(10759) ? ((ListenerUtil.mutListener.listen(10749) ? ((ListenerUtil.mutListener.listen(10743) ? (mCardList.length >= 0) : (ListenerUtil.mutListener.listen(10742) ? (mCardList.length <= 0) : (ListenerUtil.mutListener.listen(10741) ? (mCardList.length > 0) : (ListenerUtil.mutListener.listen(10740) ? (mCardList.length < 0) : (ListenerUtil.mutListener.listen(10739) ? (mCardList.length != 0) : (mCardList.length == 0)))))) && (ListenerUtil.mutListener.listen(10748) ? (mIndex >= 0) : (ListenerUtil.mutListener.listen(10747) ? (mIndex <= 0) : (ListenerUtil.mutListener.listen(10746) ? (mIndex > 0) : (ListenerUtil.mutListener.listen(10745) ? (mIndex != 0) : (ListenerUtil.mutListener.listen(10744) ? (mIndex == 0) : (mIndex < 0))))))) : ((ListenerUtil.mutListener.listen(10743) ? (mCardList.length >= 0) : (ListenerUtil.mutListener.listen(10742) ? (mCardList.length <= 0) : (ListenerUtil.mutListener.listen(10741) ? (mCardList.length > 0) : (ListenerUtil.mutListener.listen(10740) ? (mCardList.length < 0) : (ListenerUtil.mutListener.listen(10739) ? (mCardList.length != 0) : (mCardList.length == 0)))))) || (ListenerUtil.mutListener.listen(10748) ? (mIndex >= 0) : (ListenerUtil.mutListener.listen(10747) ? (mIndex <= 0) : (ListenerUtil.mutListener.listen(10746) ? (mIndex > 0) : (ListenerUtil.mutListener.listen(10745) ? (mIndex != 0) : (ListenerUtil.mutListener.listen(10744) ? (mIndex == 0) : (mIndex < 0)))))))) && (ListenerUtil.mutListener.listen(10758) ? (mIndex >= (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10757) ? (mIndex <= (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10756) ? (mIndex < (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10755) ? (mIndex != (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10754) ? (mIndex == (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (mIndex > (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))))))))) : ((ListenerUtil.mutListener.listen(10749) ? ((ListenerUtil.mutListener.listen(10743) ? (mCardList.length >= 0) : (ListenerUtil.mutListener.listen(10742) ? (mCardList.length <= 0) : (ListenerUtil.mutListener.listen(10741) ? (mCardList.length > 0) : (ListenerUtil.mutListener.listen(10740) ? (mCardList.length < 0) : (ListenerUtil.mutListener.listen(10739) ? (mCardList.length != 0) : (mCardList.length == 0)))))) && (ListenerUtil.mutListener.listen(10748) ? (mIndex >= 0) : (ListenerUtil.mutListener.listen(10747) ? (mIndex <= 0) : (ListenerUtil.mutListener.listen(10746) ? (mIndex > 0) : (ListenerUtil.mutListener.listen(10745) ? (mIndex != 0) : (ListenerUtil.mutListener.listen(10744) ? (mIndex == 0) : (mIndex < 0))))))) : ((ListenerUtil.mutListener.listen(10743) ? (mCardList.length >= 0) : (ListenerUtil.mutListener.listen(10742) ? (mCardList.length <= 0) : (ListenerUtil.mutListener.listen(10741) ? (mCardList.length > 0) : (ListenerUtil.mutListener.listen(10740) ? (mCardList.length < 0) : (ListenerUtil.mutListener.listen(10739) ? (mCardList.length != 0) : (mCardList.length == 0)))))) || (ListenerUtil.mutListener.listen(10748) ? (mIndex >= 0) : (ListenerUtil.mutListener.listen(10747) ? (mIndex <= 0) : (ListenerUtil.mutListener.listen(10746) ? (mIndex > 0) : (ListenerUtil.mutListener.listen(10745) ? (mIndex != 0) : (ListenerUtil.mutListener.listen(10744) ? (mIndex == 0) : (mIndex < 0)))))))) || (ListenerUtil.mutListener.listen(10758) ? (mIndex >= (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10757) ? (mIndex <= (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10756) ? (mIndex < (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10755) ? (mIndex != (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10754) ? (mIndex == (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (mIndex > (ListenerUtil.mutListener.listen(10753) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10752) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10751) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10750) ? (mCardList.length + 1) : (mCardList.length - 1)))))))))))))) {
                if (!ListenerUtil.mutListener.listen(10760)) {
                    Timber.e("Previewer started with empty card list or invalid index");
                }
                if (!ListenerUtil.mutListener.listen(10761)) {
                    finishWithoutAnimation();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10763)) {
            showBackIcon();
        }
        if (!ListenerUtil.mutListener.listen(10764)) {
            // Ensure navigation drawer can't be opened. Various actions in the drawer cause crashes.
            disableDrawerSwipe();
        }
        if (!ListenerUtil.mutListener.listen(10765)) {
            startLoadingCollection();
        }
    }

    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(10766)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(10767)) {
            mCurrentCard = col.getCard(mCardList[mIndex]);
        }
        if (!ListenerUtil.mutListener.listen(10768)) {
            displayCardQuestion();
        }
        if (!ListenerUtil.mutListener.listen(10770)) {
            if (mShowingAnswer) {
                if (!ListenerUtil.mutListener.listen(10769)) {
                    displayCardAnswer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10771)) {
            showBackIcon();
        }
    }

    /**
     * Given a new collection of card Ids, find the 'best' valid card given the current collection
     * We define the best as searching to the left, then searching to the right of the current element
     * This occurs as many cards can be deleted when editing a note (from the Card Template Editor)
     */
    private int getNextIndex(List<Long> newCardList) {
        HashSet<Long> validIndices = new HashSet<>(newCardList);
        {
            long _loopCounter180 = 0;
            for (int i = mIndex; (ListenerUtil.mutListener.listen(10776) ? (i <= 0) : (ListenerUtil.mutListener.listen(10775) ? (i > 0) : (ListenerUtil.mutListener.listen(10774) ? (i < 0) : (ListenerUtil.mutListener.listen(10773) ? (i != 0) : (ListenerUtil.mutListener.listen(10772) ? (i == 0) : (i >= 0)))))); i--) {
                ListenerUtil.loopListener.listen("_loopCounter180", ++_loopCounter180);
                if (validIndices.contains(mCardList[i])) {
                    return newCardList.indexOf(mCardList[i]);
                }
            }
        }
        {
            long _loopCounter181 = 0;
            for (int i = (ListenerUtil.mutListener.listen(10785) ? (mIndex % 1) : (ListenerUtil.mutListener.listen(10784) ? (mIndex / 1) : (ListenerUtil.mutListener.listen(10783) ? (mIndex * 1) : (ListenerUtil.mutListener.listen(10782) ? (mIndex - 1) : (mIndex + 1))))); (ListenerUtil.mutListener.listen(10781) ? (i >= validIndices.size()) : (ListenerUtil.mutListener.listen(10780) ? (i <= validIndices.size()) : (ListenerUtil.mutListener.listen(10779) ? (i > validIndices.size()) : (ListenerUtil.mutListener.listen(10778) ? (i != validIndices.size()) : (ListenerUtil.mutListener.listen(10777) ? (i == validIndices.size()) : (i < validIndices.size())))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter181", ++_loopCounter181);
                if (validIndices.contains(mCardList[i])) {
                    return newCardList.indexOf(mCardList[i]);
                }
            }
        }
        throw new IllegalStateException("newCardList was empty");
    }

    @Override
    protected void setTitle() {
        if (!ListenerUtil.mutListener.listen(10786)) {
            getSupportActionBar().setTitle(R.string.preview_title);
        }
    }

    @Override
    protected void initLayout() {
        if (!ListenerUtil.mutListener.listen(10787)) {
            super.initLayout();
        }
        if (!ListenerUtil.mutListener.listen(10788)) {
            mTopBarLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(10789)) {
            findViewById(R.id.answer_options_layout).setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(10790)) {
            mPreviewButtonsLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(10791)) {
            mPreviewButtonsLayout.setOnClickListener(mToggleAnswerHandler);
        }
        if (!ListenerUtil.mutListener.listen(10792)) {
            mPreviewPrevCard.setOnClickListener(mSelectScrollHandler);
        }
        if (!ListenerUtil.mutListener.listen(10793)) {
            mPreviewNextCard.setOnClickListener(mSelectScrollHandler);
        }
        if (!ListenerUtil.mutListener.listen(10797)) {
            if (animationEnabled()) {
                int resId = Themes.getResFromAttr(this, R.attr.hardButtonRippleRef);
                if (!ListenerUtil.mutListener.listen(10794)) {
                    mPreviewButtonsLayout.setBackgroundResource(resId);
                }
                if (!ListenerUtil.mutListener.listen(10795)) {
                    mPreviewPrevCard.setBackgroundResource(R.drawable.item_background_light_selectable_borderless);
                }
                if (!ListenerUtil.mutListener.listen(10796)) {
                    mPreviewNextCard.setBackgroundResource(R.drawable.item_background_light_selectable_borderless);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(10799)) {
            if (item.getItemId() == R.id.action_edit) {
                if (!ListenerUtil.mutListener.listen(10798)) {
                    editCard();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(10800)) {
            setResult(RESULT_OK, getResultIntent());
        }
        if (!ListenerUtil.mutListener.listen(10801)) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onNavigationPressed() {
        if (!ListenerUtil.mutListener.listen(10802)) {
            setResult(RESULT_OK, getResultIntent());
        }
        if (!ListenerUtil.mutListener.listen(10803)) {
            super.onNavigationPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(10804)) {
            getMenuInflater().inflate(R.menu.previewer, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10805)) {
            outState.putLongArray("cardList", mCardList);
        }
        if (!ListenerUtil.mutListener.listen(10806)) {
            outState.putInt("index", mIndex);
        }
        if (!ListenerUtil.mutListener.listen(10807)) {
            outState.putBoolean("showingAnswer", mShowingAnswer);
        }
        if (!ListenerUtil.mutListener.listen(10808)) {
            outState.putBoolean("reloadRequired", mReloadRequired);
        }
        if (!ListenerUtil.mutListener.listen(10809)) {
            outState.putBoolean("noteChanged", mNoteChanged);
        }
        if (!ListenerUtil.mutListener.listen(10810)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void displayCardQuestion() {
        if (!ListenerUtil.mutListener.listen(10811)) {
            super.displayCardQuestion();
        }
        if (!ListenerUtil.mutListener.listen(10812)) {
            mShowingAnswer = false;
        }
        if (!ListenerUtil.mutListener.listen(10813)) {
            updateButtonsState();
        }
    }

    // Called via mFlipCardListener in parent class when answer button pressed
    @Override
    protected void displayCardAnswer() {
        if (!ListenerUtil.mutListener.listen(10814)) {
            super.displayCardAnswer();
        }
        if (!ListenerUtil.mutListener.listen(10815)) {
            mShowingAnswer = true;
        }
        if (!ListenerUtil.mutListener.listen(10816)) {
            updateButtonsState();
        }
    }

    @Override
    protected void hideEaseButtons() {
    }

    @Override
    protected void displayAnswerBottomBar() {
    }

    @Override
    public boolean executeCommand(int which) {
        /* do nothing */
        return false;
    }

    @Override
    protected void performReload() {
        if (!ListenerUtil.mutListener.listen(10817)) {
            mReloadRequired = true;
        }
        List<Long> newCardList = getCol().filterToValidCards(mCardList);
        if (!ListenerUtil.mutListener.listen(10819)) {
            if (newCardList.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(10818)) {
                    finishWithoutAnimation();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10820)) {
            mIndex = getNextIndex(newCardList);
        }
        if (!ListenerUtil.mutListener.listen(10821)) {
            mCardList = Utils.collection2Array(newCardList);
        }
        if (!ListenerUtil.mutListener.listen(10822)) {
            mCurrentCard = getCol().getCard(mCardList[mIndex]);
        }
        if (!ListenerUtil.mutListener.listen(10823)) {
            displayCardQuestion();
        }
    }

    @Override
    protected void onEditedNoteChanged() {
        if (!ListenerUtil.mutListener.listen(10824)) {
            super.onEditedNoteChanged();
        }
        if (!ListenerUtil.mutListener.listen(10825)) {
            mNoteChanged = true;
        }
    }

    private final View.OnClickListener mSelectScrollHandler = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (!ListenerUtil.mutListener.listen(10828)) {
                if (view.getId() == R.id.preview_previous_flashcard) {
                    if (!ListenerUtil.mutListener.listen(10827)) {
                        mIndex--;
                    }
                } else if (view.getId() == R.id.preview_next_flashcard) {
                    if (!ListenerUtil.mutListener.listen(10826)) {
                        mIndex++;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10829)) {
                mCurrentCard = getCol().getCard(mCardList[mIndex]);
            }
            if (!ListenerUtil.mutListener.listen(10830)) {
                displayCardQuestion();
            }
        }
    };

    private final View.OnClickListener mToggleAnswerHandler = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(10833)) {
                if (mShowingAnswer) {
                    if (!ListenerUtil.mutListener.listen(10832)) {
                        displayCardQuestion();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(10831)) {
                        displayCardAnswer();
                    }
                }
            }
        }
    };

    private void updateButtonsState() {
        if (!ListenerUtil.mutListener.listen(10834)) {
            mPreviewToggleAnswerText.setText(mShowingAnswer ? R.string.hide_answer : R.string.show_answer);
        }
        if (!ListenerUtil.mutListener.listen(10842)) {
            // and hide navigation buttons.
            if ((ListenerUtil.mutListener.listen(10839) ? (mCardList.length >= 1) : (ListenerUtil.mutListener.listen(10838) ? (mCardList.length <= 1) : (ListenerUtil.mutListener.listen(10837) ? (mCardList.length > 1) : (ListenerUtil.mutListener.listen(10836) ? (mCardList.length < 1) : (ListenerUtil.mutListener.listen(10835) ? (mCardList.length != 1) : (mCardList.length == 1))))))) {
                if (!ListenerUtil.mutListener.listen(10840)) {
                    mPreviewPrevCard.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(10841)) {
                    mPreviewNextCard.setVisibility(View.GONE);
                }
                return;
            }
        }
        boolean prevBtnDisabled = (ListenerUtil.mutListener.listen(10847) ? (mIndex >= 0) : (ListenerUtil.mutListener.listen(10846) ? (mIndex > 0) : (ListenerUtil.mutListener.listen(10845) ? (mIndex < 0) : (ListenerUtil.mutListener.listen(10844) ? (mIndex != 0) : (ListenerUtil.mutListener.listen(10843) ? (mIndex == 0) : (mIndex <= 0))))));
        boolean nextBtnDisabled = (ListenerUtil.mutListener.listen(10856) ? (mIndex <= (ListenerUtil.mutListener.listen(10851) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10850) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10849) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10848) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10855) ? (mIndex > (ListenerUtil.mutListener.listen(10851) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10850) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10849) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10848) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10854) ? (mIndex < (ListenerUtil.mutListener.listen(10851) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10850) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10849) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10848) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10853) ? (mIndex != (ListenerUtil.mutListener.listen(10851) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10850) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10849) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10848) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (ListenerUtil.mutListener.listen(10852) ? (mIndex == (ListenerUtil.mutListener.listen(10851) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10850) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10849) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10848) ? (mCardList.length + 1) : (mCardList.length - 1)))))) : (mIndex >= (ListenerUtil.mutListener.listen(10851) ? (mCardList.length % 1) : (ListenerUtil.mutListener.listen(10850) ? (mCardList.length / 1) : (ListenerUtil.mutListener.listen(10849) ? (mCardList.length * 1) : (ListenerUtil.mutListener.listen(10848) ? (mCardList.length + 1) : (mCardList.length - 1)))))))))));
        if (!ListenerUtil.mutListener.listen(10857)) {
            mPreviewPrevCard.setEnabled(!prevBtnDisabled);
        }
        if (!ListenerUtil.mutListener.listen(10858)) {
            mPreviewNextCard.setEnabled(!nextBtnDisabled);
        }
        if (!ListenerUtil.mutListener.listen(10859)) {
            mPreviewPrevCard.setAlpha(prevBtnDisabled ? 0.38F : 1);
        }
        if (!ListenerUtil.mutListener.listen(10860)) {
            mPreviewNextCard.setAlpha(nextBtnDisabled ? 0.38F : 1);
        }
    }

    @NonNull
    private Intent getResultIntent() {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(10861)) {
            intent.putExtra("reloadRequired", mReloadRequired);
        }
        if (!ListenerUtil.mutListener.listen(10862)) {
            intent.putExtra("noteChanged", mNoteChanged);
        }
        return intent;
    }
}
