package fr.free.nrw.commons.navtab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import fr.free.nrw.commons.contributions.MainActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NavTabLayout extends BottomNavigationView {

    public NavTabLayout(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(5984)) {
            setTabViews();
        }
    }

    public NavTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(5985)) {
            setTabViews();
        }
    }

    public NavTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(5986)) {
            setTabViews();
        }
    }

    private void setTabViews() {
        if (!ListenerUtil.mutListener.listen(6001)) {
            if (((MainActivity) getContext()).applicationKvStore.getBoolean("login_skipped") == true) {
                if (!ListenerUtil.mutListener.listen(6000)) {
                    {
                        long _loopCounter85 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(5999) ? (i >= NavTabLoggedOut.size()) : (ListenerUtil.mutListener.listen(5998) ? (i <= NavTabLoggedOut.size()) : (ListenerUtil.mutListener.listen(5997) ? (i > NavTabLoggedOut.size()) : (ListenerUtil.mutListener.listen(5996) ? (i != NavTabLoggedOut.size()) : (ListenerUtil.mutListener.listen(5995) ? (i == NavTabLoggedOut.size()) : (i < NavTabLoggedOut.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter85", ++_loopCounter85);
                            NavTabLoggedOut navTab = NavTabLoggedOut.of(i);
                            if (!ListenerUtil.mutListener.listen(5994)) {
                                getMenu().add(Menu.NONE, i, i, navTab.text()).setIcon(navTab.icon());
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5993)) {
                    {
                        long _loopCounter84 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(5992) ? (i >= NavTab.size()) : (ListenerUtil.mutListener.listen(5991) ? (i <= NavTab.size()) : (ListenerUtil.mutListener.listen(5990) ? (i > NavTab.size()) : (ListenerUtil.mutListener.listen(5989) ? (i != NavTab.size()) : (ListenerUtil.mutListener.listen(5988) ? (i == NavTab.size()) : (i < NavTab.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter84", ++_loopCounter84);
                            NavTab navTab = NavTab.of(i);
                            if (!ListenerUtil.mutListener.listen(5987)) {
                                getMenu().add(Menu.NONE, i, i, navTab.text()).setIcon(navTab.icon());
                            }
                        }
                    }
                }
            }
        }
    }
}
