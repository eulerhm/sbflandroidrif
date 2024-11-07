package org.wordpress.android.ui.plugins;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.PluginActionBuilder;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel;
import org.wordpress.android.fluxc.model.plugin.PluginDirectoryType;
import org.wordpress.android.fluxc.store.PluginStore;
import org.wordpress.android.fluxc.store.PluginStore.ConfigureSitePluginPayload;
import org.wordpress.android.fluxc.store.PluginStore.DeleteSitePluginPayload;
import org.wordpress.android.fluxc.store.PluginStore.InstallSitePluginPayload;
import org.wordpress.android.fluxc.store.PluginStore.OnPluginDirectoryFetched;
import org.wordpress.android.fluxc.store.PluginStore.OnSitePluginConfigured;
import org.wordpress.android.fluxc.store.PluginStore.OnSitePluginDeleted;
import org.wordpress.android.fluxc.store.PluginStore.OnSitePluginInstalled;
import org.wordpress.android.fluxc.store.PluginStore.OnSitePluginUpdated;
import org.wordpress.android.fluxc.store.PluginStore.OnWPOrgPluginFetched;
import org.wordpress.android.fluxc.store.PluginStore.UpdateSitePluginPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.InitiateAutomatedTransferPayload;
import org.wordpress.android.fluxc.store.SiteStore.OnAutomatedTransferEligibilityChecked;
import org.wordpress.android.fluxc.store.SiteStore.OnAutomatedTransferInitiated;
import org.wordpress.android.fluxc.store.SiteStore.OnAutomatedTransferStatusChecked;
import org.wordpress.android.fluxc.store.SiteStore.OnPlansFetched;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.domains.DomainRegistrationActivity;
import org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose;
import org.wordpress.android.ui.posts.BasicFragmentDialog;
import org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.FormatUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.ToastUtils.Duration;
import org.wordpress.android.util.WPLinkMovementMethod;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.widgets.WPSnackbar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.inject.Inject;
import static org.wordpress.android.ui.plans.PlanUtilsKt.isDomainCreditAvailable;
import static org.wordpress.android.util.DomainRegistrationUtilsKt.requestEmailValidation;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PluginDetailActivity extends LocaleAwareActivity implements OnDomainRegistrationRequestedListener, BasicDialogPositiveClickInterface {

    public static final String KEY_PLUGIN_SLUG = "KEY_PLUGIN_SLUG";

    private static final String KEY_IS_CONFIGURING_PLUGIN = "KEY_IS_CONFIGURING_PLUGIN";

    private static final String KEY_IS_INSTALLING_PLUGIN = "KEY_IS_INSTALLING_PLUGIN";

    private static final String KEY_IS_UPDATING_PLUGIN = "KEY_IS_UPDATING_PLUGIN";

    private static final String KEY_IS_REMOVING_PLUGIN = "KEY_IS_REMOVING_PLUGIN";

    private static final String KEY_IS_ACTIVE = "KEY_IS_ACTIVE";

    private static final String KEY_IS_AUTO_UPDATE_ENABLED = "KEY_IS_AUTO_UPDATE_ENABLED";

    private static final String KEY_IS_SHOWING_REMOVE_PLUGIN_CONFIRMATION_DIALOG = "KEY_IS_SHOWING_REMOVE_PLUGIN_CONFIRMATION_DIALOG";

    private static final String KEY_IS_SHOWING_INSTALL_FIRST_PLUGIN_CONFIRMATION_DIALOG = "KEY_IS_SHOWING_INSTALL_FIRST_PLUGIN_CONFIRMATION_DIALOG";

    private static final String KEY_IS_SHOWING_AUTOMATED_TRANSFER_PROGRESS = "KEY_IS_SHOWING_AUTOMATED_TRANSFER_PROGRESS";

    private static final String KEY_IS_SHOWING_DOMAIN_CREDIT_CHECK_PROGRESS = "KEY_IS_SHOWING_DOMAIN_CREDIT_CHECK_PROGRESS";

    private static final String KEY_PLUGIN_RECHECKED_TIMES = "KEY_PLUGIN_RECHECKED_TIMES";

    private static final String TAG_ERROR_DIALOG = "ERROR_DIALOG";

    private static final int MAX_PLUGIN_CHECK_TRIES = 10;

    private static final int DEFAULT_RETRY_DELAY_MS = 3000;

    private static final int PLUGIN_RETRY_DELAY_MS = 10000;

    private SiteModel mSite;

    private String mSlug;

    protected ImmutablePluginModel mPlugin;

    private Handler mHandler;

    private ViewGroup mContainer;

    private TextView mTitleTextView;

    private TextView mByLineTextView;

    private TextView mVersionTopTextView;

    private TextView mVersionBottomTextView;

    private TextView mInstalledText;

    private AppCompatButton mUpdateButton;

    private AppCompatButton mInstallButton;

    private SwitchCompat mSwitchActive;

    private SwitchCompat mSwitchAutoupdates;

    private ProgressDialog mRemovePluginProgressDialog;

    private ProgressDialog mAutomatedTransferProgressDialog;

    private ProgressDialog mCheckingDomainCreditsProgressDialog;

    private CardView mWPOrgPluginDetailsContainer;

    private RelativeLayout mRatingsSectionContainer;

    protected TextView mDescriptionTextView;

    protected ImageView mDescriptionChevron;

    protected TextView mInstallationTextView;

    protected ImageView mInstallationChevron;

    protected TextView mWhatsNewTextView;

    protected ImageView mWhatsNewChevron;

    protected TextView mFaqTextView;

    protected ImageView mFaqChevron;

    private ImageView mImageBanner;

    private ImageView mImageIcon;

    private boolean mIsConfiguringPlugin;

    private boolean mIsInstallingPlugin;

    private boolean mIsUpdatingPlugin;

    private boolean mIsRemovingPlugin;

    protected boolean mIsShowingRemovePluginConfirmationDialog;

    protected boolean mIsShowingInstallFirstPluginConfirmationDialog;

    protected boolean mIsShowingAutomatedTransferProgress;

    private int mPluginReCheckTimer = 0;

    // These flags reflects the UI state
    protected boolean mIsActive;

    protected boolean mIsAutoUpdateEnabled;

    @Inject
    PluginStore mPluginStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ImageManager mImageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10504)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10505)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(10506)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(10511)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(10509)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(10510)) {
                    mSlug = getIntent().getStringExtra(KEY_PLUGIN_SLUG);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10507)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(10508)) {
                    mSlug = savedInstanceState.getString(KEY_PLUGIN_SLUG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10514)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(10512)) {
                    ToastUtils.showToast(this, R.string.blog_not_found);
                }
                if (!ListenerUtil.mutListener.listen(10513)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10515)) {
            refreshPluginFromStore();
        }
        if (!ListenerUtil.mutListener.listen(10518)) {
            if (mPlugin == null) {
                if (!ListenerUtil.mutListener.listen(10516)) {
                    ToastUtils.showToast(this, R.string.plugin_not_found);
                }
                if (!ListenerUtil.mutListener.listen(10517)) {
                    finish();
                }
                return;
            }
        }
        boolean isShowingDomainCreditCheckProgress = false;
        if (!ListenerUtil.mutListener.listen(10533)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(10530)) {
                    mIsActive = mPlugin.isActive();
                }
                if (!ListenerUtil.mutListener.listen(10531)) {
                    mIsAutoUpdateEnabled = mPlugin.isAutoUpdateEnabled();
                }
                if (!ListenerUtil.mutListener.listen(10532)) {
                    // Refresh the wporg plugin which should also fetch fields such as descriptionAsHtml if it's missing
                    mDispatcher.dispatch(PluginActionBuilder.newFetchWporgPluginAction(mSlug));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10519)) {
                    mIsConfiguringPlugin = savedInstanceState.getBoolean(KEY_IS_CONFIGURING_PLUGIN);
                }
                if (!ListenerUtil.mutListener.listen(10520)) {
                    mIsInstallingPlugin = savedInstanceState.getBoolean(KEY_IS_INSTALLING_PLUGIN);
                }
                if (!ListenerUtil.mutListener.listen(10521)) {
                    mIsUpdatingPlugin = savedInstanceState.getBoolean(KEY_IS_UPDATING_PLUGIN);
                }
                if (!ListenerUtil.mutListener.listen(10522)) {
                    mIsRemovingPlugin = savedInstanceState.getBoolean(KEY_IS_REMOVING_PLUGIN);
                }
                if (!ListenerUtil.mutListener.listen(10523)) {
                    mIsActive = savedInstanceState.getBoolean(KEY_IS_ACTIVE);
                }
                if (!ListenerUtil.mutListener.listen(10524)) {
                    mIsAutoUpdateEnabled = savedInstanceState.getBoolean(KEY_IS_AUTO_UPDATE_ENABLED);
                }
                if (!ListenerUtil.mutListener.listen(10525)) {
                    mIsShowingRemovePluginConfirmationDialog = savedInstanceState.getBoolean(KEY_IS_SHOWING_REMOVE_PLUGIN_CONFIRMATION_DIALOG);
                }
                if (!ListenerUtil.mutListener.listen(10526)) {
                    mIsShowingInstallFirstPluginConfirmationDialog = savedInstanceState.getBoolean(KEY_IS_SHOWING_INSTALL_FIRST_PLUGIN_CONFIRMATION_DIALOG);
                }
                if (!ListenerUtil.mutListener.listen(10527)) {
                    mIsShowingAutomatedTransferProgress = savedInstanceState.getBoolean(KEY_IS_SHOWING_AUTOMATED_TRANSFER_PROGRESS);
                }
                if (!ListenerUtil.mutListener.listen(10528)) {
                    isShowingDomainCreditCheckProgress = savedInstanceState.getBoolean(KEY_IS_SHOWING_DOMAIN_CREDIT_CHECK_PROGRESS);
                }
                if (!ListenerUtil.mutListener.listen(10529)) {
                    mPluginReCheckTimer = savedInstanceState.getInt(KEY_PLUGIN_RECHECKED_TIMES, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10534)) {
            setContentView(R.layout.plugin_detail_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(10535)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(10540)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(10536)) {
                    actionBar.setTitle(null);
                }
                if (!ListenerUtil.mutListener.listen(10537)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(10538)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(10539)) {
                    actionBar.setElevation(0);
                }
            }
        }
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(this);
        float appbarElevation = getResources().getDimension(R.dimen.appbar_elevation);
        int elevatedColor = elevationOverlayProvider.compositeOverlayIfNeeded(ContextExtensionsKt.getColorFromAttribute(this, R.attr.wpColorAppBar), appbarElevation);
        if (!ListenerUtil.mutListener.listen(10541)) {
            collapsingToolbarLayout.setContentScrimColor(elevatedColor);
        }
        if (!ListenerUtil.mutListener.listen(10542)) {
            mHandler = new Handler();
        }
        if (!ListenerUtil.mutListener.listen(10543)) {
            setupViews();
        }
        if (!ListenerUtil.mutListener.listen(10546)) {
            if (mIsShowingRemovePluginConfirmationDialog) {
                if (!ListenerUtil.mutListener.listen(10545)) {
                    // Show remove plugin confirmation dialog if it's dismissed while activity is re-created
                    confirmRemovePlugin();
                }
            } else if (mIsRemovingPlugin) {
                if (!ListenerUtil.mutListener.listen(10544)) {
                    // Show remove plugin progress dialog if it's dismissed while activity is re-created
                    showRemovePluginProgressDialog();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10548)) {
            if (mIsShowingInstallFirstPluginConfirmationDialog) {
                if (!ListenerUtil.mutListener.listen(10547)) {
                    confirmInstallPluginForAutomatedTransfer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10550)) {
            if (isShowingDomainCreditCheckProgress) {
                if (!ListenerUtil.mutListener.listen(10549)) {
                    showDomainCreditsCheckProgressDialog();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10552)) {
            if (mIsShowingAutomatedTransferProgress) {
                if (!ListenerUtil.mutListener.listen(10551)) {
                    showAutomatedTransferProgressDialog();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlansFetched(OnPlansFetched event) {
        if (!ListenerUtil.mutListener.listen(10555)) {
            if ((ListenerUtil.mutListener.listen(10553) ? (mCheckingDomainCreditsProgressDialog == null && !mCheckingDomainCreditsProgressDialog.isShowing()) : (mCheckingDomainCreditsProgressDialog == null || !mCheckingDomainCreditsProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(10554)) {
                    AppLog.w(T.PLANS, "User cancelled domain credit checking. Ignoring the result.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10556)) {
            cancelDomainCreditsCheckProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(10566)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10564)) {
                    AppLog.e(T.PLANS, PluginDetailActivity.class.getSimpleName() + ".onPlansFetched: " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(10565)) {
                    WPSnackbar.make(mContainer, getString(R.string.plugin_check_domain_credit_error), Snackbar.LENGTH_LONG).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10560)) {
                    // This should not happen
                    if (event.plans == null) {
                        String errorMessage = "Failed to fetch user Plans. The result is null.";
                        if (!ListenerUtil.mutListener.listen(10557)) {
                            if (BuildConfig.DEBUG) {
                                throw new IllegalStateException(errorMessage);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(10558)) {
                            WPSnackbar.make(mContainer, getString(R.string.plugin_check_domain_credit_error), Snackbar.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(10559)) {
                            AppLog.e(T.PLANS, errorMessage);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(10563)) {
                    if (isDomainCreditAvailable(event.plans)) {
                        if (!ListenerUtil.mutListener.listen(10562)) {
                            showDomainRegistrationDialog();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10561)) {
                            dispatchInstallPluginAction();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDomainRegistrationRequested() {
        if (!ListenerUtil.mutListener.listen(10567)) {
            ActivityLauncher.viewDomainRegistrationActivityForResult(this, mSite, DomainRegistrationPurpose.AUTOMATED_TRANSFER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!ListenerUtil.mutListener.listen(10568)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(10575)) {
            if (requestCode == RequestCodes.DOMAIN_REGISTRATION) {
                if (!ListenerUtil.mutListener.listen(10570)) {
                    if ((ListenerUtil.mutListener.listen(10569) ? (resultCode != Activity.RESULT_OK && isFinishing()) : (resultCode != Activity.RESULT_OK || isFinishing()))) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(10572)) {
                    if (data != null) {
                        String email = data.getStringExtra(DomainRegistrationActivity.RESULT_REGISTERED_DOMAIN_EMAIL);
                        if (!ListenerUtil.mutListener.listen(10571)) {
                            requestEmailValidation(this, email);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10573)) {
                    AnalyticsTracker.track(Stat.AUTOMATED_TRANSFER_CUSTOM_DOMAIN_PURCHASED);
                }
                if (!ListenerUtil.mutListener.listen(10574)) {
                    confirmInstallPluginForAutomatedTransfer();
                }
            }
        }
    }

    @Override
    public void onPositiveClicked(@NotNull String instanceTag) {
    }

    public static class DomainRegistrationPromptDialog extends DialogFragment {

        static final String DOMAIN_REGISTRATION_PROMPT_DIALOG_TAG = "DOMAIN_REGISTRATION_PROMPT_DIALOG";

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
            if (!ListenerUtil.mutListener.listen(10576)) {
                builder.setTitle(R.string.plugin_install_custom_domain_required_dialog_title);
            }
            if (!ListenerUtil.mutListener.listen(10577)) {
                builder.setMessage(R.string.plugin_install_custom_domain_required_dialog_message);
            }
            if (!ListenerUtil.mutListener.listen(10578)) {
                builder.setPositiveButton(R.string.plugin_install_custom_domain_required_dialog_register_btn, (dialogInterface, i) -> {
                    if (isAdded() && getActivity() instanceof OnDomainRegistrationRequestedListener) {
                        ((OnDomainRegistrationRequestedListener) getActivity()).onDomainRegistrationRequested();
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(10579)) {
                builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                });
            }
            if (!ListenerUtil.mutListener.listen(10580)) {
                builder.setCancelable(true);
            }
            return builder.create();
        }
    }

    private void showDomainRegistrationDialog() {
        DialogFragment dialogFragment = new DomainRegistrationPromptDialog();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(10581)) {
            ft.add(dialogFragment, DomainRegistrationPromptDialog.DOMAIN_REGISTRATION_PROMPT_DIALOG_TAG);
        }
        if (!ListenerUtil.mutListener.listen(10582)) {
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(10583)) {
            // would get stuck. This seems to be helping with that.
            cancelRemovePluginProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(10584)) {
            cancelDomainCreditsCheckProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(10585)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(10586)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(10587)) {
            getMenuInflater().inflate(R.menu.plugin_detail, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean showTrash = canPluginBeDisabledOrRemoved();
        if (!ListenerUtil.mutListener.listen(10588)) {
            menu.findItem(R.id.menu_trash).setVisible(showTrash);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(10594)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(10592)) {
                    if (isPluginStateChangedSinceLastConfigurationDispatch()) {
                        if (!ListenerUtil.mutListener.listen(10591)) {
                            // user is leaving the page
                            dispatchConfigurePluginAction(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10593)) {
                    onBackPressed();
                }
                return true;
            } else if (item.getItemId() == R.id.menu_trash) {
                if (!ListenerUtil.mutListener.listen(10590)) {
                    if (NetworkUtils.checkConnection(this)) {
                        if (!ListenerUtil.mutListener.listen(10589)) {
                            confirmRemovePlugin();
                        }
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10595)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(10596)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(10597)) {
            outState.putString(KEY_PLUGIN_SLUG, mSlug);
        }
        if (!ListenerUtil.mutListener.listen(10598)) {
            outState.putBoolean(KEY_IS_CONFIGURING_PLUGIN, mIsConfiguringPlugin);
        }
        if (!ListenerUtil.mutListener.listen(10599)) {
            outState.putBoolean(KEY_IS_INSTALLING_PLUGIN, mIsInstallingPlugin);
        }
        if (!ListenerUtil.mutListener.listen(10600)) {
            outState.putBoolean(KEY_IS_UPDATING_PLUGIN, mIsUpdatingPlugin);
        }
        if (!ListenerUtil.mutListener.listen(10601)) {
            outState.putBoolean(KEY_IS_REMOVING_PLUGIN, mIsRemovingPlugin);
        }
        if (!ListenerUtil.mutListener.listen(10602)) {
            outState.putBoolean(KEY_IS_ACTIVE, mIsActive);
        }
        if (!ListenerUtil.mutListener.listen(10603)) {
            outState.putBoolean(KEY_IS_AUTO_UPDATE_ENABLED, mIsAutoUpdateEnabled);
        }
        if (!ListenerUtil.mutListener.listen(10604)) {
            outState.putBoolean(KEY_IS_SHOWING_REMOVE_PLUGIN_CONFIRMATION_DIALOG, mIsShowingRemovePluginConfirmationDialog);
        }
        if (!ListenerUtil.mutListener.listen(10605)) {
            outState.putBoolean(KEY_IS_SHOWING_INSTALL_FIRST_PLUGIN_CONFIRMATION_DIALOG, mIsShowingInstallFirstPluginConfirmationDialog);
        }
        if (!ListenerUtil.mutListener.listen(10606)) {
            outState.putBoolean(KEY_IS_SHOWING_AUTOMATED_TRANSFER_PROGRESS, mIsShowingAutomatedTransferProgress);
        }
        if (!ListenerUtil.mutListener.listen(10608)) {
            outState.putBoolean(KEY_IS_SHOWING_DOMAIN_CREDIT_CHECK_PROGRESS, (ListenerUtil.mutListener.listen(10607) ? (mCheckingDomainCreditsProgressDialog != null || mCheckingDomainCreditsProgressDialog.isShowing()) : (mCheckingDomainCreditsProgressDialog != null && mCheckingDomainCreditsProgressDialog.isShowing())));
        }
        if (!ListenerUtil.mutListener.listen(10609)) {
            outState.putInt(KEY_PLUGIN_RECHECKED_TIMES, mPluginReCheckTimer);
        }
    }

    private void setupViews() {
        if (!ListenerUtil.mutListener.listen(10610)) {
            mContainer = findViewById(R.id.plugin_detail_container);
        }
        if (!ListenerUtil.mutListener.listen(10611)) {
            mTitleTextView = findViewById(R.id.text_title);
        }
        if (!ListenerUtil.mutListener.listen(10612)) {
            mByLineTextView = findViewById(R.id.text_byline);
        }
        if (!ListenerUtil.mutListener.listen(10613)) {
            mVersionTopTextView = findViewById(R.id.plugin_version_top);
        }
        if (!ListenerUtil.mutListener.listen(10614)) {
            mVersionBottomTextView = findViewById(R.id.plugin_version_bottom);
        }
        if (!ListenerUtil.mutListener.listen(10615)) {
            mInstalledText = findViewById(R.id.plugin_installed);
        }
        if (!ListenerUtil.mutListener.listen(10616)) {
            mUpdateButton = findViewById(R.id.plugin_btn_update);
        }
        if (!ListenerUtil.mutListener.listen(10617)) {
            mInstallButton = findViewById(R.id.plugin_btn_install);
        }
        if (!ListenerUtil.mutListener.listen(10618)) {
            mSwitchActive = findViewById(R.id.plugin_state_active);
        }
        if (!ListenerUtil.mutListener.listen(10619)) {
            mSwitchAutoupdates = findViewById(R.id.plugin_state_autoupdates);
        }
        if (!ListenerUtil.mutListener.listen(10620)) {
            mImageBanner = findViewById(R.id.image_banner);
        }
        if (!ListenerUtil.mutListener.listen(10621)) {
            mImageIcon = findViewById(R.id.image_icon);
        }
        if (!ListenerUtil.mutListener.listen(10622)) {
            mWPOrgPluginDetailsContainer = findViewById(R.id.plugin_wp_org_details_container);
        }
        if (!ListenerUtil.mutListener.listen(10623)) {
            mRatingsSectionContainer = findViewById(R.id.plugin_ratings_section_container);
        }
        if (!ListenerUtil.mutListener.listen(10624)) {
            mDescriptionTextView = findViewById(R.id.plugin_description_text);
        }
        if (!ListenerUtil.mutListener.listen(10625)) {
            mDescriptionChevron = findViewById(R.id.plugin_description_chevron);
        }
        if (!ListenerUtil.mutListener.listen(10626)) {
            findViewById(R.id.plugin_description_container).setOnClickListener(v -> toggleText(mDescriptionTextView, mDescriptionChevron));
        }
        if (!ListenerUtil.mutListener.listen(10627)) {
            mInstallationTextView = findViewById(R.id.plugin_installation_text);
        }
        if (!ListenerUtil.mutListener.listen(10628)) {
            mInstallationChevron = findViewById(R.id.plugin_installation_chevron);
        }
        if (!ListenerUtil.mutListener.listen(10629)) {
            findViewById(R.id.plugin_installation_container).setOnClickListener(v -> toggleText(mInstallationTextView, mInstallationChevron));
        }
        if (!ListenerUtil.mutListener.listen(10630)) {
            mWhatsNewTextView = findViewById(R.id.plugin_whatsnew_text);
        }
        if (!ListenerUtil.mutListener.listen(10631)) {
            mWhatsNewChevron = findViewById(R.id.plugin_whatsnew_chevron);
        }
        if (!ListenerUtil.mutListener.listen(10632)) {
            findViewById(R.id.plugin_whatsnew_container).setOnClickListener(v -> toggleText(mWhatsNewTextView, mWhatsNewChevron));
        }
        if (!ListenerUtil.mutListener.listen(10635)) {
            // this is an installed plugin and there's an update available
            if (mPlugin.isInstalled()) {
                if (!ListenerUtil.mutListener.listen(10634)) {
                    toggleText(mDescriptionTextView, mDescriptionChevron);
                }
            } else if (PluginUtils.isUpdateAvailable(mPlugin)) {
                if (!ListenerUtil.mutListener.listen(10633)) {
                    toggleText(mWhatsNewTextView, mWhatsNewChevron);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10636)) {
            mFaqTextView = findViewById(R.id.plugin_faq_text);
        }
        if (!ListenerUtil.mutListener.listen(10637)) {
            mFaqChevron = findViewById(R.id.plugin_faq_chevron);
        }
        if (!ListenerUtil.mutListener.listen(10638)) {
            findViewById(R.id.plugin_faq_container).setOnClickListener(v -> toggleText(mFaqTextView, mFaqChevron));
        }
        if (!ListenerUtil.mutListener.listen(10639)) {
            findViewById(R.id.plugin_version_layout).setOnClickListener(v -> showPluginInfoPopup());
        }
        if (!ListenerUtil.mutListener.listen(10640)) {
            mSwitchActive.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (compoundButton.isPressed()) {
                    if (NetworkUtils.checkConnection(PluginDetailActivity.this)) {
                        mIsActive = isChecked;
                        dispatchConfigurePluginAction(false);
                    } else {
                        compoundButton.setChecked(mIsActive);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10641)) {
            mSwitchAutoupdates.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (compoundButton.isPressed()) {
                    if (NetworkUtils.checkConnection(PluginDetailActivity.this)) {
                        mIsAutoUpdateEnabled = isChecked;
                        dispatchConfigurePluginAction(false);
                    } else {
                        compoundButton.setChecked(mIsAutoUpdateEnabled);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10642)) {
            mUpdateButton.setOnClickListener(view -> dispatchUpdatePluginAction());
        }
        if (!ListenerUtil.mutListener.listen(10643)) {
            mInstallButton.setOnClickListener(v -> {
                if (isCustomDomainRequired()) {
                    showDomainCreditsCheckProgressDialog();
                    mDispatcher.dispatch(SiteActionBuilder.newFetchPlansAction(mSite));
                } else {
                    dispatchInstallPluginAction();
                }
            });
        }
        View settingsView = findViewById(R.id.plugin_settings_page);
        if (!ListenerUtil.mutListener.listen(10647)) {
            if (canShowSettings()) {
                if (!ListenerUtil.mutListener.listen(10645)) {
                    settingsView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(10646)) {
                    settingsView.setOnClickListener(v -> openUrl(mPlugin.getSettingsUrl()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10644)) {
                    settingsView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10648)) {
            findViewById(R.id.plugin_wp_org_page).setOnClickListener(view -> openUrl(getWpOrgPluginUrl()));
        }
        if (!ListenerUtil.mutListener.listen(10649)) {
            findViewById(R.id.plugin_home_page).setOnClickListener(view -> openUrl(mPlugin.getHomepageUrl()));
        }
        if (!ListenerUtil.mutListener.listen(10650)) {
            findViewById(R.id.read_reviews_container).setOnClickListener(view -> openUrl(getWpOrgReviewsUrl()));
        }
        // set the height of the gradient scrim that appears atop the banner image
        int toolbarHeight = DisplayUtils.getActionBarHeight(this);
        ImageView imgScrim = findViewById(R.id.image_gradient_scrim);
        if (!ListenerUtil.mutListener.listen(10655)) {
            imgScrim.getLayoutParams().height = (ListenerUtil.mutListener.listen(10654) ? (toolbarHeight % 2) : (ListenerUtil.mutListener.listen(10653) ? (toolbarHeight / 2) : (ListenerUtil.mutListener.listen(10652) ? (toolbarHeight - 2) : (ListenerUtil.mutListener.listen(10651) ? (toolbarHeight + 2) : (toolbarHeight * 2)))));
        }
        if (!ListenerUtil.mutListener.listen(10656)) {
            refreshViews();
        }
    }

    private boolean isCustomDomainRequired() {
        return mSite.getUrl().contains(".wordpress.com");
    }

    private void refreshViews() {
        View scrollView = findViewById(R.id.scroll_view);
        if (!ListenerUtil.mutListener.listen(10658)) {
            if (scrollView.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(10657)) {
                    AniUtils.fadeIn(scrollView, AniUtils.Duration.MEDIUM);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10659)) {
            mTitleTextView.setText(mPlugin.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(10660)) {
            mImageManager.load(mImageBanner, ImageType.PHOTO, StringUtils.notNullStr(mPlugin.getBanner()), ScaleType.CENTER_CROP);
        }
        if (!ListenerUtil.mutListener.listen(10661)) {
            mImageManager.load(mImageIcon, ImageType.PLUGIN, StringUtils.notNullStr(mPlugin.getIcon()));
        }
        if (!ListenerUtil.mutListener.listen(10668)) {
            if (mPlugin.doesHaveWPOrgPluginDetails()) {
                if (!ListenerUtil.mutListener.listen(10663)) {
                    mWPOrgPluginDetailsContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(10664)) {
                    setCollapsibleHtmlText(mDescriptionTextView, mPlugin.getDescriptionAsHtml());
                }
                if (!ListenerUtil.mutListener.listen(10665)) {
                    setCollapsibleHtmlText(mInstallationTextView, mPlugin.getInstallationInstructionsAsHtml());
                }
                if (!ListenerUtil.mutListener.listen(10666)) {
                    setCollapsibleHtmlText(mWhatsNewTextView, mPlugin.getWhatsNewAsHtml());
                }
                if (!ListenerUtil.mutListener.listen(10667)) {
                    setCollapsibleHtmlText(mFaqTextView, mPlugin.getFaqAsHtml());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10662)) {
                    mWPOrgPluginDetailsContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10669)) {
            mByLineTextView.setMovementMethod(WPLinkMovementMethod.getInstance());
        }
        if (!ListenerUtil.mutListener.listen(10675)) {
            if (!TextUtils.isEmpty(mPlugin.getAuthorAsHtml())) {
                if (!ListenerUtil.mutListener.listen(10674)) {
                    mByLineTextView.setText(Html.fromHtml(mPlugin.getAuthorAsHtml()));
                }
            } else {
                String authorName = mPlugin.getAuthorName();
                String authorUrl = mPlugin.getAuthorUrl();
                if (!ListenerUtil.mutListener.listen(10673)) {
                    if (TextUtils.isEmpty(authorUrl)) {
                        if (!ListenerUtil.mutListener.listen(10672)) {
                            mByLineTextView.setText(String.format(getString(R.string.plugin_byline), authorName));
                        }
                    } else {
                        String authorLink = "<a href='" + authorUrl + "'>" + authorName + "</a>";
                        String byline = String.format(getString(R.string.plugin_byline), authorLink);
                        if (!ListenerUtil.mutListener.listen(10670)) {
                            mByLineTextView.setMovementMethod(WPLinkMovementMethod.getInstance());
                        }
                        if (!ListenerUtil.mutListener.listen(10671)) {
                            mByLineTextView.setText(Html.fromHtml(byline));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10677)) {
            findViewById(R.id.plugin_card_site).setVisibility((ListenerUtil.mutListener.listen(10676) ? (mPlugin.isInstalled() || isNotAutoManaged()) : (mPlugin.isInstalled() && isNotAutoManaged())) ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(10678)) {
            findViewById(R.id.plugin_state_active_container).setVisibility(canPluginBeDisabledOrRemoved() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(10679)) {
            findViewById(R.id.plugin_state_autoupdates_container).setVisibility(mSite.isAutomatedTransfer() ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(10680)) {
            mSwitchActive.setChecked(mIsActive);
        }
        if (!ListenerUtil.mutListener.listen(10681)) {
            mSwitchAutoupdates.setChecked(mIsAutoUpdateEnabled);
        }
        if (!ListenerUtil.mutListener.listen(10682)) {
            refreshPluginVersionViews();
        }
        if (!ListenerUtil.mutListener.listen(10683)) {
            refreshRatingsViews();
        }
    }

    private void setCollapsibleHtmlText(@NonNull TextView textView, @Nullable String htmlText) {
        if (!ListenerUtil.mutListener.listen(10689)) {
            if (!TextUtils.isEmpty(htmlText)) {
                if (!ListenerUtil.mutListener.listen(10686)) {
                    textView.setTextColor(ContextExtensionsKt.getColorFromAttribute(this, R.attr.colorOnSurface));
                }
                if (!ListenerUtil.mutListener.listen(10687)) {
                    textView.setMovementMethod(WPLinkMovementMethod.getInstance());
                }
                if (!ListenerUtil.mutListener.listen(10688)) {
                    textView.setText(Html.fromHtml(htmlText));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10684)) {
                    textView.setTextColor(ContextExtensionsKt.getColorStateListFromAttribute(this, R.attr.wpColorOnSurfaceMedium));
                }
                if (!ListenerUtil.mutListener.listen(10685)) {
                    textView.setText(R.string.plugin_empty_text);
                }
            }
        }
    }

    private void refreshPluginVersionViews() {
        String pluginVersion = TextUtils.isEmpty(mPlugin.getInstalledVersion()) ? "?" : mPlugin.getInstalledVersion();
        String availableVersion = mPlugin.getWPOrgPluginVersion();
        String versionTopText = "";
        String versionBottomText = "";
        if (!ListenerUtil.mutListener.listen(10696)) {
            if ((ListenerUtil.mutListener.listen(10690) ? (mPlugin.isInstalled() || isNotAutoManaged()) : (mPlugin.isInstalled() && isNotAutoManaged()))) {
                if (!ListenerUtil.mutListener.listen(10695)) {
                    if (PluginUtils.isUpdateAvailable(mPlugin)) {
                        if (!ListenerUtil.mutListener.listen(10693)) {
                            versionTopText = String.format(getString(R.string.plugin_available_version), availableVersion);
                        }
                        if (!ListenerUtil.mutListener.listen(10694)) {
                            versionBottomText = String.format(getString(R.string.plugin_installed_version), pluginVersion);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10692)) {
                            versionTopText = String.format(getString(R.string.plugin_version), pluginVersion);
                        }
                    }
                }
            } else if (!TextUtils.isEmpty(availableVersion)) {
                if (!ListenerUtil.mutListener.listen(10691)) {
                    versionTopText = String.format(getString(R.string.plugin_version), availableVersion);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10697)) {
            mVersionTopTextView.setText(versionTopText);
        }
        if (!ListenerUtil.mutListener.listen(10698)) {
            mVersionBottomTextView.setVisibility(TextUtils.isEmpty(versionBottomText) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(10699)) {
            mVersionBottomTextView.setText(versionBottomText);
        }
        if (!ListenerUtil.mutListener.listen(10700)) {
            refreshUpdateVersionViews();
        }
    }

    private void refreshUpdateVersionViews() {
        if (!ListenerUtil.mutListener.listen(10712)) {
            if (mPlugin.isInstalled()) {
                if (!ListenerUtil.mutListener.listen(10704)) {
                    mInstallButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(10711)) {
                    if (isNotAutoManaged()) {
                        boolean isUpdateAvailable = PluginUtils.isUpdateAvailable(mPlugin);
                        boolean canUpdate = (ListenerUtil.mutListener.listen(10707) ? (isUpdateAvailable || !mIsUpdatingPlugin) : (isUpdateAvailable && !mIsUpdatingPlugin));
                        if (!ListenerUtil.mutListener.listen(10708)) {
                            mUpdateButton.setVisibility(canUpdate ? View.VISIBLE : View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(10710)) {
                            mInstalledText.setVisibility((ListenerUtil.mutListener.listen(10709) ? (isUpdateAvailable && mIsUpdatingPlugin) : (isUpdateAvailable || mIsUpdatingPlugin)) ? View.GONE : View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10705)) {
                            mUpdateButton.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(10706)) {
                            mInstalledText.setVisibility(View.GONE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10701)) {
                    mUpdateButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(10702)) {
                    mInstalledText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(10703)) {
                    mInstallButton.setVisibility(mIsInstallingPlugin ? View.GONE : View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10714)) {
            findViewById(R.id.plugin_update_progress_bar).setVisibility((ListenerUtil.mutListener.listen(10713) ? (mIsUpdatingPlugin && mIsInstallingPlugin) : (mIsUpdatingPlugin || mIsInstallingPlugin)) ? View.VISIBLE : View.GONE);
        }
    }

    private void refreshRatingsViews() {
        if (!ListenerUtil.mutListener.listen(10716)) {
            if (!mPlugin.doesHaveWPOrgPluginDetails()) {
                if (!ListenerUtil.mutListener.listen(10715)) {
                    mRatingsSectionContainer.setVisibility(View.GONE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10717)) {
            mRatingsSectionContainer.setVisibility(View.VISIBLE);
        }
        int numRatingsTotal = mPlugin.getNumberOfRatings();
        TextView txtNumRatings = findViewById(R.id.text_num_ratings);
        String numRatings = FormatUtils.formatInt(numRatingsTotal);
        if (!ListenerUtil.mutListener.listen(10718)) {
            txtNumRatings.setText(String.format(getString(R.string.plugin_num_ratings), numRatings));
        }
        TextView txtNumDownloads = findViewById(R.id.text_num_downloads);
        if (!ListenerUtil.mutListener.listen(10726)) {
            if ((ListenerUtil.mutListener.listen(10723) ? (mPlugin.getDownloadCount() >= 0) : (ListenerUtil.mutListener.listen(10722) ? (mPlugin.getDownloadCount() <= 0) : (ListenerUtil.mutListener.listen(10721) ? (mPlugin.getDownloadCount() < 0) : (ListenerUtil.mutListener.listen(10720) ? (mPlugin.getDownloadCount() != 0) : (ListenerUtil.mutListener.listen(10719) ? (mPlugin.getDownloadCount() == 0) : (mPlugin.getDownloadCount() > 0))))))) {
                String numDownloads = FormatUtils.formatInt(mPlugin.getDownloadCount());
                if (!ListenerUtil.mutListener.listen(10725)) {
                    txtNumDownloads.setText(String.format(getString(R.string.plugin_num_downloads), numDownloads));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10724)) {
                    txtNumDownloads.setText("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10727)) {
            setRatingsProgressBar(R.id.progress5, mPlugin.getNumberOfRatingsOfFive(), numRatingsTotal);
        }
        if (!ListenerUtil.mutListener.listen(10728)) {
            setRatingsProgressBar(R.id.progress4, mPlugin.getNumberOfRatingsOfFour(), numRatingsTotal);
        }
        if (!ListenerUtil.mutListener.listen(10729)) {
            setRatingsProgressBar(R.id.progress3, mPlugin.getNumberOfRatingsOfThree(), numRatingsTotal);
        }
        if (!ListenerUtil.mutListener.listen(10730)) {
            setRatingsProgressBar(R.id.progress2, mPlugin.getNumberOfRatingsOfTwo(), numRatingsTotal);
        }
        if (!ListenerUtil.mutListener.listen(10731)) {
            setRatingsProgressBar(R.id.progress1, mPlugin.getNumberOfRatingsOfOne(), numRatingsTotal);
        }
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        if (!ListenerUtil.mutListener.listen(10732)) {
            ratingBar.setRating(mPlugin.getAverageStarRating());
        }
    }

    private void setRatingsProgressBar(@IdRes int progressResId, int numRatingsForStar, int numRatingsTotal) {
        ProgressBar bar = findViewById(progressResId);
        if (!ListenerUtil.mutListener.listen(10733)) {
            bar.setMax(numRatingsTotal);
        }
        if (!ListenerUtil.mutListener.listen(10734)) {
            bar.setProgress(numRatingsForStar);
        }
    }

    private static final String KEY_LABEL = "label";

    private static final String KEY_TEXT = "text";

    private String timespanFromUpdateDate(@NonNull String lastUpdated) {
        if (!ListenerUtil.mutListener.listen(10740)) {
            // ex: 2017-12-13 2:55pm GMT
            if (lastUpdated.endsWith(" GMT")) {
                if (!ListenerUtil.mutListener.listen(10739)) {
                    lastUpdated = lastUpdated.substring(0, (ListenerUtil.mutListener.listen(10738) ? (lastUpdated.length() % 4) : (ListenerUtil.mutListener.listen(10737) ? (lastUpdated.length() / 4) : (ListenerUtil.mutListener.listen(10736) ? (lastUpdated.length() * 4) : (ListenerUtil.mutListener.listen(10735) ? (lastUpdated.length() + 4) : (lastUpdated.length() - 4))))));
                }
            }
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        try {
            if (!ListenerUtil.mutListener.listen(10741)) {
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            }
            Date date = formatter.parse(lastUpdated);
            return DateTimeUtils.javaDateToTimeSpan(date, this);
        } catch (ParseException var2) {
            return "?";
        }
    }

    protected void showPluginInfoPopup() {
        if (!ListenerUtil.mutListener.listen(10742)) {
            if (!mPlugin.doesHaveWPOrgPluginDetails()) {
                return;
            }
        }
        List<Map<String, String>> data = new ArrayList<>();
        int[] to = { R.id.text1, R.id.text2 };
        String[] from = { KEY_LABEL, KEY_TEXT };
        String[] labels = { getString(R.string.plugin_info_version), getString(R.string.plugin_info_lastupdated), getString(R.string.plugin_info_requires_version), getString(R.string.plugin_info_your_version) };
        Map<String, String> mapVersion = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(10743)) {
            mapVersion.put(KEY_LABEL, labels[0]);
        }
        if (!ListenerUtil.mutListener.listen(10744)) {
            mapVersion.put(KEY_TEXT, StringUtils.notNullStr(mPlugin.getWPOrgPluginVersion()));
        }
        if (!ListenerUtil.mutListener.listen(10745)) {
            data.add(mapVersion);
        }
        Map<String, String> mapUpdated = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(10746)) {
            mapUpdated.put(KEY_LABEL, labels[1]);
        }
        if (!ListenerUtil.mutListener.listen(10747)) {
            mapUpdated.put(KEY_TEXT, timespanFromUpdateDate(StringUtils.notNullStr(mPlugin.getLastUpdatedForWPOrgPlugin())));
        }
        if (!ListenerUtil.mutListener.listen(10748)) {
            data.add(mapUpdated);
        }
        Map<String, String> mapRequiredVer = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(10749)) {
            mapRequiredVer.put(KEY_LABEL, labels[2]);
        }
        if (!ListenerUtil.mutListener.listen(10750)) {
            mapRequiredVer.put(KEY_TEXT, StringUtils.notNullStr(mPlugin.getRequiredWordPressVersion()));
        }
        if (!ListenerUtil.mutListener.listen(10751)) {
            data.add(mapRequiredVer);
        }
        Map<String, String> mapThisVer = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(10752)) {
            mapThisVer.put(KEY_LABEL, labels[3]);
        }
        if (!ListenerUtil.mutListener.listen(10753)) {
            mapThisVer.put(KEY_TEXT, !TextUtils.isEmpty(mSite.getSoftwareVersion()) ? mSite.getSoftwareVersion() : "?");
        }
        if (!ListenerUtil.mutListener.listen(10754)) {
            data.add(mapThisVer);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.plugin_info_row, from, to);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(10755)) {
            builder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(10756)) {
            builder.setAdapter(adapter, (dialog, which) -> dialog.dismiss());
        }
        if (!ListenerUtil.mutListener.listen(10757)) {
            builder.show();
        }
    }

    protected void toggleText(@NonNull final TextView textView, @NonNull ImageView chevron) {
        AniUtils.Duration duration = AniUtils.Duration.SHORT;
        boolean isExpanded = textView.getVisibility() == View.VISIBLE;
        if (!ListenerUtil.mutListener.listen(10760)) {
            if (isExpanded) {
                if (!ListenerUtil.mutListener.listen(10759)) {
                    AniUtils.fadeOut(textView, duration);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10758)) {
                    AniUtils.fadeIn(textView, duration);
                }
            }
        }
        float startRotate = isExpanded ? -180f : 0f;
        float endRotate = isExpanded ? 0f : -180f;
        ObjectAnimator animRotate = ObjectAnimator.ofFloat(chevron, View.ROTATION, startRotate, endRotate);
        if (!ListenerUtil.mutListener.listen(10761)) {
            animRotate.setDuration(duration.toMillis(this));
        }
        if (!ListenerUtil.mutListener.listen(10762)) {
            animRotate.start();
        }
    }

    protected void openUrl(@Nullable String url) {
        if (!ListenerUtil.mutListener.listen(10764)) {
            if (url != null) {
                if (!ListenerUtil.mutListener.listen(10763)) {
                    ActivityLauncher.openUrlExternal(this, url);
                }
            }
        }
    }

    private void confirmRemovePlugin() {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(10765)) {
            builder.setTitle(getResources().getText(R.string.plugin_remove_dialog_title));
        }
        String confirmationMessage = getString(R.string.plugin_remove_dialog_message, mPlugin.getDisplayName(), SiteUtils.getSiteNameOrHomeURL(mSite));
        if (!ListenerUtil.mutListener.listen(10766)) {
            builder.setMessage(confirmationMessage);
        }
        if (!ListenerUtil.mutListener.listen(10767)) {
            builder.setPositiveButton(R.string.remove, (dialogInterface, i) -> {
                mIsShowingRemovePluginConfirmationDialog = false;
                disableAndRemovePlugin();
            });
        }
        if (!ListenerUtil.mutListener.listen(10768)) {
            builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> mIsShowingRemovePluginConfirmationDialog = false);
        }
        if (!ListenerUtil.mutListener.listen(10769)) {
            builder.setOnCancelListener(dialogInterface -> mIsShowingRemovePluginConfirmationDialog = false);
        }
        if (!ListenerUtil.mutListener.listen(10770)) {
            builder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(10771)) {
            builder.create();
        }
        if (!ListenerUtil.mutListener.listen(10772)) {
            mIsShowingRemovePluginConfirmationDialog = true;
        }
        if (!ListenerUtil.mutListener.listen(10773)) {
            builder.show();
        }
    }

    private void showSuccessfulUpdateSnackbar() {
        if (!ListenerUtil.mutListener.listen(10774)) {
            WPSnackbar.make(mContainer, getString(R.string.plugin_updated_successfully, mPlugin.getDisplayName()), Snackbar.LENGTH_LONG).show();
        }
    }

    private void showSuccessfulInstallSnackbar() {
        if (!ListenerUtil.mutListener.listen(10775)) {
            WPSnackbar.make(mContainer, getString(R.string.plugin_installed_successfully, mPlugin.getDisplayName()), Snackbar.LENGTH_LONG).show();
        }
    }

    private void showSuccessfulPluginRemovedSnackbar(String pluginDisplayName) {
        if (!ListenerUtil.mutListener.listen(10776)) {
            WPSnackbar.make(mContainer, getString(R.string.plugin_removed_successfully, pluginDisplayName), Snackbar.LENGTH_LONG).show();
        }
    }

    private void showUpdateFailedSnackbar() {
        if (!ListenerUtil.mutListener.listen(10777)) {
            WPSnackbar.make(mContainer, getString(R.string.plugin_updated_failed, mPlugin.getDisplayName()), Snackbar.LENGTH_LONG).setAction(R.string.retry, view -> dispatchUpdatePluginAction()).show();
        }
    }

    private void showInstallFailedSnackbar() {
        if (!ListenerUtil.mutListener.listen(10778)) {
            WPSnackbar.make(mContainer, getString(R.string.plugin_installed_failed, mPlugin.getDisplayName()), Snackbar.LENGTH_LONG).setAction(R.string.retry, view -> dispatchInstallPluginAction()).show();
        }
    }

    private void showPluginRemoveFailedSnackbar() {
        if (!ListenerUtil.mutListener.listen(10779)) {
            WPSnackbar.make(mContainer, getString(R.string.plugin_remove_failed, mPlugin.getDisplayName()), Snackbar.LENGTH_LONG).show();
        }
    }

    private void showDomainCreditsCheckProgressDialog() {
        if (!ListenerUtil.mutListener.listen(10784)) {
            if (mCheckingDomainCreditsProgressDialog == null) {
                if (!ListenerUtil.mutListener.listen(10780)) {
                    mCheckingDomainCreditsProgressDialog = new ProgressDialog(this);
                }
                if (!ListenerUtil.mutListener.listen(10781)) {
                    mCheckingDomainCreditsProgressDialog.setCancelable(true);
                }
                if (!ListenerUtil.mutListener.listen(10782)) {
                    mCheckingDomainCreditsProgressDialog.setIndeterminate(true);
                }
                if (!ListenerUtil.mutListener.listen(10783)) {
                    mCheckingDomainCreditsProgressDialog.setMessage(getString(R.string.plugin_check_domain_credits_progress_dialog_message));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10786)) {
            if (!mCheckingDomainCreditsProgressDialog.isShowing()) {
                if (!ListenerUtil.mutListener.listen(10785)) {
                    mCheckingDomainCreditsProgressDialog.show();
                }
            }
        }
    }

    private void cancelDomainCreditsCheckProgressDialog() {
        if (!ListenerUtil.mutListener.listen(10789)) {
            if ((ListenerUtil.mutListener.listen(10787) ? (mCheckingDomainCreditsProgressDialog != null || mCheckingDomainCreditsProgressDialog.isShowing()) : (mCheckingDomainCreditsProgressDialog != null && mCheckingDomainCreditsProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(10788)) {
                    mCheckingDomainCreditsProgressDialog.cancel();
                }
            }
        }
    }

    private void showRemovePluginProgressDialog() {
        if (!ListenerUtil.mutListener.listen(10794)) {
            if (mRemovePluginProgressDialog == null) {
                if (!ListenerUtil.mutListener.listen(10790)) {
                    mRemovePluginProgressDialog = new ProgressDialog(this);
                }
                if (!ListenerUtil.mutListener.listen(10791)) {
                    mRemovePluginProgressDialog.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(10792)) {
                    mRemovePluginProgressDialog.setIndeterminate(true);
                }
                // sees that the plugin is disabled, it'd be confusing to say we are disabling the plugin
                String message = mIsActive ? getString(R.string.plugin_disable_progress_dialog_message, mPlugin.getDisplayName()) : getRemovingPluginMessage();
                if (!ListenerUtil.mutListener.listen(10793)) {
                    mRemovePluginProgressDialog.setMessage(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10796)) {
            if (!mRemovePluginProgressDialog.isShowing()) {
                if (!ListenerUtil.mutListener.listen(10795)) {
                    mRemovePluginProgressDialog.show();
                }
            }
        }
    }

    private void cancelRemovePluginProgressDialog() {
        if (!ListenerUtil.mutListener.listen(10799)) {
            if ((ListenerUtil.mutListener.listen(10797) ? (mRemovePluginProgressDialog != null || mRemovePluginProgressDialog.isShowing()) : (mRemovePluginProgressDialog != null && mRemovePluginProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(10798)) {
                    mRemovePluginProgressDialog.cancel();
                }
            }
        }
    }

    protected void dispatchConfigurePluginAction(boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(10800)) {
            if (!NetworkUtils.isNetworkAvailable(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10802)) {
            if ((ListenerUtil.mutListener.listen(10801) ? (!forceUpdate || mIsConfiguringPlugin) : (!forceUpdate && mIsConfiguringPlugin))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10803)) {
            if (!mPlugin.isInstalled()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10804)) {
            mIsConfiguringPlugin = true;
        }
        if (!ListenerUtil.mutListener.listen(10805)) {
            mDispatcher.dispatch(PluginActionBuilder.newConfigureSitePluginAction(new ConfigureSitePluginPayload(mSite, mPlugin.getName(), mPlugin.getSlug(), mIsActive, mIsAutoUpdateEnabled)));
        }
    }

    protected void dispatchUpdatePluginAction() {
        if (!ListenerUtil.mutListener.listen(10806)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10808)) {
            if ((ListenerUtil.mutListener.listen(10807) ? (!PluginUtils.isUpdateAvailable(mPlugin) && mIsUpdatingPlugin) : (!PluginUtils.isUpdateAvailable(mPlugin) || mIsUpdatingPlugin))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10809)) {
            mIsUpdatingPlugin = true;
        }
        if (!ListenerUtil.mutListener.listen(10810)) {
            refreshUpdateVersionViews();
        }
        UpdateSitePluginPayload payload = new UpdateSitePluginPayload(mSite, mPlugin.getName(), mPlugin.getSlug());
        if (!ListenerUtil.mutListener.listen(10811)) {
            mDispatcher.dispatch(PluginActionBuilder.newUpdateSitePluginAction(payload));
        }
    }

    protected void dispatchInstallPluginAction() {
        if (!ListenerUtil.mutListener.listen(10814)) {
            if ((ListenerUtil.mutListener.listen(10813) ? ((ListenerUtil.mutListener.listen(10812) ? (!NetworkUtils.checkConnection(this) && mPlugin.isInstalled()) : (!NetworkUtils.checkConnection(this) || mPlugin.isInstalled())) && mIsInstallingPlugin) : ((ListenerUtil.mutListener.listen(10812) ? (!NetworkUtils.checkConnection(this) && mPlugin.isInstalled()) : (!NetworkUtils.checkConnection(this) || mPlugin.isInstalled())) || mIsInstallingPlugin))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10819)) {
            if (SiteUtils.isNonAtomicBusinessPlanSite(mSite)) {
                if (!ListenerUtil.mutListener.listen(10818)) {
                    confirmInstallPluginForAutomatedTransfer();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10815)) {
                    mIsInstallingPlugin = true;
                }
                if (!ListenerUtil.mutListener.listen(10816)) {
                    refreshUpdateVersionViews();
                }
                InstallSitePluginPayload payload = new InstallSitePluginPayload(mSite, mSlug);
                if (!ListenerUtil.mutListener.listen(10817)) {
                    mDispatcher.dispatch(PluginActionBuilder.newInstallSitePluginAction(payload));
                }
            }
        }
    }

    protected void dispatchRemovePluginAction() {
        if (!ListenerUtil.mutListener.listen(10820)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10821)) {
            mRemovePluginProgressDialog.setMessage(getRemovingPluginMessage());
        }
        DeleteSitePluginPayload payload = new DeleteSitePluginPayload(mSite, mPlugin.getName(), mSlug);
        if (!ListenerUtil.mutListener.listen(10822)) {
            mDispatcher.dispatch(PluginActionBuilder.newDeleteSitePluginAction(payload));
        }
    }

    protected void disableAndRemovePlugin() {
        if (!ListenerUtil.mutListener.listen(10823)) {
            // plugins in certain cases, so we should still make this sanity check
            if (!canPluginBeDisabledOrRemoved()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10824)) {
            // We need to make sure that plugin is disabled before attempting to remove it
            mIsRemovingPlugin = true;
        }
        if (!ListenerUtil.mutListener.listen(10825)) {
            showRemovePluginProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(10826)) {
            mIsActive = false;
        }
        if (!ListenerUtil.mutListener.listen(10827)) {
            dispatchConfigurePluginAction(false);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSitePluginConfigured(OnSitePluginConfigured event) {
        if (!ListenerUtil.mutListener.listen(10828)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10829)) {
            if (!shouldHandleFluxCSitePluginEvent(event.site, event.pluginName)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10830)) {
            mIsConfiguringPlugin = false;
        }
        if (!ListenerUtil.mutListener.listen(10843)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10833)) {
                    // The plugin was already removed in remote, there is no need to show an error to the user
                    if ((ListenerUtil.mutListener.listen(10831) ? (mIsRemovingPlugin || event.error.type == PluginStore.ConfigureSitePluginErrorType.UNKNOWN_PLUGIN) : (mIsRemovingPlugin && event.error.type == PluginStore.ConfigureSitePluginErrorType.UNKNOWN_PLUGIN))) {
                        if (!ListenerUtil.mutListener.listen(10832)) {
                            // plugin is not installed anymore on remote
                            dispatchRemovePluginAction();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(10834)) {
                    ToastUtils.showToast(this, getString(R.string.plugin_configuration_failed, event.error.message));
                }
                if (!ListenerUtil.mutListener.listen(10835)) {
                    // Refresh the UI to plugin's last known state
                    refreshPluginFromStore();
                }
                if (!ListenerUtil.mutListener.listen(10836)) {
                    mIsActive = mPlugin.isActive();
                }
                if (!ListenerUtil.mutListener.listen(10837)) {
                    mIsAutoUpdateEnabled = mPlugin.isAutoUpdateEnabled();
                }
                if (!ListenerUtil.mutListener.listen(10838)) {
                    refreshViews();
                }
                if (!ListenerUtil.mutListener.listen(10842)) {
                    if (mIsRemovingPlugin) {
                        if (!ListenerUtil.mutListener.listen(10839)) {
                            mIsRemovingPlugin = false;
                        }
                        if (!ListenerUtil.mutListener.listen(10840)) {
                            cancelRemovePluginProgressDialog();
                        }
                        if (!ListenerUtil.mutListener.listen(10841)) {
                            showPluginRemoveFailedSnackbar();
                        }
                    }
                }
                return;
            }
        }
        // Sanity check
        ImmutablePluginModel configuredPlugin = mPluginStore.getImmutablePluginBySlug(mSite, mSlug);
        if (!ListenerUtil.mutListener.listen(10846)) {
            if (configuredPlugin == null) {
                if (!ListenerUtil.mutListener.listen(10844)) {
                    ToastUtils.showToast(this, R.string.plugin_not_found);
                }
                if (!ListenerUtil.mutListener.listen(10845)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10848)) {
            // Before refreshing the plugin from store, check the changes and track them
            if (mPlugin.isActive() != configuredPlugin.isActive()) {
                Stat stat = configuredPlugin.isActive() ? Stat.PLUGIN_ACTIVATED : Stat.PLUGIN_DEACTIVATED;
                if (!ListenerUtil.mutListener.listen(10847)) {
                    AnalyticsUtils.trackWithSiteDetails(stat, mSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10850)) {
            if (mPlugin.isAutoUpdateEnabled() != configuredPlugin.isAutoUpdateEnabled()) {
                Stat stat = configuredPlugin.isAutoUpdateEnabled() ? Stat.PLUGIN_AUTOUPDATE_ENABLED : Stat.PLUGIN_AUTOUPDATE_DISABLED;
                if (!ListenerUtil.mutListener.listen(10849)) {
                    AnalyticsUtils.trackWithSiteDetails(stat, mSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10851)) {
            // Now we can update the plugin with the new one from store
            mPlugin = configuredPlugin;
        }
        if (!ListenerUtil.mutListener.listen(10857)) {
            // This might happen either because user changed the state or a remove plugin action has started
            if (isPluginStateChangedSinceLastConfigurationDispatch()) {
                if (!ListenerUtil.mutListener.listen(10856)) {
                    // to make sure UI is reflected correctly in network and DB
                    dispatchConfigurePluginAction(false);
                }
            } else if ((ListenerUtil.mutListener.listen(10852) ? (mIsRemovingPlugin || !mPlugin.isActive()) : (mIsRemovingPlugin && !mPlugin.isActive()))) {
                if (!ListenerUtil.mutListener.listen(10853)) {
                    // We don't want to trigger the remove plugin action before configuration changes are reflected in network
                    dispatchRemovePluginAction();
                }
                if (!ListenerUtil.mutListener.listen(10854)) {
                    // The plugin should be disabled if it was active, we should show that to the user
                    mIsActive = mPlugin.isActive();
                }
                if (!ListenerUtil.mutListener.listen(10855)) {
                    mSwitchActive.setChecked(mIsActive);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWPOrgPluginFetched(OnWPOrgPluginFetched event) {
        if (!ListenerUtil.mutListener.listen(10858)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10859)) {
            if (!mSlug.equals(event.pluginSlug)) {
                // another plugin fetched, no need to handle it
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10863)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10862)) {
                    AppLog.e(T.PLUGINS, "An error occurred while fetching wporg plugin" + event.pluginSlug + " with type: " + event.error.type);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10860)) {
                    refreshPluginFromStore();
                }
                if (!ListenerUtil.mutListener.listen(10861)) {
                    refreshViews();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSitePluginUpdated(OnSitePluginUpdated event) {
        if (!ListenerUtil.mutListener.listen(10864)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10865)) {
            if (!shouldHandleFluxCSitePluginEvent(event.site, event.pluginName)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10866)) {
            mIsUpdatingPlugin = false;
        }
        if (!ListenerUtil.mutListener.listen(10870)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10867)) {
                    AppLog.e(T.PLUGINS, "An error occurred while updating the plugin with type: " + event.error.type + " and message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(10868)) {
                    refreshPluginVersionViews();
                }
                if (!ListenerUtil.mutListener.listen(10869)) {
                    showUpdateFailedSnackbar();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10871)) {
            refreshPluginFromStore();
        }
        if (!ListenerUtil.mutListener.listen(10872)) {
            refreshViews();
        }
        if (!ListenerUtil.mutListener.listen(10873)) {
            showSuccessfulUpdateSnackbar();
        }
        if (!ListenerUtil.mutListener.listen(10874)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.PLUGIN_UPDATED, mSite);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSitePluginInstalled(OnSitePluginInstalled event) {
        if (!ListenerUtil.mutListener.listen(10875)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10877)) {
            if ((ListenerUtil.mutListener.listen(10876) ? (mSite.getId() != event.site.getId() && !mSlug.equals(event.slug)) : (mSite.getId() != event.site.getId() || !mSlug.equals(event.slug)))) {
                // Not the event we are interested in
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10878)) {
            mIsInstallingPlugin = false;
        }
        if (!ListenerUtil.mutListener.listen(10882)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10879)) {
                    AppLog.e(T.PLUGINS, "An error occurred while installing the plugin with type: " + event.error.type + " and message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(10880)) {
                    refreshPluginVersionViews();
                }
                if (!ListenerUtil.mutListener.listen(10881)) {
                    showInstallFailedSnackbar();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10883)) {
            mIsInstallingPlugin = false;
        }
        if (!ListenerUtil.mutListener.listen(10884)) {
            refreshPluginFromStore();
        }
        if (!ListenerUtil.mutListener.listen(10885)) {
            // it'll be successful.
            mIsActive = true;
        }
        if (!ListenerUtil.mutListener.listen(10886)) {
            mIsAutoUpdateEnabled = true;
        }
        if (!ListenerUtil.mutListener.listen(10887)) {
            refreshViews();
        }
        if (!ListenerUtil.mutListener.listen(10888)) {
            showSuccessfulInstallSnackbar();
        }
        if (!ListenerUtil.mutListener.listen(10889)) {
            invalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(10890)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.PLUGIN_INSTALLED, mSite);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSitePluginDeleted(OnSitePluginDeleted event) {
        if (!ListenerUtil.mutListener.listen(10891)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10892)) {
            if (!shouldHandleFluxCSitePluginEvent(event.site, event.pluginName)) {
                return;
            }
        }
        String pluginDisplayName = mPlugin.getDisplayName();
        if (!ListenerUtil.mutListener.listen(10893)) {
            mIsRemovingPlugin = false;
        }
        if (!ListenerUtil.mutListener.listen(10894)) {
            cancelRemovePluginProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(10897)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10895)) {
                    AppLog.e(T.PLUGINS, "An error occurred while removing the plugin with type: " + event.error.type + " and message: " + event.error.message);
                }
                String toastMessage = getString(R.string.plugin_updated_failed_detailed, pluginDisplayName, event.error.message);
                if (!ListenerUtil.mutListener.listen(10896)) {
                    ToastUtils.showToast(this, toastMessage, Duration.LONG);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10898)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.PLUGIN_REMOVED, mSite);
        }
        if (!ListenerUtil.mutListener.listen(10899)) {
            refreshPluginFromStore();
        }
        if (!ListenerUtil.mutListener.listen(10903)) {
            if (mPlugin == null) {
                if (!ListenerUtil.mutListener.listen(10902)) {
                    // A plugin that doesn't exist in the directory is removed, go back to plugin list
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10900)) {
                    // Refresh the views to show wporg plugin details
                    refreshViews();
                }
                if (!ListenerUtil.mutListener.listen(10901)) {
                    invalidateOptionsMenu();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10904)) {
            showSuccessfulPluginRemovedSnackbar(pluginDisplayName);
        }
    }

    // onSitePluginUpdated, onSitePluginDeleted
    private boolean shouldHandleFluxCSitePluginEvent(SiteModel eventSite, String eventPluginName) {
        return (ListenerUtil.mutListener.listen(10907) ? ((ListenerUtil.mutListener.listen(10906) ? ((ListenerUtil.mutListener.listen(10905) ? (// correct site
        mSite.getId() == eventSite.getId() || // needs plugin to be already installed
        mPlugin.isInstalled()) : (// correct site
        mSite.getId() == eventSite.getId() && // needs plugin to be already installed
        mPlugin.isInstalled())) || // sanity check for NPE since if plugin is installed it'll have the name
        mPlugin.getName() != null) : ((ListenerUtil.mutListener.listen(10905) ? (// correct site
        mSite.getId() == eventSite.getId() || // needs plugin to be already installed
        mPlugin.isInstalled()) : (// correct site
        mSite.getId() == eventSite.getId() && // needs plugin to be already installed
        mPlugin.isInstalled())) && // sanity check for NPE since if plugin is installed it'll have the name
        mPlugin.getName() != null)) || // event is for the plugin we are showing
        mPlugin.getName().equals(eventPluginName)) : ((ListenerUtil.mutListener.listen(10906) ? ((ListenerUtil.mutListener.listen(10905) ? (// correct site
        mSite.getId() == eventSite.getId() || // needs plugin to be already installed
        mPlugin.isInstalled()) : (// correct site
        mSite.getId() == eventSite.getId() && // needs plugin to be already installed
        mPlugin.isInstalled())) || // sanity check for NPE since if plugin is installed it'll have the name
        mPlugin.getName() != null) : ((ListenerUtil.mutListener.listen(10905) ? (// correct site
        mSite.getId() == eventSite.getId() || // needs plugin to be already installed
        mPlugin.isInstalled()) : (// correct site
        mSite.getId() == eventSite.getId() && // needs plugin to be already installed
        mPlugin.isInstalled())) && // sanity check for NPE since if plugin is installed it'll have the name
        mPlugin.getName() != null)) && // event is for the plugin we are showing
        mPlugin.getName().equals(eventPluginName)));
    }

    private void refreshPluginFromStore() {
        if (!ListenerUtil.mutListener.listen(10908)) {
            mPlugin = mPluginStore.getImmutablePluginBySlug(mSite, mSlug);
        }
    }

    protected String getWpOrgPluginUrl() {
        return "https://wordpress.org/plugins/" + mSlug;
    }

    protected String getWpOrgReviewsUrl() {
        return "https://wordpress.org/plugins/" + mSlug + "/#reviews";
    }

    private String getRemovingPluginMessage() {
        return getString(R.string.plugin_remove_progress_dialog_message, mPlugin.getDisplayName());
    }

    private boolean canPluginBeDisabledOrRemoved() {
        if (!ListenerUtil.mutListener.listen(10909)) {
            if (!mPlugin.isInstalled()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(10910)) {
            // Disable removing jetpack as the site will stop working in the client
            if (PluginUtils.isJetpack(mPlugin)) {
                return false;
            }
        }
        // Disable removing for auto-managed AT sites
        return isNotAutoManaged();
    }

    // only show settings for active plugins on .org sites
    private boolean canShowSettings() {
        return (ListenerUtil.mutListener.listen(10913) ? ((ListenerUtil.mutListener.listen(10912) ? ((ListenerUtil.mutListener.listen(10911) ? (mPlugin.isInstalled() || isNotAutoManaged()) : (mPlugin.isInstalled() && isNotAutoManaged())) || mPlugin.isActive()) : ((ListenerUtil.mutListener.listen(10911) ? (mPlugin.isInstalled() || isNotAutoManaged()) : (mPlugin.isInstalled() && isNotAutoManaged())) && mPlugin.isActive())) || !TextUtils.isEmpty(mPlugin.getSettingsUrl())) : ((ListenerUtil.mutListener.listen(10912) ? ((ListenerUtil.mutListener.listen(10911) ? (mPlugin.isInstalled() || isNotAutoManaged()) : (mPlugin.isInstalled() && isNotAutoManaged())) || mPlugin.isActive()) : ((ListenerUtil.mutListener.listen(10911) ? (mPlugin.isInstalled() || isNotAutoManaged()) : (mPlugin.isInstalled() && isNotAutoManaged())) && mPlugin.isActive())) && !TextUtils.isEmpty(mPlugin.getSettingsUrl())));
    }

    private boolean isPluginStateChangedSinceLastConfigurationDispatch() {
        if (!ListenerUtil.mutListener.listen(10914)) {
            if (!mPlugin.isInstalled()) {
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(10915) ? (mPlugin.isActive() != mIsActive && mPlugin.isAutoUpdateEnabled() != mIsAutoUpdateEnabled) : (mPlugin.isActive() != mIsActive || mPlugin.isAutoUpdateEnabled() != mIsAutoUpdateEnabled));
    }

    /**
     * Automated Transfer starts by confirming that the user will not be able to use their site. We'll need to block the
     * UI for it, so we get a confirmation first in this step.
     */
    private void confirmInstallPluginForAutomatedTransfer() {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(10916)) {
            builder.setTitle(getResources().getText(R.string.plugin_install_first_plugin_confirmation_dialog_title));
        }
        if (!ListenerUtil.mutListener.listen(10917)) {
            builder.setMessage(R.string.plugin_install_first_plugin_confirmation_dialog_message);
        }
        if (!ListenerUtil.mutListener.listen(10918)) {
            builder.setPositiveButton(R.string.plugin_install_first_plugin_confirmation_dialog_install_btn, (dialogInterface, i) -> {
                mIsShowingInstallFirstPluginConfirmationDialog = false;
                startAutomatedTransfer();
            });
        }
        if (!ListenerUtil.mutListener.listen(10919)) {
            builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_CONFIRM_DIALOG_CANCELLED, mSite);
                mIsShowingInstallFirstPluginConfirmationDialog = false;
            });
        }
        if (!ListenerUtil.mutListener.listen(10920)) {
            builder.setOnCancelListener(dialogInterface -> {
                AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_CONFIRM_DIALOG_CANCELLED, mSite);
                mIsShowingInstallFirstPluginConfirmationDialog = false;
            });
        }
        if (!ListenerUtil.mutListener.listen(10921)) {
            builder.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(10922)) {
            builder.create();
        }
        if (!ListenerUtil.mutListener.listen(10923)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_CONFIRM_DIALOG_SHOWN, mSite);
        }
        if (!ListenerUtil.mutListener.listen(10924)) {
            mIsShowingInstallFirstPluginConfirmationDialog = true;
        }
        if (!ListenerUtil.mutListener.listen(10925)) {
            builder.show();
        }
    }

    /**
     * We'll trigger an eligibility check for the site for Automated Transfer and show a determinate progress bar.
     * Check out `OnAutomatedTransferEligibilityChecked` for its callback.
     */
    private void startAutomatedTransfer() {
        if (!ListenerUtil.mutListener.listen(10926)) {
            AppLog.v(T.PLUGINS, "Starting the Automated Transfer for '" + mSite.getDisplayName() + "' by checking its eligibility");
        }
        if (!ListenerUtil.mutListener.listen(10927)) {
            showAutomatedTransferProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(10928)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_CHECK_ELIGIBILITY, mSite);
        }
        if (!ListenerUtil.mutListener.listen(10929)) {
            mDispatcher.dispatch(SiteActionBuilder.newCheckAutomatedTransferEligibilityAction(mSite));
        }
    }

    /**
     * The reason we are using a blocking progress bar is that if the user changes anything about the site, adds a post,
     * updates site settings etc, it'll be lost when the Automated Transfer is completed. The process takes about 1 min
     * on average, and we'll be able to update the progress by checking the status of the transfer.
     */
    private void showAutomatedTransferProgressDialog() {
        if (!ListenerUtil.mutListener.listen(10935)) {
            if (mAutomatedTransferProgressDialog == null) {
                if (!ListenerUtil.mutListener.listen(10930)) {
                    mAutomatedTransferProgressDialog = new ProgressDialog(this);
                }
                if (!ListenerUtil.mutListener.listen(10931)) {
                    mAutomatedTransferProgressDialog.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(10932)) {
                    mAutomatedTransferProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                }
                if (!ListenerUtil.mutListener.listen(10933)) {
                    mAutomatedTransferProgressDialog.setIndeterminate(false);
                }
                String message = getString(R.string.plugin_install_first_plugin_progress_dialog_message);
                if (!ListenerUtil.mutListener.listen(10934)) {
                    mAutomatedTransferProgressDialog.setMessage(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10938)) {
            if (!mAutomatedTransferProgressDialog.isShowing()) {
                if (!ListenerUtil.mutListener.listen(10936)) {
                    mIsShowingAutomatedTransferProgress = true;
                }
                if (!ListenerUtil.mutListener.listen(10937)) {
                    mAutomatedTransferProgressDialog.show();
                }
            }
        }
    }

    /**
     * Either Automated Transfer is completed or an error occurred.
     */
    private void cancelAutomatedTransferDialog() {
        if (!ListenerUtil.mutListener.listen(10942)) {
            if ((ListenerUtil.mutListener.listen(10939) ? (mAutomatedTransferProgressDialog != null || mAutomatedTransferProgressDialog.isShowing()) : (mAutomatedTransferProgressDialog != null && mAutomatedTransferProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(10940)) {
                    mAutomatedTransferProgressDialog.cancel();
                }
                if (!ListenerUtil.mutListener.listen(10941)) {
                    mIsShowingAutomatedTransferProgress = false;
                }
            }
        }
    }

    /**
     * Automated Transfer successfully completed, the site has been refreshed and site plugins has been fetched. We can
     * close the progress dialog, get the new version of the plugin from Store and refresh the views
     */
    private void automatedTransferCompleted() {
        if (!ListenerUtil.mutListener.listen(10943)) {
            AppLog.v(T.PLUGINS, "Automated Transfer successfully completed!");
        }
        if (!ListenerUtil.mutListener.listen(10944)) {
            AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_FLOW_COMPLETE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(10945)) {
            cancelAutomatedTransferDialog();
        }
        if (!ListenerUtil.mutListener.listen(10946)) {
            refreshPluginFromStore();
        }
        if (!ListenerUtil.mutListener.listen(10947)) {
            dispatchConfigurePluginAction(true);
        }
        if (!ListenerUtil.mutListener.listen(10948)) {
            refreshViews();
        }
        if (!ListenerUtil.mutListener.listen(10949)) {
            showSuccessfulInstallSnackbar();
        }
        if (!ListenerUtil.mutListener.listen(10950)) {
            invalidateOptionsMenu();
        }
    }

    /**
     * Helper for if any of the FluxC Automated Transfer events fail. We are using a Toast for now, but the only likely
     * error is the site missing a domain which will be implemented later on and will be handled differently.
     */
    private void handleAutomatedTransferFailed(String errorMessage) {
        if (!ListenerUtil.mutListener.listen(10951)) {
            cancelAutomatedTransferDialog();
        }
        BasicFragmentDialog errorDialog = new BasicFragmentDialog();
        if (!ListenerUtil.mutListener.listen(10952)) {
            errorDialog.initialize(TAG_ERROR_DIALOG, null, errorMessage, getString(R.string.dialog_button_ok), null, null);
        }
        if (!ListenerUtil.mutListener.listen(10953)) {
            errorDialog.show(getSupportFragmentManager(), TAG_ERROR_DIALOG);
        }
    }

    /**
     * This is the first Automated Transfer FluxC event. It returns whether the site is eligible or not with a set of
     * errors for why it's not eligible. We are handling a single error at a time, but all the likely errors should be
     * pre-handled by preventing the access of plugins page.
     * <p>
     * If the site is eligible, we'll initiate the Automated Transfer. Check out `onAutomatedTransferInitiated` for next
     * step.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAutomatedTransferEligibilityChecked(OnAutomatedTransferEligibilityChecked event) {
        if (!ListenerUtil.mutListener.listen(10954)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10967)) {
            // error code, so we'll show the generic error message.
            if (!event.isEligible) {
                if (!ListenerUtil.mutListener.listen(10958)) {
                    AppLog.e(T.PLUGINS, "Automated Transfer has failed because the site is not eligible!");
                }
                if (!ListenerUtil.mutListener.listen(10959)) {
                    AppLog.e(T.PLUGINS, "Eligibility error codes: " + event.eligibilityErrorCodes);
                }
                if (!ListenerUtil.mutListener.listen(10961)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(10960)) {
                            // error codes.
                            AppLog.e(T.PLUGINS, "Eligibility API error with type: " + event.error.type + " and message: " + event.error.message);
                        }
                    }
                }
                String errorCode = event.eligibilityErrorCodes.isEmpty() ? "" : event.eligibilityErrorCodes.get(0);
                if (!ListenerUtil.mutListener.listen(10966)) {
                    if (errorCode.equalsIgnoreCase("transfer_already_exists")) {
                        if (!ListenerUtil.mutListener.listen(10964)) {
                            AppLog.v(T.PLUGINS, "Automated Transfer eligibility check resulted in `transfer_already_exists` " + "error, checking its status...");
                        }
                        if (!ListenerUtil.mutListener.listen(10965)) {
                            mDispatcher.dispatch(SiteActionBuilder.newCheckAutomatedTransferStatusAction(mSite));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10962)) {
                            AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_NOT_ELIGIBLE, mSite);
                        }
                        if (!ListenerUtil.mutListener.listen(10963)) {
                            handleAutomatedTransferFailed(getEligibilityErrorMessage(errorCode));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10955)) {
                    AppLog.v(T.PLUGINS, "The site is eligible for Automated Transfer. Initiating the transfer...");
                }
                if (!ListenerUtil.mutListener.listen(10956)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_INITIATE, mSite);
                }
                if (!ListenerUtil.mutListener.listen(10957)) {
                    mDispatcher.dispatch(SiteActionBuilder.newInitiateAutomatedTransferAction(new InitiateAutomatedTransferPayload(mSite, mSlug)));
                }
            }
        }
    }

    /**
     * After we check the eligibility of a site, the Automated Transfer will be initiated. This is its callback and it
     * should be a fairly quick one, that's why we are not updating the progress bar. The event contains the plugin that
     * will be installed after Automated Transfer is completed, but we don't need to handle anything about that.
     * <p>
     * We don't know if there is any specific errors we might need to handle, so we are just showing a message about it
     * for now.
     * <p>
     * Once the transfer is initiated, we need to start checking the status of it. Check out
     * `onAutomatedTransferStatusChecked` for the callback.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAutomatedTransferInitiated(OnAutomatedTransferInitiated event) {
        if (!ListenerUtil.mutListener.listen(10968)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10975)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10972)) {
                    AppLog.e(T.PLUGINS, "Automated Transfer failed during initiation with error type " + event.error.type + " and message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(10973)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_INITIATION_FAILED, mSite);
                }
                if (!ListenerUtil.mutListener.listen(10974)) {
                    handleAutomatedTransferFailed(event.error.message);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10969)) {
                    AppLog.v(T.PLUGINS, "Automated Transfer is successfully initiated. Checking the status of it...");
                }
                if (!ListenerUtil.mutListener.listen(10970)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_INITIATED, mSite);
                }
                if (!ListenerUtil.mutListener.listen(10971)) {
                    mDispatcher.dispatch(SiteActionBuilder.newCheckAutomatedTransferStatusAction(mSite));
                }
            }
        }
    }

    /**
     * After Automated Transfer is initiated, we'll need to check for the status of it several times as the process
     * takes about 1 minute on average. We don't know if there are any specific errors we can handle, so for now we are
     * simply showing the message.
     * <p>
     * We'll get an `isCompleted` flag from the event and when that's `true` we'll need to re-fetch the site. It'll
     * become a Jetpack site at that point and we'll need the updated site to be able to fetch the plugins and refresh
     * this page. If the transfer is not completed, we use the current step and total steps to update the progress bar
     * and check the status again after waiting for a second.
     * <p>
     * Unfortunately we can't close the progress dialog until both the site and its plugins are fetched. Check out
     * `onSiteChanged` for the next step.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAutomatedTransferStatusChecked(OnAutomatedTransferStatusChecked event) {
        if (!ListenerUtil.mutListener.listen(10976)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10997)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(10994)) {
                    AppLog.e(T.PLUGINS, "Automated Transfer failed after initiation with error type " + event.error.type + " and message: " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(10995)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_STATUS_FAILED, mSite);
                }
                if (!ListenerUtil.mutListener.listen(10996)) {
                    handleAutomatedTransferFailed(event.error.message);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10993)) {
                    if (event.isCompleted) {
                        if (!ListenerUtil.mutListener.listen(10988)) {
                            AppLog.v(T.PLUGINS, "Automated Transfer is successfully completed. Fetching the site...");
                        }
                        if (!ListenerUtil.mutListener.listen(10989)) {
                            // and its plugins
                            mAutomatedTransferProgressDialog.setProgress(99);
                        }
                        if (!ListenerUtil.mutListener.listen(10990)) {
                            mAutomatedTransferProgressDialog.setMessage(getString(R.string.plugin_install_first_plugin_almost_finished_dialog_message));
                        }
                        if (!ListenerUtil.mutListener.listen(10991)) {
                            AnalyticsUtils.trackWithSiteDetails(Stat.AUTOMATED_TRANSFER_STATUS_COMPLETE, mSite);
                        }
                        if (!ListenerUtil.mutListener.listen(10992)) {
                            mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(mSite));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10977)) {
                            AppLog.v(T.PLUGINS, "Automated Transfer is still in progress: " + event.currentStep + "/" + event.totalSteps);
                        }
                        if (!ListenerUtil.mutListener.listen(10986)) {
                            mAutomatedTransferProgressDialog.setProgress((ListenerUtil.mutListener.listen(10985) ? ((ListenerUtil.mutListener.listen(10981) ? (event.currentStep % 100) : (ListenerUtil.mutListener.listen(10980) ? (event.currentStep / 100) : (ListenerUtil.mutListener.listen(10979) ? (event.currentStep - 100) : (ListenerUtil.mutListener.listen(10978) ? (event.currentStep + 100) : (event.currentStep * 100))))) % event.totalSteps) : (ListenerUtil.mutListener.listen(10984) ? ((ListenerUtil.mutListener.listen(10981) ? (event.currentStep % 100) : (ListenerUtil.mutListener.listen(10980) ? (event.currentStep / 100) : (ListenerUtil.mutListener.listen(10979) ? (event.currentStep - 100) : (ListenerUtil.mutListener.listen(10978) ? (event.currentStep + 100) : (event.currentStep * 100))))) * event.totalSteps) : (ListenerUtil.mutListener.listen(10983) ? ((ListenerUtil.mutListener.listen(10981) ? (event.currentStep % 100) : (ListenerUtil.mutListener.listen(10980) ? (event.currentStep / 100) : (ListenerUtil.mutListener.listen(10979) ? (event.currentStep - 100) : (ListenerUtil.mutListener.listen(10978) ? (event.currentStep + 100) : (event.currentStep * 100))))) - event.totalSteps) : (ListenerUtil.mutListener.listen(10982) ? ((ListenerUtil.mutListener.listen(10981) ? (event.currentStep % 100) : (ListenerUtil.mutListener.listen(10980) ? (event.currentStep / 100) : (ListenerUtil.mutListener.listen(10979) ? (event.currentStep - 100) : (ListenerUtil.mutListener.listen(10978) ? (event.currentStep + 100) : (event.currentStep * 100))))) + event.totalSteps) : ((ListenerUtil.mutListener.listen(10981) ? (event.currentStep % 100) : (ListenerUtil.mutListener.listen(10980) ? (event.currentStep / 100) : (ListenerUtil.mutListener.listen(10979) ? (event.currentStep - 100) : (ListenerUtil.mutListener.listen(10978) ? (event.currentStep + 100) : (event.currentStep * 100))))) / event.totalSteps))))));
                        }
                        if (!ListenerUtil.mutListener.listen(10987)) {
                            mHandler.postDelayed(() -> {
                                AppLog.v(T.PLUGINS, "Checking the Automated Transfer status...");
                                // Wait 3 seconds before checking the status again
                                mDispatcher.dispatch(SiteActionBuilder.newCheckAutomatedTransferStatusAction(mSite));
                            }, DEFAULT_RETRY_DELAY_MS);
                        }
                    }
                }
            }
        }
    }

    /**
     * Once the Automated Transfer is completed, we'll trigger a fetch for the site since it'll become a Jetpack site.
     * Whenever the site is updated we update `mSite` property. If the Automated Transfer progress dialog is
     * showing and we make sure that the updated site has the correct `isAutomatedTransfer` flag, we fetch the site
     * plugins so we can refresh this page.
     * <p>
     * Check out `onPluginDirectoryFetched` for the last step of a successful Automated Transfer.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteChanged(OnSiteChanged event) {
        if (!ListenerUtil.mutListener.listen(10998)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11001)) {
            if (!event.isError()) {
                if (!ListenerUtil.mutListener.listen(11000)) {
                    mSite = mSiteStore.getSiteBySiteId(mSite.getSiteId());
                }
            } else if (mIsShowingAutomatedTransferProgress) {
                if (!ListenerUtil.mutListener.listen(10999)) {
                    AppLog.e(T.PLUGINS, "Fetching the site after Automated Transfer has failed with error type " + event.error.type + " and message: " + event.error.message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11006)) {
            if (mIsShowingAutomatedTransferProgress) {
                if (!ListenerUtil.mutListener.listen(11005)) {
                    // we are still showing the AT progress and the site is AT site, we can continue with plugins fetch
                    if (mSite.isAutomatedTransfer()) {
                        if (!ListenerUtil.mutListener.listen(11003)) {
                            AppLog.v(T.PLUGINS, "Site is successfully fetched after Automated Transfer, fetching" + " the site plugins to complete the process...");
                        }
                        if (!ListenerUtil.mutListener.listen(11004)) {
                            fetchPluginDirectory(0);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11002)) {
                            // start from the my site page and the site will be refreshed.
                            mHandler.postDelayed(() -> {
                                AppLog.v(T.PLUGINS, "Fetching the site again after Automated Transfer since the changes " + "are not yet reflected");
                                // Wait 3 seconds before fetching the site again
                                mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(mSite));
                            }, DEFAULT_RETRY_DELAY_MS);
                        }
                    }
                }
            }
        }
    }

    /**
     * Completing an Automated Transfer will trigger a site fetch which then will trigger a fetch for the site plugins.
     * We'll complete the Automated Transfer if the progress dialog is showing and only update the plugin and the views
     * if it's not.
     * <p>
     * This event is unlikely to happen outside of Automated Transfer process, and it is even less likely that the views
     * will need to be updated because of it, but they are both still possible and we try to handle it with a refresh.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPluginDirectoryFetched(OnPluginDirectoryFetched event) {
        if (!ListenerUtil.mutListener.listen(11007)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11008)) {
            refreshPluginFromStore();
        }
        if (!ListenerUtil.mutListener.listen(11024)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(11023)) {
                    if (mIsShowingAutomatedTransferProgress) {
                        if (!ListenerUtil.mutListener.listen(11020)) {
                            AppLog.e(T.PLUGINS, "Fetching the plugin directory after Automated Transfer has failed with error type" + event.error.type + " and message: " + event.error.message);
                        }
                        if (!ListenerUtil.mutListener.listen(11021)) {
                            // This should hopefully be an edge case and fetching the plugins again should
                            AppLog.v(T.PLUGINS, "Fetching the site plugins again after Automated Transfer since the" + " changes are not yet reflected");
                        }
                        if (!ListenerUtil.mutListener.listen(11022)) {
                            fetchPluginDirectory(PLUGIN_RETRY_DELAY_MS);
                        }
                    }
                }
                // one triggered in this page and only one we care about.
                return;
            } else if (!mPlugin.isInstalled()) {
                if (!ListenerUtil.mutListener.listen(11019)) {
                    // Automated Transfer is performed right after domain registration
                    if (mIsShowingAutomatedTransferProgress) {
                        if (!ListenerUtil.mutListener.listen(11018)) {
                            if ((ListenerUtil.mutListener.listen(11013) ? (mPluginReCheckTimer >= MAX_PLUGIN_CHECK_TRIES) : (ListenerUtil.mutListener.listen(11012) ? (mPluginReCheckTimer <= MAX_PLUGIN_CHECK_TRIES) : (ListenerUtil.mutListener.listen(11011) ? (mPluginReCheckTimer > MAX_PLUGIN_CHECK_TRIES) : (ListenerUtil.mutListener.listen(11010) ? (mPluginReCheckTimer != MAX_PLUGIN_CHECK_TRIES) : (ListenerUtil.mutListener.listen(11009) ? (mPluginReCheckTimer == MAX_PLUGIN_CHECK_TRIES) : (mPluginReCheckTimer < MAX_PLUGIN_CHECK_TRIES))))))) {
                                if (!ListenerUtil.mutListener.listen(11015)) {
                                    AppLog.v(T.PLUGINS, "Targeted plugin is not marked as installed after Automated Transfer." + " Fetching the site plugins to reflect the changes.");
                                }
                                if (!ListenerUtil.mutListener.listen(11016)) {
                                    fetchPluginDirectory(PLUGIN_RETRY_DELAY_MS);
                                }
                                if (!ListenerUtil.mutListener.listen(11017)) {
                                    mPluginReCheckTimer++;
                                }
                                return;
                            } else {
                                if (!ListenerUtil.mutListener.listen(11014)) {
                                    // finish Automated Transfer
                                    ToastUtils.showToast(this, R.string.plugin_fetching_error_after_at, Duration.LONG);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11028)) {
            if ((ListenerUtil.mutListener.listen(11025) ? (event.type == PluginDirectoryType.SITE || mIsShowingAutomatedTransferProgress) : (event.type == PluginDirectoryType.SITE && mIsShowingAutomatedTransferProgress))) {
                if (!ListenerUtil.mutListener.listen(11027)) {
                    // we can finish the whole flow
                    automatedTransferCompleted();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11026)) {
                    // should be safe to refresh the plugin and the view in case the plugin we are showing has changed
                    refreshViews();
                }
            }
        }
    }

    private void fetchPluginDirectory(int delay) {
        if (!ListenerUtil.mutListener.listen(11029)) {
            mHandler.postDelayed(() -> mDispatcher.dispatch(PluginActionBuilder.newFetchPluginDirectoryAction(new PluginStore.FetchPluginDirectoryPayload(PluginDirectoryType.SITE, mSite, false))), delay);
        }
    }

    private String getEligibilityErrorMessage(String errorCode) {
        int errorMessageRes;
        switch(errorCode) {
            case "email_unverified":
                errorMessageRes = R.string.plugin_install_site_ineligible_email_unverified;
                break;
            case "excessive_disk_space":
                errorMessageRes = R.string.plugin_install_site_ineligible_excessive_disk_space;
                break;
            case "no_business_plan":
                errorMessageRes = R.string.plugin_install_site_ineligible_no_business_plan;
                break;
            case "no_vip_sites":
                errorMessageRes = R.string.plugin_install_site_ineligible_no_vip_sites;
                break;
            case "non_admin_user":
                errorMessageRes = R.string.plugin_install_site_ineligible_non_admin_user;
                break;
            case "not_domain_owner":
                errorMessageRes = R.string.plugin_install_site_ineligible_not_domain_owner;
                break;
            case "not_using_custom_domain":
                errorMessageRes = R.string.plugin_install_site_ineligible_not_using_custom_domain;
                break;
            case "site_graylisted":
                errorMessageRes = R.string.plugin_install_site_ineligible_site_graylisted;
                break;
            case "site_private":
                errorMessageRes = R.string.plugin_install_site_ineligible_site_private;
                break;
            default:
                // no_jetpack_sites, no_ssl_certificate, no_wpcom_nameservers, not_resolving_to_wpcom
                errorMessageRes = R.string.plugin_install_site_ineligible_default_error;
                break;
        }
        return getString(errorMessageRes);
    }

    private boolean isNotAutoManaged() {
        return !PluginUtils.isAutoManaged(mSite, mPlugin);
    }
}
