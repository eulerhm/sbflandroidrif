package fr.free.nrw.commons.navtab;

import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NavTabFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment currentFragment;

    public NavTabFragmentPagerAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Nullable
    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public Fragment getItem(int pos) {
        return NavTab.of(pos).newInstance();
    }

    @Override
    public int getCount() {
        return NavTab.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (!ListenerUtil.mutListener.listen(5940)) {
            currentFragment = ((Fragment) object);
        }
        if (!ListenerUtil.mutListener.listen(5941)) {
            super.setPrimaryItem(container, position, object);
        }
    }
}
