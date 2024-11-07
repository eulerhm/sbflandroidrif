/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.reviewer;

import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static com.ichi2.anki.cardviewer.ViewerCommand.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PeripheralCommand {

    @Nullable
    private final Integer mKeyCode;

    @Nullable
    private final Character mUnicodeCharacter;

    @NonNull
    private final CardSide mCardSide;

    @ViewerCommandDef
    private final int mCommand;

    private final ModifierKeys modifierKeys;

    private PeripheralCommand(int keyCode, @ViewerCommandDef int command, @NonNull CardSide side, ModifierKeys modifierKeys) {
        this.mKeyCode = keyCode;
        this.mUnicodeCharacter = null;
        this.mCommand = command;
        this.mCardSide = side;
        this.modifierKeys = modifierKeys;
    }

    private PeripheralCommand(@Nullable Character unicodeCharacter, @ViewerCommandDef int command, @NonNull CardSide side, ModifierKeys modifierKeys) {
        this.modifierKeys = modifierKeys;
        this.mKeyCode = null;
        this.mUnicodeCharacter = unicodeCharacter;
        this.mCommand = command;
        this.mCardSide = side;
    }

    public int getCommand() {
        return mCommand;
    }

    public Character getUnicodeCharacter() {
        return mUnicodeCharacter;
    }

    public Integer getKeycode() {
        return mKeyCode;
    }

    public boolean isQuestion() {
        return (ListenerUtil.mutListener.listen(2965) ? (mCardSide == CardSide.QUESTION && mCardSide == CardSide.BOTH) : (mCardSide == CardSide.QUESTION || mCardSide == CardSide.BOTH));
    }

    public boolean isAnswer() {
        return (ListenerUtil.mutListener.listen(2966) ? (mCardSide == CardSide.ANSWER && mCardSide == CardSide.BOTH) : (mCardSide == CardSide.ANSWER || mCardSide == CardSide.BOTH));
    }

    public static PeripheralCommand unicode(char unicodeChar, @ViewerCommandDef int command, CardSide side) {
        return unicode(unicodeChar, command, side, ModifierKeys.allowShift());
    }

    private static PeripheralCommand unicode(char unicodeChar, @ViewerCommandDef int command, CardSide side, ModifierKeys modifierKeys) {
        // Note: cast is needed to select the correct constructor
        return new PeripheralCommand((Character) unicodeChar, command, side, modifierKeys);
    }

    public static PeripheralCommand keyCode(int keyCode, @ViewerCommandDef int command, CardSide side) {
        return keyCode(keyCode, command, side, ModifierKeys.none());
    }

    private static PeripheralCommand keyCode(int keyCode, @ViewerCommandDef int command, CardSide side, ModifierKeys modifiers) {
        return new PeripheralCommand(keyCode, command, side, modifiers);
    }

    public static List<PeripheralCommand> getDefaultCommands() {
        // Number of elements below
        List<PeripheralCommand> ret = new ArrayList<>(28);
        if (!ListenerUtil.mutListener.listen(2967)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_1, COMMAND_ANSWER_FIRST_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2968)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_2, COMMAND_ANSWER_SECOND_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2969)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_3, COMMAND_ANSWER_THIRD_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2970)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_4, COMMAND_ANSWER_FOURTH_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2971)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_1, COMMAND_ANSWER_FIRST_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2972)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_2, COMMAND_ANSWER_SECOND_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2973)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_3, COMMAND_ANSWER_THIRD_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2974)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_4, COMMAND_ANSWER_FOURTH_BUTTON, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2975)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_BUTTON_Y, COMMAND_FLIP_OR_ANSWER_EASE1, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2976)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_BUTTON_X, COMMAND_FLIP_OR_ANSWER_EASE2, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2977)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_BUTTON_B, COMMAND_FLIP_OR_ANSWER_EASE3, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2978)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_BUTTON_A, COMMAND_FLIP_OR_ANSWER_EASE4, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2979)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_SPACE, COMMAND_ANSWER_RECOMMENDED, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2980)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_ENTER, COMMAND_ANSWER_RECOMMENDED, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2981)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_ENTER, COMMAND_ANSWER_RECOMMENDED, CardSide.ANSWER));
        }
        if (!ListenerUtil.mutListener.listen(2982)) {
            // See: 1643 - Unsure if this will work - nothing came through on the emulator.
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_DPAD_CENTER, COMMAND_FLIP_OR_ANSWER_RECOMMENDED, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2983)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_E, COMMAND_EDIT, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2984)) {
            // international layouts but is what Anki Desktop does
            ret.add(PeripheralCommand.unicode('*', COMMAND_MARK, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2985)) {
            ret.add(PeripheralCommand.unicode('-', COMMAND_BURY_CARD, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2986)) {
            ret.add(PeripheralCommand.unicode('=', COMMAND_BURY_NOTE, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2987)) {
            ret.add(PeripheralCommand.unicode('@', COMMAND_SUSPEND_CARD, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2988)) {
            ret.add(PeripheralCommand.unicode('!', COMMAND_SUSPEND_NOTE, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2989)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_R, COMMAND_PLAY_MEDIA, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2990)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_F5, COMMAND_PLAY_MEDIA, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2991)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_V, COMMAND_REPLAY_VOICE, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2992)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_V, COMMAND_RECORD_VOICE, CardSide.BOTH, ModifierKeys.shift()));
        }
        if (!ListenerUtil.mutListener.listen(2993)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_Z, COMMAND_UNDO, CardSide.BOTH));
        }
        if (!ListenerUtil.mutListener.listen(2994)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_1, COMMAND_TOGGLE_FLAG_RED, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(2995)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_2, COMMAND_TOGGLE_FLAG_ORANGE, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(2996)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_3, COMMAND_TOGGLE_FLAG_GREEN, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(2997)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_4, COMMAND_TOGGLE_FLAG_BLUE, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(2998)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_1, COMMAND_TOGGLE_FLAG_RED, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(2999)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_2, COMMAND_TOGGLE_FLAG_ORANGE, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(3000)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_3, COMMAND_TOGGLE_FLAG_GREEN, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        if (!ListenerUtil.mutListener.listen(3001)) {
            ret.add(PeripheralCommand.keyCode(KeyEvent.KEYCODE_NUMPAD_4, COMMAND_TOGGLE_FLAG_BLUE, CardSide.BOTH, ModifierKeys.ctrl()));
        }
        return ret;
    }

    public boolean matchesModifier(KeyEvent event) {
        return modifierKeys.matches(event);
    }

    private enum CardSide {

        NONE, QUESTION, ANSWER, BOTH
    }

    public static class ModifierKeys {

        // null == true/false works.
        @Nullable
        private final Boolean mShift;

        @Nullable
        private final Boolean mCtrl;

        @Nullable
        private final Boolean mAlt;

        private ModifierKeys(@Nullable Boolean shift, @Nullable Boolean ctrl, @Nullable Boolean alt) {
            this.mShift = shift;
            this.mCtrl = ctrl;
            this.mAlt = alt;
        }

        public static ModifierKeys none() {
            return new ModifierKeys(false, false, false);
        }

        public static ModifierKeys ctrl() {
            return new ModifierKeys(false, true, false);
        }

        public static ModifierKeys shift() {
            return new ModifierKeys(true, false, false);
        }

        /**
         * Allows shift, but not Ctrl/Alt
         */
        public static ModifierKeys allowShift() {
            return new ModifierKeys(null, false, false);
        }

        public boolean matches(KeyEvent event) {
            // return false if Ctrl+1 is pressed and 1 is expected
            return (ListenerUtil.mutListener.listen(3006) ? ((ListenerUtil.mutListener.listen(3004) ? (((ListenerUtil.mutListener.listen(3002) ? (mShift == null && mShift == event.isShiftPressed()) : (mShift == null || mShift == event.isShiftPressed()))) || ((ListenerUtil.mutListener.listen(3003) ? (mCtrl == null && mCtrl == event.isCtrlPressed()) : (mCtrl == null || mCtrl == event.isCtrlPressed())))) : (((ListenerUtil.mutListener.listen(3002) ? (mShift == null && mShift == event.isShiftPressed()) : (mShift == null || mShift == event.isShiftPressed()))) && ((ListenerUtil.mutListener.listen(3003) ? (mCtrl == null && mCtrl == event.isCtrlPressed()) : (mCtrl == null || mCtrl == event.isCtrlPressed()))))) || ((ListenerUtil.mutListener.listen(3005) ? (mAlt == null && mAlt == event.isAltPressed()) : (mAlt == null || mAlt == event.isAltPressed())))) : ((ListenerUtil.mutListener.listen(3004) ? (((ListenerUtil.mutListener.listen(3002) ? (mShift == null && mShift == event.isShiftPressed()) : (mShift == null || mShift == event.isShiftPressed()))) || ((ListenerUtil.mutListener.listen(3003) ? (mCtrl == null && mCtrl == event.isCtrlPressed()) : (mCtrl == null || mCtrl == event.isCtrlPressed())))) : (((ListenerUtil.mutListener.listen(3002) ? (mShift == null && mShift == event.isShiftPressed()) : (mShift == null || mShift == event.isShiftPressed()))) && ((ListenerUtil.mutListener.listen(3003) ? (mCtrl == null && mCtrl == event.isCtrlPressed()) : (mCtrl == null || mCtrl == event.isCtrlPressed()))))) && ((ListenerUtil.mutListener.listen(3005) ? (mAlt == null && mAlt == event.isAltPressed()) : (mAlt == null || mAlt == event.isAltPressed())))));
        }
    }
}
