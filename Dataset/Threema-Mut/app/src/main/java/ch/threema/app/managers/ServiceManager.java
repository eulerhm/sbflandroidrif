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
package ch.threema.app.managers;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;
import androidx.annotation.NonNull;
import ch.threema.app.BuildFlavor;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.backuprestore.BackupChatService;
import ch.threema.app.backuprestore.BackupChatServiceImpl;
import ch.threema.app.backuprestore.BackupRestoreDataService;
import ch.threema.app.backuprestore.csv.BackupRestoreDataServiceImpl;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.exceptions.NoIdentityException;
import ch.threema.app.processors.MessageAckProcessor;
import ch.threema.app.processors.MessageProcessor;
import ch.threema.app.services.ActivityService;
import ch.threema.app.services.ApiService;
import ch.threema.app.services.ApiServiceImpl;
import ch.threema.app.services.AvatarCacheService;
import ch.threema.app.services.AvatarCacheServiceImpl;
import ch.threema.app.services.BrowserDetectionService;
import ch.threema.app.services.BrowserDetectionServiceImpl;
import ch.threema.app.services.CacheService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ContactServiceImpl;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.ConversationServiceImpl;
import ch.threema.app.services.ConversationTagService;
import ch.threema.app.services.ConversationTagServiceImpl;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DeadlineListServiceImpl;
import ch.threema.app.services.DeviceService;
import ch.threema.app.services.DeviceServiceImpl;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.DistributionListServiceImpl;
import ch.threema.app.services.DownloadService;
import ch.threema.app.services.DownloadServiceImpl;
import ch.threema.app.services.FileService;
import ch.threema.app.services.FileServiceImpl;
import ch.threema.app.services.FingerPrintService;
import ch.threema.app.services.FingerPrintServiceImpl;
import ch.threema.app.services.GroupApiService;
import ch.threema.app.services.GroupApiServiceImpl;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.GroupServiceImpl;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.IdListServiceImpl;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.LifetimeServiceImpl;
import ch.threema.app.services.LocaleService;
import ch.threema.app.services.LocaleServiceImpl;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.MessageServiceImpl;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.NotificationServiceImpl;
import ch.threema.app.services.PinLockService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.PreferenceServiceImpl;
import ch.threema.app.services.QRCodeService;
import ch.threema.app.services.QRCodeServiceImpl;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.services.RingtoneServiceImpl;
import ch.threema.app.services.SensorService;
import ch.threema.app.services.SensorServiceImpl;
import ch.threema.app.services.ShortcutService;
import ch.threema.app.services.ShortcutServiceImpl;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.services.SynchronizeContactsServiceImpl;
import ch.threema.app.services.SystemScreenLockService;
import ch.threema.app.services.SystemScreenLockServiceImpl;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.UserServiceImpl;
import ch.threema.app.services.WallpaperService;
import ch.threema.app.services.WallpaperServiceImpl;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.services.ballot.BallotServiceImpl;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.LicenseServiceSerial;
import ch.threema.app.services.license.LicenseServiceUser;
import ch.threema.app.services.messageplayer.MessagePlayerService;
import ch.threema.app.services.messageplayer.MessagePlayerServiceImpl;
import ch.threema.app.stores.ContactStore;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.stores.PreferenceStoreInterface;
import ch.threema.app.threemasafe.ThreemaSafeService;
import ch.threema.app.threemasafe.ThreemaSafeServiceImpl;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DeviceIdUtil;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.app.webclient.manager.WebClientServiceManager;
import ch.threema.app.webclient.services.ServicesContainer;
import ch.threema.base.ThreemaException;
import ch.threema.client.APIConnector;
import ch.threema.client.MessageQueue;
import ch.threema.client.ThreemaConnection;
import ch.threema.localcrypto.MasterKey;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.DatabaseServiceNew;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ServiceManager {

    private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);

    private final ThreemaConnection connection;

    private final IdentityStore identityStore;

    private final MasterKey masterKey;

    private final PreferenceStoreInterface preferenceStore;

    private final UpdateSystemService updateSystemService;

    private final CacheService cacheService;

    private ContactStore contactStore;

    private APIConnector apiConnector;

    private MessageQueue messageQueue;

    private ContactService contactService;

    private UserService userService;

    private MessageService messageService;

    private QRCodeService qrCodeService;

    private FingerPrintService fingerPrintService;

    private FileService fileService;

    private PreferenceService preferencesService;

    private LocaleService localeService;

    private DeviceService deviceService;

    private LifetimeService lifetimeService;

    private AvatarCacheService avatarCacheService;

    private LicenseService licenseService;

    private BackupRestoreDataService backupRestoreDataService;

    private GroupService groupService;

    private GroupApiService groupApiService;

    private MessageAckProcessor messageAckProcessor;

    private LockAppService lockAppService;

    private ActivityService activityService;

    private ApiService apiService;

    private ConversationService conversationService;

    private NotificationService notificationService;

    private SynchronizeContactsService synchronizeContactsService;

    private SystemScreenLockService systemScreenLockService;

    private ShortcutService shortcutService;

    private IdListService blackListService, excludedSyncIdentitiesService, profilePicRecipientsService;

    private DeadlineListService mutedChatsListService, hiddenChatListService, mentionOnlyChatsListService;

    private DistributionListService distributionListService;

    private MessageProcessor messageProcessor;

    private MessagePlayerService messagePlayerService = null;

    private DownloadServiceImpl downloadService;

    private BallotService ballotService;

    private WallpaperService wallpaperService;

    private ThreemaSafeService threemaSafeService;

    private RingtoneService ringtoneService;

    private BackupChatService backupChatService;

    private DatabaseServiceNew databaseServiceNew;

    private SensorService sensorService;

    private VoipStateService voipStateService;

    private BrowserDetectionService browserDetectionService;

    private ConversationTagServiceImpl conversationTagService;

    private WebClientServiceManager webClientServiceManager;

    public ServiceManager(ThreemaConnection connection, DatabaseServiceNew databaseServiceNew, IdentityStore identityStore, PreferenceStoreInterface preferenceStore, MasterKey masterKey, UpdateSystemService updateSystemService) {
        this.cacheService = new CacheService();
        this.connection = connection;
        this.preferenceStore = preferenceStore;
        this.identityStore = identityStore;
        this.masterKey = masterKey;
        if (!ListenerUtil.mutListener.listen(29176)) {
            this.databaseServiceNew = databaseServiceNew;
        }
        this.updateSystemService = updateSystemService;
    }

    private ContactStore getContactStore() throws MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(29178)) {
            if (this.contactStore == null) {
                if (!ListenerUtil.mutListener.listen(29177)) {
                    this.contactStore = new ContactStore(this.getAPIConnector(), this.getPreferenceService(), this.databaseServiceNew, this.getBlackListService(), this.getExcludedSyncIdentitiesService());
                }
            }
        }
        return this.contactStore;
    }

    public APIConnector getAPIConnector() {
        if (!ListenerUtil.mutListener.listen(29183)) {
            if (this.apiConnector == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(29180)) {
                        this.apiConnector = new APIConnector(ThreemaApplication.getIPv6(), null, ConfigUtils.isWorkBuild(), BuildFlavor.isSandbox(), ConfigUtils::getSSLSocketFactory);
                    }
                    if (!ListenerUtil.mutListener.listen(29181)) {
                        this.apiConnector.setVersion(this.getConnection().getVersion());
                    }
                    if (!ListenerUtil.mutListener.listen(29182)) {
                        this.apiConnector.setLanguage(Locale.getDefault().getLanguage());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(29179)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return this.apiConnector;
    }

    /**
     *  Start the server connection. Do not call this directly; use the LifetimeService!
     *
     *  @throws NoIdentityException
     *  @throws MasterKeyLockedException
     */
    public void startConnection() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29184)) {
            logger.trace("startConnection");
        }
        String currentIdentity = this.identityStore.getIdentity();
        if (!ListenerUtil.mutListener.listen(29191)) {
            if ((ListenerUtil.mutListener.listen(29190) ? (currentIdentity == null && (ListenerUtil.mutListener.listen(29189) ? (currentIdentity.length() >= 0) : (ListenerUtil.mutListener.listen(29188) ? (currentIdentity.length() <= 0) : (ListenerUtil.mutListener.listen(29187) ? (currentIdentity.length() > 0) : (ListenerUtil.mutListener.listen(29186) ? (currentIdentity.length() < 0) : (ListenerUtil.mutListener.listen(29185) ? (currentIdentity.length() != 0) : (currentIdentity.length() == 0))))))) : (currentIdentity == null || (ListenerUtil.mutListener.listen(29189) ? (currentIdentity.length() >= 0) : (ListenerUtil.mutListener.listen(29188) ? (currentIdentity.length() <= 0) : (ListenerUtil.mutListener.listen(29187) ? (currentIdentity.length() > 0) : (ListenerUtil.mutListener.listen(29186) ? (currentIdentity.length() < 0) : (ListenerUtil.mutListener.listen(29185) ? (currentIdentity.length() != 0) : (currentIdentity.length() == 0))))))))) {
                throw new NoIdentityException();
            }
        }
        if (!ListenerUtil.mutListener.listen(29192)) {
            if (this.masterKey.isLocked()) {
                throw new MasterKeyLockedException("master key is locked");
            }
        }
        if (!ListenerUtil.mutListener.listen(29195)) {
            // add a message processor
            if (this.connection.getMessageProcessor() == null) {
                if (!ListenerUtil.mutListener.listen(29193)) {
                    logger.debug("add message processor to connection");
                }
                if (!ListenerUtil.mutListener.listen(29194)) {
                    this.connection.setMessageProcessor(this.getMessageProcessor());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29196)) {
            // add message ACK processor
            getMessageAckProcessor().setMessageService(this.getMessageService());
        }
        if (!ListenerUtil.mutListener.listen(29197)) {
            connection.addMessageAckListener(getMessageAckProcessor());
        }
        if (!ListenerUtil.mutListener.listen(29198)) {
            logger.info("Starting connection");
        }
        if (!ListenerUtil.mutListener.listen(29199)) {
            this.connection.start();
        }
    }

    public PreferenceStoreInterface getPreferenceStore() {
        return preferenceStore;
    }

    public MessageProcessor getMessageProcessor() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29201)) {
            if (this.messageProcessor == null) {
                if (!ListenerUtil.mutListener.listen(29200)) {
                    this.messageProcessor = new MessageProcessor(this.getMessageService(), this.getContactService(), this.getIdentityStore(), this.getContactStore(), this.getPreferenceService(), this.getGroupService(), this.getBlackListService(), this.getBallotService(), this.getFileService(), this.getNotificationService(), this.getVoipStateService());
                }
            }
        }
        return this.messageProcessor;
    }

    /**
     *  Stop the connection. Do not call this directly; use the LifetimeService!
     */
    public void stopConnection() throws InterruptedException {
        if (!ListenerUtil.mutListener.listen(29202)) {
            logger.info("Stopping connection");
        }
        InterruptedException interrupted = null;
        try {
            if (!ListenerUtil.mutListener.listen(29205)) {
                this.connection.stop();
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(29203)) {
                logger.error("Interrupted while stopping connection");
            }
            if (!ListenerUtil.mutListener.listen(29204)) {
                interrupted = e;
            }
        }
        // Write message queue to file at this opportunity (we can never know when we'll get killed)
        try {
            if (!ListenerUtil.mutListener.listen(29207)) {
                this.getMessageService().saveMessageQueueAsync();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(29206)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(29209)) {
            // Re-set interrupted flag
            if (interrupted != null) {
                if (!ListenerUtil.mutListener.listen(29208)) {
                    Thread.currentThread().interrupt();
                }
                throw interrupted;
            }
        }
    }

    public MessageQueue getMessageQueue() throws MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(29211)) {
            if (this.messageQueue == null) {
                if (!ListenerUtil.mutListener.listen(29210)) {
                    this.messageQueue = new MessageQueue(this.getContactStore(), this.getIdentityStore(), this.getConnection());
                }
            }
        }
        return this.messageQueue;
    }

    public MessageAckProcessor getMessageAckProcessor() {
        if (!ListenerUtil.mutListener.listen(29213)) {
            if (this.messageAckProcessor == null) {
                if (!ListenerUtil.mutListener.listen(29212)) {
                    this.messageAckProcessor = new MessageAckProcessor();
                }
            }
        }
        return this.messageAckProcessor;
    }

    public UserService getUserService() {
        if (!ListenerUtil.mutListener.listen(29216)) {
            if (this.userService == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(29215)) {
                        this.userService = new UserServiceImpl(this.getContext(), this.preferenceStore, this.getLocaleService(), this.getAPIConnector(), this.getIdentityStore(), this.getMessageQueue(), this.getPreferenceService());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(29214)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return this.userService;
    }

    public ContactService getContactService() throws MasterKeyLockedException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29219)) {
            if (this.contactService == null) {
                if (!ListenerUtil.mutListener.listen(29217)) {
                    if (this.masterKey.isLocked()) {
                        throw new MasterKeyLockedException("master key is locked");
                    }
                }
                if (!ListenerUtil.mutListener.listen(29218)) {
                    this.contactService = new ContactServiceImpl(this.getContext(), this.getContactStore(), this.getAvatarCacheService(), this.databaseServiceNew, this.getDeviceService(), this.getUserService(), this.getMessageQueue(), this.getIdentityStore(), this.getPreferenceService(), this.getBlackListService(), this.getProfilePicRecipientsService(), this.getRingtoneService(), this.getMutedChatsListService(), this.getHiddenChatsListService(), this.getFileService(), this.cacheService, this.getApiService(), this.getWallpaperService(), this.getLicenseService(), this.getExcludedSyncIdentitiesService(), this.getAPIConnector());
                }
            }
        }
        return this.contactService;
    }

    public MessageService getMessageService() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29221)) {
            if (this.messageService == null) {
                if (!ListenerUtil.mutListener.listen(29220)) {
                    this.messageService = new MessageServiceImpl(this.getContext(), this.cacheService, this.getMessageQueue(), this.databaseServiceNew, this.getContactService(), this.getFileService(), this.getIdentityStore(), this.getPreferenceService(), this.getMessageAckProcessor(), this.getLockAppService(), this.getBallotService(), this.getGroupService(), this.getApiService(), this.getDownloadService(), this.getHiddenChatsListService(), this.getProfilePicRecipientsService(), this.getBlackListService());
                }
            }
        }
        return this.messageService;
    }

    public PreferenceService getPreferenceService() {
        if (!ListenerUtil.mutListener.listen(29223)) {
            if (this.preferencesService == null) {
                if (!ListenerUtil.mutListener.listen(29222)) {
                    this.preferencesService = new PreferenceServiceImpl(this.getContext(), this.preferenceStore);
                }
            }
        }
        return this.preferencesService;
    }

    public QRCodeService getQRCodeService() {
        if (!ListenerUtil.mutListener.listen(29225)) {
            if (this.qrCodeService == null) {
                if (!ListenerUtil.mutListener.listen(29224)) {
                    this.qrCodeService = new QRCodeServiceImpl(this.getUserService());
                }
            }
        }
        return this.qrCodeService;
    }

    public FingerPrintService getFingerPrintService() throws MasterKeyLockedException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29227)) {
            if (this.fingerPrintService == null) {
                if (!ListenerUtil.mutListener.listen(29226)) {
                    this.fingerPrintService = new FingerPrintServiceImpl(this.getContactService(), this.getIdentityStore());
                }
            }
        }
        return this.fingerPrintService;
    }

    public FileService getFileService() throws FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29229)) {
            if (this.fileService == null) {
                if (!ListenerUtil.mutListener.listen(29228)) {
                    this.fileService = new FileServiceImpl(this.getContext(), this.masterKey, this.getPreferenceService());
                }
            }
        }
        return this.fileService;
    }

    public LocaleService getLocaleService() {
        if (!ListenerUtil.mutListener.listen(29231)) {
            if (this.localeService == null) {
                if (!ListenerUtil.mutListener.listen(29230)) {
                    this.localeService = new LocaleServiceImpl(this.getContext());
                }
            }
        }
        return this.localeService;
    }

    public ThreemaConnection getConnection() {
        return this.connection;
    }

    public DeviceService getDeviceService() {
        if (!ListenerUtil.mutListener.listen(29233)) {
            if (this.deviceService == null) {
                if (!ListenerUtil.mutListener.listen(29232)) {
                    this.deviceService = new DeviceServiceImpl(this.getContext());
                }
            }
        }
        return this.deviceService;
    }

    public LifetimeService getLifetimeService() {
        if (!ListenerUtil.mutListener.listen(29237)) {
            if (this.lifetimeService == null) {
                if (!ListenerUtil.mutListener.listen(29234)) {
                    this.lifetimeService = new LifetimeServiceImpl(this.getContext());
                }
                if (!ListenerUtil.mutListener.listen(29236)) {
                    if (this.getPreferenceService().isPolling()) {
                        long interval = this.getPreferenceService().getPollingInterval();
                        if (!ListenerUtil.mutListener.listen(29235)) {
                            this.lifetimeService.setPollingInterval(interval);
                        }
                    }
                }
            }
        }
        return this.lifetimeService;
    }

    public AvatarCacheService getAvatarCacheService() throws FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29239)) {
            if (this.avatarCacheService == null) {
                if (!ListenerUtil.mutListener.listen(29238)) {
                    this.avatarCacheService = new AvatarCacheServiceImpl(this.getContext(), this.getIdentityStore(), this.getPreferenceService(), this.getFileService());
                }
            }
        }
        return this.avatarCacheService;
    }

    /**
     *  service to backup or restore data (conversations and contacts)
     *  @return
     */
    public BackupRestoreDataService getBackupRestoreDataService() throws FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29241)) {
            if (this.backupRestoreDataService == null) {
                if (!ListenerUtil.mutListener.listen(29240)) {
                    this.backupRestoreDataService = new BackupRestoreDataServiceImpl(this.getContext(), this.getFileService());
                }
            }
        }
        return this.backupRestoreDataService;
    }

    public LicenseService getLicenseService() throws FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29246)) {
            if (this.licenseService == null) {
                if (!ListenerUtil.mutListener.listen(29245)) {
                    switch(BuildFlavor.getLicenseType()) {
                        case SERIAL:
                            if (!ListenerUtil.mutListener.listen(29242)) {
                                this.licenseService = new LicenseServiceSerial(this.getAPIConnector(), this.getPreferenceService(), DeviceIdUtil.getDeviceId(getContext()));
                            }
                            break;
                        case GOOGLE_WORK:
                        case HMS_WORK:
                            if (!ListenerUtil.mutListener.listen(29243)) {
                                this.licenseService = new LicenseServiceUser(this.getAPIConnector(), this.getPreferenceService(), DeviceIdUtil.getDeviceId(getContext()));
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(29244)) {
                                this.licenseService = new LicenseService() {

                                    @Override
                                    public String validate(Credentials credentials) {
                                        return null;
                                    }

                                    @Override
                                    public String validate(Credentials credentials, boolean allowException) {
                                        return null;
                                    }

                                    @Override
                                    public String validate(boolean allowException) {
                                        return null;
                                    }

                                    @Override
                                    public boolean hasCredentials() {
                                        return false;
                                    }

                                    @Override
                                    public boolean isLicensed() {
                                        return true;
                                    }

                                    @Override
                                    public Credentials loadCredentials() {
                                        return null;
                                    }
                                };
                            }
                    }
                }
            }
        }
        return this.licenseService;
    }

    public LockAppService getLockAppService() {
        if (!ListenerUtil.mutListener.listen(29248)) {
            if (null == this.lockAppService) {
                if (!ListenerUtil.mutListener.listen(29247)) {
                    this.lockAppService = new PinLockService(this.getContext(), this.getPreferenceService(), this.getUserService());
                }
            }
        }
        return this.lockAppService;
    }

    public ActivityService getActivityService() {
        if (!ListenerUtil.mutListener.listen(29250)) {
            if (null == this.activityService) {
                if (!ListenerUtil.mutListener.listen(29249)) {
                    this.activityService = new ActivityService(this.getContext(), this.getLockAppService(), this.getPreferenceService());
                }
            }
        }
        return this.activityService;
    }

    public GroupService getGroupService() throws MasterKeyLockedException, NoIdentityException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29252)) {
            if (null == this.groupService) {
                if (!ListenerUtil.mutListener.listen(29251)) {
                    this.groupService = new GroupServiceImpl(this.cacheService, this.getApiService(), this.getGroupApiService(), this.getUserService(), this.getContactService(), this.databaseServiceNew, this.getAvatarCacheService(), this.getFileService(), this.getPreferenceService(), this.getWallpaperService(), this.getMutedChatsListService(), this.getHiddenChatsListService(), this.getRingtoneService(), this.getBlackListService());
                }
            }
        }
        return this.groupService;
    }

    public GroupApiService getGroupApiService() throws MasterKeyLockedException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29254)) {
            if (null == this.groupApiService) {
                if (!ListenerUtil.mutListener.listen(29253)) {
                    this.groupApiService = new GroupApiServiceImpl(this.getUserService(), this.getContactService(), this.getMessageQueue());
                }
            }
        }
        return this.groupApiService;
    }

    public ApiService getApiService() {
        if (!ListenerUtil.mutListener.listen(29256)) {
            if (null == this.apiService) {
                if (!ListenerUtil.mutListener.listen(29255)) {
                    this.apiService = new ApiServiceImpl(ThreemaApplication.getAppVersion(), ThreemaApplication.getIPv6());
                }
            }
        }
        return this.apiService;
    }

    public DistributionListService getDistributionListService() throws MasterKeyLockedException, NoIdentityException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29258)) {
            if (null == this.distributionListService) {
                if (!ListenerUtil.mutListener.listen(29257)) {
                    this.distributionListService = new DistributionListServiceImpl(this.cacheService, this.getAvatarCacheService(), this.databaseServiceNew, this.getContactService());
                }
            }
        }
        return this.distributionListService;
    }

    public ConversationTagService getConversationTagService() {
        if (!ListenerUtil.mutListener.listen(29260)) {
            if (null == this.conversationService) {
                if (!ListenerUtil.mutListener.listen(29259)) {
                    this.conversationTagService = new ConversationTagServiceImpl(this.databaseServiceNew);
                }
            }
        }
        return this.conversationTagService;
    }

    public ConversationService getConversationService() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29262)) {
            if (null == this.conversationService) {
                if (!ListenerUtil.mutListener.listen(29261)) {
                    this.conversationService = new ConversationServiceImpl(this.cacheService, this.databaseServiceNew, this.getContactService(), this.getGroupService(), this.getDistributionListService(), this.getMessageService(), this.getHiddenChatsListService(), this.getConversationTagService());
                }
            }
        }
        return this.conversationService;
    }

    public NotificationService getNotificationService() {
        if (!ListenerUtil.mutListener.listen(29264)) {
            if (this.notificationService == null) {
                if (!ListenerUtil.mutListener.listen(29263)) {
                    this.notificationService = new NotificationServiceImpl(this.getContext(), this.getLockAppService(), this.getHiddenChatsListService(), this.getPreferenceService(), this.getRingtoneService());
                }
            }
        }
        return this.notificationService;
    }

    public SynchronizeContactsService getSynchronizeContactsService() throws MasterKeyLockedException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29266)) {
            if (this.synchronizeContactsService == null) {
                if (!ListenerUtil.mutListener.listen(29265)) {
                    this.synchronizeContactsService = new SynchronizeContactsServiceImpl(this.getContext(), this.getAPIConnector(), this.getContactService(), this.getUserService(), this.getLocaleService(), this.getExcludedSyncIdentitiesService(), this.getPreferenceService(), this.getDeviceService(), this.getFileService(), this.getIdentityStore(), this.getBlackListService(), this.getLicenseService());
                }
            }
        }
        return this.synchronizeContactsService;
    }

    public IdListService getBlackListService() {
        if (!ListenerUtil.mutListener.listen(29268)) {
            if (this.blackListService == null) {
                if (!ListenerUtil.mutListener.listen(29267)) {
                    this.blackListService = new IdListServiceImpl("identity_list_blacklist", this.getPreferenceService());
                }
            }
        }
        return this.blackListService;
    }

    public DeadlineListService getMutedChatsListService() {
        if (!ListenerUtil.mutListener.listen(29270)) {
            if (this.mutedChatsListService == null) {
                if (!ListenerUtil.mutListener.listen(29269)) {
                    this.mutedChatsListService = new DeadlineListServiceImpl("list_muted_chats", this.getPreferenceService());
                }
            }
        }
        return this.mutedChatsListService;
    }

    public DeadlineListService getHiddenChatsListService() {
        if (!ListenerUtil.mutListener.listen(29272)) {
            if (this.hiddenChatListService == null) {
                if (!ListenerUtil.mutListener.listen(29271)) {
                    this.hiddenChatListService = new DeadlineListServiceImpl("list_hidden_chats", this.getPreferenceService());
                }
            }
        }
        return this.hiddenChatListService;
    }

    public DeadlineListService getMentionOnlyChatsListService() {
        if (!ListenerUtil.mutListener.listen(29274)) {
            if (this.mentionOnlyChatsListService == null) {
                if (!ListenerUtil.mutListener.listen(29273)) {
                    this.mentionOnlyChatsListService = new DeadlineListServiceImpl("list_mention_only", this.getPreferenceService());
                }
            }
        }
        return this.mentionOnlyChatsListService;
    }

    public IdListService getExcludedSyncIdentitiesService() {
        if (!ListenerUtil.mutListener.listen(29276)) {
            if (this.excludedSyncIdentitiesService == null) {
                if (!ListenerUtil.mutListener.listen(29275)) {
                    this.excludedSyncIdentitiesService = new IdListServiceImpl("identity_list_sync_excluded", this.getPreferenceService());
                }
            }
        }
        return this.excludedSyncIdentitiesService;
    }

    public UpdateSystemService getUpdateSystemService() {
        return this.updateSystemService;
    }

    public MessagePlayerService getMessagePlayerService() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29278)) {
            if (this.messagePlayerService == null) {
                if (!ListenerUtil.mutListener.listen(29277)) {
                    this.messagePlayerService = new MessagePlayerServiceImpl(getContext(), this.getMessageService(), this.getFileService(), this.getPreferenceService());
                }
            }
        }
        return this.messagePlayerService;
    }

    public DownloadService getDownloadService() throws FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29280)) {
            if (this.downloadService == null) {
                if (!ListenerUtil.mutListener.listen(29279)) {
                    this.downloadService = new DownloadServiceImpl(this.getContext(), this.getFileService(), this.getApiService());
                }
            }
        }
        return this.downloadService;
    }

    public BallotService getBallotService() throws NoIdentityException, MasterKeyLockedException, FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29282)) {
            if (this.ballotService == null) {
                if (!ListenerUtil.mutListener.listen(29281)) {
                    this.ballotService = new BallotServiceImpl(this.cacheService.getBallotModelCache(), this.cacheService.getLinkBallotModelCache(), this.databaseServiceNew, this.getUserService(), this.getGroupService(), this.getContactService(), this);
                }
            }
        }
        return this.ballotService;
    }

    public WallpaperService getWallpaperService() throws FileSystemNotPresentException {
        if (!ListenerUtil.mutListener.listen(29284)) {
            if (this.wallpaperService == null) {
                if (!ListenerUtil.mutListener.listen(29283)) {
                    this.wallpaperService = new WallpaperServiceImpl(this.getContext(), this.getFileService(), this.getPreferenceService(), this.masterKey);
                }
            }
        }
        return this.wallpaperService;
    }

    public ThreemaSafeService getThreemaSafeService() throws FileSystemNotPresentException, MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(29286)) {
            if (this.threemaSafeService == null) {
                if (!ListenerUtil.mutListener.listen(29285)) {
                    this.threemaSafeService = new ThreemaSafeServiceImpl(this.getContext(), this.getPreferenceService(), this.getUserService(), this.getContactService(), this.getLocaleService(), this.getFileService(), this.getProfilePicRecipientsService(), this.getDatabaseServiceNew(), this.getIdentityStore(), this.getAPIConnector(), this.getHiddenChatsListService());
                }
            }
        }
        return this.threemaSafeService;
    }

    public Context getContext() {
        return ThreemaApplication.getAppContext();
    }

    public IdentityStore getIdentityStore() {
        return this.identityStore;
    }

    public RingtoneService getRingtoneService() {
        if (!ListenerUtil.mutListener.listen(29288)) {
            if (this.ringtoneService == null) {
                if (!ListenerUtil.mutListener.listen(29287)) {
                    this.ringtoneService = new RingtoneServiceImpl(this.getPreferenceService());
                }
            }
        }
        return this.ringtoneService;
    }

    public BackupChatService getBackupChatService() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29290)) {
            if (this.backupChatService == null) {
                if (!ListenerUtil.mutListener.listen(29289)) {
                    this.backupChatService = new BackupChatServiceImpl(this.getContext(), this.getFileService(), this.getMessageService(), this.getContactService());
                }
            }
        }
        return this.backupChatService;
    }

    public SystemScreenLockService getScreenLockService() {
        if (!ListenerUtil.mutListener.listen(29292)) {
            if (this.systemScreenLockService == null) {
                if (!ListenerUtil.mutListener.listen(29291)) {
                    this.systemScreenLockService = new SystemScreenLockServiceImpl(this.getContext(), this.getLockAppService(), this.getPreferenceService());
                }
            }
        }
        return this.systemScreenLockService;
    }

    public ShortcutService getShortcutService() throws FileSystemNotPresentException, MasterKeyLockedException, NoIdentityException {
        if (!ListenerUtil.mutListener.listen(29294)) {
            if (this.shortcutService == null) {
                if (!ListenerUtil.mutListener.listen(29293)) {
                    this.shortcutService = new ShortcutServiceImpl(this.getContext(), this.getContactService(), this.getGroupService(), this.getDistributionListService());
                }
            }
        }
        return this.shortcutService;
    }

    public SensorService getSensorService() {
        if (!ListenerUtil.mutListener.listen(29296)) {
            if (this.sensorService == null) {
                if (!ListenerUtil.mutListener.listen(29295)) {
                    this.sensorService = new SensorServiceImpl(this.getContext());
                }
            }
        }
        return this.sensorService;
    }

    @NonNull
    public WebClientServiceManager getWebClientServiceManager() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29298)) {
            if (this.webClientServiceManager == null) {
                if (!ListenerUtil.mutListener.listen(29297)) {
                    this.webClientServiceManager = new WebClientServiceManager(new ServicesContainer(this.getContext().getApplicationContext(), this.getLifetimeService(), this.getContactService(), this.getGroupService(), this.getDistributionListService(), this.getConversationService(), this.getConversationTagService(), this.getMessageService(), this.getNotificationService(), this.databaseServiceNew, this.getBlackListService(), this.preferencesService, this.getUserService(), this.getHiddenChatsListService(), this.getFileService(), this.getSynchronizeContactsService(), this.getLicenseService(), this.getMessageQueue()));
                }
            }
        }
        return this.webClientServiceManager;
    }

    @NonNull
    public BrowserDetectionService getBrowserDetectionService() {
        if (!ListenerUtil.mutListener.listen(29300)) {
            if (this.browserDetectionService == null) {
                if (!ListenerUtil.mutListener.listen(29299)) {
                    this.browserDetectionService = new BrowserDetectionServiceImpl();
                }
            }
        }
        return this.browserDetectionService;
    }

    @NonNull
    public IdListService getProfilePicRecipientsService() {
        if (!ListenerUtil.mutListener.listen(29302)) {
            if (this.profilePicRecipientsService == null) {
                if (!ListenerUtil.mutListener.listen(29301)) {
                    this.profilePicRecipientsService = new IdListServiceImpl("identity_list_profilepics", this.getPreferenceService());
                }
            }
        }
        return this.profilePicRecipientsService;
    }

    @NonNull
    public VoipStateService getVoipStateService() throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(29304)) {
            if (this.voipStateService == null) {
                if (!ListenerUtil.mutListener.listen(29303)) {
                    this.voipStateService = new VoipStateService(this.getContactService(), this.getRingtoneService(), this.getPreferenceService(), this.getMessageService(), this.getMessageQueue(), this.getLifetimeService(), this.getContext());
                }
            }
        }
        return this.voipStateService;
    }

    public DatabaseServiceNew getDatabaseServiceNew() {
        return this.databaseServiceNew;
    }
}
