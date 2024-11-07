/**
 * ************************************************************************************
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.format.Formatter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ichi2.anki.exception.StorageAccessException;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Storage;
import com.ichi2.libanki.exception.UnknownDatabaseVersionException;
import com.ichi2.libanki.utils.SystemTime;
import com.ichi2.libanki.utils.Time;
import com.ichi2.preferences.PreferenceExtensions;
import com.ichi2.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import static com.ichi2.libanki.Consts.SCHEMA_VERSION;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Singleton which opens, stores, and closes the reference to the Collection.
 */
public class CollectionHelper {

    // Collection instance belonging to sInstance
    private Collection mCollection;

    // Name of anki2 file
    public static final String COLLECTION_FILENAME = "collection.anki2";

    /**
     * Prevents {@link com.ichi2.async.CollectionLoader} from spuriously re-opening the {@link Collection}.
     *
     * <p>Accessed only from synchronized methods.
     */
    private boolean mCollectionLocked;

    @Nullable
    public static Long getCollectionSize(Context context) {
        try {
            String path = getCollectionPath(context);
            return new File(path).length();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6746)) {
                Timber.e(e, "Error getting collection Length");
            }
            return null;
        }
    }

    public synchronized void lockCollection() {
        if (!ListenerUtil.mutListener.listen(6747)) {
            Timber.i("Locked Collection - Collection Loading should fail");
        }
        if (!ListenerUtil.mutListener.listen(6748)) {
            mCollectionLocked = true;
        }
    }

    public synchronized void unlockCollection() {
        if (!ListenerUtil.mutListener.listen(6749)) {
            Timber.i("Unlocked Collection");
        }
        if (!ListenerUtil.mutListener.listen(6750)) {
            mCollectionLocked = false;
        }
    }

    public synchronized boolean isCollectionLocked() {
        return mCollectionLocked;
    }

    /**
     * Lazy initialization holder class idiom. High performance and thread safe way to create singleton.
     */
    @VisibleForTesting
    public static class LazyHolder {

        @VisibleForTesting
        public static CollectionHelper INSTANCE = new CollectionHelper();
    }

    /**
     * @return Singleton instance of the helper class
     */
    public static CollectionHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Get the single instance of the {@link Collection}, creating it if necessary  (lazy initialization).
     * @param context context which can be used to get the setting for the path to the Collection
     * @return instance of the Collection
     */
    public synchronized Collection getCol(Context context) {
        if (!ListenerUtil.mutListener.listen(6751)) {
            if (colIsOpen()) {
                return mCollection;
            }
        }
        return getCol(context, new SystemTime());
    }

    @VisibleForTesting
    public synchronized Collection getCol(Context context, @NonNull Time time) {
        if (!ListenerUtil.mutListener.listen(6757)) {
            // Open collection
            if (!colIsOpen()) {
                String path = getCollectionPath(context);
                // Check that the directory has been created and initialized
                try {
                    if (!ListenerUtil.mutListener.listen(6753)) {
                        initializeAnkiDroidDirectory(getParentDirectory(path));
                    }
                } catch (StorageAccessException e) {
                    if (!ListenerUtil.mutListener.listen(6752)) {
                        Timber.e(e, "Could not initialize AnkiDroid directory");
                    }
                    return null;
                }
                if (!ListenerUtil.mutListener.listen(6754)) {
                    // Open the database
                    Timber.i("Begin openCollection: %s", path);
                }
                if (!ListenerUtil.mutListener.listen(6755)) {
                    mCollection = Storage.Collection(context, path, false, true, time);
                }
                if (!ListenerUtil.mutListener.listen(6756)) {
                    Timber.i("End openCollection: %s", path);
                }
            }
        }
        return mCollection;
    }

    /**
     * Collection time if possible, otherwise real time.
     */
    public synchronized Time getTimeSafe(Context context) {
        try {
            return getCol(context).getTime();
        } catch (Exception e) {
            return new SystemTime();
        }
    }

    /**
     * Call getCol(context) inside try / catch statement.
     * Send exception report and return null if there was an exception.
     * @param context
     * @return
     */
    public synchronized Collection getColSafe(Context context) {
        try {
            return getCol(context);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6758)) {
                AnkiDroidApp.sendExceptionReport(e, "CollectionHelper.getColSafe");
            }
            return null;
        }
    }

    /**
     * Close the {@link Collection}, optionally saving
     * @param save whether or not save before closing
     */
    public synchronized void closeCollection(boolean save, String reason) {
        if (!ListenerUtil.mutListener.listen(6759)) {
            Timber.i("closeCollection: %s", reason);
        }
        if (!ListenerUtil.mutListener.listen(6761)) {
            if (mCollection != null) {
                if (!ListenerUtil.mutListener.listen(6760)) {
                    mCollection.close(save);
                }
            }
        }
    }

    /**
     * @return Whether or not {@link Collection} and its child database are open.
     */
    public boolean colIsOpen() {
        return (ListenerUtil.mutListener.listen(6764) ? ((ListenerUtil.mutListener.listen(6763) ? ((ListenerUtil.mutListener.listen(6762) ? (mCollection != null || mCollection.getDb() != null) : (mCollection != null && mCollection.getDb() != null)) || mCollection.getDb().getDatabase() != null) : ((ListenerUtil.mutListener.listen(6762) ? (mCollection != null || mCollection.getDb() != null) : (mCollection != null && mCollection.getDb() != null)) && mCollection.getDb().getDatabase() != null)) || mCollection.getDb().getDatabase().isOpen()) : ((ListenerUtil.mutListener.listen(6763) ? ((ListenerUtil.mutListener.listen(6762) ? (mCollection != null || mCollection.getDb() != null) : (mCollection != null && mCollection.getDb() != null)) || mCollection.getDb().getDatabase() != null) : ((ListenerUtil.mutListener.listen(6762) ? (mCollection != null || mCollection.getDb() != null) : (mCollection != null && mCollection.getDb() != null)) && mCollection.getDb().getDatabase() != null)) && mCollection.getDb().getDatabase().isOpen()));
    }

    /**
     * Create the AnkiDroid directory if it doesn't exist and add a .nomedia file to it if needed.
     *
     * The AnkiDroid directory is a user preference stored under the "deckPath" key, and a sensible
     * default is chosen if the preference hasn't been created yet (i.e., on the first run).
     *
     * The presence of a .nomedia file indicates to media scanners that the directory must be
     * excluded from their search. We need to include this to avoid media scanners including
     * media files from the collection.media directory. The .nomedia file works at the directory
     * level, so placing it in the AnkiDroid directory will ensure media scanners will also exclude
     * the collection.media sub-directory.
     *
     * @param path  Directory to initialize
     * @throws StorageAccessException If no write access to directory
     */
    public static synchronized void initializeAnkiDroidDirectory(String path) throws StorageAccessException {
        // Create specified directory if it doesn't exit
        File dir = new File(path);
        if (!ListenerUtil.mutListener.listen(6766)) {
            if ((ListenerUtil.mutListener.listen(6765) ? (!dir.exists() || !dir.mkdirs()) : (!dir.exists() && !dir.mkdirs()))) {
                throw new StorageAccessException("Failed to create AnkiDroid directory " + path);
            }
        }
        if (!ListenerUtil.mutListener.listen(6767)) {
            if (!dir.canWrite()) {
                throw new StorageAccessException("No write access to AnkiDroid directory " + path);
            }
        }
        // Add a .nomedia file to it if it doesn't exist
        File nomedia = new File(dir, ".nomedia");
        if (!ListenerUtil.mutListener.listen(6769)) {
            if (!nomedia.exists()) {
                try {
                    if (!ListenerUtil.mutListener.listen(6768)) {
                        nomedia.createNewFile();
                    }
                } catch (IOException e) {
                    throw new StorageAccessException("Failed to create .nomedia file", e);
                }
            }
        }
    }

    /**
     * Try to access the current AnkiDroid directory
     * @return whether or not dir is accessible
     * @param context to get directory with
     */
    public static boolean isCurrentAnkiDroidDirAccessible(Context context) {
        try {
            if (!ListenerUtil.mutListener.listen(6770)) {
                initializeAnkiDroidDirectory(getCurrentAnkiDroidDirectory(context));
            }
            return true;
        } catch (StorageAccessException e) {
            return false;
        }
    }

    /**
     * Get the absolute path to a directory that is suitable to be the default starting location
     * for the AnkiDroid folder. This is a folder named "AnkiDroid" at the top level of the
     * external storage directory.
     * @return the folder path
     */
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5304
    @SuppressWarnings("deprecation")
    public static String getDefaultAnkiDroidDirectory() {
        return new File(Environment.getExternalStorageDirectory(), "AnkiDroid").getAbsolutePath();
    }

    /**
     * @return the path to the actual {@link Collection} file
     */
    public static String getCollectionPath(Context context) {
        return new File(getCurrentAnkiDroidDirectory(context), COLLECTION_FILENAME).getAbsolutePath();
    }

    /**
     * @return the absolute path to the AnkiDroid directory.
     */
    public static String getCurrentAnkiDroidDirectory(Context context) {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(context);
        return PreferenceExtensions.getOrSetString(preferences, "deckPath", CollectionHelper::getDefaultAnkiDroidDirectory);
    }

    /**
     * Get parent directory given the {@link Collection} path.
     * @param path path to AnkiDroid collection
     * @return path to AnkiDroid folder
     */
    private static String getParentDirectory(String path) {
        return new File(path).getParentFile().getAbsolutePath();
    }

    /**
     * This currently stores either:
     * An error message stating the reason that a storage check must be performed
     * OR
     * The current storage requirements, and the current available storage.
     */
    public static class CollectionIntegrityStorageCheck {

        @Nullable
        private final String mErrorMessage;

        // OR:
        @Nullable
        private final Long mRequiredSpace;

        @Nullable
        private final Long mFreeSpace;

        private CollectionIntegrityStorageCheck(long requiredSpace, long freeSpace) {
            this.mFreeSpace = freeSpace;
            this.mRequiredSpace = requiredSpace;
            this.mErrorMessage = null;
        }

        private CollectionIntegrityStorageCheck(@NonNull String errorMessage) {
            this.mRequiredSpace = null;
            this.mFreeSpace = null;
            this.mErrorMessage = errorMessage;
        }

        private static CollectionIntegrityStorageCheck fromError(String errorMessage) {
            return new CollectionIntegrityStorageCheck(errorMessage);
        }

        private static String defaultRequiredFreeSpace(Context context) {
            // tested, 1024 displays 157MB. 1000 displays 150
            long oneHundredFiftyMB = (ListenerUtil.mutListener.listen(6778) ? ((ListenerUtil.mutListener.listen(6774) ? (150 % 1000) : (ListenerUtil.mutListener.listen(6773) ? (150 / 1000) : (ListenerUtil.mutListener.listen(6772) ? (150 - 1000) : (ListenerUtil.mutListener.listen(6771) ? (150 + 1000) : (150 * 1000))))) % 1000) : (ListenerUtil.mutListener.listen(6777) ? ((ListenerUtil.mutListener.listen(6774) ? (150 % 1000) : (ListenerUtil.mutListener.listen(6773) ? (150 / 1000) : (ListenerUtil.mutListener.listen(6772) ? (150 - 1000) : (ListenerUtil.mutListener.listen(6771) ? (150 + 1000) : (150 * 1000))))) / 1000) : (ListenerUtil.mutListener.listen(6776) ? ((ListenerUtil.mutListener.listen(6774) ? (150 % 1000) : (ListenerUtil.mutListener.listen(6773) ? (150 / 1000) : (ListenerUtil.mutListener.listen(6772) ? (150 - 1000) : (ListenerUtil.mutListener.listen(6771) ? (150 + 1000) : (150 * 1000))))) - 1000) : (ListenerUtil.mutListener.listen(6775) ? ((ListenerUtil.mutListener.listen(6774) ? (150 % 1000) : (ListenerUtil.mutListener.listen(6773) ? (150 / 1000) : (ListenerUtil.mutListener.listen(6772) ? (150 - 1000) : (ListenerUtil.mutListener.listen(6771) ? (150 + 1000) : (150 * 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(6774) ? (150 % 1000) : (ListenerUtil.mutListener.listen(6773) ? (150 / 1000) : (ListenerUtil.mutListener.listen(6772) ? (150 - 1000) : (ListenerUtil.mutListener.listen(6771) ? (150 + 1000) : (150 * 1000))))) * 1000)))));
            return Formatter.formatShortFileSize(context, oneHundredFiftyMB);
        }

        public static CollectionIntegrityStorageCheck createInstance(Context context) {
            Long maybeCurrentCollectionSizeInBytes = getCollectionSize(context);
            if (!ListenerUtil.mutListener.listen(6780)) {
                if (maybeCurrentCollectionSizeInBytes == null) {
                    if (!ListenerUtil.mutListener.listen(6779)) {
                        Timber.w("Error obtaining collection file size.");
                    }
                    String requiredFreeSpace = defaultRequiredFreeSpace(context);
                    return fromError(context.getResources().getString(R.string.integrity_check_insufficient_space, requiredFreeSpace));
                }
            }
            // required in free disk space. - https://www.sqlite.org/lang_vacuum.html
            long requiredSpaceInBytes = (ListenerUtil.mutListener.listen(6784) ? (maybeCurrentCollectionSizeInBytes % 2) : (ListenerUtil.mutListener.listen(6783) ? (maybeCurrentCollectionSizeInBytes / 2) : (ListenerUtil.mutListener.listen(6782) ? (maybeCurrentCollectionSizeInBytes - 2) : (ListenerUtil.mutListener.listen(6781) ? (maybeCurrentCollectionSizeInBytes + 2) : (maybeCurrentCollectionSizeInBytes * 2)))));
            // We currently use the same directory as the collection for VACUUM/ANALYZE due to the SQLite APIs
            File collectionFile = new File(getCollectionPath(context));
            long freeSpace = FileUtil.getFreeDiskSpace(collectionFile, -1);
            if (!ListenerUtil.mutListener.listen(6791)) {
                if ((ListenerUtil.mutListener.listen(6789) ? (freeSpace >= -1) : (ListenerUtil.mutListener.listen(6788) ? (freeSpace <= -1) : (ListenerUtil.mutListener.listen(6787) ? (freeSpace > -1) : (ListenerUtil.mutListener.listen(6786) ? (freeSpace < -1) : (ListenerUtil.mutListener.listen(6785) ? (freeSpace != -1) : (freeSpace == -1))))))) {
                    if (!ListenerUtil.mutListener.listen(6790)) {
                        Timber.w("Error obtaining free space for '%s'", collectionFile.getPath());
                    }
                    String readableFileSize = Formatter.formatFileSize(context, requiredSpaceInBytes);
                    return fromError(context.getResources().getString(R.string.integrity_check_insufficient_space, readableFileSize));
                }
            }
            return new CollectionIntegrityStorageCheck(requiredSpaceInBytes, freeSpace);
        }

        public boolean shouldWarnOnIntegrityCheck() {
            return (ListenerUtil.mutListener.listen(6792) ? (this.mErrorMessage != null && fileSystemDoesNotHaveSpaceForBackup()) : (this.mErrorMessage != null || fileSystemDoesNotHaveSpaceForBackup()));
        }

        private boolean fileSystemDoesNotHaveSpaceForBackup() {
            if (!ListenerUtil.mutListener.listen(6795)) {
                // only to be called when mErrorMessage == null
                if ((ListenerUtil.mutListener.listen(6793) ? (mFreeSpace == null && mRequiredSpace == null) : (mFreeSpace == null || mRequiredSpace == null))) {
                    if (!ListenerUtil.mutListener.listen(6794)) {
                        Timber.e("fileSystemDoesNotHaveSpaceForBackup called in invalid state.");
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(6796)) {
                Timber.d("Required Free Space: %d. Current: %d", mRequiredSpace, mFreeSpace);
            }
            return (ListenerUtil.mutListener.listen(6801) ? (mRequiredSpace >= mFreeSpace) : (ListenerUtil.mutListener.listen(6800) ? (mRequiredSpace <= mFreeSpace) : (ListenerUtil.mutListener.listen(6799) ? (mRequiredSpace < mFreeSpace) : (ListenerUtil.mutListener.listen(6798) ? (mRequiredSpace != mFreeSpace) : (ListenerUtil.mutListener.listen(6797) ? (mRequiredSpace == mFreeSpace) : (mRequiredSpace > mFreeSpace))))));
        }

        public String getWarningDetails(Context context) {
            if (!ListenerUtil.mutListener.listen(6802)) {
                if (mErrorMessage != null) {
                    return mErrorMessage;
                }
            }
            if (!ListenerUtil.mutListener.listen(6805)) {
                if ((ListenerUtil.mutListener.listen(6803) ? (mFreeSpace == null && mRequiredSpace == null) : (mFreeSpace == null || mRequiredSpace == null))) {
                    if (!ListenerUtil.mutListener.listen(6804)) {
                        Timber.e("CollectionIntegrityCheckStatus in an invalid state");
                    }
                    String defaultRequiredFreeSpace = defaultRequiredFreeSpace(context);
                    return context.getResources().getString(R.string.integrity_check_insufficient_space, defaultRequiredFreeSpace);
                }
            }
            String required = Formatter.formatShortFileSize(context, mRequiredSpace);
            String insufficientSpace = context.getResources().getString(R.string.integrity_check_insufficient_space, required);
            // Also concat in the extra content showing the current free space.
            String currentFree = Formatter.formatShortFileSize(context, mFreeSpace);
            String insufficientSpaceCurrentFree = context.getResources().getString(R.string.integrity_check_insufficient_space_extra_content, currentFree);
            return insufficientSpace + insufficientSpaceCurrentFree;
        }
    }

    /**
     * Fetches additional collection data not required for
     * application startup
     *
     * Allows mandatory startup procedures to return early, speeding up startup. Less important tasks are offloaded here
     * No-op if data is already fetched
     */
    public static void loadCollectionComplete(Collection col) {
        if (!ListenerUtil.mutListener.listen(6806)) {
            col.getModels();
        }
    }

    public static boolean isFutureAnkiDroidVersion(Context context) throws UnknownDatabaseVersionException {
        int databaseVersion = getDatabaseVersion(context);
        return (ListenerUtil.mutListener.listen(6811) ? (databaseVersion >= SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(6810) ? (databaseVersion <= SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(6809) ? (databaseVersion < SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(6808) ? (databaseVersion != SCHEMA_VERSION) : (ListenerUtil.mutListener.listen(6807) ? (databaseVersion == SCHEMA_VERSION) : (databaseVersion > SCHEMA_VERSION))))));
    }

    public static int getDatabaseVersion(Context context) throws UnknownDatabaseVersionException {
        try {
            Collection col = getInstance().mCollection;
            return col.queryVer();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6812)) {
                Timber.w(e, "Failed to query version");
            }
            return Storage.getDatabaseVersion(getCollectionPath(context));
        }
    }
}
