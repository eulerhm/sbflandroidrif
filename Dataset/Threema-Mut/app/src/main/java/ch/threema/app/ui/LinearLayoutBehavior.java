/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LinearLayoutBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private static final Logger logger = LoggerFactory.getLogger(LinearLayoutBehavior.class);

    public LinearLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        if (!ListenerUtil.mutListener.listen(45581)) {
            logger.debug("onDependentViewChanged");
        }
        float translationY = Math.min(0, (ListenerUtil.mutListener.listen(45585) ? (dependency.getTranslationY() % dependency.getHeight()) : (ListenerUtil.mutListener.listen(45584) ? (dependency.getTranslationY() / dependency.getHeight()) : (ListenerUtil.mutListener.listen(45583) ? (dependency.getTranslationY() * dependency.getHeight()) : (ListenerUtil.mutListener.listen(45582) ? (dependency.getTranslationY() + dependency.getHeight()) : (dependency.getTranslationY() - dependency.getHeight()))))));
        if (!ListenerUtil.mutListener.listen(45586)) {
            child.setTranslationY(translationY);
        }
        if (!ListenerUtil.mutListener.listen(45587)) {
            logger.debug("TranslationY: " + translationY);
        }
        return true;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, LinearLayout child, View dependency) {
        if (!ListenerUtil.mutListener.listen(45588)) {
            logger.debug("onDependentViewRemoved");
        }
        if (!ListenerUtil.mutListener.listen(45589)) {
            child.setTranslationY(0);
        }
    }
}
