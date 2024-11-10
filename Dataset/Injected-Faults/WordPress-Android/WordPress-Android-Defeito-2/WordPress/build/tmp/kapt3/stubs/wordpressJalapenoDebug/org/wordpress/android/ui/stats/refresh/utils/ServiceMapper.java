package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper;", "", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;)V", "getService", "Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper$Service;", "service", "", "map", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "services", "Lorg/wordpress/android/fluxc/model/stats/PublicizeModel$Service;", "header", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Header;", "Service", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ServiceMapper {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    
    @javax.inject.Inject()
    public ServiceMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon> map(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.PublicizeModel.Service> services, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.utils.ServiceMapper.Service getService(java.lang.String service) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper$Service;", "", "iconUrl", "", "nameResource", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getIconUrl", "()Ljava/lang/String;", "getNameResource", "()I", "FACEBOOK", "TWITTER", "TUMBLR", "GOOGLE_PLUS", "LINKED_IN", "PATH", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Service {
        /*public static final*/ FACEBOOK /* = new FACEBOOK(null, 0) */,
        /*public static final*/ TWITTER /* = new TWITTER(null, 0) */,
        /*public static final*/ TUMBLR /* = new TUMBLR(null, 0) */,
        /*public static final*/ GOOGLE_PLUS /* = new GOOGLE_PLUS(null, 0) */,
        /*public static final*/ LINKED_IN /* = new LINKED_IN(null, 0) */,
        /*public static final*/ PATH /* = new PATH(null, 0) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String iconUrl = null;
        private final int nameResource = 0;
        
        Service(java.lang.String iconUrl, @androidx.annotation.StringRes()
        int nameResource) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getIconUrl() {
            return null;
        }
        
        public final int getNameResource() {
            return 0;
        }
    }
}