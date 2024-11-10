package com.google.android.gnd.generated.callback;
public final class Runnable implements java.lang.Runnable {
    final Listener mListener;
    final int mSourceId;
    public Runnable(Listener listener, int sourceId) {
        mListener = listener;
        mSourceId = sourceId;
    }
    @Override
    public void run() {
        mListener._internalCallbackRun(mSourceId );
    }
    public interface Listener {
        void _internalCallbackRun(int sourceId );
    }
}