package org.wordpress.android.ui.publicize;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.ui.publicize.PublicizeConstants.ConnectAction;
import org.wordpress.android.ui.publicize.PublicizeEvents.ActionCompleted;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.JSONUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * API calls to connect/disconnect publicize services
 */
public class PublicizeActions {

    public interface OnPublicizeActionListener {

        void onRequestConnect(PublicizeService service);

        void onRequestDisconnect(PublicizeConnection connection);

        void onRequestReconnect(PublicizeService service, PublicizeConnection connection);
    }

    private static class PublicizeConnectionValidationException extends Exception {

        private final int mReasonResId;

        PublicizeConnectionValidationException(int reasonResId) {
            mReasonResId = reasonResId;
        }
    }

    /*
     * disconnect a currently connected publicize service
     */
    public static void disconnect(@NonNull final PublicizeConnection connection) {
        String path = String.format(Locale.ROOT, "sites/%d/publicize-connections/%d/delete", connection.siteId, connection.connectionId);
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(17295)) {
                    AppLog.d(AppLog.T.SHARING, "disconnect succeeded");
                }
                if (!ListenerUtil.mutListener.listen(17296)) {
                    EventBus.getDefault().post(new ActionCompleted(true, ConnectAction.DISCONNECT, connection.getService()));
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17297)) {
                    AppLog.e(AppLog.T.SHARING, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17298)) {
                    PublicizeTable.addOrUpdateConnection(connection);
                }
                if (!ListenerUtil.mutListener.listen(17299)) {
                    EventBus.getDefault().post(new ActionCompleted(false, ConnectAction.DISCONNECT, connection.getService()));
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(17300)) {
            // delete connection immediately - will be restored upon failure
            PublicizeTable.deleteConnection(connection.connectionId);
        }
        if (!ListenerUtil.mutListener.listen(17301)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    public static void reconnect(@NonNull final PublicizeConnection connection) {
        String path = String.format(Locale.ROOT, "sites/%d/publicize-connections/%d/delete", connection.siteId, connection.connectionId);
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(17302)) {
                    AppLog.d(AppLog.T.SHARING, "disconnect succeeded");
                }
                if (!ListenerUtil.mutListener.listen(17303)) {
                    EventBus.getDefault().post(new ActionCompleted(true, ConnectAction.RECONNECT, connection.getService()));
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17304)) {
                    AppLog.e(AppLog.T.SHARING, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17305)) {
                    PublicizeTable.addOrUpdateConnection(connection);
                }
                if (!ListenerUtil.mutListener.listen(17306)) {
                    EventBus.getDefault().post(new ActionCompleted(false, ConnectAction.RECONNECT, connection.getService()));
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(17307)) {
            // delete connection immediately - will be restored upon failure
            PublicizeTable.deleteConnection(connection.connectionId);
        }
        if (!ListenerUtil.mutListener.listen(17308)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    /*
     * create a new publicize service connection for a specific site
     */
    public static void connect(long siteId, String serviceId, long currentUserId) {
        if (!ListenerUtil.mutListener.listen(17311)) {
            if (TextUtils.isEmpty(serviceId)) {
                if (!ListenerUtil.mutListener.listen(17309)) {
                    AppLog.w(AppLog.T.SHARING, "cannot connect without service");
                }
                if (!ListenerUtil.mutListener.listen(17310)) {
                    EventBus.getDefault().post(new ActionCompleted(false, ConnectAction.CONNECT, serviceId));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17312)) {
            connectStepOne(siteId, serviceId, currentUserId);
        }
    }

    /*
     * step one in creating a publicize connection: request the list of keyring connections
     * and find the one for the passed service
     */
    private static void connectStepOne(final long siteId, final String serviceId, final long currentUserId) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                final boolean showChooserDialog;
                try {
                    showChooserDialog = shouldShowChooserDialog(siteId, serviceId, jsonObject);
                } catch (PublicizeConnectionValidationException e) {
                    final ActionCompleted event = new ActionCompleted(false, ConnectAction.CONNECT, serviceId, e.mReasonResId);
                    if (!ListenerUtil.mutListener.listen(17313)) {
                        EventBus.getDefault().post(event);
                    }
                    return;
                }
                if (!ListenerUtil.mutListener.listen(17316)) {
                    if (showChooserDialog) {
                        if (!ListenerUtil.mutListener.listen(17315)) {
                            // show dialog showing multiple options
                            EventBus.getDefault().post(new PublicizeEvents.ActionRequestChooseAccount(siteId, serviceId, jsonObject));
                        }
                    } else {
                        long keyringConnectionId = parseServiceKeyringId(serviceId, currentUserId, jsonObject);
                        if (!ListenerUtil.mutListener.listen(17314)) {
                            connectStepTwo(siteId, keyringConnectionId, serviceId, "");
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17317)) {
                    AppLog.e(AppLog.T.SHARING, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17318)) {
                    EventBus.getDefault().post(new ActionCompleted(false, ConnectAction.CONNECT, serviceId));
                }
            }
        };
        String path = "/me/keyring-connections";
        if (!ListenerUtil.mutListener.listen(17319)) {
            WordPress.getRestClientUtilsV1_1().get(path, listener, errorListener);
        }
    }

    /*
     * step two in creating a publicize connection: now that we have the keyring connection id,
     * create the actual connection
     */
    public static void connectStepTwo(final long siteId, long keyringConnectionId, final String serviceId, final String externalUserId) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(17320)) {
                    AppLog.d(AppLog.T.SHARING, "connect succeeded");
                }
                PublicizeConnection connection = PublicizeConnection.fromJson(jsonObject);
                if (!ListenerUtil.mutListener.listen(17321)) {
                    PublicizeTable.addOrUpdateConnection(connection);
                }
                if (!ListenerUtil.mutListener.listen(17322)) {
                    EventBus.getDefault().post(new ActionCompleted(true, ConnectAction.CONNECT, serviceId));
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17323)) {
                    AppLog.e(AppLog.T.SHARING, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17324)) {
                    EventBus.getDefault().post(new ActionCompleted(false, ConnectAction.CONNECT, serviceId));
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(17325)) {
            params.put("keyring_connection_ID", Long.toString(keyringConnectionId));
        }
        if (!ListenerUtil.mutListener.listen(17328)) {
            // Sending the external id for Twitter and LinkedIn connections result in an error
            if ((ListenerUtil.mutListener.listen(17326) ? (!TextUtils.isEmpty(externalUserId) || PublicizeConstants.FACEBOOK_ID.equals(serviceId)) : (!TextUtils.isEmpty(externalUserId) && PublicizeConstants.FACEBOOK_ID.equals(serviceId)))) {
                if (!ListenerUtil.mutListener.listen(17327)) {
                    params.put("external_user_ID", externalUserId);
                }
            }
        }
        String path = String.format(Locale.ROOT, "/sites/%d/publicize-connections/new", siteId);
        if (!ListenerUtil.mutListener.listen(17329)) {
            WordPress.getRestClientUtilsV1_1().post(path, params, null, listener, errorListener);
        }
    }

    private static boolean shouldShowChooserDialog(long siteId, String serviceId, JSONObject jsonObject) throws PublicizeConnectionValidationException {
        JSONArray jsonConnectionList = jsonObject.optJSONArray("connections");
        if ((ListenerUtil.mutListener.listen(17335) ? (jsonConnectionList == null && (ListenerUtil.mutListener.listen(17334) ? (jsonConnectionList.length() >= 0) : (ListenerUtil.mutListener.listen(17333) ? (jsonConnectionList.length() > 0) : (ListenerUtil.mutListener.listen(17332) ? (jsonConnectionList.length() < 0) : (ListenerUtil.mutListener.listen(17331) ? (jsonConnectionList.length() != 0) : (ListenerUtil.mutListener.listen(17330) ? (jsonConnectionList.length() == 0) : (jsonConnectionList.length() <= 0))))))) : (jsonConnectionList == null || (ListenerUtil.mutListener.listen(17334) ? (jsonConnectionList.length() >= 0) : (ListenerUtil.mutListener.listen(17333) ? (jsonConnectionList.length() > 0) : (ListenerUtil.mutListener.listen(17332) ? (jsonConnectionList.length() < 0) : (ListenerUtil.mutListener.listen(17331) ? (jsonConnectionList.length() != 0) : (ListenerUtil.mutListener.listen(17330) ? (jsonConnectionList.length() == 0) : (jsonConnectionList.length() <= 0))))))))) {
            return false;
        }
        int totalAccounts = 0;
        int totalExternalAccounts = 0;
        try {
            if (!ListenerUtil.mutListener.listen(17351)) {
                {
                    long _loopCounter286 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17350) ? (i >= jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17349) ? (i <= jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17348) ? (i > jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17347) ? (i != jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17346) ? (i == jsonConnectionList.length()) : (i < jsonConnectionList.length())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter286", ++_loopCounter286);
                        JSONObject connectionObject = jsonConnectionList.getJSONObject(i);
                        PublicizeConnection publicizeConnection = PublicizeConnection.fromJson(connectionObject);
                        if (!ListenerUtil.mutListener.listen(17345)) {
                            if ((ListenerUtil.mutListener.listen(17336) ? (publicizeConnection.getService().equals(serviceId) || !publicizeConnection.isInSite(siteId)) : (publicizeConnection.getService().equals(serviceId) && !publicizeConnection.isInSite(siteId)))) {
                                if (!ListenerUtil.mutListener.listen(17337)) {
                                    totalAccounts++;
                                }
                                JSONArray externalJsonArray = connectionObject.getJSONArray("additional_external_users");
                                if (!ListenerUtil.mutListener.listen(17344)) {
                                    {
                                        long _loopCounter285 = 0;
                                        for (int j = 0; (ListenerUtil.mutListener.listen(17343) ? (j >= externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17342) ? (j <= externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17341) ? (j > externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17340) ? (j != externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17339) ? (j == externalJsonArray.length()) : (j < externalJsonArray.length())))))); j++) {
                                            ListenerUtil.loopListener.listen("_loopCounter285", ++_loopCounter285);
                                            if (!ListenerUtil.mutListener.listen(17338)) {
                                                totalExternalAccounts++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            final boolean hasExternalAccounts = (ListenerUtil.mutListener.listen(17356) ? (totalExternalAccounts >= 0) : (ListenerUtil.mutListener.listen(17355) ? (totalExternalAccounts <= 0) : (ListenerUtil.mutListener.listen(17354) ? (totalExternalAccounts < 0) : (ListenerUtil.mutListener.listen(17353) ? (totalExternalAccounts != 0) : (ListenerUtil.mutListener.listen(17352) ? (totalExternalAccounts == 0) : (totalExternalAccounts > 0))))));
            if (PublicizeTable.onlyExternalConnections(serviceId)) {
                if ((ListenerUtil.mutListener.listen(17363) ? (!hasExternalAccounts || serviceId.equals(PublicizeService.FACEBOOK_SERVICE_ID)) : (!hasExternalAccounts && serviceId.equals(PublicizeService.FACEBOOK_SERVICE_ID)))) {
                    if (!ListenerUtil.mutListener.listen(17364)) {
                        AppLog.i(T.SHARING, "The Facebook account cannot be linked because either there was no Page selected or the " + "Page is set as not published.");
                    }
                    throw new PublicizeConnectionValidationException(R.string.sharing_facebook_account_must_have_pages);
                } else {
                    return hasExternalAccounts;
                }
            } else {
                return (ListenerUtil.mutListener.listen(17362) ? ((ListenerUtil.mutListener.listen(17361) ? (totalAccounts >= 0) : (ListenerUtil.mutListener.listen(17360) ? (totalAccounts <= 0) : (ListenerUtil.mutListener.listen(17359) ? (totalAccounts < 0) : (ListenerUtil.mutListener.listen(17358) ? (totalAccounts != 0) : (ListenerUtil.mutListener.listen(17357) ? (totalAccounts == 0) : (totalAccounts > 0)))))) && hasExternalAccounts) : ((ListenerUtil.mutListener.listen(17361) ? (totalAccounts >= 0) : (ListenerUtil.mutListener.listen(17360) ? (totalAccounts <= 0) : (ListenerUtil.mutListener.listen(17359) ? (totalAccounts < 0) : (ListenerUtil.mutListener.listen(17358) ? (totalAccounts != 0) : (ListenerUtil.mutListener.listen(17357) ? (totalAccounts == 0) : (totalAccounts > 0)))))) || hasExternalAccounts));
            }
        } catch (JSONException e) {
            return false;
        }
    }

    /*
     * extract the keyring connection for the passed service from the response
     * to /me/keyring-connections
     */
    private static long parseServiceKeyringId(String serviceId, long currentUserId, JSONObject json) {
        JSONArray jsonConnectionList = json.optJSONArray("connections");
        if (!ListenerUtil.mutListener.listen(17365)) {
            if (jsonConnectionList == null) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(17384)) {
            {
                long _loopCounter287 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17383) ? (i >= jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17382) ? (i <= jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17381) ? (i > jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17380) ? (i != jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(17379) ? (i == jsonConnectionList.length()) : (i < jsonConnectionList.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter287", ++_loopCounter287);
                    JSONObject jsonConnection = jsonConnectionList.optJSONObject(i);
                    String service = JSONUtils.getString(jsonConnection, "service");
                    if (!ListenerUtil.mutListener.listen(17378)) {
                        if (serviceId.equals(service)) {
                            // make sure userId matches the current user, or is zero (shared)
                            long userId = jsonConnection.optLong("user_ID");
                            if (!ListenerUtil.mutListener.listen(17377)) {
                                if ((ListenerUtil.mutListener.listen(17376) ? ((ListenerUtil.mutListener.listen(17370) ? (userId >= 0) : (ListenerUtil.mutListener.listen(17369) ? (userId <= 0) : (ListenerUtil.mutListener.listen(17368) ? (userId > 0) : (ListenerUtil.mutListener.listen(17367) ? (userId < 0) : (ListenerUtil.mutListener.listen(17366) ? (userId != 0) : (userId == 0)))))) && (ListenerUtil.mutListener.listen(17375) ? (userId >= currentUserId) : (ListenerUtil.mutListener.listen(17374) ? (userId <= currentUserId) : (ListenerUtil.mutListener.listen(17373) ? (userId > currentUserId) : (ListenerUtil.mutListener.listen(17372) ? (userId < currentUserId) : (ListenerUtil.mutListener.listen(17371) ? (userId != currentUserId) : (userId == currentUserId))))))) : ((ListenerUtil.mutListener.listen(17370) ? (userId >= 0) : (ListenerUtil.mutListener.listen(17369) ? (userId <= 0) : (ListenerUtil.mutListener.listen(17368) ? (userId > 0) : (ListenerUtil.mutListener.listen(17367) ? (userId < 0) : (ListenerUtil.mutListener.listen(17366) ? (userId != 0) : (userId == 0)))))) || (ListenerUtil.mutListener.listen(17375) ? (userId >= currentUserId) : (ListenerUtil.mutListener.listen(17374) ? (userId <= currentUserId) : (ListenerUtil.mutListener.listen(17373) ? (userId > currentUserId) : (ListenerUtil.mutListener.listen(17372) ? (userId < currentUserId) : (ListenerUtil.mutListener.listen(17371) ? (userId != currentUserId) : (userId == currentUserId))))))))) {
                                    return jsonConnection.optLong("ID");
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
