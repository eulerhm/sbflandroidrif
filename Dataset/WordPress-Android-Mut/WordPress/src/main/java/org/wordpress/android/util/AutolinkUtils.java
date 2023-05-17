package org.wordpress.android.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AutolinkUtils {

    private static final Set<Pattern> PROVIDERS;

    static {
        PROVIDERS = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(27502)) {
            PROVIDERS.add(Pattern.compile("(https?://((m|www)\\.)?youtube\\.com/watch\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27503)) {
            PROVIDERS.add(Pattern.compile("(https?://((m|www)\\.)?youtube\\.com/playlist\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27504)) {
            PROVIDERS.add(Pattern.compile("(https?://youtu\\.be/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27505)) {
            PROVIDERS.add(Pattern.compile("(https?://(.+\\.)?vimeo\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27506)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?dailymotion\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27507)) {
            PROVIDERS.add(Pattern.compile("(https?://dai\\.ly/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27508)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?flickr\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27509)) {
            PROVIDERS.add(Pattern.compile("(https?://flic\\.kr/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27510)) {
            PROVIDERS.add(Pattern.compile("(https?://(.+\\.)?smugmug\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27511)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?hulu\\.com/watch/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27512)) {
            PROVIDERS.add(Pattern.compile("(http://i*.photobucket.com/albums/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27513)) {
            PROVIDERS.add(Pattern.compile("(http://gi*.photobucket.com/groups/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27514)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?scribd\\.com/doc/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27515)) {
            PROVIDERS.add(Pattern.compile("(https?://wordpress\\.tv/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27516)) {
            PROVIDERS.add(Pattern.compile("(https?://(.+\\.)?polldaddy\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27517)) {
            PROVIDERS.add(Pattern.compile("(https?://poll\\.fm/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27518)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?funnyordie\\.com/videos/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27519)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?twitter\\.com/\\S+/status(es)?/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27520)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?twitter\\.com/\\S+$)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27521)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?twitter\\.com/\\S+/likes$)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27522)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?twitter\\.com/\\S+/lists/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27523)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?twitter\\.com/\\S+/timelines/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27524)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?twitter\\.com/i/moments/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27525)) {
            PROVIDERS.add(Pattern.compile("(https?://vine\\.co/v/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27526)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?soundcloud\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27527)) {
            PROVIDERS.add(Pattern.compile("(https?://(.+?\\.)?slideshare\\.net/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27528)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?instagr(\\.am|am\\.com)/p/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27529)) {
            PROVIDERS.add(Pattern.compile("(https?://(open|play)\\.spotify\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27530)) {
            PROVIDERS.add(Pattern.compile("(https?://(.+\\.)?imgur\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27531)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?meetu(\\.ps|p\\.com)/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27532)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?issuu\\.com/.+/docs/.+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27533)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?collegehumor\\.com/video/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27534)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?mixcloud\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27535)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.|embed\\.)?ted\\.com/talks/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27536)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?(animoto|video214)\\.com/play/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27537)) {
            PROVIDERS.add(Pattern.compile("(https?://(.+)\\.tumblr\\.com/post/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27538)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?kickstarter\\.com/projects/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27539)) {
            PROVIDERS.add(Pattern.compile("(https?://kck\\.st/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27540)) {
            PROVIDERS.add(Pattern.compile("(https?://cloudup\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27541)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?reverbnation\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27542)) {
            PROVIDERS.add(Pattern.compile("(https?://videopress\\.com/v/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27543)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?reddit\\.com/r/[^/]+/comments/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27544)) {
            PROVIDERS.add(Pattern.compile("(https?://(www\\.)?speakerdeck\\.com/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27545)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/\\S+/posts/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27546)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/\\S+/activity/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27547)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/\\S+/photos/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27548)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/photo(s/|\\.php)\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27549)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/permalink\\.php\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27550)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/media/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27551)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/questions/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27552)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/notes/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27553)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/\\S+/videos/\\S+)", Pattern.CASE_INSENSITIVE));
        }
        if (!ListenerUtil.mutListener.listen(27554)) {
            PROVIDERS.add(Pattern.compile("(https?://www\\.facebook\\.com/video\\.php\\S+)", Pattern.CASE_INSENSITIVE));
        }
    }

    public static String autoCreateLinks(String text) {
        if (!ListenerUtil.mutListener.listen(27555)) {
            if (text == null) {
                return null;
            }
        }
        Pattern urlPattern = Pattern.compile("(\\s+|^)((http|https|ftp|mailto):\\S+)");
        Matcher matcher = urlPattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(27562)) {
            {
                long _loopCounter406 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter406", ++_loopCounter406);
                    String whitespaces = matcher.group(1);
                    String url = matcher.group(2);
                    boolean denylisted = false;
                    if (!ListenerUtil.mutListener.listen(27558)) {
                        {
                            long _loopCounter405 = 0;
                            // Check if the URL is denylisted
                            for (Pattern providerPattern : PROVIDERS) {
                                ListenerUtil.loopListener.listen("_loopCounter405", ++_loopCounter405);
                                Matcher providerMatcher = providerPattern.matcher(url);
                                if (!ListenerUtil.mutListener.listen(27557)) {
                                    if (providerMatcher.matches()) {
                                        if (!ListenerUtil.mutListener.listen(27556)) {
                                            denylisted = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(27561)) {
                        // Create a <a href> HTML tag for the link
                        if (!denylisted) {
                            if (!ListenerUtil.mutListener.listen(27560)) {
                                matcher.appendReplacement(stringBuffer, whitespaces + "<a href=\"" + url + "\">" + url + "</a>");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(27559)) {
                                matcher.appendReplacement(stringBuffer, whitespaces + url);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27563)) {
            matcher.appendTail(stringBuffer);
        }
        return stringBuffer.toString();
    }
}
