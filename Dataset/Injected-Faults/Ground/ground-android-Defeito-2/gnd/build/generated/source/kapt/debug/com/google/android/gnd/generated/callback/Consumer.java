package com.google.android.gnd.generated.callback;
public final class Consumer implements java8.util.function.Consumer {
    final Listener mListener;
    final int mSourceId;
    public Consumer(Listener listener, int sourceId) {
        mListener = listener;
        mSourceId = sourceId;
    }
    @Override
    public void accept(java.lang.Object callbackArg_0) {
        mListener._internalCallbackAccept(mSourceId , callbackArg_0);
    }
    public interface Listener {
        void _internalCallbackAccept(int sourceId , java.lang.Object callbackArg_0);
    }
}