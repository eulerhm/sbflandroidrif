/**
 * ************************************************************************************
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
package com.ichi2.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.MetaDB;
import com.ichi2.anki.services.NotificationService;
import com.ichi2.async.BaseAsyncTask;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.sched.Counts;
import com.ichi2.libanki.sched.DeckDueTreeNode;
import java.util.List;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The status of the widget.
 */
public final class WidgetStatus {

    private static boolean sSmallWidgetEnabled = false;

    private static AsyncTask<Context, Void, Context> sUpdateDeckStatusAsyncTask;

    /**
     * This class should not be instantiated.
     */
    private WidgetStatus() {
    }

    /**
     * Request the widget to update its status.
     * TODO Mike - we can reduce battery usage by widget users by removing updatePeriodMillis from metadata
     *             and replacing it with an alarm we set so device doesn't wake to update the widget, see:
     *             https://developer.android.com/guide/topics/appwidgets/#MetaData
     */
    public static void update(Context context) {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(context);
        if (!ListenerUtil.mutListener.listen(26278)) {
            sSmallWidgetEnabled = preferences.getBoolean("widgetSmallEnabled", false);
        }
        boolean notificationEnabled = (ListenerUtil.mutListener.listen(26283) ? (Integer.parseInt(preferences.getString("minimumCardsDueForNotification", "1000001")) >= 1000000) : (ListenerUtil.mutListener.listen(26282) ? (Integer.parseInt(preferences.getString("minimumCardsDueForNotification", "1000001")) <= 1000000) : (ListenerUtil.mutListener.listen(26281) ? (Integer.parseInt(preferences.getString("minimumCardsDueForNotification", "1000001")) > 1000000) : (ListenerUtil.mutListener.listen(26280) ? (Integer.parseInt(preferences.getString("minimumCardsDueForNotification", "1000001")) != 1000000) : (ListenerUtil.mutListener.listen(26279) ? (Integer.parseInt(preferences.getString("minimumCardsDueForNotification", "1000001")) == 1000000) : (Integer.parseInt(preferences.getString("minimumCardsDueForNotification", "1000001")) < 1000000))))));
        boolean canExecuteTask = ((ListenerUtil.mutListener.listen(26284) ? ((sUpdateDeckStatusAsyncTask == null) && (sUpdateDeckStatusAsyncTask.getStatus() == AsyncTask.Status.FINISHED)) : ((sUpdateDeckStatusAsyncTask == null) || (sUpdateDeckStatusAsyncTask.getStatus() == AsyncTask.Status.FINISHED))));
        if (!ListenerUtil.mutListener.listen(26291)) {
            if ((ListenerUtil.mutListener.listen(26286) ? (((ListenerUtil.mutListener.listen(26285) ? (sSmallWidgetEnabled && notificationEnabled) : (sSmallWidgetEnabled || notificationEnabled))) || canExecuteTask) : (((ListenerUtil.mutListener.listen(26285) ? (sSmallWidgetEnabled && notificationEnabled) : (sSmallWidgetEnabled || notificationEnabled))) && canExecuteTask))) {
                if (!ListenerUtil.mutListener.listen(26288)) {
                    Timber.d("WidgetStatus.update(): updating");
                }
                if (!ListenerUtil.mutListener.listen(26289)) {
                    sUpdateDeckStatusAsyncTask = new UpdateDeckStatusAsyncTask();
                }
                if (!ListenerUtil.mutListener.listen(26290)) {
                    sUpdateDeckStatusAsyncTask.execute(context);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26287)) {
                    Timber.d("WidgetStatus.update(): already running or not enabled");
                }
            }
        }
    }

    /**
     * Returns the status of each of the decks.
     */
    public static int[] fetchSmall(Context context) {
        return MetaDB.getWidgetSmallStatus(context);
    }

    public static int fetchDue(Context context) {
        return MetaDB.getNotificationStatus(context);
    }

    private static class UpdateDeckStatusAsyncTask extends BaseAsyncTask<Context, Void, Context> {

        // due, eta
        private static Pair<Integer, Integer> sSmallWidgetStatus = new Pair<>(0, 0);

        @Override
        protected Context doInBackground(Context... params) {
            if (!ListenerUtil.mutListener.listen(26292)) {
                super.doInBackground(params);
            }
            if (!ListenerUtil.mutListener.listen(26293)) {
                Timber.d("WidgetStatus.UpdateDeckStatusAsyncTask.doInBackground()");
            }
            Context context = params[0];
            if (!ListenerUtil.mutListener.listen(26294)) {
                if (!AnkiDroidApp.isSdCardMounted()) {
                    return context;
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(26296)) {
                    updateCounts(context);
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(26295)) {
                    Timber.e(e, "Could not update widget");
                }
            }
            return context;
        }

        @Override
        protected void onPostExecute(Context context) {
            if (!ListenerUtil.mutListener.listen(26297)) {
                super.onPostExecute(context);
            }
            if (!ListenerUtil.mutListener.listen(26298)) {
                Timber.d("WidgetStatus.UpdateDeckStatusAsyncTask.onPostExecute()");
            }
            if (!ListenerUtil.mutListener.listen(26299)) {
                MetaDB.storeSmallWidgetStatus(context, sSmallWidgetStatus);
            }
            if (!ListenerUtil.mutListener.listen(26301)) {
                if (sSmallWidgetEnabled) {
                    if (!ListenerUtil.mutListener.listen(26300)) {
                        new AnkiDroidWidgetSmall.UpdateService().doUpdate(context);
                    }
                }
            }
            Intent intent = new Intent(NotificationService.INTENT_ACTION);
            Context appContext = context.getApplicationContext();
            if (!ListenerUtil.mutListener.listen(26302)) {
                LocalBroadcastManager.getInstance(appContext).sendBroadcast(intent);
            }
        }

        private void updateCounts(Context context) {
            Counts total = new Counts();
            Collection col = CollectionHelper.getInstance().getCol(context);
            if (!ListenerUtil.mutListener.listen(26303)) {
                // Ensure queues are reset if we cross over to the next day.
                col.getSched()._checkDay();
            }
            // Only count the top-level decks in the total
            List<DeckDueTreeNode> nodes = col.getSched().deckDueTree();
            if (!ListenerUtil.mutListener.listen(26307)) {
                {
                    long _loopCounter702 = 0;
                    for (DeckDueTreeNode node : nodes) {
                        ListenerUtil.loopListener.listen("_loopCounter702", ++_loopCounter702);
                        if (!ListenerUtil.mutListener.listen(26304)) {
                            total.addNew(node.getNewCount());
                        }
                        if (!ListenerUtil.mutListener.listen(26305)) {
                            total.addLrn(node.getLrnCount());
                        }
                        if (!ListenerUtil.mutListener.listen(26306)) {
                            total.addRev(node.getRevCount());
                        }
                    }
                }
            }
            int eta = col.getSched().eta(total, false);
            if (!ListenerUtil.mutListener.listen(26308)) {
                sSmallWidgetStatus = new Pair<>(total.count(), eta);
            }
        }
    }
}
