package fr.free.nrw.commons.review;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReviewPagerAdapter extends FragmentStatePagerAdapter {

    private ReviewImageFragment[] reviewImageFragments;

    /**
     * this function return the instance of ReviewviewPage current item
     */
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    ReviewPagerAdapter(FragmentManager fm) {
        super(fm);
        if (!ListenerUtil.mutListener.listen(5716)) {
            reviewImageFragments = new ReviewImageFragment[] { new ReviewImageFragment(), new ReviewImageFragment(), new ReviewImageFragment(), new ReviewImageFragment() };
        }
    }

    @Override
    public int getCount() {
        return reviewImageFragments.length;
    }

    void updateFileInformation() {
        if (!ListenerUtil.mutListener.listen(5723)) {
            {
                long _loopCounter81 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5722) ? (i >= getCount()) : (ListenerUtil.mutListener.listen(5721) ? (i <= getCount()) : (ListenerUtil.mutListener.listen(5720) ? (i > getCount()) : (ListenerUtil.mutListener.listen(5719) ? (i != getCount()) : (ListenerUtil.mutListener.listen(5718) ? (i == getCount()) : (i < getCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter81", ++_loopCounter81);
                    ReviewImageFragment fragment = reviewImageFragments[i];
                    if (!ListenerUtil.mutListener.listen(5717)) {
                        fragment.update(i);
                    }
                }
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(5724)) {
            bundle.putInt("position", position);
        }
        if (!ListenerUtil.mutListener.listen(5725)) {
            reviewImageFragments[position].setArguments(bundle);
        }
        return reviewImageFragments[position];
    }
}
