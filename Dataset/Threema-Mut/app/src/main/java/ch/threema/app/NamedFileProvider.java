/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import ch.threema.app.utils.TestUtil;
import static org.xmlpull.v1.XmlPullParser.END_DOCUMENT;
import static org.xmlpull.v1.XmlPullParser.START_TAG;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NamedFileProvider extends FileProvider {

    private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";

    private static final String TAG_ROOT_PATH = "root-path";

    private static final String TAG_FILES_PATH = "files-path";

    private static final String TAG_CACHE_PATH = "cache-path";

    private static final String TAG_EXTERNAL = "external-path";

    private static final String TAG_EXTERNAL_FILES = "external-files-path";

    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";

    private static final String TAG_EXTERNAL_MEDIA = "external-media-path";

    private static final String ATTR_NAME = "name";

    private static final String ATTR_PATH = "path";

    private static final File DEVICE_ROOT = new File("/");

    private static final String[] COLUMNS = { OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE };

    private PathStrategy mStrategy;

    @GuardedBy("sCache")
    private static final HashMap<String, PathStrategy> sCache = new HashMap<>();

    private static final SimpleArrayMap<Uri, String> sUriToDisplayNameMap = new SimpleArrayMap<>();

    @Override
    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo info) {
        if (!ListenerUtil.mutListener.listen(65191)) {
            super.attachInfo(context, info);
        }
        if (!ListenerUtil.mutListener.listen(65192)) {
            mStrategy = getPathStrategy(context, info.authority);
        }
    }

    @Override
    public Cursor query(@NonNull final Uri uri, String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
        if (!ListenerUtil.mutListener.listen(65194)) {
            if (projection == null) {
                if (!ListenerUtil.mutListener.listen(65193)) {
                    projection = COLUMNS;
                }
            }
        }
        final File file = mStrategy.getFileForUri(uri);
        String[] cols = new String[projection.length];
        Object[] values = new Object[projection.length];
        int i = 0;
        if (!ListenerUtil.mutListener.listen(65202)) {
            {
                long _loopCounter792 = 0;
                for (String col : projection) {
                    ListenerUtil.loopListener.listen("_loopCounter792", ++_loopCounter792);
                    if (!ListenerUtil.mutListener.listen(65201)) {
                        if (OpenableColumns.DISPLAY_NAME.equals(col)) {
                            if (!ListenerUtil.mutListener.listen(65197)) {
                                cols[i] = OpenableColumns.DISPLAY_NAME;
                            }
                            synchronized (sUriToDisplayNameMap) {
                                if (!ListenerUtil.mutListener.listen(65200)) {
                                    if (TestUtil.empty(sUriToDisplayNameMap.get(uri))) {
                                        if (!ListenerUtil.mutListener.listen(65199)) {
                                            values[i++] = file.getName();
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(65198)) {
                                            values[i++] = sUriToDisplayNameMap.get(uri);
                                        }
                                    }
                                }
                            }
                        } else if (OpenableColumns.SIZE.equals(col)) {
                            if (!ListenerUtil.mutListener.listen(65195)) {
                                cols[i] = OpenableColumns.SIZE;
                            }
                            if (!ListenerUtil.mutListener.listen(65196)) {
                                values[i++] = file.length();
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65203)) {
            cols = copyOf(cols, i);
        }
        if (!ListenerUtil.mutListener.listen(65204)) {
            values = copyOf(values, i);
        }
        final MatrixCursor cursor = new MatrixCursor(cols, 1);
        if (!ListenerUtil.mutListener.listen(65205)) {
            cursor.addRow(values);
        }
        return cursor;
    }

    /**
     *  Return a content URI for a given {@link File}. Specific temporary
     *  permissions for the content URI can be set with
     *  {@link Context#grantUriPermission(String, Uri, int)}, or added
     *  to an {@link Intent} by calling {@link Intent#setData(Uri) setData()} and then
     *  {@link Intent#setFlags(int) setFlags()}; in both cases, the applicable flags are
     *  {@link Intent#FLAG_GRANT_READ_URI_PERMISSION} and
     *  {@link Intent#FLAG_GRANT_WRITE_URI_PERMISSION}. A FileProvider can only return a
     *  <code>content</code> {@link Uri} for file paths defined in their <code>&lt;paths&gt;</code>
     *  meta-data element. See the Class Overview for more information.
     *
     *  @param context A {@link Context} for the current component.
     *  @param authority The authority of a {@link FileProvider} defined in a
     *             {@code <provider>} element in your app's manifest.
     *  @param file A {@link File} pointing to the filename for which you want a
     *  <code>content</code> {@link Uri}.
     *  @param filename File name to be used for this file. Will be provided to consumers in the DISPLAY_NAME xolumn
     *  @return A content URI for the file.
     *  @throws IllegalArgumentException When the given {@link File} is outside
     *  the paths supported by the provider.
     */
    public static Uri getUriForFile(@NonNull Context context, @NonNull String authority, @NonNull File file, @Nullable String filename) {
        final Uri uri = FileProvider.getUriForFile(context, authority, file);
        if (!ListenerUtil.mutListener.listen(65207)) {
            if (!TestUtil.empty(filename)) {
                synchronized (sUriToDisplayNameMap) {
                    if (!ListenerUtil.mutListener.listen(65206)) {
                        sUriToDisplayNameMap.put(uri, filename);
                    }
                }
            }
        }
        return uri;
    }

    /**
     *  Strategy for mapping between {@link File} and {@link Uri}.
     *  <p>
     *  Strategies must be symmetric so that mapping a {@link File} to a
     *  {@link Uri} and then back to a {@link File} points at the original
     *  target.
     *  <p>
     *  Strategies must remain consistent across app launches, and not rely on
     *  dynamic state. This ensures that any generated {@link Uri} can still be
     *  resolved if your process is killed and later restarted.
     *
     *  @see SimplePathStrategy
     */
    interface PathStrategy {

        /**
         *  Return a {@link File} that represents the given {@link Uri}.
         */
        File getFileForUri(Uri uri);
    }

    /**
     *  Strategy that provides access to files living under a narrow whitelist of
     *  filesystem roots. It will throw {@link SecurityException} if callers try
     *  accessing files outside the configured roots.
     *  <p>
     *  For example, if configured with
     *  {@code addRoot("myfiles", context.getFilesDir())}, then
     *  {@code context.getFileStreamPath("foo.txt")} would map to
     *  {@code content://myauthority/myfiles/foo.txt}.
     */
    static class SimplePathStrategy implements PathStrategy {

        private final HashMap<String, File> mRoots = new HashMap<String, File>();

        SimplePathStrategy(String authority) {
        }

        /**
         *  Add a mapping from a name to a filesystem root. The provider only offers
         *  access to files that live under configured roots.
         */
        void addRoot(String name, File root) {
            if (!ListenerUtil.mutListener.listen(65208)) {
                if (TextUtils.isEmpty(name)) {
                    throw new IllegalArgumentException("Name must not be empty");
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(65209)) {
                    // Resolve to canonical path to keep path checking fast
                    root = root.getCanonicalFile();
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + root, e);
            }
            if (!ListenerUtil.mutListener.listen(65210)) {
                mRoots.put(name, root);
            }
        }

        @Override
        public File getFileForUri(Uri uri) {
            String path = uri.getEncodedPath();
            final int splitIndex = path.indexOf('/', 1);
            final String tag = Uri.decode(path.substring(1, splitIndex));
            if (!ListenerUtil.mutListener.listen(65215)) {
                path = Uri.decode(path.substring((ListenerUtil.mutListener.listen(65214) ? (splitIndex % 1) : (ListenerUtil.mutListener.listen(65213) ? (splitIndex / 1) : (ListenerUtil.mutListener.listen(65212) ? (splitIndex * 1) : (ListenerUtil.mutListener.listen(65211) ? (splitIndex - 1) : (splitIndex + 1)))))));
            }
            final File root = mRoots.get(tag);
            if (!ListenerUtil.mutListener.listen(65216)) {
                if (root == null) {
                    throw new IllegalArgumentException("Unable to find configured root for " + uri);
                }
            }
            File file = new File(root, path);
            try {
                if (!ListenerUtil.mutListener.listen(65217)) {
                    file = file.getCanonicalFile();
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
            if (!ListenerUtil.mutListener.listen(65218)) {
                if (!file.getPath().startsWith(root.getPath())) {
                    throw new SecurityException("Resolved path jumped beyond configured root");
                }
            }
            return file;
        }
    }

    /**
     *  Return {@link PathStrategy} for given authority, either by parsing or
     *  returning from cache.
     */
    private static PathStrategy getPathStrategy(Context context, String authority) {
        PathStrategy strat;
        synchronized (sCache) {
            strat = sCache.get(authority);
            if (strat == null) {
                try {
                    strat = parsePathStrategy(context, authority);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to parse " + META_DATA_FILE_PROVIDER_PATHS + " meta-data", e);
                } catch (XmlPullParserException e) {
                    throw new IllegalArgumentException("Failed to parse " + META_DATA_FILE_PROVIDER_PATHS + " meta-data", e);
                }
                if (!ListenerUtil.mutListener.listen(65219)) {
                    sCache.put(authority, strat);
                }
            }
        }
        return strat;
    }

    /**
     *  Parse and return {@link PathStrategy} for given authority as defined in
     *  {@link #META_DATA_FILE_PROVIDER_PATHS} {@code <meta-data>}.
     */
    private static PathStrategy parsePathStrategy(Context context, String authority) throws IOException, XmlPullParserException {
        final SimplePathStrategy strat = new SimplePathStrategy(authority);
        final ProviderInfo info = context.getPackageManager().resolveContentProvider(authority, PackageManager.GET_META_DATA);
        if (!ListenerUtil.mutListener.listen(65220)) {
            if (info == null) {
                throw new IllegalArgumentException("Couldn't find meta-data for provider with authority " + authority);
            }
        }
        final XmlResourceParser in = info.loadXmlMetaData(context.getPackageManager(), META_DATA_FILE_PROVIDER_PATHS);
        if (!ListenerUtil.mutListener.listen(65221)) {
            if (in == null) {
                throw new IllegalArgumentException("Missing " + META_DATA_FILE_PROVIDER_PATHS + " meta-data");
            }
        }
        int type;
        if (!ListenerUtil.mutListener.listen(65257)) {
            {
                long _loopCounter793 = 0;
                while ((type = in.next()) != END_DOCUMENT) {
                    ListenerUtil.loopListener.listen("_loopCounter793", ++_loopCounter793);
                    if (!ListenerUtil.mutListener.listen(65256)) {
                        if (type == START_TAG) {
                            final String tag = in.getName();
                            final String name = in.getAttributeValue(null, ATTR_NAME);
                            String path = in.getAttributeValue(null, ATTR_PATH);
                            File target = null;
                            if (!ListenerUtil.mutListener.listen(65253)) {
                                if (TAG_ROOT_PATH.equals(tag)) {
                                    if (!ListenerUtil.mutListener.listen(65252)) {
                                        target = DEVICE_ROOT;
                                    }
                                } else if (TAG_FILES_PATH.equals(tag)) {
                                    if (!ListenerUtil.mutListener.listen(65251)) {
                                        target = context.getFilesDir();
                                    }
                                } else if (TAG_CACHE_PATH.equals(tag)) {
                                    if (!ListenerUtil.mutListener.listen(65250)) {
                                        target = context.getCacheDir();
                                    }
                                } else if (TAG_EXTERNAL.equals(tag)) {
                                    if (!ListenerUtil.mutListener.listen(65249)) {
                                        target = Environment.getExternalStorageDirectory();
                                    }
                                } else if (TAG_EXTERNAL_FILES.equals(tag)) {
                                    File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
                                    if (!ListenerUtil.mutListener.listen(65248)) {
                                        if ((ListenerUtil.mutListener.listen(65246) ? (externalFilesDirs.length >= 0) : (ListenerUtil.mutListener.listen(65245) ? (externalFilesDirs.length <= 0) : (ListenerUtil.mutListener.listen(65244) ? (externalFilesDirs.length < 0) : (ListenerUtil.mutListener.listen(65243) ? (externalFilesDirs.length != 0) : (ListenerUtil.mutListener.listen(65242) ? (externalFilesDirs.length == 0) : (externalFilesDirs.length > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(65247)) {
                                                target = externalFilesDirs[0];
                                            }
                                        }
                                    }
                                } else if (TAG_EXTERNAL_CACHE.equals(tag)) {
                                    File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context);
                                    if (!ListenerUtil.mutListener.listen(65241)) {
                                        if ((ListenerUtil.mutListener.listen(65239) ? (externalCacheDirs.length >= 0) : (ListenerUtil.mutListener.listen(65238) ? (externalCacheDirs.length <= 0) : (ListenerUtil.mutListener.listen(65237) ? (externalCacheDirs.length < 0) : (ListenerUtil.mutListener.listen(65236) ? (externalCacheDirs.length != 0) : (ListenerUtil.mutListener.listen(65235) ? (externalCacheDirs.length == 0) : (externalCacheDirs.length > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(65240)) {
                                                target = externalCacheDirs[0];
                                            }
                                        }
                                    }
                                } else if ((ListenerUtil.mutListener.listen(65227) ? ((ListenerUtil.mutListener.listen(65226) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65225) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65224) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65223) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65222) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || TAG_EXTERNAL_MEDIA.equals(tag)) : ((ListenerUtil.mutListener.listen(65226) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65225) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65224) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65223) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65222) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && TAG_EXTERNAL_MEDIA.equals(tag)))) {
                                    File[] externalMediaDirs = context.getExternalMediaDirs();
                                    if (!ListenerUtil.mutListener.listen(65234)) {
                                        if ((ListenerUtil.mutListener.listen(65232) ? (externalMediaDirs.length >= 0) : (ListenerUtil.mutListener.listen(65231) ? (externalMediaDirs.length <= 0) : (ListenerUtil.mutListener.listen(65230) ? (externalMediaDirs.length < 0) : (ListenerUtil.mutListener.listen(65229) ? (externalMediaDirs.length != 0) : (ListenerUtil.mutListener.listen(65228) ? (externalMediaDirs.length == 0) : (externalMediaDirs.length > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(65233)) {
                                                target = externalMediaDirs[0];
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(65255)) {
                                if (target != null) {
                                    if (!ListenerUtil.mutListener.listen(65254)) {
                                        strat.addRoot(name, buildPath(target, path));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return strat;
    }

    private static File buildPath(File base, String... segments) {
        File cur = base;
        if (!ListenerUtil.mutListener.listen(65260)) {
            {
                long _loopCounter794 = 0;
                for (String segment : segments) {
                    ListenerUtil.loopListener.listen("_loopCounter794", ++_loopCounter794);
                    if (!ListenerUtil.mutListener.listen(65259)) {
                        if (segment != null) {
                            if (!ListenerUtil.mutListener.listen(65258)) {
                                cur = new File(cur, segment);
                            }
                        }
                    }
                }
            }
        }
        return cur;
    }

    private static String[] copyOf(String[] original, int newLength) {
        final String[] result = new String[newLength];
        if (!ListenerUtil.mutListener.listen(65261)) {
            System.arraycopy(original, 0, result, 0, newLength);
        }
        return result;
    }

    private static Object[] copyOf(Object[] original, int newLength) {
        final Object[] result = new Object[newLength];
        if (!ListenerUtil.mutListener.listen(65262)) {
            System.arraycopy(original, 0, result, 0, newLength);
        }
        return result;
    }
}
