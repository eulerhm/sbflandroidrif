package org.owntracks.android.support;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.BatteryManager;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import org.owntracks.android.model.BatteryStatus;
import org.owntracks.android.model.messages.MessageLocation;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u000e8F\u00a2\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/owntracks/android/support/DeviceMetricsProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "batteryLevel", "", "getBatteryLevel", "()I", "batteryStatus", "Lorg/owntracks/android/model/BatteryStatus;", "getBatteryStatus", "()Lorg/owntracks/android/model/BatteryStatus;", "connectionType", "", "getConnectionType$annotations", "()V", "getConnectionType", "()Ljava/lang/String;", "app_debug"})
@javax.inject.Singleton()
public final class DeviceMetricsProvider {
    private final android.content.Context context = null;
    
    public final int getBatteryLevel() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.model.BatteryStatus getBatteryStatus() {
        return null;
    }
    
    @kotlin.Suppress(names = {"DEPRECATION"})
    @java.lang.Deprecated()
    public static void getConnectionType$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConnectionType() {
        return null;
    }
    
    @javax.inject.Inject()
    public DeviceMetricsProvider(@org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context context) {
        super();
    }
}