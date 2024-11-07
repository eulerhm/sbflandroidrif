package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.ui.utils.UiHelpers;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DetailListPreference extends ListPreference implements PreferenceHint {

    private DetailListAdapter mListAdapter;

    private String[] mDetails;

    private String mStartingValue;

    private int mSelectedIndex;

    private String mHint;

    private AlertDialog mDialog;

    private int mWhichButtonClicked;

    public DetailListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DetailListPreference);
        if (!ListenerUtil.mutListener.listen(14608)) {
            {
                long _loopCounter247 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14607) ? (i >= array.getIndexCount()) : (ListenerUtil.mutListener.listen(14606) ? (i <= array.getIndexCount()) : (ListenerUtil.mutListener.listen(14605) ? (i > array.getIndexCount()) : (ListenerUtil.mutListener.listen(14604) ? (i != array.getIndexCount()) : (ListenerUtil.mutListener.listen(14603) ? (i == array.getIndexCount()) : (i < array.getIndexCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter247", ++_loopCounter247);
                    int index = array.getIndex(i);
                    if (!ListenerUtil.mutListener.listen(14602)) {
                        if (index == R.styleable.DetailListPreference_entryDetails) {
                            int id = array.getResourceId(index, -1);
                            if (!ListenerUtil.mutListener.listen(14601)) {
                                if ((ListenerUtil.mutListener.listen(14599) ? (id >= -1) : (ListenerUtil.mutListener.listen(14598) ? (id <= -1) : (ListenerUtil.mutListener.listen(14597) ? (id > -1) : (ListenerUtil.mutListener.listen(14596) ? (id < -1) : (ListenerUtil.mutListener.listen(14595) ? (id == -1) : (id != -1))))))) {
                                    if (!ListenerUtil.mutListener.listen(14600)) {
                                        mDetails = array.getResources().getStringArray(id);
                                    }
                                }
                            }
                        } else if (index == R.styleable.DetailListPreference_longClickHint) {
                            if (!ListenerUtil.mutListener.listen(14594)) {
                                mHint = array.getString(index);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14609)) {
            array.recycle();
        }
        if (!ListenerUtil.mutListener.listen(14610)) {
            mSelectedIndex = -1;
        }
    }

    @Override
    public CharSequence getEntry() {
        int index = findIndexOfValue(getValue());
        CharSequence[] entries = getEntries();
        if (!ListenerUtil.mutListener.listen(14623)) {
            if ((ListenerUtil.mutListener.listen(14622) ? ((ListenerUtil.mutListener.listen(14616) ? (entries != null || (ListenerUtil.mutListener.listen(14615) ? (index <= 0) : (ListenerUtil.mutListener.listen(14614) ? (index > 0) : (ListenerUtil.mutListener.listen(14613) ? (index < 0) : (ListenerUtil.mutListener.listen(14612) ? (index != 0) : (ListenerUtil.mutListener.listen(14611) ? (index == 0) : (index >= 0))))))) : (entries != null && (ListenerUtil.mutListener.listen(14615) ? (index <= 0) : (ListenerUtil.mutListener.listen(14614) ? (index > 0) : (ListenerUtil.mutListener.listen(14613) ? (index < 0) : (ListenerUtil.mutListener.listen(14612) ? (index != 0) : (ListenerUtil.mutListener.listen(14611) ? (index == 0) : (index >= 0)))))))) || (ListenerUtil.mutListener.listen(14621) ? (index >= entries.length) : (ListenerUtil.mutListener.listen(14620) ? (index <= entries.length) : (ListenerUtil.mutListener.listen(14619) ? (index > entries.length) : (ListenerUtil.mutListener.listen(14618) ? (index != entries.length) : (ListenerUtil.mutListener.listen(14617) ? (index == entries.length) : (index < entries.length))))))) : ((ListenerUtil.mutListener.listen(14616) ? (entries != null || (ListenerUtil.mutListener.listen(14615) ? (index <= 0) : (ListenerUtil.mutListener.listen(14614) ? (index > 0) : (ListenerUtil.mutListener.listen(14613) ? (index < 0) : (ListenerUtil.mutListener.listen(14612) ? (index != 0) : (ListenerUtil.mutListener.listen(14611) ? (index == 0) : (index >= 0))))))) : (entries != null && (ListenerUtil.mutListener.listen(14615) ? (index <= 0) : (ListenerUtil.mutListener.listen(14614) ? (index > 0) : (ListenerUtil.mutListener.listen(14613) ? (index < 0) : (ListenerUtil.mutListener.listen(14612) ? (index != 0) : (ListenerUtil.mutListener.listen(14611) ? (index == 0) : (index >= 0)))))))) && (ListenerUtil.mutListener.listen(14621) ? (index >= entries.length) : (ListenerUtil.mutListener.listen(14620) ? (index <= entries.length) : (ListenerUtil.mutListener.listen(14619) ? (index > entries.length) : (ListenerUtil.mutListener.listen(14618) ? (index != entries.length) : (ListenerUtil.mutListener.listen(14617) ? (index == entries.length) : (index < entries.length))))))))) {
                return entries[index];
            }
        }
        return null;
    }

    @Override
    protected void showDialog(Bundle state) {
        Context context = getContext();
        Resources res = context.getResources();
        int topOffset = res.getDimensionPixelOffset(R.dimen.settings_fragment_dialog_vertical_inset);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(context).setBackgroundInsetTop(topOffset).setBackgroundInsetBottom(topOffset);
        if (!ListenerUtil.mutListener.listen(14624)) {
            mWhichButtonClicked = DialogInterface.BUTTON_NEGATIVE;
        }
        if (!ListenerUtil.mutListener.listen(14625)) {
            builder.setPositiveButton(android.R.string.ok, this);
        }
        if (!ListenerUtil.mutListener.listen(14626)) {
            builder.setNegativeButton(res.getString(android.R.string.cancel).toUpperCase(Locale.getDefault()), this);
        }
        if (!ListenerUtil.mutListener.listen(14628)) {
            if (mDetails == null) {
                if (!ListenerUtil.mutListener.listen(14627)) {
                    mDetails = new String[getEntries() == null ? 1 : getEntries().length];
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14629)) {
            mListAdapter = new DetailListAdapter(getContext(), R.layout.detail_list_preference, mDetails);
        }
        if (!ListenerUtil.mutListener.listen(14630)) {
            mStartingValue = getValue();
        }
        if (!ListenerUtil.mutListener.listen(14631)) {
            mSelectedIndex = findIndexOfValue(mStartingValue);
        }
        if (!ListenerUtil.mutListener.listen(14632)) {
            builder.setSingleChoiceItems(mListAdapter, mSelectedIndex, (dialog, which) -> mSelectedIndex = which);
        }
        View titleView = View.inflate(getContext(), R.layout.detail_list_preference_title, null);
        if (!ListenerUtil.mutListener.listen(14637)) {
            if (titleView != null) {
                TextView titleText = titleView.findViewById(R.id.title);
                if (!ListenerUtil.mutListener.listen(14635)) {
                    if (titleText != null) {
                        if (!ListenerUtil.mutListener.listen(14634)) {
                            titleText.setText(getTitle());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14636)) {
                    builder.setCustomTitle(titleView);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14633)) {
                    builder.setTitle(getTitle());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14638)) {
            mDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(14640)) {
            if (state != null) {
                if (!ListenerUtil.mutListener.listen(14639)) {
                    mDialog.onRestoreInstanceState(state);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14641)) {
            mDialog.setOnDismissListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14642)) {
            mDialog.show();
        }
        ListView listView = mDialog.getListView();
        if (!ListenerUtil.mutListener.listen(14646)) {
            if (listView != null) {
                if (!ListenerUtil.mutListener.listen(14643)) {
                    listView.setDividerHeight(0);
                }
                if (!ListenerUtil.mutListener.listen(14644)) {
                    listView.setClipToPadding(true);
                }
                if (!ListenerUtil.mutListener.listen(14645)) {
                    listView.setPadding(0, 0, 0, res.getDimensionPixelSize(R.dimen.site_settings_divider_height));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14647)) {
            UiHelpers.Companion.adjustDialogSize(mDialog);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(14648)) {
            mWhichButtonClicked = which;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(14649)) {
            mDialog = null;
        }
        if (!ListenerUtil.mutListener.listen(14650)) {
            onDialogClosed(mWhichButtonClicked == DialogInterface.BUTTON_POSITIVE);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        int index = positiveResult ? mSelectedIndex : findIndexOfValue(mStartingValue);
        CharSequence[] values = getEntryValues();
        if (!ListenerUtil.mutListener.listen(14664)) {
            if ((ListenerUtil.mutListener.listen(14662) ? ((ListenerUtil.mutListener.listen(14656) ? (values != null || (ListenerUtil.mutListener.listen(14655) ? (index <= 0) : (ListenerUtil.mutListener.listen(14654) ? (index > 0) : (ListenerUtil.mutListener.listen(14653) ? (index < 0) : (ListenerUtil.mutListener.listen(14652) ? (index != 0) : (ListenerUtil.mutListener.listen(14651) ? (index == 0) : (index >= 0))))))) : (values != null && (ListenerUtil.mutListener.listen(14655) ? (index <= 0) : (ListenerUtil.mutListener.listen(14654) ? (index > 0) : (ListenerUtil.mutListener.listen(14653) ? (index < 0) : (ListenerUtil.mutListener.listen(14652) ? (index != 0) : (ListenerUtil.mutListener.listen(14651) ? (index == 0) : (index >= 0)))))))) || (ListenerUtil.mutListener.listen(14661) ? (index >= values.length) : (ListenerUtil.mutListener.listen(14660) ? (index <= values.length) : (ListenerUtil.mutListener.listen(14659) ? (index > values.length) : (ListenerUtil.mutListener.listen(14658) ? (index != values.length) : (ListenerUtil.mutListener.listen(14657) ? (index == values.length) : (index < values.length))))))) : ((ListenerUtil.mutListener.listen(14656) ? (values != null || (ListenerUtil.mutListener.listen(14655) ? (index <= 0) : (ListenerUtil.mutListener.listen(14654) ? (index > 0) : (ListenerUtil.mutListener.listen(14653) ? (index < 0) : (ListenerUtil.mutListener.listen(14652) ? (index != 0) : (ListenerUtil.mutListener.listen(14651) ? (index == 0) : (index >= 0))))))) : (values != null && (ListenerUtil.mutListener.listen(14655) ? (index <= 0) : (ListenerUtil.mutListener.listen(14654) ? (index > 0) : (ListenerUtil.mutListener.listen(14653) ? (index < 0) : (ListenerUtil.mutListener.listen(14652) ? (index != 0) : (ListenerUtil.mutListener.listen(14651) ? (index == 0) : (index >= 0)))))))) && (ListenerUtil.mutListener.listen(14661) ? (index >= values.length) : (ListenerUtil.mutListener.listen(14660) ? (index <= values.length) : (ListenerUtil.mutListener.listen(14659) ? (index > values.length) : (ListenerUtil.mutListener.listen(14658) ? (index != values.length) : (ListenerUtil.mutListener.listen(14657) ? (index == values.length) : (index < values.length))))))))) {
                String value = String.valueOf(values[index]);
                if (!ListenerUtil.mutListener.listen(14663)) {
                    callChangeListener(value);
                }
            }
        }
    }

    @Override
    public boolean hasHint() {
        return !TextUtils.isEmpty(mHint);
    }

    @Override
    public String getHint() {
        return mHint;
    }

    @Override
    public void setHint(String hint) {
        if (!ListenerUtil.mutListener.listen(14665)) {
            mHint = hint;
        }
    }

    public void remove(int index) {
        if (!ListenerUtil.mutListener.listen(14677)) {
            if ((ListenerUtil.mutListener.listen(14676) ? ((ListenerUtil.mutListener.listen(14670) ? (index >= 0) : (ListenerUtil.mutListener.listen(14669) ? (index <= 0) : (ListenerUtil.mutListener.listen(14668) ? (index > 0) : (ListenerUtil.mutListener.listen(14667) ? (index != 0) : (ListenerUtil.mutListener.listen(14666) ? (index == 0) : (index < 0)))))) && (ListenerUtil.mutListener.listen(14675) ? (index <= mDetails.length) : (ListenerUtil.mutListener.listen(14674) ? (index > mDetails.length) : (ListenerUtil.mutListener.listen(14673) ? (index < mDetails.length) : (ListenerUtil.mutListener.listen(14672) ? (index != mDetails.length) : (ListenerUtil.mutListener.listen(14671) ? (index == mDetails.length) : (index >= mDetails.length))))))) : ((ListenerUtil.mutListener.listen(14670) ? (index >= 0) : (ListenerUtil.mutListener.listen(14669) ? (index <= 0) : (ListenerUtil.mutListener.listen(14668) ? (index > 0) : (ListenerUtil.mutListener.listen(14667) ? (index != 0) : (ListenerUtil.mutListener.listen(14666) ? (index == 0) : (index < 0)))))) || (ListenerUtil.mutListener.listen(14675) ? (index <= mDetails.length) : (ListenerUtil.mutListener.listen(14674) ? (index > mDetails.length) : (ListenerUtil.mutListener.listen(14673) ? (index < mDetails.length) : (ListenerUtil.mutListener.listen(14672) ? (index != mDetails.length) : (ListenerUtil.mutListener.listen(14671) ? (index == mDetails.length) : (index >= mDetails.length))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14678)) {
            mDetails = ArrayUtils.remove(mDetails, index);
        }
        if (!ListenerUtil.mutListener.listen(14679)) {
            mListAdapter = new DetailListAdapter(getContext(), R.layout.detail_list_preference, mDetails);
        }
    }

    public void refreshAdapter() {
        if (!ListenerUtil.mutListener.listen(14681)) {
            if (mListAdapter != null) {
                if (!ListenerUtil.mutListener.listen(14680)) {
                    mListAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void setDetails(String[] details) {
        if (!ListenerUtil.mutListener.listen(14682)) {
            mDetails = details;
        }
        if (!ListenerUtil.mutListener.listen(14683)) {
            refreshAdapter();
        }
    }

    private class DetailListAdapter extends ArrayAdapter<String> {

        DetailListAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        @NotNull
        @Override
        public View getView(final int position, View convertView, @NotNull ViewGroup parent) {
            if (!ListenerUtil.mutListener.listen(14685)) {
                if (convertView == null) {
                    if (!ListenerUtil.mutListener.listen(14684)) {
                        convertView = View.inflate(getContext(), R.layout.detail_list_preference, null);
                    }
                }
            }
            final RadioButton radioButton = convertView.findViewById(R.id.radio);
            TextView mainText = convertView.findViewById(R.id.main_text);
            TextView detailText = convertView.findViewById(R.id.detail_text);
            if (!ListenerUtil.mutListener.listen(14694)) {
                if ((ListenerUtil.mutListener.listen(14692) ? ((ListenerUtil.mutListener.listen(14686) ? (mainText != null || getEntries() != null) : (mainText != null && getEntries() != null)) || (ListenerUtil.mutListener.listen(14691) ? (position >= getEntries().length) : (ListenerUtil.mutListener.listen(14690) ? (position <= getEntries().length) : (ListenerUtil.mutListener.listen(14689) ? (position > getEntries().length) : (ListenerUtil.mutListener.listen(14688) ? (position != getEntries().length) : (ListenerUtil.mutListener.listen(14687) ? (position == getEntries().length) : (position < getEntries().length))))))) : ((ListenerUtil.mutListener.listen(14686) ? (mainText != null || getEntries() != null) : (mainText != null && getEntries() != null)) && (ListenerUtil.mutListener.listen(14691) ? (position >= getEntries().length) : (ListenerUtil.mutListener.listen(14690) ? (position <= getEntries().length) : (ListenerUtil.mutListener.listen(14689) ? (position > getEntries().length) : (ListenerUtil.mutListener.listen(14688) ? (position != getEntries().length) : (ListenerUtil.mutListener.listen(14687) ? (position == getEntries().length) : (position < getEntries().length))))))))) {
                    if (!ListenerUtil.mutListener.listen(14693)) {
                        mainText.setText(getEntries()[position]);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14706)) {
                if (detailText != null) {
                    if (!ListenerUtil.mutListener.listen(14705)) {
                        if ((ListenerUtil.mutListener.listen(14701) ? ((ListenerUtil.mutListener.listen(14700) ? (mDetails != null || (ListenerUtil.mutListener.listen(14699) ? (position >= mDetails.length) : (ListenerUtil.mutListener.listen(14698) ? (position <= mDetails.length) : (ListenerUtil.mutListener.listen(14697) ? (position > mDetails.length) : (ListenerUtil.mutListener.listen(14696) ? (position != mDetails.length) : (ListenerUtil.mutListener.listen(14695) ? (position == mDetails.length) : (position < mDetails.length))))))) : (mDetails != null && (ListenerUtil.mutListener.listen(14699) ? (position >= mDetails.length) : (ListenerUtil.mutListener.listen(14698) ? (position <= mDetails.length) : (ListenerUtil.mutListener.listen(14697) ? (position > mDetails.length) : (ListenerUtil.mutListener.listen(14696) ? (position != mDetails.length) : (ListenerUtil.mutListener.listen(14695) ? (position == mDetails.length) : (position < mDetails.length)))))))) || !TextUtils.isEmpty(mDetails[position])) : ((ListenerUtil.mutListener.listen(14700) ? (mDetails != null || (ListenerUtil.mutListener.listen(14699) ? (position >= mDetails.length) : (ListenerUtil.mutListener.listen(14698) ? (position <= mDetails.length) : (ListenerUtil.mutListener.listen(14697) ? (position > mDetails.length) : (ListenerUtil.mutListener.listen(14696) ? (position != mDetails.length) : (ListenerUtil.mutListener.listen(14695) ? (position == mDetails.length) : (position < mDetails.length))))))) : (mDetails != null && (ListenerUtil.mutListener.listen(14699) ? (position >= mDetails.length) : (ListenerUtil.mutListener.listen(14698) ? (position <= mDetails.length) : (ListenerUtil.mutListener.listen(14697) ? (position > mDetails.length) : (ListenerUtil.mutListener.listen(14696) ? (position != mDetails.length) : (ListenerUtil.mutListener.listen(14695) ? (position == mDetails.length) : (position < mDetails.length)))))))) && !TextUtils.isEmpty(mDetails[position])))) {
                            if (!ListenerUtil.mutListener.listen(14703)) {
                                detailText.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(14704)) {
                                detailText.setText(mDetails[position]);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(14702)) {
                                detailText.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14713)) {
                if (radioButton != null) {
                    if (!ListenerUtil.mutListener.listen(14712)) {
                        radioButton.setChecked((ListenerUtil.mutListener.listen(14711) ? (mSelectedIndex >= position) : (ListenerUtil.mutListener.listen(14710) ? (mSelectedIndex <= position) : (ListenerUtil.mutListener.listen(14709) ? (mSelectedIndex > position) : (ListenerUtil.mutListener.listen(14708) ? (mSelectedIndex < position) : (ListenerUtil.mutListener.listen(14707) ? (mSelectedIndex != position) : (mSelectedIndex == position)))))));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14714)) {
                convertView.setOnClickListener(v -> changeSelection(position));
            }
            return convertView;
        }

        private void changeSelection(int position) {
            if (!ListenerUtil.mutListener.listen(14715)) {
                mSelectedIndex = position;
            }
            if (!ListenerUtil.mutListener.listen(14716)) {
                notifyDataSetChanged();
            }
        }
    }
}
