package org.wordpress.android.util.analytics.service;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.util.HashMap;
import java.util.Map;
import static org.wordpress.android.util.analytics.service.InstallationReferrerServiceStarter.ARG_REFERRER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InstallationReferrerServiceLogic {

    private ServiceCompletionListener mCompletionListener;

    private Object mListenerCompanion;

    private InstallReferrerClient mReferrerClient;

    private Context mContext;

    public InstallationReferrerServiceLogic(Context context, ServiceCompletionListener completionListener) {
        if (!ListenerUtil.mutListener.listen(27117)) {
            mCompletionListener = completionListener;
        }
        if (!ListenerUtil.mutListener.listen(27118)) {
            mContext = context;
        }
    }

    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(27119)) {
            AppLog.i(T.UTILS, "installation referrer service destroyed");
        }
    }

    public void performTask(Bundle extras, Object companion) {
        if (!ListenerUtil.mutListener.listen(27120)) {
            mListenerCompanion = companion;
        }
        if (!ListenerUtil.mutListener.listen(27126)) {
            // just send it to tracks
            if ((ListenerUtil.mutListener.listen(27121) ? (extras != null || extras.containsKey(ARG_REFERRER)) : (extras != null && extras.containsKey(ARG_REFERRER)))) {
                Map<String, Object> properties = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(27122)) {
                    properties.put("install_referrer", extras.getString(ARG_REFERRER));
                }
                if (!ListenerUtil.mutListener.listen(27123)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.INSTALLATION_REFERRER_OBTAINED, properties);
                }
                if (!ListenerUtil.mutListener.listen(27124)) {
                    // mark referrer as obtained now
                    AppPrefs.setInstallationReferrerObtained(true);
                }
                if (!ListenerUtil.mutListener.listen(27125)) {
                    stopService();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27127)) {
            // if not, try to obtain it from Play Store app connection through the Install Referrer API Library if possible
            mReferrerClient = InstallReferrerClient.newBuilder(mContext).build();
        }
        final InstallReferrerStateListener installReferrerStateListener = new InstallReferrerStateListener() {

            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                if (!ListenerUtil.mutListener.listen(27140)) {
                    switch(responseCode) {
                        case InstallReferrerResponse.OK:
                            // Connection established
                            try {
                                if (!ListenerUtil.mutListener.listen(27130)) {
                                    AppLog.i(T.UTILS, "installation referrer connected");
                                }
                                ReferrerDetails response = mReferrerClient.getInstallReferrer();
                                if (!ListenerUtil.mutListener.listen(27131)) {
                                    // read more: https://developer.android.com/google/play/installreferrer/library
                                    AppPrefs.setInstallationReferrerObtained(true);
                                }
                                // handle and send information to Tracks here
                                Map<String, Object> properties = new HashMap<>();
                                if (!ListenerUtil.mutListener.listen(27132)) {
                                    properties.put("install_referrer", response.getInstallReferrer());
                                }
                                if (!ListenerUtil.mutListener.listen(27133)) {
                                    properties.put("install_referrer_timestamp_begin", response.getInstallBeginTimestampSeconds());
                                }
                                if (!ListenerUtil.mutListener.listen(27134)) {
                                    properties.put("install_referrer_timestamp_click", response.getReferrerClickTimestampSeconds());
                                }
                                if (!ListenerUtil.mutListener.listen(27135)) {
                                    AnalyticsTracker.track(AnalyticsTracker.Stat.INSTALLATION_REFERRER_OBTAINED, properties);
                                }
                                if (!ListenerUtil.mutListener.listen(27136)) {
                                    mReferrerClient.endConnection();
                                }
                            } catch (RemoteException | IllegalStateException e) {
                                if (!ListenerUtil.mutListener.listen(27128)) {
                                    e.printStackTrace();
                                }
                                if (!ListenerUtil.mutListener.listen(27129)) {
                                    AppLog.e(T.UTILS, "installation referrer: " + e.getClass().getSimpleName() + " occurred", e);
                                }
                            }
                            break;
                        case InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                            if (!ListenerUtil.mutListener.listen(27137)) {
                                // and we can obtain it also from the old com.android.vending.INSTALL_REFERRER intent
                                AppLog.i(T.UTILS, "installation referrer: feature not supported");
                            }
                            break;
                        case InstallReferrerResponse.SERVICE_UNAVAILABLE:
                            if (!ListenerUtil.mutListener.listen(27138)) {
                                // if this is retried but the error persists
                                AppLog.i(T.UTILS, "installation referrer: service unavailable");
                            }
                            break;
                        case InstallReferrerResponse.PERMISSION_ERROR:
                            if (!ListenerUtil.mutListener.listen(27139)) {
                                // response. For now we will ignore it and log for informational purposes.
                                AppLog.i(T.UTILS, "installation referrer: app is not allowed to bind to the Service");
                            }
                            break;
                        case InstallReferrerResponse.DEVELOPER_ERROR:
                            break;
                        case InstallReferrerResponse.SERVICE_DISCONNECTED:
                            break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(27141)) {
                    stopService();
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                if (!ListenerUtil.mutListener.listen(27142)) {
                    // Google Play by calling the startConnection() method.
                    stopService();
                }
            }
        };
        try {
            if (!ListenerUtil.mutListener.listen(27146)) {
                mReferrerClient.startConnection(installReferrerStateListener);
            }
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(27143)) {
                AppLog.e(T.UTILS, "installation referrer start connection failed!", e);
            }
            if (!ListenerUtil.mutListener.listen(27144)) {
                AnalyticsTracker.track(Stat.INSTALLATION_REFERRER_FAILED);
            }
            if (!ListenerUtil.mutListener.listen(27145)) {
                // just bail if we were not able to connect to the installation referrer service
                stopService();
            }
        }
    }

    private void stopService() {
        if (!ListenerUtil.mutListener.listen(27147)) {
            mCompletionListener.onCompleted(mListenerCompanion);
        }
    }

    interface ServiceCompletionListener {

        void onCompleted(Object companion);
    }
}
