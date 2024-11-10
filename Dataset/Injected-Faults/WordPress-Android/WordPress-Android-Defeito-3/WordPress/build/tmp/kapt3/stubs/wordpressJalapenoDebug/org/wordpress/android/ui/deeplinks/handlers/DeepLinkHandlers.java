package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#J\u0010\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\"\u001a\u00020#R\'\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandlers;", "", "editorLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/EditorLinkHandler;", "statsLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/StatsLinkHandler;", "startLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/StartLinkHandler;", "readerLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/ReaderLinkHandler;", "pagesLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/PagesLinkHandler;", "notificationsLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/NotificationsLinkHandler;", "qrCodeAuthLinkHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/QRCodeAuthLinkHandler;", "(Lorg/wordpress/android/ui/deeplinks/handlers/EditorLinkHandler;Lorg/wordpress/android/ui/deeplinks/handlers/StatsLinkHandler;Lorg/wordpress/android/ui/deeplinks/handlers/StartLinkHandler;Lorg/wordpress/android/ui/deeplinks/handlers/ReaderLinkHandler;Lorg/wordpress/android/ui/deeplinks/handlers/PagesLinkHandler;Lorg/wordpress/android/ui/deeplinks/handlers/NotificationsLinkHandler;Lorg/wordpress/android/ui/deeplinks/handlers/QRCodeAuthLinkHandler;)V", "_toast", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "get_toast", "()Landroidx/lifecycle/MediatorLiveData;", "_toast$delegate", "Lkotlin/Lazy;", "handlers", "", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandler;", "toast", "Landroidx/lifecycle/LiveData;", "getToast", "()Landroidx/lifecycle/LiveData;", "buildNavigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "stripUrl", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeepLinkHandlers {
    private final java.util.List<org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandler> handlers = null;
    private final kotlin.Lazy _toast$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast = null;
    
    @javax.inject.Inject()
    public DeepLinkHandlers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.EditorLinkHandler editorLinkHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.StatsLinkHandler statsLinkHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.StartLinkHandler startLinkHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.ReaderLinkHandler readerLinkHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.PagesLinkHandler pagesLinkHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.NotificationsLinkHandler notificationsLinkHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.QRCodeAuthLinkHandler qrCodeAuthLinkHandler) {
        super();
    }
    
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> get_toast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> getToast() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String stripUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
}