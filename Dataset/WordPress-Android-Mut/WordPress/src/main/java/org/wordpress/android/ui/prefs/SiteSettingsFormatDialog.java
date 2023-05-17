package org.wordpress.android.ui.prefs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.radiobutton.MaterialRadioButton;
import org.wordpress.android.Constants;
import org.wordpress.android.R;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.EditTextUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SiteSettingsFormatDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public enum FormatType {

        DATE_FORMAT, TIME_FORMAT;

        public String[] getEntries(@NonNull Context context) {
            if (this == FormatType.DATE_FORMAT) {
                return context.getResources().getStringArray(R.array.date_format_entries);
            } else {
                return context.getResources().getStringArray(R.array.time_format_entries);
            }
        }

        public String[] getValues(@NonNull Context context) {
            if (this == FormatType.DATE_FORMAT) {
                return context.getResources().getStringArray(R.array.date_format_values);
            } else {
                return context.getResources().getStringArray(R.array.time_format_values);
            }
        }
    }

    private static final String KEY_FORMAT_TYPE = "format_type";

    public static final String KEY_FORMAT_VALUE = "format_value";

    private String mFormatValue;

    private boolean mConfirmed;

    private EditText mEditCustomFormat;

    private RadioGroup mRadioGroup;

    private String[] mEntries;

    private String[] mValues;

    public static SiteSettingsFormatDialog newInstance(@NonNull FormatType formatType, @NonNull String formatValue) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15042)) {
            args.putSerializable(KEY_FORMAT_TYPE, formatType);
        }
        if (!ListenerUtil.mutListener.listen(15043)) {
            args.putString(KEY_FORMAT_VALUE, formatValue);
        }
        SiteSettingsFormatDialog dialog = new SiteSettingsFormatDialog();
        if (!ListenerUtil.mutListener.listen(15044)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.site_settings_format_dialog, null);
        TextView txtTitle = view.findViewById(R.id.text_title);
        TextView txtHelp = view.findViewById(R.id.text_help);
        if (!ListenerUtil.mutListener.listen(15045)) {
            mEditCustomFormat = view.findViewById(R.id.edit_custom);
        }
        if (!ListenerUtil.mutListener.listen(15046)) {
            mRadioGroup = view.findViewById(R.id.radio_group);
        }
        Bundle args = getArguments();
        FormatType formatType = (FormatType) args.getSerializable(KEY_FORMAT_TYPE);
        if (!ListenerUtil.mutListener.listen(15048)) {
            if (formatType == null) {
                if (!ListenerUtil.mutListener.listen(15047)) {
                    formatType = FormatType.DATE_FORMAT;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15049)) {
            mFormatValue = args.getString(KEY_FORMAT_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(15050)) {
            mEntries = formatType.getEntries(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(15051)) {
            mValues = formatType.getValues(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(15052)) {
            createRadioButtons();
        }
        boolean isCustomFormat = isCustomFormatValue(mFormatValue);
        if (!ListenerUtil.mutListener.listen(15053)) {
            mEditCustomFormat.setEnabled(isCustomFormat);
        }
        if (!ListenerUtil.mutListener.listen(15055)) {
            if (isCustomFormat) {
                if (!ListenerUtil.mutListener.listen(15054)) {
                    mEditCustomFormat.setText(mFormatValue);
                }
            }
        }
        @StringRes
        int titleRes = formatType == FormatType.DATE_FORMAT ? R.string.site_settings_date_format_title : R.string.site_settings_time_format_title;
        if (!ListenerUtil.mutListener.listen(15056)) {
            txtTitle.setText(titleRes);
        }
        if (!ListenerUtil.mutListener.listen(15057)) {
            txtHelp.setOnClickListener(v -> ActivityLauncher.openUrlExternal(v.getContext(), Constants.URL_DATETIME_FORMAT_HELP));
        }
        int topOffset = getResources().getDimensionPixelOffset(R.dimen.settings_fragment_dialog_vertical_inset);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity()).setBackgroundInsetTop(topOffset).setBackgroundInsetBottom(topOffset);
        if (!ListenerUtil.mutListener.listen(15058)) {
            builder.setPositiveButton(android.R.string.ok, this);
        }
        if (!ListenerUtil.mutListener.listen(15059)) {
            builder.setNegativeButton(R.string.cancel, this);
        }
        if (!ListenerUtil.mutListener.listen(15060)) {
            builder.setView(view);
        }
        return builder.create();
    }

    private void createRadioButtons() {
        boolean isCustomFormat = isCustomFormatValue(mFormatValue);
        int margin = getResources().getDimensionPixelSize(R.dimen.margin_small);
        if (!ListenerUtil.mutListener.listen(15075)) {
            {
                long _loopCounter250 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(15074) ? (i >= mEntries.length) : (ListenerUtil.mutListener.listen(15073) ? (i <= mEntries.length) : (ListenerUtil.mutListener.listen(15072) ? (i > mEntries.length) : (ListenerUtil.mutListener.listen(15071) ? (i != mEntries.length) : (ListenerUtil.mutListener.listen(15070) ? (i == mEntries.length) : (i < mEntries.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter250", ++_loopCounter250);
                    MaterialRadioButton radio = new MaterialRadioButton(getActivity());
                    if (!ListenerUtil.mutListener.listen(15061)) {
                        radio.setText(mEntries[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(15062)) {
                        radio.setId(i);
                    }
                    if (!ListenerUtil.mutListener.listen(15063)) {
                        mRadioGroup.addView(radio);
                    }
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) radio.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(15064)) {
                        params.topMargin = margin;
                    }
                    if (!ListenerUtil.mutListener.listen(15065)) {
                        params.bottomMargin = margin;
                    }
                    if (!ListenerUtil.mutListener.listen(15069)) {
                        if ((ListenerUtil.mutListener.listen(15066) ? (isCustomFormat || isCustomFormatEntry(mEntries[i])) : (isCustomFormat && isCustomFormatEntry(mEntries[i])))) {
                            if (!ListenerUtil.mutListener.listen(15068)) {
                                radio.setChecked(true);
                            }
                        } else if (mValues[i].equals(mFormatValue)) {
                            if (!ListenerUtil.mutListener.listen(15067)) {
                                radio.setChecked(true);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15076)) {
            mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> mEditCustomFormat.setEnabled(isCustomFormatEntry(mEntries[checkedId])));
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(15077)) {
            mConfirmed = which == DialogInterface.BUTTON_POSITIVE;
        }
        if (!ListenerUtil.mutListener.listen(15078)) {
            dismiss();
        }
    }

    private boolean isCustomFormatEntry(@NonNull String entry) {
        String customEntry = getString(R.string.site_settings_format_entry_custom);
        return entry.equals(customEntry);
    }

    private boolean isCustomFormatValue(@NonNull String value) {
        if (!ListenerUtil.mutListener.listen(15080)) {
            {
                long _loopCounter251 = 0;
                for (String thisValue : mValues) {
                    ListenerUtil.loopListener.listen("_loopCounter251", ++_loopCounter251);
                    if (!ListenerUtil.mutListener.listen(15079)) {
                        if (thisValue.equals(value)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private String getSelectedFormatValue() {
        int id = mRadioGroup.getCheckedRadioButtonId();
        if (!ListenerUtil.mutListener.listen(15086)) {
            if ((ListenerUtil.mutListener.listen(15085) ? (id >= -1) : (ListenerUtil.mutListener.listen(15084) ? (id <= -1) : (ListenerUtil.mutListener.listen(15083) ? (id > -1) : (ListenerUtil.mutListener.listen(15082) ? (id < -1) : (ListenerUtil.mutListener.listen(15081) ? (id != -1) : (id == -1))))))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(15087)) {
            if (isCustomFormatEntry(mEntries[id])) {
                return EditTextUtils.getText(mEditCustomFormat);
            }
        }
        return mValues[id];
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(15088)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(15089)) {
            UiHelpers.Companion.adjustDialogSize(getDialog());
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        String formatValue = getSelectedFormatValue();
        // See https://developer.android.com/reference/android/app/Fragment
        android.app.Fragment target = getTargetFragment();
        if (!ListenerUtil.mutListener.listen(15093)) {
            if ((ListenerUtil.mutListener.listen(15091) ? ((ListenerUtil.mutListener.listen(15090) ? (mConfirmed || target != null) : (mConfirmed && target != null)) || !TextUtils.isEmpty(formatValue)) : ((ListenerUtil.mutListener.listen(15090) ? (mConfirmed || target != null) : (mConfirmed && target != null)) && !TextUtils.isEmpty(formatValue)))) {
                Intent intent = new Intent().putExtra(KEY_FORMAT_VALUE, formatValue);
                if (!ListenerUtil.mutListener.listen(15092)) {
                    target.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15094)) {
            super.onDismiss(dialog);
        }
    }
}
