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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ExecutionException;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import ch.threema.app.R;
import ch.threema.app.utils.RuntimeUtil;
import java8.util.concurrent.CompletableFuture;
import java8.util.function.Consumer;
import java8.util.function.Supplier;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiManager {

    private static final Logger logger = LoggerFactory.getLogger(EmojiManager.class);

    // Singleton
    private static volatile EmojiManager instance = null;

    public static final int EMOJI_HEIGHT = 64;

    public static final int EMOJI_WIDTH = 64;

    private final int spritemapInSampleSize;

    private final Context appContext;

    private static final EmojiGroup[] emojiGroups = { new EmojiGroup(null, null, R.drawable.emoji_category_recent, R.string.emoji_recent), new EmojiGroup("emojis/people-", ".png", R.drawable.emoji_category_people, R.string.emoji_emotions), new EmojiGroup("emojis/nature-", ".png", R.drawable.emoji_category_nature, R.string.emoji_nature), new EmojiGroup("emojis/food-", ".png", R.drawable.emoji_category_food, R.string.emoji_food), new EmojiGroup("emojis/activity-", ".png", R.drawable.emoji_category_activities, R.string.emoji_activities), new EmojiGroup("emojis/travel-", ".png", R.drawable.emoji_category_travel, R.string.emoji_traffic), new EmojiGroup("emojis/objects-", ".png", R.drawable.emoji_category_objects, R.string.emoji_things), new EmojiGroup("emojis/symbols-", ".png", R.drawable.emoji_category_symbols, R.string.emoji_symbols), new EmojiGroup("emojis/flags-", ".png", R.drawable.emoji_category_flags, R.string.emoji_flags) };

    public static EmojiManager getInstance(Context context) {
        if (!ListenerUtil.mutListener.listen(14999)) {
            if (instance == null) {
                synchronized (EmojiManager.class) {
                    if (!ListenerUtil.mutListener.listen(14998)) {
                        if (instance == null) {
                            if (!ListenerUtil.mutListener.listen(14997)) {
                                instance = new EmojiManager(context);
                            }
                        }
                    }
                }
            }
        }
        return instance;
    }

    private EmojiManager(Context context) {
        this.appContext = context.getApplicationContext();
        this.spritemapInSampleSize = (ListenerUtil.mutListener.listen(15004) ? (context.getResources().getDisplayMetrics().density >= 1f) : (ListenerUtil.mutListener.listen(15003) ? (context.getResources().getDisplayMetrics().density > 1f) : (ListenerUtil.mutListener.listen(15002) ? (context.getResources().getDisplayMetrics().density < 1f) : (ListenerUtil.mutListener.listen(15001) ? (context.getResources().getDisplayMetrics().density != 1f) : (ListenerUtil.mutListener.listen(15000) ? (context.getResources().getDisplayMetrics().density == 1f) : (context.getResources().getDisplayMetrics().density <= 1f)))))) ? 2 : 1;
    }

    public static EmojiGroup[] getEmojiGroups() {
        return emojiGroups;
    }

    public static int getNumberOfEmojiGroups() {
        return emojiGroups.length;
    }

    /**
     *  @param emojiSequence - sequence of UTF-8 characters representing the emoji
     *  @return Drawable for emoji or null if there is no matching emoji
     */
    public Drawable getEmojiDrawable(String emojiSequence) {
        EmojiParser.ParseResult result = EmojiParser.parseAt(emojiSequence, 0);
        return result != null ? getEmojiDrawable(result.coords) : null;
    }

    /**
     *  @param coordinates - The sprite coordinates
     *  @return Drawable for emoji
     */
    @UiThread
    @Nullable
    public Drawable getEmojiDrawable(SpriteCoordinates coordinates) {
        if (!ListenerUtil.mutListener.listen(15014)) {
            if (coordinates != null) {
                final EmojiGroup emojiGroup = emojiGroups[coordinates.groupId];
                if (!ListenerUtil.mutListener.listen(15006)) {
                    if (!emojiGroup.hasSpritemapBitmap(coordinates.spritemapId)) {
                        if (!ListenerUtil.mutListener.listen(15005)) {
                            emojiGroup.setSpritemapBitmap(coordinates.spritemapId, new EmojiSpritemapBitmap(appContext, emojiGroup, coordinates.spritemapId, spritemapInSampleSize));
                        }
                    }
                }
                final EmojiDrawable drawable = new EmojiDrawable(coordinates, spritemapInSampleSize);
                if (!ListenerUtil.mutListener.listen(15013)) {
                    if (emojiGroup.getSpritemapBitmap(coordinates.spritemapId) != null) {
                        if (!ListenerUtil.mutListener.listen(15012)) {
                            if (emojiGroup.getSpritemapBitmap(coordinates.spritemapId).isSpritemapLoaded()) {
                                if (!ListenerUtil.mutListener.listen(15011)) {
                                    drawable.setBitmap(emojiGroup.getSpritemapBitmap(coordinates.spritemapId).getSpritemapBitmap());
                                }
                            } else {
                                try {
                                    if (!ListenerUtil.mutListener.listen(15010)) {
                                        CompletableFuture.supplyAsync(new Supplier<Bitmap>() {

                                            @Override
                                            public Bitmap get() {
                                                return emojiGroup.getSpritemapBitmap(coordinates.spritemapId).loadSpritemapAsset();
                                            }
                                        }).thenAccept(new Consumer<Bitmap>() {

                                            @Override
                                            public void accept(Bitmap bitmap) {
                                                if (!ListenerUtil.mutListener.listen(15009)) {
                                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            if (!ListenerUtil.mutListener.listen(15008)) {
                                                                drawable.setBitmap(bitmap);
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }).get();
                                    }
                                } catch (InterruptedException | ExecutionException e) {
                                    if (!ListenerUtil.mutListener.listen(15007)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                        return drawable;
                    }
                }
            }
        }
        return null;
    }

    public int getSpritemapInSampleSize() {
        return this.spritemapInSampleSize;
    }

    @StringRes
    public static int getGroupName(int id) {
        return emojiGroups[id].getGroupName();
    }
}
