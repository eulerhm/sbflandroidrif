package org.wordpress.android.ui.sitecreation.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u00020\u0004*\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0018\u0010\r\u001a\u00020\u0004*\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u0018\u0010\u000f\u001a\u00020\u0004*\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainSanitizer;", "", "()V", "firstPart", "", "getFirstPart", "(Ljava/lang/String;)Ljava/lang/String;", "firstPeriodIndex", "", "getFirstPeriodIndex", "(Ljava/lang/String;)Ljava/lang/Integer;", "lastPart", "getLastPart", "removeNonAlphanumeric", "getRemoveNonAlphanumeric", "removeProtocol", "getRemoveProtocol", "getDomain", "url", "getName", "sanitizeDomainQuery", "query", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationDomainSanitizer {
    
    @javax.inject.Inject()
    public SiteCreationDomainSanitizer() {
        super();
    }
    
    private final java.lang.String getRemoveProtocol(java.lang.String $this$removeProtocol) {
        return null;
    }
    
    private final java.lang.String getRemoveNonAlphanumeric(java.lang.String $this$removeNonAlphanumeric) {
        return null;
    }
    
    private final java.lang.Integer getFirstPeriodIndex(java.lang.String $this$firstPeriodIndex) {
        return null;
    }
    
    private final java.lang.String getFirstPart(java.lang.String $this$firstPart) {
        return null;
    }
    
    private final java.lang.String getLastPart(java.lang.String $this$lastPart) {
        return null;
    }
    
    /**
     * Returns the first part of the domain
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    /**
     * Returns the last part of the domain (after the first .)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDomain(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    /**
     * Sanitizes a query by taking the value before it's first period if it's present,
     * removes it's scheme and finally, removes any characters that aren't alphanumeric.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sanitizeDomainQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
}