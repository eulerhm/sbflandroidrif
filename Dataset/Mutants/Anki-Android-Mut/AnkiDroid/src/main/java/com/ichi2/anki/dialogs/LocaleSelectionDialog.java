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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import com.ichi2.ui.RecyclerSingleTouchAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java8.util.Lists;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Locale selection dialog. Note: this must be dismissed onDestroy if not called from an activity implementing LocaleSelectionDialogHandler
 */
public class LocaleSelectionDialog extends AnalyticsDialogFragment {

    private LocaleSelectionDialogHandler dialogHandler;

    public interface LocaleSelectionDialogHandler {

        void onSelectedLocale(@NonNull Locale selectedLocale);

        void onLocaleSelectionCancelled();
    }

    /**
     * @param handler Marker interface to enforce the convention the caller implementing LocaleSelectionDialogHandler
     */
    @SuppressWarnings("unused")
    @NonNull
    public static LocaleSelectionDialog newInstance(@NonNull LocaleSelectionDialogHandler handler) {
        LocaleSelectionDialog t = new LocaleSelectionDialog();
        if (!ListenerUtil.mutListener.listen(803)) {
            t.dialogHandler = handler;
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(804)) {
            t.setArguments(args);
        }
        return t;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(805)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(806)) {
            setCancelable(true);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(807)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(812)) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (!ListenerUtil.mutListener.listen(810)) {
                    if (dialogHandler == null) {
                        if (!ListenerUtil.mutListener.listen(808)) {
                            if (!(context instanceof LocaleSelectionDialogHandler)) {
                                throw new IllegalArgumentException("Calling activity must implement LocaleSelectionDialogHandler");
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(809)) {
                            this.dialogHandler = (LocaleSelectionDialogHandler) context;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(811)) {
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = requireActivity();
        View tagsDialogView = LayoutInflater.from(activity).inflate(R.layout.locale_selection_dialog, activity.findViewById(R.id.root_layout), false);
        LocaleListAdapter mAdapter = new LocaleListAdapter(Locale.getAvailableLocales());
        if (!ListenerUtil.mutListener.listen(813)) {
            setupRecyclerView(activity, tagsDialogView, mAdapter);
        }
        if (!ListenerUtil.mutListener.listen(814)) {
            inflateMenu(tagsDialogView, mAdapter);
        }
        // Only show a negative button, use the RecyclerView for positive actions
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity).negativeText(getString(R.string.dialog_cancel)).customView(tagsDialogView, false).onNegative((dialog, which) -> dialogHandler.onLocaleSelectionCancelled());
        Dialog mDialog = builder.build();
        Window window = mDialog.getWindow();
        if (!ListenerUtil.mutListener.listen(816)) {
            if (window != null) {
                if (!ListenerUtil.mutListener.listen(815)) {
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }
            }
        }
        return mDialog;
    }

    private void setupRecyclerView(@NonNull Activity activity, @NonNull View tagsDialogView, LocaleListAdapter adapter) {
        RecyclerView recyclerView = tagsDialogView.findViewById(R.id.locale_dialog_selection_list);
        if (!ListenerUtil.mutListener.listen(817)) {
            recyclerView.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(818)) {
            recyclerView.setHasFixedSize(true);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        if (!ListenerUtil.mutListener.listen(819)) {
            recyclerView.setLayoutManager(layoutManager);
        }
        if (!ListenerUtil.mutListener.listen(820)) {
            recyclerView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(821)) {
            recyclerView.addOnItemTouchListener(new RecyclerSingleTouchAdapter(activity, (view, position) -> {
                Locale l = adapter.getLocaleAtPosition(position);
                LocaleSelectionDialog.this.dialogHandler.onSelectedLocale(l);
            }));
        }
    }

    private void inflateMenu(@NonNull View tagsDialogView, @NonNull final LocaleListAdapter adapter) {
        Toolbar mToolbar = tagsDialogView.findViewById(R.id.locale_dialog_selection_toolbar);
        if (!ListenerUtil.mutListener.listen(822)) {
            mToolbar.setTitle(R.string.locale_selection_dialog_title);
        }
        if (!ListenerUtil.mutListener.listen(823)) {
            mToolbar.inflateMenu(R.menu.locale_dialog_search_bar);
        }
        MenuItem searchItem = mToolbar.getMenu().findItem(R.id.locale_dialog_action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (!ListenerUtil.mutListener.listen(824)) {
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }
        if (!ListenerUtil.mutListener.listen(826)) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!ListenerUtil.mutListener.listen(825)) {
                        adapter.getFilter().filter(newText);
                    }
                    return false;
                }
            });
        }
    }

    public static class LocaleListAdapter extends RecyclerView.Adapter<LocaleListAdapter.TextViewHolder> implements Filterable {

        private final List<Locale> mCurrentlyVisibleLocales;

        private final List<Locale> mSelectableLocales;

        public static class TextViewHolder extends RecyclerView.ViewHolder {

            @NonNull
            private final TextView mTextView;

            public TextViewHolder(@NonNull TextView textView) {
                super(textView);
                mTextView = textView;
            }

            public void setText(@NonNull String text) {
                if (!ListenerUtil.mutListener.listen(827)) {
                    mTextView.setText(text);
                }
            }

            public void setLocale(@NonNull Locale locale) {
                String displayValue = locale.getDisplayName();
                if (!ListenerUtil.mutListener.listen(828)) {
                    mTextView.setText(displayValue);
                }
            }
        }

        public LocaleListAdapter(@NonNull Locale[] locales) {
            mSelectableLocales = Lists.of(locales);
            mCurrentlyVisibleLocales = new ArrayList<>(Arrays.asList(locales));
        }

        @NonNull
        @Override
        public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.locale_dialog_fragment_textview, parent, false);
            return new TextViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
            if (!ListenerUtil.mutListener.listen(829)) {
                holder.setLocale(mCurrentlyVisibleLocales.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mCurrentlyVisibleLocales.size();
        }

        @NonNull
        public Locale getLocaleAtPosition(int position) {
            return mCurrentlyVisibleLocales.get(position);
        }

        @NonNull
        @Override
        public Filter getFilter() {
            final List<Locale> selectableLocales = mSelectableLocales;
            final List<Locale> visibleLocales = mCurrentlyVisibleLocales;
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    if (!ListenerUtil.mutListener.listen(831)) {
                        if (TextUtils.isEmpty(constraint)) {
                            FilterResults filterResults = new FilterResults();
                            if (!ListenerUtil.mutListener.listen(830)) {
                                filterResults.values = selectableLocales;
                            }
                            return filterResults;
                        }
                    }
                    String normalisedConstraint = constraint.toString().toLowerCase(Locale.getDefault());
                    ArrayList<Locale> locales = new ArrayList<>(selectableLocales.size());
                    if (!ListenerUtil.mutListener.listen(834)) {
                        {
                            long _loopCounter13 = 0;
                            for (Locale l : selectableLocales) {
                                ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                                if (!ListenerUtil.mutListener.listen(833)) {
                                    if (l.getDisplayName().toLowerCase(Locale.getDefault()).contains(normalisedConstraint)) {
                                        if (!ListenerUtil.mutListener.listen(832)) {
                                            locales.add(l);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    if (!ListenerUtil.mutListener.listen(835)) {
                        filterResults.values = locales;
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (!ListenerUtil.mutListener.listen(836)) {
                        visibleLocales.clear();
                    }
                    // noinspection unchecked
                    Collection<? extends Locale> values = (Collection<? extends Locale>) results.values;
                    if (!ListenerUtil.mutListener.listen(837)) {
                        visibleLocales.addAll(values);
                    }
                    if (!ListenerUtil.mutListener.listen(838)) {
                        notifyDataSetChanged();
                    }
                }
            };
        }
    }
}
