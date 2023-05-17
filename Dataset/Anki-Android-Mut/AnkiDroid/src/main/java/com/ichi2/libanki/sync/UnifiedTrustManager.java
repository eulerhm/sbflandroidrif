/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.libanki.sync;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Did not ignore NoSuchAlgorithmException
class UnifiedTrustManager implements X509TrustManager {

    private X509TrustManager defaultTrustManager;

    private X509TrustManager localTrustManager;

    private X509Certificate[] mAcceptedIssuers;

    public UnifiedTrustManager(KeyStore localKeyStore) throws KeyStoreException, NoSuchAlgorithmException {
        if (!ListenerUtil.mutListener.listen(20512)) {
            this.defaultTrustManager = createTrustManager(null);
        }
        if (!ListenerUtil.mutListener.listen(20513)) {
            this.localTrustManager = createTrustManager(localKeyStore);
        }
        X509Certificate[] first = defaultTrustManager.getAcceptedIssuers();
        X509Certificate[] second = localTrustManager.getAcceptedIssuers();
        if (!ListenerUtil.mutListener.listen(20518)) {
            mAcceptedIssuers = Arrays.copyOf(first, (ListenerUtil.mutListener.listen(20517) ? (first.length % second.length) : (ListenerUtil.mutListener.listen(20516) ? (first.length / second.length) : (ListenerUtil.mutListener.listen(20515) ? (first.length * second.length) : (ListenerUtil.mutListener.listen(20514) ? (first.length - second.length) : (first.length + second.length))))));
        }
        if (!ListenerUtil.mutListener.listen(20519)) {
            System.arraycopy(second, 0, mAcceptedIssuers, first.length, second.length);
        }
    }

    private X509TrustManager createTrustManager(KeyStore store) throws NoSuchAlgorithmException, KeyStoreException {
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        if (!ListenerUtil.mutListener.listen(20520)) {
            tmf.init(store);
        }
        TrustManager[] trustManagers = tmf.getTrustManagers();
        return (X509TrustManager) trustManagers[0];
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        try {
            if (!ListenerUtil.mutListener.listen(20522)) {
                localTrustManager.checkServerTrusted(chain, authType);
            }
        } catch (CertificateException ce) {
            if (!ListenerUtil.mutListener.listen(20521)) {
                defaultTrustManager.checkServerTrusted(chain, authType);
            }
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        try {
            if (!ListenerUtil.mutListener.listen(20524)) {
                localTrustManager.checkClientTrusted(chain, authType);
            }
        } catch (CertificateException ce) {
            if (!ListenerUtil.mutListener.listen(20523)) {
                defaultTrustManager.checkClientTrusted(chain, authType);
            }
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return mAcceptedIssuers;
    }
}
