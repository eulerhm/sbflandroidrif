package org.wordpress.android.ui.jetpack.scan.builders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J?\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2%\b\u0002\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\n2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/builders/ThreatItemBuilder;", "", "dateFormatWrapper", "Lorg/wordpress/android/util/DateFormatWrapper;", "(Lorg/wordpress/android/util/DateFormatWrapper;)V", "buildThreatItem", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatItemState;", "threatModel", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel;", "onThreatItemClicked", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "threatId", "", "isFixing", "", "buildThreatItemDescription", "Lorg/wordpress/android/ui/utils/UiString;", "buildThreatItemHeader", "buildThreatItemIcon", "", "buildThreatItemIconBackground", "buildThreatItemSubHeader", "buildThreatItemSubHeaderColor", "getDateString", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "date", "Ljava/util/Date;", "getDisplayFileName", "", "fileName", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ThreatItemBuilder {
    private final org.wordpress.android.util.DateFormatWrapper dateFormatWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder.Companion Companion = null;
    private static final java.lang.String UNKNOWN_VULNERABLE_EXTENSION_TYPE = "Unexpected vulnerable extension threat type";
    
    @javax.inject.Inject()
    public ThreatItemBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateFormatWrapper dateFormatWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatItemState buildThreatItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onThreatItemClicked, boolean isFixing) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString buildThreatItemHeader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString buildThreatItemSubHeader(org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel) {
        return null;
    }
    
    private final int buildThreatItemSubHeaderColor(org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString buildThreatItemDescription(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel) {
        return null;
    }
    
    public final int buildThreatItemIcon(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel) {
        return 0;
    }
    
    public final int buildThreatItemIconBackground(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.scan.threat.ThreatModel threatModel) {
        return 0;
    }
    
    private final org.wordpress.android.ui.utils.UiString.UiStringText getDateString(java.util.Date date) {
        return null;
    }
    
    /**
     * Uses regex to remove the whole path except of the file name
     * e.g. "/var/www/html/jp-scan-daily/wp-admin/index.php" returns "index.php".
     */
    private final java.lang.String getDisplayFileName(java.lang.String fileName) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/builders/ThreatItemBuilder$Companion;", "", "()V", "UNKNOWN_VULNERABLE_EXTENSION_TYPE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}