package org.wordpress.android.ui.publicize;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class PublicizeBaseFragment extends Fragment {

    private Toolbar getToolbar() {
        if (getActivity() != null) {
            return (Toolbar) getActivity().findViewById(R.id.toolbar);
        } else {
            return null;
        }
    }

    void setTitle(@StringRes int resId) {
        if (!ListenerUtil.mutListener.listen(17385)) {
            setTitle(getString(resId));
        }
    }

    void setTitle(String title) {
        Toolbar toolbar = getToolbar();
        if (!ListenerUtil.mutListener.listen(17387)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(17386)) {
                    toolbar.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17389)) {
            if (getActivity() != null) {
                if (!ListenerUtil.mutListener.listen(17388)) {
                    // important for accessibility - talkBack
                    getActivity().setTitle(title);
                }
            }
        }
    }

    void setNavigationIcon(@DrawableRes int resId) {
        Toolbar toolbar = getToolbar();
        if (!ListenerUtil.mutListener.listen(17391)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(17390)) {
                    toolbar.setNavigationIcon(resId);
                }
            }
        }
    }
}
