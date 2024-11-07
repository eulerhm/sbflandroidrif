package com.ichi2.libanki.template;

import java.util.List;
import java.util.Map;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NegatedConditional extends ParsedNode {

    private final String mKey;

    private final ParsedNode mChild;

    public NegatedConditional(String mKey, ParsedNode child) {
        this.mKey = mKey;
        this.mChild = child;
    }

    @Override
    public boolean template_is_empty(@NonNull Set<String> nonempty_fields) {
        return (ListenerUtil.mutListener.listen(20562) ? (nonempty_fields.contains(mKey) && mChild.template_is_empty(nonempty_fields)) : (nonempty_fields.contains(mKey) || mChild.template_is_empty(nonempty_fields)));
    }

    @NonNull
    @Override
    public void render_into(Map<String, String> fields, Set<String> nonempty_fields, StringBuilder builder) throws TemplateError {
        if (!ListenerUtil.mutListener.listen(20564)) {
            if (!nonempty_fields.contains(mKey)) {
                if (!ListenerUtil.mutListener.listen(20563)) {
                    mChild.render_into(fields, nonempty_fields, builder);
                }
            }
        }
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!ListenerUtil.mutListener.listen(20565)) {
            if (!(obj instanceof NegatedConditional)) {
                return false;
            }
        }
        NegatedConditional other = (NegatedConditional) obj;
        return (ListenerUtil.mutListener.listen(20566) ? (other.mKey.equals(mKey) || other.mChild.equals(mChild)) : (other.mKey.equals(mKey) && other.mChild.equals(mChild)));
    }

    @NonNull
    @Override
    public String toString() {
        return "new NegatedConditional(\"" + mKey.replace("\\", "\\\\") + "," + mChild + "\")";
    }
}
