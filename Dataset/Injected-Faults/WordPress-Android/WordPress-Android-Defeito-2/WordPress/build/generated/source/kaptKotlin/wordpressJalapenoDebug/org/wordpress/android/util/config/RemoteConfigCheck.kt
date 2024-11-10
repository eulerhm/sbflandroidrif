// Automatically generated file. DO NOT MODIFY
package org.wordpress.android.util.config

class RemoteConfigCheck(
    val appConfig: AppConfig
) {
    val bloggingPromptsFeatureConfig: BloggingPromptsFeatureConfig =
            BloggingPromptsFeatureConfig(appConfig)

    val bloggingRemindersFeatureConfig: BloggingRemindersFeatureConfig =
            BloggingRemindersFeatureConfig(appConfig)

    val commentsSnippetFeatureConfig: CommentsSnippetFeatureConfig =
            CommentsSnippetFeatureConfig(appConfig)

    val exampleRemoteFeature: ExampleRemoteFeature = ExampleRemoteFeature(appConfig)

    val likesEnhancementsFeatureConfig: LikesEnhancementsFeatureConfig =
            LikesEnhancementsFeatureConfig(appConfig)

    val mp4ComposerVideoOptimizationFeatureConfig: Mp4ComposerVideoOptimizationFeatureConfig =
            Mp4ComposerVideoOptimizationFeatureConfig(appConfig)

    val mySiteDashboardTabsFeatureConfig: MySiteDashboardTabsFeatureConfig =
            MySiteDashboardTabsFeatureConfig(appConfig)

    val mySiteDashboardTodaysStatsCardFeatureConfig: MySiteDashboardTodaysStatsCardFeatureConfig =
            MySiteDashboardTodaysStatsCardFeatureConfig(appConfig)

    val mySiteDefaultTabExperimentFeatureConfig: MySiteDefaultTabExperimentFeatureConfig =
            MySiteDefaultTabExperimentFeatureConfig(appConfig)

    val mySiteDefaultTabExperimentVariationDashboardFeatureConfig:
            MySiteDefaultTabExperimentVariationDashboardFeatureConfig =
            MySiteDefaultTabExperimentVariationDashboardFeatureConfig(appConfig)

    val qRCodeAuthFlowFeatureConfig: QRCodeAuthFlowFeatureConfig =
            QRCodeAuthFlowFeatureConfig(appConfig)

    val quickStartExistingUsersV2FeatureConfig: QuickStartExistingUsersV2FeatureConfig =
            QuickStartExistingUsersV2FeatureConfig(appConfig)

    val readerCommentsModerationFeatureConfig: ReaderCommentsModerationFeatureConfig =
            ReaderCommentsModerationFeatureConfig(appConfig)

    val recommendTheAppFeatureConfig: RecommendTheAppFeatureConfig =
            RecommendTheAppFeatureConfig(appConfig)

    val seenUnseenWithCounterFeatureConfig: SeenUnseenWithCounterFeatureConfig =
            SeenUnseenWithCounterFeatureConfig(appConfig)

    val siteDomainsFeatureConfig: SiteDomainsFeatureConfig = SiteDomainsFeatureConfig(appConfig)

    val statsRevampV2FeatureConfig: StatsRevampV2FeatureConfig =
            StatsRevampV2FeatureConfig(appConfig)

    val unifiedAboutFeatureConfig: UnifiedAboutFeatureConfig = UnifiedAboutFeatureConfig(appConfig)

    val unifiedCommentsCommentEditFeatureConfig: UnifiedCommentsCommentEditFeatureConfig =
            UnifiedCommentsCommentEditFeatureConfig(appConfig)

    val unifiedCommentsDetailFeatureConfig: UnifiedCommentsDetailFeatureConfig =
            UnifiedCommentsDetailFeatureConfig(appConfig)

    fun checkRemoteFields() {
        if (bloggingPromptsFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.BloggingPromptsFeatureConfig
                needs to define remoteField""")
        }
        if (bloggingRemindersFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.BloggingRemindersFeatureConfig
                needs to define remoteField""")
        }
        if (commentsSnippetFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.CommentsSnippetFeatureConfig
                needs to define remoteField""")
        }
        if (exampleRemoteFeature.remoteField == null) {
            throw IllegalArgumentException("""org.wordpress.android.util.config.ExampleRemoteFeature
                needs to define remoteField""")
        }
        if (likesEnhancementsFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.LikesEnhancementsFeatureConfig
                needs to define remoteField""")
        }
        if (mp4ComposerVideoOptimizationFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.Mp4ComposerVideoOptimizationFeatureConfig
                needs to define remoteField""")
        }
        if (mySiteDashboardTabsFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig
                needs to define remoteField""")
        }
        if (mySiteDashboardTodaysStatsCardFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig
                needs to define remoteField""")
        }
        if (mySiteDefaultTabExperimentFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.MySiteDefaultTabExperimentFeatureConfig
                needs to define remoteField""")
        }
        if (mySiteDefaultTabExperimentVariationDashboardFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.MySiteDefaultTabExperimentVariationDashboardFeatureConfig
                needs to define remoteField""")
        }
        if (qRCodeAuthFlowFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.QRCodeAuthFlowFeatureConfig
                needs to define remoteField""")
        }
        if (quickStartExistingUsersV2FeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.QuickStartExistingUsersV2FeatureConfig
                needs to define remoteField""")
        }
        if (readerCommentsModerationFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.ReaderCommentsModerationFeatureConfig
                needs to define remoteField""")
        }
        if (recommendTheAppFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.RecommendTheAppFeatureConfig
                needs to define remoteField""")
        }
        if (seenUnseenWithCounterFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig
                needs to define remoteField""")
        }
        if (siteDomainsFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.SiteDomainsFeatureConfig
                needs to define remoteField""")
        }
        if (statsRevampV2FeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.StatsRevampV2FeatureConfig
                needs to define remoteField""")
        }
        if (unifiedAboutFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.UnifiedAboutFeatureConfig
                needs to define remoteField""")
        }
        if (unifiedCommentsCommentEditFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.UnifiedCommentsCommentEditFeatureConfig
                needs to define remoteField""")
        }
        if (unifiedCommentsDetailFeatureConfig.remoteField == null) {
            throw
                IllegalArgumentException("""org.wordpress.android.util.config.UnifiedCommentsDetailFeatureConfig
                needs to define remoteField""")
        }
    }
}
