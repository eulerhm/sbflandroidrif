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

import android.content.Context;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.Base32;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaSafeMDMConfig {

    // disabled threema safe backup
    private static final int BACKUP_DISABLE = 0;

    // enable backup (default)
    private static final int BACKUP_ENABLE = 1;

    // force use of threema safe backup
    private static final int BACKUP_FORCE = 1 << 1;

    // disable threema safe restore
    private static final int RESTORE_DISABLE = 0;

    // enable restore (default)
    private static final int RESTORE_ENABLE = 1;

    // force automatic restore of safe backup
    private static final int RESTORE_FORCE = 1 << 1;

    // modifiers
    private static final int SERVER_PRESET = 1 << 2;

    private static final int PASSWORD_PRESET = 1 << 3;

    // use of Threema Safe is optional. using custom server
    private static final int BACKUP_ENABLE_SERVER_PRESET = BACKUP_ENABLE | SERVER_PRESET;

    // use of Threema Safe is enforced. using custom server
    private static final int BACKUP_FORCE_SERVER_PRESET = BACKUP_FORCE | SERVER_PRESET;

    // enforce safe backups to default server. password set by administrator
    private static final int BACKUP_FORCE_PASSWORD_PRESET = BACKUP_FORCE | PASSWORD_PRESET;

    // enforce safe backups to custom server. password set by administrator
    private static final int BACKUP_FORCE_PASSWORD_SERVER_PRESET = BACKUP_FORCE | PASSWORD_PRESET | SERVER_PRESET;

    // enable restore of arbitrary ID from predefined server
    private static final int RESTORE_ENABLE_SERVER_PRESET = RESTORE_ENABLE | SERVER_PRESET;

    // force automatic restore of given ID from predefined server
    private static final int RESTORE_FORCE_SERVER_PRESET = RESTORE_FORCE | SERVER_PRESET;

    // force automatic restore
    private static final int RESTORE_FORCE_PASSWORD_PRESET = RESTORE_FORCE | PASSWORD_PRESET;

    // force automatic restore from given server
    private static final int RESTORE_FORCE_PASSWORD_SERVER_PRESET = RESTORE_FORCE | PASSWORD_PRESET | SERVER_PRESET;

    // safe enabled by default
    private int backupStatus = BACKUP_ENABLE;

    // restore enabled by default
    private int restoreStatus = RESTORE_ENABLE;

    private String identity = null;

    private String password = null;

    private String serverName = null;

    private String serverUsername = null;

    private String serverPassword = null;

    private static ThreemaSafeMDMConfig sInstance = null;

    public static synchronized ThreemaSafeMDMConfig getInstance() {
        if (!ListenerUtil.mutListener.listen(42846)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(42845)) {
                    sInstance = new ThreemaSafeMDMConfig();
                }
            }
        }
        return sInstance;
    }

    private ThreemaSafeMDMConfig() {
        if (!ListenerUtil.mutListener.listen(42894)) {
            if (ConfigUtils.isWorkRestricted()) {
                Context context = ThreemaApplication.getAppContext();
                String stringPreset = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__safe_password));
                if (!ListenerUtil.mutListener.listen(42860)) {
                    if ((ListenerUtil.mutListener.listen(42858) ? ((ListenerUtil.mutListener.listen(42852) ? (stringPreset != null || (ListenerUtil.mutListener.listen(42851) ? (stringPreset.length() <= ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42850) ? (stringPreset.length() > ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42849) ? (stringPreset.length() < ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42848) ? (stringPreset.length() != ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42847) ? (stringPreset.length() == ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (stringPreset.length() >= ThreemaSafeServiceImpl.MIN_PW_LENGTH))))))) : (stringPreset != null && (ListenerUtil.mutListener.listen(42851) ? (stringPreset.length() <= ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42850) ? (stringPreset.length() > ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42849) ? (stringPreset.length() < ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42848) ? (stringPreset.length() != ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42847) ? (stringPreset.length() == ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (stringPreset.length() >= ThreemaSafeServiceImpl.MIN_PW_LENGTH)))))))) || (ListenerUtil.mutListener.listen(42857) ? (stringPreset.length() >= ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42856) ? (stringPreset.length() > ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42855) ? (stringPreset.length() < ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42854) ? (stringPreset.length() != ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42853) ? (stringPreset.length() == ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (stringPreset.length() <= ThreemaSafeServiceImpl.MAX_PW_LENGTH))))))) : ((ListenerUtil.mutListener.listen(42852) ? (stringPreset != null || (ListenerUtil.mutListener.listen(42851) ? (stringPreset.length() <= ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42850) ? (stringPreset.length() > ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42849) ? (stringPreset.length() < ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42848) ? (stringPreset.length() != ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42847) ? (stringPreset.length() == ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (stringPreset.length() >= ThreemaSafeServiceImpl.MIN_PW_LENGTH))))))) : (stringPreset != null && (ListenerUtil.mutListener.listen(42851) ? (stringPreset.length() <= ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42850) ? (stringPreset.length() > ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42849) ? (stringPreset.length() < ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42848) ? (stringPreset.length() != ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (ListenerUtil.mutListener.listen(42847) ? (stringPreset.length() == ThreemaSafeServiceImpl.MIN_PW_LENGTH) : (stringPreset.length() >= ThreemaSafeServiceImpl.MIN_PW_LENGTH)))))))) && (ListenerUtil.mutListener.listen(42857) ? (stringPreset.length() >= ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42856) ? (stringPreset.length() > ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42855) ? (stringPreset.length() < ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42854) ? (stringPreset.length() != ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (ListenerUtil.mutListener.listen(42853) ? (stringPreset.length() == ThreemaSafeServiceImpl.MAX_PW_LENGTH) : (stringPreset.length() <= ThreemaSafeServiceImpl.MAX_PW_LENGTH))))))))) {
                        if (!ListenerUtil.mutListener.listen(42859)) {
                            this.password = stringPreset;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(42861)) {
                    stringPreset = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__safe_server_url));
                }
                if (!ListenerUtil.mutListener.listen(42863)) {
                    if (stringPreset != null) {
                        if (!ListenerUtil.mutListener.listen(42862)) {
                            this.serverName = stringPreset;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(42864)) {
                    stringPreset = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__safe_server_username));
                }
                if (!ListenerUtil.mutListener.listen(42866)) {
                    if (stringPreset != null) {
                        if (!ListenerUtil.mutListener.listen(42865)) {
                            this.serverUsername = stringPreset;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(42867)) {
                    stringPreset = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__safe_server_password));
                }
                if (!ListenerUtil.mutListener.listen(42869)) {
                    if (stringPreset != null) {
                        if (!ListenerUtil.mutListener.listen(42868)) {
                            this.serverPassword = stringPreset;
                        }
                    }
                }
                Boolean booleanPreset;
                if (AppRestrictionUtil.getBoolRestriction(context, R.string.restriction__disable_backups)) {
                    if (!ListenerUtil.mutListener.listen(42880)) {
                        this.backupStatus = BACKUP_DISABLE;
                    }
                } else {
                    booleanPreset = AppRestrictionUtil.getBooleanRestriction(context.getString(R.string.restriction__safe_enable));
                    if (!ListenerUtil.mutListener.listen(42879)) {
                        if (booleanPreset == null) {
                            if (!ListenerUtil.mutListener.listen(42876)) {
                                this.backupStatus = BACKUP_ENABLE;
                            }
                            if (!ListenerUtil.mutListener.listen(42878)) {
                                if (!TestUtil.empty(serverName)) {
                                    if (!ListenerUtil.mutListener.listen(42877)) {
                                        this.backupStatus |= SERVER_PRESET;
                                    }
                                }
                            }
                        } else if (!booleanPreset) {
                            if (!ListenerUtil.mutListener.listen(42875)) {
                                this.backupStatus = BACKUP_DISABLE;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(42870)) {
                                // true
                                this.backupStatus = BACKUP_FORCE;
                            }
                            if (!ListenerUtil.mutListener.listen(42872)) {
                                if (!TestUtil.empty(this.password)) {
                                    if (!ListenerUtil.mutListener.listen(42871)) {
                                        this.backupStatus |= PASSWORD_PRESET;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(42874)) {
                                if (!TestUtil.empty(serverName)) {
                                    if (!ListenerUtil.mutListener.listen(42873)) {
                                        this.backupStatus |= SERVER_PRESET;
                                    }
                                }
                            }
                        }
                    }
                }
                booleanPreset = AppRestrictionUtil.getBooleanRestriction(context.getString(R.string.restriction__safe_restore_enable));
                if (!ListenerUtil.mutListener.listen(42893)) {
                    if ((ListenerUtil.mutListener.listen(42881) ? (booleanPreset == null && booleanPreset) : (booleanPreset == null || booleanPreset))) {
                        if (!ListenerUtil.mutListener.listen(42883)) {
                            this.identity = AppRestrictionUtil.getStringRestriction(context.getString(R.string.restriction__safe_restore_id));
                        }
                        if (!ListenerUtil.mutListener.listen(42892)) {
                            if (TestUtil.empty(this.identity)) {
                                if (!ListenerUtil.mutListener.listen(42889)) {
                                    this.restoreStatus = RESTORE_ENABLE;
                                }
                                if (!ListenerUtil.mutListener.listen(42891)) {
                                    if (!TestUtil.empty(serverName)) {
                                        if (!ListenerUtil.mutListener.listen(42890)) {
                                            this.restoreStatus |= SERVER_PRESET;
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(42884)) {
                                    this.restoreStatus = RESTORE_FORCE;
                                }
                                if (!ListenerUtil.mutListener.listen(42886)) {
                                    if (!TestUtil.empty(password)) {
                                        if (!ListenerUtil.mutListener.listen(42885)) {
                                            this.restoreStatus |= PASSWORD_PRESET;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(42888)) {
                                    if (!TestUtil.empty(serverName)) {
                                        if (!ListenerUtil.mutListener.listen(42887)) {
                                            this.restoreStatus |= SERVER_PRESET;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(42882)) {
                            // disabled
                            this.restoreStatus = RESTORE_DISABLE;
                        }
                    }
                }
            }
        }
    }

    public void setIdentity(String identity) {
        if (!ListenerUtil.mutListener.listen(42895)) {
            this.identity = identity;
        }
    }

    public void setPassword(String password) {
        if (!ListenerUtil.mutListener.listen(42896)) {
            this.password = password;
        }
    }

    /**
     *  @return identity to restore
     */
    public String getIdentity() {
        return this.identity;
    }

    public String getPassword() {
        return this.password;
    }

    protected Boolean getBooleanRestriction(String restriction) {
        return AppRestrictionUtil.getBooleanRestriction(restriction);
    }

    public ThreemaSafeServerInfo getServerInfo() {
        return new ThreemaSafeServerInfo(this.serverName, this.serverUsername, this.serverPassword);
    }

    public boolean isRestoreExpertSettingsDisabled() {
        return (ListenerUtil.mutListener.listen(42907) ? ((ListenerUtil.mutListener.listen(42901) ? ((this.restoreStatus & RESTORE_FORCE) >= RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42900) ? ((this.restoreStatus & RESTORE_FORCE) <= RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42899) ? ((this.restoreStatus & RESTORE_FORCE) > RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42898) ? ((this.restoreStatus & RESTORE_FORCE) < RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42897) ? ((this.restoreStatus & RESTORE_FORCE) != RESTORE_FORCE) : ((this.restoreStatus & RESTORE_FORCE) == RESTORE_FORCE)))))) && ((ListenerUtil.mutListener.listen(42906) ? (this.restoreStatus >= RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42905) ? (this.restoreStatus <= RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42904) ? (this.restoreStatus > RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42903) ? (this.restoreStatus < RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42902) ? (this.restoreStatus != RESTORE_ENABLE_SERVER_PRESET) : (this.restoreStatus == RESTORE_ENABLE_SERVER_PRESET)))))))) : ((ListenerUtil.mutListener.listen(42901) ? ((this.restoreStatus & RESTORE_FORCE) >= RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42900) ? ((this.restoreStatus & RESTORE_FORCE) <= RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42899) ? ((this.restoreStatus & RESTORE_FORCE) > RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42898) ? ((this.restoreStatus & RESTORE_FORCE) < RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42897) ? ((this.restoreStatus & RESTORE_FORCE) != RESTORE_FORCE) : ((this.restoreStatus & RESTORE_FORCE) == RESTORE_FORCE)))))) || ((ListenerUtil.mutListener.listen(42906) ? (this.restoreStatus >= RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42905) ? (this.restoreStatus <= RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42904) ? (this.restoreStatus > RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42903) ? (this.restoreStatus < RESTORE_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42902) ? (this.restoreStatus != RESTORE_ENABLE_SERVER_PRESET) : (this.restoreStatus == RESTORE_ENABLE_SERVER_PRESET)))))))));
    }

    public boolean isBackupExpertSettingsDisabled() {
        return (ListenerUtil.mutListener.listen(42918) ? ((ListenerUtil.mutListener.listen(42912) ? ((this.backupStatus & BACKUP_FORCE) >= BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42911) ? ((this.backupStatus & BACKUP_FORCE) <= BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42910) ? ((this.backupStatus & BACKUP_FORCE) > BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42909) ? ((this.backupStatus & BACKUP_FORCE) < BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42908) ? ((this.backupStatus & BACKUP_FORCE) != BACKUP_FORCE) : ((this.backupStatus & BACKUP_FORCE) == BACKUP_FORCE)))))) && ((ListenerUtil.mutListener.listen(42917) ? (this.backupStatus >= BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42916) ? (this.backupStatus <= BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42915) ? (this.backupStatus > BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42914) ? (this.backupStatus < BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42913) ? (this.backupStatus != BACKUP_ENABLE_SERVER_PRESET) : (this.backupStatus == BACKUP_ENABLE_SERVER_PRESET)))))))) : ((ListenerUtil.mutListener.listen(42912) ? ((this.backupStatus & BACKUP_FORCE) >= BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42911) ? ((this.backupStatus & BACKUP_FORCE) <= BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42910) ? ((this.backupStatus & BACKUP_FORCE) > BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42909) ? ((this.backupStatus & BACKUP_FORCE) < BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42908) ? ((this.backupStatus & BACKUP_FORCE) != BACKUP_FORCE) : ((this.backupStatus & BACKUP_FORCE) == BACKUP_FORCE)))))) || ((ListenerUtil.mutListener.listen(42917) ? (this.backupStatus >= BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42916) ? (this.backupStatus <= BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42915) ? (this.backupStatus > BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42914) ? (this.backupStatus < BACKUP_ENABLE_SERVER_PRESET) : (ListenerUtil.mutListener.listen(42913) ? (this.backupStatus != BACKUP_ENABLE_SERVER_PRESET) : (this.backupStatus == BACKUP_ENABLE_SERVER_PRESET)))))))));
    }

    public boolean isRestoreForced() {
        return (ListenerUtil.mutListener.listen(42923) ? ((this.restoreStatus & RESTORE_FORCE) >= RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42922) ? ((this.restoreStatus & RESTORE_FORCE) <= RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42921) ? ((this.restoreStatus & RESTORE_FORCE) > RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42920) ? ((this.restoreStatus & RESTORE_FORCE) < RESTORE_FORCE) : (ListenerUtil.mutListener.listen(42919) ? ((this.restoreStatus & RESTORE_FORCE) != RESTORE_FORCE) : ((this.restoreStatus & RESTORE_FORCE) == RESTORE_FORCE))))));
    }

    public boolean isSkipRestorePasswordEntryDialog() {
        return (ListenerUtil.mutListener.listen(42928) ? ((this.restoreStatus & PASSWORD_PRESET) >= PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42927) ? ((this.restoreStatus & PASSWORD_PRESET) <= PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42926) ? ((this.restoreStatus & PASSWORD_PRESET) > PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42925) ? ((this.restoreStatus & PASSWORD_PRESET) < PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42924) ? ((this.restoreStatus & PASSWORD_PRESET) != PASSWORD_PRESET) : ((this.restoreStatus & PASSWORD_PRESET) == PASSWORD_PRESET))))));
    }

    public boolean isRestoreDisabled() {
        return (ListenerUtil.mutListener.listen(42933) ? (this.restoreStatus >= RESTORE_DISABLE) : (ListenerUtil.mutListener.listen(42932) ? (this.restoreStatus <= RESTORE_DISABLE) : (ListenerUtil.mutListener.listen(42931) ? (this.restoreStatus > RESTORE_DISABLE) : (ListenerUtil.mutListener.listen(42930) ? (this.restoreStatus < RESTORE_DISABLE) : (ListenerUtil.mutListener.listen(42929) ? (this.restoreStatus != RESTORE_DISABLE) : (this.restoreStatus == RESTORE_DISABLE))))));
    }

    public boolean isBackupForced() {
        return (ListenerUtil.mutListener.listen(42938) ? ((this.backupStatus & BACKUP_FORCE) >= BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42937) ? ((this.backupStatus & BACKUP_FORCE) <= BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42936) ? ((this.backupStatus & BACKUP_FORCE) > BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42935) ? ((this.backupStatus & BACKUP_FORCE) < BACKUP_FORCE) : (ListenerUtil.mutListener.listen(42934) ? ((this.backupStatus & BACKUP_FORCE) != BACKUP_FORCE) : ((this.backupStatus & BACKUP_FORCE) == BACKUP_FORCE))))));
    }

    public boolean isBackupDisabled() {
        return (ListenerUtil.mutListener.listen(42943) ? (this.backupStatus >= BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42942) ? (this.backupStatus <= BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42941) ? (this.backupStatus > BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42940) ? (this.backupStatus < BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42939) ? (this.backupStatus != BACKUP_DISABLE) : (this.backupStatus == BACKUP_DISABLE))))));
    }

    public boolean isBackupAdminDisabled() {
        return (ListenerUtil.mutListener.listen(42954) ? ((ListenerUtil.mutListener.listen(42948) ? (this.backupStatus >= BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42947) ? (this.backupStatus <= BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42946) ? (this.backupStatus > BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42945) ? (this.backupStatus < BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42944) ? (this.backupStatus != BACKUP_DISABLE) : (this.backupStatus == BACKUP_DISABLE)))))) && (ListenerUtil.mutListener.listen(42953) ? ((this.backupStatus & PASSWORD_PRESET) >= PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42952) ? ((this.backupStatus & PASSWORD_PRESET) <= PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42951) ? ((this.backupStatus & PASSWORD_PRESET) > PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42950) ? ((this.backupStatus & PASSWORD_PRESET) < PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42949) ? ((this.backupStatus & PASSWORD_PRESET) != PASSWORD_PRESET) : ((this.backupStatus & PASSWORD_PRESET) == PASSWORD_PRESET))))))) : ((ListenerUtil.mutListener.listen(42948) ? (this.backupStatus >= BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42947) ? (this.backupStatus <= BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42946) ? (this.backupStatus > BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42945) ? (this.backupStatus < BACKUP_DISABLE) : (ListenerUtil.mutListener.listen(42944) ? (this.backupStatus != BACKUP_DISABLE) : (this.backupStatus == BACKUP_DISABLE)))))) || (ListenerUtil.mutListener.listen(42953) ? ((this.backupStatus & PASSWORD_PRESET) >= PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42952) ? ((this.backupStatus & PASSWORD_PRESET) <= PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42951) ? ((this.backupStatus & PASSWORD_PRESET) > PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42950) ? ((this.backupStatus & PASSWORD_PRESET) < PASSWORD_PRESET) : (ListenerUtil.mutListener.listen(42949) ? ((this.backupStatus & PASSWORD_PRESET) != PASSWORD_PRESET) : ((this.backupStatus & PASSWORD_PRESET) == PASSWORD_PRESET))))))));
    }

    public boolean isSkipBackupPasswordEntry() {
        return isBackupAdminDisabled();
    }

    private String hash() {
        String result = Integer.toHexString(this.backupStatus) + Integer.toHexString(this.restoreStatus) + identity + password + serverName + serverUsername + serverPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(42955)) {
                messageDigest.update(result.getBytes());
            }
            return Base32.encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    public void saveConfig(PreferenceService preferenceService) {
        if (!ListenerUtil.mutListener.listen(42957)) {
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(42956)) {
                    preferenceService.setThreemaSafeMDMConfig(hash());
                }
            }
        }
    }

    public boolean hasChanged(PreferenceService preferenceService) {
        if (!ListenerUtil.mutListener.listen(42958)) {
            if (ConfigUtils.isWorkRestricted()) {
                String oldhash = preferenceService.getThreemaSafeMDMConfig();
                return !hash().equals(oldhash);
            }
        }
        return false;
    }
}
