package com.ichi2.libanki.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ParsedNodes extends ParsedNode {

    private final List<ParsedNode> mChildren;

    @VisibleForTesting
    public ParsedNodes(List<ParsedNode> nodes) {
        this.mChildren = nodes;
    }

    // Only used for testing
    @VisibleForTesting
    public ParsedNodes(ParsedNode... nodes) {
        this.mChildren = new ArrayList(Arrays.asList(nodes));
    }

    /**
     * @param nodes A list of nodes to put in a tree
     * @return The list of node, as compactly as possible.
     */
    @NonNull
    public static ParsedNode create(List<ParsedNode> nodes) {
        if ((ListenerUtil.mutListener.listen(20602) ? (nodes.size() >= 0) : (ListenerUtil.mutListener.listen(20601) ? (nodes.size() <= 0) : (ListenerUtil.mutListener.listen(20600) ? (nodes.size() > 0) : (ListenerUtil.mutListener.listen(20599) ? (nodes.size() < 0) : (ListenerUtil.mutListener.listen(20598) ? (nodes.size() != 0) : (nodes.size() == 0))))))) {
            return new EmptyNode();
        } else if ((ListenerUtil.mutListener.listen(20607) ? (nodes.size() >= 1) : (ListenerUtil.mutListener.listen(20606) ? (nodes.size() <= 1) : (ListenerUtil.mutListener.listen(20605) ? (nodes.size() > 1) : (ListenerUtil.mutListener.listen(20604) ? (nodes.size() < 1) : (ListenerUtil.mutListener.listen(20603) ? (nodes.size() != 1) : (nodes.size() == 1))))))) {
            return nodes.get(0);
        } else {
            return new ParsedNodes(nodes);
        }
    }

    @Override
    public boolean template_is_empty(@NonNull Set<String> nonempty_fields) {
        if (!ListenerUtil.mutListener.listen(20609)) {
            {
                long _loopCounter432 = 0;
                for (ParsedNode child : mChildren) {
                    ListenerUtil.loopListener.listen("_loopCounter432", ++_loopCounter432);
                    if (!ListenerUtil.mutListener.listen(20608)) {
                        if (!child.template_is_empty(nonempty_fields)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @NonNull
    public void render_into(Map<String, String> fields, Set<String> nonempty_fields, StringBuilder builder) throws TemplateError {
        if (!ListenerUtil.mutListener.listen(20611)) {
            {
                long _loopCounter433 = 0;
                for (ParsedNode child : mChildren) {
                    ListenerUtil.loopListener.listen("_loopCounter433", ++_loopCounter433);
                    if (!ListenerUtil.mutListener.listen(20610)) {
                        child.render_into(fields, nonempty_fields, builder);
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!ListenerUtil.mutListener.listen(20612)) {
            if (!(obj instanceof ParsedNodes)) {
                return false;
            }
        }
        ParsedNodes other = (ParsedNodes) obj;
        return mChildren.equals(other.mChildren);
    }

    @NonNull
    @Override
    public String toString() {
        String t = "new ParsedNodes(Arrays.asList(";
        if (!ListenerUtil.mutListener.listen(20614)) {
            {
                long _loopCounter434 = 0;
                for (ParsedNode child : mChildren) {
                    ListenerUtil.loopListener.listen("_loopCounter434", ++_loopCounter434);
                    if (!ListenerUtil.mutListener.listen(20613)) {
                        t += child;
                    }
                }
            }
        }
        return t + "))";
    }
}
