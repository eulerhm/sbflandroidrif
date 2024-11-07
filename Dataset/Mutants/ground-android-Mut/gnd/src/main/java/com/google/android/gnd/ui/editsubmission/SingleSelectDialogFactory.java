/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.ui.editsubmission;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.google.android.gnd.model.submission.MultipleChoiceResponse;
import com.google.android.gnd.model.task.Option;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AutoValue
abstract class SingleSelectDialogFactory extends SelectDialogFactory {

    private int checkedItem = -1;

    public static Builder builder() {
        return new AutoValue_SingleSelectDialogFactory.Builder();
    }

    @Override
    protected ImmutableList<Option> getSelectedOptions() {
        if ((ListenerUtil.mutListener.listen(474) ? (checkedItem <= 0) : (ListenerUtil.mutListener.listen(473) ? (checkedItem > 0) : (ListenerUtil.mutListener.listen(472) ? (checkedItem < 0) : (ListenerUtil.mutListener.listen(471) ? (checkedItem != 0) : (ListenerUtil.mutListener.listen(470) ? (checkedItem == 0) : (checkedItem >= 0))))))) {
            return ImmutableList.of(getOption(checkedItem));
        } else {
            return ImmutableList.of();
        }
    }

    @Override
    protected AlertDialog.Builder createDialogBuilder() {
        return super.createDialogBuilder().setSingleChoiceItems(getLabels(), checkedItem, this::onSelect);
    }

    @Override
    protected void initSelectedState() {
        if (!ListenerUtil.mutListener.listen(475)) {
            checkedItem = getCurrentResponse().flatMap(MultipleChoiceResponse::getFirstId).flatMap(getMultipleChoice()::getIndex).orElse(-1);
        }
    }

    private void onSelect(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(484)) {
            if ((ListenerUtil.mutListener.listen(480) ? (checkedItem >= which) : (ListenerUtil.mutListener.listen(479) ? (checkedItem <= which) : (ListenerUtil.mutListener.listen(478) ? (checkedItem > which) : (ListenerUtil.mutListener.listen(477) ? (checkedItem < which) : (ListenerUtil.mutListener.listen(476) ? (checkedItem != which) : (checkedItem == which))))))) {
                if (!ListenerUtil.mutListener.listen(482)) {
                    // Allow user to toggle values off by tapping selected item.
                    checkedItem = -1;
                }
                if (!ListenerUtil.mutListener.listen(483)) {
                    ((AlertDialog) dialog).getListView().setItemChecked(which, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(481)) {
                    checkedItem = which;
                }
            }
        }
    }

    @AutoValue.Builder
    public abstract static class Builder extends SelectDialogFactory.Builder<Builder> {

        public abstract SingleSelectDialogFactory build();
    }
}
