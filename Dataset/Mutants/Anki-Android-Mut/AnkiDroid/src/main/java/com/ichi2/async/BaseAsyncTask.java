/**
 * *************************************************************************************
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
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
package com.ichi2.async;

import android.os.AsyncTask;
import com.ichi2.utils.MethodLogger;
import com.ichi2.utils.Threads;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static com.ichi2.anki.AnkiDroidApp.sendExceptionReport;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements ProgressSenderAndCancelListener<Progress> {

    /**
     * Set this to {@code true} to enable detailed debugging for this class.
     */
    private static final boolean DEBUG = false;

    public BaseAsyncTask() {
        if (!ListenerUtil.mutListener.listen(12585)) {
            if (DEBUG) {
                if (!ListenerUtil.mutListener.listen(12584)) {
                    MethodLogger.log();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12586)) {
            Threads.checkMainThread();
        }
    }

    @Override
    protected void onPreExecute() {
        if (!ListenerUtil.mutListener.listen(12588)) {
            if (DEBUG) {
                if (!ListenerUtil.mutListener.listen(12587)) {
                    MethodLogger.log();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12589)) {
            Threads.checkMainThread();
        }
        if (!ListenerUtil.mutListener.listen(12590)) {
            super.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        if (!ListenerUtil.mutListener.listen(12592)) {
            if (DEBUG) {
                if (!ListenerUtil.mutListener.listen(12591)) {
                    MethodLogger.log();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12594)) {
            if (isCancelled()) {
                if (!ListenerUtil.mutListener.listen(12593)) {
                    sendExceptionReport("onPostExecute called with task cancelled. This should never occur !", "BaseAsyncTask - onPostExecute");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12595)) {
            Threads.checkMainThread();
        }
        if (!ListenerUtil.mutListener.listen(12596)) {
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        if (!ListenerUtil.mutListener.listen(12598)) {
            if (DEBUG) {
                if (!ListenerUtil.mutListener.listen(12597)) {
                    MethodLogger.log();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12599)) {
            Threads.checkMainThread();
        }
        if (!ListenerUtil.mutListener.listen(12600)) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void onCancelled() {
        if (!ListenerUtil.mutListener.listen(12602)) {
            if (DEBUG) {
                if (!ListenerUtil.mutListener.listen(12601)) {
                    MethodLogger.log();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12603)) {
            Threads.checkMainThread();
        }
        if (!ListenerUtil.mutListener.listen(12604)) {
            super.onCancelled();
        }
    }

    @Override
    protected Result doInBackground(Params... arg0) {
        if (!ListenerUtil.mutListener.listen(12606)) {
            if (DEBUG) {
                if (!ListenerUtil.mutListener.listen(12605)) {
                    MethodLogger.log();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12607)) {
            Threads.checkNotMainThread();
        }
        return null;
    }

    public void doProgress(@Nullable Progress value) {
        if (!ListenerUtil.mutListener.listen(12608)) {
            publishProgress(value);
        }
    }
}
