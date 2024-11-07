/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.content.ContentResolver;
import android.net.Uri;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ZipUtil {

    /**
     *  Get a ZipOutputStream that writes to an OutputStream pointed to by Uri
     *  @param contetResolver ContentResolver to resolve the supplied Uri
     *  @param zipFileUri Uri of the file the OutputStream writes to
     *  @param password Desired password or null if no encryption is desired
     *  @return ZipOutputStream
     *  @throws IOException
     */
    public static ZipOutputStream initializeZipOutputStream(ContentResolver contetResolver, Uri zipFileUri, String password) throws IOException {
        OutputStream outputStream = contetResolver.openOutputStream(zipFileUri);
        if (!ListenerUtil.mutListener.listen(55881)) {
            if (password != null) {
                return new ZipOutputStream(outputStream, password.toCharArray());
            }
        }
        return new ZipOutputStream(outputStream);
    }

    /**
     *  Get a ZipOutputStream that writes to an OutputStream specified by File
     *  @param zipFile Output file
     *  @param password Desired password or null if no encryption is desired
     *  @return ZipOutputStream
     *  @throws IOException
     */
    public static ZipOutputStream initializeZipOutputStream(File zipFile, String password) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(zipFile);
        if (!ListenerUtil.mutListener.listen(55882)) {
            if (password != null) {
                return new ZipOutputStream(outputStream, password.toCharArray());
            }
        }
        return new ZipOutputStream(outputStream);
    }

    /**
     *  Add contents of InputStream to specified ZipOutputStream and closes the provided InputStream
     *  @param zipOutputStream
     *  @param inputStream
     *  @param filenameInZip
     *  @throws IOException
     */
    public static void addZipStream(ZipOutputStream zipOutputStream, InputStream inputStream, String filenameInZip) throws IOException {
        if (!ListenerUtil.mutListener.listen(55893)) {
            if (inputStream != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(55884)) {
                        zipOutputStream.putNextEntry(createZipParameter(filenameInZip));
                    }
                    byte[] buf = new byte[16384];
                    int nread;
                    if (!ListenerUtil.mutListener.listen(55891)) {
                        {
                            long _loopCounter690 = 0;
                            while ((ListenerUtil.mutListener.listen(55890) ? ((nread = inputStream.read(buf)) >= 0) : (ListenerUtil.mutListener.listen(55889) ? ((nread = inputStream.read(buf)) <= 0) : (ListenerUtil.mutListener.listen(55888) ? ((nread = inputStream.read(buf)) < 0) : (ListenerUtil.mutListener.listen(55887) ? ((nread = inputStream.read(buf)) != 0) : (ListenerUtil.mutListener.listen(55886) ? ((nread = inputStream.read(buf)) == 0) : ((nread = inputStream.read(buf)) > 0))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter690", ++_loopCounter690);
                                if (!ListenerUtil.mutListener.listen(55885)) {
                                    zipOutputStream.write(buf, 0, nread);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(55892)) {
                        zipOutputStream.closeEntry();
                    }
                } finally {
                    try {
                        if (!ListenerUtil.mutListener.listen(55883)) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    private static ZipParameters createZipParameter(String filenameInZip) {
        ZipParameters parameters = new ZipParameters();
        if (!ListenerUtil.mutListener.listen(55894)) {
            parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        }
        if (!ListenerUtil.mutListener.listen(55895)) {
            parameters.setCompressionLevel(CompressionLevel.NORMAL);
        }
        if (!ListenerUtil.mutListener.listen(55896)) {
            parameters.setEncryptFiles(true);
        }
        if (!ListenerUtil.mutListener.listen(55897)) {
            parameters.setEncryptionMethod(EncryptionMethod.AES);
        }
        if (!ListenerUtil.mutListener.listen(55898)) {
            parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        }
        if (!ListenerUtil.mutListener.listen(55899)) {
            parameters.setFileNameInZip(filenameInZip);
        }
        return parameters;
    }
}
