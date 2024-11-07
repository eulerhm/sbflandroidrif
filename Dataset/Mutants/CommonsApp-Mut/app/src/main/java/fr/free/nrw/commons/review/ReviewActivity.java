package fr.free.nrw.commons.review;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.viewpagerindicator.CirclePageIndicator;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.AccountUtil;
import fr.free.nrw.commons.delete.DeleteHelper;
import fr.free.nrw.commons.media.MediaDetailFragment;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReviewActivity extends BaseActivity {

    @BindView(R.id.pager_indicator_review)
    public CirclePageIndicator pagerIndicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.view_pager_review)
    ReviewViewPager reviewPager;

    @BindView(R.id.skip_image)
    Button btnSkipImage;

    @BindView(R.id.review_image_view)
    SimpleDraweeView simpleDraweeView;

    @BindView(R.id.pb_review_image)
    ProgressBar progressBar;

    @BindView(R.id.tv_image_caption)
    TextView imageCaption;

    @BindView(R.id.mediaDetailContainer)
    FrameLayout mediaDetailContainer;

    MediaDetailFragment mediaDetailFragment;

    @BindView(R.id.reviewActivityContainer)
    LinearLayout reviewContainer;

    public ReviewPagerAdapter reviewPagerAdapter;

    public ReviewController reviewController;

    @Inject
    ReviewHelper reviewHelper;

    @Inject
    DeleteHelper deleteHelper;

    /**
     * Represent fragment for ReviewImage
     * Use to call some methods of ReviewImage fragment
     */
    private ReviewImageFragment reviewImageFragment;

    /**
     * Flag to check whether there are any non-hidden categories in the File
     */
    private boolean hasNonHiddenCategories = false;

    final String SAVED_MEDIA = "saved_media";

    private Media media;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5726)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(5728)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(5727)) {
                    outState.putParcelable(SAVED_MEDIA, media);
                }
            }
        }
    }

    /**
     * Consumers should be simply using this method to use this activity.
     *
     * @param context
     * @param title   Page title
     */
    public static void startYourself(Context context, String title) {
        Intent reviewActivity = new Intent(context, ReviewActivity.class);
        if (!ListenerUtil.mutListener.listen(5729)) {
            reviewActivity.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        if (!ListenerUtil.mutListener.listen(5730)) {
            reviewActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(5731)) {
            context.startActivity(reviewActivity);
        }
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public Media getMedia() {
        return media;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5732)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5733)) {
            setContentView(R.layout.activity_review);
        }
        if (!ListenerUtil.mutListener.listen(5734)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(5735)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(5736)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(5737)) {
            reviewController = new ReviewController(deleteHelper, this);
        }
        if (!ListenerUtil.mutListener.listen(5738)) {
            reviewPagerAdapter = new ReviewPagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(5739)) {
            reviewPager.setAdapter(reviewPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(5740)) {
            pagerIndicator.setViewPager(reviewPager);
        }
        if (!ListenerUtil.mutListener.listen(5741)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        Drawable[] d = btnSkipImage.getCompoundDrawablesRelative();
        if (!ListenerUtil.mutListener.listen(5742)) {
            d[2].setColorFilter(getApplicationContext().getResources().getColor(R.color.button_blue), PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(5747)) {
            if ((ListenerUtil.mutListener.listen(5743) ? (savedInstanceState != null || savedInstanceState.getParcelable(SAVED_MEDIA) != null) : (savedInstanceState != null && savedInstanceState.getParcelable(SAVED_MEDIA) != null))) {
                if (!ListenerUtil.mutListener.listen(5745)) {
                    // Use existing media if we have one
                    updateImage(savedInstanceState.getParcelable(SAVED_MEDIA));
                }
                if (!ListenerUtil.mutListener.listen(5746)) {
                    setUpMediaDetailOnOrientation();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5744)) {
                    // Run randomizer whenever everything is ready so that a first random image will be added
                    runRandomizer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5748)) {
            btnSkipImage.setOnClickListener(view -> {
                reviewImageFragment = getInstanceOfReviewImageFragment();
                reviewImageFragment.disableButtons();
                runRandomizer();
            });
        }
        if (!ListenerUtil.mutListener.listen(5749)) {
            simpleDraweeView.setOnClickListener(view -> setUpMediaDetailFragment());
        }
        if (!ListenerUtil.mutListener.listen(5750)) {
            btnSkipImage.setOnTouchListener((view, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP && event.getRawX() >= (btnSkipImage.getRight() - btnSkipImage.getCompoundDrawables()[2].getBounds().width())) {
                    showSkipImageInfo();
                    return true;
                }
                return false;
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(5751)) {
            onBackPressed();
        }
        return true;
    }

    @SuppressLint("CheckResult")
    public boolean runRandomizer() {
        if (!ListenerUtil.mutListener.listen(5752)) {
            hasNonHiddenCategories = false;
        }
        if (!ListenerUtil.mutListener.listen(5753)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5754)) {
            reviewPager.setCurrentItem(0);
        }
        if (!ListenerUtil.mutListener.listen(5755)) {
            // Finds non-hidden categories from Media instance
            compositeDisposable.add(reviewHelper.getRandomMedia().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::checkWhetherFileIsUsedInWikis));
        }
        return true;
    }

    /**
     * Check whether media is used or not in any Wiki Page
     */
    @SuppressLint("CheckResult")
    private void checkWhetherFileIsUsedInWikis(final Media media) {
        if (!ListenerUtil.mutListener.listen(5756)) {
            compositeDisposable.add(reviewHelper.checkFileUsage(media.getFilename()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> {
                // result false indicates media is not used in any wiki
                if (!result) {
                    // Finds non-hidden categories from Media instance
                    findNonHiddenCategories(media);
                } else {
                    runRandomizer();
                }
            }));
        }
    }

    /**
     * Finds non-hidden categories and updates current image
     */
    private void findNonHiddenCategories(Media media) {
        if (!ListenerUtil.mutListener.listen(5759)) {
            {
                long _loopCounter82 = 0;
                for (String key : media.getCategoriesHiddenStatus().keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter82", ++_loopCounter82);
                    Boolean value = media.getCategoriesHiddenStatus().get(key);
                    if (!ListenerUtil.mutListener.listen(5758)) {
                        // so that category review cannot be skipped
                        if (!value) {
                            if (!ListenerUtil.mutListener.listen(5757)) {
                                hasNonHiddenCategories = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5760)) {
            reviewImageFragment = getInstanceOfReviewImageFragment();
        }
        if (!ListenerUtil.mutListener.listen(5761)) {
            reviewImageFragment.disableButtons();
        }
        if (!ListenerUtil.mutListener.listen(5762)) {
            updateImage(media);
        }
    }

    @SuppressLint("CheckResult")
    private void updateImage(Media media) {
        if (!ListenerUtil.mutListener.listen(5763)) {
            reviewHelper.addViewedImagesToDB(media.getPageId());
        }
        if (!ListenerUtil.mutListener.listen(5764)) {
            this.media = media;
        }
        String fileName = media.getFilename();
        if (!ListenerUtil.mutListener.listen(5771)) {
            if ((ListenerUtil.mutListener.listen(5769) ? (fileName.length() >= 0) : (ListenerUtil.mutListener.listen(5768) ? (fileName.length() <= 0) : (ListenerUtil.mutListener.listen(5767) ? (fileName.length() > 0) : (ListenerUtil.mutListener.listen(5766) ? (fileName.length() < 0) : (ListenerUtil.mutListener.listen(5765) ? (fileName.length() != 0) : (fileName.length() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(5770)) {
                    ViewUtil.showShortSnackbar(drawerLayout, R.string.error_review);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5774)) {
            // If The Media User and Current Session Username is same then Skip the Image
            if ((ListenerUtil.mutListener.listen(5772) ? (media.getUser() != null || media.getUser().equals(AccountUtil.getUserName(getApplicationContext()))) : (media.getUser() != null && media.getUser().equals(AccountUtil.getUserName(getApplicationContext()))))) {
                if (!ListenerUtil.mutListener.listen(5773)) {
                    runRandomizer();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5775)) {
            simpleDraweeView.setImageURI(media.getImageUrl());
        }
        if (!ListenerUtil.mutListener.listen(5776)) {
            // file name is updated
            reviewController.onImageRefreshed(media);
        }
        if (!ListenerUtil.mutListener.listen(5777)) {
            compositeDisposable.add(reviewHelper.getFirstRevisionOfFile(fileName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(revision -> {
                reviewController.firstRevision = revision;
                reviewPagerAdapter.updateFileInformation();
                @SuppressLint({ "StringFormatInvalid", "LocalSuppress" })
                String caption = String.format(getString(R.string.review_is_uploaded_by), fileName, revision.getUser());
                imageCaption.setText(caption);
                progressBar.setVisibility(View.GONE);
                reviewImageFragment = getInstanceOfReviewImageFragment();
                reviewImageFragment.enableButtons();
            }));
        }
        if (!ListenerUtil.mutListener.listen(5778)) {
            reviewPager.setCurrentItem(0);
        }
    }

    public void swipeToNext() {
        int nextPos = reviewPager.getCurrentItem() + 1;
        if (!ListenerUtil.mutListener.listen(5794)) {
            // If currently at category fragment, then check whether the media has any non-hidden category
            if ((ListenerUtil.mutListener.listen(5783) ? (nextPos >= 3) : (ListenerUtil.mutListener.listen(5782) ? (nextPos > 3) : (ListenerUtil.mutListener.listen(5781) ? (nextPos < 3) : (ListenerUtil.mutListener.listen(5780) ? (nextPos != 3) : (ListenerUtil.mutListener.listen(5779) ? (nextPos == 3) : (nextPos <= 3))))))) {
                if (!ListenerUtil.mutListener.listen(5785)) {
                    reviewPager.setCurrentItem(nextPos);
                }
                if (!ListenerUtil.mutListener.listen(5793)) {
                    if ((ListenerUtil.mutListener.listen(5790) ? (nextPos >= 2) : (ListenerUtil.mutListener.listen(5789) ? (nextPos <= 2) : (ListenerUtil.mutListener.listen(5788) ? (nextPos > 2) : (ListenerUtil.mutListener.listen(5787) ? (nextPos < 2) : (ListenerUtil.mutListener.listen(5786) ? (nextPos != 2) : (nextPos == 2))))))) {
                        if (!ListenerUtil.mutListener.listen(5792)) {
                            // The media has no non-hidden category. Such media are already flagged by server-side bots, so no need to review manually.
                            if (!hasNonHiddenCategories) {
                                if (!ListenerUtil.mutListener.listen(5791)) {
                                    swipeToNext();
                                }
                                return;
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5784)) {
                    runRandomizer();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(5795)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(5796)) {
            compositeDisposable.clear();
        }
    }

    public void showSkipImageInfo() {
        if (!ListenerUtil.mutListener.listen(5797)) {
            DialogUtil.showAlertDialog(ReviewActivity.this, getString(R.string.skip_image).toUpperCase(), getString(R.string.skip_image_explanation), getString(android.R.string.ok), "", null, null);
        }
    }

    public void showReviewImageInfo() {
        if (!ListenerUtil.mutListener.listen(5798)) {
            DialogUtil.showAlertDialog(ReviewActivity.this, getString(R.string.title_activity_review), getString(R.string.review_image_explanation), getString(android.R.string.ok), "", null, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(5799)) {
            inflater.inflate(R.menu.menu_review_activty, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(5801)) {
            switch(item.getItemId()) {
                case R.id.menu_image_info:
                    if (!ListenerUtil.mutListener.listen(5800)) {
                        showReviewImageInfo();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * this function return the instance of  reviewImageFragment
     */
    public ReviewImageFragment getInstanceOfReviewImageFragment() {
        int currentItemOfReviewPager = reviewPager.getCurrentItem();
        if (!ListenerUtil.mutListener.listen(5802)) {
            reviewImageFragment = (ReviewImageFragment) reviewPagerAdapter.instantiateItem(reviewPager, currentItemOfReviewPager);
        }
        return reviewImageFragment;
    }

    /**
     * set up the media detail fragment when click on the review image
     */
    private void setUpMediaDetailFragment() {
        if (!ListenerUtil.mutListener.listen(5810)) {
            if ((ListenerUtil.mutListener.listen(5803) ? (mediaDetailContainer.getVisibility() == View.GONE || media != null) : (mediaDetailContainer.getVisibility() == View.GONE && media != null))) {
                if (!ListenerUtil.mutListener.listen(5804)) {
                    mediaDetailContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5805)) {
                    reviewContainer.setVisibility(View.INVISIBLE);
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (!ListenerUtil.mutListener.listen(5806)) {
                    mediaDetailFragment = new MediaDetailFragment();
                }
                Bundle bundle = new Bundle();
                if (!ListenerUtil.mutListener.listen(5807)) {
                    bundle.putParcelable("media", media);
                }
                if (!ListenerUtil.mutListener.listen(5808)) {
                    mediaDetailFragment.setArguments(bundle);
                }
                if (!ListenerUtil.mutListener.listen(5809)) {
                    fragmentManager.beginTransaction().add(R.id.mediaDetailContainer, mediaDetailFragment).addToBackStack("MediaDetail").commit();
                }
            }
        }
    }

    /**
     * handle the back pressed event of this activity
     * this function call every time when back button is pressed
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5813)) {
            if (mediaDetailContainer.getVisibility() == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(5811)) {
                    mediaDetailContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(5812)) {
                    reviewContainer.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5814)) {
            super.onBackPressed();
        }
    }

    /**
     * set up media detail fragment after orientation change
     */
    private void setUpMediaDetailOnOrientation() {
        Fragment mediaDetailFragment = getSupportFragmentManager().findFragmentById(R.id.mediaDetailContainer);
        if (!ListenerUtil.mutListener.listen(5818)) {
            if (mediaDetailFragment != null) {
                if (!ListenerUtil.mutListener.listen(5815)) {
                    mediaDetailContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5816)) {
                    reviewContainer.setVisibility(View.INVISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5817)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mediaDetailContainer, mediaDetailFragment).commit();
                }
            }
        }
    }
}
