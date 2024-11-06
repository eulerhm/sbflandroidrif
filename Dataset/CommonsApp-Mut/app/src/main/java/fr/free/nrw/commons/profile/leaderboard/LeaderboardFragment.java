package fr.free.nrw.commons.profile.leaderboard;

import static fr.free.nrw.commons.profile.leaderboard.LeaderboardConstants.LOADED;
import static fr.free.nrw.commons.profile.leaderboard.LeaderboardConstants.LOADING;
import static fr.free.nrw.commons.profile.leaderboard.LeaderboardConstants.PAGE_SIZE;
import static fr.free.nrw.commons.profile.leaderboard.LeaderboardConstants.START_OFFSET;
import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Objects;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class extends the CommonsDaggerSupportFragment and creates leaderboard fragment
 */
public class LeaderboardFragment extends CommonsDaggerSupportFragment {

    @BindView(R.id.leaderboard_list)
    RecyclerView leaderboardListRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @BindView(R.id.duration_spinner)
    Spinner durationSpinner;

    @BindView(R.id.scroll)
    Button scrollButton;

    @Inject
    SessionManager sessionManager;

    @Inject
    OkHttpJsonApiClient okHttpJsonApiClient;

    @Inject
    ViewModelFactory viewModelFactory;

    /**
     * View model for the paged leaderboard list
     */
    private LeaderboardListViewModel viewModel;

    /**
     * Composite disposable for API call
     */
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Duration of the leaderboard API
     */
    private String duration;

    /**
     * Category of the Leaderboard API
     */
    private String category;

    /**
     * Page size of the leaderboard API
     */
    private int limit = PAGE_SIZE;

    /**
     * offset for the leaderboard API
     */
    private int offset = START_OFFSET;

    /**
     * Set initial User Rank to 0
     */
    private int userRank;

    /**
     * This variable represents if user wants to scroll to his rank or not
     */
    private boolean scrollToRank;

    private String userName;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5357)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5359)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(5358)) {
                    userName = getArguments().getString(ProfileActivity.KEY_USERNAME);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        if (!ListenerUtil.mutListener.listen(5360)) {
            ButterKnife.bind(this, rootView);
        }
        if (!ListenerUtil.mutListener.listen(5361)) {
            hideLayouts();
        }
        if (!ListenerUtil.mutListener.listen(5364)) {
            // Leaderboard currently unimplemented in Beta flavor. Skip all API calls and disable menu
            if (ConfigUtils.isBetaFlavour()) {
                if (!ListenerUtil.mutListener.listen(5362)) {
                    progressBar.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(5363)) {
                    scrollButton.setVisibility(View.GONE);
                }
                return rootView;
            }
        }
        if (!ListenerUtil.mutListener.listen(5365)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5366)) {
            setSpinners();
        }
        /**
         * This array is for the duration filter, we have three filters weekly, yearly and all-time
         * each filter have a key and value pair, the value represents the param of the API
         */
        String[] durationValues = getContext().getResources().getStringArray(R.array.leaderboard_duration_values);
        /**
         * This array is for the category filter, we have three filters upload, used and nearby
         * each filter have a key and value pair, the value represents the param of the API
         */
        String[] categoryValues = getContext().getResources().getStringArray(R.array.leaderboard_category_values);
        if (!ListenerUtil.mutListener.listen(5367)) {
            duration = durationValues[0];
        }
        if (!ListenerUtil.mutListener.listen(5368)) {
            category = categoryValues[0];
        }
        if (!ListenerUtil.mutListener.listen(5369)) {
            setLeaderboard(duration, category, limit, offset);
        }
        if (!ListenerUtil.mutListener.listen(5372)) {
            durationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!ListenerUtil.mutListener.listen(5370)) {
                        duration = durationValues[durationSpinner.getSelectedItemPosition()];
                    }
                    if (!ListenerUtil.mutListener.listen(5371)) {
                        refreshLeaderboard();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(5375)) {
            categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!ListenerUtil.mutListener.listen(5373)) {
                        category = categoryValues[categorySpinner.getSelectedItemPosition()];
                    }
                    if (!ListenerUtil.mutListener.listen(5374)) {
                        refreshLeaderboard();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(5376)) {
            scrollButton.setOnClickListener(view -> scrollToUserRank());
        }
        return rootView;
    }

    @Override
    public void setMenuVisibility(boolean visible) {
        if (!ListenerUtil.mutListener.listen(5377)) {
            super.setMenuVisibility(visible);
        }
        if (!ListenerUtil.mutListener.listen(5385)) {
            // notify Beta users the page data is unavailable
            if ((ListenerUtil.mutListener.listen(5378) ? (ConfigUtils.isBetaFlavour() || visible) : (ConfigUtils.isBetaFlavour() && visible))) {
                Context ctx = null;
                if (!ListenerUtil.mutListener.listen(5382)) {
                    if (getContext() != null) {
                        if (!ListenerUtil.mutListener.listen(5381)) {
                            ctx = getContext();
                        }
                    } else if ((ListenerUtil.mutListener.listen(5379) ? (getView() != null || getView().getContext() != null) : (getView() != null && getView().getContext() != null))) {
                        if (!ListenerUtil.mutListener.listen(5380)) {
                            ctx = getView().getContext();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5384)) {
                    if (ctx != null) {
                        if (!ListenerUtil.mutListener.listen(5383)) {
                            Toast.makeText(ctx, R.string.leaderboard_unavailable_beta, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
    }

    /**
     * Refreshes the leaderboard list
     */
    private void refreshLeaderboard() {
        if (!ListenerUtil.mutListener.listen(5386)) {
            scrollToRank = false;
        }
        if (!ListenerUtil.mutListener.listen(5389)) {
            if (viewModel != null) {
                if (!ListenerUtil.mutListener.listen(5387)) {
                    viewModel.refresh(duration, category, limit, offset);
                }
                if (!ListenerUtil.mutListener.listen(5388)) {
                    setLeaderboard(duration, category, limit, offset);
                }
            }
        }
    }

    /**
     * Performs Auto Scroll to the User's Rank
     * We use userRank+1 to load one extra user and prevent overlapping of my rank button
     * If you are viewing the leaderboard below userRank, it scrolls to the user rank at the top
     */
    private void scrollToUserRank() {
        if (!ListenerUtil.mutListener.listen(5423)) {
            if ((ListenerUtil.mutListener.listen(5394) ? (userRank >= 0) : (ListenerUtil.mutListener.listen(5393) ? (userRank <= 0) : (ListenerUtil.mutListener.listen(5392) ? (userRank > 0) : (ListenerUtil.mutListener.listen(5391) ? (userRank < 0) : (ListenerUtil.mutListener.listen(5390) ? (userRank != 0) : (userRank == 0))))))) {
                if (!ListenerUtil.mutListener.listen(5422)) {
                    Toast.makeText(getContext(), R.string.no_achievements_yet, Toast.LENGTH_SHORT).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5421)) {
                    if ((ListenerUtil.mutListener.listen(5403) ? (Objects.requireNonNull(leaderboardListRecyclerView.getAdapter()).getItemCount() >= (ListenerUtil.mutListener.listen(5398) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5397) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5396) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5395) ? (userRank - 1) : (userRank + 1)))))) : (ListenerUtil.mutListener.listen(5402) ? (Objects.requireNonNull(leaderboardListRecyclerView.getAdapter()).getItemCount() <= (ListenerUtil.mutListener.listen(5398) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5397) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5396) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5395) ? (userRank - 1) : (userRank + 1)))))) : (ListenerUtil.mutListener.listen(5401) ? (Objects.requireNonNull(leaderboardListRecyclerView.getAdapter()).getItemCount() < (ListenerUtil.mutListener.listen(5398) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5397) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5396) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5395) ? (userRank - 1) : (userRank + 1)))))) : (ListenerUtil.mutListener.listen(5400) ? (Objects.requireNonNull(leaderboardListRecyclerView.getAdapter()).getItemCount() != (ListenerUtil.mutListener.listen(5398) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5397) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5396) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5395) ? (userRank - 1) : (userRank + 1)))))) : (ListenerUtil.mutListener.listen(5399) ? (Objects.requireNonNull(leaderboardListRecyclerView.getAdapter()).getItemCount() == (ListenerUtil.mutListener.listen(5398) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5397) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5396) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5395) ? (userRank - 1) : (userRank + 1)))))) : (Objects.requireNonNull(leaderboardListRecyclerView.getAdapter()).getItemCount() > (ListenerUtil.mutListener.listen(5398) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5397) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5396) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5395) ? (userRank - 1) : (userRank + 1)))))))))))) {
                        if (!ListenerUtil.mutListener.listen(5420)) {
                            leaderboardListRecyclerView.smoothScrollToPosition((ListenerUtil.mutListener.listen(5419) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5418) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5417) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5416) ? (userRank - 1) : (userRank + 1))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5415)) {
                            if (viewModel != null) {
                                if (!ListenerUtil.mutListener.listen(5408)) {
                                    viewModel.refresh(duration, category, (ListenerUtil.mutListener.listen(5407) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5406) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5405) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5404) ? (userRank - 1) : (userRank + 1))))), 0);
                                }
                                if (!ListenerUtil.mutListener.listen(5413)) {
                                    setLeaderboard(duration, category, (ListenerUtil.mutListener.listen(5412) ? (userRank % 1) : (ListenerUtil.mutListener.listen(5411) ? (userRank / 1) : (ListenerUtil.mutListener.listen(5410) ? (userRank * 1) : (ListenerUtil.mutListener.listen(5409) ? (userRank - 1) : (userRank + 1))))), 0);
                                }
                                if (!ListenerUtil.mutListener.listen(5414)) {
                                    scrollToRank = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Set the spinners for the leaderboard filters
     */
    private void setSpinners() {
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(getContext(), R.array.leaderboard_categories, android.R.layout.simple_spinner_item);
        if (!ListenerUtil.mutListener.listen(5424)) {
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(5425)) {
            categorySpinner.setAdapter(categoryAdapter);
        }
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(getContext(), R.array.leaderboard_durations, android.R.layout.simple_spinner_item);
        if (!ListenerUtil.mutListener.listen(5426)) {
            durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(5427)) {
            durationSpinner.setAdapter(durationAdapter);
        }
    }

    /**
     * To call the API to get results
     * which then sets the views using setLeaderboardUser method
     */
    private void setLeaderboard(String duration, String category, int limit, int offset) {
        if (!ListenerUtil.mutListener.listen(5430)) {
            if (checkAccount()) {
                try {
                    if (!ListenerUtil.mutListener.listen(5429)) {
                        compositeDisposable.add(okHttpJsonApiClient.getLeaderboard(Objects.requireNonNull(userName), duration, category, null, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                            if (response != null && response.getStatus() == 200) {
                                userRank = response.getRank();
                                setViews(response, duration, category, limit, offset);
                            }
                        }, t -> {
                            Timber.e(t, "Fetching leaderboard statistics failed");
                            onError();
                        }));
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5428)) {
                        Timber.d(e + "success");
                    }
                }
            }
        }
    }

    /**
     * Set the views
     * @param response Leaderboard Response Object
     */
    private void setViews(LeaderboardResponse response, String duration, String category, int limit, int offset) {
        if (!ListenerUtil.mutListener.listen(5431)) {
            viewModel = new ViewModelProvider(this, viewModelFactory).get(LeaderboardListViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(5432)) {
            viewModel.setParams(duration, category, limit, offset);
        }
        LeaderboardListAdapter leaderboardListAdapter = new LeaderboardListAdapter();
        UserDetailAdapter userDetailAdapter = new UserDetailAdapter(response);
        MergeAdapter mergeAdapter = new MergeAdapter(userDetailAdapter, leaderboardListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        if (!ListenerUtil.mutListener.listen(5433)) {
            leaderboardListRecyclerView.setLayoutManager(linearLayoutManager);
        }
        if (!ListenerUtil.mutListener.listen(5434)) {
            leaderboardListRecyclerView.setAdapter(mergeAdapter);
        }
        if (!ListenerUtil.mutListener.listen(5435)) {
            viewModel.getListLiveData().observe(getViewLifecycleOwner(), leaderboardListAdapter::submitList);
        }
        if (!ListenerUtil.mutListener.listen(5436)) {
            viewModel.getProgressLoadStatus().observe(getViewLifecycleOwner(), status -> {
                if (Objects.requireNonNull(status).equalsIgnoreCase(LOADING)) {
                    showProgressBar();
                } else if (status.equalsIgnoreCase(LOADED)) {
                    hideProgressBar();
                    if (scrollToRank) {
                        leaderboardListRecyclerView.smoothScrollToPosition(userRank + 1);
                    }
                }
            });
        }
    }

    /**
     * to hide progressbar
     */
    private void hideProgressBar() {
        if (!ListenerUtil.mutListener.listen(5442)) {
            if (progressBar != null) {
                if (!ListenerUtil.mutListener.listen(5437)) {
                    progressBar.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(5438)) {
                    categorySpinner.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5439)) {
                    durationSpinner.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5440)) {
                    scrollButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5441)) {
                    leaderboardListRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * to show progressbar
     */
    private void showProgressBar() {
        if (!ListenerUtil.mutListener.listen(5444)) {
            if (progressBar != null) {
                if (!ListenerUtil.mutListener.listen(5443)) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5445)) {
            scrollButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * used to hide the layouts while fetching results from api
     */
    private void hideLayouts() {
        if (!ListenerUtil.mutListener.listen(5446)) {
            categorySpinner.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5447)) {
            durationSpinner.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5448)) {
            leaderboardListRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * check to ensure that user is logged in
     * @return
     */
    private boolean checkAccount() {
        Account currentAccount = sessionManager.getCurrentAccount();
        if (!ListenerUtil.mutListener.listen(5452)) {
            if (currentAccount == null) {
                if (!ListenerUtil.mutListener.listen(5449)) {
                    Timber.d("Current account is null");
                }
                if (!ListenerUtil.mutListener.listen(5450)) {
                    ViewUtil.showLongToast(getActivity(), getResources().getString(R.string.user_not_logged_in));
                }
                if (!ListenerUtil.mutListener.listen(5451)) {
                    sessionManager.forceLogin(getActivity());
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Shows a generic error toast when error occurs while loading leaderboard
     */
    private void onError() {
        if (!ListenerUtil.mutListener.listen(5453)) {
            ViewUtil.showLongToast(getActivity(), getResources().getString(R.string.error_occurred));
        }
        if (!ListenerUtil.mutListener.listen(5454)) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
