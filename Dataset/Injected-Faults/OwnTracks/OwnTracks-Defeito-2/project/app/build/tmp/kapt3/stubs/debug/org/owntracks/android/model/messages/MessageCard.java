package org.owntracks.android.model.messages;

import androidx.databinding.Bindable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048TX\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R(\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004@GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0004\u0018\u00010\u00048GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\u000b\u00a8\u0006\u0013"}, d2 = {"Lorg/owntracks/android/model/messages/MessageCard;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "baseTopicSuffix", "", "getBaseTopicSuffix", "()Ljava/lang/String;", "<set-?>", "face", "getFace", "setFace", "(Ljava/lang/String;)V", "name", "getName", "setName", "hasFace", "", "hasName", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public final class MessageCard extends org.owntracks.android.model.messages.MessageBase {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String face;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASETOPIC_SUFFIX = "/info";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "card";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.model.messages.MessageCard.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @androidx.databinding.Bindable()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFace() {
        return null;
    }
    
    @com.fasterxml.jackson.annotation.JsonSetter()
    public final void setFace(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final boolean hasFace() {
        return false;
    }
    
    public final boolean hasName() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.String getBaseTopicSuffix() {
        return null;
    }
    
    public MessageCard() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/model/messages/MessageCard$Companion;", "", "()V", "BASETOPIC_SUFFIX", "", "TYPE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}