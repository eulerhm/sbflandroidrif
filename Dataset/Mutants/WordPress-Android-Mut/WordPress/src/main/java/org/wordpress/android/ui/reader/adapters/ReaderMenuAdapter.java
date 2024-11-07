package org.wordpress.android.ui.reader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import org.wordpress.android.R;
import org.wordpress.android.ui.reader.discover.ReaderPostCardAction;
import org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction;
import org.wordpress.android.ui.reader.discover.ReaderPostCardActionType;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * adapter for the popup menu that appears when clicking "..." in the reader
 */
public class ReaderMenuAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;

    private final List<ReaderPostCardAction> mMenuItems = new ArrayList<>();

    private final UiHelpers mUiHelpers;

    private static final int TYPE_SPACER = 0;

    private static final int TYPE_CONTENT = 1;

    public ReaderMenuAdapter(Context context, @NonNull UiHelpers uiHelpers, @NonNull List<ReaderPostCardAction> menuItems) {
        super();
        mInflater = LayoutInflater.from(context);
        if (!ListenerUtil.mutListener.listen(18566)) {
            mMenuItems.addAll(menuItems);
        }
        mUiHelpers = uiHelpers;
    }

    @Override
    public int getCount() {
        return mMenuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mMenuItems.get(position).getType().ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (mMenuItems.get(position).getType() == ReaderPostCardActionType.SPACER_NO_ACTION) ? TYPE_SPACER : TYPE_CONTENT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReaderPostCardAction cardAction = mMenuItems.get(position);
        if ((ListenerUtil.mutListener.listen(18571) ? (getItemViewType(position) >= TYPE_SPACER) : (ListenerUtil.mutListener.listen(18570) ? (getItemViewType(position) <= TYPE_SPACER) : (ListenerUtil.mutListener.listen(18569) ? (getItemViewType(position) > TYPE_SPACER) : (ListenerUtil.mutListener.listen(18568) ? (getItemViewType(position) < TYPE_SPACER) : (ListenerUtil.mutListener.listen(18567) ? (getItemViewType(position) != TYPE_SPACER) : (getItemViewType(position) == TYPE_SPACER))))))) {
            return handleSpacer(convertView, parent);
        } else {
            return handleSecondaryAction((SecondaryAction) cardAction, convertView, parent);
        }
    }

    private View handleSpacer(View convertView, ViewGroup parent) {
        ReaderMenuSpacerHolder holder;
        if (convertView == null) {
            if (!ListenerUtil.mutListener.listen(18572)) {
                convertView = mInflater.inflate(R.layout.reader_popup_menu_spacer_item, parent, false);
            }
            holder = new ReaderMenuSpacerHolder(convertView);
            if (!ListenerUtil.mutListener.listen(18573)) {
                convertView.setTag(holder);
            }
        } else {
            holder = (ReaderMenuSpacerHolder) convertView.getTag();
        }
        if (!ListenerUtil.mutListener.listen(18574)) {
            holder.mSpacer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(18575)) {
            holder.mContainer.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(18576)) {
            holder.mContainer.setClickable(false);
        }
        return convertView;
    }

    private View handleSecondaryAction(SecondaryAction item, View convertView, ViewGroup parent) {
        ReaderMenuHolder holder;
        if (convertView == null) {
            if (!ListenerUtil.mutListener.listen(18577)) {
                convertView = mInflater.inflate(R.layout.reader_popup_menu_item, parent, false);
            }
            holder = new ReaderMenuHolder(convertView);
            if (!ListenerUtil.mutListener.listen(18578)) {
                convertView.setTag(holder);
            }
        } else {
            holder = (ReaderMenuHolder) convertView.getTag();
        }
        CharSequence textRes = mUiHelpers.getTextOfUiString(convertView.getContext(), item.getLabel());
        int textColorRes = ContextExtensionsKt.getColorResIdFromAttribute(convertView.getContext(), item.getLabelColor());
        int iconColorRes = ContextExtensionsKt.getColorResIdFromAttribute(convertView.getContext(), item.getIconColor());
        int iconRes = item.getIconRes();
        if (!ListenerUtil.mutListener.listen(18579)) {
            holder.mText.setText(textRes);
        }
        if (!ListenerUtil.mutListener.listen(18580)) {
            holder.mText.setTextColor(AppCompatResources.getColorStateList(convertView.getContext(), textColorRes));
        }
        if (!ListenerUtil.mutListener.listen(18581)) {
            ColorUtils.setImageResourceWithTint(holder.mIcon, iconRes, iconColorRes);
        }
        return convertView;
    }

    static class ReaderMenuHolder {

        private final TextView mText;

        private final ImageView mIcon;

        ReaderMenuHolder(View view) {
            mText = view.findViewById(R.id.text);
            mIcon = view.findViewById(R.id.image);
        }
    }

    static class ReaderMenuSpacerHolder {

        private final Space mSpacer;

        private final LinearLayout mContainer;

        ReaderMenuSpacerHolder(View view) {
            mSpacer = view.findViewById(R.id.reader_popup_menu_item_spacer);
            mContainer = view.findViewById(R.id.reader_popup_menu_item_spacer_container);
        }
    }
}
