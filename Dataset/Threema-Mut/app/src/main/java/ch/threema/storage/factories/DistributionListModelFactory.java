/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.storage.factories;

import android.content.ContentValues;
import net.sqlcipher.Cursor;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.services.DistributionListService;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.DistributionListModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListModelFactory extends ModelFactory {

    public DistributionListModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, DistributionListModel.TABLE);
    }

    public List<DistributionListModel> getAll() {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    public DistributionListModel getById(int id) {
        return getFirst(DistributionListModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    private List<DistributionListModel> convert(QueryBuilder queryBuilder, String orderBy) {
        if (!ListenerUtil.mutListener.listen(70166)) {
            queryBuilder.setTables(this.getTableName());
        }
        return convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, null, null, null, orderBy));
    }

    private List<DistributionListModel> convertList(Cursor c) {
        List<DistributionListModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70170)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70169)) {
                        {
                            long _loopCounter913 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter913", ++_loopCounter913);
                                if (!ListenerUtil.mutListener.listen(70168)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70167)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    private DistributionListModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70179)) {
            if ((ListenerUtil.mutListener.listen(70176) ? (cursor != null || (ListenerUtil.mutListener.listen(70175) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70174) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70173) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70172) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70171) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70175) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70174) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70173) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70172) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70171) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final DistributionListModel c = new DistributionListModel();
                if (!ListenerUtil.mutListener.listen(70178)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70177)) {
                                c.setId(cursorHelper.getInt(DistributionListModel.COLUMN_ID)).setName(cursorHelper.getString(DistributionListModel.COLUMN_NAME)).setCreatedAt(cursorHelper.getDateByString(DistributionListModel.COLUMN_CREATED_AT)).setArchived(cursorHelper.getBoolean(DistributionListModel.COLUMN_IS_ARCHIVED));
                            }
                            return false;
                        }
                    });
                }
                return c;
            }
        }
        return null;
    }

    public boolean createOrUpdate(DistributionListModel distributionListModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70188)) {
            if ((ListenerUtil.mutListener.listen(70184) ? (distributionListModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70183) ? (distributionListModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70182) ? (distributionListModel.getId() < 0) : (ListenerUtil.mutListener.listen(70181) ? (distributionListModel.getId() != 0) : (ListenerUtil.mutListener.listen(70180) ? (distributionListModel.getId() == 0) : (distributionListModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, DistributionListModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70187)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70186)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70185)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(distributionListModel);
        } else {
            return update(distributionListModel);
        }
    }

    private ContentValues buildContentValues(DistributionListModel distributionListModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70189)) {
            contentValues.put(DistributionListModel.COLUMN_NAME, distributionListModel.getName());
        }
        if (!ListenerUtil.mutListener.listen(70190)) {
            contentValues.put(DistributionListModel.COLUMN_CREATED_AT, distributionListModel.getCreatedAt() != null ? CursorHelper.dateAsStringFormat.get().format(distributionListModel.getCreatedAt()) : null);
        }
        if (!ListenerUtil.mutListener.listen(70191)) {
            contentValues.put(DistributionListModel.COLUMN_IS_ARCHIVED, distributionListModel.isArchived());
        }
        return contentValues;
    }

    public boolean create(DistributionListModel distributionListModel) {
        ContentValues contentValues = buildContentValues(distributionListModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70198)) {
            if ((ListenerUtil.mutListener.listen(70196) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70195) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70194) ? (newId < 0) : (ListenerUtil.mutListener.listen(70193) ? (newId != 0) : (ListenerUtil.mutListener.listen(70192) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70197)) {
                    distributionListModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(DistributionListModel distributionListModel) {
        ContentValues contentValues = buildContentValues(distributionListModel);
        if (!ListenerUtil.mutListener.listen(70199)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, DistributionListModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListModel.getId()) });
        }
        return true;
    }

    public int delete(DistributionListModel distributionListModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), DistributionListModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListModel.getId()) });
    }

    private DistributionListModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70202)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70201)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70200)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public List<DistributionListModel> filter(DistributionListService.DistributionListFilter filter) {
        QueryBuilder queryBuilder = new QueryBuilder();
        // sort by id!
        String orderBy = null;
        if (!ListenerUtil.mutListener.listen(70205)) {
            if (filter != null) {
                if (!ListenerUtil.mutListener.listen(70204)) {
                    if (!filter.sortingByDate()) {
                        if (!ListenerUtil.mutListener.listen(70203)) {
                            orderBy = DistributionListModel.COLUMN_CREATED_AT + " " + (filter.sortingAscending() ? "ASC" : "DESC");
                        }
                    }
                }
            }
        }
        return convert(queryBuilder, orderBy);
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `distribution_list` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `name` VARCHAR , `createdAt` VARCHAR, `isArchived` TINYINT DEFAULT 0 );" };
    }
}
