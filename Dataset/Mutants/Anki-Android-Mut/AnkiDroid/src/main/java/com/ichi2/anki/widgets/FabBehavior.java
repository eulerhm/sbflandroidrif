/**
 * *************************************************************************************
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.widgets;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Originally created by Paul Woitaschek (http://www.paul-woitaschek.de, woitaschek@posteo.de)
 * Defines the behavior for the floating action button. If the dependency is a Snackbar, move the
 * fab up.
 */
public class FabBehavior extends CoordinatorLayout.Behavior<FloatingActionsMenu> {

    private float mTranslationY;

    public FabBehavior() {
        super();
    }

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private static float getFabTranslationYForSnackbar(CoordinatorLayout parent, FloatingActionsMenu fab) {
        float minOffset = 0.0F;
        List<View> dependencies = parent.getDependencies(fab);
        int i = 0;
        if (!ListenerUtil.mutListener.listen(4151)) {
            {
                long _loopCounter97 = 0;
                for (int z = dependencies.size(); (ListenerUtil.mutListener.listen(4150) ? (i >= z) : (ListenerUtil.mutListener.listen(4149) ? (i <= z) : (ListenerUtil.mutListener.listen(4148) ? (i > z) : (ListenerUtil.mutListener.listen(4147) ? (i != z) : (ListenerUtil.mutListener.listen(4146) ? (i == z) : (i < z)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter97", ++_loopCounter97);
                    View view = dependencies.get(i);
                    if (!ListenerUtil.mutListener.listen(4145)) {
                        if ((ListenerUtil.mutListener.listen(4139) ? (view instanceof Snackbar.SnackbarLayout || parent.doViewsOverlap(fab, view)) : (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)))) {
                            if (!ListenerUtil.mutListener.listen(4144)) {
                                minOffset = Math.min(minOffset, (ListenerUtil.mutListener.listen(4143) ? (view.getTranslationY() % (float) view.getHeight()) : (ListenerUtil.mutListener.listen(4142) ? (view.getTranslationY() / (float) view.getHeight()) : (ListenerUtil.mutListener.listen(4141) ? (view.getTranslationY() * (float) view.getHeight()) : (ListenerUtil.mutListener.listen(4140) ? (view.getTranslationY() + (float) view.getHeight()) : (view.getTranslationY() - (float) view.getHeight()))))));
                            }
                        }
                    }
                }
            }
        }
        return minOffset;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull FloatingActionsMenu child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull FloatingActionsMenu fab, @NonNull View dependency) {
        if (!ListenerUtil.mutListener.listen(4162)) {
            if ((ListenerUtil.mutListener.listen(4152) ? (dependency instanceof Snackbar.SnackbarLayout || fab.getVisibility() == View.VISIBLE) : (dependency instanceof Snackbar.SnackbarLayout && fab.getVisibility() == View.VISIBLE))) {
                float translationY = getFabTranslationYForSnackbar(parent, fab);
                if (!ListenerUtil.mutListener.listen(4161)) {
                    if ((ListenerUtil.mutListener.listen(4157) ? (translationY >= this.mTranslationY) : (ListenerUtil.mutListener.listen(4156) ? (translationY <= this.mTranslationY) : (ListenerUtil.mutListener.listen(4155) ? (translationY > this.mTranslationY) : (ListenerUtil.mutListener.listen(4154) ? (translationY < this.mTranslationY) : (ListenerUtil.mutListener.listen(4153) ? (translationY == this.mTranslationY) : (translationY != this.mTranslationY))))))) {
                        if (!ListenerUtil.mutListener.listen(4158)) {
                            ViewCompat.animate(fab).cancel();
                        }
                        if (!ListenerUtil.mutListener.listen(4159)) {
                            fab.setTranslationY(translationY);
                        }
                        if (!ListenerUtil.mutListener.listen(4160)) {
                            this.mTranslationY = translationY;
                        }
                    }
                }
            }
        }
        return false;
    }
}
