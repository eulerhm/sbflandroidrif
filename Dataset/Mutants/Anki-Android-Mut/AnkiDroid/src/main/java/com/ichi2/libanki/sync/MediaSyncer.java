/**
 * *************************************************************************************
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2014 Houssam Salem <houssam.salem.au@gmail.com>                        *
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

import android.database.SQLException;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.exception.MediaSyncException;
import com.ichi2.anki.exception.UnknownHttpResponseException;
import com.ichi2.async.Connection;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipFile;
import timber.log.Timber;
import static com.ichi2.libanki.sync.Syncer.ConnectionResultType;
import static com.ichi2.libanki.sync.Syncer.ConnectionResultType.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * About conflicts:
 * - to minimize data loss, if both sides are marked for sending and one
 *   side has been deleted, favour the add
 * - if added/changed on both sides, favour the server version on the
 *   assumption other syncers are in sync with the server
 *
 * A note about differences to the original python version of this class. We found that:
 *  1 - There is no reliable way to detect changes to the media directory on Android due to the
 *      file systems used (mainly FAT32 for sdcards) and the utilities available to probe them.
 *  2 - Scanning for media changes can take a very long time with thousands of files.
 *
 * Given these two points, we have decided to avoid the call to findChanges() on every sync and
 * only do it on the first sync to build the initial database. Changes to the media collection
 * made through AnkiDroid (e.g., multimedia note editor, media check) are recorded directly in
 * the media database as they are made. This allows us to skip finding media changes entirely
 * as the database already contains the changes.
 *
 * The downside to this approach is that changes made to the media directory externally (e.g.,
 * through a file manager) will not be recorded and will not be synced. In this case, the user
 * must issue a media check command through the UI to bring the database up-to-date.
 */
@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.ExcessiveMethodLength", "PMD.OneDeclarationPerLine", "PMD.SwitchStmtsShouldHaveDefault", "PMD.EmptyIfStmt", "PMD.SimplifyBooleanReturns", "PMD.CollapsibleIfStatements" })
public class MediaSyncer {

    private final Collection mCol;

    private final RemoteMediaServer mServer;

    private int mDownloadCount;

    // Needed to update progress to UI
    private final Connection mCon;

    public MediaSyncer(Collection col, RemoteMediaServer server, Connection con) {
        mCol = col;
        mServer = server;
        mCon = con;
    }

    // Returned string may be null. ConnectionResultType and Pair are not null
    public Pair<ConnectionResultType, String> sync() throws UnknownHttpResponseException, MediaSyncException {
        // of this class about this difference to the original.
        if (mCol.getMedia().needScan()) {
            if (!ListenerUtil.mutListener.listen(19950)) {
                mCon.publishProgress(R.string.sync_media_find);
            }
            if (!ListenerUtil.mutListener.listen(19951)) {
                mCol.log("findChanges");
            }
            try {
                if (!ListenerUtil.mutListener.listen(19952)) {
                    mCol.getMedia().findChanges();
                }
            } catch (SQLException ignored) {
                return new Pair<>(CORRUPT, null);
            }
        }
        // begin session and check if in sync
        int lastUsn = mCol.getMedia().lastUsn();
        JSONObject ret = mServer.begin();
        int srvUsn = ret.getInt("usn");
        if ((ListenerUtil.mutListener.listen(19958) ? (((ListenerUtil.mutListener.listen(19957) ? (lastUsn >= srvUsn) : (ListenerUtil.mutListener.listen(19956) ? (lastUsn <= srvUsn) : (ListenerUtil.mutListener.listen(19955) ? (lastUsn > srvUsn) : (ListenerUtil.mutListener.listen(19954) ? (lastUsn < srvUsn) : (ListenerUtil.mutListener.listen(19953) ? (lastUsn != srvUsn) : (lastUsn == srvUsn))))))) || !(mCol.getMedia().haveDirty())) : (((ListenerUtil.mutListener.listen(19957) ? (lastUsn >= srvUsn) : (ListenerUtil.mutListener.listen(19956) ? (lastUsn <= srvUsn) : (ListenerUtil.mutListener.listen(19955) ? (lastUsn > srvUsn) : (ListenerUtil.mutListener.listen(19954) ? (lastUsn < srvUsn) : (ListenerUtil.mutListener.listen(19953) ? (lastUsn != srvUsn) : (lastUsn == srvUsn))))))) && !(mCol.getMedia().haveDirty())))) {
            return new Pair<>(NO_CHANGES, null);
        }
        if (!ListenerUtil.mutListener.listen(19959)) {
            // loop through and process changes from server
            mCol.log("last local usn is " + lastUsn);
        }
        if (!ListenerUtil.mutListener.listen(19960)) {
            mDownloadCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(20002)) {
            {
                long _loopCounter397 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter397", ++_loopCounter397);
                    if (!ListenerUtil.mutListener.listen(19962)) {
                        // Allow cancellation (note: media sync has no finish command, so just throw)
                        if (Connection.getIsCancelled()) {
                            if (!ListenerUtil.mutListener.listen(19961)) {
                                Timber.i("Sync was cancelled");
                            }
                            throw new RuntimeException(USER_ABORTED_SYNC.toString());
                        }
                    }
                    JSONArray data = mServer.mediaChanges(lastUsn);
                    if (!ListenerUtil.mutListener.listen(19963)) {
                        mCol.log("mediaChanges resp count: ", data.length());
                    }
                    if (!ListenerUtil.mutListener.listen(19964)) {
                        if (data.length() == 0) {
                            break;
                        }
                    }
                    List<String> need = new ArrayList<>(data.length());
                    if (!ListenerUtil.mutListener.listen(19969)) {
                        lastUsn = data.getJSONArray((ListenerUtil.mutListener.listen(19968) ? (data.length() % 1) : (ListenerUtil.mutListener.listen(19967) ? (data.length() / 1) : (ListenerUtil.mutListener.listen(19966) ? (data.length() * 1) : (ListenerUtil.mutListener.listen(19965) ? (data.length() + 1) : (data.length() - 1)))))).getInt(1);
                    }
                    if (!ListenerUtil.mutListener.listen(19998)) {
                        {
                            long _loopCounter396 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(19997) ? (i >= data.length()) : (ListenerUtil.mutListener.listen(19996) ? (i <= data.length()) : (ListenerUtil.mutListener.listen(19995) ? (i > data.length()) : (ListenerUtil.mutListener.listen(19994) ? (i != data.length()) : (ListenerUtil.mutListener.listen(19993) ? (i == data.length()) : (i < data.length())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter396", ++_loopCounter396);
                                if (!ListenerUtil.mutListener.listen(19971)) {
                                    // Allow cancellation (note: media sync has no finish command, so just throw)
                                    if (Connection.getIsCancelled()) {
                                        if (!ListenerUtil.mutListener.listen(19970)) {
                                            Timber.i("Sync was cancelled");
                                        }
                                        throw new RuntimeException(USER_ABORTED_SYNC.toString());
                                    }
                                }
                                String fname = data.getJSONArray(i).getString(0);
                                int rusn = data.getJSONArray(i).getInt(1);
                                String rsum = null;
                                if (!ListenerUtil.mutListener.listen(19973)) {
                                    if (!data.getJSONArray(i).isNull(2)) {
                                        if (!ListenerUtil.mutListener.listen(19972)) {
                                            // return `"null"` as a string
                                            rsum = data.getJSONArray(i).optString(2);
                                        }
                                    }
                                }
                                Pair<String, Integer> info = mCol.getMedia().syncInfo(fname);
                                String lsum = info.first;
                                int ldirty = info.second;
                                if (!ListenerUtil.mutListener.listen(19974)) {
                                    mCol.log(String.format(Locale.US, "check: lsum=%s rsum=%s ldirty=%d rusn=%d fname=%s", TextUtils.isEmpty(lsum) ? "" : lsum.subSequence(0, 4), TextUtils.isEmpty(rsum) ? "" : rsum.subSequence(0, 4), ldirty, rusn, fname));
                                }
                                if (!ListenerUtil.mutListener.listen(19992)) {
                                    if (!TextUtils.isEmpty(rsum)) {
                                        if (!ListenerUtil.mutListener.listen(19990)) {
                                            // added/changed remotely
                                            if ((ListenerUtil.mutListener.listen(19986) ? (TextUtils.isEmpty(lsum) && !lsum.equals(rsum)) : (TextUtils.isEmpty(lsum) || !lsum.equals(rsum)))) {
                                                if (!ListenerUtil.mutListener.listen(19988)) {
                                                    mCol.log("will fetch");
                                                }
                                                if (!ListenerUtil.mutListener.listen(19989)) {
                                                    need.add(fname);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(19987)) {
                                                    mCol.log("have same already");
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(19991)) {
                                            mCol.getMedia().markClean(Collections.singletonList(fname));
                                        }
                                    } else if (!TextUtils.isEmpty(lsum)) {
                                        if (!ListenerUtil.mutListener.listen(19985)) {
                                            // deleted remotely
                                            if ((ListenerUtil.mutListener.listen(19981) ? (ldirty >= 0) : (ListenerUtil.mutListener.listen(19980) ? (ldirty <= 0) : (ListenerUtil.mutListener.listen(19979) ? (ldirty > 0) : (ListenerUtil.mutListener.listen(19978) ? (ldirty < 0) : (ListenerUtil.mutListener.listen(19977) ? (ldirty != 0) : (ldirty == 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(19983)) {
                                                    mCol.log("delete local");
                                                }
                                                if (!ListenerUtil.mutListener.listen(19984)) {
                                                    mCol.getMedia().syncDelete(fname);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(19982)) {
                                                    // conflict: local add overrides remote delete
                                                    mCol.log("conflict; will send");
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(19975)) {
                                            // deleted both sides
                                            mCol.log("both sides deleted");
                                        }
                                        if (!ListenerUtil.mutListener.listen(19976)) {
                                            mCol.getMedia().markClean(Collections.singletonList(fname));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(19999)) {
                        _downloadFiles(need);
                    }
                    if (!ListenerUtil.mutListener.listen(20000)) {
                        mCol.log("update last usn to " + lastUsn);
                    }
                    if (!ListenerUtil.mutListener.listen(20001)) {
                        // commits
                        mCol.getMedia().setLastUsn(lastUsn);
                    }
                }
            }
        }
        boolean updateConflict = false;
        int toSend = mCol.getMedia().dirtyCount();
        if (!ListenerUtil.mutListener.listen(20030)) {
            {
                long _loopCounter398 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter398", ++_loopCounter398);
                    Pair<File, List<String>> changesZip = mCol.getMedia().mediaChangesZip();
                    File zip = changesZip.first;
                    try {
                        List<String> fnames = changesZip.second;
                        if (!ListenerUtil.mutListener.listen(20009)) {
                            if ((ListenerUtil.mutListener.listen(20008) ? (fnames.size() >= 0) : (ListenerUtil.mutListener.listen(20007) ? (fnames.size() <= 0) : (ListenerUtil.mutListener.listen(20006) ? (fnames.size() > 0) : (ListenerUtil.mutListener.listen(20005) ? (fnames.size() < 0) : (ListenerUtil.mutListener.listen(20004) ? (fnames.size() != 0) : (fnames.size() == 0))))))) {
                                break;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(20010)) {
                            mCon.publishProgress(String.format(AnkiDroidApp.getAppResources().getString(R.string.sync_media_changes_count), toSend));
                        }
                        JSONArray changes = mServer.uploadChanges(zip);
                        int processedCnt = changes.getInt(0);
                        int serverLastUsn = changes.getInt(1);
                        if (!ListenerUtil.mutListener.listen(20011)) {
                            mCol.getMedia().markClean(fnames.subList(0, processedCnt));
                        }
                        if (!ListenerUtil.mutListener.listen(20012)) {
                            mCol.log(String.format(Locale.US, "processed %d, serverUsn %d, clientUsn %d", processedCnt, serverLastUsn, lastUsn));
                        }
                        if (!ListenerUtil.mutListener.listen(20028)) {
                            if ((ListenerUtil.mutListener.listen(20021) ? ((ListenerUtil.mutListener.listen(20016) ? (serverLastUsn % processedCnt) : (ListenerUtil.mutListener.listen(20015) ? (serverLastUsn / processedCnt) : (ListenerUtil.mutListener.listen(20014) ? (serverLastUsn * processedCnt) : (ListenerUtil.mutListener.listen(20013) ? (serverLastUsn + processedCnt) : (serverLastUsn - processedCnt))))) >= lastUsn) : (ListenerUtil.mutListener.listen(20020) ? ((ListenerUtil.mutListener.listen(20016) ? (serverLastUsn % processedCnt) : (ListenerUtil.mutListener.listen(20015) ? (serverLastUsn / processedCnt) : (ListenerUtil.mutListener.listen(20014) ? (serverLastUsn * processedCnt) : (ListenerUtil.mutListener.listen(20013) ? (serverLastUsn + processedCnt) : (serverLastUsn - processedCnt))))) <= lastUsn) : (ListenerUtil.mutListener.listen(20019) ? ((ListenerUtil.mutListener.listen(20016) ? (serverLastUsn % processedCnt) : (ListenerUtil.mutListener.listen(20015) ? (serverLastUsn / processedCnt) : (ListenerUtil.mutListener.listen(20014) ? (serverLastUsn * processedCnt) : (ListenerUtil.mutListener.listen(20013) ? (serverLastUsn + processedCnt) : (serverLastUsn - processedCnt))))) > lastUsn) : (ListenerUtil.mutListener.listen(20018) ? ((ListenerUtil.mutListener.listen(20016) ? (serverLastUsn % processedCnt) : (ListenerUtil.mutListener.listen(20015) ? (serverLastUsn / processedCnt) : (ListenerUtil.mutListener.listen(20014) ? (serverLastUsn * processedCnt) : (ListenerUtil.mutListener.listen(20013) ? (serverLastUsn + processedCnt) : (serverLastUsn - processedCnt))))) < lastUsn) : (ListenerUtil.mutListener.listen(20017) ? ((ListenerUtil.mutListener.listen(20016) ? (serverLastUsn % processedCnt) : (ListenerUtil.mutListener.listen(20015) ? (serverLastUsn / processedCnt) : (ListenerUtil.mutListener.listen(20014) ? (serverLastUsn * processedCnt) : (ListenerUtil.mutListener.listen(20013) ? (serverLastUsn + processedCnt) : (serverLastUsn - processedCnt))))) != lastUsn) : ((ListenerUtil.mutListener.listen(20016) ? (serverLastUsn % processedCnt) : (ListenerUtil.mutListener.listen(20015) ? (serverLastUsn / processedCnt) : (ListenerUtil.mutListener.listen(20014) ? (serverLastUsn * processedCnt) : (ListenerUtil.mutListener.listen(20013) ? (serverLastUsn + processedCnt) : (serverLastUsn - processedCnt))))) == lastUsn))))))) {
                                if (!ListenerUtil.mutListener.listen(20025)) {
                                    mCol.log("lastUsn in sync, updating local");
                                }
                                if (!ListenerUtil.mutListener.listen(20026)) {
                                    lastUsn = serverLastUsn;
                                }
                                if (!ListenerUtil.mutListener.listen(20027)) {
                                    // commits
                                    mCol.getMedia().setLastUsn(serverLastUsn);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(20022)) {
                                    mCol.log("concurrent update, skipping usn update");
                                }
                                if (!ListenerUtil.mutListener.listen(20023)) {
                                    // commit for markClean
                                    mCol.getMedia().getDb().commit();
                                }
                                if (!ListenerUtil.mutListener.listen(20024)) {
                                    updateConflict = true;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(20029)) {
                            toSend -= processedCnt;
                        }
                    } finally {
                        if (!ListenerUtil.mutListener.listen(20003)) {
                            zip.delete();
                        }
                    }
                }
            }
        }
        if (updateConflict) {
            if (!ListenerUtil.mutListener.listen(20031)) {
                mCol.log("restart sync due to concurrent update");
            }
            return sync();
        }
        int lcnt = mCol.getMedia().mediacount();
        String sRet = mServer.mediaSanity(lcnt);
        if ("OK".equals(sRet)) {
            return new Pair<>(OK, null);
        } else {
            if (!ListenerUtil.mutListener.listen(20032)) {
                mCol.getMedia().forceResync();
            }
            return new Pair<>(ARBITRARY_STRING, sRet);
        }
    }

    private void _downloadFiles(List<String> fnames) {
        if (!ListenerUtil.mutListener.listen(20033)) {
            mCol.log(fnames.size() + " files to fetch");
        }
        if (!ListenerUtil.mutListener.listen(20052)) {
            {
                long _loopCounter399 = 0;
                while ((ListenerUtil.mutListener.listen(20051) ? (fnames.size() >= 0) : (ListenerUtil.mutListener.listen(20050) ? (fnames.size() <= 0) : (ListenerUtil.mutListener.listen(20049) ? (fnames.size() < 0) : (ListenerUtil.mutListener.listen(20048) ? (fnames.size() != 0) : (ListenerUtil.mutListener.listen(20047) ? (fnames.size() == 0) : (fnames.size() > 0))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter399", ++_loopCounter399);
                    try {
                        List<String> top = fnames.subList(0, Math.min(fnames.size(), Consts.SYNC_ZIP_COUNT));
                        if (!ListenerUtil.mutListener.listen(20035)) {
                            mCol.log("fetch " + top);
                        }
                        ZipFile zipData = mServer.downloadFiles(top);
                        int cnt = mCol.getMedia().addFilesFromZip(zipData);
                        if (!ListenerUtil.mutListener.listen(20036)) {
                            mDownloadCount += cnt;
                        }
                        if (!ListenerUtil.mutListener.listen(20037)) {
                            mCol.log("received " + cnt + " files");
                        }
                        if (!ListenerUtil.mutListener.listen(20045)) {
                            // if we've reached the end and clear the fnames list manually.
                            if ((ListenerUtil.mutListener.listen(20042) ? (cnt >= fnames.size()) : (ListenerUtil.mutListener.listen(20041) ? (cnt <= fnames.size()) : (ListenerUtil.mutListener.listen(20040) ? (cnt > fnames.size()) : (ListenerUtil.mutListener.listen(20039) ? (cnt < fnames.size()) : (ListenerUtil.mutListener.listen(20038) ? (cnt != fnames.size()) : (cnt == fnames.size()))))))) {
                                if (!ListenerUtil.mutListener.listen(20044)) {
                                    fnames.clear();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(20043)) {
                                    fnames = fnames.subList(cnt, fnames.size());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(20046)) {
                            mCon.publishProgress(String.format(AnkiDroidApp.getAppResources().getString(R.string.sync_media_downloaded_count), mDownloadCount));
                        }
                    } catch (IOException | UnknownHttpResponseException e) {
                        if (!ListenerUtil.mutListener.listen(20034)) {
                            Timber.e(e, "Error downloading media files");
                        }
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
