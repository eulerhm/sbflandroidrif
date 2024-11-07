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
package ch.threema.app.stores;

import android.content.Context;
import android.content.SharedPreferences;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.preference.PreferenceManager;
import ch.threema.app.listeners.PreferenceListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.client.Utils;
import ch.threema.localcrypto.MasterKey;
import ch.threema.localcrypto.MasterKeyLockedException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PreferenceStore implements PreferenceStoreInterface {

    private static final Logger logger = LoggerFactory.getLogger(PreferenceStore.class);

    public static final String PREFS_IDENTITY = "identity";

    public static final String PREFS_SERVER_GROUP = "server_group";

    public static final String PREFS_PUBLIC_KEY = "public_key";

    public static final String PREFS_PRIVATE_KEY = "private_key";

    public static final String PREFS_PUBLIC_NICKNAME = "nickname";

    public static final String PREFS_LINKED_EMAIL = "linked_email";

    public static final String PREFS_LINKED_MOBILE = "linked_mobile";

    // typo
    public static final String PREFS_LINKED_EMAIL_PENDING = "linked_mobile_pending";

    public static final String PREFS_LINKED_MOBILE_PENDING = "linked_mobile_pending_since";

    public static final String PREFS_MOBILE_VERIFICATION_ID = "linked_mobile_verification_id";

    public static final String PREFS_LAST_REVOCATION_KEY_SET = "last_revocation_key_set";

    public static final String PREFS_REVOCATION_KEY_CHECKED = "revocation_key_checked";

    public static final String CRYPTED_FILE_PREFIX = ".crs-";

    private final Context context;

    private final MasterKey masterKey;

    private SharedPreferences sharedPreferences;

    public PreferenceStore(Context context, MasterKey masterKey) {
        this.context = context;
        this.masterKey = masterKey;
        if (!ListenerUtil.mutListener.listen(42379)) {
            this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    @Override
    public void remove(String key) {
        SharedPreferences.Editor e = this.sharedPreferences.edit();
        if (!ListenerUtil.mutListener.listen(42380)) {
            e.remove(key);
        }
        if (!ListenerUtil.mutListener.listen(42381)) {
            e.commit();
        }
    }

    @Override
    public void remove(List<String> keys) {
        SharedPreferences.Editor e = this.sharedPreferences.edit();
        if (!ListenerUtil.mutListener.listen(42384)) {
            {
                long _loopCounter489 = 0;
                for (String k : keys) {
                    ListenerUtil.loopListener.listen("_loopCounter489", ++_loopCounter489);
                    if (!ListenerUtil.mutListener.listen(42382)) {
                        e.remove(k);
                    }
                    if (!ListenerUtil.mutListener.listen(42383)) {
                        // try to remove crypted file
                        this.removeCryptedFile(k);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42385)) {
            e.commit();
        }
    }

    @Override
    public void remove(String key, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42388)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42387)) {
                    removeCryptedFile(key);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42386)) {
                    remove(key);
                }
            }
        }
    }

    @Override
    public void save(String key, String thing) {
        if (!ListenerUtil.mutListener.listen(42389)) {
            this.save(key, thing, false);
        }
    }

    @Override
    public void save(String key, String thing, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42393)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42392)) {
                    // save into a file
                    this.saveDataToCryptedFile(StringConversionUtil.stringToByteArray(thing), key);
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42390)) {
                    e.putString(key, thing);
                }
                if (!ListenerUtil.mutListener.listen(42391)) {
                    e.commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42394)) {
            this.fireOnChanged(key, thing);
        }
    }

    @Override
    public void save(String key, String[] things) {
        if (!ListenerUtil.mutListener.listen(42395)) {
            this.save(key, things, false);
        }
    }

    @Override
    public void save(String key, HashMap<Integer, String> things) {
        if (!ListenerUtil.mutListener.listen(42396)) {
            this.save(key, things, false);
        }
    }

    @Override
    public void save(String key, HashMap<Integer, String> things, boolean crypt) {
        JSONArray json = new JSONArray();
        if (!ListenerUtil.mutListener.listen(42400)) {
            {
                long _loopCounter490 = 0;
                for (HashMap.Entry<Integer, String> kv : things.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter490", ++_loopCounter490);
                    JSONArray keyValueArray = new JSONArray();
                    if (!ListenerUtil.mutListener.listen(42397)) {
                        keyValueArray.put(kv.getKey());
                    }
                    if (!ListenerUtil.mutListener.listen(42398)) {
                        keyValueArray.put(kv.getValue());
                    }
                    if (!ListenerUtil.mutListener.listen(42399)) {
                        json.put(keyValueArray);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42401)) {
            this.save(key, json, crypt);
        }
    }

    @Override
    public void saveIntegerHashMap(String key, HashMap<Integer, Integer> things) {
        JSONArray json = new JSONArray();
        if (!ListenerUtil.mutListener.listen(42405)) {
            {
                long _loopCounter491 = 0;
                for (HashMap.Entry<Integer, Integer> kv : things.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter491", ++_loopCounter491);
                    JSONArray keyValueArray = new JSONArray();
                    if (!ListenerUtil.mutListener.listen(42402)) {
                        keyValueArray.put(kv.getKey());
                    }
                    if (!ListenerUtil.mutListener.listen(42403)) {
                        keyValueArray.put(kv.getValue());
                    }
                    if (!ListenerUtil.mutListener.listen(42404)) {
                        json.put(keyValueArray);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42406)) {
            this.save(key, json);
        }
    }

    @Override
    public void saveStringHashMap(String key, HashMap<String, String> things, boolean crypt) {
        JSONArray json = new JSONArray();
        if (!ListenerUtil.mutListener.listen(42410)) {
            {
                long _loopCounter492 = 0;
                for (HashMap.Entry<String, String> kv : things.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter492", ++_loopCounter492);
                    JSONArray keyValueArray = new JSONArray();
                    if (!ListenerUtil.mutListener.listen(42407)) {
                        keyValueArray.put(kv.getKey());
                    }
                    if (!ListenerUtil.mutListener.listen(42408)) {
                        keyValueArray.put(kv.getValue());
                    }
                    if (!ListenerUtil.mutListener.listen(42409)) {
                        json.put(keyValueArray);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42411)) {
            this.save(key, json, crypt);
        }
    }

    @Override
    public void save(String key, String[] things, boolean crypt) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(42420)) {
            {
                long _loopCounter493 = 0;
                for (String s : things) {
                    ListenerUtil.loopListener.listen("_loopCounter493", ++_loopCounter493);
                    if (!ListenerUtil.mutListener.listen(42418)) {
                        if ((ListenerUtil.mutListener.listen(42416) ? (sb.length() >= 0) : (ListenerUtil.mutListener.listen(42415) ? (sb.length() <= 0) : (ListenerUtil.mutListener.listen(42414) ? (sb.length() < 0) : (ListenerUtil.mutListener.listen(42413) ? (sb.length() != 0) : (ListenerUtil.mutListener.listen(42412) ? (sb.length() == 0) : (sb.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(42417)) {
                                sb.append(';');
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(42419)) {
                        sb.append(s);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42424)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42423)) {
                    // save into a file
                    this.saveDataToCryptedFile(StringConversionUtil.stringToByteArray(sb.toString()), key);
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42421)) {
                    e.putString(key, sb.toString());
                }
                if (!ListenerUtil.mutListener.listen(42422)) {
                    e.commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42425)) {
            this.fireOnChanged(key, things);
        }
    }

    @Override
    public void save(String key, long thing) {
        if (!ListenerUtil.mutListener.listen(42426)) {
            this.save(key, thing, false);
        }
    }

    @Override
    public void save(String key, long thing, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42430)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42429)) {
                    // save into a file
                    this.saveDataToCryptedFile(Utils.hexStringToByteArray(String.valueOf(thing)), key);
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42427)) {
                    e.putLong(key, thing);
                }
                if (!ListenerUtil.mutListener.listen(42428)) {
                    e.commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42431)) {
            this.fireOnChanged(key, thing);
        }
    }

    @Override
    public void save(String key, int thing) {
        if (!ListenerUtil.mutListener.listen(42432)) {
            this.save(key, thing, false);
        }
    }

    @Override
    public void save(String key, int thing, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42436)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42435)) {
                    // save into a file
                    this.saveDataToCryptedFile(Utils.hexStringToByteArray(String.valueOf(thing)), key);
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42433)) {
                    e.putInt(key, thing);
                }
                if (!ListenerUtil.mutListener.listen(42434)) {
                    e.apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42437)) {
            this.fireOnChanged(key, thing);
        }
    }

    @Override
    public void save(String key, boolean thing) {
        SharedPreferences.Editor e = this.sharedPreferences.edit();
        if (!ListenerUtil.mutListener.listen(42438)) {
            e.putBoolean(key, thing);
        }
        if (!ListenerUtil.mutListener.listen(42439)) {
            e.apply();
        }
        if (!ListenerUtil.mutListener.listen(42440)) {
            this.fireOnChanged(key, thing);
        }
    }

    @Override
    public void save(String key, byte[] thing) {
        if (!ListenerUtil.mutListener.listen(42441)) {
            this.save(key, thing, false);
        }
    }

    @Override
    public void save(String key, byte[] thing, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42445)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42444)) {
                    // save into a file
                    this.saveDataToCryptedFile(thing, key);
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42442)) {
                    e.putString(key, Utils.byteArrayToHexString(thing));
                }
                if (!ListenerUtil.mutListener.listen(42443)) {
                    e.apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42446)) {
            this.fireOnChanged(key, thing);
        }
    }

    @Override
    public void save(String key, Date date) {
        if (!ListenerUtil.mutListener.listen(42447)) {
            this.save(key, date, false);
        }
    }

    @Override
    public void save(String key, Date date, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42448)) {
            // save as long
            this.save(key, date != null ? date.getTime() : 0, crypt);
        }
    }

    @Override
    public void save(String key, Long thing) {
        if (!ListenerUtil.mutListener.listen(42449)) {
            this.save(key, thing, false);
        }
    }

    @Override
    public void save(String key, Long thing, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42453)) {
            if (crypt) {
                // save into a file
                try {
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(42452)) {
                        logger.error("Exception", e);
                    }
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42450)) {
                    e.putLong(key, thing);
                }
                if (!ListenerUtil.mutListener.listen(42451)) {
                    e.apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42454)) {
            this.fireOnChanged(key, thing);
        }
    }

    @Override
    public void save(String key, JSONArray array) {
        if (!ListenerUtil.mutListener.listen(42455)) {
            save(key, array, false);
        }
    }

    @Override
    public void save(String key, Serializable object, boolean crypt) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutput out = new ObjectOutputStream(bos);
            if (!ListenerUtil.mutListener.listen(42456)) {
                out.writeObject(object);
            }
            if (!ListenerUtil.mutListener.listen(42457)) {
                out.flush();
            }
            if (!ListenerUtil.mutListener.listen(42458)) {
                this.save(key, bos.toByteArray(), crypt);
            }
        }
    }

    public void save(String key, JSONArray array, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42463)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42462)) {
                    if (array != null) {
                        if (!ListenerUtil.mutListener.listen(42461)) {
                            this.saveDataToCryptedFile(array.toString().getBytes(), key);
                        }
                    }
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42459)) {
                    e.putString(key, array.toString());
                }
                if (!ListenerUtil.mutListener.listen(42460)) {
                    e.apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42464)) {
            this.fireOnChanged(key, array);
        }
    }

    @Override
    public void save(String key, float thing) {
        SharedPreferences.Editor e = this.sharedPreferences.edit();
        if (!ListenerUtil.mutListener.listen(42465)) {
            e.putFloat(key, thing);
        }
        if (!ListenerUtil.mutListener.listen(42466)) {
            e.apply();
        }
    }

    @Override
    public void save(String key, JSONObject object, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42471)) {
            if (crypt) {
                if (!ListenerUtil.mutListener.listen(42470)) {
                    if (object != null) {
                        if (!ListenerUtil.mutListener.listen(42469)) {
                            this.saveDataToCryptedFile(object.toString().getBytes(), key);
                        }
                    }
                }
            } else {
                SharedPreferences.Editor e = this.sharedPreferences.edit();
                if (!ListenerUtil.mutListener.listen(42467)) {
                    e.putString(key, object.toString());
                }
                if (!ListenerUtil.mutListener.listen(42468)) {
                    e.apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42472)) {
            this.fireOnChanged(key, object);
        }
    }

    @Override
    @Nullable
    public String getString(String key) {
        return this.getString(key, false);
    }

    @Override
    @Nullable
    public String getString(String key, boolean crypt) {
        if (crypt) {
            byte[] r = this.getDataFromCryptedFile(key);
            if (r != null) {
                return StringConversionUtil.byteArrayToString(r);
            } else {
                return null;
            }
        } else {
            String value = null;
            try {
                if (!ListenerUtil.mutListener.listen(42474)) {
                    value = this.sharedPreferences.getString(key, null);
                }
            } catch (ClassCastException e) {
                if (!ListenerUtil.mutListener.listen(42473)) {
                    logger.error("Class cast exception", e);
                }
            }
            return value;
        }
    }

    @Override
    public String[] getStringArray(String key) {
        return this.getStringArray(key, false);
    }

    @Override
    public String[] getStringArray(String key, boolean crypted) {
        String value = null;
        if (!ListenerUtil.mutListener.listen(42478)) {
            if (crypted) {
                byte[] r = this.getDataFromCryptedFile(key);
                if (!ListenerUtil.mutListener.listen(42477)) {
                    if (r != null) {
                        if (!ListenerUtil.mutListener.listen(42476)) {
                            value = StringConversionUtil.byteArrayToString(r);
                        }
                    } else {
                        return null;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42475)) {
                    value = this.sharedPreferences.getString(key, null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42485)) {
            if ((ListenerUtil.mutListener.listen(42484) ? (value != null || (ListenerUtil.mutListener.listen(42483) ? (value.length() >= 0) : (ListenerUtil.mutListener.listen(42482) ? (value.length() <= 0) : (ListenerUtil.mutListener.listen(42481) ? (value.length() < 0) : (ListenerUtil.mutListener.listen(42480) ? (value.length() != 0) : (ListenerUtil.mutListener.listen(42479) ? (value.length() == 0) : (value.length() > 0))))))) : (value != null && (ListenerUtil.mutListener.listen(42483) ? (value.length() >= 0) : (ListenerUtil.mutListener.listen(42482) ? (value.length() <= 0) : (ListenerUtil.mutListener.listen(42481) ? (value.length() < 0) : (ListenerUtil.mutListener.listen(42480) ? (value.length() != 0) : (ListenerUtil.mutListener.listen(42479) ? (value.length() == 0) : (value.length() > 0))))))))) {
                return value.split(";");
            }
        }
        return null;
    }

    @Override
    public HashMap<Integer, String> getHashMap(String key, boolean encrypted) {
        HashMap<Integer, String> result = new HashMap<>();
        try {
            JSONArray jsonArray;
            if (encrypted) {
                jsonArray = new JSONArray(new String(getDataFromCryptedFile(key)));
            } else {
                jsonArray = new JSONArray(this.sharedPreferences.getString(key, "[]"));
            }
            if (!ListenerUtil.mutListener.listen(42493)) {
                {
                    long _loopCounter494 = 0;
                    for (int n = 0; (ListenerUtil.mutListener.listen(42492) ? (n >= jsonArray.length()) : (ListenerUtil.mutListener.listen(42491) ? (n <= jsonArray.length()) : (ListenerUtil.mutListener.listen(42490) ? (n > jsonArray.length()) : (ListenerUtil.mutListener.listen(42489) ? (n != jsonArray.length()) : (ListenerUtil.mutListener.listen(42488) ? (n == jsonArray.length()) : (n < jsonArray.length())))))); n++) {
                        ListenerUtil.loopListener.listen("_loopCounter494", ++_loopCounter494);
                        JSONArray keyValuePair = jsonArray.getJSONArray(n);
                        if (!ListenerUtil.mutListener.listen(42487)) {
                            result.put(keyValuePair.getInt(0), keyValuePair.getString(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42486)) {
                logger.error("Exception", e);
            }
        }
        return result;
    }

    @Override
    public HashMap<String, String> getStringHashMap(String key, boolean encrypted) {
        HashMap<String, String> result = new HashMap<>();
        try {
            JSONArray jsonArray = null;
            if (!ListenerUtil.mutListener.listen(42503)) {
                if (encrypted) {
                    byte[] data = getDataFromCryptedFile(key);
                    if (!ListenerUtil.mutListener.listen(42502)) {
                        if ((ListenerUtil.mutListener.listen(42500) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(42499) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(42498) ? (data.length < 0) : (ListenerUtil.mutListener.listen(42497) ? (data.length != 0) : (ListenerUtil.mutListener.listen(42496) ? (data.length == 0) : (data.length > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(42501)) {
                                jsonArray = new JSONArray(new String(data));
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(42495)) {
                        jsonArray = new JSONArray(this.sharedPreferences.getString(key, "[]"));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(42511)) {
                if (jsonArray != null) {
                    if (!ListenerUtil.mutListener.listen(42510)) {
                        {
                            long _loopCounter495 = 0;
                            for (int n = 0; (ListenerUtil.mutListener.listen(42509) ? (n >= jsonArray.length()) : (ListenerUtil.mutListener.listen(42508) ? (n <= jsonArray.length()) : (ListenerUtil.mutListener.listen(42507) ? (n > jsonArray.length()) : (ListenerUtil.mutListener.listen(42506) ? (n != jsonArray.length()) : (ListenerUtil.mutListener.listen(42505) ? (n == jsonArray.length()) : (n < jsonArray.length())))))); n++) {
                                ListenerUtil.loopListener.listen("_loopCounter495", ++_loopCounter495);
                                JSONArray keyValuePair = jsonArray.getJSONArray(n);
                                if (!ListenerUtil.mutListener.listen(42504)) {
                                    result.put(keyValuePair.getString(0), keyValuePair.getString(1));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42494)) {
                logger.error("Exception", e);
            }
        }
        return result;
    }

    @Override
    public HashMap<Integer, Integer> getHashMap(String key) {
        HashMap<Integer, Integer> result = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(this.sharedPreferences.getString(key, "[]"));
            if (!ListenerUtil.mutListener.listen(42519)) {
                {
                    long _loopCounter496 = 0;
                    for (int n = 0; (ListenerUtil.mutListener.listen(42518) ? (n >= jsonArray.length()) : (ListenerUtil.mutListener.listen(42517) ? (n <= jsonArray.length()) : (ListenerUtil.mutListener.listen(42516) ? (n > jsonArray.length()) : (ListenerUtil.mutListener.listen(42515) ? (n != jsonArray.length()) : (ListenerUtil.mutListener.listen(42514) ? (n == jsonArray.length()) : (n < jsonArray.length())))))); n++) {
                        ListenerUtil.loopListener.listen("_loopCounter496", ++_loopCounter496);
                        JSONArray keyValuePair = jsonArray.getJSONArray(n);
                        if (!ListenerUtil.mutListener.listen(42513)) {
                            result.put(keyValuePair.getInt(0), keyValuePair.getInt(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42512)) {
                logger.error("Exception", e);
            }
        }
        return result;
    }

    @Override
    public String getHexString(String key, boolean crypt) {
        // can be removed in a few years :)
        if (crypt) {
            byte[] r = this.getDataFromCryptedFile(key);
            if (r != null) {
                return Utils.byteArrayToHexString(r);
            } else {
                return null;
            }
        } else {
            return this.sharedPreferences.getString(key, null);
        }
    }

    @Override
    public Long getLong(String key) {
        return this.getLong(key, false);
    }

    @Override
    public Long getLong(String key, boolean crypt) {
        if (crypt) {
            byte[] r = this.getDataFromCryptedFile(key);
            if (r != null) {
                return Long.getLong(Utils.byteArrayToHexString(r));
            } else {
                return null;
            }
        } else {
            return this.sharedPreferences.getLong(key, 0);
        }
    }

    @Override
    public Date getDate(String key) {
        return this.getDate(key, false);
    }

    @Override
    public Date getDate(String key, boolean crypt) {
        Long l = this.getLong(key, crypt);
        if (!ListenerUtil.mutListener.listen(42526)) {
            if ((ListenerUtil.mutListener.listen(42525) ? (l != null || (ListenerUtil.mutListener.listen(42524) ? (l >= 0) : (ListenerUtil.mutListener.listen(42523) ? (l <= 0) : (ListenerUtil.mutListener.listen(42522) ? (l < 0) : (ListenerUtil.mutListener.listen(42521) ? (l != 0) : (ListenerUtil.mutListener.listen(42520) ? (l == 0) : (l > 0))))))) : (l != null && (ListenerUtil.mutListener.listen(42524) ? (l >= 0) : (ListenerUtil.mutListener.listen(42523) ? (l <= 0) : (ListenerUtil.mutListener.listen(42522) ? (l < 0) : (ListenerUtil.mutListener.listen(42521) ? (l != 0) : (ListenerUtil.mutListener.listen(42520) ? (l == 0) : (l > 0))))))))) {
                return new Date(l);
            }
        }
        return null;
    }

    @Override
    public long getDateAsLong(String key) {
        Long l = this.getLong(key, false);
        if (!ListenerUtil.mutListener.listen(42533)) {
            if ((ListenerUtil.mutListener.listen(42532) ? (l != null || (ListenerUtil.mutListener.listen(42531) ? (l >= 0) : (ListenerUtil.mutListener.listen(42530) ? (l <= 0) : (ListenerUtil.mutListener.listen(42529) ? (l < 0) : (ListenerUtil.mutListener.listen(42528) ? (l != 0) : (ListenerUtil.mutListener.listen(42527) ? (l == 0) : (l > 0))))))) : (l != null && (ListenerUtil.mutListener.listen(42531) ? (l >= 0) : (ListenerUtil.mutListener.listen(42530) ? (l <= 0) : (ListenerUtil.mutListener.listen(42529) ? (l < 0) : (ListenerUtil.mutListener.listen(42528) ? (l != 0) : (ListenerUtil.mutListener.listen(42527) ? (l == 0) : (l > 0))))))))) {
                return l;
            }
        }
        return 0L;
    }

    @Override
    public Integer getInt(String key) {
        return this.getInt(key, false);
    }

    @Override
    public Integer getInt(String key, boolean crypt) {
        if (crypt) {
            byte[] r = this.getDataFromCryptedFile(key);
            if (r != null) {
                return Integer.getInteger(Utils.byteArrayToHexString(r));
            } else {
                return null;
            }
        } else {
            return this.sharedPreferences.getInt(key, 0);
        }
    }

    @Override
    public float getFloat(String key, float defValue) {
        return this.sharedPreferences.getFloat(key, defValue);
    }

    public boolean getBoolean(String key) {
        return this.sharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return this.sharedPreferences.getBoolean(key, defValue);
    }

    @Override
    public byte[] getBytes(String key) {
        return this.getBytes(key, false);
    }

    @Override
    public byte[] getBytes(String key, boolean crypt) {
        if (!ListenerUtil.mutListener.listen(42535)) {
            if (crypt) {
                return this.getDataFromCryptedFile(key);
            } else {
                String v = this.sharedPreferences.getString(key, null);
                if (!ListenerUtil.mutListener.listen(42534)) {
                    if (v != null) {
                        return Utils.hexStringToByteArray(v);
                    }
                }
            }
        }
        return new byte[0];
    }

    @Override
    public JSONArray getJSONArray(String key, boolean crypt) {
        try {
            if (!ListenerUtil.mutListener.listen(42537)) {
                if (crypt) {
                    byte[] data = this.getDataFromCryptedFile(key);
                    return new JSONArray(new String(data));
                } else {
                    return new JSONArray(this.sharedPreferences.getString(key, "[]"));
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42536)) {
                logger.error("Exception", e);
            }
        }
        return new JSONArray();
    }

    @Override
    public JSONObject getJSONObject(String key, boolean crypt) {
        try {
            if (!ListenerUtil.mutListener.listen(42539)) {
                if (crypt) {
                    byte[] data = this.getDataFromCryptedFile(key);
                    return new JSONObject(new String(data));
                } else {
                    String data = this.sharedPreferences.getString(key, "[]");
                    return new JSONObject(data);
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42538)) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    @Override
    public <T> T getRealObject(String key, boolean crypt) {
        try {
            if (!ListenerUtil.mutListener.listen(42545)) {
                if (crypt) {
                    byte[] data = this.getDataFromCryptedFile(key);
                    ByteArrayInputStream bis = new ByteArrayInputStream(data);
                    ObjectInput in = null;
                    T o = null;
                    try {
                        if (!ListenerUtil.mutListener.listen(42543)) {
                            in = new ObjectInputStream(bis);
                        }
                        if (!ListenerUtil.mutListener.listen(42544)) {
                            o = (T) in.readObject();
                        }
                    } finally {
                        try {
                            if (!ListenerUtil.mutListener.listen(42542)) {
                                if (in != null) {
                                    if (!ListenerUtil.mutListener.listen(42541)) {
                                        in.close();
                                    }
                                }
                            }
                        } catch (IOException ex) {
                        }
                    }
                    return o;
                } else {
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42540)) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        if (!ListenerUtil.mutListener.listen(42546)) {
            editor.clear();
        }
        if (!ListenerUtil.mutListener.listen(42547)) {
            editor.apply();
        }
        try {
            if (!ListenerUtil.mutListener.listen(42550)) {
                {
                    long _loopCounter497 = 0;
                    for (File f : this.context.getFilesDir().listFiles(new FilenameFilter() {

                        @Override
                        public boolean accept(File dir, String filename) {
                            return filename.startsWith(CRYPTED_FILE_PREFIX);
                        }
                    })) {
                        ListenerUtil.loopListener.listen("_loopCounter497", ++_loopCounter497);
                        if (!ListenerUtil.mutListener.listen(42549)) {
                            FileUtil.deleteFileOrWarn(f, "clear", logger);
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42548)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public Map<String, ?> getAllNonCrypted() {
        return this.sharedPreferences.getAll();
    }

    @Override
    public Set<String> getStringSet(final String key, final int defaultRes) {
        if (this.sharedPreferences.contains(key)) {
            return this.sharedPreferences.getStringSet(key, Collections.emptySet());
        } else {
            return new HashSet<>(Arrays.asList(context.getResources().getStringArray(defaultRes)));
        }
    }

    private void fireOnChanged(final String key, final Object value) {
        if (!ListenerUtil.mutListener.listen(42552)) {
            ListenerManager.preferenceListeners.handle(new ListenerManager.HandleListener<PreferenceListener>() {

                @Override
                public void handle(PreferenceListener listener) {
                    if (!ListenerUtil.mutListener.listen(42551)) {
                        listener.onChanged(key, value);
                    }
                }
            });
        }
    }

    @WorkerThread
    private void removeCryptedFile(String filename) {
        File f = new File(this.context.getFilesDir(), CRYPTED_FILE_PREFIX + filename);
        if (!ListenerUtil.mutListener.listen(42554)) {
            if (f.exists()) {
                if (!ListenerUtil.mutListener.listen(42553)) {
                    FileUtil.deleteFileOrWarn(f, "removeCryptedFile", logger);
                }
            }
        }
    }

    @AnyThread
    private void saveDataToCryptedFile(byte[] data, String filename) {
        File f = new File(context.getFilesDir(), CRYPTED_FILE_PREFIX + filename);
        if (!ListenerUtil.mutListener.listen(42557)) {
            if (!f.exists()) {
                try {
                    if (!ListenerUtil.mutListener.listen(42556)) {
                        FileUtil.createNewFileOrLog(f, logger);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(42555)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(f);
            CipherOutputStream cipherOutputStream = masterKey.getCipherOutputStream(fileOutputStream)) {
            if (!ListenerUtil.mutListener.listen(42559)) {
                cipherOutputStream.write(data);
            }
        } catch (IOException | MasterKeyLockedException e) {
            if (!ListenerUtil.mutListener.listen(42558)) {
                logger.error("Unable to store prefs", e);
            }
        }
    }

    @WorkerThread
    private byte[] getDataFromCryptedFile(String filename) {
        File f = new File(this.context.getFilesDir(), CRYPTED_FILE_PREFIX + filename);
        if (!ListenerUtil.mutListener.listen(42567)) {
            if (f.exists()) {
                CipherInputStream cis = null;
                FileInputStream fis = null;
                try {
                    if (!ListenerUtil.mutListener.listen(42565)) {
                        fis = new FileInputStream(f);
                    }
                    if (!ListenerUtil.mutListener.listen(42566)) {
                        cis = masterKey.getCipherInputStream(fis);
                    }
                    return IOUtils.toByteArray(cis);
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(42560)) {
                        // do nothing
                        logger.error("getDataFromCryptedFile: " + filename, x);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(42562)) {
                        if (cis != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(42561)) {
                                    cis.close();
                                }
                            } catch (IOException e) {
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(42564)) {
                        if (fis != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(42563)) {
                                    fis.close();
                                }
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
        }
        return new byte[0];
    }
}
