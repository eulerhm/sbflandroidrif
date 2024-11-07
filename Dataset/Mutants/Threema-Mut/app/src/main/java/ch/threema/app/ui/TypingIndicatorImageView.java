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
package ch.threema.app.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TypingIndicatorImageView extends androidx.appcompat.widget.AppCompatImageView {

    private AnimatedVectorDrawableCompat animatedVectorDrawableCompat;

    public TypingIndicatorImageView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47701)) {
            init();
        }
    }

    public TypingIndicatorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(47702)) {
            init();
        }
    }

    public TypingIndicatorImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(47703)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(47704)) {
            animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.typing_indicator);
        }
        if (!ListenerUtil.mutListener.listen(47705)) {
            animatedVectorDrawableCompat.setTint(ConfigUtils.getAppTheme(getContext()) == ConfigUtils.THEME_DARK ? Color.WHITE : Color.BLACK);
        }
        if (!ListenerUtil.mutListener.listen(47706)) {
            setImageDrawable(animatedVectorDrawableCompat);
        }
    }

    private void startAnimation() {
        if (!ListenerUtil.mutListener.listen(47710)) {
            if (animatedVectorDrawableCompat != null) {
                if (!ListenerUtil.mutListener.listen(47708)) {
                    animatedVectorDrawableCompat.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {

                        @Override
                        public void onAnimationEnd(Drawable drawable) {
                            if (!ListenerUtil.mutListener.listen(47707)) {
                                post(() -> animatedVectorDrawableCompat.start());
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(47709)) {
                    animatedVectorDrawableCompat.start();
                }
            }
        }
    }

    private void stopAnimation() {
        if (!ListenerUtil.mutListener.listen(47713)) {
            if (animatedVectorDrawableCompat != null) {
                if (!ListenerUtil.mutListener.listen(47711)) {
                    animatedVectorDrawableCompat.clearAnimationCallbacks();
                }
                if (!ListenerUtil.mutListener.listen(47712)) {
                    animatedVectorDrawableCompat.stop();
                }
            }
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        if (!ListenerUtil.mutListener.listen(47714)) {
            super.onVisibilityChanged(changedView, visibility);
        }
        if (!ListenerUtil.mutListener.listen(47717)) {
            if (visibility == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(47716)) {
                    post(this::startAnimation);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47715)) {
                    post(this::stopAnimation);
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(47718)) {
            post(this::stopAnimation);
        }
        if (!ListenerUtil.mutListener.listen(47719)) {
            super.onDetachedFromWindow();
        }
    }
}
