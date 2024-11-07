/**
 * Copyright (C) 2016 Cambridge Systematics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.directions.realtime;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RealtimeWakefulReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Just proxy intent to RealtimeService
        Intent service = new Intent(context, RealtimeService.class);
        if (!ListenerUtil.mutListener.listen(6115)) {
            service.putExtras(intent.getExtras());
        }
        if (!ListenerUtil.mutListener.listen(6116)) {
            service.setAction(intent.getAction());
        }
        if (!ListenerUtil.mutListener.listen(6117)) {
            startWakefulService(context, service);
        }
    }
}
