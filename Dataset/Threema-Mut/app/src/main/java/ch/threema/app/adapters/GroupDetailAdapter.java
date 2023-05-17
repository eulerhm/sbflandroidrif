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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ContactDetailActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.SectionHeaderView;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final Logger logger = LoggerFactory.getLogger(GroupDetailAdapter.class);

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;

    private Context context;

    private ContactService contactService;

    private GroupModel groupModel;

    // Cached copy of group members
    private List<ContactModel> contactModels;

    private OnClickListener onClickListener;

    public static class ItemHolder extends RecyclerView.ViewHolder {

        public final View view;

        public final TextView nameView, idView;

        public final AvatarView avatarView;

        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.nameView = itemView.findViewById(R.id.group_name);
            this.avatarView = itemView.findViewById(R.id.avatar_view);
            this.idView = itemView.findViewById(R.id.threemaid);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        private final SectionHeaderView groupMembersTitleView;

        private final LinearLayout groupOwnerContainerView;

        private final AvatarView ownerAvatarView;

        private final TextView ownerName;

        private final TextView ownerThreemaId;

        private final SectionHeaderView ownerNameTitle;

        public HeaderHolder(View view) {
            super(view);
            // items in object
            this.groupMembersTitleView = itemView.findViewById(R.id.group_members_title);
            this.groupOwnerContainerView = itemView.findViewById(R.id.group_owner_container);
            this.ownerAvatarView = itemView.findViewById(R.id.avatar_view);
            this.ownerName = itemView.findViewById(R.id.group_name);
            this.ownerThreemaId = itemView.findViewById(R.id.threemaid);
            this.ownerNameTitle = itemView.findViewById(R.id.group_owner_title);
        }
    }

    public GroupDetailAdapter(Context context, GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(9048)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(9049)) {
            this.groupModel = groupModel;
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(9051)) {
                this.contactService = serviceManager.getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(9050)) {
                logger.error("Exception", e);
            }
        }
    }

    public void setContactModels(List<ContactModel> newContactModels) {
        if (!ListenerUtil.mutListener.listen(9052)) {
            this.contactModels = newContactModels;
        }
        if (!ListenerUtil.mutListener.listen(9053)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if ((ListenerUtil.mutListener.listen(9058) ? (viewType >= TYPE_ITEM) : (ListenerUtil.mutListener.listen(9057) ? (viewType <= TYPE_ITEM) : (ListenerUtil.mutListener.listen(9056) ? (viewType > TYPE_ITEM) : (ListenerUtil.mutListener.listen(9055) ? (viewType < TYPE_ITEM) : (ListenerUtil.mutListener.listen(9054) ? (viewType != TYPE_ITEM) : (viewType == TYPE_ITEM))))))) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_detail, parent, false);
            return new ItemHolder(v);
        } else if ((ListenerUtil.mutListener.listen(9063) ? (viewType >= TYPE_HEADER) : (ListenerUtil.mutListener.listen(9062) ? (viewType <= TYPE_HEADER) : (ListenerUtil.mutListener.listen(9061) ? (viewType > TYPE_HEADER) : (ListenerUtil.mutListener.listen(9060) ? (viewType < TYPE_HEADER) : (ListenerUtil.mutListener.listen(9059) ? (viewType != TYPE_HEADER) : (viewType == TYPE_HEADER))))))) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_group_detail, parent, false);
            return new HeaderHolder(v);
        }
        throw new RuntimeException("no matching item type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (!ListenerUtil.mutListener.listen(9085)) {
            if (holder instanceof ItemHolder) {
                ItemHolder itemHolder = (ItemHolder) holder;
                final ContactModel contactModel = getItem(position);
                Bitmap avatar = this.contactService.getAvatar(contactModel, false);
                if (!ListenerUtil.mutListener.listen(9078)) {
                    itemHolder.nameView.setText(NameUtil.getDisplayNameOrNickname(contactModel, true));
                }
                if (!ListenerUtil.mutListener.listen(9079)) {
                    itemHolder.idView.setText(contactModel.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(9080)) {
                    AdapterUtil.styleContact(itemHolder.nameView, contactModel);
                }
                if (!ListenerUtil.mutListener.listen(9081)) {
                    itemHolder.avatarView.setImageBitmap(avatar);
                }
                if (!ListenerUtil.mutListener.listen(9082)) {
                    itemHolder.avatarView.setBadgeVisible(contactService.showBadge(contactModel));
                }
                if (!ListenerUtil.mutListener.listen(9084)) {
                    itemHolder.view.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(9083)) {
                                onClickListener.onItemClick(v, contactModel);
                            }
                        }
                    });
                }
            } else {
                HeaderHolder headerHolder = (HeaderHolder) holder;
                if (!ListenerUtil.mutListener.listen(9067)) {
                    headerHolder.groupOwnerContainerView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ContactDetailActivity.class);
                            if (!ListenerUtil.mutListener.listen(9064)) {
                                intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, groupModel.getCreatorIdentity());
                            }
                            if (!ListenerUtil.mutListener.listen(9065)) {
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            }
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
                            if (!ListenerUtil.mutListener.listen(9066)) {
                                ActivityCompat.startActivityForResult((Activity) context, intent, ThreemaActivity.ACTIVITY_ID_CONTACT_DETAIL, options.toBundle());
                            }
                        }
                    });
                }
                ContactModel ownerContactModel = contactService.getByIdentity(groupModel.getCreatorIdentity());
                if (!ListenerUtil.mutListener.listen(9074)) {
                    if (ownerContactModel != null) {
                        Bitmap bitmap = contactService.getAvatar(ownerContactModel, false);
                        if (!ListenerUtil.mutListener.listen(9071)) {
                            headerHolder.ownerAvatarView.setImageBitmap(bitmap);
                        }
                        if (!ListenerUtil.mutListener.listen(9072)) {
                            headerHolder.ownerThreemaId.setText(ownerContactModel.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(9073)) {
                            headerHolder.ownerName.setText(NameUtil.getDisplayNameOrNickname(ownerContactModel, true));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9068)) {
                            // creator is no longer around / has been revoked
                            headerHolder.ownerAvatarView.setImageBitmap(contactService.getDefaultAvatar(null, false));
                        }
                        if (!ListenerUtil.mutListener.listen(9069)) {
                            headerHolder.ownerThreemaId.setText(groupModel.getCreatorIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(9070)) {
                            headerHolder.ownerName.setText(R.string.invalid_threema_id);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9075)) {
                    headerHolder.ownerNameTitle.setText(context.getString(R.string.add_group_owner) + " (" + LocaleUtil.formatTimeStampString(context, groupModel.getCreatedAt().getTime(), false) + ")");
                }
                if (!ListenerUtil.mutListener.listen(9077)) {
                    if (contactModels != null) {
                        if (!ListenerUtil.mutListener.listen(9076)) {
                            headerHolder.groupMembersTitleView.setText(context.getString(R.string.add_group_members_list) + " (" + contactModels.size() + "/" + context.getResources().getInteger(R.integer.max_group_size) + ")");
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (contactModels != null)
            return contactModels.size() + 1;
        else
            return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (!ListenerUtil.mutListener.listen(9086)) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return (ListenerUtil.mutListener.listen(9091) ? (position >= 0) : (ListenerUtil.mutListener.listen(9090) ? (position <= 0) : (ListenerUtil.mutListener.listen(9089) ? (position > 0) : (ListenerUtil.mutListener.listen(9088) ? (position < 0) : (ListenerUtil.mutListener.listen(9087) ? (position != 0) : (position == 0))))));
    }

    public int getPosition(ContactModel contactModel) {
        return contactModels.indexOf(contactModel) + 1;
    }

    public ContactModel getItem(int position) {
        return contactModels.get((ListenerUtil.mutListener.listen(9095) ? (position % 1) : (ListenerUtil.mutListener.listen(9094) ? (position / 1) : (ListenerUtil.mutListener.listen(9093) ? (position * 1) : (ListenerUtil.mutListener.listen(9092) ? (position + 1) : (position - 1))))));
    }

    public void setOnClickListener(OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(9096)) {
            onClickListener = listener;
        }
    }

    public interface OnClickListener {

        void onItemClick(View v, ContactModel contactModel);
    }
}
