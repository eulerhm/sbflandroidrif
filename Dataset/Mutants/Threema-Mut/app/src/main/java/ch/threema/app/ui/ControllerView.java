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
package ch.threema.app.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatImageView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ControllerView extends FrameLayout {

    private static final Logger logger = LoggerFactory.getLogger(ControllerView.class);

    private ProgressBar progressBarIndeterminate;

    private CircularProgressBar progressBarDeterminate;

    private AppCompatImageView icon;

    private int status, progressMax = 100;

    public static final int STATUS_NONE = 0;

    public static final int STATUS_PROGRESSING = 1;

    public static final int STATUS_READY_TO_DOWNLOAD = 2;

    public static final int STATUS_READY_TO_PLAY = 3;

    public static final int STATUS_PLAYING = 4;

    public static final int STATUS_READY_TO_RETRY = 5;

    public static final int STATUS_PROGRESSING_NO_CANCEL = 6;

    public static final int STATUS_BROKEN = 7;

    public static final int STATUS_TRANSCODING = 8;

    private OnVisibilityChangedListener visibilityChangedListener;

    public ControllerView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47104)) {
            init(context);
        }
    }

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(47105)) {
            init(context);
        }
    }

    public ControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(47106)) {
            init(context);
        }
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(47107)) {
            inflater.inflate(R.layout.conversation_list_item_controller_view, this);
        }
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(47108)) {
            super.onFinishInflate();
        }
        if (!ListenerUtil.mutListener.listen(47109)) {
            progressBarIndeterminate = this.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(47110)) {
            progressBarDeterminate = this.findViewById(R.id.progress_determinate);
        }
        if (!ListenerUtil.mutListener.listen(47111)) {
            icon = this.findViewById(R.id.icon);
        }
        if (!ListenerUtil.mutListener.listen(47112)) {
            setBackgroundImage(null);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(47113)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int padding = (ListenerUtil.mutListener.listen(47117) ? (getMeasuredWidth() % 6) : (ListenerUtil.mutListener.listen(47116) ? (getMeasuredWidth() * 6) : (ListenerUtil.mutListener.listen(47115) ? (getMeasuredWidth() - 6) : (ListenerUtil.mutListener.listen(47114) ? (getMeasuredWidth() + 6) : (getMeasuredWidth() / 6)))));
        if (!ListenerUtil.mutListener.listen(47118)) {
            icon.setPadding(padding, padding, padding, padding);
        }
    }

    private void reset() {
        if (!ListenerUtil.mutListener.listen(47120)) {
            if (getVisibility() != VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47119)) {
                    setVisibility(VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47122)) {
            if (progressBarIndeterminate.getVisibility() == VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47121)) {
                    progressBarIndeterminate.setVisibility(INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47124)) {
            if (progressBarDeterminate.getVisibility() == VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47123)) {
                    progressBarDeterminate.setVisibility(INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47126)) {
            if (icon.getVisibility() != VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47125)) {
                    icon.setVisibility(VISIBLE);
                }
            }
        }
    }

    public void setNeutral() {
        if (!ListenerUtil.mutListener.listen(47127)) {
            logger.debug("setNeutral");
        }
        if (!ListenerUtil.mutListener.listen(47128)) {
            reset();
        }
        if (!ListenerUtil.mutListener.listen(47129)) {
            icon.setVisibility(INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(47130)) {
            status = STATUS_NONE;
        }
    }

    public void setHidden() {
        if (!ListenerUtil.mutListener.listen(47131)) {
            logger.debug("setHidden");
        }
        if (!ListenerUtil.mutListener.listen(47132)) {
            setVisibility(INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(47133)) {
            status = STATUS_NONE;
        }
    }

    @UiThread
    public void setPlay() {
        if (!ListenerUtil.mutListener.listen(47134)) {
            logger.debug("setPlay");
        }
        if (!ListenerUtil.mutListener.listen(47143)) {
            if ((ListenerUtil.mutListener.listen(47139) ? (status >= STATUS_READY_TO_PLAY) : (ListenerUtil.mutListener.listen(47138) ? (status <= STATUS_READY_TO_PLAY) : (ListenerUtil.mutListener.listen(47137) ? (status > STATUS_READY_TO_PLAY) : (ListenerUtil.mutListener.listen(47136) ? (status < STATUS_READY_TO_PLAY) : (ListenerUtil.mutListener.listen(47135) ? (status == STATUS_READY_TO_PLAY) : (status != STATUS_READY_TO_PLAY))))))) {
                if (!ListenerUtil.mutListener.listen(47140)) {
                    setImageResource(R.drawable.ic_play);
                }
                if (!ListenerUtil.mutListener.listen(47141)) {
                    setContentDescription(getContext().getString(R.string.play));
                }
                if (!ListenerUtil.mutListener.listen(47142)) {
                    status = STATUS_READY_TO_PLAY;
                }
            }
        }
    }

    @UiThread
    public void setBroken() {
        if (!ListenerUtil.mutListener.listen(47144)) {
            logger.debug("setBroken");
        }
        if (!ListenerUtil.mutListener.listen(47153)) {
            if ((ListenerUtil.mutListener.listen(47149) ? (status >= STATUS_BROKEN) : (ListenerUtil.mutListener.listen(47148) ? (status <= STATUS_BROKEN) : (ListenerUtil.mutListener.listen(47147) ? (status > STATUS_BROKEN) : (ListenerUtil.mutListener.listen(47146) ? (status < STATUS_BROKEN) : (ListenerUtil.mutListener.listen(47145) ? (status == STATUS_BROKEN) : (status != STATUS_BROKEN))))))) {
                if (!ListenerUtil.mutListener.listen(47150)) {
                    setImageResource(R.drawable.ic_close);
                }
                if (!ListenerUtil.mutListener.listen(47151)) {
                    setContentDescription(getContext().getString(R.string.play));
                }
                if (!ListenerUtil.mutListener.listen(47152)) {
                    status = STATUS_READY_TO_PLAY;
                }
            }
        }
    }

    @UiThread
    public void setPause() {
        if (!ListenerUtil.mutListener.listen(47154)) {
            logger.debug("setPause");
        }
        if (!ListenerUtil.mutListener.listen(47155)) {
            setImageResource(R.drawable.ic_pause);
        }
        if (!ListenerUtil.mutListener.listen(47156)) {
            setContentDescription(getContext().getString(R.string.pause));
        }
        if (!ListenerUtil.mutListener.listen(47157)) {
            status = STATUS_PLAYING;
        }
    }

    public void setTranscoding() {
        if (!ListenerUtil.mutListener.listen(47158)) {
            setHidden();
        }
        if (!ListenerUtil.mutListener.listen(47159)) {
            status = STATUS_TRANSCODING;
        }
    }

    public void setProgressing() {
        if (!ListenerUtil.mutListener.listen(47160)) {
            setProgressing(true);
        }
    }

    @UiThread
    public void setProgressing(boolean cancelable) {
        if (!ListenerUtil.mutListener.listen(47161)) {
            logger.debug("setProgressing cancelable = " + cancelable);
        }
        if (!ListenerUtil.mutListener.listen(47185)) {
            if (progressBarIndeterminate.getVisibility() != VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47166)) {
                    reset();
                }
                if (!ListenerUtil.mutListener.listen(47183)) {
                    if (cancelable) {
                        if (!ListenerUtil.mutListener.listen(47182)) {
                            if ((ListenerUtil.mutListener.listen(47179) ? (status >= STATUS_PROGRESSING) : (ListenerUtil.mutListener.listen(47178) ? (status <= STATUS_PROGRESSING) : (ListenerUtil.mutListener.listen(47177) ? (status > STATUS_PROGRESSING) : (ListenerUtil.mutListener.listen(47176) ? (status < STATUS_PROGRESSING) : (ListenerUtil.mutListener.listen(47175) ? (status == STATUS_PROGRESSING) : (status != STATUS_PROGRESSING))))))) {
                                if (!ListenerUtil.mutListener.listen(47180)) {
                                    setImageResource(R.drawable.ic_close);
                                }
                                if (!ListenerUtil.mutListener.listen(47181)) {
                                    status = STATUS_PROGRESSING;
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(47174)) {
                            if ((ListenerUtil.mutListener.listen(47171) ? (status >= STATUS_PROGRESSING_NO_CANCEL) : (ListenerUtil.mutListener.listen(47170) ? (status <= STATUS_PROGRESSING_NO_CANCEL) : (ListenerUtil.mutListener.listen(47169) ? (status > STATUS_PROGRESSING_NO_CANCEL) : (ListenerUtil.mutListener.listen(47168) ? (status < STATUS_PROGRESSING_NO_CANCEL) : (ListenerUtil.mutListener.listen(47167) ? (status == STATUS_PROGRESSING_NO_CANCEL) : (status != STATUS_PROGRESSING_NO_CANCEL))))))) {
                                if (!ListenerUtil.mutListener.listen(47172)) {
                                    icon.setVisibility(INVISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(47173)) {
                                    status = STATUS_PROGRESSING_NO_CANCEL;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(47184)) {
                    progressBarIndeterminate.setVisibility(VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47162)) {
                    setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(47165)) {
                    if (cancelable) {
                        if (!ListenerUtil.mutListener.listen(47164)) {
                            status = STATUS_PROGRESSING;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(47163)) {
                            status = STATUS_PROGRESSING_NO_CANCEL;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47186)) {
            requestLayout();
        }
    }

    public void setProgressingDeterminate(int max) {
        if (!ListenerUtil.mutListener.listen(47187)) {
            logger.debug("setProgressingDeterminate max = " + max);
        }
        if (!ListenerUtil.mutListener.listen(47188)) {
            setBackgroundImage(null);
        }
        if (!ListenerUtil.mutListener.listen(47189)) {
            setImageResource(R.drawable.ic_close);
        }
        if (!ListenerUtil.mutListener.listen(47190)) {
            setContentDescription(getContext().getString(R.string.cancel));
        }
        if (!ListenerUtil.mutListener.listen(47191)) {
            progressBarDeterminate.setMax(max);
        }
        if (!ListenerUtil.mutListener.listen(47192)) {
            progressBarDeterminate.setProgress(0);
        }
        if (!ListenerUtil.mutListener.listen(47193)) {
            progressBarDeterminate.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(47194)) {
            status = STATUS_PROGRESSING;
        }
        if (!ListenerUtil.mutListener.listen(47195)) {
            progressMax = max;
        }
    }

    public void setProgress(int progress) {
        if (!ListenerUtil.mutListener.listen(47196)) {
            logger.debug("setProgress progress = " + progress);
        }
        if (!ListenerUtil.mutListener.listen(47198)) {
            if (progressBarDeterminate.getVisibility() != VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47197)) {
                    setProgressingDeterminate(progressMax);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47199)) {
            progressBarDeterminate.setProgress(progress);
        }
    }

    public void setRetry() {
        if (!ListenerUtil.mutListener.listen(47200)) {
            logger.debug("setRetry");
        }
        if (!ListenerUtil.mutListener.listen(47201)) {
            setImageResource(R.drawable.ic_refresh_white_36dp);
        }
        if (!ListenerUtil.mutListener.listen(47202)) {
            setContentDescription(getContext().getString(R.string.retry));
        }
        if (!ListenerUtil.mutListener.listen(47203)) {
            status = STATUS_READY_TO_RETRY;
        }
    }

    public void setReadyToDownload() {
        if (!ListenerUtil.mutListener.listen(47204)) {
            logger.debug("setReadyToDownload");
        }
        if (!ListenerUtil.mutListener.listen(47205)) {
            setImageResource(R.drawable.ic_file_download_white_36dp);
        }
        if (!ListenerUtil.mutListener.listen(47206)) {
            setContentDescription(getContext().getString(R.string.download));
        }
        if (!ListenerUtil.mutListener.listen(47207)) {
            status = STATUS_READY_TO_DOWNLOAD;
        }
    }

    public void setImageResource(@DrawableRes int resource) {
        if (!ListenerUtil.mutListener.listen(47208)) {
            logger.debug("setImageResource");
        }
        if (!ListenerUtil.mutListener.listen(47209)) {
            reset();
        }
        if (!ListenerUtil.mutListener.listen(47210)) {
            icon.setImageResource(resource);
        }
        if (!ListenerUtil.mutListener.listen(47211)) {
            icon.setColorFilter(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(47212)) {
            icon.requestLayout();
        }
    }

    public void setBackgroundImage(Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(47213)) {
            logger.debug("setBackgroundImage");
        }
        if (!ListenerUtil.mutListener.listen(47216)) {
            if (bitmap == null) {
                if (!ListenerUtil.mutListener.listen(47215)) {
                    setBackgroundResource(R.drawable.circle_status_icon_color);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47214)) {
                    setBackground(new BitmapDrawable(getResources(), bitmap));
                }
            }
        }
    }

    public int getStatus() {
        return status;
    }

    @Override
    public void setVisibility(int visibility) {
        if (!ListenerUtil.mutListener.listen(47217)) {
            super.setVisibility(visibility);
        }
    }

    protected void onVisibilityChanged(@NonNull View view, int visibility) {
        if (!ListenerUtil.mutListener.listen(47218)) {
            super.onVisibilityChanged(view, visibility);
        }
        if (!ListenerUtil.mutListener.listen(47220)) {
            if (visibilityChangedListener != null) {
                if (!ListenerUtil.mutListener.listen(47219)) {
                    visibilityChangedListener.visibilityChanged(visibility);
                }
            }
        }
    }

    public void setVisibilityListener(OnVisibilityChangedListener listener) {
        if (!ListenerUtil.mutListener.listen(47221)) {
            this.visibilityChangedListener = listener;
        }
    }

    public interface OnVisibilityChangedListener {

        void visibilityChanged(int visibility);
    }
}
