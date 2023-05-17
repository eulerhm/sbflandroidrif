package org.wordpress.android.ui.reader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import org.wordpress.android.R;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.reader.adapters.ReaderTagAdapter;
import org.wordpress.android.ui.reader.views.ReaderRecyclerView;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.WPActivityUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * fragment hosted by ReaderSubsActivity which shows followed tags
 */
public class ReaderTagFragment extends Fragment implements ReaderTagAdapter.TagDeletedListener {

    private ReaderRecyclerView mRecyclerView;

    private ReaderTagAdapter mTagAdapter;

    static ReaderTagFragment newInstance() {
        if (!ListenerUtil.mutListener.listen(22633)) {
            AppLog.d(AppLog.T.READER, "reader tag list > newInstance");
        }
        return new ReaderTagFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reader_fragment_list, container, false);
        if (!ListenerUtil.mutListener.listen(22634)) {
            mRecyclerView = view.findViewById(R.id.recycler_view);
        }
        return view;
    }

    private void checkEmptyView() {
        if (!ListenerUtil.mutListener.listen(22636)) {
            if ((ListenerUtil.mutListener.listen(22635) ? (!isAdded() && getView() == null) : (!isAdded() || getView() == null))) {
                return;
            }
        }
        ActionableEmptyView actionableEmptyView = getView().findViewById(R.id.actionable_empty_view);
        if (!ListenerUtil.mutListener.listen(22637)) {
            if (actionableEmptyView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22638)) {
            actionableEmptyView.image.setImageResource(R.drawable.img_illustration_empty_results_216dp);
        }
        if (!ListenerUtil.mutListener.listen(22639)) {
            actionableEmptyView.image.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(22640)) {
            actionableEmptyView.title.setText(R.string.reader_empty_followed_tags_title);
        }
        if (!ListenerUtil.mutListener.listen(22641)) {
            actionableEmptyView.subtitle.setText(R.string.reader_empty_followed_tags_subtitle);
        }
        if (!ListenerUtil.mutListener.listen(22642)) {
            actionableEmptyView.subtitle.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(22644)) {
            actionableEmptyView.setVisibility((ListenerUtil.mutListener.listen(22643) ? (hasTagAdapter() || getTagAdapter().isEmpty()) : (hasTagAdapter() && getTagAdapter().isEmpty())) ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22645)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22646)) {
            mRecyclerView.setAdapter(getTagAdapter());
        }
        if (!ListenerUtil.mutListener.listen(22647)) {
            refresh();
        }
    }

    void refresh() {
        if (!ListenerUtil.mutListener.listen(22650)) {
            if (hasTagAdapter()) {
                if (!ListenerUtil.mutListener.listen(22648)) {
                    AppLog.d(AppLog.T.READER, "reader subs > refreshing tag fragment");
                }
                if (!ListenerUtil.mutListener.listen(22649)) {
                    getTagAdapter().refresh();
                }
            }
        }
    }

    private ReaderTagAdapter getTagAdapter() {
        if (!ListenerUtil.mutListener.listen(22655)) {
            if (mTagAdapter == null) {
                Context context = WPActivityUtils.getThemedContext(getActivity());
                if (!ListenerUtil.mutListener.listen(22651)) {
                    mTagAdapter = new ReaderTagAdapter(context);
                }
                if (!ListenerUtil.mutListener.listen(22652)) {
                    mTagAdapter.setTagDeletedListener(this);
                }
                if (!ListenerUtil.mutListener.listen(22654)) {
                    mTagAdapter.setDataLoadedListener(new ReaderInterfaces.DataLoadedListener() {

                        @Override
                        public void onDataLoaded(boolean isEmpty) {
                            if (!ListenerUtil.mutListener.listen(22653)) {
                                checkEmptyView();
                            }
                        }
                    });
                }
            }
        }
        return mTagAdapter;
    }

    private boolean hasTagAdapter() {
        return (mTagAdapter != null);
    }

    /*
     * called from adapter when user removes a tag - note that the network request
     * has been made by the time this is called
     */
    @Override
    public void onTagDeleted(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(22656)) {
            checkEmptyView();
        }
        if (!ListenerUtil.mutListener.listen(22658)) {
            // let the host activity know about the change
            if (getActivity() instanceof ReaderTagAdapter.TagDeletedListener) {
                if (!ListenerUtil.mutListener.listen(22657)) {
                    ((ReaderTagAdapter.TagDeletedListener) getActivity()).onTagDeleted(tag);
                }
            }
        }
    }
}
