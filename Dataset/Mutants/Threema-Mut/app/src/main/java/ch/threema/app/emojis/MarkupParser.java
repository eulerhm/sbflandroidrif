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

import android.graphics.Typeface;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;
import androidx.annotation.ColorInt;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MarkupParser {

    private static final Logger logger = LoggerFactory.getLogger(MarkupParser.class);

    private static final String BOUNDARY_PATTERN = "[\\s.,!?¡¿‽⸮;:&(){}\\[\\]⟨⟩‹›«»'\"‘’“”*~\\-_…⋯᠁]";

    private static final String URL_BOUNDARY_PATTERN = "[a-zA-Z0-9\\-._~:/?#\\[\\]@!$&'()*+,;=%]";

    private static final String URL_START_PATTERN = "^[a-zA-Z]+://.*";

    private static final char MARKUP_CHAR_BOLD = '*';

    private static final char MARKUP_CHAR_ITALIC = '_';

    private static final char MARKUP_CHAR_STRIKETHRU = '~';

    public static final String MARKUP_CHAR_PATTERN = ".*[\\*_~].*";

    private final Pattern boundaryPattern, urlBoundaryPattern, urlStartPattern;

    // Singleton stuff
    private static MarkupParser sInstance = null;

    public static synchronized MarkupParser getInstance() {
        if (!ListenerUtil.mutListener.listen(22962)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(22961)) {
                    sInstance = new MarkupParser();
                }
            }
        }
        return sInstance;
    }

    private MarkupParser() {
        this.boundaryPattern = Pattern.compile(BOUNDARY_PATTERN);
        this.urlBoundaryPattern = Pattern.compile(URL_BOUNDARY_PATTERN);
        this.urlStartPattern = Pattern.compile(URL_START_PATTERN);
    }

    private enum TokenType {

        TEXT, NEWLINE, ASTERISK, UNDERSCORE, TILDE
    }

    private class Token {

        TokenType kind;

        int start;

        int end;

        private Token(TokenType kind, int start, int end) {
            if (!ListenerUtil.mutListener.listen(22963)) {
                this.kind = kind;
            }
            if (!ListenerUtil.mutListener.listen(22964)) {
                this.start = start;
            }
            if (!ListenerUtil.mutListener.listen(22965)) {
                this.end = end;
            }
        }
    }

    private static class SpanItem {

        TokenType kind;

        int textStart;

        int textEnd;

        int markerStart;

        int markerEnd;

        SpanItem(TokenType kind, int textStart, int textEnd, int markerStart, int markerEnd) {
            if (!ListenerUtil.mutListener.listen(22966)) {
                this.kind = kind;
            }
            if (!ListenerUtil.mutListener.listen(22967)) {
                this.textStart = textStart;
            }
            if (!ListenerUtil.mutListener.listen(22968)) {
                this.textEnd = textEnd;
            }
            if (!ListenerUtil.mutListener.listen(22969)) {
                this.markerStart = markerStart;
            }
            if (!ListenerUtil.mutListener.listen(22970)) {
                this.markerEnd = markerEnd;
            }
        }
    }

    // This is used for optimization.
    public static class TokenPresenceMap extends HashMap<TokenType, Boolean> {

        TokenPresenceMap() {
            if (!ListenerUtil.mutListener.listen(22971)) {
                init();
            }
        }

        public void init() {
            if (!ListenerUtil.mutListener.listen(22972)) {
                this.put(TokenType.ASTERISK, false);
            }
            if (!ListenerUtil.mutListener.listen(22973)) {
                this.put(TokenType.UNDERSCORE, false);
            }
            if (!ListenerUtil.mutListener.listen(22974)) {
                this.put(TokenType.TILDE, false);
            }
        }
    }

    private HashMap<TokenType, Character> markupChars = new HashMap<>();

    {
        if (!ListenerUtil.mutListener.listen(22975)) {
            markupChars.put(TokenType.ASTERISK, MARKUP_CHAR_BOLD);
        }
        if (!ListenerUtil.mutListener.listen(22976)) {
            markupChars.put(TokenType.UNDERSCORE, MARKUP_CHAR_ITALIC);
        }
        if (!ListenerUtil.mutListener.listen(22977)) {
            markupChars.put(TokenType.TILDE, MARKUP_CHAR_STRIKETHRU);
        }
    }

    /**
     *  Return whether the specified token type is a markup token.
     */
    private boolean isMarkupToken(TokenType tokenType) {
        return markupChars.containsKey(tokenType);
    }

    /**
     *  Return whether the character at the specified position in the string is a boundary character.
     *  When `character` is out of range, the function will return true.
     */
    private boolean isBoundary(CharSequence text, int position) {
        if (!ListenerUtil.mutListener.listen(22989)) {
            if ((ListenerUtil.mutListener.listen(22988) ? ((ListenerUtil.mutListener.listen(22982) ? (position >= 0) : (ListenerUtil.mutListener.listen(22981) ? (position <= 0) : (ListenerUtil.mutListener.listen(22980) ? (position > 0) : (ListenerUtil.mutListener.listen(22979) ? (position != 0) : (ListenerUtil.mutListener.listen(22978) ? (position == 0) : (position < 0)))))) && (ListenerUtil.mutListener.listen(22987) ? (position <= text.length()) : (ListenerUtil.mutListener.listen(22986) ? (position > text.length()) : (ListenerUtil.mutListener.listen(22985) ? (position < text.length()) : (ListenerUtil.mutListener.listen(22984) ? (position != text.length()) : (ListenerUtil.mutListener.listen(22983) ? (position == text.length()) : (position >= text.length()))))))) : ((ListenerUtil.mutListener.listen(22982) ? (position >= 0) : (ListenerUtil.mutListener.listen(22981) ? (position <= 0) : (ListenerUtil.mutListener.listen(22980) ? (position > 0) : (ListenerUtil.mutListener.listen(22979) ? (position != 0) : (ListenerUtil.mutListener.listen(22978) ? (position == 0) : (position < 0)))))) || (ListenerUtil.mutListener.listen(22987) ? (position <= text.length()) : (ListenerUtil.mutListener.listen(22986) ? (position > text.length()) : (ListenerUtil.mutListener.listen(22985) ? (position < text.length()) : (ListenerUtil.mutListener.listen(22984) ? (position != text.length()) : (ListenerUtil.mutListener.listen(22983) ? (position == text.length()) : (position >= text.length()))))))))) {
                return true;
            }
        }
        return boundaryPattern.matcher(TextUtils.substring(text, position, (ListenerUtil.mutListener.listen(22993) ? (position % 1) : (ListenerUtil.mutListener.listen(22992) ? (position / 1) : (ListenerUtil.mutListener.listen(22991) ? (position * 1) : (ListenerUtil.mutListener.listen(22990) ? (position - 1) : (position + 1))))))).matches();
    }

    /**
     *  Return whether the specified character is a URL boundary character.
     *  When `character` is undefined, the function will return true.
     *
     *  Characters that may be in an URL according to RFC 3986:
     *  ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~:/?#[]@!$&'()*+,;=%
     */
    private boolean isUrlBoundary(CharSequence text, int position) {
        if (!ListenerUtil.mutListener.listen(23005)) {
            if ((ListenerUtil.mutListener.listen(23004) ? ((ListenerUtil.mutListener.listen(22998) ? (position >= 0) : (ListenerUtil.mutListener.listen(22997) ? (position <= 0) : (ListenerUtil.mutListener.listen(22996) ? (position > 0) : (ListenerUtil.mutListener.listen(22995) ? (position != 0) : (ListenerUtil.mutListener.listen(22994) ? (position == 0) : (position < 0)))))) && (ListenerUtil.mutListener.listen(23003) ? (position <= text.length()) : (ListenerUtil.mutListener.listen(23002) ? (position > text.length()) : (ListenerUtil.mutListener.listen(23001) ? (position < text.length()) : (ListenerUtil.mutListener.listen(23000) ? (position != text.length()) : (ListenerUtil.mutListener.listen(22999) ? (position == text.length()) : (position >= text.length()))))))) : ((ListenerUtil.mutListener.listen(22998) ? (position >= 0) : (ListenerUtil.mutListener.listen(22997) ? (position <= 0) : (ListenerUtil.mutListener.listen(22996) ? (position > 0) : (ListenerUtil.mutListener.listen(22995) ? (position != 0) : (ListenerUtil.mutListener.listen(22994) ? (position == 0) : (position < 0)))))) || (ListenerUtil.mutListener.listen(23003) ? (position <= text.length()) : (ListenerUtil.mutListener.listen(23002) ? (position > text.length()) : (ListenerUtil.mutListener.listen(23001) ? (position < text.length()) : (ListenerUtil.mutListener.listen(23000) ? (position != text.length()) : (ListenerUtil.mutListener.listen(22999) ? (position == text.length()) : (position >= text.length()))))))))) {
                return true;
            }
        }
        return !urlBoundaryPattern.matcher(TextUtils.substring(text, position, (ListenerUtil.mutListener.listen(23009) ? (position % 1) : (ListenerUtil.mutListener.listen(23008) ? (position / 1) : (ListenerUtil.mutListener.listen(23007) ? (position * 1) : (ListenerUtil.mutListener.listen(23006) ? (position - 1) : (position + 1))))))).matches();
    }

    /**
     *  Return whether the specified string starts an URL.
     */
    private boolean isUrlStart(CharSequence text, int position) {
        if (!ListenerUtil.mutListener.listen(23021)) {
            if ((ListenerUtil.mutListener.listen(23020) ? ((ListenerUtil.mutListener.listen(23014) ? (position >= 0) : (ListenerUtil.mutListener.listen(23013) ? (position <= 0) : (ListenerUtil.mutListener.listen(23012) ? (position > 0) : (ListenerUtil.mutListener.listen(23011) ? (position != 0) : (ListenerUtil.mutListener.listen(23010) ? (position == 0) : (position < 0)))))) && (ListenerUtil.mutListener.listen(23019) ? (position <= text.length()) : (ListenerUtil.mutListener.listen(23018) ? (position > text.length()) : (ListenerUtil.mutListener.listen(23017) ? (position < text.length()) : (ListenerUtil.mutListener.listen(23016) ? (position != text.length()) : (ListenerUtil.mutListener.listen(23015) ? (position == text.length()) : (position >= text.length()))))))) : ((ListenerUtil.mutListener.listen(23014) ? (position >= 0) : (ListenerUtil.mutListener.listen(23013) ? (position <= 0) : (ListenerUtil.mutListener.listen(23012) ? (position > 0) : (ListenerUtil.mutListener.listen(23011) ? (position != 0) : (ListenerUtil.mutListener.listen(23010) ? (position == 0) : (position < 0)))))) || (ListenerUtil.mutListener.listen(23019) ? (position <= text.length()) : (ListenerUtil.mutListener.listen(23018) ? (position > text.length()) : (ListenerUtil.mutListener.listen(23017) ? (position < text.length()) : (ListenerUtil.mutListener.listen(23016) ? (position != text.length()) : (ListenerUtil.mutListener.listen(23015) ? (position == text.length()) : (position >= text.length()))))))))) {
                return false;
            }
        }
        return urlStartPattern.matcher(TextUtils.substring(text, position, text.length())).matches();
    }

    private int pushTextBufToken(int tokenLength, int i, ArrayList<Token> tokens) {
        if (!ListenerUtil.mutListener.listen(23033)) {
            if ((ListenerUtil.mutListener.listen(23026) ? (tokenLength >= 0) : (ListenerUtil.mutListener.listen(23025) ? (tokenLength <= 0) : (ListenerUtil.mutListener.listen(23024) ? (tokenLength < 0) : (ListenerUtil.mutListener.listen(23023) ? (tokenLength != 0) : (ListenerUtil.mutListener.listen(23022) ? (tokenLength == 0) : (tokenLength > 0))))))) {
                if (!ListenerUtil.mutListener.listen(23031)) {
                    tokens.add(new Token(TokenType.TEXT, (ListenerUtil.mutListener.listen(23030) ? (i % tokenLength) : (ListenerUtil.mutListener.listen(23029) ? (i / tokenLength) : (ListenerUtil.mutListener.listen(23028) ? (i * tokenLength) : (ListenerUtil.mutListener.listen(23027) ? (i + tokenLength) : (i - tokenLength))))), i));
                }
                if (!ListenerUtil.mutListener.listen(23032)) {
                    tokenLength = 0;
                }
            }
        }
        return tokenLength;
    }

    /**
     *  This function accepts a string and returns a list of tokens.
     */
    private ArrayList<Token> tokenize(CharSequence text) {
        int tokenLength = 0;
        boolean matchingUrl = false;
        ArrayList<Token> tokens = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(23090)) {
            {
                long _loopCounter149 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23089) ? (i >= text.length()) : (ListenerUtil.mutListener.listen(23088) ? (i <= text.length()) : (ListenerUtil.mutListener.listen(23087) ? (i > text.length()) : (ListenerUtil.mutListener.listen(23086) ? (i != text.length()) : (ListenerUtil.mutListener.listen(23085) ? (i == text.length()) : (i < text.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter149", ++_loopCounter149);
                    char currentChar = text.charAt(i);
                    if (!ListenerUtil.mutListener.listen(23035)) {
                        // Detect URLs
                        if (!matchingUrl) {
                            if (!ListenerUtil.mutListener.listen(23034)) {
                                matchingUrl = isUrlStart(text, i);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23084)) {
                        // treat them separately.
                        if (matchingUrl) {
                            final boolean nextIsUrlBoundary = isUrlBoundary(text, (ListenerUtil.mutListener.listen(23079) ? (i % 1) : (ListenerUtil.mutListener.listen(23078) ? (i / 1) : (ListenerUtil.mutListener.listen(23077) ? (i * 1) : (ListenerUtil.mutListener.listen(23076) ? (i - 1) : (i + 1))))));
                            if (!ListenerUtil.mutListener.listen(23082)) {
                                if (nextIsUrlBoundary) {
                                    if (!ListenerUtil.mutListener.listen(23080)) {
                                        tokenLength = pushTextBufToken(tokenLength, i, tokens);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23081)) {
                                        matchingUrl = false;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(23083)) {
                                tokenLength++;
                            }
                        } else {
                            final boolean prevIsBoundary = isBoundary(text, (ListenerUtil.mutListener.listen(23039) ? (i % 1) : (ListenerUtil.mutListener.listen(23038) ? (i / 1) : (ListenerUtil.mutListener.listen(23037) ? (i * 1) : (ListenerUtil.mutListener.listen(23036) ? (i + 1) : (i - 1))))));
                            final boolean nextIsBoundary = isBoundary(text, (ListenerUtil.mutListener.listen(23043) ? (i % 1) : (ListenerUtil.mutListener.listen(23042) ? (i / 1) : (ListenerUtil.mutListener.listen(23041) ? (i * 1) : (ListenerUtil.mutListener.listen(23040) ? (i - 1) : (i + 1))))));
                            if (!ListenerUtil.mutListener.listen(23075)) {
                                if ((ListenerUtil.mutListener.listen(23045) ? (currentChar == MARKUP_CHAR_BOLD || ((ListenerUtil.mutListener.listen(23044) ? (prevIsBoundary && nextIsBoundary) : (prevIsBoundary || nextIsBoundary)))) : (currentChar == MARKUP_CHAR_BOLD && ((ListenerUtil.mutListener.listen(23044) ? (prevIsBoundary && nextIsBoundary) : (prevIsBoundary || nextIsBoundary)))))) {
                                    if (!ListenerUtil.mutListener.listen(23069)) {
                                        tokenLength = pushTextBufToken(tokenLength, i, tokens);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23074)) {
                                        tokens.add(new Token(TokenType.ASTERISK, i, (ListenerUtil.mutListener.listen(23073) ? (i % 1) : (ListenerUtil.mutListener.listen(23072) ? (i / 1) : (ListenerUtil.mutListener.listen(23071) ? (i * 1) : (ListenerUtil.mutListener.listen(23070) ? (i - 1) : (i + 1)))))));
                                    }
                                } else if ((ListenerUtil.mutListener.listen(23047) ? (currentChar == MARKUP_CHAR_ITALIC || ((ListenerUtil.mutListener.listen(23046) ? (prevIsBoundary && nextIsBoundary) : (prevIsBoundary || nextIsBoundary)))) : (currentChar == MARKUP_CHAR_ITALIC && ((ListenerUtil.mutListener.listen(23046) ? (prevIsBoundary && nextIsBoundary) : (prevIsBoundary || nextIsBoundary)))))) {
                                    if (!ListenerUtil.mutListener.listen(23063)) {
                                        tokenLength = pushTextBufToken(tokenLength, i, tokens);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23068)) {
                                        tokens.add(new Token(TokenType.UNDERSCORE, i, (ListenerUtil.mutListener.listen(23067) ? (i % 1) : (ListenerUtil.mutListener.listen(23066) ? (i / 1) : (ListenerUtil.mutListener.listen(23065) ? (i * 1) : (ListenerUtil.mutListener.listen(23064) ? (i - 1) : (i + 1)))))));
                                    }
                                } else if ((ListenerUtil.mutListener.listen(23049) ? (currentChar == MARKUP_CHAR_STRIKETHRU || ((ListenerUtil.mutListener.listen(23048) ? (prevIsBoundary && nextIsBoundary) : (prevIsBoundary || nextIsBoundary)))) : (currentChar == MARKUP_CHAR_STRIKETHRU && ((ListenerUtil.mutListener.listen(23048) ? (prevIsBoundary && nextIsBoundary) : (prevIsBoundary || nextIsBoundary)))))) {
                                    if (!ListenerUtil.mutListener.listen(23057)) {
                                        tokenLength = pushTextBufToken(tokenLength, i, tokens);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23062)) {
                                        tokens.add(new Token(TokenType.TILDE, i, (ListenerUtil.mutListener.listen(23061) ? (i % 1) : (ListenerUtil.mutListener.listen(23060) ? (i / 1) : (ListenerUtil.mutListener.listen(23059) ? (i * 1) : (ListenerUtil.mutListener.listen(23058) ? (i - 1) : (i + 1)))))));
                                    }
                                } else if (currentChar == '\n') {
                                    if (!ListenerUtil.mutListener.listen(23051)) {
                                        tokenLength = pushTextBufToken(tokenLength, i, tokens);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23056)) {
                                        tokens.add(new Token(TokenType.NEWLINE, i, (ListenerUtil.mutListener.listen(23055) ? (i % 1) : (ListenerUtil.mutListener.listen(23054) ? (i / 1) : (ListenerUtil.mutListener.listen(23053) ? (i * 1) : (ListenerUtil.mutListener.listen(23052) ? (i - 1) : (i + 1)))))));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(23050)) {
                                        tokenLength++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23099)) {
            pushTextBufToken((ListenerUtil.mutListener.listen(23094) ? (tokenLength % 1) : (ListenerUtil.mutListener.listen(23093) ? (tokenLength / 1) : (ListenerUtil.mutListener.listen(23092) ? (tokenLength * 1) : (ListenerUtil.mutListener.listen(23091) ? (tokenLength + 1) : (tokenLength - 1))))), (ListenerUtil.mutListener.listen(23098) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(23097) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(23096) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(23095) ? (text.length() + 1) : (text.length() - 1))))), tokens);
        }
        return tokens;
    }

    private void applySpans(SpannableStringBuilder s, Stack<SpanItem> spanStack) {
        ArrayList<Integer> deletables = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(23128)) {
            {
                long _loopCounter150 = 0;
                while (!spanStack.isEmpty()) {
                    ListenerUtil.loopListener.listen("_loopCounter150", ++_loopCounter150);
                    SpanItem span = spanStack.pop();
                    if (!ListenerUtil.mutListener.listen(23127)) {
                        if ((ListenerUtil.mutListener.listen(23104) ? (span.textStart >= span.textEnd) : (ListenerUtil.mutListener.listen(23103) ? (span.textStart <= span.textEnd) : (ListenerUtil.mutListener.listen(23102) ? (span.textStart < span.textEnd) : (ListenerUtil.mutListener.listen(23101) ? (span.textStart != span.textEnd) : (ListenerUtil.mutListener.listen(23100) ? (span.textStart == span.textEnd) : (span.textStart > span.textEnd))))))) {
                            if (!ListenerUtil.mutListener.listen(23126)) {
                                logger.debug("range problem. ignore");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23125)) {
                                if ((ListenerUtil.mutListener.listen(23115) ? ((ListenerUtil.mutListener.listen(23109) ? (span.textStart >= 0) : (ListenerUtil.mutListener.listen(23108) ? (span.textStart <= 0) : (ListenerUtil.mutListener.listen(23107) ? (span.textStart < 0) : (ListenerUtil.mutListener.listen(23106) ? (span.textStart != 0) : (ListenerUtil.mutListener.listen(23105) ? (span.textStart == 0) : (span.textStart > 0)))))) || (ListenerUtil.mutListener.listen(23114) ? (span.textEnd >= s.length()) : (ListenerUtil.mutListener.listen(23113) ? (span.textEnd <= s.length()) : (ListenerUtil.mutListener.listen(23112) ? (span.textEnd > s.length()) : (ListenerUtil.mutListener.listen(23111) ? (span.textEnd != s.length()) : (ListenerUtil.mutListener.listen(23110) ? (span.textEnd == s.length()) : (span.textEnd < s.length()))))))) : ((ListenerUtil.mutListener.listen(23109) ? (span.textStart >= 0) : (ListenerUtil.mutListener.listen(23108) ? (span.textStart <= 0) : (ListenerUtil.mutListener.listen(23107) ? (span.textStart < 0) : (ListenerUtil.mutListener.listen(23106) ? (span.textStart != 0) : (ListenerUtil.mutListener.listen(23105) ? (span.textStart == 0) : (span.textStart > 0)))))) && (ListenerUtil.mutListener.listen(23114) ? (span.textEnd >= s.length()) : (ListenerUtil.mutListener.listen(23113) ? (span.textEnd <= s.length()) : (ListenerUtil.mutListener.listen(23112) ? (span.textEnd > s.length()) : (ListenerUtil.mutListener.listen(23111) ? (span.textEnd != s.length()) : (ListenerUtil.mutListener.listen(23110) ? (span.textEnd == s.length()) : (span.textEnd < s.length()))))))))) {
                                    if (!ListenerUtil.mutListener.listen(23124)) {
                                        if ((ListenerUtil.mutListener.listen(23120) ? (span.textStart >= span.textEnd) : (ListenerUtil.mutListener.listen(23119) ? (span.textStart <= span.textEnd) : (ListenerUtil.mutListener.listen(23118) ? (span.textStart > span.textEnd) : (ListenerUtil.mutListener.listen(23117) ? (span.textStart < span.textEnd) : (ListenerUtil.mutListener.listen(23116) ? (span.textStart == span.textEnd) : (span.textStart != span.textEnd))))))) {
                                            if (!ListenerUtil.mutListener.listen(23121)) {
                                                s.setSpan(getCharacterStyle(span.kind), span.textStart, span.textEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(23122)) {
                                                deletables.add(span.markerStart);
                                            }
                                            if (!ListenerUtil.mutListener.listen(23123)) {
                                                deletables.add(span.markerEnd);
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
        if (!ListenerUtil.mutListener.listen(23141)) {
            if ((ListenerUtil.mutListener.listen(23133) ? (deletables.size() >= 0) : (ListenerUtil.mutListener.listen(23132) ? (deletables.size() <= 0) : (ListenerUtil.mutListener.listen(23131) ? (deletables.size() < 0) : (ListenerUtil.mutListener.listen(23130) ? (deletables.size() != 0) : (ListenerUtil.mutListener.listen(23129) ? (deletables.size() == 0) : (deletables.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(23134)) {
                    Collections.sort(deletables, Collections.reverseOrder());
                }
                if (!ListenerUtil.mutListener.listen(23140)) {
                    {
                        long _loopCounter151 = 0;
                        for (int deletable : deletables) {
                            ListenerUtil.loopListener.listen("_loopCounter151", ++_loopCounter151);
                            if (!ListenerUtil.mutListener.listen(23139)) {
                                s.delete(deletable, (ListenerUtil.mutListener.listen(23138) ? (deletable % 1) : (ListenerUtil.mutListener.listen(23137) ? (deletable / 1) : (ListenerUtil.mutListener.listen(23136) ? (deletable * 1) : (ListenerUtil.mutListener.listen(23135) ? (deletable - 1) : (deletable + 1))))));
                            }
                        }
                    }
                }
            }
        }
    }

    private void applySpans(Editable s, @ColorInt int markerColor, Stack<SpanItem> spanStack) {
        if (!ListenerUtil.mutListener.listen(23178)) {
            {
                long _loopCounter152 = 0;
                while (!spanStack.isEmpty()) {
                    ListenerUtil.loopListener.listen("_loopCounter152", ++_loopCounter152);
                    SpanItem span = spanStack.pop();
                    if (!ListenerUtil.mutListener.listen(23177)) {
                        if ((ListenerUtil.mutListener.listen(23146) ? (span.textStart >= span.textEnd) : (ListenerUtil.mutListener.listen(23145) ? (span.textStart <= span.textEnd) : (ListenerUtil.mutListener.listen(23144) ? (span.textStart < span.textEnd) : (ListenerUtil.mutListener.listen(23143) ? (span.textStart != span.textEnd) : (ListenerUtil.mutListener.listen(23142) ? (span.textStart == span.textEnd) : (span.textStart > span.textEnd))))))) {
                            if (!ListenerUtil.mutListener.listen(23176)) {
                                logger.debug("range problem. ignore");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23175)) {
                                if ((ListenerUtil.mutListener.listen(23157) ? ((ListenerUtil.mutListener.listen(23151) ? (span.textStart >= 0) : (ListenerUtil.mutListener.listen(23150) ? (span.textStart <= 0) : (ListenerUtil.mutListener.listen(23149) ? (span.textStart < 0) : (ListenerUtil.mutListener.listen(23148) ? (span.textStart != 0) : (ListenerUtil.mutListener.listen(23147) ? (span.textStart == 0) : (span.textStart > 0)))))) || (ListenerUtil.mutListener.listen(23156) ? (span.textEnd >= s.length()) : (ListenerUtil.mutListener.listen(23155) ? (span.textEnd <= s.length()) : (ListenerUtil.mutListener.listen(23154) ? (span.textEnd > s.length()) : (ListenerUtil.mutListener.listen(23153) ? (span.textEnd != s.length()) : (ListenerUtil.mutListener.listen(23152) ? (span.textEnd == s.length()) : (span.textEnd < s.length()))))))) : ((ListenerUtil.mutListener.listen(23151) ? (span.textStart >= 0) : (ListenerUtil.mutListener.listen(23150) ? (span.textStart <= 0) : (ListenerUtil.mutListener.listen(23149) ? (span.textStart < 0) : (ListenerUtil.mutListener.listen(23148) ? (span.textStart != 0) : (ListenerUtil.mutListener.listen(23147) ? (span.textStart == 0) : (span.textStart > 0)))))) && (ListenerUtil.mutListener.listen(23156) ? (span.textEnd >= s.length()) : (ListenerUtil.mutListener.listen(23155) ? (span.textEnd <= s.length()) : (ListenerUtil.mutListener.listen(23154) ? (span.textEnd > s.length()) : (ListenerUtil.mutListener.listen(23153) ? (span.textEnd != s.length()) : (ListenerUtil.mutListener.listen(23152) ? (span.textEnd == s.length()) : (span.textEnd < s.length()))))))))) {
                                    if (!ListenerUtil.mutListener.listen(23174)) {
                                        if ((ListenerUtil.mutListener.listen(23162) ? (span.textStart >= span.textEnd) : (ListenerUtil.mutListener.listen(23161) ? (span.textStart <= span.textEnd) : (ListenerUtil.mutListener.listen(23160) ? (span.textStart > span.textEnd) : (ListenerUtil.mutListener.listen(23159) ? (span.textStart < span.textEnd) : (ListenerUtil.mutListener.listen(23158) ? (span.textStart == span.textEnd) : (span.textStart != span.textEnd))))))) {
                                            if (!ListenerUtil.mutListener.listen(23163)) {
                                                s.setSpan(getCharacterStyle(span.kind), span.textStart, span.textEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(23168)) {
                                                s.setSpan(new ForegroundColorSpan(markerColor), span.markerStart, (ListenerUtil.mutListener.listen(23167) ? (span.markerStart % 1) : (ListenerUtil.mutListener.listen(23166) ? (span.markerStart / 1) : (ListenerUtil.mutListener.listen(23165) ? (span.markerStart * 1) : (ListenerUtil.mutListener.listen(23164) ? (span.markerStart - 1) : (span.markerStart + 1))))), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(23173)) {
                                                s.setSpan(new ForegroundColorSpan(markerColor), span.markerEnd, (ListenerUtil.mutListener.listen(23172) ? (span.markerEnd % 1) : (ListenerUtil.mutListener.listen(23171) ? (span.markerEnd / 1) : (ListenerUtil.mutListener.listen(23170) ? (span.markerEnd * 1) : (ListenerUtil.mutListener.listen(23169) ? (span.markerEnd - 1) : (span.markerEnd + 1))))), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
    }

    // Helper: Pop the stack, throw an exception if it's empty
    private Token popStack(Stack<Token> stack) throws MarkupParserException {
        try {
            return stack.pop();
        } catch (EmptyStackException e) {
            throw new MarkupParserException("Stack is empty");
        }
    }

    private static CharacterStyle getCharacterStyle(TokenType tokenType) {
        switch(tokenType) {
            case UNDERSCORE:
                return new StyleSpan(Typeface.ITALIC);
            case ASTERISK:
                return new StyleSpan(Typeface.BOLD);
            case TILDE:
            default:
                return new StrikethroughSpan();
        }
    }

    private void parse(ArrayList<Token> tokens, SpannableStringBuilder builder, Editable editable, @ColorInt int markerColor) throws MarkupParserException {
        // matching token and convert everything in between to formatted text.
        Stack<Token> stack = new Stack<>();
        Stack<SpanItem> spanStack = new Stack<>();
        TokenPresenceMap tokenPresenceMap = new TokenPresenceMap();
        if (!ListenerUtil.mutListener.listen(23202)) {
            {
                long _loopCounter154 = 0;
                for (Token token : tokens) {
                    ListenerUtil.loopListener.listen("_loopCounter154", ++_loopCounter154);
                    if (!ListenerUtil.mutListener.listen(23201)) {
                        switch(token.kind) {
                            // Keep text as-is
                            case TEXT:
                                if (!ListenerUtil.mutListener.listen(23179)) {
                                    stack.push(token);
                                }
                                break;
                            // If a markup token is found, try to find a matching token.
                            case ASTERISK:
                            case UNDERSCORE:
                            case TILDE:
                                if (!ListenerUtil.mutListener.listen(23199)) {
                                    // Optimization: Only search the stack if a token with this token type exists
                                    if (tokenPresenceMap.get(token.kind)) {
                                        // markup to the text parts in between those two tokens.
                                        Stack<Token> textParts = new Stack<>();
                                        if (!ListenerUtil.mutListener.listen(23198)) {
                                            {
                                                long _loopCounter153 = 0;
                                                while (true) {
                                                    ListenerUtil.loopListener.listen("_loopCounter153", ++_loopCounter153);
                                                    Token stackTop = popStack(stack);
                                                    if (!ListenerUtil.mutListener.listen(23196)) {
                                                        if (stackTop.kind == TokenType.TEXT) {
                                                            if (!ListenerUtil.mutListener.listen(23195)) {
                                                                textParts.push(stackTop);
                                                            }
                                                        } else if (stackTop.kind == token.kind) {
                                                            int start, end;
                                                            if ((ListenerUtil.mutListener.listen(23187) ? (textParts.size() >= 0) : (ListenerUtil.mutListener.listen(23186) ? (textParts.size() <= 0) : (ListenerUtil.mutListener.listen(23185) ? (textParts.size() > 0) : (ListenerUtil.mutListener.listen(23184) ? (textParts.size() < 0) : (ListenerUtil.mutListener.listen(23183) ? (textParts.size() != 0) : (textParts.size() == 0))))))) {
                                                                // no text in between two markups
                                                                start = end = stackTop.end;
                                                            } else {
                                                                start = textParts.get((ListenerUtil.mutListener.listen(23191) ? (textParts.size() % 1) : (ListenerUtil.mutListener.listen(23190) ? (textParts.size() / 1) : (ListenerUtil.mutListener.listen(23189) ? (textParts.size() * 1) : (ListenerUtil.mutListener.listen(23188) ? (textParts.size() + 1) : (textParts.size() - 1)))))).start;
                                                                end = textParts.get(0).end;
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(23192)) {
                                                                spanStack.push(new SpanItem(token.kind, start, end, stackTop.start, token.start));
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(23193)) {
                                                                stack.push(new Token(TokenType.TEXT, start, end));
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(23194)) {
                                                                tokenPresenceMap.put(token.kind, false);
                                                            }
                                                            break;
                                                        } else if (isMarkupToken(stackTop.kind)) {
                                                            if (!ListenerUtil.mutListener.listen(23182)) {
                                                                textParts.push(new Token(TokenType.TEXT, stackTop.start, stackTop.end));
                                                            }
                                                        } else {
                                                            throw new MarkupParserException("Unknown token on stack: " + token.kind);
                                                        }
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(23197)) {
                                                        tokenPresenceMap.put(stackTop.kind, false);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(23180)) {
                                            stack.push(token);
                                        }
                                        if (!ListenerUtil.mutListener.listen(23181)) {
                                            tokenPresenceMap.put(token.kind, true);
                                        }
                                    }
                                }
                                break;
                            // Don't apply formatting across newlines
                            case NEWLINE:
                                if (!ListenerUtil.mutListener.listen(23200)) {
                                    tokenPresenceMap.init();
                                }
                                break;
                            default:
                                throw new MarkupParserException("Invalid token kind: " + token.kind);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23211)) {
            // Concatenate processed tokens
            if (builder != null) {
                if (!ListenerUtil.mutListener.listen(23210)) {
                    applySpans(builder, spanStack);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23209)) {
                    if ((ListenerUtil.mutListener.listen(23207) ? (spanStack.size() >= 0) : (ListenerUtil.mutListener.listen(23206) ? (spanStack.size() <= 0) : (ListenerUtil.mutListener.listen(23205) ? (spanStack.size() < 0) : (ListenerUtil.mutListener.listen(23204) ? (spanStack.size() != 0) : (ListenerUtil.mutListener.listen(23203) ? (spanStack.size() == 0) : (spanStack.size() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(23208)) {
                            applySpans(editable, markerColor, spanStack);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Add text markup to given SpannableStringBuilder
     *  @param builder
     */
    public void markify(SpannableStringBuilder builder) {
        try {
            if (!ListenerUtil.mutListener.listen(23212)) {
                parse(tokenize(builder), builder, null, 0);
            }
        } catch (MarkupParserException e) {
        }
    }

    /**
     *  Add text markup to text in given editable.
     *  @param editable Editable to be markified
     *  @param markerColor Desired color of markup markers
     */
    public void markify(Editable editable, @ColorInt int markerColor) {
        try {
            if (!ListenerUtil.mutListener.listen(23213)) {
                parse(tokenize(editable), null, editable, markerColor);
            }
        } catch (MarkupParserException e) {
        }
    }

    public class MarkupParserException extends Exception {

        MarkupParserException(String e) {
            super(e);
        }
    }
}
