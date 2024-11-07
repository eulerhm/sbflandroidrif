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
package ch.threema.app.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.SendMediaActivity;
import ch.threema.app.emojis.EmojiEditText;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ComposeEditText extends EmojiEditText {

    private static final Logger logger = LoggerFactory.getLogger(ComposeEditText.class);

    private Context context;

    private boolean isLocked = false;

    private MentionTextWatcher mentionTextWatcher = null;

    public ComposeEditText(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(44878)) {
            init(context);
        }
    }

    public ComposeEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(44879)) {
            init(context);
        }
    }

    public ComposeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(44880)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(44881)) {
            this.context = context;
        }
        PreferenceService preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        boolean fullScreenIme = (ListenerUtil.mutListener.listen(44882) ? (context instanceof SendMediaActivity && preferenceService.isFullscreenIme()) : (context instanceof SendMediaActivity || preferenceService.isFullscreenIme()));
        if (!ListenerUtil.mutListener.listen(44883)) {
            this.setImeOptions(getImeOptions() | (fullScreenIme ? EditorInfo.IME_ACTION_SEND & ~EditorInfo.IME_FLAG_NO_FULLSCREEN : EditorInfo.IME_ACTION_SEND | EditorInfo.IME_FLAG_NO_FULLSCREEN));
        }
        if (!ListenerUtil.mutListener.listen(44884)) {
            this.setRawInputType(preferenceService.isEnterToSend() ? InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES | EditorInfo.TYPE_TEXT_FLAG_AUTO_CORRECT | (preferenceService.getEmojiStyle() == PreferenceService.EmojiStyle_ANDROID ? EditorInfo.TYPE_TEXT_VARIATION_SHORT_MESSAGE : 0) : InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES | EditorInfo.TYPE_TEXT_FLAG_AUTO_CORRECT | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
        }
        if (!ListenerUtil.mutListener.listen(44885)) {
            setFilters(appendMentionFilter(this.getFilters()));
        }
        if (!ListenerUtil.mutListener.listen(44886)) {
            this.mentionTextWatcher = new MentionTextWatcher(this);
        }
        if (!ListenerUtil.mutListener.listen(44887)) {
            new MarkupTextWatcher(context, this);
        }
    }

    /**
     *  Add our MentionFilter as the first item to the array of existing InputFilters
     *  @param originalFilters
     *  @return Array of filters
     */
    private InputFilter[] appendMentionFilter(@Nullable InputFilter[] originalFilters) {
        InputFilter[] result;
        if (originalFilters != null) {
            result = new InputFilter[originalFilters.length + 1];
            if (!ListenerUtil.mutListener.listen(44888)) {
                System.arraycopy(originalFilters, 0, result, 1, originalFilters.length);
            }
        } else {
            result = new InputFilter[1];
        }
        if (!ListenerUtil.mutListener.listen(44889)) {
            result[0] = new MentionFilter(this.context);
        }
        return result;
    }

    /**
     *  Add mention at the current cursor position
     *  @param identity
     */
    public void addMention(String identity) {
        final int start = getSelectionStart();
        final int end = getSelectionEnd();
        if (!ListenerUtil.mutListener.listen(44890)) {
            // fix reverse selections
            getText().replace(Math.min(start, end), Math.max(start, end), "@[" + identity + "]");
        }
    }

    public void setLocked(boolean isLocked) {
        if (!ListenerUtil.mutListener.listen(44891)) {
            this.isLocked = isLocked;
        }
        if (!ListenerUtil.mutListener.listen(44892)) {
            setLongClickable(!isLocked);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return (ListenerUtil.mutListener.listen(44894) ? (!isLocked || super.onTouchEvent(event)) : (!isLocked && super.onTouchEvent(event)));
        } catch (IndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(44893)) {
                logger.error("Exception", e);
            }
            return false;
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(44895)) {
            super.onConfigurationChanged(newConfig);
        }
        int maxLines = getResources().getInteger(R.integer.message_edittext_max_lines);
        if (!ListenerUtil.mutListener.listen(44902)) {
            if (this.mentionTextWatcher != null) {
                if (!ListenerUtil.mutListener.listen(44901)) {
                    if (TestUtil.empty(getText())) {
                        if (!ListenerUtil.mutListener.listen(44899)) {
                            // workaround to keep hint ellipsized on the first line
                            setMaxLines(1);
                        }
                        if (!ListenerUtil.mutListener.listen(44900)) {
                            setHint(this.hint);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(44897)) {
                            setMaxLines(maxLines);
                        }
                        if (!ListenerUtil.mutListener.listen(44898)) {
                            this.mentionTextWatcher.setMaxLines(maxLines);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(44896)) {
                    setMaxLines(maxLines);
                }
            }
        }
    }
}
