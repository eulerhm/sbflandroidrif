package fr.free.nrw.commons;

import androidx.annotation.NonNull;
import fr.free.nrw.commons.wikidata.cookies.CommonsCookieJar;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.wikipedia.dataclient.okhttp.HttpStatusException;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class OkHttpConnectionFactory {

    private static final String CACHE_DIR_NAME = "okhttp-cache";

    private static final long NET_CACHE_SIZE = 64 * 1024 * 1024;

    public static OkHttpClient CLIENT;

    @NonNull
    public static OkHttpClient getClient(final CommonsCookieJar cookieJar) {
        if (!ListenerUtil.mutListener.listen(9674)) {
            if (CLIENT == null) {
                if (!ListenerUtil.mutListener.listen(9673)) {
                    CLIENT = createClient(cookieJar);
                }
            }
        }
        return CLIENT;
    }

    @NonNull
    private static OkHttpClient createClient(final CommonsCookieJar cookieJar) {
        return new OkHttpClient.Builder().cookieJar(cookieJar).cache(new Cache(new File(CommonsApplication.getInstance().getCacheDir(), CACHE_DIR_NAME), NET_CACHE_SIZE)).connectTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).addInterceptor(getLoggingInterceptor()).addInterceptor(new UnsuccessfulResponseInterceptor()).addInterceptor(new CommonHeaderRequestInterceptor()).build();
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(Level.BASIC);
        if (!ListenerUtil.mutListener.listen(9675)) {
            httpLoggingInterceptor.redactHeader("Authorization");
        }
        if (!ListenerUtil.mutListener.listen(9676)) {
            httpLoggingInterceptor.redactHeader("Cookie");
        }
        return httpLoggingInterceptor;
    }

    private static class CommonHeaderRequestInterceptor implements Interceptor {

        @Override
        @NonNull
        public Response intercept(@NonNull final Chain chain) throws IOException {
            final Request request = chain.request().newBuilder().header("User-Agent", CommonsApplication.getInstance().getUserAgent()).build();
            return chain.proceed(request);
        }
    }

    public static class UnsuccessfulResponseInterceptor implements Interceptor {

        private static final List<String> DO_NOT_INTERCEPT = Collections.singletonList("api.php?format=json&formatversion=2&errorformat=plaintext&action=upload&ignorewarnings=1");

        private static final String ERRORS_PREFIX = "{\"error";

        @Override
        @NonNull
        public Response intercept(@NonNull final Chain chain) throws IOException {
            final Response rsp = chain.proceed(chain.request());
            // Do not intercept certain requests and let the caller handle the errors
            if (isExcludedUrl(chain.request())) {
                return rsp;
            }
            if (rsp.isSuccessful()) {
                try (final ResponseBody responseBody = rsp.peekBody(ERRORS_PREFIX.length())) {
                    if (!ListenerUtil.mutListener.listen(9678)) {
                        if (ERRORS_PREFIX.equals(responseBody.string())) {
                            try (final ResponseBody body = rsp.body()) {
                                throw new IOException(body.string());
                            }
                        }
                    }
                } catch (final IOException e) {
                    if (!ListenerUtil.mutListener.listen(9677)) {
                        Timber.e(e);
                    }
                }
                return rsp;
            }
            throw new HttpStatusException(rsp);
        }

        private boolean isExcludedUrl(final Request request) {
            final String requestUrl = request.url().toString();
            if (!ListenerUtil.mutListener.listen(9680)) {
                {
                    long _loopCounter163 = 0;
                    for (final String url : DO_NOT_INTERCEPT) {
                        ListenerUtil.loopListener.listen("_loopCounter163", ++_loopCounter163);
                        if (!ListenerUtil.mutListener.listen(9679)) {
                            if (requestUrl.contains(url)) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    private OkHttpConnectionFactory() {
    }
}
