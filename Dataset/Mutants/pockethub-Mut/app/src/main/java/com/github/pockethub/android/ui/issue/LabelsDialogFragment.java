/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.ui.issue;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.base.DialogFragmentHelper;
import com.github.pockethub.android.ui.item.dialog.LabelDialogItem;
import com.meisolsson.githubsdk.model.Label;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static android.app.Activity.RESULT_OK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Dialog fragment to present labels where one or more can be selected
 */
public class LabelsDialogFragment extends DialogFragmentHelper implements OnItemClickListener {

    /**
     * Arguments key for the selected items
     */
    public static final String ARG_SELECTED = "selected";

    private static final String ARG_CHOICES = "choices";

    private static final String ARG_SELECTED_CHOICES = "selectedChoices";

    private static final String TAG = "multi_choice_dialog";

    boolean[] selectedChoices;

    private GroupAdapter adapter;

    /**
     * Get selected labels from result bundle
     *
     * @param arguments
     * @return selected labels
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Label> getSelected(Bundle arguments) {
        return arguments.getParcelableArrayList(ARG_SELECTED);
    }

    /**
     * Confirm message and deliver callback to given activity
     *
     * @param activity
     * @param requestCode
     * @param title
     * @param message
     * @param choices
     * @param selectedChoices
     */
    public static void show(final BaseActivity activity, final int requestCode, final String title, final String message, final ArrayList<Label> choices, final boolean[] selectedChoices) {
        Bundle arguments = createArguments(title, message, requestCode);
        if (!ListenerUtil.mutListener.listen(1004)) {
            arguments.putParcelableArrayList(ARG_CHOICES, choices);
        }
        if (!ListenerUtil.mutListener.listen(1005)) {
            arguments.putBooleanArray(ARG_SELECTED_CHOICES, selectedChoices);
        }
        if (!ListenerUtil.mutListener.listen(1006)) {
            show(activity, new LabelsDialogFragment(), arguments, TAG);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1007)) {
            selectedChoices = getArguments().getBooleanArray(ARG_SELECTED_CHOICES);
        }
        ArrayList<Label> choices = getChoices();
        List<String> selected = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(1016)) {
            if (selectedChoices != null) {
                if (!ListenerUtil.mutListener.listen(1015)) {
                    {
                        long _loopCounter28 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1014) ? (i >= choices.size()) : (ListenerUtil.mutListener.listen(1013) ? (i <= choices.size()) : (ListenerUtil.mutListener.listen(1012) ? (i > choices.size()) : (ListenerUtil.mutListener.listen(1011) ? (i != choices.size()) : (ListenerUtil.mutListener.listen(1010) ? (i == choices.size()) : (i < choices.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                            if (!ListenerUtil.mutListener.listen(1009)) {
                                if (selectedChoices[i]) {
                                    if (!ListenerUtil.mutListener.listen(1008)) {
                                        selected.add(choices.get(i).name());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1017)) {
            adapter = new GroupAdapter();
        }
        if (!ListenerUtil.mutListener.listen(1019)) {
            {
                long _loopCounter29 = 0;
                for (Label label : getChoices()) {
                    ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                    if (!ListenerUtil.mutListener.listen(1018)) {
                        adapter.add(new LabelDialogItem(label, selected.contains(label.name())));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1020)) {
            adapter.setOnItemClickListener(this);
        }
        return createDialogBuilder().adapter(adapter, null).negativeText(R.string.cancel).neutralText(R.string.clear).positiveText(R.string.apply).onNeutral((dialog, which) -> {
            Arrays.fill(getArguments().getBooleanArray(ARG_SELECTED_CHOICES), false);
            onResult(RESULT_OK);
        }).onPositive((dialog, which) -> onResult(RESULT_OK)).build();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Label> getChoices() {
        return getArguments().getParcelableArrayList(ARG_CHOICES);
    }

    @Override
    protected void onResult(int resultCode) {
        Bundle arguments = getArguments();
        ArrayList<Label> selected = new ArrayList<>();
        ArrayList<Label> choices = getChoices();
        if (!ListenerUtil.mutListener.listen(1029)) {
            if (selectedChoices != null) {
                if (!ListenerUtil.mutListener.listen(1028)) {
                    {
                        long _loopCounter30 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1027) ? (i >= selectedChoices.length) : (ListenerUtil.mutListener.listen(1026) ? (i <= selectedChoices.length) : (ListenerUtil.mutListener.listen(1025) ? (i > selectedChoices.length) : (ListenerUtil.mutListener.listen(1024) ? (i != selectedChoices.length) : (ListenerUtil.mutListener.listen(1023) ? (i == selectedChoices.length) : (i < selectedChoices.length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                            if (!ListenerUtil.mutListener.listen(1022)) {
                                if (selectedChoices[i]) {
                                    if (!ListenerUtil.mutListener.listen(1021)) {
                                        selected.add(choices.get(i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1030)) {
            arguments.putParcelableArrayList(ARG_SELECTED, selected);
        }
        if (!ListenerUtil.mutListener.listen(1031)) {
            super.onResult(resultCode);
        }
        if (!ListenerUtil.mutListener.listen(1032)) {
            dismiss();
        }
    }

    @Override
    public void onItemClick(@NonNull Item item, @NonNull View view) {
        if (!ListenerUtil.mutListener.listen(1036)) {
            if (item instanceof LabelDialogItem) {
                LabelDialogItem labelDialogItem = (LabelDialogItem) item;
                if (!ListenerUtil.mutListener.listen(1033)) {
                    labelDialogItem.toggleSelected();
                }
                if (!ListenerUtil.mutListener.listen(1034)) {
                    selectedChoices[adapter.getAdapterPosition(item)] = labelDialogItem.isSelected();
                }
                if (!ListenerUtil.mutListener.listen(1035)) {
                    item.notifyChanged();
                }
            }
        }
    }
}
