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
package ch.threema.app;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BuildFlavor {

    private static final String FLAVOR_NONE = "none";

    private static final String FLAVOR_STORE_GOOGLE = "store_google";

    private static final String FLAVOR_STORE_THREEMA = "store_threema";

    private static final String FLAVOR_STORE_GOOGLE_WORK = "store_google_work";

    private static final String FLAVOR_SANDBOX = "sandbox";

    private static final String FLAVOR_SANDBOX_WORK = "sandbox_work";

    private static final String FLAVOR_RED = "red";

    private static final String FLAVOR_HMS = "hms";

    private static final String FLAVOR_HMS_WORK = "hms_work";

    public enum LicenseType {

        NONE,
        GOOGLE,
        SERIAL,
        GOOGLE_WORK,
        HMS,
        HMS_WORK
    }

    private static boolean initialized = false;

    private static LicenseType licenseType = null;

    private static int serverPort = 5222;

    private static int serverPortAlt = 443;

    private static boolean sandbox = false;

    private static String name = null;

    /**
     *  License Type
     *  @return
     */
    public static LicenseType getLicenseType() {
        if (!ListenerUtil.mutListener.listen(65160)) {
            init();
        }
        return licenseType;
    }

    /**
     *  Server Port
     *  @return
     */
    public static int getServerPort() {
        if (!ListenerUtil.mutListener.listen(65161)) {
            init();
        }
        return serverPort;
    }

    /**
     *  Server Port
     *  @return
     */
    public static int getServerPortAlt() {
        if (!ListenerUtil.mutListener.listen(65162)) {
            init();
        }
        return serverPortAlt;
    }

    public static String getName() {
        if (!ListenerUtil.mutListener.listen(65163)) {
            init();
        }
        return name;
    }

    public static boolean isSandbox() {
        if (!ListenerUtil.mutListener.listen(65164)) {
            init();
        }
        return sandbox;
    }

    private static void init() {
        if (!ListenerUtil.mutListener.listen(65190)) {
            if (!initialized) {
                if (!ListenerUtil.mutListener.listen(65186)) {
                    switch(BuildConfig.FLAVOR) {
                        case FLAVOR_STORE_GOOGLE:
                            if (!ListenerUtil.mutListener.listen(65165)) {
                                licenseType = LicenseType.GOOGLE;
                            }
                            if (!ListenerUtil.mutListener.listen(65166)) {
                                name = "Google Play";
                            }
                            break;
                        case FLAVOR_STORE_THREEMA:
                            if (!ListenerUtil.mutListener.listen(65167)) {
                                licenseType = LicenseType.SERIAL;
                            }
                            if (!ListenerUtil.mutListener.listen(65168)) {
                                name = "Threema Shop";
                            }
                            break;
                        case FLAVOR_NONE:
                            if (!ListenerUtil.mutListener.listen(65169)) {
                                licenseType = LicenseType.NONE;
                            }
                            if (!ListenerUtil.mutListener.listen(65170)) {
                                name = "DEV";
                            }
                            break;
                        case FLAVOR_STORE_GOOGLE_WORK:
                            if (!ListenerUtil.mutListener.listen(65171)) {
                                licenseType = LicenseType.GOOGLE_WORK;
                            }
                            if (!ListenerUtil.mutListener.listen(65172)) {
                                name = "Work";
                            }
                            break;
                        case FLAVOR_SANDBOX:
                            if (!ListenerUtil.mutListener.listen(65173)) {
                                sandbox = true;
                            }
                            if (!ListenerUtil.mutListener.listen(65174)) {
                                name = "Sandbox";
                            }
                            if (!ListenerUtil.mutListener.listen(65175)) {
                                licenseType = LicenseType.NONE;
                            }
                            break;
                        case FLAVOR_SANDBOX_WORK:
                            if (!ListenerUtil.mutListener.listen(65176)) {
                                sandbox = true;
                            }
                            if (!ListenerUtil.mutListener.listen(65177)) {
                                name = "Sandbox Work";
                            }
                            if (!ListenerUtil.mutListener.listen(65178)) {
                                licenseType = LicenseType.GOOGLE_WORK;
                            }
                            break;
                        case FLAVOR_RED:
                            if (!ListenerUtil.mutListener.listen(65179)) {
                                sandbox = true;
                            }
                            if (!ListenerUtil.mutListener.listen(65180)) {
                                name = "Red";
                            }
                            if (!ListenerUtil.mutListener.listen(65181)) {
                                licenseType = LicenseType.GOOGLE_WORK;
                            }
                            break;
                        case FLAVOR_HMS:
                            if (!ListenerUtil.mutListener.listen(65182)) {
                                name = "HMS";
                            }
                            if (!ListenerUtil.mutListener.listen(65183)) {
                                licenseType = LicenseType.HMS;
                            }
                            break;
                        case FLAVOR_HMS_WORK:
                            if (!ListenerUtil.mutListener.listen(65184)) {
                                name = "Hms Work";
                            }
                            if (!ListenerUtil.mutListener.listen(65185)) {
                                licenseType = LicenseType.HMS_WORK;
                            }
                            break;
                        default:
                            throw new RuntimeException("invalid flavor build " + BuildConfig.FLAVOR);
                    }
                }
                if (!ListenerUtil.mutListener.listen(65188)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(65187)) {
                            name += " (DEBUG)";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(65189)) {
                    initialized = true;
                }
            }
        }
    }
}
