package org.wordpress.android.ui.sitecreation.misc;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001:\u0001DB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0016J\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fJ\u0006\u0010\u0013\u001a\u00020\nJ\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\fH\u0016J\"\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\fJ\"\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00192\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u001a\u001a\u00020\nJ\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0018\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\fJ\u0010\u0010 \u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010!\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0006\u0010\"\u001a\u00020\nJ\u0018\u0010#\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010$\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\fJ\u0010\u0010%\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\fJ\u0016\u0010&\u001a\u00020\n2\u0006\u0010\'\u001a\u00020\f2\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\nJ\u0010\u0010+\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\fJ\u000e\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020.J\u0018\u0010/\u001a\u00020\n2\u0010\u00100\u001a\f\u0012\u0004\u0012\u00020\f\u0012\u0002\b\u000301J\u0016\u00102\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u00103\u001a\u00020\u0006J\u0006\u00104\u001a\u00020\nJ\u000e\u00105\u001a\u00020\n2\u0006\u00106\u001a\u00020\fJ\u0006\u00107\u001a\u00020\nJ\u000e\u00108\u001a\u00020\n2\u0006\u00109\u001a\u00020\fJ\u0006\u0010:\u001a\u00020\nJ\u0006\u0010;\u001a\u00020\nJ\u000e\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\fJ\u0006\u0010>\u001a\u00020\nJ\u0006\u0010?\u001a\u00020\nJ\u0010\u0010@\u001a\u00020\n2\b\u0010A\u001a\u0004\u0018\u00010\fJ\u0006\u0010B\u001a\u00020\nJ\u0006\u0010C\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006E"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerTracker;", "tracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "designSelectionSkipped", "", "getTracker", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "filterDeselected", "", "filter", "", "selectedFilters", "", "filterSelected", "trackDomainSelected", "chosenDomain", "searchTerm", "trackDomainsAccessed", "trackErrorShown", "message", "errorContext", "errorType", "errorDescription", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationErrorType;", "trackFlowExited", "trackNoNetworkErrorShown", "trackPreviewLoaded", "template", "mode", "trackPreviewLoading", "trackPreviewModeChanged", "trackPreviewModeTapped", "trackPreviewOkButtonTapped", "trackPreviewViewed", "trackPreviewWebviewFullyLoaded", "trackPreviewWebviewShown", "trackSegmentSelected", "segmentName", "segmentId", "", "trackSegmentsViewed", "trackSiteCreated", "trackSiteCreationAccessed", "siteCreationSource", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSource;", "trackSiteCreationServiceStateUpdated", "props", "", "trackSiteDesignSelected", "recommended", "trackSiteDesignSkipped", "trackSiteDesignViewed", "previewMode", "trackSiteIntentQuestionCanceled", "trackSiteIntentQuestionCustomVerticalSelected", "searchInput", "trackSiteIntentQuestionSearchFocused", "trackSiteIntentQuestionSkipped", "trackSiteIntentQuestionVerticalSelected", "verticalSlug", "trackSiteIntentQuestionViewed", "trackSiteNameCanceled", "trackSiteNameEntered", "siteName", "trackSiteNameSkipped", "trackSiteNameViewed", "PROPERTY", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class SiteCreationTracker implements org.wordpress.android.ui.layoutpicker.LayoutPickerTracker {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker = null;
    private boolean designSelectionSkipped = false;
    
    @javax.inject.Inject()
    public SiteCreationTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper getTracker() {
        return null;
    }
    
    public final void trackSiteCreationAccessed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationSource siteCreationSource) {
    }
    
    public final void trackSegmentsViewed() {
    }
    
    public final void trackSegmentSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String segmentName, long segmentId) {
    }
    
    public final void trackDomainsAccessed() {
    }
    
    public final void trackDomainSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String chosenDomain, @org.jetbrains.annotations.NotNull()
    java.lang.String searchTerm) {
    }
    
    public final void trackPreviewLoading(@org.jetbrains.annotations.Nullable()
    java.lang.String template) {
    }
    
    public final void trackPreviewWebviewShown(@org.jetbrains.annotations.Nullable()
    java.lang.String template) {
    }
    
    public final void trackPreviewWebviewFullyLoaded(@org.jetbrains.annotations.Nullable()
    java.lang.String template) {
    }
    
    public final void trackPreviewOkButtonTapped() {
    }
    
    /**
     * This stat is part of a funnel that provides critical information.  Before
     * making ANY modification to this stat please refer to: p4qSXL-35X-p2
     */
    public final void trackSiteCreated(@org.jetbrains.annotations.Nullable()
    java.lang.String template) {
    }
    
    public final void trackFlowExited() {
    }
    
    public final void trackErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String errorContext, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationErrorType errorType, @org.jetbrains.annotations.Nullable()
    java.lang.String errorDescription) {
    }
    
    public final void trackErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String errorContext, @org.jetbrains.annotations.NotNull()
    java.lang.String errorType, @org.jetbrains.annotations.Nullable()
    java.lang.String errorDescription) {
    }
    
    public final void trackSiteCreationServiceStateUpdated(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> props) {
    }
    
    public final void trackSiteDesignViewed(@org.jetbrains.annotations.NotNull()
    java.lang.String previewMode) {
    }
    
    public final void trackSiteDesignSkipped() {
    }
    
    public final void trackSiteDesignSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String template, boolean recommended) {
    }
    
    @java.lang.Override()
    public void trackPreviewViewed(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewLoading(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewLoaded(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewModeTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewModeChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackNoNetworkErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @java.lang.Override()
    public void trackErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @java.lang.Override()
    public void filterSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedFilters) {
    }
    
    @java.lang.Override()
    public void filterDeselected(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedFilters) {
    }
    
    public final void trackSiteIntentQuestionViewed() {
    }
    
    public final void trackSiteIntentQuestionCanceled() {
    }
    
    public final void trackSiteIntentQuestionSkipped() {
    }
    
    public final void trackSiteIntentQuestionSearchFocused() {
    }
    
    public final void trackSiteIntentQuestionCustomVerticalSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String searchInput) {
    }
    
    public final void trackSiteIntentQuestionVerticalSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String verticalSlug) {
    }
    
    public final void trackSiteNameViewed() {
    }
    
    public final void trackSiteNameCanceled() {
    }
    
    public final void trackSiteNameSkipped() {
    }
    
    public final void trackSiteNameEntered(@org.jetbrains.annotations.Nullable()
    java.lang.String siteName) {
    }
    
    public void trackThumbnailModeTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker$PROPERTY;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "TEMPLATE", "SEGMENT_NAME", "SEGMENT_ID", "CHOSEN_DOMAIN", "SEARCH_TERM", "THUMBNAIL_MODE", "PREVIEW_MODE", "LOCATION", "FILTER", "SELECTED_FILTERS", "VERTICAL_SLUG", "SITE_NAME", "RECOMMENDED", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum PROPERTY {
        /*public static final*/ TEMPLATE /* = new TEMPLATE(null) */,
        /*public static final*/ SEGMENT_NAME /* = new SEGMENT_NAME(null) */,
        /*public static final*/ SEGMENT_ID /* = new SEGMENT_ID(null) */,
        /*public static final*/ CHOSEN_DOMAIN /* = new CHOSEN_DOMAIN(null) */,
        /*public static final*/ SEARCH_TERM /* = new SEARCH_TERM(null) */,
        /*public static final*/ THUMBNAIL_MODE /* = new THUMBNAIL_MODE(null) */,
        /*public static final*/ PREVIEW_MODE /* = new PREVIEW_MODE(null) */,
        /*public static final*/ LOCATION /* = new LOCATION(null) */,
        /*public static final*/ FILTER /* = new FILTER(null) */,
        /*public static final*/ SELECTED_FILTERS /* = new SELECTED_FILTERS(null) */,
        /*public static final*/ VERTICAL_SLUG /* = new VERTICAL_SLUG(null) */,
        /*public static final*/ SITE_NAME /* = new SITE_NAME(null) */,
        /*public static final*/ RECOMMENDED /* = new RECOMMENDED(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        
        PROPERTY(java.lang.String key) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getKey() {
            return null;
        }
    }
}