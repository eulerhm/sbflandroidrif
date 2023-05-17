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
package ch.threema.app.emojis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiTextView extends AppCompatTextView {

    protected final EmojiMarkupUtil emojiMarkupUtil;

    public EmojiTextView(Context context) {
        this(context, null);
    }

    public EmojiTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        emojiMarkupUtil = EmojiMarkupUtil.getInstance();
    }

    @Override
    public void setText(@Nullable CharSequence text, BufferType type) {
        if (!ListenerUtil.mutListener.listen(22954)) {
            if (emojiMarkupUtil != null) {
                if (!ListenerUtil.mutListener.listen(22953)) {
                    super.setText(emojiMarkupUtil.addTextSpans(getContext(), text, this, true), type);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22952)) {
                    super.setText(text, type);
                }
            }
        }
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(22957)) {
            if (drawable instanceof EmojiDrawable) {
                if (!ListenerUtil.mutListener.listen(22956)) {
                    invalidate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22955)) {
                    super.invalidateDrawable(drawable);
                }
            }
        }
    }
}
