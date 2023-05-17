package org.wordpress.android.ui.deeplinks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UriWrapper;
import javax.inject.Inject;
import static org.wordpress.android.WordPress.getContext;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An activity to handle deep linking and intercepting links like:
 * <p>
 * wordpress://viewpost?blogId={blogId}&postId={postId}
 * <p>
 * Redirects users to the reader activity along with IDs passed in the intent
 */
public class DeepLinkingIntentReceiverActivity extends LocaleAwareActivity {

    private static final String URI_KEY = "uri_key";

    @Inject
    DeepLinkNavigator mDeeplinkNavigator;

    @Inject
    DeepLinkUriUtils mDeepLinkUriUtils;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private DeepLinkingIntentReceiverViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5032)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5033)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5034)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(DeepLinkingIntentReceiverViewModel.class);
        }
        String action = null;
        Uri uri;
        if (savedInstanceState == null) {
            if (!ListenerUtil.mutListener.listen(5035)) {
                action = getIntent().getAction();
            }
            uri = getIntent().getData();
        } else {
            uri = savedInstanceState.getParcelable(URI_KEY);
        }
        if (!ListenerUtil.mutListener.listen(5036)) {
            setupObservers();
        }
        UriWrapper uriWrapper = null;
        if (!ListenerUtil.mutListener.listen(5038)) {
            if (uri != null) {
                if (!ListenerUtil.mutListener.listen(5037)) {
                    uriWrapper = new UriWrapper(uri);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5039)) {
            mViewModel.start(action, uriWrapper);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5040)) {
            super.onSaveInstanceState(outState);
        }
        UriWrapper cachedUri = mViewModel.getCachedUri();
        if (!ListenerUtil.mutListener.listen(5042)) {
            if (cachedUri != null) {
                if (!ListenerUtil.mutListener.listen(5041)) {
                    outState.putParcelable(URI_KEY, cachedUri.getUri());
                }
            }
        }
    }

    private void setupObservers() {
        if (!ListenerUtil.mutListener.listen(5043)) {
            mViewModel.getNavigateAction().observe(this, navigateActionEvent -> navigateActionEvent.applyIfNotHandled(navigateAction -> {
                mDeeplinkNavigator.handleNavigationAction(navigateAction, this);
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(5044)) {
            mViewModel.getFinish().observe(this, finishEvent -> finishEvent.applyIfNotHandled(unit -> {
                finish();
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(5045)) {
            mViewModel.getToast().observe(this, toastEvent -> toastEvent.applyIfNotHandled(toastMessage -> {
                ToastUtils.showToast(getContext(), toastMessage);
                return null;
            }));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(5046)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(5050)) {
            // show the post if user is returning from successful login
            if ((ListenerUtil.mutListener.listen(5047) ? (requestCode == RequestCodes.DO_LOGIN || resultCode == RESULT_OK) : (requestCode == RequestCodes.DO_LOGIN && resultCode == RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(5049)) {
                    mViewModel.onSuccessfulLogin();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5048)) {
                    finish();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5051)) {
            super.onBackPressed();
        }
        if (!ListenerUtil.mutListener.listen(5052)) {
            finish();
        }
    }
}
