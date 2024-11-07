/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import ch.threema.app.R;
import ch.threema.app.fragments.BackupDataFragment;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.threemasafe.BackupThreemaSafeFragment;
import ch.threema.app.threemasafe.ThreemaSafeMDMConfig;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.HiddenChatUtil;
import ch.threema.app.utils.TestUtil;
import static ch.threema.app.services.PreferenceService.LockingMech_NONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackupAdminActivity extends ThreemaToolbarActivity {

    private static final Logger logger = LoggerFactory.getLogger(BackupAdminActivity.class);

    private static final String BUNDLE_IS_UNLOCKED = "biu";

    private DeadlineListService hiddenChatsListService;

    private boolean isUnlocked;

    private ThreemaSafeMDMConfig safeConfig;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1817)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1818)) {
            isUnlocked = false;
        }
        if (!ListenerUtil.mutListener.listen(1819)) {
            safeConfig = ThreemaSafeMDMConfig.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(1822)) {
            if ((ListenerUtil.mutListener.listen(1820) ? (!this.requiredInstances() && AppRestrictionUtil.isBackupsDisabled(this)) : (!this.requiredInstances() || AppRestrictionUtil.isBackupsDisabled(this)))) {
                if (!ListenerUtil.mutListener.listen(1821)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1825)) {
            if ((ListenerUtil.mutListener.listen(1823) ? (AppRestrictionUtil.isDataBackupsDisabled(this) || threemaSafeUIDisabled()) : (AppRestrictionUtil.isDataBackupsDisabled(this) && threemaSafeUIDisabled()))) {
                if (!ListenerUtil.mutListener.listen(1824)) {
                    this.finish();
                }
                return;
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(1828)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(1826)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(1827)) {
                    actionBar.setTitle(R.string.my_backups_title);
                }
            }
        }
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.pager);
        if (!ListenerUtil.mutListener.listen(1829)) {
            viewPager.setAdapter(new BackupAdminPagerAdapter(getSupportFragmentManager()));
        }
        if (!ListenerUtil.mutListener.listen(1830)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(1832)) {
            // recover lock state after rotation
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(1831)) {
                    isUnlocked = savedInstanceState.getBoolean(BUNDLE_IS_UNLOCKED, false);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(1833)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1836)) {
            if (!isUnlocked) {
                if (!ListenerUtil.mutListener.listen(1835)) {
                    if (!preferenceService.getLockMechanism().equals(LockingMech_NONE)) {
                        if (!ListenerUtil.mutListener.listen(1834)) {
                            HiddenChatUtil.launchLockCheckDialog(this, preferenceService);
                        }
                    }
                }
            }
        }
    }

    public int getLayoutResource() {
        return R.layout.activity_backup_admin;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1837)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(1841)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_CHECK_LOCK:
                    if (!ListenerUtil.mutListener.listen(1840)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(1839)) {
                                isUnlocked = true;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1838)) {
                                finish();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1843)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(1842)) {
                        finish();
                    }
                    break;
            }
        }
        return true;
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.serviceManager, this.preferenceService, this.hiddenChatsListService);
    }

    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(1846)) {
            if (this.serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(1845)) {
                        this.hiddenChatsListService = this.serviceManager.getHiddenChatsListService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1844)) {
                        logger.debug("Master Key locked!");
                    }
                }
            }
        }
    }

    private boolean threemaSafeUIDisabled() {
        return (ListenerUtil.mutListener.listen(1847) ? (ConfigUtils.isWorkRestricted() || safeConfig.isBackupAdminDisabled()) : (ConfigUtils.isWorkRestricted() && safeConfig.isBackupAdminDisabled()));
    }

    private boolean dataBackupUIDisabled() {
        return AppRestrictionUtil.isDataBackupsDisabled(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1848)) {
            outState.putBoolean(BUNDLE_IS_UNLOCKED, isUnlocked);
        }
        if (!ListenerUtil.mutListener.listen(1849)) {
            super.onSaveInstanceState(outState);
        }
    }

    public class BackupAdminPagerAdapter extends FragmentPagerAdapter {

        BackupAdminPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public int getCount() {
            return ((ListenerUtil.mutListener.listen(1850) ? (threemaSafeUIDisabled() && dataBackupUIDisabled()) : (threemaSafeUIDisabled() || dataBackupUIDisabled()))) ? 1 : 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (!ListenerUtil.mutListener.listen(1851)) {
                switch(position) {
                    case 0:
                        return threemaSafeUIDisabled() ? getString(R.string.backup_data) : getString(R.string.threema_safe);
                    case 1:
                        return getString(R.string.backup_data);
                }
            }
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            if (!ListenerUtil.mutListener.listen(1852)) {
                switch(position) {
                    case 0:
                        return threemaSafeUIDisabled() ? new BackupDataFragment() : new BackupThreemaSafeFragment();
                    case 1:
                        return new BackupDataFragment();
                }
            }
            return null;
        }
    }
}
