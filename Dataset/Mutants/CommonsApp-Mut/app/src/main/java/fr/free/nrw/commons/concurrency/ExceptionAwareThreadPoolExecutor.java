package fr.free.nrw.commons.concurrency;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class ExceptionAwareThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    private final ExceptionHandler exceptionHandler;

    public ExceptionAwareThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, ExceptionHandler exceptionHandler) {
        super(corePoolSize, threadFactory);
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (!ListenerUtil.mutListener.listen(4725)) {
            super.afterExecute(r, t);
        }
        if (!ListenerUtil.mutListener.listen(4731)) {
            if ((ListenerUtil.mutListener.listen(4726) ? (t == null || r instanceof Future<?>) : (t == null && r instanceof Future<?>))) {
                try {
                    Future<?> future = (Future<?>) r;
                    if (!ListenerUtil.mutListener.listen(4730)) {
                        if (future.isDone())
                            if (!ListenerUtil.mutListener.listen(4729)) {
                                future.get();
                            }
                    }
                } catch (CancellationException | InterruptedException e) {
                } catch (ExecutionException e) {
                    if (!ListenerUtil.mutListener.listen(4727)) {
                        t = e.getCause() != null ? e.getCause() : e;
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(4728)) {
                        t = e;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4733)) {
            if (t != null) {
                if (!ListenerUtil.mutListener.listen(4732)) {
                    exceptionHandler.onException(t);
                }
            }
        }
    }
}
