/*
 * Copyright (C) 2016 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.joulespersecond.seattlebusbot;

import org.onebusaway.android.util.ReminderUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Responsible for receiving Intents from the Android platform related to scheduled reminders.
 *
 * Used to provide backwards compatibility with reminders created with v1.x - see Issue #558.
 */
@Deprecated
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "LegacyAlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(12797)) {
            Log.d(TAG, "Received legacy alarm");
        }
        if (!ListenerUtil.mutListener.listen(12798)) {
            ReminderUtils.startReminderService(context, intent, TAG);
        }
    }
}
