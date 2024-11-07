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

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.LayoutRes;
import ch.threema.app.R;
import ch.threema.app.mediaattacher.MediaAttachItem;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.LocaleUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoPopup extends DimmingPopupWindow {

    private static final Logger logger = LoggerFactory.getLogger(ImagePopup.class);

    private Context context;

    private PlayerView videoView;

    private TextView filenameTextView, dateTextView;

    private View topLayout;

    private View parentView;

    private ContentResolver contentResolver;

    private SimpleExoPlayer player;

    private int screenWidth;

    private int screenHeight;

    private int borderSize;

    final int[] location = new int[2];

    public VideoPopup(Context context, View parentView, int screenWidth, int screenHeight) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47753)) {
            init(context, parentView, screenWidth, screenHeight, 0, 0);
        }
    }

    public VideoPopup(Context context, View parentView, int screenWidth, int screenHeight, int innerBorder) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47754)) {
            init(context, parentView, screenWidth, screenHeight, innerBorder, 0);
        }
    }

    public VideoPopup(Context context, View parentView, int screenWidth, int screenHeight, int innerBorder, @LayoutRes int layout) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47755)) {
            init(context, parentView, screenWidth, screenHeight, innerBorder, layout);
        }
    }

    private void init(Context context, View parentView, int screenWidth, int screenHeight, int innerBorder, @LayoutRes int layout) {
        if (!ListenerUtil.mutListener.listen(47756)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(47757)) {
            this.parentView = parentView;
        }
        if (!ListenerUtil.mutListener.listen(47758)) {
            this.contentResolver = context.getContentResolver();
        }
        if (!ListenerUtil.mutListener.listen(47759)) {
            this.screenHeight = screenHeight;
        }
        if (!ListenerUtil.mutListener.listen(47760)) {
            this.screenWidth = screenWidth;
        }
        if (!ListenerUtil.mutListener.listen(47761)) {
            this.borderSize = context.getResources().getDimensionPixelSize(R.dimen.image_popup_screen_border_width);
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(47769)) {
            if ((ListenerUtil.mutListener.listen(47766) ? (layout >= 0) : (ListenerUtil.mutListener.listen(47765) ? (layout <= 0) : (ListenerUtil.mutListener.listen(47764) ? (layout > 0) : (ListenerUtil.mutListener.listen(47763) ? (layout < 0) : (ListenerUtil.mutListener.listen(47762) ? (layout != 0) : (layout == 0))))))) {
                if (!ListenerUtil.mutListener.listen(47768)) {
                    topLayout = layoutInflater.inflate(R.layout.fragment_video_preview, null, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47767)) {
                    topLayout = layoutInflater.inflate(layout, null, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47770)) {
            this.videoView = topLayout.findViewById(R.id.video_view);
        }
        if (!ListenerUtil.mutListener.listen(47771)) {
            this.filenameTextView = topLayout.findViewById(R.id.filename_view);
        }
        if (!ListenerUtil.mutListener.listen(47772)) {
            this.dateTextView = topLayout.findViewById(R.id.date_view);
        }
        if (!ListenerUtil.mutListener.listen(47773)) {
            setContentView(topLayout);
        }
        if (!ListenerUtil.mutListener.listen(47792)) {
            if ((ListenerUtil.mutListener.listen(47778) ? (innerBorder >= 0) : (ListenerUtil.mutListener.listen(47777) ? (innerBorder <= 0) : (ListenerUtil.mutListener.listen(47776) ? (innerBorder > 0) : (ListenerUtil.mutListener.listen(47775) ? (innerBorder < 0) : (ListenerUtil.mutListener.listen(47774) ? (innerBorder == 0) : (innerBorder != 0))))))) {
                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) videoView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(47779)) {
                    marginParams.setMargins(innerBorder, innerBorder, innerBorder, innerBorder);
                }
                if (!ListenerUtil.mutListener.listen(47780)) {
                    videoView.setLayoutParams(marginParams);
                }
                if (!ListenerUtil.mutListener.listen(47781)) {
                    marginParams = (ViewGroup.MarginLayoutParams) filenameTextView.getLayoutParams();
                }
                if (!ListenerUtil.mutListener.listen(47790)) {
                    marginParams.setMargins(innerBorder, (ListenerUtil.mutListener.listen(47789) ? ((ListenerUtil.mutListener.listen(47785) ? (innerBorder % context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47784) ? (innerBorder / context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47783) ? (innerBorder * context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47782) ? (innerBorder + context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (innerBorder - context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)))))) % context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_margin_bottom)) : (ListenerUtil.mutListener.listen(47788) ? ((ListenerUtil.mutListener.listen(47785) ? (innerBorder % context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47784) ? (innerBorder / context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47783) ? (innerBorder * context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47782) ? (innerBorder + context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (innerBorder - context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)))))) / context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_margin_bottom)) : (ListenerUtil.mutListener.listen(47787) ? ((ListenerUtil.mutListener.listen(47785) ? (innerBorder % context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47784) ? (innerBorder / context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47783) ? (innerBorder * context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47782) ? (innerBorder + context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (innerBorder - context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)))))) * context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_margin_bottom)) : (ListenerUtil.mutListener.listen(47786) ? ((ListenerUtil.mutListener.listen(47785) ? (innerBorder % context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47784) ? (innerBorder / context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47783) ? (innerBorder * context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47782) ? (innerBorder + context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (innerBorder - context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)))))) + context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_margin_bottom)) : ((ListenerUtil.mutListener.listen(47785) ? (innerBorder % context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47784) ? (innerBorder / context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47783) ? (innerBorder * context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (ListenerUtil.mutListener.listen(47782) ? (innerBorder + context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)) : (innerBorder - context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_size)))))) - context.getResources().getDimensionPixelSize(R.dimen.image_popup_text_margin_bottom)))))), 0, 0);
                }
                if (!ListenerUtil.mutListener.listen(47791)) {
                    filenameTextView.setLayoutParams(marginParams);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47810)) {
            if ((ListenerUtil.mutListener.listen(47797) ? (screenHeight >= screenWidth) : (ListenerUtil.mutListener.listen(47796) ? (screenHeight <= screenWidth) : (ListenerUtil.mutListener.listen(47795) ? (screenHeight < screenWidth) : (ListenerUtil.mutListener.listen(47794) ? (screenHeight != screenWidth) : (ListenerUtil.mutListener.listen(47793) ? (screenHeight == screenWidth) : (screenHeight > screenWidth))))))) {
                if (!ListenerUtil.mutListener.listen(47808)) {
                    // portrait
                    setWidth((ListenerUtil.mutListener.listen(47807) ? (screenWidth % borderSize) : (ListenerUtil.mutListener.listen(47806) ? (screenWidth / borderSize) : (ListenerUtil.mutListener.listen(47805) ? (screenWidth * borderSize) : (ListenerUtil.mutListener.listen(47804) ? (screenWidth + borderSize) : (screenWidth - borderSize))))));
                }
                if (!ListenerUtil.mutListener.listen(47809)) {
                    setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47798)) {
                    // landscape
                    setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
                }
                if (!ListenerUtil.mutListener.listen(47803)) {
                    setHeight((ListenerUtil.mutListener.listen(47802) ? (screenHeight % borderSize) : (ListenerUtil.mutListener.listen(47801) ? (screenHeight / borderSize) : (ListenerUtil.mutListener.listen(47800) ? (screenHeight * borderSize) : (ListenerUtil.mutListener.listen(47799) ? (screenHeight + borderSize) : (screenHeight - borderSize))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47811)) {
            setBackgroundDrawable(new BitmapDrawable());
        }
        if (!ListenerUtil.mutListener.listen(47812)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(47819)) {
            if ((ListenerUtil.mutListener.listen(47817) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(47816) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(47815) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(47814) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(47813) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(47818)) {
                    setElevation(10);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47820)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        }
    }

    public void show(final View view, final MediaAttachItem mediaAttachItem) {
        if (!ListenerUtil.mutListener.listen(47821)) {
            this.filenameTextView.setText(mediaAttachItem.getBucketName() + "/" + mediaAttachItem.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(47822)) {
            this.dateTextView.setText(LocaleUtil.formatTimeStampString(context, mediaAttachItem.getDateTaken(), false));
        }
        if (!ListenerUtil.mutListener.listen(47832)) {
            getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(47823)) {
                        getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(47829)) {
                        if (mediaAttachItem != null) {
                            if (!ListenerUtil.mutListener.listen(47824)) {
                                logger.debug(("mediaAttachItem orientation is " + mediaAttachItem.getOrientation()));
                            }
                            if (!ListenerUtil.mutListener.listen(47825)) {
                                player = new SimpleExoPlayer.Builder(getContext()).build();
                            }
                            if (!ListenerUtil.mutListener.listen(47826)) {
                                videoView.setPlayer(player);
                            }
                            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "threema"));
                            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaAttachItem.getUri());
                            if (!ListenerUtil.mutListener.listen(47827)) {
                                player.setPlayWhenReady(true);
                            }
                            if (!ListenerUtil.mutListener.listen(47828)) {
                                player.prepare(videoSource);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(47830)) {
                        AnimationUtil.getViewCenter(view, getContentView(), location);
                    }
                    if (!ListenerUtil.mutListener.listen(47831)) {
                        AnimationUtil.popupAnimateIn(getContentView());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(47833)) {
            showAtLocation(parentView, Gravity.CENTER, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(47834)) {
            dimBackground();
        }
        if (!ListenerUtil.mutListener.listen(47836)) {
            topLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(47835)) {
                        dismiss();
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(47841)) {
            AnimationUtil.popupAnimateOut(getContentView(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(47837)) {
                        VideoPopup.super.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(47840)) {
                        if (player != null) {
                            if (!ListenerUtil.mutListener.listen(47838)) {
                                player.release();
                            }
                            if (!ListenerUtil.mutListener.listen(47839)) {
                                player = null;
                            }
                        }
                    }
                }
            });
        }
    }
}
