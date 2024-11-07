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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 *  A simple string dialog with a "don't show again" checkbox
 *  If the checkbox has not previously been checked, the dialog will be shown, otherwise nothing will happen
 *  Make sure to use a unique tag for this dialog in the show() method
 */
public class ShowOnceDialog extends ThreemaDialogFragment {

    private AlertDialog alertDialog;

    private Activity activity;

    private static String PREF_PREFIX = "dialog_";

    public static ShowOnceDialog newInstance(@StringRes int title, @StringRes int message) {
        ShowOnceDialog dialog = new ShowOnceDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14226)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14227)) {
            args.putInt("messageInt", message);
        }
        if (!ListenerUtil.mutListener.listen(14228)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14229)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14230)) {
            this.activity = activity;
        }
    }

    @Override
    public // generally allow state loss for simple string alerts
    void show(FragmentManager manager, String tag) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ThreemaApplication.getAppContext());
        if (!ListenerUtil.mutListener.listen(14233)) {
            if (!sharedPreferences.getBoolean(PREF_PREFIX + tag, false)) {
                FragmentTransaction ft = manager.beginTransaction();
                if (!ListenerUtil.mutListener.listen(14231)) {
                    ft.add(this, tag);
                }
                if (!ListenerUtil.mutListener.listen(14232)) {
                    ft.commitAllowingStateLoss();
                }
            }
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ThreemaApplication.getAppContext());
        @StringRes
        int title = getArguments().getInt("title");
        @StringRes
        int messageInt = getArguments().getInt("messageInt");
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_show_once, null);
        final TextView textView = dialogView.findViewById(R.id.message);
        final AppCompatCheckBox checkbox = dialogView.findViewById(R.id.checkbox);
        if (!ListenerUtil.mutListener.listen(14234)) {
            checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> sharedPreferences.edit().putBoolean(PREF_PREFIX + getTag(), isChecked).apply());
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(14235)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(14236)) {
            builder.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(14243)) {
            if ((ListenerUtil.mutListener.listen(14241) ? (title >= -1) : (ListenerUtil.mutListener.listen(14240) ? (title <= -1) : (ListenerUtil.mutListener.listen(14239) ? (title > -1) : (ListenerUtil.mutListener.listen(14238) ? (title < -1) : (ListenerUtil.mutListener.listen(14237) ? (title == -1) : (title != -1))))))) {
                if (!ListenerUtil.mutListener.listen(14242)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14244)) {
            builder.setPositiveButton(getString(R.string.ok), null);
        }
        if (!ListenerUtil.mutListener.listen(14245)) {
            textView.setText(messageInt);
        }
        if (!ListenerUtil.mutListener.listen(14246)) {
            setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(14247)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }
}
