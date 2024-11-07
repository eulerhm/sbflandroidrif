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

import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class BallotDetailActivity extends ThreemaToolbarActivity {

    private BallotModel ballotModel = null;

    interface ServiceCall {

        boolean call(BallotService service);
    }

    protected boolean setBallotModel(final BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(120)) {
            this.ballotModel = ballotModel;
        }
        if (!ListenerUtil.mutListener.listen(121)) {
            this.updateViewState();
        }
        return this.ballotModel != null;
    }

    protected BallotModel getBallotModel() {
        return this.ballotModel;
    }

    protected Integer getBallotModelId() {
        if (!ListenerUtil.mutListener.listen(122)) {
            if (this.ballotModel != null) {
                return this.ballotModel.getId();
            }
        }
        return null;
    }

    private void updateViewState() {
        if (!ListenerUtil.mutListener.listen(125)) {
            if (this.ballotModel != null) {
                if (!ListenerUtil.mutListener.listen(124)) {
                    this.callService(new ServiceCall() {

                        @Override
                        public boolean call(BallotService service) {
                            if (!ListenerUtil.mutListener.listen(123)) {
                                service.viewingBallot(ballotModel, true);
                            }
                            return true;
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(126)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(129)) {
            if (this.ballotModel != null) {
                if (!ListenerUtil.mutListener.listen(128)) {
                    this.callService(new ServiceCall() {

                        @Override
                        public boolean call(BallotService service) {
                            if (!ListenerUtil.mutListener.listen(127)) {
                                service.viewingBallot(ballotModel, true);
                            }
                            return true;
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(132)) {
            if (this.ballotModel != null) {
                if (!ListenerUtil.mutListener.listen(131)) {
                    this.callService(new ServiceCall() {

                        @Override
                        public boolean call(BallotService service) {
                            if (!ListenerUtil.mutListener.listen(130)) {
                                service.viewingBallot(ballotModel, false);
                            }
                            return true;
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(133)) {
            super.onPause();
        }
    }

    private boolean callService(ServiceCall serviceCall) {
        if (!ListenerUtil.mutListener.listen(135)) {
            if (serviceCall != null) {
                BallotService s = this.getBallotService();
                if (!ListenerUtil.mutListener.listen(134)) {
                    if (s != null) {
                        return serviceCall.call(s);
                    }
                }
            }
        }
        return false;
    }

    abstract BallotService getBallotService();

    abstract ContactService getContactService();

    abstract GroupService getGroupService();

    abstract String getIdentity();
}
