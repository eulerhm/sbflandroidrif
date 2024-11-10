package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.*;
import com.google.android.gms.location.Geofence;
import org.owntracks.android.support.Preferences;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 42\u00020\u0001:\u00014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u000201H\u0007J\u000e\u00102\u001a\u00020-2\u0006\u00103\u001a\u000201R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\n8TX\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u0010R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u00020\u00158\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010#\u001a\u0004\u0018\u00010\n8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010\u0010R \u0010&\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\f\"\u0004\b(\u0010\u0010R\u001e\u0010)\u001a\u00020\u001e8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010 \"\u0004\b+\u0010\"\u00a8\u00065"}, d2 = {"Lorg/owntracks/android/model/messages/MessageTransition;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "accuracy", "", "getAccuracy", "()F", "setAccuracy", "(F)V", "baseTopicSuffix", "", "getBaseTopicSuffix", "()Ljava/lang/String;", "description", "getDescription", "setDescription", "(Ljava/lang/String;)V", "event", "getEvent", "setEvent", "latitude", "", "getLatitude", "()D", "setLatitude", "(D)V", "longitude", "getLongitude", "setLongitude", "timestamp", "", "getTimestamp", "()J", "setTimestamp", "(J)V", "trackerId", "getTrackerId", "setTrackerId", "trigger", "getTrigger", "setTrigger", "waypointTimestamp", "getWaypointTimestamp", "setWaypointTimestamp", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "getTransition", "", "setTransition", "value", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public final class MessageTransition extends org.owntracks.android.model.messages.MessageBase {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "event")
    private java.lang.String event;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "desc")
    private java.lang.String description;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "tid")
    private java.lang.String trackerId;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "t")
    private java.lang.String trigger;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "tst")
    private long timestamp = 0L;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "wtst")
    private long waypointTimestamp = 0L;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "acc")
    private float accuracy = 0.0F;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lon")
    private double longitude = 0.0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lat")
    private double latitude = 0.0;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "transition";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRIGGER_CIRCULAR = "c";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRIGGER_LOCATION = "l";
    private static final java.lang.String BASETOPIC_SUFFIX = "/event";
    private static final java.lang.String EVENT_ENTER = "enter";
    private static final java.lang.String EVENT_LEAVE = "leave";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageTransition.Companion Companion = null;
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final int getTransition() {
        return 0;
    }
    
    public final void setTransition(int value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEvent() {
        return null;
    }
    
    public final void setEvent(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final void setDescription(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getTrackerId() {
        return null;
    }
    
    @java.lang.Override()
    public void setTrackerId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTrigger() {
        return null;
    }
    
    public final void setTrigger(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final void setTimestamp(long p0) {
    }
    
    public final long getWaypointTimestamp() {
        return 0L;
    }
    
    public final void setWaypointTimestamp(long p0) {
    }
    
    public final float getAccuracy() {
        return 0.0F;
    }
    
    public final void setAccuracy(float p0) {
    }
    
    public final double getLongitude() {
        return 0.0;
    }
    
    public final void setLongitude(double p0) {
    }
    
    public final double getLatitude() {
        return 0.0;
    }
    
    public final void setLatitude(double p0) {
    }
    
    @java.lang.Override()
    public void addMqttPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.String getBaseTopicSuffix() {
        return null;
    }
    
    public MessageTransition() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lorg/owntracks/android/model/messages/MessageTransition$Companion;", "", "()V", "BASETOPIC_SUFFIX", "", "EVENT_ENTER", "EVENT_LEAVE", "TRIGGER_CIRCULAR", "TRIGGER_LOCATION", "TYPE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}