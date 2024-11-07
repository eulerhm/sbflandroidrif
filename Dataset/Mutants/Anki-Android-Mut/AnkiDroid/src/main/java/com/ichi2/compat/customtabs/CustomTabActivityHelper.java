// Copyright 2015 Google Inc. All Rights Reserved.
package com.ichi2.compat.customtabs;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import java.util.List;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This is a helper class to manage the connection to the Custom Tabs Service.
 */
public class CustomTabActivityHelper implements ServiceConnectionCallback {

    private static boolean sCustomTabsFailed = false;

    @Nullable
    private CustomTabsSession mCustomTabsSession;

    @Nullable
    private CustomTabsClient mClient;

    @Nullable
    private CustomTabsServiceConnection mConnection;

    /**
     * Opens the URL on a Custom Tab if possible. Otherwise fallsback to opening it on a WebView.
     *
     * @param activity The host activity.
     * @param customTabsIntent a CustomTabsIntent to be used if Custom Tabs is available.
     * @param uri the Uri to be opened.
     * @param fallback a CustomTabFallback to be used if Custom Tabs is not available.
     */
    public static void openCustomTab(@NonNull Activity activity, CustomTabsIntent customTabsIntent, Uri uri, CustomTabFallback fallback) {
        String packageName = CustomTabsHelper.getPackageNameToUse(activity);
        if (!ListenerUtil.mutListener.listen(13163)) {
            // Chrome Custom Tabs. So, we fallback to the webview
            if ((ListenerUtil.mutListener.listen(13157) ? (packageName == null && sCustomTabsFailed) : (packageName == null || sCustomTabsFailed))) {
                if (!ListenerUtil.mutListener.listen(13162)) {
                    if (fallback != null) {
                        if (!ListenerUtil.mutListener.listen(13161)) {
                            fallback.openUri(activity, uri);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(13160)) {
                            Timber.e("A version of Chrome supporting custom tabs was not available, and the fallback was null");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13158)) {
                    customTabsIntent.intent.setPackage(packageName);
                }
                if (!ListenerUtil.mutListener.listen(13159)) {
                    customTabsIntent.launchUrl(activity, uri);
                }
            }
        }
    }

    /**
     * Unbinds the Activity from the Custom Tabs Service.
     * @param activity the activity that is connected to the service.
     */
    public void unbindCustomTabsService(Activity activity) {
        if (!ListenerUtil.mutListener.listen(13164)) {
            if (mConnection == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(13165)) {
            activity.unbindService(mConnection);
        }
        if (!ListenerUtil.mutListener.listen(13166)) {
            mClient = null;
        }
        if (!ListenerUtil.mutListener.listen(13167)) {
            mCustomTabsSession = null;
        }
        if (!ListenerUtil.mutListener.listen(13168)) {
            mConnection = null;
        }
    }

    /**
     * Creates or retrieves an exiting CustomTabsSession.
     *
     * @return a CustomTabsSession.
     */
    public CustomTabsSession getSession() {
        if (!ListenerUtil.mutListener.listen(13171)) {
            if (mClient == null) {
                if (!ListenerUtil.mutListener.listen(13170)) {
                    mCustomTabsSession = null;
                }
            } else if (mCustomTabsSession == null) {
                if (!ListenerUtil.mutListener.listen(13169)) {
                    mCustomTabsSession = mClient.newSession(null);
                }
            }
        }
        return mCustomTabsSession;
    }

    /**
     * Binds the Activity to the Custom Tabs Service.
     * @param activity the activity to be binded to the service.
     */
    public void bindCustomTabsService(Activity activity) {
        if (!ListenerUtil.mutListener.listen(13172)) {
            if (mClient != null)
                return;
        }
        String packageName = CustomTabsHelper.getPackageNameToUse(activity);
        if (!ListenerUtil.mutListener.listen(13173)) {
            if (packageName == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(13174)) {
            mConnection = new ServiceConnection(this);
        }
        try {
            if (!ListenerUtil.mutListener.listen(13177)) {
                CustomTabsClient.bindCustomTabsService(activity, packageName, mConnection);
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(13175)) {
                Timber.w(e, "CustomTabsService bind attempt failed, using fallback");
            }
            if (!ListenerUtil.mutListener.listen(13176)) {
                disableCustomTabHandler();
            }
        }
    }

    private void disableCustomTabHandler() {
        if (!ListenerUtil.mutListener.listen(13178)) {
            Timber.i("Disabling custom tab handler and using fallback");
        }
        if (!ListenerUtil.mutListener.listen(13179)) {
            sCustomTabsFailed = true;
        }
        if (!ListenerUtil.mutListener.listen(13180)) {
            mClient = null;
        }
        if (!ListenerUtil.mutListener.listen(13181)) {
            mCustomTabsSession = null;
        }
        if (!ListenerUtil.mutListener.listen(13182)) {
            mConnection = null;
        }
    }

    /**
     * @see {@link CustomTabsSession#mayLaunchUrl(Uri, Bundle, List)}.
     * @return true if call to mayLaunchUrl was accepted.
     */
    public boolean mayLaunchUrl(Uri uri, Bundle extras, List<Bundle> otherLikelyBundles) {
        if (!ListenerUtil.mutListener.listen(13183)) {
            if (mClient == null)
                return false;
        }
        CustomTabsSession session = getSession();
        if (!ListenerUtil.mutListener.listen(13184)) {
            if (session == null)
                return false;
        }
        return session.mayLaunchUrl(uri, extras, otherLikelyBundles);
    }

    @Override
    public void onServiceConnected(CustomTabsClient client) {
        try {
            if (!ListenerUtil.mutListener.listen(13187)) {
                mClient = client;
            }
            try {
                if (!ListenerUtil.mutListener.listen(13189)) {
                    mClient.warmup(0L);
                }
            } catch (IllegalStateException e) {
                if (!ListenerUtil.mutListener.listen(13188)) {
                    // They will crash as they attempt to start services. warmup failure shouldn't be fatal though.
                    Timber.w(e, "Ignoring CustomTabs implementation that doesn't conform to Android 8 background limits");
                }
            }
            if (!ListenerUtil.mutListener.listen(13190)) {
                getSession();
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(13185)) {
                // the IllegalStateException was a failure, but could be continued from
                Timber.w(e, "CustomTabsService bind attempt failed, using fallback");
            }
            if (!ListenerUtil.mutListener.listen(13186)) {
                disableCustomTabHandler();
            }
        }
    }

    @Override
    public void onServiceDisconnected() {
        if (!ListenerUtil.mutListener.listen(13191)) {
            mClient = null;
        }
        if (!ListenerUtil.mutListener.listen(13192)) {
            mCustomTabsSession = null;
        }
    }

    /**
     * To be used as a fallback to open the Uri when Custom Tabs is not available.
     */
    public interface CustomTabFallback {

        /**
         * @param activity The Activity that wants to open the Uri.
         * @param uri The uri to be opened by the fallback.
         */
        void openUri(Activity activity, Uri uri);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @CheckResult
    boolean isFailed() {
        return (ListenerUtil.mutListener.listen(13193) ? (sCustomTabsFailed || mClient == null) : (sCustomTabsFailed && mClient == null));
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void resetFailed() {
        if (!ListenerUtil.mutListener.listen(13194)) {
            sCustomTabsFailed = false;
        }
    }
}
