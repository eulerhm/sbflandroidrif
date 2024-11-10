package org.wordpress.android.ui.jetpack.restore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "NetworkUnavailable", "RemoteRequestFailure", "GenericFailure", "OtherRequestRunning", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public enum RestoreErrorTypes {
    /*public static final*/ NetworkUnavailable /* = new NetworkUnavailable(0) */,
    /*public static final*/ RemoteRequestFailure /* = new RemoteRequestFailure(0) */,
    /*public static final*/ GenericFailure /* = new GenericFailure(0) */,
    /*public static final*/ OtherRequestRunning /* = new OtherRequestRunning(0) */;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes.Companion Companion = null;
    
    RestoreErrorTypes(int id) {
    }
    
    public final int getId() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes$Companion;", "", "()V", "fromInt", "Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;", "value", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes fromInt(int value) {
            return null;
        }
    }
}