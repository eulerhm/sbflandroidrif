/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.services;

import android.content.Context;
import android.os.PowerManager;
import com.neilalexander.jnacl.NaCl;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.BuildConfig;
import ch.threema.app.utils.FileUtil;
import ch.threema.client.BlobLoader;
import ch.threema.client.ProgressListener;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DownloadServiceImpl implements DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadServiceImpl.class);

    private static final String TAG = "DownloadService";

    private static final String WAKELOCK_TAG = BuildConfig.APPLICATION_ID + ":" + TAG;

    private static final int DOWNLOAD_WAKELOCK_TIMEOUT = 10 * 1000;

    private final ArrayList<Download> downloads = new ArrayList<>();

    private final FileService fileService;

    private final ApiService apiService;

    private final PowerManager powerManager;

    private static final class Download {

        int messageModelId;

        byte[] blobId;

        BlobLoader blobLoader;

        public Download(int messageModelId, byte[] blobId, BlobLoader blobLoader) {
            if (!ListenerUtil.mutListener.listen(37610)) {
                this.messageModelId = messageModelId;
            }
            if (!ListenerUtil.mutListener.listen(37611)) {
                this.blobId = blobId;
            }
            if (!ListenerUtil.mutListener.listen(37612)) {
                this.blobLoader = blobLoader;
            }
        }
    }

    @Nullable
    private ArrayList<Download> getDownloadsByMessageModelId(int messageModelId) {
        ArrayList<Download> matchingDownloads = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(37620)) {
            {
                long _loopCounter393 = 0;
                for (Download download : this.downloads) {
                    ListenerUtil.loopListener.listen("_loopCounter393", ++_loopCounter393);
                    if (!ListenerUtil.mutListener.listen(37619)) {
                        if ((ListenerUtil.mutListener.listen(37617) ? (download.messageModelId >= messageModelId) : (ListenerUtil.mutListener.listen(37616) ? (download.messageModelId <= messageModelId) : (ListenerUtil.mutListener.listen(37615) ? (download.messageModelId > messageModelId) : (ListenerUtil.mutListener.listen(37614) ? (download.messageModelId < messageModelId) : (ListenerUtil.mutListener.listen(37613) ? (download.messageModelId != messageModelId) : (download.messageModelId == messageModelId))))))) {
                            if (!ListenerUtil.mutListener.listen(37618)) {
                                matchingDownloads.add(download);
                            }
                        }
                    }
                }
            }
        }
        return (ListenerUtil.mutListener.listen(37625) ? (matchingDownloads.size() >= 0) : (ListenerUtil.mutListener.listen(37624) ? (matchingDownloads.size() <= 0) : (ListenerUtil.mutListener.listen(37623) ? (matchingDownloads.size() < 0) : (ListenerUtil.mutListener.listen(37622) ? (matchingDownloads.size() != 0) : (ListenerUtil.mutListener.listen(37621) ? (matchingDownloads.size() == 0) : (matchingDownloads.size() > 0)))))) ? matchingDownloads : null;
    }

    @Nullable
    private Download getDownloadByBlobId(@NonNull byte[] blobId) {
        if (!ListenerUtil.mutListener.listen(37627)) {
            {
                long _loopCounter394 = 0;
                for (Download download : this.downloads) {
                    ListenerUtil.loopListener.listen("_loopCounter394", ++_loopCounter394);
                    if (!ListenerUtil.mutListener.listen(37626)) {
                        if (Arrays.equals(blobId, download.blobId)) {
                            return download;
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean removeDownloadByBlobId(@NonNull byte[] blobId) {
        synchronized (this.downloads) {
            Download download = getDownloadByBlobId(blobId);
            if (download != null) {
                if (!ListenerUtil.mutListener.listen(37628)) {
                    logger.info("Blob {} remove downloader", Utils.byteArrayToHexString(blobId));
                }
                if (!ListenerUtil.mutListener.listen(37629)) {
                    downloads.remove(download);
                }
                return true;
            }
            return false;
        }
    }

    private boolean removeDownloadByMessageModelId(int messageModelId, boolean cancel) {
        synchronized (this.downloads) {
            ArrayList<Download> matchingDownloads = getDownloadsByMessageModelId(messageModelId);
            if (matchingDownloads != null) {
                if (!ListenerUtil.mutListener.listen(37634)) {
                    {
                        long _loopCounter395 = 0;
                        for (Download download : matchingDownloads) {
                            ListenerUtil.loopListener.listen("_loopCounter395", ++_loopCounter395);
                            if (!ListenerUtil.mutListener.listen(37630)) {
                                logger.info("Blob {} remove downloader for message {}. Cancel = {}", Utils.byteArrayToHexString(download.blobId), messageModelId, cancel);
                            }
                            if (!ListenerUtil.mutListener.listen(37632)) {
                                if (cancel) {
                                    if (!ListenerUtil.mutListener.listen(37631)) {
                                        download.blobLoader.cancel();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(37633)) {
                                this.downloads.remove(download);
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        }
    }

    public DownloadServiceImpl(Context context, FileService fileService, ApiService apiService) {
        this.fileService = fileService;
        this.apiService = apiService;
        this.powerManager = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
    }

    @Override
    @WorkerThread
    @Nullable
    public byte[] download(int messageModelId, final byte[] blobId, boolean markAsDown, ProgressListener progressListener) {
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, WAKELOCK_TAG);
        try {
            if (!ListenerUtil.mutListener.listen(37641)) {
                if (wakeLock != null) {
                    if (!ListenerUtil.mutListener.listen(37639)) {
                        wakeLock.acquire(DOWNLOAD_WAKELOCK_TIMEOUT);
                    }
                    if (!ListenerUtil.mutListener.listen(37640)) {
                        logger.info("Acquire download wakelock");
                    }
                }
            }
            ;
            if (blobId == null) {
                if (!ListenerUtil.mutListener.listen(37642)) {
                    logger.warn("Blob ID is null");
                }
                return null;
            }
            String blobIdHex = Utils.byteArrayToHexString(blobId);
            if (!ListenerUtil.mutListener.listen(37643)) {
                logger.info("Blob {} for message {} download requested", blobIdHex, messageModelId);
            }
            byte[] imageBlob = null;
            File downloadFile = this.getTemporaryDownloadFile(blobId);
            boolean downloadSuccess = false;
            try {
                // check if a temporary file exist
                if (downloadFile.exists()) {
                    if ((ListenerUtil.mutListener.listen(37649) ? (downloadFile.length() <= NaCl.BOXOVERHEAD) : (ListenerUtil.mutListener.listen(37648) ? (downloadFile.length() > NaCl.BOXOVERHEAD) : (ListenerUtil.mutListener.listen(37647) ? (downloadFile.length() < NaCl.BOXOVERHEAD) : (ListenerUtil.mutListener.listen(37646) ? (downloadFile.length() != NaCl.BOXOVERHEAD) : (ListenerUtil.mutListener.listen(37645) ? (downloadFile.length() == NaCl.BOXOVERHEAD) : (downloadFile.length() >= NaCl.BOXOVERHEAD))))))) {
                        if (!ListenerUtil.mutListener.listen(37651)) {
                            logger.warn("Blob {} download file already exists", blobIdHex);
                        }
                        try (FileInputStream fileInputStream = new FileInputStream(downloadFile)) {
                            return IOUtils.toByteArray(fileInputStream);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(37650)) {
                            // invalid download file - try again
                            FileUtil.deleteFileOrWarn(downloadFile, "Download File", logger);
                        }
                    }
                }
                BlobLoader blobLoader;
                synchronized (this.downloads) {
                    if (getDownloadByBlobId(blobId) == null) {
                        blobLoader = this.apiService.createLoader(blobId);
                        if (!ListenerUtil.mutListener.listen(37653)) {
                            this.downloads.add(new Download(messageModelId, blobId, blobLoader));
                        }
                        if (!ListenerUtil.mutListener.listen(37654)) {
                            logger.info("Blob {} downloader created", blobIdHex);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(37652)) {
                            logger.info("Blob {} downloader already exists. Not adding again", blobIdHex);
                        }
                        return null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(37656)) {
                    if (progressListener != null) {
                        if (!ListenerUtil.mutListener.listen(37655)) {
                            blobLoader.setProgressListener(progressListener);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(37657)) {
                    // load image from server
                    logger.info("Blob {} now fetching", blobIdHex);
                }
                if (!ListenerUtil.mutListener.listen(37658)) {
                    imageBlob = blobLoader.load(false);
                }
                if (imageBlob != null) {
                    synchronized (this.downloads) {
                        // check if loader already existing in array (otherwise its canceled)
                        if (getDownloadByBlobId(blobId) != null) {
                            if (!ListenerUtil.mutListener.listen(37660)) {
                                logger.info("Blob {} now saving", blobIdHex);
                            }
                            if (!ListenerUtil.mutListener.listen(37661)) {
                                // write to temporary file
                                FileUtil.createNewFileOrLog(downloadFile, logger);
                            }
                            if (downloadFile.isFile()) {
                                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downloadFile))) {
                                    if (!ListenerUtil.mutListener.listen(37663)) {
                                        bos.write(imageBlob);
                                    }
                                    if (!ListenerUtil.mutListener.listen(37664)) {
                                        bos.flush();
                                    }
                                }
                                if ((ListenerUtil.mutListener.listen(37669) ? (downloadFile.length() >= imageBlob.length) : (ListenerUtil.mutListener.listen(37668) ? (downloadFile.length() <= imageBlob.length) : (ListenerUtil.mutListener.listen(37667) ? (downloadFile.length() > imageBlob.length) : (ListenerUtil.mutListener.listen(37666) ? (downloadFile.length() < imageBlob.length) : (ListenerUtil.mutListener.listen(37665) ? (downloadFile.length() != imageBlob.length) : (downloadFile.length() == imageBlob.length))))))) {
                                    if (!ListenerUtil.mutListener.listen(37671)) {
                                        downloadSuccess = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(37674)) {
                                        // ok download saved, set as down if set
                                        if (markAsDown) {
                                            if (!ListenerUtil.mutListener.listen(37672)) {
                                                logger.info("Blob {} scheduled for marking as downloaded", blobIdHex);
                                            }
                                            try {
                                                if (!ListenerUtil.mutListener.listen(37673)) {
                                                    new Thread(() -> {
                                                        synchronized (this.downloads) {
                                                            Download download = getDownloadByBlobId(blobId);
                                                            if (download != null) {
                                                                if (download.blobLoader != null) {
                                                                    download.blobLoader.markAsDown(download.blobId);
                                                                }
                                                                logger.info("Blob {} marked as downloaded", blobIdHex);
                                                            }
                                                        }
                                                    }, "MarkAsDownThread").start();
                                                }
                                            } catch (Exception ignored) {
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(37670)) {
                                        logger.warn("Blob and file size don't match.");
                                    }
                                    return null;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(37662)) {
                                    logger.warn("Blob file is a directory");
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(37659)) {
                                logger.debug("No blob loaders, canceled?");
                            }
                        }
                    }
                }
            } catch (Exception x) {
                if (!ListenerUtil.mutListener.listen(37644)) {
                    logger.error("Exception during blob download", x);
                }
            }
            if (!ListenerUtil.mutListener.listen(37677)) {
                if (downloadSuccess) {
                    if (!ListenerUtil.mutListener.listen(37676)) {
                        logger.info("Blob {} successfully downloaded. Size = {}", blobIdHex, imageBlob.length);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(37675)) {
                        logger.warn("Blob {} download failed.", blobIdHex);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37681)) {
                if (imageBlob == null) {
                    synchronized (this.downloads) {
                        // download failed. remove loader
                        Download download = getDownloadByBlobId(blobId);
                        if (!ListenerUtil.mutListener.listen(37680)) {
                            if (download != null) {
                                if (!ListenerUtil.mutListener.listen(37678)) {
                                    logger.info("Blob {} remove downloader. Download failed.", blobIdHex);
                                }
                                if (!ListenerUtil.mutListener.listen(37679)) {
                                    this.downloads.remove(download);
                                }
                            }
                        }
                    }
                }
            }
            return imageBlob;
        } finally {
            if (!ListenerUtil.mutListener.listen(37638)) {
                if ((ListenerUtil.mutListener.listen(37635) ? (wakeLock != null || wakeLock.isHeld()) : (wakeLock != null && wakeLock.isHeld()))) {
                    if (!ListenerUtil.mutListener.listen(37636)) {
                        logger.info("Release download wakelock");
                    }
                    if (!ListenerUtil.mutListener.listen(37637)) {
                        wakeLock.release();
                    }
                }
            }
        }
    }

    @Override
    public void complete(int messageModelId, byte[] blobId) {
        if (!ListenerUtil.mutListener.listen(37682)) {
            // success has been signalled. remove loader
            removeDownloadByBlobId(blobId);
        }
        // remove temp file
        File f = this.getTemporaryDownloadFile(blobId);
        if (!ListenerUtil.mutListener.listen(37684)) {
            if (f.exists()) {
                if (!ListenerUtil.mutListener.listen(37683)) {
                    FileUtil.deleteFileOrWarn(f, "remove temporary blob file", logger);
                }
            }
        }
    }

    @Override
    public boolean cancel(int messageModelId) {
        return removeDownloadByMessageModelId(messageModelId, true);
    }

    @Override
    public boolean isDownloading(int messageModelId) {
        synchronized (this.downloads) {
            return getDownloadsByMessageModelId(messageModelId) != null;
        }
    }

    @Override
    public boolean isDownloading() {
        synchronized (this.downloads) {
            return (ListenerUtil.mutListener.listen(37689) ? (this.downloads.size() >= 0) : (ListenerUtil.mutListener.listen(37688) ? (this.downloads.size() <= 0) : (ListenerUtil.mutListener.listen(37687) ? (this.downloads.size() < 0) : (ListenerUtil.mutListener.listen(37686) ? (this.downloads.size() != 0) : (ListenerUtil.mutListener.listen(37685) ? (this.downloads.size() == 0) : (this.downloads.size() > 0))))));
        }
    }

    @Override
    public void error(int messageModelId) {
        if (!ListenerUtil.mutListener.listen(37690)) {
            // error has been signalled. remove loaders for this MessageModel
            removeDownloadByMessageModelId(messageModelId, false);
        }
    }

    private File getTemporaryDownloadFile(byte[] blobId) {
        File path = this.fileService.getBlobDownloadPath();
        return new File(path.getPath() + "/" + Utils.byteArrayToHexString(blobId));
    }
}
