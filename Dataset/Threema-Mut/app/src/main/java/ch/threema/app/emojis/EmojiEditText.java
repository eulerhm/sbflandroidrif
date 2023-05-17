/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.ui.ThreemaEditText;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiEditText extends ThreemaEditText {

    protected Context appContext;

    protected CharSequence hint;

    private String currentText;

    private int maxByteSize;

    public EmojiEditText(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(14815)) {
            init2(context);
        }
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(14816)) {
            init2(context);
        }
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(14817)) {
            init2(context);
        }
    }

    private void init2(Context context) {
        if (!ListenerUtil.mutListener.listen(14818)) {
            this.appContext = context.getApplicationContext();
        }
        if (!ListenerUtil.mutListener.listen(14819)) {
            this.hint = getHint();
        }
        if (!ListenerUtil.mutListener.listen(14820)) {
            this.currentText = "";
        }
        if (!ListenerUtil.mutListener.listen(14821)) {
            this.maxByteSize = 0;
        }
        if (!ListenerUtil.mutListener.listen(14823)) {
            if (ConfigUtils.isDefaultEmojiStyle()) {
                if (!ListenerUtil.mutListener.listen(14822)) {
                    setFilters(appendEmojiFilter(this.getFilters()));
                }
            }
        }
    }

    /**
     *  Add our EmojiFilter as the first item to the array of existing InputFilters
     *  @param originalFilters
     *  @return Array of filters
     */
    private InputFilter[] appendEmojiFilter(@Nullable InputFilter[] originalFilters) {
        InputFilter[] result;
        if (originalFilters != null) {
            result = new InputFilter[originalFilters.length + 1];
            if (!ListenerUtil.mutListener.listen(14824)) {
                System.arraycopy(originalFilters, 0, result, 1, originalFilters.length);
            }
        } else {
            result = new InputFilter[1];
        }
        if (!ListenerUtil.mutListener.listen(14825)) {
            result[0] = new EmojiFilter(this);
        }
        return result;
    }

    /**
     *  Add single emoji at the current cursor position
     *  @param emojiCodeString
     */
    public void addEmoji(String emojiCodeString) {
        final int start = getSelectionStart();
        final int end = getSelectionEnd();
        if (!ListenerUtil.mutListener.listen(14826)) {
            // fix reverse selections
            getText().replace(Math.min(start, end), Math.max(start, end), emojiCodeString);
        }
        if (!ListenerUtil.mutListener.listen(14831)) {
            setSelection((ListenerUtil.mutListener.listen(14830) ? (start % emojiCodeString.length()) : (ListenerUtil.mutListener.listen(14829) ? (start / emojiCodeString.length()) : (ListenerUtil.mutListener.listen(14828) ? (start * emojiCodeString.length()) : (ListenerUtil.mutListener.listen(14827) ? (start - emojiCodeString.length()) : (start + emojiCodeString.length()))))));
        }
    }

    /**
     *  Callback called by invalidateSelf of EmojiDrawable
     *  @param drawable
     */
    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(14834)) {
            if (drawable instanceof EmojiDrawable) {
                if (!ListenerUtil.mutListener.listen(14833)) {
                    /* setHint() invalidates the view while invalidate() does not */
                    setHint(this.hint);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14832)) {
                    super.invalidateDrawable(drawable);
                }
            }
        }
    }

    /**
     *  Limit input size to maxByteSize by not allowing any input that exceeds the value thus keeping multi-byte characters intact
     *  @param maxByteSize Maximum input size in byte
     */
    public void setMaxByteSize(int maxByteSize) {
        if (!ListenerUtil.mutListener.listen(14835)) {
            removeTextChangedListener(textLengthWatcher);
        }
        if (!ListenerUtil.mutListener.listen(14842)) {
            if ((ListenerUtil.mutListener.listen(14840) ? (maxByteSize >= 0) : (ListenerUtil.mutListener.listen(14839) ? (maxByteSize <= 0) : (ListenerUtil.mutListener.listen(14838) ? (maxByteSize < 0) : (ListenerUtil.mutListener.listen(14837) ? (maxByteSize != 0) : (ListenerUtil.mutListener.listen(14836) ? (maxByteSize == 0) : (maxByteSize > 0))))))) {
                if (!ListenerUtil.mutListener.listen(14841)) {
                    addTextChangedListener(textLengthWatcher);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14843)) {
            this.maxByteSize = maxByteSize;
        }
    }

    private final TextWatcher textLengthWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!ListenerUtil.mutListener.listen(14848)) {
                if (s != null) {
                    String text = s.toString();
                    String cropped = Utils.truncateUTF8String(text, maxByteSize);
                    if (!ListenerUtil.mutListener.listen(14847)) {
                        if (!TestUtil.compare(text, cropped == null ? "" : cropped)) {
                            if (!ListenerUtil.mutListener.listen(14845)) {
                                setText(currentText);
                            }
                            if (!ListenerUtil.mutListener.listen(14846)) {
                                setSelection(currentText.length());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(14844)) {
                                currentText = text;
                            }
                        }
                    }
                }
            }
        }
    };
}
