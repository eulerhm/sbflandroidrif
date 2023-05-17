package net.programmierecke.radiodroid2;

import android.app.UiModeManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;
import androidx.preference.PreferenceManager;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import net.programmierecke.radiodroid2.alarm.RadioAlarmManager;
import net.programmierecke.radiodroid2.history.TrackHistoryRepository;
import net.programmierecke.radiodroid2.players.mpd.MPDClient;
import net.programmierecke.radiodroid2.station.live.metadata.TrackMetadataSearcher;
import net.programmierecke.radiodroid2.proxy.ProxySettings;
import net.programmierecke.radiodroid2.recording.RecordingsManager;
import net.programmierecke.radiodroid2.utils.TvChannelManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioDroidApp extends MultiDexApplication {

    private HistoryManager historyManager;

    private FavouriteManager favouriteManager;

    private RecordingsManager recordingsManager;

    private RadioAlarmManager alarmManager;

    private TvChannelManager tvChannelManager;

    private TrackHistoryRepository trackHistoryRepository;

    private MPDClient mpdClient;

    private CastHandler castHandler;

    private TrackMetadataSearcher trackMetadataSearcher;

    private ConnectionPool connectionPool;

    private OkHttpClient httpClient;

    private Interceptor testsInterceptor;

    public class UserAgentInterceptor implements Interceptor {

        private final String userAgent;

        public UserAgentInterceptor(String userAgent) {
            this.userAgent = userAgent;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder().header("User-Agent", userAgent).build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(5007)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(5008)) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(5009)) {
            GoogleProviderHelper.use(getBaseContext());
        }
        if (!ListenerUtil.mutListener.listen(5010)) {
            connectionPool = new ConnectionPool();
        }
        if (!ListenerUtil.mutListener.listen(5011)) {
            rebuildHttpClient();
        }
        Picasso.Builder builder = new Picasso.Builder(this);
        if (!ListenerUtil.mutListener.listen(5012)) {
            builder.downloader(new OkHttp3Downloader(newHttpClientForPicasso()));
        }
        Picasso picassoInstance = builder.build();
        if (!ListenerUtil.mutListener.listen(5013)) {
            Picasso.setSingletonInstance(picassoInstance);
        }
        if (!ListenerUtil.mutListener.listen(5014)) {
            CountryCodeDictionary.getInstance().load(this);
        }
        if (!ListenerUtil.mutListener.listen(5015)) {
            CountryFlagsLoader.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(5016)) {
            historyManager = new HistoryManager(this);
        }
        if (!ListenerUtil.mutListener.listen(5017)) {
            favouriteManager = new FavouriteManager(this);
        }
        if (!ListenerUtil.mutListener.listen(5018)) {
            recordingsManager = new RecordingsManager();
        }
        if (!ListenerUtil.mutListener.listen(5019)) {
            alarmManager = new RadioAlarmManager(this);
        }
        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        if (!ListenerUtil.mutListener.listen(5022)) {
            if (uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION) {
                if (!ListenerUtil.mutListener.listen(5020)) {
                    tvChannelManager = new TvChannelManager(this);
                }
                if (!ListenerUtil.mutListener.listen(5021)) {
                    favouriteManager.addObserver(tvChannelManager);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5023)) {
            trackHistoryRepository = new TrackHistoryRepository(this);
        }
        if (!ListenerUtil.mutListener.listen(5024)) {
            mpdClient = new MPDClient(this);
        }
        if (!ListenerUtil.mutListener.listen(5025)) {
            castHandler = new CastHandler();
        }
        if (!ListenerUtil.mutListener.listen(5026)) {
            trackMetadataSearcher = new TrackMetadataSearcher(httpClient);
        }
        if (!ListenerUtil.mutListener.listen(5027)) {
            recordingsManager.updateRecordingsList();
        }
    }

    public void setTestsInterceptor(Interceptor testsInterceptor) {
        if (!ListenerUtil.mutListener.listen(5028)) {
            this.testsInterceptor = testsInterceptor;
        }
    }

    public void rebuildHttpClient() {
        OkHttpClient.Builder builder = newHttpClient().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).addInterceptor(new UserAgentInterceptor("RadioDroid2/" + BuildConfig.VERSION_NAME));
        if (!ListenerUtil.mutListener.listen(5029)) {
            httpClient = builder.build();
        }
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public FavouriteManager getFavouriteManager() {
        return favouriteManager;
    }

    public RecordingsManager getRecordingsManager() {
        return recordingsManager;
    }

    public RadioAlarmManager getAlarmManager() {
        return alarmManager;
    }

    public TrackHistoryRepository getTrackHistoryRepository() {
        return trackHistoryRepository;
    }

    public MPDClient getMpdClient() {
        return mpdClient;
    }

    public CastHandler getCastHandler() {
        return castHandler;
    }

    public TrackMetadataSearcher getTrackMetadataSearcher() {
        return trackMetadataSearcher;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public OkHttpClient.Builder newHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(connectionPool);
        if (!ListenerUtil.mutListener.listen(5031)) {
            if (testsInterceptor != null) {
                if (!ListenerUtil.mutListener.listen(5030)) {
                    builder.addInterceptor(testsInterceptor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5033)) {
            if (!setCurrentOkHttpProxy(builder)) {
                Toast toast = Toast.makeText(this, getResources().getString(R.string.ignore_proxy_settings_invalid), Toast.LENGTH_SHORT);
                if (!ListenerUtil.mutListener.listen(5032)) {
                    toast.show();
                }
            }
        }
        return Utils.enableTls12OnPreLollipop(builder);
    }

    public OkHttpClient.Builder newHttpClientWithoutProxy() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectionPool(connectionPool);
        if (!ListenerUtil.mutListener.listen(5035)) {
            if (testsInterceptor != null) {
                if (!ListenerUtil.mutListener.listen(5034)) {
                    builder.addInterceptor(testsInterceptor);
                }
            }
        }
        return Utils.enableTls12OnPreLollipop(builder);
    }

    public boolean setCurrentOkHttpProxy(@NonNull OkHttpClient.Builder builder) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        ProxySettings proxySettings = ProxySettings.fromPreferences(sharedPref);
        if (!ListenerUtil.mutListener.listen(5037)) {
            if (proxySettings != null) {
                if (!ListenerUtil.mutListener.listen(5036)) {
                    if (!Utils.setOkHttpProxy(builder, proxySettings)) {
                        // proxy settings are not valid
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private OkHttpClient newHttpClientForPicasso() {
        File cache = new File(getCacheDir(), "picasso-cache");
        if (!ListenerUtil.mutListener.listen(5039)) {
            if (!cache.exists()) {
                if (!ListenerUtil.mutListener.listen(5038)) {
                    // noinspection ResultOfMethodCallIgnored
                    cache.mkdirs();
                }
            }
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(new UserAgentInterceptor("RadioDroid2/" + BuildConfig.VERSION_NAME)).cache(new Cache(cache, Integer.MAX_VALUE));
        if (!ListenerUtil.mutListener.listen(5041)) {
            if (testsInterceptor != null) {
                if (!ListenerUtil.mutListener.listen(5040)) {
                    builder.addInterceptor(testsInterceptor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5042)) {
            setCurrentOkHttpProxy(builder);
        }
        return builder.build();
    }
}
