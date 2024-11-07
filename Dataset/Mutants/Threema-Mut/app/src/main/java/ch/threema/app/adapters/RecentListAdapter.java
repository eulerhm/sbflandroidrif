/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.CheckableConstraintLayout;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecentListAdapter extends FilterableListAdapter {

    private final Context context;

    private List<ConversationModel> values;

    private List<ConversationModel> ovalues;

    private RecentListFilter recentListFilter;

    private final Bitmap defaultContactImage, defaultGroupImage;

    private final Bitmap defaultDistributionListImage;

    private final ContactService contactService;

    private final GroupService groupService;

    private final DistributionListService distributionListService;

    public RecentListAdapter(Context context, final List<ConversationModel> values, final List<Integer> checkedItems, ContactService contactService, GroupService groupService, DistributionListService distributionListService) {
        super(context, R.layout.item_user_list, (List<Object>) (Object) values);
        this.context = context;
        if (!ListenerUtil.mutListener.listen(9494)) {
            this.values = values;
        }
        if (!ListenerUtil.mutListener.listen(9495)) {
            this.ovalues = values;
        }
        this.contactService = contactService;
        this.groupService = groupService;
        this.distributionListService = distributionListService;
        this.defaultContactImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_contact);
        this.defaultGroupImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_group);
        this.defaultDistributionListImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_distribution_list);
        if (!ListenerUtil.mutListener.listen(9503)) {
            if ((ListenerUtil.mutListener.listen(9501) ? (checkedItems != null || (ListenerUtil.mutListener.listen(9500) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9499) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9498) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9497) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9496) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))) : (checkedItems != null && (ListenerUtil.mutListener.listen(9500) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9499) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9498) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9497) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9496) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9502)) {
                    // restore checked items
                    this.checkedItems.addAll(checkedItems);
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckableConstraintLayout itemView = (CheckableConstraintLayout) convertView;
        RecentListHolder holder = new RecentListHolder();
        if (!ListenerUtil.mutListener.listen(9515)) {
            if (convertView == null) {
                // This a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (!ListenerUtil.mutListener.listen(9505)) {
                    itemView = (CheckableConstraintLayout) inflater.inflate(R.layout.item_recent_list, parent, false);
                }
                TextView nameView = itemView.findViewById(R.id.name);
                TextView subjectView = itemView.findViewById(R.id.subject);
                ImageView groupView = itemView.findViewById(R.id.group);
                AvatarView avatarView = itemView.findViewById(R.id.avatar_view);
                if (!ListenerUtil.mutListener.listen(9506)) {
                    holder.nameView = nameView;
                }
                if (!ListenerUtil.mutListener.listen(9507)) {
                    holder.subjectView = subjectView;
                }
                if (!ListenerUtil.mutListener.listen(9508)) {
                    holder.groupView = groupView;
                }
                if (!ListenerUtil.mutListener.listen(9509)) {
                    holder.avatarView = avatarView;
                }
                if (!ListenerUtil.mutListener.listen(9510)) {
                    itemView.setTag(holder);
                }
                if (!ListenerUtil.mutListener.listen(9514)) {
                    itemView.setOnCheckedChangeListener(new CheckableConstraintLayout.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CheckableConstraintLayout checkableView, boolean isChecked) {
                            if (!ListenerUtil.mutListener.listen(9513)) {
                                if (isChecked) {
                                    if (!ListenerUtil.mutListener.listen(9512)) {
                                        checkedItems.add(((RecentListHolder) checkableView.getTag()).originalPosition);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9511)) {
                                        checkedItems.remove(((RecentListHolder) checkableView.getTag()).originalPosition);
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9504)) {
                    holder = (RecentListHolder) itemView.getTag();
                }
            }
        }
        final ConversationModel conversationModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(9516)) {
            holder.originalPosition = ovalues.indexOf(conversationModel);
        }
        final ContactModel contactModel = conversationModel.getContact();
        final GroupModel groupModel = conversationModel.getGroup();
        final DistributionListModel distributionListModel = conversationModel.getDistributionList();
        String fromtext, subjecttext;
        if (conversationModel.isGroupConversation()) {
            fromtext = NameUtil.getDisplayName(groupModel, this.groupService);
            subjecttext = groupService.getMembersString(groupModel);
            if (!ListenerUtil.mutListener.listen(9519)) {
                holder.groupView.setImageResource(groupService.isGroupOwner(groupModel) ? (groupService.isNotesGroup(groupModel) ? R.drawable.ic_spiral_bound_booklet_outline : R.drawable.ic_group_outline) : R.drawable.ic_group_filled);
            }
        } else if (conversationModel.isDistributionListConversation()) {
            fromtext = NameUtil.getDisplayName(distributionListModel, this.distributionListService);
            subjecttext = context.getString(R.string.distribution_list);
            if (!ListenerUtil.mutListener.listen(9518)) {
                holder.groupView.setImageResource(R.drawable.ic_bullhorn_outline);
            }
        } else {
            fromtext = NameUtil.getDisplayNameOrNickname(contactModel, true);
            subjecttext = contactModel.getIdentity();
            if (!ListenerUtil.mutListener.listen(9517)) {
                holder.groupView.setImageResource(R.drawable.ic_person_outline);
            }
        }
        String filterString = null;
        if (!ListenerUtil.mutListener.listen(9521)) {
            if (recentListFilter != null) {
                if (!ListenerUtil.mutListener.listen(9520)) {
                    filterString = recentListFilter.getFilterString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9522)) {
            holder.nameView.setText(highlightMatches(fromtext, filterString));
        }
        if (!ListenerUtil.mutListener.listen(9525)) {
            if (conversationModel.isGroupConversation()) {
                if (!ListenerUtil.mutListener.listen(9524)) {
                    AdapterUtil.styleGroup(holder.nameView, groupService, groupModel);
                }
            } else if (conversationModel.isContactConversation()) {
                if (!ListenerUtil.mutListener.listen(9523)) {
                    AdapterUtil.styleContact(holder.nameView, contactModel);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9526)) {
            holder.subjectView.setText(highlightMatches(subjecttext, filterString));
        }
        if (!ListenerUtil.mutListener.listen(9527)) {
            // load avatars asynchronously
            AvatarListItemUtil.loadAvatar(position, conversationModel, this.defaultContactImage, this.defaultGroupImage, this.defaultDistributionListImage, this.contactService, this.groupService, this.distributionListService, holder);
        }
        if (!ListenerUtil.mutListener.listen(9528)) {
            ((ListView) parent).setItemChecked(position, checkedItems.contains(holder.originalPosition));
        }
        return itemView;
    }

    private static class RecentListHolder extends AvatarListItemHolder {

        TextView nameView;

        TextView subjectView;

        ImageView groupView;

        int originalPosition;
    }

    public class RecentListFilter extends Filter {

        String filterString = null;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(9549)) {
                if ((ListenerUtil.mutListener.listen(9534) ? (constraint == null && (ListenerUtil.mutListener.listen(9533) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9532) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9531) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9530) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9529) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(9533) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9532) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9531) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9530) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9529) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(9546)) {
                        // no filtering
                        filterString = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9547)) {
                        results.values = ovalues;
                    }
                    if (!ListenerUtil.mutListener.listen(9548)) {
                        results.count = ovalues.size();
                    }
                } else {
                    // perform filtering
                    List<ConversationModel> conversationList = new ArrayList<ConversationModel>();
                    if (!ListenerUtil.mutListener.listen(9535)) {
                        filterString = constraint.toString();
                    }
                    if (!ListenerUtil.mutListener.listen(9543)) {
                        {
                            long _loopCounter78 = 0;
                            for (ConversationModel conversationModel : ovalues) {
                                ListenerUtil.loopListener.listen("_loopCounter78", ++_loopCounter78);
                                if (!ListenerUtil.mutListener.listen(9542)) {
                                    if (conversationModel.isGroupConversation()) {
                                        String text = NameUtil.getDisplayName(conversationModel.getGroup(), groupService);
                                        if (!ListenerUtil.mutListener.listen(9541)) {
                                            if (text.toUpperCase().contains(filterString.toUpperCase())) {
                                                if (!ListenerUtil.mutListener.listen(9540)) {
                                                    conversationList.add(conversationModel);
                                                }
                                            }
                                        }
                                    } else if (conversationModel.isDistributionListConversation()) {
                                        String text = NameUtil.getDisplayName(conversationModel.getDistributionList(), distributionListService);
                                        if (!ListenerUtil.mutListener.listen(9539)) {
                                            if (text.toUpperCase().contains(filterString.toUpperCase())) {
                                                if (!ListenerUtil.mutListener.listen(9538)) {
                                                    conversationList.add(conversationModel);
                                                }
                                            }
                                        }
                                    } else {
                                        String text = NameUtil.getDisplayNameOrNickname(conversationModel.getContact(), false) + conversationModel.getContact().getIdentity();
                                        if (!ListenerUtil.mutListener.listen(9537)) {
                                            if (text.toUpperCase().contains(filterString.toUpperCase())) {
                                                if (!ListenerUtil.mutListener.listen(9536)) {
                                                    conversationList.add(conversationModel);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9544)) {
                        results.values = conversationList;
                    }
                    if (!ListenerUtil.mutListener.listen(9545)) {
                        results.count = conversationList.size();
                    }
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(9550)) {
                values = (List<ConversationModel>) results.values;
            }
            if (!ListenerUtil.mutListener.listen(9551)) {
                notifyDataSetChanged();
            }
        }

        public String getFilterString() {
            return filterString;
        }
    }

    @Override
    public Filter getFilter() {
        if (!ListenerUtil.mutListener.listen(9553)) {
            if (recentListFilter == null)
                if (!ListenerUtil.mutListener.listen(9552)) {
                    recentListFilter = new RecentListFilter();
                }
        }
        return recentListFilter;
    }

    @Override
    public int getCount() {
        return values != null ? values.size() : 0;
    }

    @Override
    public HashSet<?> getCheckedItems() {
        HashSet<Object> conversations = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(9555)) {
            {
                long _loopCounter79 = 0;
                for (int position : checkedItems) {
                    ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                    if (!ListenerUtil.mutListener.listen(9554)) {
                        conversations.add(getModel(ovalues.get(position)));
                    }
                }
            }
        }
        return conversations;
    }

    @Override
    public Object getClickedItem(View v) {
        return getModel(ovalues.get(((RecentListHolder) v.getTag()).originalPosition));
    }

    private Object getModel(ConversationModel conversationModel) {
        if (conversationModel.isGroupConversation()) {
            return conversationModel.getGroup();
        } else if (conversationModel.isDistributionListConversation()) {
            return conversationModel.getDistributionList();
        } else {
            return conversationModel.getContact();
        }
    }
}
