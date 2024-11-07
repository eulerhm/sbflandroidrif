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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Pair;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.ColorInt;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.UserService;
import ch.threema.app.ui.MentionClickableSpan;
import ch.threema.app.ui.MentionSpan;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.NameUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiMarkupUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmojiMarkupUtil.class);

    private static final int LARGE_EMOJI_SCALE_FACTOR = 2;

    private static final int LARGE_EMOJI_THRESHOLD = 3;

    public static final String MENTION_INDICATOR = "@";

    protected static final String MENTION_REGEX = MENTION_INDICATOR + "\\[[0-9A-Z*@]{8}\\]";

    private final Pattern mention;

    // Singleton stuff
    private static EmojiMarkupUtil sInstance = null;

    public static synchronized EmojiMarkupUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(15016)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(15015)) {
                    sInstance = new EmojiMarkupUtil();
                }
            }
        }
        return sInstance;
    }

    private EmojiMarkupUtil() {
        this.mention = Pattern.compile(MENTION_REGEX);
        if (!ListenerUtil.mutListener.listen(15017)) {
            // set emoji style based on preferences
            ConfigUtils.setEmojiStyle(ThreemaApplication.getAppContext(), -1);
        }
    }

    // the ContactService may not yet exist when the instance is created (e.g. when masterkey is locked)
    private ContactService getContactService() {
        try {
            return ThreemaApplication.getServiceManager().getContactService();
        } catch (Exception e) {
        }
        return null;
    }

    // the UserService may not yet exist when the instance is created (e.g. when masterkey is locked)
    private UserService getUserService() {
        try {
            return ThreemaApplication.getServiceManager().getUserService();
        } catch (Exception e) {
        }
        return null;
    }

    public CharSequence addTextSpans(CharSequence text) {
        return addTextSpans(null, text, null, false, false);
    }

    public CharSequence addTextSpans(Context context, CharSequence text, TextView textView, boolean ignoreMarkup) {
        return addTextSpans(context, text, textView, ignoreMarkup, false);
    }

    public CharSequence addTextSpans(Context context, CharSequence text, TextView textView, boolean ignoreMarkup, boolean singleScale) {
        return addTextSpans(context, text, textView, ignoreMarkup, ignoreMarkup, singleScale);
    }

    public CharSequence addTextSpans(Context context, CharSequence text, TextView textView, boolean ignoreMarkup, boolean ignoreMentions, boolean singleScale) {
        if (!ListenerUtil.mutListener.listen(15018)) {
            if (text == null) {
                return "";
            }
        }
        int length = text.length();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (!ListenerUtil.mutListener.listen(15024)) {
            if ((ListenerUtil.mutListener.listen(15023) ? (length >= 0) : (ListenerUtil.mutListener.listen(15022) ? (length <= 0) : (ListenerUtil.mutListener.listen(15021) ? (length > 0) : (ListenerUtil.mutListener.listen(15020) ? (length < 0) : (ListenerUtil.mutListener.listen(15019) ? (length != 0) : (length == 0))))))) {
                return builder;
            }
        }
        if (!ListenerUtil.mutListener.listen(15078)) {
            if ((ListenerUtil.mutListener.listen(15032) ? ((ListenerUtil.mutListener.listen(15025) ? (context != null || textView != null) : (context != null && textView != null)) || ((ListenerUtil.mutListener.listen(15031) ? (ConfigUtils.isDefaultEmojiStyle() && (ListenerUtil.mutListener.listen(15030) ? (length >= 5) : (ListenerUtil.mutListener.listen(15029) ? (length > 5) : (ListenerUtil.mutListener.listen(15028) ? (length < 5) : (ListenerUtil.mutListener.listen(15027) ? (length != 5) : (ListenerUtil.mutListener.listen(15026) ? (length == 5) : (length <= 5))))))) : (ConfigUtils.isDefaultEmojiStyle() || (ListenerUtil.mutListener.listen(15030) ? (length >= 5) : (ListenerUtil.mutListener.listen(15029) ? (length > 5) : (ListenerUtil.mutListener.listen(15028) ? (length < 5) : (ListenerUtil.mutListener.listen(15027) ? (length != 5) : (ListenerUtil.mutListener.listen(15026) ? (length == 5) : (length <= 5)))))))))) : ((ListenerUtil.mutListener.listen(15025) ? (context != null || textView != null) : (context != null && textView != null)) && ((ListenerUtil.mutListener.listen(15031) ? (ConfigUtils.isDefaultEmojiStyle() && (ListenerUtil.mutListener.listen(15030) ? (length >= 5) : (ListenerUtil.mutListener.listen(15029) ? (length > 5) : (ListenerUtil.mutListener.listen(15028) ? (length < 5) : (ListenerUtil.mutListener.listen(15027) ? (length != 5) : (ListenerUtil.mutListener.listen(15026) ? (length == 5) : (length <= 5))))))) : (ConfigUtils.isDefaultEmojiStyle() || (ListenerUtil.mutListener.listen(15030) ? (length >= 5) : (ListenerUtil.mutListener.listen(15029) ? (length > 5) : (ListenerUtil.mutListener.listen(15028) ? (length < 5) : (ListenerUtil.mutListener.listen(15027) ? (length != 5) : (ListenerUtil.mutListener.listen(15026) ? (length == 5) : (length <= 5)))))))))))) {
                ArrayList<Pair<EmojiParser.ParseResult, Integer>> results = new ArrayList<>();
                boolean containsRegularText = false;
                if (!ListenerUtil.mutListener.listen(15052)) {
                    {
                        long _loopCounter136 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(15051) ? (i >= length) : (ListenerUtil.mutListener.listen(15050) ? (i <= length) : (ListenerUtil.mutListener.listen(15049) ? (i > length) : (ListenerUtil.mutListener.listen(15048) ? (i != length) : (ListenerUtil.mutListener.listen(15047) ? (i == length) : (i < length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter136", ++_loopCounter136);
                            // Try to find emoji at the specified index
                            final EmojiParser.ParseResult result = EmojiParser.parseAt(text, i);
                            if (!ListenerUtil.mutListener.listen(15046)) {
                                if ((ListenerUtil.mutListener.listen(15038) ? (result != null || (ListenerUtil.mutListener.listen(15037) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(15036) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(15035) ? (result.length < 0) : (ListenerUtil.mutListener.listen(15034) ? (result.length != 0) : (ListenerUtil.mutListener.listen(15033) ? (result.length == 0) : (result.length > 0))))))) : (result != null && (ListenerUtil.mutListener.listen(15037) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(15036) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(15035) ? (result.length < 0) : (ListenerUtil.mutListener.listen(15034) ? (result.length != 0) : (ListenerUtil.mutListener.listen(15033) ? (result.length == 0) : (result.length > 0))))))))) {
                                    if (!ListenerUtil.mutListener.listen(15040)) {
                                        // An emoji was found!
                                        results.add(new Pair<>(result, i));
                                    }
                                    if (!ListenerUtil.mutListener.listen(15045)) {
                                        i += (ListenerUtil.mutListener.listen(15044) ? (result.length % 1) : (ListenerUtil.mutListener.listen(15043) ? (result.length / 1) : (ListenerUtil.mutListener.listen(15042) ? (result.length * 1) : (ListenerUtil.mutListener.listen(15041) ? (result.length + 1) : (result.length - 1)))));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(15039)) {
                                        containsRegularText = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15077)) {
                    if ((ListenerUtil.mutListener.listen(15057) ? (results.size() >= 0) : (ListenerUtil.mutListener.listen(15056) ? (results.size() <= 0) : (ListenerUtil.mutListener.listen(15055) ? (results.size() < 0) : (ListenerUtil.mutListener.listen(15054) ? (results.size() != 0) : (ListenerUtil.mutListener.listen(15053) ? (results.size() == 0) : (results.size() > 0))))))) {
                        int scaleFactor = (ListenerUtil.mutListener.listen(15065) ? ((ListenerUtil.mutListener.listen(15059) ? ((ListenerUtil.mutListener.listen(15058) ? (singleScale || ConfigUtils.isBiggerSingleEmojis(context)) : (singleScale && ConfigUtils.isBiggerSingleEmojis(context))) || !containsRegularText) : ((ListenerUtil.mutListener.listen(15058) ? (singleScale || ConfigUtils.isBiggerSingleEmojis(context)) : (singleScale && ConfigUtils.isBiggerSingleEmojis(context))) && !containsRegularText)) || (ListenerUtil.mutListener.listen(15064) ? (results.size() >= LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15063) ? (results.size() > LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15062) ? (results.size() < LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15061) ? (results.size() != LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15060) ? (results.size() == LARGE_EMOJI_THRESHOLD) : (results.size() <= LARGE_EMOJI_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(15059) ? ((ListenerUtil.mutListener.listen(15058) ? (singleScale || ConfigUtils.isBiggerSingleEmojis(context)) : (singleScale && ConfigUtils.isBiggerSingleEmojis(context))) || !containsRegularText) : ((ListenerUtil.mutListener.listen(15058) ? (singleScale || ConfigUtils.isBiggerSingleEmojis(context)) : (singleScale && ConfigUtils.isBiggerSingleEmojis(context))) && !containsRegularText)) && (ListenerUtil.mutListener.listen(15064) ? (results.size() >= LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15063) ? (results.size() > LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15062) ? (results.size() < LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15061) ? (results.size() != LARGE_EMOJI_THRESHOLD) : (ListenerUtil.mutListener.listen(15060) ? (results.size() == LARGE_EMOJI_THRESHOLD) : (results.size() <= LARGE_EMOJI_THRESHOLD)))))))) ? LARGE_EMOJI_SCALE_FACTOR : 1;
                        if (!ListenerUtil.mutListener.listen(15076)) {
                            if (ConfigUtils.isDefaultEmojiStyle()) {
                                if (!ListenerUtil.mutListener.listen(15075)) {
                                    {
                                        long _loopCounter138 = 0;
                                        for (Pair<EmojiParser.ParseResult, Integer> result : results) {
                                            ListenerUtil.loopListener.listen("_loopCounter138", ++_loopCounter138);
                                            Drawable drawable = EmojiManager.getInstance(context).getEmojiDrawable(result.first.coords);
                                            if (!ListenerUtil.mutListener.listen(15074)) {
                                                if (drawable != null) {
                                                    if (!ListenerUtil.mutListener.listen(15073)) {
                                                        builder.setSpan(new EmojiImageSpan(drawable, textView, scaleFactor), result.second, result.second + result.first.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if ((ListenerUtil.mutListener.listen(15070) ? (scaleFactor >= 1) : (ListenerUtil.mutListener.listen(15069) ? (scaleFactor <= 1) : (ListenerUtil.mutListener.listen(15068) ? (scaleFactor > 1) : (ListenerUtil.mutListener.listen(15067) ? (scaleFactor < 1) : (ListenerUtil.mutListener.listen(15066) ? (scaleFactor == 1) : (scaleFactor != 1))))))) {
                                if (!ListenerUtil.mutListener.listen(15072)) {
                                    {
                                        long _loopCounter137 = 0;
                                        for (Pair<EmojiParser.ParseResult, Integer> result : results) {
                                            ListenerUtil.loopListener.listen("_loopCounter137", ++_loopCounter137);
                                            if (!ListenerUtil.mutListener.listen(15071)) {
                                                builder.setSpan(new RelativeSizeSpan(scaleFactor), result.second, result.second + result.first.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        if (!ListenerUtil.mutListener.listen(15082)) {
            if (!ignoreMentions) {
                if (!ListenerUtil.mutListener.listen(15081)) {
                    if (textView == null) {
                        if (!ListenerUtil.mutListener.listen(15080)) {
                            builder = new SpannableStringBuilder(applyTextMentionMarkup(text));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(15079)) {
                            builder = applyMentionMarkup(context, builder);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15084)) {
            if (!ignoreMarkup) {
                if (!ListenerUtil.mutListener.listen(15083)) {
                    MarkupParser.getInstance().markify(builder);
                }
            }
        }
        return builder;
    }

    private SpannableStringBuilder applyMentionMarkup(Context context, SpannableStringBuilder inputText) {
        int start, end;
        ArrayList<Pair<Integer, Integer>> matches = new ArrayList<>();
        Matcher matcher = this.mention.matcher(inputText);
        if (!ListenerUtil.mutListener.listen(15086)) {
            {
                long _loopCounter139 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter139", ++_loopCounter139);
                    if (!ListenerUtil.mutListener.listen(15085)) {
                        matches.add(new Pair<>(matcher.start(), matcher.end()));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15092)) {
            if ((ListenerUtil.mutListener.listen(15091) ? (matches.size() >= 1) : (ListenerUtil.mutListener.listen(15090) ? (matches.size() <= 1) : (ListenerUtil.mutListener.listen(15089) ? (matches.size() > 1) : (ListenerUtil.mutListener.listen(15088) ? (matches.size() != 1) : (ListenerUtil.mutListener.listen(15087) ? (matches.size() == 1) : (matches.size() < 1))))))) {
                return inputText;
            }
        }
        @ColorInt
        int mentionColor = ConfigUtils.getColorFromAttribute(context, R.attr.mention_background);
        @ColorInt
        int invertedMentionColor = ConfigUtils.getColorFromAttribute(context, R.attr.mention_background_inverted);
        @ColorInt
        int mentionTextColor = ConfigUtils.getColorFromAttribute(context, R.attr.mention_text_color);
        @ColorInt
        int invertedMentionTextColor = ConfigUtils.getColorFromAttribute(context, R.attr.mention_text_color_inverted);
        SpannableStringBuilder s = new SpannableStringBuilder(inputText);
        {
            long _loopCounter140 = 0;
            for (int i = (ListenerUtil.mutListener.listen(15117) ? (matches.size() % 1) : (ListenerUtil.mutListener.listen(15116) ? (matches.size() / 1) : (ListenerUtil.mutListener.listen(15115) ? (matches.size() * 1) : (ListenerUtil.mutListener.listen(15114) ? (matches.size() + 1) : (matches.size() - 1))))); (ListenerUtil.mutListener.listen(15113) ? (i <= 0) : (ListenerUtil.mutListener.listen(15112) ? (i > 0) : (ListenerUtil.mutListener.listen(15111) ? (i < 0) : (ListenerUtil.mutListener.listen(15110) ? (i != 0) : (ListenerUtil.mutListener.listen(15109) ? (i == 0) : (i >= 0)))))); i--) {
                ListenerUtil.loopListener.listen("_loopCounter140", ++_loopCounter140);
                start = matches.get(i).first;
                end = matches.get(i).second;
                if (!ListenerUtil.mutListener.listen(15093)) {
                    s.setSpan(new MentionSpan(mentionColor, invertedMentionColor, mentionTextColor, invertedMentionTextColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(15099)) {
                    // hack: https://stackoverflow.com/questions/20069537/replacementspans-draw-method-isnt-called
                    if (inputText.length() == (ListenerUtil.mutListener.listen(15097) ? (end % start) : (ListenerUtil.mutListener.listen(15096) ? (end / start) : (ListenerUtil.mutListener.listen(15095) ? (end * start) : (ListenerUtil.mutListener.listen(15094) ? (end + start) : (end - start)))))) {
                        if (!ListenerUtil.mutListener.listen(15098)) {
                            s.append(" ");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15108)) {
                    s.setSpan(new MentionClickableSpan(inputText.subSequence((ListenerUtil.mutListener.listen(15103) ? (start % 2) : (ListenerUtil.mutListener.listen(15102) ? (start / 2) : (ListenerUtil.mutListener.listen(15101) ? (start * 2) : (ListenerUtil.mutListener.listen(15100) ? (start - 2) : (start + 2))))), (ListenerUtil.mutListener.listen(15107) ? (end % 1) : (ListenerUtil.mutListener.listen(15106) ? (end / 1) : (ListenerUtil.mutListener.listen(15105) ? (end * 1) : (ListenerUtil.mutListener.listen(15104) ? (end + 1) : (end - 1)))))).toString()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return s;
    }

    /**
     *  Replace mentions by text instead of spans (used where spans cannot be displayed, i.e. in notifications)
     *  @param inputText
     *  @return ChatSequence where all mentions have been replaced by contact names
     */
    private CharSequence applyTextMentionMarkup(CharSequence inputText) {
        String match, identity;
        CharSequence outputText = inputText;
        Matcher matcher = this.mention.matcher(inputText);
        {
            long _loopCounter141 = 0;
            while (matcher.find()) {
                ListenerUtil.loopListener.listen("_loopCounter141", ++_loopCounter141);
                match = matcher.group();
                identity = match.substring(2, (ListenerUtil.mutListener.listen(15121) ? (match.length() % 1) : (ListenerUtil.mutListener.listen(15120) ? (match.length() / 1) : (ListenerUtil.mutListener.listen(15119) ? (match.length() * 1) : (ListenerUtil.mutListener.listen(15118) ? (match.length() + 1) : (match.length() - 1))))));
                if (!ListenerUtil.mutListener.listen(15122)) {
                    outputText = TextUtils.replace(outputText, new String[] { match }, new CharSequence[] { MENTION_INDICATOR + NameUtil.getQuoteName(identity, getContactService(), getUserService()) });
                }
                if (!ListenerUtil.mutListener.listen(15123)) {
                    matcher = this.mention.matcher(outputText);
                }
            }
        }
        return outputText;
    }

    public CharSequence addMarkup(Context context, CharSequence text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (!ListenerUtil.mutListener.listen(15124)) {
            builder = applyMentionMarkup(context, builder);
        }
        if (!ListenerUtil.mutListener.listen(15125)) {
            MarkupParser.getInstance().markify(builder);
        }
        return builder;
    }

    public CharSequence addMentionMarkup(Context context, CharSequence text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (!ListenerUtil.mutListener.listen(15126)) {
            builder = applyMentionMarkup(context, builder);
        }
        return builder;
    }

    public String stripMentions(String inputText) {
        return inputText.replaceAll(MENTION_REGEX, "");
    }

    public CharSequence formatBodyTextString(Context context, String string, int maxLen) {
        if ((ListenerUtil.mutListener.listen(15132) ? (string != null || (ListenerUtil.mutListener.listen(15131) ? (string.length() >= 0) : (ListenerUtil.mutListener.listen(15130) ? (string.length() <= 0) : (ListenerUtil.mutListener.listen(15129) ? (string.length() < 0) : (ListenerUtil.mutListener.listen(15128) ? (string.length() != 0) : (ListenerUtil.mutListener.listen(15127) ? (string.length() == 0) : (string.length() > 0))))))) : (string != null && (ListenerUtil.mutListener.listen(15131) ? (string.length() >= 0) : (ListenerUtil.mutListener.listen(15130) ? (string.length() <= 0) : (ListenerUtil.mutListener.listen(15129) ? (string.length() < 0) : (ListenerUtil.mutListener.listen(15128) ? (string.length() != 0) : (ListenerUtil.mutListener.listen(15127) ? (string.length() == 0) : (string.length() > 0)))))))))
            return addMarkup(context, string.substring(0, Math.min(maxLen, string.length())));
        else
            return "";
    }
}
