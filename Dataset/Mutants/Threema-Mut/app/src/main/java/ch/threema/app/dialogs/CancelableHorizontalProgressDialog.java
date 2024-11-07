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
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.text.NumberFormat;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CancelableHorizontalProgressDialog extends ThreemaDialogFragment {

    private ProgressDialogClickListener callback;

    private Activity activity;

    private DialogInterface.OnClickListener listener;

    private NumberFormat mProgressPercentFormat;

    private TextView progressPercent;

    private ProgressBar progressBar;

    private int max;

    /**
     *  Creates a DialogFragment with a horizontal progress bar and a percentage display below. Mimics deprecated system ProgressDialog behavior
     *  @param title title of dialog
     *  @param message currently ignored
     *  @param button label of cancel button
     *  @param total maximum allowed progress value.
     *  @return nothing
     */
    public static CancelableHorizontalProgressDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int button, int total) {
        CancelableHorizontalProgressDialog dialog = new CancelableHorizontalProgressDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13276)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13277)) {
            args.putInt("button", button);
        }
        if (!ListenerUtil.mutListener.listen(13278)) {
            args.putInt("total", total);
        }
        if (!ListenerUtil.mutListener.listen(13279)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13280)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13281)) {
            this.mProgressPercentFormat = NumberFormat.getPercentInstance();
        }
        if (!ListenerUtil.mutListener.listen(13282)) {
            this.mProgressPercentFormat.setMaximumFractionDigits(0);
        }
        if (!ListenerUtil.mutListener.listen(13284)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13283)) {
                        callback = (ProgressDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13287)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(13286)) {
                    if ((activity instanceof ProgressDialogClickListener)) {
                        if (!ListenerUtil.mutListener.listen(13285)) {
                            callback = (ProgressDialogClickListener) activity;
                        }
                    }
                }
            }
        }
    }

    /**
     *  Set a listener to be attached to the cancel button. Do not use, implement {@link ProgressDialogClickListener} listener on the calling activity/fragment instead!
     *  @param onClickListener
     */
    @Deprecated
    public void setOnCancelListener(DialogInterface.OnClickListener onClickListener) {
        if (!ListenerUtil.mutListener.listen(13288)) {
            this.listener = onClickListener;
        }
    }

    public interface ProgressDialogClickListener {

        void onCancel(String tag, Object object);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(13289)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13290)) {
            this.activity = activity;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int button = getArguments().getInt("button");
        int total = getArguments().getInt("total", 0);
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_progress_horizontal, null);
        if (!ListenerUtil.mutListener.listen(13291)) {
            progressBar = dialogView.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(13292)) {
            progressPercent = dialogView.findViewById(R.id.progress_percent);
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme()).setCancelable(false);
        if (!ListenerUtil.mutListener.listen(13293)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13300)) {
            if ((ListenerUtil.mutListener.listen(13298) ? (title >= -1) : (ListenerUtil.mutListener.listen(13297) ? (title <= -1) : (ListenerUtil.mutListener.listen(13296) ? (title > -1) : (ListenerUtil.mutListener.listen(13295) ? (title < -1) : (ListenerUtil.mutListener.listen(13294) ? (title == -1) : (title != -1))))))) {
                if (!ListenerUtil.mutListener.listen(13299)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13301)) {
            max = total;
        }
        if (!ListenerUtil.mutListener.listen(13308)) {
            if ((ListenerUtil.mutListener.listen(13306) ? (max >= 0) : (ListenerUtil.mutListener.listen(13305) ? (max <= 0) : (ListenerUtil.mutListener.listen(13304) ? (max > 0) : (ListenerUtil.mutListener.listen(13303) ? (max < 0) : (ListenerUtil.mutListener.listen(13302) ? (max != 0) : (max == 0))))))) {
                if (!ListenerUtil.mutListener.listen(13307)) {
                    max = 100;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13309)) {
            progressBar.setMax(max);
        }
        if (!ListenerUtil.mutListener.listen(13310)) {
            setProgress(0);
        }
        if (!ListenerUtil.mutListener.listen(13321)) {
            if ((ListenerUtil.mutListener.listen(13315) ? (button >= 0) : (ListenerUtil.mutListener.listen(13314) ? (button <= 0) : (ListenerUtil.mutListener.listen(13313) ? (button > 0) : (ListenerUtil.mutListener.listen(13312) ? (button < 0) : (ListenerUtil.mutListener.listen(13311) ? (button == 0) : (button != 0))))))) {
                if (!ListenerUtil.mutListener.listen(13320)) {
                    builder.setPositiveButton(getString(button), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!ListenerUtil.mutListener.listen(13317)) {
                                if (listener != null) {
                                    if (!ListenerUtil.mutListener.listen(13316)) {
                                        listener.onClick(dialog, which);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(13319)) {
                                if (callback != null) {
                                    if (!ListenerUtil.mutListener.listen(13318)) {
                                        callback.onCancel(tag, object);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        AlertDialog progressDialog = builder.create();
        if (!ListenerUtil.mutListener.listen(13322)) {
            setCancelable(false);
        }
        return progressDialog;
    }

    /**
     *  Updates progress bar. Do not call this directly, use {@link ch.threema.app.utils.DialogUtil#updateProgress(FragmentManager, String, int)} instead!
     *  @param progress
     */
    @UiThread
    public void setProgress(int progress) {
        if (!ListenerUtil.mutListener.listen(13331)) {
            if ((ListenerUtil.mutListener.listen(13323) ? (progressBar != null || progressPercent != null) : (progressBar != null && progressPercent != null))) {
                double percent = (ListenerUtil.mutListener.listen(13327) ? ((double) progress % (double) max) : (ListenerUtil.mutListener.listen(13326) ? ((double) progress * (double) max) : (ListenerUtil.mutListener.listen(13325) ? ((double) progress - (double) max) : (ListenerUtil.mutListener.listen(13324) ? ((double) progress + (double) max) : ((double) progress / (double) max)))));
                SpannableString tmp = new SpannableString(mProgressPercentFormat.format(percent));
                if (!ListenerUtil.mutListener.listen(13328)) {
                    tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(13329)) {
                    progressPercent.setText(tmp);
                }
                if (!ListenerUtil.mutListener.listen(13330)) {
                    progressBar.setProgress(progress);
                }
            }
        }
    }
}
