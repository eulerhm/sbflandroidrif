/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
import android.util.AttributeSet;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiButton extends androidx.appcompat.widget.AppCompatImageButton implements EmojiPicker.EmojiPickerListener {

    private boolean fullscreenIme;

    private Context context;

    public EmojiButton(Context context) {
        this(context, null);
    }

    public EmojiButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(14709)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(14710)) {
            showEmojiIcon();
        }
    }

    public void showEmojiIcon() {
        if (!ListenerUtil.mutListener.listen(14711)) {
            setImageResource(R.drawable.ic_tag_faces_outline);
        }
    }

    public void showKeyboardIcon() {
        if (!ListenerUtil.mutListener.listen(14716)) {
            if ((ListenerUtil.mutListener.listen(14713) ? ((ListenerUtil.mutListener.listen(14712) ? (ConfigUtils.isLandscape(context) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(context) && !ConfigUtils.isTabletLayout())) || fullscreenIme) : ((ListenerUtil.mutListener.listen(14712) ? (ConfigUtils.isLandscape(context) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(context) && !ConfigUtils.isTabletLayout())) && fullscreenIme))) {
                if (!ListenerUtil.mutListener.listen(14715)) {
                    setImageResource(R.drawable.ic_keyboard_arrow_down_outline);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14714)) {
                    setImageResource(R.drawable.ic_keyboard_outline);
                }
            }
        }
    }

    public void attach(EmojiPicker emojiPicker, boolean fullscreenIme) {
        if (!ListenerUtil.mutListener.listen(14717)) {
            this.fullscreenIme = fullscreenIme;
        }
        if (!ListenerUtil.mutListener.listen(14718)) {
            emojiPicker.addEmojiPickerListener(this);
        }
    }

    public void detach(EmojiPicker emojiPicker) {
        if (!ListenerUtil.mutListener.listen(14720)) {
            if (emojiPicker != null) {
                if (!ListenerUtil.mutListener.listen(14719)) {
                    emojiPicker.removeEmojiPickerListener(this);
                }
            }
        }
    }

    @Override
    public void onEmojiPickerOpen() {
        if (!ListenerUtil.mutListener.listen(14721)) {
            showKeyboardIcon();
        }
    }

    @Override
    public void onEmojiPickerClose() {
        if (!ListenerUtil.mutListener.listen(14722)) {
            showEmojiIcon();
        }
    }
}
