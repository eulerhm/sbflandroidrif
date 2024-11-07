/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SendButton extends FrameLayout {

    private static final int STATE_SEND = 1;

    private static final int STATE_RECORD = 2;

    private static final int TRANSITION_DURATION_MS = 150;

    private Drawable backgroundEnabled, backgroundDisabled;

    private Context context;

    private AppCompatImageView icon;

    private TransitionDrawable transitionDrawable;

    private int currentState;

    private final Object currentStateLock = new Object();

    public SendButton(Context context) {
        this(context, null);
    }

    public SendButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(47063)) {
            init(context, attrs);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(47064)) {
            inflater.inflate(R.layout.send_button, this);
        }
        if (!ListenerUtil.mutListener.listen(47065)) {
            this.context = context;
        }
        int theme = ConfigUtils.getAppTheme(context);
        if (!ListenerUtil.mutListener.listen(47069)) {
            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SendButton, 0, 0);
                if (!ListenerUtil.mutListener.listen(47068)) {
                    if (a != null) {
                        if (!ListenerUtil.mutListener.listen(47066)) {
                            theme = a.getInt(R.styleable.SendButton_buttonTheme, theme);
                        }
                        if (!ListenerUtil.mutListener.listen(47067)) {
                            a.recycle();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47074)) {
            if (theme == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(47072)) {
                    this.backgroundEnabled = context.getResources().getDrawable(R.drawable.ic_circle_send_dark);
                }
                if (!ListenerUtil.mutListener.listen(47073)) {
                    this.backgroundDisabled = context.getResources().getDrawable(R.drawable.ic_circle_send_disabled);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47070)) {
                    this.backgroundEnabled = context.getResources().getDrawable(R.drawable.ic_circle_send_light);
                }
                if (!ListenerUtil.mutListener.listen(47071)) {
                    this.backgroundDisabled = context.getResources().getDrawable(R.drawable.ic_circle_send_disabled);
                }
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        if (!ListenerUtil.mutListener.listen(47075)) {
            super.onFinishInflate();
        }
        if (!ListenerUtil.mutListener.listen(47076)) {
            this.icon = this.findViewById(R.id.icon);
        }
        if (!ListenerUtil.mutListener.listen(47077)) {
            this.transitionDrawable = (TransitionDrawable) ContextCompat.getDrawable(getContext(), R.drawable.transition_send_button);
        }
        if (!ListenerUtil.mutListener.listen(47078)) {
            this.transitionDrawable.setCrossFadeEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(47079)) {
            this.icon.setImageDrawable(this.transitionDrawable);
        }
        synchronized (currentStateLock) {
            if (!ListenerUtil.mutListener.listen(47080)) {
                this.transitionDrawable.resetTransition();
            }
            if (!ListenerUtil.mutListener.listen(47081)) {
                currentState = STATE_SEND;
            }
        }
    }

    public void setSend() {
        synchronized (currentStateLock) {
            if (!ListenerUtil.mutListener.listen(47090)) {
                if ((ListenerUtil.mutListener.listen(47086) ? (currentState >= STATE_SEND) : (ListenerUtil.mutListener.listen(47085) ? (currentState <= STATE_SEND) : (ListenerUtil.mutListener.listen(47084) ? (currentState > STATE_SEND) : (ListenerUtil.mutListener.listen(47083) ? (currentState < STATE_SEND) : (ListenerUtil.mutListener.listen(47082) ? (currentState == STATE_SEND) : (currentState != STATE_SEND))))))) {
                    if (!ListenerUtil.mutListener.listen(47087)) {
                        this.transitionDrawable.reverseTransition(TRANSITION_DURATION_MS);
                    }
                    if (!ListenerUtil.mutListener.listen(47088)) {
                        setContentDescription(this.context.getString(R.string.send));
                    }
                    if (!ListenerUtil.mutListener.listen(47089)) {
                        currentState = STATE_SEND;
                    }
                }
            }
        }
    }

    public void setRecord() {
        synchronized (currentStateLock) {
            if (!ListenerUtil.mutListener.listen(47099)) {
                if ((ListenerUtil.mutListener.listen(47095) ? (currentState >= STATE_RECORD) : (ListenerUtil.mutListener.listen(47094) ? (currentState <= STATE_RECORD) : (ListenerUtil.mutListener.listen(47093) ? (currentState > STATE_RECORD) : (ListenerUtil.mutListener.listen(47092) ? (currentState < STATE_RECORD) : (ListenerUtil.mutListener.listen(47091) ? (currentState == STATE_RECORD) : (currentState != STATE_RECORD))))))) {
                    if (!ListenerUtil.mutListener.listen(47096)) {
                        this.transitionDrawable.startTransition(TRANSITION_DURATION_MS);
                    }
                    if (!ListenerUtil.mutListener.listen(47097)) {
                        setContentDescription(this.context.getString(R.string.voice_message_record));
                    }
                    if (!ListenerUtil.mutListener.listen(47098)) {
                        currentState = STATE_RECORD;
                    }
                }
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(47100)) {
            super.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(47101)) {
            setBackground(enabled ? this.backgroundEnabled : this.backgroundDisabled);
        }
        if (!ListenerUtil.mutListener.listen(47103)) {
            if (!enabled) {
                if (!ListenerUtil.mutListener.listen(47102)) {
                    setSend();
                }
            }
        }
    }
}
