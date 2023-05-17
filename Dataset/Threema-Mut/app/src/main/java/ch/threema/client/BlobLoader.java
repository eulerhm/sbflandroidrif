/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.client;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class that loads blobs (images, videos etc.) from the blob server given a blob ID. No
 * processing is done on the loaded data; any decryption etc. must be done separately.
 */
public class BlobLoader {

    private static final Logger logger = LoggerFactory.getLogger(BlobLoader.class);

    private static final int BUFFER_SIZE = 8192;

    @NonNull
    private final SSLSocketFactoryFactory factory;

    private final byte[] blobId;

    private ProgressListener progressListener;

    private volatile boolean cancel;

    private Version version;

    private String blobUrlPattern;

    /* "https://%s.blob.threema.ch/%s" */
    private String blobDoneUrlPattern;

    public BlobLoader(@NonNull SSLSocketFactoryFactory factory, byte[] blobId) {
        this(factory, blobId, null);
    }

    public BlobLoader(@NonNull SSLSocketFactoryFactory factory, byte[] blobId, ProgressListener progressListener) {
        this(factory, blobId, false, progressListener);
    }

    public BlobLoader(@NonNull SSLSocketFactoryFactory factory, byte[] blobId, boolean ipv6, ProgressListener progressListener) {
        this.factory = factory;
        this.blobId = blobId;
        if (!ListenerUtil.mutListener.listen(68026)) {
            this.progressListener = progressListener;
        }
        if (!ListenerUtil.mutListener.listen(68027)) {
            this.version = new Version();
        }
        if (!ListenerUtil.mutListener.listen(68028)) {
            this.setServerUrls(ipv6);
        }
    }

    /**
     *  Attempt to load the given blob.
     *
     *  @param markAsDone if true, the server is informed of successful download and will delete the
     *  blob. Do not use for group messages.
     *  @return blob data or null if download was cancelled
     *  @throws IOException
     */
    @Nullable
    public byte[] load(boolean markAsDone) throws IOException {
        if (!ListenerUtil.mutListener.listen(68029)) {
            cancel = false;
        }
        InputStreamLength isl = getInputStream();
        int read;
        byte[] blob;
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* Content length known? */
        if ((ListenerUtil.mutListener.listen(68034) ? (isl.length >= -1) : (ListenerUtil.mutListener.listen(68033) ? (isl.length <= -1) : (ListenerUtil.mutListener.listen(68032) ? (isl.length > -1) : (ListenerUtil.mutListener.listen(68031) ? (isl.length < -1) : (ListenerUtil.mutListener.listen(68030) ? (isl.length == -1) : (isl.length != -1))))))) {
            if (!ListenerUtil.mutListener.listen(68048)) {
                logger.debug("Blob content length is {}", isl.length);
            }
            int offset = 0;
            if (!ListenerUtil.mutListener.listen(68067)) {
                {
                    long _loopCounter852 = 0;
                    while ((ListenerUtil.mutListener.listen(68066) ? ((ListenerUtil.mutListener.listen(68065) ? ((read = isl.inputStream.read(buffer)) >= -1) : (ListenerUtil.mutListener.listen(68064) ? ((read = isl.inputStream.read(buffer)) <= -1) : (ListenerUtil.mutListener.listen(68063) ? ((read = isl.inputStream.read(buffer)) > -1) : (ListenerUtil.mutListener.listen(68062) ? ((read = isl.inputStream.read(buffer)) < -1) : (ListenerUtil.mutListener.listen(68061) ? ((read = isl.inputStream.read(buffer)) == -1) : ((read = isl.inputStream.read(buffer)) != -1)))))) || !cancel) : ((ListenerUtil.mutListener.listen(68065) ? ((read = isl.inputStream.read(buffer)) >= -1) : (ListenerUtil.mutListener.listen(68064) ? ((read = isl.inputStream.read(buffer)) <= -1) : (ListenerUtil.mutListener.listen(68063) ? ((read = isl.inputStream.read(buffer)) > -1) : (ListenerUtil.mutListener.listen(68062) ? ((read = isl.inputStream.read(buffer)) < -1) : (ListenerUtil.mutListener.listen(68061) ? ((read = isl.inputStream.read(buffer)) == -1) : ((read = isl.inputStream.read(buffer)) != -1)))))) && !cancel))) {
                        ListenerUtil.loopListener.listen("_loopCounter852", ++_loopCounter852);
                        if (!ListenerUtil.mutListener.listen(68049)) {
                            offset += read;
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(68050)) {
                                bos.write(buffer, 0, read);
                            }
                        } catch (OutOfMemoryError e) {
                            throw new IOException("Out of memory on write");
                        }
                        if (!ListenerUtil.mutListener.listen(68060)) {
                            if (progressListener != null) {
                                if (!ListenerUtil.mutListener.listen(68059)) {
                                    progressListener.updateProgress((int) ((ListenerUtil.mutListener.listen(68058) ? ((ListenerUtil.mutListener.listen(68054) ? ((float) 100 % offset) : (ListenerUtil.mutListener.listen(68053) ? ((float) 100 / offset) : (ListenerUtil.mutListener.listen(68052) ? ((float) 100 - offset) : (ListenerUtil.mutListener.listen(68051) ? ((float) 100 + offset) : ((float) 100 * offset))))) % isl.length) : (ListenerUtil.mutListener.listen(68057) ? ((ListenerUtil.mutListener.listen(68054) ? ((float) 100 % offset) : (ListenerUtil.mutListener.listen(68053) ? ((float) 100 / offset) : (ListenerUtil.mutListener.listen(68052) ? ((float) 100 - offset) : (ListenerUtil.mutListener.listen(68051) ? ((float) 100 + offset) : ((float) 100 * offset))))) * isl.length) : (ListenerUtil.mutListener.listen(68056) ? ((ListenerUtil.mutListener.listen(68054) ? ((float) 100 % offset) : (ListenerUtil.mutListener.listen(68053) ? ((float) 100 / offset) : (ListenerUtil.mutListener.listen(68052) ? ((float) 100 - offset) : (ListenerUtil.mutListener.listen(68051) ? ((float) 100 + offset) : ((float) 100 * offset))))) - isl.length) : (ListenerUtil.mutListener.listen(68055) ? ((ListenerUtil.mutListener.listen(68054) ? ((float) 100 % offset) : (ListenerUtil.mutListener.listen(68053) ? ((float) 100 / offset) : (ListenerUtil.mutListener.listen(68052) ? ((float) 100 - offset) : (ListenerUtil.mutListener.listen(68051) ? ((float) 100 + offset) : ((float) 100 * offset))))) + isl.length) : ((ListenerUtil.mutListener.listen(68054) ? ((float) 100 % offset) : (ListenerUtil.mutListener.listen(68053) ? ((float) 100 / offset) : (ListenerUtil.mutListener.listen(68052) ? ((float) 100 - offset) : (ListenerUtil.mutListener.listen(68051) ? ((float) 100 + offset) : ((float) 100 * offset))))) / isl.length)))))));
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(68071)) {
                if (cancel) {
                    if (!ListenerUtil.mutListener.listen(68068)) {
                        logger.info("Blob load cancelled");
                    }
                    if (!ListenerUtil.mutListener.listen(68070)) {
                        if (progressListener != null) {
                            if (!ListenerUtil.mutListener.listen(68069)) {
                                progressListener.onFinished(false);
                            }
                        }
                    }
                    return null;
                }
            }
            if (!ListenerUtil.mutListener.listen(68079)) {
                if ((ListenerUtil.mutListener.listen(68076) ? (offset >= isl.length) : (ListenerUtil.mutListener.listen(68075) ? (offset <= isl.length) : (ListenerUtil.mutListener.listen(68074) ? (offset > isl.length) : (ListenerUtil.mutListener.listen(68073) ? (offset < isl.length) : (ListenerUtil.mutListener.listen(68072) ? (offset == isl.length) : (offset != isl.length))))))) {
                    if (!ListenerUtil.mutListener.listen(68078)) {
                        if (progressListener != null) {
                            if (!ListenerUtil.mutListener.listen(68077)) {
                                progressListener.onFinished(false);
                            }
                        }
                    }
                    throw new IOException("Unexpected read size. current: " + offset + ", excepted: " + isl.length);
                }
            }
            blob = bos.toByteArray();
        } else {
            if (!ListenerUtil.mutListener.listen(68035)) {
                /* Content length is unknown - need to read until EOF */
                logger.debug("Blob content length is unknown");
            }
            if (!ListenerUtil.mutListener.listen(68043)) {
                {
                    long _loopCounter851 = 0;
                    while ((ListenerUtil.mutListener.listen(68042) ? ((ListenerUtil.mutListener.listen(68041) ? ((read = isl.inputStream.read(buffer)) >= -1) : (ListenerUtil.mutListener.listen(68040) ? ((read = isl.inputStream.read(buffer)) <= -1) : (ListenerUtil.mutListener.listen(68039) ? ((read = isl.inputStream.read(buffer)) > -1) : (ListenerUtil.mutListener.listen(68038) ? ((read = isl.inputStream.read(buffer)) < -1) : (ListenerUtil.mutListener.listen(68037) ? ((read = isl.inputStream.read(buffer)) == -1) : ((read = isl.inputStream.read(buffer)) != -1)))))) || !cancel) : ((ListenerUtil.mutListener.listen(68041) ? ((read = isl.inputStream.read(buffer)) >= -1) : (ListenerUtil.mutListener.listen(68040) ? ((read = isl.inputStream.read(buffer)) <= -1) : (ListenerUtil.mutListener.listen(68039) ? ((read = isl.inputStream.read(buffer)) > -1) : (ListenerUtil.mutListener.listen(68038) ? ((read = isl.inputStream.read(buffer)) < -1) : (ListenerUtil.mutListener.listen(68037) ? ((read = isl.inputStream.read(buffer)) == -1) : ((read = isl.inputStream.read(buffer)) != -1)))))) && !cancel))) {
                        ListenerUtil.loopListener.listen("_loopCounter851", ++_loopCounter851);
                        if (!ListenerUtil.mutListener.listen(68036)) {
                            bos.write(buffer, 0, read);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(68047)) {
                if (cancel) {
                    if (!ListenerUtil.mutListener.listen(68044)) {
                        logger.info("Blob load cancelled");
                    }
                    if (!ListenerUtil.mutListener.listen(68046)) {
                        if (progressListener != null) {
                            if (!ListenerUtil.mutListener.listen(68045)) {
                                progressListener.onFinished(false);
                            }
                        }
                    }
                    return null;
                }
            }
            blob = bos.toByteArray();
        }
        if (!ListenerUtil.mutListener.listen(68080)) {
            logger.info("Blob load complete ({} bytes received)", blob.length);
        }
        if (!ListenerUtil.mutListener.listen(68082)) {
            if (progressListener != null) {
                if (!ListenerUtil.mutListener.listen(68081)) {
                    progressListener.onFinished(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68090)) {
            if (markAsDone) {
                if (!ListenerUtil.mutListener.listen(68089)) {
                    if ((ListenerUtil.mutListener.listen(68087) ? (blob.length >= 0) : (ListenerUtil.mutListener.listen(68086) ? (blob.length <= 0) : (ListenerUtil.mutListener.listen(68085) ? (blob.length < 0) : (ListenerUtil.mutListener.listen(68084) ? (blob.length != 0) : (ListenerUtil.mutListener.listen(68083) ? (blob.length == 0) : (blob.length > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(68088)) {
                            this.markAsDown(blobId);
                        }
                    }
                }
            }
        }
        return blob;
    }

    public InputStreamLength getInputStream() throws IOException {
        String blobIdHex = Utils.byteArrayToHexString(blobId);
        String blobIdPrefix = blobIdHex.substring(0, 2);
        URL blobUrl = new URL(String.format(blobUrlPattern, blobIdPrefix, blobIdHex));
        if (!ListenerUtil.mutListener.listen(68091)) {
            logger.info("Loading blob from {}", blobUrl.getHost());
        }
        HttpsURLConnection connection = (HttpsURLConnection) blobUrl.openConnection();
        if (!ListenerUtil.mutListener.listen(68092)) {
            connection.setSSLSocketFactory(this.factory.makeFactory(blobUrl.getHost()));
        }
        if (!ListenerUtil.mutListener.listen(68097)) {
            connection.setConnectTimeout((ListenerUtil.mutListener.listen(68096) ? (ProtocolDefines.BLOB_CONNECT_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(68095) ? (ProtocolDefines.BLOB_CONNECT_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(68094) ? (ProtocolDefines.BLOB_CONNECT_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(68093) ? (ProtocolDefines.BLOB_CONNECT_TIMEOUT + 1000) : (ProtocolDefines.BLOB_CONNECT_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(68102)) {
            connection.setReadTimeout((ListenerUtil.mutListener.listen(68101) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(68100) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(68099) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(68098) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT + 1000) : (ProtocolDefines.BLOB_LOAD_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(68103)) {
            connection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT + "/" + version.getVersion());
        }
        if (!ListenerUtil.mutListener.listen(68104)) {
            connection.setDoOutput(false);
        }
        BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
        int contentLength = connection.getContentLength();
        return new InputStreamLength(inputStream, contentLength);
    }

    public boolean markAsDown(byte[] blobId) {
        String blobIdHex = Utils.byteArrayToHexString(blobId);
        String blobIdPrefix = blobIdHex.substring(0, 2);
        try {
            URL blobDoneUrl = new URL(String.format(blobDoneUrlPattern, blobIdPrefix, blobIdHex));
            HttpsURLConnection doneConnection = (HttpsURLConnection) blobDoneUrl.openConnection();
            if (!ListenerUtil.mutListener.listen(68106)) {
                doneConnection.setSSLSocketFactory(this.factory.makeFactory(blobDoneUrl.getHost()));
            }
            if (!ListenerUtil.mutListener.listen(68107)) {
                doneConnection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT + "/" + version.getVersion());
            }
            if (!ListenerUtil.mutListener.listen(68108)) {
                doneConnection.setDoOutput(false);
            }
            if (!ListenerUtil.mutListener.listen(68109)) {
                doneConnection.setDoInput(true);
            }
            if (!ListenerUtil.mutListener.listen(68110)) {
                doneConnection.setRequestMethod("POST");
            }
            if (!ListenerUtil.mutListener.listen(68111)) {
                IOUtils.toByteArray(doneConnection.getInputStream());
            }
            return true;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(68105)) {
                logger.warn("Marking blob as done failed", e);
            }
        }
        return false;
    }

    /**
     *  Cancel a download in progress. load() will return null.
     */
    public void cancel() {
        if (!ListenerUtil.mutListener.listen(68112)) {
            cancel = true;
        }
    }

    public void setProgressListener(ProgressListener progressListener) {
        if (!ListenerUtil.mutListener.listen(68113)) {
            this.progressListener = progressListener;
        }
    }

    public void setVersion(Version version) {
        if (!ListenerUtil.mutListener.listen(68114)) {
            this.version = version;
        }
    }

    public void setServerUrls(boolean ipv6) {
        if (!ListenerUtil.mutListener.listen(68115)) {
            blobUrlPattern = ipv6 ? ProtocolStrings.BLOB_URL_PATTERN_IPV6 : ProtocolStrings.BLOB_URL_PATTERN;
        }
        if (!ListenerUtil.mutListener.listen(68116)) {
            blobDoneUrlPattern = ipv6 ? ProtocolStrings.BLOB_DONE_PATTERN_IPV6 : ProtocolStrings.BLOB_DONE_PATTERN;
        }
    }

    public class InputStreamLength {

        public final BufferedInputStream inputStream;

        public final int length;

        public InputStreamLength(BufferedInputStream inputStream, int length) {
            this.inputStream = inputStream;
            this.length = length;
        }
    }
}
