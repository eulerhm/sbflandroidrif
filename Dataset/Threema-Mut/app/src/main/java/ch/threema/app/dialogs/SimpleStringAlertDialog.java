/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
import android.os.Bundle;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ch.threema.app.R;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SimpleStringAlertDialog extends ThreemaDialogFragment {

    private AlertDialog alertDialog;

    private Activity activity;

    public static SimpleStringAlertDialog newInstance(int title, CharSequence message) {
        SimpleStringAlertDialog dialog = new SimpleStringAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14248)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14249)) {
            args.putCharSequence("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14250)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static SimpleStringAlertDialog newInstance(int title, int message) {
        SimpleStringAlertDialog dialog = new SimpleStringAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14251)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14252)) {
            args.putInt("messageInt", message);
        }
        if (!ListenerUtil.mutListener.listen(14253)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static SimpleStringAlertDialog newInstance(int title, int message, boolean noButton) {
        SimpleStringAlertDialog dialog = new SimpleStringAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14254)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14255)) {
            args.putInt("messageInt", message);
        }
        if (!ListenerUtil.mutListener.listen(14256)) {
            args.putBoolean("noButton", noButton);
        }
        if (!ListenerUtil.mutListener.listen(14257)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(14258)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14259)) {
            this.activity = activity;
        }
    }

    @Override
    public // generally allow state loss for simple string alerts
    void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        if (!ListenerUtil.mutListener.listen(14260)) {
            ft.add(this, tag);
        }
        if (!ListenerUtil.mutListener.listen(14261)) {
            ft.commitAllowingStateLoss();
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int messageInt = getArguments().getInt("messageInt");
        CharSequence message = getArguments().getCharSequence("message");
        boolean noButton = getArguments().getBoolean("noButton", false);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme()).setCancelable(false);
        if (!ListenerUtil.mutListener.listen(14268)) {
            if ((ListenerUtil.mutListener.listen(14266) ? (title >= -1) : (ListenerUtil.mutListener.listen(14265) ? (title <= -1) : (ListenerUtil.mutListener.listen(14264) ? (title > -1) : (ListenerUtil.mutListener.listen(14263) ? (title < -1) : (ListenerUtil.mutListener.listen(14262) ? (title == -1) : (title != -1))))))) {
                if (!ListenerUtil.mutListener.listen(14267)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14271)) {
            if (!noButton) {
                if (!ListenerUtil.mutListener.listen(14270)) {
                    builder.setPositiveButton(getString(R.string.ok), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14269)) {
                    setCancelable(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14274)) {
            if (TestUtil.empty(message)) {
                if (!ListenerUtil.mutListener.listen(14273)) {
                    builder.setMessage(messageInt);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14272)) {
                    builder.setMessage(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14275)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }
}
