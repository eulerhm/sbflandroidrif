package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0016H\u0007R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler;", "", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/EventBusWrapper;)V", "_onUploadedItem", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel;", "onUploadedItem", "Landroidx/lifecycle/LiveData;", "getOnUploadedItem", "()Landroidx/lifecycle/LiveData;", "clear", "", "onEventMainThread", "event", "Lorg/wordpress/android/ui/uploads/UploadService$UploadErrorEvent;", "Lorg/wordpress/android/ui/uploads/UploadService$UploadMediaSuccessEvent;", "ItemUploadedModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteIconUploadHandler {
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel>> _onUploadedItem = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel>> onUploadedItem = null;
    
    @javax.inject.Inject()
    public SiteIconUploadHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel>> getOnUploadedItem() {
        return null;
    }
    
    public final void clear() {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadService.UploadErrorEvent event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadService.UploadMediaSuccessEvent event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u001d\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0002\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "errorMessage", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "MediaUploaded", "PostUploaded", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel$PostUploaded;", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel$MediaUploaded;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ItemUploadedModel {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String errorMessage = null;
        
        private ItemUploadedModel(org.wordpress.android.fluxc.model.SiteModel site, java.lang.String errorMessage) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.String getErrorMessage() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0007H\u00d6\u0001R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel$PostUploaded;", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "errorMessage", "", "(Lorg/wordpress/android/fluxc/model/PostModel;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PostUploaded extends org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.PostModel post = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String errorMessage = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel.PostUploaded copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
            java.lang.String errorMessage) {
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
            
            public PostUploaded(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
            java.lang.String errorMessage) {
                super(null, null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.PostModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.PostModel getPost() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.fluxc.model.SiteModel component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.String getErrorMessage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\bH\u00d6\u0001R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel$MediaUploaded;", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel;", "media", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "errorMessage", "", "(Ljava/util/List;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "getMedia", "()Ljava/util/List;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class MediaUploaded extends org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.fluxc.model.MediaModel> media = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String errorMessage = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel.MediaUploaded copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> media, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
            java.lang.String errorMessage) {
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
            
            public MediaUploaded(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> media, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
            java.lang.String errorMessage) {
                super(null, null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.MediaModel> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.MediaModel> getMedia() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.fluxc.model.SiteModel component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.String getErrorMessage() {
                return null;
            }
        }
    }
}