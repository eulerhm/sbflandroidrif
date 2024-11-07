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
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.GridView;
import androidx.appcompat.view.ContextThemeWrapper;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FastScrollGridView extends GridView implements AbsListView.OnScrollListener {

    private ScrollListener scrollListener;

    private int lastFirstVisibleItem = -1;

    private final Handler fastScrollRemoveHandler = new Handler();

    private final Runnable fastScrollRemoveTask = () -> RuntimeUtil.runOnUiThread(() -> setFastScrollAlwaysVisible(false));

    public FastScrollGridView(Context context, AttributeSet attrs) {
        super(new ContextThemeWrapper(context, ConfigUtils.getAppTheme(context) == ConfigUtils.THEME_DARK ? R.style.Threema_MediaGallery_FastScroll_Dark : R.style.Threema_MediaGallery_FastScroll), attrs);
        if (!ListenerUtil.mutListener.listen(45117)) {
            setOnScrollListener(this);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!ListenerUtil.mutListener.listen(45122)) {
            if (scrollState == SCROLL_STATE_IDLE) {
                if (!ListenerUtil.mutListener.listen(45120)) {
                    fastScrollRemoveHandler.removeCallbacks(fastScrollRemoveTask);
                }
                if (!ListenerUtil.mutListener.listen(45121)) {
                    fastScrollRemoveHandler.postDelayed(fastScrollRemoveTask, 1000);
                }
            } else if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                if (!ListenerUtil.mutListener.listen(45118)) {
                    fastScrollRemoveHandler.removeCallbacks(fastScrollRemoveTask);
                }
                if (!ListenerUtil.mutListener.listen(45119)) {
                    setFastScrollAlwaysVisible(true);
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!ListenerUtil.mutListener.listen(45131)) {
            if ((ListenerUtil.mutListener.listen(45127) ? (firstVisibleItem >= this.lastFirstVisibleItem) : (ListenerUtil.mutListener.listen(45126) ? (firstVisibleItem <= this.lastFirstVisibleItem) : (ListenerUtil.mutListener.listen(45125) ? (firstVisibleItem > this.lastFirstVisibleItem) : (ListenerUtil.mutListener.listen(45124) ? (firstVisibleItem < this.lastFirstVisibleItem) : (ListenerUtil.mutListener.listen(45123) ? (firstVisibleItem == this.lastFirstVisibleItem) : (firstVisibleItem != this.lastFirstVisibleItem))))))) {
                if (!ListenerUtil.mutListener.listen(45129)) {
                    if (this.scrollListener != null) {
                        if (!ListenerUtil.mutListener.listen(45128)) {
                            this.scrollListener.onScroll(firstVisibleItem);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(45130)) {
                    this.lastFirstVisibleItem = firstVisibleItem;
                }
            }
        }
    }

    public void setScrollListener(ScrollListener scrollListener) {
        if (!ListenerUtil.mutListener.listen(45132)) {
            this.scrollListener = scrollListener;
        }
    }

    public interface ScrollListener {

        void onScroll(int firstVisibleItem);
    }
}
