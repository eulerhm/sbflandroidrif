package org.wordpress.android.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPPrefView extends LinearLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public enum PrefType {

        // text setting
        TEXT,
        // boolean setting
        TOGGLE,
        // multi-select setting
        CHECKLIST,
        // single-select setting
        RADIOLIST;

        public static PrefType fromInt(int value) {
            switch(value) {
                case 1:
                    return TOGGLE;
                case 2:
                    return CHECKLIST;
                case 3:
                    return RADIOLIST;
                default:
                    return TEXT;
            }
        }
    }

    /*
     * listener for when the user changes the preference
     * TEXT use prefView.getTextEntry() to retrieve the updated setting
     * TOGGLE use prefView.isChecked() to retrieve the updated setting
     * RADIOLIST use prefView.getSelectedItem() to retrieve the updated setting
     * CHECKLIST use prefView.getSelectedItems() to retrieve the updated setting
     */
    public interface OnPrefChangedListener {

        void onPrefChanged(@NonNull WPPrefView prefView);
    }

    private PrefType mPrefType = PrefType.TEXT;

    private final PrefListItems mListItems = new PrefListItems();

    private TextView mHeadingTextView;

    private TextView mTitleTextView;

    private TextView mSummaryTextView;

    private SwitchCompat mToggleSwitch;

    private String mTextEntry;

    private String mTextDialogSubtitle;

    private OnPrefChangedListener mListener;

    private static final String KEY_LIST_ITEMS = "prefview_listitems";

    private static final String KEY_SUPER_STATE = "prefview_super_state";

    /*
     * single item when this is a list preference
     */
    public static class PrefListItem implements Serializable {

        // name to display for this item
        private final String mItemName;

        // value for this item (can be same as name)
        private final String mItemValue;

        // whether this item is checked
        private boolean mIsChecked;

        public PrefListItem(@NonNull String itemName, @NonNull String itemValue, boolean isChecked) {
            mItemName = itemName;
            mItemValue = itemValue;
            if (!ListenerUtil.mutListener.listen(29126)) {
                mIsChecked = isChecked;
            }
        }

        @SuppressWarnings("unused")
        @NonNull
        public String getItemName() {
            return mItemName;
        }

        @NonNull
        public String getItemValue() {
            return mItemValue;
        }
    }

    /*
     * all items when this is a list preference (both single- and multi-select)
     */
    public static class PrefListItems extends ArrayList<PrefListItem> {

        private void setCheckedItems(@NonNull SparseBooleanArray checkedItems) {
            if (!ListenerUtil.mutListener.listen(29133)) {
                {
                    long _loopCounter435 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(29132) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(29131) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(29130) ? (i > this.size()) : (ListenerUtil.mutListener.listen(29129) ? (i != this.size()) : (ListenerUtil.mutListener.listen(29128) ? (i == this.size()) : (i < this.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter435", ++_loopCounter435);
                        if (!ListenerUtil.mutListener.listen(29127)) {
                            this.get(i).mIsChecked = checkedItems.get(i);
                        }
                    }
                }
            }
        }

        // use this for RADIOLIST prefs to get the single-select item
        private PrefListItem getFirstSelectedItem() {
            if (!ListenerUtil.mutListener.listen(29135)) {
                {
                    long _loopCounter436 = 0;
                    for (PrefListItem item : this) {
                        ListenerUtil.loopListener.listen("_loopCounter436", ++_loopCounter436);
                        if (!ListenerUtil.mutListener.listen(29134)) {
                            if (item.mIsChecked) {
                                return item;
                            }
                        }
                    }
                }
            }
            return null;
        }

        // use this for CHECKLIST prefs to get all selected items
        @NonNull
        private PrefListItems getSelectedItems() {
            PrefListItems selectedItems = new PrefListItems();
            if (!ListenerUtil.mutListener.listen(29138)) {
                {
                    long _loopCounter437 = 0;
                    for (PrefListItem item : this) {
                        ListenerUtil.loopListener.listen("_loopCounter437", ++_loopCounter437);
                        if (!ListenerUtil.mutListener.listen(29137)) {
                            if (item.mIsChecked) {
                                if (!ListenerUtil.mutListener.listen(29136)) {
                                    selectedItems.add(item);
                                }
                            }
                        }
                    }
                }
            }
            return selectedItems;
        }

        // use this with RADIOLIST prefs to select only the passed name
        public void setSelectedName(@NonNull String selectedName) {
            if (!ListenerUtil.mutListener.listen(29140)) {
                {
                    long _loopCounter438 = 0;
                    for (PrefListItem item : this) {
                        ListenerUtil.loopListener.listen("_loopCounter438", ++_loopCounter438);
                        if (!ListenerUtil.mutListener.listen(29139)) {
                            item.mIsChecked = StringUtils.equals(selectedName, item.mItemName);
                        }
                    }
                }
            }
        }

        public boolean removeItems(@NonNull PrefListItems items) {
            boolean isChanged = false;
            if (!ListenerUtil.mutListener.listen(29149)) {
                {
                    long _loopCounter439 = 0;
                    for (PrefListItem item : items) {
                        ListenerUtil.loopListener.listen("_loopCounter439", ++_loopCounter439);
                        int i = indexOfValue(item.getItemValue());
                        if (!ListenerUtil.mutListener.listen(29148)) {
                            if ((ListenerUtil.mutListener.listen(29145) ? (i >= -1) : (ListenerUtil.mutListener.listen(29144) ? (i <= -1) : (ListenerUtil.mutListener.listen(29143) ? (i < -1) : (ListenerUtil.mutListener.listen(29142) ? (i != -1) : (ListenerUtil.mutListener.listen(29141) ? (i == -1) : (i > -1))))))) {
                                if (!ListenerUtil.mutListener.listen(29146)) {
                                    this.remove(i);
                                }
                                if (!ListenerUtil.mutListener.listen(29147)) {
                                    isChanged = true;
                                }
                            }
                        }
                    }
                }
            }
            return isChanged;
        }

        private int indexOfValue(@NonNull String value) {
            if (!ListenerUtil.mutListener.listen(29156)) {
                {
                    long _loopCounter440 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(29155) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(29154) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(29153) ? (i > this.size()) : (ListenerUtil.mutListener.listen(29152) ? (i != this.size()) : (ListenerUtil.mutListener.listen(29151) ? (i == this.size()) : (i < this.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter440", ++_loopCounter440);
                        if (!ListenerUtil.mutListener.listen(29150)) {
                            if (this.get(i).getItemValue().equals(value)) {
                                return i;
                            }
                        }
                    }
                }
            }
            return -1;
        }

        public boolean containsValue(@NonNull String value) {
            return (ListenerUtil.mutListener.listen(29161) ? (indexOfValue(value) >= -1) : (ListenerUtil.mutListener.listen(29160) ? (indexOfValue(value) <= -1) : (ListenerUtil.mutListener.listen(29159) ? (indexOfValue(value) < -1) : (ListenerUtil.mutListener.listen(29158) ? (indexOfValue(value) != -1) : (ListenerUtil.mutListener.listen(29157) ? (indexOfValue(value) == -1) : (indexOfValue(value) > -1))))));
        }
    }

    /*
     * Wrapper that will allow us to preserve type of PrefListItems when serializing it
     */
    public static class PrefListItemsWrapper implements Serializable {

        private PrefListItems mList;

        public PrefListItems getList() {
            return mList;
        }

        PrefListItemsWrapper(PrefListItems mList) {
            if (!ListenerUtil.mutListener.listen(29162)) {
                this.mList = mList;
            }
        }
    }

    public WPPrefView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(29163)) {
            initView(context, null);
        }
    }

    public WPPrefView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(29164)) {
            initView(context, attrs);
        }
    }

    public WPPrefView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(29165)) {
            initView(context, attrs);
        }
    }

    public WPPrefView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!ListenerUtil.mutListener.listen(29166)) {
            initView(context, attrs);
        }
    }

    private void initView(Context context, AttributeSet attrs) {
        ViewGroup view = (ViewGroup) inflate(context, R.layout.wppref_view, this);
        ViewGroup container = view.findViewById(R.id.container);
        if (!ListenerUtil.mutListener.listen(29167)) {
            mHeadingTextView = view.findViewById(R.id.text_heading);
        }
        if (!ListenerUtil.mutListener.listen(29168)) {
            mTitleTextView = view.findViewById(R.id.text_title);
        }
        if (!ListenerUtil.mutListener.listen(29169)) {
            mSummaryTextView = view.findViewById(R.id.text_summary);
        }
        if (!ListenerUtil.mutListener.listen(29170)) {
            mToggleSwitch = view.findViewById(R.id.switch_view);
        }
        if (!ListenerUtil.mutListener.listen(29171)) {
            container.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29179)) {
            if (attrs != null) {
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.wpPrefView, 0, 0);
                try {
                    int prefTypeInt = a.getInteger(R.styleable.wpPrefView_wpPrefType, 0);
                    String heading = a.getString(R.styleable.wpPrefView_wpHeading);
                    String title = a.getString(R.styleable.wpPrefView_wpTitle);
                    String summary = a.getString(R.styleable.wpPrefView_wpSummary);
                    String dialogSubtitle = a.getString(R.styleable.wpPrefView_wpTextDialogSubtitle);
                    boolean showDivider = a.getBoolean(R.styleable.wpPrefView_wpShowDivider, true);
                    if (!ListenerUtil.mutListener.listen(29173)) {
                        setPrefType(PrefType.fromInt(prefTypeInt));
                    }
                    if (!ListenerUtil.mutListener.listen(29174)) {
                        setHeading(heading);
                    }
                    if (!ListenerUtil.mutListener.listen(29175)) {
                        setTitle(title);
                    }
                    if (!ListenerUtil.mutListener.listen(29176)) {
                        setSummary(summary);
                    }
                    if (!ListenerUtil.mutListener.listen(29177)) {
                        setTextDialogSubtitle(dialogSubtitle);
                    }
                    View divider = view.findViewById(R.id.divider);
                    if (!ListenerUtil.mutListener.listen(29178)) {
                        divider.setVisibility(showDivider ? View.VISIBLE : View.GONE);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(29172)) {
                        a.recycle();
                    }
                }
            }
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(29180)) {
            bundle.putSerializable(KEY_LIST_ITEMS, new PrefListItemsWrapper(mListItems));
        }
        if (!ListenerUtil.mutListener.listen(29181)) {
            bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        }
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!ListenerUtil.mutListener.listen(29185)) {
            if (state instanceof Bundle) {
                Bundle bundle = (Bundle) state;
                PrefListItemsWrapper listWrapper = (PrefListItemsWrapper) bundle.getSerializable(KEY_LIST_ITEMS);
                if (!ListenerUtil.mutListener.listen(29183)) {
                    if (listWrapper != null) {
                        PrefListItems items = listWrapper.getList();
                        if (!ListenerUtil.mutListener.listen(29182)) {
                            setListItems(items);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29184)) {
                    state = bundle.getParcelable(KEY_SUPER_STATE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29186)) {
            super.onRestoreInstanceState(state);
        }
    }

    public void setOnPrefChangedListener(OnPrefChangedListener listener) {
        if (!ListenerUtil.mutListener.listen(29187)) {
            mListener = listener;
        }
    }

    private void doPrefChanged() {
        if (!ListenerUtil.mutListener.listen(29189)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(29188)) {
                    mListener.onPrefChanged(this);
                }
            }
        }
    }

    private void setPrefType(@NonNull PrefType prefType) {
        if (!ListenerUtil.mutListener.listen(29190)) {
            mPrefType = prefType;
        }
        boolean isToggle = mPrefType == PrefType.TOGGLE;
        if (!ListenerUtil.mutListener.listen(29191)) {
            mToggleSwitch.setVisibility(isToggle ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(29192)) {
            mToggleSwitch.setOnCheckedChangeListener(isToggle ? this : null);
        }
    }

    /*
     * blue heading text that should appear above the preference when it's the first in a group
     */
    public void setHeading(String heading) {
        if (!ListenerUtil.mutListener.listen(29193)) {
            mHeadingTextView.setText(heading);
        }
        if (!ListenerUtil.mutListener.listen(29194)) {
            mHeadingTextView.setVisibility(TextUtils.isEmpty(heading) ? GONE : VISIBLE);
        }
    }

    /*
     * title above the preference and below the optional heading
     */
    private void setTitle(String title) {
        if (!ListenerUtil.mutListener.listen(29195)) {
            mTitleTextView.setText(title);
        }
    }

    /*
     * optional description that appears below the title
     */
    public void setSummary(String summary) {
        if (!ListenerUtil.mutListener.listen(29196)) {
            mSummaryTextView.setText(summary);
        }
        if (!ListenerUtil.mutListener.listen(29197)) {
            mSummaryTextView.setVisibility(TextUtils.isEmpty(summary) ? View.GONE : View.VISIBLE);
        }
    }

    /*
     * subtitle on the dialog that appears when the PrefType is TEXT
     */
    private void setTextDialogSubtitle(String subtitle) {
        if (!ListenerUtil.mutListener.listen(29198)) {
            mTextDialogSubtitle = subtitle;
        }
    }

    /*
     * current entry when the PrefType is TEXT
     */
    public String getTextEntry() {
        return mTextEntry;
    }

    public void setTextEntry(String entry) {
        if (!ListenerUtil.mutListener.listen(29199)) {
            mTextEntry = entry;
        }
        if (!ListenerUtil.mutListener.listen(29200)) {
            setSummary(entry);
        }
    }

    /*
     * returns whether or not the switch is checked when the PrefType is TOGGLE
     */
    public boolean isChecked() {
        return (ListenerUtil.mutListener.listen(29201) ? (mPrefType == PrefType.TOGGLE || mToggleSwitch.isChecked()) : (mPrefType == PrefType.TOGGLE && mToggleSwitch.isChecked()));
    }

    public void setChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(29202)) {
            mToggleSwitch.setChecked(checked);
        }
    }

    public void setListItems(@NonNull PrefListItems items) {
        if (!ListenerUtil.mutListener.listen(29203)) {
            mListItems.clear();
        }
        if (!ListenerUtil.mutListener.listen(29204)) {
            mListItems.addAll(items);
        }
    }

    public PrefListItem getSelectedItem() {
        return mListItems.getFirstSelectedItem();
    }

    public PrefListItems getSelectedItems() {
        return mListItems.getSelectedItems();
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(29209)) {
            switch(mPrefType) {
                case CHECKLIST:
                case RADIOLIST:
                case TEXT:
                    if (!ListenerUtil.mutListener.listen(29207)) {
                        if (getContext() instanceof Activity) {
                            Activity activity = (Activity) getContext();
                            WPPrefDialogFragment fragment = WPPrefDialogFragment.newInstance(this);
                            if (!ListenerUtil.mutListener.listen(29205)) {
                                activity.getFragmentManager().executePendingTransactions();
                            }
                            if (!ListenerUtil.mutListener.listen(29206)) {
                                fragment.show(activity.getFragmentManager(), "pref_dialog_tag");
                            }
                        }
                    }
                    break;
                case TOGGLE:
                    if (!ListenerUtil.mutListener.listen(29208)) {
                        mToggleSwitch.setChecked(!mToggleSwitch.isChecked());
                    }
                    break;
            }
        }
    }

    /*
     * user clicked the toggle switch
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(29210)) {
            doPrefChanged();
        }
    }

    /*
     * user clicked the view when the PrefType is TEXT - shows a dialog enabling the user
     * to edit the entry
     */
    private Dialog getTextDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        // noinspection InflateParams
        ViewGroup customView = (ViewGroup) inflater.inflate(R.layout.wppref_text_dialog, null);
        final EditText editText = customView.findViewById(R.id.edit);
        if (!ListenerUtil.mutListener.listen(29211)) {
            editText.setText(mSummaryTextView.getText());
        }
        TextView txtSubtitle = customView.findViewById(R.id.text_subtitle);
        if (!ListenerUtil.mutListener.listen(29214)) {
            if (!TextUtils.isEmpty(mTextDialogSubtitle)) {
                if (!ListenerUtil.mutListener.listen(29213)) {
                    txtSubtitle.setText(mTextDialogSubtitle);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29212)) {
                    txtSubtitle.setVisibility(GONE);
                }
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getContext()).setTitle(mTitleTextView.getText()).setView(customView).setNegativeButton(android.R.string.cancel, null).setPositiveButton(android.R.string.ok, (dialog, which) -> {
            setTextEntry(editText.getText().toString());
            doPrefChanged();
        });
        return builder.create();
    }

    /*
     * user clicked the view when the PrefType is CHECKLIST - shows a multi-select dialog enabling
     * the user to modify the list
     */
    private Dialog getCheckListDialog() {
        CharSequence[] items = new CharSequence[mListItems.size()];
        boolean[] checkedItems = new boolean[mListItems.size()];
        if (!ListenerUtil.mutListener.listen(29222)) {
            {
                long _loopCounter441 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(29221) ? (i >= mListItems.size()) : (ListenerUtil.mutListener.listen(29220) ? (i <= mListItems.size()) : (ListenerUtil.mutListener.listen(29219) ? (i > mListItems.size()) : (ListenerUtil.mutListener.listen(29218) ? (i != mListItems.size()) : (ListenerUtil.mutListener.listen(29217) ? (i == mListItems.size()) : (i < mListItems.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter441", ++_loopCounter441);
                    if (!ListenerUtil.mutListener.listen(29215)) {
                        items[i] = mListItems.get(i).mItemName;
                    }
                    if (!ListenerUtil.mutListener.listen(29216)) {
                        checkedItems[i] = mListItems.get(i).mIsChecked;
                    }
                }
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getContext()).setTitle(mTitleTextView.getText()).setNegativeButton(android.R.string.cancel, null).setPositiveButton(android.R.string.ok, (dialog, which) -> {
            SparseBooleanArray userCheckedItems = ((AlertDialog) dialog).getListView().getCheckedItemPositions();
            mListItems.setCheckedItems(userCheckedItems);
            doPrefChanged();
        }).setMultiChoiceItems(items, checkedItems, null);
        return builder.create();
    }

    /*
     * user clicked the view when the PrefType is RADIOLIST - shows a single-select dialog enabling
     * the user to choose a different item
     */
    private Dialog getRadioListDialog() {
        CharSequence[] items = new CharSequence[mListItems.size()];
        int selectedPos = 0;
        if (!ListenerUtil.mutListener.listen(29231)) {
            {
                long _loopCounter442 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(29230) ? (i >= mListItems.size()) : (ListenerUtil.mutListener.listen(29229) ? (i <= mListItems.size()) : (ListenerUtil.mutListener.listen(29228) ? (i > mListItems.size()) : (ListenerUtil.mutListener.listen(29227) ? (i != mListItems.size()) : (ListenerUtil.mutListener.listen(29226) ? (i == mListItems.size()) : (i < mListItems.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter442", ++_loopCounter442);
                    if (!ListenerUtil.mutListener.listen(29223)) {
                        items[i] = mListItems.get(i).mItemName;
                    }
                    if (!ListenerUtil.mutListener.listen(29225)) {
                        if (mListItems.get(i).mIsChecked) {
                            if (!ListenerUtil.mutListener.listen(29224)) {
                                selectedPos = i;
                            }
                        }
                    }
                }
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getContext()).setTitle(mTitleTextView.getText()).setNegativeButton(android.R.string.cancel, null).setPositiveButton(android.R.string.ok, (dialog, which) -> {
            SparseBooleanArray checkedItems = ((AlertDialog) dialog).getListView().getCheckedItemPositions();
            mListItems.setCheckedItems(checkedItems);
            PrefListItem item = mListItems.getFirstSelectedItem();
            setSummary(item != null ? item.mItemName : "");
            doPrefChanged();
        }).setSingleChoiceItems(items, selectedPos, null);
        return builder.create();
    }

    public static class WPPrefDialogFragment extends DialogFragment {

        private int mPrefViewId;

        private static final String ARG_PREF_VIEW_ID = "pref_view_ID";

        public WPPrefDialogFragment() {
        }

        public static WPPrefDialogFragment newInstance(@NonNull WPPrefView prefView) {
            WPPrefDialogFragment frag = new WPPrefDialogFragment();
            Bundle args = new Bundle();
            if (!ListenerUtil.mutListener.listen(29232)) {
                args.putInt(ARG_PREF_VIEW_ID, prefView.getId());
            }
            if (!ListenerUtil.mutListener.listen(29233)) {
                frag.setArguments(args);
            }
            return frag;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(29234)) {
                super.onCreate(savedInstanceState);
            }
            if (!ListenerUtil.mutListener.listen(29235)) {
                mPrefViewId = getArguments().getInt(ARG_PREF_VIEW_ID);
            }
        }

        @Nullable
        private WPPrefView getPrefView() {
            if (!ListenerUtil.mutListener.listen(29236)) {
                if (getActivity() != null) {
                    return (WPPrefView) getActivity().findViewById(mPrefViewId);
                }
            }
            return null;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            WPPrefView prefView = getPrefView();
            if (!ListenerUtil.mutListener.listen(29238)) {
                if (prefView != null) {
                    if (!ListenerUtil.mutListener.listen(29237)) {
                        switch(prefView.mPrefType) {
                            case TEXT:
                                return prefView.getTextDialog();
                            case RADIOLIST:
                                return prefView.getRadioListDialog();
                            case CHECKLIST:
                                return prefView.getCheckListDialog();
                        }
                    }
                }
            }
            return super.onCreateDialog(savedInstanceState);
        }
    }
}
