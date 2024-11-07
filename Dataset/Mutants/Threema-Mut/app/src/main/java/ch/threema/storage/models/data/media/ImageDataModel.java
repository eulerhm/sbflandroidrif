/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
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
package ch.threema.storage.models.data.media;

import android.util.JsonReader;
import android.util.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.StringReader;
import java.io.StringWriter;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageDataModel implements MediaMessageDataInterface {

    private static final Logger logger = LoggerFactory.getLogger(ImageDataModel.class);

    private byte[] imageBlobId;

    private byte[] encryptionKey;

    private byte[] nonce;

    private boolean isDownloaded;

    private ImageDataModel() {
    }

    public ImageDataModel(byte[] imageBlobId, byte[] encryptionKey, byte[] nonce) {
        if (!ListenerUtil.mutListener.listen(70716)) {
            this.imageBlobId = imageBlobId;
        }
        if (!ListenerUtil.mutListener.listen(70717)) {
            this.encryptionKey = encryptionKey;
        }
        if (!ListenerUtil.mutListener.listen(70718)) {
            this.nonce = nonce;
        }
        if (!ListenerUtil.mutListener.listen(70719)) {
            this.isDownloaded = false;
        }
    }

    public ImageDataModel(boolean isDownloaded) {
        if (!ListenerUtil.mutListener.listen(70720)) {
            this.isDownloaded = isDownloaded;
        }
        if (!ListenerUtil.mutListener.listen(70721)) {
            this.imageBlobId = new byte[0];
        }
        if (!ListenerUtil.mutListener.listen(70722)) {
            this.encryptionKey = new byte[0];
        }
    }

    @Override
    public byte[] getBlobId() {
        return this.imageBlobId;
    }

    @Override
    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    @Override
    public boolean isDownloaded() {
        return this.isDownloaded;
    }

    @Override
    public void isDownloaded(boolean isDownloaded) {
        if (!ListenerUtil.mutListener.listen(70723)) {
            this.isDownloaded = isDownloaded;
        }
        if (!ListenerUtil.mutListener.listen(70727)) {
            if (this.isDownloaded) {
                if (!ListenerUtil.mutListener.listen(70724)) {
                    // Clear stuff
                    this.nonce = new byte[0];
                }
                if (!ListenerUtil.mutListener.listen(70725)) {
                    this.encryptionKey = new byte[0];
                }
                if (!ListenerUtil.mutListener.listen(70726)) {
                    this.imageBlobId = new byte[0];
                }
            }
        }
    }

    public void fromString(String s) {
        if (!ListenerUtil.mutListener.listen(70732)) {
            if (TestUtil.empty(s)) {
                if (!ListenerUtil.mutListener.listen(70728)) {
                    // "old" image model, set defaults
                    this.isDownloaded = true;
                }
                if (!ListenerUtil.mutListener.listen(70729)) {
                    this.encryptionKey = new byte[0];
                }
                if (!ListenerUtil.mutListener.listen(70730)) {
                    this.imageBlobId = new byte[0];
                }
                if (!ListenerUtil.mutListener.listen(70731)) {
                    this.nonce = new byte[0];
                }
                return;
            }
        }
        JsonReader r = new JsonReader(new StringReader(s));
        try {
            if (!ListenerUtil.mutListener.listen(70734)) {
                r.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70735)) {
                this.isDownloaded = r.nextBoolean();
            }
            if (!ListenerUtil.mutListener.listen(70736)) {
                this.encryptionKey = Utils.hexStringToByteArray(r.nextString());
            }
            if (!ListenerUtil.mutListener.listen(70737)) {
                this.imageBlobId = Utils.hexStringToByteArray(r.nextString());
            }
            if (!ListenerUtil.mutListener.listen(70738)) {
                this.nonce = Utils.hexStringToByteArray(r.nextString());
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70733)) {
                logger.error("Exception", x);
            }
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        JsonWriter j = new JsonWriter(sw);
        try {
            if (!ListenerUtil.mutListener.listen(70740)) {
                j.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70741)) {
                j.value(this.isDownloaded()).value(Utils.byteArrayToHexString(this.getEncryptionKey())).value(Utils.byteArrayToHexString(this.getBlobId())).value(Utils.byteArrayToHexString(this.getNonce()));
            }
            if (!ListenerUtil.mutListener.listen(70742)) {
                j.endArray();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70739)) {
                logger.error("Exception", x);
            }
            return null;
        }
        return sw.toString();
    }

    public static ImageDataModel create(String s) {
        ImageDataModel m = new ImageDataModel();
        if (!ListenerUtil.mutListener.listen(70743)) {
            m.fromString(s);
        }
        return m;
    }
}
