package org.wordpress.android.ui.accounts;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.CloseWithResultOk;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.CreateNewSite;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.SelectSite;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowNoJetpackSites;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowPostSignupInterstitialScreen;
import org.wordpress.android.ui.accounts.login.LoginEpilogueFragment;
import org.wordpress.android.ui.accounts.login.LoginEpilogueListener;
import org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment;
import org.wordpress.android.ui.main.SitePickerActivity;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationSource;
import java.util.ArrayList;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoginEpilogueActivity extends LocaleAwareActivity implements LoginEpilogueListener {

    public static final String EXTRA_DO_LOGIN_UPDATE = "EXTRA_DO_LOGIN_UPDATE";

    public static final String EXTRA_SHOW_AND_RETURN = "EXTRA_SHOW_AND_RETURN";

    public static final String ARG_OLD_SITES_IDS = "ARG_OLD_SITES_IDS";

    public static final String KEY_SITE_CREATED_FROM_LOGIN_EPILOGUE = "SITE_CREATED_FROM_LOGIN_EPILOGUE";

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    LoginEpilogueViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4104)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4105)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(4106)) {
            LoginFlowThemeHelper.injectMissingCustomAttributes(getTheme());
        }
        if (!ListenerUtil.mutListener.listen(4107)) {
            setContentView(R.layout.login_epilogue_activity);
        }
        if (!ListenerUtil.mutListener.listen(4109)) {
            if (savedInstanceState == null) {
                boolean doLoginUpdate = getIntent().getBooleanExtra(EXTRA_DO_LOGIN_UPDATE, false);
                boolean showAndReturn = getIntent().getBooleanExtra(EXTRA_SHOW_AND_RETURN, false);
                ArrayList<Integer> oldSitesIds = getIntent().getIntegerArrayListExtra(ARG_OLD_SITES_IDS);
                if (!ListenerUtil.mutListener.listen(4108)) {
                    addPostLoginFragment(doLoginUpdate, showAndReturn, oldSitesIds);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4110)) {
            initViewModel();
        }
        if (!ListenerUtil.mutListener.listen(4111)) {
            initObservers();
        }
    }

    private void initViewModel() {
        if (!ListenerUtil.mutListener.listen(4112)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(LoginEpilogueViewModel.class);
        }
    }

    private void initObservers() {
        if (!ListenerUtil.mutListener.listen(4113)) {
            mViewModel.getNavigationEvents().observe(this, event -> {
                LoginNavigationEvents loginEvent = event.getContentIfNotHandled();
                if (loginEvent instanceof ShowPostSignupInterstitialScreen) {
                    showPostSignupInterstitialScreen();
                } else if (loginEvent instanceof SelectSite) {
                    selectSite(((SelectSite) loginEvent).getLocalId());
                } else if (loginEvent instanceof CreateNewSite) {
                    createNewSite();
                } else if (loginEvent instanceof CloseWithResultOk) {
                    closeWithResultOk();
                } else if (loginEvent instanceof ShowNoJetpackSites) {
                    showNoJetpackSites();
                }
            });
        }
    }

    protected void addPostLoginFragment(boolean doLoginUpdate, boolean showAndReturn, ArrayList<Integer> oldSitesIds) {
        LoginEpilogueFragment loginEpilogueFragment = LoginEpilogueFragment.newInstance(doLoginUpdate, showAndReturn, oldSitesIds);
        if (!ListenerUtil.mutListener.listen(4114)) {
            showFragment(loginEpilogueFragment, LoginEpilogueFragment.TAG, false);
        }
    }

    @Override
    public void onSiteClick(int localId) {
        if (!ListenerUtil.mutListener.listen(4115)) {
            mViewModel.onSiteClick(localId);
        }
    }

    @Override
    public void onCreateNewSite() {
        if (!ListenerUtil.mutListener.listen(4116)) {
            mViewModel.onCreateNewSite();
        }
    }

    @Override
    public void onConnectAnotherSite() {
        if (!ListenerUtil.mutListener.listen(4119)) {
            if (mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(4118)) {
                    ActivityLauncher.addSelfHostedSiteForResult(this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4117)) {
                    ActivityLauncher.showSignInForResult(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4120)) {
            finish();
        }
    }

    @Override
    public void onContinue() {
        if (!ListenerUtil.mutListener.listen(4121)) {
            mViewModel.onContinue();
        }
    }

    private void showPostSignupInterstitialScreen() {
        if (!ListenerUtil.mutListener.listen(4122)) {
            ActivityLauncher.showPostSignupInterstitial(this);
        }
    }

    private void selectSite(int localId) {
        if (!ListenerUtil.mutListener.listen(4123)) {
            setResult(RESULT_OK, new Intent().putExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, localId));
        }
        if (!ListenerUtil.mutListener.listen(4124)) {
            finish();
        }
    }

    private void createNewSite() {
        if (!ListenerUtil.mutListener.listen(4125)) {
            ActivityLauncher.newBlogForResult(this, SiteCreationSource.LOGIN_EPILOGUE);
        }
    }

    private void closeWithResultOk() {
        if (!ListenerUtil.mutListener.listen(4126)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(4127)) {
            finish();
        }
    }

    private void showNoJetpackSites() {
        LoginNoSitesFragment fragment = LoginNoSitesFragment.Companion.newInstance();
        if (!ListenerUtil.mutListener.listen(4128)) {
            showFragment(fragment, LoginNoSitesFragment.TAG, true);
        }
    }

    private void showFragment(Fragment fragment, String tag, boolean applySlideAnimation) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(4130)) {
            if (applySlideAnimation) {
                if (!ListenerUtil.mutListener.listen(4129)) {
                    fragmentTransaction.setCustomAnimations(R.anim.activity_slide_in_from_right, R.anim.activity_slide_out_to_left, R.anim.activity_slide_in_from_left, R.anim.activity_slide_out_to_right);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4131)) {
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        }
        if (!ListenerUtil.mutListener.listen(4132)) {
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(4133)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(4138)) {
            if ((ListenerUtil.mutListener.listen(4135) ? ((ListenerUtil.mutListener.listen(4134) ? (requestCode == RequestCodes.CREATE_SITE || resultCode == RESULT_OK) : (requestCode == RequestCodes.CREATE_SITE && resultCode == RESULT_OK)) || data != null) : ((ListenerUtil.mutListener.listen(4134) ? (requestCode == RequestCodes.CREATE_SITE || resultCode == RESULT_OK) : (requestCode == RequestCodes.CREATE_SITE && resultCode == RESULT_OK)) && data != null))) {
                int newSiteLocalID = data.getIntExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE);
                boolean isTitleTaskCompleted = data.getBooleanExtra(SitePickerActivity.KEY_SITE_TITLE_TASK_COMPLETED, false);
                if (!ListenerUtil.mutListener.listen(4136)) {
                    setResult(RESULT_OK, new Intent().putExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, newSiteLocalID).putExtra(SitePickerActivity.KEY_SITE_TITLE_TASK_COMPLETED, isTitleTaskCompleted).putExtra(KEY_SITE_CREATED_FROM_LOGIN_EPILOGUE, true));
                }
                if (!ListenerUtil.mutListener.listen(4137)) {
                    finish();
                }
            }
        }
    }
}
