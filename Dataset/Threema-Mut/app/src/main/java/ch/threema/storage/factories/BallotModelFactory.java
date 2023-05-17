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
import androidx.annotation.Nullable;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import ch.threema.storage.models.ballot.GroupBallotModel;
import ch.threema.storage.models.ballot.IdentityBallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotModelFactory extends ModelFactory {

    public BallotModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, BallotModel.TABLE);
    }

    public List<BallotModel> getAll() {
        return convertList(this.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    @Nullable
    public BallotModel getById(int id) {
        return getFirst(BallotModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    public List<BallotModel> convert(QueryBuilder queryBuilder, String[] args, String orderBy) {
        if (!ListenerUtil.mutListener.listen(69868)) {
            queryBuilder.setTables(this.getTableName());
        }
        return convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, args, null, null, orderBy));
    }

    protected List<BallotModel> convertList(Cursor c) {
        List<BallotModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(69872)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(69871)) {
                        {
                            long _loopCounter902 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter902", ++_loopCounter902);
                                if (!ListenerUtil.mutListener.listen(69870)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(69869)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    protected BallotModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(69889)) {
            if ((ListenerUtil.mutListener.listen(69878) ? (cursor != null || (ListenerUtil.mutListener.listen(69877) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(69876) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(69875) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(69874) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(69873) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(69877) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(69876) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(69875) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(69874) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(69873) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final BallotModel c = new BallotModel();
                if (!ListenerUtil.mutListener.listen(69888)) {
                    // convert default
                    new CursorHelper(cursor, this.getColumnIndexCache()).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(69879)) {
                                c.setId(cursorHelper.getInt(BallotModel.COLUMN_ID)).setApiBallotId(cursorHelper.getString(BallotModel.COLUMN_API_BALLOT_ID)).setCreatorIdentity(cursorHelper.getString(BallotModel.COLUMN_CREATOR_IDENTITY)).setName(cursorHelper.getString(BallotModel.COLUMN_NAME)).setCreatedAt(cursorHelper.getDate(BallotModel.COLUMN_CREATED_AT)).setModifiedAt(cursorHelper.getDate(BallotModel.COLUMN_MODIFIED_AT)).setLastViewedAt(cursorHelper.getDate(BallotModel.COLUMN_LAST_VIEWED_AT));
                            }
                            String stateString = cursorHelper.getString(BallotModel.COLUMN_STATE);
                            if (!ListenerUtil.mutListener.listen(69881)) {
                                if (!TestUtil.empty(stateString)) {
                                    if (!ListenerUtil.mutListener.listen(69880)) {
                                        c.setState(BallotModel.State.valueOf(stateString));
                                    }
                                }
                            }
                            String assessment = cursorHelper.getString(BallotModel.COLUMN_ASSESSMENT);
                            if (!ListenerUtil.mutListener.listen(69883)) {
                                if (!TestUtil.empty(assessment)) {
                                    if (!ListenerUtil.mutListener.listen(69882)) {
                                        c.setAssessment(BallotModel.Assessment.valueOf(assessment));
                                    }
                                }
                            }
                            String type = cursorHelper.getString(BallotModel.COLUMN_TYPE);
                            if (!ListenerUtil.mutListener.listen(69885)) {
                                if (!TestUtil.empty(type)) {
                                    if (!ListenerUtil.mutListener.listen(69884)) {
                                        c.setType(BallotModel.Type.valueOf(type));
                                    }
                                }
                            }
                            String choiceType = cursorHelper.getString(BallotModel.COLUMN_CHOICE_TYPE);
                            if (!ListenerUtil.mutListener.listen(69887)) {
                                if (!TestUtil.empty(choiceType)) {
                                    if (!ListenerUtil.mutListener.listen(69886)) {
                                        c.setChoiceType(BallotModel.ChoiceType.valueOf(choiceType));
                                    }
                                }
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

    public boolean createOrUpdate(BallotModel ballotModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(69898)) {
            if ((ListenerUtil.mutListener.listen(69894) ? (ballotModel.getId() >= 0) : (ListenerUtil.mutListener.listen(69893) ? (ballotModel.getId() <= 0) : (ListenerUtil.mutListener.listen(69892) ? (ballotModel.getId() < 0) : (ListenerUtil.mutListener.listen(69891) ? (ballotModel.getId() != 0) : (ListenerUtil.mutListener.listen(69890) ? (ballotModel.getId() == 0) : (ballotModel.getId() > 0))))))) {
                Cursor cursor = this.getReadableDatabase().query(this.getTableName(), null, BallotModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(69897)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(69896)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(69895)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(ballotModel);
        } else {
            return update(ballotModel);
        }
    }

    protected ContentValues buildContentValues(BallotModel ballotModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(69899)) {
            contentValues.put(BallotModel.COLUMN_API_BALLOT_ID, ballotModel.getApiBallotId());
        }
        if (!ListenerUtil.mutListener.listen(69900)) {
            contentValues.put(BallotModel.COLUMN_CREATOR_IDENTITY, ballotModel.getCreatorIdentity());
        }
        if (!ListenerUtil.mutListener.listen(69901)) {
            contentValues.put(BallotModel.COLUMN_NAME, ballotModel.getName());
        }
        if (!ListenerUtil.mutListener.listen(69902)) {
            contentValues.put(BallotModel.COLUMN_STATE, ballotModel.getState() != null ? ballotModel.getState().toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(69903)) {
            contentValues.put(BallotModel.COLUMN_ASSESSMENT, ballotModel.getAssessment() != null ? ballotModel.getAssessment().toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(69904)) {
            contentValues.put(BallotModel.COLUMN_TYPE, ballotModel.getType() != null ? ballotModel.getType().toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(69905)) {
            contentValues.put(BallotModel.COLUMN_CHOICE_TYPE, ballotModel.getChoiceType() != null ? ballotModel.getChoiceType().toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(69906)) {
            contentValues.put(BallotModel.COLUMN_CREATED_AT, ballotModel.getCreatedAt() != null ? ballotModel.getCreatedAt().getTime() : null);
        }
        if (!ListenerUtil.mutListener.listen(69907)) {
            contentValues.put(BallotModel.COLUMN_MODIFIED_AT, ballotModel.getModifiedAt() != null ? ballotModel.getModifiedAt().getTime() : null);
        }
        if (!ListenerUtil.mutListener.listen(69908)) {
            contentValues.put(BallotModel.COLUMN_LAST_VIEWED_AT, ballotModel.getLastViewedAt() != null ? ballotModel.getLastViewedAt().getTime() : null);
        }
        return contentValues;
    }

    public boolean create(BallotModel ballotModel) {
        ContentValues contentValues = buildContentValues(ballotModel);
        long newId = this.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(69915)) {
            if ((ListenerUtil.mutListener.listen(69913) ? (newId >= 0) : (ListenerUtil.mutListener.listen(69912) ? (newId <= 0) : (ListenerUtil.mutListener.listen(69911) ? (newId < 0) : (ListenerUtil.mutListener.listen(69910) ? (newId != 0) : (ListenerUtil.mutListener.listen(69909) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(69914)) {
                    ballotModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(BallotModel ballotModel) {
        ContentValues contentValues = buildContentValues(ballotModel);
        if (!ListenerUtil.mutListener.listen(69916)) {
            this.getWritableDatabase().update(this.getTableName(), contentValues, BallotModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotModel.getId()) });
        }
        return true;
    }

    public int delete(BallotModel ballotModel) {
        return this.getWritableDatabase().delete(this.getTableName(), BallotModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotModel.getId()) });
    }

    @Nullable
    protected BallotModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(69919)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(69918)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(69917)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public long count(BallotService.BallotFilter filter) {
        Cursor resultCursor = this.runBallotFilterQuery(filter, "SELECT COUNT(*)");
        if (!ListenerUtil.mutListener.listen(69920)) {
            if (resultCursor != null) {
                return DatabaseUtil.count(resultCursor);
            }
        }
        return 0L;
    }

    public List<BallotModel> filter(BallotService.BallotFilter filter) {
        Cursor resultCursor = this.runBallotFilterQuery(filter, "SELECT DISTINCT b.*");
        if (!ListenerUtil.mutListener.listen(69921)) {
            if (resultCursor != null) {
                return this.convertList(resultCursor);
            }
        }
        return new ArrayList<>();
    }

    public BallotModel getByApiBallotIdAndIdentity(String apiBallotId, String groupCreator) {
        return getFirst(BallotModel.COLUMN_API_BALLOT_ID + "=? " + "AND " + BallotModel.COLUMN_CREATOR_IDENTITY + "=?", new String[] { apiBallotId, groupCreator });
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `ballot` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `apiBallotId` VARCHAR NOT NULL , `creatorIdentity` VARCHAR NOT NULL , `name` VARCHAR , `state` VARCHAR NOT NULL , `assessment` VARCHAR NOT NULL , `type` VARCHAR NOT NULL , `choiceType` VARCHAR NOT NULL , `createdAt` BIGINT NOT NULL , `modifiedAt` BIGINT NOT NULL , `lastViewedAt` BIGINT )", // indices
        "CREATE UNIQUE INDEX `apiBallotIdAndCreator` ON `ballot` ( `apiBallotId`, `creatorIdentity` )" };
    }

    protected Cursor runBallotFilterQuery(BallotService.BallotFilter filter, String select) {
        String query = select + " FROM " + this.getTableName() + " b";
        List<String> args = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(69956)) {
            if (filter != null) {
                MessageReceiver receiver = filter.getReceiver();
                if (!ListenerUtil.mutListener.listen(69925)) {
                    if (receiver != null) {
                        String linkTable;
                        String linkField;
                        String linkFieldReceiver;
                        String linkValue;
                        switch(receiver.getType()) {
                            case MessageReceiver.Type_GROUP:
                                linkTable = GroupBallotModel.TABLE;
                                linkField = GroupBallotModel.COLUMN_BALLOT_ID;
                                linkFieldReceiver = GroupBallotModel.COLUMN_GROUP_ID;
                                linkValue = String.valueOf(((GroupMessageReceiver) receiver).getGroup().getId());
                                break;
                            case MessageReceiver.Type_CONTACT:
                                linkTable = IdentityBallotModel.TABLE;
                                linkField = IdentityBallotModel.COLUMN_BALLOT_ID;
                                linkFieldReceiver = IdentityBallotModel.COLUMN_IDENTITY;
                                linkValue = ((ContactMessageReceiver) receiver).getContact().getIdentity();
                                break;
                            default:
                                // do not run a ballot query
                                return null;
                        }
                        if (!ListenerUtil.mutListener.listen(69924)) {
                            if (linkTable != null) {
                                if (!ListenerUtil.mutListener.listen(69922)) {
                                    query += " INNER JOIN " + linkTable + " l" + " ON l." + linkField + " = b." + BallotModel.COLUMN_ID + " AND l." + linkFieldReceiver + " = ?";
                                }
                                if (!ListenerUtil.mutListener.listen(69923)) {
                                    args.add(linkValue);
                                }
                            }
                        }
                    }
                }
                // Build where statement
                List<String> where = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(69935)) {
                    if ((ListenerUtil.mutListener.listen(69931) ? (filter.getStates() != null || (ListenerUtil.mutListener.listen(69930) ? (filter.getStates().length >= 0) : (ListenerUtil.mutListener.listen(69929) ? (filter.getStates().length <= 0) : (ListenerUtil.mutListener.listen(69928) ? (filter.getStates().length < 0) : (ListenerUtil.mutListener.listen(69927) ? (filter.getStates().length != 0) : (ListenerUtil.mutListener.listen(69926) ? (filter.getStates().length == 0) : (filter.getStates().length > 0))))))) : (filter.getStates() != null && (ListenerUtil.mutListener.listen(69930) ? (filter.getStates().length >= 0) : (ListenerUtil.mutListener.listen(69929) ? (filter.getStates().length <= 0) : (ListenerUtil.mutListener.listen(69928) ? (filter.getStates().length < 0) : (ListenerUtil.mutListener.listen(69927) ? (filter.getStates().length != 0) : (ListenerUtil.mutListener.listen(69926) ? (filter.getStates().length == 0) : (filter.getStates().length > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(69932)) {
                            where.add("b." + BallotModel.COLUMN_STATE + " IN (" + DatabaseUtil.makePlaceholders(filter.getStates().length) + ")");
                        }
                        if (!ListenerUtil.mutListener.listen(69934)) {
                            {
                                long _loopCounter903 = 0;
                                for (BallotModel.State f : filter.getStates()) {
                                    ListenerUtil.loopListener.listen("_loopCounter903", ++_loopCounter903);
                                    if (!ListenerUtil.mutListener.listen(69933)) {
                                        args.add(f.toString());
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69939)) {
                    if (filter.createdOrNotVotedByIdentity() != null) {
                        if (!ListenerUtil.mutListener.listen(69936)) {
                            // Created by the identity OR no votes from the identity
                            where.add("b." + BallotModel.COLUMN_CREATOR_IDENTITY + " = ? OR NOT EXISTS (" + "SELECT sv." + BallotVoteModel.COLUMN_BALLOT_ID + " FROM " + BallotVoteModel.TABLE + " sv" + " WHERE sv." + BallotVoteModel.COLUMN_VOTING_IDENTITY + " = ? AND sv." + BallotVoteModel.COLUMN_BALLOT_ID + " = b." + BallotModel.COLUMN_ID + ")");
                        }
                        if (!ListenerUtil.mutListener.listen(69937)) {
                            args.add(filter.createdOrNotVotedByIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(69938)) {
                            args.add(filter.createdOrNotVotedByIdentity());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69954)) {
                    if ((ListenerUtil.mutListener.listen(69944) ? (where.size() >= 0) : (ListenerUtil.mutListener.listen(69943) ? (where.size() <= 0) : (ListenerUtil.mutListener.listen(69942) ? (where.size() < 0) : (ListenerUtil.mutListener.listen(69941) ? (where.size() != 0) : (ListenerUtil.mutListener.listen(69940) ? (where.size() == 0) : (where.size() > 0))))))) {
                        String whereStatement = "";
                        if (!ListenerUtil.mutListener.listen(69952)) {
                            {
                                long _loopCounter904 = 0;
                                for (String s : where) {
                                    ListenerUtil.loopListener.listen("_loopCounter904", ++_loopCounter904);
                                    if (!ListenerUtil.mutListener.listen(69950)) {
                                        whereStatement += ((ListenerUtil.mutListener.listen(69949) ? (whereStatement.length() >= 0) : (ListenerUtil.mutListener.listen(69948) ? (whereStatement.length() <= 0) : (ListenerUtil.mutListener.listen(69947) ? (whereStatement.length() < 0) : (ListenerUtil.mutListener.listen(69946) ? (whereStatement.length() != 0) : (ListenerUtil.mutListener.listen(69945) ? (whereStatement.length() == 0) : (whereStatement.length() > 0)))))) ? ") AND (" : "");
                                    }
                                    if (!ListenerUtil.mutListener.listen(69951)) {
                                        whereStatement += s;
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(69953)) {
                            query += " WHERE (" + whereStatement + ")";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69955)) {
                    query += " ORDER BY b." + BallotModel.COLUMN_CREATED_AT + " DESC";
                }
            }
        }
        return this.getReadableDatabase().rawQuery(query, DatabaseUtil.convertArguments(args));
    }
}
