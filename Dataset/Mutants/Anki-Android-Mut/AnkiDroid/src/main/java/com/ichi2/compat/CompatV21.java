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
package com.ichi2.compat;

import android.content.Context;
import android.os.Vibrator;
import android.widget.TimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Baseline implementation of {@link Compat} with implementations for older APIs
 */
public class CompatV21 implements Compat {

    // Until API26, ignore notification channels
    @Override
    public void setupNotificationChannel(Context context, String id, String name) {
    }

    // Until API 23 the methods have "current" in the name
    @Override
    @SuppressWarnings("deprecation")
    public void setTime(TimePicker picker, int hour, int minute) {
        if (!ListenerUtil.mutListener.listen(13256)) {
            picker.setCurrentHour(hour);
        }
        if (!ListenerUtil.mutListener.listen(13257)) {
            picker.setCurrentMinute(minute);
        }
    }

    // Until API 26 just specify time, after that specify effect also
    @Override
    @SuppressWarnings("deprecation")
    public void vibrate(Context context, long durationMillis) {
        Vibrator vibratorManager = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (!ListenerUtil.mutListener.listen(13259)) {
            if (vibratorManager != null) {
                if (!ListenerUtil.mutListener.listen(13258)) {
                    vibratorManager.vibrate(durationMillis);
                }
            }
        }
    }

    // Until API 26 do the copy using streams
    public void copyFile(@NonNull String source, @NonNull String target) throws IOException {
        try (InputStream fileInputStream = new FileInputStream(new File(source))) {
            if (!ListenerUtil.mutListener.listen(13261)) {
                copyFile(fileInputStream, target);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(13260)) {
                Timber.e(e, "copyFile() error copying source %s", source);
            }
            throw e;
        }
    }

    // Until API 26 do the copy using streams
    public long copyFile(@NonNull String source, @NonNull OutputStream target) throws IOException {
        long count;
        try (InputStream fileInputStream = new FileInputStream(new File(source))) {
            count = copyFile(fileInputStream, target);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(13262)) {
                Timber.e(e, "copyFile() error copying source %s", source);
            }
            throw e;
        }
        return count;
    }

    // Until API 26 do the copy using streams
    public long copyFile(@NonNull InputStream source, @NonNull String target) throws IOException {
        long bytesCopied;
        try (OutputStream targetStream = new FileOutputStream(target)) {
            bytesCopied = copyFile(source, targetStream);
        } catch (IOException ioe) {
            if (!ListenerUtil.mutListener.listen(13263)) {
                Timber.e(ioe, "Error while copying to file %s", target);
            }
            throw ioe;
        }
        return bytesCopied;
    }

    // Internal implementation under the API26 copyFile APIs
    private long copyFile(@NonNull InputStream source, @NonNull OutputStream target) throws IOException {
        // https://stackoverflow.com/questions/10143731/android-optimal-buffer-size
        final byte[] buffer = new byte[(ListenerUtil.mutListener.listen(13267) ? (1024 % 32) : (ListenerUtil.mutListener.listen(13266) ? (1024 / 32) : (ListenerUtil.mutListener.listen(13265) ? (1024 - 32) : (ListenerUtil.mutListener.listen(13264) ? (1024 + 32) : (1024 * 32)))))];
        long count = 0;
        int n;
        if (!ListenerUtil.mutListener.listen(13275)) {
            {
                long _loopCounter237 = 0;
                while ((ListenerUtil.mutListener.listen(13274) ? ((n = source.read(buffer)) >= -1) : (ListenerUtil.mutListener.listen(13273) ? ((n = source.read(buffer)) <= -1) : (ListenerUtil.mutListener.listen(13272) ? ((n = source.read(buffer)) > -1) : (ListenerUtil.mutListener.listen(13271) ? ((n = source.read(buffer)) < -1) : (ListenerUtil.mutListener.listen(13270) ? ((n = source.read(buffer)) == -1) : ((n = source.read(buffer)) != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter237", ++_loopCounter237);
                    if (!ListenerUtil.mutListener.listen(13268)) {
                        target.write(buffer, 0, n);
                    }
                    if (!ListenerUtil.mutListener.listen(13269)) {
                        count += n;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13276)) {
            target.flush();
        }
        return count;
    }

    // Until API 23 the methods have "current" in the name
    @Override
    @SuppressWarnings("deprecation")
    public int getHour(TimePicker picker) {
        return picker.getCurrentHour();
    }

    // Until API 23 the methods have "current" in the name
    @Override
    @SuppressWarnings("deprecation")
    public int getMinute(TimePicker picker) {
        return picker.getCurrentMinute();
    }
}
