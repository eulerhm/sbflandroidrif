package org.wordpress.android.ui.accounts;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.accounts.signup.SignupEpilogueFragment;
import org.wordpress.android.ui.accounts.signup.SignupEpilogueListener;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SignupEpilogueActivity extends LocaleAwareActivity implements SignupEpilogueListener {

    public static final String EXTRA_SIGNUP_DISPLAY_NAME = "EXTRA_SIGNUP_DISPLAY_NAME";

    public static final String EXTRA_SIGNUP_EMAIL_ADDRESS = "EXTRA_SIGNUP_EMAIL_ADDRESS";

    public static final String EXTRA_SIGNUP_IS_EMAIL = "EXTRA_SIGNUP_IS_EMAIL";

    public static final String EXTRA_SIGNUP_PHOTO_URL = "EXTRA_SIGNUP_PHOTO_URL";

    public static final String EXTRA_SIGNUP_USERNAME = "EXTRA_SIGNUP_USERNAME";

    public static final String MAGIC_SIGNUP_PARAMETER = "new_user";

    public static final String MAGIC_SIGNUP_VALUE = "1";

    @Inject
    SiteStore mSiteStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4165)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4166)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(4167)) {
            LoginFlowThemeHelper.injectMissingCustomAttributes(getTheme());
        }
        if (!ListenerUtil.mutListener.listen(4168)) {
            setContentView(R.layout.signup_epilogue_activity);
        }
        if (!ListenerUtil.mutListener.listen(4170)) {
            if (savedInstanceState == null) {
                String name = getIntent().getStringExtra(EXTRA_SIGNUP_DISPLAY_NAME);
                String email = getIntent().getStringExtra(EXTRA_SIGNUP_EMAIL_ADDRESS);
                String photoUrl = getIntent().getStringExtra(EXTRA_SIGNUP_PHOTO_URL);
                String username = getIntent().getStringExtra(EXTRA_SIGNUP_USERNAME);
                boolean isEmail = getIntent().getBooleanExtra(EXTRA_SIGNUP_IS_EMAIL, false);
                if (!ListenerUtil.mutListener.listen(4169)) {
                    addSignupEpilogueFragment(name, email, photoUrl, username, isEmail);
                }
            }
        }
    }

    protected void addSignupEpilogueFragment(String name, String email, String photoUrl, String username, boolean isEmail) {
        SignupEpilogueFragment signupEpilogueSocialFragment = SignupEpilogueFragment.newInstance(name, email, photoUrl, username, isEmail);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(4171)) {
            fragmentTransaction.replace(R.id.fragment_container, signupEpilogueSocialFragment, SignupEpilogueFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(4172)) {
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onContinue() {
        if (!ListenerUtil.mutListener.listen(4174)) {
            if (!mSiteStore.hasSite()) {
                if (!ListenerUtil.mutListener.listen(4173)) {
                    ActivityLauncher.showPostSignupInterstitial(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4175)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(4176)) {
            finish();
        }
    }
}
