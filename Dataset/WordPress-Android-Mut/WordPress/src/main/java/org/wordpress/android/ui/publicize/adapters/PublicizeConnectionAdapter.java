package org.wordpress.android.ui.publicize.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeConnection.ConnectStatus;
import org.wordpress.android.models.PublicizeConnectionList;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.ui.publicize.ConnectButton;
import org.wordpress.android.ui.publicize.PublicizeActions;
import org.wordpress.android.ui.publicize.PublicizeConstants;
import org.wordpress.android.ui.publicize.PublicizeConstants.ConnectAction;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeConnectionAdapter extends RecyclerView.Adapter<PublicizeConnectionAdapter.ConnectionViewHolder> {

    public interface OnAdapterLoadedListener {

        void onAdapterLoaded(boolean isEmpty);
    }

    private final PublicizeConnectionList mConnections = new PublicizeConnectionList();

    private final long mSiteId;

    private final long mCurrentUserId;

    private final PublicizeService mService;

    private PublicizeActions.OnPublicizeActionListener mActionListener;

    private OnAdapterLoadedListener mLoadedListener;

    @Inject
    ImageManager mImageManager;

    public PublicizeConnectionAdapter(Context context, long siteId, PublicizeService service, long currentUserId) {
        super();
        if (!ListenerUtil.mutListener.listen(17093)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        mSiteId = siteId;
        mService = service;
        mCurrentUserId = currentUserId;
        if (!ListenerUtil.mutListener.listen(17094)) {
            setHasStableIds(true);
        }
    }

    public void setOnAdapterLoadedListener(OnAdapterLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(17095)) {
            mLoadedListener = listener;
        }
    }

    public void setOnPublicizeActionListener(PublicizeActions.OnPublicizeActionListener listener) {
        if (!ListenerUtil.mutListener.listen(17096)) {
            mActionListener = listener;
        }
    }

    public void refresh() {
        PublicizeConnectionList siteConnections = PublicizeTable.getConnectionsForSite(mSiteId);
        PublicizeConnectionList serviceConnections = siteConnections.getServiceConnectionsForUser(mCurrentUserId, mService.getId());
        if (!ListenerUtil.mutListener.listen(17100)) {
            if (!mConnections.isSameAs(serviceConnections)) {
                if (!ListenerUtil.mutListener.listen(17097)) {
                    mConnections.clear();
                }
                if (!ListenerUtil.mutListener.listen(17098)) {
                    mConnections.addAll(serviceConnections);
                }
                if (!ListenerUtil.mutListener.listen(17099)) {
                    notifyDataSetChanged();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17102)) {
            if (mLoadedListener != null) {
                if (!ListenerUtil.mutListener.listen(17101)) {
                    mLoadedListener.onAdapterLoaded(isEmpty());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mConnections.size();
    }

    private boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(17107) ? (getItemCount() >= 0) : (ListenerUtil.mutListener.listen(17106) ? (getItemCount() <= 0) : (ListenerUtil.mutListener.listen(17105) ? (getItemCount() > 0) : (ListenerUtil.mutListener.listen(17104) ? (getItemCount() < 0) : (ListenerUtil.mutListener.listen(17103) ? (getItemCount() != 0) : (getItemCount() == 0)))))));
    }

    @Override
    public long getItemId(int position) {
        return mConnections.get(position).connectionId;
    }

    @NotNull
    @Override
    public ConnectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicize_listitem_connection, parent, false);
        return new ConnectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConnectionViewHolder holder, int position) {
        final PublicizeConnection connection = mConnections.get(position);
        if (!ListenerUtil.mutListener.listen(17108)) {
            holder.mTxtUser.setText(connection.getExternalDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(17114)) {
            holder.mDivider.setVisibility((ListenerUtil.mutListener.listen(17113) ? (position >= 0) : (ListenerUtil.mutListener.listen(17112) ? (position <= 0) : (ListenerUtil.mutListener.listen(17111) ? (position > 0) : (ListenerUtil.mutListener.listen(17110) ? (position < 0) : (ListenerUtil.mutListener.listen(17109) ? (position != 0) : (position == 0)))))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(17115)) {
            mImageManager.loadIntoCircle(holder.mImgAvatar, ImageType.AVATAR_WITH_BACKGROUND, connection.getExternalProfilePictureUrl());
        }
        if (!ListenerUtil.mutListener.listen(17116)) {
            bindButton(holder.mBtnConnect, connection);
        }
    }

    private void bindButton(ConnectButton btnConnect, final PublicizeConnection connection) {
        ConnectStatus status = connection.getStatusEnum();
        if (!ListenerUtil.mutListener.listen(17121)) {
            switch(status) {
                case OK:
                case MUST_DISCONNECT:
                    if (!ListenerUtil.mutListener.listen(17117)) {
                        btnConnect.setAction(PublicizeConstants.ConnectAction.DISCONNECT);
                    }
                    if (!ListenerUtil.mutListener.listen(17118)) {
                        btnConnect.setOnClickListener(v -> {
                            if (mActionListener != null) {
                                mActionListener.onRequestDisconnect(connection);
                            }
                        });
                    }
                    break;
                case BROKEN:
                default:
                    if (!ListenerUtil.mutListener.listen(17119)) {
                        btnConnect.setAction(ConnectAction.RECONNECT);
                    }
                    if (!ListenerUtil.mutListener.listen(17120)) {
                        btnConnect.setOnClickListener(view -> {
                            if (mActionListener != null) {
                                mActionListener.onRequestReconnect(mService, connection);
                            }
                        });
                    }
            }
        }
    }

    class ConnectionViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtUser;

        private final ConnectButton mBtnConnect;

        private final ImageView mImgAvatar;

        private final View mDivider;

        ConnectionViewHolder(View view) {
            super(view);
            mTxtUser = view.findViewById(R.id.text_user);
            mImgAvatar = view.findViewById(R.id.image_avatar);
            mBtnConnect = view.findViewById(R.id.button_connect);
            mDivider = view.findViewById(R.id.divider);
        }
    }
}
