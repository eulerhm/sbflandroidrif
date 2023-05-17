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
package ch.threema.app.jobs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.core.app.FixedJobIntentService;
import androidx.preference.PreferenceManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.UpdateAppLogoRoutine;
import ch.threema.app.routines.UpdateWorkInfoRoutine;
import ch.threema.app.services.AppRestrictionService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.UserCredentials;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.VerificationLevel;
import ch.threema.client.APIConnector;
import ch.threema.client.work.WorkContact;
import ch.threema.client.work.WorkData;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WorkSyncService extends FixedJobIntentService {

    private static final Logger logger = LoggerFactory.getLogger(WorkSyncService.class);

    private static final int JOB_ID = 2004;

    public static final String EXTRA_WORK_UPDATE_RESTRICTIONS_ONLY = "reon";

    private static boolean isRunning;

    private static boolean forceUpdate = false;

    private ServiceManager serviceManager;

    private ContactService contactService;

    private PreferenceService preferenceService;

    private FileService fileService;

    private LicenseService licenseService;

    private APIConnector apiConnector;

    private NotificationService notificationService;

    private UserService userService;

    private IdentityStore identityStore;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(28401)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(28402)) {
            isRunning = true;
        }
        try {
            if (!ListenerUtil.mutListener.listen(28403)) {
                serviceManager = ThreemaApplication.getServiceManager();
            }
            if (!ListenerUtil.mutListener.listen(28404)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(28405)) {
                preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(28406)) {
                licenseService = serviceManager.getLicenseService();
            }
            if (!ListenerUtil.mutListener.listen(28407)) {
                fileService = serviceManager.getFileService();
            }
            if (!ListenerUtil.mutListener.listen(28408)) {
                notificationService = serviceManager.getNotificationService();
            }
            if (!ListenerUtil.mutListener.listen(28409)) {
                userService = serviceManager.getUserService();
            }
            if (!ListenerUtil.mutListener.listen(28410)) {
                apiConnector = serviceManager.getAPIConnector();
            }
            if (!ListenerUtil.mutListener.listen(28411)) {
                identityStore = serviceManager.getIdentityStore();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(28412)) {
            isRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(28413)) {
            super.onDestroy();
        }
    }

    /**
     *  Convenience method for enqueuing work in to this service.
     */
    public static void enqueueWork(Context context, Intent work, boolean force) {
        if (!ListenerUtil.mutListener.listen(28414)) {
            logger.trace("enqueueWork");
        }
        if (!ListenerUtil.mutListener.listen(28415)) {
            if (isRunning())
                return;
        }
        if (!ListenerUtil.mutListener.listen(28416)) {
            forceUpdate = force;
        }
        if (!ListenerUtil.mutListener.listen(28417)) {
            logger.trace("forceUpdate = " + forceUpdate);
        }
        if (!ListenerUtil.mutListener.listen(28418)) {
            enqueueWork(context, WorkSyncService.class, JOB_ID, work);
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (!ListenerUtil.mutListener.listen(28419)) {
            logger.trace("onHandleWork");
        }
        if (!ListenerUtil.mutListener.listen(28421)) {
            if (!ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(28420)) {
                    logger.error("Not allowed to run routine in a non work environment");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28423)) {
            if (this.licenseService == null) {
                if (!ListenerUtil.mutListener.listen(28422)) {
                    logger.trace("license service not available");
                }
                return;
            }
        }
        LicenseService.Credentials credentials = this.licenseService.loadCredentials();
        if (!ListenerUtil.mutListener.listen(28424)) {
            if (!(credentials instanceof UserCredentials)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28425)) {
            logger.trace("showNotification");
        }
        if (!ListenerUtil.mutListener.listen(28426)) {
            notificationService.showWorkSyncProgress();
        }
        if (!ListenerUtil.mutListener.listen(28469)) {
            if (!intent.getBooleanExtra(EXTRA_WORK_UPDATE_RESTRICTIONS_ONLY, false)) {
                WorkData workData = null;
                try {
                    List<ContactModel> allContacts = this.contactService.getAll(true, true);
                    String[] identities = new String[allContacts.size()];
                    if (!ListenerUtil.mutListener.listen(28435)) {
                        {
                            long _loopCounter178 = 0;
                            for (int n = 0; (ListenerUtil.mutListener.listen(28434) ? (n >= allContacts.size()) : (ListenerUtil.mutListener.listen(28433) ? (n <= allContacts.size()) : (ListenerUtil.mutListener.listen(28432) ? (n > allContacts.size()) : (ListenerUtil.mutListener.listen(28431) ? (n != allContacts.size()) : (ListenerUtil.mutListener.listen(28430) ? (n == allContacts.size()) : (n < allContacts.size())))))); n++) {
                                ListenerUtil.loopListener.listen("_loopCounter178", ++_loopCounter178);
                                if (!ListenerUtil.mutListener.listen(28429)) {
                                    identities[n] = allContacts.get(n).getIdentity();
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28436)) {
                        workData = this.apiConnector.fetchWorkData(((UserCredentials) credentials).username, ((UserCredentials) credentials).password, identities);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(28427)) {
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(28428)) {
                        notificationService.cancelWorkSyncProgress();
                    }
                    return;
                }
                if (!ListenerUtil.mutListener.listen(28468)) {
                    if (workData != null) {
                        /*				logger.trace("workData contacts size = " + workData.workContacts.size());

				logger.debug("data found");
				logger.debug("checkInterval: " + workData.checkInterval);
				logger.debug("supportUrl: " + (null != workData.supportUrl ? "yes" : "no"));
				logger.debug("logos: " + ((null != workData.logoDark ? 1 : 0) + (null != workData.logoLight ? 1 : 0)));
				//get all saved work contacts
				logger.debug("contacts: " + workData.workContacts.size());
*/
                        List<ContactModel> existingWorkContacts = this.contactService.getIsWork();
                        if (!ListenerUtil.mutListener.listen(28440)) {
                            {
                                long _loopCounter179 = 0;
                                for (WorkContact workContact : workData.workContacts) {
                                    ListenerUtil.loopListener.listen("_loopCounter179", ++_loopCounter179);
                                    if (!ListenerUtil.mutListener.listen(28439)) {
                                        contactService.addWorkContact(workContact, existingWorkContacts);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28450)) {
                            {
                                long _loopCounter180 = 0;
                                // downgrade work contacts
                                for (int x = 0; (ListenerUtil.mutListener.listen(28449) ? (x >= existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(28448) ? (x <= existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(28447) ? (x > existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(28446) ? (x != existingWorkContacts.size()) : (ListenerUtil.mutListener.listen(28445) ? (x == existingWorkContacts.size()) : (x < existingWorkContacts.size())))))); x++) {
                                    ListenerUtil.loopListener.listen("_loopCounter180", ++_loopCounter180);
                                    // remove isWork flag
                                    ContactModel c = existingWorkContacts.get(x);
                                    if (!ListenerUtil.mutListener.listen(28441)) {
                                        c.setIsWork(false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(28443)) {
                                        if (c.getVerificationLevel() != VerificationLevel.FULLY_VERIFIED) {
                                            if (!ListenerUtil.mutListener.listen(28442)) {
                                                c.setVerificationLevel(VerificationLevel.UNVERIFIED);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(28444)) {
                                        this.contactService.save(c);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28451)) {
                            // start a new thread to lazy download the app icons
                            logger.trace("start update app icon routine");
                        }
                        if (!ListenerUtil.mutListener.listen(28452)) {
                            new Thread(new UpdateAppLogoRoutine(this.fileService, this.preferenceService, workData.logoLight, workData.logoDark, forceUpdate), "UpdateAppIcon").start();
                        }
                        if (!ListenerUtil.mutListener.listen(28453)) {
                            this.preferenceService.setCustomSupportUrl(workData.supportUrl);
                        }
                        if (!ListenerUtil.mutListener.listen(28455)) {
                            if (workData.mdm.parameters != null) {
                                if (!ListenerUtil.mutListener.listen(28454)) {
                                    // Save the Mini-MDM Parameters to a local file
                                    AppRestrictionService.getInstance().storeWorkMDMSettings(workData.mdm);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28456)) {
                            // update work info
                            new UpdateWorkInfoRoutine(this, this.apiConnector, this.identityStore, null, this.licenseService).run();
                        }
                        if (!ListenerUtil.mutListener.listen(28457)) {
                            this.preferenceService.setWorkDirectoryEnabled(workData.directory.enabled);
                        }
                        if (!ListenerUtil.mutListener.listen(28458)) {
                            this.preferenceService.setWorkDirectoryCategories(workData.directory.categories);
                        }
                        if (!ListenerUtil.mutListener.listen(28459)) {
                            this.preferenceService.setWorkOrganization(workData.organization);
                        }
                        if (!ListenerUtil.mutListener.listen(28460)) {
                            logger.trace("workData checkInterval = " + workData.checkInterval);
                        }
                        if (!ListenerUtil.mutListener.listen(28467)) {
                            if ((ListenerUtil.mutListener.listen(28465) ? (workData.checkInterval >= 0) : (ListenerUtil.mutListener.listen(28464) ? (workData.checkInterval <= 0) : (ListenerUtil.mutListener.listen(28463) ? (workData.checkInterval < 0) : (ListenerUtil.mutListener.listen(28462) ? (workData.checkInterval != 0) : (ListenerUtil.mutListener.listen(28461) ? (workData.checkInterval == 0) : (workData.checkInterval > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(28466)) {
                                    // schedule next interval
                                    this.preferenceService.setWorkSyncCheckInterval(workData.checkInterval);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(28437)) {
                            logger.trace("workData == null");
                        }
                        if (!ListenerUtil.mutListener.listen(28438)) {
                            this.preferenceService.clearAppLogos();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28470)) {
            resetRestrictions();
        }
        if (!ListenerUtil.mutListener.listen(28471)) {
            notificationService.cancelWorkSyncProgress();
        }
        if (!ListenerUtil.mutListener.listen(28472)) {
            logger.trace("deleteNotification");
        }
    }

    private void resetRestrictions() {
        if (!ListenerUtil.mutListener.listen(28473)) {
            /* note that PreferenceService may not be available at this time */
            logger.debug("resetRestrictions");
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!ListenerUtil.mutListener.listen(28500)) {
            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(28499)) {
                    if (editor != null) {
                        Boolean preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__block_unknown));
                        if (!ListenerUtil.mutListener.listen(28475)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28474)) {
                                    editor.putBoolean(getString(R.string.preferences__block_unknown), preset);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28476)) {
                            preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_screenshots));
                        }
                        if (!ListenerUtil.mutListener.listen(28478)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28477)) {
                                    editor.putBoolean(getString(R.string.preferences__hide_screenshots), preset);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28479)) {
                            preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_save_to_gallery));
                        }
                        if (!ListenerUtil.mutListener.listen(28481)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28480)) {
                                    editor.putBoolean(getString(R.string.preferences__save_media), !preset);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28482)) {
                            preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_message_preview));
                        }
                        if (!ListenerUtil.mutListener.listen(28484)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28483)) {
                                    editor.putBoolean(getString(R.string.preferences__notification_preview), !preset);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28485)) {
                            preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_send_profile_picture));
                        }
                        if (!ListenerUtil.mutListener.listen(28487)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28486)) {
                                    editor.putInt(getString(R.string.preferences__profile_pic_release), preset ? PreferenceService.PROFILEPIC_RELEASE_NOBODY : PreferenceService.PROFILEPIC_RELEASE_EVERYONE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28488)) {
                            preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_calls));
                        }
                        if (!ListenerUtil.mutListener.listen(28490)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28489)) {
                                    editor.putBoolean(getString(R.string.preferences__voip_enable), !preset);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28491)) {
                            preset = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__hide_inactive_ids));
                        }
                        if (!ListenerUtil.mutListener.listen(28493)) {
                            if (preset != null) {
                                if (!ListenerUtil.mutListener.listen(28492)) {
                                    editor.putBoolean(getString(R.string.preferences__show_inactive_contacts), !preset);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(28494)) {
                            editor.apply();
                        }
                        String sPreset = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__nickname));
                        if (!ListenerUtil.mutListener.listen(28498)) {
                            if ((ListenerUtil.mutListener.listen(28495) ? (sPreset != null || userService != null) : (sPreset != null && userService != null))) {
                                if (!ListenerUtil.mutListener.listen(28497)) {
                                    if (!TestUtil.compare(userService.getPublicNickname(), sPreset)) {
                                        if (!ListenerUtil.mutListener.listen(28496)) {
                                            userService.setPublicNickname(sPreset);
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
