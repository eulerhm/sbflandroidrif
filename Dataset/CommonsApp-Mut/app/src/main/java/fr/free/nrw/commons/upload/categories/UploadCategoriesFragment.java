package fr.free.nrw.commons.upload.categories;

import static fr.free.nrw.commons.wikidata.WikidataConstants.SELECTED_NEARBY_PLACE_CATEGORY;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import fr.free.nrw.commons.category.CategoryItem;
import fr.free.nrw.commons.contributions.ContributionsFragment;
import fr.free.nrw.commons.media.MediaDetailFragment;
import fr.free.nrw.commons.ui.PasteSensitiveTextInputEditText;
import fr.free.nrw.commons.upload.UploadActivity;
import fr.free.nrw.commons.upload.UploadBaseFragment;
import fr.free.nrw.commons.utils.DialogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Unit;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadCategoriesFragment extends UploadBaseFragment implements CategoriesContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_subtitle)
    TextView tvSubTitle;

    @BindView(R.id.til_container_search)
    TextInputLayout tilContainerEtSearch;

    @BindView(R.id.et_search)
    PasteSensitiveTextInputEditText etSearch;

    @BindView(R.id.pb_categories)
    ProgressBar pbCategories;

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @BindView(R.id.tooltip)
    ImageView tooltip;

    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.btn_previous)
    Button btnPrevious;

    @Inject
    CategoriesContract.UserActionListener presenter;

    private UploadCategoryAdapter adapter;

    private Disposable subscribe;

    /**
     * Current media
     */
    private Media media;

    /**
     * Progress Dialog for showing background process
     */
    private ProgressDialog progressDialog;

    /**
     * WikiText from the server
     */
    private String wikiText;

    private String nearbyPlaceCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_categories_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6695)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6696)) {
            ButterKnife.bind(this, view);
        }
        final Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(6700)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(6697)) {
                    media = bundle.getParcelable("Existing_Categories");
                }
                if (!ListenerUtil.mutListener.listen(6698)) {
                    wikiText = bundle.getString("WikiText");
                }
                if (!ListenerUtil.mutListener.listen(6699)) {
                    nearbyPlaceCategory = bundle.getString(SELECTED_NEARBY_PLACE_CATEGORY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6703)) {
            if (callback != null) {
                if (!ListenerUtil.mutListener.listen(6701)) {
                    init();
                }
                if (!ListenerUtil.mutListener.listen(6702)) {
                    presenter.getCategories().observe(getViewLifecycleOwner(), this::setCategories);
                }
            }
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(6709)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6708)) {
                    tvTitle.setText(getString(R.string.step_count, callback.getIndexInViewFlipper(this) + 1, callback.getTotalNumberOfSteps(), getString(R.string.categories_activity_title)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6704)) {
                    tvTitle.setText(R.string.edit_categories);
                }
                if (!ListenerUtil.mutListener.listen(6705)) {
                    tvSubTitle.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6706)) {
                    btnNext.setText(R.string.menu_save_categories);
                }
                if (!ListenerUtil.mutListener.listen(6707)) {
                    btnPrevious.setText(R.string.menu_cancel_upload);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6710)) {
            setTvSubTitle();
        }
        if (!ListenerUtil.mutListener.listen(6712)) {
            tooltip.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6711)) {
                        DialogUtil.showAlertDialog(getActivity(), getString(R.string.categories_activity_title), getString(R.string.categories_tooltip), getString(android.R.string.ok), null, true);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6715)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6714)) {
                    presenter.onAttachView(this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6713)) {
                    presenter.onAttachViewWithMedia(this, media);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6716)) {
            initRecyclerView();
        }
        if (!ListenerUtil.mutListener.listen(6717)) {
            addTextChangeListenerToEtSearch();
        }
    }

    private void addTextChangeListenerToEtSearch() {
        if (!ListenerUtil.mutListener.listen(6718)) {
            subscribe = RxTextView.textChanges(etSearch).doOnEach(v -> tilContainerEtSearch.setError(null)).takeUntil(RxView.detaches(etSearch)).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(filter -> searchForCategory(filter.toString()), Timber::e);
        }
    }

    /**
     * Removes  the tv subtitle If the activity is the instance of [UploadActivity] and
     * if multiple files aren't selected.
     */
    private void setTvSubTitle() {
        final Activity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(6721)) {
            if (activity instanceof UploadActivity) {
                final boolean isMultipleFileSelected = ((UploadActivity) activity).getIsMultipleFilesSelected();
                if (!ListenerUtil.mutListener.listen(6720)) {
                    if (!isMultipleFileSelected) {
                        if (!ListenerUtil.mutListener.listen(6719)) {
                            tvSubTitle.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void searchForCategory(String query) {
        if (!ListenerUtil.mutListener.listen(6722)) {
            presenter.searchForCategories(query);
        }
    }

    private void initRecyclerView() {
        if (!ListenerUtil.mutListener.listen(6723)) {
            adapter = new UploadCategoryAdapter(categoryItem -> {
                presenter.onCategoryItemClicked(categoryItem);
                return Unit.INSTANCE;
            }, nearbyPlaceCategory);
        }
        if (!ListenerUtil.mutListener.listen(6724)) {
            rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6725)) {
            rvCategories.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(6726)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(6727)) {
            presenter.onDetachView();
        }
        if (!ListenerUtil.mutListener.listen(6728)) {
            subscribe.dispose();
        }
    }

    @Override
    public void showProgress(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(6729)) {
            pbCategories.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showError(String error) {
        if (!ListenerUtil.mutListener.listen(6730)) {
            tilContainerEtSearch.setError(error);
        }
    }

    @Override
    public void showError(int stringResourceId) {
        if (!ListenerUtil.mutListener.listen(6731)) {
            tilContainerEtSearch.setError(getString(stringResourceId));
        }
    }

    @Override
    public void setCategories(List<CategoryItem> categories) {
        if (!ListenerUtil.mutListener.listen(6734)) {
            if (categories == null) {
                if (!ListenerUtil.mutListener.listen(6733)) {
                    adapter.clear();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6732)) {
                    adapter.setItems(categories);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6735)) {
            adapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(6739)) {
            // list and smoothly scroll to the top of the search result list.
            rvCategories.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(6736)) {
                        rvCategories.smoothScrollToPosition(0);
                    }
                    if (!ListenerUtil.mutListener.listen(6738)) {
                        rvCategories.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(6737)) {
                                    rvCategories.smoothScrollToPosition(0);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void goToNextScreen() {
        if (!ListenerUtil.mutListener.listen(6740)) {
            callback.onNextButtonClicked(callback.getIndexInViewFlipper(this));
        }
    }

    @Override
    public void showNoCategorySelected() {
        if (!ListenerUtil.mutListener.listen(6745)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(6744)) {
                    DialogUtil.showAlertDialog(getActivity(), getString(R.string.no_categories_selected), getString(R.string.no_categories_selected_warning_desc), getString(R.string.continue_message), getString(R.string.cancel), () -> goToNextScreen(), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6741)) {
                    Toast.makeText(requireContext(), getString(R.string.no_categories_selected), Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(6742)) {
                    presenter.clearPreviousSelection();
                }
                if (!ListenerUtil.mutListener.listen(6743)) {
                    goBackToPreviousScreen();
                }
            }
        }
    }

    /**
     * Gets existing categories from media
     */
    @Override
    public List<String> getExistingCategories() {
        return (media == null) ? null : media.getCategories();
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
        if (!ListenerUtil.mutListener.listen(6746)) {
            getFragmentManager().popBackStack();
        }
    }

    /**
     * Shows the progress dialog
     */
    @Override
    public void showProgressDialog() {
        if (!ListenerUtil.mutListener.listen(6747)) {
            progressDialog = new ProgressDialog(requireContext());
        }
        if (!ListenerUtil.mutListener.listen(6748)) {
            progressDialog.setMessage(getString(R.string.please_wait));
        }
        if (!ListenerUtil.mutListener.listen(6749)) {
            progressDialog.show();
        }
    }

    /**
     * Hides the progress dialog
     */
    @Override
    public void dismissProgressDialog() {
        if (!ListenerUtil.mutListener.listen(6750)) {
            progressDialog.dismiss();
        }
    }

    /**
     * Refreshes the categories
     */
    @Override
    public void refreshCategories() {
        final MediaDetailFragment mediaDetailFragment = (MediaDetailFragment) getParentFragment();
        assert mediaDetailFragment != null;
        if (!ListenerUtil.mutListener.listen(6751)) {
            mediaDetailFragment.updateCategories();
        }
    }

    @OnClick(R.id.btn_next)
    public void onNextButtonClicked() {
        if (!ListenerUtil.mutListener.listen(6754)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6753)) {
                    presenter.updateCategories(media, wikiText);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6752)) {
                    presenter.verifyCategories();
                }
            }
        }
    }

    @OnClick(R.id.btn_previous)
    public void onPreviousButtonClicked() {
        if (!ListenerUtil.mutListener.listen(6760)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6756)) {
                    presenter.clearPreviousSelection();
                }
                if (!ListenerUtil.mutListener.listen(6757)) {
                    adapter.setItems(null);
                }
                final MediaDetailFragment mediaDetailFragment = (MediaDetailFragment) getParentFragment();
                assert mediaDetailFragment != null;
                if (!ListenerUtil.mutListener.listen(6758)) {
                    mediaDetailFragment.onResume();
                }
                if (!ListenerUtil.mutListener.listen(6759)) {
                    goBackToPreviousScreen();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6755)) {
                    callback.onPreviousButtonClicked(callback.getIndexInViewFlipper(this));
                }
            }
        }
    }

    @Override
    protected void onBecameVisible() {
        if (!ListenerUtil.mutListener.listen(6761)) {
            super.onBecameVisible();
        }
        if (!ListenerUtil.mutListener.listen(6762)) {
            presenter.selectCategories();
        }
        final Editable text = etSearch.getText();
        if (!ListenerUtil.mutListener.listen(6764)) {
            if (text != null) {
                if (!ListenerUtil.mutListener.listen(6763)) {
                    presenter.searchForCategories(text.toString());
                }
            }
        }
    }

    /**
     * Hides the action bar while opening editing fragment
     */
    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(6765)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(6773)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6766)) {
                    etSearch.setOnKeyListener((v, keyCode, event) -> {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            etSearch.clearFocus();
                            presenter.clearPreviousSelection();
                            final MediaDetailFragment mediaDetailFragment = (MediaDetailFragment) getParentFragment();
                            assert mediaDetailFragment != null;
                            mediaDetailFragment.onResume();
                            goBackToPreviousScreen();
                            return true;
                        }
                        return false;
                    });
                }
                if (!ListenerUtil.mutListener.listen(6767)) {
                    Objects.requireNonNull(getView()).setFocusableInTouchMode(true);
                }
                if (!ListenerUtil.mutListener.listen(6768)) {
                    getView().requestFocus();
                }
                if (!ListenerUtil.mutListener.listen(6769)) {
                    getView().setOnKeyListener((v, keyCode, event) -> {
                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                            presenter.clearPreviousSelection();
                            final MediaDetailFragment mediaDetailFragment = (MediaDetailFragment) getParentFragment();
                            assert mediaDetailFragment != null;
                            mediaDetailFragment.onResume();
                            goBackToPreviousScreen();
                            return true;
                        }
                        return false;
                    });
                }
                if (!ListenerUtil.mutListener.listen(6770)) {
                    Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
                }
                if (!ListenerUtil.mutListener.listen(6772)) {
                    if (getParentFragment().getParentFragment().getParentFragment() instanceof ContributionsFragment) {
                        if (!ListenerUtil.mutListener.listen(6771)) {
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
        if (!ListenerUtil.mutListener.listen(6774)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(6776)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(6775)) {
                    Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
                }
            }
        }
    }
}
