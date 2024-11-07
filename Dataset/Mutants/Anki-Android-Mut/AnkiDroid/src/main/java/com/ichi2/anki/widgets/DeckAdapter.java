/**
 * *************************************************************************************
 *  Copyright (c) 2015 Houssam Salem <houssam.salem.au@gmail.com>                        *
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
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ichi2.anki.R;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.sched.AbstractDeckTreeNode;
import com.ichi2.utils.FilterResultsUtils;
import com.ichi2.libanki.sched.Counts;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static android.view.View.IMPORTANT_FOR_ACCESSIBILITY_NO;
import static android.view.View.IMPORTANT_FOR_ACCESSIBILITY_YES;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckAdapter<T extends AbstractDeckTreeNode<T>> extends RecyclerView.Adapter<DeckAdapter.ViewHolder> implements Filterable {

    /* Make the selected deck roughly half transparent if there is a background */
    public static final double SELECTED_DECK_ALPHA_AGAINST_BACKGROUND = 0.45;

    private final LayoutInflater mLayoutInflater;

    private final List<T> mDeckList;

    /**
     * A subset of mDeckList (currently displayed)
     */
    private final List<AbstractDeckTreeNode<?>> mCurrentDeckList = new ArrayList<>();

    private final int mZeroCountColor;

    private final int mNewCountColor;

    private final int mLearnCountColor;

    private final int mReviewCountColor;

    private final int mRowCurrentDrawable;

    private final int mDeckNameDefaultColor;

    private final int mDeckNameDynColor;

    private final Drawable mExpandImage;

    private final Drawable mCollapseImage;

    private final Drawable mNoExpander = new ColorDrawable(Color.TRANSPARENT);

    // Listeners
    private View.OnClickListener mDeckClickListener;

    private View.OnClickListener mDeckExpanderClickListener;

    private View.OnLongClickListener mDeckLongClickListener;

    private View.OnClickListener mCountsClickListener;

    private Collection mCol;

    // Totals accumulated as each deck is processed
    private int mNew;

    private int mLrn;

    private int mRev;

    private boolean mNumbersComputed;

    // Flags
    private boolean mHasSubdecks;

    // Whether we have a background (so some items should be partially transparent).
    private boolean mPartiallyTransparentForBackground;

    // ViewHolder class to save inflated views for recycling
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout deckLayout;

        public final LinearLayout countsLayout;

        public final ImageButton deckExpander;

        public final ImageButton indentView;

        public final TextView deckName;

        public final TextView deckNew;

        public final TextView deckLearn;

        public final TextView deckRev;

        public ViewHolder(View v) {
            super(v);
            deckLayout = v.findViewById(R.id.DeckPickerHoriz);
            countsLayout = v.findViewById(R.id.counts_layout);
            deckExpander = v.findViewById(R.id.deckpicker_expander);
            indentView = v.findViewById(R.id.deckpicker_indent);
            deckName = v.findViewById(R.id.deckpicker_name);
            deckNew = v.findViewById(R.id.deckpicker_new);
            deckLearn = v.findViewById(R.id.deckpicker_lrn);
            deckRev = v.findViewById(R.id.deckpicker_rev);
        }
    }

    public DeckAdapter(LayoutInflater layoutInflater, Context context) {
        mLayoutInflater = layoutInflater;
        mDeckList = new ArrayList<>((mCol == null) ? 10 : mCol.getDecks().count());
        // Get the colors from the theme attributes
        int[] attrs = new int[] { R.attr.zeroCountColor, R.attr.newCountColor, R.attr.learnCountColor, R.attr.reviewCountColor, R.attr.currentDeckBackground, android.R.attr.textColor, R.attr.dynDeckColor, R.attr.expandRef, R.attr.collapseRef };
        TypedArray ta = context.obtainStyledAttributes(attrs);
        mZeroCountColor = ta.getColor(0, ContextCompat.getColor(context, R.color.black));
        mNewCountColor = ta.getColor(1, ContextCompat.getColor(context, R.color.black));
        mLearnCountColor = ta.getColor(2, ContextCompat.getColor(context, R.color.black));
        mReviewCountColor = ta.getColor(3, ContextCompat.getColor(context, R.color.black));
        mRowCurrentDrawable = ta.getResourceId(4, 0);
        mDeckNameDefaultColor = ta.getColor(5, ContextCompat.getColor(context, R.color.black));
        mDeckNameDynColor = ta.getColor(6, ContextCompat.getColor(context, R.color.material_blue_A700));
        mExpandImage = ta.getDrawable(7);
        mCollapseImage = ta.getDrawable(8);
        if (!ListenerUtil.mutListener.listen(3939)) {
            ta.recycle();
        }
    }

    public void setDeckClickListener(View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(3940)) {
            mDeckClickListener = listener;
        }
    }

    public void setCountsClickListener(View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(3941)) {
            mCountsClickListener = listener;
        }
    }

    public void setDeckExpanderClickListener(View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(3942)) {
            mDeckExpanderClickListener = listener;
        }
    }

    public void setDeckLongClickListener(View.OnLongClickListener listener) {
        if (!ListenerUtil.mutListener.listen(3943)) {
            mDeckLongClickListener = listener;
        }
    }

    /**
     * Sets whether the control should have partial transparency to allow a background to be seen
     */
    public void enablePartialTransparencyForBackground(boolean isTransparent) {
        if (!ListenerUtil.mutListener.listen(3944)) {
            mPartiallyTransparentForBackground = isTransparent;
        }
    }

    /**
     * Consume a list of {@link AbstractDeckTreeNode}s to render a new deck list.
     * @param filter The string to filter the deck by
     */
    public void buildDeckList(List<T> nodes, Collection col, @Nullable CharSequence filter) {
        if (!ListenerUtil.mutListener.listen(3945)) {
            mCol = col;
        }
        if (!ListenerUtil.mutListener.listen(3946)) {
            mDeckList.clear();
        }
        if (!ListenerUtil.mutListener.listen(3947)) {
            mCurrentDeckList.clear();
        }
        if (!ListenerUtil.mutListener.listen(3948)) {
            mNew = mLrn = mRev = 0;
        }
        if (!ListenerUtil.mutListener.listen(3949)) {
            mNumbersComputed = true;
        }
        if (!ListenerUtil.mutListener.listen(3950)) {
            mHasSubdecks = false;
        }
        if (!ListenerUtil.mutListener.listen(3951)) {
            processNodes(nodes);
        }
        if (!ListenerUtil.mutListener.listen(3952)) {
            // Filtering performs notifyDataSetChanged after the async work is complete
            getFilter().filter(filter);
        }
    }

    public AbstractDeckTreeNode<?> getNodeByDid(long did) {
        int pos = findDeckPosition(did);
        return getDeckList().get(pos);
    }

    @NonNull
    @Override
    public DeckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.deck_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Update views for this node
        AbstractDeckTreeNode<?> node = mCurrentDeckList.get(position);
        // Set the expander icon and padding according to whether or not there are any subdecks
        RelativeLayout deckLayout = holder.deckLayout;
        int rightPadding = (int) deckLayout.getResources().getDimension(R.dimen.deck_picker_right_padding);
        if (!ListenerUtil.mutListener.listen(3958)) {
            if (mHasSubdecks) {
                int smallPadding = (int) deckLayout.getResources().getDimension(R.dimen.deck_picker_left_padding_small);
                if (!ListenerUtil.mutListener.listen(3955)) {
                    deckLayout.setPadding(smallPadding, 0, rightPadding, 0);
                }
                if (!ListenerUtil.mutListener.listen(3956)) {
                    holder.deckExpander.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3957)) {
                    // Create the correct expander for this deck
                    setDeckExpander(holder.deckExpander, holder.indentView, node);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3953)) {
                    holder.deckExpander.setVisibility(View.GONE);
                }
                int normalPadding = (int) deckLayout.getResources().getDimension(R.dimen.deck_picker_left_padding);
                if (!ListenerUtil.mutListener.listen(3954)) {
                    deckLayout.setPadding(normalPadding, 0, rightPadding, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3962)) {
            if (node.hasChildren()) {
                if (!ListenerUtil.mutListener.listen(3960)) {
                    holder.deckExpander.setTag(node.getDid());
                }
                if (!ListenerUtil.mutListener.listen(3961)) {
                    holder.deckExpander.setOnClickListener(mDeckExpanderClickListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3959)) {
                    holder.deckExpander.setClickable(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3963)) {
            holder.deckLayout.setBackgroundResource(mRowCurrentDrawable);
        }
        if (!ListenerUtil.mutListener.listen(3969)) {
            // Set background colour. The current deck has its own color
            if (isCurrentlySelectedDeck(node)) {
                if (!ListenerUtil.mutListener.listen(3966)) {
                    holder.deckLayout.setBackgroundResource(mRowCurrentDrawable);
                }
                if (!ListenerUtil.mutListener.listen(3968)) {
                    if (mPartiallyTransparentForBackground) {
                        if (!ListenerUtil.mutListener.listen(3967)) {
                            setBackgroundAlpha(holder.deckLayout, SELECTED_DECK_ALPHA_AGAINST_BACKGROUND);
                        }
                    }
                }
            } else {
                // Ripple effect
                int[] attrs = new int[] { android.R.attr.selectableItemBackground };
                TypedArray ta = holder.deckLayout.getContext().obtainStyledAttributes(attrs);
                if (!ListenerUtil.mutListener.listen(3964)) {
                    holder.deckLayout.setBackgroundResource(ta.getResourceId(0, 0));
                }
                if (!ListenerUtil.mutListener.listen(3965)) {
                    ta.recycle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3970)) {
            // Set deck name and colour. Filtered decks have their own colour
            holder.deckName.setText(node.getLastDeckNameComponent());
        }
        if (!ListenerUtil.mutListener.listen(3973)) {
            if (mCol.getDecks().isDyn(node.getDid())) {
                if (!ListenerUtil.mutListener.listen(3972)) {
                    holder.deckName.setTextColor(mDeckNameDynColor);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3971)) {
                    holder.deckName.setTextColor(mDeckNameDefaultColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3995)) {
            // Set the card counts and their colors
            if (node.shouldDisplayCounts()) {
                if (!ListenerUtil.mutListener.listen(3974)) {
                    holder.deckNew.setText(String.valueOf(node.getNewCount()));
                }
                if (!ListenerUtil.mutListener.listen(3980)) {
                    holder.deckNew.setTextColor(((ListenerUtil.mutListener.listen(3979) ? (node.getNewCount() >= 0) : (ListenerUtil.mutListener.listen(3978) ? (node.getNewCount() <= 0) : (ListenerUtil.mutListener.listen(3977) ? (node.getNewCount() > 0) : (ListenerUtil.mutListener.listen(3976) ? (node.getNewCount() < 0) : (ListenerUtil.mutListener.listen(3975) ? (node.getNewCount() != 0) : (node.getNewCount() == 0))))))) ? mZeroCountColor : mNewCountColor);
                }
                if (!ListenerUtil.mutListener.listen(3981)) {
                    holder.deckLearn.setText(String.valueOf(node.getLrnCount()));
                }
                if (!ListenerUtil.mutListener.listen(3987)) {
                    holder.deckLearn.setTextColor(((ListenerUtil.mutListener.listen(3986) ? (node.getLrnCount() >= 0) : (ListenerUtil.mutListener.listen(3985) ? (node.getLrnCount() <= 0) : (ListenerUtil.mutListener.listen(3984) ? (node.getLrnCount() > 0) : (ListenerUtil.mutListener.listen(3983) ? (node.getLrnCount() < 0) : (ListenerUtil.mutListener.listen(3982) ? (node.getLrnCount() != 0) : (node.getLrnCount() == 0))))))) ? mZeroCountColor : mLearnCountColor);
                }
                if (!ListenerUtil.mutListener.listen(3988)) {
                    holder.deckRev.setText(String.valueOf(node.getRevCount()));
                }
                if (!ListenerUtil.mutListener.listen(3994)) {
                    holder.deckRev.setTextColor(((ListenerUtil.mutListener.listen(3993) ? (node.getRevCount() >= 0) : (ListenerUtil.mutListener.listen(3992) ? (node.getRevCount() <= 0) : (ListenerUtil.mutListener.listen(3991) ? (node.getRevCount() > 0) : (ListenerUtil.mutListener.listen(3990) ? (node.getRevCount() < 0) : (ListenerUtil.mutListener.listen(3989) ? (node.getRevCount() != 0) : (node.getRevCount() == 0))))))) ? mZeroCountColor : mReviewCountColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3996)) {
            // Store deck ID in layout's tag for easy retrieval in our click listeners
            holder.deckLayout.setTag(node.getDid());
        }
        if (!ListenerUtil.mutListener.listen(3997)) {
            holder.countsLayout.setTag(node.getDid());
        }
        if (!ListenerUtil.mutListener.listen(3998)) {
            // Set click listeners
            holder.deckLayout.setOnClickListener(mDeckClickListener);
        }
        if (!ListenerUtil.mutListener.listen(3999)) {
            holder.deckLayout.setOnLongClickListener(mDeckLongClickListener);
        }
        if (!ListenerUtil.mutListener.listen(4000)) {
            holder.countsLayout.setOnClickListener(mCountsClickListener);
        }
    }

    private void setBackgroundAlpha(View view, @SuppressWarnings("SameParameterValue") double alphaPercentage) {
        Drawable background = view.getBackground().mutate();
        if (!ListenerUtil.mutListener.listen(4005)) {
            background.setAlpha((int) ((ListenerUtil.mutListener.listen(4004) ? (255 % alphaPercentage) : (ListenerUtil.mutListener.listen(4003) ? (255 / alphaPercentage) : (ListenerUtil.mutListener.listen(4002) ? (255 - alphaPercentage) : (ListenerUtil.mutListener.listen(4001) ? (255 + alphaPercentage) : (255 * alphaPercentage)))))));
        }
        if (!ListenerUtil.mutListener.listen(4006)) {
            view.setBackground(background);
        }
    }

    private boolean isCurrentlySelectedDeck(AbstractDeckTreeNode<?> node) {
        return node.getDid() == mCol.getDecks().current().optLong("id");
    }

    @Override
    public int getItemCount() {
        return mCurrentDeckList.size();
    }

    private void setDeckExpander(ImageButton expander, ImageButton indent, AbstractDeckTreeNode<?> node) {
        boolean collapsed = mCol.getDecks().get(node.getDid()).optBoolean("collapsed", false);
        if (!ListenerUtil.mutListener.listen(4015)) {
            // Apply the correct expand/collapse drawable
            if (node.hasChildren()) {
                if (!ListenerUtil.mutListener.listen(4009)) {
                    expander.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
                }
                if (!ListenerUtil.mutListener.listen(4014)) {
                    if (collapsed) {
                        if (!ListenerUtil.mutListener.listen(4012)) {
                            expander.setImageDrawable(mExpandImage);
                        }
                        if (!ListenerUtil.mutListener.listen(4013)) {
                            expander.setContentDescription(expander.getContext().getString(R.string.expand));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4010)) {
                            expander.setImageDrawable(mCollapseImage);
                        }
                        if (!ListenerUtil.mutListener.listen(4011)) {
                            expander.setContentDescription(expander.getContext().getString(R.string.collapse));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4007)) {
                    expander.setImageDrawable(mNoExpander);
                }
                if (!ListenerUtil.mutListener.listen(4008)) {
                    expander.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO);
                }
            }
        }
        // Add some indenting for each nested level
        int width = (ListenerUtil.mutListener.listen(4019) ? ((int) indent.getResources().getDimension(R.dimen.keyline_1) % node.getDepth()) : (ListenerUtil.mutListener.listen(4018) ? ((int) indent.getResources().getDimension(R.dimen.keyline_1) / node.getDepth()) : (ListenerUtil.mutListener.listen(4017) ? ((int) indent.getResources().getDimension(R.dimen.keyline_1) - node.getDepth()) : (ListenerUtil.mutListener.listen(4016) ? ((int) indent.getResources().getDimension(R.dimen.keyline_1) + node.getDepth()) : ((int) indent.getResources().getDimension(R.dimen.keyline_1) * node.getDepth())))));
        if (!ListenerUtil.mutListener.listen(4020)) {
            indent.setMinimumWidth(width);
        }
    }

    private void processNodes(List<T> nodes) {
        if (!ListenerUtil.mutListener.listen(4051)) {
            {
                long _loopCounter93 = 0;
                for (T node : nodes) {
                    ListenerUtil.loopListener.listen("_loopCounter93", ++_loopCounter93);
                    if (!ListenerUtil.mutListener.listen(4034)) {
                        // We don't hide it if it's the only deck or if it has sub-decks.
                        if ((ListenerUtil.mutListener.listen(4032) ? ((ListenerUtil.mutListener.listen(4031) ? ((ListenerUtil.mutListener.listen(4025) ? (node.getDid() >= 1) : (ListenerUtil.mutListener.listen(4024) ? (node.getDid() <= 1) : (ListenerUtil.mutListener.listen(4023) ? (node.getDid() > 1) : (ListenerUtil.mutListener.listen(4022) ? (node.getDid() < 1) : (ListenerUtil.mutListener.listen(4021) ? (node.getDid() != 1) : (node.getDid() == 1)))))) || (ListenerUtil.mutListener.listen(4030) ? (nodes.size() >= 1) : (ListenerUtil.mutListener.listen(4029) ? (nodes.size() <= 1) : (ListenerUtil.mutListener.listen(4028) ? (nodes.size() < 1) : (ListenerUtil.mutListener.listen(4027) ? (nodes.size() != 1) : (ListenerUtil.mutListener.listen(4026) ? (nodes.size() == 1) : (nodes.size() > 1))))))) : ((ListenerUtil.mutListener.listen(4025) ? (node.getDid() >= 1) : (ListenerUtil.mutListener.listen(4024) ? (node.getDid() <= 1) : (ListenerUtil.mutListener.listen(4023) ? (node.getDid() > 1) : (ListenerUtil.mutListener.listen(4022) ? (node.getDid() < 1) : (ListenerUtil.mutListener.listen(4021) ? (node.getDid() != 1) : (node.getDid() == 1)))))) && (ListenerUtil.mutListener.listen(4030) ? (nodes.size() >= 1) : (ListenerUtil.mutListener.listen(4029) ? (nodes.size() <= 1) : (ListenerUtil.mutListener.listen(4028) ? (nodes.size() < 1) : (ListenerUtil.mutListener.listen(4027) ? (nodes.size() != 1) : (ListenerUtil.mutListener.listen(4026) ? (nodes.size() == 1) : (nodes.size() > 1)))))))) || !node.hasChildren()) : ((ListenerUtil.mutListener.listen(4031) ? ((ListenerUtil.mutListener.listen(4025) ? (node.getDid() >= 1) : (ListenerUtil.mutListener.listen(4024) ? (node.getDid() <= 1) : (ListenerUtil.mutListener.listen(4023) ? (node.getDid() > 1) : (ListenerUtil.mutListener.listen(4022) ? (node.getDid() < 1) : (ListenerUtil.mutListener.listen(4021) ? (node.getDid() != 1) : (node.getDid() == 1)))))) || (ListenerUtil.mutListener.listen(4030) ? (nodes.size() >= 1) : (ListenerUtil.mutListener.listen(4029) ? (nodes.size() <= 1) : (ListenerUtil.mutListener.listen(4028) ? (nodes.size() < 1) : (ListenerUtil.mutListener.listen(4027) ? (nodes.size() != 1) : (ListenerUtil.mutListener.listen(4026) ? (nodes.size() == 1) : (nodes.size() > 1))))))) : ((ListenerUtil.mutListener.listen(4025) ? (node.getDid() >= 1) : (ListenerUtil.mutListener.listen(4024) ? (node.getDid() <= 1) : (ListenerUtil.mutListener.listen(4023) ? (node.getDid() > 1) : (ListenerUtil.mutListener.listen(4022) ? (node.getDid() < 1) : (ListenerUtil.mutListener.listen(4021) ? (node.getDid() != 1) : (node.getDid() == 1)))))) && (ListenerUtil.mutListener.listen(4030) ? (nodes.size() >= 1) : (ListenerUtil.mutListener.listen(4029) ? (nodes.size() <= 1) : (ListenerUtil.mutListener.listen(4028) ? (nodes.size() < 1) : (ListenerUtil.mutListener.listen(4027) ? (nodes.size() != 1) : (ListenerUtil.mutListener.listen(4026) ? (nodes.size() == 1) : (nodes.size() > 1)))))))) && !node.hasChildren()))) {
                            if (!ListenerUtil.mutListener.listen(4033)) {
                                if (mCol.getDb().queryScalar("select 1 from cards where did = 1") == 0) {
                                    continue;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4037)) {
                        {
                            long _loopCounter92 = 0;
                            // If any of this node's parents are collapsed, don't add it to the deck list
                            for (Deck parent : mCol.getDecks().parents(node.getDid())) {
                                ListenerUtil.loopListener.listen("_loopCounter92", ++_loopCounter92);
                                if (!ListenerUtil.mutListener.listen(4035)) {
                                    // If a deck has a parent it means it's a subdeck so set a flag
                                    mHasSubdecks = true;
                                }
                                if (!ListenerUtil.mutListener.listen(4036)) {
                                    if (parent.optBoolean("collapsed")) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4038)) {
                        mDeckList.add(node);
                    }
                    if (!ListenerUtil.mutListener.listen(4039)) {
                        mCurrentDeckList.add(node);
                    }
                    if (!ListenerUtil.mutListener.listen(4049)) {
                        // Add this node's counts to the totals if it's a parent deck
                        if ((ListenerUtil.mutListener.listen(4044) ? (node.getDepth() >= 0) : (ListenerUtil.mutListener.listen(4043) ? (node.getDepth() <= 0) : (ListenerUtil.mutListener.listen(4042) ? (node.getDepth() > 0) : (ListenerUtil.mutListener.listen(4041) ? (node.getDepth() < 0) : (ListenerUtil.mutListener.listen(4040) ? (node.getDepth() != 0) : (node.getDepth() == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(4048)) {
                                if (node.shouldDisplayCounts()) {
                                    if (!ListenerUtil.mutListener.listen(4045)) {
                                        mNew += node.getNewCount();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4046)) {
                                        mLrn += node.getLrnCount();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4047)) {
                                        mRev += node.getRevCount();
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4050)) {
                        // Process sub-decks
                        processNodes(node.getChildren());
                    }
                }
            }
        }
    }

    /**
     * Return the position of the deck in the deck list. If the deck is a child of a collapsed deck
     * (i.e., not visible in the deck list), then the position of the parent deck is returned instead.
     *
     * An invalid deck ID will return position 0.
     */
    public int findDeckPosition(long did) {
        {
            long _loopCounter94 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(4061) ? (i >= mCurrentDeckList.size()) : (ListenerUtil.mutListener.listen(4060) ? (i <= mCurrentDeckList.size()) : (ListenerUtil.mutListener.listen(4059) ? (i > mCurrentDeckList.size()) : (ListenerUtil.mutListener.listen(4058) ? (i != mCurrentDeckList.size()) : (ListenerUtil.mutListener.listen(4057) ? (i == mCurrentDeckList.size()) : (i < mCurrentDeckList.size())))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter94", ++_loopCounter94);
                if ((ListenerUtil.mutListener.listen(4056) ? (mCurrentDeckList.get(i).getDid() >= did) : (ListenerUtil.mutListener.listen(4055) ? (mCurrentDeckList.get(i).getDid() <= did) : (ListenerUtil.mutListener.listen(4054) ? (mCurrentDeckList.get(i).getDid() > did) : (ListenerUtil.mutListener.listen(4053) ? (mCurrentDeckList.get(i).getDid() < did) : (ListenerUtil.mutListener.listen(4052) ? (mCurrentDeckList.get(i).getDid() != did) : (mCurrentDeckList.get(i).getDid() == did))))))) {
                    return i;
                }
            }
        }
        // If the deck is not in our list, we search again using the immediate parent
        List<Deck> parents = mCol.getDecks().parents(did);
        if ((ListenerUtil.mutListener.listen(4066) ? (parents.size() >= 0) : (ListenerUtil.mutListener.listen(4065) ? (parents.size() <= 0) : (ListenerUtil.mutListener.listen(4064) ? (parents.size() > 0) : (ListenerUtil.mutListener.listen(4063) ? (parents.size() < 0) : (ListenerUtil.mutListener.listen(4062) ? (parents.size() != 0) : (parents.size() == 0))))))) {
            return 0;
        } else {
            return findDeckPosition(parents.get((ListenerUtil.mutListener.listen(4070) ? (parents.size() % 1) : (ListenerUtil.mutListener.listen(4069) ? (parents.size() / 1) : (ListenerUtil.mutListener.listen(4068) ? (parents.size() * 1) : (ListenerUtil.mutListener.listen(4067) ? (parents.size() + 1) : (parents.size() - 1)))))).optLong("id", 0));
        }
    }

    @Nullable
    public Integer getEta() {
        if (mNumbersComputed) {
            return mCol.getSched().eta(new Counts(mNew, mLrn, mRev));
        } else {
            return null;
        }
    }

    @Nullable
    public Integer getDue() {
        if (mNumbersComputed) {
            return (ListenerUtil.mutListener.listen(4078) ? ((ListenerUtil.mutListener.listen(4074) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(4073) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(4072) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(4071) ? (mNew - mLrn) : (mNew + mLrn))))) % mRev) : (ListenerUtil.mutListener.listen(4077) ? ((ListenerUtil.mutListener.listen(4074) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(4073) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(4072) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(4071) ? (mNew - mLrn) : (mNew + mLrn))))) / mRev) : (ListenerUtil.mutListener.listen(4076) ? ((ListenerUtil.mutListener.listen(4074) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(4073) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(4072) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(4071) ? (mNew - mLrn) : (mNew + mLrn))))) * mRev) : (ListenerUtil.mutListener.listen(4075) ? ((ListenerUtil.mutListener.listen(4074) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(4073) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(4072) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(4071) ? (mNew - mLrn) : (mNew + mLrn))))) - mRev) : ((ListenerUtil.mutListener.listen(4074) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(4073) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(4072) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(4071) ? (mNew - mLrn) : (mNew + mLrn))))) + mRev)))));
        } else {
            return null;
        }
    }

    private List<AbstractDeckTreeNode<?>> getDeckList() {
        return mCurrentDeckList;
    }

    @Override
    public Filter getFilter() {
        return new DeckFilter();
    }

    private class DeckFilter extends Filter {

        @NonNull
        private final ArrayList<AbstractDeckTreeNode<?>> mFilteredDecks = new ArrayList<>();

        private DeckFilter() {
            super();
        }

        private List<T> getAllDecks() {
            return mDeckList;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (!ListenerUtil.mutListener.listen(4079)) {
                mFilteredDecks.clear();
            }
            if (!ListenerUtil.mutListener.listen(4080)) {
                mFilteredDecks.ensureCapacity(mCol.getDecks().count());
            }
            List<T> allDecks = getAllDecks();
            if (!ListenerUtil.mutListener.listen(4083)) {
                if (TextUtils.isEmpty(constraint)) {
                    if (!ListenerUtil.mutListener.listen(4082)) {
                        mFilteredDecks.addAll(allDecks);
                    }
                } else {
                    final String filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim();
                    List<T> filteredDecks = filterDecks(filterPattern, allDecks);
                    if (!ListenerUtil.mutListener.listen(4081)) {
                        mFilteredDecks.addAll(filteredDecks);
                    }
                }
            }
            return FilterResultsUtils.fromCollection(mFilteredDecks);
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(4084)) {
                mCurrentDeckList.clear();
            }
            if (!ListenerUtil.mutListener.listen(4085)) {
                mCurrentDeckList.addAll(mFilteredDecks);
            }
            if (!ListenerUtil.mutListener.listen(4086)) {
                notifyDataSetChanged();
            }
        }

        private List<T> filterDecks(String filterPattern, List<T> allDecks) {
            ArrayList<T> ret = new ArrayList<>(allDecks.size());
            if (!ListenerUtil.mutListener.listen(4089)) {
                {
                    long _loopCounter95 = 0;
                    for (T tag : allDecks) {
                        ListenerUtil.loopListener.listen("_loopCounter95", ++_loopCounter95);
                        T node = filterDeckInternal(filterPattern, tag);
                        if (!ListenerUtil.mutListener.listen(4088)) {
                            if (node != null) {
                                if (!ListenerUtil.mutListener.listen(4087)) {
                                    ret.add(node);
                                }
                            }
                        }
                    }
                }
            }
            return ret;
        }

        @Nullable
        private T filterDeckInternal(String filterPattern, T root) {
            if (!ListenerUtil.mutListener.listen(4090)) {
                // If a deck contains the string, then all its children are valid
                if (containsFilterString(filterPattern, root)) {
                    return root;
                }
            }
            List<T> children = root.getChildren();
            List<T> ret = new ArrayList<>(children.size());
            if (!ListenerUtil.mutListener.listen(4093)) {
                {
                    long _loopCounter96 = 0;
                    for (T child : children) {
                        ListenerUtil.loopListener.listen("_loopCounter96", ++_loopCounter96);
                        T returned = filterDeckInternal(filterPattern, child);
                        if (!ListenerUtil.mutListener.listen(4092)) {
                            if (returned != null) {
                                if (!ListenerUtil.mutListener.listen(4091)) {
                                    ret.add(returned);
                                }
                            }
                        }
                    }
                }
            }
            // If any of a deck's children contains the search string, then the deck is valid
            return ret.isEmpty() ? null : root.withChildren(ret);
        }

        private boolean containsFilterString(String filterPattern, T root) {
            String deckName = root.getFullDeckName();
            return (ListenerUtil.mutListener.listen(4094) ? (deckName.toLowerCase(Locale.getDefault()).contains(filterPattern) && deckName.toLowerCase(Locale.ROOT).contains(filterPattern)) : (deckName.toLowerCase(Locale.getDefault()).contains(filterPattern) || deckName.toLowerCase(Locale.ROOT).contains(filterPattern)));
        }
    }
}
