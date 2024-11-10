package org.wordpress.android.ui.reader.repository;

import java.lang.System;

/**
 * ReaderTagRepository is middleware that encapsulates data related business related data logic
 * Handle communicate with ReaderServices and Actions
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001BS\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0011\u0010\u001f\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0018H\u0002J\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0011\u0010%\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u001f\u0010&\u001a\u00020 2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00150(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderTagRepository;", "", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "fetchInterestTagUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/tags/FetchInterestTagsUseCase;", "followInterestTagsUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/tags/FollowInterestTagsUseCase;", "fetchFollowedTagUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/tags/FetchFollowedTagsUseCase;", "getFollowedTagsUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/tags/GetFollowedTagsUseCase;", "shouldAutoUpdateTagUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/ShouldAutoUpdateTagUseCase;", "readerTagTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderTagTableWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/ui/reader/repository/usecases/tags/FetchInterestTagsUseCase;Lorg/wordpress/android/ui/reader/repository/usecases/tags/FollowInterestTagsUseCase;Lorg/wordpress/android/ui/reader/repository/usecases/tags/FetchFollowedTagsUseCase;Lorg/wordpress/android/ui/reader/repository/usecases/tags/GetFollowedTagsUseCase;Lorg/wordpress/android/ui/reader/repository/usecases/ShouldAutoUpdateTagUseCase;Lorg/wordpress/android/datasets/wrappers/ReaderTagTableWrapper;)V", "followingReaderTag", "Lorg/wordpress/android/models/ReaderTag;", "mutableRecommendedInterests", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/models/ReaderTagList;", "recommendedInterests", "Landroidx/lifecycle/LiveData;", "clearTagLastUpdated", "", "tag", "(Lorg/wordpress/android/models/ReaderTag;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInterests", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMockInterests", "getMockRecommendedInterests", "getRecommendedInterests", "getUserTags", "saveInterests", "tags", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderTagRepository {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.repository.usecases.tags.FetchInterestTagsUseCase fetchInterestTagUseCase = null;
    private final org.wordpress.android.ui.reader.repository.usecases.tags.FollowInterestTagsUseCase followInterestTagsUseCase = null;
    private final org.wordpress.android.ui.reader.repository.usecases.tags.FetchFollowedTagsUseCase fetchFollowedTagUseCase = null;
    private final org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase getFollowedTagsUseCase = null;
    private final org.wordpress.android.ui.reader.repository.usecases.ShouldAutoUpdateTagUseCase shouldAutoUpdateTagUseCase = null;
    private final org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper readerTagTableWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.ReaderTagList> mutableRecommendedInterests = null;
    private final androidx.lifecycle.LiveData<org.wordpress.android.models.ReaderTagList> recommendedInterests = null;
    private final org.wordpress.android.models.ReaderTag followingReaderTag = null;
    
    @javax.inject.Inject()
    public ReaderTagRepository(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.tags.FetchInterestTagsUseCase fetchInterestTagUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.tags.FollowInterestTagsUseCase followInterestTagsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.tags.FetchFollowedTagsUseCase fetchFollowedTagUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase getFollowedTagsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.ShouldAutoUpdateTagUseCase shouldAutoUpdateTagUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper readerTagTableWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getInterests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserTags(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveInterests(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.models.ReaderTag> tags, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearTagLastUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag tag, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRecommendedInterests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.lifecycle.LiveData<org.wordpress.android.models.ReaderTagList>> continuation) {
        return null;
    }
    
    private final java.lang.Object getMockRecommendedInterests(kotlin.coroutines.Continuation<? super androidx.lifecycle.LiveData<org.wordpress.android.models.ReaderTagList>> continuation) {
        return null;
    }
    
    private final org.wordpress.android.models.ReaderTagList getMockInterests() {
        return null;
    }
}