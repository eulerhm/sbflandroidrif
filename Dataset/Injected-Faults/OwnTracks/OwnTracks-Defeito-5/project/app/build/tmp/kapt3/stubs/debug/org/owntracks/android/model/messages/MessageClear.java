package org.owntracks.android.model.messages;

import org.owntracks.android.support.Parser;
import org.owntracks.android.support.Preferences;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\r"}, d2 = {"Lorg/owntracks/android/model/messages/MessageClear;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "toJson", "", "parser", "Lorg/owntracks/android/support/Parser;", "toJsonBytes", "", "app_debug"})
public final class MessageClear extends org.owntracks.android.model.messages.MessageBase {
    
    @java.lang.Override()
    public void addMqttPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public byte[] toJsonBytes(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Parser parser) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toJson(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Parser parser) {
        return null;
    }
    
    public MessageClear() {
        super();
    }
}