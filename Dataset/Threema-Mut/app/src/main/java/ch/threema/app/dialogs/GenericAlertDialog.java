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
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GenericAlertDialog extends ThreemaDialogFragment {

    private DialogClickListener callback;

    private Activity activity;

    private AlertDialog alertDialog;

    private boolean isHtml;

    public static GenericAlertDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative) {
        GenericAlertDialog dialog = new GenericAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13502)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13503)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(13504)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13505)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13506)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static GenericAlertDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative, boolean cancelable) {
        GenericAlertDialog dialog = new GenericAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13507)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13508)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(13509)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13510)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13511)) {
            args.putBoolean("cancelable", cancelable);
        }
        if (!ListenerUtil.mutListener.listen(13512)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static GenericAlertDialog newInstance(@StringRes int title, String messageString, @StringRes int positive, @StringRes int negative, boolean cancelable) {
        GenericAlertDialog dialog = new GenericAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13513)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13514)) {
            args.putString("messageString", messageString);
        }
        if (!ListenerUtil.mutListener.listen(13515)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13516)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13517)) {
            args.putBoolean("cancelable", cancelable);
        }
        if (!ListenerUtil.mutListener.listen(13518)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static GenericAlertDialog newInstanceHtml(@StringRes int title, String messageString, @StringRes int positive, @StringRes int negative, boolean cancelable) {
        GenericAlertDialog dialog = new GenericAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13519)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13520)) {
            args.putString("messageString", messageString);
        }
        if (!ListenerUtil.mutListener.listen(13521)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13522)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13523)) {
            args.putBoolean("cancelable", cancelable);
        }
        if (!ListenerUtil.mutListener.listen(13524)) {
            args.putBoolean("html", true);
        }
        if (!ListenerUtil.mutListener.listen(13525)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static GenericAlertDialog newInstance(@StringRes int title, CharSequence messageString, @StringRes int positive, @StringRes int negative) {
        GenericAlertDialog dialog = new GenericAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13526)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13527)) {
            args.putCharSequence("messageString", messageString);
        }
        if (!ListenerUtil.mutListener.listen(13528)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13529)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13530)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static GenericAlertDialog newInstance(String titleString, CharSequence messageString, @StringRes int positive, @StringRes int negative) {
        GenericAlertDialog dialog = new GenericAlertDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13531)) {
            args.putString("titleString", titleString);
        }
        if (!ListenerUtil.mutListener.listen(13532)) {
            args.putCharSequence("messageString", messageString);
        }
        if (!ListenerUtil.mutListener.listen(13533)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(13534)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(13535)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface DialogClickListener {

        void onYes(String tag, Object data);

        void onNo(String tag, Object data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13536)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13541)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13537)) {
                        callback = (DialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(13540)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(13539)) {
                            if ((activity instanceof DialogClickListener)) {
                                if (!ListenerUtil.mutListener.listen(13538)) {
                                    callback = (DialogClickListener) activity;
                                }
                            } else {
                                throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(13542)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13543)) {
            this.activity = activity;
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13544)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13547)) {
            if (isHtml) {
                View textView = alertDialog.findViewById(android.R.id.message);
                if (!ListenerUtil.mutListener.listen(13546)) {
                    if (textView instanceof TextView) {
                        if (!ListenerUtil.mutListener.listen(13545)) {
                            ((TextView) textView).setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                }
            }
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        String titleString = getArguments().getString("titleString");
        int message = getArguments().getInt("message");
        CharSequence messageString = getArguments().getCharSequence("messageString");
        int positive = getArguments().getInt("positive");
        int negative = getArguments().getInt("negative");
        boolean cancelable = getArguments().getBoolean("cancelable", true);
        if (!ListenerUtil.mutListener.listen(13548)) {
            isHtml = getArguments().getBoolean("html", false);
        }
        final String tag = this.getTag();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(13551)) {
            if (TestUtil.empty(titleString)) {
                if (!ListenerUtil.mutListener.listen(13550)) {
                    builder.setTitle(title);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13549)) {
                    builder.setTitle(titleString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13556)) {
            if (TextUtils.isEmpty(messageString)) {
                if (!ListenerUtil.mutListener.listen(13555)) {
                    builder.setMessage(message);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13554)) {
                    if (isHtml) {
                        if (!ListenerUtil.mutListener.listen(13553)) {
                            builder.setMessage(Html.fromHtml(messageString.toString()));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(13552)) {
                            builder.setMessage(messageString);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13557)) {
            builder.setPositiveButton(getString(positive), (dialog, whichButton) -> callback.onYes(tag, object));
        }
        if (!ListenerUtil.mutListener.listen(13564)) {
            if ((ListenerUtil.mutListener.listen(13562) ? (negative >= 0) : (ListenerUtil.mutListener.listen(13561) ? (negative <= 0) : (ListenerUtil.mutListener.listen(13560) ? (negative > 0) : (ListenerUtil.mutListener.listen(13559) ? (negative < 0) : (ListenerUtil.mutListener.listen(13558) ? (negative == 0) : (negative != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13563)) {
                    builder.setNegativeButton(getString(negative), (dialog, whichButton) -> callback.onNo(tag, object));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13565)) {
            alertDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(13567)) {
            if (!cancelable) {
                if (!ListenerUtil.mutListener.listen(13566)) {
                    setCancelable(false);
                }
            }
        }
        return alertDialog;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(13568)) {
            callback.onNo(getTag(), object);
        }
    }

    public GenericAlertDialog setTargetFragment(@Nullable Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(13569)) {
            setTargetFragment(fragment, 0);
        }
        return this;
    }

    public void showInActivity() {
        if (!ListenerUtil.mutListener.listen(13570)) {
            alertDialog.show();
        }
    }
}
