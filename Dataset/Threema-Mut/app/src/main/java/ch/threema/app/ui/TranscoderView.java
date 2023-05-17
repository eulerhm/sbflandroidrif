/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.MessageService;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TranscoderView extends FrameLayout {

    private static final Logger logger = LoggerFactory.getLogger(TranscoderView.class);

    public static final int PROGRESS_MAX = 100;

    private ProgressBar transcodeProgress;

    private AbstractMessageModel messageModel;

    private MessageService messageService;

    public TranscoderView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47662)) {
            init(context);
        }
    }

    public TranscoderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(47663)) {
            init(context);
        }
    }

    public TranscoderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(47664)) {
            init(context);
        }
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(47665)) {
            inflater.inflate(R.layout.conversation_list_item_transcoder_view, this);
        }
        try {
            if (!ListenerUtil.mutListener.listen(47667)) {
                messageService = ThreemaApplication.getServiceManager().getMessageService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(47666)) {
                logger.debug("Unable to get MessageService", e);
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(47668)) {
            super.onFinishInflate();
        }
        if (!ListenerUtil.mutListener.listen(47669)) {
            transcodeProgress = this.findViewById(R.id.transcode_progress);
        }
        if (!ListenerUtil.mutListener.listen(47673)) {
            this.findViewById(R.id.cancel_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(47672)) {
                        if ((ListenerUtil.mutListener.listen(47670) ? (messageModel != null || messageService != null) : (messageModel != null && messageService != null))) {
                            if (!ListenerUtil.mutListener.listen(47671)) {
                                messageService.cancelVideoTranscoding(messageModel);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(47674)) {
            transcodeProgress.setMax(PROGRESS_MAX);
        }
        if (!ListenerUtil.mutListener.listen(47675)) {
            transcodeProgress.setProgress(0);
        }
        if (!ListenerUtil.mutListener.listen(47676)) {
            transcodeProgress.setIndeterminate(true);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!ListenerUtil.mutListener.listen(47677)) {
            super.onLayout(changed, left, top, right, bottom);
        }
        final View parent = ((View) getParent());
        final ViewTreeObserver observer = parent.getViewTreeObserver();
        if (!ListenerUtil.mutListener.listen(47683)) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    ImageView imageView = parent.findViewById(R.id.attachment_image_view);
                    if (!ListenerUtil.mutListener.listen(47681)) {
                        if (imageView != null) {
                            if (!ListenerUtil.mutListener.listen(47678)) {
                                getLayoutParams().height = imageView.getMeasuredHeight();
                            }
                            if (!ListenerUtil.mutListener.listen(47679)) {
                                getLayoutParams().width = imageView.getMeasuredWidth();
                            }
                            if (!ListenerUtil.mutListener.listen(47680)) {
                                requestLayout();
                            }
                        }
                    }
                    ViewTreeObserver obs = parent.getViewTreeObserver();
                    if (!ListenerUtil.mutListener.listen(47682)) {
                        obs.removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }

    public void setProgress(int progress) {
        if (!ListenerUtil.mutListener.listen(47690)) {
            if ((ListenerUtil.mutListener.listen(47688) ? (progress >= PROGRESS_MAX) : (ListenerUtil.mutListener.listen(47687) ? (progress <= PROGRESS_MAX) : (ListenerUtil.mutListener.listen(47686) ? (progress < PROGRESS_MAX) : (ListenerUtil.mutListener.listen(47685) ? (progress != PROGRESS_MAX) : (ListenerUtil.mutListener.listen(47684) ? (progress == PROGRESS_MAX) : (progress > PROGRESS_MAX))))))) {
                if (!ListenerUtil.mutListener.listen(47689)) {
                    progress = PROGRESS_MAX;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47699)) {
            if ((ListenerUtil.mutListener.listen(47695) ? (progress >= 0) : (ListenerUtil.mutListener.listen(47694) ? (progress <= 0) : (ListenerUtil.mutListener.listen(47693) ? (progress < 0) : (ListenerUtil.mutListener.listen(47692) ? (progress != 0) : (ListenerUtil.mutListener.listen(47691) ? (progress == 0) : (progress > 0))))))) {
                if (!ListenerUtil.mutListener.listen(47697)) {
                    transcodeProgress.setIndeterminate(false);
                }
                if (!ListenerUtil.mutListener.listen(47698)) {
                    transcodeProgress.setProgress(progress);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47696)) {
                    transcodeProgress.setIndeterminate(true);
                }
            }
        }
    }

    public void setMessageModel(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(47700)) {
            this.messageModel = messageModel;
        }
    }
}
