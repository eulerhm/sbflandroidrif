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

import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ReplacementSpan;
import android.widget.EditText;
import java.util.concurrent.CopyOnWriteArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MentionTextWatcher implements TextWatcher {

    private final EditText editText;

    private final CharSequence hint;

    private int maxLines;

    private final CopyOnWriteArrayList<ReplacementSpan> spansToRemove = new CopyOnWriteArrayList<>();

    public MentionTextWatcher(EditText editor) {
        editText = editor;
        hint = editText.getHint();
        if (!ListenerUtil.mutListener.listen(46215)) {
            maxLines = editText.getMaxLines();
        }
        if (!ListenerUtil.mutListener.listen(46216)) {
            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!ListenerUtil.mutListener.listen(46240)) {
            if ((ListenerUtil.mutListener.listen(46221) ? (count >= 1) : (ListenerUtil.mutListener.listen(46220) ? (count <= 1) : (ListenerUtil.mutListener.listen(46219) ? (count > 1) : (ListenerUtil.mutListener.listen(46218) ? (count < 1) : (ListenerUtil.mutListener.listen(46217) ? (count != 1) : (count == 1))))))) {
                int end = (ListenerUtil.mutListener.listen(46225) ? (start % count) : (ListenerUtil.mutListener.listen(46224) ? (start / count) : (ListenerUtil.mutListener.listen(46223) ? (start * count) : (ListenerUtil.mutListener.listen(46222) ? (start - count) : (start + count)))));
                Editable editableText = editText.getEditableText();
                ReplacementSpan[] list = editableText.getSpans(start, end, ReplacementSpan.class);
                if (!ListenerUtil.mutListener.listen(46239)) {
                    {
                        long _loopCounter540 = 0;
                        for (ReplacementSpan span : list) {
                            ListenerUtil.loopListener.listen("_loopCounter540", ++_loopCounter540);
                            int spanStart = editableText.getSpanStart(span);
                            int spanEnd = editableText.getSpanEnd(span);
                            if (!ListenerUtil.mutListener.listen(46238)) {
                                if ((ListenerUtil.mutListener.listen(46236) ? (((ListenerUtil.mutListener.listen(46230) ? (spanStart >= end) : (ListenerUtil.mutListener.listen(46229) ? (spanStart <= end) : (ListenerUtil.mutListener.listen(46228) ? (spanStart > end) : (ListenerUtil.mutListener.listen(46227) ? (spanStart != end) : (ListenerUtil.mutListener.listen(46226) ? (spanStart == end) : (spanStart < end))))))) || ((ListenerUtil.mutListener.listen(46235) ? (spanEnd >= start) : (ListenerUtil.mutListener.listen(46234) ? (spanEnd <= start) : (ListenerUtil.mutListener.listen(46233) ? (spanEnd < start) : (ListenerUtil.mutListener.listen(46232) ? (spanEnd != start) : (ListenerUtil.mutListener.listen(46231) ? (spanEnd == start) : (spanEnd > start)))))))) : (((ListenerUtil.mutListener.listen(46230) ? (spanStart >= end) : (ListenerUtil.mutListener.listen(46229) ? (spanStart <= end) : (ListenerUtil.mutListener.listen(46228) ? (spanStart > end) : (ListenerUtil.mutListener.listen(46227) ? (spanStart != end) : (ListenerUtil.mutListener.listen(46226) ? (spanStart == end) : (spanStart < end))))))) && ((ListenerUtil.mutListener.listen(46235) ? (spanEnd >= start) : (ListenerUtil.mutListener.listen(46234) ? (spanEnd <= start) : (ListenerUtil.mutListener.listen(46233) ? (spanEnd < start) : (ListenerUtil.mutListener.listen(46232) ? (spanEnd != start) : (ListenerUtil.mutListener.listen(46231) ? (spanEnd == start) : (spanEnd > start)))))))))) {
                                    if (!ListenerUtil.mutListener.listen(46237)) {
                                        spansToRemove.add(span);
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
    }

    @Override
    public void afterTextChanged(Editable s) {
        Editable editableText = editText.getEditableText();
        if (!ListenerUtil.mutListener.listen(46249)) {
            {
                long _loopCounter541 = 0;
                for (ReplacementSpan span : spansToRemove) {
                    ListenerUtil.loopListener.listen("_loopCounter541", ++_loopCounter541);
                    int start = editableText.getSpanStart(span);
                    int end = editableText.getSpanEnd(span);
                    if (!ListenerUtil.mutListener.listen(46241)) {
                        editableText.removeSpan(span);
                    }
                    if (!ListenerUtil.mutListener.listen(46248)) {
                        if ((ListenerUtil.mutListener.listen(46246) ? (start >= end) : (ListenerUtil.mutListener.listen(46245) ? (start <= end) : (ListenerUtil.mutListener.listen(46244) ? (start > end) : (ListenerUtil.mutListener.listen(46243) ? (start < end) : (ListenerUtil.mutListener.listen(46242) ? (start == end) : (start != end))))))) {
                            if (!ListenerUtil.mutListener.listen(46247)) {
                                editableText.delete(start, end);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46250)) {
            spansToRemove.clear();
        }
        if (!ListenerUtil.mutListener.listen(46260)) {
            // workaround to keep hint ellipsized on the first line
            if ((ListenerUtil.mutListener.listen(46255) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(46254) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(46253) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(46252) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(46251) ? (s.length() == 0) : (s.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(46258)) {
                    editText.setHint(null);
                }
                if (!ListenerUtil.mutListener.listen(46259)) {
                    editText.setMaxLines(maxLines);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(46256)) {
                    editText.setMaxLines(1);
                }
                if (!ListenerUtil.mutListener.listen(46257)) {
                    editText.setHint(this.hint);
                }
            }
        }
    }

    public void setMaxLines(int maxLines) {
        if (!ListenerUtil.mutListener.listen(46261)) {
            this.maxLines = maxLines;
        }
    }
}
