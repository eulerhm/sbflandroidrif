package org.wordpress.android.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.elevation.ElevationOverlayProvider;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A {@link DialogFragment} implementing the full-screen dialog pattern defined in the
 * <a href="https://material.io/guidelines/components/dialogs.html#dialogs-full-screen-dialogs">
 * Material Design guidelines</a> with an icon rather than text action.
 */
public class CollapseFullScreenDialogFragment extends DialogFragment {

    private Fragment mFragment;

    private CollapseFullScreenDialogController mController;

    private MenuItem mMenuAction;

    private OnConfirmListener mOnConfirmListener;

    private OnCollapseListener mOnCollapseListener;

    private String mAction;

    private String mTitle;

    private boolean mHideActivityBar;

    private static final String ARG_ACTION = "ARG_ACTION";

    private static final String ARG_HIDE_ACTIVITY_BAR = "ARG_HIDE_ACTIVITY_BAR";

    private static final String ARG_TITLE = "ARG_TITLE";

    private static final int ID_ACTION = 1;

    public static final String TAG = CollapseFullScreenDialogFragment.class.getSimpleName();

    public interface CollapseFullScreenDialogContent {

        boolean onCollapseClicked(CollapseFullScreenDialogController controller);

        boolean onConfirmClicked(CollapseFullScreenDialogController controller);

        void onViewCreated(CollapseFullScreenDialogController controller);
    }

    public interface CollapseFullScreenDialogController {

        void collapse(@Nullable Bundle result);

        void confirm(@Nullable Bundle result);

        void setConfirmEnabled(boolean enabled);
    }

    public interface OnCollapseListener {

        void onCollapse(@Nullable Bundle result);
    }

    public interface OnConfirmListener {

        void onConfirm(@Nullable Bundle result);
    }

    protected static CollapseFullScreenDialogFragment newInstance(Builder builder) {
        CollapseFullScreenDialogFragment dialog = new CollapseFullScreenDialogFragment();
        if (!ListenerUtil.mutListener.listen(25954)) {
            dialog.setArguments(setArguments(builder));
        }
        if (!ListenerUtil.mutListener.listen(25955)) {
            dialog.setContent(Fragment.instantiate(builder.mContext, builder.mClass.getName(), builder.mArguments));
        }
        if (!ListenerUtil.mutListener.listen(25956)) {
            dialog.setOnCollapseListener(builder.mOnCollapseListener);
        }
        if (!ListenerUtil.mutListener.listen(25957)) {
            dialog.setOnConfirmListener(builder.mOnConfirmListener);
        }
        if (!ListenerUtil.mutListener.listen(25958)) {
            dialog.setHideActivityBar(builder.mHideActivityBar);
        }
        return dialog;
    }

    private static Bundle setArguments(Builder builder) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(25959)) {
            bundle.putString(ARG_ACTION, builder.mAction);
        }
        if (!ListenerUtil.mutListener.listen(25960)) {
            bundle.putString(ARG_TITLE, builder.mTitle);
        }
        if (!ListenerUtil.mutListener.listen(25961)) {
            bundle.putBoolean(ARG_HIDE_ACTIVITY_BAR, builder.mHideActivityBar);
        }
        return bundle;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25962)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(25964)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(25963)) {
                    getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.full_screen_dialog_fragment_none, 0, 0, R.anim.full_screen_dialog_fragment_none).add(R.id.full_screen_dialog_fragment_content, mFragment).commitNow();
                }
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25965)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(25967)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(25966)) {
                    mFragment = getChildFragmentManager().findFragmentById(R.id.full_screen_dialog_fragment_content);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25972)) {
            mController = new CollapseFullScreenDialogController() {

                @Override
                public void collapse(@Nullable Bundle result) {
                    if (!ListenerUtil.mutListener.listen(25968)) {
                        CollapseFullScreenDialogFragment.this.collapse(result);
                    }
                }

                @Override
                public void confirm(@Nullable Bundle result) {
                    if (!ListenerUtil.mutListener.listen(25969)) {
                        CollapseFullScreenDialogFragment.this.confirm(result);
                    }
                }

                @Override
                public void setConfirmEnabled(boolean enabled) {
                    if (!ListenerUtil.mutListener.listen(25971)) {
                        if (CollapseFullScreenDialogFragment.this.mMenuAction != null) {
                            if (!ListenerUtil.mutListener.listen(25970)) {
                                CollapseFullScreenDialogFragment.this.mMenuAction.setEnabled(enabled);
                            }
                        }
                    }
                }
            };
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25973)) {
            initBuilderArguments();
        }
        Dialog dialog = new Dialog(requireContext(), getTheme()) {

            @Override
            public void onBackPressed() {
                if (!ListenerUtil.mutListener.listen(25974)) {
                    onCollapseClicked();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(25975)) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25976)) {
            initBuilderArguments();
        }
        if (!ListenerUtil.mutListener.listen(25978)) {
            if (mHideActivityBar) {
                if (!ListenerUtil.mutListener.listen(25977)) {
                    hideActivityBar();
                }
            }
        }
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.collapse_full_screen_dialog_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(25979)) {
            initToolbar(view);
        }
        if (!ListenerUtil.mutListener.listen(25980)) {
            setThemeBackground(view);
        }
        if (!ListenerUtil.mutListener.listen(25981)) {
            view.setFocusableInTouchMode(true);
        }
        if (!ListenerUtil.mutListener.listen(25982)) {
            view.requestFocus();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25983)) {
            ((CollapseFullScreenDialogContent) getContent()).onViewCreated(mController);
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(25985)) {
            if (mHideActivityBar) {
                if (!ListenerUtil.mutListener.listen(25984)) {
                    showActivityBar();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25987)) {
            if (getFragmentManager() != null) {
                if (!ListenerUtil.mutListener.listen(25986)) {
                    getFragmentManager().popBackStackImmediate();
                }
            }
        }
    }

    @Override
    @SuppressLint("CommitTransaction")
    public void show(FragmentManager manager, String tag) {
        if (!ListenerUtil.mutListener.listen(25988)) {
            show(manager.beginTransaction(), tag);
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (!ListenerUtil.mutListener.listen(25989)) {
            initBuilderArguments();
        }
        if (!ListenerUtil.mutListener.listen(25990)) {
            transaction.setCustomAnimations(R.anim.full_screen_dialog_fragment_slide_up, 0, 0, R.anim.full_screen_dialog_fragment_slide_down);
        }
        return transaction.add(android.R.id.content, this, tag).addToBackStack(null).commit();
    }

    protected void collapse(Bundle result) {
        if (!ListenerUtil.mutListener.listen(25992)) {
            if (mOnCollapseListener != null) {
                if (!ListenerUtil.mutListener.listen(25991)) {
                    mOnCollapseListener.onCollapse(result);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25993)) {
            dismiss();
        }
    }

    protected void confirm(Bundle result) {
        if (!ListenerUtil.mutListener.listen(25995)) {
            if (mOnConfirmListener != null) {
                if (!ListenerUtil.mutListener.listen(25994)) {
                    mOnConfirmListener.onConfirm(result);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25996)) {
            dismiss();
        }
    }

    /**
     * Get {@link Fragment} to be able to interact directly with it.
     *
     * @return {@link Fragment} dialog content
     */
    public Fragment getContent() {
        return this.mFragment;
    }

    /**
     * Hide {@link AppCompatActivity} bar when showing fullscreen dialog.
     */
    public void hideActivityBar() {
        FragmentActivity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(26000)) {
            if (activity instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(25999)) {
                    if ((ListenerUtil.mutListener.listen(25997) ? (actionBar != null || actionBar.isShowing()) : (actionBar != null && actionBar.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(25998)) {
                            actionBar.hide();
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialize arguments passed in {@link Builder}.
     */
    private void initBuilderArguments() {
        if (!ListenerUtil.mutListener.listen(26004)) {
            if (getArguments() != null) {
                Bundle bundle = getArguments();
                if (!ListenerUtil.mutListener.listen(26001)) {
                    mAction = bundle.getString(ARG_ACTION);
                }
                if (!ListenerUtil.mutListener.listen(26002)) {
                    mTitle = bundle.getString(ARG_TITLE);
                }
                if (!ListenerUtil.mutListener.listen(26003)) {
                    mHideActivityBar = bundle.getBoolean(ARG_HIDE_ACTIVITY_BAR);
                }
            }
        }
    }

    /**
     * Initialize toolbar title and action.
     *
     * @param view {@link View}
     */
    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.full_screen_dialog_fragment_toolbar);
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(view.getContext());
        float appbarElevation = getResources().getDimension(R.dimen.appbar_elevation);
        int elevatedColor = elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(appbarElevation);
        if (!ListenerUtil.mutListener.listen(26005)) {
            toolbar.setBackgroundColor(elevatedColor);
        }
        if (!ListenerUtil.mutListener.listen(26006)) {
            toolbar.setTitle(mTitle);
        }
        if (!ListenerUtil.mutListener.listen(26007)) {
            toolbar.setNavigationContentDescription(R.string.description_collapse);
        }
        if (!ListenerUtil.mutListener.listen(26008)) {
            toolbar.setNavigationIcon(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_chevron_down_white_24dp));
        }
        if (!ListenerUtil.mutListener.listen(26009)) {
            toolbar.setNavigationOnClickListener(v -> onCollapseClicked());
        }
        if (!ListenerUtil.mutListener.listen(26016)) {
            if (!mAction.isEmpty()) {
                Menu menu = toolbar.getMenu();
                if (!ListenerUtil.mutListener.listen(26010)) {
                    mMenuAction = menu.add(0, ID_ACTION, 0, this.mAction);
                }
                if (!ListenerUtil.mutListener.listen(26011)) {
                    mMenuAction.setIcon(R.drawable.ic_send_white_24dp);
                }
                if (!ListenerUtil.mutListener.listen(26012)) {
                    MenuItemCompat.setIconTintList(mMenuAction, AppCompatResources.getColorStateList(view.getContext(), R.color.primary_neutral_30_selector));
                }
                if (!ListenerUtil.mutListener.listen(26013)) {
                    mMenuAction.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(26014)) {
                    mMenuAction.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
                if (!ListenerUtil.mutListener.listen(26015)) {
                    mMenuAction.setOnMenuItemClickListener(item -> {
                        if (item.getItemId() == ID_ACTION) {
                            onConfirmClicked();
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            }
        }
    }

    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(26018)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(26017)) {
                    onCollapseClicked();
                }
            }
        }
    }

    protected void onConfirmClicked() {
        boolean isConsumed = ((CollapseFullScreenDialogContent) mFragment).onConfirmClicked(mController);
        if (!ListenerUtil.mutListener.listen(26020)) {
            if (!isConsumed) {
                if (!ListenerUtil.mutListener.listen(26019)) {
                    mController.confirm(null);
                }
            }
        }
    }

    protected void onCollapseClicked() {
        boolean isConsumed = ((CollapseFullScreenDialogContent) mFragment).onCollapseClicked(mController);
        if (!ListenerUtil.mutListener.listen(26022)) {
            if (!isConsumed) {
                if (!ListenerUtil.mutListener.listen(26021)) {
                    mController.collapse(null);
                }
            }
        }
    }

    /**
     * Set {@link Fragment} as dialog content.
     *
     * @param fragment {@link Fragment} to set as dialog content
     */
    private void setContent(Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(26023)) {
            this.mFragment = fragment;
        }
    }

    /**
     * Set flag to hide activity bar when showing fullscreen dialog.
     *
     * @param hide boolean to hide activity bar
     */
    public void setHideActivityBar(boolean hide) {
        if (!ListenerUtil.mutListener.listen(26024)) {
            this.mHideActivityBar = hide;
        }
    }

    /**
     * Set callback to call when dialog is closed due to collapse click.
     *
     * @param listener {@link OnCollapseListener} interface to call on collapse click
     */
    public void setOnCollapseListener(@Nullable OnCollapseListener listener) {
        if (!ListenerUtil.mutListener.listen(26025)) {
            this.mOnCollapseListener = listener;
        }
    }

    /**
     * Set callback to call when dialog is closed due to confirm click.
     *
     * @param listener {@link OnConfirmListener} interface to call on confirm click
     */
    public void setOnConfirmListener(@Nullable OnConfirmListener listener) {
        if (!ListenerUtil.mutListener.listen(26026)) {
            this.mOnConfirmListener = listener;
        }
    }

    /**
     * Set theme background for {@link CollapseFullScreenDialogFragment} view.
     *
     * @param view {@link View} to set background
     */
    private void setThemeBackground(View view) {
        TypedValue value = new TypedValue();
        if (!ListenerUtil.mutListener.listen(26027)) {
            requireActivity().getTheme().resolveAttribute(android.R.attr.windowBackground, value, true);
        }
        if (!ListenerUtil.mutListener.listen(26041)) {
            if ((ListenerUtil.mutListener.listen(26038) ? ((ListenerUtil.mutListener.listen(26032) ? (value.type <= TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26031) ? (value.type > TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26030) ? (value.type < TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26029) ? (value.type != TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26028) ? (value.type == TypedValue.TYPE_FIRST_COLOR_INT) : (value.type >= TypedValue.TYPE_FIRST_COLOR_INT)))))) || (ListenerUtil.mutListener.listen(26037) ? (value.type >= TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26036) ? (value.type > TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26035) ? (value.type < TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26034) ? (value.type != TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26033) ? (value.type == TypedValue.TYPE_LAST_COLOR_INT) : (value.type <= TypedValue.TYPE_LAST_COLOR_INT))))))) : ((ListenerUtil.mutListener.listen(26032) ? (value.type <= TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26031) ? (value.type > TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26030) ? (value.type < TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26029) ? (value.type != TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26028) ? (value.type == TypedValue.TYPE_FIRST_COLOR_INT) : (value.type >= TypedValue.TYPE_FIRST_COLOR_INT)))))) && (ListenerUtil.mutListener.listen(26037) ? (value.type >= TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26036) ? (value.type > TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26035) ? (value.type < TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26034) ? (value.type != TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26033) ? (value.type == TypedValue.TYPE_LAST_COLOR_INT) : (value.type <= TypedValue.TYPE_LAST_COLOR_INT))))))))) {
                if (!ListenerUtil.mutListener.listen(26040)) {
                    view.setBackgroundColor(value.data);
                }
            } else {
                try {
                    Drawable drawable = ResourcesCompat.getDrawable(requireActivity().getResources(), value.resourceId, requireActivity().getTheme());
                    if (!ListenerUtil.mutListener.listen(26039)) {
                        ViewCompat.setBackground(view, drawable);
                    }
                } catch (Resources.NotFoundException ignore) {
                }
            }
        }
    }

    /**
     * Show {@link AppCompatActivity} bar when hiding fullscreen dialog.
     */
    public void showActivityBar() {
        FragmentActivity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(26045)) {
            if (activity instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(26044)) {
                    if ((ListenerUtil.mutListener.listen(26042) ? (actionBar != null || !actionBar.isShowing()) : (actionBar != null && !actionBar.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(26043)) {
                            actionBar.show();
                        }
                    }
                }
            }
        }
    }

    public static class Builder {

        Bundle mArguments;

        Class<? extends Fragment> mClass;

        Context mContext;

        OnCollapseListener mOnCollapseListener;

        OnConfirmListener mOnConfirmListener;

        String mAction = "";

        String mTitle = "";

        boolean mHideActivityBar = false;

        /**
         * Builder to construct {@link CollapseFullScreenDialogFragment}.
         *
         * @param context {@link Context}
         */
        public Builder(@NonNull Context context) {
            if (!ListenerUtil.mutListener.listen(26046)) {
                this.mContext = context;
            }
        }

        /**
         * Creates {@link CollapseFullScreenDialogFragment} with provided parameters.
         *
         * @return {@link CollapseFullScreenDialogFragment} instance created
         */
        public CollapseFullScreenDialogFragment build() {
            return CollapseFullScreenDialogFragment.newInstance(this);
        }

        /**
         * Set {@link CollapseFullScreenDialogFragment} action text.
         *
         * @param text {@link String} to set as action text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setAction(@NonNull String text) {
            if (!ListenerUtil.mutListener.listen(26047)) {
                this.mAction = text;
            }
            return this;
        }

        /**
         * Set {@link CollapseFullScreenDialogFragment} action text.
         *
         * @param textId resource ID to set as action text
         */
        public Builder setAction(@StringRes int textId) {
            return setAction(mContext.getString(textId));
        }

        /**
         * Set {@link Fragment} to be added as dialog, which must implement {@link CollapseFullScreenDialogContent}.
         *
         * @param contentClass     Fragment class to be instantiated
         * @param contentArguments arguments to be added to Fragment
         * @return {@link Builder} object to allow for chaining of calls to set methods
         * @throws IllegalArgumentException if content class does not implement
         *                                  {@link CollapseFullScreenDialogContent} interface
         */
        public Builder setContent(Class<? extends Fragment> contentClass, @Nullable Bundle contentArguments) {
            if (!ListenerUtil.mutListener.listen(26048)) {
                if (!CollapseFullScreenDialogContent.class.isAssignableFrom(contentClass)) {
                    throw new IllegalArgumentException("The fragment class must implement CollapseFullScreenDialogContent interface");
                }
            }
            if (!ListenerUtil.mutListener.listen(26049)) {
                this.mClass = contentClass;
            }
            if (!ListenerUtil.mutListener.listen(26050)) {
                this.mArguments = contentArguments;
            }
            return this;
        }

        /**
         * Set flag to hide activity bar when showing fullscreen dialog.
         *
         * @param hide boolean to hide activity bar
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setHideActivityBar(boolean hide) {
            if (!ListenerUtil.mutListener.listen(26051)) {
                this.mHideActivityBar = hide;
            }
            return this;
        }

        /**
         * Set callback to call when dialog is closed due to collapse click.
         *
         * @param listener {@link OnCollapseListener} interface to call on collapse click
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setOnCollapseListener(@Nullable OnCollapseListener listener) {
            if (!ListenerUtil.mutListener.listen(26052)) {
                this.mOnCollapseListener = listener;
            }
            return this;
        }

        /**
         * Set callback to call when dialog is closed due to confirm click.
         *
         * @param listener {@link OnConfirmListener} interface to call on confirm click
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setOnConfirmListener(@Nullable OnConfirmListener listener) {
            if (!ListenerUtil.mutListener.listen(26053)) {
                this.mOnConfirmListener = listener;
            }
            return this;
        }

        /**
         * Set {@link CollapseFullScreenDialogFragment} title text.
         *
         * @param text {@link String} to set as title text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setTitle(@NonNull String text) {
            if (!ListenerUtil.mutListener.listen(26054)) {
                this.mTitle = text;
            }
            return this;
        }

        /**
         * Set {@link CollapseFullScreenDialogFragment} title text.
         *
         * @param textId resource ID to set as title text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setTitle(@StringRes int textId) {
            if (!ListenerUtil.mutListener.listen(26055)) {
                this.mTitle = mContext.getString(textId);
            }
            return this;
        }
    }
}
