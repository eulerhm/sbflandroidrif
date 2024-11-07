/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.mediaattacher;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.MimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Query the system media store and return images and videos found on the system.
 */
public class MediaRepository {

    private static final Logger logger = LoggerFactory.getLogger(MediaRepository.class);

    private final Context appContext;

    public MediaRepository(Context appContext) {
        this.appContext = appContext;
    }

    /**
     *  Query the Android media store via content resolver.
     *  Return images and videos, sorted by modification date.
     *  This method is synchronous.
     */
    @WorkerThread
    public List<MediaAttachItem> getMediaFromMediaStore() {
        final String[] imageProjection = this.getImageProjection();
        final String[] videoProjection = this.getVideoProjection();
        final List<MediaAttachItem> mediaList = new ArrayList<>();
        // Process images
        try (Cursor imageCursor = appContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageProjection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " DESC")) {
            if (!ListenerUtil.mutListener.listen(29756)) {
                addToMediaResults(imageCursor, mediaList, false);
            }
        }
        // Process videos
        try (Cursor videoCursor = appContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoProjection, null, null, MediaStore.Video.Media.DATE_MODIFIED + " DESC")) {
            if (!ListenerUtil.mutListener.listen(29757)) {
                addToMediaResults(videoCursor, mediaList, true);
            }
        }
        if (!ListenerUtil.mutListener.listen(29758)) {
            // Sort media list from most recent descending
            Collections.sort(mediaList, (o1, o2) -> Double.compare(o2.getDateModified(), o1.getDateModified()));
        }
        return mediaList;
    }

    @SuppressLint("InlinedApi")
    @NonNull
    private String[] getImageProjection() {
        return new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATE_ADDED, MediaStore.Images.ImageColumns.DATE_TAKEN, MediaStore.Images.ImageColumns.DATE_MODIFIED, MediaStore.Images.ImageColumns.DISPLAY_NAME, MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, MediaStore.Images.ImageColumns.ORIENTATION, MediaStore.Images.ImageColumns.MIME_TYPE, MediaStore.Images.ImageColumns.IS_PRIVATE };
    }

    @SuppressLint("InlinedApi")
    @NonNull
    private String[] getVideoProjection() {
        return new String[] { MediaStore.Video.VideoColumns._ID, MediaStore.Video.VideoColumns.DATE_ADDED, MediaStore.Video.VideoColumns.DATE_TAKEN, MediaStore.Video.VideoColumns.DATE_MODIFIED, MediaStore.Video.VideoColumns.DISPLAY_NAME, MediaStore.Video.VideoColumns.DURATION, MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME, MediaStore.Video.VideoColumns.MIME_TYPE, MediaStore.Video.VideoColumns.IS_PRIVATE };
    }

    /**
     *  Consume the cursor and add the entries to the provided media list.
     *  The cursor will not be closed, make sure to run this method inside a try-with-resources block!
     */
    @SuppressLint("NewApi")
    @WorkerThread
    private void addToMediaResults(@Nullable Cursor cursor, @NonNull List<MediaAttachItem> mediaList, boolean isVideo) {
        if (!ListenerUtil.mutListener.listen(29772)) {
            if (cursor != null) {
                if (!ListenerUtil.mutListener.listen(29771)) {
                    {
                        long _loopCounter197 = 0;
                        while (cursor.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter197", ++_loopCounter197);
                            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                            long dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_ADDED));
                            long dateModified = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_MODIFIED));
                            long dateTaken = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_TAKEN));
                            String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME));
                            String bucketName = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME));
                            int orientation = 0;
                            int duration = 0;
                            String mimeType = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE));
                            Uri contentUri;
                            if (isVideo) {
                                contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);
                                if (!ListenerUtil.mutListener.listen(29760)) {
                                    duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION));
                                }
                            } else {
                                contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                                if (!ListenerUtil.mutListener.listen(29759)) {
                                    orientation = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.ORIENTATION));
                                }
                            }
                            int type;
                            if (MimeUtil.isVideoFile(mimeType)) {
                                type = MediaItem.TYPE_VIDEO;
                                if (!ListenerUtil.mutListener.listen(29769)) {
                                    if ((ListenerUtil.mutListener.listen(29765) ? (duration >= 0) : (ListenerUtil.mutListener.listen(29764) ? (duration <= 0) : (ListenerUtil.mutListener.listen(29763) ? (duration > 0) : (ListenerUtil.mutListener.listen(29762) ? (duration < 0) : (ListenerUtil.mutListener.listen(29761) ? (duration != 0) : (duration == 0))))))) {
                                        // do not use automatic resource management on MediaMetadataRetriever
                                        MediaMetadataRetriever metaDataRetriever = new MediaMetadataRetriever();
                                        try {
                                            if (!ListenerUtil.mutListener.listen(29767)) {
                                                metaDataRetriever.setDataSource(ThreemaApplication.getAppContext(), contentUri);
                                            }
                                            if (!ListenerUtil.mutListener.listen(29768)) {
                                                duration = Integer.parseInt(metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                                            }
                                        } catch (Exception ignored) {
                                        } finally {
                                            if (!ListenerUtil.mutListener.listen(29766)) {
                                                metaDataRetriever.release();
                                            }
                                        }
                                    }
                                }
                            } else if (MimeUtil.isGifFile(mimeType)) {
                                type = MediaItem.TYPE_GIF;
                            } else {
                                type = MediaItem.TYPE_IMAGE;
                            }
                            MediaAttachItem item = new MediaAttachItem(id, dateAdded, dateTaken, dateModified, contentUri, displayName, bucketName, orientation, duration, type);
                            if (!ListenerUtil.mutListener.listen(29770)) {
                                mediaList.add(item);
                            }
                        }
                    }
                }
            }
        }
    }
}
