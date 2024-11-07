package org.wordpress.android.ui.publicize;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.publicize.adapters.PublicizeServiceAdapter;
import org.wordpress.android.ui.publicize.adapters.PublicizeServiceAdapter.OnAdapterLoadedListener;
import org.wordpress.android.ui.publicize.adapters.PublicizeServiceAdapter.OnServiceClickListener;
import org.wordpress.android.ui.quickstart.QuickStartEvent;
import org.wordpress.android.ui.utils.UiString.UiStringText;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.QuickStartUtils;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.SnackbarItem;
import org.wordpress.android.util.SnackbarItem.Info;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.ToastUtils;
import javax.inject.Inject;
import static org.wordpress.android.fluxc.store.QuickStartStore.QuickStartNewSiteTask.ENABLE_POST_SHARING;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeListFragment extends PublicizeBaseFragment {

    public interface PublicizeButtonPrefsListener {

        void onButtonPrefsClicked();
    }

    private PublicizeButtonPrefsListener mListener;

    private SiteModel mSite;

    private PublicizeServiceAdapter mAdapter;

    private RecyclerView mRecycler;

    private TextView mEmptyView;

    private View mNestedScrollView;

    private QuickStartEvent mQuickStartEvent;

    @Inject
    AccountStore mAccountStore;

    @Inject
    QuickStartUtilsWrapper mQuickStartUtilsWrapper;

    @Inject
    QuickStartRepository mQuickStartRepository;

    @Inject
    SnackbarSequencer mSnackbarSequencer;

    public static PublicizeListFragment newInstance(@NonNull SiteModel site) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(17688)) {
            args.putSerializable(WordPress.SITE, site);
        }
        PublicizeListFragment fragment = new PublicizeListFragment();
        if (!ListenerUtil.mutListener.listen(17689)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17690)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(17691)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(17693)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(17692)) {
                    mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17696)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(17694)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(17695)) {
                    getActivity().finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17698)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(17697)) {
                    mQuickStartEvent = savedInstanceState.getParcelable(QuickStartEvent.KEY);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(17699)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(17702)) {
            if ((ListenerUtil.mutListener.listen(17700) ? (isAdded() || mRecycler.getAdapter() == null) : (isAdded() && mRecycler.getAdapter() == null))) {
                if (!ListenerUtil.mutListener.listen(17701)) {
                    mRecycler.setAdapter(getAdapter());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17703)) {
            getAdapter().refresh();
        }
        if (!ListenerUtil.mutListener.listen(17704)) {
            setTitle(R.string.sharing);
        }
        if (!ListenerUtil.mutListener.listen(17705)) {
            setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(17707)) {
            if (getActivity() instanceof ScrollableViewInitializedListener) {
                if (!ListenerUtil.mutListener.listen(17706)) {
                    ((ScrollableViewInitializedListener) getActivity()).onScrollableViewInitialized(mNestedScrollView.getId());
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.publicize_list_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(17708)) {
            mRecycler = rootView.findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(17709)) {
            mEmptyView = rootView.findViewById(R.id.empty_view);
        }
        if (!ListenerUtil.mutListener.listen(17710)) {
            mNestedScrollView = rootView.findViewById(R.id.publicize_list_nested_scroll_view);
        }
        boolean isAdminOrSelfHosted = (ListenerUtil.mutListener.listen(17711) ? (mSite.getHasCapabilityManageOptions() && !SiteUtils.isAccessedViaWPComRest(mSite)) : (mSite.getHasCapabilityManageOptions() || !SiteUtils.isAccessedViaWPComRest(mSite)));
        View manageContainer = rootView.findViewById(R.id.manage_container);
        if (!ListenerUtil.mutListener.listen(17715)) {
            if (isAdminOrSelfHosted) {
                if (!ListenerUtil.mutListener.listen(17713)) {
                    manageContainer.setVisibility(View.VISIBLE);
                }
                View manageButton = rootView.findViewById(R.id.manage_button);
                if (!ListenerUtil.mutListener.listen(17714)) {
                    manageButton.setOnClickListener(view -> {
                        if (mListener != null) {
                            mListener.onButtonPrefsClicked();
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17712)) {
                    manageContainer.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17717)) {
            if (mQuickStartEvent != null) {
                if (!ListenerUtil.mutListener.listen(17716)) {
                    showQuickStartFocusPoint();
                }
            }
        }
        return rootView;
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(final QuickStartEvent event) {
        if (!ListenerUtil.mutListener.listen(17719)) {
            if ((ListenerUtil.mutListener.listen(17718) ? (!isAdded() && getView() == null) : (!isAdded() || getView() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17720)) {
            mQuickStartEvent = event;
        }
        if (!ListenerUtil.mutListener.listen(17721)) {
            EventBus.getDefault().removeStickyEvent(event);
        }
        if (!ListenerUtil.mutListener.listen(17724)) {
            if (mQuickStartEvent.getTask() == ENABLE_POST_SHARING) {
                if (!ListenerUtil.mutListener.listen(17722)) {
                    showQuickStartFocusPoint();
                }
                if (!ListenerUtil.mutListener.listen(17723)) {
                    showQuickStartSnackbar();
                }
            }
        }
    }

    private void showQuickStartFocusPoint() {
        if (!ListenerUtil.mutListener.listen(17738)) {
            // we are waiting for RecyclerView to populate itself with views and then grab the first one when it's ready
            mRecycler.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    RecyclerView.ViewHolder holder = mRecycler.findViewHolderForAdapterPosition(0);
                    if (!ListenerUtil.mutListener.listen(17737)) {
                        if (holder != null) {
                            final View quickStartTarget = holder.itemView;
                            if (!ListenerUtil.mutListener.listen(17735)) {
                                quickStartTarget.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(17725)) {
                                            if (getView() == null) {
                                                return;
                                            }
                                        }
                                        ViewGroup focusPointContainer = getView().findViewById(R.id.publicize_scroll_view_child);
                                        int focusPointSize = getResources().getDimensionPixelOffset(R.dimen.quick_start_focus_point_size);
                                        int verticalOffset = ((ListenerUtil.mutListener.listen(17733) ? (((ListenerUtil.mutListener.listen(17729) ? ((quickStartTarget.getHeight()) % focusPointSize) : (ListenerUtil.mutListener.listen(17728) ? ((quickStartTarget.getHeight()) / focusPointSize) : (ListenerUtil.mutListener.listen(17727) ? ((quickStartTarget.getHeight()) * focusPointSize) : (ListenerUtil.mutListener.listen(17726) ? ((quickStartTarget.getHeight()) + focusPointSize) : ((quickStartTarget.getHeight()) - focusPointSize)))))) % 2) : (ListenerUtil.mutListener.listen(17732) ? (((ListenerUtil.mutListener.listen(17729) ? ((quickStartTarget.getHeight()) % focusPointSize) : (ListenerUtil.mutListener.listen(17728) ? ((quickStartTarget.getHeight()) / focusPointSize) : (ListenerUtil.mutListener.listen(17727) ? ((quickStartTarget.getHeight()) * focusPointSize) : (ListenerUtil.mutListener.listen(17726) ? ((quickStartTarget.getHeight()) + focusPointSize) : ((quickStartTarget.getHeight()) - focusPointSize)))))) * 2) : (ListenerUtil.mutListener.listen(17731) ? (((ListenerUtil.mutListener.listen(17729) ? ((quickStartTarget.getHeight()) % focusPointSize) : (ListenerUtil.mutListener.listen(17728) ? ((quickStartTarget.getHeight()) / focusPointSize) : (ListenerUtil.mutListener.listen(17727) ? ((quickStartTarget.getHeight()) * focusPointSize) : (ListenerUtil.mutListener.listen(17726) ? ((quickStartTarget.getHeight()) + focusPointSize) : ((quickStartTarget.getHeight()) - focusPointSize)))))) - 2) : (ListenerUtil.mutListener.listen(17730) ? (((ListenerUtil.mutListener.listen(17729) ? ((quickStartTarget.getHeight()) % focusPointSize) : (ListenerUtil.mutListener.listen(17728) ? ((quickStartTarget.getHeight()) / focusPointSize) : (ListenerUtil.mutListener.listen(17727) ? ((quickStartTarget.getHeight()) * focusPointSize) : (ListenerUtil.mutListener.listen(17726) ? ((quickStartTarget.getHeight()) + focusPointSize) : ((quickStartTarget.getHeight()) - focusPointSize)))))) + 2) : (((ListenerUtil.mutListener.listen(17729) ? ((quickStartTarget.getHeight()) % focusPointSize) : (ListenerUtil.mutListener.listen(17728) ? ((quickStartTarget.getHeight()) / focusPointSize) : (ListenerUtil.mutListener.listen(17727) ? ((quickStartTarget.getHeight()) * focusPointSize) : (ListenerUtil.mutListener.listen(17726) ? ((quickStartTarget.getHeight()) + focusPointSize) : ((quickStartTarget.getHeight()) - focusPointSize)))))) / 2))))));
                                        if (!ListenerUtil.mutListener.listen(17734)) {
                                            QuickStartUtils.addQuickStartFocusPointAboveTheView(focusPointContainer, quickStartTarget, 0, verticalOffset);
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(17736)) {
                                mRecycler.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    }
                }
            });
        }
    }

    private void showQuickStartSnackbar() {
        Spannable title = mQuickStartUtilsWrapper.stylizeQuickStartPrompt(requireContext(), R.string.quick_start_dialog_enable_sharing_message_short_connections);
        if (!ListenerUtil.mutListener.listen(17739)) {
            mSnackbarSequencer.enqueue(new SnackbarItem(new Info(mRecycler, new UiStringText(title), Snackbar.LENGTH_LONG)));
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(17740)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(17741)) {
            outState.putParcelable(QuickStartEvent.KEY, mQuickStartEvent);
        }
    }

    @Override
    public void onAttach(@NotNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(17742)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(17744)) {
            if (activity instanceof PublicizeButtonPrefsListener) {
                if (!ListenerUtil.mutListener.listen(17743)) {
                    mListener = (PublicizeButtonPrefsListener) activity;
                }
            } else {
                throw new RuntimeException(activity.toString() + " must implement PublicizeButtonPrefsListener");
            }
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(17745)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(17746)) {
            mListener = null;
        }
    }

    private final OnAdapterLoadedListener mAdapterLoadedListener = new OnAdapterLoadedListener() {

        @Override
        public void onAdapterLoaded(boolean isEmpty) {
            if (!ListenerUtil.mutListener.listen(17747)) {
                if (!isAdded()) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(17751)) {
                if (isEmpty) {
                    if (!ListenerUtil.mutListener.listen(17750)) {
                        if (!NetworkUtils.isNetworkAvailable(getActivity())) {
                            if (!ListenerUtil.mutListener.listen(17749)) {
                                mEmptyView.setText(R.string.no_network_title);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(17748)) {
                                mEmptyView.setText(R.string.loading);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(17752)) {
                mEmptyView.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
            }
        }
    };

    private PublicizeServiceAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(17757)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(17753)) {
                    mAdapter = new PublicizeServiceAdapter(getActivity(), mSite.getSiteId(), mAccountStore.getAccount().getUserId());
                }
                if (!ListenerUtil.mutListener.listen(17754)) {
                    mAdapter.setOnAdapterLoadedListener(mAdapterLoadedListener);
                }
                if (!ListenerUtil.mutListener.listen(17756)) {
                    if (getActivity() instanceof OnServiceClickListener) {
                        if (!ListenerUtil.mutListener.listen(17755)) {
                            mAdapter.setOnServiceClickListener(service -> {
                                mQuickStartRepository.completeTask(ENABLE_POST_SHARING);
                                if (getView() != null) {
                                    QuickStartUtils.removeQuickStartFocusPoint((ViewGroup) getView());
                                }
                                mQuickStartEvent = null;
                                ((OnServiceClickListener) getActivity()).onServiceClicked(service);
                            });
                        }
                    }
                }
            }
        }
        return mAdapter;
    }

    void reload() {
        if (!ListenerUtil.mutListener.listen(17758)) {
            getAdapter().reload();
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(17759)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(17760)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(17761)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(17762)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
