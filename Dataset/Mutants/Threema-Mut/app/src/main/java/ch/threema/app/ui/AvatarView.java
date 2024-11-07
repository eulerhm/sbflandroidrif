/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AvatarView extends FrameLayout {

    private ImageView avatar, badge;

    public AvatarView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(44700)) {
            init(context);
        }
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(44701)) {
            init(context);
        }
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(44702)) {
            init(context);
        }
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(44703)) {
            inflater.inflate(R.layout.avatar_view, this);
        }
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(44704)) {
            super.onFinishInflate();
        }
        if (!ListenerUtil.mutListener.listen(44705)) {
            avatar = this.findViewById(R.id.avatar);
        }
        if (!ListenerUtil.mutListener.listen(44706)) {
            badge = this.findViewById(R.id.avatar_badge);
        }
        if (!ListenerUtil.mutListener.listen(44707)) {
            badge.setVisibility(GONE);
        }
    }

    public void setImageResource(@DrawableRes int resource) {
        if (!ListenerUtil.mutListener.listen(44708)) {
            avatar.setImageResource(resource);
        }
        if (!ListenerUtil.mutListener.listen(44709)) {
            avatar.requestLayout();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(44710)) {
            avatar.setImageBitmap(bitmap);
        }
        if (!ListenerUtil.mutListener.listen(44711)) {
            avatar.requestLayout();
        }
    }

    public void setBadgeVisible(boolean visibile) {
        if (!ListenerUtil.mutListener.listen(44712)) {
            badge.setVisibility(visibile ? VISIBLE : GONE);
        }
    }
}
