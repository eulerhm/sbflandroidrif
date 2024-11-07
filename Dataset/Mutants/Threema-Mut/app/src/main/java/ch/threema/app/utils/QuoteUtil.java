/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.cache.ThumbnailCache;
import ch.threema.app.messagereceiver.MessageReceiver.MessageReceiverType;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.UserService;
import ch.threema.client.Utils;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.MessageType;
import static ch.threema.app.messagereceiver.MessageReceiver.Type_CONTACT;
import static ch.threema.app.messagereceiver.MessageReceiver.Type_DISTRIBUTION_LIST;
import static ch.threema.app.messagereceiver.MessageReceiver.Type_GROUP;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class QuoteUtil {

    private static final Logger logger = LoggerFactory.getLogger(QuoteUtil.class);

    private static final Pattern bodyMatchPattern = Pattern.compile("(?sm)(\\A> .*?)^(?!> ).+");

    private static final Pattern quoteV1MatchPattern = Pattern.compile("(?sm)\\A> ([A-Z0-9*]{8}): (.*?)^(?!> ).+");

    private static final Pattern quoteV2MatchPattern = Pattern.compile("(?sm)\\A> quote #([0-9a-f]{16})\\n\\n(.*)");

    private static final String QUOTE_V2_PATTERN = "> quote #[0-9a-f]{16}\\n\\n";

    private static final int QUOTE_V2_SIGNATURE_LENGTH = 27;

    private static final String QUOTE_PREFIX = "> ";

    public static final int QUOTE_TYPE_NONE = 0;

    public static final int QUOTE_TYPE_V1 = 1;

    public static final int QUOTE_TYPE_V2 = 2;

    private static final int MAX_QUOTE_CONTENTS_LENGTH = 256;

    /**
     *  Get all content to be displayed in a message containing a quote
     *
     *  @param includeMessageModel If set to `true`, the quoted message model is included in the `QuoteContent`
     */
    @Nullable
    public static QuoteContent getQuoteContent(@NonNull AbstractMessageModel messageModel, @MessageReceiverType int receiverType, boolean includeMessageModel, @Nullable ThumbnailCache thumbnailCache, @NonNull Context context, @NonNull MessageService messageService, @NonNull UserService userService, @NonNull FileService fileService) {
        if (messageModel.getQuotedMessageId() != null) {
            return extractQuoteV2(messageModel, receiverType, includeMessageModel, thumbnailCache, context, messageService, userService, fileService);
        } else {
            String text = messageModel.getBody();
            if (!TestUtil.empty(text)) {
                return parseQuoteV1(text);
            }
            return null;
        }
    }

    /**
     *  Parse quote v1 contents.
     *
     *   A v1 quote message looks like this
     *
     *   > ABCDEFGH: Quoted text
     *   > Quoted text ctd.
     *
     *   Body text
     *   Body text ctd.
     */
    @Nullable
    static QuoteContent parseQuoteV1(@NonNull String text) {
        final Matcher match = quoteV1MatchPattern.matcher(text);
        try {
            if (!ListenerUtil.mutListener.listen(55281)) {
                if ((ListenerUtil.mutListener.listen(55278) ? (match.find() || (ListenerUtil.mutListener.listen(55277) ? (match.groupCount() >= 2) : (ListenerUtil.mutListener.listen(55276) ? (match.groupCount() <= 2) : (ListenerUtil.mutListener.listen(55275) ? (match.groupCount() > 2) : (ListenerUtil.mutListener.listen(55274) ? (match.groupCount() < 2) : (ListenerUtil.mutListener.listen(55273) ? (match.groupCount() != 2) : (match.groupCount() == 2))))))) : (match.find() && (ListenerUtil.mutListener.listen(55277) ? (match.groupCount() >= 2) : (ListenerUtil.mutListener.listen(55276) ? (match.groupCount() <= 2) : (ListenerUtil.mutListener.listen(55275) ? (match.groupCount() > 2) : (ListenerUtil.mutListener.listen(55274) ? (match.groupCount() < 2) : (ListenerUtil.mutListener.listen(55273) ? (match.groupCount() != 2) : (match.groupCount() == 2))))))))) {
                    final String identity = match.group(1);
                    final String quotedTextRaw = match.group(2);
                    if (!ListenerUtil.mutListener.listen(55280)) {
                        if ((ListenerUtil.mutListener.listen(55279) ? (identity != null || quotedTextRaw != null) : (identity != null && quotedTextRaw != null))) {
                            final String bodyText = text.substring(match.end(2)).trim();
                            final String quotedText = quotedTextRaw.replace("\n" + QUOTE_PREFIX, "\n").trim();
                            return QuoteContent.createV1(identity, quotedText, bodyText);
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(55272)) {
                logger.error("Could not process v1 quote", e);
            }
        }
        return null;
    }

    /**
     *  Extract quote v2 contents.
     *
     *  @param includeMessageModel If set to `true`, the quoted message model is included in the `QuoteContent`
     */
    @NonNull
    static QuoteContent extractQuoteV2(@NonNull AbstractMessageModel messageModel, @MessageReceiverType int receiverType, boolean includeMessageModel, @Nullable ThumbnailCache thumbnailCache, @NonNull Context context, @NonNull MessageService messageService, @NonNull UserService userService, @NonNull FileService fileService) {
        final String quotedMessageId = messageModel.getQuotedMessageId();
        final String bodyText = messageModel.getBody();
        final String placeholder;
        // Retrieve message model referenced by quote
        final AbstractMessageModel quotedMessageModel = messageService.getMessageModelByApiMessageId(quotedMessageId, receiverType);
        if (quotedMessageModel != null) {
            boolean receiverMatch = false;
            if (!ListenerUtil.mutListener.listen(55285)) {
                switch(receiverType) {
                    case Type_CONTACT:
                        if (!ListenerUtil.mutListener.listen(55282)) {
                            receiverMatch = quotedMessageModel.getIdentity().equals(messageModel.getIdentity());
                        }
                        break;
                    case Type_GROUP:
                        if (!ListenerUtil.mutListener.listen(55283)) {
                            receiverMatch = ((GroupMessageModel) quotedMessageModel).getGroupId() == ((GroupMessageModel) messageModel).getGroupId();
                        }
                        break;
                    case Type_DISTRIBUTION_LIST:
                        if (!ListenerUtil.mutListener.listen(55284)) {
                            receiverMatch = ((DistributionListMessageModel) quotedMessageModel).getDistributionListId() == ((DistributionListMessageModel) messageModel).getDistributionListId();
                        }
                        break;
                }
            }
            if (receiverMatch) {
                final MessageUtil.MessageViewElement viewElement = MessageUtil.getViewElement(context, quotedMessageModel);
                final String identity = quotedMessageModel.isOutbox() ? userService.getIdentity() : quotedMessageModel.getIdentity();
                @NonNull
                final String quotedText = TestUtil.empty(viewElement.text) ? (viewElement.placeholder != null ? viewElement.placeholder : "") : viewElement.text;
                @DrawableRes
                final Integer icon = viewElement.icon;
                Bitmap thumbnail = null;
                try {
                    if (!ListenerUtil.mutListener.listen(55286)) {
                        thumbnail = fileService.getMessageThumbnailBitmap(quotedMessageModel, thumbnailCache);
                    }
                } catch (Exception ignore) {
                }
                return QuoteContent.createV2(identity, quotedText, bodyText, quotedMessageId, includeMessageModel ? quotedMessageModel : null, receiverType, thumbnail, icon);
            } else {
                placeholder = context.getString(R.string.quote_not_found);
            }
        } else {
            placeholder = context.getString(R.string.quoted_message_deleted);
        }
        return QuoteContent.createV2Deleted(quotedMessageId, placeholder, bodyText);
    }

    /**
     *  Extract body text and quoted message reference from text string containing a quote v2 signature and add to MessageModel
     *  If no valid quote v2 signature is found, add full input text to body
     *  @param messageModel where to add extracted information
     *  @param text source text containing a quote v2 signature
     */
    public static void addBodyAndQuotedMessageId(@NonNull AbstractMessageModel messageModel, @Nullable String text) {
        if (!ListenerUtil.mutListener.listen(55298)) {
            if (!TestUtil.empty(text)) {
                Matcher match = quoteV2MatchPattern.matcher(text);
                try {
                    if (!ListenerUtil.mutListener.listen(55297)) {
                        if (match.find()) {
                            if (!ListenerUtil.mutListener.listen(55296)) {
                                if ((ListenerUtil.mutListener.listen(55291) ? (match.groupCount() >= 2) : (ListenerUtil.mutListener.listen(55290) ? (match.groupCount() <= 2) : (ListenerUtil.mutListener.listen(55289) ? (match.groupCount() > 2) : (ListenerUtil.mutListener.listen(55288) ? (match.groupCount() < 2) : (ListenerUtil.mutListener.listen(55287) ? (match.groupCount() != 2) : (match.groupCount() == 2))))))) {
                                    if (!ListenerUtil.mutListener.listen(55292)) {
                                        messageModel.setQuotedMessageId(match.group(1));
                                    }
                                    if (!ListenerUtil.mutListener.listen(55295)) {
                                        if (!TestUtil.empty(match.group(2))) {
                                            if (!ListenerUtil.mutListener.listen(55294)) {
                                                messageModel.setBody(match.group(2).trim());
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(55293)) {
                                                messageModel.setBody("");
                                            }
                                        }
                                    }
                                    return;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55299)) {
            messageModel.setBody(text);
        }
    }

    /**
     *  Check if the body of this message model contains a quote signature
     *  @param messageModel message model to check for quote signature
     *  @return quote type or QUOTE_TYPE_NONE if no quote was found
     */
    public static int getQuoteType(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(55303)) {
            if (messageModel != null) {
                if (!ListenerUtil.mutListener.listen(55300)) {
                    if (!TestUtil.empty(messageModel.getQuotedMessageId())) {
                        return QUOTE_TYPE_V2;
                    }
                }
                if (!ListenerUtil.mutListener.listen(55302)) {
                    if (!TestUtil.empty(messageModel.getBody())) {
                        if (!ListenerUtil.mutListener.listen(55301)) {
                            if (isQuoteV1(messageModel.getBody())) {
                                return QUOTE_TYPE_V1;
                            }
                        }
                    }
                }
            }
        }
        return QUOTE_TYPE_NONE;
    }

    public static boolean isQuoteV1(String body) {
        return (ListenerUtil.mutListener.listen(55312) ? ((ListenerUtil.mutListener.listen(55311) ? ((ListenerUtil.mutListener.listen(55310) ? ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) || body.startsWith(QUOTE_PREFIX)) : ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) && body.startsWith(QUOTE_PREFIX))) || body.charAt(10) == ':') : ((ListenerUtil.mutListener.listen(55310) ? ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) || body.startsWith(QUOTE_PREFIX)) : ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) && body.startsWith(QUOTE_PREFIX))) && body.charAt(10) == ':')) || body.contains("\n")) : ((ListenerUtil.mutListener.listen(55311) ? ((ListenerUtil.mutListener.listen(55310) ? ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) || body.startsWith(QUOTE_PREFIX)) : ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) && body.startsWith(QUOTE_PREFIX))) || body.charAt(10) == ':') : ((ListenerUtil.mutListener.listen(55310) ? ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) || body.startsWith(QUOTE_PREFIX)) : ((ListenerUtil.mutListener.listen(55309) ? (body != null || (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10))))))) : (body != null && (ListenerUtil.mutListener.listen(55308) ? (body.length() >= 10) : (ListenerUtil.mutListener.listen(55307) ? (body.length() <= 10) : (ListenerUtil.mutListener.listen(55306) ? (body.length() < 10) : (ListenerUtil.mutListener.listen(55305) ? (body.length() != 10) : (ListenerUtil.mutListener.listen(55304) ? (body.length() == 10) : (body.length() > 10)))))))) && body.startsWith(QUOTE_PREFIX))) && body.charAt(10) == ':')) && body.contains("\n")));
    }

    public static boolean isQuoteV2(String body) {
        return (ListenerUtil.mutListener.listen(55319) ? ((ListenerUtil.mutListener.listen(55318) ? (body != null || (ListenerUtil.mutListener.listen(55317) ? (body.length() >= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55316) ? (body.length() <= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55315) ? (body.length() < QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55314) ? (body.length() != QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55313) ? (body.length() == QUOTE_V2_SIGNATURE_LENGTH) : (body.length() > QUOTE_V2_SIGNATURE_LENGTH))))))) : (body != null && (ListenerUtil.mutListener.listen(55317) ? (body.length() >= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55316) ? (body.length() <= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55315) ? (body.length() < QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55314) ? (body.length() != QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55313) ? (body.length() == QUOTE_V2_SIGNATURE_LENGTH) : (body.length() > QUOTE_V2_SIGNATURE_LENGTH)))))))) || body.substring(0, QUOTE_V2_SIGNATURE_LENGTH).matches(QUOTE_V2_PATTERN)) : ((ListenerUtil.mutListener.listen(55318) ? (body != null || (ListenerUtil.mutListener.listen(55317) ? (body.length() >= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55316) ? (body.length() <= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55315) ? (body.length() < QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55314) ? (body.length() != QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55313) ? (body.length() == QUOTE_V2_SIGNATURE_LENGTH) : (body.length() > QUOTE_V2_SIGNATURE_LENGTH))))))) : (body != null && (ListenerUtil.mutListener.listen(55317) ? (body.length() >= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55316) ? (body.length() <= QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55315) ? (body.length() < QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55314) ? (body.length() != QUOTE_V2_SIGNATURE_LENGTH) : (ListenerUtil.mutListener.listen(55313) ? (body.length() == QUOTE_V2_SIGNATURE_LENGTH) : (body.length() > QUOTE_V2_SIGNATURE_LENGTH)))))))) && body.substring(0, QUOTE_V2_SIGNATURE_LENGTH).matches(QUOTE_V2_PATTERN)));
    }

    /**
     *  get body text of a message containing a quote
     *  this is safe to call on message models without a quote
     *  @param messageModel
     *  @param substituteAndTruncate if true, result is truncated at MAX_QUOTE_CONTENTS_LENGTH and ellipsis is added.
     *                               if no body text is present, alternative text sources such as captions or file names are considered
     *  @return body text
     */
    public static String getMessageBody(AbstractMessageModel messageModel, boolean substituteAndTruncate) {
        String text;
        if (messageModel.getType() == MessageType.TEXT) {
            text = messageModel.getBody();
        } else {
            text = messageModel.getCaption();
        }
        if ((ListenerUtil.mutListener.listen(55320) ? (substituteAndTruncate || TestUtil.empty(text)) : (substituteAndTruncate && TestUtil.empty(text)))) {
            text = messageModel.getCaption();
            if (TestUtil.empty(text)) {
                MessageUtil.MessageViewElement viewElement = MessageUtil.getViewElement(ThreemaApplication.getAppContext(), messageModel);
                text = viewElement.text;
                if (text == null) {
                    text = viewElement.placeholder;
                }
            }
        }
        if (text != null) {
            if (QuoteUtil.isQuoteV1(text)) {
                Matcher match = bodyMatchPattern.matcher(messageModel.getBody());
                try {
                    if (match.find()) {
                        if ((ListenerUtil.mutListener.listen(55325) ? (match.groupCount() >= 1) : (ListenerUtil.mutListener.listen(55324) ? (match.groupCount() <= 1) : (ListenerUtil.mutListener.listen(55323) ? (match.groupCount() > 1) : (ListenerUtil.mutListener.listen(55322) ? (match.groupCount() < 1) : (ListenerUtil.mutListener.listen(55321) ? (match.groupCount() != 1) : (match.groupCount() == 1))))))) {
                            text = messageModel.getBody().substring(match.end(1)).trim();
                        }
                    }
                } catch (Exception e) {
                }
            }
            if (substituteAndTruncate) {
                text = truncateQuote(text);
            }
        }
        return text;
    }

    private static String truncateQuote(String text) {
        if (!ListenerUtil.mutListener.listen(55333)) {
            if ((ListenerUtil.mutListener.listen(55330) ? (text.length() >= MAX_QUOTE_CONTENTS_LENGTH) : (ListenerUtil.mutListener.listen(55329) ? (text.length() <= MAX_QUOTE_CONTENTS_LENGTH) : (ListenerUtil.mutListener.listen(55328) ? (text.length() < MAX_QUOTE_CONTENTS_LENGTH) : (ListenerUtil.mutListener.listen(55327) ? (text.length() != MAX_QUOTE_CONTENTS_LENGTH) : (ListenerUtil.mutListener.listen(55326) ? (text.length() == MAX_QUOTE_CONTENTS_LENGTH) : (text.length() > MAX_QUOTE_CONTENTS_LENGTH))))))) {
                if (!ListenerUtil.mutListener.listen(55331)) {
                    text = Utils.truncateUTF8String(text, MAX_QUOTE_CONTENTS_LENGTH);
                }
                if (!ListenerUtil.mutListener.listen(55332)) {
                    text += "…";
                }
            }
        }
        return text;
    }

    /**
     *  Check if the supplied message can be quoted
     *  @param messageModel
     *  @return true if the message can be quoted, false otherwise
     */
    public static boolean isQuoteable(AbstractMessageModel messageModel) {
        if (ConfigUtils.canCreateV2Quotes()) {
            switch(messageModel.getType()) {
                case IMAGE:
                case FILE:
                case TEXT:
                case VIDEO:
                case BALLOT:
                case VOICEMESSAGE:
                case LOCATION:
                    return true;
                default:
                    return false;
            }
        } else {
            switch(messageModel.getType()) {
                case TEXT:
                    return true;
                case IMAGE:
                case LOCATION:
                case FILE:
                    return !TestUtil.empty(messageModel.getCaption());
                default:
                    return false;
            }
        }
    }

    /**
     *  Append quoting to text
     *
     *  TODO: create unit tests!
     *
     *  @param text
     *  @param quoteIdentity
     *  @param quoteText
     *  @return
     */
    public static String quote(String text, @Nullable String quoteIdentity, @Nullable String quoteText, AbstractMessageModel messageModel) {
        // do not quote if identity or quoting text is empty or null
        if (TestUtil.empty(quoteIdentity, quoteText)) {
            return text;
        }
        if ((ListenerUtil.mutListener.listen(55334) ? (ConfigUtils.canCreateV2Quotes() || messageModel != null) : (ConfigUtils.canCreateV2Quotes() && messageModel != null))) {
            return "> quote #" + messageModel.getApiMessageId() + "\n\n" + text;
        } else {
            String quote = quoteIdentity + ": " + quoteText;
            if (!ListenerUtil.mutListener.listen(55335)) {
                quote = quote.replaceAll(".*(\\r\\n|\\n)|.+\\z", Matcher.quoteReplacement(QuoteUtil.QUOTE_PREFIX) + "$0");
            }
            return quote + "\n\n" + text;
        }
    }

    public static class QuoteContent {

        @NonNull
        public String quotedText;

        @NonNull
        public String bodyText;

        @Nullable
        public String identity;

        @Nullable
        public String quotedMessageId;

        @Nullable
        public AbstractMessageModel quotedMessageModel;

        @Nullable
        @MessageReceiverType
        public Integer receiverType;

        @Nullable
        public Bitmap thumbnail;

        @Nullable
        @DrawableRes
        public Integer icon;

        private QuoteContent(@NonNull String quotedText, @NonNull String bodyText) {
            if (!ListenerUtil.mutListener.listen(55336)) {
                this.quotedText = quotedText;
            }
            if (!ListenerUtil.mutListener.listen(55337)) {
                this.bodyText = bodyText;
            }
        }

        /**
         *  Create a v1 quote.
         */
        @NonNull
        public static QuoteContent createV1(@NonNull String identity, @NonNull String quotedText, @NonNull String bodyText) {
            final QuoteContent content = new QuoteContent(quotedText, bodyText);
            if (!ListenerUtil.mutListener.listen(55338)) {
                content.identity = identity;
            }
            return content;
        }

        /**
         *  Create a v2 quote for a known message.
         */
        @NonNull
        public static QuoteContent createV2(@NonNull String identity, @NonNull String quotedText, @NonNull String bodyText, @NonNull String quotedMessageId, @Nullable AbstractMessageModel quotedMessageModel, @Nullable @MessageReceiverType Integer receiverType, @Nullable Bitmap thumbnail, @Nullable @DrawableRes Integer icon) {
            final QuoteContent content = new QuoteContent(quotedText, bodyText);
            if (!ListenerUtil.mutListener.listen(55339)) {
                content.identity = identity;
            }
            if (!ListenerUtil.mutListener.listen(55340)) {
                content.quotedMessageId = quotedMessageId;
            }
            if (!ListenerUtil.mutListener.listen(55341)) {
                content.quotedMessageModel = quotedMessageModel;
            }
            if (!ListenerUtil.mutListener.listen(55342)) {
                content.receiverType = receiverType;
            }
            if (!ListenerUtil.mutListener.listen(55343)) {
                content.thumbnail = thumbnail;
            }
            if (!ListenerUtil.mutListener.listen(55344)) {
                content.icon = icon;
            }
            return content;
        }

        /**
         *  Create a v2 quote for a deleted target message.
         *
         *  Thie `quotedText` should be set to `R.string.quoted_message_deleted`.
         */
        @NonNull
        public static QuoteContent createV2Deleted(@NonNull String quotedMessageId, @NonNull String quotedText, @NonNull String bodyText) {
            final QuoteContent content = new QuoteContent(quotedText, bodyText);
            if (!ListenerUtil.mutListener.listen(55345)) {
                content.quotedMessageId = quotedMessageId;
            }
            return content;
        }

        public boolean isQuoteV1() {
            return this.quotedMessageId == null;
        }

        public boolean isQuoteV2() {
            return this.quotedMessageId != null;
        }
    }
}
