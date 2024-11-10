package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/pages/PagesActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogPositiveClickInterface;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogNegativeClickInterface;", "()V", "systemNotificationTracker", "Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "getSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "setSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;)V", "handleIntent", "", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNegativeClicked", "instanceTag", "", "onNewIntent", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPositiveClicked", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PagesActivity extends org.wordpress.android.ui.LocaleAwareActivity implements org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface, org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogNegativeClickInterface {
    @javax.inject.Inject()
    public org.wordpress.android.ui.notifications.SystemNotificationsTracker systemNotificationTracker;
    
    public PagesActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.notifications.SystemNotificationsTracker getSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.SystemNotificationsTracker p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void handleIntent(android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onPositiveClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @java.lang.Override()
    public void onNegativeClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
}