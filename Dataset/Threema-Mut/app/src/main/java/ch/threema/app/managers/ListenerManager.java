/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ch.threema.app.listeners.AppIconListener;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.listeners.ChatListener;
import ch.threema.app.listeners.ContactCountListener;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.listeners.ContactTypingListener;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.listeners.DistributionListListener;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.listeners.MessageListener;
import ch.threema.app.listeners.MessagePlayerListener;
import ch.threema.app.listeners.NewSyncedContactsListener;
import ch.threema.app.listeners.PreferenceListener;
import ch.threema.app.listeners.ProfileListener;
import ch.threema.app.listeners.QRCodeScanListener;
import ch.threema.app.listeners.SMSVerificationListener;
import ch.threema.app.listeners.ServerMessageListener;
import ch.threema.app.listeners.SynchronizeContactsListener;
import ch.threema.app.listeners.ThreemaSafeListener;
import ch.threema.app.listeners.VoipCallListener;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ListenerManager {

    private static final Logger logger = LoggerFactory.getLogger(ListenerManager.class);

    public interface HandleListener<T> {

        void handle(T listener);
    }

    public static class TypedListenerManager<T> {

        private final List<T> listeners = new ArrayList<>();

        private final Map<String, Integer> tags = new HashMap<>();

        private boolean enabled = true;

        public void add(T l, String tag) {
            synchronized (this.listeners) {
                Integer pos = this.tags.get(tag);
                if (!ListenerUtil.mutListener.listen(29146)) {
                    if ((ListenerUtil.mutListener.listen(29144) ? (pos != null || (ListenerUtil.mutListener.listen(29143) ? (pos <= 0) : (ListenerUtil.mutListener.listen(29142) ? (pos > 0) : (ListenerUtil.mutListener.listen(29141) ? (pos < 0) : (ListenerUtil.mutListener.listen(29140) ? (pos != 0) : (ListenerUtil.mutListener.listen(29139) ? (pos == 0) : (pos >= 0))))))) : (pos != null && (ListenerUtil.mutListener.listen(29143) ? (pos <= 0) : (ListenerUtil.mutListener.listen(29142) ? (pos > 0) : (ListenerUtil.mutListener.listen(29141) ? (pos < 0) : (ListenerUtil.mutListener.listen(29140) ? (pos != 0) : (ListenerUtil.mutListener.listen(29139) ? (pos == 0) : (pos >= 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(29145)) {
                            // remove listener first
                            this.listeners.remove(this.listeners.get(pos));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(29147)) {
                    addInternal(this.listeners, l, false);
                }
                if (!ListenerUtil.mutListener.listen(29152)) {
                    // save tagged position
                    this.tags.put(tag, (ListenerUtil.mutListener.listen(29151) ? (this.listeners.size() % 1) : (ListenerUtil.mutListener.listen(29150) ? (this.listeners.size() / 1) : (ListenerUtil.mutListener.listen(29149) ? (this.listeners.size() * 1) : (ListenerUtil.mutListener.listen(29148) ? (this.listeners.size() + 1) : (this.listeners.size() - 1))))));
                }
            }
        }

        public void add(T l) {
            if (!ListenerUtil.mutListener.listen(29153)) {
                addInternal(this.listeners, l, false);
            }
        }

        public void add(T l, boolean higherPriority) {
            if (!ListenerUtil.mutListener.listen(29154)) {
                addInternal(this.listeners, l, higherPriority);
            }
        }

        public void remove(T l) {
            if (!ListenerUtil.mutListener.listen(29155)) {
                removeInternal(this.listeners, l);
            }
        }

        /**
         *  Remove all listeners.
         */
        public void clear() {
            synchronized (this.listeners) {
                if (!ListenerUtil.mutListener.listen(29156)) {
                    this.listeners.clear();
                }
            }
        }

        /**
         *  Return whether the specified listener was already added.
         */
        public boolean contains(T l) {
            return (ListenerUtil.mutListener.listen(29157) ? (l != null || this.listeners.contains(l)) : (l != null && this.listeners.contains(l)));
        }

        public void handle(ListenerManager.HandleListener<T> handleListener) {
            if (!ListenerUtil.mutListener.listen(29163)) {
                if ((ListenerUtil.mutListener.listen(29158) ? (handleListener != null || this.enabled) : (handleListener != null && this.enabled))) {
                    // Therefore we iterate over a copy of the listeners, to avoid that problem.
                    final List<T> listenersCopy;
                    synchronized (this.listeners) {
                        listenersCopy = new ArrayList<>(this.listeners);
                    }
                    if (!ListenerUtil.mutListener.listen(29162)) {
                        {
                            long _loopCounter189 = 0;
                            // Run the handle method on every listener
                            for (T listener : listenersCopy) {
                                ListenerUtil.loopListener.listen("_loopCounter189", ++_loopCounter189);
                                if (!ListenerUtil.mutListener.listen(29161)) {
                                    if (listener != null) {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(29160)) {
                                                handleListener.handle(listener);
                                            }
                                        } catch (Exception x) {
                                            if (!ListenerUtil.mutListener.listen(29159)) {
                                                logger.error("cannot handle event", x);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private <T> void addInternal(List<T> holder, T listener, boolean higherPriority) {
            if (!ListenerUtil.mutListener.listen(29169)) {
                if ((ListenerUtil.mutListener.listen(29164) ? (holder != null || listener != null) : (holder != null && listener != null))) {
                    synchronized (holder) {
                        if (!ListenerUtil.mutListener.listen(29168)) {
                            if (!holder.contains(listener)) {
                                if (!ListenerUtil.mutListener.listen(29167)) {
                                    if (higherPriority) {
                                        if (!ListenerUtil.mutListener.listen(29166)) {
                                            // add first!
                                            holder.add(0, listener);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(29165)) {
                                            holder.add(listener);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private <T> void removeInternal(List<T> holder, T listener) {
            if (!ListenerUtil.mutListener.listen(29172)) {
                if ((ListenerUtil.mutListener.listen(29170) ? (holder != null || listener != null) : (holder != null && listener != null))) {
                    synchronized (holder) {
                        if (!ListenerUtil.mutListener.listen(29171)) {
                            holder.remove(listener);
                        }
                    }
                }
            }
        }

        public void enabled(boolean enabled) {
            if (!ListenerUtil.mutListener.listen(29175)) {
                if (this.enabled != enabled) {
                    if (!ListenerUtil.mutListener.listen(29173)) {
                        logger.debug(this.getClass() + " " + (enabled ? "enabled" : "disabled"));
                    }
                    if (!ListenerUtil.mutListener.listen(29174)) {
                        this.enabled = enabled;
                    }
                }
            }
        }

        public boolean isEnabled() {
            return this.enabled;
        }
    }

    public static final TypedListenerManager<ConversationListener> conversationListeners = new TypedListenerManager<ConversationListener>();

    public static final TypedListenerManager<ContactListener> contactListeners = new TypedListenerManager<ContactListener>();

    public static final TypedListenerManager<ContactTypingListener> contactTypingListeners = new TypedListenerManager<ContactTypingListener>();

    public static final TypedListenerManager<DistributionListListener> distributionListListeners = new TypedListenerManager<DistributionListListener>();

    public static final TypedListenerManager<GroupListener> groupListeners = new TypedListenerManager<GroupListener>();

    public static final TypedListenerManager<MessageListener> messageListeners = new TypedListenerManager<MessageListener>();

    public static final TypedListenerManager<PreferenceListener> preferenceListeners = new TypedListenerManager<PreferenceListener>();

    public static final TypedListenerManager<ServerMessageListener> serverMessageListeners = new TypedListenerManager<ServerMessageListener>();

    public static final TypedListenerManager<SynchronizeContactsListener> synchronizeContactsListeners = new TypedListenerManager<SynchronizeContactsListener>();

    public static final TypedListenerManager<ContactSettingsListener> contactSettingsListeners = new TypedListenerManager<ContactSettingsListener>();

    public static final TypedListenerManager<BallotListener> ballotListeners = new TypedListenerManager<BallotListener>();

    public static final TypedListenerManager<BallotVoteListener> ballotVoteListeners = new TypedListenerManager<BallotVoteListener>();

    public static final TypedListenerManager<SMSVerificationListener> smsVerificationListeners = new TypedListenerManager<SMSVerificationListener>();

    public static final TypedListenerManager<AppIconListener> appIconListeners = new TypedListenerManager<AppIconListener>();

    public static final TypedListenerManager<ProfileListener> profileListeners = new TypedListenerManager<ProfileListener>();

    public static final TypedListenerManager<VoipCallListener> voipCallListeners = new TypedListenerManager<VoipCallListener>();

    public static final TypedListenerManager<ThreemaSafeListener> threemaSafeListeners = new TypedListenerManager<ThreemaSafeListener>();

    public static final TypedListenerManager<ChatListener> chatListener = new TypedListenerManager<>();

    public static final TypedListenerManager<MessagePlayerListener> messagePlayerListener = new TypedListenerManager<>();

    public static final TypedListenerManager<NewSyncedContactsListener> newSyncedContactListener = new TypedListenerManager<>();

    public static final TypedListenerManager<QRCodeScanListener> qrCodeScanListener = new TypedListenerManager<>();

    public static final TypedListenerManager<ContactCountListener> contactCountListener = new TypedListenerManager<>();
}
