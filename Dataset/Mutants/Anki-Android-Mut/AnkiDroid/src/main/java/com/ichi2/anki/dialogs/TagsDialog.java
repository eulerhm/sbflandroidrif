package com.ichi2.anki.dialogs;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import com.ichi2.utils.FilterResultsUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.TreeSet;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TagsDialog extends AnalyticsDialogFragment {

    public interface TagsDialogListener {

        void onPositive(ArrayList<String> selectedTags, int option);
    }

    private static final int TYPE_NONE = -1;

    public static final int TYPE_ADD_TAG = 0;

    public static final int TYPE_FILTER_BY_TAG = 1;

    public static final int TYPE_CUSTOM_STUDY_TAGS = 2;

    private static final String DIALOG_TYPE_KEY = "dialog_type";

    private static final String CHECKED_TAGS_KEY = "checked_tags";

    private static final String ALL_TAGS_KEY = "all_tags";

    private int mType = TYPE_NONE;

    private TreeSet<String> mCurrentTags;

    private ArrayList<String> mAllTags;

    private String mPositiveText;

    private String mDialogTitle;

    private TagsDialogListener mTagsDialogListener = null;

    private TagsArrayAdapter mTagsArrayAdapter;

    private int mSelectedOption = -1;

    private SearchView mToolbarSearchView;

    private MenuItem mToolbarSearchItem;

    private TextView mNoTagsTextView;

    private RecyclerView mTagsListRecyclerView;

    private MaterialDialog mDialog;

    public static TagsDialog newInstance(int type, ArrayList<String> checked_tags, ArrayList<String> all_tags) {
        TagsDialog t = new TagsDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(995)) {
            args.putInt(DIALOG_TYPE_KEY, type);
        }
        if (!ListenerUtil.mutListener.listen(996)) {
            args.putStringArrayList(CHECKED_TAGS_KEY, checked_tags);
        }
        if (!ListenerUtil.mutListener.listen(997)) {
            args.putStringArrayList(ALL_TAGS_KEY, all_tags);
        }
        if (!ListenerUtil.mutListener.listen(998)) {
            t.setArguments(args);
        }
        return t;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(999)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1000)) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        if (!ListenerUtil.mutListener.listen(1001)) {
            mType = getArguments().getInt(DIALOG_TYPE_KEY);
        }
        if (!ListenerUtil.mutListener.listen(1002)) {
            mCurrentTags = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        }
        if (!ListenerUtil.mutListener.listen(1003)) {
            mCurrentTags.addAll(getArguments().getStringArrayList(CHECKED_TAGS_KEY));
        }
        if (!ListenerUtil.mutListener.listen(1004)) {
            mAllTags = (ArrayList<String>) getArguments().getStringArrayList(ALL_TAGS_KEY).clone();
        }
        if (!ListenerUtil.mutListener.listen(1007)) {
            {
                long _loopCounter16 = 0;
                for (String tag : mCurrentTags) {
                    ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                    if (!ListenerUtil.mutListener.listen(1006)) {
                        if (!mAllTags.contains(tag)) {
                            if (!ListenerUtil.mutListener.listen(1005)) {
                                mAllTags.add(tag);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1008)) {
            setCancelable(true);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Resources res = getResources();
        View tagsDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.tags_dialog, null, false);
        if (!ListenerUtil.mutListener.listen(1009)) {
            mTagsListRecyclerView = tagsDialogView.findViewById(R.id.tags_dialog_tags_list);
        }
        if (!ListenerUtil.mutListener.listen(1010)) {
            mTagsListRecyclerView.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(1011)) {
            mTagsListRecyclerView.setHasFixedSize(true);
        }
        RecyclerView.LayoutManager tagsListLayout = new LinearLayoutManager(getActivity());
        if (!ListenerUtil.mutListener.listen(1012)) {
            mTagsListRecyclerView.setLayoutManager(tagsListLayout);
        }
        if (!ListenerUtil.mutListener.listen(1013)) {
            mTagsArrayAdapter = new TagsArrayAdapter();
        }
        if (!ListenerUtil.mutListener.listen(1014)) {
            mTagsListRecyclerView.setAdapter(mTagsArrayAdapter);
        }
        if (!ListenerUtil.mutListener.listen(1015)) {
            mNoTagsTextView = tagsDialogView.findViewById(R.id.tags_dialog_no_tags_textview);
        }
        if (!ListenerUtil.mutListener.listen(1017)) {
            if (mAllTags.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(1016)) {
                    mNoTagsTextView.setVisibility(View.VISIBLE);
                }
            }
        }
        RadioGroup mOptionsGroup = tagsDialogView.findViewById(R.id.tags_dialog_options_radiogroup);
        if (!ListenerUtil.mutListener.listen(1024)) {
            {
                long _loopCounter17 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1023) ? (i >= mOptionsGroup.getChildCount()) : (ListenerUtil.mutListener.listen(1022) ? (i <= mOptionsGroup.getChildCount()) : (ListenerUtil.mutListener.listen(1021) ? (i > mOptionsGroup.getChildCount()) : (ListenerUtil.mutListener.listen(1020) ? (i != mOptionsGroup.getChildCount()) : (ListenerUtil.mutListener.listen(1019) ? (i == mOptionsGroup.getChildCount()) : (i < mOptionsGroup.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                    if (!ListenerUtil.mutListener.listen(1018)) {
                        mOptionsGroup.getChildAt(i).setId(i);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1025)) {
            mOptionsGroup.check(0);
        }
        if (!ListenerUtil.mutListener.listen(1026)) {
            mSelectedOption = mOptionsGroup.getCheckedRadioButtonId();
        }
        if (!ListenerUtil.mutListener.listen(1027)) {
            mOptionsGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> mSelectedOption = checkedId);
        }
        if (!ListenerUtil.mutListener.listen(1038)) {
            if ((ListenerUtil.mutListener.listen(1032) ? (mType >= TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1031) ? (mType <= TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1030) ? (mType > TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1029) ? (mType < TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1028) ? (mType != TYPE_ADD_TAG) : (mType == TYPE_ADD_TAG))))))) {
                if (!ListenerUtil.mutListener.listen(1035)) {
                    mDialogTitle = getResources().getString(R.string.card_details_tags);
                }
                if (!ListenerUtil.mutListener.listen(1036)) {
                    mOptionsGroup.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1037)) {
                    mPositiveText = getString(R.string.dialog_ok);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1033)) {
                    mDialogTitle = getResources().getString(R.string.studyoptions_limit_select_tags);
                }
                if (!ListenerUtil.mutListener.listen(1034)) {
                    mPositiveText = getString(R.string.select);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1039)) {
            adjustToolbar(tagsDialogView);
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity()).positiveText(mPositiveText).negativeText(R.string.dialog_cancel).customView(tagsDialogView, false).onPositive((dialog, which) -> mTagsDialogListener.onPositive(new ArrayList<>(mCurrentTags), mSelectedOption));
        if (!ListenerUtil.mutListener.listen(1040)) {
            mDialog = builder.build();
        }
        if (!ListenerUtil.mutListener.listen(1041)) {
            mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        return mDialog;
    }

    private void adjustToolbar(View tagsDialogView) {
        Toolbar mToolbar = tagsDialogView.findViewById(R.id.tags_dialog_toolbar);
        if (!ListenerUtil.mutListener.listen(1042)) {
            mToolbar.setTitle(mDialogTitle);
        }
        if (!ListenerUtil.mutListener.listen(1043)) {
            mToolbar.inflateMenu(R.menu.tags_dialog_menu);
        }
        final InputFilter addTagFilter = (source, start, end, dest, dstart, dend) -> {
            {
                long _loopCounter18 = 0;
                for (int i = start; i < end; i++) {
                    ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                    if (source.charAt(i) == ' ') {
                        return "";
                    }
                }
            }
            return null;
        };
        MenuItem mToolbarAddItem = mToolbar.getMenu().findItem(R.id.tags_dialog_action_add);
        if (!ListenerUtil.mutListener.listen(1044)) {
            mToolbarAddItem.setOnMenuItemClickListener(menuItem -> {
                String query = mToolbarSearchView.getQuery().toString();
                if (mToolbarSearchItem.isActionViewExpanded() && !TextUtils.isEmpty(query)) {
                    addTag(query);
                    mToolbarSearchView.setQuery("", true);
                } else {
                    MaterialDialog.Builder addTagBuilder = new MaterialDialog.Builder(getActivity()).title(getString(R.string.add_tag)).negativeText(R.string.dialog_cancel).positiveText(R.string.dialog_ok).inputType(InputType.TYPE_CLASS_TEXT).input(R.string.tag_name, R.string.empty_string, (dialog, input) -> addTag(input.toString()));
                    final MaterialDialog addTagDialog = addTagBuilder.build();
                    EditText inputET = addTagDialog.getInputEditText();
                    inputET.setFilters(new InputFilter[] { addTagFilter });
                    addTagDialog.show();
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(1045)) {
            mToolbarSearchItem = mToolbar.getMenu().findItem(R.id.tags_dialog_action_filter);
        }
        if (!ListenerUtil.mutListener.listen(1046)) {
            mToolbarSearchView = (SearchView) mToolbarSearchItem.getActionView();
        }
        EditText queryET = mToolbarSearchView.findViewById(R.id.search_src_text);
        if (!ListenerUtil.mutListener.listen(1047)) {
            queryET.setFilters(new InputFilter[] { addTagFilter });
        }
        if (!ListenerUtil.mutListener.listen(1048)) {
            mToolbarSearchView.setQueryHint(getString(R.string.filter_tags));
        }
        if (!ListenerUtil.mutListener.listen(1051)) {
            mToolbarSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!ListenerUtil.mutListener.listen(1049)) {
                        mToolbarSearchView.clearFocus();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    TagsArrayAdapter adapter = (TagsArrayAdapter) mTagsListRecyclerView.getAdapter();
                    if (!ListenerUtil.mutListener.listen(1050)) {
                        adapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        }
        MenuItem checkAllItem = mToolbar.getMenu().findItem(R.id.tags_dialog_action_select_all);
        if (!ListenerUtil.mutListener.listen(1052)) {
            checkAllItem.setOnMenuItemClickListener(menuItem -> {
                boolean changed = false;
                if (mCurrentTags.containsAll(mTagsArrayAdapter.mTagsList)) {
                    mCurrentTags.removeAll(mTagsArrayAdapter.mTagsList);
                    changed = true;
                } else {
                    {
                        long _loopCounter19 = 0;
                        for (String tag : mTagsArrayAdapter.mTagsList) {
                            ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                            if (!mCurrentTags.contains(tag)) {
                                mCurrentTags.add(tag);
                                changed = true;
                            }
                        }
                    }
                }
                if (changed) {
                    mTagsArrayAdapter.notifyDataSetChanged();
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(1060)) {
            if ((ListenerUtil.mutListener.listen(1057) ? (mType >= TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1056) ? (mType <= TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1055) ? (mType > TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1054) ? (mType < TYPE_ADD_TAG) : (ListenerUtil.mutListener.listen(1053) ? (mType != TYPE_ADD_TAG) : (mType == TYPE_ADD_TAG))))))) {
                if (!ListenerUtil.mutListener.listen(1059)) {
                    mToolbarSearchView.setQueryHint(getString(R.string.add_new_filter_tags));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1058)) {
                    mToolbarAddItem.setVisible(false);
                }
            }
        }
    }

    public void addTag(String tag) {
        if (!ListenerUtil.mutListener.listen(1072)) {
            if (!TextUtils.isEmpty(tag)) {
                String feedbackText = "";
                if (!ListenerUtil.mutListener.listen(1068)) {
                    if (!mAllTags.contains(tag)) {
                        if (!ListenerUtil.mutListener.listen(1062)) {
                            mAllTags.add(tag);
                        }
                        if (!ListenerUtil.mutListener.listen(1064)) {
                            if (mNoTagsTextView.getVisibility() == View.VISIBLE) {
                                if (!ListenerUtil.mutListener.listen(1063)) {
                                    mNoTagsTextView.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1065)) {
                            mTagsArrayAdapter.mTagsList.add(tag);
                        }
                        if (!ListenerUtil.mutListener.listen(1066)) {
                            mTagsArrayAdapter.sortData();
                        }
                        if (!ListenerUtil.mutListener.listen(1067)) {
                            feedbackText = getString(R.string.tag_editor_add_feedback, tag, mPositiveText);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1061)) {
                            feedbackText = getString(R.string.tag_editor_add_feedback_existing, tag);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1069)) {
                    mCurrentTags.add(tag);
                }
                if (!ListenerUtil.mutListener.listen(1070)) {
                    mTagsArrayAdapter.notifyDataSetChanged();
                }
                if (!ListenerUtil.mutListener.listen(1071)) {
                    // Show a snackbar to let the user know the tag was added successfully
                    UIUtils.showSnackbar(getActivity(), feedbackText, false, -1, null, mDialog.getView().findViewById(R.id.tags_dialog_snackbar), null);
                }
            }
        }
    }

    public void setTagsDialogListener(TagsDialogListener selectedTagsListener) {
        if (!ListenerUtil.mutListener.listen(1073)) {
            mTagsDialogListener = selectedTagsListener;
        }
    }

    public class TagsArrayAdapter extends RecyclerView.Adapter<TagsArrayAdapter.ViewHolder> implements Filterable {

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final CheckedTextView mTagItemCheckedTextView;

            public ViewHolder(CheckedTextView ctv) {
                super(ctv);
                mTagItemCheckedTextView = ctv;
            }
        }

        public final ArrayList<String> mTagsList;

        public TagsArrayAdapter() {
            mTagsList = (ArrayList<String>) mAllTags.clone();
            if (!ListenerUtil.mutListener.listen(1074)) {
                sortData();
            }
        }

        public void sortData() {
            if (!ListenerUtil.mutListener.listen(1075)) {
                Collections.sort(mTagsList, (lhs, rhs) -> {
                    boolean lhs_checked = mCurrentTags.contains(lhs);
                    boolean rhs_checked = mCurrentTags.contains(rhs);
                    // priority for checked items.
                    return lhs_checked == rhs_checked ? lhs.compareToIgnoreCase(rhs) : lhs_checked ? -1 : 1;
                });
            }
        }

        @NonNull
        @Override
        public TagsArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tags_item_list_dialog, parent, false);
            ViewHolder vh = new ViewHolder(v.findViewById(R.id.tags_dialog_tag_item));
            if (!ListenerUtil.mutListener.listen(1076)) {
                vh.mTagItemCheckedTextView.setOnClickListener(view -> {
                    CheckedTextView ctv = (CheckedTextView) view;
                    ctv.toggle();
                    String tag = ctv.getText().toString();
                    if (ctv.isChecked() && !mCurrentTags.contains(tag)) {
                        mCurrentTags.add(tag);
                    } else if (!ctv.isChecked()) {
                        mCurrentTags.remove(tag);
                    }
                });
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String tag = mTagsList.get(position);
            if (!ListenerUtil.mutListener.listen(1077)) {
                holder.mTagItemCheckedTextView.setText(tag);
            }
            if (!ListenerUtil.mutListener.listen(1078)) {
                holder.mTagItemCheckedTextView.setChecked(mCurrentTags.contains(tag));
            }
        }

        @Override
        public int getItemCount() {
            return mTagsList.size();
        }

        @Override
        public Filter getFilter() {
            return new TagsFilter();
        }

        /* Custom Filter class - as seen in http://stackoverflow.com/a/29792313/1332026 */
        private class TagsFilter extends Filter {

            private final ArrayList<String> mFilteredTags;

            private TagsFilter() {
                super();
                mFilteredTags = new ArrayList<>();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (!ListenerUtil.mutListener.listen(1079)) {
                    mFilteredTags.clear();
                }
                if (!ListenerUtil.mutListener.listen(1089)) {
                    if ((ListenerUtil.mutListener.listen(1084) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(1083) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(1082) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(1081) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(1080) ? (constraint.length() != 0) : (constraint.length() == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(1088)) {
                            mFilteredTags.addAll(mAllTags);
                        }
                    } else {
                        final String filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim();
                        if (!ListenerUtil.mutListener.listen(1087)) {
                            {
                                long _loopCounter20 = 0;
                                for (String tag : mAllTags) {
                                    ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                                    if (!ListenerUtil.mutListener.listen(1086)) {
                                        if (tag.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                                            if (!ListenerUtil.mutListener.listen(1085)) {
                                                mFilteredTags.add(tag);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return FilterResultsUtils.fromCollection(mFilteredTags);
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (!ListenerUtil.mutListener.listen(1090)) {
                    mTagsList.clear();
                }
                if (!ListenerUtil.mutListener.listen(1091)) {
                    mTagsList.addAll(mFilteredTags);
                }
                if (!ListenerUtil.mutListener.listen(1092)) {
                    sortData();
                }
                if (!ListenerUtil.mutListener.listen(1093)) {
                    notifyDataSetChanged();
                }
            }
        }
    }
}
