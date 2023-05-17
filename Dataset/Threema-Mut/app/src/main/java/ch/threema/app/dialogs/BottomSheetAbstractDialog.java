/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.adapters.BottomSheetGridAdapter;
import ch.threema.app.adapters.BottomSheetListAdapter;
import ch.threema.app.ui.BottomSheetItem;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BottomSheetAbstractDialog extends BottomSheetDialogFragment {

    private BottomSheetDialogCallback callback;

    private BottomSheetDialogInlineClickListener inlineCallback;

    private Activity activity;

    public interface BottomSheetDialogCallback {

        void onSelected(String tag);
    }

    public interface BottomSheetDialogInlineClickListener extends Parcelable {

        void onSelected(String tag);

        void onCancel(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13184)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(13185)) {
                callback = (BottomSheetDialogCallback) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(13188)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(13187)) {
                    if ((activity instanceof BottomSheetDialogCallback)) {
                        if (!ListenerUtil.mutListener.listen(13186)) {
                            callback = (BottomSheetDialogCallback) activity;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(13189)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13190)) {
            this.activity = activity;
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(13191)) {
            super.onResume();
        }
        // Hack to set width of bottom sheet
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(13192)) {
            display.getMetrics(metrics);
        }
        int width = (ListenerUtil.mutListener.listen(13197) ? (metrics.widthPixels >= 1440) : (ListenerUtil.mutListener.listen(13196) ? (metrics.widthPixels <= 1440) : (ListenerUtil.mutListener.listen(13195) ? (metrics.widthPixels > 1440) : (ListenerUtil.mutListener.listen(13194) ? (metrics.widthPixels != 1440) : (ListenerUtil.mutListener.listen(13193) ? (metrics.widthPixels == 1440) : (metrics.widthPixels < 1440)))))) ? metrics.widthPixels : 1440;
        int height = -1;
        Window window = getDialog().getWindow();
        if (!ListenerUtil.mutListener.listen(13199)) {
            if (window != null) {
                if (!ListenerUtil.mutListener.listen(13198)) {
                    window.setLayout(width, height);
                }
            }
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int selectedItem = getArguments().getInt("selected");
        final ArrayList<BottomSheetItem> items = getArguments().getParcelableArrayList("items");
        BottomSheetDialogInlineClickListener listener = getArguments().getParcelable("listener");
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View dialogView = activity.getLayoutInflater().inflate(this instanceof BottomSheetGridDialog ? R.layout.dialog_bottomsheet_grid : R.layout.dialog_bottomsheet_list, null);
        final AbsListView listView = dialogView.findViewById(R.id.list_view);
        final TextView titleView = dialogView.findViewById(R.id.title_text);
        if (!ListenerUtil.mutListener.listen(13201)) {
            if (listener != null) {
                if (!ListenerUtil.mutListener.listen(13200)) {
                    inlineCallback = listener;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13209)) {
            if ((ListenerUtil.mutListener.listen(13206) ? (title >= 0) : (ListenerUtil.mutListener.listen(13205) ? (title <= 0) : (ListenerUtil.mutListener.listen(13204) ? (title > 0) : (ListenerUtil.mutListener.listen(13203) ? (title < 0) : (ListenerUtil.mutListener.listen(13202) ? (title == 0) : (title != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13208)) {
                    titleView.setText(title);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13207)) {
                    titleView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13210)) {
            listView.setAdapter(this instanceof BottomSheetGridDialog ? new BottomSheetGridAdapter(getContext(), items) : new BottomSheetListAdapter(getContext(), items, selectedItem));
        }
        if (!ListenerUtil.mutListener.listen(13222)) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!ListenerUtil.mutListener.listen(13221)) {
                        if ((ListenerUtil.mutListener.listen(13216) ? (items != null || (ListenerUtil.mutListener.listen(13215) ? (i >= items.size()) : (ListenerUtil.mutListener.listen(13214) ? (i <= items.size()) : (ListenerUtil.mutListener.listen(13213) ? (i > items.size()) : (ListenerUtil.mutListener.listen(13212) ? (i != items.size()) : (ListenerUtil.mutListener.listen(13211) ? (i == items.size()) : (i < items.size()))))))) : (items != null && (ListenerUtil.mutListener.listen(13215) ? (i >= items.size()) : (ListenerUtil.mutListener.listen(13214) ? (i <= items.size()) : (ListenerUtil.mutListener.listen(13213) ? (i > items.size()) : (ListenerUtil.mutListener.listen(13212) ? (i != items.size()) : (ListenerUtil.mutListener.listen(13211) ? (i == items.size()) : (i < items.size()))))))))) {
                            if (!ListenerUtil.mutListener.listen(13217)) {
                                dismiss();
                            }
                            if (!ListenerUtil.mutListener.listen(13220)) {
                                if (inlineCallback != null) {
                                    if (!ListenerUtil.mutListener.listen(13219)) {
                                        inlineCallback.onSelected(items.get(i).getTag());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(13218)) {
                                        callback.onSelected(items.get(i).getTag());
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13223)) {
            dialog.setContentView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13227)) {
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                @Override
                public void onShow(DialogInterface dialog) {
                    BottomSheetDialog d = (BottomSheetDialog) dialog;
                    final FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                    if (!ListenerUtil.mutListener.listen(13226)) {
                        if (bottomSheet != null) {
                            if (!ListenerUtil.mutListener.listen(13225)) {
                                bottomSheet.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(13224)) {
                                            // https://github.com/material-components/material-components-android/pull/437#issuecomment-536668983
                                            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }
        return dialog;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(13228)) {
            super.onCancel(dialog);
        }
        if (!ListenerUtil.mutListener.listen(13230)) {
            if (inlineCallback != null) {
                if (!ListenerUtil.mutListener.listen(13229)) {
                    inlineCallback.onCancel(this.getTag());
                }
            }
        }
    }
}
