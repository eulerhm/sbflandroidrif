package fr.free.nrw.commons.explore.depictions;

import java.lang.System;

/**
 * The Dagger Module for explore:depictions related presenters and (some other objects maybe in future)
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\'J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\'J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/explore/depictions/DepictionModule;", "", "()V", "bindsChildDepictionPresenter", "Lfr/free/nrw/commons/explore/depictions/child/ChildDepictionsPresenter;", "Lfr/free/nrw/commons/explore/depictions/child/ChildDepictionsPresenterImpl;", "bindsDepictedImagesContractPresenter", "Lfr/free/nrw/commons/explore/depictions/media/DepictedImagesPresenter;", "Lfr/free/nrw/commons/explore/depictions/media/DepictedImagesPresenterImpl;", "bindsParentDepictionPresenter", "Lfr/free/nrw/commons/explore/depictions/parent/ParentDepictionsPresenter;", "Lfr/free/nrw/commons/explore/depictions/parent/ParentDepictionsPresenterImpl;", "app-commons-v4.2.1-master_prodDebug"})
@dagger.Module
public abstract class DepictionModule {
    
    public DepictionModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.depictions.parent.ParentDepictionsPresenter bindsParentDepictionPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.parent.ParentDepictionsPresenterImpl $this$bindsParentDepictionPresenter);
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.depictions.child.ChildDepictionsPresenter bindsChildDepictionPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.child.ChildDepictionsPresenterImpl $this$bindsChildDepictionPresenter);
    
    @org.jetbrains.annotations.NotNull
    @dagger.Binds
    public abstract fr.free.nrw.commons.explore.depictions.media.DepictedImagesPresenter bindsDepictedImagesContractPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.media.DepictedImagesPresenterImpl $this$bindsDepictedImagesContractPresenter);
}