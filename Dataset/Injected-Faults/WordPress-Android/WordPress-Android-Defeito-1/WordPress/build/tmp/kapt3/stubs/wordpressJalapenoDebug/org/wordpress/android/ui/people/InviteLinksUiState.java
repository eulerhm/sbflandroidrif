package org.wordpress.android.ui.people;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0011J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0007H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\fH\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003Js\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010(\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020-H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksUiState;", "", "type", "Lorg/wordpress/android/ui/people/InviteLinksUiStateType;", "isLinksSectionVisible", "", "loadAndRetryUiState", "Lorg/wordpress/android/ui/people/LoadAndRetryUiState;", "isShimmerSectionVisible", "isRoleSelectionAllowed", "links", "", "Lorg/wordpress/android/ui/people/InviteLinksUiItem;", "inviteLinksSelectedRole", "enableManageLinksActions", "startShimmer", "isActionButtonsEnabled", "(Lorg/wordpress/android/ui/people/InviteLinksUiStateType;ZLorg/wordpress/android/ui/people/LoadAndRetryUiState;ZZLjava/util/List;Lorg/wordpress/android/ui/people/InviteLinksUiItem;ZZZ)V", "getEnableManageLinksActions", "()Z", "getInviteLinksSelectedRole", "()Lorg/wordpress/android/ui/people/InviteLinksUiItem;", "getLinks", "()Ljava/util/List;", "getLoadAndRetryUiState", "()Lorg/wordpress/android/ui/people/LoadAndRetryUiState;", "getStartShimmer", "getType", "()Lorg/wordpress/android/ui/people/InviteLinksUiStateType;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InviteLinksUiState {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.people.InviteLinksUiStateType type = null;
    private final boolean isLinksSectionVisible = false;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.people.LoadAndRetryUiState loadAndRetryUiState = null;
    private final boolean isShimmerSectionVisible = false;
    private final boolean isRoleSelectionAllowed = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.people.InviteLinksUiItem> links = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.people.InviteLinksUiItem inviteLinksSelectedRole = null;
    private final boolean enableManageLinksActions = false;
    private final boolean startShimmer = false;
    private final boolean isActionButtonsEnabled = false;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.InviteLinksUiState copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.InviteLinksUiStateType type, boolean isLinksSectionVisible, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.LoadAndRetryUiState loadAndRetryUiState, boolean isShimmerSectionVisible, boolean isRoleSelectionAllowed, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.people.InviteLinksUiItem> links, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.InviteLinksUiItem inviteLinksSelectedRole, boolean enableManageLinksActions, boolean startShimmer, boolean isActionButtonsEnabled) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public InviteLinksUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.InviteLinksUiStateType type, boolean isLinksSectionVisible, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.LoadAndRetryUiState loadAndRetryUiState, boolean isShimmerSectionVisible, boolean isRoleSelectionAllowed, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.people.InviteLinksUiItem> links, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.InviteLinksUiItem inviteLinksSelectedRole, boolean enableManageLinksActions, boolean startShimmer, boolean isActionButtonsEnabled) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.InviteLinksUiStateType component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.InviteLinksUiStateType getType() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean isLinksSectionVisible() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.LoadAndRetryUiState component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.LoadAndRetryUiState getLoadAndRetryUiState() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean isShimmerSectionVisible() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean isRoleSelectionAllowed() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.people.InviteLinksUiItem> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.people.InviteLinksUiItem> getLinks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.InviteLinksUiItem component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.people.InviteLinksUiItem getInviteLinksSelectedRole() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean getEnableManageLinksActions() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    public final boolean getStartShimmer() {
        return false;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean isActionButtonsEnabled() {
        return false;
    }
}