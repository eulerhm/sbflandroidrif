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
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.IdListService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.CheckableConstraintLayout;
import ch.threema.app.ui.CheckableView;
import ch.threema.app.ui.VerificationLevelImageView;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UserListAdapter extends FilterableListAdapter {

    private final Context context;

    private List<ContactModel> values;

    private List<ContactModel> ovalues;

    private UserListFilter userListFilter;

    private final Bitmap defaultContactImage;

    private final ContactService contactService;

    private final IdListService blacklistService;

    private final DeadlineListService hiddenChatsListService;

    public UserListAdapter(Context context, final List<ContactModel> values, final List<String> preselectedIdentities, final List<Integer> checkedItems, ContactService contactService, IdListService blacklistService, DeadlineListService hiddenChatsListService) {
        super(context, R.layout.item_user_list, (List<Object>) (Object) values);
        this.context = context;
        if (!ListenerUtil.mutListener.listen(9623)) {
            this.values = values;
        }
        if (!ListenerUtil.mutListener.listen(9624)) {
            this.ovalues = values;
        }
        this.contactService = contactService;
        this.blacklistService = blacklistService;
        this.hiddenChatsListService = hiddenChatsListService;
        this.defaultContactImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_contact);
        if (!ListenerUtil.mutListener.listen(9647)) {
            if ((ListenerUtil.mutListener.listen(9630) ? (checkedItems != null || (ListenerUtil.mutListener.listen(9629) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9628) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9627) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9626) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9625) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))) : (checkedItems != null && (ListenerUtil.mutListener.listen(9629) ? (checkedItems.size() >= 0) : (ListenerUtil.mutListener.listen(9628) ? (checkedItems.size() <= 0) : (ListenerUtil.mutListener.listen(9627) ? (checkedItems.size() < 0) : (ListenerUtil.mutListener.listen(9626) ? (checkedItems.size() != 0) : (ListenerUtil.mutListener.listen(9625) ? (checkedItems.size() == 0) : (checkedItems.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9646)) {
                    // restore checked items
                    this.checkedItems.addAll(checkedItems);
                }
            } else // validate if preselected items are in dataset
            if ((ListenerUtil.mutListener.listen(9637) ? ((ListenerUtil.mutListener.listen(9631) ? (values != null || preselectedIdentities != null) : (values != null && preselectedIdentities != null)) || (ListenerUtil.mutListener.listen(9636) ? (preselectedIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(9635) ? (preselectedIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(9634) ? (preselectedIdentities.size() < 0) : (ListenerUtil.mutListener.listen(9633) ? (preselectedIdentities.size() != 0) : (ListenerUtil.mutListener.listen(9632) ? (preselectedIdentities.size() == 0) : (preselectedIdentities.size() > 0))))))) : ((ListenerUtil.mutListener.listen(9631) ? (values != null || preselectedIdentities != null) : (values != null && preselectedIdentities != null)) && (ListenerUtil.mutListener.listen(9636) ? (preselectedIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(9635) ? (preselectedIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(9634) ? (preselectedIdentities.size() < 0) : (ListenerUtil.mutListener.listen(9633) ? (preselectedIdentities.size() != 0) : (ListenerUtil.mutListener.listen(9632) ? (preselectedIdentities.size() == 0) : (preselectedIdentities.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9645)) {
                    {
                        long _loopCounter80 = 0;
                        // TODO do not restore after rotate (members)
                        for (int i = 0; (ListenerUtil.mutListener.listen(9644) ? (i >= values.size()) : (ListenerUtil.mutListener.listen(9643) ? (i <= values.size()) : (ListenerUtil.mutListener.listen(9642) ? (i > values.size()) : (ListenerUtil.mutListener.listen(9641) ? (i != values.size()) : (ListenerUtil.mutListener.listen(9640) ? (i == values.size()) : (i < values.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter80", ++_loopCounter80);
                            ContactModel contactModel = values.get(i);
                            if (!ListenerUtil.mutListener.listen(9639)) {
                                if (preselectedIdentities.contains(contactModel.getIdentity())) {
                                    if (!ListenerUtil.mutListener.listen(9638)) {
                                        this.checkedItems.add(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckableConstraintLayout itemView = (CheckableConstraintLayout) convertView;
        UserListHolder holder = new UserListHolder();
        if (!ListenerUtil.mutListener.listen(9663)) {
            if (convertView == null) {
                // This a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (!ListenerUtil.mutListener.listen(9649)) {
                    itemView = (CheckableConstraintLayout) inflater.inflate(R.layout.item_user_list, parent, false);
                }
                TextView nameView = itemView.findViewById(R.id.name);
                TextView subjectView = itemView.findViewById(R.id.subject);
                VerificationLevelImageView verificationLevelView = itemView.findViewById(R.id.verification_level);
                AvatarView avatarView = itemView.findViewById(R.id.avatar_view);
                ImageView blockedView = itemView.findViewById(R.id.blocked_contact);
                CheckableView checkableView = itemView.findViewById(R.id.check_box);
                TextView dateView = itemView.findViewById(R.id.date);
                ImageView lastMessageView = itemView.findViewById(R.id.last_message_icon);
                if (!ListenerUtil.mutListener.listen(9650)) {
                    holder.nameView = nameView;
                }
                if (!ListenerUtil.mutListener.listen(9651)) {
                    holder.subjectView = subjectView;
                }
                if (!ListenerUtil.mutListener.listen(9652)) {
                    holder.verificationLevelView = verificationLevelView;
                }
                if (!ListenerUtil.mutListener.listen(9653)) {
                    holder.avatarView = avatarView;
                }
                if (!ListenerUtil.mutListener.listen(9654)) {
                    holder.blockedView = blockedView;
                }
                if (!ListenerUtil.mutListener.listen(9655)) {
                    holder.checkableView = checkableView;
                }
                if (!ListenerUtil.mutListener.listen(9656)) {
                    holder.dateView = dateView;
                }
                if (!ListenerUtil.mutListener.listen(9657)) {
                    holder.lastMessageView = lastMessageView;
                }
                if (!ListenerUtil.mutListener.listen(9658)) {
                    itemView.setTag(holder);
                }
                if (!ListenerUtil.mutListener.listen(9662)) {
                    itemView.setOnCheckedChangeListener(new CheckableConstraintLayout.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CheckableConstraintLayout checkableView, boolean isChecked) {
                            if (!ListenerUtil.mutListener.listen(9661)) {
                                if (isChecked) {
                                    if (!ListenerUtil.mutListener.listen(9660)) {
                                        checkedItems.add(((UserListHolder) checkableView.getTag()).originalPosition);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9659)) {
                                        checkedItems.remove(((UserListHolder) checkableView.getTag()).originalPosition);
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9648)) {
                    holder = (UserListHolder) itemView.getTag();
                }
            }
        }
        final ContactModel contactModel = values.get(position);
        if (!ListenerUtil.mutListener.listen(9664)) {
            holder.originalPosition = ovalues.indexOf(contactModel);
        }
        String filterString = null;
        if (!ListenerUtil.mutListener.listen(9666)) {
            if (userListFilter != null) {
                if (!ListenerUtil.mutListener.listen(9665)) {
                    filterString = userListFilter.getFilterString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9667)) {
            ViewUtil.showAndSet(holder.nameView, highlightMatches(NameUtil.getDisplayNameOrNickname(contactModel, true), filterString));
        }
        if (!ListenerUtil.mutListener.listen(9668)) {
            AdapterUtil.styleContact(holder.nameView, contactModel);
        }
        if (!ListenerUtil.mutListener.listen(9669)) {
            ViewUtil.showAndSet(holder.subjectView, highlightMatches(contactModel.getIdentity(), filterString));
        }
        if (!ListenerUtil.mutListener.listen(9670)) {
            AdapterUtil.styleContact(holder.subjectView, contactModel);
        }
        if (!ListenerUtil.mutListener.listen(9672)) {
            ViewUtil.show(holder.blockedView, (ListenerUtil.mutListener.listen(9671) ? (blacklistService != null || blacklistService.has(contactModel.getIdentity())) : (blacklistService != null && blacklistService.has(contactModel.getIdentity()))));
        }
        if (!ListenerUtil.mutListener.listen(9673)) {
            holder.verificationLevelView.setContactModel(contactModel);
        }
        String lastMessageDateString = null;
        MessageReceiver messageReceiver = this.contactService.createReceiver(contactModel);
        if (!ListenerUtil.mutListener.listen(9676)) {
            if ((ListenerUtil.mutListener.listen(9674) ? (messageReceiver != null || !hiddenChatsListService.has(messageReceiver.getUniqueIdString())) : (messageReceiver != null && !hiddenChatsListService.has(messageReceiver.getUniqueIdString())))) {
                if (!ListenerUtil.mutListener.listen(9675)) {
                    lastMessageDateString = MessageUtil.getDisplayDate(this.context, ((ContactMessageReceiver) messageReceiver).getLastMessage(), false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9677)) {
            ViewUtil.showAndSet(holder.dateView, lastMessageDateString);
        }
        if (!ListenerUtil.mutListener.listen(9678)) {
            ViewUtil.show(holder.lastMessageView, !TestUtil.empty(lastMessageDateString));
        }
        if (!ListenerUtil.mutListener.listen(9679)) {
            // load avatars asynchronously
            AvatarListItemUtil.loadAvatar(position, contactModel, this.defaultContactImage, this.contactService, holder);
        }
        if (!ListenerUtil.mutListener.listen(9680)) {
            ((ListView) parent).setItemChecked(position, checkedItems.contains(holder.originalPosition));
        }
        return itemView;
    }

    private static class UserListHolder extends AvatarListItemHolder {

        TextView nameView;

        TextView subjectView;

        VerificationLevelImageView verificationLevelView;

        ImageView blockedView;

        CheckableView checkableView;

        TextView dateView;

        ImageView lastMessageView;

        int originalPosition;
    }

    public class UserListFilter extends Filter {

        String filterString = null;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(9697)) {
                if ((ListenerUtil.mutListener.listen(9686) ? (constraint == null && (ListenerUtil.mutListener.listen(9685) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9684) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9683) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9682) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9681) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(9685) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(9684) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(9683) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(9682) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(9681) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(9694)) {
                        // no filtering
                        filterString = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9695)) {
                        results.values = ovalues;
                    }
                    if (!ListenerUtil.mutListener.listen(9696)) {
                        results.count = ovalues.size();
                    }
                } else {
                    // perform filtering
                    List<ContactModel> nContactList = new ArrayList<ContactModel>();
                    if (!ListenerUtil.mutListener.listen(9687)) {
                        filterString = constraint.toString();
                    }
                    if (!ListenerUtil.mutListener.listen(9691)) {
                        {
                            long _loopCounter81 = 0;
                            for (ContactModel contactModel : ovalues) {
                                ListenerUtil.loopListener.listen("_loopCounter81", ++_loopCounter81);
                                if (!ListenerUtil.mutListener.listen(9690)) {
                                    if ((ListenerUtil.mutListener.listen(9688) ? ((NameUtil.getDisplayNameOrNickname(contactModel, false).toUpperCase().contains(filterString.toUpperCase())) && (contactModel.getIdentity().toUpperCase().contains(filterString.toUpperCase()))) : ((NameUtil.getDisplayNameOrNickname(contactModel, false).toUpperCase().contains(filterString.toUpperCase())) || (contactModel.getIdentity().toUpperCase().contains(filterString.toUpperCase()))))) {
                                        if (!ListenerUtil.mutListener.listen(9689)) {
                                            nContactList.add(contactModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9692)) {
                        results.values = nContactList;
                    }
                    if (!ListenerUtil.mutListener.listen(9693)) {
                        results.count = nContactList.size();
                    }
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(9698)) {
                values = (List<ContactModel>) results.values;
            }
            if (!ListenerUtil.mutListener.listen(9699)) {
                notifyDataSetChanged();
            }
        }

        public String getFilterString() {
            return filterString;
        }
    }

    @Override
    public Filter getFilter() {
        if (!ListenerUtil.mutListener.listen(9701)) {
            if (userListFilter == null)
                if (!ListenerUtil.mutListener.listen(9700)) {
                    userListFilter = new UserListFilter();
                }
        }
        return userListFilter;
    }

    @Override
    public int getCount() {
        return values != null ? values.size() : 0;
    }

    @Override
    public HashSet<ContactModel> getCheckedItems() {
        HashSet<ContactModel> contacts = new HashSet<>();
        ContactModel contactModel;
        {
            long _loopCounter82 = 0;
            for (int position : checkedItems) {
                ListenerUtil.loopListener.listen("_loopCounter82", ++_loopCounter82);
                contactModel = ovalues.get(position);
                if (!ListenerUtil.mutListener.listen(9703)) {
                    if (contactModel != null) {
                        if (!ListenerUtil.mutListener.listen(9702)) {
                            contacts.add(contactModel);
                        }
                    }
                }
            }
        }
        return contacts;
    }

    @Override
    public ContactModel getClickedItem(View v) {
        return ovalues.get(((UserListHolder) v.getTag()).originalPosition);
    }
}
