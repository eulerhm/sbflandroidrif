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
package ch.threema.client.file;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import androidx.annotation.IntDef;
import ch.threema.client.BadMessageException;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileData {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ RENDERING_DEFAULT, RENDERING_MEDIA, RENDERING_STICKER })
    public @interface RenderingType {
    }

    public static final int RENDERING_DEFAULT = 0;

    public static final int RENDERING_MEDIA = 1;

    public static final int RENDERING_STICKER = 2;

    private static final String KEY_BLOB_ID = "b";

    private static final String KEY_THUMBNAIL_BLOB_ID = "t";

    private static final String KEY_ENCRYPTION_KEY = "k";

    private static final String KEY_MIME_TYPE = "m";

    private static final String KEY_THUMBNAIL_MIME_TYPE = "p";

    private static final String KEY_FILE_NAME = "n";

    private static final String KEY_FILE_SIZE = "s";

    private static final String KEY_RENDERING_TYPE_DEPRECATED = "i";

    private static final String KEY_RENDERING_TYPE = "j";

    private static final String KEY_DESCRIPTION = "d";

    private static final String KEY_CORRELATION_ID = "c";

    private static final String KEY_META_DATA = "x";

    private byte[] fileBlobId;

    private byte[] thumbnailBlobId;

    private byte[] encryptionKey;

    private String mimeType;

    private String thumbnailMimeType;

    private long fileSize;

    private String fileName;

    @RenderingType
    private int renderingType;

    private String description;

    private String correlationId;

    private Map<String, Object> metaData;

    public byte[] getFileBlobId() {
        return fileBlobId;
    }

    public FileData setFileBlobId(byte[] fileBlobId) {
        if (!ListenerUtil.mutListener.listen(66007)) {
            this.fileBlobId = fileBlobId;
        }
        return this;
    }

    public byte[] getThumbnailBlobId() {
        return thumbnailBlobId;
    }

    public FileData setThumbnailBlobId(byte[] thumbnailBlobId) {
        if (!ListenerUtil.mutListener.listen(66008)) {
            this.thumbnailBlobId = thumbnailBlobId;
        }
        return this;
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public FileData setEncryptionKey(byte[] encryptionKey) {
        if (!ListenerUtil.mutListener.listen(66009)) {
            this.encryptionKey = encryptionKey;
        }
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public FileData setMimeType(String mimeType) {
        if (!ListenerUtil.mutListener.listen(66010)) {
            this.mimeType = mimeType;
        }
        return this;
    }

    public String getThumbnailMimeType() {
        return thumbnailMimeType;
    }

    public FileData setThumbnailMimeType(String thumbnailMimeType) {
        if (!ListenerUtil.mutListener.listen(66011)) {
            this.thumbnailMimeType = thumbnailMimeType;
        }
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public FileData setFileSize(long fileSize) {
        if (!ListenerUtil.mutListener.listen(66012)) {
            this.fileSize = fileSize;
        }
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileData setFileName(String fileName) {
        if (!ListenerUtil.mutListener.listen(66013)) {
            this.fileName = fileName;
        }
        return this;
    }

    @RenderingType
    public int getRenderingType() {
        return this.renderingType;
    }

    public FileData setRenderingType(@RenderingType int renderingType) {
        if (!ListenerUtil.mutListener.listen(66014)) {
            this.renderingType = renderingType;
        }
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public FileData setDescription(String description) {
        if (!ListenerUtil.mutListener.listen(66015)) {
            this.description = description;
        }
        return this;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }

    public FileData setCorrelationId(String correlationId) {
        if (!ListenerUtil.mutListener.listen(66016)) {
            this.correlationId = correlationId;
        }
        return this;
    }

    public Map<String, Object> getMetaData() {
        return this.metaData;
    }

    public FileData setMetaData(Map<String, Object> metaData) {
        if (!ListenerUtil.mutListener.listen(66017)) {
            this.metaData = metaData;
        }
        return this;
    }

    public static FileData parse(String jsonObjectString) throws BadMessageException {
        try {
            JSONObject o = new JSONObject(jsonObjectString);
            FileData fileData = new FileData();
            try {
                if (!ListenerUtil.mutListener.listen(66018)) {
                    fileData.fileBlobId = Utils.hexStringToByteArray(o.getString(KEY_BLOB_ID));
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM038");
            }
            if (!ListenerUtil.mutListener.listen(66020)) {
                // optional field
                if (o.has(KEY_THUMBNAIL_BLOB_ID)) {
                    try {
                        if (!ListenerUtil.mutListener.listen(66019)) {
                            fileData.thumbnailBlobId = Utils.hexStringToByteArray(o.getString(KEY_THUMBNAIL_BLOB_ID));
                        }
                    } catch (IllegalArgumentException e) {
                        throw new BadMessageException("TM039");
                    }
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(66021)) {
                    fileData.encryptionKey = Utils.hexStringToByteArray(o.getString(KEY_ENCRYPTION_KEY));
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM040");
            }
            try {
                if (!ListenerUtil.mutListener.listen(66022)) {
                    fileData.mimeType = o.getString(KEY_MIME_TYPE);
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM041");
            }
            if (!ListenerUtil.mutListener.listen(66024)) {
                // optional field
                if (o.has(KEY_THUMBNAIL_MIME_TYPE)) {
                    if (!ListenerUtil.mutListener.listen(66023)) {
                        fileData.thumbnailMimeType = o.getString(KEY_THUMBNAIL_MIME_TYPE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66026)) {
                // optional field
                if (o.has(KEY_FILE_NAME)) {
                    if (!ListenerUtil.mutListener.listen(66025)) {
                        fileData.fileName = o.getString(KEY_FILE_NAME);
                    }
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(66027)) {
                    fileData.fileSize = o.getInt(KEY_FILE_SIZE);
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM042");
            }
            if (!ListenerUtil.mutListener.listen(66038)) {
                if (o.has(KEY_RENDERING_TYPE)) {
                    if (!ListenerUtil.mutListener.listen(66030)) {
                        fileData.renderingType = o.getInt(KEY_RENDERING_TYPE);
                    }
                    if (!ListenerUtil.mutListener.listen(66037)) {
                        if ((ListenerUtil.mutListener.listen(66035) ? (fileData.renderingType >= RENDERING_STICKER) : (ListenerUtil.mutListener.listen(66034) ? (fileData.renderingType <= RENDERING_STICKER) : (ListenerUtil.mutListener.listen(66033) ? (fileData.renderingType < RENDERING_STICKER) : (ListenerUtil.mutListener.listen(66032) ? (fileData.renderingType != RENDERING_STICKER) : (ListenerUtil.mutListener.listen(66031) ? (fileData.renderingType == RENDERING_STICKER) : (fileData.renderingType > RENDERING_STICKER))))))) {
                            if (!ListenerUtil.mutListener.listen(66036)) {
                                fileData.renderingType = RENDERING_DEFAULT;
                            }
                        }
                    }
                } else {
                    try {
                        if (!ListenerUtil.mutListener.listen(66029)) {
                            fileData.renderingType = o.getInt(KEY_RENDERING_TYPE_DEPRECATED);
                        }
                    } catch (IllegalArgumentException | JSONException e) {
                        if (!ListenerUtil.mutListener.listen(66028)) {
                            fileData.renderingType = RENDERING_DEFAULT;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66040)) {
                // optional field
                if (o.has(KEY_DESCRIPTION)) {
                    try {
                        if (!ListenerUtil.mutListener.listen(66039)) {
                            fileData.description = o.getString(KEY_DESCRIPTION);
                        }
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66042)) {
                if (o.has(KEY_CORRELATION_ID)) {
                    try {
                        if (!ListenerUtil.mutListener.listen(66041)) {
                            fileData.correlationId = o.getString(KEY_CORRELATION_ID);
                        }
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66046)) {
                if (o.has(KEY_META_DATA)) {
                    try {
                        final JSONObject metaData = o.getJSONObject(KEY_META_DATA);
                        Iterator<String> keys = metaData.keys();
                        if (!ListenerUtil.mutListener.listen(66043)) {
                            fileData.metaData = new HashMap<>();
                        }
                        if (!ListenerUtil.mutListener.listen(66045)) {
                            {
                                long _loopCounter815 = 0;
                                while (keys.hasNext()) {
                                    ListenerUtil.loopListener.listen("_loopCounter815", ++_loopCounter815);
                                    String key = keys.next();
                                    if (!ListenerUtil.mutListener.listen(66044)) {
                                        fileData.metaData.put(key, metaData.get(key));
                                    }
                                }
                            }
                        }
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
            return fileData;
        } catch (JSONException e) {
            throw new BadMessageException("TM037");
        }
    }

    public void write(ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(66047)) {
            bos.write(this.generateString().getBytes(StandardCharsets.UTF_8));
        }
    }

    protected String generateString() throws BadMessageException {
        JSONObject o = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(66048)) {
                o.put(KEY_BLOB_ID, Utils.byteArrayToHexString(this.fileBlobId));
            }
            if (!ListenerUtil.mutListener.listen(66049)) {
                o.put(KEY_THUMBNAIL_BLOB_ID, Utils.byteArrayToHexString(this.thumbnailBlobId));
            }
            if (!ListenerUtil.mutListener.listen(66050)) {
                o.put(KEY_ENCRYPTION_KEY, Utils.byteArrayToHexString(this.encryptionKey));
            }
            if (!ListenerUtil.mutListener.listen(66051)) {
                o.put(KEY_MIME_TYPE, this.mimeType);
            }
            if (!ListenerUtil.mutListener.listen(66052)) {
                o.put(KEY_THUMBNAIL_MIME_TYPE, this.thumbnailMimeType);
            }
            if (!ListenerUtil.mutListener.listen(66053)) {
                o.put(KEY_FILE_NAME, this.fileName);
            }
            if (!ListenerUtil.mutListener.listen(66054)) {
                o.put(KEY_FILE_SIZE, this.fileSize);
            }
            if (!ListenerUtil.mutListener.listen(66060)) {
                o.put(KEY_RENDERING_TYPE_DEPRECATED, (ListenerUtil.mutListener.listen(66059) ? (this.renderingType >= RENDERING_MEDIA) : (ListenerUtil.mutListener.listen(66058) ? (this.renderingType <= RENDERING_MEDIA) : (ListenerUtil.mutListener.listen(66057) ? (this.renderingType > RENDERING_MEDIA) : (ListenerUtil.mutListener.listen(66056) ? (this.renderingType < RENDERING_MEDIA) : (ListenerUtil.mutListener.listen(66055) ? (this.renderingType != RENDERING_MEDIA) : (this.renderingType == RENDERING_MEDIA)))))) ? RENDERING_MEDIA : RENDERING_DEFAULT);
            }
            if (!ListenerUtil.mutListener.listen(66062)) {
                if (this.description != null) {
                    if (!ListenerUtil.mutListener.listen(66061)) {
                        o.put(KEY_DESCRIPTION, this.description);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66064)) {
                if (this.correlationId != null) {
                    if (!ListenerUtil.mutListener.listen(66063)) {
                        o.put(KEY_CORRELATION_ID, this.correlationId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66068)) {
                if (this.metaData != null) {
                    JSONObject metaDataJsonObject = new JSONObject();
                    if (!ListenerUtil.mutListener.listen(66066)) {
                        {
                            long _loopCounter816 = 0;
                            for (Map.Entry<String, Object> metaValue : this.metaData.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter816", ++_loopCounter816);
                                if (!ListenerUtil.mutListener.listen(66065)) {
                                    metaDataJsonObject.put(metaValue.getKey(), metaValue.getValue());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(66067)) {
                        o.put(KEY_META_DATA, metaDataJsonObject);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66069)) {
                o.put(KEY_RENDERING_TYPE, this.renderingType);
            }
        } catch (Exception e) {
            throw new BadMessageException("TM037");
        }
        return o.toString();
    }
}
