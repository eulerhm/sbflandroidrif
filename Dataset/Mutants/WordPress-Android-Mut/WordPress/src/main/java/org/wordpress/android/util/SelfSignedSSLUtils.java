package org.wordpress.android.util;

import android.content.Context;
import android.content.DialogInterface;
import android.net.http.SslCertificate;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.fluxc.network.MemorizingTrustManager;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.util.AppLog.T;
import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SelfSignedSSLUtils {

    public interface Callback {

        void certificateTrusted();
    }

    public static void showSSLWarningDialog(@NonNull final Context context, @NonNull final MemorizingTrustManager memorizingTrustManager, @Nullable final Callback callback) {
        AlertDialog.Builder alert = new MaterialAlertDialogBuilder(context);
        if (!ListenerUtil.mutListener.listen(27780)) {
            alert.setTitle(context.getString(org.wordpress.android.R.string.ssl_certificate_error));
        }
        if (!ListenerUtil.mutListener.listen(27781)) {
            alert.setMessage(context.getString(org.wordpress.android.R.string.ssl_certificate_ask_trust));
        }
        if (!ListenerUtil.mutListener.listen(27785)) {
            alert.setPositiveButton(org.wordpress.android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(27782)) {
                        // Add the certificate to our list
                        memorizingTrustManager.storeLastFailure();
                    }
                    if (!ListenerUtil.mutListener.listen(27784)) {
                        // Retry login action
                        if (callback != null) {
                            if (!ListenerUtil.mutListener.listen(27783)) {
                                callback.certificateTrusted();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(27787)) {
            alert.setNeutralButton(org.wordpress.android.R.string.ssl_certificate_details, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(27786)) {
                        ActivityLauncher.viewSSLCerts(context, memorizingTrustManager.getLastFailure().toString());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(27788)) {
            alert.show();
        }
    }

    public static X509Certificate sslCertificateToX509(@Nullable SslCertificate cert) {
        if (!ListenerUtil.mutListener.listen(27789)) {
            if (cert == null) {
                return null;
            }
        }
        Bundle bundle = SslCertificate.saveState(cert);
        X509Certificate x509Certificate = null;
        byte[] bytes = bundle.getByteArray("x509-certificate");
        if (!ListenerUtil.mutListener.listen(27793)) {
            if (bytes == null) {
                if (!ListenerUtil.mutListener.listen(27792)) {
                    AppLog.e(T.API, "Cannot load the SSLCertificate bytes from the bundle");
                }
            } else {
                try {
                    CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
                    Certificate certX509 = certFactory.generateCertificate(new ByteArrayInputStream(bytes));
                    if (!ListenerUtil.mutListener.listen(27791)) {
                        x509Certificate = (X509Certificate) certX509;
                    }
                } catch (CertificateException e) {
                    if (!ListenerUtil.mutListener.listen(27790)) {
                        AppLog.e(T.API, "Cannot generate the X509Certificate with the bytes provided", e);
                    }
                }
            }
        }
        return x509Certificate;
    }
}
