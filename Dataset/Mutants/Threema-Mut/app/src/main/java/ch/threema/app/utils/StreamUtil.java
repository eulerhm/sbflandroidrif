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
package ch.threema.app.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import ch.threema.app.ThreemaApplication;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StreamUtil {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    public static InputStream getFromUri(Context context, Uri uri) throws FileNotFoundException {
        InputStream inputStream = null;
        if ((ListenerUtil.mutListener.listen(55521) ? (uri != null || uri.getScheme() != null) : (uri != null && uri.getScheme() != null))) {
            if (!ListenerUtil.mutListener.listen(55524)) {
                if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                    try {
                        if (!ListenerUtil.mutListener.listen(55523)) {
                            inputStream = context.getContentResolver().openInputStream(uri);
                        }
                    } catch (FileNotFoundException e) {
                        if (!ListenerUtil.mutListener.listen(55522)) {
                            logger.info("Unable to get an InputStream for this file using ContentResolver: " + uri.toString());
                        }
                    }
                }
            }
            if (inputStream == null) {
                // try to open as local file if openInputStream fails for a content Uri
                String filePath = FileUtil.getRealPathFromURI(context, uri);
                String appPath;
                String tmpPath;
                try {
                    tmpPath = ThreemaApplication.getServiceManager().getFileService().getTempPath().getAbsolutePath();
                    appPath = context.getApplicationInfo().dataDir;
                } catch (Exception e) {
                    return null;
                }
                if (!ListenerUtil.mutListener.listen(55529)) {
                    if (TestUtil.required(filePath, appPath, tmpPath)) {
                        if (!ListenerUtil.mutListener.listen(55528)) {
                            // do not allow sending of files from local directories - but allow tmp dir
                            if ((ListenerUtil.mutListener.listen(55526) ? (!filePath.startsWith(appPath) && filePath.startsWith(tmpPath)) : (!filePath.startsWith(appPath) || filePath.startsWith(tmpPath)))) {
                                if (!ListenerUtil.mutListener.listen(55527)) {
                                    inputStream = new FileInputStream(new File(filePath));
                                }
                            } else {
                                throw new FileNotFoundException("File on private directory");
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(55525)) {
                            inputStream = context.getContentResolver().openInputStream(uri);
                        }
                    }
                }
            }
            return inputStream;
        } else {
            throw new FileNotFoundException();
        }
    }
}
