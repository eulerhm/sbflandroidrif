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
package ch.threema.app.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ListViewSwipeListener implements View.OnTouchListener {

    private static final Logger logger = LoggerFactory.getLogger(ListViewSwipeListener.class);

    // Cached ViewConfiguration and system-wide constant values
    private int mSlop;

    private long mAnimationTime;

    // Fixed properties
    private ListView mListView;

    private DismissCallbacks mCallbacks;

    // 1 and not 0 to prevent dividing by zero
    private int mViewWidth = 1;

    // Transient properties
    private float mDownX;

    private float mDownY;

    private boolean mSwiping;

    private int mSwipingSlop;

    private int mDownPosition;

    private View mDownView;

    private boolean mPaused;

    private ImageView quoteIcon;

    private int bubbleInset;

    public interface DismissCallbacks {

        boolean canSwipe(int position);

        void onSwiped(int position);
    }

    public ListViewSwipeListener(ListView listView, DismissCallbacks callbacks) {
        ViewConfiguration vc = ViewConfiguration.get(listView.getContext());
        if (!ListenerUtil.mutListener.listen(45601)) {
            mSlop = vc.getScaledTouchSlop();
        }
        if (!ListenerUtil.mutListener.listen(45602)) {
            mAnimationTime = listView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
        }
        if (!ListenerUtil.mutListener.listen(45603)) {
            mListView = listView;
        }
        if (!ListenerUtil.mutListener.listen(45604)) {
            mCallbacks = callbacks;
        }
        if (!ListenerUtil.mutListener.listen(45605)) {
            bubbleInset = ThreemaApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.chat_bubble_opposite_inset);
        }
    }

    public void setEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(45606)) {
            mPaused = !enabled;
        }
    }

    public AbsListView.OnScrollListener makeScrollListener() {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (!ListenerUtil.mutListener.listen(45607)) {
                    setEnabled(scrollState != AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        };
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!ListenerUtil.mutListener.listen(45614)) {
            if ((ListenerUtil.mutListener.listen(45612) ? (mViewWidth >= 2) : (ListenerUtil.mutListener.listen(45611) ? (mViewWidth <= 2) : (ListenerUtil.mutListener.listen(45610) ? (mViewWidth > 2) : (ListenerUtil.mutListener.listen(45609) ? (mViewWidth != 2) : (ListenerUtil.mutListener.listen(45608) ? (mViewWidth == 2) : (mViewWidth < 2))))))) {
                if (!ListenerUtil.mutListener.listen(45613)) {
                    mViewWidth = mListView.getWidth();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45734)) {
            switch(motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    {
                        if (!ListenerUtil.mutListener.listen(45615)) {
                            logger.debug("*** ACTION_DOWN");
                        }
                        if (!ListenerUtil.mutListener.listen(45616)) {
                            if (mPaused) {
                                return false;
                            }
                        }
                        // Find the child view that was touched (perform a hit test)
                        Rect rect = new Rect();
                        int childCount = mListView.getChildCount();
                        int[] listViewCoords = new int[2];
                        if (!ListenerUtil.mutListener.listen(45617)) {
                            mListView.getLocationOnScreen(listViewCoords);
                        }
                        int x = (ListenerUtil.mutListener.listen(45621) ? ((int) motionEvent.getRawX() % listViewCoords[0]) : (ListenerUtil.mutListener.listen(45620) ? ((int) motionEvent.getRawX() / listViewCoords[0]) : (ListenerUtil.mutListener.listen(45619) ? ((int) motionEvent.getRawX() * listViewCoords[0]) : (ListenerUtil.mutListener.listen(45618) ? ((int) motionEvent.getRawX() + listViewCoords[0]) : ((int) motionEvent.getRawX() - listViewCoords[0])))));
                        int y = (ListenerUtil.mutListener.listen(45625) ? ((int) motionEvent.getRawY() % listViewCoords[1]) : (ListenerUtil.mutListener.listen(45624) ? ((int) motionEvent.getRawY() / listViewCoords[1]) : (ListenerUtil.mutListener.listen(45623) ? ((int) motionEvent.getRawY() * listViewCoords[1]) : (ListenerUtil.mutListener.listen(45622) ? ((int) motionEvent.getRawY() + listViewCoords[1]) : ((int) motionEvent.getRawY() - listViewCoords[1])))));
                        View child;
                        {
                            long _loopCounter538 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(45633) ? (i >= childCount) : (ListenerUtil.mutListener.listen(45632) ? (i <= childCount) : (ListenerUtil.mutListener.listen(45631) ? (i > childCount) : (ListenerUtil.mutListener.listen(45630) ? (i != childCount) : (ListenerUtil.mutListener.listen(45629) ? (i == childCount) : (i < childCount)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter538", ++_loopCounter538);
                                child = mListView.getChildAt(i);
                                if (!ListenerUtil.mutListener.listen(45626)) {
                                    child.getHitRect(rect);
                                }
                                if (!ListenerUtil.mutListener.listen(45628)) {
                                    if (rect.contains(x, y)) {
                                        if (!ListenerUtil.mutListener.listen(45627)) {
                                            mDownView = child;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45642)) {
                            if (mDownView != null) {
                                if (!ListenerUtil.mutListener.listen(45634)) {
                                    mDownX = motionEvent.getRawX();
                                }
                                if (!ListenerUtil.mutListener.listen(45635)) {
                                    mDownY = motionEvent.getRawY();
                                }
                                if (!ListenerUtil.mutListener.listen(45636)) {
                                    mDownPosition = mListView.getPositionForView(mDownView);
                                }
                                if (!ListenerUtil.mutListener.listen(45641)) {
                                    if (!mCallbacks.canSwipe(mDownPosition)) {
                                        if (!ListenerUtil.mutListener.listen(45640)) {
                                            mDownView = null;
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(45637)) {
                                            /*
						quoteIcon = mDownView.findViewById(R.id.quote_icon);
						*/
                                            quoteIcon = null;
                                        }
                                        View messageBlock = mDownView.findViewById(R.id.message_block);
                                        if (!ListenerUtil.mutListener.listen(45639)) {
                                            if (messageBlock != null) {
                                                if (!ListenerUtil.mutListener.listen(45638)) {
                                                    mViewWidth = messageBlock.getWidth();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return false;
                    }
                case MotionEvent.ACTION_CANCEL:
                    {
                        if (!ListenerUtil.mutListener.listen(45643)) {
                            logger.debug("*** ACTION_CANCEL");
                        }
                        if (!ListenerUtil.mutListener.listen(45646)) {
                            if ((ListenerUtil.mutListener.listen(45644) ? (mDownView != null || mSwiping) : (mDownView != null && mSwiping))) {
                                if (!ListenerUtil.mutListener.listen(45645)) {
                                    // cancel
                                    mDownView.animate().translationX(0).alpha(1).setDuration(mAnimationTime).setListener(null);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45647)) {
                            mDownX = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(45648)) {
                            mDownY = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(45649)) {
                            mDownView = null;
                        }
                        if (!ListenerUtil.mutListener.listen(45650)) {
                            mDownPosition = ListView.INVALID_POSITION;
                        }
                        if (!ListenerUtil.mutListener.listen(45651)) {
                            mSwiping = false;
                        }
                        if (!ListenerUtil.mutListener.listen(45652)) {
                            setQuoteIconVisibility(View.GONE);
                        }
                        break;
                    }
                case MotionEvent.ACTION_UP:
                    {
                        if (!ListenerUtil.mutListener.listen(45653)) {
                            logger.debug("*** ACTION_UP");
                        }
                        if (!ListenerUtil.mutListener.listen(45674)) {
                            if (mDownView != null) {
                                float deltaX = (ListenerUtil.mutListener.listen(45657) ? (motionEvent.getRawX() % mDownX) : (ListenerUtil.mutListener.listen(45656) ? (motionEvent.getRawX() / mDownX) : (ListenerUtil.mutListener.listen(45655) ? (motionEvent.getRawX() * mDownX) : (ListenerUtil.mutListener.listen(45654) ? (motionEvent.getRawX() + mDownX) : (motionEvent.getRawX() - mDownX)))));
                                if (!ListenerUtil.mutListener.listen(45673)) {
                                    if ((ListenerUtil.mutListener.listen(45668) ? ((ListenerUtil.mutListener.listen(45667) ? ((ListenerUtil.mutListener.listen(45666) ? (Math.abs(deltaX) >= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45665) ? (Math.abs(deltaX) <= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45664) ? (Math.abs(deltaX) < (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45663) ? (Math.abs(deltaX) != (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45662) ? (Math.abs(deltaX) == (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (Math.abs(deltaX) > (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4))))))))))) || mSwiping) : ((ListenerUtil.mutListener.listen(45666) ? (Math.abs(deltaX) >= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45665) ? (Math.abs(deltaX) <= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45664) ? (Math.abs(deltaX) < (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45663) ? (Math.abs(deltaX) != (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45662) ? (Math.abs(deltaX) == (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (Math.abs(deltaX) > (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4))))))))))) && mSwiping)) || mDownPosition != ListView.INVALID_POSITION) : ((ListenerUtil.mutListener.listen(45667) ? ((ListenerUtil.mutListener.listen(45666) ? (Math.abs(deltaX) >= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45665) ? (Math.abs(deltaX) <= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45664) ? (Math.abs(deltaX) < (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45663) ? (Math.abs(deltaX) != (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45662) ? (Math.abs(deltaX) == (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (Math.abs(deltaX) > (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4))))))))))) || mSwiping) : ((ListenerUtil.mutListener.listen(45666) ? (Math.abs(deltaX) >= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45665) ? (Math.abs(deltaX) <= (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45664) ? (Math.abs(deltaX) < (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45663) ? (Math.abs(deltaX) != (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (ListenerUtil.mutListener.listen(45662) ? (Math.abs(deltaX) == (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4)))))) : (Math.abs(deltaX) > (ListenerUtil.mutListener.listen(45661) ? (mViewWidth % 4) : (ListenerUtil.mutListener.listen(45660) ? (mViewWidth * 4) : (ListenerUtil.mutListener.listen(45659) ? (mViewWidth - 4) : (ListenerUtil.mutListener.listen(45658) ? (mViewWidth + 4) : (mViewWidth / 4))))))))))) && mSwiping)) && mDownPosition != ListView.INVALID_POSITION))) {
                                        // ok
                                        final int downPosition = mDownPosition;
                                        if (!ListenerUtil.mutListener.listen(45672)) {
                                            mDownView.animate().translationX(0).setDuration(mAnimationTime).setListener(new AnimatorListenerAdapter() {

                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    if (!ListenerUtil.mutListener.listen(45670)) {
                                                        mCallbacks.onSwiped(downPosition);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(45671)) {
                                                        mDownPosition = ListView.INVALID_POSITION;
                                                    }
                                                }
                                            });
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(45669)) {
                                            // cancel
                                            mDownView.animate().translationX(0).setDuration(mAnimationTime).setListener(null);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45675)) {
                            mDownX = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(45676)) {
                            mDownY = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(45677)) {
                            mDownView = null;
                        }
                        if (!ListenerUtil.mutListener.listen(45678)) {
                            mDownPosition = ListView.INVALID_POSITION;
                        }
                        if (!ListenerUtil.mutListener.listen(45679)) {
                            mSwiping = false;
                        }
                        if (!ListenerUtil.mutListener.listen(45680)) {
                            setQuoteIconVisibility(View.GONE);
                        }
                        break;
                    }
                case MotionEvent.ACTION_MOVE:
                    {
                        if (!ListenerUtil.mutListener.listen(45681)) {
                            logger.debug("*** ACTION_MOVE");
                        }
                        if (!ListenerUtil.mutListener.listen(45683)) {
                            if ((ListenerUtil.mutListener.listen(45682) ? (mPaused && mDownView == null) : (mPaused || mDownView == null))) {
                                break;
                            }
                        }
                        float deltaX = (ListenerUtil.mutListener.listen(45687) ? (motionEvent.getRawX() % mDownX) : (ListenerUtil.mutListener.listen(45686) ? (motionEvent.getRawX() / mDownX) : (ListenerUtil.mutListener.listen(45685) ? (motionEvent.getRawX() * mDownX) : (ListenerUtil.mutListener.listen(45684) ? (motionEvent.getRawX() + mDownX) : (motionEvent.getRawX() - mDownX)))));
                        float deltaY = (ListenerUtil.mutListener.listen(45691) ? (motionEvent.getRawY() % mDownY) : (ListenerUtil.mutListener.listen(45690) ? (motionEvent.getRawY() / mDownY) : (ListenerUtil.mutListener.listen(45689) ? (motionEvent.getRawY() * mDownY) : (ListenerUtil.mutListener.listen(45688) ? (motionEvent.getRawY() + mDownY) : (motionEvent.getRawY() - mDownY)))));
                        if (!ListenerUtil.mutListener.listen(45698)) {
                            if ((ListenerUtil.mutListener.listen(45696) ? (deltaX >= 0) : (ListenerUtil.mutListener.listen(45695) ? (deltaX <= 0) : (ListenerUtil.mutListener.listen(45694) ? (deltaX > 0) : (ListenerUtil.mutListener.listen(45693) ? (deltaX != 0) : (ListenerUtil.mutListener.listen(45692) ? (deltaX == 0) : (deltaX < 0))))))) {
                                if (!ListenerUtil.mutListener.listen(45697)) {
                                    deltaX = 0;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45727)) {
                            if ((ListenerUtil.mutListener.listen(45713) ? ((ListenerUtil.mutListener.listen(45703) ? (deltaX >= mSlop) : (ListenerUtil.mutListener.listen(45702) ? (deltaX <= mSlop) : (ListenerUtil.mutListener.listen(45701) ? (deltaX < mSlop) : (ListenerUtil.mutListener.listen(45700) ? (deltaX != mSlop) : (ListenerUtil.mutListener.listen(45699) ? (deltaX == mSlop) : (deltaX > mSlop)))))) || (ListenerUtil.mutListener.listen(45712) ? (Math.abs(deltaY) >= (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45711) ? (Math.abs(deltaY) <= (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45710) ? (Math.abs(deltaY) > (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45709) ? (Math.abs(deltaY) != (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45708) ? (Math.abs(deltaY) == (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (Math.abs(deltaY) < (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))))))))) : ((ListenerUtil.mutListener.listen(45703) ? (deltaX >= mSlop) : (ListenerUtil.mutListener.listen(45702) ? (deltaX <= mSlop) : (ListenerUtil.mutListener.listen(45701) ? (deltaX < mSlop) : (ListenerUtil.mutListener.listen(45700) ? (deltaX != mSlop) : (ListenerUtil.mutListener.listen(45699) ? (deltaX == mSlop) : (deltaX > mSlop)))))) && (ListenerUtil.mutListener.listen(45712) ? (Math.abs(deltaY) >= (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45711) ? (Math.abs(deltaY) <= (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45710) ? (Math.abs(deltaY) > (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45709) ? (Math.abs(deltaY) != (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (ListenerUtil.mutListener.listen(45708) ? (Math.abs(deltaY) == (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))) : (Math.abs(deltaY) < (ListenerUtil.mutListener.listen(45707) ? (Math.abs(deltaX) % 2) : (ListenerUtil.mutListener.listen(45706) ? (Math.abs(deltaX) * 2) : (ListenerUtil.mutListener.listen(45705) ? (Math.abs(deltaX) - 2) : (ListenerUtil.mutListener.listen(45704) ? (Math.abs(deltaX) + 2) : (Math.abs(deltaX) / 2)))))))))))))) {
                                if (!ListenerUtil.mutListener.listen(45714)) {
                                    mSwiping = true;
                                }
                                if (!ListenerUtil.mutListener.listen(45715)) {
                                    mSwipingSlop = mSlop;
                                }
                                if (!ListenerUtil.mutListener.listen(45716)) {
                                    mListView.requestDisallowInterceptTouchEvent(true);
                                }
                                if (!ListenerUtil.mutListener.listen(45723)) {
                                    if ((ListenerUtil.mutListener.listen(45721) ? (deltaX >= 0) : (ListenerUtil.mutListener.listen(45720) ? (deltaX <= 0) : (ListenerUtil.mutListener.listen(45719) ? (deltaX < 0) : (ListenerUtil.mutListener.listen(45718) ? (deltaX != 0) : (ListenerUtil.mutListener.listen(45717) ? (deltaX == 0) : (deltaX > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(45722)) {
                                            setQuoteIconVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                // Cancel ListView's touch (un-highlighting the item)
                                MotionEvent cancelEvent = MotionEvent.obtain(motionEvent);
                                if (!ListenerUtil.mutListener.listen(45724)) {
                                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL | (motionEvent.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                                }
                                if (!ListenerUtil.mutListener.listen(45725)) {
                                    mListView.onTouchEvent(cancelEvent);
                                }
                                if (!ListenerUtil.mutListener.listen(45726)) {
                                    cancelEvent.recycle();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45733)) {
                            if (mSwiping) {
                                if (!ListenerUtil.mutListener.listen(45732)) {
                                    mDownView.setTranslationX((ListenerUtil.mutListener.listen(45731) ? (deltaX % mSwipingSlop) : (ListenerUtil.mutListener.listen(45730) ? (deltaX / mSwipingSlop) : (ListenerUtil.mutListener.listen(45729) ? (deltaX * mSwipingSlop) : (ListenerUtil.mutListener.listen(45728) ? (deltaX + mSwipingSlop) : (deltaX - mSwipingSlop))))));
                                }
                                return true;
                            }
                        }
                        break;
                    }
            }
        }
        return false;
    }

    private void setQuoteIconVisibility(int visibility) {
        if (!ListenerUtil.mutListener.listen(45740)) {
            if (quoteIcon != null) {
                if (!ListenerUtil.mutListener.listen(45739)) {
                    if (quoteIcon.getVisibility() != visibility) {
                        if (!ListenerUtil.mutListener.listen(45738)) {
                            if (visibility == View.VISIBLE) {
                                if (!ListenerUtil.mutListener.listen(45736)) {
                                    quoteIcon.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(45737)) {
                                    ObjectAnimator.ofFloat(quoteIcon, View.ALPHA, 0.2f, 1.0f).setDuration(300).start();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(45735)) {
                                    quoteIcon.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
