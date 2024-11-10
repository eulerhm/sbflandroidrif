package org.wordpress.android.ui.reader.services.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverServiceStarter;", "", "()V", "ARG_DISCOVER_TASK", "", "startService", "", "context", "Landroid/content/Context;", "task", "Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic$DiscoverTasks;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverServiceStarter {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.services.discover.ReaderDiscoverServiceStarter INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_DISCOVER_TASK = "discover_task";
    
    private ReaderDiscoverServiceStarter() {
        super();
    }
    
    public final boolean startService(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks task) {
        return false;
    }
}