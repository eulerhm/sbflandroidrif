/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaDialogFragment extends DialogFragment {

    protected Object object;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14504)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14505)) {
            setRetainInstance(true);
        }
    }

    /**
     *  Shows a DialogFragment. Can be used from onActivityResult() without provoking "IllegalStateException: Can not perform this action after onSaveInstanceState"
     *  @param manager FragmentManager
     *  @param tag Arbitrary tag for this DialogFragment
     */
    @Override
    public void show(@Nullable FragmentManager manager, String tag) {
        if (!ListenerUtil.mutListener.listen(14509)) {
            if (manager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(14508)) {
                        super.show(manager, tag);
                    }
                } catch (IllegalStateException e) {
                    FragmentTransaction ft = manager.beginTransaction();
                    if (!ListenerUtil.mutListener.listen(14506)) {
                        ft.add(this, tag);
                    }
                    if (!ListenerUtil.mutListener.listen(14507)) {
                        ft.commitAllowingStateLoss();
                    }
                }
            }
        }
    }

    /**
     *  Immediately shows a DialogFragment. Can be used from onActivityResult() without provoking "IllegalStateException: Can not perform this action after onSaveInstanceState"
     *  @param manager FragmentManager
     *  @param tag Arbitrary tag for this DialogFragment
     */
    @Override
    public void showNow(@Nullable FragmentManager manager, String tag) {
        if (!ListenerUtil.mutListener.listen(14513)) {
            if (manager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(14512)) {
                        super.showNow(manager, tag);
                    }
                } catch (IllegalStateException e) {
                    FragmentTransaction ft = manager.beginTransaction();
                    if (!ListenerUtil.mutListener.listen(14510)) {
                        ft.add(this, tag);
                    }
                    if (!ListenerUtil.mutListener.listen(14511)) {
                        ft.commitNowAllowingStateLoss();
                    }
                }
            }
        }
    }

    public ThreemaDialogFragment setData(Object o) {
        if (!ListenerUtil.mutListener.listen(14514)) {
            object = o;
        }
        return this;
    }
}
