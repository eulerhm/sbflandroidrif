package fr.free.nrw.commons.explore.categories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\'J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\'J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/explore/categories/CategoriesModule;", "", "()V", "bindsCategoryMediaPresenter", "Lfr/free/nrw/commons/explore/categories/media/CategoryMediaPresenter;", "Lfr/free/nrw/commons/explore/categories/media/CategoryMediaPresenterImpl;", "bindsParentCategoriesPresenter", "Lfr/free/nrw/commons/explore/categories/parent/ParentCategoriesPresenter;", "Lfr/free/nrw/commons/explore/categories/parent/ParentCategoriesPresenterImpl;", "bindsSubCategoriesPresenter", "Lfr/free/nrw/commons/explore/categories/sub/SubCategoriesPresenter;", "Lfr/free/nrw/commons/explore/categories/sub/SubCategoriesPresenterImpl;", "app-commons-v4.2.1-master_prodDebug"})
@dagger.Module
public abstract class CategoriesModule {
    
    public CategoriesModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.categories.media.CategoryMediaPresenter bindsCategoryMediaPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.categories.media.CategoryMediaPresenterImpl $this$bindsCategoryMediaPresenter);
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.categories.sub.SubCategoriesPresenter bindsSubCategoriesPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.categories.sub.SubCategoriesPresenterImpl $this$bindsSubCategoriesPresenter);
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.categories.parent.ParentCategoriesPresenter bindsParentCategoriesPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.categories.parent.ParentCategoriesPresenterImpl $this$bindsParentCategoriesPresenter);
}