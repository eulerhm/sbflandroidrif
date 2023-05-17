/**
 * ************************************************************************************
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2015 Houssam Salem <houssam.salem.au@gmail.com>                        *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki;

import android.text.Html;
import com.ichi2.utils.HtmlUtils;
import com.ichi2.utils.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.VisibleForTesting;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class is used to detect LaTeX tags in HTML and convert them to their corresponding image
 * file names.
 *
 * Anki provides shortcut forms for certain expressions. These three forms are considered valid
 * LaTeX tags in Anki:
 * 1 - [latex]...[/latex]
 * 2 - [$]...[$]
 * 3 - [$$]...[$$]
 *
 * Unlike the original python implementation of this class, the AnkiDroid version does not support
 * the generation of LaTeX images.
 */
@SuppressWarnings({ "PMD.MethodNamingConventions", "PMD.AvoidReassigningParameters" })
public class LaTeX {

    /**
     * Patterns used to identify LaTeX tags
     */
    public static final Pattern sStandardPattern = Pattern.compile("\\[latex](.+?)\\[/latex]", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    public static final Pattern sExpressionPattern = Pattern.compile("\\[\\$](.+?)\\[/\\$]", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    public static final Pattern sMathPattern = Pattern.compile("\\[\\$\\$](.+?)\\[/\\$\\$]", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    /**
     * Convert HTML with embedded latex tags to image links.
     * NOTE: _imgLink produces an alphanumeric filename so there is no need to escape the replacement string.
     */
    public static String mungeQA(String html, Collection col, Model model) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = sStandardPattern.matcher(html);
        Media m = col.getMedia();
        if (!ListenerUtil.mutListener.listen(22822)) {
            {
                long _loopCounter540 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter540", ++_loopCounter540);
                    if (!ListenerUtil.mutListener.listen(22821)) {
                        matcher.appendReplacement(sb, _imgLink(matcher.group(1), model, m));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22823)) {
            matcher.appendTail(sb);
        }
        if (!ListenerUtil.mutListener.listen(22824)) {
            matcher = sExpressionPattern.matcher(sb.toString());
        }
        if (!ListenerUtil.mutListener.listen(22825)) {
            sb = new StringBuffer();
        }
        if (!ListenerUtil.mutListener.listen(22827)) {
            {
                long _loopCounter541 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter541", ++_loopCounter541);
                    if (!ListenerUtil.mutListener.listen(22826)) {
                        matcher.appendReplacement(sb, _imgLink("$" + matcher.group(1) + "$", model, m));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22828)) {
            matcher.appendTail(sb);
        }
        if (!ListenerUtil.mutListener.listen(22829)) {
            matcher = sMathPattern.matcher(sb.toString());
        }
        if (!ListenerUtil.mutListener.listen(22830)) {
            sb = new StringBuffer();
        }
        if (!ListenerUtil.mutListener.listen(22832)) {
            {
                long _loopCounter542 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter542", ++_loopCounter542);
                    if (!ListenerUtil.mutListener.listen(22831)) {
                        matcher.appendReplacement(sb, _imgLink("\\begin{displaymath}" + matcher.group(1) + "\\end{displaymath}", model, m));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22833)) {
            matcher.appendTail(sb);
        }
        return sb.toString();
    }

    /**
     * Return an img link for LATEX.
     */
    @VisibleForTesting
    protected static String _imgLink(String latex, Model model, Media m) {
        String txt = _latexFromHtml(latex);
        String ext = "png";
        if (!ListenerUtil.mutListener.listen(22835)) {
            if (model.optBoolean("latexsvg", false)) {
                if (!ListenerUtil.mutListener.listen(22834)) {
                    ext = "svg";
                }
            }
        }
        String fname = "latex-" + Utils.checksum(txt) + "." + ext;
        if (m.have(fname)) {
            return "<img class=latex alt=\"" + HtmlUtils.escape(latex) + "\" src=\"" + fname + "\">";
        } else {
            return Matcher.quoteReplacement(latex);
        }
    }

    /**
     * Convert entities and fix newlines.
     */
    private static String _latexFromHtml(String latex) {
        if (!ListenerUtil.mutListener.listen(22836)) {
            latex = latex.replaceAll("<br( /)?>|<div>", "\n");
        }
        if (!ListenerUtil.mutListener.listen(22837)) {
            latex = Utils.stripHTML(latex);
        }
        return latex;
    }
}
