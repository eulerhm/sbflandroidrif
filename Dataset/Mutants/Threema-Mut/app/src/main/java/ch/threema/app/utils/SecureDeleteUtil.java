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
package ch.threema.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SecureDeleteUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecureDeleteUtil.class);

    public static void secureDelete(File file) throws IOException {
        if (!ListenerUtil.mutListener.listen(55435)) {
            if ((ListenerUtil.mutListener.listen(55410) ? (file != null || file.exists()) : (file != null && file.exists()))) {
                if (!ListenerUtil.mutListener.listen(55420)) {
                    if (file.isDirectory()) {
                        File[] children = file.listFiles();
                        if (!ListenerUtil.mutListener.listen(55418)) {
                            if (children != null) {
                                if (!ListenerUtil.mutListener.listen(55417)) {
                                    {
                                        long _loopCounter674 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(55416) ? (i >= children.length) : (ListenerUtil.mutListener.listen(55415) ? (i <= children.length) : (ListenerUtil.mutListener.listen(55414) ? (i > children.length) : (ListenerUtil.mutListener.listen(55413) ? (i != children.length) : (ListenerUtil.mutListener.listen(55412) ? (i == children.length) : (i < children.length)))))); i++) {
                                            ListenerUtil.loopListener.listen("_loopCounter674", ++_loopCounter674);
                                            if (!ListenerUtil.mutListener.listen(55411)) {
                                                SecureDeleteUtil.secureDelete(children[i]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(55419)) {
                            // remove directory
                            FileUtil.deleteFileOrWarn(file, "secureDelete", logger);
                        }
                        return;
                    }
                }
                long length = file.length();
                try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                    if (!ListenerUtil.mutListener.listen(55421)) {
                        raf.seek(0);
                    }
                    byte[] zero = new byte[16384];
                    long pos = 0;
                    if (!ListenerUtil.mutListener.listen(55433)) {
                        {
                            long _loopCounter675 = 0;
                            while ((ListenerUtil.mutListener.listen(55432) ? (pos >= length) : (ListenerUtil.mutListener.listen(55431) ? (pos <= length) : (ListenerUtil.mutListener.listen(55430) ? (pos > length) : (ListenerUtil.mutListener.listen(55429) ? (pos != length) : (ListenerUtil.mutListener.listen(55428) ? (pos == length) : (pos < length))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter675", ++_loopCounter675);
                                int nwrite = (int) Math.min((long) zero.length, (ListenerUtil.mutListener.listen(55425) ? (length % pos) : (ListenerUtil.mutListener.listen(55424) ? (length / pos) : (ListenerUtil.mutListener.listen(55423) ? (length * pos) : (ListenerUtil.mutListener.listen(55422) ? (length + pos) : (length - pos))))));
                                if (!ListenerUtil.mutListener.listen(55426)) {
                                    raf.write(zero, 0, nwrite);
                                }
                                if (!ListenerUtil.mutListener.listen(55427)) {
                                    pos += nwrite;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55434)) {
                    FileUtil.deleteFileOrWarn(file, "secureDelete", logger);
                }
            }
        }
    }
}
