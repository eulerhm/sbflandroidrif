package org.wordpress.android.ui.posts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import org.apache.commons.text.StringEscapeUtils;
import org.wordpress.android.R;
import org.wordpress.android.models.CategoryNode;
import org.wordpress.android.util.DisplayUtils;
import java.util.List;
import static org.wordpress.android.WordPress.getContext;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ParentCategorySpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private int mResourceId;

    private List<CategoryNode> mObjects;

    private Context mContext;

    public int getCount() {
        return mObjects.size();
    }

    public CategoryNode getItem(int position) {
        return mObjects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public ParentCategorySpinnerAdapter(Context context, int resource, List<CategoryNode> objects) {
        super();
        if (!ListenerUtil.mutListener.listen(12721)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(12722)) {
            mObjects = objects;
        }
        if (!ListenerUtil.mutListener.listen(12723)) {
            mResourceId = resource;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(mResourceId, parent, false);
        TextView textView = rowView.findViewById(R.id.categoryRowText);
        if (!ListenerUtil.mutListener.listen(12724)) {
            textView.setText(StringEscapeUtils.unescapeHtml4(getItem(position).getName()));
        }
        CategoryNode node = getItem(position);
        if (!ListenerUtil.mutListener.listen(12731)) {
            if (node != null) {
                if (!ListenerUtil.mutListener.listen(12725)) {
                    textView.setText(StringEscapeUtils.unescapeHtml4(node.getName()));
                }
                if (!ListenerUtil.mutListener.listen(12730)) {
                    ViewCompat.setPaddingRelative(textView, (ListenerUtil.mutListener.listen(12729) ? (DisplayUtils.dpToPx(getContext(), 16) % (node.getLevel() + 1)) : (ListenerUtil.mutListener.listen(12728) ? (DisplayUtils.dpToPx(getContext(), 16) / (node.getLevel() + 1)) : (ListenerUtil.mutListener.listen(12727) ? (DisplayUtils.dpToPx(getContext(), 16) - (node.getLevel() + 1)) : (ListenerUtil.mutListener.listen(12726) ? (DisplayUtils.dpToPx(getContext(), 16) + (node.getLevel() + 1)) : (DisplayUtils.dpToPx(getContext(), 16) * (node.getLevel() + 1)))))), 0, DisplayUtils.dpToPx(getContext(), 16), 0);
                }
            }
        }
        return rowView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        if (!ListenerUtil.mutListener.listen(12734)) {
            if (rowView == null) {
                if (!ListenerUtil.mutListener.listen(12732)) {
                    rowView = inflater.inflate(mResourceId, parent, false);
                }
                ViewHolder viewHolder = new ViewHolder(rowView);
                if (!ListenerUtil.mutListener.listen(12733)) {
                    rowView.setTag(viewHolder);
                }
            }
        }
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        if (!ListenerUtil.mutListener.listen(12735)) {
            viewHolder.mCategoryRowText.setText(StringEscapeUtils.unescapeHtml4(getItem(position).getName()));
        }
        return rowView;
    }

    private static class ViewHolder {

        private final TextView mCategoryRowText;

        private ViewHolder(View view) {
            this.mCategoryRowText = view.findViewById(R.id.categoryRowText);
        }
    }

    public void replaceItems(List<CategoryNode> categoryNodes) {
        if (!ListenerUtil.mutListener.listen(12739)) {
            if (categoryNodes.size() != mObjects.size()) {
                if (!ListenerUtil.mutListener.listen(12736)) {
                    this.mObjects.clear();
                }
                if (!ListenerUtil.mutListener.listen(12737)) {
                    this.mObjects.addAll(categoryNodes);
                }
                if (!ListenerUtil.mutListener.listen(12738)) {
                    notifyDataSetChanged();
                }
            }
        }
    }
}
