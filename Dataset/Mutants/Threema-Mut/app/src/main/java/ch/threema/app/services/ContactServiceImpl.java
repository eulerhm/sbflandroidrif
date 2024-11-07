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

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.neilalexander.jnacl.NaCl;
import net.sqlcipher.Cursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.app.listeners.ContactTypingListener;
import ch.threema.app.listeners.ProfileListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.routines.UpdateBusinessAvatarRoutine;
import ch.threema.app.routines.UpdateFeatureLevelRoutine;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.UserCredentials;
import ch.threema.app.stores.ContactStore;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.AvatarConverterUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ColorUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.base.VerificationLevel;
import ch.threema.client.APIConnector;
import ch.threema.client.AbstractMessage;
import ch.threema.client.Base32;
import ch.threema.client.BlobLoader;
import ch.threema.client.BlobUploader;
import ch.threema.client.ContactDeletePhotoMessage;
import ch.threema.client.ContactRequestPhotoMessage;
import ch.threema.client.ContactSetPhotoMessage;
import ch.threema.client.IdentityType;
import ch.threema.client.MessageQueue;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.work.WorkContact;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.factories.ContactModelFactory;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ValidationMessage;
import ch.threema.storage.models.access.AccessModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    private static final int TYPING_RECEIVE_TIMEOUT = (int) DateUtils.MINUTE_IN_MILLIS;

    private final Context context;

    private final AvatarCacheService avatarCacheService;

    private final ContactStore contactStore;

    private final DatabaseServiceNew databaseServiceNew;

    private final DeviceService deviceService;

    private final UserService userService;

    private final MessageQueue messageQueue;

    private final IdentityStore identityStore;

    private final PreferenceService preferenceService;

    private final IdListService excludeFromSyncListService;

    private final Map<String, ContactModel> contactModelCache;

    private final IdListService blackListIdentityService, profilePicRecipientsService;

    private DeadlineListService mutedChatsListService, hiddenChatsListService;

    private RingtoneService ringtoneService;

    private final FileService fileService;

    private final ApiService apiService;

    private final WallpaperService wallpaperService;

    private final LicenseService licenseService;

    private APIConnector apiConnector;

    private final Timer typingTimer;

    private final Map<String, TimerTask> typingTimerTasks;

    private final VectorDrawableCompat contactDefaultAvatar;

    private final int avatarSizeSmall;

    private final List<String> typingIdentities = new ArrayList<>();

    private ContactModel me;

    // These are public keys of identities that will be immediately trusted (three green dots)
    private static final byte[][] TRUSTED_PUBLIC_KEYS = { new byte[] { // *THREEMA
    58, 56, 101, 12, 104, 20, 53, -67, 31, -72, 73, -114, 33, 58, 41, 25, -80, -109, -120, -11, -128, 58, -92, 70, 64, -32, -9, 6, 50, 106, -122, 92 }, new byte[] { // *SUPPORT
    15, -108, 77, 24, 50, 75, 33, 50, -58, 29, -114, 64, -81, -50, 96, -96, -21, -41, 1, -69, 17, -24, -101, -23, 73, 114, -44, 34, -98, -108, 114, 42 }, new byte[] { // *MY3DATA
    59, 1, -123, 79, 36, 115, 110, 45, 13, 45, -61, -121, -22, -14, -64, 39, 60, 80, 73, 5, 33, 71, 19, 35, 105, -65, 57, 96, -48, -96, -65, 2 } };

    class ContactPhotoUploadResult {

        public byte[] bitmapArray;

        public byte[] blobId;

        public byte[] encryptionKey;

        public int size;
    }

    public ContactServiceImpl(Context context, ContactStore contactStore, AvatarCacheService avatarCacheService, DatabaseServiceNew databaseServiceNew, DeviceService deviceService, UserService userService, MessageQueue messageQueue, IdentityStore identityStore, PreferenceService preferenceService, IdListService blackListIdentityService, IdListService profilePicRecipientsService, RingtoneService ringtoneService, DeadlineListService mutedChatsListService, DeadlineListService hiddenChatsListService, FileService fileService, CacheService cacheService, ApiService apiService, WallpaperService wallpaperService, LicenseService licenseService, IdListService excludeFromSyncListService, APIConnector apiConnector) {
        this.context = context;
        this.avatarCacheService = avatarCacheService;
        this.contactStore = contactStore;
        this.databaseServiceNew = databaseServiceNew;
        this.deviceService = deviceService;
        this.userService = userService;
        this.messageQueue = messageQueue;
        this.identityStore = identityStore;
        this.preferenceService = preferenceService;
        this.blackListIdentityService = blackListIdentityService;
        this.profilePicRecipientsService = profilePicRecipientsService;
        if (!ListenerUtil.mutListener.listen(36841)) {
            this.ringtoneService = ringtoneService;
        }
        if (!ListenerUtil.mutListener.listen(36842)) {
            this.mutedChatsListService = mutedChatsListService;
        }
        if (!ListenerUtil.mutListener.listen(36843)) {
            this.hiddenChatsListService = hiddenChatsListService;
        }
        this.fileService = fileService;
        this.apiService = apiService;
        this.wallpaperService = wallpaperService;
        this.licenseService = licenseService;
        this.excludeFromSyncListService = excludeFromSyncListService;
        if (!ListenerUtil.mutListener.listen(36844)) {
            this.apiConnector = apiConnector;
        }
        this.typingTimer = new Timer();
        this.typingTimerTasks = new HashMap<>();
        this.contactModelCache = cacheService.getContactModelCache();
        this.contactDefaultAvatar = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_contact, null);
        this.avatarSizeSmall = context.getResources().getDimensionPixelSize(R.dimen.avatar_size_small);
    }

    @Override
    public ContactModel getMe() {
        if (!ListenerUtil.mutListener.listen(36851)) {
            if ((ListenerUtil.mutListener.listen(36845) ? (this.me == null || this.userService.getIdentity() != null) : (this.me == null && this.userService.getIdentity() != null))) {
                if (!ListenerUtil.mutListener.listen(36846)) {
                    this.me = new ContactModel(this.userService.getIdentity(), this.userService.getPublicKey());
                }
                if (!ListenerUtil.mutListener.listen(36847)) {
                    this.me.setState(ContactModel.State.ACTIVE);
                }
                if (!ListenerUtil.mutListener.listen(36848)) {
                    this.me.setFirstName(context.getString(R.string.me_myself_and_i));
                }
                if (!ListenerUtil.mutListener.listen(36849)) {
                    this.me.setVerificationLevel(VerificationLevel.FULLY_VERIFIED);
                }
                if (!ListenerUtil.mutListener.listen(36850)) {
                    this.me.setFeatureMask(-1);
                }
            }
        }
        return this.me;
    }

    @Override
    public List<ContactModel> getAll() {
        return getAll(false, true);
    }

    @Override
    public List<ContactModel> getAll(final boolean includeHiddenContacts, final boolean includeInvalid) {
        return this.find(new Filter() {

            @Override
            public ContactModel.State[] states() {
                if (preferenceService.showInactiveContacts()) {
                    if (includeInvalid) {
                        return null;
                    } else {
                        // do not show contacts with INVALID state
                        return new ContactModel.State[] { ContactModel.State.ACTIVE, ContactModel.State.INACTIVE };
                    }
                } else {
                    return new ContactModel.State[] { ContactModel.State.ACTIVE };
                }
            }

            @Override
            public Integer requiredFeature() {
                return null;
            }

            @Override
            public Boolean fetchMissingFeatureLevel() {
                return null;
            }

            @Override
            public Boolean includeMyself() {
                return false;
            }

            @Override
            public Boolean includeHidden() {
                return includeHiddenContacts;
            }
        });
    }

    @Override
    public List<ContactModel> find(Filter filter) {
        ContactModelFactory contactModelFactory = this.databaseServiceNew.getContactModelFactory();
        // TODO: move this to database factory!
        QueryBuilder queryBuilder = new QueryBuilder();
        List<String> placeholders = new ArrayList<>();
        List<ContactModel> result;
        if (filter != null) {
            ContactModel.State[] filterStates = filter.states();
            if (!ListenerUtil.mutListener.listen(36861)) {
                if ((ListenerUtil.mutListener.listen(36857) ? (filterStates != null || (ListenerUtil.mutListener.listen(36856) ? (filterStates.length >= 0) : (ListenerUtil.mutListener.listen(36855) ? (filterStates.length <= 0) : (ListenerUtil.mutListener.listen(36854) ? (filterStates.length < 0) : (ListenerUtil.mutListener.listen(36853) ? (filterStates.length != 0) : (ListenerUtil.mutListener.listen(36852) ? (filterStates.length == 0) : (filterStates.length > 0))))))) : (filterStates != null && (ListenerUtil.mutListener.listen(36856) ? (filterStates.length >= 0) : (ListenerUtil.mutListener.listen(36855) ? (filterStates.length <= 0) : (ListenerUtil.mutListener.listen(36854) ? (filterStates.length < 0) : (ListenerUtil.mutListener.listen(36853) ? (filterStates.length != 0) : (ListenerUtil.mutListener.listen(36852) ? (filterStates.length == 0) : (filterStates.length > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(36858)) {
                        // dirty, add placeholder should be added to makePlaceholders
                        queryBuilder.appendWhere(ContactModel.COLUMN_STATE + " IN (" + DatabaseUtil.makePlaceholders(filterStates.length) + ")");
                    }
                    if (!ListenerUtil.mutListener.listen(36860)) {
                        {
                            long _loopCounter362 = 0;
                            for (ContactModel.State s : filterStates) {
                                ListenerUtil.loopListener.listen("_loopCounter362", ++_loopCounter362);
                                if (!ListenerUtil.mutListener.listen(36859)) {
                                    placeholders.add(s.toString());
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(36863)) {
                if (!filter.includeHidden()) {
                    if (!ListenerUtil.mutListener.listen(36862)) {
                        queryBuilder.appendWhere(ContactModel.COLUMN_IS_HIDDEN + "=0");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(36867)) {
                if ((ListenerUtil.mutListener.listen(36864) ? (!filter.includeMyself() || getMe() != null) : (!filter.includeMyself() && getMe() != null))) {
                    if (!ListenerUtil.mutListener.listen(36865)) {
                        queryBuilder.appendWhere(ContactModel.COLUMN_IDENTITY + "!=?");
                    }
                    if (!ListenerUtil.mutListener.listen(36866)) {
                        placeholders.add(getMe().getIdentity());
                    }
                }
            }
            result = contactModelFactory.convert(queryBuilder, placeholders.toArray(new String[placeholders.size()]), null);
        } else {
            result = contactModelFactory.convert(queryBuilder, placeholders.toArray(new String[placeholders.size()]), null);
        }
        // sort
        final boolean sortOrderFirstName = preferenceService.isContactListSortingFirstName();
        final Collator collator = Collator.getInstance();
        if (!ListenerUtil.mutListener.listen(36868)) {
            collator.setStrength(Collator.PRIMARY);
        }
        if (!ListenerUtil.mutListener.listen(36869)) {
            Collections.sort(result, new Comparator<ContactModel>() {

                @Override
                public int compare(ContactModel contactModel1, ContactModel contactModel2) {
                    return collator.compare(getSortKey(contactModel1, sortOrderFirstName), getSortKey(contactModel2, sortOrderFirstName));
                }
            });
        }
        if (filter != null) {
            final Integer feature = filter.requiredFeature();
            // update feature level routine call
            if (feature != null) {
                if (!ListenerUtil.mutListener.listen(36871)) {
                    if (filter.fetchMissingFeatureLevel()) {
                        // do not filtering with sql
                        UpdateFeatureLevelRoutine routine = new UpdateFeatureLevelRoutine(this, this.apiConnector, Functional.filter(result, new IPredicateNonNull<ContactModel>() {

                            @Override
                            public boolean apply(@NonNull ContactModel contactModel) {
                                return !ThreemaFeature.hasFeature(contactModel.getFeatureMask(), feature);
                            }
                        }));
                        if (!ListenerUtil.mutListener.listen(36870)) {
                            routine.run();
                        }
                    }
                }
                // Now filter
                result = Functional.filter(result, new IPredicateNonNull<ContactModel>() {

                    @Override
                    public boolean apply(@NonNull ContactModel contactModel) {
                        return ThreemaFeature.hasFeature(contactModel.getFeatureMask(), feature);
                    }
                });
            }
        }
        if (!ListenerUtil.mutListener.listen(36879)) {
            {
                long _loopCounter363 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(36878) ? (n >= result.size()) : (ListenerUtil.mutListener.listen(36877) ? (n <= result.size()) : (ListenerUtil.mutListener.listen(36876) ? (n > result.size()) : (ListenerUtil.mutListener.listen(36875) ? (n != result.size()) : (ListenerUtil.mutListener.listen(36874) ? (n == result.size()) : (n < result.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter363", ++_loopCounter363);
                    synchronized (this.contactModelCache) {
                        String identity = result.get(n).getIdentity();
                        if (!ListenerUtil.mutListener.listen(36873)) {
                            if (this.contactModelCache.containsKey(identity)) {
                                if (!ListenerUtil.mutListener.listen(36872)) {
                                    // but do not cache the result
                                    result.set(n, this.contactModelCache.get(identity));
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private String getSortKey(ContactModel contactModel, boolean sortOrderFirstName) {
        String key = contactModel.getIdentity();
        if (!ListenerUtil.mutListener.listen(36888)) {
            if (sortOrderFirstName) {
                if (!ListenerUtil.mutListener.listen(36885)) {
                    if (!TextUtils.isEmpty(contactModel.getLastName())) {
                        if (!ListenerUtil.mutListener.listen(36884)) {
                            key = contactModel.getLastName() + " " + key;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36887)) {
                    if (!TextUtils.isEmpty(contactModel.getFirstName())) {
                        if (!ListenerUtil.mutListener.listen(36886)) {
                            key = contactModel.getFirstName() + " " + key;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(36881)) {
                    if (!TextUtils.isEmpty(contactModel.getFirstName())) {
                        if (!ListenerUtil.mutListener.listen(36880)) {
                            key = contactModel.getFirstName() + " " + key;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36883)) {
                    if (!TextUtils.isEmpty(contactModel.getLastName())) {
                        if (!ListenerUtil.mutListener.listen(36882)) {
                            key = contactModel.getLastName() + " " + key;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36890)) {
            if (contactModel.getIdentity().startsWith("*")) {
                if (!ListenerUtil.mutListener.listen(36889)) {
                    key = "\uFFFF" + key;
                }
            }
        }
        return key;
    }

    @Override
    @Nullable
    public ContactModel getByLookupKey(String lookupKey) {
        if (!ListenerUtil.mutListener.listen(36891)) {
            if (lookupKey == null) {
                return null;
            }
        }
        return this.contactStore.getContactModelForLookupKey(lookupKey);
    }

    @Override
    @Nullable
    public ContactModel getByIdentity(@Nullable String identity) {
        if (!ListenerUtil.mutListener.listen(36892)) {
            if (identity == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(36894)) {
            // return me object
            if ((ListenerUtil.mutListener.listen(36893) ? (this.getMe() != null || this.getMe().getIdentity().equals(identity)) : (this.getMe() != null && this.getMe().getIdentity().equals(identity)))) {
                return this.me;
            }
        }
        synchronized (this.contactModelCache) {
            if (!ListenerUtil.mutListener.listen(36895)) {
                if (this.contactModelCache.containsKey(identity)) {
                    return this.contactModelCache.get(identity);
                }
            }
        }
        return this.cache(this.contactStore.getContactModelForIdentity(identity));
    }

    /**
     *  If a contact for the specified identity exists, return the contactmodel.
     *  Otherwise, create a new contact and return the contactmodel.
     */
    @Override
    @NonNull
    public ContactModel getOrCreateByIdentity(@NonNull String identity, boolean force) throws EntryAlreadyExistsException, InvalidEntryException, PolicyViolationException {
        ContactModel contactModel = this.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(36897)) {
            if (contactModel == null) {
                if (!ListenerUtil.mutListener.listen(36896)) {
                    contactModel = this.createContactByIdentity(identity, force);
                }
            }
        }
        return contactModel;
    }

    private ContactModel cache(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(36899)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(36898)) {
                    this.contactModelCache.put(contactModel.getIdentity(), contactModel);
                }
            }
        }
        return contactModel;
    }

    @Override
    public List<ContactModel> getByIdentities(String[] identities) {
        List<ContactModel> models = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(36902)) {
            {
                long _loopCounter364 = 0;
                for (String s : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter364", ++_loopCounter364);
                    ContactModel model = this.getByIdentity(s);
                    if (!ListenerUtil.mutListener.listen(36901)) {
                        if (model != null) {
                            if (!ListenerUtil.mutListener.listen(36900)) {
                                models.add(model);
                            }
                        }
                    }
                }
            }
        }
        return models;
    }

    @Override
    public List<ContactModel> getByIdentities(List<String> identities) {
        List<ContactModel> models = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(36905)) {
            {
                long _loopCounter365 = 0;
                for (String s : identities) {
                    ListenerUtil.loopListener.listen("_loopCounter365", ++_loopCounter365);
                    ContactModel model = this.getByIdentity(s);
                    if (!ListenerUtil.mutListener.listen(36904)) {
                        if (model != null) {
                            if (!ListenerUtil.mutListener.listen(36903)) {
                                models.add(model);
                            }
                        }
                    }
                }
            }
        }
        return models;
    }

    @Override
    public List<ContactModel> getIsWork() {
        return Functional.filter(this.find(null), new IPredicateNonNull<ContactModel>() {

            @Override
            public boolean apply(@NonNull ContactModel type) {
                return type.isWork();
            }
        });
    }

    @Override
    public int countIsWork() {
        int count = 0;
        Cursor c = this.databaseServiceNew.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM contacts " + "WHERE " + ContactModel.COLUMN_IS_WORK + " = 1 " + "AND " + ContactModel.COLUMN_IS_HIDDEN + " = 0", null);
        if (!ListenerUtil.mutListener.listen(36909)) {
            if (c != null) {
                if (!ListenerUtil.mutListener.listen(36907)) {
                    if (c.moveToFirst()) {
                        if (!ListenerUtil.mutListener.listen(36906)) {
                            count = c.getInt(0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36908)) {
                    c.close();
                }
            }
        }
        return count;
    }

    @Override
    public List<ContactModel> getCanReceiveProfilePics() {
        return Functional.filter(this.find(new Filter() {

            @Override
            public ContactModel.State[] states() {
                if (!ListenerUtil.mutListener.listen(36910)) {
                    if (preferenceService.showInactiveContacts()) {
                        return null;
                    }
                }
                return new ContactModel.State[] { ContactModel.State.ACTIVE };
            }

            @Override
            public Integer requiredFeature() {
                return null;
            }

            @Override
            public Boolean fetchMissingFeatureLevel() {
                return null;
            }

            @Override
            public Boolean includeMyself() {
                return false;
            }

            @Override
            public Boolean includeHidden() {
                return false;
            }
        }), new IPredicateNonNull<ContactModel>() {

            @Override
            public boolean apply(@NonNull ContactModel type) {
                return ContactUtil.canReceiveProfilePics(type);
            }
        });
    }

    @Override
    @Nullable
    public List<String> getSynchronizedIdentities() {
        Cursor c = this.databaseServiceNew.getReadableDatabase().rawQuery("" + "SELECT identity FROM contacts " + "WHERE isSynchronized = ?", new String[] { "1" });
        if (!ListenerUtil.mutListener.listen(36914)) {
            if (c != null) {
                List<String> identities = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(36912)) {
                    {
                        long _loopCounter366 = 0;
                        while (c.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter366", ++_loopCounter366);
                            if (!ListenerUtil.mutListener.listen(36911)) {
                                identities.add(c.getString(0));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36913)) {
                    c.close();
                }
                return identities;
            }
        }
        return null;
    }

    @Override
    @Nullable
    public List<String> getIdentitiesByVerificationLevel(VerificationLevel verificationLevel) {
        Cursor c = this.databaseServiceNew.getReadableDatabase().rawQuery("" + "SELECT identity FROM contacts " + "WHERE verificationLevel = ?", new String[] { String.valueOf(verificationLevel.getCode()) });
        if (!ListenerUtil.mutListener.listen(36918)) {
            if (c != null) {
                List<String> identities = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(36916)) {
                    {
                        long _loopCounter367 = 0;
                        while (c.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter367", ++_loopCounter367);
                            if (!ListenerUtil.mutListener.listen(36915)) {
                                identities.add(c.getString(0));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36917)) {
                    c.close();
                }
                return identities;
            }
        }
        return null;
    }

    @Override
    @Nullable
    public ContactModel getByPublicKey(byte[] publicKey) {
        return this.contactStore.getContactModelForPublicKey(publicKey);
    }

    @Override
    public void setIsTyping(final String identity, final boolean isTyping) {
        // cancel old timer task
        synchronized (typingTimerTasks) {
            TimerTask oldTimerTask = typingTimerTasks.get(identity);
            if (!ListenerUtil.mutListener.listen(36921)) {
                if (oldTimerTask != null) {
                    if (!ListenerUtil.mutListener.listen(36919)) {
                        oldTimerTask.cancel();
                    }
                    if (!ListenerUtil.mutListener.listen(36920)) {
                        typingTimerTasks.remove(identity);
                    }
                }
            }
        }
        // get the cached model
        final ContactModel contact = this.getByIdentity(identity);
        synchronized (this.typingIdentities) {
            boolean contains = this.typingIdentities.contains(identity);
            if (!ListenerUtil.mutListener.listen(36926)) {
                if (isTyping) {
                    if (!ListenerUtil.mutListener.listen(36925)) {
                        if (!contains) {
                            if (!ListenerUtil.mutListener.listen(36924)) {
                                this.typingIdentities.add(identity);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(36923)) {
                        if (contains) {
                            if (!ListenerUtil.mutListener.listen(36922)) {
                                this.typingIdentities.remove(identity);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36928)) {
            ListenerManager.contactTypingListeners.handle(new ListenerManager.HandleListener<ContactTypingListener>() {

                @Override
                public void handle(ContactTypingListener listener) {
                    if (!ListenerUtil.mutListener.listen(36927)) {
                        listener.onContactIsTyping(contact, isTyping);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(36936)) {
            // schedule a new timer task to reset typing state after timeout if necessary
            if (isTyping) {
                synchronized (typingTimerTasks) {
                    TimerTask newTimerTask = new TimerTask() {

                        @Override
                        public void run() {
                            synchronized (typingIdentities) {
                                if (!ListenerUtil.mutListener.listen(36930)) {
                                    if (typingIdentities.contains(identity)) {
                                        if (!ListenerUtil.mutListener.listen(36929)) {
                                            typingIdentities.remove(identity);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(36932)) {
                                ListenerManager.contactTypingListeners.handle(new ListenerManager.HandleListener<ContactTypingListener>() {

                                    @Override
                                    public void handle(ContactTypingListener listener) {
                                        if (!ListenerUtil.mutListener.listen(36931)) {
                                            listener.onContactIsTyping(contact, false);
                                        }
                                    }
                                });
                            }
                            synchronized (typingTimerTasks) {
                                if (!ListenerUtil.mutListener.listen(36933)) {
                                    typingTimerTasks.remove(identity);
                                }
                            }
                        }
                    };
                    if (!ListenerUtil.mutListener.listen(36934)) {
                        typingTimerTasks.put(identity, newTimerTask);
                    }
                    if (!ListenerUtil.mutListener.listen(36935)) {
                        typingTimer.schedule(newTimerTask, TYPING_RECEIVE_TIMEOUT);
                    }
                }
            }
        }
    }

    @Override
    public boolean isTyping(String identity) {
        synchronized (this.typingIdentities) {
            return this.typingIdentities.contains(identity);
        }
    }

    @Override
    public void setActive(String identity) {
        final ContactModel contact = this.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(36939)) {
            if ((ListenerUtil.mutListener.listen(36937) ? (contact != null || contact.getState() == ContactModel.State.INACTIVE) : (contact != null && contact.getState() == ContactModel.State.INACTIVE))) {
                if (!ListenerUtil.mutListener.listen(36938)) {
                    contact.setState(ContactModel.State.ACTIVE);
                }
            }
        }
    }

    /**
     *  Change hidden status of contact
     *  @param identity
     *  @param hide true if we want to hide the contact, false to unhide
     */
    @Override
    public void setIsHidden(String identity, boolean hide) {
        final ContactModel contact = this.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(36943)) {
            if ((ListenerUtil.mutListener.listen(36940) ? (contact != null || contact.isHidden() != hide) : (contact != null && contact.isHidden() != hide))) {
                // remove from cache
                synchronized (this.contactModelCache) {
                    if (!ListenerUtil.mutListener.listen(36941)) {
                        this.contactModelCache.remove(identity);
                    }
                }
                if (!ListenerUtil.mutListener.listen(36942)) {
                    this.contactStore.hideContact(contact, hide);
                }
            }
        }
    }

    /**
     *  Get hidden status of contact
     *  @param identity
     *  @return true if contact is hidden from contact list, false otherwise
     */
    @Override
    public boolean getIsHidden(String identity) {
        final ContactModel contact = this.getByIdentity(identity);
        return ((ListenerUtil.mutListener.listen(36944) ? (contact != null || contact.isHidden()) : (contact != null && contact.isHidden())));
    }

    @Override
    public void setIsArchived(String identity, boolean archived) {
        final ContactModel contact = this.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(36948)) {
            if ((ListenerUtil.mutListener.listen(36945) ? (contact != null || contact.isArchived() != archived) : (contact != null && contact.isArchived() != archived))) {
                if (!ListenerUtil.mutListener.listen(36946)) {
                    contact.setArchived(archived);
                }
                if (!ListenerUtil.mutListener.listen(36947)) {
                    save(contact);
                }
            }
        }
    }

    @Override
    public void save(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(36949)) {
            this.contactStore.addContact(contactModel);
        }
    }

    @Override
    public int save(List<ContactModel> contactModels, ContactProcessor contactProcessor) {
        int savedModels = 0;
        if (!ListenerUtil.mutListener.listen(36954)) {
            if (TestUtil.required(contactModels, contactProcessor)) {
                if (!ListenerUtil.mutListener.listen(36953)) {
                    {
                        long _loopCounter368 = 0;
                        for (ContactModel contactModel : contactModels) {
                            ListenerUtil.loopListener.listen("_loopCounter368", ++_loopCounter368);
                            if (!ListenerUtil.mutListener.listen(36952)) {
                                if (contactProcessor.process(contactModel)) {
                                    if (!ListenerUtil.mutListener.listen(36950)) {
                                        this.save(contactModel);
                                    }
                                    if (!ListenerUtil.mutListener.listen(36951)) {
                                        savedModels++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return savedModels;
    }

    @Override
    public boolean remove(ContactModel model) {
        return this.remove(model, true);
    }

    @Override
    public boolean remove(@NonNull ContactModel model, boolean removeLink) {
        String uniqueIdString = getUniqueIdString(model);
        if (!ListenerUtil.mutListener.listen(36955)) {
            clearAvatarCache(model);
        }
        AccessModel access = this.getAccess(model);
        if (!ListenerUtil.mutListener.listen(36967)) {
            if (access.canDelete()) {
                if (!ListenerUtil.mutListener.listen(36959)) {
                    // remove
                    this.contactStore.removeContact(model);
                }
                // remove from cache
                synchronized (this.contactModelCache) {
                    if (!ListenerUtil.mutListener.listen(36960)) {
                        this.contactModelCache.remove(model.getIdentity());
                    }
                }
                if (!ListenerUtil.mutListener.listen(36961)) {
                    this.ringtoneService.removeCustomRingtone(uniqueIdString);
                }
                if (!ListenerUtil.mutListener.listen(36962)) {
                    this.mutedChatsListService.remove(uniqueIdString);
                }
                if (!ListenerUtil.mutListener.listen(36963)) {
                    this.hiddenChatsListService.remove(uniqueIdString);
                }
                if (!ListenerUtil.mutListener.listen(36964)) {
                    this.profilePicRecipientsService.remove(model.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(36965)) {
                    this.wallpaperService.removeWallpaper(uniqueIdString);
                }
                if (!ListenerUtil.mutListener.listen(36966)) {
                    this.fileService.removeAndroidContactAvatar(model);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(36956)) {
                    // hide contact
                    setIsHidden(model.getIdentity(), true);
                }
                // also remove conversation of this contact
                try {
                    ConversationService conversationService = ThreemaApplication.getServiceManager().getConversationService();
                    if (!ListenerUtil.mutListener.listen(36958)) {
                        conversationService.removed(model);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(36957)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36969)) {
            if (removeLink) {
                if (!ListenerUtil.mutListener.listen(36968)) {
                    AndroidContactUtil.getInstance().deleteThreemaRawContact(model);
                }
            }
        }
        return true;
    }

    @NonNull
    @Override
    public AccessModel getAccess(ContactModel model) {
        if (!ListenerUtil.mutListener.listen(36980)) {
            if (model == null) {
                return new AccessModel() {

                    @Override
                    public boolean canDelete() {
                        return false;
                    }

                    @Override
                    public ValidationMessage[] canNotDeleteReasons() {
                        return new ValidationMessage[] { new ValidationMessage(context.getString(R.string.can_not_delete_contact), context.getString(R.string.can_not_delete_not_valid)) };
                    }
                };
            } else {
                boolean isInGroup = false;
                Cursor c = this.databaseServiceNew.getReadableDatabase().rawQuery("" + "SELECT COUNT(*) FROM m_group g " + "INNER JOIN group_member m " + "	ON m.groupId = g.id " + "WHERE m.identity = ? AND deleted = 0", new String[] { model.getIdentity() });
                if (!ListenerUtil.mutListener.listen(36978)) {
                    if (c != null) {
                        if (!ListenerUtil.mutListener.listen(36976)) {
                            if (c.moveToFirst()) {
                                if (!ListenerUtil.mutListener.listen(36975)) {
                                    isInGroup = (ListenerUtil.mutListener.listen(36974) ? (c.getInt(0) >= 0) : (ListenerUtil.mutListener.listen(36973) ? (c.getInt(0) <= 0) : (ListenerUtil.mutListener.listen(36972) ? (c.getInt(0) < 0) : (ListenerUtil.mutListener.listen(36971) ? (c.getInt(0) != 0) : (ListenerUtil.mutListener.listen(36970) ? (c.getInt(0) == 0) : (c.getInt(0) > 0))))));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(36977)) {
                            c.close();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36979)) {
                    if (isInGroup) {
                        return new AccessModel() {

                            @Override
                            public boolean canDelete() {
                                return false;
                            }

                            @Override
                            public ValidationMessage[] canNotDeleteReasons() {
                                return new ValidationMessage[] { new ValidationMessage(context.getString(R.string.can_not_delete_contact), context.getString(R.string.can_not_delete_contact_until_in_group)) };
                            }
                        };
                    }
                }
            }
        }
        return new AccessModel() {

            @Override
            public boolean canDelete() {
                return true;
            }

            @Override
            public ValidationMessage[] canNotDeleteReasons() {
                return new ValidationMessage[0];
            }
        };
    }

    @Override
    public int updateContactVerification(String identity, byte[] publicKey) {
        ContactModel c = this.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(36985)) {
            if (c != null) {
                if (!ListenerUtil.mutListener.listen(36984)) {
                    if (Arrays.equals(c.getPublicKey(), publicKey)) {
                        if (!ListenerUtil.mutListener.listen(36983)) {
                            if (c.getVerificationLevel() != VerificationLevel.FULLY_VERIFIED) {
                                if (!ListenerUtil.mutListener.listen(36981)) {
                                    c.setVerificationLevel(VerificationLevel.FULLY_VERIFIED);
                                }
                                if (!ListenerUtil.mutListener.listen(36982)) {
                                    this.save(c);
                                }
                                return ContactVerificationResult_VERIFIED;
                            } else {
                                return ContactVerificationResult_ALREADY_VERIFIED;
                            }
                        }
                    }
                }
            }
        }
        return ContactVerificationResult_NO_MATCH;
    }

    @Override
    @Nullable
    public Bitmap getCachedAvatar(ContactModel model) {
        if (!ListenerUtil.mutListener.listen(36986)) {
            if (model == null) {
                return null;
            }
        }
        return this.avatarCacheService.getContactAvatarLowFromCache(model);
    }

    @Override
    @Nullable
    public Bitmap getAvatar(ContactModel model, boolean highResolution) {
        return getAvatar(model, highResolution, true);
    }

    @Override
    public Bitmap getAvatar(ContactModel contact, boolean highResolution, boolean returnDefaultAvatarIfNone) {
        Bitmap b = null;
        if (!ListenerUtil.mutListener.listen(36993)) {
            if (contact != null) {
                if (!ListenerUtil.mutListener.listen(36989)) {
                    if (highResolution) {
                        if (!ListenerUtil.mutListener.listen(36988)) {
                            b = this.avatarCacheService.getContactAvatarHigh(contact);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(36987)) {
                            b = this.avatarCacheService.getContactAvatarLow(contact);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36992)) {
                    // check if a business avatar update is necessary
                    if ((ListenerUtil.mutListener.listen(36990) ? (ContactUtil.isChannelContact(contact) || ContactUtil.isAvatarExpired(contact)) : (ContactUtil.isChannelContact(contact) && ContactUtil.isAvatarExpired(contact)))) {
                        if (!ListenerUtil.mutListener.listen(36991)) {
                            // simple start
                            UpdateBusinessAvatarRoutine.startUpdate(contact, this.fileService, this);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36995)) {
            // return default avatar pic as a last resort
            if ((ListenerUtil.mutListener.listen(36994) ? (b == null || returnDefaultAvatarIfNone) : (b == null && returnDefaultAvatarIfNone))) {
                return getDefaultAvatar(contact, highResolution);
            }
        }
        return b;
    }

    @Override
    public Bitmap getDefaultAvatar(ContactModel contact, boolean highResolution) {
        @ColorInt
        int color = ColorUtil.getInstance().getCurrentThemeGray(this.context);
        if (!ListenerUtil.mutListener.listen(37000)) {
            if ((ListenerUtil.mutListener.listen(36998) ? (avatarCacheService.getDefaultAvatarColored() || ((ListenerUtil.mutListener.listen(36997) ? ((ListenerUtil.mutListener.listen(36996) ? (contact != null || contact.getIdentity() != null) : (contact != null && contact.getIdentity() != null)) || !contact.getIdentity().equals(identityStore.getIdentity())) : ((ListenerUtil.mutListener.listen(36996) ? (contact != null || contact.getIdentity() != null) : (contact != null && contact.getIdentity() != null)) && !contact.getIdentity().equals(identityStore.getIdentity()))))) : (avatarCacheService.getDefaultAvatarColored() && ((ListenerUtil.mutListener.listen(36997) ? ((ListenerUtil.mutListener.listen(36996) ? (contact != null || contact.getIdentity() != null) : (contact != null && contact.getIdentity() != null)) || !contact.getIdentity().equals(identityStore.getIdentity())) : ((ListenerUtil.mutListener.listen(36996) ? (contact != null || contact.getIdentity() != null) : (contact != null && contact.getIdentity() != null)) && !contact.getIdentity().equals(identityStore.getIdentity()))))))) {
                if (!ListenerUtil.mutListener.listen(36999)) {
                    color = contact.getColor();
                }
            }
        }
        if (highResolution) {
            return this.avatarCacheService.buildHiresDefaultAvatar(color, AvatarCacheService.CONTACT_AVATAR);
        } else {
            return AvatarConverterUtil.getAvatarBitmap(contactDefaultAvatar, color, avatarSizeSmall);
        }
    }

    @Override
    public Bitmap getNeutralAvatar(boolean highResolution) {
        return getDefaultAvatar(null, highResolution);
    }

    @Override
    public void clearAvatarCache(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(37002)) {
            if (this.avatarCacheService != null) {
                if (!ListenerUtil.mutListener.listen(37001)) {
                    this.avatarCacheService.reset(contactModel);
                }
            }
        }
    }

    @Override
    @NonNull
    public ContactModel createContactByIdentity(String identity, boolean force) throws InvalidEntryException, EntryAlreadyExistsException, PolicyViolationException {
        return createContactByIdentity(identity, force, false);
    }

    @Override
    @NonNull
    public ContactModel createContactByIdentity(String identity, boolean force, boolean hideContactByDefault) throws InvalidEntryException, EntryAlreadyExistsException, PolicyViolationException {
        if (!ListenerUtil.mutListener.listen(37004)) {
            if ((ListenerUtil.mutListener.listen(37003) ? (!force || AppRestrictionUtil.isAddContactDisabled(ThreemaApplication.getAppContext())) : (!force && AppRestrictionUtil.isAddContactDisabled(ThreemaApplication.getAppContext())))) {
                throw new PolicyViolationException();
            }
        }
        if (!ListenerUtil.mutListener.listen(37005)) {
            if (identity.equals(getMe().getIdentity())) {
                throw new InvalidEntryException(R.string.identity_already_exists);
            }
        }
        ContactModel newContact = this.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(37008)) {
            if (newContact == null) {
                if (!ListenerUtil.mutListener.listen(37007)) {
                    // create a new contact
                    newContact = this.createContactModelByIdentity(identity);
                }
            } else if ((ListenerUtil.mutListener.listen(37006) ? (!newContact.isHidden() && hideContactByDefault) : (!newContact.isHidden() || hideContactByDefault))) {
                throw new EntryAlreadyExistsException(R.string.identity_already_exists);
            }
        }
        if (!ListenerUtil.mutListener.listen(37009)) {
            // set default hidden status
            newContact.setIsHidden(hideContactByDefault);
        }
        if (!ListenerUtil.mutListener.listen(37010)) {
            // Set initial verification level
            newContact.setVerificationLevel(getInitialVerificationLevel(newContact));
        }
        if (!ListenerUtil.mutListener.listen(37011)) {
            this.save(newContact);
        }
        return newContact;
    }

    public VerificationLevel getInitialVerificationLevel(ContactModel contactModel) {
        // Determine whether this is a trusted public key (e.g. for *SUPPORT)
        final byte[] pubKey = contactModel.getPublicKey();
        boolean isTrusted = false;
        if (!ListenerUtil.mutListener.listen(37014)) {
            {
                long _loopCounter369 = 0;
                for (byte[] trustedKey : TRUSTED_PUBLIC_KEYS) {
                    ListenerUtil.loopListener.listen("_loopCounter369", ++_loopCounter369);
                    if (!ListenerUtil.mutListener.listen(37013)) {
                        if (Arrays.equals(trustedKey, pubKey)) {
                            if (!ListenerUtil.mutListener.listen(37012)) {
                                isTrusted = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return isTrusted ? VerificationLevel.FULLY_VERIFIED : VerificationLevel.UNVERIFIED;
    }

    @Override
    public ContactModel createContactByQRResult(QRCodeService.QRCodeContentResult qrResult) throws InvalidEntryException, EntryAlreadyExistsException, PolicyViolationException {
        ContactModel newContact = this.createContactByIdentity(qrResult.getIdentity(), false);
        if (!ListenerUtil.mutListener.listen(37017)) {
            if ((ListenerUtil.mutListener.listen(37015) ? (newContact == null && !Arrays.equals(newContact.getPublicKey(), qrResult.getPublicKey())) : (newContact == null || !Arrays.equals(newContact.getPublicKey(), qrResult.getPublicKey())))) {
                if (!ListenerUtil.mutListener.listen(37016)) {
                    // remove CONTACT!
                    this.remove(newContact);
                }
                throw new InvalidEntryException(R.string.invalid_threema_qr_code);
            }
        }
        if (!ListenerUtil.mutListener.listen(37018)) {
            newContact.setVerificationLevel(VerificationLevel.FULLY_VERIFIED);
        }
        if (!ListenerUtil.mutListener.listen(37019)) {
            this.save(newContact);
        }
        return newContact;
    }

    @Override
    public void removeAll() {
        if (!ListenerUtil.mutListener.listen(37021)) {
            {
                long _loopCounter370 = 0;
                for (ContactModel model : this.find(null)) {
                    ListenerUtil.loopListener.listen("_loopCounter370", ++_loopCounter370);
                    if (!ListenerUtil.mutListener.listen(37020)) {
                        this.remove(model, false);
                    }
                }
            }
        }
    }

    @Override
    public ContactMessageReceiver createReceiver(ContactModel contact) {
        return new ContactMessageReceiver(contact, this, this.databaseServiceNew, this.messageQueue, this.identityStore, this.blackListIdentityService);
    }

    private ContactModel getContact(AbstractMessage msg) {
        return this.getByIdentity(msg.getFromIdentity());
    }

    @Override
    public void updatePublicNickName(AbstractMessage msg) {
        if (!ListenerUtil.mutListener.listen(37022)) {
            if (msg == null) {
                return;
            }
        }
        ContactModel contact = getContact(msg);
        if (!ListenerUtil.mutListener.listen(37023)) {
            if (contact == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(37034)) {
            if ((ListenerUtil.mutListener.listen(37031) ? ((ListenerUtil.mutListener.listen(37030) ? ((ListenerUtil.mutListener.listen(37029) ? (msg.getPushFromName() != null || (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0))))))) : (msg.getPushFromName() != null && (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0)))))))) || !msg.getPushFromName().equals(contact.getIdentity())) : ((ListenerUtil.mutListener.listen(37029) ? (msg.getPushFromName() != null || (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0))))))) : (msg.getPushFromName() != null && (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0)))))))) && !msg.getPushFromName().equals(contact.getIdentity()))) || !msg.getPushFromName().equals(contact.getPublicNickName())) : ((ListenerUtil.mutListener.listen(37030) ? ((ListenerUtil.mutListener.listen(37029) ? (msg.getPushFromName() != null || (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0))))))) : (msg.getPushFromName() != null && (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0)))))))) || !msg.getPushFromName().equals(contact.getIdentity())) : ((ListenerUtil.mutListener.listen(37029) ? (msg.getPushFromName() != null || (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0))))))) : (msg.getPushFromName() != null && (ListenerUtil.mutListener.listen(37028) ? (msg.getPushFromName().length() >= 0) : (ListenerUtil.mutListener.listen(37027) ? (msg.getPushFromName().length() <= 0) : (ListenerUtil.mutListener.listen(37026) ? (msg.getPushFromName().length() < 0) : (ListenerUtil.mutListener.listen(37025) ? (msg.getPushFromName().length() != 0) : (ListenerUtil.mutListener.listen(37024) ? (msg.getPushFromName().length() == 0) : (msg.getPushFromName().length() > 0)))))))) && !msg.getPushFromName().equals(contact.getIdentity()))) && !msg.getPushFromName().equals(contact.getPublicNickName())))) {
                if (!ListenerUtil.mutListener.listen(37032)) {
                    contact.setPublicNickName(msg.getPushFromName());
                }
                if (!ListenerUtil.mutListener.listen(37033)) {
                    this.save(contact);
                }
            }
        }
    }

    @Override
    public boolean updateAllContactNamesAndAvatarsFromAndroidContacts() {
        if (!ListenerUtil.mutListener.listen(37041)) {
            if ((ListenerUtil.mutListener.listen(37040) ? ((ListenerUtil.mutListener.listen(37039) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37038) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37037) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37036) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37035) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(37039) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37038) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37037) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37036) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37035) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED))) {
                return false;
            }
        }
        List<ContactModel> androidContacts = this.getAll(true, true);
        if (!ListenerUtil.mutListener.listen(37049)) {
            if (androidContacts != null) {
                if (!ListenerUtil.mutListener.listen(37048)) {
                    {
                        long _loopCounter371 = 0;
                        for (ContactModel c : androidContacts) {
                            ListenerUtil.loopListener.listen("_loopCounter371", ++_loopCounter371);
                            if (!ListenerUtil.mutListener.listen(37047)) {
                                if (!TestUtil.empty(c.getAndroidContactLookupKey())) {
                                    try {
                                        if (!ListenerUtil.mutListener.listen(37046)) {
                                            if (AndroidContactUtil.getInstance().updateNameByAndroidContact(c)) {
                                                if (!ListenerUtil.mutListener.listen(37043)) {
                                                    AndroidContactUtil.getInstance().updateAvatarByAndroidContact(c);
                                                }
                                                if (!ListenerUtil.mutListener.listen(37044)) {
                                                    this.save(c);
                                                }
                                                if (!ListenerUtil.mutListener.listen(37045)) {
                                                    this.contactStore.reset(c);
                                                }
                                            }
                                        }
                                    } catch (ThreemaException e) {
                                        if (!ListenerUtil.mutListener.listen(37042)) {
                                            logger.error("Exception", e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void removeAllThreemaContactIds() {
        if (!ListenerUtil.mutListener.listen(37054)) {
            {
                long _loopCounter372 = 0;
                for (ContactModel c : this.find(null)) {
                    ListenerUtil.loopListener.listen("_loopCounter372", ++_loopCounter372);
                    if (!ListenerUtil.mutListener.listen(37052)) {
                        if (c.isSynchronized()) {
                            if (!ListenerUtil.mutListener.listen(37050)) {
                                c.setAndroidContactLookupKey(null);
                            }
                            if (!ListenerUtil.mutListener.listen(37051)) {
                                c.setIsSynchronized(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(37053)) {
                        this.save(c);
                    }
                }
            }
        }
    }

    @Override
    public boolean rebuildColors() {
        List<ContactModel> models = this.getAll(true, true);
        if (!ListenerUtil.mutListener.listen(37063)) {
            if (models != null) {
                int[] colors = ColorUtil.getInstance().generateGoogleColorPalette(models.size());
                if (!ListenerUtil.mutListener.listen(37062)) {
                    {
                        long _loopCounter373 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(37061) ? (n >= colors.length) : (ListenerUtil.mutListener.listen(37060) ? (n <= colors.length) : (ListenerUtil.mutListener.listen(37059) ? (n > colors.length) : (ListenerUtil.mutListener.listen(37058) ? (n != colors.length) : (ListenerUtil.mutListener.listen(37057) ? (n == colors.length) : (n < colors.length)))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter373", ++_loopCounter373);
                            ContactModel m = models.get(n);
                            if (!ListenerUtil.mutListener.listen(37055)) {
                                m.setColor(colors[n]);
                            }
                            if (!ListenerUtil.mutListener.listen(37056)) {
                                this.save(m);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    @Deprecated
    public int getUniqueId(ContactModel contactModel) {
        if (contactModel != null) {
            return ("c-" + contactModel.getIdentity()).hashCode();
        } else {
            return 0;
        }
    }

    @Override
    public String getUniqueIdString(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(37064)) {
            if (contactModel != null) {
                return getUniqueIdString(contactModel.getIdentity());
            }
        }
        return "";
    }

    @Override
    public String getUniqueIdString(String identity) {
        if (!ListenerUtil.mutListener.listen(37066)) {
            if (identity != null) {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    if (!ListenerUtil.mutListener.listen(37065)) {
                        messageDigest.update(("c-" + identity).getBytes());
                    }
                    return Base32.encode(messageDigest.digest());
                } catch (NoSuchAlgorithmException e) {
                }
            }
        }
        return "";
    }

    @Override
    public boolean setAvatar(final ContactModel contactModel, File temporaryAvatarFile) throws Exception {
        if (!ListenerUtil.mutListener.listen(37069)) {
            if ((ListenerUtil.mutListener.listen(37067) ? (contactModel != null || temporaryAvatarFile != null) : (contactModel != null && temporaryAvatarFile != null))) {
                if (!ListenerUtil.mutListener.listen(37068)) {
                    if (this.fileService.writeContactAvatar(contactModel, temporaryAvatarFile)) {
                        return this.onAvatarSet(contactModel);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean setAvatar(final ContactModel contactModel, byte[] avatar) throws Exception {
        if (!ListenerUtil.mutListener.listen(37072)) {
            if ((ListenerUtil.mutListener.listen(37070) ? (contactModel != null || avatar != null) : (contactModel != null && avatar != null))) {
                if (!ListenerUtil.mutListener.listen(37071)) {
                    if (this.fileService.writeContactAvatar(contactModel, avatar)) {
                        return this.onAvatarSet(contactModel);
                    }
                }
            }
        }
        return false;
    }

    private boolean onAvatarSet(final ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(37073)) {
            this.clearAvatarCache(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(37077)) {
            if (this.userService.isMe(contactModel.getIdentity())) {
                if (!ListenerUtil.mutListener.listen(37075)) {
                    // Update last profile picture change date
                    this.preferenceService.setProfilePicLastUpdate(new Date());
                }
                if (!ListenerUtil.mutListener.listen(37076)) {
                    // Notify listeners
                    ListenerManager.profileListeners.handle(ProfileListener::onAvatarChanged);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(37074)) {
                    ListenerManager.contactListeners.handle(listener -> listener.onAvatarChanged(contactModel));
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeAvatar(final ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(37084)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(37083)) {
                    if (this.fileService.removeContactAvatar(contactModel)) {
                        if (!ListenerUtil.mutListener.listen(37078)) {
                            this.clearAvatarCache(contactModel);
                        }
                        if (!ListenerUtil.mutListener.listen(37081)) {
                            // Notify listeners
                            if (this.userService.isMe(contactModel.getIdentity())) {
                                if (!ListenerUtil.mutListener.listen(37079)) {
                                    // Update last profile picture change date
                                    this.preferenceService.setProfilePicLastUpdate(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(37080)) {
                                    ListenerManager.profileListeners.handle(ProfileListener::onAvatarRemoved);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(37082)) {
                            ListenerManager.contactListeners.handle(listener -> listener.onAvatarChanged(contactModel));
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ContactPhotoUploadResult uploadContactPhoto(Bitmap picture) throws IOException, ThreemaException {
        /* only upload blob every 7 days */
        Date uploadDeadline = new Date(preferenceService.getProfilePicUploadDate() + DateUtils.WEEK_IN_MILLIS);
        Date now = new Date();
        ContactPhotoUploadResult result = new ContactPhotoUploadResult();
        if (!ListenerUtil.mutListener.listen(37094)) {
            if (now.after(uploadDeadline)) {
                if (!ListenerUtil.mutListener.listen(37086)) {
                    logger.info("Uploading profile picture blob");
                }
                SecureRandom rnd = new SecureRandom();
                if (!ListenerUtil.mutListener.listen(37087)) {
                    result.encryptionKey = new byte[NaCl.SYMMKEYBYTES];
                }
                if (!ListenerUtil.mutListener.listen(37088)) {
                    rnd.nextBytes(result.encryptionKey);
                }
                if (!ListenerUtil.mutListener.listen(37089)) {
                    result.bitmapArray = BitmapUtil.bitmapToJpegByteArray(picture);
                }
                byte[] imageData = NaCl.symmetricEncryptData(result.bitmapArray, result.encryptionKey, ProtocolDefines.CONTACT_PHOTO_NONCE);
                BlobUploader blobUploader = this.apiService.createUploader(imageData);
                if (!ListenerUtil.mutListener.listen(37090)) {
                    result.blobId = blobUploader.upload();
                }
                if (!ListenerUtil.mutListener.listen(37091)) {
                    result.size = imageData.length;
                }
                if (!ListenerUtil.mutListener.listen(37092)) {
                    preferenceService.setProfilePicUploadDate(now);
                }
                if (!ListenerUtil.mutListener.listen(37093)) {
                    preferenceService.setProfilePicUploadData(result);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(37085)) {
                    result = preferenceService.getProfilePicUploadData(result);
                }
            }
        }
        return result;
    }

    @Override
    public boolean updateContactPhoto(ContactSetPhotoMessage msg) {
        final ContactModel contactModel = this.getContact(msg);
        if (!ListenerUtil.mutListener.listen(37102)) {
            if (contactModel != null) {
                BlobLoader blobLoader = this.apiService.createLoader(msg.getBlobId());
                try {
                    byte[] encryptedBlob = blobLoader.load(false);
                    if (!ListenerUtil.mutListener.listen(37101)) {
                        if (encryptedBlob != null) {
                            if (!ListenerUtil.mutListener.listen(37097)) {
                                NaCl.symmetricDecryptDataInplace(encryptedBlob, msg.getEncryptionKey(), ProtocolDefines.CONTACT_PHOTO_NONCE);
                            }
                            if (!ListenerUtil.mutListener.listen(37098)) {
                                this.fileService.writeContactPhoto(contactModel, encryptedBlob);
                            }
                            if (!ListenerUtil.mutListener.listen(37099)) {
                                this.avatarCacheService.reset(contactModel);
                            }
                            if (!ListenerUtil.mutListener.listen(37100)) {
                                ListenerManager.contactListeners.handle(listener -> listener.onAvatarChanged(contactModel));
                            }
                            return true;
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(37095)) {
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(37096)) {
                        if (e instanceof FileNotFoundException) {
                            // do not bother trying download again
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteContactPhoto(ContactDeletePhotoMessage msg) {
        final ContactModel contactModel = this.getContact(msg);
        if (!ListenerUtil.mutListener.listen(37106)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(37103)) {
                    fileService.removeContactPhoto(contactModel);
                }
                if (!ListenerUtil.mutListener.listen(37104)) {
                    this.avatarCacheService.reset(contactModel);
                }
                if (!ListenerUtil.mutListener.listen(37105)) {
                    ListenerManager.contactListeners.handle(listener -> listener.onAvatarChanged(contactModel));
                }
            }
        }
        return true;
    }

    @Override
    public boolean requestContactPhoto(ContactRequestPhotoMessage msg) {
        final ContactModel contactModel = this.getContact(msg);
        if (!ListenerUtil.mutListener.listen(37110)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(37107)) {
                    logger.info("Received request to re-send profile pic by {}", msg.getFromIdentity());
                }
                if (!ListenerUtil.mutListener.listen(37108)) {
                    contactModel.setProfilePicSentDate(new Date(0));
                }
                if (!ListenerUtil.mutListener.listen(37109)) {
                    save(contactModel);
                }
            }
        }
        return true;
    }

    @Override
    public ContactModel createContactModelByIdentity(String identity) throws InvalidEntryException {
        if (!ListenerUtil.mutListener.listen(37112)) {
            if ((ListenerUtil.mutListener.listen(37111) ? (identity == null && identity.length() != ProtocolDefines.IDENTITY_LEN) : (identity == null || identity.length() != ProtocolDefines.IDENTITY_LEN))) {
                throw new InvalidEntryException(R.string.invalid_threema_id);
            }
        }
        if (!ListenerUtil.mutListener.listen(37113)) {
            // auto UPPERCASE identity
            identity = identity.toUpperCase();
        }
        if (!ListenerUtil.mutListener.listen(37114)) {
            // check for existing
            if (this.getByIdentity(identity) != null) {
                throw new InvalidEntryException(R.string.contact_already_exists);
            }
        }
        if (!ListenerUtil.mutListener.listen(37115)) {
            if (identity.equals(userService.getIdentity())) {
                throw new InvalidEntryException(R.string.contact_already_exists);
            }
        }
        if (!ListenerUtil.mutListener.listen(37116)) {
            if (!this.deviceService.isOnline()) {
                throw new InvalidEntryException(R.string.connection_error);
            }
        }
        // try to fetch
        byte[] publicKey;
        ContactModel newContact;
        try {
            publicKey = this.contactStore.fetchPublicKeyForIdentity(identity);
            if (!ListenerUtil.mutListener.listen(37117)) {
                if (publicKey == null) {
                    throw new InvalidEntryException(R.string.connection_error);
                }
            }
            newContact = this.getByIdentity(identity);
        } catch (FileNotFoundException e) {
            throw new InvalidEntryException(R.string.invalid_threema_id);
        }
        if (!ListenerUtil.mutListener.listen(37118)) {
            if (newContact == null) {
                throw new InvalidEntryException(R.string.invalid_threema_id);
            }
        }
        return newContact;
    }

    @Override
    public boolean showBadge(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(37122)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(37121)) {
                    if (ConfigUtils.isWorkBuild()) {
                        if (!ListenerUtil.mutListener.listen(37119)) {
                            if (userService.isMe(contactModel.getIdentity())) {
                                return false;
                            }
                        }
                        return (ListenerUtil.mutListener.listen(37120) ? (contactModel.getType() == IdentityType.NORMAL || ContactUtil.canReceiveProfilePics(contactModel)) : (contactModel.getType() == IdentityType.NORMAL && ContactUtil.canReceiveProfilePics(contactModel)));
                    } else {
                        return contactModel.getType() == IdentityType.WORK;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void setName(ContactModel contact, String firstName, String lastName) {
        if (!ListenerUtil.mutListener.listen(37123)) {
            contact.setFirstName(firstName);
        }
        if (!ListenerUtil.mutListener.listen(37124)) {
            contact.setLastName(lastName);
        }
        synchronized (this.contactModelCache) {
            if (!ListenerUtil.mutListener.listen(37125)) {
                this.contactModelCache.remove(contact.getIdentity());
            }
        }
        if (!ListenerUtil.mutListener.listen(37126)) {
            save(contact);
        }
    }

    /**
     *  Get Android contact lookup key Uri in String representation to be used for Notification.Builder.addPerson()
     *  @param contactModel ContactModel to get Uri for
     *  @return Uri of Android contact as a string or null if there's no linked contact or permission to access contacts has not been granted
     */
    public String getAndroidContactLookupUriString(ContactModel contactModel) {
        String contactLookupUri = null;
        if (!ListenerUtil.mutListener.listen(37137)) {
            if ((ListenerUtil.mutListener.listen(37131) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37130) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37129) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37128) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(37127) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(37136)) {
                    if (ContextCompat.checkSelfPermission(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                        if (!ListenerUtil.mutListener.listen(37135)) {
                            if ((ListenerUtil.mutListener.listen(37132) ? (contactModel != null || contactModel.getAndroidContactLookupKey() != null) : (contactModel != null && contactModel.getAndroidContactLookupKey() != null))) {
                                Uri lookupUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, contactModel.getAndroidContactLookupKey());
                                if (!ListenerUtil.mutListener.listen(37134)) {
                                    if (lookupUri != null) {
                                        if (!ListenerUtil.mutListener.listen(37133)) {
                                            contactLookupUri = lookupUri.toString();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return contactLookupUri;
    }

    /**
     *  Create a ContactModel for the provided Work contact. If a ContactModel already exists, it will be updated with the data from the Work API,
     *  namely. name, verification level, work status. If the contact was hidden (i.e. added by a group), it will be visible after this operation
     *  @param workContact WorkContact object for the contact to add
     *  @param existingWorkContacts An optional list of ContactModels. If a ContactModel already exists for workContact, the ContactModel will be removed from this list
     *  @return ContactModel of created or updated contact or null if public key of provided WorkContact was invalid
     */
    @Override
    @Nullable
    public ContactModel addWorkContact(@NonNull WorkContact workContact, @Nullable List<ContactModel> existingWorkContacts) {
        if (!ListenerUtil.mutListener.listen(37138)) {
            if (!ConfigUtils.isWorkBuild()) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(37140)) {
            if ((ListenerUtil.mutListener.listen(37139) ? (workContact.publicKey == null && workContact.publicKey.length != NaCl.PUBLICKEYBYTES) : (workContact.publicKey == null || workContact.publicKey.length != NaCl.PUBLICKEYBYTES))) {
                // ignore work contact with invalid public key
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(37142)) {
            if ((ListenerUtil.mutListener.listen(37141) ? (workContact.threemaId != null || workContact.threemaId.equals(getMe().getIdentity())) : (workContact.threemaId != null && workContact.threemaId.equals(getMe().getIdentity())))) {
                // do not add our own ID as a contact
                return null;
            }
        }
        ContactModel contactModel = getByIdentity(workContact.threemaId);
        if (!ListenerUtil.mutListener.listen(37152)) {
            if (contactModel == null) {
                if (!ListenerUtil.mutListener.listen(37151)) {
                    contactModel = new ContactModel(workContact.threemaId, workContact.publicKey);
                }
            } else if (existingWorkContacts != null) {
                if (!ListenerUtil.mutListener.listen(37150)) {
                    {
                        long _loopCounter374 = 0;
                        // try to remove from list of existing work contacts
                        for (int x = 0; (ListenerUtil.mutListener.listen(37149) ? (x >= existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(37148) ? (x <= existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(37147) ? (x > existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(37146) ? (x != existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(37145) ? (x == existingWorkContacts.size()) : (x < existingWorkContacts.size())))))); x++) {
                            ListenerUtil.loopListener.listen("_loopCounter374", ++_loopCounter374);
                            if (!ListenerUtil.mutListener.listen(37144)) {
                                if (existingWorkContacts.get(x).getIdentity().equals(workContact.threemaId)) {
                                    if (!ListenerUtil.mutListener.listen(37143)) {
                                        existingWorkContacts.remove(x);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37157)) {
            if ((ListenerUtil.mutListener.listen(37154) ? (!ContactUtil.isLinked(contactModel) || ((ListenerUtil.mutListener.listen(37153) ? (workContact.firstName != null && workContact.lastName != null) : (workContact.firstName != null || workContact.lastName != null)))) : (!ContactUtil.isLinked(contactModel) && ((ListenerUtil.mutListener.listen(37153) ? (workContact.firstName != null && workContact.lastName != null) : (workContact.firstName != null || workContact.lastName != null)))))) {
                if (!ListenerUtil.mutListener.listen(37155)) {
                    contactModel.setFirstName(workContact.firstName);
                }
                if (!ListenerUtil.mutListener.listen(37156)) {
                    contactModel.setLastName(workContact.lastName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37158)) {
            contactModel.setIsWork(true);
        }
        if (!ListenerUtil.mutListener.listen(37159)) {
            contactModel.setIsHidden(false);
        }
        if (!ListenerUtil.mutListener.listen(37161)) {
            if (contactModel.getVerificationLevel() != VerificationLevel.FULLY_VERIFIED) {
                if (!ListenerUtil.mutListener.listen(37160)) {
                    contactModel.setVerificationLevel(VerificationLevel.SERVER_VERIFIED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37162)) {
            this.save(contactModel);
        }
        return contactModel;
    }

    /**
     *  Check if a contact for the provided identity exists, if not, try to fetch a contact from work api and add it to the contact database
     *  @param identity Identity
     */
    @Override
    public void createWorkContact(@NonNull String identity) {
        if (!ListenerUtil.mutListener.listen(37163)) {
            if (!ConfigUtils.isWorkBuild()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(37173)) {
            if (contactStore.getPublicKeyForIdentity(identity, false) == null) {
                LicenseService.Credentials credentials = this.licenseService.loadCredentials();
                if (!ListenerUtil.mutListener.listen(37172)) {
                    if ((credentials instanceof UserCredentials)) {
                        try {
                            List<WorkContact> workContacts = apiConnector.fetchWorkContacts(((UserCredentials) credentials).username, ((UserCredentials) credentials).password, new String[] { identity });
                            if (!ListenerUtil.mutListener.listen(37171)) {
                                if ((ListenerUtil.mutListener.listen(37169) ? (workContacts.size() >= 0) : (ListenerUtil.mutListener.listen(37168) ? (workContacts.size() <= 0) : (ListenerUtil.mutListener.listen(37167) ? (workContacts.size() < 0) : (ListenerUtil.mutListener.listen(37166) ? (workContacts.size() != 0) : (ListenerUtil.mutListener.listen(37165) ? (workContacts.size() == 0) : (workContacts.size() > 0))))))) {
                                    WorkContact workContact = workContacts.get(0);
                                    if (!ListenerUtil.mutListener.listen(37170)) {
                                        addWorkContact(workContact, null);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(37164)) {
                                logger.error("Error fetching work contact", e);
                            }
                        }
                    }
                }
            }
        }
    }
}
