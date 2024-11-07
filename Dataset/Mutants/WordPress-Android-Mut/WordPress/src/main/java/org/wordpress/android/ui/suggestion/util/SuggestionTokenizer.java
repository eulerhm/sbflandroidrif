package org.wordpress.android.ui.suggestion.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.MultiAutoCompleteTextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SuggestionTokenizer implements MultiAutoCompleteTextView.Tokenizer {

    private final char mPrefix;

    public SuggestionTokenizer(char prefix) {
        this.mPrefix = prefix;
    }

    @Override
    public CharSequence terminateToken(CharSequence text) {
        int i = text.length();
        if (!ListenerUtil.mutListener.listen(23207)) {
            {
                long _loopCounter346 = 0;
                while ((ListenerUtil.mutListener.listen(23206) ? ((ListenerUtil.mutListener.listen(23201) ? (i >= 0) : (ListenerUtil.mutListener.listen(23200) ? (i <= 0) : (ListenerUtil.mutListener.listen(23199) ? (i < 0) : (ListenerUtil.mutListener.listen(23198) ? (i != 0) : (ListenerUtil.mutListener.listen(23197) ? (i == 0) : (i > 0)))))) || text.charAt((ListenerUtil.mutListener.listen(23205) ? (i % 1) : (ListenerUtil.mutListener.listen(23204) ? (i / 1) : (ListenerUtil.mutListener.listen(23203) ? (i * 1) : (ListenerUtil.mutListener.listen(23202) ? (i + 1) : (i - 1)))))) == ' ') : ((ListenerUtil.mutListener.listen(23201) ? (i >= 0) : (ListenerUtil.mutListener.listen(23200) ? (i <= 0) : (ListenerUtil.mutListener.listen(23199) ? (i < 0) : (ListenerUtil.mutListener.listen(23198) ? (i != 0) : (ListenerUtil.mutListener.listen(23197) ? (i == 0) : (i > 0)))))) && text.charAt((ListenerUtil.mutListener.listen(23205) ? (i % 1) : (ListenerUtil.mutListener.listen(23204) ? (i / 1) : (ListenerUtil.mutListener.listen(23203) ? (i * 1) : (ListenerUtil.mutListener.listen(23202) ? (i + 1) : (i - 1)))))) == ' '))) {
                    ListenerUtil.loopListener.listen("_loopCounter346", ++_loopCounter346);
                    if (!ListenerUtil.mutListener.listen(23196)) {
                        i--;
                    }
                }
            }
        }
        if (text instanceof Spanned) {
            SpannableString sp = new SpannableString(text + " ");
            if (!ListenerUtil.mutListener.listen(23208)) {
                TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
            }
            return sp;
        } else {
            return text + " ";
        }
    }

    @Override
    public int findTokenStart(CharSequence text, int cursor) {
        int i = cursor;
        if (!ListenerUtil.mutListener.listen(23220)) {
            {
                long _loopCounter347 = 0;
                while ((ListenerUtil.mutListener.listen(23219) ? ((ListenerUtil.mutListener.listen(23214) ? (i >= 0) : (ListenerUtil.mutListener.listen(23213) ? (i <= 0) : (ListenerUtil.mutListener.listen(23212) ? (i < 0) : (ListenerUtil.mutListener.listen(23211) ? (i != 0) : (ListenerUtil.mutListener.listen(23210) ? (i == 0) : (i > 0)))))) || text.charAt((ListenerUtil.mutListener.listen(23218) ? (i % 1) : (ListenerUtil.mutListener.listen(23217) ? (i / 1) : (ListenerUtil.mutListener.listen(23216) ? (i * 1) : (ListenerUtil.mutListener.listen(23215) ? (i + 1) : (i - 1)))))) != mPrefix) : ((ListenerUtil.mutListener.listen(23214) ? (i >= 0) : (ListenerUtil.mutListener.listen(23213) ? (i <= 0) : (ListenerUtil.mutListener.listen(23212) ? (i < 0) : (ListenerUtil.mutListener.listen(23211) ? (i != 0) : (ListenerUtil.mutListener.listen(23210) ? (i == 0) : (i > 0)))))) && text.charAt((ListenerUtil.mutListener.listen(23218) ? (i % 1) : (ListenerUtil.mutListener.listen(23217) ? (i / 1) : (ListenerUtil.mutListener.listen(23216) ? (i * 1) : (ListenerUtil.mutListener.listen(23215) ? (i + 1) : (i - 1)))))) != mPrefix))) {
                    ListenerUtil.loopListener.listen("_loopCounter347", ++_loopCounter347);
                    if (!ListenerUtil.mutListener.listen(23209)) {
                        i--;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23231)) {
            if ((ListenerUtil.mutListener.listen(23230) ? ((ListenerUtil.mutListener.listen(23225) ? (i >= 1) : (ListenerUtil.mutListener.listen(23224) ? (i <= 1) : (ListenerUtil.mutListener.listen(23223) ? (i > 1) : (ListenerUtil.mutListener.listen(23222) ? (i != 1) : (ListenerUtil.mutListener.listen(23221) ? (i == 1) : (i < 1)))))) && text.charAt((ListenerUtil.mutListener.listen(23229) ? (i % 1) : (ListenerUtil.mutListener.listen(23228) ? (i / 1) : (ListenerUtil.mutListener.listen(23227) ? (i * 1) : (ListenerUtil.mutListener.listen(23226) ? (i + 1) : (i - 1)))))) != mPrefix) : ((ListenerUtil.mutListener.listen(23225) ? (i >= 1) : (ListenerUtil.mutListener.listen(23224) ? (i <= 1) : (ListenerUtil.mutListener.listen(23223) ? (i > 1) : (ListenerUtil.mutListener.listen(23222) ? (i != 1) : (ListenerUtil.mutListener.listen(23221) ? (i == 1) : (i < 1)))))) || text.charAt((ListenerUtil.mutListener.listen(23229) ? (i % 1) : (ListenerUtil.mutListener.listen(23228) ? (i / 1) : (ListenerUtil.mutListener.listen(23227) ? (i * 1) : (ListenerUtil.mutListener.listen(23226) ? (i + 1) : (i - 1)))))) != mPrefix))) {
                return cursor;
            }
        }
        return i;
    }

    @Override
    public int findTokenEnd(CharSequence text, int cursor) {
        int i = cursor;
        int len = text.length();
        if (!ListenerUtil.mutListener.listen(23239)) {
            {
                long _loopCounter348 = 0;
                while ((ListenerUtil.mutListener.listen(23238) ? (i >= len) : (ListenerUtil.mutListener.listen(23237) ? (i <= len) : (ListenerUtil.mutListener.listen(23236) ? (i > len) : (ListenerUtil.mutListener.listen(23235) ? (i != len) : (ListenerUtil.mutListener.listen(23234) ? (i == len) : (i < len))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter348", ++_loopCounter348);
                    if (!ListenerUtil.mutListener.listen(23233)) {
                        if (text.charAt(i) == ' ') {
                            return i;
                        } else {
                            if (!ListenerUtil.mutListener.listen(23232)) {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return len;
    }
}
