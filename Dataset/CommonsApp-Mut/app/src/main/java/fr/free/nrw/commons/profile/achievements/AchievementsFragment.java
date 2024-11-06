package fr.free.nrw.commons.profile.achievements;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.dinuscxj.progressbar.CircleProgressBar;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.databinding.FragmentAchievementsBinding;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.kvstore.BasicKvStore;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.ViewUtil;
import fr.free.nrw.commons.profile.ProfileActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Objects;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * fragment for sharing feedback on uploaded activity
 */
public class AchievementsFragment extends CommonsDaggerSupportFragment {

    private static final double BADGE_IMAGE_WIDTH_RATIO = 0.4;

    private static final double BADGE_IMAGE_HEIGHT_RATIO = 0.3;

    /**
     * Help link URLs
     */
    private static final String IMAGES_UPLOADED_URL = "https://commons.wikimedia.org/wiki/Commons:Project_scope";

    private static final String IMAGES_REVERT_URL = "https://commons.wikimedia.org/wiki/Commons:Deletion_policy#Reasons_for_deletion";

    private static final String IMAGES_USED_URL = "https://en.wikipedia.org/wiki/Wikipedia:Manual_of_Style/Images";

    private static final String IMAGES_NEARBY_PLACES_URL = "https://www.wikidata.org/wiki/Property:P18";

    private static final String IMAGES_FEATURED_URL = "https://commons.wikimedia.org/wiki/Commons:Featured_pictures";

    private static final String QUALITY_IMAGE_URL = "https://commons.wikimedia.org/wiki/Commons:Quality_images";

    private static final String THANKS_URL = "https://www.mediawiki.org/wiki/Extension:Thanks";

    private LevelController.LevelInfo levelInfo;

    @Inject
    SessionManager sessionManager;

    @Inject
    OkHttpJsonApiClient okHttpJsonApiClient;

    private FragmentAchievementsBinding binding;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // To keep track of the number of wiki edits made by a user
    private int numberOfEdits = 0;

    private String userName;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5202)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5204)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(5203)) {
                    userName = getArguments().getString(ProfileActivity.KEY_USERNAME);
                }
            }
        }
    }

    /**
     * This method helps in the creation Achievement screen and
     * dynamically set the size of imageView
     *
     * @param savedInstanceState Data bundle
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5205)) {
            binding = FragmentAchievementsBinding.inflate(inflater, container, false);
        }
        View rootView = binding.getRoot();
        if (!ListenerUtil.mutListener.listen(5206)) {
            binding.achievementInfo.setOnClickListener(view -> showInfoDialog());
        }
        if (!ListenerUtil.mutListener.listen(5207)) {
            binding.imagesUploadInfo.setOnClickListener(view -> showUploadInfo());
        }
        if (!ListenerUtil.mutListener.listen(5208)) {
            binding.imagesRevertedInfo.setOnClickListener(view -> showRevertedInfo());
        }
        if (!ListenerUtil.mutListener.listen(5209)) {
            binding.imagesUsedByWikiInfo.setOnClickListener(view -> showUsedByWikiInfo());
        }
        if (!ListenerUtil.mutListener.listen(5210)) {
            binding.imagesNearbyInfo.setOnClickListener(view -> showImagesViaNearbyInfo());
        }
        if (!ListenerUtil.mutListener.listen(5211)) {
            binding.imagesFeaturedInfo.setOnClickListener(view -> showFeaturedImagesInfo());
        }
        if (!ListenerUtil.mutListener.listen(5212)) {
            binding.thanksReceivedInfo.setOnClickListener(view -> showThanksReceivedInfo());
        }
        if (!ListenerUtil.mutListener.listen(5213)) {
            binding.qualityImagesInfo.setOnClickListener(view -> showQualityImagesInfo());
        }
        // DisplayMetrics used to fetch the size of the screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(5214)) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        // Used for the setting the size of imageView at runtime
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.achievementBadgeImage.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(5219)) {
            params.height = (int) ((ListenerUtil.mutListener.listen(5218) ? (height % BADGE_IMAGE_HEIGHT_RATIO) : (ListenerUtil.mutListener.listen(5217) ? (height / BADGE_IMAGE_HEIGHT_RATIO) : (ListenerUtil.mutListener.listen(5216) ? (height - BADGE_IMAGE_HEIGHT_RATIO) : (ListenerUtil.mutListener.listen(5215) ? (height + BADGE_IMAGE_HEIGHT_RATIO) : (height * BADGE_IMAGE_HEIGHT_RATIO))))));
        }
        if (!ListenerUtil.mutListener.listen(5224)) {
            params.width = (int) ((ListenerUtil.mutListener.listen(5223) ? (width % BADGE_IMAGE_WIDTH_RATIO) : (ListenerUtil.mutListener.listen(5222) ? (width / BADGE_IMAGE_WIDTH_RATIO) : (ListenerUtil.mutListener.listen(5221) ? (width - BADGE_IMAGE_WIDTH_RATIO) : (ListenerUtil.mutListener.listen(5220) ? (width + BADGE_IMAGE_WIDTH_RATIO) : (width * BADGE_IMAGE_WIDTH_RATIO))))));
        }
        if (!ListenerUtil.mutListener.listen(5225)) {
            binding.achievementBadgeImage.requestLayout();
        }
        if (!ListenerUtil.mutListener.listen(5226)) {
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5227)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(5228)) {
            // Set the initial value of WikiData edits to 0
            binding.wikidataEdits.setText("0");
        }
        if (!ListenerUtil.mutListener.listen(5233)) {
            if ((ListenerUtil.mutListener.listen(5229) ? (sessionManager.getUserName() == null && sessionManager.getUserName().equals(userName)) : (sessionManager.getUserName() == null || sessionManager.getUserName().equals(userName)))) {
                if (!ListenerUtil.mutListener.listen(5232)) {
                    binding.tvAchievementsOfUser.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5230)) {
                    binding.tvAchievementsOfUser.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5231)) {
                    binding.tvAchievementsOfUser.setText(getString(R.string.achievements_of_user, userName));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5243)) {
            // Achievements currently unimplemented in Beta flavor. Skip all API calls.
            if (ConfigUtils.isBetaFlavour()) {
                if (!ListenerUtil.mutListener.listen(5234)) {
                    binding.progressBar.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(5235)) {
                    binding.imagesUsedByWikiText.setText(R.string.no_image);
                }
                if (!ListenerUtil.mutListener.listen(5236)) {
                    binding.imagesRevertedText.setText(R.string.no_image_reverted);
                }
                if (!ListenerUtil.mutListener.listen(5237)) {
                    binding.imagesUploadTextParam.setText(R.string.no_image_uploaded);
                }
                if (!ListenerUtil.mutListener.listen(5238)) {
                    binding.wikidataEdits.setText("0");
                }
                if (!ListenerUtil.mutListener.listen(5239)) {
                    binding.imageFeatured.setText("0");
                }
                if (!ListenerUtil.mutListener.listen(5240)) {
                    binding.qualityImages.setText("0");
                }
                if (!ListenerUtil.mutListener.listen(5241)) {
                    binding.achievementLevel.setText("0");
                }
                if (!ListenerUtil.mutListener.listen(5242)) {
                    setMenuVisibility(true);
                }
                return rootView;
            }
        }
        if (!ListenerUtil.mutListener.listen(5244)) {
            setWikidataEditCount();
        }
        if (!ListenerUtil.mutListener.listen(5245)) {
            setAchievements();
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(5246)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(5247)) {
            super.onDestroyView();
        }
    }

    @Override
    public void setMenuVisibility(boolean visible) {
        if (!ListenerUtil.mutListener.listen(5248)) {
            super.setMenuVisibility(visible);
        }
        if (!ListenerUtil.mutListener.listen(5256)) {
            // notify Beta users the page data is unavailable
            if ((ListenerUtil.mutListener.listen(5249) ? (ConfigUtils.isBetaFlavour() || visible) : (ConfigUtils.isBetaFlavour() && visible))) {
                Context ctx = null;
                if (!ListenerUtil.mutListener.listen(5253)) {
                    if (getContext() != null) {
                        if (!ListenerUtil.mutListener.listen(5252)) {
                            ctx = getContext();
                        }
                    } else if ((ListenerUtil.mutListener.listen(5250) ? (getView() != null || getView().getContext() != null) : (getView() != null && getView().getContext() != null))) {
                        if (!ListenerUtil.mutListener.listen(5251)) {
                            ctx = getView().getContext();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5255)) {
                    if (ctx != null) {
                        if (!ListenerUtil.mutListener.listen(5254)) {
                            Toast.makeText(ctx, R.string.achievements_unavailable_beta, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
    }

    /**
     * To invoke the AlertDialog on clicking info button
     */
    protected void showInfoDialog() {
        if (!ListenerUtil.mutListener.listen(5257)) {
            launchAlert(getResources().getString(R.string.Achievements), getResources().getString(R.string.achievements_info_message));
        }
    }

    /**
     * To call the API to get results in form Single<JSONObject>
     * which then calls parseJson when results are fetched
     */
    private void setAchievements() {
        if (!ListenerUtil.mutListener.listen(5258)) {
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5261)) {
            if (checkAccount()) {
                try {
                    if (!ListenerUtil.mutListener.listen(5260)) {
                        compositeDisposable.add(okHttpJsonApiClient.getAchievements(Objects.requireNonNull(userName)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                            if (response != null) {
                                setUploadCount(Achievements.from(response));
                            } else {
                                Timber.d("success");
                                binding.layoutImageReverts.setVisibility(View.INVISIBLE);
                                binding.achievementBadgeImage.setVisibility(View.INVISIBLE);
                                // refer Issue: #3295
                                if (numberOfEdits <= 150000) {
                                    showSnackBarWithRetry(false);
                                } else {
                                    showSnackBarWithRetry(true);
                                }
                            }
                        }, t -> {
                            Timber.e(t, "Fetching achievements statistics failed");
                            if (numberOfEdits <= 150000) {
                                showSnackBarWithRetry(false);
                            } else {
                                showSnackBarWithRetry(true);
                            }
                        }));
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5259)) {
                        Timber.d(e + "success");
                    }
                }
            }
        }
    }

    /**
     * To call the API to fetch the count of wiki data edits
     *  in the form of JavaRx Single object<JSONobject>
     */
    private void setWikidataEditCount() {
        if (!ListenerUtil.mutListener.listen(5262)) {
            if (StringUtils.isBlank(userName)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5263)) {
            compositeDisposable.add(okHttpJsonApiClient.getWikidataEdits(userName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(edits -> {
                numberOfEdits = edits;
                binding.wikidataEdits.setText(String.valueOf(edits));
            }, e -> {
                Timber.e("Error:" + e);
            }));
        }
    }

    /**
     * Shows a snack bar which has an action button which on click dismisses the snackbar and invokes the
     * listener passed
     * @param tooManyAchievements if this value is true it means that the number of achievements of the
     * user are so high that it wrecks havoc with the Achievements calculator due to which request may time
     * out. Well this is the Ultimate Achievement
     */
    private void showSnackBarWithRetry(boolean tooManyAchievements) {
        if (!ListenerUtil.mutListener.listen(5268)) {
            if (tooManyAchievements) {
                if (!ListenerUtil.mutListener.listen(5266)) {
                    binding.progressBar.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(5267)) {
                    ViewUtil.showDismissibleSnackBar(getActivity().findViewById(android.R.id.content), R.string.achievements_fetch_failed_ultimate_achievement, R.string.retry, view -> setAchievements());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5264)) {
                    binding.progressBar.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(5265)) {
                    ViewUtil.showDismissibleSnackBar(getActivity().findViewById(android.R.id.content), R.string.achievements_fetch_failed, R.string.retry, view -> setAchievements());
                }
            }
        }
    }

    /**
     * Shows a generic error toast when error occurs while loading achievements or uploads
     */
    private void onError() {
        if (!ListenerUtil.mutListener.listen(5269)) {
            ViewUtil.showLongToast(getActivity(), getResources().getString(R.string.error_occurred));
        }
        if (!ListenerUtil.mutListener.listen(5270)) {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * used to the count of images uploaded by user
     */
    private void setUploadCount(Achievements achievements) {
        if (!ListenerUtil.mutListener.listen(5272)) {
            if (checkAccount()) {
                if (!ListenerUtil.mutListener.listen(5271)) {
                    compositeDisposable.add(okHttpJsonApiClient.getUploadCount(Objects.requireNonNull(userName)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(uploadCount -> setAchievementsUploadCount(achievements, uploadCount), t -> {
                        Timber.e(t, "Fetching upload count failed");
                        onError();
                    }));
                }
            }
        }
    }

    /**
     * used to set achievements upload count and call hideProgressbar
     * @param uploadCount
     */
    private void setAchievementsUploadCount(Achievements achievements, int uploadCount) {
        if (!ListenerUtil.mutListener.listen(5273)) {
            achievements.setImagesUploaded(uploadCount);
        }
        if (!ListenerUtil.mutListener.listen(5274)) {
            hideProgressBar(achievements);
        }
    }

    /**
     * used to the uploaded images progressbar
     * @param uploadCount
     */
    private void setUploadProgress(int uploadCount) {
        if (!ListenerUtil.mutListener.listen(5292)) {
            if ((ListenerUtil.mutListener.listen(5279) ? (uploadCount >= 0) : (ListenerUtil.mutListener.listen(5278) ? (uploadCount <= 0) : (ListenerUtil.mutListener.listen(5277) ? (uploadCount > 0) : (ListenerUtil.mutListener.listen(5276) ? (uploadCount < 0) : (ListenerUtil.mutListener.listen(5275) ? (uploadCount != 0) : (uploadCount == 0))))))) {
                if (!ListenerUtil.mutListener.listen(5291)) {
                    setZeroAchievements();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5280)) {
                    binding.imagesUploadedProgressbar.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5289)) {
                    binding.imagesUploadedProgressbar.setProgress((ListenerUtil.mutListener.listen(5288) ? ((ListenerUtil.mutListener.listen(5284) ? (100 % uploadCount) : (ListenerUtil.mutListener.listen(5283) ? (100 / uploadCount) : (ListenerUtil.mutListener.listen(5282) ? (100 - uploadCount) : (ListenerUtil.mutListener.listen(5281) ? (100 + uploadCount) : (100 * uploadCount))))) % levelInfo.getMaxUploadCount()) : (ListenerUtil.mutListener.listen(5287) ? ((ListenerUtil.mutListener.listen(5284) ? (100 % uploadCount) : (ListenerUtil.mutListener.listen(5283) ? (100 / uploadCount) : (ListenerUtil.mutListener.listen(5282) ? (100 - uploadCount) : (ListenerUtil.mutListener.listen(5281) ? (100 + uploadCount) : (100 * uploadCount))))) * levelInfo.getMaxUploadCount()) : (ListenerUtil.mutListener.listen(5286) ? ((ListenerUtil.mutListener.listen(5284) ? (100 % uploadCount) : (ListenerUtil.mutListener.listen(5283) ? (100 / uploadCount) : (ListenerUtil.mutListener.listen(5282) ? (100 - uploadCount) : (ListenerUtil.mutListener.listen(5281) ? (100 + uploadCount) : (100 * uploadCount))))) - levelInfo.getMaxUploadCount()) : (ListenerUtil.mutListener.listen(5285) ? ((ListenerUtil.mutListener.listen(5284) ? (100 % uploadCount) : (ListenerUtil.mutListener.listen(5283) ? (100 / uploadCount) : (ListenerUtil.mutListener.listen(5282) ? (100 - uploadCount) : (ListenerUtil.mutListener.listen(5281) ? (100 + uploadCount) : (100 * uploadCount))))) + levelInfo.getMaxUploadCount()) : ((ListenerUtil.mutListener.listen(5284) ? (100 % uploadCount) : (ListenerUtil.mutListener.listen(5283) ? (100 / uploadCount) : (ListenerUtil.mutListener.listen(5282) ? (100 - uploadCount) : (ListenerUtil.mutListener.listen(5281) ? (100 + uploadCount) : (100 * uploadCount))))) / levelInfo.getMaxUploadCount()))))));
                }
                if (!ListenerUtil.mutListener.listen(5290)) {
                    binding.tvUploadedImages.setText(uploadCount + "/" + levelInfo.getMaxUploadCount());
                }
            }
        }
    }

    private void setZeroAchievements() {
        String message = !Objects.equals(sessionManager.getUserName(), userName) ? getString(R.string.no_achievements_yet, userName) : getString(R.string.you_have_no_achievements_yet);
        if (!ListenerUtil.mutListener.listen(5293)) {
            DialogUtil.showAlertDialog(getActivity(), null, message, getString(R.string.ok), () -> {
            }, true);
        }
        if (!ListenerUtil.mutListener.listen(5294)) {
            binding.imagesUploadedProgressbar.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5295)) {
            binding.imageRevertsProgressbar.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5296)) {
            binding.imagesUsedByWikiProgressBar.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5297)) {
            binding.achievementBadgeImage.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5298)) {
            binding.imagesUsedByWikiText.setText(R.string.no_image);
        }
        if (!ListenerUtil.mutListener.listen(5299)) {
            binding.imagesRevertedText.setText(R.string.no_image_reverted);
        }
        if (!ListenerUtil.mutListener.listen(5300)) {
            binding.imagesUploadTextParam.setText(R.string.no_image_uploaded);
        }
        if (!ListenerUtil.mutListener.listen(5301)) {
            binding.achievementBadgeImage.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * used to set the non revert image percentage
     * @param notRevertPercentage
     */
    private void setImageRevertPercentage(int notRevertPercentage) {
        if (!ListenerUtil.mutListener.listen(5302)) {
            binding.imageRevertsProgressbar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5303)) {
            binding.imageRevertsProgressbar.setProgress(notRevertPercentage);
        }
        String revertPercentage = Integer.toString(notRevertPercentage);
        if (!ListenerUtil.mutListener.listen(5304)) {
            binding.imageRevertsProgressbar.setProgressTextFormatPattern(revertPercentage + "%%");
        }
        if (!ListenerUtil.mutListener.listen(5305)) {
            binding.imagesRevertLimitText.setText(getResources().getString(R.string.achievements_revert_limit_message) + levelInfo.getMinNonRevertPercentage() + "%");
        }
    }

    /**
     * Used the inflate the fetched statistics of the images uploaded by user
     * and assign badge and level. Also stores the achievements level of the user in BasicKvStore to display in menu
     * @param achievements
     */
    private void inflateAchievements(Achievements achievements) {
        if (!ListenerUtil.mutListener.listen(5306)) {
            binding.imagesUsedByWikiProgressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5307)) {
            binding.achievementLevel.setText(String.valueOf(achievements.getThanksReceived()));
        }
        if (!ListenerUtil.mutListener.listen(5316)) {
            binding.imagesUsedByWikiProgressBar.setProgress((ListenerUtil.mutListener.listen(5315) ? ((ListenerUtil.mutListener.listen(5311) ? (100 % achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5310) ? (100 / achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5309) ? (100 - achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5308) ? (100 + achievements.getUniqueUsedImages()) : (100 * achievements.getUniqueUsedImages()))))) % levelInfo.getMaxUniqueImages()) : (ListenerUtil.mutListener.listen(5314) ? ((ListenerUtil.mutListener.listen(5311) ? (100 % achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5310) ? (100 / achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5309) ? (100 - achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5308) ? (100 + achievements.getUniqueUsedImages()) : (100 * achievements.getUniqueUsedImages()))))) * levelInfo.getMaxUniqueImages()) : (ListenerUtil.mutListener.listen(5313) ? ((ListenerUtil.mutListener.listen(5311) ? (100 % achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5310) ? (100 / achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5309) ? (100 - achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5308) ? (100 + achievements.getUniqueUsedImages()) : (100 * achievements.getUniqueUsedImages()))))) - levelInfo.getMaxUniqueImages()) : (ListenerUtil.mutListener.listen(5312) ? ((ListenerUtil.mutListener.listen(5311) ? (100 % achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5310) ? (100 / achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5309) ? (100 - achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5308) ? (100 + achievements.getUniqueUsedImages()) : (100 * achievements.getUniqueUsedImages()))))) + levelInfo.getMaxUniqueImages()) : ((ListenerUtil.mutListener.listen(5311) ? (100 % achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5310) ? (100 / achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5309) ? (100 - achievements.getUniqueUsedImages()) : (ListenerUtil.mutListener.listen(5308) ? (100 + achievements.getUniqueUsedImages()) : (100 * achievements.getUniqueUsedImages()))))) / levelInfo.getMaxUniqueImages()))))));
        }
        if (!ListenerUtil.mutListener.listen(5318)) {
            if (binding.tvWikiPb != null) {
                if (!ListenerUtil.mutListener.listen(5317)) {
                    binding.tvWikiPb.setText(achievements.getUniqueUsedImages() + "/" + levelInfo.getMaxUniqueImages());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5319)) {
            binding.imageFeatured.setText(String.valueOf(achievements.getFeaturedImages()));
        }
        if (!ListenerUtil.mutListener.listen(5320)) {
            binding.qualityImages.setText(String.valueOf(achievements.getQualityImages()));
        }
        String levelUpInfoString = getString(R.string.level).toUpperCase();
        if (!ListenerUtil.mutListener.listen(5321)) {
            levelUpInfoString += " " + levelInfo.getLevelNumber();
        }
        if (!ListenerUtil.mutListener.listen(5322)) {
            binding.achievementLevel.setText(levelUpInfoString);
        }
        if (!ListenerUtil.mutListener.listen(5323)) {
            binding.achievementBadgeImage.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.badge, new ContextThemeWrapper(getActivity(), levelInfo.getLevelStyle()).getTheme()));
        }
        if (!ListenerUtil.mutListener.listen(5324)) {
            binding.achievementBadgeText.setText(Integer.toString(levelInfo.getLevelNumber()));
        }
        BasicKvStore store = new BasicKvStore(this.getContext(), userName);
        if (!ListenerUtil.mutListener.listen(5325)) {
            store.putString("userAchievementsLevel", Integer.toString(levelInfo.getLevelNumber()));
        }
    }

    /**
     * to hide progressbar
     */
    private void hideProgressBar(Achievements achievements) {
        if (!ListenerUtil.mutListener.listen(5331)) {
            if (binding.progressBar != null) {
                if (!ListenerUtil.mutListener.listen(5326)) {
                    levelInfo = LevelController.LevelInfo.from(achievements.getImagesUploaded(), achievements.getUniqueUsedImages(), achievements.getNotRevertPercentage());
                }
                if (!ListenerUtil.mutListener.listen(5327)) {
                    inflateAchievements(achievements);
                }
                if (!ListenerUtil.mutListener.listen(5328)) {
                    setUploadProgress(achievements.getImagesUploaded());
                }
                if (!ListenerUtil.mutListener.listen(5329)) {
                    setImageRevertPercentage(achievements.getNotRevertPercentage());
                }
                if (!ListenerUtil.mutListener.listen(5330)) {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void showUploadInfo() {
        if (!ListenerUtil.mutListener.listen(5332)) {
            launchAlertWithHelpLink(getResources().getString(R.string.images_uploaded), getResources().getString(R.string.images_uploaded_explanation), IMAGES_UPLOADED_URL);
        }
    }

    protected void showRevertedInfo() {
        if (!ListenerUtil.mutListener.listen(5333)) {
            launchAlertWithHelpLink(getResources().getString(R.string.image_reverts), getResources().getString(R.string.images_reverted_explanation), IMAGES_REVERT_URL);
        }
    }

    protected void showUsedByWikiInfo() {
        if (!ListenerUtil.mutListener.listen(5334)) {
            launchAlertWithHelpLink(getResources().getString(R.string.images_used_by_wiki), getResources().getString(R.string.images_used_explanation), IMAGES_USED_URL);
        }
    }

    protected void showImagesViaNearbyInfo() {
        if (!ListenerUtil.mutListener.listen(5335)) {
            launchAlertWithHelpLink(getResources().getString(R.string.statistics_wikidata_edits), getResources().getString(R.string.images_via_nearby_explanation), IMAGES_NEARBY_PLACES_URL);
        }
    }

    protected void showFeaturedImagesInfo() {
        if (!ListenerUtil.mutListener.listen(5336)) {
            launchAlertWithHelpLink(getResources().getString(R.string.statistics_featured), getResources().getString(R.string.images_featured_explanation), IMAGES_FEATURED_URL);
        }
    }

    protected void showThanksReceivedInfo() {
        if (!ListenerUtil.mutListener.listen(5337)) {
            launchAlertWithHelpLink(getResources().getString(R.string.statistics_thanks), getResources().getString(R.string.thanks_received_explanation), THANKS_URL);
        }
    }

    public void showQualityImagesInfo() {
        if (!ListenerUtil.mutListener.listen(5338)) {
            launchAlertWithHelpLink(getResources().getString(R.string.statistics_quality), getResources().getString(R.string.quality_images_info), QUALITY_IMAGE_URL);
        }
    }

    /**
     * takes title and message as input to display alerts
     * @param title
     * @param message
     */
    private void launchAlert(String title, String message) {
        if (!ListenerUtil.mutListener.listen(5339)) {
            DialogUtil.showAlertDialog(getActivity(), title, message, getString(R.string.ok), () -> {
            }, true);
        }
    }

    /**
     *  Launch Alert with a READ MORE button and clicking it open a custom webpage
     */
    private void launchAlertWithHelpLink(String title, String message, String helpLinkUrl) {
        if (!ListenerUtil.mutListener.listen(5340)) {
            DialogUtil.showAlertDialog(getActivity(), title, message, getString(R.string.ok), getString(R.string.read_help_link), () -> {
            }, () -> Utils.handleWebUrl(requireContext(), Uri.parse(helpLinkUrl)), null, true);
        }
    }

    /**
     * check to ensure that user is logged in
     * @return
     */
    private boolean checkAccount() {
        Account currentAccount = sessionManager.getCurrentAccount();
        if (!ListenerUtil.mutListener.listen(5344)) {
            if (currentAccount == null) {
                if (!ListenerUtil.mutListener.listen(5341)) {
                    Timber.d("Current account is null");
                }
                if (!ListenerUtil.mutListener.listen(5342)) {
                    ViewUtil.showLongToast(getActivity(), getResources().getString(R.string.user_not_logged_in));
                }
                if (!ListenerUtil.mutListener.listen(5343)) {
                    sessionManager.forceLogin(getActivity());
                }
                return false;
            }
        }
        return true;
    }
}
