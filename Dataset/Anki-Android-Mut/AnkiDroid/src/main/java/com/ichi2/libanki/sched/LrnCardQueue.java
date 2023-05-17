package com.ichi2.libanki.sched;

import java.util.Collections;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class LrnCardQueue extends CardQueue<LrnCard> {

    /**
     * Whether the queue already contains its current expected value.
     * If it's not the case, then we won't add cards reviewed immediately and wait for a filling to occur.
     */
    private boolean mIsFilled = false;

    public LrnCardQueue(AbstractSched sched) {
        super(sched);
    }

    public void add(long due, long cid) {
        if (!ListenerUtil.mutListener.listen(14631)) {
            add(new LrnCard(getCol(), due, cid));
        }
    }

    public void sort() {
        if (!ListenerUtil.mutListener.listen(14632)) {
            Collections.sort(getQueue());
        }
    }

    public long getFirstDue() {
        return getQueue().getFirst().getDue();
    }

    @Override
    public void clear() {
        if (!ListenerUtil.mutListener.listen(14633)) {
            super.clear();
        }
        if (!ListenerUtil.mutListener.listen(14634)) {
            mIsFilled = false;
        }
    }

    public void setFilled() {
        if (!ListenerUtil.mutListener.listen(14635)) {
            mIsFilled = true;
        }
    }

    public boolean isFilled() {
        return mIsFilled;
    }
}
