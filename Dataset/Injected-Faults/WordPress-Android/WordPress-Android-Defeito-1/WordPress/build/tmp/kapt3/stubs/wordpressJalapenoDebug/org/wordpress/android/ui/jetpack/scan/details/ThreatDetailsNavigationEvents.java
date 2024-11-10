package org.wordpress.android.ui.jetpack.scan.details;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents;", "", "()V", "OpenThreatActionDialog", "ShowGetFreeEstimate", "ShowJetpackSettings", "ShowUpdatedFixState", "ShowUpdatedScanStateWithMessage", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$OpenThreatActionDialog;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowUpdatedScanStateWithMessage;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowUpdatedFixState;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowGetFreeEstimate;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowJetpackSettings;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ThreatDetailsNavigationEvents {
    
    private ThreatDetailsNavigationEvents() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\f8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$OpenThreatActionDialog;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "message", "okButtonAction", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "negativeButtonLabel", "", "getNegativeButtonLabel", "()I", "getOkButtonAction", "()Lkotlin/jvm/functions/Function0;", "positiveButtonLabel", "getPositiveButtonLabel", "getTitle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenThreatActionDialog extends org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString title = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString message = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> okButtonAction = null;
        @androidx.annotation.StringRes()
        private final int positiveButtonLabel = org.wordpress.android.R.string.dialog_button_ok;
        @androidx.annotation.StringRes()
        private final int negativeButtonLabel = org.wordpress.android.R.string.dialog_button_cancel;
        
        public OpenThreatActionDialog(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString message, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> okButtonAction) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOkButtonAction() {
            return null;
        }
        
        public final int getPositiveButtonLabel() {
            return 0;
        }
        
        public final int getNegativeButtonLabel() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowUpdatedScanStateWithMessage;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents;", "messageRes", "", "(I)V", "getMessageRes", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowUpdatedScanStateWithMessage extends org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents {
        private final int messageRes = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents.ShowUpdatedScanStateWithMessage copy(@androidx.annotation.StringRes()
        int messageRes) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ShowUpdatedScanStateWithMessage(@androidx.annotation.StringRes()
        int messageRes) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getMessageRes() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowUpdatedFixState;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents;", "threatId", "", "(J)V", "getThreatId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowUpdatedFixState extends org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents {
        private final long threatId = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents.ShowUpdatedFixState copy(long threatId) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ShowUpdatedFixState(long threatId) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getThreatId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowGetFreeEstimate;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents;", "()V", "url", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowGetFreeEstimate extends org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents.ShowGetFreeEstimate INSTANCE = null;
        
        private ShowGetFreeEstimate() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String url() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$ShowJetpackSettings;", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowJetpackSettings extends org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents.ShowJetpackSettings copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ShowJetpackSettings(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
    }
}