/*
 * Copyright (C) 2019 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.travelbehavior.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeviceInformation {

    public String appVersion;

    public String deviceModel;

    public String sdkVersion;

    public Integer sdkVersionInt;

    public String googlePlayServicesApp;

    public Integer googlePlayServicesLib;

    public Long regionId;

    public Boolean isTalkBackEnabled;

    public String timestamp;

    public Boolean isPowerSaveModeEnabled;

    public Boolean isIgnoringBatteryOptimizations;

    public DeviceInformation(String appVersion, String deviceModel, String sdkVersion, Integer sdkVersionInt, String googlePlayServicesApp, Integer googlePlayServicesLib, Long regionId, Boolean isTalkBackEnabled, Boolean isPowerSaveModeEnabled, Boolean isIgnoringBatteryOptimizations) {
        if (!ListenerUtil.mutListener.listen(9873)) {
            this.appVersion = appVersion;
        }
        if (!ListenerUtil.mutListener.listen(9874)) {
            this.deviceModel = deviceModel;
        }
        if (!ListenerUtil.mutListener.listen(9875)) {
            this.sdkVersion = sdkVersion;
        }
        if (!ListenerUtil.mutListener.listen(9876)) {
            this.sdkVersionInt = sdkVersionInt;
        }
        if (!ListenerUtil.mutListener.listen(9877)) {
            this.googlePlayServicesApp = googlePlayServicesApp;
        }
        if (!ListenerUtil.mutListener.listen(9878)) {
            this.googlePlayServicesLib = googlePlayServicesLib;
        }
        if (!ListenerUtil.mutListener.listen(9879)) {
            this.regionId = regionId;
        }
        if (!ListenerUtil.mutListener.listen(9880)) {
            this.isTalkBackEnabled = isTalkBackEnabled;
        }
        if (!ListenerUtil.mutListener.listen(9881)) {
            this.isPowerSaveModeEnabled = isPowerSaveModeEnabled;
        }
        if (!ListenerUtil.mutListener.listen(9882)) {
            this.isIgnoringBatteryOptimizations = isIgnoringBatteryOptimizations;
        }
    }

    public void setTimestamp(String timestamp) {
        if (!ListenerUtil.mutListener.listen(9883)) {
            this.timestamp = timestamp;
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder().append(appVersion).append(deviceModel).append(sdkVersion).append(sdkVersionInt).append(googlePlayServicesApp).append(googlePlayServicesLib).append(regionId).append(isTalkBackEnabled);
        if (!ListenerUtil.mutListener.listen(9885)) {
            if (isPowerSaveModeEnabled != null) {
                if (!ListenerUtil.mutListener.listen(9884)) {
                    builder.append(isPowerSaveModeEnabled);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9887)) {
            if (isIgnoringBatteryOptimizations != null) {
                if (!ListenerUtil.mutListener.listen(9886)) {
                    builder.append(isIgnoringBatteryOptimizations);
                }
            }
        }
        return builder.toHashCode();
    }
}
