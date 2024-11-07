package org.wordpress.android.ui.prefs;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * RecyclerView.Adapter for selecting multiple list items with simple layout (TextView + divider).
 */
public class MultiSelectRecyclerViewAdapter extends RecyclerView.Adapter<MultiSelectRecyclerViewAdapter.ItemHolder> {

    private final List<String> mItems;

    private final SparseBooleanArray mItemsSelected;

    MultiSelectRecyclerViewAdapter(List<String> items) {
        this.mItems = items;
        this.mItemsSelected = new SparseBooleanArray();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private final View mContainer;

        private final TextView mText;

        ItemHolder(View view) {
            super(view);
            mContainer = view.findViewById(R.id.container);
            mText = view.findViewById(R.id.text);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        String item = getItem(holder.getAdapterPosition());
        if (!ListenerUtil.mutListener.listen(14854)) {
            holder.mText.setText(item);
        }
        if (!ListenerUtil.mutListener.listen(14855)) {
            holder.mContainer.setSelected(isItemSelected(position));
        }
    }

    @NotNull
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int type) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wp_simple_list_item_1, parent, false));
    }

    public String getItem(int position) {
        return mItems.get(position);
    }

    SparseBooleanArray getItemsSelected() {
        return mItemsSelected;
    }

    private boolean isItemSelected(int position) {
        String item = getItem(position);
        return (ListenerUtil.mutListener.listen(14856) ? (item != null || mItemsSelected.get(position)) : (item != null && mItemsSelected.get(position)));
    }

    void removeItemsSelected() {
        if (!ListenerUtil.mutListener.listen(14857)) {
            mItemsSelected.clear();
        }
        if (!ListenerUtil.mutListener.listen(14858)) {
            notifyDataSetChanged();
        }
    }

    void setItemSelected(int position) {
        if (!ListenerUtil.mutListener.listen(14860)) {
            if (!mItemsSelected.get(position)) {
                if (!ListenerUtil.mutListener.listen(14859)) {
                    mItemsSelected.put(position, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14861)) {
            notifyItemChanged(position);
        }
    }

    void toggleItemSelected(int position) {
        if (!ListenerUtil.mutListener.listen(14864)) {
            if (!mItemsSelected.get(position)) {
                if (!ListenerUtil.mutListener.listen(14863)) {
                    mItemsSelected.put(position, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14862)) {
                    mItemsSelected.delete(position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14865)) {
            notifyItemChanged(position);
        }
    }
}
