package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\t\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018B\u000f\b\u0004\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0004\u001a\u00028\u0000X\u0084\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\t\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "(Landroidx/viewbinding/ViewBinding;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "drawEmphasizedText", "", "Landroid/widget/TextView;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "text", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;", "CaptionViewHolder", "DayButtonsViewHolder", "HighEmphasisTextViewHolder", "IllustrationViewHolder", "MediumEmphasisTextViewHolder", "PromptSwitchViewHolder", "TimeViewHolder", "TipViewHolder", "TitleViewHolder", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$IllustrationViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$TitleViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$HighEmphasisTextViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$MediumEmphasisTextViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$CaptionViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$DayButtonsViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$TipViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$TimeViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$PromptSwitchViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BloggingRemindersViewHolder<T extends androidx.viewbinding.ViewBinding> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final T binding = null;
    
    private BloggingRemindersViewHolder(T binding) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final T getBinding() {
        return null;
    }
    
    public final void drawEmphasizedText(@org.jetbrains.annotations.NotNull()
    android.widget.TextView $this$drawEmphasizedText, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$IllustrationViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersIllustrationBinding;", "parentView", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Illustration;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class IllustrationViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersIllustrationBinding> {
        
        public IllustrationViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Illustration item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$TitleViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersTitleBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Title;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TitleViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersTitleBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public TitleViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Title item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$HighEmphasisTextViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersTextHighEmphasisBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$HighEmphasisText;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HighEmphasisTextViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersTextHighEmphasisBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public HighEmphasisTextViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.HighEmphasisText item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$MediumEmphasisTextViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersTextMediumEmphasisBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$MediumEmphasisText;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MediumEmphasisTextViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersTextMediumEmphasisBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public MediumEmphasisTextViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.MediumEmphasisText item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$CaptionViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersCaptionBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Caption;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CaptionViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersCaptionBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public CaptionViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Caption item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0014\u0010\u000e\u001a\u00020\t*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$DayButtonsViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersDayButtonsBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$DayButtons;", "payload", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersDiffCallback$DayButtonsPayload;", "initDay", "Landroid/widget/TextView;", "dayItem", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$DayButtons$DayItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DayButtonsViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersDayButtonsBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public DayButtonsViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons item, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersDiffCallback.DayButtonsPayload payload) {
        }
        
        private final void initDay(android.widget.TextView $this$initDay, org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem dayItem) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$TipViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersTipBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Tip;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TipViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersTipBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public TipViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Tip item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$TimeViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersTimeBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$TimeItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TimeViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersTimeBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public TimeViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.TimeItem item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder$PromptSwitchViewHolder;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewHolder;", "Lorg/wordpress/android/databinding/BloggingRemindersPromptSwitchBinding;", "parentView", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$PromptSwitch;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PromptSwitchViewHolder extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewHolder<org.wordpress.android.databinding.BloggingRemindersPromptSwitchBinding> {
        
        public PromptSwitchViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.PromptSwitch item) {
        }
    }
}