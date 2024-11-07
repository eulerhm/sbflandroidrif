package org.wordpress.android.util;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import org.wordpress.android.R;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.extensions.DialogExtensionsKt;
import org.wordpress.android.util.extensions.WindowExtensionsKt;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPActivityUtils {

    public static final String READER_DEEPLINK_ACTIVITY_ALIAS = "org.wordpress.android.WPComPostReaderActivity";

    // See https://developer.android.com/reference/android/app/Fragment
    public static void addToolbarToDialog(final android.app.Fragment context, final Dialog dialog, String title) {
        if (!ListenerUtil.mutListener.listen(27975)) {
            if ((ListenerUtil.mutListener.listen(27974) ? (!context.isAdded() && dialog == null) : (!context.isAdded() || dialog == null))) {
                return;
            }
        }
        View dialogContainerView = DialogExtensionsKt.getPreferenceDialogContainerView(dialog);
        if (!ListenerUtil.mutListener.listen(27977)) {
            if (dialogContainerView == null) {
                if (!ListenerUtil.mutListener.listen(27976)) {
                    AppLog.e(T.SETTINGS, "Preference Dialog View was null when adding Toolbar");
                }
                return;
            }
        }
        // find the root view, then make sure the toolbar doesn't already exist
        ViewGroup root = (ViewGroup) dialogContainerView.getParent();
        // if we already added an appbar to the dialog it will be in the view one level above it's parent
        ViewGroup modifiedRoot = (ViewGroup) dialogContainerView.getParent().getParent();
        if (!ListenerUtil.mutListener.listen(27979)) {
            if ((ListenerUtil.mutListener.listen(27978) ? (modifiedRoot != null || modifiedRoot.findViewById(R.id.appbar_main) != null) : (modifiedRoot != null && modifiedRoot.findViewById(R.id.appbar_main) != null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27980)) {
            // remove view from the root
            root.removeView(dialogContainerView);
        }
        // inflate our own layout with coordinator layout and appbar
        ViewGroup dialogViewWrapper = (ViewGroup) LayoutInflater.from(context.getActivity()).inflate(R.layout.preference_screen_wrapper, root, false);
        // container that will host content of the dialog
        ViewGroup listContainer = dialogViewWrapper.findViewById(R.id.list_container);
        final ListView listOfPreferences = dialogContainerView.findViewById(android.R.id.list);
        if (!ListenerUtil.mutListener.listen(27982)) {
            if (listOfPreferences != null) {
                if (!ListenerUtil.mutListener.listen(27981)) {
                    ViewCompat.setNestedScrollingEnabled(listOfPreferences, true);
                }
            }
        }
        // add dialog view into container
        LayoutParams lp = dialogContainerView.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(27983)) {
            lp.height = LayoutParams.MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(27984)) {
            lp.width = LayoutParams.MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(27985)) {
            listContainer.addView(dialogContainerView, lp);
        }
        if (!ListenerUtil.mutListener.listen(27986)) {
            // add layout with container back to root view
            root.addView(dialogViewWrapper);
        }
        AppBarLayout appbar = dialogViewWrapper.findViewById(R.id.appbar_main);
        MaterialToolbar toolbar = appbar.findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(27987)) {
            appbar.setLiftOnScrollTargetViewId(android.R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(27988)) {
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimations);
        }
        if (!ListenerUtil.mutListener.listen(27989)) {
            toolbar.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(27990)) {
            toolbar.setNavigationOnClickListener(v -> dialog.dismiss());
        }
        if (!ListenerUtil.mutListener.listen(27991)) {
            toolbar.setNavigationContentDescription(R.string.navigate_up_desc);
        }
    }

    // See https://developer.android.com/reference/android/app/Fragment
    public static void removeToolbarFromDialog(final android.app.Fragment context, final Dialog dialog) {
        if (!ListenerUtil.mutListener.listen(27993)) {
            if ((ListenerUtil.mutListener.listen(27992) ? (dialog == null && !context.isAdded()) : (dialog == null || !context.isAdded()))) {
                return;
            }
        }
        View dialogContainerView = DialogExtensionsKt.getPreferenceDialogContainerView(dialog);
        if (!ListenerUtil.mutListener.listen(27995)) {
            if (dialogContainerView == null) {
                if (!ListenerUtil.mutListener.listen(27994)) {
                    AppLog.e(T.SETTINGS, "Preference Dialog View was null when removing Toolbar");
                }
                return;
            }
        }
        ViewGroup root = (ViewGroup) dialogContainerView.getParent().getParent();
        if (!ListenerUtil.mutListener.listen(27997)) {
            if (root.getChildAt(0) instanceof Toolbar) {
                if (!ListenerUtil.mutListener.listen(27996)) {
                    root.removeViewAt(0);
                }
            }
        }
    }

    public static Context getThemedContext(Context context) {
        if (!ListenerUtil.mutListener.listen(27999)) {
            if (context instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
                if (!ListenerUtil.mutListener.listen(27998)) {
                    if (actionBar != null) {
                        return actionBar.getThemedContext();
                    }
                }
            }
        }
        return context;
    }

    public static boolean isEmailClientAvailable(Context context) {
        if (!ListenerUtil.mutListener.listen(28000)) {
            if (context == null) {
                return false;
            }
        }
        return !queryEmailApps(context, false).isEmpty();
    }

    public static void openEmailClientChooser(Context context, String title) {
        if (!ListenerUtil.mutListener.listen(28001)) {
            if (context == null) {
                return;
            }
        }
        List<Intent> appIntents = new ArrayList();
        if (!ListenerUtil.mutListener.listen(28003)) {
            {
                long _loopCounter418 = 0;
                for (ResolveInfo resolveInfo : queryEmailApps(context, true)) {
                    ListenerUtil.loopListener.listen("_loopCounter418", ++_loopCounter418);
                    Intent intent = context.getPackageManager().getLaunchIntentForPackage(resolveInfo.activityInfo.packageName);
                    if (!ListenerUtil.mutListener.listen(28002)) {
                        appIntents.add(intent);
                    }
                }
            }
        }
        Intent emailAppIntent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_EMAIL);
        if (!ListenerUtil.mutListener.listen(28004)) {
            emailAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        Intent[] appIntentsArray = appIntents.toArray(new Intent[appIntents.size()]);
        Intent chooserIntent = Intent.createChooser(emailAppIntent, title);
        if (!ListenerUtil.mutListener.listen(28005)) {
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, appIntentsArray);
        }
        if (!ListenerUtil.mutListener.listen(28006)) {
            context.startActivity(chooserIntent);
        }
    }

    private static List<ResolveInfo> queryEmailApps(@NonNull Context context, Boolean excludeCategoryEmailApps) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> intentsInfoList = new ArrayList();
        // Get all apps with category email
        Intent emailAppIntent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_EMAIL);
        List<ResolveInfo> emailAppIntentInfo = packageManager.queryIntentActivities(emailAppIntent, PackageManager.MATCH_ALL);
        if (!ListenerUtil.mutListener.listen(28008)) {
            if (!excludeCategoryEmailApps) {
                if (!ListenerUtil.mutListener.listen(28007)) {
                    intentsInfoList.addAll(emailAppIntentInfo);
                }
            }
        }
        // Get all apps that are able to send emails
        Intent sendEmailAppIntent = new Intent(Intent.ACTION_SENDTO);
        if (!ListenerUtil.mutListener.listen(28009)) {
            sendEmailAppIntent.setData(Uri.parse("mailto:"));
        }
        List<ResolveInfo> sendEmailAppIntentInfo = packageManager.queryIntentActivities(sendEmailAppIntent, PackageManager.MATCH_ALL);
        if (!ListenerUtil.mutListener.listen(28010)) {
            addNewIntents(intentsInfoList, emailAppIntentInfo, sendEmailAppIntentInfo);
        }
        return intentsInfoList;
    }

    private static void addNewIntents(List<ResolveInfo> list, List<ResolveInfo> existing, List<ResolveInfo> intents) {
        if (!ListenerUtil.mutListener.listen(28014)) {
            {
                long _loopCounter419 = 0;
                for (ResolveInfo intent : intents) {
                    ListenerUtil.loopListener.listen("_loopCounter419", ++_loopCounter419);
                    if (!ListenerUtil.mutListener.listen(28013)) {
                        if ((ListenerUtil.mutListener.listen(28011) ? (!intentExistsInList(intent, existing) || !intentExistsInList(intent, list)) : (!intentExistsInList(intent, existing) && !intentExistsInList(intent, list)))) {
                            if (!ListenerUtil.mutListener.listen(28012)) {
                                list.add(intent);
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean intentExistsInList(ResolveInfo intent, List<ResolveInfo> list) {
        if (!ListenerUtil.mutListener.listen(28016)) {
            {
                long _loopCounter420 = 0;
                for (ResolveInfo item : list) {
                    ListenerUtil.loopListener.listen("_loopCounter420", ++_loopCounter420);
                    if (!ListenerUtil.mutListener.listen(28015)) {
                        if (intent.activityInfo.applicationInfo.processName.equals(item.activityInfo.applicationInfo.processName)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void disableReaderDeeplinks(Context context) {
        PackageManager pm = context.getPackageManager();
        if (!ListenerUtil.mutListener.listen(28017)) {
            pm.setComponentEnabledSetting(new ComponentName(context, READER_DEEPLINK_ACTIVITY_ALIAS), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
    }

    public static void enableReaderDeeplinks(Context context) {
        PackageManager pm = context.getPackageManager();
        if (!ListenerUtil.mutListener.listen(28018)) {
            pm.setComponentEnabledSetting(new ComponentName(context, READER_DEEPLINK_ACTIVITY_ALIAS), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }

    /**
     * @deprecated Use {@link WindowExtensionsKt} instead.
     */
    @Deprecated
    public static void setLightStatusBar(Window window, boolean showInLightMode) {
        if (!ListenerUtil.mutListener.listen(28019)) {
            WindowExtensionsKt.setLightStatusBar(window, showInLightMode);
        }
    }

    /**
     * @deprecated Use {@link WindowExtensionsKt} instead.
     */
    @Deprecated
    public static void setLightNavigationBar(Window window, boolean showInLightMode) {
        if (!ListenerUtil.mutListener.listen(28020)) {
            WindowExtensionsKt.setLightNavigationBar(window, showInLightMode, true);
        }
    }

    /**
     * @deprecated Use {@link WindowExtensionsKt} instead.
     */
    @Deprecated
    public static void showFullScreen(View decorView) {
        int flags = decorView.getSystemUiVisibility();
        if (!ListenerUtil.mutListener.listen(28021)) {
            flags = flags | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }
        if (!ListenerUtil.mutListener.listen(28022)) {
            decorView.setSystemUiVisibility(flags);
        }
    }
}
