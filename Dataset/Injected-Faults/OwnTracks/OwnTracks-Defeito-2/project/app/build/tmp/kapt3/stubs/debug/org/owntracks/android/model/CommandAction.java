package org.owntracks.android.model;

import com.fasterxml.jackson.annotation.JsonValue;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lorg/owntracks/android/model/CommandAction;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "getVal", "REPORT_LOCATION", "SET_WAYPOINTS", "SET_CONFIGURATION", "RESTART", "RECONNECT", "WAYPOINTS", "app_debug"})
public enum CommandAction {
    /*public static final*/ REPORT_LOCATION /* = new REPORT_LOCATION(null) */,
    /*public static final*/ SET_WAYPOINTS /* = new SET_WAYPOINTS(null) */,
    /*public static final*/ SET_CONFIGURATION /* = new SET_CONFIGURATION(null) */,
    /*public static final*/ RESTART /* = new RESTART(null) */,
    /*public static final*/ RECONNECT /* = new RECONNECT(null) */,
    /*public static final*/ WAYPOINTS /* = new WAYPOINTS(null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String value = null;
    
    @org.jetbrains.annotations.NotNull()
    @com.fasterxml.jackson.annotation.JsonValue()
    public final java.lang.String getVal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getValue() {
        return null;
    }
    
    CommandAction(java.lang.String value) {
    }
}