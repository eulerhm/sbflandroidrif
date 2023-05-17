package org.owntracks.android.services;

import org.owntracks.android.model.messages.MessageBase;
import org.owntracks.android.support.interfaces.ConfigurationIncompleteException;
import org.owntracks.android.support.interfaces.OutgoingMessageProcessor;
import java.io.IOException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MessageProcessorEndpoint implements OutgoingMessageProcessor {

    MessageProcessor messageProcessor;

    MessageProcessorEndpoint(MessageProcessor messageProcessor) {
        if (!ListenerUtil.mutListener.listen(705)) {
            this.messageProcessor = messageProcessor;
        }
    }

    void onMessageReceived(MessageBase message) {
        if (!ListenerUtil.mutListener.listen(706)) {
            message.setIncoming();
        }
        if (!ListenerUtil.mutListener.listen(707)) {
            message.setModeId(getModeId());
        }
        if (!ListenerUtil.mutListener.listen(708)) {
            messageProcessor.processIncomingMessage(onFinalizeMessage(message));
        }
    }

    protected abstract MessageBase onFinalizeMessage(MessageBase message);

    abstract int getModeId();

    abstract void sendMessage(MessageBase m) throws ConfigurationIncompleteException, OutgoingMessageSendingException, IOException;
}

class OutgoingMessageSendingException extends Exception {

    OutgoingMessageSendingException(Exception e) {
        super(e);
    }
}
