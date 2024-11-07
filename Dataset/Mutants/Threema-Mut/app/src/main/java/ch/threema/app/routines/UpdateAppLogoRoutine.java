/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.routines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.services.FileService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ConfigUtils.AppTheme;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.TestUtil;
import static android.provider.MediaStore.MEDIA_IGNORE_FILENAME;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Update the app icon
 */
public class UpdateAppLogoRoutine implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UpdateAppLogoRoutine.class);

    private FileService fileService;

    private final PreferenceService preferenceService;

    private final String lightUrl;

    private final String darkUrl;

    private boolean running = false;

    private boolean forceUpdate = false;

    public UpdateAppLogoRoutine(FileService fileService, PreferenceService preferenceService, @Nullable String lightUrl, @Nullable String darkUrl, boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(34746)) {
            this.fileService = fileService;
        }
        this.preferenceService = preferenceService;
        this.lightUrl = lightUrl;
        this.darkUrl = darkUrl;
        if (!ListenerUtil.mutListener.listen(34747)) {
            this.forceUpdate = forceUpdate;
        }
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(34748)) {
            logger.debug("start update app logo " + this.lightUrl + ", " + this.darkUrl);
        }
        if (!ListenerUtil.mutListener.listen(34749)) {
            this.running = true;
        }
        if (!ListenerUtil.mutListener.listen(34752)) {
            // validate instances
            if (!TestUtil.required(this.fileService, this.preferenceService)) {
                if (!ListenerUtil.mutListener.listen(34750)) {
                    this.running = false;
                }
                if (!ListenerUtil.mutListener.listen(34751)) {
                    logger.error("Not all required instances defined");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(34753)) {
            this.downloadLogo(this.lightUrl, ConfigUtils.THEME_LIGHT);
        }
        if (!ListenerUtil.mutListener.listen(34754)) {
            this.downloadLogo(this.darkUrl, ConfigUtils.THEME_DARK);
        }
        if (!ListenerUtil.mutListener.listen(34755)) {
            this.running = false;
        }
    }

    private void setLogo(@NonNull String url, @NonNull File file, @NonNull Date expires, @AppTheme int theme) {
        if (!ListenerUtil.mutListener.listen(34756)) {
            this.fileService.saveAppLogo(file, theme);
        }
        if (!ListenerUtil.mutListener.listen(34757)) {
            this.preferenceService.setAppLogo(url, theme);
        }
        if (!ListenerUtil.mutListener.listen(34758)) {
            this.preferenceService.setAppLogoExpiresAt(expires, theme);
        }
    }

    private void clearLogo(@AppTheme int theme) {
        if (!ListenerUtil.mutListener.listen(34759)) {
            this.fileService.saveAppLogo(null, theme);
        }
        if (!ListenerUtil.mutListener.listen(34760)) {
            this.preferenceService.clearAppLogo(theme);
        }
    }

    private void downloadLogo(@Nullable String urlString, @AppTheme int theme) {
        if (!ListenerUtil.mutListener.listen(34761)) {
            logger.debug("Logo download forced = " + forceUpdate);
        }
        Date now = new Date();
        if (!ListenerUtil.mutListener.listen(34763)) {
            if (TestUtil.empty(urlString)) {
                if (!ListenerUtil.mutListener.listen(34762)) {
                    this.clearLogo(theme);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(34767)) {
            // check expiry date only on force update
            if (!this.forceUpdate) {
                Date expiresAt = this.preferenceService.getAppLogoExpiresAt(theme);
                if (!ListenerUtil.mutListener.listen(34766)) {
                    if ((ListenerUtil.mutListener.listen(34764) ? (expiresAt != null || now.before(expiresAt)) : (expiresAt != null && now.before(expiresAt)))) {
                        if (!ListenerUtil.mutListener.listen(34765)) {
                            logger.debug("Logo not expired");
                        }
                        // do nothing!
                        return;
                    }
                }
            }
        }
        // define default expiry date (now + 1day)
        Calendar tomorrowCalendar = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(34768)) {
            tomorrowCalendar.setTime(now);
        }
        if (!ListenerUtil.mutListener.listen(34769)) {
            tomorrowCalendar.add(Calendar.DATE, 1);
        }
        Date tomorrow = tomorrowCalendar.getTime();
        try {
            if (!ListenerUtil.mutListener.listen(34771)) {
                logger.debug("Download " + urlString);
            }
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            if (!ListenerUtil.mutListener.listen(34772)) {
                connection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(url.getHost()));
            }
            try {
                if (!ListenerUtil.mutListener.listen(34775)) {
                    // Warning: This may implicitly open an error stream in the 4xx/5xx case!
                    connection.connect();
                }
                final int responseCode = connection.getResponseCode();
                if (!ListenerUtil.mutListener.listen(34803)) {
                    if ((ListenerUtil.mutListener.listen(34780) ? (responseCode >= HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34779) ? (responseCode <= HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34778) ? (responseCode > HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34777) ? (responseCode < HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34776) ? (responseCode == HttpsURLConnection.HTTP_OK) : (responseCode != HttpsURLConnection.HTTP_OK))))))) {
                        if (!ListenerUtil.mutListener.listen(34802)) {
                            if ((ListenerUtil.mutListener.listen(34800) ? (responseCode >= HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34799) ? (responseCode <= HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34798) ? (responseCode > HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34797) ? (responseCode < HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34796) ? (responseCode != HttpsURLConnection.HTTP_NOT_FOUND) : (responseCode == HttpsURLConnection.HTTP_NOT_FOUND))))))) {
                                if (!ListenerUtil.mutListener.listen(34801)) {
                                    logger.debug("Logo not found");
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(34781)) {
                            // cool, save avatar
                            logger.debug("Logo found. Start download");
                        }
                        File temporaryFile = this.fileService.createTempFile(MEDIA_IGNORE_FILENAME, "appicon");
                        // might be -1: server did not report the length
                        int fileLength = connection.getContentLength();
                        if (!ListenerUtil.mutListener.listen(34782)) {
                            logger.debug("size: " + fileLength);
                        }
                        // download the file
                        try (InputStream input = connection.getInputStream()) {
                            Date expires = new Date(connection.getHeaderFieldDate("Expires", tomorrow.getTime()));
                            if (!ListenerUtil.mutListener.listen(34783)) {
                                logger.debug("expires " + expires);
                            }
                            try (FileOutputStream output = new FileOutputStream(temporaryFile.getPath())) {
                                byte[] data = new byte[4096];
                                int count;
                                if (!ListenerUtil.mutListener.listen(34791)) {
                                    {
                                        long _loopCounter256 = 0;
                                        while ((ListenerUtil.mutListener.listen(34790) ? ((count = input.read(data)) >= -1) : (ListenerUtil.mutListener.listen(34789) ? ((count = input.read(data)) <= -1) : (ListenerUtil.mutListener.listen(34788) ? ((count = input.read(data)) > -1) : (ListenerUtil.mutListener.listen(34787) ? ((count = input.read(data)) < -1) : (ListenerUtil.mutListener.listen(34786) ? ((count = input.read(data)) == -1) : ((count = input.read(data)) != -1))))))) {
                                            ListenerUtil.loopListener.listen("_loopCounter256", ++_loopCounter256);
                                            if (!ListenerUtil.mutListener.listen(34785)) {
                                                // write to file
                                                output.write(data, 0, count);
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(34792)) {
                                    logger.debug("Logo downloaded");
                                }
                                if (!ListenerUtil.mutListener.listen(34793)) {
                                    output.close();
                                }
                                if (!ListenerUtil.mutListener.listen(34794)) {
                                    // ok, save the app logo
                                    this.setLogo(urlString, temporaryFile, expires, theme);
                                }
                                if (!ListenerUtil.mutListener.listen(34795)) {
                                    // remove the temporary file
                                    FileUtil.deleteFileOrWarn(temporaryFile, "temporary file", logger);
                                }
                            } catch (IOException x) {
                                if (!ListenerUtil.mutListener.listen(34784)) {
                                    // do nothing an try again later
                                    logger.error("Exception", x);
                                }
                            }
                        }
                    }
                }
            } finally {
                try {
                    final InputStream errorStream = connection.getErrorStream();
                    if (!ListenerUtil.mutListener.listen(34774)) {
                        if (errorStream != null) {
                            if (!ListenerUtil.mutListener.listen(34773)) {
                                errorStream.close();
                            }
                        }
                    }
                } catch (IOException e) {
                }
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(34770)) {
                logger.error("Exception", x);
            }
        }
    }

    protected boolean isRunning() {
        return this.running;
    }
}
