package fr.free.nrw.commons.navtab;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import fr.free.nrw.commons.AboutActivity;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.LoginActivity;
import fr.free.nrw.commons.databinding.FragmentMoreBottomSheetLoggedOutBinding;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.logging.CommonsLogSender;
import fr.free.nrw.commons.settings.SettingsActivity;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MoreBottomSheetLoggedOutFragment extends BottomSheetDialogFragment {

    private FragmentMoreBottomSheetLoggedOutBinding binding;

    @Inject
    CommonsLogSender commonsLogSender;

    @Inject
    @Named("default_preferences")
    JsonKvStore applicationKvStore;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5910)) {
            binding = FragmentMoreBottomSheetLoggedOutBinding.inflate(inflater, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5911)) {
            binding.moreLogin.setOnClickListener(v -> onLogoutClicked());
        }
        if (!ListenerUtil.mutListener.listen(5912)) {
            binding.moreFeedback.setOnClickListener(v -> onFeedbackClicked());
        }
        if (!ListenerUtil.mutListener.listen(5913)) {
            binding.moreAbout.setOnClickListener(v -> onAboutClicked());
        }
        if (!ListenerUtil.mutListener.listen(5914)) {
            binding.moreSettings.setOnClickListener(v -> onSettingsClicked());
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(5915)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(5916)) {
            binding = null;
        }
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        if (!ListenerUtil.mutListener.listen(5917)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(5918)) {
            ApplicationlessInjection.getInstance(requireActivity().getApplicationContext()).getCommonsApplicationComponent().inject(this);
        }
    }

    public void onLogoutClicked() {
        if (!ListenerUtil.mutListener.listen(5919)) {
            applicationKvStore.putBoolean("login_skipped", false);
        }
        final Intent intent = new Intent(getContext(), LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(5920)) {
            // Kill the activity from which you will go to next activity
            requireActivity().finish();
        }
        if (!ListenerUtil.mutListener.listen(5921)) {
            startActivity(intent);
        }
    }

    public void onFeedbackClicked() {
        if (!ListenerUtil.mutListener.listen(5922)) {
            showAlertDialog();
        }
    }

    /**
     * This method shows the alert dialog when a user wants to send feedback about the app.
     */
    private void showAlertDialog() {
        if (!ListenerUtil.mutListener.listen(5923)) {
            new AlertDialog.Builder(requireActivity()).setMessage(R.string.feedback_sharing_data_alert).setCancelable(false).setPositiveButton(R.string.ok, (dialog, which) -> {
                sendFeedback();
            }).show();
        }
    }

    /**
     * This method collects the feedback message and starts and activity with implicit intent to
     * available email client.
     */
    private void sendFeedback() {
        final String technicalInfo = commonsLogSender.getExtraInfo();
        final Intent feedbackIntent = new Intent(Intent.ACTION_SENDTO);
        if (!ListenerUtil.mutListener.listen(5924)) {
            feedbackIntent.setType("message/rfc822");
        }
        if (!ListenerUtil.mutListener.listen(5925)) {
            feedbackIntent.setData(Uri.parse("mailto:"));
        }
        if (!ListenerUtil.mutListener.listen(5926)) {
            feedbackIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { CommonsApplication.FEEDBACK_EMAIL });
        }
        if (!ListenerUtil.mutListener.listen(5927)) {
            feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, CommonsApplication.FEEDBACK_EMAIL_SUBJECT);
        }
        if (!ListenerUtil.mutListener.listen(5928)) {
            feedbackIntent.putExtra(Intent.EXTRA_TEXT, String.format("\n\n%s\n%s", CommonsApplication.FEEDBACK_EMAIL_TEMPLATE_HEADER, technicalInfo));
        }
        try {
            if (!ListenerUtil.mutListener.listen(5930)) {
                startActivity(feedbackIntent);
            }
        } catch (final ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(5929)) {
                Toast.makeText(getActivity(), R.string.no_email_client, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onAboutClicked() {
        final Intent intent = new Intent(getActivity(), AboutActivity.class);
        if (!ListenerUtil.mutListener.listen(5931)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(5932)) {
            requireActivity().startActivity(intent);
        }
    }

    public void onSettingsClicked() {
        final Intent intent = new Intent(getActivity(), SettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(5933)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(5934)) {
            requireActivity().startActivity(intent);
        }
    }

    private class BaseLogoutListener implements CommonsApplication.LogoutListener {

        @Override
        public void onLogoutComplete() {
            if (!ListenerUtil.mutListener.listen(5935)) {
                Timber.d("Logout complete callback received.");
            }
            final Intent nearbyIntent = new Intent(getContext(), LoginActivity.class);
            if (!ListenerUtil.mutListener.listen(5936)) {
                nearbyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (!ListenerUtil.mutListener.listen(5937)) {
                nearbyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(5938)) {
                startActivity(nearbyIntent);
            }
            if (!ListenerUtil.mutListener.listen(5939)) {
                requireActivity().finish();
            }
        }
    }
}
