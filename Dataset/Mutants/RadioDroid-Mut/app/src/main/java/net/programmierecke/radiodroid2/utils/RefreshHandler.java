package net.programmierecke.radiodroid2.utils;

import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Periodical refreshes which allows the object it refreshes be garbage collected
 */
public final class RefreshHandler {

    private final Handler handler;

    private RunnableDecorator runnableDecorator;

    public RefreshHandler() {
        handler = new Handler(Looper.getMainLooper());
    }

    public final void executePeriodically(final ObjectBoundRunnable task, final long interval) {
        if (!ListenerUtil.mutListener.listen(3439)) {
            if (runnableDecorator != null) {
                if (!ListenerUtil.mutListener.listen(3438)) {
                    handler.removeCallbacks(runnableDecorator);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3440)) {
            runnableDecorator = new RunnableDecorator(task, interval);
        }
        if (!ListenerUtil.mutListener.listen(3441)) {
            handler.post(runnableDecorator);
        }
    }

    public final void cancel() {
        if (!ListenerUtil.mutListener.listen(3443)) {
            if (runnableDecorator != null) {
                if (!ListenerUtil.mutListener.listen(3442)) {
                    handler.removeCallbacks(runnableDecorator);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3444)) {
            runnableDecorator = null;
        }
    }

    private class RunnableDecorator implements Runnable {

        private final ObjectBoundRunnable runnable;

        private final long interval;

        RunnableDecorator(ObjectBoundRunnable runnable, long interval) {
            this.runnable = runnable;
            this.interval = interval;
        }

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(3445)) {
                runnable.run();
            }
            if (!ListenerUtil.mutListener.listen(3450)) {
                if ((ListenerUtil.mutListener.listen(3446) ? (runnable.objectRef.get() != null || !runnable.terminate) : (runnable.objectRef.get() != null && !runnable.terminate))) {
                    if (!ListenerUtil.mutListener.listen(3449)) {
                        handler.postDelayed(this, interval);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3447)) {
                        handler.removeCallbacks(this);
                    }
                    if (!ListenerUtil.mutListener.listen(3448)) {
                        runnableDecorator = null;
                    }
                }
            }
        }
    }

    public abstract static class ObjectBoundRunnable<T> implements Runnable {

        private final WeakReference<T> objectRef;

        private boolean terminate = false;

        public ObjectBoundRunnable(T obj) {
            objectRef = new WeakReference<>(obj);
        }

        @Override
        public void run() {
            T obj = objectRef.get();
            if (!ListenerUtil.mutListener.listen(3452)) {
                if (obj != null) {
                    if (!ListenerUtil.mutListener.listen(3451)) {
                        run(obj);
                    }
                }
            }
        }

        protected final void terminate() {
            if (!ListenerUtil.mutListener.listen(3453)) {
                terminate = true;
            }
        }

        protected abstract void run(T obj);
    }
}
