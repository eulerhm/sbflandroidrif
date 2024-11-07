package com.ichi2.libanki.sched;

import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class CardQueue<T extends Card.Cache> {

    // sched.getCol is null.
    private final AbstractSched mSched;

    private final LinkedList<T> mQueue = new LinkedList<>();

    public CardQueue(AbstractSched sched) {
        mSched = sched;
    }

    public void loadFirstCard() {
        if (!ListenerUtil.mutListener.listen(14509)) {
            if (!mQueue.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(14508)) {
                    // No nead to reload. If the card was changed, reset would have been called and emptied the queue
                    mQueue.get(0).loadQA(false, false);
                }
            }
        }
    }

    public Card removeFirstCard() throws NoSuchElementException {
        return mQueue.remove().getCard();
    }

    public boolean remove(long cid) {
        // CardCache and LrnCache with the same id will be considered as equal so it's a valid implementation.
        return mQueue.remove(new Card.Cache(getCol(), cid));
    }

    public void add(T elt) {
        if (!ListenerUtil.mutListener.listen(14510)) {
            mQueue.add(elt);
        }
    }

    public void clear() {
        if (!ListenerUtil.mutListener.listen(14511)) {
            mQueue.clear();
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isEmpty() {
        return mQueue.isEmpty();
    }

    public int size() {
        return mQueue.size();
    }

    protected LinkedList<T> getQueue() {
        return mQueue;
    }

    public void shuffle(Random r) {
        if (!ListenerUtil.mutListener.listen(14512)) {
            Collections.shuffle(mQueue, r);
        }
    }

    public ListIterator<T> listIterator() {
        return mQueue.listIterator();
    }

    protected Collection getCol() {
        return mSched.getCol();
    }
}
