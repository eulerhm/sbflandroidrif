package net.programmierecke.radiodroid2;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.IIcon;
import net.programmierecke.radiodroid2.players.PlayStationTask;
import net.programmierecke.radiodroid2.players.selector.PlayerSelectorDialog;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.service.ConnectivityChecker;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.proxy.ProxySettings;
import net.programmierecke.radiodroid2.utils.Tls12SocketFactory;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Authenticator;
import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.TlsVersion;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Utils {

    private static int loadIcons = -1;

    public static int parseIntWithDefault(String number, int defaultVal) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public static String getCacheFile(Context ctx, String theURI) {
        StringBuilder chaine = new StringBuilder("");
        try {
            String aFileName = theURI.toLowerCase().replace("http://", "");
            if (!ListenerUtil.mutListener.listen(5233)) {
                aFileName = aFileName.toLowerCase().replace("https://", "");
            }
            if (!ListenerUtil.mutListener.listen(5234)) {
                aFileName = sanitizeName(aFileName);
            }
            File file = new File(ctx.getCacheDir().getAbsolutePath() + "/" + aFileName);
            Date lastModDate = new Date(file.lastModified());
            Date now = new Date();
            long millis = (ListenerUtil.mutListener.listen(5238) ? (now.getTime() % file.lastModified()) : (ListenerUtil.mutListener.listen(5237) ? (now.getTime() / file.lastModified()) : (ListenerUtil.mutListener.listen(5236) ? (now.getTime() * file.lastModified()) : (ListenerUtil.mutListener.listen(5235) ? (now.getTime() + file.lastModified()) : (now.getTime() - file.lastModified())))));
            long secs = (ListenerUtil.mutListener.listen(5242) ? (millis % 1000) : (ListenerUtil.mutListener.listen(5241) ? (millis * 1000) : (ListenerUtil.mutListener.listen(5240) ? (millis - 1000) : (ListenerUtil.mutListener.listen(5239) ? (millis + 1000) : (millis / 1000)))));
            long mins = (ListenerUtil.mutListener.listen(5246) ? (secs % 60) : (ListenerUtil.mutListener.listen(5245) ? (secs * 60) : (ListenerUtil.mutListener.listen(5244) ? (secs - 60) : (ListenerUtil.mutListener.listen(5243) ? (secs + 60) : (secs / 60)))));
            long hours = (ListenerUtil.mutListener.listen(5250) ? (mins % 60) : (ListenerUtil.mutListener.listen(5249) ? (mins * 60) : (ListenerUtil.mutListener.listen(5248) ? (mins - 60) : (ListenerUtil.mutListener.listen(5247) ? (mins + 60) : (mins / 60)))));
            if (!ListenerUtil.mutListener.listen(5252)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(5251)) {
                        Log.d("UTIL", "File last modified : " + lastModDate.toString() + " secs=" + secs + "  mins=" + mins + " hours=" + hours);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5263)) {
                if ((ListenerUtil.mutListener.listen(5257) ? (hours >= 1) : (ListenerUtil.mutListener.listen(5256) ? (hours <= 1) : (ListenerUtil.mutListener.listen(5255) ? (hours > 1) : (ListenerUtil.mutListener.listen(5254) ? (hours != 1) : (ListenerUtil.mutListener.listen(5253) ? (hours == 1) : (hours < 1))))))) {
                    FileInputStream aStream = new FileInputStream(file);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(aStream));
                    String line;
                    if (!ListenerUtil.mutListener.listen(5259)) {
                        {
                            long _loopCounter75 = 0;
                            while ((line = rd.readLine()) != null) {
                                ListenerUtil.loopListener.listen("_loopCounter75", ++_loopCounter75);
                                if (!ListenerUtil.mutListener.listen(5258)) {
                                    chaine.append(line);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5260)) {
                        rd.close();
                    }
                    if (!ListenerUtil.mutListener.listen(5262)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(5261)) {
                                Log.d("UTIL", "used cache for:" + theURI);
                            }
                        }
                    }
                    return chaine.toString();
                }
            }
            if (!ListenerUtil.mutListener.listen(5265)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(5264)) {
                        Log.d("UTIL", "do not use cache, because too old:" + theURI);
                    }
                }
            }
            return null;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5232)) {
                Log.e("UTIL", "getCacheFile() " + e);
            }
        }
        return null;
    }

    public static void writeFileCache(Context ctx, String theURI, String content) {
        try {
            String aFileName = theURI.toLowerCase().replace("http://", "");
            if (!ListenerUtil.mutListener.listen(5267)) {
                aFileName = aFileName.toLowerCase().replace("https://", "");
            }
            if (!ListenerUtil.mutListener.listen(5268)) {
                aFileName = sanitizeName(aFileName);
            }
            File f = new File(ctx.getCacheDir() + "/" + aFileName);
            FileOutputStream aStream = new FileOutputStream(f);
            if (!ListenerUtil.mutListener.listen(5269)) {
                aStream.write(content.getBytes("utf-8"));
            }
            if (!ListenerUtil.mutListener.listen(5270)) {
                aStream.close();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5266)) {
                Log.e("UTIL", "writeFileCache() could not write to cache file for:" + theURI);
            }
        }
    }

    private static String downloadFeed(OkHttpClient httpClient, Context ctx, String theURI, boolean forceUpdate, Map<String, String> dictParams) {
        if (!ListenerUtil.mutListener.listen(5271)) {
            Log.i("DOWN", "Url=" + theURI);
        }
        if (!ListenerUtil.mutListener.listen(5273)) {
            if (!forceUpdate) {
                String cache = getCacheFile(ctx, theURI);
                if (!ListenerUtil.mutListener.listen(5272)) {
                    if (cache != null) {
                        return cache;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5274)) {
            Log.i("DOWN", "Url=" + theURI + " (not cached)");
        }
        try {
            HttpUrl url = HttpUrl.parse(theURI);
            Request.Builder requestBuilder = new Request.Builder().url(url);
            if (!ListenerUtil.mutListener.listen(5278)) {
                if (dictParams != null) {
                    MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
                    Gson gson = new Gson();
                    String json = gson.toJson(dictParams);
                    okhttp3.RequestBody requestBody = RequestBody.create(jsonMediaType, json);
                    if (!ListenerUtil.mutListener.listen(5277)) {
                        requestBuilder.post(requestBody);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(5276)) {
                        requestBuilder.get();
                    }
                }
            }
            Request request = requestBuilder.build();
            okhttp3.Response response = httpClient.newCall(request).execute();
            String responseStr = response.body().string();
            if (!ListenerUtil.mutListener.listen(5279)) {
                writeFileCache(ctx, theURI, responseStr);
            }
            if (!ListenerUtil.mutListener.listen(5281)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(5280)) {
                        Log.d("UTIL", "wrote cache file for:" + theURI);
                    }
                }
            }
            return responseStr;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5275)) {
                Log.e("UTIL", "downloadFeed() " + e);
            }
        }
        return null;
    }

    public static String downloadFeedRelative(OkHttpClient httpClient, Context ctx, String theRelativeUri, boolean forceUpdate, Map<String, String> dictParams) {
        // try current server for download
        String currentServer = RadioBrowserServerManager.getCurrentServer();
        if (!ListenerUtil.mutListener.listen(5282)) {
            if (currentServer == null) {
                return null;
            }
        }
        String endpoint = RadioBrowserServerManager.constructEndpoint(currentServer, theRelativeUri);
        String result = downloadFeed(httpClient, ctx, endpoint, forceUpdate, dictParams);
        if (!ListenerUtil.mutListener.listen(5283)) {
            if (result != null) {
                return result;
            }
        }
        // get a list of all servers
        String[] serverList = RadioBrowserServerManager.getServerList(false);
        if (!ListenerUtil.mutListener.listen(5289)) {
            {
                long _loopCounter76 = 0;
                // try all other servers for download
                for (String newServer : serverList) {
                    ListenerUtil.loopListener.listen("_loopCounter76", ++_loopCounter76);
                    if (!ListenerUtil.mutListener.listen(5284)) {
                        if (newServer.equals(currentServer)) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5285)) {
                        endpoint = RadioBrowserServerManager.constructEndpoint(newServer, theRelativeUri);
                    }
                    if (!ListenerUtil.mutListener.listen(5286)) {
                        result = downloadFeed(httpClient, ctx, endpoint, forceUpdate, dictParams);
                    }
                    if (!ListenerUtil.mutListener.listen(5288)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(5287)) {
                                // set the working server as new current server
                                RadioBrowserServerManager.setCurrentServer(newServer);
                            }
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String getRealStationLink(OkHttpClient httpClient, Context ctx, String stationId) {
        if (!ListenerUtil.mutListener.listen(5290)) {
            Log.i("UTIL", "StationUUID:" + stationId);
        }
        String result = Utils.downloadFeedRelative(httpClient, ctx, "json/url/" + stationId, true, null);
        if (!ListenerUtil.mutListener.listen(5293)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(5291)) {
                    Log.i("UTIL", result);
                }
                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    return jsonObj.getString("url");
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5292)) {
                        Log.e("UTIL", "getRealStationLink() " + e);
                    }
                }
            }
        }
        return null;
    }

    @Deprecated
    public static DataRadioStation getStationById(OkHttpClient httpClient, Context ctx, String stationId) {
        if (!ListenerUtil.mutListener.listen(5294)) {
            Log.w("UTIL", "Search by id:" + stationId);
        }
        String result = Utils.downloadFeed(httpClient, ctx, "json/stations/byid/" + stationId, true, null);
        if (!ListenerUtil.mutListener.listen(5299)) {
            if (result != null) {
                try {
                    List<DataRadioStation> list = DataRadioStation.DecodeJson(result);
                    if (!ListenerUtil.mutListener.listen(5298)) {
                        if (list != null) {
                            if (!ListenerUtil.mutListener.listen(5296)) {
                                if (list.size() == 1) {
                                    return list.get(0);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5297)) {
                                Log.e("UTIL", "stations by id did have length:" + list.size());
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5295)) {
                        Log.e("UTIL", "getStationByid() " + e);
                    }
                }
            }
        }
        return null;
    }

    public static DataRadioStation getStationByUuid(OkHttpClient httpClient, Context ctx, String stationUuid) {
        if (!ListenerUtil.mutListener.listen(5300)) {
            Log.w("UTIL", "Search by uuid:" + stationUuid);
        }
        String result = Utils.downloadFeedRelative(httpClient, ctx, "json/stations/byuuid/" + stationUuid, true, null);
        if (!ListenerUtil.mutListener.listen(5305)) {
            if (result != null) {
                try {
                    List<DataRadioStation> list = DataRadioStation.DecodeJson(result);
                    if (!ListenerUtil.mutListener.listen(5304)) {
                        if (list != null) {
                            if (!ListenerUtil.mutListener.listen(5302)) {
                                if (list.size() == 1) {
                                    return list.get(0);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5303)) {
                                Log.e("UTIL", "stations by uuid did have length:" + list.size());
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5301)) {
                        Log.e("UTIL", "getStationByUuid() " + e);
                    }
                }
            }
        }
        return null;
    }

    public static List<DataRadioStation> getStationsByUuid(OkHttpClient httpClient, Context ctx, Iterable<String> listUUids) {
        String uuids = TextUtils.join(",", listUUids);
        if (!ListenerUtil.mutListener.listen(5306)) {
            Log.d("UTIL", "Search by uuid for items");
        }
        HashMap<String, String> p = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(5307)) {
            p.put("uuids", uuids);
        }
        String result = Utils.downloadFeedRelative(httpClient, ctx, "json/stations/byuuid", true, p);
        if (!ListenerUtil.mutListener.listen(5311)) {
            if (result != null) {
                try {
                    List<DataRadioStation> list = DataRadioStation.DecodeJson(result);
                    if (!ListenerUtil.mutListener.listen(5310)) {
                        if (list != null) {
                            return list;
                        } else {
                            if (!ListenerUtil.mutListener.listen(5309)) {
                                Log.e("UTIL", "stations by uuid was null");
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5308)) {
                        Log.e("UTIL", "getStationsByUuid() " + e);
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public static DataRadioStation getCurrentOrLastStation(@NonNull Context ctx) {
        DataRadioStation station = PlayerServiceUtil.getCurrentStation();
        if (!ListenerUtil.mutListener.listen(5313)) {
            if (station == null) {
                RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
                HistoryManager historyManager = radioDroidApp.getHistoryManager();
                if (!ListenerUtil.mutListener.listen(5312)) {
                    station = historyManager.getFirst();
                }
            }
        }
        return station;
    }

    public static void showMpdServersDialog(final RadioDroidApp radioDroidApp, final FragmentManager fragmentManager, @Nullable final DataRadioStation station) {
        Fragment oldFragment = fragmentManager.findFragmentByTag(PlayerSelectorDialog.FRAGMENT_TAG);
        if (!ListenerUtil.mutListener.listen(5315)) {
            if ((ListenerUtil.mutListener.listen(5314) ? (oldFragment != null || oldFragment.isVisible()) : (oldFragment != null && oldFragment.isVisible()))) {
                return;
            }
        }
        PlayerSelectorDialog playerSelectorDialogFragment = new PlayerSelectorDialog(radioDroidApp.getMpdClient(), station);
        if (!ListenerUtil.mutListener.listen(5316)) {
            playerSelectorDialogFragment.show(fragmentManager, PlayerSelectorDialog.FRAGMENT_TAG);
        }
    }

    public static void showPlaySelection(final RadioDroidApp radioDroidApp, final DataRadioStation station, final FragmentManager fragmentManager) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(radioDroidApp);
        final boolean externalAvailable = sharedPref.getBoolean("play_external", false);
        CastHandler castHandler = radioDroidApp.getCastHandler();
        final boolean castAvailable = castHandler.isCastSessionAvailable();
        final boolean mpdAvailable = radioDroidApp.getMpdClient().isMpdEnabled();
        if (!ListenerUtil.mutListener.listen(5323)) {
            if ((ListenerUtil.mutListener.listen(5318) ? ((ListenerUtil.mutListener.listen(5317) ? (castAvailable || !externalAvailable) : (castAvailable && !externalAvailable)) || !mpdAvailable) : ((ListenerUtil.mutListener.listen(5317) ? (castAvailable || !externalAvailable) : (castAvailable && !externalAvailable)) && !mpdAvailable))) {
                if (!ListenerUtil.mutListener.listen(5322)) {
                    new PlayStationTask(station, radioDroidApp.getApplicationContext(), url -> castHandler.playRemote(station.Name, url, station.IconUrl), null).execute();
                }
            } else if ((ListenerUtil.mutListener.listen(5319) ? (externalAvailable && mpdAvailable) : (externalAvailable || mpdAvailable))) {
                if (!ListenerUtil.mutListener.listen(5321)) {
                    showMpdServersDialog(radioDroidApp, fragmentManager, station);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5320)) {
                    playAndWarnIfMetered(radioDroidApp, station, PlayerType.RADIODROID, () -> play(radioDroidApp, station));
                }
            }
        }
    }

    public static void playAndWarnIfMetered(RadioDroidApp radioDroidApp, DataRadioStation station, PlayerType playerType, Runnable playFunc) {
        if (!ListenerUtil.mutListener.listen(5324)) {
            playAndWarnIfMetered(radioDroidApp, station, playerType, playFunc, (station1, playerType1) -> {
                // and not issue warning a second time.
                PlayerServiceUtil.setStation(station1);
                PlayerServiceUtil.warnAboutMeteredConnection(playerType1);
            });
        }
    }

    public static boolean urlIndicatesHlsStream(String streamUrl) {
        final Pattern p = Pattern.compile(".*\\.m3u8([#?\\s].*)?$");
        return p.matcher(streamUrl).matches();
    }

    public interface MeteredWarningCallback {

        void warn(DataRadioStation station, PlayerType playerType);
    }

    // PlayerServiceUtil as a proxy between common code and the service.
    public static void playAndWarnIfMetered(RadioDroidApp radioDroidApp, DataRadioStation station, PlayerType playerType, Runnable playFunc, MeteredWarningCallback warningCallback) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(radioDroidApp);
        final boolean warnOnMetered = sharedPref.getBoolean("warn_no_wifi", false);
        if (!ListenerUtil.mutListener.listen(5328)) {
            if ((ListenerUtil.mutListener.listen(5325) ? (warnOnMetered || ConnectivityChecker.getCurrentConnectionType(radioDroidApp) == ConnectivityChecker.ConnectionType.METERED) : (warnOnMetered && ConnectivityChecker.getCurrentConnectionType(radioDroidApp) == ConnectivityChecker.ConnectionType.METERED))) {
                if (!ListenerUtil.mutListener.listen(5327)) {
                    warningCallback.warn(station, playerType);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5326)) {
                    playFunc.run();
                }
            }
        }
    }

    public static void play(final RadioDroidApp radioDroidApp, final DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(5329)) {
            PlayerServiceUtil.play(station);
        }
    }

    public static boolean shouldLoadIcons(final Context context) {
        if (!ListenerUtil.mutListener.listen(5333)) {
            switch(loadIcons) {
                case -1:
                    if (!ListenerUtil.mutListener.listen(5332)) {
                        if (PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getBoolean("load_icons", false)) {
                            if (!ListenerUtil.mutListener.listen(5331)) {
                                loadIcons = 1;
                            }
                            return true;
                        } else {
                            if (!ListenerUtil.mutListener.listen(5330)) {
                                loadIcons = 0;
                            }
                            return true;
                        }
                    }
                case 0:
                    return false;
                case 1:
                    return true;
            }
        }
        return false;
    }

    public static String getTheme(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString("theme_name", context.getResources().getString(R.string.theme_light));
    }

    public static int getThemeResId(final Context context) {
        String selectedTheme = getTheme(context);
        if (selectedTheme.equals(context.getResources().getString(R.string.theme_dark)))
            return R.style.MyMaterialTheme_Dark;
        else
            return R.style.MyMaterialTheme;
    }

    public static boolean isDarkTheme(final Context context) {
        return getThemeResId(context) == R.style.MyMaterialTheme_Dark;
    }

    public static int getTimePickerThemeResId(final Context context) {
        int theme;
        if (getThemeResId(context) == R.style.MyMaterialTheme_Dark)
            theme = R.style.DialogTheme_Dark;
        else
            theme = R.style.DialogTheme;
        return theme;
    }

    public static boolean useCircularIcons(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean("circular_icons", false);
    }

    // Storage Permissions
    private static String[] PERMISSIONS_STORAGE = { Manifest.permission.WRITE_EXTERNAL_STORAGE };

    public static boolean verifyStoragePermissions(Activity activity, int request_id) {
        // Check if we have write permission
        int permission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!ListenerUtil.mutListener.listen(5335)) {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                if (!ListenerUtil.mutListener.listen(5334)) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, request_id);
                }
                return false;
            }
        }
        return true;
    }

    public static boolean verifyStoragePermissions(Fragment fragment, int request_id) {
        // Check if we have write permission
        int permission = ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!ListenerUtil.mutListener.listen(5337)) {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                if (!ListenerUtil.mutListener.listen(5336)) {
                    // We don't have permission so prompt the user
                    fragment.requestPermissions(PERMISSIONS_STORAGE, request_id);
                }
                return false;
            }
        }
        return true;
    }

    public static String getReadableBytes(double bytes) {
        String[] str = new String[] { "B", "KB", "MB", "GB", "TB" };
        if (!ListenerUtil.mutListener.listen(5349)) {
            {
                long _loopCounter77 = 0;
                for (String aStr : str) {
                    ListenerUtil.loopListener.listen("_loopCounter77", ++_loopCounter77);
                    if (!ListenerUtil.mutListener.listen(5343)) {
                        if ((ListenerUtil.mutListener.listen(5342) ? (bytes >= 1024) : (ListenerUtil.mutListener.listen(5341) ? (bytes <= 1024) : (ListenerUtil.mutListener.listen(5340) ? (bytes > 1024) : (ListenerUtil.mutListener.listen(5339) ? (bytes != 1024) : (ListenerUtil.mutListener.listen(5338) ? (bytes == 1024) : (bytes < 1024))))))) {
                            return String.format(Locale.getDefault(), "%1$,.1f %2$s", bytes, aStr);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5348)) {
                        bytes = (ListenerUtil.mutListener.listen(5347) ? (bytes % 1024) : (ListenerUtil.mutListener.listen(5346) ? (bytes * 1024) : (ListenerUtil.mutListener.listen(5345) ? (bytes - 1024) : (ListenerUtil.mutListener.listen(5344) ? (bytes + 1024) : (bytes / 1024)))));
                    }
                }
            }
        }
        return String.format(Locale.getDefault(), "%1$,.1f %2$s", (ListenerUtil.mutListener.listen(5353) ? (bytes % 1024) : (ListenerUtil.mutListener.listen(5352) ? (bytes / 1024) : (ListenerUtil.mutListener.listen(5351) ? (bytes - 1024) : (ListenerUtil.mutListener.listen(5350) ? (bytes + 1024) : (bytes * 1024))))), str[(ListenerUtil.mutListener.listen(5357) ? (str.length % 1) : (ListenerUtil.mutListener.listen(5356) ? (str.length / 1) : (ListenerUtil.mutListener.listen(5355) ? (str.length * 1) : (ListenerUtil.mutListener.listen(5354) ? (str.length + 1) : (str.length - 1)))))]);
    }

    public static String sanitizeName(String str) {
        return str.replaceAll("\\W+", "_").replaceAll("^_+", "").replaceAll("_+$", "");
    }

    public static boolean hasWifiConnection(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    public static boolean hasAnyConnection(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        // should check null because in airplane mode it will be null
        return ((ListenerUtil.mutListener.listen(5358) ? (netInfo != null || netInfo.isConnected()) : (netInfo != null && netInfo.isConnected())));
    }

    public static boolean bottomNavigationEnabled(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean("bottom_navigation", true);
    }

    public static String formatStringWithNamedArgs(String format, Map<String, String> args) {
        StringBuilder builder = new StringBuilder(format);
        if (!ListenerUtil.mutListener.listen(5376)) {
            {
                long _loopCounter79 = 0;
                for (Map.Entry<String, String> entry : args.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                    final String key = "${" + entry.getKey() + "}";
                    int startIdx = 0;
                    if (!ListenerUtil.mutListener.listen(5375)) {
                        {
                            long _loopCounter78 = 0;
                            while (true) {
                                ListenerUtil.loopListener.listen("_loopCounter78", ++_loopCounter78);
                                final int keyIdx = builder.indexOf(key, startIdx);
                                if (!ListenerUtil.mutListener.listen(5364)) {
                                    if ((ListenerUtil.mutListener.listen(5363) ? (keyIdx >= -1) : (ListenerUtil.mutListener.listen(5362) ? (keyIdx <= -1) : (ListenerUtil.mutListener.listen(5361) ? (keyIdx > -1) : (ListenerUtil.mutListener.listen(5360) ? (keyIdx < -1) : (ListenerUtil.mutListener.listen(5359) ? (keyIdx != -1) : (keyIdx == -1))))))) {
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5369)) {
                                    builder.replace(keyIdx, (ListenerUtil.mutListener.listen(5368) ? (keyIdx % key.length()) : (ListenerUtil.mutListener.listen(5367) ? (keyIdx / key.length()) : (ListenerUtil.mutListener.listen(5366) ? (keyIdx * key.length()) : (ListenerUtil.mutListener.listen(5365) ? (keyIdx - key.length()) : (keyIdx + key.length()))))), entry.getValue());
                                }
                                if (!ListenerUtil.mutListener.listen(5374)) {
                                    startIdx = (ListenerUtil.mutListener.listen(5373) ? (keyIdx % entry.getValue().length()) : (ListenerUtil.mutListener.listen(5372) ? (keyIdx / entry.getValue().length()) : (ListenerUtil.mutListener.listen(5371) ? (keyIdx * entry.getValue().length()) : (ListenerUtil.mutListener.listen(5370) ? (keyIdx - entry.getValue().length()) : (keyIdx + entry.getValue().length())))));
                                }
                            }
                        }
                    }
                }
            }
        }
        return builder.toString();
    }

    public static int themeAttributeToColor(int themeAttributeId, Context context, int fallbackColorId) {
        TypedValue outValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        boolean wasResolved = theme.resolveAttribute(themeAttributeId, outValue, true);
        if (wasResolved) {
            return outValue.resourceId == 0 ? outValue.data : ContextCompat.getColor(context, outValue.resourceId);
        } else {
            return fallbackColorId;
        }
    }

    public static int getIconColor(Context context) {
        return themeAttributeToColor(R.attr.menuTextColorDefault, context, Color.LTGRAY);
    }

    public static int getAccentColor(Context context) {
        return themeAttributeToColor(R.attr.colorAccent, context, Color.LTGRAY);
    }

    /**
     * Add proxy to an okhttp builder.
     *
     * @return true if successful, false otherwise
     */
    public static boolean setOkHttpProxy(@NonNull OkHttpClient.Builder builder, @NonNull final ProxySettings proxySettings) {
        if (!ListenerUtil.mutListener.listen(5377)) {
            if (proxySettings.type == Proxy.Type.DIRECT) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(5378)) {
            if (TextUtils.isEmpty(proxySettings.host)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5390)) {
            if ((ListenerUtil.mutListener.listen(5389) ? ((ListenerUtil.mutListener.listen(5383) ? (proxySettings.port >= 1) : (ListenerUtil.mutListener.listen(5382) ? (proxySettings.port <= 1) : (ListenerUtil.mutListener.listen(5381) ? (proxySettings.port > 1) : (ListenerUtil.mutListener.listen(5380) ? (proxySettings.port != 1) : (ListenerUtil.mutListener.listen(5379) ? (proxySettings.port == 1) : (proxySettings.port < 1)))))) && (ListenerUtil.mutListener.listen(5388) ? (proxySettings.port >= 65535) : (ListenerUtil.mutListener.listen(5387) ? (proxySettings.port <= 65535) : (ListenerUtil.mutListener.listen(5386) ? (proxySettings.port < 65535) : (ListenerUtil.mutListener.listen(5385) ? (proxySettings.port != 65535) : (ListenerUtil.mutListener.listen(5384) ? (proxySettings.port == 65535) : (proxySettings.port > 65535))))))) : ((ListenerUtil.mutListener.listen(5383) ? (proxySettings.port >= 1) : (ListenerUtil.mutListener.listen(5382) ? (proxySettings.port <= 1) : (ListenerUtil.mutListener.listen(5381) ? (proxySettings.port > 1) : (ListenerUtil.mutListener.listen(5380) ? (proxySettings.port != 1) : (ListenerUtil.mutListener.listen(5379) ? (proxySettings.port == 1) : (proxySettings.port < 1)))))) || (ListenerUtil.mutListener.listen(5388) ? (proxySettings.port >= 65535) : (ListenerUtil.mutListener.listen(5387) ? (proxySettings.port <= 65535) : (ListenerUtil.mutListener.listen(5386) ? (proxySettings.port < 65535) : (ListenerUtil.mutListener.listen(5385) ? (proxySettings.port != 65535) : (ListenerUtil.mutListener.listen(5384) ? (proxySettings.port == 65535) : (proxySettings.port > 65535))))))))) {
                return false;
            }
        }
        InetSocketAddress proxyAddress = InetSocketAddress.createUnresolved(proxySettings.host, proxySettings.port);
        Proxy proxy = new Proxy(proxySettings.type, proxyAddress);
        if (!ListenerUtil.mutListener.listen(5391)) {
            builder.proxy(proxy);
        }
        if (!ListenerUtil.mutListener.listen(5393)) {
            if (!proxySettings.login.isEmpty()) {
                Authenticator proxyAuthenticator = new Authenticator() {

                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic(proxySettings.login, proxySettings.password);
                        return response.request().newBuilder().header("Proxy-Authorization", credential).build();
                    }
                };
                if (!ListenerUtil.mutListener.listen(5392)) {
                    builder.authenticator(proxyAuthenticator);
                }
            }
        }
        return true;
    }

    public static Uri resourceToUri(Resources resources, int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(resID) + '/' + resources.getResourceTypeName(resID) + '/' + resources.getResourceEntryName(resID));
    }

    public static IconicsDrawable IconicsIcon(Context context, IIcon icon) {
        return new IconicsDrawable(context, icon).size(IconicsSize.TOOLBAR_ICON_SIZE).padding(IconicsSize.TOOLBAR_ICON_PADDING).color(IconicsColor.colorInt(getIconColor(context)));
    }

    public static String getMimeType(String url, String defaultMimeType) {
        String type = defaultMimeType;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (!ListenerUtil.mutListener.listen(5395)) {
            if (extension != null) {
                if (!ListenerUtil.mutListener.listen(5394)) {
                    type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                }
            }
        }
        return type;
    }

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (!ListenerUtil.mutListener.listen(5416)) {
            if ((ListenerUtil.mutListener.listen(5406) ? ((ListenerUtil.mutListener.listen(5400) ? (Build.VERSION.SDK_INT <= 16) : (ListenerUtil.mutListener.listen(5399) ? (Build.VERSION.SDK_INT > 16) : (ListenerUtil.mutListener.listen(5398) ? (Build.VERSION.SDK_INT < 16) : (ListenerUtil.mutListener.listen(5397) ? (Build.VERSION.SDK_INT != 16) : (ListenerUtil.mutListener.listen(5396) ? (Build.VERSION.SDK_INT == 16) : (Build.VERSION.SDK_INT >= 16)))))) || (ListenerUtil.mutListener.listen(5405) ? (Build.VERSION.SDK_INT >= 22) : (ListenerUtil.mutListener.listen(5404) ? (Build.VERSION.SDK_INT <= 22) : (ListenerUtil.mutListener.listen(5403) ? (Build.VERSION.SDK_INT > 22) : (ListenerUtil.mutListener.listen(5402) ? (Build.VERSION.SDK_INT != 22) : (ListenerUtil.mutListener.listen(5401) ? (Build.VERSION.SDK_INT == 22) : (Build.VERSION.SDK_INT < 22))))))) : ((ListenerUtil.mutListener.listen(5400) ? (Build.VERSION.SDK_INT <= 16) : (ListenerUtil.mutListener.listen(5399) ? (Build.VERSION.SDK_INT > 16) : (ListenerUtil.mutListener.listen(5398) ? (Build.VERSION.SDK_INT < 16) : (ListenerUtil.mutListener.listen(5397) ? (Build.VERSION.SDK_INT != 16) : (ListenerUtil.mutListener.listen(5396) ? (Build.VERSION.SDK_INT == 16) : (Build.VERSION.SDK_INT >= 16)))))) && (ListenerUtil.mutListener.listen(5405) ? (Build.VERSION.SDK_INT >= 22) : (ListenerUtil.mutListener.listen(5404) ? (Build.VERSION.SDK_INT <= 22) : (ListenerUtil.mutListener.listen(5403) ? (Build.VERSION.SDK_INT > 22) : (ListenerUtil.mutListener.listen(5402) ? (Build.VERSION.SDK_INT != 22) : (ListenerUtil.mutListener.listen(5401) ? (Build.VERSION.SDK_INT == 22) : (Build.VERSION.SDK_INT < 22))))))))) {
                try {
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    if (!ListenerUtil.mutListener.listen(5408)) {
                        trustManagerFactory.init((KeyStore) null);
                    }
                    TrustManager[] tmList = trustManagerFactory.getTrustManagers();
                    if (!ListenerUtil.mutListener.listen(5409)) {
                        Log.i("OkHttpTLSCompat", "Found trustmanagers:" + tmList.length);
                    }
                    X509TrustManager tm = (X509TrustManager) tmList[0];
                    SSLContext sc = SSLContext.getInstance("TLSv1.2");
                    if (!ListenerUtil.mutListener.listen(5410)) {
                        sc.init(null, null, null);
                    }
                    if (!ListenerUtil.mutListener.listen(5411)) {
                        client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()), tm);
                    }
                    ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                    List<ConnectionSpec> specs = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(5412)) {
                        specs.add(cs);
                    }
                    if (!ListenerUtil.mutListener.listen(5413)) {
                        specs.add(ConnectionSpec.COMPATIBLE_TLS);
                    }
                    if (!ListenerUtil.mutListener.listen(5414)) {
                        specs.add(ConnectionSpec.CLEARTEXT);
                    }
                    if (!ListenerUtil.mutListener.listen(5415)) {
                        client.connectionSpecs(specs);
                    }
                } catch (Exception exc) {
                    if (!ListenerUtil.mutListener.listen(5407)) {
                        Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
                    }
                }
            }
        }
        return client;
    }
}
