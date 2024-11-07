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

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.ContactsContract;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.BuildConfig;
import ch.threema.app.BuildFlavor;
import ch.threema.app.R;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.listeners.SMSVerificationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.routines.UpdateWorkInfoRoutine;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.SerialCredentials;
import ch.threema.app.services.license.UserCredentials;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.stores.PreferenceStore;
import ch.threema.app.stores.PreferenceStoreInterface;
import ch.threema.app.stores.PreferenceStoreInterfaceDevNullImpl;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DeviceIdUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.PushUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.APIConnector;
import ch.threema.client.CreateIdentityRequestDataInterface;
import ch.threema.client.IdentityBackupDecoder;
import ch.threema.client.IdentityStoreInterface;
import ch.threema.client.MessageQueue;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.TypingIndicatorMessage;
import ch.threema.client.Utils;
import static ch.threema.app.ThreemaApplication.PHONE_LINKED_PLACEHOLDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This service class handle all user actions (db/identity....)
 */
public class UserServiceImpl implements UserService, CreateIdentityRequestDataInterface {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final Context context;

    private final PreferenceStoreInterface preferenceStore;

    private final IdentityStore identityStore;

    private final APIConnector apiConnector;

    private final LocaleService localeService;

    private final MessageQueue messageQueue;

    private final PreferenceService preferenceService;

    private String policyResponseData;

    private String policySignature;

    private int policyErrorCode;

    private LicenseService.Credentials credentials;

    private Account account;

    public UserServiceImpl(Context context, PreferenceStoreInterface preferenceStore, LocaleService localeService, APIConnector apiConnector, IdentityStore identityStore, MessageQueue messageQueue, PreferenceService preferenceService) {
        this.context = context;
        this.preferenceStore = preferenceStore;
        this.localeService = localeService;
        this.messageQueue = messageQueue;
        this.identityStore = identityStore;
        this.apiConnector = apiConnector;
        this.preferenceService = preferenceService;
    }

    @Override
    public void createIdentity(byte[] newRandomSeed) throws Exception {
        if (!ListenerUtil.mutListener.listen(41217)) {
            if (this.hasIdentity()) {
                throw new ThreemaException("please remove your existing identity " + this.getIdentity());
            }
        }
        if (!ListenerUtil.mutListener.listen(41222)) {
            // note that CheckLicenseRoutine may not have received an upstream response yet.
            if ((ListenerUtil.mutListener.listen(41220) ? ((ListenerUtil.mutListener.listen(41219) ? ((ListenerUtil.mutListener.listen(41218) ? (policySignature == null || policyResponseData == null) : (policySignature == null && policyResponseData == null)) || credentials == null) : ((ListenerUtil.mutListener.listen(41218) ? (policySignature == null || policyResponseData == null) : (policySignature == null && policyResponseData == null)) && credentials == null)) || !BuildConfig.DEBUG) : ((ListenerUtil.mutListener.listen(41219) ? ((ListenerUtil.mutListener.listen(41218) ? (policySignature == null || policyResponseData == null) : (policySignature == null && policyResponseData == null)) || credentials == null) : ((ListenerUtil.mutListener.listen(41218) ? (policySignature == null || policyResponseData == null) : (policySignature == null && policyResponseData == null)) && credentials == null)) && !BuildConfig.DEBUG))) {
                throw new ThreemaException(context.getString(R.string.missing_app_licence) + "\n" + context.getString(R.string.app_store_error_code, policyErrorCode));
            } else {
                if (!ListenerUtil.mutListener.listen(41221)) {
                    this.apiConnector.createIdentity(this.identityStore, newRandomSeed, this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41223)) {
            this.sendFlags();
        }
        if (!ListenerUtil.mutListener.listen(41224)) {
            // identity has been successfully created. set push token
            PushUtil.enqueuePushTokenUpdate(context, false, false);
        }
    }

    @Override
    public void removeIdentity() throws Exception {
        if (!ListenerUtil.mutListener.listen(41225)) {
            if (!this.hasIdentity()) {
                throw new ThreemaException("no identity to remove");
            }
        }
        if (!ListenerUtil.mutListener.listen(41226)) {
            this.removeAccount();
        }
        if (!ListenerUtil.mutListener.listen(41227)) {
            this.identityStore.clear();
        }
    }

    @Override
    public Account getAccount() {
        return this.getAccount(false);
    }

    @Override
    public Account getAccount(boolean createIfNotExists) {
        if (!ListenerUtil.mutListener.listen(41239)) {
            if (this.account == null) {
                AccountManager accountManager = AccountManager.get(this.context);
                try {
                    if (!ListenerUtil.mutListener.listen(41229)) {
                        this.account = Functional.select(new HashSet<Account>(Arrays.asList(accountManager.getAccountsByType(context.getPackageName()))), new IPredicateNonNull<Account>() {

                            @Override
                            public boolean apply(@NonNull Account type) {
                                return true;
                            }
                        });
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(41228)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(41238)) {
                    // if sync enabled, create one!
                    if ((ListenerUtil.mutListener.listen(41231) ? (this.account == null || ((ListenerUtil.mutListener.listen(41230) ? (createIfNotExists && this.preferenceService.isSyncContacts()) : (createIfNotExists || this.preferenceService.isSyncContacts())))) : (this.account == null && ((ListenerUtil.mutListener.listen(41230) ? (createIfNotExists && this.preferenceService.isSyncContacts()) : (createIfNotExists || this.preferenceService.isSyncContacts())))))) {
                        if (!ListenerUtil.mutListener.listen(41232)) {
                            this.account = new Account(context.getString(R.string.app_name), context.getString(R.string.package_name));
                        }
                        // This method requires the caller to have the same UID as the added account's authenticator.
                        try {
                            if (!ListenerUtil.mutListener.listen(41234)) {
                                accountManager.addAccountExplicitly(this.account, "", null);
                            }
                            if (!ListenerUtil.mutListener.listen(41235)) {
                                // auto enable sync
                                ContentResolver.setIsSyncable(account, ContactsContract.AUTHORITY, 1);
                            }
                            if (!ListenerUtil.mutListener.listen(41237)) {
                                if (!ContentResolver.getSyncAutomatically(account, ContactsContract.AUTHORITY)) {
                                    if (!ListenerUtil.mutListener.listen(41236)) {
                                        ContentResolver.setSyncAutomatically(account, ContactsContract.AUTHORITY, true);
                                    }
                                }
                            }
                        } catch (SecurityException e) {
                            if (!ListenerUtil.mutListener.listen(41233)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            }
        }
        return this.account;
    }

    @Override
    public boolean checkAccount() {
        AccountManager accountManager = AccountManager.get(this.context);
        return Functional.select(new HashSet<Account>(Arrays.asList(accountManager.getAccountsByType(context.getPackageName()))), new IPredicateNonNull<Account>() {

            @Override
            public boolean apply(@NonNull Account type) {
                return true;
            }
        }) != null;
    }

    @Override
    public boolean enableAccountAutoSync(boolean enable) {
        Account account = this.getAccount();
        if (!ListenerUtil.mutListener.listen(41242)) {
            if (account != null) {
                if (!ListenerUtil.mutListener.listen(41241)) {
                    if (enable != ContentResolver.getSyncAutomatically(account, ContactsContract.AUTHORITY)) {
                        if (!ListenerUtil.mutListener.listen(41240)) {
                            ContentResolver.setSyncAutomatically(account, ContactsContract.AUTHORITY, enable);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeAccount() {
        if (!ListenerUtil.mutListener.listen(41243)) {
            this.removeAccount(null);
        }
    }

    @Override
    public boolean removeAccount(AccountManagerCallback<Boolean> callback) {
        Account a = this.getAccount(false);
        if (a != null) {
            AccountManager accountManager = AccountManager.get(this.context);
            if (!ListenerUtil.mutListener.listen(41244)) {
                accountManager.removeAccount(a, callback, null);
            }
            if (!ListenerUtil.mutListener.listen(41245)) {
                this.account = null;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasIdentity() {
        return this.getIdentity() != null;
    }

    @Override
    public String getIdentity() {
        return this.identityStore.getIdentity();
    }

    @Override
    public boolean isMe(@Nullable String identity) {
        return (ListenerUtil.mutListener.listen(41246) ? (identity != null || identity.equals(this.getIdentity())) : (identity != null && identity.equals(this.getIdentity())));
    }

    @Override
    public byte[] getPublicKey() {
        return this.identityStore.getPublicKey();
    }

    @Override
    public byte[] getPrivateKey() {
        return this.identityStore.getPrivateKey();
    }

    @Override
    public String getLinkedEmail() {
        String email = this.preferenceStore.getString(PreferenceStore.PREFS_LINKED_EMAIL);
        return email != null ? email : "";
    }

    @Override
    public void linkWithEmail(String email) throws Exception {
        boolean pending = this.apiConnector.linkEmail(email, this.getLanguage(), this.identityStore);
        if (!ListenerUtil.mutListener.listen(41247)) {
            this.preferenceStore.save(PreferenceStore.PREFS_LINKED_EMAIL, email);
        }
        if (!ListenerUtil.mutListener.listen(41248)) {
            this.preferenceStore.save(PreferenceStore.PREFS_LINKED_EMAIL_PENDING, pending);
        }
    }

    @Override
    public void unlinkEmail() throws Exception {
        String email = this.preferenceStore.getString(PreferenceStore.PREFS_LINKED_EMAIL);
        if (!ListenerUtil.mutListener.listen(41249)) {
            if (email == null) {
                throw new ThreemaException("no email linked");
            }
        }
        if (!ListenerUtil.mutListener.listen(41250)) {
            this.apiConnector.linkEmail("", this.getLanguage(), this.identityStore);
        }
        if (!ListenerUtil.mutListener.listen(41251)) {
            this.preferenceStore.remove(PreferenceStore.PREFS_LINKED_EMAIL);
        }
        if (!ListenerUtil.mutListener.listen(41252)) {
            this.preferenceStore.remove(PreferenceStore.PREFS_LINKED_EMAIL_PENDING);
        }
    }

    @Override
    public int getEmailLinkingState() {
        if (this.preferenceStore.getBoolean(PreferenceStore.PREFS_LINKED_EMAIL_PENDING)) {
            return LinkingState_PENDING;
        } else if (this.preferenceStore.getString(PreferenceStore.PREFS_LINKED_EMAIL) != null) {
            return LinkingState_LINKED;
        } else {
            return LinkingState_NONE;
        }
    }

    @Override
    public void checkEmailLinkState() {
        if (!ListenerUtil.mutListener.listen(41256)) {
            if (this.getEmailLinkingState() == LinkingState_PENDING) {
                try {
                    if (!ListenerUtil.mutListener.listen(41255)) {
                        if (this.apiConnector.linkEmailCheckStatus(this.getLinkedEmail(), this.identityStore)) {
                            if (!ListenerUtil.mutListener.listen(41254)) {
                                this.preferenceStore.remove(PreferenceStore.PREFS_LINKED_EMAIL_PENDING);
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(41253)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public Date linkWithMobileNumber(String number) throws Exception {
        Date linkWithMobileTime = new Date();
        String normalizedMobileNo = this.localeService.getNormalizedPhoneNumber(number);
        if (!ListenerUtil.mutListener.listen(41265)) {
            if ((ListenerUtil.mutListener.listen(41263) ? ((ListenerUtil.mutListener.listen(41262) ? (normalizedMobileNo != null || (ListenerUtil.mutListener.listen(41261) ? (normalizedMobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(41260) ? (normalizedMobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(41259) ? (normalizedMobileNo.length() < 0) : (ListenerUtil.mutListener.listen(41258) ? (normalizedMobileNo.length() != 0) : (ListenerUtil.mutListener.listen(41257) ? (normalizedMobileNo.length() == 0) : (normalizedMobileNo.length() > 0))))))) : (normalizedMobileNo != null && (ListenerUtil.mutListener.listen(41261) ? (normalizedMobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(41260) ? (normalizedMobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(41259) ? (normalizedMobileNo.length() < 0) : (ListenerUtil.mutListener.listen(41258) ? (normalizedMobileNo.length() != 0) : (ListenerUtil.mutListener.listen(41257) ? (normalizedMobileNo.length() == 0) : (normalizedMobileNo.length() > 0)))))))) || normalizedMobileNo.startsWith("+")) : ((ListenerUtil.mutListener.listen(41262) ? (normalizedMobileNo != null || (ListenerUtil.mutListener.listen(41261) ? (normalizedMobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(41260) ? (normalizedMobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(41259) ? (normalizedMobileNo.length() < 0) : (ListenerUtil.mutListener.listen(41258) ? (normalizedMobileNo.length() != 0) : (ListenerUtil.mutListener.listen(41257) ? (normalizedMobileNo.length() == 0) : (normalizedMobileNo.length() > 0))))))) : (normalizedMobileNo != null && (ListenerUtil.mutListener.listen(41261) ? (normalizedMobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(41260) ? (normalizedMobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(41259) ? (normalizedMobileNo.length() < 0) : (ListenerUtil.mutListener.listen(41258) ? (normalizedMobileNo.length() != 0) : (ListenerUtil.mutListener.listen(41257) ? (normalizedMobileNo.length() == 0) : (normalizedMobileNo.length() > 0)))))))) && normalizedMobileNo.startsWith("+")))) {
                if (!ListenerUtil.mutListener.listen(41264)) {
                    normalizedMobileNo = normalizedMobileNo.substring(1);
                }
            }
        }
        String verificationId = this.apiConnector.linkMobileNo(normalizedMobileNo, this.getLanguage(), this.identityStore, ((ListenerUtil.mutListener.listen(41266) ? (BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.GOOGLE_WORK && BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.HMS_WORK) : (BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.GOOGLE_WORK || BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.HMS_WORK))) ? "threemawork" : null);
        if (!ListenerUtil.mutListener.listen(41267)) {
            this.preferenceStore.save(PreferenceStore.PREFS_LINKED_MOBILE, number);
        }
        if (!ListenerUtil.mutListener.listen(41268)) {
            if (verificationId == null) {
                throw new ThreemaException(this.context.getResources().getString(R.string.mobile_already_linked));
            }
        }
        if (!ListenerUtil.mutListener.listen(41269)) {
            this.preferenceStore.save(PreferenceStore.PREFS_LINKED_MOBILE_PENDING, System.currentTimeMillis());
        }
        if (!ListenerUtil.mutListener.listen(41270)) {
            this.preferenceStore.save(PreferenceStore.PREFS_MOBILE_VERIFICATION_ID, verificationId);
        }
        if (!ListenerUtil.mutListener.listen(41272)) {
            ListenerManager.smsVerificationListeners.handle(new ListenerManager.HandleListener<SMSVerificationListener>() {

                @Override
                public void handle(SMSVerificationListener listener) {
                    if (!ListenerUtil.mutListener.listen(41271)) {
                        listener.onVerificationStarted();
                    }
                }
            });
        }
        return linkWithMobileTime;
    }

    @Override
    public void makeMobileLinkCall() throws Exception {
        if (!ListenerUtil.mutListener.listen(41273)) {
            if (this.getMobileLinkingState() != LinkingState_PENDING) {
                throw new ThreemaException("no verification in progress");
            }
        }
        if (!ListenerUtil.mutListener.listen(41274)) {
            this.apiConnector.linkMobileNoCall(getCurrentMobileNumberVerificationId());
        }
    }

    private String getCurrentMobileNumberVerificationId() {
        return this.preferenceStore.getString(PreferenceStore.PREFS_MOBILE_VERIFICATION_ID);
    }

    private String getCurrentMobileNumber() {
        return this.preferenceStore.getString(PreferenceStore.PREFS_LINKED_MOBILE);
    }

    @Override
    public void unlinkMobileNumber() throws Exception {
        String mobileNumber = this.preferenceStore.getString(PreferenceStore.PREFS_LINKED_MOBILE);
        if (!ListenerUtil.mutListener.listen(41282)) {
            if (mobileNumber == null) {
                String currentMobileNumber = getCurrentMobileNumber();
                if (!ListenerUtil.mutListener.listen(41281)) {
                    if ((ListenerUtil.mutListener.listen(41280) ? (currentMobileNumber == null && (ListenerUtil.mutListener.listen(41279) ? (currentMobileNumber.length() >= 0) : (ListenerUtil.mutListener.listen(41278) ? (currentMobileNumber.length() <= 0) : (ListenerUtil.mutListener.listen(41277) ? (currentMobileNumber.length() > 0) : (ListenerUtil.mutListener.listen(41276) ? (currentMobileNumber.length() < 0) : (ListenerUtil.mutListener.listen(41275) ? (currentMobileNumber.length() != 0) : (currentMobileNumber.length() == 0))))))) : (currentMobileNumber == null || (ListenerUtil.mutListener.listen(41279) ? (currentMobileNumber.length() >= 0) : (ListenerUtil.mutListener.listen(41278) ? (currentMobileNumber.length() <= 0) : (ListenerUtil.mutListener.listen(41277) ? (currentMobileNumber.length() > 0) : (ListenerUtil.mutListener.listen(41276) ? (currentMobileNumber.length() < 0) : (ListenerUtil.mutListener.listen(41275) ? (currentMobileNumber.length() != 0) : (currentMobileNumber.length() == 0))))))))) {
                        throw new ThreemaException("no mobile number linked");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41283)) {
            this.apiConnector.linkMobileNo("", this.getLanguage(), this.identityStore);
        }
        if (!ListenerUtil.mutListener.listen(41284)) {
            this.preferenceStore.remove(PreferenceStore.PREFS_LINKED_MOBILE);
        }
        if (!ListenerUtil.mutListener.listen(41285)) {
            this.preferenceStore.remove(PreferenceStore.PREFS_LINKED_MOBILE_PENDING);
        }
        if (!ListenerUtil.mutListener.listen(41286)) {
            this.preferenceStore.remove(PreferenceStore.PREFS_MOBILE_VERIFICATION_ID);
        }
        if (!ListenerUtil.mutListener.listen(41288)) {
            ListenerManager.smsVerificationListeners.handle(new ListenerManager.HandleListener<SMSVerificationListener>() {

                @Override
                public void handle(SMSVerificationListener listener) {
                    if (!ListenerUtil.mutListener.listen(41287)) {
                        listener.onVerified();
                    }
                }
            });
        }
    }

    @Override
    public boolean verifyMobileNumber(String code) throws Exception {
        if (!ListenerUtil.mutListener.listen(41294)) {
            if (this.getMobileLinkingState() == LinkingState_PENDING) {
                if (!ListenerUtil.mutListener.listen(41289)) {
                    this.apiConnector.linkMobileNoVerify(getCurrentMobileNumberVerificationId(), code);
                }
                if (!ListenerUtil.mutListener.listen(41290)) {
                    // verification ok, save phone number
                    this.preferenceStore.remove(PreferenceStore.PREFS_LINKED_MOBILE_PENDING);
                }
                if (!ListenerUtil.mutListener.listen(41291)) {
                    this.preferenceStore.remove(PreferenceStore.PREFS_MOBILE_VERIFICATION_ID);
                }
                if (!ListenerUtil.mutListener.listen(41293)) {
                    ListenerManager.smsVerificationListeners.handle(new ListenerManager.HandleListener<SMSVerificationListener>() {

                        @Override
                        public void handle(SMSVerificationListener listener) {
                            if (!ListenerUtil.mutListener.listen(41292)) {
                                listener.onVerified();
                            }
                        }
                    });
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String getLinkedMobileE164() {
        return this.preferenceStore.getString(PreferenceStore.PREFS_LINKED_MOBILE);
    }

    @Override
    public String getLinkedMobile() {
        String linkedMobile = getLinkedMobileE164();
        if (!ListenerUtil.mutListener.listen(41295)) {
            if (PHONE_LINKED_PLACEHOLDER.equals(linkedMobile)) {
                return linkedMobile;
            }
        }
        if (!ListenerUtil.mutListener.listen(41296)) {
            if (TestUtil.empty(linkedMobile)) {
                return null;
            }
        }
        return "+" + linkedMobile;
    }

    @Override
    public String getLinkedMobile(boolean returnPendingNumber) {
        String currentMobileNumber = getCurrentMobileNumber();
        if (!ListenerUtil.mutListener.listen(41303)) {
            if ((ListenerUtil.mutListener.listen(41302) ? (currentMobileNumber != null || (ListenerUtil.mutListener.listen(41301) ? (currentMobileNumber.length() >= 0) : (ListenerUtil.mutListener.listen(41300) ? (currentMobileNumber.length() <= 0) : (ListenerUtil.mutListener.listen(41299) ? (currentMobileNumber.length() < 0) : (ListenerUtil.mutListener.listen(41298) ? (currentMobileNumber.length() != 0) : (ListenerUtil.mutListener.listen(41297) ? (currentMobileNumber.length() == 0) : (currentMobileNumber.length() > 0))))))) : (currentMobileNumber != null && (ListenerUtil.mutListener.listen(41301) ? (currentMobileNumber.length() >= 0) : (ListenerUtil.mutListener.listen(41300) ? (currentMobileNumber.length() <= 0) : (ListenerUtil.mutListener.listen(41299) ? (currentMobileNumber.length() < 0) : (ListenerUtil.mutListener.listen(41298) ? (currentMobileNumber.length() != 0) : (ListenerUtil.mutListener.listen(41297) ? (currentMobileNumber.length() == 0) : (currentMobileNumber.length() > 0))))))))) {
                return currentMobileNumber;
            }
        }
        return this.getLinkedMobile();
    }

    @Override
    public int getMobileLinkingState() {
        if ((ListenerUtil.mutListener.listen(41308) ? (this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING) >= 0) : (ListenerUtil.mutListener.listen(41307) ? (this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING) <= 0) : (ListenerUtil.mutListener.listen(41306) ? (this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING) < 0) : (ListenerUtil.mutListener.listen(41305) ? (this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING) != 0) : (ListenerUtil.mutListener.listen(41304) ? (this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING) == 0) : (this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING) > 0))))))) {
            return LinkingState_PENDING;
        } else if (this.getLinkedMobile() != null) {
            return LinkingState_LINKED;
        } else {
            return LinkingState_NONE;
        }
    }

    @Override
    public long getMobileLinkingTime() {
        return this.preferenceStore.getLong(PreferenceStore.PREFS_LINKED_MOBILE_PENDING);
    }

    @Override
    public String getPublicNickname() {
        return this.identityStore.getPublicNickname();
    }

    @Override
    public String setPublicNickname(String publicNickname) {
        // fix #ANDR-530
        String truncated = Utils.truncateUTF8String(publicNickname, ProtocolDefines.PUSH_FROM_LEN);
        if (!ListenerUtil.mutListener.listen(41309)) {
            this.identityStore.setPublicNickname(truncated);
        }
        if (!ListenerUtil.mutListener.listen(41311)) {
            // run update work info (only if the app is the work version)
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(41310)) {
                    UpdateWorkInfoRoutine.start();
                }
            }
        }
        return truncated;
    }

    private String getLanguage() {
        return LocaleUtil.getLanguage();
    }

    @Override
    public boolean isTyping(String toIdentity, boolean isTyping) {
        if (!ListenerUtil.mutListener.listen(41312)) {
            if (!preferenceService.isTypingIndicator()) {
                return false;
            }
        }
        final TypingIndicatorMessage msg = new TypingIndicatorMessage();
        if (!ListenerUtil.mutListener.listen(41313)) {
            msg.setTyping(isTyping);
        }
        if (!ListenerUtil.mutListener.listen(41314)) {
            msg.setFromIdentity(this.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(41315)) {
            msg.setToIdentity(toIdentity);
        }
        try {
            return this.messageQueue.enqueue(msg) != null;
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(41316)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    @Override
    public boolean restoreIdentity(final String backupString, final String password) throws Exception {
        final IdentityBackupDecoder identityBackupDecoder = new IdentityBackupDecoder(backupString);
        if (!ListenerUtil.mutListener.listen(41317)) {
            if (!identityBackupDecoder.decode(password)) {
                return false;
            }
        }
        return restoreIdentity(identityBackupDecoder.getIdentity(), identityBackupDecoder.getPrivateKey(), identityBackupDecoder.getPublicKey());
    }

    @Override
    public boolean restoreIdentity(String identity, byte[] privateKey, byte[] publicKey) throws Exception {
        IdentityStoreInterface temporaryIdentityStore = new IdentityStore(new PreferenceStoreInterfaceDevNullImpl());
        if (!ListenerUtil.mutListener.listen(41318)) {
            // store identity without server group
            temporaryIdentityStore.storeIdentity(identity, "", publicKey, privateKey);
        }
        // fetching identity group
        APIConnector.FetchIdentityPrivateResult result = this.apiConnector.fetchIdentityPrivate(temporaryIdentityStore);
        if (!ListenerUtil.mutListener.listen(41319)) {
            if (result == null) {
                throw new ThreemaException("fetching private result failed");
            }
        }
        if (!ListenerUtil.mutListener.listen(41320)) {
            this.removeAccount();
        }
        if (!ListenerUtil.mutListener.listen(41321)) {
            // store to the REAL identity store!
            this.identityStore.storeIdentity(identity, result.serverGroup, publicKey, privateKey);
        }
        if (!ListenerUtil.mutListener.listen(41322)) {
            this.sendFlags();
        }
        if (!ListenerUtil.mutListener.listen(41330)) {
            if ((ListenerUtil.mutListener.listen(41328) ? (result.email != null || (ListenerUtil.mutListener.listen(41327) ? (result.email.length() >= 0) : (ListenerUtil.mutListener.listen(41326) ? (result.email.length() <= 0) : (ListenerUtil.mutListener.listen(41325) ? (result.email.length() < 0) : (ListenerUtil.mutListener.listen(41324) ? (result.email.length() != 0) : (ListenerUtil.mutListener.listen(41323) ? (result.email.length() == 0) : (result.email.length() > 0))))))) : (result.email != null && (ListenerUtil.mutListener.listen(41327) ? (result.email.length() >= 0) : (ListenerUtil.mutListener.listen(41326) ? (result.email.length() <= 0) : (ListenerUtil.mutListener.listen(41325) ? (result.email.length() < 0) : (ListenerUtil.mutListener.listen(41324) ? (result.email.length() != 0) : (ListenerUtil.mutListener.listen(41323) ? (result.email.length() == 0) : (result.email.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(41329)) {
                    this.preferenceStore.save(PreferenceStore.PREFS_LINKED_EMAIL, result.email);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41338)) {
            if ((ListenerUtil.mutListener.listen(41336) ? (result.mobileNo != null || (ListenerUtil.mutListener.listen(41335) ? (result.mobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(41334) ? (result.mobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(41333) ? (result.mobileNo.length() < 0) : (ListenerUtil.mutListener.listen(41332) ? (result.mobileNo.length() != 0) : (ListenerUtil.mutListener.listen(41331) ? (result.mobileNo.length() == 0) : (result.mobileNo.length() > 0))))))) : (result.mobileNo != null && (ListenerUtil.mutListener.listen(41335) ? (result.mobileNo.length() >= 0) : (ListenerUtil.mutListener.listen(41334) ? (result.mobileNo.length() <= 0) : (ListenerUtil.mutListener.listen(41333) ? (result.mobileNo.length() < 0) : (ListenerUtil.mutListener.listen(41332) ? (result.mobileNo.length() != 0) : (ListenerUtil.mutListener.listen(41331) ? (result.mobileNo.length() == 0) : (result.mobileNo.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(41337)) {
                    this.preferenceStore.save(PreferenceStore.PREFS_LINKED_MOBILE, result.mobileNo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41339)) {
            // identity has been successfully restored. set push token
            PushUtil.enqueuePushTokenUpdate(context, false, false);
        }
        return true;
    }

    @Override
    public void setPolicyResponse(String responseData, String signature, int policyErrorCode) {
        if (!ListenerUtil.mutListener.listen(41340)) {
            this.policyResponseData = responseData;
        }
        if (!ListenerUtil.mutListener.listen(41341)) {
            this.policySignature = signature;
        }
        if (!ListenerUtil.mutListener.listen(41342)) {
            this.policyErrorCode = policyErrorCode;
        }
    }

    @Override
    public void setCredentials(LicenseService.Credentials credentials) {
        if (!ListenerUtil.mutListener.listen(41343)) {
            this.credentials = credentials;
        }
    }

    @Override
    public boolean sendFlags() {
        boolean success = false;
        try {
            ThreemaFeature.Builder builder = (new ThreemaFeature.Builder()).audio(true).group(true).ballot(true).file(true).voip(true).videocalls(true);
            if (!ListenerUtil.mutListener.listen(41347)) {
                if (this.preferenceService.getTransmittedFeatureLevel() != builder.build()) {
                    if (!ListenerUtil.mutListener.listen(41345)) {
                        this.apiConnector.setFeatureMask(builder, this.identityStore);
                    }
                    if (!ListenerUtil.mutListener.listen(41346)) {
                        this.preferenceService.setTransmittedFeatureLevel(builder.build());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(41348)) {
                success = true;
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(41344)) {
                logger.error("Exception", e);
            }
        }
        return success;
    }

    @Override
    public boolean setRevocationKey(String revocationKey) {
        APIConnector.SetRevocationKeyResult result = null;
        try {
            if (!ListenerUtil.mutListener.listen(41350)) {
                result = this.apiConnector.setRevocationKey(this.identityStore, revocationKey);
            }
            if (!ListenerUtil.mutListener.listen(41353)) {
                if (!result.success) {
                    if (!ListenerUtil.mutListener.listen(41352)) {
                        logger.error("set revocation key failed: " + result.error);
                    }
                    return false;
                } else {
                    if (!ListenerUtil.mutListener.listen(41351)) {
                        // update
                        this.checkRevocationKey(true);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(41349)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    @Override
    public Date getLastRevocationKeySet() {
        return this.preferenceStore.getDate(PreferenceStore.PREFS_LAST_REVOCATION_KEY_SET);
    }

    @Override
    public void checkRevocationKey(boolean force) {
        if (!ListenerUtil.mutListener.listen(41354)) {
            logger.debug("RevocationKey", "check (force = " + force + ")");
        }
        Date lastSet = null;
        try {
            // check if force = true or PREFS_REVOCATION_KEY_CHECKED is false or not set
            boolean check = (ListenerUtil.mutListener.listen(41356) ? (force && !this.preferenceStore.getBoolean(PreferenceStore.PREFS_REVOCATION_KEY_CHECKED)) : (force || !this.preferenceStore.getBoolean(PreferenceStore.PREFS_REVOCATION_KEY_CHECKED)));
            if (!ListenerUtil.mutListener.listen(41357)) {
                logger.debug("RevocationKey", "check = " + check);
            }
            if (!ListenerUtil.mutListener.listen(41365)) {
                if (check) {
                    APIConnector.CheckRevocationKeyResult result = this.apiConnector.checkRevocationKey(this.identityStore);
                    if (!ListenerUtil.mutListener.listen(41364)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(41360)) {
                                if (result.isSet) {
                                    if (!ListenerUtil.mutListener.listen(41359)) {
                                        lastSet = result.lastChanged;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(41361)) {
                                logger.debug("RevocationKey", "result = " + result.isSet);
                            }
                            if (!ListenerUtil.mutListener.listen(41362)) {
                                // update new state
                                this.preferenceStore.save(PreferenceStore.PREFS_LAST_REVOCATION_KEY_SET, lastSet);
                            }
                            if (!ListenerUtil.mutListener.listen(41363)) {
                                // update checked state
                                this.preferenceStore.save(PreferenceStore.PREFS_REVOCATION_KEY_CHECKED, true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(41358)) {
                                logger.debug("RevocationKey", "result is null");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(41355)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public JSONObject createIdentityRequestDataJSON() throws JSONException {
        JSONObject baseObject = new JSONObject();
        BuildFlavor.LicenseType licenseType = BuildFlavor.getLicenseType();
        String deviceId = DeviceIdUtil.getDeviceId(this.context);
        if (!ListenerUtil.mutListener.listen(41367)) {
            if (deviceId != null) {
                if (!ListenerUtil.mutListener.listen(41366)) {
                    baseObject.put("deviceId", deviceId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41383)) {
            if (licenseType == BuildFlavor.LicenseType.GOOGLE) {
                if (!ListenerUtil.mutListener.listen(41381)) {
                    baseObject.put("lvlResponseData", policyResponseData);
                }
                if (!ListenerUtil.mutListener.listen(41382)) {
                    baseObject.put("lvlSignature", policySignature);
                }
            } else if (licenseType == BuildFlavor.LicenseType.HMS) {
                if (!ListenerUtil.mutListener.listen(41379)) {
                    baseObject.put("hmsResponseData", policyResponseData);
                }
                if (!ListenerUtil.mutListener.listen(41380)) {
                    baseObject.put("hmsSignature", policySignature);
                }
            } else {
                String licenseKey = null;
                String licenseUsername = null;
                String licensePassword = null;
                if (!ListenerUtil.mutListener.listen(41372)) {
                    if (this.credentials != null) {
                        if (!ListenerUtil.mutListener.listen(41371)) {
                            if (this.credentials instanceof SerialCredentials) {
                                if (!ListenerUtil.mutListener.listen(41370)) {
                                    licenseKey = ((SerialCredentials) this.credentials).licenseKey;
                                }
                            } else if (this.credentials instanceof UserCredentials) {
                                if (!ListenerUtil.mutListener.listen(41368)) {
                                    licenseUsername = ((UserCredentials) this.credentials).username;
                                }
                                if (!ListenerUtil.mutListener.listen(41369)) {
                                    licensePassword = ((UserCredentials) this.credentials).password;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41374)) {
                    if (licenseKey != null) {
                        if (!ListenerUtil.mutListener.listen(41373)) {
                            baseObject.put("licenseKey", licenseKey);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41376)) {
                    if (licenseUsername != null) {
                        if (!ListenerUtil.mutListener.listen(41375)) {
                            baseObject.put("licenseUsername", licenseUsername);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41378)) {
                    if (licensePassword != null) {
                        if (!ListenerUtil.mutListener.listen(41377)) {
                            baseObject.put("licensePassword", licensePassword);
                        }
                    }
                }
            }
        }
        return baseObject;
    }
}
