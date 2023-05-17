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
package com.ichi2.anki.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import timber.log.Timber;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.IntentHandler;
import com.ichi2.anki.NotificationChannels;
import com.ichi2.anki.R;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.sched.DeckDueTreeNode;
import com.ichi2.utils.JSONObject;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReminderService extends BroadcastReceiver {

    public static final String EXTRA_DECK_OPTION_ID = "EXTRA_DECK_OPTION_ID";

    public static final String EXTRA_DECK_ID = "EXTRA_DECK_ID";

    /**
     * Cancelling all deck reminder. We used to use them, now we have deck option reminders.
     */
    private void cancelDeckReminder(Context context, Intent intent) {
        // 0 Is not a valid deck id.
        final long deckId = intent.getLongExtra(EXTRA_DECK_ID, 0);
        if (!ListenerUtil.mutListener.listen(3186)) {
            if ((ListenerUtil.mutListener.listen(3185) ? (deckId >= 0) : (ListenerUtil.mutListener.listen(3184) ? (deckId <= 0) : (ListenerUtil.mutListener.listen(3183) ? (deckId > 0) : (ListenerUtil.mutListener.listen(3182) ? (deckId < 0) : (ListenerUtil.mutListener.listen(3181) ? (deckId != 0) : (deckId == 0))))))) {
                return;
            }
        }
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final PendingIntent reminderIntent = PendingIntent.getBroadcast(context, (int) deckId, new Intent(context, ReminderService.class).putExtra(EXTRA_DECK_OPTION_ID, deckId), 0);
        if (!ListenerUtil.mutListener.listen(3187)) {
            alarmManager.cancel(reminderIntent);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(3188)) {
            cancelDeckReminder(context, intent);
        }
        // 0 is not a valid dconf id.
        final long dConfId = intent.getLongExtra(EXTRA_DECK_OPTION_ID, 0);
        if (!ListenerUtil.mutListener.listen(3195)) {
            if ((ListenerUtil.mutListener.listen(3193) ? (dConfId >= 0) : (ListenerUtil.mutListener.listen(3192) ? (dConfId <= 0) : (ListenerUtil.mutListener.listen(3191) ? (dConfId > 0) : (ListenerUtil.mutListener.listen(3190) ? (dConfId < 0) : (ListenerUtil.mutListener.listen(3189) ? (dConfId != 0) : (dConfId == 0))))))) {
                if (!ListenerUtil.mutListener.listen(3194)) {
                    Timber.w("onReceive - dConfId 0, returning");
                }
                return;
            }
        }
        CollectionHelper colHelper;
        Collection col;
        try {
            colHelper = CollectionHelper.getInstance();
            col = colHelper.getCol(context);
        } catch (Throwable t) {
            if (!ListenerUtil.mutListener.listen(3196)) {
                Timber.w("onReceive - unexpectedly unable to get collection. Returning.");
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(3199)) {
            if ((ListenerUtil.mutListener.listen(3197) ? (null == col && !colHelper.colIsOpen()) : (null == col || !colHelper.colIsOpen()))) {
                if (!ListenerUtil.mutListener.listen(3198)) {
                    Timber.w("onReceive - null or closed collection, unable to process reminders");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3201)) {
            if (col.getDecks().getConf(dConfId) == null) {
                final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                final PendingIntent reminderIntent = PendingIntent.getBroadcast(context, (int) dConfId, new Intent(context, ReminderService.class).putExtra(EXTRA_DECK_OPTION_ID, dConfId), 0);
                if (!ListenerUtil.mutListener.listen(3200)) {
                    alarmManager.cancel(reminderIntent);
                }
            }
        }
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (!ListenerUtil.mutListener.listen(3203)) {
            if (!notificationManager.areNotificationsEnabled()) {
                if (!ListenerUtil.mutListener.listen(3202)) {
                    Timber.v("onReceive - notifications disabled, returning");
                }
                return;
            }
        }
        List<DeckDueTreeNode> decksDue = getDeckOptionDue(col, dConfId, true);
        if (!ListenerUtil.mutListener.listen(3205)) {
            if (null == decksDue) {
                if (!ListenerUtil.mutListener.listen(3204)) {
                    Timber.v("onReceive - no decks due, returning");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3224)) {
            {
                long _loopCounter83 = 0;
                for (DeckDueTreeNode deckDue : decksDue) {
                    ListenerUtil.loopListener.listen("_loopCounter83", ++_loopCounter83);
                    long deckId = deckDue.getDid();
                    final int total = (ListenerUtil.mutListener.listen(3213) ? ((ListenerUtil.mutListener.listen(3209) ? (deckDue.getRevCount() % deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3208) ? (deckDue.getRevCount() / deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3207) ? (deckDue.getRevCount() * deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3206) ? (deckDue.getRevCount() - deckDue.getLrnCount()) : (deckDue.getRevCount() + deckDue.getLrnCount()))))) % deckDue.getNewCount()) : (ListenerUtil.mutListener.listen(3212) ? ((ListenerUtil.mutListener.listen(3209) ? (deckDue.getRevCount() % deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3208) ? (deckDue.getRevCount() / deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3207) ? (deckDue.getRevCount() * deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3206) ? (deckDue.getRevCount() - deckDue.getLrnCount()) : (deckDue.getRevCount() + deckDue.getLrnCount()))))) / deckDue.getNewCount()) : (ListenerUtil.mutListener.listen(3211) ? ((ListenerUtil.mutListener.listen(3209) ? (deckDue.getRevCount() % deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3208) ? (deckDue.getRevCount() / deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3207) ? (deckDue.getRevCount() * deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3206) ? (deckDue.getRevCount() - deckDue.getLrnCount()) : (deckDue.getRevCount() + deckDue.getLrnCount()))))) * deckDue.getNewCount()) : (ListenerUtil.mutListener.listen(3210) ? ((ListenerUtil.mutListener.listen(3209) ? (deckDue.getRevCount() % deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3208) ? (deckDue.getRevCount() / deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3207) ? (deckDue.getRevCount() * deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3206) ? (deckDue.getRevCount() - deckDue.getLrnCount()) : (deckDue.getRevCount() + deckDue.getLrnCount()))))) - deckDue.getNewCount()) : ((ListenerUtil.mutListener.listen(3209) ? (deckDue.getRevCount() % deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3208) ? (deckDue.getRevCount() / deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3207) ? (deckDue.getRevCount() * deckDue.getLrnCount()) : (ListenerUtil.mutListener.listen(3206) ? (deckDue.getRevCount() - deckDue.getLrnCount()) : (deckDue.getRevCount() + deckDue.getLrnCount()))))) + deckDue.getNewCount())))));
                    if (!ListenerUtil.mutListener.listen(3220)) {
                        if ((ListenerUtil.mutListener.listen(3218) ? (total >= 0) : (ListenerUtil.mutListener.listen(3217) ? (total > 0) : (ListenerUtil.mutListener.listen(3216) ? (total < 0) : (ListenerUtil.mutListener.listen(3215) ? (total != 0) : (ListenerUtil.mutListener.listen(3214) ? (total == 0) : (total <= 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3219)) {
                                Timber.v("onReceive - no cards due in deck %d", deckId);
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3221)) {
                        Timber.v("onReceive - deck '%s' due count %d", deckDue.getFullDeckName(), total);
                    }
                    final Notification notification = new NotificationCompat.Builder(context, NotificationChannels.getId(NotificationChannels.Channel.DECK_REMINDERS)).setCategory(NotificationCompat.CATEGORY_REMINDER).setContentTitle(context.getString(R.string.reminder_title)).setContentText(context.getResources().getQuantityString(R.plurals.reminder_text, total, deckDue.getFullDeckName(), total)).setSmallIcon(R.drawable.ic_stat_notify).setColor(ContextCompat.getColor(context, R.color.material_light_blue_700)).setContentIntent(PendingIntent.getActivity(context, (int) deckId, getReviewDeckIntent(context, deckId), PendingIntent.FLAG_UPDATE_CURRENT)).setAutoCancel(true).build();
                    if (!ListenerUtil.mutListener.listen(3222)) {
                        notificationManager.notify((int) deckId, notification);
                    }
                    if (!ListenerUtil.mutListener.listen(3223)) {
                        Timber.v("onReceive - notification state: %s", notification);
                    }
                }
            }
        }
    }

    @NonNull
    public static Intent getReviewDeckIntent(@NonNull Context context, long deckId) {
        return new Intent(context, IntentHandler.class).putExtra(EXTRA_DECK_ID, deckId);
    }

    // getDeckOptionDue information, will recur one time to workaround collection close if recur is true
    @Nullable
    private List<DeckDueTreeNode> getDeckOptionDue(Collection col, long dConfId, boolean recur) {
        if (!ListenerUtil.mutListener.listen(3227)) {
            // are working
            if ((ListenerUtil.mutListener.listen(3225) ? (col.getDb() == null && col.getDecks().getConf(dConfId) == null) : (col.getDb() == null || col.getDecks().getConf(dConfId) == null))) {
                if (!ListenerUtil.mutListener.listen(3226)) {
                    Timber.d("Deck option %s became unavailable while ReminderService was working. Ignoring", dConfId);
                }
                return null;
            }
        }
        List<DeckDueTreeNode> dues = col.getSched().deckDueTree();
        List<DeckDueTreeNode> decks = new ArrayList<>(dues.size());
        try {
            if (!ListenerUtil.mutListener.listen(3237)) {
                {
                    long _loopCounter84 = 0;
                    // This loop over top level deck only. No notification will ever occur for subdecks.
                    for (DeckDueTreeNode node : dues) {
                        ListenerUtil.loopListener.listen("_loopCounter84", ++_loopCounter84);
                        JSONObject deck = col.getDecks().get(node.getDid(), false);
                        if (!ListenerUtil.mutListener.listen(3236)) {
                            // Dynamic deck has no "conf", so are not added here.
                            if ((ListenerUtil.mutListener.listen(3234) ? (deck != null || deck.optLong("conf") == dConfId) : (deck != null && deck.optLong("conf") == dConfId))) {
                                if (!ListenerUtil.mutListener.listen(3235)) {
                                    decks.add(node);
                                }
                            }
                        }
                    }
                }
            }
            return decks;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(3233)) {
                if (recur) {
                    if (!ListenerUtil.mutListener.listen(3229)) {
                        Timber.i(e, "getDeckOptionDue exception - likely database re-initialization from auto-sync. Will re-try after sleep.");
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(3232)) {
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException ex) {
                        if (!ListenerUtil.mutListener.listen(3230)) {
                            Timber.i(ex, "Thread interrupted while waiting to retry. Likely unimportant.");
                        }
                        if (!ListenerUtil.mutListener.listen(3231)) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    return getDeckOptionDue(col, dConfId, false);
                } else {
                    if (!ListenerUtil.mutListener.listen(3228)) {
                        Timber.w(e, "Database unavailable while working. No re-tries left.");
                    }
                }
            }
        }
        return null;
    }
}
