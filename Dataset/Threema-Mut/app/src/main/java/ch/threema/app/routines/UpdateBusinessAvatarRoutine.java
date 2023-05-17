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
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import static android.provider.MediaStore.MEDIA_IGNORE_FILENAME;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Update avatars of the business account
 */
public class UpdateBusinessAvatarRoutine implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UpdateBusinessAvatarRoutine.class);

    private final ContactService contactService;

    private FileService fileService;

    private ContactModel contactModel;

    private boolean running = false;

    private boolean forceUpdate = false;

    protected UpdateBusinessAvatarRoutine(ContactService contactService, FileService fileService, ContactModel contactModel) {
        this.contactService = contactService;
        if (!ListenerUtil.mutListener.listen(34804)) {
            this.fileService = fileService;
        }
        if (!ListenerUtil.mutListener.listen(34805)) {
            this.contactModel = contactModel;
        }
    }

    protected UpdateBusinessAvatarRoutine forceUpdate() {
        if (!ListenerUtil.mutListener.listen(34806)) {
            this.forceUpdate = true;
        }
        return this;
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(34807)) {
            this.running = true;
        }
        if (!ListenerUtil.mutListener.listen(34810)) {
            // validate instances
            if (!TestUtil.required(this.contactModel, this.contactService, this.fileService)) {
                if (!ListenerUtil.mutListener.listen(34808)) {
                    this.running = false;
                }
                if (!ListenerUtil.mutListener.listen(34809)) {
                    logger.error(": not all required instances defined");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(34813)) {
            if (!ContactUtil.isChannelContact(this.contactModel)) {
                if (!ListenerUtil.mutListener.listen(34811)) {
                    logger.error(": contact is not a business account");
                }
                if (!ListenerUtil.mutListener.listen(34812)) {
                    this.running = false;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(34817)) {
            // validate expiry date
            if ((ListenerUtil.mutListener.listen(34814) ? (!this.forceUpdate || !ContactUtil.isAvatarExpired(this.contactModel)) : (!this.forceUpdate && !ContactUtil.isAvatarExpired(this.contactModel)))) {
                if (!ListenerUtil.mutListener.listen(34815)) {
                    logger.error(": avatar is not expired");
                }
                if (!ListenerUtil.mutListener.listen(34816)) {
                    this.running = false;
                }
                return;
            }
        }
        // define default expiry date (now + 1day)
        Calendar tomorrowCalendar = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(34818)) {
            tomorrowCalendar.setTime(new Date());
        }
        if (!ListenerUtil.mutListener.listen(34819)) {
            tomorrowCalendar.add(Calendar.DATE, 1);
        }
        Date tomorrow = tomorrowCalendar.getTime();
        try {
            if (!ListenerUtil.mutListener.listen(34821)) {
                logger.debug("Download Avatar");
            }
            URL url = new URL("https://avatar.threema.ch/" + contactModel.getIdentity());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            if (!ListenerUtil.mutListener.listen(34822)) {
                connection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(url.getHost()));
            }
            try {
                if (!ListenerUtil.mutListener.listen(34825)) {
                    // Warning: This may implicitly open an error stream in the 4xx/5xx case!
                    connection.connect();
                }
                boolean avatarModified = false;
                int responseCode = connection.getResponseCode();
                if (!ListenerUtil.mutListener.listen(34860)) {
                    if ((ListenerUtil.mutListener.listen(34830) ? (responseCode >= HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34829) ? (responseCode <= HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34828) ? (responseCode > HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34827) ? (responseCode < HttpsURLConnection.HTTP_OK) : (ListenerUtil.mutListener.listen(34826) ? (responseCode == HttpsURLConnection.HTTP_OK) : (responseCode != HttpsURLConnection.HTTP_OK))))))) {
                        if (!ListenerUtil.mutListener.listen(34859)) {
                            if ((ListenerUtil.mutListener.listen(34853) ? (responseCode >= HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34852) ? (responseCode <= HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34851) ? (responseCode > HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34850) ? (responseCode < HttpsURLConnection.HTTP_NOT_FOUND) : (ListenerUtil.mutListener.listen(34849) ? (responseCode != HttpsURLConnection.HTTP_NOT_FOUND) : (responseCode == HttpsURLConnection.HTTP_NOT_FOUND))))))) {
                                if (!ListenerUtil.mutListener.listen(34854)) {
                                    logger.debug("Avatar not found");
                                }
                                if (!ListenerUtil.mutListener.listen(34855)) {
                                    // remove existing avatar
                                    avatarModified = this.fileService.removeContactAvatar(contactModel);
                                }
                                if (!ListenerUtil.mutListener.listen(34856)) {
                                    // add expires date = now + 1day
                                    this.contactModel.setAvatarExpires(tomorrow);
                                }
                                if (!ListenerUtil.mutListener.listen(34857)) {
                                    this.contactService.clearAvatarCache(this.contactModel);
                                }
                                if (!ListenerUtil.mutListener.listen(34858)) {
                                    this.contactService.save(this.contactModel);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(34831)) {
                            // cool, save avatar
                            logger.debug("Avatar found start download");
                        }
                        File temporaryFile = this.fileService.createTempFile(MEDIA_IGNORE_FILENAME, "avatardownload-" + String.valueOf(this.contactModel.getIdentity()).hashCode());
                        // might be -1: server did not report the length
                        int fileLength = connection.getContentLength();
                        if (!ListenerUtil.mutListener.listen(34832)) {
                            logger.debug("size: " + fileLength);
                        }
                        // download the file
                        Date expires = new Date(connection.getHeaderFieldDate("Expires", tomorrow.getTime()));
                        if (!ListenerUtil.mutListener.listen(34833)) {
                            logger.debug("expires " + expires);
                        }
                        byte[] data = new byte[4096];
                        int count;
                        try (InputStream input = connection.getInputStream();
                            FileOutputStream output = new FileOutputStream(temporaryFile.getPath())) {
                            if (!ListenerUtil.mutListener.listen(34841)) {
                                {
                                    long _loopCounter257 = 0;
                                    while ((ListenerUtil.mutListener.listen(34840) ? ((count = input.read(data)) >= -1) : (ListenerUtil.mutListener.listen(34839) ? ((count = input.read(data)) <= -1) : (ListenerUtil.mutListener.listen(34838) ? ((count = input.read(data)) > -1) : (ListenerUtil.mutListener.listen(34837) ? ((count = input.read(data)) < -1) : (ListenerUtil.mutListener.listen(34836) ? ((count = input.read(data)) == -1) : ((count = input.read(data)) != -1))))))) {
                                        ListenerUtil.loopListener.listen("_loopCounter257", ++_loopCounter257);
                                        if (!ListenerUtil.mutListener.listen(34835)) {
                                            // write to file
                                            output.write(data, 0, count);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(34842)) {
                                logger.debug("Avatar downloaded");
                            }
                            if (!ListenerUtil.mutListener.listen(34843)) {
                                // define avatar
                                this.contactService.setAvatar(contactModel, temporaryFile);
                            }
                            if (!ListenerUtil.mutListener.listen(34844)) {
                                // set expires header
                                this.contactModel.setAvatarExpires(expires);
                            }
                            if (!ListenerUtil.mutListener.listen(34845)) {
                                this.contactService.clearAvatarCache(this.contactModel);
                            }
                            if (!ListenerUtil.mutListener.listen(34846)) {
                                this.contactService.save(this.contactModel);
                            }
                            if (!ListenerUtil.mutListener.listen(34847)) {
                                // remove temporary file
                                FileUtil.deleteFileOrWarn(temporaryFile, "temporaryFile", logger);
                            }
                            if (!ListenerUtil.mutListener.listen(34848)) {
                                avatarModified = true;
                            }
                        } catch (IOException x) {
                            if (!ListenerUtil.mutListener.listen(34834)) {
                                // do nothing an try again later
                                logger.error("Failed to download", x);
                            }
                        }
                    }
                }
            } finally {
                try {
                    final InputStream errorStream = connection.getErrorStream();
                    if (!ListenerUtil.mutListener.listen(34824)) {
                        if (errorStream != null) {
                            if (!ListenerUtil.mutListener.listen(34823)) {
                                errorStream.close();
                            }
                        }
                    }
                } catch (IOException e) {
                }
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(34820)) {
                logger.error("Exception", x);
            }
        }
        if (!ListenerUtil.mutListener.listen(34861)) {
            this.running = false;
        }
    }

    protected boolean isRunning() {
        return this.running;
    }

    /**
     *  routine states
     */
    private static final Map<String, UpdateBusinessAvatarRoutine> runningUpdates = new HashMap<>();

    /**
     *  Update (if necessary) a business avatar
     *
     *  @param contactModel
     *  @param fileService
     *  @param contactService
     *  @return
     */
    public static final boolean startUpdate(ContactModel contactModel, FileService fileService, ContactService contactService) {
        return startUpdate(contactModel, fileService, contactService, false);
    }

    /**
     *  Update (if necessary) a business avatar
     *
     *  @param contactModel
     *  @param fileService
     *  @param contactService
     *  @param forceUpdate if true, the expiry date will be ignored
     *  @return
     */
    public static final boolean startUpdate(final ContactModel contactModel, FileService fileService, ContactService contactService, boolean forceUpdate) {
        UpdateBusinessAvatarRoutine instance = createInstance(contactModel, fileService, contactService, forceUpdate);
        if (!ListenerUtil.mutListener.listen(34866)) {
            if (instance != null) {
                // simple start thread!
                Thread thread = new Thread(instance);
                if (!ListenerUtil.mutListener.listen(34864)) {
                    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                        @Override
                        public void uncaughtException(Thread thread, Throwable throwable) {
                            if (!ListenerUtil.mutListener.listen(34862)) {
                                logger.error("Uncaught exception", throwable);
                            }
                            synchronized (runningUpdates) {
                                if (!ListenerUtil.mutListener.listen(34863)) {
                                    runningUpdates.remove(contactModel.getIdentity());
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(34865)) {
                    thread.start();
                }
                return thread != null;
            }
        }
        return false;
    }

    /**
     *  Update (if necessary) a business avatar
     *  IMPORTANT: this method run the method in the same thread
     *
     *  @param contactModel
     *  @param fileService
     *  @param contactService
     *  @param forceUpdate if true, the expiry date will be ignored
     *  @return
     */
    public static final boolean start(ContactModel contactModel, FileService fileService, ContactService contactService, boolean forceUpdate) {
        UpdateBusinessAvatarRoutine instance = createInstance(contactModel, fileService, contactService, forceUpdate);
        if (!ListenerUtil.mutListener.listen(34868)) {
            if (instance != null) {
                if (!ListenerUtil.mutListener.listen(34867)) {
                    instance.run();
                }
                return true;
            }
        }
        return false;
    }

    private static UpdateBusinessAvatarRoutine createInstance(ContactModel contactModel, FileService fileService, ContactService contactService, boolean forceUpdate) {
        synchronized (runningUpdates) {
            final String key = contactModel.getIdentity();
            if (!ListenerUtil.mutListener.listen(34878)) {
                // check if a update is running now
                if ((ListenerUtil.mutListener.listen(34870) ? ((ListenerUtil.mutListener.listen(34869) ? (!runningUpdates.containsKey(key) && runningUpdates.get(key) == null) : (!runningUpdates.containsKey(key) || runningUpdates.get(key) == null)) && !runningUpdates.get(key).isRunning()) : ((ListenerUtil.mutListener.listen(34869) ? (!runningUpdates.containsKey(key) && runningUpdates.get(key) == null) : (!runningUpdates.containsKey(key) || runningUpdates.get(key) == null)) || !runningUpdates.get(key).isRunning()))) {
                    if (!ListenerUtil.mutListener.listen(34873)) {
                        // check if necessary
                        if (!forceUpdate) {
                            if (!ListenerUtil.mutListener.listen(34872)) {
                                if (ContactUtil.isAvatarExpired(contactModel)) {
                                    if (!ListenerUtil.mutListener.listen(34871)) {
                                        logger.debug("do not update avatar, not expired");
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34874)) {
                        logger.debug("Start update business avatar routine");
                    }
                    UpdateBusinessAvatarRoutine newRoutine = new UpdateBusinessAvatarRoutine(contactService, fileService, contactModel);
                    if (!ListenerUtil.mutListener.listen(34876)) {
                        if (forceUpdate) {
                            if (!ListenerUtil.mutListener.listen(34875)) {
                                // set force update
                                newRoutine.forceUpdate();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34877)) {
                        runningUpdates.put(key, newRoutine);
                    }
                    return newRoutine;
                }
            }
        }
        return null;
    }
}
