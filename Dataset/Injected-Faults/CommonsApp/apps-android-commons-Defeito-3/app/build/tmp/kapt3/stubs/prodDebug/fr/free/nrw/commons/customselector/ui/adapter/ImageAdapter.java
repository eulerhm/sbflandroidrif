package fr.free.nrw.commons.customselector.ui.adapter;

import java.lang.System;

/**
 * Custom selector ImageAdapter.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00049:;<B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\rJ\b\u0010%\u001a\u00020\rH\u0016J\u0010\u0010&\u001a\u00020\'2\u0006\u0010$\u001a\u00020\rH\u0016J6\u0010(\u001a\u00020!2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u0018\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\rH\u0016J\u0018\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\rH\u0016J\u0018\u00102\u001a\u00020!2\u0006\u0010$\u001a\u00020\r2\u0006\u0010-\u001a\u00020\u0002H\u0002J!\u00103\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J\"\u00105\u001a\u00020!2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u0018\u00106\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\rH\u0002J\u001e\u00107\u001a\u00020!2\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0012j\b\u0012\u0004\u0012\u00020\u000e`\u0013R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0012j\b\u0012\u0004\u0012\u00020\r`\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0012j\b\u0012\u0004\u0012\u00020\u000e`\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006="}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter;", "Lfr/free/nrw/commons/customselector/ui/adapter/RecyclerViewAdapter;", "Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImageViewHolder;", "Lcom/simplecityapps/recyclerview_fastscroll/views/FastScrollRecyclerView$SectionedAdapter;", "context", "Landroid/content/Context;", "imageSelectListener", "Lfr/free/nrw/commons/customselector/listeners/ImageSelectListener;", "imageLoader", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader;", "(Landroid/content/Context;Lfr/free/nrw/commons/customselector/listeners/ImageSelectListener;Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader;)V", "actionableImagesMap", "Ljava/util/TreeMap;", "", "Lfr/free/nrw/commons/customselector/model/Image;", "allImages", "", "alreadyAddedPositions", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "imagePositionAsPerIncreasingOrder", "images", "ioDispatcher", "nextImagePosition", "numberOfSelectedImagesMarkedAsNotForUpload", "reachedEndOfFolder", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "selectedImages", "cleanUp", "", "getImageIdAt", "", "position", "getItemCount", "getSectionName", "", "init", "newImages", "fixedImages", "emptyMap", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onThumbnailClicked", "processThumbnailForActionedImage", "(Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImageViewHolder;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "selectOrRemoveImage", "setSelectedImages", "newSelectedImages", "ImageSelectedOrUpdated", "ImageUnselected", "ImageViewHolder", "ImagesDiffCallback", "app-commons-v4.2.1-master_prodDebug"})
public final class ImageAdapter extends fr.free.nrw.commons.customselector.ui.adapter.RecyclerViewAdapter<fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder> implements com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView.SectionedAdapter {
    
    /**
     * Image select listener for click events on image.
     */
    private fr.free.nrw.commons.customselector.listeners.ImageSelectListener imageSelectListener;
    
    /**
     * ImageLoader queries images.
     */
    private fr.free.nrw.commons.customselector.ui.selector.ImageLoader imageLoader;
    
    /**
     * Determines whether addition of all actionable images is done or not
     */
    private boolean reachedEndOfFolder = false;
    
    /**
     * Currently selected images.
     */
    private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> selectedImages;
    
    /**
     * Number of selected images that are marked as not for upload
     */
    private int numberOfSelectedImagesMarkedAsNotForUpload = 0;
    
    /**
     * List of all images in adapter.
     */
    private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images;
    
    /**
     * Stores all images
     */
    private java.util.List<fr.free.nrw.commons.customselector.model.Image> allImages;
    
    /**
     * Map to store actionable images
     */
    private java.util.TreeMap<java.lang.Integer, fr.free.nrw.commons.customselector.model.Image> actionableImagesMap;
    
    /**
     * Stores already added positions of actionable images
     */
    private java.util.ArrayList<java.lang.Integer> alreadyAddedPositions;
    
    /**
     * Next starting index to initiate query to find next actionable image
     */
    private int nextImagePosition = 0;
    
    /**
     * Helps to maintain the increasing sequence of the position. eg- 0, 1, 2, 3
     */
    private int imagePositionAsPerIncreasingOrder = 0;
    
    /**
     * Coroutine Dispatchers and Scope.
     */
    private kotlinx.coroutines.CoroutineDispatcher defaultDispatcher;
    private kotlinx.coroutines.CoroutineDispatcher ioDispatcher;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public ImageAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.listeners.ImageSelectListener imageSelectListener, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.selector.ImageLoader imageLoader) {
        super(null);
    }
    
    /**
     * Create View holder.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    /**
     * Bind View holder, load image, selected view, click listeners.
     */
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder holder, int position) {
    }
    
    /**
     * Process thumbnail for actioned image
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processThumbnailForActionedImage(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder holder, int position, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Handles click on thumbnail
     */
    private final void onThumbnailClicked(int position, fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder holder) {
    }
    
    /**
     * Handle click event on an image, update counter on images.
     */
    private final void selectOrRemoveImage(fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder holder, int position) {
    }
    
    /**
     * Initialize the data set.
     */
    public final void init(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Image> newImages, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Image> fixedImages, @org.jetbrains.annotations.NotNull
    java.util.TreeMap<java.lang.Integer, fr.free.nrw.commons.customselector.model.Image> emptyMap) {
    }
    
    /**
     * Set new selected images
     */
    public final void setSelectedImages(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> newSelectedImages) {
    }
    
    /**
     * Refresh the data in the adapter
     */
    public final void refresh(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Image> newImages, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Image> fixedImages) {
    }
    
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final long getImageIdAt(int position) {
        return 0L;
    }
    
    /**
     * CleanUp function.
     */
    public final void cleanUp() {
    }
    
    /**
     * Returns the text for showing inside the bubble during bubble scroll.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String getSectionName(int position) {
        return null;
    }
    
    /**
     * ImageSelectedOrUpdated payload class.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImageSelectedOrUpdated;", "", "()V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class ImageSelectedOrUpdated {
        
        public ImageSelectedOrUpdated() {
            super();
        }
    }
    
    /**
     * ImageUnselected payload class.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImageUnselected;", "", "()V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class ImageUnselected {
        
        public ImageUnselected() {
            super();
        }
    }
    
    /**
     * Image view holder.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "image", "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "notForUploadGroup", "Landroidx/constraintlayout/widget/Group;", "selectedGroup", "uploadedGroup", "isItemNotForUpload", "", "isItemUploaded", "itemForUpload", "", "itemNotForUpload", "itemNotUploaded", "itemSelected", "itemUnselected", "itemUploaded", "app-commons-v4.2.1-master_prodDebug"})
    public static final class ImageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView image = null;
        private final androidx.constraintlayout.widget.Group uploadedGroup = null;
        private final androidx.constraintlayout.widget.Group notForUploadGroup = null;
        private final androidx.constraintlayout.widget.Group selectedGroup = null;
        
        public ImageViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getImage() {
            return null;
        }
        
        /**
         * Item selected view.
         */
        public final void itemSelected() {
        }
        
        /**
         * Item Unselected view.
         */
        public final void itemUnselected() {
        }
        
        /**
         * Item Uploaded view.
         */
        public final void itemUploaded() {
        }
        
        /**
         * Item is not for upload view
         */
        public final void itemNotForUpload() {
        }
        
        public final boolean isItemUploaded() {
            return false;
        }
        
        /**
         * Item is not for upload
         */
        public final boolean isItemNotForUpload() {
            return false;
        }
        
        /**
         * Item Not Uploaded view.
         */
        public final void itemNotUploaded() {
        }
        
        /**
         * Item can be uploaded view
         */
        public final void itemForUpload() {
        }
    }
    
    /**
     * DiffUtilCallback.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B5\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016R*\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR*\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImagesDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldImageList", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "newImageList", "(Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getNewImageList", "()Ljava/util/ArrayList;", "setNewImageList", "(Ljava/util/ArrayList;)V", "getOldImageList", "setOldImageList", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "app-commons-v4.2.1-master_prodDebug"})
    public static final class ImagesDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        @org.jetbrains.annotations.NotNull
        private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> oldImageList;
        @org.jetbrains.annotations.NotNull
        private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> newImageList;
        
        public ImagesDiffCallback(@org.jetbrains.annotations.NotNull
        java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> oldImageList, @org.jetbrains.annotations.NotNull
        java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> newImageList) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> getOldImageList() {
            return null;
        }
        
        public final void setOldImageList(@org.jetbrains.annotations.NotNull
        java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> getNewImageList() {
            return null;
        }
        
        public final void setNewImageList(@org.jetbrains.annotations.NotNull
        java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> p0) {
        }
        
        /**
         * Returns the size of the old list.
         */
        @java.lang.Override
        public int getOldListSize() {
            return 0;
        }
        
        /**
         * Returns the size of the new list.
         */
        @java.lang.Override
        public int getNewListSize() {
            return 0;
        }
        
        /**
         * Called by the DiffUtil to decide whether two object represent the same Item.
         */
        @java.lang.Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
        
        /**
         * Called by the DiffUtil when it wants to check whether two items have the same data.
         * DiffUtil uses this information to detect if the contents of an item has changed.
         */
        @java.lang.Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }
}