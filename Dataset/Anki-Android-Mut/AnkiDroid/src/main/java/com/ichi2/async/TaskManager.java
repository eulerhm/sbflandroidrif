package com.ichi2.async;

import android.content.res.Resources;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TaskManager {

    /**
     * Tasks which are running or waiting to run.
     */
    private static final List<CollectionTask> sTasks = Collections.synchronizedList(new LinkedList<>());

    protected static void addTasks(CollectionTask task) {
        if (!ListenerUtil.mutListener.listen(13130)) {
            sTasks.add(task);
        }
    }

    protected static boolean removeTask(CollectionTask task) {
        return sTasks.remove(task);
    }

    /**
     * The most recently started {@link CollectionTask} instance.
     */
    private static CollectionTask sLatestInstance;

    protected static void setLatestInstance(CollectionTask task) {
        if (!ListenerUtil.mutListener.listen(13131)) {
            sLatestInstance = task;
        }
    }

    /**
     * Starts a new {@link CollectionTask}, with no listener
     * <p>
     * Tasks will be executed serially, in the order in which they are started.
     * <p>
     * This method must be called on the main thread.
     *
     * @param task the task to execute
     * @return the newly created task
     */
    public static <ProgressBackground, ResultBackground> CollectionTask<ProgressBackground, ProgressBackground, ResultBackground, ResultBackground> launchCollectionTask(CollectionTask.Task<ProgressBackground, ResultBackground> task) {
        return launchCollectionTask(task, null);
    }

    /**
     * Starts a new {@link CollectionTask}, with a listener provided for callbacks during execution
     * <p>
     * Tasks will be executed serially, in the order in which they are started.
     * <p>
     * This method must be called on the main thread.
     *
     * @param task the task to execute
     * @param listener to the status and result of the task, may be null
     * @return the newly created task
     */
    public static <ProgressListener, ProgressBackground extends ProgressListener, ResultListener, ResultBackground extends ResultListener> CollectionTask<ProgressListener, ProgressBackground, ResultListener, ResultBackground> launchCollectionTask(@NonNull CollectionTask.Task<ProgressBackground, ResultBackground> task, @Nullable TaskListener<ProgressListener, ResultListener> listener) {
        // Start new task
        CollectionTask<ProgressListener, ProgressBackground, ResultListener, ResultBackground> newTask = new CollectionTask<>(task, listener, sLatestInstance);
        if (!ListenerUtil.mutListener.listen(13132)) {
            newTask.execute();
        }
        return newTask;
    }

    /**
     * Block the current thread until the currently running CollectionTask instance (if any) has finished.
     */
    public static void waitToFinish() {
        if (!ListenerUtil.mutListener.listen(13133)) {
            waitToFinish(null);
        }
    }

    /**
     * Block the current thread until the currently running CollectionTask instance (if any) has finished.
     * @param timeoutSeconds timeout in seconds
     * @return whether or not the previous task was successful or not
     */
    public static boolean waitToFinish(Integer timeoutSeconds) {
        try {
            if (!ListenerUtil.mutListener.listen(13140)) {
                if ((ListenerUtil.mutListener.listen(13135) ? ((sLatestInstance != null) || (sLatestInstance.getStatus() != AsyncTask.Status.FINISHED)) : ((sLatestInstance != null) && (sLatestInstance.getStatus() != AsyncTask.Status.FINISHED)))) {
                    if (!ListenerUtil.mutListener.listen(13136)) {
                        Timber.d("CollectionTask: waiting for task %s to finish...", sLatestInstance.getTask().getClass());
                    }
                    if (!ListenerUtil.mutListener.listen(13139)) {
                        if (timeoutSeconds != null) {
                            if (!ListenerUtil.mutListener.listen(13138)) {
                                sLatestInstance.get(timeoutSeconds, TimeUnit.SECONDS);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13137)) {
                                sLatestInstance.get();
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(13134)) {
                Timber.e(e, "Exception waiting for task to finish");
            }
            return false;
        }
    }

    /**
     * Cancel the current task only if it's of type taskType
     */
    public static void cancelCurrentlyExecutingTask() {
        CollectionTask latestInstance = sLatestInstance;
        if (!ListenerUtil.mutListener.listen(13143)) {
            if (latestInstance != null) {
                if (!ListenerUtil.mutListener.listen(13142)) {
                    if (latestInstance.safeCancel()) {
                        if (!ListenerUtil.mutListener.listen(13141)) {
                            Timber.i("Cancelled task %s", latestInstance.getTask().getClass());
                        }
                    }
                }
            }
        }
    }

    /**
     * Cancel all tasks of type taskType
     */
    public static void cancelAllTasks(Class taskType) {
        int count = 0;
        if (!ListenerUtil.mutListener.listen(13147)) {
            {
                long _loopCounter234 = 0;
                // safeCancel modifies sTasks, so iterate over a concrete copy
                for (CollectionTask task : new ArrayList<>(sTasks)) {
                    ListenerUtil.loopListener.listen("_loopCounter234", ++_loopCounter234);
                    if (!ListenerUtil.mutListener.listen(13144)) {
                        if (task.getTask().getClass() != taskType) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13146)) {
                        if (task.safeCancel()) {
                            if (!ListenerUtil.mutListener.listen(13145)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13154)) {
            if ((ListenerUtil.mutListener.listen(13152) ? (count >= 0) : (ListenerUtil.mutListener.listen(13151) ? (count <= 0) : (ListenerUtil.mutListener.listen(13150) ? (count < 0) : (ListenerUtil.mutListener.listen(13149) ? (count != 0) : (ListenerUtil.mutListener.listen(13148) ? (count == 0) : (count > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13153)) {
                    Timber.i("Cancelled %d instances of task %s", count, taskType);
                }
            }
        }
    }

    public static ProgressCallback progressCallback(CollectionTask task, Resources res) {
        return new ProgressCallback(task, res);
    }

    /**
     * Helper class for allowing inner function to publish progress of an AsyncTask.
     */
    public static class ProgressCallback<Progress> {

        private final Resources res;

        private final ProgressSender<Progress> task;

        protected ProgressCallback(ProgressSender<Progress> task, Resources res) {
            this.res = res;
            if (res != null) {
                this.task = task;
            } else {
                this.task = null;
            }
        }

        public Resources getResources() {
            return res;
        }

        public void publishProgress(Progress value) {
            if (!ListenerUtil.mutListener.listen(13156)) {
                if (task != null) {
                    if (!ListenerUtil.mutListener.listen(13155)) {
                        task.doProgress(value);
                    }
                }
            }
        }
    }
}
