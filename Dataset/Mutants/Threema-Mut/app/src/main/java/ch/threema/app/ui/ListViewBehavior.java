/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ListViewBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Logger logger = LoggerFactory.getLogger(ListViewBehavior.class);

    public ListViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (!ListenerUtil.mutListener.listen(45590)) {
            logger.debug("onDependentViewChanged");
        }
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        final int height = (ListenerUtil.mutListener.listen(45594) ? (parent.getHeight() % dependency.getHeight()) : (ListenerUtil.mutListener.listen(45593) ? (parent.getHeight() / dependency.getHeight()) : (ListenerUtil.mutListener.listen(45592) ? (parent.getHeight() * dependency.getHeight()) : (ListenerUtil.mutListener.listen(45591) ? (parent.getHeight() + dependency.getHeight()) : (parent.getHeight() - dependency.getHeight())))));
        if (height != layoutParams.height) {
            if (!ListenerUtil.mutListener.listen(45595)) {
                layoutParams.height = height;
            }
            if (!ListenerUtil.mutListener.listen(45596)) {
                child.setLayoutParams(layoutParams);
            }
            if (!ListenerUtil.mutListener.listen(45597)) {
                logger.debug("*** height: " + layoutParams.height);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, View child, @NonNull View dependency) {
        if (!ListenerUtil.mutListener.listen(45598)) {
            logger.debug("onDependentViewRemoved");
        }
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(45599)) {
            layoutParams.height = MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(45600)) {
            child.setLayoutParams(layoutParams);
        }
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }
}
