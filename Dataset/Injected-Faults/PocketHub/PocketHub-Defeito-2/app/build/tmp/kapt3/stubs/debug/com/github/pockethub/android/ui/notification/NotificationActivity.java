package com.github.pockethub.android.ui.notification;

import android.os.Bundle;
import android.view.MenuItem;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import kotlinx.android.synthetic.main.pager_with_tabs.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\u0007H\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/github/pockethub/android/ui/notification/NotificationActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "()V", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/notification/NotificationPagerAdapter;", "configurePager", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "app_debug"})
public final class NotificationActivity extends com.github.pockethub.android.ui.base.BaseActivity {
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.notification.NotificationPagerAdapter> pagerHandler;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void configurePager() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    public NotificationActivity() {
        super();
    }
}