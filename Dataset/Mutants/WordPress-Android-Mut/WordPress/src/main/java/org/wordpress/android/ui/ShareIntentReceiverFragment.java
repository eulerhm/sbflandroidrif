package org.wordpress.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.main.SitePickerAdapter;
import org.wordpress.android.ui.main.SitePickerAdapter.SiteList;
import org.wordpress.android.ui.main.SitePickerAdapter.ViewHolderHandler;
import org.wordpress.android.ui.media.MediaBrowserActivity;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.util.image.ImageManager;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ShareIntentReceiverFragment extends Fragment {

    public static final String TAG = "share_intent_fragment_tag";

    private static final String ARG_SHARING_MEDIA = "ARG_SHARING_MEDIA";

    private static final String ARG_LAST_USED_BLOG_LOCAL_ID = "ARG_LAST_USED_BLOG_LOCAL_ID";

    @Inject
    AccountStore mAccountStore;

    @Inject
    ImageManager mImageManager;

    private ShareIntentFragmentListener mShareIntentFragmentListener;

    private SitePickerAdapter mAdapter;

    private Button mSharePostBtn;

    private Button mShareMediaBtn;

    private boolean mSharingMediaFile;

    private int mLastUsedBlogLocalId;

    private RecyclerView mRecyclerView;

    private View mBottomButtonsContainer;

    private View mBottomButtonsShadow;

    public static ShareIntentReceiverFragment newInstance(boolean sharingMediaFile, int lastUsedBlogLocalId) {
        ShareIntentReceiverFragment fragment = new ShareIntentReceiverFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(26569)) {
            args.putBoolean(ARG_SHARING_MEDIA, sharingMediaFile);
        }
        if (!ListenerUtil.mutListener.listen(26570)) {
            args.putInt(ARG_LAST_USED_BLOG_LOCAL_ID, lastUsedBlogLocalId);
        }
        if (!ListenerUtil.mutListener.listen(26571)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(26572)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(26574)) {
            if (context instanceof ShareIntentFragmentListener) {
                if (!ListenerUtil.mutListener.listen(26573)) {
                    mShareIntentFragmentListener = (ShareIntentFragmentListener) context;
                }
            } else {
                throw new RuntimeException("The parent activity doesn't implement ShareIntentFragmentListener.");
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26575)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26576)) {
            // important for accessibility - talkback
            getActivity().setTitle(R.string.share_intent_screen_title);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.share_intent_receiver_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(26577)) {
            initButtonsContainer(layout);
        }
        if (!ListenerUtil.mutListener.listen(26578)) {
            initShareActionPostButton(layout);
        }
        if (!ListenerUtil.mutListener.listen(26579)) {
            initShareActionMediaButton(layout, mSharingMediaFile);
        }
        if (!ListenerUtil.mutListener.listen(26580)) {
            initRecyclerView(layout);
        }
        return layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26581)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26582)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(26583)) {
            mSharingMediaFile = getArguments().getBoolean(ARG_SHARING_MEDIA);
        }
        if (!ListenerUtil.mutListener.listen(26584)) {
            mLastUsedBlogLocalId = getArguments().getInt(ARG_LAST_USED_BLOG_LOCAL_ID);
        }
        if (!ListenerUtil.mutListener.listen(26585)) {
            loadSavedState(savedInstanceState);
        }
    }

    private void loadSavedState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26587)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(26586)) {
                    mLastUsedBlogLocalId = savedInstanceState.getInt(ARG_LAST_USED_BLOG_LOCAL_ID);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(26588)) {
            super.onSaveInstanceState(outState);
        }
        int selectedItemLocalId = mAdapter.getSelectedItemLocalId();
        if (!ListenerUtil.mutListener.listen(26595)) {
            if ((ListenerUtil.mutListener.listen(26593) ? (selectedItemLocalId >= -1) : (ListenerUtil.mutListener.listen(26592) ? (selectedItemLocalId <= -1) : (ListenerUtil.mutListener.listen(26591) ? (selectedItemLocalId > -1) : (ListenerUtil.mutListener.listen(26590) ? (selectedItemLocalId < -1) : (ListenerUtil.mutListener.listen(26589) ? (selectedItemLocalId == -1) : (selectedItemLocalId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(26594)) {
                    outState.putInt(ARG_LAST_USED_BLOG_LOCAL_ID, mAdapter.getSelectedItemLocalId());
                }
            }
        }
    }

    private void initButtonsContainer(ViewGroup layout) {
        if (!ListenerUtil.mutListener.listen(26596)) {
            mBottomButtonsContainer = layout.findViewById(R.id.bottom_buttons);
        }
        if (!ListenerUtil.mutListener.listen(26597)) {
            mBottomButtonsShadow = layout.findViewById(R.id.bottom_shadow);
        }
    }

    private void initShareActionPostButton(final ViewGroup layout) {
        if (!ListenerUtil.mutListener.listen(26598)) {
            mSharePostBtn = layout.findViewById(R.id.primary_button);
        }
        if (!ListenerUtil.mutListener.listen(26599)) {
            addShareActionListener(mSharePostBtn, ShareAction.SHARE_TO_POST);
        }
    }

    private void initShareActionMediaButton(final ViewGroup layout, boolean sharingMediaFile) {
        if (!ListenerUtil.mutListener.listen(26600)) {
            mShareMediaBtn = layout.findViewById(R.id.secondary_button);
        }
        if (!ListenerUtil.mutListener.listen(26601)) {
            addShareActionListener(mShareMediaBtn, ShareAction.SHARE_TO_MEDIA_LIBRARY);
        }
        if (!ListenerUtil.mutListener.listen(26602)) {
            mShareMediaBtn.setVisibility(sharingMediaFile ? View.VISIBLE : View.GONE);
        }
    }

    private void addShareActionListener(final Button button, final ShareAction shareAction) {
        if (!ListenerUtil.mutListener.listen(26604)) {
            button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(26603)) {
                        mShareIntentFragmentListener.share(shareAction, mAdapter.getSelectedItemLocalId());
                    }
                }
            });
        }
    }

    private void initRecyclerView(ViewGroup layout) {
        if (!ListenerUtil.mutListener.listen(26605)) {
            mRecyclerView = layout.findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(26606)) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        if (!ListenerUtil.mutListener.listen(26607)) {
            mRecyclerView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(26608)) {
            mRecyclerView.setItemAnimator(null);
        }
        if (!ListenerUtil.mutListener.listen(26609)) {
            mRecyclerView.setAdapter(createSiteAdapter());
        }
    }

    private Adapter createSiteAdapter() {
        if (!ListenerUtil.mutListener.listen(26621)) {
            mAdapter = new SitePickerAdapter(getActivity(), R.layout.share_intent_sites_listitem, 0, "", false, new SitePickerAdapter.OnDataLoadedListener() {

                @Override
                public void onBeforeLoad(boolean isEmpty) {
                }

                @Override
                public void onAfterLoad() {
                    if (!ListenerUtil.mutListener.listen(26619)) {
                        mRecyclerView.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(26610)) {
                                    if (!isAdded()) {
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26618)) {
                                    if ((ListenerUtil.mutListener.listen(26615) ? (mRecyclerView.computeVerticalScrollRange() >= mRecyclerView.getHeight()) : (ListenerUtil.mutListener.listen(26614) ? (mRecyclerView.computeVerticalScrollRange() <= mRecyclerView.getHeight()) : (ListenerUtil.mutListener.listen(26613) ? (mRecyclerView.computeVerticalScrollRange() < mRecyclerView.getHeight()) : (ListenerUtil.mutListener.listen(26612) ? (mRecyclerView.computeVerticalScrollRange() != mRecyclerView.getHeight()) : (ListenerUtil.mutListener.listen(26611) ? (mRecyclerView.computeVerticalScrollRange() == mRecyclerView.getHeight()) : (mRecyclerView.computeVerticalScrollRange() > mRecyclerView.getHeight()))))))) {
                                        if (!ListenerUtil.mutListener.listen(26617)) {
                                            mBottomButtonsShadow.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(26616)) {
                                            mBottomButtonsShadow.setVisibility(View.GONE);
                                        }
                                    }
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(26620)) {
                        mAdapter.findAndSelect(mLastUsedBlogLocalId);
                    }
                }
            }, createHeaderHandler(), null);
        }
        if (!ListenerUtil.mutListener.listen(26622)) {
            mAdapter.setSingleItemSelectionEnabled(true);
        }
        return mAdapter;
    }

    private ViewHolderHandler<HeaderViewHolder> createHeaderHandler() {
        return new ViewHolderHandler<HeaderViewHolder>() {

            @Override
            public HeaderViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, boolean attachToRoot) {
                return new HeaderViewHolder(layoutInflater.inflate(R.layout.share_intent_receiver_header, parent, false));
            }

            @Override
            public void onBindViewHolder(HeaderViewHolder holder, SiteList sites) {
                if (!ListenerUtil.mutListener.listen(26623)) {
                    if (!isAdded()) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(26624)) {
                    holder.bindText(getString(sites.size() == 1 ? R.string.share_intent_adding_to : R.string.share_intent_pick_site));
                }
            }
        };
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView mHeaderTextView;

        HeaderViewHolder(View view) {
            super(view);
            mHeaderTextView = view.findViewById(R.id.login_epilogue_header_sites_subheader);
        }

        void bindText(String text) {
            if (!ListenerUtil.mutListener.listen(26625)) {
                mHeaderTextView.setText(text);
            }
        }
    }

    enum ShareAction {

        SHARE_TO_POST("new_post", EditPostActivity.class), SHARE_TO_MEDIA_LIBRARY("media_library", MediaBrowserActivity.class);

        public final Class targetClass;

        public final String analyticsName;

        ShareAction(String analyticsName, Class targetClass) {
            this.targetClass = targetClass;
            this.analyticsName = analyticsName;
        }
    }

    interface ShareIntentFragmentListener {

        void share(ShareAction shareAction, int selectedSiteLocalId);
    }
}
