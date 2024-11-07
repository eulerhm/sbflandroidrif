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
package ch.threema.app.services.license;

import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.APIConnector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class LicenseServiceThreema<T extends LicenseService.Credentials> implements LicenseService<T> {

    protected final APIConnector apiConnector;

    protected final PreferenceService preferenceService;

    private String deviceId;

    private String updateMessage;

    private String updateUrl;

    private boolean updateMessageShown;

    /* not the best place to track this... */
    private boolean isLicensed;

    public LicenseServiceThreema(APIConnector apiConnector, PreferenceService preferenceService, String deviceId) {
        this.apiConnector = apiConnector;
        this.preferenceService = preferenceService;
        if (!ListenerUtil.mutListener.listen(35452)) {
            this.deviceId = deviceId;
        }
        if (!ListenerUtil.mutListener.listen(35453)) {
            this.isLicensed = preferenceService.getLicensedStatus();
        }
    }

    @Override
    public boolean hasCredentials() {
        return (ListenerUtil.mutListener.listen(35454) ? (!TestUtil.empty(this.preferenceService.getSerialNumber()) && !TestUtil.empty(this.preferenceService.getLicenseUsername(), this.preferenceService.getLicensePassword())) : (!TestUtil.empty(this.preferenceService.getSerialNumber()) || !TestUtil.empty(this.preferenceService.getLicenseUsername(), this.preferenceService.getLicensePassword())));
    }

    @Override
    public String validate(T credentials) {
        return this.validate(credentials, false);
    }

    /**
     *  Validate the license credentials and check for updates.
     */
    @Override
    public String validate(T credentials, boolean allowException) {
        APIConnector.CheckLicenseResult result;
        try {
            result = this.checkLicense(credentials, deviceId);
            if (!ListenerUtil.mutListener.listen(35463)) {
                if (result.success) {
                    if (!ListenerUtil.mutListener.listen(35458)) {
                        this.updateMessage = result.updateMessage;
                    }
                    if (!ListenerUtil.mutListener.listen(35459)) {
                        this.updateUrl = result.updateUrl;
                    }
                    if (!ListenerUtil.mutListener.listen(35460)) {
                        // save in preferences
                        this.saveCredentials(credentials);
                    }
                    if (!ListenerUtil.mutListener.listen(35461)) {
                        this.preferenceService.setLicensedStatus(true);
                    }
                    if (!ListenerUtil.mutListener.listen(35462)) {
                        this.isLicensed = true;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(35456)) {
                        this.preferenceService.setLicensedStatus(false);
                    }
                    if (!ListenerUtil.mutListener.listen(35457)) {
                        this.isLicensed = false;
                    }
                    return result.error;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(35455)) {
                if (!allowException) {
                    return e.getMessage();
                }
            }
        }
        return null;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public boolean isUpdateMessageShown() {
        return updateMessageShown;
    }

    public void setUpdateMessageShown(boolean updateMessageShown) {
        if (!ListenerUtil.mutListener.listen(35464)) {
            this.updateMessageShown = updateMessageShown;
        }
    }

    @Override
    public boolean isLicensed() {
        return this.isLicensed;
    }

    @Override
    public String validate(boolean allowException) {
        T credentials = this.loadCredentials();
        if (!ListenerUtil.mutListener.listen(35465)) {
            if (credentials != null) {
                return this.validate(credentials, allowException);
            }
        }
        return "no license";
    }

    protected abstract APIConnector.CheckLicenseResult checkLicense(T credentials, String deviceId) throws Exception;

    protected abstract void saveCredentials(T credentials);
}
