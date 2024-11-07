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
package ch.threema.app.services;

import net.sqlcipher.Cursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.DistributionListMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.ConversationTagModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.TagModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConversationServiceImpl implements ConversationService {

    private static final Logger logger = LoggerFactory.getLogger(ConversationServiceImpl.class);

    private final List<ConversationModel> conversationCache;

    private final ConversationTagService conversationTagService;

    private final DatabaseServiceNew databaseServiceNew;

    private final ContactService contactService;

    private final GroupService groupService;

    private final DistributionListService distributionListService;

    private final MessageService messageService;

    private final DeadlineListService hiddenChatsListService;

    private boolean initAllLoaded = false;

    private final TagModel starTag, unreadTag;

    static class ConversationResult {

        public final int messageId;

        public final long count;

        public final String refId;

        ConversationResult(int messageId, long count, String refId) {
            this.messageId = messageId;
            this.count = count;
            this.refId = refId;
        }
    }

    public ConversationServiceImpl(CacheService cacheService, DatabaseServiceNew databaseServiceNew, ContactService contactService, GroupService groupService, DistributionListService distributionListService, MessageService messageService, DeadlineListService hiddenChatsListService, ConversationTagService conversationTagService) {
        this.databaseServiceNew = databaseServiceNew;
        this.contactService = contactService;
        this.groupService = groupService;
        this.distributionListService = distributionListService;
        this.messageService = messageService;
        this.hiddenChatsListService = hiddenChatsListService;
        this.conversationCache = cacheService.getConversationModelCache();
        this.conversationTagService = conversationTagService;
        this.starTag = conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_PIN);
        this.unreadTag = conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_UNREAD);
    }

    @Override
    public synchronized List<ConversationModel> getAll(boolean forceReloadFromDatabase) {
        return this.getAll(forceReloadFromDatabase, null);
    }

    @Override
    public synchronized List<ConversationModel> getAll(boolean forceReloadFromDatabase, final Filter filter) {
        if (!ListenerUtil.mutListener.listen(37177)) {
            logger.debug("getAll forceReloadFromDatabase = " + forceReloadFromDatabase);
        }
        synchronized (this.conversationCache) {
            if (!ListenerUtil.mutListener.listen(37180)) {
                if ((ListenerUtil.mutListener.listen(37178) ? (forceReloadFromDatabase && !this.initAllLoaded) : (forceReloadFromDatabase || !this.initAllLoaded))) {
                    if (!ListenerUtil.mutListener.listen(37179)) {
                        this.conversationCache.clear();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37186)) {
                if (this.conversationCache.size() == 0) {
                    if (!ListenerUtil.mutListener.listen(37181)) {
                        logger.debug("start selecting");
                    }
                    if (!ListenerUtil.mutListener.listen(37183)) {
                        {
                            long _loopCounter375 = 0;
                            for (ConversationModelParser parser : new ConversationModelParser[] { new ContactConversationModelParser(), new GroupConversationModelParser(), new DistributionListConversationModelParser() }) {
                                ListenerUtil.loopListener.listen("_loopCounter375", ++_loopCounter375);
                                if (!ListenerUtil.mutListener.listen(37182)) {
                                    parser.processAll();
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37184)) {
                        logger.debug("selection finished");
                    }
                    if (!ListenerUtil.mutListener.listen(37185)) {
                        this.initAllLoaded = true;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37187)) {
                this.sort();
            }
            if (!ListenerUtil.mutListener.listen(37211)) {
                // filter only if a filter object is set and one of the filter property contains a filter
                if ((ListenerUtil.mutListener.listen(37192) ? (filter != null || ((ListenerUtil.mutListener.listen(37191) ? ((ListenerUtil.mutListener.listen(37190) ? ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) && filter.noInvalid()) : ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) || filter.noInvalid())) && !TestUtil.empty(filter.filterQuery())) : ((ListenerUtil.mutListener.listen(37190) ? ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) && filter.noInvalid()) : ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) || filter.noInvalid())) || !TestUtil.empty(filter.filterQuery()))))) : (filter != null && ((ListenerUtil.mutListener.listen(37191) ? ((ListenerUtil.mutListener.listen(37190) ? ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) && filter.noInvalid()) : ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) || filter.noInvalid())) && !TestUtil.empty(filter.filterQuery())) : ((ListenerUtil.mutListener.listen(37190) ? ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) && filter.noInvalid()) : ((ListenerUtil.mutListener.listen(37189) ? ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) && filter.noHiddenChats()) : ((ListenerUtil.mutListener.listen(37188) ? (filter.onlyUnread() && filter.noDistributionLists()) : (filter.onlyUnread() || filter.noDistributionLists())) || filter.noHiddenChats())) || filter.noInvalid())) || !TestUtil.empty(filter.filterQuery()))))))) {
                    List<ConversationModel> filtered = this.conversationCache;
                    if (!ListenerUtil.mutListener.listen(37195)) {
                        if (filter.onlyUnread()) {
                            if (!ListenerUtil.mutListener.listen(37193)) {
                                logger.debug("filter unread");
                            }
                            if (!ListenerUtil.mutListener.listen(37194)) {
                                filtered = Functional.filter(filtered, new IPredicateNonNull<ConversationModel>() {

                                    @Override
                                    public boolean apply(@NonNull ConversationModel conversationModel) {
                                        return conversationModel.hasUnreadMessage();
                                    }
                                });
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37198)) {
                        if (filter.noDistributionLists()) {
                            if (!ListenerUtil.mutListener.listen(37196)) {
                                logger.debug("filter distribution lists");
                            }
                            if (!ListenerUtil.mutListener.listen(37197)) {
                                filtered = Functional.filter(filtered, new IPredicateNonNull<ConversationModel>() {

                                    @Override
                                    public boolean apply(@NonNull ConversationModel conversationModel) {
                                        return !conversationModel.isDistributionListConversation();
                                    }
                                });
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37201)) {
                        if (filter.noHiddenChats()) {
                            if (!ListenerUtil.mutListener.listen(37199)) {
                                logger.debug("filter hidden lists");
                            }
                            if (!ListenerUtil.mutListener.listen(37200)) {
                                filtered = Functional.filter(filtered, new IPredicateNonNull<ConversationModel>() {

                                    @Override
                                    public boolean apply(@NonNull ConversationModel conversationModel) {
                                        return !hiddenChatsListService.has(conversationModel.getReceiver().getUniqueIdString());
                                    }
                                });
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37207)) {
                        if (filter.noInvalid()) {
                            if (!ListenerUtil.mutListener.listen(37202)) {
                                logger.debug("filter chats with revoked contacts / left group that cannot receive messages");
                            }
                            if (!ListenerUtil.mutListener.listen(37206)) {
                                filtered = Functional.filter(filtered, new IPredicateNonNull<ConversationModel>() {

                                    @Override
                                    public boolean apply(@NonNull ConversationModel conversationModel) {
                                        if (!ListenerUtil.mutListener.listen(37205)) {
                                            if (conversationModel.isContactConversation()) {
                                                return (ListenerUtil.mutListener.listen(37204) ? (conversationModel.getContact() != null || !(conversationModel.getContact().getState() == ContactModel.State.INVALID)) : (conversationModel.getContact() != null && !(conversationModel.getContact().getState() == ContactModel.State.INVALID)));
                                            } else if (conversationModel.isGroupConversation()) {
                                                return (ListenerUtil.mutListener.listen(37203) ? (conversationModel.getGroup() != null || groupService.isGroupMember(conversationModel.getGroup())) : (conversationModel.getGroup() != null && groupService.isGroupMember(conversationModel.getGroup())));
                                            }
                                        }
                                        return true;
                                    }
                                });
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37210)) {
                        if (!TestUtil.empty(filter.filterQuery())) {
                            if (!ListenerUtil.mutListener.listen(37208)) {
                                logger.debug("filter query");
                            }
                            if (!ListenerUtil.mutListener.listen(37209)) {
                                filtered = Functional.filter(filtered, new IPredicateNonNull<ConversationModel>() {

                                    @Override
                                    public boolean apply(@NonNull ConversationModel conversationModel) {
                                        return TestUtil.contains(filter.filterQuery(), conversationModel.getReceiver().getDisplayName());
                                    }
                                });
                            }
                        }
                    }
                    return filtered;
                }
            }
        }
        return this.conversationCache;
    }

    @Override
    public List<ConversationModel> getArchived(String constraint) {
        List<ConversationModel> conversationModels = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(37213)) {
            {
                long _loopCounter376 = 0;
                for (ConversationModelParser parser : new ConversationModelParser[] { new ContactConversationModelParser(), new GroupConversationModelParser(), new DistributionListConversationModelParser() }) {
                    ListenerUtil.loopListener.listen("_loopCounter376", ++_loopCounter376);
                    if (!ListenerUtil.mutListener.listen(37212)) {
                        parser.processArchived(conversationModels, constraint);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37214)) {
            Collections.sort(conversationModels, (conversationModel, conversationModel2) -> {
                if (conversationModel2.getSortDate() == null || conversationModel.getSortDate() == null) {
                    return 0;
                }
                return conversationModel2.getSortDate().compareTo(conversationModel.getSortDate());
            });
        }
        return conversationModels;
    }

    @Override
    public int getArchivedCount() {
        String query = "SELECT" + getArchivedContactsCountQuery() + " + " + getArchivedGroupsCountQuery() + " + " + getArchivedDistListsCountQuery();
        Cursor c = databaseServiceNew.getReadableDatabase().rawQuery(query, null);
        if (!ListenerUtil.mutListener.listen(37217)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(37216)) {
                        c.moveToNext();
                    }
                    return (int) c.getLong(0);
                } catch (Exception ignored) {
                } finally {
                    if (!ListenerUtil.mutListener.listen(37215)) {
                        c.close();
                    }
                }
            }
        }
        return 0;
    }

    private String getArchivedContactsCountQuery() {
        return "(SELECT COUNT(DISTINCT c.identity) FROM contacts c " + "INNER JOIN message m " + "ON c.identity = m.identity " + "WHERE m.isSaved = 1 " + "AND c.isArchived = 1)";
    }

    private String getArchivedGroupsCountQuery() {
        return "(SELECT COUNT(DISTINCT g.id) FROM m_group g " + "LEFT OUTER JOIN m_group_message gm " + "ON gm.groupId = g.id " + "AND gm.isStatusMessage = 0 " + "AND gm.isSaved = 1 " + "WHERE g.deleted != 1 " + "AND g.isArchived = 1)";
    }

    private String getArchivedDistListsCountQuery() {
        return "(SELECT COUNT(DISTINCT d.id) FROM distribution_list d " + "LEFT OUTER JOIN distribution_list_message dm " + "ON dm.distributionListId = d.id " + "AND dm.isStatusMessage = 0 " + "AND dm.isSaved = 1 " + "WHERE d.isArchived = 1)";
    }

    @Override
    public void sort() {
        List<String> taggedConversationUids = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(37220)) {
            {
                long _loopCounter377 = 0;
                for (ConversationTagModel tagModel : conversationTagService.getAll()) {
                    ListenerUtil.loopListener.listen("_loopCounter377", ++_loopCounter377);
                    if (!ListenerUtil.mutListener.listen(37219)) {
                        if (tagModel.getTag().equals(starTag.getTag())) {
                            if (!ListenerUtil.mutListener.listen(37218)) {
                                taggedConversationUids.add(tagModel.getConversationUid());
                            }
                        }
                    }
                }
            }
        }
        int size = taggedConversationUids.size();
        synchronized (this.conversationCache) {
            if (!ListenerUtil.mutListener.listen(37227)) {
                Collections.sort(this.conversationCache, new Comparator<ConversationModel>() {

                    @Override
                    public int compare(ConversationModel conversationModel, ConversationModel conversationModel2) {
                        if ((ListenerUtil.mutListener.listen(37221) ? (conversationModel2.getSortDate() == null && conversationModel.getSortDate() == null) : (conversationModel2.getSortDate() == null || conversationModel.getSortDate() == null))) {
                            return 0;
                        }
                        if ((ListenerUtil.mutListener.listen(37226) ? (size >= 0) : (ListenerUtil.mutListener.listen(37225) ? (size <= 0) : (ListenerUtil.mutListener.listen(37224) ? (size < 0) : (ListenerUtil.mutListener.listen(37223) ? (size != 0) : (ListenerUtil.mutListener.listen(37222) ? (size == 0) : (size > 0))))))) {
                            boolean tagged1 = taggedConversationUids.contains(conversationModel.getUid());
                            boolean tagged2 = taggedConversationUids.contains(conversationModel2.getUid());
                            return tagged1 == tagged2 ? conversationModel2.getSortDate().compareTo(conversationModel.getSortDate()) : tagged2 ? 1 : -1;
                        } else {
                            return conversationModel2.getSortDate().compareTo(conversationModel.getSortDate());
                        }
                    }
                });
            }
            // set new position
            int pos = 0;
            if (!ListenerUtil.mutListener.listen(37229)) {
                {
                    long _loopCounter378 = 0;
                    for (ConversationModel m : this.conversationCache) {
                        ListenerUtil.loopListener.listen("_loopCounter378", ++_loopCounter378);
                        if (!ListenerUtil.mutListener.listen(37228)) {
                            m.setPosition(pos++);
                        }
                    }
                }
            }
        }
    }

    @Override
    public synchronized ConversationModel refresh(AbstractMessageModel modifiedMessageModel) {
        ConversationModelParser parser = this.createParser(modifiedMessageModel);
        if (!ListenerUtil.mutListener.listen(37230)) {
            if (parser != null) {
                return parser.refresh(modifiedMessageModel);
            }
        }
        return null;
    }

    @Override
    public synchronized ConversationModel refresh(ContactModel contactModel) {
        return new ContactConversationModelParser().refresh(contactModel);
    }

    @Override
    public synchronized ConversationModel refresh(GroupModel groupModel) {
        return new GroupConversationModelParser().refresh(groupModel);
    }

    @Override
    public synchronized ConversationModel refresh(DistributionListModel distributionListModel) {
        return new DistributionListConversationModelParser().refresh(distributionListModel);
    }

    @Override
    public synchronized ConversationModel refresh(@NonNull MessageReceiver receiver) {
        switch(receiver.getType()) {
            case MessageReceiver.Type_CONTACT:
                return this.refresh(((ContactMessageReceiver) receiver).getContact());
            case MessageReceiver.Type_GROUP:
                return this.refresh(((GroupMessageReceiver) receiver).getGroup());
            case MessageReceiver.Type_DISTRIBUTION_LIST:
                return this.refresh(((DistributionListMessageReceiver) receiver).getDistributionList());
        }
        throw new IllegalStateException("Got ReceiverModel with invalid receiver type!");
    }

    @Override
    public synchronized ConversationModel setIsTyping(ContactModel contact, boolean isTyping) {
        ContactConversationModelParser p = new ContactConversationModelParser();
        ConversationModel conversationModel = p.getCached(p.getIndex(contact));
        if (!ListenerUtil.mutListener.listen(37233)) {
            if (conversationModel != null) {
                if (!ListenerUtil.mutListener.listen(37231)) {
                    conversationModel.setIsTyping(isTyping);
                }
                if (!ListenerUtil.mutListener.listen(37232)) {
                    this.fireOnModifiedConversation(conversationModel);
                }
            }
        }
        return conversationModel;
    }

    @Override
    public synchronized void refreshWithDeletedMessage(AbstractMessageModel modifiedMessageModel) {
        ConversationModelParser parser = this.createParser(modifiedMessageModel);
        if (!ListenerUtil.mutListener.listen(37235)) {
            if (parser != null) {
                if (!ListenerUtil.mutListener.listen(37234)) {
                    parser.messageDeleted(modifiedMessageModel);
                }
            }
        }
    }

    @Override
    public synchronized void archive(ConversationModel conversationModel) {
        if (!ListenerUtil.mutListener.listen(37236)) {
            this.conversationTagService.removeAll(conversationModel);
        }
        if (!ListenerUtil.mutListener.listen(37238)) {
            if (hiddenChatsListService.has(conversationModel.getUid())) {
                if (!ListenerUtil.mutListener.listen(37237)) {
                    hiddenChatsListService.remove(conversationModel.getUid());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37239)) {
            conversationModel.setUnreadCount(0);
        }
        if (!ListenerUtil.mutListener.listen(37243)) {
            if (conversationModel.isContactConversation()) {
                if (!ListenerUtil.mutListener.listen(37242)) {
                    contactService.setIsArchived(conversationModel.getContact().getIdentity(), true);
                }
            } else if (conversationModel.isGroupConversation()) {
                if (!ListenerUtil.mutListener.listen(37241)) {
                    groupService.setIsArchived(conversationModel.getGroup(), true);
                }
            } else if (conversationModel.isDistributionListConversation()) {
                if (!ListenerUtil.mutListener.listen(37240)) {
                    distributionListService.setIsArchived(conversationModel.getDistributionList(), true);
                }
            }
        }
        synchronized (conversationCache) {
            if (!ListenerUtil.mutListener.listen(37244)) {
                conversationCache.remove(conversationModel);
            }
        }
        if (!ListenerUtil.mutListener.listen(37246)) {
            ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                @Override
                public void handle(ConversationListener listener) {
                    if (!ListenerUtil.mutListener.listen(37245)) {
                        listener.onRemoved(conversationModel);
                    }
                }
            });
        }
    }

    @Override
    public void unarchive(List<ConversationModel> conversationModels) {
        if (!ListenerUtil.mutListener.listen(37251)) {
            {
                long _loopCounter379 = 0;
                for (ConversationModel conversationModel : conversationModels) {
                    ListenerUtil.loopListener.listen("_loopCounter379", ++_loopCounter379);
                    if (!ListenerUtil.mutListener.listen(37250)) {
                        if (conversationModel.isContactConversation()) {
                            if (!ListenerUtil.mutListener.listen(37249)) {
                                contactService.setIsArchived(conversationModel.getContact().getIdentity(), false);
                            }
                        } else if (conversationModel.isGroupConversation()) {
                            if (!ListenerUtil.mutListener.listen(37248)) {
                                groupService.setIsArchived(conversationModel.getGroup(), false);
                            }
                        } else if (conversationModel.isDistributionListConversation()) {
                            if (!ListenerUtil.mutListener.listen(37247)) {
                                distributionListService.setIsArchived(conversationModel.getDistributionList(), false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public synchronized boolean clear(final ConversationModel conversation) {
        return this.clear(conversation, false);
    }

    @Override
    public synchronized void clear(final ConversationModel[] conversations) {
        if (!ListenerUtil.mutListener.listen(37264)) {
            {
                long _loopCounter380 = 0;
                for (ConversationModel conversation : conversations) {
                    ListenerUtil.loopListener.listen("_loopCounter380", ++_loopCounter380);
                    if (!ListenerUtil.mutListener.listen(37252)) {
                        // Remove tags
                        this.conversationTagService.removeAll(conversation);
                    }
                    if (!ListenerUtil.mutListener.listen(37263)) {
                        // Remove from cache if the conversation is a contact conversation
                        if ((ListenerUtil.mutListener.listen(37253) ? (!conversation.isGroupConversation() || !conversation.isDistributionListConversation()) : (!conversation.isGroupConversation() && !conversation.isDistributionListConversation()))) {
                            synchronized (this.conversationCache) {
                                if (!ListenerUtil.mutListener.listen(37259)) {
                                    this.conversationCache.remove(conversation);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(37262)) {
                                if (conversations.length == 1) {
                                    if (!ListenerUtil.mutListener.listen(37261)) {
                                        ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                                            @Override
                                            public void handle(ConversationListener listener) {
                                                if (!ListenerUtil.mutListener.listen(37260)) {
                                                    listener.onRemoved(conversation);
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(37254)) {
                                conversation.setLatestMessage(null);
                            }
                            if (!ListenerUtil.mutListener.listen(37255)) {
                                conversation.setMessageCount(0);
                            }
                            if (!ListenerUtil.mutListener.listen(37256)) {
                                conversation.setUnreadCount(0);
                            }
                            if (!ListenerUtil.mutListener.listen(37258)) {
                                if (conversations.length == 1) {
                                    if (!ListenerUtil.mutListener.listen(37257)) {
                                        this.fireOnModifiedConversation(conversation);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37265)) {
            // resort!
            this.sort();
        }
        if (!ListenerUtil.mutListener.listen(37273)) {
            if ((ListenerUtil.mutListener.listen(37270) ? (conversations.length >= 1) : (ListenerUtil.mutListener.listen(37269) ? (conversations.length <= 1) : (ListenerUtil.mutListener.listen(37268) ? (conversations.length < 1) : (ListenerUtil.mutListener.listen(37267) ? (conversations.length != 1) : (ListenerUtil.mutListener.listen(37266) ? (conversations.length == 1) : (conversations.length > 1))))))) {
                if (!ListenerUtil.mutListener.listen(37272)) {
                    ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                        @Override
                        public void handle(ConversationListener listener) {
                            if (!ListenerUtil.mutListener.listen(37271)) {
                                listener.onModifiedAll();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public synchronized void clear(@NonNull MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(37277)) {
            switch(receiver.getType()) {
                case MessageReceiver.Type_CONTACT:
                    if (!ListenerUtil.mutListener.listen(37274)) {
                        this.removed(((ContactMessageReceiver) receiver).getContact());
                    }
                    break;
                case MessageReceiver.Type_GROUP:
                    if (!ListenerUtil.mutListener.listen(37275)) {
                        this.removed(((GroupMessageReceiver) receiver).getGroup());
                    }
                    break;
                case MessageReceiver.Type_DISTRIBUTION_LIST:
                    if (!ListenerUtil.mutListener.listen(37276)) {
                        this.removed(((DistributionListMessageReceiver) receiver).getDistributionList());
                    }
                    break;
                default:
                    throw new IllegalStateException("Got ReceiverModel with invalid receiver type!");
            }
        }
    }

    private synchronized boolean clear(final ConversationModel conversation, boolean removeFromCache) {
        if (!ListenerUtil.mutListener.listen(37279)) {
            {
                long _loopCounter381 = 0;
                for (AbstractMessageModel m : this.messageService.getMessagesForReceiver(conversation.getReceiver())) {
                    ListenerUtil.loopListener.listen("_loopCounter381", ++_loopCounter381);
                    if (!ListenerUtil.mutListener.listen(37278)) {
                        this.messageService.remove(m, true);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37280)) {
            // Remove tags
            this.conversationTagService.removeAll(conversation);
        }
        if (!ListenerUtil.mutListener.listen(37291)) {
            // Remove from cache if the conversation is a contact conversation
            if ((ListenerUtil.mutListener.listen(37282) ? (removeFromCache && ((ListenerUtil.mutListener.listen(37281) ? (!conversation.isGroupConversation() || !conversation.isDistributionListConversation()) : (!conversation.isGroupConversation() && !conversation.isDistributionListConversation())))) : (removeFromCache || ((ListenerUtil.mutListener.listen(37281) ? (!conversation.isGroupConversation() || !conversation.isDistributionListConversation()) : (!conversation.isGroupConversation() && !conversation.isDistributionListConversation())))))) {
                synchronized (this.conversationCache) {
                    if (!ListenerUtil.mutListener.listen(37287)) {
                        this.conversationCache.remove(conversation);
                    }
                }
                if (!ListenerUtil.mutListener.listen(37289)) {
                    ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                        @Override
                        public void handle(ConversationListener listener) {
                            if (!ListenerUtil.mutListener.listen(37288)) {
                                listener.onRemoved(conversation);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(37290)) {
                    // resort!
                    this.sort();
                }
                return true;
            } else {
                if (!ListenerUtil.mutListener.listen(37283)) {
                    conversation.setLatestMessage(null);
                }
                if (!ListenerUtil.mutListener.listen(37284)) {
                    conversation.setMessageCount(0);
                }
                if (!ListenerUtil.mutListener.listen(37285)) {
                    conversation.setUnreadCount(0);
                }
                if (!ListenerUtil.mutListener.listen(37286)) {
                    this.fireOnModifiedConversation(conversation);
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean removed(DistributionListModel distributionListModel) {
        return new DistributionListConversationModelParser().removed(distributionListModel);
    }

    @Override
    public synchronized boolean removed(GroupModel groupModel) {
        return new GroupConversationModelParser().removed(groupModel);
    }

    @Override
    public synchronized boolean removed(ContactModel contactModel) {
        return new ContactConversationModelParser().removed(contactModel);
    }

    @Override
    public synchronized boolean reset() {
        synchronized (this.conversationCache) {
            if (!ListenerUtil.mutListener.listen(37292)) {
                this.conversationCache.clear();
            }
        }
        return true;
    }

    @Override
    public boolean hasConversations() {
        synchronized (this.conversationCache) {
            if (!ListenerUtil.mutListener.listen(37298)) {
                if ((ListenerUtil.mutListener.listen(37297) ? (this.conversationCache.size() >= 0) : (ListenerUtil.mutListener.listen(37296) ? (this.conversationCache.size() <= 0) : (ListenerUtil.mutListener.listen(37295) ? (this.conversationCache.size() < 0) : (ListenerUtil.mutListener.listen(37294) ? (this.conversationCache.size() != 0) : (ListenerUtil.mutListener.listen(37293) ? (this.conversationCache.size() == 0) : (this.conversationCache.size() > 0))))))) {
                    return true;
                }
            }
        }
        long count = this.databaseServiceNew.getDistributionListMessageModelFactory().count();
        if (!ListenerUtil.mutListener.listen(37304)) {
            if ((ListenerUtil.mutListener.listen(37303) ? (count >= 0) : (ListenerUtil.mutListener.listen(37302) ? (count <= 0) : (ListenerUtil.mutListener.listen(37301) ? (count < 0) : (ListenerUtil.mutListener.listen(37300) ? (count != 0) : (ListenerUtil.mutListener.listen(37299) ? (count == 0) : (count > 0))))))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(37305)) {
            count = this.databaseServiceNew.getMessageModelFactory().count();
        }
        if (!ListenerUtil.mutListener.listen(37311)) {
            if ((ListenerUtil.mutListener.listen(37310) ? (count >= 0) : (ListenerUtil.mutListener.listen(37309) ? (count <= 0) : (ListenerUtil.mutListener.listen(37308) ? (count < 0) : (ListenerUtil.mutListener.listen(37307) ? (count != 0) : (ListenerUtil.mutListener.listen(37306) ? (count == 0) : (count > 0))))))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(37312)) {
            count = this.databaseServiceNew.getGroupMessageModelFactory().count();
        }
        return (ListenerUtil.mutListener.listen(37317) ? (count >= 0) : (ListenerUtil.mutListener.listen(37316) ? (count <= 0) : (ListenerUtil.mutListener.listen(37315) ? (count < 0) : (ListenerUtil.mutListener.listen(37314) ? (count != 0) : (ListenerUtil.mutListener.listen(37313) ? (count == 0) : (count > 0))))));
    }

    private void fireOnModifiedConversation(final ConversationModel c) {
        if (!ListenerUtil.mutListener.listen(37318)) {
            this.fireOnModifiedConversation(c, null);
        }
    }

    private void fireOnModifiedConversation(final ConversationModel c, final Integer oldPosition) {
        if (!ListenerUtil.mutListener.listen(37320)) {
            ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                @Override
                public void handle(ConversationListener listener) {
                    if (!ListenerUtil.mutListener.listen(37319)) {
                        listener.onModified(c, oldPosition);
                    }
                }
            });
        }
    }

    private ConversationModelParser createParser(AbstractMessageModel m) {
        if (!ListenerUtil.mutListener.listen(37321)) {
            if (m instanceof GroupMessageModel) {
                return new GroupConversationModelParser();
            } else if (m instanceof DistributionListMessageModel) {
                return new DistributionListConversationModelParser();
            } else if (m instanceof MessageModel) {
                return new ContactConversationModelParser();
            }
        }
        return null;
    }

    private abstract class ConversationModelParser<I, M extends AbstractMessageModel, P> {

        public abstract boolean belongsTo(ConversationModel conversationModel, I model);

        public abstract ConversationModel parseResult(ConversationResult result, ConversationModel conversationModel, boolean addToCache);

        public abstract List<ConversationResult> select(I model);

        public abstract List<ConversationResult> selectAll(boolean archived);

        protected abstract I getIndex(M messageModel);

        protected abstract I getIndex(P parentObject);

        public final ConversationModel getCached(final I index) {
            if (index == null) {
                return null;
            }
            synchronized (conversationCache) {
                return Functional.select(conversationCache, new IPredicateNonNull<ConversationModel>() {

                    @Override
                    public boolean apply(@NonNull ConversationModel conversationModel) {
                        return belongsTo(conversationModel, index);
                    }
                });
            }
        }

        public final void processAll() {
            List<ConversationResult> res = this.selectAll(false);
            if (!ListenerUtil.mutListener.listen(37323)) {
                {
                    long _loopCounter382 = 0;
                    for (ConversationResult r : res) {
                        ListenerUtil.loopListener.listen("_loopCounter382", ++_loopCounter382);
                        if (!ListenerUtil.mutListener.listen(37322)) {
                            this.parseResult(r, null, true);
                        }
                    }
                }
            }
        }

        public final List<ConversationModel> processArchived(List<ConversationModel> conversationModels, String constraint) {
            List<ConversationResult> res = this.selectAll(true);
            if (!ListenerUtil.mutListener.listen(37331)) {
                if (!TestUtil.empty(constraint)) {
                    if (!ListenerUtil.mutListener.listen(37326)) {
                        constraint = constraint.toLowerCase();
                    }
                    if (!ListenerUtil.mutListener.listen(37330)) {
                        {
                            long _loopCounter384 = 0;
                            for (ConversationResult r : res) {
                                ListenerUtil.loopListener.listen("_loopCounter384", ++_loopCounter384);
                                ConversationModel conversationModel = this.parseResult(r, null, false);
                                String title = conversationModel.toString();
                                if (!ListenerUtil.mutListener.listen(37329)) {
                                    if (!TestUtil.empty(title)) {
                                        if (!ListenerUtil.mutListener.listen(37328)) {
                                            if (title.toLowerCase().contains(constraint)) {
                                                if (!ListenerUtil.mutListener.listen(37327)) {
                                                    conversationModels.add(conversationModel);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(37325)) {
                        {
                            long _loopCounter383 = 0;
                            for (ConversationResult r : res) {
                                ListenerUtil.loopListener.listen("_loopCounter383", ++_loopCounter383);
                                if (!ListenerUtil.mutListener.listen(37324)) {
                                    conversationModels.add(this.parseResult(r, null, false));
                                }
                            }
                        }
                    }
                }
            }
            return conversationModels;
        }

        public final ConversationModel getSelected(final I index) {
            List<ConversationResult> results = this.select(index);
            if (!ListenerUtil.mutListener.listen(37338)) {
                if ((ListenerUtil.mutListener.listen(37337) ? (results != null || (ListenerUtil.mutListener.listen(37336) ? (results.size() >= 0) : (ListenerUtil.mutListener.listen(37335) ? (results.size() <= 0) : (ListenerUtil.mutListener.listen(37334) ? (results.size() < 0) : (ListenerUtil.mutListener.listen(37333) ? (results.size() != 0) : (ListenerUtil.mutListener.listen(37332) ? (results.size() == 0) : (results.size() > 0))))))) : (results != null && (ListenerUtil.mutListener.listen(37336) ? (results.size() >= 0) : (ListenerUtil.mutListener.listen(37335) ? (results.size() <= 0) : (ListenerUtil.mutListener.listen(37334) ? (results.size() < 0) : (ListenerUtil.mutListener.listen(37333) ? (results.size() != 0) : (ListenerUtil.mutListener.listen(37332) ? (results.size() == 0) : (results.size() > 0))))))))) {
                    return this.parseResult(results.get(0), null, true);
                }
            }
            return null;
        }

        public final ConversationModel refresh(P parentObject) {
            I index = this.getIndex(parentObject);
            ConversationModel model = this.getCached(index);
            boolean newConversationModel = false;
            if (!ListenerUtil.mutListener.listen(37345)) {
                if (model == null) {
                    if (!ListenerUtil.mutListener.listen(37342)) {
                        newConversationModel = true;
                    }
                    if (!ListenerUtil.mutListener.listen(37343)) {
                        model = this.getSelected(index);
                    }
                    if (!ListenerUtil.mutListener.listen(37344)) {
                        // resort
                        sort();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(37341)) {
                        // refresh name if it's a distribution list
                        if ((ListenerUtil.mutListener.listen(37339) ? (model.isDistributionListConversation() || parentObject instanceof DistributionListModel) : (model.isDistributionListConversation() && parentObject instanceof DistributionListModel))) {
                            if (!ListenerUtil.mutListener.listen(37340)) {
                                model.getDistributionList().setName(((DistributionListModel) parentObject).getName());
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37346)) {
                if (model == null) {
                    return null;
                }
            }
            final ConversationModel finalModel = model;
            if (!ListenerUtil.mutListener.listen(37353)) {
                if (newConversationModel) {
                    if (!ListenerUtil.mutListener.listen(37350)) {
                        logger.debug("refresh modified parent NEW");
                    }
                    if (!ListenerUtil.mutListener.listen(37352)) {
                        ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                            @Override
                            public void handle(ConversationListener listener) {
                                if (!ListenerUtil.mutListener.listen(37351)) {
                                    listener.onNew(finalModel);
                                }
                            }
                        });
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(37347)) {
                        logger.debug("refresh modified parent MODIFIED");
                    }
                    if (!ListenerUtil.mutListener.listen(37349)) {
                        ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                            @Override
                            public void handle(ConversationListener listener) {
                                if (!ListenerUtil.mutListener.listen(37348)) {
                                    listener.onModified(finalModel, null);
                                }
                            }
                        });
                    }
                }
            }
            return model;
        }

        public final ConversationModel refresh(@Nullable M modifiedMessageModel) {
            if (!ListenerUtil.mutListener.listen(37354)) {
                if (modifiedMessageModel == null) {
                    return null;
                }
            }
            // Look up conversation in cache
            I index = this.getIndex(modifiedMessageModel);
            ConversationModel model = this.getCached(index);
            // On cache miss, get the conversation from the DB
            boolean newConversationModel = false;
            if (!ListenerUtil.mutListener.listen(37357)) {
                if (model == null) {
                    if (!ListenerUtil.mutListener.listen(37355)) {
                        newConversationModel = true;
                    }
                    if (!ListenerUtil.mutListener.listen(37356)) {
                        model = this.getSelected(index);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37358)) {
                // If conversation was not found, give up
                if (model == null) {
                    return null;
                }
            }
            if (!ListenerUtil.mutListener.listen(37367)) {
                if (((ListenerUtil.mutListener.listen(37364) ? (model.getLatestMessage() == null && (ListenerUtil.mutListener.listen(37363) ? (model.getLatestMessage().getId() >= modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37362) ? (model.getLatestMessage().getId() <= modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37361) ? (model.getLatestMessage().getId() > modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37360) ? (model.getLatestMessage().getId() != modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37359) ? (model.getLatestMessage().getId() == modifiedMessageModel.getId()) : (model.getLatestMessage().getId() < modifiedMessageModel.getId()))))))) : (model.getLatestMessage() == null || (ListenerUtil.mutListener.listen(37363) ? (model.getLatestMessage().getId() >= modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37362) ? (model.getLatestMessage().getId() <= modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37361) ? (model.getLatestMessage().getId() > modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37360) ? (model.getLatestMessage().getId() != modifiedMessageModel.getId()) : (ListenerUtil.mutListener.listen(37359) ? (model.getLatestMessage().getId() == modifiedMessageModel.getId()) : (model.getLatestMessage().getId() < modifiedMessageModel.getId())))))))))) {
                    if (!ListenerUtil.mutListener.listen(37365)) {
                        // set this message as latest message
                        model.setLatestMessage(modifiedMessageModel);
                    }
                    if (!ListenerUtil.mutListener.listen(37366)) {
                        // increase message count
                        model.setMessageCount(model.getMessageCount() + 1);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37374)) {
                if ((ListenerUtil.mutListener.listen(37368) ? (model.getReceiver() != null || MessageUtil.isUnread(model.getLatestMessage())) : (model.getReceiver() != null && MessageUtil.isUnread(model.getLatestMessage())))) {
                    if (!ListenerUtil.mutListener.listen(37373)) {
                        // update unread count
                        model.setUnreadCount(model.getReceiver().getUnreadMessagesCount());
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(37372)) {
                        if (model.getLatestMessage() == null) {
                            if (!ListenerUtil.mutListener.listen(37371)) {
                                // If there are no messages, mark the conversation as read
                                model.setUnreadCount(0);
                            }
                        } else if ((ListenerUtil.mutListener.listen(37369) ? (model.getLatestMessage().getId() == modifiedMessageModel.getId() || modifiedMessageModel.isRead()) : (model.getLatestMessage().getId() == modifiedMessageModel.getId() && modifiedMessageModel.isRead()))) {
                            if (!ListenerUtil.mutListener.listen(37370)) {
                                // and if it's read, mark the entire conversation as read.
                                model.setUnreadCount(0);
                            }
                        }
                    }
                }
            }
            final ConversationModel finalModel = model;
            final int oldPosition = model.getPosition();
            if (!ListenerUtil.mutListener.listen(37375)) {
                sort();
            }
            if (!ListenerUtil.mutListener.listen(37380)) {
                if (newConversationModel) {
                    if (!ListenerUtil.mutListener.listen(37378)) {
                        logger.debug("refresh modified message NEW");
                    }
                    if (!ListenerUtil.mutListener.listen(37379)) {
                        ListenerManager.conversationListeners.handle(listener -> listener.onNew(finalModel));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(37376)) {
                        logger.debug("refresh modified message MODIFIED");
                    }
                    if (!ListenerUtil.mutListener.listen(37377)) {
                        ListenerManager.conversationListeners.handle(listener -> {
                            listener.onModified(finalModel, oldPosition != finalModel.getPosition() ? oldPosition : null);
                        });
                    }
                }
            }
            return model;
        }

        public final ConversationModel messageDeleted(M messageModel) {
            final ConversationModel model = this.getCached(this.getIndex(messageModel));
            if (!ListenerUtil.mutListener.listen(37388)) {
                // if the newest message is deleted, reload
                if ((ListenerUtil.mutListener.listen(37382) ? ((ListenerUtil.mutListener.listen(37381) ? (model != null || model.getLatestMessage() != null) : (model != null && model.getLatestMessage() != null)) || messageModel != null) : ((ListenerUtil.mutListener.listen(37381) ? (model != null || model.getLatestMessage() != null) : (model != null && model.getLatestMessage() != null)) && messageModel != null))) {
                    if (!ListenerUtil.mutListener.listen(37387)) {
                        if (model.getLatestMessage().getId() == messageModel.getId()) {
                            if (!ListenerUtil.mutListener.listen(37383)) {
                                updateLatestConversationMessageAfterDelete(model);
                            }
                            final int oldPosition = model.getPosition();
                            if (!ListenerUtil.mutListener.listen(37384)) {
                                sort();
                            }
                            if (!ListenerUtil.mutListener.listen(37386)) {
                                ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                                    @Override
                                    public void handle(ConversationListener listener) {
                                        if (!ListenerUtil.mutListener.listen(37385)) {
                                            listener.onModified(model, oldPosition != model.getPosition() ? oldPosition : null);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
            return model;
        }

        public final boolean removed(P parentObject) {
            ConversationModel model = this.getCached(this.getIndex(parentObject));
            if (!ListenerUtil.mutListener.listen(37390)) {
                if (model != null) {
                    if (!ListenerUtil.mutListener.listen(37389)) {
                        // remove from cache
                        clear(model, true);
                    }
                }
            }
            return true;
        }

        protected List<ConversationResult> parse(String query) {
            return parse(query, null);
        }

        protected List<ConversationResult> parse(String query, String[] args) {
            List<ConversationResult> r = new ArrayList<>();
            Cursor c = databaseServiceNew.getReadableDatabase().rawQuery(query, args);
            if (!ListenerUtil.mutListener.listen(37394)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(37393)) {
                            {
                                long _loopCounter385 = 0;
                                while (c.moveToNext()) {
                                    ListenerUtil.loopListener.listen("_loopCounter385", ++_loopCounter385);
                                    if (!ListenerUtil.mutListener.listen(37392)) {
                                        r.add(new ConversationResult(c.getInt(0), c.getLong(1), c.getString(2)));
                                    }
                                }
                            }
                        }
                    } finally {
                        if (!ListenerUtil.mutListener.listen(37391)) {
                            c.close();
                        }
                    }
                }
            }
            return r;
        }
    }

    private class ContactConversationModelParser extends ConversationModelParser<String, MessageModel, ContactModel> {

        @Override
        public boolean belongsTo(ConversationModel conversationModel, String identity) {
            return (ListenerUtil.mutListener.listen(37395) ? (conversationModel.getContact() != null || conversationModel.getContact().getIdentity().equals(identity)) : (conversationModel.getContact() != null && conversationModel.getContact().getIdentity().equals(identity)));
        }

        @Override
        public List<ConversationResult> select(String identity) {
            return this.parse("SELECT MAX(id), COUNT(*), identity as id FROM message m WHERE " + "m.identity = ? " + "AND m.isStatusMessage = 0 " + "AND m.isSaved = 1 " + "GROUP BY identity", new String[] { identity });
        }

        @Override
        public List<ConversationResult> selectAll(boolean archived) {
            return this.parse("SELECT MAX(m.id), COUNT(*), m.identity as id FROM message m " + "INNER JOIN contacts c ON c.identity = m.identity " + "WHERE m.isSaved = 1 " + "AND c.isArchived = " + (archived ? "1 " : "0 ") + "GROUP BY m.identity");
        }

        @Override
        public ConversationModel parseResult(ConversationResult result, ConversationModel conversationModel, boolean addToCache) {
            final String identity = result.refId;
            // no cached contacts!?
            final ContactModel contactModel = contactService.getByIdentity(identity);
            if (!ListenerUtil.mutListener.listen(37413)) {
                if (contactModel != null) {
                    final ContactMessageReceiver receiver = contactService.createReceiver(contactModel);
                    if (!ListenerUtil.mutListener.listen(37400)) {
                        if (conversationModel == null) {
                            if (!ListenerUtil.mutListener.listen(37396)) {
                                conversationModel = new ConversationModel(receiver);
                            }
                            if (!ListenerUtil.mutListener.listen(37399)) {
                                if ((ListenerUtil.mutListener.listen(37397) ? (addToCache || !contactModel.isArchived()) : (addToCache && !contactModel.isArchived()))) {
                                    synchronized (conversationCache) {
                                        if (!ListenerUtil.mutListener.listen(37398)) {
                                            conversationCache.add(conversationModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37412)) {
                        if ((ListenerUtil.mutListener.listen(37405) ? (result.count >= 0) : (ListenerUtil.mutListener.listen(37404) ? (result.count <= 0) : (ListenerUtil.mutListener.listen(37403) ? (result.count < 0) : (ListenerUtil.mutListener.listen(37402) ? (result.count != 0) : (ListenerUtil.mutListener.listen(37401) ? (result.count == 0) : (result.count > 0))))))) {
                            MessageModel latestMessage = messageService.getContactMessageModel(result.messageId, true);
                            if (!ListenerUtil.mutListener.listen(37408)) {
                                conversationModel.setLatestMessage(latestMessage);
                            }
                            if (!ListenerUtil.mutListener.listen(37410)) {
                                if (MessageUtil.isUnread(latestMessage)) {
                                    if (!ListenerUtil.mutListener.listen(37409)) {
                                        // update unread message count only if the "newest" message is unread (ANDR-398)
                                        conversationModel.setUnreadCount(receiver.getUnreadMessagesCount());
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(37411)) {
                                conversationModel.setMessageCount(result.count);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(37406)) {
                                conversationModel.setUnreadCount(0);
                            }
                            if (!ListenerUtil.mutListener.listen(37407)) {
                                conversationModel.setMessageCount(0);
                            }
                        }
                    }
                    return conversationModel;
                }
            }
            return null;
        }

        @Override
        protected String getIndex(MessageModel messageModel) {
            return messageModel != null ? messageModel.getIdentity() : null;
        }

        @Override
        protected String getIndex(ContactModel contactModel) {
            return contactModel != null ? contactModel.getIdentity() : null;
        }
    }

    private class GroupConversationModelParser extends ConversationModelParser<Integer, GroupMessageModel, GroupModel> {

        @Override
        public boolean belongsTo(ConversationModel conversationModel, Integer groupId) {
            return (ListenerUtil.mutListener.listen(37414) ? (conversationModel.getGroup() != null || conversationModel.getGroup().getId() == groupId) : (conversationModel.getGroup() != null && conversationModel.getGroup().getId() == groupId));
        }

        @Override
        public ConversationModel parseResult(ConversationResult result, ConversationModel conversationModel, boolean addToCache) {
            final GroupModel groupModel = groupService.getById(Integer.valueOf(result.refId));
            GroupMessageReceiver receiver = groupService.createReceiver(groupModel);
            if (!ListenerUtil.mutListener.listen(37432)) {
                if (groupModel != null) {
                    if (!ListenerUtil.mutListener.listen(37419)) {
                        if (conversationModel == null) {
                            if (!ListenerUtil.mutListener.listen(37415)) {
                                conversationModel = new ConversationModel(receiver);
                            }
                            if (!ListenerUtil.mutListener.listen(37418)) {
                                if ((ListenerUtil.mutListener.listen(37416) ? (addToCache || !groupModel.isArchived()) : (addToCache && !groupModel.isArchived()))) {
                                    synchronized (conversationCache) {
                                        if (!ListenerUtil.mutListener.listen(37417)) {
                                            conversationCache.add(conversationModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37430)) {
                        if ((ListenerUtil.mutListener.listen(37424) ? (result.count >= 0) : (ListenerUtil.mutListener.listen(37423) ? (result.count <= 0) : (ListenerUtil.mutListener.listen(37422) ? (result.count < 0) : (ListenerUtil.mutListener.listen(37421) ? (result.count != 0) : (ListenerUtil.mutListener.listen(37420) ? (result.count == 0) : (result.count > 0))))))) {
                            GroupMessageModel latestMessage = messageService.getGroupMessageModel(result.messageId, true);
                            if (!ListenerUtil.mutListener.listen(37426)) {
                                conversationModel.setLatestMessage(latestMessage);
                            }
                            if (!ListenerUtil.mutListener.listen(37428)) {
                                if (MessageUtil.isUnread(latestMessage)) {
                                    if (!ListenerUtil.mutListener.listen(37427)) {
                                        // update unread message count only if the "newest" message is unread (ANDR-398)
                                        conversationModel.setUnreadCount(receiver.getUnreadMessagesCount());
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(37429)) {
                                conversationModel.setMessageCount(result.count);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(37425)) {
                                conversationModel.setUnreadCount(0);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37431)) {
                        conversationModel.setMessageCount(result.count);
                    }
                    return conversationModel;
                }
            }
            return null;
        }

        @Override
        public List<ConversationResult> select(Integer groupId) {
            return this.parse("SELECT MAX(gm.id), COUNT(gm.id), g.id FROM m_group g " + "LEFT OUTER JOIN m_group_message gm " + "ON gm.groupId = g.id " + "AND gm.isStatusMessage = 0 " + "AND gm.isSaved = 1 " + "WHERE g.id = ? " + "GROUP BY g.id", new String[] { String.valueOf(groupId) });
        }

        @Override
        public List<ConversationResult> selectAll(boolean archived) {
            return this.parse("SELECT MAX(gm.id), COUNT(gm.id), g.id FROM m_group g " + "LEFT OUTER JOIN m_group_message gm " + "ON gm.groupId = g.id " + "AND gm.isStatusMessage = 0 " + "AND gm.isSaved = 1 " + "WHERE g.deleted != 1 " + "AND g.isArchived = " + (archived ? "1 " : "0 ") + "GROUP BY g.id");
        }

        @Override
        protected Integer getIndex(GroupMessageModel messageModel) {
            return messageModel != null ? messageModel.getGroupId() : null;
        }

        @Override
        protected Integer getIndex(GroupModel groupModel) {
            return groupModel != null ? groupModel.getId() : null;
        }
    }

    private class DistributionListConversationModelParser extends ConversationModelParser<Integer, DistributionListMessageModel, DistributionListModel> {

        @Override
        public boolean belongsTo(ConversationModel conversationModel, Integer distributionListId) {
            return (ListenerUtil.mutListener.listen(37433) ? (conversationModel.getDistributionList() != null || conversationModel.getDistributionList().getId() == distributionListId) : (conversationModel.getDistributionList() != null && conversationModel.getDistributionList().getId() == distributionListId));
        }

        @Override
        public ConversationModel parseResult(ConversationResult result, ConversationModel conversationModel, boolean addToCache) {
            final DistributionListModel distributionListModel = distributionListService.getById(Integer.valueOf(result.refId));
            DistributionListMessageReceiver receiver = distributionListService.createReceiver(distributionListModel);
            if (!ListenerUtil.mutListener.listen(37448)) {
                if (distributionListModel != null) {
                    if (!ListenerUtil.mutListener.listen(37438)) {
                        if (conversationModel == null) {
                            if (!ListenerUtil.mutListener.listen(37434)) {
                                conversationModel = new ConversationModel(receiver);
                            }
                            if (!ListenerUtil.mutListener.listen(37437)) {
                                if ((ListenerUtil.mutListener.listen(37435) ? (addToCache || !distributionListModel.isArchived()) : (addToCache && !distributionListModel.isArchived()))) {
                                    synchronized (conversationCache) {
                                        if (!ListenerUtil.mutListener.listen(37436)) {
                                            conversationCache.add(conversationModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37445)) {
                        if ((ListenerUtil.mutListener.listen(37443) ? (result.count >= 0) : (ListenerUtil.mutListener.listen(37442) ? (result.count <= 0) : (ListenerUtil.mutListener.listen(37441) ? (result.count < 0) : (ListenerUtil.mutListener.listen(37440) ? (result.count != 0) : (ListenerUtil.mutListener.listen(37439) ? (result.count == 0) : (result.count > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(37444)) {
                                conversationModel.setLatestMessage(messageService.getDistributionListMessageModel(result.messageId, true));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37446)) {
                        conversationModel.setUnreadCount(0);
                    }
                    if (!ListenerUtil.mutListener.listen(37447)) {
                        conversationModel.setMessageCount(result.count);
                    }
                    return conversationModel;
                }
            }
            return null;
        }

        @Override
        public List<ConversationResult> select(Integer distributionListId) {
            return this.parse("SELECT MAX(dm.id), COUNT(dm.id), d.id FROM distribution_list d " + "LEFT OUTER JOIN distribution_list_message dm " + "ON dm.distributionListId = d.id " + "AND dm.isStatusMessage = 0 " + "AND dm.isSaved = 1 " + "WHERE d.id = ? " + "GROUP BY d.id", new String[] { String.valueOf(distributionListId) });
        }

        @Override
        public List<ConversationResult> selectAll(boolean archived) {
            return this.parse("SELECT MAX(dm.id), COUNT(dm.id), d.id FROM distribution_list d " + "LEFT OUTER JOIN distribution_list_message dm " + "ON dm.distributionListId = d.id " + "AND dm.isStatusMessage = 0 " + "AND dm.isSaved = 1 " + "WHERE d.isArchived = " + (archived ? "1 " : "0 ") + "GROUP BY d.id");
        }

        @Override
        protected Integer getIndex(DistributionListMessageModel messageModel) {
            return messageModel != null ? messageModel.getDistributionListId() : null;
        }

        @Override
        protected Integer getIndex(DistributionListModel distributionListModel) {
            return distributionListModel != null ? distributionListModel.getId() : null;
        }
    }

    private void updateLatestConversationMessageAfterDelete(ConversationModel conversationModel) {
        AbstractMessageModel newestMessage;
        // dirty diana hack
        newestMessage = Functional.select(this.messageService.getMessagesForReceiver(conversationModel.getReceiver(), new MessageService.MessageFilter() {

            @Override
            public long getPageSize() {
                return 1;
            }

            @Override
            public Integer getPageReferenceId() {
                return null;
            }

            @Override
            public boolean withStatusMessages() {
                return false;
            }

            @Override
            public boolean withUnsaved() {
                return false;
            }

            @Override
            public boolean onlyUnread() {
                return false;
            }

            @Override
            public boolean onlyDownloaded() {
                return true;
            }

            @Override
            public MessageType[] types() {
                return null;
            }

            @Override
            public int[] contentTypes() {
                return null;
            }
        }), new IPredicateNonNull<AbstractMessageModel>() {

            @Override
            public boolean apply(@NonNull AbstractMessageModel type) {
                return true;
            }
        });
        if (!ListenerUtil.mutListener.listen(37449)) {
            conversationModel.setLatestMessage(newestMessage);
        }
        if (!ListenerUtil.mutListener.listen(37453)) {
            if ((ListenerUtil.mutListener.listen(37451) ? (newestMessage == null && ((ListenerUtil.mutListener.listen(37450) ? (newestMessage.isOutbox() && newestMessage.isRead()) : (newestMessage.isOutbox() || newestMessage.isRead())))) : (newestMessage == null || ((ListenerUtil.mutListener.listen(37450) ? (newestMessage.isOutbox() && newestMessage.isRead()) : (newestMessage.isOutbox() || newestMessage.isRead())))))) {
                if (!ListenerUtil.mutListener.listen(37452)) {
                    conversationModel.setUnreadCount(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37459)) {
            if (newestMessage == null) {
                if (!ListenerUtil.mutListener.listen(37458)) {
                    if ((ListenerUtil.mutListener.listen(37454) ? (conversationModel.isGroupConversation() && conversationModel.isDistributionListConversation()) : (conversationModel.isGroupConversation() || conversationModel.isDistributionListConversation()))) {
                        if (!ListenerUtil.mutListener.listen(37457)) {
                            // do not remove groups and distribution list conversations from cache as they should still be accessible in message list
                            conversationModel.setMessageCount(0);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(37456)) {
                            if (conversationModel.getMessageCount() == 1) {
                                // remove model from cache completely
                                synchronized (this.conversationCache) {
                                    if (!ListenerUtil.mutListener.listen(37455)) {
                                        this.conversationCache.remove(conversationModel);
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
