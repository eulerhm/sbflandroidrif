package org.wordpress.android.ui.prefs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NumberPickerDialog extends DialogFragment implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String SHOW_SWITCH_KEY = "show-switch";

    public static final String SWITCH_ENABLED_KEY = "switch-enabled";

    public static final String SWITCH_TITLE_KEY = "switch-title";

    public static final String SWITCH_DESC_KEY = "switch-description";

    public static final String TITLE_KEY = "dialog-title";

    public static final String HEADER_TEXT_KEY = "header-text";

    public static final String MIN_VALUE_KEY = "min-value";

    public static final String MAX_VALUE_KEY = "max-value";

    public static final String CUR_VALUE_KEY = "cur-value";

    private static final int DEFAULT_MIN_VALUE = 0;

    private static final int DEFAULT_MAX_VALUE = 99;

    private SwitchCompat mSwitch;

    private TextView mHeaderText;

    private NumberPicker mNumberPicker;

    private NumberPicker.Formatter mFormat;

    private int mMinValue;

    private int mMaxValue;

    private boolean mConfirmed;

    public NumberPickerDialog() {
        if (!ListenerUtil.mutListener.listen(14919)) {
            mMinValue = DEFAULT_MIN_VALUE;
        }
        if (!ListenerUtil.mutListener.listen(14920)) {
            mMaxValue = DEFAULT_MAX_VALUE;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int topOffset = getResources().getDimensionPixelOffset(R.dimen.settings_fragment_dialog_vertical_inset);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity()).setBackgroundInsetTop(topOffset).setBackgroundInsetBottom(topOffset);
        View view = View.inflate(getActivity(), R.layout.number_picker_dialog, null);
        TextView switchText = view.findViewById(R.id.number_picker_text);
        if (!ListenerUtil.mutListener.listen(14921)) {
            mSwitch = view.findViewById(R.id.number_picker_switch);
        }
        if (!ListenerUtil.mutListener.listen(14922)) {
            mHeaderText = view.findViewById(R.id.number_picker_header);
        }
        if (!ListenerUtil.mutListener.listen(14923)) {
            mNumberPicker = view.findViewById(R.id.number_picker);
        }
        int value = mMinValue;
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(14937)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(14930)) {
                    if (args.getBoolean(SHOW_SWITCH_KEY, false)) {
                        if (!ListenerUtil.mutListener.listen(14925)) {
                            mSwitch.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(14926)) {
                            mSwitch.setText(args.getString(SWITCH_TITLE_KEY, ""));
                        }
                        if (!ListenerUtil.mutListener.listen(14927)) {
                            mSwitch.setChecked(args.getBoolean(SWITCH_ENABLED_KEY, false));
                        }
                        final View toggleContainer = view.findViewById(R.id.number_picker_toggleable);
                        if (!ListenerUtil.mutListener.listen(14928)) {
                            toggleContainer.setEnabled(mSwitch.isChecked());
                        }
                        if (!ListenerUtil.mutListener.listen(14929)) {
                            mNumberPicker.setEnabled(mSwitch.isChecked());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14924)) {
                            mSwitch.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14931)) {
                    switchText.setText(args.getString(SWITCH_DESC_KEY, ""));
                }
                if (!ListenerUtil.mutListener.listen(14932)) {
                    mHeaderText.setText(args.getString(HEADER_TEXT_KEY, ""));
                }
                if (!ListenerUtil.mutListener.listen(14933)) {
                    mMinValue = args.getInt(MIN_VALUE_KEY, DEFAULT_MIN_VALUE);
                }
                if (!ListenerUtil.mutListener.listen(14934)) {
                    mMaxValue = args.getInt(MAX_VALUE_KEY, DEFAULT_MAX_VALUE);
                }
                if (!ListenerUtil.mutListener.listen(14935)) {
                    value = args.getInt(CUR_VALUE_KEY, mMinValue);
                }
                if (!ListenerUtil.mutListener.listen(14936)) {
                    builder.setCustomTitle(getDialogTitleView(args.getString(TITLE_KEY, "")));
                }
            }
        }
        // Fix for https://issuetracker.google.com/issues/36952035
        View editText = mNumberPicker.getChildAt(0);
        if (!ListenerUtil.mutListener.listen(14939)) {
            if (editText instanceof EditText) {
                if (!ListenerUtil.mutListener.listen(14938)) {
                    ((EditText) editText).setFilters(new InputFilter[0]);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14940)) {
            mNumberPicker.setFormatter(mFormat);
        }
        if (!ListenerUtil.mutListener.listen(14941)) {
            mNumberPicker.setMinValue(mMinValue);
        }
        if (!ListenerUtil.mutListener.listen(14942)) {
            mNumberPicker.setMaxValue(mMaxValue);
        }
        if (!ListenerUtil.mutListener.listen(14943)) {
            mNumberPicker.setValue(value);
        }
        if (!ListenerUtil.mutListener.listen(14944)) {
            mSwitch.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14946)) {
            // hide empty text views
            if (TextUtils.isEmpty(switchText.getText())) {
                if (!ListenerUtil.mutListener.listen(14945)) {
                    switchText.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14948)) {
            if (TextUtils.isEmpty(mHeaderText.getText())) {
                if (!ListenerUtil.mutListener.listen(14947)) {
                    mHeaderText.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14949)) {
            builder.setPositiveButton(android.R.string.ok, this);
        }
        if (!ListenerUtil.mutListener.listen(14950)) {
            builder.setNegativeButton(R.string.cancel, this);
        }
        if (!ListenerUtil.mutListener.listen(14951)) {
            builder.setView(view);
        }
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(14952)) {
            mConfirmed = which == DialogInterface.BUTTON_POSITIVE;
        }
        if (!ListenerUtil.mutListener.listen(14953)) {
            dismiss();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(14954)) {
            mNumberPicker.setEnabled(isChecked);
        }
        if (!ListenerUtil.mutListener.listen(14955)) {
            mHeaderText.setEnabled(isChecked);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // See https://developer.android.com/reference/android/app/Fragment
        android.app.Fragment target = getTargetFragment();
        if (!ListenerUtil.mutListener.listen(14957)) {
            if (target != null) {
                if (!ListenerUtil.mutListener.listen(14956)) {
                    target.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getResultIntent());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14958)) {
            super.onDismiss(dialog);
        }
    }

    public void setNumberFormat(NumberPicker.Formatter format) {
        if (!ListenerUtil.mutListener.listen(14959)) {
            mFormat = format;
        }
    }

    private View getDialogTitleView(String title) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams")
        View titleView = inflater.inflate(R.layout.detail_list_preference_title, null);
        TextView titleText = titleView.findViewById(R.id.title);
        if (!ListenerUtil.mutListener.listen(14960)) {
            titleText.setText(title);
        }
        if (!ListenerUtil.mutListener.listen(14961)) {
            titleText.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        }
        return titleView;
    }

    private Intent getResultIntent() {
        if (!ListenerUtil.mutListener.listen(14962)) {
            if (mConfirmed) {
                return new Intent().putExtra(SWITCH_ENABLED_KEY, mSwitch.isChecked()).putExtra(CUR_VALUE_KEY, mNumberPicker.getValue());
            }
        }
        return null;
    }
}
