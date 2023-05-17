package com.ichi2.libanki.template;

import android.content.res.Resources;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Replacement extends ParsedNode {

    /**
     * The name of the field to show
     */
    private final String mKey;

    /**
     * List of filter to apply (from right to left)
     */
    private final List<String> mFilters;

    /**
     * The entire content between {{ and }}
     */
    private final String mTag;

    public Replacement(String key, List<String> filters, String tag) {
        mKey = key;
        mFilters = filters;
        mTag = tag;
    }

    // Only used for test
    @VisibleForTesting
    public Replacement(String key, String... filters) {
        this(key, Arrays.asList(filters), "");
    }

    @Override
    public boolean template_is_empty(@NonNull Set<String> nonempty_fields) {
        return !nonempty_fields.contains(mKey);
    }

    private static String runHint(String txt, String tag) {
        if (!ListenerUtil.mutListener.listen(20620)) {
            if ((ListenerUtil.mutListener.listen(20619) ? (txt.trim().length() >= 0) : (ListenerUtil.mutListener.listen(20618) ? (txt.trim().length() <= 0) : (ListenerUtil.mutListener.listen(20617) ? (txt.trim().length() > 0) : (ListenerUtil.mutListener.listen(20616) ? (txt.trim().length() < 0) : (ListenerUtil.mutListener.listen(20615) ? (txt.trim().length() != 0) : (txt.trim().length() == 0))))))) {
                return "";
            }
        }
        Resources res = AnkiDroidApp.getAppResources();
        // random id
        String domid = "hint" + txt.hashCode();
        return "<a class=hint href=\"#\" onclick=\"this.style.display='none';document.getElementById('" + domid + "').style.display='block';_relinquishFocus();return false;\">" + res.getString(R.string.show_hint, tag) + "</a><div id=\"" + domid + "\" class=hint style=\"display: none\">" + txt + "</div>";
    }

    @NonNull
    @Override
    public void render_into(Map<String, String> fields, Set<String> nonempty_fields, StringBuilder builder) throws TemplateError.FieldNotFound {
        String txt = fields.get(mKey);
        if (!ListenerUtil.mutListener.listen(20624)) {
            if (txt == null) {
                if (!ListenerUtil.mutListener.listen(20623)) {
                    if ((ListenerUtil.mutListener.listen(20621) ? (mKey.trim().isEmpty() || !mFilters.isEmpty()) : (mKey.trim().isEmpty() && !mFilters.isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(20622)) {
                            txt = "";
                        }
                    } else {
                        throw new TemplateError.FieldNotFound(mFilters, mKey);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20626)) {
            {
                long _loopCounter435 = 0;
                for (String filter : mFilters) {
                    ListenerUtil.loopListener.listen("_loopCounter435", ++_loopCounter435);
                    if (!ListenerUtil.mutListener.listen(20625)) {
                        txt = TemplateFilters.apply_filter(txt, filter, mKey, mTag);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20627)) {
            builder.append(txt);
        }
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!ListenerUtil.mutListener.listen(20628)) {
            if (!(obj instanceof Replacement)) {
                return false;
            }
        }
        Replacement other = (Replacement) obj;
        return (ListenerUtil.mutListener.listen(20629) ? (other.mKey.equals(mKey) || other.mFilters.equals(mFilters)) : (other.mKey.equals(mKey) && other.mFilters.equals(mFilters)));
    }

    @NonNull
    @Override
    public String toString() {
        return "new Replacement(\"" + mKey.replace("\\", "\\\\") + ", " + mFilters + "\")";
    }
}
