package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.*;
import org.owntracks.android.model.BatteryStatus;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.support.Preferences;
import java.lang.ref.WeakReference;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u0000 W2\u00020\u00012\u00020\u0002:\u0001WB\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020QH\u0016J\b\u0010R\u001a\u00020+H\u0016J\b\u0010S\u001a\u00020OH\u0002J\u0010\u0010T\u001a\u00020O2\b\u0010U\u001a\u0004\u0018\u00010\u0007J\b\u0010V\u001a\u00020\u001bH\u0017R\u0018\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001e\u0010\u0011\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR \u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020!X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020\u001b8AX\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u001dR*\u0010\'\u001a\u0004\u0018\u00010\u001b2\b\u0010&\u001a\u0004\u0018\u00010\u001b8G@GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001fR \u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020+8G@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R&\u0010/\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u0001008\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001e\u00105\u001a\u0002068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001e\u0010;\u001a\u0002068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u00108\"\u0004\b=\u0010:R\u001e\u0010>\u001a\u00020!8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010#\"\u0004\b@\u0010AR*\u0010B\u001a\u0004\u0018\u00010\u001b2\b\u0010&\u001a\u0004\u0018\u00010\u001b8\u0016@VX\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001d\"\u0004\bD\u0010\u001fR \u0010E\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001d\"\u0004\bG\u0010\u001fR\u001e\u0010H\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u000b\"\u0004\bJ\u0010\rR\u001e\u0010K\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u000b\"\u0004\bM\u0010\r\u00a8\u0006X"}, d2 = {"Lorg/owntracks/android/model/messages/MessageLocation;", "Lorg/owntracks/android/model/messages/MessageBase;", "Lorg/owntracks/android/model/messages/MessageWithCreatedAt;", "dep", "(Lorg/owntracks/android/model/messages/MessageWithCreatedAt;)V", "_contact", "Ljava/lang/ref/WeakReference;", "Lorg/owntracks/android/model/FusedContact;", "accuracy", "", "getAccuracy", "()I", "setAccuracy", "(I)V", "altitude", "getAltitude", "setAltitude", "battery", "getBattery", "setBattery", "batteryStatus", "Lorg/owntracks/android/model/BatteryStatus;", "getBatteryStatus", "()Lorg/owntracks/android/model/BatteryStatus;", "setBatteryStatus", "(Lorg/owntracks/android/model/BatteryStatus;)V", "conn", "", "getConn", "()Ljava/lang/String;", "setConn", "(Ljava/lang/String;)V", "created_at", "", "getCreated_at", "()J", "fallbackGeocode", "getFallbackGeocode$app_debug", "value", "geocode", "getGeocode", "setGeocode", "<set-?>", "", "hasGeocode", "getHasGeocode", "()Z", "inregions", "", "getInregions", "()Ljava/util/List;", "setInregions", "(Ljava/util/List;)V", "latitude", "", "getLatitude", "()D", "setLatitude", "(D)V", "longitude", "getLongitude", "setLongitude", "timestamp", "getTimestamp", "setTimestamp", "(J)V", "trackerId", "getTrackerId", "setTrackerId", "trigger", "getTrigger", "setTrigger", "velocity", "getVelocity", "setVelocity", "verticalAccuracy", "getVerticalAccuracy", "setVerticalAccuracy", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "isValidMessage", "notifyContactPropertyChanged", "setContact", "contact", "toString", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public class MessageLocation extends org.owntracks.android.model.messages.MessageBase implements org.owntracks.android.model.messages.MessageWithCreatedAt {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "t")
    private java.lang.String trigger;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "batt")
    private int battery = 0;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "bs")
    private org.owntracks.android.model.BatteryStatus batteryStatus;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "acc")
    private int accuracy = 0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "vac")
    private int verticalAccuracy = 0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lat")
    private double latitude = 0.0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lon")
    private double longitude = 0.0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "alt")
    private int altitude = 0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "vel")
    private int velocity = 0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "tst")
    private long timestamp = 0L;
    private java.lang.ref.WeakReference<org.owntracks.android.model.FusedContact> _contact;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String conn;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "inregions")
    private java.util.List<java.lang.String> inregions;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String geocode;
    private boolean hasGeocode = false;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "tid")
    @com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
    private java.lang.String trackerId;
    private final org.owntracks.android.model.messages.MessageWithCreatedAt dep = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "location";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REPORT_TYPE_USER = "u";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REPORT_TYPE_RESPONSE = "r";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REPORT_TYPE_CIRCULAR = "c";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REPORT_TYPE_PING = "p";
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String REPORT_TYPE_DEFAULT = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONN_TYPE_OFFLINE = "o";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONN_TYPE_WIFI = "w";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONN_TYPE_MOBILE = "m";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageLocation.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTrigger() {
        return null;
    }
    
    public final void setTrigger(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final int getBattery() {
        return 0;
    }
    
    public final void setBattery(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.owntracks.android.model.BatteryStatus getBatteryStatus() {
        return null;
    }
    
    public final void setBatteryStatus(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.model.BatteryStatus p0) {
    }
    
    public final int getAccuracy() {
        return 0;
    }
    
    public final void setAccuracy(int p0) {
    }
    
    public final int getVerticalAccuracy() {
        return 0;
    }
    
    public final void setVerticalAccuracy(int p0) {
    }
    
    public final double getLatitude() {
        return 0.0;
    }
    
    public final void setLatitude(double p0) {
    }
    
    public final double getLongitude() {
        return 0.0;
    }
    
    public final void setLongitude(double p0) {
    }
    
    public final int getAltitude() {
        return 0;
    }
    
    public final void setAltitude(int p0) {
    }
    
    public final int getVelocity() {
        return 0;
    }
    
    public final void setVelocity(int p0) {
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final void setTimestamp(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConn() {
        return null;
    }
    
    public final void setConn(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getInregions() {
        return null;
    }
    
    public final void setInregions(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final java.lang.String getGeocode() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final void setGeocode(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final java.lang.String getFallbackGeocode$app_debug() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final boolean getHasGeocode() {
        return false;
    }
    
    public final void setContact(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.model.FusedContact contact) {
    }
    
    private final void notifyContactPropertyChanged() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getTrackerId() {
        return null;
    }
    
    @java.lang.Override()
    public void setTrackerId(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public boolean isValidMessage() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public void addMqttPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
    }
    
    public MessageLocation(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageWithCreatedAt dep) {
        super();
    }
    
    public MessageLocation() {
        super();
    }
    
    @java.lang.Override()
    public long getCreated_at() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/owntracks/android/model/messages/MessageLocation$Companion;", "", "()V", "CONN_TYPE_MOBILE", "", "CONN_TYPE_OFFLINE", "CONN_TYPE_WIFI", "REPORT_TYPE_CIRCULAR", "REPORT_TYPE_DEFAULT", "REPORT_TYPE_PING", "REPORT_TYPE_RESPONSE", "REPORT_TYPE_USER", "TYPE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}