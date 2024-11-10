package org.wordpress.android.ui.sitecreation.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layout", "", "(Landroid/view/ViewGroup;I)V", "getParent$org_wordpress_android_wordpressJalapenoDebug", "()Landroid/view/ViewGroup;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "DomainSuggestionErrorViewHolder", "DomainSuggestionItemViewHolder", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder$DomainSuggestionItemViewHolder;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder$DomainSuggestionErrorViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class SiteCreationDomainViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final android.view.ViewGroup parent = null;
    
    private SiteCreationDomainViewHolder(android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layout) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.ViewGroup getParent$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState uiState);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \b*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder$DomainSuggestionItemViewHolder;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "container", "kotlin.jvm.PlatformType", "domainSuggestion", "Landroid/widget/TextView;", "domainUnavailability", "nameSuggestion", "onDomainSelected", "Lkotlin/Function0;", "", "suggestionRadioButton", "Landroid/widget/RadioButton;", "onBind", "uiState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainSuggestionItemViewHolder extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainViewHolder {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final android.view.ViewGroup container = null;
        private final android.widget.TextView nameSuggestion = null;
        private final android.widget.TextView domainSuggestion = null;
        private final android.widget.RadioButton suggestionRadioButton = null;
        private final android.widget.TextView domainUnavailability = null;
        private kotlin.jvm.functions.Function0<kotlin.Unit> onDomainSelected;
        
        public DomainSuggestionItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null, 0);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState uiState) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder$DomainSuggestionErrorViewHolder;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainViewHolder;", "parentView", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "retry", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "text", "addRetryCompoundDrawable", "", "onBind", "uiState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainSuggestionErrorViewHolder extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainViewHolder {
        private final android.widget.TextView text = null;
        private final android.widget.TextView retry = null;
        
        public DomainSuggestionErrorViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView) {
            super(null, 0);
        }
        
        private final void addRetryCompoundDrawable() {
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState uiState) {
        }
    }
}