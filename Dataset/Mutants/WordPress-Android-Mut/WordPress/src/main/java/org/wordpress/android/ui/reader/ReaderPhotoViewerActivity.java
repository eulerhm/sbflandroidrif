package org.wordpress.android.ui.reader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import org.wordpress.android.R;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.reader.models.ReaderImageList;
import org.wordpress.android.ui.reader.utils.ReaderImageScanner;
import org.wordpress.android.ui.reader.views.ReaderPhotoView.PhotoViewListener;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.widgets.WPViewPager;
import org.wordpress.android.widgets.WPViewPagerTransformer;
import org.wordpress.android.widgets.WPViewPagerTransformer.TransformType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Full-screen photo viewer - uses a ViewPager to enable scrolling between images in a blog
 * post, but also supports viewing a single image
 */
public class ReaderPhotoViewerActivity extends LocaleAwareActivity implements PhotoViewListener {

    private String mInitialImageUrl;

    private boolean mIsPrivate;

    private boolean mIsGallery;

    private String mContent;

    private WPViewPager mViewPager;

    private PhotoPagerAdapter mAdapter;

    private TextView mTxtTitle;

    private boolean mIsTitleVisible;

    private Toolbar mToolbar;

    private static final long FADE_DELAY_MS = 3000;

    private final Handler mFadeHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(20833)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(20834)) {
            setContentView(R.layout.reader_activity_photo_viewer);
        }
        if (!ListenerUtil.mutListener.listen(20835)) {
            mViewPager = (WPViewPager) findViewById(R.id.viewpager);
        }
        if (!ListenerUtil.mutListener.listen(20836)) {
            mTxtTitle = (TextView) findViewById(R.id.text_title);
        }
        if (!ListenerUtil.mutListener.listen(20837)) {
            // title is hidden until we know we can show it
            mTxtTitle.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(20846)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(20842)) {
                    mInitialImageUrl = savedInstanceState.getString(ReaderConstants.ARG_IMAGE_URL);
                }
                if (!ListenerUtil.mutListener.listen(20843)) {
                    mIsPrivate = savedInstanceState.getBoolean(ReaderConstants.ARG_IS_PRIVATE);
                }
                if (!ListenerUtil.mutListener.listen(20844)) {
                    mIsGallery = savedInstanceState.getBoolean(ReaderConstants.ARG_IS_GALLERY);
                }
                if (!ListenerUtil.mutListener.listen(20845)) {
                    mContent = savedInstanceState.getString(ReaderConstants.ARG_CONTENT);
                }
            } else if (getIntent() != null) {
                if (!ListenerUtil.mutListener.listen(20838)) {
                    mInitialImageUrl = getIntent().getStringExtra(ReaderConstants.ARG_IMAGE_URL);
                }
                if (!ListenerUtil.mutListener.listen(20839)) {
                    mIsPrivate = getIntent().getBooleanExtra(ReaderConstants.ARG_IS_PRIVATE, false);
                }
                if (!ListenerUtil.mutListener.listen(20840)) {
                    mIsGallery = getIntent().getBooleanExtra(ReaderConstants.ARG_IS_GALLERY, false);
                }
                if (!ListenerUtil.mutListener.listen(20841)) {
                    mContent = getIntent().getStringExtra(ReaderConstants.ARG_CONTENT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20847)) {
            mToolbar = findViewById(R.id.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(20848)) {
            setSupportActionBar(mToolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(20851)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(20849)) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(20850)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20852)) {
            mViewPager.setPageTransformer(false, new WPViewPagerTransformer(TransformType.FLOW));
        }
        if (!ListenerUtil.mutListener.listen(20855)) {
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(20853)) {
                        updateTitle(position);
                    }
                    if (!ListenerUtil.mutListener.listen(20854)) {
                        showToolbar();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20856)) {
            mViewPager.setAdapter(getAdapter());
        }
        if (!ListenerUtil.mutListener.listen(20857)) {
            loadImageList();
        }
        if (!ListenerUtil.mutListener.listen(20858)) {
            showToolbar();
        }
    }

    private void loadImageList() {
        // so parse images from it
        final ReaderImageList imageList;
        if (TextUtils.isEmpty(mContent)) {
            imageList = new ReaderImageList(mIsPrivate);
        } else {
            int minImageWidth = mIsGallery ? ReaderConstants.MIN_GALLERY_IMAGE_WIDTH : 0;
            imageList = new ReaderImageScanner(mContent, mIsPrivate).getImageList(0, minImageWidth);
        }
        if (!ListenerUtil.mutListener.listen(20861)) {
            // make sure initial image is in the list
            if ((ListenerUtil.mutListener.listen(20859) ? (!TextUtils.isEmpty(mInitialImageUrl) || !imageList.hasImageUrl(mInitialImageUrl)) : (!TextUtils.isEmpty(mInitialImageUrl) && !imageList.hasImageUrl(mInitialImageUrl)))) {
                if (!ListenerUtil.mutListener.listen(20860)) {
                    imageList.addImageUrl(0, mInitialImageUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20862)) {
            getAdapter().setImageList(imageList, mInitialImageUrl);
        }
    }

    private void showToolbar() {
        if (!ListenerUtil.mutListener.listen(20868)) {
            if (!isFinishing()) {
                if (!ListenerUtil.mutListener.listen(20863)) {
                    mFadeHandler.removeCallbacks(mFadeOutRunnable);
                }
                if (!ListenerUtil.mutListener.listen(20864)) {
                    mFadeHandler.postDelayed(mFadeOutRunnable, FADE_DELAY_MS);
                }
                if (!ListenerUtil.mutListener.listen(20867)) {
                    if (mToolbar.getVisibility() != View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(20866)) {
                            AniUtils.startAnimation(mToolbar, R.anim.toolbar_fade_in_and_down, new Animation.AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {
                                    if (!ListenerUtil.mutListener.listen(20865)) {
                                        mToolbar.setVisibility(View.VISIBLE);
                                    }
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private final Runnable mFadeOutRunnable = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(20872)) {
                if ((ListenerUtil.mutListener.listen(20869) ? (!isFinishing() || mToolbar.getVisibility() == View.VISIBLE) : (!isFinishing() && mToolbar.getVisibility() == View.VISIBLE))) {
                    if (!ListenerUtil.mutListener.listen(20871)) {
                        AniUtils.startAnimation(mToolbar, R.anim.toolbar_fade_out_and_up, new Animation.AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                if (!ListenerUtil.mutListener.listen(20870)) {
                                    mToolbar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                }
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(20874)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(20873)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(20875)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(20876)) {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    private PhotoPagerAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(20878)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(20877)) {
                    mAdapter = new PhotoPagerAdapter(getSupportFragmentManager());
                }
            }
        }
        return mAdapter;
    }

    private boolean hasAdapter() {
        return (mAdapter != null);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(20880)) {
            if (hasAdapter()) {
                String imageUrl = getAdapter().getImageUrl(mViewPager.getCurrentItem());
                if (!ListenerUtil.mutListener.listen(20879)) {
                    outState.putString(ReaderConstants.ARG_IMAGE_URL, imageUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20881)) {
            outState.putBoolean(ReaderConstants.ARG_IS_PRIVATE, mIsPrivate);
        }
        if (!ListenerUtil.mutListener.listen(20882)) {
            outState.putBoolean(ReaderConstants.ARG_IS_GALLERY, mIsGallery);
        }
        if (!ListenerUtil.mutListener.listen(20883)) {
            outState.putString(ReaderConstants.ARG_CONTENT, mContent);
        }
        if (!ListenerUtil.mutListener.listen(20884)) {
            super.onSaveInstanceState(outState);
        }
    }

    private int getImageCount() {
        if (hasAdapter()) {
            return getAdapter().getCount();
        } else {
            return 0;
        }
    }

    private void updateTitle(int position) {
        if (!ListenerUtil.mutListener.listen(20886)) {
            if ((ListenerUtil.mutListener.listen(20885) ? (isFinishing() && !canShowTitle()) : (isFinishing() || !canShowTitle()))) {
                return;
            }
        }
        String titlePhotoViewer = getString(R.string.reader_title_photo_viewer);
        String title = String.format(titlePhotoViewer, (ListenerUtil.mutListener.listen(20890) ? (position % 1) : (ListenerUtil.mutListener.listen(20889) ? (position / 1) : (ListenerUtil.mutListener.listen(20888) ? (position * 1) : (ListenerUtil.mutListener.listen(20887) ? (position - 1) : (position + 1))))), getImageCount());
        if (!ListenerUtil.mutListener.listen(20891)) {
            if (title.equals(mTxtTitle.getText())) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20892)) {
            mTxtTitle.setText(title);
        }
    }

    /*
     * title (image count) is only shown if there are multiple images
     */
    private boolean canShowTitle() {
        return ((ListenerUtil.mutListener.listen(20897) ? (getImageCount() >= 1) : (ListenerUtil.mutListener.listen(20896) ? (getImageCount() <= 1) : (ListenerUtil.mutListener.listen(20895) ? (getImageCount() < 1) : (ListenerUtil.mutListener.listen(20894) ? (getImageCount() != 1) : (ListenerUtil.mutListener.listen(20893) ? (getImageCount() == 1) : (getImageCount() > 1)))))));
    }

    private void toggleTitle() {
        if (!ListenerUtil.mutListener.listen(20899)) {
            if ((ListenerUtil.mutListener.listen(20898) ? (isFinishing() && !canShowTitle()) : (isFinishing() || !canShowTitle()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20900)) {
            mTxtTitle.clearAnimation();
        }
        if (!ListenerUtil.mutListener.listen(20903)) {
            if (mIsTitleVisible) {
                if (!ListenerUtil.mutListener.listen(20902)) {
                    AniUtils.fadeOut(mTxtTitle, AniUtils.Duration.SHORT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20901)) {
                    AniUtils.fadeIn(mTxtTitle, AniUtils.Duration.SHORT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20904)) {
            mIsTitleVisible = !mIsTitleVisible;
        }
    }

    @Override
    public void onTapPhotoView() {
        if (!ListenerUtil.mutListener.listen(20905)) {
            toggleTitle();
        }
        if (!ListenerUtil.mutListener.listen(20906)) {
            showToolbar();
        }
    }

    private class PhotoPagerAdapter extends FragmentStatePagerAdapter {

        private ReaderImageList mImageList;

        PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void setImageList(ReaderImageList imageList, String initialImageUrl) {
            if (!ListenerUtil.mutListener.listen(20907)) {
                mImageList = (ReaderImageList) imageList.clone();
            }
            if (!ListenerUtil.mutListener.listen(20908)) {
                notifyDataSetChanged();
            }
            int position = indexOfImageUrl(initialImageUrl);
            if (!ListenerUtil.mutListener.listen(20915)) {
                if (isValidPosition(position)) {
                    if (!ListenerUtil.mutListener.listen(20909)) {
                        mViewPager.setCurrentItem(position);
                    }
                    if (!ListenerUtil.mutListener.listen(20914)) {
                        if (canShowTitle()) {
                            if (!ListenerUtil.mutListener.listen(20911)) {
                                mTxtTitle.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(20912)) {
                                mIsTitleVisible = true;
                            }
                            if (!ListenerUtil.mutListener.listen(20913)) {
                                updateTitle(position);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(20910)) {
                                mIsTitleVisible = false;
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            // https://code.google.com/p/android/issues/detail?id=42601
            try {
                if (!ListenerUtil.mutListener.listen(20917)) {
                    super.restoreState(state, loader);
                }
            } catch (IllegalStateException e) {
                if (!ListenerUtil.mutListener.listen(20916)) {
                    AppLog.e(AppLog.T.READER, e);
                }
            }
        }

        @Override
        public Fragment getItem(int position) {
            return ReaderPhotoViewerFragment.newInstance(mImageList.get(position), mImageList.isPrivate());
        }

        @Override
        public int getCount() {
            return (mImageList != null ? mImageList.size() : 0);
        }

        private int indexOfImageUrl(String imageUrl) {
            if (!ListenerUtil.mutListener.listen(20918)) {
                if (mImageList == null) {
                    return -1;
                }
            }
            return mImageList.indexOfImageUrl(imageUrl);
        }

        private boolean isValidPosition(int position) {
            return ((ListenerUtil.mutListener.listen(20930) ? ((ListenerUtil.mutListener.listen(20924) ? (mImageList != null || (ListenerUtil.mutListener.listen(20923) ? (position <= 0) : (ListenerUtil.mutListener.listen(20922) ? (position > 0) : (ListenerUtil.mutListener.listen(20921) ? (position < 0) : (ListenerUtil.mutListener.listen(20920) ? (position != 0) : (ListenerUtil.mutListener.listen(20919) ? (position == 0) : (position >= 0))))))) : (mImageList != null && (ListenerUtil.mutListener.listen(20923) ? (position <= 0) : (ListenerUtil.mutListener.listen(20922) ? (position > 0) : (ListenerUtil.mutListener.listen(20921) ? (position < 0) : (ListenerUtil.mutListener.listen(20920) ? (position != 0) : (ListenerUtil.mutListener.listen(20919) ? (position == 0) : (position >= 0)))))))) || (ListenerUtil.mutListener.listen(20929) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(20928) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(20927) ? (position > getCount()) : (ListenerUtil.mutListener.listen(20926) ? (position != getCount()) : (ListenerUtil.mutListener.listen(20925) ? (position == getCount()) : (position < getCount()))))))) : ((ListenerUtil.mutListener.listen(20924) ? (mImageList != null || (ListenerUtil.mutListener.listen(20923) ? (position <= 0) : (ListenerUtil.mutListener.listen(20922) ? (position > 0) : (ListenerUtil.mutListener.listen(20921) ? (position < 0) : (ListenerUtil.mutListener.listen(20920) ? (position != 0) : (ListenerUtil.mutListener.listen(20919) ? (position == 0) : (position >= 0))))))) : (mImageList != null && (ListenerUtil.mutListener.listen(20923) ? (position <= 0) : (ListenerUtil.mutListener.listen(20922) ? (position > 0) : (ListenerUtil.mutListener.listen(20921) ? (position < 0) : (ListenerUtil.mutListener.listen(20920) ? (position != 0) : (ListenerUtil.mutListener.listen(20919) ? (position == 0) : (position >= 0)))))))) && (ListenerUtil.mutListener.listen(20929) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(20928) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(20927) ? (position > getCount()) : (ListenerUtil.mutListener.listen(20926) ? (position != getCount()) : (ListenerUtil.mutListener.listen(20925) ? (position == getCount()) : (position < getCount())))))))));
        }

        private String getImageUrl(int position) {
            if (isValidPosition(position)) {
                return mImageList.get(position);
            } else {
                return null;
            }
        }
    }
}
