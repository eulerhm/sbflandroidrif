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
import android.os.Bundle;
import android.view.View;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardRestoreSelectorDialog extends DialogFragment {

    private WizardRestoreSelectorDialogCallback callback;

    private Activity activity;

    public static WizardRestoreSelectorDialog newInstance() {
        return new WizardRestoreSelectorDialog();
    }

    public interface WizardRestoreSelectorDialogCallback {

        void onNo(String tag);

        void onDataBackupRestore();

        void onIdBackupRestore();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14558)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14559)) {
                callback = (WizardRestoreSelectorDialogCallback) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(14562)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(14560)) {
                    if (!(activity instanceof WizardRestoreSelectorDialogCallback)) {
                        throw new ClassCastException("Calling fragment must implement WizardRestoreSelectorDialogCallback interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(14561)) {
                    callback = (WizardRestoreSelectorDialogCallback) activity;
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14563)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14564)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_wizard_restore_selector, null);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.Threema_Dialog_Wizard);
        if (!ListenerUtil.mutListener.listen(14565)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(14568)) {
            dialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(14566)) {
                        dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(14567)) {
                        callback.onNo(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14571)) {
            dialogView.findViewById(R.id.id_backup).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(14569)) {
                        dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(14570)) {
                        callback.onIdBackupRestore();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14574)) {
            dialogView.findViewById(R.id.data_backup).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(14572)) {
                        dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(14573)) {
                        callback.onDataBackupRestore();
                    }
                }
            });
        }
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(14575)) {
            callback.onNo(this.getTag());
        }
    }
}
