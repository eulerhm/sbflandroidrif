/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SilentSwitchCompat extends SwitchCompat {

    private OnCheckedChangeListener listener = null;

    private TextView label = null;

    public SilentSwitchCompat(Context context) {
        super(context);
    }

    public SilentSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SilentSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        if (!ListenerUtil.mutListener.listen(47256)) {
            super.setOnCheckedChangeListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(47257)) {
            this.listener = listener;
        }
    }

    public void setCheckedSilent(boolean checked) {
        OnCheckedChangeListener tmpListener = this.listener;
        if (!ListenerUtil.mutListener.listen(47258)) {
            setOnCheckedChangeListener(null);
        }
        if (!ListenerUtil.mutListener.listen(47259)) {
            setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(47260)) {
            setOnCheckedChangeListener(tmpListener);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(47261)) {
            super.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(47263)) {
            if (label != null) {
                if (!ListenerUtil.mutListener.listen(47262)) {
                    label.setText(checked ? R.string.on_cap : R.string.off_cap);
                }
            }
        }
    }

    public void setOnOffLabel(TextView textView) {
        if (!ListenerUtil.mutListener.listen(47264)) {
            label = textView;
        }
    }
}
