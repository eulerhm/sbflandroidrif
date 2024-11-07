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
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.services.messageplayer.MessagePlayer;
import ch.threema.app.ui.ControllerView;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ImageViewUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.data.media.ImageDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(ImageChatAdapterDecorator.class);

    private static final String LISTENER_TAG = "ImageDecorator";

    public ImageChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        final MessagePlayer imageMessagePlayer = this.getMessagePlayerService().createPlayer(this.getMessageModel(), (Activity) this.getContext(), helper.getMessageReceiver());
        if (!ListenerUtil.mutListener.listen(7772)) {
            logger.debug("configureChatMessage Image");
        }
        if (!ListenerUtil.mutListener.listen(7773)) {
            holder.messagePlayer = imageMessagePlayer;
        }
        Bitmap thumbnail;
        try {
            thumbnail = this.getFileService().getMessageThumbnailBitmap(this.getMessageModel(), this.getThumbnailCache());
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7774)) {
                logger.error("Exception", e);
            }
            thumbnail = null;
        }
        if (!ListenerUtil.mutListener.listen(7775)) {
            this.setOnClickListener(view -> viewImage(getMessageModel(), holder.attachmentImage), holder.messageBlockView);
        }
        if (!ListenerUtil.mutListener.listen(7787)) {
            if (holder.controller != null) {
                if (!ListenerUtil.mutListener.listen(7786)) {
                    holder.controller.setOnClickListener(new DebouncedOnClickListener(500) {

                        @Override
                        public void onDebouncedClick(View v) {
                            int status = holder.controller.getStatus();
                            if (!ListenerUtil.mutListener.listen(7785)) {
                                switch(status) {
                                    case ControllerView.STATUS_PROGRESSING:
                                        if (!ListenerUtil.mutListener.listen(7780)) {
                                            if ((ListenerUtil.mutListener.listen(7777) ? (ImageChatAdapterDecorator.this.getMessageModel().isOutbox() || ((ListenerUtil.mutListener.listen(7776) ? (ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING && ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING) : (ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING || ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING)))) : (ImageChatAdapterDecorator.this.getMessageModel().isOutbox() && ((ListenerUtil.mutListener.listen(7776) ? (ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING && ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING) : (ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING || ImageChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING)))))) {
                                                if (!ListenerUtil.mutListener.listen(7779)) {
                                                    ImageChatAdapterDecorator.this.getMessageService().cancelMessageUpload(ImageChatAdapterDecorator.this.getMessageModel());
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7778)) {
                                                    imageMessagePlayer.cancel();
                                                }
                                            }
                                        }
                                        break;
                                    case ControllerView.STATUS_READY_TO_RETRY:
                                        if (!ListenerUtil.mutListener.listen(7782)) {
                                            if (onClickRetry != null) {
                                                if (!ListenerUtil.mutListener.listen(7781)) {
                                                    onClickRetry.onClick(ImageChatAdapterDecorator.this.getMessageModel());
                                                }
                                            }
                                        }
                                        break;
                                    case ControllerView.STATUS_READY_TO_DOWNLOAD:
                                        if (!ListenerUtil.mutListener.listen(7783)) {
                                            imageMessagePlayer.open();
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(7784)) {
                                            ImageChatAdapterDecorator.this.viewImage(ImageChatAdapterDecorator.this.getMessageModel(), holder.attachmentImage);
                                        }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7794)) {
            if (thumbnail != null) {
                if (!ListenerUtil.mutListener.listen(7791)) {
                    ImageViewUtil.showRoundedBitmap(getContext(), holder.contentView, holder.attachmentImage, thumbnail, this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7792)) {
                    holder.bodyTextView.setWidth(this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7793)) {
                    this.showHide(holder.controller, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7788)) {
                    ImageViewUtil.showPlaceholderBitmap(holder.contentView, holder.attachmentImage, this.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7789)) {
                    holder.bodyTextView.setWidth(0);
                }
                if (!ListenerUtil.mutListener.listen(7790)) {
                    holder.controller.setHidden();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7797)) {
            if ((ListenerUtil.mutListener.listen(7795) ? (getContext() != null || holder.attachmentImage != null) : (getContext() != null && holder.attachmentImage != null))) {
                if (!ListenerUtil.mutListener.listen(7796)) {
                    holder.attachmentImage.setContentDescription(getContext().getString(R.string.image_placeholder));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7798)) {
            RuntimeUtil.runOnUiThread(() -> setControllerState(holder, getMessageModel().getImageData()));
        }
        if (!ListenerUtil.mutListener.listen(7808)) {
            if (!TestUtil.empty(getMessageModel().getCaption())) {
                if (!ListenerUtil.mutListener.listen(7800)) {
                    holder.bodyTextView.setText(formatTextString(getMessageModel().getCaption(), this.filterString));
                }
                if (!ListenerUtil.mutListener.listen(7806)) {
                    LinkifyUtil.getInstance().linkify((ComposeMessageFragment) helper.getFragment(), holder.bodyTextView, this.getMessageModel(), (ListenerUtil.mutListener.listen(7805) ? (getMessageModel().getCaption().length() >= 80) : (ListenerUtil.mutListener.listen(7804) ? (getMessageModel().getCaption().length() <= 80) : (ListenerUtil.mutListener.listen(7803) ? (getMessageModel().getCaption().length() > 80) : (ListenerUtil.mutListener.listen(7802) ? (getMessageModel().getCaption().length() != 80) : (ListenerUtil.mutListener.listen(7801) ? (getMessageModel().getCaption().length() == 80) : (getMessageModel().getCaption().length() < 80)))))), actionModeStatus.getActionModeEnabled(), onClickElement);
                }
                if (!ListenerUtil.mutListener.listen(7807)) {
                    this.showHide(holder.bodyTextView, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7799)) {
                    this.showHide(holder.bodyTextView, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7811)) {
            imageMessagePlayer.addListener(LISTENER_TAG, new MessagePlayer.DownloadListener() {

                @Override
                public void onStart(AbstractMessageModel messageModel) {
                    if (!ListenerUtil.mutListener.listen(7809)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgressing(false));
                    }
                }

                @Override
                public void onStatusUpdate(AbstractMessageModel messageModel, final int progress) {
                }

                @Override
                public void onEnd(AbstractMessageModel messageModel, final boolean success, final String message) {
                    if (!ListenerUtil.mutListener.listen(7810)) {
                        // hide progressbar
                        RuntimeUtil.runOnUiThread(() -> {
                            if (success) {
                                holder.controller.setHidden();
                            } else {
                                holder.controller.setReadyToDownload();
                                if (!TestUtil.empty(message) && getContext() != null) {
                                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private void viewImage(final AbstractMessageModel m, final View v) {
        if (!ListenerUtil.mutListener.listen(7815)) {
            if (m.isAvailable()) {
                Intent intent = new Intent(getContext(), MediaViewerActivity.class);
                if (!ListenerUtil.mutListener.listen(7812)) {
                    IntentDataUtil.append(m, intent);
                }
                if (!ListenerUtil.mutListener.listen(7813)) {
                    intent.putExtra(MediaViewerActivity.EXTRA_ID_REVERSE_ORDER, true);
                }
                if (!ListenerUtil.mutListener.listen(7814)) {
                    AnimationUtil.startActivityForResult((Activity) getContext(), v, intent, ThreemaActivity.ACTIVITY_ID_MEDIA_VIEWER);
                }
            }
        }
    }

    private void setControllerState(@NonNull ComposeMessageHolder holder, ImageDataModel imageDataModel) {
        if (!ListenerUtil.mutListener.listen(7816)) {
            if (holder.controller == null) {
                return;
            }
        }
        AbstractMessageModel messageModel = this.getMessageModel();
        if (!ListenerUtil.mutListener.listen(7833)) {
            if (messageModel != null) {
                if (!ListenerUtil.mutListener.listen(7832)) {
                    if ((ListenerUtil.mutListener.listen(7818) ? (messageModel.isOutbox() || !(messageModel instanceof DistributionListMessageModel)) : (messageModel.isOutbox() && !(messageModel instanceof DistributionListMessageModel)))) {
                        if (!ListenerUtil.mutListener.listen(7831)) {
                            // outgoing message
                            switch(messageModel.getState()) {
                                case TRANSCODING:
                                    if (!ListenerUtil.mutListener.listen(7827)) {
                                        holder.controller.setTranscoding();
                                    }
                                    break;
                                case PENDING:
                                case SENDING:
                                    if (!ListenerUtil.mutListener.listen(7828)) {
                                        holder.controller.setProgressing();
                                    }
                                    break;
                                case SENDFAILED:
                                    if (!ListenerUtil.mutListener.listen(7829)) {
                                        holder.controller.setRetry();
                                    }
                                    break;
                                default:
                                    if (!ListenerUtil.mutListener.listen(7830)) {
                                        holder.controller.setHidden();
                                    }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7826)) {
                            // incoming message
                            if (TestUtil.required(imageDataModel)) {
                                if (!ListenerUtil.mutListener.listen(7825)) {
                                    if (imageDataModel.isDownloaded()) {
                                        if (!ListenerUtil.mutListener.listen(7824)) {
                                            holder.controller.setHidden();
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7823)) {
                                            if (holder.messagePlayer.getState() == MessagePlayer.State_DOWNLOADING) {
                                                if (!ListenerUtil.mutListener.listen(7822)) {
                                                    // set correct state if re-entering this chat
                                                    holder.controller.setProgressing(false);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7821)) {
                                                    if (helper.getDownloadService().isDownloading(messageModel.getId())) {
                                                        if (!ListenerUtil.mutListener.listen(7820)) {
                                                            holder.controller.setProgressing(false);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(7819)) {
                                                            holder.controller.setReadyToDownload();
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
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7817)) {
                    holder.controller.setHidden();
                }
            }
        }
    }
}
