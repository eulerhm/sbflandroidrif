package org.wordpress.android.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.image.BlavatarShape;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SitePickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnSiteClickListener {

        void onSiteClick(SiteRecord site);

        boolean onSiteLongClick(SiteRecord site);
    }

    interface OnSelectedCountChangedListener {

        void onSelectedCountChanged(int numSelected);
    }

    public interface OnDataLoadedListener {

        void onBeforeLoad(boolean isEmpty);

        void onAfterLoad();
    }

    public interface ViewHolderHandler<T extends RecyclerView.ViewHolder> {

        T onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, boolean attachToRoot);

        void onBindViewHolder(T holder, SiteList sites);
    }

    /**
     * Represents the available SitePicker modes
     */
    public enum SitePickerMode {

        DEFAULT_MODE, REBLOG_SELECT_MODE, REBLOG_CONTINUE_MODE, BLOGGING_PROMPTS_MODE;

        public boolean isReblogMode() {
            return (ListenerUtil.mutListener.listen(5488) ? (this == REBLOG_SELECT_MODE && this == REBLOG_CONTINUE_MODE) : (this == REBLOG_SELECT_MODE || this == REBLOG_CONTINUE_MODE));
        }

        public boolean isBloggingPromptsMode() {
            return this == BLOGGING_PROMPTS_MODE;
        }
    }

    @LayoutRes
    private final int mItemLayoutReourceId;

    private static int mBlavatarSz;

    private SiteList mSites = new SiteList();

    private final int mCurrentLocalId;

    private int mSelectedLocalId;

    private final int mSelectedItemBackground;

    private final float mDisabledSiteOpacity;

    private final LayoutInflater mInflater;

    private final HashSet<Integer> mSelectedPositions = new HashSet<>();

    @Nullable
    private final ViewHolderHandler mHeaderHandler;

    @Nullable
    private final ViewHolderHandler mFooterHandler;

    private boolean mIsMultiSelectEnabled;

    private final boolean mIsInSearchMode;

    private boolean mShowHiddenSites = false;

    private final boolean mShowAndReturn;

    private boolean mShowSelfHostedSites = true;

    private String mLastSearch;

    private SiteList mAllSites;

    @Nullable
    private final ArrayList<Integer> mIgnoreSitesIds;

    private OnSiteClickListener mSiteSelectedListener;

    private OnSelectedCountChangedListener mSelectedCountListener;

    @NonNull
    private final OnDataLoadedListener mDataLoadedListener;

    private boolean mIsSingleItemSelectionEnabled;

    private int mSelectedItemPos;

    private SitePickerMode mSitePickerMode = SitePickerMode.DEFAULT_MODE;

    // show recently picked first if there are at least this many blogs
    private static final int RECENTLY_PICKED_THRESHOLD = 11;

    private static final int VIEW_TYPE_HEADER = 0;

    private static final int VIEW_TYPE_ITEM = 1;

    private static final int VIEW_TYPE_FOOTER = 2;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ImageManager mImageManager;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    class SiteViewHolder extends RecyclerView.ViewHolder {

        private final ViewGroup mLayoutContainer;

        private final TextView mTxtTitle;

        private final TextView mTxtDomain;

        private final ImageView mImgBlavatar;

        @Nullable
        private final View mItemDivider;

        @Nullable
        private final View mDivider;

        private Boolean mIsSiteHidden;

        private final RadioButton mSelectedRadioButton;

        SiteViewHolder(View view) {
            super(view);
            mLayoutContainer = view.findViewById(R.id.layout_container);
            mTxtTitle = view.findViewById(R.id.text_title);
            mTxtDomain = view.findViewById(R.id.text_domain);
            mImgBlavatar = view.findViewById(R.id.image_blavatar);
            mItemDivider = view.findViewById(R.id.item_divider);
            mDivider = view.findViewById(R.id.divider);
            if (!ListenerUtil.mutListener.listen(5489)) {
                mIsSiteHidden = null;
            }
            mSelectedRadioButton = view.findViewById(R.id.radio_selected);
        }
    }

    public SitePickerAdapter(Context context, @LayoutRes int itemLayoutResourceId, int currentLocalBlogId, String lastSearch, boolean isInSearchMode, @NonNull OnDataLoadedListener dataLoadedListener, SitePickerMode sitePickerMode, boolean isInEditMode) {
        this(context, itemLayoutResourceId, currentLocalBlogId, lastSearch, isInSearchMode, dataLoadedListener, null, null, null, sitePickerMode, isInEditMode, false);
    }

    public SitePickerAdapter(Context context, @LayoutRes int itemLayoutResourceId, int currentLocalBlogId, String lastSearch, boolean isInSearchMode, @NonNull OnDataLoadedListener dataLoadedListener, @NonNull ViewHolderHandler<?> headerHandler, @Nullable ArrayList<Integer> ignoreSitesIds) {
        this(context, itemLayoutResourceId, currentLocalBlogId, lastSearch, isInSearchMode, dataLoadedListener, headerHandler, null, ignoreSitesIds, SitePickerMode.DEFAULT_MODE, false, false);
    }

    public SitePickerAdapter(Context context, @LayoutRes int itemLayoutResourceId, int currentLocalBlogId, String lastSearch, boolean isInSearchMode, @NonNull OnDataLoadedListener dataLoadedListener, @NonNull ViewHolderHandler<?> headerHandler, @Nullable ViewHolderHandler<?> footerHandler, ArrayList<Integer> ignoreSitesIds, SitePickerMode sitePickerMode, boolean showAndReturn) {
        this(context, itemLayoutResourceId, currentLocalBlogId, lastSearch, isInSearchMode, dataLoadedListener, headerHandler, footerHandler, ignoreSitesIds, sitePickerMode, false, showAndReturn);
    }

    public SitePickerAdapter(Context context, @LayoutRes int itemLayoutResourceId, int currentLocalBlogId, String lastSearch, boolean isInSearchMode, @NonNull OnDataLoadedListener dataLoadedListener, @Nullable ViewHolderHandler<?> headerHandler, @Nullable ViewHolderHandler<?> footerHandler, @Nullable ArrayList<Integer> ignoreSitesIds, SitePickerMode sitePickerMode, boolean isInEditMode, boolean showAndReturn) {
        super();
        if (!ListenerUtil.mutListener.listen(5490)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5491)) {
            setHasStableIds(true);
        }
        if (!ListenerUtil.mutListener.listen(5492)) {
            mLastSearch = StringUtils.notNullStr(lastSearch);
        }
        if (!ListenerUtil.mutListener.listen(5493)) {
            mAllSites = new SiteList();
        }
        mIsInSearchMode = isInSearchMode;
        mItemLayoutReourceId = itemLayoutResourceId;
        mCurrentLocalId = currentLocalBlogId;
        if (!ListenerUtil.mutListener.listen(5494)) {
            mSelectedLocalId = mCurrentLocalId;
        }
        mInflater = LayoutInflater.from(context);
        mDataLoadedListener = dataLoadedListener;
        if (!ListenerUtil.mutListener.listen(5495)) {
            mBlavatarSz = context.getResources().getDimensionPixelSize(R.dimen.blavatar_sz);
        }
        TypedValue disabledAlpha = new TypedValue();
        if (!ListenerUtil.mutListener.listen(5496)) {
            context.getResources().getValue(R.dimen.material_emphasis_disabled, disabledAlpha, true);
        }
        mDisabledSiteOpacity = disabledAlpha.getFloat();
        mSelectedItemBackground = ColorUtils.setAlphaComponent(ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorOnSurface), context.getResources().getInteger(R.integer.selected_list_item_opacity));
        mHeaderHandler = headerHandler;
        mFooterHandler = footerHandler;
        if (!ListenerUtil.mutListener.listen(5497)) {
            mSelectedItemPos = getPositionOffset();
        }
        mIgnoreSitesIds = ignoreSitesIds;
        if (!ListenerUtil.mutListener.listen(5498)) {
            mSitePickerMode = sitePickerMode;
        }
        if (!ListenerUtil.mutListener.listen(5499)) {
            // If site picker is in edit mode, show hidden sites.
            mShowHiddenSites = isInEditMode;
        }
        mShowAndReturn = showAndReturn;
        if (!ListenerUtil.mutListener.listen(5500)) {
            loadSites();
        }
    }

    @Override
    public int getItemCount() {
        return (ListenerUtil.mutListener.listen(5508) ? ((ListenerUtil.mutListener.listen(5504) ? ((mHeaderHandler != null ? 1 : 0) % mSites.size()) : (ListenerUtil.mutListener.listen(5503) ? ((mHeaderHandler != null ? 1 : 0) / mSites.size()) : (ListenerUtil.mutListener.listen(5502) ? ((mHeaderHandler != null ? 1 : 0) * mSites.size()) : (ListenerUtil.mutListener.listen(5501) ? ((mHeaderHandler != null ? 1 : 0) - mSites.size()) : ((mHeaderHandler != null ? 1 : 0) + mSites.size()))))) % (mFooterHandler != null ? 1 : 0)) : (ListenerUtil.mutListener.listen(5507) ? ((ListenerUtil.mutListener.listen(5504) ? ((mHeaderHandler != null ? 1 : 0) % mSites.size()) : (ListenerUtil.mutListener.listen(5503) ? ((mHeaderHandler != null ? 1 : 0) / mSites.size()) : (ListenerUtil.mutListener.listen(5502) ? ((mHeaderHandler != null ? 1 : 0) * mSites.size()) : (ListenerUtil.mutListener.listen(5501) ? ((mHeaderHandler != null ? 1 : 0) - mSites.size()) : ((mHeaderHandler != null ? 1 : 0) + mSites.size()))))) / (mFooterHandler != null ? 1 : 0)) : (ListenerUtil.mutListener.listen(5506) ? ((ListenerUtil.mutListener.listen(5504) ? ((mHeaderHandler != null ? 1 : 0) % mSites.size()) : (ListenerUtil.mutListener.listen(5503) ? ((mHeaderHandler != null ? 1 : 0) / mSites.size()) : (ListenerUtil.mutListener.listen(5502) ? ((mHeaderHandler != null ? 1 : 0) * mSites.size()) : (ListenerUtil.mutListener.listen(5501) ? ((mHeaderHandler != null ? 1 : 0) - mSites.size()) : ((mHeaderHandler != null ? 1 : 0) + mSites.size()))))) * (mFooterHandler != null ? 1 : 0)) : (ListenerUtil.mutListener.listen(5505) ? ((ListenerUtil.mutListener.listen(5504) ? ((mHeaderHandler != null ? 1 : 0) % mSites.size()) : (ListenerUtil.mutListener.listen(5503) ? ((mHeaderHandler != null ? 1 : 0) / mSites.size()) : (ListenerUtil.mutListener.listen(5502) ? ((mHeaderHandler != null ? 1 : 0) * mSites.size()) : (ListenerUtil.mutListener.listen(5501) ? ((mHeaderHandler != null ? 1 : 0) - mSites.size()) : ((mHeaderHandler != null ? 1 : 0) + mSites.size()))))) - (mFooterHandler != null ? 1 : 0)) : ((ListenerUtil.mutListener.listen(5504) ? ((mHeaderHandler != null ? 1 : 0) % mSites.size()) : (ListenerUtil.mutListener.listen(5503) ? ((mHeaderHandler != null ? 1 : 0) / mSites.size()) : (ListenerUtil.mutListener.listen(5502) ? ((mHeaderHandler != null ? 1 : 0) * mSites.size()) : (ListenerUtil.mutListener.listen(5501) ? ((mHeaderHandler != null ? 1 : 0) - mSites.size()) : ((mHeaderHandler != null ? 1 : 0) + mSites.size()))))) + (mFooterHandler != null ? 1 : 0))))));
    }

    private int getSitesCount() {
        return mSites.size();
    }

    @Override
    public long getItemId(int position) {
        int viewType = getItemViewType(position);
        if ((ListenerUtil.mutListener.listen(5513) ? (viewType >= VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5512) ? (viewType <= VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5511) ? (viewType > VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5510) ? (viewType < VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5509) ? (viewType != VIEW_TYPE_HEADER) : (viewType == VIEW_TYPE_HEADER))))))) {
            return -1;
        } else if ((ListenerUtil.mutListener.listen(5518) ? (viewType >= VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5517) ? (viewType <= VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5516) ? (viewType > VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5515) ? (viewType < VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5514) ? (viewType != VIEW_TYPE_FOOTER) : (viewType == VIEW_TYPE_FOOTER))))))) {
            return -2;
        } else {
            return getItem(position).mLocalId;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if ((ListenerUtil.mutListener.listen(5524) ? (mHeaderHandler != null || (ListenerUtil.mutListener.listen(5523) ? (position >= 0) : (ListenerUtil.mutListener.listen(5522) ? (position <= 0) : (ListenerUtil.mutListener.listen(5521) ? (position > 0) : (ListenerUtil.mutListener.listen(5520) ? (position < 0) : (ListenerUtil.mutListener.listen(5519) ? (position != 0) : (position == 0))))))) : (mHeaderHandler != null && (ListenerUtil.mutListener.listen(5523) ? (position >= 0) : (ListenerUtil.mutListener.listen(5522) ? (position <= 0) : (ListenerUtil.mutListener.listen(5521) ? (position > 0) : (ListenerUtil.mutListener.listen(5520) ? (position < 0) : (ListenerUtil.mutListener.listen(5519) ? (position != 0) : (position == 0))))))))) {
            return VIEW_TYPE_HEADER;
        } else if ((ListenerUtil.mutListener.listen(5534) ? (mFooterHandler != null || (ListenerUtil.mutListener.listen(5533) ? (position >= (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5532) ? (position <= (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5531) ? (position > (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5530) ? (position < (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5529) ? (position != (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : (mFooterHandler != null && (ListenerUtil.mutListener.listen(5533) ? (position >= (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5532) ? (position <= (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5531) ? (position > (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5530) ? (position < (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5529) ? (position != (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(5528) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5527) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5526) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5525) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))))) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    private SiteRecord getItem(int position) {
        return mSites.get((ListenerUtil.mutListener.listen(5538) ? (position % getPositionOffset()) : (ListenerUtil.mutListener.listen(5537) ? (position / getPositionOffset()) : (ListenerUtil.mutListener.listen(5536) ? (position * getPositionOffset()) : (ListenerUtil.mutListener.listen(5535) ? (position + getPositionOffset()) : (position - getPositionOffset()))))));
    }

    private int getPositionOffset() {
        return (mHeaderHandler == null ? 0 : 1);
    }

    void setOnSelectedCountChangedListener(OnSelectedCountChangedListener listener) {
        if (!ListenerUtil.mutListener.listen(5539)) {
            mSelectedCountListener = listener;
        }
    }

    public void setOnSiteClickListener(OnSiteClickListener listener) {
        if (!ListenerUtil.mutListener.listen(5540)) {
            mSiteSelectedListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(5541)) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if ((ListenerUtil.mutListener.listen(5546) ? (viewType >= VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5545) ? (viewType <= VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5544) ? (viewType > VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5543) ? (viewType < VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5542) ? (viewType != VIEW_TYPE_HEADER) : (viewType == VIEW_TYPE_HEADER))))))) {
            return mHeaderHandler.onCreateViewHolder(mInflater, parent, false);
        } else if ((ListenerUtil.mutListener.listen(5551) ? (viewType >= VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5550) ? (viewType <= VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5549) ? (viewType > VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5548) ? (viewType < VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5547) ? (viewType != VIEW_TYPE_FOOTER) : (viewType == VIEW_TYPE_FOOTER))))))) {
            return mFooterHandler.onCreateViewHolder(mInflater, parent, false);
        } else {
            View itemView = mInflater.inflate(mItemLayoutReourceId, parent, false);
            return new SiteViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (!ListenerUtil.mutListener.listen(5558)) {
            if ((ListenerUtil.mutListener.listen(5556) ? (viewType >= VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5555) ? (viewType <= VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5554) ? (viewType > VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5553) ? (viewType < VIEW_TYPE_HEADER) : (ListenerUtil.mutListener.listen(5552) ? (viewType != VIEW_TYPE_HEADER) : (viewType == VIEW_TYPE_HEADER))))))) {
                if (!ListenerUtil.mutListener.listen(5557)) {
                    mHeaderHandler.onBindViewHolder(viewHolder, mSites);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5565)) {
            if ((ListenerUtil.mutListener.listen(5563) ? (viewType >= VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5562) ? (viewType <= VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5561) ? (viewType > VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5560) ? (viewType < VIEW_TYPE_FOOTER) : (ListenerUtil.mutListener.listen(5559) ? (viewType != VIEW_TYPE_FOOTER) : (viewType == VIEW_TYPE_FOOTER))))))) {
                if (!ListenerUtil.mutListener.listen(5564)) {
                    mFooterHandler.onBindViewHolder(viewHolder, mSites);
                }
                return;
            }
        }
        SiteRecord site = getItem(position);
        final SiteViewHolder holder = (SiteViewHolder) viewHolder;
        if (!ListenerUtil.mutListener.listen(5566)) {
            holder.mTxtTitle.setText(site.getBlogNameOrHomeURL());
        }
        if (!ListenerUtil.mutListener.listen(5567)) {
            holder.mTxtDomain.setText(site.mHomeURL);
        }
        if (!ListenerUtil.mutListener.listen(5568)) {
            mImageManager.loadImageWithCorners(holder.mImgBlavatar, site.getBlavatarType(), site.mBlavatarUrl, DisplayUtils.dpToPx(holder.itemView.getContext(), 4));
        }
        if (!ListenerUtil.mutListener.listen(5587)) {
            if ((ListenerUtil.mutListener.listen(5584) ? ((ListenerUtil.mutListener.listen(5577) ? (((ListenerUtil.mutListener.listen(5575) ? ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) || mSitePickerMode == SitePickerMode.DEFAULT_MODE) : ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) && mSitePickerMode == SitePickerMode.DEFAULT_MODE))) && ((ListenerUtil.mutListener.listen(5576) ? (mIsMultiSelectEnabled || isItemSelected(position)) : (mIsMultiSelectEnabled && isItemSelected(position))))) : (((ListenerUtil.mutListener.listen(5575) ? ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) || mSitePickerMode == SitePickerMode.DEFAULT_MODE) : ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) && mSitePickerMode == SitePickerMode.DEFAULT_MODE))) || ((ListenerUtil.mutListener.listen(5576) ? (mIsMultiSelectEnabled || isItemSelected(position)) : (mIsMultiSelectEnabled && isItemSelected(position)))))) && ((ListenerUtil.mutListener.listen(5583) ? (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE || (ListenerUtil.mutListener.listen(5582) ? (mSelectedLocalId >= site.mLocalId) : (ListenerUtil.mutListener.listen(5581) ? (mSelectedLocalId <= site.mLocalId) : (ListenerUtil.mutListener.listen(5580) ? (mSelectedLocalId > site.mLocalId) : (ListenerUtil.mutListener.listen(5579) ? (mSelectedLocalId < site.mLocalId) : (ListenerUtil.mutListener.listen(5578) ? (mSelectedLocalId != site.mLocalId) : (mSelectedLocalId == site.mLocalId))))))) : (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE && (ListenerUtil.mutListener.listen(5582) ? (mSelectedLocalId >= site.mLocalId) : (ListenerUtil.mutListener.listen(5581) ? (mSelectedLocalId <= site.mLocalId) : (ListenerUtil.mutListener.listen(5580) ? (mSelectedLocalId > site.mLocalId) : (ListenerUtil.mutListener.listen(5579) ? (mSelectedLocalId < site.mLocalId) : (ListenerUtil.mutListener.listen(5578) ? (mSelectedLocalId != site.mLocalId) : (mSelectedLocalId == site.mLocalId)))))))))) : ((ListenerUtil.mutListener.listen(5577) ? (((ListenerUtil.mutListener.listen(5575) ? ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) || mSitePickerMode == SitePickerMode.DEFAULT_MODE) : ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) && mSitePickerMode == SitePickerMode.DEFAULT_MODE))) && ((ListenerUtil.mutListener.listen(5576) ? (mIsMultiSelectEnabled || isItemSelected(position)) : (mIsMultiSelectEnabled && isItemSelected(position))))) : (((ListenerUtil.mutListener.listen(5575) ? ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) || mSitePickerMode == SitePickerMode.DEFAULT_MODE) : ((ListenerUtil.mutListener.listen(5574) ? ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) || !mIsMultiSelectEnabled) : ((ListenerUtil.mutListener.listen(5573) ? (site.mLocalId >= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5572) ? (site.mLocalId <= mCurrentLocalId) : (ListenerUtil.mutListener.listen(5571) ? (site.mLocalId > mCurrentLocalId) : (ListenerUtil.mutListener.listen(5570) ? (site.mLocalId < mCurrentLocalId) : (ListenerUtil.mutListener.listen(5569) ? (site.mLocalId != mCurrentLocalId) : (site.mLocalId == mCurrentLocalId)))))) && !mIsMultiSelectEnabled)) && mSitePickerMode == SitePickerMode.DEFAULT_MODE))) || ((ListenerUtil.mutListener.listen(5576) ? (mIsMultiSelectEnabled || isItemSelected(position)) : (mIsMultiSelectEnabled && isItemSelected(position)))))) || ((ListenerUtil.mutListener.listen(5583) ? (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE || (ListenerUtil.mutListener.listen(5582) ? (mSelectedLocalId >= site.mLocalId) : (ListenerUtil.mutListener.listen(5581) ? (mSelectedLocalId <= site.mLocalId) : (ListenerUtil.mutListener.listen(5580) ? (mSelectedLocalId > site.mLocalId) : (ListenerUtil.mutListener.listen(5579) ? (mSelectedLocalId < site.mLocalId) : (ListenerUtil.mutListener.listen(5578) ? (mSelectedLocalId != site.mLocalId) : (mSelectedLocalId == site.mLocalId))))))) : (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE && (ListenerUtil.mutListener.listen(5582) ? (mSelectedLocalId >= site.mLocalId) : (ListenerUtil.mutListener.listen(5581) ? (mSelectedLocalId <= site.mLocalId) : (ListenerUtil.mutListener.listen(5580) ? (mSelectedLocalId > site.mLocalId) : (ListenerUtil.mutListener.listen(5579) ? (mSelectedLocalId < site.mLocalId) : (ListenerUtil.mutListener.listen(5578) ? (mSelectedLocalId != site.mLocalId) : (mSelectedLocalId == site.mLocalId)))))))))))) {
                if (!ListenerUtil.mutListener.listen(5586)) {
                    holder.mLayoutContainer.setBackgroundColor(mSelectedItemBackground);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5585)) {
                    holder.mLayoutContainer.setBackground(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5592)) {
            // different styling for visible/hidden sites
            if ((ListenerUtil.mutListener.listen(5588) ? (holder.mIsSiteHidden == null && holder.mIsSiteHidden != site.mIsHidden) : (holder.mIsSiteHidden == null || holder.mIsSiteHidden != site.mIsHidden))) {
                if (!ListenerUtil.mutListener.listen(5589)) {
                    holder.mIsSiteHidden = site.mIsHidden;
                }
                if (!ListenerUtil.mutListener.listen(5590)) {
                    holder.mTxtTitle.setAlpha(site.mIsHidden ? mDisabledSiteOpacity : 1f);
                }
                if (!ListenerUtil.mutListener.listen(5591)) {
                    holder.mImgBlavatar.setAlpha(site.mIsHidden ? mDisabledSiteOpacity : 1f);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5603)) {
            if (holder.mItemDivider != null) {
                boolean showDivider = (ListenerUtil.mutListener.listen(5601) ? (position >= (ListenerUtil.mutListener.listen(5596) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5595) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5594) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5593) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5600) ? (position <= (ListenerUtil.mutListener.listen(5596) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5595) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5594) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5593) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5599) ? (position > (ListenerUtil.mutListener.listen(5596) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5595) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5594) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5593) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5598) ? (position != (ListenerUtil.mutListener.listen(5596) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5595) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5594) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5593) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5597) ? (position == (ListenerUtil.mutListener.listen(5596) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5595) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5594) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5593) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position < (ListenerUtil.mutListener.listen(5596) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5595) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5594) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5593) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))));
                if (!ListenerUtil.mutListener.listen(5602)) {
                    holder.mItemDivider.setVisibility(showDivider ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5621)) {
            if (holder.mDivider != null) {
                // only show divider after last recent pick
                boolean showDivider = (ListenerUtil.mutListener.listen(5619) ? ((ListenerUtil.mutListener.listen(5614) ? ((ListenerUtil.mutListener.listen(5604) ? (site.mIsRecentPick || !mIsInSearchMode) : (site.mIsRecentPick && !mIsInSearchMode)) || (ListenerUtil.mutListener.listen(5613) ? (position >= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5612) ? (position <= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5611) ? (position > (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5610) ? (position != (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5609) ? (position == (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position < (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(5604) ? (site.mIsRecentPick || !mIsInSearchMode) : (site.mIsRecentPick && !mIsInSearchMode)) && (ListenerUtil.mutListener.listen(5613) ? (position >= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5612) ? (position <= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5611) ? (position > (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5610) ? (position != (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5609) ? (position == (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position < (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))))) || !getItem((ListenerUtil.mutListener.listen(5618) ? (position % 1) : (ListenerUtil.mutListener.listen(5617) ? (position / 1) : (ListenerUtil.mutListener.listen(5616) ? (position * 1) : (ListenerUtil.mutListener.listen(5615) ? (position - 1) : (position + 1)))))).mIsRecentPick) : ((ListenerUtil.mutListener.listen(5614) ? ((ListenerUtil.mutListener.listen(5604) ? (site.mIsRecentPick || !mIsInSearchMode) : (site.mIsRecentPick && !mIsInSearchMode)) || (ListenerUtil.mutListener.listen(5613) ? (position >= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5612) ? (position <= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5611) ? (position > (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5610) ? (position != (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5609) ? (position == (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position < (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(5604) ? (site.mIsRecentPick || !mIsInSearchMode) : (site.mIsRecentPick && !mIsInSearchMode)) && (ListenerUtil.mutListener.listen(5613) ? (position >= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5612) ? (position <= (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5611) ? (position > (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5610) ? (position != (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(5609) ? (position == (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position < (ListenerUtil.mutListener.listen(5608) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(5607) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(5606) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(5605) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))))) && !getItem((ListenerUtil.mutListener.listen(5618) ? (position % 1) : (ListenerUtil.mutListener.listen(5617) ? (position / 1) : (ListenerUtil.mutListener.listen(5616) ? (position * 1) : (ListenerUtil.mutListener.listen(5615) ? (position - 1) : (position + 1)))))).mIsRecentPick));
                if (!ListenerUtil.mutListener.listen(5620)) {
                    holder.mDivider.setVisibility(showDivider ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5627)) {
            if ((ListenerUtil.mutListener.listen(5622) ? (mIsMultiSelectEnabled && mSiteSelectedListener != null) : (mIsMultiSelectEnabled || mSiteSelectedListener != null))) {
                if (!ListenerUtil.mutListener.listen(5623)) {
                    holder.itemView.setOnClickListener(view -> {
                        int clickedPosition = holder.getAdapterPosition();
                        if (isValidPosition(clickedPosition)) {
                            if (mIsMultiSelectEnabled) {
                                toggleSelection(clickedPosition);
                            } else if (mSiteSelectedListener != null) {
                                if (mSitePickerMode.isReblogMode()) {
                                    mSitePickerMode = SitePickerMode.REBLOG_CONTINUE_MODE;
                                    mSelectedLocalId = site.mLocalId;
                                    selectSingleItem(clickedPosition);
                                }
                                mSiteSelectedListener.onSiteClick(getItem(clickedPosition));
                            }
                        } else {
                            AppLog.w(AppLog.T.MAIN, "site picker > invalid clicked position " + clickedPosition);
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(5626)) {
                    if (!mSitePickerMode.isReblogMode()) {
                        if (!ListenerUtil.mutListener.listen(5624)) {
                            holder.itemView.setOnLongClickListener(view -> {
                                int clickedPosition = holder.getAdapterPosition();
                                if (isValidPosition(clickedPosition)) {
                                    if (mIsMultiSelectEnabled) {
                                        toggleSelection(clickedPosition);
                                        return true;
                                    } else if (mSiteSelectedListener != null) {
                                        return mSiteSelectedListener.onSiteLongClick(getItem(clickedPosition));
                                    }
                                } else {
                                    AppLog.w(AppLog.T.MAIN, "site picker > invalid clicked position " + clickedPosition);
                                }
                                return false;
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(5625)) {
                            ViewExtensionsKt.redirectContextClickToLongPressListener(holder.itemView);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5645)) {
            if (mIsSingleItemSelectionEnabled) {
                if (!ListenerUtil.mutListener.listen(5644)) {
                    if ((ListenerUtil.mutListener.listen(5634) ? (getSitesCount() >= 1) : (ListenerUtil.mutListener.listen(5633) ? (getSitesCount() > 1) : (ListenerUtil.mutListener.listen(5632) ? (getSitesCount() < 1) : (ListenerUtil.mutListener.listen(5631) ? (getSitesCount() != 1) : (ListenerUtil.mutListener.listen(5630) ? (getSitesCount() == 1) : (getSitesCount() <= 1))))))) {
                        if (!ListenerUtil.mutListener.listen(5643)) {
                            holder.mSelectedRadioButton.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5635)) {
                            holder.mSelectedRadioButton.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(5641)) {
                            holder.mSelectedRadioButton.setChecked((ListenerUtil.mutListener.listen(5640) ? (mSelectedItemPos >= position) : (ListenerUtil.mutListener.listen(5639) ? (mSelectedItemPos <= position) : (ListenerUtil.mutListener.listen(5638) ? (mSelectedItemPos > position) : (ListenerUtil.mutListener.listen(5637) ? (mSelectedItemPos < position) : (ListenerUtil.mutListener.listen(5636) ? (mSelectedItemPos != position) : (mSelectedItemPos == position)))))));
                        }
                        if (!ListenerUtil.mutListener.listen(5642)) {
                            holder.mLayoutContainer.setOnClickListener(v -> selectSingleItem(holder.getAdapterPosition()));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5629)) {
                    if (holder.mSelectedRadioButton != null) {
                        if (!ListenerUtil.mutListener.listen(5628)) {
                            holder.mSelectedRadioButton.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void selectSingleItem(final int newItemPosition) {
        if (!ListenerUtil.mutListener.listen(5646)) {
            // clear last selected item
            notifyItemChanged(mSelectedItemPos);
        }
        if (!ListenerUtil.mutListener.listen(5647)) {
            mSelectedItemPos = newItemPosition;
        }
        if (!ListenerUtil.mutListener.listen(5648)) {
            // select new item
            notifyItemChanged(mSelectedItemPos);
        }
    }

    public void setSingleItemSelectionEnabled(final boolean enabled) {
        if (!ListenerUtil.mutListener.listen(5651)) {
            if (enabled != mIsSingleItemSelectionEnabled) {
                if (!ListenerUtil.mutListener.listen(5649)) {
                    mIsSingleItemSelectionEnabled = enabled;
                }
                if (!ListenerUtil.mutListener.listen(5650)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    public void findAndSelect(final int lastUsedBlogLocalId) {
        int positionInSitesArray = mSites.indexOfSiteId(lastUsedBlogLocalId);
        if (!ListenerUtil.mutListener.listen(5662)) {
            if ((ListenerUtil.mutListener.listen(5656) ? (positionInSitesArray >= -1) : (ListenerUtil.mutListener.listen(5655) ? (positionInSitesArray <= -1) : (ListenerUtil.mutListener.listen(5654) ? (positionInSitesArray > -1) : (ListenerUtil.mutListener.listen(5653) ? (positionInSitesArray < -1) : (ListenerUtil.mutListener.listen(5652) ? (positionInSitesArray == -1) : (positionInSitesArray != -1))))))) {
                if (!ListenerUtil.mutListener.listen(5661)) {
                    selectSingleItem((ListenerUtil.mutListener.listen(5660) ? (positionInSitesArray % getPositionOffset()) : (ListenerUtil.mutListener.listen(5659) ? (positionInSitesArray / getPositionOffset()) : (ListenerUtil.mutListener.listen(5658) ? (positionInSitesArray * getPositionOffset()) : (ListenerUtil.mutListener.listen(5657) ? (positionInSitesArray - getPositionOffset()) : (positionInSitesArray + getPositionOffset()))))));
                }
            }
        }
    }

    public int getSelectedItemLocalId() {
        return (ListenerUtil.mutListener.listen(5667) ? (mSites.size() >= 0) : (ListenerUtil.mutListener.listen(5666) ? (mSites.size() <= 0) : (ListenerUtil.mutListener.listen(5665) ? (mSites.size() > 0) : (ListenerUtil.mutListener.listen(5664) ? (mSites.size() < 0) : (ListenerUtil.mutListener.listen(5663) ? (mSites.size() == 0) : (mSites.size() != 0)))))) ? getItem(mSelectedItemPos).mLocalId : -1;
    }

    public int getItemPosByLocalId(int localId) {
        int positionInSitesArray = mSites.indexOfSiteId(localId);
        return (ListenerUtil.mutListener.listen(5678) ? ((ListenerUtil.mutListener.listen(5672) ? (mSites.size() >= 0) : (ListenerUtil.mutListener.listen(5671) ? (mSites.size() <= 0) : (ListenerUtil.mutListener.listen(5670) ? (mSites.size() > 0) : (ListenerUtil.mutListener.listen(5669) ? (mSites.size() < 0) : (ListenerUtil.mutListener.listen(5668) ? (mSites.size() == 0) : (mSites.size() != 0)))))) || (ListenerUtil.mutListener.listen(5677) ? (positionInSitesArray >= -1) : (ListenerUtil.mutListener.listen(5676) ? (positionInSitesArray <= -1) : (ListenerUtil.mutListener.listen(5675) ? (positionInSitesArray < -1) : (ListenerUtil.mutListener.listen(5674) ? (positionInSitesArray != -1) : (ListenerUtil.mutListener.listen(5673) ? (positionInSitesArray == -1) : (positionInSitesArray > -1))))))) : ((ListenerUtil.mutListener.listen(5672) ? (mSites.size() >= 0) : (ListenerUtil.mutListener.listen(5671) ? (mSites.size() <= 0) : (ListenerUtil.mutListener.listen(5670) ? (mSites.size() > 0) : (ListenerUtil.mutListener.listen(5669) ? (mSites.size() < 0) : (ListenerUtil.mutListener.listen(5668) ? (mSites.size() == 0) : (mSites.size() != 0)))))) && (ListenerUtil.mutListener.listen(5677) ? (positionInSitesArray >= -1) : (ListenerUtil.mutListener.listen(5676) ? (positionInSitesArray <= -1) : (ListenerUtil.mutListener.listen(5675) ? (positionInSitesArray < -1) : (ListenerUtil.mutListener.listen(5674) ? (positionInSitesArray != -1) : (ListenerUtil.mutListener.listen(5673) ? (positionInSitesArray == -1) : (positionInSitesArray > -1)))))))) ? positionInSitesArray : -1;
    }

    String getLastSearch() {
        return mLastSearch;
    }

    void setLastSearch(String lastSearch) {
        if (!ListenerUtil.mutListener.listen(5679)) {
            mLastSearch = lastSearch;
        }
    }

    boolean getIsInSearchMode() {
        return mIsInSearchMode;
    }

    void searchSites(String searchText) {
        if (!ListenerUtil.mutListener.listen(5680)) {
            mLastSearch = searchText;
        }
        if (!ListenerUtil.mutListener.listen(5681)) {
            mSites = filteredSitesByText(mAllSites);
        }
        if (!ListenerUtil.mutListener.listen(5682)) {
            notifyDataSetChanged();
        }
    }

    private boolean isValidPosition(int position) {
        if (isNewLoginEpilogueScreenEnabled()) {
            return ((ListenerUtil.mutListener.listen(5704) ? ((ListenerUtil.mutListener.listen(5698) ? (position <= 0) : (ListenerUtil.mutListener.listen(5697) ? (position > 0) : (ListenerUtil.mutListener.listen(5696) ? (position < 0) : (ListenerUtil.mutListener.listen(5695) ? (position != 0) : (ListenerUtil.mutListener.listen(5694) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(5703) ? (position >= mSites.size()) : (ListenerUtil.mutListener.listen(5702) ? (position > mSites.size()) : (ListenerUtil.mutListener.listen(5701) ? (position < mSites.size()) : (ListenerUtil.mutListener.listen(5700) ? (position != mSites.size()) : (ListenerUtil.mutListener.listen(5699) ? (position == mSites.size()) : (position <= mSites.size()))))))) : ((ListenerUtil.mutListener.listen(5698) ? (position <= 0) : (ListenerUtil.mutListener.listen(5697) ? (position > 0) : (ListenerUtil.mutListener.listen(5696) ? (position < 0) : (ListenerUtil.mutListener.listen(5695) ? (position != 0) : (ListenerUtil.mutListener.listen(5694) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(5703) ? (position >= mSites.size()) : (ListenerUtil.mutListener.listen(5702) ? (position > mSites.size()) : (ListenerUtil.mutListener.listen(5701) ? (position < mSites.size()) : (ListenerUtil.mutListener.listen(5700) ? (position != mSites.size()) : (ListenerUtil.mutListener.listen(5699) ? (position == mSites.size()) : (position <= mSites.size())))))))));
        } else {
            return ((ListenerUtil.mutListener.listen(5693) ? ((ListenerUtil.mutListener.listen(5687) ? (position <= 0) : (ListenerUtil.mutListener.listen(5686) ? (position > 0) : (ListenerUtil.mutListener.listen(5685) ? (position < 0) : (ListenerUtil.mutListener.listen(5684) ? (position != 0) : (ListenerUtil.mutListener.listen(5683) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(5692) ? (position >= mSites.size()) : (ListenerUtil.mutListener.listen(5691) ? (position <= mSites.size()) : (ListenerUtil.mutListener.listen(5690) ? (position > mSites.size()) : (ListenerUtil.mutListener.listen(5689) ? (position != mSites.size()) : (ListenerUtil.mutListener.listen(5688) ? (position == mSites.size()) : (position < mSites.size()))))))) : ((ListenerUtil.mutListener.listen(5687) ? (position <= 0) : (ListenerUtil.mutListener.listen(5686) ? (position > 0) : (ListenerUtil.mutListener.listen(5685) ? (position < 0) : (ListenerUtil.mutListener.listen(5684) ? (position != 0) : (ListenerUtil.mutListener.listen(5683) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(5692) ? (position >= mSites.size()) : (ListenerUtil.mutListener.listen(5691) ? (position <= mSites.size()) : (ListenerUtil.mutListener.listen(5690) ? (position > mSites.size()) : (ListenerUtil.mutListener.listen(5689) ? (position != mSites.size()) : (ListenerUtil.mutListener.listen(5688) ? (position == mSites.size()) : (position < mSites.size())))))))));
        }
    }

    private boolean isNewLoginEpilogueScreenEnabled() {
        return (ListenerUtil.mutListener.listen(5705) ? (mBuildConfigWrapper.isSiteCreationEnabled() || !mShowAndReturn) : (mBuildConfigWrapper.isSiteCreationEnabled() && !mShowAndReturn));
    }

    /*
     * called when the user chooses to edit the visibility of wp.com blogs
     */
    void setEnableEditMode(boolean enable, HashSet<Integer> selectedPositions) {
        if (!ListenerUtil.mutListener.listen(5706)) {
            if (mIsMultiSelectEnabled == enable) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5711)) {
            if (enable) {
                if (!ListenerUtil.mutListener.listen(5709)) {
                    mShowHiddenSites = true;
                }
                if (!ListenerUtil.mutListener.listen(5710)) {
                    mShowSelfHostedSites = false;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5707)) {
                    mShowHiddenSites = false;
                }
                if (!ListenerUtil.mutListener.listen(5708)) {
                    mShowSelfHostedSites = true;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5712)) {
            mIsMultiSelectEnabled = enable;
        }
        if (!ListenerUtil.mutListener.listen(5715)) {
            // Adapter's mSelectedPositions. Otherwise, reset the selected positions.
            if (!selectedPositions.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(5714)) {
                    mSelectedPositions.addAll(selectedPositions);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5713)) {
                    mSelectedPositions.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5716)) {
            loadSites();
        }
    }

    int getNumSelected() {
        return mSelectedPositions.size();
    }

    int getNumHiddenSelected() {
        int numHidden = 0;
        if (!ListenerUtil.mutListener.listen(5720)) {
            {
                long _loopCounter129 = 0;
                for (Integer i : mSelectedPositions) {
                    ListenerUtil.loopListener.listen("_loopCounter129", ++_loopCounter129);
                    if (!ListenerUtil.mutListener.listen(5719)) {
                        if ((ListenerUtil.mutListener.listen(5717) ? (isValidPosition(i) || mSites.get(i).mIsHidden) : (isValidPosition(i) && mSites.get(i).mIsHidden))) {
                            if (!ListenerUtil.mutListener.listen(5718)) {
                                numHidden++;
                            }
                        }
                    }
                }
            }
        }
        return numHidden;
    }

    int getNumVisibleSelected() {
        int numVisible = 0;
        if (!ListenerUtil.mutListener.listen(5729)) {
            {
                long _loopCounter130 = 0;
                for (Integer i : mSelectedPositions) {
                    ListenerUtil.loopListener.listen("_loopCounter130", ++_loopCounter130);
                    if (!ListenerUtil.mutListener.listen(5728)) {
                        if ((ListenerUtil.mutListener.listen(5726) ? ((ListenerUtil.mutListener.listen(5725) ? (i >= mSites.size()) : (ListenerUtil.mutListener.listen(5724) ? (i <= mSites.size()) : (ListenerUtil.mutListener.listen(5723) ? (i > mSites.size()) : (ListenerUtil.mutListener.listen(5722) ? (i != mSites.size()) : (ListenerUtil.mutListener.listen(5721) ? (i == mSites.size()) : (i < mSites.size())))))) || !mSites.get(i).mIsHidden) : ((ListenerUtil.mutListener.listen(5725) ? (i >= mSites.size()) : (ListenerUtil.mutListener.listen(5724) ? (i <= mSites.size()) : (ListenerUtil.mutListener.listen(5723) ? (i > mSites.size()) : (ListenerUtil.mutListener.listen(5722) ? (i != mSites.size()) : (ListenerUtil.mutListener.listen(5721) ? (i == mSites.size()) : (i < mSites.size())))))) && !mSites.get(i).mIsHidden))) {
                            if (!ListenerUtil.mutListener.listen(5727)) {
                                numVisible++;
                            }
                        }
                    }
                }
            }
        }
        return numVisible;
    }

    private void toggleSelection(int position) {
        if (!ListenerUtil.mutListener.listen(5730)) {
            setItemSelected(position, !isItemSelected(position));
        }
    }

    private boolean isItemSelected(int position) {
        return mSelectedPositions.contains(position);
    }

    private void setItemSelected(int position, boolean isSelected) {
        if (!ListenerUtil.mutListener.listen(5731)) {
            if (isItemSelected(position) == isSelected) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5734)) {
            if (isSelected) {
                if (!ListenerUtil.mutListener.listen(5733)) {
                    mSelectedPositions.add(position);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5732)) {
                    mSelectedPositions.remove(position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5735)) {
            notifyItemChanged(position);
        }
        if (!ListenerUtil.mutListener.listen(5737)) {
            if (mSelectedCountListener != null) {
                if (!ListenerUtil.mutListener.listen(5736)) {
                    mSelectedCountListener.onSelectedCountChanged(getNumSelected());
                }
            }
        }
    }

    void selectAll() {
        if (!ListenerUtil.mutListener.listen(5743)) {
            if ((ListenerUtil.mutListener.listen(5742) ? (mSelectedPositions.size() >= mSites.size()) : (ListenerUtil.mutListener.listen(5741) ? (mSelectedPositions.size() <= mSites.size()) : (ListenerUtil.mutListener.listen(5740) ? (mSelectedPositions.size() > mSites.size()) : (ListenerUtil.mutListener.listen(5739) ? (mSelectedPositions.size() < mSites.size()) : (ListenerUtil.mutListener.listen(5738) ? (mSelectedPositions.size() != mSites.size()) : (mSelectedPositions.size() == mSites.size()))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5744)) {
            mSelectedPositions.clear();
        }
        if (!ListenerUtil.mutListener.listen(5751)) {
            {
                long _loopCounter131 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5750) ? (i >= mSites.size()) : (ListenerUtil.mutListener.listen(5749) ? (i <= mSites.size()) : (ListenerUtil.mutListener.listen(5748) ? (i > mSites.size()) : (ListenerUtil.mutListener.listen(5747) ? (i != mSites.size()) : (ListenerUtil.mutListener.listen(5746) ? (i == mSites.size()) : (i < mSites.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter131", ++_loopCounter131);
                    if (!ListenerUtil.mutListener.listen(5745)) {
                        mSelectedPositions.add(i);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5752)) {
            notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(5754)) {
            if (mSelectedCountListener != null) {
                if (!ListenerUtil.mutListener.listen(5753)) {
                    mSelectedCountListener.onSelectedCountChanged(getNumSelected());
                }
            }
        }
    }

    void deselectAll() {
        if (!ListenerUtil.mutListener.listen(5760)) {
            if ((ListenerUtil.mutListener.listen(5759) ? (mSelectedPositions.size() >= 0) : (ListenerUtil.mutListener.listen(5758) ? (mSelectedPositions.size() <= 0) : (ListenerUtil.mutListener.listen(5757) ? (mSelectedPositions.size() > 0) : (ListenerUtil.mutListener.listen(5756) ? (mSelectedPositions.size() < 0) : (ListenerUtil.mutListener.listen(5755) ? (mSelectedPositions.size() != 0) : (mSelectedPositions.size() == 0))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5761)) {
            mSelectedPositions.clear();
        }
        if (!ListenerUtil.mutListener.listen(5762)) {
            notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(5764)) {
            if (mSelectedCountListener != null) {
                if (!ListenerUtil.mutListener.listen(5763)) {
                    mSelectedCountListener.onSelectedCountChanged(getNumSelected());
                }
            }
        }
    }

    void clearReblogSelection() {
        if (!ListenerUtil.mutListener.listen(5765)) {
            mSitePickerMode = SitePickerMode.REBLOG_SELECT_MODE;
        }
        if (!ListenerUtil.mutListener.listen(5766)) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    private SiteList getSelectedSites() {
        SiteList sites = new SiteList();
        if (!ListenerUtil.mutListener.listen(5767)) {
            if (!mIsMultiSelectEnabled) {
                return sites;
            }
        }
        if (!ListenerUtil.mutListener.listen(5770)) {
            {
                long _loopCounter132 = 0;
                for (Integer position : mSelectedPositions) {
                    ListenerUtil.loopListener.listen("_loopCounter132", ++_loopCounter132);
                    if (!ListenerUtil.mutListener.listen(5769)) {
                        if (isValidPosition(position)) {
                            if (!ListenerUtil.mutListener.listen(5768)) {
                                sites.add(mSites.get(position));
                            }
                        }
                    }
                }
            }
        }
        return sites;
    }

    public HashSet<Integer> getSelectedPositions() {
        return mSelectedPositions;
    }

    SiteList getHiddenSites() {
        SiteList hiddenSites = new SiteList();
        if (!ListenerUtil.mutListener.listen(5773)) {
            {
                long _loopCounter133 = 0;
                for (SiteRecord site : mSites) {
                    ListenerUtil.loopListener.listen("_loopCounter133", ++_loopCounter133);
                    if (!ListenerUtil.mutListener.listen(5772)) {
                        if (site.mIsHidden) {
                            if (!ListenerUtil.mutListener.listen(5771)) {
                                hiddenSites.add(site);
                            }
                        }
                    }
                }
            }
        }
        return hiddenSites;
    }

    Set<SiteRecord> setVisibilityForSelectedSites(boolean makeVisible) {
        SiteList sites = getSelectedSites();
        Set<SiteRecord> changeSet = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(5800)) {
            if ((ListenerUtil.mutListener.listen(5778) ? (sites.size() >= 0) : (ListenerUtil.mutListener.listen(5777) ? (sites.size() <= 0) : (ListenerUtil.mutListener.listen(5776) ? (sites.size() < 0) : (ListenerUtil.mutListener.listen(5775) ? (sites.size() != 0) : (ListenerUtil.mutListener.listen(5774) ? (sites.size() == 0) : (sites.size() > 0))))))) {
                ArrayList<Integer> recentIds = AppPrefs.getRecentlyPickedSiteIds();
                int selectedSiteLocalId = mSelectedSiteRepository.getSelectedSiteLocalId();
                if (!ListenerUtil.mutListener.listen(5797)) {
                    {
                        long _loopCounter134 = 0;
                        for (SiteRecord site : sites) {
                            ListenerUtil.loopListener.listen("_loopCounter134", ++_loopCounter134);
                            int index = mAllSites.indexOfSite(site);
                            if (!ListenerUtil.mutListener.listen(5796)) {
                                if ((ListenerUtil.mutListener.listen(5783) ? (index >= -1) : (ListenerUtil.mutListener.listen(5782) ? (index <= -1) : (ListenerUtil.mutListener.listen(5781) ? (index < -1) : (ListenerUtil.mutListener.listen(5780) ? (index != -1) : (ListenerUtil.mutListener.listen(5779) ? (index == -1) : (index > -1))))))) {
                                    SiteRecord siteRecord = mAllSites.get(index);
                                    if (!ListenerUtil.mutListener.listen(5795)) {
                                        if (siteRecord.mIsHidden == makeVisible) {
                                            if (!ListenerUtil.mutListener.listen(5784)) {
                                                changeSet.add(siteRecord);
                                            }
                                            if (!ListenerUtil.mutListener.listen(5785)) {
                                                siteRecord.mIsHidden = !makeVisible;
                                            }
                                            if (!ListenerUtil.mutListener.listen(5794)) {
                                                if ((ListenerUtil.mutListener.listen(5792) ? ((ListenerUtil.mutListener.listen(5791) ? (!makeVisible || (ListenerUtil.mutListener.listen(5790) ? (siteRecord.mLocalId >= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5789) ? (siteRecord.mLocalId <= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5788) ? (siteRecord.mLocalId > selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5787) ? (siteRecord.mLocalId < selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5786) ? (siteRecord.mLocalId == selectedSiteLocalId) : (siteRecord.mLocalId != selectedSiteLocalId))))))) : (!makeVisible && (ListenerUtil.mutListener.listen(5790) ? (siteRecord.mLocalId >= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5789) ? (siteRecord.mLocalId <= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5788) ? (siteRecord.mLocalId > selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5787) ? (siteRecord.mLocalId < selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5786) ? (siteRecord.mLocalId == selectedSiteLocalId) : (siteRecord.mLocalId != selectedSiteLocalId)))))))) || recentIds.contains(siteRecord.mLocalId)) : ((ListenerUtil.mutListener.listen(5791) ? (!makeVisible || (ListenerUtil.mutListener.listen(5790) ? (siteRecord.mLocalId >= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5789) ? (siteRecord.mLocalId <= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5788) ? (siteRecord.mLocalId > selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5787) ? (siteRecord.mLocalId < selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5786) ? (siteRecord.mLocalId == selectedSiteLocalId) : (siteRecord.mLocalId != selectedSiteLocalId))))))) : (!makeVisible && (ListenerUtil.mutListener.listen(5790) ? (siteRecord.mLocalId >= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5789) ? (siteRecord.mLocalId <= selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5788) ? (siteRecord.mLocalId > selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5787) ? (siteRecord.mLocalId < selectedSiteLocalId) : (ListenerUtil.mutListener.listen(5786) ? (siteRecord.mLocalId == selectedSiteLocalId) : (siteRecord.mLocalId != selectedSiteLocalId)))))))) && recentIds.contains(siteRecord.mLocalId)))) {
                                                    if (!ListenerUtil.mutListener.listen(5793)) {
                                                        AppPrefs.removeRecentlyPickedSiteId(siteRecord.mLocalId);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5799)) {
                    if (!changeSet.isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(5798)) {
                            notifyDataSetChanged();
                        }
                    }
                }
            }
        }
        return changeSet;
    }

    void loadSites() {
        if (!ListenerUtil.mutListener.listen(5801)) {
            new LoadSitesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private SiteList filteredSitesByTextIfInSearchMode(SiteList sites) {
        if (!mIsInSearchMode) {
            return sites;
        } else {
            return filteredSitesByText(sites);
        }
    }

    private SiteList filteredSitesByText(SiteList sites) {
        SiteList filteredSiteList = new SiteList();
        if (!ListenerUtil.mutListener.listen(5810)) {
            {
                long _loopCounter135 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5809) ? (i >= sites.size()) : (ListenerUtil.mutListener.listen(5808) ? (i <= sites.size()) : (ListenerUtil.mutListener.listen(5807) ? (i > sites.size()) : (ListenerUtil.mutListener.listen(5806) ? (i != sites.size()) : (ListenerUtil.mutListener.listen(5805) ? (i == sites.size()) : (i < sites.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter135", ++_loopCounter135);
                    SiteRecord record = sites.get(i);
                    String siteNameLowerCase = record.mBlogName.toLowerCase(Locale.getDefault());
                    String hostNameLowerCase = record.mHomeURL.toLowerCase(Locale.ROOT);
                    if (!ListenerUtil.mutListener.listen(5804)) {
                        if ((ListenerUtil.mutListener.listen(5802) ? (siteNameLowerCase.contains(mLastSearch.toLowerCase(Locale.getDefault())) && hostNameLowerCase.contains(mLastSearch.toLowerCase(Locale.ROOT))) : (siteNameLowerCase.contains(mLastSearch.toLowerCase(Locale.getDefault())) || hostNameLowerCase.contains(mLastSearch.toLowerCase(Locale.ROOT))))) {
                            if (!ListenerUtil.mutListener.listen(5803)) {
                                filteredSiteList.add(record);
                            }
                        }
                    }
                }
            }
        }
        return filteredSiteList;
    }

    public List<SiteModel> getBlogsForCurrentView() {
        if ((ListenerUtil.mutListener.listen(5811) ? (mSitePickerMode.isReblogMode() && mSitePickerMode.isBloggingPromptsMode()) : (mSitePickerMode.isReblogMode() || mSitePickerMode.isBloggingPromptsMode()))) {
            // If we are reblogging we only want to select or search into the WPCom visible sites.
            return mSiteStore.getVisibleSitesAccessedViaWPCom();
        } else if (mIsInSearchMode) {
            return mSiteStore.getSites();
        }
        if (mShowHiddenSites) {
            if (mShowSelfHostedSites) {
                return mSiteStore.getSites();
            } else {
                return mSiteStore.getSitesAccessedViaWPComRest();
            }
        } else {
            if (mShowSelfHostedSites) {
                List<SiteModel> out = mSiteStore.getVisibleSitesAccessedViaWPCom();
                if (!ListenerUtil.mutListener.listen(5812)) {
                    out.addAll(mSiteStore.getSitesAccessedViaXMLRPC());
                }
                return out;
            } else {
                return mSiteStore.getVisibleSitesAccessedViaWPCom();
            }
        }
    }

    /*
     * AsyncTask which loads sites from database and populates the adapter
     */
    @SuppressLint("StaticFieldLeak")
    private class LoadSitesTask extends AsyncTask<Void, Void, SiteList[]> {

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(5813)) {
                super.onPreExecute();
            }
            boolean isEmpty = (ListenerUtil.mutListener.listen(5819) ? (mSites == null && (ListenerUtil.mutListener.listen(5818) ? (mSites.size() >= 0) : (ListenerUtil.mutListener.listen(5817) ? (mSites.size() <= 0) : (ListenerUtil.mutListener.listen(5816) ? (mSites.size() > 0) : (ListenerUtil.mutListener.listen(5815) ? (mSites.size() < 0) : (ListenerUtil.mutListener.listen(5814) ? (mSites.size() != 0) : (mSites.size() == 0))))))) : (mSites == null || (ListenerUtil.mutListener.listen(5818) ? (mSites.size() >= 0) : (ListenerUtil.mutListener.listen(5817) ? (mSites.size() <= 0) : (ListenerUtil.mutListener.listen(5816) ? (mSites.size() > 0) : (ListenerUtil.mutListener.listen(5815) ? (mSites.size() < 0) : (ListenerUtil.mutListener.listen(5814) ? (mSites.size() != 0) : (mSites.size() == 0))))))));
            if (!ListenerUtil.mutListener.listen(5820)) {
                mDataLoadedListener.onBeforeLoad(isEmpty);
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(5821)) {
                super.onCancelled();
            }
        }

        @Override
        protected SiteList[] doInBackground(Void... params) {
            List<SiteModel> siteModels = getBlogsForCurrentView();
            if (!ListenerUtil.mutListener.listen(5826)) {
                if (mIgnoreSitesIds != null) {
                    List<SiteModel> unignoredSiteModels = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(5824)) {
                        {
                            long _loopCounter136 = 0;
                            for (SiteModel site : siteModels) {
                                ListenerUtil.loopListener.listen("_loopCounter136", ++_loopCounter136);
                                if (!ListenerUtil.mutListener.listen(5823)) {
                                    if (!mIgnoreSitesIds.contains(site.getId())) {
                                        if (!ListenerUtil.mutListener.listen(5822)) {
                                            unignoredSiteModels.add(site);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5825)) {
                        siteModels = unignoredSiteModels;
                    }
                }
            }
            SiteList sites = new SiteList(siteModels);
            // sort primary blog to the top, otherwise sort by blog/host
            final long primaryBlogId = mAccountStore.getAccount().getPrimarySiteId();
            if (!ListenerUtil.mutListener.listen(5827)) {
                Collections.sort(sites, (site1, site2) -> {
                    if (primaryBlogId > 0 && !mIsInSearchMode) {
                        if (site1.mSiteId == primaryBlogId) {
                            return -1;
                        } else if (site2.mSiteId == primaryBlogId) {
                            return 1;
                        }
                    }
                    return site1.getBlogNameOrHomeURL().compareToIgnoreCase(site2.getBlogNameOrHomeURL());
                });
            }
            if (!ListenerUtil.mutListener.listen(5852)) {
                // the user isn't searching
                if ((ListenerUtil.mutListener.listen(5833) ? (!mIsInSearchMode || (ListenerUtil.mutListener.listen(5832) ? (sites.size() <= RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5831) ? (sites.size() > RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5830) ? (sites.size() < RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5829) ? (sites.size() != RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5828) ? (sites.size() == RECENTLY_PICKED_THRESHOLD) : (sites.size() >= RECENTLY_PICKED_THRESHOLD))))))) : (!mIsInSearchMode && (ListenerUtil.mutListener.listen(5832) ? (sites.size() <= RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5831) ? (sites.size() > RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5830) ? (sites.size() < RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5829) ? (sites.size() != RECENTLY_PICKED_THRESHOLD) : (ListenerUtil.mutListener.listen(5828) ? (sites.size() == RECENTLY_PICKED_THRESHOLD) : (sites.size() >= RECENTLY_PICKED_THRESHOLD))))))))) {
                    ArrayList<Integer> pickedIds = AppPrefs.getRecentlyPickedSiteIds();
                    if (!ListenerUtil.mutListener.listen(5851)) {
                        {
                            long _loopCounter137 = 0;
                            for (int i = (ListenerUtil.mutListener.listen(5850) ? (pickedIds.size() % 1) : (ListenerUtil.mutListener.listen(5849) ? (pickedIds.size() / 1) : (ListenerUtil.mutListener.listen(5848) ? (pickedIds.size() * 1) : (ListenerUtil.mutListener.listen(5847) ? (pickedIds.size() + 1) : (pickedIds.size() - 1))))); (ListenerUtil.mutListener.listen(5846) ? (i >= -1) : (ListenerUtil.mutListener.listen(5845) ? (i <= -1) : (ListenerUtil.mutListener.listen(5844) ? (i < -1) : (ListenerUtil.mutListener.listen(5843) ? (i != -1) : (ListenerUtil.mutListener.listen(5842) ? (i == -1) : (i > -1)))))); i--) {
                                ListenerUtil.loopListener.listen("_loopCounter137", ++_loopCounter137);
                                int thisId = pickedIds.get(i);
                                int indexOfSite = sites.indexOfSiteId(thisId);
                                if (!ListenerUtil.mutListener.listen(5841)) {
                                    if ((ListenerUtil.mutListener.listen(5838) ? (indexOfSite >= -1) : (ListenerUtil.mutListener.listen(5837) ? (indexOfSite <= -1) : (ListenerUtil.mutListener.listen(5836) ? (indexOfSite < -1) : (ListenerUtil.mutListener.listen(5835) ? (indexOfSite != -1) : (ListenerUtil.mutListener.listen(5834) ? (indexOfSite == -1) : (indexOfSite > -1))))))) {
                                        SiteRecord site = sites.remove(indexOfSite);
                                        if (!ListenerUtil.mutListener.listen(5839)) {
                                            site.mIsRecentPick = true;
                                        }
                                        if (!ListenerUtil.mutListener.listen(5840)) {
                                            sites.add(0, site);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5854)) {
                if ((ListenerUtil.mutListener.listen(5853) ? (mSites == null && !mSites.isSameList(sites)) : (mSites == null || !mSites.isSameList(sites)))) {
                    SiteList allSites = (SiteList) sites.clone();
                    SiteList filteredSites = filteredSitesByTextIfInSearchMode(sites);
                    return new SiteList[] { allSites, filteredSites };
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(SiteList[] updatedSiteLists) {
            if (!ListenerUtil.mutListener.listen(5858)) {
                if (updatedSiteLists != null) {
                    if (!ListenerUtil.mutListener.listen(5855)) {
                        mAllSites = updatedSiteLists[0];
                    }
                    if (!ListenerUtil.mutListener.listen(5856)) {
                        mSites = updatedSiteLists[1];
                    }
                    if (!ListenerUtil.mutListener.listen(5857)) {
                        notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5859)) {
                mDataLoadedListener.onAfterLoad();
            }
        }
    }

    /**
     * SiteRecord is a simplified version of the full account (blog) record
     */
    public static class SiteRecord {

        private final int mLocalId;

        private final long mSiteId;

        private final String mBlogName;

        private final String mHomeURL;

        private final String mBlavatarUrl;

        private final ImageType mBlavatarType;

        private boolean mIsHidden;

        private boolean mIsRecentPick;

        public SiteRecord(SiteModel siteModel) {
            mLocalId = siteModel.getId();
            mSiteId = siteModel.getSiteId();
            mBlogName = SiteUtils.getSiteNameOrHomeURL(siteModel);
            mHomeURL = SiteUtils.getHomeURLOrHostName(siteModel);
            mBlavatarUrl = SiteUtils.getSiteIconUrl(siteModel, mBlavatarSz);
            mBlavatarType = SiteUtils.getSiteImageType(siteModel.isWpForTeamsSite(), BlavatarShape.SQUARE_WITH_ROUNDED_CORNERES);
            if (!ListenerUtil.mutListener.listen(5860)) {
                mIsHidden = !siteModel.isVisible();
            }
        }

        public String getBlogNameOrHomeURL() {
            if (!ListenerUtil.mutListener.listen(5861)) {
                if (TextUtils.isEmpty(mBlogName)) {
                    return mHomeURL;
                }
            }
            return mBlogName;
        }

        public int getLocalId() {
            return mLocalId;
        }

        public boolean isHidden() {
            return mIsHidden;
        }

        public void setHidden(boolean hidden) {
            if (!ListenerUtil.mutListener.listen(5862)) {
                mIsHidden = hidden;
            }
        }

        public String getHomeURL() {
            return mHomeURL;
        }

        public String getBlavatarUrl() {
            return mBlavatarUrl;
        }

        public ImageType getBlavatarType() {
            return mBlavatarType;
        }

        public long getSiteId() {
            return mSiteId;
        }
    }

    public static class SiteList extends ArrayList<SiteRecord> {

        SiteList() {
        }

        SiteList(List<SiteModel> siteModels) {
            if (!ListenerUtil.mutListener.listen(5865)) {
                if (siteModels != null) {
                    if (!ListenerUtil.mutListener.listen(5864)) {
                        {
                            long _loopCounter138 = 0;
                            for (SiteModel siteModel : siteModels) {
                                ListenerUtil.loopListener.listen("_loopCounter138", ++_loopCounter138);
                                if (!ListenerUtil.mutListener.listen(5863)) {
                                    add(new SiteRecord(siteModel));
                                }
                            }
                        }
                    }
                }
            }
        }

        boolean isSameList(SiteList sites) {
            if (!ListenerUtil.mutListener.listen(5872)) {
                if ((ListenerUtil.mutListener.listen(5871) ? (sites == null && (ListenerUtil.mutListener.listen(5870) ? (sites.size() >= this.size()) : (ListenerUtil.mutListener.listen(5869) ? (sites.size() <= this.size()) : (ListenerUtil.mutListener.listen(5868) ? (sites.size() > this.size()) : (ListenerUtil.mutListener.listen(5867) ? (sites.size() < this.size()) : (ListenerUtil.mutListener.listen(5866) ? (sites.size() == this.size()) : (sites.size() != this.size()))))))) : (sites == null || (ListenerUtil.mutListener.listen(5870) ? (sites.size() >= this.size()) : (ListenerUtil.mutListener.listen(5869) ? (sites.size() <= this.size()) : (ListenerUtil.mutListener.listen(5868) ? (sites.size() > this.size()) : (ListenerUtil.mutListener.listen(5867) ? (sites.size() < this.size()) : (ListenerUtil.mutListener.listen(5866) ? (sites.size() == this.size()) : (sites.size() != this.size()))))))))) {
                    return false;
                }
            }
            int i;
            {
                long _loopCounter139 = 0;
                for (SiteRecord site : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter139", ++_loopCounter139);
                    i = indexOfSite(site);
                    if (!ListenerUtil.mutListener.listen(5880)) {
                        if ((ListenerUtil.mutListener.listen(5879) ? ((ListenerUtil.mutListener.listen(5878) ? ((ListenerUtil.mutListener.listen(5877) ? (i >= -1) : (ListenerUtil.mutListener.listen(5876) ? (i <= -1) : (ListenerUtil.mutListener.listen(5875) ? (i > -1) : (ListenerUtil.mutListener.listen(5874) ? (i < -1) : (ListenerUtil.mutListener.listen(5873) ? (i != -1) : (i == -1)))))) && this.get(i).mIsHidden != site.mIsHidden) : ((ListenerUtil.mutListener.listen(5877) ? (i >= -1) : (ListenerUtil.mutListener.listen(5876) ? (i <= -1) : (ListenerUtil.mutListener.listen(5875) ? (i > -1) : (ListenerUtil.mutListener.listen(5874) ? (i < -1) : (ListenerUtil.mutListener.listen(5873) ? (i != -1) : (i == -1)))))) || this.get(i).mIsHidden != site.mIsHidden)) && this.get(i).mIsRecentPick != site.mIsRecentPick) : ((ListenerUtil.mutListener.listen(5878) ? ((ListenerUtil.mutListener.listen(5877) ? (i >= -1) : (ListenerUtil.mutListener.listen(5876) ? (i <= -1) : (ListenerUtil.mutListener.listen(5875) ? (i > -1) : (ListenerUtil.mutListener.listen(5874) ? (i < -1) : (ListenerUtil.mutListener.listen(5873) ? (i != -1) : (i == -1)))))) && this.get(i).mIsHidden != site.mIsHidden) : ((ListenerUtil.mutListener.listen(5877) ? (i >= -1) : (ListenerUtil.mutListener.listen(5876) ? (i <= -1) : (ListenerUtil.mutListener.listen(5875) ? (i > -1) : (ListenerUtil.mutListener.listen(5874) ? (i < -1) : (ListenerUtil.mutListener.listen(5873) ? (i != -1) : (i == -1)))))) || this.get(i).mIsHidden != site.mIsHidden)) || this.get(i).mIsRecentPick != site.mIsRecentPick))) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        int indexOfSite(SiteRecord site) {
            if (!ListenerUtil.mutListener.listen(5899)) {
                if ((ListenerUtil.mutListener.listen(5886) ? (site != null || (ListenerUtil.mutListener.listen(5885) ? (site.mSiteId >= 0) : (ListenerUtil.mutListener.listen(5884) ? (site.mSiteId <= 0) : (ListenerUtil.mutListener.listen(5883) ? (site.mSiteId < 0) : (ListenerUtil.mutListener.listen(5882) ? (site.mSiteId != 0) : (ListenerUtil.mutListener.listen(5881) ? (site.mSiteId == 0) : (site.mSiteId > 0))))))) : (site != null && (ListenerUtil.mutListener.listen(5885) ? (site.mSiteId >= 0) : (ListenerUtil.mutListener.listen(5884) ? (site.mSiteId <= 0) : (ListenerUtil.mutListener.listen(5883) ? (site.mSiteId < 0) : (ListenerUtil.mutListener.listen(5882) ? (site.mSiteId != 0) : (ListenerUtil.mutListener.listen(5881) ? (site.mSiteId == 0) : (site.mSiteId > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(5898)) {
                        {
                            long _loopCounter140 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(5897) ? (i >= size()) : (ListenerUtil.mutListener.listen(5896) ? (i <= size()) : (ListenerUtil.mutListener.listen(5895) ? (i > size()) : (ListenerUtil.mutListener.listen(5894) ? (i != size()) : (ListenerUtil.mutListener.listen(5893) ? (i == size()) : (i < size())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter140", ++_loopCounter140);
                                if (!ListenerUtil.mutListener.listen(5892)) {
                                    if ((ListenerUtil.mutListener.listen(5891) ? (site.mSiteId >= this.get(i).mSiteId) : (ListenerUtil.mutListener.listen(5890) ? (site.mSiteId <= this.get(i).mSiteId) : (ListenerUtil.mutListener.listen(5889) ? (site.mSiteId > this.get(i).mSiteId) : (ListenerUtil.mutListener.listen(5888) ? (site.mSiteId < this.get(i).mSiteId) : (ListenerUtil.mutListener.listen(5887) ? (site.mSiteId != this.get(i).mSiteId) : (site.mSiteId == this.get(i).mSiteId))))))) {
                                        return i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        int indexOfSiteId(int localId) {
            if (!ListenerUtil.mutListener.listen(5911)) {
                {
                    long _loopCounter141 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(5910) ? (i >= size()) : (ListenerUtil.mutListener.listen(5909) ? (i <= size()) : (ListenerUtil.mutListener.listen(5908) ? (i > size()) : (ListenerUtil.mutListener.listen(5907) ? (i != size()) : (ListenerUtil.mutListener.listen(5906) ? (i == size()) : (i < size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter141", ++_loopCounter141);
                        if (!ListenerUtil.mutListener.listen(5905)) {
                            if ((ListenerUtil.mutListener.listen(5904) ? (localId >= this.get(i).mLocalId) : (ListenerUtil.mutListener.listen(5903) ? (localId <= this.get(i).mLocalId) : (ListenerUtil.mutListener.listen(5902) ? (localId > this.get(i).mLocalId) : (ListenerUtil.mutListener.listen(5901) ? (localId < this.get(i).mLocalId) : (ListenerUtil.mutListener.listen(5900) ? (localId != this.get(i).mLocalId) : (localId == this.get(i).mLocalId))))))) {
                                return i;
                            }
                        }
                    }
                }
            }
            return -1;
        }
    }
}
