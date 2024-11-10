package org.wordpress.android.ui.jetpack.scan;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\f\r\u000e\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "type", "Lorg/wordpress/android/ui/jetpack/common/ViewType;", "(Lorg/wordpress/android/ui/jetpack/common/ViewType;)V", "getType", "()Lorg/wordpress/android/ui/jetpack/common/ViewType;", "FootnoteState", "ThreatDateItemState", "ThreatItemLoadingSkeletonState", "ThreatItemState", "ThreatsHeaderItemState", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatsHeaderItemState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatDateItemState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatItemState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatItemLoadingSkeletonState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ScanListItemState extends org.wordpress.android.ui.jetpack.common.JetpackListItemState {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.jetpack.common.ViewType type = null;
    
    private ScanListItemState(org.wordpress.android.ui.jetpack.common.ViewType type) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.jetpack.common.ViewType getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatsHeaderItemState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "threatsCount", "", "(I)V", "text", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getThreatsCount", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ThreatsHeaderItemState extends org.wordpress.android.ui.jetpack.scan.ScanListItemState {
        private final int threatsCount = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString.UiStringRes text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatsHeaderItemState copy(int threatsCount) {
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
        
        public ThreatsHeaderItemState(int threatsCount) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getThreatsCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringRes getText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatDateItemState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "text", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ThreatDateItemState extends org.wordpress.android.ui.jetpack.scan.ScanListItemState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatDateItemState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text) {
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
        
        public ThreatDateItemState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\b\b\u0001\u0010\r\u001a\u00020\u000b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010&\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000bH\u00c6\u0003J\t\u0010(\u001a\u00020\u000bH\u00c6\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00c6\u0003Jk\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u000b2\b\b\u0003\u0010\r\u001a\u00020\u000b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00c6\u0001J\u0013\u0010+\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010-H\u00d6\u0003J\t\u0010.\u001a\u00020\u000bH\u00d6\u0001J\b\u0010/\u001a\u00020\u0003H\u0016J\t\u00100\u001a\u000201H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u001a\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u00062"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatItemState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "threatId", "", "isFixing", "", "firstDetectedDate", "Lorg/wordpress/android/ui/utils/UiString;", "header", "subHeader", "subHeaderColor", "", "icon", "iconBackground", "onClick", "Lkotlin/Function0;", "", "(JZLorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;IIILkotlin/jvm/functions/Function0;)V", "getFirstDetectedDate", "()Lorg/wordpress/android/ui/utils/UiString;", "getHeader", "getIcon", "()I", "getIconBackground", "()Z", "isIconVisible", "isLoadingVisible", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "getSubHeader", "getSubHeaderColor", "getThreatId", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "longId", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ThreatItemState extends org.wordpress.android.ui.jetpack.scan.ScanListItemState {
        private final long threatId = 0L;
        private final boolean isFixing = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString firstDetectedDate = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString header = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString subHeader = null;
        private final int subHeaderColor = 0;
        private final int icon = 0;
        private final int iconBackground = 0;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
        private final boolean isLoadingVisible = false;
        private final boolean isIconVisible = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatItemState copy(long threatId, boolean isFixing, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString firstDetectedDate, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString header, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString subHeader, @androidx.annotation.AttrRes()
        int subHeaderColor, @androidx.annotation.DrawableRes()
        int icon, @androidx.annotation.DrawableRes()
        int iconBackground, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
        
        public ThreatItemState(long threatId, boolean isFixing, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString firstDetectedDate, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString header, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString subHeader, @androidx.annotation.AttrRes()
        int subHeaderColor, @androidx.annotation.DrawableRes()
        int icon, @androidx.annotation.DrawableRes()
        int iconBackground, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
            super(null);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getThreatId() {
            return 0L;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isFixing() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getFirstDetectedDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getHeader() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getSubHeader() {
            return null;
        }
        
        public final int component6() {
            return 0;
        }
        
        public final int getSubHeaderColor() {
            return 0;
        }
        
        public final int component7() {
            return 0;
        }
        
        public final int getIcon() {
            return 0;
        }
        
        public final int component8() {
            return 0;
        }
        
        public final int getIconBackground() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
            return null;
        }
        
        public final boolean isLoadingVisible() {
            return false;
        }
        
        public final boolean isIconVisible() {
            return false;
        }
        
        @java.lang.Override()
        public long longId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatItemLoadingSkeletonState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ThreatItemLoadingSkeletonState extends org.wordpress.android.ui.jetpack.scan.ScanListItemState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatItemLoadingSkeletonState INSTANCE = null;
        
        private ThreatItemLoadingSkeletonState() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u00c6\u0003JL\u0010\u001b\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$FootnoteState;", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "iconResId", "", "iconColorResId", "text", "Lorg/wordpress/android/ui/utils/UiString;", "isVisible", "", "onIconClick", "Lkotlin/Function0;", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)V", "getIconColorResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIconResId", "()Z", "getOnIconClick", "()Lkotlin/jvm/functions/Function0;", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$FootnoteState;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FootnoteState extends org.wordpress.android.ui.jetpack.common.JetpackListItemState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer iconResId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer iconColorResId = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        private final boolean isVisible = false;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.ScanListItemState.FootnoteState copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer iconResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer iconColorResId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, boolean isVisible, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick) {
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
        
        public FootnoteState(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer iconResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer iconColorResId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, boolean isVisible, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIconResId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIconColorResId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getText() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean isVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnIconClick() {
            return null;
        }
    }
}