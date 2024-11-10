package org.wordpress.android.ui.bloggingprompts.onboarding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiState;", "", "()V", "Ready", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiState$Ready;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BloggingPromptsOnboardingUiState {
    
    private BloggingPromptsOnboardingUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\r\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\u0002\u0010\u0014J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\rH\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\rH\u00c6\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00c6\u0003J\u0093\u0001\u0010/\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u00032\b\b\u0003\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0003\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\r2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00c6\u0001J\u0013\u00100\u001a\u00020\r2\b\u00101\u001a\u0004\u0018\u000102H\u00d6\u0003J\t\u00103\u001a\u00020\u0003H\u00d6\u0001J\t\u00104\u001a\u000205H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001aR\u0011\u0010\u0012\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u001aR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016\u00a8\u00066"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiState$Ready;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiState;", "promptRes", "", "respondents", "", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "contentTopRes", "contentBottomRes", "contentNoteTitle", "contentNoteContent", "primaryButtonLabel", "isPrimaryButtonVisible", "", "onPrimaryButtonClick", "Lkotlin/Function0;", "", "secondaryButtonLabel", "isSecondaryButtonVisible", "onSecondaryButtonClick", "(ILjava/util/List;IIIIIZLkotlin/jvm/functions/Function0;IZLkotlin/jvm/functions/Function0;)V", "getContentBottomRes", "()I", "getContentNoteContent", "getContentNoteTitle", "getContentTopRes", "()Z", "getOnPrimaryButtonClick", "()Lkotlin/jvm/functions/Function0;", "getOnSecondaryButtonClick", "getPrimaryButtonLabel", "getPromptRes", "getRespondents", "()Ljava/util/List;", "getSecondaryButtonLabel", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Ready extends org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiState {
        private final int promptRes = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> respondents = null;
        private final int contentTopRes = 0;
        private final int contentBottomRes = 0;
        private final int contentNoteTitle = 0;
        private final int contentNoteContent = 0;
        private final int primaryButtonLabel = 0;
        private final boolean isPrimaryButtonVisible = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onPrimaryButtonClick = null;
        private final int secondaryButtonLabel = 0;
        private final boolean isSecondaryButtonVisible = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onSecondaryButtonClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiState.Ready copy(@androidx.annotation.StringRes()
        int promptRes, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> respondents, @androidx.annotation.StringRes()
        int contentTopRes, @androidx.annotation.StringRes()
        int contentBottomRes, @androidx.annotation.StringRes()
        int contentNoteTitle, @androidx.annotation.StringRes()
        int contentNoteContent, @androidx.annotation.StringRes()
        int primaryButtonLabel, boolean isPrimaryButtonVisible, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onPrimaryButtonClick, @androidx.annotation.StringRes()
        int secondaryButtonLabel, boolean isSecondaryButtonVisible, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onSecondaryButtonClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public Ready(@androidx.annotation.StringRes()
        int promptRes, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> respondents, @androidx.annotation.StringRes()
        int contentTopRes, @androidx.annotation.StringRes()
        int contentBottomRes, @androidx.annotation.StringRes()
        int contentNoteTitle, @androidx.annotation.StringRes()
        int contentNoteContent, @androidx.annotation.StringRes()
        int primaryButtonLabel, boolean isPrimaryButtonVisible, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onPrimaryButtonClick, @androidx.annotation.StringRes()
        int secondaryButtonLabel, boolean isSecondaryButtonVisible, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onSecondaryButtonClick) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPromptRes() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> getRespondents() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getContentTopRes() {
            return 0;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getContentBottomRes() {
            return 0;
        }
        
        public final int component5() {
            return 0;
        }
        
        public final int getContentNoteTitle() {
            return 0;
        }
        
        public final int component6() {
            return 0;
        }
        
        public final int getContentNoteContent() {
            return 0;
        }
        
        public final int component7() {
            return 0;
        }
        
        public final int getPrimaryButtonLabel() {
            return 0;
        }
        
        public final boolean component8() {
            return false;
        }
        
        public final boolean isPrimaryButtonVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnPrimaryButtonClick() {
            return null;
        }
        
        public final int component10() {
            return 0;
        }
        
        public final int getSecondaryButtonLabel() {
            return 0;
        }
        
        public final boolean component11() {
            return false;
        }
        
        public final boolean isSecondaryButtonVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnSecondaryButtonClick() {
            return null;
        }
    }
}