/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
import android.os.Parcelable;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SelectorDialog extends ThreemaDialogFragment {

    private SelectorDialogClickListener callback;

    private SelectorDialogInlineClickListener inlineCallback;

    private Activity activity;

    private AlertDialog alertDialog;

    public static SelectorDialog newInstance(String title, ArrayList<String> items, String negative, SelectorDialogInlineClickListener listener) {
        // or fragments without setRetainInstance(true)
        SelectorDialog dialog = new SelectorDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14169)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14170)) {
            args.putStringArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(14171)) {
            args.putString("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14172)) {
            args.putParcelable("listener", listener);
        }
        if (!ListenerUtil.mutListener.listen(14173)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static SelectorDialog newInstance(String title, ArrayList<String> items, String negative) {
        SelectorDialog dialog = new SelectorDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14174)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14175)) {
            args.putStringArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(14176)) {
            args.putString("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14177)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static SelectorDialog newInstance(String title, ArrayList<String> items, ArrayList<Integer> values, String negative) {
        SelectorDialog dialog = new SelectorDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14178)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14179)) {
            args.putIntegerArrayList("values", values);
        }
        if (!ListenerUtil.mutListener.listen(14180)) {
            args.putStringArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(14181)) {
            args.putString("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14182)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static SelectorDialog newInstance(String title, ArrayList<String> items, ArrayList<Integer> values, String negative, SelectorDialogInlineClickListener listener) {
        SelectorDialog dialog = new SelectorDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14183)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14184)) {
            args.putIntegerArrayList("values", values);
        }
        if (!ListenerUtil.mutListener.listen(14185)) {
            args.putStringArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(14186)) {
            args.putString("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14187)) {
            args.putParcelable("listener", listener);
        }
        if (!ListenerUtil.mutListener.listen(14188)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface SelectorDialogClickListener {

        void onClick(String tag, int which, Object data);

        void onCancel(String tag);

        void onNo(String tag);
    }

    public interface SelectorDialogInlineClickListener extends Parcelable {

        void onClick(String tag, int which, Object data);

        void onCancel(String tag);

        void onNo(String tag);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(14189)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14190)) {
            this.activity = activity;
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(14191)) {
            super.onCancel(dialogInterface);
        }
        if (!ListenerUtil.mutListener.listen(14194)) {
            if (inlineCallback != null) {
                if (!ListenerUtil.mutListener.listen(14193)) {
                    inlineCallback.onCancel(this.getTag());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14192)) {
                    callback.onCancel(this.getTag());
                }
            }
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        final ArrayList<String> items = getArguments().getStringArrayList("items");
        final ArrayList<Integer> values = getArguments().getIntegerArrayList("values");
        String negative = getArguments().getString("negative");
        SelectorDialogInlineClickListener listener = getArguments().getParcelable("listener");
        if (!ListenerUtil.mutListener.listen(14196)) {
            if (listener != null) {
                if (!ListenerUtil.mutListener.listen(14195)) {
                    inlineCallback = listener;
                }
            }
        }
        final String tag = this.getTag();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(14198)) {
            if (title != null) {
                if (!ListenerUtil.mutListener.listen(14197)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14213)) {
            builder.setItems(items.toArray(new String[0]), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(14199)) {
                        dialog.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(14212)) {
                        if ((ListenerUtil.mutListener.listen(14205) ? (values != null || (ListenerUtil.mutListener.listen(14204) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(14203) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(14202) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(14201) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(14200) ? (values.size() == 0) : (values.size() > 0))))))) : (values != null && (ListenerUtil.mutListener.listen(14204) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(14203) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(14202) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(14201) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(14200) ? (values.size() == 0) : (values.size() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(14211)) {
                                if (inlineCallback != null) {
                                    if (!ListenerUtil.mutListener.listen(14210)) {
                                        inlineCallback.onClick(tag, values.get(which), object);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(14209)) {
                                        callback.onClick(tag, values.get(which), object);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(14208)) {
                                if (inlineCallback != null) {
                                    if (!ListenerUtil.mutListener.listen(14207)) {
                                        inlineCallback.onClick(tag, which, object);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(14206)) {
                                        callback.onClick(tag, which, object);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14219)) {
            if (negative != null) {
                if (!ListenerUtil.mutListener.listen(14218)) {
                    builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!ListenerUtil.mutListener.listen(14214)) {
                                dialog.dismiss();
                            }
                            if (!ListenerUtil.mutListener.listen(14217)) {
                                if (inlineCallback != null) {
                                    if (!ListenerUtil.mutListener.listen(14216)) {
                                        inlineCallback.onNo(tag);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(14215)) {
                                        callback.onNo(tag);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14220)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14221)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14222)) {
                callback = (SelectorDialogClickListener) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(14225)) {
            // maybe called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(14224)) {
                    if ((activity instanceof SelectorDialogClickListener)) {
                        if (!ListenerUtil.mutListener.listen(14223)) {
                            callback = (SelectorDialogClickListener) activity;
                        }
                    }
                }
            }
        }
    }
}
