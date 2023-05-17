package com.ichi2.async;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import android.os.AsyncTask;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.libanki.Collection;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class CollectionLoader extends AsyncTask<Void, Void, Collection> {

    private final LifecycleOwner mLifecycleOwner;

    private final Callback mCallback;

    public interface Callback {

        void execute(Collection col);
    }

    public static void load(LifecycleOwner lifecycleOwner, Callback callback) {
        CollectionLoader loader = new CollectionLoader(lifecycleOwner, callback);
        if (!ListenerUtil.mutListener.listen(12609)) {
            loader.execute();
        }
    }

    private CollectionLoader(LifecycleOwner lifecycleOwner, Callback callback) {
        mLifecycleOwner = lifecycleOwner;
        mCallback = callback;
    }

    @Override
    protected Collection doInBackground(Void... params) {
        // Don't touch collection if lockCollection flag is set
        if (CollectionHelper.getInstance().isCollectionLocked()) {
            if (!ListenerUtil.mutListener.listen(12610)) {
                Timber.w("onStartLoading() :: Another thread has requested to keep the collection closed.");
            }
            return null;
        }
        // load collection
        try {
            if (!ListenerUtil.mutListener.listen(12613)) {
                Timber.d("CollectionLoader accessing collection");
            }
            Collection col = CollectionHelper.getInstance().getCol(AnkiDroidApp.getInstance().getApplicationContext());
            if (!ListenerUtil.mutListener.listen(12614)) {
                Timber.i("CollectionLoader obtained collection");
            }
            return col;
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(12611)) {
                Timber.e(e, "loadInBackground - RuntimeException on opening collection");
            }
            if (!ListenerUtil.mutListener.listen(12612)) {
                AnkiDroidApp.sendExceptionReport(e, "CollectionLoader.loadInBackground");
            }
            return null;
        }
    }

    @Override
    protected void onPostExecute(Collection col) {
        if (!ListenerUtil.mutListener.listen(12615)) {
            super.onPostExecute(col);
        }
        if (!ListenerUtil.mutListener.listen(12617)) {
            if (mLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                if (!ListenerUtil.mutListener.listen(12616)) {
                    mCallback.execute(col);
                }
            }
        }
    }
}
