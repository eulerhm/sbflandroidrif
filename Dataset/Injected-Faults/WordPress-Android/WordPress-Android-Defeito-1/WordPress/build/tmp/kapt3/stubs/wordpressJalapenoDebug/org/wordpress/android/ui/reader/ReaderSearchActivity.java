package org.wordpress.android.ui.reader;

import java.lang.System;

/**
 * This Activity was created during ReaderImprovements project. Extracting and refactoring the search from
 * ReaderPostListFragment was out-of-scope. This workaround enabled us writing new "discover" and "following" screens
 * into new tested classes without requiring us to change the search behavior.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/ReaderSearchActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "setReaderTracker", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderSearchActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker;
    
    public ReaderSearchActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void setReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
}