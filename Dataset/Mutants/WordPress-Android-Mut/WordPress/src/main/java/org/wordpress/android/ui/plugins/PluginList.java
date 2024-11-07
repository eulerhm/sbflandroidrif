package org.wordpress.android.ui.plugins;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class PluginList extends ArrayList<ImmutablePluginModel> {

    long getItemId(int position) {
        ImmutablePluginModel plugin = (ImmutablePluginModel) getItem(position);
        if (!ListenerUtil.mutListener.listen(11031)) {
            if ((ListenerUtil.mutListener.listen(11030) ? (plugin == null && TextUtils.isEmpty(plugin.getSlug())) : (plugin == null || TextUtils.isEmpty(plugin.getSlug())))) {
                // This should never happen
                return -1;
            }
        }
        return plugin.getSlug().hashCode();
    }

    @Nullable
    Object getItem(int position) {
        if (!ListenerUtil.mutListener.listen(11043)) {
            if ((ListenerUtil.mutListener.listen(11042) ? ((ListenerUtil.mutListener.listen(11036) ? (position <= 0) : (ListenerUtil.mutListener.listen(11035) ? (position > 0) : (ListenerUtil.mutListener.listen(11034) ? (position < 0) : (ListenerUtil.mutListener.listen(11033) ? (position != 0) : (ListenerUtil.mutListener.listen(11032) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(11041) ? (position >= size()) : (ListenerUtil.mutListener.listen(11040) ? (position <= size()) : (ListenerUtil.mutListener.listen(11039) ? (position > size()) : (ListenerUtil.mutListener.listen(11038) ? (position != size()) : (ListenerUtil.mutListener.listen(11037) ? (position == size()) : (position < size()))))))) : ((ListenerUtil.mutListener.listen(11036) ? (position <= 0) : (ListenerUtil.mutListener.listen(11035) ? (position > 0) : (ListenerUtil.mutListener.listen(11034) ? (position < 0) : (ListenerUtil.mutListener.listen(11033) ? (position != 0) : (ListenerUtil.mutListener.listen(11032) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(11041) ? (position >= size()) : (ListenerUtil.mutListener.listen(11040) ? (position <= size()) : (ListenerUtil.mutListener.listen(11039) ? (position > size()) : (ListenerUtil.mutListener.listen(11038) ? (position != size()) : (ListenerUtil.mutListener.listen(11037) ? (position == size()) : (position < size()))))))))) {
                return get(position);
            }
        }
        return null;
    }
}
