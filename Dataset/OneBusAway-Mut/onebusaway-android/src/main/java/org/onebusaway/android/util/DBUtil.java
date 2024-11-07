package org.onebusaway.android.util;

import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.provider.ObaContract;
import android.content.ContentValues;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created by azizmb9494 on 2/20/16.
 */
public class DBUtil {

    public static void addToDB(ObaStop stop) {
        String name = UIUtils.formatDisplayText(stop.getName());
        // Update the database
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(6414)) {
            values.put(ObaContract.Stops.CODE, stop.getStopCode());
        }
        if (!ListenerUtil.mutListener.listen(6415)) {
            values.put(ObaContract.Stops.NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(6416)) {
            values.put(ObaContract.Stops.DIRECTION, stop.getDirection());
        }
        if (!ListenerUtil.mutListener.listen(6417)) {
            values.put(ObaContract.Stops.LATITUDE, stop.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(6418)) {
            values.put(ObaContract.Stops.LONGITUDE, stop.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(6420)) {
            if (Application.get().getCurrentRegion() != null) {
                if (!ListenerUtil.mutListener.listen(6419)) {
                    values.put(ObaContract.Stops.REGION_ID, Application.get().getCurrentRegion().getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6421)) {
            ObaContract.Stops.insertOrUpdate(stop.getId(), values, true);
        }
    }
}
