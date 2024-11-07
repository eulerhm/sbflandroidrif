package fr.free.nrw.commons.upload.depicts;

import static fr.free.nrw.commons.wikidata.WikidataConstants.SELECTED_NEARBY_PLACE;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.ContributionsFragment;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.media.MediaDetailFragment;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.ui.PasteSensitiveTextInputEditText;
import fr.free.nrw.commons.upload.UploadActivity;
import fr.free.nrw.commons.upload.UploadBaseFragment;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import fr.free.nrw.commons.utils.DialogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Unit;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fragment for showing depicted items list in Upload activity after media details
 */
public class DepictsFragment extends UploadBaseFragment implements DepictsContract.View {

    @BindView(R.id.depicts_title)
    TextView depictsTitle;

    @BindView(R.id.depicts_subtitle)
    TextView depictsSubTitle;

    @BindView(R.id.depicts_search_container)
    TextInputLayout depictsSearchContainer;

    @BindView(R.id.depicts_search)
    PasteSensitiveTextInputEditText depictsSearch;

    @BindView(R.id.depictsSearchInProgress)
    ProgressBar depictsSearchInProgress;

    @BindView(R.id.depicts_recycler_view)
    RecyclerView depictsRecyclerView;

    @BindView(R.id.tooltip)
    ImageView tooltip;

    @BindView(R.id.depicts_next)
    Button btnNext;

    @BindView(R.id.depicts_previous)
    Button btnPrevious;

    @Inject
    @Named("default_preferences")
    public JsonKvStore applicationKvStore;

    @Inject
    DepictsContract.UserActionListener presenter;

    private UploadDepictsAdapter adapter;

    private Disposable subscribe;

    private Media media;

    private ProgressDialog progressDialog;

    /**
     * Determines each encounter of edit depicts
     */
    private int count;

    private Place nearbyPlace;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_depicts_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6777)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6778)) {
            ButterKnife.bind(this, view);
        }
        Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(6781)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(6779)) {
                    media = bundle.getParcelable("Existing_Depicts");
                }
                if (!ListenerUtil.mutListener.listen(6780)) {
                    nearbyPlace = bundle.getParcelable(SELECTED_NEARBY_PLACE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6785)) {
            if ((ListenerUtil.mutListener.listen(6782) ? (callback != null && media != null) : (callback != null || media != null))) {
                if (!ListenerUtil.mutListener.listen(6783)) {
                    init();
                }
                if (!ListenerUtil.mutListener.listen(6784)) {
                    presenter.getDepictedItems().observe(getViewLifecycleOwner(), this::setDepictsList);
                }
            }
        }
    }

    /**
     * Initialize presenter and views
     */
    private void init() {
        if (!ListenerUtil.mutListener.listen(6791)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6790)) {
                    depictsTitle.setText(String.format(getString(R.string.step_count), callback.getIndexInViewFlipper(this) + 1, callback.getTotalNumberOfSteps(), getString(R.string.depicts_step_title)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6786)) {
                    depictsTitle.setText(R.string.edit_depictions);
                }
                if (!ListenerUtil.mutListener.listen(6787)) {
                    depictsSubTitle.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6788)) {
                    btnNext.setText(R.string.menu_save_categories);
                }
                if (!ListenerUtil.mutListener.listen(6789)) {
                    btnPrevious.setText(R.string.menu_cancel_upload);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6792)) {
            setDepictsSubTitle();
        }
        if (!ListenerUtil.mutListener.listen(6793)) {
            tooltip.setOnClickListener(v -> DialogUtil.showAlertDialog(getActivity(), getString(R.string.depicts_step_title), getString(R.string.depicts_tooltip), getString(android.R.string.ok), null, true));
        }
        if (!ListenerUtil.mutListener.listen(6796)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6795)) {
                    presenter.onAttachView(this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6794)) {
                    presenter.onAttachViewWithMedia(this, media);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6797)) {
            initRecyclerView();
        }
        if (!ListenerUtil.mutListener.listen(6798)) {
            addTextChangeListenerToSearchBox();
        }
    }

    /**
     * Removes the depicts subtitle If the activity is the instance of [UploadActivity] and
     * if multiple files aren't selected.
     */
    private void setDepictsSubTitle() {
        final Activity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(6801)) {
            if (activity instanceof UploadActivity) {
                final boolean isMultipleFileSelected = ((UploadActivity) activity).getIsMultipleFilesSelected();
                if (!ListenerUtil.mutListener.listen(6800)) {
                    if (!isMultipleFileSelected) {
                        if (!ListenerUtil.mutListener.listen(6799)) {
                            depictsSubTitle.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialise recyclerView and set adapter
     */
    private void initRecyclerView() {
        if (!ListenerUtil.mutListener.listen(6804)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6803)) {
                    adapter = new UploadDepictsAdapter(categoryItem -> {
                        presenter.onDepictItemClicked(categoryItem);
                        return Unit.INSTANCE;
                    }, nearbyPlace);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6802)) {
                    adapter = new UploadDepictsAdapter(item -> {
                        presenter.onDepictItemClicked(item);
                        return Unit.INSTANCE;
                    }, nearbyPlace);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6805)) {
            depictsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6806)) {
            depictsRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onBecameVisible() {
        if (!ListenerUtil.mutListener.listen(6807)) {
            super.onBecameVisible();
        }
        if (!ListenerUtil.mutListener.listen(6808)) {
            // Place is used (i.e. if the user accepts a nearby place dialog)
            presenter.selectPlaceDepictions();
        }
    }

    @Override
    public void goToNextScreen() {
        if (!ListenerUtil.mutListener.listen(6809)) {
            callback.onNextButtonClicked(callback.getIndexInViewFlipper(this));
        }
    }

    @Override
    public void goToPreviousScreen() {
        if (!ListenerUtil.mutListener.listen(6810)) {
            callback.onPreviousButtonClicked(callback.getIndexInViewFlipper(this));
        }
    }

    @Override
    public void noDepictionSelected() {
        if (!ListenerUtil.mutListener.listen(6816)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6815)) {
                    DialogUtil.showAlertDialog(getActivity(), getString(R.string.no_depictions_selected), getString(R.string.no_depictions_selected_warning_desc), getString(R.string.continue_message), getString(R.string.cancel), this::goToNextScreen, null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6811)) {
                    Toast.makeText(requireContext(), getString(R.string.no_depictions_selected), Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(6812)) {
                    presenter.clearPreviousSelection();
                }
                if (!ListenerUtil.mutListener.listen(6813)) {
                    updateDepicts();
                }
                if (!ListenerUtil.mutListener.listen(6814)) {
                    goBackToPreviousScreen();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(6817)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(6818)) {
            media = null;
        }
        if (!ListenerUtil.mutListener.listen(6819)) {
            presenter.onDetachView();
        }
        if (!ListenerUtil.mutListener.listen(6820)) {
            subscribe.dispose();
        }
    }

    @Override
    public void showProgress(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(6821)) {
            depictsSearchInProgress.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showError(Boolean value) {
        if (!ListenerUtil.mutListener.listen(6824)) {
            if (value) {
                if (!ListenerUtil.mutListener.listen(6823)) {
                    depictsSearchContainer.setError(getString(R.string.no_depiction_found));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6822)) {
                    depictsSearchContainer.setErrorEnabled(false);
                }
            }
        }
    }

    @Override
    public void setDepictsList(List<DepictedItem> depictedItemList) {
        if (!ListenerUtil.mutListener.listen(6838)) {
            if (applicationKvStore.getBoolean("first_edit_depict")) {
                if (!ListenerUtil.mutListener.listen(6835)) {
                    count = 1;
                }
                if (!ListenerUtil.mutListener.listen(6836)) {
                    applicationKvStore.putBoolean("first_edit_depict", false);
                }
                if (!ListenerUtil.mutListener.listen(6837)) {
                    adapter.setItems(depictedItemList);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6834)) {
                    if ((ListenerUtil.mutListener.listen(6830) ? (((ListenerUtil.mutListener.listen(6829) ? (count >= 0) : (ListenerUtil.mutListener.listen(6828) ? (count <= 0) : (ListenerUtil.mutListener.listen(6827) ? (count > 0) : (ListenerUtil.mutListener.listen(6826) ? (count < 0) : (ListenerUtil.mutListener.listen(6825) ? (count != 0) : (count == 0))))))) || (!depictedItemList.isEmpty())) : (((ListenerUtil.mutListener.listen(6829) ? (count >= 0) : (ListenerUtil.mutListener.listen(6828) ? (count <= 0) : (ListenerUtil.mutListener.listen(6827) ? (count > 0) : (ListenerUtil.mutListener.listen(6826) ? (count < 0) : (ListenerUtil.mutListener.listen(6825) ? (count != 0) : (count == 0))))))) && (!depictedItemList.isEmpty())))) {
                        if (!ListenerUtil.mutListener.listen(6832)) {
                            adapter.setItems(null);
                        }
                        if (!ListenerUtil.mutListener.listen(6833)) {
                            count = 1;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6831)) {
                            adapter.setItems(depictedItemList);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6842)) {
            // list and smoothly scroll to the top of the search result list.
            depictsRecyclerView.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(6839)) {
                        depictsRecyclerView.smoothScrollToPosition(0);
                    }
                    if (!ListenerUtil.mutListener.listen(6841)) {
                        depictsRecyclerView.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(6840)) {
                                    depictsRecyclerView.smoothScrollToPosition(0);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * Returns required context
     */
    @Override
    public Context getFragmentContext() {
        return requireContext();
    }

    /**
     * Returns to previous fragment
     */
    @Override
    public void goBackToPreviousScreen() {
        if (!ListenerUtil.mutListener.listen(6843)) {
            getFragmentManager().popBackStack();
        }
    }

    /**
     * Gets existing depictions IDs from media
     */
    @Override
    public List<String> getExistingDepictions() {
        return (media == null) ? null : media.getDepictionIds();
    }

    /**
     * Shows the progress dialog
     */
    @Override
    public void showProgressDialog() {
        if (!ListenerUtil.mutListener.listen(6844)) {
            progressDialog = new ProgressDialog(requireContext());
        }
        if (!ListenerUtil.mutListener.listen(6845)) {
            progressDialog.setMessage(getString(R.string.please_wait));
        }
        if (!ListenerUtil.mutListener.listen(6846)) {
            progressDialog.show();
        }
    }

    /**
     * Hides the progress dialog
     */
    @Override
    public void dismissProgressDialog() {
        if (!ListenerUtil.mutListener.listen(6847)) {
            progressDialog.dismiss();
        }
    }

    /**
     * Update the depicts
     */
    @Override
    public void updateDepicts() {
        final MediaDetailFragment mediaDetailFragment = (MediaDetailFragment) getParentFragment();
        assert mediaDetailFragment != null;
        if (!ListenerUtil.mutListener.listen(6848)) {
            mediaDetailFragment.onResume();
        }
    }

    /**
     * Determines the calling fragment by media nullability and act accordingly
     */
    @OnClick(R.id.depicts_next)
    public void onNextButtonClicked() {
        if (!ListenerUtil.mutListener.listen(6851)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6850)) {
                    presenter.updateDepictions(media);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6849)) {
                    presenter.verifyDepictions();
                }
            }
        }
    }

    /**
     * Determines the calling fragment by media nullability and act accordingly
     */
    @OnClick(R.id.depicts_previous)
    public void onPreviousButtonClicked() {
        if (!ListenerUtil.mutListener.listen(6856)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6853)) {
                    presenter.clearPreviousSelection();
                }
                if (!ListenerUtil.mutListener.listen(6854)) {
                    updateDepicts();
                }
                if (!ListenerUtil.mutListener.listen(6855)) {
                    goBackToPreviousScreen();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6852)) {
                    callback.onPreviousButtonClicked(callback.getIndexInViewFlipper(this));
                }
            }
        }
    }

    /**
     * Text change listener for the edit text view of depicts
     */
    private void addTextChangeListenerToSearchBox() {
        if (!ListenerUtil.mutListener.listen(6857)) {
            subscribe = RxTextView.textChanges(depictsSearch).doOnEach(v -> depictsSearchContainer.setError(null)).takeUntil(RxView.detaches(depictsSearch)).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(filter -> searchForDepictions(filter.toString()), Timber::e);
        }
    }

    /**
     * Search for depictions for the following query
     *
     * @param query query string
     */
    private void searchForDepictions(final String query) {
        if (!ListenerUtil.mutListener.listen(6858)) {
            presenter.searchForDepictions(query);
        }
    }

    /**
     * Hides the action bar while opening editing fragment
     */
    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(6859)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(6867)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6860)) {
                    depictsSearch.setOnKeyListener((v, keyCode, event) -> {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            depictsSearch.clearFocus();
                            presenter.clearPreviousSelection();
                            updateDepicts();
                            goBackToPreviousScreen();
                            return true;
                        }
                        return false;
                    });
                }
                if (!ListenerUtil.mutListener.listen(6861)) {
                    Objects.requireNonNull(getView()).setFocusableInTouchMode(true);
                }
                if (!ListenerUtil.mutListener.listen(6862)) {
                    getView().requestFocus();
                }
                if (!ListenerUtil.mutListener.listen(6863)) {
                    getView().setOnKeyListener((v, keyCode, event) -> {
                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                            presenter.clearPreviousSelection();
                            updateDepicts();
                            goBackToPreviousScreen();
                            return true;
                        }
                        return false;
                    });
                }
                if (!ListenerUtil.mutListener.listen(6864)) {
                    Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
                }
                if (!ListenerUtil.mutListener.listen(6866)) {
                    if (getParentFragment().getParentFragment().getParentFragment() instanceof ContributionsFragment) {
                        if (!ListenerUtil.mutListener.listen(6865)) {
                            ((ContributionsFragment) (getParentFragment().getParentFragment().getParentFragment())).nearbyNotificationCardView.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Shows the action bar while closing editing fragment
     */
    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(6868)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(6870)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6869)) {
                    Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
                }
            }
        }
    }
}
