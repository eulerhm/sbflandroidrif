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
package ch.threema.app.services.messageplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ImageViewUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.MediaMessageDataInterface;
import pl.droidsonroids.gif.GifDrawable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GifMessagePlayer extends MessagePlayer {

    private static final Logger logger = LoggerFactory.getLogger(GifMessagePlayer.class);

    private static final String TAG = "GifMessagePlayer";

    private final PreferenceService preferenceService;

    private GifDrawable gifDrawable;

    private ImageView imageContainer;

    protected GifMessagePlayer(Context context, MessageService messageService, FileService fileService, PreferenceService preferenceService, MessageReceiver messageReceiver, AbstractMessageModel messageModel) {
        super(context, messageService, fileService, messageReceiver, messageModel);
        this.preferenceService = preferenceService;
    }

    public GifMessagePlayer attachContainer(ImageView container) {
        if (!ListenerUtil.mutListener.listen(35733)) {
            this.imageContainer = container;
        }
        return this;
    }

    @Override
    public MediaMessageDataInterface getData() {
        return this.getMessageModel().getFileData();
    }

    @Override
    protected AbstractMessageModel setData(MediaMessageDataInterface data) {
        AbstractMessageModel messageModel = this.getMessageModel();
        if (!ListenerUtil.mutListener.listen(35734)) {
            messageModel.setFileData((FileDataModel) data);
        }
        return messageModel;
    }

    @Override
    protected void open(final File decryptedFile) {
        if (!ListenerUtil.mutListener.listen(35735)) {
            logger.debug("open(decryptedFile)");
        }
        if (!ListenerUtil.mutListener.listen(35743)) {
            if ((ListenerUtil.mutListener.listen(35737) ? ((ListenerUtil.mutListener.listen(35736) ? (this.currentActivityRef != null || this.currentActivityRef.get() != null) : (this.currentActivityRef != null && this.currentActivityRef.get() != null)) || this.isReceiverMatch(this.currentMessageReceiver)) : ((ListenerUtil.mutListener.listen(35736) ? (this.currentActivityRef != null || this.currentActivityRef.get() != null) : (this.currentActivityRef != null && this.currentActivityRef.get() != null)) && this.isReceiverMatch(this.currentMessageReceiver)))) {
                final String mimeType = getMessageModel().getFileData().getMimeType();
                if (!ListenerUtil.mutListener.listen(35742)) {
                    if ((ListenerUtil.mutListener.listen(35738) ? (!TestUtil.empty(mimeType) || decryptedFile.exists()) : (!TestUtil.empty(mimeType) && decryptedFile.exists()))) {
                        if (!ListenerUtil.mutListener.listen(35741)) {
                            if (preferenceService.isGifAutoplay()) {
                                if (!ListenerUtil.mutListener.listen(35740)) {
                                    autoPlay(decryptedFile);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(35739)) {
                                    openInExternalPlayer(decryptedFile);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void autoPlay(final File decryptedFile) {
        if (!ListenerUtil.mutListener.listen(35744)) {
            logger.debug("autoPlay(decryptedFile)");
        }
        if (!ListenerUtil.mutListener.listen(35754)) {
            if ((ListenerUtil.mutListener.listen(35746) ? ((ListenerUtil.mutListener.listen(35745) ? (this.imageContainer != null || this.currentActivityRef != null) : (this.imageContainer != null && this.currentActivityRef != null)) || this.currentActivityRef.get() != null) : ((ListenerUtil.mutListener.listen(35745) ? (this.imageContainer != null || this.currentActivityRef != null) : (this.imageContainer != null && this.currentActivityRef != null)) && this.currentActivityRef.get() != null))) {
                if (!ListenerUtil.mutListener.listen(35749)) {
                    if ((ListenerUtil.mutListener.listen(35747) ? (this.gifDrawable != null || !gifDrawable.isRecycled()) : (this.gifDrawable != null && !gifDrawable.isRecycled()))) {
                        if (!ListenerUtil.mutListener.listen(35748)) {
                            this.gifDrawable.stop();
                        }
                    }
                }
                final Uri uri = Uri.parse(decryptedFile.getPath());
                try {
                    if (!ListenerUtil.mutListener.listen(35751)) {
                        this.gifDrawable = new GifDrawable(uri.getPath());
                    }
                    if (!ListenerUtil.mutListener.listen(35752)) {
                        this.gifDrawable.setCornerRadius(ImageViewUtil.getCornerRadius(getContext()));
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(35750)) {
                        logger.error("I/O Exception", e);
                    }
                    return;
                }
                if (!ListenerUtil.mutListener.listen(35753)) {
                    RuntimeUtil.runOnUiThread(() -> {
                        if (gifDrawable != null && !gifDrawable.isRecycled()) {
                            imageContainer.setImageDrawable(gifDrawable);
                            if (preferenceService.isGifAutoplay()) {
                                gifDrawable.start();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public boolean open() {
        if (!ListenerUtil.mutListener.listen(35755)) {
            logger.debug("open");
        }
        return super.open();
    }

    public boolean autoPlay() {
        if (!ListenerUtil.mutListener.listen(35756)) {
            logger.debug("autoPlay");
        }
        return super.open(true);
    }

    public void openInExternalPlayer(File decryptedFile) {
        if (!ListenerUtil.mutListener.listen(35757)) {
            RuntimeUtil.runOnUiThread(() -> {
                if (currentActivityRef != null && currentActivityRef.get() != null && this.isReceiverMatch(currentMessageReceiver)) {
                    Intent intent = new Intent(getContext(), MediaViewerActivity.class);
                    IntentDataUtil.append(getMessageModel(), intent);
                    intent.putExtra(MediaViewerActivity.EXTRA_ID_REVERSE_ORDER, true);
                    AnimationUtil.startActivityForResult(currentActivityRef.get(), null, intent, ThreemaActivity.ACTIVITY_ID_MEDIA_VIEWER);
                }
            });
        }
    }

    @Override
    protected void makePause(int source) {
        if (!ListenerUtil.mutListener.listen(35758)) {
            logger.debug("makePause");
        }
        if (!ListenerUtil.mutListener.listen(35763)) {
            if (this.imageContainer != null) {
                if (!ListenerUtil.mutListener.listen(35762)) {
                    if ((ListenerUtil.mutListener.listen(35760) ? ((ListenerUtil.mutListener.listen(35759) ? (this.gifDrawable != null || this.gifDrawable.isPlaying()) : (this.gifDrawable != null && this.gifDrawable.isPlaying())) || !gifDrawable.isRecycled()) : ((ListenerUtil.mutListener.listen(35759) ? (this.gifDrawable != null || this.gifDrawable.isPlaying()) : (this.gifDrawable != null && this.gifDrawable.isPlaying())) && !gifDrawable.isRecycled()))) {
                        if (!ListenerUtil.mutListener.listen(35761)) {
                            this.gifDrawable.pause();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void makeResume(int source) {
        if (!ListenerUtil.mutListener.listen(35764)) {
            logger.debug("makeResume: " + getMessageModel().getId());
        }
        if (!ListenerUtil.mutListener.listen(35769)) {
            if (this.imageContainer != null) {
                if (!ListenerUtil.mutListener.listen(35768)) {
                    if ((ListenerUtil.mutListener.listen(35766) ? ((ListenerUtil.mutListener.listen(35765) ? (this.gifDrawable != null || !this.gifDrawable.isPlaying()) : (this.gifDrawable != null && !this.gifDrawable.isPlaying())) || !gifDrawable.isRecycled()) : ((ListenerUtil.mutListener.listen(35765) ? (this.gifDrawable != null || !this.gifDrawable.isPlaying()) : (this.gifDrawable != null && !this.gifDrawable.isPlaying())) && !gifDrawable.isRecycled()))) {
                        if (!ListenerUtil.mutListener.listen(35767)) {
                            this.gifDrawable.start();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void seekTo(int pos) {
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public void removeListeners() {
        if (!ListenerUtil.mutListener.listen(35770)) {
            super.removeListeners();
        }
        if (!ListenerUtil.mutListener.listen(35771)) {
            logger.debug("removeListeners");
        }
        if (!ListenerUtil.mutListener.listen(35776)) {
            // release animgif players if item comes out of view
            if ((ListenerUtil.mutListener.listen(35772) ? (this.gifDrawable != null || !this.gifDrawable.isRecycled()) : (this.gifDrawable != null && !this.gifDrawable.isRecycled()))) {
                if (!ListenerUtil.mutListener.listen(35773)) {
                    this.gifDrawable.stop();
                }
                if (!ListenerUtil.mutListener.listen(35774)) {
                    this.gifDrawable.recycle();
                }
                if (!ListenerUtil.mutListener.listen(35775)) {
                    this.gifDrawable = null;
                }
            }
        }
    }
}
