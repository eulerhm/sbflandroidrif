package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\u001b\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainSuggestionsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/wordpress/android/ui/domains/DomainSuggestionItem;", "Lorg/wordpress/android/ui/domains/DomainSuggestionsViewHolder;", "itemSelectionListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DomainSuggestionItemDiffCallback", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainSuggestionsAdapter extends androidx.recyclerview.widget.ListAdapter<org.wordpress.android.ui.domains.DomainSuggestionItem, org.wordpress.android.ui.domains.DomainSuggestionsViewHolder> {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.domains.DomainSuggestionItem, kotlin.Unit> itemSelectionListener = null;
    
    public DomainSuggestionsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.domains.DomainSuggestionItem, kotlin.Unit> itemSelectionListener) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.domains.DomainSuggestionsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainSuggestionsViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainSuggestionsAdapter$DomainSuggestionItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/wordpress/android/ui/domains/DomainSuggestionItem;", "()V", "areContentsTheSame", "", "old", "new", "areItemsTheSame", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class DomainSuggestionItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.wordpress.android.ui.domains.DomainSuggestionItem> {
        
        public DomainSuggestionItemDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainSuggestionItem old, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainSuggestionItem p1_54480) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainSuggestionItem old, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainSuggestionItem p1_54480) {
            return false;
        }
    }
}