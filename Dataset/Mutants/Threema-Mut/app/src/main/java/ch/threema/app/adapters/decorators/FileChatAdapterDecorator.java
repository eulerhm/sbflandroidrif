/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import ch.threema.app.R;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.messageplayer.FileMessagePlayer;
import ch.threema.app.services.messageplayer.MessagePlayer;
import ch.threema.app.ui.ControllerView;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.AvatarConverterUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.ImageViewUtil;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.data.media.FileDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(FileChatAdapterDecorator.class);

    private static final String LISTENER_TAG = "FileChatDecorator";

    private Context context;

    private FileDataModel fileData;

    private FileMessagePlayer fileMessagePlayer;

    public FileChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context, messageModel, helper);
        if (!ListenerUtil.mutListener.listen(7646)) {
            this.context = context;
        }
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        if (!ListenerUtil.mutListener.listen(7647)) {
            this.fileMessagePlayer = (FileMessagePlayer) this.getMessagePlayerService().createPlayer(this.getMessageModel(), (Activity) context, this.helper.getMessageReceiver());
        }
        if (!ListenerUtil.mutListener.listen(7648)) {
            holder.messagePlayer = fileMessagePlayer;
        }
        if (!ListenerUtil.mutListener.listen(7649)) {
            holder.controller.setClickable(false);
        }
        if (!ListenerUtil.mutListener.listen(7650)) {
            fileData = this.getMessageModel().getFileData();
        }
        if (!ListenerUtil.mutListener.listen(7651)) {
            setThumbnail(holder, false);
        }
        if (!ListenerUtil.mutListener.listen(7652)) {
            RuntimeUtil.runOnUiThread(() -> setControllerState(holder, fileData));
        }
        if (!ListenerUtil.mutListener.listen(7663)) {
            if (holder.controller != null) {
                if (!ListenerUtil.mutListener.listen(7662)) {
                    holder.controller.setOnClickListener(new DebouncedOnClickListener(500) {

                        @Override
                        public void onDebouncedClick(View v) {
                            int status = holder.controller.getStatus();
                            if (!ListenerUtil.mutListener.listen(7661)) {
                                switch(status) {
                                    case ControllerView.STATUS_READY_TO_PLAY:
                                    case ControllerView.STATUS_READY_TO_DOWNLOAD:
                                    case ControllerView.STATUS_NONE:
                                        if (!ListenerUtil.mutListener.listen(7653)) {
                                            FileChatAdapterDecorator.this.prepareDownload(fileData, fileMessagePlayer);
                                        }
                                        break;
                                    case ControllerView.STATUS_PROGRESSING:
                                        if (!ListenerUtil.mutListener.listen(7658)) {
                                            if ((ListenerUtil.mutListener.listen(7655) ? (FileChatAdapterDecorator.this.getMessageModel().isOutbox() || ((ListenerUtil.mutListener.listen(7654) ? (FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING && FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING) : (FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING || FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING)))) : (FileChatAdapterDecorator.this.getMessageModel().isOutbox() && ((ListenerUtil.mutListener.listen(7654) ? (FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING && FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING) : (FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.PENDING || FileChatAdapterDecorator.this.getMessageModel().getState() == MessageState.SENDING)))))) {
                                                if (!ListenerUtil.mutListener.listen(7657)) {
                                                    FileChatAdapterDecorator.this.getMessageService().cancelMessageUpload(FileChatAdapterDecorator.this.getMessageModel());
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(7656)) {
                                                    fileMessagePlayer.cancel();
                                                }
                                            }
                                        }
                                        break;
                                    case ControllerView.STATUS_READY_TO_RETRY:
                                        if (!ListenerUtil.mutListener.listen(7660)) {
                                            if (onClickRetry != null) {
                                                if (!ListenerUtil.mutListener.listen(7659)) {
                                                    onClickRetry.onClick(FileChatAdapterDecorator.this.getMessageModel());
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7664)) {
            this.setOnClickListener(view -> prepareDownload(fileData, fileMessagePlayer), holder.messageBlockView);
        }
        if (!ListenerUtil.mutListener.listen(7671)) {
            fileMessagePlayer.addListener(LISTENER_TAG, new MessagePlayer.PlaybackListener() {

                @Override
                public void onPlay(AbstractMessageModel messageModel, boolean autoPlay) {
                    if (!ListenerUtil.mutListener.listen(7670)) {
                        RuntimeUtil.runOnUiThread(() -> invalidate(holder, position));
                    }
                }

                @Override
                public void onPause(AbstractMessageModel messageModel) {
                }

                @Override
                public void onStatusUpdate(AbstractMessageModel messageModel, int position) {
                }

                @Override
                public void onStop(AbstractMessageModel messageModel) {
                }
            }).addListener(LISTENER_TAG, new MessagePlayer.DecryptionListener() {

                @Override
                public void onStart(AbstractMessageModel messageModel) {
                    if (!ListenerUtil.mutListener.listen(7668)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgressing(false));
                    }
                }

                @Override
                public void onEnd(AbstractMessageModel messageModel, final boolean success, final String message, File decryptedFile) {
                    if (!ListenerUtil.mutListener.listen(7669)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (!success) {
                                holder.controller.setReadyToDownload();
                                if (!TestUtil.empty(message)) {
                                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                holder.controller.setHidden();
                            }
                        });
                    }
                }
            }).addListener(LISTENER_TAG, new MessagePlayer.DownloadListener() {

                @Override
                public void onStart(AbstractMessageModel messageModel) {
                    if (!ListenerUtil.mutListener.listen(7665)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgressingDeterminate(100));
                    }
                }

                @Override
                public void onStatusUpdate(AbstractMessageModel messageModel, final int progress) {
                    if (!ListenerUtil.mutListener.listen(7666)) {
                        RuntimeUtil.runOnUiThread(() -> holder.controller.setProgress(progress));
                    }
                }

                @Override
                public void onEnd(AbstractMessageModel messageModel, final boolean success, final String message) {
                    if (!ListenerUtil.mutListener.listen(7667)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (success) {
                                if (FileUtil.isImageFile(fileData) && (fileData.getRenderingType() == FileData.RENDERING_STICKER || fileData.getRenderingType() == FileData.RENDERING_MEDIA)) {
                                    holder.controller.setHidden();
                                } else {
                                    holder.controller.setNeutral();
                                    setThumbnail(holder, false);
                                }
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
        if (!ListenerUtil.mutListener.listen(7672)) {
            this.showHide(holder.tertiaryTextView, true);
        }
        if (!ListenerUtil.mutListener.listen(7673)) {
            this.showHide(holder.secondaryTextView, true);
        }
        if (!ListenerUtil.mutListener.listen(7674)) {
            this.showHide(holder.size, true);
        }
        if (!ListenerUtil.mutListener.listen(7684)) {
            if (!TestUtil.empty(fileData.getCaption())) {
                if (!ListenerUtil.mutListener.listen(7676)) {
                    holder.bodyTextView.setText(formatTextString(fileData.getCaption(), this.filterString));
                }
                if (!ListenerUtil.mutListener.listen(7682)) {
                    LinkifyUtil.getInstance().linkify((ComposeMessageFragment) helper.getFragment(), holder.bodyTextView, this.getMessageModel(), (ListenerUtil.mutListener.listen(7681) ? (fileData.getCaption().length() >= 80) : (ListenerUtil.mutListener.listen(7680) ? (fileData.getCaption().length() <= 80) : (ListenerUtil.mutListener.listen(7679) ? (fileData.getCaption().length() > 80) : (ListenerUtil.mutListener.listen(7678) ? (fileData.getCaption().length() != 80) : (ListenerUtil.mutListener.listen(7677) ? (fileData.getCaption().length() == 80) : (fileData.getCaption().length() < 80)))))), actionModeStatus.getActionModeEnabled(), onClickElement);
                }
                if (!ListenerUtil.mutListener.listen(7683)) {
                    this.showHide(holder.bodyTextView, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7675)) {
                    this.showHide(holder.bodyTextView, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7688)) {
            if (holder.tertiaryTextView != null) {
                String fileName = fileData.getFileName();
                if (!ListenerUtil.mutListener.listen(7687)) {
                    if (!TestUtil.empty(fileName)) {
                        if (!ListenerUtil.mutListener.listen(7686)) {
                            holder.tertiaryTextView.setText(highlightMatches(fileName, this.filterString));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7685)) {
                            holder.tertiaryTextView.setText(R.string.no_filename);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7693)) {
            if (holder.secondaryTextView != null) {
                String mimeString = fileData.getMimeType();
                if (!ListenerUtil.mutListener.listen(7692)) {
                    if (holder.secondaryTextView != null) {
                        if (!ListenerUtil.mutListener.listen(7691)) {
                            if (!TestUtil.empty(mimeString)) {
                                if (!ListenerUtil.mutListener.listen(7690)) {
                                    holder.secondaryTextView.setText(MimeUtil.getMimeDescription(context, fileData.getMimeType()));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7689)) {
                                    holder.secondaryTextView.setText("");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7701)) {
            if (holder.size != null) {
                long size = fileData.getFileSize();
                if (!ListenerUtil.mutListener.listen(7700)) {
                    if ((ListenerUtil.mutListener.listen(7698) ? (size >= 0) : (ListenerUtil.mutListener.listen(7697) ? (size <= 0) : (ListenerUtil.mutListener.listen(7696) ? (size < 0) : (ListenerUtil.mutListener.listen(7695) ? (size != 0) : (ListenerUtil.mutListener.listen(7694) ? (size == 0) : (size > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(7699)) {
                            holder.size.setText(Formatter.formatShortFileSize(getContext(), fileData.getFileSize()));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7703)) {
            if (holder.dateView != null) {
                if (!ListenerUtil.mutListener.listen(7702)) {
                    this.setDatePrefix(FileUtil.getFileMessageDatePrefix(getContext(), getMessageModel(), FileUtil.isImageFile(fileData) ? getContext().getString(R.string.image_placeholder) : null), 0);
                }
            }
        }
    }

    private void prepareDownload(final FileDataModel fileData, final FileMessagePlayer fileMessagePlayer) {
        if (!ListenerUtil.mutListener.listen(7713)) {
            if (TestUtil.required(fileData, fileMessagePlayer)) {
                if (!ListenerUtil.mutListener.listen(7712)) {
                    if (fileData.isDownloaded()) {
                        if (!ListenerUtil.mutListener.listen(7711)) {
                            fileMessagePlayer.open();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7710)) {
                            if (!this.getMessageModel().isOutbox()) {
                                final PreferenceService preferenceService = getPreferenceService();
                                if (!ListenerUtil.mutListener.listen(7709)) {
                                    if ((ListenerUtil.mutListener.listen(7704) ? (preferenceService != null || !preferenceService.getFileSendInfoShown()) : (preferenceService != null && !preferenceService.getFileSendInfoShown()))) {
                                        if (!ListenerUtil.mutListener.listen(7708)) {
                                            new MaterialAlertDialogBuilder(getContext()).setTitle(R.string.download).setMessage(R.string.send_as_files_warning).setNegativeButton(R.string.cancel, null).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                                                public void onClick(DialogInterface dialog, int id) {
                                                    if (!ListenerUtil.mutListener.listen(7706)) {
                                                        preferenceService.setFileSendInfoShown(true);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(7707)) {
                                                        fileMessagePlayer.open();
                                                    }
                                                }
                                            }).show();
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7705)) {
                                            fileMessagePlayer.open();
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

    private void setThumbnail(ComposeMessageHolder holder, final boolean updateBitmap) {
        Bitmap thumbnail = null;
        try {
            if (!ListenerUtil.mutListener.listen(7714)) {
                thumbnail = this.getFileService().getMessageThumbnailBitmap(this.getMessageModel(), updateBitmap ? null : this.getThumbnailCache());
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(7736)) {
            if ((ListenerUtil.mutListener.listen(7716) ? (FileUtil.isImageFile(fileData) || ((ListenerUtil.mutListener.listen(7715) ? (fileData.getRenderingType() == FileData.RENDERING_STICKER && fileData.getRenderingType() == FileData.RENDERING_MEDIA) : (fileData.getRenderingType() == FileData.RENDERING_STICKER || fileData.getRenderingType() == FileData.RENDERING_MEDIA)))) : (FileUtil.isImageFile(fileData) && ((ListenerUtil.mutListener.listen(7715) ? (fileData.getRenderingType() == FileData.RENDERING_STICKER && fileData.getRenderingType() == FileData.RENDERING_MEDIA) : (fileData.getRenderingType() == FileData.RENDERING_STICKER || fileData.getRenderingType() == FileData.RENDERING_MEDIA)))))) {
                if (!ListenerUtil.mutListener.listen(7728)) {
                    ImageViewUtil.showRoundedBitmap(getContext(), holder.contentView, holder.attachmentImage, thumbnail, helper.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7729)) {
                    holder.bodyTextView.setWidth(helper.getThumbnailWidth());
                }
                if (!ListenerUtil.mutListener.listen(7732)) {
                    if (holder.attachmentImage != null) {
                        if (!ListenerUtil.mutListener.listen(7730)) {
                            holder.attachmentImage.setVisibility(thumbnail != null ? View.VISIBLE : View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(7731)) {
                            holder.attachmentImage.setContentDescription(getContext().getString(R.string.image_placeholder));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7735)) {
                    if (fileData.getRenderingType() == FileData.RENDERING_STICKER) {
                        if (!ListenerUtil.mutListener.listen(7734)) {
                            holder.messageBlockView.setBackground(null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7733)) {
                            setDefaultBackground(holder);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7721)) {
                    if (thumbnail == null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(7718)) {
                                thumbnail = getFileService().getDefaultMessageThumbnailBitmap(context, getMessageModel(), null, fileData.getMimeType());
                            }
                            if (!ListenerUtil.mutListener.listen(7720)) {
                                if (thumbnail != null) {
                                    if (!ListenerUtil.mutListener.listen(7719)) {
                                        thumbnail = AvatarConverterUtil.convert(getContext().getResources(), thumbnail, getContext().getResources().getColor(R.color.item_controller_color), Color.WHITE);
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7717)) {
                            thumbnail = AvatarConverterUtil.convert(getContext().getResources(), thumbnail);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7724)) {
                    if (thumbnail != null) {
                        if (!ListenerUtil.mutListener.listen(7723)) {
                            if (holder.controller != null) {
                                if (!ListenerUtil.mutListener.listen(7722)) {
                                    holder.controller.setBackgroundImage(thumbnail);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7726)) {
                    if (holder.attachmentImage != null) {
                        if (!ListenerUtil.mutListener.listen(7725)) {
                            holder.attachmentImage.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7727)) {
                    setDefaultBackground(holder);
                }
            }
        }
    }

    private void setControllerState(ComposeMessageHolder holder, FileDataModel fileData) {
        if (!ListenerUtil.mutListener.listen(7763)) {
            if ((ListenerUtil.mutListener.listen(7737) ? (this.getMessageModel().isOutbox() || !(this.getMessageModel() instanceof DistributionListMessageModel)) : (this.getMessageModel().isOutbox() && !(this.getMessageModel() instanceof DistributionListMessageModel)))) {
                if (!ListenerUtil.mutListener.listen(7762)) {
                    switch(this.getMessageModel().getState()) {
                        case TRANSCODING:
                            if (!ListenerUtil.mutListener.listen(7751)) {
                                holder.controller.setTranscoding();
                            }
                            if (!ListenerUtil.mutListener.listen(7753)) {
                                if (holder.transcoderView != null) {
                                    if (!ListenerUtil.mutListener.listen(7752)) {
                                        holder.transcoderView.setProgress(holder.messagePlayer.getTranscodeProgress());
                                    }
                                }
                            }
                            break;
                        case PENDING:
                            if (!ListenerUtil.mutListener.listen(7754)) {
                                setThumbnail(holder, true);
                            }
                        // fallthrough
                        case SENDING:
                            if (!ListenerUtil.mutListener.listen(7755)) {
                                holder.controller.setProgressing();
                            }
                            break;
                        case SENDFAILED:
                            if (!ListenerUtil.mutListener.listen(7756)) {
                                holder.controller.setRetry();
                            }
                            break;
                        case SENT:
                        default:
                            if (!ListenerUtil.mutListener.listen(7761)) {
                                if ((ListenerUtil.mutListener.listen(7758) ? (FileUtil.isImageFile(fileData) || ((ListenerUtil.mutListener.listen(7757) ? (fileData.getRenderingType() == FileData.RENDERING_MEDIA && fileData.getRenderingType() == FileData.RENDERING_STICKER) : (fileData.getRenderingType() == FileData.RENDERING_MEDIA || fileData.getRenderingType() == FileData.RENDERING_STICKER)))) : (FileUtil.isImageFile(fileData) && ((ListenerUtil.mutListener.listen(7757) ? (fileData.getRenderingType() == FileData.RENDERING_MEDIA && fileData.getRenderingType() == FileData.RENDERING_STICKER) : (fileData.getRenderingType() == FileData.RENDERING_MEDIA || fileData.getRenderingType() == FileData.RENDERING_STICKER)))))) {
                                    if (!ListenerUtil.mutListener.listen(7760)) {
                                        holder.controller.setHidden();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7759)) {
                                        holder.controller.setNeutral();
                                    }
                                }
                            }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7745)) {
                    // incoming message
                    if (fileData != null) {
                        if (!ListenerUtil.mutListener.listen(7744)) {
                            if (fileData.isDownloaded()) {
                                if (!ListenerUtil.mutListener.listen(7743)) {
                                    if ((ListenerUtil.mutListener.listen(7740) ? (FileUtil.isImageFile(fileData) || ((ListenerUtil.mutListener.listen(7739) ? (fileData.getRenderingType() == FileData.RENDERING_MEDIA && fileData.getRenderingType() == FileData.RENDERING_STICKER) : (fileData.getRenderingType() == FileData.RENDERING_MEDIA || fileData.getRenderingType() == FileData.RENDERING_STICKER)))) : (FileUtil.isImageFile(fileData) && ((ListenerUtil.mutListener.listen(7739) ? (fileData.getRenderingType() == FileData.RENDERING_MEDIA && fileData.getRenderingType() == FileData.RENDERING_STICKER) : (fileData.getRenderingType() == FileData.RENDERING_MEDIA || fileData.getRenderingType() == FileData.RENDERING_STICKER)))))) {
                                        if (!ListenerUtil.mutListener.listen(7742)) {
                                            holder.controller.setHidden();
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7741)) {
                                            holder.controller.setNeutral();
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7738)) {
                                    holder.controller.setReadyToDownload();
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7750)) {
                    if (holder.messagePlayer != null) {
                        if (!ListenerUtil.mutListener.listen(7749)) {
                            switch(holder.messagePlayer.getState()) {
                                case MessagePlayer.State_DOWNLOADING:
                                    if (!ListenerUtil.mutListener.listen(7746)) {
                                        holder.controller.setProgressingDeterminate(100);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7747)) {
                                        holder.controller.setProgress(holder.messagePlayer.getDownloadProgress());
                                    }
                                    break;
                                case MessagePlayer.State_DECRYPTING:
                                    if (!ListenerUtil.mutListener.listen(7748)) {
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
