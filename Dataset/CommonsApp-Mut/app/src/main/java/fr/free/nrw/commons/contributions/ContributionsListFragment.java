package fr.free.nrw.commons.contributions;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static fr.free.nrw.commons.di.NetworkingModule.NAMED_LANGUAGE_WIKI_PEDIA_WIKI_SITE;
import android.Manifest.permission;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.databinding.FragmentContributionsListBinding;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.media.MediaClient;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.SystemThemeUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.wikipedia.dataclient.WikiSite;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContributionsListFragment extends CommonsDaggerSupportFragment implements ContributionsListContract.View, ContributionsListAdapter.Callback, WikipediaInstructionsDialogFragment.Callback {

    private static final String RV_STATE = "rv_scroll_state";

    @Inject
    SystemThemeUtils systemThemeUtils;

    @Inject
    ContributionController controller;

    @Inject
    MediaClient mediaClient;

    @Named(NAMED_LANGUAGE_WIKI_PEDIA_WIKI_SITE)
    @Inject
    WikiSite languageWikipediaSite;

    @Inject
    ContributionsListPresenter contributionsListPresenter;

    @Inject
    SessionManager sessionManager;

    private FragmentContributionsListBinding binding;

    private Animation fab_close;

    private Animation fab_open;

    private Animation rotate_forward;

    private Animation rotate_backward;

    private boolean isFabOpen;

    @VisibleForTesting
    protected RecyclerView rvContributionsList;

    @VisibleForTesting
    protected ContributionsListAdapter adapter;

    @Nullable
    @VisibleForTesting
    protected Callback callback;

    private final int SPAN_COUNT_LANDSCAPE = 3;

    private final int SPAN_COUNT_PORTRAIT = 1;

    private int contributionsSize;

    private String userName;

    private ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean areAllGranted = true;
            if (!ListenerUtil.mutListener.listen(751)) {
                {
                    long _loopCounter14 = 0;
                    for (final boolean b : result.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                        if (!ListenerUtil.mutListener.listen(750)) {
                            areAllGranted = (ListenerUtil.mutListener.listen(749) ? (areAllGranted || b) : (areAllGranted && b));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(756)) {
                if (areAllGranted) {
                    if (!ListenerUtil.mutListener.listen(755)) {
                        controller.locationPermissionCallback.onLocationPermissionGranted();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(754)) {
                        if (shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)) {
                            if (!ListenerUtil.mutListener.listen(753)) {
                                controller.handleShowRationaleFlowCameraLocation(getActivity());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(752)) {
                                controller.locationPermissionCallback.onLocationPermissionDenied(getActivity().getString(R.string.in_app_camera_location_permission_denied));
                            }
                        }
                    }
                }
            }
        }
    });

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(757)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(759)) {
            // any userName- we expect it to be passed as an argument
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(758)) {
                    userName = getArguments().getString(ProfileActivity.KEY_USERNAME);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(761)) {
            if (StringUtils.isEmpty(userName)) {
                if (!ListenerUtil.mutListener.listen(760)) {
                    userName = sessionManager.getUserName();
                }
            }
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(762)) {
            binding = FragmentContributionsListBinding.inflate(inflater, container, false);
        }
        if (!ListenerUtil.mutListener.listen(763)) {
            rvContributionsList = binding.contributionsList;
        }
        if (!ListenerUtil.mutListener.listen(764)) {
            contributionsListPresenter.onAttachView(this);
        }
        if (!ListenerUtil.mutListener.listen(765)) {
            binding.fabCustomGallery.setOnClickListener(v -> launchCustomSelector());
        }
        if (!ListenerUtil.mutListener.listen(771)) {
            if (Objects.equals(sessionManager.getUserName(), userName)) {
                if (!ListenerUtil.mutListener.listen(769)) {
                    binding.tvContributionsOfUser.setVisibility(GONE);
                }
                if (!ListenerUtil.mutListener.listen(770)) {
                    binding.fabLayout.setVisibility(VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(766)) {
                    binding.tvContributionsOfUser.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(767)) {
                    binding.tvContributionsOfUser.setText(getString(R.string.contributions_of_user, userName));
                }
                if (!ListenerUtil.mutListener.listen(768)) {
                    binding.fabLayout.setVisibility(GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(772)) {
            initAdapter();
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(773)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(774)) {
            super.onDestroyView();
        }
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(775)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(778)) {
            if ((ListenerUtil.mutListener.listen(776) ? (getParentFragment() != null || getParentFragment() instanceof ContributionsFragment) : (getParentFragment() != null && getParentFragment() instanceof ContributionsFragment))) {
                if (!ListenerUtil.mutListener.listen(777)) {
                    callback = ((ContributionsFragment) getParentFragment());
                }
            }
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(779)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(780)) {
            // To avoid possible memory leak
            callback = null;
        }
    }

    private void initAdapter() {
        if (!ListenerUtil.mutListener.listen(781)) {
            adapter = new ContributionsListAdapter(this, mediaClient);
        }
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(782)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(783)) {
            initRecyclerView();
        }
        if (!ListenerUtil.mutListener.listen(784)) {
            initializeAnimations();
        }
        if (!ListenerUtil.mutListener.listen(785)) {
            setListeners();
        }
    }

    private void initRecyclerView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), getSpanCount(getResources().getConfiguration().orientation));
        if (!ListenerUtil.mutListener.listen(786)) {
            rvContributionsList.setLayoutManager(layoutManager);
        }
        // Setting flicker animation of recycler view to false.
        final ItemAnimator animator = rvContributionsList.getItemAnimator();
        if (!ListenerUtil.mutListener.listen(788)) {
            if (animator instanceof SimpleItemAnimator) {
                if (!ListenerUtil.mutListener.listen(787)) {
                    ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(789)) {
            contributionsListPresenter.setup(userName, Objects.equals(sessionManager.getUserName(), userName));
        }
        if (!ListenerUtil.mutListener.listen(790)) {
            contributionsListPresenter.contributionList.observe(getViewLifecycleOwner(), list -> {
                contributionsSize = list.size();
                adapter.submitList(list);
                if (callback != null) {
                    callback.notifyDataSetChanged();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(791)) {
            rvContributionsList.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(813)) {
            adapter.registerAdapterDataObserver(new AdapterDataObserver() {

                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    if (!ListenerUtil.mutListener.listen(792)) {
                        super.onItemRangeInserted(positionStart, itemCount);
                    }
                    if (!ListenerUtil.mutListener.listen(793)) {
                        contributionsSize = adapter.getItemCount();
                    }
                    if (!ListenerUtil.mutListener.listen(795)) {
                        if (callback != null) {
                            if (!ListenerUtil.mutListener.listen(794)) {
                                callback.notifyDataSetChanged();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(809)) {
                        if ((ListenerUtil.mutListener.listen(806) ? ((ListenerUtil.mutListener.listen(800) ? (itemCount >= 0) : (ListenerUtil.mutListener.listen(799) ? (itemCount <= 0) : (ListenerUtil.mutListener.listen(798) ? (itemCount < 0) : (ListenerUtil.mutListener.listen(797) ? (itemCount != 0) : (ListenerUtil.mutListener.listen(796) ? (itemCount == 0) : (itemCount > 0)))))) || (ListenerUtil.mutListener.listen(805) ? (positionStart >= 0) : (ListenerUtil.mutListener.listen(804) ? (positionStart <= 0) : (ListenerUtil.mutListener.listen(803) ? (positionStart > 0) : (ListenerUtil.mutListener.listen(802) ? (positionStart < 0) : (ListenerUtil.mutListener.listen(801) ? (positionStart != 0) : (positionStart == 0))))))) : ((ListenerUtil.mutListener.listen(800) ? (itemCount >= 0) : (ListenerUtil.mutListener.listen(799) ? (itemCount <= 0) : (ListenerUtil.mutListener.listen(798) ? (itemCount < 0) : (ListenerUtil.mutListener.listen(797) ? (itemCount != 0) : (ListenerUtil.mutListener.listen(796) ? (itemCount == 0) : (itemCount > 0)))))) && (ListenerUtil.mutListener.listen(805) ? (positionStart >= 0) : (ListenerUtil.mutListener.listen(804) ? (positionStart <= 0) : (ListenerUtil.mutListener.listen(803) ? (positionStart > 0) : (ListenerUtil.mutListener.listen(802) ? (positionStart < 0) : (ListenerUtil.mutListener.listen(801) ? (positionStart != 0) : (positionStart == 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(808)) {
                                if (adapter.getContributionForPosition(positionStart) != null) {
                                    if (!ListenerUtil.mutListener.listen(807)) {
                                        rvContributionsList.scrollToPosition(// Newly upload items are always added to the top
                                        0);
                                    }
                                }
                            }
                        }
                    }
                }

                /**
                 * Called whenever items in the list have changed
                 * Calls viewPagerNotifyDataSetChanged() that will notify the viewpager
                 */
                @Override
                public void onItemRangeChanged(final int positionStart, final int itemCount) {
                    if (!ListenerUtil.mutListener.listen(810)) {
                        super.onItemRangeChanged(positionStart, itemCount);
                    }
                    if (!ListenerUtil.mutListener.listen(812)) {
                        if (callback != null) {
                            if (!ListenerUtil.mutListener.listen(811)) {
                                callback.viewPagerNotifyDataSetChanged();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(817)) {
            // Fab close on touch outside (Scrolling or taping on item triggers this action).
            rvContributionsList.addOnItemTouchListener(new OnItemTouchListener() {

                /**
                 * Silently observe and/or take over touch events sent to the RecyclerView before
                 * they are handled by either the RecyclerView itself or its child views.
                 */
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(816)) {
                        if (e.getAction() == MotionEvent.ACTION_DOWN) {
                            if (!ListenerUtil.mutListener.listen(815)) {
                                if (isFabOpen) {
                                    if (!ListenerUtil.mutListener.listen(814)) {
                                        animateFAB(isFabOpen);
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }

                /**
                 * Process a touch event as part of a gesture that was claimed by returning true
                 * from a previous call to {@link #onInterceptTouchEvent}.
                 *
                 * @param rv
                 * @param e  MotionEvent describing the touch event. All coordinates are in the
                 *           RecyclerView's coordinate system.
                 */
                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                }

                /**
                 * Called when a child of RecyclerView does not want RecyclerView and its ancestors
                 * to intercept touch events with {@link ViewGroup#onInterceptTouchEvent(MotionEvent)}.
                 *
                 * @param disallowIntercept True if the child does not want the parent to intercept
                 *                          touch events.
                 */
                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                }
            });
        }
    }

    private int getSpanCount(final int orientation) {
        return orientation == Configuration.ORIENTATION_LANDSCAPE ? SPAN_COUNT_LANDSCAPE : SPAN_COUNT_PORTRAIT;
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(818)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(819)) {
            // check orientation
            binding.fabLayout.setOrientation(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(820)) {
            rvContributionsList.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount(newConfig.orientation)));
        }
    }

    private void initializeAnimations() {
        if (!ListenerUtil.mutListener.listen(821)) {
            fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        }
        if (!ListenerUtil.mutListener.listen(822)) {
            fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        }
        if (!ListenerUtil.mutListener.listen(823)) {
            rotate_forward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        }
        if (!ListenerUtil.mutListener.listen(824)) {
            rotate_backward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);
        }
    }

    private void setListeners() {
        if (!ListenerUtil.mutListener.listen(825)) {
            binding.fabPlus.setOnClickListener(view -> animateFAB(isFabOpen));
        }
        if (!ListenerUtil.mutListener.listen(826)) {
            binding.fabCamera.setOnClickListener(view -> {
                controller.initiateCameraPick(getActivity(), inAppCameraLocationPermissionLauncher);
                animateFAB(isFabOpen);
            });
        }
        if (!ListenerUtil.mutListener.listen(827)) {
            binding.fabGallery.setOnClickListener(view -> {
                controller.initiateGalleryPick(getActivity(), true);
                animateFAB(isFabOpen);
            });
        }
    }

    /**
     * Launch Custom Selector.
     */
    protected void launchCustomSelector() {
        if (!ListenerUtil.mutListener.listen(828)) {
            controller.initiateCustomGalleryPickWithPermission(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(829)) {
            animateFAB(isFabOpen);
        }
    }

    public void scrollToTop() {
        if (!ListenerUtil.mutListener.listen(830)) {
            rvContributionsList.smoothScrollToPosition(0);
        }
    }

    private void animateFAB(final boolean isFabOpen) {
        if (!ListenerUtil.mutListener.listen(831)) {
            this.isFabOpen = !isFabOpen;
        }
        if (!ListenerUtil.mutListener.listen(848)) {
            if (binding.fabPlus.isShown()) {
                if (!ListenerUtil.mutListener.listen(846)) {
                    if (isFabOpen) {
                        if (!ListenerUtil.mutListener.listen(839)) {
                            binding.fabPlus.startAnimation(rotate_backward);
                        }
                        if (!ListenerUtil.mutListener.listen(840)) {
                            binding.fabCamera.startAnimation(fab_close);
                        }
                        if (!ListenerUtil.mutListener.listen(841)) {
                            binding.fabGallery.startAnimation(fab_close);
                        }
                        if (!ListenerUtil.mutListener.listen(842)) {
                            binding.fabCustomGallery.startAnimation(fab_close);
                        }
                        if (!ListenerUtil.mutListener.listen(843)) {
                            binding.fabCamera.hide();
                        }
                        if (!ListenerUtil.mutListener.listen(844)) {
                            binding.fabGallery.hide();
                        }
                        if (!ListenerUtil.mutListener.listen(845)) {
                            binding.fabCustomGallery.hide();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(832)) {
                            binding.fabPlus.startAnimation(rotate_forward);
                        }
                        if (!ListenerUtil.mutListener.listen(833)) {
                            binding.fabCamera.startAnimation(fab_open);
                        }
                        if (!ListenerUtil.mutListener.listen(834)) {
                            binding.fabGallery.startAnimation(fab_open);
                        }
                        if (!ListenerUtil.mutListener.listen(835)) {
                            binding.fabCustomGallery.startAnimation(fab_open);
                        }
                        if (!ListenerUtil.mutListener.listen(836)) {
                            binding.fabCamera.show();
                        }
                        if (!ListenerUtil.mutListener.listen(837)) {
                            binding.fabGallery.show();
                        }
                        if (!ListenerUtil.mutListener.listen(838)) {
                            binding.fabCustomGallery.show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(847)) {
                    this.isFabOpen = !isFabOpen;
                }
            }
        }
    }

    /**
     * Shows welcome message if user has no contributions yet i.e. new user.
     */
    @Override
    public void showWelcomeTip(final boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(849)) {
            binding.noContributionsYet.setVisibility(shouldShow ? VISIBLE : GONE);
        }
    }

    /**
     * Responsible to set progress bar invisible and visible
     *
     * @param shouldShow True when contributions list should be hidden.
     */
    @Override
    public void showProgress(final boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(850)) {
            binding.loadingContributionsProgressBar.setVisibility(shouldShow ? VISIBLE : GONE);
        }
    }

    @Override
    public void showNoContributionsUI(final boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(851)) {
            binding.noContributionsYet.setVisibility(shouldShow ? VISIBLE : GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(852)) {
            super.onSaveInstanceState(outState);
        }
        final GridLayoutManager layoutManager = (GridLayoutManager) rvContributionsList.getLayoutManager();
        if (!ListenerUtil.mutListener.listen(853)) {
            outState.putParcelable(RV_STATE, layoutManager.onSaveInstanceState());
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(854)) {
            super.onViewStateRestored(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(856)) {
            if (null != savedInstanceState) {
                final Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(RV_STATE);
                if (!ListenerUtil.mutListener.listen(855)) {
                    rvContributionsList.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
                }
            }
        }
    }

    @Override
    public void retryUpload(final Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(858)) {
            if (null != callback) {
                if (!ListenerUtil.mutListener.listen(857)) {
                    // Just being safe, ideally they won't be called when detached
                    callback.retryUpload(contribution);
                }
            }
        }
    }

    @Override
    public void deleteUpload(final Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(859)) {
            DialogUtil.showAlertDialog(getActivity(), String.format(Locale.getDefault(), getString(R.string.cancelling_upload)), String.format(Locale.getDefault(), getString(R.string.cancel_upload_dialog)), String.format(Locale.getDefault(), getString(R.string.yes)), String.format(Locale.getDefault(), getString(R.string.no)), () -> {
                ViewUtil.showShortToast(getContext(), R.string.cancelling_upload);
                contributionsListPresenter.deleteUpload(contribution);
                CommonsApplication.cancelledUploads.add(contribution.getPageId());
            }, () -> {
            });
        }
    }

    @Override
    public void openMediaDetail(final int position, boolean isWikipediaButtonDisplayed) {
        if (!ListenerUtil.mutListener.listen(861)) {
            if (null != callback) {
                if (!ListenerUtil.mutListener.listen(860)) {
                    // Just being safe, ideally they won't be called when detached
                    callback.showDetail(position, isWikipediaButtonDisplayed);
                }
            }
        }
    }

    /**
     * Handle callback for wikipedia icon clicked
     *
     * @param contribution
     */
    @Override
    public void addImageToWikipedia(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(862)) {
            DialogUtil.showAlertDialog(getActivity(), getString(R.string.add_picture_to_wikipedia_article_title), getString(R.string.add_picture_to_wikipedia_article_desc), () -> {
                showAddImageToWikipediaInstructions(contribution);
            }, () -> {
            });
        }
    }

    /**
     * Pauses the current upload
     *
     * @param contribution
     */
    @Override
    public void pauseUpload(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(863)) {
            ViewUtil.showShortToast(getContext(), R.string.pausing_upload);
        }
        if (!ListenerUtil.mutListener.listen(864)) {
            callback.pauseUpload(contribution);
        }
    }

    /**
     * Resumes the current upload
     *
     * @param contribution
     */
    @Override
    public void resumeUpload(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(865)) {
            ViewUtil.showShortToast(getContext(), R.string.resuming_upload);
        }
        if (!ListenerUtil.mutListener.listen(866)) {
            callback.retryUpload(contribution);
        }
    }

    /**
     * Display confirmation dialog with instructions when the user tries to add image to wikipedia
     *
     * @param contribution
     */
    private void showAddImageToWikipediaInstructions(Contribution contribution) {
        FragmentManager fragmentManager = getFragmentManager();
        WikipediaInstructionsDialogFragment fragment = WikipediaInstructionsDialogFragment.newInstance(contribution);
        if (!ListenerUtil.mutListener.listen(867)) {
            fragment.setCallback(this::onConfirmClicked);
        }
        if (!ListenerUtil.mutListener.listen(868)) {
            fragment.show(fragmentManager, "WikimediaFragment");
        }
    }

    public Media getMediaAtPosition(final int i) {
        if (!ListenerUtil.mutListener.listen(869)) {
            if (adapter.getContributionForPosition(i) != null) {
                return adapter.getContributionForPosition(i).getMedia();
            }
        }
        return null;
    }

    public int getTotalMediaCount() {
        return contributionsSize;
    }

    /**
     * Open the editor for the language Wikipedia
     *
     * @param contribution
     */
    @Override
    public void onConfirmClicked(@Nullable Contribution contribution, boolean copyWikicode) {
        if (!ListenerUtil.mutListener.listen(871)) {
            if (copyWikicode) {
                String wikicode = contribution.getMedia().getWikiCode();
                if (!ListenerUtil.mutListener.listen(870)) {
                    Utils.copy("wikicode", wikicode, getContext());
                }
            }
        }
        final String url = languageWikipediaSite.mobileUrl() + "/wiki/" + contribution.getWikidataPlace().getWikipediaPageTitle();
        if (!ListenerUtil.mutListener.listen(872)) {
            Utils.handleWebUrl(getContext(), Uri.parse(url));
        }
    }

    public Integer getContributionStateAt(int position) {
        return adapter.getContributionForPosition(position).getState();
    }

    public interface Callback {

        void notifyDataSetChanged();

        void retryUpload(Contribution contribution);

        void showDetail(int position, boolean isWikipediaButtonDisplayed);

        void pauseUpload(Contribution contribution);

        // Notify the viewpager that number of items have changed.
        void viewPagerNotifyDataSetChanged();
    }
}
