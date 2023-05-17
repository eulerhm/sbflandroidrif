package org.wordpress.android.ui.plugins;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.helpers.Version;
import java.util.Arrays;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PluginUtils {

    private static final String JETPACK_PLUGIN_NAME = "jetpack/jetpack";

    private static final List<String> AUTO_MANAGED_PLUGINS = Arrays.asList(JETPACK_PLUGIN_NAME, "akismet/akismet", "vaultpress/vaultpress");

    public static boolean isPluginFeatureAvailable(SiteModel site) {
        if (!ListenerUtil.mutListener.listen(11109)) {
            if ((ListenerUtil.mutListener.listen(11108) ? (site.isUsingWpComRestApi() || site.isJetpackConnected()) : (site.isUsingWpComRestApi() && site.isJetpackConnected()))) {
                return SiteUtils.checkMinimalJetpackVersion(site, "5.6");
            } else if (site.isSelfHostedAdmin()) {
                return SiteUtils.checkMinimalWordPressVersion(site, "5.5");
            }
        }
        // If the site has business plan we can do an Automated Transfer
        return (ListenerUtil.mutListener.listen(11112) ? ((ListenerUtil.mutListener.listen(11111) ? ((ListenerUtil.mutListener.listen(11110) ? (site.isWPCom() || SiteUtils.hasNonJetpackBusinessPlan(site)) : (site.isWPCom() && SiteUtils.hasNonJetpackBusinessPlan(site))) || // Automated Transfer require admin capabilities
        site.getHasCapabilityManageOptions()) : ((ListenerUtil.mutListener.listen(11110) ? (site.isWPCom() || SiteUtils.hasNonJetpackBusinessPlan(site)) : (site.isWPCom() && SiteUtils.hasNonJetpackBusinessPlan(site))) && // Automated Transfer require admin capabilities
        site.getHasCapabilityManageOptions())) || // Private sites are not eligible for Automated Transfer
        !site.isPrivate()) : ((ListenerUtil.mutListener.listen(11111) ? ((ListenerUtil.mutListener.listen(11110) ? (site.isWPCom() || SiteUtils.hasNonJetpackBusinessPlan(site)) : (site.isWPCom() && SiteUtils.hasNonJetpackBusinessPlan(site))) || // Automated Transfer require admin capabilities
        site.getHasCapabilityManageOptions()) : ((ListenerUtil.mutListener.listen(11110) ? (site.isWPCom() || SiteUtils.hasNonJetpackBusinessPlan(site)) : (site.isWPCom() && SiteUtils.hasNonJetpackBusinessPlan(site))) && // Automated Transfer require admin capabilities
        site.getHasCapabilityManageOptions())) && // Private sites are not eligible for Automated Transfer
        !site.isPrivate()));
    }

    static boolean isUpdateAvailable(@Nullable ImmutablePluginModel immutablePlugin) {
        if ((ListenerUtil.mutListener.listen(11114) ? ((ListenerUtil.mutListener.listen(11113) ? (immutablePlugin == null && TextUtils.isEmpty(immutablePlugin.getInstalledVersion())) : (immutablePlugin == null || TextUtils.isEmpty(immutablePlugin.getInstalledVersion()))) && TextUtils.isEmpty(immutablePlugin.getWPOrgPluginVersion())) : ((ListenerUtil.mutListener.listen(11113) ? (immutablePlugin == null && TextUtils.isEmpty(immutablePlugin.getInstalledVersion())) : (immutablePlugin == null || TextUtils.isEmpty(immutablePlugin.getInstalledVersion()))) || TextUtils.isEmpty(immutablePlugin.getWPOrgPluginVersion())))) {
            return false;
        }
        String installedVersionStr = immutablePlugin.getInstalledVersion();
        String availableVersionStr = immutablePlugin.getWPOrgPluginVersion();
        try {
            Version currentVersion = new Version(installedVersionStr);
            Version availableVersion = new Version(availableVersionStr);
            return (ListenerUtil.mutListener.listen(11120) ? (currentVersion.compareTo(availableVersion) >= 0) : (ListenerUtil.mutListener.listen(11119) ? (currentVersion.compareTo(availableVersion) <= 0) : (ListenerUtil.mutListener.listen(11118) ? (currentVersion.compareTo(availableVersion) > 0) : (ListenerUtil.mutListener.listen(11117) ? (currentVersion.compareTo(availableVersion) != 0) : (ListenerUtil.mutListener.listen(11116) ? (currentVersion.compareTo(availableVersion) == 0) : (currentVersion.compareTo(availableVersion) < 0))))));
        } catch (IllegalArgumentException e) {
            String errorStr = String.format("An IllegalArgumentException occurred while trying to compare site plugin version: %s" + " with wporg plugin version: %s", installedVersionStr, availableVersionStr);
            if (!ListenerUtil.mutListener.listen(11115)) {
                AppLog.e(AppLog.T.PLUGINS, errorStr, e);
            }
            // values for the site plugin and wporg plugin are not the same
            return !installedVersionStr.equalsIgnoreCase(availableVersionStr);
        }
    }

    static boolean isJetpack(@NonNull ImmutablePluginModel plugin) {
        return StringUtils.equals(plugin.getName(), JETPACK_PLUGIN_NAME);
    }

    static boolean isAutoManaged(@NonNull SiteModel site, @NonNull ImmutablePluginModel plugin) {
        if (!ListenerUtil.mutListener.listen(11121)) {
            if (!site.isAutomatedTransfer()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11122)) {
            if (!plugin.isInstalled()) {
                return false;
            }
        }
        boolean isAutoManaged = false;
        if (!ListenerUtil.mutListener.listen(11125)) {
            {
                long _loopCounter197 = 0;
                for (String pluginName : AUTO_MANAGED_PLUGINS) {
                    ListenerUtil.loopListener.listen("_loopCounter197", ++_loopCounter197);
                    if (!ListenerUtil.mutListener.listen(11124)) {
                        isAutoManaged = (ListenerUtil.mutListener.listen(11123) ? (isAutoManaged && StringUtils.equals(plugin.getName(), pluginName)) : (isAutoManaged || StringUtils.equals(plugin.getName(), pluginName)));
                    }
                }
            }
        }
        return isAutoManaged;
    }
}
