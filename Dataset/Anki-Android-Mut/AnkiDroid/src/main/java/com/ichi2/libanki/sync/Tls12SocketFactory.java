/**
 * *************************************************************************************
 *  Copyright (c) 2019 Mike Hardy <github@mikehardy.net>                                 *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki.sync;

import android.os.Build;
import com.ichi2.anki.AnkiDroidApp;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Enables TLS v1.2 when creating SSLSockets.
 * <p/>
 * For some reason, android supports TLS v1.2 from API 16, but enables it by
 * default only from API 20. Additionally some Samsung API21 phones also need this.
 *
 * @link https://developer.android.com/reference/javax/net/ssl/SSLSocket.html
 * @see SSLSocketFactory
 */
public class Tls12SocketFactory extends SSLSocketFactory {

    private static final String[] TLS_V12_ONLY = { "TLSv1.2" };

    private final SSLSocketFactory delegate;

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (!ListenerUtil.mutListener.listen(20509)) {
            if ((ListenerUtil.mutListener.listen(20497) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(20496) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(20495) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(20494) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(20493) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(20499)) {
                        Timber.d("Creating unified TrustManager");
                    }
                    Certificate cert = getUserTrustRootCertificate();
                    String keyStoreType = KeyStore.getDefaultType();
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    if (!ListenerUtil.mutListener.listen(20500)) {
                        keyStore.load(null, null);
                    }
                    if (!ListenerUtil.mutListener.listen(20501)) {
                        keyStore.setCertificateEntry("ca", cert);
                    }
                    UnifiedTrustManager trustManager = new UnifiedTrustManager(keyStore);
                    if (!ListenerUtil.mutListener.listen(20502)) {
                        Timber.d("Finished: Creating unified TrustManager");
                    }
                    SSLContext sc = SSLContext.getInstance("TLSv1.2");
                    if (!ListenerUtil.mutListener.listen(20503)) {
                        sc.init(null, new TrustManager[] { trustManager }, null);
                    }
                    Tls12SocketFactory socketFactory = new Tls12SocketFactory(sc.getSocketFactory());
                    if (!ListenerUtil.mutListener.listen(20504)) {
                        client.sslSocketFactory(socketFactory, trustManager);
                    }
                    ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                    List<ConnectionSpec> specs = new ArrayList<>(3);
                    if (!ListenerUtil.mutListener.listen(20505)) {
                        specs.add(cs);
                    }
                    if (!ListenerUtil.mutListener.listen(20506)) {
                        specs.add(ConnectionSpec.COMPATIBLE_TLS);
                    }
                    if (!ListenerUtil.mutListener.listen(20507)) {
                        specs.add(ConnectionSpec.CLEARTEXT);
                    }
                    if (!ListenerUtil.mutListener.listen(20508)) {
                        client.connectionSpecs(specs);
                    }
                } catch (Exception exc) {
                    if (!ListenerUtil.mutListener.listen(20498)) {
                        Timber.e(exc, "Error while setting TLS 1.2");
                    }
                }
            }
        }
        return client;
    }

    private static Certificate getUserTrustRootCertificate() throws CertificateException, IOException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        try (InputStream crt = AnkiDroidApp.getResourceAsStream("assets/USERTrust_RSA.crt")) {
            return cf.generateCertificate(crt);
        }
    }

    private Tls12SocketFactory(SSLSocketFactory base) {
        this.delegate = base;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return patch(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return patch(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return patch(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return patch(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return patch(delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket patch(Socket s) {
        if (!ListenerUtil.mutListener.listen(20511)) {
            if (s instanceof SSLSocket) {
                if (!ListenerUtil.mutListener.listen(20510)) {
                    ((SSLSocket) s).setEnabledProtocols(TLS_V12_ONLY);
                }
            }
        }
        return s;
    }
}
