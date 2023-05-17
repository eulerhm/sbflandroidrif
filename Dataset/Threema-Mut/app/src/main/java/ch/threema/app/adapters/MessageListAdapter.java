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
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.emojis.EmojiMarkupUtil;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.ConversationTagService;
import ch.threema.app.services.ConversationTagServiceImpl;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.ui.AvatarListItemUtil;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.CountBoxView;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.AdapterUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.StateBitmapUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.TagModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageListAdapter extends AbstractRecyclerAdapter<ConversationModel, RecyclerView.ViewHolder> {

    private static final Logger logger = LoggerFactory.getLogger(MessageListAdapter.class);

    private static final int MAX_SELECTED_ITEMS = 0;

    public static final int TYPE_ITEM = 0;

    public static final int TYPE_FOOTER = 1;

    private final Context context;

    private final GroupService groupService;

    private final ConversationTagService conversationTagService;

    private final ContactService contactService;

    private final DistributionListService distributionListService;

    private final DeadlineListService mutedChatsListService, hiddenChatsListService, mentionOnlyChatsListService;

    private final RingtoneService ringtoneService;

    private final ConversationService conversationService;

    private final EmojiMarkupUtil emojiMarkupUtil;

    private final Bitmap defaultContactImage, defaultGroupImage, defaultDistributionListImage;

    private final StateBitmapUtil stateBitmapUtil;

    @ColorInt
    private final int regularColor;

    @ColorInt
    private final int ackColor;

    @ColorInt
    private final int decColor;

    @ColorInt
    private final int backgroundColor;

    private final boolean isTablet;

    private final LayoutInflater inflater;

    private final ItemClickListener clickListener;

    private final List<ConversationModel> selectedChats = new ArrayList<>();

    private String highlightUid;

    private RecyclerView recyclerView;

    private final TagModel starTagModel, unreadTagModel;

    public static class MessageListViewHolder extends RecyclerView.ViewHolder {

        TextView fromView;

        protected TextView dateView;

        TextView subjectView;

        ImageView deliveryView, attachmentView, pinIcon;

        View listItemFG;

        View latestMessageContainer;

        View typingContainer;

        TextView groupMemberName;

        CountBoxView unreadCountView;

        View unreadIndicator;

        ImageView muteStatus;

        ImageView hiddenStatus;

        protected AvatarView avatarView;

        protected ConversationModel conversationModel;

        AvatarListItemHolder avatarListItemHolder;

        final View tagStarOn;

        MessageListViewHolder(final View itemView) {
            super(itemView);
            tagStarOn = itemView.findViewById(R.id.tag_star_on);
            if (!ListenerUtil.mutListener.listen(9257)) {
                fromView = itemView.findViewById(R.id.from);
            }
            if (!ListenerUtil.mutListener.listen(9258)) {
                dateView = itemView.findViewById(R.id.date);
            }
            if (!ListenerUtil.mutListener.listen(9259)) {
                subjectView = itemView.findViewById(R.id.subject);
            }
            if (!ListenerUtil.mutListener.listen(9260)) {
                unreadCountView = itemView.findViewById(R.id.unread_count);
            }
            if (!ListenerUtil.mutListener.listen(9261)) {
                avatarView = itemView.findViewById(R.id.avatar_view);
            }
            if (!ListenerUtil.mutListener.listen(9262)) {
                attachmentView = itemView.findViewById(R.id.attachment);
            }
            if (!ListenerUtil.mutListener.listen(9263)) {
                deliveryView = itemView.findViewById(R.id.delivery);
            }
            if (!ListenerUtil.mutListener.listen(9264)) {
                listItemFG = itemView.findViewById(R.id.list_item_fg);
            }
            if (!ListenerUtil.mutListener.listen(9265)) {
                latestMessageContainer = itemView.findViewById(R.id.latest_message_container);
            }
            if (!ListenerUtil.mutListener.listen(9266)) {
                typingContainer = itemView.findViewById(R.id.typing_container);
            }
            if (!ListenerUtil.mutListener.listen(9267)) {
                groupMemberName = itemView.findViewById(R.id.group_member_name);
            }
            if (!ListenerUtil.mutListener.listen(9268)) {
                unreadIndicator = itemView.findViewById(R.id.unread_view);
            }
            if (!ListenerUtil.mutListener.listen(9269)) {
                muteStatus = itemView.findViewById(R.id.mute_status);
            }
            if (!ListenerUtil.mutListener.listen(9270)) {
                hiddenStatus = itemView.findViewById(R.id.hidden_status);
            }
            if (!ListenerUtil.mutListener.listen(9271)) {
                pinIcon = itemView.findViewById(R.id.pin_icon);
            }
            if (!ListenerUtil.mutListener.listen(9272)) {
                avatarListItemHolder = new AvatarListItemHolder();
            }
            if (!ListenerUtil.mutListener.listen(9273)) {
                avatarListItemHolder.avatarView = avatarView;
            }
            if (!ListenerUtil.mutListener.listen(9274)) {
                avatarListItemHolder.avatarLoadingAsyncTask = null;
            }
        }

        public View getItem() {
            return itemView;
        }

        public ConversationModel getConversationModel() {
            return conversationModel;
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface ItemClickListener {

        void onItemClick(View view, int position, ConversationModel conversationModel);

        boolean onItemLongClick(View view, int position, ConversationModel conversationModel);

        void onAvatarClick(View view, int position, ConversationModel conversationModel);

        void onFooterClick(View view);
    }

    public MessageListAdapter(Context context, ContactService contactService, GroupService groupService, DistributionListService distributionListService, ConversationService conversationService, DeadlineListService mutedChatsListService, DeadlineListService mentionOnlyChatsListService, DeadlineListService hiddenChatsListService, ConversationTagService conversationTagService, RingtoneService ringtoneService, String highlightUid, ItemClickListener clickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.contactService = contactService;
        this.groupService = groupService;
        this.conversationTagService = conversationTagService;
        this.emojiMarkupUtil = EmojiMarkupUtil.getInstance();
        this.stateBitmapUtil = StateBitmapUtil.getInstance();
        this.distributionListService = distributionListService;
        this.conversationService = conversationService;
        this.mutedChatsListService = mutedChatsListService;
        this.mentionOnlyChatsListService = mentionOnlyChatsListService;
        this.hiddenChatsListService = hiddenChatsListService;
        this.ringtoneService = ringtoneService;
        this.defaultContactImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_contact);
        this.defaultGroupImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_group);
        this.defaultDistributionListImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_distribution_list);
        if (!ListenerUtil.mutListener.listen(9275)) {
            this.highlightUid = highlightUid;
        }
        this.clickListener = clickListener;
        this.regularColor = ConfigUtils.getColorFromAttribute(context, android.R.attr.textColorSecondary);
        this.backgroundColor = ConfigUtils.getColorFromAttribute(context, android.R.attr.windowBackground);
        this.ackColor = context.getResources().getColor(R.color.material_green);
        this.decColor = context.getResources().getColor(R.color.material_orange);
        this.isTablet = ConfigUtils.isTabletLayout();
        this.starTagModel = this.conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_PIN);
        this.unreadTagModel = this.conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_UNREAD);
    }

    @Override
    public int getItemViewType(int position) {
        return (ListenerUtil.mutListener.listen(9280) ? (position <= super.getItemCount()) : (ListenerUtil.mutListener.listen(9279) ? (position > super.getItemCount()) : (ListenerUtil.mutListener.listen(9278) ? (position < super.getItemCount()) : (ListenerUtil.mutListener.listen(9277) ? (position != super.getItemCount()) : (ListenerUtil.mutListener.listen(9276) ? (position == super.getItemCount()) : (position >= super.getItemCount())))))) ? TYPE_FOOTER : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if ((ListenerUtil.mutListener.listen(9285) ? (count >= 0) : (ListenerUtil.mutListener.listen(9284) ? (count <= 0) : (ListenerUtil.mutListener.listen(9283) ? (count < 0) : (ListenerUtil.mutListener.listen(9282) ? (count != 0) : (ListenerUtil.mutListener.listen(9281) ? (count == 0) : (count > 0))))))) {
            return (ListenerUtil.mutListener.listen(9289) ? (count % 1) : (ListenerUtil.mutListener.listen(9288) ? (count / 1) : (ListenerUtil.mutListener.listen(9287) ? (count * 1) : (ListenerUtil.mutListener.listen(9286) ? (count - 1) : (count + 1)))));
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (!ListenerUtil.mutListener.listen(9297)) {
            if ((ListenerUtil.mutListener.listen(9294) ? (viewType >= TYPE_ITEM) : (ListenerUtil.mutListener.listen(9293) ? (viewType <= TYPE_ITEM) : (ListenerUtil.mutListener.listen(9292) ? (viewType > TYPE_ITEM) : (ListenerUtil.mutListener.listen(9291) ? (viewType < TYPE_ITEM) : (ListenerUtil.mutListener.listen(9290) ? (viewType != TYPE_ITEM) : (viewType == TYPE_ITEM))))))) {
                View itemView = inflater.inflate(R.layout.item_message_list, viewGroup, false);
                if (!ListenerUtil.mutListener.listen(9295)) {
                    itemView.setClickable(true);
                }
                if (!ListenerUtil.mutListener.listen(9296)) {
                    // TODO: MaterialCardView: Setting a custom background is not supported.
                    itemView.setBackgroundResource(R.drawable.listitem_background_selector);
                }
                return new MessageListViewHolder(itemView);
            }
        }
        return new FooterViewHolder(inflater.inflate(R.layout.footer_message_section, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int p) {
        if (!ListenerUtil.mutListener.listen(9467)) {
            if (h instanceof MessageListViewHolder) {
                final MessageListViewHolder holder = (MessageListViewHolder) h;
                final int position = h.getAdapterPosition();
                final ConversationModel conversationModel = this.getEntity(position);
                if (!ListenerUtil.mutListener.listen(9312)) {
                    holder.conversationModel = conversationModel;
                }
                if (!ListenerUtil.mutListener.listen(9320)) {
                    holder.itemView.setOnClickListener(new DebouncedOnClickListener(500) {

                        @Override
                        public void onDebouncedClick(View v) {
                            // position may have changed after the item was bound. query current position from holder
                            int currentPos = holder.getLayoutPosition();
                            if (!ListenerUtil.mutListener.listen(9319)) {
                                if ((ListenerUtil.mutListener.listen(9317) ? (currentPos <= 0) : (ListenerUtil.mutListener.listen(9316) ? (currentPos > 0) : (ListenerUtil.mutListener.listen(9315) ? (currentPos < 0) : (ListenerUtil.mutListener.listen(9314) ? (currentPos != 0) : (ListenerUtil.mutListener.listen(9313) ? (currentPos == 0) : (currentPos >= 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(9318)) {
                                        clickListener.onItemClick(v, currentPos, getEntity(currentPos));
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(9327)) {
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                        @Override
                        public boolean onLongClick(View v) {
                            // position may have changed after the item was bound. query current position from holder
                            int currentPos = holder.getLayoutPosition();
                            if (!ListenerUtil.mutListener.listen(9326)) {
                                if ((ListenerUtil.mutListener.listen(9325) ? (currentPos <= 0) : (ListenerUtil.mutListener.listen(9324) ? (currentPos > 0) : (ListenerUtil.mutListener.listen(9323) ? (currentPos < 0) : (ListenerUtil.mutListener.listen(9322) ? (currentPos != 0) : (ListenerUtil.mutListener.listen(9321) ? (currentPos == 0) : (currentPos >= 0))))))) {
                                    return clickListener.onItemLongClick(v, currentPos, getEntity(currentPos));
                                }
                            }
                            return false;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(9335)) {
                    holder.avatarView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // position may have changed after the item was bound. query current position from holder
                            int currentPos = holder.getLayoutPosition();
                            if (!ListenerUtil.mutListener.listen(9334)) {
                                if ((ListenerUtil.mutListener.listen(9332) ? (currentPos <= 0) : (ListenerUtil.mutListener.listen(9331) ? (currentPos > 0) : (ListenerUtil.mutListener.listen(9330) ? (currentPos < 0) : (ListenerUtil.mutListener.listen(9329) ? (currentPos != 0) : (ListenerUtil.mutListener.listen(9328) ? (currentPos == 0) : (currentPos >= 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(9333)) {
                                        clickListener.onAvatarClick(v, currentPos, getEntity(currentPos));
                                    }
                                }
                            }
                        }
                    });
                }
                // Show or hide star tag
                boolean isTagStarOn = this.conversationTagService.isTaggedWith(conversationModel, this.starTagModel);
                if (!ListenerUtil.mutListener.listen(9336)) {
                    ViewUtil.show(holder.tagStarOn, isTagStarOn);
                }
                if (!ListenerUtil.mutListener.listen(9337)) {
                    ViewUtil.show(holder.pinIcon, isTagStarOn);
                }
                AbstractMessageModel messageModel = conversationModel.getLatestMessage();
                if (!ListenerUtil.mutListener.listen(9339)) {
                    if (holder.groupMemberName != null) {
                        if (!ListenerUtil.mutListener.listen(9338)) {
                            holder.groupMemberName.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9340)) {
                    // holder.fromView.setText(from);
                    holder.fromView.setText(conversationModel.getReceiver().getDisplayName());
                }
                if (!ListenerUtil.mutListener.listen(9368)) {
                    if ((ListenerUtil.mutListener.listen(9343) ? (messageModel != null || ((ListenerUtil.mutListener.listen(9342) ? (((ListenerUtil.mutListener.listen(9341) ? (!messageModel.isOutbox() || conversationModel.hasUnreadMessage()) : (!messageModel.isOutbox() && conversationModel.hasUnreadMessage()))) && this.conversationTagService.isTaggedWith(conversationModel, this.unreadTagModel)) : (((ListenerUtil.mutListener.listen(9341) ? (!messageModel.isOutbox() || conversationModel.hasUnreadMessage()) : (!messageModel.isOutbox() && conversationModel.hasUnreadMessage()))) || this.conversationTagService.isTaggedWith(conversationModel, this.unreadTagModel))))) : (messageModel != null && ((ListenerUtil.mutListener.listen(9342) ? (((ListenerUtil.mutListener.listen(9341) ? (!messageModel.isOutbox() || conversationModel.hasUnreadMessage()) : (!messageModel.isOutbox() && conversationModel.hasUnreadMessage()))) && this.conversationTagService.isTaggedWith(conversationModel, this.unreadTagModel)) : (((ListenerUtil.mutListener.listen(9341) ? (!messageModel.isOutbox() || conversationModel.hasUnreadMessage()) : (!messageModel.isOutbox() && conversationModel.hasUnreadMessage()))) || this.conversationTagService.isTaggedWith(conversationModel, this.unreadTagModel))))))) {
                        if (!ListenerUtil.mutListener.listen(9351)) {
                            holder.fromView.setTextAppearance(context, R.style.Threema_TextAppearance_List_FirstLine_Bold);
                        }
                        if (!ListenerUtil.mutListener.listen(9352)) {
                            holder.subjectView.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine_Bold);
                        }
                        if (!ListenerUtil.mutListener.listen(9355)) {
                            if ((ListenerUtil.mutListener.listen(9353) ? (holder.groupMemberName != null || holder.dateView != null) : (holder.groupMemberName != null && holder.dateView != null))) {
                                if (!ListenerUtil.mutListener.listen(9354)) {
                                    holder.groupMemberName.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine_Bold);
                                }
                            }
                        }
                        long unreadCount = conversationModel.getUnreadCount();
                        if (!ListenerUtil.mutListener.listen(9367)) {
                            if ((ListenerUtil.mutListener.listen(9360) ? (unreadCount >= 0) : (ListenerUtil.mutListener.listen(9359) ? (unreadCount <= 0) : (ListenerUtil.mutListener.listen(9358) ? (unreadCount < 0) : (ListenerUtil.mutListener.listen(9357) ? (unreadCount != 0) : (ListenerUtil.mutListener.listen(9356) ? (unreadCount == 0) : (unreadCount > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(9364)) {
                                    holder.unreadCountView.setText(String.valueOf(unreadCount));
                                }
                                if (!ListenerUtil.mutListener.listen(9365)) {
                                    holder.unreadCountView.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9366)) {
                                    holder.unreadIndicator.setVisibility(View.VISIBLE);
                                }
                            } else if (this.conversationTagService.isTaggedWith(conversationModel, this.unreadTagModel)) {
                                if (!ListenerUtil.mutListener.listen(9361)) {
                                    holder.unreadCountView.setText("");
                                }
                                if (!ListenerUtil.mutListener.listen(9362)) {
                                    holder.unreadCountView.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9363)) {
                                    holder.unreadIndicator.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9344)) {
                            holder.fromView.setTextAppearance(context, R.style.Threema_TextAppearance_List_FirstLine);
                        }
                        if (!ListenerUtil.mutListener.listen(9345)) {
                            holder.subjectView.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine);
                        }
                        if (!ListenerUtil.mutListener.listen(9348)) {
                            if ((ListenerUtil.mutListener.listen(9346) ? (holder.groupMemberName != null || holder.dateView != null) : (holder.groupMemberName != null && holder.dateView != null))) {
                                if (!ListenerUtil.mutListener.listen(9347)) {
                                    holder.groupMemberName.setTextAppearance(context, R.style.Threema_TextAppearance_List_SecondLine);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9349)) {
                            holder.unreadCountView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9350)) {
                            holder.unreadIndicator.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9369)) {
                    holder.deliveryView.setColorFilter(this.regularColor);
                }
                if (!ListenerUtil.mutListener.listen(9370)) {
                    holder.attachmentView.setColorFilter(this.regularColor);
                }
                if (!ListenerUtil.mutListener.listen(9371)) {
                    holder.muteStatus.setColorFilter(this.regularColor);
                }
                if (!ListenerUtil.mutListener.listen(9372)) {
                    holder.dateView.setTextAppearance(context, R.style.Threema_TextAppearance_List_ThirdLine);
                }
                if (!ListenerUtil.mutListener.listen(9373)) {
                    holder.subjectView.setVisibility(View.VISIBLE);
                }
                String uniqueId = conversationModel.getReceiver().getUniqueIdString();
                if (!ListenerUtil.mutListener.listen(9454)) {
                    if (messageModel != null) {
                        if (!ListenerUtil.mutListener.listen(9444)) {
                            if (hiddenChatsListService.has(uniqueId)) {
                                if (!ListenerUtil.mutListener.listen(9439)) {
                                    holder.hiddenStatus.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9440)) {
                                    // give user some privacy even in visible mode
                                    holder.subjectView.setText(R.string.private_chat_subject);
                                }
                                if (!ListenerUtil.mutListener.listen(9441)) {
                                    holder.attachmentView.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(9442)) {
                                    holder.dateView.setVisibility(View.INVISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(9443)) {
                                    holder.deliveryView.setVisibility(View.GONE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9383)) {
                                    holder.hiddenStatus.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(9384)) {
                                    holder.dateView.setText(MessageUtil.getDisplayDate(this.context, messageModel, false));
                                }
                                if (!ListenerUtil.mutListener.listen(9385)) {
                                    holder.dateView.setContentDescription("." + context.getString(R.string.state_dialog_modified) + "." + holder.dateView.getText() + ".");
                                }
                                if (!ListenerUtil.mutListener.listen(9386)) {
                                    holder.dateView.setVisibility(View.VISIBLE);
                                }
                                String draft = ThreemaApplication.getMessageDraft(uniqueId);
                                if (!ListenerUtil.mutListener.listen(9438)) {
                                    if (!TestUtil.empty(draft)) {
                                        if (!ListenerUtil.mutListener.listen(9430)) {
                                            holder.groupMemberName.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9431)) {
                                            holder.attachmentView.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9432)) {
                                            holder.deliveryView.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9433)) {
                                            holder.dateView.setText(" " + context.getString(R.string.draft));
                                        }
                                        if (!ListenerUtil.mutListener.listen(9434)) {
                                            holder.dateView.setContentDescription(null);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9435)) {
                                            holder.dateView.setTextAppearance(context, R.style.Threema_TextAppearance_List_ThirdLine_Bold);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9436)) {
                                            holder.dateView.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9437)) {
                                            holder.subjectView.setText(emojiMarkupUtil.formatBodyTextString(context, draft + " ", 100));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(9390)) {
                                            if (conversationModel.isGroupConversation()) {
                                                if (!ListenerUtil.mutListener.listen(9389)) {
                                                    if (holder.groupMemberName != null) {
                                                        if (!ListenerUtil.mutListener.listen(9387)) {
                                                            holder.groupMemberName.setText(NameUtil.getShortName(this.context, messageModel, this.contactService) + ": ");
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(9388)) {
                                                            holder.groupMemberName.setVisibility(View.VISIBLE);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        // Configure subject
                                        MessageUtil.MessageViewElement viewElement = MessageUtil.getViewElement(this.context, messageModel);
                                        String subject = viewElement.text;
                                        if (!ListenerUtil.mutListener.listen(9392)) {
                                            if (messageModel.getType() == MessageType.TEXT) {
                                                if (!ListenerUtil.mutListener.listen(9391)) {
                                                    // we need to add an arbitrary character - otherwise span-only strings are formatted incorrectly in the item layout
                                                    subject += " ";
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(9401)) {
                                            if (viewElement.icon != null) {
                                                if (!ListenerUtil.mutListener.listen(9394)) {
                                                    holder.attachmentView.setVisibility(View.VISIBLE);
                                                }
                                                if (!ListenerUtil.mutListener.listen(9395)) {
                                                    holder.attachmentView.setImageResource(viewElement.icon);
                                                }
                                                if (!ListenerUtil.mutListener.listen(9398)) {
                                                    if (viewElement.placeholder != null) {
                                                        if (!ListenerUtil.mutListener.listen(9397)) {
                                                            holder.attachmentView.setContentDescription(viewElement.placeholder);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(9396)) {
                                                            holder.attachmentView.setContentDescription("");
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(9400)) {
                                                    // Configure color of the attachment view
                                                    if (viewElement.color != null) {
                                                        if (!ListenerUtil.mutListener.listen(9399)) {
                                                            holder.attachmentView.setColorFilter(this.context.getResources().getColor(viewElement.color), PorterDuff.Mode.SRC_IN);
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(9393)) {
                                                    holder.attachmentView.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(9408)) {
                                            if (TestUtil.empty(subject)) {
                                                if (!ListenerUtil.mutListener.listen(9406)) {
                                                    holder.subjectView.setText("");
                                                }
                                                if (!ListenerUtil.mutListener.listen(9407)) {
                                                    holder.subjectView.setContentDescription("");
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(9403)) {
                                                    // Append space if attachmentView is visible
                                                    if (holder.attachmentView.getVisibility() == View.VISIBLE) {
                                                        if (!ListenerUtil.mutListener.listen(9402)) {
                                                            subject = " " + subject;
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(9404)) {
                                                    holder.subjectView.setText(emojiMarkupUtil.formatBodyTextString(context, subject, 100));
                                                }
                                                if (!ListenerUtil.mutListener.listen(9405)) {
                                                    holder.subjectView.setContentDescription(viewElement.contentDescription);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(9419)) {
                                            // Special icons for voice call message
                                            if (messageModel.getType() == MessageType.VOIP_STATUS) {
                                                if (!ListenerUtil.mutListener.listen(9418)) {
                                                    // Always show the phone icon
                                                    holder.deliveryView.setImageResource(R.drawable.ic_phone_locked);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(9417)) {
                                                    if (!messageModel.isOutbox()) {
                                                        if (!ListenerUtil.mutListener.listen(9410)) {
                                                            holder.deliveryView.setImageResource(R.drawable.ic_reply_filled);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(9411)) {
                                                            holder.deliveryView.setContentDescription(context.getString(R.string.state_sent));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(9415)) {
                                                            if (messageModel.getState() != null) {
                                                                if (!ListenerUtil.mutListener.listen(9414)) {
                                                                    switch(messageModel.getState()) {
                                                                        case USERACK:
                                                                            if (!ListenerUtil.mutListener.listen(9412)) {
                                                                                holder.deliveryView.setColorFilter(this.ackColor);
                                                                            }
                                                                            break;
                                                                        case USERDEC:
                                                                            if (!ListenerUtil.mutListener.listen(9413)) {
                                                                                holder.deliveryView.setColorFilter(this.decColor);
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(9416)) {
                                                            holder.deliveryView.setVisibility(View.VISIBLE);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(9409)) {
                                                            stateBitmapUtil.setStateDrawable(messageModel, holder.deliveryView, false);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(9429)) {
                                            if (conversationModel.isGroupConversation()) {
                                                if (!ListenerUtil.mutListener.listen(9427)) {
                                                    if (groupService.isNotesGroup(conversationModel.getGroup())) {
                                                        if (!ListenerUtil.mutListener.listen(9425)) {
                                                            holder.deliveryView.setImageResource(R.drawable.ic_spiral_bound_booklet_outline);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(9426)) {
                                                            holder.deliveryView.setContentDescription(context.getString(R.string.notes));
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(9423)) {
                                                            holder.deliveryView.setImageResource(R.drawable.ic_group_filled);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(9424)) {
                                                            holder.deliveryView.setContentDescription(context.getString(R.string.prefs_group_notifications));
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(9428)) {
                                                    holder.deliveryView.setVisibility(View.VISIBLE);
                                                }
                                            } else if (conversationModel.isDistributionListConversation()) {
                                                if (!ListenerUtil.mutListener.listen(9420)) {
                                                    holder.deliveryView.setImageResource(R.drawable.ic_distribution_list_filled);
                                                }
                                                if (!ListenerUtil.mutListener.listen(9421)) {
                                                    holder.deliveryView.setContentDescription(context.getString(R.string.distribution_list));
                                                }
                                                if (!ListenerUtil.mutListener.listen(9422)) {
                                                    holder.deliveryView.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9453)) {
                            if (mutedChatsListService.has(uniqueId)) {
                                if (!ListenerUtil.mutListener.listen(9451)) {
                                    holder.muteStatus.setImageResource(R.drawable.ic_do_not_disturb_filled);
                                }
                                if (!ListenerUtil.mutListener.listen(9452)) {
                                    holder.muteStatus.setVisibility(View.VISIBLE);
                                }
                            } else if (mentionOnlyChatsListService.has(uniqueId)) {
                                if (!ListenerUtil.mutListener.listen(9449)) {
                                    holder.muteStatus.setImageResource(R.drawable.ic_dnd_mention_black_18dp);
                                }
                                if (!ListenerUtil.mutListener.listen(9450)) {
                                    holder.muteStatus.setVisibility(View.VISIBLE);
                                }
                            } else if ((ListenerUtil.mutListener.listen(9445) ? (ringtoneService.hasCustomRingtone(uniqueId) || ringtoneService.isSilent(uniqueId, conversationModel.isGroupConversation())) : (ringtoneService.hasCustomRingtone(uniqueId) && ringtoneService.isSilent(uniqueId, conversationModel.isGroupConversation())))) {
                                if (!ListenerUtil.mutListener.listen(9447)) {
                                    holder.muteStatus.setImageResource(R.drawable.ic_notifications_off_filled);
                                }
                                if (!ListenerUtil.mutListener.listen(9448)) {
                                    holder.muteStatus.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9446)) {
                                    holder.muteStatus.setVisibility(View.GONE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9374)) {
                            // empty chat
                            holder.attachmentView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9375)) {
                            holder.deliveryView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9376)) {
                            holder.dateView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9377)) {
                            holder.dateView.setContentDescription(null);
                        }
                        if (!ListenerUtil.mutListener.listen(9378)) {
                            holder.subjectView.setText("");
                        }
                        if (!ListenerUtil.mutListener.listen(9379)) {
                            holder.subjectView.setContentDescription("");
                        }
                        if (!ListenerUtil.mutListener.listen(9380)) {
                            holder.muteStatus.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9382)) {
                            holder.hiddenStatus.setVisibility((ListenerUtil.mutListener.listen(9381) ? (uniqueId != null || hiddenChatsListService.has(uniqueId)) : (uniqueId != null && hiddenChatsListService.has(uniqueId))) ? View.VISIBLE : View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9455)) {
                    AdapterUtil.styleConversation(holder.fromView, groupService, conversationModel);
                }
                if (!ListenerUtil.mutListener.listen(9456)) {
                    // load avatars asynchronously
                    AvatarListItemUtil.loadAvatar(position, conversationModel, this.defaultContactImage, this.defaultGroupImage, this.defaultDistributionListImage, this.contactService, this.groupService, this.distributionListService, holder.avatarListItemHolder);
                }
                if (!ListenerUtil.mutListener.listen(9457)) {
                    this.updateTypingIndicator(holder, conversationModel.isTyping());
                }
                if (!ListenerUtil.mutListener.listen(9458)) {
                    holder.itemView.setActivated(selectedChats.contains(conversationModel));
                }
                if (!ListenerUtil.mutListener.listen(9466)) {
                    if (isTablet) {
                        if (!ListenerUtil.mutListener.listen(9465)) {
                            // handle selection in multi-pane mode
                            if ((ListenerUtil.mutListener.listen(9460) ? ((ListenerUtil.mutListener.listen(9459) ? (highlightUid != null || highlightUid.equals(conversationModel.getUid())) : (highlightUid != null && highlightUid.equals(conversationModel.getUid()))) || context instanceof ComposeMessageActivity) : ((ListenerUtil.mutListener.listen(9459) ? (highlightUid != null || highlightUid.equals(conversationModel.getUid())) : (highlightUid != null && highlightUid.equals(conversationModel.getUid()))) && context instanceof ComposeMessageActivity))) {
                                if (!ListenerUtil.mutListener.listen(9464)) {
                                    if (ConfigUtils.getAppTheme(context) == ConfigUtils.THEME_DARK) {
                                        if (!ListenerUtil.mutListener.listen(9463)) {
                                            holder.listItemFG.setBackgroundResource(R.color.settings_multipane_selection_bg_dark);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(9462)) {
                                            holder.listItemFG.setBackgroundResource(R.color.settings_multipane_selection_bg_light);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9461)) {
                                    holder.listItemFG.setBackgroundColor(this.backgroundColor);
                                }
                            }
                        }
                    }
                }
            } else {
                // footer
                Chip archivedChip = h.itemView.findViewById(R.id.archived_text);
                int archivedCount = conversationService.getArchivedCount();
                if (!ListenerUtil.mutListener.listen(9311)) {
                    if ((ListenerUtil.mutListener.listen(9302) ? (archivedCount >= 0) : (ListenerUtil.mutListener.listen(9301) ? (archivedCount <= 0) : (ListenerUtil.mutListener.listen(9300) ? (archivedCount < 0) : (ListenerUtil.mutListener.listen(9299) ? (archivedCount != 0) : (ListenerUtil.mutListener.listen(9298) ? (archivedCount == 0) : (archivedCount > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(9306)) {
                            archivedChip.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(9307)) {
                            archivedChip.setOnClickListener(v -> clickListener.onFooterClick(v));
                        }
                        if (!ListenerUtil.mutListener.listen(9308)) {
                            archivedChip.setText(String.format(context.getString(R.string.num_archived_chats), archivedCount));
                        }
                        if (!ListenerUtil.mutListener.listen(9310)) {
                            if (recyclerView != null) {
                                if (!ListenerUtil.mutListener.listen(9309)) {
                                    ((EmptyRecyclerView) recyclerView).setNumHeadersAndFooters(0);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9303)) {
                            archivedChip.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(9305)) {
                            if (recyclerView != null) {
                                if (!ListenerUtil.mutListener.listen(9304)) {
                                    ((EmptyRecyclerView) recyclerView).setNumHeadersAndFooters(1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(9468)) {
            super.onAttachedToRecyclerView(recyclerView);
        }
        if (!ListenerUtil.mutListener.listen(9469)) {
            this.recyclerView = recyclerView;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(9470)) {
            this.recyclerView = null;
        }
        if (!ListenerUtil.mutListener.listen(9471)) {
            super.onDetachedFromRecyclerView(recyclerView);
        }
    }

    public void toggleItemChecked(ConversationModel model, int position) {
        if (!ListenerUtil.mutListener.listen(9479)) {
            if (selectedChats.contains(model)) {
                if (!ListenerUtil.mutListener.listen(9478)) {
                    selectedChats.remove(model);
                }
            } else if ((ListenerUtil.mutListener.listen(9476) ? (selectedChats.size() >= MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(9475) ? (selectedChats.size() > MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(9474) ? (selectedChats.size() < MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(9473) ? (selectedChats.size() != MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(9472) ? (selectedChats.size() == MAX_SELECTED_ITEMS) : (selectedChats.size() <= MAX_SELECTED_ITEMS))))))) {
                if (!ListenerUtil.mutListener.listen(9477)) {
                    selectedChats.add(model);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9480)) {
            notifyItemChanged(position);
        }
    }

    public void clearSelections() {
        if (!ListenerUtil.mutListener.listen(9481)) {
            selectedChats.clear();
        }
        if (!ListenerUtil.mutListener.listen(9482)) {
            notifyDataSetChanged();
        }
    }

    public int getCheckedItemCount() {
        return selectedChats.size();
    }

    public void refreshFooter() {
        if (!ListenerUtil.mutListener.listen(9487)) {
            notifyItemChanged((ListenerUtil.mutListener.listen(9486) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(9485) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(9484) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(9483) ? (getItemCount() + 1) : (getItemCount() - 1))))));
        }
    }

    public List<ConversationModel> getCheckedItems() {
        return selectedChats;
    }

    public void setHighlightItem(String uid) {
        if (!ListenerUtil.mutListener.listen(9488)) {
            highlightUid = uid;
        }
    }

    private void updateTypingIndicator(MessageListViewHolder holder, boolean isTyping) {
        if (!ListenerUtil.mutListener.listen(9493)) {
            if ((ListenerUtil.mutListener.listen(9490) ? ((ListenerUtil.mutListener.listen(9489) ? (holder != null || holder.latestMessageContainer != null) : (holder != null && holder.latestMessageContainer != null)) || holder.typingContainer != null) : ((ListenerUtil.mutListener.listen(9489) ? (holder != null || holder.latestMessageContainer != null) : (holder != null && holder.latestMessageContainer != null)) && holder.typingContainer != null))) {
                if (!ListenerUtil.mutListener.listen(9491)) {
                    holder.latestMessageContainer.setVisibility(isTyping ? View.GONE : View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(9492)) {
                    holder.typingContainer.setVisibility(!isTyping ? View.GONE : View.VISIBLE);
                }
            }
        }
    }
}
