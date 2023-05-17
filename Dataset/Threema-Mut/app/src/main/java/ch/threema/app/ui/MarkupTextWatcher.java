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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import java.util.regex.Pattern;
import androidx.annotation.ColorInt;
import ch.threema.app.R;
import ch.threema.app.emojis.MarkupParser;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MarkupTextWatcher implements TextWatcher {

    private final EditText editText;

    @ColorInt
    private final int markerColor;

    private boolean afterMarkup = false, beforeMarkup = false;

    private final Pattern markupCharPattern;

    MarkupTextWatcher(Context context, EditText editor) {
        editText = editor;
        if (!ListenerUtil.mutListener.listen(45765)) {
            editText.addTextChangedListener(this);
        }
        markerColor = ConfigUtils.getColorFromAttribute(context, R.attr.markup_marker_color);
        markupCharPattern = Pattern.compile(MarkupParser.MARKUP_CHAR_PATTERN);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!ListenerUtil.mutListener.listen(45766)) {
            beforeMarkup = false;
        }
        if (!ListenerUtil.mutListener.listen(45808)) {
            if ((ListenerUtil.mutListener.listen(45777) ? ((ListenerUtil.mutListener.listen(45771) ? (count <= after) : (ListenerUtil.mutListener.listen(45770) ? (count > after) : (ListenerUtil.mutListener.listen(45769) ? (count < after) : (ListenerUtil.mutListener.listen(45768) ? (count != after) : (ListenerUtil.mutListener.listen(45767) ? (count == after) : (count >= after)))))) || (ListenerUtil.mutListener.listen(45776) ? (count >= 0) : (ListenerUtil.mutListener.listen(45775) ? (count <= 0) : (ListenerUtil.mutListener.listen(45774) ? (count < 0) : (ListenerUtil.mutListener.listen(45773) ? (count != 0) : (ListenerUtil.mutListener.listen(45772) ? (count == 0) : (count > 0))))))) : ((ListenerUtil.mutListener.listen(45771) ? (count <= after) : (ListenerUtil.mutListener.listen(45770) ? (count > after) : (ListenerUtil.mutListener.listen(45769) ? (count < after) : (ListenerUtil.mutListener.listen(45768) ? (count != after) : (ListenerUtil.mutListener.listen(45767) ? (count == after) : (count >= after)))))) && (ListenerUtil.mutListener.listen(45776) ? (count >= 0) : (ListenerUtil.mutListener.listen(45775) ? (count <= 0) : (ListenerUtil.mutListener.listen(45774) ? (count < 0) : (ListenerUtil.mutListener.listen(45773) ? (count != 0) : (ListenerUtil.mutListener.listen(45772) ? (count == 0) : (count > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45807)) {
                    // text will be deleted or replaced
                    if (markupCharPattern.matcher(TextUtils.substring(s, start, (ListenerUtil.mutListener.listen(45781) ? (start % count) : (ListenerUtil.mutListener.listen(45780) ? (start / count) : (ListenerUtil.mutListener.listen(45779) ? (start * count) : (ListenerUtil.mutListener.listen(45778) ? (start - count) : (start + count))))))).matches()) {
                        if (!ListenerUtil.mutListener.listen(45806)) {
                            beforeMarkup = true;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(45805)) {
                            if ((ListenerUtil.mutListener.listen(45798) ? ((ListenerUtil.mutListener.listen(45792) ? ((ListenerUtil.mutListener.listen(45786) ? (after >= 0) : (ListenerUtil.mutListener.listen(45785) ? (after <= 0) : (ListenerUtil.mutListener.listen(45784) ? (after > 0) : (ListenerUtil.mutListener.listen(45783) ? (after < 0) : (ListenerUtil.mutListener.listen(45782) ? (after != 0) : (after == 0)))))) || (ListenerUtil.mutListener.listen(45791) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45790) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45789) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45788) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45787) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start)))))))) : ((ListenerUtil.mutListener.listen(45786) ? (after >= 0) : (ListenerUtil.mutListener.listen(45785) ? (after <= 0) : (ListenerUtil.mutListener.listen(45784) ? (after > 0) : (ListenerUtil.mutListener.listen(45783) ? (after < 0) : (ListenerUtil.mutListener.listen(45782) ? (after != 0) : (after == 0)))))) && (ListenerUtil.mutListener.listen(45791) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45790) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45789) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45788) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45787) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start))))))))) || (ListenerUtil.mutListener.listen(45797) ? (start >= 0) : (ListenerUtil.mutListener.listen(45796) ? (start <= 0) : (ListenerUtil.mutListener.listen(45795) ? (start < 0) : (ListenerUtil.mutListener.listen(45794) ? (start != 0) : (ListenerUtil.mutListener.listen(45793) ? (start == 0) : (start > 0))))))) : ((ListenerUtil.mutListener.listen(45792) ? ((ListenerUtil.mutListener.listen(45786) ? (after >= 0) : (ListenerUtil.mutListener.listen(45785) ? (after <= 0) : (ListenerUtil.mutListener.listen(45784) ? (after > 0) : (ListenerUtil.mutListener.listen(45783) ? (after < 0) : (ListenerUtil.mutListener.listen(45782) ? (after != 0) : (after == 0)))))) || (ListenerUtil.mutListener.listen(45791) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45790) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45789) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45788) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45787) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start)))))))) : ((ListenerUtil.mutListener.listen(45786) ? (after >= 0) : (ListenerUtil.mutListener.listen(45785) ? (after <= 0) : (ListenerUtil.mutListener.listen(45784) ? (after > 0) : (ListenerUtil.mutListener.listen(45783) ? (after < 0) : (ListenerUtil.mutListener.listen(45782) ? (after != 0) : (after == 0)))))) && (ListenerUtil.mutListener.listen(45791) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45790) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45789) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45788) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45787) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start))))))))) && (ListenerUtil.mutListener.listen(45797) ? (start >= 0) : (ListenerUtil.mutListener.listen(45796) ? (start <= 0) : (ListenerUtil.mutListener.listen(45795) ? (start < 0) : (ListenerUtil.mutListener.listen(45794) ? (start != 0) : (ListenerUtil.mutListener.listen(45793) ? (start == 0) : (start > 0))))))))) {
                                if (!ListenerUtil.mutListener.listen(45804)) {
                                    // simply deleting a single character (count == getCharCount()), do not replace anything (after == 0) - check if previous character is relevant for markup
                                    if (markupCharPattern.matcher(TextUtils.substring(s, (ListenerUtil.mutListener.listen(45802) ? (start % 1) : (ListenerUtil.mutListener.listen(45801) ? (start / 1) : (ListenerUtil.mutListener.listen(45800) ? (start * 1) : (ListenerUtil.mutListener.listen(45799) ? (start + 1) : (start - 1))))), start)).matches()) {
                                        if (!ListenerUtil.mutListener.listen(45803)) {
                                            beforeMarkup = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!ListenerUtil.mutListener.listen(45809)) {
            afterMarkup = false;
        }
        if (!ListenerUtil.mutListener.listen(45851)) {
            if ((ListenerUtil.mutListener.listen(45820) ? ((ListenerUtil.mutListener.listen(45814) ? (count <= before) : (ListenerUtil.mutListener.listen(45813) ? (count > before) : (ListenerUtil.mutListener.listen(45812) ? (count < before) : (ListenerUtil.mutListener.listen(45811) ? (count != before) : (ListenerUtil.mutListener.listen(45810) ? (count == before) : (count >= before)))))) || (ListenerUtil.mutListener.listen(45819) ? (count >= 0) : (ListenerUtil.mutListener.listen(45818) ? (count <= 0) : (ListenerUtil.mutListener.listen(45817) ? (count < 0) : (ListenerUtil.mutListener.listen(45816) ? (count != 0) : (ListenerUtil.mutListener.listen(45815) ? (count == 0) : (count > 0))))))) : ((ListenerUtil.mutListener.listen(45814) ? (count <= before) : (ListenerUtil.mutListener.listen(45813) ? (count > before) : (ListenerUtil.mutListener.listen(45812) ? (count < before) : (ListenerUtil.mutListener.listen(45811) ? (count != before) : (ListenerUtil.mutListener.listen(45810) ? (count == before) : (count >= before)))))) && (ListenerUtil.mutListener.listen(45819) ? (count >= 0) : (ListenerUtil.mutListener.listen(45818) ? (count <= 0) : (ListenerUtil.mutListener.listen(45817) ? (count < 0) : (ListenerUtil.mutListener.listen(45816) ? (count != 0) : (ListenerUtil.mutListener.listen(45815) ? (count == 0) : (count > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(45850)) {
                    // text has been added or replaced
                    if (markupCharPattern.matcher(TextUtils.substring(s, start, (ListenerUtil.mutListener.listen(45824) ? (start % count) : (ListenerUtil.mutListener.listen(45823) ? (start / count) : (ListenerUtil.mutListener.listen(45822) ? (start * count) : (ListenerUtil.mutListener.listen(45821) ? (start - count) : (start + count))))))).matches()) {
                        if (!ListenerUtil.mutListener.listen(45849)) {
                            afterMarkup = true;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(45848)) {
                            if ((ListenerUtil.mutListener.listen(45841) ? ((ListenerUtil.mutListener.listen(45835) ? ((ListenerUtil.mutListener.listen(45829) ? (before >= 0) : (ListenerUtil.mutListener.listen(45828) ? (before <= 0) : (ListenerUtil.mutListener.listen(45827) ? (before > 0) : (ListenerUtil.mutListener.listen(45826) ? (before < 0) : (ListenerUtil.mutListener.listen(45825) ? (before != 0) : (before == 0)))))) || (ListenerUtil.mutListener.listen(45834) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45833) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45832) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45831) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45830) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start)))))))) : ((ListenerUtil.mutListener.listen(45829) ? (before >= 0) : (ListenerUtil.mutListener.listen(45828) ? (before <= 0) : (ListenerUtil.mutListener.listen(45827) ? (before > 0) : (ListenerUtil.mutListener.listen(45826) ? (before < 0) : (ListenerUtil.mutListener.listen(45825) ? (before != 0) : (before == 0)))))) && (ListenerUtil.mutListener.listen(45834) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45833) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45832) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45831) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45830) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start))))))))) || (ListenerUtil.mutListener.listen(45840) ? (start >= 0) : (ListenerUtil.mutListener.listen(45839) ? (start <= 0) : (ListenerUtil.mutListener.listen(45838) ? (start < 0) : (ListenerUtil.mutListener.listen(45837) ? (start != 0) : (ListenerUtil.mutListener.listen(45836) ? (start == 0) : (start > 0))))))) : ((ListenerUtil.mutListener.listen(45835) ? ((ListenerUtil.mutListener.listen(45829) ? (before >= 0) : (ListenerUtil.mutListener.listen(45828) ? (before <= 0) : (ListenerUtil.mutListener.listen(45827) ? (before > 0) : (ListenerUtil.mutListener.listen(45826) ? (before < 0) : (ListenerUtil.mutListener.listen(45825) ? (before != 0) : (before == 0)))))) || (ListenerUtil.mutListener.listen(45834) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45833) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45832) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45831) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45830) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start)))))))) : ((ListenerUtil.mutListener.listen(45829) ? (before >= 0) : (ListenerUtil.mutListener.listen(45828) ? (before <= 0) : (ListenerUtil.mutListener.listen(45827) ? (before > 0) : (ListenerUtil.mutListener.listen(45826) ? (before < 0) : (ListenerUtil.mutListener.listen(45825) ? (before != 0) : (before == 0)))))) && (ListenerUtil.mutListener.listen(45834) ? (count >= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45833) ? (count <= getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45832) ? (count > getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45831) ? (count < getCharCount(s, start)) : (ListenerUtil.mutListener.listen(45830) ? (count != getCharCount(s, start)) : (count == getCharCount(s, start))))))))) && (ListenerUtil.mutListener.listen(45840) ? (start >= 0) : (ListenerUtil.mutListener.listen(45839) ? (start <= 0) : (ListenerUtil.mutListener.listen(45838) ? (start < 0) : (ListenerUtil.mutListener.listen(45837) ? (start != 0) : (ListenerUtil.mutListener.listen(45836) ? (start == 0) : (start > 0))))))))) {
                                if (!ListenerUtil.mutListener.listen(45847)) {
                                    // simply adding a single character (count == getCharCount()), do not replace anything (before == 0) - check if previous character is relevant for markup
                                    if (markupCharPattern.matcher(TextUtils.substring(s, (ListenerUtil.mutListener.listen(45845) ? (start % 1) : (ListenerUtil.mutListener.listen(45844) ? (start / 1) : (ListenerUtil.mutListener.listen(45843) ? (start * 1) : (ListenerUtil.mutListener.listen(45842) ? (start + 1) : (start - 1))))), start)).matches()) {
                                        if (!ListenerUtil.mutListener.listen(45846)) {
                                            afterMarkup = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!ListenerUtil.mutListener.listen(45859)) {
            if ((ListenerUtil.mutListener.listen(45852) ? (beforeMarkup && afterMarkup) : (beforeMarkup || afterMarkup))) {
                Editable editableText = editText.getEditableText();
                // remove old spans
                Object[] spans = editableText.getSpans(0, s.length(), Object.class);
                if (!ListenerUtil.mutListener.listen(45857)) {
                    {
                        long _loopCounter539 = 0;
                        for (Object span : spans) {
                            ListenerUtil.loopListener.listen("_loopCounter539", ++_loopCounter539);
                            if (!ListenerUtil.mutListener.listen(45856)) {
                                if ((ListenerUtil.mutListener.listen(45854) ? ((ListenerUtil.mutListener.listen(45853) ? (span instanceof StyleSpan && span instanceof StrikethroughSpan) : (span instanceof StyleSpan || span instanceof StrikethroughSpan)) && span instanceof ForegroundColorSpan) : ((ListenerUtil.mutListener.listen(45853) ? (span instanceof StyleSpan && span instanceof StrikethroughSpan) : (span instanceof StyleSpan || span instanceof StrikethroughSpan)) || span instanceof ForegroundColorSpan))) {
                                    if (!ListenerUtil.mutListener.listen(45855)) {
                                        editableText.removeSpan(span);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(45858)) {
                    MarkupParser.getInstance().markify(s, markerColor);
                }
            }
        }
    }

    /**
     *  Get number of characters at this position if it's a valid codepoint, otherwise 1 (some emojis might consist of more than one character)
     *  @param s input CharSequence
     *  @param start index of codepoint to check
     *  @return number of characters at this codepoint
     */
    private int getCharCount(CharSequence s, int start) {
        final int codePoint = Character.codePointAt(s, start);
        if (!ListenerUtil.mutListener.listen(45860)) {
            if (Character.isValidCodePoint(codePoint)) {
                return Character.charCount(codePoint);
            }
        }
        return 1;
    }
}
