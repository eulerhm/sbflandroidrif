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
package com.ichi2.anki.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.libanki.Collection;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SdCardReceiver extends BroadcastReceiver {

    public static final String MEDIA_EJECT = "com.ichi2.anki.action.MEDIA_EJECT";

    public static final String MEDIA_MOUNT = "com.ichi2.anki.action.MEDIA_MOUNT";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(2900)) {
            if (intent.getAction().equals(Intent.ACTION_MEDIA_EJECT)) {
                if (!ListenerUtil.mutListener.listen(2894)) {
                    Timber.i("media eject detected - closing collection and sending broadcast");
                }
                Intent i = new Intent();
                if (!ListenerUtil.mutListener.listen(2895)) {
                    i.setAction(MEDIA_EJECT);
                }
                if (!ListenerUtil.mutListener.listen(2896)) {
                    context.sendBroadcast(i);
                }
                try {
                    Collection col = CollectionHelper.getInstance().getCol(context);
                    if (!ListenerUtil.mutListener.listen(2899)) {
                        if (col != null) {
                            if (!ListenerUtil.mutListener.listen(2898)) {
                                col.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(2897)) {
                        Timber.w(e, "Exception while trying to close collection likely because it was already unmounted");
                    }
                }
            } else if (intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)) {
                if (!ListenerUtil.mutListener.listen(2891)) {
                    Timber.i("media mount detected - sending broadcast");
                }
                Intent i = new Intent();
                if (!ListenerUtil.mutListener.listen(2892)) {
                    i.setAction(MEDIA_MOUNT);
                }
                if (!ListenerUtil.mutListener.listen(2893)) {
                    context.sendBroadcast(i);
                }
            }
        }
    }
}
