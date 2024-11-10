package fr.free.nrw.commons.upload.worker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00be\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0002hiB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0002J\b\u0010I\u001a\u00020JH\u0002J\b\u0010K\u001a\u00020LH\u0002J\u0011\u0010M\u001a\u00020NH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010OJ\u0010\u0010P\u001a\u00020\n2\u0006\u0010Q\u001a\u00020\nH\u0002J\u0011\u0010R\u001a\u00020JH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010OJ\u0012\u0010S\u001a\u0004\u0018\u00010\u001a2\u0006\u0010T\u001a\u00020\nH\u0002J\u0018\u0010U\u001a\u00020V2\u000e\u0010W\u001a\n\u0012\u0006\b\u0001\u0012\u00020Y0XH\u0002J\b\u0010Z\u001a\u00020[H\u0002J!\u0010\\\u001a\u00020F2\u0006\u0010]\u001a\u00020^2\u0006\u0010G\u001a\u00020HH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010_J\u0010\u0010`\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0002J\u0018\u0010a\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010]\u001a\u00020^H\u0002J\u0010\u0010b\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010c\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0003J\u0010\u0010d\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010e\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0003J\u0019\u0010f\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0083@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010gR\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R2\u0010\u000b\u001a&\u0012\f\u0012\n \r*\u0004\u0018\u00010\n0\n \r*\u0012\u0012\f\u0012\n \r*\u0004\u0018\u00010\n0\n\u0018\u00010\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010+\u001a\u00020,8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\b02X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u00103\u001a\u0002048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001e\u00109\u001a\u00020:8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001e\u0010?\u001a\u00020@8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006j"}, d2 = {"Lfr/free/nrw/commons/upload/worker/UploadWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "PROCESSING_UPLOADS_NOTIFICATION_ID", "", "PROCESSING_UPLOADS_NOTIFICATION_TAG", "", "STASH_ERROR_CODES", "", "kotlin.jvm.PlatformType", "", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "contributionDao", "Lfr/free/nrw/commons/contributions/ContributionDao;", "getContributionDao", "()Lfr/free/nrw/commons/contributions/ContributionDao;", "setContributionDao", "(Lfr/free/nrw/commons/contributions/ContributionDao;)V", "curentNotification", "Landroidx/core/app/NotificationCompat$Builder;", "currentNotificationID", "currentNotificationTag", "fileUtilsWrapper", "Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "getFileUtilsWrapper", "()Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "setFileUtilsWrapper", "(Lfr/free/nrw/commons/upload/FileUtilsWrapper;)V", "mediaClient", "Lfr/free/nrw/commons/media/MediaClient;", "getMediaClient", "()Lfr/free/nrw/commons/media/MediaClient;", "setMediaClient", "(Lfr/free/nrw/commons/media/MediaClient;)V", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "sessionManager", "Lfr/free/nrw/commons/auth/SessionManager;", "getSessionManager", "()Lfr/free/nrw/commons/auth/SessionManager;", "setSessionManager", "(Lfr/free/nrw/commons/auth/SessionManager;)V", "statesToProcess", "Ljava/util/ArrayList;", "uploadClient", "Lfr/free/nrw/commons/upload/UploadClient;", "getUploadClient", "()Lfr/free/nrw/commons/upload/UploadClient;", "setUploadClient", "(Lfr/free/nrw/commons/upload/UploadClient;)V", "uploadedStatusDao", "Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "getUploadedStatusDao", "()Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "setUploadedStatusDao", "(Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;)V", "wikidataEditService", "Lfr/free/nrw/commons/wikidata/WikidataEditService;", "getWikidataEditService", "()Lfr/free/nrw/commons/wikidata/WikidataEditService;", "setWikidataEditService", "(Lfr/free/nrw/commons/wikidata/WikidataEditService;)V", "clearChunks", "", "contribution", "Lfr/free/nrw/commons/contributions/Contribution;", "createForegroundInfo", "Landroidx/work/ForegroundInfo;", "createNotificationForForegroundService", "Landroid/app/Notification;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findUniqueFileName", "fileName", "getForegroundInfo", "getNotificationBuilder", "channelId", "getPendingIntent", "Landroid/app/PendingIntent;", "toClass", "Ljava/lang/Class;", "Lfr/free/nrw/commons/theme/BaseActivity;", "isLimitedConnectionModeEnabled", "", "makeWikiDataEdit", "uploadResult", "Lfr/free/nrw/commons/upload/UploadResult;", "(Lfr/free/nrw/commons/upload/UploadResult;Lfr/free/nrw/commons/contributions/Contribution;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeUploadFromInMemoryHashSet", "saveCompletedContribution", "saveIntoUploadedStatus", "showFailedNotification", "showPausedNotification", "showSuccessNotification", "uploadContribution", "(Lfr/free/nrw/commons/contributions/Contribution;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Module", "NotificationUpdateProgressListener", "app-commons-v4.2.1-main_betaDebug"})
public final class UploadWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull
    private android.content.Context appContext;
    private androidx.core.app.NotificationManagerCompat notificationManager;
    @javax.inject.Inject
    public fr.free.nrw.commons.wikidata.WikidataEditService wikidataEditService;
    @javax.inject.Inject
    public fr.free.nrw.commons.auth.SessionManager sessionManager;
    @javax.inject.Inject
    public fr.free.nrw.commons.contributions.ContributionDao contributionDao;
    @javax.inject.Inject
    public fr.free.nrw.commons.customselector.database.UploadedStatusDao uploadedStatusDao;
    @javax.inject.Inject
    public fr.free.nrw.commons.upload.UploadClient uploadClient;
    @javax.inject.Inject
    public fr.free.nrw.commons.media.MediaClient mediaClient;
    @javax.inject.Inject
    public fr.free.nrw.commons.upload.FileUtilsWrapper fileUtilsWrapper;
    private final java.lang.String PROCESSING_UPLOADS_NOTIFICATION_TAG = "androidx.multidex : upload_tag";
    private final int PROCESSING_UPLOADS_NOTIFICATION_ID = 101;
    private int currentNotificationID = -1;
    private java.lang.String currentNotificationTag;
    private androidx.core.app.NotificationCompat.Builder curentNotification;
    private final java.util.ArrayList<java.lang.Integer> statesToProcess = null;
    private final java.util.List<java.lang.String> STASH_ERROR_CODES = null;
    
    public UploadWorker(@org.jetbrains.annotations.NotNull
    android.content.Context appContext, @org.jetbrains.annotations.NotNull
    androidx.work.WorkerParameters workerParams) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getAppContext() {
        return null;
    }
    
    public final void setAppContext(@org.jetbrains.annotations.NotNull
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.wikidata.WikidataEditService getWikidataEditService() {
        return null;
    }
    
    public final void setWikidataEditService(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.wikidata.WikidataEditService p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.auth.SessionManager getSessionManager() {
        return null;
    }
    
    public final void setSessionManager(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.SessionManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.contributions.ContributionDao getContributionDao() {
        return null;
    }
    
    public final void setContributionDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.ContributionDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.database.UploadedStatusDao getUploadedStatusDao() {
        return null;
    }
    
    public final void setUploadedStatusDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatusDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.UploadClient getUploadClient() {
        return null;
    }
    
    public final void setUploadClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.UploadClient p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.media.MediaClient getMediaClient() {
        return null;
    }
    
    public final void setMediaClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaClient p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.FileUtilsWrapper getFileUtilsWrapper() {
        return null;
    }
    
    public final void setFileUtilsWrapper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileUtilsWrapper p0) {
    }
    
    private final androidx.core.app.NotificationCompat.Builder getNotificationBuilder(java.lang.String channelId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    /**
     * Removes the processed contribution from the cancelledUploads in-memory hashset
     */
    private final void removeUploadFromInMemoryHashSet(fr.free.nrw.commons.contributions.Contribution contribution) {
    }
    
    /**
     * Create new notification for foreground service
     */
    private final androidx.work.ForegroundInfo createForegroundInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getForegroundInfo(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super androidx.work.ForegroundInfo> continuation) {
        return null;
    }
    
    private final android.app.Notification createNotificationForForegroundService() {
        return null;
    }
    
    /**
     * Returns true is the limited connection mode is enabled
     */
    private final boolean isLimitedConnectionModeEnabled() {
        return false;
    }
    
    /**
     * Upload the contribution
     * @param contribution
     */
    @android.annotation.SuppressLint(value = {"StringFormatInvalid"})
    private final java.lang.Object uploadContribution(fr.free.nrw.commons.contributions.Contribution contribution, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void clearChunks(fr.free.nrw.commons.contributions.Contribution contribution) {
    }
    
    /**
     * Make the WikiData Edit, if applicable
     */
    private final java.lang.Object makeWikiDataEdit(fr.free.nrw.commons.upload.UploadResult uploadResult, fr.free.nrw.commons.contributions.Contribution contribution, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void saveCompletedContribution(fr.free.nrw.commons.contributions.Contribution contribution, fr.free.nrw.commons.upload.UploadResult uploadResult) {
    }
    
    /**
     * Save to uploadedStatusDao.
     */
    private final void saveIntoUploadedStatus(fr.free.nrw.commons.contributions.Contribution contribution) {
    }
    
    private final java.lang.String findUniqueFileName(java.lang.String fileName) {
        return null;
    }
    
    /**
     * Notify that the current upload has succeeded
     * @param contribution
     */
    @android.annotation.SuppressLint(value = {"StringFormatInvalid"})
    private final void showSuccessNotification(fr.free.nrw.commons.contributions.Contribution contribution) {
    }
    
    /**
     * Notify that the current upload has failed
     * @param contribution
     */
    @android.annotation.SuppressLint(value = {"StringFormatInvalid"})
    private final void showFailedNotification(fr.free.nrw.commons.contributions.Contribution contribution) {
    }
    
    /**
     * Notify that the current upload is paused
     * @param contribution
     */
    private final void showPausedNotification(fr.free.nrw.commons.contributions.Contribution contribution) {
    }
    
    /**
     * Method used to get Pending intent for opening different screen after clicking on notification
     * @param toClass
     */
    private final android.app.PendingIntent getPendingIntent(java.lang.Class<? extends fr.free.nrw.commons.theme.BaseActivity> toClass) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'\u00a8\u0006\u0004"}, d2 = {"Lfr/free/nrw/commons/upload/worker/UploadWorker$Module;", "", "worker", "Lfr/free/nrw/commons/upload/worker/UploadWorker;", "app-commons-v4.2.1-main_betaDebug"})
    @dagger.Module
    public static abstract interface Module {
        
        @org.jetbrains.annotations.NotNull
        @dagger.android.ContributesAndroidInjector
        public abstract fr.free.nrw.commons.upload.worker.UploadWorker worker();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lfr/free/nrw/commons/upload/worker/UploadWorker$NotificationUpdateProgressListener;", "", "notificationFinishingTitle", "", "contribution", "Lfr/free/nrw/commons/contributions/Contribution;", "(Lfr/free/nrw/commons/upload/worker/UploadWorker;Ljava/lang/String;Lfr/free/nrw/commons/contributions/Contribution;)V", "getContribution", "()Lfr/free/nrw/commons/contributions/Contribution;", "setContribution", "(Lfr/free/nrw/commons/contributions/Contribution;)V", "onChunkUploaded", "", "chunkInfo", "Lfr/free/nrw/commons/contributions/ChunkInfo;", "onProgress", "transferred", "", "total", "app-commons-v4.2.1-main_betaDebug"})
    public class NotificationUpdateProgressListener {
        private java.lang.String notificationFinishingTitle;
        @org.jetbrains.annotations.Nullable
        private fr.free.nrw.commons.contributions.Contribution contribution;
        
        public NotificationUpdateProgressListener(@org.jetbrains.annotations.Nullable
        java.lang.String notificationFinishingTitle, @org.jetbrains.annotations.Nullable
        fr.free.nrw.commons.contributions.Contribution contribution) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final fr.free.nrw.commons.contributions.Contribution getContribution() {
            return null;
        }
        
        public final void setContribution(@org.jetbrains.annotations.Nullable
        fr.free.nrw.commons.contributions.Contribution p0) {
        }
        
        public final void onProgress(long transferred, long total) {
        }
        
        public void onChunkUploaded(@org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.contributions.Contribution contribution, @org.jetbrains.annotations.Nullable
        fr.free.nrw.commons.contributions.ChunkInfo chunkInfo) {
        }
    }
}