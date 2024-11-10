package org.wordpress.android.ui.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/main/MainActionListItem;", "", "()V", "actionType", "Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "getActionType", "()Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "ActionType", "AnswerBloggingPromptAction", "CreateAction", "Lorg/wordpress/android/ui/main/MainActionListItem$CreateAction;", "Lorg/wordpress/android/ui/main/MainActionListItem$AnswerBloggingPromptAction;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class MainActionListItem {
    
    private MainActionListItem() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.main.MainActionListItem.ActionType getActionType();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "", "(Ljava/lang/String;I)V", "NO_ACTION", "CREATE_NEW_PAGE", "CREATE_NEW_POST", "CREATE_NEW_STORY", "ANSWER_BLOGGING_PROMPT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ActionType {
        /*public static final*/ NO_ACTION /* = new NO_ACTION() */,
        /*public static final*/ CREATE_NEW_PAGE /* = new CREATE_NEW_PAGE() */,
        /*public static final*/ CREATE_NEW_POST /* = new CREATE_NEW_POST() */,
        /*public static final*/ CREATE_NEW_STORY /* = new CREATE_NEW_STORY() */,
        /*public static final*/ ANSWER_BLOGGING_PROMPT /* = new ANSWER_BLOGGING_PROMPT() */;
        
        ActionType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BP\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012#\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J&\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\rH\u00c6\u0003JX\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052%\b\u0002\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R.\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/main/MainActionListItem$CreateAction;", "Lorg/wordpress/android/ui/main/MainActionListItem;", "actionType", "Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "iconRes", "", "labelRes", "onClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "showQuickStartFocusPoint", "", "(Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;IILkotlin/jvm/functions/Function1;Z)V", "getActionType", "()Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "getIconRes", "()I", "getLabelRes", "getOnClickAction", "()Lkotlin/jvm/functions/Function1;", "getShowQuickStartFocusPoint", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CreateAction extends org.wordpress.android.ui.main.MainActionListItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.main.MainActionListItem.ActionType actionType = null;
        private final int iconRes = 0;
        private final int labelRes = 0;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.main.MainActionListItem.ActionType, kotlin.Unit> onClickAction = null;
        private final boolean showQuickStartFocusPoint = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.main.MainActionListItem.CreateAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.MainActionListItem.ActionType actionType, @androidx.annotation.DrawableRes()
        int iconRes, @androidx.annotation.StringRes()
        int labelRes, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.main.MainActionListItem.ActionType, kotlin.Unit> onClickAction, boolean showQuickStartFocusPoint) {
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
        
        public CreateAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.MainActionListItem.ActionType actionType, @androidx.annotation.DrawableRes()
        int iconRes, @androidx.annotation.StringRes()
        int labelRes, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.main.MainActionListItem.ActionType, kotlin.Unit> onClickAction, boolean showQuickStartFocusPoint) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.main.MainActionListItem.ActionType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.main.MainActionListItem.ActionType getActionType() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getIconRes() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getLabelRes() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.main.MainActionListItem.ActionType, kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.main.MainActionListItem.ActionType, kotlin.Unit> getOnClickAction() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getShowQuickStartFocusPoint() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bb\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012#\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012\u00a2\u0006\u0002\u0010\u0013J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\tH\u00c6\u0003J\t\u0010%\u001a\u00020\u000bH\u00c6\u0003J&\u0010&\u001a\u001f\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rH\u00c6\u0003J\u0011\u0010\'\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012H\u00c6\u0003Jt\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2%\b\u0002\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012H\u00c6\u0001J\u0013\u0010)\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010+H\u00d6\u0003J\t\u0010,\u001a\u00020\tH\u00d6\u0001J\t\u0010-\u001a\u00020.H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0018R.\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/main/MainActionListItem$AnswerBloggingPromptAction;", "Lorg/wordpress/android/ui/main/MainActionListItem;", "actionType", "Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "promptTitle", "Lorg/wordpress/android/ui/utils/UiString;", "isAnswered", "", "promptId", "", "attribution", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptAttribution;", "onClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "onHelpAction", "Lkotlin/Function0;", "(Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;Lorg/wordpress/android/ui/utils/UiString;ZILorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptAttribution;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "getActionType", "()Lorg/wordpress/android/ui/main/MainActionListItem$ActionType;", "getAttribution", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptAttribution;", "()Z", "getOnClickAction", "()Lkotlin/jvm/functions/Function1;", "getOnHelpAction", "()Lkotlin/jvm/functions/Function0;", "getPromptId", "()I", "getPromptTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class AnswerBloggingPromptAction extends org.wordpress.android.ui.main.MainActionListItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.main.MainActionListItem.ActionType actionType = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString promptTitle = null;
        private final boolean isAnswered = false;
        private final int promptId = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution attribution = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onClickAction = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onHelpAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.main.MainActionListItem.AnswerBloggingPromptAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.MainActionListItem.ActionType actionType, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString promptTitle, boolean isAnswered, int promptId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution attribution, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onClickAction, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onHelpAction) {
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
        
        public AnswerBloggingPromptAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.MainActionListItem.ActionType actionType, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString promptTitle, boolean isAnswered, int promptId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution attribution, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onClickAction, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onHelpAction) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.main.MainActionListItem.ActionType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.main.MainActionListItem.ActionType getActionType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getPromptTitle() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isAnswered() {
            return false;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getPromptId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution getAttribution() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnClickAction() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnHelpAction() {
            return null;
        }
    }
}