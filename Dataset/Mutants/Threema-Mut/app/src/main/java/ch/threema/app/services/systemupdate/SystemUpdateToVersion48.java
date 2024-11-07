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
package ch.threema.app.services.systemupdate;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.preference.PreferenceManager;
import java.sql.SQLException;
import java.util.Arrays;
import ch.threema.app.R;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.stores.PreferenceStore;
import ch.threema.app.utils.LogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 *  rename account manager accounts
 */
public class SystemUpdateToVersion48 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion48.class);

    private final Context context;

    public SystemUpdateToVersion48(Context context) {
        this.context = context;
    }

    @Override
    public boolean runDirectly() throws SQLException {
        final AccountManager accountManager = AccountManager.get(this.context);
        final String myIdentity = PreferenceManager.getDefaultSharedPreferences(this.context).getString(PreferenceStore.PREFS_IDENTITY, null);
        if (!ListenerUtil.mutListener.listen(36337)) {
            if ((ListenerUtil.mutListener.listen(36322) ? (accountManager != null || myIdentity != null) : (accountManager != null && myIdentity != null))) {
                try {
                    Account accountToRename = null;
                    if (!ListenerUtil.mutListener.listen(36328)) {
                        {
                            long _loopCounter344 = 0;
                            for (Account account : Arrays.asList(accountManager.getAccountsByType(context.getPackageName()))) {
                                ListenerUtil.loopListener.listen("_loopCounter344", ++_loopCounter344);
                                if (!ListenerUtil.mutListener.listen(36327)) {
                                    if (account.name.equals(myIdentity)) {
                                        if (!ListenerUtil.mutListener.listen(36326)) {
                                            accountToRename = account;
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(36325)) {
                                            if (!account.name.equals(context.getString(R.string.title_mythreemaid))) {
                                                if (!ListenerUtil.mutListener.listen(36324)) {
                                                    accountManager.removeAccount(account, null, null);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36336)) {
                        // rename old-style ID-based account to generic name
                        if ((ListenerUtil.mutListener.listen(36334) ? (accountToRename != null || (ListenerUtil.mutListener.listen(36333) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36332) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36331) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36330) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36329) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) : (accountToRename != null && (ListenerUtil.mutListener.listen(36333) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36332) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36331) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36330) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(36329) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))))) {
                            if (!ListenerUtil.mutListener.listen(36335)) {
                                accountManager.renameAccount(accountToRename, context.getString(R.string.title_mythreemaid), null, null);
                            }
                        }
                    }
                    return true;
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(36323)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean runASync() {
        return true;
    }

    @Override
    public String getText() {
        return "version 48";
    }
}
