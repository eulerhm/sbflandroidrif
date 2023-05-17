package org.wordpress.android.ui.suggestion.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import org.wordpress.android.ui.suggestion.service.SuggestionService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SuggestionServiceConnectionManager implements ServiceConnection {

    private final Context mContext;

    private final long mSiteId;

    private boolean mAttemptingToBind = false;

    private boolean mBindCalled = false;

    public SuggestionServiceConnectionManager(Context context, long siteId) {
        mContext = context;
        mSiteId = siteId;
    }

    public void bindToService() {
        if (!ListenerUtil.mutListener.listen(23189)) {
            if (!mAttemptingToBind) {
                if (!ListenerUtil.mutListener.listen(23186)) {
                    mAttemptingToBind = true;
                }
                if (!ListenerUtil.mutListener.listen(23187)) {
                    mBindCalled = true;
                }
                Intent intent = new Intent(mContext, SuggestionService.class);
                if (!ListenerUtil.mutListener.listen(23188)) {
                    mContext.bindService(intent, this, Context.BIND_AUTO_CREATE);
                }
            }
        }
    }

    public void unbindFromService() {
        if (!ListenerUtil.mutListener.listen(23190)) {
            mAttemptingToBind = false;
        }
        if (!ListenerUtil.mutListener.listen(23193)) {
            if (mBindCalled) {
                if (!ListenerUtil.mutListener.listen(23191)) {
                    mContext.unbindService(this);
                }
                if (!ListenerUtil.mutListener.listen(23192)) {
                    mBindCalled = false;
                }
            }
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SuggestionService.SuggestionBinder b = (SuggestionService.SuggestionBinder) iBinder;
        SuggestionService suggestionService = b.getService();
        if (!ListenerUtil.mutListener.listen(23194)) {
            suggestionService.update(mSiteId);
        }
        if (!ListenerUtil.mutListener.listen(23195)) {
            mAttemptingToBind = false;
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
