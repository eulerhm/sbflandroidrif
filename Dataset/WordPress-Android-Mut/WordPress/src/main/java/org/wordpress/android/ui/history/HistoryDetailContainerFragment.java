package org.wordpress.android.ui.history;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.editor.EditorMediaUtils;
import org.wordpress.android.fluxc.model.revisions.RevisionModel;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.ui.history.HistoryListItem.Revision;
import org.wordpress.android.ui.posts.services.AztecImageLoader;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AniUtils.Duration;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.widgets.WPViewPager;
import org.wordpress.android.widgets.WPViewPagerTransformer;
import org.wordpress.android.widgets.WPViewPagerTransformer.TransformType;
import org.wordpress.aztec.AztecText;
import org.wordpress.aztec.plugins.IAztecPlugin;
import org.wordpress.aztec.plugins.shortcodes.AudioShortcodePlugin;
import org.wordpress.aztec.plugins.shortcodes.CaptionShortcodePlugin;
import org.wordpress.aztec.plugins.shortcodes.VideoShortcodePlugin;
import org.wordpress.aztec.plugins.wpcomments.HiddenGutenbergPlugin;
import org.wordpress.aztec.plugins.wpcomments.WordPressCommentsPlugin;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HistoryDetailContainerFragment extends Fragment {

    private ArrayList<Revision> mRevisions;

    private HistoryDetailFragmentAdapter mAdapter;

    private ImageView mNextButton;

    private ImageView mPreviousButton;

    private OnPageChangeListener mOnPageChangeListener;

    private Revision mRevision;

    private TextView mTotalAdditions;

    private TextView mTotalDeletions;

    private WPViewPager mViewPager;

    private AztecText mVisualContent;

    private TextView mVisualTitle;

    private ScrollView mVisualPreviewContainer;

    private int mPosition;

    private boolean mIsChevronClicked = false;

    private boolean mIsFragmentRecreated = false;

    public static final String EXTRA_CURRENT_REVISION = "EXTRA_CURRENT_REVISION";

    public static final String EXTRA_PREVIOUS_REVISIONS_IDS = "EXTRA_PREVIOUS_REVISIONS_IDS";

    public static final String EXTRA_POST_ID = "EXTRA_POST_ID";

    public static final String EXTRA_SITE_ID = "EXTRA_SITE_ID";

    public static final String KEY_REVISION = "KEY_REVISION";

    public static final String KEY_IS_IN_VISUAL_PREVIEW = "KEY_IS_IN_VISUAL_PREVIEW";

    @Inject
    ImageManager mImageManager;

    @Inject
    PostStore mPostStore;

    public static HistoryDetailContainerFragment newInstance(final Revision revision, final long[] previousRevisionsIds, final long postId, final long siteId) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(5053)) {
            args.putParcelable(EXTRA_CURRENT_REVISION, revision);
        }
        if (!ListenerUtil.mutListener.listen(5054)) {
            args.putLongArray(EXTRA_PREVIOUS_REVISIONS_IDS, previousRevisionsIds);
        }
        if (!ListenerUtil.mutListener.listen(5055)) {
            args.putLong(EXTRA_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(5056)) {
            args.putLong(EXTRA_SITE_ID, siteId);
        }
        HistoryDetailContainerFragment fragment = new HistoryDetailContainerFragment();
        if (!ListenerUtil.mutListener.listen(5057)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.history_detail_container_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(5058)) {
            mIsFragmentRecreated = savedInstanceState != null;
        }
        if (!ListenerUtil.mutListener.listen(5059)) {
            mapRevisions();
        }
        if (!ListenerUtil.mutListener.listen(5063)) {
            if (mRevisions != null) {
                if (!ListenerUtil.mutListener.listen(5062)) {
                    {
                        long _loopCounter124 = 0;
                        for (final Revision revision : mRevisions) {
                            ListenerUtil.loopListener.listen("_loopCounter124", ++_loopCounter124);
                            if (!ListenerUtil.mutListener.listen(5061)) {
                                if (revision.getRevisionId() == mRevision.getRevisionId()) {
                                    if (!ListenerUtil.mutListener.listen(5060)) {
                                        mPosition = mRevisions.indexOf(revision);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Revisions list extra is null in HistoryDetailContainerFragment");
            }
        }
        if (!ListenerUtil.mutListener.listen(5064)) {
            mViewPager = rootView.findViewById(R.id.diff_pager);
        }
        if (!ListenerUtil.mutListener.listen(5065)) {
            mViewPager.setPageTransformer(false, new WPViewPagerTransformer(TransformType.SLIDE_OVER));
        }
        if (!ListenerUtil.mutListener.listen(5066)) {
            mAdapter = new HistoryDetailFragmentAdapter(getChildFragmentManager(), mRevisions);
        }
        if (!ListenerUtil.mutListener.listen(5067)) {
            mViewPager.setAdapter(mAdapter);
        }
        if (!ListenerUtil.mutListener.listen(5068)) {
            mViewPager.setCurrentItem(mPosition);
        }
        if (!ListenerUtil.mutListener.listen(5069)) {
            mTotalAdditions = rootView.findViewById(R.id.diff_additions);
        }
        if (!ListenerUtil.mutListener.listen(5070)) {
            mTotalDeletions = rootView.findViewById(R.id.diff_deletions);
        }
        if (!ListenerUtil.mutListener.listen(5071)) {
            mNextButton = rootView.findViewById(R.id.next);
        }
        if (!ListenerUtil.mutListener.listen(5072)) {
            mNextButton.setOnClickListener(view -> {
                mIsChevronClicked = true;
                mViewPager.setCurrentItem(mPosition + 1, true);
            });
        }
        if (!ListenerUtil.mutListener.listen(5073)) {
            mPreviousButton = rootView.findViewById(R.id.previous);
        }
        if (!ListenerUtil.mutListener.listen(5074)) {
            mPreviousButton.setOnClickListener(view -> {
                mIsChevronClicked = true;
                mViewPager.setCurrentItem(mPosition - 1, true);
            });
        }
        if (!ListenerUtil.mutListener.listen(5075)) {
            mVisualTitle = rootView.findViewById(R.id.visual_title);
        }
        if (!ListenerUtil.mutListener.listen(5076)) {
            mVisualContent = rootView.findViewById(R.id.visual_content);
        }
        Drawable loadingImagePlaceholder = EditorMediaUtils.getAztecPlaceholderDrawableFromResID(requireContext(), org.wordpress.android.editor.R.drawable.ic_gridicons_image, EditorMediaUtils.getMaximumThumbnailSizeForEditor(requireContext()));
        if (!ListenerUtil.mutListener.listen(5077)) {
            mVisualContent.setImageGetter(new AztecImageLoader(requireContext(), mImageManager, loadingImagePlaceholder));
        }
        if (!ListenerUtil.mutListener.listen(5078)) {
            mVisualContent.setKeyListener(null);
        }
        if (!ListenerUtil.mutListener.listen(5079)) {
            mVisualContent.setTextIsSelectable(true);
        }
        if (!ListenerUtil.mutListener.listen(5080)) {
            mVisualContent.setCursorVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(5081)) {
            mVisualContent.setMovementMethod(LinkMovementMethod.getInstance());
        }
        ArrayList<IAztecPlugin> plugins = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(5082)) {
            plugins.add(new WordPressCommentsPlugin(mVisualContent));
        }
        if (!ListenerUtil.mutListener.listen(5083)) {
            plugins.add(new CaptionShortcodePlugin(mVisualContent));
        }
        if (!ListenerUtil.mutListener.listen(5084)) {
            plugins.add(new VideoShortcodePlugin());
        }
        if (!ListenerUtil.mutListener.listen(5085)) {
            plugins.add(new AudioShortcodePlugin());
        }
        if (!ListenerUtil.mutListener.listen(5086)) {
            plugins.add(new HiddenGutenbergPlugin(mVisualContent));
        }
        if (!ListenerUtil.mutListener.listen(5087)) {
            mVisualContent.setPlugins(plugins);
        }
        if (!ListenerUtil.mutListener.listen(5088)) {
            mVisualPreviewContainer = rootView.findViewById(R.id.visual_preview_container);
        }
        boolean isInVisualPreview = (ListenerUtil.mutListener.listen(5089) ? (savedInstanceState != null || savedInstanceState.getBoolean(KEY_IS_IN_VISUAL_PREVIEW)) : (savedInstanceState != null && savedInstanceState.getBoolean(KEY_IS_IN_VISUAL_PREVIEW)));
        if (!ListenerUtil.mutListener.listen(5090)) {
            mVisualPreviewContainer.setVisibility(isInVisualPreview ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(5091)) {
            mViewPager.setVisibility(isInVisualPreview ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5092)) {
            mPreviousButton.setVisibility(isInVisualPreview ? View.INVISIBLE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5093)) {
            mNextButton.setVisibility(isInVisualPreview ? View.INVISIBLE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5094)) {
            refreshHistoryDetail();
        }
        if (!ListenerUtil.mutListener.listen(5095)) {
            resetOnPageChangeListener();
        }
        return rootView;
    }

    private void mapRevisions() {
        if (!ListenerUtil.mutListener.listen(5100)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(5096)) {
                    mRevision = getArguments().getParcelable(EXTRA_CURRENT_REVISION);
                }
                final long[] previousRevisionsIds = getArguments().getLongArray(EXTRA_PREVIOUS_REVISIONS_IDS);
                final List<RevisionModel> revisionModels = new ArrayList<>();
                final long postId = getArguments().getLong(EXTRA_POST_ID);
                final long siteId = getArguments().getLong(EXTRA_SITE_ID);
                if (!ListenerUtil.mutListener.listen(5098)) {
                    {
                        long _loopCounter125 = 0;
                        for (final long revisionId : previousRevisionsIds) {
                            ListenerUtil.loopListener.listen("_loopCounter125", ++_loopCounter125);
                            if (!ListenerUtil.mutListener.listen(5097)) {
                                revisionModels.add(mPostStore.getRevisionById(revisionId, postId, siteId));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5099)) {
                    mRevisions = mapRevisionModelsToRevisions(revisionModels);
                }
            }
        }
    }

    @Nullable
    private ArrayList<Revision> mapRevisionModelsToRevisions(@Nullable final List<RevisionModel> revisionModels) {
        if (!ListenerUtil.mutListener.listen(5101)) {
            if (revisionModels == null) {
                return null;
            }
        }
        final ArrayList<Revision> revisions = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(5108)) {
            {
                long _loopCounter126 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5107) ? (i >= revisionModels.size()) : (ListenerUtil.mutListener.listen(5106) ? (i <= revisionModels.size()) : (ListenerUtil.mutListener.listen(5105) ? (i > revisionModels.size()) : (ListenerUtil.mutListener.listen(5104) ? (i != revisionModels.size()) : (ListenerUtil.mutListener.listen(5103) ? (i == revisionModels.size()) : (i < revisionModels.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter126", ++_loopCounter126);
                    final RevisionModel current = revisionModels.get(i);
                    if (!ListenerUtil.mutListener.listen(5102)) {
                        revisions.add(new Revision(current));
                    }
                }
            }
        }
        return revisions;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5109)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5110)) {
            showHistoryTimeStampInToolbar();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5111)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5112)) {
            ((WordPress) requireActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5113)) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5114)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(5115)) {
            outState.putBoolean(KEY_IS_IN_VISUAL_PREVIEW, isInVisualPreview());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(5116)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
        if (!ListenerUtil.mutListener.listen(5117)) {
            inflater.inflate(R.menu.history_detail, menu);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(5118)) {
            super.onPrepareOptionsMenu(menu);
        }
        MenuItem viewMode = menu.findItem(R.id.history_toggle_view);
        if (!ListenerUtil.mutListener.listen(5119)) {
            viewMode.setTitle(isInVisualPreview() ? R.string.history_preview_html : R.string.history_preview_visual);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(5131)) {
            if (item.getItemId() == R.id.history_load) {
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(5128)) {
                    intent.putExtra(KEY_REVISION, mRevision);
                }
                if (!ListenerUtil.mutListener.listen(5129)) {
                    requireActivity().setResult(Activity.RESULT_OK, intent);
                }
                if (!ListenerUtil.mutListener.listen(5130)) {
                    requireActivity().finish();
                }
            } else if (item.getItemId() == R.id.history_toggle_view) {
                if (!ListenerUtil.mutListener.listen(5126)) {
                    if (isInVisualPreview()) {
                        if (!ListenerUtil.mutListener.listen(5124)) {
                            AniUtils.fadeIn(mNextButton, Duration.SHORT);
                        }
                        if (!ListenerUtil.mutListener.listen(5125)) {
                            AniUtils.fadeIn(mPreviousButton, Duration.SHORT);
                        }
                    } else {
                        String title = TextUtils.isEmpty(mRevision.getPostTitle()) ? getString(R.string.history_no_title) : mRevision.getPostTitle();
                        if (!ListenerUtil.mutListener.listen(5120)) {
                            mVisualTitle.setText(title);
                        }
                        if (!ListenerUtil.mutListener.listen(5121)) {
                            mVisualContent.fromHtml(StringUtils.notNullStr(mRevision.getPostContent()), false);
                        }
                        if (!ListenerUtil.mutListener.listen(5122)) {
                            AniUtils.fadeOut(mNextButton, Duration.SHORT, View.INVISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(5123)) {
                            AniUtils.fadeOut(mPreviousButton, Duration.SHORT, View.INVISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5127)) {
                    crossfadePreviewViews();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void crossfadePreviewViews() {
        final View fadeInView = isInVisualPreview() ? mViewPager : mVisualPreviewContainer;
        final View fadeOutView = isInVisualPreview() ? mVisualPreviewContainer : mViewPager;
        if (!ListenerUtil.mutListener.listen(5132)) {
            mVisualPreviewContainer.smoothScrollTo(0, 0);
        }
        if (!ListenerUtil.mutListener.listen(5135)) {
            mVisualPreviewContainer.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(5133)) {
                        AniUtils.fadeIn(fadeInView, Duration.SHORT);
                    }
                    if (!ListenerUtil.mutListener.listen(5134)) {
                        AniUtils.fadeOut(fadeOutView, Duration.SHORT);
                    }
                }
            });
        }
    }

    private void showHistoryTimeStampInToolbar() {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(5137)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(5136)) {
                    actionBar.setSubtitle(mRevision.getTimeSpan());
                }
            }
        }
    }

    private void refreshHistoryDetail() {
        if (!ListenerUtil.mutListener.listen(5146)) {
            if ((ListenerUtil.mutListener.listen(5142) ? (mRevision.getTotalAdditions() >= 0) : (ListenerUtil.mutListener.listen(5141) ? (mRevision.getTotalAdditions() <= 0) : (ListenerUtil.mutListener.listen(5140) ? (mRevision.getTotalAdditions() < 0) : (ListenerUtil.mutListener.listen(5139) ? (mRevision.getTotalAdditions() != 0) : (ListenerUtil.mutListener.listen(5138) ? (mRevision.getTotalAdditions() == 0) : (mRevision.getTotalAdditions() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(5144)) {
                    mTotalAdditions.setText(String.valueOf(mRevision.getTotalAdditions()));
                }
                if (!ListenerUtil.mutListener.listen(5145)) {
                    mTotalAdditions.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5143)) {
                    mTotalAdditions.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5155)) {
            if ((ListenerUtil.mutListener.listen(5151) ? (mRevision.getTotalDeletions() >= 0) : (ListenerUtil.mutListener.listen(5150) ? (mRevision.getTotalDeletions() <= 0) : (ListenerUtil.mutListener.listen(5149) ? (mRevision.getTotalDeletions() < 0) : (ListenerUtil.mutListener.listen(5148) ? (mRevision.getTotalDeletions() != 0) : (ListenerUtil.mutListener.listen(5147) ? (mRevision.getTotalDeletions() == 0) : (mRevision.getTotalDeletions() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(5153)) {
                    mTotalDeletions.setText(String.valueOf(mRevision.getTotalDeletions()));
                }
                if (!ListenerUtil.mutListener.listen(5154)) {
                    mTotalDeletions.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5152)) {
                    mTotalDeletions.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5161)) {
            mPreviousButton.setEnabled((ListenerUtil.mutListener.listen(5160) ? (mPosition >= 0) : (ListenerUtil.mutListener.listen(5159) ? (mPosition <= 0) : (ListenerUtil.mutListener.listen(5158) ? (mPosition > 0) : (ListenerUtil.mutListener.listen(5157) ? (mPosition < 0) : (ListenerUtil.mutListener.listen(5156) ? (mPosition == 0) : (mPosition != 0)))))));
        }
        if (!ListenerUtil.mutListener.listen(5171)) {
            mNextButton.setEnabled((ListenerUtil.mutListener.listen(5170) ? (mPosition >= (ListenerUtil.mutListener.listen(5165) ? (mAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(5164) ? (mAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(5163) ? (mAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(5162) ? (mAdapter.getCount() + 1) : (mAdapter.getCount() - 1)))))) : (ListenerUtil.mutListener.listen(5169) ? (mPosition <= (ListenerUtil.mutListener.listen(5165) ? (mAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(5164) ? (mAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(5163) ? (mAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(5162) ? (mAdapter.getCount() + 1) : (mAdapter.getCount() - 1)))))) : (ListenerUtil.mutListener.listen(5168) ? (mPosition > (ListenerUtil.mutListener.listen(5165) ? (mAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(5164) ? (mAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(5163) ? (mAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(5162) ? (mAdapter.getCount() + 1) : (mAdapter.getCount() - 1)))))) : (ListenerUtil.mutListener.listen(5167) ? (mPosition < (ListenerUtil.mutListener.listen(5165) ? (mAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(5164) ? (mAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(5163) ? (mAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(5162) ? (mAdapter.getCount() + 1) : (mAdapter.getCount() - 1)))))) : (ListenerUtil.mutListener.listen(5166) ? (mPosition == (ListenerUtil.mutListener.listen(5165) ? (mAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(5164) ? (mAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(5163) ? (mAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(5162) ? (mAdapter.getCount() + 1) : (mAdapter.getCount() - 1)))))) : (mPosition != (ListenerUtil.mutListener.listen(5165) ? (mAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(5164) ? (mAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(5163) ? (mAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(5162) ? (mAdapter.getCount() + 1) : (mAdapter.getCount() - 1))))))))))));
        }
    }

    private void resetOnPageChangeListener() {
        if (!ListenerUtil.mutListener.listen(5184)) {
            if (mOnPageChangeListener != null) {
                if (!ListenerUtil.mutListener.listen(5183)) {
                    mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5182)) {
                    mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }

                        @Override
                        public void onPageSelected(int position) {
                            if (!ListenerUtil.mutListener.listen(5177)) {
                                if (mIsChevronClicked) {
                                    if (!ListenerUtil.mutListener.listen(5175)) {
                                        AnalyticsTracker.track(Stat.REVISIONS_DETAIL_VIEWED_FROM_CHEVRON);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5176)) {
                                        mIsChevronClicked = false;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(5174)) {
                                        if (!mIsFragmentRecreated) {
                                            if (!ListenerUtil.mutListener.listen(5173)) {
                                                AnalyticsTracker.track(Stat.REVISIONS_DETAIL_VIEWED_FROM_SWIPE);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(5172)) {
                                                mIsFragmentRecreated = false;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5178)) {
                                mPosition = position;
                            }
                            if (!ListenerUtil.mutListener.listen(5179)) {
                                mRevision = mAdapter.getRevisionAtPosition(mPosition);
                            }
                            if (!ListenerUtil.mutListener.listen(5180)) {
                                refreshHistoryDetail();
                            }
                            if (!ListenerUtil.mutListener.listen(5181)) {
                                showHistoryTimeStampInToolbar();
                            }
                        }
                    };
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5185)) {
            mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        }
    }

    private boolean isInVisualPreview() {
        return mVisualPreviewContainer.getVisibility() == View.VISIBLE;
    }

    private class HistoryDetailFragmentAdapter extends FragmentStatePagerAdapter {

        private final ArrayList<Revision> mRevisions;

        @SuppressWarnings("unchecked")
        HistoryDetailFragmentAdapter(FragmentManager fragmentManager, ArrayList<Revision> revisions) {
            super(fragmentManager);
            mRevisions = (ArrayList<Revision>) revisions.clone();
        }

        @Override
        public Fragment getItem(int position) {
            return HistoryDetailFragment.Companion.newInstance(mRevisions.get(position));
        }

        @Override
        public int getCount() {
            return mRevisions.size();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            try {
                if (!ListenerUtil.mutListener.listen(5187)) {
                    super.restoreState(state, loader);
                }
            } catch (IllegalStateException exception) {
                if (!ListenerUtil.mutListener.listen(5186)) {
                    AppLog.e(T.EDITOR, exception);
                }
            }
        }

        @Override
        public Parcelable saveState() {
            Bundle bundle = (Bundle) super.saveState();
            if (!ListenerUtil.mutListener.listen(5189)) {
                if (bundle == null) {
                    if (!ListenerUtil.mutListener.listen(5188)) {
                        bundle = new Bundle();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5190)) {
                bundle.putParcelableArray("states", null);
            }
            return bundle;
        }

        private Revision getRevisionAtPosition(int position) {
            if (isValidPosition(position)) {
                return mRevisions.get(position);
            } else {
                return null;
            }
        }

        private boolean isValidPosition(int position) {
            return ((ListenerUtil.mutListener.listen(5201) ? ((ListenerUtil.mutListener.listen(5195) ? (position <= 0) : (ListenerUtil.mutListener.listen(5194) ? (position > 0) : (ListenerUtil.mutListener.listen(5193) ? (position < 0) : (ListenerUtil.mutListener.listen(5192) ? (position != 0) : (ListenerUtil.mutListener.listen(5191) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(5200) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(5199) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(5198) ? (position > getCount()) : (ListenerUtil.mutListener.listen(5197) ? (position != getCount()) : (ListenerUtil.mutListener.listen(5196) ? (position == getCount()) : (position < getCount()))))))) : ((ListenerUtil.mutListener.listen(5195) ? (position <= 0) : (ListenerUtil.mutListener.listen(5194) ? (position > 0) : (ListenerUtil.mutListener.listen(5193) ? (position < 0) : (ListenerUtil.mutListener.listen(5192) ? (position != 0) : (ListenerUtil.mutListener.listen(5191) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(5200) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(5199) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(5198) ? (position > getCount()) : (ListenerUtil.mutListener.listen(5197) ? (position != getCount()) : (ListenerUtil.mutListener.listen(5196) ? (position == getCount()) : (position < getCount())))))))));
        }
    }
}
