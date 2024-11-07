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
package com.google.android.gnd.ui.common;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.annotation.StringRes;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO(#712): Replace with custom View.
public abstract class ProgressDialogs {

    /**
     * Do not instantiate.
     */
    private ProgressDialogs() {
    }

    public static ProgressDialog modalSpinner(Context context, @StringRes int messageId) {
        ProgressDialog dialog = new ProgressDialog(context);
        if (!ListenerUtil.mutListener.listen(15)) {
            dialog.setMessage(context.getResources().getString(messageId));
        }
        if (!ListenerUtil.mutListener.listen(16)) {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        if (!ListenerUtil.mutListener.listen(17)) {
            dialog.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(18)) {
            dialog.setCanceledOnTouchOutside(false);
        }
        return dialog;
    }
}
