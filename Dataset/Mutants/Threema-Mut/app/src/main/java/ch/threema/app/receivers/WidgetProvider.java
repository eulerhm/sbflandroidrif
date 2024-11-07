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
package ch.threema.app.receivers;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.RemoteViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.activities.RecipientListBaseActivity;
import ch.threema.app.services.WidgetService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WidgetProvider extends AppWidgetProvider {

    private static final Logger logger = LoggerFactory.getLogger(WidgetProvider.class);

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (!ListenerUtil.mutListener.listen(34481)) {
            logger.debug("onUpdate");
        }
        final String ACTION_OPEN = context.getPackageName() + ".ACTION_OPEN";
        if (!ListenerUtil.mutListener.listen(34500)) {
            {
                long _loopCounter245 = 0;
                // Perform this loop procedure for each App Widget that belongs to this provider
                for (int i = 0; (ListenerUtil.mutListener.listen(34499) ? (i >= appWidgetIds.length) : (ListenerUtil.mutListener.listen(34498) ? (i <= appWidgetIds.length) : (ListenerUtil.mutListener.listen(34497) ? (i > appWidgetIds.length) : (ListenerUtil.mutListener.listen(34496) ? (i != appWidgetIds.length) : (ListenerUtil.mutListener.listen(34495) ? (i == appWidgetIds.length) : (i < appWidgetIds.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter245", ++_loopCounter245);
                    int appWidgetId = appWidgetIds[i];
                    Intent intent = new Intent(context, RecipientListBaseActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                    Intent titleIntent = new Intent(context, HomeActivity.class);
                    PendingIntent titlePendingIntent = PendingIntent.getActivity(context, 0, titleIntent, 0);
                    // to the button
                    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_messages);
                    if (!ListenerUtil.mutListener.listen(34482)) {
                        views.setOnClickPendingIntent(R.id.widget_edit, pendingIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(34483)) {
                        views.setOnClickPendingIntent(R.id.widget_title, titlePendingIntent);
                    }
                    // This is how you populate the data.
                    Intent svcIntent = new Intent(context, WidgetService.class);
                    if (!ListenerUtil.mutListener.listen(34484)) {
                        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                    }
                    if (!ListenerUtil.mutListener.listen(34485)) {
                        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
                    }
                    if (!ListenerUtil.mutListener.listen(34486)) {
                        logger.debug("setRemoteAdapter");
                    }
                    if (!ListenerUtil.mutListener.listen(34487)) {
                        views.setRemoteAdapter(R.id.widget_list, svcIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(34488)) {
                        // object above.
                        views.setEmptyView(R.id.widget_list, R.id.empty_view);
                    }
                    Intent itemIntent = new Intent(context, ComposeMessageActivity.class);
                    if (!ListenerUtil.mutListener.listen(34489)) {
                        itemIntent.setAction(ACTION_OPEN);
                    }
                    if (!ListenerUtil.mutListener.listen(34490)) {
                        itemIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                    }
                    if (!ListenerUtil.mutListener.listen(34491)) {
                        itemIntent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
                    }
                    if (!ListenerUtil.mutListener.listen(34492)) {
                        itemIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    PendingIntent itemPendingIntent = PendingIntent.getActivity(context, 0, itemIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    if (!ListenerUtil.mutListener.listen(34493)) {
                        views.setPendingIntentTemplate(R.id.widget_list, itemPendingIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(34494)) {
                        // Tell the AppWidgetManager to perform an update on the current app widget
                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(34501)) {
            super.onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }

    @Override
    public void onEnabled(Context context) {
        if (!ListenerUtil.mutListener.listen(34502)) {
            logger.debug("onEnabled");
        }
        if (!ListenerUtil.mutListener.listen(34503)) {
            super.onEnabled(context);
        }
    }
}
