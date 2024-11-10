package org.wordpress.android;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J,\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/WPWellSqlConfig;", "Lorg/wordpress/android/fluxc/persistence/WellSqlConfig;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getCursorWindowSize", "", "onDowngrade", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "helper", "Lcom/yarolegovich/wellsql/WellTableManager;", "oldVersion", "", "newVersion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPWellSqlConfig extends org.wordpress.android.fluxc.persistence.WellSqlConfig {
    
    public WPWellSqlConfig(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    /**
     * Detect when the database is downgraded in debug builds, and if the build flag is set recreate all the tables
     * and show a toast alerting to the downgrade. The sole purpose of this is to avoid the hassle of devs switching
     * branches and having to clear storage and login again due to a version downgrade.
     */
    @java.lang.Override()
    public void onDowngrade(@org.jetbrains.annotations.Nullable()
    android.database.sqlite.SQLiteDatabase db, @org.jetbrains.annotations.Nullable()
    com.yarolegovich.wellsql.WellTableManager helper, int oldVersion, int newVersion) {
    }
    
    /**
     * Increase the cursor window size to 20MB for devices running API 28 and above. This should reduce the
     * number of SQLiteBlobTooBigExceptions. Note that this is only called on API 28 and
     * above since earlier versions don't allow adjusting the cursor window size.
     */
    @java.lang.Override()
    public long getCursorWindowSize() {
        return 0L;
    }
}