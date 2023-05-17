/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NewWizardFingerPrintView extends SquareImageView implements View.OnTouchListener {

    public interface OnSwipeResult {

        void newBytes(byte[] bytes, int step, int maxStep);
    }

    static int CHAR_LENGTH = 16;

    private final String backgroundChars = "0123456789ABCDEF";

    private int maximalSteps = 0;

    private int currentStep = 0;

    private final int backgroundCharsCount = this.backgroundChars.length();

    private float positionCorrection;

    final Paint backgroundCharPaint = new Paint();

    final Paint backgroundCharPaintFixed = new Paint();

    private int backgroundCharSpace;

    private OnSwipeResult swipeByteListener;

    private Integer pointLeakCount = 0;

    private Integer pointLeak = 5;

    private byte[] lastDigest;

    private LockableScrollView lockableScrollViewParent;

    Random randomGenerator = new Random();

    private int fixedCharCount;

    private int charsToFixPerStep;

    private class Char {

        public boolean isFixed = false;

        public char text;

        public int[] position = new int[2];
    }

    private final List<Char> currentChars = new ArrayList<>();

    public NewWizardFingerPrintView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(46262)) {
            this.initView();
        }
    }

    private void initView() {
        if (!ListenerUtil.mutListener.listen(46263)) {
            this.setFocusable(true);
        }
        if (!ListenerUtil.mutListener.listen(46264)) {
            this.setFocusableInTouchMode(true);
        }
        if (!ListenerUtil.mutListener.listen(46265)) {
            this.setOnTouchListener(this);
        }
        if (!ListenerUtil.mutListener.listen(46266)) {
            this.backgroundCharPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(46267)) {
            this.backgroundCharPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(46268)) {
            this.backgroundCharPaint.setTextAlign(Paint.Align.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(46269)) {
            this.backgroundCharPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
        if (!ListenerUtil.mutListener.listen(46270)) {
            this.backgroundCharPaintFixed.setColor(this.getResources().getColor(R.color.new_wizard_color_accent));
        }
        if (!ListenerUtil.mutListener.listen(46271)) {
            this.backgroundCharPaintFixed.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(46272)) {
            this.backgroundCharPaintFixed.setTextAlign(Paint.Align.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(46273)) {
            this.backgroundCharPaintFixed.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
        if (!ListenerUtil.mutListener.listen(46274)) {
            this.reset();
        }
    }

    public void reset() {
        if (!ListenerUtil.mutListener.listen(46275)) {
            this.currentStep = 0;
        }
        if (!ListenerUtil.mutListener.listen(46276)) {
            this.fixedCharCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(46277)) {
            this.resetChars(true);
        }
        if (!ListenerUtil.mutListener.listen(46278)) {
            this.invalidate();
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        ViewParent p = this.getParent();
        if (!ListenerUtil.mutListener.listen(46282)) {
            {
                long _loopCounter542 = 0;
                while (p != null) {
                    ListenerUtil.loopListener.listen("_loopCounter542", ++_loopCounter542);
                    if (!ListenerUtil.mutListener.listen(46280)) {
                        if (p instanceof LockableScrollView) {
                            if (!ListenerUtil.mutListener.listen(46279)) {
                                this.lockableScrollViewParent = (LockableScrollView) p;
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(46281)) {
                        p = p.getParent();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46283)) {
            super.onWindowVisibilityChanged(visibility);
        }
    }

    public NewWizardFingerPrintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(46284)) {
            this.initView();
        }
    }

    public NewWizardFingerPrintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(46285)) {
            this.initView();
        }
    }

    public void setOnSwipeByte(OnSwipeResult swipeByteListener, int maximalSteps) {
        if (!ListenerUtil.mutListener.listen(46286)) {
            this.swipeByteListener = swipeByteListener;
        }
        if (!ListenerUtil.mutListener.listen(46287)) {
            this.maximalSteps = maximalSteps;
        }
        if (!ListenerUtil.mutListener.listen(46292)) {
            this.charsToFixPerStep = Math.max((int) Math.ceil((ListenerUtil.mutListener.listen(46291) ? (this.currentChars.size() % this.maximalSteps) : (ListenerUtil.mutListener.listen(46290) ? (this.currentChars.size() * this.maximalSteps) : (ListenerUtil.mutListener.listen(46289) ? (this.currentChars.size() - this.maximalSteps) : (ListenerUtil.mutListener.listen(46288) ? (this.currentChars.size() + this.maximalSteps) : (this.currentChars.size() / this.maximalSteps)))))), 1);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!ListenerUtil.mutListener.listen(46293)) {
            if (!this.isEnabled()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(46298)) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (!ListenerUtil.mutListener.listen(46297)) {
                    if (this.lockableScrollViewParent != null) {
                        if (!ListenerUtil.mutListener.listen(46296)) {
                            this.lockableScrollViewParent.setScrollingEnabled(false);
                        }
                    }
                }
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (!ListenerUtil.mutListener.listen(46295)) {
                    if (this.lockableScrollViewParent != null) {
                        if (!ListenerUtil.mutListener.listen(46294)) {
                            this.lockableScrollViewParent.setScrollingEnabled(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46336)) {
            if ((ListenerUtil.mutListener.listen(46303) ? (this.pointLeakCount++ <= this.pointLeak) : (ListenerUtil.mutListener.listen(46302) ? (this.pointLeakCount++ > this.pointLeak) : (ListenerUtil.mutListener.listen(46301) ? (this.pointLeakCount++ < this.pointLeak) : (ListenerUtil.mutListener.listen(46300) ? (this.pointLeakCount++ != this.pointLeak) : (ListenerUtil.mutListener.listen(46299) ? (this.pointLeakCount++ == this.pointLeak) : (this.pointLeakCount++ >= this.pointLeak))))))) {
                PointF currentPoint = new PointF(motionEvent.getX(), motionEvent.getY());
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    if (!ListenerUtil.mutListener.listen(46305)) {
                        // add last digest
                        if (lastDigest != null)
                            if (!ListenerUtil.mutListener.listen(46304)) {
                                messageDigest.update(lastDigest);
                            }
                    }
                    // add position and timestamp of this touch
                    String positionTimestamp = currentPoint.x + "-" + currentPoint.y + "-" + new Date().getTime();
                    if (!ListenerUtil.mutListener.listen(46306)) {
                        messageDigest.update(positionTimestamp.getBytes());
                    }
                    if (!ListenerUtil.mutListener.listen(46307)) {
                        lastDigest = messageDigest.digest();
                    }
                    if (!ListenerUtil.mutListener.listen(46329)) {
                        if ((ListenerUtil.mutListener.listen(46312) ? (this.fixedCharCount >= this.currentChars.size()) : (ListenerUtil.mutListener.listen(46311) ? (this.fixedCharCount <= this.currentChars.size()) : (ListenerUtil.mutListener.listen(46310) ? (this.fixedCharCount > this.currentChars.size()) : (ListenerUtil.mutListener.listen(46309) ? (this.fixedCharCount != this.currentChars.size()) : (ListenerUtil.mutListener.listen(46308) ? (this.fixedCharCount == this.currentChars.size()) : (this.fixedCharCount < this.currentChars.size()))))))) {
                            // get next FIXED chars
                            int fixCharCount = this.charsToFixPerStep;
                            if (!ListenerUtil.mutListener.listen(46328)) {
                                {
                                    long _loopCounter543 = 0;
                                    do {
                                        ListenerUtil.loopListener.listen("_loopCounter543", ++_loopCounter543);
                                        int index = randomGenerator.nextInt(this.currentChars.size());
                                        Char fChar = this.currentChars.get(index);
                                        if (!ListenerUtil.mutListener.listen(46316)) {
                                            if (!fChar.isFixed) {
                                                if (!ListenerUtil.mutListener.listen(46313)) {
                                                    fChar.isFixed = true;
                                                }
                                                if (!ListenerUtil.mutListener.listen(46314)) {
                                                    this.fixedCharCount++;
                                                }
                                                if (!ListenerUtil.mutListener.listen(46315)) {
                                                    fixCharCount--;
                                                }
                                            }
                                        }
                                    } while ((ListenerUtil.mutListener.listen(46327) ? ((ListenerUtil.mutListener.listen(46321) ? (fixCharCount >= 0) : (ListenerUtil.mutListener.listen(46320) ? (fixCharCount <= 0) : (ListenerUtil.mutListener.listen(46319) ? (fixCharCount < 0) : (ListenerUtil.mutListener.listen(46318) ? (fixCharCount != 0) : (ListenerUtil.mutListener.listen(46317) ? (fixCharCount == 0) : (fixCharCount > 0)))))) || (ListenerUtil.mutListener.listen(46326) ? (this.fixedCharCount >= this.currentChars.size()) : (ListenerUtil.mutListener.listen(46325) ? (this.fixedCharCount <= this.currentChars.size()) : (ListenerUtil.mutListener.listen(46324) ? (this.fixedCharCount > this.currentChars.size()) : (ListenerUtil.mutListener.listen(46323) ? (this.fixedCharCount != this.currentChars.size()) : (ListenerUtil.mutListener.listen(46322) ? (this.fixedCharCount == this.currentChars.size()) : (this.fixedCharCount < this.currentChars.size()))))))) : ((ListenerUtil.mutListener.listen(46321) ? (fixCharCount >= 0) : (ListenerUtil.mutListener.listen(46320) ? (fixCharCount <= 0) : (ListenerUtil.mutListener.listen(46319) ? (fixCharCount < 0) : (ListenerUtil.mutListener.listen(46318) ? (fixCharCount != 0) : (ListenerUtil.mutListener.listen(46317) ? (fixCharCount == 0) : (fixCharCount > 0)))))) && (ListenerUtil.mutListener.listen(46326) ? (this.fixedCharCount >= this.currentChars.size()) : (ListenerUtil.mutListener.listen(46325) ? (this.fixedCharCount <= this.currentChars.size()) : (ListenerUtil.mutListener.listen(46324) ? (this.fixedCharCount > this.currentChars.size()) : (ListenerUtil.mutListener.listen(46323) ? (this.fixedCharCount != this.currentChars.size()) : (ListenerUtil.mutListener.listen(46322) ? (this.fixedCharCount == this.currentChars.size()) : (this.fixedCharCount < this.currentChars.size())))))))));
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(46331)) {
                        if (this.swipeByteListener != null) {
                            if (!ListenerUtil.mutListener.listen(46330)) {
                                this.swipeByteListener.newBytes(lastDigest, this.currentStep, this.maximalSteps);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(46332)) {
                        this.currentStep++;
                    }
                } catch (NoSuchAlgorithmException e) {
                    return false;
                }
                if (!ListenerUtil.mutListener.listen(46333)) {
                    this.resetChars(false);
                }
                if (!ListenerUtil.mutListener.listen(46334)) {
                    this.invalidate();
                }
                if (!ListenerUtil.mutListener.listen(46335)) {
                    this.pointLeakCount = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46337)) {
            this.pointLeakCount++;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(46338)) {
            super.onDraw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(46380)) {
            {
                long _loopCounter544 = 0;
                for (Char chr : this.currentChars) {
                    ListenerUtil.loopListener.listen("_loopCounter544", ++_loopCounter544);
                    Paint paint = chr.isFixed ? this.backgroundCharPaintFixed : this.backgroundCharPaint;
                    int x = (int) ((ListenerUtil.mutListener.listen(46346) ? (positionCorrection % ((ListenerUtil.mutListener.listen(46342) ? (chr.position[0] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46341) ? (chr.position[0] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46340) ? (chr.position[0] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46339) ? (chr.position[0] + this.backgroundCharSpace) : (chr.position[0] * this.backgroundCharSpace))))))) : (ListenerUtil.mutListener.listen(46345) ? (positionCorrection / ((ListenerUtil.mutListener.listen(46342) ? (chr.position[0] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46341) ? (chr.position[0] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46340) ? (chr.position[0] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46339) ? (chr.position[0] + this.backgroundCharSpace) : (chr.position[0] * this.backgroundCharSpace))))))) : (ListenerUtil.mutListener.listen(46344) ? (positionCorrection * ((ListenerUtil.mutListener.listen(46342) ? (chr.position[0] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46341) ? (chr.position[0] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46340) ? (chr.position[0] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46339) ? (chr.position[0] + this.backgroundCharSpace) : (chr.position[0] * this.backgroundCharSpace))))))) : (ListenerUtil.mutListener.listen(46343) ? (positionCorrection - ((ListenerUtil.mutListener.listen(46342) ? (chr.position[0] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46341) ? (chr.position[0] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46340) ? (chr.position[0] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46339) ? (chr.position[0] + this.backgroundCharSpace) : (chr.position[0] * this.backgroundCharSpace))))))) : (positionCorrection + ((ListenerUtil.mutListener.listen(46342) ? (chr.position[0] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46341) ? (chr.position[0] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46340) ? (chr.position[0] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46339) ? (chr.position[0] + this.backgroundCharSpace) : (chr.position[0] * this.backgroundCharSpace))))))))))));
                    int y = (int) ((ListenerUtil.mutListener.listen(46354) ? (positionCorrection % ((ListenerUtil.mutListener.listen(46350) ? (chr.position[1] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46349) ? (chr.position[1] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46348) ? (chr.position[1] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46347) ? (chr.position[1] + this.backgroundCharSpace) : (chr.position[1] * this.backgroundCharSpace))))))) : (ListenerUtil.mutListener.listen(46353) ? (positionCorrection / ((ListenerUtil.mutListener.listen(46350) ? (chr.position[1] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46349) ? (chr.position[1] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46348) ? (chr.position[1] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46347) ? (chr.position[1] + this.backgroundCharSpace) : (chr.position[1] * this.backgroundCharSpace))))))) : (ListenerUtil.mutListener.listen(46352) ? (positionCorrection * ((ListenerUtil.mutListener.listen(46350) ? (chr.position[1] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46349) ? (chr.position[1] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46348) ? (chr.position[1] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46347) ? (chr.position[1] + this.backgroundCharSpace) : (chr.position[1] * this.backgroundCharSpace))))))) : (ListenerUtil.mutListener.listen(46351) ? (positionCorrection - ((ListenerUtil.mutListener.listen(46350) ? (chr.position[1] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46349) ? (chr.position[1] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46348) ? (chr.position[1] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46347) ? (chr.position[1] + this.backgroundCharSpace) : (chr.position[1] * this.backgroundCharSpace))))))) : (positionCorrection + ((ListenerUtil.mutListener.listen(46350) ? (chr.position[1] % this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46349) ? (chr.position[1] / this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46348) ? (chr.position[1] - this.backgroundCharSpace) : (ListenerUtil.mutListener.listen(46347) ? (chr.position[1] + this.backgroundCharSpace) : (chr.position[1] * this.backgroundCharSpace))))))))))));
                    if (!ListenerUtil.mutListener.listen(46379)) {
                        canvas.drawText(String.valueOf(chr.text), (ListenerUtil.mutListener.listen(46362) ? (x % ((ListenerUtil.mutListener.listen(46358) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46357) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46356) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46355) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46361) ? (x / ((ListenerUtil.mutListener.listen(46358) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46357) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46356) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46355) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46360) ? (x * ((ListenerUtil.mutListener.listen(46358) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46357) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46356) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46355) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46359) ? (x - ((ListenerUtil.mutListener.listen(46358) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46357) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46356) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46355) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (x + ((ListenerUtil.mutListener.listen(46358) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46357) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46356) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46355) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))))))), (int) ((ListenerUtil.mutListener.listen(46378) ? (((ListenerUtil.mutListener.listen(46370) ? (y % ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46369) ? (y / ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46368) ? (y * ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46367) ? (y - ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (y + ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2)))))))))))) % ((ListenerUtil.mutListener.listen(46374) ? ((paint.descent() + paint.ascent()) % 2) : (ListenerUtil.mutListener.listen(46373) ? ((paint.descent() + paint.ascent()) * 2) : (ListenerUtil.mutListener.listen(46372) ? ((paint.descent() + paint.ascent()) - 2) : (ListenerUtil.mutListener.listen(46371) ? ((paint.descent() + paint.ascent()) + 2) : ((paint.descent() + paint.ascent()) / 2))))))) : (ListenerUtil.mutListener.listen(46377) ? (((ListenerUtil.mutListener.listen(46370) ? (y % ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46369) ? (y / ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46368) ? (y * ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46367) ? (y - ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (y + ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2)))))))))))) / ((ListenerUtil.mutListener.listen(46374) ? ((paint.descent() + paint.ascent()) % 2) : (ListenerUtil.mutListener.listen(46373) ? ((paint.descent() + paint.ascent()) * 2) : (ListenerUtil.mutListener.listen(46372) ? ((paint.descent() + paint.ascent()) - 2) : (ListenerUtil.mutListener.listen(46371) ? ((paint.descent() + paint.ascent()) + 2) : ((paint.descent() + paint.ascent()) / 2))))))) : (ListenerUtil.mutListener.listen(46376) ? (((ListenerUtil.mutListener.listen(46370) ? (y % ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46369) ? (y / ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46368) ? (y * ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46367) ? (y - ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (y + ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2)))))))))))) * ((ListenerUtil.mutListener.listen(46374) ? ((paint.descent() + paint.ascent()) % 2) : (ListenerUtil.mutListener.listen(46373) ? ((paint.descent() + paint.ascent()) * 2) : (ListenerUtil.mutListener.listen(46372) ? ((paint.descent() + paint.ascent()) - 2) : (ListenerUtil.mutListener.listen(46371) ? ((paint.descent() + paint.ascent()) + 2) : ((paint.descent() + paint.ascent()) / 2))))))) : (ListenerUtil.mutListener.listen(46375) ? (((ListenerUtil.mutListener.listen(46370) ? (y % ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46369) ? (y / ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46368) ? (y * ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46367) ? (y - ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (y + ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2)))))))))))) + ((ListenerUtil.mutListener.listen(46374) ? ((paint.descent() + paint.ascent()) % 2) : (ListenerUtil.mutListener.listen(46373) ? ((paint.descent() + paint.ascent()) * 2) : (ListenerUtil.mutListener.listen(46372) ? ((paint.descent() + paint.ascent()) - 2) : (ListenerUtil.mutListener.listen(46371) ? ((paint.descent() + paint.ascent()) + 2) : ((paint.descent() + paint.ascent()) / 2))))))) : (((ListenerUtil.mutListener.listen(46370) ? (y % ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46369) ? (y / ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46368) ? (y * ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (ListenerUtil.mutListener.listen(46367) ? (y - ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2))))))) : (y + ((ListenerUtil.mutListener.listen(46366) ? (this.backgroundCharSpace % 2) : (ListenerUtil.mutListener.listen(46365) ? (this.backgroundCharSpace * 2) : (ListenerUtil.mutListener.listen(46364) ? (this.backgroundCharSpace - 2) : (ListenerUtil.mutListener.listen(46363) ? (this.backgroundCharSpace + 2) : (this.backgroundCharSpace / 2)))))))))))) - ((ListenerUtil.mutListener.listen(46374) ? ((paint.descent() + paint.ascent()) % 2) : (ListenerUtil.mutListener.listen(46373) ? ((paint.descent() + paint.ascent()) * 2) : (ListenerUtil.mutListener.listen(46372) ? ((paint.descent() + paint.ascent()) - 2) : (ListenerUtil.mutListener.listen(46371) ? ((paint.descent() + paint.ascent()) + 2) : ((paint.descent() + paint.ascent()) / 2)))))))))))), paint);
                    }
                }
            }
        }
    }

    private void resetChars(boolean initState) {
        if (!ListenerUtil.mutListener.listen(46382)) {
            if (initState) {
                if (!ListenerUtil.mutListener.listen(46381)) {
                    this.currentChars.clear();
                }
            }
        }
        // regenerate chars
        int listIndex = 0;
        if (!ListenerUtil.mutListener.listen(46419)) {
            {
                long _loopCounter546 = 0;
                for (int x = 0; (ListenerUtil.mutListener.listen(46418) ? (x >= CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46417) ? (x <= CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46416) ? (x > CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46415) ? (x != CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46414) ? (x == CHAR_LENGTH) : (x < CHAR_LENGTH)))))); x++) {
                    ListenerUtil.loopListener.listen("_loopCounter546", ++_loopCounter546);
                    if (!ListenerUtil.mutListener.listen(46413)) {
                        {
                            long _loopCounter545 = 0;
                            for (int y = 0; (ListenerUtil.mutListener.listen(46412) ? (y >= CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46411) ? (y <= CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46410) ? (y > CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46409) ? (y != CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46408) ? (y == CHAR_LENGTH) : (y < CHAR_LENGTH)))))); y++) {
                                ListenerUtil.loopListener.listen("_loopCounter545", ++_loopCounter545);
                                final Char c;
                                if ((ListenerUtil.mutListener.listen(46391) ? (this.currentChars.size() >= (ListenerUtil.mutListener.listen(46386) ? (listIndex % 1) : (ListenerUtil.mutListener.listen(46385) ? (listIndex / 1) : (ListenerUtil.mutListener.listen(46384) ? (listIndex * 1) : (ListenerUtil.mutListener.listen(46383) ? (listIndex - 1) : (listIndex + 1)))))) : (ListenerUtil.mutListener.listen(46390) ? (this.currentChars.size() <= (ListenerUtil.mutListener.listen(46386) ? (listIndex % 1) : (ListenerUtil.mutListener.listen(46385) ? (listIndex / 1) : (ListenerUtil.mutListener.listen(46384) ? (listIndex * 1) : (ListenerUtil.mutListener.listen(46383) ? (listIndex - 1) : (listIndex + 1)))))) : (ListenerUtil.mutListener.listen(46389) ? (this.currentChars.size() > (ListenerUtil.mutListener.listen(46386) ? (listIndex % 1) : (ListenerUtil.mutListener.listen(46385) ? (listIndex / 1) : (ListenerUtil.mutListener.listen(46384) ? (listIndex * 1) : (ListenerUtil.mutListener.listen(46383) ? (listIndex - 1) : (listIndex + 1)))))) : (ListenerUtil.mutListener.listen(46388) ? (this.currentChars.size() != (ListenerUtil.mutListener.listen(46386) ? (listIndex % 1) : (ListenerUtil.mutListener.listen(46385) ? (listIndex / 1) : (ListenerUtil.mutListener.listen(46384) ? (listIndex * 1) : (ListenerUtil.mutListener.listen(46383) ? (listIndex - 1) : (listIndex + 1)))))) : (ListenerUtil.mutListener.listen(46387) ? (this.currentChars.size() == (ListenerUtil.mutListener.listen(46386) ? (listIndex % 1) : (ListenerUtil.mutListener.listen(46385) ? (listIndex / 1) : (ListenerUtil.mutListener.listen(46384) ? (listIndex * 1) : (ListenerUtil.mutListener.listen(46383) ? (listIndex - 1) : (listIndex + 1)))))) : (this.currentChars.size() < (ListenerUtil.mutListener.listen(46386) ? (listIndex % 1) : (ListenerUtil.mutListener.listen(46385) ? (listIndex / 1) : (ListenerUtil.mutListener.listen(46384) ? (listIndex * 1) : (ListenerUtil.mutListener.listen(46383) ? (listIndex - 1) : (listIndex + 1)))))))))))) {
                                    c = new Char();
                                    if (!ListenerUtil.mutListener.listen(46392)) {
                                        // set position!
                                        c.position[0] = x;
                                    }
                                    if (!ListenerUtil.mutListener.listen(46393)) {
                                        c.position[1] = y;
                                    }
                                    if (!ListenerUtil.mutListener.listen(46394)) {
                                        this.currentChars.add(listIndex, c);
                                    }
                                } else {
                                    c = this.currentChars.get(listIndex);
                                }
                                if (!ListenerUtil.mutListener.listen(46406)) {
                                    if (!c.isFixed) {
                                        if (!ListenerUtil.mutListener.listen(46405)) {
                                            if (!initState) {
                                                if (!ListenerUtil.mutListener.listen(46404)) {
                                                    c.text = this.backgroundChars.charAt(randomGenerator.nextInt((ListenerUtil.mutListener.listen(46403) ? (this.backgroundCharsCount % 1) : (ListenerUtil.mutListener.listen(46402) ? (this.backgroundCharsCount / 1) : (ListenerUtil.mutListener.listen(46401) ? (this.backgroundCharsCount * 1) : (ListenerUtil.mutListener.listen(46400) ? (this.backgroundCharsCount + 1) : (this.backgroundCharsCount - 1)))))));
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(46399)) {
                                                    c.text = this.backgroundChars.charAt((ListenerUtil.mutListener.listen(46398) ? (listIndex / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46397) ? (listIndex * CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46396) ? (listIndex - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46395) ? (listIndex + CHAR_LENGTH) : (listIndex % CHAR_LENGTH))))));
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(46407)) {
                                    listIndex++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!ListenerUtil.mutListener.listen(46424)) {
            this.backgroundCharSpace = (ListenerUtil.mutListener.listen(46423) ? (this.getWidth() % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46422) ? (this.getWidth() * CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46421) ? (this.getWidth() - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46420) ? (this.getWidth() + CHAR_LENGTH) : (this.getWidth() / CHAR_LENGTH)))));
        }
        if (!ListenerUtil.mutListener.listen(46437)) {
            this.positionCorrection = ((ListenerUtil.mutListener.listen(46436) ? (((ListenerUtil.mutListener.listen(46432) ? (this.getWidth() % ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46431) ? (this.getWidth() / ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46430) ? (this.getWidth() * ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46429) ? (this.getWidth() + ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (this.getWidth() - ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH)))))))))))) % 2) : (ListenerUtil.mutListener.listen(46435) ? (((ListenerUtil.mutListener.listen(46432) ? (this.getWidth() % ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46431) ? (this.getWidth() / ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46430) ? (this.getWidth() * ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46429) ? (this.getWidth() + ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (this.getWidth() - ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH)))))))))))) * 2) : (ListenerUtil.mutListener.listen(46434) ? (((ListenerUtil.mutListener.listen(46432) ? (this.getWidth() % ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46431) ? (this.getWidth() / ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46430) ? (this.getWidth() * ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46429) ? (this.getWidth() + ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (this.getWidth() - ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH)))))))))))) - 2) : (ListenerUtil.mutListener.listen(46433) ? (((ListenerUtil.mutListener.listen(46432) ? (this.getWidth() % ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46431) ? (this.getWidth() / ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46430) ? (this.getWidth() * ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46429) ? (this.getWidth() + ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (this.getWidth() - ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH)))))))))))) + 2) : (((ListenerUtil.mutListener.listen(46432) ? (this.getWidth() % ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46431) ? (this.getWidth() / ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46430) ? (this.getWidth() * ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (ListenerUtil.mutListener.listen(46429) ? (this.getWidth() + ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH))))))) : (this.getWidth() - ((ListenerUtil.mutListener.listen(46428) ? (this.backgroundCharSpace % CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46427) ? (this.backgroundCharSpace / CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46426) ? (this.backgroundCharSpace - CHAR_LENGTH) : (ListenerUtil.mutListener.listen(46425) ? (this.backgroundCharSpace + CHAR_LENGTH) : (this.backgroundCharSpace * CHAR_LENGTH)))))))))))) / 2))))));
        }
        if (!ListenerUtil.mutListener.listen(46442)) {
            this.backgroundCharPaint.setTextSize((ListenerUtil.mutListener.listen(46441) ? (this.determineMaxTextSize("X", this.backgroundCharSpace) % 2) : (ListenerUtil.mutListener.listen(46440) ? (this.determineMaxTextSize("X", this.backgroundCharSpace) * 2) : (ListenerUtil.mutListener.listen(46439) ? (this.determineMaxTextSize("X", this.backgroundCharSpace) - 2) : (ListenerUtil.mutListener.listen(46438) ? (this.determineMaxTextSize("X", this.backgroundCharSpace) + 2) : (this.determineMaxTextSize("X", this.backgroundCharSpace) / 2))))));
        }
        if (!ListenerUtil.mutListener.listen(46443)) {
            this.backgroundCharPaintFixed.setTextSize(this.backgroundCharPaint.getTextSize());
        }
        if (!ListenerUtil.mutListener.listen(46444)) {
            super.onSizeChanged(w, h, oldw, oldh);
        }
    }

    private int determineMaxTextSize(String str, float maxWidth) {
        int size = 0;
        Paint paint = new Paint();
        if (!ListenerUtil.mutListener.listen(46451)) {
            {
                long _loopCounter547 = 0;
                do {
                    ListenerUtil.loopListener.listen("_loopCounter547", ++_loopCounter547);
                    if (!ListenerUtil.mutListener.listen(46445)) {
                        paint.setTextSize(++size);
                    }
                } while ((ListenerUtil.mutListener.listen(46450) ? (paint.measureText(str) >= maxWidth) : (ListenerUtil.mutListener.listen(46449) ? (paint.measureText(str) <= maxWidth) : (ListenerUtil.mutListener.listen(46448) ? (paint.measureText(str) > maxWidth) : (ListenerUtil.mutListener.listen(46447) ? (paint.measureText(str) != maxWidth) : (ListenerUtil.mutListener.listen(46446) ? (paint.measureText(str) == maxWidth) : (paint.measureText(str) < maxWidth)))))));
            }
        }
        return size;
    }
}
