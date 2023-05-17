/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.MessageUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final Logger logger = LoggerFactory.getLogger(WidgetViewsFactory.class);

    private Context context;

    private int appWidgetId;

    private ServiceManager serviceManager;

    private ConversationService conversationService;

    private GroupService groupService;

    private ContactService contactService;

    private DistributionListService distributionListService;

    private LockAppService lockAppService;

    private PreferenceService preferenceService;

    private MessageService messageService;

    private DeadlineListService hiddenChatsListService;

    private List<ConversationModel> conversations;

    public WidgetViewsFactory(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(9704)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(9705)) {
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (!ListenerUtil.mutListener.listen(9706)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(9716)) {
            if (this.serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(9708)) {
                        this.conversationService = serviceManager.getConversationService();
                    }
                    if (!ListenerUtil.mutListener.listen(9709)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(9710)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(9711)) {
                        this.distributionListService = serviceManager.getDistributionListService();
                    }
                    if (!ListenerUtil.mutListener.listen(9712)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(9713)) {
                        this.lockAppService = serviceManager.getLockAppService();
                    }
                    if (!ListenerUtil.mutListener.listen(9714)) {
                        this.preferenceService = serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(9715)) {
                        this.hiddenChatsListService = serviceManager.getHiddenChatsListService();
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(9707)) {
                        logger.debug("no conversationservice");
                    }
                }
            }
        }
    }

    /**
     *  Called when your factory is first constructed. The same factory may be shared across
     *  multiple RemoteViewAdapters depending on the intent passed.
     */
    @Override
    public void onCreate() {
    }

    /**
     *  Called when notifyDataSetChanged() is triggered on the remote adapter. This allows a
     *  RemoteViewsFactory to respond to data changes by updating any internal references.
     *  <p/>
     *  Note: expensive tasks can be safely performed synchronously within this method. In the
     *  interim, the old data will be displayed within the widget.
     *
     *  @see android.appwidget.AppWidgetManager#notifyAppWidgetViewDataChanged(int[], int)
     */
    @Override
    public void onDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(9718)) {
            if (contactService != null) {
                if (!ListenerUtil.mutListener.listen(9717)) {
                    conversations = conversationService.getAll(false, new ConversationService.Filter() {

                        @Override
                        public boolean onlyUnread() {
                            return true;
                        }

                        @Override
                        public boolean noDistributionLists() {
                            return false;
                        }

                        @Override
                        public boolean noHiddenChats() {
                            return preferenceService.isPrivateChatsHidden();
                        }

                        @Override
                        public boolean noInvalid() {
                            return false;
                        }
                    });
                }
            }
        }
    }

    /**
     *  Called when the last RemoteViewsAdapter that is associated with this factory is
     *  unbound.
     */
    @Override
    public void onDestroy() {
    }

    /**
     *  @return Count of items.
     */
    @Override
    public int getCount() {
        if ((ListenerUtil.mutListener.listen(9721) ? ((ListenerUtil.mutListener.listen(9720) ? ((ListenerUtil.mutListener.listen(9719) ? (lockAppService != null || !lockAppService.isLocked()) : (lockAppService != null && !lockAppService.isLocked())) || preferenceService.isShowMessagePreview()) : ((ListenerUtil.mutListener.listen(9719) ? (lockAppService != null || !lockAppService.isLocked()) : (lockAppService != null && !lockAppService.isLocked())) && preferenceService.isShowMessagePreview())) || conversations != null) : ((ListenerUtil.mutListener.listen(9720) ? ((ListenerUtil.mutListener.listen(9719) ? (lockAppService != null || !lockAppService.isLocked()) : (lockAppService != null && !lockAppService.isLocked())) || preferenceService.isShowMessagePreview()) : ((ListenerUtil.mutListener.listen(9719) ? (lockAppService != null || !lockAppService.isLocked()) : (lockAppService != null && !lockAppService.isLocked())) && preferenceService.isShowMessagePreview())) && conversations != null))) {
            return conversations.size();
        } else {
            return 0;
        }
    }

    /**
     *  Note: expensive tasks can be safely performed synchronously within this method, and a
     *  loading view will be displayed in the interim. See {@link #getLoadingView()}.
     *
     *  @param position The position of the item within the Factory's data set of the item whose
     *                  view we want.
     *  @return A RemoteViews object corresponding to the data at the specified position.
     */
    @Override
    public RemoteViews getViewAt(int position) {
        if (!ListenerUtil.mutListener.listen(9765)) {
            if ((ListenerUtil.mutListener.listen(9733) ? ((ListenerUtil.mutListener.listen(9727) ? (conversations != null || (ListenerUtil.mutListener.listen(9726) ? (conversations.size() >= 0) : (ListenerUtil.mutListener.listen(9725) ? (conversations.size() <= 0) : (ListenerUtil.mutListener.listen(9724) ? (conversations.size() < 0) : (ListenerUtil.mutListener.listen(9723) ? (conversations.size() != 0) : (ListenerUtil.mutListener.listen(9722) ? (conversations.size() == 0) : (conversations.size() > 0))))))) : (conversations != null && (ListenerUtil.mutListener.listen(9726) ? (conversations.size() >= 0) : (ListenerUtil.mutListener.listen(9725) ? (conversations.size() <= 0) : (ListenerUtil.mutListener.listen(9724) ? (conversations.size() < 0) : (ListenerUtil.mutListener.listen(9723) ? (conversations.size() != 0) : (ListenerUtil.mutListener.listen(9722) ? (conversations.size() == 0) : (conversations.size() > 0)))))))) || (ListenerUtil.mutListener.listen(9732) ? (position >= conversations.size()) : (ListenerUtil.mutListener.listen(9731) ? (position <= conversations.size()) : (ListenerUtil.mutListener.listen(9730) ? (position > conversations.size()) : (ListenerUtil.mutListener.listen(9729) ? (position != conversations.size()) : (ListenerUtil.mutListener.listen(9728) ? (position == conversations.size()) : (position < conversations.size()))))))) : ((ListenerUtil.mutListener.listen(9727) ? (conversations != null || (ListenerUtil.mutListener.listen(9726) ? (conversations.size() >= 0) : (ListenerUtil.mutListener.listen(9725) ? (conversations.size() <= 0) : (ListenerUtil.mutListener.listen(9724) ? (conversations.size() < 0) : (ListenerUtil.mutListener.listen(9723) ? (conversations.size() != 0) : (ListenerUtil.mutListener.listen(9722) ? (conversations.size() == 0) : (conversations.size() > 0))))))) : (conversations != null && (ListenerUtil.mutListener.listen(9726) ? (conversations.size() >= 0) : (ListenerUtil.mutListener.listen(9725) ? (conversations.size() <= 0) : (ListenerUtil.mutListener.listen(9724) ? (conversations.size() < 0) : (ListenerUtil.mutListener.listen(9723) ? (conversations.size() != 0) : (ListenerUtil.mutListener.listen(9722) ? (conversations.size() == 0) : (conversations.size() > 0)))))))) && (ListenerUtil.mutListener.listen(9732) ? (position >= conversations.size()) : (ListenerUtil.mutListener.listen(9731) ? (position <= conversations.size()) : (ListenerUtil.mutListener.listen(9730) ? (position > conversations.size()) : (ListenerUtil.mutListener.listen(9729) ? (position != conversations.size()) : (ListenerUtil.mutListener.listen(9728) ? (position == conversations.size()) : (position < conversations.size()))))))))) {
                ConversationModel conversationModel = conversations.get(position);
                if (!ListenerUtil.mutListener.listen(9764)) {
                    if (conversationModel != null) {
                        String sender = "", message = "", date = "", count = "";
                        Bitmap avatar = null;
                        Bundle extras = new Bundle();
                        String uniqueId = conversationModel.getReceiver().getUniqueIdString();
                        if (!ListenerUtil.mutListener.listen(9754)) {
                            if ((ListenerUtil.mutListener.listen(9735) ? ((ListenerUtil.mutListener.listen(9734) ? (this.lockAppService != null || !this.lockAppService.isLocked()) : (this.lockAppService != null && !this.lockAppService.isLocked())) || preferenceService.isShowMessagePreview()) : ((ListenerUtil.mutListener.listen(9734) ? (this.lockAppService != null || !this.lockAppService.isLocked()) : (this.lockAppService != null && !this.lockAppService.isLocked())) && preferenceService.isShowMessagePreview()))) {
                                if (!ListenerUtil.mutListener.listen(9740)) {
                                    sender = conversationModel.getReceiver().getDisplayName();
                                }
                                if (!ListenerUtil.mutListener.listen(9747)) {
                                    if (conversationModel.isContactConversation()) {
                                        if (!ListenerUtil.mutListener.listen(9745)) {
                                            avatar = contactService.getAvatar(conversationModel.getContact(), false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9746)) {
                                            extras.putString(ThreemaApplication.INTENT_DATA_CONTACT, conversationModel.getContact().getIdentity());
                                        }
                                    } else if (conversationModel.isGroupConversation()) {
                                        if (!ListenerUtil.mutListener.listen(9743)) {
                                            avatar = groupService.getAvatar(conversationModel.getGroup(), false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9744)) {
                                            extras.putInt(ThreemaApplication.INTENT_DATA_GROUP, conversationModel.getGroup().getId());
                                        }
                                    } else if (conversationModel.isDistributionListConversation()) {
                                        if (!ListenerUtil.mutListener.listen(9741)) {
                                            avatar = distributionListService.getAvatar(conversationModel.getDistributionList(), false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(9742)) {
                                            extras.putInt(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, conversationModel.getDistributionList().getId());
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(9748)) {
                                    count = Long.toString(conversationModel.getUnreadCount());
                                }
                                if (!ListenerUtil.mutListener.listen(9753)) {
                                    if ((ListenerUtil.mutListener.listen(9749) ? (hiddenChatsListService != null || hiddenChatsListService.has(uniqueId)) : (hiddenChatsListService != null && hiddenChatsListService.has(uniqueId)))) {
                                        if (!ListenerUtil.mutListener.listen(9752)) {
                                            message = context.getString(R.string.private_chat_subject);
                                        }
                                    } else if (conversationModel.getLatestMessage() != null) {
                                        AbstractMessageModel messageModel = conversationModel.getLatestMessage();
                                        if (!ListenerUtil.mutListener.listen(9750)) {
                                            message = messageService.getMessageString(messageModel, 200).getMessage();
                                        }
                                        if (!ListenerUtil.mutListener.listen(9751)) {
                                            date = MessageUtil.getDisplayDate(context, conversationModel.getLatestMessage(), false);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9736)) {
                                    sender = context.getString(R.string.new_unprocessed_messages);
                                }
                                if (!ListenerUtil.mutListener.listen(9737)) {
                                    message = context.getString(R.string.new_unprocessed_messages_description);
                                }
                                if (!ListenerUtil.mutListener.listen(9739)) {
                                    if (conversationModel.getLatestMessage() != null) {
                                        if (!ListenerUtil.mutListener.listen(9738)) {
                                            date = MessageUtil.getDisplayDate(context, conversationModel.getLatestMessage(), false);
                                        }
                                    }
                                }
                            }
                        }
                        // and set the text based on the position.
                        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.item_widget);
                        if (!ListenerUtil.mutListener.listen(9755)) {
                            rv.setTextViewText(R.id.sender_text, sender);
                        }
                        if (!ListenerUtil.mutListener.listen(9756)) {
                            rv.setTextViewText(R.id.message_text, message);
                        }
                        if (!ListenerUtil.mutListener.listen(9757)) {
                            rv.setTextViewText(R.id.msg_date, date);
                        }
                        if (!ListenerUtil.mutListener.listen(9758)) {
                            rv.setTextViewText(R.id.message_count, count);
                        }
                        if (!ListenerUtil.mutListener.listen(9761)) {
                            if (avatar != null) {
                                if (!ListenerUtil.mutListener.listen(9760)) {
                                    rv.setImageViewBitmap(R.id.avatar, avatar);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9759)) {
                                    rv.setImageViewResource(R.id.avatar, R.drawable.ic_contact);
                                }
                            }
                        }
                        // that is set on the collection view in StackWidgetProvider.
                        Intent fillInIntent = new Intent();
                        if (!ListenerUtil.mutListener.listen(9762)) {
                            fillInIntent.putExtras(extras);
                        }
                        if (!ListenerUtil.mutListener.listen(9763)) {
                            // action of a given item
                            rv.setOnClickFillInIntent(R.id.item_layout, fillInIntent);
                        }
                        // Return the remote views object.
                        return rv;
                    }
                }
            }
        }
        return null;
    }

    /**
     *  This allows for the use of a custom loading view which appears between the time that
     *  {@link #getViewAt(int)} is called and returns. If null is returned, a default loading
     *  view will be used.
     *
     *  @return The RemoteViews representing the desired loading view.
     */
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    /**
     *  @return The number of types of Views that will be returned by this factory.
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    /**
     *  @param position The position of the item within the data set whose row id we want.
     *  @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *  @return True if the same id always refers to the same object.
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }
}
