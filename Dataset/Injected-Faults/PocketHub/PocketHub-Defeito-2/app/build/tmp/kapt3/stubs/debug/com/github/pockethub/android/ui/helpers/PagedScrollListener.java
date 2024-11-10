package com.github.pockethub.android.ui.helpers;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xwray.groupie.Section;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/github/pockethub/android/ui/helpers/PagedScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "section", "Lcom/xwray/groupie/Section;", "listFetcher", "Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;", "(Lcom/xwray/groupie/Section;Lcom/github/pockethub/android/ui/helpers/PagedListFetcher;)V", "loadingIndicator", "Lcom/github/pockethub/android/ui/helpers/ResourceLoadingIndicator;", "onScrolled", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "app_debug"})
public final class PagedScrollListener extends androidx.recyclerview.widget.RecyclerView.OnScrollListener {
    private final com.github.pockethub.android.ui.helpers.ResourceLoadingIndicator loadingIndicator = null;
    private final com.xwray.groupie.Section section = null;
    private final com.github.pockethub.android.ui.helpers.PagedListFetcher<?> listFetcher = null;
    
    @java.lang.Override()
    public void onScrolled(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, int dx, int dy) {
    }
    
    public PagedScrollListener(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Section section, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.PagedListFetcher<?> listFetcher) {
        super();
    }
}