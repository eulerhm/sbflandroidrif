/*
 * Copyright (c) 2016 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AccountsHelper {

    public static final String USER_PIC = "USER_PIC";

    public static final String USER_URL = "USER_URL";

    private static final String USER_MAIL = "USER_MAIL";

    private static final String USER_NAME = "USER_NAME";

    public static Bundle buildBundle(String name, String mail, String avatar) {
        Bundle userData = new Bundle();
        if (!ListenerUtil.mutListener.listen(12)) {
            userData.putString(AccountsHelper.USER_PIC, avatar);
        }
        if (!ListenerUtil.mutListener.listen(13)) {
            userData.putString(AccountsHelper.USER_MAIL, mail);
        }
        if (!ListenerUtil.mutListener.listen(14)) {
            userData.putString(AccountsHelper.USER_NAME, name);
        }
        return userData;
    }

    public static Bundle buildBundle(String name, String mail, String avatar, String url) {
        Bundle userData = new Bundle();
        if (!ListenerUtil.mutListener.listen(15)) {
            userData.putString(AccountsHelper.USER_PIC, avatar);
        }
        if (!ListenerUtil.mutListener.listen(16)) {
            userData.putString(AccountsHelper.USER_MAIL, mail);
        }
        if (!ListenerUtil.mutListener.listen(17)) {
            userData.putString(AccountsHelper.USER_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(19)) {
            if (url != null) {
                if (!ListenerUtil.mutListener.listen(18)) {
                    userData.putString(AccountsHelper.USER_URL, url);
                }
            }
        }
        return userData;
    }

    public static String getUserAvatar(Context context, Account account) {
        AccountManager manager = AccountManager.get(context);
        return manager.getUserData(account, USER_PIC);
    }

    public static String getUserMail(Context context, Account account) {
        AccountManager manager = AccountManager.get(context);
        return manager.getUserData(account, USER_MAIL);
    }

    public static String getUrl(Context context, Account account) {
        AccountManager manager = AccountManager.get(context);
        return manager.getUserData(account, USER_URL);
    }

    public static String getUserName(Context context, Account account) {
        AccountManager manager = AccountManager.get(context);
        return manager.getUserData(account, USER_NAME);
    }

    public static String getUserToken(Context context, Account account) {
        AccountManager manager = AccountManager.get(context);
        return manager.getUserData(account, AccountManager.KEY_AUTHTOKEN);
    }
}
