/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.voip.receivers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;
import androidx.core.content.ContextCompat;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Attempt to reject regular phone call if a Threema Call is running
 */
public class IncomingMobileCallReceiver extends BroadcastReceiver {

    private static final Logger logger = LoggerFactory.getLogger(IncomingMobileCallReceiver.class);

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(58653)) {
            if (!intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58654)) {
            logger.debug("Incoming mobile call");
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(58655)) {
            if (serviceManager == null) {
                return;
            }
        }
        VoipStateService voipStateService;
        try {
            voipStateService = serviceManager.getVoipStateService();
        } catch (ThreemaException e) {
            return;
        }
        if (!ListenerUtil.mutListener.listen(58671)) {
            if (!voipStateService.getCallState().isIdle()) {
                if (!ListenerUtil.mutListener.listen(58670)) {
                    if ((ListenerUtil.mutListener.listen(58660) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58659) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58658) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58657) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58656) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                        if (!ListenerUtil.mutListener.listen(58669)) {
                            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ANSWER_PHONE_CALLS) == PackageManager.PERMISSION_GRANTED) {
                                TelecomManager telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
                                if (!ListenerUtil.mutListener.listen(58668)) {
                                    if (telecomManager != null) {
                                        if (!ListenerUtil.mutListener.listen(58667)) {
                                            telecomManager.endCall();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58666)) {
                            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                                // Hacky, hacky
                                try {
                                    @SuppressLint("PrivateApi")
                                    Method getTelephony = telephonyManager.getClass().getDeclaredMethod("getITelephony");
                                    if (!ListenerUtil.mutListener.listen(58662)) {
                                        getTelephony.setAccessible(true);
                                    }
                                    Object telephonyService = getTelephony.invoke(telephonyManager);
                                    Method silenceRinger = telephonyService.getClass().getDeclaredMethod("silenceRinger");
                                    if (!ListenerUtil.mutListener.listen(58663)) {
                                        silenceRinger.invoke(telephonyService);
                                    }
                                    Method endCall = telephonyService.getClass().getDeclaredMethod("endCall");
                                    if (!ListenerUtil.mutListener.listen(58664)) {
                                        endCall.invoke(telephonyService);
                                    }
                                    if (!ListenerUtil.mutListener.listen(58665)) {
                                        logger.debug("Mobile call rejected");
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(58661)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
