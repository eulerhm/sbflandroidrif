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
package com.DrmSDK;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.TextUtils;
import android.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * EMUI辅助类
 * EMUI Auxiliary
 *
 * @since 2020/07/01
 */
public final class EMUISupportUtil {

    private static final Logger logger = LoggerFactory.getLogger(EMUISupportUtil.class);

    private static final String PATTERN = "^EmotionUI_[1-9]{1}";

    public static final int EMUI3 = 3;

    public static final int EMUI4 = 4;

    public static final int EMUI5 = 5;

    private int emuiVersion = 0;

    private static EMUISupportUtil instance = new EMUISupportUtil();

    public static final String UNDERLINE_SYMBOL = "_";

    public static EMUISupportUtil getInstance() {
        return instance;
    }

    private EMUISupportUtil() {
        String buildVersion = getBuildVersion();
        if (!ListenerUtil.mutListener.listen(72373)) {
            emuiVersion = getEmuiVersion(buildVersion);
        }
    }

    private String getBuildVersion() {
        return getProp("ro.build.version.emui");
    }

    /**
     * Get version of EMUI.
     *
     * @return EMUI version.
     */
    private int getEmuiVersion(String buildVersion) {
        Pattern pa = Pattern.compile(PATTERN);
        Matcher matcher = pa.matcher(buildVersion);
        String version = "";
        if (!ListenerUtil.mutListener.listen(72376)) {
            if (!matcher.find()) {
                if (!ListenerUtil.mutListener.listen(72375)) {
                    logger.error("can not find versionName: {}", buildVersion);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(72374)) {
                    version = matcher.group(0);
                }
            }
        }
        int ver = 0;
        if (!ListenerUtil.mutListener.listen(72385)) {
            if (!TextUtils.isEmpty(version)) {
                String[] strs = version.split(UNDERLINE_SYMBOL);
                if (!ListenerUtil.mutListener.listen(72384)) {
                    if ((ListenerUtil.mutListener.listen(72381) ? (strs.length >= 2) : (ListenerUtil.mutListener.listen(72380) ? (strs.length <= 2) : (ListenerUtil.mutListener.listen(72379) ? (strs.length > 2) : (ListenerUtil.mutListener.listen(72378) ? (strs.length < 2) : (ListenerUtil.mutListener.listen(72377) ? (strs.length != 2) : (strs.length == 2))))))) {
                        try {
                            if (!ListenerUtil.mutListener.listen(72383)) {
                                ver = Integer.parseInt(strs[1]);
                            }
                        } catch (NumberFormatException e) {
                            if (!ListenerUtil.mutListener.listen(72382)) {
                                logger.error("get emui version error!");
                            }
                        }
                    }
                }
            }
        }
        return ver;
    }

    /**
     * Get the EMUI version, such as EMUI3.0.5 will return 3,EMUI4.0 will return
     *
     * @return EMUI version.
     * @see EMUISupportUtil.EMUI3
     * @see EMUISupportUtil.EMUI4
     */
    public int getEmuiVersion() {
        return emuiVersion;
    }

    public boolean isEMUI3() {
        if (!ListenerUtil.mutListener.listen(72391)) {
            if ((ListenerUtil.mutListener.listen(72390) ? (EMUI3 >= emuiVersion) : (ListenerUtil.mutListener.listen(72389) ? (EMUI3 <= emuiVersion) : (ListenerUtil.mutListener.listen(72388) ? (EMUI3 > emuiVersion) : (ListenerUtil.mutListener.listen(72387) ? (EMUI3 < emuiVersion) : (ListenerUtil.mutListener.listen(72386) ? (EMUI3 != emuiVersion) : (EMUI3 == emuiVersion))))))) {
                return true;
            }
        }
        return false;
    }

    public boolean isEMUI4() {
        if (!ListenerUtil.mutListener.listen(72397)) {
            if ((ListenerUtil.mutListener.listen(72396) ? (EMUI4 >= emuiVersion) : (ListenerUtil.mutListener.listen(72395) ? (EMUI4 <= emuiVersion) : (ListenerUtil.mutListener.listen(72394) ? (EMUI4 > emuiVersion) : (ListenerUtil.mutListener.listen(72393) ? (EMUI4 < emuiVersion) : (ListenerUtil.mutListener.listen(72392) ? (EMUI4 != emuiVersion) : (EMUI4 == emuiVersion))))))) {
                return true;
            }
        }
        return false;
    }

    public boolean isEMUI4Style() {
        if (!ListenerUtil.mutListener.listen(72403)) {
            if ((ListenerUtil.mutListener.listen(72402) ? (emuiVersion <= EMUI4) : (ListenerUtil.mutListener.listen(72401) ? (emuiVersion > EMUI4) : (ListenerUtil.mutListener.listen(72400) ? (emuiVersion < EMUI4) : (ListenerUtil.mutListener.listen(72399) ? (emuiVersion != EMUI4) : (ListenerUtil.mutListener.listen(72398) ? (emuiVersion == EMUI4) : (emuiVersion >= EMUI4))))))) {
                return true;
            }
        }
        return false;
    }

    public boolean isEMUI5() {
        if (!ListenerUtil.mutListener.listen(72409)) {
            if ((ListenerUtil.mutListener.listen(72408) ? (EMUI5 >= emuiVersion) : (ListenerUtil.mutListener.listen(72407) ? (EMUI5 <= emuiVersion) : (ListenerUtil.mutListener.listen(72406) ? (EMUI5 > emuiVersion) : (ListenerUtil.mutListener.listen(72405) ? (EMUI5 < emuiVersion) : (ListenerUtil.mutListener.listen(72404) ? (EMUI5 != emuiVersion) : (EMUI5 == emuiVersion))))))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check Android OS whether supports EMUI.
     *
     * @return True is supported, false is not supported.
     */
    public boolean isSupportEMUI() {
        return (ListenerUtil.mutListener.listen(72414) ? (emuiVersion >= 0) : (ListenerUtil.mutListener.listen(72413) ? (emuiVersion <= 0) : (ListenerUtil.mutListener.listen(72412) ? (emuiVersion < 0) : (ListenerUtil.mutListener.listen(72411) ? (emuiVersion != 0) : (ListenerUtil.mutListener.listen(72410) ? (emuiVersion == 0) : (emuiVersion > 0))))));
    }

    /**
     * Get property by a string key.
     *
     * @return return null if get property failed.
     * @throws IllegalArgumentException if key exceeds 32 characters throws exception
     */
    public static String getProp(String key) {
        String ret = "";
        try {
            Class<?> systemProp = Class.forName("android.os.SystemProperties");
            // params type
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[1];
            if (!ListenerUtil.mutListener.listen(72420)) {
                paramTypes[0] = String.class;
            }
            Method get = systemProp.getMethod("get", paramTypes);
            // params
            Object[] params = new Object[1];
            if (!ListenerUtil.mutListener.listen(72421)) {
                params[0] = key;
            }
            if (!ListenerUtil.mutListener.listen(72422)) {
                ret = (String) get.invoke(systemProp, params);
            }
        } catch (IllegalArgumentException iAE) {
            if (!ListenerUtil.mutListener.listen(72415)) {
                logger.error("IllegalArgumentException get system properties error!", iAE);
            }
        } catch (ClassNotFoundException iAE) {
            if (!ListenerUtil.mutListener.listen(72416)) {
                logger.error("ClassNotFoundException get system properties error!", iAE);
            }
        } catch (NoSuchMethodException iAE) {
            if (!ListenerUtil.mutListener.listen(72417)) {
                logger.error("NoSuchMethodException get system properties error!", iAE);
            }
        } catch (IllegalAccessException iAE) {
            if (!ListenerUtil.mutListener.listen(72418)) {
                logger.error("IllegalAccessException get system properties error!", iAE);
            }
        } catch (InvocationTargetException iAE) {
            if (!ListenerUtil.mutListener.listen(72419)) {
                logger.error("InvocationTargetException get system properties error!", iAE);
            }
        }
        return ret;
    }
}
