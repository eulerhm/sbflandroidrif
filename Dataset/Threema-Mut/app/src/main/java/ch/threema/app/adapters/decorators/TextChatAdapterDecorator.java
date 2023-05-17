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
package ch.threema.app.adapters.decorators;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.activities.TextChatBubbleActivity;
import ch.threema.app.emojis.EmojiConversationTextView;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(ChatAdapterDecorator.class);

    private final int quoteType;

    public TextChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
        this.quoteType = QuoteUtil.getQuoteType(messageModel);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        if (!ListenerUtil.mutListener.listen(7905)) {
            if (holder.bodyTextView != null) {
                if (!ListenerUtil.mutListener.listen(7879)) {
                    holder.bodyTextView.setMovementMethod(LinkMovementMethod.getInstance());
                }
                String messageText = this.getMessageModel().getBody();
                if (!ListenerUtil.mutListener.listen(7880)) {
                    this.setOnClickListener(view -> {
                    }, holder.messageBlockView);
                }
                if (!ListenerUtil.mutListener.listen(7884)) {
                    if (quoteType != QuoteUtil.QUOTE_TYPE_NONE) {
                        QuoteUtil.QuoteContent quoteContent = configureQuote(holder, this.getMessageModel());
                        if (!ListenerUtil.mutListener.listen(7883)) {
                            if (quoteContent != null) {
                                if (!ListenerUtil.mutListener.listen(7882)) {
                                    messageText = quoteContent.bodyText;
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7881)) {
                            holder.bodyTextView.setText(formatTextString(messageText, this.filterString, helper.getMaxBubbleTextLength() + 8));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7898)) {
                    if (holder.readOnContainer != null) {
                        if (!ListenerUtil.mutListener.listen(7897)) {
                            if ((ListenerUtil.mutListener.listen(7890) ? (messageText != null || (ListenerUtil.mutListener.listen(7889) ? (messageText.length() >= helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7888) ? (messageText.length() <= helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7887) ? (messageText.length() < helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7886) ? (messageText.length() != helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7885) ? (messageText.length() == helper.getMaxBubbleTextLength()) : (messageText.length() > helper.getMaxBubbleTextLength()))))))) : (messageText != null && (ListenerUtil.mutListener.listen(7889) ? (messageText.length() >= helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7888) ? (messageText.length() <= helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7887) ? (messageText.length() < helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7886) ? (messageText.length() != helper.getMaxBubbleTextLength()) : (ListenerUtil.mutListener.listen(7885) ? (messageText.length() == helper.getMaxBubbleTextLength()) : (messageText.length() > helper.getMaxBubbleTextLength()))))))))) {
                                if (!ListenerUtil.mutListener.listen(7893)) {
                                    holder.readOnContainer.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(7895)) {
                                    if (quoteType != QuoteUtil.QUOTE_TYPE_NONE) {
                                        if (!ListenerUtil.mutListener.listen(7894)) {
                                            holder.readOnContainer.setBackgroundResource(ConfigUtils.getResourceFromAttribute(getContext(), this.getMessageModel().isOutbox() ? R.attr.chat_bubble_fade_send : R.attr.chat_bubble_fade_recv));
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(7896)) {
                                    holder.readOnButton.setOnClickListener(view -> {
                                        Intent intent = new Intent(helper.getFragment().getContext(), TextChatBubbleActivity.class);
                                        IntentDataUtil.append(this.getMessageModel(), intent);
                                        helper.getFragment().startActivity(intent);
                                    });
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7891)) {
                                    holder.readOnContainer.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(7892)) {
                                    holder.readOnButton.setOnClickListener(null);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7904)) {
                    LinkifyUtil.getInstance().linkify((ComposeMessageFragment) helper.getFragment(), holder.bodyTextView, this.getMessageModel(), (ListenerUtil.mutListener.listen(7903) ? (messageText.length() >= 80) : (ListenerUtil.mutListener.listen(7902) ? (messageText.length() <= 80) : (ListenerUtil.mutListener.listen(7901) ? (messageText.length() > 80) : (ListenerUtil.mutListener.listen(7900) ? (messageText.length() != 80) : (ListenerUtil.mutListener.listen(7899) ? (messageText.length() == 80) : (messageText.length() < 80)))))), actionModeStatus.getActionModeEnabled(), onClickElement);
                }
            }
        }
    }

    @Nullable
    private QuoteUtil.QuoteContent configureQuote(final ComposeMessageHolder holder, final AbstractMessageModel messageModel) {
        QuoteUtil.QuoteContent content = QuoteUtil.getQuoteContent(messageModel, this.helper.getMessageReceiver().getType(), false, this.helper.getThumbnailCache(), this.getContext(), this.helper.getMessageService(), this.helper.getUserService(), this.helper.getFileService());
        if (!ListenerUtil.mutListener.listen(7946)) {
            if (content != null) {
                if (!ListenerUtil.mutListener.listen(7914)) {
                    if (holder.secondaryTextView instanceof EmojiConversationTextView) {
                        if (!ListenerUtil.mutListener.listen(7906)) {
                            holder.secondaryTextView.setText(formatTextString(content.quotedText, this.filterString, helper.getMaxQuoteTextLength() + 8));
                        }
                        if (!ListenerUtil.mutListener.listen(7913)) {
                            ((EmojiConversationTextView) holder.secondaryTextView).setFade((ListenerUtil.mutListener.listen(7912) ? (content.quotedText != null || (ListenerUtil.mutListener.listen(7911) ? (content.quotedText.length() >= helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7910) ? (content.quotedText.length() <= helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7909) ? (content.quotedText.length() < helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7908) ? (content.quotedText.length() != helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7907) ? (content.quotedText.length() == helper.getMaxQuoteTextLength()) : (content.quotedText.length() > helper.getMaxQuoteTextLength()))))))) : (content.quotedText != null && (ListenerUtil.mutListener.listen(7911) ? (content.quotedText.length() >= helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7910) ? (content.quotedText.length() <= helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7909) ? (content.quotedText.length() < helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7908) ? (content.quotedText.length() != helper.getMaxQuoteTextLength()) : (ListenerUtil.mutListener.listen(7907) ? (content.quotedText.length() == helper.getMaxQuoteTextLength()) : (content.quotedText.length() > helper.getMaxQuoteTextLength())))))))));
                        }
                    }
                }
                ContactModel contactModel = this.helper.getContactService().getByIdentity(content.identity);
                if (!ListenerUtil.mutListener.listen(7930)) {
                    if (contactModel != null) {
                        if (!ListenerUtil.mutListener.listen(7920)) {
                            if (holder.tertiaryTextView != null) {
                                if (!ListenerUtil.mutListener.listen(7918)) {
                                    holder.tertiaryTextView.setText(NameUtil.getQuoteName(contactModel, this.getUserService()));
                                }
                                if (!ListenerUtil.mutListener.listen(7919)) {
                                    holder.tertiaryTextView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        int barColor = ConfigUtils.getAccentColor(getContext());
                        if (!ListenerUtil.mutListener.listen(7926)) {
                            if (!helper.getMyIdentity().equals(content.identity)) {
                                if (!ListenerUtil.mutListener.listen(7925)) {
                                    if (getMessageModel() instanceof GroupMessageModel) {
                                        if (!ListenerUtil.mutListener.listen(7924)) {
                                            if ((ListenerUtil.mutListener.listen(7922) ? (this.identityColors != null || this.identityColors.containsKey(content.identity)) : (this.identityColors != null && this.identityColors.containsKey(content.identity)))) {
                                                if (!ListenerUtil.mutListener.listen(7923)) {
                                                    barColor = this.identityColors.get(content.identity);
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7921)) {
                                            barColor = contactModel.getColor();
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7929)) {
                            if (holder.quoteBar != null) {
                                if (!ListenerUtil.mutListener.listen(7927)) {
                                    holder.quoteBar.setBackgroundColor(barColor);
                                }
                                if (!ListenerUtil.mutListener.listen(7928)) {
                                    holder.quoteBar.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7916)) {
                            if (holder.tertiaryTextView != null) {
                                if (!ListenerUtil.mutListener.listen(7915)) {
                                    holder.tertiaryTextView.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7917)) {
                            holder.quoteBar.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7935)) {
                    if (content.bodyText != null) {
                        if (!ListenerUtil.mutListener.listen(7933)) {
                            holder.bodyTextView.setText(formatTextString(content.bodyText, this.filterString, helper.getMaxBubbleTextLength() + 8));
                        }
                        if (!ListenerUtil.mutListener.listen(7934)) {
                            holder.bodyTextView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7931)) {
                            holder.bodyTextView.setText("");
                        }
                        if (!ListenerUtil.mutListener.listen(7932)) {
                            holder.bodyTextView.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7940)) {
                    if (holder.quoteThumbnail != null) {
                        if (!ListenerUtil.mutListener.listen(7939)) {
                            if (content.thumbnail != null) {
                                if (!ListenerUtil.mutListener.listen(7937)) {
                                    holder.quoteThumbnail.setImageBitmap(content.thumbnail);
                                }
                                if (!ListenerUtil.mutListener.listen(7938)) {
                                    holder.quoteThumbnail.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7936)) {
                                    holder.quoteThumbnail.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7945)) {
                    if (holder.quoteTypeImage != null) {
                        if (!ListenerUtil.mutListener.listen(7944)) {
                            if (content.icon != null) {
                                if (!ListenerUtil.mutListener.listen(7942)) {
                                    holder.quoteTypeImage.setImageResource(content.icon);
                                }
                                if (!ListenerUtil.mutListener.listen(7943)) {
                                    holder.quoteTypeImage.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7941)) {
                                    holder.quoteTypeImage.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }
        return content;
    }
}
