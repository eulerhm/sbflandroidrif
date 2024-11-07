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
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import ch.threema.app.services.MessageServiceImpl;
import ch.threema.app.services.messageplayer.GifMessagePlayer;
import ch.threema.app.services.messageplayer.MessagePlayer;
import ch.threema.app.ui.ControllerView;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.ImageViewUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.data.media.FileDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnimGifChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(AnimGifChatAdapterDecorator.class);

    private static final String LISTENER_TAG = "decorator";

    private GifMessagePlayer gifMessagePlayer;

    public AnimGifChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper decoratorHelper) {
        super(context, messageModel, decoratorHelper);
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        final long fileSize;
        if (!ListenerUtil.mutListener.listen(7336)) {
            logger.debug("configureChatMessage - position " + position);
        }
        if (!ListenerUtil.mutListener.listen(7337)) {
            gifMessagePlayer = (GifMessagePlayer) this.getMessagePlayerService().createPlayer(this.getMessageModel(), (Activity) this.getContext(), this.helper.getMessageReceiver());
        }
        if (!ListenerUtil.mutListener.listen(7338)) {
            holder.messagePlayer = gifMessagePlayer;
        }
        if (!ListenerUtil.mutListener.listen(7350)) {
            /*
		 * setup click listeners
		 */
            if (holder.controller != null) {
                if (!ListenerUtil.mutListener.listen(7349)) {
                    holder.controller.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            int status = holder.controller.getStatus();
                            if (!ListenerUtil.mutListener.listen(7348)) {
                                switch(status) {
                                    case ControllerView.STATUS_READY_TO_PLAY:
                                    case ControllerView.STATUS_READY_TO_DOWNLOAD:
                                        if (!ListenerUtil.mutListener.listen(7339)) {
                                            gifMessagePlayer.open();
                                        }
                                        break;
                                    case ControllerView.STATUS_PROGRESSING:
                                        if (!ListenerUtil.mutListener.listen(7345)) {
                                            if ((ListenerUtil.mutListener.listen(7342) ? (getMessageModel().isOutbox() || ((ListenerUtil.mutListener.listen(7341) ? ((ListenerUtil.mutListener.listen(7340) ? (getMessageModel().getState() == MessageState.TRANSCODING && getMessageModel().getState() == MessageState.PENDING) : (getMessageModel().getState() == MessageState.TRANSCODING || getMessageModel().getState() == MessageState.PENDING)) && getMessageModel().getState() == MessageState.SENDING) : ((ListenerUtil.mutListener.listen(7340) ? (getMessageModel().getState() == MessageState.TRANSCODING && getMessageModel().getState() == MessageState.PENDING) : (getMessageModel().getState() == MessageState.TRANSCODING || getMessageModel().getState() == MessageState.PENDING)) || getMessageModel().getState() == MessageState.SENDING)))) : (getMessageModel().isOutbox() && ((ListenerUtil.mutListener.listen(7341) ? ((ListenerUtil.mutListener.listen(7340) ? (getMessageModel().getState() == MessageState.TRANSCODING && getMessageModel().getState() == MessageState.PENDING) : (getMessageModel().getState() == MessageState.TRANSCODING || getMessageModel().getState() == MessageState.PENDING)) && getMessageModel().getState() == MessageState.SENDING) : ((ListenerUtil.mutListener.listen(7340) ? (getMessageModel().getState() == MessageState.TRANSCODING && getMessageModel().getState() == MessageState.PENDING) : (getMessageModel().getState() == MessageState.TRANSCODING || getMessageModel().getState() == MessageState.PENDING)) || getMessageModel().getState() == MessageState.SENDING)))))) {
                                                if (!ListenerUtil.mutListener.listen(7344)) {
                                                    getMessageService().remove(getMessageModel());
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7343)) {
                                                    gifMessagePlayer.cancel();
                                                }
                                            }
                                        }
                                        break;
                                    case ControllerView.STATUS_READY_TO_RETRY:
                                        if (!ListenerUtil.mutListener.listen(7347)) {
                                            if (onClickRetry != null) {
                                                if (!ListenerUtil.mutListener.listen(7346)) {
                                                    onClickRetry.onClick(getMessageModel());
                                                }
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7358)) {
            this.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7357)) {
                        if (!isInChoiceMode()) {
                            if (!ListenerUtil.mutListener.listen(7353)) {
                                if (((ListenerUtil.mutListener.listen(7351) ? (!getPreferenceService().isGifAutoplay() && holder.controller.getStatus() == ControllerView.STATUS_READY_TO_DOWNLOAD) : (!getPreferenceService().isGifAutoplay() || holder.controller.getStatus() == ControllerView.STATUS_READY_TO_DOWNLOAD)))) {
                                    if (!ListenerUtil.mutListener.listen(7352)) {
                                        gifMessagePlayer.open();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7356)) {
                                if ((ListenerUtil.mutListener.listen(7354) ? (getPreferenceService().isGifAutoplay() || holder.controller.getStatus() == ControllerView.STATUS_NONE) : (getPreferenceService().isGifAutoplay() && holder.controller.getStatus() == ControllerView.STATUS_NONE))) {
                                    if (!ListenerUtil.mutListener.listen(7355)) {
                                        gifMessagePlayer.openInExternalPlayer(null);
                                    }
                                }
                            }
                        }
                    }
                }
            }, holder.messageBlockView);
        }
        /*
		 * get thumbnail
		 */
        Bitmap thumbnail;
        try {
            thumbnail = this.getFileService().getMessageThumbnailBitmap(this.getMessageModel(), this.getThumbnailCache());
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7359)) {
                logger.error("Exception", e);
            }
            thumbnail = null;
        }
        final FileDataModel fileData = this.getMessageModel().getFileData();
        fileSize = fileData.getFileSize();
        if (!ListenerUtil.mutListener.listen(7369)) {
            if (thumbnail != null) {
                if (!ListenerUtil.mutListener.listen(7362)) {
                    ImageViewUtil.showRoundedBitmap(getContext(), holder.contentView, holder.attachmentImage, thumbnail, this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7363)) {
                    holder.bodyTextView.setWidth(this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7365)) {
                    if (holder.attachmentImage != null) {
                        if (!ListenerUtil.mutListener.listen(7364)) {
                            holder.attachmentImage.invalidate();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7368)) {
                    if (fileData.getRenderingType() == FileData.RENDERING_STICKER) {
                        if (!ListenerUtil.mutListener.listen(7367)) {
                            holder.messageBlockView.setBackground(null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7366)) {
                            setDefaultBackground(holder);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7360)) {
                    // TODO show placeholder
                    this.showHide(holder.attachmentImage, false);
                }
                if (!ListenerUtil.mutListener.listen(7361)) {
                    holder.controller.setHidden();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7373)) {
            if (!TestUtil.empty(fileData.getCaption())) {
                if (!ListenerUtil.mutListener.listen(7371)) {
                    holder.bodyTextView.setText(formatTextString(fileData.getCaption(), this.filterString));
                }
                if (!ListenerUtil.mutListener.listen(7372)) {
                    this.showHide(holder.bodyTextView, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7370)) {
                    this.showHide(holder.bodyTextView, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7374)) {
            RuntimeUtil.runOnUiThread(() -> setControllerState(holder, fileData, fileSize));
        }
        if (!ListenerUtil.mutListener.listen(7375)) {
            this.setDatePrefix(FileUtil.getFileMessageDatePrefix(getContext(), getMessageModel(), "GIF"), 0);
        }
        if (!ListenerUtil.mutListener.listen(7381)) {
            gifMessagePlayer.attachContainer(holder.attachmentImage).addListener(LISTENER_TAG, new MessagePlayer.DecryptionListener() {

                @Override
                public void onStart(AbstractMessageModel messageModel) {
                    if (!ListenerUtil.mutListener.listen(7379)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (!helper.getPreferenceService().isGifAutoplay()) {
                                holder.controller.setProgressing();
                            }
                        });
                    }
                }

                @Override
                public void onEnd(final AbstractMessageModel messageModel, final boolean success, final String message, final File decryptedFile) {
                    if (!ListenerUtil.mutListener.listen(7380)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            holder.controller.setNeutral();
                            if (success) {
                                if (helper.getPreferenceService().isGifAutoplay()) {
                                    holder.controller.setVisibility(View.INVISIBLE);
                                } else {
                                    setControllerState(holder, messageModel.getFileData(), messageModel.getFileData().getFileSize());
                                }
                            } else {
                                holder.controller.setVisibility(View.GONE);
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
                    if (!ListenerUtil.mutListener.listen(7376)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgressingDeterminate(100));
                    }
                }

                @Override
                public void onStatusUpdate(AbstractMessageModel messageModel, final int progress) {
                    if (!ListenerUtil.mutListener.listen(7377)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgress(progress));
                    }
                }

                @Override
                public void onEnd(AbstractMessageModel messageModel, final boolean success, final String message) {
                    if (!ListenerUtil.mutListener.listen(7378)) {
                        // hide progressbar
                        RuntimeUtil.runOnUiThread(() -> {
                            // report error
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
            });
        }
    }

    private void setControllerState(ComposeMessageHolder holder, FileDataModel fileData, long fileSize) {
        if (!ListenerUtil.mutListener.listen(7393)) {
            if (this.getMessageModel().isOutbox()) {
                if (!ListenerUtil.mutListener.listen(7392)) {
                    // outgoing message
                    switch(this.getMessageModel().getState()) {
                        case TRANSCODING:
                            if (!ListenerUtil.mutListener.listen(7388)) {
                                holder.controller.setTranscoding();
                            }
                            break;
                        case PENDING:
                        case SENDING:
                            if (!ListenerUtil.mutListener.listen(7389)) {
                                holder.controller.setProgressing();
                            }
                            break;
                        case SENDFAILED:
                            if (!ListenerUtil.mutListener.listen(7390)) {
                                holder.controller.setRetry();
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(7391)) {
                                setAutoplay(fileData, fileSize, holder);
                            }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7387)) {
                    // incoming message
                    if ((ListenerUtil.mutListener.listen(7382) ? (this.getMessageModel() != null || this.getMessageModel().getState() == MessageState.PENDING) : (this.getMessageModel() != null && this.getMessageModel().getState() == MessageState.PENDING))) {
                        if (!ListenerUtil.mutListener.listen(7386)) {
                            if (fileData.isDownloaded()) {
                                if (!ListenerUtil.mutListener.listen(7385)) {
                                    holder.controller.setProgressing();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7384)) {
                                    holder.controller.setProgressingDeterminate(100);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7383)) {
                            setAutoplay(fileData, fileSize, holder);
                        }
                    }
                }
            }
        }
    }

    private void setAutoplay(FileDataModel fileData, long fileSize, ComposeMessageHolder holder) {
        if (!ListenerUtil.mutListener.listen(7394)) {
            logger.debug("setAutoPlay holder position " + holder.position);
        }
        if (!ListenerUtil.mutListener.listen(7411)) {
            if (fileData.isDownloaded()) {
                if (!ListenerUtil.mutListener.listen(7410)) {
                    if ((ListenerUtil.mutListener.listen(7406) ? (helper.getPreferenceService().isGifAutoplay() || gifMessagePlayer != null) : (helper.getPreferenceService().isGifAutoplay() && gifMessagePlayer != null))) {
                        if (!ListenerUtil.mutListener.listen(7408)) {
                            gifMessagePlayer.autoPlay();
                        }
                        if (!ListenerUtil.mutListener.listen(7409)) {
                            holder.controller.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7407)) {
                            holder.controller.setPlay();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7405)) {
                    if ((ListenerUtil.mutListener.listen(7401) ? ((ListenerUtil.mutListener.listen(7395) ? (helper.getPreferenceService().isGifAutoplay() || gifMessagePlayer != null) : (helper.getPreferenceService().isGifAutoplay() && gifMessagePlayer != null)) || (ListenerUtil.mutListener.listen(7400) ? (fileSize >= MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7399) ? (fileSize <= MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7398) ? (fileSize > MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7397) ? (fileSize != MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7396) ? (fileSize == MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (fileSize < MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO))))))) : ((ListenerUtil.mutListener.listen(7395) ? (helper.getPreferenceService().isGifAutoplay() || gifMessagePlayer != null) : (helper.getPreferenceService().isGifAutoplay() && gifMessagePlayer != null)) && (ListenerUtil.mutListener.listen(7400) ? (fileSize >= MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7399) ? (fileSize <= MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7398) ? (fileSize > MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7397) ? (fileSize != MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (ListenerUtil.mutListener.listen(7396) ? (fileSize == MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO) : (fileSize < MessageServiceImpl.FILE_AUTO_DOWNLOAD_MAX_SIZE_ISO))))))))) {
                        if (!ListenerUtil.mutListener.listen(7403)) {
                            gifMessagePlayer.autoPlay();
                        }
                        if (!ListenerUtil.mutListener.listen(7404)) {
                            holder.controller.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7402)) {
                            holder.controller.setReadyToDownload();
                        }
                    }
                }
            }
        }
    }
}
