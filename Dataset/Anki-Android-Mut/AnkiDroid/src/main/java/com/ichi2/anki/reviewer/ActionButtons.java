package com.ichi2.anki.reviewer;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import com.ichi2.anki.R;
import com.ichi2.ui.ActionBarOverflow;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ActionButtons {

    private final ActionButtonStatus mActionButtonStatus;

    @IdRes
    public static final int RES_FLAG = R.id.action_flag;

    @IdRes
    public static final int RES_MARK = R.id.action_mark_card;

    private Menu mMenu;

    public ActionButtons(ReviewerUi reviewerUi) {
        this.mActionButtonStatus = new ActionButtonStatus(reviewerUi);
    }

    public void setup(SharedPreferences preferences) {
        if (!ListenerUtil.mutListener.listen(2940)) {
            this.mActionButtonStatus.setup(preferences);
        }
    }

    /**
     * Sets the order of the Action Buttons in the action bar
     */
    public void setCustomButtonsStatus(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2941)) {
            this.mActionButtonStatus.setCustomButtons(menu);
        }
        if (!ListenerUtil.mutListener.listen(2942)) {
            this.mMenu = menu;
        }
    }

    @Nullable
    public Boolean isShownInActionBar(@IdRes int resId) {
        MenuItem menuItem = findMenuItem(resId);
        if (!ListenerUtil.mutListener.listen(2943)) {
            if (menuItem == null) {
                return null;
            }
        }
        // firstly, see if we can definitively determine whether the action is visible.
        Boolean isActionButton = ActionBarOverflow.isActionButton(menuItem);
        if (!ListenerUtil.mutListener.listen(2944)) {
            if (isActionButton != null) {
                return isActionButton;
            }
        }
        // If not, use heuristics based on preferences.
        return isLikelyActionButton(resId);
    }

    @Nullable
    private MenuItem findMenuItem(@IdRes int resId) {
        if (!ListenerUtil.mutListener.listen(2945)) {
            if (mMenu == null) {
                return null;
            }
        }
        return mMenu.findItem(resId);
    }

    private boolean isLikelyActionButton(@IdRes int resourceId) {
        /*
        https://github.com/ankidroid/Anki-Android/pull/5918#issuecomment-609484093
        Heuristic approach: Show the item in the top bar unless the corresponding menu item is set to "always" show.

        There are two scenarios where the heuristic fails:

        1. An item is set to 'if room' but is actually visible in the toolbar
        2. An item is set to 'always' but is actually not visible in the toolbar

        Failure scenario one is totally acceptable IMO as it just falls back to the current behavior.
        Failure scenario two is not ideal, but it should only happen in the pathological case where the user has gone
        and explicitly changed the preferences to set more items to 'always' than there is room for in the toolbar.

        In any case, both failure scenarios only happen if the user deviated from the default settings in strange ways.
         */
        Integer status = mActionButtonStatus.getByMenuResourceId(resourceId);
        if (!ListenerUtil.mutListener.listen(2947)) {
            if (status == null) {
                if (!ListenerUtil.mutListener.listen(2946)) {
                    Timber.w("Failed to get status for resource: %d", resourceId);
                }
                // If we return "true", we may hide the flag/mark status completely. False is safer.
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(2952) ? (status >= ActionButtonStatus.SHOW_AS_ACTION_ALWAYS) : (ListenerUtil.mutListener.listen(2951) ? (status <= ActionButtonStatus.SHOW_AS_ACTION_ALWAYS) : (ListenerUtil.mutListener.listen(2950) ? (status > ActionButtonStatus.SHOW_AS_ACTION_ALWAYS) : (ListenerUtil.mutListener.listen(2949) ? (status < ActionButtonStatus.SHOW_AS_ACTION_ALWAYS) : (ListenerUtil.mutListener.listen(2948) ? (status != ActionButtonStatus.SHOW_AS_ACTION_ALWAYS) : (status == ActionButtonStatus.SHOW_AS_ACTION_ALWAYS))))));
    }

    public ActionButtonStatus getStatus() {
        // to this point
        return this.mActionButtonStatus;
    }
}
