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
package ch.threema.app.threemasafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.Base64;
import ch.threema.client.Utils;
import static ch.threema.app.threemasafe.ThreemaSafeService.BACKUP_ID_LENGTH;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSafeServerInfo {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaSafeServerInfo.class);

    private static final String DEFAULT_THREEMA_SAFE_SERVER_NAME = "safe-%h.threema.ch";

    private static final String SAFE_URL_PREFIX = "https://";

    private static final String BACKUP_DIRECTORY_NAME = "backups/";

    private String serverName;

    private String serverUsername;

    private String serverPassword;

    public ThreemaSafeServerInfo() {
        if (!ListenerUtil.mutListener.listen(42959)) {
            this.serverName = DEFAULT_THREEMA_SAFE_SERVER_NAME;
        }
    }

    public ThreemaSafeServerInfo(String serverName, String serverUsername, String serverPassword) {
        if (!ListenerUtil.mutListener.listen(42960)) {
            this.serverUsername = serverUsername;
        }
        if (!ListenerUtil.mutListener.listen(42961)) {
            this.serverPassword = serverPassword;
        }
        if (!ListenerUtil.mutListener.listen(42962)) {
            this.setServerName(serverName);
        }
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        if (!ListenerUtil.mutListener.listen(42967)) {
            if (!TestUtil.empty(serverName)) {
                if (!ListenerUtil.mutListener.listen(42964)) {
                    serverName = serverName.trim();
                }
                if (!ListenerUtil.mutListener.listen(42966)) {
                    // strip https prefix
                    if (serverName.startsWith(SAFE_URL_PREFIX)) {
                        if (!ListenerUtil.mutListener.listen(42965)) {
                            serverName = serverName.substring(SAFE_URL_PREFIX.length());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42963)) {
                    serverName = DEFAULT_THREEMA_SAFE_SERVER_NAME;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42968)) {
            this.serverName = serverName;
        }
    }

    public String getServerUsername() {
        return serverUsername;
    }

    void setServerUsername(String serverUsername) {
        if (!ListenerUtil.mutListener.listen(42969)) {
            this.serverUsername = serverUsername;
        }
    }

    public String getServerPassword() {
        return serverPassword;
    }

    void setServerPassword(String serverPassword) {
        if (!ListenerUtil.mutListener.listen(42970)) {
            this.serverPassword = serverPassword;
        }
    }

    public boolean isDefaultServer() {
        return (ListenerUtil.mutListener.listen(42971) ? (this.serverName == null && this.serverName.equals(DEFAULT_THREEMA_SAFE_SERVER_NAME)) : (this.serverName == null || this.serverName.equals(DEFAULT_THREEMA_SAFE_SERVER_NAME)));
    }

    URL getBackupUrl(byte[] backupId) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(42973)) {
            if ((ListenerUtil.mutListener.listen(42972) ? (backupId == null && backupId.length != BACKUP_ID_LENGTH) : (backupId == null || backupId.length != BACKUP_ID_LENGTH))) {
                throw new ThreemaException("Invalid Backup ID");
            }
        }
        URL serverUrl = getServerUrl(backupId, BACKUP_DIRECTORY_NAME + Utils.byteArrayToHexString(backupId));
        if (!ListenerUtil.mutListener.listen(42974)) {
            if (serverUrl == null) {
                throw new ThreemaException("Invalid Server URL");
            }
        }
        return serverUrl;
    }

    URL getConfigUrl(byte[] backupId) throws ThreemaException {
        URL serverUrl = getServerUrl(backupId, "config");
        if (!ListenerUtil.mutListener.listen(42975)) {
            if (serverUrl == null) {
                throw new ThreemaException("Invalid URL");
            }
        }
        return serverUrl;
    }

    void addAuthorization(HttpsURLConnection urlConnection) {
        String username = serverUsername, password = serverPassword;
        if (!ListenerUtil.mutListener.listen(43005)) {
            if ((ListenerUtil.mutListener.listen(42976) ? (TestUtil.empty(serverUsername) && TestUtil.empty(serverPassword)) : (TestUtil.empty(serverUsername) || TestUtil.empty(serverPassword)))) {
                int atPos = serverName.indexOf("@");
                if (!ListenerUtil.mutListener.listen(43004)) {
                    if ((ListenerUtil.mutListener.listen(42981) ? (atPos >= 0) : (ListenerUtil.mutListener.listen(42980) ? (atPos <= 0) : (ListenerUtil.mutListener.listen(42979) ? (atPos < 0) : (ListenerUtil.mutListener.listen(42978) ? (atPos != 0) : (ListenerUtil.mutListener.listen(42977) ? (atPos == 0) : (atPos > 0))))))) {
                        String userInfo = serverName.substring(0, atPos);
                        int colonPos = userInfo.indexOf(":");
                        if (!ListenerUtil.mutListener.listen(43003)) {
                            if ((ListenerUtil.mutListener.listen(42996) ? ((ListenerUtil.mutListener.listen(42986) ? (colonPos >= 0) : (ListenerUtil.mutListener.listen(42985) ? (colonPos <= 0) : (ListenerUtil.mutListener.listen(42984) ? (colonPos < 0) : (ListenerUtil.mutListener.listen(42983) ? (colonPos != 0) : (ListenerUtil.mutListener.listen(42982) ? (colonPos == 0) : (colonPos > 0)))))) || (ListenerUtil.mutListener.listen(42995) ? (colonPos >= (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42994) ? (colonPos <= (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42993) ? (colonPos > (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42992) ? (colonPos != (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42991) ? (colonPos == (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (colonPos < (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(42986) ? (colonPos >= 0) : (ListenerUtil.mutListener.listen(42985) ? (colonPos <= 0) : (ListenerUtil.mutListener.listen(42984) ? (colonPos < 0) : (ListenerUtil.mutListener.listen(42983) ? (colonPos != 0) : (ListenerUtil.mutListener.listen(42982) ? (colonPos == 0) : (colonPos > 0)))))) && (ListenerUtil.mutListener.listen(42995) ? (colonPos >= (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42994) ? (colonPos <= (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42993) ? (colonPos > (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42992) ? (colonPos != (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (ListenerUtil.mutListener.listen(42991) ? (colonPos == (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))) : (colonPos < (ListenerUtil.mutListener.listen(42990) ? (userInfo.length() % 1) : (ListenerUtil.mutListener.listen(42989) ? (userInfo.length() / 1) : (ListenerUtil.mutListener.listen(42988) ? (userInfo.length() * 1) : (ListenerUtil.mutListener.listen(42987) ? (userInfo.length() + 1) : (userInfo.length() - 1)))))))))))))) {
                                if (!ListenerUtil.mutListener.listen(42997)) {
                                    username = userInfo.substring(0, colonPos);
                                }
                                if (!ListenerUtil.mutListener.listen(43002)) {
                                    password = userInfo.substring((ListenerUtil.mutListener.listen(43001) ? (colonPos % 1) : (ListenerUtil.mutListener.listen(43000) ? (colonPos / 1) : (ListenerUtil.mutListener.listen(42999) ? (colonPos * 1) : (ListenerUtil.mutListener.listen(42998) ? (colonPos - 1) : (colonPos + 1))))));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(43008)) {
            if ((ListenerUtil.mutListener.listen(43006) ? (!TestUtil.empty(username) || !TestUtil.empty(password)) : (!TestUtil.empty(username) && !TestUtil.empty(password)))) {
                String basicAuth = "Basic " + Base64.encodeBytes((username + ":" + password).getBytes());
                if (!ListenerUtil.mutListener.listen(43007)) {
                    urlConnection.setRequestProperty("Authorization", basicAuth);
                }
            }
        }
    }

    private URL getServerUrl(byte[] backupId, String filePart) {
        try {
            String serverUrl = "https://" + (isDefaultServer() ? serverName.replaceAll("%h", getShardHash(backupId)) : serverName);
            if (!ListenerUtil.mutListener.listen(43010)) {
                if (!serverUrl.endsWith("/")) {
                    if (!ListenerUtil.mutListener.listen(43009)) {
                        serverUrl += "/";
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(43011)) {
                serverUrl += filePart;
            }
            return new URL(serverUrl);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private String getShardHash(byte[] backupId) {
        if (!ListenerUtil.mutListener.listen(43013)) {
            if ((ListenerUtil.mutListener.listen(43012) ? (backupId != null || backupId.length == BACKUP_ID_LENGTH) : (backupId != null && backupId.length == BACKUP_ID_LENGTH))) {
                return Utils.byteArrayToHexString(backupId).substring(0, 2);
            }
        }
        return "xx";
    }

    public String getHostName() {
        try {
            return new URL("https://" + serverName).getHost();
        } catch (MalformedURLException e) {
            if (!ListenerUtil.mutListener.listen(43014)) {
                logger.error("Exception", e);
            }
        }
        return "";
    }
}
