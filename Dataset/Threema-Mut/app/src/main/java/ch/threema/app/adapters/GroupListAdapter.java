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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.CheckableConstraintLayout;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupListAdapter extends FilterableListAdapter {

    private final Context context;

    private List<GroupModel> values;

    private List<GroupModel> ovalues;

    private GroupListFilter groupListFilter;

    private final Bitmap defaultGroupImage;

    private final GroupService groupService;

    public GroupListAdapter(Context context, List<GroupModel> values, List<Integer> checkedItems, GroupService groupService) {
        super(context, R.layout.item_group_list, (List<Object>) (Object) values);
        this.context = context;
        if (!ListenerUtil.mutListener.listen(9097)) {
            this.values = values;
        }
        if (!ListenerUtil.mutListener.listen(9098)) {
            this.ovalues = values;
        }
        this.groupService = groupService;
        this.defaultGroupImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_group);
        if (!ListenerUtil.mutListener.listen(9106)) {
            if ((ListenerUtil.mutListener.listen(9104) ? (checkedItems != null || (ListenerUtil.mutListener.listen(9103) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9102) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9101) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9100) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9099) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))) : (checkedItems != null && (ListenerUtil.mutListener.listen(9103) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9102) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9101) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9100) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9099) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9105)) {
                    // restore checked items
                    this.checkedItems.addAll(checkedItems);
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckableConstraintLayout itemView = (CheckableConstraintLayout) convertView;
        GroupListHolder holder = new GroupListHolder();
        if (!ListenerUtil.mutListener.listen(9118)) {
            if (convertView == null) {
                // This a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (!ListenerUtil.mutListener.listen(9108)) {
                    itemView = (CheckableConstraintLayout) inflater.inflate(R.layout.item_group_list, parent, false);
                }
                TextView nameView = itemView.findViewById(R.id.name);
                TextView subjectView = itemView.findViewById(R.id.subject);
                ImageView roleView = itemView.findViewById(R.id.role);
                AvatarView avatarView = itemView.findViewById(R.id.avatar_view);
                if (!ListenerUtil.mutListener.listen(9109)) {
                    holder.nameView = nameView;
                }
                if (!ListenerUtil.mutListener.listen(9110)) {
                    holder.subjectView = subjectView;
                }
                if (!ListenerUtil.mutListener.listen(9111)) {
                    holder.roleView = roleView;
                }
                if (!ListenerUtil.mutListener.listen(9112)) {
                    holder.avatarView = avatarView;
                }
                if (!ListenerUtil.mutListener.listen(9113)) {
                    itemView.setTag(holder);
                }
                if (!ListenerUtil.mutListener.listen(9117)) {
                    itemView.setOnCheckedChangeListener(new CheckableConstraintLayout.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CheckableConstraintLayout checkableView, boolean isChecked) {
                            if (!ListenerUtil.mutListener.listen(9116)) {
                                if (isChecked) {
                                    if (!ListenerUtil.mutListener.listen(9115)) {
                                        checkedItems.add(((GroupListHolder) checkableView.getTag()).originalPosition);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9114)) {
                                        checkedItems.remove(((GroupListHolder) checkableView.getTag()).originalPosition);
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9107)) {
                    holder = (GroupListHolder) itemView.getTag();
                }
            }
        }
        final GroupModel groupModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(9119)) {
            holder.originalPosition = ovalues.indexOf(groupModel);
        }
        String filterString = null;
        if (!ListenerUtil.mutListener.listen(9121)) {
            if (groupListFilter != null) {
                if (!ListenerUtil.mutListener.listen(9120)) {
                    filterString = groupListFilter.getFilterString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9122)) {
            holder.nameView.setText(TextUtil.highlightMatches(context, NameUtil.getDisplayName(groupModel, this.groupService), filterString, false, false));
        }
        if (!ListenerUtil.mutListener.listen(9123)) {
            AdapterUtil.styleGroup(holder.nameView, groupService, groupModel);
        }
        if (!ListenerUtil.mutListener.listen(9124)) {
            holder.subjectView.setText(this.groupService.getMembersString(groupModel));
        }
        if (!ListenerUtil.mutListener.listen(9125)) {
            holder.roleView.setImageResource(groupService.isGroupOwner(groupModel) ? (groupService.isNotesGroup(groupModel) ? R.drawable.ic_spiral_bound_booklet_outline : R.drawable.ic_group_outline) : R.drawable.ic_group_filled);
        }
        if (!ListenerUtil.mutListener.listen(9126)) {
            // load avatars asynchronously
            AvatarListItemUtil.loadAvatar(position, groupModel, this.defaultGroupImage, this.groupService, holder);
        }
        if (!ListenerUtil.mutListener.listen(9127)) {
            ((ListView) parent).setItemChecked(position, checkedItems.contains(holder.originalPosition));
        }
        return itemView;
    }

    private static class GroupListHolder extends AvatarListItemHolder {

        public TextView nameView;

        private TextView subjectView;

        private ImageView roleView;

        private int originalPosition;
    }

    public class GroupListFilter extends Filter {

        String filterString = null;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(9143)) {
                if ((ListenerUtil.mutListener.listen(9133) ? (constraint == null && (ListenerUtil.mutListener.listen(9132) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9131) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9130) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9129) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9128) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(9132) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9131) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9130) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9129) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9128) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(9140)) {
                        // no filtering
                        filterString = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9141)) {
                        results.values = ovalues;
                    }
                    if (!ListenerUtil.mutListener.listen(9142)) {
                        results.count = ovalues.size();
                    }
                } else {
                    // perform filtering
                    List<GroupModel> nGroupList = new ArrayList<GroupModel>();
                    if (!ListenerUtil.mutListener.listen(9134)) {
                        filterString = constraint.toString();
                    }
                    if (!ListenerUtil.mutListener.listen(9137)) {
                        {
                            long _loopCounter76 = 0;
                            for (GroupModel groupModel : ovalues) {
                                ListenerUtil.loopListener.listen("_loopCounter76", ++_loopCounter76);
                                if (!ListenerUtil.mutListener.listen(9136)) {
                                    if (NameUtil.getDisplayName(groupModel, groupService).toUpperCase().contains(filterString.toUpperCase())) {
                                        if (!ListenerUtil.mutListener.listen(9135)) {
                                            nGroupList.add(groupModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9138)) {
                        results.values = nGroupList;
                    }
                    if (!ListenerUtil.mutListener.listen(9139)) {
                        results.count = nGroupList.size();
                    }
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(9144)) {
                values = (List<GroupModel>) results.values;
            }
            if (!ListenerUtil.mutListener.listen(9145)) {
                notifyDataSetChanged();
            }
        }

        public String getFilterString() {
            return filterString;
        }
    }

    @Override
    public Filter getFilter() {
        if (!ListenerUtil.mutListener.listen(9147)) {
            if (groupListFilter == null)
                if (!ListenerUtil.mutListener.listen(9146)) {
                    groupListFilter = new GroupListFilter();
                }
        }
        return groupListFilter;
    }

    @Override
    public int getCount() {
        return values != null ? values.size() : 0;
    }

    @Override
    public HashSet<GroupModel> getCheckedItems() {
        HashSet<GroupModel> groups = new HashSet<>();
        GroupModel groupModel;
        {
            long _loopCounter77 = 0;
            for (int position : checkedItems) {
                ListenerUtil.loopListener.listen("_loopCounter77", ++_loopCounter77);
                groupModel = ovalues.get(position);
                if (!ListenerUtil.mutListener.listen(9149)) {
                    if (groupModel != null) {
                        if (!ListenerUtil.mutListener.listen(9148)) {
                            groups.add(groupModel);
                        }
                    }
                }
            }
        }
        return groups;
    }

    @Override
    public GroupModel getClickedItem(View v) {
        return ovalues.get(((GroupListHolder) v.getTag()).originalPosition);
    }
}
