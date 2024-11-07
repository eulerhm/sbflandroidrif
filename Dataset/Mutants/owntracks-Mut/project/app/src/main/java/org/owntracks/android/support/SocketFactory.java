package org.owntracks.android.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SocketFactory extends javax.net.ssl.SSLSocketFactory {

    private javax.net.ssl.SSLSocketFactory factory;

    private String[] protocols = new String[] { "TLSv1", "TLSv1.1", "TLSv1.2", "TLSv1.3" };

    public static class SocketFactoryOptions {

        private InputStream caCrtInputStream;

        private InputStream caClientP12InputStream;

        private String caClientP12Password;

        public SocketFactoryOptions withCaInputStream(InputStream stream) {
            if (!ListenerUtil.mutListener.listen(1321)) {
                this.caCrtInputStream = stream;
            }
            return this;
        }

        public SocketFactoryOptions withClientP12InputStream(InputStream stream) {
            if (!ListenerUtil.mutListener.listen(1322)) {
                this.caClientP12InputStream = stream;
            }
            return this;
        }

        public SocketFactoryOptions withClientP12Password(String password) {
            if (!ListenerUtil.mutListener.listen(1323)) {
                this.caClientP12Password = password;
            }
            return this;
        }

        boolean hasCaCrt() {
            return caCrtInputStream != null;
        }

        boolean hasClientP12Crt() {
            return caClientP12Password != null;
        }

        InputStream getCaCrtInputStream() {
            return caCrtInputStream;
        }

        InputStream getCaClientP12InputStream() {
            return caClientP12InputStream;
        }

        String getCaClientP12Password() {
            return caClientP12Password;
        }

        boolean hasClientP12Password() {
            return (ListenerUtil.mutListener.listen(1324) ? ((caClientP12Password != null) || !caClientP12Password.equals("")) : ((caClientP12Password != null) && !caClientP12Password.equals("")));
        }
    }

    private final TrustManagerFactory tmf;

    public SocketFactory(SocketFactoryOptions options) throws KeyStoreException, NoSuchAlgorithmException, IOException, KeyManagementException, java.security.cert.CertificateException, UnrecoverableKeyException {
        if (!ListenerUtil.mutListener.listen(1325)) {
            Timber.tag(this.toString()).v("initializing CustomSocketFactory");
        }
        tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
        if (!ListenerUtil.mutListener.listen(1341)) {
            if (options.hasCaCrt()) {
                if (!ListenerUtil.mutListener.listen(1329)) {
                    Timber.tag(this.toString()).v("options.hasCaCrt(): true");
                }
                KeyStore caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                if (!ListenerUtil.mutListener.listen(1330)) {
                    caKeyStore.load(null, null);
                }
                CertificateFactory caCF = CertificateFactory.getInstance("X.509");
                X509Certificate ca = (X509Certificate) caCF.generateCertificate(options.getCaCrtInputStream());
                String alias = ca.getSubjectX500Principal().getName();
                if (!ListenerUtil.mutListener.listen(1331)) {
                    // Set propper alias name
                    caKeyStore.setCertificateEntry(alias, ca);
                }
                if (!ListenerUtil.mutListener.listen(1332)) {
                    tmf.init(caKeyStore);
                }
                if (!ListenerUtil.mutListener.listen(1333)) {
                    Timber.v("Certificate Owner: %s", ca.getSubjectDN().toString());
                }
                if (!ListenerUtil.mutListener.listen(1334)) {
                    Timber.v("Certificate Issuer: %s", ca.getIssuerDN().toString());
                }
                if (!ListenerUtil.mutListener.listen(1335)) {
                    Timber.v("Certificate Serial Number: %s", ca.getSerialNumber().toString());
                }
                if (!ListenerUtil.mutListener.listen(1336)) {
                    Timber.v("Certificate Algorithm: %s", ca.getSigAlgName());
                }
                if (!ListenerUtil.mutListener.listen(1337)) {
                    Timber.v("Certificate Version: %s", ca.getVersion());
                }
                if (!ListenerUtil.mutListener.listen(1338)) {
                    Timber.v("Certificate OID: %s", ca.getSigAlgOID());
                }
                Enumeration<String> aliasesCA = caKeyStore.aliases();
                if (!ListenerUtil.mutListener.listen(1340)) {
                    {
                        long _loopCounter13 = 0;
                        while (aliasesCA.hasMoreElements()) {
                            ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                            String o = aliasesCA.nextElement();
                            if (!ListenerUtil.mutListener.listen(1339)) {
                                Timber.v("Alias: %s isKeyEntry:%s isCertificateEntry:%s", o, caKeyStore.isKeyEntry(o), caKeyStore.isCertificateEntry(o));
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1326)) {
                    Timber.v("CA sideload: false, using system keystore");
                }
                KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
                if (!ListenerUtil.mutListener.listen(1327)) {
                    keyStore.load(null);
                }
                if (!ListenerUtil.mutListener.listen(1328)) {
                    tmf.init(keyStore);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1350)) {
            if (options.hasClientP12Crt()) {
                if (!ListenerUtil.mutListener.listen(1344)) {
                    Timber.tag(this.toString()).v("options.hasClientP12Crt(): true");
                }
                KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
                if (!ListenerUtil.mutListener.listen(1345)) {
                    clientKeyStore.load(options.getCaClientP12InputStream(), options.hasClientP12Password() ? options.getCaClientP12Password().toCharArray() : new char[0]);
                }
                if (!ListenerUtil.mutListener.listen(1346)) {
                    kmf.init(clientKeyStore, options.hasClientP12Password() ? options.getCaClientP12Password().toCharArray() : new char[0]);
                }
                if (!ListenerUtil.mutListener.listen(1347)) {
                    Timber.tag(this.toString()).v("Client .p12 Keystore content: ");
                }
                Enumeration<String> aliasesClientCert = clientKeyStore.aliases();
                if (!ListenerUtil.mutListener.listen(1349)) {
                    {
                        long _loopCounter14 = 0;
                        while (aliasesClientCert.hasMoreElements()) {
                            ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                            String o = aliasesClientCert.nextElement();
                            if (!ListenerUtil.mutListener.listen(1348)) {
                                Timber.v("Alias: %s", o);
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1342)) {
                    Timber.tag(this.toString()).v("Client .p12 sideload: false, using null client cert");
                }
                if (!ListenerUtil.mutListener.listen(1343)) {
                    kmf.init(null, null);
                }
            }
        }
        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        if (!ListenerUtil.mutListener.listen(1351)) {
            context.init(kmf.getKeyManagers(), getTrustManagers(), null);
        }
        if (!ListenerUtil.mutListener.listen(1352)) {
            this.factory = context.getSocketFactory();
        }
    }

    public TrustManager[] getTrustManagers() {
        return tmf.getTrustManagers();
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return this.factory.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return this.factory.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket() throws IOException {
        SSLSocket r = (SSLSocket) this.factory.createSocket();
        if (!ListenerUtil.mutListener.listen(1353)) {
            r.setEnabledProtocols(protocols);
        }
        return r;
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        SSLSocket r = (SSLSocket) this.factory.createSocket(s, host, port, autoClose);
        if (!ListenerUtil.mutListener.listen(1354)) {
            r.setEnabledProtocols(protocols);
        }
        return r;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        SSLSocket r = (SSLSocket) this.factory.createSocket(host, port);
        if (!ListenerUtil.mutListener.listen(1355)) {
            r.setEnabledProtocols(protocols);
        }
        return r;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        SSLSocket r = (SSLSocket) this.factory.createSocket(host, port, localHost, localPort);
        if (!ListenerUtil.mutListener.listen(1356)) {
            r.setEnabledProtocols(protocols);
        }
        return r;
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        SSLSocket r = (SSLSocket) this.factory.createSocket(host, port);
        if (!ListenerUtil.mutListener.listen(1357)) {
            r.setEnabledProtocols(protocols);
        }
        return r;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        SSLSocket r = (SSLSocket) this.factory.createSocket(address, port, localAddress, localPort);
        if (!ListenerUtil.mutListener.listen(1358)) {
            r.setEnabledProtocols(protocols);
        }
        return r;
    }
}
