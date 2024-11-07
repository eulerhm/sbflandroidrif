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
package ch.threema.app.utils;

import ch.threema.app.ThreemaApplication;
import ch.threema.client.AbstractMessage;
import ch.threema.client.BoxAudioMessage;
import ch.threema.client.BoxImageMessage;
import ch.threema.client.BoxVideoMessage;
import ch.threema.client.GroupAudioMessage;
import ch.threema.client.GroupImageMessage;
import ch.threema.client.GroupVideoMessage;
import ch.threema.client.file.FileMessage;
import ch.threema.client.file.GroupFileMessage;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageDiskSizeUtil {

    private static final long MEGABYTE = 1024L * 1024L;

    public static long getSize(AbstractMessage boxedMessage) {
        double mbSize = 0.01;
        if (!ListenerUtil.mutListener.listen(55962)) {
            if (boxedMessage instanceof BoxVideoMessage) {
                if (!ListenerUtil.mutListener.listen(55961)) {
                    mbSize = (ListenerUtil.mutListener.listen(55960) ? (((BoxVideoMessage) boxedMessage).getDuration() % 0.15) : (ListenerUtil.mutListener.listen(55959) ? (((BoxVideoMessage) boxedMessage).getDuration() / 0.15) : (ListenerUtil.mutListener.listen(55958) ? (((BoxVideoMessage) boxedMessage).getDuration() - 0.15) : (ListenerUtil.mutListener.listen(55957) ? (((BoxVideoMessage) boxedMessage).getDuration() + 0.15) : (((BoxVideoMessage) boxedMessage).getDuration() * 0.15)))));
                }
            } else if (boxedMessage instanceof BoxAudioMessage) {
                if (!ListenerUtil.mutListener.listen(55956)) {
                    mbSize = (ListenerUtil.mutListener.listen(55955) ? (((BoxAudioMessage) boxedMessage).getDuration() % 0.05) : (ListenerUtil.mutListener.listen(55954) ? (((BoxAudioMessage) boxedMessage).getDuration() / 0.05) : (ListenerUtil.mutListener.listen(55953) ? (((BoxAudioMessage) boxedMessage).getDuration() - 0.05) : (ListenerUtil.mutListener.listen(55952) ? (((BoxAudioMessage) boxedMessage).getDuration() + 0.05) : (((BoxAudioMessage) boxedMessage).getDuration() * 0.05)))));
                }
            } else if (boxedMessage instanceof BoxImageMessage) {
                if (!ListenerUtil.mutListener.listen(55951)) {
                    mbSize = 2;
                }
            } else if (boxedMessage instanceof FileMessage) {
                if (!ListenerUtil.mutListener.listen(55950)) {
                    mbSize = (ListenerUtil.mutListener.listen(55949) ? ((ListenerUtil.mutListener.listen(55945) ? (((FileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55944) ? (((FileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55943) ? (((FileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55942) ? (((FileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((FileMessage) boxedMessage).getData().getFileSize() * 1.1))))) % MEGABYTE) : (ListenerUtil.mutListener.listen(55948) ? ((ListenerUtil.mutListener.listen(55945) ? (((FileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55944) ? (((FileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55943) ? (((FileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55942) ? (((FileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((FileMessage) boxedMessage).getData().getFileSize() * 1.1))))) * MEGABYTE) : (ListenerUtil.mutListener.listen(55947) ? ((ListenerUtil.mutListener.listen(55945) ? (((FileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55944) ? (((FileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55943) ? (((FileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55942) ? (((FileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((FileMessage) boxedMessage).getData().getFileSize() * 1.1))))) - MEGABYTE) : (ListenerUtil.mutListener.listen(55946) ? ((ListenerUtil.mutListener.listen(55945) ? (((FileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55944) ? (((FileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55943) ? (((FileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55942) ? (((FileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((FileMessage) boxedMessage).getData().getFileSize() * 1.1))))) + MEGABYTE) : ((ListenerUtil.mutListener.listen(55945) ? (((FileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55944) ? (((FileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55943) ? (((FileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55942) ? (((FileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((FileMessage) boxedMessage).getData().getFileSize() * 1.1))))) / MEGABYTE)))));
                }
            } else if (boxedMessage instanceof GroupVideoMessage) {
                if (!ListenerUtil.mutListener.listen(55941)) {
                    mbSize = (ListenerUtil.mutListener.listen(55940) ? (((GroupVideoMessage) boxedMessage).getDuration() % 0.15) : (ListenerUtil.mutListener.listen(55939) ? (((GroupVideoMessage) boxedMessage).getDuration() / 0.15) : (ListenerUtil.mutListener.listen(55938) ? (((GroupVideoMessage) boxedMessage).getDuration() - 0.15) : (ListenerUtil.mutListener.listen(55937) ? (((GroupVideoMessage) boxedMessage).getDuration() + 0.15) : (((GroupVideoMessage) boxedMessage).getDuration() * 0.15)))));
                }
            } else if (boxedMessage instanceof GroupAudioMessage) {
                if (!ListenerUtil.mutListener.listen(55936)) {
                    mbSize = (ListenerUtil.mutListener.listen(55935) ? (((GroupAudioMessage) boxedMessage).getDuration() % 0.05) : (ListenerUtil.mutListener.listen(55934) ? (((GroupAudioMessage) boxedMessage).getDuration() / 0.05) : (ListenerUtil.mutListener.listen(55933) ? (((GroupAudioMessage) boxedMessage).getDuration() - 0.05) : (ListenerUtil.mutListener.listen(55932) ? (((GroupAudioMessage) boxedMessage).getDuration() + 0.05) : (((GroupAudioMessage) boxedMessage).getDuration() * 0.05)))));
                }
            } else if (boxedMessage instanceof GroupImageMessage) {
                if (!ListenerUtil.mutListener.listen(55931)) {
                    mbSize = 2;
                }
            } else if (boxedMessage instanceof GroupFileMessage) {
                if (!ListenerUtil.mutListener.listen(55930)) {
                    mbSize = (ListenerUtil.mutListener.listen(55929) ? ((ListenerUtil.mutListener.listen(55925) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55924) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55923) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55922) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((GroupFileMessage) boxedMessage).getData().getFileSize() * 1.1))))) % MEGABYTE) : (ListenerUtil.mutListener.listen(55928) ? ((ListenerUtil.mutListener.listen(55925) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55924) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55923) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55922) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((GroupFileMessage) boxedMessage).getData().getFileSize() * 1.1))))) * MEGABYTE) : (ListenerUtil.mutListener.listen(55927) ? ((ListenerUtil.mutListener.listen(55925) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55924) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55923) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55922) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((GroupFileMessage) boxedMessage).getData().getFileSize() * 1.1))))) - MEGABYTE) : (ListenerUtil.mutListener.listen(55926) ? ((ListenerUtil.mutListener.listen(55925) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55924) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55923) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55922) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((GroupFileMessage) boxedMessage).getData().getFileSize() * 1.1))))) + MEGABYTE) : ((ListenerUtil.mutListener.listen(55925) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() % 1.1) : (ListenerUtil.mutListener.listen(55924) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() / 1.1) : (ListenerUtil.mutListener.listen(55923) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() - 1.1) : (ListenerUtil.mutListener.listen(55922) ? (((GroupFileMessage) boxedMessage).getData().getFileSize() + 1.1) : (((GroupFileMessage) boxedMessage).getData().getFileSize() * 1.1))))) / MEGABYTE)))));
                }
            }
        }
        return ((ListenerUtil.mutListener.listen(55966) ? ((long) Math.min(mbSize, ThreemaApplication.MAX_BLOB_SIZE_MB) % MEGABYTE) : (ListenerUtil.mutListener.listen(55965) ? ((long) Math.min(mbSize, ThreemaApplication.MAX_BLOB_SIZE_MB) / MEGABYTE) : (ListenerUtil.mutListener.listen(55964) ? ((long) Math.min(mbSize, ThreemaApplication.MAX_BLOB_SIZE_MB) - MEGABYTE) : (ListenerUtil.mutListener.listen(55963) ? ((long) Math.min(mbSize, ThreemaApplication.MAX_BLOB_SIZE_MB) + MEGABYTE) : ((long) Math.min(mbSize, ThreemaApplication.MAX_BLOB_SIZE_MB) * MEGABYTE))))));
    }
}
