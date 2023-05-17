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
package com.google.android.gnd.ui.home.featuredetails;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gnd.R;
import com.google.android.gnd.databinding.SubmissionListItemBinding;
import com.google.android.gnd.model.submission.Response;
import com.google.android.gnd.model.submission.Submission;
import com.google.android.gnd.model.task.Field;
import com.google.android.gnd.model.task.Step;
import com.google.android.gnd.model.task.Step.Type;
import com.google.android.gnd.model.task.Task;
import com.google.common.collect.ImmutableList;
import java8.util.Optional;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class SubmissionListItemViewHolder extends RecyclerView.ViewHolder {

    private static final int MAX_COLUMNS = 4;

    private final SubmissionListItemBinding binding;

    SubmissionListItemViewHolder(@NonNull SubmissionListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(SubmissionListItemViewModel viewModel, Submission submission) {
        if (!ListenerUtil.mutListener.listen(906)) {
            binding.setViewModel(viewModel);
        }
        if (!ListenerUtil.mutListener.listen(907)) {
            binding.executePendingBindings();
        }
        if (!ListenerUtil.mutListener.listen(908)) {
            // Add UI elements for each field with data.
            addFieldsFromSubmission(submission);
        }
    }

    private void addFieldsFromSubmission(Submission submission) {
        if (!ListenerUtil.mutListener.listen(909)) {
            binding.fieldLabelRow.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(910)) {
            binding.fieldValueRow.removeAllViews();
        }
        Task task = submission.getTask();
        // TODO: Clean this up.
        ImmutableList<Step> steps = task.getStepsSorted();
        if (!ListenerUtil.mutListener.listen(926)) {
            {
                long _loopCounter23 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(925) ? ((ListenerUtil.mutListener.listen(919) ? (i >= MAX_COLUMNS) : (ListenerUtil.mutListener.listen(918) ? (i <= MAX_COLUMNS) : (ListenerUtil.mutListener.listen(917) ? (i > MAX_COLUMNS) : (ListenerUtil.mutListener.listen(916) ? (i != MAX_COLUMNS) : (ListenerUtil.mutListener.listen(915) ? (i == MAX_COLUMNS) : (i < MAX_COLUMNS)))))) || (ListenerUtil.mutListener.listen(924) ? (i >= steps.size()) : (ListenerUtil.mutListener.listen(923) ? (i <= steps.size()) : (ListenerUtil.mutListener.listen(922) ? (i > steps.size()) : (ListenerUtil.mutListener.listen(921) ? (i != steps.size()) : (ListenerUtil.mutListener.listen(920) ? (i == steps.size()) : (i < steps.size()))))))) : ((ListenerUtil.mutListener.listen(919) ? (i >= MAX_COLUMNS) : (ListenerUtil.mutListener.listen(918) ? (i <= MAX_COLUMNS) : (ListenerUtil.mutListener.listen(917) ? (i > MAX_COLUMNS) : (ListenerUtil.mutListener.listen(916) ? (i != MAX_COLUMNS) : (ListenerUtil.mutListener.listen(915) ? (i == MAX_COLUMNS) : (i < MAX_COLUMNS)))))) && (ListenerUtil.mutListener.listen(924) ? (i >= steps.size()) : (ListenerUtil.mutListener.listen(923) ? (i <= steps.size()) : (ListenerUtil.mutListener.listen(922) ? (i > steps.size()) : (ListenerUtil.mutListener.listen(921) ? (i != steps.size()) : (ListenerUtil.mutListener.listen(920) ? (i == steps.size()) : (i < steps.size())))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                    Step step = steps.get(i);
                    if (!ListenerUtil.mutListener.listen(914)) {
                        if (step.getType() == Type.FIELD) {
                            Field field = step.getField();
                            Optional<Response> response = submission.getResponses().getResponse(field.getId());
                            if (!ListenerUtil.mutListener.listen(912)) {
                                binding.fieldLabelRow.addView(newFieldTextView(field.getLabel(), R.style.SubmissionListText_FieldLabel));
                            }
                            if (!ListenerUtil.mutListener.listen(913)) {
                                binding.fieldValueRow.addView(newFieldTextView(response.map(Response::getSummaryText).orElse(""), R.style.SubmissionListText_Field));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(911)) {
                                Timber.e("Unhandled step type: %s", step.getType());
                            }
                        }
                    }
                }
            }
        }
    }

    @NonNull
    private TextView newFieldTextView(String text, @StyleRes int textAppearance) {
        Context context = binding.getRoot().getContext();
        Resources resources = context.getResources();
        TextView v = new TextView(context);
        if (!ListenerUtil.mutListener.listen(927)) {
            v.setTextAppearance(context, textAppearance);
        }
        if (!ListenerUtil.mutListener.listen(928)) {
            // them here individually instead.
            v.setPadding(0, 0, resources.getDimensionPixelSize(R.dimen.submission_summary_text_padding_right), 0);
        }
        if (!ListenerUtil.mutListener.listen(929)) {
            v.setMaxWidth(resources.getDimensionPixelSize(R.dimen.submission_summary_text_max_width));
        }
        if (!ListenerUtil.mutListener.listen(930)) {
            v.setMaxLines(1);
        }
        if (!ListenerUtil.mutListener.listen(931)) {
            v.setSingleLine();
        }
        if (!ListenerUtil.mutListener.listen(932)) {
            v.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (!ListenerUtil.mutListener.listen(933)) {
            v.setText(text);
        }
        return v;
    }
}
