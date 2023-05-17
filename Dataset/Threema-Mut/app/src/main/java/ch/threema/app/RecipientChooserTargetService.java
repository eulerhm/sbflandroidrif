/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import androidx.annotation.Nullable;
import ch.threema.app.activities.RecipientListActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@TargetApi(Build.VERSION_CODES.M)
public class RecipientChooserTargetService extends ChooserTargetService {

    private static final Logger logger = LoggerFactory.getLogger(RecipientChooserTargetService.class);

    private static final int MAX_CONVERSATIONS = 8;

    private PreferenceService preferenceService;

    @Override
    @Nullable
    public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName, IntentFilter matchedFilter) {
        if (!ListenerUtil.mutListener.listen(65263)) {
            logger.debug("onGetChooserTargets");
        }
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(65264)) {
            if (serviceManager == null) {
                return null;
            }
        }
        ConversationService conversationService = null;
        GroupService groupService = null;
        ContactService contactService = null;
        if (!ListenerUtil.mutListener.listen(65265)) {
            preferenceService = null;
        }
        try {
            if (!ListenerUtil.mutListener.listen(65267)) {
                conversationService = serviceManager.getConversationService();
            }
            if (!ListenerUtil.mutListener.listen(65268)) {
                groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(65269)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(65270)) {
                preferenceService = serviceManager.getPreferenceService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(65266)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(65274)) {
            if ((ListenerUtil.mutListener.listen(65273) ? ((ListenerUtil.mutListener.listen(65272) ? ((ListenerUtil.mutListener.listen(65271) ? (conversationService == null && groupService == null) : (conversationService == null || groupService == null)) && contactService == null) : ((ListenerUtil.mutListener.listen(65271) ? (conversationService == null && groupService == null) : (conversationService == null || groupService == null)) || contactService == null)) && preferenceService == null) : ((ListenerUtil.mutListener.listen(65272) ? ((ListenerUtil.mutListener.listen(65271) ? (conversationService == null && groupService == null) : (conversationService == null || groupService == null)) && contactService == null) : ((ListenerUtil.mutListener.listen(65271) ? (conversationService == null && groupService == null) : (conversationService == null || groupService == null)) || contactService == null)) || preferenceService == null))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(65275)) {
            if (!preferenceService.isDirectShare()) {
                // only enable this feature if sync contacts is enabled (privacy risk)
                return null;
            }
        }
        final ConversationService.Filter filter = new ConversationService.Filter() {

            @Override
            public boolean onlyUnread() {
                return false;
            }

            @Override
            public boolean noDistributionLists() {
                return true;
            }

            @Override
            public boolean noHiddenChats() {
                return preferenceService.isPrivateChatsHidden();
            }

            @Override
            public boolean noInvalid() {
                return true;
            }
        };
        final List<ConversationModel> conversations = conversationService.getAll(false, filter);
        int length = Math.min(conversations.size(), MAX_CONVERSATIONS);
        final ComponentName componentName = new ComponentName(getPackageName(), Objects.requireNonNull(RecipientListActivity.class.getCanonicalName()));
        final List<ChooserTarget> targets = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(65296)) {
            {
                long _loopCounter795 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(65295) ? (i >= length) : (ListenerUtil.mutListener.listen(65294) ? (i <= length) : (ListenerUtil.mutListener.listen(65293) ? (i > length) : (ListenerUtil.mutListener.listen(65292) ? (i != length) : (ListenerUtil.mutListener.listen(65291) ? (i == length) : (i < length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter795", ++_loopCounter795);
                    final String title;
                    final Bitmap avatar;
                    final Bundle extras = new Bundle();
                    final ConversationModel conversationModel = conversations.get(i);
                    if (conversationModel.isGroupConversation()) {
                        title = NameUtil.getDisplayName(conversationModel.getGroup(), groupService);
                        avatar = groupService.getAvatar(conversationModel.getGroup(), false);
                        if (!ListenerUtil.mutListener.listen(65277)) {
                            extras.putInt(IntentDataUtil.INTENT_DATA_GROUP_ID, conversationModel.getGroup().getId());
                        }
                    } else {
                        title = NameUtil.getDisplayNameOrNickname(conversationModel.getContact(), true);
                        avatar = contactService.getAvatar(conversationModel.getContact(), false);
                        if (!ListenerUtil.mutListener.listen(65276)) {
                            extras.putString(IntentDataUtil.INTENT_DATA_IDENTITY, conversationModel.getContact().getIdentity());
                        }
                    }
                    final Icon icon = Icon.createWithBitmap(avatar);
                    final float score = (ListenerUtil.mutListener.listen(65289) ? (((ListenerUtil.mutListener.listen(65285) ? ((float) MAX_CONVERSATIONS % ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65284) ? ((float) MAX_CONVERSATIONS / ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65283) ? ((float) MAX_CONVERSATIONS * ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65282) ? ((float) MAX_CONVERSATIONS + ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : ((float) MAX_CONVERSATIONS - ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2)))))))))))) % (float) MAX_CONVERSATIONS) : (ListenerUtil.mutListener.listen(65288) ? (((ListenerUtil.mutListener.listen(65285) ? ((float) MAX_CONVERSATIONS % ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65284) ? ((float) MAX_CONVERSATIONS / ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65283) ? ((float) MAX_CONVERSATIONS * ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65282) ? ((float) MAX_CONVERSATIONS + ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : ((float) MAX_CONVERSATIONS - ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2)))))))))))) * (float) MAX_CONVERSATIONS) : (ListenerUtil.mutListener.listen(65287) ? (((ListenerUtil.mutListener.listen(65285) ? ((float) MAX_CONVERSATIONS % ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65284) ? ((float) MAX_CONVERSATIONS / ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65283) ? ((float) MAX_CONVERSATIONS * ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65282) ? ((float) MAX_CONVERSATIONS + ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : ((float) MAX_CONVERSATIONS - ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2)))))))))))) - (float) MAX_CONVERSATIONS) : (ListenerUtil.mutListener.listen(65286) ? (((ListenerUtil.mutListener.listen(65285) ? ((float) MAX_CONVERSATIONS % ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65284) ? ((float) MAX_CONVERSATIONS / ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65283) ? ((float) MAX_CONVERSATIONS * ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65282) ? ((float) MAX_CONVERSATIONS + ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : ((float) MAX_CONVERSATIONS - ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2)))))))))))) + (float) MAX_CONVERSATIONS) : (((ListenerUtil.mutListener.listen(65285) ? ((float) MAX_CONVERSATIONS % ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65284) ? ((float) MAX_CONVERSATIONS / ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65283) ? ((float) MAX_CONVERSATIONS * ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : (ListenerUtil.mutListener.listen(65282) ? ((float) MAX_CONVERSATIONS + ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2))))))) : ((float) MAX_CONVERSATIONS - ((ListenerUtil.mutListener.listen(65281) ? ((float) i % 2) : (ListenerUtil.mutListener.listen(65280) ? ((float) i * 2) : (ListenerUtil.mutListener.listen(65279) ? ((float) i - 2) : (ListenerUtil.mutListener.listen(65278) ? ((float) i + 2) : ((float) i / 2)))))))))))) / (float) MAX_CONVERSATIONS)))));
                    if (!ListenerUtil.mutListener.listen(65290)) {
                        targets.add(new ChooserTarget(title, icon, score, componentName, extras));
                    }
                }
            }
        }
        return targets;
    }
}
