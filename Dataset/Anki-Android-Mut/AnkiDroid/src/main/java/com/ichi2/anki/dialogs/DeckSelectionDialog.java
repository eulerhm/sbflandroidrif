/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Deck;
import com.ichi2.utils.FunctionalInterfaces;
import com.ichi2.utils.FilterResultsUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckSelectionDialog extends AnalyticsDialogFragment {

    private MaterialDialog mDialog;

    /**
     * A dialog which handles selecting a deck
     */
    @NonNull
    public static DeckSelectionDialog newInstance(@NonNull String title, @NonNull String summaryMessage, @NonNull List<SelectableDeck> decks) {
        DeckSelectionDialog f = new DeckSelectionDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(629)) {
            args.putString("summaryMessage", summaryMessage);
        }
        if (!ListenerUtil.mutListener.listen(630)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(631)) {
            args.putParcelableArrayList("deckNames", new ArrayList<>(decks));
        }
        if (!ListenerUtil.mutListener.listen(632)) {
            f.setArguments(args);
        }
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(633)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(634)) {
            setCancelable(true);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.deck_picker_dialog, null, false);
        TextView summary = dialogView.findViewById(R.id.deck_picker_dialog_summary);
        Bundle arguments = requireArguments();
        if (!ListenerUtil.mutListener.listen(635)) {
            summary.setText(getSummaryMessage(arguments));
        }
        RecyclerView recyclerView = dialogView.findViewById(R.id.deck_picker_dialog_list);
        if (!ListenerUtil.mutListener.listen(636)) {
            recyclerView.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(637)) {
            recyclerView.setHasFixedSize(true);
        }
        RecyclerView.LayoutManager deckLayoutManager = new LinearLayoutManager(requireActivity());
        if (!ListenerUtil.mutListener.listen(638)) {
            recyclerView.setLayoutManager(deckLayoutManager);
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        if (!ListenerUtil.mutListener.listen(639)) {
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
        List<SelectableDeck> decks = getDeckNames(arguments);
        DecksArrayAdapter adapter = new DecksArrayAdapter(decks);
        if (!ListenerUtil.mutListener.listen(640)) {
            recyclerView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(641)) {
            adjustToolbar(dialogView, adapter);
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(requireActivity()).neutralText(R.string.dialog_cancel).negativeText(R.string.restore_default).customView(dialogView, false).onNegative((dialog, which) -> onDeckSelected(null)).onNeutral((dialog, which) -> {
        });
        if (!ListenerUtil.mutListener.listen(642)) {
            mDialog = builder.build();
        }
        return mDialog;
    }

    @NonNull
    private String getSummaryMessage(Bundle arguments) {
        return Objects.requireNonNull(arguments.getString("summaryMessage"));
    }

    @NonNull
    private ArrayList<SelectableDeck> getDeckNames(Bundle arguments) {
        return Objects.requireNonNull(arguments.getParcelableArrayList("deckNames"));
    }

    @NonNull
    private String getTitle() {
        return Objects.requireNonNull(requireArguments().getString("title"));
    }

    private void adjustToolbar(View dialogView, DecksArrayAdapter adapter) {
        Toolbar mToolbar = dialogView.findViewById(R.id.deck_picker_dialog_toolbar);
        if (!ListenerUtil.mutListener.listen(643)) {
            mToolbar.setTitle(getTitle());
        }
        if (!ListenerUtil.mutListener.listen(644)) {
            mToolbar.inflateMenu(R.menu.deck_picker_dialog_menu);
        }
        MenuItem searchItem = mToolbar.getMenu().findItem(R.id.deck_picker_dialog_action_filter);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (!ListenerUtil.mutListener.listen(645)) {
            searchView.setQueryHint(getString(R.string.deck_picker_dialog_filter_decks));
        }
        if (!ListenerUtil.mutListener.listen(648)) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!ListenerUtil.mutListener.listen(646)) {
                        searchView.clearFocus();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!ListenerUtil.mutListener.listen(647)) {
                        adapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        }
    }

    protected void onDeckSelected(@Nullable SelectableDeck deck) {
        if (!ListenerUtil.mutListener.listen(649)) {
            ((DeckSelectionListener) requireActivity()).onDeckSelected(deck);
        }
    }

    protected void selectDeckAndClose(@NonNull SelectableDeck deck) {
        if (!ListenerUtil.mutListener.listen(650)) {
            onDeckSelected(deck);
        }
        if (!ListenerUtil.mutListener.listen(651)) {
            mDialog.dismiss();
        }
    }

    protected void displayErrorAndCancel() {
        if (!ListenerUtil.mutListener.listen(652)) {
            mDialog.dismiss();
        }
    }

    public class DecksArrayAdapter extends RecyclerView.Adapter<DecksArrayAdapter.ViewHolder> implements Filterable {

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView mDeckTextView;

            public ViewHolder(@NonNull TextView ctv) {
                super(ctv);
                mDeckTextView = ctv;
                if (!ListenerUtil.mutListener.listen(653)) {
                    mDeckTextView.setOnClickListener(view -> {
                        String deckName = ctv.getText().toString();
                        selectDeckByNameAndClose(deckName);
                    });
                }
            }

            public void setDeck(@NonNull SelectableDeck deck) {
                if (!ListenerUtil.mutListener.listen(654)) {
                    mDeckTextView.setText(deck.getName());
                }
            }
        }

        private final ArrayList<SelectableDeck> mAllDecksList = new ArrayList<>();

        private final ArrayList<SelectableDeck> mCurrentlyDisplayedDecks = new ArrayList<>();

        public DecksArrayAdapter(@NonNull List<SelectableDeck> deckNames) {
            if (!ListenerUtil.mutListener.listen(655)) {
                mAllDecksList.addAll(deckNames);
            }
            if (!ListenerUtil.mutListener.listen(656)) {
                mCurrentlyDisplayedDecks.addAll(deckNames);
            }
            if (!ListenerUtil.mutListener.listen(657)) {
                Collections.sort(mCurrentlyDisplayedDecks);
            }
        }

        protected void selectDeckByNameAndClose(@NonNull String deckName) {
            if (!ListenerUtil.mutListener.listen(660)) {
                {
                    long _loopCounter8 = 0;
                    for (SelectableDeck d : mAllDecksList) {
                        ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                        if (!ListenerUtil.mutListener.listen(659)) {
                            if (d.getName().equals(deckName)) {
                                if (!ListenerUtil.mutListener.listen(658)) {
                                    selectDeckAndClose(d);
                                }
                                return;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(661)) {
                displayErrorAndCancel();
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_picker_dialog_list_item, parent, false);
            return new ViewHolder(v.findViewById(R.id.deck_picker_dialog_list_item_value));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            SelectableDeck deck = mCurrentlyDisplayedDecks.get(position);
            if (!ListenerUtil.mutListener.listen(662)) {
                holder.setDeck(deck);
            }
        }

        @Override
        public int getItemCount() {
            return mCurrentlyDisplayedDecks.size();
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return new DecksFilter();
        }

        /* Custom Filter class - as seen in http://stackoverflow.com/a/29792313/1332026 */
        private class DecksFilter extends Filter {

            private final ArrayList<SelectableDeck> mFilteredDecks;

            protected DecksFilter() {
                super();
                mFilteredDecks = new ArrayList<>();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (!ListenerUtil.mutListener.listen(663)) {
                    mFilteredDecks.clear();
                }
                ArrayList<SelectableDeck> allDecks = DecksArrayAdapter.this.mAllDecksList;
                if (!ListenerUtil.mutListener.listen(673)) {
                    if ((ListenerUtil.mutListener.listen(668) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(667) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(666) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(665) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(664) ? (constraint.length() != 0) : (constraint.length() == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(672)) {
                            mFilteredDecks.addAll(allDecks);
                        }
                    } else {
                        final String filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim();
                        if (!ListenerUtil.mutListener.listen(671)) {
                            {
                                long _loopCounter9 = 0;
                                for (SelectableDeck deck : allDecks) {
                                    ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                                    if (!ListenerUtil.mutListener.listen(670)) {
                                        if (deck.getName().toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                                            if (!ListenerUtil.mutListener.listen(669)) {
                                                mFilteredDecks.add(deck);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return FilterResultsUtils.fromCollection(mFilteredDecks);
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ArrayList<SelectableDeck> currentlyDisplayedDecks = DecksArrayAdapter.this.mCurrentlyDisplayedDecks;
                if (!ListenerUtil.mutListener.listen(674)) {
                    currentlyDisplayedDecks.clear();
                }
                if (!ListenerUtil.mutListener.listen(675)) {
                    currentlyDisplayedDecks.addAll(mFilteredDecks);
                }
                if (!ListenerUtil.mutListener.listen(676)) {
                    Collections.sort(currentlyDisplayedDecks);
                }
                if (!ListenerUtil.mutListener.listen(677)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    public static class SelectableDeck implements Comparable<SelectableDeck>, Parcelable {

        private final long mDeckId;

        private final String mName;

        @NonNull
        public static List<SelectableDeck> fromCollection(@NonNull Collection c, @NonNull FunctionalInterfaces.Filter<Deck> filter) {
            List<Deck> all = c.getDecks().all();
            List<SelectableDeck> ret = new ArrayList<>(all.size());
            if (!ListenerUtil.mutListener.listen(680)) {
                {
                    long _loopCounter10 = 0;
                    for (Deck d : all) {
                        ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                        if (!ListenerUtil.mutListener.listen(678)) {
                            if (!filter.shouldInclude(d)) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(679)) {
                            ret.add(new SelectableDeck(d));
                        }
                    }
                }
            }
            return ret;
        }

        @SuppressWarnings("unused")
        @NonNull
        public static List<SelectableDeck> fromCollection(@NonNull Collection c) {
            return fromCollection(c, FunctionalInterfaces.Filters.allowAll());
        }

        public SelectableDeck(long deckId, @NonNull String name) {
            this.mDeckId = deckId;
            this.mName = name;
        }

        protected SelectableDeck(@NonNull Deck d) {
            this(d.getLong("id"), d.getString("name"));
        }

        protected SelectableDeck(@NonNull Parcel in) {
            mDeckId = in.readLong();
            mName = in.readString();
        }

        public long getDeckId() {
            return mDeckId;
        }

        @NonNull
        public String getName() {
            return mName;
        }

        @Override
        public int compareTo(@NonNull SelectableDeck o) {
            return this.mName.compareTo(o.mName);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            if (!ListenerUtil.mutListener.listen(681)) {
                dest.writeLong(mDeckId);
            }
            if (!ListenerUtil.mutListener.listen(682)) {
                dest.writeString(mName);
            }
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<SelectableDeck> CREATOR = new Parcelable.Creator<SelectableDeck>() {

            @Override
            public SelectableDeck createFromParcel(Parcel in) {
                return new SelectableDeck(in);
            }

            @Override
            public SelectableDeck[] newArray(int size) {
                return new SelectableDeck[size];
            }
        };
    }

    public interface DeckSelectionListener {

        void onDeckSelected(@Nullable SelectableDeck deck);
    }
}
