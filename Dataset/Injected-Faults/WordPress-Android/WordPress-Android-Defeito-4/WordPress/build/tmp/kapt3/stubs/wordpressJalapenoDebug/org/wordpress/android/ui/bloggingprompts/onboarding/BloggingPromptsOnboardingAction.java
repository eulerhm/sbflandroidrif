package org.wordpress.android.ui.bloggingprompts.onboarding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "", "()V", "DismissDialog", "DoNothing", "OpenEditor", "OpenRemindersIntro", "OpenSitePicker", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$OpenEditor;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$OpenSitePicker;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$OpenRemindersIntro;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$DismissDialog;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$DoNothing;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BloggingPromptsOnboardingAction {
    
    private BloggingPromptsOnboardingAction() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$OpenEditor;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "promptId", "", "(I)V", "getPromptId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenEditor extends org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction {
        private final int promptId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction.OpenEditor copy(int promptId) {
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
        
        public OpenEditor(int promptId) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPromptId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$OpenSitePicker;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "selectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "getSelectedSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenSitePicker extends org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.SiteModel selectedSite = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction.OpenSitePicker copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel selectedSite) {
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
        
        public OpenSitePicker(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel selectedSite) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.SiteModel getSelectedSite() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$OpenRemindersIntro;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "selectedSiteLocalId", "", "(I)V", "getSelectedSiteLocalId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenRemindersIntro extends org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction {
        private final int selectedSiteLocalId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction.OpenRemindersIntro copy(int selectedSiteLocalId) {
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
        
        public OpenRemindersIntro(int selectedSiteLocalId) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getSelectedSiteLocalId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$DismissDialog;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DismissDialog extends org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction.DismissDialog INSTANCE = null;
        
        private DismissDialog() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction$DoNothing;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DoNothing extends org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction.DoNothing INSTANCE = null;
        
        private DoNothing() {
            super();
        }
    }
}