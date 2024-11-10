package org.wordpress.android.ui.notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 02\u00020\u00012\u00020\u0002:\u000201B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0014H\u0016J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010$\u001a\u00020\u0014H\u0016J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0017H\u0016J\u0010\u0010\'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\rH\u0016J\u001a\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\f\u0010,\u001a\u00020\u0014*\u00020\u000bH\u0002J\u0014\u0010-\u001a\u00020\u0014*\u00020\u000b2\u0006\u0010.\u001a\u00020\rH\u0002J\f\u0010/\u001a\u00020\u0014*\u00020\u000bH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsListFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/ScrollableViewInitializedListener;", "()V", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "setAccountStore", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "binding", "Lorg/wordpress/android/databinding/NotificationsListFragmentBinding;", "lastTabPosition", "", "shouldRefreshNotifications", "", "buildTitles", "", "", "fetchNotesFromRemote", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onPrepareOptionsMenu", "onResume", "onSaveInstanceState", "outState", "onScrollableViewInitialized", "containerId", "onViewCreated", "view", "Landroid/view/View;", "clearToolbarScrollFlags", "setSelectedTab", "position", "showConnectJetpackView", "Companion", "NotificationsFragmentAdapter", "org.wordpress.android_wordpressJalapenoDebug"})
public final class NotificationsListFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.ScrollableViewInitializedListener {
    private boolean shouldRefreshNotifications = false;
    private int lastTabPosition = 0;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.AccountStore accountStore;
    private org.wordpress.android.databinding.NotificationsListFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.notifications.NotificationsListFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTE_ID_EXTRA = "noteId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTE_INSTANT_REPLY_EXTRA = "instantReply";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTE_PREFILLED_REPLY_EXTRA = "prefilledReplyText";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTE_MODERATE_ID_EXTRA = "moderateNoteId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTE_MODERATE_STATUS_EXTRA = "moderateNoteStatus";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTE_CURRENT_LIST_FILTER_EXTRA = "currentFilter";
    private static final int TAB_COUNT = 5;
    public static final int TAB_POSITION_ALL = 0;
    public static final int TAB_POSITION_UNREAD = 1;
    public static final int TAB_POSITION_COMMENT = 2;
    public static final int TAB_POSITION_FOLLOW = 3;
    public static final int TAB_POSITION_LIKE = 4;
    private static final java.lang.String KEY_LAST_TAB_POSITION = "lastTabPosition";
    
    public NotificationsListFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    public final void setAccountStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore p0) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.util.List<java.lang.String> buildTitles() {
        return null;
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void clearToolbarScrollFlags(org.wordpress.android.databinding.NotificationsListFragmentBinding $this$clearToolbarScrollFlags) {
    }
    
    private final void fetchNotesFromRemote() {
    }
    
    private final void setSelectedTab(org.wordpress.android.databinding.NotificationsListFragmentBinding $this$setSelectedTab, int position) {
    }
    
    private final void showConnectJetpackView(org.wordpress.android.databinding.NotificationsListFragmentBinding $this$showConnectJetpackView) {
    }
    
    @java.lang.Override()
    public void onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void openNoteForReply(@org.jetbrains.annotations.Nullable()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    java.lang.String noteId, boolean shouldShowKeyboard, @org.jetbrains.annotations.Nullable()
    java.lang.String replyText, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.notifications.adapters.NotesAdapter.FILTERS filter, boolean isTappedFromPushNotification) {
    }
    
    @java.lang.Override()
    public void onScrollableViewInitialized(int containerId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\tH\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsListFragment$NotificationsFragmentAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "titles", "", "", "(Landroidx/fragment/app/FragmentManager;Ljava/util/List;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "restoreState", "", "state", "Landroid/os/Parcelable;", "loader", "Ljava/lang/ClassLoader;", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class NotificationsFragmentAdapter extends androidx.fragment.app.FragmentPagerAdapter {
        private final java.util.List<java.lang.String> titles = null;
        
        public NotificationsFragmentAdapter(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.FragmentManager fragmentManager, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> titles) {
            super(null);
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public androidx.fragment.app.Fragment getItem(int position) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.CharSequence getPageTitle(int position) {
            return null;
        }
        
        @java.lang.Override()
        public void restoreState(@org.jetbrains.annotations.Nullable()
        android.os.Parcelable state, @org.jetbrains.annotations.Nullable()
        java.lang.ClassLoader loader) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J@\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u001cH\u0007J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsListFragment$Companion;", "", "()V", "KEY_LAST_TAB_POSITION", "", "NOTE_CURRENT_LIST_FILTER_EXTRA", "NOTE_ID_EXTRA", "NOTE_INSTANT_REPLY_EXTRA", "NOTE_MODERATE_ID_EXTRA", "NOTE_MODERATE_STATUS_EXTRA", "NOTE_PREFILLED_REPLY_EXTRA", "TAB_COUNT", "", "TAB_POSITION_ALL", "TAB_POSITION_COMMENT", "TAB_POSITION_FOLLOW", "TAB_POSITION_LIKE", "TAB_POSITION_UNREAD", "getOpenNoteIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "noteId", "newInstance", "Lorg/wordpress/android/ui/notifications/NotificationsListFragment;", "openNoteForReply", "", "shouldShowKeyboard", "", "replyText", "filter", "Lorg/wordpress/android/ui/notifications/adapters/NotesAdapter$FILTERS;", "isTappedFromPushNotification", "openNoteForReplyWithParams", "detailIntent", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.notifications.NotificationsListFragment newInstance() {
            return null;
        }
        
        private final android.content.Intent getOpenNoteIntent(android.app.Activity activity, java.lang.String noteId) {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        public final void openNoteForReply(@org.jetbrains.annotations.Nullable()
        android.app.Activity activity, @org.jetbrains.annotations.Nullable()
        java.lang.String noteId, boolean shouldShowKeyboard, @org.jetbrains.annotations.Nullable()
        java.lang.String replyText, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.notifications.adapters.NotesAdapter.FILTERS filter, boolean isTappedFromPushNotification) {
        }
        
        private final void openNoteForReplyWithParams(android.content.Intent detailIntent, android.app.Activity activity) {
        }
    }
}