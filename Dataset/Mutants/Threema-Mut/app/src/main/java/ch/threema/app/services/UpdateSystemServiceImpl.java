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
package ch.threema.app.services;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UpdateSystemServiceImpl implements UpdateSystemService {

    private Queue<SystemUpdate> systemUpdates = new LinkedList<SystemUpdate>();

    @Override
    public void addUpdate(SystemUpdate systemUpdate) {
        // run directly
        try {
            if (!ListenerUtil.mutListener.listen(41190)) {
                systemUpdate.runDirectly();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        if (!ListenerUtil.mutListener.listen(41191)) {
            // add to queue to run a sync in a queue
            this.systemUpdates.add(systemUpdate);
        }
    }

    @Override
    public void update(OnSystemUpdateRun onSystemUpdateRun) {
        if (!ListenerUtil.mutListener.listen(41201)) {
            {
                long _loopCounter472 = 0;
                while ((ListenerUtil.mutListener.listen(41200) ? (this.systemUpdates.size() >= 0) : (ListenerUtil.mutListener.listen(41199) ? (this.systemUpdates.size() <= 0) : (ListenerUtil.mutListener.listen(41198) ? (this.systemUpdates.size() < 0) : (ListenerUtil.mutListener.listen(41197) ? (this.systemUpdates.size() != 0) : (ListenerUtil.mutListener.listen(41196) ? (this.systemUpdates.size() == 0) : (this.systemUpdates.size() > 0))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter472", ++_loopCounter472);
                    SystemUpdate update = this.systemUpdates.poll();
                    if (!ListenerUtil.mutListener.listen(41193)) {
                        if (onSystemUpdateRun != null) {
                            if (!ListenerUtil.mutListener.listen(41192)) {
                                onSystemUpdateRun.onStart(update);
                            }
                        }
                    }
                    boolean success = update.runASync();
                    if (!ListenerUtil.mutListener.listen(41195)) {
                        if (onSystemUpdateRun != null) {
                            if (!ListenerUtil.mutListener.listen(41194)) {
                                onSystemUpdateRun.onFinished(update, success);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        if (!ListenerUtil.mutListener.listen(41202)) {
            this.update(null);
        }
    }

    @Override
    public boolean hasUpdates() {
        return (ListenerUtil.mutListener.listen(41207) ? (this.systemUpdates.size() >= 0) : (ListenerUtil.mutListener.listen(41206) ? (this.systemUpdates.size() <= 0) : (ListenerUtil.mutListener.listen(41205) ? (this.systemUpdates.size() < 0) : (ListenerUtil.mutListener.listen(41204) ? (this.systemUpdates.size() != 0) : (ListenerUtil.mutListener.listen(41203) ? (this.systemUpdates.size() == 0) : (this.systemUpdates.size() > 0))))));
    }

    @Override
    public void prepareForTest() {
        if (!ListenerUtil.mutListener.listen(41208)) {
            this.systemUpdates.clear();
        }
        if (!ListenerUtil.mutListener.listen(41216)) {
            {
                long _loopCounter473 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(41215) ? (i >= 10) : (ListenerUtil.mutListener.listen(41214) ? (i <= 10) : (ListenerUtil.mutListener.listen(41213) ? (i > 10) : (ListenerUtil.mutListener.listen(41212) ? (i != 10) : (ListenerUtil.mutListener.listen(41211) ? (i == 10) : (i < 10)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter473", ++_loopCounter473);
                    final String name = "test script " + String.valueOf(i);
                    if (!ListenerUtil.mutListener.listen(41210)) {
                        this.addUpdate(new SystemUpdate() {

                            @Override
                            public boolean runASync() {
                                try {
                                    if (!ListenerUtil.mutListener.listen(41209)) {
                                        Thread.sleep(5000);
                                    }
                                } catch (InterruptedException e) {
                                }
                                return true;
                            }

                            @Override
                            public boolean runDirectly() {
                                return true;
                            }

                            @Override
                            public String getText() {
                                return name;
                            }
                        });
                    }
                }
            }
        }
    }
}
