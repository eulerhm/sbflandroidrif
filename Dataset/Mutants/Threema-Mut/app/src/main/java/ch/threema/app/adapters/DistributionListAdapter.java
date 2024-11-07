/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.CheckableConstraintLayout;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.NameUtil;
import ch.threema.storage.models.DistributionListModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListAdapter extends FilterableListAdapter {

    private final Context context;

    private List<DistributionListModel> values;

    private List<DistributionListModel> ovalues;

    private DistributionListFilter groupListFilter;

    private final Bitmap defaultAvatar;

    private final DistributionListService distributionListService;

    public DistributionListAdapter(Context context, List<DistributionListModel> values, List<Integer> checkedItems, DistributionListService distributionListService) {
        super(context, R.layout.item_distribution_list, (List<Object>) (Object) values);
        this.context = context;
        if (!ListenerUtil.mutListener.listen(9000)) {
            this.values = values;
        }
        if (!ListenerUtil.mutListener.listen(9001)) {
            this.ovalues = values;
        }
        this.distributionListService = distributionListService;
        this.defaultAvatar = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_distribution_list);
        if (!ListenerUtil.mutListener.listen(9009)) {
            if ((ListenerUtil.mutListener.listen(9007) ? (checkedItems != null || (ListenerUtil.mutListener.listen(9006) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9005) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9004) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9003) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9002) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))) : (checkedItems != null && (ListenerUtil.mutListener.listen(9006) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9005) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9004) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9003) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9002) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9008)) {
                    // restore checked items
                    this.checkedItems.addAll(checkedItems);
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckableConstraintLayout itemView = (CheckableConstraintLayout) convertView;
        DistributionListHolder holder = new DistributionListHolder();
        if (!ListenerUtil.mutListener.listen(9020)) {
            if (convertView == null) {
                // This a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (!ListenerUtil.mutListener.listen(9011)) {
                    itemView = (CheckableConstraintLayout) inflater.inflate(R.layout.item_distribution_list, parent, false);
                }
                TextView nameView = itemView.findViewById(R.id.name);
                TextView subjectView = itemView.findViewById(R.id.subject);
                AvatarView avatarView = itemView.findViewById(R.id.avatar_view);
                if (!ListenerUtil.mutListener.listen(9012)) {
                    holder.nameView = nameView;
                }
                if (!ListenerUtil.mutListener.listen(9013)) {
                    holder.subjectView = subjectView;
                }
                if (!ListenerUtil.mutListener.listen(9014)) {
                    holder.avatarView = avatarView;
                }
                if (!ListenerUtil.mutListener.listen(9015)) {
                    itemView.setTag(holder);
                }
                if (!ListenerUtil.mutListener.listen(9019)) {
                    itemView.setOnCheckedChangeListener(new CheckableConstraintLayout.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CheckableConstraintLayout checkableView, boolean isChecked) {
                            if (!ListenerUtil.mutListener.listen(9018)) {
                                if (isChecked) {
                                    if (!ListenerUtil.mutListener.listen(9017)) {
                                        checkedItems.add(((DistributionListHolder) checkableView.getTag()).originalPosition);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9016)) {
                                        checkedItems.remove(((DistributionListHolder) checkableView.getTag()).originalPosition);
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9010)) {
                    holder = (DistributionListHolder) itemView.getTag();
                }
            }
        }
        final DistributionListModel distributionListModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(9021)) {
            holder.originalPosition = ovalues.indexOf(distributionListModel);
        }
        String filterString = null;
        if (!ListenerUtil.mutListener.listen(9023)) {
            if (groupListFilter != null) {
                if (!ListenerUtil.mutListener.listen(9022)) {
                    filterString = groupListFilter.getFilterString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9024)) {
            holder.nameView.setText(highlightMatches(NameUtil.getDisplayName(distributionListModel, this.distributionListService), filterString));
        }
        if (!ListenerUtil.mutListener.listen(9025)) {
            holder.subjectView.setText(this.distributionListService.getMembersString(distributionListModel));
        }
        if (!ListenerUtil.mutListener.listen(9026)) {
            // load avatars asynchronously
            AvatarListItemUtil.loadAvatar(position, distributionListModel, this.defaultAvatar, this.distributionListService, holder);
        }
        if (!ListenerUtil.mutListener.listen(9027)) {
            ((ListView) parent).setItemChecked(position, checkedItems.contains(holder.originalPosition));
        }
        return itemView;
    }

    private static class DistributionListHolder extends AvatarListItemHolder {

        public TextView nameView;

        public TextView subjectView;

        public int originalPosition;
    }

    public class DistributionListFilter extends Filter {

        String filterString = null;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(9040)) {
                if ((ListenerUtil.mutListener.listen(9033) ? (constraint == null && (ListenerUtil.mutListener.listen(9032) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9031) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9030) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9029) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9028) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(9032) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9031) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9030) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9029) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9028) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(9037)) {
                        // no filtering
                        filterString = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9038)) {
                        results.values = ovalues;
                    }
                    if (!ListenerUtil.mutListener.listen(9039)) {
                        results.count = ovalues.size();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9034)) {
                        // perform filtering
                        filterString = constraint.toString();
                    }
                    Collection<DistributionListModel> distributionListModelList = Functional.filter(ovalues, new IPredicateNonNull<DistributionListModel>() {

                        @Override
                        public boolean apply(@NonNull DistributionListModel distributionListModel) {
                            return (NameUtil.getDisplayName(distributionListModel, distributionListService).toUpperCase().contains(filterString.toUpperCase()));
                        }
                    });
                    if (!ListenerUtil.mutListener.listen(9035)) {
                        results.values = distributionListModelList;
                    }
                    if (!ListenerUtil.mutListener.listen(9036)) {
                        results.count = distributionListModelList.size();
                    }
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(9041)) {
                values = (List<DistributionListModel>) results.values;
            }
            if (!ListenerUtil.mutListener.listen(9042)) {
                notifyDataSetChanged();
            }
        }

        public String getFilterString() {
            return filterString;
        }
    }

    @Override
    public Filter getFilter() {
        if (!ListenerUtil.mutListener.listen(9044)) {
            if (groupListFilter == null)
                if (!ListenerUtil.mutListener.listen(9043)) {
                    groupListFilter = new DistributionListFilter();
                }
        }
        return groupListFilter;
    }

    @Override
    public int getCount() {
        return values != null ? values.size() : 0;
    }

    @Override
    public HashSet<DistributionListModel> getCheckedItems() {
        HashSet<DistributionListModel> distributionLists = new HashSet<>();
        DistributionListModel distributionListModel;
        {
            long _loopCounter75 = 0;
            for (int position : checkedItems) {
                ListenerUtil.loopListener.listen("_loopCounter75", ++_loopCounter75);
                distributionListModel = ovalues.get(position);
                if (!ListenerUtil.mutListener.listen(9046)) {
                    if (distributionListModel != null) {
                        if (!ListenerUtil.mutListener.listen(9045)) {
                            distributionLists.add(distributionListModel);
                        }
                    }
                }
            }
        }
        return distributionLists;
    }

    @Override
    public DistributionListModel getClickedItem(View v) {
        return ovalues.get(((DistributionListHolder) v.getTag()).originalPosition);
    }
}
