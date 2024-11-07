/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.activities.ballot;

import androidx.fragment.app.Fragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class BallotWizardFragment extends Fragment {

    private BallotWizardActivity ballotWizardActivity = null;

    /**
     *  update the data fields
     */
    abstract void updateView();

    /**
     *  cast activity to ballotActivity
     *  @return
     */
    public BallotWizardActivity getBallotActivity() {
        if (!ListenerUtil.mutListener.listen(499)) {
            if (this.ballotWizardActivity == null) {
                if (!ListenerUtil.mutListener.listen(498)) {
                    if (super.getActivity() instanceof BallotWizardActivity) {
                        if (!ListenerUtil.mutListener.listen(497)) {
                            this.ballotWizardActivity = (BallotWizardActivity) this.getActivity();
                        }
                    }
                }
            }
        }
        return this.ballotWizardActivity;
    }
}
