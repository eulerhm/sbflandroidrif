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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.AttrRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.cache.ThumbnailCache;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DownloadService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.messageplayer.MessagePlayerService;
import ch.threema.app.ui.listitemholder.AbstractListItemHolder;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.StateBitmapUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class ChatAdapterDecorator extends AdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(ChatAdapterDecorator.class);

    public interface OnClickRetry {

        void onClick(AbstractMessageModel messageModel);
    }

    public interface OnClickElement {

        void onClick(AbstractMessageModel messageModel);
    }

    public interface OnLongClickElement {

        void onLongClick(AbstractMessageModel messageModel);
    }

    public interface OnTouchElement {

        boolean onTouch(MotionEvent motionEvent, AbstractMessageModel messageModel);
    }

    public interface ActionModeStatus {

        boolean getActionModeEnabled();
    }

    private final AbstractMessageModel messageModel;

    protected final Helper helper;

    private final StateBitmapUtil stateBitmapUtil;

    protected OnClickRetry onClickRetry = null;

    protected OnClickElement onClickElement = null;

    private OnLongClickElement onLongClickElement = null;

    private OnTouchElement onTouchElement = null;

    protected ActionModeStatus actionModeStatus = null;

    private CharSequence datePrefix = "";

    protected String dateContentDescriptionPreifx = "";

    private int groupId = 0;

    protected Map<String, Integer> identityColors = null;

    protected String filterString;

    public class ContactCache {

        public String identity;

        public String displayName;

        public Bitmap avatar;

        public ContactModel contactModel;
    }

    public static class Helper {

        private final String myIdentity;

        private final MessageService messageService;

        private final UserService userService;

        private final ContactService contactService;

        private final FileService fileService;

        private final MessagePlayerService messagePlayerService;

        private final BallotService ballotService;

        private final ThumbnailCache thumbnailCache;

        private final PreferenceService preferenceService;

        private final DownloadService downloadService;

        private final LicenseService licenseService;

        private MessageReceiver messageReceiver;

        private int thumbnailWidth;

        private final Fragment fragment;

        protected int regularColor;

        private final Map<String, ContactCache> contacts = new HashMap<>();

        private final Drawable stopwatchIcon;

        private final int maxBubbleTextLength;

        private final int maxQuoteTextLength;

        public Helper(String myIdentity, MessageService messageService, UserService userService, ContactService contactService, FileService fileService, MessagePlayerService messagePlayerService, BallotService ballotService, ThumbnailCache thumbnailCache, PreferenceService preferenceService, DownloadService downloadService, LicenseService licenseService, MessageReceiver messageReceiver, int thumbnailWidth, Fragment fragment, int regularColor, Drawable stopwatchIcon, int maxBubbleTextLength, int maxQuoteTextLength) {
            this.myIdentity = myIdentity;
            this.messageService = messageService;
            this.userService = userService;
            this.contactService = contactService;
            this.fileService = fileService;
            this.messagePlayerService = messagePlayerService;
            this.ballotService = ballotService;
            this.thumbnailCache = thumbnailCache;
            this.preferenceService = preferenceService;
            this.downloadService = downloadService;
            this.licenseService = licenseService;
            if (!ListenerUtil.mutListener.listen(7531)) {
                this.messageReceiver = messageReceiver;
            }
            if (!ListenerUtil.mutListener.listen(7532)) {
                this.thumbnailWidth = thumbnailWidth;
            }
            this.fragment = fragment;
            if (!ListenerUtil.mutListener.listen(7533)) {
                this.regularColor = regularColor;
            }
            this.stopwatchIcon = stopwatchIcon;
            this.maxBubbleTextLength = maxBubbleTextLength;
            this.maxQuoteTextLength = maxQuoteTextLength;
        }

        public Fragment getFragment() {
            return this.fragment;
        }

        public int getThumbnailWidth() {
            return this.thumbnailWidth;
        }

        public ThumbnailCache getThumbnailCache() {
            return this.thumbnailCache;
        }

        public MessagePlayerService getMessagePlayerService() {
            return this.messagePlayerService;
        }

        public FileService getFileService() {
            return this.fileService;
        }

        public UserService getUserService() {
            return this.userService;
        }

        public ContactService getContactService() {
            return this.contactService;
        }

        public MessageService getMessageService() {
            return this.messageService;
        }

        public PreferenceService getPreferenceService() {
            return this.preferenceService;
        }

        public DownloadService getDownloadService() {
            return this.downloadService;
        }

        public LicenseService getLicenseService() {
            return this.licenseService;
        }

        public String getMyIdentity() {
            return myIdentity;
        }

        public BallotService getBallotService() {
            return this.ballotService;
        }

        public Map<String, ContactCache> getContactCache() {
            return this.contacts;
        }

        public MessageReceiver getMessageReceiver() {
            return this.messageReceiver;
        }

        public void setThumbnailWidth(int preferredThumbnailWidth) {
            if (!ListenerUtil.mutListener.listen(7534)) {
                this.thumbnailWidth = preferredThumbnailWidth;
            }
        }

        public Drawable getStopwatchIcon() {
            return stopwatchIcon;
        }

        public int getMaxBubbleTextLength() {
            return maxBubbleTextLength;
        }

        public int getMaxQuoteTextLength() {
            return maxQuoteTextLength;
        }

        public void setMessageReceiver(MessageReceiver messageReceiver) {
            if (!ListenerUtil.mutListener.listen(7535)) {
                this.messageReceiver = messageReceiver;
            }
        }
    }

    public ChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context);
        this.messageModel = messageModel;
        this.helper = helper;
        this.stateBitmapUtil = StateBitmapUtil.getInstance();
        try {
            if (!ListenerUtil.mutListener.listen(7536)) {
                this.actionModeStatus = (ActionModeStatus) helper.getFragment();
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ActionModeStatus");
        }
    }

    public void setGroupMessage(int groupId, Map<String, Integer> identityColors) {
        if (!ListenerUtil.mutListener.listen(7537)) {
            this.groupId = groupId;
        }
        if (!ListenerUtil.mutListener.listen(7538)) {
            this.identityColors = identityColors;
        }
    }

    public ChatAdapterDecorator setOnClickRetry(OnClickRetry onClickRetry) {
        if (!ListenerUtil.mutListener.listen(7539)) {
            this.onClickRetry = onClickRetry;
        }
        return this;
    }

    public ChatAdapterDecorator setOnClickElement(OnClickElement onClickElement) {
        if (!ListenerUtil.mutListener.listen(7540)) {
            this.onClickElement = onClickElement;
        }
        return this;
    }

    public ChatAdapterDecorator setOnLongClickElement(OnLongClickElement onClickElement) {
        if (!ListenerUtil.mutListener.listen(7541)) {
            this.onLongClickElement = onClickElement;
        }
        return this;
    }

    public ChatAdapterDecorator setOnTouchElement(OnTouchElement onTouchElement) {
        if (!ListenerUtil.mutListener.listen(7542)) {
            this.onTouchElement = onTouchElement;
        }
        return this;
    }

    public final ChatAdapterDecorator setFilter(String filterString) {
        if (!ListenerUtil.mutListener.listen(7543)) {
            this.filterString = filterString;
        }
        return this;
    }

    @Override
    protected final void configure(final AbstractListItemHolder h, int position) {
        if (!ListenerUtil.mutListener.listen(7546)) {
            if ((ListenerUtil.mutListener.listen(7545) ? ((ListenerUtil.mutListener.listen(7544) ? (h == null && !(h instanceof ComposeMessageHolder)) : (h == null || !(h instanceof ComposeMessageHolder))) && h.position != position) : ((ListenerUtil.mutListener.listen(7544) ? (h == null && !(h instanceof ComposeMessageHolder)) : (h == null || !(h instanceof ComposeMessageHolder))) || h.position != position))) {
                return;
            }
        }
        boolean isUserMessage = (ListenerUtil.mutListener.listen(7547) ? (!this.getMessageModel().isStatusMessage() || this.getMessageModel().getType() != MessageType.STATUS) : (!this.getMessageModel().isStatusMessage() && this.getMessageModel().getType() != MessageType.STATUS));
        String identity = (messageModel.isOutbox() ? this.helper.getMyIdentity() : messageModel.getIdentity());
        final ComposeMessageHolder holder = (ComposeMessageHolder) h;
        if (!ListenerUtil.mutListener.listen(7548)) {
            // configure the chat message
            this.configureChatMessage(holder, position);
        }
        if (!ListenerUtil.mutListener.listen(7589)) {
            if (isUserMessage) {
                if (!ListenerUtil.mutListener.listen(7579)) {
                    if ((ListenerUtil.mutListener.listen(7554) ? (!messageModel.isOutbox() || (ListenerUtil.mutListener.listen(7553) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(7552) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(7551) ? (groupId < 0) : (ListenerUtil.mutListener.listen(7550) ? (groupId != 0) : (ListenerUtil.mutListener.listen(7549) ? (groupId == 0) : (groupId > 0))))))) : (!messageModel.isOutbox() && (ListenerUtil.mutListener.listen(7553) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(7552) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(7551) ? (groupId < 0) : (ListenerUtil.mutListener.listen(7550) ? (groupId != 0) : (ListenerUtil.mutListener.listen(7549) ? (groupId == 0) : (groupId > 0))))))))) {
                        ContactCache c = null;
                        if (!ListenerUtil.mutListener.listen(7559)) {
                            c = this.helper.getContactCache().get(identity);
                        }
                        ContactModel contactModel = null;
                        if (!ListenerUtil.mutListener.listen(7566)) {
                            if (c == null) {
                                if (!ListenerUtil.mutListener.listen(7560)) {
                                    contactModel = this.helper.getContactService().getByIdentity(messageModel.getIdentity());
                                }
                                if (!ListenerUtil.mutListener.listen(7561)) {
                                    c = new ContactCache();
                                }
                                if (!ListenerUtil.mutListener.listen(7562)) {
                                    c.displayName = NameUtil.getDisplayNameOrNickname(contactModel, true);
                                }
                                if (!ListenerUtil.mutListener.listen(7563)) {
                                    c.avatar = this.helper.getContactService().getAvatar(contactModel, false);
                                }
                                if (!ListenerUtil.mutListener.listen(7564)) {
                                    c.contactModel = contactModel;
                                }
                                if (!ListenerUtil.mutListener.listen(7565)) {
                                    this.helper.getContactCache().put(identity, c);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7573)) {
                            if (holder.senderView != null) {
                                if (!ListenerUtil.mutListener.listen(7567)) {
                                    holder.senderView.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(7568)) {
                                    holder.senderName.setText(c.displayName);
                                }
                                if (!ListenerUtil.mutListener.listen(7572)) {
                                    if ((ListenerUtil.mutListener.listen(7569) ? (this.identityColors != null || this.identityColors.containsKey(identity)) : (this.identityColors != null && this.identityColors.containsKey(identity)))) {
                                        if (!ListenerUtil.mutListener.listen(7571)) {
                                            holder.senderName.setTextColor(this.identityColors.get(identity));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7570)) {
                                            holder.senderName.setTextColor(this.helper.regularColor);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7578)) {
                            if (holder.avatarView != null) {
                                if (!ListenerUtil.mutListener.listen(7574)) {
                                    holder.avatarView.setImageBitmap(c.avatar);
                                }
                                if (!ListenerUtil.mutListener.listen(7575)) {
                                    holder.avatarView.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(7577)) {
                                    if (c.contactModel != null) {
                                        if (!ListenerUtil.mutListener.listen(7576)) {
                                            holder.avatarView.setBadgeVisible(this.helper.getContactService().showBadge(c.contactModel));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7556)) {
                            if (holder.avatarView != null) {
                                if (!ListenerUtil.mutListener.listen(7555)) {
                                    holder.avatarView.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7558)) {
                            if (holder.senderView != null) {
                                if (!ListenerUtil.mutListener.listen(7557)) {
                                    holder.senderView.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
                CharSequence s = MessageUtil.getDisplayDate(this.getContext(), messageModel, true);
                if (!ListenerUtil.mutListener.listen(7581)) {
                    if (s == null) {
                        if (!ListenerUtil.mutListener.listen(7580)) {
                            s = "";
                        }
                    }
                }
                CharSequence contentDescription;
                if (!TestUtil.empty(this.datePrefix)) {
                    contentDescription = this.dateContentDescriptionPreifx + ". " + getContext().getString(R.string.state_dialog_modified) + ": " + s;
                    if (!ListenerUtil.mutListener.listen(7584)) {
                        if (messageModel.isOutbox()) {
                            if (!ListenerUtil.mutListener.listen(7583)) {
                                s = TextUtils.concat(this.datePrefix, " | " + s);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7582)) {
                                s = TextUtils.concat(s + " | ", this.datePrefix);
                            }
                        }
                    }
                } else {
                    contentDescription = s;
                }
                if (!ListenerUtil.mutListener.listen(7587)) {
                    if (holder.dateView != null) {
                        if (!ListenerUtil.mutListener.listen(7585)) {
                            holder.dateView.setText(s, TextView.BufferType.SPANNABLE);
                        }
                        if (!ListenerUtil.mutListener.listen(7586)) {
                            holder.dateView.setContentDescription(contentDescription);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7588)) {
                    stateBitmapUtil.setStateDrawable(messageModel, holder.deliveredIndicator, true);
                }
            }
        }
    }

    public Spannable highlightMatches(CharSequence fullText, String filterText) {
        return TextUtil.highlightMatches(this.getContext(), fullText, filterText, true, false);
    }

    CharSequence formatTextString(@Nullable String string, String filterString) {
        return formatTextString(string, filterString, -1);
    }

    CharSequence formatTextString(@Nullable String string, String filterString, int maxLength) {
        if (!ListenerUtil.mutListener.listen(7590)) {
            if (TextUtils.isEmpty(string)) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(7606)) {
            if ((ListenerUtil.mutListener.listen(7601) ? ((ListenerUtil.mutListener.listen(7595) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(7594) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(7593) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(7592) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(7591) ? (maxLength == 0) : (maxLength > 0)))))) || (ListenerUtil.mutListener.listen(7600) ? (string.length() >= maxLength) : (ListenerUtil.mutListener.listen(7599) ? (string.length() <= maxLength) : (ListenerUtil.mutListener.listen(7598) ? (string.length() < maxLength) : (ListenerUtil.mutListener.listen(7597) ? (string.length() != maxLength) : (ListenerUtil.mutListener.listen(7596) ? (string.length() == maxLength) : (string.length() > maxLength))))))) : ((ListenerUtil.mutListener.listen(7595) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(7594) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(7593) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(7592) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(7591) ? (maxLength == 0) : (maxLength > 0)))))) && (ListenerUtil.mutListener.listen(7600) ? (string.length() >= maxLength) : (ListenerUtil.mutListener.listen(7599) ? (string.length() <= maxLength) : (ListenerUtil.mutListener.listen(7598) ? (string.length() < maxLength) : (ListenerUtil.mutListener.listen(7597) ? (string.length() != maxLength) : (ListenerUtil.mutListener.listen(7596) ? (string.length() == maxLength) : (string.length() > maxLength))))))))) {
                return highlightMatches(string.substring(0, (ListenerUtil.mutListener.listen(7605) ? (maxLength % 1) : (ListenerUtil.mutListener.listen(7604) ? (maxLength / 1) : (ListenerUtil.mutListener.listen(7603) ? (maxLength * 1) : (ListenerUtil.mutListener.listen(7602) ? (maxLength + 1) : (maxLength - 1)))))), filterString);
            }
        }
        return highlightMatches(string, filterString);
    }

    protected abstract void configureChatMessage(final ComposeMessageHolder holder, final int position);

    protected ChatAdapterDecorator setDatePrefix(String prefix, float textSize) {
        if (!ListenerUtil.mutListener.listen(7625)) {
            if ((ListenerUtil.mutListener.listen(7612) ? (!TestUtil.empty(prefix) || (ListenerUtil.mutListener.listen(7611) ? (textSize >= 0) : (ListenerUtil.mutListener.listen(7610) ? (textSize <= 0) : (ListenerUtil.mutListener.listen(7609) ? (textSize < 0) : (ListenerUtil.mutListener.listen(7608) ? (textSize != 0) : (ListenerUtil.mutListener.listen(7607) ? (textSize == 0) : (textSize > 0))))))) : (!TestUtil.empty(prefix) && (ListenerUtil.mutListener.listen(7611) ? (textSize >= 0) : (ListenerUtil.mutListener.listen(7610) ? (textSize <= 0) : (ListenerUtil.mutListener.listen(7609) ? (textSize < 0) : (ListenerUtil.mutListener.listen(7608) ? (textSize != 0) : (ListenerUtil.mutListener.listen(7607) ? (textSize == 0) : (textSize > 0))))))))) {
                Drawable icon = this.helper.getStopwatchIcon();
                if (!ListenerUtil.mutListener.listen(7622)) {
                    icon.setBounds(0, 0, (int) ((ListenerUtil.mutListener.listen(7617) ? (textSize % 0.8) : (ListenerUtil.mutListener.listen(7616) ? (textSize / 0.8) : (ListenerUtil.mutListener.listen(7615) ? (textSize - 0.8) : (ListenerUtil.mutListener.listen(7614) ? (textSize + 0.8) : (textSize * 0.8)))))), (int) ((ListenerUtil.mutListener.listen(7621) ? (textSize % 0.8) : (ListenerUtil.mutListener.listen(7620) ? (textSize / 0.8) : (ListenerUtil.mutListener.listen(7619) ? (textSize - 0.8) : (ListenerUtil.mutListener.listen(7618) ? (textSize + 0.8) : (textSize * 0.8)))))));
                }
                SpannableStringBuilder spannableString = new SpannableStringBuilder("  " + prefix);
                if (!ListenerUtil.mutListener.listen(7623)) {
                    spannableString.setSpan(new ImageSpan(icon, ImageSpan.ALIGN_BASELINE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(7624)) {
                    this.datePrefix = spannableString;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7613)) {
                    this.datePrefix = prefix;
                }
            }
        }
        return this;
    }

    protected MessageService getMessageService() {
        return this.helper.getMessageService();
    }

    protected MessagePlayerService getMessagePlayerService() {
        return this.helper.getMessagePlayerService();
    }

    protected FileService getFileService() {
        return this.helper.getFileService();
    }

    protected int getThumbnailWidth() {
        return this.helper.getThumbnailWidth();
    }

    protected ThumbnailCache getThumbnailCache() {
        return this.helper.getThumbnailCache();
    }

    protected AbstractMessageModel getMessageModel() {
        return this.messageModel;
    }

    protected PreferenceService getPreferenceService() {
        return this.helper.getPreferenceService();
    }

    protected LicenseService getLicenseService() {
        return this.helper.getLicenseService();
    }

    protected UserService getUserService() {
        return this.helper.getUserService();
    }

    protected void setOnClickListener(final View.OnClickListener onViewClickListener, View view) {
        if (!ListenerUtil.mutListener.listen(7637)) {
            if (view != null) {
                if (!ListenerUtil.mutListener.listen(7631)) {
                    view.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(7628)) {
                                if ((ListenerUtil.mutListener.listen(7626) ? (onViewClickListener != null || !actionModeStatus.getActionModeEnabled()) : (onViewClickListener != null && !actionModeStatus.getActionModeEnabled()))) {
                                    if (!ListenerUtil.mutListener.listen(7627)) {
                                        // do not propagate click if actionMode (selection mode) is enabled in parent
                                        onViewClickListener.onClick(view);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7630)) {
                                if (onClickElement != null) {
                                    if (!ListenerUtil.mutListener.listen(7629)) {
                                        // propagate event to parents
                                        onClickElement.onClick(getMessageModel());
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(7634)) {
                    // propagate long click listener
                    view.setOnLongClickListener(new View.OnLongClickListener() {

                        @Override
                        public boolean onLongClick(View v) {
                            if (!ListenerUtil.mutListener.listen(7633)) {
                                if (onLongClickElement != null) {
                                    if (!ListenerUtil.mutListener.listen(7632)) {
                                        onLongClickElement.onLongClick(getMessageModel());
                                    }
                                }
                            }
                            return false;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(7636)) {
                    // propagate touch listener
                    view.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View arg0, MotionEvent event) {
                            if (!ListenerUtil.mutListener.listen(7635)) {
                                if (onTouchElement != null) {
                                    return onTouchElement.onTouch(event, getMessageModel());
                                }
                            }
                            return false;
                        }
                    });
                }
            }
        }
    }

    void setDefaultBackground(ComposeMessageHolder holder) {
        if (!ListenerUtil.mutListener.listen(7642)) {
            if (holder.messageBlockView.getBackground() == null) {
                @AttrRes
                int attr;
                if ((ListenerUtil.mutListener.listen(7638) ? (this.getMessageModel().isOutbox() || !(this.getMessageModel() instanceof DistributionListMessageModel)) : (this.getMessageModel().isOutbox() && !(this.getMessageModel() instanceof DistributionListMessageModel)))) {
                    // outgoing
                    attr = R.attr.chat_bubble_send;
                } else {
                    // incoming
                    attr = R.attr.chat_bubble_recv;
                }
                TypedArray typedArray;
                typedArray = getContext().getTheme().obtainStyledAttributes(new int[] { attr });
                Drawable drawable = typedArray.getDrawable(0);
                if (!ListenerUtil.mutListener.listen(7639)) {
                    typedArray.recycle();
                }
                if (!ListenerUtil.mutListener.listen(7640)) {
                    holder.messageBlockView.setBackground(drawable);
                }
                if (!ListenerUtil.mutListener.listen(7641)) {
                    logger.debug("*** setDefaultBackground");
                }
            }
        }
    }
}
