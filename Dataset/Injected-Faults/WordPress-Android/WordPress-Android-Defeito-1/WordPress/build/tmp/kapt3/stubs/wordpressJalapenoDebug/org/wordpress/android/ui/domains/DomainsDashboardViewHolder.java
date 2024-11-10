package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0007\t\n\u000b\f\r\u000e\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0005R\u0016\u0010\u0004\u001a\u00028\u0000X\u0084\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0007\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "(Landroidx/viewbinding/ViewBinding;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "AddDomainViewHolder", "DomainBlurbViewHolder", "FreeDomainViewHolder", "ManageDomainsViewHolder", "PurchaseDomainViewHolder", "SiteDomainsHeaderViewHolder", "SiteDomainsViewHolder", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$FreeDomainViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$SiteDomainsHeaderViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$SiteDomainsViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$AddDomainViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$ManageDomainsViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$PurchaseDomainViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$DomainBlurbViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class DomainsDashboardViewHolder<T extends androidx.viewbinding.ViewBinding> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final T binding = null;
    
    private DomainsDashboardViewHolder(T binding) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final T getBinding() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$FreeDomainViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainFreeSiteAddressCardBinding;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$FreeDomain;", "popupMenuClick", "v", "Landroid/view/View;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FreeDomainViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainFreeSiteAddressCardBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public FreeDomainViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.FreeDomain item) {
        }
        
        private final void popupMenuClick(org.wordpress.android.ui.domains.DomainsDashboardItem.FreeDomain item, android.view.View v) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$SiteDomainsHeaderViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainSiteDomainsHeaderBinding;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$SiteDomainsHeader;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteDomainsHeaderViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainSiteDomainsHeaderBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public SiteDomainsHeaderViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.SiteDomainsHeader item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$SiteDomainsViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainSiteDomainsCardBinding;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$SiteDomains;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteDomainsViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainSiteDomainsCardBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public SiteDomainsViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.SiteDomains item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$AddDomainViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainAddDomainCtaBinding;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$AddDomain;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class AddDomainViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainAddDomainCtaBinding> {
        
        public AddDomainViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.AddDomain item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$ManageDomainsViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainManageDomainsCtaBinding;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$ManageDomains;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ManageDomainsViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainManageDomainsCtaBinding> {
        
        public ManageDomainsViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.ManageDomains item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$PurchaseDomainViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainPurchaseCardBinding;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$PurchaseDomain;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PurchaseDomainViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainPurchaseCardBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public PurchaseDomainViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.PurchaseDomain item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder$DomainBlurbViewHolder;", "Lorg/wordpress/android/ui/domains/DomainsDashboardViewHolder;", "Lorg/wordpress/android/databinding/DomainSiteDomainsBlurbBinding;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$DomainBlurb;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainBlurbViewHolder extends org.wordpress.android.ui.domains.DomainsDashboardViewHolder<org.wordpress.android.databinding.DomainSiteDomainsBlurbBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public DomainBlurbViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainsDashboardItem.DomainBlurb item) {
        }
    }
}