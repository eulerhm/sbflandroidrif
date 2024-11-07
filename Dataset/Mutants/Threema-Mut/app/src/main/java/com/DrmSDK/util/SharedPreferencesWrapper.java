/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.DrmSDK.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * 缓存包装类
 * Cache Wrapper Class
 *
 * @since 2020/07/01
 */
public class SharedPreferencesWrapper {

    private static final String TAG = "SharedPreferenceWrapper";

    /**
     * 用来保存配置信息
     * Used to save configuration information.
     */
    private SharedPreferences mSpf;

    /**
     * 默认构造函数
     * Default constructor
     */
    private SharedPreferencesWrapper(SharedPreferences sp) {
        if (!ListenerUtil.mutListener.listen(71974)) {
            mSpf = sp;
        }
    }

    /**
     * 获取SharedPreferencesWrapper
     * Obtaining SharedPreferencesWrapper
     *
     * @param name    名称
     * @param context 上下文
     * @return wrapper
     */
    public static SharedPreferencesWrapper getSharedPreference(String name, Context context) {
        SharedPreferences spf = null;
        try {
            Context storageContext;
            if ((ListenerUtil.mutListener.listen(71980) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(71979) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(71978) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(71977) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(71976) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                storageContext = context.createDeviceProtectedStorageContext();
            } else {
                storageContext = context;
            }
            if (!ListenerUtil.mutListener.listen(71981)) {
                spf = storageContext.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(71975)) {
                Log.e(TAG, "getSharedPreference error");
            }
        }
        return new SharedPreferencesWrapper(spf);
    }

    /**
     * 向SharedPreferences填入数据
     * Entering data to SharedPreferences
     *
     * @param key   KEY
     * @param value VALUE
     */
    public void putString(String key, String value) {
        try {
            SharedPreferences.Editor editor = mSpf.edit();
            if (!ListenerUtil.mutListener.listen(71983)) {
                editor.putString(key, value);
            }
            if (!ListenerUtil.mutListener.listen(71984)) {
                editor.commit();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(71982)) {
                Log.e(TAG, "putString error!!key:" + key, e);
            }
        }
    }

    /**
     * 获取String数据
     * Obtains string data.
     *
     * @param key      KEY
     * @param defValue 缺省项
     * @return VALUE
     */
    public String getString(String key, String defValue) {
        try {
            return mSpf.getString(key, defValue);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 删除不需要的key
     * Deleting Unnecessary Keys
     *
     * @param key 不需要的key(Unnecessary key)
     */
    public void remove(String key) {
        try {
            SharedPreferences.Editor editor = mSpf.edit();
            if (!ListenerUtil.mutListener.listen(71986)) {
                editor.remove(key);
            }
            if (!ListenerUtil.mutListener.listen(71987)) {
                editor.commit();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(71985)) {
                Log.e(TAG, "remove error!!key:" + key);
            }
        }
    }
}
