package net.programmierecke.radiodroid2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import net.programmierecke.radiodroid2.data.DataCategory;
import net.programmierecke.radiodroid2.R;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ItemAdapterCategory extends RecyclerView.Adapter<ItemAdapterCategory.CategoryViewHolder> {

    public interface CategoryClickListener {

        void onCategoryClick(DataCategory category);
    }

    private List<DataCategory> categoriesList;

    private int resourceId;

    private CategoryClickListener categoryClickListener;

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;

        TextView textViewCount;

        ImageView iconView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(0)) {
                textViewName = (TextView) itemView.findViewById(R.id.textViewTop);
            }
            if (!ListenerUtil.mutListener.listen(1)) {
                textViewCount = (TextView) itemView.findViewById(R.id.textViewBottom);
            }
            if (!ListenerUtil.mutListener.listen(2)) {
                iconView = (ImageView) itemView.findViewById(R.id.iconCategoryViewIcon);
            }
            if (!ListenerUtil.mutListener.listen(3)) {
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if (!ListenerUtil.mutListener.listen(5)) {
                if (categoryClickListener != null) {
                    if (!ListenerUtil.mutListener.listen(4)) {
                        categoryClickListener.onCategoryClick(categoriesList.get(getAdapterPosition()));
                    }
                }
            }
        }
    }

    public ItemAdapterCategory(int resourceId) {
        if (!ListenerUtil.mutListener.listen(6)) {
            this.resourceId = resourceId;
        }
    }

    public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
        if (!ListenerUtil.mutListener.listen(7)) {
            this.categoryClickListener = categoryClickListener;
        }
    }

    public void updateList(List<DataCategory> categoriesList) {
        if (!ListenerUtil.mutListener.listen(8)) {
            this.categoriesList = categoriesList;
        }
        if (!ListenerUtil.mutListener.listen(9)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(resourceId, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final DataCategory category = categoriesList.get(position);
        if (!ListenerUtil.mutListener.listen(12)) {
            if (category.Label != null) {
                if (!ListenerUtil.mutListener.listen(11)) {
                    holder.textViewName.setText(category.Label);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10)) {
                    holder.textViewName.setText(category.Name);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14)) {
            if (category.Icon != null) {
                if (!ListenerUtil.mutListener.listen(13)) {
                    holder.iconView.setImageDrawable(category.Icon);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15)) {
            holder.textViewCount.setText(String.valueOf(category.UsedCount));
        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
