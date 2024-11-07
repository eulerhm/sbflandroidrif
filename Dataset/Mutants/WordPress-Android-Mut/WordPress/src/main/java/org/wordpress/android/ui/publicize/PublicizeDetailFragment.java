package org.wordpress.android.ui.publicize;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.publicize.PublicizeConstants.ConnectAction;
import org.wordpress.android.ui.publicize.adapters.PublicizeConnectionAdapter;
import org.wordpress.android.util.ToastUtils;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeDetailFragment extends PublicizeBaseFragment implements PublicizeConnectionAdapter.OnAdapterLoadedListener {

    private SiteModel mSite;

    private String mServiceId;

    private PublicizeService mService;

    private ConnectButton mConnectBtn;

    private RecyclerView mRecycler;

    private View mConnectionsContainer;

    private ViewGroup mServiceContainer;

    private View mNestedScrollView;

    @Inject
    AccountStore mAccountStore;

    public static PublicizeDetailFragment newInstance(@NonNull SiteModel site, @NonNull PublicizeService service) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(17539)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(17540)) {
            args.putString(PublicizeConstants.ARG_SERVICE_ID, service.getId());
        }
        PublicizeDetailFragment fragment = new PublicizeDetailFragment();
        if (!ListenerUtil.mutListener.listen(17541)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        if (!ListenerUtil.mutListener.listen(17542)) {
            super.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(17545)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(17543)) {
                    mSite = (SiteModel) args.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(17544)) {
                    mServiceId = args.getString(PublicizeConstants.ARG_SERVICE_ID);
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17546)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(17547)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(17550)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(17548)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(17549)) {
                    mServiceId = savedInstanceState.getString(PublicizeConstants.ARG_SERVICE_ID);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(17551)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(17552)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(17553)) {
            outState.putString(PublicizeConstants.ARG_SERVICE_ID, mServiceId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.publicize_detail_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(17554)) {
            mConnectionsContainer = rootView.findViewById(R.id.connections_container);
        }
        if (!ListenerUtil.mutListener.listen(17555)) {
            mServiceContainer = rootView.findViewById(R.id.service_container);
        }
        if (!ListenerUtil.mutListener.listen(17556)) {
            mConnectBtn = mServiceContainer.findViewById(R.id.button_connect);
        }
        if (!ListenerUtil.mutListener.listen(17557)) {
            mRecycler = rootView.findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(17558)) {
            mNestedScrollView = rootView.findViewById(R.id.publicize_details_nested_scroll_View);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(17559)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(17560)) {
            loadData();
        }
        if (!ListenerUtil.mutListener.listen(17561)) {
            setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(17563)) {
            if (getActivity() instanceof ScrollableViewInitializedListener) {
                if (!ListenerUtil.mutListener.listen(17562)) {
                    ((ScrollableViewInitializedListener) getActivity()).onScrollableViewInitialized(mNestedScrollView.getId());
                }
            }
        }
    }

    public void loadData() {
        if (!ListenerUtil.mutListener.listen(17564)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17565)) {
            mService = PublicizeTable.getService(mServiceId);
        }
        if (!ListenerUtil.mutListener.listen(17567)) {
            if (mService == null) {
                if (!ListenerUtil.mutListener.listen(17566)) {
                    ToastUtils.showToast(getActivity(), R.string.error_generic);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17568)) {
            setTitle(mService.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(17572)) {
            // disable the ability to add another G+ connection
            if (isGooglePlus()) {
                if (!ListenerUtil.mutListener.listen(17571)) {
                    mServiceContainer.setVisibility(View.GONE);
                }
            } else {
                String serviceLabel = String.format(getString(R.string.connection_service_label), mService.getLabel());
                TextView txtService = mServiceContainer.findViewById(R.id.text_service);
                if (!ListenerUtil.mutListener.listen(17569)) {
                    txtService.setText(serviceLabel);
                }
                String description = String.format(getString(R.string.connection_service_description), mService.getLabel());
                TextView txtDescription = mServiceContainer.findViewById(R.id.text_description);
                if (!ListenerUtil.mutListener.listen(17570)) {
                    txtDescription.setText(description);
                }
            }
        }
        long currentUserId = mAccountStore.getAccount().getUserId();
        PublicizeConnectionAdapter adapter = new PublicizeConnectionAdapter(getActivity(), mSite.getSiteId(), mService, currentUserId);
        if (!ListenerUtil.mutListener.listen(17573)) {
            adapter.setOnPublicizeActionListener(getOnPublicizeActionListener());
        }
        if (!ListenerUtil.mutListener.listen(17574)) {
            adapter.setOnAdapterLoadedListener(this);
        }
        if (!ListenerUtil.mutListener.listen(17575)) {
            mRecycler.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(17576)) {
            adapter.refresh();
        }
    }

    private boolean isGooglePlus() {
        return mService.getId().equals(PublicizeConstants.GOOGLE_PLUS_ID);
    }

    private boolean hasOnPublicizeActionListener() {
        return getOnPublicizeActionListener() != null;
    }

    private PublicizeActions.OnPublicizeActionListener getOnPublicizeActionListener() {
        if (!ListenerUtil.mutListener.listen(17577)) {
            if (getActivity() instanceof PublicizeActions.OnPublicizeActionListener) {
                return (PublicizeActions.OnPublicizeActionListener) getActivity();
            }
        }
        return null;
    }

    @Override
    public void onAdapterLoaded(boolean isEmpty) {
        if (!ListenerUtil.mutListener.listen(17578)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17579)) {
            mConnectionsContainer.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(17584)) {
            if (hasOnPublicizeActionListener()) {
                if (!ListenerUtil.mutListener.listen(17582)) {
                    if (isEmpty) {
                        if (!ListenerUtil.mutListener.listen(17581)) {
                            mConnectBtn.setAction(ConnectAction.CONNECT);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17580)) {
                            mConnectBtn.setAction(ConnectAction.CONNECT_ANOTHER_ACCOUNT);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17583)) {
                    mConnectBtn.setOnClickListener(v -> getOnPublicizeActionListener().onRequestConnect(mService));
                }
            }
        }
    }
}
