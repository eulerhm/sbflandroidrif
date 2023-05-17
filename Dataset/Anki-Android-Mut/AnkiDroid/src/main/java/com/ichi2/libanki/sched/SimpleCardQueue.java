package com.ichi2.libanki.sched;

import com.ichi2.libanki.Card;
import androidx.annotation.VisibleForTesting;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class SimpleCardQueue extends CardQueue<Card.Cache> {

    public SimpleCardQueue(AbstractSched sched) {
        super(sched);
    }

    public void add(long id) {
        if (!ListenerUtil.mutListener.listen(17352)) {
            add(new Card.Cache(getCol(), id));
        }
    }
}
