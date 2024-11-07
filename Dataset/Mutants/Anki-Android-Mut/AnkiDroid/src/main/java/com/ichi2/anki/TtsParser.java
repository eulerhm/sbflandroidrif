package com.ichi2.anki;

import com.ichi2.libanki.template.TemplateFilters;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Parse card sides, extracting text snippets that should be read using a text-to-speech engine.
 */
public final class TtsParser {

    /**
     * Returns the list of text snippets contained in the given HTML fragment that should be read
     * using the Android text-to-speech engine, together with the languages they are in.
     * <p>
     * Each returned LocalisedText object contains the text extracted from a &lt;tts&gt; element
     * whose 'service' attribute is set to 'android', and the localeCode taken from the 'voice'
     * attribute of that element. This holds unless the HTML fragment contains no such &lt;tts&gt;
     * elements; in that case the function returns a single LocalisedText object containing the
     * text extracted from the whole HTML fragment, with the localeCode set to an empty string.
     */
    public static List<LocalisedText> getTextsToRead(String html, String clozeReplacement) {
        List<LocalisedText> textsToRead = new ArrayList<>();
        Element elem = Jsoup.parseBodyFragment(html).body();
        if (!ListenerUtil.mutListener.listen(12311)) {
            parseTtsElements(elem, textsToRead);
        }
        if (!ListenerUtil.mutListener.listen(12318)) {
            if ((ListenerUtil.mutListener.listen(12316) ? (textsToRead.size() >= 0) : (ListenerUtil.mutListener.listen(12315) ? (textsToRead.size() <= 0) : (ListenerUtil.mutListener.listen(12314) ? (textsToRead.size() > 0) : (ListenerUtil.mutListener.listen(12313) ? (textsToRead.size() < 0) : (ListenerUtil.mutListener.listen(12312) ? (textsToRead.size() != 0) : (textsToRead.size() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(12317)) {
                    // No <tts service="android"> elements found: return the text of the whole HTML fragment
                    textsToRead.add(new LocalisedText(elem.text().replace(TemplateFilters.CLOZE_DELETION_REPLACEMENT, clozeReplacement)));
                }
            }
        }
        return textsToRead;
    }

    private static void parseTtsElements(Element element, List<LocalisedText> textsToRead) {
        if (!ListenerUtil.mutListener.listen(12321)) {
            if ((ListenerUtil.mutListener.listen(12319) ? ("tts".equalsIgnoreCase(element.tagName()) || "android".equalsIgnoreCase(element.attr("service"))) : ("tts".equalsIgnoreCase(element.tagName()) && "android".equalsIgnoreCase(element.attr("service"))))) {
                if (!ListenerUtil.mutListener.listen(12320)) {
                    textsToRead.add(new LocalisedText(element.text(), element.attr("voice")));
                }
                // ignore any children
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12323)) {
            {
                long _loopCounter201 = 0;
                for (Element child : element.children()) {
                    ListenerUtil.loopListener.listen("_loopCounter201", ++_loopCounter201);
                    if (!ListenerUtil.mutListener.listen(12322)) {
                        parseTtsElements(child, textsToRead);
                    }
                }
            }
        }
    }

    /**
     * Snippet of text accompanied by its locale code (if known).
     */
    public static final class LocalisedText {

        private final String mText;

        private final String mLocaleCode;

        /**
         * Construct an object representing a snippet of text in an unknown locale.
         */
        public LocalisedText(String text) {
            mText = text;
            mLocaleCode = "";
        }

        /**
         * Construct an object representing a snippet of text in a particular locale.
         *
         * @param localeCode A string representation of a locale in the format returned by
         *                   Locale.toString().
         */
        public LocalisedText(String text, String localeCode) {
            mText = text;
            mLocaleCode = localeCode;
        }

        public String getText() {
            return mText;
        }

        public String getLocaleCode() {
            return mLocaleCode;
        }
    }
}
