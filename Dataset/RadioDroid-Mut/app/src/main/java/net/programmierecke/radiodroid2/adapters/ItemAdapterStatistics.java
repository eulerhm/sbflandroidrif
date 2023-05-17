package net.programmierecke.radiodroid2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import net.programmierecke.radiodroid2.data.DataStatistics;
import net.programmierecke.radiodroid2.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ItemAdapterStatistics extends ArrayAdapter<DataStatistics> {

    private Context context;

    private int resourceId;

    public ItemAdapterStatistics(Context context, int resourceId) {
        super(context, resourceId);
        if (!ListenerUtil.mutListener.listen(16)) {
            this.resourceId = resourceId;
        }
        if (!ListenerUtil.mutListener.listen(17)) {
            this.context = context;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataStatistics aData = getItem(position);
        View v = convertView;
        if (!ListenerUtil.mutListener.listen(19)) {
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (!ListenerUtil.mutListener.listen(18)) {
                    v = vi.inflate(resourceId, null);
                }
            }
        }
        TextView aTextViewTop = (TextView) v.findViewById(R.id.stats_name);
        TextView aTextViewBottom = (TextView) v.findViewById(R.id.stats_value);
        if (!ListenerUtil.mutListener.listen(21)) {
            if (aTextViewTop != null) {
                if (!ListenerUtil.mutListener.listen(20)) {
                    aTextViewTop.setText("" + aData.Name);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23)) {
            if (aTextViewBottom != null) {
                if (!ListenerUtil.mutListener.listen(22)) {
                    aTextViewBottom.setText("" + aData.Value);
                }
            }
        }
        return v;
    }
}
