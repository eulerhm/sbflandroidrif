/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.voip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import androidx.annotation.AnyThread;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The call state is a combination of the plain state and a call ID.
 *
 * The call state is global, there should not be multiple instances of this.
 *
 * This is a pure data holder, no validation is being done.
 *
 * This class is thread safe.
 */
@AnyThread
public class CallState {

    private static final Logger logger = LoggerFactory.getLogger(CallState.class);

    /**
     *  No call is currently active.
     */
    static final int IDLE = 0;

    /**
     *  This state only happens on the callee side,
     *  before the call was accepted.
     */
    static final int RINGING = 1;

    /**
     *  A call was accepted and is being setup.
     */
    static final int INITIALIZING = 2;

    /**
     *  A call is currently ongoing.
     */
    static final int CALLING = 3;

    /**
     *  A call is being disconnected.
     */
    static final int DISCONNECTING = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ IDLE, RINGING, INITIALIZING, CALLING, DISCONNECTING })
    @interface State {
    }

    private final AtomicInteger state = new AtomicInteger(IDLE);

    private final AtomicLong callId = new AtomicLong(0);

    /**
     *  Whether or not an answer for this call ID has been received yet.
     *
     *  This flag is reset when the state transitions to DISCONNECTING or IDLE.
     */
    private volatile boolean answerReceived = false;

    /**
     *  The incoming call counter is a transitional variable that is used as long as the call ID has
     *  not yet been fully rolled out. It is being used to avoid problems if two calls are using the
     *  default call ID "0". The counter is only incremented for incoming calls (in setStateRinging).
     */
    @Deprecated
    private final AtomicLong incomingCallCounter = new AtomicLong(0);

    @Override
    public synchronized String toString() {
        return "CallState{" + "state=" + getStateName(this.state.get()) + ", callId=" + this.callId.get() + '}';
    }

    public boolean isIdle() {
        return (ListenerUtil.mutListener.listen(60720) ? (this.state.get() >= IDLE) : (ListenerUtil.mutListener.listen(60719) ? (this.state.get() <= IDLE) : (ListenerUtil.mutListener.listen(60718) ? (this.state.get() > IDLE) : (ListenerUtil.mutListener.listen(60717) ? (this.state.get() < IDLE) : (ListenerUtil.mutListener.listen(60716) ? (this.state.get() != IDLE) : (this.state.get() == IDLE))))));
    }

    public synchronized boolean isRinging() {
        return (ListenerUtil.mutListener.listen(60725) ? (this.state.get() >= RINGING) : (ListenerUtil.mutListener.listen(60724) ? (this.state.get() <= RINGING) : (ListenerUtil.mutListener.listen(60723) ? (this.state.get() > RINGING) : (ListenerUtil.mutListener.listen(60722) ? (this.state.get() < RINGING) : (ListenerUtil.mutListener.listen(60721) ? (this.state.get() != RINGING) : (this.state.get() == RINGING))))));
    }

    public synchronized boolean isInitializing() {
        return (ListenerUtil.mutListener.listen(60730) ? (this.state.get() >= INITIALIZING) : (ListenerUtil.mutListener.listen(60729) ? (this.state.get() <= INITIALIZING) : (ListenerUtil.mutListener.listen(60728) ? (this.state.get() > INITIALIZING) : (ListenerUtil.mutListener.listen(60727) ? (this.state.get() < INITIALIZING) : (ListenerUtil.mutListener.listen(60726) ? (this.state.get() != INITIALIZING) : (this.state.get() == INITIALIZING))))));
    }

    public synchronized boolean isCalling() {
        return (ListenerUtil.mutListener.listen(60735) ? (this.state.get() >= CALLING) : (ListenerUtil.mutListener.listen(60734) ? (this.state.get() <= CALLING) : (ListenerUtil.mutListener.listen(60733) ? (this.state.get() > CALLING) : (ListenerUtil.mutListener.listen(60732) ? (this.state.get() < CALLING) : (ListenerUtil.mutListener.listen(60731) ? (this.state.get() != CALLING) : (this.state.get() == CALLING))))));
    }

    public synchronized boolean isDisconnecting() {
        return (ListenerUtil.mutListener.listen(60740) ? (this.state.get() >= DISCONNECTING) : (ListenerUtil.mutListener.listen(60739) ? (this.state.get() <= DISCONNECTING) : (ListenerUtil.mutListener.listen(60738) ? (this.state.get() > DISCONNECTING) : (ListenerUtil.mutListener.listen(60737) ? (this.state.get() < DISCONNECTING) : (ListenerUtil.mutListener.listen(60736) ? (this.state.get() != DISCONNECTING) : (this.state.get() == DISCONNECTING))))));
    }

    /**
     *  Return the current Call ID.
     *
     *  Note: Depending on the use case you might want to use {@link #getStateSnapshot()} instead.
     */
    public long getCallId() {
        return this.callId.get();
    }

    /**
     *  Return the incoming call counter.
     */
    @Deprecated
    public long getIncomingCallCounter() {
        return this.incomingCallCounter.get();
    }

    /**
     *  Return whether an answer was already received for this call.
     */
    public synchronized boolean answerReceived() {
        return this.answerReceived;
    }

    /**
     *  Return an immutable snapshot of the current state.
     *  This allows reading the state and the Call ID independently without locking.
     */
    @NonNull
    public synchronized CallStateSnapshot getStateSnapshot() {
        return new CallStateSnapshot(this.state.get(), this.callId.get(), this.incomingCallCounter.get());
    }

    /**
     *  Return the state name for the specified state.
     */
    @NonNull
    static String getStateName(@State int state) {
        switch(state) {
            case CallState.IDLE:
                return "IDLE";
            case CallState.RINGING:
                return "RINGING";
            case CallState.INITIALIZING:
                return "INITIALIZING";
            case CallState.CALLING:
                return "CALLING";
            case CallState.DISCONNECTING:
                return "DISCONNECTING";
            default:
                return "UNKNOWN";
        }
    }

    public synchronized void setIdle() {
        if (!ListenerUtil.mutListener.listen(60741)) {
            this.state.set(IDLE);
        }
        if (!ListenerUtil.mutListener.listen(60742)) {
            this.callId.set(0);
        }
        if (!ListenerUtil.mutListener.listen(60743)) {
            this.answerReceived = false;
        }
    }

    public synchronized void setRinging(long callId) {
        @State
        final int state = this.state.get();
        if (!ListenerUtil.mutListener.listen(60750)) {
            if ((ListenerUtil.mutListener.listen(60748) ? (this.state.get() >= IDLE) : (ListenerUtil.mutListener.listen(60747) ? (this.state.get() <= IDLE) : (ListenerUtil.mutListener.listen(60746) ? (this.state.get() > IDLE) : (ListenerUtil.mutListener.listen(60745) ? (this.state.get() < IDLE) : (ListenerUtil.mutListener.listen(60744) ? (this.state.get() == IDLE) : (this.state.get() != IDLE))))))) {
                if (!ListenerUtil.mutListener.listen(60749)) {
                    logger.warn("Call state change from {} to RINGING", getStateName(state));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60751)) {
            this.state.set(RINGING);
        }
        if (!ListenerUtil.mutListener.listen(60758)) {
            if ((ListenerUtil.mutListener.listen(60756) ? (this.callId.get() >= 0) : (ListenerUtil.mutListener.listen(60755) ? (this.callId.get() <= 0) : (ListenerUtil.mutListener.listen(60754) ? (this.callId.get() > 0) : (ListenerUtil.mutListener.listen(60753) ? (this.callId.get() < 0) : (ListenerUtil.mutListener.listen(60752) ? (this.callId.get() == 0) : (this.callId.get() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(60757)) {
                    logger.warn("Call ID changed from {} to {}", this.callId, callId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60759)) {
            this.callId.set(callId);
        }
        if (!ListenerUtil.mutListener.listen(60760)) {
            this.incomingCallCounter.incrementAndGet();
        }
    }

    public synchronized void setInitializing(long callId) {
        @State
        final int state = this.state.get();
        if (!ListenerUtil.mutListener.listen(60773)) {
            if ((ListenerUtil.mutListener.listen(60771) ? ((ListenerUtil.mutListener.listen(60765) ? (state >= RINGING) : (ListenerUtil.mutListener.listen(60764) ? (state <= RINGING) : (ListenerUtil.mutListener.listen(60763) ? (state > RINGING) : (ListenerUtil.mutListener.listen(60762) ? (state < RINGING) : (ListenerUtil.mutListener.listen(60761) ? (state == RINGING) : (state != RINGING)))))) || (ListenerUtil.mutListener.listen(60770) ? (state >= IDLE) : (ListenerUtil.mutListener.listen(60769) ? (state <= IDLE) : (ListenerUtil.mutListener.listen(60768) ? (state > IDLE) : (ListenerUtil.mutListener.listen(60767) ? (state < IDLE) : (ListenerUtil.mutListener.listen(60766) ? (state == IDLE) : (state != IDLE))))))) : ((ListenerUtil.mutListener.listen(60765) ? (state >= RINGING) : (ListenerUtil.mutListener.listen(60764) ? (state <= RINGING) : (ListenerUtil.mutListener.listen(60763) ? (state > RINGING) : (ListenerUtil.mutListener.listen(60762) ? (state < RINGING) : (ListenerUtil.mutListener.listen(60761) ? (state == RINGING) : (state != RINGING)))))) && (ListenerUtil.mutListener.listen(60770) ? (state >= IDLE) : (ListenerUtil.mutListener.listen(60769) ? (state <= IDLE) : (ListenerUtil.mutListener.listen(60768) ? (state > IDLE) : (ListenerUtil.mutListener.listen(60767) ? (state < IDLE) : (ListenerUtil.mutListener.listen(60766) ? (state == IDLE) : (state != IDLE))))))))) {
                if (!ListenerUtil.mutListener.listen(60772)) {
                    logger.warn("Call state change from {} to INITIALIZING", getStateName(state));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60774)) {
            this.state.set(INITIALIZING);
        }
        final long oldCallId = this.callId.get();
        if (!ListenerUtil.mutListener.listen(60787)) {
            if ((ListenerUtil.mutListener.listen(60785) ? ((ListenerUtil.mutListener.listen(60779) ? (oldCallId >= 0) : (ListenerUtil.mutListener.listen(60778) ? (oldCallId <= 0) : (ListenerUtil.mutListener.listen(60777) ? (oldCallId > 0) : (ListenerUtil.mutListener.listen(60776) ? (oldCallId < 0) : (ListenerUtil.mutListener.listen(60775) ? (oldCallId == 0) : (oldCallId != 0)))))) || (ListenerUtil.mutListener.listen(60784) ? (oldCallId >= callId) : (ListenerUtil.mutListener.listen(60783) ? (oldCallId <= callId) : (ListenerUtil.mutListener.listen(60782) ? (oldCallId > callId) : (ListenerUtil.mutListener.listen(60781) ? (oldCallId < callId) : (ListenerUtil.mutListener.listen(60780) ? (oldCallId == callId) : (oldCallId != callId))))))) : ((ListenerUtil.mutListener.listen(60779) ? (oldCallId >= 0) : (ListenerUtil.mutListener.listen(60778) ? (oldCallId <= 0) : (ListenerUtil.mutListener.listen(60777) ? (oldCallId > 0) : (ListenerUtil.mutListener.listen(60776) ? (oldCallId < 0) : (ListenerUtil.mutListener.listen(60775) ? (oldCallId == 0) : (oldCallId != 0)))))) && (ListenerUtil.mutListener.listen(60784) ? (oldCallId >= callId) : (ListenerUtil.mutListener.listen(60783) ? (oldCallId <= callId) : (ListenerUtil.mutListener.listen(60782) ? (oldCallId > callId) : (ListenerUtil.mutListener.listen(60781) ? (oldCallId < callId) : (ListenerUtil.mutListener.listen(60780) ? (oldCallId == callId) : (oldCallId != callId))))))))) {
                if (!ListenerUtil.mutListener.listen(60786)) {
                    logger.warn("Call ID changed from {} to {}", oldCallId, callId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60788)) {
            this.callId.set(callId);
        }
    }

    public synchronized void setAnswerReceived() {
        if (!ListenerUtil.mutListener.listen(60789)) {
            this.answerReceived = true;
        }
    }

    public synchronized void setCalling(long callId) {
        @State
        final int state = this.state.get();
        if (!ListenerUtil.mutListener.listen(60796)) {
            if ((ListenerUtil.mutListener.listen(60794) ? (state >= INITIALIZING) : (ListenerUtil.mutListener.listen(60793) ? (state <= INITIALIZING) : (ListenerUtil.mutListener.listen(60792) ? (state > INITIALIZING) : (ListenerUtil.mutListener.listen(60791) ? (state < INITIALIZING) : (ListenerUtil.mutListener.listen(60790) ? (state == INITIALIZING) : (state != INITIALIZING))))))) {
                if (!ListenerUtil.mutListener.listen(60795)) {
                    logger.warn("Call state change from {} to CALLING", getStateName(state));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60797)) {
            this.state.set(CALLING);
        }
        final long oldCallId = this.callId.get();
        if (!ListenerUtil.mutListener.listen(60804)) {
            if ((ListenerUtil.mutListener.listen(60802) ? (oldCallId >= callId) : (ListenerUtil.mutListener.listen(60801) ? (oldCallId <= callId) : (ListenerUtil.mutListener.listen(60800) ? (oldCallId > callId) : (ListenerUtil.mutListener.listen(60799) ? (oldCallId < callId) : (ListenerUtil.mutListener.listen(60798) ? (oldCallId == callId) : (oldCallId != callId))))))) {
                if (!ListenerUtil.mutListener.listen(60803)) {
                    logger.warn("Call ID changed from {} to {}", oldCallId, callId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60805)) {
            this.callId.set(callId);
        }
    }

    public synchronized void setDisconnecting(long callId) {
        @State
        final int state = this.state.get();
        if (!ListenerUtil.mutListener.listen(60818)) {
            if ((ListenerUtil.mutListener.listen(60816) ? ((ListenerUtil.mutListener.listen(60810) ? (state >= INITIALIZING) : (ListenerUtil.mutListener.listen(60809) ? (state <= INITIALIZING) : (ListenerUtil.mutListener.listen(60808) ? (state > INITIALIZING) : (ListenerUtil.mutListener.listen(60807) ? (state < INITIALIZING) : (ListenerUtil.mutListener.listen(60806) ? (state == INITIALIZING) : (state != INITIALIZING)))))) || (ListenerUtil.mutListener.listen(60815) ? (state >= CALLING) : (ListenerUtil.mutListener.listen(60814) ? (state <= CALLING) : (ListenerUtil.mutListener.listen(60813) ? (state > CALLING) : (ListenerUtil.mutListener.listen(60812) ? (state < CALLING) : (ListenerUtil.mutListener.listen(60811) ? (state == CALLING) : (state != CALLING))))))) : ((ListenerUtil.mutListener.listen(60810) ? (state >= INITIALIZING) : (ListenerUtil.mutListener.listen(60809) ? (state <= INITIALIZING) : (ListenerUtil.mutListener.listen(60808) ? (state > INITIALIZING) : (ListenerUtil.mutListener.listen(60807) ? (state < INITIALIZING) : (ListenerUtil.mutListener.listen(60806) ? (state == INITIALIZING) : (state != INITIALIZING)))))) && (ListenerUtil.mutListener.listen(60815) ? (state >= CALLING) : (ListenerUtil.mutListener.listen(60814) ? (state <= CALLING) : (ListenerUtil.mutListener.listen(60813) ? (state > CALLING) : (ListenerUtil.mutListener.listen(60812) ? (state < CALLING) : (ListenerUtil.mutListener.listen(60811) ? (state == CALLING) : (state != CALLING))))))))) {
                if (!ListenerUtil.mutListener.listen(60817)) {
                    logger.warn("Call state change from {} to DISCONNECTING", getStateName(state));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60819)) {
            this.state.set(DISCONNECTING);
        }
        final long oldCallId = this.callId.get();
        if (!ListenerUtil.mutListener.listen(60826)) {
            if ((ListenerUtil.mutListener.listen(60824) ? (oldCallId >= callId) : (ListenerUtil.mutListener.listen(60823) ? (oldCallId <= callId) : (ListenerUtil.mutListener.listen(60822) ? (oldCallId > callId) : (ListenerUtil.mutListener.listen(60821) ? (oldCallId < callId) : (ListenerUtil.mutListener.listen(60820) ? (oldCallId == callId) : (oldCallId != callId))))))) {
                if (!ListenerUtil.mutListener.listen(60825)) {
                    logger.warn("Call ID changed from {} to {}", oldCallId, callId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60827)) {
            this.callId.set(callId);
        }
        if (!ListenerUtil.mutListener.listen(60828)) {
            this.answerReceived = false;
        }
    }
}
