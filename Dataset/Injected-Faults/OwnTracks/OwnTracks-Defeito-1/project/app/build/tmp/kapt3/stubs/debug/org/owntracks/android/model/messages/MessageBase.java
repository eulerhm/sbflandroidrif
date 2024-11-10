package org.owntracks.android.model.messages;

import androidx.databinding.BaseObservable;
import com.fasterxml.jackson.annotation.*;
import org.owntracks.android.support.Parser;
import org.owntracks.android.support.Preferences;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\'\u0018\u0000 52\u00020\u0001:\u00015B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0003J\b\u0010,\u001a\u00020\nH\u0007J\b\u0010-\u001a\u00020\nH\u0017J\b\u0010.\u001a\u00020(H\u0007J\u0012\u0010/\u001a\u0004\u0018\u00010\u00042\u0006\u00100\u001a\u000201H\u0016J\u0012\u00102\u001a\u0004\u0018\u0001032\u0006\u00100\u001a\u000201H\u0016J\b\u00104\u001a\u00020\u0004H\u0017R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048UX\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R \u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@BX\u0087\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u000e8GX\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R&\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R&\u0010\u0017\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R&\u0010\u001a\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u001dR&\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00048\u0016@VX\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0006\"\u0004\b!\u0010\"R\u0014\u0010#\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R \u0010$\u001a\u0004\u0018\u00010\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\"\u00a8\u00066"}, d2 = {"Lorg/owntracks/android/model/messages/MessageBase;", "Landroidx/databinding/BaseObservable;", "()V", "baseTopicSuffix", "", "getBaseTopicSuffix", "()Ljava/lang/String;", "contactKey", "getContactKey", "<set-?>", "", "isIncoming", "()Z", "messageId", "", "getMessageId", "()J", "", "modeId", "getModeId", "()I", "setModeId", "(I)V", "qos", "getQos", "setQos", "retained", "getRetained", "setRetained", "(Z)V", "value", "topic", "getTopic", "setTopic", "(Ljava/lang/String;)V", "topicBase", "trackerId", "getTrackerId", "setTrackerId", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "getBaseTopic", "hasTrackerId", "isValidMessage", "setIncoming", "toJson", "parser", "Lorg/owntracks/android/support/Parser;", "toJsonBytes", "", "toString", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(alphabetic = true)
@com.fasterxml.jackson.annotation.JsonSubTypes(value = {@com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageLocation.class, name = "location"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageTransition.class, name = "transition"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageCard.class, name = "card"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageCmd.class, name = "cmd"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageConfiguration.class, name = "configuration"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageEncrypted.class, name = "encrypted"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageWaypoint.class, name = "waypoint"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageWaypoints.class, name = "waypoints"), @com.fasterxml.jackson.annotation.JsonSubTypes.Type(value = org.owntracks.android.model.messages.MessageLwt.class, name = "lwt")})
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type", defaultImpl = org.owntracks.android.model.messages.MessageUnknown.class)
public abstract class MessageBase extends androidx.databinding.BaseObservable {
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private final long messageId = 0L;
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private java.lang.String topic = "";
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private java.lang.String topicBase;
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private int modeId = 0;
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private boolean isIncoming = false;
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private int qos = 0;
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private boolean retained = false;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "tid")
    @com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
    private java.lang.String trackerId;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "base";
    @org.jetbrains.annotations.Nullable()
    private static final java.lang.Void BASETOPIC_SUFFIX = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageBase.Companion Companion = null;
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final long getMessageId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getTopic() {
        return null;
    }
    
    public void setTopic(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final int getModeId() {
        return 0;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final void setModeId(int p0) {
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final boolean isIncoming() {
        return false;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final int getQos() {
        return 0;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final void setQos(int p0) {
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final boolean getRetained() {
        return false;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final void setRetained(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getTrackerId() {
        return null;
    }
    
    public void setTrackerId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final java.lang.String getContactKey() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final void setIncoming() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    protected java.lang.String getBaseTopicSuffix() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public boolean isValidMessage() {
        return false;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final boolean hasTrackerId() {
        return false;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    private final java.lang.String getBaseTopic(java.lang.String topic) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public java.lang.String toString() {
        return null;
    }
    
    public void addMqttPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public byte[] toJsonBytes(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Parser parser) throws java.io.IOException {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String toJson(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Parser parser) throws java.io.IOException {
        return null;
    }
    
    public MessageBase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/owntracks/android/model/messages/MessageBase$Companion;", "", "()V", "BASETOPIC_SUFFIX", "", "getBASETOPIC_SUFFIX", "()Ljava/lang/Void;", "TYPE", "", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Void getBASETOPIC_SUFFIX() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}