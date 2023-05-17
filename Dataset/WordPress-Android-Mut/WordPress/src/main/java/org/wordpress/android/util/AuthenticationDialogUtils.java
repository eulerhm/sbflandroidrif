package org.wordpress.android.util;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.login.LoginMode;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.accounts.LoginActivity;
import org.wordpress.android.widgets.AuthErrorDialogFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AuthenticationDialogUtils {

    public static void showAuthErrorView(AppCompatActivity activity, SiteStore siteStore, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(27491)) {
            showAuthErrorView(activity, siteStore, AuthErrorDialogFragment.DEFAULT_RESOURCE_ID, AuthErrorDialogFragment.DEFAULT_RESOURCE_ID, site);
        }
    }

    public static void showAuthErrorView(AppCompatActivity activity, SiteStore siteStore, int titleResId, int messageResId, SiteModel site) {
        final String alertTag = "alert_ask_credentials";
        if (!ListenerUtil.mutListener.listen(27492)) {
            if (activity.isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27497)) {
            // WP.com errors will show the sign in activity
            if (site.isWPCom()) {
                if (!ListenerUtil.mutListener.listen(27496)) {
                    if (siteStore.hasSiteAccessedViaXMLRPC()) {
                        if (!ListenerUtil.mutListener.listen(27495)) {
                            // show site picker since there are site besides WPCOM ones
                            ActivityLauncher.showSitePickerForResult(activity, site);
                        }
                    } else {
                        // only WPCOM sites are available so, need to ask the user to log in again
                        Intent intent = new Intent(activity, LoginActivity.class);
                        if (!ListenerUtil.mutListener.listen(27493)) {
                            LoginMode.WPCOM_REAUTHENTICATE.putInto(intent);
                        }
                        if (!ListenerUtil.mutListener.listen(27494)) {
                            activity.startActivityForResult(intent, RequestCodes.REAUTHENTICATE);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27498)) {
            // abort if the dialog is already visible
            if (activity.getFragmentManager().findFragmentByTag(alertTag) != null) {
                return;
            }
        }
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        AuthErrorDialogFragment authAlert = new AuthErrorDialogFragment();
        if (!ListenerUtil.mutListener.listen(27499)) {
            authAlert.setArgs(titleResId, messageResId, site);
        }
        if (!ListenerUtil.mutListener.listen(27500)) {
            ft.add(authAlert, alertTag);
        }
        if (!ListenerUtil.mutListener.listen(27501)) {
            ft.commitAllowingStateLoss();
        }
    }
}
