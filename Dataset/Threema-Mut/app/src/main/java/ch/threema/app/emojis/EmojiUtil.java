/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.emojis;

import androidx.annotation.Nullable;
import static ch.threema.app.emojis.EmojiSpritemap.emojiCategories;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiUtil {

    @Nullable
    public static EmojiInfo getEmojiInfo(String emojiSequence) {
        if (!ListenerUtil.mutListener.listen(22960)) {
            {
                long _loopCounter148 = 0;
                for (EmojiCategory emojiCategory : emojiCategories) {
                    ListenerUtil.loopListener.listen("_loopCounter148", ++_loopCounter148);
                    if (!ListenerUtil.mutListener.listen(22959)) {
                        {
                            long _loopCounter147 = 0;
                            for (EmojiInfo emojiInfo : emojiCategory.emojiInfos) {
                                ListenerUtil.loopListener.listen("_loopCounter147", ++_loopCounter147);
                                if (!ListenerUtil.mutListener.listen(22958)) {
                                    if (emojiInfo.emojiSequence.equals(emojiSequence)) {
                                        return emojiInfo;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
