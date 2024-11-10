package fr.free.nrw.commons.media;

import java.lang.System;

/**
 * Activity for helping to view an image in full-screen mode with some other features
 * like zoom, and swap gestures
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001dB\u0005\u00a2\u0006\u0002\u0010\u0002J,\u0010F\u001a\u00020,2\u001a\u0010G\u001a\u0016\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cj\n\u0012\u0004\u0012\u00020\u001d\u0018\u0001`\u001e2\u0006\u0010H\u001a\u00020\u001dH\u0002J\u0019\u0010I\u001a\u00020,2\u0006\u0010J\u001a\u00020,H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010KJ\u0019\u0010L\u001a\u00020,2\u0006\u0010J\u001a\u00020,H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010KJ\u0010\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0002J\u0012\u0010Q\u001a\u00020N2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0019\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020\u001dH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010TJ\u0010\u0010U\u001a\u00020N2\u0006\u0010V\u001a\u00020,H\u0002J\b\u0010W\u001a\u00020NH\u0002J\b\u0010X\u001a\u00020NH\u0016J\u0012\u0010Y\u001a\u00020N2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0014J\b\u0010\\\u001a\u00020NH\u0014J\b\u0010]\u001a\u00020NH\u0002J\u0010\u0010^\u001a\u00020N2\u0006\u0010_\u001a\u00020;H\u0002J\u0010\u0010`\u001a\u00020N2\u0006\u0010_\u001a\u00020;H\u0002J\b\u0010a\u001a\u00020NH\u0002J\b\u0010b\u001a\u00020NH\u0002J\b\u0010c\u001a\u00020NH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\"\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cj\n\u0012\u0004\u0012\u00020\u001d\u0018\u0001`\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u0014\u0010)\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u00101\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00102\u001a\u00020,X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00107\u001a\u0004\u0018\u0001088\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\"\u00109\u001a\u0016\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cj\n\u0012\u0004\u0012\u00020\u001d\u0018\u0001`\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010<\u001a\u0004\u0018\u00010=8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010>\u001a\u00020?8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u000e\u0010D\u001a\u00020EX\u0082.\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006e"}, d2 = {"Lfr/free/nrw/commons/media/ZoomableActivity;", "Lfr/free/nrw/commons/theme/BaseActivity;", "()V", "bucketId", "", "customSelectorViewModelFactory", "Lfr/free/nrw/commons/customselector/ui/selector/CustomSelectorViewModelFactory;", "getCustomSelectorViewModelFactory", "()Lfr/free/nrw/commons/customselector/ui/selector/CustomSelectorViewModelFactory;", "setCustomSelectorViewModelFactory", "(Lfr/free/nrw/commons/customselector/ui/selector/CustomSelectorViewModelFactory;)V", "defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "fileProcessor", "Lfr/free/nrw/commons/upload/FileProcessor;", "getFileProcessor", "()Lfr/free/nrw/commons/upload/FileProcessor;", "setFileProcessor", "(Lfr/free/nrw/commons/upload/FileProcessor;)V", "fileUtilsWrapper", "Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "getFileUtilsWrapper", "()Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "setFileUtilsWrapper", "(Lfr/free/nrw/commons/upload/FileUtilsWrapper;)V", "imageUri", "Landroid/net/Uri;", "images", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "ioDispatcher", "loadingListener", "Lcom/facebook/drawee/controller/ControllerListener;", "Lcom/facebook/imagepipeline/image/ImageInfo;", "notForUploadStatusDao", "Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;", "getNotForUploadStatusDao", "()Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;", "setNotForUploadStatusDao", "(Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;)V", "photo", "Lfr/free/nrw/commons/media/zoomControllers/zoomable/ZoomableDraweeView;", "photoBackgroundColor", "", "getPhotoBackgroundColor", "()Ljava/lang/Integer;", "setPhotoBackgroundColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "position", "prefs", "Landroid/content/SharedPreferences;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "selectedCount", "Landroid/widget/TextView;", "selectedImages", "shouldRefresh", "", "spinner", "Landroid/widget/ProgressBar;", "uploadedStatusDao", "Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "getUploadedStatusDao", "()Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "setUploadedStatusDao", "(Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;)V", "viewModel", "Lfr/free/nrw/commons/customselector/ui/selector/CustomSelectorViewModel;", "getImagePosition", "list", "image", "getNextActionableImage", "index", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPreviousActionableImage", "handleResult", "", "result", "Lfr/free/nrw/commons/customselector/model/Result;", "init", "insertInNotForUpload", "it", "(Lfr/free/nrw/commons/customselector/model/Image;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "itemSelected", "i", "itemUnselected", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDownSwiped", "onLeftSwiped", "showAlreadyActionedImages", "onRightSwiped", "onSwipe", "onUpSwiped", "showWelcomeDialog", "ZoomableActivityConstants", "app-commons-v4.2.1-main_betaDebug"})
public final class ZoomableActivity extends fr.free.nrw.commons.theme.BaseActivity {
    private android.net.Uri imageUri;
    
    /**
     * View model.
     */
    private fr.free.nrw.commons.customselector.ui.selector.CustomSelectorViewModel viewModel;
    
    /**
     * Pref for saving states.
     */
    private android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmField
    @butterknife.BindView(value = fr.free.nrw.commons.R.id.zoomable)
    public fr.free.nrw.commons.media.zoomControllers.zoomable.ZoomableDraweeView photo;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer photoBackgroundColor;
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmField
    @butterknife.BindView(value = fr.free.nrw.commons.R.id.zoom_progress_bar)
    public android.widget.ProgressBar spinner;
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmField
    @butterknife.BindView(value = fr.free.nrw.commons.R.id.selection_count)
    public android.widget.TextView selectedCount;
    
    /**
     * Total images present in folder
     */
    private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images;
    
    /**
     * Total selected images present in folder
     */
    private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> selectedImages;
    
    /**
     * Present position of the image
     */
    private int position = 0;
    
    /**
     * Present bucket ID
     */
    private long bucketId = 0L;
    
    /**
     * Determines whether the adapter should refresh
     */
    private boolean shouldRefresh = false;
    
    /**
     * FileUtilsWrapper class to get imageSHA1 from uri
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.upload.FileUtilsWrapper fileUtilsWrapper;
    
    /**
     * FileProcessor to pre-process the file.
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.upload.FileProcessor fileProcessor;
    
    /**
     * NotForUploadStatus Dao class for database operations
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.customselector.database.NotForUploadStatusDao notForUploadStatusDao;
    
    /**
     * UploadedStatus Dao class for database operations
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.customselector.database.UploadedStatusDao uploadedStatusDao;
    
    /**
     * View Model Factory.
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.customselector.ui.selector.CustomSelectorViewModelFactory customSelectorViewModelFactory;
    
    /**
     * Coroutine Dispatchers and Scope.
     */
    private kotlinx.coroutines.CoroutineDispatcher defaultDispatcher;
    private kotlinx.coroutines.CoroutineDispatcher ioDispatcher;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    /**
     * Two types of loading indicators have been added to the zoom activity:
     * 1.  An Indeterminate spinner for showing the time lapsed between dispatch of the image request
     * and starting to receiving the image.
     * 2.  ProgressBarDrawable that reflects how much image has been downloaded
     */
    private final com.facebook.drawee.controller.ControllerListener<com.facebook.imagepipeline.image.ImageInfo> loadingListener = null;
    private java.util.HashMap _$_findViewCache;
    
    public ZoomableActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getPhotoBackgroundColor() {
        return null;
    }
    
    public final void setPhotoBackgroundColor(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.FileUtilsWrapper getFileUtilsWrapper() {
        return null;
    }
    
    public final void setFileUtilsWrapper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.FileProcessor getFileProcessor() {
        return null;
    }
    
    public final void setFileProcessor(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileProcessor p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.database.NotForUploadStatusDao getNotForUploadStatusDao() {
        return null;
    }
    
    public final void setNotForUploadStatusDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.NotForUploadStatusDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.database.UploadedStatusDao getUploadedStatusDao() {
        return null;
    }
    
    public final void setUploadedStatusDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatusDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.ui.selector.CustomSelectorViewModelFactory getCustomSelectorViewModelFactory() {
        return null;
    }
    
    public final void setCustomSelectorViewModelFactory(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.selector.CustomSelectorViewModelFactory p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Show Full Screen Mode Welcome Dialog.
     */
    private final void showWelcomeDialog() {
    }
    
    /**
     * Handle view model result.
     */
    private final void handleResult(fr.free.nrw.commons.customselector.model.Result result) {
    }
    
    /**
     * Handle swap gestures. Ex. onSwipeLeft, onSwipeRight, onSwipeUp, onSwipeDown
     */
    private final void onSwipe() {
    }
    
    /**
     * Handles down swipe action
     */
    private final void onDownSwiped() {
    }
    
    /**
     * Handles up swipe action
     */
    private final void onUpSwiped() {
    }
    
    /**
     * Handles right swipe action
     */
    private final void onRightSwiped(boolean showAlreadyActionedImages) {
    }
    
    /**
     * Handles left swipe action
     */
    private final void onLeftSwiped(boolean showAlreadyActionedImages) {
    }
    
    /**
     * Gets next actionable image.
     * Iterates from an index to the end of the folder and check whether the current image is
     * present in already uploaded table or in not for upload table,
     * and returns the first actionable image it can find.
     */
    private final java.lang.Object getNextActionableImage(int index, kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    /**
     * Gets previous actionable image.
     * Iterates from an index to the first image of the folder and check whether the current image
     * is present in already uploaded table or in not for upload table,
     * and returns the first actionable image it can find
     */
    private final java.lang.Object getPreviousActionableImage(int index, kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    /**
     * Unselect item UI
     */
    private final void itemUnselected() {
    }
    
    /**
     * Select item UI
     */
    private final void itemSelected(int i) {
    }
    
    /**
     * Get position of an image from list
     */
    private final int getImagePosition(java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> list, fr.free.nrw.commons.customselector.model.Image image) {
        return 0;
    }
    
    private final void init(android.net.Uri imageUri) {
    }
    
    /**
     * Inserts an image in Not For Upload table
     */
    private final java.lang.Object insertInNotForUpload(fr.free.nrw.commons.customselector.model.Image it, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Send selected images in fragment
     */
    @java.lang.Override
    public void onBackPressed() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lfr/free/nrw/commons/media/ZoomableActivity$ZoomableActivityConstants;", "", "()V", "ORIGIN", "", "PHOTO_BACKGROUND_COLOR", "app-commons-v4.2.1-main_betaDebug"})
    public static final class ZoomableActivityConstants {
        @org.jetbrains.annotations.NotNull
        public static final fr.free.nrw.commons.media.ZoomableActivity.ZoomableActivityConstants INSTANCE = null;
        
        /**
         * Key for Accessing Intent Data Named "Origin", The value indicates what fragment
         * ZoomableActivity was created by. It is null if ZoomableActivity was created by
         * the custom picker.
         */
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String ORIGIN = "Origin";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String PHOTO_BACKGROUND_COLOR = "photo_background_color";
        
        private ZoomableActivityConstants() {
            super();
        }
    }
}