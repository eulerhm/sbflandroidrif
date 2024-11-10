package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/DaysProvider;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "getDaysOfWeekByLocale", "", "Ljava/time/DayOfWeek;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DaysProvider {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.bloggingreminders.DaysProvider.Companion Companion = null;
    private static final int START_OFFSET = 0;
    private static final long END_OFFSET = 6L;
    
    @javax.inject.Inject()
    public DaysProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.time.DayOfWeek> getDaysOfWeekByLocale() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/DaysProvider$Companion;", "", "()V", "END_OFFSET", "", "START_OFFSET", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}