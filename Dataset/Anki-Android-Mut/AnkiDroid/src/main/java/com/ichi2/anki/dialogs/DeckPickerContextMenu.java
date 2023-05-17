/**
 * *************************************************************************************
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
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
package com.ichi2.anki.dialogs;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.AnkiActivity;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;
import com.ichi2.anki.StudyOptionsFragment;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import com.ichi2.libanki.Collection;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import timber.log.Timber;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckPickerContextMenu extends AnalyticsDialogFragment {

    /**
     * Context Menus
     */
    private static final int CONTEXT_MENU_RENAME_DECK = 0;

    private static final int CONTEXT_MENU_DECK_OPTIONS = 1;

    private static final int CONTEXT_MENU_CUSTOM_STUDY = 2;

    private static final int CONTEXT_MENU_DELETE_DECK = 3;

    private static final int CONTEXT_MENU_EXPORT_DECK = 4;

    private static final int CONTEXT_MENU_UNBURY = 5;

    private static final int CONTEXT_MENU_CUSTOM_STUDY_REBUILD = 6;

    private static final int CONTEXT_MENU_CUSTOM_STUDY_EMPTY = 7;

    private static final int CONTEXT_MENU_CREATE_SUBDECK = 8;

    private static final int CONTEXT_MENU_CREATE_SHORTCUT = 9;

    @Retention(SOURCE)
    @IntDef({ CONTEXT_MENU_RENAME_DECK, CONTEXT_MENU_DECK_OPTIONS, CONTEXT_MENU_CUSTOM_STUDY, CONTEXT_MENU_DELETE_DECK, CONTEXT_MENU_EXPORT_DECK, CONTEXT_MENU_UNBURY, CONTEXT_MENU_CUSTOM_STUDY_REBUILD, CONTEXT_MENU_CUSTOM_STUDY_EMPTY, CONTEXT_MENU_CREATE_SUBDECK, CONTEXT_MENU_CREATE_SHORTCUT })
    public @interface DECK_PICKER_CONTEXT_MENU {
    }

    public static DeckPickerContextMenu newInstance(long did) {
        DeckPickerContextMenu f = new DeckPickerContextMenu();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(596)) {
            args.putLong("did", did);
        }
        if (!ListenerUtil.mutListener.listen(597)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(598)) {
            super.onCreate(savedInstanceState);
        }
        long did = getArguments().getLong("did");
        String title = CollectionHelper.getInstance().getCol(getContext()).getDecks().name(did);
        int[] itemIds = getListIds();
        return new MaterialDialog.Builder(getActivity()).title(title).cancelable(true).autoDismiss(false).itemsIds(itemIds).items(ContextMenuHelper.getValuesFromKeys(getKeyValueMap(), itemIds)).itemsCallback(mContextMenuListener).build();
    }

    private HashMap<Integer, String> getKeyValueMap() {
        Resources res = getResources();
        HashMap<Integer, String> keyValueMap = new HashMap<>(9);
        if (!ListenerUtil.mutListener.listen(599)) {
            keyValueMap.put(CONTEXT_MENU_RENAME_DECK, res.getString(R.string.rename_deck));
        }
        if (!ListenerUtil.mutListener.listen(600)) {
            keyValueMap.put(CONTEXT_MENU_DECK_OPTIONS, res.getString(R.string.menu__deck_options));
        }
        if (!ListenerUtil.mutListener.listen(601)) {
            keyValueMap.put(CONTEXT_MENU_CUSTOM_STUDY, res.getString(R.string.custom_study));
        }
        if (!ListenerUtil.mutListener.listen(602)) {
            keyValueMap.put(CONTEXT_MENU_DELETE_DECK, res.getString(R.string.contextmenu_deckpicker_delete_deck));
        }
        if (!ListenerUtil.mutListener.listen(603)) {
            keyValueMap.put(CONTEXT_MENU_EXPORT_DECK, res.getString(R.string.export_deck));
        }
        if (!ListenerUtil.mutListener.listen(604)) {
            keyValueMap.put(CONTEXT_MENU_UNBURY, res.getString(R.string.unbury));
        }
        if (!ListenerUtil.mutListener.listen(605)) {
            keyValueMap.put(CONTEXT_MENU_CUSTOM_STUDY_REBUILD, res.getString(R.string.rebuild_cram_label));
        }
        if (!ListenerUtil.mutListener.listen(606)) {
            keyValueMap.put(CONTEXT_MENU_CUSTOM_STUDY_EMPTY, res.getString(R.string.empty_cram_label));
        }
        if (!ListenerUtil.mutListener.listen(607)) {
            keyValueMap.put(CONTEXT_MENU_CREATE_SUBDECK, res.getString(R.string.create_subdeck));
        }
        if (!ListenerUtil.mutListener.listen(608)) {
            keyValueMap.put(CONTEXT_MENU_CREATE_SHORTCUT, res.getString(R.string.create_shortcut));
        }
        return keyValueMap;
    }

    /**
     * Retrieve the list of ids to put in the context menu list
     * @return the ids of which values to show
     */
    @DECK_PICKER_CONTEXT_MENU
    private int[] getListIds() {
        Collection col = CollectionHelper.getInstance().getCol(getContext());
        long did = getArguments().getLong("did");
        ArrayList<Integer> itemIds = new ArrayList<>(9);
        if (!ListenerUtil.mutListener.listen(611)) {
            if (col.getDecks().isDyn(did)) {
                if (!ListenerUtil.mutListener.listen(609)) {
                    itemIds.add(CONTEXT_MENU_CUSTOM_STUDY_REBUILD);
                }
                if (!ListenerUtil.mutListener.listen(610)) {
                    itemIds.add(CONTEXT_MENU_CUSTOM_STUDY_EMPTY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(612)) {
            itemIds.add(CONTEXT_MENU_RENAME_DECK);
        }
        if (!ListenerUtil.mutListener.listen(613)) {
            itemIds.add(CONTEXT_MENU_CREATE_SUBDECK);
        }
        if (!ListenerUtil.mutListener.listen(614)) {
            itemIds.add(CONTEXT_MENU_DECK_OPTIONS);
        }
        if (!ListenerUtil.mutListener.listen(616)) {
            if (!col.getDecks().isDyn(did)) {
                if (!ListenerUtil.mutListener.listen(615)) {
                    itemIds.add(CONTEXT_MENU_CUSTOM_STUDY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(617)) {
            itemIds.add(CONTEXT_MENU_DELETE_DECK);
        }
        if (!ListenerUtil.mutListener.listen(618)) {
            itemIds.add(CONTEXT_MENU_EXPORT_DECK);
        }
        if (!ListenerUtil.mutListener.listen(620)) {
            if (col.getSched().haveBuried(did)) {
                if (!ListenerUtil.mutListener.listen(619)) {
                    itemIds.add(CONTEXT_MENU_UNBURY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(621)) {
            itemIds.add(CONTEXT_MENU_CREATE_SHORTCUT);
        }
        return ContextMenuHelper.integerListToArray(itemIds);
    }

    // Handle item selection on context menu which is shown when the user long-clicks on a deck
    private final MaterialDialog.ListCallback mContextMenuListener = (materialDialog, view, item, charSequence) -> {
        @DECK_PICKER_CONTEXT_MENU
        int id = view.getId();
        switch(id) {
            case CONTEXT_MENU_DELETE_DECK:
                Timber.i("Delete deck selected");
                ((DeckPicker) getActivity()).confirmDeckDeletion();
                break;
            case CONTEXT_MENU_DECK_OPTIONS:
                Timber.i("Open deck options selected");
                ((DeckPicker) getActivity()).showContextMenuDeckOptions();
                ((AnkiActivity) getActivity()).dismissAllDialogFragments();
                break;
            case CONTEXT_MENU_CUSTOM_STUDY:
                {
                    Timber.i("Custom study option selected");
                    long did = getArguments().getLong("did");
                    CustomStudyDialog d = CustomStudyDialog.newInstance(CustomStudyDialog.CONTEXT_MENU_STANDARD, did);
                    ((AnkiActivity) getActivity()).showDialogFragment(d);
                    break;
                }
            case CONTEXT_MENU_CREATE_SHORTCUT:
                Timber.i("Create icon for a deck");
                ((DeckPicker) getActivity()).createIcon(getContext());
                break;
            case CONTEXT_MENU_RENAME_DECK:
                Timber.i("Rename deck selected");
                ((DeckPicker) getActivity()).renameDeckDialog();
                break;
            case CONTEXT_MENU_EXPORT_DECK:
                Timber.i("Export deck selected");
                ((DeckPicker) getActivity()).showContextMenuExportDialog();
                break;
            case CONTEXT_MENU_UNBURY:
                {
                    Timber.i("Unbury deck selected");
                    Collection col = CollectionHelper.getInstance().getCol(getContext());
                    col.getSched().unburyCardsForDeck(getArguments().getLong("did"));
                    ((StudyOptionsFragment.StudyOptionsListener) getActivity()).onRequireDeckListUpdate();
                    ((AnkiActivity) getActivity()).dismissAllDialogFragments();
                    break;
                }
            case CONTEXT_MENU_CUSTOM_STUDY_REBUILD:
                {
                    Timber.i("Empty deck selected");
                    ((DeckPicker) getActivity()).rebuildFiltered();
                    ((AnkiActivity) getActivity()).dismissAllDialogFragments();
                    break;
                }
            case CONTEXT_MENU_CUSTOM_STUDY_EMPTY:
                {
                    Timber.i("Empty deck selected");
                    ((DeckPicker) getActivity()).emptyFiltered();
                    ((AnkiActivity) getActivity()).dismissAllDialogFragments();
                    break;
                }
            case CONTEXT_MENU_CREATE_SUBDECK:
                {
                    Timber.i("Create Subdeck selected");
                    ((DeckPicker) getActivity()).createSubdeckDialog();
                    break;
                }
        }
    };
}
