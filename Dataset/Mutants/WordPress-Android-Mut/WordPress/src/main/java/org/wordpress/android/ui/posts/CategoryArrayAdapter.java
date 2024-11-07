package org.wordpress.android.ui.posts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import org.apache.commons.text.StringEscapeUtils;
import org.wordpress.android.R;
import org.wordpress.android.models.CategoryNode;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CategoryArrayAdapter extends ArrayAdapter<CategoryNode> {

    private int mResourceId;

    CategoryArrayAdapter(Context context, int resource, List<CategoryNode> objects) {
        super(context, resource, objects);
        if (!ListenerUtil.mutListener.listen(11288)) {
            mResourceId = resource;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        if (!ListenerUtil.mutListener.listen(11291)) {
            if (rowView == null) {
                if (!ListenerUtil.mutListener.listen(11289)) {
                    rowView = inflater.inflate(mResourceId, parent, false);
                }
                ViewHolder viewHolder = new ViewHolder(rowView);
                if (!ListenerUtil.mutListener.listen(11290)) {
                    rowView.setTag(viewHolder);
                }
            }
        }
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        CategoryNode node = getItem(position);
        if (!ListenerUtil.mutListener.listen(11298)) {
            if (node != null) {
                int verticalPadding = rowView.getResources().getDimensionPixelOffset(R.dimen.margin_large);
                int horizontalPadding = rowView.getResources().getDimensionPixelOffset(R.dimen.margin_extra_large);
                if (!ListenerUtil.mutListener.listen(11292)) {
                    viewHolder.mCategoryRowText.setText(StringEscapeUtils.unescapeHtml4(node.getName()));
                }
                if (!ListenerUtil.mutListener.listen(11297)) {
                    ViewCompat.setPaddingRelative(viewHolder.mCategoryRowText, (ListenerUtil.mutListener.listen(11296) ? (horizontalPadding % node.getLevel()) : (ListenerUtil.mutListener.listen(11295) ? (horizontalPadding / node.getLevel()) : (ListenerUtil.mutListener.listen(11294) ? (horizontalPadding - node.getLevel()) : (ListenerUtil.mutListener.listen(11293) ? (horizontalPadding + node.getLevel()) : (horizontalPadding * node.getLevel()))))), verticalPadding, horizontalPadding, verticalPadding);
                }
            }
        }
        return rowView;
    }

    private static class ViewHolder {

        private final TextView mCategoryRowText;

        private ViewHolder(View view) {
            this.mCategoryRowText = view.findViewById(R.id.categoryRowText);
        }
    }
}
