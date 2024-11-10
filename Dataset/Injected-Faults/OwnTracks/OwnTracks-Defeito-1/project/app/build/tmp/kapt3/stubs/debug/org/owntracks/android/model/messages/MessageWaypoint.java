package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.owntracks.android.support.Preferences;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020&H\u0016R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048TX\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0006\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u00a8\u0006("}, d2 = {"Lorg/owntracks/android/model/messages/MessageWaypoint;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "baseTopicSuffix", "", "getBaseTopicSuffix", "()Ljava/lang/String;", "description", "getDescription", "setDescription", "(Ljava/lang/String;)V", "latitude", "", "getLatitude", "()D", "setLatitude", "(D)V", "longitude", "getLongitude", "setLongitude", "radius", "", "getRadius", "()Ljava/lang/Integer;", "setRadius", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "timestamp", "", "getTimestamp", "()J", "setTimestamp", "(J)V", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "isValidMessage", "", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public final class MessageWaypoint extends org.owntracks.android.model.messages.MessageBase {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "desc")
    private java.lang.String description;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lon")
    private double longitude = 0.0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lat")
    private double latitude = 0.0;
    @com.fasterxml.jackson.annotation.JsonProperty(value = "tst")
    private long timestamp = 0L;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "rad")
    private java.lang.Integer radius;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "waypoint";
    private static final java.lang.String BASETOPIC_SUFFIX = "/event";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageWaypoint.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final void setDescription(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
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
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final void setTimestamp(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getRadius() {
        return null;
    }
    
    public final void setRadius(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @java.lang.Override()
    public boolean isValidMessage() {
        return false;
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
    
    public MessageWaypoint() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/model/messages/MessageWaypoint$Companion;", "", "()V", "BASETOPIC_SUFFIX", "", "TYPE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}