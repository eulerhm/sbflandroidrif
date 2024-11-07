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
package ch.threema.app.ui;

import android.os.SystemClock;
import android.view.View;
import java.util.Map;
import java.util.WeakHashMap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A Debounced OnClickListener Rejects clicks that are too close together in time. This class is
 * safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 */
public abstract class DebouncedOnClickListener implements View.OnClickListener {

    private final long minimumInterval;

    private Map<View, Long> lastClickMap;

    /**
     *  Implement this in your subclass instead of onClick
     *
     *  @param v The view that was clicked
     */
    public abstract void onDebouncedClick(View v);

    /**
     *  The one and only constructor
     *
     *  @param minimumIntervalMsec The minimum allowed time between clicks - any click sooner than
     *  this after a previous click will be rejected
     */
    public DebouncedOnClickListener(long minimumIntervalMsec) {
        this.minimumInterval = minimumIntervalMsec;
        if (!ListenerUtil.mutListener.listen(44966)) {
            this.lastClickMap = new WeakHashMap<View, Long>();
        }
    }

    @Override
    public void onClick(View clickedView) {
        Long previousClickTimestamp = lastClickMap.get(clickedView);
        long currentTimestamp = SystemClock.uptimeMillis();
        if (!ListenerUtil.mutListener.listen(44967)) {
            lastClickMap.put(clickedView, currentTimestamp);
        }
        if (!ListenerUtil.mutListener.listen(44979)) {
            if ((ListenerUtil.mutListener.listen(44977) ? (previousClickTimestamp == null && ((ListenerUtil.mutListener.listen(44976) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) >= minimumInterval) : (ListenerUtil.mutListener.listen(44975) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) <= minimumInterval) : (ListenerUtil.mutListener.listen(44974) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) < minimumInterval) : (ListenerUtil.mutListener.listen(44973) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) != minimumInterval) : (ListenerUtil.mutListener.listen(44972) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) == minimumInterval) : ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) > minimumInterval)))))))) : (previousClickTimestamp == null || ((ListenerUtil.mutListener.listen(44976) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) >= minimumInterval) : (ListenerUtil.mutListener.listen(44975) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) <= minimumInterval) : (ListenerUtil.mutListener.listen(44974) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) < minimumInterval) : (ListenerUtil.mutListener.listen(44973) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) != minimumInterval) : (ListenerUtil.mutListener.listen(44972) ? ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) == minimumInterval) : ((ListenerUtil.mutListener.listen(44971) ? (currentTimestamp % previousClickTimestamp) : (ListenerUtil.mutListener.listen(44970) ? (currentTimestamp / previousClickTimestamp) : (ListenerUtil.mutListener.listen(44969) ? (currentTimestamp * previousClickTimestamp) : (ListenerUtil.mutListener.listen(44968) ? (currentTimestamp + previousClickTimestamp) : (currentTimestamp - previousClickTimestamp))))) > minimumInterval)))))))))) {
                if (!ListenerUtil.mutListener.listen(44978)) {
                    onDebouncedClick(clickedView);
                }
            }
        }
    }
}
