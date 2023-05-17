/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.ConversationTagModel;
import ch.threema.storage.models.TagModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConversationTagServiceImpl implements ConversationTagService {

    // Do not change this tag before db entries not changed
    public static final String FIXED_TAG_PIN = "star";

    // chats deliberately marked as unread
    public static final String FIXED_TAG_UNREAD = "unread";

    private final DatabaseServiceNew databaseService;

    private final List<TagModel> tagModels = new ArrayList<TagModel>() {

        {
            if (!ListenerUtil.mutListener.listen(37460)) {
                add(new TagModel(FIXED_TAG_PIN, 1, 2, R.string.pin));
            }
            if (!ListenerUtil.mutListener.listen(37461)) {
                add(new TagModel(FIXED_TAG_UNREAD, 0xFFFF0000, 0xFFFFFFFF, R.string.unread));
            }
        }
    };

    public ConversationTagServiceImpl(DatabaseServiceNew databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<TagModel> getTagModels() {
        return this.tagModels;
    }

    @Override
    @Nullable
    public TagModel getTagModel(@NonNull final String tagKey) {
        if (!ListenerUtil.mutListener.listen(37463)) {
            {
                long _loopCounter386 = 0;
                for (TagModel tagModel : this.tagModels) {
                    ListenerUtil.loopListener.listen("_loopCounter386", ++_loopCounter386);
                    if (!ListenerUtil.mutListener.listen(37462)) {
                        if (tagKey.equals(tagModel.getTag())) {
                            return tagModel;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<ConversationTagModel> getTagsForConversation(@NonNull final ConversationModel conversation) {
        return this.databaseService.getConversationTagFactory().getByConversationUid(conversation.getUid());
    }

    @Override
    public boolean tag(@Nullable ConversationModel conversation, @Nullable TagModel tagModel) {
        if (!ListenerUtil.mutListener.listen(37468)) {
            if ((ListenerUtil.mutListener.listen(37464) ? (conversation != null || tagModel != null) : (conversation != null && tagModel != null))) {
                if (!ListenerUtil.mutListener.listen(37467)) {
                    if (!this.isTaggedWith(conversation, tagModel)) {
                        if (!ListenerUtil.mutListener.listen(37465)) {
                            this.databaseService.getConversationTagFactory().create(new ConversationTagModel(conversation.getUid(), tagModel.getTag()));
                        }
                        if (!ListenerUtil.mutListener.listen(37466)) {
                            this.triggerChange(conversation);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean unTag(@Nullable ConversationModel conversation, @Nullable TagModel tagModel) {
        if (!ListenerUtil.mutListener.listen(37473)) {
            if ((ListenerUtil.mutListener.listen(37469) ? (conversation != null || tagModel != null) : (conversation != null && tagModel != null))) {
                if (!ListenerUtil.mutListener.listen(37472)) {
                    if (this.isTaggedWith(conversation, tagModel)) {
                        if (!ListenerUtil.mutListener.listen(37470)) {
                            this.databaseService.getConversationTagFactory().deleteByConversationUidAndTag(conversation.getUid(), tagModel.getTag());
                        }
                        if (!ListenerUtil.mutListener.listen(37471)) {
                            this.triggerChange(conversation);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean toggle(@Nullable ConversationModel conversation, @Nullable TagModel tagModel, boolean silent) {
        if (!ListenerUtil.mutListener.listen(37482)) {
            if ((ListenerUtil.mutListener.listen(37474) ? (conversation != null || tagModel != null) : (conversation != null && tagModel != null))) {
                if (!ListenerUtil.mutListener.listen(37481)) {
                    if (this.isTaggedWith(conversation, tagModel)) {
                        if (!ListenerUtil.mutListener.listen(37478)) {
                            // remove
                            this.databaseService.getConversationTagFactory().deleteByConversationUidAndTag(conversation.getUid(), tagModel.getTag());
                        }
                        if (!ListenerUtil.mutListener.listen(37480)) {
                            if (!silent) {
                                if (!ListenerUtil.mutListener.listen(37479)) {
                                    this.triggerChange(conversation);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(37475)) {
                            // Add
                            this.databaseService.getConversationTagFactory().create(new ConversationTagModel(conversation.getUid(), tagModel.getTag()));
                        }
                        if (!ListenerUtil.mutListener.listen(37477)) {
                            if (!silent) {
                                if (!ListenerUtil.mutListener.listen(37476)) {
                                    this.triggerChange(conversation);
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
    public boolean isTaggedWith(@Nullable ConversationModel conversation, @Nullable TagModel tagModel) {
        if (!ListenerUtil.mutListener.listen(37484)) {
            if ((ListenerUtil.mutListener.listen(37483) ? (conversation == null && tagModel == null) : (conversation == null || tagModel == null))) {
                return false;
            }
        }
        return this.databaseService.getConversationTagFactory().getByConversationUidAndTag(conversation.getUid(), tagModel.getTag()) != null;
    }

    @Override
    public void removeAll(@Nullable ConversationModel conversation) {
        if (!ListenerUtil.mutListener.listen(37486)) {
            if (conversation != null) {
                if (!ListenerUtil.mutListener.listen(37485)) {
                    this.databaseService.getConversationTagFactory().deleteByConversationUid(conversation.getUid());
                }
            }
        }
    }

    @Override
    public void removeAll(@Nullable TagModel tagModel) {
        if (!ListenerUtil.mutListener.listen(37488)) {
            if (tagModel != null) {
                if (!ListenerUtil.mutListener.listen(37487)) {
                    this.databaseService.getConversationTagFactory().deleteByConversationTag(tagModel.getTag());
                }
            }
        }
    }

    @Override
    public List<ConversationTagModel> getAll() {
        return this.databaseService.getConversationTagFactory().getAll();
    }

    @Override
    public long getCount(@NonNull TagModel tagModel) {
        return this.databaseService.getConversationTagFactory().countByTag(tagModel.getTag());
    }

    private void triggerChange(final ConversationModel conversationModel) {
        if (!ListenerUtil.mutListener.listen(37490)) {
            ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                @Override
                public void handle(ConversationListener listener) {
                    if (!ListenerUtil.mutListener.listen(37489)) {
                        listener.onModified(conversationModel, conversationModel.getPosition());
                    }
                }
            });
        }
    }
}
