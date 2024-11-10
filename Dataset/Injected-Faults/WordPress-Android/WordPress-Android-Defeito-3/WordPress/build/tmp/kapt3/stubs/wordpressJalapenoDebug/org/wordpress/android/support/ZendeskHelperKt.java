package org.wordpress.android.support;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000D\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aP\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a8\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001aB\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fH\u0002\u001a\u001c\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0002\u001a\u0018\u0010\u001b\u001a\u00020\u00032\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00038BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"enablePushNotificationsDelayAfterIdentityChange", "", "wpcomPushNotificationDeviceToken", "", "getWpcomPushNotificationDeviceToken", "()Ljava/lang/String;", "zendeskNeedsToBeEnabledError", "buildZendeskConfig", "Lzendesk/configurations/Configuration;", "context", "Landroid/content/Context;", "allSites", "", "Lorg/wordpress/android/fluxc/model/SiteModel;", "origin", "Lorg/wordpress/android/ui/accounts/HelpActivity$Origin;", "selectedSite", "extraTags", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "buildZendeskCustomFields", "Lzendesk/support/CustomField;", "buildZendeskTags", "createZendeskIdentity", "Lzendesk/core/Identity;", "email", "name", "getCombinedLogInformationOfSites", "getNetworkInformation", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ZendeskHelperKt {
    private static final java.lang.String zendeskNeedsToBeEnabledError = "Zendesk needs to be setup before this method can be called";
    private static final long enablePushNotificationsDelayAfterIdentityChange = 2500L;
    
    /**
     * This is a helper function which builds a `UiConfig` through helpers to be used during ticket creation.
     */
    @kotlin.Suppress(names = {"LongParameterList"})
    private static final zendesk.configurations.Configuration buildZendeskConfig(android.content.Context context, java.util.List<? extends org.wordpress.android.fluxc.model.SiteModel> allSites, org.wordpress.android.ui.accounts.HelpActivity.Origin origin, org.wordpress.android.fluxc.model.SiteModel selectedSite, java.util.List<java.lang.String> extraTags, org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper) {
        return null;
    }
    
    /**
     * This is a helper function which builds a list of `CustomField`s which will be used during ticket creation. They
     * will be used to fill the custom fields we have setup in Zendesk UI for Happiness Engineers.
     */
    private static final java.util.List<zendesk.support.CustomField> buildZendeskCustomFields(android.content.Context context, java.util.List<? extends org.wordpress.android.fluxc.model.SiteModel> allSites, org.wordpress.android.fluxc.model.SiteModel selectedSite, org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper) {
        return null;
    }
    
    /**
     * This is a helper function which creates an anonymous Zendesk identity with the email and name passed in. They can
     * both be `null` as they are not required for a valid identity.
     *
     * An important thing to note is that whenever a different set of values are passed in, a different identity will be
     * created which will reset the ticket list for the user. So, for example, even if the passed in email is the same,
     * if the name is different, it'll reset Zendesk's local DB.
     *
     * This is currently the way we handle identity for Zendesk, but it's possible that we may switch to a JWT based
     * authentication which will avoid the resetting issue, but will mean that we'll need to involve our own servers in the
     * authentication. More information can be found in their documentation:
     * https://developer.zendesk.com/embeddables/docs/android-support-sdk/sdk_set_identity#setting-a-unique-identity
     */
    private static final zendesk.core.Identity createZendeskIdentity(java.lang.String email, java.lang.String name) {
        return null;
    }
    
    /**
     * This is a small helper function which just joins the `logInformation` of all the sites passed in with a separator.
     */
    private static final java.lang.String getCombinedLogInformationOfSites(java.util.List<? extends org.wordpress.android.fluxc.model.SiteModel> allSites) {
        return null;
    }
    
    /**
     * This is a helper function which returns a set of pre-defined tags depending on some conditions. It accepts a list of
     * custom tags to be added for special cases.
     */
    private static final java.util.List<java.lang.String> buildZendeskTags(java.util.List<? extends org.wordpress.android.fluxc.model.SiteModel> allSites, org.wordpress.android.fluxc.model.SiteModel selectedSite, org.wordpress.android.ui.accounts.HelpActivity.Origin origin, java.util.List<java.lang.String> extraTags) {
        return null;
    }
    
    /**
     * This is a helper function which returns information about the network state of the app to be sent to Zendesk, which
     * could prove useful for the Happiness Engineers while debugging the users' issues.
     */
    private static final java.lang.String getNetworkInformation(android.content.Context context) {
        return null;
    }
    
    private static final java.lang.String getWpcomPushNotificationDeviceToken() {
        return null;
    }
}