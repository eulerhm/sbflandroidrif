package fr.free.nrw.commons.nearby;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.utils.SwipableCardView;
import fr.free.nrw.commons.utils.ViewUtil;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Custom card view for nearby notification card view on main screen, above contributions list
 */
public class NearbyNotificationCardView extends SwipableCardView {

    public Button permissionRequestButton;

    private LinearLayout contentLayout;

    private TextView notificationTitle;

    private TextView notificationDistance;

    private ImageView notificationIcon;

    private ImageView notificationCompass;

    private ProgressBar progressBar;

    public CardViewVisibilityState cardViewVisibilityState;

    public PermissionType permissionType;

    public NearbyNotificationCardView(@NonNull Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3620)) {
            cardViewVisibilityState = CardViewVisibilityState.INVISIBLE;
        }
        if (!ListenerUtil.mutListener.listen(3621)) {
            init();
        }
    }

    public NearbyNotificationCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(3622)) {
            cardViewVisibilityState = CardViewVisibilityState.INVISIBLE;
        }
        if (!ListenerUtil.mutListener.listen(3623)) {
            init();
        }
    }

    public NearbyNotificationCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(3624)) {
            cardViewVisibilityState = CardViewVisibilityState.INVISIBLE;
        }
        if (!ListenerUtil.mutListener.listen(3625)) {
            init();
        }
    }

    /**
     * Initializes views and action listeners
     */
    private void init() {
        View rootView = inflate(getContext(), R.layout.nearby_card_view, this);
        if (!ListenerUtil.mutListener.listen(3626)) {
            permissionRequestButton = rootView.findViewById(R.id.permission_request_button);
        }
        if (!ListenerUtil.mutListener.listen(3627)) {
            contentLayout = rootView.findViewById(R.id.content_layout);
        }
        if (!ListenerUtil.mutListener.listen(3628)) {
            notificationTitle = rootView.findViewById(R.id.nearby_title);
        }
        if (!ListenerUtil.mutListener.listen(3629)) {
            notificationDistance = rootView.findViewById(R.id.nearby_distance);
        }
        if (!ListenerUtil.mutListener.listen(3630)) {
            notificationIcon = rootView.findViewById(R.id.nearby_icon);
        }
        if (!ListenerUtil.mutListener.listen(3631)) {
            notificationCompass = rootView.findViewById(R.id.nearby_compass);
        }
        if (!ListenerUtil.mutListener.listen(3632)) {
            progressBar = rootView.findViewById(R.id.progressBar);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(3633)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(3638)) {
            // If you don't setVisibility after getting layout params, then you will se an empty space in place of nearby NotificationCardView
            if ((ListenerUtil.mutListener.listen(3635) ? ((ListenerUtil.mutListener.listen(3634) ? (getContext() instanceof MainActivity || ((MainActivity) getContext()).defaultKvStore.getBoolean("displayNearbyCardView", true)) : (getContext() instanceof MainActivity && ((MainActivity) getContext()).defaultKvStore.getBoolean("displayNearbyCardView", true))) || this.cardViewVisibilityState == NearbyNotificationCardView.CardViewVisibilityState.READY) : ((ListenerUtil.mutListener.listen(3634) ? (getContext() instanceof MainActivity || ((MainActivity) getContext()).defaultKvStore.getBoolean("displayNearbyCardView", true)) : (getContext() instanceof MainActivity && ((MainActivity) getContext()).defaultKvStore.getBoolean("displayNearbyCardView", true))) && this.cardViewVisibilityState == NearbyNotificationCardView.CardViewVisibilityState.READY))) {
                if (!ListenerUtil.mutListener.listen(3637)) {
                    setVisibility(VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3636)) {
                    setVisibility(GONE);
                }
            }
        }
    }

    private void setActionListeners(Place place) {
        if (!ListenerUtil.mutListener.listen(3639)) {
            this.setOnClickListener(view -> {
                ((MainActivity) getContext()).centerMapToPlace(place);
            });
        }
    }

    @Override
    public boolean onSwipe(View view) {
        if (!ListenerUtil.mutListener.listen(3640)) {
            view.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(3641)) {
            // Save shared preference for nearby card view accordingly
            ((MainActivity) getContext()).defaultKvStore.putBoolean("displayNearbyCardView", false);
        }
        if (!ListenerUtil.mutListener.listen(3642)) {
            ViewUtil.showLongToast(getContext(), getResources().getString(R.string.nearby_notification_dismiss_message));
        }
        return true;
    }

    /**
     * Time is up, data for card view is not ready, so do not display it
     */
    private void errorOccurred() {
        if (!ListenerUtil.mutListener.listen(3643)) {
            this.setVisibility(GONE);
        }
    }

    /**
     * Data for card view is ready, display card view
     */
    private void succeeded() {
        if (!ListenerUtil.mutListener.listen(3644)) {
            this.setVisibility(VISIBLE);
        }
    }

    /**
     * Pass place information to views
     *
     * @param place Closes place where we will get information from
     */
    public void updateContent(Place place) {
        if (!ListenerUtil.mutListener.listen(3645)) {
            Timber.d("Update nearby card notification content");
        }
        if (!ListenerUtil.mutListener.listen(3646)) {
            this.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3647)) {
            cardViewVisibilityState = CardViewVisibilityState.READY;
        }
        if (!ListenerUtil.mutListener.listen(3648)) {
            permissionRequestButton.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(3649)) {
            contentLayout.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3650)) {
            // Make progress bar invisible once data is ready
            progressBar.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(3651)) {
            setActionListeners(place);
        }
        if (!ListenerUtil.mutListener.listen(3652)) {
            // And content views visible since they are ready
            notificationTitle.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3653)) {
            notificationDistance.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3654)) {
            notificationIcon.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3655)) {
            notificationTitle.setText(place.name);
        }
        if (!ListenerUtil.mutListener.listen(3656)) {
            notificationDistance.setText(place.distance);
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        if (!ListenerUtil.mutListener.listen(3657)) {
            super.onVisibilityChanged(changedView, visibility);
        }
        if (!ListenerUtil.mutListener.listen(3676)) {
            if (visibility == VISIBLE) {
                if (!ListenerUtil.mutListener.listen(3675)) {
                    /*
              Sometimes we need to preserve previous state of notification card view without getting
              any data from user. Ie. wen user came back from Media Details fragment to Contrib List
              fragment, we need to know what was the state of card view, and set it to exact same state.
             */
                    switch(cardViewVisibilityState) {
                        case READY:
                            if (!ListenerUtil.mutListener.listen(3658)) {
                                permissionRequestButton.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3659)) {
                                contentLayout.setVisibility(VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3660)) {
                                // Make progress bar invisible once data is ready
                                progressBar.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3661)) {
                                // And content views visible since they are ready
                                notificationTitle.setVisibility(VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3662)) {
                                notificationDistance.setVisibility(VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3663)) {
                                notificationIcon.setVisibility(VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3664)) {
                                notificationCompass.setVisibility(VISIBLE);
                            }
                            break;
                        case LOADING:
                            if (!ListenerUtil.mutListener.listen(3665)) {
                                permissionRequestButton.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3666)) {
                                contentLayout.setVisibility(VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3667)) {
                                // Set visibility of elements in content layout once it become visible
                                progressBar.setVisibility(VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3668)) {
                                notificationTitle.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3669)) {
                                notificationDistance.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3670)) {
                                notificationIcon.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3671)) {
                                notificationCompass.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3672)) {
                                permissionRequestButton.setVisibility(GONE);
                            }
                            break;
                        case ASK_PERMISSION:
                            if (!ListenerUtil.mutListener.listen(3673)) {
                                contentLayout.setVisibility(GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(3674)) {
                                permissionRequestButton.setVisibility(VISIBLE);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * This states will help us to preserve progress bar and content layout states
     */
    public enum CardViewVisibilityState {

        LOADING, READY, INVISIBLE, ASK_PERMISSION, ERROR_OCCURRED
    }

    /**
     * We need to know which kind of permission we need to request, then update permission request
     * button action accordingly
     */
    public enum PermissionType {

        ENABLE_GPS,
        // For only after Marshmallow
        ENABLE_LOCATION_PERMISSION,
        NO_PERMISSION_NEEDED
    }

    /**
     * Rotates the compass arrow in tandem with the rotation of device
     *
     * @param rotateDegree Degree by which device was rotated
     * @param direction Direction in which arrow has to point
     */
    public void rotateCompass(float rotateDegree, float direction) {
        if (!ListenerUtil.mutListener.listen(3681)) {
            notificationCompass.setRotation(-((ListenerUtil.mutListener.listen(3680) ? (rotateDegree % direction) : (ListenerUtil.mutListener.listen(3679) ? (rotateDegree / direction) : (ListenerUtil.mutListener.listen(3678) ? (rotateDegree * direction) : (ListenerUtil.mutListener.listen(3677) ? (rotateDegree + direction) : (rotateDegree - direction)))))));
        }
    }
}
