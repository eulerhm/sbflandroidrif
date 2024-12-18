package org.wordpress.android.ui.stockmedia;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.wordpress.android.fluxc.model.StockMediaModel;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StockMediaRetainedFragment extends Fragment {

    static StockMediaRetainedFragment newInstance() {
        return new StockMediaRetainedFragment();
    }

    static class StockMediaRetainedData {

        private final List<StockMediaModel> mStockMediaList;

        private final ArrayList<Integer> mSelectedItems;

        private final boolean mCanLoadMore;

        private final int mNextPage;

        StockMediaRetainedData(@NonNull List<StockMediaModel> stockMediaList, @NonNull ArrayList<Integer> selectedItems, boolean canLoadMore, int nextPage) {
            mStockMediaList = stockMediaList;
            mSelectedItems = selectedItems;
            mCanLoadMore = canLoadMore;
            mNextPage = nextPage;
        }

        @NonNull
        List<StockMediaModel> getStockMediaList() {
            return mStockMediaList;
        }

        @NonNull
        List<Integer> getSelectedItems() {
            return mSelectedItems;
        }

        boolean canLoadMore() {
            return mCanLoadMore;
        }

        int getNextPage() {
            return mNextPage;
        }
    }

    private StockMediaRetainedData mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23113)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(23114)) {
            setRetainInstance(true);
        }
    }

    @Nullable
    StockMediaRetainedData getData() {
        return mData;
    }

    void setData(@Nullable StockMediaRetainedData data) {
        if (!ListenerUtil.mutListener.listen(23115)) {
            mData = data;
        }
    }
}
