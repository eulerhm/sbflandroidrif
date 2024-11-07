/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import ch.threema.app.R;
import ch.threema.app.emojis.EmojiParser;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextUtil {

    private static final Logger logger = LoggerFactory.getLogger(TextUtil.class);

    public static String trim(String string, int maxLength, String postFix) {
        if (!ListenerUtil.mutListener.listen(55681)) {
            if ((ListenerUtil.mutListener.listen(55676) ? (((ListenerUtil.mutListener.listen(55670) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(55669) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(55668) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(55667) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(55666) ? (maxLength == 0) : (maxLength > 0))))))) || ((ListenerUtil.mutListener.listen(55675) ? (string.length() >= maxLength) : (ListenerUtil.mutListener.listen(55674) ? (string.length() <= maxLength) : (ListenerUtil.mutListener.listen(55673) ? (string.length() < maxLength) : (ListenerUtil.mutListener.listen(55672) ? (string.length() != maxLength) : (ListenerUtil.mutListener.listen(55671) ? (string.length() == maxLength) : (string.length() > maxLength)))))))) : (((ListenerUtil.mutListener.listen(55670) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(55669) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(55668) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(55667) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(55666) ? (maxLength == 0) : (maxLength > 0))))))) && ((ListenerUtil.mutListener.listen(55675) ? (string.length() >= maxLength) : (ListenerUtil.mutListener.listen(55674) ? (string.length() <= maxLength) : (ListenerUtil.mutListener.listen(55673) ? (string.length() < maxLength) : (ListenerUtil.mutListener.listen(55672) ? (string.length() != maxLength) : (ListenerUtil.mutListener.listen(55671) ? (string.length() == maxLength) : (string.length() > maxLength)))))))))) {
                return string.substring(0, (ListenerUtil.mutListener.listen(55680) ? (maxLength % postFix.length()) : (ListenerUtil.mutListener.listen(55679) ? (maxLength / postFix.length()) : (ListenerUtil.mutListener.listen(55678) ? (maxLength * postFix.length()) : (ListenerUtil.mutListener.listen(55677) ? (maxLength + postFix.length()) : (maxLength - postFix.length())))))) + postFix;
            }
        }
        return string;
    }

    public static String trim(String string, int maxLength) {
        return trim(string, maxLength, "...");
    }

    public static CharSequence trim(CharSequence string, int maxLength, CharSequence postFix) {
        if (!ListenerUtil.mutListener.listen(55696)) {
            if ((ListenerUtil.mutListener.listen(55692) ? (((ListenerUtil.mutListener.listen(55686) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(55685) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(55684) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(55683) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(55682) ? (maxLength == 0) : (maxLength > 0))))))) || ((ListenerUtil.mutListener.listen(55691) ? (string.length() >= maxLength) : (ListenerUtil.mutListener.listen(55690) ? (string.length() <= maxLength) : (ListenerUtil.mutListener.listen(55689) ? (string.length() < maxLength) : (ListenerUtil.mutListener.listen(55688) ? (string.length() != maxLength) : (ListenerUtil.mutListener.listen(55687) ? (string.length() == maxLength) : (string.length() > maxLength)))))))) : (((ListenerUtil.mutListener.listen(55686) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(55685) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(55684) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(55683) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(55682) ? (maxLength == 0) : (maxLength > 0))))))) && ((ListenerUtil.mutListener.listen(55691) ? (string.length() >= maxLength) : (ListenerUtil.mutListener.listen(55690) ? (string.length() <= maxLength) : (ListenerUtil.mutListener.listen(55689) ? (string.length() < maxLength) : (ListenerUtil.mutListener.listen(55688) ? (string.length() != maxLength) : (ListenerUtil.mutListener.listen(55687) ? (string.length() == maxLength) : (string.length() > maxLength)))))))))) {
                if (!ListenerUtil.mutListener.listen(55695)) {
                    if (postFix != null) {
                        if (!ListenerUtil.mutListener.listen(55694)) {
                            TextUtils.concat(string.subSequence(0, maxLength), postFix);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(55693)) {
                            string.subSequence(0, maxLength);
                        }
                    }
                }
            }
        }
        return string;
    }

    public static Spannable highlightMatches(Context context, CharSequence fullText, String filterText, boolean background, boolean normalize) {
        if (!ListenerUtil.mutListener.listen(55697)) {
            if (fullText == null) {
                return new SpannableString("");
            }
        }
        int stringLength = fullText.length();
        Spannable spannableString = new SpannableString(fullText);
        if (!ListenerUtil.mutListener.listen(55725)) {
            if ((ListenerUtil.mutListener.listen(55703) ? (filterText != null || (ListenerUtil.mutListener.listen(55702) ? (stringLength >= 0) : (ListenerUtil.mutListener.listen(55701) ? (stringLength <= 0) : (ListenerUtil.mutListener.listen(55700) ? (stringLength < 0) : (ListenerUtil.mutListener.listen(55699) ? (stringLength != 0) : (ListenerUtil.mutListener.listen(55698) ? (stringLength == 0) : (stringLength > 0))))))) : (filterText != null && (ListenerUtil.mutListener.listen(55702) ? (stringLength >= 0) : (ListenerUtil.mutListener.listen(55701) ? (stringLength <= 0) : (ListenerUtil.mutListener.listen(55700) ? (stringLength < 0) : (ListenerUtil.mutListener.listen(55699) ? (stringLength != 0) : (ListenerUtil.mutListener.listen(55698) ? (stringLength == 0) : (stringLength > 0))))))))) {
                int start, end, index = 0, length = filterText.length();
                int highlightColor = context.getResources().getColor(R.color.match_highlight_color);
                String fullUpperText = normalize ? LocaleUtil.normalize(fullText.toString()) : fullText.toString().toLowerCase();
                String filterUpperText = normalize ? LocaleUtil.normalize(filterText) : filterText.toLowerCase();
                {
                    long _loopCounter684 = 0;
                    while ((ListenerUtil.mutListener.listen(55724) ? ((start = fullUpperText.indexOf(filterUpperText, index)) <= 0) : (ListenerUtil.mutListener.listen(55723) ? ((start = fullUpperText.indexOf(filterUpperText, index)) > 0) : (ListenerUtil.mutListener.listen(55722) ? ((start = fullUpperText.indexOf(filterUpperText, index)) < 0) : (ListenerUtil.mutListener.listen(55721) ? ((start = fullUpperText.indexOf(filterUpperText, index)) != 0) : (ListenerUtil.mutListener.listen(55720) ? ((start = fullUpperText.indexOf(filterUpperText, index)) == 0) : ((start = fullUpperText.indexOf(filterUpperText, index)) >= 0))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter684", ++_loopCounter684);
                        end = (ListenerUtil.mutListener.listen(55707) ? (start % length) : (ListenerUtil.mutListener.listen(55706) ? (start / length) : (ListenerUtil.mutListener.listen(55705) ? (start * length) : (ListenerUtil.mutListener.listen(55704) ? (start - length) : (start + length)))));
                        if (!ListenerUtil.mutListener.listen(55714)) {
                            if ((ListenerUtil.mutListener.listen(55712) ? (end >= stringLength) : (ListenerUtil.mutListener.listen(55711) ? (end > stringLength) : (ListenerUtil.mutListener.listen(55710) ? (end < stringLength) : (ListenerUtil.mutListener.listen(55709) ? (end != stringLength) : (ListenerUtil.mutListener.listen(55708) ? (end == stringLength) : (end <= stringLength))))))) {
                                if (!ListenerUtil.mutListener.listen(55713)) {
                                    spannableString.setSpan(background ? new BackgroundColorSpan(highlightColor) : new ForegroundColorSpan(highlightColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(55719)) {
                            index = (ListenerUtil.mutListener.listen(55718) ? (start % 1) : (ListenerUtil.mutListener.listen(55717) ? (start / 1) : (ListenerUtil.mutListener.listen(55716) ? (start * 1) : (ListenerUtil.mutListener.listen(55715) ? (start - 1) : (start + 1)))));
                        }
                    }
                }
            }
        }
        return spannableString;
    }

    /**
     *  Splits a given text string into multiple strings no longer than maxLength bytes keeping in account emojis (even composite emojis such as flags)
     *
     *  @param text input text
     *  @param maxLength maximum length of one result string in bytes
     *  @return array of text strings
     */
    public static ArrayList<String> splitEmojiText(String text, int maxLength) {
        ArrayList<String> splitText = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(55772)) {
            if ((ListenerUtil.mutListener.listen(55730) ? (text.getBytes(StandardCharsets.UTF_8).length >= maxLength) : (ListenerUtil.mutListener.listen(55729) ? (text.getBytes(StandardCharsets.UTF_8).length <= maxLength) : (ListenerUtil.mutListener.listen(55728) ? (text.getBytes(StandardCharsets.UTF_8).length < maxLength) : (ListenerUtil.mutListener.listen(55727) ? (text.getBytes(StandardCharsets.UTF_8).length != maxLength) : (ListenerUtil.mutListener.listen(55726) ? (text.getBytes(StandardCharsets.UTF_8).length == maxLength) : (text.getBytes(StandardCharsets.UTF_8).length > maxLength))))))) {
                String toAdd;
                StringBuilder newString = new StringBuilder();
                {
                    long _loopCounter685 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(55764) ? (i >= text.length()) : (ListenerUtil.mutListener.listen(55763) ? (i <= text.length()) : (ListenerUtil.mutListener.listen(55762) ? (i > text.length()) : (ListenerUtil.mutListener.listen(55761) ? (i != text.length()) : (ListenerUtil.mutListener.listen(55760) ? (i == text.length()) : (i < text.length())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter685", ++_loopCounter685);
                        final EmojiParser.ParseResult result = EmojiParser.parseAt(text, i);
                        if ((ListenerUtil.mutListener.listen(55737) ? (result != null || (ListenerUtil.mutListener.listen(55736) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(55735) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(55734) ? (result.length < 0) : (ListenerUtil.mutListener.listen(55733) ? (result.length != 0) : (ListenerUtil.mutListener.listen(55732) ? (result.length == 0) : (result.length > 0))))))) : (result != null && (ListenerUtil.mutListener.listen(55736) ? (result.length >= 0) : (ListenerUtil.mutListener.listen(55735) ? (result.length <= 0) : (ListenerUtil.mutListener.listen(55734) ? (result.length < 0) : (ListenerUtil.mutListener.listen(55733) ? (result.length != 0) : (ListenerUtil.mutListener.listen(55732) ? (result.length == 0) : (result.length > 0))))))))) {
                            // emoji found at this position
                            toAdd = text.substring(i, i + result.length);
                            if (!ListenerUtil.mutListener.listen(55746)) {
                                i += (ListenerUtil.mutListener.listen(55745) ? (result.length % 1) : (ListenerUtil.mutListener.listen(55744) ? (result.length / 1) : (ListenerUtil.mutListener.listen(55743) ? (result.length * 1) : (ListenerUtil.mutListener.listen(55742) ? (result.length + 1) : (result.length - 1)))));
                            }
                        } else {
                            toAdd = text.substring(i, (ListenerUtil.mutListener.listen(55741) ? (i % 1) : (ListenerUtil.mutListener.listen(55740) ? (i / 1) : (ListenerUtil.mutListener.listen(55739) ? (i * 1) : (ListenerUtil.mutListener.listen(55738) ? (i - 1) : (i + 1))))));
                        }
                        if (!ListenerUtil.mutListener.listen(55758)) {
                            if ((ListenerUtil.mutListener.listen(55755) ? ((ListenerUtil.mutListener.listen(55750) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length % toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55749) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length / toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55748) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length * toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55747) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length - toAdd.getBytes(StandardCharsets.UTF_8).length) : (newString.toString().getBytes(StandardCharsets.UTF_8).length + toAdd.getBytes(StandardCharsets.UTF_8).length))))) >= maxLength) : (ListenerUtil.mutListener.listen(55754) ? ((ListenerUtil.mutListener.listen(55750) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length % toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55749) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length / toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55748) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length * toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55747) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length - toAdd.getBytes(StandardCharsets.UTF_8).length) : (newString.toString().getBytes(StandardCharsets.UTF_8).length + toAdd.getBytes(StandardCharsets.UTF_8).length))))) <= maxLength) : (ListenerUtil.mutListener.listen(55753) ? ((ListenerUtil.mutListener.listen(55750) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length % toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55749) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length / toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55748) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length * toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55747) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length - toAdd.getBytes(StandardCharsets.UTF_8).length) : (newString.toString().getBytes(StandardCharsets.UTF_8).length + toAdd.getBytes(StandardCharsets.UTF_8).length))))) < maxLength) : (ListenerUtil.mutListener.listen(55752) ? ((ListenerUtil.mutListener.listen(55750) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length % toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55749) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length / toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55748) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length * toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55747) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length - toAdd.getBytes(StandardCharsets.UTF_8).length) : (newString.toString().getBytes(StandardCharsets.UTF_8).length + toAdd.getBytes(StandardCharsets.UTF_8).length))))) != maxLength) : (ListenerUtil.mutListener.listen(55751) ? ((ListenerUtil.mutListener.listen(55750) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length % toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55749) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length / toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55748) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length * toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55747) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length - toAdd.getBytes(StandardCharsets.UTF_8).length) : (newString.toString().getBytes(StandardCharsets.UTF_8).length + toAdd.getBytes(StandardCharsets.UTF_8).length))))) == maxLength) : ((ListenerUtil.mutListener.listen(55750) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length % toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55749) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length / toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55748) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length * toAdd.getBytes(StandardCharsets.UTF_8).length) : (ListenerUtil.mutListener.listen(55747) ? (newString.toString().getBytes(StandardCharsets.UTF_8).length - toAdd.getBytes(StandardCharsets.UTF_8).length) : (newString.toString().getBytes(StandardCharsets.UTF_8).length + toAdd.getBytes(StandardCharsets.UTF_8).length))))) > maxLength))))))) {
                                if (!ListenerUtil.mutListener.listen(55756)) {
                                    splitText.add(newString.toString());
                                }
                                if (!ListenerUtil.mutListener.listen(55757)) {
                                    newString = new StringBuilder();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(55759)) {
                            newString.append(toAdd);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55771)) {
                    if ((ListenerUtil.mutListener.listen(55769) ? (newString.toString().length() >= 0) : (ListenerUtil.mutListener.listen(55768) ? (newString.toString().length() <= 0) : (ListenerUtil.mutListener.listen(55767) ? (newString.toString().length() < 0) : (ListenerUtil.mutListener.listen(55766) ? (newString.toString().length() != 0) : (ListenerUtil.mutListener.listen(55765) ? (newString.toString().length() == 0) : (newString.toString().length() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(55770)) {
                            splitText.add(newString.toString());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55731)) {
                    splitText.add(text);
                }
            }
        }
        return splitText;
    }

    private static String[] passwordChecks = { // do not allow single repeating characters
    "(.)\\1+", // do not short numeric-only passwords
    "^[0-9]{0,15}$" };

    /**
     *  Check a given password string for badness
     *  @param password
     *  @return true if the password is considered bad or listed in the list of bad passwords, false otherwise
     */
    @WorkerThread
    public static boolean checkBadPassword(@NonNull Context context, @NonNull String password) {
        if (!ListenerUtil.mutListener.listen(55782)) {
            if (AppRestrictionUtil.isSafePasswordPatternSet(context)) {
                try {
                    Pattern pattern = Pattern.compile(AppRestrictionUtil.getSafePasswordPattern(context));
                    return !pattern.matcher(password).matches();
                } catch (Exception e) {
                    return true;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55774)) {
                    {
                        long _loopCounter686 = 0;
                        // check if password is unsafe
                        for (String pattern : passwordChecks) {
                            ListenerUtil.loopListener.listen("_loopCounter686", ++_loopCounter686);
                            if (!ListenerUtil.mutListener.listen(55773)) {
                                if (password.matches(pattern)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                BufferedReader bufferedReader = null;
                try {
                    if (!ListenerUtil.mutListener.listen(55779)) {
                        bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("passwords/bad_passwords.txt")));
                    }
                    String line;
                    if (!ListenerUtil.mutListener.listen(55781)) {
                        {
                            long _loopCounter687 = 0;
                            while ((line = bufferedReader.readLine()) != null) {
                                ListenerUtil.loopListener.listen("_loopCounter687", ++_loopCounter687);
                                if (!ListenerUtil.mutListener.listen(55780)) {
                                    if (password.equalsIgnoreCase(line)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(55775)) {
                        logger.error("Exception", e);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(55778)) {
                        if (bufferedReader != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(55777)) {
                                    bufferedReader.close();
                                }
                            } catch (IOException e) {
                                if (!ListenerUtil.mutListener.listen(55776)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @NonNull
    public static String capitalize(String string) {
        if (!ListenerUtil.mutListener.listen(55789)) {
            if (!TestUtil.empty(string)) {
                if (!ListenerUtil.mutListener.listen(55788)) {
                    if ((ListenerUtil.mutListener.listen(55787) ? (string.length() >= 1) : (ListenerUtil.mutListener.listen(55786) ? (string.length() <= 1) : (ListenerUtil.mutListener.listen(55785) ? (string.length() < 1) : (ListenerUtil.mutListener.listen(55784) ? (string.length() != 1) : (ListenerUtil.mutListener.listen(55783) ? (string.length() == 1) : (string.length() > 1))))))) {
                        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
                    } else {
                        return Character.toString(Character.toUpperCase(string.charAt(0)));
                    }
                }
            }
        }
        return "";
    }
}
