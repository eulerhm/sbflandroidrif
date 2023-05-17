/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.archive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.emojis.EmojiMarkupUtil;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.CheckableRelativeLayout;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder> {

    private static final Logger logger = LoggerFactory.getLogger(ArchiveAdapter.class);

    private final Context context;

    private ArchiveAdapter.OnClickItemListener onClickItemListener;

    private final Bitmap defaultContactImage, defaultGroupImage, defaultDistributionListImage;

    private ContactService contactService;

    private GroupService groupService;

    private DistributionListService distributionListService;

    private DeadlineListService hiddenChatsListService;

    private SparseBooleanArray checkedItems = new SparseBooleanArray();

    class ArchiveViewHolder extends RecyclerView.ViewHolder {

        TextView fromView;

        TextView dateView;

        TextView subjectView;

        ImageView deliveryView, attachmentView;

        View latestMessageContainer;

        TextView groupMemberName;

        AvatarView avatarView;

        AvatarListItemHolder avatarListItemHolder;

        private ArchiveViewHolder(final View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(9894)) {
                fromView = itemView.findViewById(R.id.from);
            }
            if (!ListenerUtil.mutListener.listen(9895)) {
                dateView = itemView.findViewById(R.id.date);
            }
            if (!ListenerUtil.mutListener.listen(9896)) {
                subjectView = itemView.findViewById(R.id.subject);
            }
            if (!ListenerUtil.mutListener.listen(9897)) {
                avatarView = itemView.findViewById(R.id.avatar_view);
            }
            if (!ListenerUtil.mutListener.listen(9898)) {
                attachmentView = itemView.findViewById(R.id.attachment);
            }
            if (!ListenerUtil.mutListener.listen(9899)) {
                deliveryView = itemView.findViewById(R.id.delivery);
            }
            if (!ListenerUtil.mutListener.listen(9900)) {
                latestMessageContainer = itemView.findViewById(R.id.latest_message_container);
            }
            if (!ListenerUtil.mutListener.listen(9901)) {
                groupMemberName = itemView.findViewById(R.id.group_member_name);
            }
            if (!ListenerUtil.mutListener.listen(9902)) {
                avatarListItemHolder = new AvatarListItemHolder();
            }
            if (!ListenerUtil.mutListener.listen(9903)) {
                avatarListItemHolder.avatarView = avatarView;
            }
            if (!ListenerUtil.mutListener.listen(9904)) {
                avatarListItemHolder.avatarLoadingAsyncTask = null;
            }
        }
    }

    private final LayoutInflater inflater;

    // Cached copy of conversationModels
    private List<ConversationModel> conversationModels;

    ArchiveAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.defaultContactImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_contact);
        this.defaultGroupImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_group);
        this.defaultDistributionListImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_distribution_list);
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            if (!ListenerUtil.mutListener.listen(9906)) {
                if (serviceManager == null) {
                    throw new ThreemaException("Missing servicemanager");
                }
            }
            if (!ListenerUtil.mutListener.listen(9907)) {
                this.distributionListService = serviceManager.getDistributionListService();
            }
            if (!ListenerUtil.mutListener.listen(9908)) {
                this.groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(9909)) {
                this.contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(9910)) {
                this.hiddenChatsListService = serviceManager.getHiddenChatsListService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(9905)) {
                logger.debug("Exception", e);
            }
        }
    }

    @NonNull
    @Override
    public ArchiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_archive, parent, false);
        return new ArchiveViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(9963)) {
            if (conversationModels != null) {
                final ConversationModel conversationModel = conversationModels.get(position);
                final AbstractMessageModel messageModel = conversationModel.getLatestMessage();
                if (!ListenerUtil.mutListener.listen(9915)) {
                    if (holder.groupMemberName != null) {
                        if (!ListenerUtil.mutListener.listen(9914)) {
                            holder.groupMemberName.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9916)) {
                    holder.deliveryView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(9917)) {
                    holder.fromView.setText(conversationModel.getReceiver().getDisplayName());
                }
                if (!ListenerUtil.mutListener.listen(9918)) {
                    holder.fromView.setTextAppearance(context, R.style.Threema_TextAppearance_List_FirstLine);
                }
                if (!ListenerUtil.mutListener.listen(9919)) {
                    holder.subjectView.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine);
                }
                if (!ListenerUtil.mutListener.listen(9923)) {
                    if ((ListenerUtil.mutListener.listen(9920) ? (holder.groupMemberName != null || holder.dateView != null) : (holder.groupMemberName != null && holder.dateView != null))) {
                        if (!ListenerUtil.mutListener.listen(9921)) {
                            holder.groupMemberName.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine);
                        }
                        if (!ListenerUtil.mutListener.listen(9922)) {
                            holder.dateView.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9956)) {
                    if (messageModel != null) {
                        if (!ListenerUtil.mutListener.listen(9955)) {
                            if (hiddenChatsListService.has(conversationModel.getReceiver().getUniqueIdString())) {
                                if (!ListenerUtil.mutListener.listen(9950)) {
                                    // give user some privacy even in visible mode
                                    holder.subjectView.setText(R.string.private_chat_subject);
                                }
                                if (!ListenerUtil.mutListener.listen(9951)) {
                                    holder.subjectView.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9952)) {
                                    holder.attachmentView.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(9953)) {
                                    holder.dateView.setVisibility(View.INVISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9954)) {
                                    holder.deliveryView.setVisibility(View.GONE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9929)) {
                                    holder.dateView.setText(MessageUtil.getDisplayDate(this.context, messageModel, false));
                                }
                                if (!ListenerUtil.mutListener.listen(9930)) {
                                    holder.dateView.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9934)) {
                                    if (conversationModel.isGroupConversation()) {
                                        if (!ListenerUtil.mutListener.listen(9933)) {
                                            if (holder.groupMemberName != null) {
                                                if (!ListenerUtil.mutListener.listen(9931)) {
                                                    holder.groupMemberName.setText(NameUtil.getShortName(this.context, messageModel, this.contactService) + ": ");
                                                }
                                                if (!ListenerUtil.mutListener.listen(9932)) {
                                                    holder.groupMemberName.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        }
                                    }
                                }
                                // Configure subject
                                MessageUtil.MessageViewElement viewElement = MessageUtil.getViewElement(this.context, messageModel);
                                String subject = viewElement.text;
                                if (!ListenerUtil.mutListener.listen(9936)) {
                                    if (messageModel.getType() == MessageType.TEXT) {
                                        if (!ListenerUtil.mutListener.listen(9935)) {
                                            // we need to add an arbitrary character - otherwise span-only strings are formatted incorrectly in the item layout
                                            subject += " ";
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(9945)) {
                                    if (viewElement.icon != null) {
                                        if (!ListenerUtil.mutListener.listen(9938)) {
                                            holder.attachmentView.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9939)) {
                                            holder.attachmentView.setImageResource(viewElement.icon);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9942)) {
                                            if (viewElement.placeholder != null) {
                                                if (!ListenerUtil.mutListener.listen(9941)) {
                                                    holder.attachmentView.setContentDescription(viewElement.placeholder);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(9940)) {
                                                    holder.attachmentView.setContentDescription("");
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(9944)) {
                                            // Configure color of the attachment view
                                            if (viewElement.color != null) {
                                                if (!ListenerUtil.mutListener.listen(9943)) {
                                                    holder.attachmentView.setColorFilter(this.context.getResources().getColor(viewElement.color), PorterDuff.Mode.SRC_IN);
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(9937)) {
                                            holder.attachmentView.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(9949)) {
                                    if (ViewUtil.show(holder.subjectView, subject != null)) {
                                        if (!ListenerUtil.mutListener.listen(9947)) {
                                            if (holder.attachmentView.getVisibility() == View.VISIBLE) {
                                                if (!ListenerUtil.mutListener.listen(9946)) {
                                                    subject = " " + subject;
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(9948)) {
                                            holder.subjectView.setText(EmojiMarkupUtil.getInstance().formatBodyTextString(context, subject, 100));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9924)) {
                            // empty chat
                            holder.attachmentView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9925)) {
                            holder.deliveryView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9926)) {
                            holder.dateView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9927)) {
                            holder.subjectView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(9928)) {
                            holder.subjectView.setText("");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9957)) {
                    AdapterUtil.styleConversation(holder.fromView, groupService, conversationModel);
                }
                if (!ListenerUtil.mutListener.listen(9958)) {
                    // load avatars asynchronously
                    AvatarListItemUtil.loadAvatar(position, conversationModel, this.defaultContactImage, this.defaultGroupImage, this.defaultDistributionListImage, this.contactService, this.groupService, this.distributionListService, holder.avatarListItemHolder);
                }
                if (!ListenerUtil.mutListener.listen(9959)) {
                    ((CheckableRelativeLayout) holder.itemView).setChecked(checkedItems.get(position));
                }
                if (!ListenerUtil.mutListener.listen(9962)) {
                    if (this.onClickItemListener != null) {
                        if (!ListenerUtil.mutListener.listen(9960)) {
                            holder.itemView.setOnClickListener(v -> onClickItemListener.onClick(conversationModel, holder.itemView, position));
                        }
                        if (!ListenerUtil.mutListener.listen(9961)) {
                            holder.itemView.setOnLongClickListener(v -> onClickItemListener.onLongClick(conversationModel, holder.itemView, position));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9911)) {
                    // Covers the case of data not being ready yet.
                    holder.fromView.setText("No data");
                }
                if (!ListenerUtil.mutListener.listen(9912)) {
                    holder.dateView.setText("");
                }
                if (!ListenerUtil.mutListener.listen(9913)) {
                    holder.subjectView.setText("");
                }
            }
        }
    }

    // conversationModels has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (conversationModels != null)
            return conversationModels.size();
        else
            return 0;
    }

    void setConversationModels(List<ConversationModel> newConversationModels) {
        if (!ListenerUtil.mutListener.listen(9981)) {
            if (conversationModels != null) {
                SparseBooleanArray newCheckedItems = new SparseBooleanArray(newConversationModels.size());
                if (!ListenerUtil.mutListener.listen(9979)) {
                    {
                        long _loopCounter84 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(9978) ? (i >= newConversationModels.size()) : (ListenerUtil.mutListener.listen(9977) ? (i <= newConversationModels.size()) : (ListenerUtil.mutListener.listen(9976) ? (i > newConversationModels.size()) : (ListenerUtil.mutListener.listen(9975) ? (i != newConversationModels.size()) : (ListenerUtil.mutListener.listen(9974) ? (i == newConversationModels.size()) : (i < newConversationModels.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter84", ++_loopCounter84);
                            String newUid = newConversationModels.get(i).getUid();
                            if (!ListenerUtil.mutListener.listen(9973)) {
                                if (newUid != null) {
                                    if (!ListenerUtil.mutListener.listen(9972)) {
                                        {
                                            long _loopCounter83 = 0;
                                            for (int j = 0; (ListenerUtil.mutListener.listen(9971) ? (j >= conversationModels.size()) : (ListenerUtil.mutListener.listen(9970) ? (j <= conversationModels.size()) : (ListenerUtil.mutListener.listen(9969) ? (j > conversationModels.size()) : (ListenerUtil.mutListener.listen(9968) ? (j != conversationModels.size()) : (ListenerUtil.mutListener.listen(9967) ? (j == conversationModels.size()) : (j < conversationModels.size())))))); j++) {
                                                ListenerUtil.loopListener.listen("_loopCounter83", ++_loopCounter83);
                                                if (!ListenerUtil.mutListener.listen(9966)) {
                                                    if (newUid.equals(conversationModels.get(j).getUid())) {
                                                        if (!ListenerUtil.mutListener.listen(9965)) {
                                                            if (checkedItems.get(j)) {
                                                                if (!ListenerUtil.mutListener.listen(9964)) {
                                                                    newCheckedItems.put(i, true);
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9980)) {
                    this.checkedItems = newCheckedItems;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9982)) {
            this.conversationModels = newConversationModels;
        }
        if (!ListenerUtil.mutListener.listen(9983)) {
            notifyDataSetChanged();
        }
    }

    void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        if (!ListenerUtil.mutListener.listen(9984)) {
            this.onClickItemListener = onClickItemListener;
        }
    }

    void toggleChecked(int pos) {
        if (!ListenerUtil.mutListener.listen(9987)) {
            if (checkedItems.get(pos, false)) {
                if (!ListenerUtil.mutListener.listen(9986)) {
                    checkedItems.delete(pos);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9985)) {
                    checkedItems.put(pos, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9988)) {
            notifyItemChanged(pos);
        }
    }

    void clearCheckedItems() {
        if (!ListenerUtil.mutListener.listen(9989)) {
            checkedItems.clear();
        }
        if (!ListenerUtil.mutListener.listen(9990)) {
            notifyDataSetChanged();
        }
    }

    void selectAll() {
        if (!ListenerUtil.mutListener.listen(10000)) {
            if (checkedItems.size() == conversationModels.size()) {
                if (!ListenerUtil.mutListener.listen(9999)) {
                    clearCheckedItems();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9997)) {
                    {
                        long _loopCounter85 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(9996) ? (i >= conversationModels.size()) : (ListenerUtil.mutListener.listen(9995) ? (i <= conversationModels.size()) : (ListenerUtil.mutListener.listen(9994) ? (i > conversationModels.size()) : (ListenerUtil.mutListener.listen(9993) ? (i != conversationModels.size()) : (ListenerUtil.mutListener.listen(9992) ? (i == conversationModels.size()) : (i < conversationModels.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter85", ++_loopCounter85);
                            if (!ListenerUtil.mutListener.listen(9991)) {
                                checkedItems.put(i, true);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9998)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    int getCheckedItemsCount() {
        return checkedItems.size();
    }

    public List<ConversationModel> getCheckedItems() {
        List<ConversationModel> items = new ArrayList<>(checkedItems.size());
        if (!ListenerUtil.mutListener.listen(10007)) {
            {
                long _loopCounter86 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10006) ? (i >= checkedItems.size()) : (ListenerUtil.mutListener.listen(10005) ? (i <= checkedItems.size()) : (ListenerUtil.mutListener.listen(10004) ? (i > checkedItems.size()) : (ListenerUtil.mutListener.listen(10003) ? (i != checkedItems.size()) : (ListenerUtil.mutListener.listen(10002) ? (i == checkedItems.size()) : (i < checkedItems.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter86", ++_loopCounter86);
                    if (!ListenerUtil.mutListener.listen(10001)) {
                        items.add(conversationModels.get(checkedItems.keyAt(i)));
                    }
                }
            }
        }
        return items;
    }

    public interface OnClickItemListener {

        void onClick(ConversationModel conversationModel, View view, int position);

        boolean onLongClick(ConversationModel conversationModel, View itemView, int position);
    }
}
