package org.wordpress.android.ui.accounts.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UsernameChangerRecyclerViewAdapter extends RecyclerView.Adapter<UsernameChangerRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    protected List<String> mItems;

    protected OnUsernameSelectedListener mListener;

    protected int mSelectedItem = -1;

    public UsernameChangerRecyclerViewAdapter(Context context, List<String> items) {
        if (!ListenerUtil.mutListener.listen(3842)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(3843)) {
            mItems = items;
        }
    }

    @Override
    public void onBindViewHolder(UsernameChangerRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        if (!ListenerUtil.mutListener.listen(3849)) {
            viewHolder.mRadio.setChecked((ListenerUtil.mutListener.listen(3848) ? (position >= mSelectedItem) : (ListenerUtil.mutListener.listen(3847) ? (position <= mSelectedItem) : (ListenerUtil.mutListener.listen(3846) ? (position > mSelectedItem) : (ListenerUtil.mutListener.listen(3845) ? (position < mSelectedItem) : (ListenerUtil.mutListener.listen(3844) ? (position != mSelectedItem) : (position == mSelectedItem)))))));
        }
        if (!ListenerUtil.mutListener.listen(3850)) {
            viewHolder.mText.setText(mItems.get(position));
        }
    }

    @Override
    public UsernameChangerRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.single_choice_recycler_view_item, viewGroup, false);
        return new UsernameChangerRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public int getSelectedItem() {
        return mSelectedItem;
    }

    public void setOnUsernameSelectedListener(OnUsernameSelectedListener listener) {
        if (!ListenerUtil.mutListener.listen(3851)) {
            mListener = listener;
        }
    }

    public void setSelectedItem(int position) {
        if (!ListenerUtil.mutListener.listen(3852)) {
            mSelectedItem = position;
        }
        if (!ListenerUtil.mutListener.listen(3853)) {
            notifyItemRangeChanged(0, mItems.size());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton mRadio;

        public TextView mText;

        public ViewHolder(final View inflate) {
            super(inflate);
            if (!ListenerUtil.mutListener.listen(3854)) {
                mRadio = inflate.findViewById(R.id.radio);
            }
            if (!ListenerUtil.mutListener.listen(3855)) {
                mText = inflate.findViewById(R.id.text);
            }
            View.OnClickListener listener = new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(3856)) {
                        mSelectedItem = getAdapterPosition();
                    }
                    if (!ListenerUtil.mutListener.listen(3857)) {
                        notifyItemRangeChanged(0, mItems.size());
                    }
                    if (!ListenerUtil.mutListener.listen(3859)) {
                        if (mListener != null) {
                            if (!ListenerUtil.mutListener.listen(3858)) {
                                mListener.onUsernameSelected(mItems.get(mSelectedItem));
                            }
                        }
                    }
                }
            };
            if (!ListenerUtil.mutListener.listen(3860)) {
                itemView.setOnClickListener(listener);
            }
            if (!ListenerUtil.mutListener.listen(3861)) {
                mRadio.setOnClickListener(listener);
            }
        }
    }

    interface OnUsernameSelectedListener {

        void onUsernameSelected(String username);
    }
}
