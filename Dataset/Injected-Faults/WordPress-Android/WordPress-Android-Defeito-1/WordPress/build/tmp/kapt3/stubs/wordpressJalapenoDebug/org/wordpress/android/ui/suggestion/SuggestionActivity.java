package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\u001c\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0002J\b\u0010 \u001a\u00020\u0011H\u0016J\u0012\u0010!\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0007J\b\u0010\'\u001a\u00020\u0011H\u0014J\b\u0010(\u001a\u00020\u0011H\u0014J\b\u0010)\u001a\u00020\u0011H\u0002J\f\u0010*\u001a\u00020\u0011*\u00020+H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "binding", "Lorg/wordpress/android/databinding/SuggestUsersActivityBinding;", "siteId", "", "Ljava/lang/Long;", "suggestionAdapter", "Lorg/wordpress/android/ui/suggestion/adapters/SuggestionAdapter;", "viewModel", "Lorg/wordpress/android/ui/suggestion/SuggestionViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/suggestion/SuggestionViewModel;", "setViewModel", "(Lorg/wordpress/android/ui/suggestion/SuggestionViewModel;)V", "abortDueToMissingIntentExtra", "", "key", "", "exitIfOnlyOneMatchingUser", "finish", "finishWithValue", "value", "withSuggestion", "", "initializeActivity", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "suggestionType", "Lorg/wordpress/android/ui/suggestion/SuggestionType;", "initializeSuggestionAdapter", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onEventMainThread", "event", "Lorg/wordpress/android/networking/ConnectionChangeReceiver$ConnectionChangeEvent;", "onPause", "onResume", "updateEmptyView", "showDropdownOnTouch", "Lorg/wordpress/android/widgets/SuggestionAutoCompleteText;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SuggestionActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    private org.wordpress.android.ui.suggestion.adapters.SuggestionAdapter suggestionAdapter;
    private java.lang.Long siteId;
    @javax.inject.Inject()
    public org.wordpress.android.ui.suggestion.SuggestionViewModel viewModel;
    private org.wordpress.android.databinding.SuggestUsersActivityBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.suggestion.SuggestionActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SELECTED_VALUE = "SELECTED_VALUE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_SUGGESTION_TYPE = "INTENT_KEY_SUGGESTION_TYPE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_SITE_MODEL = "INTENT_KEY_SITE_MODEL";
    
    public SuggestionActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.suggestion.SuggestionViewModel getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.suggestion.SuggestionViewModel p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void abortDueToMissingIntentExtra(java.lang.String key) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void initializeActivity(org.wordpress.android.fluxc.model.SiteModel siteModel, org.wordpress.android.ui.suggestion.SuggestionType suggestionType) {
    }
    
    private final void exitIfOnlyOneMatchingUser() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void showDropdownOnTouch(org.wordpress.android.widgets.SuggestionAutoCompleteText $this$showDropdownOnTouch) {
    }
    
    @java.lang.Override()
    public void finish() {
    }
    
    private final void finishWithValue(java.lang.String value, boolean withSuggestion) {
    }
    
    private final void initializeSuggestionAdapter() {
    }
    
    private final void updateEmptyView() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.ConnectionChangeReceiver.ConnectionChangeEvent event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionActivity$Companion;", "", "()V", "INTENT_KEY_SITE_MODEL", "", "INTENT_KEY_SUGGESTION_TYPE", "SELECTED_VALUE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}