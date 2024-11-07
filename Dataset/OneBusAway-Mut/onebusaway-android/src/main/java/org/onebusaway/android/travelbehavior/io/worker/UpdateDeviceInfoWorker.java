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
package org.onebusaway.android.travelbehavior.io.worker;

import com.google.android.gms.common.GoogleApiAvailability;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.model.DeviceInformation;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorFirebaseIOUtils;
import org.onebusaway.android.util.PreferenceUtils;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import static android.content.Context.ACCESSIBILITY_SERVICE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UpdateDeviceInfoWorker extends Worker {

    private static final String TAG = "TripPlanReadWorker";

    public UpdateDeviceInfoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(9634)) {
            saveDeviceInformation();
        }
        return Result.success();
    }

    private void saveDeviceInformation() {
        PackageManager pm = getApplicationContext().getPackageManager();
        PackageInfo appInfoOba;
        PackageInfo appInfoGps;
        String obaVersion = "";
        String googlePlayServicesAppVersion = "";
        try {
            appInfoOba = pm.getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            if (!ListenerUtil.mutListener.listen(9635)) {
                obaVersion = appInfoOba.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        try {
            appInfoGps = pm.getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0);
            if (!ListenerUtil.mutListener.listen(9636)) {
                googlePlayServicesAppVersion = appInfoGps.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        AccessibilityManager am = (AccessibilityManager) getApplicationContext().getSystemService(ACCESSIBILITY_SERVICE);
        Boolean isTalkBackEnabled = am.isTouchExplorationEnabled();
        PowerManager powerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        Boolean isPowerSaveModeActive = null;
        if (!ListenerUtil.mutListener.listen(9643)) {
            if ((ListenerUtil.mutListener.listen(9641) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(9640) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(9639) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(9638) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(9637) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(9642)) {
                    isPowerSaveModeActive = powerManager.isPowerSaveMode();
                }
            }
        }
        Boolean isIgnoringBatteryOptimizations = null;
        if (!ListenerUtil.mutListener.listen(9650)) {
            if ((ListenerUtil.mutListener.listen(9648) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9647) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9646) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9645) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9644) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(9649)) {
                    isIgnoringBatteryOptimizations = Application.isIgnoringBatteryOptimizations(getApplicationContext());
                }
            }
        }
        DeviceInformation di = new DeviceInformation(obaVersion, Build.MODEL, Build.VERSION.RELEASE, Build.VERSION.SDK_INT, googlePlayServicesAppVersion, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, Application.get().getCurrentRegion().getId(), isTalkBackEnabled, isPowerSaveModeActive, isIgnoringBatteryOptimizations);
        int hashCode = di.hashCode();
        int mostRecentDeviceHash = PreferenceUtils.getInt(TravelBehaviorConstants.DEVICE_INFO_HASH, -1);
        String uid = getInputData().getString(TravelBehaviorConstants.USER_ID);
        if (!ListenerUtil.mutListener.listen(9658)) {
            // Update if the device info changed
            if ((ListenerUtil.mutListener.listen(9656) ? ((ListenerUtil.mutListener.listen(9655) ? (hashCode >= mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9654) ? (hashCode <= mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9653) ? (hashCode > mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9652) ? (hashCode < mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9651) ? (hashCode == mostRecentDeviceHash) : (hashCode != mostRecentDeviceHash)))))) || uid != null) : ((ListenerUtil.mutListener.listen(9655) ? (hashCode >= mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9654) ? (hashCode <= mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9653) ? (hashCode > mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9652) ? (hashCode < mostRecentDeviceHash) : (ListenerUtil.mutListener.listen(9651) ? (hashCode == mostRecentDeviceHash) : (hashCode != mostRecentDeviceHash)))))) && uid != null))) {
                String recordId = Long.toString(System.currentTimeMillis());
                if (!ListenerUtil.mutListener.listen(9657)) {
                    TravelBehaviorFirebaseIOUtils.saveDeviceInfo(di, uid, recordId, hashCode);
                }
            }
        }
    }
}
