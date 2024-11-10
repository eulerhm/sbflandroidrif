package org.wordpress.android.ui.reader.utils;

import java.lang.System;

/**
 * Injectable wrapper around ReaderTag.
 *
 * ReaderTag interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/ReaderTagWrapper;", "", "()V", "createDiscoverPostCardsTag", "Lorg/wordpress/android/models/ReaderTag;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderTagWrapper {
    
    @javax.inject.Inject()
    public ReaderTagWrapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderTag createDiscoverPostCardsTag() {
        return null;
    }
}