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

import android.content.Context;
import android.content.RestrictionsManager;
import android.os.Build;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.Map;
import androidx.annotation.WorkerThread;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.license.UserCredentials;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.APIConnector;
import ch.threema.client.work.WorkData;
import ch.threema.client.work.WorkMDMSettings;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Hold all Work App Restrictions
 */
public class AppRestrictionService {

    private static final Logger logger = LoggerFactory.getLogger(AppRestrictionService.class);

    private Bundle appRestrictions;

    private volatile WorkMDMSettings workMDMSettings;

    private static String PREFERENCE_KEY = "wrk_app_restriction";

    /**
     *  Save the given WorkMDMSettings and reload the AppRestrictions
     */
    public boolean storeWorkMDMSettings(final WorkMDMSettings settings) {
        if (!ListenerUtil.mutListener.listen(36618)) {
            if (this.workMDMSettings != settings) {
                if (!ListenerUtil.mutListener.listen(36617)) {
                    if ((ListenerUtil.mutListener.listen(36613) ? (ThreemaApplication.getServiceManager() != null || ThreemaApplication.getServiceManager().getPreferenceStore() != null) : (ThreemaApplication.getServiceManager() != null && ThreemaApplication.getServiceManager().getPreferenceStore() != null))) {
                        if (!ListenerUtil.mutListener.listen(36614)) {
                            ThreemaApplication.getServiceManager().getPreferenceStore().save(PREFERENCE_KEY, convert(settings), true);
                        }
                        if (!ListenerUtil.mutListener.listen(36615)) {
                            this.workMDMSettings = settings;
                        }
                        if (!ListenerUtil.mutListener.listen(36616)) {
                            this.reload();
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     *  Get the current fetched or saved WorkMDMSettings
     */
    public WorkMDMSettings getWorkMDMSettings() {
        if (!ListenerUtil.mutListener.listen(36623)) {
            if (this.workMDMSettings == null) {
                if (!ListenerUtil.mutListener.listen(36622)) {
                    // Load from preference store
                    if ((ListenerUtil.mutListener.listen(36619) ? (ThreemaApplication.getServiceManager() != null || ThreemaApplication.getServiceManager().getPreferenceStore() != null) : (ThreemaApplication.getServiceManager() != null && ThreemaApplication.getServiceManager().getPreferenceStore() != null))) {
                        JSONObject object = ThreemaApplication.getServiceManager().getPreferenceStore().getJSONObject(PREFERENCE_KEY, true);
                        if (!ListenerUtil.mutListener.listen(36621)) {
                            if (object != null) {
                                if (!ListenerUtil.mutListener.listen(36620)) {
                                    this.workMDMSettings = this.convert(object);
                                }
                            }
                        }
                    }
                }
            }
        }
        return this.workMDMSettings;
    }

    /**
     *  Fetch the MDM Settings
     */
    @WorkerThread
    public boolean fetchAndStoreWorkMDMSettings(APIConnector apiConnector, UserCredentials credentials) throws Exception {
        if (!ListenerUtil.mutListener.listen(36625)) {
            // Verify notnull instances
            if ((ListenerUtil.mutListener.listen(36624) ? (apiConnector == null && credentials == null) : (apiConnector == null || credentials == null))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(36626)) {
            if (RuntimeUtil.isOnUiThread()) {
                throw new ThreemaException("failed to fetch MDM settings in the main thread");
            }
        }
        // Fetch data from work
        WorkData result = apiConnector.fetchWorkData(credentials.username, credentials.password, new String[] {});
        return (ListenerUtil.mutListener.listen(36627) ? (null != result || this.storeWorkMDMSettings(result.mdm)) : (null != result && this.storeWorkMDMSettings(result.mdm)));
    }

    /**
     *  Reload restriction (without fetching work data)
     */
    public void reload() {
        if (!ListenerUtil.mutListener.listen(36634)) {
            if ((ListenerUtil.mutListener.listen(36632) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36631) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36630) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36629) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36628) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP))))))) {
                RestrictionsManager restrictionsManager = (RestrictionsManager) ThreemaApplication.getAppContext().getSystemService(Context.RESTRICTIONS_SERVICE);
                if (!ListenerUtil.mutListener.listen(36633)) {
                    this.appRestrictions = restrictionsManager.getApplicationRestrictions();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36636)) {
            if (this.appRestrictions == null) {
                if (!ListenerUtil.mutListener.listen(36635)) {
                    this.appRestrictions = new Bundle();
                }
            }
        }
        WorkMDMSettings settings = this.getWorkMDMSettings();
        if (!ListenerUtil.mutListener.listen(36646)) {
            // Get Mini MDM Settings and override
            if (settings != null) {
                if (!ListenerUtil.mutListener.listen(36645)) {
                    {
                        long _loopCounter359 = 0;
                        for (Map.Entry<String, Object> miniMDMSetting : settings.parameters.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter359", ++_loopCounter359);
                            if (!ListenerUtil.mutListener.listen(36644)) {
                                if ((ListenerUtil.mutListener.listen(36637) ? (settings.override && !appRestrictions.containsKey(miniMDMSetting.getKey())) : (settings.override || !appRestrictions.containsKey(miniMDMSetting.getKey())))) {
                                    if (!ListenerUtil.mutListener.listen(36643)) {
                                        if (miniMDMSetting.getValue() instanceof Integer) {
                                            if (!ListenerUtil.mutListener.listen(36642)) {
                                                appRestrictions.putInt(miniMDMSetting.getKey(), (Integer) miniMDMSetting.getValue());
                                            }
                                        } else if (miniMDMSetting.getValue() instanceof Boolean) {
                                            if (!ListenerUtil.mutListener.listen(36641)) {
                                                appRestrictions.putBoolean(miniMDMSetting.getKey(), (Boolean) miniMDMSetting.getValue());
                                            }
                                        } else if (miniMDMSetting.getValue() instanceof String) {
                                            if (!ListenerUtil.mutListener.listen(36640)) {
                                                appRestrictions.putString(miniMDMSetting.getKey(), (String) miniMDMSetting.getValue());
                                            }
                                        } else if (miniMDMSetting.getValue() instanceof Long) {
                                            if (!ListenerUtil.mutListener.listen(36639)) {
                                                appRestrictions.putLong(miniMDMSetting.getKey(), (Long) miniMDMSetting.getValue());
                                            }
                                        } else if (miniMDMSetting.getValue() instanceof Double) {
                                            if (!ListenerUtil.mutListener.listen(36638)) {
                                                appRestrictions.putDouble(miniMDMSetting.getKey(), (Double) miniMDMSetting.getValue());
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

    public Bundle getAppRestrictions() {
        return this.appRestrictions;
    }

    /**
     *  Convert a json Object to a valid WorkMDMSettings object
     */
    public WorkMDMSettings convert(JSONObject jsonObject) {
        WorkMDMSettings settings = new WorkMDMSettings();
        if (!ListenerUtil.mutListener.listen(36653)) {
            if (null != jsonObject) {
                try {
                    if (!ListenerUtil.mutListener.listen(36649)) {
                        if (jsonObject.has("override")) {
                            if (!ListenerUtil.mutListener.listen(36648)) {
                                settings.override = jsonObject.getBoolean("override");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36652)) {
                        if (jsonObject.has("parameters")) {
                            JSONObject parameters = jsonObject.getJSONObject("parameters");
                            Iterator<String> keys = parameters.keys();
                            if (!ListenerUtil.mutListener.listen(36651)) {
                                {
                                    long _loopCounter360 = 0;
                                    while (keys.hasNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter360", ++_loopCounter360);
                                        String key = keys.next();
                                        if (!ListenerUtil.mutListener.listen(36650)) {
                                            settings.parameters.put(key, parameters.get(key));
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (JSONException x) {
                    if (!ListenerUtil.mutListener.listen(36647)) {
                        logger.error("failed to convert json to WorkMDMSettings", x);
                    }
                }
            }
        }
        return settings;
    }

    /**
     *  Convert a WorkMDMSettings Object to a valid JSON Object
     */
    public JSONObject convert(WorkMDMSettings mdmSettings) {
        JSONObject json = new JSONObject();
        if (!ListenerUtil.mutListener.listen(36660)) {
            if (mdmSettings != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(36655)) {
                        json.put("override", mdmSettings.override);
                    }
                    JSONObject parameters = new JSONObject();
                    if (!ListenerUtil.mutListener.listen(36658)) {
                        if (mdmSettings.parameters != null) {
                            if (!ListenerUtil.mutListener.listen(36657)) {
                                {
                                    long _loopCounter361 = 0;
                                    for (Map.Entry<String, Object> settings : mdmSettings.parameters.entrySet()) {
                                        ListenerUtil.loopListener.listen("_loopCounter361", ++_loopCounter361);
                                        if (!ListenerUtil.mutListener.listen(36656)) {
                                            parameters.put(settings.getKey(), settings.getValue());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36659)) {
                        json.put("parameters", parameters);
                    }
                } catch (JSONException x) {
                    if (!ListenerUtil.mutListener.listen(36654)) {
                        logger.error("failed to convert WorkMDMSettings to json", x);
                    }
                    return null;
                }
            }
        }
        return json;
    }

    /**
     * ********************************************************************************************
     *  Singleton Stuff
     * *********************************************************************************************
     */
    private static volatile AppRestrictionService instance;

    private static final Object lock = new Object();

    public static AppRestrictionService getInstance() {
        if (!ListenerUtil.mutListener.listen(36663)) {
            if (instance == null) {
                synchronized (lock) {
                    if (!ListenerUtil.mutListener.listen(36662)) {
                        if (instance == null) {
                            if (!ListenerUtil.mutListener.listen(36661)) {
                                instance = new AppRestrictionService();
                            }
                        }
                    }
                }
            }
        }
        return instance;
    }
}
