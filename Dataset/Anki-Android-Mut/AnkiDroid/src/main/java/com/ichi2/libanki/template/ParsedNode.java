package com.ichi2.libanki.template;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.R;
import com.ichi2.libanki.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import okhttp3.internal.Util;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents a template, allow to check in linear time which card is empty/render card.
 */
public abstract class ParsedNode {

    public static final String TEMPLATE_ERROR_LINK = "https://anki.tenderapp.com/kb/problems/card-template-has-a-problem";

    public static final String TEMPLATE_BLANK_LINK = "https://anki.tenderapp.com/kb/card-appearance/the-front-of-this-card-is-blank";

    public static final String TEMPLATE_BLANK_CLOZE_LINK = "https://anki.tenderapp.com/kb/problems/no-cloze-found-on-card";

    /**
     * @param nonempty_fields A set of fields that are not empty
     * @return Whether the card is empty. I.e. no non-empty fields are shown
     */
    public abstract boolean template_is_empty(Set<String> nonempty_fields);

    // Used only fot testing
    @VisibleForTesting
    public boolean template_is_empty(String... nonempty_fields) {
        return template_is_empty(new HashSet<>(Arrays.asList(nonempty_fields)));
    }

    public abstract void render_into(Map<String, String> fields, Set<String> nonempty_fields, StringBuilder builder) throws TemplateError;

    /**
     * Associate to each template its node, or the error it generates
     */
    private static WeakHashMap<String, Pair<ParsedNode, TemplateError>> parse_inner_cache = new WeakHashMap<>();

    /**
     * @param template A question or answer template
     * @return A tree representing the template.
     * @throws TemplateError if the template is not valid
     */
    @NonNull
    public static ParsedNode parse_inner(@NonNull String template) throws TemplateError {
        if (!ListenerUtil.mutListener.listen(20568)) {
            if (!parse_inner_cache.containsKey(template)) {
                Pair<ParsedNode, TemplateError> res;
                try {
                    ParsedNode node = parse_inner(new Tokenizer(template));
                    res = new Pair<>(node, null);
                } catch (TemplateError er) {
                    res = new Pair<>(null, er);
                }
                if (!ListenerUtil.mutListener.listen(20567)) {
                    parse_inner_cache.put(template, res);
                }
            }
        }
        Pair<ParsedNode, TemplateError> res = parse_inner_cache.get(template);
        if (res.first != null) {
            return res.first;
        }
        throw res.second;
    }

    /**
     * @param tokens An iterator returning a list of token obtained from a template
     * @return A tree representing the template
     * @throws TemplateError Any reason meaning the data is not valid as a template.
     */
    @NonNull
    protected static ParsedNode parse_inner(@NonNull Iterator<Tokenizer.Token> tokens) throws TemplateError {
        return parse_inner(tokens, null);
    }

    /**
     * @param tokens An iterator returning a list of token obtained from a template
     * @param open_tag The last opened tag that is not yet closed, or null
     * @return A tree representing the template, or nulll if no text can be generated.
     * @throws TemplateError Any reason meaning the data is not valid as a template.
     */
    @Nullable
    private static ParsedNode parse_inner(@NonNull Iterator<Tokenizer.Token> tokens, @Nullable String open_tag) throws TemplateError {
        List<ParsedNode> nodes = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(20595)) {
            {
                long _loopCounter431 = 0;
                while (tokens.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter431", ++_loopCounter431);
                    Tokenizer.Token token = tokens.next();
                    if (!ListenerUtil.mutListener.listen(20594)) {
                        switch(token.getKind()) {
                            case Text:
                                {
                                    if (!ListenerUtil.mutListener.listen(20569)) {
                                        nodes.add(new Text(token.getText()));
                                    }
                                    break;
                                }
                            case Replacement:
                                {
                                    String[] it = token.getText().split(":", -1);
                                    String key = it[(ListenerUtil.mutListener.listen(20573) ? (it.length % 1) : (ListenerUtil.mutListener.listen(20572) ? (it.length / 1) : (ListenerUtil.mutListener.listen(20571) ? (it.length * 1) : (ListenerUtil.mutListener.listen(20570) ? (it.length + 1) : (it.length - 1)))))];
                                    List<String> filters = new ArrayList<>((ListenerUtil.mutListener.listen(20577) ? (it.length % 1) : (ListenerUtil.mutListener.listen(20576) ? (it.length / 1) : (ListenerUtil.mutListener.listen(20575) ? (it.length * 1) : (ListenerUtil.mutListener.listen(20574) ? (it.length + 1) : (it.length - 1))))));
                                    if (!ListenerUtil.mutListener.listen(20588)) {
                                        {
                                            long _loopCounter430 = 0;
                                            for (int i = (ListenerUtil.mutListener.listen(20587) ? (it.length % 2) : (ListenerUtil.mutListener.listen(20586) ? (it.length / 2) : (ListenerUtil.mutListener.listen(20585) ? (it.length * 2) : (ListenerUtil.mutListener.listen(20584) ? (it.length + 2) : (it.length - 2))))); (ListenerUtil.mutListener.listen(20583) ? (i <= 0) : (ListenerUtil.mutListener.listen(20582) ? (i > 0) : (ListenerUtil.mutListener.listen(20581) ? (i < 0) : (ListenerUtil.mutListener.listen(20580) ? (i != 0) : (ListenerUtil.mutListener.listen(20579) ? (i == 0) : (i >= 0)))))); i--) {
                                                ListenerUtil.loopListener.listen("_loopCounter430", ++_loopCounter430);
                                                if (!ListenerUtil.mutListener.listen(20578)) {
                                                    filters.add(it[i]);
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(20589)) {
                                        nodes.add(new Replacement(key, filters, token.getText()));
                                    }
                                    break;
                                }
                            case OpenConditional:
                                {
                                    String tag = token.getText();
                                    if (!ListenerUtil.mutListener.listen(20590)) {
                                        nodes.add(new Conditional(tag, parse_inner(tokens, tag)));
                                    }
                                    break;
                                }
                            case OpenNegated:
                                {
                                    String tag = token.getText();
                                    if (!ListenerUtil.mutListener.listen(20591)) {
                                        nodes.add(new NegatedConditional(tag, parse_inner(tokens, tag)));
                                    }
                                    break;
                                }
                            case CloseConditional:
                                {
                                    String tag = token.getText();
                                    if (!ListenerUtil.mutListener.listen(20592)) {
                                        if (open_tag == null) {
                                            throw new TemplateError.ConditionalNotOpen(tag);
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(20593)) {
                                        if (!tag.equals(open_tag)) {
                                            // open_tag may be null, tag is not
                                            throw new TemplateError.WrongConditionalClosed(tag, open_tag);
                                        } else {
                                            return ParsedNodes.create(nodes);
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20596)) {
            if (open_tag != null) {
                throw new TemplateError.ConditionalNotClosed(open_tag);
            }
        }
        return ParsedNodes.create(nodes);
    }

    @NonNull
    public String render(Map<String, String> fields, boolean question, Context context) {
        try {
            StringBuilder builder = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(20597)) {
                render_into(fields, Utils.nonEmptyFields(fields), builder);
            }
            return builder.toString();
        } catch (TemplateError er) {
            String side = (question) ? context.getString(R.string.card_template_editor_front) : context.getString(R.string.card_template_editor_back);
            String explanation = context.getString(R.string.has_a_problem, side, er.message(context));
            String more_explanation = "<a href=\"" + TEMPLATE_ERROR_LINK + "\">" + context.getString(R.string.more_information) + "</a>";
            return explanation + "<br/>" + more_explanation;
        }
    }
}
