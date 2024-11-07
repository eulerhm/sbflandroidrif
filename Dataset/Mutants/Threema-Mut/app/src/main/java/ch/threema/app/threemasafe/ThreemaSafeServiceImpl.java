/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.threemasafe;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.Toast;
import com.lambdaworks.crypto.SCrypt;
import com.neilalexander.jnacl.NaCl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.Nullable;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.LocaleService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ColorUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.base.VerificationLevel;
import ch.threema.client.APIConnector;
import ch.threema.client.Base64;
import ch.threema.client.GroupId;
import ch.threema.client.IdentityState;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.ProtocolStrings;
import ch.threema.client.Utils;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.factories.ContactModelFactory;
import ch.threema.storage.factories.DistributionListMemberModelFactory;
import ch.threema.storage.factories.GroupMemberModelFactory;
import ch.threema.storage.factories.GroupModelFactory;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMemberModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupMemberModel;
import ch.threema.storage.models.GroupModel;
import static ch.threema.app.services.PreferenceService.PROFILEPIC_RELEASE_EVERYONE;
import static ch.threema.app.services.PreferenceService.PROFILEPIC_RELEASE_NOBODY;
import static ch.threema.app.services.PreferenceService.PROFILEPIC_RELEASE_SOME;
import static ch.threema.app.threemasafe.ThreemaSafeConfigureActivity.EXTRA_WORK_FORCE_PASSWORD;
import static ch.threema.app.threemasafe.ThreemaSafeServerTestResponse.CONFIG_MAX_BACKUP_BYTES;
import static ch.threema.app.threemasafe.ThreemaSafeServerTestResponse.CONFIG_RETENTION_DAYS;
import static ch.threema.app.threemasafe.ThreemaSafeUploadService.EXTRA_FORCE_UPLOAD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSafeServiceImpl implements ThreemaSafeService {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaSafeServiceImpl.class);

    private static final int SCRYPT_N = 65536;

    private static final int SCRYPT_R = 8;

    private static final int SCRYPT_P = 1;

    private static final int MASTERKEY_LENGTH = 64;

    private static final int PROFILEPIC_MAX_WIDTH = 400;

    private static final int PROFILEPIC_QUALITY = 60;

    private static final String PROFILE_PIC_RELEASE_ALL_PLACEHOLDER = "*";

    private static final int ENCRYPTION_KEY_LENGTH = NaCl.SYMMKEYBYTES;

    private static final int PROTOCOL_VERSION = 1;

    private static final int UPLOAD_JOB_ID = 6587625;

    public static final int MIN_PW_LENGTH = 8;

    public static final int MAX_PW_LENGTH = 4096;

    /* Threema Safe tags */
    private static final String TAG_SAFE_INFO = "info";

    private static final String TAG_SAFE_INFO_VERSION = "version";

    private static final String TAG_SAFE_INFO_DEVICE = "device";

    private static final String TAG_SAFE_USER = "user";

    private static final String TAG_SAFE_USER_PRIVATE_KEY = "privatekey";

    private static final String TAG_SAFE_USER_NICKNAME = "nickname";

    private static final String TAG_SAFE_USER_PROFILE_PIC = "profilePic";

    private static final String TAG_SAFE_USER_PROFILE_PIC_RELEASE = "profilePicRelease";

    private static final String TAG_SAFE_USER_LINKS = "links";

    private static final String TAG_SAFE_USER_LINK_TYPE = "type";

    private static final String TAG_SAFE_USER_LINK_VALUE = "value";

    private static final String TAG_SAFE_USER_LINK_TYPE_MOBILE = "mobile";

    private static final String TAG_SAFE_USER_LINK_TYPE_EMAIL = "email";

    private static final String TAG_SAFE_CONTACTS = "contacts";

    private static final String TAG_SAFE_CONTACT_IDENTITY = "identity";

    private static final String TAG_SAFE_CONTACT_PUBLIC_KEY = "publickey";

    private static final String TAG_SAFE_CONTACT_CREATED_AT = "createdAt";

    private static final String TAG_SAFE_CONTACT_VERIFICATION_LEVEL = "verification";

    private static final String TAG_SAFE_CONTACT_WORK_VERIFIED = "workVerified";

    private static final String TAG_SAFE_CONTACT_FIRST_NAME = "firstname";

    private static final String TAG_SAFE_CONTACT_LAST_NAME = "lastname";

    private static final String TAG_SAFE_CONTACT_NICKNAME = "nickname";

    private static final String TAG_SAFE_CONTACT_HIDDEN = "hidden";

    private static final String TAG_SAFE_CONTACT_PRIVATE = "private";

    private static final String TAG_SAFE_GROUPS = "groups";

    private static final String TAG_SAFE_GROUP_ID = "id";

    private static final String TAG_SAFE_GROUP_CREATOR = "creator";

    private static final String TAG_SAFE_GROUP_NAME = "groupname";

    private static final String TAG_SAFE_GROUP_CREATED_AT = "createdAt";

    private static final String TAG_SAFE_GROUP_MEMBERS = "members";

    private static final String TAG_SAFE_GROUP_DELETED = "deleted";

    private static final String TAG_SAFE_GROUP_PRIVATE = "private";

    private static final String TAG_SAFE_DISTRIBUTIONLISTS = "distributionlists";

    private static final String TAG_SAFE_DISTRIBUTIONLIST_NAME = "name";

    private static final String TAG_SAFE_DISTRIBUTIONLIST_CREATED_AT = "createdAt";

    private static final String TAG_SAFE_DISTRIBUTIONLIST_MEMBERS = "members";

    private static final String TAG_SAFE_DISTRIBUTIONLIST_PRIVATE = "private";

    private static final String TAG_SAFE_SETTINGS = "settings";

    private static final String TAG_SAFE_SETTINGS_SYNC_CONTACTS = "syncContacts";

    private static final String TAG_SAFE_SETTINGS_BLOCK_UNKNOWN = "blockUnknown";

    private static final String TAG_SAFE_SETTINGS_READ_RECEIPTS = "readReceipts";

    private static final String TAG_SAFE_SETTINGS_SEND_TYPING = "sendTyping";

    private static final String TAG_SAFE_SETTINGS_BLOCKED_CONTACTS = "blockedContacts";

    private static final String TAG_SAFE_SETTINGS_THREEMA_CALLS = "threemaCalls";

    private static final String TAG_SAFE_SETTINGS_LOCATION_PREVIEWS = "locationPreviews";

    private static final String TAG_SAFE_SETTINGS_RELAY_THREEMA_CALLS = "relayThreemaCalls";

    private static final String TAG_SAFE_SETTINGS_DISABLE_SCREENSHOTS = "disableScreenshots";

    private static final String TAG_SAFE_SETTINGS_INCOGNITO_KEAYBOARD = "incognitoKeyboard";

    private static final String TAG_SAFE_SETTINGS_SYNC_EXCLUDED_CONTACTS = "syncExcludedIds";

    private static final String TAG_SAFE_SETTINGS_RECENT_EMOJIS = "recentEmojis";

    private static final String KEY_USER_AGENT = "User-Agent";

    private final Context context;

    private final PreferenceService preferenceService;

    private final UserService userService;

    private final IdentityStore identityStore;

    private final APIConnector apiConnector;

    private final LocaleService localeService;

    private final ContactService contactService;

    private final FileService fileService;

    private final IdListService profilePicRecipientsService;

    private final DatabaseServiceNew databaseServiceNew;

    private final DeadlineListService hiddenChatsListService;

    public ThreemaSafeServiceImpl(Context context, PreferenceService preferenceService, UserService userService, ContactService contactService, LocaleService localeService, FileService fileService, IdListService profilePicRecipientsService, DatabaseServiceNew databaseServiceNew, IdentityStore identityStore, APIConnector apiConnector, DeadlineListService hiddehChatsListService) {
        this.context = context;
        this.preferenceService = preferenceService;
        this.userService = userService;
        this.contactService = contactService;
        this.identityStore = identityStore;
        this.apiConnector = apiConnector;
        this.localeService = localeService;
        this.databaseServiceNew = databaseServiceNew;
        this.fileService = fileService;
        this.profilePicRecipientsService = profilePicRecipientsService;
        this.hiddenChatsListService = hiddehChatsListService;
    }

    @Override
    @Nullable
    public byte[] deriveMasterKey(String password, String identity) {
        if (!ListenerUtil.mutListener.listen(43017)) {
            if ((ListenerUtil.mutListener.listen(43015) ? (!TextUtils.isEmpty(password) || !TextUtils.isEmpty(identity)) : (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(identity)))) {
                try {
                    final byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
                    final byte[] identityBytes = identity.getBytes(StandardCharsets.UTF_8);
                    return SCrypt.scrypt(passwordBytes, identityBytes, SCRYPT_N, SCRYPT_R, SCRYPT_P, MASTERKEY_LENGTH);
                } catch (GeneralSecurityException e) {
                    if (!ListenerUtil.mutListener.listen(43016)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean storeMasterKey(byte[] masterKey) {
        if (!ListenerUtil.mutListener.listen(43019)) {
            if (masterKey != null) {
                if (!ListenerUtil.mutListener.listen(43018)) {
                    preferenceService.setThreemaSafeMasterKey(masterKey);
                }
            }
        }
        return false;
    }

    @Override
    @Nullable
    public byte[] getThreemaSafeBackupId() {
        byte[] masterKey = preferenceService.getThreemaSafeMasterKey();
        if (!ListenerUtil.mutListener.listen(43026)) {
            if ((ListenerUtil.mutListener.listen(43025) ? (masterKey != null || (ListenerUtil.mutListener.listen(43024) ? (masterKey.length >= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43023) ? (masterKey.length <= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43022) ? (masterKey.length > MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43021) ? (masterKey.length < MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43020) ? (masterKey.length != MASTERKEY_LENGTH) : (masterKey.length == MASTERKEY_LENGTH))))))) : (masterKey != null && (ListenerUtil.mutListener.listen(43024) ? (masterKey.length >= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43023) ? (masterKey.length <= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43022) ? (masterKey.length > MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43021) ? (masterKey.length < MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43020) ? (masterKey.length != MASTERKEY_LENGTH) : (masterKey.length == MASTERKEY_LENGTH))))))))) {
                return Arrays.copyOfRange(masterKey, 0, BACKUP_ID_LENGTH);
            }
        }
        return null;
    }

    @Override
    @Nullable
    public byte[] getThreemaSafeEncryptionKey() {
        byte[] masterKey = preferenceService.getThreemaSafeMasterKey();
        if (!ListenerUtil.mutListener.listen(43033)) {
            if ((ListenerUtil.mutListener.listen(43032) ? (masterKey != null || (ListenerUtil.mutListener.listen(43031) ? (masterKey.length >= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43030) ? (masterKey.length <= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43029) ? (masterKey.length > MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43028) ? (masterKey.length < MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43027) ? (masterKey.length != MASTERKEY_LENGTH) : (masterKey.length == MASTERKEY_LENGTH))))))) : (masterKey != null && (ListenerUtil.mutListener.listen(43031) ? (masterKey.length >= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43030) ? (masterKey.length <= MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43029) ? (masterKey.length > MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43028) ? (masterKey.length < MASTERKEY_LENGTH) : (ListenerUtil.mutListener.listen(43027) ? (masterKey.length != MASTERKEY_LENGTH) : (masterKey.length == MASTERKEY_LENGTH))))))))) {
                return Arrays.copyOfRange(masterKey, BACKUP_ID_LENGTH, BACKUP_ID_LENGTH + ENCRYPTION_KEY_LENGTH);
            }
        }
        return null;
    }

    @Override
    public byte[] getThreemaSafeMasterKey() {
        return preferenceService.getThreemaSafeMasterKey();
    }

    @Override
    public ThreemaSafeServerTestResponse testServer(ThreemaSafeServerInfo serverInfo) throws ThreemaException {
        URL configUrl = serverInfo.getConfigUrl(getThreemaSafeBackupId());
        HttpsURLConnection urlConnection;
        try {
            urlConnection = (HttpsURLConnection) configUrl.openConnection();
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(43034)) {
                logger.error("Exception", e);
            }
            throw new ThreemaException("Unable to connect to server");
        }
        try {
            if (!ListenerUtil.mutListener.listen(43044)) {
                urlConnection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(configUrl.getHost()));
            }
            if (!ListenerUtil.mutListener.listen(43045)) {
                urlConnection.setConnectTimeout(15000);
            }
            if (!ListenerUtil.mutListener.listen(43046)) {
                urlConnection.setReadTimeout(30000);
            }
            if (!ListenerUtil.mutListener.listen(43047)) {
                urlConnection.setRequestMethod("GET");
            }
            if (!ListenerUtil.mutListener.listen(43048)) {
                urlConnection.setRequestProperty("Accept", "application/json");
            }
            if (!ListenerUtil.mutListener.listen(43049)) {
                urlConnection.setRequestProperty(KEY_USER_AGENT, ProtocolStrings.USER_AGENT);
            }
            if (!ListenerUtil.mutListener.listen(43050)) {
                serverInfo.addAuthorization(urlConnection);
            }
            if (!ListenerUtil.mutListener.listen(43051)) {
                urlConnection.setDoOutput(false);
            }
            byte[] buf;
            try (BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream())) {
                int bufLength = 4096;
                buf = new byte[bufLength];
                int bytesRead = bis.read(buf, 0, bufLength);
                if (!ListenerUtil.mutListener.listen(43057)) {
                    if ((ListenerUtil.mutListener.listen(43056) ? (bytesRead >= 0) : (ListenerUtil.mutListener.listen(43055) ? (bytesRead > 0) : (ListenerUtil.mutListener.listen(43054) ? (bytesRead < 0) : (ListenerUtil.mutListener.listen(43053) ? (bytesRead != 0) : (ListenerUtil.mutListener.listen(43052) ? (bytesRead == 0) : (bytesRead <= 0))))))) {
                        throw new ThreemaException("Config file empty or not readable");
                    }
                }
            }
            final int responseCode = urlConnection.getResponseCode();
            if (!ListenerUtil.mutListener.listen(43063)) {
                if ((ListenerUtil.mutListener.listen(43062) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43061) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43060) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43059) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43058) ? (responseCode == 200) : (responseCode != 200))))))) {
                    throw new ThreemaException("Server error: " + responseCode);
                }
            }
            String configJson = new String(buf, StandardCharsets.UTF_8);
            ThreemaSafeServerTestResponse response = new ThreemaSafeServerTestResponse();
            JSONObject jsonObject = new JSONObject(configJson);
            if (!ListenerUtil.mutListener.listen(43064)) {
                response.maxBackupBytes = jsonObject.getLong(CONFIG_MAX_BACKUP_BYTES);
            }
            if (!ListenerUtil.mutListener.listen(43065)) {
                response.retentionDays = jsonObject.getInt(CONFIG_RETENTION_DAYS);
            }
            if (!ListenerUtil.mutListener.listen(43066)) {
                preferenceService.setThreemaSafeServerMaxUploadSize(response.maxBackupBytes);
            }
            if (!ListenerUtil.mutListener.listen(43067)) {
                preferenceService.setThreemaSafeServerRetention(response.retentionDays);
            }
            return response;
        } catch (IOException e) {
            try {
                int responseCode = urlConnection.getResponseCode();
                String responseMessage = urlConnection.getResponseMessage();
                if (!ListenerUtil.mutListener.listen(43042)) {
                    if ((ListenerUtil.mutListener.listen(43041) ? (e instanceof FileNotFoundException || (ListenerUtil.mutListener.listen(43040) ? (responseCode >= 404) : (ListenerUtil.mutListener.listen(43039) ? (responseCode <= 404) : (ListenerUtil.mutListener.listen(43038) ? (responseCode > 404) : (ListenerUtil.mutListener.listen(43037) ? (responseCode < 404) : (ListenerUtil.mutListener.listen(43036) ? (responseCode != 404) : (responseCode == 404))))))) : (e instanceof FileNotFoundException && (ListenerUtil.mutListener.listen(43040) ? (responseCode >= 404) : (ListenerUtil.mutListener.listen(43039) ? (responseCode <= 404) : (ListenerUtil.mutListener.listen(43038) ? (responseCode > 404) : (ListenerUtil.mutListener.listen(43037) ? (responseCode < 404) : (ListenerUtil.mutListener.listen(43036) ? (responseCode != 404) : (responseCode == 404))))))))) {
                        throw new ThreemaException("Config file not found");
                    } else {
                        throw new ThreemaException(responseCode + ": " + responseMessage);
                    }
                }
            } catch (IOException e1) {
                if (!ListenerUtil.mutListener.listen(43035)) {
                    logger.error("I/O Exception", e1);
                }
            }
            throw new ThreemaException("IO Exception: " + e.getMessage());
        } catch (JSONException e) {
            throw new ThreemaException("Malformed server response");
        } catch (IllegalArgumentException e) {
            throw new ThreemaException(e.getMessage());
        } finally {
            if (!ListenerUtil.mutListener.listen(43043)) {
                urlConnection.disconnect();
            }
        }
    }

    @Override
    public boolean scheduleUpload() {
        if (!ListenerUtil.mutListener.listen(43081)) {
            if (preferenceService.getThreemaSafeEnabled()) {
                if (!ListenerUtil.mutListener.listen(43069)) {
                    logger.info("Scheduling Threema Safe upload");
                }
                if (!ListenerUtil.mutListener.listen(43080)) {
                    // schedule the start of the service every 24 hours
                    if ((ListenerUtil.mutListener.listen(43074) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43073) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43072) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43071) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43070) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        if (!ListenerUtil.mutListener.listen(43079)) {
                            if (jobScheduler != null) {
                                ComponentName serviceComponent = new ComponentName(context, ThreemaSafeUploadJobService.class);
                                JobInfo.Builder builder = new JobInfo.Builder(UPLOAD_JOB_ID, serviceComponent).setPeriodic(SCHEDULE_PERIOD).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
                                try {
                                    if (!ListenerUtil.mutListener.listen(43078)) {
                                        jobScheduler.schedule(builder.build());
                                    }
                                } catch (IllegalArgumentException e) {
                                    if (!ListenerUtil.mutListener.listen(43077)) {
                                        logger.error("Exception", e);
                                    }
                                }
                                return true;
                            }
                        }
                    } else {
                        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        if (!ListenerUtil.mutListener.listen(43076)) {
                            if (alarmMgr != null) {
                                Intent intent = new Intent(context, ThreemaSafeUploadService.class);
                                PendingIntent pendingIntent = PendingIntent.getService(context, UPLOAD_JOB_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                                if (!ListenerUtil.mutListener.listen(43075)) {
                                    alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), SCHEDULE_PERIOD, pendingIntent);
                                }
                                return true;
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(43068)) {
                    logger.info("Threema Safe disabled");
                }
            }
        }
        return false;
    }

    @Override
    public void unscheduleUpload() {
        if (!ListenerUtil.mutListener.listen(43082)) {
            logger.info("Unscheduling Threema Safe upload");
        }
        if (!ListenerUtil.mutListener.listen(43092)) {
            if ((ListenerUtil.mutListener.listen(43087) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43086) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43085) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43084) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(43083) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                if (!ListenerUtil.mutListener.listen(43091)) {
                    if (jobScheduler != null) {
                        if (!ListenerUtil.mutListener.listen(43090)) {
                            jobScheduler.cancel(UPLOAD_JOB_ID);
                        }
                    }
                }
            } else {
                AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                if (!ListenerUtil.mutListener.listen(43089)) {
                    if (alarmMgr != null) {
                        Intent intent = new Intent(context, ThreemaSafeUploadService.class);
                        PendingIntent pendingIntent = PendingIntent.getService(context, UPLOAD_JOB_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        if (!ListenerUtil.mutListener.listen(43088)) {
                            alarmMgr.cancel(pendingIntent);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isUploadDue() {
        return ((ListenerUtil.mutListener.listen(43103) ? ((ListenerUtil.mutListener.listen(43093) ? (preferenceService != null || preferenceService.getThreemaSafeEnabled()) : (preferenceService != null && preferenceService.getThreemaSafeEnabled())) || (ListenerUtil.mutListener.listen(43102) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) >= ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43101) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) <= ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43100) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) < ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43099) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) != ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43098) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) == ThreemaSafeService.SCHEDULE_PERIOD) : ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) > ThreemaSafeService.SCHEDULE_PERIOD))))))) : ((ListenerUtil.mutListener.listen(43093) ? (preferenceService != null || preferenceService.getThreemaSafeEnabled()) : (preferenceService != null && preferenceService.getThreemaSafeEnabled())) && (ListenerUtil.mutListener.listen(43102) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) >= ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43101) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) <= ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43100) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) < ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43099) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) != ThreemaSafeService.SCHEDULE_PERIOD) : (ListenerUtil.mutListener.listen(43098) ? ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) == ThreemaSafeService.SCHEDULE_PERIOD) : ((ListenerUtil.mutListener.listen(43097) ? (System.currentTimeMillis() % preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43096) ? (System.currentTimeMillis() / preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43095) ? (System.currentTimeMillis() * preferenceService.getThreemaSafeUploadDate().getTime()) : (ListenerUtil.mutListener.listen(43094) ? (System.currentTimeMillis() + preferenceService.getThreemaSafeUploadDate().getTime()) : (System.currentTimeMillis() - preferenceService.getThreemaSafeUploadDate().getTime()))))) > ThreemaSafeService.SCHEDULE_PERIOD)))))))));
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(43106)) {
            if ((ListenerUtil.mutListener.listen(43104) ? (ConfigUtils.isWorkRestricted() || ThreemaSafeMDMConfig.getInstance().isBackupDisabled()) : (ConfigUtils.isWorkRestricted() && ThreemaSafeMDMConfig.getInstance().isBackupDisabled()))) {
                if (!ListenerUtil.mutListener.listen(43105)) {
                    enabled = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43107)) {
            preferenceService.setThreemaSafeEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(43117)) {
            if (enabled) {
                if (!ListenerUtil.mutListener.listen(43116)) {
                    scheduleUpload();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(43108)) {
                    // disable Safe
                    unscheduleUpload();
                }
                if (!ListenerUtil.mutListener.listen(43109)) {
                    preferenceService.setThreemaSafeEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(43110)) {
                    preferenceService.setThreemaSafeMasterKey(new byte[0]);
                }
                if (!ListenerUtil.mutListener.listen(43111)) {
                    preferenceService.setThreemaSafeServerInfo(null);
                }
                if (!ListenerUtil.mutListener.listen(43112)) {
                    preferenceService.setThreemaSafeUploadDate(new Date(0));
                }
                if (!ListenerUtil.mutListener.listen(43113)) {
                    preferenceService.setThreemaSafeBackupDate(new Date(0));
                }
                if (!ListenerUtil.mutListener.listen(43114)) {
                    preferenceService.setThreemaSafeHashString("");
                }
                if (!ListenerUtil.mutListener.listen(43115)) {
                    preferenceService.setThreemaSafeErrorCode(ERROR_CODE_OK);
                }
            }
        }
    }

    @Override
    public void uploadNow(Context context, boolean force) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(43119)) {
            if (force) {
                if (!ListenerUtil.mutListener.listen(43118)) {
                    intent.putExtra(EXTRA_FORCE_UPLOAD, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43120)) {
            ThreemaSafeUploadService.enqueueWork(context, intent);
        }
    }

    @Override
    public void createBackup(boolean force) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(43121)) {
            logger.info("Starting Threema Safe backup");
        }
        if (!ListenerUtil.mutListener.listen(43122)) {
            if (!preferenceService.getThreemaSafeEnabled()) {
                throw new ThreemaException("Disabled");
            }
        }
        if (!ListenerUtil.mutListener.listen(43123)) {
            if (getThreemaSafeEncryptionKey() == null) {
                throw new ThreemaException("No key");
            }
        }
        ThreemaSafeServerInfo serverInfo = preferenceService.getThreemaSafeServerInfo();
        if (!ListenerUtil.mutListener.listen(43124)) {
            if (serverInfo == null) {
                throw new ThreemaException("No server info");
            }
        }
        // test server to update configuration
        final ThreemaSafeServerTestResponse serverTestResponse;
        try {
            serverTestResponse = testServer(serverInfo);
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(43125)) {
                preferenceService.setThreemaSafeErrorCode(ERROR_CODE_SERVER_FAIL);
            }
            throw new ThreemaException("Server test failed. " + e.getMessage());
        }
        String json = getJson();
        if (!ListenerUtil.mutListener.listen(43127)) {
            if (json == null) {
                if (!ListenerUtil.mutListener.listen(43126)) {
                    preferenceService.setThreemaSafeErrorCode(ERROR_CODE_JSON_FAIL);
                }
                throw new ThreemaException("Json failed");
            }
        }
        // get a hash of the json to determine if there are any changes
        String hashString;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            if (!ListenerUtil.mutListener.listen(43129)) {
                messageDigest.update(json.getBytes(StandardCharsets.UTF_8));
            }
            hashString = StringConversionUtil.byteArrayToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            if (!ListenerUtil.mutListener.listen(43128)) {
                preferenceService.setThreemaSafeErrorCode(ERROR_CODE_HASH_FAIL);
            }
            throw new ThreemaException("Hash calculation failed");
        }
        if (!ListenerUtil.mutListener.listen(43140)) {
            if (!force) {
                if (!ListenerUtil.mutListener.listen(43139)) {
                    if (hashString.equals(preferenceService.getThreemaSafeHashString())) {
                        Date aWeekAgo = new Date((ListenerUtil.mutListener.listen(43133) ? (System.currentTimeMillis() % DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(43132) ? (System.currentTimeMillis() / DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(43131) ? (System.currentTimeMillis() * DateUtils.WEEK_IN_MILLIS) : (ListenerUtil.mutListener.listen(43130) ? (System.currentTimeMillis() + DateUtils.WEEK_IN_MILLIS) : (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS))))));
                        if (!ListenerUtil.mutListener.listen(43138)) {
                            if ((ListenerUtil.mutListener.listen(43134) ? (preferenceService.getThreemaSafeErrorCode() == ERROR_CODE_OK || aWeekAgo.before(preferenceService.getThreemaSafeUploadDate())) : (preferenceService.getThreemaSafeErrorCode() == ERROR_CODE_OK && aWeekAgo.before(preferenceService.getThreemaSafeUploadDate())))) {
                                if (!ListenerUtil.mutListener.listen(43135)) {
                                    preferenceService.setThreemaSafeErrorCode(ERROR_CODE_OK);
                                }
                                if (!ListenerUtil.mutListener.listen(43136)) {
                                    preferenceService.setThreemaSafeBackupDate(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(43137)) {
                                    logger.info("Threema Safe contents unchanged. Not uploaded");
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
        byte[] gzippedPlaintext = gZipCompress(json.getBytes());
        if (!ListenerUtil.mutListener.listen(43148)) {
            if ((ListenerUtil.mutListener.listen(43146) ? (gzippedPlaintext == null && (ListenerUtil.mutListener.listen(43145) ? (gzippedPlaintext.length >= 0) : (ListenerUtil.mutListener.listen(43144) ? (gzippedPlaintext.length > 0) : (ListenerUtil.mutListener.listen(43143) ? (gzippedPlaintext.length < 0) : (ListenerUtil.mutListener.listen(43142) ? (gzippedPlaintext.length != 0) : (ListenerUtil.mutListener.listen(43141) ? (gzippedPlaintext.length == 0) : (gzippedPlaintext.length <= 0))))))) : (gzippedPlaintext == null || (ListenerUtil.mutListener.listen(43145) ? (gzippedPlaintext.length >= 0) : (ListenerUtil.mutListener.listen(43144) ? (gzippedPlaintext.length > 0) : (ListenerUtil.mutListener.listen(43143) ? (gzippedPlaintext.length < 0) : (ListenerUtil.mutListener.listen(43142) ? (gzippedPlaintext.length != 0) : (ListenerUtil.mutListener.listen(43141) ? (gzippedPlaintext.length == 0) : (gzippedPlaintext.length <= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(43147)) {
                    preferenceService.setThreemaSafeErrorCode(ERROR_CODE_GZIP_FAIL);
                }
                throw new ThreemaException("Compression failed");
            }
        }
        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[NaCl.NONCEBYTES];
        if (!ListenerUtil.mutListener.listen(43149)) {
            random.nextBytes(nonce);
        }
        try {
            byte[] encdata = NaCl.symmetricEncryptData(gzippedPlaintext, getThreemaSafeEncryptionKey(), nonce);
            byte[] threemaSafeEncryptedBackup = new byte[(ListenerUtil.mutListener.listen(43157) ? (nonce.length % encdata.length) : (ListenerUtil.mutListener.listen(43156) ? (nonce.length / encdata.length) : (ListenerUtil.mutListener.listen(43155) ? (nonce.length * encdata.length) : (ListenerUtil.mutListener.listen(43154) ? (nonce.length - encdata.length) : (nonce.length + encdata.length)))))];
            if (!ListenerUtil.mutListener.listen(43158)) {
                System.arraycopy(nonce, 0, threemaSafeEncryptedBackup, 0, nonce.length);
            }
            if (!ListenerUtil.mutListener.listen(43159)) {
                System.arraycopy(encdata, 0, threemaSafeEncryptedBackup, nonce.length, encdata.length);
            }
            if (!ListenerUtil.mutListener.listen(43172)) {
                if ((ListenerUtil.mutListener.listen(43164) ? (threemaSafeEncryptedBackup.length >= serverTestResponse.maxBackupBytes) : (ListenerUtil.mutListener.listen(43163) ? (threemaSafeEncryptedBackup.length > serverTestResponse.maxBackupBytes) : (ListenerUtil.mutListener.listen(43162) ? (threemaSafeEncryptedBackup.length < serverTestResponse.maxBackupBytes) : (ListenerUtil.mutListener.listen(43161) ? (threemaSafeEncryptedBackup.length != serverTestResponse.maxBackupBytes) : (ListenerUtil.mutListener.listen(43160) ? (threemaSafeEncryptedBackup.length == serverTestResponse.maxBackupBytes) : (threemaSafeEncryptedBackup.length <= serverTestResponse.maxBackupBytes))))))) {
                    if (!ListenerUtil.mutListener.listen(43166)) {
                        uploadData(serverInfo, threemaSafeEncryptedBackup);
                    }
                    if (!ListenerUtil.mutListener.listen(43167)) {
                        preferenceService.setThreemaSafeBackupSize(threemaSafeEncryptedBackup.length);
                    }
                    if (!ListenerUtil.mutListener.listen(43168)) {
                        preferenceService.setThreemaSafeUploadDate(new Date());
                    }
                    if (!ListenerUtil.mutListener.listen(43169)) {
                        preferenceService.setThreemaSafeBackupDate(new Date());
                    }
                    if (!ListenerUtil.mutListener.listen(43170)) {
                        preferenceService.setThreemaSafeHashString(hashString);
                    }
                    if (!ListenerUtil.mutListener.listen(43171)) {
                        preferenceService.setThreemaSafeErrorCode(ERROR_CODE_OK);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(43165)) {
                        preferenceService.setThreemaSafeBackupSize(threemaSafeEncryptedBackup.length);
                    }
                    throw new UploadSizeExceedException("Upload size exceeded");
                }
            }
        } catch (UploadSizeExceedException e) {
            if (!ListenerUtil.mutListener.listen(43150)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(43151)) {
                preferenceService.setThreemaSafeErrorCode(ERROR_CODE_SIZE_EXCEEDED);
            }
            throw new ThreemaException(e.getMessage());
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43152)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(43153)) {
                preferenceService.setThreemaSafeErrorCode(ERROR_CODE_UPLOAD_FAIL);
            }
            throw new ThreemaException("Upload failed");
        }
        if (!ListenerUtil.mutListener.listen(43175)) {
            if (force) {
                if (!ListenerUtil.mutListener.listen(43174)) {
                    RuntimeUtil.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(43173)) {
                                Toast.makeText(context, R.string.threema_safe_upload_successful, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43176)) {
            logger.info(context.getString(R.string.threema_safe_upload_successful));
        }
    }

    @Override
    public void deleteBackup() throws ThreemaException {
        ThreemaSafeServerInfo serverInfo = preferenceService.getThreemaSafeServerInfo();
        if (!ListenerUtil.mutListener.listen(43177)) {
            if (serverInfo == null) {
                throw new ThreemaException("No server info");
            }
        }
        URL serverUrl = serverInfo.getBackupUrl(getThreemaSafeBackupId());
        HttpsURLConnection urlConnection;
        try {
            urlConnection = (HttpsURLConnection) serverUrl.openConnection();
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(43178)) {
                logger.error("Exception", e);
            }
            throw new ThreemaException("Unable to connect to server");
        }
        try {
            if (!ListenerUtil.mutListener.listen(43180)) {
                urlConnection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(serverUrl.getHost()));
            }
            if (!ListenerUtil.mutListener.listen(43181)) {
                urlConnection.setConnectTimeout(15000);
            }
            if (!ListenerUtil.mutListener.listen(43182)) {
                urlConnection.setReadTimeout(30000);
            }
            if (!ListenerUtil.mutListener.listen(43183)) {
                urlConnection.setRequestMethod("DELETE");
            }
            if (!ListenerUtil.mutListener.listen(43184)) {
                urlConnection.setRequestProperty(KEY_USER_AGENT, ProtocolStrings.USER_AGENT);
            }
            if (!ListenerUtil.mutListener.listen(43185)) {
                serverInfo.addAuthorization(urlConnection);
            }
            if (!ListenerUtil.mutListener.listen(43186)) {
                urlConnection.setDoOutput(false);
            }
            final int responseCode = urlConnection.getResponseCode();
            if (!ListenerUtil.mutListener.listen(43204)) {
                if ((ListenerUtil.mutListener.listen(43203) ? ((ListenerUtil.mutListener.listen(43197) ? ((ListenerUtil.mutListener.listen(43191) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43190) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43189) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43188) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43187) ? (responseCode == 200) : (responseCode != 200)))))) || (ListenerUtil.mutListener.listen(43196) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43195) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43194) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43193) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43192) ? (responseCode == 201) : (responseCode != 201))))))) : ((ListenerUtil.mutListener.listen(43191) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43190) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43189) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43188) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43187) ? (responseCode == 200) : (responseCode != 200)))))) && (ListenerUtil.mutListener.listen(43196) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43195) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43194) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43193) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43192) ? (responseCode == 201) : (responseCode != 201)))))))) || (ListenerUtil.mutListener.listen(43202) ? (responseCode >= 204) : (ListenerUtil.mutListener.listen(43201) ? (responseCode <= 204) : (ListenerUtil.mutListener.listen(43200) ? (responseCode > 204) : (ListenerUtil.mutListener.listen(43199) ? (responseCode < 204) : (ListenerUtil.mutListener.listen(43198) ? (responseCode == 204) : (responseCode != 204))))))) : ((ListenerUtil.mutListener.listen(43197) ? ((ListenerUtil.mutListener.listen(43191) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43190) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43189) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43188) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43187) ? (responseCode == 200) : (responseCode != 200)))))) || (ListenerUtil.mutListener.listen(43196) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43195) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43194) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43193) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43192) ? (responseCode == 201) : (responseCode != 201))))))) : ((ListenerUtil.mutListener.listen(43191) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43190) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43189) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43188) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43187) ? (responseCode == 200) : (responseCode != 200)))))) && (ListenerUtil.mutListener.listen(43196) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43195) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43194) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43193) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43192) ? (responseCode == 201) : (responseCode != 201)))))))) && (ListenerUtil.mutListener.listen(43202) ? (responseCode >= 204) : (ListenerUtil.mutListener.listen(43201) ? (responseCode <= 204) : (ListenerUtil.mutListener.listen(43200) ? (responseCode > 204) : (ListenerUtil.mutListener.listen(43199) ? (responseCode < 204) : (ListenerUtil.mutListener.listen(43198) ? (responseCode == 204) : (responseCode != 204))))))))) {
                    throw new ThreemaException("Unable to delete backup. Response code: " + responseCode);
                }
            }
        } catch (IOException e) {
            throw new ThreemaException("IO Exception");
        } catch (IllegalArgumentException e) {
            throw new ThreemaException(e.getMessage());
        } finally {
            if (!ListenerUtil.mutListener.listen(43179)) {
                urlConnection.disconnect();
            }
        }
    }

    @Override
    public void restoreBackup(String identity, String password, ThreemaSafeServerInfo serverInfo) throws ThreemaException, IOException {
        if (!ListenerUtil.mutListener.listen(43208)) {
            if ((ListenerUtil.mutListener.listen(43207) ? ((ListenerUtil.mutListener.listen(43206) ? ((ListenerUtil.mutListener.listen(43205) ? (TestUtil.empty(password) && serverInfo == null) : (TestUtil.empty(password) || serverInfo == null)) && TestUtil.empty(identity)) : ((ListenerUtil.mutListener.listen(43205) ? (TestUtil.empty(password) && serverInfo == null) : (TestUtil.empty(password) || serverInfo == null)) || TestUtil.empty(identity))) && identity.length() != ProtocolDefines.IDENTITY_LEN) : ((ListenerUtil.mutListener.listen(43206) ? ((ListenerUtil.mutListener.listen(43205) ? (TestUtil.empty(password) && serverInfo == null) : (TestUtil.empty(password) || serverInfo == null)) && TestUtil.empty(identity)) : ((ListenerUtil.mutListener.listen(43205) ? (TestUtil.empty(password) && serverInfo == null) : (TestUtil.empty(password) || serverInfo == null)) || TestUtil.empty(identity))) || identity.length() != ProtocolDefines.IDENTITY_LEN))) {
                throw new ThreemaException("Illegal arguments");
            }
        }
        byte[] masterKey = deriveMasterKey(password, identity);
        if (!ListenerUtil.mutListener.listen(43209)) {
            if (masterKey == null) {
                throw new ThreemaException("Unable to derive master key");
            }
        }
        if (!ListenerUtil.mutListener.listen(43210)) {
            preferenceService.setThreemaSafeMasterKey(masterKey);
        }
        if (!ListenerUtil.mutListener.listen(43211)) {
            preferenceService.setThreemaSafeServerInfo(serverInfo);
        }
        URL serverUrl = serverInfo.getBackupUrl(getThreemaSafeBackupId());
        HttpsURLConnection urlConnection;
        try {
            urlConnection = (HttpsURLConnection) serverUrl.openConnection();
        } catch (IOException e) {
            throw new ThreemaException("Unable to connect to server");
        }
        byte[] threemaSafeEncryptedBackup;
        try {
            if (!ListenerUtil.mutListener.listen(43213)) {
                urlConnection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(serverUrl.getHost()));
            }
            if (!ListenerUtil.mutListener.listen(43214)) {
                urlConnection.setConnectTimeout(15000);
            }
            if (!ListenerUtil.mutListener.listen(43215)) {
                urlConnection.setReadTimeout(30000);
            }
            if (!ListenerUtil.mutListener.listen(43216)) {
                urlConnection.setRequestMethod("GET");
            }
            if (!ListenerUtil.mutListener.listen(43217)) {
                urlConnection.setRequestProperty("Accept", "application/octet-stream");
            }
            if (!ListenerUtil.mutListener.listen(43218)) {
                urlConnection.setRequestProperty(KEY_USER_AGENT, ProtocolStrings.USER_AGENT);
            }
            if (!ListenerUtil.mutListener.listen(43219)) {
                serverInfo.addAuthorization(urlConnection);
            }
            if (!ListenerUtil.mutListener.listen(43220)) {
                urlConnection.setDoOutput(false);
            }
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream())) {
                byte[] buf = new byte[16384];
                int nread;
                if (!ListenerUtil.mutListener.listen(43227)) {
                    {
                        long _loopCounter498 = 0;
                        while ((ListenerUtil.mutListener.listen(43226) ? ((nread = bis.read(buf)) >= 0) : (ListenerUtil.mutListener.listen(43225) ? ((nread = bis.read(buf)) <= 0) : (ListenerUtil.mutListener.listen(43224) ? ((nread = bis.read(buf)) < 0) : (ListenerUtil.mutListener.listen(43223) ? ((nread = bis.read(buf)) != 0) : (ListenerUtil.mutListener.listen(43222) ? ((nread = bis.read(buf)) == 0) : ((nread = bis.read(buf)) > 0))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter498", ++_loopCounter498);
                            if (!ListenerUtil.mutListener.listen(43221)) {
                                baos.write(buf, 0, nread);
                            }
                        }
                    }
                }
                threemaSafeEncryptedBackup = baos.toByteArray();
                final int responseCode = urlConnection.getResponseCode();
                if (!ListenerUtil.mutListener.listen(43233)) {
                    if ((ListenerUtil.mutListener.listen(43232) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43231) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43230) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43229) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43228) ? (responseCode == 200) : (responseCode != 200))))))) {
                        throw new ThreemaException("Server error: " + responseCode);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new ThreemaException(e.getMessage());
        } finally {
            if (!ListenerUtil.mutListener.listen(43212)) {
                urlConnection.disconnect();
            }
        }
        byte[] nonce = new byte[NaCl.NONCEBYTES];
        byte[] gzippedData = new byte[(ListenerUtil.mutListener.listen(43237) ? (threemaSafeEncryptedBackup.length % NaCl.NONCEBYTES) : (ListenerUtil.mutListener.listen(43236) ? (threemaSafeEncryptedBackup.length / NaCl.NONCEBYTES) : (ListenerUtil.mutListener.listen(43235) ? (threemaSafeEncryptedBackup.length * NaCl.NONCEBYTES) : (ListenerUtil.mutListener.listen(43234) ? (threemaSafeEncryptedBackup.length + NaCl.NONCEBYTES) : (threemaSafeEncryptedBackup.length - NaCl.NONCEBYTES)))))];
        if (!ListenerUtil.mutListener.listen(43238)) {
            System.arraycopy(threemaSafeEncryptedBackup, 0, nonce, 0, NaCl.NONCEBYTES);
        }
        if (!ListenerUtil.mutListener.listen(43243)) {
            System.arraycopy(threemaSafeEncryptedBackup, NaCl.NONCEBYTES, gzippedData, 0, (ListenerUtil.mutListener.listen(43242) ? (threemaSafeEncryptedBackup.length % NaCl.NONCEBYTES) : (ListenerUtil.mutListener.listen(43241) ? (threemaSafeEncryptedBackup.length / NaCl.NONCEBYTES) : (ListenerUtil.mutListener.listen(43240) ? (threemaSafeEncryptedBackup.length * NaCl.NONCEBYTES) : (ListenerUtil.mutListener.listen(43239) ? (threemaSafeEncryptedBackup.length + NaCl.NONCEBYTES) : (threemaSafeEncryptedBackup.length - NaCl.NONCEBYTES))))));
        }
        if (!ListenerUtil.mutListener.listen(43244)) {
            if (!NaCl.symmetricDecryptDataInplace(gzippedData, getThreemaSafeEncryptionKey(), nonce)) {
                throw new ThreemaException("Unable to decrypt");
            }
        }
        byte[] uncompressed = gZipUncompress(gzippedData);
        if (!ListenerUtil.mutListener.listen(43245)) {
            if (uncompressed == null) {
                throw new ThreemaException("Uncompress failed");
            }
        }
        String json;
        json = new String(uncompressed, StandardCharsets.UTF_8);
        if (!ListenerUtil.mutListener.listen(43246)) {
            parseJson(identity, json);
        }
        if (!ListenerUtil.mutListener.listen(43247)) {
            // successfully restored - update mdm settings config
            ThreemaSafeMDMConfig.getInstance().saveConfig(preferenceService);
        }
    }

    private void parseJson(String identity, String json) throws ThreemaException {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            if (!ListenerUtil.mutListener.listen(43248)) {
                parseInfo(jsonObject.getJSONObject(TAG_SAFE_INFO));
            }
        } catch (JSONException e) {
            throw new ThreemaException("Missing Info object or version mismatch");
        }
        try {
            if (!ListenerUtil.mutListener.listen(43249)) {
                parseUser(identity, jsonObject.getJSONObject(TAG_SAFE_USER));
            }
        } catch (IOException | JSONException e) {
            throw new ThreemaException("Unable to restore user");
        }
        try {
            if (!ListenerUtil.mutListener.listen(43250)) {
                parseSettings(jsonObject.getJSONObject(TAG_SAFE_SETTINGS));
            }
        } catch (JSONException e) {
        }
        try {
            if (!ListenerUtil.mutListener.listen(43251)) {
                parseContacts(jsonObject.getJSONArray(TAG_SAFE_CONTACTS));
            }
        } catch (JSONException e) {
            // no contacts - stop here as groups and distributions lists are of no use without contacts
            return;
        }
        try {
            if (!ListenerUtil.mutListener.listen(43252)) {
                parseGroups(jsonObject.getJSONArray(TAG_SAFE_GROUPS));
            }
        } catch (JSONException e) {
        }
        try {
            if (!ListenerUtil.mutListener.listen(43253)) {
                parseDistributionlists(jsonObject.getJSONArray(TAG_SAFE_DISTRIBUTIONLISTS));
            }
        } catch (JSONException e) {
        }
    }

    private void parseUser(String identity, JSONObject user) throws ThreemaException, IOException, JSONException {
        byte[] privateKey, publicKey;
        String encodedPrivateKey = user.getString(TAG_SAFE_USER_PRIVATE_KEY);
        if (!ListenerUtil.mutListener.listen(43254)) {
            if (TestUtil.empty(encodedPrivateKey)) {
                throw new ThreemaException("Invalid JSON");
            }
        }
        privateKey = Base64.decode(encodedPrivateKey);
        publicKey = NaCl.derivePublicKey(privateKey);
        try {
            if (!ListenerUtil.mutListener.listen(43255)) {
                userService.restoreIdentity(identity, privateKey, publicKey);
            }
        } catch (Exception e) {
            throw new ThreemaException("Unable to restore identity: " + e.getMessage());
        }
        String nickname = user.optString(TAG_SAFE_USER_NICKNAME, identity);
        ContactModel contactModel = contactService.getByIdentity(userService.getIdentity());
        if (!ListenerUtil.mutListener.listen(43285)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(43256)) {
                    userService.setPublicNickname(nickname);
                }
                boolean isLinksRestricted = false;
                if (!ListenerUtil.mutListener.listen(43266)) {
                    if (ConfigUtils.isWorkRestricted()) {
                        // if links have been set do not restore links if readonly profile is set to true and the user is unable to change or remove links
                        String stringPreset;
                        stringPreset = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__linked_email));
                        if (!ListenerUtil.mutListener.listen(43259)) {
                            if (stringPreset != null) {
                                if (!ListenerUtil.mutListener.listen(43257)) {
                                    isLinksRestricted = true;
                                }
                                if (!ListenerUtil.mutListener.listen(43258)) {
                                    doLink(TAG_SAFE_USER_LINK_TYPE_EMAIL, stringPreset);
                                }
                            }
                        }
                        stringPreset = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__linked_phone));
                        if (!ListenerUtil.mutListener.listen(43262)) {
                            if (stringPreset != null) {
                                if (!ListenerUtil.mutListener.listen(43260)) {
                                    isLinksRestricted = true;
                                }
                                if (!ListenerUtil.mutListener.listen(43261)) {
                                    doLink(TAG_SAFE_USER_LINK_TYPE_MOBILE, stringPreset);
                                }
                            }
                        }
                        // do not restore links if readonly profile is set to true and the user is unable to change or remove links later
                        Boolean booleanRestriction = AppRestrictionUtil.getBooleanRestriction(context.getString(R.string.restriction__readonly_profile));
                        if (!ListenerUtil.mutListener.listen(43265)) {
                            if ((ListenerUtil.mutListener.listen(43263) ? (booleanRestriction != null || booleanRestriction) : (booleanRestriction != null && booleanRestriction))) {
                                if (!ListenerUtil.mutListener.listen(43264)) {
                                    isLinksRestricted = true;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(43268)) {
                    if (!isLinksRestricted) {
                        if (!ListenerUtil.mutListener.listen(43267)) {
                            parseLinks(user.optJSONArray(TAG_SAFE_USER_LINKS));
                        }
                    }
                }
                String profilePic = user.optString(TAG_SAFE_USER_PROFILE_PIC, null);
                if (!ListenerUtil.mutListener.listen(43270)) {
                    if (profilePic != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(43269)) {
                                contactService.setAvatar(contactModel, Base64.decode(profilePic));
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                JSONArray profilePicRelease = user.optJSONArray(TAG_SAFE_USER_PROFILE_PIC_RELEASE);
                if (!ListenerUtil.mutListener.listen(43284)) {
                    if (profilePicRelease != null) {
                        if (!ListenerUtil.mutListener.listen(43271)) {
                            preferenceService.setProfilePicRelease(PROFILEPIC_RELEASE_SOME);
                        }
                        if (!ListenerUtil.mutListener.listen(43283)) {
                            {
                                long _loopCounter499 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(43282) ? (i >= profilePicRelease.length()) : (ListenerUtil.mutListener.listen(43281) ? (i <= profilePicRelease.length()) : (ListenerUtil.mutListener.listen(43280) ? (i > profilePicRelease.length()) : (ListenerUtil.mutListener.listen(43279) ? (i != profilePicRelease.length()) : (ListenerUtil.mutListener.listen(43278) ? (i == profilePicRelease.length()) : (i < profilePicRelease.length())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter499", ++_loopCounter499);
                                    String id = profilePicRelease.getString(i);
                                    if (!ListenerUtil.mutListener.listen(43273)) {
                                        if (id == null) {
                                            if (!ListenerUtil.mutListener.listen(43272)) {
                                                preferenceService.setProfilePicRelease(PROFILEPIC_RELEASE_NOBODY);
                                            }
                                            break;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(43275)) {
                                        if (PROFILE_PIC_RELEASE_ALL_PLACEHOLDER.equals(id)) {
                                            if (!ListenerUtil.mutListener.listen(43274)) {
                                                preferenceService.setProfilePicRelease(PROFILEPIC_RELEASE_EVERYONE);
                                            }
                                            break;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(43277)) {
                                        if (id.length() == ProtocolDefines.IDENTITY_LEN) {
                                            if (!ListenerUtil.mutListener.listen(43276)) {
                                                profilePicRecipientsService.add(id);
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

    private void doLink(String type, String value) {
        if (!ListenerUtil.mutListener.listen(43286)) {
            if (TestUtil.empty(type, value))
                return;
        }
        if (!ListenerUtil.mutListener.listen(43289)) {
            switch(type) {
                case TAG_SAFE_USER_LINK_TYPE_EMAIL:
                    try {
                        if (!ListenerUtil.mutListener.listen(43287)) {
                            userService.linkWithEmail(value);
                        }
                    } catch (Exception e) {
                    }
                    break;
                case TAG_SAFE_USER_LINK_TYPE_MOBILE:
                    try {
                        if (!ListenerUtil.mutListener.listen(43288)) {
                            // should always be a fully qualified phone number starting with a "+"
                            userService.linkWithMobileNumber(value.startsWith("+") ? value : "+" + value);
                        }
                    } catch (Exception e) {
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void parseLink(JSONObject link) {
        String type = link.optString(TAG_SAFE_USER_LINK_TYPE);
        String value = link.optString(TAG_SAFE_USER_LINK_VALUE);
        if (!ListenerUtil.mutListener.listen(43290)) {
            doLink(type, value);
        }
    }

    private void parseLinks(JSONArray links) {
        if (!ListenerUtil.mutListener.listen(43291)) {
            if (links == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(43299)) {
            {
                long _loopCounter500 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43298) ? (i >= links.length()) : (ListenerUtil.mutListener.listen(43297) ? (i <= links.length()) : (ListenerUtil.mutListener.listen(43296) ? (i > links.length()) : (ListenerUtil.mutListener.listen(43295) ? (i != links.length()) : (ListenerUtil.mutListener.listen(43294) ? (i == links.length()) : (i < links.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter500", ++_loopCounter500);
                    JSONObject link = links.optJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(43293)) {
                        if (link != null) {
                            if (!ListenerUtil.mutListener.listen(43292)) {
                                parseLink(link);
                            }
                        }
                    }
                }
            }
        }
    }

    private void parseContacts(JSONArray contacts) {
        if (!ListenerUtil.mutListener.listen(43300)) {
            if (contacts == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(43301)) {
            if (databaseServiceNew == null)
                return;
        }
        ContactModelFactory contactModelFactory = databaseServiceNew.getContactModelFactory();
        ArrayList<String> identities = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(43308)) {
            {
                long _loopCounter501 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43307) ? (i >= contacts.length()) : (ListenerUtil.mutListener.listen(43306) ? (i <= contacts.length()) : (ListenerUtil.mutListener.listen(43305) ? (i > contacts.length()) : (ListenerUtil.mutListener.listen(43304) ? (i != contacts.length()) : (ListenerUtil.mutListener.listen(43303) ? (i == contacts.length()) : (i < contacts.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter501", ++_loopCounter501);
                    try {
                        if (!ListenerUtil.mutListener.listen(43302)) {
                            identities.add(contacts.getJSONObject(i).getString(TAG_SAFE_CONTACT_IDENTITY));
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43314)) {
            if ((ListenerUtil.mutListener.listen(43313) ? (identities.size() >= 0) : (ListenerUtil.mutListener.listen(43312) ? (identities.size() <= 0) : (ListenerUtil.mutListener.listen(43311) ? (identities.size() > 0) : (ListenerUtil.mutListener.listen(43310) ? (identities.size() < 0) : (ListenerUtil.mutListener.listen(43309) ? (identities.size() != 0) : (identities.size() == 0))))))) {
                return;
            }
        }
        ArrayList<APIConnector.FetchIdentityResult> results;
        try {
            results = this.apiConnector.fetchIdentities(identities);
        } catch (Exception e) {
            return;
        }
        if (!ListenerUtil.mutListener.listen(43346)) {
            {
                long _loopCounter502 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43345) ? (i >= contacts.length()) : (ListenerUtil.mutListener.listen(43344) ? (i <= contacts.length()) : (ListenerUtil.mutListener.listen(43343) ? (i > contacts.length()) : (ListenerUtil.mutListener.listen(43342) ? (i != contacts.length()) : (ListenerUtil.mutListener.listen(43341) ? (i == contacts.length()) : (i < contacts.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter502", ++_loopCounter502);
                    try {
                        JSONObject contact = contacts.getJSONObject(i);
                        String identity = contact.getString(TAG_SAFE_CONTACT_IDENTITY);
                        String publicKey = contact.optString(TAG_SAFE_CONTACT_PUBLIC_KEY);
                        VerificationLevel verificationLevel = VerificationLevel.from(contact.optInt(TAG_SAFE_CONTACT_VERIFICATION_LEVEL, VerificationLevel.UNVERIFIED.getCode()));
                        APIConnector.FetchIdentityResult result = apiConnector.getFetchResultByIdentity(results, identity);
                        if (!ListenerUtil.mutListener.listen(43340)) {
                            if (result != null) {
                                ContactModel contactModel = contactService.getByIdentity(result.identity);
                                if (!ListenerUtil.mutListener.listen(43339)) {
                                    if (contactModel == null) {
                                        if (!ListenerUtil.mutListener.listen(43321)) {
                                            // create a new contact
                                            if ((ListenerUtil.mutListener.listen(43316) ? (verificationLevel == VerificationLevel.FULLY_VERIFIED || !TestUtil.empty(publicKey)) : (verificationLevel == VerificationLevel.FULLY_VERIFIED && !TestUtil.empty(publicKey)))) {
                                                if (!ListenerUtil.mutListener.listen(43319)) {
                                                    // use the public key from the backup
                                                    contactModel = new ContactModel(identity, Base64.decode(publicKey));
                                                }
                                                if (!ListenerUtil.mutListener.listen(43320)) {
                                                    contactModel.setVerificationLevel(verificationLevel);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(43317)) {
                                                    // use the fetched key
                                                    contactModel = new ContactModel(result.identity, result.publicKey);
                                                }
                                                if (!ListenerUtil.mutListener.listen(43318)) {
                                                    contactModel.setVerificationLevel(VerificationLevel.UNVERIFIED);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(43322)) {
                                            contactModel.setFeatureMask(result.featureMask);
                                        }
                                        if (!ListenerUtil.mutListener.listen(43323)) {
                                            contactModel.setType(result.type);
                                        }
                                        if (!ListenerUtil.mutListener.listen(43327)) {
                                            switch(result.state) {
                                                case IdentityState.ACTIVE:
                                                    if (!ListenerUtil.mutListener.listen(43324)) {
                                                        contactModel.setState(ContactModel.State.ACTIVE);
                                                    }
                                                    break;
                                                case IdentityState.INACTIVE:
                                                    if (!ListenerUtil.mutListener.listen(43325)) {
                                                        contactModel.setState(ContactModel.State.INACTIVE);
                                                    }
                                                    break;
                                                case IdentityState.INVALID:
                                                    if (!ListenerUtil.mutListener.listen(43326)) {
                                                        contactModel.setState(ContactModel.State.INVALID);
                                                    }
                                                    break;
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(43328)) {
                                            contactModel.setIsWork(contact.optBoolean(TAG_SAFE_CONTACT_WORK_VERIFIED));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43329)) {
                                            contactModel.setFirstName(contact.optString(TAG_SAFE_CONTACT_FIRST_NAME));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43330)) {
                                            contactModel.setLastName(contact.optString(TAG_SAFE_CONTACT_LAST_NAME));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43331)) {
                                            contactModel.setPublicNickName(contact.optString(TAG_SAFE_CONTACT_NICKNAME));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43332)) {
                                            contactModel.setIsHidden(contact.optBoolean(TAG_SAFE_CONTACT_HIDDEN, false));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43333)) {
                                            contactModel.setDateCreated(new Date(contact.optLong(TAG_SAFE_CONTACT_CREATED_AT, System.currentTimeMillis())));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43334)) {
                                            contactModel.setColor(ColorUtil.getInstance().getRecordColor((int) contactModelFactory.count()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(43335)) {
                                            contactModel.setIsRestored(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(43336)) {
                                            contactModelFactory.createOrUpdate(contactModel);
                                        }
                                        if (!ListenerUtil.mutListener.listen(43338)) {
                                            if (contact.optBoolean(TAG_SAFE_CONTACT_PRIVATE, false)) {
                                                if (!ListenerUtil.mutListener.listen(43337)) {
                                                    hiddenChatsListService.add(contactService.getUniqueIdString(contactModel), DeadlineListService.DEADLINE_INDEFINITE);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (JSONException | IOException e) {
                        if (!ListenerUtil.mutListener.listen(43315)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }
        }
    }

    private void parseGroups(JSONArray groups) {
        if (!ListenerUtil.mutListener.listen(43347)) {
            if (groups == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(43348)) {
            if (databaseServiceNew == null)
                return;
        }
        final GroupService groupService;
        try {
            groupService = ThreemaApplication.getServiceManager().getGroupService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43349)) {
                logger.error("Exception", e);
            }
            return;
        }
        GroupModelFactory groupModelFactory = databaseServiceNew.getGroupModelFactory();
        GroupMemberModelFactory groupMemberModelFactory = databaseServiceNew.getGroupMemberModelFactory();
        if (!ListenerUtil.mutListener.listen(43382)) {
            {
                long _loopCounter504 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43381) ? (i >= groups.length()) : (ListenerUtil.mutListener.listen(43380) ? (i <= groups.length()) : (ListenerUtil.mutListener.listen(43379) ? (i > groups.length()) : (ListenerUtil.mutListener.listen(43378) ? (i != groups.length()) : (ListenerUtil.mutListener.listen(43377) ? (i == groups.length()) : (i < groups.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter504", ++_loopCounter504);
                    try {
                        JSONObject group = groups.getJSONObject(i);
                        String creatorIdentity = group.getString(TAG_SAFE_GROUP_CREATOR);
                        if (!ListenerUtil.mutListener.listen(43376)) {
                            // do not create group if creator no longer exists (i.e. was revoked)
                            if (contactService.getByIdentity(creatorIdentity) != null) {
                                GroupModel groupModel = new GroupModel();
                                long createdAt = group.optLong(TAG_SAFE_GROUP_CREATED_AT, 0L);
                                if (!ListenerUtil.mutListener.listen(43350)) {
                                    groupModel.setApiGroupId(group.getString(TAG_SAFE_GROUP_ID).toLowerCase());
                                }
                                if (!ListenerUtil.mutListener.listen(43351)) {
                                    groupModel.setCreatorIdentity(creatorIdentity);
                                }
                                if (!ListenerUtil.mutListener.listen(43352)) {
                                    groupModel.setName(group.optString(TAG_SAFE_GROUP_NAME, ""));
                                }
                                if (!ListenerUtil.mutListener.listen(43353)) {
                                    groupModel.setCreatedAt(new Date(createdAt));
                                }
                                if (!ListenerUtil.mutListener.listen(43354)) {
                                    groupModel.setDeleted(group.getBoolean(TAG_SAFE_GROUP_DELETED));
                                }
                                if (!ListenerUtil.mutListener.listen(43355)) {
                                    groupModel.setSynchronizedAt(new Date(0));
                                }
                                if (!ListenerUtil.mutListener.listen(43375)) {
                                    if (groupModelFactory.create(groupModel)) {
                                        if (!ListenerUtil.mutListener.listen(43357)) {
                                            if (group.optBoolean(TAG_SAFE_GROUP_PRIVATE, false)) {
                                                if (!ListenerUtil.mutListener.listen(43356)) {
                                                    hiddenChatsListService.add(groupService.getUniqueIdString(groupModel), DeadlineListService.DEADLINE_INDEFINITE);
                                                }
                                            }
                                        }
                                        JSONArray members = group.getJSONArray(TAG_SAFE_GROUP_MEMBERS);
                                        if (!ListenerUtil.mutListener.listen(43370)) {
                                            {
                                                long _loopCounter503 = 0;
                                                for (int j = 0; (ListenerUtil.mutListener.listen(43369) ? (j >= members.length()) : (ListenerUtil.mutListener.listen(43368) ? (j <= members.length()) : (ListenerUtil.mutListener.listen(43367) ? (j > members.length()) : (ListenerUtil.mutListener.listen(43366) ? (j != members.length()) : (ListenerUtil.mutListener.listen(43365) ? (j == members.length()) : (j < members.length())))))); j++) {
                                                    ListenerUtil.loopListener.listen("_loopCounter503", ++_loopCounter503);
                                                    String identity = members.getString(j);
                                                    if (!ListenerUtil.mutListener.listen(43364)) {
                                                        if (!TestUtil.empty(identity)) {
                                                            if (!ListenerUtil.mutListener.listen(43359)) {
                                                                if (contactService.getByIdentity(identity) == null) {
                                                                    // fetch group contact if not in contact list
                                                                    try {
                                                                        if (!ListenerUtil.mutListener.listen(43358)) {
                                                                            contactService.createContactByIdentity(identity, true, true);
                                                                        }
                                                                    } catch (InvalidEntryException | EntryAlreadyExistsException | PolicyViolationException e) {
                                                                        // do not add as group member if contact cannot be created
                                                                        continue;
                                                                    }
                                                                }
                                                            }
                                                            GroupMemberModel groupMemberModel = new GroupMemberModel();
                                                            if (!ListenerUtil.mutListener.listen(43360)) {
                                                                groupMemberModel.setGroupId(groupModel.getId());
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(43361)) {
                                                                groupMemberModel.setIdentity(identity);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(43362)) {
                                                                groupMemberModel.setActive(true);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(43363)) {
                                                                groupMemberModelFactory.create(groupMemberModel);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(43374)) {
                                            if (!groupModel.isDeleted()) {
                                                if (!ListenerUtil.mutListener.listen(43373)) {
                                                    if (groupService.isGroupOwner(groupModel)) {
                                                        if (!ListenerUtil.mutListener.listen(43372)) {
                                                            groupService.sendSync(groupModel);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(43371)) {
                                                            groupService.requestSync(creatorIdentity, new GroupId(Utils.hexStringToByteArray(groupModel.getApiGroupId())));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (JSONException | NullPointerException | ThreemaException e) {
                    }
                }
            }
        }
    }

    private void parseDistributionlists(JSONArray distributionlists) {
        if (!ListenerUtil.mutListener.listen(43383)) {
            if (distributionlists == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(43384)) {
            if (databaseServiceNew == null)
                return;
        }
        final DistributionListService distributionListService;
        try {
            distributionListService = ThreemaApplication.getServiceManager().getDistributionListService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43385)) {
                logger.error("Exception", e);
            }
            return;
        }
        DistributionListMemberModelFactory distributionListMemberModelFactory = databaseServiceNew.getDistributionListMemberModelFactory();
        if (!ListenerUtil.mutListener.listen(43409)) {
            {
                long _loopCounter506 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43408) ? (i >= distributionlists.length()) : (ListenerUtil.mutListener.listen(43407) ? (i <= distributionlists.length()) : (ListenerUtil.mutListener.listen(43406) ? (i > distributionlists.length()) : (ListenerUtil.mutListener.listen(43405) ? (i != distributionlists.length()) : (ListenerUtil.mutListener.listen(43404) ? (i == distributionlists.length()) : (i < distributionlists.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter506", ++_loopCounter506);
                    try {
                        JSONObject distributionlist = distributionlists.getJSONObject(i);
                        DistributionListModel distributionListModel = new DistributionListModel();
                        long createdAt = distributionlist.optLong(TAG_SAFE_DISTRIBUTIONLIST_CREATED_AT, 0L);
                        if (!ListenerUtil.mutListener.listen(43386)) {
                            distributionListModel.setName(distributionlist.getString(TAG_SAFE_DISTRIBUTIONLIST_NAME));
                        }
                        if (!ListenerUtil.mutListener.listen(43387)) {
                            distributionListModel.setCreatedAt(new Date(createdAt));
                        }
                        if (!ListenerUtil.mutListener.listen(43388)) {
                            databaseServiceNew.getDistributionListModelFactory().create(distributionListModel);
                        }
                        if (!ListenerUtil.mutListener.listen(43390)) {
                            if (distributionlist.optBoolean(TAG_SAFE_DISTRIBUTIONLIST_PRIVATE, false)) {
                                if (!ListenerUtil.mutListener.listen(43389)) {
                                    hiddenChatsListService.add(distributionListService.getUniqueIdString(distributionListModel), DeadlineListService.DEADLINE_INDEFINITE);
                                }
                            }
                        }
                        JSONArray members = distributionlist.getJSONArray(TAG_SAFE_DISTRIBUTIONLIST_MEMBERS);
                        if (!ListenerUtil.mutListener.listen(43403)) {
                            {
                                long _loopCounter505 = 0;
                                for (int j = 0; (ListenerUtil.mutListener.listen(43402) ? (j >= members.length()) : (ListenerUtil.mutListener.listen(43401) ? (j <= members.length()) : (ListenerUtil.mutListener.listen(43400) ? (j > members.length()) : (ListenerUtil.mutListener.listen(43399) ? (j != members.length()) : (ListenerUtil.mutListener.listen(43398) ? (j == members.length()) : (j < members.length())))))); j++) {
                                    ListenerUtil.loopListener.listen("_loopCounter505", ++_loopCounter505);
                                    String identity = members.getString(j);
                                    if (!ListenerUtil.mutListener.listen(43397)) {
                                        if (!TestUtil.empty(identity)) {
                                            if (!ListenerUtil.mutListener.listen(43392)) {
                                                if (contactService.getByIdentity(identity) == null) {
                                                    // fetch contact if not in contact list
                                                    try {
                                                        if (!ListenerUtil.mutListener.listen(43391)) {
                                                            contactService.createContactByIdentity(identity, true, true);
                                                        }
                                                    } catch (InvalidEntryException | EntryAlreadyExistsException | PolicyViolationException e) {
                                                        // do not add as distribution list member if contact cannot be created
                                                        continue;
                                                    }
                                                }
                                            }
                                            DistributionListMemberModel distributionListMemberModel = new DistributionListMemberModel();
                                            if (!ListenerUtil.mutListener.listen(43393)) {
                                                distributionListMemberModel.setIdentity(identity);
                                            }
                                            if (!ListenerUtil.mutListener.listen(43394)) {
                                                distributionListMemberModel.setDistributionListId(distributionListModel.getId());
                                            }
                                            if (!ListenerUtil.mutListener.listen(43395)) {
                                                distributionListMemberModel.setActive(true);
                                            }
                                            if (!ListenerUtil.mutListener.listen(43396)) {
                                                distributionListMemberModelFactory.create(distributionListMemberModel);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (JSONException | NullPointerException e) {
                    }
                }
            }
        }
    }

    private void parseSettings(JSONObject settings) {
        boolean syncContactsRestricted = false;
        if (!ListenerUtil.mutListener.listen(43413)) {
            if (ConfigUtils.isWorkRestricted()) {
                Boolean booleanPreset = AppRestrictionUtil.getBooleanRestriction(context.getString(R.string.restriction__contact_sync));
                if (!ListenerUtil.mutListener.listen(43412)) {
                    if (booleanPreset != null) {
                        if (!ListenerUtil.mutListener.listen(43410)) {
                            preferenceService.setSyncContacts(booleanPreset);
                        }
                        if (!ListenerUtil.mutListener.listen(43411)) {
                            syncContactsRestricted = true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43415)) {
            if (!syncContactsRestricted) {
                if (!ListenerUtil.mutListener.listen(43414)) {
                    preferenceService.setSyncContacts(settings.optBoolean(TAG_SAFE_SETTINGS_SYNC_CONTACTS, false));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43416)) {
            preferenceService.setBlockUnkown(settings.optBoolean(TAG_SAFE_SETTINGS_BLOCK_UNKNOWN, false));
        }
        if (!ListenerUtil.mutListener.listen(43417)) {
            preferenceService.setTypingIndicator(settings.optBoolean(TAG_SAFE_SETTINGS_SEND_TYPING, true));
        }
        if (!ListenerUtil.mutListener.listen(43418)) {
            preferenceService.setReadReceipts(settings.optBoolean(TAG_SAFE_SETTINGS_READ_RECEIPTS, true));
        }
        if (!ListenerUtil.mutListener.listen(43419)) {
            preferenceService.setVoipEnabled(settings.optBoolean(TAG_SAFE_SETTINGS_THREEMA_CALLS, true));
        }
        if (!ListenerUtil.mutListener.listen(43420)) {
            preferenceService.setForceTURN(settings.optBoolean(TAG_SAFE_SETTINGS_RELAY_THREEMA_CALLS, false));
        }
        if (!ListenerUtil.mutListener.listen(43421)) {
            preferenceService.setDisableScreenshots(settings.optBoolean(TAG_SAFE_SETTINGS_DISABLE_SCREENSHOTS, false));
        }
        if (!ListenerUtil.mutListener.listen(43422)) {
            preferenceService.setIncognitoKeyboard(settings.optBoolean(TAG_SAFE_SETTINGS_INCOGNITO_KEAYBOARD, false));
        }
        if (!ListenerUtil.mutListener.listen(43423)) {
            setSettingsBlockedContacts(settings.optJSONArray(TAG_SAFE_SETTINGS_BLOCKED_CONTACTS));
        }
        if (!ListenerUtil.mutListener.listen(43424)) {
            setSettingsSyncExcluded(settings.optJSONArray(TAG_SAFE_SETTINGS_SYNC_EXCLUDED_CONTACTS));
        }
        if (!ListenerUtil.mutListener.listen(43425)) {
            setSettingsRecentEmojis(settings.optJSONArray(TAG_SAFE_SETTINGS_RECENT_EMOJIS));
        }
    }

    private void parseInfo(JSONObject info) throws ThreemaException, JSONException {
        int version = info.getInt(TAG_SAFE_INFO_VERSION);
        if (!ListenerUtil.mutListener.listen(43431)) {
            if ((ListenerUtil.mutListener.listen(43430) ? (version >= PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(43429) ? (version <= PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(43428) ? (version < PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(43427) ? (version != PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(43426) ? (version == PROTOCOL_VERSION) : (version > PROTOCOL_VERSION))))))) {
                throw new ThreemaException(context.getResources().getString(R.string.safe_version_mismatch));
            }
        }
    }

    /**
     *  Search a Threema ID by phone number and/or email address.
     *  @param phone
     *  @param email
     *  @return ArrayList of matching Threema IDs, null if none was found
     */
    @Override
    public ArrayList<String> searchID(String phone, String email) {
        if (!ListenerUtil.mutListener.listen(43442)) {
            if ((ListenerUtil.mutListener.listen(43432) ? (phone != null && email != null) : (phone != null || email != null))) {
                Map<String, Object> phoneMap = new HashMap<String, Object>() {

                    {
                        if (!ListenerUtil.mutListener.listen(43433)) {
                            put(phone, null);
                        }
                    }
                };
                Map<String, Object> emailMap = new HashMap<String, Object>() {

                    {
                        if (!ListenerUtil.mutListener.listen(43434)) {
                            put(email, null);
                        }
                    }
                };
                try {
                    Map<String, APIConnector.MatchIdentityResult> results = apiConnector.matchIdentities(emailMap, phoneMap, localeService.getCountryIsoCode(), true, identityStore, null);
                    if (!ListenerUtil.mutListener.listen(43441)) {
                        if ((ListenerUtil.mutListener.listen(43440) ? (results.size() >= 0) : (ListenerUtil.mutListener.listen(43439) ? (results.size() <= 0) : (ListenerUtil.mutListener.listen(43438) ? (results.size() < 0) : (ListenerUtil.mutListener.listen(43437) ? (results.size() != 0) : (ListenerUtil.mutListener.listen(43436) ? (results.size() == 0) : (results.size() > 0))))))) {
                            return new ArrayList<>(results.keySet());
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(43435)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void launchForcedPasswordDialog(Activity activity) {
        // ask user for a new password
        Intent intent = new Intent(activity, ThreemaSafeConfigureActivity.class);
        if (!ListenerUtil.mutListener.listen(43443)) {
            intent.putExtra(EXTRA_WORK_FORCE_PASSWORD, true);
        }
        if (!ListenerUtil.mutListener.listen(43444)) {
            activity.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(43445)) {
            activity.overridePendingTransition(R.anim.slide_in_right_short, R.anim.slide_out_left_short);
        }
    }

    private void uploadData(ThreemaSafeServerInfo serverInfo, byte[] data) throws ThreemaException {
        URL serverUrl = serverInfo.getBackupUrl(getThreemaSafeBackupId());
        HttpsURLConnection urlConnection;
        try {
            urlConnection = (HttpsURLConnection) serverUrl.openConnection();
        } catch (IOException e) {
            throw new ThreemaException("Unable to connect to server");
        }
        try {
            if (!ListenerUtil.mutListener.listen(43447)) {
                urlConnection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(serverUrl.getHost()));
            }
            if (!ListenerUtil.mutListener.listen(43448)) {
                urlConnection.setConnectTimeout(15000);
            }
            if (!ListenerUtil.mutListener.listen(43449)) {
                urlConnection.setReadTimeout(30000);
            }
            if (!ListenerUtil.mutListener.listen(43450)) {
                urlConnection.setRequestMethod("PUT");
            }
            if (!ListenerUtil.mutListener.listen(43451)) {
                urlConnection.setRequestProperty("Content-Type", "application/octet-stream");
            }
            if (!ListenerUtil.mutListener.listen(43452)) {
                urlConnection.setRequestProperty(KEY_USER_AGENT, ProtocolStrings.USER_AGENT);
            }
            if (!ListenerUtil.mutListener.listen(43453)) {
                serverInfo.addAuthorization(urlConnection);
            }
            if (!ListenerUtil.mutListener.listen(43454)) {
                urlConnection.setDoOutput(true);
            }
            if (!ListenerUtil.mutListener.listen(43455)) {
                urlConnection.setDoInput(true);
            }
            if (!ListenerUtil.mutListener.listen(43456)) {
                urlConnection.setFixedLengthStreamingMode(data.length);
            }
            try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
                BufferedOutputStream bos = new BufferedOutputStream(urlConnection.getOutputStream())) {
                byte[] buf = new byte[16384];
                int nread;
                if (!ListenerUtil.mutListener.listen(43463)) {
                    {
                        long _loopCounter507 = 0;
                        while ((ListenerUtil.mutListener.listen(43462) ? ((nread = bis.read(buf)) >= 0) : (ListenerUtil.mutListener.listen(43461) ? ((nread = bis.read(buf)) <= 0) : (ListenerUtil.mutListener.listen(43460) ? ((nread = bis.read(buf)) < 0) : (ListenerUtil.mutListener.listen(43459) ? ((nread = bis.read(buf)) != 0) : (ListenerUtil.mutListener.listen(43458) ? ((nread = bis.read(buf)) == 0) : ((nread = bis.read(buf)) > 0))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter507", ++_loopCounter507);
                            if (!ListenerUtil.mutListener.listen(43457)) {
                                bos.write(buf, 0, nread);
                            }
                        }
                    }
                }
            }
            final int responseCode = urlConnection.getResponseCode();
            if (!ListenerUtil.mutListener.listen(43465)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(43464)) {
                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(context, "ThreemaSafe response code: " + responseCode, Toast.LENGTH_LONG).show());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(43483)) {
                if ((ListenerUtil.mutListener.listen(43482) ? ((ListenerUtil.mutListener.listen(43476) ? ((ListenerUtil.mutListener.listen(43470) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43469) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43468) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43467) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43466) ? (responseCode == 200) : (responseCode != 200)))))) || (ListenerUtil.mutListener.listen(43475) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43474) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43473) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43472) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43471) ? (responseCode == 201) : (responseCode != 201))))))) : ((ListenerUtil.mutListener.listen(43470) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43469) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43468) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43467) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43466) ? (responseCode == 200) : (responseCode != 200)))))) && (ListenerUtil.mutListener.listen(43475) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43474) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43473) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43472) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43471) ? (responseCode == 201) : (responseCode != 201)))))))) || (ListenerUtil.mutListener.listen(43481) ? (responseCode >= 204) : (ListenerUtil.mutListener.listen(43480) ? (responseCode <= 204) : (ListenerUtil.mutListener.listen(43479) ? (responseCode > 204) : (ListenerUtil.mutListener.listen(43478) ? (responseCode < 204) : (ListenerUtil.mutListener.listen(43477) ? (responseCode == 204) : (responseCode != 204))))))) : ((ListenerUtil.mutListener.listen(43476) ? ((ListenerUtil.mutListener.listen(43470) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43469) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43468) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43467) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43466) ? (responseCode == 200) : (responseCode != 200)))))) || (ListenerUtil.mutListener.listen(43475) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43474) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43473) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43472) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43471) ? (responseCode == 201) : (responseCode != 201))))))) : ((ListenerUtil.mutListener.listen(43470) ? (responseCode >= 200) : (ListenerUtil.mutListener.listen(43469) ? (responseCode <= 200) : (ListenerUtil.mutListener.listen(43468) ? (responseCode > 200) : (ListenerUtil.mutListener.listen(43467) ? (responseCode < 200) : (ListenerUtil.mutListener.listen(43466) ? (responseCode == 200) : (responseCode != 200)))))) && (ListenerUtil.mutListener.listen(43475) ? (responseCode >= 201) : (ListenerUtil.mutListener.listen(43474) ? (responseCode <= 201) : (ListenerUtil.mutListener.listen(43473) ? (responseCode > 201) : (ListenerUtil.mutListener.listen(43472) ? (responseCode < 201) : (ListenerUtil.mutListener.listen(43471) ? (responseCode == 201) : (responseCode != 201)))))))) && (ListenerUtil.mutListener.listen(43481) ? (responseCode >= 204) : (ListenerUtil.mutListener.listen(43480) ? (responseCode <= 204) : (ListenerUtil.mutListener.listen(43479) ? (responseCode > 204) : (ListenerUtil.mutListener.listen(43478) ? (responseCode < 204) : (ListenerUtil.mutListener.listen(43477) ? (responseCode == 204) : (responseCode != 204))))))))) {
                    throw new ThreemaException("Server error: " + responseCode);
                }
            }
        } catch (IOException e) {
            throw new ThreemaException("HTTPS IO Exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ThreemaException(e.getMessage());
        } finally {
            if (!ListenerUtil.mutListener.listen(43446)) {
                urlConnection.disconnect();
            }
        }
    }

    private byte[] gZipCompress(byte[] uncompressedBytes) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(uncompressedBytes.length);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            if (!ListenerUtil.mutListener.listen(43484)) {
                gzipOutputStream.write(uncompressedBytes);
            }
            if (!ListenerUtil.mutListener.listen(43485)) {
                gzipOutputStream.close();
            }
            byte[] compressedBytes = outputStream.toByteArray();
            if (!ListenerUtil.mutListener.listen(43486)) {
                outputStream.close();
            }
            return compressedBytes;
        } catch (Exception e) {
            return null;
        }
    }

    private byte[] gZipUncompress(byte[] compressedBytes) {
        byte[] buffer = new byte[16384];
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedBytes));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            if (!ListenerUtil.mutListener.listen(43493)) {
                {
                    long _loopCounter508 = 0;
                    while ((ListenerUtil.mutListener.listen(43492) ? ((len = gzipInputStream.read(buffer)) >= 0) : (ListenerUtil.mutListener.listen(43491) ? ((len = gzipInputStream.read(buffer)) <= 0) : (ListenerUtil.mutListener.listen(43490) ? ((len = gzipInputStream.read(buffer)) < 0) : (ListenerUtil.mutListener.listen(43489) ? ((len = gzipInputStream.read(buffer)) != 0) : (ListenerUtil.mutListener.listen(43488) ? ((len = gzipInputStream.read(buffer)) == 0) : ((len = gzipInputStream.read(buffer)) > 0))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter508", ++_loopCounter508);
                        if (!ListenerUtil.mutListener.listen(43487)) {
                            outputStream.write(buffer, 0, len);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(43494)) {
                gzipInputStream.close();
            }
            byte[] uncompressedBytes = outputStream.toByteArray();
            if (!ListenerUtil.mutListener.listen(43495)) {
                outputStream.close();
            }
            return uncompressedBytes;
        } catch (Exception e) {
            return null;
        }
    }

    private JSONObject getLink(String type, String value) throws JSONException {
        JSONObject link = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43496)) {
            link.put(TAG_SAFE_USER_LINK_TYPE, type);
        }
        if (!ListenerUtil.mutListener.listen(43497)) {
            link.put(TAG_SAFE_USER_LINK_VALUE, value);
        }
        return link;
    }

    private JSONArray getLinks() throws JSONException {
        JSONArray linksArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43506)) {
            // currently, there's only one set of links
            if (userService.getMobileLinkingState() == UserService.LinkingState_LINKED) {
                String linkedMobile = userService.getLinkedMobileE164();
                if (!ListenerUtil.mutListener.listen(43505)) {
                    if (linkedMobile != null) {
                        if (!ListenerUtil.mutListener.listen(43504)) {
                            // make sure + is stripped from number
                            linksArray.put(getLink(TAG_SAFE_USER_LINK_TYPE_MOBILE, (ListenerUtil.mutListener.listen(43503) ? ((ListenerUtil.mutListener.listen(43502) ? (linkedMobile.length() >= 1) : (ListenerUtil.mutListener.listen(43501) ? (linkedMobile.length() <= 1) : (ListenerUtil.mutListener.listen(43500) ? (linkedMobile.length() < 1) : (ListenerUtil.mutListener.listen(43499) ? (linkedMobile.length() != 1) : (ListenerUtil.mutListener.listen(43498) ? (linkedMobile.length() == 1) : (linkedMobile.length() > 1)))))) || linkedMobile.startsWith("+")) : ((ListenerUtil.mutListener.listen(43502) ? (linkedMobile.length() >= 1) : (ListenerUtil.mutListener.listen(43501) ? (linkedMobile.length() <= 1) : (ListenerUtil.mutListener.listen(43500) ? (linkedMobile.length() < 1) : (ListenerUtil.mutListener.listen(43499) ? (linkedMobile.length() != 1) : (ListenerUtil.mutListener.listen(43498) ? (linkedMobile.length() == 1) : (linkedMobile.length() > 1)))))) && linkedMobile.startsWith("+"))) ? linkedMobile.substring(1) : linkedMobile));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43509)) {
            if (userService.getEmailLinkingState() == UserService.LinkingState_LINKED) {
                String linkedEmail = userService.getLinkedEmail();
                if (!ListenerUtil.mutListener.listen(43508)) {
                    if (linkedEmail != null) {
                        if (!ListenerUtil.mutListener.listen(43507)) {
                            linksArray.put(getLink(TAG_SAFE_USER_LINK_TYPE_EMAIL, linkedEmail));
                        }
                    }
                }
            }
        }
        return linksArray;
    }

    private JSONObject getContact(ContactModel contactModel) throws JSONException {
        JSONObject contact = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43510)) {
            contact.put(TAG_SAFE_CONTACT_IDENTITY, contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(43513)) {
            if ((ListenerUtil.mutListener.listen(43511) ? (contactModel.getVerificationLevel() == VerificationLevel.FULLY_VERIFIED || contactModel.getPublicKey() != null) : (contactModel.getVerificationLevel() == VerificationLevel.FULLY_VERIFIED && contactModel.getPublicKey() != null))) {
                if (!ListenerUtil.mutListener.listen(43512)) {
                    contact.put(TAG_SAFE_CONTACT_PUBLIC_KEY, Base64.encodeBytes(contactModel.getPublicKey()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43516)) {
            if (contactModel.getDateCreated() != null) {
                if (!ListenerUtil.mutListener.listen(43515)) {
                    contact.put(TAG_SAFE_CONTACT_CREATED_AT, contactModel.getDateCreated().getTime());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(43514)) {
                    contact.put(TAG_SAFE_CONTACT_CREATED_AT, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43517)) {
            contact.put(TAG_SAFE_CONTACT_VERIFICATION_LEVEL, contactModel.getVerificationLevel().getCode());
        }
        if (!ListenerUtil.mutListener.listen(43518)) {
            contact.put(TAG_SAFE_CONTACT_WORK_VERIFIED, contactModel.isWork());
        }
        if (!ListenerUtil.mutListener.listen(43519)) {
            contact.put(TAG_SAFE_CONTACT_FIRST_NAME, contactModel.getFirstName());
        }
        if (!ListenerUtil.mutListener.listen(43520)) {
            contact.put(TAG_SAFE_CONTACT_LAST_NAME, contactModel.getLastName());
        }
        if (!ListenerUtil.mutListener.listen(43521)) {
            contact.put(TAG_SAFE_CONTACT_NICKNAME, contactModel.getPublicNickName());
        }
        if (!ListenerUtil.mutListener.listen(43522)) {
            contact.put(TAG_SAFE_CONTACT_HIDDEN, contactModel.isHidden());
        }
        if (!ListenerUtil.mutListener.listen(43523)) {
            contact.put(TAG_SAFE_CONTACT_PRIVATE, hiddenChatsListService.has(contactService.getUniqueIdString(contactModel)));
        }
        return contact;
    }

    private JSONArray getContacts() throws JSONException {
        JSONArray contactsArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43525)) {
            {
                long _loopCounter509 = 0;
                for (final ContactModel contactModel : contactService.find(null)) {
                    ListenerUtil.loopListener.listen("_loopCounter509", ++_loopCounter509);
                    if (!ListenerUtil.mutListener.listen(43524)) {
                        contactsArray.put(getContact(contactModel));
                    }
                }
            }
        }
        return contactsArray;
    }

    private JSONArray getGroupMembers(String[] groupMembers) {
        JSONArray membersArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43527)) {
            {
                long _loopCounter510 = 0;
                for (final String groupMember : groupMembers) {
                    ListenerUtil.loopListener.listen("_loopCounter510", ++_loopCounter510);
                    if (!ListenerUtil.mutListener.listen(43526)) {
                        membersArray.put(groupMember);
                    }
                }
            }
        }
        return membersArray;
    }

    private JSONObject getGroup(GroupService groupService, GroupModel groupModel) throws JSONException {
        JSONObject group = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43528)) {
            group.put(TAG_SAFE_GROUP_ID, groupModel.getApiGroupId());
        }
        if (!ListenerUtil.mutListener.listen(43529)) {
            group.put(TAG_SAFE_GROUP_CREATOR, groupModel.getCreatorIdentity());
        }
        if (!ListenerUtil.mutListener.listen(43530)) {
            group.put(TAG_SAFE_GROUP_NAME, groupModel.getName());
        }
        if (!ListenerUtil.mutListener.listen(43533)) {
            if (groupModel.getCreatedAt() != null) {
                if (!ListenerUtil.mutListener.listen(43532)) {
                    group.put(TAG_SAFE_GROUP_CREATED_AT, groupModel.getCreatedAt().getTime());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(43531)) {
                    group.put(TAG_SAFE_GROUP_CREATED_AT, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43534)) {
            group.put(TAG_SAFE_GROUP_MEMBERS, getGroupMembers(groupService.getGroupIdentities(groupModel)));
        }
        if (!ListenerUtil.mutListener.listen(43535)) {
            group.put(TAG_SAFE_GROUP_DELETED, groupModel.isDeleted());
        }
        if (!ListenerUtil.mutListener.listen(43536)) {
            group.put(TAG_SAFE_GROUP_PRIVATE, hiddenChatsListService.has(groupService.getUniqueIdString(groupModel)));
        }
        return group;
    }

    private JSONArray getGroups() throws JSONException {
        final GroupService groupService;
        try {
            groupService = ThreemaApplication.getServiceManager().getGroupService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43537)) {
                logger.error("Exception", e);
            }
            return null;
        }
        JSONArray groupsArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43539)) {
            {
                long _loopCounter511 = 0;
                for (final GroupModel groupModel : groupService.getAll(new GroupService.GroupFilter() {

                    @Override
                    public boolean sortingByDate() {
                        return false;
                    }

                    @Override
                    public boolean sortingByName() {
                        return false;
                    }

                    @Override
                    public boolean sortingAscending() {
                        return false;
                    }

                    @Override
                    public boolean withDeleted() {
                        return true;
                    }

                    @Override
                    public boolean withDeserted() {
                        return true;
                    }
                })) {
                    ListenerUtil.loopListener.listen("_loopCounter511", ++_loopCounter511);
                    if (!ListenerUtil.mutListener.listen(43538)) {
                        groupsArray.put(getGroup(groupService, groupModel));
                    }
                }
            }
        }
        return groupsArray;
    }

    private JSONArray getDistributionlistMembers(String[] distributionlistMembers) {
        JSONArray membersArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43541)) {
            {
                long _loopCounter512 = 0;
                for (final String distributionlistMember : distributionlistMembers) {
                    ListenerUtil.loopListener.listen("_loopCounter512", ++_loopCounter512);
                    if (!ListenerUtil.mutListener.listen(43540)) {
                        membersArray.put(distributionlistMember);
                    }
                }
            }
        }
        return membersArray;
    }

    private JSONObject getDistributionlist(DistributionListService distributionListService, DistributionListModel distributionListModel) throws JSONException {
        JSONObject distributionlist = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43542)) {
            distributionlist.put(TAG_SAFE_DISTRIBUTIONLIST_NAME, distributionListModel.getName());
        }
        if (!ListenerUtil.mutListener.listen(43545)) {
            if (distributionListModel.getCreatedAt() != null) {
                if (!ListenerUtil.mutListener.listen(43544)) {
                    distributionlist.put(TAG_SAFE_DISTRIBUTIONLIST_CREATED_AT, distributionListModel.getCreatedAt().getTime());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(43543)) {
                    distributionlist.put(TAG_SAFE_DISTRIBUTIONLIST_CREATED_AT, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43546)) {
            distributionlist.put(TAG_SAFE_DISTRIBUTIONLIST_MEMBERS, getDistributionlistMembers(distributionListService.getDistributionListIdentities(distributionListModel)));
        }
        if (!ListenerUtil.mutListener.listen(43547)) {
            distributionlist.put(TAG_SAFE_DISTRIBUTIONLIST_PRIVATE, hiddenChatsListService.has(distributionListService.getUniqueIdString(distributionListModel)));
        }
        return distributionlist;
    }

    private JSONArray getDistributionlists() throws JSONException {
        final DistributionListService distributionListService;
        try {
            distributionListService = ThreemaApplication.getServiceManager().getDistributionListService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43548)) {
                logger.error("Exception", e);
            }
            return null;
        }
        JSONArray distributionlistsArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43550)) {
            {
                long _loopCounter513 = 0;
                for (final DistributionListModel distributionListModel : distributionListService.getAll(new DistributionListService.DistributionListFilter() {

                    @Override
                    public boolean sortingByDate() {
                        return false;
                    }

                    @Override
                    public boolean sortingAscending() {
                        return false;
                    }
                })) {
                    ListenerUtil.loopListener.listen("_loopCounter513", ++_loopCounter513);
                    if (!ListenerUtil.mutListener.listen(43549)) {
                        distributionlistsArray.put(getDistributionlist(distributionListService, distributionListModel));
                    }
                }
            }
        }
        return distributionlistsArray;
    }

    private JSONObject getInfo() throws JSONException {
        JSONObject info = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43551)) {
            info.put(TAG_SAFE_INFO_VERSION, PROTOCOL_VERSION);
        }
        if (!ListenerUtil.mutListener.listen(43552)) {
            info.put(TAG_SAFE_INFO_DEVICE, ConfigUtils.getAppVersion(context) + "A/" + Locale.getDefault().toString());
        }
        return info;
    }

    private JSONObject getUser() throws JSONException {
        JSONObject user = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43553)) {
            user.put(TAG_SAFE_USER_PRIVATE_KEY, Base64.encodeBytes(identityStore.getPrivateKey()));
        }
        if (!ListenerUtil.mutListener.listen(43554)) {
            user.put(TAG_SAFE_USER_NICKNAME, userService.getPublicNickname());
        }
        try {
            Bitmap image = fileService.getContactAvatar(contactService.getMe());
            if (!ListenerUtil.mutListener.listen(43569)) {
                if (image != null) {
                    if (!ListenerUtil.mutListener.listen(43561)) {
                        // scale image - assume profile pics are always square
                        if ((ListenerUtil.mutListener.listen(43559) ? (Math.max(image.getWidth(), image.getHeight()) >= PROFILEPIC_MAX_WIDTH) : (ListenerUtil.mutListener.listen(43558) ? (Math.max(image.getWidth(), image.getHeight()) <= PROFILEPIC_MAX_WIDTH) : (ListenerUtil.mutListener.listen(43557) ? (Math.max(image.getWidth(), image.getHeight()) < PROFILEPIC_MAX_WIDTH) : (ListenerUtil.mutListener.listen(43556) ? (Math.max(image.getWidth(), image.getHeight()) != PROFILEPIC_MAX_WIDTH) : (ListenerUtil.mutListener.listen(43555) ? (Math.max(image.getWidth(), image.getHeight()) == PROFILEPIC_MAX_WIDTH) : (Math.max(image.getWidth(), image.getHeight()) > PROFILEPIC_MAX_WIDTH))))))) {
                            if (!ListenerUtil.mutListener.listen(43560)) {
                                image = BitmapUtil.resizeBitmap(image, PROFILEPIC_MAX_WIDTH, PROFILEPIC_MAX_WIDTH);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(43562)) {
                        user.put(TAG_SAFE_USER_PROFILE_PIC, Base64.encodeBytes(BitmapUtil.bitmapToByteArray(image, Bitmap.CompressFormat.JPEG, PROFILEPIC_QUALITY)));
                    }
                    JSONArray profilePicRelease = new JSONArray();
                    if (!ListenerUtil.mutListener.listen(43567)) {
                        switch(preferenceService.getProfilePicRelease()) {
                            case PROFILEPIC_RELEASE_EVERYONE:
                                if (!ListenerUtil.mutListener.listen(43563)) {
                                    profilePicRelease.put(PROFILE_PIC_RELEASE_ALL_PLACEHOLDER);
                                }
                                break;
                            case PROFILEPIC_RELEASE_SOME:
                                if (!ListenerUtil.mutListener.listen(43565)) {
                                    {
                                        long _loopCounter514 = 0;
                                        for (String id : profilePicRecipientsService.getAll()) {
                                            ListenerUtil.loopListener.listen("_loopCounter514", ++_loopCounter514);
                                            if (!ListenerUtil.mutListener.listen(43564)) {
                                                profilePicRelease.put(id);
                                            }
                                        }
                                    }
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(43566)) {
                                    profilePicRelease.put(null);
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(43568)) {
                        user.put(TAG_SAFE_USER_PROFILE_PIC_RELEASE, profilePicRelease);
                    }
                }
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(43570)) {
            user.put(TAG_SAFE_USER_LINKS, getLinks());
        }
        return user;
    }

    private JSONArray getSettingsBlockedContacts() {
        final IdListService blacklistService;
        try {
            blacklistService = ThreemaApplication.getServiceManager().getBlackListService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43571)) {
                logger.error("Exception", e);
            }
            return null;
        }
        JSONArray blockedContactsArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43573)) {
            {
                long _loopCounter515 = 0;
                for (final String id : blacklistService.getAll()) {
                    ListenerUtil.loopListener.listen("_loopCounter515", ++_loopCounter515);
                    if (!ListenerUtil.mutListener.listen(43572)) {
                        blockedContactsArray.put(id);
                    }
                }
            }
        }
        return blockedContactsArray;
    }

    private void setSettingsBlockedContacts(JSONArray blockedContacts) {
        if (!ListenerUtil.mutListener.listen(43574)) {
            if (blockedContacts == null)
                return;
        }
        final IdListService blacklistService;
        try {
            blacklistService = ThreemaApplication.getServiceManager().getBlackListService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43575)) {
                logger.error("Exception", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(43582)) {
            {
                long _loopCounter516 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43581) ? (i >= blockedContacts.length()) : (ListenerUtil.mutListener.listen(43580) ? (i <= blockedContacts.length()) : (ListenerUtil.mutListener.listen(43579) ? (i > blockedContacts.length()) : (ListenerUtil.mutListener.listen(43578) ? (i != blockedContacts.length()) : (ListenerUtil.mutListener.listen(43577) ? (i == blockedContacts.length()) : (i < blockedContacts.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter516", ++_loopCounter516);
                    try {
                        if (!ListenerUtil.mutListener.listen(43576)) {
                            blacklistService.add(blockedContacts.getString(i));
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
    }

    private JSONArray getSettingsSyncExcludedContacts() {
        final IdListService excludedSyncIdentitiesService;
        try {
            excludedSyncIdentitiesService = ThreemaApplication.getServiceManager().getExcludedSyncIdentitiesService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43583)) {
                logger.error("Exception", e);
            }
            return null;
        }
        JSONArray excludedSyncIds = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43585)) {
            {
                long _loopCounter517 = 0;
                for (final String id : excludedSyncIdentitiesService.getAll()) {
                    ListenerUtil.loopListener.listen("_loopCounter517", ++_loopCounter517);
                    if (!ListenerUtil.mutListener.listen(43584)) {
                        excludedSyncIds.put(id);
                    }
                }
            }
        }
        return excludedSyncIds;
    }

    private void setSettingsSyncExcluded(JSONArray excludedIdentities) {
        if (!ListenerUtil.mutListener.listen(43586)) {
            if (excludedIdentities == null)
                return;
        }
        final IdListService excludedSyncIdentitiesService;
        try {
            excludedSyncIdentitiesService = ThreemaApplication.getServiceManager().getExcludedSyncIdentitiesService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(43587)) {
                logger.error("Exception", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(43594)) {
            {
                long _loopCounter518 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43593) ? (i >= excludedIdentities.length()) : (ListenerUtil.mutListener.listen(43592) ? (i <= excludedIdentities.length()) : (ListenerUtil.mutListener.listen(43591) ? (i > excludedIdentities.length()) : (ListenerUtil.mutListener.listen(43590) ? (i != excludedIdentities.length()) : (ListenerUtil.mutListener.listen(43589) ? (i == excludedIdentities.length()) : (i < excludedIdentities.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter518", ++_loopCounter518);
                    try {
                        if (!ListenerUtil.mutListener.listen(43588)) {
                            excludedSyncIdentitiesService.add(excludedIdentities.getString(i));
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
    }

    private JSONArray getSettingsRecentEmojis() {
        JSONArray recentEmojis = new JSONArray();
        if (!ListenerUtil.mutListener.listen(43596)) {
            {
                long _loopCounter519 = 0;
                for (final String emoji : preferenceService.getRecentEmojis2()) {
                    ListenerUtil.loopListener.listen("_loopCounter519", ++_loopCounter519);
                    if (!ListenerUtil.mutListener.listen(43595)) {
                        recentEmojis.put(emoji);
                    }
                }
            }
        }
        return recentEmojis;
    }

    private void setSettingsRecentEmojis(JSONArray recentEmojis) {
        if (!ListenerUtil.mutListener.listen(43597)) {
            if (recentEmojis == null)
                return;
        }
        LinkedList<String> emojiList = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(43604)) {
            {
                long _loopCounter520 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(43603) ? (i >= recentEmojis.length()) : (ListenerUtil.mutListener.listen(43602) ? (i <= recentEmojis.length()) : (ListenerUtil.mutListener.listen(43601) ? (i > recentEmojis.length()) : (ListenerUtil.mutListener.listen(43600) ? (i != recentEmojis.length()) : (ListenerUtil.mutListener.listen(43599) ? (i == recentEmojis.length()) : (i < recentEmojis.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter520", ++_loopCounter520);
                    try {
                        if (!ListenerUtil.mutListener.listen(43598)) {
                            emojiList.add(recentEmojis.getString(i));
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43611)) {
            if ((ListenerUtil.mutListener.listen(43609) ? (emojiList.size() >= 0) : (ListenerUtil.mutListener.listen(43608) ? (emojiList.size() <= 0) : (ListenerUtil.mutListener.listen(43607) ? (emojiList.size() < 0) : (ListenerUtil.mutListener.listen(43606) ? (emojiList.size() != 0) : (ListenerUtil.mutListener.listen(43605) ? (emojiList.size() == 0) : (emojiList.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(43610)) {
                    preferenceService.setRecentEmojis2(emojiList);
                }
            }
        }
    }

    private JSONObject getSettings() throws JSONException {
        JSONObject settings = new JSONObject();
        if (!ListenerUtil.mutListener.listen(43612)) {
            settings.put(TAG_SAFE_SETTINGS_SYNC_CONTACTS, preferenceService.isSyncContacts());
        }
        if (!ListenerUtil.mutListener.listen(43613)) {
            settings.put(TAG_SAFE_SETTINGS_BLOCK_UNKNOWN, preferenceService.isBlockUnknown());
        }
        if (!ListenerUtil.mutListener.listen(43614)) {
            settings.put(TAG_SAFE_SETTINGS_SEND_TYPING, preferenceService.isTypingIndicator());
        }
        if (!ListenerUtil.mutListener.listen(43615)) {
            settings.put(TAG_SAFE_SETTINGS_READ_RECEIPTS, preferenceService.isReadReceipts());
        }
        if (!ListenerUtil.mutListener.listen(43616)) {
            settings.put(TAG_SAFE_SETTINGS_THREEMA_CALLS, preferenceService.isVoipEnabled());
        }
        if (!ListenerUtil.mutListener.listen(43617)) {
            settings.put(TAG_SAFE_SETTINGS_LOCATION_PREVIEWS, false);
        }
        if (!ListenerUtil.mutListener.listen(43618)) {
            settings.put(TAG_SAFE_SETTINGS_RELAY_THREEMA_CALLS, preferenceService.getForceTURN());
        }
        if (!ListenerUtil.mutListener.listen(43619)) {
            settings.put(TAG_SAFE_SETTINGS_DISABLE_SCREENSHOTS, preferenceService.isDisableScreenshots());
        }
        if (!ListenerUtil.mutListener.listen(43620)) {
            settings.put(TAG_SAFE_SETTINGS_INCOGNITO_KEAYBOARD, preferenceService.getIncognitoKeyboard());
        }
        if (!ListenerUtil.mutListener.listen(43621)) {
            settings.put(TAG_SAFE_SETTINGS_BLOCKED_CONTACTS, getSettingsBlockedContacts());
        }
        if (!ListenerUtil.mutListener.listen(43622)) {
            settings.put(TAG_SAFE_SETTINGS_SYNC_EXCLUDED_CONTACTS, getSettingsSyncExcludedContacts());
        }
        if (!ListenerUtil.mutListener.listen(43623)) {
            settings.put(TAG_SAFE_SETTINGS_RECENT_EMOJIS, getSettingsRecentEmojis());
        }
        return settings;
    }

    private String getJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(43625)) {
                jsonObject.put(TAG_SAFE_INFO, getInfo());
            }
            if (!ListenerUtil.mutListener.listen(43626)) {
                jsonObject.put(TAG_SAFE_USER, getUser());
            }
            if (!ListenerUtil.mutListener.listen(43627)) {
                jsonObject.put(TAG_SAFE_CONTACTS, getContacts());
            }
            if (!ListenerUtil.mutListener.listen(43628)) {
                jsonObject.put(TAG_SAFE_GROUPS, getGroups());
            }
            if (!ListenerUtil.mutListener.listen(43629)) {
                jsonObject.put(TAG_SAFE_DISTRIBUTIONLISTS, getDistributionlists());
            }
            if (!ListenerUtil.mutListener.listen(43630)) {
                jsonObject.put(TAG_SAFE_SETTINGS, getSettings());
            }
            return jsonObject.toString(BuildConfig.DEBUG ? 4 : 0);
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(43624)) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    public class UploadSizeExceedException extends Exception {

        UploadSizeExceedException(String e) {
            super(e);
        }
    }
}
