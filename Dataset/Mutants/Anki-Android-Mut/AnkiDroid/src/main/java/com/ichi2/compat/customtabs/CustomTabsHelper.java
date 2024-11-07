// Copyright 2015 Google Inc. All Rights Reserved.
package com.ichi2.compat.customtabs;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsService;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class for Custom Tabs.
 */
public class CustomTabsHelper {

    private static final String TAG = "CustomTabsHelper";

    static final String STABLE_PACKAGE = "com.android.chrome";

    static final String BETA_PACKAGE = "com.chrome.beta";

    static final String DEV_PACKAGE = "com.chrome.dev";

    static final String LOCAL_PACKAGE = "com.google.android.apps.chrome";

    private static final String EXTRA_CUSTOM_TABS_KEEP_ALIVE = "android.support.customtabs.extra.KEEP_ALIVE";

    private static String sPackageNameToUse;

    private CustomTabsHelper() {
    }

    public static void addKeepAliveExtra(Context context, Intent intent) {
        Intent keepAliveIntent = new Intent().setClassName(context.getPackageName(), KeepAliveService.class.getCanonicalName());
        if (!ListenerUtil.mutListener.listen(13198)) {
            intent.putExtra(EXTRA_CUSTOM_TABS_KEEP_ALIVE, keepAliveIntent);
        }
    }

    /**
     * Goes through all apps that handle VIEW intents and have a warmup service. Picks
     * the one chosen by the user if there is one, otherwise makes a best effort to return a
     * valid package name.
     *
     * This is <strong>not</strong> threadsafe.
     *
     * @param context {@link Context} to use for accessing {@link PackageManager}.
     * @return The package name recommended to use for connecting to custom tabs related components.
     */
    public static String getPackageNameToUse(Context context) {
        if (!ListenerUtil.mutListener.listen(13199)) {
            if (sPackageNameToUse != null)
                return sPackageNameToUse;
        }
        PackageManager pm = context.getPackageManager();
        // Get default VIEW intent handler.
        Intent activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"));
        ResolveInfo defaultViewHandlerInfo = pm.resolveActivity(activityIntent, 0);
        String defaultViewHandlerPackageName = null;
        if (!ListenerUtil.mutListener.listen(13201)) {
            if (defaultViewHandlerInfo != null) {
                if (!ListenerUtil.mutListener.listen(13200)) {
                    defaultViewHandlerPackageName = defaultViewHandlerInfo.activityInfo.packageName;
                }
            }
        }
        // Get all apps that can handle VIEW intents.
        List<ResolveInfo> resolvedActivityList = pm.queryIntentActivities(activityIntent, 0);
        List<String> packagesSupportingCustomTabs = new ArrayList<>(resolvedActivityList.size());
        if (!ListenerUtil.mutListener.listen(13206)) {
            {
                long _loopCounter235 = 0;
                for (ResolveInfo info : resolvedActivityList) {
                    ListenerUtil.loopListener.listen("_loopCounter235", ++_loopCounter235);
                    Intent serviceIntent = new Intent();
                    if (!ListenerUtil.mutListener.listen(13202)) {
                        serviceIntent.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
                    }
                    if (!ListenerUtil.mutListener.listen(13203)) {
                        serviceIntent.setPackage(info.activityInfo.packageName);
                    }
                    if (!ListenerUtil.mutListener.listen(13205)) {
                        if (pm.resolveService(serviceIntent, 0) != null) {
                            if (!ListenerUtil.mutListener.listen(13204)) {
                                packagesSupportingCustomTabs.add(info.activityInfo.packageName);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13221)) {
            // and service calls.
            if (packagesSupportingCustomTabs.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(13220)) {
                    sPackageNameToUse = null;
                }
            } else if ((ListenerUtil.mutListener.listen(13211) ? (packagesSupportingCustomTabs.size() >= 1) : (ListenerUtil.mutListener.listen(13210) ? (packagesSupportingCustomTabs.size() <= 1) : (ListenerUtil.mutListener.listen(13209) ? (packagesSupportingCustomTabs.size() > 1) : (ListenerUtil.mutListener.listen(13208) ? (packagesSupportingCustomTabs.size() < 1) : (ListenerUtil.mutListener.listen(13207) ? (packagesSupportingCustomTabs.size() != 1) : (packagesSupportingCustomTabs.size() == 1))))))) {
                if (!ListenerUtil.mutListener.listen(13219)) {
                    sPackageNameToUse = packagesSupportingCustomTabs.get(0);
                }
            } else if ((ListenerUtil.mutListener.listen(13213) ? ((ListenerUtil.mutListener.listen(13212) ? (!TextUtils.isEmpty(defaultViewHandlerPackageName) || !hasSpecializedHandlerIntents(context, activityIntent)) : (!TextUtils.isEmpty(defaultViewHandlerPackageName) && !hasSpecializedHandlerIntents(context, activityIntent))) || packagesSupportingCustomTabs.contains(defaultViewHandlerPackageName)) : ((ListenerUtil.mutListener.listen(13212) ? (!TextUtils.isEmpty(defaultViewHandlerPackageName) || !hasSpecializedHandlerIntents(context, activityIntent)) : (!TextUtils.isEmpty(defaultViewHandlerPackageName) && !hasSpecializedHandlerIntents(context, activityIntent))) && packagesSupportingCustomTabs.contains(defaultViewHandlerPackageName)))) {
                if (!ListenerUtil.mutListener.listen(13218)) {
                    sPackageNameToUse = defaultViewHandlerPackageName;
                }
            } else if (packagesSupportingCustomTabs.contains(STABLE_PACKAGE)) {
                if (!ListenerUtil.mutListener.listen(13217)) {
                    sPackageNameToUse = STABLE_PACKAGE;
                }
            } else if (packagesSupportingCustomTabs.contains(BETA_PACKAGE)) {
                if (!ListenerUtil.mutListener.listen(13216)) {
                    sPackageNameToUse = BETA_PACKAGE;
                }
            } else if (packagesSupportingCustomTabs.contains(DEV_PACKAGE)) {
                if (!ListenerUtil.mutListener.listen(13215)) {
                    sPackageNameToUse = DEV_PACKAGE;
                }
            } else if (packagesSupportingCustomTabs.contains(LOCAL_PACKAGE)) {
                if (!ListenerUtil.mutListener.listen(13214)) {
                    sPackageNameToUse = LOCAL_PACKAGE;
                }
            }
        }
        return sPackageNameToUse;
    }

    /**
     * Used to check whether there is a specialized handler for a given intent.
     * @param intent The intent to check with.
     * @return Whether there is a specialized handler for the given intent.
     */
    private static boolean hasSpecializedHandlerIntents(Context context, Intent intent) {
        try {
            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> handlers = pm.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
            if (!ListenerUtil.mutListener.listen(13224)) {
                if ((ListenerUtil.mutListener.listen(13223) ? (handlers == null && handlers.size() == 0) : (handlers == null || handlers.size() == 0))) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(13229)) {
                {
                    long _loopCounter236 = 0;
                    for (ResolveInfo resolveInfo : handlers) {
                        ListenerUtil.loopListener.listen("_loopCounter236", ++_loopCounter236);
                        IntentFilter filter = resolveInfo.filter;
                        if (!ListenerUtil.mutListener.listen(13225)) {
                            if (filter == null)
                                continue;
                        }
                        if (!ListenerUtil.mutListener.listen(13227)) {
                            if ((ListenerUtil.mutListener.listen(13226) ? (filter.countDataAuthorities() == 0 && filter.countDataPaths() == 0) : (filter.countDataAuthorities() == 0 || filter.countDataPaths() == 0)))
                                continue;
                        }
                        if (!ListenerUtil.mutListener.listen(13228)) {
                            if (resolveInfo.activityInfo == null)
                                continue;
                        }
                        return true;
                    }
                }
            }
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(13222)) {
                Log.e(TAG, "Runtime exception while getting specialized handlers");
            }
        }
        return false;
    }

    /**
     * @return All possible chrome package names that provide custom tabs feature.
     */
    public static String[] getPackages() {
        return new String[] { "", STABLE_PACKAGE, BETA_PACKAGE, DEV_PACKAGE, LOCAL_PACKAGE };
    }
}
