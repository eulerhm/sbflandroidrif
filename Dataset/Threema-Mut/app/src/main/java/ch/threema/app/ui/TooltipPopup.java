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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.preference.PreferenceManager;
import ch.threema.app.R;
import ch.threema.app.emojis.EmojiTextView;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TooltipPopup extends PopupWindow implements DefaultLifecycleObserver {

    private static final Logger logger = LoggerFactory.getLogger(TooltipPopup.class);

    public static final int ALIGN_ABOVE_ANCHOR_ARROW_LEFT = 1;

    public static final int ALIGN_BELOW_ANCHOR_ARROW_RIGHT = 2;

    public static final int ALIGN_BELOW_ANCHOR_ARROW_LEFT = 3;

    public static final int ALIGN_ABOVE_ANCHOR_ARROW_RIGHT = 4;

    private Context context;

    private EmojiTextView textView;

    private final String preferenceString;

    private Handler timeoutHandler;

    private Runnable dismissRunnable = () -> dismiss(false);

    public TooltipPopup(Context context, int preferenceKey, @LayoutRes int layoutResource, LifecycleOwner lifecycleOwner) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47518)) {
            if (lifecycleOwner != null) {
                if (!ListenerUtil.mutListener.listen(47517)) {
                    lifecycleOwner.getLifecycle().addObserver(this);
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(47523) ? (preferenceKey >= 0) : (ListenerUtil.mutListener.listen(47522) ? (preferenceKey <= 0) : (ListenerUtil.mutListener.listen(47521) ? (preferenceKey > 0) : (ListenerUtil.mutListener.listen(47520) ? (preferenceKey < 0) : (ListenerUtil.mutListener.listen(47519) ? (preferenceKey != 0) : (preferenceKey == 0))))))) {
            this.preferenceString = null;
        } else {
            this.preferenceString = context.getString(preferenceKey);
        }
        if (!ListenerUtil.mutListener.listen(47524)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(47525)) {
            init(context, layoutResource, null);
        }
    }

    public TooltipPopup(Context context, int preferenceKey, @LayoutRes int layoutResource, LifecycleOwner lifecycleOwner, Intent launchIntent) {
        super(context);
        if (!ListenerUtil.mutListener.listen(47527)) {
            if (lifecycleOwner != null) {
                if (!ListenerUtil.mutListener.listen(47526)) {
                    lifecycleOwner.getLifecycle().addObserver(this);
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(47532) ? (preferenceKey >= 0) : (ListenerUtil.mutListener.listen(47531) ? (preferenceKey <= 0) : (ListenerUtil.mutListener.listen(47530) ? (preferenceKey > 0) : (ListenerUtil.mutListener.listen(47529) ? (preferenceKey < 0) : (ListenerUtil.mutListener.listen(47528) ? (preferenceKey != 0) : (preferenceKey == 0))))))) {
            this.preferenceString = null;
        } else {
            this.preferenceString = context.getString(preferenceKey);
        }
        if (!ListenerUtil.mutListener.listen(47533)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(47534)) {
            init(context, layoutResource, launchIntent);
        }
    }

    public TooltipPopup(Context context, String preferenceString, @LayoutRes int layoutResource) {
        super(context);
        this.preferenceString = preferenceString;
        if (!ListenerUtil.mutListener.listen(47535)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(47536)) {
            if (isDismissed(context, preferenceString)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(47537)) {
            init(context, layoutResource, null);
        }
    }

    private void init(Context context, int layoutResource, Intent launchIntent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout popupLayout = (LinearLayout) layoutInflater.inflate(layoutResource, null, false);
        if (!ListenerUtil.mutListener.listen(47538)) {
            this.textView = popupLayout.findViewById(R.id.label);
        }
        if (!ListenerUtil.mutListener.listen(47539)) {
            setContentView(popupLayout);
        }
        if (!ListenerUtil.mutListener.listen(47540)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(47541)) {
            setAnimationStyle(R.style.TooltipAnimation);
        }
        if (!ListenerUtil.mutListener.listen(47542)) {
            setFocusable(false);
        }
        if (!ListenerUtil.mutListener.listen(47543)) {
            setTouchable(true);
        }
        if (!ListenerUtil.mutListener.listen(47544)) {
            setOutsideTouchable(false);
        }
        if (!ListenerUtil.mutListener.listen(47545)) {
            setBackgroundDrawable(new BitmapDrawable());
        }
        if (!ListenerUtil.mutListener.listen(47546)) {
            popupLayout.setOnClickListener(v -> {
                if (launchIntent != null) {
                    context.startActivity(launchIntent);
                    if (context instanceof Activity) {
                        ((Activity) context).overridePendingTransition(0, 0);
                    }
                } else {
                    dismissForever();
                }
            });
        }
        ImageView closeButton = popupLayout.findViewById(R.id.close_button);
        if (!ListenerUtil.mutListener.listen(47550)) {
            if (closeButton != null) {
                if (!ListenerUtil.mutListener.listen(47549)) {
                    if (preferenceString == null) {
                        if (!ListenerUtil.mutListener.listen(47548)) {
                            closeButton.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(47547)) {
                            closeButton.setOnClickListener(v -> dismissForever());
                        }
                    }
                }
            }
        }
    }

    public static boolean isDismissed(Context context, String preferenceString) {
        if (!ListenerUtil.mutListener.listen(47551)) {
            if (preferenceString == null) {
                return false;
            }
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(47552)) {
            if (sharedPreferences != null) {
                return sharedPreferences.getBoolean(preferenceString, false);
            }
        }
        return false;
    }

    public void dismissForever() {
        if (!ListenerUtil.mutListener.listen(47555)) {
            if (preferenceString != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (!ListenerUtil.mutListener.listen(47554)) {
                    if (sharedPreferences != null) {
                        if (!ListenerUtil.mutListener.listen(47553)) {
                            sharedPreferences.edit().putBoolean(preferenceString, true).apply();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47556)) {
            dismiss(false);
        }
    }

    public void dismiss(boolean immediate) {
        if (!ListenerUtil.mutListener.listen(47558)) {
            if (immediate) {
                if (!ListenerUtil.mutListener.listen(47557)) {
                    setAnimationStyle(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47561)) {
            if (timeoutHandler != null) {
                if (!ListenerUtil.mutListener.listen(47559)) {
                    timeoutHandler.removeCallbacks(dismissRunnable);
                }
                if (!ListenerUtil.mutListener.listen(47560)) {
                    timeoutHandler = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47562)) {
            this.dismiss();
        }
    }

    public void show(Activity activity, final View anchor, String text, int align) {
        if (!ListenerUtil.mutListener.listen(47563)) {
            show(activity, anchor, text, align, 0);
        }
    }

    public void show(Activity activity, final View anchor, String text, int align, int timeoutMs) {
        if (!ListenerUtil.mutListener.listen(47564)) {
            if (isDismissed(context, preferenceString)) {
                return;
            }
        }
        int[] originLocation = { 0, 0 };
        if (!ListenerUtil.mutListener.listen(47565)) {
            anchor.getLocationInWindow(originLocation);
        }
        if (!ListenerUtil.mutListener.listen(47566)) {
            show(activity, anchor, text, align, originLocation, timeoutMs);
        }
    }

    public void show(Activity activity, final View anchor, String text, int align, int[] originLocation, int timeoutMs) {
        if (!ListenerUtil.mutListener.listen(47567)) {
            if (isDismissed(context, preferenceString)) {
                return;
            }
        }
        int popupX;
        int popupY;
        if (!ListenerUtil.mutListener.listen(47568)) {
            this.textView.setText(text);
        }
        int screenHeight = activity.getWindowManager().getDefaultDisplay().getHeight();
        int screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        int maxWidth = context.getResources().getDimensionPixelSize(R.dimen.tooltip_max_width);
        if ((ListenerUtil.mutListener.listen(47573) ? (align >= ALIGN_ABOVE_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47572) ? (align <= ALIGN_ABOVE_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47571) ? (align > ALIGN_ABOVE_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47570) ? (align < ALIGN_ABOVE_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47569) ? (align != ALIGN_ABOVE_ANCHOR_ARROW_LEFT) : (align == ALIGN_ABOVE_ANCHOR_ARROW_LEFT))))))) {
            popupX = originLocation[0];
            popupY = (ListenerUtil.mutListener.listen(47638) ? (screenHeight % originLocation[1]) : (ListenerUtil.mutListener.listen(47637) ? (screenHeight / originLocation[1]) : (ListenerUtil.mutListener.listen(47636) ? (screenHeight * originLocation[1]) : (ListenerUtil.mutListener.listen(47635) ? (screenHeight + originLocation[1]) : (screenHeight - originLocation[1]))))) + ConfigUtils.getNavigationBarHeight(activity);
            int marginRight = context.getResources().getDimensionPixelSize(R.dimen.tooltip_margin_right);
            if (!ListenerUtil.mutListener.listen(47647)) {
                this.setWidth(Math.min((ListenerUtil.mutListener.listen(47646) ? ((ListenerUtil.mutListener.listen(47642) ? (screenWidth % marginRight) : (ListenerUtil.mutListener.listen(47641) ? (screenWidth / marginRight) : (ListenerUtil.mutListener.listen(47640) ? (screenWidth * marginRight) : (ListenerUtil.mutListener.listen(47639) ? (screenWidth + marginRight) : (screenWidth - marginRight))))) % popupX) : (ListenerUtil.mutListener.listen(47645) ? ((ListenerUtil.mutListener.listen(47642) ? (screenWidth % marginRight) : (ListenerUtil.mutListener.listen(47641) ? (screenWidth / marginRight) : (ListenerUtil.mutListener.listen(47640) ? (screenWidth * marginRight) : (ListenerUtil.mutListener.listen(47639) ? (screenWidth + marginRight) : (screenWidth - marginRight))))) / popupX) : (ListenerUtil.mutListener.listen(47644) ? ((ListenerUtil.mutListener.listen(47642) ? (screenWidth % marginRight) : (ListenerUtil.mutListener.listen(47641) ? (screenWidth / marginRight) : (ListenerUtil.mutListener.listen(47640) ? (screenWidth * marginRight) : (ListenerUtil.mutListener.listen(47639) ? (screenWidth + marginRight) : (screenWidth - marginRight))))) * popupX) : (ListenerUtil.mutListener.listen(47643) ? ((ListenerUtil.mutListener.listen(47642) ? (screenWidth % marginRight) : (ListenerUtil.mutListener.listen(47641) ? (screenWidth / marginRight) : (ListenerUtil.mutListener.listen(47640) ? (screenWidth * marginRight) : (ListenerUtil.mutListener.listen(47639) ? (screenWidth + marginRight) : (screenWidth - marginRight))))) + popupX) : ((ListenerUtil.mutListener.listen(47642) ? (screenWidth % marginRight) : (ListenerUtil.mutListener.listen(47641) ? (screenWidth / marginRight) : (ListenerUtil.mutListener.listen(47640) ? (screenWidth * marginRight) : (ListenerUtil.mutListener.listen(47639) ? (screenWidth + marginRight) : (screenWidth - marginRight))))) - popupX))))), maxWidth));
            }
            if (!ListenerUtil.mutListener.listen(47649)) {
                if ((ListenerUtil.mutListener.listen(47648) ? (activity.isFinishing() && activity.isDestroyed()) : (activity.isFinishing() || activity.isDestroyed()))) {
                    return;
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(47650)) {
                    showAtLocation(anchor, Gravity.LEFT | Gravity.BOTTOM, popupX, popupY);
                }
            } catch (WindowManager.BadTokenException e) {
                return;
            }
        } else if ((ListenerUtil.mutListener.listen(47578) ? (align >= ALIGN_ABOVE_ANCHOR_ARROW_RIGHT) : (ListenerUtil.mutListener.listen(47577) ? (align <= ALIGN_ABOVE_ANCHOR_ARROW_RIGHT) : (ListenerUtil.mutListener.listen(47576) ? (align > ALIGN_ABOVE_ANCHOR_ARROW_RIGHT) : (ListenerUtil.mutListener.listen(47575) ? (align < ALIGN_ABOVE_ANCHOR_ARROW_RIGHT) : (ListenerUtil.mutListener.listen(47574) ? (align != ALIGN_ABOVE_ANCHOR_ARROW_RIGHT) : (align == ALIGN_ABOVE_ANCHOR_ARROW_RIGHT))))))) {
            popupX = originLocation[0] + anchor.getWidth();
            popupY = (ListenerUtil.mutListener.listen(47622) ? (screenHeight % originLocation[1]) : (ListenerUtil.mutListener.listen(47621) ? (screenHeight / originLocation[1]) : (ListenerUtil.mutListener.listen(47620) ? (screenHeight * originLocation[1]) : (ListenerUtil.mutListener.listen(47619) ? (screenHeight + originLocation[1]) : (screenHeight - originLocation[1]))))) + ConfigUtils.getNavigationBarHeight(activity);
            int marginLeft = context.getResources().getDimensionPixelSize(R.dimen.tooltip_margin_right);
            int popupWidth = Math.min((ListenerUtil.mutListener.listen(47626) ? (popupX % marginLeft) : (ListenerUtil.mutListener.listen(47625) ? (popupX / marginLeft) : (ListenerUtil.mutListener.listen(47624) ? (popupX * marginLeft) : (ListenerUtil.mutListener.listen(47623) ? (popupX + marginLeft) : (popupX - marginLeft))))), maxWidth);
            if (!ListenerUtil.mutListener.listen(47627)) {
                this.setWidth(popupWidth);
            }
            if (!ListenerUtil.mutListener.listen(47629)) {
                if ((ListenerUtil.mutListener.listen(47628) ? (activity.isFinishing() && activity.isDestroyed()) : (activity.isFinishing() || activity.isDestroyed()))) {
                    return;
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(47634)) {
                    showAtLocation(anchor, Gravity.LEFT | Gravity.BOTTOM, (ListenerUtil.mutListener.listen(47633) ? (popupX % popupWidth) : (ListenerUtil.mutListener.listen(47632) ? (popupX / popupWidth) : (ListenerUtil.mutListener.listen(47631) ? (popupX * popupWidth) : (ListenerUtil.mutListener.listen(47630) ? (popupX + popupWidth) : (popupX - popupWidth))))), popupY);
                }
            } catch (WindowManager.BadTokenException e) {
                return;
            }
        } else {
            int marginOnOtherEdge = context.getResources().getDimensionPixelSize(R.dimen.tooltip_margin_right);
            int arrowOffset = context.getResources().getDimensionPixelSize(R.dimen.tooltip_arrow_offset);
            int popupWidth;
            if ((ListenerUtil.mutListener.listen(47583) ? (align >= ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47582) ? (align <= ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47581) ? (align > ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47580) ? (align < ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47579) ? (align != ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (align == ALIGN_BELOW_ANCHOR_ARROW_LEFT))))))) {
                popupX = (ListenerUtil.mutListener.listen(47595) ? (originLocation[0] % arrowOffset) : (ListenerUtil.mutListener.listen(47594) ? (originLocation[0] / arrowOffset) : (ListenerUtil.mutListener.listen(47593) ? (originLocation[0] * arrowOffset) : (ListenerUtil.mutListener.listen(47592) ? (originLocation[0] + arrowOffset) : (originLocation[0] - arrowOffset)))));
                popupY = originLocation[1];
                popupWidth = Math.min((ListenerUtil.mutListener.listen(47603) ? ((ListenerUtil.mutListener.listen(47599) ? (screenWidth % popupX) : (ListenerUtil.mutListener.listen(47598) ? (screenWidth / popupX) : (ListenerUtil.mutListener.listen(47597) ? (screenWidth * popupX) : (ListenerUtil.mutListener.listen(47596) ? (screenWidth + popupX) : (screenWidth - popupX))))) % marginOnOtherEdge) : (ListenerUtil.mutListener.listen(47602) ? ((ListenerUtil.mutListener.listen(47599) ? (screenWidth % popupX) : (ListenerUtil.mutListener.listen(47598) ? (screenWidth / popupX) : (ListenerUtil.mutListener.listen(47597) ? (screenWidth * popupX) : (ListenerUtil.mutListener.listen(47596) ? (screenWidth + popupX) : (screenWidth - popupX))))) / marginOnOtherEdge) : (ListenerUtil.mutListener.listen(47601) ? ((ListenerUtil.mutListener.listen(47599) ? (screenWidth % popupX) : (ListenerUtil.mutListener.listen(47598) ? (screenWidth / popupX) : (ListenerUtil.mutListener.listen(47597) ? (screenWidth * popupX) : (ListenerUtil.mutListener.listen(47596) ? (screenWidth + popupX) : (screenWidth - popupX))))) * marginOnOtherEdge) : (ListenerUtil.mutListener.listen(47600) ? ((ListenerUtil.mutListener.listen(47599) ? (screenWidth % popupX) : (ListenerUtil.mutListener.listen(47598) ? (screenWidth / popupX) : (ListenerUtil.mutListener.listen(47597) ? (screenWidth * popupX) : (ListenerUtil.mutListener.listen(47596) ? (screenWidth + popupX) : (screenWidth - popupX))))) + marginOnOtherEdge) : ((ListenerUtil.mutListener.listen(47599) ? (screenWidth % popupX) : (ListenerUtil.mutListener.listen(47598) ? (screenWidth / popupX) : (ListenerUtil.mutListener.listen(47597) ? (screenWidth * popupX) : (ListenerUtil.mutListener.listen(47596) ? (screenWidth + popupX) : (screenWidth - popupX))))) - marginOnOtherEdge))))), maxWidth);
            } else {
                popupX = originLocation[0] + anchor.getWidth();
                popupY = originLocation[1] + anchor.getHeight();
                // popupWidth = Math.min(popupX - (screenWidth - popupX) - marginOnOtherEdge, maxWidth);
                popupWidth = Math.min((ListenerUtil.mutListener.listen(47591) ? ((ListenerUtil.mutListener.listen(47587) ? (popupX % arrowOffset) : (ListenerUtil.mutListener.listen(47586) ? (popupX / arrowOffset) : (ListenerUtil.mutListener.listen(47585) ? (popupX * arrowOffset) : (ListenerUtil.mutListener.listen(47584) ? (popupX - arrowOffset) : (popupX + arrowOffset))))) % marginOnOtherEdge) : (ListenerUtil.mutListener.listen(47590) ? ((ListenerUtil.mutListener.listen(47587) ? (popupX % arrowOffset) : (ListenerUtil.mutListener.listen(47586) ? (popupX / arrowOffset) : (ListenerUtil.mutListener.listen(47585) ? (popupX * arrowOffset) : (ListenerUtil.mutListener.listen(47584) ? (popupX - arrowOffset) : (popupX + arrowOffset))))) / marginOnOtherEdge) : (ListenerUtil.mutListener.listen(47589) ? ((ListenerUtil.mutListener.listen(47587) ? (popupX % arrowOffset) : (ListenerUtil.mutListener.listen(47586) ? (popupX / arrowOffset) : (ListenerUtil.mutListener.listen(47585) ? (popupX * arrowOffset) : (ListenerUtil.mutListener.listen(47584) ? (popupX - arrowOffset) : (popupX + arrowOffset))))) * marginOnOtherEdge) : (ListenerUtil.mutListener.listen(47588) ? ((ListenerUtil.mutListener.listen(47587) ? (popupX % arrowOffset) : (ListenerUtil.mutListener.listen(47586) ? (popupX / arrowOffset) : (ListenerUtil.mutListener.listen(47585) ? (popupX * arrowOffset) : (ListenerUtil.mutListener.listen(47584) ? (popupX - arrowOffset) : (popupX + arrowOffset))))) + marginOnOtherEdge) : ((ListenerUtil.mutListener.listen(47587) ? (popupX % arrowOffset) : (ListenerUtil.mutListener.listen(47586) ? (popupX / arrowOffset) : (ListenerUtil.mutListener.listen(47585) ? (popupX * arrowOffset) : (ListenerUtil.mutListener.listen(47584) ? (popupX - arrowOffset) : (popupX + arrowOffset))))) - marginOnOtherEdge))))), maxWidth);
            }
            if (!ListenerUtil.mutListener.listen(47604)) {
                this.setWidth(popupWidth);
            }
            if (!ListenerUtil.mutListener.listen(47606)) {
                if ((ListenerUtil.mutListener.listen(47605) ? (activity.isFinishing() && activity.isDestroyed()) : (activity.isFinishing() || activity.isDestroyed()))) {
                    return;
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(47618)) {
                    if ((ListenerUtil.mutListener.listen(47611) ? (align >= ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47610) ? (align <= ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47609) ? (align > ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47608) ? (align < ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (ListenerUtil.mutListener.listen(47607) ? (align != ALIGN_BELOW_ANCHOR_ARROW_LEFT) : (align == ALIGN_BELOW_ANCHOR_ARROW_LEFT))))))) {
                        if (!ListenerUtil.mutListener.listen(47617)) {
                            showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, popupX, popupY);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(47616)) {
                            showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, (ListenerUtil.mutListener.listen(47615) ? (popupX % popupWidth) : (ListenerUtil.mutListener.listen(47614) ? (popupX / popupWidth) : (ListenerUtil.mutListener.listen(47613) ? (popupX * popupWidth) : (ListenerUtil.mutListener.listen(47612) ? (popupX + popupWidth) : (popupX - popupWidth))))), popupY);
                        }
                    }
                }
            } catch (WindowManager.BadTokenException e) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(47660)) {
            if ((ListenerUtil.mutListener.listen(47655) ? (timeoutMs >= 0) : (ListenerUtil.mutListener.listen(47654) ? (timeoutMs <= 0) : (ListenerUtil.mutListener.listen(47653) ? (timeoutMs < 0) : (ListenerUtil.mutListener.listen(47652) ? (timeoutMs != 0) : (ListenerUtil.mutListener.listen(47651) ? (timeoutMs == 0) : (timeoutMs > 0))))))) {
                if (!ListenerUtil.mutListener.listen(47657)) {
                    if (timeoutHandler == null) {
                        if (!ListenerUtil.mutListener.listen(47656)) {
                            timeoutHandler = new Handler();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(47658)) {
                    timeoutHandler.removeCallbacks(dismissRunnable);
                }
                if (!ListenerUtil.mutListener.listen(47659)) {
                    timeoutHandler.postDelayed(dismissRunnable, timeoutMs);
                }
            }
        }
    }

    /**
     *  Notifies that {@code ON_PAUSE} event occurred.
     *  <p>
     *  This method will be called before the {@link LifecycleOwner}'s {@code onPause} method
     *  is called.
     *
     *  @param owner the component, whose state was changed
     */
    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(47661)) {
            dismiss(true);
        }
    }
}
