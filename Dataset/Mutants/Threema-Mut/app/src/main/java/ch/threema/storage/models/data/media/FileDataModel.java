/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.storage.models.data.media;

import android.util.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.utils.JsonUtil;
import ch.threema.app.utils.ListReader;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.Utils;
import ch.threema.client.file.FileData;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileDataModel implements MediaMessageDataInterface {

    private static final Logger logger = LoggerFactory.getLogger(FileDataModel.class);

    public static final String METADATA_KEY_DURATION = "d";

    public static final String METADATA_KEY_WIDTH = "w";

    public static final String METADATA_KEY_HEIGHT = "h";

    private byte[] fileBlobId;

    private byte[] encryptionKey;

    private String mimeType;

    private String thumbnailMimeType;

    private long fileSize;

    @Nullable
    private String fileName;

    @FileData.RenderingType
    private int renderingType;

    private boolean isDownloaded;

    private String caption;

    private Map<String, Object> metaData;

    // incoming
    public FileDataModel(byte[] fileBlobId, byte[] encryptionKey, String mimeType, String thumbnailMimeType, long fileSize, @Nullable String fileName, @FileData.RenderingType int renderingType, String caption, boolean isDownloaded, Map<String, Object> metaData) {
        if (!ListenerUtil.mutListener.listen(70639)) {
            this.fileBlobId = fileBlobId;
        }
        if (!ListenerUtil.mutListener.listen(70640)) {
            this.encryptionKey = encryptionKey;
        }
        if (!ListenerUtil.mutListener.listen(70641)) {
            this.mimeType = mimeType;
        }
        if (!ListenerUtil.mutListener.listen(70642)) {
            this.thumbnailMimeType = thumbnailMimeType;
        }
        if (!ListenerUtil.mutListener.listen(70643)) {
            this.fileSize = fileSize;
        }
        if (!ListenerUtil.mutListener.listen(70644)) {
            this.fileName = fileName;
        }
        if (!ListenerUtil.mutListener.listen(70645)) {
            this.renderingType = renderingType;
        }
        if (!ListenerUtil.mutListener.listen(70646)) {
            this.isDownloaded = isDownloaded;
        }
        if (!ListenerUtil.mutListener.listen(70647)) {
            this.caption = caption;
        }
        if (!ListenerUtil.mutListener.listen(70648)) {
            this.metaData = metaData;
        }
    }

    // outgoing
    public FileDataModel(String mimeType, String thumbnailMimeType, long fileSize, @Nullable String fileName, @FileData.RenderingType int renderingType, String caption, boolean isDownloaded, Map<String, Object> metaData) {
        if (!ListenerUtil.mutListener.listen(70649)) {
            this.mimeType = mimeType;
        }
        if (!ListenerUtil.mutListener.listen(70650)) {
            this.thumbnailMimeType = thumbnailMimeType;
        }
        if (!ListenerUtil.mutListener.listen(70651)) {
            this.fileSize = fileSize;
        }
        if (!ListenerUtil.mutListener.listen(70652)) {
            this.fileName = fileName;
        }
        if (!ListenerUtil.mutListener.listen(70653)) {
            this.renderingType = renderingType;
        }
        if (!ListenerUtil.mutListener.listen(70654)) {
            this.isDownloaded = isDownloaded;
        }
        if (!ListenerUtil.mutListener.listen(70655)) {
            this.caption = caption;
        }
        if (!ListenerUtil.mutListener.listen(70656)) {
            this.metaData = metaData;
        }
    }

    private FileDataModel() {
    }

    public void setCaption(String caption) {
        if (!ListenerUtil.mutListener.listen(70657)) {
            this.caption = caption;
        }
    }

    public void setFileName(@Nullable String fileName) {
        if (!ListenerUtil.mutListener.listen(70658)) {
            this.fileName = fileName;
        }
    }

    public void setRenderingType(@FileData.RenderingType int renderingType) {
        if (!ListenerUtil.mutListener.listen(70659)) {
            this.renderingType = renderingType;
        }
    }

    @Override
    public byte[] getBlobId() {
        return this.fileBlobId;
    }

    @Override
    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }

    @Override
    public boolean isDownloaded() {
        return this.isDownloaded;
    }

    @Override
    public void isDownloaded(boolean isDownloaded) {
        if (!ListenerUtil.mutListener.listen(70660)) {
            this.isDownloaded = isDownloaded;
        }
    }

    @Override
    public byte[] getNonce() {
        return new byte[0];
    }

    @NonNull
    public String getMimeType() {
        if (!ListenerUtil.mutListener.listen(70661)) {
            if (this.mimeType == null) {
                return MimeUtil.MIME_TYPE_DEFAULT;
            }
        }
        return this.mimeType;
    }

    public void setMimeType(String mimeType) {
        if (!ListenerUtil.mutListener.listen(70662)) {
            this.mimeType = mimeType;
        }
    }

    @NonNull
    public String getThumbnailMimeType() {
        if (!ListenerUtil.mutListener.listen(70663)) {
            if (this.thumbnailMimeType == null) {
                return MimeUtil.MIME_TYPE_IMAGE_JPG;
            }
        }
        return this.thumbnailMimeType;
    }

    public void setThumbnailMimeType(String thumbnailMimeType) {
        if (!ListenerUtil.mutListener.listen(70664)) {
            this.thumbnailMimeType = thumbnailMimeType;
        }
    }

    public void setFileSize(long fileSize) {
        if (!ListenerUtil.mutListener.listen(70665)) {
            this.fileSize = fileSize;
        }
    }

    public long getFileSize() {
        return this.fileSize;
    }

    @Nullable
    public String getFileName() {
        return this.fileName;
    }

    @FileData.RenderingType
    public int getRenderingType() {
        return this.renderingType;
    }

    public String getCaption() {
        return this.caption;
    }

    public Map<String, Object> getMetaData() {
        return this.metaData;
    }

    public void setMetaData(Map<String, Object> metaData) {
        if (!ListenerUtil.mutListener.listen(70666)) {
            this.metaData = metaData;
        }
    }

    @Nullable
    public Integer getMetaDataInt(String metaDataKey) {
        return (ListenerUtil.mutListener.listen(70668) ? ((ListenerUtil.mutListener.listen(70667) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey))) || this.metaData.get(metaDataKey) instanceof Number) : ((ListenerUtil.mutListener.listen(70667) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey))) && this.metaData.get(metaDataKey) instanceof Number)) ? (Integer) this.metaData.get(metaDataKey) : null;
    }

    @Nullable
    public String getMetaDataString(String metaDataKey) {
        return (ListenerUtil.mutListener.listen(70670) ? ((ListenerUtil.mutListener.listen(70669) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey))) || this.metaData.get(metaDataKey) instanceof String) : ((ListenerUtil.mutListener.listen(70669) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey))) && this.metaData.get(metaDataKey) instanceof String)) ? (String) this.metaData.get(metaDataKey) : null;
    }

    @Nullable
    public Boolean getMetaDataBool(String metaDataKey) {
        return (ListenerUtil.mutListener.listen(70672) ? ((ListenerUtil.mutListener.listen(70671) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey))) || this.metaData.get(metaDataKey) instanceof Boolean) : ((ListenerUtil.mutListener.listen(70671) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey))) && this.metaData.get(metaDataKey) instanceof Boolean)) ? (Boolean) this.metaData.get(metaDataKey) : null;
    }

    @Nullable
    public Float getMetaDataFloat(String metaDataKey) {
        if (!ListenerUtil.mutListener.listen(70676)) {
            if ((ListenerUtil.mutListener.listen(70673) ? (this.metaData != null || this.metaData.containsKey(metaDataKey)) : (this.metaData != null && this.metaData.containsKey(metaDataKey)))) {
                Object value = this.metaData.get(metaDataKey);
                if (!ListenerUtil.mutListener.listen(70675)) {
                    if (value instanceof Number) {
                        if (!ListenerUtil.mutListener.listen(70674)) {
                            if (value instanceof Double) {
                                return ((Double) value).floatValue();
                            } else if (value instanceof Float) {
                                return (Float) value;
                            } else if (value instanceof Integer) {
                                return ((Integer) value).floatValue();
                            } else {
                                return 0F;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getDurationString() {
        try {
            Float durationF = getMetaDataFloat(METADATA_KEY_DURATION);
            if (!ListenerUtil.mutListener.listen(70683)) {
                if (durationF != null) {
                    long duration = durationF.longValue();
                    if (!ListenerUtil.mutListener.listen(70682)) {
                        if ((ListenerUtil.mutListener.listen(70681) ? (duration >= 0) : (ListenerUtil.mutListener.listen(70680) ? (duration <= 0) : (ListenerUtil.mutListener.listen(70679) ? (duration < 0) : (ListenerUtil.mutListener.listen(70678) ? (duration != 0) : (ListenerUtil.mutListener.listen(70677) ? (duration == 0) : (duration > 0))))))) {
                            return StringConversionUtil.secondsToString(duration, false);
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     *  Return the duration in SECONDS as set in the metadata field.
     *
     *  Note: Floats are converted to long integers.
     */
    public long getDuration() {
        try {
            Float durationF = getMetaDataFloat(METADATA_KEY_DURATION);
            if (!ListenerUtil.mutListener.listen(70684)) {
                if (durationF != null) {
                    return durationF.longValue();
                }
            }
        } catch (Exception ignored) {
        }
        return 0;
    }

    private void fromString(String s) {
        if (!ListenerUtil.mutListener.listen(70685)) {
            if (TestUtil.empty(s)) {
                return;
            }
        }
        try {
            ListReader reader = new ListReader(JsonUtil.convertArray(s));
            if (!ListenerUtil.mutListener.listen(70687)) {
                this.fileBlobId = reader.nextStringAsByteArray();
            }
            if (!ListenerUtil.mutListener.listen(70688)) {
                this.encryptionKey = reader.nextStringAsByteArray();
            }
            if (!ListenerUtil.mutListener.listen(70689)) {
                this.mimeType = reader.nextString();
            }
            if (!ListenerUtil.mutListener.listen(70690)) {
                this.fileSize = reader.nextInteger();
            }
            if (!ListenerUtil.mutListener.listen(70691)) {
                this.fileName = reader.nextString();
            }
            try {
                Integer typeId = reader.nextInteger();
                if (!ListenerUtil.mutListener.listen(70693)) {
                    if (typeId != null) {
                        if (!ListenerUtil.mutListener.listen(70692)) {
                            this.renderingType = typeId;
                        }
                    }
                }
            } catch (ClassCastException ignore) {
            }
            if (!ListenerUtil.mutListener.listen(70694)) {
                this.isDownloaded = reader.nextBool();
            }
            if (!ListenerUtil.mutListener.listen(70695)) {
                this.caption = reader.nextString();
            }
            if (!ListenerUtil.mutListener.listen(70696)) {
                this.thumbnailMimeType = reader.nextString();
            }
            if (!ListenerUtil.mutListener.listen(70697)) {
                this.metaData = reader.nextMap();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(70686)) {
                // Ignore error, just log
                logger.error("Extract file data model", e);
            }
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        JsonWriter j = new JsonWriter(sw);
        try {
            if (!ListenerUtil.mutListener.listen(70699)) {
                j.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70700)) {
                j.value(Utils.byteArrayToHexString(this.getBlobId())).value(Utils.byteArrayToHexString(this.getEncryptionKey())).value(this.mimeType).value(this.fileSize).value(this.fileName).value(this.renderingType).value(this.isDownloaded).value(this.caption).value(this.thumbnailMimeType);
            }
            // Always write the meta data object
            JsonWriter metaDataObject = j.beginObject();
            if (!ListenerUtil.mutListener.listen(70712)) {
                if (this.metaData != null) {
                    Iterator<String> keys = this.metaData.keySet().iterator();
                    if (!ListenerUtil.mutListener.listen(70711)) {
                        {
                            long _loopCounter926 = 0;
                            while (keys.hasNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter926", ++_loopCounter926);
                                String key = keys.next();
                                Object value = this.metaData.get(key);
                                if (!ListenerUtil.mutListener.listen(70701)) {
                                    metaDataObject.name(key);
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(70710)) {
                                        if (value instanceof Integer) {
                                            if (!ListenerUtil.mutListener.listen(70709)) {
                                                metaDataObject.value((Integer) value);
                                            }
                                        } else if (value instanceof Float) {
                                            if (!ListenerUtil.mutListener.listen(70708)) {
                                                metaDataObject.value((Float) value);
                                            }
                                        } else if (value instanceof Double) {
                                            if (!ListenerUtil.mutListener.listen(70707)) {
                                                metaDataObject.value((Double) value);
                                            }
                                        } else if (value instanceof Boolean) {
                                            if (!ListenerUtil.mutListener.listen(70706)) {
                                                metaDataObject.value((Boolean) value);
                                            }
                                        } else if (value == null) {
                                            if (!ListenerUtil.mutListener.listen(70705)) {
                                                metaDataObject.nullValue();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(70704)) {
                                                metaDataObject.value(value.toString());
                                            }
                                        }
                                    }
                                } catch (IOException x) {
                                    if (!ListenerUtil.mutListener.listen(70702)) {
                                        logger.error("Failed to write meta data", x);
                                    }
                                    if (!ListenerUtil.mutListener.listen(70703)) {
                                        // Write a NULL
                                        metaDataObject.nullValue();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(70713)) {
                j.endObject();
            }
            if (!ListenerUtil.mutListener.listen(70714)) {
                j.endArray();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70698)) {
                logger.error("Exception", x);
            }
            return null;
        }
        return sw.toString();
    }

    public static FileDataModel create(@NonNull String s) {
        FileDataModel m = new FileDataModel();
        if (!ListenerUtil.mutListener.listen(70715)) {
            m.fromString(s);
        }
        return m;
    }
}
