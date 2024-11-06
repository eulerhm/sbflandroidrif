package fr.free.nrw.commons.concurrency;

import androidx.annotation.NonNull;
import fr.free.nrw.commons.BuildConfig;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackgroundPoolExceptionHandler implements ExceptionHandler {

    /**
     * If an exception occurs on a background thread, this handler will crash for debug builds
     * but fail silently for release builds.
     * @param t
     */
    @Override
    public void onException(@NonNull final Throwable t) {
        if (!ListenerUtil.mutListener.listen(4700)) {
            // Crash for debug build
            if (BuildConfig.DEBUG) {
                Thread thread = new Thread(() -> {
                    throw new RuntimeException(t);
                });
                if (!ListenerUtil.mutListener.listen(4699)) {
                    thread.start();
                }
            }
        }
    }
}
