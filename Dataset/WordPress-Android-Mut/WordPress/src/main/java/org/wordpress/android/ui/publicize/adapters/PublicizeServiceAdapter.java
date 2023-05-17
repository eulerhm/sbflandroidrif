package org.wordpress.android.ui.publicize.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeConnectionList;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.models.PublicizeServiceList;
import org.wordpress.android.ui.publicize.PublicizeConstants;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.Collections;
import java.util.Comparator;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeServiceAdapter extends RecyclerView.Adapter<PublicizeServiceAdapter.SharingViewHolder> {

    private final PublicizeServiceList mServices = new PublicizeServiceList();

    private final PublicizeConnectionList mConnections = new PublicizeConnectionList();

    private final long mSiteId;

    private final int mBlavatarSz;

    private final ColorFilter mGrayScaleFilter;

    private final long mCurrentUserId;

    private OnAdapterLoadedListener mAdapterLoadedListener;

    private OnServiceClickListener mServiceClickListener;

    private boolean mShouldHideGPlus;

    /*
     * AsyncTask to load services
     */
    private boolean mIsTaskRunning = false;

    @Inject
    ImageManager mImageManager;

    public PublicizeServiceAdapter(Context context, long siteId, long currentUserId) {
        super();
        if (!ListenerUtil.mutListener.listen(17122)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        mSiteId = siteId;
        mBlavatarSz = context.getResources().getDimensionPixelSize(R.dimen.blavatar_sz_small);
        mCurrentUserId = currentUserId;
        if (!ListenerUtil.mutListener.listen(17123)) {
            mShouldHideGPlus = true;
        }
        ColorMatrix matrix = new ColorMatrix();
        if (!ListenerUtil.mutListener.listen(17124)) {
            matrix.setSaturation(0);
        }
        mGrayScaleFilter = new ColorMatrixColorFilter(matrix);
        if (!ListenerUtil.mutListener.listen(17125)) {
            setHasStableIds(true);
        }
    }

    public void setOnAdapterLoadedListener(OnAdapterLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(17126)) {
            mAdapterLoadedListener = listener;
        }
    }

    public void setOnServiceClickListener(OnServiceClickListener listener) {
        if (!ListenerUtil.mutListener.listen(17127)) {
            mServiceClickListener = listener;
        }
    }

    public void refresh() {
        if (!ListenerUtil.mutListener.listen(17129)) {
            if (!mIsTaskRunning) {
                if (!ListenerUtil.mutListener.listen(17128)) {
                    new LoadServicesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        }
    }

    public void reload() {
        if (!ListenerUtil.mutListener.listen(17130)) {
            clear();
        }
        if (!ListenerUtil.mutListener.listen(17131)) {
            refresh();
        }
    }

    private void clear() {
        if (!ListenerUtil.mutListener.listen(17132)) {
            mServices.clear();
        }
        if (!ListenerUtil.mutListener.listen(17133)) {
            mConnections.clear();
        }
        if (!ListenerUtil.mutListener.listen(17134)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    private boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(17139) ? (getItemCount() >= 0) : (ListenerUtil.mutListener.listen(17138) ? (getItemCount() <= 0) : (ListenerUtil.mutListener.listen(17137) ? (getItemCount() > 0) : (ListenerUtil.mutListener.listen(17136) ? (getItemCount() < 0) : (ListenerUtil.mutListener.listen(17135) ? (getItemCount() != 0) : (getItemCount() == 0)))))));
    }

    @Override
    public long getItemId(int position) {
        return mServices.get(position).getId().hashCode();
    }

    @Override
    public SharingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicize_listitem_service, parent, false);
        return new SharingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SharingViewHolder holder, int position) {
        final PublicizeService service = mServices.get(position);
        final PublicizeConnectionList connections = mConnections.getServiceConnectionsForUser(mCurrentUserId, service.getId());
        if (!ListenerUtil.mutListener.listen(17140)) {
            holder.mTxtService.setText(service.getLabel());
        }
        String iconUrl = PhotonUtils.getPhotonImageUrl(service.getIconUrl(), mBlavatarSz, mBlavatarSz);
        if (!ListenerUtil.mutListener.listen(17141)) {
            mImageManager.load(holder.mImgIcon, ImageType.BLAVATAR, iconUrl);
        }
        if (!ListenerUtil.mutListener.listen(17154)) {
            if ((ListenerUtil.mutListener.listen(17146) ? (connections.size() >= 0) : (ListenerUtil.mutListener.listen(17145) ? (connections.size() <= 0) : (ListenerUtil.mutListener.listen(17144) ? (connections.size() < 0) : (ListenerUtil.mutListener.listen(17143) ? (connections.size() != 0) : (ListenerUtil.mutListener.listen(17142) ? (connections.size() == 0) : (connections.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(17150)) {
                    holder.mTxtUser.setText(connections.getUserDisplayNames());
                }
                if (!ListenerUtil.mutListener.listen(17151)) {
                    holder.mTxtUser.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(17152)) {
                    holder.mImgIcon.clearColorFilter();
                }
                if (!ListenerUtil.mutListener.listen(17153)) {
                    holder.mImgIcon.setImageAlpha(255);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17147)) {
                    holder.mTxtUser.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(17148)) {
                    holder.mImgIcon.setColorFilter(mGrayScaleFilter);
                }
                if (!ListenerUtil.mutListener.listen(17149)) {
                    holder.mImgIcon.setImageAlpha(128);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17160)) {
            // show divider for all but the first item
            holder.mDivider.setVisibility((ListenerUtil.mutListener.listen(17159) ? (position >= 0) : (ListenerUtil.mutListener.listen(17158) ? (position <= 0) : (ListenerUtil.mutListener.listen(17157) ? (position < 0) : (ListenerUtil.mutListener.listen(17156) ? (position != 0) : (ListenerUtil.mutListener.listen(17155) ? (position == 0) : (position > 0)))))) ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(17163)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(17162)) {
                        if (mServiceClickListener != null) {
                            if (!ListenerUtil.mutListener.listen(17161)) {
                                mServiceClickListener.onServiceClicked(service);
                            }
                        }
                    }
                }
            });
        }
    }

    private boolean isHiddenService(PublicizeService service) {
        boolean shouldHideGooglePlus = (ListenerUtil.mutListener.listen(17164) ? (service.getId().equals(PublicizeConstants.GOOGLE_PLUS_ID) || mShouldHideGPlus) : (service.getId().equals(PublicizeConstants.GOOGLE_PLUS_ID) && mShouldHideGPlus));
        return shouldHideGooglePlus;
    }

    public interface OnAdapterLoadedListener {

        void onAdapterLoaded(boolean isEmpty);
    }

    public interface OnServiceClickListener {

        void onServiceClicked(PublicizeService service);
    }

    class SharingViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtService;

        private final TextView mTxtUser;

        private final View mDivider;

        private final ImageView mImgIcon;

        SharingViewHolder(View view) {
            super(view);
            mTxtService = view.findViewById(R.id.text_service);
            mTxtUser = view.findViewById(R.id.text_user);
            mImgIcon = view.findViewById(R.id.image_icon);
            mDivider = view.findViewById(R.id.divider);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadServicesTask extends AsyncTask<Void, Void, Boolean> {

        private final PublicizeServiceList mTmpServices = new PublicizeServiceList();

        private final PublicizeConnectionList mTmpConnections = new PublicizeConnectionList();

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(17165)) {
                mIsTaskRunning = true;
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(17166)) {
                mIsTaskRunning = false;
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            PublicizeConnectionList connections = PublicizeTable.getConnectionsForSite(mSiteId);
            if (!ListenerUtil.mutListener.listen(17170)) {
                {
                    long _loopCounter281 = 0;
                    for (PublicizeConnection connection : connections) {
                        ListenerUtil.loopListener.listen("_loopCounter281", ++_loopCounter281);
                        if (!ListenerUtil.mutListener.listen(17168)) {
                            if (connection.getService().equals(PublicizeConstants.GOOGLE_PLUS_ID)) {
                                if (!ListenerUtil.mutListener.listen(17167)) {
                                    mShouldHideGPlus = false;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(17169)) {
                            mTmpConnections.add(connection);
                        }
                    }
                }
            }
            PublicizeServiceList services = PublicizeTable.getServiceList();
            if (!ListenerUtil.mutListener.listen(17173)) {
                {
                    long _loopCounter282 = 0;
                    for (PublicizeService service : services) {
                        ListenerUtil.loopListener.listen("_loopCounter282", ++_loopCounter282);
                        if (!ListenerUtil.mutListener.listen(17172)) {
                            if (!isHiddenService(service)) {
                                if (!ListenerUtil.mutListener.listen(17171)) {
                                    mTmpServices.add(service);
                                }
                            }
                        }
                    }
                }
            }
            return !((ListenerUtil.mutListener.listen(17174) ? (mTmpServices.isSameAs(mServices) || mTmpConnections.isSameAs(mConnections)) : (mTmpServices.isSameAs(mServices) && mTmpConnections.isSameAs(mConnections))));
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!ListenerUtil.mutListener.listen(17181)) {
                if (result) {
                    if (!ListenerUtil.mutListener.listen(17175)) {
                        mServices.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(17176)) {
                        mServices.addAll(mTmpServices);
                    }
                    if (!ListenerUtil.mutListener.listen(17177)) {
                        mConnections.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(17178)) {
                        mConnections.addAll(mTmpConnections);
                    }
                    if (!ListenerUtil.mutListener.listen(17179)) {
                        sortConnections();
                    }
                    if (!ListenerUtil.mutListener.listen(17180)) {
                        notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(17182)) {
                mIsTaskRunning = false;
            }
            if (!ListenerUtil.mutListener.listen(17184)) {
                if (mAdapterLoadedListener != null) {
                    if (!ListenerUtil.mutListener.listen(17183)) {
                        mAdapterLoadedListener.onAdapterLoaded(isEmpty());
                    }
                }
            }
        }

        /*
         * sort connected services to the top
         */
        private void sortConnections() {
            if (!ListenerUtil.mutListener.listen(17187)) {
                Collections.sort(mServices, new Comparator<PublicizeService>() {

                    @Override
                    public int compare(PublicizeService lhs, PublicizeService rhs) {
                        boolean isLhsConnected = mConnections.isServiceConnectedForUser(mCurrentUserId, lhs);
                        boolean isRhsConnected = mConnections.isServiceConnectedForUser(mCurrentUserId, rhs);
                        if ((ListenerUtil.mutListener.listen(17185) ? (isLhsConnected || !isRhsConnected) : (isLhsConnected && !isRhsConnected))) {
                            return -1;
                        } else if ((ListenerUtil.mutListener.listen(17186) ? (isRhsConnected || !isLhsConnected) : (isRhsConnected && !isLhsConnected))) {
                            return 1;
                        } else {
                            return lhs.getLabel().compareToIgnoreCase(rhs.getLabel());
                        }
                    }
                });
            }
        }
    }
}
