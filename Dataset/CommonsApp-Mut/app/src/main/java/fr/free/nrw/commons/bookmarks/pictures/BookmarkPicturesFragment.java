package fr.free.nrw.commons.bookmarks.pictures;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.bookmarks.BookmarkListRootFragment;
import fr.free.nrw.commons.category.GridViewAdapter;
import fr.free.nrw.commons.utils.NetworkUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BookmarkPicturesFragment extends DaggerFragment {

    private GridViewAdapter gridAdapter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.statusMessage)
    TextView statusTextView;

    @BindView(R.id.loadingImagesProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.bookmarkedPicturesList)
    GridView gridView;

    @BindView(R.id.parentLayout)
    RelativeLayout parentLayout;

    @Inject
    BookmarkPicturesController controller;

    /**
     * Create an instance of the fragment with the right bundle parameters
     * @return an instance of the fragment
     */
    public static BookmarkPicturesFragment newInstance() {
        return new BookmarkPicturesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookmarks_pictures, container, false);
        if (!ListenerUtil.mutListener.listen(4800)) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4801)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4802)) {
            gridView.setOnItemClickListener((AdapterView.OnItemClickListener) getParentFragment());
        }
        if (!ListenerUtil.mutListener.listen(4803)) {
            initList();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(4804)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(4805)) {
            controller.stop();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4806)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(4807)) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4808)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4814)) {
            if (controller.needRefreshBookmarkedPictures()) {
                if (!ListenerUtil.mutListener.listen(4809)) {
                    gridView.setVisibility(GONE);
                }
                if (!ListenerUtil.mutListener.listen(4812)) {
                    if (gridAdapter != null) {
                        if (!ListenerUtil.mutListener.listen(4810)) {
                            gridAdapter.clear();
                        }
                        if (!ListenerUtil.mutListener.listen(4811)) {
                            ((BookmarkListRootFragment) getParentFragment()).viewPagerNotifyDataSetChanged();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4813)) {
                    initList();
                }
            }
        }
    }

    /**
     * Checks for internet connection and then initializes
     * the recycler view with bookmarked pictures
     */
    @SuppressLint("CheckResult")
    private void initList() {
        if (!ListenerUtil.mutListener.listen(4816)) {
            if (!NetworkUtils.isInternetConnectionEstablished(getContext())) {
                if (!ListenerUtil.mutListener.listen(4815)) {
                    handleNoInternet();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4817)) {
            progressBar.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4818)) {
            statusTextView.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(4819)) {
            compositeDisposable.add(controller.loadBookmarkedPictures().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleSuccess, this::handleError));
        }
    }

    /**
     * Handles the UI updates for no internet scenario
     */
    private void handleNoInternet() {
        if (!ListenerUtil.mutListener.listen(4820)) {
            progressBar.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(4825)) {
            if ((ListenerUtil.mutListener.listen(4821) ? (gridAdapter == null && gridAdapter.isEmpty()) : (gridAdapter == null || gridAdapter.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(4823)) {
                    statusTextView.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4824)) {
                    statusTextView.setText(getString(R.string.no_internet));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4822)) {
                    ViewUtil.showShortSnackbar(parentLayout, R.string.no_internet);
                }
            }
        }
    }

    /**
     * Logs and handles API error scenario
     * @param throwable
     */
    private void handleError(Throwable throwable) {
        if (!ListenerUtil.mutListener.listen(4826)) {
            Timber.e(throwable, "Error occurred while loading images inside a category");
        }
        try {
            if (!ListenerUtil.mutListener.listen(4828)) {
                ViewUtil.showShortSnackbar(parentLayout, R.string.error_loading_images);
            }
            if (!ListenerUtil.mutListener.listen(4829)) {
                initErrorView();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(4827)) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the UI updates for a error scenario
     */
    private void initErrorView() {
        if (!ListenerUtil.mutListener.listen(4830)) {
            progressBar.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(4835)) {
            if ((ListenerUtil.mutListener.listen(4831) ? (gridAdapter == null && gridAdapter.isEmpty()) : (gridAdapter == null || gridAdapter.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(4833)) {
                    statusTextView.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4834)) {
                    statusTextView.setText(getString(R.string.no_images_found));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4832)) {
                    statusTextView.setVisibility(GONE);
                }
            }
        }
    }

    /**
     * Handles the UI updates when there is no bookmarks
     */
    private void initEmptyBookmarkListView() {
        if (!ListenerUtil.mutListener.listen(4836)) {
            progressBar.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(4841)) {
            if ((ListenerUtil.mutListener.listen(4837) ? (gridAdapter == null && gridAdapter.isEmpty()) : (gridAdapter == null || gridAdapter.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(4839)) {
                    statusTextView.setVisibility(VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4840)) {
                    statusTextView.setText(getString(R.string.bookmark_empty));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4838)) {
                    statusTextView.setVisibility(GONE);
                }
            }
        }
    }

    /**
     * Handles the success scenario
     * On first load, it initializes the grid view. On subsequent loads, it adds items to the adapter
     * @param collection List of new Media to be displayed
     */
    private void handleSuccess(List<Media> collection) {
        if (!ListenerUtil.mutListener.listen(4843)) {
            if (collection == null) {
                if (!ListenerUtil.mutListener.listen(4842)) {
                    initErrorView();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4845)) {
            if (collection.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(4844)) {
                    initEmptyBookmarkListView();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4854)) {
            if (gridAdapter == null) {
                if (!ListenerUtil.mutListener.listen(4853)) {
                    setAdapter(collection);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4850)) {
                    if (gridAdapter.containsAll(collection)) {
                        if (!ListenerUtil.mutListener.listen(4846)) {
                            progressBar.setVisibility(GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(4847)) {
                            statusTextView.setVisibility(GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(4848)) {
                            gridView.setVisibility(VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(4849)) {
                            gridView.setAdapter(gridAdapter);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(4851)) {
                    gridAdapter.addItems(collection);
                }
                if (!ListenerUtil.mutListener.listen(4852)) {
                    ((BookmarkListRootFragment) getParentFragment()).viewPagerNotifyDataSetChanged();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4855)) {
            progressBar.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(4856)) {
            statusTextView.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(4857)) {
            gridView.setVisibility(VISIBLE);
        }
    }

    /**
     * Initializes the adapter with a list of Media objects
     * @param mediaList List of new Media to be displayed
     */
    private void setAdapter(List<Media> mediaList) {
        if (!ListenerUtil.mutListener.listen(4858)) {
            gridAdapter = new GridViewAdapter(this.getContext(), R.layout.layout_category_images, mediaList);
        }
        if (!ListenerUtil.mutListener.listen(4859)) {
            gridView.setAdapter(gridAdapter);
        }
    }

    /**
     * It return an instance of gridView adapter which helps in extracting media details
     * used by the gridView
     * @return  GridView Adapter
     */
    public ListAdapter getAdapter() {
        return gridView.getAdapter();
    }
}
