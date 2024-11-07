/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ClippingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.video.VideoTimelineCache;
import static com.google.android.exoplayer2.C.TIME_END_OF_SOURCE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoEditView extends FrameLayout implements DefaultLifecycleObserver {

    private static final Logger logger = LoggerFactory.getLogger(VideoEditView.class);

    private static final int MOVING_NONE = 0;

    private static final int MOVING_LEFT = 1;

    private static final int MOVING_RIGHT = 2;

    private static final String THUMBNAIL_THREAD_NAME = "TimelineThumbs";

    private Context context;

    private int targetHeight, calculatedWidth;

    private Paint borderPaint, arrowPaint, dashPaint, progressPaint, dimPaint;

    private int arrowWidth, arrowHeight, borderWidth;

    private int offsetLeft = 0, offsetRight = 0, touchTargetWidth;

    private long videoCurrentPosition = 0L, videoFileSize = 0L;

    private MediaItem videoItem;

    private int isMoving = MOVING_NONE;

    private GridLayout timelineGridLayout;

    private PlayerView videoView;

    private SimpleExoPlayer videoPlayer;

    private MediaSource videoSource;

    private TextView startTimeTextView, endTimeTextView, sizeTextView;

    private Thread thumbnailThread;

    private Handler progressHandler = new Handler();

    public VideoEditView(Context context) {
        this(context, null);
    }

    public VideoEditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(12239)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(12240)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(12241)) {
            this.targetHeight = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_item_size);
        }
        if (!ListenerUtil.mutListener.listen(12242)) {
            this.arrowWidth = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_arrow_width);
        }
        if (!ListenerUtil.mutListener.listen(12243)) {
            this.arrowHeight = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_arrow_height);
        }
        if (!ListenerUtil.mutListener.listen(12244)) {
            this.borderWidth = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_border_width);
        }
        int progressWidth = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_progress_width);
        if (!ListenerUtil.mutListener.listen(12245)) {
            this.touchTargetWidth = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_touch_target_width);
        }
        if (!ListenerUtil.mutListener.listen(12246)) {
            ((LifecycleOwner) context).getLifecycle().addObserver(this);
        }
        if (!ListenerUtil.mutListener.listen(12247)) {
            LayoutInflater.from(context).inflate(R.layout.view_video_edit, this, true);
        }
        if (!ListenerUtil.mutListener.listen(12248)) {
            this.timelineGridLayout = findViewById(R.id.video_timeline);
        }
        if (!ListenerUtil.mutListener.listen(12249)) {
            this.videoView = findViewById(R.id.video_view);
        }
        if (!ListenerUtil.mutListener.listen(12250)) {
            this.startTimeTextView = findViewById(R.id.start);
        }
        if (!ListenerUtil.mutListener.listen(12251)) {
            this.endTimeTextView = findViewById(R.id.end);
        }
        if (!ListenerUtil.mutListener.listen(12252)) {
            this.sizeTextView = findViewById(R.id.size);
        }
        if (!ListenerUtil.mutListener.listen(12253)) {
            this.borderPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12254)) {
            this.borderPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12255)) {
            this.borderPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12256)) {
            this.borderPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12257)) {
            this.borderPaint.setStrokeWidth(this.borderWidth);
        }
        if (!ListenerUtil.mutListener.listen(12258)) {
            this.dimPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12259)) {
            this.dimPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(12260)) {
            this.dimPaint.setColor(context.getResources().getColor(R.color.background_dim_dark));
        }
        if (!ListenerUtil.mutListener.listen(12261)) {
            this.dimPaint.setAntiAlias(false);
        }
        if (!ListenerUtil.mutListener.listen(12262)) {
            this.dimPaint.setStrokeWidth(0);
        }
        if (!ListenerUtil.mutListener.listen(12263)) {
            this.arrowPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12264)) {
            this.arrowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12265)) {
            this.arrowPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12266)) {
            this.arrowPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12267)) {
            this.arrowPaint.setStrokeWidth(this.borderWidth);
        }
        if (!ListenerUtil.mutListener.listen(12268)) {
            this.dashPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12269)) {
            this.dashPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12270)) {
            this.dashPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12271)) {
            this.dashPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12272)) {
            this.dashPaint.setStrokeWidth(this.borderWidth);
        }
        if (!ListenerUtil.mutListener.listen(12273)) {
            this.dashPaint.setPathEffect(new DashPathEffect(new float[] { 3, 8 }, 0));
        }
        if (!ListenerUtil.mutListener.listen(12274)) {
            this.progressPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12275)) {
            this.progressPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12276)) {
            this.progressPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12277)) {
            this.progressPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12278)) {
            this.progressPaint.setStrokeWidth(progressWidth);
        }
        if (!ListenerUtil.mutListener.listen(12279)) {
            initVideoView();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initVideoView() {
        if (!ListenerUtil.mutListener.listen(12280)) {
            this.videoPlayer = new SimpleExoPlayer.Builder(context).build();
        }
        if (!ListenerUtil.mutListener.listen(12281)) {
            this.videoPlayer.setPlayWhenReady(false);
        }
        if (!ListenerUtil.mutListener.listen(12283)) {
            this.videoPlayer.addListener(new Player.EventListener() {

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (!ListenerUtil.mutListener.listen(12282)) {
                        updateProgressBar();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12284)) {
            this.videoView.setPlayer(videoPlayer);
        }
        if (!ListenerUtil.mutListener.listen(12285)) {
            this.videoView.setControllerHideOnTouch(true);
        }
        if (!ListenerUtil.mutListener.listen(12286)) {
            this.videoView.setControllerShowTimeoutMs(1500);
        }
        if (!ListenerUtil.mutListener.listen(12287)) {
            this.videoView.setControllerAutoShow(true);
        }
        if (!ListenerUtil.mutListener.listen(12288)) {
            this.videoView.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return onTouchEvent(event);
                }
            });
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(12289)) {
            super.dispatchDraw(canvas);
        }
        int left = this.timelineGridLayout.getLeft() + this.offsetLeft;
        int right = (ListenerUtil.mutListener.listen(12293) ? (this.timelineGridLayout.getRight() % this.offsetRight) : (ListenerUtil.mutListener.listen(12292) ? (this.timelineGridLayout.getRight() / this.offsetRight) : (ListenerUtil.mutListener.listen(12291) ? (this.timelineGridLayout.getRight() * this.offsetRight) : (ListenerUtil.mutListener.listen(12290) ? (this.timelineGridLayout.getRight() + this.offsetRight) : (this.timelineGridLayout.getRight() - this.offsetRight)))));
        int top = this.timelineGridLayout.getTop();
        int bottom = this.timelineGridLayout.getBottom();
        if (!ListenerUtil.mutListener.listen(12294)) {
            // draw rectangle
            canvas.drawLine(left, top, left, bottom, this.borderPaint);
        }
        if (!ListenerUtil.mutListener.listen(12295)) {
            canvas.drawLine(right, top, right, bottom, this.borderPaint);
        }
        Path path = new Path();
        if (!ListenerUtil.mutListener.listen(12296)) {
            path.moveTo(left, top);
        }
        if (!ListenerUtil.mutListener.listen(12297)) {
            path.lineTo(right, top);
        }
        if (!ListenerUtil.mutListener.listen(12298)) {
            canvas.drawPath(path, dashPaint);
        }
        if (!ListenerUtil.mutListener.listen(12299)) {
            path = new Path();
        }
        if (!ListenerUtil.mutListener.listen(12300)) {
            path.moveTo(left, bottom);
        }
        if (!ListenerUtil.mutListener.listen(12301)) {
            path.lineTo(right, bottom);
        }
        if (!ListenerUtil.mutListener.listen(12302)) {
            canvas.drawPath(path, dashPaint);
        }
        if (!ListenerUtil.mutListener.listen(12303)) {
            // draw arrows
            path = new Path();
        }
        if (!ListenerUtil.mutListener.listen(12304)) {
            path.setFillType(Path.FillType.EVEN_ODD);
        }
        if (!ListenerUtil.mutListener.listen(12305)) {
            path.moveTo(left, bottom);
        }
        if (!ListenerUtil.mutListener.listen(12310)) {
            path.lineTo((ListenerUtil.mutListener.listen(12309) ? (left % arrowWidth) : (ListenerUtil.mutListener.listen(12308) ? (left / arrowWidth) : (ListenerUtil.mutListener.listen(12307) ? (left * arrowWidth) : (ListenerUtil.mutListener.listen(12306) ? (left - arrowWidth) : (left + arrowWidth))))), bottom);
        }
        if (!ListenerUtil.mutListener.listen(12315)) {
            path.lineTo(left, (ListenerUtil.mutListener.listen(12314) ? (bottom % arrowHeight) : (ListenerUtil.mutListener.listen(12313) ? (bottom / arrowHeight) : (ListenerUtil.mutListener.listen(12312) ? (bottom * arrowHeight) : (ListenerUtil.mutListener.listen(12311) ? (bottom - arrowHeight) : (bottom + arrowHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(12316)) {
            path.close();
        }
        if (!ListenerUtil.mutListener.listen(12317)) {
            canvas.drawPath(path, this.arrowPaint);
        }
        if (!ListenerUtil.mutListener.listen(12318)) {
            path = new Path();
        }
        if (!ListenerUtil.mutListener.listen(12319)) {
            path.setFillType(Path.FillType.EVEN_ODD);
        }
        if (!ListenerUtil.mutListener.listen(12320)) {
            path.moveTo(right, bottom);
        }
        if (!ListenerUtil.mutListener.listen(12325)) {
            path.lineTo((ListenerUtil.mutListener.listen(12324) ? (right % arrowWidth) : (ListenerUtil.mutListener.listen(12323) ? (right / arrowWidth) : (ListenerUtil.mutListener.listen(12322) ? (right * arrowWidth) : (ListenerUtil.mutListener.listen(12321) ? (right + arrowWidth) : (right - arrowWidth))))), bottom);
        }
        if (!ListenerUtil.mutListener.listen(12330)) {
            path.lineTo(right, (ListenerUtil.mutListener.listen(12329) ? (bottom % arrowHeight) : (ListenerUtil.mutListener.listen(12328) ? (bottom / arrowHeight) : (ListenerUtil.mutListener.listen(12327) ? (bottom * arrowHeight) : (ListenerUtil.mutListener.listen(12326) ? (bottom - arrowHeight) : (bottom + arrowHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(12331)) {
            path.close();
        }
        if (!ListenerUtil.mutListener.listen(12332)) {
            canvas.drawPath(path, this.arrowPaint);
        }
        if (!ListenerUtil.mutListener.listen(12333)) {
            path = new Path();
        }
        if (!ListenerUtil.mutListener.listen(12334)) {
            path.setFillType(Path.FillType.EVEN_ODD);
        }
        if (!ListenerUtil.mutListener.listen(12335)) {
            path.moveTo(left, top);
        }
        if (!ListenerUtil.mutListener.listen(12340)) {
            path.lineTo((ListenerUtil.mutListener.listen(12339) ? (left % arrowWidth) : (ListenerUtil.mutListener.listen(12338) ? (left / arrowWidth) : (ListenerUtil.mutListener.listen(12337) ? (left * arrowWidth) : (ListenerUtil.mutListener.listen(12336) ? (left - arrowWidth) : (left + arrowWidth))))), top);
        }
        if (!ListenerUtil.mutListener.listen(12345)) {
            path.lineTo(left, (ListenerUtil.mutListener.listen(12344) ? (top % arrowHeight) : (ListenerUtil.mutListener.listen(12343) ? (top / arrowHeight) : (ListenerUtil.mutListener.listen(12342) ? (top * arrowHeight) : (ListenerUtil.mutListener.listen(12341) ? (top + arrowHeight) : (top - arrowHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(12346)) {
            path.close();
        }
        if (!ListenerUtil.mutListener.listen(12347)) {
            canvas.drawPath(path, this.arrowPaint);
        }
        if (!ListenerUtil.mutListener.listen(12348)) {
            path = new Path();
        }
        if (!ListenerUtil.mutListener.listen(12349)) {
            path.setFillType(Path.FillType.EVEN_ODD);
        }
        if (!ListenerUtil.mutListener.listen(12350)) {
            path.moveTo(right, top);
        }
        if (!ListenerUtil.mutListener.listen(12355)) {
            path.lineTo((ListenerUtil.mutListener.listen(12354) ? (right % arrowWidth) : (ListenerUtil.mutListener.listen(12353) ? (right / arrowWidth) : (ListenerUtil.mutListener.listen(12352) ? (right * arrowWidth) : (ListenerUtil.mutListener.listen(12351) ? (right + arrowWidth) : (right - arrowWidth))))), top);
        }
        if (!ListenerUtil.mutListener.listen(12360)) {
            path.lineTo(right, (ListenerUtil.mutListener.listen(12359) ? (top % arrowHeight) : (ListenerUtil.mutListener.listen(12358) ? (top / arrowHeight) : (ListenerUtil.mutListener.listen(12357) ? (top * arrowHeight) : (ListenerUtil.mutListener.listen(12356) ? (top + arrowHeight) : (top - arrowHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(12361)) {
            path.close();
        }
        if (!ListenerUtil.mutListener.listen(12362)) {
            canvas.drawPath(path, this.arrowPaint);
        }
        if (!ListenerUtil.mutListener.listen(12397)) {
            if (videoItem != null) {
                if (!ListenerUtil.mutListener.listen(12369)) {
                    if ((ListenerUtil.mutListener.listen(12367) ? (videoItem.getStartTimeMs() >= 0) : (ListenerUtil.mutListener.listen(12366) ? (videoItem.getStartTimeMs() <= 0) : (ListenerUtil.mutListener.listen(12365) ? (videoItem.getStartTimeMs() < 0) : (ListenerUtil.mutListener.listen(12364) ? (videoItem.getStartTimeMs() != 0) : (ListenerUtil.mutListener.listen(12363) ? (videoItem.getStartTimeMs() == 0) : (videoItem.getStartTimeMs() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(12368)) {
                            canvas.drawRect(new Rect(this.timelineGridLayout.getLeft(), top, left, bottom), this.dimPaint);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12371)) {
                    if (videoItem.getEndTimeMs() != MediaItem.TIME_UNDEFINED) {
                        if (!ListenerUtil.mutListener.listen(12370)) {
                            canvas.drawRect(new Rect(right, top, this.timelineGridLayout.getRight(), bottom), this.dimPaint);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12396)) {
                    if ((ListenerUtil.mutListener.listen(12383) ? ((ListenerUtil.mutListener.listen(12377) ? (videoItem.getDurationMs() != 0 || (ListenerUtil.mutListener.listen(12376) ? (videoCurrentPosition >= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12375) ? (videoCurrentPosition <= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12374) ? (videoCurrentPosition < videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12373) ? (videoCurrentPosition != videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12372) ? (videoCurrentPosition == videoItem.getStartTimeMs()) : (videoCurrentPosition > videoItem.getStartTimeMs()))))))) : (videoItem.getDurationMs() != 0 && (ListenerUtil.mutListener.listen(12376) ? (videoCurrentPosition >= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12375) ? (videoCurrentPosition <= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12374) ? (videoCurrentPosition < videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12373) ? (videoCurrentPosition != videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12372) ? (videoCurrentPosition == videoItem.getStartTimeMs()) : (videoCurrentPosition > videoItem.getStartTimeMs())))))))) || (ListenerUtil.mutListener.listen(12382) ? (videoCurrentPosition >= videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12381) ? (videoCurrentPosition <= videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12380) ? (videoCurrentPosition > videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12379) ? (videoCurrentPosition != videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12378) ? (videoCurrentPosition == videoItem.getEndTimeMs()) : (videoCurrentPosition < videoItem.getEndTimeMs()))))))) : ((ListenerUtil.mutListener.listen(12377) ? (videoItem.getDurationMs() != 0 || (ListenerUtil.mutListener.listen(12376) ? (videoCurrentPosition >= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12375) ? (videoCurrentPosition <= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12374) ? (videoCurrentPosition < videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12373) ? (videoCurrentPosition != videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12372) ? (videoCurrentPosition == videoItem.getStartTimeMs()) : (videoCurrentPosition > videoItem.getStartTimeMs()))))))) : (videoItem.getDurationMs() != 0 && (ListenerUtil.mutListener.listen(12376) ? (videoCurrentPosition >= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12375) ? (videoCurrentPosition <= videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12374) ? (videoCurrentPosition < videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12373) ? (videoCurrentPosition != videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12372) ? (videoCurrentPosition == videoItem.getStartTimeMs()) : (videoCurrentPosition > videoItem.getStartTimeMs())))))))) && (ListenerUtil.mutListener.listen(12382) ? (videoCurrentPosition >= videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12381) ? (videoCurrentPosition <= videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12380) ? (videoCurrentPosition > videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12379) ? (videoCurrentPosition != videoItem.getEndTimeMs()) : (ListenerUtil.mutListener.listen(12378) ? (videoCurrentPosition == videoItem.getEndTimeMs()) : (videoCurrentPosition < videoItem.getEndTimeMs()))))))))) {
                        int offset = (int) ((ListenerUtil.mutListener.listen(12391) ? ((ListenerUtil.mutListener.listen(12387) ? (this.timelineGridLayout.getWidth() % videoCurrentPosition) : (ListenerUtil.mutListener.listen(12386) ? (this.timelineGridLayout.getWidth() / videoCurrentPosition) : (ListenerUtil.mutListener.listen(12385) ? (this.timelineGridLayout.getWidth() - videoCurrentPosition) : (ListenerUtil.mutListener.listen(12384) ? (this.timelineGridLayout.getWidth() + videoCurrentPosition) : (this.timelineGridLayout.getWidth() * videoCurrentPosition))))) % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12390) ? ((ListenerUtil.mutListener.listen(12387) ? (this.timelineGridLayout.getWidth() % videoCurrentPosition) : (ListenerUtil.mutListener.listen(12386) ? (this.timelineGridLayout.getWidth() / videoCurrentPosition) : (ListenerUtil.mutListener.listen(12385) ? (this.timelineGridLayout.getWidth() - videoCurrentPosition) : (ListenerUtil.mutListener.listen(12384) ? (this.timelineGridLayout.getWidth() + videoCurrentPosition) : (this.timelineGridLayout.getWidth() * videoCurrentPosition))))) * videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12389) ? ((ListenerUtil.mutListener.listen(12387) ? (this.timelineGridLayout.getWidth() % videoCurrentPosition) : (ListenerUtil.mutListener.listen(12386) ? (this.timelineGridLayout.getWidth() / videoCurrentPosition) : (ListenerUtil.mutListener.listen(12385) ? (this.timelineGridLayout.getWidth() - videoCurrentPosition) : (ListenerUtil.mutListener.listen(12384) ? (this.timelineGridLayout.getWidth() + videoCurrentPosition) : (this.timelineGridLayout.getWidth() * videoCurrentPosition))))) - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12388) ? ((ListenerUtil.mutListener.listen(12387) ? (this.timelineGridLayout.getWidth() % videoCurrentPosition) : (ListenerUtil.mutListener.listen(12386) ? (this.timelineGridLayout.getWidth() / videoCurrentPosition) : (ListenerUtil.mutListener.listen(12385) ? (this.timelineGridLayout.getWidth() - videoCurrentPosition) : (ListenerUtil.mutListener.listen(12384) ? (this.timelineGridLayout.getWidth() + videoCurrentPosition) : (this.timelineGridLayout.getWidth() * videoCurrentPosition))))) + videoItem.getDurationMs()) : ((ListenerUtil.mutListener.listen(12387) ? (this.timelineGridLayout.getWidth() % videoCurrentPosition) : (ListenerUtil.mutListener.listen(12386) ? (this.timelineGridLayout.getWidth() / videoCurrentPosition) : (ListenerUtil.mutListener.listen(12385) ? (this.timelineGridLayout.getWidth() - videoCurrentPosition) : (ListenerUtil.mutListener.listen(12384) ? (this.timelineGridLayout.getWidth() + videoCurrentPosition) : (this.timelineGridLayout.getWidth() * videoCurrentPosition))))) / videoItem.getDurationMs())))))) + this.timelineGridLayout.getLeft();
                        if (!ListenerUtil.mutListener.listen(12392)) {
                            path = new Path();
                        }
                        if (!ListenerUtil.mutListener.listen(12393)) {
                            path.moveTo(offset, top);
                        }
                        if (!ListenerUtil.mutListener.listen(12394)) {
                            path.lineTo(offset, bottom);
                        }
                        if (!ListenerUtil.mutListener.listen(12395)) {
                            canvas.drawPath(path, progressPaint);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        int left = this.timelineGridLayout.getLeft() + this.offsetLeft;
        int right = (ListenerUtil.mutListener.listen(12401) ? (this.timelineGridLayout.getRight() % this.offsetRight) : (ListenerUtil.mutListener.listen(12400) ? (this.timelineGridLayout.getRight() / this.offsetRight) : (ListenerUtil.mutListener.listen(12399) ? (this.timelineGridLayout.getRight() * this.offsetRight) : (ListenerUtil.mutListener.listen(12398) ? (this.timelineGridLayout.getRight() + this.offsetRight) : (this.timelineGridLayout.getRight() - this.offsetRight)))));
        if (!ListenerUtil.mutListener.listen(12575)) {
            switch(action) {
                case MotionEvent.ACTION_DOWN:
                    if (!ListenerUtil.mutListener.listen(12460)) {
                        if ((ListenerUtil.mutListener.listen(12416) ? ((ListenerUtil.mutListener.listen(12410) ? (y <= ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12409) ? (y > ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12408) ? (y < ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12407) ? (y != ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12406) ? (y == ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (y >= ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight)))))))))))) || (ListenerUtil.mutListener.listen(12415) ? (y >= (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12414) ? (y > (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12413) ? (y < (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12412) ? (y != (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12411) ? (y == (this.timelineGridLayout.getBottom() + arrowHeight)) : (y <= (this.timelineGridLayout.getBottom() + arrowHeight)))))))) : ((ListenerUtil.mutListener.listen(12410) ? (y <= ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12409) ? (y > ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12408) ? (y < ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12407) ? (y != ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (ListenerUtil.mutListener.listen(12406) ? (y == ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight))))))) : (y >= ((ListenerUtil.mutListener.listen(12405) ? (this.timelineGridLayout.getTop() % arrowHeight) : (ListenerUtil.mutListener.listen(12404) ? (this.timelineGridLayout.getTop() / arrowHeight) : (ListenerUtil.mutListener.listen(12403) ? (this.timelineGridLayout.getTop() * arrowHeight) : (ListenerUtil.mutListener.listen(12402) ? (this.timelineGridLayout.getTop() + arrowHeight) : (this.timelineGridLayout.getTop() - arrowHeight)))))))))))) && (ListenerUtil.mutListener.listen(12415) ? (y >= (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12414) ? (y > (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12413) ? (y < (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12412) ? (y != (this.timelineGridLayout.getBottom() + arrowHeight)) : (ListenerUtil.mutListener.listen(12411) ? (y == (this.timelineGridLayout.getBottom() + arrowHeight)) : (y <= (this.timelineGridLayout.getBottom() + arrowHeight)))))))))) {
                            if (!ListenerUtil.mutListener.listen(12459)) {
                                if ((ListenerUtil.mutListener.listen(12435) ? ((ListenerUtil.mutListener.listen(12425) ? (x <= ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12424) ? (x > ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12423) ? (x < ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12422) ? (x != ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12421) ? (x == ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (x >= ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth)))))))))))) || (ListenerUtil.mutListener.listen(12434) ? (x >= ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12433) ? (x > ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12432) ? (x < ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12431) ? (x != ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12430) ? (x == ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (x <= ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))))))))) : ((ListenerUtil.mutListener.listen(12425) ? (x <= ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12424) ? (x > ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12423) ? (x < ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12422) ? (x != ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12421) ? (x == ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth))))))) : (x >= ((ListenerUtil.mutListener.listen(12420) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12419) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12418) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12417) ? (left + touchTargetWidth) : (left - touchTargetWidth)))))))))))) && (ListenerUtil.mutListener.listen(12434) ? (x >= ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12433) ? (x > ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12432) ? (x < ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12431) ? (x != ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12430) ? (x == ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (x <= ((ListenerUtil.mutListener.listen(12429) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12428) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12427) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12426) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(12457)) {
                                        logger.debug("start moving left: " + x);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12458)) {
                                        isMoving = MOVING_LEFT;
                                    }
                                    return true;
                                } else if ((ListenerUtil.mutListener.listen(12454) ? ((ListenerUtil.mutListener.listen(12444) ? (x <= ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12443) ? (x > ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12442) ? (x < ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12441) ? (x != ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12440) ? (x == ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (x >= ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth)))))))))))) || (ListenerUtil.mutListener.listen(12453) ? (x >= ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12452) ? (x > ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12451) ? (x < ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12450) ? (x != ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12449) ? (x == ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (x <= ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))))))))) : ((ListenerUtil.mutListener.listen(12444) ? (x <= ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12443) ? (x > ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12442) ? (x < ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12441) ? (x != ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12440) ? (x == ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (x >= ((ListenerUtil.mutListener.listen(12439) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12438) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12437) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12436) ? (right + touchTargetWidth) : (right - touchTargetWidth)))))))))))) && (ListenerUtil.mutListener.listen(12453) ? (x >= ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12452) ? (x > ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12451) ? (x < ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12450) ? (x != ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12449) ? (x == ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))) : (x <= ((ListenerUtil.mutListener.listen(12448) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12447) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12446) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12445) ? (right - touchTargetWidth) : (right + touchTargetWidth))))))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(12455)) {
                                        logger.debug("start moving right: " + x);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12456)) {
                                        isMoving = MOVING_RIGHT;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12461)) {
                        isMoving = MOVING_NONE;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!ListenerUtil.mutListener.listen(12462)) {
                        logger.debug("moving " + x);
                    }
                    if (!ListenerUtil.mutListener.listen(12553)) {
                        if ((ListenerUtil.mutListener.listen(12467) ? (isMoving >= MOVING_NONE) : (ListenerUtil.mutListener.listen(12466) ? (isMoving <= MOVING_NONE) : (ListenerUtil.mutListener.listen(12465) ? (isMoving > MOVING_NONE) : (ListenerUtil.mutListener.listen(12464) ? (isMoving < MOVING_NONE) : (ListenerUtil.mutListener.listen(12463) ? (isMoving == MOVING_NONE) : (isMoving != MOVING_NONE))))))) {
                            int oldOffsetLeft = offsetLeft;
                            int oldOffsetRight = offsetRight;
                            if (!ListenerUtil.mutListener.listen(12552)) {
                                switch(isMoving) {
                                    case MOVING_LEFT:
                                        if (!ListenerUtil.mutListener.listen(12468)) {
                                            logger.debug("moving left to: " + x);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12473)) {
                                            offsetLeft = (ListenerUtil.mutListener.listen(12472) ? (x % this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12471) ? (x / this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12470) ? (x * this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12469) ? (x + this.timelineGridLayout.getLeft()) : (x - this.timelineGridLayout.getLeft())))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(12498)) {
                                            if ((ListenerUtil.mutListener.listen(12478) ? (offsetLeft >= 0) : (ListenerUtil.mutListener.listen(12477) ? (offsetLeft <= 0) : (ListenerUtil.mutListener.listen(12476) ? (offsetLeft > 0) : (ListenerUtil.mutListener.listen(12475) ? (offsetLeft != 0) : (ListenerUtil.mutListener.listen(12474) ? (offsetLeft == 0) : (offsetLeft < 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(12497)) {
                                                    offsetLeft = 0;
                                                }
                                            } else if ((ListenerUtil.mutListener.listen(12487) ? (x >= ((ListenerUtil.mutListener.listen(12482) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12481) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12480) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12479) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12486) ? (x <= ((ListenerUtil.mutListener.listen(12482) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12481) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12480) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12479) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12485) ? (x < ((ListenerUtil.mutListener.listen(12482) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12481) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12480) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12479) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12484) ? (x != ((ListenerUtil.mutListener.listen(12482) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12481) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12480) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12479) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12483) ? (x == ((ListenerUtil.mutListener.listen(12482) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12481) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12480) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12479) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))) : (x > ((ListenerUtil.mutListener.listen(12482) ? (right % touchTargetWidth) : (ListenerUtil.mutListener.listen(12481) ? (right / touchTargetWidth) : (ListenerUtil.mutListener.listen(12480) ? (right * touchTargetWidth) : (ListenerUtil.mutListener.listen(12479) ? (right + touchTargetWidth) : (right - touchTargetWidth))))))))))))) {
                                                if (!ListenerUtil.mutListener.listen(12496)) {
                                                    offsetLeft = (ListenerUtil.mutListener.listen(12495) ? ((ListenerUtil.mutListener.listen(12491) ? (right % this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12490) ? (right / this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12489) ? (right * this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12488) ? (right + this.timelineGridLayout.getLeft()) : (right - this.timelineGridLayout.getLeft()))))) % touchTargetWidth) : (ListenerUtil.mutListener.listen(12494) ? ((ListenerUtil.mutListener.listen(12491) ? (right % this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12490) ? (right / this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12489) ? (right * this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12488) ? (right + this.timelineGridLayout.getLeft()) : (right - this.timelineGridLayout.getLeft()))))) / touchTargetWidth) : (ListenerUtil.mutListener.listen(12493) ? ((ListenerUtil.mutListener.listen(12491) ? (right % this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12490) ? (right / this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12489) ? (right * this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12488) ? (right + this.timelineGridLayout.getLeft()) : (right - this.timelineGridLayout.getLeft()))))) * touchTargetWidth) : (ListenerUtil.mutListener.listen(12492) ? ((ListenerUtil.mutListener.listen(12491) ? (right % this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12490) ? (right / this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12489) ? (right * this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12488) ? (right + this.timelineGridLayout.getLeft()) : (right - this.timelineGridLayout.getLeft()))))) + touchTargetWidth) : ((ListenerUtil.mutListener.listen(12491) ? (right % this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12490) ? (right / this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12489) ? (right * this.timelineGridLayout.getLeft()) : (ListenerUtil.mutListener.listen(12488) ? (right + this.timelineGridLayout.getLeft()) : (right - this.timelineGridLayout.getLeft()))))) - touchTargetWidth)))));
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(12507)) {
                                            if ((ListenerUtil.mutListener.listen(12503) ? (oldOffsetLeft >= offsetLeft) : (ListenerUtil.mutListener.listen(12502) ? (oldOffsetLeft <= offsetLeft) : (ListenerUtil.mutListener.listen(12501) ? (oldOffsetLeft > offsetLeft) : (ListenerUtil.mutListener.listen(12500) ? (oldOffsetLeft < offsetLeft) : (ListenerUtil.mutListener.listen(12499) ? (oldOffsetLeft == offsetLeft) : (oldOffsetLeft != offsetLeft))))))) {
                                                if (!ListenerUtil.mutListener.listen(12504)) {
                                                    videoItem.setStartTimeMs(getVideoPositionFromTimelinePosition(offsetLeft));
                                                }
                                                if (!ListenerUtil.mutListener.listen(12505)) {
                                                    updateStartAndEnd();
                                                }
                                                if (!ListenerUtil.mutListener.listen(12506)) {
                                                    invalidate();
                                                }
                                            }
                                        }
                                        break;
                                    case MOVING_RIGHT:
                                        if (!ListenerUtil.mutListener.listen(12508)) {
                                            logger.debug("moving right to: " + x);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12513)) {
                                            offsetRight = (ListenerUtil.mutListener.listen(12512) ? (this.timelineGridLayout.getRight() % x) : (ListenerUtil.mutListener.listen(12511) ? (this.timelineGridLayout.getRight() / x) : (ListenerUtil.mutListener.listen(12510) ? (this.timelineGridLayout.getRight() * x) : (ListenerUtil.mutListener.listen(12509) ? (this.timelineGridLayout.getRight() + x) : (this.timelineGridLayout.getRight() - x)))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(12538)) {
                                            if ((ListenerUtil.mutListener.listen(12518) ? (offsetRight >= 0) : (ListenerUtil.mutListener.listen(12517) ? (offsetRight <= 0) : (ListenerUtil.mutListener.listen(12516) ? (offsetRight > 0) : (ListenerUtil.mutListener.listen(12515) ? (offsetRight != 0) : (ListenerUtil.mutListener.listen(12514) ? (offsetRight == 0) : (offsetRight < 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(12537)) {
                                                    offsetRight = 0;
                                                }
                                            } else if ((ListenerUtil.mutListener.listen(12527) ? (x >= ((ListenerUtil.mutListener.listen(12522) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12521) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12520) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12519) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12526) ? (x <= ((ListenerUtil.mutListener.listen(12522) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12521) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12520) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12519) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12525) ? (x > ((ListenerUtil.mutListener.listen(12522) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12521) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12520) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12519) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12524) ? (x != ((ListenerUtil.mutListener.listen(12522) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12521) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12520) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12519) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12523) ? (x == ((ListenerUtil.mutListener.listen(12522) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12521) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12520) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12519) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (x < ((ListenerUtil.mutListener.listen(12522) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12521) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12520) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12519) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))))))))) {
                                                if (!ListenerUtil.mutListener.listen(12536)) {
                                                    offsetRight = (ListenerUtil.mutListener.listen(12535) ? (this.timelineGridLayout.getRight() % ((ListenerUtil.mutListener.listen(12531) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12530) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12529) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12528) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12534) ? (this.timelineGridLayout.getRight() / ((ListenerUtil.mutListener.listen(12531) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12530) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12529) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12528) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12533) ? (this.timelineGridLayout.getRight() * ((ListenerUtil.mutListener.listen(12531) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12530) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12529) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12528) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (ListenerUtil.mutListener.listen(12532) ? (this.timelineGridLayout.getRight() + ((ListenerUtil.mutListener.listen(12531) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12530) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12529) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12528) ? (left - touchTargetWidth) : (left + touchTargetWidth))))))) : (this.timelineGridLayout.getRight() - ((ListenerUtil.mutListener.listen(12531) ? (left % touchTargetWidth) : (ListenerUtil.mutListener.listen(12530) ? (left / touchTargetWidth) : (ListenerUtil.mutListener.listen(12529) ? (left * touchTargetWidth) : (ListenerUtil.mutListener.listen(12528) ? (left - touchTargetWidth) : (left + touchTargetWidth)))))))))));
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(12551)) {
                                            if ((ListenerUtil.mutListener.listen(12543) ? (oldOffsetRight >= offsetRight) : (ListenerUtil.mutListener.listen(12542) ? (oldOffsetRight <= offsetRight) : (ListenerUtil.mutListener.listen(12541) ? (oldOffsetRight > offsetRight) : (ListenerUtil.mutListener.listen(12540) ? (oldOffsetRight < offsetRight) : (ListenerUtil.mutListener.listen(12539) ? (oldOffsetRight == offsetRight) : (oldOffsetRight != offsetRight))))))) {
                                                if (!ListenerUtil.mutListener.listen(12548)) {
                                                    videoItem.setEndTimeMs(getVideoPositionFromTimelinePosition((ListenerUtil.mutListener.listen(12547) ? (this.timelineGridLayout.getWidth() % offsetRight) : (ListenerUtil.mutListener.listen(12546) ? (this.timelineGridLayout.getWidth() / offsetRight) : (ListenerUtil.mutListener.listen(12545) ? (this.timelineGridLayout.getWidth() * offsetRight) : (ListenerUtil.mutListener.listen(12544) ? (this.timelineGridLayout.getWidth() + offsetRight) : (this.timelineGridLayout.getWidth() - offsetRight)))))));
                                                }
                                                if (!ListenerUtil.mutListener.listen(12549)) {
                                                    updateStartAndEnd();
                                                }
                                                if (!ListenerUtil.mutListener.listen(12550)) {
                                                    invalidate();
                                                }
                                            }
                                        }
                                        break;
                                }
                            }
                            return true;
                        }
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (!ListenerUtil.mutListener.listen(12574)) {
                        if ((ListenerUtil.mutListener.listen(12564) ? ((ListenerUtil.mutListener.listen(12558) ? (isMoving >= MOVING_LEFT) : (ListenerUtil.mutListener.listen(12557) ? (isMoving <= MOVING_LEFT) : (ListenerUtil.mutListener.listen(12556) ? (isMoving > MOVING_LEFT) : (ListenerUtil.mutListener.listen(12555) ? (isMoving < MOVING_LEFT) : (ListenerUtil.mutListener.listen(12554) ? (isMoving != MOVING_LEFT) : (isMoving == MOVING_LEFT)))))) && (ListenerUtil.mutListener.listen(12563) ? (isMoving >= MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12562) ? (isMoving <= MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12561) ? (isMoving > MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12560) ? (isMoving < MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12559) ? (isMoving != MOVING_RIGHT) : (isMoving == MOVING_RIGHT))))))) : ((ListenerUtil.mutListener.listen(12558) ? (isMoving >= MOVING_LEFT) : (ListenerUtil.mutListener.listen(12557) ? (isMoving <= MOVING_LEFT) : (ListenerUtil.mutListener.listen(12556) ? (isMoving > MOVING_LEFT) : (ListenerUtil.mutListener.listen(12555) ? (isMoving < MOVING_LEFT) : (ListenerUtil.mutListener.listen(12554) ? (isMoving != MOVING_LEFT) : (isMoving == MOVING_LEFT)))))) || (ListenerUtil.mutListener.listen(12563) ? (isMoving >= MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12562) ? (isMoving <= MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12561) ? (isMoving > MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12560) ? (isMoving < MOVING_RIGHT) : (ListenerUtil.mutListener.listen(12559) ? (isMoving != MOVING_RIGHT) : (isMoving == MOVING_RIGHT))))))))) {
                            if (!ListenerUtil.mutListener.listen(12565)) {
                                videoItem.setStartTimeMs(getVideoPositionFromTimelinePosition(offsetLeft));
                            }
                            if (!ListenerUtil.mutListener.listen(12570)) {
                                videoItem.setEndTimeMs(getVideoPositionFromTimelinePosition((ListenerUtil.mutListener.listen(12569) ? (this.timelineGridLayout.getWidth() % offsetRight) : (ListenerUtil.mutListener.listen(12568) ? (this.timelineGridLayout.getWidth() / offsetRight) : (ListenerUtil.mutListener.listen(12567) ? (this.timelineGridLayout.getWidth() * offsetRight) : (ListenerUtil.mutListener.listen(12566) ? (this.timelineGridLayout.getWidth() + offsetRight) : (this.timelineGridLayout.getWidth() - offsetRight)))))));
                            }
                            if (!ListenerUtil.mutListener.listen(12571)) {
                                isMoving = MOVING_NONE;
                            }
                            if (!ListenerUtil.mutListener.listen(12572)) {
                                updateStartAndEnd();
                            }
                            if (!ListenerUtil.mutListener.listen(12573)) {
                                preparePlayer();
                            }
                            return true;
                        }
                    }
            }
        }
        return super.onTouchEvent(event);
    }

    @SuppressLint("StaticFieldLeak")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @UiThread
    public void setVideo(MediaItem mediaItem) {
        int numColumns = calculateNumColumns();
        if (!ListenerUtil.mutListener.listen(12588)) {
            if ((ListenerUtil.mutListener.listen(12586) ? ((ListenerUtil.mutListener.listen(12580) ? (numColumns >= 0) : (ListenerUtil.mutListener.listen(12579) ? (numColumns > 0) : (ListenerUtil.mutListener.listen(12578) ? (numColumns < 0) : (ListenerUtil.mutListener.listen(12577) ? (numColumns != 0) : (ListenerUtil.mutListener.listen(12576) ? (numColumns == 0) : (numColumns <= 0)))))) && (ListenerUtil.mutListener.listen(12585) ? (numColumns >= 64) : (ListenerUtil.mutListener.listen(12584) ? (numColumns <= 64) : (ListenerUtil.mutListener.listen(12583) ? (numColumns < 64) : (ListenerUtil.mutListener.listen(12582) ? (numColumns != 64) : (ListenerUtil.mutListener.listen(12581) ? (numColumns == 64) : (numColumns > 64))))))) : ((ListenerUtil.mutListener.listen(12580) ? (numColumns >= 0) : (ListenerUtil.mutListener.listen(12579) ? (numColumns > 0) : (ListenerUtil.mutListener.listen(12578) ? (numColumns < 0) : (ListenerUtil.mutListener.listen(12577) ? (numColumns != 0) : (ListenerUtil.mutListener.listen(12576) ? (numColumns == 0) : (numColumns <= 0)))))) || (ListenerUtil.mutListener.listen(12585) ? (numColumns >= 64) : (ListenerUtil.mutListener.listen(12584) ? (numColumns <= 64) : (ListenerUtil.mutListener.listen(12583) ? (numColumns < 64) : (ListenerUtil.mutListener.listen(12582) ? (numColumns != 64) : (ListenerUtil.mutListener.listen(12581) ? (numColumns == 64) : (numColumns > 64))))))))) {
                if (!ListenerUtil.mutListener.listen(12587)) {
                    numColumns = GridLayout.UNDEFINED;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12589)) {
            this.videoItem = mediaItem;
        }
        if (!ListenerUtil.mutListener.listen(12592)) {
            if ((ListenerUtil.mutListener.listen(12590) ? (thumbnailThread != null || thumbnailThread.isAlive()) : (thumbnailThread != null && thumbnailThread.isAlive()))) {
                if (!ListenerUtil.mutListener.listen(12591)) {
                    thumbnailThread.interrupt();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12593)) {
            this.timelineGridLayout.setUseDefaultMargins(false);
        }
        if (!ListenerUtil.mutListener.listen(12594)) {
            this.timelineGridLayout.removeAllViewsInLayout();
        }
        GridLayout.Spec rowSpec = GridLayout.spec(0, 1, 1);
        if (!ListenerUtil.mutListener.listen(12611)) {
            {
                long _loopCounter117 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12610) ? (i >= numColumns) : (ListenerUtil.mutListener.listen(12609) ? (i <= numColumns) : (ListenerUtil.mutListener.listen(12608) ? (i > numColumns) : (ListenerUtil.mutListener.listen(12607) ? (i != numColumns) : (ListenerUtil.mutListener.listen(12606) ? (i == numColumns) : (i < numColumns)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter117", ++_loopCounter117);
                    GridLayout.Spec colSpec = GridLayout.spec(i, 1, 1);
                    FrameLayout frameLayout = new FrameLayout(context);
                    if (!ListenerUtil.mutListener.listen(12595)) {
                        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(calculatedWidth, targetHeight));
                    }
                    ImageView imageView = new ImageView(context);
                    if (!ListenerUtil.mutListener.listen(12596)) {
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    }
                    if (!ListenerUtil.mutListener.listen(12597)) {
                        imageView.setAdjustViewBounds(false);
                    }
                    if (!ListenerUtil.mutListener.listen(12598)) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                    if (!ListenerUtil.mutListener.listen(12599)) {
                        imageView.setTag(i);
                    }
                    if (!ListenerUtil.mutListener.listen(12600)) {
                        frameLayout.addView(imageView);
                    }
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    if (!ListenerUtil.mutListener.listen(12601)) {
                        layoutParams.rowSpec = rowSpec;
                    }
                    if (!ListenerUtil.mutListener.listen(12602)) {
                        layoutParams.columnSpec = colSpec;
                    }
                    if (!ListenerUtil.mutListener.listen(12603)) {
                        layoutParams.width = calculatedWidth;
                    }
                    if (!ListenerUtil.mutListener.listen(12604)) {
                        layoutParams.height = targetHeight;
                    }
                    if (!ListenerUtil.mutListener.listen(12605)) {
                        this.timelineGridLayout.addView(frameLayout, layoutParams);
                    }
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(12613)) {
                this.timelineGridLayout.setColumnCount(numColumns);
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(12612)) {
                logger.debug("Invalid column count. Num columns {}", numColumns);
            }
        }
        if (!ListenerUtil.mutListener.listen(12712)) {
            thumbnailThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    int height = 0, width = 0, duration = 0;
                    // do not use automatic resource management on MediaMetadataRetriever
                    MediaMetadataRetriever metaDataRetriever = new MediaMetadataRetriever();
                    try {
                        if (!ListenerUtil.mutListener.listen(12617)) {
                            metaDataRetriever.setDataSource(ThreemaApplication.getAppContext(), mediaItem.getUri());
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(12621)) {
                                height = Integer.parseInt(metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
                            }
                            if (!ListenerUtil.mutListener.listen(12622)) {
                                width = Integer.parseInt(metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                            }
                            if (!ListenerUtil.mutListener.listen(12623)) {
                                duration = Integer.parseInt(metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                            }
                        } catch (NumberFormatException e) {
                            // some phones (notably the galaxy s7) do not provide width/height.
                            Bitmap bitmap = metaDataRetriever.getFrameAtTime(1);
                            if (!ListenerUtil.mutListener.listen(12620)) {
                                if (bitmap != null) {
                                    if (!ListenerUtil.mutListener.listen(12618)) {
                                        height = bitmap.getHeight();
                                    }
                                    if (!ListenerUtil.mutListener.listen(12619)) {
                                        width = bitmap.getWidth();
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12641)) {
                            if ((ListenerUtil.mutListener.listen(12640) ? ((ListenerUtil.mutListener.listen(12634) ? ((ListenerUtil.mutListener.listen(12628) ? (duration >= 0) : (ListenerUtil.mutListener.listen(12627) ? (duration <= 0) : (ListenerUtil.mutListener.listen(12626) ? (duration > 0) : (ListenerUtil.mutListener.listen(12625) ? (duration < 0) : (ListenerUtil.mutListener.listen(12624) ? (duration != 0) : (duration == 0)))))) && (ListenerUtil.mutListener.listen(12633) ? (height >= 0) : (ListenerUtil.mutListener.listen(12632) ? (height <= 0) : (ListenerUtil.mutListener.listen(12631) ? (height > 0) : (ListenerUtil.mutListener.listen(12630) ? (height < 0) : (ListenerUtil.mutListener.listen(12629) ? (height != 0) : (height == 0))))))) : ((ListenerUtil.mutListener.listen(12628) ? (duration >= 0) : (ListenerUtil.mutListener.listen(12627) ? (duration <= 0) : (ListenerUtil.mutListener.listen(12626) ? (duration > 0) : (ListenerUtil.mutListener.listen(12625) ? (duration < 0) : (ListenerUtil.mutListener.listen(12624) ? (duration != 0) : (duration == 0)))))) || (ListenerUtil.mutListener.listen(12633) ? (height >= 0) : (ListenerUtil.mutListener.listen(12632) ? (height <= 0) : (ListenerUtil.mutListener.listen(12631) ? (height > 0) : (ListenerUtil.mutListener.listen(12630) ? (height < 0) : (ListenerUtil.mutListener.listen(12629) ? (height != 0) : (height == 0)))))))) && (ListenerUtil.mutListener.listen(12639) ? (width >= 0) : (ListenerUtil.mutListener.listen(12638) ? (width <= 0) : (ListenerUtil.mutListener.listen(12637) ? (width > 0) : (ListenerUtil.mutListener.listen(12636) ? (width < 0) : (ListenerUtil.mutListener.listen(12635) ? (width != 0) : (width == 0))))))) : ((ListenerUtil.mutListener.listen(12634) ? ((ListenerUtil.mutListener.listen(12628) ? (duration >= 0) : (ListenerUtil.mutListener.listen(12627) ? (duration <= 0) : (ListenerUtil.mutListener.listen(12626) ? (duration > 0) : (ListenerUtil.mutListener.listen(12625) ? (duration < 0) : (ListenerUtil.mutListener.listen(12624) ? (duration != 0) : (duration == 0)))))) && (ListenerUtil.mutListener.listen(12633) ? (height >= 0) : (ListenerUtil.mutListener.listen(12632) ? (height <= 0) : (ListenerUtil.mutListener.listen(12631) ? (height > 0) : (ListenerUtil.mutListener.listen(12630) ? (height < 0) : (ListenerUtil.mutListener.listen(12629) ? (height != 0) : (height == 0))))))) : ((ListenerUtil.mutListener.listen(12628) ? (duration >= 0) : (ListenerUtil.mutListener.listen(12627) ? (duration <= 0) : (ListenerUtil.mutListener.listen(12626) ? (duration > 0) : (ListenerUtil.mutListener.listen(12625) ? (duration < 0) : (ListenerUtil.mutListener.listen(12624) ? (duration != 0) : (duration == 0)))))) || (ListenerUtil.mutListener.listen(12633) ? (height >= 0) : (ListenerUtil.mutListener.listen(12632) ? (height <= 0) : (ListenerUtil.mutListener.listen(12631) ? (height > 0) : (ListenerUtil.mutListener.listen(12630) ? (height < 0) : (ListenerUtil.mutListener.listen(12629) ? (height != 0) : (height == 0)))))))) || (ListenerUtil.mutListener.listen(12639) ? (width >= 0) : (ListenerUtil.mutListener.listen(12638) ? (width <= 0) : (ListenerUtil.mutListener.listen(12637) ? (width > 0) : (ListenerUtil.mutListener.listen(12636) ? (width < 0) : (ListenerUtil.mutListener.listen(12635) ? (width != 0) : (width == 0))))))))) {
                                return;
                            }
                        }
                        // works with file URIs only
                        String path = FileUtil.getRealPathFromURI(ThreemaApplication.getAppContext(), mediaItem.getUri());
                        if (!ListenerUtil.mutListener.listen(12643)) {
                            if (path != null) {
                                File f = new File(path);
                                if (!ListenerUtil.mutListener.listen(12642)) {
                                    videoFileSize = f.length();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12650)) {
                            if ((ListenerUtil.mutListener.listen(12648) ? (videoItem.getStartTimeMs() >= 0) : (ListenerUtil.mutListener.listen(12647) ? (videoItem.getStartTimeMs() <= 0) : (ListenerUtil.mutListener.listen(12646) ? (videoItem.getStartTimeMs() > 0) : (ListenerUtil.mutListener.listen(12645) ? (videoItem.getStartTimeMs() != 0) : (ListenerUtil.mutListener.listen(12644) ? (videoItem.getStartTimeMs() == 0) : (videoItem.getStartTimeMs() < 0))))))) {
                                if (!ListenerUtil.mutListener.listen(12649)) {
                                    videoItem.setStartTimeMs(0);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12652)) {
                            if (videoItem.getEndTimeMs() == MediaItem.TIME_UNDEFINED) {
                                if (!ListenerUtil.mutListener.listen(12651)) {
                                    videoItem.setEndTimeMs(duration);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12653)) {
                            videoItem.setDurationMs(duration);
                        }
                        if (!ListenerUtil.mutListener.listen(12654)) {
                            offsetLeft = VideoEditView.this.getTimelinePositionFromVideoPosition(videoItem.getStartTimeMs());
                        }
                        if (!ListenerUtil.mutListener.listen(12659)) {
                            offsetRight = (ListenerUtil.mutListener.listen(12658) ? (timelineGridLayout.getWidth() % VideoEditView.this.getTimelinePositionFromVideoPosition(videoItem.getEndTimeMs())) : (ListenerUtil.mutListener.listen(12657) ? (timelineGridLayout.getWidth() / VideoEditView.this.getTimelinePositionFromVideoPosition(videoItem.getEndTimeMs())) : (ListenerUtil.mutListener.listen(12656) ? (timelineGridLayout.getWidth() * VideoEditView.this.getTimelinePositionFromVideoPosition(videoItem.getEndTimeMs())) : (ListenerUtil.mutListener.listen(12655) ? (timelineGridLayout.getWidth() + VideoEditView.this.getTimelinePositionFromVideoPosition(videoItem.getEndTimeMs())) : (timelineGridLayout.getWidth() - VideoEditView.this.getTimelinePositionFromVideoPosition(videoItem.getEndTimeMs()))))));
                        }
                        if (!ListenerUtil.mutListener.listen(12662)) {
                            RuntimeUtil.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(12661)) {
                                        if (isAttachedToWindow()) {
                                            if (!ListenerUtil.mutListener.listen(12660)) {
                                                updateStartAndEnd();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                        int numColumns = timelineGridLayout.getColumnCount();
                        if (!ListenerUtil.mutListener.listen(12711)) {
                            if (numColumns != GridLayout.UNDEFINED) {
                                int step = (int) ((ListenerUtil.mutListener.listen(12670) ? ((ListenerUtil.mutListener.listen(12666) ? ((float) duration % 1000) : (ListenerUtil.mutListener.listen(12665) ? ((float) duration / 1000) : (ListenerUtil.mutListener.listen(12664) ? ((float) duration - 1000) : (ListenerUtil.mutListener.listen(12663) ? ((float) duration + 1000) : ((float) duration * 1000))))) % numColumns) : (ListenerUtil.mutListener.listen(12669) ? ((ListenerUtil.mutListener.listen(12666) ? ((float) duration % 1000) : (ListenerUtil.mutListener.listen(12665) ? ((float) duration / 1000) : (ListenerUtil.mutListener.listen(12664) ? ((float) duration - 1000) : (ListenerUtil.mutListener.listen(12663) ? ((float) duration + 1000) : ((float) duration * 1000))))) * numColumns) : (ListenerUtil.mutListener.listen(12668) ? ((ListenerUtil.mutListener.listen(12666) ? ((float) duration % 1000) : (ListenerUtil.mutListener.listen(12665) ? ((float) duration / 1000) : (ListenerUtil.mutListener.listen(12664) ? ((float) duration - 1000) : (ListenerUtil.mutListener.listen(12663) ? ((float) duration + 1000) : ((float) duration * 1000))))) - numColumns) : (ListenerUtil.mutListener.listen(12667) ? ((ListenerUtil.mutListener.listen(12666) ? ((float) duration % 1000) : (ListenerUtil.mutListener.listen(12665) ? ((float) duration / 1000) : (ListenerUtil.mutListener.listen(12664) ? ((float) duration - 1000) : (ListenerUtil.mutListener.listen(12663) ? ((float) duration + 1000) : ((float) duration * 1000))))) + numColumns) : ((ListenerUtil.mutListener.listen(12666) ? ((float) duration % 1000) : (ListenerUtil.mutListener.listen(12665) ? ((float) duration / 1000) : (ListenerUtil.mutListener.listen(12664) ? ((float) duration - 1000) : (ListenerUtil.mutListener.listen(12663) ? ((float) duration + 1000) : ((float) duration * 1000))))) / numColumns))))));
                                if (!ListenerUtil.mutListener.listen(12710)) {
                                    {
                                        long _loopCounter118 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(12709) ? (i >= numColumns) : (ListenerUtil.mutListener.listen(12708) ? (i <= numColumns) : (ListenerUtil.mutListener.listen(12707) ? (i > numColumns) : (ListenerUtil.mutListener.listen(12706) ? (i != numColumns) : (ListenerUtil.mutListener.listen(12705) ? (i == numColumns) : (i < numColumns)))))); i++) {
                                            ListenerUtil.loopListener.listen("_loopCounter118", ++_loopCounter118);
                                            int position = (ListenerUtil.mutListener.listen(12674) ? (i % step) : (ListenerUtil.mutListener.listen(12673) ? (i / step) : (ListenerUtil.mutListener.listen(12672) ? (i - step) : (ListenerUtil.mutListener.listen(12671) ? (i + step) : (i * step)))));
                                            if (!ListenerUtil.mutListener.listen(12675)) {
                                                logger.debug("*** frame at position: " + position);
                                            }
                                            Bitmap bitmap = VideoTimelineCache.getInstance().get(mediaItem.getUri(), i);
                                            if (!ListenerUtil.mutListener.listen(12699)) {
                                                if (bitmap == null) {
                                                    if (!ListenerUtil.mutListener.listen(12696)) {
                                                        if ((ListenerUtil.mutListener.listen(12680) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(12679) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(12678) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(12677) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(12676) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1))))))) {
                                                            if (!ListenerUtil.mutListener.listen(12682)) {
                                                                bitmap = metaDataRetriever.getFrameAtTime(position, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(12695)) {
                                                                if ((ListenerUtil.mutListener.listen(12693) ? ((ListenerUtil.mutListener.listen(12687) ? (bitmap.getWidth() >= calculatedWidth) : (ListenerUtil.mutListener.listen(12686) ? (bitmap.getWidth() <= calculatedWidth) : (ListenerUtil.mutListener.listen(12685) ? (bitmap.getWidth() < calculatedWidth) : (ListenerUtil.mutListener.listen(12684) ? (bitmap.getWidth() != calculatedWidth) : (ListenerUtil.mutListener.listen(12683) ? (bitmap.getWidth() == calculatedWidth) : (bitmap.getWidth() > calculatedWidth)))))) && (ListenerUtil.mutListener.listen(12692) ? (bitmap.getHeight() >= targetHeight) : (ListenerUtil.mutListener.listen(12691) ? (bitmap.getHeight() <= targetHeight) : (ListenerUtil.mutListener.listen(12690) ? (bitmap.getHeight() < targetHeight) : (ListenerUtil.mutListener.listen(12689) ? (bitmap.getHeight() != targetHeight) : (ListenerUtil.mutListener.listen(12688) ? (bitmap.getHeight() == targetHeight) : (bitmap.getHeight() > targetHeight))))))) : ((ListenerUtil.mutListener.listen(12687) ? (bitmap.getWidth() >= calculatedWidth) : (ListenerUtil.mutListener.listen(12686) ? (bitmap.getWidth() <= calculatedWidth) : (ListenerUtil.mutListener.listen(12685) ? (bitmap.getWidth() < calculatedWidth) : (ListenerUtil.mutListener.listen(12684) ? (bitmap.getWidth() != calculatedWidth) : (ListenerUtil.mutListener.listen(12683) ? (bitmap.getWidth() == calculatedWidth) : (bitmap.getWidth() > calculatedWidth)))))) || (ListenerUtil.mutListener.listen(12692) ? (bitmap.getHeight() >= targetHeight) : (ListenerUtil.mutListener.listen(12691) ? (bitmap.getHeight() <= targetHeight) : (ListenerUtil.mutListener.listen(12690) ? (bitmap.getHeight() < targetHeight) : (ListenerUtil.mutListener.listen(12689) ? (bitmap.getHeight() != targetHeight) : (ListenerUtil.mutListener.listen(12688) ? (bitmap.getHeight() == targetHeight) : (bitmap.getHeight() > targetHeight))))))))) {
                                                                    if (!ListenerUtil.mutListener.listen(12694)) {
                                                                        bitmap = BitmapUtil.resizeBitmap(bitmap, calculatedWidth, targetHeight);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(12681)) {
                                                                bitmap = metaDataRetriever.getScaledFrameAtTime(position, MediaMetadataRetriever.OPTION_CLOSEST_SYNC, calculatedWidth, targetHeight);
                                                            }
                                                        }
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(12697)) {
                                                        VideoTimelineCache.getInstance().set(mediaItem.getUri(), i, bitmap);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(12698)) {
                                                        logger.debug("*** bitmap width: " + bitmap.getWidth() + " height: " + bitmap.getHeight());
                                                    }
                                                }
                                            }
                                            final int column = i;
                                            Bitmap finalBitmap = bitmap;
                                            if (!ListenerUtil.mutListener.listen(12704)) {
                                                if (Thread.interrupted()) {
                                                    throw new InterruptedException();
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(12703)) {
                                                        RuntimeUtil.runOnUiThread(new Runnable() {

                                                            @Override
                                                            public void run() {
                                                                if (!ListenerUtil.mutListener.listen(12702)) {
                                                                    if (isAttachedToWindow()) {
                                                                        ImageView imageView = findViewWithTag(column);
                                                                        if (!ListenerUtil.mutListener.listen(12701)) {
                                                                            if (imageView != null) {
                                                                                if (!ListenerUtil.mutListener.listen(12700)) {
                                                                                    imageView.setImageBitmap(finalBitmap);
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
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(12615)) {
                            if (!(e instanceof InterruptedException)) {
                                if (!ListenerUtil.mutListener.listen(12614)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    } finally {
                        if (!ListenerUtil.mutListener.listen(12616)) {
                            metaDataRetriever.release();
                        }
                    }
                }
            }, THUMBNAIL_THREAD_NAME);
        }
        if (!ListenerUtil.mutListener.listen(12717)) {
            if ((ListenerUtil.mutListener.listen(12713) ? (isAttachedToWindow() || context != null) : (isAttachedToWindow() && context != null))) {
                if (!ListenerUtil.mutListener.listen(12714)) {
                    thumbnailThread.start();
                }
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getString(R.string.app_name)));
                if (!ListenerUtil.mutListener.listen(12715)) {
                    videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(videoItem.getUri());
                }
                if (!ListenerUtil.mutListener.listen(12716)) {
                    preparePlayer();
                }
            }
        }
    }

    private void preparePlayer() {
        if (!ListenerUtil.mutListener.listen(12739)) {
            if ((ListenerUtil.mutListener.listen(12718) ? (videoPlayer != null || videoSource != null) : (videoPlayer != null && videoSource != null))) {
                long endPosition = ((ListenerUtil.mutListener.listen(12720) ? ((ListenerUtil.mutListener.listen(12719) ? (videoItem.getEndTimeMs() == videoItem.getDurationMs() && videoItem.getEndTimeMs() == 0) : (videoItem.getEndTimeMs() == videoItem.getDurationMs() || videoItem.getEndTimeMs() == 0)) && videoItem.getEndTimeMs() == MediaItem.TIME_UNDEFINED) : ((ListenerUtil.mutListener.listen(12719) ? (videoItem.getEndTimeMs() == videoItem.getDurationMs() && videoItem.getEndTimeMs() == 0) : (videoItem.getEndTimeMs() == videoItem.getDurationMs() || videoItem.getEndTimeMs() == 0)) || videoItem.getEndTimeMs() == MediaItem.TIME_UNDEFINED))) ? TIME_END_OF_SOURCE : (ListenerUtil.mutListener.listen(12724) ? (videoItem.getEndTimeMs() % 1000) : (ListenerUtil.mutListener.listen(12723) ? (videoItem.getEndTimeMs() / 1000) : (ListenerUtil.mutListener.listen(12722) ? (videoItem.getEndTimeMs() - 1000) : (ListenerUtil.mutListener.listen(12721) ? (videoItem.getEndTimeMs() + 1000) : (videoItem.getEndTimeMs() * 1000)))));
                if (!ListenerUtil.mutListener.listen(12729)) {
                    logger.debug("startPosition: " + ((ListenerUtil.mutListener.listen(12728) ? (videoItem.getStartTimeMs() % 1000) : (ListenerUtil.mutListener.listen(12727) ? (videoItem.getStartTimeMs() / 1000) : (ListenerUtil.mutListener.listen(12726) ? (videoItem.getStartTimeMs() - 1000) : (ListenerUtil.mutListener.listen(12725) ? (videoItem.getStartTimeMs() + 1000) : (videoItem.getStartTimeMs() * 1000)))))) + " endPosition: " + endPosition);
                }
                ClippingMediaSource clippingSource = new ClippingMediaSource(videoSource, (ListenerUtil.mutListener.listen(12733) ? (videoItem.getStartTimeMs() % 1000) : (ListenerUtil.mutListener.listen(12732) ? (videoItem.getStartTimeMs() / 1000) : (ListenerUtil.mutListener.listen(12731) ? (videoItem.getStartTimeMs() - 1000) : (ListenerUtil.mutListener.listen(12730) ? (videoItem.getStartTimeMs() + 1000) : (videoItem.getStartTimeMs() * 1000))))), endPosition);
                if (!ListenerUtil.mutListener.listen(12736)) {
                    if ((ListenerUtil.mutListener.listen(12734) ? (videoPlayer.isLoading() && videoPlayer.isPlaying()) : (videoPlayer.isLoading() || videoPlayer.isPlaying()))) {
                        if (!ListenerUtil.mutListener.listen(12735)) {
                            videoPlayer.stop(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12737)) {
                    videoPlayer.setPlayWhenReady(false);
                }
                if (!ListenerUtil.mutListener.listen(12738)) {
                    videoPlayer.prepare(clippingSource);
                }
            }
        }
    }

    @MainThread
    private int calculateNumColumns() {
        if (!ListenerUtil.mutListener.listen(12763)) {
            if (context != null) {
                int timelineMargin = context.getResources().getDimensionPixelSize(R.dimen.video_timeline_marginLeftRight);
                int timelineWidth = (ListenerUtil.mutListener.listen(12747) ? (getWidth() % ((ListenerUtil.mutListener.listen(12743) ? (2 % timelineMargin) : (ListenerUtil.mutListener.listen(12742) ? (2 / timelineMargin) : (ListenerUtil.mutListener.listen(12741) ? (2 - timelineMargin) : (ListenerUtil.mutListener.listen(12740) ? (2 + timelineMargin) : (2 * timelineMargin))))))) : (ListenerUtil.mutListener.listen(12746) ? (getWidth() / ((ListenerUtil.mutListener.listen(12743) ? (2 % timelineMargin) : (ListenerUtil.mutListener.listen(12742) ? (2 / timelineMargin) : (ListenerUtil.mutListener.listen(12741) ? (2 - timelineMargin) : (ListenerUtil.mutListener.listen(12740) ? (2 + timelineMargin) : (2 * timelineMargin))))))) : (ListenerUtil.mutListener.listen(12745) ? (getWidth() * ((ListenerUtil.mutListener.listen(12743) ? (2 % timelineMargin) : (ListenerUtil.mutListener.listen(12742) ? (2 / timelineMargin) : (ListenerUtil.mutListener.listen(12741) ? (2 - timelineMargin) : (ListenerUtil.mutListener.listen(12740) ? (2 + timelineMargin) : (2 * timelineMargin))))))) : (ListenerUtil.mutListener.listen(12744) ? (getWidth() + ((ListenerUtil.mutListener.listen(12743) ? (2 % timelineMargin) : (ListenerUtil.mutListener.listen(12742) ? (2 / timelineMargin) : (ListenerUtil.mutListener.listen(12741) ? (2 - timelineMargin) : (ListenerUtil.mutListener.listen(12740) ? (2 + timelineMargin) : (2 * timelineMargin))))))) : (getWidth() - ((ListenerUtil.mutListener.listen(12743) ? (2 % timelineMargin) : (ListenerUtil.mutListener.listen(12742) ? (2 / timelineMargin) : (ListenerUtil.mutListener.listen(12741) ? (2 - timelineMargin) : (ListenerUtil.mutListener.listen(12740) ? (2 + timelineMargin) : (2 * timelineMargin)))))))))));
                int approximateColumns = (ListenerUtil.mutListener.listen(12751) ? (timelineWidth % targetHeight) : (ListenerUtil.mutListener.listen(12750) ? (timelineWidth * targetHeight) : (ListenerUtil.mutListener.listen(12749) ? (timelineWidth - targetHeight) : (ListenerUtil.mutListener.listen(12748) ? (timelineWidth + targetHeight) : (timelineWidth / targetHeight)))));
                if (!ListenerUtil.mutListener.listen(12762)) {
                    if ((ListenerUtil.mutListener.listen(12756) ? (approximateColumns >= 0) : (ListenerUtil.mutListener.listen(12755) ? (approximateColumns <= 0) : (ListenerUtil.mutListener.listen(12754) ? (approximateColumns < 0) : (ListenerUtil.mutListener.listen(12753) ? (approximateColumns != 0) : (ListenerUtil.mutListener.listen(12752) ? (approximateColumns == 0) : (approximateColumns > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(12761)) {
                            calculatedWidth = (ListenerUtil.mutListener.listen(12760) ? (timelineWidth % approximateColumns) : (ListenerUtil.mutListener.listen(12759) ? (timelineWidth * approximateColumns) : (ListenerUtil.mutListener.listen(12758) ? (timelineWidth - approximateColumns) : (ListenerUtil.mutListener.listen(12757) ? (timelineWidth + approximateColumns) : (timelineWidth / approximateColumns)))));
                        }
                        return approximateColumns;
                    }
                }
            }
        }
        return 0;
    }

    @MainThread
    private void updateStartAndEnd() {
        if (!ListenerUtil.mutListener.listen(12764)) {
            startTimeTextView.setText(LocaleUtil.formatTimerText(videoItem.getStartTimeMs(), true));
        }
        if (!ListenerUtil.mutListener.listen(12765)) {
            endTimeTextView.setText(LocaleUtil.formatTimerText(videoItem.getEndTimeMs(), true));
        }
        if (!ListenerUtil.mutListener.listen(12784)) {
            if ((ListenerUtil.mutListener.listen(12770) ? (videoFileSize >= 0L) : (ListenerUtil.mutListener.listen(12769) ? (videoFileSize <= 0L) : (ListenerUtil.mutListener.listen(12768) ? (videoFileSize < 0L) : (ListenerUtil.mutListener.listen(12767) ? (videoFileSize != 0L) : (ListenerUtil.mutListener.listen(12766) ? (videoFileSize == 0L) : (videoFileSize > 0L))))))) {
                long croppedDurationMs = (ListenerUtil.mutListener.listen(12774) ? (videoItem.getEndTimeMs() % videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12773) ? (videoItem.getEndTimeMs() / videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12772) ? (videoItem.getEndTimeMs() * videoItem.getStartTimeMs()) : (ListenerUtil.mutListener.listen(12771) ? (videoItem.getEndTimeMs() + videoItem.getStartTimeMs()) : (videoItem.getEndTimeMs() - videoItem.getStartTimeMs())))));
                long size = (ListenerUtil.mutListener.listen(12782) ? ((ListenerUtil.mutListener.listen(12778) ? (videoFileSize % croppedDurationMs) : (ListenerUtil.mutListener.listen(12777) ? (videoFileSize / croppedDurationMs) : (ListenerUtil.mutListener.listen(12776) ? (videoFileSize - croppedDurationMs) : (ListenerUtil.mutListener.listen(12775) ? (videoFileSize + croppedDurationMs) : (videoFileSize * croppedDurationMs))))) % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12781) ? ((ListenerUtil.mutListener.listen(12778) ? (videoFileSize % croppedDurationMs) : (ListenerUtil.mutListener.listen(12777) ? (videoFileSize / croppedDurationMs) : (ListenerUtil.mutListener.listen(12776) ? (videoFileSize - croppedDurationMs) : (ListenerUtil.mutListener.listen(12775) ? (videoFileSize + croppedDurationMs) : (videoFileSize * croppedDurationMs))))) * videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12780) ? ((ListenerUtil.mutListener.listen(12778) ? (videoFileSize % croppedDurationMs) : (ListenerUtil.mutListener.listen(12777) ? (videoFileSize / croppedDurationMs) : (ListenerUtil.mutListener.listen(12776) ? (videoFileSize - croppedDurationMs) : (ListenerUtil.mutListener.listen(12775) ? (videoFileSize + croppedDurationMs) : (videoFileSize * croppedDurationMs))))) - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12779) ? ((ListenerUtil.mutListener.listen(12778) ? (videoFileSize % croppedDurationMs) : (ListenerUtil.mutListener.listen(12777) ? (videoFileSize / croppedDurationMs) : (ListenerUtil.mutListener.listen(12776) ? (videoFileSize - croppedDurationMs) : (ListenerUtil.mutListener.listen(12775) ? (videoFileSize + croppedDurationMs) : (videoFileSize * croppedDurationMs))))) + videoItem.getDurationMs()) : ((ListenerUtil.mutListener.listen(12778) ? (videoFileSize % croppedDurationMs) : (ListenerUtil.mutListener.listen(12777) ? (videoFileSize / croppedDurationMs) : (ListenerUtil.mutListener.listen(12776) ? (videoFileSize - croppedDurationMs) : (ListenerUtil.mutListener.listen(12775) ? (videoFileSize + croppedDurationMs) : (videoFileSize * croppedDurationMs))))) / videoItem.getDurationMs())))));
                if (!ListenerUtil.mutListener.listen(12783)) {
                    sizeTextView.setText(Formatter.formatFileSize(context, size));
                }
            }
        }
    }

    @MainThread
    private void updateProgressBar() {
        if (!ListenerUtil.mutListener.listen(12785)) {
            videoCurrentPosition = videoPlayer == null ? 0 : videoPlayer.getCurrentPosition() + videoItem.getStartTimeMs();
        }
        if (!ListenerUtil.mutListener.listen(12786)) {
            invalidate();
        }
        if (!ListenerUtil.mutListener.listen(12787)) {
            // Remove scheduled updates.
            progressHandler.removeCallbacks(updateProgressAction);
        }
        // Schedule an update if necessary.
        int playbackState = videoPlayer == null ? Player.STATE_IDLE : videoPlayer.getPlaybackState();
        if (!ListenerUtil.mutListener.listen(12794)) {
            if ((ListenerUtil.mutListener.listen(12788) ? (playbackState != Player.STATE_IDLE || playbackState != Player.STATE_ENDED) : (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED))) {
                long delayMs;
                if ((ListenerUtil.mutListener.listen(12792) ? ((ListenerUtil.mutListener.listen(12791) ? (videoPlayer != null || videoPlayer.getPlayWhenReady()) : (videoPlayer != null && videoPlayer.getPlayWhenReady())) || playbackState == Player.STATE_READY) : ((ListenerUtil.mutListener.listen(12791) ? (videoPlayer != null || videoPlayer.getPlayWhenReady()) : (videoPlayer != null && videoPlayer.getPlayWhenReady())) && playbackState == Player.STATE_READY))) {
                    delayMs = 100;
                } else {
                    delayMs = 1000;
                }
                if (!ListenerUtil.mutListener.listen(12793)) {
                    progressHandler.postDelayed(updateProgressAction, delayMs);
                }
            } else if (playbackState != Player.STATE_ENDED) {
                if (!ListenerUtil.mutListener.listen(12789)) {
                    videoCurrentPosition = 0;
                }
                if (!ListenerUtil.mutListener.listen(12790)) {
                    invalidate();
                }
            }
        }
    }

    private final Runnable updateProgressAction = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(12795)) {
                updateProgressBar();
            }
        }
    };

    private long getVideoPositionFromTimelinePosition(int timelinePosition) {
        if (!ListenerUtil.mutListener.listen(12804)) {
            if (this.timelineGridLayout.getWidth() != 0) {
                return (ListenerUtil.mutListener.listen(12803) ? ((ListenerUtil.mutListener.listen(12799) ? (timelinePosition % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12798) ? (timelinePosition / videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12797) ? (timelinePosition - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12796) ? (timelinePosition + videoItem.getDurationMs()) : (timelinePosition * videoItem.getDurationMs()))))) % this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12802) ? ((ListenerUtil.mutListener.listen(12799) ? (timelinePosition % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12798) ? (timelinePosition / videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12797) ? (timelinePosition - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12796) ? (timelinePosition + videoItem.getDurationMs()) : (timelinePosition * videoItem.getDurationMs()))))) * this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12801) ? ((ListenerUtil.mutListener.listen(12799) ? (timelinePosition % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12798) ? (timelinePosition / videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12797) ? (timelinePosition - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12796) ? (timelinePosition + videoItem.getDurationMs()) : (timelinePosition * videoItem.getDurationMs()))))) - this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12800) ? ((ListenerUtil.mutListener.listen(12799) ? (timelinePosition % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12798) ? (timelinePosition / videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12797) ? (timelinePosition - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12796) ? (timelinePosition + videoItem.getDurationMs()) : (timelinePosition * videoItem.getDurationMs()))))) + this.timelineGridLayout.getWidth()) : ((ListenerUtil.mutListener.listen(12799) ? (timelinePosition % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12798) ? (timelinePosition / videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12797) ? (timelinePosition - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12796) ? (timelinePosition + videoItem.getDurationMs()) : (timelinePosition * videoItem.getDurationMs()))))) / this.timelineGridLayout.getWidth())))));
            }
        }
        return 0;
    }

    private int getTimelinePositionFromVideoPosition(long videoCurrentPosition) {
        if (!ListenerUtil.mutListener.listen(12813)) {
            if (this.timelineGridLayout.getWidth() != 0) {
                return (int) ((ListenerUtil.mutListener.listen(12812) ? ((ListenerUtil.mutListener.listen(12808) ? (videoCurrentPosition % this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12807) ? (videoCurrentPosition / this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12806) ? (videoCurrentPosition - this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12805) ? (videoCurrentPosition + this.timelineGridLayout.getWidth()) : (videoCurrentPosition * this.timelineGridLayout.getWidth()))))) % videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12811) ? ((ListenerUtil.mutListener.listen(12808) ? (videoCurrentPosition % this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12807) ? (videoCurrentPosition / this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12806) ? (videoCurrentPosition - this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12805) ? (videoCurrentPosition + this.timelineGridLayout.getWidth()) : (videoCurrentPosition * this.timelineGridLayout.getWidth()))))) * videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12810) ? ((ListenerUtil.mutListener.listen(12808) ? (videoCurrentPosition % this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12807) ? (videoCurrentPosition / this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12806) ? (videoCurrentPosition - this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12805) ? (videoCurrentPosition + this.timelineGridLayout.getWidth()) : (videoCurrentPosition * this.timelineGridLayout.getWidth()))))) - videoItem.getDurationMs()) : (ListenerUtil.mutListener.listen(12809) ? ((ListenerUtil.mutListener.listen(12808) ? (videoCurrentPosition % this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12807) ? (videoCurrentPosition / this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12806) ? (videoCurrentPosition - this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12805) ? (videoCurrentPosition + this.timelineGridLayout.getWidth()) : (videoCurrentPosition * this.timelineGridLayout.getWidth()))))) + videoItem.getDurationMs()) : ((ListenerUtil.mutListener.listen(12808) ? (videoCurrentPosition % this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12807) ? (videoCurrentPosition / this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12806) ? (videoCurrentPosition - this.timelineGridLayout.getWidth()) : (ListenerUtil.mutListener.listen(12805) ? (videoCurrentPosition + this.timelineGridLayout.getWidth()) : (videoCurrentPosition * this.timelineGridLayout.getWidth()))))) / videoItem.getDurationMs()))))));
            }
        }
        return 0;
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(12816)) {
            if ((ListenerUtil.mutListener.listen(12814) ? (thumbnailThread != null || thumbnailThread.isAlive()) : (thumbnailThread != null && thumbnailThread.isAlive()))) {
                if (!ListenerUtil.mutListener.listen(12815)) {
                    thumbnailThread.interrupt();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12819)) {
            if (videoView != null) {
                if (!ListenerUtil.mutListener.listen(12818)) {
                    if (videoView.getPlayer() != null) {
                        if (!ListenerUtil.mutListener.listen(12817)) {
                            videoView.setPlayer(null);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12822)) {
            if (videoPlayer != null) {
                if (!ListenerUtil.mutListener.listen(12820)) {
                    videoPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(12821)) {
                    videoPlayer.release();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12823)) {
            this.context = null;
        }
    }
}
