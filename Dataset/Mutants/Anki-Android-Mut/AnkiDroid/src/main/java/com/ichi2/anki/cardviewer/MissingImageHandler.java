/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.cardviewer;

import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import com.ichi2.utils.FunctionalInterfaces.Consumer;
import java.io.File;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles logic for displaying help for missing media files
 */
public class MissingImageHandler {

    /**
     * Specify a maximum number of times to display, as it's somewhat annoying
     */
    public static final int MAX_DISPLAY_TIMES = 2;

    private int mMissingMediaCount = 0;

    private boolean mHasShownInefficientImage = false;

    private boolean mHasExecuted = false;

    public MissingImageHandler() {
    }

    public void processFailure(WebResourceRequest request, @NonNull Consumer<String> onFailure) {
        if (!ListenerUtil.mutListener.listen(294)) {
            // We do not want this to trigger more than once on the same side of the card as the UI will flicker.
            if ((ListenerUtil.mutListener.listen(293) ? (request == null && mHasExecuted) : (request == null || mHasExecuted))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(300)) {
            // The UX of the snackbar is annoying, as it obscures the content. Assume that if a user ignores it twice, they don't care.
            if ((ListenerUtil.mutListener.listen(299) ? (mMissingMediaCount <= MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(298) ? (mMissingMediaCount > MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(297) ? (mMissingMediaCount < MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(296) ? (mMissingMediaCount != MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(295) ? (mMissingMediaCount == MAX_DISPLAY_TIMES) : (mMissingMediaCount >= MAX_DISPLAY_TIMES))))))) {
                return;
            }
        }
        String url = request.getUrl().toString();
        if (!ListenerUtil.mutListener.listen(301)) {
            // Therefore limit this feature to the common case of local files, which should always work.
            if (!url.contains("collection.media")) {
                return;
            }
        }
        try {
            String filename = URLUtil.guessFileName(url, null, null);
            if (!ListenerUtil.mutListener.listen(304)) {
                onFailure.consume(filename);
            }
            if (!ListenerUtil.mutListener.listen(305)) {
                mMissingMediaCount++;
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(302)) {
                Timber.w(e, "Failed to notify UI of media failure");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(303)) {
                mHasExecuted = true;
            }
        }
    }

    public void processMissingSound(File file, @NonNull Consumer<String> onFailure) {
        if (!ListenerUtil.mutListener.listen(306)) {
            // and we want to provide feedback
            if (file == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(312)) {
            // The UX of the snackbar is annoying, as it obscures the content. Assume that if a user ignores it twice, they don't care.
            if ((ListenerUtil.mutListener.listen(311) ? (mMissingMediaCount <= MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(310) ? (mMissingMediaCount > MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(309) ? (mMissingMediaCount < MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(308) ? (mMissingMediaCount != MAX_DISPLAY_TIMES) : (ListenerUtil.mutListener.listen(307) ? (mMissingMediaCount == MAX_DISPLAY_TIMES) : (mMissingMediaCount >= MAX_DISPLAY_TIMES))))))) {
                return;
            }
        }
        try {
            String fileName = file.getName();
            if (!ListenerUtil.mutListener.listen(315)) {
                onFailure.consume(fileName);
            }
            if (!ListenerUtil.mutListener.listen(317)) {
                if (!mHasExecuted) {
                    if (!ListenerUtil.mutListener.listen(316)) {
                        mMissingMediaCount++;
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(313)) {
                Timber.w(e, "Failed to notify UI of media failure");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(314)) {
                mHasExecuted = true;
            }
        }
    }

    public void onCardSideChange() {
        if (!ListenerUtil.mutListener.listen(318)) {
            mHasExecuted = false;
        }
    }

    public void processInefficientImage(Runnable onFailure) {
        if (!ListenerUtil.mutListener.listen(319)) {
            if (mHasShownInefficientImage) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(320)) {
            mHasShownInefficientImage = true;
        }
        if (!ListenerUtil.mutListener.listen(321)) {
            onFailure.run();
        }
    }
}
