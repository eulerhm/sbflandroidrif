package org.owntracks.android.ui.preferences.load;

import android.content.ContentResolver;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.apache.commons.codec.binary.Base64;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;
import org.owntracks.android.data.repos.WaypointsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.messages.MessageBase;
import org.owntracks.android.model.messages.MessageConfiguration;
import org.owntracks.android.support.Parser;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class LoadViewModel extends BaseViewModel<LoadMvvm.View> implements LoadMvvm.ViewModel<LoadMvvm.View> {

    private final Preferences preferences;

    private final Parser parser;

    private final WaypointsRepo waypointsRepo;

    private MessageConfiguration configuration;

    private String displayedConfiguration;

    private ImportStatus importStatus = ImportStatus.LOADING;

    @Inject
    public LoadViewModel(Preferences preferences, Parser parser, WaypointsRepo waypointsRepo) {
        this.preferences = preferences;
        this.parser = parser;
        this.waypointsRepo = waypointsRepo;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull LoadMvvm.View view) {
        if (!ListenerUtil.mutListener.listen(2143)) {
            super.attachView(savedInstanceState, view);
        }
    }

    private void setConfiguration(String json) throws IOException, Parser.EncryptionException {
        MessageBase message = parser.fromJson(json.getBytes());
        if (!ListenerUtil.mutListener.listen(2149)) {
            if (message instanceof MessageConfiguration) {
                if (!ListenerUtil.mutListener.listen(2144)) {
                    this.configuration = (MessageConfiguration) parser.fromJson(json.getBytes());
                }
                String prettyConfiguration;
                try {
                    prettyConfiguration = parser.toJsonPlainPretty(this.configuration);
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(2145)) {
                        Timber.e(e);
                    }
                    prettyConfiguration = "Unable to parse configuration";
                }
                if (!ListenerUtil.mutListener.listen(2146)) {
                    displayedConfiguration = prettyConfiguration;
                }
                if (!ListenerUtil.mutListener.listen(2147)) {
                    importStatus = ImportStatus.SUCCESS;
                }
                if (!ListenerUtil.mutListener.listen(2148)) {
                    notifyChange();
                }
            } else {
                throw new IOException("Message is not a valid configuration message");
            }
        }
    }

    @Override
    public void saveConfiguration() {
        if (!ListenerUtil.mutListener.listen(2150)) {
            preferences.importFromMessage(configuration);
        }
        if (!ListenerUtil.mutListener.listen(2152)) {
            if (!configuration.getWaypoints().isEmpty()) {
                if (!ListenerUtil.mutListener.listen(2151)) {
                    waypointsRepo.importFromMessage(configuration.getWaypoints());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2153)) {
            getView().showFinishDialog();
        }
    }

    @Override
    public void extractPreferences(byte[] content) {
        try {
            if (!ListenerUtil.mutListener.listen(2155)) {
                setConfiguration(new String(content, StandardCharsets.UTF_8));
            }
        } catch (IOException | Parser.EncryptionException e) {
            if (!ListenerUtil.mutListener.listen(2154)) {
                configurationImportFailed(e);
            }
        }
    }

    @Override
    public void extractPreferences(URI uri) {
        try {
            if (!ListenerUtil.mutListener.listen(2186)) {
                if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
                    if (!ListenerUtil.mutListener.listen(2182)) {
                        // with sufficient testing. Will not work on Android >5 without granting READ_EXTERNAL_STORAGE permission
                        Timber.v("using file:// uri");
                    }
                    BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(uri.getPath())));
                    StringBuilder total = new StringBuilder();
                    String content;
                    if (!ListenerUtil.mutListener.listen(2184)) {
                        {
                            long _loopCounter20 = 0;
                            while ((content = r.readLine()) != null) {
                                ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                                if (!ListenerUtil.mutListener.listen(2183)) {
                                    total.append(content);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2185)) {
                        setConfiguration(total.toString());
                    }
                } else if ((ListenerUtil.mutListener.listen(2157) ? ("owntracks".equals(uri.getScheme()) || "/config".equals(uri.getPath())) : ("owntracks".equals(uri.getScheme()) && "/config".equals(uri.getPath())))) {
                    if (!ListenerUtil.mutListener.listen(2158)) {
                        Timber.v("Importing config using owntracks: scheme");
                    }
                    List<NameValuePair> queryParams = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
                    List<String> urlQueryParam = new ArrayList<>();
                    List<String> configQueryParam = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(2163)) {
                        {
                            long _loopCounter19 = 0;
                            for (NameValuePair queryParam : queryParams) {
                                ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                                if (!ListenerUtil.mutListener.listen(2160)) {
                                    if (queryParam.getName().equals("url")) {
                                        if (!ListenerUtil.mutListener.listen(2159)) {
                                            urlQueryParam.add(queryParam.getValue());
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2162)) {
                                    if (queryParam.getName().equals("inline")) {
                                        if (!ListenerUtil.mutListener.listen(2161)) {
                                            configQueryParam.add(queryParam.getValue());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2181)) {
                        if ((ListenerUtil.mutListener.listen(2168) ? (configQueryParam.size() >= 1) : (ListenerUtil.mutListener.listen(2167) ? (configQueryParam.size() <= 1) : (ListenerUtil.mutListener.listen(2166) ? (configQueryParam.size() > 1) : (ListenerUtil.mutListener.listen(2165) ? (configQueryParam.size() < 1) : (ListenerUtil.mutListener.listen(2164) ? (configQueryParam.size() != 1) : (configQueryParam.size() == 1))))))) {
                            byte[] config = new Base64().decodeBase64(configQueryParam.get(0).getBytes());
                            if (!ListenerUtil.mutListener.listen(2180)) {
                                setConfiguration(new String(config, StandardCharsets.UTF_8));
                            }
                        } else if ((ListenerUtil.mutListener.listen(2173) ? (urlQueryParam.size() >= 1) : (ListenerUtil.mutListener.listen(2172) ? (urlQueryParam.size() <= 1) : (ListenerUtil.mutListener.listen(2171) ? (urlQueryParam.size() > 1) : (ListenerUtil.mutListener.listen(2170) ? (urlQueryParam.size() < 1) : (ListenerUtil.mutListener.listen(2169) ? (urlQueryParam.size() != 1) : (urlQueryParam.size() == 1))))))) {
                            URL remoteConfigUrl = new URL(urlQueryParam.get(0));
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url(remoteConfigUrl).build();
                            if (!ListenerUtil.mutListener.listen(2179)) {
                                client.newCall(request).enqueue(new Callback() {

                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        if (!ListenerUtil.mutListener.listen(2174)) {
                                            configurationImportFailed(new Exception("Failure fetching config from remote URL", e));
                                        }
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        try (ResponseBody responseBody = response.body()) {
                                            if (!ListenerUtil.mutListener.listen(2177)) {
                                                if (!response.isSuccessful()) {
                                                    if (!ListenerUtil.mutListener.listen(2176)) {
                                                        configurationImportFailed(new IOException(String.format("Unexpected status code: %s", response)));
                                                    }
                                                    return;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2178)) {
                                                setConfiguration(responseBody != null ? responseBody.string() : "");
                                            }
                                        } catch (Parser.EncryptionException e) {
                                            if (!ListenerUtil.mutListener.listen(2175)) {
                                                configurationImportFailed(e);
                                            }
                                        }
                                    }
                                });
                            }
                        } else {
                            throw new IOException("Invalid config URL");
                        }
                    }
                } else {
                    throw new IOException("Invalid config URL");
                }
            }
        } catch (OutOfMemoryError | IOException | Parser.EncryptionException | IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(2156)) {
                configurationImportFailed(e);
            }
        }
    }

    @Override
    public String getDisplayedConfiguration() {
        return displayedConfiguration;
    }

    @Override
    public ImportStatus getConfigurationImportStatus() {
        return importStatus;
    }

    @Override
    public void setError(Throwable e) {
        if (!ListenerUtil.mutListener.listen(2187)) {
            configurationImportFailed(e);
        }
    }

    private void configurationImportFailed(Throwable e) {
        if (!ListenerUtil.mutListener.listen(2188)) {
            Timber.e(e);
        }
        if (!ListenerUtil.mutListener.listen(2189)) {
            displayedConfiguration = String.format("Import failed: %s", e.getMessage());
        }
        if (!ListenerUtil.mutListener.listen(2190)) {
            importStatus = ImportStatus.FAILED;
        }
        if (!ListenerUtil.mutListener.listen(2191)) {
            notifyChange();
        }
    }
}
