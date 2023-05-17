package net.programmierecke.radiodroid2.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.core.net.ConnectivityManagerCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectivityChecker {

    public enum ConnectionType {

        NOT_METERED, METERED
    }

    public interface ConnectivityCallback {

        void onConnectivityChanged(boolean connected, ConnectionType connectionType);
    }

    private ConnectivityManager connectivityManager;

    private ConnectivityManager.NetworkCallback networkCallback;

    private BroadcastReceiver networkBroadcastReceiver;

    private ConnectivityCallback connectivityCallback;

    private ConnectionType lastConnectionType;

    public static ConnectionType getCurrentConnectionType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager) ? ConnectionType.METERED : ConnectionType.NOT_METERED;
    }

    public void startListening(Context context, ConnectivityCallback connectivityCallback) {
        if (!ListenerUtil.mutListener.listen(1675)) {
            this.connectivityCallback = connectivityCallback;
        }
        if (!ListenerUtil.mutListener.listen(1677)) {
            if ((ListenerUtil.mutListener.listen(1676) ? (networkCallback != null && networkBroadcastReceiver != null) : (networkCallback != null || networkBroadcastReceiver != null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1678)) {
            lastConnectionType = getCurrentConnectionType(context);
        }
        if (!ListenerUtil.mutListener.listen(1679)) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(1691)) {
            if ((ListenerUtil.mutListener.listen(1684) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1683) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1682) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1681) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1680) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(1689)) {
                    networkCallback = new ConnectivityManager.NetworkCallback() {

                        @Override
                        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                            boolean connected = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                            boolean metered = !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
                            if (!ListenerUtil.mutListener.listen(1688)) {
                                onConnectivityChanged(connected, metered ? ConnectionType.METERED : ConnectionType.NOT_METERED);
                            }
                        }
                    };
                }
                if (!ListenerUtil.mutListener.listen(1690)) {
                    connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), networkCallback);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1686)) {
                    networkBroadcastReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            boolean connected = !intent.hasExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
                            if (!ListenerUtil.mutListener.listen(1685)) {
                                onConnectivityChanged(connected, ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager) ? ConnectionType.METERED : ConnectionType.NOT_METERED);
                            }
                        }
                    };
                }
                if (!ListenerUtil.mutListener.listen(1687)) {
                    context.registerReceiver(networkBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                }
            }
        }
    }

    public void stopListening(Context context) {
        if (!ListenerUtil.mutListener.listen(1692)) {
            this.connectivityCallback = null;
        }
        if (!ListenerUtil.mutListener.listen(1703)) {
            if ((ListenerUtil.mutListener.listen(1698) ? ((ListenerUtil.mutListener.listen(1697) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1696) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1695) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1694) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1693) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || networkCallback != null) : ((ListenerUtil.mutListener.listen(1697) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1696) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1695) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1694) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1693) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && networkCallback != null))) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (!ListenerUtil.mutListener.listen(1701)) {
                    connectivityManager.unregisterNetworkCallback(networkCallback);
                }
                if (!ListenerUtil.mutListener.listen(1702)) {
                    networkCallback = null;
                }
            } else if (networkBroadcastReceiver != null) {
                if (!ListenerUtil.mutListener.listen(1699)) {
                    context.unregisterReceiver(networkBroadcastReceiver);
                }
                if (!ListenerUtil.mutListener.listen(1700)) {
                    networkBroadcastReceiver = null;
                }
            }
        }
    }

    private void onConnectivityChanged(boolean connected, ConnectionType connectionType) {
        if (!ListenerUtil.mutListener.listen(1705)) {
            if (lastConnectionType == connectionType) {
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(1704)) {
                    lastConnectionType = connectionType;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1707)) {
            if (connectivityCallback != null) {
                if (!ListenerUtil.mutListener.listen(1706)) {
                    connectivityCallback.onConnectivityChanged(connected, connectionType);
                }
            }
        }
    }
}
