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
 *     This file is part of FairEmail.
 *
 *     FairEmail is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     FairEmail is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with FairEmail.  If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright 2018-2020 by Marcel Bokhorst (M66B)
 *
 * Source: https://github.com/M66B/FairEmail/blob/75fe7d0ec92a9874a98c22b61eeb8e6a8906a9ea/app/src/main/java/eu/faircode/email/FixedTextView.java
 *
 */
package com.ichi2.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FixedTextView extends AppCompatTextView {

    public FixedTextView(@NonNull Context context) {
        super(context);
    }

    public FixedTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            if (!ListenerUtil.mutListener.listen(25111)) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25109)) {
                Timber.w(ex);
            }
            if (!ListenerUtil.mutListener.listen(25110)) {
                /*
        java.lang.ArrayIndexOutOfBoundsException: length=...; index=...
                at android.text.TextLine.measure(TextLine.java:316)
                at android.text.TextLine.metrics(TextLine.java:271)
                at android.text.Layout.measurePara(Layout.java:2056)
                at android.text.Layout.getDesiredWidth(Layout.java:164)
                at android.widget.TextView.onMeasure(TextView.java:8291)
                at androidx.appcompat.widget.AppCompatTextView.onMeasure(SourceFile:554)
                at android.view.View.measure(View.java:22360)
*/
                setMeasuredDimension(0, 0);
            }
        }
    }

    @Override
    public boolean onPreDraw() {
        try {
            return super.onPreDraw();
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25112)) {
                Timber.w(ex);
            }
            /*
                java.lang.ArrayIndexOutOfBoundsException: length=54; index=54
                at android.text.TextLine.measure(TextLine.java:316)
                at android.text.TextLine.metrics(TextLine.java:271)
                at android.text.Layout.getLineExtent(Layout.java:1374)
                at android.text.Layout.getLineStartPos(Layout.java:700)
                at android.text.Layout.getHorizontal(Layout.java:1175)
                at android.text.Layout.getHorizontal(Layout.java:1144)
                at android.text.Layout.getPrimaryHorizontal(Layout.java:1115)
                at android.widget.TextView.bringPointIntoView(TextView.java:8944)
                at android.widget.TextView.onPreDraw(TextView.java:6475)
            */
            return true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if (!ListenerUtil.mutListener.listen(25114)) {
                super.onDraw(canvas);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25113)) {
                Timber.w(ex);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(25119)) {
            // https://issuetracker.google.com/issues/37068143
            if ((ListenerUtil.mutListener.listen(25116) ? ((ListenerUtil.mutListener.listen(25115) ? (event.getActionMasked() == MotionEvent.ACTION_DOWN || Build.VERSION.RELEASE.equals("6.0")) : (event.getActionMasked() == MotionEvent.ACTION_DOWN && Build.VERSION.RELEASE.equals("6.0"))) || hasSelection()) : ((ListenerUtil.mutListener.listen(25115) ? (event.getActionMasked() == MotionEvent.ACTION_DOWN || Build.VERSION.RELEASE.equals("6.0")) : (event.getActionMasked() == MotionEvent.ACTION_DOWN && Build.VERSION.RELEASE.equals("6.0"))) && hasSelection()))) {
                // Remove selection
                CharSequence text = getText();
                if (!ListenerUtil.mutListener.listen(25117)) {
                    setText(null);
                }
                if (!ListenerUtil.mutListener.listen(25118)) {
                    setText(text);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25120)) {
                Timber.w(ex);
            }
            return false;
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        try {
            if (!ListenerUtil.mutListener.listen(25122)) {
                super.onFocusChanged(focused, direction, previouslyFocusedRect);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25121)) {
                /*
            java.lang.ClassCastException: android.text.SpannedString cannot be cast to android.text.Spannable
              at android.widget.Editor.onFocusChanged(Editor.java:1058)
              at android.widget.TextView.onFocusChanged(TextView.java:9262)
              at android.view.View.handleFocusGainInternal(View.java:5388)
              at android.view.View.requestFocusNoSearch(View.java:8131)
              at android.view.View.requestFocus(View.java:8110)
              at android.view.View.requestFocus(View.java:8077)
              at android.view.View.requestFocus(View.java:8056)
              at android.view.View.onTouchEvent(View.java:10359)
              at android.widget.TextView.onTouchEvent(TextView.java:9580)
              at android.view.View.dispatchTouchEvent(View.java:8981)
*/
                Timber.w(ex);
            }
        }
    }

    @Override
    public boolean performLongClick() {
        try {
            return super.performLongClick();
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25123)) {
                /*
            java.lang.NullPointerException: Attempt to invoke virtual method 'int android.widget.Editor$SelectionModifierCursorController.getMinTouchOffset()' on a null object reference
                    at android.widget.Editor.touchPositionIsInSelection(Unknown:36)
                    at android.widget.Editor.performLongClick(Unknown:72)
                    at android.widget.TextView.performLongClick(Unknown:24)
*/
                Timber.w(ex);
            }
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            return super.onKeyDown(keyCode, event);
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25124)) {
                /*
                java.lang.IllegalArgumentException
                  at com.android.internal.util.Preconditions.checkArgument(Preconditions.java:33)
                  at android.widget.SelectionActionModeHelper$TextClassificationHelper.init(SelectionActionModeHelper.java:641)
                  at android.widget.SelectionActionModeHelper.resetTextClassificationHelper(SelectionActionModeHelper.java:204)
                  at android.widget.SelectionActionModeHelper.startActionModeAsync(SelectionActionModeHelper.java:88)
                  at android.widget.Editor.startSelectionActionModeAsync(Editor.java:2021)
                  at android.widget.Editor.refreshTextActionMode(Editor.java:1966)
                  at android.widget.TextView.spanChange(TextView.java:9525)
                  at android.widget.TextView$ChangeWatcher.onSpanChanged(TextView.java:11973)
                  at android.text.SpannableStringBuilder.sendSpanChanged(SpannableStringBuilder.java:1292)
                  at android.text.SpannableStringBuilder.setSpan(SpannableStringBuilder.java:748)
                  at android.text.SpannableStringBuilder.setSpan(SpannableStringBuilder.java:672)
                  at android.text.Selection.extendSelection(Selection.java:102)
                  at android.text.Selection.extendLeft(Selection.java:324)
                  at android.text.method.ArrowKeyMovementMethod.left(ArrowKeyMovementMethod.java:72)
                  at android.text.method.BaseMovementMethod.handleMovementKey(BaseMovementMethod.java:165)
                  at android.text.method.ArrowKeyMovementMethod.handleMovementKey(ArrowKeyMovementMethod.java:65)
                  at android.text.method.BaseMovementMethod.onKeyDown(BaseMovementMethod.java:42)
                  at android.widget.TextView.doKeyDown(TextView.java:7367)
                  at android.widget.TextView.onKeyDown(TextView.java:7117)
                  at android.view.KeyEvent.dispatch(KeyEvent.java:2707)
             */
                Timber.w(ex);
            }
            return false;
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        try {
            if (!ListenerUtil.mutListener.listen(25126)) {
                super.setText(text, type);
            }
        } catch (Throwable ex) {
            if (!ListenerUtil.mutListener.listen(25125)) {
                Timber.w(ex);
            }
        }
    }
}
