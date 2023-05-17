package net.programmierecke.radiodroid2.data;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DataCategory implements Comparable<DataCategory> {

    public String Name = "";

    public int UsedCount = 0;

    public String Label = null;

    public Drawable Icon = null;

    public String getSortField() {
        if (Label != null) {
            return Label;
        } else {
            return Name;
        }
    }

    public static DataCategory[] DecodeJson(String result) {
        List<DataCategory> aList = new ArrayList<DataCategory>();
        if (!ListenerUtil.mutListener.listen(397)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(396)) {
                    if (TextUtils.isGraphic(result)) {
                        try {
                            JSONArray jsonArray = new JSONArray(result);
                            if (!ListenerUtil.mutListener.listen(395)) {
                                {
                                    long _loopCounter9 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(394) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(393) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(392) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(391) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(390) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                                        JSONObject anObject = jsonArray.getJSONObject(i);
                                        DataCategory aData = new DataCategory();
                                        if (!ListenerUtil.mutListener.listen(387)) {
                                            aData.Name = anObject.getString("name");
                                        }
                                        if (!ListenerUtil.mutListener.listen(388)) {
                                            aData.UsedCount = anObject.getInt("stationcount");
                                        }
                                        if (!ListenerUtil.mutListener.listen(389)) {
                                            aList.add(aData);
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(386)) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return aList.toArray(new DataCategory[0]);
    }

    @Override
    public int compareTo(DataCategory o) {
        return getSortField().compareToIgnoreCase(o.getSortField());
    }
}
