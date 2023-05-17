package org.owntracks.android.ui.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.BR;
import org.owntracks.android.R;
import org.owntracks.android.support.DrawerProvider;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BaseActivity<B extends ViewDataBinding, V extends MvvmViewModel> extends DaggerAppCompatActivity {

    protected B binding;

    @Inject
    protected V viewModel;

    @Inject
    EventBus eventBus;

    @Inject
    DrawerProvider drawerProvider;

    @Inject
    protected Preferences preferences;

    private boolean hasEventBus = true;

    private boolean disablesAnimation = false;

    protected void setHasEventBus(boolean enable) {
        if (!ListenerUtil.mutListener.listen(1364)) {
            this.hasEventBus = enable;
        }
    }

    /* Use this method to set the content view on your Activity. This method also handles
     * creating the binding, setting the view model on the binding and attaching the view. */
    protected final void bindAndAttachContentView(@LayoutRes int layoutResId, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1365)) {
            if (viewModel == null) {
                throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)");
            }
        }
        if (!ListenerUtil.mutListener.listen(1366)) {
            binding = DataBindingUtil.setContentView(this, layoutResId);
        }
        if (!ListenerUtil.mutListener.listen(1367)) {
            binding.setVariable(BR.vm, viewModel);
        }
        if (!ListenerUtil.mutListener.listen(1368)) {
            binding.setLifecycleOwner(this);
        }
        if (!ListenerUtil.mutListener.listen(1369)) {
            // noinspection unchecked
            viewModel.attachView(savedInstanceState, (MvvmView) this);
        }
    }

    private boolean mBound;

    void setBound(boolean bound) {
        if (!ListenerUtil.mutListener.listen(1370)) {
            mBound = bound;
        }
    }

    // Monitors the state of the connection to the service.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (!ListenerUtil.mutListener.listen(1372)) {
                if (service != null) {
                    if (!ListenerUtil.mutListener.listen(1371)) {
                        setBound(true);
                    }
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (!ListenerUtil.mutListener.listen(1373)) {
                setBound(false);
            }
        }
    };

    protected void setSupportToolbar(@NonNull Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(1374)) {
            setSupportToolbar(toolbar, true, true);
        }
    }

    protected void setSupportToolbar(@NonNull Toolbar toolbar, boolean showTitle, boolean showHome) {
        if (!ListenerUtil.mutListener.listen(1375)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(1381)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(1377)) {
                    if (showTitle)
                        if (!ListenerUtil.mutListener.listen(1376)) {
                            getSupportActionBar().setTitle(getTitle());
                        }
                }
                if (!ListenerUtil.mutListener.listen(1378)) {
                    getSupportActionBar().setDisplayShowTitleEnabled(showTitle);
                }
                if (!ListenerUtil.mutListener.listen(1379)) {
                    getSupportActionBar().setDisplayShowHomeEnabled(showHome);
                }
                if (!ListenerUtil.mutListener.listen(1380)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
                }
            }
        }
    }

    protected void setSupportToolbarWithDrawer(@NonNull Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(1382)) {
            setSupportToolbar(toolbar, true, true);
        }
        if (!ListenerUtil.mutListener.listen(1383)) {
            setDrawer(toolbar);
        }
    }

    protected void setDrawer(@NonNull Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(1384)) {
            drawerProvider.attach(toolbar);
        }
    }

    @Override
    protected void onCreate(Bundle b) {
        if (!ListenerUtil.mutListener.listen(1385)) {
            super.onCreate(b);
        }
        if (!ListenerUtil.mutListener.listen(1386)) {
            disablesAnimation = (getIntent().getFlags() & Intent.FLAG_ACTIVITY_NO_ANIMATION) != 0;
        }
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1387)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1389)) {
            if (viewModel != null) {
                if (!ListenerUtil.mutListener.listen(1388)) {
                    viewModel.saveInstanceState(outState);
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(1392)) {
            if (disablesAnimation) {
                if (!ListenerUtil.mutListener.listen(1391)) {
                    overridePendingTransition(0, 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1390)) {
                    overridePendingTransition(R.anim.push_up_in, R.anim.none);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1393)) {
            super.onStart();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(1396)) {
            if (mBound) {
                if (!ListenerUtil.mutListener.listen(1394)) {
                    unbindService(mServiceConnection);
                }
                if (!ListenerUtil.mutListener.listen(1395)) {
                    mBound = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1397)) {
            super.onStop();
        }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1398)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(1400)) {
            if (viewModel != null) {
                if (!ListenerUtil.mutListener.listen(1399)) {
                    viewModel.detachView();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1401)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(1402)) {
            viewModel = null;
        }
    }

    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1403)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1406)) {
            if ((ListenerUtil.mutListener.listen(1404) ? (hasEventBus || !eventBus.isRegistered(viewModel)) : (hasEventBus && !eventBus.isRegistered(viewModel))))
                if (!ListenerUtil.mutListener.listen(1405)) {
                    eventBus.register(viewModel);
                }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1407)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1409)) {
            if (eventBus.isRegistered(viewModel))
                if (!ListenerUtil.mutListener.listen(1408)) {
                    eventBus.unregister(viewModel);
                }
        }
        if (!ListenerUtil.mutListener.listen(1412)) {
            if (disablesAnimation) {
                if (!ListenerUtil.mutListener.listen(1411)) {
                    overridePendingTransition(0, 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1410)) {
                    overridePendingTransition(R.anim.push_up_in, R.anim.none);
                }
            }
        }
    }

    protected void disablesAnimation() {
        if (!ListenerUtil.mutListener.listen(1413)) {
            disablesAnimation = true;
        }
    }
}
