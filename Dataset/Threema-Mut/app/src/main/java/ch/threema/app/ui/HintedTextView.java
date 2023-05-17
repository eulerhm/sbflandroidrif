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
import ch.threema.app.emojis.EmojiConversationTextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HintedTextView extends EmojiConversationTextView implements View.OnClickListener {

    private View.OnClickListener onClickListener;

    private Toast toaster = null;

    public HintedTextView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47222)) {
            setOnClickListener(this);
        }
    }

    public HintedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(47223)) {
            setOnClickListener(this);
        }
    }

    public HintedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (!ListenerUtil.mutListener.listen(47226)) {
            if (l == this) {
                if (!ListenerUtil.mutListener.listen(47224)) {
                    super.setOnClickListener(l);
                }
                if (!ListenerUtil.mutListener.listen(47225)) {
                    this.onClickListener = l;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(47228)) {
            if (this.onClickListener != null) {
                if (!ListenerUtil.mutListener.listen(47227)) {
                    handleClick();
                }
            }
        }
    }

    private void handleClick() {
        if (!ListenerUtil.mutListener.listen(47253)) {
            if ((ListenerUtil.mutListener.listen(47229) ? (getText() != null || getContext() != null) : (getText() != null && getContext() != null))) {
                String text = getText().toString();
                if (!ListenerUtil.mutListener.listen(47252)) {
                    if (!TextUtils.isEmpty(text)) {
                        if (!ListenerUtil.mutListener.listen(47231)) {
                            if (this.toaster != null) {
                                if (!ListenerUtil.mutListener.listen(47230)) {
                                    this.toaster.cancel();
                                }
                            }
                        }
                        int[] pos = new int[2];
                        if (!ListenerUtil.mutListener.listen(47232)) {
                            getLocationInWindow(pos);
                        }
                        if (!ListenerUtil.mutListener.listen(47233)) {
                            this.toaster = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
                        }
                        if (!ListenerUtil.mutListener.listen(47250)) {
                            this.toaster.setGravity(Gravity.TOP | Gravity.LEFT, (ListenerUtil.mutListener.listen(47245) ? (pos[0] % ((ListenerUtil.mutListener.listen(47241) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(47240) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(47239) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(47238) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) * 12))))))) : (ListenerUtil.mutListener.listen(47244) ? (pos[0] / ((ListenerUtil.mutListener.listen(47241) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(47240) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(47239) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(47238) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) * 12))))))) : (ListenerUtil.mutListener.listen(47243) ? (pos[0] * ((ListenerUtil.mutListener.listen(47241) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(47240) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(47239) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(47238) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) * 12))))))) : (ListenerUtil.mutListener.listen(47242) ? (pos[0] + ((ListenerUtil.mutListener.listen(47241) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(47240) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(47239) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(47238) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) * 12))))))) : (pos[0] - ((ListenerUtil.mutListener.listen(47241) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) % 12) : (ListenerUtil.mutListener.listen(47240) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) / 12) : (ListenerUtil.mutListener.listen(47239) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) - 12) : (ListenerUtil.mutListener.listen(47238) ? (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) + 12) : (((ListenerUtil.mutListener.listen(47237) ? (text.length() % 2) : (ListenerUtil.mutListener.listen(47236) ? (text.length() * 2) : (ListenerUtil.mutListener.listen(47235) ? (text.length() - 2) : (ListenerUtil.mutListener.listen(47234) ? (text.length() + 2) : (text.length() / 2)))))) * 12))))))))))), (ListenerUtil.mutListener.listen(47249) ? (pos[1] % 128) : (ListenerUtil.mutListener.listen(47248) ? (pos[1] / 128) : (ListenerUtil.mutListener.listen(47247) ? (pos[1] * 128) : (ListenerUtil.mutListener.listen(47246) ? (pos[1] + 128) : (pos[1] - 128))))));
                        }
                        if (!ListenerUtil.mutListener.listen(47251)) {
                            this.toaster.show();
                        }
                    }
                }
            }
        }
    }
}
