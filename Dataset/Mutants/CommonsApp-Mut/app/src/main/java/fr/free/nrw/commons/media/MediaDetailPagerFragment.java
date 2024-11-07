package fr.free.nrw.commons.media;

import static fr.free.nrw.commons.Utils.handleWebUrl;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.snackbar.Snackbar;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.bookmarks.models.Bookmark;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesContentProvider;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesDao;
import fr.free.nrw.commons.contributions.Contribution;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.utils.DownloadUtils;
import fr.free.nrw.commons.utils.ImageUtils;
import fr.free.nrw.commons.utils.NetworkUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaDetailPagerFragment extends CommonsDaggerSupportFragment implements ViewPager.OnPageChangeListener, MediaDetailFragment.Callback {

    @Inject
    BookmarkPicturesDao bookmarkDao;

    @Inject
    protected OkHttpJsonApiClient okHttpJsonApiClient;

    @Inject
    protected SessionManager sessionManager;

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.mediaDetailsPager)
    ViewPager pager;

    private boolean editable;

    private boolean isFeaturedImage;

    private boolean isWikipediaButtonDisplayed;

    MediaDetailAdapter adapter;

    private Bookmark bookmark;

    private MediaDetailProvider provider;

    private boolean isFromFeaturedRootFragment;

    private int position;

    private ArrayList<Integer> removedItems = new ArrayList<Integer>();

    public void clearRemoved() {
        if (!ListenerUtil.mutListener.listen(9312)) {
            removedItems.clear();
        }
    }

    public ArrayList<Integer> getRemovedItems() {
        return removedItems;
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided
     * parameters.
     *
     * This method will create a new instance of MediaDetailPagerFragment and the arguments will be
     * saved to a bundle which will be later available in the {@link #onCreate(Bundle)}
     * @param editable
     * @param isFeaturedImage
     * @return
     */
    public static MediaDetailPagerFragment newInstance(boolean editable, boolean isFeaturedImage) {
        MediaDetailPagerFragment mediaDetailPagerFragment = new MediaDetailPagerFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(9313)) {
            args.putBoolean("is_editable", editable);
        }
        if (!ListenerUtil.mutListener.listen(9314)) {
            args.putBoolean("is_featured_image", isFeaturedImage);
        }
        if (!ListenerUtil.mutListener.listen(9315)) {
            mediaDetailPagerFragment.setArguments(args);
        }
        return mediaDetailPagerFragment;
    }

    public MediaDetailPagerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_detail_pager, container, false);
        if (!ListenerUtil.mutListener.listen(9316)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(9317)) {
            pager.addOnPageChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(9318)) {
            adapter = new MediaDetailAdapter(getChildFragmentManager());
        }
        // ActionBar is now supported in both activities - if this crashes something is quite wrong
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(9320)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(9319)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            } else {
                throw new AssertionError("Action bar should not be null!");
            }
        }
        if (!ListenerUtil.mutListener.listen(9323)) {
            // If fragment is associated with ProfileActivity, then hide the tabLayout
            if (getActivity() instanceof ProfileActivity) {
                if (!ListenerUtil.mutListener.listen(9322)) {
                    ((ProfileActivity) getActivity()).tabLayout.setVisibility(View.GONE);
                }
            } else // Else if fragment is associated with MainActivity then hide that tab layout
            if (getActivity() instanceof MainActivity) {
                if (!ListenerUtil.mutListener.listen(9321)) {
                    ((MainActivity) getActivity()).hideTabs();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9324)) {
            pager.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(9327)) {
            if (savedInstanceState != null) {
                final int pageNumber = savedInstanceState.getInt("current-page");
                if (!ListenerUtil.mutListener.listen(9325)) {
                    pager.setCurrentItem(pageNumber, false);
                }
                if (!ListenerUtil.mutListener.listen(9326)) {
                    getActivity().invalidateOptionsMenu();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9328)) {
            adapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(9329)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(9330)) {
            outState.putInt("current-page", pager.getCurrentItem());
        }
        if (!ListenerUtil.mutListener.listen(9331)) {
            outState.putBoolean("editable", editable);
        }
        if (!ListenerUtil.mutListener.listen(9332)) {
            outState.putBoolean("isFeaturedImage", isFeaturedImage);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9333)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9338)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(9334)) {
                    editable = savedInstanceState.getBoolean("editable", false);
                }
                if (!ListenerUtil.mutListener.listen(9335)) {
                    isFeaturedImage = savedInstanceState.getBoolean("isFeaturedImage", false);
                }
                if (!ListenerUtil.mutListener.listen(9337)) {
                    if (null != pager) {
                        if (!ListenerUtil.mutListener.listen(9336)) {
                            pager.setCurrentItem(savedInstanceState.getInt("current-page", 0), false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9339)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(9340)) {
            initProvider();
        }
    }

    /**
     * initialise the provider, based on from where the fragment was started, as in from an activity
     * or a fragment
     */
    private void initProvider() {
        if (!ListenerUtil.mutListener.listen(9343)) {
            if (getParentFragment() != null) {
                if (!ListenerUtil.mutListener.listen(9342)) {
                    provider = (MediaDetailProvider) getParentFragment();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9341)) {
                    provider = (MediaDetailProvider) getActivity();
                }
            }
        }
    }

    public MediaDetailProvider getMediaDetailProvider() {
        return provider;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getActivity() == null) {
            if (!ListenerUtil.mutListener.listen(9344)) {
                Timber.d("Returning as activity is destroyed!");
            }
            return true;
        }
        Media m = provider.getMediaAtPosition(pager.getCurrentItem());
        MediaDetailFragment mediaDetailFragment = this.adapter.getCurrentMediaDetailFragment();
        switch(item.getItemId()) {
            case R.id.menu_bookmark_current_image:
                boolean bookmarkExists = bookmarkDao.updateBookmark(bookmark);
                Snackbar snackbar = bookmarkExists ? Snackbar.make(getView(), R.string.add_bookmark, Snackbar.LENGTH_LONG) : Snackbar.make(getView(), R.string.remove_bookmark, Snackbar.LENGTH_LONG);
                if (!ListenerUtil.mutListener.listen(9345)) {
                    snackbar.show();
                }
                if (!ListenerUtil.mutListener.listen(9346)) {
                    updateBookmarkState(item);
                }
                return true;
            case R.id.menu_share_current_image:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                if (!ListenerUtil.mutListener.listen(9347)) {
                    shareIntent.setType("text/plain");
                }
                if (!ListenerUtil.mutListener.listen(9348)) {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, m.getDisplayTitle() + " \n" + m.getPageTitle().getCanonicalUri());
                }
                if (!ListenerUtil.mutListener.listen(9349)) {
                    startActivity(Intent.createChooser(shareIntent, "Share image via..."));
                }
                // of back stack fixing:https://github.com/commons-app/apps-android-commons/issues/2296
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                if (!ListenerUtil.mutListener.listen(9357)) {
                    if ((ListenerUtil.mutListener.listen(9354) ? (supportFragmentManager.getBackStackEntryCount() >= 2) : (ListenerUtil.mutListener.listen(9353) ? (supportFragmentManager.getBackStackEntryCount() <= 2) : (ListenerUtil.mutListener.listen(9352) ? (supportFragmentManager.getBackStackEntryCount() > 2) : (ListenerUtil.mutListener.listen(9351) ? (supportFragmentManager.getBackStackEntryCount() != 2) : (ListenerUtil.mutListener.listen(9350) ? (supportFragmentManager.getBackStackEntryCount() == 2) : (supportFragmentManager.getBackStackEntryCount() < 2))))))) {
                        if (!ListenerUtil.mutListener.listen(9355)) {
                            supportFragmentManager.beginTransaction().addToBackStack(MediaDetailPagerFragment.class.getName()).commit();
                        }
                        if (!ListenerUtil.mutListener.listen(9356)) {
                            supportFragmentManager.executePendingTransactions();
                        }
                    }
                }
                return true;
            case R.id.menu_browser_current_image:
                if (!ListenerUtil.mutListener.listen(9358)) {
                    // View in browser
                    handleWebUrl(requireContext(), Uri.parse(m.getPageTitle().getMobileUri()));
                }
                return true;
            case R.id.menu_download_current_image:
                // Download
                if (!NetworkUtils.isInternetConnectionEstablished(getActivity())) {
                    if (!ListenerUtil.mutListener.listen(9359)) {
                        ViewUtil.showShortSnackbar(getView(), R.string.no_internet);
                    }
                    return false;
                }
                if (!ListenerUtil.mutListener.listen(9360)) {
                    DownloadUtils.downloadMedia(getActivity(), m);
                }
                return true;
            case R.id.menu_set_as_wallpaper:
                if (!ListenerUtil.mutListener.listen(9361)) {
                    // Set wallpaper
                    setWallpaper(m);
                }
                return true;
            case R.id.menu_set_as_avatar:
                if (!ListenerUtil.mutListener.listen(9362)) {
                    // Set avatar
                    setAvatar(m);
                }
                return true;
            case R.id.menu_view_user_page:
                if (!ListenerUtil.mutListener.listen(9365)) {
                    if ((ListenerUtil.mutListener.listen(9363) ? (m != null || m.getUser() != null) : (m != null && m.getUser() != null))) {
                        if (!ListenerUtil.mutListener.listen(9364)) {
                            ProfileActivity.startYourself(getActivity(), m.getUser(), !Objects.equals(sessionManager.getUserName(), m.getUser()));
                        }
                    }
                }
                return true;
            case R.id.menu_view_report:
                if (!ListenerUtil.mutListener.listen(9366)) {
                    showReportDialog(m);
                }
            case R.id.menu_view_set_white_background:
                if (!ListenerUtil.mutListener.listen(9368)) {
                    if (mediaDetailFragment != null) {
                        if (!ListenerUtil.mutListener.listen(9367)) {
                            mediaDetailFragment.onImageBackgroundChanged(ContextCompat.getColor(getContext(), R.color.white));
                        }
                    }
                }
                return true;
            case R.id.menu_view_set_black_background:
                if (!ListenerUtil.mutListener.listen(9370)) {
                    if (mediaDetailFragment != null) {
                        if (!ListenerUtil.mutListener.listen(9369)) {
                            mediaDetailFragment.onImageBackgroundChanged(ContextCompat.getColor(getContext(), R.color.black));
                        }
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showReportDialog(final Media media) {
        if (!ListenerUtil.mutListener.listen(9371)) {
            if (media == null) {
                return;
            }
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        final String[] values = requireContext().getResources().getStringArray(R.array.report_violation_options);
        if (!ListenerUtil.mutListener.listen(9372)) {
            builder.setTitle(R.string.report_violation);
        }
        if (!ListenerUtil.mutListener.listen(9373)) {
            builder.setItems(R.array.report_violation_options, (dialog, which) -> {
                sendReportEmail(media, values[which]);
            });
        }
        if (!ListenerUtil.mutListener.listen(9374)) {
            builder.show();
        }
    }

    private void sendReportEmail(final Media media, final String type) {
        final String technicalInfo = getTechInfo(media, type);
        final Intent feedbackIntent = new Intent(Intent.ACTION_SENDTO);
        if (!ListenerUtil.mutListener.listen(9375)) {
            feedbackIntent.setType("message/rfc822");
        }
        if (!ListenerUtil.mutListener.listen(9376)) {
            feedbackIntent.setData(Uri.parse("mailto:"));
        }
        if (!ListenerUtil.mutListener.listen(9377)) {
            feedbackIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { CommonsApplication.REPORT_EMAIL });
        }
        if (!ListenerUtil.mutListener.listen(9378)) {
            feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, CommonsApplication.REPORT_EMAIL_SUBJECT);
        }
        if (!ListenerUtil.mutListener.listen(9379)) {
            feedbackIntent.putExtra(Intent.EXTRA_TEXT, technicalInfo);
        }
        try {
            if (!ListenerUtil.mutListener.listen(9381)) {
                startActivity(feedbackIntent);
            }
        } catch (final ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(9380)) {
                Toast.makeText(getActivity(), R.string.no_email_client, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getTechInfo(final Media media, final String type) {
        final StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(9382)) {
            builder.append("Report type: ").append(type).append("\n\n");
        }
        if (!ListenerUtil.mutListener.listen(9383)) {
            builder.append("Image that you want to report: ").append(media.getImageUrl()).append("\n\n");
        }
        if (!ListenerUtil.mutListener.listen(9384)) {
            builder.append("User that you want to report: ").append(media.getAuthor()).append("\n\n");
        }
        if (!ListenerUtil.mutListener.listen(9386)) {
            if (sessionManager.getUserName() != null) {
                if (!ListenerUtil.mutListener.listen(9385)) {
                    builder.append("Your username: ").append(sessionManager.getUserName()).append("\n\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9387)) {
            builder.append("Violation reason: ").append("\n");
        }
        if (!ListenerUtil.mutListener.listen(9388)) {
            builder.append("----------------------------------------------").append("\n").append("(please write reason here)").append("\n").append("----------------------------------------------").append("\n\n").append("Thank you for your report! Our team will investigate as soon as possible.").append("\n").append("Please note that images also have a `Nominate for deletion` button.");
        }
        return builder.toString();
    }

    /**
     * Set the media as the device's wallpaper if the imageUrl is not null
     * Fails silently if setting the wallpaper fails
     * @param media
     */
    private void setWallpaper(Media media) {
        if (!ListenerUtil.mutListener.listen(9391)) {
            if ((ListenerUtil.mutListener.listen(9389) ? (media.getImageUrl() == null && media.getImageUrl().isEmpty()) : (media.getImageUrl() == null || media.getImageUrl().isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(9390)) {
                    Timber.d("Media URL not present");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9392)) {
            ImageUtils.setWallpaperFromImageUrl(getActivity(), Uri.parse(media.getImageUrl()));
        }
    }

    /**
     * Set the media as user's leaderboard avatar
     * @param media
     */
    private void setAvatar(Media media) {
        if (!ListenerUtil.mutListener.listen(9395)) {
            if ((ListenerUtil.mutListener.listen(9393) ? (media.getImageUrl() == null && media.getImageUrl().isEmpty()) : (media.getImageUrl() == null || media.getImageUrl().isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(9394)) {
                    Timber.d("Media URL not present");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9396)) {
            ImageUtils.setAvatarFromImageUrl(getActivity(), media.getImageUrl(), Objects.requireNonNull(sessionManager.getCurrentAccount()).name, okHttpJsonApiClient, compositeDisposable);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(9427)) {
            if (!editable) {
                if (!ListenerUtil.mutListener.listen(9397)) {
                    // see http://stackoverflow.com/a/8495697/17865
                    menu.clear();
                }
                if (!ListenerUtil.mutListener.listen(9398)) {
                    inflater.inflate(R.menu.fragment_image_detail, menu);
                }
                if (!ListenerUtil.mutListener.listen(9426)) {
                    if (pager != null) {
                        MediaDetailProvider provider = getMediaDetailProvider();
                        if (!ListenerUtil.mutListener.listen(9399)) {
                            if (provider == null) {
                                return;
                            }
                        }
                        final int position;
                        if (isFromFeaturedRootFragment) {
                            position = this.position;
                        } else {
                            position = pager.getCurrentItem();
                        }
                        Media m = provider.getMediaAtPosition(position);
                        if (!ListenerUtil.mutListener.listen(9423)) {
                            if (m != null) {
                                if (!ListenerUtil.mutListener.listen(9405)) {
                                    // Enable default set of actions, then re-enable different set of actions only if it is a failed contrib
                                    menu.findItem(R.id.menu_browser_current_image).setEnabled(true).setVisible(true);
                                }
                                if (!ListenerUtil.mutListener.listen(9406)) {
                                    menu.findItem(R.id.menu_share_current_image).setEnabled(true).setVisible(true);
                                }
                                if (!ListenerUtil.mutListener.listen(9407)) {
                                    menu.findItem(R.id.menu_download_current_image).setEnabled(true).setVisible(true);
                                }
                                if (!ListenerUtil.mutListener.listen(9408)) {
                                    menu.findItem(R.id.menu_bookmark_current_image).setEnabled(true).setVisible(true);
                                }
                                if (!ListenerUtil.mutListener.listen(9409)) {
                                    menu.findItem(R.id.menu_set_as_wallpaper).setEnabled(true).setVisible(true);
                                }
                                if (!ListenerUtil.mutListener.listen(9411)) {
                                    if (m.getUser() != null) {
                                        if (!ListenerUtil.mutListener.listen(9410)) {
                                            menu.findItem(R.id.menu_view_user_page).setEnabled(true).setVisible(true);
                                        }
                                    }
                                }
                                try {
                                    URL mediaUrl = new URL(m.getImageUrl());
                                    if (!ListenerUtil.mutListener.listen(9413)) {
                                        this.handleBackgroundColorMenuItems(() -> BitmapFactory.decodeStream(mediaUrl.openConnection().getInputStream()), menu);
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(9412)) {
                                        Timber.e("Cant detect media transparency");
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(9414)) {
                                    // Initialize bookmark object
                                    bookmark = new Bookmark(m.getFilename(), m.getAuthor(), BookmarkPicturesContentProvider.uriForName(m.getFilename()));
                                }
                                if (!ListenerUtil.mutListener.listen(9415)) {
                                    updateBookmarkState(menu.findItem(R.id.menu_bookmark_current_image));
                                }
                                final Integer contributionState = provider.getContributionStateAt(position);
                                if (!ListenerUtil.mutListener.listen(9422)) {
                                    if (contributionState != null) {
                                        if (!ListenerUtil.mutListener.listen(9421)) {
                                            switch(contributionState) {
                                                case Contribution.STATE_FAILED:
                                                case Contribution.STATE_IN_PROGRESS:
                                                case Contribution.STATE_QUEUED:
                                                    if (!ListenerUtil.mutListener.listen(9416)) {
                                                        menu.findItem(R.id.menu_browser_current_image).setEnabled(false).setVisible(false);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(9417)) {
                                                        menu.findItem(R.id.menu_share_current_image).setEnabled(false).setVisible(false);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(9418)) {
                                                        menu.findItem(R.id.menu_download_current_image).setEnabled(false).setVisible(false);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(9419)) {
                                                        menu.findItem(R.id.menu_bookmark_current_image).setEnabled(false).setVisible(false);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(9420)) {
                                                        menu.findItem(R.id.menu_set_as_wallpaper).setEnabled(false).setVisible(false);
                                                    }
                                                    break;
                                                case Contribution.STATE_COMPLETED:
                                                    // Default set of menu items works fine. Treat same as regular media object
                                                    break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9400)) {
                                    menu.findItem(R.id.menu_browser_current_image).setEnabled(false).setVisible(false);
                                }
                                if (!ListenerUtil.mutListener.listen(9401)) {
                                    menu.findItem(R.id.menu_share_current_image).setEnabled(false).setVisible(false);
                                }
                                if (!ListenerUtil.mutListener.listen(9402)) {
                                    menu.findItem(R.id.menu_download_current_image).setEnabled(false).setVisible(false);
                                }
                                if (!ListenerUtil.mutListener.listen(9403)) {
                                    menu.findItem(R.id.menu_bookmark_current_image).setEnabled(false).setVisible(false);
                                }
                                if (!ListenerUtil.mutListener.listen(9404)) {
                                    menu.findItem(R.id.menu_set_as_wallpaper).setEnabled(false).setVisible(false);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9425)) {
                            if (!sessionManager.isUserLoggedIn()) {
                                if (!ListenerUtil.mutListener.listen(9424)) {
                                    menu.findItem(R.id.menu_set_as_avatar).setVisible(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Decide wether or not we should display the background color menu items
     * We display them if the image is transparent
     * @param getBitmap
     * @param menu
     */
    private void handleBackgroundColorMenuItems(Callable<Bitmap> getBitmap, Menu menu) {
        if (!ListenerUtil.mutListener.listen(9428)) {
            Observable.fromCallable(getBitmap).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(image -> {
                if (image.hasAlpha()) {
                    menu.findItem(R.id.menu_view_set_white_background).setVisible(true).setEnabled(true);
                    menu.findItem(R.id.menu_view_set_black_background).setVisible(true).setEnabled(true);
                }
            });
        }
    }

    private void updateBookmarkState(MenuItem item) {
        boolean isBookmarked = bookmarkDao.findBookmark(bookmark);
        if (!ListenerUtil.mutListener.listen(9433)) {
            if (isBookmarked) {
                if (!ListenerUtil.mutListener.listen(9432)) {
                    if (removedItems.contains(pager.getCurrentItem())) {
                        if (!ListenerUtil.mutListener.listen(9431)) {
                            removedItems.remove(new Integer(pager.getCurrentItem()));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9430)) {
                    if (!removedItems.contains(pager.getCurrentItem())) {
                        if (!ListenerUtil.mutListener.listen(9429)) {
                            removedItems.add(pager.getCurrentItem());
                        }
                    }
                }
            }
        }
        int icon = isBookmarked ? R.drawable.menu_ic_round_star_filled_24px : R.drawable.menu_ic_round_star_border_24px;
        if (!ListenerUtil.mutListener.listen(9434)) {
            item.setIcon(icon);
        }
    }

    public void showImage(int i, boolean isWikipediaButtonDisplayed) {
        if (!ListenerUtil.mutListener.listen(9435)) {
            this.isWikipediaButtonDisplayed = isWikipediaButtonDisplayed;
        }
        if (!ListenerUtil.mutListener.listen(9436)) {
            setViewPagerCurrentItem(i);
        }
    }

    public void showImage(int i) {
        if (!ListenerUtil.mutListener.listen(9437)) {
            setViewPagerCurrentItem(i);
        }
    }

    /**
     * This function waits for the item to load then sets the item to current item
     * @param position current item that to be shown
     */
    private void setViewPagerCurrentItem(int position) {
        final Boolean[] currentItemNotShown = { true };
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                if (!ListenerUtil.mutListener.listen(9446)) {
                    {
                        long _loopCounter161 = 0;
                        while (currentItemNotShown[0]) {
                            ListenerUtil.loopListener.listen("_loopCounter161", ++_loopCounter161);
                            if (!ListenerUtil.mutListener.listen(9445)) {
                                if ((ListenerUtil.mutListener.listen(9442) ? (adapter.getCount() >= position) : (ListenerUtil.mutListener.listen(9441) ? (adapter.getCount() <= position) : (ListenerUtil.mutListener.listen(9440) ? (adapter.getCount() < position) : (ListenerUtil.mutListener.listen(9439) ? (adapter.getCount() != position) : (ListenerUtil.mutListener.listen(9438) ? (adapter.getCount() == position) : (adapter.getCount() > position))))))) {
                                    if (!ListenerUtil.mutListener.listen(9443)) {
                                        pager.setCurrentItem(position, false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(9444)) {
                                        currentItemNotShown[0] = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(9447)) {
            new Thread(runnable).start();
        }
    }

    /**
     * The method notify the viewpager that number of items have changed.
     */
    public void notifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(9449)) {
            if (null != adapter) {
                if (!ListenerUtil.mutListener.listen(9448)) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        if (!ListenerUtil.mutListener.listen(9451)) {
            if (getActivity() == null) {
                if (!ListenerUtil.mutListener.listen(9450)) {
                    Timber.d("Returning as activity is destroyed!");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9452)) {
            getActivity().invalidateOptionsMenu();
        }
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    public void onDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(9454)) {
            if (null != adapter) {
                if (!ListenerUtil.mutListener.listen(9453)) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Called after the media is nominated for deletion
     *
     * @param index item position that has been nominated
     */
    @Override
    public void nominatingForDeletion(int index) {
        if (!ListenerUtil.mutListener.listen(9455)) {
            provider.refreshNominatedMedia(index);
        }
    }

    public interface MediaDetailProvider {

        Media getMediaAtPosition(int i);

        int getTotalMediaCount();

        Integer getContributionStateAt(int position);

        // Reload media detail fragment once media is nominated
        void refreshNominatedMedia(int index);
    }

    // FragmentStatePagerAdapter allows user to swipe across collection of images (no. of images undetermined)
    private class MediaDetailAdapter extends FragmentStatePagerAdapter {

        /**
         * Keeps track of the current displayed fragment.
         */
        private Fragment mCurrentFragment;

        public MediaDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if ((ListenerUtil.mutListener.listen(9460) ? (i >= 0) : (ListenerUtil.mutListener.listen(9459) ? (i <= 0) : (ListenerUtil.mutListener.listen(9458) ? (i > 0) : (ListenerUtil.mutListener.listen(9457) ? (i < 0) : (ListenerUtil.mutListener.listen(9456) ? (i != 0) : (i == 0))))))) {
                // See bug https://code.google.com/p/android/issues/detail?id=27526
                if (getActivity() == null) {
                    if (!ListenerUtil.mutListener.listen(9461)) {
                        Timber.d("Skipping getItem. Returning as activity is destroyed!");
                    }
                    return null;
                }
                if (!ListenerUtil.mutListener.listen(9462)) {
                    pager.postDelayed(() -> getActivity().invalidateOptionsMenu(), 5);
                }
            }
            if (isFromFeaturedRootFragment) {
                return MediaDetailFragment.forMedia((ListenerUtil.mutListener.listen(9466) ? (position % i) : (ListenerUtil.mutListener.listen(9465) ? (position / i) : (ListenerUtil.mutListener.listen(9464) ? (position * i) : (ListenerUtil.mutListener.listen(9463) ? (position - i) : (position + i))))), editable, isFeaturedImage, isWikipediaButtonDisplayed);
            } else {
                return MediaDetailFragment.forMedia(i, editable, isFeaturedImage, isWikipediaButtonDisplayed);
            }
        }

        @Override
        public int getCount() {
            if (!ListenerUtil.mutListener.listen(9468)) {
                if (getActivity() == null) {
                    if (!ListenerUtil.mutListener.listen(9467)) {
                        Timber.d("Skipping getCount. Returning as activity is destroyed!");
                    }
                    return 0;
                }
            }
            return provider.getTotalMediaCount();
        }

        /**
         * Get the currently displayed fragment.
         * @return
         */
        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }

        /**
         * If current fragment is of type MediaDetailFragment, return it, otherwise return null.
         * @return MediaDetailFragment
         */
        public MediaDetailFragment getCurrentMediaDetailFragment() {
            if (!ListenerUtil.mutListener.listen(9469)) {
                if (mCurrentFragment instanceof MediaDetailFragment) {
                    return (MediaDetailFragment) mCurrentFragment;
                }
            }
            return null;
        }

        /**
         * Called to inform the adapter of which item is currently considered to be the "primary",
         * that is the one show to the user as the current page.
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void setPrimaryItem(@NonNull final ViewGroup container, final int position, @NonNull final Object object) {
            if (!ListenerUtil.mutListener.listen(9471)) {
                // Update the current fragment if changed
                if (getCurrentFragment() != object) {
                    if (!ListenerUtil.mutListener.listen(9470)) {
                        mCurrentFragment = ((Fragment) object);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9472)) {
                super.setPrimaryItem(container, position, object);
            }
        }
    }
}
