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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.ref.WeakReference;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.Group;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.AddContactActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.QRCodeService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.ShareUtil;
import ch.threema.app.webclient.activities.SessionsActivity;
import ch.threema.app.webclient.listeners.WebClientServiceListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.SessionService;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityPopup extends DimmingPopupWindow {

    private static final Logger logger = LoggerFactory.getLogger(IdentityPopup.class);

    private Context context;

    private WeakReference<Activity> activityRef = new WeakReference<>(null);

    private ImageView qrCodeView;

    private QRCodeService qrCodeService;

    private SwitchCompat webEnableView;

    private SessionService sessionService;

    private int animationCenterX, animationCenterY;

    private ProfileButtonListener profileButtonListener;

    public IdentityPopup(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(45253)) {
            this.context = context;
        }
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(45255)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(45254)) {
                    dismiss();
                }
                return;
            }
        }
        final UserService userService = serviceManager.getUserService();
        if (!ListenerUtil.mutListener.listen(45257)) {
            if (userService == null) {
                if (!ListenerUtil.mutListener.listen(45256)) {
                    dismiss();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(45258)) {
            qrCodeService = serviceManager.getQRCodeService();
        }
        if (!ListenerUtil.mutListener.listen(45260)) {
            if (qrCodeService == null) {
                if (!ListenerUtil.mutListener.listen(45259)) {
                    dismiss();
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(45262)) {
                sessionService = serviceManager.getWebClientServiceManager().getSessionService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(45261)) {
                dismiss();
            }
            return;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FrameLayout popupLayout = (FrameLayout) layoutInflater.inflate(R.layout.popup_identity, null, false);
        TextView textView = popupLayout.findViewById(R.id.identity_label);
        if (!ListenerUtil.mutListener.listen(45263)) {
            this.qrCodeView = popupLayout.findViewById(R.id.qr_image);
        }
        Group webControls = popupLayout.findViewById(R.id.web_controls);
        if (!ListenerUtil.mutListener.listen(45264)) {
            this.webEnableView = popupLayout.findViewById(R.id.web_enable);
        }
        if (!ListenerUtil.mutListener.listen(45267)) {
            popupLayout.findViewById(R.id.web_label).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SessionsActivity.class);
                    if (!ListenerUtil.mutListener.listen(45265)) {
                        AnimationUtil.startActivity(activityRef.get(), v, intent);
                    }
                    if (!ListenerUtil.mutListener.listen(45266)) {
                        dismiss();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(45269)) {
            popupLayout.findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(45268)) {
                        ShareUtil.shareContact(activityRef.get(), null);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(45270)) {
            textView.setText(userService.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(45271)) {
            textView.setContentDescription(context.getString(R.string.my_id) + " " + userService.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(45272)) {
            setContentView(popupLayout);
        }
        if (!ListenerUtil.mutListener.listen(45273)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(45274)) {
            setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(45275)) {
            setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(45276)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(45277)) {
            setFocusable(false);
        }
        if (!ListenerUtil.mutListener.listen(45278)) {
            setTouchable(true);
        }
        if (!ListenerUtil.mutListener.listen(45279)) {
            setOutsideTouchable(true);
        }
        if (!ListenerUtil.mutListener.listen(45280)) {
            setBackgroundDrawable(new BitmapDrawable());
        }
        if (!ListenerUtil.mutListener.listen(45281)) {
            popupLayout.setOnClickListener(v -> dismiss());
        }
        Chip scanButton = popupLayout.findViewById(R.id.scan_button);
        if (!ListenerUtil.mutListener.listen(45283)) {
            if (scanButton != null) {
                if (!ListenerUtil.mutListener.listen(45282)) {
                    scanButton.setOnClickListener(v -> scanQR());
                }
            }
        }
        Chip profileButton = popupLayout.findViewById(R.id.profile_button);
        if (!ListenerUtil.mutListener.listen(45285)) {
            if (profileButton != null) {
                if (!ListenerUtil.mutListener.listen(45284)) {
                    profileButton.setOnClickListener(v -> {
                        dismiss();
                        this.profileButtonListener.onClicked();
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45287)) {
            if (qrCodeView != null) {
                if (!ListenerUtil.mutListener.listen(45286)) {
                    qrCodeView.setOnClickListener(v -> zoomQR(v));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45295)) {
            if ((ListenerUtil.mutListener.listen(45288) ? (webControls != null || webEnableView != null) : (webControls != null && webEnableView != null))) {
                if (!ListenerUtil.mutListener.listen(45294)) {
                    if ((ListenerUtil.mutListener.listen(45289) ? (AppRestrictionUtil.isWebDisabled(context) && ConfigUtils.isBlackBerry()) : (AppRestrictionUtil.isWebDisabled(context) || ConfigUtils.isBlackBerry()))) {
                        if (!ListenerUtil.mutListener.listen(45292)) {
                            // Webclient is disabled, hide UI elements
                            webEnableView.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(45293)) {
                            webControls.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(45290)) {
                            webEnableView.setChecked(this.isWebClientEnabled());
                        }
                        if (!ListenerUtil.mutListener.listen(45291)) {
                            webEnableView.setOnCheckedChangeListener((buttonView, isChecked) -> startWebClient(isChecked));
                        }
                    }
                }
            }
        }
    }

    private void scanQR() {
        Intent intent = new Intent(context, AddContactActivity.class);
        if (!ListenerUtil.mutListener.listen(45296)) {
            intent.putExtra(AddContactActivity.EXTRA_ADD_BY_QR, true);
        }
        if (!ListenerUtil.mutListener.listen(45299)) {
            if (activityRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(45297)) {
                    activityRef.get().startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(45298)) {
                    activityRef.get().overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                }
            }
        }
    }

    private void zoomQR(View v) {
        if (!ListenerUtil.mutListener.listen(45300)) {
            new QRCodePopup(context, activityRef.get().getWindow().getDecorView(), null).show(v, null);
        }
    }

    /**
     *  @param activity
     *  @param toolbarView Toolbar this popup will be aligned to
     *  @param location center location of navigation icon in toolbar
     */
    public void show(Activity activity, final View toolbarView, int[] location, ProfileButtonListener profileButtonListener) {
        if (!ListenerUtil.mutListener.listen(45301)) {
            this.activityRef = new WeakReference<>(activity);
        }
        if (!ListenerUtil.mutListener.listen(45302)) {
            this.profileButtonListener = profileButtonListener;
        }
        int offsetY = (ListenerUtil.mutListener.listen(45306) ? (activity.getResources().getDimensionPixelSize(R.dimen.navigation_icon_size) % 2) : (ListenerUtil.mutListener.listen(45305) ? (activity.getResources().getDimensionPixelSize(R.dimen.navigation_icon_size) * 2) : (ListenerUtil.mutListener.listen(45304) ? (activity.getResources().getDimensionPixelSize(R.dimen.navigation_icon_size) - 2) : (ListenerUtil.mutListener.listen(45303) ? (activity.getResources().getDimensionPixelSize(R.dimen.navigation_icon_size) + 2) : (activity.getResources().getDimensionPixelSize(R.dimen.navigation_icon_size) / 2)))));
        int offsetX = activity.getResources().getDimensionPixelSize(R.dimen.identity_popup_arrow_margin_left) + ((ListenerUtil.mutListener.listen(45310) ? (activity.getResources().getDimensionPixelSize(R.dimen.identity_popup_arrow_width) % 2) : (ListenerUtil.mutListener.listen(45309) ? (activity.getResources().getDimensionPixelSize(R.dimen.identity_popup_arrow_width) * 2) : (ListenerUtil.mutListener.listen(45308) ? (activity.getResources().getDimensionPixelSize(R.dimen.identity_popup_arrow_width) - 2) : (ListenerUtil.mutListener.listen(45307) ? (activity.getResources().getDimensionPixelSize(R.dimen.identity_popup_arrow_width) + 2) : (activity.getResources().getDimensionPixelSize(R.dimen.identity_popup_arrow_width) / 2))))));
        if (!ListenerUtil.mutListener.listen(45311)) {
            animationCenterX = offsetX;
        }
        if (!ListenerUtil.mutListener.listen(45312)) {
            animationCenterY = 0;
        }
        Bitmap bitmap = qrCodeService.getUserQRCode();
        if (!ListenerUtil.mutListener.listen(45314)) {
            if (bitmap == null) {
                if (!ListenerUtil.mutListener.listen(45313)) {
                    dismiss();
                }
                return;
            }
        }
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (!ListenerUtil.mutListener.listen(45315)) {
            bitmapDrawable.setFilterBitmap(false);
        }
        if (!ListenerUtil.mutListener.listen(45316)) {
            this.qrCodeView.setImageDrawable(bitmapDrawable);
        }
        if (!ListenerUtil.mutListener.listen(45318)) {
            if (ConfigUtils.getAppTheme(context) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(45317)) {
                    ConfigUtils.invertColors(this.qrCodeView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45335)) {
            if (toolbarView != null) {
                int[] toolbarLocation = { 0, 0 };
                if (!ListenerUtil.mutListener.listen(45319)) {
                    toolbarView.getLocationInWindow(toolbarLocation);
                }
                if (!ListenerUtil.mutListener.listen(45321)) {
                    if ((ListenerUtil.mutListener.listen(45320) ? (activity.isFinishing() && activity.isDestroyed()) : (activity.isFinishing() || activity.isDestroyed()))) {
                        return;
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(45330)) {
                        showAtLocation(toolbarView, Gravity.LEFT | Gravity.TOP, (ListenerUtil.mutListener.listen(45325) ? (location[0] % offsetX) : (ListenerUtil.mutListener.listen(45324) ? (location[0] / offsetX) : (ListenerUtil.mutListener.listen(45323) ? (location[0] * offsetX) : (ListenerUtil.mutListener.listen(45322) ? (location[0] + offsetX) : (location[0] - offsetX))))), (ListenerUtil.mutListener.listen(45329) ? (location[1] % offsetY) : (ListenerUtil.mutListener.listen(45328) ? (location[1] / offsetY) : (ListenerUtil.mutListener.listen(45327) ? (location[1] * offsetY) : (ListenerUtil.mutListener.listen(45326) ? (location[1] - offsetY) : (location[1] + offsetY))))));
                    }
                } catch (WindowManager.BadTokenException e) {
                }
                if (!ListenerUtil.mutListener.listen(45331)) {
                    dimBackground();
                }
                if (!ListenerUtil.mutListener.listen(45334)) {
                    getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            if (!ListenerUtil.mutListener.listen(45332)) {
                                getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            }
                            if (!ListenerUtil.mutListener.listen(45333)) {
                                AnimationUtil.circularReveal(getContentView(), animationCenterX, animationCenterY, false);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45336)) {
            WebClientListenerManager.serviceListener.add(this.webClientServiceListener);
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(45339)) {
            if (isShowing()) {
                if (!ListenerUtil.mutListener.listen(45338)) {
                    AnimationUtil.circularObscure(getContentView(), animationCenterX, animationCenterY, false, new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(45337)) {
                                IdentityPopup.super.dismiss();
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45340)) {
            WebClientListenerManager.serviceListener.remove(this.webClientServiceListener);
        }
    }

    private WebClientServiceListener webClientServiceListener = new WebClientServiceListener() {

        @Override
        public void onEnabled() {
            if (!ListenerUtil.mutListener.listen(45341)) {
                this.setEnabled(true);
            }
        }

        @Override
        public void onDisabled() {
            if (!ListenerUtil.mutListener.listen(45342)) {
                this.setEnabled(false);
            }
        }

        private void setEnabled(final boolean enabled) {
            if (!ListenerUtil.mutListener.listen(45343)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (webEnableView != null) {
                        webEnableView.setChecked(enabled);
                    }
                });
            }
        }
    };

    private boolean isWebClientEnabled() {
        return sessionService.isEnabled();
    }

    private void startWebClient(boolean start) {
        if (!ListenerUtil.mutListener.listen(45348)) {
            if ((ListenerUtil.mutListener.listen(45344) ? (start || sessionService.getAllSessionModels().size() == 0) : (start && sessionService.getAllSessionModels().size() == 0))) {
                if (!ListenerUtil.mutListener.listen(45346)) {
                    context.startActivity(new Intent(context, SessionsActivity.class));
                }
                if (!ListenerUtil.mutListener.listen(45347)) {
                    dismiss();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(45345)) {
                    sessionService.setEnabled(start);
                }
            }
        }
    }

    public interface ProfileButtonListener {

        void onClicked();
    }
}
