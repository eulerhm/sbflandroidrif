package org.wordpress.android.ui.stats.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.android.R
import org.wordpress.android.databinding.StatsRevampV2FeaturesIntroContentViewBinding
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.ui.featureintroduction.FeatureIntroductionDialogFragment
import org.wordpress.android.ui.stats.intro.StatsNewFeaturesIntroAction.DismissDialog
import org.wordpress.android.ui.stats.intro.StatsNewFeaturesIntroAction.OpenStats
import org.wordpress.android.util.extensions.exhaustive

@AndroidEntryPoint
class StatsNewFeaturesIntroDialogFragment : FeatureIntroductionDialogFragment() {
    private val viewModel: StatsNewFeatureIntroViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()
        initializeViews()
        setupObservers()
    }

    private fun initializeViews() {
        setHeaderIcon(R.drawable.ic_stats_alt_blue_gradient_wordpress_48dp)
        setHeaderTitle(R.string.stats_revamp_v2_intro_header_title)

        val contentBinding = StatsRevampV2FeaturesIntroContentViewBinding.inflate(layoutInflater)
        setContent(contentBinding.root)

        setPrimaryButtonText(R.string.stats_revamp_v2_intro_primary_button_text)
        setPrimaryButtonListener { viewModel.onPrimaryButtonClick() }

        setSecondaryButtonText(R.string.stats_revamp_v2_intro_secondary_button_text)
        setSecondaryButtonListener { viewModel.onSecondaryButtonClick() }

        setCloseButtonListener { viewModel.onCloseButtonClick() }
    }

    private fun setupObservers() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is OpenStats -> {
                    dismiss()
                    activity?.let {
                        ActivityLauncher.openBlogStats(it, action.site)
                    }
                }
                is DismissDialog -> {
                    dismiss()
                }
            }.exhaustive
        }
    }

    companion object {
        const val TAG = "STATS_NEW_FEATURES_INTRO_DIALOG_FRAGMENT"

        @JvmStatic
        fun newInstance(): StatsNewFeaturesIntroDialogFragment =
                StatsNewFeaturesIntroDialogFragment()
    }
}
