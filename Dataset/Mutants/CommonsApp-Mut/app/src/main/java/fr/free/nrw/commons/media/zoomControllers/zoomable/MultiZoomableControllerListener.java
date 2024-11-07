package fr.free.nrw.commons.media.zoomControllers.zoomable;

import android.graphics.Matrix;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MultiZoomableControllerListener implements ZoomableController.Listener {

    private final List<ZoomableController.Listener> mListeners = new ArrayList<>();

    @Override
    public synchronized void onTransformBegin(Matrix transform) {
        if (!ListenerUtil.mutListener.listen(8213)) {
            {
                long _loopCounter127 = 0;
                for (ZoomableController.Listener listener : mListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter127", ++_loopCounter127);
                    if (!ListenerUtil.mutListener.listen(8212)) {
                        listener.onTransformBegin(transform);
                    }
                }
            }
        }
    }

    @Override
    public synchronized void onTransformChanged(Matrix transform) {
        if (!ListenerUtil.mutListener.listen(8215)) {
            {
                long _loopCounter128 = 0;
                for (ZoomableController.Listener listener : mListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter128", ++_loopCounter128);
                    if (!ListenerUtil.mutListener.listen(8214)) {
                        listener.onTransformChanged(transform);
                    }
                }
            }
        }
    }

    @Override
    public synchronized void onTransformEnd(Matrix transform) {
        if (!ListenerUtil.mutListener.listen(8217)) {
            {
                long _loopCounter129 = 0;
                for (ZoomableController.Listener listener : mListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter129", ++_loopCounter129);
                    if (!ListenerUtil.mutListener.listen(8216)) {
                        listener.onTransformEnd(transform);
                    }
                }
            }
        }
    }

    public synchronized void addListener(ZoomableController.Listener listener) {
        if (!ListenerUtil.mutListener.listen(8218)) {
            mListeners.add(listener);
        }
    }

    public synchronized void removeListener(ZoomableController.Listener listener) {
        if (!ListenerUtil.mutListener.listen(8219)) {
            mListeners.remove(listener);
        }
    }
}
