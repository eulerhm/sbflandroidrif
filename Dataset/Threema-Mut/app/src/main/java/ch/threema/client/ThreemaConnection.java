/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.client;

import androidx.annotation.NonNull;
import com.neilalexander.jnacl.NaCl;
import org.apache.commons.io.EndianUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import androidx.annotation.WorkerThread;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class ThreemaConnection implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaConnection.class);

    private static final int IDENTITY_LEN = 8;

    private static final int COOKIE_LEN = 16;

    private static final int SERVER_HELLO_BOXLEN = NaCl.PUBLICKEYBYTES + COOKIE_LEN + NaCl.BOXOVERHEAD;

    private static final int VOUCH_LEN = NaCl.PUBLICKEYBYTES;

    private static final int VERSION_LEN = 32;

    private static final int LOGIN_LEN = IDENTITY_LEN + VERSION_LEN + COOKIE_LEN + NaCl.NONCEBYTES + VOUCH_LEN + NaCl.BOXOVERHEAD;

    private static final int LOGIN_ACK_RESERVED_LEN = 16;

    private static final int LOGIN_ACK_LEN = LOGIN_ACK_RESERVED_LEN + NaCl.BOXOVERHEAD;

    /* Delegate objects */
    private final IdentityStoreInterface identityStore;

    private final NonceFactory nonceFactory;

    private MessageProcessorInterface messageProcessor;

    /* Permanent data */
    private final String serverNamePrefix;

    private final String serverNameSuffix;

    private final int serverPort;

    private final int serverPortAlt;

    private final boolean useServerGroup;

    private final byte[] serverPubKey;

    private final byte[] serverPubKeyAlt;

    /* Temporary data for each individual TCP connection */
    private volatile Socket socket;

    private byte[] clientTempKeyPub;

    private byte[] clientTempKeySec;

    private SenderThread senderThread;

    private int lastSentEchoSeq;

    private int lastRcvdEchoSeq;

    /* Connection state dependent objects */
    private volatile ConnectionState state;

    private volatile Thread curThread;

    private volatile boolean running;

    @NonNull
    private final AtomicInteger connectionNumber = new AtomicInteger();

    private int reconnectAttempts;

    private int curSocketAddressIndex;

    private ArrayList<InetSocketAddress> serverSocketAddresses;

    /* Helper objects */
    private final SecureRandom random;

    private final Timer timer;

    private Date clientTempKeyGenTime;

    private int pushTokenType;

    private String pushToken;

    private final Set<String> lastAlertMessages;

    private Version version;

    private int anotherConnectionCount;

    /* Listeners */
    private final Set<MessageAckListener> ackListeners;

    private final Set<ConnectionStateListener> connectionStateListeners;

    private final Set<QueueSendCompleteListener> queueSendCompleteListeners;

    /**
     *  Create a new ThreemaConnection.
     *
     *  @param identityStore the identity store to use for login
     *  @param serverNamePrefix prefix for server name (prepended to server group)
     *  @param serverNameSuffix suffix for server name (appended to server group)
     *  @param serverPort default server port
     *  @param serverPortAlt alternate server port (used if the default port does not work)
     *  @param ipv6 whether to use IPv4+IPv6 for connection, or only IPv4
     *  @param serverPubKey server public key
     *  @param serverPubKeyAlt alternate server public key
     *  @param useServerGroup whether to use the server group
     */
    public ThreemaConnection(IdentityStoreInterface identityStore, NonceFactory nonceFactory, String serverNamePrefix, String serverNameIPv6Prefix, String serverNameSuffix, int serverPort, int serverPortAlt, boolean ipv6, byte[] serverPubKey, byte[] serverPubKeyAlt, boolean useServerGroup) {
        this.identityStore = identityStore;
        this.nonceFactory = nonceFactory;
        this.serverNamePrefix = (ipv6 ? serverNameIPv6Prefix : "") + serverNamePrefix;
        this.serverNameSuffix = serverNameSuffix;
        this.serverPort = serverPort;
        this.serverPortAlt = serverPortAlt;
        this.serverPubKey = serverPubKey;
        this.serverPubKeyAlt = serverPubKeyAlt;
        this.useServerGroup = useServerGroup;
        if (!ListenerUtil.mutListener.listen(68766)) {
            this.curSocketAddressIndex = 0;
        }
        if (!ListenerUtil.mutListener.listen(68767)) {
            this.serverSocketAddresses = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(68768)) {
            this.connectionNumber.set(0);
        }
        random = new SecureRandom();
        timer = new Timer(/*"ThreemaConnectionTimer", */
        true);
        ackListeners = new HashSet<>();
        connectionStateListeners = new HashSet<>();
        queueSendCompleteListeners = new CopyOnWriteArraySet<>();
        lastAlertMessages = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(68769)) {
            state = ConnectionState.DISCONNECTED;
        }
        if (!ListenerUtil.mutListener.listen(68770)) {
            version = new Version();
        }
    }

    public MessageProcessorInterface getMessageProcessor() {
        return messageProcessor;
    }

    public void setMessageProcessor(MessageProcessorInterface messageProcessor) {
        if (!ListenerUtil.mutListener.listen(68771)) {
            this.messageProcessor = messageProcessor;
        }
    }

    private void getInetAdresses() throws UnknownHostException, ExecutionException, InterruptedException {
        ArrayList<InetSocketAddress> addresses = new ArrayList<>();
        String serverHost = serverNamePrefix + (useServerGroup ? identityStore.getServerGroup() : ".") + serverNameSuffix;
        if (!ListenerUtil.mutListener.listen(68787)) {
            if (ProxyAwareSocketFactory.shouldUseProxy(serverHost, serverPort)) {
                if (!ListenerUtil.mutListener.listen(68785)) {
                    // Create unresolved addresses for proxy
                    addresses.add(InetSocketAddress.createUnresolved(serverHost, serverPort));
                }
                if (!ListenerUtil.mutListener.listen(68786)) {
                    addresses.add(InetSocketAddress.createUnresolved(serverHost, serverPortAlt));
                }
            } else {
                InetAddress[] inetAddresses = AsyncResolver.getAllByName(serverHost);
                if (!ListenerUtil.mutListener.listen(68777)) {
                    if ((ListenerUtil.mutListener.listen(68776) ? (inetAddresses.length >= 0) : (ListenerUtil.mutListener.listen(68775) ? (inetAddresses.length <= 0) : (ListenerUtil.mutListener.listen(68774) ? (inetAddresses.length > 0) : (ListenerUtil.mutListener.listen(68773) ? (inetAddresses.length < 0) : (ListenerUtil.mutListener.listen(68772) ? (inetAddresses.length != 0) : (inetAddresses.length == 0))))))) {
                        throw new UnknownHostException();
                    }
                }
                if (!ListenerUtil.mutListener.listen(68781)) {
                    Arrays.sort(inetAddresses, new Comparator<InetAddress>() {

                        @Override
                        public int compare(InetAddress o1, InetAddress o2) {
                            if (!ListenerUtil.mutListener.listen(68779)) {
                                if (o1 instanceof Inet6Address) {
                                    if (!ListenerUtil.mutListener.listen(68778)) {
                                        if (o2 instanceof Inet6Address) {
                                            return o1.getHostAddress().compareTo(o2.getHostAddress());
                                        } else {
                                            return -1;
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(68780)) {
                                if (o2 instanceof Inet4Address) {
                                    return o1.getHostAddress().compareTo(o2.getHostAddress());
                                }
                            }
                            return 1;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(68784)) {
                    {
                        long _loopCounter873 = 0;
                        for (InetAddress inetAddress : inetAddresses) {
                            ListenerUtil.loopListener.listen("_loopCounter873", ++_loopCounter873);
                            if (!ListenerUtil.mutListener.listen(68782)) {
                                addresses.add(new InetSocketAddress(inetAddress, serverPort));
                            }
                            if (!ListenerUtil.mutListener.listen(68783)) {
                                addresses.add(new InetSocketAddress(inetAddress, serverPortAlt));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68795)) {
            if ((ListenerUtil.mutListener.listen(68792) ? (addresses.size() >= serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68791) ? (addresses.size() <= serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68790) ? (addresses.size() > serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68789) ? (addresses.size() < serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68788) ? (addresses.size() == serverSocketAddresses.size()) : (addresses.size() != serverSocketAddresses.size()))))))) {
                if (!ListenerUtil.mutListener.listen(68793)) {
                    serverSocketAddresses = addresses;
                }
                if (!ListenerUtil.mutListener.listen(68794)) {
                    curSocketAddressIndex = 0;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(68810)) {
            {
                long _loopCounter874 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(68809) ? (i >= addresses.size()) : (ListenerUtil.mutListener.listen(68808) ? (i <= addresses.size()) : (ListenerUtil.mutListener.listen(68807) ? (i > addresses.size()) : (ListenerUtil.mutListener.listen(68806) ? (i != addresses.size()) : (ListenerUtil.mutListener.listen(68805) ? (i == addresses.size()) : (i < addresses.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter874", ++_loopCounter874);
                    if (!ListenerUtil.mutListener.listen(68804)) {
                        // we have switched between unresolved and resolved addresses.
                        if ((ListenerUtil.mutListener.listen(68801) ? ((ListenerUtil.mutListener.listen(68798) ? (((ListenerUtil.mutListener.listen(68796) ? (addresses.get(i).getAddress() == null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() == null && serverSocketAddresses.get(i).getAddress() != null))) && ((ListenerUtil.mutListener.listen(68797) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() == null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() == null)))) : (((ListenerUtil.mutListener.listen(68796) ? (addresses.get(i).getAddress() == null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() == null && serverSocketAddresses.get(i).getAddress() != null))) || ((ListenerUtil.mutListener.listen(68797) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() == null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() == null))))) && ((ListenerUtil.mutListener.listen(68800) ? ((ListenerUtil.mutListener.listen(68799) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() != null)) || !addresses.get(i).getAddress().getHostAddress().equals(serverSocketAddresses.get(i).getAddress().getHostAddress())) : ((ListenerUtil.mutListener.listen(68799) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() != null)) && !addresses.get(i).getAddress().getHostAddress().equals(serverSocketAddresses.get(i).getAddress().getHostAddress()))))) : ((ListenerUtil.mutListener.listen(68798) ? (((ListenerUtil.mutListener.listen(68796) ? (addresses.get(i).getAddress() == null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() == null && serverSocketAddresses.get(i).getAddress() != null))) && ((ListenerUtil.mutListener.listen(68797) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() == null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() == null)))) : (((ListenerUtil.mutListener.listen(68796) ? (addresses.get(i).getAddress() == null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() == null && serverSocketAddresses.get(i).getAddress() != null))) || ((ListenerUtil.mutListener.listen(68797) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() == null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() == null))))) || ((ListenerUtil.mutListener.listen(68800) ? ((ListenerUtil.mutListener.listen(68799) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() != null)) || !addresses.get(i).getAddress().getHostAddress().equals(serverSocketAddresses.get(i).getAddress().getHostAddress())) : ((ListenerUtil.mutListener.listen(68799) ? (addresses.get(i).getAddress() != null || serverSocketAddresses.get(i).getAddress() != null) : (addresses.get(i).getAddress() != null && serverSocketAddresses.get(i).getAddress() != null)) && !addresses.get(i).getAddress().getHostAddress().equals(serverSocketAddresses.get(i).getAddress().getHostAddress()))))))) {
                            if (!ListenerUtil.mutListener.listen(68802)) {
                                serverSocketAddresses = addresses;
                            }
                            if (!ListenerUtil.mutListener.listen(68803)) {
                                curSocketAddressIndex = 0;
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     *  Start the ThreemaConnection thread.
     */
    public synchronized void start() {
        if (!ListenerUtil.mutListener.listen(68811)) {
            if (curThread != null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(68812)) {
            running = true;
        }
        if (!ListenerUtil.mutListener.listen(68813)) {
            curThread = new Thread(this, "ThreemaConnection");
        }
        if (!ListenerUtil.mutListener.listen(68814)) {
            curThread.start();
        }
    }

    /**
     *  Stop the connection and wait for the connection thread to terminate.
     *
     *  Because this calls {@link Thread#join} (and thus blocks), it should only be called
     *  from a worker thread, not from the main thread.
     */
    @WorkerThread
    public synchronized void stop() throws InterruptedException {
        Thread myCurThread = curThread;
        if (!ListenerUtil.mutListener.listen(68815)) {
            if (myCurThread == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(68816)) {
            running = false;
        }
        /* must close socket, as interrupt() will not interrupt socket read */
        try {
            if (!ListenerUtil.mutListener.listen(68819)) {
                if (socket != null) {
                    if (!ListenerUtil.mutListener.listen(68818)) {
                        socket.close();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(68817)) {
                logger.warn("Ignored exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(68820)) {
            myCurThread.interrupt();
        }
        if (!ListenerUtil.mutListener.listen(68821)) {
            // TODO(ANDR-1216): THIS CALL IS CURRENTLY THE MOST PROMINENT CAUSE FOR ANRs :-(
            myCurThread.join();
        }
    }

    public boolean isRunning() {
        return (curThread != null);
    }

    public ConnectionState getConnectionState() {
        return state;
    }

    public boolean sendBoxedMessage(BoxedMessage boxedMessage) {
        if (!ListenerUtil.mutListener.listen(68822)) {
            logger.info("sendBoxedMessage " + boxedMessage.getMessageId());
        }
        return sendPayload(boxedMessage.makePayload());
    }

    /**
     *  Set the push token to be used by the server when pushing messages to this client. This method
     *  can be called no matter if the client is currently connected to the server.
     *
     *  @param pushTokenType the push token type (usually ProtocolDefines.PUSHTOKEN_TYPE_GCM)
     *  @param pushToken the new push token (or "registration ID" in case of GCM)
     */
    public void setPushToken(int pushTokenType, String pushToken) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(68823)) {
            /* new token - store and send it */
            this.pushTokenType = pushTokenType;
        }
        if (!ListenerUtil.mutListener.listen(68824)) {
            this.pushToken = pushToken;
        }
        if (!ListenerUtil.mutListener.listen(68826)) {
            if ((ListenerUtil.mutListener.listen(68825) ? (getConnectionState() != ConnectionState.LOGGEDIN && !sendPushToken()) : (getConnectionState() != ConnectionState.LOGGEDIN || !sendPushToken()))) {
                throw new ThreemaException("Unable to send / clear push token. Make sure you're online.");
            }
        }
    }

    public boolean sendPayload(Payload payload) {
        /* delegate to sender thread, if connected */
        SenderThread mySenderThread = senderThread;
        if (mySenderThread != null) {
            if (!ListenerUtil.mutListener.listen(68828)) {
                mySenderThread.sendPayload(payload);
            }
            return true;
        } else {
            if (!ListenerUtil.mutListener.listen(68827)) {
                logger.info("SenderThread not available");
            }
            return false;
        }
    }

    public Version getVersion() {
        return version;
    }

    /**
     *  Set the version object to be used for communicating the client version to the server.
     *  Defaults to a plain Version object that only includes generic Java information.
     *
     *  @param version
     */
    public void setVersion(Version version) {
        if (!ListenerUtil.mutListener.listen(68829)) {
            this.version = version;
        }
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(68849)) {
            /* generate a new temporary key pair for the server connection, if necessary */
            if ((ListenerUtil.mutListener.listen(68844) ? ((ListenerUtil.mutListener.listen(68830) ? (clientTempKeyPub == null && clientTempKeySec == null) : (clientTempKeyPub == null || clientTempKeySec == null)) && (ListenerUtil.mutListener.listen(68843) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) >= ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68842) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) <= ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68841) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) < ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68840) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) != ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68839) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) == ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) > ProtocolDefines.CLIENT_TEMPKEY_MAXAGE))))))) : ((ListenerUtil.mutListener.listen(68830) ? (clientTempKeyPub == null && clientTempKeySec == null) : (clientTempKeyPub == null || clientTempKeySec == null)) || (ListenerUtil.mutListener.listen(68843) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) >= ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68842) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) <= ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68841) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) < ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68840) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) != ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (ListenerUtil.mutListener.listen(68839) ? (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) == ProtocolDefines.CLIENT_TEMPKEY_MAXAGE) : (((ListenerUtil.mutListener.listen(68838) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) % 1000) : (ListenerUtil.mutListener.listen(68837) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) * 1000) : (ListenerUtil.mutListener.listen(68836) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) - 1000) : (ListenerUtil.mutListener.listen(68835) ? (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) + 1000) : (((ListenerUtil.mutListener.listen(68834) ? (new Date().getTime() % clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68833) ? (new Date().getTime() / clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68832) ? (new Date().getTime() * clientTempKeyGenTime.getTime()) : (ListenerUtil.mutListener.listen(68831) ? (new Date().getTime() + clientTempKeyGenTime.getTime()) : (new Date().getTime() - clientTempKeyGenTime.getTime())))))) / 1000)))))) > ProtocolDefines.CLIENT_TEMPKEY_MAXAGE))))))))) {
                if (!ListenerUtil.mutListener.listen(68845)) {
                    clientTempKeyPub = new byte[NaCl.PUBLICKEYBYTES];
                }
                if (!ListenerUtil.mutListener.listen(68846)) {
                    clientTempKeySec = new byte[NaCl.SECRETKEYBYTES];
                }
                if (!ListenerUtil.mutListener.listen(68847)) {
                    NaCl.genkeypair(clientTempKeyPub, clientTempKeySec);
                }
                if (!ListenerUtil.mutListener.listen(68848)) {
                    clientTempKeyGenTime = new Date();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68850)) {
            anotherConnectionCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(68988)) {
            {
                long _loopCounter876 = 0;
                while (running) {
                    ListenerUtil.loopListener.listen("_loopCounter876", ++_loopCounter876);
                    TimerTask echoSendTask = null;
                    try {
                        if (!ListenerUtil.mutListener.listen(68862)) {
                            getInetAdresses();
                        }
                        if (!ListenerUtil.mutListener.listen(68863)) {
                            connectionNumber.getAndIncrement();
                        }
                        if (!ListenerUtil.mutListener.listen(68864)) {
                            setConnectionState(ConnectionState.CONNECTING);
                        }
                        InetSocketAddress address = serverSocketAddresses.get(curSocketAddressIndex);
                        if (!ListenerUtil.mutListener.listen(68865)) {
                            logger.info("Connecting to {}...", address);
                        }
                        if (!ListenerUtil.mutListener.listen(68866)) {
                            socket = ProxyAwareSocketFactory.makeSocket(address);
                        }
                        if (!ListenerUtil.mutListener.listen(68875)) {
                            socket.connect(address, address.getAddress() instanceof Inet6Address ? (ListenerUtil.mutListener.listen(68874) ? (ProtocolDefines.CONNECT_TIMEOUT_IPV6 % 1000) : (ListenerUtil.mutListener.listen(68873) ? (ProtocolDefines.CONNECT_TIMEOUT_IPV6 / 1000) : (ListenerUtil.mutListener.listen(68872) ? (ProtocolDefines.CONNECT_TIMEOUT_IPV6 - 1000) : (ListenerUtil.mutListener.listen(68871) ? (ProtocolDefines.CONNECT_TIMEOUT_IPV6 + 1000) : (ProtocolDefines.CONNECT_TIMEOUT_IPV6 * 1000))))) : (ListenerUtil.mutListener.listen(68870) ? (ProtocolDefines.CONNECT_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(68869) ? (ProtocolDefines.CONNECT_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(68868) ? (ProtocolDefines.CONNECT_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(68867) ? (ProtocolDefines.CONNECT_TIMEOUT + 1000) : (ProtocolDefines.CONNECT_TIMEOUT * 1000))))));
                        }
                        if (!ListenerUtil.mutListener.listen(68876)) {
                            setConnectionState(ConnectionState.CONNECTED);
                        }
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        OutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                        if (!ListenerUtil.mutListener.listen(68881)) {
                            /* set socket timeout for connection phase */
                            socket.setSoTimeout((ListenerUtil.mutListener.listen(68880) ? (ProtocolDefines.READ_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(68879) ? (ProtocolDefines.READ_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(68878) ? (ProtocolDefines.READ_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(68877) ? (ProtocolDefines.READ_TIMEOUT + 1000) : (ProtocolDefines.READ_TIMEOUT * 1000))))));
                        }
                        /* send client hello */
                        byte[] clientCookie = new byte[COOKIE_LEN];
                        if (!ListenerUtil.mutListener.listen(68882)) {
                            random.nextBytes(clientCookie);
                        }
                        if (!ListenerUtil.mutListener.listen(68884)) {
                            if (logger.isDebugEnabled()) {
                                if (!ListenerUtil.mutListener.listen(68883)) {
                                    logger.debug("Client cookie = {}", NaCl.asHex(clientCookie));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(68885)) {
                            bos.write(clientTempKeyPub);
                        }
                        if (!ListenerUtil.mutListener.listen(68886)) {
                            bos.write(clientCookie);
                        }
                        if (!ListenerUtil.mutListener.listen(68887)) {
                            bos.flush();
                        }
                        /* read server hello */
                        byte[] serverCookie = new byte[COOKIE_LEN];
                        if (!ListenerUtil.mutListener.listen(68888)) {
                            dis.readFully(serverCookie);
                        }
                        if (!ListenerUtil.mutListener.listen(68890)) {
                            if (logger.isDebugEnabled()) {
                                if (!ListenerUtil.mutListener.listen(68889)) {
                                    logger.debug("Server cookie = {}", NaCl.asHex(serverCookie));
                                }
                            }
                        }
                        NonceCounter serverNonce = new NonceCounter(serverCookie);
                        NonceCounter clientNonce = new NonceCounter(clientCookie);
                        byte[] serverHelloBox = new byte[SERVER_HELLO_BOXLEN];
                        if (!ListenerUtil.mutListener.listen(68891)) {
                            dis.readFully(serverHelloBox);
                        }
                        /* decrypt server hello */
                        byte[] nonce = serverNonce.nextNonce();
                        if (!ListenerUtil.mutListener.listen(68893)) {
                            if (logger.isDebugEnabled()) {
                                if (!ListenerUtil.mutListener.listen(68892)) {
                                    logger.debug("Server nonce = {}", NaCl.asHex(nonce));
                                }
                            }
                        }
                        /* precalculate shared keys */
                        byte[] serverPubKeyCur = serverPubKey;
                        NaCl kclientTempServerPerm = new NaCl(clientTempKeySec, serverPubKeyCur);
                        byte[] serverHello = kclientTempServerPerm.decrypt(serverHelloBox, nonce);
                        if (!ListenerUtil.mutListener.listen(68898)) {
                            if (serverHello == null) {
                                if (!ListenerUtil.mutListener.listen(68894)) {
                                    /* Try again with alternate key */
                                    serverPubKeyCur = serverPubKeyAlt;
                                }
                                if (!ListenerUtil.mutListener.listen(68895)) {
                                    kclientTempServerPerm = new NaCl(clientTempKeySec, serverPubKeyCur);
                                }
                                if (!ListenerUtil.mutListener.listen(68896)) {
                                    serverHello = kclientTempServerPerm.decrypt(serverHelloBox, nonce);
                                }
                                if (!ListenerUtil.mutListener.listen(68897)) {
                                    if (serverHello == null) {
                                        throw new ThreemaException("Decryption of server hello box failed");
                                    }
                                }
                            }
                        }
                        /* extract server tempkey and client cookie from server hello */
                        byte[] serverTempKeyPub = new byte[NaCl.PUBLICKEYBYTES];
                        byte[] clientCookieFromServer = new byte[COOKIE_LEN];
                        if (!ListenerUtil.mutListener.listen(68899)) {
                            System.arraycopy(serverHello, 0, serverTempKeyPub, 0, NaCl.PUBLICKEYBYTES);
                        }
                        if (!ListenerUtil.mutListener.listen(68900)) {
                            System.arraycopy(serverHello, NaCl.PUBLICKEYBYTES, clientCookieFromServer, 0, COOKIE_LEN);
                        }
                        if (!ListenerUtil.mutListener.listen(68901)) {
                            /* verify client copy */
                            if (!Arrays.equals(clientCookie, clientCookieFromServer)) {
                                throw new ThreemaException("Client cookie mismatch");
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(68902)) {
                            logger.info("Server hello successful");
                        }
                        /* prepare vouch sub packet */
                        byte[] vouchNonce = new byte[NaCl.NONCEBYTES];
                        if (!ListenerUtil.mutListener.listen(68903)) {
                            random.nextBytes(vouchNonce);
                        }
                        byte[] vouchBox = identityStore.encryptData(clientTempKeyPub, vouchNonce, serverPubKeyCur);
                        if (!ListenerUtil.mutListener.listen(68904)) {
                            if (vouchBox == null) {
                                throw new ThreemaException("Vouch box encryption failed");
                            }
                        }
                        /* now prepare login packet */
                        byte[] version = this.makeVersion();
                        byte[] login = new byte[LOGIN_LEN];
                        int login_i = 0;
                        if (!ListenerUtil.mutListener.listen(68905)) {
                            System.arraycopy(identityStore.getIdentity().getBytes(), 0, login, 0, IDENTITY_LEN);
                        }
                        if (!ListenerUtil.mutListener.listen(68906)) {
                            login_i += IDENTITY_LEN;
                        }
                        if (!ListenerUtil.mutListener.listen(68907)) {
                            System.arraycopy(version, 0, login, login_i, VERSION_LEN);
                        }
                        if (!ListenerUtil.mutListener.listen(68908)) {
                            login_i += VERSION_LEN;
                        }
                        if (!ListenerUtil.mutListener.listen(68909)) {
                            System.arraycopy(serverCookie, 0, login, login_i, COOKIE_LEN);
                        }
                        if (!ListenerUtil.mutListener.listen(68910)) {
                            login_i += COOKIE_LEN;
                        }
                        if (!ListenerUtil.mutListener.listen(68911)) {
                            System.arraycopy(vouchNonce, 0, login, login_i, NaCl.NONCEBYTES);
                        }
                        if (!ListenerUtil.mutListener.listen(68912)) {
                            login_i += NaCl.NONCEBYTES;
                        }
                        if (!ListenerUtil.mutListener.listen(68913)) {
                            System.arraycopy(vouchBox, 0, login, login_i, VOUCH_LEN + NaCl.BOXOVERHEAD);
                        }
                        /* encrypt login packet */
                        NaCl kclientTempServerTemp = new NaCl(clientTempKeySec, serverTempKeyPub);
                        byte[] loginBox = kclientTempServerTemp.encrypt(login, clientNonce.nextNonce());
                        if (!ListenerUtil.mutListener.listen(68914)) {
                            /* send it! */
                            bos.write(loginBox);
                        }
                        if (!ListenerUtil.mutListener.listen(68915)) {
                            bos.flush();
                        }
                        if (!ListenerUtil.mutListener.listen(68916)) {
                            logger.debug("Sent login packet");
                        }
                        /* read login ack */
                        byte[] loginackBox = new byte[LOGIN_ACK_LEN];
                        if (!ListenerUtil.mutListener.listen(68917)) {
                            dis.readFully(loginackBox);
                        }
                        /* decrypt login ack */
                        byte[] loginack = kclientTempServerTemp.decrypt(loginackBox, serverNonce.nextNonce());
                        if (!ListenerUtil.mutListener.listen(68918)) {
                            if (loginack == null) {
                                throw new ThreemaException("Decryption of login ack box failed");
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(68919)) {
                            logger.info("Login ack received");
                        }
                        if (!ListenerUtil.mutListener.listen(68920)) {
                            /* clear socket timeout */
                            socket.setSoTimeout(0);
                        }
                        if (!ListenerUtil.mutListener.listen(68921)) {
                            reconnectAttempts = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(68922)) {
                            /* fire up sender thread to send packets while we are receiving */
                            senderThread = new SenderThread(bos, kclientTempServerTemp, clientNonce);
                        }
                        if (!ListenerUtil.mutListener.listen(68923)) {
                            senderThread.start();
                        }
                        if (!ListenerUtil.mutListener.listen(68925)) {
                            /* schedule timer task for sending echo packets */
                            echoSendTask = new TimerTask() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(68924)) {
                                        sendEchoRequest();
                                    }
                                }
                            };
                        }
                        if (!ListenerUtil.mutListener.listen(68934)) {
                            timer.schedule(echoSendTask, (ListenerUtil.mutListener.listen(68929) ? (ProtocolDefines.KEEPALIVE_INTERVAL % 1000) : (ListenerUtil.mutListener.listen(68928) ? (ProtocolDefines.KEEPALIVE_INTERVAL / 1000) : (ListenerUtil.mutListener.listen(68927) ? (ProtocolDefines.KEEPALIVE_INTERVAL - 1000) : (ListenerUtil.mutListener.listen(68926) ? (ProtocolDefines.KEEPALIVE_INTERVAL + 1000) : (ProtocolDefines.KEEPALIVE_INTERVAL * 1000))))), (ListenerUtil.mutListener.listen(68933) ? (ProtocolDefines.KEEPALIVE_INTERVAL % 1000) : (ListenerUtil.mutListener.listen(68932) ? (ProtocolDefines.KEEPALIVE_INTERVAL / 1000) : (ListenerUtil.mutListener.listen(68931) ? (ProtocolDefines.KEEPALIVE_INTERVAL - 1000) : (ListenerUtil.mutListener.listen(68930) ? (ProtocolDefines.KEEPALIVE_INTERVAL + 1000) : (ProtocolDefines.KEEPALIVE_INTERVAL * 1000))))));
                        }
                        if (!ListenerUtil.mutListener.listen(68935)) {
                            /* tell our listeners */
                            setConnectionState(ConnectionState.LOGGEDIN);
                        }
                        if (!ListenerUtil.mutListener.listen(68957)) {
                            {
                                long _loopCounter875 = 0;
                                /* receive packets until the connection dies */
                                while (running) {
                                    ListenerUtil.loopListener.listen("_loopCounter875", ++_loopCounter875);
                                    int length = EndianUtils.swapShort(dis.readShort());
                                    byte[] data = new byte[length];
                                    if (!ListenerUtil.mutListener.listen(68936)) {
                                        dis.readFully(data);
                                    }
                                    if (!ListenerUtil.mutListener.listen(68937)) {
                                        logger.debug("Received payload ({} bytes)", length);
                                    }
                                    if (!ListenerUtil.mutListener.listen(68944)) {
                                        if ((ListenerUtil.mutListener.listen(68942) ? (length >= 4) : (ListenerUtil.mutListener.listen(68941) ? (length <= 4) : (ListenerUtil.mutListener.listen(68940) ? (length > 4) : (ListenerUtil.mutListener.listen(68939) ? (length != 4) : (ListenerUtil.mutListener.listen(68938) ? (length == 4) : (length < 4))))))) {
                                            if (!ListenerUtil.mutListener.listen(68943)) {
                                                logger.error("Short payload received (" + length + " bytes)");
                                            }
                                            break;
                                        }
                                    }
                                    /* decrypt payload */
                                    byte[] decrypted = kclientTempServerTemp.decrypt(data, serverNonce.nextNonce());
                                    if (!ListenerUtil.mutListener.listen(68946)) {
                                        if (decrypted == null) {
                                            if (!ListenerUtil.mutListener.listen(68945)) {
                                                logger.error("Payload decryption failed");
                                            }
                                            break;
                                        }
                                    }
                                    int payloadType = decrypted[0] & 0xFF;
                                    byte[] payloadData = new byte[(ListenerUtil.mutListener.listen(68950) ? (decrypted.length % 4) : (ListenerUtil.mutListener.listen(68949) ? (decrypted.length / 4) : (ListenerUtil.mutListener.listen(68948) ? (decrypted.length * 4) : (ListenerUtil.mutListener.listen(68947) ? (decrypted.length + 4) : (decrypted.length - 4)))))];
                                    if (!ListenerUtil.mutListener.listen(68955)) {
                                        System.arraycopy(decrypted, 4, payloadData, 0, (ListenerUtil.mutListener.listen(68954) ? (decrypted.length % 4) : (ListenerUtil.mutListener.listen(68953) ? (decrypted.length / 4) : (ListenerUtil.mutListener.listen(68952) ? (decrypted.length * 4) : (ListenerUtil.mutListener.listen(68951) ? (decrypted.length + 4) : (decrypted.length - 4))))));
                                    }
                                    Payload payload = new Payload(payloadType, payloadData);
                                    if (!ListenerUtil.mutListener.listen(68956)) {
                                        processPayload(payload);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(68861)) {
                            if (running) {
                                if (!ListenerUtil.mutListener.listen(68851)) {
                                    logger.warn("Connection exception", e);
                                }
                                if (!ListenerUtil.mutListener.listen(68860)) {
                                    if (state != ConnectionState.LOGGEDIN) {
                                        if (!ListenerUtil.mutListener.listen(68852)) {
                                            /* switch to alternate port */
                                            curSocketAddressIndex++;
                                        }
                                        if (!ListenerUtil.mutListener.listen(68859)) {
                                            if ((ListenerUtil.mutListener.listen(68857) ? (curSocketAddressIndex <= serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68856) ? (curSocketAddressIndex > serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68855) ? (curSocketAddressIndex < serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68854) ? (curSocketAddressIndex != serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68853) ? (curSocketAddressIndex == serverSocketAddresses.size()) : (curSocketAddressIndex >= serverSocketAddresses.size()))))))) {
                                                if (!ListenerUtil.mutListener.listen(68858)) {
                                                    curSocketAddressIndex = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(68958)) {
                        setConnectionState(ConnectionState.DISCONNECTED);
                    }
                    if (!ListenerUtil.mutListener.listen(68961)) {
                        if (senderThread != null) {
                            if (!ListenerUtil.mutListener.listen(68959)) {
                                senderThread.shutdown();
                            }
                            if (!ListenerUtil.mutListener.listen(68960)) {
                                senderThread = null;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(68963)) {
                        if (echoSendTask != null)
                            if (!ListenerUtil.mutListener.listen(68962)) {
                                echoSendTask.cancel();
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(68967)) {
                        if (socket != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(68965)) {
                                    socket.close();
                                }
                            } catch (IOException e) {
                                if (!ListenerUtil.mutListener.listen(68964)) {
                                    logger.warn("Ignored IOException", e);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(68966)) {
                                socket = null;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(68968)) {
                        if (!running) {
                            break;
                        }
                    }
                    /* Sleep before reconnecting (bounded exponential backoff) */
                    double reconnectDelay = Math.pow(ProtocolDefines.RECONNECT_BASE_INTERVAL, Math.min((ListenerUtil.mutListener.listen(68972) ? (reconnectAttempts % 1) : (ListenerUtil.mutListener.listen(68971) ? (reconnectAttempts / 1) : (ListenerUtil.mutListener.listen(68970) ? (reconnectAttempts * 1) : (ListenerUtil.mutListener.listen(68969) ? (reconnectAttempts + 1) : (reconnectAttempts - 1))))), 10));
                    if (!ListenerUtil.mutListener.listen(68979)) {
                        if ((ListenerUtil.mutListener.listen(68977) ? (reconnectDelay >= ProtocolDefines.RECONNECT_MAX_INTERVAL) : (ListenerUtil.mutListener.listen(68976) ? (reconnectDelay <= ProtocolDefines.RECONNECT_MAX_INTERVAL) : (ListenerUtil.mutListener.listen(68975) ? (reconnectDelay < ProtocolDefines.RECONNECT_MAX_INTERVAL) : (ListenerUtil.mutListener.listen(68974) ? (reconnectDelay != ProtocolDefines.RECONNECT_MAX_INTERVAL) : (ListenerUtil.mutListener.listen(68973) ? (reconnectDelay == ProtocolDefines.RECONNECT_MAX_INTERVAL) : (reconnectDelay > ProtocolDefines.RECONNECT_MAX_INTERVAL)))))))
                            if (!ListenerUtil.mutListener.listen(68978)) {
                                reconnectDelay = ProtocolDefines.RECONNECT_MAX_INTERVAL;
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(68980)) {
                        reconnectAttempts++;
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(68982)) {
                            /* Don't reconnect too quickly */
                            logger.debug("Waiting {} seconds before reconnecting", reconnectDelay);
                        }
                        if (!ListenerUtil.mutListener.listen(68987)) {
                            Thread.sleep((long) ((ListenerUtil.mutListener.listen(68986) ? (reconnectDelay % 1000) : (ListenerUtil.mutListener.listen(68985) ? (reconnectDelay / 1000) : (ListenerUtil.mutListener.listen(68984) ? (reconnectDelay - 1000) : (ListenerUtil.mutListener.listen(68983) ? (reconnectDelay + 1000) : (reconnectDelay * 1000)))))));
                        }
                    } catch (InterruptedException ignored) {
                        if (!ListenerUtil.mutListener.listen(68981)) {
                            // We were interrupted. Break the main loop.
                            logger.debug("Interrupted");
                        }
                        break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68989)) {
            curThread = null;
        }
        if (!ListenerUtil.mutListener.listen(68990)) {
            logger.info("Ended");
        }
    }

    public void addMessageAckListener(MessageAckListener listener) {
        if (!ListenerUtil.mutListener.listen(68991)) {
            ackListeners.add(listener);
        }
    }

    public void removeMessageAckListener(MessageAckListener listener) {
        if (!ListenerUtil.mutListener.listen(68992)) {
            ackListeners.remove(listener);
        }
    }

    /**
     *  Notify active listeners that a new message ack was received from the server.
     */
    private void notifyMessageAckListeners(@NonNull MessageAck messageAck) {
        if (!ListenerUtil.mutListener.listen(68995)) {
            {
                long _loopCounter877 = 0;
                for (MessageAckListener listener : ackListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter877", ++_loopCounter877);
                    try {
                        if (!ListenerUtil.mutListener.listen(68994)) {
                            listener.processAck(messageAck);
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(68993)) {
                            logger.warn("Exception while invoking message ACK listener", e);
                        }
                    }
                }
            }
        }
    }

    public void addConnectionStateListener(ConnectionStateListener listener) {
        synchronized (connectionStateListeners) {
            if (!ListenerUtil.mutListener.listen(68996)) {
                connectionStateListeners.add(listener);
            }
        }
    }

    public void removeConnectionStateListener(ConnectionStateListener listener) {
        synchronized (connectionStateListeners) {
            if (!ListenerUtil.mutListener.listen(68997)) {
                connectionStateListeners.remove(listener);
            }
        }
    }

    private void setConnectionState(ConnectionState state) {
        if (!ListenerUtil.mutListener.listen(68998)) {
            this.state = state;
        }
        if (!ListenerUtil.mutListener.listen(69007)) {
            if ((ListenerUtil.mutListener.listen(69003) ? (curSocketAddressIndex >= serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(69002) ? (curSocketAddressIndex <= serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(69001) ? (curSocketAddressIndex > serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(69000) ? (curSocketAddressIndex != serverSocketAddresses.size()) : (ListenerUtil.mutListener.listen(68999) ? (curSocketAddressIndex == serverSocketAddresses.size()) : (curSocketAddressIndex < serverSocketAddresses.size()))))))) {
                synchronized (connectionStateListeners) {
                    if (!ListenerUtil.mutListener.listen(69006)) {
                        {
                            long _loopCounter878 = 0;
                            for (ConnectionStateListener listener : connectionStateListeners) {
                                ListenerUtil.loopListener.listen("_loopCounter878", ++_loopCounter878);
                                try {
                                    if (!ListenerUtil.mutListener.listen(69005)) {
                                        listener.updateConnectionState(state, serverSocketAddresses.get(curSocketAddressIndex));
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(69004)) {
                                        logger.warn("Exception while invoking connection state listener", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void addQueueSendCompleteListener(QueueSendCompleteListener listener) {
        if (!ListenerUtil.mutListener.listen(69008)) {
            queueSendCompleteListeners.add(listener);
        }
    }

    public void removeQueueSendCompleteListener(QueueSendCompleteListener listener) {
        if (!ListenerUtil.mutListener.listen(69009)) {
            queueSendCompleteListeners.remove(listener);
        }
    }

    private void notifyQueueSendComplete() {
        if (!ListenerUtil.mutListener.listen(69012)) {
            {
                long _loopCounter879 = 0;
                for (QueueSendCompleteListener listener : queueSendCompleteListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter879", ++_loopCounter879);
                    try {
                        if (!ListenerUtil.mutListener.listen(69011)) {
                            listener.queueSendComplete();
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(69010)) {
                            logger.warn("Exception while invoking queue send complete listener", e);
                        }
                    }
                }
            }
        }
    }

    private void processPayload(Payload payload) throws PayloadProcessingException {
        byte[] data = payload.getData();
        if (!ListenerUtil.mutListener.listen(69013)) {
            logger.debug("Payload type {}", payload.getType());
        }
        if (!ListenerUtil.mutListener.listen(69058)) {
            switch(payload.getType()) {
                case ProtocolDefines.PLTYPE_ECHO_REPLY:
                    if (!ListenerUtil.mutListener.listen(69015)) {
                        if (data.length == 4) {
                            if (!ListenerUtil.mutListener.listen(69014)) {
                                lastRcvdEchoSeq = Utils.byteArrayToInt(data);
                            }
                        } else {
                            throw new PayloadProcessingException("Bad length (" + data.length + ") for echo reply payload");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(69016)) {
                        logger.info("Received echo reply (seq {})", lastRcvdEchoSeq);
                    }
                    break;
                case ProtocolDefines.PLTYPE_ERROR:
                    if (!ListenerUtil.mutListener.listen(69022)) {
                        if ((ListenerUtil.mutListener.listen(69021) ? (data.length >= 1) : (ListenerUtil.mutListener.listen(69020) ? (data.length <= 1) : (ListenerUtil.mutListener.listen(69019) ? (data.length > 1) : (ListenerUtil.mutListener.listen(69018) ? (data.length != 1) : (ListenerUtil.mutListener.listen(69017) ? (data.length == 1) : (data.length < 1))))))) {
                            throw new PayloadProcessingException("Bad length (" + data.length + ") for error payload");
                        }
                    }
                    int reconnectAllowed = data[0] & 0xFF;
                    String errorMessage = new String(data, 1, (ListenerUtil.mutListener.listen(69026) ? (data.length % 1) : (ListenerUtil.mutListener.listen(69025) ? (data.length / 1) : (ListenerUtil.mutListener.listen(69024) ? (data.length * 1) : (ListenerUtil.mutListener.listen(69023) ? (data.length + 1) : (data.length - 1))))), StandardCharsets.UTF_8);
                    if (!ListenerUtil.mutListener.listen(69027)) {
                        logger.error("Received error message from server: {}", errorMessage);
                    }
                    if (!ListenerUtil.mutListener.listen(69035)) {
                        /* workaround for stray "Another connection" messages due to weird timing
				   when switching between networks: ignore first few occurrences */
                        if ((ListenerUtil.mutListener.listen(69033) ? (errorMessage.contains("Another connection") || (ListenerUtil.mutListener.listen(69032) ? (anotherConnectionCount >= 5) : (ListenerUtil.mutListener.listen(69031) ? (anotherConnectionCount <= 5) : (ListenerUtil.mutListener.listen(69030) ? (anotherConnectionCount > 5) : (ListenerUtil.mutListener.listen(69029) ? (anotherConnectionCount != 5) : (ListenerUtil.mutListener.listen(69028) ? (anotherConnectionCount == 5) : (anotherConnectionCount < 5))))))) : (errorMessage.contains("Another connection") && (ListenerUtil.mutListener.listen(69032) ? (anotherConnectionCount >= 5) : (ListenerUtil.mutListener.listen(69031) ? (anotherConnectionCount <= 5) : (ListenerUtil.mutListener.listen(69030) ? (anotherConnectionCount > 5) : (ListenerUtil.mutListener.listen(69029) ? (anotherConnectionCount != 5) : (ListenerUtil.mutListener.listen(69028) ? (anotherConnectionCount == 5) : (anotherConnectionCount < 5))))))))) {
                            if (!ListenerUtil.mutListener.listen(69034)) {
                                anotherConnectionCount++;
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(69042)) {
                        if (messageProcessor != null) {
                            if (!ListenerUtil.mutListener.listen(69041)) {
                                messageProcessor.processServerError(errorMessage, (ListenerUtil.mutListener.listen(69040) ? (reconnectAllowed >= 0) : (ListenerUtil.mutListener.listen(69039) ? (reconnectAllowed <= 0) : (ListenerUtil.mutListener.listen(69038) ? (reconnectAllowed > 0) : (ListenerUtil.mutListener.listen(69037) ? (reconnectAllowed < 0) : (ListenerUtil.mutListener.listen(69036) ? (reconnectAllowed == 0) : (reconnectAllowed != 0)))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(69049)) {
                        if ((ListenerUtil.mutListener.listen(69047) ? (reconnectAllowed >= 0) : (ListenerUtil.mutListener.listen(69046) ? (reconnectAllowed <= 0) : (ListenerUtil.mutListener.listen(69045) ? (reconnectAllowed > 0) : (ListenerUtil.mutListener.listen(69044) ? (reconnectAllowed < 0) : (ListenerUtil.mutListener.listen(69043) ? (reconnectAllowed != 0) : (reconnectAllowed == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(69048)) {
                                running = false;
                            }
                        }
                    }
                    break;
                case ProtocolDefines.PLTYPE_ALERT:
                    final String alertMessage = new String(data, StandardCharsets.UTF_8);
                    if (!ListenerUtil.mutListener.listen(69050)) {
                        logger.info("Received alert message from server: {}", alertMessage);
                    }
                    if (!ListenerUtil.mutListener.listen(69054)) {
                        if (!lastAlertMessages.contains(alertMessage)) {
                            if (!ListenerUtil.mutListener.listen(69053)) {
                                if (messageProcessor != null) {
                                    if (!ListenerUtil.mutListener.listen(69051)) {
                                        messageProcessor.processServerAlert(alertMessage);
                                    }
                                    if (!ListenerUtil.mutListener.listen(69052)) {
                                        lastAlertMessages.add(alertMessage);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case ProtocolDefines.PLTYPE_OUTGOING_MESSAGE_ACK:
                    if (!ListenerUtil.mutListener.listen(69055)) {
                        processOutgoingMessageAckPayload(payload);
                    }
                    break;
                case ProtocolDefines.PLTYPE_INCOMING_MESSAGE:
                    if (!ListenerUtil.mutListener.listen(69056)) {
                        processIncomingMessagePayload(payload);
                    }
                    break;
                case ProtocolDefines.PLTYPE_QUEUE_SEND_COMPLETE:
                    if (!ListenerUtil.mutListener.listen(69057)) {
                        notifyQueueSendComplete();
                    }
                    break;
            }
        }
    }

    /**
     *  Process a message ack payload received from the server.
     */
    private void processOutgoingMessageAckPayload(@NonNull Payload payload) throws PayloadProcessingException {
        final byte[] data = payload.getData();
        if (!ListenerUtil.mutListener.listen(69059)) {
            // Validate message length
            if (data.length != ProtocolDefines.IDENTITY_LEN + ProtocolDefines.MESSAGE_ID_LEN) {
                throw new PayloadProcessingException("Bad length (" + data.length + ") for message ack payload");
            }
        }
        // Recipient identity
        byte[] recipientId = new byte[ProtocolDefines.IDENTITY_LEN];
        if (!ListenerUtil.mutListener.listen(69060)) {
            System.arraycopy(data, 0, recipientId, 0, ProtocolDefines.IDENTITY_LEN);
        }
        // Message ID
        final MessageId messageId = new MessageId(data, ProtocolDefines.IDENTITY_LEN);
        // Create MessageAck instance
        final MessageAck ack = new MessageAck(recipientId, messageId);
        if (!ListenerUtil.mutListener.listen(69061)) {
            logger.debug("Received message ack for message {} to {}", ack.getMessageId(), ack.getRecipientId());
        }
        if (!ListenerUtil.mutListener.listen(69062)) {
            // Notify listeners
            notifyMessageAckListeners(ack);
        }
    }

    private void processIncomingMessagePayload(Payload payload) throws PayloadProcessingException {
        byte[] data = payload.getData();
        if (!ListenerUtil.mutListener.listen(69068)) {
            if ((ListenerUtil.mutListener.listen(69067) ? (data.length >= ProtocolDefines.OVERHEAD_MSG_HDR) : (ListenerUtil.mutListener.listen(69066) ? (data.length <= ProtocolDefines.OVERHEAD_MSG_HDR) : (ListenerUtil.mutListener.listen(69065) ? (data.length > ProtocolDefines.OVERHEAD_MSG_HDR) : (ListenerUtil.mutListener.listen(69064) ? (data.length != ProtocolDefines.OVERHEAD_MSG_HDR) : (ListenerUtil.mutListener.listen(69063) ? (data.length == ProtocolDefines.OVERHEAD_MSG_HDR) : (data.length < ProtocolDefines.OVERHEAD_MSG_HDR))))))) {
                throw new PayloadProcessingException("Bad length (" + data.length + ") for message payload");
            }
        }
        try {
            BoxedMessage boxmsg = BoxedMessage.parsePayload(payload);
            if (!ListenerUtil.mutListener.listen(69070)) {
                logger.info("Incoming message from {} (ID {})", boxmsg.getFromIdentity(), boxmsg.getMessageId());
            }
            if (!ListenerUtil.mutListener.listen(69080)) {
                if (messageProcessor != null) {
                    boolean ackMessage;
                    if (!this.nonceFactory.exists(boxmsg.getNonce())) {
                        MessageProcessorInterface.ProcessIncomingResult result = messageProcessor.processIncomingMessage(boxmsg);
                        if (!ListenerUtil.mutListener.listen(69075)) {
                            // and if the message is *not* a typing indicator
                            if ((ListenerUtil.mutListener.listen(69073) ? ((ListenerUtil.mutListener.listen(69072) ? ((ListenerUtil.mutListener.listen(69071) ? (result != null || result.processed) : (result != null && result.processed)) || result.abstractMessage != null) : ((ListenerUtil.mutListener.listen(69071) ? (result != null || result.processed) : (result != null && result.processed)) && result.abstractMessage != null)) || result.abstractMessage.getType() != ProtocolDefines.MSGTYPE_TYPING_INDICATOR) : ((ListenerUtil.mutListener.listen(69072) ? ((ListenerUtil.mutListener.listen(69071) ? (result != null || result.processed) : (result != null && result.processed)) || result.abstractMessage != null) : ((ListenerUtil.mutListener.listen(69071) ? (result != null || result.processed) : (result != null && result.processed)) && result.abstractMessage != null)) && result.abstractMessage.getType() != ProtocolDefines.MSGTYPE_TYPING_INDICATOR))) {
                                if (!ListenerUtil.mutListener.listen(69074)) {
                                    this.nonceFactory.store(boxmsg.getNonce());
                                }
                            }
                        }
                        ackMessage = (ListenerUtil.mutListener.listen(69076) ? (result != null || result.processed) : (result != null && result.processed));
                    } else {
                        // auto ack a already nonce'd message
                        ackMessage = true;
                    }
                    if (!ListenerUtil.mutListener.listen(69079)) {
                        if ((ListenerUtil.mutListener.listen(69077) ? (ackMessage || (boxmsg.getFlags() & ProtocolDefines.MESSAGE_FLAG_NOACK) == 0) : (ackMessage && (boxmsg.getFlags() & ProtocolDefines.MESSAGE_FLAG_NOACK) == 0))) {
                            if (!ListenerUtil.mutListener.listen(69078)) {
                                sendAck(boxmsg.getMessageId(), boxmsg.getFromIdentity());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(69069)) {
                /* don't break connection if we cannot parse the message (may not be the server's fault) */
                logger.warn("Box message parse failed", e);
            }
        }
    }

    private void sendAck(MessageId messageId, String identity) {
        if (!ListenerUtil.mutListener.listen(69081)) {
            logger.debug("Sending ack for message ID {} from {}", messageId, identity);
        }
        byte[] plData = new byte[ProtocolDefines.IDENTITY_LEN + ProtocolDefines.MESSAGE_ID_LEN];
        byte[] identityB = identity.getBytes();
        if (!ListenerUtil.mutListener.listen(69082)) {
            System.arraycopy(identityB, 0, plData, 0, ProtocolDefines.IDENTITY_LEN);
        }
        if (!ListenerUtil.mutListener.listen(69083)) {
            System.arraycopy(messageId.getMessageId(), 0, plData, ProtocolDefines.IDENTITY_LEN, ProtocolDefines.MESSAGE_ID_LEN);
        }
        Payload ackPayload = new Payload(ProtocolDefines.PLTYPE_INCOMING_MESSAGE_ACK, plData);
        if (!ListenerUtil.mutListener.listen(69084)) {
            sendPayload(ackPayload);
        }
    }

    private void sendEchoRequest() {
        if (!ListenerUtil.mutListener.listen(69085)) {
            lastSentEchoSeq++;
        }
        if (!ListenerUtil.mutListener.listen(69086)) {
            logger.debug("Sending echo request (seq {})", lastSentEchoSeq);
        }
        byte[] echoData = Utils.intToByteArray(lastSentEchoSeq);
        Payload echoPayload = new Payload(ProtocolDefines.PLTYPE_ECHO_REQUEST, echoData);
        if (!ListenerUtil.mutListener.listen(69087)) {
            sendPayload(echoPayload);
        }
        /* schedule timer task to check that we have received the echo reply */
        final int curConnectionNumber = connectionNumber.get();
        if (!ListenerUtil.mutListener.listen(69107)) {
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(69102)) {
                        if ((ListenerUtil.mutListener.listen(69098) ? ((ListenerUtil.mutListener.listen(69092) ? (connectionNumber.get() >= curConnectionNumber) : (ListenerUtil.mutListener.listen(69091) ? (connectionNumber.get() <= curConnectionNumber) : (ListenerUtil.mutListener.listen(69090) ? (connectionNumber.get() > curConnectionNumber) : (ListenerUtil.mutListener.listen(69089) ? (connectionNumber.get() < curConnectionNumber) : (ListenerUtil.mutListener.listen(69088) ? (connectionNumber.get() != curConnectionNumber) : (connectionNumber.get() == curConnectionNumber)))))) || (ListenerUtil.mutListener.listen(69097) ? (lastRcvdEchoSeq >= lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69096) ? (lastRcvdEchoSeq <= lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69095) ? (lastRcvdEchoSeq > lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69094) ? (lastRcvdEchoSeq != lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69093) ? (lastRcvdEchoSeq == lastSentEchoSeq) : (lastRcvdEchoSeq < lastSentEchoSeq))))))) : ((ListenerUtil.mutListener.listen(69092) ? (connectionNumber.get() >= curConnectionNumber) : (ListenerUtil.mutListener.listen(69091) ? (connectionNumber.get() <= curConnectionNumber) : (ListenerUtil.mutListener.listen(69090) ? (connectionNumber.get() > curConnectionNumber) : (ListenerUtil.mutListener.listen(69089) ? (connectionNumber.get() < curConnectionNumber) : (ListenerUtil.mutListener.listen(69088) ? (connectionNumber.get() != curConnectionNumber) : (connectionNumber.get() == curConnectionNumber)))))) && (ListenerUtil.mutListener.listen(69097) ? (lastRcvdEchoSeq >= lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69096) ? (lastRcvdEchoSeq <= lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69095) ? (lastRcvdEchoSeq > lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69094) ? (lastRcvdEchoSeq != lastSentEchoSeq) : (ListenerUtil.mutListener.listen(69093) ? (lastRcvdEchoSeq == lastSentEchoSeq) : (lastRcvdEchoSeq < lastSentEchoSeq))))))))) {
                            if (!ListenerUtil.mutListener.listen(69099)) {
                                logger.info("No reply to echo payload; reconnecting");
                            }
                            try {
                                Socket s = socket;
                                if (!ListenerUtil.mutListener.listen(69101)) {
                                    /* avoid race condition */
                                    if (s != null) {
                                        if (!ListenerUtil.mutListener.listen(69100)) {
                                            s.close();
                                        }
                                    }
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }, (ListenerUtil.mutListener.listen(69106) ? (ProtocolDefines.READ_TIMEOUT % 1000) : (ListenerUtil.mutListener.listen(69105) ? (ProtocolDefines.READ_TIMEOUT / 1000) : (ListenerUtil.mutListener.listen(69104) ? (ProtocolDefines.READ_TIMEOUT - 1000) : (ListenerUtil.mutListener.listen(69103) ? (ProtocolDefines.READ_TIMEOUT + 1000) : (ProtocolDefines.READ_TIMEOUT * 1000))))));
        }
    }

    private boolean sendPushToken() {
        if (!ListenerUtil.mutListener.listen(69118)) {
            if (this.pushToken != null) {
                if (!ListenerUtil.mutListener.listen(69108)) {
                    logger.debug("Sending push token type {}: {}", pushTokenType, pushToken);
                }
                byte[] pushTokenBytes = pushToken.getBytes(StandardCharsets.US_ASCII);
                byte[] pushTokenData = new byte[(ListenerUtil.mutListener.listen(69112) ? (pushTokenBytes.length % 1) : (ListenerUtil.mutListener.listen(69111) ? (pushTokenBytes.length / 1) : (ListenerUtil.mutListener.listen(69110) ? (pushTokenBytes.length * 1) : (ListenerUtil.mutListener.listen(69109) ? (pushTokenBytes.length - 1) : (pushTokenBytes.length + 1)))))];
                if (!ListenerUtil.mutListener.listen(69113)) {
                    /* prepend token type */
                    pushTokenData[0] = (byte) pushTokenType;
                }
                if (!ListenerUtil.mutListener.listen(69114)) {
                    System.arraycopy(pushTokenBytes, 0, pushTokenData, 1, pushTokenBytes.length);
                }
                /* send regular push token */
                final Payload pushPayload = new Payload(ProtocolDefines.PLTYPE_PUSH_NOTIFICATION_TOKEN, pushTokenData);
                if (!ListenerUtil.mutListener.listen(69117)) {
                    if (sendPayload(pushPayload)) {
                        /* This is identical to the regular push token. */
                        final Payload voipPushPayload = new Payload(ProtocolDefines.PLTYPE_VOIP_PUSH_NOTIFICATION_TOKEN, pushTokenData);
                        if (!ListenerUtil.mutListener.listen(69116)) {
                            if (sendPayload(voipPushPayload)) {
                                /* clear push filter (we don't need pushes to be filtered as we can handle "block unknown"/blacklist ourselves) */
                                Payload pushfilterPayload = new Payload(ProtocolDefines.PLTYPE_PUSH_ALLOWED_IDENTITIES, new byte[1]);
                                if (!ListenerUtil.mutListener.listen(69115)) {
                                    if (sendPayload(pushfilterPayload)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private byte[] makeVersion() {
        byte[] versionTrunc = new byte[VERSION_LEN];
        byte[] versionBytes = version.getFullVersion().getBytes();
        if (!ListenerUtil.mutListener.listen(69119)) {
            System.arraycopy(versionBytes, 0, versionTrunc, 0, Math.min(VERSION_LEN, versionBytes.length));
        }
        return versionTrunc;
    }

    public NonceFactory getNonceFactory() {
        return this.nonceFactory;
    }
}
