package org.wordpress.android.ui.reader.viewmodels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/SubfilterPageViewModel;", "Landroidx/lifecycle/ViewModel;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "_emptyState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterBottomSheetEmptyUiState;", "category", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "emptyState", "Landroidx/lifecycle/LiveData;", "getEmptyState", "()Landroidx/lifecycle/LiveData;", "isStarted", "", "onSubFiltersChanged", "", "isEmpty", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SubfilterPageViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.reader.subfilter.SubfilterBottomSheetEmptyUiState> _emptyState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.subfilter.SubfilterBottomSheetEmptyUiState> emptyState = null;
    private boolean isStarted = false;
    private org.wordpress.android.ui.reader.subfilter.SubfilterCategory category;
    
    @javax.inject.Inject()
    public SubfilterPageViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.subfilter.SubfilterBottomSheetEmptyUiState> getEmptyState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.subfilter.SubfilterCategory category) {
    }
    
    public final void onSubFiltersChanged(boolean isEmpty) {
    }
}