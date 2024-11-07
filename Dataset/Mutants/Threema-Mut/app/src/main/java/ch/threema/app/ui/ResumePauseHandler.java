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
package ch.threema.app.ui;

import android.app.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ResumePauseHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResumePauseHandler.class);

    private static final Map<String, ResumePauseHandler> instances = new HashMap<>();

    private static final Object lock = new Object();

    private final Map<String, RunIfActive> runIfActiveList = new HashMap<String, RunIfActive>();

    private final WeakReference<Activity> activityReference;

    private boolean isActive;

    private boolean hasHandlers = false;

    private ResumePauseHandler(Activity activity) {
        this.activityReference = new WeakReference<>(activity);
    }

    public static ResumePauseHandler getByActivity(Object useInObject, Activity activity) {
        final String key = useInObject.getClass().toString();
        ResumePauseHandler instance = instances.get(key);
        if (!ListenerUtil.mutListener.listen(47025)) {
            if (instance == null) {
                synchronized (lock) {
                    if (!ListenerUtil.mutListener.listen(47021)) {
                        instance = instances.get(key);
                    }
                    if (!ListenerUtil.mutListener.listen(47024)) {
                        if (instance == null) {
                            if (!ListenerUtil.mutListener.listen(47022)) {
                                instance = new ResumePauseHandler(activity);
                            }
                            if (!ListenerUtil.mutListener.listen(47023)) {
                                instances.put(key, instance);
                            }
                        }
                    }
                }
            }
        }
        return instance;
    }

    public interface RunIfActive {

        void runOnUiThread();
    }

    public void runOnActive(String tag, RunIfActive runIfActive) {
        if (!ListenerUtil.mutListener.listen(47026)) {
            this.runOnActive(tag, runIfActive, false);
        }
    }

    public void runOnActive(String tag, RunIfActive runIfActive, boolean lowPriority) {
        if (!ListenerUtil.mutListener.listen(47027)) {
            if (runIfActive == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(47033)) {
            if (this.isActive) {
                if (!ListenerUtil.mutListener.listen(47032)) {
                    this.run(runIfActive);
                }
            } else {
                // pending
                synchronized (this.runIfActiveList) {
                    if (!ListenerUtil.mutListener.listen(47031)) {
                        if ((ListenerUtil.mutListener.listen(47028) ? (!lowPriority && !this.runIfActiveList.containsKey(tag)) : (!lowPriority || !this.runIfActiveList.containsKey(tag)))) {
                            if (!ListenerUtil.mutListener.listen(47029)) {
                                this.runIfActiveList.put(tag, runIfActive);
                            }
                            if (!ListenerUtil.mutListener.listen(47030)) {
                                this.hasHandlers = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void onResume() {
        if (!ListenerUtil.mutListener.listen(47042)) {
            if (!this.isActive) {
                if (!ListenerUtil.mutListener.listen(47034)) {
                    this.isActive = true;
                }
                if (!ListenerUtil.mutListener.listen(47041)) {
                    if (this.hasHandlers) {
                        synchronized (this.runIfActiveList) {
                            if (!ListenerUtil.mutListener.listen(47038)) {
                                {
                                    long _loopCounter553 = 0;
                                    for (RunIfActive r : this.runIfActiveList.values()) {
                                        ListenerUtil.loopListener.listen("_loopCounter553", ++_loopCounter553);
                                        if (!ListenerUtil.mutListener.listen(47037)) {
                                            if ((ListenerUtil.mutListener.listen(47035) ? (r != null || this.isActive) : (r != null && this.isActive))) {
                                                if (!ListenerUtil.mutListener.listen(47036)) {
                                                    this.run(r);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(47039)) {
                                this.runIfActiveList.clear();
                            }
                            if (!ListenerUtil.mutListener.listen(47040)) {
                                this.hasHandlers = false;
                            }
                        }
                    }
                }
            }
        }
    }

    public void onPause() {
        if (!ListenerUtil.mutListener.listen(47043)) {
            this.isActive = false;
        }
    }

    public void onDestroy(Object object) {
        synchronized (this.runIfActiveList) {
            if (!ListenerUtil.mutListener.listen(47044)) {
                this.isActive = false;
            }
            if (!ListenerUtil.mutListener.listen(47045)) {
                this.runIfActiveList.clear();
            }
            if (!ListenerUtil.mutListener.listen(47046)) {
                instances.remove(object.getClass().toString());
            }
        }
    }

    private boolean run(final RunIfActive runIfActive) {
        if (!ListenerUtil.mutListener.listen(47048)) {
            if (TestUtil.required(runIfActive, this.activityReference.get())) {
                if (!ListenerUtil.mutListener.listen(47047)) {
                    RuntimeUtil.runOnUiThread(() -> runIfActive.runOnUiThread());
                }
            }
        }
        return true;
    }
}
