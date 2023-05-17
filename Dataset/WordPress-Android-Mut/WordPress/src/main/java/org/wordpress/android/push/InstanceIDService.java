package org.wordpress.android.push;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InstanceIDService extends FirebaseMessagingService {

    // [START refresh_token]
    @Override
    public void onNewToken(@NonNull String s) {
        if (!ListenerUtil.mutListener.listen(3111)) {
            super.onNewToken(s);
        }
        if (!ListenerUtil.mutListener.listen(3112)) {
            GCMRegistrationIntentService.enqueueWork(this, new Intent(this, GCMRegistrationIntentService.class));
        }
    }
}
