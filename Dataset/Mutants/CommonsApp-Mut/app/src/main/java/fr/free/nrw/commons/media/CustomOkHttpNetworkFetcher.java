package fr.free.nrw.commons.media;

import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// https://github.com/facebook/fresco/blob/master/imagepipeline-backends/imagepipeline-okhttp3/src/main/java/com/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher.java
@Singleton
public class CustomOkHttpNetworkFetcher extends BaseNetworkFetcher<CustomOkHttpNetworkFetcher.OkHttpNetworkFetchState> {

    private static final String QUEUE_TIME = "queue_time";

    private static final String FETCH_TIME = "fetch_time";

    private static final String TOTAL_TIME = "total_time";

    private static final String IMAGE_SIZE = "image_size";

    private final Call.Factory mCallFactory;

    @Nullable
    private final CacheControl mCacheControl;

    private final Executor mCancellationExecutor;

    private final JsonKvStore defaultKvStore;

    /**
     * @param okHttpClient client to use
     */
    @Inject
    public CustomOkHttpNetworkFetcher(final OkHttpClient okHttpClient, @Named("default_preferences") final JsonKvStore defaultKvStore) {
        this(okHttpClient, okHttpClient.dispatcher().executorService(), defaultKvStore);
    }

    /**
     * @param callFactory          custom {@link Call.Factory} for fetching image from the network
     * @param cancellationExecutor executor on which fetching cancellation is performed if
     *                             cancellation is requested from the UI Thread
     */
    public CustomOkHttpNetworkFetcher(final Call.Factory callFactory, final Executor cancellationExecutor, final JsonKvStore defaultKvStore) {
        this(callFactory, cancellationExecutor, defaultKvStore, true);
    }

    /**
     * @param callFactory          custom {@link Call.Factory} for fetching image from the network
     * @param cancellationExecutor executor on which fetching cancellation is performed if
     *                             cancellation is requested from the UI Thread
     * @param disableOkHttpCache   true if network requests should not be cached by OkHttp
     */
    public CustomOkHttpNetworkFetcher(final Call.Factory callFactory, final Executor cancellationExecutor, final JsonKvStore defaultKvStore, final boolean disableOkHttpCache) {
        this.defaultKvStore = defaultKvStore;
        mCallFactory = callFactory;
        mCancellationExecutor = cancellationExecutor;
        mCacheControl = disableOkHttpCache ? new CacheControl.Builder().noStore().build() : null;
    }

    @Override
    public OkHttpNetworkFetchState createFetchState(final Consumer<EncodedImage> consumer, final ProducerContext context) {
        return new OkHttpNetworkFetchState(consumer, context);
    }

    @Override
    public void fetch(final OkHttpNetworkFetchState fetchState, final NetworkFetcher.Callback callback) {
        if (!ListenerUtil.mutListener.listen(9250)) {
            fetchState.submitTime = SystemClock.elapsedRealtime();
        }
        final Uri uri = fetchState.getUri();
        try {
            if (!ListenerUtil.mutListener.listen(9254)) {
                if (defaultKvStore.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, false)) {
                    if (!ListenerUtil.mutListener.listen(9252)) {
                        Timber.d("Skipping loading of image as limited connection mode is enabled");
                    }
                    if (!ListenerUtil.mutListener.listen(9253)) {
                        callback.onFailure(new Exception("Failing image request as limited connection mode is enabled"));
                    }
                    return;
                }
            }
            final Request.Builder requestBuilder = new Request.Builder().url(uri.toString()).get();
            if (!ListenerUtil.mutListener.listen(9256)) {
                if (mCacheControl != null) {
                    if (!ListenerUtil.mutListener.listen(9255)) {
                        requestBuilder.cacheControl(mCacheControl);
                    }
                }
            }
            final BytesRange bytesRange = fetchState.getContext().getImageRequest().getBytesRange();
            if (!ListenerUtil.mutListener.listen(9258)) {
                if (bytesRange != null) {
                    if (!ListenerUtil.mutListener.listen(9257)) {
                        requestBuilder.addHeader("Range", bytesRange.toHttpRangeHeaderValue());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9259)) {
                fetchWithRequest(fetchState, callback, requestBuilder.build());
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(9251)) {
                // handle error while creating the request
                callback.onFailure(e);
            }
        }
    }

    @Override
    public void onFetchCompletion(final OkHttpNetworkFetchState fetchState, final int byteSize) {
        if (!ListenerUtil.mutListener.listen(9260)) {
            fetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
        }
    }

    @Override
    public Map<String, String> getExtraMap(final OkHttpNetworkFetchState fetchState, final int byteSize) {
        final Map<String, String> extraMap = new HashMap<>(4);
        if (!ListenerUtil.mutListener.listen(9265)) {
            extraMap.put(QUEUE_TIME, Long.toString((ListenerUtil.mutListener.listen(9264) ? (fetchState.responseTime % fetchState.submitTime) : (ListenerUtil.mutListener.listen(9263) ? (fetchState.responseTime / fetchState.submitTime) : (ListenerUtil.mutListener.listen(9262) ? (fetchState.responseTime * fetchState.submitTime) : (ListenerUtil.mutListener.listen(9261) ? (fetchState.responseTime + fetchState.submitTime) : (fetchState.responseTime - fetchState.submitTime)))))));
        }
        if (!ListenerUtil.mutListener.listen(9270)) {
            extraMap.put(FETCH_TIME, Long.toString((ListenerUtil.mutListener.listen(9269) ? (fetchState.fetchCompleteTime % fetchState.responseTime) : (ListenerUtil.mutListener.listen(9268) ? (fetchState.fetchCompleteTime / fetchState.responseTime) : (ListenerUtil.mutListener.listen(9267) ? (fetchState.fetchCompleteTime * fetchState.responseTime) : (ListenerUtil.mutListener.listen(9266) ? (fetchState.fetchCompleteTime + fetchState.responseTime) : (fetchState.fetchCompleteTime - fetchState.responseTime)))))));
        }
        if (!ListenerUtil.mutListener.listen(9275)) {
            extraMap.put(TOTAL_TIME, Long.toString((ListenerUtil.mutListener.listen(9274) ? (fetchState.fetchCompleteTime % fetchState.submitTime) : (ListenerUtil.mutListener.listen(9273) ? (fetchState.fetchCompleteTime / fetchState.submitTime) : (ListenerUtil.mutListener.listen(9272) ? (fetchState.fetchCompleteTime * fetchState.submitTime) : (ListenerUtil.mutListener.listen(9271) ? (fetchState.fetchCompleteTime + fetchState.submitTime) : (fetchState.fetchCompleteTime - fetchState.submitTime)))))));
        }
        if (!ListenerUtil.mutListener.listen(9276)) {
            extraMap.put(IMAGE_SIZE, Integer.toString(byteSize));
        }
        return extraMap;
    }

    protected void fetchWithRequest(final OkHttpNetworkFetchState fetchState, final NetworkFetcher.Callback callback, final Request request) {
        final Call call = mCallFactory.newCall(request);
        if (!ListenerUtil.mutListener.listen(9278)) {
            fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() {

                @Override
                public void onCancellationRequested() {
                    if (!ListenerUtil.mutListener.listen(9277)) {
                        onFetchCancellationRequested(call);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9281)) {
            call.enqueue(new okhttp3.Callback() {

                @Override
                public void onResponse(final Call call, final Response response) {
                    if (!ListenerUtil.mutListener.listen(9279)) {
                        onFetchResponse(fetchState, call, response, callback);
                    }
                }

                @Override
                public void onFailure(final Call call, final IOException e) {
                    if (!ListenerUtil.mutListener.listen(9280)) {
                        handleException(call, e, callback);
                    }
                }
            });
        }
    }

    private void onFetchCancellationRequested(final Call call) {
        if (!ListenerUtil.mutListener.listen(9284)) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                if (!ListenerUtil.mutListener.listen(9283)) {
                    call.cancel();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9282)) {
                    mCancellationExecutor.execute(call::cancel);
                }
            }
        }
    }

    private void onFetchResponse(final OkHttpNetworkFetchState fetchState, final Call call, final Response response, final NetworkFetcher.Callback callback) {
        if (!ListenerUtil.mutListener.listen(9285)) {
            fetchState.responseTime = SystemClock.elapsedRealtime();
        }
        try (final ResponseBody body = response.body()) {
            if (!ListenerUtil.mutListener.listen(9288)) {
                if (!response.isSuccessful()) {
                    if (!ListenerUtil.mutListener.listen(9287)) {
                        handleException(call, new IOException("Unexpected HTTP code " + response), callback);
                    }
                    return;
                }
            }
            final BytesRange responseRange = BytesRange.fromContentRangeHeader(response.header("Content-Range"));
            if (!ListenerUtil.mutListener.listen(9293)) {
                if ((ListenerUtil.mutListener.listen(9290) ? (responseRange != null || !((ListenerUtil.mutListener.listen(9289) ? (responseRange.from == 0 || responseRange.to == BytesRange.TO_END_OF_CONTENT) : (responseRange.from == 0 && responseRange.to == BytesRange.TO_END_OF_CONTENT)))) : (responseRange != null && !((ListenerUtil.mutListener.listen(9289) ? (responseRange.from == 0 || responseRange.to == BytesRange.TO_END_OF_CONTENT) : (responseRange.from == 0 && responseRange.to == BytesRange.TO_END_OF_CONTENT)))))) {
                    if (!ListenerUtil.mutListener.listen(9291)) {
                        // Only treat as a partial image if the range is not all of the content
                        fetchState.setResponseBytesRange(responseRange);
                    }
                    if (!ListenerUtil.mutListener.listen(9292)) {
                        fetchState.setOnNewResultStatusFlags(Consumer.IS_PARTIAL_RESULT);
                    }
                }
            }
            long contentLength = body.contentLength();
            if (!ListenerUtil.mutListener.listen(9300)) {
                if ((ListenerUtil.mutListener.listen(9298) ? (contentLength >= 0) : (ListenerUtil.mutListener.listen(9297) ? (contentLength <= 0) : (ListenerUtil.mutListener.listen(9296) ? (contentLength > 0) : (ListenerUtil.mutListener.listen(9295) ? (contentLength != 0) : (ListenerUtil.mutListener.listen(9294) ? (contentLength == 0) : (contentLength < 0))))))) {
                    if (!ListenerUtil.mutListener.listen(9299)) {
                        contentLength = 0;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9301)) {
                callback.onResponse(body.byteStream(), (int) contentLength);
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(9286)) {
                handleException(call, e, callback);
            }
        }
    }

    /**
     * Handles exceptions.
     *
     * <p>OkHttp notifies callers of cancellations via an IOException. If IOException is caught
     * after request cancellation, then the exception is interpreted as successful cancellation and
     * onCancellation is called. Otherwise onFailure is called.
     */
    private void handleException(final Call call, final Exception e, final Callback callback) {
        if (!ListenerUtil.mutListener.listen(9304)) {
            if (call.isCanceled()) {
                if (!ListenerUtil.mutListener.listen(9303)) {
                    callback.onCancellation();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9302)) {
                    callback.onFailure(e);
                }
            }
        }
    }

    public static class OkHttpNetworkFetchState extends FetchState {

        public long submitTime;

        public long responseTime;

        public long fetchCompleteTime;

        public OkHttpNetworkFetchState(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }
}
