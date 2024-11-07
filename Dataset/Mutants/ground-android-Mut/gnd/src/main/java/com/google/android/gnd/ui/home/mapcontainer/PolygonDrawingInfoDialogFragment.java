/*
 * Copyright 2021 Google LLC
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
package com.google.android.gnd.ui.home.mapcontainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import com.google.android.gnd.databinding.DialogPolygonInfoBinding;
import com.google.android.gnd.ui.common.AbstractDialogFragment;
import dagger.hilt.android.AndroidEntryPoint;
import org.jetbrains.annotations.NotNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AndroidEntryPoint
public class PolygonDrawingInfoDialogFragment extends AbstractDialogFragment {

    private final Runnable onGetStartedButtonClick;

    @SuppressWarnings("NullAway")
    DialogPolygonInfoBinding binding;

    public PolygonDrawingInfoDialogFragment(Runnable onGetStartedButtonClick) {
        this.onGetStartedButtonClick = onGetStartedButtonClick;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(731)) {
            super.onCreateDialog(savedInstanceState);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        if (!ListenerUtil.mutListener.listen(732)) {
            binding = DialogPolygonInfoBinding.inflate(inflater);
        }
        if (!ListenerUtil.mutListener.listen(733)) {
            binding.getStartedButton.setOnClickListener(v -> {
                dismiss();
                onGetStartedButtonClick.run();
            });
        }
        if (!ListenerUtil.mutListener.listen(734)) {
            builder.setView(binding.getRoot());
        }
        if (!ListenerUtil.mutListener.listen(735)) {
            binding.cancelTextView.setOnClickListener(v -> dismiss());
        }
        return builder.create();
    }
}
