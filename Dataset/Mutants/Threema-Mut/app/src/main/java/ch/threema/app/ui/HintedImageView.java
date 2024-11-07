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
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HintedImageView extends androidx.appcompat.widget.AppCompatImageView implements View.OnClickListener {

    private static final Logger logger = LoggerFactory.getLogger(HintedImageView.class);

    private OnClickListener onClickListener;

    public HintedImageView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(45225)) {
            setOnClickListener(this);
        }
    }

    public HintedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(45226)) {
            setOnClickListener(this);
        }
    }

    public HintedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (!ListenerUtil.mutListener.listen(45229)) {
            if (l == this) {
                if (!ListenerUtil.mutListener.listen(45227)) {
                    super.setOnClickListener(l);
                }
                if (!ListenerUtil.mutListener.listen(45228)) {
                    this.onClickListener = l;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(45231)) {
            if (this.onClickListener != null) {
                if (!ListenerUtil.mutListener.listen(45230)) {
                    handleClick();
                }
            }
        }
    }

    private void handleClick() {
        if (!ListenerUtil.mutListener.listen(45252)) {
            if ((ListenerUtil.mutListener.listen(45232) ? (getContentDescription() != null || getContext() != null) : (getContentDescription() != null && getContext() != null))) {
                String contentDesc = getContentDescription().toString();
                if (!ListenerUtil.mutListener.listen(45251)) {
                    if (!TextUtils.isEmpty(contentDesc)) {
                        int[] pos = new int[2];
                        if (!ListenerUtil.mutListener.listen(45233)) {
                            getLocationInWindow(pos);
                        }
                        if (!ListenerUtil.mutListener.listen(45250)) {
                            SingleToast.getInstance().text(contentDesc, Toast.LENGTH_SHORT, Gravity.TOP | Gravity.LEFT, (ListenerUtil.mutListener.listen(45245) ? (pos[0] % ((ListenerUtil.mutListener.listen(45241) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(45240) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(45239) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(45238) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) * 12))))))) : (ListenerUtil.mutListener.listen(45244) ? (pos[0] / ((ListenerUtil.mutListener.listen(45241) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(45240) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(45239) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(45238) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) * 12))))))) : (ListenerUtil.mutListener.listen(45243) ? (pos[0] * ((ListenerUtil.mutListener.listen(45241) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(45240) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(45239) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(45238) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) * 12))))))) : (ListenerUtil.mutListener.listen(45242) ? (pos[0] + ((ListenerUtil.mutListener.listen(45241) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(45240) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(45239) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(45238) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) * 12))))))) : (pos[0] - ((ListenerUtil.mutListener.listen(45241) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(45240) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(45239) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(45238) ? (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(45237) ? (contentDesc.length() % 2) : (ListenerUtil.mutListener.listen(45236) ? (contentDesc.length() * 2) : (ListenerUtil.mutListener.listen(45235) ? (contentDesc.length() - 2) : (ListenerUtil.mutListener.listen(45234) ? (contentDesc.length() + 2) : (contentDesc.length() / 2)))))) * 12))))))))))), (ListenerUtil.mutListener.listen(45249) ? (pos[1] % 128) : (ListenerUtil.mutListener.listen(45248) ? (pos[1] / 128) : (ListenerUtil.mutListener.listen(45247) ? (pos[1] * 128) : (ListenerUtil.mutListener.listen(45246) ? (pos[1] + 128) : (pos[1] - 128))))));
                        }
                    }
                }
            }
        }
    }
}
