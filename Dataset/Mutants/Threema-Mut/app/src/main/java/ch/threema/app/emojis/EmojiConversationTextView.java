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
package ch.threema.app.emojis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiConversationTextView extends androidx.appcompat.widget.AppCompatTextView {

    protected final EmojiMarkupUtil emojiMarkupUtil;

    private boolean isFade = false;

    private boolean ignoreMarkup = false;

    public EmojiConversationTextView(Context context) {
        this(context, null);
    }

    public EmojiConversationTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiConversationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        emojiMarkupUtil = EmojiMarkupUtil.getInstance();
    }

    @Override
    public void setText(@Nullable CharSequence text, BufferType type) {
        if (!ListenerUtil.mutListener.listen(14725)) {
            if (emojiMarkupUtil != null) {
                if (!ListenerUtil.mutListener.listen(14724)) {
                    super.setText(emojiMarkupUtil.addTextSpans(getContext(), text, this, this.ignoreMarkup, false, true), type);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14723)) {
                    super.setText(text, type);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(14736)) {
            if (isFade) {
                if (!ListenerUtil.mutListener.listen(14726)) {
                    getPaint().clearShadowLayer();
                }
                if (!ListenerUtil.mutListener.listen(14735)) {
                    getPaint().setShader(new LinearGradient(0, getHeight(), 0, (ListenerUtil.mutListener.listen(14734) ? (getHeight() % ((ListenerUtil.mutListener.listen(14730) ? (getTextSize() % 3) : (ListenerUtil.mutListener.listen(14729) ? (getTextSize() / 3) : (ListenerUtil.mutListener.listen(14728) ? (getTextSize() - 3) : (ListenerUtil.mutListener.listen(14727) ? (getTextSize() + 3) : (getTextSize() * 3))))))) : (ListenerUtil.mutListener.listen(14733) ? (getHeight() / ((ListenerUtil.mutListener.listen(14730) ? (getTextSize() % 3) : (ListenerUtil.mutListener.listen(14729) ? (getTextSize() / 3) : (ListenerUtil.mutListener.listen(14728) ? (getTextSize() - 3) : (ListenerUtil.mutListener.listen(14727) ? (getTextSize() + 3) : (getTextSize() * 3))))))) : (ListenerUtil.mutListener.listen(14732) ? (getHeight() * ((ListenerUtil.mutListener.listen(14730) ? (getTextSize() % 3) : (ListenerUtil.mutListener.listen(14729) ? (getTextSize() / 3) : (ListenerUtil.mutListener.listen(14728) ? (getTextSize() - 3) : (ListenerUtil.mutListener.listen(14727) ? (getTextSize() + 3) : (getTextSize() * 3))))))) : (ListenerUtil.mutListener.listen(14731) ? (getHeight() + ((ListenerUtil.mutListener.listen(14730) ? (getTextSize() % 3) : (ListenerUtil.mutListener.listen(14729) ? (getTextSize() / 3) : (ListenerUtil.mutListener.listen(14728) ? (getTextSize() - 3) : (ListenerUtil.mutListener.listen(14727) ? (getTextSize() + 3) : (getTextSize() * 3))))))) : (getHeight() - ((ListenerUtil.mutListener.listen(14730) ? (getTextSize() % 3) : (ListenerUtil.mutListener.listen(14729) ? (getTextSize() / 3) : (ListenerUtil.mutListener.listen(14728) ? (getTextSize() - 3) : (ListenerUtil.mutListener.listen(14727) ? (getTextSize() + 3) : (getTextSize() * 3))))))))))), Color.TRANSPARENT, ConfigUtils.getColorFromAttribute(getContext(), android.R.attr.textColorPrimary), Shader.TileMode.CLAMP));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14737)) {
            super.onDraw(canvas);
        }
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(14740)) {
            if (drawable instanceof EmojiDrawable) {
                if (!ListenerUtil.mutListener.listen(14739)) {
                    invalidate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14738)) {
                    super.invalidateDrawable(drawable);
                }
            }
        }
    }

    public void setFade(boolean isFade) {
        if (!ListenerUtil.mutListener.listen(14741)) {
            this.isFade = isFade;
        }
    }

    public void setIgnoreMarkup(boolean ignoreMarkup) {
        if (!ListenerUtil.mutListener.listen(14742)) {
            this.ignoreMarkup = ignoreMarkup;
        }
    }
}
