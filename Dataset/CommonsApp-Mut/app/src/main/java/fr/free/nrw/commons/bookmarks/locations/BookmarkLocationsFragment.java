package fr.free.nrw.commons.bookmarks.locations;

import android.Manifest.permission;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.ContributionController;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.nearby.fragments.CommonPlaceClickActions;
import fr.free.nrw.commons.nearby.fragments.PlaceAdapter;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Unit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BookmarkLocationsFragment extends DaggerFragment {

    @BindView(R.id.statusMessage)
    TextView statusTextView;

    @BindView(R.id.loadingImagesProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.listView)
    RecyclerView recyclerView;

    @BindView(R.id.parentLayout)
    RelativeLayout parentLayout;

    @Inject
    BookmarkLocationsController controller;

    @Inject
    ContributionController contributionController;

    @Inject
    BookmarkLocationsDao bookmarkLocationDao;

    @Inject
    CommonPlaceClickActions commonPlaceClickActions;

    private PlaceAdapter adapter;

    private ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean areAllGranted = true;
            if (!ListenerUtil.mutListener.listen(4967)) {
                {
                    long _loopCounter70 = 0;
                    for (final boolean b : result.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter70", ++_loopCounter70);
                        if (!ListenerUtil.mutListener.listen(4966)) {
                            areAllGranted = (ListenerUtil.mutListener.listen(4965) ? (areAllGranted || b) : (areAllGranted && b));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4972)) {
                if (areAllGranted) {
                    if (!ListenerUtil.mutListener.listen(4971)) {
                        contributionController.locationPermissionCallback.onLocationPermissionGranted();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(4970)) {
                        if (shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)) {
                            if (!ListenerUtil.mutListener.listen(4969)) {
                                contributionController.handleShowRationaleFlowCameraLocation(getActivity());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4968)) {
                                contributionController.locationPermissionCallback.onLocationPermissionDenied(getActivity().getString(R.string.in_app_camera_location_permission_denied));
                            }
                        }
                    }
                }
            }
        }
    });

    /**
     * Create an instance of the fragment with the right bundle parameters
     * @return an instance of the fragment
     */
    public static BookmarkLocationsFragment newInstance() {
        return new BookmarkLocationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookmarks_locations, container, false);
        if (!ListenerUtil.mutListener.listen(4973)) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4974)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4975)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4976)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(4977)) {
            adapter = new PlaceAdapter(bookmarkLocationDao, place -> Unit.INSTANCE, (place, isBookmarked) -> {
                adapter.remove(place);
                return Unit.INSTANCE;
            }, commonPlaceClickActions, inAppCameraLocationPermissionLauncher);
        }
        if (!ListenerUtil.mutListener.listen(4978)) {
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4979)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4980)) {
            initList();
        }
    }

    /**
     * Initialize the recycler view with bookmarked locations
     */
    private void initList() {
        List<Place> places = controller.loadFavoritesLocations();
        if (!ListenerUtil.mutListener.listen(4981)) {
            adapter.setItems(places);
        }
        if (!ListenerUtil.mutListener.listen(4982)) {
            progressBar.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4991)) {
            if ((ListenerUtil.mutListener.listen(4987) ? (places.size() >= 0) : (ListenerUtil.mutListener.listen(4986) ? (places.size() > 0) : (ListenerUtil.mutListener.listen(4985) ? (places.size() < 0) : (ListenerUtil.mutListener.listen(4984) ? (places.size() != 0) : (ListenerUtil.mutListener.listen(4983) ? (places.size() == 0) : (places.size() <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(4989)) {
                    statusTextView.setText(R.string.bookmark_empty);
                }
                if (!ListenerUtil.mutListener.listen(4990)) {
                    statusTextView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4988)) {
                    statusTextView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(4992)) {
            contributionController.handleActivityResult(getActivity(), requestCode, resultCode, data);
        }
    }
}
