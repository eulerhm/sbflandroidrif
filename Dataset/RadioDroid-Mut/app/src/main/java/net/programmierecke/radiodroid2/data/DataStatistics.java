package net.programmierecke.radiodroid2.data;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DataStatistics {

    public String Name = "";

    public String Value = "";

    public static DataStatistics[] DecodeJson(String result) {
        List<DataStatistics> aList = new ArrayList<DataStatistics>();
        if (!ListenerUtil.mutListener.listen(404)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(403)) {
                    if (TextUtils.isGraphic(result)) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            Iterator<?> keys = jsonObject.keys();
                            if (!ListenerUtil.mutListener.listen(402)) {
                                {
                                    long _loopCounter10 = 0;
                                    while (keys.hasNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                                        String key = (String) keys.next();
                                        DataStatistics aData = new DataStatistics();
                                        if (!ListenerUtil.mutListener.listen(399)) {
                                            aData.Name = key;
                                        }
                                        if (!ListenerUtil.mutListener.listen(400)) {
                                            aData.Value = jsonObject.getString(key);
                                        }
                                        if (!ListenerUtil.mutListener.listen(401)) {
                                            aList.add(aData);
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(398)) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return aList.toArray(new DataStatistics[0]);
    }
}
