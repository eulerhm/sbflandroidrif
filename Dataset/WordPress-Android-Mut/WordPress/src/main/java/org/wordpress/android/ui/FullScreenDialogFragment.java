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
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.AppBarLayout;
import org.wordpress.android.R;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A {@link DialogFragment} implementing the full-screen dialog pattern defined in the
 * <a href="https://material.io/guidelines/components/dialogs.html#dialogs-full-screen-dialogs">
 * Material Design guidelines</a>.
 */
public class FullScreenDialogFragment extends DialogFragment {

    private Fragment mFragment;

    private FullScreenDialogController mController;

    private OnConfirmListener mOnConfirmListener;

    private OnDismissListener mOnDismissListener;

    private OnShownListener mOnShownListener;

    private String mAction;

    private MenuItem mActionItem;

    private String mSubtitle;

    private String mTitle;

    private Toolbar mToolbar;

    private boolean mHideActivityBar;

    private boolean mIsLiftOnScroll;

    private int mToolbarTheme;

    private int mToolbarColor;

    private static final String ARG_ACTION = "ARG_ACTION";

    private static final String ARG_HIDE_ACTIVITY_BAR = "ARG_IS_LIFT_ON_SCROLL";

    private static final String ARG_IS_LIFT_ON_SCROLL = "ARG_LIFT_ON_SCROLL";

    private static final String ARG_SUBTITLE = "ARG_SUBTITLE";

    private static final String ARG_TITLE = "ARG_TITLE";

    private static final String ARG_TOOLBAR_THEME = "ARG_TOOLBAR_THEME";

    private static final String ARG_TOOLBAR_COLOR = "ARG_TOOLBAR_COLOR";

    private static final int ID_ACTION = 1;

    public static final String TAG = FullScreenDialogFragment.class.getSimpleName();

    public interface FullScreenDialogContent {

        boolean onConfirmClicked(FullScreenDialogController controller);

        boolean onDismissClicked(FullScreenDialogController controller);

        void setController(FullScreenDialogController controller);
    }

    public interface FullScreenDialogController {

        void confirm(@Nullable Bundle result);

        void dismiss();

        void setActionEnabled(boolean enabled);
    }

    public interface OnConfirmListener {

        void onConfirm(@Nullable Bundle result);
    }

    public interface OnDismissListener {

        void onDismiss();
    }

    public interface OnShownListener {

        void onShown();
    }

    protected static FullScreenDialogFragment newInstance(Builder builder) {
        FullScreenDialogFragment dialog = new FullScreenDialogFragment();
        if (!ListenerUtil.mutListener.listen(26227)) {
            dialog.setArguments(setArguments(builder));
        }
        if (!ListenerUtil.mutListener.listen(26228)) {
            dialog.setContent(Fragment.instantiate(builder.mContext, builder.mClass.getName(), builder.mArguments));
        }
        if (!ListenerUtil.mutListener.listen(26229)) {
            dialog.setOnConfirmListener(builder.mOnConfirmListener);
        }
        if (!ListenerUtil.mutListener.listen(26230)) {
            dialog.setOnDismissListener(builder.mOnDismissListener);
        }
        if (!ListenerUtil.mutListener.listen(26231)) {
            dialog.setOnShownListener(builder.mOnShownListener);
        }
        if (!ListenerUtil.mutListener.listen(26232)) {
            dialog.setHideActivityBar(builder.mHideActivityBar);
        }
        if (!ListenerUtil.mutListener.listen(26233)) {
            dialog.setLiftOnScroll(builder.mIsLiftOnScroll);
        }
        return dialog;
    }

    private static Bundle setArguments(Builder builder) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(26234)) {
            bundle.putString(ARG_ACTION, builder.mAction);
        }
        if (!ListenerUtil.mutListener.listen(26235)) {
            bundle.putString(ARG_TITLE, builder.mTitle);
        }
        if (!ListenerUtil.mutListener.listen(26236)) {
            bundle.putString(ARG_SUBTITLE, builder.mSubtitle);
        }
        if (!ListenerUtil.mutListener.listen(26237)) {
            bundle.putInt(ARG_TOOLBAR_THEME, builder.mToolbarTheme);
        }
        if (!ListenerUtil.mutListener.listen(26238)) {
            bundle.putInt(ARG_TOOLBAR_COLOR, builder.mToolbarColor);
        }
        if (!ListenerUtil.mutListener.listen(26239)) {
            bundle.putBoolean(ARG_HIDE_ACTIVITY_BAR, builder.mHideActivityBar);
        }
        if (!ListenerUtil.mutListener.listen(26240)) {
            bundle.putBoolean(ARG_IS_LIFT_ON_SCROLL, builder.mIsLiftOnScroll);
        }
        return bundle;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26241)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26243)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(26242)) {
                    getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.full_screen_dialog_fragment_none, 0, 0, R.anim.full_screen_dialog_fragment_none).add(R.id.full_screen_dialog_fragment_content, mFragment).commitNow();
                }
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26244)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26246)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(26245)) {
                    mFragment = getChildFragmentManager().findFragmentById(R.id.full_screen_dialog_fragment_content);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26251)) {
            mController = new FullScreenDialogController() {

                @Override
                public void confirm(@Nullable Bundle result) {
                    if (!ListenerUtil.mutListener.listen(26247)) {
                        FullScreenDialogFragment.this.confirm(result);
                    }
                }

                @Override
                public void dismiss() {
                    if (!ListenerUtil.mutListener.listen(26248)) {
                        FullScreenDialogFragment.this.dismiss();
                    }
                }

                @Override
                public void setActionEnabled(boolean enabled) {
                    if (!ListenerUtil.mutListener.listen(26250)) {
                        if (mActionItem != null) {
                            if (!ListenerUtil.mutListener.listen(26249)) {
                                mActionItem.setEnabled(enabled);
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
        if (!ListenerUtil.mutListener.listen(26252)) {
            initBuilderArguments();
        }
        Dialog dialog = new Dialog(getActivity(), getTheme()) {

            @Override
            public void onBackPressed() {
                if (!ListenerUtil.mutListener.listen(26253)) {
                    onDismissClicked();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(26254)) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26255)) {
            initBuilderArguments();
        }
        if (!ListenerUtil.mutListener.listen(26257)) {
            if (mHideActivityBar) {
                if (!ListenerUtil.mutListener.listen(26256)) {
                    hideActivityBar();
                }
            }
        }
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.full_screen_dialog_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(26258)) {
            initToolbar(view);
        }
        if (!ListenerUtil.mutListener.listen(26259)) {
            setThemeBackground(view);
        }
        if (!ListenerUtil.mutListener.listen(26260)) {
            view.setFocusableInTouchMode(true);
        }
        if (!ListenerUtil.mutListener.listen(26261)) {
            view.requestFocus();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26262)) {
            ((FullScreenDialogContent) getContent()).setController(mController);
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(26264)) {
            if (mOnDismissListener != null) {
                if (!ListenerUtil.mutListener.listen(26263)) {
                    mOnDismissListener.onDismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26266)) {
            if (mHideActivityBar) {
                if (!ListenerUtil.mutListener.listen(26265)) {
                    showActivityBar();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26267)) {
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    @SuppressLint("CommitTransaction")
    public void show(FragmentManager manager, String tag) {
        if (!ListenerUtil.mutListener.listen(26268)) {
            show(manager.beginTransaction(), tag);
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (!ListenerUtil.mutListener.listen(26269)) {
            initBuilderArguments();
        }
        if (!ListenerUtil.mutListener.listen(26270)) {
            shown();
        }
        if (!ListenerUtil.mutListener.listen(26271)) {
            transaction.setCustomAnimations(R.anim.full_screen_dialog_fragment_slide_up, 0, 0, R.anim.full_screen_dialog_fragment_slide_down);
        }
        return transaction.add(android.R.id.content, this, tag).addToBackStack(null).commit();
    }

    protected void confirm(Bundle result) {
        if (!ListenerUtil.mutListener.listen(26273)) {
            if (mOnConfirmListener != null) {
                if (!ListenerUtil.mutListener.listen(26272)) {
                    mOnConfirmListener.onConfirm(result);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26274)) {
            dismiss();
        }
    }

    protected void shown() {
        if (!ListenerUtil.mutListener.listen(26276)) {
            if (mOnShownListener != null) {
                if (!ListenerUtil.mutListener.listen(26275)) {
                    mOnShownListener.onShown();
                }
            }
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
     * Hide {@link androidx.appcompat.app.AppCompatActivity} bar when showing fullscreen dialog.
     */
    public void hideActivityBar() {
        FragmentActivity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(26280)) {
            if (activity instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(26279)) {
                    if ((ListenerUtil.mutListener.listen(26277) ? (actionBar != null || actionBar.isShowing()) : (actionBar != null && actionBar.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(26278)) {
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
        Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(26281)) {
            mAction = bundle.getString(ARG_ACTION);
        }
        if (!ListenerUtil.mutListener.listen(26282)) {
            mTitle = bundle.getString(ARG_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(26283)) {
            mSubtitle = bundle.getString(ARG_SUBTITLE);
        }
        if (!ListenerUtil.mutListener.listen(26284)) {
            mToolbarTheme = bundle.getInt(ARG_TOOLBAR_THEME);
        }
        if (!ListenerUtil.mutListener.listen(26285)) {
            mToolbarColor = bundle.getInt(ARG_TOOLBAR_COLOR);
        }
        if (!ListenerUtil.mutListener.listen(26286)) {
            mHideActivityBar = bundle.getBoolean(ARG_HIDE_ACTIVITY_BAR);
        }
        if (!ListenerUtil.mutListener.listen(26287)) {
            mIsLiftOnScroll = bundle.getBoolean(ARG_IS_LIFT_ON_SCROLL);
        }
    }

    /**
     * Initialize toolbar title and action.
     *
     * @param view {@link View}
     */
    private void initToolbar(View view) {
        if (!ListenerUtil.mutListener.listen(26288)) {
            mToolbar = view.findViewById(R.id.toolbar_main);
        }
        if (!ListenerUtil.mutListener.listen(26295)) {
            if ((ListenerUtil.mutListener.listen(26293) ? (mToolbarTheme >= 0) : (ListenerUtil.mutListener.listen(26292) ? (mToolbarTheme <= 0) : (ListenerUtil.mutListener.listen(26291) ? (mToolbarTheme < 0) : (ListenerUtil.mutListener.listen(26290) ? (mToolbarTheme != 0) : (ListenerUtil.mutListener.listen(26289) ? (mToolbarTheme == 0) : (mToolbarTheme > 0))))))) {
                if (!ListenerUtil.mutListener.listen(26294)) {
                    mToolbar.getContext().setTheme(mToolbarTheme);
                }
            }
        }
        final Context context = mToolbar.getContext();
        if (!ListenerUtil.mutListener.listen(26296)) {
            mToolbar.setTitle(mTitle);
        }
        if (!ListenerUtil.mutListener.listen(26297)) {
            mToolbar.setSubtitle(mSubtitle);
        }
        if (!ListenerUtil.mutListener.listen(26298)) {
            mToolbar.setNavigationIcon(ColorUtils.applyTintToDrawable(context, R.drawable.ic_close_white_24dp, ContextExtensionsKt.getColorResIdFromAttribute(context, R.attr.colorControlNormal)));
        }
        if (!ListenerUtil.mutListener.listen(26299)) {
            mToolbar.setNavigationContentDescription(R.string.close_dialog_button_desc);
        }
        if (!ListenerUtil.mutListener.listen(26300)) {
            mToolbar.setNavigationOnClickListener(v -> onDismissClicked());
        }
        if (!ListenerUtil.mutListener.listen(26307)) {
            if ((ListenerUtil.mutListener.listen(26305) ? (mToolbarColor >= 0) : (ListenerUtil.mutListener.listen(26304) ? (mToolbarColor <= 0) : (ListenerUtil.mutListener.listen(26303) ? (mToolbarColor < 0) : (ListenerUtil.mutListener.listen(26302) ? (mToolbarColor != 0) : (ListenerUtil.mutListener.listen(26301) ? (mToolbarColor == 0) : (mToolbarColor > 0))))))) {
                if (!ListenerUtil.mutListener.listen(26306)) {
                    mToolbar.setBackgroundColor(getResources().getColor(mToolbarColor));
                }
            }
        }
        AppBarLayout appBarLayout = view.findViewById(R.id.appbar_main);
        if (!ListenerUtil.mutListener.listen(26308)) {
            appBarLayout.setLiftOnScroll(mIsLiftOnScroll);
        }
        if (!ListenerUtil.mutListener.listen(26310)) {
            if (!mIsLiftOnScroll) {
                if (!ListenerUtil.mutListener.listen(26309)) {
                    appBarLayout.setLifted(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26314)) {
            if (!mAction.isEmpty()) {
                Menu menu = mToolbar.getMenu();
                if (!ListenerUtil.mutListener.listen(26311)) {
                    mActionItem = menu.add(0, ID_ACTION, 0, this.mAction);
                }
                if (!ListenerUtil.mutListener.listen(26312)) {
                    mActionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
                if (!ListenerUtil.mutListener.listen(26313)) {
                    mActionItem.setOnMenuItemClickListener(item -> {
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
        if (!ListenerUtil.mutListener.listen(26316)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(26315)) {
                    onDismissClicked();
                }
            }
        }
    }

    protected void onConfirmClicked() {
        boolean isConsumed = ((FullScreenDialogContent) mFragment).onConfirmClicked(mController);
        if (!ListenerUtil.mutListener.listen(26318)) {
            if (!isConsumed) {
                if (!ListenerUtil.mutListener.listen(26317)) {
                    mController.confirm(null);
                }
            }
        }
    }

    protected void onDismissClicked() {
        boolean isConsumed = ((FullScreenDialogContent) mFragment).onDismissClicked(mController);
        if (!ListenerUtil.mutListener.listen(26320)) {
            if (!isConsumed) {
                if (!ListenerUtil.mutListener.listen(26319)) {
                    mController.dismiss();
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
        if (!ListenerUtil.mutListener.listen(26321)) {
            this.mFragment = fragment;
        }
    }

    /**
     * Set flag to hide activity bar when showing fullscreen dialog.
     *
     * @param hide boolean to hide activity bar
     */
    public void setHideActivityBar(boolean hide) {
        if (!ListenerUtil.mutListener.listen(26322)) {
            this.mHideActivityBar = hide;
        }
    }

    /**
     * Set flag to enable or disable AppBar's lift on scroll.
     *
     * @param isLiftOnScroll boolean to toggle lift on scroll
     */
    public void setLiftOnScroll(boolean isLiftOnScroll) {
        if (!ListenerUtil.mutListener.listen(26323)) {
            this.mIsLiftOnScroll = isLiftOnScroll;
        }
    }

    /**
     * Set callback to call when dialog is closed due to confirm click.
     *
     * @param listener {@link OnConfirmListener} interface to call on confirm click
     */
    public void setOnConfirmListener(@Nullable OnConfirmListener listener) {
        if (!ListenerUtil.mutListener.listen(26324)) {
            this.mOnConfirmListener = listener;
        }
    }

    /**
     * Set callback to call when dialog is closed due to dismiss click.
     *
     * @param listener {@link OnDismissListener} interface to call on dismiss click
     */
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        if (!ListenerUtil.mutListener.listen(26325)) {
            this.mOnDismissListener = listener;
        }
    }

    /**
     * Set callback to call when dialog is shown due to shown requested.
     *
     * @param listener {@link OnShownListener} interface to call on shown executed
     */
    public void setOnShownListener(@Nullable OnShownListener listener) {
        if (!ListenerUtil.mutListener.listen(26326)) {
            this.mOnShownListener = listener;
        }
    }

    /**
     * Set {@link FullScreenDialogFragment} subtitle text.
     *
     * @param text {@link String} to set as subtitle text
     */
    public void setSubtitle(@NonNull String text) {
        if (!ListenerUtil.mutListener.listen(26327)) {
            mSubtitle = text;
        }
        if (!ListenerUtil.mutListener.listen(26328)) {
            mToolbar.setSubtitle(mSubtitle);
        }
    }

    /**
     * Set {@link FullScreenDialogFragment} subtitle text.
     *
     * @param textId resource ID to set as subtitle text
     */
    public void setSubtitle(@StringRes int textId) {
        if (!ListenerUtil.mutListener.listen(26331)) {
            if (getContext() != null) {
                if (!ListenerUtil.mutListener.listen(26329)) {
                    mSubtitle = getContext().getString(textId);
                }
                if (!ListenerUtil.mutListener.listen(26330)) {
                    mToolbar.setSubtitle(mSubtitle);
                }
            }
        }
    }

    /**
     * Set theme background for {@link FullScreenDialogFragment} view.
     *
     * @param view {@link View} to set background
     */
    private void setThemeBackground(View view) {
        TypedValue value = new TypedValue();
        if (!ListenerUtil.mutListener.listen(26332)) {
            getActivity().getTheme().resolveAttribute(android.R.attr.windowBackground, value, true);
        }
        if (!ListenerUtil.mutListener.listen(26346)) {
            if ((ListenerUtil.mutListener.listen(26343) ? ((ListenerUtil.mutListener.listen(26337) ? (value.type <= TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26336) ? (value.type > TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26335) ? (value.type < TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26334) ? (value.type != TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26333) ? (value.type == TypedValue.TYPE_FIRST_COLOR_INT) : (value.type >= TypedValue.TYPE_FIRST_COLOR_INT)))))) || (ListenerUtil.mutListener.listen(26342) ? (value.type >= TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26341) ? (value.type > TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26340) ? (value.type < TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26339) ? (value.type != TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26338) ? (value.type == TypedValue.TYPE_LAST_COLOR_INT) : (value.type <= TypedValue.TYPE_LAST_COLOR_INT))))))) : ((ListenerUtil.mutListener.listen(26337) ? (value.type <= TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26336) ? (value.type > TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26335) ? (value.type < TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26334) ? (value.type != TypedValue.TYPE_FIRST_COLOR_INT) : (ListenerUtil.mutListener.listen(26333) ? (value.type == TypedValue.TYPE_FIRST_COLOR_INT) : (value.type >= TypedValue.TYPE_FIRST_COLOR_INT)))))) && (ListenerUtil.mutListener.listen(26342) ? (value.type >= TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26341) ? (value.type > TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26340) ? (value.type < TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26339) ? (value.type != TypedValue.TYPE_LAST_COLOR_INT) : (ListenerUtil.mutListener.listen(26338) ? (value.type == TypedValue.TYPE_LAST_COLOR_INT) : (value.type <= TypedValue.TYPE_LAST_COLOR_INT))))))))) {
                if (!ListenerUtil.mutListener.listen(26345)) {
                    view.setBackgroundColor(value.data);
                }
            } else {
                try {
                    Drawable drawable = ResourcesCompat.getDrawable(getActivity().getResources(), value.resourceId, getActivity().getTheme());
                    if (!ListenerUtil.mutListener.listen(26344)) {
                        ViewCompat.setBackground(view, drawable);
                    }
                } catch (Resources.NotFoundException ignore) {
                }
            }
        }
    }

    /**
     * Show {@link androidx.appcompat.app.AppCompatActivity} bar when hiding fullscreen dialog.
     */
    public void showActivityBar() {
        FragmentActivity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(26350)) {
            if (activity instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(26349)) {
                    if ((ListenerUtil.mutListener.listen(26347) ? (actionBar != null || !actionBar.isShowing()) : (actionBar != null && !actionBar.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(26348)) {
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

        OnConfirmListener mOnConfirmListener;

        OnDismissListener mOnDismissListener;

        OnShownListener mOnShownListener;

        String mAction = "";

        String mSubtitle = "";

        String mTitle = "";

        boolean mHideActivityBar = false;

        boolean mIsLiftOnScroll = true;

        int mToolbarTheme = 0;

        int mToolbarColor = 0;

        /**
         * Builder to construct {@link FullScreenDialogFragment}.
         *
         * @param context {@link Context}
         */
        public Builder(@NonNull Context context) {
            if (!ListenerUtil.mutListener.listen(26351)) {
                this.mContext = context;
            }
        }

        /**
         * Creates {@link FullScreenDialogFragment} with provided parameters.
         *
         * @return {@link FullScreenDialogFragment} instance created
         */
        public FullScreenDialogFragment build() {
            return FullScreenDialogFragment.newInstance(this);
        }

        /**
         * Set {@link FullScreenDialogFragment} action text.
         *
         * @param text {@link String} to set as action text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setAction(@NonNull String text) {
            if (!ListenerUtil.mutListener.listen(26352)) {
                this.mAction = text;
            }
            return this;
        }

        /**
         * Set {@link FullScreenDialogFragment} action text.
         *
         * @param textId resource ID to set as action text
         */
        public Builder setAction(@StringRes int textId) {
            return setAction(mContext.getString(textId));
        }

        /**
         * Set {@link Fragment} to be added as dialog, which must implement {@link FullScreenDialogContent}.
         *
         * @param contentClass     Fragment class to be instantiated
         * @param contentArguments arguments to be added to Fragment
         * @return {@link Builder} object to allow for chaining of calls to set methods
         * @throws IllegalArgumentException if content class does not implement
         *                                  {@link FullScreenDialogContent} interface
         */
        public Builder setContent(Class<? extends Fragment> contentClass, @Nullable Bundle contentArguments) {
            if (!ListenerUtil.mutListener.listen(26353)) {
                if (!FullScreenDialogContent.class.isAssignableFrom(contentClass)) {
                    throw new IllegalArgumentException("The fragment class must implement FullScreenDialogContent interface");
                }
            }
            if (!ListenerUtil.mutListener.listen(26354)) {
                this.mClass = contentClass;
            }
            if (!ListenerUtil.mutListener.listen(26355)) {
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
            if (!ListenerUtil.mutListener.listen(26356)) {
                this.mHideActivityBar = hide;
            }
            return this;
        }

        /**
         * Set {@link FullScreenDialogFragment} subtitle text.
         *
         * @param text {@link String} to set as subtitle text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setSubtitle(@NonNull String text) {
            if (!ListenerUtil.mutListener.listen(26357)) {
                this.mSubtitle = text;
            }
            return this;
        }

        /**
         * Set {@link FullScreenDialogFragment} subtitle text.
         *
         * @param textId resource ID to set as subtitle text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setSubtitle(@StringRes int textId) {
            if (!ListenerUtil.mutListener.listen(26358)) {
                this.mSubtitle = mContext.getString(textId);
            }
            return this;
        }

        /**
         * Set {@link FullScreenDialogFragment} title text.
         *
         * @param text {@link String} to set as title text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setTitle(@NonNull String text) {
            if (!ListenerUtil.mutListener.listen(26359)) {
                this.mTitle = text;
            }
            return this;
        }

        /**
         * Set {@link FullScreenDialogFragment} title text.
         *
         * @param textId resource ID to set as title text
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setTitle(@StringRes int textId) {
            if (!ListenerUtil.mutListener.listen(26360)) {
                this.mTitle = mContext.getString(textId);
            }
            return this;
        }

        public Builder setToolbarTheme(@StyleRes int themeId) {
            if (!ListenerUtil.mutListener.listen(26361)) {
                this.mToolbarTheme = themeId;
            }
            return this;
        }

        /**
         * Set {@link FullScreenDialogFragment} toolbar color.
         *
         * @param colorId resource ID to set as toolbar color
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setToolbarColor(@ColorRes int colorId) {
            if (!ListenerUtil.mutListener.listen(26362)) {
                this.mToolbarColor = colorId;
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
            if (!ListenerUtil.mutListener.listen(26363)) {
                this.mOnConfirmListener = listener;
            }
            return this;
        }

        /**
         * Set callback to call when dialog is closed due to dismiss click.
         *
         * @param listener {@link OnDismissListener} interface to call on dismiss click
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(@Nullable OnDismissListener listener) {
            if (!ListenerUtil.mutListener.listen(26364)) {
                this.mOnDismissListener = listener;
            }
            return this;
        }

        /**
         * Set callback to call when dialog is shown.
         *
         * @param listener {@link OnShownListener} interface to call on shown
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setOnShownListener(@Nullable OnShownListener listener) {
            if (!ListenerUtil.mutListener.listen(26365)) {
                this.mOnShownListener = listener;
            }
            return this;
        }

        /**
         * Set flag to enable or disable AppBar's lift on scroll.
         *
         * @param isLifOnScroll boolean to toggle lift on scroll
         * @return {@link Builder} object to allow for chaining of calls to set methods
         */
        public Builder setIsLifOnScroll(Boolean isLifOnScroll) {
            if (!ListenerUtil.mutListener.listen(26366)) {
                this.mIsLiftOnScroll = isLifOnScroll;
            }
            return this;
        }
    }
}
