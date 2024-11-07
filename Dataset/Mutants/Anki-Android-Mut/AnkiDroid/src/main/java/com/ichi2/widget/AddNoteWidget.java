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

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.ichi2.anki.NoteEditor;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.UsageAnalytics;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AddNoteWidget extends AppWidgetProvider {

    @Override
    public void onEnabled(Context context) {
        if (!ListenerUtil.mutListener.listen(26085)) {
            super.onEnabled(context);
        }
        if (!ListenerUtil.mutListener.listen(26086)) {
            UsageAnalytics.sendAnalyticsEvent(this.getClass().getSimpleName(), "enabled");
        }
    }

    @Override
    public void onDisabled(Context context) {
        if (!ListenerUtil.mutListener.listen(26087)) {
            super.onEnabled(context);
        }
        if (!ListenerUtil.mutListener.listen(26088)) {
            UsageAnalytics.sendAnalyticsEvent(this.getClass().getSimpleName(), "disabled");
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (!ListenerUtil.mutListener.listen(26089)) {
            super.onUpdate(context, appWidgetManager, appWidgetIds);
        }
        if (!ListenerUtil.mutListener.listen(26090)) {
            Timber.d("onUpdate");
        }
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_add_note);
        final Intent intent = new Intent(context, NoteEditor.class);
        if (!ListenerUtil.mutListener.listen(26091)) {
            intent.putExtra(NoteEditor.EXTRA_CALLER, NoteEditor.CALLER_DECKPICKER);
        }
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        if (!ListenerUtil.mutListener.listen(26092)) {
            remoteViews.setOnClickPendingIntent(R.id.widget_add_note_button, pendingIntent);
        }
        if (!ListenerUtil.mutListener.listen(26093)) {
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        }
    }
}
