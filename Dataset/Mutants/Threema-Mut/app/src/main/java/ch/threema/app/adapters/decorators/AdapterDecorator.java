/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.adapters.decorators;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import androidx.annotation.AnyThread;
import ch.threema.app.ui.listitemholder.AbstractListItemHolder;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class AdapterDecorator {

    private final Context context;

    private transient ListView inListView = null;

    protected AdapterDecorator(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return this.context;
    }

    public final void decorate(AbstractListItemHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(7327)) {
            this.configure(holder, position);
        }
    }

    protected boolean showHide(View view, boolean show) {
        if (!ListenerUtil.mutListener.listen(7331)) {
            if (view != null) {
                if (!ListenerUtil.mutListener.listen(7330)) {
                    if (show) {
                        if (!ListenerUtil.mutListener.listen(7329)) {
                            view.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7328)) {
                            view.setVisibility(View.GONE);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @AnyThread
    protected void invalidate(final AbstractListItemHolder holder, final int position) {
        if (!ListenerUtil.mutListener.listen(7332)) {
            RuntimeUtil.runOnUiThread(() -> {
                if (holder != null && holder.position == position) {
                    configure(holder, position);
                }
            });
        }
    }

    protected abstract void configure(AbstractListItemHolder holder, int position);

    public void setInListView(ListView inListView) {
        if (!ListenerUtil.mutListener.listen(7333)) {
            this.inListView = inListView;
        }
    }

    protected boolean isInChoiceMode() {
        return (ListenerUtil.mutListener.listen(7335) ? (this.inListView != null || ((ListenerUtil.mutListener.listen(7334) ? (this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE && this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE_MODAL) : (this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE || this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE_MODAL)))) : (this.inListView != null && ((ListenerUtil.mutListener.listen(7334) ? (this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE && this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE_MODAL) : (this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE || this.inListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE_MODAL)))));
    }
}
