package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB#\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0002\u000e\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "", "buttonType", "Lorg/wordpress/android/widgets/PostListButtonType;", "onButtonClicked", "Lkotlin/Function1;", "", "(Lorg/wordpress/android/widgets/PostListButtonType;Lkotlin/jvm/functions/Function1;)V", "getButtonType", "()Lorg/wordpress/android/widgets/PostListButtonType;", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "MoreItem", "SingleItem", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction$SingleItem;", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction$MoreItem;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListItemAction {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.widgets.PostListButtonType buttonType = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> onButtonClicked = null;
    
    private PostListItemAction(org.wordpress.android.widgets.PostListButtonType buttonType, kotlin.jvm.functions.Function1<? super org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> onButtonClicked) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.widgets.PostListButtonType getButtonType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> getOnButtonClicked() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemAction$SingleItem;", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "buttonType", "Lorg/wordpress/android/widgets/PostListButtonType;", "onButtonClicked", "Lkotlin/Function1;", "", "(Lorg/wordpress/android/widgets/PostListButtonType;Lkotlin/jvm/functions/Function1;)V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SingleItem extends org.wordpress.android.viewmodel.posts.PostListItemAction {
        
        public SingleItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.widgets.PostListButtonType buttonType, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> onButtonClicked) {
            super(null, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemAction$MoreItem;", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "actions", "", "onButtonClicked", "Lkotlin/Function1;", "Lorg/wordpress/android/widgets/PostListButtonType;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getActions", "()Ljava/util/List;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MoreItem extends org.wordpress.android.viewmodel.posts.PostListItemAction {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.viewmodel.posts.PostListItemAction> actions = null;
        
        public MoreItem(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.viewmodel.posts.PostListItemAction> actions, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> onButtonClicked) {
            super(null, null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.viewmodel.posts.PostListItemAction> getActions() {
            return null;
        }
    }
}