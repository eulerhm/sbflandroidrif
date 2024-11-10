package org.wordpress.android.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eJ&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000eJ\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\u000f\u001a\u00020\u0010J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/models/InvitePeopleUtils;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "roleUtilsWrapper", "Lorg/wordpress/android/models/wrappers/RoleUtilsWrapper;", "simpleDateFormatWrapper", "Lorg/wordpress/android/models/wrappers/SimpleDateFormatWrapper;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/util/DateTimeUtilsWrapper;Lorg/wordpress/android/models/wrappers/RoleUtilsWrapper;Lorg/wordpress/android/models/wrappers/SimpleDateFormatWrapper;)V", "getDisplayNameForRole", "", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "roleName", "getInviteLinkDataFromRoleDisplayName", "Lorg/wordpress/android/ui/people/InviteLinksApiCallsProvider$InviteLinksItem;", "inviteLinksData", "", "roleDisplayName", "getInviteLinksRoleDisplayNames", "", "getMappedLinksUiItems", "Lorg/wordpress/android/ui/people/InviteLinksUiItem;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class InvitePeopleUtils {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
    private final org.wordpress.android.models.wrappers.RoleUtilsWrapper roleUtilsWrapper = null;
    private final org.wordpress.android.models.wrappers.SimpleDateFormatWrapper simpleDateFormatWrapper = null;
    
    @javax.inject.Inject()
    public InvitePeopleUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.wrappers.RoleUtilsWrapper roleUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.wrappers.SimpleDateFormatWrapper simpleDateFormatWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem getInviteLinkDataFromRoleDisplayName(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> inviteLinksData, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
    java.lang.String roleDisplayName) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayNameForRole(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
    java.lang.String roleName) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.people.InviteLinksUiItem> getMappedLinksUiItems(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> inviteLinksData, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getInviteLinksRoleDisplayNames(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.people.InviteLinksApiCallsProvider.InviteLinksItem> inviteLinksData, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
}