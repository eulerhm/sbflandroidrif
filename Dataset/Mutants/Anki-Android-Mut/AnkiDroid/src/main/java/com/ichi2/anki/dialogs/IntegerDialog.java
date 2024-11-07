package com.ichi2.anki.dialogs;

import android.os.Bundle;
import android.text.InputType;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import com.ichi2.utils.FunctionalInterfaces.Consumer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IntegerDialog extends AnalyticsDialogFragment {

    private Consumer<Integer> consumer;

    public void setCallbackRunnable(Consumer<Integer> consumer) {
        if (!ListenerUtil.mutListener.listen(793)) {
            this.consumer = consumer;
        }
    }

    public void setArgs(String title, String prompt, int digits) {
        if (!ListenerUtil.mutListener.listen(794)) {
            setArgs(title, prompt, digits, null);
        }
    }

    public void setArgs(String title, String prompt, int digits, @Nullable String content) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(795)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(796)) {
            args.putString("prompt", prompt);
        }
        if (!ListenerUtil.mutListener.listen(797)) {
            args.putInt("digits", digits);
        }
        if (!ListenerUtil.mutListener.listen(798)) {
            args.putString("content", content);
        }
        if (!ListenerUtil.mutListener.listen(799)) {
            setArguments(args);
        }
    }

    @Override
    @NonNull
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(800)) {
            super.onCreate(savedInstanceState);
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity()).title(getArguments().getString("title")).positiveText(getResources().getString(R.string.dialog_ok)).negativeText(R.string.dialog_cancel).inputType(InputType.TYPE_CLASS_NUMBER).inputRange(1, getArguments().getInt("digits")).input(getArguments().getString("prompt"), "", (dialog, text) -> consumer.consume(Integer.parseInt(text.toString())));
        // We can't use "" as that creates padding, and want to respect the contract, so only set if not null
        String content = getArguments().getString("content");
        if (!ListenerUtil.mutListener.listen(802)) {
            if (content != null) {
                if (!ListenerUtil.mutListener.listen(801)) {
                    builder = builder.content(content);
                }
            }
        }
        return builder.show();
    }
}
