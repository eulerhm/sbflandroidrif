package org.wordpress.android.util;

import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class RateLimitedTask {

    private Date mLastUpdate;

    private int mMinRateInSeconds;

    public RateLimitedTask(int minRateInSeconds) {
        if (!ListenerUtil.mutListener.listen(27766)) {
            mMinRateInSeconds = minRateInSeconds;
        }
    }

    public void forceLastUpdate() {
        if (!ListenerUtil.mutListener.listen(27767)) {
            mLastUpdate = new Date();
        }
    }

    public synchronized boolean forceRun() {
        if (!ListenerUtil.mutListener.listen(27769)) {
            if (run()) {
                if (!ListenerUtil.mutListener.listen(27768)) {
                    mLastUpdate = new Date();
                }
                return true;
            }
        }
        return false;
    }

    public synchronized boolean runIfNotLimited() {
        Date now = new Date();
        if (!ListenerUtil.mutListener.listen(27778)) {
            if ((ListenerUtil.mutListener.listen(27775) ? (mLastUpdate == null && (ListenerUtil.mutListener.listen(27774) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) <= mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27773) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) > mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27772) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) < mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27771) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) != mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27770) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) == mMinRateInSeconds) : (DateTimeUtils.secondsBetween(now, mLastUpdate) >= mMinRateInSeconds))))))) : (mLastUpdate == null || (ListenerUtil.mutListener.listen(27774) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) <= mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27773) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) > mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27772) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) < mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27771) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) != mMinRateInSeconds) : (ListenerUtil.mutListener.listen(27770) ? (DateTimeUtils.secondsBetween(now, mLastUpdate) == mMinRateInSeconds) : (DateTimeUtils.secondsBetween(now, mLastUpdate) >= mMinRateInSeconds))))))))) {
                if (!ListenerUtil.mutListener.listen(27777)) {
                    if (run()) {
                        if (!ListenerUtil.mutListener.listen(27776)) {
                            mLastUpdate = now;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected abstract boolean run();
}
