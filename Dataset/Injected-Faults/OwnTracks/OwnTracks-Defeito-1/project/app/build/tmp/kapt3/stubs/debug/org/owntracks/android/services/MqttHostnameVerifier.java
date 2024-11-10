package org.owntracks.android.services;

import org.conscrypt.OkHostnameVerifier;
import timber.log.Timber;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * *
 * A Hostname verifier for use on MQTT connections that skips the verification if the server leaf
 * certificate matches the supplied CA certificate. This is typically for use with self-signed
 * certificates where the CA is the same as the leaf cert presented by the MQTT endpoint.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/owntracks/android/services/MqttHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "caCertificate", "Ljava/security/cert/Certificate;", "(Ljava/security/cert/Certificate;)V", "caFingerprint", "", "verify", "", "hostname", "", "session", "Ljavax/net/ssl/SSLSession;", "app_debug"})
public final class MqttHostnameVerifier implements javax.net.ssl.HostnameVerifier {
    private final byte[] caFingerprint = null;
    
    @java.lang.Override()
    public boolean verify(@org.jetbrains.annotations.Nullable()
    java.lang.String hostname, @org.jetbrains.annotations.Nullable()
    javax.net.ssl.SSLSession session) {
        return false;
    }
    
    public MqttHostnameVerifier(@org.jetbrains.annotations.NotNull()
    java.security.cert.Certificate caCertificate) {
        super();
    }
}