package fr.free.nrw.commons.upload;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An {@link InputFilter} class that removes characters blocklisted in Wikimedia titles. The list
 * of blocklisted characters is linked below.
 * @see <a href="https://commons.wikimedia.org/wiki/MediaWiki:Titleblacklist"></a>wikimedia.org</a>
 */
public class UploadMediaDetailInputFilter implements InputFilter {

    private final Pattern[] patterns;

    /**
     * Initializes the blocklisted patterns.
     */
    public UploadMediaDetailInputFilter() {
        patterns = new Pattern[] { Pattern.compile("[\\x{00A0}\\x{1680}\\x{180E}\\x{2000}-\\x{200B}\\x{2028}\\x{2029}\\x{202F}\\x{205F}]"), Pattern.compile("[\\x{202A}-\\x{202E}]"), Pattern.compile("\\p{Cc}"), // Added for colon(:)
        Pattern.compile("\\x{3A}"), Pattern.compile("\\x{FEFF}"), Pattern.compile("\\x{00AD}"), Pattern.compile("[\\x{E000}-\\x{F8FF}\\x{FFF0}-\\x{FFFF}]"), Pattern.compile("[^\\x{0000}-\\x{FFFF}\\p{sc=Han}]") };
    }

    /**
     * Checks if the source text contains any blocklisted characters.
     * @param source input text
     * @return contains a blocklisted character
     */
    private Boolean checkBlocklisted(final CharSequence source) {
        if (!ListenerUtil.mutListener.listen(7687)) {
            {
                long _loopCounter120 = 0;
                for (final Pattern pattern : patterns) {
                    ListenerUtil.loopListener.listen("_loopCounter120", ++_loopCounter120);
                    if (!ListenerUtil.mutListener.listen(7686)) {
                        if (pattern.matcher(source).find()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes any blocklisted characters from the source text.
     * @param source input text
     * @return a cleaned character sequence
     */
    private CharSequence removeBlocklisted(CharSequence source) {
        if (!ListenerUtil.mutListener.listen(7689)) {
            {
                long _loopCounter121 = 0;
                for (final Pattern pattern : patterns) {
                    ListenerUtil.loopListener.listen("_loopCounter121", ++_loopCounter121);
                    if (!ListenerUtil.mutListener.listen(7688)) {
                        source = pattern.matcher(source).replaceAll("");
                    }
                }
            }
        }
        return source;
    }

    /**
     * Filters out any blocklisted characters.
     * @param source {@inheritDoc}
     * @param start {@inheritDoc}
     * @param end {@inheritDoc}
     * @param dest {@inheritDoc}
     * @param dstart {@inheritDoc}
     * @param dend {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (!ListenerUtil.mutListener.listen(7696)) {
            if (checkBlocklisted(source)) {
                if (!ListenerUtil.mutListener.listen(7695)) {
                    if ((ListenerUtil.mutListener.listen(7694) ? (start >= dstart) : (ListenerUtil.mutListener.listen(7693) ? (start <= dstart) : (ListenerUtil.mutListener.listen(7692) ? (start > dstart) : (ListenerUtil.mutListener.listen(7691) ? (start < dstart) : (ListenerUtil.mutListener.listen(7690) ? (start != dstart) : (start == dstart))))))) {
                        return dest;
                    }
                }
                return removeBlocklisted(source);
            }
        }
        return null;
    }
}
