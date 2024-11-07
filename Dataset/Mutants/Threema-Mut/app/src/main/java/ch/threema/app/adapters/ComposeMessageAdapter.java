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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.adapters.decorators.AnimGifChatAdapterDecorator;
import ch.threema.app.adapters.decorators.AudioChatAdapterDecorator;
import ch.threema.app.adapters.decorators.BallotChatAdapterDecorator;
import ch.threema.app.adapters.decorators.ChatAdapterDecorator;
import ch.threema.app.adapters.decorators.DateSeparatorChatAdapterDecorator;
import ch.threema.app.adapters.decorators.FileChatAdapterDecorator;
import ch.threema.app.adapters.decorators.FirstUnreadChatAdapterDecorator;
import ch.threema.app.adapters.decorators.ImageChatAdapterDecorator;
import ch.threema.app.adapters.decorators.LocationChatAdapterDecorator;
import ch.threema.app.adapters.decorators.StatusChatAdapterDecorator;
import ch.threema.app.adapters.decorators.TextChatAdapterDecorator;
import ch.threema.app.adapters.decorators.VideoChatAdapterDecorator;
import ch.threema.app.adapters.decorators.VoipStatusDataChatAdapterDecorator;
import ch.threema.app.cache.ThumbnailCache;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.emojis.EmojiMarkupUtil;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DownloadService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.messageplayer.MessagePlayerService;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DateSeparatorMessageModel;
import ch.threema.storage.models.FirstUnreadMessageModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ComposeMessageAdapter extends ArrayAdapter<AbstractMessageModel> {

    private static final Logger logger = LoggerFactory.getLogger(ComposeMessageAdapter.class);

    private final List<AbstractMessageModel> values;

    private final ChatAdapterDecorator.Helper decoratorHelper;

    private final MessageService messageService;

    private final UserService userService;

    private final FileService fileService;

    private final SparseIntArray resultMap = new SparseIntArray();

    private int resultMapIndex;

    private ConversationListFilter convListFilter = new ConversationListFilter();

    public ListView listView;

    private int groupId;

    private final EmojiMarkupUtil emojiMarkupUtil = EmojiMarkupUtil.getInstance();

    private CharSequence currentConstraint = "";

    private int firstUnreadPos = -1, unreadMessagesCount;

    private final Context context;

    private final LayoutInflater layoutInflater;

    public static final int TYPE_SEND = 0;

    public static final int TYPE_RECV = 1;

    public static final int TYPE_STATUS = 2;

    public static final int TYPE_FIRST_UNREAD = 3;

    public static final int TYPE_MEDIA_SEND = 4;

    public static final int TYPE_MEDIA_RECV = 5;

    public static final int TYPE_LOCATION_SEND = 6;

    public static final int TYPE_LOCATION_RECV = 7;

    public static final int TYPE_AUDIO_SEND = 8;

    public static final int TYPE_AUDIO_RECV = 9;

    public static final int TYPE_FILE_SEND = 10;

    public static final int TYPE_FILE_RECV = 11;

    public static final int TYPE_BALLOT_SEND = 12;

    public static final int TYPE_BALLOT_RECV = 13;

    public static final int TYPE_ANIMGIF_SEND = 14;

    public static final int TYPE_ANIMGIF_RECV = 15;

    public static final int TYPE_TEXT_QUOTE_SEND = 16;

    public static final int TYPE_TEXT_QUOTE_RECV = 17;

    public static final int TYPE_STATUS_DATA_SEND = 18;

    public static final int TYPE_STATUS_DATA_RECV = 19;

    public static final int TYPE_DATE_SEPARATOR = 20;

    public static final int TYPE_FILE_MEDIA_SEND = 21;

    public static final int TYPE_FILE_MEDIA_RECV = 22;

    public static final int TYPE_FILE_VIDEO_SEND = 23;

    // don't forget to update this after adding new types:
    private static final int TYPE_MAX_COUNT = TYPE_FILE_VIDEO_SEND + 1;

    private OnClickListener onClickListener;

    private Map<String, Integer> identityColors = null;

    public interface OnClickListener {

        void resend(AbstractMessageModel messageModel);

        void click(View view, int position, AbstractMessageModel messageModel);

        void longClick(View view, int position, AbstractMessageModel messageModel);

        boolean touch(View view, MotionEvent motionEvent, AbstractMessageModel messageModel);

        void avatarClick(View view, int position, AbstractMessageModel messageModel);

        void onSearchResultsUpdate(int searchResultsIndex, int searchResultsSize);

        void onSearchInProgress(boolean inProgress);
    }

    public ComposeMessageAdapter(Context context, MessagePlayerService messagePlayerService, List<AbstractMessageModel> values, UserService userService, ContactService contactService, FileService fileService, MessageService messageService, BallotService ballotService, PreferenceService preferenceService, DownloadService downloadService, LicenseService licenseService, MessageReceiver messageReceiver, ListView listView, ThumbnailCache<?> thumbnailCache, int thumbnailWidth, Fragment fragment, int unreadMessagesCount) {
        super(context, R.layout.conversation_list_item_send, values);
        this.context = context;
        this.values = values;
        if (!ListenerUtil.mutListener.listen(8217)) {
            this.listView = listView;
        }
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int regularColor;
        if (ConfigUtils.getAppTheme(context) != ConfigUtils.THEME_LIGHT) {
            regularColor = context.getResources().getColor(R.color.dark_text_color_secondary);
        } else {
            regularColor = context.getResources().getColor(R.color.text_color_secondary);
        }
        Drawable stopwatchIcon = ConfigUtils.getThemedDrawable(getContext(), R.drawable.ic_av_timer_grey600_18dp);
        int maxBubbleTextLength = context.getResources().getInteger(R.integer.max_bubble_text_length);
        int maxQuoteTextLength = context.getResources().getInteger(R.integer.max_quote_text_length);
        if (!ListenerUtil.mutListener.listen(8218)) {
            this.resultMapIndex = 0;
        }
        if (!ListenerUtil.mutListener.listen(8219)) {
            this.unreadMessagesCount = unreadMessagesCount;
        }
        this.messageService = messageService;
        this.userService = userService;
        this.fileService = fileService;
        this.decoratorHelper = new ChatAdapterDecorator.Helper(userService.getIdentity(), messageService, userService, contactService, fileService, messagePlayerService, ballotService, thumbnailCache, preferenceService, downloadService, licenseService, messageReceiver, thumbnailWidth, fragment, regularColor, stopwatchIcon, maxBubbleTextLength, maxQuoteTextLength);
    }

    /**
     *  remove the contact saved stuff and update the list
     *  @param contactModel
     */
    @UiThread
    public void resetCachedContactModelData(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(8223)) {
            if ((ListenerUtil.mutListener.listen(8220) ? (contactModel != null || this.decoratorHelper != null) : (contactModel != null && this.decoratorHelper != null))) {
                if (!ListenerUtil.mutListener.listen(8222)) {
                    if (this.decoratorHelper.getContactCache().remove(contactModel.getIdentity()) != null) {
                        if (!ListenerUtil.mutListener.listen(8221)) {
                            notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }

    public void setGroupId(int groupId) {
        if (!ListenerUtil.mutListener.listen(8224)) {
            this.groupId = groupId;
        }
    }

    public void setMessageReceiver(MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(8226)) {
            if (this.decoratorHelper != null) {
                if (!ListenerUtil.mutListener.listen(8225)) {
                    this.decoratorHelper.setMessageReceiver(messageReceiver);
                }
            }
        }
    }

    public void setThumbnailWidth(int preferredThumbnailWidth) {
        if (!ListenerUtil.mutListener.listen(8228)) {
            if (this.decoratorHelper != null) {
                if (!ListenerUtil.mutListener.listen(8227)) {
                    this.decoratorHelper.setThumbnailWidth(preferredThumbnailWidth);
                }
            }
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        if (!ListenerUtil.mutListener.listen(8229)) {
            this.onClickListener = onClickListener;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!ListenerUtil.mutListener.listen(8235)) {
            if ((ListenerUtil.mutListener.listen(8234) ? (position >= values.size()) : (ListenerUtil.mutListener.listen(8233) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(8232) ? (position > values.size()) : (ListenerUtil.mutListener.listen(8231) ? (position != values.size()) : (ListenerUtil.mutListener.listen(8230) ? (position == values.size()) : (position < values.size()))))))) {
                final AbstractMessageModel m = this.getItem(position);
                return this.getType(m);
            }
        }
        return TYPE_STATUS;
    }

    @Nullable
    @Override
    public AbstractMessageModel getItem(int position) {
        if (!ListenerUtil.mutListener.listen(8241)) {
            if ((ListenerUtil.mutListener.listen(8240) ? (position >= values.size()) : (ListenerUtil.mutListener.listen(8239) ? (position <= values.size()) : (ListenerUtil.mutListener.listen(8238) ? (position > values.size()) : (ListenerUtil.mutListener.listen(8237) ? (position != values.size()) : (ListenerUtil.mutListener.listen(8236) ? (position == values.size()) : (position < values.size()))))))) {
                return super.getItem(position);
            }
        }
        return null;
    }

    private int getType(AbstractMessageModel m) {
        if (!ListenerUtil.mutListener.listen(8249)) {
            if (m != null) {
                if (!ListenerUtil.mutListener.listen(8248)) {
                    if (m.isStatusMessage()) {
                        // Special handling for data status messages
                        return m instanceof FirstUnreadMessageModel ? TYPE_FIRST_UNREAD : m instanceof DateSeparatorMessageModel ? TYPE_DATE_SEPARATOR : TYPE_STATUS;
                    } else {
                        boolean o = m.isOutbox();
                        if (!ListenerUtil.mutListener.listen(8247)) {
                            switch(m.getType()) {
                                case LOCATION:
                                    return o ? TYPE_LOCATION_SEND : TYPE_LOCATION_RECV;
                                case IMAGE:
                                    return o ? TYPE_MEDIA_SEND : TYPE_MEDIA_RECV;
                                case VIDEO:
                                    return o ? TYPE_MEDIA_SEND : TYPE_MEDIA_RECV;
                                case VOICEMESSAGE:
                                    return o ? TYPE_AUDIO_SEND : TYPE_AUDIO_RECV;
                                case FILE:
                                    String mimeType = m.getFileData().getMimeType();
                                    int renderingType = m.getFileData().getRenderingType();
                                    if (!ListenerUtil.mutListener.listen(8245)) {
                                        if (MimeUtil.isGifFile(mimeType)) {
                                            return o ? TYPE_ANIMGIF_SEND : TYPE_ANIMGIF_RECV;
                                        } else if ((ListenerUtil.mutListener.listen(8242) ? (MimeUtil.isAudioFile(mimeType) || renderingType == FileData.RENDERING_MEDIA) : (MimeUtil.isAudioFile(mimeType) && renderingType == FileData.RENDERING_MEDIA))) {
                                            return o ? TYPE_AUDIO_SEND : TYPE_AUDIO_RECV;
                                        } else if ((ListenerUtil.mutListener.listen(8243) ? (renderingType == FileData.RENDERING_MEDIA && renderingType == FileData.RENDERING_STICKER) : (renderingType == FileData.RENDERING_MEDIA || renderingType == FileData.RENDERING_STICKER))) {
                                            if (!ListenerUtil.mutListener.listen(8244)) {
                                                if (MimeUtil.isImageFile(mimeType)) {
                                                    return o ? TYPE_FILE_MEDIA_SEND : TYPE_FILE_MEDIA_RECV;
                                                } else if (MimeUtil.isVideoFile(mimeType)) {
                                                    return o ? TYPE_FILE_VIDEO_SEND : TYPE_FILE_MEDIA_RECV;
                                                }
                                            }
                                        }
                                    }
                                    return o ? TYPE_FILE_SEND : TYPE_FILE_RECV;
                                case BALLOT:
                                    return o ? TYPE_BALLOT_SEND : TYPE_BALLOT_RECV;
                                case VOIP_STATUS:
                                    return o ? TYPE_STATUS_DATA_SEND : TYPE_STATUS_DATA_RECV;
                                default:
                                    if (!ListenerUtil.mutListener.listen(8246)) {
                                        if (QuoteUtil.getQuoteType(m) != QuoteUtil.QUOTE_TYPE_NONE) {
                                            return o ? TYPE_TEXT_QUOTE_SEND : TYPE_TEXT_QUOTE_RECV;
                                        }
                                    }
                                    return o ? TYPE_SEND : TYPE_RECV;
                            }
                        }
                    }
                }
            }
        }
        return TYPE_RECV;
    }

    private int getLayoutByType(int typeId) {
        if (!ListenerUtil.mutListener.listen(8250)) {
            switch(typeId) {
                case TYPE_SEND:
                    return R.layout.conversation_list_item_send;
                case TYPE_RECV:
                    return R.layout.conversation_list_item_recv;
                case TYPE_STATUS:
                    return R.layout.conversation_list_item_status;
                case TYPE_FIRST_UNREAD:
                    return R.layout.conversation_list_item_unread;
                case TYPE_MEDIA_SEND:
                case TYPE_FILE_MEDIA_SEND:
                    return R.layout.conversation_list_item_media_send;
                case TYPE_MEDIA_RECV:
                case TYPE_FILE_MEDIA_RECV:
                    return R.layout.conversation_list_item_media_recv;
                case TYPE_FILE_VIDEO_SEND:
                    return R.layout.conversation_list_item_video_send;
                case TYPE_LOCATION_SEND:
                    return R.layout.conversation_list_item_location_send;
                case TYPE_LOCATION_RECV:
                    return R.layout.conversation_list_item_location_recv;
                case TYPE_AUDIO_SEND:
                    return R.layout.conversation_list_item_audio_send;
                case TYPE_AUDIO_RECV:
                    return R.layout.conversation_list_item_audio_recv;
                case TYPE_FILE_SEND:
                    return R.layout.conversation_list_item_file_send;
                case TYPE_FILE_RECV:
                    return R.layout.conversation_list_item_file_recv;
                case TYPE_BALLOT_SEND:
                    return R.layout.conversation_list_item_ballot_send;
                case TYPE_BALLOT_RECV:
                    return R.layout.conversation_list_item_ballot_recv;
                case TYPE_ANIMGIF_SEND:
                    return R.layout.conversation_list_item_animgif_send;
                case TYPE_ANIMGIF_RECV:
                    return R.layout.conversation_list_item_animgif_recv;
                case TYPE_TEXT_QUOTE_SEND:
                    return R.layout.conversation_list_item_quote_send;
                case TYPE_TEXT_QUOTE_RECV:
                    return R.layout.conversation_list_item_quote_recv;
                case TYPE_STATUS_DATA_SEND:
                    return R.layout.conversation_list_item_voip_status_send;
                case TYPE_STATUS_DATA_RECV:
                    return R.layout.conversation_list_item_voip_status_recv;
                case TYPE_DATE_SEPARATOR:
                    return R.layout.conversation_list_item_date_separator;
            }
        }
        // return default!?
        return R.layout.conversation_list_item_recv;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @NonNull
    @SuppressLint("WrongViewCast")
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        final ComposeMessageHolder holder;
        final AbstractMessageModel messageModel = values.get(position);
        final MessageType messageType = messageModel.getType();
        int itemType = this.getType(messageModel);
        int itemLayout = this.getLayoutByType(itemType);
        if (!ListenerUtil.mutListener.listen(8253)) {
            if ((ListenerUtil.mutListener.listen(8251) ? (messageModel.isStatusMessage() || messageModel instanceof FirstUnreadMessageModel) : (messageModel.isStatusMessage() && messageModel instanceof FirstUnreadMessageModel))) {
                if (!ListenerUtil.mutListener.listen(8252)) {
                    firstUnreadPos = position;
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(8259) ? ((convertView == null) && ((ListenerUtil.mutListener.listen(8258) ? (getItemViewType(position) >= itemType) : (ListenerUtil.mutListener.listen(8257) ? (getItemViewType(position) <= itemType) : (ListenerUtil.mutListener.listen(8256) ? (getItemViewType(position) > itemType) : (ListenerUtil.mutListener.listen(8255) ? (getItemViewType(position) < itemType) : (ListenerUtil.mutListener.listen(8254) ? (getItemViewType(position) == itemType) : (getItemViewType(position) != itemType)))))))) : ((convertView == null) || ((ListenerUtil.mutListener.listen(8258) ? (getItemViewType(position) >= itemType) : (ListenerUtil.mutListener.listen(8257) ? (getItemViewType(position) <= itemType) : (ListenerUtil.mutListener.listen(8256) ? (getItemViewType(position) > itemType) : (ListenerUtil.mutListener.listen(8255) ? (getItemViewType(position) < itemType) : (ListenerUtil.mutListener.listen(8254) ? (getItemViewType(position) == itemType) : (getItemViewType(position) != itemType)))))))))) {
            // this is a new view or the ListView item type (and thus the layout) has changed
            holder = new ComposeMessageHolder();
            if (!ListenerUtil.mutListener.listen(8283)) {
                itemView = this.layoutInflater.inflate(itemLayout, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(8324)) {
                if (itemView != null) {
                    if (!ListenerUtil.mutListener.listen(8284)) {
                        holder.bodyTextView = itemView.findViewById(R.id.text_view);
                    }
                    if (!ListenerUtil.mutListener.listen(8285)) {
                        holder.messageBlockView = itemView.findViewById(R.id.message_block);
                    }
                    if (!ListenerUtil.mutListener.listen(8322)) {
                        if ((ListenerUtil.mutListener.listen(8302) ? ((ListenerUtil.mutListener.listen(8296) ? ((ListenerUtil.mutListener.listen(8290) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8289) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8288) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8287) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8286) ? (itemType == TYPE_STATUS) : (itemType != TYPE_STATUS)))))) || (ListenerUtil.mutListener.listen(8295) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8294) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8293) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8292) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8291) ? (itemType == TYPE_FIRST_UNREAD) : (itemType != TYPE_FIRST_UNREAD))))))) : ((ListenerUtil.mutListener.listen(8290) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8289) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8288) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8287) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8286) ? (itemType == TYPE_STATUS) : (itemType != TYPE_STATUS)))))) && (ListenerUtil.mutListener.listen(8295) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8294) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8293) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8292) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8291) ? (itemType == TYPE_FIRST_UNREAD) : (itemType != TYPE_FIRST_UNREAD)))))))) || (ListenerUtil.mutListener.listen(8301) ? (itemType >= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8300) ? (itemType <= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8299) ? (itemType > TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8298) ? (itemType < TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8297) ? (itemType == TYPE_DATE_SEPARATOR) : (itemType != TYPE_DATE_SEPARATOR))))))) : ((ListenerUtil.mutListener.listen(8296) ? ((ListenerUtil.mutListener.listen(8290) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8289) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8288) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8287) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8286) ? (itemType == TYPE_STATUS) : (itemType != TYPE_STATUS)))))) || (ListenerUtil.mutListener.listen(8295) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8294) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8293) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8292) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8291) ? (itemType == TYPE_FIRST_UNREAD) : (itemType != TYPE_FIRST_UNREAD))))))) : ((ListenerUtil.mutListener.listen(8290) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8289) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8288) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8287) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8286) ? (itemType == TYPE_STATUS) : (itemType != TYPE_STATUS)))))) && (ListenerUtil.mutListener.listen(8295) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8294) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8293) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8292) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8291) ? (itemType == TYPE_FIRST_UNREAD) : (itemType != TYPE_FIRST_UNREAD)))))))) && (ListenerUtil.mutListener.listen(8301) ? (itemType >= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8300) ? (itemType <= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8299) ? (itemType > TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8298) ? (itemType < TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8297) ? (itemType == TYPE_DATE_SEPARATOR) : (itemType != TYPE_DATE_SEPARATOR))))))))) {
                            if (!ListenerUtil.mutListener.listen(8303)) {
                                holder.senderView = itemView.findViewById(R.id.group_sender_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8304)) {
                                holder.senderName = itemView.findViewById(R.id.group_sender_name);
                            }
                            if (!ListenerUtil.mutListener.listen(8305)) {
                                holder.dateView = itemView.findViewById(R.id.date_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8306)) {
                                holder.deliveredIndicator = itemView.findViewById(R.id.delivered_indicator);
                            }
                            if (!ListenerUtil.mutListener.listen(8307)) {
                                holder.attachmentImage = itemView.findViewById(R.id.attachment_image_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8308)) {
                                holder.avatarView = itemView.findViewById(R.id.avatar_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8309)) {
                                holder.contentView = itemView.findViewById(R.id.content_block);
                            }
                            if (!ListenerUtil.mutListener.listen(8310)) {
                                holder.secondaryTextView = itemView.findViewById(R.id.secondary_text_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8311)) {
                                holder.seekBar = itemView.findViewById(R.id.seek);
                            }
                            if (!ListenerUtil.mutListener.listen(8312)) {
                                holder.tertiaryTextView = itemView.findViewById(R.id.tertiaryTextView);
                            }
                            if (!ListenerUtil.mutListener.listen(8313)) {
                                holder.size = itemView.findViewById(R.id.document_size_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8314)) {
                                holder.controller = itemView.findViewById(R.id.controller);
                            }
                            if (!ListenerUtil.mutListener.listen(8315)) {
                                holder.quoteBar = itemView.findViewById(R.id.quote_bar);
                            }
                            if (!ListenerUtil.mutListener.listen(8316)) {
                                holder.quoteThumbnail = itemView.findViewById(R.id.quote_thumbnail);
                            }
                            if (!ListenerUtil.mutListener.listen(8317)) {
                                holder.quoteTypeImage = itemView.findViewById(R.id.quote_type_image);
                            }
                            if (!ListenerUtil.mutListener.listen(8318)) {
                                holder.transcoderView = itemView.findViewById(R.id.transcoder_view);
                            }
                            if (!ListenerUtil.mutListener.listen(8319)) {
                                holder.readOnContainer = itemView.findViewById(R.id.read_on_container);
                            }
                            if (!ListenerUtil.mutListener.listen(8320)) {
                                holder.readOnButton = itemView.findViewById(R.id.read_on_button);
                            }
                            if (!ListenerUtil.mutListener.listen(8321)) {
                                holder.messageTypeButton = itemView.findViewById(R.id.message_type_button);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8323)) {
                        itemView.setTag(holder);
                    }
                }
            }
        } else {
            // recycled view - reset a few views to their initial state
            holder = (ComposeMessageHolder) itemView.getTag();
            if (!ListenerUtil.mutListener.listen(8262)) {
                if (holder.messagePlayer != null) {
                    if (!ListenerUtil.mutListener.listen(8260)) {
                        // remove any references to listeners in case of a recycled view
                        holder.messagePlayer.removeListeners();
                    }
                    if (!ListenerUtil.mutListener.listen(8261)) {
                        holder.messagePlayer = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8282)) {
                // make sure height is re-set to zero to force redraw of item layout if it's recycled after swipe-to-delete
                if ((ListenerUtil.mutListener.listen(8279) ? ((ListenerUtil.mutListener.listen(8273) ? ((ListenerUtil.mutListener.listen(8267) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8266) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8265) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8264) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8263) ? (itemType != TYPE_STATUS) : (itemType == TYPE_STATUS)))))) && (ListenerUtil.mutListener.listen(8272) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8271) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8270) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8269) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8268) ? (itemType != TYPE_FIRST_UNREAD) : (itemType == TYPE_FIRST_UNREAD))))))) : ((ListenerUtil.mutListener.listen(8267) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8266) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8265) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8264) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8263) ? (itemType != TYPE_STATUS) : (itemType == TYPE_STATUS)))))) || (ListenerUtil.mutListener.listen(8272) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8271) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8270) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8269) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8268) ? (itemType != TYPE_FIRST_UNREAD) : (itemType == TYPE_FIRST_UNREAD)))))))) && (ListenerUtil.mutListener.listen(8278) ? (itemType >= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8277) ? (itemType <= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8276) ? (itemType > TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8275) ? (itemType < TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8274) ? (itemType != TYPE_DATE_SEPARATOR) : (itemType == TYPE_DATE_SEPARATOR))))))) : ((ListenerUtil.mutListener.listen(8273) ? ((ListenerUtil.mutListener.listen(8267) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8266) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8265) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8264) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8263) ? (itemType != TYPE_STATUS) : (itemType == TYPE_STATUS)))))) && (ListenerUtil.mutListener.listen(8272) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8271) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8270) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8269) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8268) ? (itemType != TYPE_FIRST_UNREAD) : (itemType == TYPE_FIRST_UNREAD))))))) : ((ListenerUtil.mutListener.listen(8267) ? (itemType >= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8266) ? (itemType <= TYPE_STATUS) : (ListenerUtil.mutListener.listen(8265) ? (itemType > TYPE_STATUS) : (ListenerUtil.mutListener.listen(8264) ? (itemType < TYPE_STATUS) : (ListenerUtil.mutListener.listen(8263) ? (itemType != TYPE_STATUS) : (itemType == TYPE_STATUS)))))) || (ListenerUtil.mutListener.listen(8272) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8271) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8270) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8269) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8268) ? (itemType != TYPE_FIRST_UNREAD) : (itemType == TYPE_FIRST_UNREAD)))))))) || (ListenerUtil.mutListener.listen(8278) ? (itemType >= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8277) ? (itemType <= TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8276) ? (itemType > TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8275) ? (itemType < TYPE_DATE_SEPARATOR) : (ListenerUtil.mutListener.listen(8274) ? (itemType != TYPE_DATE_SEPARATOR) : (itemType == TYPE_DATE_SEPARATOR))))))))) {
                    if (!ListenerUtil.mutListener.listen(8281)) {
                        itemView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 0));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8280)) {
                        itemView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, 0));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8325)) {
            holder.position = position;
        }
        final ChatAdapterDecorator decorator;
        if ((ListenerUtil.mutListener.listen(8330) ? (itemType >= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8329) ? (itemType <= TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8328) ? (itemType > TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8327) ? (itemType < TYPE_FIRST_UNREAD) : (ListenerUtil.mutListener.listen(8326) ? (itemType != TYPE_FIRST_UNREAD) : (itemType == TYPE_FIRST_UNREAD))))))) {
            // add number of unread messages
            decorator = new FirstUnreadChatAdapterDecorator(this.context, messageModel, this.decoratorHelper, unreadMessagesCount);
        } else {
            switch(messageType) {
                case STATUS:
                    decorator = new StatusChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                case VIDEO:
                    decorator = new VideoChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                case IMAGE:
                    decorator = new ImageChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                case LOCATION:
                    decorator = new LocationChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                case VOICEMESSAGE:
                    decorator = new AudioChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                case BALLOT:
                    decorator = new BallotChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                case FILE:
                    if (MimeUtil.isGifFile(messageModel.getFileData().getMimeType())) {
                        decorator = new AnimGifChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    } else if ((ListenerUtil.mutListener.listen(8332) ? (MimeUtil.isVideoFile(messageModel.getFileData().getMimeType()) || ((ListenerUtil.mutListener.listen(8331) ? (messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA && messageModel.getFileData().getRenderingType() == FileData.RENDERING_STICKER) : (messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA || messageModel.getFileData().getRenderingType() == FileData.RENDERING_STICKER)))) : (MimeUtil.isVideoFile(messageModel.getFileData().getMimeType()) && ((ListenerUtil.mutListener.listen(8331) ? (messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA && messageModel.getFileData().getRenderingType() == FileData.RENDERING_STICKER) : (messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA || messageModel.getFileData().getRenderingType() == FileData.RENDERING_STICKER)))))) {
                        decorator = new VideoChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    } else if ((ListenerUtil.mutListener.listen(8333) ? (MimeUtil.isAudioFile(messageModel.getFileData().getMimeType()) || messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA) : (MimeUtil.isAudioFile(messageModel.getFileData().getMimeType()) && messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA))) {
                        decorator = new AudioChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    } else {
                        decorator = new FileChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    }
                    break;
                case VOIP_STATUS:
                    decorator = new VoipStatusDataChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    break;
                // Fallback to text chat adapter
                default:
                    if (messageModel.isStatusMessage()) {
                        if (messageModel instanceof DateSeparatorMessageModel) {
                            decorator = new DateSeparatorChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                        } else {
                            decorator = new StatusChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                        }
                    } else {
                        decorator = new TextChatAdapterDecorator(this.context, messageModel, this.decoratorHelper);
                    }
            }
            if (!ListenerUtil.mutListener.listen(8340)) {
                if ((ListenerUtil.mutListener.listen(8338) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(8337) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(8336) ? (groupId < 0) : (ListenerUtil.mutListener.listen(8335) ? (groupId != 0) : (ListenerUtil.mutListener.listen(8334) ? (groupId == 0) : (groupId > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(8339)) {
                        decorator.setGroupMessage(groupId, this.identityColors);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8356)) {
                if (this.onClickListener != null) {
                    if (!ListenerUtil.mutListener.listen(8341)) {
                        decorator.setOnClickRetry(messageModel1 -> onClickListener.resend(messageModel1));
                    }
                    final View v = itemView;
                    if (!ListenerUtil.mutListener.listen(8342)) {
                        decorator.setOnClickElement(messageModel12 -> onClickListener.click(v, position, messageModel12));
                    }
                    if (!ListenerUtil.mutListener.listen(8343)) {
                        decorator.setOnLongClickElement(messageModel13 -> onClickListener.longClick(v, position, messageModel13));
                    }
                    if (!ListenerUtil.mutListener.listen(8344)) {
                        decorator.setOnTouchElement((motionEvent, messageModel14) -> {
                            return onClickListener.touch(v, motionEvent, messageModel14);
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(8355)) {
                        if ((ListenerUtil.mutListener.listen(8345) ? (!messageModel.isOutbox() || holder.avatarView != null) : (!messageModel.isOutbox() && holder.avatarView != null))) {
                            if (!ListenerUtil.mutListener.listen(8354)) {
                                if ((ListenerUtil.mutListener.listen(8350) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(8349) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(8348) ? (groupId < 0) : (ListenerUtil.mutListener.listen(8347) ? (groupId != 0) : (ListenerUtil.mutListener.listen(8346) ? (groupId == 0) : (groupId > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(8351)) {
                                        holder.avatarView.setOnClickListener(v1 -> onClickListener.avatarClick(v1, position, messageModel));
                                    }
                                    if (!ListenerUtil.mutListener.listen(8353)) {
                                        if (messageModel.getIdentity() != null) {
                                            ContactModel contactModel = decoratorHelper.getContactService().getByIdentity(messageModel.getIdentity());
                                            String displayName = NameUtil.getDisplayNameOrNickname(contactModel, true);
                                            if (!ListenerUtil.mutListener.listen(8352)) {
                                                holder.avatarView.setContentDescription(getContext().getString(R.string.show_contact) + ": " + displayName);
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
        if (!ListenerUtil.mutListener.listen(8359)) {
            if ((ListenerUtil.mutListener.listen(8357) ? (convListFilter != null || convListFilter.getHighlightMatches()) : (convListFilter != null && convListFilter.getHighlightMatches()))) {
                if (!ListenerUtil.mutListener.listen(8358)) {
                    /* show matches in decorator */
                    decorator.setFilter(convListFilter.getFilterString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8362)) {
            if ((ListenerUtil.mutListener.listen(8360) ? (parent != null || parent instanceof ListView) : (parent != null && parent instanceof ListView))) {
                if (!ListenerUtil.mutListener.listen(8361)) {
                    decorator.setInListView(((ListView) parent));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8363)) {
            decorator.decorate(holder, position);
        }
        return itemView;
    }

    public class ConversationListFilter extends Filter {

        private String filterString = null;

        private String filterIdentity = null;

        private String myIdentity = null;

        private boolean highlightMatches = true;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (!ListenerUtil.mutListener.listen(8364)) {
                currentConstraint = constraint;
            }
            if (!ListenerUtil.mutListener.listen(8365)) {
                onClickListener.onSearchInProgress(true);
            }
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(8366)) {
                resultMap.clear();
            }
            if (!ListenerUtil.mutListener.listen(8367)) {
                resultMapIndex = 0;
            }
            if (!ListenerUtil.mutListener.listen(8368)) {
                searchUpdate();
            }
            if (!ListenerUtil.mutListener.listen(8441)) {
                if ((ListenerUtil.mutListener.listen(8374) ? (constraint == null && (ListenerUtil.mutListener.listen(8373) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(8372) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(8371) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(8370) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(8369) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(8373) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(8372) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(8371) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(8370) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(8369) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(8440)) {
                        // no filtering
                        filterString = null;
                    }
                } else {
                    // perform filtering
                    int index = 0, position = 0;
                    if (!ListenerUtil.mutListener.listen(8375)) {
                        filterString = constraint.toString();
                    }
                    if (!ListenerUtil.mutListener.listen(8437)) {
                        if (filterIdentity != null) {
                            // search for quotes referenced by either the text or the API message ID of the original message
                            String apiMessageIdToSearchFor = null;
                            if (!ListenerUtil.mutListener.listen(8409)) {
                                if ((ListenerUtil.mutListener.listen(8407) ? (filterString.startsWith("#") || (ListenerUtil.mutListener.listen(8406) ? (filterString.length() >= 17) : (ListenerUtil.mutListener.listen(8405) ? (filterString.length() <= 17) : (ListenerUtil.mutListener.listen(8404) ? (filterString.length() > 17) : (ListenerUtil.mutListener.listen(8403) ? (filterString.length() < 17) : (ListenerUtil.mutListener.listen(8402) ? (filterString.length() != 17) : (filterString.length() == 17))))))) : (filterString.startsWith("#") && (ListenerUtil.mutListener.listen(8406) ? (filterString.length() >= 17) : (ListenerUtil.mutListener.listen(8405) ? (filterString.length() <= 17) : (ListenerUtil.mutListener.listen(8404) ? (filterString.length() > 17) : (ListenerUtil.mutListener.listen(8403) ? (filterString.length() < 17) : (ListenerUtil.mutListener.listen(8402) ? (filterString.length() != 17) : (filterString.length() == 17))))))))) {
                                    if (!ListenerUtil.mutListener.listen(8408)) {
                                        apiMessageIdToSearchFor = filterString.substring(1, 17);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8436)) {
                                {
                                    long _loopCounter67 = 0;
                                    for (position = (ListenerUtil.mutListener.listen(8435) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8434) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8433) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8432) ? (values.size() + 1) : (values.size() - 1))))); (ListenerUtil.mutListener.listen(8431) ? (position <= 0) : (ListenerUtil.mutListener.listen(8430) ? (position > 0) : (ListenerUtil.mutListener.listen(8429) ? (position < 0) : (ListenerUtil.mutListener.listen(8428) ? (position != 0) : (ListenerUtil.mutListener.listen(8427) ? (position == 0) : (position >= 0)))))); position--) {
                                        ListenerUtil.loopListener.listen("_loopCounter67", ++_loopCounter67);
                                        AbstractMessageModel messageModel = values.get(position);
                                        if (!ListenerUtil.mutListener.listen(8426)) {
                                            if (apiMessageIdToSearchFor != null) {
                                                if (!ListenerUtil.mutListener.listen(8425)) {
                                                    // search for message ids
                                                    if (apiMessageIdToSearchFor.equals(messageModel.getApiMessageId())) {
                                                        if (!ListenerUtil.mutListener.listen(8424)) {
                                                            resultMap.put(index, position);
                                                        }
                                                        break;
                                                    }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(8423)) {
                                                    if (((ListenerUtil.mutListener.listen(8413) ? ((ListenerUtil.mutListener.listen(8412) ? ((ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) && messageModel.getType() == MessageType.IMAGE) : (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) || messageModel.getType() == MessageType.IMAGE)) && messageModel.getType() == MessageType.FILE) : ((ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) && messageModel.getType() == MessageType.IMAGE) : (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) || messageModel.getType() == MessageType.IMAGE)) || messageModel.getType() == MessageType.FILE)) && messageModel.getType() == MessageType.LOCATION) : ((ListenerUtil.mutListener.listen(8412) ? ((ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) && messageModel.getType() == MessageType.IMAGE) : (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) || messageModel.getType() == MessageType.IMAGE)) && messageModel.getType() == MessageType.FILE) : ((ListenerUtil.mutListener.listen(8411) ? (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) && messageModel.getType() == MessageType.IMAGE) : (((ListenerUtil.mutListener.listen(8410) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) || messageModel.getType() == MessageType.IMAGE)) || messageModel.getType() == MessageType.FILE)) || messageModel.getType() == MessageType.LOCATION)))) {
                                                        String body = messageModel.getCaption();
                                                        if (!ListenerUtil.mutListener.listen(8415)) {
                                                            if (TextUtils.isEmpty(body)) {
                                                                if (!ListenerUtil.mutListener.listen(8414)) {
                                                                    body = QuoteUtil.getMessageBody(messageModel, false);
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(8422)) {
                                                            if (body != null) {
                                                                if (!ListenerUtil.mutListener.listen(8421)) {
                                                                    if (body.equals(filterString)) {
                                                                        if (!ListenerUtil.mutListener.listen(8420)) {
                                                                            if (messageModel.isOutbox()) {
                                                                                if (!ListenerUtil.mutListener.listen(8419)) {
                                                                                    if (filterIdentity.equals(myIdentity)) {
                                                                                        if (!ListenerUtil.mutListener.listen(8418)) {
                                                                                            resultMap.put(index, position);
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (!ListenerUtil.mutListener.listen(8417)) {
                                                                                    if (messageModel.getIdentity().equals(filterIdentity)) {
                                                                                        if (!ListenerUtil.mutListener.listen(8416)) {
                                                                                            resultMap.put(index, position);
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
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8401)) {
                                {
                                    long _loopCounter66 = 0;
                                    // filtering of matching messages by content
                                    for (AbstractMessageModel messageModel : values) {
                                        ListenerUtil.loopListener.listen("_loopCounter66", ++_loopCounter66);
                                        if (!ListenerUtil.mutListener.listen(8399)) {
                                            if ((ListenerUtil.mutListener.listen(8378) ? ((ListenerUtil.mutListener.listen(8377) ? (((ListenerUtil.mutListener.listen(8376) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) && messageModel.getType() == MessageType.LOCATION) : (((ListenerUtil.mutListener.listen(8376) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) || messageModel.getType() == MessageType.LOCATION)) && messageModel.getType() == MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(8377) ? (((ListenerUtil.mutListener.listen(8376) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) && messageModel.getType() == MessageType.LOCATION) : (((ListenerUtil.mutListener.listen(8376) ? (messageModel.getType() == MessageType.TEXT || !messageModel.isStatusMessage()) : (messageModel.getType() == MessageType.TEXT && !messageModel.isStatusMessage()))) || messageModel.getType() == MessageType.LOCATION)) || messageModel.getType() == MessageType.BALLOT))) {
                                                String body = messageModel.getBody();
                                                if (!ListenerUtil.mutListener.listen(8394)) {
                                                    if (messageModel.getType() == MessageType.TEXT) {
                                                        // enable searching in quoted text
                                                        int quoteType = QuoteUtil.getQuoteType(messageModel);
                                                        if (!ListenerUtil.mutListener.listen(8392)) {
                                                            if (quoteType != QuoteUtil.QUOTE_TYPE_NONE) {
                                                                QuoteUtil.QuoteContent quoteContent = QuoteUtil.getQuoteContent(messageModel, decoratorHelper.getMessageReceiver().getType(), false, decoratorHelper.getThumbnailCache(), getContext(), messageService, userService, fileService);
                                                                if (!ListenerUtil.mutListener.listen(8391)) {
                                                                    if (quoteContent != null) {
                                                                        if (!ListenerUtil.mutListener.listen(8390)) {
                                                                            body = quoteContent.quotedText + " " + quoteContent.bodyText;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(8393)) {
                                                            // strip away mentions
                                                            body = emojiMarkupUtil.stripMentions(body);
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(8398)) {
                                                    if ((ListenerUtil.mutListener.listen(8395) ? (body != null || body.toLowerCase().contains(filterString.toLowerCase())) : (body != null && body.toLowerCase().contains(filterString.toLowerCase())))) {
                                                        if (!ListenerUtil.mutListener.listen(8396)) {
                                                            resultMap.put(index, position);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(8397)) {
                                                            index++;
                                                        }
                                                    }
                                                }
                                            } else if (messageModel.getType() == MessageType.FILE) {
                                                String searchString = "";
                                                if (!ListenerUtil.mutListener.listen(8384)) {
                                                    if ((ListenerUtil.mutListener.listen(8382) ? (!MimeUtil.isImageFile(messageModel.getFileData().getMimeType()) || !TestUtil.empty(messageModel.getFileData().getFileName())) : (!MimeUtil.isImageFile(messageModel.getFileData().getMimeType()) && !TestUtil.empty(messageModel.getFileData().getFileName())))) {
                                                        if (!ListenerUtil.mutListener.listen(8383)) {
                                                            // do not index filename for images and GIFs - as it's not visible in the UI
                                                            searchString += messageModel.getFileData().getFileName();
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(8386)) {
                                                    if (!TestUtil.empty(messageModel.getFileData().getCaption())) {
                                                        if (!ListenerUtil.mutListener.listen(8385)) {
                                                            searchString += messageModel.getFileData().getCaption();
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(8389)) {
                                                    if (searchString.toLowerCase().contains(filterString.toLowerCase())) {
                                                        if (!ListenerUtil.mutListener.listen(8387)) {
                                                            resultMap.put(index, position);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(8388)) {
                                                            index++;
                                                        }
                                                    }
                                                }
                                            } else if (!TestUtil.empty(messageModel.getCaption())) {
                                                if (!ListenerUtil.mutListener.listen(8381)) {
                                                    if (messageModel.getCaption().toLowerCase().contains(filterString.toLowerCase())) {
                                                        if (!ListenerUtil.mutListener.listen(8379)) {
                                                            resultMap.put(index, position);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(8380)) {
                                                            index++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(8400)) {
                                            position++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8438)) {
                        results.values = resultMap;
                    }
                    if (!ListenerUtil.mutListener.listen(8439)) {
                        results.count = resultMap.size();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8442)) {
                onClickListener.onSearchInProgress(false);
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(8445)) {
                if ((ListenerUtil.mutListener.listen(8444) ? ((ListenerUtil.mutListener.listen(8443) ? (constraint != null || currentConstraint != null) : (constraint != null && currentConstraint != null)) || !constraint.toString().equals(currentConstraint.toString())) : ((ListenerUtil.mutListener.listen(8443) ? (constraint != null || currentConstraint != null) : (constraint != null && currentConstraint != null)) && !constraint.toString().equals(currentConstraint.toString())))) {
                    return;
                }
            }
            final int positionOfLastMatch = getMatchPosition(filterString);
            if (!ListenerUtil.mutListener.listen(8460)) {
                if ((ListenerUtil.mutListener.listen(8446) ? (convListFilter != null || convListFilter.getHighlightMatches()) : (convListFilter != null && convListFilter.getHighlightMatches()))) {
                    if (!ListenerUtil.mutListener.listen(8450)) {
                        notifyDataSetChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(8455)) {
                        resultMapIndex = (ListenerUtil.mutListener.listen(8454) ? (resultMap.size() % 1) : (ListenerUtil.mutListener.listen(8453) ? (resultMap.size() / 1) : (ListenerUtil.mutListener.listen(8452) ? (resultMap.size() * 1) : (ListenerUtil.mutListener.listen(8451) ? (resultMap.size() + 1) : (resultMap.size() - 1)))));
                    }
                    if (!ListenerUtil.mutListener.listen(8456)) {
                        searchUpdate();
                    }
                    if (!ListenerUtil.mutListener.listen(8459)) {
                        if (!TextUtils.isEmpty(filterString)) {
                            if (!ListenerUtil.mutListener.listen(8458)) {
                                listView.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(8457)) {
                                            listView.setSelection(positionOfLastMatch);
                                        }
                                    }
                                }, 500);
                            }
                        }
                    }
                } else if (positionOfLastMatch != AbsListView.INVALID_POSITION) {
                    if (!ListenerUtil.mutListener.listen(8449)) {
                        if (listView != null) {
                            if (!ListenerUtil.mutListener.listen(8447)) {
                                notifyDataSetChanged();
                            }
                            if (!ListenerUtil.mutListener.listen(8448)) {
                                listView.post(() -> {
                                    smoothScrollTo(positionOfLastMatch);
                                    listView.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            listView.setItemChecked(positionOfLastMatch, true);
                                            listView.postDelayed(new Runnable() {

                                                @Override
                                                public void run() {
                                                    listView.setItemChecked(positionOfLastMatch, false);
                                                    listView.postDelayed(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            listView.setItemChecked(positionOfLastMatch, true);
                                                            listView.postDelayed(new Runnable() {

                                                                @Override
                                                                public void run() {
                                                                    listView.setItemChecked(positionOfLastMatch, false);
                                                                }
                                                            }, 300);
                                                        }
                                                    }, 200);
                                                }
                                            }, 200);
                                        }
                                    }, 300);
                                });
                            }
                        }
                    }
                }
            }
        }

        public String getFilterString() {
            return filterString;
        }

        public void setFilterIdentity(String filterIdentity) {
            if (!ListenerUtil.mutListener.listen(8461)) {
                this.filterIdentity = filterIdentity;
            }
        }

        public void setMyIdentity(String myIdentity) {
            if (!ListenerUtil.mutListener.listen(8462)) {
                this.myIdentity = myIdentity;
            }
        }

        public void setHighlightMatches(boolean highlightMatches) {
            if (!ListenerUtil.mutListener.listen(8463)) {
                this.highlightMatches = highlightMatches;
            }
        }

        public boolean getHighlightMatches() {
            return highlightMatches;
        }
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return convListFilter;
    }

    /**
     *  Create an instance of ConversationListFilter for quote searching
     *
     *  @param quoteContent
     *  @return
     */
    public Filter getQuoteFilter(QuoteUtil.QuoteContent quoteContent) {
        if (!ListenerUtil.mutListener.listen(8464)) {
            convListFilter = new ConversationListFilter();
        }
        if (!ListenerUtil.mutListener.listen(8465)) {
            convListFilter.setFilterIdentity(quoteContent.identity);
        }
        if (!ListenerUtil.mutListener.listen(8466)) {
            convListFilter.setMyIdentity(userService.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(8467)) {
            convListFilter.setHighlightMatches(false);
        }
        return convListFilter;
    }

    private void searchUpdate() {
        int size = resultMap.size();
        if (!ListenerUtil.mutListener.listen(8477)) {
            onClickListener.onSearchResultsUpdate((ListenerUtil.mutListener.listen(8472) ? (size >= 0) : (ListenerUtil.mutListener.listen(8471) ? (size <= 0) : (ListenerUtil.mutListener.listen(8470) ? (size < 0) : (ListenerUtil.mutListener.listen(8469) ? (size != 0) : (ListenerUtil.mutListener.listen(8468) ? (size == 0) : (size > 0)))))) ? (ListenerUtil.mutListener.listen(8476) ? (resultMapIndex % 1) : (ListenerUtil.mutListener.listen(8475) ? (resultMapIndex / 1) : (ListenerUtil.mutListener.listen(8474) ? (resultMapIndex * 1) : (ListenerUtil.mutListener.listen(8473) ? (resultMapIndex - 1) : (resultMapIndex + 1))))) : 0, resultMap.size());
        }
    }

    public void resetMatchPosition() {
        if (!ListenerUtil.mutListener.listen(8487)) {
            resultMapIndex = (ListenerUtil.mutListener.listen(8482) ? (resultMap.size() >= 0) : (ListenerUtil.mutListener.listen(8481) ? (resultMap.size() <= 0) : (ListenerUtil.mutListener.listen(8480) ? (resultMap.size() < 0) : (ListenerUtil.mutListener.listen(8479) ? (resultMap.size() != 0) : (ListenerUtil.mutListener.listen(8478) ? (resultMap.size() == 0) : (resultMap.size() > 0)))))) ? (ListenerUtil.mutListener.listen(8486) ? (resultMap.size() % 1) : (ListenerUtil.mutListener.listen(8485) ? (resultMap.size() / 1) : (ListenerUtil.mutListener.listen(8484) ? (resultMap.size() * 1) : (ListenerUtil.mutListener.listen(8483) ? (resultMap.size() + 1) : (resultMap.size() - 1))))) : 0;
        }
        if (!ListenerUtil.mutListener.listen(8488)) {
            searchUpdate();
        }
    }

    public void nextMatchPosition() {
        if (!ListenerUtil.mutListener.listen(8489)) {
            SingleToast.getInstance().close();
        }
        if (!ListenerUtil.mutListener.listen(8506)) {
            if ((ListenerUtil.mutListener.listen(8494) ? (resultMap.size() >= 1) : (ListenerUtil.mutListener.listen(8493) ? (resultMap.size() <= 1) : (ListenerUtil.mutListener.listen(8492) ? (resultMap.size() < 1) : (ListenerUtil.mutListener.listen(8491) ? (resultMap.size() != 1) : (ListenerUtil.mutListener.listen(8490) ? (resultMap.size() == 1) : (resultMap.size() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(8496)) {
                    resultMapIndex++;
                }
                if (!ListenerUtil.mutListener.listen(8503)) {
                    if ((ListenerUtil.mutListener.listen(8501) ? (resultMapIndex <= resultMap.size()) : (ListenerUtil.mutListener.listen(8500) ? (resultMapIndex > resultMap.size()) : (ListenerUtil.mutListener.listen(8499) ? (resultMapIndex < resultMap.size()) : (ListenerUtil.mutListener.listen(8498) ? (resultMapIndex != resultMap.size()) : (ListenerUtil.mutListener.listen(8497) ? (resultMapIndex == resultMap.size()) : (resultMapIndex >= resultMap.size()))))))) {
                        if (!ListenerUtil.mutListener.listen(8502)) {
                            // wrap around - search from beginning
                            resultMapIndex = 0;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8504)) {
                    smoothScrollTo(resultMap.get(resultMapIndex));
                }
                if (!ListenerUtil.mutListener.listen(8505)) {
                    searchUpdate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8495)) {
                    SingleToast.getInstance().showShortText(context.getString(R.string.search_no_more_matches));
                }
            }
        }
    }

    public void previousMatchPosition() {
        if (!ListenerUtil.mutListener.listen(8507)) {
            SingleToast.getInstance().close();
        }
        if (!ListenerUtil.mutListener.listen(8528)) {
            if ((ListenerUtil.mutListener.listen(8512) ? (resultMap.size() >= 1) : (ListenerUtil.mutListener.listen(8511) ? (resultMap.size() <= 1) : (ListenerUtil.mutListener.listen(8510) ? (resultMap.size() < 1) : (ListenerUtil.mutListener.listen(8509) ? (resultMap.size() != 1) : (ListenerUtil.mutListener.listen(8508) ? (resultMap.size() == 1) : (resultMap.size() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(8514)) {
                    resultMapIndex--;
                }
                if (!ListenerUtil.mutListener.listen(8525)) {
                    if ((ListenerUtil.mutListener.listen(8519) ? (resultMapIndex >= 0) : (ListenerUtil.mutListener.listen(8518) ? (resultMapIndex <= 0) : (ListenerUtil.mutListener.listen(8517) ? (resultMapIndex > 0) : (ListenerUtil.mutListener.listen(8516) ? (resultMapIndex != 0) : (ListenerUtil.mutListener.listen(8515) ? (resultMapIndex == 0) : (resultMapIndex < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(8524)) {
                            // wrap around - search from end
                            resultMapIndex = (ListenerUtil.mutListener.listen(8523) ? (resultMap.size() % 1) : (ListenerUtil.mutListener.listen(8522) ? (resultMap.size() / 1) : (ListenerUtil.mutListener.listen(8521) ? (resultMap.size() * 1) : (ListenerUtil.mutListener.listen(8520) ? (resultMap.size() + 1) : (resultMap.size() - 1)))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8526)) {
                    smoothScrollTo(resultMap.get(resultMapIndex));
                }
                if (!ListenerUtil.mutListener.listen(8527)) {
                    searchUpdate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8513)) {
                    SingleToast.getInstance().showShortText(context.getString(R.string.search_no_more_matches));
                }
            }
        }
    }

    private void smoothScrollTo(int to) {
        int from = listView.getFirstVisiblePosition();
        if (!ListenerUtil.mutListener.listen(8540)) {
            if ((ListenerUtil.mutListener.listen(8537) ? (Math.abs((ListenerUtil.mutListener.listen(8532) ? (to % from) : (ListenerUtil.mutListener.listen(8531) ? (to / from) : (ListenerUtil.mutListener.listen(8530) ? (to * from) : (ListenerUtil.mutListener.listen(8529) ? (to + from) : (to - from)))))) >= 5) : (ListenerUtil.mutListener.listen(8536) ? (Math.abs((ListenerUtil.mutListener.listen(8532) ? (to % from) : (ListenerUtil.mutListener.listen(8531) ? (to / from) : (ListenerUtil.mutListener.listen(8530) ? (to * from) : (ListenerUtil.mutListener.listen(8529) ? (to + from) : (to - from)))))) <= 5) : (ListenerUtil.mutListener.listen(8535) ? (Math.abs((ListenerUtil.mutListener.listen(8532) ? (to % from) : (ListenerUtil.mutListener.listen(8531) ? (to / from) : (ListenerUtil.mutListener.listen(8530) ? (to * from) : (ListenerUtil.mutListener.listen(8529) ? (to + from) : (to - from)))))) > 5) : (ListenerUtil.mutListener.listen(8534) ? (Math.abs((ListenerUtil.mutListener.listen(8532) ? (to % from) : (ListenerUtil.mutListener.listen(8531) ? (to / from) : (ListenerUtil.mutListener.listen(8530) ? (to * from) : (ListenerUtil.mutListener.listen(8529) ? (to + from) : (to - from)))))) != 5) : (ListenerUtil.mutListener.listen(8533) ? (Math.abs((ListenerUtil.mutListener.listen(8532) ? (to % from) : (ListenerUtil.mutListener.listen(8531) ? (to / from) : (ListenerUtil.mutListener.listen(8530) ? (to * from) : (ListenerUtil.mutListener.listen(8529) ? (to + from) : (to - from)))))) == 5) : (Math.abs((ListenerUtil.mutListener.listen(8532) ? (to % from) : (ListenerUtil.mutListener.listen(8531) ? (to / from) : (ListenerUtil.mutListener.listen(8530) ? (to * from) : (ListenerUtil.mutListener.listen(8529) ? (to + from) : (to - from)))))) < 5))))))) {
                if (!ListenerUtil.mutListener.listen(8539)) {
                    listView.smoothScrollToPosition(to);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8538)) {
                    listView.setSelection(to);
                }
            }
        }
    }

    public void clearFilter() {
        if (!ListenerUtil.mutListener.listen(8541)) {
            resultMapIndex = 0;
        }
        if (!ListenerUtil.mutListener.listen(8542)) {
            resultMap.clear();
        }
        if (!ListenerUtil.mutListener.listen(8543)) {
            convListFilter = new ConversationListFilter();
        }
    }

    private int getMatchPosition(String filterString) {
        if (!ListenerUtil.mutListener.listen(8568)) {
            if ((ListenerUtil.mutListener.listen(8554) ? (((ListenerUtil.mutListener.listen(8548) ? (resultMap.size() >= 0) : (ListenerUtil.mutListener.listen(8547) ? (resultMap.size() <= 0) : (ListenerUtil.mutListener.listen(8546) ? (resultMap.size() < 0) : (ListenerUtil.mutListener.listen(8545) ? (resultMap.size() != 0) : (ListenerUtil.mutListener.listen(8544) ? (resultMap.size() == 0) : (resultMap.size() > 0))))))) || ((ListenerUtil.mutListener.listen(8553) ? (resultMapIndex >= resultMap.size()) : (ListenerUtil.mutListener.listen(8552) ? (resultMapIndex <= resultMap.size()) : (ListenerUtil.mutListener.listen(8551) ? (resultMapIndex > resultMap.size()) : (ListenerUtil.mutListener.listen(8550) ? (resultMapIndex != resultMap.size()) : (ListenerUtil.mutListener.listen(8549) ? (resultMapIndex == resultMap.size()) : (resultMapIndex < resultMap.size())))))))) : (((ListenerUtil.mutListener.listen(8548) ? (resultMap.size() >= 0) : (ListenerUtil.mutListener.listen(8547) ? (resultMap.size() <= 0) : (ListenerUtil.mutListener.listen(8546) ? (resultMap.size() < 0) : (ListenerUtil.mutListener.listen(8545) ? (resultMap.size() != 0) : (ListenerUtil.mutListener.listen(8544) ? (resultMap.size() == 0) : (resultMap.size() > 0))))))) && ((ListenerUtil.mutListener.listen(8553) ? (resultMapIndex >= resultMap.size()) : (ListenerUtil.mutListener.listen(8552) ? (resultMapIndex <= resultMap.size()) : (ListenerUtil.mutListener.listen(8551) ? (resultMapIndex > resultMap.size()) : (ListenerUtil.mutListener.listen(8550) ? (resultMapIndex != resultMap.size()) : (ListenerUtil.mutListener.listen(8549) ? (resultMapIndex == resultMap.size()) : (resultMapIndex < resultMap.size())))))))))) {
                if (!ListenerUtil.mutListener.listen(8563)) {
                    // Destroy toast!
                    SingleToast.getInstance().close();
                }
                return resultMap.get((ListenerUtil.mutListener.listen(8567) ? (resultMap.size() % 1) : (ListenerUtil.mutListener.listen(8566) ? (resultMap.size() / 1) : (ListenerUtil.mutListener.listen(8565) ? (resultMap.size() * 1) : (ListenerUtil.mutListener.listen(8564) ? (resultMap.size() + 1) : (resultMap.size() - 1))))));
            } else if ((ListenerUtil.mutListener.listen(8560) ? (filterString != null || (ListenerUtil.mutListener.listen(8559) ? (filterString.length() >= 0) : (ListenerUtil.mutListener.listen(8558) ? (filterString.length() <= 0) : (ListenerUtil.mutListener.listen(8557) ? (filterString.length() < 0) : (ListenerUtil.mutListener.listen(8556) ? (filterString.length() != 0) : (ListenerUtil.mutListener.listen(8555) ? (filterString.length() == 0) : (filterString.length() > 0))))))) : (filterString != null && (ListenerUtil.mutListener.listen(8559) ? (filterString.length() >= 0) : (ListenerUtil.mutListener.listen(8558) ? (filterString.length() <= 0) : (ListenerUtil.mutListener.listen(8557) ? (filterString.length() < 0) : (ListenerUtil.mutListener.listen(8556) ? (filterString.length() != 0) : (ListenerUtil.mutListener.listen(8555) ? (filterString.length() == 0) : (filterString.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(8562)) {
                    if (convListFilter.getHighlightMatches()) {
                        if (!ListenerUtil.mutListener.listen(8561)) {
                            SingleToast.getInstance().showShortText(context.getString(R.string.search_no_matches));
                        }
                    } else {
                        return AbsListView.INVALID_POSITION;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public void setUnreadMessagesCount(int unreadMessagesCount) {
        if (!ListenerUtil.mutListener.listen(8569)) {
            this.unreadMessagesCount = unreadMessagesCount;
        }
    }

    public boolean removeFirstUnreadPosition() {
        if (!ListenerUtil.mutListener.listen(8586)) {
            if ((ListenerUtil.mutListener.listen(8574) ? (this.firstUnreadPos <= 0) : (ListenerUtil.mutListener.listen(8573) ? (this.firstUnreadPos > 0) : (ListenerUtil.mutListener.listen(8572) ? (this.firstUnreadPos < 0) : (ListenerUtil.mutListener.listen(8571) ? (this.firstUnreadPos != 0) : (ListenerUtil.mutListener.listen(8570) ? (this.firstUnreadPos == 0) : (this.firstUnreadPos >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(8581)) {
                    if ((ListenerUtil.mutListener.listen(8579) ? (this.firstUnreadPos <= this.getCount()) : (ListenerUtil.mutListener.listen(8578) ? (this.firstUnreadPos > this.getCount()) : (ListenerUtil.mutListener.listen(8577) ? (this.firstUnreadPos < this.getCount()) : (ListenerUtil.mutListener.listen(8576) ? (this.firstUnreadPos != this.getCount()) : (ListenerUtil.mutListener.listen(8575) ? (this.firstUnreadPos == this.getCount()) : (this.firstUnreadPos >= this.getCount()))))))) {
                        if (!ListenerUtil.mutListener.listen(8580)) {
                            this.firstUnreadPos = -1;
                        }
                        return false;
                    }
                }
                AbstractMessageModel m = this.getItem(this.firstUnreadPos);
                if (!ListenerUtil.mutListener.listen(8585)) {
                    if ((ListenerUtil.mutListener.listen(8582) ? (m != null || m instanceof FirstUnreadMessageModel) : (m != null && m instanceof FirstUnreadMessageModel))) {
                        if (!ListenerUtil.mutListener.listen(8583)) {
                            this.firstUnreadPos = -1;
                        }
                        if (!ListenerUtil.mutListener.listen(8584)) {
                            this.remove(m);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setIdentityColors(Map<String, Integer> colors) {
        if (!ListenerUtil.mutListener.listen(8587)) {
            this.identityColors = colors;
        }
    }

    @Override
    public void remove(final AbstractMessageModel object) {
        int c = this.getCount();
        if (!ListenerUtil.mutListener.listen(8588)) {
            super.remove(object);
        }
        if (!ListenerUtil.mutListener.listen(8597)) {
            if ((ListenerUtil.mutListener.listen(8594) ? ((ListenerUtil.mutListener.listen(8593) ? (c >= 0) : (ListenerUtil.mutListener.listen(8592) ? (c <= 0) : (ListenerUtil.mutListener.listen(8591) ? (c < 0) : (ListenerUtil.mutListener.listen(8590) ? (c != 0) : (ListenerUtil.mutListener.listen(8589) ? (c == 0) : (c > 0)))))) || c == this.getCount()) : ((ListenerUtil.mutListener.listen(8593) ? (c >= 0) : (ListenerUtil.mutListener.listen(8592) ? (c <= 0) : (ListenerUtil.mutListener.listen(8591) ? (c < 0) : (ListenerUtil.mutListener.listen(8590) ? (c != 0) : (ListenerUtil.mutListener.listen(8589) ? (c == 0) : (c > 0)))))) && c == this.getCount()))) {
                // nothing deleted, search!
                AbstractMessageModel newObject = Functional.select(this.values, new IPredicateNonNull<AbstractMessageModel>() {

                    @Override
                    public boolean apply(@NonNull AbstractMessageModel o) {
                        return o.getId() == object.getId();
                    }
                });
                if (!ListenerUtil.mutListener.listen(8596)) {
                    if (newObject != null) {
                        if (!ListenerUtil.mutListener.listen(8595)) {
                            super.remove(newObject);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    /**
     *  Get adapter position of next available (i.e. downloaded) voice message with same incoming/outgoing status
     *  @param messageModel of original message
     *  @return AbstractMessageModel of next message in adapter that matches the specified criteria or AbsListView.INVALID_POSITION if none is found
     */
    public int getNextVoiceMessage(AbstractMessageModel messageModel) {
        int index = values.indexOf(messageModel);
        if (!ListenerUtil.mutListener.listen(8624)) {
            if ((ListenerUtil.mutListener.listen(8606) ? (index >= (ListenerUtil.mutListener.listen(8601) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8600) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8599) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8598) ? (values.size() + 1) : (values.size() - 1)))))) : (ListenerUtil.mutListener.listen(8605) ? (index <= (ListenerUtil.mutListener.listen(8601) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8600) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8599) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8598) ? (values.size() + 1) : (values.size() - 1)))))) : (ListenerUtil.mutListener.listen(8604) ? (index > (ListenerUtil.mutListener.listen(8601) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8600) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8599) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8598) ? (values.size() + 1) : (values.size() - 1)))))) : (ListenerUtil.mutListener.listen(8603) ? (index != (ListenerUtil.mutListener.listen(8601) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8600) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8599) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8598) ? (values.size() + 1) : (values.size() - 1)))))) : (ListenerUtil.mutListener.listen(8602) ? (index == (ListenerUtil.mutListener.listen(8601) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8600) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8599) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8598) ? (values.size() + 1) : (values.size() - 1)))))) : (index < (ListenerUtil.mutListener.listen(8601) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(8600) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(8599) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(8598) ? (values.size() + 1) : (values.size() - 1)))))))))))) {
                AbstractMessageModel nextMessage = values.get((ListenerUtil.mutListener.listen(8610) ? (index % 1) : (ListenerUtil.mutListener.listen(8609) ? (index / 1) : (ListenerUtil.mutListener.listen(8608) ? (index * 1) : (ListenerUtil.mutListener.listen(8607) ? (index - 1) : (index + 1))))));
                if (!ListenerUtil.mutListener.listen(8623)) {
                    if (nextMessage != null) {
                        boolean isVoiceMessage = nextMessage.getType() == MessageType.VOICEMESSAGE;
                        if (!ListenerUtil.mutListener.listen(8615)) {
                            if (!isVoiceMessage) {
                                if (!ListenerUtil.mutListener.listen(8614)) {
                                    // new school voice messages
                                    isVoiceMessage = (ListenerUtil.mutListener.listen(8613) ? ((ListenerUtil.mutListener.listen(8612) ? ((ListenerUtil.mutListener.listen(8611) ? (nextMessage.getType() == MessageType.FILE || MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType())) : (nextMessage.getType() == MessageType.FILE && MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType()))) || nextMessage.getFileData().getRenderingType() == FileData.RENDERING_MEDIA) : ((ListenerUtil.mutListener.listen(8611) ? (nextMessage.getType() == MessageType.FILE || MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType())) : (nextMessage.getType() == MessageType.FILE && MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType()))) && nextMessage.getFileData().getRenderingType() == FileData.RENDERING_MEDIA)) || nextMessage.getFileData().isDownloaded()) : ((ListenerUtil.mutListener.listen(8612) ? ((ListenerUtil.mutListener.listen(8611) ? (nextMessage.getType() == MessageType.FILE || MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType())) : (nextMessage.getType() == MessageType.FILE && MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType()))) || nextMessage.getFileData().getRenderingType() == FileData.RENDERING_MEDIA) : ((ListenerUtil.mutListener.listen(8611) ? (nextMessage.getType() == MessageType.FILE || MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType())) : (nextMessage.getType() == MessageType.FILE && MimeUtil.isAudioFile(nextMessage.getFileData().getMimeType()))) && nextMessage.getFileData().getRenderingType() == FileData.RENDERING_MEDIA)) && nextMessage.getFileData().isDownloaded()));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8622)) {
                            if (isVoiceMessage) {
                                if (!ListenerUtil.mutListener.listen(8621)) {
                                    if (messageModel.isOutbox() == nextMessage.isOutbox()) {
                                        if (!ListenerUtil.mutListener.listen(8620)) {
                                            if (messageModel.isAvailable()) {
                                                return (ListenerUtil.mutListener.listen(8619) ? (index % 1) : (ListenerUtil.mutListener.listen(8618) ? (index / 1) : (ListenerUtil.mutListener.listen(8617) ? (index * 1) : (ListenerUtil.mutListener.listen(8616) ? (index - 1) : (index + 1)))));
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
        return AbsListView.INVALID_POSITION;
    }
}
