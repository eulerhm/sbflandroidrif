/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
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
package org.onebusaway.android.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import org.onebusaway.android.BuildConfig;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A class containing utility methods related to unit tests
 */
public class TestUtils {

    static String CATEGORY_TEST = BuildConfig.APPLICATION_ID + ".category.TEST";

    static String ACTION_LOAD_FINISHED = BuildConfig.APPLICATION_ID + ".LOAD_FINISHED";

    static void notifyLoadFinished(Context context) {
        if (!ListenerUtil.mutListener.listen(6422)) {
            sendBroadcast(context, CATEGORY_TEST, ACTION_LOAD_FINISHED);
        }
    }

    static void sendBroadcast(Context context, String action, String category) {
        if (!ListenerUtil.mutListener.listen(6426)) {
            if (BuildConfig.DEBUG) {
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(6423)) {
                    intent.setAction(action);
                }
                if (!ListenerUtil.mutListener.listen(6424)) {
                    intent.addCategory(category);
                }
                if (!ListenerUtil.mutListener.listen(6425)) {
                    context.sendBroadcast(intent);
                }
            }
        }
    }

    public static void waitForLoadFinished(Context context) throws InterruptedException {
        if (!ListenerUtil.mutListener.listen(6427)) {
            waitForBroadcast(context, ACTION_LOAD_FINISHED, CATEGORY_TEST);
        }
    }

    public static void waitForBroadcast(Context context, String action, String category) throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        IntentFilter intentFilter = new IntentFilter(action);
        if (!ListenerUtil.mutListener.listen(6428)) {
            intentFilter.addCategory(category);
        }
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (!ListenerUtil.mutListener.listen(6429)) {
                    signal.countDown();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(6430)) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
        if (!ListenerUtil.mutListener.listen(6431)) {
            signal.await(10000, TimeUnit.MILLISECONDS);
        }
        if (!ListenerUtil.mutListener.listen(6432)) {
            context.unregisterReceiver(broadcastReceiver);
        }
        if (!ListenerUtil.mutListener.listen(6433)) {
            Thread.sleep(1000);
        }
    }

    /**
     * Returns true if tests are running on an emulator, false if tests are running
     * on an actual device
     *
     * @return true if tests are running on an emulator, false if tests are running
     * on an actual device
     */
    public static boolean isRunningOnEmulator() {
        return Build.FINGERPRINT.contains("generic");
    }

    /**
     * Returns true if the test is running on CI, and false if it is not
     *
     * @return true if the test is running on CI, and false if it is not
     */
    public static boolean isRunningOnCI() {
        return (ListenerUtil.mutListener.listen(6434) ? (BuildConfig.CI != null || BuildConfig.CI.equals("true")) : (BuildConfig.CI != null && BuildConfig.CI.equals("true")));
    }
}
