package org.wordpress.android.ui.prefs.notifications;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.store.AccountStore.UpdateSubscriptionPayload.SubscriptionFrequency;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A {@link DialogFragment} displaying notification settings for followed blogs.
 */
public class NotificationSettingsFollowedDialog extends DialogFragment implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String ARG_EMAIL_COMMENTS = "EXTRA_EMAIL_COMMENTS";

    public static final String ARG_EMAIL_POSTS = "EXTRA_EMAIL_POSTS";

    public static final String ARG_EMAIL_POSTS_FREQUENCY = "EXTRA_EMAIL_POSTS_FREQUENCY";

    public static final String ARG_NOTIFICATION_POSTS = "EXTRA_NOTIFICATION_POSTS";

    public static final String KEY_EMAIL_COMMENTS = "KEY_EMAIL_COMMENTS";

    public static final String KEY_EMAIL_POSTS = "KEY_EMAIL_POSTS";

    public static final String KEY_EMAIL_POSTS_FREQUENCY = "KEY_EMAIL_POSTS_FREQUENCY";

    public static final String KEY_NOTIFICATION_POSTS = "KEY_NOTIFICATION_POSTS";

    public static final String TAG = "notification-settings-followed-dialog";

    private static final String EMAIL_POSTS_FREQUENCY_DAILY = SubscriptionFrequency.DAILY.toString();

    private static final String EMAIL_POSTS_FREQUENCY_INSTANTLY = SubscriptionFrequency.INSTANTLY.toString();

    private static final String EMAIL_POSTS_FREQUENCY_WEEKLY = SubscriptionFrequency.WEEKLY.toString();

    private RadioButton mRadioButtonFrequencyDaily;

    private RadioButton mRadioButtonFrequencyInstantly;

    private RadioButton mRadioButtonFrequencyWeekly;

    private RadioGroup mRadioGroupEmailPosts;

    private String mRadioButtonSelected;

    private SwitchCompat mSwitchEmailComments;

    private SwitchCompat mSwitchEmailPosts;

    private SwitchCompat mSwitchNotificationPosts;

    private boolean mConfirmed;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams")
        View layout = inflater.inflate(R.layout.followed_sites_dialog, null);
        if (!ListenerUtil.mutListener.listen(13284)) {
            mRadioGroupEmailPosts = layout.findViewById(R.id.email_new_posts_radio_group);
        }
        if (!ListenerUtil.mutListener.listen(13285)) {
            mRadioButtonFrequencyInstantly = layout.findViewById(R.id.email_new_posts_radio_button_instantly);
        }
        if (!ListenerUtil.mutListener.listen(13286)) {
            mRadioButtonFrequencyDaily = layout.findViewById(R.id.email_new_posts_radio_button_daily);
        }
        if (!ListenerUtil.mutListener.listen(13287)) {
            mRadioButtonFrequencyWeekly = layout.findViewById(R.id.email_new_posts_radio_button_weekly);
        }
        if (!ListenerUtil.mutListener.listen(13288)) {
            mSwitchNotificationPosts = layout.findViewById(R.id.notification_new_posts_switch);
        }
        if (!ListenerUtil.mutListener.listen(13289)) {
            mSwitchEmailPosts = layout.findViewById(R.id.email_new_posts_switch);
        }
        if (!ListenerUtil.mutListener.listen(13290)) {
            mSwitchEmailComments = layout.findViewById(R.id.email_new_comments_switch);
        }
        if (!ListenerUtil.mutListener.listen(13291)) {
            mRadioButtonFrequencyInstantly.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13292)) {
            mRadioButtonFrequencyDaily.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13293)) {
            mRadioButtonFrequencyWeekly.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13294)) {
            mSwitchNotificationPosts.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13295)) {
            mSwitchEmailPosts.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13296)) {
            mSwitchEmailComments.setOnCheckedChangeListener(this);
        }
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(13305)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(13297)) {
                    mSwitchNotificationPosts.setChecked(args.getBoolean(ARG_NOTIFICATION_POSTS, false));
                }
                if (!ListenerUtil.mutListener.listen(13298)) {
                    mSwitchEmailPosts.setChecked(args.getBoolean(ARG_EMAIL_POSTS, false));
                }
                if (!ListenerUtil.mutListener.listen(13299)) {
                    mSwitchEmailComments.setChecked(args.getBoolean(ARG_EMAIL_COMMENTS, false));
                }
                if (!ListenerUtil.mutListener.listen(13300)) {
                    mRadioButtonSelected = args.getString(ARG_EMAIL_POSTS_FREQUENCY, "");
                }
                if (!ListenerUtil.mutListener.listen(13304)) {
                    if (mRadioButtonSelected.equalsIgnoreCase(EMAIL_POSTS_FREQUENCY_INSTANTLY)) {
                        if (!ListenerUtil.mutListener.listen(13303)) {
                            mRadioButtonFrequencyInstantly.setChecked(true);
                        }
                    } else if (mRadioButtonSelected.equalsIgnoreCase(EMAIL_POSTS_FREQUENCY_DAILY)) {
                        if (!ListenerUtil.mutListener.listen(13302)) {
                            mRadioButtonFrequencyDaily.setChecked(true);
                        }
                    } else if (mRadioButtonSelected.equalsIgnoreCase(EMAIL_POSTS_FREQUENCY_WEEKLY)) {
                        if (!ListenerUtil.mutListener.listen(13301)) {
                            mRadioButtonFrequencyWeekly.setChecked(true);
                        }
                    }
                }
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(13306)) {
            builder.setTitle(getString(R.string.notification_settings_followed_dialog_title));
        }
        if (!ListenerUtil.mutListener.listen(13307)) {
            builder.setPositiveButton(android.R.string.ok, this);
        }
        if (!ListenerUtil.mutListener.listen(13308)) {
            builder.setNegativeButton(R.string.cancel, this);
        }
        if (!ListenerUtil.mutListener.listen(13309)) {
            builder.setView(layout);
        }
        return builder.create();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(13314)) {
            if (isChecked) {
                if (!ListenerUtil.mutListener.listen(13313)) {
                    if (buttonView == mRadioButtonFrequencyInstantly) {
                        if (!ListenerUtil.mutListener.listen(13312)) {
                            mRadioButtonSelected = EMAIL_POSTS_FREQUENCY_INSTANTLY;
                        }
                    } else if (buttonView == mRadioButtonFrequencyDaily) {
                        if (!ListenerUtil.mutListener.listen(13311)) {
                            mRadioButtonSelected = EMAIL_POSTS_FREQUENCY_DAILY;
                        }
                    } else if (buttonView == mRadioButtonFrequencyWeekly) {
                        if (!ListenerUtil.mutListener.listen(13310)) {
                            mRadioButtonSelected = EMAIL_POSTS_FREQUENCY_WEEKLY;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13316)) {
            if (buttonView == mSwitchEmailPosts) {
                if (!ListenerUtil.mutListener.listen(13315)) {
                    toggleEmailFrequencyButtons(isChecked);
                }
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(13317)) {
            mConfirmed = which == DialogInterface.BUTTON_POSITIVE;
        }
        if (!ListenerUtil.mutListener.listen(13318)) {
            dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // See https://developer.android.com/reference/android/app/Fragment
        android.app.Fragment target = getTargetFragment();
        if (!ListenerUtil.mutListener.listen(13320)) {
            if (target != null) {
                if (!ListenerUtil.mutListener.listen(13319)) {
                    target.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getResultIntent());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13321)) {
            super.onDismiss(dialog);
        }
    }

    private Intent getResultIntent() {
        if (!ListenerUtil.mutListener.listen(13327)) {
            if (mConfirmed) {
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(13322)) {
                    intent.putExtra(KEY_NOTIFICATION_POSTS, mSwitchNotificationPosts.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(13323)) {
                    intent.putExtra(KEY_EMAIL_POSTS, mSwitchEmailPosts.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(13324)) {
                    intent.putExtra(KEY_EMAIL_COMMENTS, mSwitchEmailComments.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(13326)) {
                    if (mSwitchEmailPosts.isChecked()) {
                        if (!ListenerUtil.mutListener.listen(13325)) {
                            intent.putExtra(KEY_EMAIL_POSTS_FREQUENCY, mRadioButtonSelected);
                        }
                    }
                }
                return intent;
            }
        }
        return null;
    }

    private void toggleEmailFrequencyButtons(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(13328)) {
            mRadioGroupEmailPosts.setVisibility(enabled ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(13332)) {
            if ((ListenerUtil.mutListener.listen(13330) ? ((ListenerUtil.mutListener.listen(13329) ? (enabled || mRadioButtonSelected != null) : (enabled && mRadioButtonSelected != null)) || mRadioButtonSelected.equalsIgnoreCase("")) : ((ListenerUtil.mutListener.listen(13329) ? (enabled || mRadioButtonSelected != null) : (enabled && mRadioButtonSelected != null)) && mRadioButtonSelected.equalsIgnoreCase("")))) {
                if (!ListenerUtil.mutListener.listen(13331)) {
                    mRadioButtonFrequencyInstantly.setChecked(true);
                }
            }
        }
    }
}
