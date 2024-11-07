/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class ConversationListView extends ListView {

    public ConversationListView(Context context) {
        super(context);
    }

    public ConversationListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean isLastItemVisible() {
        // returns true if the last item in this list view is visible
        final int lastPos = getLastVisiblePosition();
        final int countPos = getCount();
        return ((ListenerUtil.mutListener.listen(44923) ? (lastPos >= (ListenerUtil.mutListener.listen(44918) ? (countPos % 1) : (ListenerUtil.mutListener.listen(44917) ? (countPos / 1) : (ListenerUtil.mutListener.listen(44916) ? (countPos * 1) : (ListenerUtil.mutListener.listen(44915) ? (countPos + 1) : (countPos - 1)))))) : (ListenerUtil.mutListener.listen(44922) ? (lastPos <= (ListenerUtil.mutListener.listen(44918) ? (countPos % 1) : (ListenerUtil.mutListener.listen(44917) ? (countPos / 1) : (ListenerUtil.mutListener.listen(44916) ? (countPos * 1) : (ListenerUtil.mutListener.listen(44915) ? (countPos + 1) : (countPos - 1)))))) : (ListenerUtil.mutListener.listen(44921) ? (lastPos > (ListenerUtil.mutListener.listen(44918) ? (countPos % 1) : (ListenerUtil.mutListener.listen(44917) ? (countPos / 1) : (ListenerUtil.mutListener.listen(44916) ? (countPos * 1) : (ListenerUtil.mutListener.listen(44915) ? (countPos + 1) : (countPos - 1)))))) : (ListenerUtil.mutListener.listen(44920) ? (lastPos < (ListenerUtil.mutListener.listen(44918) ? (countPos % 1) : (ListenerUtil.mutListener.listen(44917) ? (countPos / 1) : (ListenerUtil.mutListener.listen(44916) ? (countPos * 1) : (ListenerUtil.mutListener.listen(44915) ? (countPos + 1) : (countPos - 1)))))) : (ListenerUtil.mutListener.listen(44919) ? (lastPos != (ListenerUtil.mutListener.listen(44918) ? (countPos % 1) : (ListenerUtil.mutListener.listen(44917) ? (countPos / 1) : (ListenerUtil.mutListener.listen(44916) ? (countPos * 1) : (ListenerUtil.mutListener.listen(44915) ? (countPos + 1) : (countPos - 1)))))) : (lastPos == (ListenerUtil.mutListener.listen(44918) ? (countPos % 1) : (ListenerUtil.mutListener.listen(44917) ? (countPos / 1) : (ListenerUtil.mutListener.listen(44916) ? (countPos * 1) : (ListenerUtil.mutListener.listen(44915) ? (countPos + 1) : (countPos - 1))))))))))));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!ListenerUtil.mutListener.listen(44924)) {
            // called when size of view changes (e.g. soft keyboard appears or screen is rotated)
            super.onSizeChanged(w, h, oldw, oldh);
        }
        if (!ListenerUtil.mutListener.listen(44926)) {
            if (isLastItemVisible()) {
                if (!ListenerUtil.mutListener.listen(44925)) {
                    // only scroll to end of list if last item is visible - otherwise stay put
                    setSelection(Integer.MAX_VALUE);
                }
            }
        }
    }
}
