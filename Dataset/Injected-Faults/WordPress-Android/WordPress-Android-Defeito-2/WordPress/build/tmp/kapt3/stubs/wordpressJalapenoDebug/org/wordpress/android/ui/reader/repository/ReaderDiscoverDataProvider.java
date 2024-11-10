package org.wordpress.android.ui.reader.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0011\u0010-\u001a\u00020.H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0011\u00100\u001a\u00020.H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\b\u00101\u001a\u00020.H\u0002J\u0010\u00102\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0007J\u0010\u00105\u001a\u00020.2\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020.2\u0006\u00103\u001a\u000209H\u0007J\b\u0010:\u001a\u00020.H\u0002J\u0010\u0010;\u001a\u00020.2\u0006\u00103\u001a\u00020<H\u0007J\u0010\u0010=\u001a\u00020.2\u0006\u00106\u001a\u000207H\u0002J\u0012\u0010>\u001a\u00020.2\b\u00106\u001a\u0004\u0018\u000107H\u0002J\u0011\u0010?\u001a\u00020.H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0011\u0010@\u001a\u00020.H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0006\u0010A\u001a\u00020.J\u0006\u0010B\u001a\u00020.R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010)\u001a\u00020*8F\u00a2\u0006\u0006\u001a\u0004\b+\u0010,R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006C"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderDiscoverDataProvider;", "Lkotlinx/coroutines/CoroutineScope;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "readerTagWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderTagWrapper;", "getDiscoverCardsUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/GetDiscoverCardsUseCase;", "shouldAutoUpdateTagUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/ShouldAutoUpdateTagUseCase;", "fetchDiscoverCardsUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/FetchDiscoverCardsUseCase;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/ui/reader/utils/ReaderTagWrapper;Lorg/wordpress/android/ui/reader/repository/usecases/GetDiscoverCardsUseCase;Lorg/wordpress/android/ui/reader/repository/usecases/ShouldAutoUpdateTagUseCase;Lorg/wordpress/android/ui/reader/repository/usecases/FetchDiscoverCardsUseCase;)V", "_communicationChannel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/reader/repository/ReaderDiscoverCommunication;", "_discoverFeed", "Lorg/wordpress/android/viewmodel/ReactiveMutableLiveData;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCards;", "communicationChannel", "Landroidx/lifecycle/LiveData;", "getCommunicationChannel", "()Landroidx/lifecycle/LiveData;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "discoverFeed", "getDiscoverFeed", "hasMoreCards", "", "isDirty", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isLoadMoreRequestInProgress", "isStarted", "job", "Lkotlinx/coroutines/Job;", "readerTag", "Lorg/wordpress/android/models/ReaderTag;", "getReaderTag", "()Lorg/wordpress/android/models/ReaderTag;", "loadCards", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadMoreCards", "onActiveDiscoverFeed", "onCardsUpdated", "event", "Lorg/wordpress/android/ui/reader/ReaderEvents$FetchDiscoverCardsEnded;", "onFailed", "task", "Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic$DiscoverTasks;", "onFollowedTagsChanged", "Lorg/wordpress/android/ui/reader/ReaderEvents$FollowedTagsChanged;", "onInactiveDiscoverFeed", "onReaderPostTableAction", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryEvent$ReaderPostTableActionEnded;", "onUnchanged", "onUpdated", "refreshCards", "reloadPosts", "start", "stop", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverDataProvider implements kotlinx.coroutines.CoroutineScope {
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.ui.reader.utils.ReaderTagWrapper readerTagWrapper = null;
    private final org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase getDiscoverCardsUseCase = null;
    private final org.wordpress.android.ui.reader.repository.usecases.ShouldAutoUpdateTagUseCase shouldAutoUpdateTagUseCase = null;
    private final org.wordpress.android.ui.reader.repository.usecases.FetchDiscoverCardsUseCase fetchDiscoverCardsUseCase = null;
    private kotlinx.coroutines.Job job;
    private boolean isStarted = false;
    private final java.util.concurrent.atomic.AtomicBoolean isDirty = null;
    private boolean isLoadMoreRequestInProgress = false;
    private final org.wordpress.android.viewmodel.ReactiveMutableLiveData<org.wordpress.android.models.discover.ReaderDiscoverCards> _discoverFeed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.models.discover.ReaderDiscoverCards> discoverFeed = null;
    private boolean hasMoreCards = true;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.repository.ReaderDiscoverCommunication>> _communicationChannel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.repository.ReaderDiscoverCommunication>> communicationChannel = null;
    
    @javax.inject.Inject()
    public ReaderDiscoverDataProvider(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderTagWrapper readerTagWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase getDiscoverCardsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.ShouldAutoUpdateTagUseCase shouldAutoUpdateTagUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.FetchDiscoverCardsUseCase fetchDiscoverCardsUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.discover.ReaderDiscoverCards> getDiscoverFeed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.repository.ReaderDiscoverCommunication>> getCommunicationChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderTag getReaderTag() {
        return null;
    }
    
    public final void start() {
    }
    
    public final void stop() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshCards(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadMoreCards(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object loadCards(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object reloadPosts(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void onUpdated(org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks task) {
    }
    
    private final void onUnchanged(org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks task) {
    }
    
    private final void onFailed(org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.DiscoverTasks task) {
    }
    
    private final void onActiveDiscoverFeed() {
    }
    
    private final void onInactiveDiscoverFeed() {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onReaderPostTableAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.ReaderRepositoryEvent.ReaderPostTableActionEnded event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onCardsUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.FetchDiscoverCardsEnded event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onFollowedTagsChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.FollowedTagsChanged event) {
    }
}