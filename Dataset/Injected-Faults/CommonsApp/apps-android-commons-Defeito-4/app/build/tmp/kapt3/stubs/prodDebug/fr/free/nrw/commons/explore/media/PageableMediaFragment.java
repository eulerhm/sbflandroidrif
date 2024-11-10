package fr.free.nrw.commons.explore.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u0011H\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0016J\u0012\u0010\"\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001e\u001a\u00020\u0011H\u0016J\b\u0010#\u001a\u00020\u0011H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0016J&\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020%H\u0016J\u001a\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\u0011H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lfr/free/nrw/commons/explore/media/PageableMediaFragment;", "Lfr/free/nrw/commons/explore/paging/BasePagingFragment;", "Lfr/free/nrw/commons/Media;", "Lfr/free/nrw/commons/media/MediaDetailPagerFragment$MediaDetailProvider;", "()V", "_binding", "Lfr/free/nrw/commons/databinding/FragmentSearchPaginatedBinding;", "binding", "getBinding", "()Lfr/free/nrw/commons/databinding/FragmentSearchPaginatedBinding;", "categoryImagesCallback", "Lfr/free/nrw/commons/category/CategoryImagesCallback;", "getCategoryImagesCallback", "()Lfr/free/nrw/commons/category/CategoryImagesCallback;", "setCategoryImagesCallback", "(Lfr/free/nrw/commons/category/CategoryImagesCallback;)V", "errorTextId", "", "getErrorTextId", "()I", "pagedListAdapter", "Lfr/free/nrw/commons/explore/media/PagedMediaAdapter;", "getPagedListAdapter", "()Lfr/free/nrw/commons/explore/media/PagedMediaAdapter;", "pagedListAdapter$delegate", "Lkotlin/Lazy;", "simpleDataObserver", "Lfr/free/nrw/commons/explore/media/SimpleDataObserver;", "getContributionStateAt", "", "position", "getEmptyText", "", "query", "getMediaAtPosition", "getTotalMediaCount", "onAttach", "", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "refreshNominatedMedia", "index", "app-commons-v4.2.1-master_prodDebug"})
public abstract class PageableMediaFragment extends fr.free.nrw.commons.explore.paging.BasePagingFragment<fr.free.nrw.commons.Media> implements fr.free.nrw.commons.media.MediaDetailPagerFragment.MediaDetailProvider {
    
    /**
     * ViewBinding
     */
    private fr.free.nrw.commons.databinding.FragmentSearchPaginatedBinding _binding;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy pagedListAdapter$delegate = null;
    private final int errorTextId = fr.free.nrw.commons.R.string.error_loading_images;
    public fr.free.nrw.commons.category.CategoryImagesCallback categoryImagesCallback;
    private final fr.free.nrw.commons.explore.media.SimpleDataObserver simpleDataObserver = null;
    private java.util.HashMap _$_findViewCache;
    
    public PageableMediaFragment() {
        super();
    }
    
    private final fr.free.nrw.commons.databinding.FragmentSearchPaginatedBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.explore.media.PagedMediaAdapter getPagedListAdapter() {
        return null;
    }
    
    @java.lang.Override
    public int getErrorTextId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String getEmptyText(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.category.CategoryImagesCallback getCategoryImagesCallback() {
        return null;
    }
    
    public final void setCategoryImagesCallback(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryImagesCallback p0) {
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public fr.free.nrw.commons.Media getMediaAtPosition(int position) {
        return null;
    }
    
    @java.lang.Override
    public int getTotalMediaCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Void getContributionStateAt(int position) {
        return null;
    }
    
    /**
     * Reload media detail fragment once media is nominated
     *
     * @param index item position that has been nominated
     */
    @java.lang.Override
    public void refreshNominatedMedia(int index) {
    }
}