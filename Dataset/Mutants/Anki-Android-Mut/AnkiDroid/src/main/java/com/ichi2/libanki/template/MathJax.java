package com.ichi2.libanki.template;

import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MathJax {

    // MathJax opening delimiters
    private static final String[] sMathJaxOpenings = { "\\(", "\\[" };

    // MathJax closing delimiters
    private static final String[] sMathJaxClosings = { "\\)", "\\]" };

    public static boolean textContainsMathjax(@NonNull String txt) {
        String opening;
        String closing;
        {
            long _loopCounter429 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(20561) ? (i >= sMathJaxOpenings.length) : (ListenerUtil.mutListener.listen(20560) ? (i <= sMathJaxOpenings.length) : (ListenerUtil.mutListener.listen(20559) ? (i > sMathJaxOpenings.length) : (ListenerUtil.mutListener.listen(20558) ? (i != sMathJaxOpenings.length) : (ListenerUtil.mutListener.listen(20557) ? (i == sMathJaxOpenings.length) : (i < sMathJaxOpenings.length)))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter429", ++_loopCounter429);
                opening = sMathJaxOpenings[i];
                closing = sMathJaxClosings[i];
                int first_opening_index = txt.indexOf(opening);
                int last_closing_index = txt.lastIndexOf(closing);
                if (!ListenerUtil.mutListener.listen(20556)) {
                    if ((ListenerUtil.mutListener.listen(20555) ? ((ListenerUtil.mutListener.listen(20549) ? ((ListenerUtil.mutListener.listen(20543) ? (first_opening_index >= -1) : (ListenerUtil.mutListener.listen(20542) ? (first_opening_index <= -1) : (ListenerUtil.mutListener.listen(20541) ? (first_opening_index > -1) : (ListenerUtil.mutListener.listen(20540) ? (first_opening_index < -1) : (ListenerUtil.mutListener.listen(20539) ? (first_opening_index == -1) : (first_opening_index != -1)))))) || (ListenerUtil.mutListener.listen(20548) ? (last_closing_index >= -1) : (ListenerUtil.mutListener.listen(20547) ? (last_closing_index <= -1) : (ListenerUtil.mutListener.listen(20546) ? (last_closing_index > -1) : (ListenerUtil.mutListener.listen(20545) ? (last_closing_index < -1) : (ListenerUtil.mutListener.listen(20544) ? (last_closing_index == -1) : (last_closing_index != -1))))))) : ((ListenerUtil.mutListener.listen(20543) ? (first_opening_index >= -1) : (ListenerUtil.mutListener.listen(20542) ? (first_opening_index <= -1) : (ListenerUtil.mutListener.listen(20541) ? (first_opening_index > -1) : (ListenerUtil.mutListener.listen(20540) ? (first_opening_index < -1) : (ListenerUtil.mutListener.listen(20539) ? (first_opening_index == -1) : (first_opening_index != -1)))))) && (ListenerUtil.mutListener.listen(20548) ? (last_closing_index >= -1) : (ListenerUtil.mutListener.listen(20547) ? (last_closing_index <= -1) : (ListenerUtil.mutListener.listen(20546) ? (last_closing_index > -1) : (ListenerUtil.mutListener.listen(20545) ? (last_closing_index < -1) : (ListenerUtil.mutListener.listen(20544) ? (last_closing_index == -1) : (last_closing_index != -1)))))))) || (ListenerUtil.mutListener.listen(20554) ? (first_opening_index >= last_closing_index) : (ListenerUtil.mutListener.listen(20553) ? (first_opening_index <= last_closing_index) : (ListenerUtil.mutListener.listen(20552) ? (first_opening_index > last_closing_index) : (ListenerUtil.mutListener.listen(20551) ? (first_opening_index != last_closing_index) : (ListenerUtil.mutListener.listen(20550) ? (first_opening_index == last_closing_index) : (first_opening_index < last_closing_index))))))) : ((ListenerUtil.mutListener.listen(20549) ? ((ListenerUtil.mutListener.listen(20543) ? (first_opening_index >= -1) : (ListenerUtil.mutListener.listen(20542) ? (first_opening_index <= -1) : (ListenerUtil.mutListener.listen(20541) ? (first_opening_index > -1) : (ListenerUtil.mutListener.listen(20540) ? (first_opening_index < -1) : (ListenerUtil.mutListener.listen(20539) ? (first_opening_index == -1) : (first_opening_index != -1)))))) || (ListenerUtil.mutListener.listen(20548) ? (last_closing_index >= -1) : (ListenerUtil.mutListener.listen(20547) ? (last_closing_index <= -1) : (ListenerUtil.mutListener.listen(20546) ? (last_closing_index > -1) : (ListenerUtil.mutListener.listen(20545) ? (last_closing_index < -1) : (ListenerUtil.mutListener.listen(20544) ? (last_closing_index == -1) : (last_closing_index != -1))))))) : ((ListenerUtil.mutListener.listen(20543) ? (first_opening_index >= -1) : (ListenerUtil.mutListener.listen(20542) ? (first_opening_index <= -1) : (ListenerUtil.mutListener.listen(20541) ? (first_opening_index > -1) : (ListenerUtil.mutListener.listen(20540) ? (first_opening_index < -1) : (ListenerUtil.mutListener.listen(20539) ? (first_opening_index == -1) : (first_opening_index != -1)))))) && (ListenerUtil.mutListener.listen(20548) ? (last_closing_index >= -1) : (ListenerUtil.mutListener.listen(20547) ? (last_closing_index <= -1) : (ListenerUtil.mutListener.listen(20546) ? (last_closing_index > -1) : (ListenerUtil.mutListener.listen(20545) ? (last_closing_index < -1) : (ListenerUtil.mutListener.listen(20544) ? (last_closing_index == -1) : (last_closing_index != -1)))))))) && (ListenerUtil.mutListener.listen(20554) ? (first_opening_index >= last_closing_index) : (ListenerUtil.mutListener.listen(20553) ? (first_opening_index <= last_closing_index) : (ListenerUtil.mutListener.listen(20552) ? (first_opening_index > last_closing_index) : (ListenerUtil.mutListener.listen(20551) ? (first_opening_index != last_closing_index) : (ListenerUtil.mutListener.listen(20550) ? (first_opening_index == last_closing_index) : (first_opening_index < last_closing_index))))))))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
