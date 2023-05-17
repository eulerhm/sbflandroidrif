/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.LocaleService;
import ch.threema.app.threemasafe.ThreemaSafeService;
import ch.threema.app.utils.DialogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardSafeSearchPhoneDialog extends DialogFragment implements SelectorDialog.SelectorDialogClickListener {

    private static final String DIALOG_TAG_PROGRESS = "pro";

    private static final String DIALOG_TAG_SELECT_ID = "se";

    private WizardSafeSearchPhoneDialogCallback callback;

    private Activity activity;

    private ThreemaSafeService threemaSafeService;

    private LocaleService localeService;

    private EditText emailEditText, phoneEditText;

    private ArrayList<String> matchingIDs;

    public static WizardSafeSearchPhoneDialog newInstance() {
        return new WizardSafeSearchPhoneDialog();
    }

    public interface WizardSafeSearchPhoneDialogCallback {

        void onYes(String tag, String id);

        void onNo(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14576)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14577)) {
                callback = (WizardSafeSearchPhoneDialogCallback) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(14580)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(14578)) {
                    if (!(activity instanceof WizardSafeSearchPhoneDialogCallback)) {
                        throw new ClassCastException("Calling fragment must implement WizardSafeSearchPhoneDialogCallback interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(14579)) {
                    callback = (WizardSafeSearchPhoneDialogCallback) activity;
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14581)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14582)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_wizard_safe_search_phone, null);
        Button positiveButton = dialogView.findViewById(R.id.ok);
        final Button negativeButton = dialogView.findViewById(R.id.cancel);
        if (!ListenerUtil.mutListener.listen(14583)) {
            phoneEditText = dialogView.findViewById(R.id.safe_phone);
        }
        if (!ListenerUtil.mutListener.listen(14584)) {
            emailEditText = dialogView.findViewById(R.id.safe_email);
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.Threema_Dialog_Wizard);
        if (!ListenerUtil.mutListener.listen(14585)) {
            builder.setView(dialogView);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14587)) {
                threemaSafeService = ThreemaApplication.getServiceManager().getThreemaSafeService();
            }
            if (!ListenerUtil.mutListener.listen(14588)) {
                localeService = ThreemaApplication.getServiceManager().getLocaleService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(14586)) {
                dismiss();
            }
        }
        if (!ListenerUtil.mutListener.listen(14598)) {
            positiveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String phone = null, email = null;
                    if (!ListenerUtil.mutListener.listen(14590)) {
                        if (phoneEditText.getText() != null) {
                            if (!ListenerUtil.mutListener.listen(14589)) {
                                phone = localeService.getNormalizedPhoneNumber(phoneEditText.getText().toString());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14592)) {
                        if (emailEditText.getText() != null) {
                            if (!ListenerUtil.mutListener.listen(14591)) {
                                email = emailEditText.getText().toString();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14597)) {
                        if ((ListenerUtil.mutListener.listen(14593) ? (phone != null && email != null) : (phone != null || email != null))) {
                            if (!ListenerUtil.mutListener.listen(14596)) {
                                searchID(phone, email);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(14594)) {
                                dismiss();
                            }
                            if (!ListenerUtil.mutListener.listen(14595)) {
                                callback.onYes(tag, null);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14601)) {
            negativeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(14599)) {
                        dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(14600)) {
                        callback.onNo(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14609)) {
            if ((ListenerUtil.mutListener.listen(14606) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14605) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14604) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14603) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14602) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(14608)) {
                    phoneEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher(localeService.getCountryIsoCode()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14607)) {
                    phoneEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14610)) {
            setCancelable(false);
        }
        return builder.create();
    }

    private void searchID(String phone, String email) {
        if (!ListenerUtil.mutListener.listen(14611)) {
            new SearchIdTask(this).execute(phone, email);
        }
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(14612)) {
            callback.onNo(this.getTag());
        }
    }

    private static class SearchIdTask extends AsyncTask<String, Void, ArrayList<String>> {

        private WeakReference<WizardSafeSearchPhoneDialog> contextReference;

        SearchIdTask(WizardSafeSearchPhoneDialog context) {
            if (!ListenerUtil.mutListener.listen(14613)) {
                contextReference = new WeakReference<>(context);
            }
        }

        @Override
        protected void onPreExecute() {
            WizardSafeSearchPhoneDialog dialog = contextReference.get();
            if (!ListenerUtil.mutListener.listen(14616)) {
                if ((ListenerUtil.mutListener.listen(14615) ? ((ListenerUtil.mutListener.listen(14614) ? (dialog == null && dialog.isRemoving()) : (dialog == null || dialog.isRemoving())) && dialog.isDetached()) : ((ListenerUtil.mutListener.listen(14614) ? (dialog == null && dialog.isRemoving()) : (dialog == null || dialog.isRemoving())) || dialog.isDetached())))
                    return;
            }
            if (!ListenerUtil.mutListener.listen(14617)) {
                GenericProgressDialog.newInstance(R.string.safe_id_lookup, R.string.please_wait).show(dialog.getFragmentManager(), DIALOG_TAG_PROGRESS);
            }
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            return contextReference.get().threemaSafeService.searchID(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> ids) {
            final WizardSafeSearchPhoneDialog dialog = contextReference.get();
            if (!ListenerUtil.mutListener.listen(14620)) {
                if ((ListenerUtil.mutListener.listen(14619) ? ((ListenerUtil.mutListener.listen(14618) ? (dialog == null && dialog.isRemoving()) : (dialog == null || dialog.isRemoving())) && dialog.isDetached()) : ((ListenerUtil.mutListener.listen(14618) ? (dialog == null && dialog.isRemoving()) : (dialog == null || dialog.isRemoving())) || dialog.isDetached())))
                    return;
            }
            if (!ListenerUtil.mutListener.listen(14621)) {
                dialog.matchingIDs = ids;
            }
            if (!ListenerUtil.mutListener.listen(14622)) {
                DialogUtil.dismissDialog(dialog.getFragmentManager(), DIALOG_TAG_PROGRESS, true);
            }
            if (!ListenerUtil.mutListener.listen(14634)) {
                if (ids != null) {
                    if (!ListenerUtil.mutListener.listen(14633)) {
                        if ((ListenerUtil.mutListener.listen(14628) ? (ids.size() >= 1) : (ListenerUtil.mutListener.listen(14627) ? (ids.size() <= 1) : (ListenerUtil.mutListener.listen(14626) ? (ids.size() > 1) : (ListenerUtil.mutListener.listen(14625) ? (ids.size() < 1) : (ListenerUtil.mutListener.listen(14624) ? (ids.size() != 1) : (ids.size() == 1))))))) {
                            if (!ListenerUtil.mutListener.listen(14631)) {
                                dialog.callback.onYes(dialog.getTag(), ids.get(0));
                            }
                            if (!ListenerUtil.mutListener.listen(14632)) {
                                dialog.dismiss();
                            }
                        } else {
                            SelectorDialog selectorDialog = SelectorDialog.newInstance(dialog.getString(R.string.safe_select_id), ids, null);
                            if (!ListenerUtil.mutListener.listen(14629)) {
                                selectorDialog.setTargetFragment(dialog, 0);
                            }
                            if (!ListenerUtil.mutListener.listen(14630)) {
                                selectorDialog.show(dialog.getFragmentManager(), DIALOG_TAG_SELECT_ID);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(14623)) {
                        Toast.makeText(dialog.getActivity(), R.string.safe_no_id_found, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(String tag, int which, Object data) {
        if (!ListenerUtil.mutListener.listen(14635)) {
            callback.onYes(getTag(), matchingIDs.get(which));
        }
        if (!ListenerUtil.mutListener.listen(14636)) {
            dismiss();
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void onNo(String tag) {
    }
}
