/**
 * ************************************************************************************
 *  Copyright (c) 2012 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.StudyOptionsFragment.StudyOptionsListener;
import com.ichi2.anki.dialogs.CustomStudyDialog;
import com.ichi2.widget.WidgetStatus;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.RIGHT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StudyOptionsActivity extends NavigationDrawerActivity implements StudyOptionsListener, CustomStudyDialog.CustomStudyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11714)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11715)) {
            super.onCreate(savedInstanceState);
        }
        // higher) with the appcompat package.
        View mainView = getLayoutInflater().inflate(R.layout.studyoptions, null);
        if (!ListenerUtil.mutListener.listen(11716)) {
            setContentView(mainView);
        }
        if (!ListenerUtil.mutListener.listen(11717)) {
            // create inherited navigation drawer layout here so that it can be used by parent class
            initNavigationDrawer(mainView);
        }
        if (!ListenerUtil.mutListener.listen(11719)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(11718)) {
                    loadStudyOptionsFragment();
                }
            }
        }
    }

    private void loadStudyOptionsFragment() {
        boolean withDeckOptions = false;
        if (!ListenerUtil.mutListener.listen(11721)) {
            if (getIntent().getExtras() != null) {
                if (!ListenerUtil.mutListener.listen(11720)) {
                    withDeckOptions = getIntent().getExtras().getBoolean("withDeckOptions");
                }
            }
        }
        StudyOptionsFragment currentFragment = StudyOptionsFragment.newInstance(withDeckOptions);
        if (!ListenerUtil.mutListener.listen(11722)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.studyoptions_frame, currentFragment).commit();
        }
    }

    private StudyOptionsFragment getCurrentFragment() {
        return (StudyOptionsFragment) getSupportFragmentManager().findFragmentById(R.id.studyoptions_frame);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(11723)) {
            if (getDrawerToggle().onOptionsItemSelected(item)) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(11725)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(11724)) {
                    closeStudyOptions();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(11726)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(11727)) {
            Timber.d("onActivityResult (requestCode = %d, resultCode = %d)", requestCode, resultCode);
        }
    }

    private void closeStudyOptions() {
        if (!ListenerUtil.mutListener.listen(11728)) {
            closeStudyOptions(RESULT_OK);
        }
    }

    private void closeStudyOptions(int result) {
        if (!ListenerUtil.mutListener.listen(11729)) {
            // mCompat.invalidateOptionsMenu(this);
            setResult(result);
        }
        if (!ListenerUtil.mutListener.listen(11730)) {
            finishWithAnimation(RIGHT);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(11734)) {
            if (isDrawerOpen()) {
                if (!ListenerUtil.mutListener.listen(11733)) {
                    super.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11731)) {
                    Timber.i("Back key pressed");
                }
                if (!ListenerUtil.mutListener.listen(11732)) {
                    closeStudyOptions();
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(11735)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(11738)) {
            if (colIsOpen()) {
                if (!ListenerUtil.mutListener.listen(11736)) {
                    WidgetStatus.update(this);
                }
                if (!ListenerUtil.mutListener.listen(11737)) {
                    UIUtils.saveCollectionInBackground();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(11739)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(11740)) {
            selectNavigationItem(-1);
        }
    }

    @Override
    public void onRequireDeckListUpdate() {
        if (!ListenerUtil.mutListener.listen(11741)) {
            getCurrentFragment().refreshInterface();
        }
    }

    /**
     * Callback methods from CustomStudyDialog
     */
    @Override
    public void onCreateCustomStudySession() {
        if (!ListenerUtil.mutListener.listen(11742)) {
            // Sched already reset by CollectionTask in CustomStudyDialog
            getCurrentFragment().refreshInterface();
        }
    }

    @Override
    public void onExtendStudyLimits() {
        if (!ListenerUtil.mutListener.listen(11743)) {
            // Sched needs to be reset so provide true argument
            getCurrentFragment().refreshInterface(true);
        }
    }
}
