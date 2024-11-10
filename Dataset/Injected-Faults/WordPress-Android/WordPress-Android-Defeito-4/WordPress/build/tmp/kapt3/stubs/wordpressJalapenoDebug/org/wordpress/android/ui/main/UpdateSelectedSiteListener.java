package org.wordpress.android.ui.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/main/UpdateSelectedSiteListener;", "", "onUpdateSelectedSiteResult", "", "resultCode", "", "data", "Landroid/content/Intent;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface UpdateSelectedSiteListener {
    
    public abstract void onUpdateSelectedSiteResult(int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data);
}