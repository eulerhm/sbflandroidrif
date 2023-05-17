package org.wordpress.android.ui.prefs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeleteSiteDialogFragment extends DialogFragment implements TextWatcher, DialogInterface.OnShowListener {

    public static final String SITE_DOMAIN_KEY = "site-domain";

    private AlertDialog mDeleteSiteDialog;

    private EditText mUrlConfirmation;

    private Button mDeleteButton;

    private String mSiteDomain = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(14564)) {
            retrieveSiteDomain();
        }
        if (!ListenerUtil.mutListener.listen(14565)) {
            configureAlertViewBuilder(builder);
        }
        if (!ListenerUtil.mutListener.listen(14566)) {
            mDeleteSiteDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(14567)) {
            mDeleteSiteDialog.setOnShowListener(this);
        }
        return mDeleteSiteDialog;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!ListenerUtil.mutListener.listen(14570)) {
            if (isUrlConfirmationTextValid()) {
                if (!ListenerUtil.mutListener.listen(14569)) {
                    mDeleteButton.setEnabled(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14568)) {
                    mDeleteButton.setEnabled(false);
                }
            }
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(14571)) {
            mDeleteButton = mDeleteSiteDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        }
        if (!ListenerUtil.mutListener.listen(14572)) {
            mDeleteButton.setEnabled(false);
        }
    }

    private void configureAlertViewBuilder(AlertDialog.Builder builder) {
        if (!ListenerUtil.mutListener.listen(14573)) {
            builder.setTitle(R.string.confirm_delete_site);
        }
        if (!ListenerUtil.mutListener.listen(14574)) {
            builder.setMessage(confirmationPromptString());
        }
        if (!ListenerUtil.mutListener.listen(14575)) {
            configureUrlConfirmation(builder);
        }
        if (!ListenerUtil.mutListener.listen(14576)) {
            configureButtons(builder);
        }
    }

    private void configureButtons(AlertDialog.Builder builder) {
        if (!ListenerUtil.mutListener.listen(14578)) {
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(14577)) {
                        dismiss();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14582)) {
            builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // See https://developer.android.com/reference/android/app/Fragment
                    android.app.Fragment target = getTargetFragment();
                    if (!ListenerUtil.mutListener.listen(14580)) {
                        if (target != null) {
                            if (!ListenerUtil.mutListener.listen(14579)) {
                                target.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14581)) {
                        dismiss();
                    }
                }
            });
        }
    }

    private Spannable confirmationPromptString() {
        String deletePrompt = String.format(getString(R.string.confirm_delete_site_prompt), mSiteDomain);
        Spannable promptSpannable = new SpannableString(deletePrompt);
        int beginning = deletePrompt.indexOf(mSiteDomain);
        int end = (ListenerUtil.mutListener.listen(14586) ? (beginning % mSiteDomain.length()) : (ListenerUtil.mutListener.listen(14585) ? (beginning / mSiteDomain.length()) : (ListenerUtil.mutListener.listen(14584) ? (beginning * mSiteDomain.length()) : (ListenerUtil.mutListener.listen(14583) ? (beginning - mSiteDomain.length()) : (beginning + mSiteDomain.length())))));
        if (!ListenerUtil.mutListener.listen(14587)) {
            promptSpannable.setSpan(new StyleSpan(Typeface.BOLD), beginning, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return promptSpannable;
    }

    private void configureUrlConfirmation(AlertDialog.Builder builder) {
        // noinspection InflateParams
        View view = getActivity().getLayoutInflater().inflate(R.layout.delete_site_dialog, null);
        if (!ListenerUtil.mutListener.listen(14588)) {
            mUrlConfirmation = (EditText) view.findViewById(R.id.url_confirmation);
        }
        if (!ListenerUtil.mutListener.listen(14589)) {
            mUrlConfirmation.addTextChangedListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14590)) {
            builder.setView(view);
        }
    }

    private void retrieveSiteDomain() {
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(14591)) {
            mSiteDomain = getString(R.string.wordpress_dot_com).toLowerCase(Locale.ROOT);
        }
        if (!ListenerUtil.mutListener.listen(14593)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(14592)) {
                    mSiteDomain = args.getString(SITE_DOMAIN_KEY);
                }
            }
        }
    }

    private boolean isUrlConfirmationTextValid() {
        String confirmationText = mUrlConfirmation.getText().toString().trim().toLowerCase(Locale.ROOT);
        String hintText = mSiteDomain.toLowerCase(Locale.ROOT);
        return confirmationText.equals(hintText);
    }
}
