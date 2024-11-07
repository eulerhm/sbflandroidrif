package com.ichi2.libanki.template;

import java.util.Iterator;
import java.util.NoSuchElementException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import static com.ichi2.libanki.template.Tokenizer.TokenKind.CloseConditional;
import static com.ichi2.libanki.template.Tokenizer.TokenKind.OpenConditional;
import static com.ichi2.libanki.template.Tokenizer.TokenKind.OpenNegated;
import static com.ichi2.libanki.template.Tokenizer.TokenKind.Replacement;
import static com.ichi2.libanki.template.Tokenizer.TokenKind.Text;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class encodes template.rs's file creating template.
 * Due to the way iterator work in java, it's easier for the class to keep track of the template
 */
public class Tokenizer implements Iterator<Tokenizer.Token> {

    /**
     * The remaining of the string to read.
     */
    @NonNull
    private String mTemplate;

    /**
     * Become true if lexing failed. That is, the string start with {{, but no }} is found.
     */
    @Nullable
    private boolean mFailed;

    Tokenizer(@NonNull String template) {
        if (!ListenerUtil.mutListener.listen(20667)) {
            mTemplate = template;
        }
    }

    @Override
    public boolean hasNext() {
        return (ListenerUtil.mutListener.listen(20673) ? ((ListenerUtil.mutListener.listen(20672) ? (mTemplate.length() >= 0) : (ListenerUtil.mutListener.listen(20671) ? (mTemplate.length() <= 0) : (ListenerUtil.mutListener.listen(20670) ? (mTemplate.length() < 0) : (ListenerUtil.mutListener.listen(20669) ? (mTemplate.length() != 0) : (ListenerUtil.mutListener.listen(20668) ? (mTemplate.length() == 0) : (mTemplate.length() > 0)))))) || !mFailed) : ((ListenerUtil.mutListener.listen(20672) ? (mTemplate.length() >= 0) : (ListenerUtil.mutListener.listen(20671) ? (mTemplate.length() <= 0) : (ListenerUtil.mutListener.listen(20670) ? (mTemplate.length() < 0) : (ListenerUtil.mutListener.listen(20669) ? (mTemplate.length() != 0) : (ListenerUtil.mutListener.listen(20668) ? (mTemplate.length() == 0) : (mTemplate.length() > 0)))))) && !mFailed));
    }

    /**
     * The kind of data we can find in a template and may want to consider for card generation
     */
    enum TokenKind {

        /**
         * Some text, assumed not to contains {{*}}
         */
        Text,
        /**
         * {{Field name}}
         */
        Replacement,
        /**
         * {{#Field name}}
         */
        OpenConditional,
        /**
         * {{^Field name}}
         */
        OpenNegated,
        /**
         * {{/Field name}}
         */
        CloseConditional
    }

    /**
     * This is equivalent to upstream's template.rs's Token type.
     */
    @VisibleForTesting
    protected static class Token {

        private final TokenKind mKind;

        /**
         * If mKind is Text, then this contains the text.
         * Otherwise, it contains the content between "{{" and "}}", without the curly braces.
         */
        private final String mText;

        public Token(TokenKind king, String text) {
            mKind = king;
            mText = text;
        }

        @NonNull
        @Override
        public String toString() {
            return mKind + "(\"" + mText + "\")";
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (!ListenerUtil.mutListener.listen(20674)) {
                if (!(obj instanceof Token)) {
                    return false;
                }
            }
            Token t = ((Token) obj);
            return (ListenerUtil.mutListener.listen(20675) ? (mKind == t.mKind || mText.equals(t.mText)) : (mKind == t.mKind && mText.equals(t.mText)));
        }

        @NonNull
        public TokenKind getKind() {
            return mKind;
        }

        @NonNull
        public String getText() {
            return mText;
        }
    }

    /**
     * This is similar to template.rs's type IResult<&str, Token>.
     * That is, it contains a token that was parsed, and the remaining of the string that must be read.
     */
    @VisibleForTesting
    protected static class IResult {

        private final Token mToken;

        /*
        This is a substring of the template. Java deal efficiently with substring by encoding it as original string,
        start index and length, so there is no loss in efficiency in using string instead of position.
         */
        private final String mRemaining;

        public IResult(Token token, String remaining) {
            this.mToken = token;
            this.mRemaining = remaining;
        }

        @NonNull
        @Override
        public String toString() {
            return "(" + mToken + ", \"" + mRemaining + "\")";
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (!ListenerUtil.mutListener.listen(20676)) {
                if (!(obj instanceof IResult)) {
                    return false;
                }
            }
            IResult r = ((IResult) obj);
            return (ListenerUtil.mutListener.listen(20677) ? (mToken.equals(r.mToken) || mRemaining.equals(r.mRemaining)) : (mToken.equals(r.mToken) && mRemaining.equals(r.mRemaining)));
        }
    }

    /**
     * @param template The part of the template that must still be lexed
     * @return The longest prefix without {{, or null if it's empty.
     */
    @VisibleForTesting
    @Nullable
    protected static IResult text_token(@NonNull String template) {
        int first_handlebar = template.indexOf("{{");
        int text_size = ((ListenerUtil.mutListener.listen(20682) ? (first_handlebar >= -1) : (ListenerUtil.mutListener.listen(20681) ? (first_handlebar <= -1) : (ListenerUtil.mutListener.listen(20680) ? (first_handlebar > -1) : (ListenerUtil.mutListener.listen(20679) ? (first_handlebar < -1) : (ListenerUtil.mutListener.listen(20678) ? (first_handlebar != -1) : (first_handlebar == -1))))))) ? template.length() : first_handlebar;
        if (!ListenerUtil.mutListener.listen(20688)) {
            if ((ListenerUtil.mutListener.listen(20687) ? (text_size >= 0) : (ListenerUtil.mutListener.listen(20686) ? (text_size <= 0) : (ListenerUtil.mutListener.listen(20685) ? (text_size > 0) : (ListenerUtil.mutListener.listen(20684) ? (text_size < 0) : (ListenerUtil.mutListener.listen(20683) ? (text_size != 0) : (text_size == 0))))))) {
                return null;
            }
        }
        return new IResult(new Token(Text, template.substring(0, text_size)), template.substring(text_size));
    }

    /**
     * classify handle based on leading character
     * @param handle The content between {{ and }}
     */
    @NonNull
    protected static Token classify_handle(@NonNull String handle) {
        int start_pos = 0;
        if (!ListenerUtil.mutListener.listen(20696)) {
            {
                long _loopCounter439 = 0;
                while ((ListenerUtil.mutListener.listen(20695) ? ((ListenerUtil.mutListener.listen(20694) ? (start_pos >= handle.length()) : (ListenerUtil.mutListener.listen(20693) ? (start_pos <= handle.length()) : (ListenerUtil.mutListener.listen(20692) ? (start_pos > handle.length()) : (ListenerUtil.mutListener.listen(20691) ? (start_pos != handle.length()) : (ListenerUtil.mutListener.listen(20690) ? (start_pos == handle.length()) : (start_pos < handle.length())))))) || handle.charAt(start_pos) == '{') : ((ListenerUtil.mutListener.listen(20694) ? (start_pos >= handle.length()) : (ListenerUtil.mutListener.listen(20693) ? (start_pos <= handle.length()) : (ListenerUtil.mutListener.listen(20692) ? (start_pos > handle.length()) : (ListenerUtil.mutListener.listen(20691) ? (start_pos != handle.length()) : (ListenerUtil.mutListener.listen(20690) ? (start_pos == handle.length()) : (start_pos < handle.length())))))) && handle.charAt(start_pos) == '{'))) {
                    ListenerUtil.loopListener.listen("_loopCounter439", ++_loopCounter439);
                    if (!ListenerUtil.mutListener.listen(20689)) {
                        start_pos++;
                    }
                }
            }
        }
        String start = handle.substring(start_pos).trim();
        if ((ListenerUtil.mutListener.listen(20701) ? (start.length() >= 2) : (ListenerUtil.mutListener.listen(20700) ? (start.length() <= 2) : (ListenerUtil.mutListener.listen(20699) ? (start.length() > 2) : (ListenerUtil.mutListener.listen(20698) ? (start.length() != 2) : (ListenerUtil.mutListener.listen(20697) ? (start.length() == 2) : (start.length() < 2))))))) {
            return new Token(Replacement, start);
        }
        switch(start.charAt(0)) {
            case '#':
                return new Token(OpenConditional, start.substring(1));
            case '/':
                return new Token(CloseConditional, start.substring(1));
            case '^':
                return new Token(OpenNegated, start.substring(1));
            default:
                return new Token(Replacement, start);
        }
    }

    /**
     * @param template a part of a template to lex
     * @return The content of handlebar at start of template
     */
    @VisibleForTesting
    @Nullable
    protected static IResult handlebar_token(@NonNull String template) {
        if (!ListenerUtil.mutListener.listen(20709)) {
            if ((ListenerUtil.mutListener.listen(20708) ? ((ListenerUtil.mutListener.listen(20707) ? ((ListenerUtil.mutListener.listen(20706) ? (template.length() >= 2) : (ListenerUtil.mutListener.listen(20705) ? (template.length() <= 2) : (ListenerUtil.mutListener.listen(20704) ? (template.length() > 2) : (ListenerUtil.mutListener.listen(20703) ? (template.length() != 2) : (ListenerUtil.mutListener.listen(20702) ? (template.length() == 2) : (template.length() < 2)))))) && template.charAt(0) != '{') : ((ListenerUtil.mutListener.listen(20706) ? (template.length() >= 2) : (ListenerUtil.mutListener.listen(20705) ? (template.length() <= 2) : (ListenerUtil.mutListener.listen(20704) ? (template.length() > 2) : (ListenerUtil.mutListener.listen(20703) ? (template.length() != 2) : (ListenerUtil.mutListener.listen(20702) ? (template.length() == 2) : (template.length() < 2)))))) || template.charAt(0) != '{')) && template.charAt(1) != '{') : ((ListenerUtil.mutListener.listen(20707) ? ((ListenerUtil.mutListener.listen(20706) ? (template.length() >= 2) : (ListenerUtil.mutListener.listen(20705) ? (template.length() <= 2) : (ListenerUtil.mutListener.listen(20704) ? (template.length() > 2) : (ListenerUtil.mutListener.listen(20703) ? (template.length() != 2) : (ListenerUtil.mutListener.listen(20702) ? (template.length() == 2) : (template.length() < 2)))))) && template.charAt(0) != '{') : ((ListenerUtil.mutListener.listen(20706) ? (template.length() >= 2) : (ListenerUtil.mutListener.listen(20705) ? (template.length() <= 2) : (ListenerUtil.mutListener.listen(20704) ? (template.length() > 2) : (ListenerUtil.mutListener.listen(20703) ? (template.length() != 2) : (ListenerUtil.mutListener.listen(20702) ? (template.length() == 2) : (template.length() < 2)))))) || template.charAt(0) != '{')) || template.charAt(1) != '{'))) {
                return null;
            }
        }
        int end = template.indexOf("}}");
        if (!ListenerUtil.mutListener.listen(20715)) {
            if ((ListenerUtil.mutListener.listen(20714) ? (end >= -1) : (ListenerUtil.mutListener.listen(20713) ? (end <= -1) : (ListenerUtil.mutListener.listen(20712) ? (end > -1) : (ListenerUtil.mutListener.listen(20711) ? (end < -1) : (ListenerUtil.mutListener.listen(20710) ? (end != -1) : (end == -1))))))) {
                return null;
            }
        }
        String content = template.substring(2, end);
        @NonNull
        Token handlebar = classify_handle(content);
        return new IResult(handlebar, template.substring((ListenerUtil.mutListener.listen(20719) ? (end % 2) : (ListenerUtil.mutListener.listen(20718) ? (end / 2) : (ListenerUtil.mutListener.listen(20717) ? (end * 2) : (ListenerUtil.mutListener.listen(20716) ? (end - 2) : (end + 2)))))));
    }

    /**
     * @param template The remaining of template to lex
     * @return The next token, or null at end of string
     */
    @VisibleForTesting
    @Nullable
    protected static IResult next_token(@NonNull String template) {
        IResult t = handlebar_token(template);
        if (!ListenerUtil.mutListener.listen(20720)) {
            if (t != null) {
                return t;
            }
        }
        return text_token(template);
    }

    /**
     * @return The next token.
     * @throws TemplateError.NoClosingBrackets with no message if the template is entirely lexed, and with the remaining string otherwise.
     */
    @Override
    public Token next() throws TemplateError.NoClosingBrackets {
        if (!ListenerUtil.mutListener.listen(20726)) {
            if ((ListenerUtil.mutListener.listen(20725) ? (mTemplate.length() >= 0) : (ListenerUtil.mutListener.listen(20724) ? (mTemplate.length() <= 0) : (ListenerUtil.mutListener.listen(20723) ? (mTemplate.length() > 0) : (ListenerUtil.mutListener.listen(20722) ? (mTemplate.length() < 0) : (ListenerUtil.mutListener.listen(20721) ? (mTemplate.length() != 0) : (mTemplate.length() == 0))))))) {
                throw new NoSuchElementException();
            }
        }
        IResult ir = next_token(mTemplate);
        if (!ListenerUtil.mutListener.listen(20728)) {
            if (ir == null) {
                if (!ListenerUtil.mutListener.listen(20727)) {
                    // Missing closing }}
                    mFailed = true;
                }
                throw new TemplateError.NoClosingBrackets(mTemplate);
            }
        }
        if (!ListenerUtil.mutListener.listen(20729)) {
            mTemplate = ir.mRemaining;
        }
        return ir.mToken;
    }
}
