package org.wordpress.android.ui.jetpack.scan.builders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 H2\u00020\u0001:\u0001HB7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJC\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0002\u00a2\u0006\u0002\u0010\u001aJ \u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0019H\u0002J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002J \u0010&\u001a\u00020\u001c2\b\b\u0001\u0010\'\u001a\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J#\u0010)\u001a\u00020*2\b\b\u0001\u0010+\u001a\u00020\u00152\n\b\u0001\u0010,\u001a\u0004\u0018\u00010\u0015H\u0002\u00a2\u0006\u0002\u0010-J\u0090\u0001\u0010.\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00190$2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122!\u00105\u001a\u001d\u0012\u0013\u0012\u00110\u0019\u00a2\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\u0013062\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;J\"\u0010<\u001a\b\u0012\u0004\u0012\u00020%0$2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u00010>H\u0002J%\u0010@\u001a\b\u0012\u0004\u0012\u00020%0$2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00190$H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ&\u0010B\u001a\u00020!2\u0006\u00101\u001a\u0002022\u0006\u0010\u0017\u001a\u00020\u00152\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0087\u0001\u0010C\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010/\u001a\u0002002\f\u0010D\u001a\b\u0012\u0004\u0012\u00020E0$2\u0006\u00101\u001a\u0002022\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122!\u00105\u001a\u001d\u0012\u0013\u0012\u00110\u0019\u00a2\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\u0013062\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J$\u0010F\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010G\u001a\u0002002\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006I"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/builders/ScanStateListItemsBuilder;", "", "dateProvider", "Lorg/wordpress/android/ui/reader/utils/DateProvider;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "threatItemBuilder", "Lorg/wordpress/android/ui/jetpack/scan/builders/ThreatItemBuilder;", "threatDetailsListItemsBuilder", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsListItemsBuilder;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "(Lorg/wordpress/android/ui/reader/utils/DateProvider;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/jetpack/scan/builders/ThreatItemBuilder;Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsListItemsBuilder;Lorg/wordpress/android/fluxc/store/ScanStore;)V", "buildEnterServerCredsMessageState", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$FootnoteState;", "onEnterServerCredsIconClicked", "Lkotlin/Function0;", "", "iconResId", "", "iconColorResId", "threatsCount", "siteId", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Integer;Ljava/lang/Integer;IJ)Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$FootnoteState;", "buildFixAllButtonAction", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ActionButtonState;", "onFixAllButtonClicked", "isEnabled", "", "buildLastScanDescription", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$DescriptionState;", "timeInMs", "buildProvisioningStateItems", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "buildScanButtonAction", "titleRes", "onClick", "buildScanIcon", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$IconState;", "icon", "color", "(ILjava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$IconState;", "buildScanStateListItems", "model", "Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "fixingThreatIds", "onScanButtonClicked", "onThreatItemClicked", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "threatId", "onHelpClicked", "(Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/util/List;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildScanningStateItems", "mostRecentStatus", "Lorg/wordpress/android/fluxc/model/scan/ScanStateModel$ScanProgressStatus;", "currentProgress", "buildThreatsFixingStateItems", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildThreatsFoundDescription", "buildThreatsFoundStateItems", "threats", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel;", "buildThreatsNotFoundStateItems", "scanStateModel", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ScanStateListItemsBuilder {
    private final org.wordpress.android.ui.reader.utils.DateProvider dateProvider = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder threatItemBuilder = null;
    private final org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsListItemsBuilder threatDetailsListItemsBuilder = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.scan.builders.ScanStateListItemsBuilder.Companion Companion = null;
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    
    @javax.inject.Inject()
    public ScanStateListItemsBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.DateProvider dateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder threatItemBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsListItemsBuilder threatDetailsListItemsBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"LongParameterList"})
    public final java.lang.Object buildScanStateListItems(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.scan.ScanStateModel model, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> fixingThreatIds, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onScanButtonClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFixAllButtonClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onThreatItemClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onHelpClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEnterServerCredsIconClicked, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState>> continuation) {
        return null;
    }
    
    private final java.lang.Object buildThreatsFixingStateItems(java.util.List<java.lang.Long> fixingThreatIds, kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState>> continuation) {
        return null;
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildThreatsFoundStateItems(org.wordpress.android.fluxc.model.scan.ScanStateModel model, java.util.List<? extends org.wordpress.android.fluxc.model.scan.threat.ThreatModel> threats, org.wordpress.android.fluxc.model.SiteModel site, kotlin.jvm.functions.Function0<kotlin.Unit> onScanButtonClicked, kotlin.jvm.functions.Function0<kotlin.Unit> onFixAllButtonClicked, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onThreatItemClicked, kotlin.jvm.functions.Function0<kotlin.Unit> onHelpClicked, kotlin.jvm.functions.Function0<kotlin.Unit> onEnterServerCredsIconClicked) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildThreatsNotFoundStateItems(org.wordpress.android.fluxc.model.scan.ScanStateModel scanStateModel, kotlin.jvm.functions.Function0<kotlin.Unit> onScanButtonClicked) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildScanningStateItems(org.wordpress.android.fluxc.model.scan.ScanStateModel.ScanProgressStatus mostRecentStatus, org.wordpress.android.fluxc.model.scan.ScanStateModel.ScanProgressStatus currentProgress) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildProvisioningStateItems() {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.IconState buildScanIcon(@androidx.annotation.DrawableRes()
    int icon, @androidx.annotation.ColorRes()
    java.lang.Integer color) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.ActionButtonState buildScanButtonAction(@androidx.annotation.StringRes()
    int titleRes, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.ActionButtonState buildFixAllButtonAction(kotlin.jvm.functions.Function0<kotlin.Unit> onFixAllButtonClicked, boolean isEnabled) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.DescriptionState buildLastScanDescription(long timeInMs) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.DescriptionState buildThreatsFoundDescription(org.wordpress.android.fluxc.model.SiteModel site, int threatsCount, kotlin.jvm.functions.Function0<kotlin.Unit> onHelpClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.scan.ScanListItemState.FootnoteState buildEnterServerCredsMessageState(kotlin.jvm.functions.Function0<kotlin.Unit> onEnterServerCredsIconClicked, @androidx.annotation.DrawableRes()
    java.lang.Integer iconResId, @androidx.annotation.ColorRes()
    java.lang.Integer iconColorResId, int threatsCount, long siteId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/builders/ScanStateListItemsBuilder$Companion;", "", "()V", "ONE_HOUR", "", "ONE_MINUTE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}