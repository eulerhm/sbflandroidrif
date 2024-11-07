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
package ch.threema.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InitialAvatarView extends FrameLayout {

    private TextView avatarInitials;

    public InitialAvatarView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(45433)) {
            init(context);
        }
    }

    public InitialAvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(45434)) {
            init(context);
        }
    }

    public InitialAvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(45435)) {
            init(context);
        }
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(45436)) {
            inflater.inflate(R.layout.initial_avatar_view, this);
        }
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(45437)) {
            super.onFinishInflate();
        }
        if (!ListenerUtil.mutListener.listen(45438)) {
            avatarInitials = this.findViewById(R.id.avatar_initials);
        }
    }

    public void setInitials(String firstName, String lastName) {
        StringBuilder initialsBuilder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(45446)) {
            if ((ListenerUtil.mutListener.listen(45444) ? (firstName != null || (ListenerUtil.mutListener.listen(45443) ? (firstName.length() >= 0) : (ListenerUtil.mutListener.listen(45442) ? (firstName.length() <= 0) : (ListenerUtil.mutListener.listen(45441) ? (firstName.length() < 0) : (ListenerUtil.mutListener.listen(45440) ? (firstName.length() != 0) : (ListenerUtil.mutListener.listen(45439) ? (firstName.length() == 0) : (firstName.length() > 0))))))) : (firstName != null && (ListenerUtil.mutListener.listen(45443) ? (firstName.length() >= 0) : (ListenerUtil.mutListener.listen(45442) ? (firstName.length() <= 0) : (ListenerUtil.mutListener.listen(45441) ? (firstName.length() < 0) : (ListenerUtil.mutListener.listen(45440) ? (firstName.length() != 0) : (ListenerUtil.mutListener.listen(45439) ? (firstName.length() == 0) : (firstName.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45445)) {
                    initialsBuilder.append(firstName.substring(0, 1));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45454)) {
            if ((ListenerUtil.mutListener.listen(45452) ? (lastName != null || (ListenerUtil.mutListener.listen(45451) ? (lastName.length() >= 0) : (ListenerUtil.mutListener.listen(45450) ? (lastName.length() <= 0) : (ListenerUtil.mutListener.listen(45449) ? (lastName.length() < 0) : (ListenerUtil.mutListener.listen(45448) ? (lastName.length() != 0) : (ListenerUtil.mutListener.listen(45447) ? (lastName.length() == 0) : (lastName.length() > 0))))))) : (lastName != null && (ListenerUtil.mutListener.listen(45451) ? (lastName.length() >= 0) : (ListenerUtil.mutListener.listen(45450) ? (lastName.length() <= 0) : (ListenerUtil.mutListener.listen(45449) ? (lastName.length() < 0) : (ListenerUtil.mutListener.listen(45448) ? (lastName.length() != 0) : (ListenerUtil.mutListener.listen(45447) ? (lastName.length() == 0) : (lastName.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45453)) {
                    initialsBuilder.append(lastName.substring(0, 1));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45460)) {
            avatarInitials.setText((ListenerUtil.mutListener.listen(45459) ? (initialsBuilder.length() >= 0) : (ListenerUtil.mutListener.listen(45458) ? (initialsBuilder.length() <= 0) : (ListenerUtil.mutListener.listen(45457) ? (initialsBuilder.length() < 0) : (ListenerUtil.mutListener.listen(45456) ? (initialsBuilder.length() != 0) : (ListenerUtil.mutListener.listen(45455) ? (initialsBuilder.length() == 0) : (initialsBuilder.length() > 0)))))) ? initialsBuilder.toString() : "");
        }
        if (!ListenerUtil.mutListener.listen(45461)) {
            requestLayout();
        }
    }
}
