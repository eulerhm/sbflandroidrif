package org.wordpress.android.datasets;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0002J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/datasets/ReaderDiscoverCardsTable;", "", "()V", "CARDS_JSON_COLUMN", "", "DISCOVER_CARDS_TABLE", "addCardsPage", "", "cardsJson", "clear", "createTable", "db", "Landroid/database/sqlite/SQLiteDatabase;", "dropTables", "getReadableDb", "getWritableDb", "loadDiscoverCardsJsons", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverCardsTable {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.datasets.ReaderDiscoverCardsTable INSTANCE = null;
    private static final java.lang.String DISCOVER_CARDS_TABLE = "tbl_discover_cards";
    private static final java.lang.String CARDS_JSON_COLUMN = "cards_json";
    
    private ReaderDiscoverCardsTable() {
        super();
    }
    
    public final void createTable(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase db) {
    }
    
    public final void dropTables(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase db) {
    }
    
    public final void clear() {
    }
    
    private final android.database.sqlite.SQLiteDatabase getReadableDb() {
        return null;
    }
    
    private final android.database.sqlite.SQLiteDatabase getWritableDb() {
        return null;
    }
    
    public final void addCardsPage(@org.jetbrains.annotations.NotNull()
    java.lang.String cardsJson) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> loadDiscoverCardsJsons() {
        return null;
    }
}