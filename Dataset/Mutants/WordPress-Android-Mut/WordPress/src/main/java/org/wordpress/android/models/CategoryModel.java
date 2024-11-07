package org.wordpress.android.models;

import android.content.ContentValues;
import android.database.Cursor;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents WordPress post Category data and handles local database (de) serialization.
 */
public class CategoryModel {

    // Categories table column names
    public static final String ID_COLUMN_NAME = "ID";

    public static final String NAME_COLUMN_NAME = "name";

    public static final String SLUG_COLUMN_NAME = "slug";

    public static final String DESC_COLUMN_NAME = "description";

    public static final String PARENT_ID_COLUMN_NAME = "parent";

    public static final String POST_COUNT_COLUMN_NAME = "post_count";

    public int id;

    public String name;

    public String slug;

    public String description;

    public int parentId;

    public int postCount;

    public boolean isInLocalTable;

    public CategoryModel() {
        if (!ListenerUtil.mutListener.listen(1335)) {
            id = -1;
        }
        if (!ListenerUtil.mutListener.listen(1336)) {
            name = "";
        }
        if (!ListenerUtil.mutListener.listen(1337)) {
            slug = "";
        }
        if (!ListenerUtil.mutListener.listen(1338)) {
            description = "";
        }
        if (!ListenerUtil.mutListener.listen(1339)) {
            parentId = -1;
        }
        if (!ListenerUtil.mutListener.listen(1340)) {
            postCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(1341)) {
            isInLocalTable = false;
        }
    }

    /**
     * Sets data from a local database {@link Cursor}.
     */
    public void deserializeFromDatabase(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(1342)) {
            if (cursor == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1343)) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN_NAME));
        }
        if (!ListenerUtil.mutListener.listen(1344)) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(NAME_COLUMN_NAME));
        }
        if (!ListenerUtil.mutListener.listen(1345)) {
            slug = cursor.getString(cursor.getColumnIndexOrThrow(SLUG_COLUMN_NAME));
        }
        if (!ListenerUtil.mutListener.listen(1346)) {
            description = cursor.getString(cursor.getColumnIndexOrThrow(DESC_COLUMN_NAME));
        }
        if (!ListenerUtil.mutListener.listen(1347)) {
            parentId = cursor.getInt(cursor.getColumnIndexOrThrow(PARENT_ID_COLUMN_NAME));
        }
        if (!ListenerUtil.mutListener.listen(1348)) {
            postCount = cursor.getInt(cursor.getColumnIndexOrThrow(POST_COUNT_COLUMN_NAME));
        }
        if (!ListenerUtil.mutListener.listen(1349)) {
            isInLocalTable = true;
        }
    }

    /**
     * Creates the {@link ContentValues} object to store this category data in a local database.
     */
    public ContentValues serializeToDatabase() {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(1350)) {
            values.put(ID_COLUMN_NAME, id);
        }
        if (!ListenerUtil.mutListener.listen(1351)) {
            values.put(NAME_COLUMN_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(1352)) {
            values.put(SLUG_COLUMN_NAME, slug);
        }
        if (!ListenerUtil.mutListener.listen(1353)) {
            values.put(DESC_COLUMN_NAME, description);
        }
        if (!ListenerUtil.mutListener.listen(1354)) {
            values.put(PARENT_ID_COLUMN_NAME, parentId);
        }
        if (!ListenerUtil.mutListener.listen(1355)) {
            values.put(POST_COUNT_COLUMN_NAME, postCount);
        }
        return values;
    }
}
