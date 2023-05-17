package net.programmierecke.radiodroid2.players.mediaplayer;

import android.util.Log;
import androidx.annotation.NonNull;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import net.programmierecke.radiodroid2.recording.Recordable;
import net.programmierecke.radiodroid2.recording.RecordableListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StreamProxy implements Recordable {

    private static final String TAG = "PROXY";

    private static final int MAX_RETRIES = 100;

    private OkHttpClient httpClient;

    private StreamProxyListener callback;

    private RecordableListener recordableListener;

    private String uri;

    private byte[] readBuffer = new byte[256 * 16];

    private volatile String localAddress = null;

    private boolean isStopped = false;

    public StreamProxy(OkHttpClient httpClient, String uri, StreamProxyListener callback) {
        if (!ListenerUtil.mutListener.listen(832)) {
            this.httpClient = httpClient;
        }
        if (!ListenerUtil.mutListener.listen(833)) {
            this.uri = uri;
        }
        if (!ListenerUtil.mutListener.listen(834)) {
            this.callback = callback;
        }
        if (!ListenerUtil.mutListener.listen(835)) {
            createProxy();
        }
    }

    private void createProxy() {
        if (!ListenerUtil.mutListener.listen(837)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(836)) {
                    Log.d(TAG, "thread started");
                }
        }
        if (!ListenerUtil.mutListener.listen(842)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        if (!ListenerUtil.mutListener.listen(839)) {
                            connectToStream();
                        }
                        if (!ListenerUtil.mutListener.listen(841)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(840)) {
                                    Log.d(TAG, "createProxy() ended");
                                }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(838)) {
                            Log.e(TAG, "", e);
                        }
                    }
                }
            }, "StreamProxy").start();
        }
    }

    private void proxyDefaultStream(ShoutcastInfo info, ResponseBody responseBody, OutputStream outStream) throws Exception {
        int bytesUntilMetaData = 0;
        boolean streamHasMetaData = false;
        if (!ListenerUtil.mutListener.listen(846)) {
            if (info != null) {
                if (!ListenerUtil.mutListener.listen(843)) {
                    callback.onFoundShoutcastStream(info, false);
                }
                if (!ListenerUtil.mutListener.listen(844)) {
                    bytesUntilMetaData = info.metadataOffset;
                }
                if (!ListenerUtil.mutListener.listen(845)) {
                    streamHasMetaData = true;
                }
            }
        }
        InputStream inputStream = responseBody.byteStream();
        if (!ListenerUtil.mutListener.listen(876)) {
            {
                long _loopCounter13 = 0;
                while (!isStopped) {
                    ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                    if (!ListenerUtil.mutListener.listen(875)) {
                        if ((ListenerUtil.mutListener.listen(852) ? (!streamHasMetaData && ((ListenerUtil.mutListener.listen(851) ? (bytesUntilMetaData >= 0) : (ListenerUtil.mutListener.listen(850) ? (bytesUntilMetaData <= 0) : (ListenerUtil.mutListener.listen(849) ? (bytesUntilMetaData < 0) : (ListenerUtil.mutListener.listen(848) ? (bytesUntilMetaData != 0) : (ListenerUtil.mutListener.listen(847) ? (bytesUntilMetaData == 0) : (bytesUntilMetaData > 0)))))))) : (!streamHasMetaData || ((ListenerUtil.mutListener.listen(851) ? (bytesUntilMetaData >= 0) : (ListenerUtil.mutListener.listen(850) ? (bytesUntilMetaData <= 0) : (ListenerUtil.mutListener.listen(849) ? (bytesUntilMetaData < 0) : (ListenerUtil.mutListener.listen(848) ? (bytesUntilMetaData != 0) : (ListenerUtil.mutListener.listen(847) ? (bytesUntilMetaData == 0) : (bytesUntilMetaData > 0)))))))))) {
                            int bytesToRead = Math.min(readBuffer.length, inputStream.available());
                            if (!ListenerUtil.mutListener.listen(856)) {
                                if (streamHasMetaData) {
                                    if (!ListenerUtil.mutListener.listen(855)) {
                                        bytesToRead = Math.min(bytesUntilMetaData, bytesToRead);
                                    }
                                }
                            }
                            int readBytes = inputStream.read(readBuffer, 0, bytesToRead);
                            if (!ListenerUtil.mutListener.listen(862)) {
                                if ((ListenerUtil.mutListener.listen(861) ? (readBytes >= 0) : (ListenerUtil.mutListener.listen(860) ? (readBytes <= 0) : (ListenerUtil.mutListener.listen(859) ? (readBytes > 0) : (ListenerUtil.mutListener.listen(858) ? (readBytes < 0) : (ListenerUtil.mutListener.listen(857) ? (readBytes != 0) : (readBytes == 0))))))) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(868)) {
                                if ((ListenerUtil.mutListener.listen(867) ? (readBytes >= 0) : (ListenerUtil.mutListener.listen(866) ? (readBytes <= 0) : (ListenerUtil.mutListener.listen(865) ? (readBytes > 0) : (ListenerUtil.mutListener.listen(864) ? (readBytes != 0) : (ListenerUtil.mutListener.listen(863) ? (readBytes == 0) : (readBytes < 0))))))) {
                                    break;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(870)) {
                                if (streamHasMetaData) {
                                    if (!ListenerUtil.mutListener.listen(869)) {
                                        bytesUntilMetaData -= readBytes;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(871)) {
                                outStream.write(readBuffer, 0, readBytes);
                            }
                            if (!ListenerUtil.mutListener.listen(873)) {
                                if (recordableListener != null) {
                                    if (!ListenerUtil.mutListener.listen(872)) {
                                        recordableListener.onBytesAvailable(readBuffer, 0, readBytes);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(874)) {
                                callback.onBytesRead(readBuffer, 0, readBytes);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(853)) {
                                readMetaData(inputStream);
                            }
                            if (!ListenerUtil.mutListener.listen(854)) {
                                bytesUntilMetaData = info.metadataOffset;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(877)) {
            stopRecording();
        }
    }

    private int readMetaData(InputStream inputStream) throws IOException {
        int metadataBytes = (ListenerUtil.mutListener.listen(881) ? (inputStream.read() % 16) : (ListenerUtil.mutListener.listen(880) ? (inputStream.read() / 16) : (ListenerUtil.mutListener.listen(879) ? (inputStream.read() - 16) : (ListenerUtil.mutListener.listen(878) ? (inputStream.read() + 16) : (inputStream.read() * 16)))));
        int metadataBytesToRead = metadataBytes;
        int readBytesBufferMetadata = 0;
        int readBytes;
        if (!ListenerUtil.mutListener.listen(883)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(882)) {
                    Log.d(TAG, "metadata size:" + metadataBytes);
                }
        }
        if ((ListenerUtil.mutListener.listen(888) ? (metadataBytes >= 0) : (ListenerUtil.mutListener.listen(887) ? (metadataBytes <= 0) : (ListenerUtil.mutListener.listen(886) ? (metadataBytes < 0) : (ListenerUtil.mutListener.listen(885) ? (metadataBytes != 0) : (ListenerUtil.mutListener.listen(884) ? (metadataBytes == 0) : (metadataBytes > 0))))))) {
            if (!ListenerUtil.mutListener.listen(889)) {
                Arrays.fill(readBuffer, (byte) 0);
            }
            {
                long _loopCounter14 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                    readBytes = inputStream.read(readBuffer, readBytesBufferMetadata, metadataBytesToRead);
                    if (!ListenerUtil.mutListener.listen(895)) {
                        if ((ListenerUtil.mutListener.listen(894) ? (readBytes >= 0) : (ListenerUtil.mutListener.listen(893) ? (readBytes <= 0) : (ListenerUtil.mutListener.listen(892) ? (readBytes > 0) : (ListenerUtil.mutListener.listen(891) ? (readBytes < 0) : (ListenerUtil.mutListener.listen(890) ? (readBytes != 0) : (readBytes == 0))))))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(901)) {
                        if ((ListenerUtil.mutListener.listen(900) ? (readBytes >= 0) : (ListenerUtil.mutListener.listen(899) ? (readBytes <= 0) : (ListenerUtil.mutListener.listen(898) ? (readBytes > 0) : (ListenerUtil.mutListener.listen(897) ? (readBytes != 0) : (ListenerUtil.mutListener.listen(896) ? (readBytes == 0) : (readBytes < 0))))))) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(902)) {
                        metadataBytesToRead -= readBytes;
                    }
                    if (!ListenerUtil.mutListener.listen(903)) {
                        readBytesBufferMetadata += readBytes;
                    }
                    if (!ListenerUtil.mutListener.listen(914)) {
                        if ((ListenerUtil.mutListener.listen(908) ? (metadataBytesToRead >= 0) : (ListenerUtil.mutListener.listen(907) ? (metadataBytesToRead > 0) : (ListenerUtil.mutListener.listen(906) ? (metadataBytesToRead < 0) : (ListenerUtil.mutListener.listen(905) ? (metadataBytesToRead != 0) : (ListenerUtil.mutListener.listen(904) ? (metadataBytesToRead == 0) : (metadataBytesToRead <= 0))))))) {
                            String s = new String(readBuffer, 0, metadataBytes, "utf-8");
                            if (!ListenerUtil.mutListener.listen(910)) {
                                if (BuildConfig.DEBUG)
                                    if (!ListenerUtil.mutListener.listen(909)) {
                                        Log.d(TAG, "METADATA:" + s);
                                    }
                            }
                            Map<String, String> rawMetadata = decodeShoutcastMetadata(s);
                            StreamLiveInfo streamLiveInfo = new StreamLiveInfo(rawMetadata);
                            if (!ListenerUtil.mutListener.listen(912)) {
                                if (BuildConfig.DEBUG)
                                    if (!ListenerUtil.mutListener.listen(911)) {
                                        Log.d(TAG, "META:" + streamLiveInfo.getTitle());
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(913)) {
                                callback.onFoundLiveStreamInfo(streamLiveInfo);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return (ListenerUtil.mutListener.listen(918) ? (readBytesBufferMetadata % 1) : (ListenerUtil.mutListener.listen(917) ? (readBytesBufferMetadata / 1) : (ListenerUtil.mutListener.listen(916) ? (readBytesBufferMetadata * 1) : (ListenerUtil.mutListener.listen(915) ? (readBytesBufferMetadata - 1) : (readBytesBufferMetadata + 1)))));
    }

    private void connectToStream() {
        if (!ListenerUtil.mutListener.listen(919)) {
            isStopped = false;
        }
        int retry = MAX_RETRIES;
        Socket socketProxy = null;
        OutputStream outputStream = null;
        ServerSocket proxyServer = null;
        try {
            if (!ListenerUtil.mutListener.listen(929)) {
                if (BuildConfig.DEBUG)
                    if (!ListenerUtil.mutListener.listen(928)) {
                        Log.d(TAG, "creating local proxy");
                    }
            }
            try {
                if (!ListenerUtil.mutListener.listen(931)) {
                    proxyServer = new ServerSocket(0, 1, InetAddress.getLocalHost());
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(930)) {
                    e.printStackTrace();
                }
                return;
            }
            final int port = proxyServer.getLocalPort();
            if (!ListenerUtil.mutListener.listen(932)) {
                localAddress = String.format(Locale.US, "http://localhost:%d", port);
            }
            final Request request = new Request.Builder().url(uri).addHeader("Icy-MetaData", "1").build();
            if (!ListenerUtil.mutListener.listen(974)) {
                {
                    long _loopCounter15 = 0;
                    while ((ListenerUtil.mutListener.listen(973) ? (!isStopped || (ListenerUtil.mutListener.listen(972) ? (retry >= 0) : (ListenerUtil.mutListener.listen(971) ? (retry <= 0) : (ListenerUtil.mutListener.listen(970) ? (retry < 0) : (ListenerUtil.mutListener.listen(969) ? (retry != 0) : (ListenerUtil.mutListener.listen(968) ? (retry == 0) : (retry > 0))))))) : (!isStopped && (ListenerUtil.mutListener.listen(972) ? (retry >= 0) : (ListenerUtil.mutListener.listen(971) ? (retry <= 0) : (ListenerUtil.mutListener.listen(970) ? (retry < 0) : (ListenerUtil.mutListener.listen(969) ? (retry != 0) : (ListenerUtil.mutListener.listen(968) ? (retry == 0) : (retry > 0))))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                        ResponseBody responseBody = null;
                        try {
                            if (!ListenerUtil.mutListener.listen(938)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(937)) {
                                        Log.d(TAG, "connecting to stream (try=" + retry + "):" + uri);
                                    }
                                }
                            }
                            Response response = httpClient.newCall(request).execute();
                            if (!ListenerUtil.mutListener.listen(939)) {
                                responseBody = response.body();
                            }
                            assert responseBody != null;
                            final MediaType contentType = responseBody.contentType();
                            if (!ListenerUtil.mutListener.listen(941)) {
                                if (BuildConfig.DEBUG)
                                    if (!ListenerUtil.mutListener.listen(940)) {
                                        Log.d(TAG, "waiting...");
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(944)) {
                                if (isStopped) {
                                    if (!ListenerUtil.mutListener.listen(943)) {
                                        if (BuildConfig.DEBUG)
                                            if (!ListenerUtil.mutListener.listen(942)) {
                                                Log.d(TAG, "stopped from the outside");
                                            }
                                    }
                                    break;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(947)) {
                                if (socketProxy != null) {
                                    if (!ListenerUtil.mutListener.listen(945)) {
                                        socketProxy.close();
                                    }
                                    if (!ListenerUtil.mutListener.listen(946)) {
                                        socketProxy = null;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(950)) {
                                if (outputStream != null) {
                                    if (!ListenerUtil.mutListener.listen(948)) {
                                        outputStream.close();
                                    }
                                    if (!ListenerUtil.mutListener.listen(949)) {
                                        outputStream = null;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(951)) {
                                callback.onStreamCreated(localAddress);
                            }
                            if (!ListenerUtil.mutListener.listen(952)) {
                                proxyServer.setSoTimeout(2000);
                            }
                            if (!ListenerUtil.mutListener.listen(953)) {
                                socketProxy = proxyServer.accept();
                            }
                            if (!ListenerUtil.mutListener.listen(955)) {
                                // send ok message to local mediaplayer
                                if (BuildConfig.DEBUG)
                                    if (!ListenerUtil.mutListener.listen(954)) {
                                        Log.d(TAG, "sending OK to the local media player");
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(956)) {
                                outputStream = socketProxy.getOutputStream();
                            }
                            if (!ListenerUtil.mutListener.listen(957)) {
                                outputStream.write(("HTTP/1.0 200 OK\r\n" + "Pragma: no-cache\r\n" + "Content-Type: " + contentType + "\r\n\r\n").getBytes("utf-8"));
                            }
                            final String type = contentType.toString().toLowerCase();
                            if (!ListenerUtil.mutListener.listen(959)) {
                                if (BuildConfig.DEBUG)
                                    if (!ListenerUtil.mutListener.listen(958)) {
                                        Log.d(TAG, "Content Type: " + type);
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(963)) {
                                if ((ListenerUtil.mutListener.listen(960) ? (type.equals("application/vnd.apple.mpegurl") && type.equals("application/x-mpegurl")) : (type.equals("application/vnd.apple.mpegurl") || type.equals("application/x-mpegurl")))) {
                                    if (!ListenerUtil.mutListener.listen(962)) {
                                        Log.e(TAG, "Cannot play HLS streams through proxy!");
                                    }
                                } else {
                                    // try to get shoutcast information from stream connection
                                    final ShoutcastInfo info = ShoutcastInfo.Decode(response);
                                    if (!ListenerUtil.mutListener.listen(961)) {
                                        proxyDefaultStream(info, responseBody, outputStream);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(964)) {
                                // reset retry count, if connection was ok
                                retry = MAX_RETRIES;
                            }
                        } catch (ProtocolException protocolException) {
                            if (!ListenerUtil.mutListener.listen(933)) {
                                Log.e(TAG, "connecting to stream failed due to protocol exception, will NOT retry.", protocolException);
                            }
                            break;
                        } catch (SocketTimeoutException ignored) {
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(934)) {
                                Log.e(TAG, "exception occurred inside the connection loop, retry.", e);
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(936)) {
                                if (responseBody != null) {
                                    if (!ListenerUtil.mutListener.listen(935)) {
                                        responseBody.close();
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(965)) {
                            if (isStopped) {
                                break;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(966)) {
                            retry--;
                        }
                        if (!ListenerUtil.mutListener.listen(967)) {
                            Thread.sleep(1000);
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(920)) {
                Log.e(TAG, "Interrupted ex Proxy() ", e);
            }
        } finally {
            try {
                if (!ListenerUtil.mutListener.listen(923)) {
                    if (proxyServer != null) {
                        if (!ListenerUtil.mutListener.listen(922)) {
                            proxyServer.close();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(925)) {
                    if (socketProxy != null) {
                        if (!ListenerUtil.mutListener.listen(924)) {
                            socketProxy.close();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(927)) {
                    if (outputStream != null) {
                        if (!ListenerUtil.mutListener.listen(926)) {
                            outputStream.close();
                        }
                    }
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(921)) {
                    Log.e(TAG, "exception occurred while closing resources.", e);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(976)) {
            // inform outside if stream stopped, only if outside did not initiate stop
            if (!isStopped) {
                if (!ListenerUtil.mutListener.listen(975)) {
                    callback.onStreamStopped();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(977)) {
            stop();
        }
    }

    private Map<String, String> decodeShoutcastMetadata(String metadataStr) {
        Map<String, String> metadata = new HashMap<>();
        String[] kvs = metadataStr.split(";");
        if (!ListenerUtil.mutListener.listen(1025)) {
            {
                long _loopCounter16 = 0;
                for (String kv : kvs) {
                    ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                    final int n = kv.indexOf('=');
                    if (!ListenerUtil.mutListener.listen(983)) {
                        if ((ListenerUtil.mutListener.listen(982) ? (n >= 1) : (ListenerUtil.mutListener.listen(981) ? (n <= 1) : (ListenerUtil.mutListener.listen(980) ? (n > 1) : (ListenerUtil.mutListener.listen(979) ? (n != 1) : (ListenerUtil.mutListener.listen(978) ? (n == 1) : (n < 1)))))))
                            continue;
                    }
                    final boolean isString = (ListenerUtil.mutListener.listen(1002) ? ((ListenerUtil.mutListener.listen(997) ? ((ListenerUtil.mutListener.listen(992) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) >= kv.length()) : (ListenerUtil.mutListener.listen(991) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) <= kv.length()) : (ListenerUtil.mutListener.listen(990) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) > kv.length()) : (ListenerUtil.mutListener.listen(989) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) != kv.length()) : (ListenerUtil.mutListener.listen(988) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) == kv.length()) : ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) < kv.length())))))) || kv.charAt((ListenerUtil.mutListener.listen(996) ? (kv.length() % 1) : (ListenerUtil.mutListener.listen(995) ? (kv.length() / 1) : (ListenerUtil.mutListener.listen(994) ? (kv.length() * 1) : (ListenerUtil.mutListener.listen(993) ? (kv.length() + 1) : (kv.length() - 1)))))) == '\'') : ((ListenerUtil.mutListener.listen(992) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) >= kv.length()) : (ListenerUtil.mutListener.listen(991) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) <= kv.length()) : (ListenerUtil.mutListener.listen(990) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) > kv.length()) : (ListenerUtil.mutListener.listen(989) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) != kv.length()) : (ListenerUtil.mutListener.listen(988) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) == kv.length()) : ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) < kv.length())))))) && kv.charAt((ListenerUtil.mutListener.listen(996) ? (kv.length() % 1) : (ListenerUtil.mutListener.listen(995) ? (kv.length() / 1) : (ListenerUtil.mutListener.listen(994) ? (kv.length() * 1) : (ListenerUtil.mutListener.listen(993) ? (kv.length() + 1) : (kv.length() - 1)))))) == '\'')) || kv.charAt((ListenerUtil.mutListener.listen(1001) ? (n % 1) : (ListenerUtil.mutListener.listen(1000) ? (n / 1) : (ListenerUtil.mutListener.listen(999) ? (n * 1) : (ListenerUtil.mutListener.listen(998) ? (n - 1) : (n + 1)))))) == '\'') : ((ListenerUtil.mutListener.listen(997) ? ((ListenerUtil.mutListener.listen(992) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) >= kv.length()) : (ListenerUtil.mutListener.listen(991) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) <= kv.length()) : (ListenerUtil.mutListener.listen(990) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) > kv.length()) : (ListenerUtil.mutListener.listen(989) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) != kv.length()) : (ListenerUtil.mutListener.listen(988) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) == kv.length()) : ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) < kv.length())))))) || kv.charAt((ListenerUtil.mutListener.listen(996) ? (kv.length() % 1) : (ListenerUtil.mutListener.listen(995) ? (kv.length() / 1) : (ListenerUtil.mutListener.listen(994) ? (kv.length() * 1) : (ListenerUtil.mutListener.listen(993) ? (kv.length() + 1) : (kv.length() - 1)))))) == '\'') : ((ListenerUtil.mutListener.listen(992) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) >= kv.length()) : (ListenerUtil.mutListener.listen(991) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) <= kv.length()) : (ListenerUtil.mutListener.listen(990) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) > kv.length()) : (ListenerUtil.mutListener.listen(989) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) != kv.length()) : (ListenerUtil.mutListener.listen(988) ? ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) == kv.length()) : ((ListenerUtil.mutListener.listen(987) ? (n % 1) : (ListenerUtil.mutListener.listen(986) ? (n / 1) : (ListenerUtil.mutListener.listen(985) ? (n * 1) : (ListenerUtil.mutListener.listen(984) ? (n - 1) : (n + 1))))) < kv.length())))))) && kv.charAt((ListenerUtil.mutListener.listen(996) ? (kv.length() % 1) : (ListenerUtil.mutListener.listen(995) ? (kv.length() / 1) : (ListenerUtil.mutListener.listen(994) ? (kv.length() * 1) : (ListenerUtil.mutListener.listen(993) ? (kv.length() + 1) : (kv.length() - 1)))))) == '\'')) && kv.charAt((ListenerUtil.mutListener.listen(1001) ? (n % 1) : (ListenerUtil.mutListener.listen(1000) ? (n / 1) : (ListenerUtil.mutListener.listen(999) ? (n * 1) : (ListenerUtil.mutListener.listen(998) ? (n - 1) : (n + 1)))))) == '\''));
                    final String key = kv.substring(0, n);
                    final String val = isString ? kv.substring((ListenerUtil.mutListener.listen(1019) ? (n % 2) : (ListenerUtil.mutListener.listen(1018) ? (n / 2) : (ListenerUtil.mutListener.listen(1017) ? (n * 2) : (ListenerUtil.mutListener.listen(1016) ? (n - 2) : (n + 2))))), (ListenerUtil.mutListener.listen(1023) ? (kv.length() % 1) : (ListenerUtil.mutListener.listen(1022) ? (kv.length() / 1) : (ListenerUtil.mutListener.listen(1021) ? (kv.length() * 1) : (ListenerUtil.mutListener.listen(1020) ? (kv.length() + 1) : (kv.length() - 1)))))) : (ListenerUtil.mutListener.listen(1011) ? ((ListenerUtil.mutListener.listen(1006) ? (n % 1) : (ListenerUtil.mutListener.listen(1005) ? (n / 1) : (ListenerUtil.mutListener.listen(1004) ? (n * 1) : (ListenerUtil.mutListener.listen(1003) ? (n - 1) : (n + 1))))) >= kv.length()) : (ListenerUtil.mutListener.listen(1010) ? ((ListenerUtil.mutListener.listen(1006) ? (n % 1) : (ListenerUtil.mutListener.listen(1005) ? (n / 1) : (ListenerUtil.mutListener.listen(1004) ? (n * 1) : (ListenerUtil.mutListener.listen(1003) ? (n - 1) : (n + 1))))) <= kv.length()) : (ListenerUtil.mutListener.listen(1009) ? ((ListenerUtil.mutListener.listen(1006) ? (n % 1) : (ListenerUtil.mutListener.listen(1005) ? (n / 1) : (ListenerUtil.mutListener.listen(1004) ? (n * 1) : (ListenerUtil.mutListener.listen(1003) ? (n - 1) : (n + 1))))) > kv.length()) : (ListenerUtil.mutListener.listen(1008) ? ((ListenerUtil.mutListener.listen(1006) ? (n % 1) : (ListenerUtil.mutListener.listen(1005) ? (n / 1) : (ListenerUtil.mutListener.listen(1004) ? (n * 1) : (ListenerUtil.mutListener.listen(1003) ? (n - 1) : (n + 1))))) != kv.length()) : (ListenerUtil.mutListener.listen(1007) ? ((ListenerUtil.mutListener.listen(1006) ? (n % 1) : (ListenerUtil.mutListener.listen(1005) ? (n / 1) : (ListenerUtil.mutListener.listen(1004) ? (n * 1) : (ListenerUtil.mutListener.listen(1003) ? (n - 1) : (n + 1))))) == kv.length()) : ((ListenerUtil.mutListener.listen(1006) ? (n % 1) : (ListenerUtil.mutListener.listen(1005) ? (n / 1) : (ListenerUtil.mutListener.listen(1004) ? (n * 1) : (ListenerUtil.mutListener.listen(1003) ? (n - 1) : (n + 1))))) < kv.length())))))) ? kv.substring((ListenerUtil.mutListener.listen(1015) ? (n % 1) : (ListenerUtil.mutListener.listen(1014) ? (n / 1) : (ListenerUtil.mutListener.listen(1013) ? (n * 1) : (ListenerUtil.mutListener.listen(1012) ? (n - 1) : (n + 1)))))) : "";
                    if (!ListenerUtil.mutListener.listen(1024)) {
                        metadata.put(key, val);
                    }
                }
            }
        }
        return metadata;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void stop() {
        if (!ListenerUtil.mutListener.listen(1027)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(1026)) {
                    Log.d(TAG, "stopping proxy.");
                }
        }
        if (!ListenerUtil.mutListener.listen(1028)) {
            isStopped = true;
        }
        if (!ListenerUtil.mutListener.listen(1029)) {
            stopRecording();
        }
    }

    @Override
    public boolean canRecord() {
        return true;
    }

    @Override
    public void startRecording(@NonNull RecordableListener recordableListener) {
        if (!ListenerUtil.mutListener.listen(1030)) {
            this.recordableListener = recordableListener;
        }
    }

    @Override
    public void stopRecording() {
        if (!ListenerUtil.mutListener.listen(1033)) {
            if (recordableListener != null) {
                if (!ListenerUtil.mutListener.listen(1031)) {
                    recordableListener.onRecordingEnded();
                }
                if (!ListenerUtil.mutListener.listen(1032)) {
                    recordableListener = null;
                }
            }
        }
    }

    @Override
    public boolean isRecording() {
        return recordableListener != null;
    }

    @Override
    public Map<String, String> getRecordNameFormattingArgs() {
        return null;
    }

    @Override
    public String getExtension() {
        return "mp3";
    }
}
