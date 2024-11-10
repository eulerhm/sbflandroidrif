package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.owntracks.android.model.CommandAction;
import org.owntracks.android.support.Preferences;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\n8TX\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006 "}, d2 = {"Lorg/owntracks/android/model/messages/MessageCmd;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "action", "Lorg/owntracks/android/model/CommandAction;", "getAction", "()Lorg/owntracks/android/model/CommandAction;", "setAction", "(Lorg/owntracks/android/model/CommandAction;)V", "baseTopicSuffix", "", "getBaseTopicSuffix", "()Ljava/lang/String;", "configuration", "Lorg/owntracks/android/model/messages/MessageConfiguration;", "getConfiguration", "()Lorg/owntracks/android/model/messages/MessageConfiguration;", "setConfiguration", "(Lorg/owntracks/android/model/messages/MessageConfiguration;)V", "waypoints", "Lorg/owntracks/android/model/messages/MessageWaypoints;", "getWaypoints", "()Lorg/owntracks/android/model/messages/MessageWaypoints;", "setWaypoints", "(Lorg/owntracks/android/model/messages/MessageWaypoints;)V", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "isValidMessage", "", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public final class MessageCmd extends org.owntracks.android.model.messages.MessageBase {
    @org.jetbrains.annotations.Nullable()
    private org.owntracks.android.model.CommandAction action;
    @org.jetbrains.annotations.Nullable()
    private org.owntracks.android.model.messages.MessageWaypoints waypoints;
    @org.jetbrains.annotations.Nullable()
    private org.owntracks.android.model.messages.MessageConfiguration configuration;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "cmd";
    private static final java.lang.String BASETOPIC_SUFFIX = "/cmd";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageCmd.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final org.owntracks.android.model.CommandAction getAction() {
        return null;
    }
    
    public final void setAction(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.model.CommandAction p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.owntracks.android.model.messages.MessageWaypoints getWaypoints() {
        return null;
    }
    
    public final void setWaypoints(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.model.messages.MessageWaypoints p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.owntracks.android.model.messages.MessageConfiguration getConfiguration() {
        return null;
    }
    
    public final void setConfiguration(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.model.messages.MessageConfiguration p0) {
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
    
    public MessageCmd() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/model/messages/MessageCmd$Companion;", "", "()V", "BASETOPIC_SUFFIX", "", "TYPE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}