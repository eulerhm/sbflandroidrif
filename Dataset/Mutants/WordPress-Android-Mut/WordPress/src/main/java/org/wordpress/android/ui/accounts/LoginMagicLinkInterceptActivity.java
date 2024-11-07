package org.wordpress.android.ui.accounts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import org.wordpress.android.WordPress;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.ui.JetpackConnectionSource;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.main.WPMainActivity;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Deep link receiver for magic links. Starts {@link WPMainActivity} where flow is routed to login
 * or signup based on deep link scheme, host, and parameters.
 */
public class LoginMagicLinkInterceptActivity extends LocaleAwareActivity {

    private static final String PARAMETER_FLOW = "flow";

    private static final String PARAMETER_FLOW_JETPACK = "jetpack";

    private static final String PARAMETER_SOURCE = "source";

    private String mAction;

    private Uri mUri;

    @Inject
    protected LoginAnalyticsListener mLoginAnalyticsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4139)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4140)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(4141)) {
            mAction = getIntent().getAction();
        }
        if (!ListenerUtil.mutListener.listen(4142)) {
            mUri = getIntent().getData();
        }
        Intent intent = new Intent(this, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(4143)) {
            intent.setAction(mAction);
        }
        if (!ListenerUtil.mutListener.listen(4144)) {
            intent.setData(mUri);
        }
        if (!ListenerUtil.mutListener.listen(4151)) {
            if (hasMagicLinkLoginIntent()) {
                if (!ListenerUtil.mutListener.listen(4145)) {
                    intent.putExtra(WPMainActivity.ARG_IS_MAGIC_LINK_LOGIN, true);
                }
                if (!ListenerUtil.mutListener.listen(4150)) {
                    if (hasMagicLinkSignupIntent()) {
                        if (!ListenerUtil.mutListener.listen(4148)) {
                            mLoginAnalyticsListener.trackSignupMagicLinkOpened();
                        }
                        if (!ListenerUtil.mutListener.listen(4149)) {
                            intent.putExtra(WPMainActivity.ARG_IS_MAGIC_LINK_SIGNUP, true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4146)) {
                            mLoginAnalyticsListener.trackLoginMagicLinkOpened();
                        }
                        if (!ListenerUtil.mutListener.listen(4147)) {
                            intent.putExtra(WPMainActivity.ARG_IS_MAGIC_LINK_SIGNUP, false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4153)) {
            if (isJetpackConnectFlow()) {
                if (!ListenerUtil.mutListener.listen(4152)) {
                    intent.putExtra(WPMainActivity.ARG_JETPACK_CONNECT_SOURCE, getJetpackConnectSource());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4154)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(4155)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(4156)) {
            finish();
        }
    }

    private boolean hasMagicLinkLoginIntent() {
        String host = ((ListenerUtil.mutListener.listen(4157) ? (mUri != null || mUri.getHost() != null) : (mUri != null && mUri.getHost() != null))) ? mUri.getHost() : "";
        return (ListenerUtil.mutListener.listen(4158) ? (Intent.ACTION_VIEW.equals(mAction) || host.contains(LoginActivity.MAGIC_LOGIN)) : (Intent.ACTION_VIEW.equals(mAction) && host.contains(LoginActivity.MAGIC_LOGIN)));
    }

    private boolean hasMagicLinkSignupIntent() {
        if (mUri != null) {
            String parameter = SignupEpilogueActivity.MAGIC_SIGNUP_PARAMETER;
            String value = ((ListenerUtil.mutListener.listen(4159) ? (mUri.getQueryParameterNames() != null || mUri.getQueryParameter(parameter) != null) : (mUri.getQueryParameterNames() != null && mUri.getQueryParameter(parameter) != null))) ? mUri.getQueryParameter(parameter) : "";
            return (ListenerUtil.mutListener.listen(4160) ? (Intent.ACTION_VIEW.equals(mAction) || value.equalsIgnoreCase(SignupEpilogueActivity.MAGIC_SIGNUP_VALUE)) : (Intent.ACTION_VIEW.equals(mAction) && value.equalsIgnoreCase(SignupEpilogueActivity.MAGIC_SIGNUP_VALUE)));
        } else {
            return false;
        }
    }

    private boolean isJetpackConnectFlow() {
        if (mUri != null) {
            String value = ((ListenerUtil.mutListener.listen(4161) ? (mUri.getQueryParameterNames() != null || mUri.getQueryParameter(PARAMETER_FLOW) != null) : (mUri.getQueryParameterNames() != null && mUri.getQueryParameter(PARAMETER_FLOW) != null))) ? mUri.getQueryParameter(PARAMETER_FLOW) : "";
            return (ListenerUtil.mutListener.listen(4162) ? (Intent.ACTION_VIEW.equals(mAction) || value.equalsIgnoreCase(PARAMETER_FLOW_JETPACK)) : (Intent.ACTION_VIEW.equals(mAction) && value.equalsIgnoreCase(PARAMETER_FLOW_JETPACK)));
        } else {
            return false;
        }
    }

    private JetpackConnectionSource getJetpackConnectSource() {
        String value = ((ListenerUtil.mutListener.listen(4164) ? ((ListenerUtil.mutListener.listen(4163) ? (mUri != null || mUri.getQueryParameterNames() != null) : (mUri != null && mUri.getQueryParameterNames() != null)) || mUri.getQueryParameter(PARAMETER_SOURCE) != null) : ((ListenerUtil.mutListener.listen(4163) ? (mUri != null || mUri.getQueryParameterNames() != null) : (mUri != null && mUri.getQueryParameterNames() != null)) && mUri.getQueryParameter(PARAMETER_SOURCE) != null))) ? mUri.getQueryParameter(PARAMETER_SOURCE) : "";
        return JetpackConnectionSource.fromString(value);
    }
}
