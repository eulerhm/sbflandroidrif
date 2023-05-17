/**
 * ************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki.sync;

import android.database.Cursor;
import android.database.SQLException;
import android.util.Pair;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.anki.exception.UnknownHttpResponseException;
import com.ichi2.async.Connection;
import com.ichi2.libanki.DB;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.sched.AbstractSched;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.sched.Counts;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import okhttp3.Response;
import timber.log.Timber;
import static com.ichi2.libanki.sync.Syncer.ConnectionResultType.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ // tracking HTTP transport change in github already
"deprecation", "PMD.ExcessiveClassLength", "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.ExcessiveMethodLength", "PMD.SwitchStmtsShouldHaveDefault", "PMD.EmptyIfStmt", "PMD.SingularField" })
public class Syncer {

    // Mapping of column type names to Cursor types for API < 11
    public static final int TYPE_NULL = 0;

    public static final int TYPE_INTEGER = 1;

    public static final int TYPE_FLOAT = 2;

    public static final int TYPE_STRING = 3;

    public static final int TYPE_BLOB = 4;

    /**
     * The libAnki value of `sched.mReportLimit`
     */
    private static final int SYNC_SCHEDULER_REPORT_LIMIT = 1000;

    private final Collection mCol;

    private final RemoteServer mRemoteServer;

    // private long mRScm;
    private int mMaxUsn;

    private final HostNum mHostNum;

    // private long mLScm;
    private int mMinUsn;

    private boolean mLNewer;

    private String mSyncMsg;

    private LinkedList<String> mTablesLeft;

    private Cursor mCursor;

    public Syncer(Collection col, RemoteServer server, HostNum hostNum) {
        mCol = col;
        mRemoteServer = server;
        mHostNum = hostNum;
    }

    public enum ConnectionResultType {

        BAD_AUTH("badAuth"),
        NO_CHANGES("noChanges"),
        CLOCK_OFF("clockOff"),
        FULL_SYNC("fullSync"),
        DB_ERROR("dbError"),
        BASIC_CHECK_FAILED("basicCheckFailed"),
        OVERWRITE_ERROR("overwriteError"),
        REMOTE_DB_ERROR("remoteDbError"),
        SD_ACCESS_ERROR("sdAccessError"),
        FINISH_ERROR("finishError"),
        IO_EXCEPTION("IOException"),
        GENERIC_ERROR("genericError"),
        OUT_OF_MEMORY_ERROR("outOfMemoryError"),
        SANITY_CHECK_ERROR("sanityCheckError"),
        SERVER_ABORT("serverAbort"),
        MEDIA_SYNC_SERVER_ERROR("mediaSyncServerError"),
        CUSTOM_SYNC_SERVER_URL("customSyncServerUrl"),
        USER_ABORTED_SYNC("userAbortedSync"),
        SUCCESS("success"),
        // arbitrary error message received from sync
        ARBITRARY_STRING("arbitraryString"),
        MEDIA_SANITY_FAILED("sanityFailed"),
        CORRUPT("corrupt"),
        OK("OK"),
        // The next three ones are the only that can be returned during login
        UPGRADE_REQUIRED("upgradeRequired"),
        CONNECTION_ERROR("connectionError"),
        ERROR("error");

        private final String mMessage;

        ConnectionResultType(String mMessage) {
            this.mMessage = mMessage;
        }

        public String toString() {
            return mMessage;
        }
    }

    public Pair<ConnectionResultType, Object> sync(Connection con) throws UnknownHttpResponseException {
        if (!ListenerUtil.mutListener.listen(20085)) {
            mSyncMsg = "";
        }
        if (!ListenerUtil.mutListener.listen(20086)) {
            // if the deck has any pending changes, flush them first and bump mod time
            mCol.getSched()._updateCutoff();
        }
        if (!ListenerUtil.mutListener.listen(20087)) {
            mCol.save();
        }
        // step 1: login & metadata
        Response ret = mRemoteServer.meta();
        if (!ListenerUtil.mutListener.listen(20088)) {
            if (ret == null) {
                return null;
            }
        }
        int returntype = ret.code();
        if (!ListenerUtil.mutListener.listen(20094)) {
            if ((ListenerUtil.mutListener.listen(20093) ? (returntype >= 403) : (ListenerUtil.mutListener.listen(20092) ? (returntype <= 403) : (ListenerUtil.mutListener.listen(20091) ? (returntype > 403) : (ListenerUtil.mutListener.listen(20090) ? (returntype < 403) : (ListenerUtil.mutListener.listen(20089) ? (returntype != 403) : (returntype == 403))))))) {
                return new Pair<>(BAD_AUTH, null);
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(20097)) {
                mCol.getDb().getDatabase().beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(20099)) {
                    Timber.i("Sync: getting meta data from server");
                }
                JSONObject rMeta = new JSONObject(ret.body().string());
                if (!ListenerUtil.mutListener.listen(20100)) {
                    mCol.log("rmeta", rMeta);
                }
                if (!ListenerUtil.mutListener.listen(20101)) {
                    mSyncMsg = rMeta.getString("msg");
                }
                if (!ListenerUtil.mutListener.listen(20102)) {
                    if (!rMeta.getBoolean("cont")) {
                        // Don't add syncMsg; it can be fetched by UI code using the accessor
                        return new Pair<>(SERVER_ABORT, null);
                    } else {
                    }
                }
                if (!ListenerUtil.mutListener.listen(20103)) {
                    throwExceptionIfCancelled(con);
                }
                long rscm = rMeta.getLong("scm");
                int rts = rMeta.getInt("ts");
                long rMod = rMeta.getLong("mod");
                if (!ListenerUtil.mutListener.listen(20104)) {
                    mMaxUsn = rMeta.getInt("usn");
                }
                if (!ListenerUtil.mutListener.listen(20105)) {
                    // skip uname, AnkiDroid already stores and shows it
                    trySetHostNum(rMeta);
                }
                if (!ListenerUtil.mutListener.listen(20106)) {
                    Timber.i("Sync: building local meta data");
                }
                JSONObject lMeta = meta();
                if (!ListenerUtil.mutListener.listen(20107)) {
                    mCol.log("lmeta", lMeta);
                }
                long lMod = lMeta.getLong("mod");
                if (!ListenerUtil.mutListener.listen(20108)) {
                    mMinUsn = lMeta.getInt("usn");
                }
                long lscm = lMeta.getLong("scm");
                int lts = lMeta.getInt("ts");
                long diff = Math.abs((ListenerUtil.mutListener.listen(20112) ? (rts % lts) : (ListenerUtil.mutListener.listen(20111) ? (rts / lts) : (ListenerUtil.mutListener.listen(20110) ? (rts * lts) : (ListenerUtil.mutListener.listen(20109) ? (rts + lts) : (rts - lts))))));
                if (!ListenerUtil.mutListener.listen(20119)) {
                    if ((ListenerUtil.mutListener.listen(20117) ? (diff >= 300) : (ListenerUtil.mutListener.listen(20116) ? (diff <= 300) : (ListenerUtil.mutListener.listen(20115) ? (diff < 300) : (ListenerUtil.mutListener.listen(20114) ? (diff != 300) : (ListenerUtil.mutListener.listen(20113) ? (diff == 300) : (diff > 300))))))) {
                        if (!ListenerUtil.mutListener.listen(20118)) {
                            mCol.log("clock off");
                        }
                        return new Pair<>(CLOCK_OFF, new Object[] { diff });
                    }
                }
                if (!ListenerUtil.mutListener.listen(20134)) {
                    if ((ListenerUtil.mutListener.listen(20124) ? (lMod >= rMod) : (ListenerUtil.mutListener.listen(20123) ? (lMod <= rMod) : (ListenerUtil.mutListener.listen(20122) ? (lMod > rMod) : (ListenerUtil.mutListener.listen(20121) ? (lMod < rMod) : (ListenerUtil.mutListener.listen(20120) ? (lMod != rMod) : (lMod == rMod))))))) {
                        if (!ListenerUtil.mutListener.listen(20132)) {
                            Timber.i("Sync: no changes - returning");
                        }
                        if (!ListenerUtil.mutListener.listen(20133)) {
                            mCol.log("no changes");
                        }
                        return new Pair<>(NO_CHANGES, null);
                    } else if ((ListenerUtil.mutListener.listen(20129) ? (lscm >= rscm) : (ListenerUtil.mutListener.listen(20128) ? (lscm <= rscm) : (ListenerUtil.mutListener.listen(20127) ? (lscm > rscm) : (ListenerUtil.mutListener.listen(20126) ? (lscm < rscm) : (ListenerUtil.mutListener.listen(20125) ? (lscm == rscm) : (lscm != rscm))))))) {
                        if (!ListenerUtil.mutListener.listen(20130)) {
                            Timber.i("Sync: full sync necessary - returning");
                        }
                        if (!ListenerUtil.mutListener.listen(20131)) {
                            mCol.log("schema diff");
                        }
                        return new Pair<>(FULL_SYNC, null);
                    }
                }
                if (!ListenerUtil.mutListener.listen(20140)) {
                    mLNewer = (ListenerUtil.mutListener.listen(20139) ? (lMod >= rMod) : (ListenerUtil.mutListener.listen(20138) ? (lMod <= rMod) : (ListenerUtil.mutListener.listen(20137) ? (lMod < rMod) : (ListenerUtil.mutListener.listen(20136) ? (lMod != rMod) : (ListenerUtil.mutListener.listen(20135) ? (lMod == rMod) : (lMod > rMod))))));
                }
                if (!ListenerUtil.mutListener.listen(20142)) {
                    // step 1.5: check collection is valid
                    if (!mCol.basicCheck()) {
                        if (!ListenerUtil.mutListener.listen(20141)) {
                            mCol.log("basic check");
                        }
                        return new Pair<>(BASIC_CHECK_FAILED, null);
                    }
                }
                if (!ListenerUtil.mutListener.listen(20143)) {
                    throwExceptionIfCancelled(con);
                }
                if (!ListenerUtil.mutListener.listen(20144)) {
                    // step 2: deletions
                    publishProgress(con, R.string.sync_deletions_message);
                }
                if (!ListenerUtil.mutListener.listen(20145)) {
                    Timber.i("Sync: collection removed data");
                }
                JSONObject lrem = removed();
                JSONObject o = new JSONObject();
                if (!ListenerUtil.mutListener.listen(20146)) {
                    o.put("minUsn", mMinUsn);
                }
                if (!ListenerUtil.mutListener.listen(20147)) {
                    o.put("lnewer", mLNewer);
                }
                if (!ListenerUtil.mutListener.listen(20148)) {
                    o.put("graves", lrem);
                }
                if (!ListenerUtil.mutListener.listen(20149)) {
                    Timber.i("Sync: sending and receiving removed data");
                }
                JSONObject rrem = mRemoteServer.start(o);
                if (!ListenerUtil.mutListener.listen(20150)) {
                    Timber.i("Sync: applying removed data");
                }
                if (!ListenerUtil.mutListener.listen(20151)) {
                    throwExceptionIfCancelled(con);
                }
                if (!ListenerUtil.mutListener.listen(20152)) {
                    remove(rrem);
                }
                if (!ListenerUtil.mutListener.listen(20153)) {
                    // ... and small objects
                    publishProgress(con, R.string.sync_small_objects_message);
                }
                if (!ListenerUtil.mutListener.listen(20154)) {
                    Timber.i("Sync: collection small changes");
                }
                JSONObject lchg = changes();
                JSONObject sch = new JSONObject();
                if (!ListenerUtil.mutListener.listen(20155)) {
                    sch.put("changes", lchg);
                }
                if (!ListenerUtil.mutListener.listen(20156)) {
                    Timber.i("Sync: sending and receiving small changes");
                }
                JSONObject rchg = mRemoteServer.applyChanges(sch);
                if (!ListenerUtil.mutListener.listen(20157)) {
                    throwExceptionIfCancelled(con);
                }
                if (!ListenerUtil.mutListener.listen(20158)) {
                    Timber.i("Sync: merging small changes");
                }
                try {
                    if (!ListenerUtil.mutListener.listen(20161)) {
                        mergeChanges(lchg, rchg);
                    }
                } catch (UnexpectedSchemaChange e) {
                    if (!ListenerUtil.mutListener.listen(20159)) {
                        mRemoteServer.abort();
                    }
                    if (!ListenerUtil.mutListener.listen(20160)) {
                        _forceFullSync();
                    }
                }
                if (!ListenerUtil.mutListener.listen(20162)) {
                    // step 3: stream large tables from server
                    publishProgress(con, R.string.sync_download_chunk);
                }
                if (!ListenerUtil.mutListener.listen(20169)) {
                    {
                        long _loopCounter400 = 0;
                        while (true) {
                            ListenerUtil.loopListener.listen("_loopCounter400", ++_loopCounter400);
                            if (!ListenerUtil.mutListener.listen(20163)) {
                                throwExceptionIfCancelled(con);
                            }
                            if (!ListenerUtil.mutListener.listen(20164)) {
                                Timber.i("Sync: downloading chunked data");
                            }
                            JSONObject chunk = mRemoteServer.chunk();
                            if (!ListenerUtil.mutListener.listen(20165)) {
                                mCol.log("server chunk", chunk);
                            }
                            if (!ListenerUtil.mutListener.listen(20166)) {
                                Timber.i("Sync: applying chunked data");
                            }
                            if (!ListenerUtil.mutListener.listen(20167)) {
                                applyChunk(chunk);
                            }
                            if (!ListenerUtil.mutListener.listen(20168)) {
                                if (chunk.getBoolean("done")) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20170)) {
                    // step 4: stream to server
                    publishProgress(con, R.string.sync_upload_chunk);
                }
                if (!ListenerUtil.mutListener.listen(20178)) {
                    {
                        long _loopCounter401 = 0;
                        while (true) {
                            ListenerUtil.loopListener.listen("_loopCounter401", ++_loopCounter401);
                            if (!ListenerUtil.mutListener.listen(20171)) {
                                throwExceptionIfCancelled(con);
                            }
                            if (!ListenerUtil.mutListener.listen(20172)) {
                                Timber.i("Sync: collecting chunked data");
                            }
                            JSONObject chunk = chunk();
                            if (!ListenerUtil.mutListener.listen(20173)) {
                                mCol.log("client chunk", chunk);
                            }
                            JSONObject sech = new JSONObject();
                            if (!ListenerUtil.mutListener.listen(20174)) {
                                sech.put("chunk", chunk);
                            }
                            if (!ListenerUtil.mutListener.listen(20175)) {
                                Timber.i("Sync: sending chunked data");
                            }
                            if (!ListenerUtil.mutListener.listen(20176)) {
                                mRemoteServer.applyChunk(sech);
                            }
                            if (!ListenerUtil.mutListener.listen(20177)) {
                                if (chunk.getBoolean("done")) {
                                    break;
                                }
                            }
                        }
                    }
                }
                // step 5: sanity check
                JSONObject c = sanityCheck();
                JSONObject sanity = mRemoteServer.sanityCheck2(c);
                if (!ListenerUtil.mutListener.listen(20180)) {
                    if ((ListenerUtil.mutListener.listen(20179) ? (sanity == null && !"ok".equals(sanity.optString("status", "bad"))) : (sanity == null || !"ok".equals(sanity.optString("status", "bad"))))) {
                        return sanityCheckError(c, sanity);
                    }
                }
                if (!ListenerUtil.mutListener.listen(20181)) {
                    // finalize
                    publishProgress(con, R.string.sync_finish_message);
                }
                if (!ListenerUtil.mutListener.listen(20182)) {
                    Timber.i("Sync: sending finish command");
                }
                long mod = mRemoteServer.finish();
                if (!ListenerUtil.mutListener.listen(20188)) {
                    if ((ListenerUtil.mutListener.listen(20187) ? (mod >= 0) : (ListenerUtil.mutListener.listen(20186) ? (mod <= 0) : (ListenerUtil.mutListener.listen(20185) ? (mod > 0) : (ListenerUtil.mutListener.listen(20184) ? (mod < 0) : (ListenerUtil.mutListener.listen(20183) ? (mod != 0) : (mod == 0))))))) {
                        return new Pair<>(FINISH_ERROR, null);
                    }
                }
                if (!ListenerUtil.mutListener.listen(20189)) {
                    Timber.i("Sync: finishing");
                }
                if (!ListenerUtil.mutListener.listen(20190)) {
                    finish(mod);
                }
                if (!ListenerUtil.mutListener.listen(20191)) {
                    publishProgress(con, R.string.sync_writing_db);
                }
                if (!ListenerUtil.mutListener.listen(20192)) {
                    mCol.getDb().getDatabase().setTransactionSuccessful();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(20098)) {
                    DB.safeEndInTransaction(mCol.getDb());
                }
            }
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (OutOfMemoryError e) {
            if (!ListenerUtil.mutListener.listen(20095)) {
                AnkiDroidApp.sendExceptionReport(e, "Syncer-sync");
            }
            return new Pair<>(OUT_OF_MEMORY_ERROR, null);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(20096)) {
                AnkiDroidApp.sendExceptionReport(e, "Syncer-sync");
            }
            return new Pair<>(IO_EXCEPTION, null);
        }
        return new Pair<>(SUCCESS, null);
    }

    private void trySetHostNum(JSONObject rMeta) {
        // And it's fine to continue without one.
        try {
            if (!ListenerUtil.mutListener.listen(20195)) {
                if (rMeta.has("hostNum")) {
                    if (!ListenerUtil.mutListener.listen(20194)) {
                        mHostNum.setHostNum(rMeta.getInt("hostNum"));
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(20193)) {
                Timber.w(e, "Failed to set hostNum");
            }
        }
    }

    @NonNull
    protected Pair<ConnectionResultType, Object> sanityCheckError(JSONObject c, JSONObject sanity) {
        if (!ListenerUtil.mutListener.listen(20196)) {
            mCol.log("sanity check failed", c, sanity);
        }
        if (!ListenerUtil.mutListener.listen(20197)) {
            UsageAnalytics.sendAnalyticsEvent(UsageAnalytics.Category.SYNC, "sanityCheckError");
        }
        if (!ListenerUtil.mutListener.listen(20198)) {
            _forceFullSync();
        }
        return new Pair<>(SANITY_CHECK_ERROR, null);
    }

    private void _forceFullSync() {
        if (!ListenerUtil.mutListener.listen(20199)) {
            // roll back and force full sync
            mCol.modSchemaNoCheck();
        }
        if (!ListenerUtil.mutListener.listen(20200)) {
            mCol.save();
        }
    }

    private void publishProgress(Connection con, int id) {
        if (!ListenerUtil.mutListener.listen(20202)) {
            if (con != null) {
                if (!ListenerUtil.mutListener.listen(20201)) {
                    con.publishProgress(id);
                }
            }
        }
    }

    public JSONObject meta() throws JSONException {
        JSONObject j = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20203)) {
            j.put("mod", mCol.getMod());
        }
        if (!ListenerUtil.mutListener.listen(20204)) {
            j.put("scm", mCol.getScm());
        }
        if (!ListenerUtil.mutListener.listen(20205)) {
            j.put("usn", mCol.getUsnForSync());
        }
        if (!ListenerUtil.mutListener.listen(20206)) {
            j.put("ts", mCol.getTime().intTime());
        }
        if (!ListenerUtil.mutListener.listen(20207)) {
            j.put("musn", 0);
        }
        if (!ListenerUtil.mutListener.listen(20208)) {
            j.put("msg", "");
        }
        if (!ListenerUtil.mutListener.listen(20209)) {
            j.put("cont", true);
        }
        return j;
    }

    /**
     * Bundle up small objects.
     */
    public JSONObject changes() {
        JSONObject o = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20210)) {
            o.put("models", getModels());
        }
        if (!ListenerUtil.mutListener.listen(20211)) {
            o.put("decks", getDecks());
        }
        if (!ListenerUtil.mutListener.listen(20212)) {
            o.put("tags", getTags());
        }
        if (!ListenerUtil.mutListener.listen(20215)) {
            if (mLNewer) {
                if (!ListenerUtil.mutListener.listen(20213)) {
                    o.put("conf", getConf());
                }
                if (!ListenerUtil.mutListener.listen(20214)) {
                    o.put("crt", mCol.getCrt());
                }
            }
        }
        return o;
    }

    public JSONObject applyChanges(JSONObject changes) throws UnexpectedSchemaChange {
        JSONObject lchg = changes();
        if (!ListenerUtil.mutListener.listen(20216)) {
            // merge our side before returning
            mergeChanges(lchg, changes);
        }
        return lchg;
    }

    public void mergeChanges(JSONObject lchg, JSONObject rchg) throws UnexpectedSchemaChange {
        if (!ListenerUtil.mutListener.listen(20217)) {
            // then the other objects
            mergeModels(rchg.getJSONArray("models"));
        }
        if (!ListenerUtil.mutListener.listen(20218)) {
            mergeDecks(rchg.getJSONArray("decks"));
        }
        if (!ListenerUtil.mutListener.listen(20219)) {
            mergeTags(rchg.getJSONArray("tags"));
        }
        if (!ListenerUtil.mutListener.listen(20221)) {
            if (rchg.has("conf")) {
                if (!ListenerUtil.mutListener.listen(20220)) {
                    mergeConf(rchg.getJSONObject("conf"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20223)) {
            // this was left out of earlier betas
            if (rchg.has("crt")) {
                if (!ListenerUtil.mutListener.listen(20222)) {
                    mCol.setCrt(rchg.getLong("crt"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20224)) {
            prepareToChunk();
        }
    }

    public JSONObject sanityCheck() {
        JSONObject result = new JSONObject();
        try {
            if (mCol.getDb().queryScalar("SELECT count() FROM cards WHERE nid NOT IN (SELECT id FROM notes)") != 0) {
                if (!ListenerUtil.mutListener.listen(20226)) {
                    Timber.e("Sync - SanityCheck: there are cards without mother notes");
                }
                if (!ListenerUtil.mutListener.listen(20227)) {
                    result.put("client", "missing notes");
                }
                return result;
            }
            if (mCol.getDb().queryScalar("SELECT count() FROM notes WHERE id NOT IN (SELECT DISTINCT nid FROM cards)") != 0) {
                if (!ListenerUtil.mutListener.listen(20228)) {
                    Timber.e("Sync - SanityCheck: there are notes without cards");
                }
                if (!ListenerUtil.mutListener.listen(20229)) {
                    result.put("client", "missing cards");
                }
                return result;
            }
            if (mCol.getDb().queryScalar("SELECT count() FROM cards WHERE usn = -1") != 0) {
                if (!ListenerUtil.mutListener.listen(20230)) {
                    Timber.e("Sync - SanityCheck: there are unsynced cards");
                }
                if (!ListenerUtil.mutListener.listen(20231)) {
                    result.put("client", "cards had usn = -1");
                }
                return result;
            }
            if (mCol.getDb().queryScalar("SELECT count() FROM notes WHERE usn = -1") != 0) {
                if (!ListenerUtil.mutListener.listen(20232)) {
                    Timber.e("Sync - SanityCheck: there are unsynced notes");
                }
                if (!ListenerUtil.mutListener.listen(20233)) {
                    result.put("client", "notes had usn = -1");
                }
                return result;
            }
            if (mCol.getDb().queryScalar("SELECT count() FROM revlog WHERE usn = -1") != 0) {
                if (!ListenerUtil.mutListener.listen(20234)) {
                    Timber.e("Sync - SanityCheck: there are unsynced revlogs");
                }
                if (!ListenerUtil.mutListener.listen(20235)) {
                    result.put("client", "revlog had usn = -1");
                }
                return result;
            }
            if (mCol.getDb().queryScalar("SELECT count() FROM graves WHERE usn = -1") != 0) {
                if (!ListenerUtil.mutListener.listen(20236)) {
                    Timber.e("Sync - SanityCheck: there are unsynced graves");
                }
                if (!ListenerUtil.mutListener.listen(20237)) {
                    result.put("client", "graves had usn = -1");
                }
                return result;
            }
            {
                long _loopCounter402 = 0;
                for (Deck g : mCol.getDecks().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter402", ++_loopCounter402);
                    if (g.getInt("usn") == -1) {
                        if (!ListenerUtil.mutListener.listen(20238)) {
                            Timber.e("Sync - SanityCheck: unsynced deck: %s", g.getString("name"));
                        }
                        if (!ListenerUtil.mutListener.listen(20239)) {
                            result.put("client", "deck had usn = -1");
                        }
                        return result;
                    }
                }
            }
            if (mCol.getTags().minusOneValue()) {
                if (!ListenerUtil.mutListener.listen(20240)) {
                    Timber.e("Sync - SanityCheck: there are unsynced tags");
                }
                if (!ListenerUtil.mutListener.listen(20241)) {
                    result.put("client", "tag had usn = -1");
                }
                return result;
            }
            boolean found = false;
            {
                long _loopCounter403 = 0;
                for (JSONObject m : mCol.getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter403", ++_loopCounter403);
                    if (mCol.getServer()) {
                        if (!ListenerUtil.mutListener.listen(20256)) {
                            // the web upgrade was mistakenly setting usn
                            if ((ListenerUtil.mutListener.listen(20253) ? (m.getInt("usn") >= 0) : (ListenerUtil.mutListener.listen(20252) ? (m.getInt("usn") <= 0) : (ListenerUtil.mutListener.listen(20251) ? (m.getInt("usn") > 0) : (ListenerUtil.mutListener.listen(20250) ? (m.getInt("usn") != 0) : (ListenerUtil.mutListener.listen(20249) ? (m.getInt("usn") == 0) : (m.getInt("usn") < 0))))))) {
                                if (!ListenerUtil.mutListener.listen(20254)) {
                                    m.put("usn", 0);
                                }
                                if (!ListenerUtil.mutListener.listen(20255)) {
                                    found = true;
                                }
                            }
                        }
                    } else {
                        if ((ListenerUtil.mutListener.listen(20246) ? (m.getInt("usn") >= -1) : (ListenerUtil.mutListener.listen(20245) ? (m.getInt("usn") <= -1) : (ListenerUtil.mutListener.listen(20244) ? (m.getInt("usn") > -1) : (ListenerUtil.mutListener.listen(20243) ? (m.getInt("usn") < -1) : (ListenerUtil.mutListener.listen(20242) ? (m.getInt("usn") != -1) : (m.getInt("usn") == -1))))))) {
                            if (!ListenerUtil.mutListener.listen(20247)) {
                                Timber.e("Sync - SanityCheck: unsynced model: %s", m.getString("name"));
                            }
                            if (!ListenerUtil.mutListener.listen(20248)) {
                                result.put("client", "model had usn = -1");
                            }
                            return result;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20258)) {
                if (found) {
                    if (!ListenerUtil.mutListener.listen(20257)) {
                        mCol.getModels().save();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20259)) {
                // check for missing parent decks
                mCol.getSched().deckDueList();
            }
            // return summary of deck
            JSONArray check = new JSONArray();
            JSONArray counts = new JSONArray();
            // We modified mReportLimit inside the scheduler, and this causes issues syncing dynamic decks.
            AbstractSched syncScheduler = mCol.createScheduler(SYNC_SCHEDULER_REPORT_LIMIT);
            if (!ListenerUtil.mutListener.listen(20260)) {
                syncScheduler.resetCounts();
            }
            Counts counts_ = syncScheduler.counts();
            if (!ListenerUtil.mutListener.listen(20261)) {
                counts.put(counts_.getNew());
            }
            if (!ListenerUtil.mutListener.listen(20262)) {
                counts.put(counts_.getLrn());
            }
            if (!ListenerUtil.mutListener.listen(20263)) {
                counts.put(counts_.getRev());
            }
            if (!ListenerUtil.mutListener.listen(20264)) {
                check.put(counts);
            }
            if (!ListenerUtil.mutListener.listen(20265)) {
                check.put(mCol.getDb().queryScalar("SELECT count() FROM cards"));
            }
            if (!ListenerUtil.mutListener.listen(20266)) {
                check.put(mCol.getDb().queryScalar("SELECT count() FROM notes"));
            }
            if (!ListenerUtil.mutListener.listen(20267)) {
                check.put(mCol.getDb().queryScalar("SELECT count() FROM revlog"));
            }
            if (!ListenerUtil.mutListener.listen(20268)) {
                check.put(mCol.getDb().queryScalar("SELECT count() FROM graves"));
            }
            if (!ListenerUtil.mutListener.listen(20269)) {
                check.put(mCol.getModels().all().size());
            }
            if (!ListenerUtil.mutListener.listen(20270)) {
                check.put(mCol.getDecks().all().size());
            }
            if (!ListenerUtil.mutListener.listen(20271)) {
                check.put(mCol.getDecks().allConf().size());
            }
            if (!ListenerUtil.mutListener.listen(20272)) {
                result.put("client", check);
            }
            return result;
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(20225)) {
                Timber.e(e, "Syncer.sanityCheck()");
            }
            throw new RuntimeException(e);
        }
    }

    private Pair<String, Object[]> usnLim() {
        if (mCol.getServer()) {
            return new Pair<>("usn >= ?", new Object[] { mMinUsn });
        } else {
            return new Pair<>("usn = -1", null);
        }
    }

    public long finish() {
        return finish(0);
    }

    private long finish(long mod) {
        if (!ListenerUtil.mutListener.listen(20279)) {
            if ((ListenerUtil.mutListener.listen(20277) ? (mod >= 0) : (ListenerUtil.mutListener.listen(20276) ? (mod <= 0) : (ListenerUtil.mutListener.listen(20275) ? (mod > 0) : (ListenerUtil.mutListener.listen(20274) ? (mod < 0) : (ListenerUtil.mutListener.listen(20273) ? (mod != 0) : (mod == 0))))))) {
                if (!ListenerUtil.mutListener.listen(20278)) {
                    // server side; we decide new mod time
                    mod = mCol.getTime().intTimeMS();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20280)) {
            mCol.setLs(mod);
        }
        if (!ListenerUtil.mutListener.listen(20285)) {
            mCol.setUsnAfterSync((ListenerUtil.mutListener.listen(20284) ? (mMaxUsn % 1) : (ListenerUtil.mutListener.listen(20283) ? (mMaxUsn / 1) : (ListenerUtil.mutListener.listen(20282) ? (mMaxUsn * 1) : (ListenerUtil.mutListener.listen(20281) ? (mMaxUsn - 1) : (mMaxUsn + 1))))));
        }
        if (!ListenerUtil.mutListener.listen(20286)) {
            // ensure we save the mod time even if no changes made
            mCol.getDb().setMod(true);
        }
        if (!ListenerUtil.mutListener.listen(20287)) {
            mCol.save(null, mod);
        }
        return mod;
    }

    private void prepareToChunk() {
        if (!ListenerUtil.mutListener.listen(20288)) {
            mTablesLeft = new LinkedList<>();
        }
        if (!ListenerUtil.mutListener.listen(20289)) {
            mTablesLeft.add("revlog");
        }
        if (!ListenerUtil.mutListener.listen(20290)) {
            mTablesLeft.add("cards");
        }
        if (!ListenerUtil.mutListener.listen(20291)) {
            mTablesLeft.add("notes");
        }
        if (!ListenerUtil.mutListener.listen(20292)) {
            mCursor = null;
        }
    }

    private Cursor cursorForTable(String table) {
        Pair<String, Object[]> limAndArg = usnLim();
        if ("revlog".equals(table)) {
            return mCol.getDb().query("SELECT id, cid, " + mMaxUsn + ", ease, ivl, lastIvl, factor, time, type FROM revlog WHERE " + limAndArg.first, limAndArg.second);
        } else if ("cards".equals(table)) {
            return mCol.getDb().query("SELECT id, nid, did, ord, mod, " + mMaxUsn + ", type, queue, due, ivl, factor, reps, lapses, left, odue, odid, flags, data FROM cards WHERE " + limAndArg.first, limAndArg.second);
        } else {
            return mCol.getDb().query("SELECT id, guid, mid, mod, " + mMaxUsn + ", tags, flds, '', '', flags, data FROM notes WHERE " + limAndArg.first, limAndArg.second);
        }
    }

    private List<Integer> columnTypesForQuery(String table) {
        if ("revlog".equals(table)) {
            return Arrays.asList(TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER);
        } else if ("cards".equals(table)) {
            return Arrays.asList(TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_STRING);
        } else {
            return Arrays.asList(TYPE_INTEGER, TYPE_STRING, TYPE_INTEGER, TYPE_INTEGER, TYPE_INTEGER, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_STRING, TYPE_INTEGER, TYPE_STRING);
        }
    }

    public JSONObject chunk() {
        JSONObject buf = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20293)) {
            buf.put("done", false);
        }
        int lim = 250;
        List<Integer> colTypes = null;
        if (!ListenerUtil.mutListener.listen(20334)) {
            {
                long _loopCounter406 = 0;
                while ((ListenerUtil.mutListener.listen(20333) ? (!mTablesLeft.isEmpty() || (ListenerUtil.mutListener.listen(20332) ? (lim >= 0) : (ListenerUtil.mutListener.listen(20331) ? (lim <= 0) : (ListenerUtil.mutListener.listen(20330) ? (lim < 0) : (ListenerUtil.mutListener.listen(20329) ? (lim != 0) : (ListenerUtil.mutListener.listen(20328) ? (lim == 0) : (lim > 0))))))) : (!mTablesLeft.isEmpty() && (ListenerUtil.mutListener.listen(20332) ? (lim >= 0) : (ListenerUtil.mutListener.listen(20331) ? (lim <= 0) : (ListenerUtil.mutListener.listen(20330) ? (lim < 0) : (ListenerUtil.mutListener.listen(20329) ? (lim != 0) : (ListenerUtil.mutListener.listen(20328) ? (lim == 0) : (lim > 0))))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter406", ++_loopCounter406);
                    String curTable = mTablesLeft.getFirst();
                    if (!ListenerUtil.mutListener.listen(20295)) {
                        if (mCursor == null) {
                            if (!ListenerUtil.mutListener.listen(20294)) {
                                mCursor = cursorForTable(curTable);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20296)) {
                        colTypes = columnTypesForQuery(curTable);
                    }
                    JSONArray rows = new JSONArray();
                    int count = mCursor.getColumnCount();
                    int fetched = 0;
                    if (!ListenerUtil.mutListener.listen(20314)) {
                        {
                            long _loopCounter405 = 0;
                            while (mCursor.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter405", ++_loopCounter405);
                                JSONArray r = new JSONArray();
                                if (!ListenerUtil.mutListener.listen(20306)) {
                                    {
                                        long _loopCounter404 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(20305) ? (i >= count) : (ListenerUtil.mutListener.listen(20304) ? (i <= count) : (ListenerUtil.mutListener.listen(20303) ? (i > count) : (ListenerUtil.mutListener.listen(20302) ? (i != count) : (ListenerUtil.mutListener.listen(20301) ? (i == count) : (i < count)))))); i++) {
                                            ListenerUtil.loopListener.listen("_loopCounter404", ++_loopCounter404);
                                            if (!ListenerUtil.mutListener.listen(20300)) {
                                                switch(colTypes.get(i)) {
                                                    case TYPE_STRING:
                                                        if (!ListenerUtil.mutListener.listen(20297)) {
                                                            r.put(mCursor.getString(i));
                                                        }
                                                        break;
                                                    case TYPE_FLOAT:
                                                        if (!ListenerUtil.mutListener.listen(20298)) {
                                                            r.put(mCursor.getDouble(i));
                                                        }
                                                        break;
                                                    case TYPE_INTEGER:
                                                        if (!ListenerUtil.mutListener.listen(20299)) {
                                                            r.put(mCursor.getLong(i));
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(20307)) {
                                    rows.put(r);
                                }
                                if (!ListenerUtil.mutListener.listen(20313)) {
                                    if ((ListenerUtil.mutListener.listen(20312) ? (++fetched >= lim) : (ListenerUtil.mutListener.listen(20311) ? (++fetched <= lim) : (ListenerUtil.mutListener.listen(20310) ? (++fetched > lim) : (ListenerUtil.mutListener.listen(20309) ? (++fetched < lim) : (ListenerUtil.mutListener.listen(20308) ? (++fetched != lim) : (++fetched == lim))))))) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20325)) {
                        if ((ListenerUtil.mutListener.listen(20319) ? (fetched >= lim) : (ListenerUtil.mutListener.listen(20318) ? (fetched <= lim) : (ListenerUtil.mutListener.listen(20317) ? (fetched > lim) : (ListenerUtil.mutListener.listen(20316) ? (fetched < lim) : (ListenerUtil.mutListener.listen(20315) ? (fetched == lim) : (fetched != lim))))))) {
                            if (!ListenerUtil.mutListener.listen(20320)) {
                                // table is empty
                                mTablesLeft.removeFirst();
                            }
                            if (!ListenerUtil.mutListener.listen(20321)) {
                                mCursor.close();
                            }
                            if (!ListenerUtil.mutListener.listen(20322)) {
                                mCursor = null;
                            }
                            if (!ListenerUtil.mutListener.listen(20324)) {
                                // if we're the client, mark the objects as having been sent
                                if (!mCol.getServer()) {
                                    if (!ListenerUtil.mutListener.listen(20323)) {
                                        mCol.getDb().execute("UPDATE " + curTable + " SET usn=? WHERE usn=-1", mMaxUsn);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(20326)) {
                        buf.put(curTable, rows);
                    }
                    if (!ListenerUtil.mutListener.listen(20327)) {
                        lim -= fetched;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20336)) {
            if (mTablesLeft.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(20335)) {
                    buf.put("done", true);
                }
            }
        }
        return buf;
    }

    public void applyChunk(JSONObject chunk) {
        if (!ListenerUtil.mutListener.listen(20338)) {
            if (chunk.has("revlog")) {
                if (!ListenerUtil.mutListener.listen(20337)) {
                    mergeRevlog(chunk.getJSONArray("revlog"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20340)) {
            if (chunk.has("cards")) {
                if (!ListenerUtil.mutListener.listen(20339)) {
                    mergeCards(chunk.getJSONArray("cards"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20342)) {
            if (chunk.has("notes")) {
                if (!ListenerUtil.mutListener.listen(20341)) {
                    mergeNotes(chunk.getJSONArray("notes"));
                }
            }
        }
    }

    private JSONObject removed() {
        JSONArray cards = new JSONArray();
        JSONArray notes = new JSONArray();
        JSONArray decks = new JSONArray();
        Pair<String, Object[]> limAndArgs = usnLim();
        try (Cursor cur = mCol.getDb().query("SELECT oid, type FROM graves WHERE " + limAndArgs.first, limAndArgs.second)) {
            if (!ListenerUtil.mutListener.listen(20347)) {
                {
                    long _loopCounter407 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter407", ++_loopCounter407);
                        @Consts.REM_TYPE
                        int type = cur.getInt(1);
                        if (!ListenerUtil.mutListener.listen(20346)) {
                            switch(type) {
                                case Consts.REM_CARD:
                                    if (!ListenerUtil.mutListener.listen(20343)) {
                                        cards.put(cur.getLong(0));
                                    }
                                    break;
                                case Consts.REM_NOTE:
                                    if (!ListenerUtil.mutListener.listen(20344)) {
                                        notes.put(cur.getLong(0));
                                    }
                                    break;
                                case Consts.REM_DECK:
                                    if (!ListenerUtil.mutListener.listen(20345)) {
                                        decks.put(cur.getLong(0));
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20349)) {
            if (!mCol.getServer()) {
                if (!ListenerUtil.mutListener.listen(20348)) {
                    mCol.getDb().execute("UPDATE graves SET usn=" + mMaxUsn + " WHERE usn=-1");
                }
            }
        }
        JSONObject o = new JSONObject();
        if (!ListenerUtil.mutListener.listen(20350)) {
            o.put("cards", cards);
        }
        if (!ListenerUtil.mutListener.listen(20351)) {
            o.put("notes", notes);
        }
        if (!ListenerUtil.mutListener.listen(20352)) {
            o.put("decks", decks);
        }
        return o;
    }

    public JSONObject start(int minUsn, boolean lnewer, JSONObject graves) {
        if (!ListenerUtil.mutListener.listen(20353)) {
            mMaxUsn = mCol.getUsnForSync();
        }
        if (!ListenerUtil.mutListener.listen(20354)) {
            mMinUsn = minUsn;
        }
        if (!ListenerUtil.mutListener.listen(20355)) {
            mLNewer = !lnewer;
        }
        JSONObject lgraves = removed();
        if (!ListenerUtil.mutListener.listen(20356)) {
            remove(graves);
        }
        return lgraves;
    }

    private void remove(JSONObject graves) {
        // pretend to be the server so we don't set usn = -1
        boolean wasServer = mCol.getServer();
        if (!ListenerUtil.mutListener.listen(20357)) {
            mCol.setServer(true);
        }
        if (!ListenerUtil.mutListener.listen(20358)) {
            // notes first, so we don't end up with duplicate graves
            mCol._remNotes(graves.getJSONArray("notes").toLongList());
        }
        if (!ListenerUtil.mutListener.listen(20359)) {
            // then cards
            mCol.remCards(graves.getJSONArray("cards").toLongList(), false);
        }
        // and decks
        JSONArray decks = graves.getJSONArray("decks");
        if (!ListenerUtil.mutListener.listen(20361)) {
            {
                long _loopCounter408 = 0;
                for (Long did : decks.longIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter408", ++_loopCounter408);
                    if (!ListenerUtil.mutListener.listen(20360)) {
                        mCol.getDecks().rem(did, false, false);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20362)) {
            mCol.setServer(wasServer);
        }
    }

    private JSONArray getModels() {
        JSONArray result = new JSONArray();
        if (!ListenerUtil.mutListener.listen(20381)) {
            if (mCol.getServer()) {
                if (!ListenerUtil.mutListener.listen(20380)) {
                    {
                        long _loopCounter410 = 0;
                        for (JSONObject m : mCol.getModels().all()) {
                            ListenerUtil.loopListener.listen("_loopCounter410", ++_loopCounter410);
                            if (!ListenerUtil.mutListener.listen(20379)) {
                                if ((ListenerUtil.mutListener.listen(20377) ? (m.getInt("usn") <= mMinUsn) : (ListenerUtil.mutListener.listen(20376) ? (m.getInt("usn") > mMinUsn) : (ListenerUtil.mutListener.listen(20375) ? (m.getInt("usn") < mMinUsn) : (ListenerUtil.mutListener.listen(20374) ? (m.getInt("usn") != mMinUsn) : (ListenerUtil.mutListener.listen(20373) ? (m.getInt("usn") == mMinUsn) : (m.getInt("usn") >= mMinUsn))))))) {
                                    if (!ListenerUtil.mutListener.listen(20378)) {
                                        result.put(m);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20371)) {
                    {
                        long _loopCounter409 = 0;
                        for (JSONObject m : mCol.getModels().all()) {
                            ListenerUtil.loopListener.listen("_loopCounter409", ++_loopCounter409);
                            if (!ListenerUtil.mutListener.listen(20370)) {
                                if ((ListenerUtil.mutListener.listen(20367) ? (m.getInt("usn") >= -1) : (ListenerUtil.mutListener.listen(20366) ? (m.getInt("usn") <= -1) : (ListenerUtil.mutListener.listen(20365) ? (m.getInt("usn") > -1) : (ListenerUtil.mutListener.listen(20364) ? (m.getInt("usn") < -1) : (ListenerUtil.mutListener.listen(20363) ? (m.getInt("usn") != -1) : (m.getInt("usn") == -1))))))) {
                                    if (!ListenerUtil.mutListener.listen(20368)) {
                                        m.put("usn", mMaxUsn);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20369)) {
                                        result.put(m);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20372)) {
                    mCol.getModels().save();
                }
            }
        }
        return result;
    }

    private void mergeModels(JSONArray rchg) throws UnexpectedSchemaChange {
        if (!ListenerUtil.mutListener.listen(20393)) {
            {
                long _loopCounter411 = 0;
                for (JSONObject model : rchg.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter411", ++_loopCounter411);
                    Model r = new Model(model);
                    Model l = mCol.getModels().get(r.getLong("id"));
                    if (!ListenerUtil.mutListener.listen(20392)) {
                        // if missing locally or server is newer, update
                        if ((ListenerUtil.mutListener.listen(20387) ? (l == null && (ListenerUtil.mutListener.listen(20386) ? (r.getLong("mod") >= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20385) ? (r.getLong("mod") <= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20384) ? (r.getLong("mod") < l.getLong("mod")) : (ListenerUtil.mutListener.listen(20383) ? (r.getLong("mod") != l.getLong("mod")) : (ListenerUtil.mutListener.listen(20382) ? (r.getLong("mod") == l.getLong("mod")) : (r.getLong("mod") > l.getLong("mod")))))))) : (l == null || (ListenerUtil.mutListener.listen(20386) ? (r.getLong("mod") >= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20385) ? (r.getLong("mod") <= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20384) ? (r.getLong("mod") < l.getLong("mod")) : (ListenerUtil.mutListener.listen(20383) ? (r.getLong("mod") != l.getLong("mod")) : (ListenerUtil.mutListener.listen(20382) ? (r.getLong("mod") == l.getLong("mod")) : (r.getLong("mod") > l.getLong("mod")))))))))) {
                            if (!ListenerUtil.mutListener.listen(20390)) {
                                // syncing algorithm should handle this in a better way.
                                if (l != null) {
                                    if (!ListenerUtil.mutListener.listen(20388)) {
                                        if (l.getJSONArray("flds").length() != r.getJSONArray("flds").length()) {
                                            throw new UnexpectedSchemaChange();
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(20389)) {
                                        if (l.getJSONArray("tmpls").length() != r.getJSONArray("tmpls").length()) {
                                            throw new UnexpectedSchemaChange();
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(20391)) {
                                mCol.getModels().update(r);
                            }
                        }
                    }
                }
            }
        }
    }

    private JSONArray getDecks() {
        JSONArray result = new JSONArray();
        if (!ListenerUtil.mutListener.listen(20423)) {
            if (mCol.getServer()) {
                JSONArray decks = new JSONArray();
                if (!ListenerUtil.mutListener.listen(20412)) {
                    {
                        long _loopCounter414 = 0;
                        for (Deck g : mCol.getDecks().all()) {
                            ListenerUtil.loopListener.listen("_loopCounter414", ++_loopCounter414);
                            if (!ListenerUtil.mutListener.listen(20411)) {
                                if ((ListenerUtil.mutListener.listen(20409) ? (g.getInt("usn") <= mMinUsn) : (ListenerUtil.mutListener.listen(20408) ? (g.getInt("usn") > mMinUsn) : (ListenerUtil.mutListener.listen(20407) ? (g.getInt("usn") < mMinUsn) : (ListenerUtil.mutListener.listen(20406) ? (g.getInt("usn") != mMinUsn) : (ListenerUtil.mutListener.listen(20405) ? (g.getInt("usn") == mMinUsn) : (g.getInt("usn") >= mMinUsn))))))) {
                                    if (!ListenerUtil.mutListener.listen(20410)) {
                                        decks.put(g);
                                    }
                                }
                            }
                        }
                    }
                }
                JSONArray dconfs = new JSONArray();
                if (!ListenerUtil.mutListener.listen(20420)) {
                    {
                        long _loopCounter415 = 0;
                        for (DeckConfig g : mCol.getDecks().allConf()) {
                            ListenerUtil.loopListener.listen("_loopCounter415", ++_loopCounter415);
                            if (!ListenerUtil.mutListener.listen(20419)) {
                                if ((ListenerUtil.mutListener.listen(20417) ? (g.getInt("usn") <= mMinUsn) : (ListenerUtil.mutListener.listen(20416) ? (g.getInt("usn") > mMinUsn) : (ListenerUtil.mutListener.listen(20415) ? (g.getInt("usn") < mMinUsn) : (ListenerUtil.mutListener.listen(20414) ? (g.getInt("usn") != mMinUsn) : (ListenerUtil.mutListener.listen(20413) ? (g.getInt("usn") == mMinUsn) : (g.getInt("usn") >= mMinUsn))))))) {
                                    if (!ListenerUtil.mutListener.listen(20418)) {
                                        dconfs.put(g);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20421)) {
                    result.put(decks);
                }
                if (!ListenerUtil.mutListener.listen(20422)) {
                    result.put(dconfs);
                }
            } else {
                JSONArray decks = new JSONArray();
                if (!ListenerUtil.mutListener.listen(20397)) {
                    {
                        long _loopCounter412 = 0;
                        for (Deck g : mCol.getDecks().all()) {
                            ListenerUtil.loopListener.listen("_loopCounter412", ++_loopCounter412);
                            if (!ListenerUtil.mutListener.listen(20396)) {
                                if (g.getInt("usn") == -1) {
                                    if (!ListenerUtil.mutListener.listen(20394)) {
                                        g.put("usn", mMaxUsn);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20395)) {
                                        decks.put(g);
                                    }
                                }
                            }
                        }
                    }
                }
                JSONArray dconfs = new JSONArray();
                if (!ListenerUtil.mutListener.listen(20401)) {
                    {
                        long _loopCounter413 = 0;
                        for (DeckConfig g : mCol.getDecks().allConf()) {
                            ListenerUtil.loopListener.listen("_loopCounter413", ++_loopCounter413);
                            if (!ListenerUtil.mutListener.listen(20400)) {
                                if (g.getInt("usn") == -1) {
                                    if (!ListenerUtil.mutListener.listen(20398)) {
                                        g.put("usn", mMaxUsn);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20399)) {
                                        dconfs.put(g);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20402)) {
                    mCol.getDecks().save();
                }
                if (!ListenerUtil.mutListener.listen(20403)) {
                    result.put(decks);
                }
                if (!ListenerUtil.mutListener.listen(20404)) {
                    result.put(dconfs);
                }
            }
        }
        return result;
    }

    private void mergeDecks(JSONArray rchg) {
        JSONArray decks = rchg.getJSONArray(0);
        if (!ListenerUtil.mutListener.listen(20432)) {
            {
                long _loopCounter416 = 0;
                for (JSONObject deck : decks.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter416", ++_loopCounter416);
                    Deck r = new Deck(deck);
                    Deck l = mCol.getDecks().get(r.getLong("id"), false);
                    if (!ListenerUtil.mutListener.listen(20431)) {
                        // if missing locally or server is newer, update
                        if ((ListenerUtil.mutListener.listen(20429) ? (l == null && (ListenerUtil.mutListener.listen(20428) ? (r.getLong("mod") >= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20427) ? (r.getLong("mod") <= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20426) ? (r.getLong("mod") < l.getLong("mod")) : (ListenerUtil.mutListener.listen(20425) ? (r.getLong("mod") != l.getLong("mod")) : (ListenerUtil.mutListener.listen(20424) ? (r.getLong("mod") == l.getLong("mod")) : (r.getLong("mod") > l.getLong("mod")))))))) : (l == null || (ListenerUtil.mutListener.listen(20428) ? (r.getLong("mod") >= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20427) ? (r.getLong("mod") <= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20426) ? (r.getLong("mod") < l.getLong("mod")) : (ListenerUtil.mutListener.listen(20425) ? (r.getLong("mod") != l.getLong("mod")) : (ListenerUtil.mutListener.listen(20424) ? (r.getLong("mod") == l.getLong("mod")) : (r.getLong("mod") > l.getLong("mod")))))))))) {
                            if (!ListenerUtil.mutListener.listen(20430)) {
                                mCol.getDecks().update(r);
                            }
                        }
                    }
                }
            }
        }
        JSONArray confs = rchg.getJSONArray(1);
        if (!ListenerUtil.mutListener.listen(20441)) {
            {
                long _loopCounter417 = 0;
                for (JSONObject deckConfig : confs.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter417", ++_loopCounter417);
                    DeckConfig r = new DeckConfig(deckConfig);
                    DeckConfig l = mCol.getDecks().getConf(r.getLong("id"));
                    if (!ListenerUtil.mutListener.listen(20440)) {
                        // if missing locally or server is newer, update
                        if ((ListenerUtil.mutListener.listen(20438) ? (l == null && (ListenerUtil.mutListener.listen(20437) ? (r.getLong("mod") >= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20436) ? (r.getLong("mod") <= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20435) ? (r.getLong("mod") < l.getLong("mod")) : (ListenerUtil.mutListener.listen(20434) ? (r.getLong("mod") != l.getLong("mod")) : (ListenerUtil.mutListener.listen(20433) ? (r.getLong("mod") == l.getLong("mod")) : (r.getLong("mod") > l.getLong("mod")))))))) : (l == null || (ListenerUtil.mutListener.listen(20437) ? (r.getLong("mod") >= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20436) ? (r.getLong("mod") <= l.getLong("mod")) : (ListenerUtil.mutListener.listen(20435) ? (r.getLong("mod") < l.getLong("mod")) : (ListenerUtil.mutListener.listen(20434) ? (r.getLong("mod") != l.getLong("mod")) : (ListenerUtil.mutListener.listen(20433) ? (r.getLong("mod") == l.getLong("mod")) : (r.getLong("mod") > l.getLong("mod")))))))))) {
                            if (!ListenerUtil.mutListener.listen(20439)) {
                                mCol.getDecks().updateConf(r);
                            }
                        }
                    }
                }
            }
        }
    }

    private JSONArray getTags() {
        JSONArray result = new JSONArray();
        if (!ListenerUtil.mutListener.listen(20460)) {
            if (mCol.getServer()) {
                if (!ListenerUtil.mutListener.listen(20459)) {
                    {
                        long _loopCounter419 = 0;
                        for (Map.Entry<String, Integer> t : mCol.getTags().allItems()) {
                            ListenerUtil.loopListener.listen("_loopCounter419", ++_loopCounter419);
                            if (!ListenerUtil.mutListener.listen(20458)) {
                                if ((ListenerUtil.mutListener.listen(20456) ? (t.getValue() <= mMinUsn) : (ListenerUtil.mutListener.listen(20455) ? (t.getValue() > mMinUsn) : (ListenerUtil.mutListener.listen(20454) ? (t.getValue() < mMinUsn) : (ListenerUtil.mutListener.listen(20453) ? (t.getValue() != mMinUsn) : (ListenerUtil.mutListener.listen(20452) ? (t.getValue() == mMinUsn) : (t.getValue() >= mMinUsn))))))) {
                                    if (!ListenerUtil.mutListener.listen(20457)) {
                                        result.put(t.getKey());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20450)) {
                    {
                        long _loopCounter418 = 0;
                        for (Map.Entry<String, Integer> t : mCol.getTags().allItems()) {
                            ListenerUtil.loopListener.listen("_loopCounter418", ++_loopCounter418);
                            if (!ListenerUtil.mutListener.listen(20449)) {
                                if ((ListenerUtil.mutListener.listen(20446) ? (t.getValue() >= -1) : (ListenerUtil.mutListener.listen(20445) ? (t.getValue() <= -1) : (ListenerUtil.mutListener.listen(20444) ? (t.getValue() > -1) : (ListenerUtil.mutListener.listen(20443) ? (t.getValue() < -1) : (ListenerUtil.mutListener.listen(20442) ? (t.getValue() != -1) : (t.getValue() == -1))))))) {
                                    String tag = t.getKey();
                                    if (!ListenerUtil.mutListener.listen(20447)) {
                                        mCol.getTags().add(t.getKey(), mMaxUsn);
                                    }
                                    if (!ListenerUtil.mutListener.listen(20448)) {
                                        result.put(tag);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20451)) {
                    mCol.getTags().save();
                }
            }
        }
        return result;
    }

    private void mergeTags(JSONArray tags) {
        if (!ListenerUtil.mutListener.listen(20461)) {
            mCol.getTags().register(tags.toStringList(), mMaxUsn);
        }
    }

    private void mergeRevlog(JSONArray logs) {
        if (!ListenerUtil.mutListener.listen(20463)) {
            {
                long _loopCounter420 = 0;
                for (JSONArray log : logs.jsonArrayIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter420", ++_loopCounter420);
                    try {
                        if (!ListenerUtil.mutListener.listen(20462)) {
                            mCol.getDb().execute("INSERT OR IGNORE INTO revlog VALUES (?,?,?,?,?,?,?,?,?)", Utils.jsonArray2Objects(log));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private ArrayList<Object[]> newerRows(JSONArray data, String table, int modIdx) {
        long[] ids = new long[data.length()];
        if (!ListenerUtil.mutListener.listen(20470)) {
            {
                long _loopCounter421 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(20469) ? (i >= data.length()) : (ListenerUtil.mutListener.listen(20468) ? (i <= data.length()) : (ListenerUtil.mutListener.listen(20467) ? (i > data.length()) : (ListenerUtil.mutListener.listen(20466) ? (i != data.length()) : (ListenerUtil.mutListener.listen(20465) ? (i == data.length()) : (i < data.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter421", ++_loopCounter421);
                    if (!ListenerUtil.mutListener.listen(20464)) {
                        ids[i] = data.getJSONArray(i).getLong(0);
                    }
                }
            }
        }
        Pair<String, Object[]> limAndArg = usnLim();
        Map<Long, Long> lmods = new HashMap<>(mCol.getDb().queryScalar("SELECT count() FROM " + table + " WHERE id IN " + Utils.ids2str(ids) + " AND " + limAndArg.first, limAndArg.second));
        try (Cursor cur = mCol.getDb().query("SELECT id, mod FROM " + table + " WHERE id IN " + Utils.ids2str(ids) + " AND " + limAndArg.first, limAndArg.second)) {
            if (!ListenerUtil.mutListener.listen(20472)) {
                {
                    long _loopCounter422 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter422", ++_loopCounter422);
                        if (!ListenerUtil.mutListener.listen(20471)) {
                            lmods.put(cur.getLong(0), cur.getLong(1));
                        }
                    }
                }
            }
        }
        ArrayList<Object[]> update = new ArrayList<>(data.length());
        if (!ListenerUtil.mutListener.listen(20481)) {
            {
                long _loopCounter423 = 0;
                for (JSONArray r : data.jsonArrayIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter423", ++_loopCounter423);
                    if (!ListenerUtil.mutListener.listen(20480)) {
                        if ((ListenerUtil.mutListener.listen(20478) ? (!lmods.containsKey(r.getLong(0)) && (ListenerUtil.mutListener.listen(20477) ? (lmods.get(r.getLong(0)) >= r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20476) ? (lmods.get(r.getLong(0)) <= r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20475) ? (lmods.get(r.getLong(0)) > r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20474) ? (lmods.get(r.getLong(0)) != r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20473) ? (lmods.get(r.getLong(0)) == r.getLong(modIdx)) : (lmods.get(r.getLong(0)) < r.getLong(modIdx)))))))) : (!lmods.containsKey(r.getLong(0)) || (ListenerUtil.mutListener.listen(20477) ? (lmods.get(r.getLong(0)) >= r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20476) ? (lmods.get(r.getLong(0)) <= r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20475) ? (lmods.get(r.getLong(0)) > r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20474) ? (lmods.get(r.getLong(0)) != r.getLong(modIdx)) : (ListenerUtil.mutListener.listen(20473) ? (lmods.get(r.getLong(0)) == r.getLong(modIdx)) : (lmods.get(r.getLong(0)) < r.getLong(modIdx)))))))))) {
                            if (!ListenerUtil.mutListener.listen(20479)) {
                                update.add(Utils.jsonArray2Objects(r));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20482)) {
            mCol.log(table, data);
        }
        return update;
    }

    private void mergeCards(JSONArray cards) {
        if (!ListenerUtil.mutListener.listen(20484)) {
            {
                long _loopCounter424 = 0;
                for (Object[] r : newerRows(cards, "cards", 4)) {
                    ListenerUtil.loopListener.listen("_loopCounter424", ++_loopCounter424);
                    if (!ListenerUtil.mutListener.listen(20483)) {
                        mCol.getDb().execute("INSERT OR REPLACE INTO cards VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", r);
                    }
                }
            }
        }
    }

    private void mergeNotes(JSONArray notes) {
        if (!ListenerUtil.mutListener.listen(20487)) {
            {
                long _loopCounter425 = 0;
                for (Object[] n : newerRows(notes, "notes", 4)) {
                    ListenerUtil.loopListener.listen("_loopCounter425", ++_loopCounter425);
                    if (!ListenerUtil.mutListener.listen(20485)) {
                        mCol.getDb().execute("INSERT OR REPLACE INTO notes VALUES (?,?,?,?,?,?,?,?,?,?,?)", n);
                    }
                    if (!ListenerUtil.mutListener.listen(20486)) {
                        mCol.updateFieldCache(new long[] { ((Number) n[0]).longValue() });
                    }
                }
            }
        }
    }

    public String getSyncMsg() {
        return mSyncMsg;
    }

    private JSONObject getConf() {
        return mCol.getConf();
    }

    private void mergeConf(JSONObject conf) {
        if (!ListenerUtil.mutListener.listen(20488)) {
            mCol.setConf(conf);
        }
    }

    /**
     * If the user asked to cancel the sync then we just throw a Runtime exception which should be gracefully handled
     * @param con
     */
    private void throwExceptionIfCancelled(Connection con) {
        if (!ListenerUtil.mutListener.listen(20492)) {
            if (Connection.getIsCancelled()) {
                if (!ListenerUtil.mutListener.listen(20489)) {
                    Timber.i("Sync was cancelled");
                }
                if (!ListenerUtil.mutListener.listen(20490)) {
                    publishProgress(con, R.string.sync_cancelled);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(20491)) {
                        mRemoteServer.abort();
                    }
                } catch (UnknownHttpResponseException ignored) {
                }
                throw new RuntimeException(USER_ABORTED_SYNC.toString());
            }
        }
    }

    private static class UnexpectedSchemaChange extends Exception {
    }
}
