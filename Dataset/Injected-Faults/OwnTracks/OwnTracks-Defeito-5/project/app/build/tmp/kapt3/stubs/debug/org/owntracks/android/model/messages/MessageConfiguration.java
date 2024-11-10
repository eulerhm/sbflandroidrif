package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.*;
import org.owntracks.android.support.MessageWaypointCollection;
import java.util.*;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0012H\u0007J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0007J\u0015\u0010\u0016\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0087\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0007J\u001b\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\nH\u0087\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0005H\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048G\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001e"}, d2 = {"Lorg/owntracks/android/model/messages/MessageConfiguration;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "keys", "", "", "getKeys", "()Ljava/util/Set;", "map", "", "", "waypoints", "Lorg/owntracks/android/support/MessageWaypointCollection;", "getWaypoints", "()Lorg/owntracks/android/support/MessageWaypointCollection;", "setWaypoints", "(Lorg/owntracks/android/support/MessageWaypointCollection;)V", "any", "", "containsKey", "", "key", "get", "removeKey", "", "set", "value", "setTid", "tid", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public final class MessageConfiguration extends org.owntracks.android.model.messages.MessageBase {
    private final java.util.Map<java.lang.String, java.lang.Object> map = null;
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
    private org.owntracks.android.support.MessageWaypointCollection waypoints;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "configuration";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageConfiguration.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.support.MessageWaypointCollection getWaypoints() {
        return null;
    }
    
    public final void setWaypoints(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.MessageWaypointCollection p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonPropertyOrder(alphabetic = true)
    @com.fasterxml.jackson.annotation.JsonAnyGetter()
    public final java.util.Map<java.lang.String, java.lang.Object> any() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonAnySetter()
    public final void set(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.Object value) {
    }
    
    @com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
    public final void setTid(@org.jetbrains.annotations.NotNull()
    java.lang.String tid) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final java.lang.Object get(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final boolean containsKey(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final java.util.Set<java.lang.String> getKeys() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonIgnore()
    public final void removeKey(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
    }
    
    public MessageConfiguration() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/model/messages/MessageConfiguration$Companion;", "", "()V", "TYPE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}