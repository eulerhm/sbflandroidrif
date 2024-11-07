package org.wordpress.android.ui.posts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import org.wordpress.android.R;
import org.wordpress.android.util.ActivityUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PostSettingsInputDialogFragment extends DialogFragment implements TextWatcher {

    public static final String TAG = "post_settings_input_dialog_fragment";

    public interface PostSettingsInputDialogListener {

        void onInputUpdated(String input);
    }

    private static final String INPUT_TAG = "input";

    private static final String TITLE_TAG = "title";

    private static final String HINT_TAG = "hint";

    private static final String DISABLE_EMPTY_INPUT_TAG = "disable_empty_input";

    private String mCurrentInput;

    private String mTitle;

    private String mHint;

    private boolean mDisableEmptyInput;

    private PostSettingsInputDialogListener mListener;

    private AlertDialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12801)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12810)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(12806)) {
                    mCurrentInput = savedInstanceState.getString(INPUT_TAG, "");
                }
                if (!ListenerUtil.mutListener.listen(12807)) {
                    mTitle = savedInstanceState.getString(TITLE_TAG, "");
                }
                if (!ListenerUtil.mutListener.listen(12808)) {
                    mHint = savedInstanceState.getString(HINT_TAG, "");
                }
                if (!ListenerUtil.mutListener.listen(12809)) {
                    mDisableEmptyInput = savedInstanceState.getBoolean(DISABLE_EMPTY_INPUT_TAG, false);
                }
            } else if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(12802)) {
                    mCurrentInput = getArguments().getString(INPUT_TAG, "");
                }
                if (!ListenerUtil.mutListener.listen(12803)) {
                    mTitle = getArguments().getString(TITLE_TAG, "");
                }
                if (!ListenerUtil.mutListener.listen(12804)) {
                    mHint = getArguments().getString(HINT_TAG, "");
                }
                if (!ListenerUtil.mutListener.listen(12805)) {
                    mDisableEmptyInput = getArguments().getBoolean(DISABLE_EMPTY_INPUT_TAG, false);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(12811)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(12812)) {
            outState.putSerializable(INPUT_TAG, mCurrentInput);
        }
        if (!ListenerUtil.mutListener.listen(12813)) {
            outState.putSerializable(TITLE_TAG, mTitle);
        }
        if (!ListenerUtil.mutListener.listen(12814)) {
            outState.putSerializable(HINT_TAG, mHint);
        }
        if (!ListenerUtil.mutListener.listen(12815)) {
            outState.putBoolean(DISABLE_EMPTY_INPUT_TAG, mDisableEmptyInput);
        }
    }

    public static PostSettingsInputDialogFragment newInstance(String currentText, String title, String hint, boolean disableEmptyInput) {
        PostSettingsInputDialogFragment dialogFragment = new PostSettingsInputDialogFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(12816)) {
            args.putString(INPUT_TAG, currentText);
        }
        if (!ListenerUtil.mutListener.listen(12817)) {
            args.putString(TITLE_TAG, title);
        }
        if (!ListenerUtil.mutListener.listen(12818)) {
            args.putString(HINT_TAG, hint);
        }
        if (!ListenerUtil.mutListener.listen(12819)) {
            args.putBoolean(DISABLE_EMPTY_INPUT_TAG, disableEmptyInput);
        }
        if (!ListenerUtil.mutListener.listen(12820)) {
            dialogFragment.setArguments(args);
        }
        return dialogFragment;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(12821)) {
            super.onDismiss(dialog);
        }
        if (!ListenerUtil.mutListener.listen(12822)) {
            ActivityUtils.hideKeyboard(getActivity());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        // noinspection InflateParams
        View dialogView = layoutInflater.inflate(R.layout.post_settings_input_dialog, null);
        if (!ListenerUtil.mutListener.listen(12823)) {
            builder.setView(dialogView);
        }
        final EditText editText = dialogView.findViewById(R.id.post_settings_input_dialog_edit_text);
        if (!ListenerUtil.mutListener.listen(12826)) {
            if (!TextUtils.isEmpty(mCurrentInput)) {
                if (!ListenerUtil.mutListener.listen(12824)) {
                    editText.setText(mCurrentInput);
                }
                if (!ListenerUtil.mutListener.listen(12825)) {
                    // move the cursor to the end
                    editText.setSelection(mCurrentInput.length());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12827)) {
            editText.addTextChangedListener(this);
        }
        TextInputLayout textInputLayout = dialogView.findViewById(R.id.post_settings_input_dialog_input_layout);
        if (!ListenerUtil.mutListener.listen(12828)) {
            textInputLayout.setHint(mTitle);
        }
        TextView hintTextView = dialogView.findViewById(R.id.post_settings_input_dialog_hint);
        if (!ListenerUtil.mutListener.listen(12829)) {
            hintTextView.setText(mHint);
        }
        if (!ListenerUtil.mutListener.listen(12830)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(12834)) {
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(12831)) {
                        mCurrentInput = editText.getText().toString();
                    }
                    if (!ListenerUtil.mutListener.listen(12833)) {
                        if (mListener != null) {
                            if (!ListenerUtil.mutListener.listen(12832)) {
                                mListener.onInputUpdated(mCurrentInput);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12835)) {
            mDialog = builder.create();
        }
        return mDialog;
    }

    public void setPostSettingsInputDialogListener(PostSettingsInputDialogListener listener) {
        if (!ListenerUtil.mutListener.listen(12836)) {
            mListener = listener;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!ListenerUtil.mutListener.listen(12839)) {
            if (mDialog != null) {
                boolean disabled = (ListenerUtil.mutListener.listen(12837) ? (mDisableEmptyInput || TextUtils.isEmpty(editable)) : (mDisableEmptyInput && TextUtils.isEmpty(editable)));
                if (!ListenerUtil.mutListener.listen(12838)) {
                    mDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(!disabled);
                }
            }
        }
    }
}
