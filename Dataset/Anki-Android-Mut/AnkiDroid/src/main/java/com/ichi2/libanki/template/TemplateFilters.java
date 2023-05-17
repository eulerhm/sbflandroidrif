package com.ichi2.libanki.template;

import android.content.res.Resources;
import android.text.TextUtils;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.libanki.Utils;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Port template_filters.rs
 */
public class TemplateFilters {

    public static final String CLOZE_DELETION_REPLACEMENT = "[...]";

    private static final Pattern fHookFieldMod = Pattern.compile("^(.*?)(?:\\((.*)\\))?$");

    public static final String clozeReg = "(?si)\\{\\{(c)%s::(.*?)(::(.*?))?\\}\\}";

    /**
     * @param txt The content of the field field_name
     * @param filters a list of filter to apply to this text
     * @param field_name A name of a field
     * @param tag The entire part between {{ and }}
     * @return The result of applying each filter successively to txt
     */
    @NonNull
    public static String apply_filters(@NonNull String txt, @NonNull List<String> filters, @NonNull String field_name, @NonNull String tag) {
        if (!ListenerUtil.mutListener.listen(20633)) {
            {
                long _loopCounter436 = 0;
                for (String filter : filters) {
                    ListenerUtil.loopListener.listen("_loopCounter436", ++_loopCounter436);
                    if (!ListenerUtil.mutListener.listen(20630)) {
                        txt = TemplateFilters.apply_filter(txt, filter, field_name, tag);
                    }
                    if (!ListenerUtil.mutListener.listen(20632)) {
                        if (txt == null) {
                            if (!ListenerUtil.mutListener.listen(20631)) {
                                txt = "";
                            }
                        }
                    }
                }
            }
        }
        return txt;
    }

    /**
     * @param txt The current text the filter may change. It may be changed by multiple filter.
     * @param filter The name of the filter to apply.
     * @param field_name The name of the field whose text is shown
     * @param tag The entire content of the tag.
     * @return Result of filter on current txt.
     */
    @Nullable
    protected static String apply_filter(@NonNull String txt, @NonNull String filter, @NonNull String field_name, @NonNull String tag) {
        // built-in modifiers
        if ("text".equals(filter)) {
            // strip html
            if (!TextUtils.isEmpty(txt)) {
                return Utils.stripHTML(txt);
            } else {
                return "";
            }
        } else if ("type".equals(filter)) {
            // to process
            return String.format(Locale.US, "[[%s]]", tag);
        } else if ((ListenerUtil.mutListener.listen(20634) ? (filter.startsWith("cq-") && filter.startsWith("ca-")) : (filter.startsWith("cq-") || filter.startsWith("ca-")))) {
            // cloze deletion
            String[] split = filter.split("-");
            if (!ListenerUtil.mutListener.listen(20640)) {
                filter = split[0];
            }
            String extra = split[1];
            if ((ListenerUtil.mutListener.listen(20641) ? (!TextUtils.isEmpty(txt) || !TextUtils.isEmpty(extra)) : (!TextUtils.isEmpty(txt) && !TextUtils.isEmpty(extra)))) {
                return clozeText(txt != null ? txt : "", extra, filter.charAt(1));
            } else {
                return "";
            }
        } else {
            // hook-based field modifier
            Matcher m = fHookFieldMod.matcher(filter);
            if (!ListenerUtil.mutListener.listen(20636)) {
                if (m.matches()) {
                    if (!ListenerUtil.mutListener.listen(20635)) {
                        filter = m.group(1);
                    }
                    String extra = m.group(2);
                }
            }
            if (!ListenerUtil.mutListener.listen(20638)) {
                if (txt == null) {
                    if (!ListenerUtil.mutListener.listen(20637)) {
                        txt = "";
                    }
                }
            }
            try {
                switch(filter) {
                    case "hint":
                        return runHint(txt, field_name);
                    case "kanji":
                        return FuriganaFilters.kanjiFilter(txt);
                    case "kana":
                        return FuriganaFilters.kanaFilter(txt);
                    case "furigana":
                        return FuriganaFilters.furiganaFilter(txt);
                    default:
                        return txt;
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(20639)) {
                    Timber.e(e, "Exception while running hook %s", filter);
                }
                return AnkiDroidApp.getAppResources().getString(R.string.filter_error, filter);
            }
        }
    }

    private static String runHint(String txt, String tag) {
        if (!ListenerUtil.mutListener.listen(20647)) {
            if ((ListenerUtil.mutListener.listen(20646) ? (txt.trim().length() >= 0) : (ListenerUtil.mutListener.listen(20645) ? (txt.trim().length() <= 0) : (ListenerUtil.mutListener.listen(20644) ? (txt.trim().length() > 0) : (ListenerUtil.mutListener.listen(20643) ? (txt.trim().length() < 0) : (ListenerUtil.mutListener.listen(20642) ? (txt.trim().length() != 0) : (txt.trim().length() == 0))))))) {
                return "";
            }
        }
        Resources res = AnkiDroidApp.getAppResources();
        // random id
        String domid = "hint" + txt.hashCode();
        return "<a class=hint href=\"#\" onclick=\"this.style.display='none';document.getElementById('" + domid + "').style.display='block';_relinquishFocus();return false;\">" + res.getString(R.string.show_hint, tag) + "</a><div id=\"" + domid + "\" class=hint style=\"display: none\">" + txt + "</div>";
    }

    @NonNull
    private static String clozeText(@NonNull String txt, @NonNull String ord, char type) {
        if (!ListenerUtil.mutListener.listen(20648)) {
            if (!Pattern.compile(String.format(Locale.US, clozeReg, ord)).matcher(txt).find()) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(20649)) {
            txt = removeFormattingFromMathjax(txt, ord);
        }
        Matcher m = Pattern.compile(String.format(Locale.US, clozeReg, ord)).matcher(txt);
        StringBuffer repl = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(20651)) {
            {
                long _loopCounter437 = 0;
                while (m.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter437", ++_loopCounter437);
                    // replace chosen cloze with type
                    String buf;
                    if (type == 'q') {
                        if (!TextUtils.isEmpty(m.group(4))) {
                            buf = "[" + m.group(4) + "]";
                        } else {
                            buf = CLOZE_DELETION_REPLACEMENT;
                        }
                    } else {
                        buf = m.group(2);
                    }
                    if ("c".equals(m.group(1))) {
                        buf = String.format("<span class=cloze>%s</span>", buf);
                    }
                    if (!ListenerUtil.mutListener.listen(20650)) {
                        m.appendReplacement(repl, Matcher.quoteReplacement(buf));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20652)) {
            txt = m.appendTail(repl).toString();
        }
        // and display other clozes normally
        return txt.replaceAll(String.format(Locale.US, clozeReg, "\\d+"), "$2");
    }

    /**
     * Marks all clozes within MathJax to prevent formatting them.
     *
     * Active Cloze deletions within MathJax should not be wrapped inside
     * a Cloze <span>, as that would interfere with MathJax. This method finds
     * all Cloze deletions number `ord` in `txt` which are inside MathJax inline
     * or display formulas, and replaces their opening '{{c123' with a '{{C123'.
     * The clozeText method interprets the upper-case C as "don't wrap this
     * Cloze in a <span>".
     */
    @NonNull
    public static String removeFormattingFromMathjax(@NonNull String txt, @NonNull String ord) {
        String creg = clozeReg.replace("(?si)", "");
        // flags in middle of expression deprecated
        boolean in_mathjax = false;
        // The following regex matches one of 3 things, noted below:
        String regex = "(?si)" + // group 1, MathJax opening
        "(\\\\[(\\[])|" + // group 2, MathJax close
        "(\\\\[])])|" + // group 3, Cloze deletion number `ord`
        "(" + String.format(Locale.US, creg, ord) + ")";
        Matcher m = Pattern.compile(regex).matcher(txt);
        StringBuffer repl = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(20664)) {
            {
                long _loopCounter438 = 0;
                while (m.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter438", ++_loopCounter438);
                    if (!ListenerUtil.mutListener.listen(20662)) {
                        if (m.group(1) != null) {
                            if (!ListenerUtil.mutListener.listen(20660)) {
                                if (in_mathjax) {
                                    if (!ListenerUtil.mutListener.listen(20659)) {
                                        Timber.d("MathJax opening found while already in MathJax");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(20661)) {
                                in_mathjax = true;
                            }
                        } else if (m.group(2) != null) {
                            if (!ListenerUtil.mutListener.listen(20657)) {
                                if (!in_mathjax) {
                                    if (!ListenerUtil.mutListener.listen(20656)) {
                                        Timber.d("MathJax close found while not in MathJax");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(20658)) {
                                in_mathjax = false;
                            }
                        } else if (m.group(3) != null) {
                            if (!ListenerUtil.mutListener.listen(20655)) {
                                if (in_mathjax) {
                                    if (!ListenerUtil.mutListener.listen(20654)) {
                                        // appendReplacement has an issue with backslashes, so...
                                        m.appendReplacement(repl, Matcher.quoteReplacement(m.group(0).replace("{{c" + ord + "::", "{{C" + ord + "::")));
                                    }
                                    continue;
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(20653)) {
                                Timber.d("Unexpected: no expected capture group is present");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20663)) {
                        // appendReplacement has an issue with backslashes, so...
                        m.appendReplacement(repl, Matcher.quoteReplacement(m.group(0)));
                    }
                }
            }
        }
        return m.appendTail(repl).toString();
    }
}
