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
package ch.threema.app.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.SystemClock;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.Nullable;
import ch.threema.app.BuildConfig;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.backuprestore.BackupRestoreDataService;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.mediaattacher.MediaFilterQuery;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.DistributionListMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.ServerMessageModel;
import ch.threema.storage.models.WebClientSessionModel;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IntentDataUtil {

    public static final String ACTION_LICENSE_NOT_ALLOWED = BuildConfig.APPLICATION_ID + "license_not_allowed";

    public static final String ACTION_CONTACTS_CHANGED = BuildConfig.APPLICATION_ID + "contacts_changed";

    public static final String ACTION_UPDATE_AVAILABLE = BuildConfig.APPLICATION_ID + "update_available";

    public static final String INTENT_DATA_LOCATION_LAT = "latitude";

    public static final String INTENT_DATA_LOCATION_LNG = "longitude";

    public static final String INTENT_DATA_LOCATION_NAME = "lname";

    private static final String INTENT_DATA_LOCATION_ALT = "altitude";

    private static final String INTENT_DATA_LOCATION_ACCURACY = "accuracy";

    public static final String INTENT_DATA_LOCATION_PROVIDER = "location_provider";

    private static final String INTENT_DATA_CONTACT_LIST = "contactl";

    private static final String INTENT_DATA_GROUP_LIST = "groupl";

    private static final String INTENT_DATA_DIST_LIST = "distl";

    private static final String INTENT_DATA_SERVER_MESSAGE_TEXT = "server_message_text";

    private static final String INTENT_DATA_SERVER_MESSAGE_TYPE = "server_message_type";

    private static final String INTENT_DATA_MESSAGE = "message";

    private static final String INTENT_DATA_URL = "url";

    private static final String INTENT_DATA_CONTACTS = "contacts";

    public static final String INTENT_DATA_GROUP_ID = "group_id";

    private static final String INTENT_DATA_ABSTRACT_MESSAGE_ID = "abstract_message_id";

    private static final String INTENT_DATA_ABSTRACT_MESSAGE_IDS = "abstract_message_ids";

    private static final String INTENT_DATA_ABSTRACT_MESSAGE_TYPE = "abstract_message_type";

    private static final String INTENT_DATA_ABSTRACT_MESSAGE_TYPES = "abstract_message_types";

    public static final String INTENT_DATA_IDENTITY = "identity";

    public static final String INTENT_DATA_WEB_CLIENT_SESSION_MODEL_ID = "session_model_id";

    public static final String INTENT_DATA_PAYLOAD = "payload";

    private static final String INTENT_DATA_BACKUP_FILE = "backup_file";

    private static final String INTENT_HIDE_AFTER_UNLOCK = "hide_after_unlock";

    private static final String INTENT_DATA_BALLOT_ID = "ballot_id";

    private static final String INTENT_DATA_BALLOT_CHOICE_ID = "ballot_choide_id";

    public static void append(BackupRestoreDataService.BackupData backupData, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54402)) {
            intent.putExtra(INTENT_DATA_BACKUP_FILE, backupData.getFile().getPath());
        }
    }

    public static void append(byte[] payload, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54403)) {
            intent.putExtra(INTENT_DATA_PAYLOAD, payload);
        }
    }

    public static void append(ContactModel contactModel, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54404)) {
            intent.putExtra(INTENT_DATA_IDENTITY, contactModel.getIdentity());
        }
    }

    public static void append(String identity, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54405)) {
            intent.putExtra(INTENT_DATA_IDENTITY, identity);
        }
    }

    public static void append(GroupModel groupModel, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54406)) {
            intent.putExtra(INTENT_DATA_GROUP_ID, groupModel.getId());
        }
    }

    public static void append(LatLng latLng, String provider, String name, String address, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54407)) {
            intent.putExtra(INTENT_DATA_LOCATION_LAT, latLng.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(54408)) {
            intent.putExtra(INTENT_DATA_LOCATION_LNG, latLng.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(54409)) {
            intent.putExtra(INTENT_DATA_LOCATION_PROVIDER, provider);
        }
        if (!ListenerUtil.mutListener.listen(54412)) {
            if (TestUtil.empty(name)) {
                if (!ListenerUtil.mutListener.listen(54411)) {
                    intent.putExtra(INTENT_DATA_LOCATION_NAME, address);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(54410)) {
                    intent.putExtra(INTENT_DATA_LOCATION_NAME, name);
                }
            }
        }
    }

    public static void append(ServerMessageModel serverMessageModel, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54413)) {
            intent.putExtra(INTENT_DATA_SERVER_MESSAGE_TEXT, serverMessageModel.getMessage());
        }
        if (!ListenerUtil.mutListener.listen(54414)) {
            intent.putExtra(INTENT_DATA_SERVER_MESSAGE_TYPE, serverMessageModel.getType().toString());
        }
    }

    public static void append(AbstractMessageModel abstractMessageModel, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54415)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_ID, abstractMessageModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(54416)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_TYPE, abstractMessageModel.getClass().toString());
        }
    }

    public static void append(WebClientSessionModel model, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54417)) {
            intent.putExtra(INTENT_DATA_WEB_CLIENT_SESSION_MODEL_ID, model.getId());
        }
    }

    public static void appendMultiple(List<AbstractMessageModel> models, Intent intent) {
        ArrayList<Integer> messageIDs = new ArrayList<>(models.size());
        if (!ListenerUtil.mutListener.listen(54419)) {
            {
                long _loopCounter655 = 0;
                for (AbstractMessageModel model : models) {
                    ListenerUtil.loopListener.listen("_loopCounter655", ++_loopCounter655);
                    if (!ListenerUtil.mutListener.listen(54418)) {
                        messageIDs.add(model.getId());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54420)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_IDS, messageIDs);
        }
        if (!ListenerUtil.mutListener.listen(54421)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_TYPE, models.get(0).getClass().toString());
        }
    }

    public static void appendMultipleMessageTypes(List<AbstractMessageModel> models, Intent intent) {
        ArrayList<Integer> messageIDs = new ArrayList<>(models.size());
        ArrayList<String> messageTypes = new ArrayList<>();
        Iterator<AbstractMessageModel> iterator = models.iterator();
        if (!ListenerUtil.mutListener.listen(54424)) {
            {
                long _loopCounter656 = 0;
                while (iterator.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter656", ++_loopCounter656);
                    AbstractMessageModel failedMessage = iterator.next();
                    if (!ListenerUtil.mutListener.listen(54422)) {
                        messageIDs.add(failedMessage.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(54423)) {
                        messageTypes.add(failedMessage.getClass().toString());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54425)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_IDS, messageIDs);
        }
        if (!ListenerUtil.mutListener.listen(54426)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_TYPES, messageTypes);
        }
    }

    public static void append(int id, String classname, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54427)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_ID, id);
        }
        if (!ListenerUtil.mutListener.listen(54428)) {
            intent.putExtra(INTENT_DATA_ABSTRACT_MESSAGE_TYPE, classname);
        }
    }

    public static void append(List<ContactModel> contacts, Intent intent) {
        String[] identities = new String[contacts.size()];
        int p = 0;
        if (!ListenerUtil.mutListener.listen(54430)) {
            {
                long _loopCounter657 = 0;
                for (ContactModel c : contacts) {
                    ListenerUtil.loopListener.listen("_loopCounter657", ++_loopCounter657);
                    if (!ListenerUtil.mutListener.listen(54429)) {
                        identities[p++] = c.getIdentity();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54431)) {
            intent.putExtra(INTENT_DATA_CONTACTS, identities);
        }
    }

    public static Location getLocation(Intent intent) {
        Location location = new Location(intent.getStringExtra(INTENT_DATA_LOCATION_PROVIDER));
        if (!ListenerUtil.mutListener.listen(54432)) {
            location.setLatitude(intent.getDoubleExtra(INTENT_DATA_LOCATION_LAT, 0));
        }
        if (!ListenerUtil.mutListener.listen(54433)) {
            location.setLongitude(intent.getDoubleExtra(INTENT_DATA_LOCATION_LNG, 0));
        }
        if (!ListenerUtil.mutListener.listen(54434)) {
            location.setAltitude(intent.getDoubleExtra(INTENT_DATA_LOCATION_ALT, 0));
        }
        if (!ListenerUtil.mutListener.listen(54435)) {
            location.setAccuracy(intent.getFloatExtra(INTENT_DATA_LOCATION_ACCURACY, 0));
        }
        return location;
    }

    public static ServerMessageModel getServerMessageModel(Intent intent) {
        return new ServerMessageModel(intent.getStringExtra(INTENT_DATA_SERVER_MESSAGE_TEXT), ServerMessageModel.Type.ALERT.toString().equals(intent.getStringExtra(INTENT_DATA_SERVER_MESSAGE_TYPE)) ? ServerMessageModel.Type.ALERT : ServerMessageModel.Type.ERROR);
    }

    public static Intent createActionIntentLicenseNotAllowed(String message) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(54436)) {
            intent.putExtra(INTENT_DATA_MESSAGE, message);
        }
        if (!ListenerUtil.mutListener.listen(54437)) {
            intent.setAction(ACTION_LICENSE_NOT_ALLOWED);
        }
        return intent;
    }

    public static Intent createActionIntentUpdateAvailable(String updateMessage, String updateUrl) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(54438)) {
            intent.putExtra(INTENT_DATA_MESSAGE, updateMessage);
        }
        if (!ListenerUtil.mutListener.listen(54439)) {
            intent.putExtra(INTENT_DATA_URL, updateUrl);
        }
        if (!ListenerUtil.mutListener.listen(54440)) {
            intent.setAction(ACTION_UPDATE_AVAILABLE);
        }
        return intent;
    }

    public static Intent createActionIntentHideAfterUnlock(Intent intent) {
        if (!ListenerUtil.mutListener.listen(54441)) {
            intent.putExtra(INTENT_HIDE_AFTER_UNLOCK, true);
        }
        return intent;
    }

    public static boolean hideAfterUnlock(Intent intent) {
        return (ListenerUtil.mutListener.listen(54442) ? (intent.hasExtra(INTENT_HIDE_AFTER_UNLOCK) || intent.getBooleanExtra(INTENT_HIDE_AFTER_UNLOCK, false)) : (intent.hasExtra(INTENT_HIDE_AFTER_UNLOCK) && intent.getBooleanExtra(INTENT_HIDE_AFTER_UNLOCK, false)));
    }

    public static Intent createActionIntentContactsChanged() {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(54443)) {
            intent.setAction(ACTION_CONTACTS_CHANGED);
        }
        return intent;
    }

    public static String getMessage(Intent intent) {
        return intent.getStringExtra(INTENT_DATA_MESSAGE);
    }

    public static String getUrl(Intent intent) {
        return intent.getStringExtra(INTENT_DATA_URL);
    }

    public static String[] getContactIdentities(Intent intent) {
        return intent.getStringArrayExtra(INTENT_DATA_CONTACTS);
    }

    public static int getGroupId(Intent intent) {
        if (!ListenerUtil.mutListener.listen(54444)) {
            if (intent.hasExtra(INTENT_DATA_GROUP_ID)) {
                return intent.getIntExtra(INTENT_DATA_GROUP_ID, -1);
            }
        }
        return -1;
    }

    public static String getIdentity(Intent intent) {
        if (!ListenerUtil.mutListener.listen(54445)) {
            if (intent.hasExtra(INTENT_DATA_IDENTITY)) {
                return intent.getStringExtra(INTENT_DATA_IDENTITY);
            }
        }
        return null;
    }

    public static void append(BallotModel ballotModel, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54446)) {
            intent.putExtra(INTENT_DATA_BALLOT_ID, ballotModel.getId());
        }
    }

    public static int getBallotId(Intent intent) {
        if (!ListenerUtil.mutListener.listen(54447)) {
            if (intent.hasExtra(INTENT_DATA_BALLOT_ID)) {
                return intent.getIntExtra(INTENT_DATA_BALLOT_ID, 0);
            }
        }
        return 0;
    }

    public static void append(BallotChoiceModel ballotChoiceModel, Intent intent) {
        if (!ListenerUtil.mutListener.listen(54448)) {
            intent.putExtra(INTENT_DATA_BALLOT_CHOICE_ID, ballotChoiceModel.getId());
        }
    }

    public static int getBallotChoiceId(Intent intent) {
        if (!ListenerUtil.mutListener.listen(54449)) {
            if (intent.hasExtra(INTENT_DATA_BALLOT_CHOICE_ID)) {
                return intent.getIntExtra(INTENT_DATA_BALLOT_CHOICE_ID, 0);
            }
        }
        return 0;
    }

    @Nullable
    public static String getAbstractMessageType(Intent intent) {
        return intent.getStringExtra(INTENT_DATA_ABSTRACT_MESSAGE_TYPE);
    }

    public static int getAbstractMessageId(Intent intent) {
        return intent.getIntExtra(INTENT_DATA_ABSTRACT_MESSAGE_ID, 0);
    }

    public static ArrayList<Integer> getAbstractMessageIds(Intent intent) {
        return intent.getIntegerArrayListExtra(INTENT_DATA_ABSTRACT_MESSAGE_IDS);
    }

    public static ArrayList<String> getAbstractMessageTypes(Intent intent) {
        return intent.getStringArrayListExtra(INTENT_DATA_ABSTRACT_MESSAGE_TYPES);
    }

    public static AbstractMessageModel getAbstractMessageModel(Intent intent, MessageService messageService) {
        if (!ListenerUtil.mutListener.listen(54451)) {
            if ((ListenerUtil.mutListener.listen(54450) ? (intent != null || messageService != null) : (intent != null && messageService != null))) {
                int id = getAbstractMessageId(intent);
                String type = getAbstractMessageType(intent);
                return messageService.getMessageModelFromId(id, type);
            }
        }
        return null;
    }

    public static ArrayList<AbstractMessageModel> getAbstractMessageModels(Intent intent, MessageService messageService) {
        ArrayList<Integer> messageIDs = getAbstractMessageIds(intent);
        ArrayList<String> messageTypes = getAbstractMessageTypes(intent);
        ArrayList<AbstractMessageModel> messageModels = new ArrayList<>(messageIDs.size());
        Iterator<Integer> ids = messageIDs.iterator();
        Iterator<String> types = messageTypes.iterator();
        if (!ListenerUtil.mutListener.listen(54454)) {
            {
                long _loopCounter658 = 0;
                while ((ListenerUtil.mutListener.listen(54453) ? (ids.hasNext() || types.hasNext()) : (ids.hasNext() && types.hasNext()))) {
                    ListenerUtil.loopListener.listen("_loopCounter658", ++_loopCounter658);
                    if (!ListenerUtil.mutListener.listen(54452)) {
                        messageModels.add(messageService.getMessageModelFromId(ids.next(), types.next()));
                    }
                }
            }
        }
        return messageModels;
    }

    public static MessageReceiver getMessageReceiverFromIntent(Context context, Intent intent) {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        ContactService contactService;
        GroupService groupService;
        DistributionListService distributionListService;
        try {
            contactService = serviceManager.getContactService();
            groupService = serviceManager.getGroupService();
            distributionListService = serviceManager.getDistributionListService();
        } catch (Exception e) {
            return null;
        }
        if (!ListenerUtil.mutListener.listen(54455)) {
            if (!TestUtil.required(contactService, groupService, distributionListService)) {
                return null;
            }
        }
        String identity = ContactUtil.getIdentityFromViewIntent(context, intent);
        if (!ListenerUtil.mutListener.listen(54456)) {
            if (!TestUtil.empty(identity)) {
                return contactService.createReceiver(contactService.getByIdentity(identity));
            }
        }
        if (!ListenerUtil.mutListener.listen(54457)) {
            if (intent.hasExtra(ThreemaApplication.INTENT_DATA_CONTACT)) {
                String cIdentity = intent.getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
                return contactService.createReceiver(contactService.getByIdentity(cIdentity));
            } else if (intent.hasExtra(ThreemaApplication.INTENT_DATA_GROUP)) {
                int groupId = intent.getIntExtra(ThreemaApplication.INTENT_DATA_GROUP, 0);
                return groupService.createReceiver(groupService.getById(groupId));
            } else if (intent.hasExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST)) {
                int distId = intent.getIntExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, 0);
                return distributionListService.createReceiver(distributionListService.getById(distId));
            }
        }
        return null;
    }

    /**
     *  Get a list of message receivers from an intent
     *  @param intent
     *  @return ArrayList of MessageReceivers
     */
    public static ArrayList<MessageReceiver> getMessageReceiversFromIntent(Intent intent) {
        ArrayList<MessageReceiver> messageReceivers = new ArrayList<>();
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        ContactService contactService;
        GroupService groupService;
        DistributionListService distributionListService;
        try {
            contactService = serviceManager.getContactService();
            groupService = serviceManager.getGroupService();
            distributionListService = serviceManager.getDistributionListService();
        } catch (Exception e) {
            return null;
        }
        if (!ListenerUtil.mutListener.listen(54458)) {
            if (!TestUtil.required(contactService, groupService, distributionListService)) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(54461)) {
            if (intent.hasExtra(INTENT_DATA_CONTACT_LIST)) {
                ArrayList<String> contactIds = intent.getStringArrayListExtra(INTENT_DATA_CONTACT_LIST);
                if (!ListenerUtil.mutListener.listen(54460)) {
                    {
                        long _loopCounter659 = 0;
                        for (String contactId : contactIds) {
                            ListenerUtil.loopListener.listen("_loopCounter659", ++_loopCounter659);
                            if (!ListenerUtil.mutListener.listen(54459)) {
                                messageReceivers.add(contactService.createReceiver(contactService.getByIdentity(contactId)));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54464)) {
            if (intent.hasExtra(INTENT_DATA_GROUP_LIST)) {
                ArrayList<Integer> groupIds = intent.getIntegerArrayListExtra(INTENT_DATA_GROUP_LIST);
                if (!ListenerUtil.mutListener.listen(54463)) {
                    {
                        long _loopCounter660 = 0;
                        for (int groupId : groupIds) {
                            ListenerUtil.loopListener.listen("_loopCounter660", ++_loopCounter660);
                            if (!ListenerUtil.mutListener.listen(54462)) {
                                messageReceivers.add(groupService.createReceiver(groupService.getById(groupId)));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54467)) {
            if (intent.hasExtra(INTENT_DATA_DIST_LIST)) {
                ArrayList<Integer> distributionListIds = intent.getIntegerArrayListExtra(INTENT_DATA_DIST_LIST);
                if (!ListenerUtil.mutListener.listen(54466)) {
                    {
                        long _loopCounter661 = 0;
                        for (int distributionListId : distributionListIds) {
                            ListenerUtil.loopListener.listen("_loopCounter661", ++_loopCounter661);
                            if (!ListenerUtil.mutListener.listen(54465)) {
                                messageReceivers.add(distributionListService.createReceiver(distributionListService.getById(distributionListId)));
                            }
                        }
                    }
                }
            }
        }
        return messageReceivers;
    }

    public static Intent addMessageReceiverToIntent(Intent intent, MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(54471)) {
            switch(messageReceiver.getType()) {
                case MessageReceiver.Type_CONTACT:
                    if (!ListenerUtil.mutListener.listen(54468)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, ((ContactMessageReceiver) messageReceiver).getContact().getIdentity());
                    }
                    break;
                case MessageReceiver.Type_GROUP:
                    if (!ListenerUtil.mutListener.listen(54469)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, ((GroupMessageReceiver) messageReceiver).getGroup().getId());
                    }
                    break;
                case MessageReceiver.Type_DISTRIBUTION_LIST:
                    if (!ListenerUtil.mutListener.listen(54470)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, ((DistributionListMessageReceiver) messageReceiver).getDistributionList().getId());
                    }
                    break;
                default:
                    break;
            }
        }
        return intent;
    }

    /**
     *  Add extras to an existing intent representing a list of MessageReceivers
     *  @param intent
     *  @param messageReceivers
     *  @return intent
     */
    public static Intent addMessageReceiversToIntent(Intent intent, MessageReceiver[] messageReceivers) {
        ArrayList<String> contactIds = new ArrayList<>();
        ArrayList<Integer> groupIds = new ArrayList<>();
        ArrayList<Integer> distributionListIds = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(54476)) {
            {
                long _loopCounter662 = 0;
                for (MessageReceiver messageReceiver : messageReceivers) {
                    ListenerUtil.loopListener.listen("_loopCounter662", ++_loopCounter662);
                    if (!ListenerUtil.mutListener.listen(54475)) {
                        switch(messageReceiver.getType()) {
                            case MessageReceiver.Type_CONTACT:
                                if (!ListenerUtil.mutListener.listen(54472)) {
                                    contactIds.add(((ContactMessageReceiver) messageReceiver).getContact().getIdentity());
                                }
                                break;
                            case MessageReceiver.Type_GROUP:
                                if (!ListenerUtil.mutListener.listen(54473)) {
                                    groupIds.add(((GroupMessageReceiver) messageReceiver).getGroup().getId());
                                }
                                break;
                            case MessageReceiver.Type_DISTRIBUTION_LIST:
                                if (!ListenerUtil.mutListener.listen(54474)) {
                                    distributionListIds.add(((DistributionListMessageReceiver) messageReceiver).getDistributionList().getId());
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54483)) {
            if ((ListenerUtil.mutListener.listen(54481) ? (contactIds.size() >= 0) : (ListenerUtil.mutListener.listen(54480) ? (contactIds.size() <= 0) : (ListenerUtil.mutListener.listen(54479) ? (contactIds.size() < 0) : (ListenerUtil.mutListener.listen(54478) ? (contactIds.size() != 0) : (ListenerUtil.mutListener.listen(54477) ? (contactIds.size() == 0) : (contactIds.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(54482)) {
                    intent.putExtra(INTENT_DATA_CONTACT_LIST, contactIds);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54490)) {
            if ((ListenerUtil.mutListener.listen(54488) ? (groupIds.size() >= 0) : (ListenerUtil.mutListener.listen(54487) ? (groupIds.size() <= 0) : (ListenerUtil.mutListener.listen(54486) ? (groupIds.size() < 0) : (ListenerUtil.mutListener.listen(54485) ? (groupIds.size() != 0) : (ListenerUtil.mutListener.listen(54484) ? (groupIds.size() == 0) : (groupIds.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(54489)) {
                    intent.putExtra(INTENT_DATA_GROUP_LIST, groupIds);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54497)) {
            if ((ListenerUtil.mutListener.listen(54495) ? (distributionListIds.size() >= 0) : (ListenerUtil.mutListener.listen(54494) ? (distributionListIds.size() <= 0) : (ListenerUtil.mutListener.listen(54493) ? (distributionListIds.size() < 0) : (ListenerUtil.mutListener.listen(54492) ? (distributionListIds.size() != 0) : (ListenerUtil.mutListener.listen(54491) ? (distributionListIds.size() == 0) : (distributionListIds.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(54496)) {
                    intent.putExtra(INTENT_DATA_DIST_LIST, distributionListIds);
                }
            }
        }
        return intent;
    }

    public static AbstractMessageModel getMessageModelFromReceiver(Intent intent, MessageReceiver messageReceiver) {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        MessageService messageService;
        try {
            messageService = serviceManager.getMessageService();
        } catch (Exception e) {
            return null;
        }
        if (!ListenerUtil.mutListener.listen(54505)) {
            if (intent.hasExtra(ThreemaApplication.INTENT_DATA_MESSAGE_ID)) {
                int id = intent.getIntExtra(ThreemaApplication.INTENT_DATA_MESSAGE_ID, -1);
                if (!ListenerUtil.mutListener.listen(54504)) {
                    if ((ListenerUtil.mutListener.listen(54502) ? (id <= 0) : (ListenerUtil.mutListener.listen(54501) ? (id > 0) : (ListenerUtil.mutListener.listen(54500) ? (id < 0) : (ListenerUtil.mutListener.listen(54499) ? (id != 0) : (ListenerUtil.mutListener.listen(54498) ? (id == 0) : (id >= 0))))))) {
                        if (!ListenerUtil.mutListener.listen(54503)) {
                            if (messageReceiver.getType() == MessageReceiver.Type_CONTACT) {
                                return messageService.getContactMessageModel(id, true);
                            } else if (messageReceiver.getType() == MessageReceiver.Type_GROUP) {
                                return messageService.getGroupMessageModel(id, true);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *  get the payload byte array or null
     *  @param intent
     *  @return
     */
    public static byte[] getPayload(Intent intent) {
        return intent.hasExtra(INTENT_DATA_PAYLOAD) ? intent.getByteArrayExtra(INTENT_DATA_PAYLOAD) : null;
    }

    public static Intent getShowConversationIntent(ConversationModel conversationModel, Context context) {
        if (!ListenerUtil.mutListener.listen(54506)) {
            if (conversationModel == null) {
                return null;
            }
        }
        Intent intent = new Intent(context, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(54510)) {
            if (conversationModel.isGroupConversation()) {
                if (!ListenerUtil.mutListener.listen(54509)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, conversationModel.getGroup().getId());
                }
            } else if (conversationModel.isDistributionListConversation()) {
                if (!ListenerUtil.mutListener.listen(54508)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, conversationModel.getDistributionList().getId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(54507)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, conversationModel.getContact().getIdentity());
                }
            }
        }
        return intent;
    }

    public static Intent getComposeIntentForReceivers(Context context, ArrayList<MessageReceiver> receivers) {
        Intent intent;
        if ((ListenerUtil.mutListener.listen(54515) ? (receivers.size() <= 1) : (ListenerUtil.mutListener.listen(54514) ? (receivers.size() > 1) : (ListenerUtil.mutListener.listen(54513) ? (receivers.size() < 1) : (ListenerUtil.mutListener.listen(54512) ? (receivers.size() != 1) : (ListenerUtil.mutListener.listen(54511) ? (receivers.size() == 1) : (receivers.size() >= 1))))))) {
            intent = addMessageReceiverToIntent(new Intent(context, ComposeMessageActivity.class), receivers.get(0));
            if (!ListenerUtil.mutListener.listen(54516)) {
                intent.putExtra(ThreemaApplication.INTENT_DATA_EDITFOCUS, Boolean.TRUE);
            }
        } else {
            intent = new Intent(context, HomeActivity.class);
        }
        if (!ListenerUtil.mutListener.listen(54517)) {
            // fix for <4.1 - keeps android from re-using existing intent and stripping extras
            intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
        }
        if (!ListenerUtil.mutListener.listen(54518)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(54519)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_TIMESTAMP, SystemClock.elapsedRealtime());
        }
        return intent;
    }

    public static Intent getJumpToMessageIntent(Context context, AbstractMessageModel messageModel) {
        Intent intent = new Intent(context, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(54520)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(54524)) {
            if (messageModel instanceof GroupMessageModel) {
                if (!ListenerUtil.mutListener.listen(54523)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, ((GroupMessageModel) messageModel).getGroupId());
                }
            } else if (messageModel instanceof DistributionListMessageModel) {
                if (!ListenerUtil.mutListener.listen(54522)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, ((DistributionListMessageModel) messageModel).getDistributionListId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(54521)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, messageModel.getIdentity());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54525)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_API_MESSAGE_ID, messageModel.getApiMessageId());
        }
        if (!ListenerUtil.mutListener.listen(54526)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_SEARCH_QUERY, " ");
        }
        return intent;
    }

    public static MediaFilterQuery getLastMediaFilterFromIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(54527)) {
            if (intent.getStringExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_SEARCH_QUERY) != null) {
                return new MediaFilterQuery(intent.getStringExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_SEARCH_QUERY), intent.getIntExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_TYPE_QUERY, -1));
            }
        }
        return null;
    }

    public static Intent addLastMediaFilterToIntent(Intent intent, MediaFilterQuery mediaFilterQuery) {
        if (!ListenerUtil.mutListener.listen(54528)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_SEARCH_QUERY, mediaFilterQuery.getQuery());
        }
        if (!ListenerUtil.mutListener.listen(54529)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_TYPE_QUERY, mediaFilterQuery.getType());
        }
        return intent;
    }

    public static Intent addLastMediaFilterToIntent(Intent intent, String query, int type) {
        if (!ListenerUtil.mutListener.listen(54530)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_SEARCH_QUERY, query);
        }
        if (!ListenerUtil.mutListener.listen(54531)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_LAST_MEDIA_TYPE_QUERY, type);
        }
        return intent;
    }
}
