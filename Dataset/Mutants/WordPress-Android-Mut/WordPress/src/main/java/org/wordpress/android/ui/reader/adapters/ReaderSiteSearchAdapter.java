package org.wordpress.android.ui.reader.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.fluxc.model.ReaderSiteModel;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.views.ReaderSiteSearchResultView;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * adapter which shows the results of a reader site search
 */
public class ReaderSiteSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ReaderSiteSearchResultView.OnSiteFollowedListener {

    public interface SiteSearchAdapterListener {

        void onSiteClicked(@NonNull ReaderSiteModel site);

        void onLoadMore(int offset);
    }

    private final SiteSearchAdapterListener mListener;

    private final List<ReaderSiteModel> mSites = new ArrayList<>();

    private boolean mCanLoadMore = true;

    private boolean mIsLoadingMore;

    public ReaderSiteSearchAdapter(@NonNull SiteSearchAdapterListener listener) {
        super();
        mListener = listener;
        if (!ListenerUtil.mutListener.listen(18896)) {
            setHasStableIds(true);
        }
    }

    public void setSiteList(@NonNull List<ReaderSiteModel> sites) {
        if (!ListenerUtil.mutListener.listen(18897)) {
            mSites.clear();
        }
        if (!ListenerUtil.mutListener.listen(18898)) {
            mSites.addAll(sites);
        }
        if (!ListenerUtil.mutListener.listen(18899)) {
            mCanLoadMore = true;
        }
        if (!ListenerUtil.mutListener.listen(18900)) {
            mIsLoadingMore = false;
        }
        if (!ListenerUtil.mutListener.listen(18901)) {
            notifyDataSetChanged();
        }
    }

    public void addSiteList(@NonNull List<ReaderSiteModel> sites) {
        if (!ListenerUtil.mutListener.listen(18902)) {
            mSites.addAll(sites);
        }
        if (!ListenerUtil.mutListener.listen(18903)) {
            mIsLoadingMore = false;
        }
        if (!ListenerUtil.mutListener.listen(18904)) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (!ListenerUtil.mutListener.listen(18905)) {
            mIsLoadingMore = false;
        }
        if (!ListenerUtil.mutListener.listen(18913)) {
            if ((ListenerUtil.mutListener.listen(18910) ? (mSites.size() >= 0) : (ListenerUtil.mutListener.listen(18909) ? (mSites.size() <= 0) : (ListenerUtil.mutListener.listen(18908) ? (mSites.size() < 0) : (ListenerUtil.mutListener.listen(18907) ? (mSites.size() != 0) : (ListenerUtil.mutListener.listen(18906) ? (mSites.size() == 0) : (mSites.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(18911)) {
                    mSites.clear();
                }
                if (!ListenerUtil.mutListener.listen(18912)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    private void checkLoadMore(int position) {
        if (!ListenerUtil.mutListener.listen(18933)) {
            if ((ListenerUtil.mutListener.listen(18930) ? ((ListenerUtil.mutListener.listen(18924) ? ((ListenerUtil.mutListener.listen(18914) ? (mCanLoadMore || !mIsLoadingMore) : (mCanLoadMore && !mIsLoadingMore)) || (ListenerUtil.mutListener.listen(18923) ? (position <= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18922) ? (position > (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18921) ? (position < (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18920) ? (position != (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18919) ? (position == (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(18914) ? (mCanLoadMore || !mIsLoadingMore) : (mCanLoadMore && !mIsLoadingMore)) && (ListenerUtil.mutListener.listen(18923) ? (position <= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18922) ? (position > (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18921) ? (position < (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18920) ? (position != (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18919) ? (position == (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))))) || (ListenerUtil.mutListener.listen(18929) ? (getItemCount() <= ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18928) ? (getItemCount() > ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18927) ? (getItemCount() < ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18926) ? (getItemCount() != ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18925) ? (getItemCount() == ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (getItemCount() >= ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST))))))) : ((ListenerUtil.mutListener.listen(18924) ? ((ListenerUtil.mutListener.listen(18914) ? (mCanLoadMore || !mIsLoadingMore) : (mCanLoadMore && !mIsLoadingMore)) || (ListenerUtil.mutListener.listen(18923) ? (position <= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18922) ? (position > (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18921) ? (position < (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18920) ? (position != (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18919) ? (position == (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(18914) ? (mCanLoadMore || !mIsLoadingMore) : (mCanLoadMore && !mIsLoadingMore)) && (ListenerUtil.mutListener.listen(18923) ? (position <= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18922) ? (position > (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18921) ? (position < (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18920) ? (position != (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18919) ? (position == (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(18918) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18917) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18916) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18915) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))))) && (ListenerUtil.mutListener.listen(18929) ? (getItemCount() <= ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18928) ? (getItemCount() > ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18927) ? (getItemCount() < ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18926) ? (getItemCount() != ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (ListenerUtil.mutListener.listen(18925) ? (getItemCount() == ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST) : (getItemCount() >= ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST))))))))) {
                if (!ListenerUtil.mutListener.listen(18931)) {
                    mIsLoadingMore = true;
                }
                if (!ListenerUtil.mutListener.listen(18932)) {
                    mListener.onLoadMore(getItemCount());
                }
            }
        }
    }

    public void setCanLoadMore(boolean canLoadMore) {
        if (!ListenerUtil.mutListener.listen(18934)) {
            mCanLoadMore = canLoadMore;
        }
    }

    private boolean isValidPosition(int position) {
        return (ListenerUtil.mutListener.listen(18945) ? ((ListenerUtil.mutListener.listen(18939) ? (position <= 0) : (ListenerUtil.mutListener.listen(18938) ? (position > 0) : (ListenerUtil.mutListener.listen(18937) ? (position < 0) : (ListenerUtil.mutListener.listen(18936) ? (position != 0) : (ListenerUtil.mutListener.listen(18935) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(18944) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(18943) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(18942) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(18941) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(18940) ? (position == getItemCount()) : (position < getItemCount()))))))) : ((ListenerUtil.mutListener.listen(18939) ? (position <= 0) : (ListenerUtil.mutListener.listen(18938) ? (position > 0) : (ListenerUtil.mutListener.listen(18937) ? (position < 0) : (ListenerUtil.mutListener.listen(18936) ? (position != 0) : (ListenerUtil.mutListener.listen(18935) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(18944) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(18943) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(18942) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(18941) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(18940) ? (position == getItemCount()) : (position < getItemCount()))))))));
    }

    public boolean isEmpty() {
        return mSites.size() == 0;
    }

    @Override
    public int getItemCount() {
        return mSites.size();
    }

    @Override
    public long getItemId(int position) {
        if (!ListenerUtil.mutListener.listen(18946)) {
            if (!isValidPosition(position)) {
                return -1;
            }
        }
        ReaderSiteModel site = mSites.get(position);
        return site.getFeedId() != 0 ? site.getFeedId() : site.getSiteId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReaderSiteSearchResultView view = new ReaderSiteSearchResultView(parent.getContext());
        return new SiteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(18947)) {
            if (!isValidPosition(position)) {
                return;
            }
        }
        SiteViewHolder siteHolder = (SiteViewHolder) holder;
        if (!ListenerUtil.mutListener.listen(18948)) {
            siteHolder.mSearchResultView.setSite(mSites.get(position), this);
        }
        if (!ListenerUtil.mutListener.listen(18949)) {
            checkLoadMore(position);
        }
    }

    @Override
    public void onSiteFollowed(@NonNull ReaderSiteModel site) {
        if (!ListenerUtil.mutListener.listen(18950)) {
            setSiteFollowed(site, true);
        }
    }

    @Override
    public void onSiteUnFollowed(@NonNull ReaderSiteModel site) {
        if (!ListenerUtil.mutListener.listen(18951)) {
            setSiteFollowed(site, false);
        }
    }

    private void setSiteFollowed(@NonNull ReaderSiteModel site, boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(18960)) {
            {
                long _loopCounter302 = 0;
                for (int position = 0; (ListenerUtil.mutListener.listen(18959) ? (position >= mSites.size()) : (ListenerUtil.mutListener.listen(18958) ? (position <= mSites.size()) : (ListenerUtil.mutListener.listen(18957) ? (position > mSites.size()) : (ListenerUtil.mutListener.listen(18956) ? (position != mSites.size()) : (ListenerUtil.mutListener.listen(18955) ? (position == mSites.size()) : (position < mSites.size())))))); position++) {
                    ListenerUtil.loopListener.listen("_loopCounter302", ++_loopCounter302);
                    if (!ListenerUtil.mutListener.listen(18954)) {
                        if (mSites.get(position).getFeedId() == site.getFeedId()) {
                            if (!ListenerUtil.mutListener.listen(18952)) {
                                mSites.get(position).setFollowing(isFollowed);
                            }
                            if (!ListenerUtil.mutListener.listen(18953)) {
                                notifyItemChanged(position);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void checkFollowStatusForSite(@NonNull ReaderSiteModel site) {
        boolean isFollowed;
        if (site.getSiteId() != 0) {
            isFollowed = ReaderBlogTable.isFollowedBlog(site.getSiteId());
        } else {
            isFollowed = ReaderBlogTable.isFollowedFeed(site.getFeedId());
        }
        if (!ListenerUtil.mutListener.listen(18961)) {
            setSiteFollowed(site, isFollowed);
        }
    }

    class SiteViewHolder extends RecyclerView.ViewHolder {

        private final ReaderSiteSearchResultView mSearchResultView;

        SiteViewHolder(View view) {
            super(view);
            mSearchResultView = (ReaderSiteSearchResultView) view;
            if (!ListenerUtil.mutListener.listen(18965)) {
                view.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (!ListenerUtil.mutListener.listen(18964)) {
                            if ((ListenerUtil.mutListener.listen(18962) ? (isValidPosition(position) || mListener != null) : (isValidPosition(position) && mListener != null))) {
                                ReaderSiteModel site = mSites.get(position);
                                if (!ListenerUtil.mutListener.listen(18963)) {
                                    mListener.onSiteClicked(site);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
