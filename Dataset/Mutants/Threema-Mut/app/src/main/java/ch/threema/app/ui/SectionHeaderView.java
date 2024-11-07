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
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import ch.threema.app.R;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SectionHeaderView extends LinearLayout {

    private TextView textView;

    public SectionHeaderView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47049)) {
            this.init(null);
        }
    }

    public SectionHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(47050)) {
            this.init(attrs);
        }
    }

    public SectionHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(47051)) {
            this.init(attrs);
        }
    }

    private void init(AttributeSet attrs) {
        if (!ListenerUtil.mutListener.listen(47052)) {
            inflate(getContext(), R.layout.header_section, this);
        }
        if (!ListenerUtil.mutListener.listen(47053)) {
            this.textView = this.findViewById(R.id.text);
        }
        if (!ListenerUtil.mutListener.listen(47058)) {
            if (attrs != null) {
                TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.SectionHeaderView);
                if (!ListenerUtil.mutListener.listen(47057)) {
                    if (a != null) {
                        if (!ListenerUtil.mutListener.listen(47055)) {
                            if (this.textView != null) {
                                String text = a.getString(R.styleable.SectionHeaderView_android_text);
                                if (!ListenerUtil.mutListener.listen(47054)) {
                                    this.setText(text);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(47056)) {
                            a.recycle();
                        }
                    }
                }
            }
        }
    }

    public void setText(String text) {
        if (!ListenerUtil.mutListener.listen(47062)) {
            if (this.textView != null) {
                if (!ListenerUtil.mutListener.listen(47061)) {
                    if (!TestUtil.empty(text)) {
                        if (!ListenerUtil.mutListener.listen(47060)) {
                            this.textView.setText(text);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(47059)) {
                            this.textView.setText("");
                        }
                    }
                }
            }
        }
    }
}
