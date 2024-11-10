package fr.free.nrw.commons.description;

import java.lang.System;

/**
 * Activity for populating and editing existing description and caption
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0016J$\u0010\u001d\u001a\u00020\u001c2\u001a\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020 \u0018\u00010\u001fj\n\u0012\u0004\u0012\u00020 \u0018\u0001`!H\u0002J\"\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\u0010\u0010\'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020)H\u0002J\u0012\u0010*\u001a\u00020\u001c2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020)H\u0002J\u0018\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0005H\u0002J\b\u00104\u001a\u00020\u001cH\u0002J\u0018\u00105\u001a\u00020\u001c2\u000e\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 07H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u00068"}, d2 = {"Lfr/free/nrw/commons/description/DescriptionEditActivity;", "Lfr/free/nrw/commons/theme/BaseActivity;", "Lfr/free/nrw/commons/upload/UploadMediaDetailAdapter$EventListener;", "()V", "REQUEST_CODE_FOR_VOICE_INPUT", "", "binding", "Lfr/free/nrw/commons/databinding/ActivityDescriptionEditBinding;", "progressDialog", "Landroid/app/ProgressDialog;", "recentLanguagesDao", "Lfr/free/nrw/commons/recentlanguages/RecentLanguagesDao;", "getRecentLanguagesDao", "()Lfr/free/nrw/commons/recentlanguages/RecentLanguagesDao;", "setRecentLanguagesDao", "(Lfr/free/nrw/commons/recentlanguages/RecentLanguagesDao;)V", "rvDescriptions", "Landroidx/recyclerview/widget/RecyclerView;", "savedLanguageValue", "", "uploadMediaDetailAdapter", "Lfr/free/nrw/commons/upload/UploadMediaDetailAdapter;", "wikiText", "getWikiText", "()Ljava/lang/String;", "setWikiText", "(Ljava/lang/String;)V", "addLanguage", "", "initRecyclerView", "descriptionAndCaptions", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/upload/UploadMediaDetail;", "Lkotlin/collections/ArrayList;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackButtonClicked", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPrimaryCaptionTextChange", "isNotEmpty", "", "onSubmitButtonClicked", "showInfoAlert", "titleStringID", "messageStringId", "showLoggingProgressBar", "updateDescription", "uploadMediaDetails", "", "app-commons-v4.2.1-master_prodDebug"})
public final class DescriptionEditActivity extends fr.free.nrw.commons.theme.BaseActivity implements fr.free.nrw.commons.upload.UploadMediaDetailAdapter.EventListener {
    
    /**
     * Adapter for showing UploadMediaDetail in the activity
     */
    private fr.free.nrw.commons.upload.UploadMediaDetailAdapter uploadMediaDetailAdapter;
    
    /**
     * Recyclerview for recycling data in views
     */
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmField
    public androidx.recyclerview.widget.RecyclerView rvDescriptions;
    
    /**
     * Current wikitext
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String wikiText;
    
    /**
     * Saved language
     */
    private java.lang.String savedLanguageValue;
    
    /**
     * For showing progress dialog
     */
    private android.app.ProgressDialog progressDialog;
    @javax.inject.Inject
    public fr.free.nrw.commons.recentlanguages.RecentLanguagesDao recentLanguagesDao;
    private fr.free.nrw.commons.databinding.ActivityDescriptionEditBinding binding;
    private final int REQUEST_CODE_FOR_VOICE_INPUT = 1213;
    private java.util.HashMap _$_findViewCache;
    
    public DescriptionEditActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getWikiText() {
        return null;
    }
    
    public final void setWikiText(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.recentlanguages.RecentLanguagesDao getRecentLanguagesDao() {
        return null;
    }
    
    public final void setRecentLanguagesDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.recentlanguages.RecentLanguagesDao p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Initializes the RecyclerView
     * @param descriptionAndCaptions list of description and caption
     */
    private final void initRecyclerView(java.util.ArrayList<fr.free.nrw.commons.upload.UploadMediaDetail> descriptionAndCaptions) {
    }
    
    /**
     * show dialog with info
     * @param titleStringID Title ID
     * @param messageStringId Message ID
     */
    private final void showInfoAlert(int titleStringID, int messageStringId) {
    }
    
    @java.lang.Override
    public void onPrimaryCaptionTextChange(boolean isNotEmpty) {
    }
    
    /**
     * Adds new language item to RecyclerView
     */
    @java.lang.Override
    public void addLanguage() {
    }
    
    private final void onBackButtonClicked(android.view.View view) {
    }
    
    private final void onSubmitButtonClicked(android.view.View view) {
    }
    
    /**
     * Updates newly added descriptions in the wikiText and send to calling fragment
     * @param uploadMediaDetails descriptions and captions
     */
    private final void updateDescription(java.util.List<fr.free.nrw.commons.upload.UploadMediaDetail> uploadMediaDetails) {
    }
    
    private final void showLoggingProgressBar() {
    }
    
    @java.lang.Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
}