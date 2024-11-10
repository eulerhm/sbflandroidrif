package org.wordpress.android.ui.notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 Z2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002Z[B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010(\u001a\u00020\u001cH\u0002J\b\u0010)\u001a\u00020*H\u0002J\n\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u0012\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\"\u00100\u001a\u00020*2\u0006\u00101\u001a\u00020\'2\u0006\u00102\u001a\u00020\'2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00105\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00106\u001a\u00020*2\u0006\u00107\u001a\u00020\'H\u0016J\b\u00108\u001a\u00020*H\u0016J\u0010\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020;H\u0007J\u0010\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020<H\u0007J\u0010\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020=H\u0007J\u0012\u00109\u001a\u00020*2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0010\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020@H\u0007J\b\u0010A\u001a\u00020*H\u0016J\b\u0010B\u001a\u00020*H\u0016J\u0010\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020/H\u0016J\b\u0010E\u001a\u00020*H\u0016J\b\u0010F\u001a\u00020*H\u0016J\b\u0010G\u001a\u00020*H\u0016J\u001a\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020,2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u0010J\u001a\u00020*H\u0002J\u0018\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020OH\u0002J\f\u0010P\u001a\u00020**\u00020\fH\u0002J\f\u0010Q\u001a\u00020**\u00020\fH\u0002J\f\u0010R\u001a\u00020**\u00020\fH\u0002J*\u0010S\u001a\u00020**\u00020\f2\b\b\u0001\u0010T\u001a\u00020\'2\b\b\u0003\u0010U\u001a\u00020\'2\b\b\u0003\u0010V\u001a\u00020\'H\u0002J\f\u0010W\u001a\u00020**\u00020\fH\u0002J\f\u0010X\u001a\u00020**\u00020\fH\u0002J\f\u0010Y\u001a\u00020**\u00020\fH\u0002R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\\"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsListFragmentPage;", "Lorg/wordpress/android/ui/ViewPagerFragment;", "Lorg/wordpress/android/ui/main/WPMainActivity$OnScrollToTopListener;", "Lorg/wordpress/android/ui/notifications/adapters/NotesAdapter$DataLoadedListener;", "()V", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "setAccountStore", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "binding", "Lorg/wordpress/android/databinding/NotificationsListFragmentPageBinding;", "gcmMessageHandler", "Lorg/wordpress/android/push/GCMMessageHandler;", "getGcmMessageHandler", "()Lorg/wordpress/android/push/GCMMessageHandler;", "setGcmMessageHandler", "(Lorg/wordpress/android/push/GCMMessageHandler;)V", "isAnimatingOutNewNotificationsBar", "", "isNewNotificationsBarShowing", "()Z", "mOnNoteClickListener", "Lorg/wordpress/android/ui/notifications/NotificationsListFragmentPage$OnNoteClickListener;", "mOnScrollListener", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "notesAdapter", "Lorg/wordpress/android/ui/notifications/adapters/NotesAdapter;", "selectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSelectedSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "shouldRefreshNotifications", "showNewUnseenNotificationsRunnable", "Ljava/lang/Runnable;", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "tabPosition", "", "createOrGetNotesAdapter", "fetchNotesFromRemote", "", "getScrollableViewForUniqueIdProvision", "Landroid/view/View;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "onDataLoaded", "itemsCount", "onDestroyView", "onEventMainThread", "event", "Lorg/wordpress/android/ui/notifications/NotificationEvents$NoteLikeOrModerationStatusChanged;", "Lorg/wordpress/android/ui/notifications/NotificationEvents$NotificationsChanged;", "Lorg/wordpress/android/ui/notifications/NotificationEvents$NotificationsRefreshCompleted;", "error", "Lorg/wordpress/android/ui/notifications/NotificationEvents$NotificationsRefreshError;", "Lorg/wordpress/android/ui/notifications/NotificationEvents$NotificationsUnseenStatus;", "onPause", "onResume", "onSaveInstanceState", "outState", "onScrollToTop", "onStart", "onStop", "onViewCreated", "view", "performActionForActiveFilter", "updateNote", "noteId", "", "status", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "clearPendingNotificationsItemsOnUI", "hideEmptyView", "hideNewNotificationsBar", "showEmptyView", "titleResId", "descriptionResId", "buttonResId", "showEmptyViewForCurrentFilter", "showNewNotificationsBar", "showNewUnseenNotificationsUI", "Companion", "OnNoteClickListener", "org.wordpress.android_wordpressJalapenoDebug"})
public final class NotificationsListFragmentPage extends org.wordpress.android.ui.ViewPagerFragment implements org.wordpress.android.ui.main.WPMainActivity.OnScrollToTopListener, org.wordpress.android.ui.notifications.adapters.NotesAdapter.DataLoadedListener {
    private org.wordpress.android.ui.notifications.adapters.NotesAdapter notesAdapter;
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    private boolean isAnimatingOutNewNotificationsBar = false;
    private boolean shouldRefreshNotifications = false;
    private int tabPosition = 0;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.AccountStore accountStore;
    @javax.inject.Inject()
    public org.wordpress.android.push.GCMMessageHandler gcmMessageHandler;
    private final java.lang.Runnable showNewUnseenNotificationsRunnable = null;
    private org.wordpress.android.databinding.NotificationsListFragmentPageBinding binding;
    private final org.wordpress.android.ui.notifications.NotificationsListFragmentPage.OnNoteClickListener mOnNoteClickListener = null;
    private final androidx.recyclerview.widget.RecyclerView.OnScrollListener mOnScrollListener = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.notifications.NotificationsListFragmentPage.Companion Companion = null;
    private static final java.lang.String KEY_TAB_POSITION = "tabPosition";
    
    public NotificationsListFragmentPage() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    public final void setAccountStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.push.GCMMessageHandler getGcmMessageHandler() {
        return null;
    }
    
    public final void setGcmMessageHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.push.GCMMessageHandler p0) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
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
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onDataLoaded(int itemsCount) {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View getScrollableViewForUniqueIdProvision() {
        return null;
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onScrollToTop() {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
    
    private final void clearPendingNotificationsItemsOnUI(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$clearPendingNotificationsItemsOnUI) {
    }
    
    private final void fetchNotesFromRemote() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.SiteModel getSelectedSite() {
        return null;
    }
    
    private final void hideEmptyView(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$hideEmptyView) {
    }
    
    private final void hideNewNotificationsBar(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$hideNewNotificationsBar) {
    }
    
    private final boolean isNewNotificationsBarShowing() {
        return false;
    }
    
    private final void performActionForActiveFilter() {
    }
    
    private final void showEmptyView(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$showEmptyView, @androidx.annotation.StringRes()
    int titleResId, @androidx.annotation.StringRes()
    int descriptionResId, @androidx.annotation.StringRes()
    int buttonResId) {
    }
    
    private final void showEmptyViewForCurrentFilter(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$showEmptyViewForCurrentFilter) {
    }
    
    private final void showNewNotificationsBar(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$showNewNotificationsBar) {
    }
    
    private final void showNewUnseenNotificationsUI(org.wordpress.android.databinding.NotificationsListFragmentPageBinding $this$showNewUnseenNotificationsUI) {
    }
    
    private final void updateNote(java.lang.String noteId, org.wordpress.android.fluxc.model.CommentStatus status) {
    }
    
    private final org.wordpress.android.ui.notifications.adapters.NotesAdapter createOrGetNotesAdapter() {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.NotificationEvents.NoteLikeOrModerationStatusChanged event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.NotificationEvents.NotificationsChanged event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.NotificationEvents.NotificationsRefreshCompleted event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.notifications.NotificationEvents.NotificationsRefreshError error) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.NotificationEvents.NotificationsUnseenStatus event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsListFragmentPage$OnNoteClickListener;", "", "onClickNote", "", "noteId", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface OnNoteClickListener {
        
        public abstract void onClickNote(@org.jetbrains.annotations.Nullable()
        java.lang.String noteId);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ>\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0011J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsListFragmentPage$Companion;", "", "()V", "KEY_TAB_POSITION", "", "getOpenNoteIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "noteId", "newInstance", "Landroidx/fragment/app/Fragment;", "position", "", "openNoteForReply", "", "shouldShowKeyboard", "", "replyText", "filter", "Lorg/wordpress/android/ui/notifications/adapters/NotesAdapter$FILTERS;", "isTappedFromPushNotification", "openNoteForReplyWithParams", "detailIntent", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.fragment.app.Fragment newInstance(int position) {
            return null;
        }
        
        private final android.content.Intent getOpenNoteIntent(android.app.Activity activity, java.lang.String noteId) {
            return null;
        }
        
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