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
package ch.threema.app.voip.util;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.UpdateFeatureLevelRoutine;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.activities.CallActivity;
import ch.threema.app.voip.services.VoipCallService;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.base.ThreemaException;
import ch.threema.client.ThreemaFeature;
import ch.threema.logging.ThreemaLogger;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoipUtil {

    private static final Logger logger = LoggerFactory.getLogger(VoipUtil.class);

    private static final String DIALOG_TAG_FETCHING_FEATURE_MASK = "fetchingFeatureMask";

    /**
     *  Send a VoIP broadcast without any intent extras
     */
    public static void sendVoipBroadcast(Context context, String action) {
        if (!ListenerUtil.mutListener.listen(60508)) {
            sendVoipBroadcast(context, action, null, null);
        }
    }

    /**
     *  Send a VoIP broadcast with a single intent extra
     */
    public static void sendVoipBroadcast(Context context, String action, String extraName, String extra) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(60509)) {
            intent.setAction(action);
        }
        if (!ListenerUtil.mutListener.listen(60511)) {
            if (!TestUtil.empty(extraName)) {
                if (!ListenerUtil.mutListener.listen(60510)) {
                    intent.putExtra(extraName, extra);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60512)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    /**
     *  Send a command to a service
     */
    public static void sendVoipCommand(Context context, Class service, String action) {
        final Intent intent = new Intent(context, service);
        if (!ListenerUtil.mutListener.listen(60513)) {
            intent.setAction(action);
        }
        if (!ListenerUtil.mutListener.listen(60514)) {
            context.startService(intent);
        }
    }

    /**
     *  Start a call. If necessary, fetch the feature mask of the specified contact.
     *
     *  @param activity The activity that triggered this call.
     *  @param contactModel The contact to call
     *  @param onFinishRunnable
     *  @return true if the call could be initiated, false otherwise
     */
    public static boolean initiateCall(@NonNull final AppCompatActivity activity, @NonNull final ContactModel contactModel, boolean launchVideo, @Nullable Runnable onFinishRunnable) {
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(60515)) {
            if (serviceManager == null) {
                return false;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(60517)) {
                if (!ConfigUtils.isCallsEnabled(activity, serviceManager.getPreferenceService(), serviceManager.getLicenseService())) {
                    return false;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(60516)) {
                logger.error("Exception", e);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(60519)) {
            if (serviceManager.getBlackListService().has(contactModel.getIdentity())) {
                if (!ListenerUtil.mutListener.listen(60518)) {
                    Toast.makeText(activity, R.string.blocked_cannot_send, Toast.LENGTH_LONG).show();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(60521)) {
            // Check for internet connection
            if (!serviceManager.getDeviceService().isOnline()) {
                if (!ListenerUtil.mutListener.listen(60520)) {
                    SimpleStringAlertDialog.newInstance(R.string.internet_connection_required, R.string.connection_error).show(activity.getSupportFragmentManager(), "err");
                }
                return false;
            }
        }
        VoipStateService voipStateService = null;
        try {
            if (!ListenerUtil.mutListener.listen(60522)) {
                voipStateService = serviceManager.getVoipStateService();
            }
        } catch (ThreemaException e) {
            return false;
        }
        if (!ListenerUtil.mutListener.listen(60524)) {
            if (!voipStateService.getCallState().isIdle()) {
                if (!ListenerUtil.mutListener.listen(60523)) {
                    SimpleStringAlertDialog.newInstance(R.string.threema_call, R.string.voip_another_call).show(activity.getSupportFragmentManager(), "err");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(60526)) {
            if (isPSTNCallOngoing(activity)) {
                if (!ListenerUtil.mutListener.listen(60525)) {
                    SimpleStringAlertDialog.newInstance(R.string.threema_call, R.string.voip_another_pstn_call).show(activity.getSupportFragmentManager(), "err");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(60539)) {
            if ((ListenerUtil.mutListener.listen(60528) ? (!ThreemaFeature.canVoip(contactModel.getFeatureMask()) && ((ListenerUtil.mutListener.listen(60527) ? (ConfigUtils.isVideoCallsEnabled() || !ThreemaFeature.canVideocall(contactModel.getFeatureMask())) : (ConfigUtils.isVideoCallsEnabled() && !ThreemaFeature.canVideocall(contactModel.getFeatureMask()))))) : (!ThreemaFeature.canVoip(contactModel.getFeatureMask()) || ((ListenerUtil.mutListener.listen(60527) ? (ConfigUtils.isVideoCallsEnabled() || !ThreemaFeature.canVideocall(contactModel.getFeatureMask())) : (ConfigUtils.isVideoCallsEnabled() && !ThreemaFeature.canVideocall(contactModel.getFeatureMask()))))))) {
                if (!ListenerUtil.mutListener.listen(60538)) {
                    // Start fetching routine in a separate thread
                    new AsyncTask<Void, Void, Exception>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(60530)) {
                                // Show a loading
                                GenericProgressDialog.newInstance(R.string.please_wait, R.string.voip_checking_compatibility).show(activity.getSupportFragmentManager(), DIALOG_TAG_FETCHING_FEATURE_MASK);
                            }
                        }

                        @Override
                        protected Exception doInBackground(Void... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(60531)) {
                                    // Reset the cache (only for Beta?)
                                    UpdateFeatureLevelRoutine.removeTimeCache(contactModel);
                                }
                                if (!ListenerUtil.mutListener.listen(60532)) {
                                    (new UpdateFeatureLevelRoutine(serviceManager.getContactService(), // Bad code
                                    serviceManager.getAPIConnector(), Collections.singletonList(contactModel))).run();
                                }
                            } catch (Exception e) {
                                return e;
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Exception exception) {
                            if (!ListenerUtil.mutListener.listen(60537)) {
                                if (!activity.isDestroyed()) {
                                    if (!ListenerUtil.mutListener.listen(60533)) {
                                        DialogUtil.dismissDialog(activity.getSupportFragmentManager(), DIALOG_TAG_FETCHING_FEATURE_MASK, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(60534)) {
                                        phoneAction(activity, activity.getSupportFragmentManager(), contactModel, onFinishRunnable != null, launchVideo);
                                    }
                                    if (!ListenerUtil.mutListener.listen(60536)) {
                                        if (onFinishRunnable != null) {
                                            if (!ListenerUtil.mutListener.listen(60535)) {
                                                onFinishRunnable.run();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(60529)) {
                    phoneAction(activity, activity.getSupportFragmentManager(), contactModel, onFinishRunnable != null, launchVideo);
                }
            }
        }
        return true;
    }

    /**
     *  Start the call activity, but do not fetch the feature mask.
     */
    private static void phoneAction(final AppCompatActivity activity, final FragmentManager fragmentManager, final ContactModel contactModel, boolean useToast, boolean launchVideo) {
        if (!ListenerUtil.mutListener.listen(60551)) {
            if ((ListenerUtil.mutListener.listen(60540) ? (!ThreemaFeature.canVoip(contactModel.getFeatureMask()) || !RuntimeUtil.isInTest()) : (!ThreemaFeature.canVoip(contactModel.getFeatureMask()) && !RuntimeUtil.isInTest()))) {
                if (!ListenerUtil.mutListener.listen(60550)) {
                    if (useToast) {
                        if (!ListenerUtil.mutListener.listen(60549)) {
                            Toast.makeText(ThreemaApplication.getAppContext(), R.string.voip_incompatible, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(60548)) {
                            SimpleStringAlertDialog.newInstance(R.string.threema_call, R.string.voip_incompatible).show(fragmentManager, "tc");
                        }
                    }
                }
            } else {
                final Intent callActivityIntent = new Intent(activity, CallActivity.class);
                if (!ListenerUtil.mutListener.listen(60541)) {
                    callActivityIntent.putExtra(VoipCallService.EXTRA_ACTIVITY_MODE, CallActivity.MODE_OUTGOING_CALL);
                }
                if (!ListenerUtil.mutListener.listen(60542)) {
                    callActivityIntent.putExtra(VoipCallService.EXTRA_CONTACT_IDENTITY, contactModel.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(60543)) {
                    callActivityIntent.putExtra(VoipCallService.EXTRA_IS_INITIATOR, true);
                }
                if (!ListenerUtil.mutListener.listen(60544)) {
                    callActivityIntent.putExtra(VoipCallService.EXTRA_LAUNCH_VIDEO, launchVideo);
                }
                if (!ListenerUtil.mutListener.listen(60545)) {
                    callActivityIntent.putExtra(VoipCallService.EXTRA_CALL_ID, -1L);
                }
                if (!ListenerUtil.mutListener.listen(60546)) {
                    activity.startActivity(callActivityIntent);
                }
                if (!ListenerUtil.mutListener.listen(60547)) {
                    activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                }
            }
        }
    }

    public static boolean isPSTNCallOngoing(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((ListenerUtil.mutListener.listen(60552) ? (telephonyManager != null || telephonyManager.getCallState() != TelephonyManager.CALL_STATE_IDLE) : (telephonyManager != null && telephonyManager.getCallState() != TelephonyManager.CALL_STATE_IDLE)));
    }

    /**
     *  If the logger is a {@link ch.threema.logging.ThreemaLogger}, set the appropriate
     *  call ID logging prefix.
     *
     *  If the logger is null, or if it's not a {@link ch.threema.logging.ThreemaLogger}, do nothing.
     */
    public static void setLoggerPrefix(@Nullable Logger logger, long callId) {
        if (!ListenerUtil.mutListener.listen(60553)) {
            if (logger == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(60555)) {
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(60554)) {
                    ((ThreemaLogger) logger).setPrefix("[cid=" + callId + "]");
                }
            }
        }
    }
}
