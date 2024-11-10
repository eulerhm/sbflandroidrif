package fr.free.nrw.commons.db;

import java.lang.System;

/**
 * The database for accessing the respective DAOs
 */
@androidx.room.TypeConverters(value = {fr.free.nrw.commons.db.Converters.class})
@androidx.room.Database(entities = {fr.free.nrw.commons.contributions.Contribution.class, fr.free.nrw.commons.upload.depicts.Depicts.class, fr.free.nrw.commons.customselector.database.UploadedStatus.class, fr.free.nrw.commons.customselector.database.NotForUploadStatus.class, fr.free.nrw.commons.review.ReviewEntity.class}, version = 16, exportSchema = false)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "DepictsDao", "Lfr/free/nrw/commons/upload/depicts/DepictsDao;", "NotForUploadStatusDao", "Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;", "ReviewDao", "Lfr/free/nrw/commons/review/ReviewDao;", "UploadedStatusDao", "Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "contributionDao", "Lfr/free/nrw/commons/contributions/ContributionDao;", "app-commons-v4.2.1-master_prodDebug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract fr.free.nrw.commons.contributions.ContributionDao contributionDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract fr.free.nrw.commons.upload.depicts.DepictsDao DepictsDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract fr.free.nrw.commons.customselector.database.UploadedStatusDao UploadedStatusDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract fr.free.nrw.commons.customselector.database.NotForUploadStatusDao NotForUploadStatusDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract fr.free.nrw.commons.review.ReviewDao ReviewDao();
}