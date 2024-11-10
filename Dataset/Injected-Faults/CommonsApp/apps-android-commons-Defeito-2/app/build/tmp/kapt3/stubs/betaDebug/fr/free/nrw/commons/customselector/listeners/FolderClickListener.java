package fr.free.nrw.commons.customselector.listeners;

import java.lang.System;

/**
 * Custom Selector Folder Click Listener
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H&\u00a8\u0006\t"}, d2 = {"Lfr/free/nrw/commons/customselector/listeners/FolderClickListener;", "", "onFolderClick", "", "folderId", "", "folderName", "", "lastItemId", "app-commons-v4.2.1-main_betaDebug"})
public abstract interface FolderClickListener {
    
    /**
     * onFolderClick
     * @param folderId : folder id of the folder.
     * @param folderName : folder name of the folder.
     * @param lastItemId : last scroll position in the folder.
     */
    public abstract void onFolderClick(long folderId, @org.jetbrains.annotations.NotNull
    java.lang.String folderName, long lastItemId);
}