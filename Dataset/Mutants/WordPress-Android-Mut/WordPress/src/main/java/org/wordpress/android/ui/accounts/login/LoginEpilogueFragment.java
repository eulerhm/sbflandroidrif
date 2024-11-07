package org.wordpress.android.ui.accounts.login;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.model.AccountModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.login.LoginBaseFormFragment;
import org.wordpress.android.ui.accounts.LoginEpilogueViewModel;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Click;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step;
import org.wordpress.android.ui.main.SitePickerAdapter;
import org.wordpress.android.ui.main.SitePickerAdapter.OnDataLoadedListener;
import org.wordpress.android.ui.main.SitePickerAdapter.OnSiteClickListener;
import org.wordpress.android.ui.main.SitePickerAdapter.SiteList;
import org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode;
import org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord;
import org.wordpress.android.ui.main.SitePickerAdapter.ViewHolderHandler;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.image.ImageManager;
import java.util.ArrayList;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoginEpilogueFragment extends LoginBaseFormFragment<LoginEpilogueListener> {

    public static final String TAG = "login_epilogue_fragment_tag";

    private static final String ARG_DO_LOGIN_UPDATE = "ARG_DO_LOGIN_UPDATE";

    private static final String ARG_SHOW_AND_RETURN = "ARG_SHOW_AND_RETURN";

    private static final String ARG_OLD_SITES_IDS = "ARG_OLD_SITES_IDS";

    private static final int EXPANDED_UI_THRESHOLD = 3;

    private RecyclerView mSitesList;

    @Nullable
    private View mBottomShadow;

    private SitePickerAdapter mAdapter;

    private boolean mDoLoginUpdate;

    private boolean mShowAndReturn;

    private ArrayList<Integer> mOldSitesIds;

    private LoginEpilogueListener mLoginEpilogueListener;

    @Inject
    ImageManager mImageManager;

    @Inject
    UnifiedLoginTracker mUnifiedLoginTracker;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    LoginEpilogueViewModel mParentViewModel;

    public static LoginEpilogueFragment newInstance(boolean doLoginUpdate, boolean showAndReturn, ArrayList<Integer> oldSitesIds) {
        LoginEpilogueFragment fragment = new LoginEpilogueFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(3403)) {
            args.putBoolean(ARG_DO_LOGIN_UPDATE, doLoginUpdate);
        }
        if (!ListenerUtil.mutListener.listen(3404)) {
            args.putBoolean(ARG_SHOW_AND_RETURN, showAndReturn);
        }
        if (!ListenerUtil.mutListener.listen(3405)) {
            args.putIntegerArrayList(ARG_OLD_SITES_IDS, oldSitesIds);
        }
        if (!ListenerUtil.mutListener.listen(3406)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    protected boolean listenForLogin() {
        return mDoLoginUpdate;
    }

    @Override
    @LayoutRes
    protected int getContentLayout() {
        // nothing special here. The view is inflated in createMainView()
        return 0;
    }

    @Override
    @LayoutRes
    protected int getProgressBarText() {
        return R.string.logging_in;
    }

    @Override
    protected void setupLabel(@NonNull TextView label) {
    }

    @Override
    protected ViewGroup createMainView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(loginEpilogueScreenResource(), container, false);
    }

    @LayoutRes
    private int loginEpilogueScreenResource() {
        if (isNewLoginEpilogueScreenEnabled()) {
            if ((ListenerUtil.mutListener.listen(3412) ? ((ListenerUtil.mutListener.listen(3411) ? (mAdapter.getBlogsForCurrentView().size() >= EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3410) ? (mAdapter.getBlogsForCurrentView().size() > EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3409) ? (mAdapter.getBlogsForCurrentView().size() < EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3408) ? (mAdapter.getBlogsForCurrentView().size() != EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3407) ? (mAdapter.getBlogsForCurrentView().size() == EXPANDED_UI_THRESHOLD) : (mAdapter.getBlogsForCurrentView().size() <= EXPANDED_UI_THRESHOLD)))))) || getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) : ((ListenerUtil.mutListener.listen(3411) ? (mAdapter.getBlogsForCurrentView().size() >= EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3410) ? (mAdapter.getBlogsForCurrentView().size() > EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3409) ? (mAdapter.getBlogsForCurrentView().size() < EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3408) ? (mAdapter.getBlogsForCurrentView().size() != EXPANDED_UI_THRESHOLD) : (ListenerUtil.mutListener.listen(3407) ? (mAdapter.getBlogsForCurrentView().size() == EXPANDED_UI_THRESHOLD) : (mAdapter.getBlogsForCurrentView().size() <= EXPANDED_UI_THRESHOLD)))))) && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT))) {
                return R.layout.login_epilogue_screen_new;
            } else {
                return R.layout.login_epilogue_screen_new_expanded;
            }
        } else {
            return R.layout.login_epilogue_screen;
        }
    }

    private boolean isNewLoginEpilogueScreenEnabled() {
        return (ListenerUtil.mutListener.listen(3413) ? (mBuildConfigWrapper.isSiteCreationEnabled() || !mShowAndReturn) : (mBuildConfigWrapper.isSiteCreationEnabled() && !mShowAndReturn));
    }

    @Override
    protected void setupContent(ViewGroup rootView) {
        if (!ListenerUtil.mutListener.listen(3414)) {
            mBottomShadow = rootView.findViewById(R.id.bottom_shadow);
        }
        if (!ListenerUtil.mutListener.listen(3415)) {
            mSitesList = rootView.findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(3416)) {
            mSitesList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        }
        if (!ListenerUtil.mutListener.listen(3417)) {
            mSitesList.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(3418)) {
            mSitesList.setItemAnimator(null);
        }
        if (!ListenerUtil.mutListener.listen(3419)) {
            mSitesList.setAdapter(mAdapter);
        }
    }

    @Override
    protected void setupBottomButton(Button button) {
        if (!ListenerUtil.mutListener.listen(3420)) {
            button.setOnClickListener(v -> {
                if (mLoginEpilogueListener != null) {
                    if (isNewLoginEpilogueScreenEnabled()) {
                        AnalyticsTracker.track(Stat.LOGIN_EPILOGUE_CREATE_NEW_SITE_TAPPED);
                        mUnifiedLoginTracker.trackClick(Click.CREATE_NEW_SITE);
                        mLoginEpilogueListener.onCreateNewSite();
                    } else {
                        mUnifiedLoginTracker.trackClick(Click.CONTINUE);
                        mLoginEpilogueListener.onContinue();
                    }
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3421)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3422)) {
            ((WordPress) requireActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(3423)) {
            mDoLoginUpdate = requireArguments().getBoolean(ARG_DO_LOGIN_UPDATE, false);
        }
        if (!ListenerUtil.mutListener.listen(3424)) {
            mShowAndReturn = requireArguments().getBoolean(ARG_SHOW_AND_RETURN, false);
        }
        if (!ListenerUtil.mutListener.listen(3425)) {
            mOldSitesIds = requireArguments().getIntegerArrayList(ARG_OLD_SITES_IDS);
        }
        if (!ListenerUtil.mutListener.listen(3426)) {
            initAdapter();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3427)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3428)) {
            initViewModel();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3429)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3433)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(3430)) {
                    mParentViewModel.checkAndSetVariantForMySiteDefaultTabExperiment();
                }
                if (!ListenerUtil.mutListener.listen(3431)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_EPILOGUE_VIEWED);
                }
                if (!ListenerUtil.mutListener.listen(3432)) {
                    mUnifiedLoginTracker.track(Step.SUCCESS);
                }
            }
        }
    }

    private void initViewModel() {
        if (!ListenerUtil.mutListener.listen(3434)) {
            mParentViewModel = new ViewModelProvider(requireActivity(), mViewModelFactory).get(LoginEpilogueViewModel.class);
        }
    }

    private void initAdapter() {
        if (!ListenerUtil.mutListener.listen(3436)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(3435)) {
                    setNewAdapter();
                }
            }
        }
    }

    private void setNewAdapter() {
        if (!ListenerUtil.mutListener.listen(3437)) {
            mAdapter = new SitePickerAdapter(requireActivity(), R.layout.login_epilogue_sites_listitem, 0, "", false, dataLoadedListener(), headerHandler(), footerHandler(), mOldSitesIds, SitePickerMode.DEFAULT_MODE, mShowAndReturn);
        }
        if (!ListenerUtil.mutListener.listen(3438)) {
            setOnSiteClickListener();
        }
    }

    @NonNull
    private OnDataLoadedListener dataLoadedListener() {
        return new OnDataLoadedListener() {

            @Override
            public void onBeforeLoad(boolean isEmpty) {
            }

            @Override
            public void onAfterLoad() {
                if (!ListenerUtil.mutListener.listen(3439)) {
                    mSitesList.post(() -> {
                        if (!isAdded()) {
                            return;
                        }
                        if (mBottomShadow != null) {
                            if (mSitesList.computeVerticalScrollRange() > mSitesList.getHeight()) {
                                mBottomShadow.setVisibility(View.VISIBLE);
                            } else {
                                mBottomShadow.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        };
    }

    @NonNull
    private ViewHolderHandler<LoginHeaderViewHolder> headerHandler() {
        return new ViewHolderHandler<LoginHeaderViewHolder>() {

            @Override
            public LoginHeaderViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, boolean attachToRoot) {
                if (isNewLoginEpilogueScreenEnabled()) {
                    return new LoginHeaderViewHolder(layoutInflater.inflate(R.layout.login_epilogue_header_new, parent, false), true);
                } else {
                    return new LoginHeaderViewHolder(layoutInflater.inflate(R.layout.login_epilogue_header, parent, false), false);
                }
            }

            @Override
            public void onBindViewHolder(LoginHeaderViewHolder holder, SiteList sites) {
                if (!ListenerUtil.mutListener.listen(3440)) {
                    bindHeaderViewHolder(holder, sites);
                }
            }
        };
    }

    @Nullable
    private ViewHolderHandler<LoginFooterViewHolder> footerHandler() {
        if (isNewLoginEpilogueScreenEnabled()) {
            return null;
        } else {
            return new ViewHolderHandler<LoginFooterViewHolder>() {

                @Override
                public LoginFooterViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, boolean attachToRoot) {
                    return new LoginFooterViewHolder(layoutInflater.inflate(R.layout.login_epilogue_footer, parent, false));
                }

                @Override
                public void onBindViewHolder(LoginFooterViewHolder holder, SiteList sites) {
                    if (!ListenerUtil.mutListener.listen(3441)) {
                        bindFooterViewHolder(holder, sites);
                    }
                }
            };
        }
    }

    private void setOnSiteClickListener() {
        if (!ListenerUtil.mutListener.listen(3446)) {
            if (isNewLoginEpilogueScreenEnabled()) {
                if (!ListenerUtil.mutListener.listen(3445)) {
                    mAdapter.setOnSiteClickListener(new OnSiteClickListener() {

                        @Override
                        public void onSiteClick(SiteRecord site) {
                            if (!ListenerUtil.mutListener.listen(3442)) {
                                AnalyticsTracker.track(Stat.LOGIN_EPILOGUE_CHOOSE_SITE_TAPPED);
                            }
                            if (!ListenerUtil.mutListener.listen(3443)) {
                                mUnifiedLoginTracker.trackClick(Click.CHOOSE_SITE);
                            }
                            if (!ListenerUtil.mutListener.listen(3444)) {
                                mLoginEpilogueListener.onSiteClick(site.getLocalId());
                            }
                        }

                        @Override
                        public boolean onSiteLongClick(SiteRecord site) {
                            return false;
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(3447)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(3449)) {
            if (context instanceof LoginEpilogueListener) {
                if (!ListenerUtil.mutListener.listen(3448)) {
                    mLoginEpilogueListener = (LoginEpilogueListener) context;
                }
            } else {
                throw new RuntimeException(context.toString() + " must implement LoginEpilogueListener");
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(3450)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3452)) {
            if (mDoLoginUpdate) {
                if (!ListenerUtil.mutListener.listen(3451)) {
                    // when from magiclink, we need to complete the login process here (update account and settings)
                    doFinishLogin();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3453)) {
            mParentViewModel.onLoginEpilogueResume(mDoLoginUpdate);
        }
    }

    @Override
    protected boolean isJetpackAppLogin() {
        return (ListenerUtil.mutListener.listen(3454) ? (mDoLoginUpdate || mBuildConfigWrapper.isJetpackApp()) : (mDoLoginUpdate && mBuildConfigWrapper.isJetpackApp()));
    }

    private void bindHeaderViewHolder(LoginHeaderViewHolder holder, SiteList sites) {
        if (!ListenerUtil.mutListener.listen(3455)) {
            if (!isAdded()) {
                return;
            }
        }
        final boolean isWpcom = mAccountStore.hasAccessToken();
        final boolean hasSites = sites.size() != 0;
        if (!ListenerUtil.mutListener.listen(3458)) {
            if (isWpcom) {
                final AccountModel account = mAccountStore.getAccount();
                if (!ListenerUtil.mutListener.listen(3457)) {
                    holder.updateLoggedInAsHeading(getContext(), mImageManager, account);
                }
            } else if (hasSites) {
                final SiteModel site = mSiteStore.getSiteByLocalId(sites.get(0).getLocalId());
                if (!ListenerUtil.mutListener.listen(3456)) {
                    holder.updateLoggedInAsHeading(getContext(), mImageManager, site);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3463)) {
            if (hasSites) {
                if (!ListenerUtil.mutListener.listen(3462)) {
                    if (isNewLoginEpilogueScreenEnabled()) {
                        if (!ListenerUtil.mutListener.listen(3461)) {
                            holder.showSitesHeading();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3460)) {
                            holder.showSitesHeading(StringUtils.getQuantityString(requireActivity(), R.string.login_epilogue_mysites_one, R.string.login_epilogue_mysites_one, R.string.login_epilogue_mysites_other, sites.size()));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3459)) {
                    holder.hideSitesHeading();
                }
            }
        }
    }

    private void bindFooterViewHolder(LoginFooterViewHolder holder, SiteList sites) {
        if (!ListenerUtil.mutListener.listen(3465)) {
            holder.itemView.setVisibility(((ListenerUtil.mutListener.listen(3464) ? (mShowAndReturn && BuildConfig.IS_JETPACK_APP) : (mShowAndReturn || BuildConfig.IS_JETPACK_APP))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3466)) {
            holder.itemView.setOnClickListener(v -> {
                if (mLoginEpilogueListener != null) {
                    mUnifiedLoginTracker.trackClick(Click.CONNECT_SITE);
                    mLoginEpilogueListener.onConnectAnotherSite();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3469)) {
            if (sites.size() == 0) {
                if (!ListenerUtil.mutListener.listen(3468)) {
                    holder.bindText(getString(R.string.connect_site));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3467)) {
                    holder.bindText(getString(R.string.connect_more));
                }
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    protected void onHelp() {
    }

    @Override
    protected void onLoginFinished() {
        if (!ListenerUtil.mutListener.listen(3470)) {
            AnalyticsUtils.trackAnalyticsSignIn(mAccountStore, mSiteStore, true);
        }
        if (!ListenerUtil.mutListener.listen(3471)) {
            endProgress();
        }
        if (!ListenerUtil.mutListener.listen(3472)) {
            setNewAdapter();
        }
        if (!ListenerUtil.mutListener.listen(3473)) {
            mSitesList.setAdapter(mAdapter);
        }
        if (!ListenerUtil.mutListener.listen(3474)) {
            mParentViewModel.onLoginFinished(mDoLoginUpdate);
        }
    }
}
