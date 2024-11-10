package fr.free.nrw.commons.explore;

import java.lang.System;

/**
 * The Dagger Module for explore:depictions related presenters and (some other objects maybe in future)
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\'J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\'J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/explore/SearchModule;", "", "()V", "bindsSearchCategoriesFragmentPresenter", "Lfr/free/nrw/commons/explore/categories/search/SearchCategoriesFragmentPresenter;", "Lfr/free/nrw/commons/explore/categories/search/SearchCategoriesFragmentPresenterImpl;", "bindsSearchDepictionsFragmentPresenter", "Lfr/free/nrw/commons/explore/depictions/search/SearchDepictionsFragmentPresenter;", "Lfr/free/nrw/commons/explore/depictions/search/SearchDepictionsFragmentPresenterImpl;", "bindsSearchMediaFragmentPresenter", "Lfr/free/nrw/commons/explore/media/SearchMediaFragmentPresenter;", "Lfr/free/nrw/commons/explore/media/SearchMediaFragmentPresenterImpl;", "app-commons-v4.2.1-master_prodDebug"})
@dagger.Module
public abstract class SearchModule {
    
    public SearchModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.depictions.search.SearchDepictionsFragmentPresenter bindsSearchDepictionsFragmentPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.search.SearchDepictionsFragmentPresenterImpl $this$bindsSearchDepictionsFragmentPresenter);
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.categories.search.SearchCategoriesFragmentPresenter bindsSearchCategoriesFragmentPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.categories.search.SearchCategoriesFragmentPresenterImpl $this$bindsSearchCategoriesFragmentPresenter);
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.media.SearchMediaFragmentPresenter bindsSearchMediaFragmentPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.media.SearchMediaFragmentPresenterImpl $this$bindsSearchMediaFragmentPresenter);
}