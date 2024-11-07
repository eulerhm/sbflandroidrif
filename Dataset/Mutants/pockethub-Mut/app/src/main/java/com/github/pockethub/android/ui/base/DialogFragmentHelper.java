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
package com.github.pockethub.android.ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pockethub.android.ui.DialogResultListener;
import dagger.android.support.DaggerAppCompatDialogFragment;
import static android.app.Activity.RESULT_CANCELED;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base dialog fragment helper
 */
public abstract class DialogFragmentHelper extends DaggerAppCompatDialogFragment {

    /**
     * Dialog message
     */
    private static final String ARG_TITLE = "title";

    /**
     * Dialog message
     */
    private static final String ARG_MESSAGE = "message";

    /**
     * Request code
     */
    private static final String ARG_REQUEST_CODE = "requestCode";

    /**
     * Show dialog
     *
     * @param activity
     * @param fragment
     * @param arguments
     * @param tag
     */
    protected static void show(FragmentActivity activity, DialogFragmentHelper fragment, Bundle arguments, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment current = manager.findFragmentByTag(tag);
        if (!ListenerUtil.mutListener.listen(691)) {
            if (current != null) {
                if (!ListenerUtil.mutListener.listen(690)) {
                    transaction.remove(current);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(692)) {
            transaction.addToBackStack(null);
        }
        if (!ListenerUtil.mutListener.listen(693)) {
            fragment.setArguments(arguments);
        }
        if (!ListenerUtil.mutListener.listen(694)) {
            fragment.show(manager, tag);
        }
    }

    /**
     * Create bundle with standard arguments
     *
     * @param title
     * @param message
     * @param requestCode
     * @return bundle
     */
    protected static Bundle createArguments(final String title, final String message, final int requestCode) {
        Bundle arguments = new Bundle();
        if (!ListenerUtil.mutListener.listen(695)) {
            arguments.putInt(ARG_REQUEST_CODE, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(696)) {
            arguments.putString(ARG_TITLE, title);
        }
        if (!ListenerUtil.mutListener.listen(697)) {
            arguments.putString(ARG_MESSAGE, message);
        }
        return arguments;
    }

    /**
     * Call back to the activity with the dialog result
     *
     * @param resultCode
     */
    protected void onResult(final int resultCode) {
        final DialogResultListener dialogResultListener = (DialogResultListener) getActivity();
        if (!ListenerUtil.mutListener.listen(700)) {
            if (dialogResultListener != null) {
                final Bundle arguments = getArguments();
                if (!ListenerUtil.mutListener.listen(699)) {
                    if (arguments != null) {
                        if (!ListenerUtil.mutListener.listen(698)) {
                            dialogResultListener.onDialogResult(arguments.getInt(ARG_REQUEST_CODE), resultCode, arguments);
                        }
                    }
                }
            }
        }
    }

    /**
     * Get title
     *
     * @return title
     */
    protected String getTitle() {
        return getArguments().getString(ARG_TITLE);
    }

    /**
     * Get message
     *
     * @return message
     */
    protected String getMessage() {
        return getArguments().getString(ARG_MESSAGE);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(701)) {
            onResult(RESULT_CANCELED);
        }
    }

    /**
     * Create default dialog
     *
     * @return dialog
     */
    protected MaterialDialog.Builder createDialogBuilder() {
        return new MaterialDialog.Builder(getActivity()).title(getTitle()).content(getMessage()).cancelable(true).cancelListener(this);
    }
}
