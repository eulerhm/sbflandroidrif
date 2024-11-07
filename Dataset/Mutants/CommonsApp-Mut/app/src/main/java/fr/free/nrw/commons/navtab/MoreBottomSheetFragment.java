package fr.free.nrw.commons.navtab;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import fr.free.nrw.commons.AboutActivity;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.WelcomeActivity;
import fr.free.nrw.commons.actions.PageEditClient;
import fr.free.nrw.commons.auth.LoginActivity;
import fr.free.nrw.commons.databinding.FragmentMoreBottomSheetBinding;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import fr.free.nrw.commons.feedback.FeedbackContentCreator;
import fr.free.nrw.commons.feedback.model.Feedback;
import fr.free.nrw.commons.feedback.FeedbackDialog;
import fr.free.nrw.commons.kvstore.BasicKvStore;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.logging.CommonsLogSender;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.review.ReviewActivity;
import fr.free.nrw.commons.settings.SettingsActivity;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MoreBottomSheetFragment extends BottomSheetDialogFragment {

    @Inject
    CommonsLogSender commonsLogSender;

    private TextView moreProfile;

    @Inject
    @Named("default_preferences")
    JsonKvStore store;

    @Inject
    @Named("commons-page-edit")
    PageEditClient pageEditClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5942)) {
            super.onCreateView(inflater, container, savedInstanceState);
        }
        @NonNull
        final FragmentMoreBottomSheetBinding binding = FragmentMoreBottomSheetBinding.inflate(inflater, container, false);
        if (!ListenerUtil.mutListener.listen(5943)) {
            moreProfile = binding.moreProfile;
        }
        if (!ListenerUtil.mutListener.listen(5945)) {
            if (store.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED)) {
                if (!ListenerUtil.mutListener.listen(5944)) {
                    binding.morePeerReview.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5946)) {
            binding.moreLogout.setOnClickListener(v -> onLogoutClicked());
        }
        if (!ListenerUtil.mutListener.listen(5947)) {
            binding.moreFeedback.setOnClickListener(v -> onFeedbackClicked());
        }
        if (!ListenerUtil.mutListener.listen(5948)) {
            binding.moreAbout.setOnClickListener(v -> onAboutClicked());
        }
        if (!ListenerUtil.mutListener.listen(5949)) {
            binding.moreTutorial.setOnClickListener(v -> onTutorialClicked());
        }
        if (!ListenerUtil.mutListener.listen(5950)) {
            binding.moreSettings.setOnClickListener(v -> onSettingsClicked());
        }
        if (!ListenerUtil.mutListener.listen(5951)) {
            binding.moreProfile.setOnClickListener(v -> onProfileClicked());
        }
        if (!ListenerUtil.mutListener.listen(5952)) {
            binding.morePeerReview.setOnClickListener(v -> onPeerReviewClicked());
        }
        if (!ListenerUtil.mutListener.listen(5953)) {
            setUserName();
        }
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        if (!ListenerUtil.mutListener.listen(5954)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(5955)) {
            ApplicationlessInjection.getInstance(requireActivity().getApplicationContext()).getCommonsApplicationComponent().inject(this);
        }
    }

    /**
     * Set the username and user achievements level (if available) in navigationHeader.
     */
    private void setUserName() {
        BasicKvStore store = new BasicKvStore(this.getContext(), getUserName());
        String level = store.getString("userAchievementsLevel", "0");
        if (!ListenerUtil.mutListener.listen(5958)) {
            if (level.equals("0")) {
                if (!ListenerUtil.mutListener.listen(5957)) {
                    moreProfile.setText(getUserName() + " (" + getString(R.string.see_your_achievements) + ")");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5956)) {
                    moreProfile.setText(getUserName() + " (" + getString(R.string.level) + " " + level + ")");
                }
            }
        }
    }

    private String getUserName() {
        final AccountManager accountManager = AccountManager.get(getActivity());
        final Account[] allAccounts = accountManager.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
        if (!ListenerUtil.mutListener.listen(5959)) {
            if (allAccounts.length != 0) {
                return allAccounts[0].name;
            }
        }
        return "";
    }

    protected void onLogoutClicked() {
        if (!ListenerUtil.mutListener.listen(5960)) {
            new AlertDialog.Builder(requireActivity()).setMessage(R.string.logout_verification).setCancelable(false).setPositiveButton(R.string.yes, (dialog, which) -> {
                final CommonsApplication app = (CommonsApplication) requireContext().getApplicationContext();
                app.clearApplicationData(requireContext(), new BaseLogoutListener());
            }).setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel()).show();
        }
    }

    protected void onFeedbackClicked() {
        if (!ListenerUtil.mutListener.listen(5961)) {
            showFeedbackDialog();
        }
    }

    /**
     * Creates and shows a dialog asking feedback from users
     */
    private void showFeedbackDialog() {
        if (!ListenerUtil.mutListener.listen(5962)) {
            new FeedbackDialog(getContext(), this::uploadFeedback).show();
        }
    }

    /**
     * uploads feedback data on the server
     */
    void uploadFeedback(final Feedback feedback) {
        final FeedbackContentCreator feedbackContentCreator = new FeedbackContentCreator(getContext(), feedback);
        Single<Boolean> single = pageEditClient.prependEdit("Commons:Mobile_app/Feedback", feedbackContentCreator.toString(), "Summary").flatMapSingle(result -> Single.just(result)).firstOrError();
        if (!ListenerUtil.mutListener.listen(5963)) {
            Single.defer((Callable<SingleSource<Boolean>>) () -> single).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> {
                if (aBoolean) {
                    Toast.makeText(getContext(), getString(R.string.thanks_feedback), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_feedback), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * This method shows the alert dialog when a user wants to send feedback about the app.
     */
    private void showAlertDialog() {
        if (!ListenerUtil.mutListener.listen(5964)) {
            new AlertDialog.Builder(requireActivity()).setMessage(R.string.feedback_sharing_data_alert).setCancelable(false).setPositiveButton(R.string.ok, (dialog, which) -> sendFeedback()).show();
        }
    }

    /**
     * This method collects the feedback message and starts the activity with implicit intent
     * to available email client.
     */
    private void sendFeedback() {
        final String technicalInfo = commonsLogSender.getExtraInfo();
        final Intent feedbackIntent = new Intent(Intent.ACTION_SENDTO);
        if (!ListenerUtil.mutListener.listen(5965)) {
            feedbackIntent.setType("message/rfc822");
        }
        if (!ListenerUtil.mutListener.listen(5966)) {
            feedbackIntent.setData(Uri.parse("mailto:"));
        }
        if (!ListenerUtil.mutListener.listen(5967)) {
            feedbackIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { CommonsApplication.FEEDBACK_EMAIL });
        }
        if (!ListenerUtil.mutListener.listen(5968)) {
            feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, CommonsApplication.FEEDBACK_EMAIL_SUBJECT);
        }
        if (!ListenerUtil.mutListener.listen(5969)) {
            feedbackIntent.putExtra(Intent.EXTRA_TEXT, String.format("\n\n%s\n%s", CommonsApplication.FEEDBACK_EMAIL_TEMPLATE_HEADER, technicalInfo));
        }
        try {
            if (!ListenerUtil.mutListener.listen(5971)) {
                startActivity(feedbackIntent);
            }
        } catch (final ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(5970)) {
                Toast.makeText(getActivity(), R.string.no_email_client, Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onAboutClicked() {
        final Intent intent = new Intent(getActivity(), AboutActivity.class);
        if (!ListenerUtil.mutListener.listen(5972)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(5973)) {
            requireActivity().startActivity(intent);
        }
    }

    protected void onTutorialClicked() {
        if (!ListenerUtil.mutListener.listen(5974)) {
            WelcomeActivity.startYourself(getActivity());
        }
    }

    protected void onSettingsClicked() {
        final Intent intent = new Intent(getActivity(), SettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(5975)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(5976)) {
            requireActivity().startActivity(intent);
        }
    }

    protected void onProfileClicked() {
        if (!ListenerUtil.mutListener.listen(5977)) {
            ProfileActivity.startYourself(getActivity(), getUserName(), false);
        }
    }

    protected void onPeerReviewClicked() {
        if (!ListenerUtil.mutListener.listen(5978)) {
            ReviewActivity.startYourself(getActivity(), getString(R.string.title_activity_review));
        }
    }

    private class BaseLogoutListener implements CommonsApplication.LogoutListener {

        @Override
        public void onLogoutComplete() {
            if (!ListenerUtil.mutListener.listen(5979)) {
                Timber.d("Logout complete callback received.");
            }
            final Intent nearbyIntent = new Intent(getContext(), LoginActivity.class);
            if (!ListenerUtil.mutListener.listen(5980)) {
                nearbyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (!ListenerUtil.mutListener.listen(5981)) {
                nearbyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(5982)) {
                startActivity(nearbyIntent);
            }
            if (!ListenerUtil.mutListener.listen(5983)) {
                requireActivity().finish();
            }
        }
    }
}
