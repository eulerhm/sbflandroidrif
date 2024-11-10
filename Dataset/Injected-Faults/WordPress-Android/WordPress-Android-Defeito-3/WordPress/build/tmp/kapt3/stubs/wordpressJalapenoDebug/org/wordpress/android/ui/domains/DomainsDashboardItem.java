package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\t\u0007\b\t\n\u000b\f\r\u000e\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0007\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "", "type", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Type;", "(Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Type;)V", "getType", "()Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Type;", "Action", "AddDomain", "DomainBlurb", "FreeDomain", "ManageDomains", "PurchaseDomain", "SiteDomains", "SiteDomainsHeader", "Type", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$FreeDomain;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$SiteDomainsHeader;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$SiteDomains;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$AddDomain;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$ManageDomains;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$PurchaseDomain;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$DomainBlurb;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class DomainsDashboardItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.domains.DomainsDashboardItem.Type type = null;
    
    private DomainsDashboardItem(org.wordpress.android.ui.domains.DomainsDashboardItem.Type type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.domains.DomainsDashboardItem.Type getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Type;", "", "(Ljava/lang/String;I)V", "PRIMARY_DOMAIN", "SITE_DOMAINS_HEADER", "SITE_DOMAINS", "ADD_DOMAIN", "MANAGE_DOMAINS", "PURCHASE_DOMAIN", "DOMAIN_BLURB", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ PRIMARY_DOMAIN /* = new PRIMARY_DOMAIN() */,
        /*public static final*/ SITE_DOMAINS_HEADER /* = new SITE_DOMAINS_HEADER() */,
        /*public static final*/ SITE_DOMAINS /* = new SITE_DOMAINS() */,
        /*public static final*/ ADD_DOMAIN /* = new ADD_DOMAIN() */,
        /*public static final*/ MANAGE_DOMAINS /* = new MANAGE_DOMAINS() */,
        /*public static final*/ PURCHASE_DOMAIN /* = new PURCHASE_DOMAIN() */,
        /*public static final*/ DOMAIN_BLURB /* = new DOMAIN_BLURB() */;
        
        Type() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007H\u00c6\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$FreeDomain;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "domain", "Lorg/wordpress/android/ui/utils/UiString;", "isPrimary", "", "onPopupMenuClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Action;", "(Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function1;)V", "getDomain", "()Lorg/wordpress/android/ui/utils/UiString;", "()Z", "getOnPopupMenuClick", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FreeDomain extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString domain = null;
        private final boolean isPrimary = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.domains.DomainsDashboardItem.Action, java.lang.Boolean> onPopupMenuClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.FreeDomain copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString domain, boolean isPrimary, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.domains.DomainsDashboardItem.Action, java.lang.Boolean> onPopupMenuClick) {
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
        
        public FreeDomain(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString domain, boolean isPrimary, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.domains.DomainsDashboardItem.Action, java.lang.Boolean> onPopupMenuClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getDomain() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isPrimary() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.domains.DomainsDashboardItem.Action, java.lang.Boolean> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.domains.DomainsDashboardItem.Action, java.lang.Boolean> getOnPopupMenuClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$SiteDomainsHeader;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteDomainsHeader extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString title = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.SiteDomainsHeader copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title) {
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
        
        public SiteDomainsHeader(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$SiteDomains;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "domain", "Lorg/wordpress/android/ui/utils/UiString;", "expiry", "isPrimary", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Z)V", "getDomain", "()Lorg/wordpress/android/ui/utils/UiString;", "getExpiry", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteDomains extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString domain = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString expiry = null;
        private final boolean isPrimary = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.SiteDomains copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString domain, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString expiry, boolean isPrimary) {
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
        
        public SiteDomains(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString domain, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString expiry, boolean isPrimary) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getDomain() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getExpiry() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isPrimary() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$AddDomain;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class AddDomain extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.AddDomain copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
        
        public AddDomain(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$ManageDomains;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ManageDomains extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.ManageDomains copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
        
        public ManageDomains(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\bH\u00c6\u0003J8\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$PurchaseDomain;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "image", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "body", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getBody", "()Lorg/wordpress/android/ui/utils/UiString;", "getImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getTitle", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/ListItemInteraction;)Lorg/wordpress/android/ui/domains/DomainsDashboardItem$PurchaseDomain;", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PurchaseDomain extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer image = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString title = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString body = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.PurchaseDomain copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer image, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString body, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
        
        public PurchaseDomain(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer image, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString body, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getBody() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$DomainBlurb;", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "blurb", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getBlurb", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainBlurb extends org.wordpress.android.ui.domains.DomainsDashboardItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString blurb = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainsDashboardItem.DomainBlurb copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString blurb) {
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
        
        public DomainBlurb(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString blurb) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getBlurb() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Action;", "", "itemId", "", "(Ljava/lang/String;II)V", "getItemId", "()I", "CHANGE_SITE_ADDRESS", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Action {
        /*public static final*/ CHANGE_SITE_ADDRESS /* = new CHANGE_SITE_ADDRESS(0) */;
        private final int itemId = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.domains.DomainsDashboardItem.Action.Companion Companion = null;
        
        Action(@androidx.annotation.IdRes()
        int itemId) {
        }
        
        public final int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Action$Companion;", "", "()V", "fromItemId", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Action;", "itemId", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.domains.DomainsDashboardItem.Action fromItemId(int itemId) {
                return null;
            }
        }
    }
}