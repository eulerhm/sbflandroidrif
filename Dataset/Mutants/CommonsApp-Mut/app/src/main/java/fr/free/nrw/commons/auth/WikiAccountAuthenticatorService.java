package fr.free.nrw.commons.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.di.CommonsDaggerService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles the Auth service of the App, see AndroidManifests for details
 * (Uses Dagger 2 as injector)
 */
public class WikiAccountAuthenticatorService extends CommonsDaggerService {

    @Nullable
    private AbstractAccountAuthenticator authenticator;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(1268)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(1269)) {
            authenticator = new WikiAccountAuthenticator(this);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return authenticator == null ? null : authenticator.getIBinder();
    }
}
