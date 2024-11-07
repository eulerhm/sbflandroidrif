package net.programmierecke.radiodroid2.players.exoplayer;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static net.programmierecke.radiodroid2.Utils.getMimeType;
import static okhttp3.internal.Util.closeQuietly;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An {@link HttpDataSource} that uses {@link OkHttpClient},
 * retrieves stream's {@link ShoutcastInfo} and {@link StreamLiveInfo} if any,
 * attempts to reconnect if connection is lost. These distinguishes it from {@link DefaultHttpDataSource}.
 * <p>
 * When connection is lost attempts to reconnect will made alongside with calling
 * {@link IcyDataSourceListener#onDataSourceConnectionLost()}.
 * After reconnecting time has passed
 * {@link IcyDataSourceListener#onDataSourceConnectionLostIrrecoverably()} will be called.
 */
public class IcyDataSource implements HttpDataSource {

    // 2 minutes
    public static final long DEFAULT_TIME_UNTIL_STOP_RECONNECTING = 2 * 60 * 1000;

    public static final long DEFAULT_DELAY_BETWEEN_RECONNECTIONS = 0;

    public interface IcyDataSourceListener {

        /**
         * Called on first connection and after successful reconnection.
         */
        void onDataSourceConnected();

        /**
         * Called when connection is lost and reconnection attempts will be made.
         */
        void onDataSourceConnectionLost();

        /**
         * Called when data source gives up reconnecting.
         */
        void onDataSourceConnectionLostIrrecoverably();

        void onDataSourceShoutcastInfo(@Nullable ShoutcastInfo shoutcastInfo);

        void onDataSourceStreamLiveInfo(StreamLiveInfo streamLiveInfo);

        void onDataSourceBytesRead(byte[] buffer, int offset, int length);
    }

    private static final String TAG = "IcyDataSource";

    private DataSpec dataSpec;

    private final OkHttpClient httpClient;

    private final TransferListener transferListener;

    private final IcyDataSourceListener dataSourceListener;

    private Request request;

    private ResponseBody responseBody;

    private Map<String, List<String>> responseHeaders;

    int metadataBytesToSkip = 0;

    int remainingUntilMetadata = Integer.MAX_VALUE;

    private boolean opened;

    ShoutcastInfo shoutcastInfo;

    private StreamLiveInfo streamLiveInfo;

    public IcyDataSource(@NonNull OkHttpClient httpClient, @NonNull TransferListener listener, @NonNull IcyDataSourceListener dataSourceListener) {
        this.httpClient = httpClient;
        this.transferListener = listener;
        this.dataSourceListener = dataSourceListener;
    }

    @Override
    public long open(DataSpec dataSpec) throws HttpDataSourceException {
        if (!ListenerUtil.mutListener.listen(667)) {
            close();
        }
        if (!ListenerUtil.mutListener.listen(668)) {
            this.dataSpec = dataSpec;
        }
        final boolean allowGzip = (dataSpec.flags & DataSpec.FLAG_ALLOW_GZIP) != 0;
        HttpUrl url = HttpUrl.parse(dataSpec.uri.toString());
        Request.Builder builder = new Request.Builder().url(url).addHeader("Icy-MetaData", "1");
        if (!ListenerUtil.mutListener.listen(670)) {
            if (!allowGzip) {
                if (!ListenerUtil.mutListener.listen(669)) {
                    builder.addHeader("Accept-Encoding", "identity");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(671)) {
            request = builder.build();
        }
        return connect();
    }

    private long connect() throws HttpDataSourceException {
        Response response;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new HttpDataSourceException("Unable to connect to " + dataSpec.uri.toString(), e, dataSpec, HttpDataSourceException.TYPE_OPEN);
        }
        final int responseCode = response.code();
        if (!ListenerUtil.mutListener.listen(672)) {
            if (!response.isSuccessful()) {
                final Map<String, List<String>> headers = request.headers().toMultimap();
                throw new InvalidResponseCodeException(responseCode, headers, dataSpec);
            }
        }
        if (!ListenerUtil.mutListener.listen(673)) {
            responseBody = response.body();
        }
        assert responseBody != null;
        if (!ListenerUtil.mutListener.listen(674)) {
            responseHeaders = response.headers().toMultimap();
        }
        final MediaType contentType = responseBody.contentType();
        final String type = contentType == null ? getMimeType(dataSpec.uri.toString(), "audio/mpeg") : contentType.toString().toLowerCase();
        if (!ListenerUtil.mutListener.listen(676)) {
            if (!REJECT_PAYWALL_TYPES.evaluate(type)) {
                if (!ListenerUtil.mutListener.listen(675)) {
                    close();
                }
                throw new InvalidContentTypeException(type, dataSpec);
            }
        }
        if (!ListenerUtil.mutListener.listen(677)) {
            opened = true;
        }
        if (!ListenerUtil.mutListener.listen(678)) {
            dataSourceListener.onDataSourceConnected();
        }
        if (!ListenerUtil.mutListener.listen(679)) {
            transferListener.onTransferStart(this, dataSpec, true);
        }
        if ((ListenerUtil.mutListener.listen(680) ? (type.equals("application/vnd.apple.mpegurl") && type.equals("application/x-mpegurl")) : (type.equals("application/vnd.apple.mpegurl") || type.equals("application/x-mpegurl")))) {
            return responseBody.contentLength();
        } else {
            if (!ListenerUtil.mutListener.listen(681)) {
                // try to get shoutcast information from stream connection
                shoutcastInfo = ShoutcastInfo.Decode(response);
            }
            if (!ListenerUtil.mutListener.listen(682)) {
                dataSourceListener.onDataSourceShoutcastInfo(shoutcastInfo);
            }
            if (!ListenerUtil.mutListener.listen(683)) {
                metadataBytesToSkip = 0;
            }
            if (!ListenerUtil.mutListener.listen(686)) {
                if (shoutcastInfo != null) {
                    if (!ListenerUtil.mutListener.listen(685)) {
                        remainingUntilMetadata = shoutcastInfo.metadataOffset;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(684)) {
                        remainingUntilMetadata = Integer.MAX_VALUE;
                    }
                }
            }
            return responseBody.contentLength();
        }
    }

    @Override
    public void close() throws HttpDataSourceException {
        if (!ListenerUtil.mutListener.listen(689)) {
            if (opened) {
                if (!ListenerUtil.mutListener.listen(687)) {
                    opened = false;
                }
                if (!ListenerUtil.mutListener.listen(688)) {
                    transferListener.onTransferEnd(this, dataSpec, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(692)) {
            if (responseBody != null) {
                if (!ListenerUtil.mutListener.listen(690)) {
                    closeQuietly(responseBody);
                }
                if (!ListenerUtil.mutListener.listen(691)) {
                    responseBody = null;
                }
            }
        }
    }

    @Override
    public int read(byte[] buffer, int offset, int readLength) throws HttpDataSourceException {
        try {
            final int bytesTransferred = readInternal(buffer, offset, readLength);
            if (!ListenerUtil.mutListener.listen(694)) {
                transferListener.onBytesTransferred(this, dataSpec, true, bytesTransferred);
            }
            return bytesTransferred;
        } catch (HttpDataSourceException readError) {
            if (!ListenerUtil.mutListener.listen(693)) {
                dataSourceListener.onDataSourceConnectionLost();
            }
            throw readError;
        }
    }

    void sendToDataSourceListenersWithoutMetadata(byte[] buffer, int offset, int bytesAvailable) {
        int canSkip = Math.min(metadataBytesToSkip, bytesAvailable);
        if (!ListenerUtil.mutListener.listen(695)) {
            offset += canSkip;
        }
        if (!ListenerUtil.mutListener.listen(696)) {
            bytesAvailable -= canSkip;
        }
        if (!ListenerUtil.mutListener.listen(697)) {
            remainingUntilMetadata -= canSkip;
        }
        if (!ListenerUtil.mutListener.listen(748)) {
            {
                long _loopCounter12 = 0;
                while ((ListenerUtil.mutListener.listen(747) ? (bytesAvailable >= 0) : (ListenerUtil.mutListener.listen(746) ? (bytesAvailable <= 0) : (ListenerUtil.mutListener.listen(745) ? (bytesAvailable < 0) : (ListenerUtil.mutListener.listen(744) ? (bytesAvailable != 0) : (ListenerUtil.mutListener.listen(743) ? (bytesAvailable == 0) : (bytesAvailable > 0))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                    if (!ListenerUtil.mutListener.listen(722)) {
                        if ((ListenerUtil.mutListener.listen(702) ? (bytesAvailable >= remainingUntilMetadata) : (ListenerUtil.mutListener.listen(701) ? (bytesAvailable <= remainingUntilMetadata) : (ListenerUtil.mutListener.listen(700) ? (bytesAvailable < remainingUntilMetadata) : (ListenerUtil.mutListener.listen(699) ? (bytesAvailable != remainingUntilMetadata) : (ListenerUtil.mutListener.listen(698) ? (bytesAvailable == remainingUntilMetadata) : (bytesAvailable > remainingUntilMetadata))))))) {
                            if (!ListenerUtil.mutListener.listen(711)) {
                                // do we need to handle a metadata frame at all?
                                if ((ListenerUtil.mutListener.listen(707) ? (remainingUntilMetadata >= 0) : (ListenerUtil.mutListener.listen(706) ? (remainingUntilMetadata <= 0) : (ListenerUtil.mutListener.listen(705) ? (remainingUntilMetadata < 0) : (ListenerUtil.mutListener.listen(704) ? (remainingUntilMetadata != 0) : (ListenerUtil.mutListener.listen(703) ? (remainingUntilMetadata == 0) : (remainingUntilMetadata > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(708)) {
                                        // is there any audio data before the metadata frame?
                                        dataSourceListener.onDataSourceBytesRead(buffer, offset, remainingUntilMetadata);
                                    }
                                    if (!ListenerUtil.mutListener.listen(709)) {
                                        offset += remainingUntilMetadata;
                                    }
                                    if (!ListenerUtil.mutListener.listen(710)) {
                                        bytesAvailable -= remainingUntilMetadata;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(720)) {
                                metadataBytesToSkip = (ListenerUtil.mutListener.listen(719) ? ((ListenerUtil.mutListener.listen(715) ? (buffer[offset] % 16) : (ListenerUtil.mutListener.listen(714) ? (buffer[offset] / 16) : (ListenerUtil.mutListener.listen(713) ? (buffer[offset] - 16) : (ListenerUtil.mutListener.listen(712) ? (buffer[offset] + 16) : (buffer[offset] * 16))))) % 1) : (ListenerUtil.mutListener.listen(718) ? ((ListenerUtil.mutListener.listen(715) ? (buffer[offset] % 16) : (ListenerUtil.mutListener.listen(714) ? (buffer[offset] / 16) : (ListenerUtil.mutListener.listen(713) ? (buffer[offset] - 16) : (ListenerUtil.mutListener.listen(712) ? (buffer[offset] + 16) : (buffer[offset] * 16))))) / 1) : (ListenerUtil.mutListener.listen(717) ? ((ListenerUtil.mutListener.listen(715) ? (buffer[offset] % 16) : (ListenerUtil.mutListener.listen(714) ? (buffer[offset] / 16) : (ListenerUtil.mutListener.listen(713) ? (buffer[offset] - 16) : (ListenerUtil.mutListener.listen(712) ? (buffer[offset] + 16) : (buffer[offset] * 16))))) * 1) : (ListenerUtil.mutListener.listen(716) ? ((ListenerUtil.mutListener.listen(715) ? (buffer[offset] % 16) : (ListenerUtil.mutListener.listen(714) ? (buffer[offset] / 16) : (ListenerUtil.mutListener.listen(713) ? (buffer[offset] - 16) : (ListenerUtil.mutListener.listen(712) ? (buffer[offset] + 16) : (buffer[offset] * 16))))) - 1) : ((ListenerUtil.mutListener.listen(715) ? (buffer[offset] % 16) : (ListenerUtil.mutListener.listen(714) ? (buffer[offset] / 16) : (ListenerUtil.mutListener.listen(713) ? (buffer[offset] - 16) : (ListenerUtil.mutListener.listen(712) ? (buffer[offset] + 16) : (buffer[offset] * 16))))) + 1)))));
                            }
                            if (!ListenerUtil.mutListener.listen(721)) {
                                remainingUntilMetadata = shoutcastInfo.metadataOffset + metadataBytesToSkip;
                            }
                        }
                    }
                    int bytesLeft = Math.min(bytesAvailable, remainingUntilMetadata);
                    if (!ListenerUtil.mutListener.listen(739)) {
                        if ((ListenerUtil.mutListener.listen(727) ? (bytesLeft >= metadataBytesToSkip) : (ListenerUtil.mutListener.listen(726) ? (bytesLeft <= metadataBytesToSkip) : (ListenerUtil.mutListener.listen(725) ? (bytesLeft < metadataBytesToSkip) : (ListenerUtil.mutListener.listen(724) ? (bytesLeft != metadataBytesToSkip) : (ListenerUtil.mutListener.listen(723) ? (bytesLeft == metadataBytesToSkip) : (bytesLeft > metadataBytesToSkip))))))) {
                            if (!ListenerUtil.mutListener.listen(737)) {
                                // is there audio data left we need to send?
                                dataSourceListener.onDataSourceBytesRead(buffer, (ListenerUtil.mutListener.listen(732) ? (offset % metadataBytesToSkip) : (ListenerUtil.mutListener.listen(731) ? (offset / metadataBytesToSkip) : (ListenerUtil.mutListener.listen(730) ? (offset * metadataBytesToSkip) : (ListenerUtil.mutListener.listen(729) ? (offset - metadataBytesToSkip) : (offset + metadataBytesToSkip))))), (ListenerUtil.mutListener.listen(736) ? (bytesLeft % metadataBytesToSkip) : (ListenerUtil.mutListener.listen(735) ? (bytesLeft / metadataBytesToSkip) : (ListenerUtil.mutListener.listen(734) ? (bytesLeft * metadataBytesToSkip) : (ListenerUtil.mutListener.listen(733) ? (bytesLeft + metadataBytesToSkip) : (bytesLeft - metadataBytesToSkip))))));
                            }
                            if (!ListenerUtil.mutListener.listen(738)) {
                                metadataBytesToSkip = 0;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(728)) {
                                metadataBytesToSkip -= bytesLeft;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(740)) {
                        offset += bytesLeft;
                    }
                    if (!ListenerUtil.mutListener.listen(741)) {
                        bytesAvailable -= bytesLeft;
                    }
                    if (!ListenerUtil.mutListener.listen(742)) {
                        remainingUntilMetadata -= bytesLeft;
                    }
                }
            }
        }
    }

    private int readInternal(byte[] buffer, int offset, int readLength) throws HttpDataSourceException {
        if (!ListenerUtil.mutListener.listen(749)) {
            if (responseBody == null) {
                throw new HttpDataSourceException(dataSpec, HttpDataSourceException.TYPE_READ);
            }
        }
        InputStream stream = responseBody.byteStream();
        int bytesRead = 0;
        try {
            if (!ListenerUtil.mutListener.listen(750)) {
                bytesRead = stream.read(buffer, offset, readLength);
            }
        } catch (IOException e) {
            throw new HttpDataSourceException(e, dataSpec, HttpDataSourceException.TYPE_READ);
        }
        if (!ListenerUtil.mutListener.listen(751)) {
            sendToDataSourceListenersWithoutMetadata(buffer, offset, bytesRead);
        }
        return bytesRead;
    }

    @Override
    public Uri getUri() {
        return dataSpec.uri;
    }

    @Override
    public void setRequestProperty(String name, String value) {
    }

    @Override
    public void clearRequestProperty(String name) {
    }

    @Override
    public void clearAllRequestProperties() {
    }

    @Override
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public int getResponseCode() {
        return 0;
    }

    @Override
    public void addTransferListener(TransferListener transferListener) {
    }
}
