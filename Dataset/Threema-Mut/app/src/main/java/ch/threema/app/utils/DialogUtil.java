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
package ch.threema.app.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.UiThread;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class DialogUtil {

    private static final Logger logger = LoggerFactory.getLogger(DialogUtil.class);

    public static void dismissDialog(FragmentManager fragmentManager, String tag, boolean allowStateLoss) {
        if (!ListenerUtil.mutListener.listen(50981)) {
            logger.debug("dismissDialog: " + tag);
        }
        if (!ListenerUtil.mutListener.listen(50982)) {
            if (fragmentManager == null) {
                return;
            }
        }
        DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(tag);
        if (!ListenerUtil.mutListener.listen(50986)) {
            if ((ListenerUtil.mutListener.listen(50983) ? (dialogFragment == null || !fragmentManager.isDestroyed()) : (dialogFragment == null && !fragmentManager.isDestroyed()))) {
                // make sure dialogfragment is really shown before removing it
                try {
                    if (!ListenerUtil.mutListener.listen(50984)) {
                        fragmentManager.executePendingTransactions();
                    }
                } catch (IllegalStateException e) {
                }
                if (!ListenerUtil.mutListener.listen(50985)) {
                    dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(tag);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50990)) {
            if (dialogFragment != null) {
                if (!ListenerUtil.mutListener.listen(50989)) {
                    if (allowStateLoss) {
                        try {
                            if (!ListenerUtil.mutListener.listen(50988)) {
                                dialogFragment.dismissAllowingStateLoss();
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            if (!ListenerUtil.mutListener.listen(50987)) {
                                dialogFragment.dismiss();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    @UiThread
    public static void updateProgress(FragmentManager fragmentManager, String tag, int progress) {
        if (!ListenerUtil.mutListener.listen(50993)) {
            if (fragmentManager != null) {
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(tag);
                if (!ListenerUtil.mutListener.listen(50992)) {
                    if (dialogFragment instanceof CancelableHorizontalProgressDialog) {
                        CancelableHorizontalProgressDialog progressDialog = (CancelableHorizontalProgressDialog) dialogFragment;
                        if (!ListenerUtil.mutListener.listen(50991)) {
                            progressDialog.setProgress(progress);
                        }
                    }
                }
            }
        }
    }

    @UiThread
    public static void updateMessage(FragmentManager fragmentManager, String tag, String message) {
        if (!ListenerUtil.mutListener.listen(50996)) {
            if (fragmentManager != null) {
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(tag);
                if (!ListenerUtil.mutListener.listen(50995)) {
                    if (dialogFragment instanceof GenericProgressDialog) {
                        GenericProgressDialog progressDialog = (GenericProgressDialog) dialogFragment;
                        if (!ListenerUtil.mutListener.listen(50994)) {
                            progressDialog.setMessage(message);
                        }
                    }
                }
            }
        }
    }

    public static ColorStateList getButtonColorStateList(Context context) {
        // Fix for appcompat bug. Set button text color from theme
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[] { R.attr.colorAccent });
        int accentColor = a.getColor(0, 0);
        if (!ListenerUtil.mutListener.listen(50997)) {
            a.recycle();
        }
        // you can't have attrs in xml colorstatelists :-(
        ColorStateList colorStateList = new ColorStateList(new int[][] { new int[] { -android.R.attr.state_enabled }, new int[] {} }, new int[] { context.getResources().getColor(R.color.material_grey_400), accentColor });
        return colorStateList;
    }
}
