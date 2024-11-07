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
import com.ichi2.anki.cardviewer.ViewerCommand.CommandProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Accepts peripheral input, mapping via various keybinding strategies,
 * and converting them to commands for the Reviewer.
 */
public class PeripheralKeymap {

    private final ReviewerUi mReviewerUI;

    private final KeyMap mAnswerKeyMap;

    private final KeyMap mQuestionKeyMap;

    private boolean mHasSetup = false;

    public PeripheralKeymap(ReviewerUi reviewerUi, CommandProcessor commandProcessor) {
        this.mReviewerUI = reviewerUi;
        this.mQuestionKeyMap = new KeyMap(commandProcessor);
        this.mAnswerKeyMap = new KeyMap(commandProcessor);
    }

    public void setup() {
        List<PeripheralCommand> commands = PeripheralCommand.getDefaultCommands();
        if (!ListenerUtil.mutListener.listen(3011)) {
            {
                long _loopCounter73 = 0;
                for (PeripheralCommand command : commands) {
                    ListenerUtil.loopListener.listen("_loopCounter73", ++_loopCounter73);
                    if (!ListenerUtil.mutListener.listen(3008)) {
                        // NOTE: Can be both
                        if (command.isQuestion()) {
                            if (!ListenerUtil.mutListener.listen(3007)) {
                                mQuestionKeyMap.addCommand(command);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3010)) {
                        if (command.isAnswer()) {
                            if (!ListenerUtil.mutListener.listen(3009)) {
                                mAnswerKeyMap.addCommand(command);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3012)) {
            mHasSetup = true;
        }
    }

    @SuppressWarnings({ "unused", "RedundantSuppression" })
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!mHasSetup) {
            return false;
        }
        if (mReviewerUI.isDisplayingAnswer()) {
            return mAnswerKeyMap.onKeyUp(keyCode, event);
        } else {
            return mQuestionKeyMap.onKeyUp(keyCode, event);
        }
    }

    private static class KeyMap {

        public final HashMap<Integer, List<PeripheralCommand>> mKeyCodeToCommand = new HashMap<>();

        public final HashMap<Integer, List<PeripheralCommand>> mUnicodeToCommand = new HashMap<>();

        private final CommandProcessor mProcessor;

        private KeyMap(CommandProcessor commandProcessor) {
            this.mProcessor = commandProcessor;
        }

        public boolean onKeyUp(int keyCode, KeyEvent event) {
            boolean ret = false;
            {
                List<PeripheralCommand> a = mKeyCodeToCommand.get(keyCode);
                if (!ListenerUtil.mutListener.listen(3016)) {
                    if (a != null) {
                        if (!ListenerUtil.mutListener.listen(3015)) {
                            {
                                long _loopCounter74 = 0;
                                for (PeripheralCommand command : a) {
                                    ListenerUtil.loopListener.listen("_loopCounter74", ++_loopCounter74);
                                    if (!ListenerUtil.mutListener.listen(3013)) {
                                        if (!command.matchesModifier(event)) {
                                            continue;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3014)) {
                                        ret |= mProcessor.executeCommand(command.getCommand());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            {
                // NOTE: We do not differentiate on upper/lower case via KeyEvent.META_CAPS_LOCK_ON
                int unicodeChar = event.getUnicodeChar(event.getMetaState() & (KeyEvent.META_SHIFT_ON | KeyEvent.META_NUM_LOCK_ON));
                List<PeripheralCommand> unicodeLookup = mUnicodeToCommand.get(unicodeChar);
                if (!ListenerUtil.mutListener.listen(3020)) {
                    if (unicodeLookup != null) {
                        if (!ListenerUtil.mutListener.listen(3019)) {
                            {
                                long _loopCounter75 = 0;
                                for (PeripheralCommand command : unicodeLookup) {
                                    ListenerUtil.loopListener.listen("_loopCounter75", ++_loopCounter75);
                                    if (!ListenerUtil.mutListener.listen(3017)) {
                                        if (!command.matchesModifier(event)) {
                                            continue;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3018)) {
                                        ret |= mProcessor.executeCommand(command.getCommand());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return ret;
        }

        public void addCommand(PeripheralCommand command) {
            if (!ListenerUtil.mutListener.listen(3024)) {
                // COULD_BE_BETTER: DefaultDict
                if (command.getUnicodeCharacter() != null) {
                    // NB: Int is correct here, the value from KeyCode is an int.
                    int unicodeChar = command.getUnicodeCharacter();
                    if (!ListenerUtil.mutListener.listen(3022)) {
                        if (!mUnicodeToCommand.containsKey(unicodeChar)) {
                            if (!ListenerUtil.mutListener.listen(3021)) {
                                mUnicodeToCommand.put(unicodeChar, new ArrayList<>(0));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3023)) {
                        // noinspection ConstantConditions
                        mUnicodeToCommand.get(unicodeChar).add(command);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3028)) {
                if (command.getKeycode() != null) {
                    Integer c = command.getKeycode();
                    if (!ListenerUtil.mutListener.listen(3026)) {
                        if (!mKeyCodeToCommand.containsKey(c)) {
                            if (!ListenerUtil.mutListener.listen(3025)) {
                                mKeyCodeToCommand.put(c, new ArrayList<>(0));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3027)) {
                        // noinspection ConstantConditions
                        mKeyCodeToCommand.get(c).add(command);
                    }
                }
            }
        }
    }
}
