package com.ichi2.anki.reviewer;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import com.ichi2.anki.R;
import com.ichi2.themes.Themes;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// loads of unboxing issues, which are safe
@SuppressWarnings("ConstantConditions")
public class ActionButtonStatus {

    /**
     * Custom button allocation
     */
    @NonNull
    protected final Map<Integer, Integer> // setup's size
    mCustomButtons = new HashMap<>(25);

    private final ReviewerUi mReviewerUi;

    public static final int SHOW_AS_ACTION_NEVER = MenuItem.SHOW_AS_ACTION_NEVER;

    public static final int SHOW_AS_ACTION_IF_ROOM = MenuItem.SHOW_AS_ACTION_IF_ROOM;

    public static final int SHOW_AS_ACTION_ALWAYS = MenuItem.SHOW_AS_ACTION_ALWAYS;

    public static final int MENU_DISABLED = 3;

    @Nullable
    public Integer getByMenuResourceId(int resourceId) {
        if (!ListenerUtil.mutListener.listen(2902)) {
            if (!mCustomButtons.containsKey(resourceId)) {
                if (!ListenerUtil.mutListener.listen(2901)) {
                    Timber.w("Invalid resource lookup: %d", resourceId);
                }
                return SHOW_AS_ACTION_NEVER;
            }
        }
        return mCustomButtons.get(resourceId);
    }

    public ActionButtonStatus(ReviewerUi reviewerUi) {
        this.mReviewerUi = reviewerUi;
    }

    public void setup(SharedPreferences preferences) {
        if (!ListenerUtil.mutListener.listen(2903)) {
            // NOTE: the default values below should be in sync with preferences_custom_buttons.xml and reviewer.xml
            setupButton(preferences, R.id.action_undo, "customButtonUndo", SHOW_AS_ACTION_ALWAYS);
        }
        if (!ListenerUtil.mutListener.listen(2904)) {
            setupButton(preferences, R.id.action_schedule, "customButtonScheduleCard", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2905)) {
            setupButton(preferences, R.id.action_flag, "customButtonFlag", SHOW_AS_ACTION_ALWAYS);
        }
        if (!ListenerUtil.mutListener.listen(2906)) {
            setupButton(preferences, R.id.action_tag, "customButtonTags", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2907)) {
            setupButton(preferences, R.id.action_edit, "customButtonEditCard", SHOW_AS_ACTION_IF_ROOM);
        }
        if (!ListenerUtil.mutListener.listen(2908)) {
            setupButton(preferences, R.id.action_add_note_reviewer, "customButtonAddCard", MENU_DISABLED);
        }
        if (!ListenerUtil.mutListener.listen(2909)) {
            setupButton(preferences, R.id.action_replay, "customButtonReplay", SHOW_AS_ACTION_IF_ROOM);
        }
        if (!ListenerUtil.mutListener.listen(2910)) {
            setupButton(preferences, R.id.action_card_info, "customButtonCardInfo", MENU_DISABLED);
        }
        if (!ListenerUtil.mutListener.listen(2911)) {
            setupButton(preferences, R.id.action_clear_whiteboard, "customButtonClearWhiteboard", SHOW_AS_ACTION_IF_ROOM);
        }
        if (!ListenerUtil.mutListener.listen(2912)) {
            setupButton(preferences, R.id.action_hide_whiteboard, "customButtonShowHideWhiteboard", SHOW_AS_ACTION_ALWAYS);
        }
        if (!ListenerUtil.mutListener.listen(2913)) {
            setupButton(preferences, R.id.action_select_tts, "customButtonSelectTts", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2914)) {
            setupButton(preferences, R.id.action_open_deck_options, "customButtonDeckOptions", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2915)) {
            setupButton(preferences, R.id.action_bury, "customButtonBury", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2916)) {
            setupButton(preferences, R.id.action_suspend, "customButtonSuspend", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2917)) {
            setupButton(preferences, R.id.action_mark_card, "customButtonMarkCard", SHOW_AS_ACTION_IF_ROOM);
        }
        if (!ListenerUtil.mutListener.listen(2918)) {
            setupButton(preferences, R.id.action_delete, "customButtonDelete", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2919)) {
            setupButton(preferences, R.id.action_toggle_mic_tool_bar, "customButtonToggleMicToolBar", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2920)) {
            setupButton(preferences, R.id.action_toggle_whiteboard, "customButtonEnableWhiteboard", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2921)) {
            setupButton(preferences, R.id.action_save_whiteboard, "customButtonSaveWhiteboard", SHOW_AS_ACTION_NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2922)) {
            setupButton(preferences, R.id.action_change_whiteboard_pen_color, "customButtonWhiteboardPenColor", SHOW_AS_ACTION_IF_ROOM);
        }
    }

    private void setupButton(SharedPreferences preferences, @IdRes int resourceId, String preferenceName, int showAsActionType) {
        if (!ListenerUtil.mutListener.listen(2923)) {
            mCustomButtons.put(resourceId, Integer.parseInt(preferences.getString(preferenceName, Integer.toString(showAsActionType))));
        }
    }

    public void setCustomButtons(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2939)) {
            {
                long _loopCounter72 = 0;
                for (Map.Entry<Integer, Integer> entry : mCustomButtons.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter72", ++_loopCounter72);
                    int itemId = entry.getKey();
                    if (!ListenerUtil.mutListener.listen(2938)) {
                        if ((ListenerUtil.mutListener.listen(2928) ? (entry.getValue() >= MENU_DISABLED) : (ListenerUtil.mutListener.listen(2927) ? (entry.getValue() <= MENU_DISABLED) : (ListenerUtil.mutListener.listen(2926) ? (entry.getValue() > MENU_DISABLED) : (ListenerUtil.mutListener.listen(2925) ? (entry.getValue() < MENU_DISABLED) : (ListenerUtil.mutListener.listen(2924) ? (entry.getValue() == MENU_DISABLED) : (entry.getValue() != MENU_DISABLED))))))) {
                            MenuItem item = menu.findItem(itemId);
                            if (!ListenerUtil.mutListener.listen(2931)) {
                                if (item == null) {
                                    if (!ListenerUtil.mutListener.listen(2930)) {
                                        // Happens with TV - removing flag icon
                                        Timber.w("Could not find Menu Item %d", itemId);
                                    }
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2932)) {
                                item.setShowAsAction(entry.getValue());
                            }
                            Drawable icon = item.getIcon();
                            if (!ListenerUtil.mutListener.listen(2933)) {
                                item.setEnabled(!mReviewerUi.isControlBlocked());
                            }
                            if (!ListenerUtil.mutListener.listen(2937)) {
                                if (icon != null) {
                                    /* Ideally, we want to give feedback to users that
                    buttons are disabled.  However, some actions are
                    expected to be so quick that the visual feedback
                    is useless and is only seen as a flickering.

                    We use a heuristic to decide whether the next card
                    will appear quickly or slowly.  We change the
                    color only if the buttons are blocked and we
                    expect the next card to take time to arrive.
                    */
                                    Drawable mutableIcon = icon.mutate();
                                    if (!ListenerUtil.mutListener.listen(2936)) {
                                        if (mReviewerUi.getControlBlocked() == ReviewerUi.ControlBlock.SLOW) {
                                            if (!ListenerUtil.mutListener.listen(2935)) {
                                                mutableIcon.setAlpha(Themes.ALPHA_ICON_DISABLED_LIGHT);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(2934)) {
                                                mutableIcon.setAlpha(Themes.ALPHA_ICON_ENABLED_LIGHT);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2929)) {
                                menu.findItem(itemId).setVisible(false);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean hideWhiteboardIsDisabled() {
        return mCustomButtons.get(R.id.action_hide_whiteboard) == MENU_DISABLED;
    }

    public boolean clearWhiteboardIsDisabled() {
        return mCustomButtons.get(R.id.action_clear_whiteboard) == MENU_DISABLED;
    }

    public boolean selectTtsIsDisabled() {
        return mCustomButtons.get(R.id.action_select_tts) == MENU_DISABLED;
    }

    public boolean saveWhiteboardIsDisabled() {
        return mCustomButtons.get(R.id.action_save_whiteboard) == MENU_DISABLED;
    }

    public boolean whiteboardPenColorIsDisabled() {
        return mCustomButtons.get(R.id.action_change_whiteboard_pen_color) == MENU_DISABLED;
    }
}
