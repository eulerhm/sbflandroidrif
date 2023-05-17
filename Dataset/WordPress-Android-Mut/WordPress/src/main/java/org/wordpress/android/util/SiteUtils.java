package org.wordpress.android.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.DesignateMobileEditorForAllSitesPayload;
import org.wordpress.android.fluxc.store.SiteStore.DesignateMobileEditorPayload;
import org.wordpress.android.fluxc.store.SiteStore.FetchSitesPayload;
import org.wordpress.android.fluxc.store.SiteStore.SiteFilter;
import org.wordpress.android.ui.plans.PlansConstants;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.utils.SiteAccessibilityInfo;
import org.wordpress.android.ui.reader.utils.SiteVisibility;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.BlockEditorEnabledSource;
import org.wordpress.android.util.image.BlavatarShape;
import org.wordpress.android.util.image.ImageType;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SiteUtils {

    public static final String GB_EDITOR_NAME = "gutenberg";

    public static final String AZTEC_EDITOR_NAME = "aztec";

    public static final String WP_STORIES_CREATOR_NAME = "wp_stories_creator";

    public static final String WP_STORIES_JETPACK_VERSION = "9.1";

    public static final String WP_CONTACT_INFO_JETPACK_VERSION = "8.5";

    public static final String WP_FACEBOOK_EMBED_JETPACK_VERSION = "9.0";

    public static final String WP_INSTAGRAM_EMBED_JETPACK_VERSION = "9.0";

    public static final String WP_LOOM_EMBED_JETPACK_VERSION = "9.0";

    public static final String WP_SMARTFRAME_EMBED_JETPACK_VERSION = "10.2";

    private static final int GB_ROLLOUT_PERCENTAGE_PHASE_1 = 100;

    private static final int GB_ROLLOUT_PERCENTAGE_PHASE_2 = 100;

    /**
     * Migrate the old app-wide editor preference value to per-site setting. wpcom sites will make a network call
     * and store the value on the backend. selfHosted sites just store the value in the local DB in FluxC
     * <p>
     * Strategy: Check if there is the old app-wide preference still available (v12.9 and before used it).
     * -- 12.9 ON -> turn all sites ON in 13.0
     * -- 12.9 OPTED OUT (were auto-opted in but turned it OFF) -> turn all sites OFF in 13.0
     */
    public static void migrateAppWideMobileEditorPreferenceToRemote(final AccountStore accountStore, final SiteStore siteStore, final Dispatcher dispatcher) {
        if (!ListenerUtil.mutListener.listen(27802)) {
            // Skip if the user is not signed in
            if (!FluxCUtils.isSignedInWPComOrHasWPOrgSite(accountStore, siteStore)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27803)) {
            // If the user is already in the rollout group, we can skip this the migration.
            if (AppPrefs.isUserInGutenbergRolloutGroup()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27828)) {
            // If user has any Aztec enabled sites, we'll migrate their sites to Gutenberg and schedule showing popup.
            if (atLeastOneSiteHasAztecEnabled(siteStore)) {
                if (!ListenerUtil.mutListener.listen(27827)) {
                    // Randomly pick the user in the rollout group
                    if ((ListenerUtil.mutListener.listen(27816) ? ((ListenerUtil.mutListener.listen(27807) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27806) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27805) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27804) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) <= ((ListenerUtil.mutListener.listen(27811) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27810) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27809) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27808) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_2) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_2))))))) : (ListenerUtil.mutListener.listen(27815) ? ((ListenerUtil.mutListener.listen(27807) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27806) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27805) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27804) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) > ((ListenerUtil.mutListener.listen(27811) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27810) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27809) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27808) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_2) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_2))))))) : (ListenerUtil.mutListener.listen(27814) ? ((ListenerUtil.mutListener.listen(27807) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27806) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27805) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27804) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) < ((ListenerUtil.mutListener.listen(27811) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27810) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27809) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27808) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_2) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_2))))))) : (ListenerUtil.mutListener.listen(27813) ? ((ListenerUtil.mutListener.listen(27807) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27806) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27805) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27804) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) != ((ListenerUtil.mutListener.listen(27811) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27810) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27809) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27808) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_2) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_2))))))) : (ListenerUtil.mutListener.listen(27812) ? ((ListenerUtil.mutListener.listen(27807) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27806) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27805) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27804) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) == ((ListenerUtil.mutListener.listen(27811) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27810) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27809) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27808) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_2) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_2))))))) : ((ListenerUtil.mutListener.listen(27807) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27806) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27805) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27804) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) >= ((ListenerUtil.mutListener.listen(27811) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27810) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27809) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_2) : (ListenerUtil.mutListener.listen(27808) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_2) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_2))))))))))))) {
                        if (!ListenerUtil.mutListener.listen(27817)) {
                            if (!NetworkUtils.isNetworkAvailable(WordPress.getContext())) {
                                // If the network is not available, abort. We can't update the remote setting.
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(27823)) {
                            {
                                long _loopCounter412 = 0;
                                for (SiteModel site : siteStore.getSites()) {
                                    ListenerUtil.loopListener.listen("_loopCounter412", ++_loopCounter412);
                                    if (!ListenerUtil.mutListener.listen(27820)) {
                                        // Show "phase 2" dialog on sites that get switched from aztec to gutenberg
                                        if (TextUtils.equals(site.getMobileEditor(), AZTEC_EDITOR_NAME)) {
                                            if (!ListenerUtil.mutListener.listen(27818)) {
                                                AppPrefs.setShowGutenbergInfoPopupPhase2ForNewPosts(site.getUrl(), true);
                                            }
                                            if (!ListenerUtil.mutListener.listen(27819)) {
                                                // Will show the popup again, even if it was displayed in the past
                                                AppPrefs.setGutenbergInfoPopupDisplayed(site.getUrl(), false);
                                            }
                                            continue;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(27822)) {
                                        // Show "phase 1" dialog on sites that get switched from "empty" (no pref) to gutenberg
                                        if (TextUtils.isEmpty(site.getMobileEditor())) {
                                            if (!ListenerUtil.mutListener.listen(27821)) {
                                                AppPrefs.setShowGutenbergInfoPopupForTheNewPosts(site.getUrl(), true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(27824)) {
                            // still on Aztec.
                            trackGutenbergEnabledForNonGutenbergSites(siteStore, BlockEditorEnabledSource.ON_PROGRESSIVE_ROLLOUT_PHASE_2);
                        }
                        if (!ListenerUtil.mutListener.listen(27825)) {
                            dispatcher.dispatch(SiteActionBuilder.newDesignateMobileEditorForAllSitesAction(new DesignateMobileEditorForAllSitesPayload(SiteUtils.GB_EDITOR_NAME, false)));
                        }
                        if (!ListenerUtil.mutListener.listen(27826)) {
                            // After enabling Gutenberg on these sites, we consider the user entered the rollout group
                            AppPrefs.setUserInGutenbergRolloutGroup();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27850)) {
            // we'll use a test like `id % 100 >= 90` instead of `id % 100 < 10`.
            if ((ListenerUtil.mutListener.listen(27841) ? ((ListenerUtil.mutListener.listen(27832) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27831) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27830) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27829) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) <= ((ListenerUtil.mutListener.listen(27836) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27835) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27834) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27833) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_1) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_1))))))) : (ListenerUtil.mutListener.listen(27840) ? ((ListenerUtil.mutListener.listen(27832) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27831) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27830) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27829) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) > ((ListenerUtil.mutListener.listen(27836) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27835) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27834) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27833) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_1) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_1))))))) : (ListenerUtil.mutListener.listen(27839) ? ((ListenerUtil.mutListener.listen(27832) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27831) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27830) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27829) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) < ((ListenerUtil.mutListener.listen(27836) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27835) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27834) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27833) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_1) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_1))))))) : (ListenerUtil.mutListener.listen(27838) ? ((ListenerUtil.mutListener.listen(27832) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27831) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27830) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27829) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) != ((ListenerUtil.mutListener.listen(27836) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27835) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27834) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27833) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_1) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_1))))))) : (ListenerUtil.mutListener.listen(27837) ? ((ListenerUtil.mutListener.listen(27832) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27831) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27830) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27829) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) == ((ListenerUtil.mutListener.listen(27836) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27835) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27834) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27833) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_1) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_1))))))) : ((ListenerUtil.mutListener.listen(27832) ? (accountStore.getAccount().getUserId() / 100) : (ListenerUtil.mutListener.listen(27831) ? (accountStore.getAccount().getUserId() * 100) : (ListenerUtil.mutListener.listen(27830) ? (accountStore.getAccount().getUserId() - 100) : (ListenerUtil.mutListener.listen(27829) ? (accountStore.getAccount().getUserId() + 100) : (accountStore.getAccount().getUserId() % 100))))) >= ((ListenerUtil.mutListener.listen(27836) ? (100 % GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27835) ? (100 / GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27834) ? (100 * GB_ROLLOUT_PERCENTAGE_PHASE_1) : (ListenerUtil.mutListener.listen(27833) ? (100 + GB_ROLLOUT_PERCENTAGE_PHASE_1) : (100 - GB_ROLLOUT_PERCENTAGE_PHASE_1))))))))))))) {
                if (!ListenerUtil.mutListener.listen(27842)) {
                    if (atLeastOneSiteHasAztecEnabled(siteStore)) {
                        // If the user has opt-ed out from at least one of their site, then exclude them from the cohort
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(27843)) {
                    if (!NetworkUtils.isNetworkAvailable(WordPress.getContext())) {
                        // If the network is not available, abort. We can't update the remote setting.
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(27846)) {
                    {
                        long _loopCounter413 = 0;
                        // Force the dialog to be shown on updated sites
                        for (SiteModel site : siteStore.getSites()) {
                            ListenerUtil.loopListener.listen("_loopCounter413", ++_loopCounter413);
                            if (!ListenerUtil.mutListener.listen(27845)) {
                                if (TextUtils.isEmpty(site.getMobileEditor())) {
                                    if (!ListenerUtil.mutListener.listen(27844)) {
                                        AppPrefs.setShowGutenbergInfoPopupForTheNewPosts(site.getUrl(), true);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27847)) {
                    // still on Aztec.
                    trackGutenbergEnabledForNonGutenbergSites(siteStore, BlockEditorEnabledSource.ON_PROGRESSIVE_ROLLOUT_PHASE_1);
                }
                if (!ListenerUtil.mutListener.listen(27848)) {
                    dispatcher.dispatch(SiteActionBuilder.newDesignateMobileEditorForAllSitesAction(new DesignateMobileEditorForAllSitesPayload(SiteUtils.GB_EDITOR_NAME)));
                }
                if (!ListenerUtil.mutListener.listen(27849)) {
                    // After enabling Gutenberg on these sites, we consider the user entered the rollout group
                    AppPrefs.setUserInGutenbergRolloutGroup();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27851)) {
            if (!AppPrefs.isDefaultAppWideEditorPreferenceSet()) {
                return;
            }
        }
        final boolean oldAppWidePreferenceValue = AppPrefs.isGutenbergDefaultForNewPosts();
        if (!ListenerUtil.mutListener.listen(27854)) {
            if (oldAppWidePreferenceValue) {
                if (!ListenerUtil.mutListener.listen(27853)) {
                    dispatcher.dispatch(SiteActionBuilder.newDesignateMobileEditorForAllSitesAction(new DesignateMobileEditorForAllSitesPayload(SiteUtils.GB_EDITOR_NAME)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27852)) {
                    dispatcher.dispatch(SiteActionBuilder.newDesignateMobileEditorForAllSitesAction(new DesignateMobileEditorForAllSitesPayload(SiteUtils.AZTEC_EDITOR_NAME)));
                }
            }
        }
    }

    private static void trackGutenbergEnabledForNonGutenbergSites(final SiteStore siteStore, final BlockEditorEnabledSource source) {
        if (!ListenerUtil.mutListener.listen(27857)) {
            {
                long _loopCounter414 = 0;
                for (SiteModel site : siteStore.getSites()) {
                    ListenerUtil.loopListener.listen("_loopCounter414", ++_loopCounter414);
                    if (!ListenerUtil.mutListener.listen(27856)) {
                        if (!TextUtils.equals(site.getMobileEditor(), GB_EDITOR_NAME)) {
                            if (!ListenerUtil.mutListener.listen(27855)) {
                                AnalyticsUtils.trackWithSiteDetails(Stat.EDITOR_GUTENBERG_ENABLED, site, source.asPropertyMap());
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean atLeastOneSiteHasAztecEnabled(final SiteStore siteStore) {
        if (!ListenerUtil.mutListener.listen(27859)) {
            {
                long _loopCounter415 = 0;
                // We want to make sure to enable Gutenberg only on the sites they didn't opt-out.
                for (SiteModel site : siteStore.getSites()) {
                    ListenerUtil.loopListener.listen("_loopCounter415", ++_loopCounter415);
                    if (!ListenerUtil.mutListener.listen(27858)) {
                        if (TextUtils.equals(site.getMobileEditor(), AZTEC_EDITOR_NAME)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean enableBlockEditorOnSiteCreation(Dispatcher dispatcher, SiteStore siteStore, int siteLocalSiteID) {
        SiteModel newSiteModel = siteStore.getSiteByLocalId(siteLocalSiteID);
        if (!ListenerUtil.mutListener.listen(27862)) {
            if (newSiteModel != null) {
                if (!ListenerUtil.mutListener.listen(27860)) {
                    enableBlockEditor(dispatcher, newSiteModel);
                }
                if (!ListenerUtil.mutListener.listen(27861)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.EDITOR_GUTENBERG_ENABLED, newSiteModel, BlockEditorEnabledSource.ON_SITE_CREATION.asPropertyMap());
                }
                return true;
            }
        }
        return false;
    }

    public static void enableBlockEditor(Dispatcher dispatcher, SiteModel siteModel) {
        if (!ListenerUtil.mutListener.listen(27863)) {
            dispatcher.dispatch(SiteActionBuilder.newDesignateMobileEditorAction(new DesignateMobileEditorPayload(siteModel, GB_EDITOR_NAME)));
        }
    }

    public static void disableBlockEditor(Dispatcher dispatcher, SiteModel siteModel) {
        if (!ListenerUtil.mutListener.listen(27864)) {
            dispatcher.dispatch(SiteActionBuilder.newDesignateMobileEditorAction(new DesignateMobileEditorPayload(siteModel, AZTEC_EDITOR_NAME)));
        }
    }

    public static boolean isBlockEditorDefaultForNewPost(@Nullable SiteModel site) {
        if (site == null) {
            return true;
        }
        if (TextUtils.isEmpty(site.getMobileEditor())) {
            // Default to block editor when mobile editor setting is empty
            return true;
        } else {
            return (ListenerUtil.mutListener.listen(27865) ? (alwaysDefaultToGutenberg(site) && site.getMobileEditor().equals(SiteUtils.GB_EDITOR_NAME)) : (alwaysDefaultToGutenberg(site) || site.getMobileEditor().equals(SiteUtils.GB_EDITOR_NAME)));
        }
    }

    public static boolean alwaysDefaultToGutenberg(SiteModel site) {
        return (ListenerUtil.mutListener.listen(27866) ? (site.isWPCom() || !site.isWPComAtomic()) : (site.isWPCom() && !site.isWPComAtomic()));
    }

    public static String getSiteNameOrHomeURL(SiteModel site) {
        String siteName = site.getName();
        if (!ListenerUtil.mutListener.listen(27867)) {
            if (siteName == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(27874)) {
            if ((ListenerUtil.mutListener.listen(27872) ? (siteName.trim().length() >= 0) : (ListenerUtil.mutListener.listen(27871) ? (siteName.trim().length() <= 0) : (ListenerUtil.mutListener.listen(27870) ? (siteName.trim().length() > 0) : (ListenerUtil.mutListener.listen(27869) ? (siteName.trim().length() < 0) : (ListenerUtil.mutListener.listen(27868) ? (siteName.trim().length() != 0) : (siteName.trim().length() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(27873)) {
                    siteName = getHomeURLOrHostName(site);
                }
            }
        }
        return siteName;
    }

    public static String getHomeURLOrHostName(SiteModel site) {
        String homeURL = UrlUtils.removeScheme(site.getUrl());
        if (!ListenerUtil.mutListener.listen(27875)) {
            homeURL = StringUtils.removeTrailingSlash(homeURL);
        }
        if (!ListenerUtil.mutListener.listen(27876)) {
            if (TextUtils.isEmpty(homeURL)) {
                return UrlUtils.getHost(site.getXmlRpcUrl());
            }
        }
        return homeURL;
    }

    /**
     * @return true if the site is WPCom or Jetpack and is not private
     */
    public static boolean isPhotonCapable(SiteModel site) {
        return (ListenerUtil.mutListener.listen(27878) ? (SiteUtils.isAccessedViaWPComRest(site) || ((ListenerUtil.mutListener.listen(27877) ? (!site.isPrivate() && site.isWPComAtomic()) : (!site.isPrivate() || site.isWPComAtomic())))) : (SiteUtils.isAccessedViaWPComRest(site) && ((ListenerUtil.mutListener.listen(27877) ? (!site.isPrivate() && site.isWPComAtomic()) : (!site.isPrivate() || site.isWPComAtomic())))));
    }

    public static boolean isAccessedViaWPComRest(@NonNull SiteModel site) {
        return site.getOrigin() == SiteModel.ORIGIN_WPCOM_REST;
    }

    public static String getSiteIconUrl(SiteModel site, int size) {
        return PhotonUtils.getPhotonImageUrl(site.getIconUrl(), size, size, PhotonUtils.Quality.HIGH, site.isPrivateWPComAtomic());
    }

    public static ImageType getSiteImageType(boolean isP2, BlavatarShape shape) {
        ImageType type = ImageType.BLAVATAR;
        if (!ListenerUtil.mutListener.listen(27887)) {
            if (isP2) {
                if (!ListenerUtil.mutListener.listen(27886)) {
                    switch(shape) {
                        case SQUARE:
                            if (!ListenerUtil.mutListener.listen(27883)) {
                                type = ImageType.P2_BLAVATAR;
                            }
                            break;
                        case SQUARE_WITH_ROUNDED_CORNERES:
                            if (!ListenerUtil.mutListener.listen(27884)) {
                                type = ImageType.P2_BLAVATAR_ROUNDED_CORNERS;
                            }
                            break;
                        case CIRCULAR:
                            if (!ListenerUtil.mutListener.listen(27885)) {
                                type = ImageType.P2_BLAVATAR_CIRCULAR;
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27882)) {
                    switch(shape) {
                        case SQUARE:
                            if (!ListenerUtil.mutListener.listen(27879)) {
                                type = ImageType.BLAVATAR;
                            }
                            break;
                        case SQUARE_WITH_ROUNDED_CORNERES:
                            if (!ListenerUtil.mutListener.listen(27880)) {
                                type = ImageType.BLAVATAR_ROUNDED_CORNERS;
                            }
                            break;
                        case CIRCULAR:
                            if (!ListenerUtil.mutListener.listen(27881)) {
                                type = ImageType.BLAVATAR_CIRCULAR;
                            }
                            break;
                    }
                }
            }
        }
        return type;
    }

    public static SiteAccessibilityInfo getAccessibilityInfoFromSite(@NotNull SiteModel site) {
        SiteVisibility siteVisibility;
        if (site.isPrivateWPComAtomic()) {
            siteVisibility = SiteVisibility.PRIVATE_ATOMIC;
        } else if (site.isPrivate()) {
            siteVisibility = SiteVisibility.PRIVATE;
        } else {
            siteVisibility = SiteVisibility.PUBLIC;
        }
        return new SiteAccessibilityInfo(siteVisibility, isPhotonCapable(site));
    }

    public static ArrayList<Integer> getCurrentSiteIds(SiteStore siteStore, boolean selfhostedOnly) {
        ArrayList<Integer> siteIDs = new ArrayList<>();
        List<SiteModel> sites = selfhostedOnly ? siteStore.getSitesAccessedViaXMLRPC() : siteStore.getSites();
        if (!ListenerUtil.mutListener.listen(27889)) {
            {
                long _loopCounter416 = 0;
                for (SiteModel site : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter416", ++_loopCounter416);
                    if (!ListenerUtil.mutListener.listen(27888)) {
                        siteIDs.add(site.getId());
                    }
                }
            }
        }
        return siteIDs;
    }

    /**
     * Checks if site Jetpack version is higher than limit version
     *
     * @param site
     * @param limitVersion minimal acceptable Jetpack version
     * @return
     */
    public static boolean checkMinimalJetpackVersion(SiteModel site, String limitVersion) {
        String jetpackVersion = site.getJetpackVersion();
        if (!ListenerUtil.mutListener.listen(27893)) {
            if ((ListenerUtil.mutListener.listen(27891) ? ((ListenerUtil.mutListener.listen(27890) ? (site.isUsingWpComRestApi() || site.isJetpackConnected()) : (site.isUsingWpComRestApi() && site.isJetpackConnected())) || !TextUtils.isEmpty(jetpackVersion)) : ((ListenerUtil.mutListener.listen(27890) ? (site.isUsingWpComRestApi() || site.isJetpackConnected()) : (site.isUsingWpComRestApi() && site.isJetpackConnected())) && !TextUtils.isEmpty(jetpackVersion)))) {
                if (!ListenerUtil.mutListener.listen(27892)) {
                    // longer active.
                    if (jetpackVersion.equals("false")) {
                        return false;
                    }
                }
                return VersionUtils.checkMinimalVersion(jetpackVersion, limitVersion);
            }
        }
        return false;
    }

    public static boolean checkMinimalWordPressVersion(SiteModel site, String minVersion) {
        return VersionUtils.checkMinimalVersion(site.getSoftwareVersion(), minVersion);
    }

    public static boolean supportsStoriesFeature(SiteModel site) {
        return (ListenerUtil.mutListener.listen(27895) ? (site != null || ((ListenerUtil.mutListener.listen(27894) ? (site.isWPCom() && checkMinimalJetpackVersion(site, WP_STORIES_JETPACK_VERSION)) : (site.isWPCom() || checkMinimalJetpackVersion(site, WP_STORIES_JETPACK_VERSION))))) : (site != null && ((ListenerUtil.mutListener.listen(27894) ? (site.isWPCom() && checkMinimalJetpackVersion(site, WP_STORIES_JETPACK_VERSION)) : (site.isWPCom() || checkMinimalJetpackVersion(site, WP_STORIES_JETPACK_VERSION))))));
    }

    public static boolean supportsContactInfoFeature(SiteModel site) {
        return (ListenerUtil.mutListener.listen(27897) ? (site != null || ((ListenerUtil.mutListener.listen(27896) ? (site.isWPCom() && checkMinimalJetpackVersion(site, WP_CONTACT_INFO_JETPACK_VERSION)) : (site.isWPCom() || checkMinimalJetpackVersion(site, WP_CONTACT_INFO_JETPACK_VERSION))))) : (site != null && ((ListenerUtil.mutListener.listen(27896) ? (site.isWPCom() && checkMinimalJetpackVersion(site, WP_CONTACT_INFO_JETPACK_VERSION)) : (site.isWPCom() || checkMinimalJetpackVersion(site, WP_CONTACT_INFO_JETPACK_VERSION))))));
    }

    public static boolean supportsLayoutGridFeature(SiteModel site) {
        return (ListenerUtil.mutListener.listen(27899) ? (site != null || ((ListenerUtil.mutListener.listen(27898) ? (site.isWPCom() && site.isWPComAtomic()) : (site.isWPCom() || site.isWPComAtomic())))) : (site != null && ((ListenerUtil.mutListener.listen(27898) ? (site.isWPCom() && site.isWPComAtomic()) : (site.isWPCom() || site.isWPComAtomic())))));
    }

    public static boolean supportsTiledGalleryFeature(SiteModel site) {
        return (ListenerUtil.mutListener.listen(27900) ? (site != null || site.isWPCom()) : (site != null && site.isWPCom()));
    }

    public static boolean supportsEmbedVariationFeature(SiteModel site, String minimalJetpackVersion) {
        return (ListenerUtil.mutListener.listen(27902) ? (site != null || ((ListenerUtil.mutListener.listen(27901) ? (site.isWPCom() && checkMinimalJetpackVersion(site, minimalJetpackVersion)) : (site.isWPCom() || checkMinimalJetpackVersion(site, minimalJetpackVersion))))) : (site != null && ((ListenerUtil.mutListener.listen(27901) ? (site.isWPCom() && checkMinimalJetpackVersion(site, minimalJetpackVersion)) : (site.isWPCom() || checkMinimalJetpackVersion(site, minimalJetpackVersion))))));
    }

    public static boolean isNonAtomicBusinessPlanSite(@Nullable SiteModel site) {
        return (ListenerUtil.mutListener.listen(27904) ? ((ListenerUtil.mutListener.listen(27903) ? (site != null || !site.isAutomatedTransfer()) : (site != null && !site.isAutomatedTransfer())) || SiteUtils.hasNonJetpackBusinessPlan(site)) : ((ListenerUtil.mutListener.listen(27903) ? (site != null || !site.isAutomatedTransfer()) : (site != null && !site.isAutomatedTransfer())) && SiteUtils.hasNonJetpackBusinessPlan(site)));
    }

    public static boolean hasNonJetpackBusinessPlan(SiteModel site) {
        return site.getPlanId() == PlansConstants.BUSINESS_PLAN_ID;
    }

    public static boolean onFreePlan(@NonNull SiteModel site) {
        return site.getPlanId() == PlansConstants.FREE_PLAN_ID;
    }

    public static boolean onBloggerPlan(@NonNull SiteModel site) {
        return (ListenerUtil.mutListener.listen(27905) ? (site.getPlanId() == PlansConstants.BLOGGER_PLAN_ONE_YEAR_ID && site.getPlanId() == PlansConstants.BLOGGER_PLAN_TWO_YEARS_ID) : (site.getPlanId() == PlansConstants.BLOGGER_PLAN_ONE_YEAR_ID || site.getPlanId() == PlansConstants.BLOGGER_PLAN_TWO_YEARS_ID));
    }

    public static boolean hasFullAccessToContent(@Nullable SiteModel site) {
        return (ListenerUtil.mutListener.listen(27907) ? (site != null || ((ListenerUtil.mutListener.listen(27906) ? (site.isSelfHostedAdmin() && site.getHasCapabilityEditPages()) : (site.isSelfHostedAdmin() || site.getHasCapabilityEditPages())))) : (site != null && ((ListenerUtil.mutListener.listen(27906) ? (site.isSelfHostedAdmin() && site.getHasCapabilityEditPages()) : (site.isSelfHostedAdmin() || site.getHasCapabilityEditPages())))));
    }

    // TODO: Inline this method when the legacy 'MySiteFragment' class is removed.
    public static boolean isScanEnabled(boolean scanPurchased, SiteModel site) {
        return (ListenerUtil.mutListener.listen(27909) ? ((ListenerUtil.mutListener.listen(27908) ? (scanPurchased || !site.isWPCom()) : (scanPurchased && !site.isWPCom())) || !site.isWPComAtomic()) : ((ListenerUtil.mutListener.listen(27908) ? (scanPurchased || !site.isWPCom()) : (scanPurchased && !site.isWPCom())) && !site.isWPComAtomic()));
    }

    @NonNull
    public static FetchSitesPayload getFetchSitesPayload() {
        ArrayList<SiteFilter> siteFilters = new ArrayList<>();
        return new FetchSitesPayload(siteFilters, !BuildConfig.IS_JETPACK_APP);
    }
}
