/**
 * ************************************************************************************
 *  Copyright (c) 2012 Norbert Nagold <norbert.nagold@gmail.com>                         *
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

import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.util.Pair;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.exception.UnknownHttpResponseException;
import com.ichi2.async.Connection;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.DB;
import com.ichi2.libanki.Utils;
import com.ichi2.utils.VersionUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import androidx.annotation.NonNull;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;
import static com.ichi2.libanki.sync.Syncer.ConnectionResultType.*;
import static com.ichi2.libanki.sync.Syncer.ConnectionResultType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.NPathComplexity" })
public class FullSyncer extends HttpSyncer {

    private Collection mCol;

    private final Connection mCon;

    public FullSyncer(Collection col, String hkey, Connection con, HostNum hostNum) {
        super(hkey, con, hostNum);
        if (!ListenerUtil.mutListener.listen(19744)) {
            mPostVars = new HashMap<>(2);
        }
        if (!ListenerUtil.mutListener.listen(19745)) {
            mPostVars.put("k", hkey);
        }
        if (!ListenerUtil.mutListener.listen(19746)) {
            mPostVars.put("v", String.format(Locale.US, "ankidroid,%s,%s", VersionUtils.getPkgVersionName(), Utils.platDesc()));
        }
        if (!ListenerUtil.mutListener.listen(19747)) {
            mCol = col;
        }
        mCon = con;
    }

    @NonNull
    public ConnectionResultType download() throws UnknownHttpResponseException {
        InputStream cont;
        ResponseBody body = null;
        try {
            Response ret = super.req("download");
            if ((ListenerUtil.mutListener.listen(19750) ? (ret == null && ret.body() == null) : (ret == null || ret.body() == null))) {
                return null;
            }
            if (!ListenerUtil.mutListener.listen(19751)) {
                body = ret.body();
            }
            cont = body.byteStream();
        } catch (IllegalArgumentException e1) {
            if (!ListenerUtil.mutListener.listen(19749)) {
                if (body != null) {
                    if (!ListenerUtil.mutListener.listen(19748)) {
                        body.close();
                    }
                }
            }
            throw new RuntimeException(e1);
        }
        String path;
        if (mCol != null) {
            if (!ListenerUtil.mutListener.listen(19753)) {
                Timber.i("Closing collection for full sync");
            }
            // Usual case where collection is non-null
            path = mCol.getPath();
            if (!ListenerUtil.mutListener.listen(19754)) {
                mCol.close();
            }
            if (!ListenerUtil.mutListener.listen(19755)) {
                mCol = null;
            }
        } else {
            if (!ListenerUtil.mutListener.listen(19752)) {
                // Allow for case where collection is completely unreadable
                Timber.w("Collection was unexpectedly null when doing full sync download");
            }
            path = CollectionHelper.getCollectionPath(AnkiDroidApp.getInstance());
        }
        String tpath = path + ".tmp";
        try {
            if (!ListenerUtil.mutListener.listen(19759)) {
                super.writeToFile(cont, tpath);
            }
            if (!ListenerUtil.mutListener.listen(19760)) {
                Timber.d("Full Sync - Downloaded temp file");
            }
            FileInputStream fis = new FileInputStream(tpath);
            if ("upgradeRequired".equals(super.stream2String(fis, 15))) {
                if (!ListenerUtil.mutListener.listen(19761)) {
                    Timber.w("Full Sync - 'Upgrade Required' message received");
                }
                return UPGRADE_REQUIRED;
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(19756)) {
                Timber.e(e, "Failed to create temp file when downloading collection.");
            }
            throw new RuntimeException(e);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(19757)) {
                Timber.e(e, "Full sync failed to download collection.");
            }
            return SD_ACCESS_ERROR;
        } finally {
            if (!ListenerUtil.mutListener.listen(19758)) {
                body.close();
            }
        }
        if (!ListenerUtil.mutListener.listen(19762)) {
            // check the received file is ok
            mCon.publishProgress(R.string.sync_check_download_file);
        }
        DB tempDb = null;
        try {
            if (!ListenerUtil.mutListener.listen(19766)) {
                tempDb = new DB(tpath);
            }
            if (!"ok".equalsIgnoreCase(tempDb.queryString("PRAGMA integrity_check"))) {
                if (!ListenerUtil.mutListener.listen(19767)) {
                    Timber.e("Full sync - downloaded file corrupt");
                }
                return REMOTE_DB_ERROR;
            }
        } catch (SQLiteDatabaseCorruptException e) {
            if (!ListenerUtil.mutListener.listen(19763)) {
                Timber.e("Full sync - downloaded file corrupt");
            }
            return REMOTE_DB_ERROR;
        } finally {
            if (!ListenerUtil.mutListener.listen(19765)) {
                if (tempDb != null) {
                    if (!ListenerUtil.mutListener.listen(19764)) {
                        tempDb.close();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19768)) {
            Timber.d("Full Sync: Downloaded file was not corrupt");
        }
        // overwrite existing collection
        File newFile = new File(tpath);
        if (newFile.renameTo(new File(path))) {
            if (!ListenerUtil.mutListener.listen(19770)) {
                Timber.i("Full Sync Success: Overwritten collection with downloaded file");
            }
            return SUCCESS;
        } else {
            if (!ListenerUtil.mutListener.listen(19769)) {
                Timber.w("Full Sync: Error overwriting collection with downloaded file");
            }
            return OVERWRITE_ERROR;
        }
    }

    public Pair<ConnectionResultType, Object[]> upload() throws UnknownHttpResponseException {
        if (!ListenerUtil.mutListener.listen(19771)) {
            // make sure it's ok before we try to upload
            mCon.publishProgress(R.string.sync_check_upload_file);
        }
        if (!"ok".equalsIgnoreCase(mCol.getDb().queryString("PRAGMA integrity_check"))) {
            return new Pair<>(DB_ERROR, null);
        }
        if (!mCol.basicCheck()) {
            return new Pair<>(DB_ERROR, null);
        }
        if (!ListenerUtil.mutListener.listen(19772)) {
            // apply some adjustments, then upload
            mCol.beforeUpload();
        }
        String filePath = mCol.getPath();
        Response ret;
        if (!ListenerUtil.mutListener.listen(19773)) {
            mCon.publishProgress(R.string.sync_uploading_message);
        }
        try {
            ret = super.req("upload", new FileInputStream(filePath));
            if ((ListenerUtil.mutListener.listen(19774) ? (ret == null && ret.body() == null) : (ret == null || ret.body() == null))) {
                return null;
            }
            int status = ret.code();
            if ((ListenerUtil.mutListener.listen(19779) ? (status >= 200) : (ListenerUtil.mutListener.listen(19778) ? (status <= 200) : (ListenerUtil.mutListener.listen(19777) ? (status > 200) : (ListenerUtil.mutListener.listen(19776) ? (status < 200) : (ListenerUtil.mutListener.listen(19775) ? (status == 200) : (status != 200))))))) {
                // error occurred
                return new Pair<>(ERROR, new Object[] { status, ret.message() });
            } else {
                return new Pair<>(ARBITRARY_STRING, new Object[] { ret.body().string() });
            }
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
