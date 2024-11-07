package net.programmierecke.radiodroid2.station;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import net.programmierecke.radiodroid2.ActivityMain;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.service.MediaSessionCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import okhttp3.OkHttpClient;
import static net.programmierecke.radiodroid2.Utils.resourceToUri;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DataRadioStation implements Parcelable {

    static final String TAG = "DATAStation";

    public static final int MAX_REFRESH_RETRIES = 16;

    public static final String RADIO_STATION_LOCAL_INFO_CHAGED = "net.programmierecke.radiodroid2.radiostation.changed";

    public static final String RADIO_STATION_UUID = "UUID";

    public DataRadioStation() {
    }

    public String Name;

    public String StationUuid = "";

    public String ChangeUuid = "";

    public String StreamUrl;

    public String HomePageUrl;

    public String IconUrl;

    public String Country;

    public String CountryCode;

    public String State;

    public String TagsAll;

    public String Language;

    public int ClickCount;

    public int ClickTrend;

    public int Votes;

    public int RefreshRetryCount;

    public int Bitrate;

    public String Codec;

    public boolean Working = true;

    public boolean Hls = false;

    public boolean DeletedOnServer = false;

    public String playableUrl;

    @Deprecated
    public String StationId = "";

    public String getShortDetails(Context ctx) {
        List<String> aList = new ArrayList<String>();
        if (!ListenerUtil.mutListener.listen(2548)) {
            if (DeletedOnServer) {
                if (!ListenerUtil.mutListener.listen(2547)) {
                    aList.add(ctx.getResources().getString(R.string.station_detail_deleted_on_server));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2550)) {
            if (!Working) {
                if (!ListenerUtil.mutListener.listen(2549)) {
                    aList.add(ctx.getResources().getString(R.string.station_detail_broken));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2557)) {
            if ((ListenerUtil.mutListener.listen(2555) ? (Bitrate >= 0) : (ListenerUtil.mutListener.listen(2554) ? (Bitrate <= 0) : (ListenerUtil.mutListener.listen(2553) ? (Bitrate < 0) : (ListenerUtil.mutListener.listen(2552) ? (Bitrate != 0) : (ListenerUtil.mutListener.listen(2551) ? (Bitrate == 0) : (Bitrate > 0))))))) {
                if (!ListenerUtil.mutListener.listen(2556)) {
                    aList.add(ctx.getResources().getString(R.string.station_detail_bitrate, Bitrate));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2560)) {
            if (State != null) {
                if (!ListenerUtil.mutListener.listen(2559)) {
                    if (!State.trim().equals(""))
                        if (!ListenerUtil.mutListener.listen(2558)) {
                            aList.add(State);
                        }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2563)) {
            if (Language != null) {
                if (!ListenerUtil.mutListener.listen(2562)) {
                    if (!Language.trim().equals(""))
                        if (!ListenerUtil.mutListener.listen(2561)) {
                            aList.add(Language);
                        }
                }
            }
        }
        return TextUtils.join(", ", aList);
    }

    public String getLongDetails(Context ctx) {
        List<String> aList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2565)) {
            if (DeletedOnServer) {
                if (!ListenerUtil.mutListener.listen(2564)) {
                    aList.add(ctx.getResources().getString(R.string.station_detail_deleted_on_server));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2567)) {
            if (!Working) {
                if (!ListenerUtil.mutListener.listen(2566)) {
                    aList.add(ctx.getResources().getString(R.string.station_detail_broken));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2574)) {
            if ((ListenerUtil.mutListener.listen(2572) ? (Bitrate >= 0) : (ListenerUtil.mutListener.listen(2571) ? (Bitrate <= 0) : (ListenerUtil.mutListener.listen(2570) ? (Bitrate < 0) : (ListenerUtil.mutListener.listen(2569) ? (Bitrate != 0) : (ListenerUtil.mutListener.listen(2568) ? (Bitrate == 0) : (Bitrate > 0))))))) {
                if (!ListenerUtil.mutListener.listen(2573)) {
                    aList.add(ctx.getResources().getString(R.string.station_detail_bitrate, Bitrate));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2576)) {
            if (!TextUtils.isEmpty(Codec)) {
                if (!ListenerUtil.mutListener.listen(2575)) {
                    aList.add(Codec);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2579)) {
            if (State != null) {
                if (!ListenerUtil.mutListener.listen(2578)) {
                    if (!State.trim().equals(""))
                        if (!ListenerUtil.mutListener.listen(2577)) {
                            aList.add(State);
                        }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2582)) {
            if (Language != null) {
                if (!ListenerUtil.mutListener.listen(2581)) {
                    if (!Language.trim().equals(""))
                        if (!ListenerUtil.mutListener.listen(2580)) {
                            aList.add(Language);
                        }
                }
            }
        }
        return TextUtils.join(", ", aList);
    }

    public boolean hasIcon() {
        return !TextUtils.isEmpty(IconUrl);
    }

    private void fixStationFields() {
        if (!ListenerUtil.mutListener.listen(2585)) {
            if ((ListenerUtil.mutListener.listen(2583) ? (IconUrl == null && TextUtils.isEmpty(IconUrl.trim())) : (IconUrl == null || TextUtils.isEmpty(IconUrl.trim())))) {
                if (!ListenerUtil.mutListener.listen(2584)) {
                    IconUrl = "";
                }
            }
        }
    }

    public static List<DataRadioStation> DecodeJson(String result) {
        List<DataRadioStation> aList = new ArrayList<DataRadioStation>();
        if (!ListenerUtil.mutListener.listen(2632)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(2631)) {
                    if (TextUtils.isGraphic(result)) {
                        try {
                            JSONArray jsonArray = new JSONArray(result);
                            if (!ListenerUtil.mutListener.listen(2630)) {
                                {
                                    long _loopCounter39 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(2629) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(2628) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(2627) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(2626) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(2625) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                                        try {
                                            JSONObject anObject = jsonArray.getJSONObject(i);
                                            DataRadioStation aStation = new DataRadioStation();
                                            if (!ListenerUtil.mutListener.listen(2588)) {
                                                aStation.Name = anObject.getString("name");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2589)) {
                                                aStation.StreamUrl = "";
                                            }
                                            if (!ListenerUtil.mutListener.listen(2591)) {
                                                if (anObject.has("url")) {
                                                    if (!ListenerUtil.mutListener.listen(2590)) {
                                                        aStation.StreamUrl = anObject.getString("url");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2593)) {
                                                if (anObject.has("stationuuid")) {
                                                    if (!ListenerUtil.mutListener.listen(2592)) {
                                                        aStation.StationUuid = anObject.getString("stationuuid");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2595)) {
                                                if (!aStation.hasValidUuid()) {
                                                    if (!ListenerUtil.mutListener.listen(2594)) {
                                                        aStation.StationId = anObject.getString("id");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2597)) {
                                                if (anObject.has("changeuuid")) {
                                                    if (!ListenerUtil.mutListener.listen(2596)) {
                                                        aStation.ChangeUuid = anObject.getString("changeuuid");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2598)) {
                                                aStation.Votes = anObject.getInt("votes");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2601)) {
                                                if (anObject.has("refreshretrycount")) {
                                                    if (!ListenerUtil.mutListener.listen(2600)) {
                                                        aStation.RefreshRetryCount = anObject.getInt("refreshretrycount");
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(2599)) {
                                                        aStation.RefreshRetryCount = 0;
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2602)) {
                                                aStation.HomePageUrl = anObject.getString("homepage");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2603)) {
                                                aStation.TagsAll = anObject.getString("tags");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2604)) {
                                                aStation.Country = anObject.getString("country");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2606)) {
                                                if (anObject.has("countrycode")) {
                                                    if (!ListenerUtil.mutListener.listen(2605)) {
                                                        aStation.CountryCode = anObject.getString("countrycode");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2607)) {
                                                aStation.State = anObject.getString("state");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2608)) {
                                                aStation.IconUrl = anObject.getString("favicon");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2609)) {
                                                aStation.Language = anObject.getString("language");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2610)) {
                                                aStation.ClickCount = anObject.getInt("clickcount");
                                            }
                                            if (!ListenerUtil.mutListener.listen(2612)) {
                                                if (anObject.has("clicktrend")) {
                                                    if (!ListenerUtil.mutListener.listen(2611)) {
                                                        aStation.ClickTrend = anObject.getInt("clicktrend");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2614)) {
                                                if (anObject.has("bitrate")) {
                                                    if (!ListenerUtil.mutListener.listen(2613)) {
                                                        aStation.Bitrate = anObject.getInt("bitrate");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2616)) {
                                                if (anObject.has("codec")) {
                                                    if (!ListenerUtil.mutListener.listen(2615)) {
                                                        aStation.Codec = anObject.getString("codec");
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2618)) {
                                                if (anObject.has("lastcheckok")) {
                                                    if (!ListenerUtil.mutListener.listen(2617)) {
                                                        aStation.Working = anObject.getInt("lastcheckok") != 0;
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2620)) {
                                                if (anObject.has("hls")) {
                                                    if (!ListenerUtil.mutListener.listen(2619)) {
                                                        aStation.Hls = anObject.getInt("hls") != 0;
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2622)) {
                                                if (anObject.has("DeletedOnServer")) {
                                                    if (!ListenerUtil.mutListener.listen(2621)) {
                                                        aStation.DeletedOnServer = anObject.getInt("DeletedOnServer") != 0;
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2623)) {
                                                aStation.fixStationFields();
                                            }
                                            if (!ListenerUtil.mutListener.listen(2624)) {
                                                aList.add(aStation);
                                            }
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(2587)) {
                                                Log.e(TAG, "DecodeJson() #2 " + e);
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(2586)) {
                                Log.e(TAG, "DecodeJson() #1 " + e);
                            }
                        }
                    }
                }
            }
        }
        return aList;
    }

    public static DataRadioStation DecodeJsonSingle(String result) {
        if (!ListenerUtil.mutListener.listen(2669)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(2668)) {
                    if (TextUtils.isGraphic(result)) {
                        try {
                            JSONObject anObject = new JSONObject(result);
                            DataRadioStation aStation = new DataRadioStation();
                            if (!ListenerUtil.mutListener.listen(2634)) {
                                aStation.Name = anObject.getString("name");
                            }
                            if (!ListenerUtil.mutListener.listen(2635)) {
                                aStation.StreamUrl = "";
                            }
                            if (!ListenerUtil.mutListener.listen(2637)) {
                                if (anObject.has("url")) {
                                    if (!ListenerUtil.mutListener.listen(2636)) {
                                        aStation.StreamUrl = anObject.getString("url");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2639)) {
                                if (anObject.has("stationuuid")) {
                                    if (!ListenerUtil.mutListener.listen(2638)) {
                                        aStation.StationUuid = anObject.getString("stationuuid");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2641)) {
                                if (!aStation.hasValidUuid()) {
                                    if (!ListenerUtil.mutListener.listen(2640)) {
                                        aStation.StationId = anObject.getString("id");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2643)) {
                                if (anObject.has("changeuuid")) {
                                    if (!ListenerUtil.mutListener.listen(2642)) {
                                        aStation.ChangeUuid = anObject.getString("changeuuid");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2644)) {
                                aStation.Votes = anObject.getInt("votes");
                            }
                            if (!ListenerUtil.mutListener.listen(2647)) {
                                if (anObject.has("refreshretrycount")) {
                                    if (!ListenerUtil.mutListener.listen(2646)) {
                                        aStation.RefreshRetryCount = anObject.getInt("refreshretrycount");
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2645)) {
                                        aStation.RefreshRetryCount = 0;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2648)) {
                                aStation.HomePageUrl = anObject.getString("homepage");
                            }
                            if (!ListenerUtil.mutListener.listen(2649)) {
                                aStation.TagsAll = anObject.getString("tags");
                            }
                            if (!ListenerUtil.mutListener.listen(2650)) {
                                aStation.Country = anObject.getString("country");
                            }
                            if (!ListenerUtil.mutListener.listen(2652)) {
                                if (anObject.has("countrycode")) {
                                    if (!ListenerUtil.mutListener.listen(2651)) {
                                        aStation.CountryCode = anObject.getString("countrycode");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2653)) {
                                aStation.State = anObject.getString("state");
                            }
                            if (!ListenerUtil.mutListener.listen(2654)) {
                                aStation.IconUrl = anObject.getString("favicon");
                            }
                            if (!ListenerUtil.mutListener.listen(2655)) {
                                aStation.Language = anObject.getString("language");
                            }
                            if (!ListenerUtil.mutListener.listen(2656)) {
                                aStation.ClickCount = anObject.getInt("clickcount");
                            }
                            if (!ListenerUtil.mutListener.listen(2658)) {
                                if (anObject.has("clicktrend")) {
                                    if (!ListenerUtil.mutListener.listen(2657)) {
                                        aStation.ClickTrend = anObject.getInt("clicktrend");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2660)) {
                                if (anObject.has(("bitrate"))) {
                                    if (!ListenerUtil.mutListener.listen(2659)) {
                                        aStation.Bitrate = anObject.getInt("bitrate");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2662)) {
                                if (anObject.has("codec")) {
                                    if (!ListenerUtil.mutListener.listen(2661)) {
                                        aStation.Codec = anObject.getString("codec");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2664)) {
                                if (anObject.has("lastcheckok")) {
                                    if (!ListenerUtil.mutListener.listen(2663)) {
                                        aStation.Working = anObject.getInt("lastcheckok") != 0;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2666)) {
                                if (anObject.has("DeletedOnServer")) {
                                    if (!ListenerUtil.mutListener.listen(2665)) {
                                        aStation.DeletedOnServer = anObject.getInt("DeletedOnServer") != 0;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2667)) {
                                aStation.fixStationFields();
                            }
                            return aStation;
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(2633)) {
                                Log.e(TAG, "DecodeJsonSingle() " + e);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(2673)) {
                if (TextUtils.isEmpty(StationUuid)) {
                    if (!ListenerUtil.mutListener.listen(2672)) {
                        obj.put("id", StationId);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2671)) {
                        obj.put("stationuuid", StationUuid);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2674)) {
                obj.put("changeuuid", ChangeUuid);
            }
            if (!ListenerUtil.mutListener.listen(2675)) {
                obj.put("name", Name);
            }
            if (!ListenerUtil.mutListener.listen(2676)) {
                obj.put("homepage", HomePageUrl);
            }
            if (!ListenerUtil.mutListener.listen(2677)) {
                obj.put("url", StreamUrl);
            }
            if (!ListenerUtil.mutListener.listen(2678)) {
                obj.put("favicon", IconUrl);
            }
            if (!ListenerUtil.mutListener.listen(2679)) {
                obj.put("country", Country);
            }
            if (!ListenerUtil.mutListener.listen(2680)) {
                obj.put("countrycode", CountryCode);
            }
            if (!ListenerUtil.mutListener.listen(2681)) {
                obj.put("state", State);
            }
            if (!ListenerUtil.mutListener.listen(2682)) {
                obj.put("tags", TagsAll);
            }
            if (!ListenerUtil.mutListener.listen(2683)) {
                obj.put("language", Language);
            }
            if (!ListenerUtil.mutListener.listen(2684)) {
                obj.put("clickcount", ClickCount);
            }
            if (!ListenerUtil.mutListener.listen(2685)) {
                obj.put("clicktrend", ClickTrend);
            }
            if (!ListenerUtil.mutListener.listen(2692)) {
                if ((ListenerUtil.mutListener.listen(2690) ? (RefreshRetryCount >= 0) : (ListenerUtil.mutListener.listen(2689) ? (RefreshRetryCount <= 0) : (ListenerUtil.mutListener.listen(2688) ? (RefreshRetryCount < 0) : (ListenerUtil.mutListener.listen(2687) ? (RefreshRetryCount != 0) : (ListenerUtil.mutListener.listen(2686) ? (RefreshRetryCount == 0) : (RefreshRetryCount > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(2691)) {
                        obj.put("refreshretrycount", RefreshRetryCount);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2693)) {
                obj.put("votes", Votes);
            }
            if (!ListenerUtil.mutListener.listen(2694)) {
                obj.put("bitrate", "" + Bitrate);
            }
            if (!ListenerUtil.mutListener.listen(2695)) {
                obj.put("codec", Codec);
            }
            if (!ListenerUtil.mutListener.listen(2696)) {
                obj.put("lastcheckok", Working ? "1" : "0");
            }
            if (!ListenerUtil.mutListener.listen(2697)) {
                obj.put("DeletedOnServer", DeletedOnServer ? "1" : "0");
            }
            return obj;
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(2670)) {
                Log.e(TAG, "toJson() " + e);
            }
        }
        return null;
    }

    public boolean refresh(final OkHttpClient httpClient, final Context context) {
        boolean success = false;
        DataRadioStation refreshedStation = (!TextUtils.isEmpty(StationUuid) ? Utils.getStationByUuid(httpClient, context, StationUuid) : Utils.getStationById(httpClient, context, StationId));
        if (!ListenerUtil.mutListener.listen(2703)) {
            if ((ListenerUtil.mutListener.listen(2698) ? (refreshedStation != null || refreshedStation.hasValidUuid()) : (refreshedStation != null && refreshedStation.hasValidUuid()))) {
                if (!ListenerUtil.mutListener.listen(2700)) {
                    copyPropertiesFrom(refreshedStation);
                }
                if (!ListenerUtil.mutListener.listen(2701)) {
                    RefreshRetryCount = 0;
                }
                if (!ListenerUtil.mutListener.listen(2702)) {
                    success = true;
                }
            } else if (Utils.hasAnyConnection(context)) {
                if (!ListenerUtil.mutListener.listen(2699)) {
                    RefreshRetryCount++;
                }
            }
        }
        return success;
    }

    public boolean hasValidUuid() {
        return !TextUtils.isEmpty(StationUuid);
    }

    public void copyPropertiesFrom(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(2704)) {
            StationUuid = station.StationUuid;
        }
        if (!ListenerUtil.mutListener.listen(2705)) {
            StationId = station.StationId;
        }
        if (!ListenerUtil.mutListener.listen(2706)) {
            ChangeUuid = station.ChangeUuid;
        }
        if (!ListenerUtil.mutListener.listen(2707)) {
            Name = station.Name;
        }
        if (!ListenerUtil.mutListener.listen(2708)) {
            HomePageUrl = station.HomePageUrl;
        }
        if (!ListenerUtil.mutListener.listen(2709)) {
            StreamUrl = station.StreamUrl;
        }
        if (!ListenerUtil.mutListener.listen(2710)) {
            IconUrl = station.IconUrl;
        }
        if (!ListenerUtil.mutListener.listen(2711)) {
            Country = station.Country;
        }
        if (!ListenerUtil.mutListener.listen(2712)) {
            CountryCode = station.CountryCode;
        }
        if (!ListenerUtil.mutListener.listen(2713)) {
            State = station.State;
        }
        if (!ListenerUtil.mutListener.listen(2714)) {
            TagsAll = station.TagsAll;
        }
        if (!ListenerUtil.mutListener.listen(2715)) {
            Language = station.Language;
        }
        if (!ListenerUtil.mutListener.listen(2716)) {
            ClickCount = station.ClickCount;
        }
        if (!ListenerUtil.mutListener.listen(2717)) {
            ClickTrend = station.ClickTrend;
        }
        if (!ListenerUtil.mutListener.listen(2718)) {
            Votes = station.Votes;
        }
        if (!ListenerUtil.mutListener.listen(2719)) {
            RefreshRetryCount = station.RefreshRetryCount;
        }
        if (!ListenerUtil.mutListener.listen(2720)) {
            Bitrate = station.Bitrate;
        }
        if (!ListenerUtil.mutListener.listen(2721)) {
            Codec = station.Codec;
        }
        if (!ListenerUtil.mutListener.listen(2722)) {
            Working = station.Working;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (!ListenerUtil.mutListener.listen(2723)) {
            dest.writeString(this.Name);
        }
        if (!ListenerUtil.mutListener.listen(2724)) {
            dest.writeString(this.StationUuid);
        }
        if (!ListenerUtil.mutListener.listen(2725)) {
            dest.writeString(this.ChangeUuid);
        }
        if (!ListenerUtil.mutListener.listen(2726)) {
            dest.writeString(this.StreamUrl);
        }
        if (!ListenerUtil.mutListener.listen(2727)) {
            dest.writeString(this.HomePageUrl);
        }
        if (!ListenerUtil.mutListener.listen(2728)) {
            dest.writeString(this.IconUrl);
        }
        if (!ListenerUtil.mutListener.listen(2729)) {
            dest.writeString(this.Country);
        }
        if (!ListenerUtil.mutListener.listen(2730)) {
            dest.writeString(this.CountryCode);
        }
        if (!ListenerUtil.mutListener.listen(2731)) {
            dest.writeString(this.State);
        }
        if (!ListenerUtil.mutListener.listen(2732)) {
            dest.writeString(this.TagsAll);
        }
        if (!ListenerUtil.mutListener.listen(2733)) {
            dest.writeString(this.Language);
        }
        if (!ListenerUtil.mutListener.listen(2734)) {
            dest.writeInt(this.ClickCount);
        }
        if (!ListenerUtil.mutListener.listen(2735)) {
            dest.writeInt(this.ClickTrend);
        }
        if (!ListenerUtil.mutListener.listen(2736)) {
            dest.writeInt(this.Votes);
        }
        if (!ListenerUtil.mutListener.listen(2737)) {
            dest.writeInt(this.Bitrate);
        }
        if (!ListenerUtil.mutListener.listen(2738)) {
            dest.writeString(this.Codec);
        }
        if (!ListenerUtil.mutListener.listen(2739)) {
            dest.writeByte(this.Working ? (byte) 1 : (byte) 0);
        }
        if (!ListenerUtil.mutListener.listen(2740)) {
            dest.writeByte(this.Hls ? (byte) 1 : (byte) 0);
        }
        if (!ListenerUtil.mutListener.listen(2741)) {
            dest.writeString(this.playableUrl);
        }
        if (!ListenerUtil.mutListener.listen(2742)) {
            dest.writeString(this.StationId);
        }
    }

    protected DataRadioStation(Parcel in) {
        if (!ListenerUtil.mutListener.listen(2743)) {
            this.Name = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2744)) {
            this.StationUuid = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2745)) {
            this.ChangeUuid = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2746)) {
            this.StreamUrl = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2747)) {
            this.HomePageUrl = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2748)) {
            this.IconUrl = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2749)) {
            this.Country = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2750)) {
            this.CountryCode = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2751)) {
            this.State = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2752)) {
            this.TagsAll = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2753)) {
            this.Language = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2754)) {
            this.ClickCount = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2755)) {
            this.ClickTrend = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2756)) {
            this.Votes = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2757)) {
            this.Bitrate = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2758)) {
            this.Codec = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2759)) {
            this.Working = in.readByte() != 0;
        }
        if (!ListenerUtil.mutListener.listen(2760)) {
            this.Hls = in.readByte() != 0;
        }
        if (!ListenerUtil.mutListener.listen(2761)) {
            this.playableUrl = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2762)) {
            this.StationId = in.readString();
        }
    }

    public static final Parcelable.Creator<DataRadioStation> CREATOR = new Parcelable.Creator<DataRadioStation>() {

        @Override
        public DataRadioStation createFromParcel(Parcel source) {
            return new DataRadioStation(source);
        }

        @Override
        public DataRadioStation[] newArray(int size) {
            return new DataRadioStation[size];
        }
    };

    public interface ShortcutReadyListener {

        void onShortcutReadyListener(ShortcutInfo shortcutInfo);
    }

    public void prepareShortcut(Context ctx, ShortcutReadyListener cb) {
        if (!ListenerUtil.mutListener.listen(2763)) {
            Picasso.get().load((!hasIcon() ? resourceToUri(ctx.getResources(), R.drawable.ic_launcher).toString() : IconUrl)).error(R.drawable.ic_launcher).transform(Utils.useCircularIcons(ctx) ? new CropCircleTransformation() : new CropSquareTransformation()).transform(new RoundedCornersTransformation(12, 2, RoundedCornersTransformation.CornerType.ALL)).into(new RadioIconTarget(ctx, this, cb));
        }
    }

    class RadioIconTarget implements Target {

        DataRadioStation station;

        Context ctx;

        ShortcutReadyListener cb;

        RadioIconTarget(Context ctx, DataRadioStation station, ShortcutReadyListener cb) {
            super();
            if (!ListenerUtil.mutListener.listen(2764)) {
                this.ctx = ctx;
            }
            if (!ListenerUtil.mutListener.listen(2765)) {
                this.station = station;
            }
            if (!ListenerUtil.mutListener.listen(2766)) {
                this.cb = cb;
            }
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            if (!ListenerUtil.mutListener.listen(2773)) {
                if ((ListenerUtil.mutListener.listen(2771) ? (Build.VERSION.SDK_INT <= 25) : (ListenerUtil.mutListener.listen(2770) ? (Build.VERSION.SDK_INT > 25) : (ListenerUtil.mutListener.listen(2769) ? (Build.VERSION.SDK_INT < 25) : (ListenerUtil.mutListener.listen(2768) ? (Build.VERSION.SDK_INT != 25) : (ListenerUtil.mutListener.listen(2767) ? (Build.VERSION.SDK_INT == 25) : (Build.VERSION.SDK_INT >= 25))))))) {
                    Intent playByUUIDintent = new Intent(MediaSessionCallback.ACTION_PLAY_STATION_BY_UUID, null, ctx, ActivityMain.class).putExtra(MediaSessionCallback.EXTRA_STATION_UUID, station.StationUuid);
                    ShortcutInfo shortcut = new ShortcutInfo.Builder(ctx.getApplicationContext(), ctx.getPackageName() + "/" + station.StationUuid).setShortLabel(station.Name).setIcon(Icon.createWithBitmap(bitmap)).setIntent(playByUUIDintent).build();
                    if (!ListenerUtil.mutListener.listen(2772)) {
                        cb.onShortcutReadyListener(shortcut);
                    }
                }
            }
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            if (!ListenerUtil.mutListener.listen(2774)) {
                onBitmapLoaded(((BitmapDrawable) errorDrawable).getBitmap(), null);
            }
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    }
}
