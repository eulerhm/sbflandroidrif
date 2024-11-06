package fr.free.nrw.commons;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import fr.free.nrw.commons.databinding.ActivityAboutBinding;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.DialogUtil;
import java.util.Collections;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents about screen of this app
 */
public class AboutActivity extends BaseActivity {

    /*
      This View Binding class is auto-generated for each xml file. The format is usually the name
      of the file with PascalCasing (The underscore characters will be ignored).
      More information is available at https://developer.android.com/topic/libraries/view-binding
     */
    private ActivityAboutBinding binding;

    /**
     * This method helps in the creation About screen
     *
     * @param savedInstanceState Data bundle
     */
    @Override
    @SuppressLint("StringFormatInvalid")
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9564)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9565)) {
            /*
          Instead of just setting the view with the xml file. We need to use View Binding class.
         */
            binding = ActivityAboutBinding.inflate(getLayoutInflater());
        }
        final View view = binding.getRoot();
        if (!ListenerUtil.mutListener.listen(9566)) {
            setContentView(view);
        }
        if (!ListenerUtil.mutListener.listen(9567)) {
            setSupportActionBar(binding.toolbarBinding.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(9568)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        final String aboutText = getString(R.string.about_license);
        if (!ListenerUtil.mutListener.listen(9569)) {
            /*
          We can then access all the views by just using the id names like this.
          camelCasing is used with underscore characters being ignored.
         */
            binding.aboutLicense.setHtmlText(aboutText);
        }
        @SuppressLint("StringFormatMatches")
        String improveText = String.format(getString(R.string.about_improve), Urls.NEW_ISSUE_URL);
        if (!ListenerUtil.mutListener.listen(9570)) {
            binding.aboutImprove.setHtmlText(improveText);
        }
        if (!ListenerUtil.mutListener.listen(9571)) {
            binding.aboutVersion.setText(ConfigUtils.getVersionNameWithSha(getApplicationContext()));
        }
        if (!ListenerUtil.mutListener.listen(9572)) {
            Utils.setUnderlinedText(binding.aboutFaq, R.string.about_faq, getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9573)) {
            Utils.setUnderlinedText(binding.aboutRateUs, R.string.about_rate_us, getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9574)) {
            Utils.setUnderlinedText(binding.aboutUserGuide, R.string.user_guide, getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9575)) {
            Utils.setUnderlinedText(binding.aboutPrivacyPolicy, R.string.about_privacy_policy, getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9576)) {
            Utils.setUnderlinedText(binding.aboutTranslate, R.string.about_translate, getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9577)) {
            Utils.setUnderlinedText(binding.aboutCredits, R.string.about_credits, getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9578)) {
            /*
          To set listeners, we can create a separate method and use lambda syntax.
        */
            binding.facebookLaunchIcon.setOnClickListener(this::launchFacebook);
        }
        if (!ListenerUtil.mutListener.listen(9579)) {
            binding.githubLaunchIcon.setOnClickListener(this::launchGithub);
        }
        if (!ListenerUtil.mutListener.listen(9580)) {
            binding.websiteLaunchIcon.setOnClickListener(this::launchWebsite);
        }
        if (!ListenerUtil.mutListener.listen(9581)) {
            binding.aboutRateUs.setOnClickListener(this::launchRatings);
        }
        if (!ListenerUtil.mutListener.listen(9582)) {
            binding.aboutCredits.setOnClickListener(this::launchCredits);
        }
        if (!ListenerUtil.mutListener.listen(9583)) {
            binding.aboutPrivacyPolicy.setOnClickListener(this::launchPrivacyPolicy);
        }
        if (!ListenerUtil.mutListener.listen(9584)) {
            binding.aboutUserGuide.setOnClickListener(this::launchUserGuide);
        }
        if (!ListenerUtil.mutListener.listen(9585)) {
            binding.aboutFaq.setOnClickListener(this::launchFrequentlyAskedQuesions);
        }
        if (!ListenerUtil.mutListener.listen(9586)) {
            binding.aboutTranslate.setOnClickListener(this::launchTranslate);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(9587)) {
            onBackPressed();
        }
        return true;
    }

    public void launchFacebook(View view) {
        Intent intent;
        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Urls.FACEBOOK_APP_URL));
            if (!ListenerUtil.mutListener.listen(9589)) {
                intent.setPackage(Urls.FACEBOOK_PACKAGE_NAME);
            }
            if (!ListenerUtil.mutListener.listen(9590)) {
                startActivity(intent);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(9588)) {
                Utils.handleWebUrl(this, Uri.parse(Urls.FACEBOOK_WEB_URL));
            }
        }
    }

    public void launchGithub(View view) {
        Intent intent;
        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Urls.GITHUB_REPO_URL));
            if (!ListenerUtil.mutListener.listen(9592)) {
                intent.setPackage(Urls.GITHUB_PACKAGE_NAME);
            }
            if (!ListenerUtil.mutListener.listen(9593)) {
                startActivity(intent);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(9591)) {
                Utils.handleWebUrl(this, Uri.parse(Urls.GITHUB_REPO_URL));
            }
        }
    }

    public void launchWebsite(View view) {
        if (!ListenerUtil.mutListener.listen(9594)) {
            Utils.handleWebUrl(this, Uri.parse(Urls.WEBSITE_URL));
        }
    }

    public void launchRatings(View view) {
        if (!ListenerUtil.mutListener.listen(9595)) {
            Utils.rateApp(this);
        }
    }

    public void launchCredits(View view) {
        if (!ListenerUtil.mutListener.listen(9596)) {
            Utils.handleWebUrl(this, Uri.parse(Urls.CREDITS_URL));
        }
    }

    public void launchUserGuide(View view) {
        if (!ListenerUtil.mutListener.listen(9597)) {
            Utils.handleWebUrl(this, Uri.parse(Urls.USER_GUIDE_URL));
        }
    }

    public void launchPrivacyPolicy(View view) {
        if (!ListenerUtil.mutListener.listen(9598)) {
            Utils.handleWebUrl(this, Uri.parse(BuildConfig.PRIVACY_POLICY_URL));
        }
    }

    public void launchFrequentlyAskedQuesions(View view) {
        if (!ListenerUtil.mutListener.listen(9599)) {
            Utils.handleWebUrl(this, Uri.parse(Urls.FAQ_URL));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(9600)) {
            inflater.inflate(R.menu.menu_about, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.share_app_icon:
                String shareText = String.format(getString(R.string.share_text), Urls.PLAY_STORE_URL_PREFIX + this.getPackageName());
                Intent sendIntent = new Intent();
                if (!ListenerUtil.mutListener.listen(9601)) {
                    sendIntent.setAction(Intent.ACTION_SEND);
                }
                if (!ListenerUtil.mutListener.listen(9602)) {
                    sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                }
                if (!ListenerUtil.mutListener.listen(9603)) {
                    sendIntent.setType("text/plain");
                }
                if (!ListenerUtil.mutListener.listen(9604)) {
                    startActivity(Intent.createChooser(sendIntent, getString(R.string.share_via)));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void launchTranslate(View view) {
        @NonNull
        List<String> sortedLocalizedNamesRef = CommonsApplication.getInstance().getLanguageLookUpTable().getCanonicalNames();
        if (!ListenerUtil.mutListener.listen(9605)) {
            Collections.sort(sortedLocalizedNamesRef);
        }
        final ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(AboutActivity.this, android.R.layout.simple_spinner_dropdown_item, sortedLocalizedNamesRef);
        final Spinner spinner = new Spinner(AboutActivity.this);
        if (!ListenerUtil.mutListener.listen(9606)) {
            spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(9607)) {
            spinner.setAdapter(languageAdapter);
        }
        if (!ListenerUtil.mutListener.listen(9608)) {
            spinner.setGravity(17);
        }
        if (!ListenerUtil.mutListener.listen(9609)) {
            spinner.setPadding(50, 0, 0, 0);
        }
        Runnable positiveButtonRunnable = () -> {
            String langCode = CommonsApplication.getInstance().getLanguageLookUpTable().getCodes().get(spinner.getSelectedItemPosition());
            Utils.handleWebUrl(AboutActivity.this, Uri.parse(Urls.TRANSLATE_WIKI_URL + langCode));
        };
        if (!ListenerUtil.mutListener.listen(9610)) {
            DialogUtil.showAlertDialog(this, getString(R.string.about_translate_title), getString(R.string.about_translate_message), getString(R.string.about_translate_proceed), getString(R.string.about_translate_cancel), positiveButtonRunnable, () -> {
            }, spinner, true);
        }
    }
}
