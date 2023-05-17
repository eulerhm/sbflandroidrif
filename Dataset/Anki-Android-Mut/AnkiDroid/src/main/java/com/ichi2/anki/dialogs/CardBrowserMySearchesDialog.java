package com.ichi2.anki.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import com.ichi2.ui.ButtonItemAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CardBrowserMySearchesDialog extends AnalyticsDialogFragment {

    // list searches dialog
    public static final int CARD_BROWSER_MY_SEARCHES_TYPE_LIST = 0;

    // save searches dialog
    public static final int CARD_BROWSER_MY_SEARCHES_TYPE_SAVE = 1;

    private static MySearchesDialogListener mMySearchesDialogListener;

    private ButtonItemAdapter mButtonItemAdapter;

    private HashMap<String, String> mSavedFilters;

    private ArrayList<String> mSavedFilterKeys;

    private String mCurrentSearchTerms;

    public interface MySearchesDialogListener {

        void onSelection(String searchName);

        void onRemoveSearch(String searchName);

        void onSaveSearch(String searchName, String searchTerms);
    }

    public static CardBrowserMySearchesDialog newInstance(HashMap<String, String> savedFilters, MySearchesDialogListener mySearchesDialogListener, String currentSearchTerms, int type) {
        if (!ListenerUtil.mutListener.listen(340)) {
            mMySearchesDialogListener = mySearchesDialogListener;
        }
        CardBrowserMySearchesDialog m = new CardBrowserMySearchesDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(341)) {
            args.putSerializable("savedFilters", savedFilters);
        }
        if (!ListenerUtil.mutListener.listen(342)) {
            args.putInt("type", type);
        }
        if (!ListenerUtil.mutListener.listen(343)) {
            args.putString("currentSearchTerms", currentSearchTerms);
        }
        if (!ListenerUtil.mutListener.listen(344)) {
            m.setArguments(args);
        }
        return m;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(345)) {
            super.onCreate(savedInstanceState);
        }
        final Resources res = getResources();
        Activity activity = getActivity();
        final MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        int type = getArguments().getInt("type");
        if (!ListenerUtil.mutListener.listen(364)) {
            if ((ListenerUtil.mutListener.listen(350) ? (type >= CARD_BROWSER_MY_SEARCHES_TYPE_LIST) : (ListenerUtil.mutListener.listen(349) ? (type <= CARD_BROWSER_MY_SEARCHES_TYPE_LIST) : (ListenerUtil.mutListener.listen(348) ? (type > CARD_BROWSER_MY_SEARCHES_TYPE_LIST) : (ListenerUtil.mutListener.listen(347) ? (type < CARD_BROWSER_MY_SEARCHES_TYPE_LIST) : (ListenerUtil.mutListener.listen(346) ? (type != CARD_BROWSER_MY_SEARCHES_TYPE_LIST) : (type == CARD_BROWSER_MY_SEARCHES_TYPE_LIST))))))) {
                if (!ListenerUtil.mutListener.listen(358)) {
                    mSavedFilters = (HashMap<String, String>) getArguments().getSerializable("savedFilters");
                }
                if (!ListenerUtil.mutListener.listen(359)) {
                    mSavedFilterKeys = new ArrayList<>(mSavedFilters.keySet());
                }
                if (!ListenerUtil.mutListener.listen(360)) {
                    mButtonItemAdapter = new ButtonItemAdapter(mSavedFilterKeys);
                }
                if (!ListenerUtil.mutListener.listen(361)) {
                    // so the values are sorted.
                    mButtonItemAdapter.notifyAdapterDataSetChanged();
                }
                if (!ListenerUtil.mutListener.listen(362)) {
                    mButtonItemAdapter.setCallbacks(searchName -> {
                        Timber.d("item clicked: %s", searchName);
                        mMySearchesDialogListener.onSelection(searchName);
                        getDialog().dismiss();
                    }, searchName -> {
                        Timber.d("button clicked: %s", searchName);
                        removeSearch(searchName);
                    });
                }
                if (!ListenerUtil.mutListener.listen(363)) {
                    builder.title(res.getString(R.string.card_browser_list_my_searches_title)).adapter(mButtonItemAdapter, null);
                }
            } else if ((ListenerUtil.mutListener.listen(355) ? (type >= CARD_BROWSER_MY_SEARCHES_TYPE_SAVE) : (ListenerUtil.mutListener.listen(354) ? (type <= CARD_BROWSER_MY_SEARCHES_TYPE_SAVE) : (ListenerUtil.mutListener.listen(353) ? (type > CARD_BROWSER_MY_SEARCHES_TYPE_SAVE) : (ListenerUtil.mutListener.listen(352) ? (type < CARD_BROWSER_MY_SEARCHES_TYPE_SAVE) : (ListenerUtil.mutListener.listen(351) ? (type != CARD_BROWSER_MY_SEARCHES_TYPE_SAVE) : (type == CARD_BROWSER_MY_SEARCHES_TYPE_SAVE))))))) {
                if (!ListenerUtil.mutListener.listen(356)) {
                    mCurrentSearchTerms = getArguments().getString("currentSearchTerms");
                }
                if (!ListenerUtil.mutListener.listen(357)) {
                    builder.title(getString(R.string.card_browser_list_my_searches_save)).positiveText(getString(android.R.string.ok)).negativeText(getString(R.string.dialog_cancel)).input(R.string.card_browser_list_my_searches_new_name, R.string.empty_string, (dialog, text) -> {
                        Timber.d("Saving search with title/terms: %s/%s", text, mCurrentSearchTerms);
                        mMySearchesDialogListener.onSaveSearch(text.toString(), mCurrentSearchTerms);
                    });
                }
            }
        }
        MaterialDialog dialog = builder.build();
        if (!ListenerUtil.mutListener.listen(375)) {
            if (dialog.getRecyclerView() != null) {
                LinearLayoutManager mLayoutManager = (LinearLayoutManager) dialog.getRecyclerView().getLayoutManager();
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dialog.getRecyclerView().getContext(), mLayoutManager.getOrientation());
                float scale = res.getDisplayMetrics().density;
                int dpAsPixels = (int) ((ListenerUtil.mutListener.listen(372) ? ((ListenerUtil.mutListener.listen(368) ? (5 % scale) : (ListenerUtil.mutListener.listen(367) ? (5 / scale) : (ListenerUtil.mutListener.listen(366) ? (5 - scale) : (ListenerUtil.mutListener.listen(365) ? (5 + scale) : (5 * scale))))) % 0.5f) : (ListenerUtil.mutListener.listen(371) ? ((ListenerUtil.mutListener.listen(368) ? (5 % scale) : (ListenerUtil.mutListener.listen(367) ? (5 / scale) : (ListenerUtil.mutListener.listen(366) ? (5 - scale) : (ListenerUtil.mutListener.listen(365) ? (5 + scale) : (5 * scale))))) / 0.5f) : (ListenerUtil.mutListener.listen(370) ? ((ListenerUtil.mutListener.listen(368) ? (5 % scale) : (ListenerUtil.mutListener.listen(367) ? (5 / scale) : (ListenerUtil.mutListener.listen(366) ? (5 - scale) : (ListenerUtil.mutListener.listen(365) ? (5 + scale) : (5 * scale))))) * 0.5f) : (ListenerUtil.mutListener.listen(369) ? ((ListenerUtil.mutListener.listen(368) ? (5 % scale) : (ListenerUtil.mutListener.listen(367) ? (5 / scale) : (ListenerUtil.mutListener.listen(366) ? (5 - scale) : (ListenerUtil.mutListener.listen(365) ? (5 + scale) : (5 * scale))))) - 0.5f) : ((ListenerUtil.mutListener.listen(368) ? (5 % scale) : (ListenerUtil.mutListener.listen(367) ? (5 / scale) : (ListenerUtil.mutListener.listen(366) ? (5 - scale) : (ListenerUtil.mutListener.listen(365) ? (5 + scale) : (5 * scale))))) + 0.5f))))));
                if (!ListenerUtil.mutListener.listen(373)) {
                    dialog.getView().setPadding(dpAsPixels, 0, dpAsPixels, dpAsPixels);
                }
                if (!ListenerUtil.mutListener.listen(374)) {
                    dialog.getRecyclerView().addItemDecoration(dividerItemDecoration);
                }
            }
        }
        return dialog;
    }

    private void removeSearch(String searchName) {
        Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(376)) {
            new MaterialDialog.Builder(getActivity()).content(res.getString(R.string.card_browser_list_my_searches_remove_content, searchName)).positiveText(android.R.string.ok).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                mMySearchesDialogListener.onRemoveSearch(searchName);
                mSavedFilters.remove(searchName);
                mSavedFilterKeys.remove(searchName);
                mButtonItemAdapter.remove(searchName);
                mButtonItemAdapter.notifyAdapterDataSetChanged();
                dialog.dismiss();
                if (mSavedFilters.size() == 0) {
                    getDialog().dismiss();
                }
            }).show();
        }
    }
}
