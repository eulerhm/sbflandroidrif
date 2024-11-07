package org.wordpress.android.ui.publicize.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.models.PublicizeConnectionList;
import org.wordpress.android.models.PublicizeServiceList;
import org.wordpress.android.ui.publicize.PublicizeEvents;
import org.wordpress.android.util.AppLog;
import java.util.Locale;
import static org.wordpress.android.JobServiceId.JOB_PUBLICIZE_UPDATE_SERVICE_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeUpdateService extends JobIntentService {

    private static boolean mHasUpdatedServices;

    /*
     * update the publicize connections for the passed site
     */
    public static void updateConnectionsForSite(Context context, @NonNull SiteModel site) {
        Intent intent = new Intent(context, PublicizeUpdateService.class);
        if (!ListenerUtil.mutListener.listen(17188)) {
            intent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(17189)) {
            enqueueWork(context, intent);
        }
    }

    public static void enqueueWork(Context context, Intent work) {
        if (!ListenerUtil.mutListener.listen(17190)) {
            enqueueWork(context, PublicizeUpdateService.class, JOB_PUBLICIZE_UPDATE_SERVICE_ID, work);
        }
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(17191)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(17192)) {
            AppLog.i(AppLog.T.SHARING, "publicize service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(17193)) {
            AppLog.i(AppLog.T.SHARING, "publicize service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(17194)) {
            super.onDestroy();
        }
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (!ListenerUtil.mutListener.listen(17195)) {
            if (intent == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17200)) {
            // since it rarely changes
            if ((ListenerUtil.mutListener.listen(17196) ? (!mHasUpdatedServices && PublicizeTable.getNumServices() == 0) : (!mHasUpdatedServices || PublicizeTable.getNumServices() == 0))) {
                if (!ListenerUtil.mutListener.listen(17197)) {
                    updateServices();
                }
                if (!ListenerUtil.mutListener.listen(17198)) {
                    AppLog.d(AppLog.T.SHARING, "publicize service > updating services");
                }
                if (!ListenerUtil.mutListener.listen(17199)) {
                    mHasUpdatedServices = true;
                }
            }
        }
        SiteModel site = (SiteModel) intent.getSerializableExtra(WordPress.SITE);
        if (!ListenerUtil.mutListener.listen(17201)) {
            updateConnections(site.getSiteId());
        }
    }

    @Override
    public boolean onStopCurrentWork() {
        // in the case something failed.
        return false;
    }

    /*
     * update the list of publicize services
     */
    private void updateServices() {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                PublicizeServiceList serverList = PublicizeServiceList.fromJson(jsonObject);
                PublicizeServiceList localList = PublicizeTable.getServiceList();
                if (!ListenerUtil.mutListener.listen(17204)) {
                    if (!serverList.isSameAs(localList)) {
                        if (!ListenerUtil.mutListener.listen(17202)) {
                            PublicizeTable.setServiceList(serverList);
                        }
                        if (!ListenerUtil.mutListener.listen(17203)) {
                            EventBus.getDefault().post(new PublicizeEvents.ConnectionsChanged());
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17205)) {
                    AppLog.e(AppLog.T.SHARING, volleyError);
                }
            }
        };
        String path = "/meta/external-services?type=publicize";
        if (!ListenerUtil.mutListener.listen(17206)) {
            WordPress.getRestClientUtilsV1_1().get(path, null, null, listener, errorListener);
        }
    }

    /*
     * update the connections for the passed blog
     */
    private void updateConnections(final long siteId) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                PublicizeConnectionList serverList = PublicizeConnectionList.fromJson(jsonObject);
                PublicizeConnectionList localList = PublicizeTable.getConnectionsForSite(siteId);
                if (!ListenerUtil.mutListener.listen(17209)) {
                    if (!serverList.isSameAs(localList)) {
                        if (!ListenerUtil.mutListener.listen(17207)) {
                            PublicizeTable.setConnectionsForSite(siteId, serverList);
                        }
                        if (!ListenerUtil.mutListener.listen(17208)) {
                            EventBus.getDefault().post(new PublicizeEvents.ConnectionsChanged());
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17210)) {
                    AppLog.e(AppLog.T.SHARING, volleyError);
                }
            }
        };
        String path = String.format(Locale.ROOT, "sites/%d/publicize-connections", siteId);
        if (!ListenerUtil.mutListener.listen(17211)) {
            WordPress.getRestClientUtilsV1_1().get(path, null, null, listener, errorListener);
        }
    }
}
