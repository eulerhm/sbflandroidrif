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
package ch.threema.app.services;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.SparseArray;
import com.neilalexander.jnacl.NaCl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.GroupDetailActivity;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.exceptions.NoIdentityException;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.APIConnector;
import ch.threema.client.AbstractGroupMessage;
import ch.threema.client.Base32;
import ch.threema.client.BlobLoader;
import ch.threema.client.BlobUploader;
import ch.threema.client.GroupCreateMessage;
import ch.threema.client.GroupDeletePhotoMessage;
import ch.threema.client.GroupId;
import ch.threema.client.GroupLeaveMessage;
import ch.threema.client.GroupRenameMessage;
import ch.threema.client.GroupRequestSyncMessage;
import ch.threema.client.GroupSetPhotoMessage;
import ch.threema.client.IdentityState;
import ch.threema.client.MessageId;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.Utils;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.factories.GroupRequestSyncLogModelFactory;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupMemberModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.GroupRequestSyncLogModel;
import ch.threema.storage.models.access.Access;
import ch.threema.storage.models.access.GroupAccessModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupServiceImpl implements GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    private final ApiService apiService;

    private final GroupApiService groupApiService;

    private final UserService userService;

    private final ContactService contactService;

    private final DatabaseServiceNew databaseServiceNew;

    private final AvatarCacheService avatarCacheService;

    private final FileService fileService;

    private final PreferenceService preferenceService;

    private final WallpaperService wallpaperService;

    private final DeadlineListService mutedChatsListService, hiddenChatsListService;

    private final RingtoneService ringtoneService;

    private final IdListService blackListService;

    private final SparseArray<Map<String, Integer>> groupMemberColorCache;

    private final SparseArray<GroupModel> groupModelCache;

    private final SparseArray<String[]> groupIdentityCache;

    private final List<AbstractGroupMessage> pendingGroupMessages = new ArrayList<>();

    class GroupPhotoUploadResult {

        public byte[] bitmapArray;

        public byte[] blobId;

        public byte[] encryptionKey;

        public int size;
    }

    public GroupServiceImpl(CacheService cacheService, ApiService apiService, GroupApiService groupApiService, UserService userService, ContactService contactService, DatabaseServiceNew databaseServiceNew, AvatarCacheService avatarCacheService, FileService fileService, PreferenceService preferenceService, WallpaperService wallpaperService, DeadlineListService mutedChatsListService, DeadlineListService hiddenChatsListService, RingtoneService ringtoneService, IdListService blackListService) {
        this.apiService = apiService;
        this.groupApiService = groupApiService;
        this.userService = userService;
        this.contactService = contactService;
        this.databaseServiceNew = databaseServiceNew;
        this.avatarCacheService = avatarCacheService;
        this.fileService = fileService;
        this.preferenceService = preferenceService;
        this.wallpaperService = wallpaperService;
        this.mutedChatsListService = mutedChatsListService;
        this.hiddenChatsListService = hiddenChatsListService;
        this.ringtoneService = ringtoneService;
        this.blackListService = blackListService;
        this.groupModelCache = cacheService.getGroupModelCache();
        this.groupIdentityCache = cacheService.getGroupIdentityCache();
        this.groupMemberColorCache = cacheService.getGroupMemberColorCache();
    }

    @Override
    public List<GroupModel> getAll() {
        return this.getAll(null);
    }

    @Override
    public List<GroupModel> getAll(GroupFilter filter) {
        List<GroupModel> res = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(38155)) {
            res.addAll(this.databaseServiceNew.getGroupModelFactory().filter(filter));
        }
        if (!ListenerUtil.mutListener.listen(38160)) {
            if ((ListenerUtil.mutListener.listen(38156) ? (filter != null || !filter.withDeserted()) : (filter != null && !filter.withDeserted()))) {
                Iterator iterator = res.iterator();
                if (!ListenerUtil.mutListener.listen(38159)) {
                    {
                        long _loopCounter405 = 0;
                        while (iterator.hasNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter405", ++_loopCounter405);
                            GroupModel groupModel = (GroupModel) iterator.next();
                            if (!ListenerUtil.mutListener.listen(38158)) {
                                if (!isGroupMember(groupModel)) {
                                    if (!ListenerUtil.mutListener.listen(38157)) {
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38162)) {
            {
                long _loopCounter406 = 0;
                for (GroupModel m : res) {
                    ListenerUtil.loopListener.listen("_loopCounter406", ++_loopCounter406);
                    if (!ListenerUtil.mutListener.listen(38161)) {
                        this.cache(m);
                    }
                }
            }
        }
        return res;
    }

    private GroupModel cache(GroupModel groupModel) {
        if (groupModel == null) {
            return null;
        }
        synchronized (this.groupModelCache) {
            GroupModel existingGroupModel = groupModelCache.get(groupModel.getId());
            if (existingGroupModel != null) {
                return existingGroupModel;
            }
            if (!ListenerUtil.mutListener.listen(38163)) {
                groupModelCache.put(groupModel.getId(), groupModel);
            }
            return groupModel;
        }
    }

    @Override
    public boolean removeAllMembersAndLeave(final GroupModel groupModel) {
        String[] identities = new String[] { groupModel.getCreatorIdentity() };
        try {
            if (!ListenerUtil.mutListener.listen(38165)) {
                updateGroup(groupModel, null, identities, null, false);
            }
            if (!ListenerUtil.mutListener.listen(38168)) {
                if (leaveGroup(groupModel)) {
                    if (!ListenerUtil.mutListener.listen(38167)) {
                        ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                            @Override
                            public void handle(GroupListener listener) {
                                if (!ListenerUtil.mutListener.listen(38166)) {
                                    listener.onLeave(groupModel);
                                }
                            }
                        });
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(38164)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    @Override
    public boolean leaveGroup(final GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(38169)) {
            if (groupModel == null) {
                return false;
            }
        }
        // Get current display name (#ANDR-744)
        String displayName = createReceiver(groupModel).getDisplayName();
        String[] identities = this.getGroupIdentities(groupModel);
        try {
            if (!ListenerUtil.mutListener.listen(38172)) {
                this.groupApiService.sendMessage(groupModel, identities, new GroupApiService.CreateApiMessage() {

                    @Override
                    public AbstractGroupMessage create(MessageId messageId) {
                        GroupLeaveMessage groupLeaveMessage = new GroupLeaveMessage();
                        if (!ListenerUtil.mutListener.listen(38171)) {
                            groupLeaveMessage.setMessageId(messageId);
                        }
                        return groupLeaveMessage;
                    }
                });
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(38170)) {
                logger.error("Exception", e);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(38173)) {
            this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupId(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(38174)) {
            // save with "old" name
            groupModel.setName(displayName);
        }
        if (!ListenerUtil.mutListener.listen(38175)) {
            this.save(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(38176)) {
            // reset cache
            this.resetIdentityCache(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(38178)) {
            // fire kicked
            ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                @Override
                public void handle(GroupListener listener) {
                    if (!ListenerUtil.mutListener.listen(38177)) {
                        listener.onMemberKicked(groupModel, userService.getIdentity(), identities.length);
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean remove(GroupModel groupModel) {
        return this.remove(groupModel, false);
    }

    @Override
    public boolean remove(final GroupModel groupModel, boolean silent) {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(38182)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(38181)) {
                        // cannot assign ballot service fixed in the constructor because of circular dependency
                        ThreemaApplication.getServiceManager().getBallotService().remove(createReceiver(groupModel));
                    }
                } catch (MasterKeyLockedException | FileSystemNotPresentException | NoIdentityException e) {
                    if (!ListenerUtil.mutListener.listen(38180)) {
                        logger.error("Exception removing ballot models", e);
                    }
                    return false;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38179)) {
                    logger.error("Missing serviceManager, cannot delete ballot models for group");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(38183)) {
            this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupId(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(38186)) {
            {
                long _loopCounter407 = 0;
                for (GroupMessageModel messageModel : this.databaseServiceNew.getGroupMessageModelFactory().getByGroupIdUnsorted(groupModel.getId())) {
                    ListenerUtil.loopListener.listen("_loopCounter407", ++_loopCounter407);
                    if (!ListenerUtil.mutListener.listen(38184)) {
                        // remove all message identity models
                        this.databaseServiceNew.getGroupMessagePendingMessageIdModelFactory().delete(messageModel.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(38185)) {
                        // remove all files
                        this.fileService.removeMessageFiles(messageModel, true);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38187)) {
            // now remove all message models!
            this.databaseServiceNew.getGroupMessageModelFactory().deleteByGroupId(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(38188)) {
            // remove avatar
            this.fileService.removeGroupAvatar(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(38189)) {
            this.avatarCacheService.reset(groupModel);
        }
        // remove wallpaper and stuff
        String uniqueIdString = getUniqueIdString(groupModel);
        if (!ListenerUtil.mutListener.listen(38190)) {
            this.wallpaperService.removeWallpaper(uniqueIdString);
        }
        if (!ListenerUtil.mutListener.listen(38191)) {
            this.ringtoneService.removeCustomRingtone(uniqueIdString);
        }
        if (!ListenerUtil.mutListener.listen(38192)) {
            this.mutedChatsListService.remove(uniqueIdString);
        }
        if (!ListenerUtil.mutListener.listen(38193)) {
            this.hiddenChatsListService.remove(uniqueIdString);
        }
        if (!ListenerUtil.mutListener.listen(38194)) {
            groupModel.setDeleted(true);
        }
        if (!ListenerUtil.mutListener.listen(38195)) {
            this.databaseServiceNew.getGroupModelFactory().delete(groupModel);
        }
        synchronized (this.groupModelCache) {
            if (!ListenerUtil.mutListener.listen(38196)) {
                this.groupModelCache.remove(groupModel.getId());
            }
        }
        if (!ListenerUtil.mutListener.listen(38197)) {
            this.resetIdentityCache(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(38200)) {
            if (!silent) {
                if (!ListenerUtil.mutListener.listen(38199)) {
                    ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                        @Override
                        public void handle(GroupListener listener) {
                            if (!ListenerUtil.mutListener.listen(38198)) {
                                listener.onRemove(groupModel);
                            }
                        }
                    });
                }
            }
        }
        return true;
    }

    @Override
    public void removeAll() {
        if (!ListenerUtil.mutListener.listen(38202)) {
            {
                long _loopCounter408 = 0;
                for (GroupModel g : this.getAll()) {
                    ListenerUtil.loopListener.listen("_loopCounter408", ++_loopCounter408);
                    if (!ListenerUtil.mutListener.listen(38201)) {
                        this.remove(g, true);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38203)) {
            this.databaseServiceNew.getGroupRequestSyncLogModelFactory().deleteAll();
        }
    }

    @Override
    public GroupModel getGroup(final AbstractGroupMessage message) {
        GroupModel model;
        try {
            model = this.getByAbstractGroupMessage(message);
        } catch (SQLException e) {
            if (!ListenerUtil.mutListener.listen(38204)) {
                logger.error("Exception", e);
            }
            model = null;
        }
        return model;
    }

    @Override
    public boolean requestSync(AbstractGroupMessage msg, boolean leaveIfMine) {
        if (!ListenerUtil.mutListener.listen(38240)) {
            if (msg != null) {
                if (!ListenerUtil.mutListener.listen(38207)) {
                    // do not send a request to myself
                    if (TestUtil.compare(msg.getGroupCreator(), this.userService.getIdentity())) {
                        if (!ListenerUtil.mutListener.listen(38206)) {
                            if (leaveIfMine) {
                                if (!ListenerUtil.mutListener.listen(38205)) {
                                    // auto leave
                                    this.sendLeave(msg);
                                }
                            } else {
                                return false;
                            }
                        }
                    }
                }
                try {
                    GroupRequestSyncLogModelFactory groupRequestSyncLogModelFactory = this.databaseServiceNew.getGroupRequestSyncLogModelFactory();
                    GroupRequestSyncLogModel model = groupRequestSyncLogModelFactory.get(msg.getGroupId().toString(), msg.getGroupCreator());
                    if (!ListenerUtil.mutListener.listen(38237)) {
                        // send a request sync if the old request sync older than one week or NULL
                        if ((ListenerUtil.mutListener.listen(38219) ? ((ListenerUtil.mutListener.listen(38209) ? (model == null && model.getLastRequest() == null) : (model == null || model.getLastRequest() == null)) && (ListenerUtil.mutListener.listen(38218) ? (model.getLastRequest().getTime() >= ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38217) ? (model.getLastRequest().getTime() <= ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38216) ? (model.getLastRequest().getTime() > ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38215) ? (model.getLastRequest().getTime() != ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38214) ? (model.getLastRequest().getTime() == ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (model.getLastRequest().getTime() < ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))))))))) : ((ListenerUtil.mutListener.listen(38209) ? (model == null && model.getLastRequest() == null) : (model == null || model.getLastRequest() == null)) || (ListenerUtil.mutListener.listen(38218) ? (model.getLastRequest().getTime() >= ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38217) ? (model.getLastRequest().getTime() <= ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38216) ? (model.getLastRequest().getTime() > ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38215) ? (model.getLastRequest().getTime() != ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(38214) ? (model.getLastRequest().getTime() == ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))) : (model.getLastRequest().getTime() < ((ListenerUtil.mutListener.listen(38213) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38212) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38211) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(38210) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))))))))))))) {
                            if (!ListenerUtil.mutListener.listen(38221)) {
                                logger.debug("send request sync to group creator [" + msg.getGroupCreator() + "]");
                            }
                            // send a request sync to the creator!!
                            int messageCount = requestSync(msg.getGroupCreator(), msg.getGroupId());
                            if (!ListenerUtil.mutListener.listen(38236)) {
                                if ((ListenerUtil.mutListener.listen(38226) ? (messageCount >= 1) : (ListenerUtil.mutListener.listen(38225) ? (messageCount <= 1) : (ListenerUtil.mutListener.listen(38224) ? (messageCount > 1) : (ListenerUtil.mutListener.listen(38223) ? (messageCount < 1) : (ListenerUtil.mutListener.listen(38222) ? (messageCount != 1) : (messageCount == 1))))))) {
                                    if (!ListenerUtil.mutListener.listen(38235)) {
                                        if (model == null) {
                                            if (!ListenerUtil.mutListener.listen(38230)) {
                                                model = new GroupRequestSyncLogModel();
                                            }
                                            if (!ListenerUtil.mutListener.listen(38231)) {
                                                model.setAPIGroupId(msg.getGroupId().toString(), msg.getGroupCreator());
                                            }
                                            if (!ListenerUtil.mutListener.listen(38232)) {
                                                model.setCount(1);
                                            }
                                            if (!ListenerUtil.mutListener.listen(38233)) {
                                                model.setLastRequest(new Date());
                                            }
                                            if (!ListenerUtil.mutListener.listen(38234)) {
                                                groupRequestSyncLogModelFactory.create(model);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(38227)) {
                                                model.setLastRequest(new Date());
                                            }
                                            if (!ListenerUtil.mutListener.listen(38228)) {
                                                model.setCount(model.getCount() + 1);
                                            }
                                            if (!ListenerUtil.mutListener.listen(38229)) {
                                                groupRequestSyncLogModelFactory.update(model);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(38220)) {
                                logger.debug("do not send request sync to group creator [" + msg.getGroupCreator() + "]");
                            }
                        }
                    }
                    synchronized (this.pendingGroupMessages) {
                        if (!ListenerUtil.mutListener.listen(38239)) {
                            if (Functional.select(this.pendingGroupMessages, new IPredicateNonNull<AbstractGroupMessage>() {

                                @Override
                                public boolean apply(@NonNull AbstractGroupMessage m) {
                                    return m.getMessageId().toString().equals(m.getMessageId().toString());
                                }
                            }) == null) {
                                if (!ListenerUtil.mutListener.listen(38238)) {
                                    this.pendingGroupMessages.add(msg);
                                }
                            }
                        }
                    }
                    return true;
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(38208)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int requestSync(String groupCreator, GroupId groupId) throws ThreemaException {
        return this.groupApiService.sendMessage(groupId, groupCreator, new String[] { groupCreator }, new GroupApiService.CreateApiMessage() {

            @Override
            public AbstractGroupMessage create(MessageId messageId) {
                GroupRequestSyncMessage groupRequestSyncMessage = new GroupRequestSyncMessage();
                if (!ListenerUtil.mutListener.listen(38241)) {
                    groupRequestSyncMessage.setMessageId(messageId);
                }
                return groupRequestSyncMessage;
            }
        });
    }

    public boolean sendLeave(AbstractGroupMessage msg) {
        if (!ListenerUtil.mutListener.listen(38245)) {
            if (msg != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(38244)) {
                        // send a leave to the creator!!
                        this.groupApiService.sendMessage(msg.getGroupId(), msg.getGroupCreator(), new String[] { msg.getFromIdentity(), msg.getGroupCreator() }, new GroupApiService.CreateApiMessage() {

                            @Override
                            public AbstractGroupMessage create(MessageId messageId) {
                                GroupLeaveMessage groupLeaveMessage = new GroupLeaveMessage();
                                if (!ListenerUtil.mutListener.listen(38243)) {
                                    groupLeaveMessage.setMessageId(messageId);
                                }
                                return groupLeaveMessage;
                            }
                        });
                    }
                    return true;
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(38242)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return false;
    }

    private GroupModel getByAbstractGroupMessage(final AbstractGroupMessage message) throws SQLException {
        synchronized (this.groupModelCache) {
            GroupModel model = Functional.select(this.groupModelCache, new IPredicateNonNull<GroupModel>() {

                @Override
                public boolean apply(@NonNull GroupModel type) {
                    return (ListenerUtil.mutListener.listen(38246) ? (message.getGroupId().toString().equals(type.getApiGroupId()) || message.getGroupCreator().equals(type.getCreatorIdentity())) : (message.getGroupId().toString().equals(type.getApiGroupId()) && message.getGroupCreator().equals(type.getCreatorIdentity())));
                }
            });
            if (!ListenerUtil.mutListener.listen(38249)) {
                if (model == null) {
                    if (!ListenerUtil.mutListener.listen(38247)) {
                        model = this.databaseServiceNew.getGroupModelFactory().getByApiGroupIdAndCreator(message.getGroupId().toString(), message.getGroupCreator());
                    }
                    if (!ListenerUtil.mutListener.listen(38248)) {
                        if (model != null) {
                            return this.cache(model);
                        }
                    }
                } else {
                    return model;
                }
            }
            return null;
        }
    }

    @Override
    public boolean removeMemberFromGroup(GroupLeaveMessage msg) {
        try {
            GroupModel model = this.getByAbstractGroupMessage(msg);
            if (!ListenerUtil.mutListener.listen(38254)) {
                if (model != null) {
                    @GroupState
                    int groupState = getGroupState(model);
                    if (!ListenerUtil.mutListener.listen(38251)) {
                        this.removeMemberFromGroup(model, msg.getFromIdentity());
                    }
                    if (!ListenerUtil.mutListener.listen(38253)) {
                        ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                            @Override
                            public void handle(GroupListener listener) {
                                if (!ListenerUtil.mutListener.listen(38252)) {
                                    listener.onGroupStateChanged(model, groupState, getGroupState(model));
                                }
                            }
                        });
                    }
                    return true;
                } else {
                    // return true to "kill" message from server!
                    return true;
                }
            }
        } catch (SQLException e) {
            if (!ListenerUtil.mutListener.listen(38250)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    private boolean removeMemberFromGroup(final GroupModel group, final String identity) {
        final int previousMemberCount = countMembers(group);
        if (!ListenerUtil.mutListener.listen(38262)) {
            if ((ListenerUtil.mutListener.listen(38259) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(group.getId(), identity) >= 0) : (ListenerUtil.mutListener.listen(38258) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(group.getId(), identity) <= 0) : (ListenerUtil.mutListener.listen(38257) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(group.getId(), identity) < 0) : (ListenerUtil.mutListener.listen(38256) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(group.getId(), identity) != 0) : (ListenerUtil.mutListener.listen(38255) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(group.getId(), identity) == 0) : (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(group.getId(), identity) > 0))))))) {
                if (!ListenerUtil.mutListener.listen(38260)) {
                    this.resetIdentityCache(group.getId());
                }
                if (!ListenerUtil.mutListener.listen(38261)) {
                    ListenerManager.groupListeners.handle(listener -> listener.onMemberLeave(group, identity, previousMemberCount));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Intent getGroupEditIntent(@NonNull GroupModel groupModel, @NonNull Activity activity) {
        return new Intent(activity, GroupDetailActivity.class);
    }

    @Override
    public GroupModel getById(int groupId) {
        synchronized (this.groupModelCache) {
            GroupModel existingGroupModel = groupModelCache.get(groupId);
            if (existingGroupModel != null) {
                return existingGroupModel;
            }
            return this.cache(this.databaseServiceNew.getGroupModelFactory().getById(groupId));
        }
    }

    @Override
    public GroupCreateMessageResult processGroupCreateMessage(final GroupCreateMessage groupCreateMessage) {
        final GroupCreateMessageResult result = new GroupCreateMessageResult();
        if (!ListenerUtil.mutListener.listen(38263)) {
            result.success = false;
        }
        boolean isNewGroup;
        final int previousMemberCount;
        // check if i am in group
        boolean iAmAGroupMember = Functional.select(groupCreateMessage.getMembers(), new IPredicateNonNull<String>() {

            @Override
            public boolean apply(@NonNull String identity) {
                return TestUtil.compare(identity, userService.getIdentity());
            }
        }, null) != null;
        try {
            if (!ListenerUtil.mutListener.listen(38265)) {
                result.groupModel = this.getByAbstractGroupMessage(groupCreateMessage);
            }
            previousMemberCount = result.groupModel != null ? countMembers(result.groupModel) : 0;
            isNewGroup = result.groupModel == null;
        } catch (SQLException e) {
            if (!ListenerUtil.mutListener.listen(38264)) {
                logger.error("Exception", e);
            }
            return null;
        }
        @GroupState
        int groupState = getGroupState(result.groupModel);
        if (!ListenerUtil.mutListener.listen(38271)) {
            if ((ListenerUtil.mutListener.listen(38267) ? ((ListenerUtil.mutListener.listen(38266) ? (isNewGroup || this.blackListService != null) : (isNewGroup && this.blackListService != null)) || this.blackListService.has(groupCreateMessage.getFromIdentity())) : ((ListenerUtil.mutListener.listen(38266) ? (isNewGroup || this.blackListService != null) : (isNewGroup && this.blackListService != null)) && this.blackListService.has(groupCreateMessage.getFromIdentity())))) {
                if (!ListenerUtil.mutListener.listen(38268)) {
                    logger.info("GroupCreateMessage {}: Received group create from blocked ID. Sending leave.", groupCreateMessage.getMessageId());
                }
                if (!ListenerUtil.mutListener.listen(38269)) {
                    sendLeave(groupCreateMessage);
                }
                if (!ListenerUtil.mutListener.listen(38270)) {
                    result.success = true;
                }
                return result;
            }
        }
        if (!ListenerUtil.mutListener.listen(38282)) {
            if (!iAmAGroupMember) {
                if (!ListenerUtil.mutListener.listen(38280)) {
                    if (isNewGroup) {
                        if (!ListenerUtil.mutListener.listen(38278)) {
                            // ignore this groupCreate message
                            result.success = true;
                        }
                        if (!ListenerUtil.mutListener.listen(38279)) {
                            result.groupModel = null;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(38272)) {
                            // remove all members
                            this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupId(result.groupModel.getId());
                        }
                        final GroupModel groupModel = result.groupModel;
                        if (!ListenerUtil.mutListener.listen(38273)) {
                            // reset result
                            result.success = true;
                        }
                        if (!ListenerUtil.mutListener.listen(38274)) {
                            result.groupModel = null;
                        }
                        if (!ListenerUtil.mutListener.listen(38275)) {
                            // reset cache
                            this.resetIdentityCache(groupModel.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(38277)) {
                            // fire kicked
                            ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                                @Override
                                public void handle(GroupListener listener) {
                                    if (!ListenerUtil.mutListener.listen(38276)) {
                                        listener.onMemberKicked(groupModel, userService.getIdentity(), previousMemberCount);
                                    }
                                }
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38281)) {
                    ListenerManager.groupListeners.handle(listener -> listener.onGroupStateChanged(result.groupModel, groupState, getGroupState(result.groupModel)));
                }
                return result;
            }
        }
        if (result.groupModel == null) {
            if (!ListenerUtil.mutListener.listen(38285)) {
                result.groupModel = new GroupModel();
            }
            if (!ListenerUtil.mutListener.listen(38286)) {
                result.groupModel.setApiGroupId(groupCreateMessage.getGroupId().toString()).setCreatorIdentity(groupCreateMessage.getGroupCreator()).setCreatedAt(new Date());
            }
            if (!ListenerUtil.mutListener.listen(38287)) {
                this.databaseServiceNew.getGroupModelFactory().create(result.groupModel);
            }
            if (!ListenerUtil.mutListener.listen(38288)) {
                this.cache(result.groupModel);
            }
        } else if (result.groupModel.isDeleted()) {
            if (!ListenerUtil.mutListener.listen(38283)) {
                result.groupModel.setDeleted(false);
            }
            if (!ListenerUtil.mutListener.listen(38284)) {
                this.databaseServiceNew.getGroupModelFactory().update(result.groupModel);
            }
            isNewGroup = true;
        }
        List<GroupMemberModel> localSavedGroupMembers = null;
        if (!ListenerUtil.mutListener.listen(38296)) {
            if (!isNewGroup) {
                if (!ListenerUtil.mutListener.listen(38290)) {
                    // all saved members on database, excluded the group creator
                    localSavedGroupMembers = Functional.filter(this.getGroupMembers(result.groupModel), new IPredicateNonNull<GroupMemberModel>() {

                        @Override
                        public boolean apply(@NonNull GroupMemberModel type) {
                            return (ListenerUtil.mutListener.listen(38289) ? (type != null || !TestUtil.compare(type.getIdentity(), groupCreateMessage.getGroupCreator())) : (type != null && !TestUtil.compare(type.getIdentity(), groupCreateMessage.getGroupCreator())));
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(38295)) {
                    if (localSavedGroupMembers != null) {
                        if (!ListenerUtil.mutListener.listen(38294)) {
                            {
                                long _loopCounter409 = 0;
                                for (String identity : groupCreateMessage.getMembers()) {
                                    ListenerUtil.loopListener.listen("_loopCounter409", ++_loopCounter409);
                                    GroupMemberModel localSavedGroupMember = Functional.select(localSavedGroupMembers, new IPredicateNonNull<GroupMemberModel>() {

                                        @Override
                                        public boolean apply(@NonNull GroupMemberModel gm) {
                                            return (ListenerUtil.mutListener.listen(38291) ? (gm != null || TestUtil.compare(gm.getIdentity(), identity)) : (gm != null && TestUtil.compare(gm.getIdentity(), identity)));
                                        }
                                    });
                                    if (!ListenerUtil.mutListener.listen(38293)) {
                                        if (localSavedGroupMember != null) {
                                            if (!ListenerUtil.mutListener.listen(38292)) {
                                                // remove from list
                                                localSavedGroupMembers.remove(localSavedGroupMember);
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
        if (!ListenerUtil.mutListener.listen(38297)) {
            // add creator as member
            this.addMemberToGroup(result.groupModel, groupCreateMessage.getGroupCreator());
        }
        if (!ListenerUtil.mutListener.listen(38298)) {
            this.addMembersToGroup(result.groupModel, groupCreateMessage.getMembers());
        }
        if (!ListenerUtil.mutListener.listen(38309)) {
            // now remove all local saved members that not in the create message
            if ((ListenerUtil.mutListener.listen(38304) ? (localSavedGroupMembers != null || (ListenerUtil.mutListener.listen(38303) ? (localSavedGroupMembers.size() >= 0) : (ListenerUtil.mutListener.listen(38302) ? (localSavedGroupMembers.size() <= 0) : (ListenerUtil.mutListener.listen(38301) ? (localSavedGroupMembers.size() < 0) : (ListenerUtil.mutListener.listen(38300) ? (localSavedGroupMembers.size() != 0) : (ListenerUtil.mutListener.listen(38299) ? (localSavedGroupMembers.size() == 0) : (localSavedGroupMembers.size() > 0))))))) : (localSavedGroupMembers != null && (ListenerUtil.mutListener.listen(38303) ? (localSavedGroupMembers.size() >= 0) : (ListenerUtil.mutListener.listen(38302) ? (localSavedGroupMembers.size() <= 0) : (ListenerUtil.mutListener.listen(38301) ? (localSavedGroupMembers.size() < 0) : (ListenerUtil.mutListener.listen(38300) ? (localSavedGroupMembers.size() != 0) : (ListenerUtil.mutListener.listen(38299) ? (localSavedGroupMembers.size() == 0) : (localSavedGroupMembers.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(38305)) {
                    // remove ALL from database
                    this.databaseServiceNew.getGroupMemberModelFactory().delete(localSavedGroupMembers);
                }
                if (!ListenerUtil.mutListener.listen(38308)) {
                    {
                        long _loopCounter410 = 0;
                        for (final GroupMemberModel groupMemberModel : localSavedGroupMembers) {
                            ListenerUtil.loopListener.listen("_loopCounter410", ++_loopCounter410);
                            if (!ListenerUtil.mutListener.listen(38307)) {
                                ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                                    @Override
                                    public void handle(GroupListener listener) {
                                        if (!ListenerUtil.mutListener.listen(38306)) {
                                            listener.onMemberKicked(result.groupModel, groupMemberModel.getIdentity(), previousMemberCount);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38310)) {
            // success!
            result.success = true;
        }
        if (!ListenerUtil.mutListener.listen(38311)) {
            this.rebuildColors(result.groupModel);
        }
        if (!ListenerUtil.mutListener.listen(38314)) {
            if (isNewGroup) {
                // only fire on new group event
                final GroupModel gm = result.groupModel;
                if (!ListenerUtil.mutListener.listen(38313)) {
                    ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                        @Override
                        public void handle(GroupListener listener) {
                            if (!ListenerUtil.mutListener.listen(38312)) {
                                listener.onCreate(gm);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38315)) {
            ListenerManager.groupListeners.handle(listener -> listener.onGroupStateChanged(result.groupModel, groupState, getGroupState(result.groupModel)));
        }
        return result;
    }

    @Override
    public GroupModel createGroup(String name, String[] groupMemberIdentities, Bitmap picture) throws Exception {
        if (!ListenerUtil.mutListener.listen(38316)) {
            if (AppRestrictionUtil.isCreateGroupDisabled(ThreemaApplication.getAppContext())) {
                throw new PolicyViolationException();
            }
        }
        GroupPhotoUploadResult uploadPhotoResult = null;
        if (!ListenerUtil.mutListener.listen(38318)) {
            if (picture != null) {
                if (!ListenerUtil.mutListener.listen(38317)) {
                    uploadPhotoResult = this.uploadGroupPhoto(picture);
                }
            }
        }
        GroupModel model = this.createGroup(name, groupMemberIdentities);
        if (!ListenerUtil.mutListener.listen(38320)) {
            if (uploadPhotoResult != null) {
                if (!ListenerUtil.mutListener.listen(38319)) {
                    this.updateGroupPhoto(model, uploadPhotoResult);
                }
            }
        }
        return model;
    }

    private GroupModel createGroup(String name, final String[] groupMemberIdentities) throws ThreemaException, PolicyViolationException {
        if (!ListenerUtil.mutListener.listen(38321)) {
            if (AppRestrictionUtil.isCreateGroupDisabled(ThreemaApplication.getAppContext())) {
                throw new PolicyViolationException();
            }
        }
        final GroupModel groupModel = new GroupModel();
        String randomId = UUID.randomUUID().toString();
        GroupId id = new GroupId(Utils.hexStringToByteArray(randomId.substring((ListenerUtil.mutListener.listen(38329) ? (randomId.length() % ((ListenerUtil.mutListener.listen(38325) ? (ProtocolDefines.GROUP_ID_LEN % 2) : (ListenerUtil.mutListener.listen(38324) ? (ProtocolDefines.GROUP_ID_LEN / 2) : (ListenerUtil.mutListener.listen(38323) ? (ProtocolDefines.GROUP_ID_LEN - 2) : (ListenerUtil.mutListener.listen(38322) ? (ProtocolDefines.GROUP_ID_LEN + 2) : (ProtocolDefines.GROUP_ID_LEN * 2))))))) : (ListenerUtil.mutListener.listen(38328) ? (randomId.length() / ((ListenerUtil.mutListener.listen(38325) ? (ProtocolDefines.GROUP_ID_LEN % 2) : (ListenerUtil.mutListener.listen(38324) ? (ProtocolDefines.GROUP_ID_LEN / 2) : (ListenerUtil.mutListener.listen(38323) ? (ProtocolDefines.GROUP_ID_LEN - 2) : (ListenerUtil.mutListener.listen(38322) ? (ProtocolDefines.GROUP_ID_LEN + 2) : (ProtocolDefines.GROUP_ID_LEN * 2))))))) : (ListenerUtil.mutListener.listen(38327) ? (randomId.length() * ((ListenerUtil.mutListener.listen(38325) ? (ProtocolDefines.GROUP_ID_LEN % 2) : (ListenerUtil.mutListener.listen(38324) ? (ProtocolDefines.GROUP_ID_LEN / 2) : (ListenerUtil.mutListener.listen(38323) ? (ProtocolDefines.GROUP_ID_LEN - 2) : (ListenerUtil.mutListener.listen(38322) ? (ProtocolDefines.GROUP_ID_LEN + 2) : (ProtocolDefines.GROUP_ID_LEN * 2))))))) : (ListenerUtil.mutListener.listen(38326) ? (randomId.length() + ((ListenerUtil.mutListener.listen(38325) ? (ProtocolDefines.GROUP_ID_LEN % 2) : (ListenerUtil.mutListener.listen(38324) ? (ProtocolDefines.GROUP_ID_LEN / 2) : (ListenerUtil.mutListener.listen(38323) ? (ProtocolDefines.GROUP_ID_LEN - 2) : (ListenerUtil.mutListener.listen(38322) ? (ProtocolDefines.GROUP_ID_LEN + 2) : (ProtocolDefines.GROUP_ID_LEN * 2))))))) : (randomId.length() - ((ListenerUtil.mutListener.listen(38325) ? (ProtocolDefines.GROUP_ID_LEN % 2) : (ListenerUtil.mutListener.listen(38324) ? (ProtocolDefines.GROUP_ID_LEN / 2) : (ListenerUtil.mutListener.listen(38323) ? (ProtocolDefines.GROUP_ID_LEN - 2) : (ListenerUtil.mutListener.listen(38322) ? (ProtocolDefines.GROUP_ID_LEN + 2) : (ProtocolDefines.GROUP_ID_LEN * 2))))))))))))));
        if (!ListenerUtil.mutListener.listen(38330)) {
            groupModel.setApiGroupId(Utils.byteArrayToHexString(id.getGroupId())).setCreatorIdentity(this.userService.getIdentity()).setName(name).setCreatedAt(new Date()).setSynchronizedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(38331)) {
            this.databaseServiceNew.getGroupModelFactory().create(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(38332)) {
            this.cache(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(38334)) {
            {
                long _loopCounter411 = 0;
                for (String identity : groupMemberIdentities) {
                    ListenerUtil.loopListener.listen("_loopCounter411", ++_loopCounter411);
                    if (!ListenerUtil.mutListener.listen(38333)) {
                        this.addMemberToGroup(groupModel, identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38335)) {
            // add creator to group
            this.addMemberToGroup(groupModel, groupModel.getCreatorIdentity());
        }
        if (!ListenerUtil.mutListener.listen(38337)) {
            ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                @Override
                public void handle(GroupListener listener) {
                    if (!ListenerUtil.mutListener.listen(38336)) {
                        listener.onCreate(groupModel);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(38338)) {
            ListenerManager.groupListeners.handle(listener -> listener.onGroupStateChanged(groupModel, UNDEFINED, getGroupState(groupModel)));
        }
        if (!ListenerUtil.mutListener.listen(38341)) {
            // send event to server
            this.groupApiService.sendMessage(groupModel, this.getGroupIdentities(groupModel), new GroupApiService.CreateApiMessage() {

                @Override
                public AbstractGroupMessage create(MessageId messageId) {
                    GroupCreateMessage groupCreateMessage = new GroupCreateMessage();
                    if (!ListenerUtil.mutListener.listen(38339)) {
                        groupCreateMessage.setMessageId(messageId);
                    }
                    if (!ListenerUtil.mutListener.listen(38340)) {
                        groupCreateMessage.setMembers(groupMemberIdentities);
                    }
                    return groupCreateMessage;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(38349)) {
            if ((ListenerUtil.mutListener.listen(38347) ? (groupModel.getName() != null || (ListenerUtil.mutListener.listen(38346) ? (groupModel.getName().length() >= 0) : (ListenerUtil.mutListener.listen(38345) ? (groupModel.getName().length() <= 0) : (ListenerUtil.mutListener.listen(38344) ? (groupModel.getName().length() < 0) : (ListenerUtil.mutListener.listen(38343) ? (groupModel.getName().length() != 0) : (ListenerUtil.mutListener.listen(38342) ? (groupModel.getName().length() == 0) : (groupModel.getName().length() > 0))))))) : (groupModel.getName() != null && (ListenerUtil.mutListener.listen(38346) ? (groupModel.getName().length() >= 0) : (ListenerUtil.mutListener.listen(38345) ? (groupModel.getName().length() <= 0) : (ListenerUtil.mutListener.listen(38344) ? (groupModel.getName().length() < 0) : (ListenerUtil.mutListener.listen(38343) ? (groupModel.getName().length() != 0) : (ListenerUtil.mutListener.listen(38342) ? (groupModel.getName().length() == 0) : (groupModel.getName().length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(38348)) {
                    this.renameGroup(groupModel, groupModel.getName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38350)) {
            this.rebuildColors(groupModel);
        }
        return groupModel;
    }

    @Override
    public Boolean addMemberToGroup(final GroupModel groupModel, final String identity) {
        GroupMemberModel m = this.getGroupMember(groupModel, identity);
        boolean isNewMember = m == null;
        final int previousMemberCount = countMembers(groupModel);
        if (!ListenerUtil.mutListener.listen(38356)) {
            if (m == null) {
                if (!ListenerUtil.mutListener.listen(38354)) {
                    // create a identity contact if not exist
                    if ((ListenerUtil.mutListener.listen(38351) ? (!this.userService.getIdentity().equals(identity) || this.contactService.getByIdentity(identity) == null) : (!this.userService.getIdentity().equals(identity) && this.contactService.getByIdentity(identity) == null))) {
                        if (!ListenerUtil.mutListener.listen(38353)) {
                            if (!this.preferenceService.isBlockUnknown()) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(38352)) {
                                        this.contactService.createContactByIdentity(identity, true, true);
                                    }
                                } catch (InvalidEntryException | PolicyViolationException e) {
                                    return null;
                                } catch (EntryAlreadyExistsException e) {
                                }
                            } else {
                                return false;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38355)) {
                    m = new GroupMemberModel();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38357)) {
            m.setActive(true).setGroupId(groupModel.getId()).setIdentity(identity);
        }
        if (!ListenerUtil.mutListener.listen(38360)) {
            if (isNewMember) {
                if (!ListenerUtil.mutListener.listen(38359)) {
                    this.databaseServiceNew.getGroupMemberModelFactory().create(m);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38358)) {
                    this.databaseServiceNew.getGroupMemberModelFactory().update(m);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38361)) {
            this.resetIdentityCache(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(38364)) {
            // fire new member event after the data are saved
            if (isNewMember) {
                if (!ListenerUtil.mutListener.listen(38363)) {
                    ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                        @Override
                        public void handle(GroupListener listener) {
                            if (!ListenerUtil.mutListener.listen(38362)) {
                                listener.onNewMember(groupModel, identity, previousMemberCount);
                            }
                        }
                    });
                }
            }
        }
        return isNewMember;
    }

    /**
     *  Add one or more members to a group. Will fetch identities from server if not known
     *  If "block unknown" is enabled, new contacts will not be created for group members not already in contacts
     *  @param groupModel Group model to add members to
     *  @param identities Array of identities to add
     *  @return true if members have been added, false if no members have been specified, null if new identities could not be fetched
     */
    @Override
    public Boolean addMembersToGroup(final GroupModel groupModel, @Nullable final String[] identities) {
        if (!ListenerUtil.mutListener.listen(38409)) {
            if ((ListenerUtil.mutListener.listen(38370) ? (identities != null || (ListenerUtil.mutListener.listen(38369) ? (identities.length >= 0) : (ListenerUtil.mutListener.listen(38368) ? (identities.length <= 0) : (ListenerUtil.mutListener.listen(38367) ? (identities.length < 0) : (ListenerUtil.mutListener.listen(38366) ? (identities.length != 0) : (ListenerUtil.mutListener.listen(38365) ? (identities.length == 0) : (identities.length > 0))))))) : (identities != null && (ListenerUtil.mutListener.listen(38369) ? (identities.length >= 0) : (ListenerUtil.mutListener.listen(38368) ? (identities.length <= 0) : (ListenerUtil.mutListener.listen(38367) ? (identities.length < 0) : (ListenerUtil.mutListener.listen(38366) ? (identities.length != 0) : (ListenerUtil.mutListener.listen(38365) ? (identities.length == 0) : (identities.length > 0))))))))) {
                @GroupState
                int groupState = getGroupState(groupModel);
                ArrayList<String> newContacts = new ArrayList<>();
                ArrayList<String> newMembers = new ArrayList<>();
                int previousMemberCount = countMembers(groupModel);
                if (!ListenerUtil.mutListener.listen(38395)) {
                    // check for new contacts, if necessary, create them
                    if (!this.preferenceService.isBlockUnknown()) {
                        if (!ListenerUtil.mutListener.listen(38374)) {
                            {
                                long _loopCounter412 = 0;
                                for (String identity : identities) {
                                    ListenerUtil.loopListener.listen("_loopCounter412", ++_loopCounter412);
                                    if (!ListenerUtil.mutListener.listen(38373)) {
                                        if ((ListenerUtil.mutListener.listen(38371) ? (!this.userService.getIdentity().equals(identity) || this.contactService.getByIdentity(identity) == null) : (!this.userService.getIdentity().equals(identity) && this.contactService.getByIdentity(identity) == null))) {
                                            if (!ListenerUtil.mutListener.listen(38372)) {
                                                newContacts.add(identity);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(38394)) {
                            if ((ListenerUtil.mutListener.listen(38379) ? (newContacts.size() >= 0) : (ListenerUtil.mutListener.listen(38378) ? (newContacts.size() <= 0) : (ListenerUtil.mutListener.listen(38377) ? (newContacts.size() < 0) : (ListenerUtil.mutListener.listen(38376) ? (newContacts.size() != 0) : (ListenerUtil.mutListener.listen(38375) ? (newContacts.size() == 0) : (newContacts.size() > 0))))))) {
                                APIConnector apiConnector = ThreemaApplication.getServiceManager().getAPIConnector();
                                ArrayList<APIConnector.FetchIdentityResult> results;
                                try {
                                    results = apiConnector.fetchIdentities(newContacts);
                                    if (!ListenerUtil.mutListener.listen(38393)) {
                                        {
                                            long _loopCounter413 = 0;
                                            for (String identity : newContacts) {
                                                ListenerUtil.loopListener.listen("_loopCounter413", ++_loopCounter413);
                                                APIConnector.FetchIdentityResult result = apiConnector.getFetchResultByIdentity(results, identity);
                                                ContactModel contactModel;
                                                if (result != null) {
                                                    contactModel = new ContactModel(result.identity, result.publicKey);
                                                    if (!ListenerUtil.mutListener.listen(38383)) {
                                                        contactModel.setVerificationLevel(contactService.getInitialVerificationLevel(contactModel));
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(38384)) {
                                                        contactModel.setFeatureMask(result.featureMask);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(38385)) {
                                                        contactModel.setType(result.type);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(38389)) {
                                                        switch(result.state) {
                                                            case IdentityState.ACTIVE:
                                                                if (!ListenerUtil.mutListener.listen(38386)) {
                                                                    contactModel.setState(ContactModel.State.ACTIVE);
                                                                }
                                                                break;
                                                            case IdentityState.INACTIVE:
                                                                if (!ListenerUtil.mutListener.listen(38387)) {
                                                                    contactModel.setState(ContactModel.State.INACTIVE);
                                                                }
                                                                break;
                                                            case IdentityState.INVALID:
                                                                if (!ListenerUtil.mutListener.listen(38388)) {
                                                                    contactModel.setState(ContactModel.State.INVALID);
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(38390)) {
                                                        contactModel.setDateCreated(new Date());
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(38391)) {
                                                        contactModel.setIsHidden(true);
                                                    }
                                                } else {
                                                    // this is an invalid contact, as it was not returned by the call to fetchIdentities(newContacts) - fix it
                                                    contactModel = this.contactService.getByIdentity(identity);
                                                    if (!ListenerUtil.mutListener.listen(38382)) {
                                                        if (contactModel != null) {
                                                            if (!ListenerUtil.mutListener.listen(38381)) {
                                                                contactModel.setState(ContactModel.State.INVALID);
                                                            }
                                                        } else {
                                                            continue;
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(38392)) {
                                                    contactService.save(contactModel);
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(38380)) {
                                        // no connection
                                        logger.error("Exception", e);
                                    }
                                    return null;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38403)) {
                    {
                        long _loopCounter414 = 0;
                        // check for new members
                        for (String identity : identities) {
                            ListenerUtil.loopListener.listen("_loopCounter414", ++_loopCounter414);
                            GroupMemberModel m = this.getGroupMember(groupModel, identity);
                            if (!ListenerUtil.mutListener.listen(38402)) {
                                if (m == null) {
                                    if (!ListenerUtil.mutListener.listen(38398)) {
                                        // this is a new member
                                        m = new GroupMemberModel();
                                    }
                                    if (!ListenerUtil.mutListener.listen(38399)) {
                                        m.setActive(true).setGroupId(groupModel.getId()).setIdentity(identity);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38400)) {
                                        this.databaseServiceNew.getGroupMemberModelFactory().create(m);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38401)) {
                                        newMembers.add(identity);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(38396)) {
                                        // this is an existing member
                                        m.setActive(true).setGroupId(groupModel.getId()).setIdentity(identity);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38397)) {
                                        this.databaseServiceNew.getGroupMemberModelFactory().update(m);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38404)) {
                    this.resetIdentityCache(groupModel.getId());
                }
                if (!ListenerUtil.mutListener.listen(38407)) {
                    // fire new member event after the data are saved
                    ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                        @Override
                        public void handle(GroupListener listener) {
                            if (!ListenerUtil.mutListener.listen(38406)) {
                                {
                                    long _loopCounter415 = 0;
                                    for (String identity : newMembers) {
                                        ListenerUtil.loopListener.listen("_loopCounter415", ++_loopCounter415);
                                        if (!ListenerUtil.mutListener.listen(38405)) {
                                            listener.onNewMember(groupModel, identity, previousMemberCount);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(38408)) {
                    ListenerManager.groupListeners.handle(listener -> listener.onGroupStateChanged(groupModel, groupState, getGroupState(groupModel)));
                }
                return true;
            }
        }
        return false;
    }

    private int getGroupState(@Nullable GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(38410)) {
            if (groupModel != null) {
                return isNotesGroup(groupModel) ? NOTES : PEOPLE;
            }
        }
        return UNDEFINED;
    }

    @Override
    public GroupModel updateGroup(final GroupModel groupModel, String name, final String[] groupMemberIdentities, Bitmap photo, boolean removePhoto) throws Exception {
        @GroupState
        int groupState = getGroupState(groupModel);
        // existing members
        String[] existingMembers = this.getGroupIdentities(groupModel);
        // list with all (also kicked and added) members
        List<String> allInvolvedMembers = new LinkedList<>(Arrays.asList(existingMembers));
        // list of all kicked identities
        List<String> kickedGroupMemberIdentities = new ArrayList<>();
        // check new members
        List<String> newMembers = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(38415)) {
            if (groupMemberIdentities != null) {
                if (!ListenerUtil.mutListener.listen(38414)) {
                    {
                        long _loopCounter416 = 0;
                        for (String identity : groupMemberIdentities) {
                            ListenerUtil.loopListener.listen("_loopCounter416", ++_loopCounter416);
                            if (!ListenerUtil.mutListener.listen(38413)) {
                                if (this.getGroupMember(groupModel, identity) == null) {
                                    if (!ListenerUtil.mutListener.listen(38411)) {
                                        newMembers.add(identity);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38412)) {
                                        allInvolvedMembers.add(identity);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        GroupPhotoUploadResult photoUploadResult = null;
        boolean isANewGroupPhoto = photo != null;
        if (!ListenerUtil.mutListener.listen(38427)) {
            if (removePhoto) {
                if (!ListenerUtil.mutListener.listen(38426)) {
                    this.fileService.removeGroupAvatar(groupModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38423)) {
                    if ((ListenerUtil.mutListener.listen(38421) ? (photo == null || (ListenerUtil.mutListener.listen(38420) ? (newMembers.size() >= 0) : (ListenerUtil.mutListener.listen(38419) ? (newMembers.size() <= 0) : (ListenerUtil.mutListener.listen(38418) ? (newMembers.size() < 0) : (ListenerUtil.mutListener.listen(38417) ? (newMembers.size() != 0) : (ListenerUtil.mutListener.listen(38416) ? (newMembers.size() == 0) : (newMembers.size() > 0))))))) : (photo == null && (ListenerUtil.mutListener.listen(38420) ? (newMembers.size() >= 0) : (ListenerUtil.mutListener.listen(38419) ? (newMembers.size() <= 0) : (ListenerUtil.mutListener.listen(38418) ? (newMembers.size() < 0) : (ListenerUtil.mutListener.listen(38417) ? (newMembers.size() != 0) : (ListenerUtil.mutListener.listen(38416) ? (newMembers.size() == 0) : (newMembers.size() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(38422)) {
                            // load existing picture
                            photo = this.fileService.getGroupAvatar(groupModel);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38425)) {
                    if (photo != null) {
                        if (!ListenerUtil.mutListener.listen(38424)) {
                            // upload the picture if possible
                            photoUploadResult = this.uploadGroupPhoto(photo);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38436)) {
            // add new members to group
            if ((ListenerUtil.mutListener.listen(38432) ? (newMembers.size() >= 0) : (ListenerUtil.mutListener.listen(38431) ? (newMembers.size() <= 0) : (ListenerUtil.mutListener.listen(38430) ? (newMembers.size() < 0) : (ListenerUtil.mutListener.listen(38429) ? (newMembers.size() != 0) : (ListenerUtil.mutListener.listen(38428) ? (newMembers.size() == 0) : (newMembers.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(38435)) {
                    {
                        long _loopCounter417 = 0;
                        for (String newMember : newMembers) {
                            ListenerUtil.loopListener.listen("_loopCounter417", ++_loopCounter417);
                            if (!ListenerUtil.mutListener.listen(38433)) {
                                logger.debug("add member " + newMember + " to group");
                            }
                            if (!ListenerUtil.mutListener.listen(38434)) {
                                this.addMemberToGroup(groupModel, newMember);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38437)) {
            // add creator to group
            this.addMemberToGroup(groupModel, groupModel.getCreatorIdentity());
        }
        if (!ListenerUtil.mutListener.listen(38447)) {
            {
                long _loopCounter418 = 0;
                // now kick the members
                for (final String savedIdentity : existingMembers) {
                    ListenerUtil.loopListener.listen("_loopCounter418", ++_loopCounter418);
                    if (!ListenerUtil.mutListener.listen(38446)) {
                        // if the identity NOT in the new groupMemberIdentities, kick the member
                        if (null == Functional.select(groupMemberIdentities, new IPredicateNonNull<String>() {

                            @Override
                            public boolean apply(@NonNull String identity) {
                                return TestUtil.compare(identity, savedIdentity);
                            }
                        }, null)) {
                            if (!ListenerUtil.mutListener.listen(38438)) {
                                logger.debug("remove member " + savedIdentity + " from group");
                            }
                            if (!ListenerUtil.mutListener.listen(38445)) {
                                // get model
                                if ((ListenerUtil.mutListener.listen(38443) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(groupModel.getId(), savedIdentity) >= 0) : (ListenerUtil.mutListener.listen(38442) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(groupModel.getId(), savedIdentity) <= 0) : (ListenerUtil.mutListener.listen(38441) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(groupModel.getId(), savedIdentity) < 0) : (ListenerUtil.mutListener.listen(38440) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(groupModel.getId(), savedIdentity) != 0) : (ListenerUtil.mutListener.listen(38439) ? (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(groupModel.getId(), savedIdentity) == 0) : (this.databaseServiceNew.getGroupMemberModelFactory().deleteByGroupIdAndIdentity(groupModel.getId(), savedIdentity) > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(38444)) {
                                        kickedGroupMemberIdentities.add(savedIdentity);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38450)) {
            // send event to ALL members (including kicked and added) of group
            this.groupApiService.sendMessage(groupModel, allInvolvedMembers.toArray(new String[allInvolvedMembers.size()]), new GroupApiService.CreateApiMessage() {

                @Override
                public AbstractGroupMessage create(MessageId messageId) {
                    GroupCreateMessage groupCreateMessage = new GroupCreateMessage();
                    if (!ListenerUtil.mutListener.listen(38448)) {
                        groupCreateMessage.setMessageId(messageId);
                    }
                    if (!ListenerUtil.mutListener.listen(38449)) {
                        groupCreateMessage.setMembers(groupMemberIdentities);
                    }
                    return groupCreateMessage;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(38459)) {
            if (removePhoto) {
                if (!ListenerUtil.mutListener.listen(38456)) {
                    // send event to ALL members (including kicked and added) of group
                    this.groupApiService.sendMessage(groupModel, allInvolvedMembers.toArray(new String[allInvolvedMembers.size()]), new GroupApiService.CreateApiMessage() {

                        @Override
                        public AbstractGroupMessage create(MessageId messageId) {
                            GroupDeletePhotoMessage groupDeletePhotoMessage = new GroupDeletePhotoMessage();
                            if (!ListenerUtil.mutListener.listen(38455)) {
                                groupDeletePhotoMessage.setMessageId(messageId);
                            }
                            return groupDeletePhotoMessage;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(38457)) {
                    this.avatarCacheService.reset(groupModel);
                }
                if (!ListenerUtil.mutListener.listen(38458)) {
                    ListenerManager.groupListeners.handle(listener -> listener.onUpdatePhoto(groupModel));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38454)) {
                    if (photoUploadResult != null) {
                        if (!ListenerUtil.mutListener.listen(38453)) {
                            if (isANewGroupPhoto) {
                                if (!ListenerUtil.mutListener.listen(38452)) {
                                    // its a new picture, save it and send it to every member
                                    this.updateGroupPhoto(groupModel, photoUploadResult);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(38451)) {
                                    // only send the picture to the new members
                                    this.sendGroupPhotoToMembers(groupModel, newMembers.toArray(new String[newMembers.size()]), photoUploadResult);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38461)) {
            if (name != null) {
                if (!ListenerUtil.mutListener.listen(38460)) {
                    this.renameGroup(groupModel, name);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38470)) {
            if ((ListenerUtil.mutListener.listen(38466) ? (kickedGroupMemberIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(38465) ? (kickedGroupMemberIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(38464) ? (kickedGroupMemberIdentities.size() < 0) : (ListenerUtil.mutListener.listen(38463) ? (kickedGroupMemberIdentities.size() != 0) : (ListenerUtil.mutListener.listen(38462) ? (kickedGroupMemberIdentities.size() == 0) : (kickedGroupMemberIdentities.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(38467)) {
                    // remove from cache!
                    this.resetIdentityCache(groupModel.getId());
                }
                if (!ListenerUtil.mutListener.listen(38469)) {
                    {
                        long _loopCounter419 = 0;
                        for (final String kickedGroupMemberIdentity : kickedGroupMemberIdentities) {
                            ListenerUtil.loopListener.listen("_loopCounter419", ++_loopCounter419);
                            if (!ListenerUtil.mutListener.listen(38468)) {
                                ListenerManager.groupListeners.handle(listener -> listener.onMemberKicked(groupModel, kickedGroupMemberIdentity, existingMembers.length));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38471)) {
            ListenerManager.groupListeners.handle(listener -> listener.onGroupStateChanged(groupModel, groupState, getGroupState(groupModel)));
        }
        return groupModel;
    }

    @Override
    public boolean renameGroup(GroupRenameMessage renameMessage) throws ThreemaException {
        final GroupModel groupModel = this.getGroup(renameMessage);
        if (!ListenerUtil.mutListener.listen(38476)) {
            if (groupModel != null) {
                if (!ListenerUtil.mutListener.listen(38475)) {
                    // only rename, if the name is different
                    if (!TestUtil.compare(groupModel.getName(), renameMessage.getGroupName())) {
                        if (!ListenerUtil.mutListener.listen(38472)) {
                            this.renameGroup(groupModel, renameMessage.getGroupName());
                        }
                        if (!ListenerUtil.mutListener.listen(38474)) {
                            ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                                @Override
                                public void handle(GroupListener listener) {
                                    if (!ListenerUtil.mutListener.listen(38473)) {
                                        listener.onRename(groupModel);
                                    }
                                }
                            });
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean renameGroup(final GroupModel group, final String newName) throws ThreemaException {
        boolean localeRenamed = !TestUtil.compare(group.getName(), newName);
        if (!ListenerUtil.mutListener.listen(38477)) {
            group.setName(newName);
        }
        if (!ListenerUtil.mutListener.listen(38478)) {
            this.save(group);
        }
        if (!ListenerUtil.mutListener.listen(38485)) {
            if (this.isGroupOwner(group)) {
                if (!ListenerUtil.mutListener.listen(38481)) {
                    // send rename event!
                    this.groupApiService.sendMessage(group, this.getGroupIdentities(group), new GroupApiService.CreateApiMessage() {

                        @Override
                        public AbstractGroupMessage create(MessageId messageId) {
                            GroupRenameMessage rename = new GroupRenameMessage();
                            if (!ListenerUtil.mutListener.listen(38479)) {
                                rename.setMessageId(messageId);
                            }
                            if (!ListenerUtil.mutListener.listen(38480)) {
                                rename.setGroupName(newName);
                            }
                            return rename;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(38484)) {
                    if (localeRenamed) {
                        if (!ListenerUtil.mutListener.listen(38483)) {
                            ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                                @Override
                                public void handle(GroupListener listener) {
                                    if (!ListenerUtil.mutListener.listen(38482)) {
                                        listener.onRename(group);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Do not make the upload
     */
    private void updateGroupPhoto(final GroupModel groupModel, GroupPhotoUploadResult result) throws Exception {
        if (!ListenerUtil.mutListener.listen(38486)) {
            // send to the new blob to the users
            this.sendGroupPhotoToMembers(groupModel, this.getGroupIdentities(groupModel), result);
        }
        if (!ListenerUtil.mutListener.listen(38487)) {
            // save the image
            this.fileService.writeGroupAvatar(groupModel, result.bitmapArray);
        }
        if (!ListenerUtil.mutListener.listen(38488)) {
            // reset the avatar cache entry
            this.avatarCacheService.reset(groupModel);
        }
        if (!ListenerUtil.mutListener.listen(38490)) {
            ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                @Override
                public void handle(GroupListener listener) {
                    if (!ListenerUtil.mutListener.listen(38489)) {
                        listener.onUpdatePhoto(groupModel);
                    }
                }
            });
        }
    }

    private GroupPhotoUploadResult uploadGroupPhoto(Bitmap picture) throws IOException, ThreemaException {
        GroupPhotoUploadResult result = new GroupPhotoUploadResult();
        SecureRandom rnd = new SecureRandom();
        if (!ListenerUtil.mutListener.listen(38491)) {
            result.encryptionKey = new byte[NaCl.SYMMKEYBYTES];
        }
        if (!ListenerUtil.mutListener.listen(38492)) {
            rnd.nextBytes(result.encryptionKey);
        }
        if (!ListenerUtil.mutListener.listen(38493)) {
            result.bitmapArray = BitmapUtil.bitmapToJpegByteArray(picture);
        }
        byte[] thumbnailBoxed = NaCl.symmetricEncryptData(result.bitmapArray, result.encryptionKey, ProtocolDefines.GROUP_PHOTO_NONCE);
        BlobUploader blobUploaderThumbnail = this.apiService.createUploader(thumbnailBoxed);
        if (!ListenerUtil.mutListener.listen(38494)) {
            result.blobId = blobUploaderThumbnail.upload();
        }
        if (!ListenerUtil.mutListener.listen(38495)) {
            result.size = thumbnailBoxed.length;
        }
        return result;
    }

    private void sendGroupPhotoToMembers(GroupModel groupModel, String[] identities, final GroupPhotoUploadResult uploadResult) throws ThreemaException, IOException {
        if (!ListenerUtil.mutListener.listen(38500)) {
            this.groupApiService.sendMessage(groupModel, identities, new GroupApiService.CreateApiMessage() {

                @Override
                public AbstractGroupMessage create(MessageId messageId) {
                    GroupSetPhotoMessage msg = new GroupSetPhotoMessage();
                    if (!ListenerUtil.mutListener.listen(38496)) {
                        msg.setMessageId(messageId);
                    }
                    if (!ListenerUtil.mutListener.listen(38497)) {
                        msg.setBlobId(uploadResult.blobId);
                    }
                    if (!ListenerUtil.mutListener.listen(38498)) {
                        msg.setEncryptionKey(uploadResult.encryptionKey);
                    }
                    if (!ListenerUtil.mutListener.listen(38499)) {
                        msg.setSize(uploadResult.size);
                    }
                    return msg;
                }
            });
        }
    }

    @Override
    public boolean updateGroupPhoto(GroupSetPhotoMessage msg) throws Exception {
        final GroupModel groupModel = this.getGroup(msg);
        if (!ListenerUtil.mutListener.listen(38505)) {
            if (groupModel != null) {
                BlobLoader blobLoader = this.apiService.createLoader(msg.getBlobId());
                byte[] blob = blobLoader.load(false);
                if (!ListenerUtil.mutListener.listen(38501)) {
                    NaCl.symmetricDecryptDataInplace(blob, msg.getEncryptionKey(), ProtocolDefines.GROUP_PHOTO_NONCE);
                }
                if (!ListenerUtil.mutListener.listen(38502)) {
                    this.fileService.writeGroupAvatar(groupModel, blob);
                }
                if (!ListenerUtil.mutListener.listen(38503)) {
                    // reset the avatar cache entry
                    this.avatarCacheService.reset(groupModel);
                }
                if (!ListenerUtil.mutListener.listen(38504)) {
                    ListenerManager.groupListeners.handle(listener -> listener.onUpdatePhoto(groupModel));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteGroupPhoto(GroupDeletePhotoMessage msg) {
        final GroupModel groupModel = this.getGroup(msg);
        if (!ListenerUtil.mutListener.listen(38509)) {
            if (groupModel != null) {
                if (!ListenerUtil.mutListener.listen(38506)) {
                    this.fileService.removeGroupAvatar(groupModel);
                }
                if (!ListenerUtil.mutListener.listen(38507)) {
                    // reset the avatar cache entry
                    this.avatarCacheService.reset(groupModel);
                }
                if (!ListenerUtil.mutListener.listen(38508)) {
                    ListenerManager.groupListeners.handle(listener -> listener.onUpdatePhoto(groupModel));
                }
                return true;
            }
        }
        return false;
    }

    /**
     *  remove the cache entry of the identities
     */
    private void resetIdentityCache(int groupModelId) {
        synchronized (this.groupIdentityCache) {
            if (!ListenerUtil.mutListener.listen(38510)) {
                this.groupIdentityCache.remove(groupModelId);
            }
        }
        synchronized (this.groupMemberColorCache) {
            if (!ListenerUtil.mutListener.listen(38511)) {
                this.groupMemberColorCache.remove(groupModelId);
            }
        }
    }

    @NonNull
    @Override
    public String[] getGroupIdentities(GroupModel groupModel) {
        synchronized (this.groupIdentityCache) {
            String[] existingIdentities = this.groupIdentityCache.get(groupModel.getId());
            if (existingIdentities != null) {
                return existingIdentities;
            }
            List<GroupMemberModel> result = this.getGroupMembers(groupModel);
            String[] res = new String[result.size()];
            int pos = 0;
            if (!ListenerUtil.mutListener.listen(38513)) {
                {
                    long _loopCounter420 = 0;
                    for (GroupMemberModel m : result) {
                        ListenerUtil.loopListener.listen("_loopCounter420", ++_loopCounter420);
                        if (!ListenerUtil.mutListener.listen(38512)) {
                            res[pos++] = m.getIdentity();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(38514)) {
                this.groupIdentityCache.put(groupModel.getId(), res);
            }
            return res;
        }
    }

    private boolean isGroupMember(GroupModel groupModel, String identity) {
        if (!ListenerUtil.mutListener.listen(38517)) {
            if (!TestUtil.empty(identity)) {
                if (!ListenerUtil.mutListener.listen(38516)) {
                    {
                        long _loopCounter421 = 0;
                        for (String existingIdentity : this.getGroupIdentities(groupModel)) {
                            ListenerUtil.loopListener.listen("_loopCounter421", ++_loopCounter421);
                            if (!ListenerUtil.mutListener.listen(38515)) {
                                if (TestUtil.compare(existingIdentity, identity)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isGroupMember(GroupModel groupModel) {
        return isGroupMember(groupModel, userService.getIdentity());
    }

    @Override
    public List<GroupMemberModel> getGroupMembers(GroupModel groupModel) {
        return this.databaseServiceNew.getGroupMemberModelFactory().getByGroupId(groupModel.getId());
    }

    @Override
    public GroupMemberModel getGroupMember(GroupModel groupModel, String identity) {
        return this.databaseServiceNew.getGroupMemberModelFactory().getByGroupIdAndIdentity(groupModel.getId(), identity);
    }

    @Override
    public Collection<ContactModel> getMembers(GroupModel groupModel) {
        return this.contactService.getByIdentities(this.getGroupIdentities(groupModel));
    }

    @Override
    public String getMembersString(GroupModel groupModel) {
        // should probably rather return a list of ContactModels, or maybe ThreemaIds :-)
        Collection<ContactModel> contacts = this.getMembers(groupModel);
        String[] names = new String[contacts.size()];
        int pos = 0;
        if (!ListenerUtil.mutListener.listen(38519)) {
            {
                long _loopCounter422 = 0;
                for (ContactModel c : contacts) {
                    ListenerUtil.loopListener.listen("_loopCounter422", ++_loopCounter422);
                    if (!ListenerUtil.mutListener.listen(38518)) {
                        names[pos++] = NameUtil.getDisplayNameOrNickname(c, true);
                    }
                }
            }
        }
        return TextUtils.join(", ", names);
    }

    @Override
    public GroupMessageReceiver createReceiver(GroupModel groupModel) {
        // logger.debug("MessageReceiver", "create group receiver");
        return new GroupMessageReceiver(groupModel, this, this.databaseServiceNew, this.groupApiService, this.contactService);
    }

    @Override
    @Nullable
    public Bitmap getCachedAvatar(GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(38520)) {
            if (groupModel == null) {
                return null;
            }
        }
        return this.avatarCacheService.getGroupAvatarLowFromCache(groupModel);
    }

    @Override
    public Bitmap getAvatar(GroupModel groupModel, boolean highResolution) {
        return getAvatar(groupModel, highResolution, false);
    }

    @Override
    public Bitmap getDefaultAvatar(GroupModel groupModel, boolean highResolution) {
        return getAvatar(groupModel, highResolution, true);
    }

    @Override
    public Bitmap getNeutralAvatar(boolean highResolution) {
        return avatarCacheService.getGroupAvatarNeutral(highResolution);
    }

    @Nullable
    private Bitmap getAvatar(GroupModel groupModel, boolean highResolution, boolean defaultOnly) {
        if (groupModel == null) {
            return null;
        }
        Map<String, Integer> colorMap = this.getGroupMemberColors(groupModel);
        Collection<Integer> colors = null;
        if (!ListenerUtil.mutListener.listen(38522)) {
            if (colorMap != null) {
                if (!ListenerUtil.mutListener.listen(38521)) {
                    colors = colorMap.values();
                }
            }
        }
        if (highResolution) {
            return this.avatarCacheService.getGroupAvatarHigh(groupModel, colors, defaultOnly);
        } else {
            return this.avatarCacheService.getGroupAvatarLow(groupModel, colors, defaultOnly);
        }
    }

    public boolean isGroupOwner(GroupModel groupModel) {
        return (ListenerUtil.mutListener.listen(38524) ? ((ListenerUtil.mutListener.listen(38523) ? (groupModel != null || this.userService.getIdentity() != null) : (groupModel != null && this.userService.getIdentity() != null)) || this.userService.isMe(groupModel.getCreatorIdentity())) : ((ListenerUtil.mutListener.listen(38523) ? (groupModel != null || this.userService.getIdentity() != null) : (groupModel != null && this.userService.getIdentity() != null)) && this.userService.isMe(groupModel.getCreatorIdentity())));
    }

    /**
     *  Count members in a group
     *  @param groupModel
     *  @return Number of members in this group including group creator
     */
    @Override
    public int countMembers(@NonNull GroupModel groupModel) {
        synchronized (this.groupIdentityCache) {
            String[] existingIdentities = this.groupIdentityCache.get(groupModel.getId());
            if (!ListenerUtil.mutListener.listen(38525)) {
                if (existingIdentities != null) {
                    return existingIdentities.length;
                }
            }
        }
        return (int) this.databaseServiceNew.getGroupMemberModelFactory().countMembers(groupModel.getId());
    }

    /**
     *  Whether the provided group is an implicit note group (i.e. data is kept local)
     *  @param groupModel of the group
     *  @return true if the group is a note group, false otherwise
     */
    @Override
    public boolean isNotesGroup(@NonNull GroupModel groupModel) {
        return (ListenerUtil.mutListener.listen(38526) ? (isGroupOwner(groupModel) || countMembers(groupModel) == 1) : (isGroupOwner(groupModel) && countMembers(groupModel) == 1));
    }

    @Override
    public int getOtherMemberCount(GroupModel groupModel) {
        int count = 0;
        String[] identities = this.getGroupIdentities(groupModel);
        if (!ListenerUtil.mutListener.listen(38529)) {
            {
                long _loopCounter423 = 0;
                for (String identity : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter423", ++_loopCounter423);
                    if (!ListenerUtil.mutListener.listen(38528)) {
                        if (!this.userService.isMe(identity)) {
                            if (!ListenerUtil.mutListener.listen(38527)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    @Override
    public int getPrimaryColor(GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(38543)) {
            if (groupModel != null) {
                // get members
                Map<String, Integer> colors = this.getGroupMemberColors(groupModel);
                if (!ListenerUtil.mutListener.listen(38542)) {
                    if ((ListenerUtil.mutListener.listen(38535) ? (colors != null || (ListenerUtil.mutListener.listen(38534) ? (colors.size() >= 0) : (ListenerUtil.mutListener.listen(38533) ? (colors.size() <= 0) : (ListenerUtil.mutListener.listen(38532) ? (colors.size() < 0) : (ListenerUtil.mutListener.listen(38531) ? (colors.size() != 0) : (ListenerUtil.mutListener.listen(38530) ? (colors.size() == 0) : (colors.size() > 0))))))) : (colors != null && (ListenerUtil.mutListener.listen(38534) ? (colors.size() >= 0) : (ListenerUtil.mutListener.listen(38533) ? (colors.size() <= 0) : (ListenerUtil.mutListener.listen(38532) ? (colors.size() < 0) : (ListenerUtil.mutListener.listen(38531) ? (colors.size() != 0) : (ListenerUtil.mutListener.listen(38530) ? (colors.size() == 0) : (colors.size() > 0))))))))) {
                        Collection<Integer> v = colors.values();
                        if (!ListenerUtil.mutListener.listen(38541)) {
                            if ((ListenerUtil.mutListener.listen(38540) ? (v.size() >= 0) : (ListenerUtil.mutListener.listen(38539) ? (v.size() <= 0) : (ListenerUtil.mutListener.listen(38538) ? (v.size() < 0) : (ListenerUtil.mutListener.listen(38537) ? (v.size() != 0) : (ListenerUtil.mutListener.listen(38536) ? (v.size() == 0) : (v.size() > 0))))))) {
                                return v.iterator().next();
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public boolean rebuildColors(GroupModel model) {
        // }
        return false;
    }

    @Override
    public Map<String, Integer> getGroupMemberColors(GroupModel model) {
        Map<String, Integer> colors = this.groupMemberColorCache.get(model.getId());
        if (!ListenerUtil.mutListener.listen(38552)) {
            if ((ListenerUtil.mutListener.listen(38549) ? (colors == null && (ListenerUtil.mutListener.listen(38548) ? (colors.size() >= 0) : (ListenerUtil.mutListener.listen(38547) ? (colors.size() <= 0) : (ListenerUtil.mutListener.listen(38546) ? (colors.size() > 0) : (ListenerUtil.mutListener.listen(38545) ? (colors.size() < 0) : (ListenerUtil.mutListener.listen(38544) ? (colors.size() != 0) : (colors.size() == 0))))))) : (colors == null || (ListenerUtil.mutListener.listen(38548) ? (colors.size() >= 0) : (ListenerUtil.mutListener.listen(38547) ? (colors.size() <= 0) : (ListenerUtil.mutListener.listen(38546) ? (colors.size() > 0) : (ListenerUtil.mutListener.listen(38545) ? (colors.size() < 0) : (ListenerUtil.mutListener.listen(38544) ? (colors.size() != 0) : (colors.size() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(38550)) {
                    colors = this.databaseServiceNew.getGroupMemberModelFactory().getColors(model.getId());
                }
                if (!ListenerUtil.mutListener.listen(38551)) {
                    this.groupMemberColorCache.put(model.getId(), colors);
                }
            }
        }
        return colors;
    }

    @Override
    public boolean sendEmptySync(GroupModel groupModel, String receiverIdentity) {
        try {
            if (!ListenerUtil.mutListener.listen(38556)) {
                this.groupApiService.sendMessage(groupModel, new String[] { receiverIdentity }, new GroupApiService.CreateApiMessage() {

                    @Override
                    public AbstractGroupMessage create(MessageId messageId) {
                        GroupCreateMessage groupCreateMessage = new GroupCreateMessage();
                        if (!ListenerUtil.mutListener.listen(38554)) {
                            groupCreateMessage.setMessageId(messageId);
                        }
                        if (!ListenerUtil.mutListener.listen(38555)) {
                            groupCreateMessage.setMembers(new String[] { userService.getIdentity() });
                        }
                        return groupCreateMessage;
                    }
                });
            }
            return true;
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(38553)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    @Override
    public boolean sendSync(GroupModel groupModel) {
        // send event to clients
        final String[] groupMemberIdentities = this.getGroupIdentities(groupModel);
        // send to ALL members!
        return this.sendSync(groupModel, groupMemberIdentities);
    }

    @Override
    public boolean sendSync(final GroupModel groupModel, final String[] memberIdentities) {
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(38557)) {
            this.createReceiver(groupModel);
        }
        try {
            if (!ListenerUtil.mutListener.listen(38561)) {
                this.groupApiService.sendMessage(groupModel, memberIdentities, new GroupApiService.CreateApiMessage() {

                    @Override
                    public AbstractGroupMessage create(MessageId messageId) {
                        GroupCreateMessage groupCreateMessage = new GroupCreateMessage();
                        if (!ListenerUtil.mutListener.listen(38559)) {
                            groupCreateMessage.setMessageId(messageId);
                        }
                        if (!ListenerUtil.mutListener.listen(38560)) {
                            groupCreateMessage.setMembers(getGroupIdentities(groupModel));
                        }
                        return groupCreateMessage;
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(38564)) {
                this.groupApiService.sendMessage(groupModel, memberIdentities, new GroupApiService.CreateApiMessage() {

                    @Override
                    public AbstractGroupMessage create(MessageId messageId) {
                        GroupRenameMessage groupRenameMessage = new GroupRenameMessage();
                        if (!ListenerUtil.mutListener.listen(38562)) {
                            groupRenameMessage.setMessageId(messageId);
                        }
                        if (!ListenerUtil.mutListener.listen(38563)) {
                            groupRenameMessage.setGroupName(groupModel.getName());
                        }
                        return groupRenameMessage;
                    }
                });
            }
            Bitmap picture = null;
            if (!ListenerUtil.mutListener.listen(38566)) {
                /* do not send a group picture if none has been set */
                if (fileService.hasGroupAvatarFile(groupModel)) {
                    if (!ListenerUtil.mutListener.listen(38565)) {
                        picture = this.getAvatar(groupModel, true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(38574)) {
                if (picture != null) {
                    SecureRandom rnd = new SecureRandom();
                    final byte[] encryptionKey = new byte[NaCl.SYMMKEYBYTES];
                    if (!ListenerUtil.mutListener.listen(38567)) {
                        rnd.nextBytes(encryptionKey);
                    }
                    byte[] bitmapArray = BitmapUtil.bitmapToJpegByteArray(picture);
                    byte[] thumbnailBoxed = NaCl.symmetricEncryptData(bitmapArray, encryptionKey, ProtocolDefines.GROUP_PHOTO_NONCE);
                    BlobUploader blobUploaderThumbnail = this.apiService.createUploader(thumbnailBoxed);
                    final byte[] blobId;
                    try {
                        blobId = blobUploaderThumbnail.upload();
                        final int size = thumbnailBoxed.length;
                        if (!ListenerUtil.mutListener.listen(38573)) {
                            this.groupApiService.sendMessage(groupModel, memberIdentities, new GroupApiService.CreateApiMessage() {

                                @Override
                                public AbstractGroupMessage create(MessageId messageId) {
                                    GroupSetPhotoMessage msg = new GroupSetPhotoMessage();
                                    if (!ListenerUtil.mutListener.listen(38569)) {
                                        msg.setMessageId(messageId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38570)) {
                                        msg.setBlobId(blobId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38571)) {
                                        msg.setEncryptionKey(encryptionKey);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38572)) {
                                        msg.setSize(size);
                                    }
                                    return msg;
                                }
                            });
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(38568)) {
                            logger.error("Exception", e);
                        }
                        return false;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(38575)) {
                // update sync
                groupModel.setSynchronizedAt(new Date());
            }
            if (!ListenerUtil.mutListener.listen(38576)) {
                this.save(groupModel);
            }
            if (!ListenerUtil.mutListener.listen(38577)) {
                success = true;
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(38558)) {
                logger.error("Exception", e);
            }
        }
        return success;
    }

    @Override
    public boolean processRequestSync(GroupRequestSyncMessage msg) {
        GroupModel groupModel = this.getGroup(msg);
        if (!ListenerUtil.mutListener.listen(38579)) {
            if (this.isGroupOwner(groupModel)) {
                if (!ListenerUtil.mutListener.listen(38578)) {
                    // only handle, if i am the owner!
                    if (this.isGroupMember(groupModel, msg.getFromIdentity())) {
                        return this.sendSync(groupModel, new String[] { msg.getFromIdentity() });
                    }
                }
            }
        }
        // mark as "handled"
        return true;
    }

    @Override
    public List<GroupModel> getGroupsByIdentity(String identity) {
        List<GroupModel> groupModels = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(38581)) {
            if ((ListenerUtil.mutListener.listen(38580) ? (TestUtil.empty(identity) && !TestUtil.required(this.databaseServiceNew, this.groupModelCache)) : (TestUtil.empty(identity) || !TestUtil.required(this.databaseServiceNew, this.groupModelCache)))) {
                return groupModels;
            }
        }
        if (!ListenerUtil.mutListener.listen(38582)) {
            identity = identity.toUpperCase();
        }
        List<Integer> res = this.databaseServiceNew.getGroupMemberModelFactory().getGroupIdsByIdentity(identity);
        List<Integer> groupIds = new ArrayList<>();
        synchronized (this.groupModelCache) {
            if (!ListenerUtil.mutListener.listen(38586)) {
                {
                    long _loopCounter424 = 0;
                    for (int id : res) {
                        ListenerUtil.loopListener.listen("_loopCounter424", ++_loopCounter424);
                        GroupModel existingGroupModel = this.groupModelCache.get(id);
                        if (!ListenerUtil.mutListener.listen(38585)) {
                            if (existingGroupModel == null) {
                                if (!ListenerUtil.mutListener.listen(38584)) {
                                    groupIds.add(id);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(38583)) {
                                    groupModels.add(existingGroupModel);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38594)) {
            if ((ListenerUtil.mutListener.listen(38591) ? (groupIds.size() >= 0) : (ListenerUtil.mutListener.listen(38590) ? (groupIds.size() <= 0) : (ListenerUtil.mutListener.listen(38589) ? (groupIds.size() < 0) : (ListenerUtil.mutListener.listen(38588) ? (groupIds.size() != 0) : (ListenerUtil.mutListener.listen(38587) ? (groupIds.size() == 0) : (groupIds.size() > 0))))))) {
                List<GroupModel> groups = this.databaseServiceNew.getGroupModelFactory().getInId(groupIds);
                if (!ListenerUtil.mutListener.listen(38593)) {
                    {
                        long _loopCounter425 = 0;
                        for (GroupModel gm : groups) {
                            ListenerUtil.loopListener.listen("_loopCounter425", ++_loopCounter425);
                            if (!ListenerUtil.mutListener.listen(38592)) {
                                groupModels.add(this.cache(gm));
                            }
                        }
                    }
                }
            }
        }
        return groupModels;
    }

    /**
     *  @param groupModel
     *  @param allowEmpty - allow access even if there are no other members in this group
     *  @return GroupAccessModel
     */
    @Override
    public GroupAccessModel getAccess(GroupModel groupModel, boolean allowEmpty) {
        GroupAccessModel groupAccessModel = new GroupAccessModel();
        if (!ListenerUtil.mutListener.listen(38609)) {
            if (groupModel != null) {
                final String myIdentity = this.userService.getIdentity();
                boolean iAmGroupMember = this.getGroupMember(groupModel, myIdentity) != null;
                if (!ListenerUtil.mutListener.listen(38597)) {
                    if ((ListenerUtil.mutListener.listen(38595) ? (!iAmGroupMember || !allowEmpty) : (!iAmGroupMember && !allowEmpty))) {
                        if (!ListenerUtil.mutListener.listen(38596)) {
                            // check if i am the administrator - even if i'm no longer a group member
                            iAmGroupMember = TestUtil.compare(myIdentity, groupModel.getCreatorIdentity());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(38608)) {
                    if (!iAmGroupMember) {
                        if (!ListenerUtil.mutListener.listen(38606)) {
                            groupAccessModel.setCanReceiveMessageAccess(new Access(false, R.string.you_are_not_a_member_of_this_group));
                        }
                        if (!ListenerUtil.mutListener.listen(38607)) {
                            groupAccessModel.setCanSendMessageAccess(new Access(false, R.string.you_are_not_a_member_of_this_group));
                        }
                    } else if (!allowEmpty) {
                        if (!ListenerUtil.mutListener.listen(38605)) {
                            // check if the group is empty
                            if ((ListenerUtil.mutListener.listen(38602) ? (this.getOtherMemberCount(groupModel) >= 0) : (ListenerUtil.mutListener.listen(38601) ? (this.getOtherMemberCount(groupModel) > 0) : (ListenerUtil.mutListener.listen(38600) ? (this.getOtherMemberCount(groupModel) < 0) : (ListenerUtil.mutListener.listen(38599) ? (this.getOtherMemberCount(groupModel) != 0) : (ListenerUtil.mutListener.listen(38598) ? (this.getOtherMemberCount(groupModel) == 0) : (this.getOtherMemberCount(groupModel) <= 0))))))) {
                                if (!ListenerUtil.mutListener.listen(38603)) {
                                    // a empty group
                                    groupAccessModel.setCanReceiveMessageAccess(new Access(false, R.string.can_not_send_no_group_members));
                                }
                                if (!ListenerUtil.mutListener.listen(38604)) {
                                    groupAccessModel.setCanSendMessageAccess(new Access(false, R.string.can_not_send_no_group_members));
                                }
                            }
                        }
                    }
                }
            }
        }
        return groupAccessModel;
    }

    @Override
    @Deprecated
    public int getUniqueId(GroupModel groupModel) {
        return ("g-" + String.valueOf(groupModel.getId())).hashCode();
    }

    @Override
    public String getUniqueIdString(GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(38610)) {
            if (groupModel != null) {
                return getUniqueIdString(groupModel.getId());
            }
        }
        return "";
    }

    @Override
    public String getUniqueIdString(int groupId) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(38611)) {
                messageDigest.update(("g-" + String.valueOf(groupId)).getBytes());
            }
            return Base32.encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    @Override
    public void setIsArchived(GroupModel groupModel, boolean archived) {
        if (!ListenerUtil.mutListener.listen(38618)) {
            if ((ListenerUtil.mutListener.listen(38612) ? (groupModel != null || groupModel.isArchived() != archived) : (groupModel != null && groupModel.isArchived() != archived))) {
                if (!ListenerUtil.mutListener.listen(38613)) {
                    groupModel.setArchived(archived);
                }
                if (!ListenerUtil.mutListener.listen(38614)) {
                    save(groupModel);
                }
                synchronized (this.groupModelCache) {
                    if (!ListenerUtil.mutListener.listen(38615)) {
                        this.groupModelCache.remove(groupModel.getId());
                    }
                }
                if (!ListenerUtil.mutListener.listen(38617)) {
                    ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                        @Override
                        public void handle(GroupListener listener) {
                            if (!ListenerUtil.mutListener.listen(38616)) {
                                listener.onUpdate(groupModel);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void save(GroupModel model) {
        if (!ListenerUtil.mutListener.listen(38619)) {
            this.databaseServiceNew.getGroupModelFactory().createOrUpdate(model);
        }
    }
}
