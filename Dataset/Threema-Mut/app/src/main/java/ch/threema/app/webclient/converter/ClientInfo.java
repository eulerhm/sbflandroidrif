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
package ch.threema.app.webclient.converter;

import android.content.Context;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.push.PushService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.PushUtil;
import ch.threema.app.webclient.exceptions.ConversionException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class ClientInfo extends Converter {

    private static final Logger logger = LoggerFactory.getLogger(ClientInfo.class);

    // Top level keys
    private static final String DEVICE = "device";

    private static final String OS = "os";

    private static final String OS_VERSION = "osVersion";

    private static final String APP_VERSION = "appVersion";

    private static final String IS_WORK = "isWork";

    private static final String IN_APP_LOGO = "inAppLogo";

    private static final String PUSH_TOKEN = "pushToken";

    private static final String CONFIGURATION = "configuration";

    private static final String CAPABILITIES = "capabilities";

    // Configuration keys
    private static final String VOIP_ENABLED = "voipEnabled";

    private static final String VOIP_FORCE_TURN = "voipForceTurn";

    private static final String LARGE_SINGLE_EMOJI = "largeSingleEmoji";

    private static final String SHOW_INACTIVE_IDS = "showInactiveIDs";

    // Capabilities keys
    private static final String MAX_GROUP_SIZE = "maxGroupSize";

    private static final String MAX_FILE_SIZE = "maxFileSize";

    private static final String DISTRIBUTION_LISTS = "distributionLists";

    private static final String IMAGE_FORMAT = "imageFormat";

    private static final String MDM = "mdm";

    // Image format keys
    private static final String FORMAT_AVATAR = "avatar";

    private static final String FORMAT_THUMBNAIL = "thumbnail";

    // MDM keys
    private static final String DISABLE_ADD_CONTACT = "disableAddContact";

    private static final String DISABLE_CREATE_GROUP = "disableCreateGroup";

    private static final String DISABLE_SAVE_TO_GALLERY = "disableSaveToGallery";

    private static final String DISABLE_EXPORT = "disableExport";

    private static final String DISABLE_MESSAGE_PREVIEW = "disableMessagePreview";

    private static final String DISABLE_CALLS = "disableCalls";

    private static final String READONLY_PROFILE = "readonlyProfile";

    public static MsgpackObjectBuilder convert(@NonNull Context appContext, @Nullable String pushToken) throws ConversionException {
        // Services
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(62601)) {
            if (serviceManager == null) {
                throw new ConversionException("Could not get service manager");
            }
        }
        PreferenceService preferenceService;
        LicenseService licenseService;
        try {
            preferenceService = serviceManager.getPreferenceService();
            licenseService = serviceManager.getLicenseService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(62602)) {
                logger.error("Exception", e);
            }
            throw new ConversionException("Services not available");
        }
        final MsgpackObjectBuilder data = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62603)) {
            data.put(DEVICE, Build.MODEL);
        }
        if (!ListenerUtil.mutListener.listen(62604)) {
            data.put(OS, "android");
        }
        if (!ListenerUtil.mutListener.listen(62605)) {
            data.put(OS_VERSION, Build.VERSION.RELEASE);
        }
        if (!ListenerUtil.mutListener.listen(62606)) {
            data.put(APP_VERSION, ConfigUtils.getFullAppVersion(appContext));
        }
        if (!ListenerUtil.mutListener.listen(62610)) {
            if (pushToken != null) {
                if (!ListenerUtil.mutListener.listen(62609)) {
                    // protocol changes, we'll prefix the push token with "hms;".
                    if (PushService.hmsServicesInstalled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62608)) {
                            data.put(PUSH_TOKEN, "hms;" + pushToken);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(62607)) {
                            data.put(PUSH_TOKEN, pushToken);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62613)) {
            // Work stuff
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(62612)) {
                    data.put(IS_WORK, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62611)) {
                    data.put(IS_WORK, false);
                }
            }
        }
        // Configuration
        final MsgpackObjectBuilder config = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62615)) {
            if (!ConfigUtils.isCallsEnabled(appContext, preferenceService, licenseService)) {
                if (!ListenerUtil.mutListener.listen(62614)) {
                    config.put(VOIP_ENABLED, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62617)) {
            if (preferenceService.getForceTURN()) {
                if (!ListenerUtil.mutListener.listen(62616)) {
                    config.put(VOIP_FORCE_TURN, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62619)) {
            if (!ConfigUtils.isBiggerSingleEmojis(appContext)) {
                if (!ListenerUtil.mutListener.listen(62618)) {
                    config.put(LARGE_SINGLE_EMOJI, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62620)) {
            config.put(SHOW_INACTIVE_IDS, preferenceService.showInactiveContacts());
        }
        // Capabilities
        final MsgpackObjectBuilder capabilities = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62621)) {
            capabilities.put(MAX_GROUP_SIZE, appContext.getResources().getInteger(R.integer.max_group_size));
        }
        if (!ListenerUtil.mutListener.listen(62622)) {
            capabilities.put(MAX_FILE_SIZE, ThreemaApplication.MAX_BLOB_SIZE);
        }
        if (!ListenerUtil.mutListener.listen(62623)) {
            capabilities.put(DISTRIBUTION_LISTS, true);
        }
        // Image format
        final MsgpackObjectBuilder imageFormat = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62624)) {
            imageFormat.put(FORMAT_AVATAR, "image/png");
        }
        if (!ListenerUtil.mutListener.listen(62625)) {
            imageFormat.put(FORMAT_THUMBNAIL, "image/jpeg");
        }
        if (!ListenerUtil.mutListener.listen(62626)) {
            capabilities.put(IMAGE_FORMAT, imageFormat);
        }
        if (!ListenerUtil.mutListener.listen(62642)) {
            // MDM Flags
            if (ConfigUtils.isWorkRestricted()) {
                final MsgpackObjectBuilder mdm = new MsgpackObjectBuilder();
                if (!ListenerUtil.mutListener.listen(62628)) {
                    if (AppRestrictionUtil.isAddContactDisabled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62627)) {
                            mdm.put(DISABLE_ADD_CONTACT, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62630)) {
                    if (AppRestrictionUtil.isCreateGroupDisabled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62629)) {
                            mdm.put(DISABLE_CREATE_GROUP, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62632)) {
                    if (AppRestrictionUtil.isSaveToGalleryDisabled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62631)) {
                            mdm.put(DISABLE_SAVE_TO_GALLERY, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62634)) {
                    if (AppRestrictionUtil.isExportDisabled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62633)) {
                            mdm.put(DISABLE_EXPORT, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62636)) {
                    if (AppRestrictionUtil.isMessagePreviewDisabled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62635)) {
                            mdm.put(DISABLE_MESSAGE_PREVIEW, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62638)) {
                    // TODO account for new th_calls_policy restriction
                    if (AppRestrictionUtil.isCallsDisabled(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62637)) {
                            mdm.put(DISABLE_CALLS, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62640)) {
                    if (AppRestrictionUtil.isReadonlyProfile(appContext)) {
                        if (!ListenerUtil.mutListener.listen(62639)) {
                            mdm.put(READONLY_PROFILE, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62641)) {
                    capabilities.put(MDM, mdm);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62643)) {
            data.put(CONFIGURATION, config);
        }
        if (!ListenerUtil.mutListener.listen(62644)) {
            data.put(CAPABILITIES, capabilities);
        }
        return data;
    }
}
