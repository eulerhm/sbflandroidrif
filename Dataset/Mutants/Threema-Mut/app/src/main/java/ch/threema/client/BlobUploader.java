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
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.NonNull;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class that uploads a blob (image, video) to the blob server and returns the assigned blob
 * ID. No processing is done on the data; any encryption must happen separately.
 */
public class BlobUploader {

    private static final Logger logger = LoggerFactory.getLogger(BlobUploader.class);

    private static final int CHUNK_SIZE = 16384;

    @NonNull
    private final SSLSocketFactoryFactory factory;

    private final InputStream blobInputStream;

    private final int blobLength;

    private ProgressListener progressListener;

    private volatile boolean cancel;

    private Version version;

    private String blobUploadUrlHttps;

    public BlobUploader(@NonNull SSLSocketFactoryFactory factory, byte[] blobData) {
        this(factory, blobData, null);
    }

    public BlobUploader(@NonNull SSLSocketFactoryFactory factory, byte[] blobData, ProgressListener progressListener) {
        this(factory, new ByteArrayInputStream(blobData), blobData.length, progressListener);
    }

    public BlobUploader(@NonNull SSLSocketFactoryFactory factory, InputStream blobInputStream, int blobLength) {
        this(factory, blobInputStream, blobLength, null);
    }

    public BlobUploader(@NonNull SSLSocketFactoryFactory factory, InputStream blobInputStream, int blobLength, ProgressListener progressListener) {
        this(factory, blobInputStream, blobLength, false, progressListener);
    }

    public BlobUploader(@NonNull SSLSocketFactoryFactory factory, InputStream blobInputStream, int blobLength, boolean ipv6, ProgressListener progressListener) {
        this.factory = factory;
        this.blobInputStream = blobInputStream;
        this.blobLength = blobLength;
        if (!ListenerUtil.mutListener.listen(68117)) {
            this.progressListener = progressListener;
        }
        if (!ListenerUtil.mutListener.listen(68118)) {
            this.version = new Version();
        }
        if (!ListenerUtil.mutListener.listen(68119)) {
            this.setServerUrls(ipv6);
        }
    }

    /**
     *  Upload the given blob and return the blob ID on success.
     *
     *  @return blob ID
     *  @throws IOException if a network error occurs
     *  @throws ThreemaException if the server response is invalid
     */
    public byte[] upload() throws IOException, ThreemaException {
        if (!ListenerUtil.mutListener.listen(68120)) {
            cancel = false;
        }
        URL url = new URL(blobUploadUrlHttps);
        String boundary = "---------------------------Boundary_Line";
        if (!ListenerUtil.mutListener.listen(68121)) {
            logger.info("Uploading blob ({} bytes)", blobLength);
        }
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        if (!ListenerUtil.mutListener.listen(68122)) {
            urlConnection.setSSLSocketFactory(this.factory.makeFactory(url.getHost()));
        }
        if (!ListenerUtil.mutListener.listen(68127)) {
            urlConnection.setConnectTimeout((ListenerUtil.mutListener.listen(68126) ? (ProtocolDefines.CONNECT_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(68125) ? (ProtocolDefines.CONNECT_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(68124) ? (ProtocolDefines.CONNECT_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(68123) ? (ProtocolDefines.CONNECT_TIMEOUT + 1000) : (ProtocolDefines.CONNECT_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(68132)) {
            urlConnection.setReadTimeout((ListenerUtil.mutListener.listen(68131) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(68130) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(68129) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(68128) ? (ProtocolDefines.BLOB_LOAD_TIMEOUT + 1000) : (ProtocolDefines.BLOB_LOAD_TIMEOUT * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(68133)) {
            urlConnection.setRequestMethod("POST");
        }
        if (!ListenerUtil.mutListener.listen(68134)) {
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        }
        if (!ListenerUtil.mutListener.listen(68135)) {
            urlConnection.setRequestProperty("User-Agent", ProtocolStrings.USER_AGENT + "/" + version.getVersion());
        }
        String header = "--" + boundary + "\r\nContent-Disposition: form-data; name=\"blob\"; filename=\"blob.bin\"\r\n" + "Content-Type: application/octet-stream\r\n\r\n";
        byte[] headerBytes = header.getBytes();
        String footer = "\r\n--" + boundary + "--\r\n";
        byte[] footerBytes = footer.getBytes();
        if (!ListenerUtil.mutListener.listen(68144)) {
            urlConnection.setFixedLengthStreamingMode((ListenerUtil.mutListener.listen(68143) ? ((ListenerUtil.mutListener.listen(68139) ? (headerBytes.length % blobLength) : (ListenerUtil.mutListener.listen(68138) ? (headerBytes.length / blobLength) : (ListenerUtil.mutListener.listen(68137) ? (headerBytes.length * blobLength) : (ListenerUtil.mutListener.listen(68136) ? (headerBytes.length - blobLength) : (headerBytes.length + blobLength))))) % footerBytes.length) : (ListenerUtil.mutListener.listen(68142) ? ((ListenerUtil.mutListener.listen(68139) ? (headerBytes.length % blobLength) : (ListenerUtil.mutListener.listen(68138) ? (headerBytes.length / blobLength) : (ListenerUtil.mutListener.listen(68137) ? (headerBytes.length * blobLength) : (ListenerUtil.mutListener.listen(68136) ? (headerBytes.length - blobLength) : (headerBytes.length + blobLength))))) / footerBytes.length) : (ListenerUtil.mutListener.listen(68141) ? ((ListenerUtil.mutListener.listen(68139) ? (headerBytes.length % blobLength) : (ListenerUtil.mutListener.listen(68138) ? (headerBytes.length / blobLength) : (ListenerUtil.mutListener.listen(68137) ? (headerBytes.length * blobLength) : (ListenerUtil.mutListener.listen(68136) ? (headerBytes.length - blobLength) : (headerBytes.length + blobLength))))) * footerBytes.length) : (ListenerUtil.mutListener.listen(68140) ? ((ListenerUtil.mutListener.listen(68139) ? (headerBytes.length % blobLength) : (ListenerUtil.mutListener.listen(68138) ? (headerBytes.length / blobLength) : (ListenerUtil.mutListener.listen(68137) ? (headerBytes.length * blobLength) : (ListenerUtil.mutListener.listen(68136) ? (headerBytes.length - blobLength) : (headerBytes.length + blobLength))))) - footerBytes.length) : ((ListenerUtil.mutListener.listen(68139) ? (headerBytes.length % blobLength) : (ListenerUtil.mutListener.listen(68138) ? (headerBytes.length / blobLength) : (ListenerUtil.mutListener.listen(68137) ? (headerBytes.length * blobLength) : (ListenerUtil.mutListener.listen(68136) ? (headerBytes.length - blobLength) : (headerBytes.length + blobLength))))) + footerBytes.length))))));
        }
        if (!ListenerUtil.mutListener.listen(68145)) {
            urlConnection.setDoOutput(true);
        }
        if (!ListenerUtil.mutListener.listen(68146)) {
            urlConnection.setDoInput(true);
        }
        try (BufferedOutputStream bos = new BufferedOutputStream(urlConnection.getOutputStream())) {
            if (!ListenerUtil.mutListener.listen(68150)) {
                bos.write(headerBytes);
            }
            int ndone = 0;
            int nread;
            byte[] buf = new byte[CHUNK_SIZE];
            if (!ListenerUtil.mutListener.listen(68175)) {
                {
                    long _loopCounter853 = 0;
                    while ((ListenerUtil.mutListener.listen(68174) ? ((ListenerUtil.mutListener.listen(68173) ? ((nread = blobInputStream.read(buf)) >= 0) : (ListenerUtil.mutListener.listen(68172) ? ((nread = blobInputStream.read(buf)) <= 0) : (ListenerUtil.mutListener.listen(68171) ? ((nread = blobInputStream.read(buf)) < 0) : (ListenerUtil.mutListener.listen(68170) ? ((nread = blobInputStream.read(buf)) != 0) : (ListenerUtil.mutListener.listen(68169) ? ((nread = blobInputStream.read(buf)) == 0) : ((nread = blobInputStream.read(buf)) > 0)))))) || !cancel) : ((ListenerUtil.mutListener.listen(68173) ? ((nread = blobInputStream.read(buf)) >= 0) : (ListenerUtil.mutListener.listen(68172) ? ((nread = blobInputStream.read(buf)) <= 0) : (ListenerUtil.mutListener.listen(68171) ? ((nread = blobInputStream.read(buf)) < 0) : (ListenerUtil.mutListener.listen(68170) ? ((nread = blobInputStream.read(buf)) != 0) : (ListenerUtil.mutListener.listen(68169) ? ((nread = blobInputStream.read(buf)) == 0) : ((nread = blobInputStream.read(buf)) > 0)))))) && !cancel))) {
                        ListenerUtil.loopListener.listen("_loopCounter853", ++_loopCounter853);
                        if (!ListenerUtil.mutListener.listen(68151)) {
                            bos.write(buf, 0, nread);
                        }
                        if (!ListenerUtil.mutListener.listen(68152)) {
                            ndone += nread;
                        }
                        if (!ListenerUtil.mutListener.listen(68168)) {
                            if ((ListenerUtil.mutListener.listen(68158) ? (progressListener != null || (ListenerUtil.mutListener.listen(68157) ? (blobLength >= 0) : (ListenerUtil.mutListener.listen(68156) ? (blobLength <= 0) : (ListenerUtil.mutListener.listen(68155) ? (blobLength < 0) : (ListenerUtil.mutListener.listen(68154) ? (blobLength != 0) : (ListenerUtil.mutListener.listen(68153) ? (blobLength == 0) : (blobLength > 0))))))) : (progressListener != null && (ListenerUtil.mutListener.listen(68157) ? (blobLength >= 0) : (ListenerUtil.mutListener.listen(68156) ? (blobLength <= 0) : (ListenerUtil.mutListener.listen(68155) ? (blobLength < 0) : (ListenerUtil.mutListener.listen(68154) ? (blobLength != 0) : (ListenerUtil.mutListener.listen(68153) ? (blobLength == 0) : (blobLength > 0)))))))))
                                if (!ListenerUtil.mutListener.listen(68167)) {
                                    progressListener.updateProgress((ListenerUtil.mutListener.listen(68166) ? ((ListenerUtil.mutListener.listen(68162) ? (100 % ndone) : (ListenerUtil.mutListener.listen(68161) ? (100 / ndone) : (ListenerUtil.mutListener.listen(68160) ? (100 - ndone) : (ListenerUtil.mutListener.listen(68159) ? (100 + ndone) : (100 * ndone))))) % blobLength) : (ListenerUtil.mutListener.listen(68165) ? ((ListenerUtil.mutListener.listen(68162) ? (100 % ndone) : (ListenerUtil.mutListener.listen(68161) ? (100 / ndone) : (ListenerUtil.mutListener.listen(68160) ? (100 - ndone) : (ListenerUtil.mutListener.listen(68159) ? (100 + ndone) : (100 * ndone))))) * blobLength) : (ListenerUtil.mutListener.listen(68164) ? ((ListenerUtil.mutListener.listen(68162) ? (100 % ndone) : (ListenerUtil.mutListener.listen(68161) ? (100 / ndone) : (ListenerUtil.mutListener.listen(68160) ? (100 - ndone) : (ListenerUtil.mutListener.listen(68159) ? (100 + ndone) : (100 * ndone))))) - blobLength) : (ListenerUtil.mutListener.listen(68163) ? ((ListenerUtil.mutListener.listen(68162) ? (100 % ndone) : (ListenerUtil.mutListener.listen(68161) ? (100 / ndone) : (ListenerUtil.mutListener.listen(68160) ? (100 - ndone) : (ListenerUtil.mutListener.listen(68159) ? (100 + ndone) : (100 * ndone))))) + blobLength) : ((ListenerUtil.mutListener.listen(68162) ? (100 % ndone) : (ListenerUtil.mutListener.listen(68161) ? (100 / ndone) : (ListenerUtil.mutListener.listen(68160) ? (100 - ndone) : (ListenerUtil.mutListener.listen(68159) ? (100 + ndone) : (100 * ndone))))) / blobLength))))));
                                }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(68176)) {
                blobInputStream.close();
            }
            if (cancel) {
                try {
                    if (!ListenerUtil.mutListener.listen(68177)) {
                        bos.close();
                    }
                } catch (ProtocolException x) {
                }
                if (!ListenerUtil.mutListener.listen(68178)) {
                    logger.info("Blob upload cancelled");
                }
                if (!ListenerUtil.mutListener.listen(68180)) {
                    if (progressListener != null) {
                        if (!ListenerUtil.mutListener.listen(68179)) {
                            progressListener.onFinished(false);
                        }
                    }
                }
                return null;
            }
            if (!ListenerUtil.mutListener.listen(68181)) {
                bos.write(footerBytes);
            }
            if (!ListenerUtil.mutListener.listen(68182)) {
                bos.close();
            }
            String blobIdHex;
            try (InputStream blobIdInputStream = urlConnection.getInputStream()) {
                blobIdHex = IOUtils.toString(blobIdInputStream);
            }
            if (!ListenerUtil.mutListener.listen(68185)) {
                if (blobIdHex == null) {
                    if (!ListenerUtil.mutListener.listen(68184)) {
                        if (progressListener != null) {
                            if (!ListenerUtil.mutListener.listen(68183)) {
                                progressListener.onFinished(false);
                            }
                        }
                    }
                    throw new ThreemaException("TB001");
                }
            }
            byte[] blobId = Utils.hexStringToByteArray(blobIdHex);
            if (!ListenerUtil.mutListener.listen(68188)) {
                if (blobId.length != ProtocolDefines.BLOB_ID_LEN) {
                    if (!ListenerUtil.mutListener.listen(68187)) {
                        if (progressListener != null) {
                            if (!ListenerUtil.mutListener.listen(68186)) {
                                progressListener.onFinished(false);
                            }
                        }
                    }
                    throw new ThreemaException("TB001");
                }
            }
            if (!ListenerUtil.mutListener.listen(68190)) {
                if (progressListener != null) {
                    if (!ListenerUtil.mutListener.listen(68189)) {
                        progressListener.onFinished(true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(68191)) {
                logger.info("Blob upload completed; ID = {}", blobIdHex);
            }
            return blobId;
        } finally {
            if (!ListenerUtil.mutListener.listen(68147)) {
                urlConnection.disconnect();
            }
            if (!ListenerUtil.mutListener.listen(68149)) {
                if (blobInputStream != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(68148)) {
                            blobInputStream.close();
                        }
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

    /**
     *  Cancel an upload in progress. upload() will return null.
     */
    public void cancel() {
        if (!ListenerUtil.mutListener.listen(68192)) {
            cancel = true;
        }
    }

    public void setProgressListener(ProgressListener progressListener) {
        if (!ListenerUtil.mutListener.listen(68193)) {
            this.progressListener = progressListener;
        }
    }

    public void setVersion(Version version) {
        if (!ListenerUtil.mutListener.listen(68194)) {
            this.version = version;
        }
    }

    public void setServerUrls(boolean ipv6) {
        if (!ListenerUtil.mutListener.listen(68195)) {
            this.blobUploadUrlHttps = ipv6 ? ProtocolStrings.BLOB_UPLOAD_URL_IPV6 : ProtocolStrings.BLOB_UPLOAD_URL;
        }
    }
}
