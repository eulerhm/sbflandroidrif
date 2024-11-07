/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.content.Context;
import android.view.View;
import androidx.annotation.UiThread;
import ch.threema.app.R;
import ch.threema.client.ConnectionState;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionIndicatorUtil {

    private static ConnectionIndicatorUtil ourInstance;

    private final int red, green, orange, transparent;

    public static ConnectionIndicatorUtil getInstance() {
        return ourInstance;
    }

    public static void init(Context context) {
        if (!ListenerUtil.mutListener.listen(55900)) {
            ConnectionIndicatorUtil.ourInstance = new ConnectionIndicatorUtil(context);
        }
    }

    private ConnectionIndicatorUtil(Context context) {
        this.red = context.getResources().getColor(R.color.material_red);
        this.orange = context.getResources().getColor(R.color.material_orange);
        this.green = context.getResources().getColor(R.color.material_green);
        this.transparent = context.getResources().getColor(android.R.color.transparent);
    }

    @UiThread
    public void updateConnectionIndicator(View connectionIndicator, ConnectionState connectionState) {
        if (!ListenerUtil.mutListener.listen(55906)) {
            if (TestUtil.required(connectionIndicator)) {
                if (!ListenerUtil.mutListener.listen(55904)) {
                    if (connectionState == ConnectionState.CONNECTED) {
                        if (!ListenerUtil.mutListener.listen(55903)) {
                            connectionIndicator.setBackgroundColor(this.orange);
                        }
                    } else if (connectionState == ConnectionState.LOGGEDIN) {
                        if (!ListenerUtil.mutListener.listen(55902)) {
                            connectionIndicator.setBackgroundColor(this.transparent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(55901)) {
                            connectionIndicator.setBackgroundColor(this.red);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55905)) {
                    connectionIndicator.invalidate();
                }
            }
        }
    }
}
