package org.owntracks.android.services;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.owntracks.android.BuildConfig;
import org.owntracks.android.R;
import org.owntracks.android.model.messages.MessageBase;
import org.owntracks.android.services.MessageProcessor.EndpointState;
import org.owntracks.android.services.worker.Scheduler;
import org.owntracks.android.support.Parser;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.support.SocketFactory;
import org.owntracks.android.support.interfaces.ConfigurationIncompleteException;
import org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.X509TrustManager;
import okhttp3.CacheControl;
import okhttp3.ConnectionPool;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageProcessorEndpointHttp extends MessageProcessorEndpoint implements OnModeChangedPreferenceChangedListener {

    public static final int MODE_ID = 3;

    // Headers according to https://github.com/owntracks/recorder#http-mode
    static final String HEADER_USERNAME = "X-Limit-U";

    static final String HEADER_DEVICE = "X-Limit-D";

    private static final String HEADER_USERAGENT = "User-Agent";

    static final String METHOD = "POST";

    static final String HEADER_AUTHORIZATION = "Authorization";

    private static String httpEndpointHeaderUser = "";

    private static String httpEndpointHeaderDevice = "";

    private static String httpEndpointHeaderPassword = "";

    private static OkHttpClient mHttpClient;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static final String USERAGENT = "Owntracks/" + BuildConfig.VERSION_CODE;

    private static final String HTTPTOPIC = "owntracks/http/";

    private Preferences preferences;

    private Parser parser;

    private Scheduler scheduler;

    private Context applicationContext;

    private HttpUrl httpEndpoint;

    public MessageProcessorEndpointHttp(MessageProcessor messageProcessor, Parser parser, Preferences preferences, Scheduler scheduler, Context applicationContext) {
        super(messageProcessor);
        if (!ListenerUtil.mutListener.listen(709)) {
            this.parser = parser;
        }
        if (!ListenerUtil.mutListener.listen(710)) {
            this.preferences = preferences;
        }
        if (!ListenerUtil.mutListener.listen(711)) {
            this.scheduler = scheduler;
        }
        if (!ListenerUtil.mutListener.listen(712)) {
            this.applicationContext = applicationContext;
        }
        if (!ListenerUtil.mutListener.listen(713)) {
            preferences.registerOnPreferenceChangedListener(this);
        }
        if (!ListenerUtil.mutListener.listen(714)) {
            loadEndpointUrl();
        }
    }

    @Override
    public void onCreateFromProcessor() {
        try {
            if (!ListenerUtil.mutListener.listen(716)) {
                checkConfigurationComplete();
            }
        } catch (ConfigurationIncompleteException e) {
            if (!ListenerUtil.mutListener.listen(715)) {
                messageProcessor.onEndpointStateChanged(EndpointState.ERROR_CONFIGURATION.withError(e));
            }
        }
    }

    @Nullable
    private SocketFactory getSocketFactory() {
        String tlsCaCrt = preferences.getTlsCaCrt();
        String tlsClientCrt = preferences.getTlsClientCrt();
        if ((ListenerUtil.mutListener.listen(727) ? ((ListenerUtil.mutListener.listen(721) ? (tlsCaCrt.length() >= 0) : (ListenerUtil.mutListener.listen(720) ? (tlsCaCrt.length() <= 0) : (ListenerUtil.mutListener.listen(719) ? (tlsCaCrt.length() > 0) : (ListenerUtil.mutListener.listen(718) ? (tlsCaCrt.length() < 0) : (ListenerUtil.mutListener.listen(717) ? (tlsCaCrt.length() != 0) : (tlsCaCrt.length() == 0)))))) || (ListenerUtil.mutListener.listen(726) ? (tlsClientCrt.length() >= 0) : (ListenerUtil.mutListener.listen(725) ? (tlsClientCrt.length() <= 0) : (ListenerUtil.mutListener.listen(724) ? (tlsClientCrt.length() > 0) : (ListenerUtil.mutListener.listen(723) ? (tlsClientCrt.length() < 0) : (ListenerUtil.mutListener.listen(722) ? (tlsClientCrt.length() != 0) : (tlsClientCrt.length() == 0))))))) : ((ListenerUtil.mutListener.listen(721) ? (tlsCaCrt.length() >= 0) : (ListenerUtil.mutListener.listen(720) ? (tlsCaCrt.length() <= 0) : (ListenerUtil.mutListener.listen(719) ? (tlsCaCrt.length() > 0) : (ListenerUtil.mutListener.listen(718) ? (tlsCaCrt.length() < 0) : (ListenerUtil.mutListener.listen(717) ? (tlsCaCrt.length() != 0) : (tlsCaCrt.length() == 0)))))) && (ListenerUtil.mutListener.listen(726) ? (tlsClientCrt.length() >= 0) : (ListenerUtil.mutListener.listen(725) ? (tlsClientCrt.length() <= 0) : (ListenerUtil.mutListener.listen(724) ? (tlsClientCrt.length() > 0) : (ListenerUtil.mutListener.listen(723) ? (tlsClientCrt.length() < 0) : (ListenerUtil.mutListener.listen(722) ? (tlsClientCrt.length() != 0) : (tlsClientCrt.length() == 0))))))))) {
            return null;
        }
        SocketFactory.SocketFactoryOptions socketFactoryOptions = new SocketFactory.SocketFactoryOptions();
        if ((ListenerUtil.mutListener.listen(732) ? (tlsCaCrt.length() >= 0) : (ListenerUtil.mutListener.listen(731) ? (tlsCaCrt.length() <= 0) : (ListenerUtil.mutListener.listen(730) ? (tlsCaCrt.length() < 0) : (ListenerUtil.mutListener.listen(729) ? (tlsCaCrt.length() != 0) : (ListenerUtil.mutListener.listen(728) ? (tlsCaCrt.length() == 0) : (tlsCaCrt.length() > 0))))))) {
            try {
                if (!ListenerUtil.mutListener.listen(734)) {
                    socketFactoryOptions.withCaInputStream(applicationContext.openFileInput(tlsCaCrt));
                }
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(733)) {
                    Timber.e(e);
                }
                return null;
            }
        }
        if ((ListenerUtil.mutListener.listen(739) ? (tlsClientCrt.length() >= 0) : (ListenerUtil.mutListener.listen(738) ? (tlsClientCrt.length() <= 0) : (ListenerUtil.mutListener.listen(737) ? (tlsClientCrt.length() < 0) : (ListenerUtil.mutListener.listen(736) ? (tlsClientCrt.length() != 0) : (ListenerUtil.mutListener.listen(735) ? (tlsClientCrt.length() == 0) : (tlsClientCrt.length() > 0))))))) {
            try {
                if (!ListenerUtil.mutListener.listen(741)) {
                    socketFactoryOptions.withClientP12InputStream(applicationContext.openFileInput(tlsClientCrt)).withClientP12Password(preferences.getTlsClientCrtPassword());
                }
            } catch (FileNotFoundException e1) {
                if (!ListenerUtil.mutListener.listen(740)) {
                    Timber.e(e1);
                }
                return null;
            }
        }
        try {
            return new SocketFactory(socketFactoryOptions);
        } catch (Exception e) {
            return null;
        }
    }

    private OkHttpClient getHttpClient() {
        if (!ListenerUtil.mutListener.listen(742)) {
            if (preferences.getDontReuseHttpClient()) {
                return createHttpClient();
            }
        }
        if (!ListenerUtil.mutListener.listen(744)) {
            if (mHttpClient == null)
                if (!ListenerUtil.mutListener.listen(743)) {
                    mHttpClient = createHttpClient();
                }
        }
        return mHttpClient;
    }

    private OkHttpClient createHttpClient() {
        if (!ListenerUtil.mutListener.listen(745)) {
            Timber.d("creating new HTTP client instance");
        }
        SocketFactory f = getSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder().followRedirects(true).followSslRedirects(true).connectTimeout(15, TimeUnit.SECONDS).connectionPool(new ConnectionPool(1, 1, TimeUnit.MICROSECONDS)).retryOnConnectionFailure(false).protocols(Collections.singletonList(Protocol.HTTP_1_1)).cache(null);
        if (!ListenerUtil.mutListener.listen(747)) {
            if (f != null) {
                if (!ListenerUtil.mutListener.listen(746)) {
                    builder.sslSocketFactory(f, (X509TrustManager) f.getTrustManagers()[0]);
                }
            }
        }
        return builder.build();
    }

    private void loadEndpointUrl() {
        try {
            if (!ListenerUtil.mutListener.listen(750)) {
                httpEndpointHeaderUser = preferences.getUsername();
            }
            if (!ListenerUtil.mutListener.listen(751)) {
                httpEndpointHeaderDevice = preferences.getDeviceId();
            }
            if (!ListenerUtil.mutListener.listen(752)) {
                httpEndpoint = HttpUrl.get(preferences.getUrl());
            }
            if (!ListenerUtil.mutListener.listen(757)) {
                if ((ListenerUtil.mutListener.listen(753) ? (!httpEndpoint.username().isEmpty() || !httpEndpoint.password().isEmpty()) : (!httpEndpoint.username().isEmpty() && !httpEndpoint.password().isEmpty()))) {
                    if (!ListenerUtil.mutListener.listen(755)) {
                        httpEndpointHeaderUser = httpEndpoint.username();
                    }
                    if (!ListenerUtil.mutListener.listen(756)) {
                        httpEndpointHeaderPassword = httpEndpoint.password();
                    }
                } else if (!preferences.getPassword().trim().equals("")) {
                    if (!ListenerUtil.mutListener.listen(754)) {
                        httpEndpointHeaderPassword = preferences.getPassword();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(758)) {
                messageProcessor.onEndpointStateChanged(EndpointState.IDLE);
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(748)) {
                httpEndpoint = null;
            }
            if (!ListenerUtil.mutListener.listen(749)) {
                messageProcessor.onEndpointStateChanged(EndpointState.ERROR_CONFIGURATION.withError(e));
            }
        }
    }

    @Nullable
    Request getRequest(MessageBase message) {
        try {
            if (!ListenerUtil.mutListener.listen(759)) {
                this.checkConfigurationComplete();
            }
        } catch (ConfigurationIncompleteException e) {
            return null;
        }
        if (!ListenerUtil.mutListener.listen(760)) {
            Timber.d("url:%s, messageId:%s", this.httpEndpoint, message.getMessageId());
        }
        String body;
        try {
            body = message.toJson(parser);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(761)) {
                // Message serialization failed. This shouldn't happen.
                messageProcessor.onEndpointStateChanged(EndpointState.ERROR.withMessage(e.getMessage()));
            }
            return null;
        }
        // Setting httpEndpoint to null will make sure no message can be send until the problem is corrected.
        try {
            Request.Builder request = new Request.Builder().url(this.httpEndpoint).header(HEADER_USERAGENT, USERAGENT).method(METHOD, RequestBody.create(JSON, body));
            if (!ListenerUtil.mutListener.listen(767)) {
                if ((ListenerUtil.mutListener.listen(765) ? (isSet(httpEndpointHeaderUser) || isSet(httpEndpointHeaderPassword)) : (isSet(httpEndpointHeaderUser) && isSet(httpEndpointHeaderPassword)))) {
                    if (!ListenerUtil.mutListener.listen(766)) {
                        request.header(HEADER_AUTHORIZATION, Credentials.basic(httpEndpointHeaderUser, httpEndpointHeaderPassword));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(769)) {
                if (isSet(httpEndpointHeaderUser)) {
                    if (!ListenerUtil.mutListener.listen(768)) {
                        request.header(HEADER_USERNAME, httpEndpointHeaderUser);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(771)) {
                if (isSet(httpEndpointHeaderDevice)) {
                    if (!ListenerUtil.mutListener.listen(770)) {
                        request.header(HEADER_DEVICE, httpEndpointHeaderDevice);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(772)) {
                request.cacheControl(CacheControl.FORCE_NETWORK);
            }
            return request.build();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(762)) {
                Timber.e(e, "invalid header specified");
            }
            if (!ListenerUtil.mutListener.listen(763)) {
                messageProcessor.onEndpointStateChanged(EndpointState.ERROR_CONFIGURATION.withError(e));
            }
            if (!ListenerUtil.mutListener.listen(764)) {
                httpEndpoint = null;
            }
            return null;
        }
    }

    private static boolean isSet(String str) {
        return (ListenerUtil.mutListener.listen(778) ? (str != null || (ListenerUtil.mutListener.listen(777) ? (str.length() >= 0) : (ListenerUtil.mutListener.listen(776) ? (str.length() <= 0) : (ListenerUtil.mutListener.listen(775) ? (str.length() < 0) : (ListenerUtil.mutListener.listen(774) ? (str.length() != 0) : (ListenerUtil.mutListener.listen(773) ? (str.length() == 0) : (str.length() > 0))))))) : (str != null && (ListenerUtil.mutListener.listen(777) ? (str.length() >= 0) : (ListenerUtil.mutListener.listen(776) ? (str.length() <= 0) : (ListenerUtil.mutListener.listen(775) ? (str.length() < 0) : (ListenerUtil.mutListener.listen(774) ? (str.length() != 0) : (ListenerUtil.mutListener.listen(773) ? (str.length() == 0) : (str.length() > 0))))))));
    }

    void sendMessage(MessageBase message) throws OutgoingMessageSendingException {
        long messageId = message.getMessageId();
        Request request = getRequest(message);
        if (!ListenerUtil.mutListener.listen(780)) {
            if (request == null) {
                if (!ListenerUtil.mutListener.listen(779)) {
                    messageProcessor.onMessageDeliveryFailedFinal(message.getMessageId());
                }
                return;
            }
        }
        try (Response response = getHttpClient().newCall(request).execute()) {
            if (!ListenerUtil.mutListener.listen(796)) {
                // Message was send. Handle delivered message
                if ((response.isSuccessful())) {
                    if (!ListenerUtil.mutListener.listen(787)) {
                        Timber.d("request was successful: %s", response);
                    }
                    if (!ListenerUtil.mutListener.listen(795)) {
                        // Handle response
                        if (response.body() != null) {
                            try {
                                MessageBase[] result = parser.fromJson(response.body().byteStream());
                                if (!ListenerUtil.mutListener.listen(792)) {
                                    // TODO apply i18n here
                                    messageProcessor.onEndpointStateChanged(EndpointState.IDLE.withMessage(String.format(Locale.ROOT, "Response %d, (%d msgs received)", response.code(), result.length)));
                                }
                                if (!ListenerUtil.mutListener.listen(794)) {
                                    {
                                        long _loopCounter10 = 0;
                                        for (MessageBase aResult : result) {
                                            ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                                            if (!ListenerUtil.mutListener.listen(793)) {
                                                onMessageReceived(aResult);
                                            }
                                        }
                                    }
                                }
                            } catch (JsonProcessingException e) {
                                if (!ListenerUtil.mutListener.listen(788)) {
                                    Timber.e("JsonParseException HTTP status: %s", response.code());
                                }
                                if (!ListenerUtil.mutListener.listen(789)) {
                                    messageProcessor.onEndpointStateChanged(EndpointState.IDLE.withMessage(String.format(Locale.ROOT, "HTTP status %d, JsonParseException", response.code())));
                                }
                            } catch (Parser.EncryptionException e) {
                                if (!ListenerUtil.mutListener.listen(790)) {
                                    Timber.e("JsonParseException HTTP status: %s", response.code());
                                }
                                if (!ListenerUtil.mutListener.listen(791)) {
                                    messageProcessor.onEndpointStateChanged(EndpointState.ERROR.withMessage(String.format(Locale.ROOT, "HTTP status: %d, EncryptionException", response.code())));
                                }
                            }
                        }
                    }
                } else {
                    Exception httpException = new Exception(String.format("HTTP request failed. Status: %s", response.code()));
                    if (!ListenerUtil.mutListener.listen(784)) {
                        Timber.e(httpException);
                    }
                    if (!ListenerUtil.mutListener.listen(785)) {
                        messageProcessor.onEndpointStateChanged(EndpointState.ERROR.withMessage(String.format(Locale.ROOT, "HTTP code %d", response.code())));
                    }
                    if (!ListenerUtil.mutListener.listen(786)) {
                        messageProcessor.onMessageDeliveryFailed(messageId);
                    }
                    throw new OutgoingMessageSendingException(httpException);
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(781)) {
                Timber.e(e, "HTTP Delivery failed ");
            }
            if (!ListenerUtil.mutListener.listen(782)) {
                messageProcessor.onEndpointStateChanged(EndpointState.ERROR.withError(e));
            }
            if (!ListenerUtil.mutListener.listen(783)) {
                messageProcessor.onMessageDeliveryFailed(messageId);
            }
            throw new OutgoingMessageSendingException(e);
        }
        if (!ListenerUtil.mutListener.listen(797)) {
            messageProcessor.onMessageDelivered(message);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(798)) {
            scheduler.cancelHttpTasks();
        }
        if (!ListenerUtil.mutListener.listen(799)) {
            preferences.unregisterOnPreferenceChangedListener(this);
        }
    }

    @Override
    public void onAttachAfterModeChanged() {
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(807)) {
            if ((ListenerUtil.mutListener.listen(802) ? ((ListenerUtil.mutListener.listen(801) ? ((ListenerUtil.mutListener.listen(800) ? (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyPassword).equals(key)) : ((ListenerUtil.mutListener.listen(800) ? (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyPassword).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyDeviceId).equals(key)) : ((ListenerUtil.mutListener.listen(801) ? ((ListenerUtil.mutListener.listen(800) ? (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyPassword).equals(key)) : ((ListenerUtil.mutListener.listen(800) ? (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyURL).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyUsername).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyPassword).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyDeviceId).equals(key)))) {
                if (!ListenerUtil.mutListener.listen(806)) {
                    loadEndpointUrl();
                }
            } else if ((ListenerUtil.mutListener.listen(804) ? ((ListenerUtil.mutListener.listen(803) ? (preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrt).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrtPassword).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrt).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrtPassword).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyTLSCaCrt).equals(key)) : ((ListenerUtil.mutListener.listen(803) ? (preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrt).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrtPassword).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrt).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyTLSClientCrtPassword).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyTLSCaCrt).equals(key))))
                if (!ListenerUtil.mutListener.listen(805)) {
                    mHttpClient = null;
                }
        }
    }

    @Override
    public void checkConfigurationComplete() throws ConfigurationIncompleteException {
        if (!ListenerUtil.mutListener.listen(808)) {
            if (this.httpEndpoint == null) {
                throw new ConfigurationIncompleteException("HTTP Endpoint is missing");
            }
        }
    }

    @Override
    int getModeId() {
        return MODE_ID;
    }

    @Override
    protected MessageBase onFinalizeMessage(MessageBase message) {
        if (!ListenerUtil.mutListener.listen(810)) {
            // Build pseudo topic based on tid
            if (message.hasTrackerId()) {
                if (!ListenerUtil.mutListener.listen(809)) {
                    message.setTopic(HTTPTOPIC + message.getTrackerId());
                }
            }
        }
        return message;
    }
}
