/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class for getting all storages directories in an Android device
 * <a href="https://stackoverflow.com/a/40582634/3940133">Solution of this problem</a>
 * Consider to use
 * <a href="https://developer.android.com/guide/topics/providers/document-provider">StorageAccessFramework(SAF)</>
 * if your min SDK version is 19 and your requirement is just for browse and open documents, images, and other files
 *
 * @author Dmitriy Lozenko
 * @author HendraWD
 */
public class StorageUtil {

    // Primary physical SD-CARD (not emulated)
    private static final String RAW_EXTERNAL_STORAGE = System.getenv("EXTERNAL_STORAGE");

    // All Secondary SD-CARDs (all exclude primary) separated by File.pathSeparator, i.e: ":", ";"
    private static final String RAW_SECONDARY_STORAGES = System.getenv("SECONDARY_STORAGE");

    // Primary emulated SD-CARD
    private static final String RAW_EMULATED_STORAGE_TARGET = System.getenv("EMULATED_STORAGE_TARGET");

    // PhysicalPaths based on phone model
    @SuppressLint("SdCardPath")
    @SuppressWarnings("SpellCheckingInspection")
    private static final String[] KNOWN_PHYSICAL_PATHS = new String[] { "/storage/sdcard0", // Motorola Xoom
    "/storage/sdcard1", // Samsung SGS3
    "/storage/extsdcard", // Samsung SGS4
    "/storage/extSdCard", // User request
    "/storage/sdcard0/external_sdcard", "/mnt/extsdcard", // Samsung galaxy family
    "/mnt/sdcard/external_sd", "/mnt/sdcard/ext_sd", "/mnt/external_sd", // 4.4.2 on CyanogenMod S3
    "/mnt/media_rw/sdcard1", // Asus transformer prime
    "/removable/microsd", "/mnt/emmc", // LG
    "/storage/external_SD", // HTC One Max
    "/storage/ext_sd", // Sony Xperia Z1
    "/storage/removable/sdcard1", "/data/sdext", "/data/sdext2", "/data/sdext3", "/data/sdext4", // Sony Xperia Z
    "/sdcard1", // HTC One M8s
    "/sdcard2", // ASUS ZenFone 2
    "/storage/microsd" };

    /**
     *  Returns all available SD-Cards in the system (include emulated)
     *  <p/>
     *  Warning: Hack! Based on Android source code of version 4.3 (API 18)
     *  Because there is no standard way to get it.
     *
     *  @return paths to all available SD-Cards in the system (include emulated)
     */
    public static String[] getStorageDirectories(Context context) {
        // Final set of paths
        final Set<String> finalAvailableDirectoriesSet = new LinkedHashSet<>();
        if (!ListenerUtil.mutListener.listen(55492)) {
            // add default external storage path (usually internal) first
            finalAvailableDirectoriesSet.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        if (!ListenerUtil.mutListener.listen(55515)) {
            if (TextUtils.isEmpty(RAW_EMULATED_STORAGE_TARGET)) {
                if (!ListenerUtil.mutListener.listen(55514)) {
                    if ((ListenerUtil.mutListener.listen(55507) ? (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(55506) ? (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(55505) ? (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(55504) ? (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(55503) ? (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        // because the RAW_EXTERNAL_STORAGE become something i.e: "/Storage/A5F9-15F4"
                        File[] files = context.getExternalFilesDirs(null);
                        if (!ListenerUtil.mutListener.listen(55513)) {
                            {
                                long _loopCounter676 = 0;
                                for (File file : files) {
                                    ListenerUtil.loopListener.listen("_loopCounter676", ++_loopCounter676);
                                    if (!ListenerUtil.mutListener.listen(55512)) {
                                        if (file != null) {
                                            // get root path of external files dir
                                            String applicationSpecificAbsolutePath = file.getAbsolutePath();
                                            String emulatedRootPath = applicationSpecificAbsolutePath.substring(0, applicationSpecificAbsolutePath.indexOf("Android/data"));
                                            if (!ListenerUtil.mutListener.listen(55511)) {
                                                // strip trailing slash
                                                finalAvailableDirectoriesSet.add(emulatedRootPath.replaceAll("/+$", ""));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(55510)) {
                            if (TextUtils.isEmpty(RAW_EXTERNAL_STORAGE)) {
                                if (!ListenerUtil.mutListener.listen(55509)) {
                                    finalAvailableDirectoriesSet.addAll(getAvailablePhysicalPaths());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(55508)) {
                                    // Device has physical external storage; use plain paths.
                                    finalAvailableDirectoriesSet.add(RAW_EXTERNAL_STORAGE);
                                }
                            }
                        }
                    }
                }
            } else {
                // Device has emulated storage; external storage paths should have id in the last segment
                String rawStorageId = "";
                final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                final String[] folders = File.separator.split(path);
                final String lastSegment = folders[(ListenerUtil.mutListener.listen(55496) ? (folders.length % 1) : (ListenerUtil.mutListener.listen(55495) ? (folders.length / 1) : (ListenerUtil.mutListener.listen(55494) ? (folders.length * 1) : (ListenerUtil.mutListener.listen(55493) ? (folders.length + 1) : (folders.length - 1)))))];
                if (!ListenerUtil.mutListener.listen(55499)) {
                    if ((ListenerUtil.mutListener.listen(55497) ? (!TextUtils.isEmpty(lastSegment) || TextUtils.isDigitsOnly(lastSegment)) : (!TextUtils.isEmpty(lastSegment) && TextUtils.isDigitsOnly(lastSegment)))) {
                        if (!ListenerUtil.mutListener.listen(55498)) {
                            rawStorageId = lastSegment;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(55502)) {
                    // i.e: "/storage/emulated/storageId" where storageId is 0, 1, 2, ...
                    if (TextUtils.isEmpty(rawStorageId)) {
                        if (!ListenerUtil.mutListener.listen(55501)) {
                            finalAvailableDirectoriesSet.add(RAW_EMULATED_STORAGE_TARGET);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(55500)) {
                            finalAvailableDirectoriesSet.add(RAW_EMULATED_STORAGE_TARGET + File.separator + rawStorageId);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55517)) {
            // Add all secondary storages
            if (!TextUtils.isEmpty(RAW_SECONDARY_STORAGES)) {
                // All Secondary SD-CARDs split into array
                final String[] rawSecondaryStorages = RAW_SECONDARY_STORAGES.split(File.pathSeparator);
                if (!ListenerUtil.mutListener.listen(55516)) {
                    Collections.addAll(finalAvailableDirectoriesSet, rawSecondaryStorages);
                }
            }
        }
        return finalAvailableDirectoriesSet.toArray(new String[finalAvailableDirectoriesSet.size()]);
    }

    /**
     *  Filter available physical paths from known physical paths
     *
     *  @return List of available physical paths from current device
     */
    private static List<String> getAvailablePhysicalPaths() {
        List<String> availablePhysicalPaths = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(55520)) {
            {
                long _loopCounter677 = 0;
                for (String physicalPath : KNOWN_PHYSICAL_PATHS) {
                    ListenerUtil.loopListener.listen("_loopCounter677", ++_loopCounter677);
                    File file = new File(physicalPath);
                    if (!ListenerUtil.mutListener.listen(55519)) {
                        if (file.exists()) {
                            if (!ListenerUtil.mutListener.listen(55518)) {
                                availablePhysicalPaths.add(physicalPath);
                            }
                        }
                    }
                }
            }
        }
        return availablePhysicalPaths;
    }
}
