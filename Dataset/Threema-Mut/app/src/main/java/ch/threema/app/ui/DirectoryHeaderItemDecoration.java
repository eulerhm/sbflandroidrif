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

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectoryHeaderItemDecoration extends RecyclerView.ItemDecoration {

    private final int headerOffset;

    private final boolean sticky;

    private final HeaderCallback sectionCallback;

    private View headerView;

    private TextView header;

    public DirectoryHeaderItemDecoration(int headerHeight, boolean sticky, @NonNull HeaderCallback sectionCallback) {
        headerOffset = headerHeight;
        this.sticky = sticky;
        this.sectionCallback = sectionCallback;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (!ListenerUtil.mutListener.listen(45042)) {
            super.getItemOffsets(outRect, view, parent, state);
        }
        int pos = parent.getChildAdapterPosition(view);
        if (!ListenerUtil.mutListener.listen(45044)) {
            if (sectionCallback.isHeader(pos)) {
                if (!ListenerUtil.mutListener.listen(45043)) {
                    outRect.top = headerOffset;
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (!ListenerUtil.mutListener.listen(45045)) {
            super.onDrawOver(c, parent, state);
        }
        if (!ListenerUtil.mutListener.listen(45049)) {
            if (headerView == null) {
                if (!ListenerUtil.mutListener.listen(45046)) {
                    headerView = inflateHeaderView(parent);
                }
                if (!ListenerUtil.mutListener.listen(45047)) {
                    header = (TextView) headerView.findViewById(R.id.list_item_section_text);
                }
                if (!ListenerUtil.mutListener.listen(45048)) {
                    fixLayoutSize(headerView, parent);
                }
            }
        }
        CharSequence previousHeader = "";
        if (!ListenerUtil.mutListener.listen(45060)) {
            {
                long _loopCounter530 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(45059) ? (i >= parent.getChildCount()) : (ListenerUtil.mutListener.listen(45058) ? (i <= parent.getChildCount()) : (ListenerUtil.mutListener.listen(45057) ? (i > parent.getChildCount()) : (ListenerUtil.mutListener.listen(45056) ? (i != parent.getChildCount()) : (ListenerUtil.mutListener.listen(45055) ? (i == parent.getChildCount()) : (i < parent.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter530", ++_loopCounter530);
                    View child = parent.getChildAt(i);
                    final int position = parent.getChildAdapterPosition(child);
                    CharSequence title = sectionCallback.getHeaderText(position);
                    if (!ListenerUtil.mutListener.listen(45050)) {
                        header.setText(title);
                    }
                    if (!ListenerUtil.mutListener.listen(45054)) {
                        if ((ListenerUtil.mutListener.listen(45051) ? (!previousHeader.equals(title) && sectionCallback.isHeader(position)) : (!previousHeader.equals(title) || sectionCallback.isHeader(position)))) {
                            if (!ListenerUtil.mutListener.listen(45052)) {
                                drawHeader(c, child, headerView);
                            }
                            if (!ListenerUtil.mutListener.listen(45053)) {
                                previousHeader = title;
                            }
                        }
                    }
                }
            }
        }
    }

    private void drawHeader(Canvas c, View child, View headerView) {
        if (!ListenerUtil.mutListener.listen(45061)) {
            c.save();
        }
        if (!ListenerUtil.mutListener.listen(45072)) {
            if (sticky) {
                if (!ListenerUtil.mutListener.listen(45071)) {
                    c.translate(0, Math.max(0, (ListenerUtil.mutListener.listen(45070) ? (child.getTop() % headerView.getHeight()) : (ListenerUtil.mutListener.listen(45069) ? (child.getTop() / headerView.getHeight()) : (ListenerUtil.mutListener.listen(45068) ? (child.getTop() * headerView.getHeight()) : (ListenerUtil.mutListener.listen(45067) ? (child.getTop() + headerView.getHeight()) : (child.getTop() - headerView.getHeight())))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(45066)) {
                    c.translate(0, (ListenerUtil.mutListener.listen(45065) ? (child.getTop() % headerView.getHeight()) : (ListenerUtil.mutListener.listen(45064) ? (child.getTop() / headerView.getHeight()) : (ListenerUtil.mutListener.listen(45063) ? (child.getTop() * headerView.getHeight()) : (ListenerUtil.mutListener.listen(45062) ? (child.getTop() + headerView.getHeight()) : (child.getTop() - headerView.getHeight()))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45073)) {
            headerView.draw(c);
        }
        if (!ListenerUtil.mutListener.listen(45074)) {
            c.restore();
        }
    }

    private View inflateHeaderView(RecyclerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_directory_header, parent, false);
    }

    /**
     *  Measures the header view to make sure its size is greater than 0 and will be drawn
     *  https://yoda.entelect.co.za/view/9627/how-to-android-recyclerview-item-decorations
     */
    private void fixLayoutSize(View view, ViewGroup parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);
        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), view.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), view.getLayoutParams().height);
        if (!ListenerUtil.mutListener.listen(45075)) {
            view.measure(childWidth, childHeight);
        }
        if (!ListenerUtil.mutListener.listen(45076)) {
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public interface HeaderCallback {

        boolean isHeader(int position);

        CharSequence getHeaderText(int position);
    }
}
