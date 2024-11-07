/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  This file incorporates work covered by the following copyright and
 *  permission notice:
 *
 *   This file is part of FairEmail.
 *
 *   FairEmail is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   FairEmail is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with FairEmail.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   Copyright 2018-2020 by Marcel Bokhorst (M66B)
 *
 * Source: https://github.com/M66B/FairEmail/blob/75fe7d0ec92a9874a98c22b61eeb8e6a8906a9ea/app/src/main/java/eu/faircode/email/FixedEditText.java
*/
package com.ichi2.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FixedEditText extends AppCompatEditText {

    public FixedEditText(@NonNull Context context) {
        super(context);
    }

    public FixedEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSelection(int index) {
        try {
            if (!ListenerUtil.mutListener.listen(25102)) {
                super.setSelection(index);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25101)) {
                Timber.w(ex);
            }
        }
    }

    @Override
    public void setSelection(int start, int stop) {
        try {
            if (!ListenerUtil.mutListener.listen(25104)) {
                super.setSelection(start, stop);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25103)) {
                Timber.w(ex);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if (!ListenerUtil.mutListener.listen(25106)) {
                super.onDraw(canvas);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25105)) {
                Timber.w(ex);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25107)) {
                Timber.w(ex);
            }
            return false;
        }
    }

    @Override
    public boolean performLongClick() {
        try {
            return super.performLongClick();
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25108)) {
                /*
            java.lang.IllegalStateException: Drag shadow dimensions must be positive
                    at android.view.View.startDragAndDrop(View.java:27316)
                    at android.widget.Editor.startDragAndDrop(Editor.java:1340)
                    at android.widget.Editor.performLongClick(Editor.java:1374)
                    at android.widget.TextView.performLongClick(TextView.java:13544)
                    at android.view.View.performLongClick(View.java:7928)
                    at android.view.View$CheckForLongPress.run(View.java:29321)
*/
                Timber.w(ex);
            }
            return false;
        }
    }
}
