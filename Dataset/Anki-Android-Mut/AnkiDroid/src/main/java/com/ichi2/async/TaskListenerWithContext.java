package com.ichi2.async;

import java.lang.ref.WeakReference;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Similar to task listener, but if the context disappear, no action are executed.
 * We ensure that the context can't disappear during the execution of the methods.
 */
public abstract class TaskListenerWithContext<CTX, Progress, Result> extends TaskListener<Progress, Result> {

    private final WeakReference<CTX> mContext;

    protected TaskListenerWithContext(CTX context) {
        mContext = new WeakReference<>(context);
    }

    public final void onPreExecute() {
        CTX context = mContext.get();
        if (!ListenerUtil.mutListener.listen(13123)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(13122)) {
                    actualOnPreExecute(context);
                }
            }
        }
    }

    public final void onProgressUpdate(Progress value) {
        CTX context = mContext.get();
        if (!ListenerUtil.mutListener.listen(13125)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(13124)) {
                    actualOnProgressUpdate(context, value);
                }
            }
        }
    }

    /**
     * Invoked when the background task publishes an update.
     * <p>
     * The semantics of the update data depends on the task itself.
     * Assumes context exists.
     */
    public void actualOnProgressUpdate(@NonNull CTX context, Progress value) {
    }

    /**
     * Invoked before the task is started. Assumes context exists.
     */
    public abstract void actualOnPreExecute(@NonNull CTX context);

    public final void onPostExecute(Result result) {
        CTX context = mContext.get();
        if (!ListenerUtil.mutListener.listen(13127)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(13126)) {
                    actualOnPostExecute(context, result);
                }
            }
        }
    }

    /**
     * Invoked after the task has completed.
     * <p>
     * The semantics of the result depends on the task itself.
     */
    public abstract void actualOnPostExecute(@NonNull CTX context, Result result);

    public void onCancelled() {
        CTX context = mContext.get();
        if (!ListenerUtil.mutListener.listen(13129)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(13128)) {
                    actualOnCancelled(context);
                }
            }
        }
    }

    /**
     * Assumes context exists.
     */
    public void actualOnCancelled(@NonNull CTX context) {
    }
}
