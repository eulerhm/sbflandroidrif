/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import java.util.HashMap;
import java.util.List;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.InitialAvatarView;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.work.WorkDirectoryCategory;
import ch.threema.client.work.WorkDirectoryContact;
import ch.threema.client.work.WorkOrganization;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectoryAdapter extends PagedListAdapter<WorkDirectoryContact, RecyclerView.ViewHolder> {

    private final Context context;

    private final LayoutInflater inflater;

    private final PreferenceService preferenceService;

    private final ContactService contactService;

    private final WorkOrganization workOrganization;

    private final HashMap<String, String> categoryMap = new HashMap<>();

    private DirectoryAdapter.OnClickItemListener onClickItemListener;

    @DrawableRes
    private int backgroundRes;

    private static class DirectoryHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;

        private final TextView identityView;

        private final AppCompatImageView statusImageView;

        private final InitialAvatarView avatarView;

        private final TextView categoriesView;

        private final Chip organizationView;

        protected WorkDirectoryContact contact;

        private DirectoryHolder(final View itemView) {
            super(itemView);
            this.nameView = itemView.findViewById(R.id.name);
            this.identityView = itemView.findViewById(R.id.identity);
            this.statusImageView = itemView.findViewById(R.id.status);
            this.avatarView = itemView.findViewById(R.id.avatar_view);
            this.categoriesView = itemView.findViewById(R.id.categories);
            this.organizationView = itemView.findViewById(R.id.organization);
        }

        public View getItem() {
            return itemView;
        }
    }

    public DirectoryAdapter(Context context, PreferenceService preferenceService, ContactService contactService, List<WorkDirectoryCategory> categoryList) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.preferenceService = preferenceService;
        this.contactService = contactService;
        this.workOrganization = preferenceService.getWorkOrganization();
        if (!ListenerUtil.mutListener.listen(8949)) {
            {
                long _loopCounter73 = 0;
                for (WorkDirectoryCategory category : categoryList) {
                    ListenerUtil.loopListener.listen("_loopCounter73", ++_loopCounter73);
                    if (!ListenerUtil.mutListener.listen(8948)) {
                        this.categoryMap.put(category.id, category.name);
                    }
                }
            }
        }
        TypedValue outValue = new TypedValue();
        if (!ListenerUtil.mutListener.listen(8950)) {
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        }
        if (!ListenerUtil.mutListener.listen(8951)) {
            this.backgroundRes = outValue.resourceId;
        }
    }

    public interface OnClickItemListener {

        void onClick(WorkDirectoryContact workDirectoryContact, int position);

        void onAdd(WorkDirectoryContact workDirectoryContact, int position);
    }

    public DirectoryAdapter setOnClickItemListener(DirectoryAdapter.OnClickItemListener onClickItemListener) {
        if (!ListenerUtil.mutListener.listen(8952)) {
            this.onClickItemListener = onClickItemListener;
        }
        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_directory, viewGroup, false);
        if (!ListenerUtil.mutListener.listen(8953)) {
            itemView.setBackgroundResource(R.drawable.listitem_background_selector);
        }
        return new DirectoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        boolean isMe = false;
        final DirectoryHolder holder = (DirectoryHolder) viewHolder;
        final WorkDirectoryContact workDirectoryContact = this.getItem(position);
        if (!ListenerUtil.mutListener.listen(8954)) {
            if (workDirectoryContact == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8955)) {
            holder.contact = workDirectoryContact;
        }
        if (!ListenerUtil.mutListener.listen(8957)) {
            if (holder.contact != null) {
                if (!ListenerUtil.mutListener.listen(8956)) {
                    isMe = holder.contact.threemaId.equals(contactService.getMe().getIdentity());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8962)) {
            if (this.onClickItemListener != null) {
                if (!ListenerUtil.mutListener.listen(8961)) {
                    if (!isMe) {
                        if (!ListenerUtil.mutListener.listen(8959)) {
                            holder.statusImageView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(8958)) {
                                        onClickItemListener.onAdd(holder.contact, viewHolder.getAdapterPosition());
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(8960)) {
                            holder.itemView.setOnClickListener(v -> onClickItemListener.onClick(holder.contact, viewHolder.getAdapterPosition()));
                        }
                    }
                }
            }
        }
        String name;
        if (preferenceService.isContactFormatFirstNameLastName()) {
            name = (workDirectoryContact.firstName != null ? workDirectoryContact.firstName + " " : "") + (workDirectoryContact.lastName != null ? workDirectoryContact.lastName : "");
        } else {
            name = (workDirectoryContact.lastName != null ? workDirectoryContact.lastName + " " : "") + (workDirectoryContact.firstName != null ? workDirectoryContact.firstName : "");
        }
        if (!ListenerUtil.mutListener.listen(8964)) {
            if (!TestUtil.empty(workDirectoryContact.csi)) {
                if (!ListenerUtil.mutListener.listen(8963)) {
                    name += " " + workDirectoryContact.csi;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8965)) {
            holder.nameView.setText(name);
        }
        StringBuilder categoriesBuilder = new StringBuilder("");
        if (!ListenerUtil.mutListener.listen(8981)) {
            if ((ListenerUtil.mutListener.listen(8970) ? (workDirectoryContact.categoryIds.size() >= 0) : (ListenerUtil.mutListener.listen(8969) ? (workDirectoryContact.categoryIds.size() <= 0) : (ListenerUtil.mutListener.listen(8968) ? (workDirectoryContact.categoryIds.size() < 0) : (ListenerUtil.mutListener.listen(8967) ? (workDirectoryContact.categoryIds.size() != 0) : (ListenerUtil.mutListener.listen(8966) ? (workDirectoryContact.categoryIds.size() == 0) : (workDirectoryContact.categoryIds.size() > 0))))))) {
                int count = 0;
                if (!ListenerUtil.mutListener.listen(8980)) {
                    {
                        long _loopCounter74 = 0;
                        for (String categoryId : workDirectoryContact.categoryIds) {
                            ListenerUtil.loopListener.listen("_loopCounter74", ++_loopCounter74);
                            if (!ListenerUtil.mutListener.listen(8977)) {
                                if ((ListenerUtil.mutListener.listen(8975) ? (count >= 0) : (ListenerUtil.mutListener.listen(8974) ? (count <= 0) : (ListenerUtil.mutListener.listen(8973) ? (count > 0) : (ListenerUtil.mutListener.listen(8972) ? (count < 0) : (ListenerUtil.mutListener.listen(8971) ? (count == 0) : (count != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(8976)) {
                                        categoriesBuilder.append(", ");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8978)) {
                                categoriesBuilder.append(categoryMap.get(categoryId));
                            }
                            if (!ListenerUtil.mutListener.listen(8979)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8982)) {
            holder.categoriesView.setText(categoriesBuilder.toString());
        }
        if (!ListenerUtil.mutListener.listen(8983)) {
            holder.avatarView.setInitials(workDirectoryContact.firstName, workDirectoryContact.lastName);
        }
        if (!ListenerUtil.mutListener.listen(8984)) {
            holder.identityView.setText(workDirectoryContact.threemaId);
        }
        if (!ListenerUtil.mutListener.listen(8990)) {
            if ((ListenerUtil.mutListener.listen(8986) ? ((ListenerUtil.mutListener.listen(8985) ? (workDirectoryContact.organization != null || workDirectoryContact.organization.getName() != null) : (workDirectoryContact.organization != null && workDirectoryContact.organization.getName() != null)) || !workDirectoryContact.organization.getName().equals(workOrganization.getName())) : ((ListenerUtil.mutListener.listen(8985) ? (workDirectoryContact.organization != null || workDirectoryContact.organization.getName() != null) : (workDirectoryContact.organization != null && workDirectoryContact.organization.getName() != null)) && !workDirectoryContact.organization.getName().equals(workOrganization.getName())))) {
                if (!ListenerUtil.mutListener.listen(8988)) {
                    holder.organizationView.setText(workDirectoryContact.organization.getName());
                }
                if (!ListenerUtil.mutListener.listen(8989)) {
                    holder.organizationView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8987)) {
                    holder.organizationView.setVisibility(View.GONE);
                }
            }
        }
        boolean isAddedContact = contactService.getByIdentity(workDirectoryContact.threemaId) != null;
        if (!ListenerUtil.mutListener.listen(8991)) {
            holder.statusImageView.setBackgroundResource(isAddedContact ? 0 : this.backgroundRes);
        }
        if (!ListenerUtil.mutListener.listen(8992)) {
            holder.statusImageView.setImageResource(isMe ? R.drawable.ic_person_outline : (isAddedContact ? R.drawable.ic_keyboard_arrow_right_black_24dp : R.drawable.ic_add_circle_outline_black_24dp));
        }
        if (!ListenerUtil.mutListener.listen(8993)) {
            holder.statusImageView.setContentDescription(context.getString(isMe ? R.string.me_myself_and_i : (isAddedContact ? R.string.title_compose_message : R.string.menu_add_contact)));
        }
        if (!ListenerUtil.mutListener.listen(8995)) {
            holder.statusImageView.setClickable((ListenerUtil.mutListener.listen(8994) ? (!isAddedContact || !isMe) : (!isAddedContact && !isMe)));
        }
        if (!ListenerUtil.mutListener.listen(8997)) {
            holder.statusImageView.setFocusable((ListenerUtil.mutListener.listen(8996) ? (!isAddedContact || !isMe) : (!isAddedContact && !isMe)));
        }
    }

    private static final DiffUtil.ItemCallback<WorkDirectoryContact> DIFF_CALLBACK = new DiffUtil.ItemCallback<WorkDirectoryContact>() {

        @Override
        public boolean areItemsTheSame(@NonNull WorkDirectoryContact oldItem, @NonNull WorkDirectoryContact newItem) {
            return (ListenerUtil.mutListener.listen(8998) ? (oldItem.threemaId != null || oldItem.threemaId.equals(newItem.threemaId)) : (oldItem.threemaId != null && oldItem.threemaId.equals(newItem.threemaId)));
        }

        @Override
        public boolean areContentsTheSame(@NonNull WorkDirectoryContact oldItem, @NonNull WorkDirectoryContact newItem) {
            return (ListenerUtil.mutListener.listen(8999) ? (oldItem.threemaId != null || oldItem.threemaId.equals(newItem.threemaId)) : (oldItem.threemaId != null && oldItem.threemaId.equals(newItem.threemaId)));
        }
    };
}
