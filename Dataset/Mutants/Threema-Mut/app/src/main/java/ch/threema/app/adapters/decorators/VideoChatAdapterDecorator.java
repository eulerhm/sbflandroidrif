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

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Toast;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import ch.threema.app.R;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.services.messageplayer.MessagePlayer;
import ch.threema.app.ui.ControllerView;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.ImageViewUtil;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import static ch.threema.storage.models.data.media.FileDataModel.METADATA_KEY_DURATION;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(VideoChatAdapterDecorator.class);

    private static final String LISTENER_TAG = "decorator";

    public VideoChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        final MessagePlayer videoMessagePlayer = this.getMessagePlayerService().createPlayer(this.getMessageModel(), (Activity) this.getContext(), helper.getMessageReceiver());
        if (!ListenerUtil.mutListener.listen(7947)) {
            logger.debug("configureChatMessage Video on position " + position + " instance " + VideoChatAdapterDecorator.this + " holder " + holder + " messageplayer = " + videoMessagePlayer);
        }
        if (!ListenerUtil.mutListener.listen(7948)) {
            holder.messagePlayer = videoMessagePlayer;
        }
        if (!ListenerUtil.mutListener.listen(7949)) {
            RuntimeUtil.runOnUiThread(() -> setControllerState(holder));
        }
        Bitmap thumbnail;
        try {
            thumbnail = this.getFileService().getMessageThumbnailBitmap(this.getMessageModel(), this.getThumbnailCache());
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7950)) {
                logger.error("Exception", e);
            }
            thumbnail = null;
        }
        if (!ListenerUtil.mutListener.listen(7954)) {
            this.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7953)) {
                        if ((ListenerUtil.mutListener.listen(7951) ? (!isInChoiceMode() || getMessageModel().getState() != MessageState.TRANSCODING) : (!isInChoiceMode() && getMessageModel().getState() != MessageState.TRANSCODING))) {
                            if (!ListenerUtil.mutListener.listen(7952)) {
                                videoMessagePlayer.open();
                            }
                        }
                    }
                }
            }, holder.messageBlockView);
        }
        if (!ListenerUtil.mutListener.listen(7965)) {
            holder.controller.setOnClickListener(new DebouncedOnClickListener(500) {

                @Override
                public void onDebouncedClick(View v) {
                    int status = holder.controller.getStatus();
                    if (!ListenerUtil.mutListener.listen(7955)) {
                        logger.debug("onClick status = " + status);
                    }
                    if (!ListenerUtil.mutListener.listen(7964)) {
                        switch(status) {
                            case ControllerView.STATUS_READY_TO_PLAY:
                            case ControllerView.STATUS_READY_TO_DOWNLOAD:
                                if (!ListenerUtil.mutListener.listen(7956)) {
                                    videoMessagePlayer.open();
                                }
                                break;
                            case ControllerView.STATUS_PROGRESSING:
                                if (!ListenerUtil.mutListener.listen(7961)) {
                                    if ((ListenerUtil.mutListener.listen(7958) ? (getMessageModel().isOutbox() || ((ListenerUtil.mutListener.listen(7957) ? (getMessageModel().getState() == MessageState.PENDING && getMessageModel().getState() == MessageState.SENDING) : (getMessageModel().getState() == MessageState.PENDING || getMessageModel().getState() == MessageState.SENDING)))) : (getMessageModel().isOutbox() && ((ListenerUtil.mutListener.listen(7957) ? (getMessageModel().getState() == MessageState.PENDING && getMessageModel().getState() == MessageState.SENDING) : (getMessageModel().getState() == MessageState.PENDING || getMessageModel().getState() == MessageState.SENDING)))))) {
                                        if (!ListenerUtil.mutListener.listen(7960)) {
                                            getMessageService().cancelMessageUpload(getMessageModel());
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7959)) {
                                            videoMessagePlayer.cancel();
                                        }
                                    }
                                }
                                break;
                            case ControllerView.STATUS_TRANSCODING:
                                // no click while processing
                                break;
                            case ControllerView.STATUS_READY_TO_RETRY:
                                if (!ListenerUtil.mutListener.listen(7963)) {
                                    if (onClickRetry != null) {
                                        if (!ListenerUtil.mutListener.listen(7962)) {
                                            onClickRetry.onClick(getMessageModel());
                                        }
                                    }
                                }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7970)) {
            if (thumbnail != null) {
                if (!ListenerUtil.mutListener.listen(7968)) {
                    ImageViewUtil.showRoundedBitmap(getContext(), holder.contentView, holder.attachmentImage, thumbnail, this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7969)) {
                    holder.bodyTextView.setWidth(this.getThumbnailWidth());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7966)) {
                    ImageViewUtil.showPlaceholderBitmap(holder.contentView, holder.attachmentImage, this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7967)) {
                    holder.bodyTextView.setWidth(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7971)) {
            holder.attachmentImage.setContentDescription(getContext().getString(R.string.video_placeholder));
        }
        if (!ListenerUtil.mutListener.listen(7972)) {
            this.showHide(holder.bodyTextView, false);
        }
        if (!ListenerUtil.mutListener.listen(8034)) {
            if ((ListenerUtil.mutListener.listen(7973) ? (this.getMessageModel().getType() == MessageType.VIDEO || this.getMessageModel().getVideoData() != null) : (this.getMessageModel().getType() == MessageType.VIDEO && this.getMessageModel().getVideoData() != null))) {
                String datePrefixString = "";
                if (!ListenerUtil.mutListener.listen(8016)) {
                    this.dateContentDescriptionPreifx = "";
                }
                long duration = this.getMessageModel().getVideoData().getDuration();
                int size = this.getMessageModel().getVideoData().getVideoSize();
                if (!ListenerUtil.mutListener.listen(8024)) {
                    // do not show duration if 0
                    if ((ListenerUtil.mutListener.listen(8021) ? (duration >= 0) : (ListenerUtil.mutListener.listen(8020) ? (duration <= 0) : (ListenerUtil.mutListener.listen(8019) ? (duration < 0) : (ListenerUtil.mutListener.listen(8018) ? (duration != 0) : (ListenerUtil.mutListener.listen(8017) ? (duration == 0) : (duration > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(8022)) {
                            datePrefixString = StringConversionUtil.secondsToString(duration, false);
                        }
                        if (!ListenerUtil.mutListener.listen(8023)) {
                            this.dateContentDescriptionPreifx = getContext().getString(R.string.duration) + ": " + StringConversionUtil.getDurationStringHuman(getContext(), duration);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8031)) {
                    if ((ListenerUtil.mutListener.listen(8029) ? (size >= 0) : (ListenerUtil.mutListener.listen(8028) ? (size <= 0) : (ListenerUtil.mutListener.listen(8027) ? (size < 0) : (ListenerUtil.mutListener.listen(8026) ? (size != 0) : (ListenerUtil.mutListener.listen(8025) ? (size == 0) : (size > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(8030)) {
                            datePrefixString += " (" + Formatter.formatShortFileSize(getContext(), size) + ")";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8032)) {
                    this.setDatePrefix(datePrefixString, holder.dateView.getTextSize());
                }
                if (!ListenerUtil.mutListener.listen(8033)) {
                    setDefaultBackground(holder);
                }
            } else if ((ListenerUtil.mutListener.listen(7974) ? (this.getMessageModel().getType() == MessageType.FILE || this.getMessageModel().getFileData() != null) : (this.getMessageModel().getType() == MessageType.FILE && this.getMessageModel().getFileData() != null))) {
                String datePrefixString = "";
                long duration = 0;
                Float durationF = this.getMessageModel().getFileData().getMetaDataFloat(METADATA_KEY_DURATION);
                if (!ListenerUtil.mutListener.listen(7984)) {
                    if (durationF != null) {
                        if (!ListenerUtil.mutListener.listen(7975)) {
                            duration = durationF.longValue();
                        }
                        if (!ListenerUtil.mutListener.listen(7983)) {
                            if ((ListenerUtil.mutListener.listen(7980) ? (duration >= 0) : (ListenerUtil.mutListener.listen(7979) ? (duration <= 0) : (ListenerUtil.mutListener.listen(7978) ? (duration < 0) : (ListenerUtil.mutListener.listen(7977) ? (duration != 0) : (ListenerUtil.mutListener.listen(7976) ? (duration == 0) : (duration > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(7981)) {
                                    datePrefixString = StringConversionUtil.secondsToString(duration, false);
                                }
                                if (!ListenerUtil.mutListener.listen(7982)) {
                                    this.dateContentDescriptionPreifx = getContext().getString(R.string.duration) + ": " + StringConversionUtil.getDurationStringHuman(getContext(), duration);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8000)) {
                    if (this.getMessageModel().getFileData().isDownloaded()) {
                        if (!ListenerUtil.mutListener.listen(7999)) {
                            datePrefixString = "";
                        }
                    } else {
                        long size = this.getMessageModel().getFileData().getFileSize();
                        if (!ListenerUtil.mutListener.listen(7998)) {
                            if ((ListenerUtil.mutListener.listen(7989) ? (size >= 0) : (ListenerUtil.mutListener.listen(7988) ? (size <= 0) : (ListenerUtil.mutListener.listen(7987) ? (size < 0) : (ListenerUtil.mutListener.listen(7986) ? (size != 0) : (ListenerUtil.mutListener.listen(7985) ? (size == 0) : (size > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(7997)) {
                                    if ((ListenerUtil.mutListener.listen(7994) ? (duration >= 0) : (ListenerUtil.mutListener.listen(7993) ? (duration <= 0) : (ListenerUtil.mutListener.listen(7992) ? (duration < 0) : (ListenerUtil.mutListener.listen(7991) ? (duration != 0) : (ListenerUtil.mutListener.listen(7990) ? (duration == 0) : (duration > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(7996)) {
                                            datePrefixString += " (" + Formatter.formatShortFileSize(getContext(), size) + ")";
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7995)) {
                                            datePrefixString = Formatter.formatShortFileSize(getContext(), size);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8002)) {
                    if (holder.dateView != null) {
                        if (!ListenerUtil.mutListener.listen(8001)) {
                            this.setDatePrefix(datePrefixString, 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8012)) {
                    if (!TestUtil.empty(this.getMessageModel().getFileData().getCaption())) {
                        if (!ListenerUtil.mutListener.listen(8004)) {
                            holder.bodyTextView.setText(formatTextString(this.getMessageModel().getFileData().getCaption(), this.filterString));
                        }
                        if (!ListenerUtil.mutListener.listen(8010)) {
                            LinkifyUtil.getInstance().linkify((ComposeMessageFragment) helper.getFragment(), holder.bodyTextView, this.getMessageModel(), (ListenerUtil.mutListener.listen(8009) ? (this.getMessageModel().getFileData().getCaption().length() >= 80) : (ListenerUtil.mutListener.listen(8008) ? (this.getMessageModel().getFileData().getCaption().length() <= 80) : (ListenerUtil.mutListener.listen(8007) ? (this.getMessageModel().getFileData().getCaption().length() > 80) : (ListenerUtil.mutListener.listen(8006) ? (this.getMessageModel().getFileData().getCaption().length() != 80) : (ListenerUtil.mutListener.listen(8005) ? (this.getMessageModel().getFileData().getCaption().length() == 80) : (this.getMessageModel().getFileData().getCaption().length() < 80)))))), actionModeStatus.getActionModeEnabled(), onClickElement);
                        }
                        if (!ListenerUtil.mutListener.listen(8011)) {
                            this.showHide(holder.bodyTextView, true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8003)) {
                            this.showHide(holder.bodyTextView, false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8015)) {
                    if (this.getMessageModel().getFileData().getRenderingType() == FileData.RENDERING_STICKER) {
                        if (!ListenerUtil.mutListener.listen(8014)) {
                            holder.messageBlockView.setBackground(null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8013)) {
                            setDefaultBackground(holder);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8042)) {
            videoMessagePlayer.addListener(LISTENER_TAG, new MessagePlayer.DecryptionListener() {

                @Override
                public void onStart(AbstractMessageModel messageModel) {
                    if (!ListenerUtil.mutListener.listen(8040)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgressing(false));
                    }
                }

                @Override
                public void onEnd(final AbstractMessageModel messageModel, final boolean success, final String message, File decryptedFile) {
                    if (!ListenerUtil.mutListener.listen(8041)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            setControllerState(holder);
                            if (!success) {
                                if (!TestUtil.empty(message)) {
                                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }).addListener(LISTENER_TAG, new MessagePlayer.DownloadListener() {

                @Override
                public void onStart(AbstractMessageModel messageModel) {
                    if (!ListenerUtil.mutListener.listen(8037)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgressingDeterminate(100));
                    }
                }

                @Override
                public void onStatusUpdate(AbstractMessageModel messageModel, final int progress) {
                    if (!ListenerUtil.mutListener.listen(8038)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgress(progress));
                    }
                }

                @Override
                public void onEnd(AbstractMessageModel messageModel, final boolean success, final String message) {
                    if (!ListenerUtil.mutListener.listen(8039)) {
                        // hide progressbar
                        RuntimeUtil.runOnUiThread(() -> {
                            if (success) {
                                holder.controller.setPlay();
                            } else {
                                holder.controller.setReadyToDownload();
                                if (!TestUtil.empty(message)) {
                                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }).addListener(LISTENER_TAG, new MessagePlayer.TranscodeListener() {

                @Override
                public void onStart() {
                    if (!ListenerUtil.mutListener.listen(8035)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            logger.debug("**** onStart");
                            holder.transcoderView.setProgress(0);
                        });
                    }
                }

                @Override
                public void onStatusUpdate(final int progress) {
                    if (!ListenerUtil.mutListener.listen(8036)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            holder.transcoderView.setProgress(progress);
                        });
                    }
                }
            });
        }
    }

    private void setControllerState(ComposeMessageHolder holder) {
        if (!ListenerUtil.mutListener.listen(8072)) {
            if ((ListenerUtil.mutListener.listen(8043) ? (this.getMessageModel().isOutbox() || !(this.getMessageModel() instanceof DistributionListMessageModel)) : (this.getMessageModel().isOutbox() && !(this.getMessageModel() instanceof DistributionListMessageModel)))) {
                if (!ListenerUtil.mutListener.listen(8056)) {
                    // outgoing message
                    logger.debug("**** Video MessageStatus: " + this.getMessageModel().getState());
                }
                if (!ListenerUtil.mutListener.listen(8071)) {
                    switch(this.getMessageModel().getState()) {
                        case TRANSCODING:
                            if (!ListenerUtil.mutListener.listen(8057)) {
                                holder.controller.setTranscoding();
                            }
                            if (!ListenerUtil.mutListener.listen(8061)) {
                                if (holder.transcoderView != null) {
                                    if (!ListenerUtil.mutListener.listen(8058)) {
                                        holder.transcoderView.setMessageModel(this.getMessageModel());
                                    }
                                    if (!ListenerUtil.mutListener.listen(8059)) {
                                        holder.transcoderView.setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(8060)) {
                                        holder.transcoderView.setProgress(holder.messagePlayer.getTranscodeProgress());
                                    }
                                }
                            }
                            break;
                        case PENDING:
                        case SENDING:
                            if (!ListenerUtil.mutListener.listen(8062)) {
                                holder.controller.setProgressing();
                            }
                            if (!ListenerUtil.mutListener.listen(8064)) {
                                if (holder.transcoderView != null) {
                                    if (!ListenerUtil.mutListener.listen(8063)) {
                                        holder.transcoderView.setVisibility(View.GONE);
                                    }
                                }
                            }
                            break;
                        case SENDFAILED:
                            if (!ListenerUtil.mutListener.listen(8065)) {
                                holder.controller.setRetry();
                            }
                            if (!ListenerUtil.mutListener.listen(8067)) {
                                if (holder.transcoderView != null) {
                                    if (!ListenerUtil.mutListener.listen(8066)) {
                                        holder.transcoderView.setVisibility(View.GONE);
                                    }
                                }
                            }
                            break;
                        case SENT:
                        case DELIVERED:
                        case READ:
                            if (!ListenerUtil.mutListener.listen(8068)) {
                                holder.controller.setPlay();
                            }
                            if (!ListenerUtil.mutListener.listen(8070)) {
                                if (holder.transcoderView != null) {
                                    if (!ListenerUtil.mutListener.listen(8069)) {
                                        holder.transcoderView.setVisibility(View.GONE);
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            } else {
                // incoming message
                boolean isDownloaded = this.getMessageModel().getType() == MessageType.VIDEO ? ((ListenerUtil.mutListener.listen(8045) ? (this.getMessageModel().getVideoData() != null || this.getMessageModel().getVideoData().isDownloaded()) : (this.getMessageModel().getVideoData() != null && this.getMessageModel().getVideoData().isDownloaded()))) : ((ListenerUtil.mutListener.listen(8044) ? (this.getMessageModel().getFileData() != null || this.getMessageModel().getFileData().isDownloaded()) : (this.getMessageModel().getFileData() != null && this.getMessageModel().getFileData().isDownloaded())));
                if (!ListenerUtil.mutListener.listen(8048)) {
                    if (isDownloaded) {
                        if (!ListenerUtil.mutListener.listen(8047)) {
                            holder.controller.setPlay();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8046)) {
                            holder.controller.setReadyToDownload();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8055)) {
                    if (holder.messagePlayer != null) {
                        if (!ListenerUtil.mutListener.listen(8049)) {
                            logger.debug("messagePlayerState: " + holder.messagePlayer.getState());
                        }
                        if (!ListenerUtil.mutListener.listen(8054)) {
                            switch(holder.messagePlayer.getState()) {
                                case MessagePlayer.State_DOWNLOADING:
                                    if (!ListenerUtil.mutListener.listen(8052)) {
                                        if (!isDownloaded) {
                                            if (!ListenerUtil.mutListener.listen(8050)) {
                                                holder.controller.setProgressingDeterminate(100);
                                            }
                                            if (!ListenerUtil.mutListener.listen(8051)) {
                                                holder.controller.setProgress(holder.messagePlayer.getDownloadProgress());
                                            }
                                        }
                                    }
                                    break;
                                case MessagePlayer.State_DECRYPTING:
                                    if (!ListenerUtil.mutListener.listen(8053)) {
                                        holder.controller.setProgressing();
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
}
