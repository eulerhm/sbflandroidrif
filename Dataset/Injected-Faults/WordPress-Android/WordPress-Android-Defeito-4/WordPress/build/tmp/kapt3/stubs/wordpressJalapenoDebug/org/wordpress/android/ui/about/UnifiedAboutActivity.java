package org.wordpress.android.ui.about;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/about/UnifiedAboutActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "Lcom/automattic/about/model/AboutConfigProvider;", "()V", "viewModel", "Lorg/wordpress/android/ui/about/UnifiedAboutViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/about/UnifiedAboutViewModel;", "setViewModel", "(Lorg/wordpress/android/ui/about/UnifiedAboutViewModel;)V", "getAboutConfig", "Lcom/automattic/about/model/AboutConfig;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedAboutActivity extends org.wordpress.android.ui.LocaleAwareActivity implements com.automattic.about.model.AboutConfigProvider {
    @javax.inject.Inject()
    public org.wordpress.android.ui.about.UnifiedAboutViewModel viewModel;
    
    public UnifiedAboutActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.about.UnifiedAboutViewModel getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.about.UnifiedAboutViewModel p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.automattic.about.model.AboutConfig getAboutConfig() {
        return null;
    }
}