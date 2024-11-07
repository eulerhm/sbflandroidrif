/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.content.Context;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StringConversionUtil {

    public static byte[] stringToByteArray(String s) {
        if (!ListenerUtil.mutListener.listen(55530)) {
            if (s == null) {
                return new byte[0];
            }
        }
        return s.getBytes();
    }

    public static String byteArrayToString(byte[] bytes) {
        return new String(bytes);
    }

    public static String secondsToString(long fullSeconds, boolean longFormat) {
        String[] pieces = secondsToPieces(fullSeconds);
        if ((ListenerUtil.mutListener.listen(55531) ? (longFormat && !pieces[0].equals("00")) : (longFormat || !pieces[0].equals("00")))) {
            return pieces[0] + ":" + pieces[1] + ":" + pieces[2];
        } else {
            return pieces[1] + ":" + pieces[2];
        }
    }

    private static String[] secondsToPieces(long fullSeconds) {
        String[] pieces = new String[3];
        if (!ListenerUtil.mutListener.listen(55536)) {
            pieces[0] = xDigit((int) ((ListenerUtil.mutListener.listen(55535) ? ((float) fullSeconds % 3600) : (ListenerUtil.mutListener.listen(55534) ? ((float) fullSeconds * 3600) : (ListenerUtil.mutListener.listen(55533) ? ((float) fullSeconds - 3600) : (ListenerUtil.mutListener.listen(55532) ? ((float) fullSeconds + 3600) : ((float) fullSeconds / 3600)))))), 2);
        }
        if (!ListenerUtil.mutListener.listen(55545)) {
            pieces[1] = xDigit((int) ((ListenerUtil.mutListener.listen(55544) ? (((ListenerUtil.mutListener.listen(55540) ? ((float) fullSeconds / 3600) : (ListenerUtil.mutListener.listen(55539) ? ((float) fullSeconds * 3600) : (ListenerUtil.mutListener.listen(55538) ? ((float) fullSeconds - 3600) : (ListenerUtil.mutListener.listen(55537) ? ((float) fullSeconds + 3600) : ((float) fullSeconds % 3600)))))) % 60) : (ListenerUtil.mutListener.listen(55543) ? (((ListenerUtil.mutListener.listen(55540) ? ((float) fullSeconds / 3600) : (ListenerUtil.mutListener.listen(55539) ? ((float) fullSeconds * 3600) : (ListenerUtil.mutListener.listen(55538) ? ((float) fullSeconds - 3600) : (ListenerUtil.mutListener.listen(55537) ? ((float) fullSeconds + 3600) : ((float) fullSeconds % 3600)))))) * 60) : (ListenerUtil.mutListener.listen(55542) ? (((ListenerUtil.mutListener.listen(55540) ? ((float) fullSeconds / 3600) : (ListenerUtil.mutListener.listen(55539) ? ((float) fullSeconds * 3600) : (ListenerUtil.mutListener.listen(55538) ? ((float) fullSeconds - 3600) : (ListenerUtil.mutListener.listen(55537) ? ((float) fullSeconds + 3600) : ((float) fullSeconds % 3600)))))) - 60) : (ListenerUtil.mutListener.listen(55541) ? (((ListenerUtil.mutListener.listen(55540) ? ((float) fullSeconds / 3600) : (ListenerUtil.mutListener.listen(55539) ? ((float) fullSeconds * 3600) : (ListenerUtil.mutListener.listen(55538) ? ((float) fullSeconds - 3600) : (ListenerUtil.mutListener.listen(55537) ? ((float) fullSeconds + 3600) : ((float) fullSeconds % 3600)))))) + 60) : (((ListenerUtil.mutListener.listen(55540) ? ((float) fullSeconds / 3600) : (ListenerUtil.mutListener.listen(55539) ? ((float) fullSeconds * 3600) : (ListenerUtil.mutListener.listen(55538) ? ((float) fullSeconds - 3600) : (ListenerUtil.mutListener.listen(55537) ? ((float) fullSeconds + 3600) : ((float) fullSeconds % 3600)))))) / 60)))))), 2);
        }
        if (!ListenerUtil.mutListener.listen(55550)) {
            pieces[2] = xDigit((int) ((ListenerUtil.mutListener.listen(55549) ? ((float) fullSeconds / 60) : (ListenerUtil.mutListener.listen(55548) ? ((float) fullSeconds * 60) : (ListenerUtil.mutListener.listen(55547) ? ((float) fullSeconds - 60) : (ListenerUtil.mutListener.listen(55546) ? ((float) fullSeconds + 60) : ((float) fullSeconds % 60)))))), 2);
        }
        return pieces;
    }

    public static String getDurationStringHuman(Context context, long fullSeconds) {
        long minutes = (ListenerUtil.mutListener.listen(55554) ? (TimeUnit.SECONDS.toMinutes(fullSeconds) / TimeUnit.HOURS.toMinutes(1)) : (ListenerUtil.mutListener.listen(55553) ? (TimeUnit.SECONDS.toMinutes(fullSeconds) * TimeUnit.HOURS.toMinutes(1)) : (ListenerUtil.mutListener.listen(55552) ? (TimeUnit.SECONDS.toMinutes(fullSeconds) - TimeUnit.HOURS.toMinutes(1)) : (ListenerUtil.mutListener.listen(55551) ? (TimeUnit.SECONDS.toMinutes(fullSeconds) + TimeUnit.HOURS.toMinutes(1)) : (TimeUnit.SECONDS.toMinutes(fullSeconds) % TimeUnit.HOURS.toMinutes(1))))));
        long seconds = (ListenerUtil.mutListener.listen(55558) ? (TimeUnit.SECONDS.toSeconds(fullSeconds) / TimeUnit.MINUTES.toSeconds(1)) : (ListenerUtil.mutListener.listen(55557) ? (TimeUnit.SECONDS.toSeconds(fullSeconds) * TimeUnit.MINUTES.toSeconds(1)) : (ListenerUtil.mutListener.listen(55556) ? (TimeUnit.SECONDS.toSeconds(fullSeconds) - TimeUnit.MINUTES.toSeconds(1)) : (ListenerUtil.mutListener.listen(55555) ? (TimeUnit.SECONDS.toSeconds(fullSeconds) + TimeUnit.MINUTES.toSeconds(1)) : (TimeUnit.SECONDS.toSeconds(fullSeconds) % TimeUnit.MINUTES.toSeconds(1))))));
        if (!ListenerUtil.mutListener.listen(55564)) {
            if ((ListenerUtil.mutListener.listen(55563) ? (minutes >= 0) : (ListenerUtil.mutListener.listen(55562) ? (minutes <= 0) : (ListenerUtil.mutListener.listen(55561) ? (minutes > 0) : (ListenerUtil.mutListener.listen(55560) ? (minutes < 0) : (ListenerUtil.mutListener.listen(55559) ? (minutes != 0) : (minutes == 0))))))) {
                return seconds + " " + context.getString(R.string.seconds);
            }
        }
        return minutes + " " + context.getString(R.string.minutes) + context.getString(R.string.and) + " " + seconds + " " + context.getString(R.string.seconds);
    }

    public static String getDurationString(long milliseconds) {
        return String.format(Locale.US, "%02d:%02d", (ListenerUtil.mutListener.listen(55568) ? (TimeUnit.MILLISECONDS.toMinutes(milliseconds) / TimeUnit.HOURS.toMinutes(1)) : (ListenerUtil.mutListener.listen(55567) ? (TimeUnit.MILLISECONDS.toMinutes(milliseconds) * TimeUnit.HOURS.toMinutes(1)) : (ListenerUtil.mutListener.listen(55566) ? (TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(1)) : (ListenerUtil.mutListener.listen(55565) ? (TimeUnit.MILLISECONDS.toMinutes(milliseconds) + TimeUnit.HOURS.toMinutes(1)) : (TimeUnit.MILLISECONDS.toMinutes(milliseconds) % TimeUnit.HOURS.toMinutes(1)))))), (ListenerUtil.mutListener.listen(55572) ? (TimeUnit.MILLISECONDS.toSeconds(milliseconds) / TimeUnit.MINUTES.toSeconds(1)) : (ListenerUtil.mutListener.listen(55571) ? (TimeUnit.MILLISECONDS.toSeconds(milliseconds) * TimeUnit.MINUTES.toSeconds(1)) : (ListenerUtil.mutListener.listen(55570) ? (TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(1)) : (ListenerUtil.mutListener.listen(55569) ? (TimeUnit.MILLISECONDS.toSeconds(milliseconds) + TimeUnit.MINUTES.toSeconds(1)) : (TimeUnit.MILLISECONDS.toSeconds(milliseconds) % TimeUnit.MINUTES.toSeconds(1)))))));
    }

    public static String xDigit(int number, int digits) {
        String res = String.valueOf(number);
        if (!ListenerUtil.mutListener.listen(55579)) {
            {
                long _loopCounter678 = 0;
                while ((ListenerUtil.mutListener.listen(55578) ? (res.length() >= digits) : (ListenerUtil.mutListener.listen(55577) ? (res.length() <= digits) : (ListenerUtil.mutListener.listen(55576) ? (res.length() > digits) : (ListenerUtil.mutListener.listen(55575) ? (res.length() != digits) : (ListenerUtil.mutListener.listen(55574) ? (res.length() == digits) : (res.length() < digits))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter678", ++_loopCounter678);
                    if (!ListenerUtil.mutListener.listen(55573)) {
                        res = "0" + res;
                    }
                }
            }
        }
        return res;
    }

    /**
     *  Join a string array using a delimiter.
     *  Ignore empty elements.
     */
    public static String join(CharSequence delimiter, String... pieces) {
        final StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(55589)) {
            {
                long _loopCounter679 = 0;
                for (String p : pieces) {
                    ListenerUtil.loopListener.listen("_loopCounter679", ++_loopCounter679);
                    if (!ListenerUtil.mutListener.listen(55588)) {
                        if (!TestUtil.empty(p)) {
                            if (!ListenerUtil.mutListener.listen(55586)) {
                                if ((ListenerUtil.mutListener.listen(55584) ? (builder.length() >= 0) : (ListenerUtil.mutListener.listen(55583) ? (builder.length() <= 0) : (ListenerUtil.mutListener.listen(55582) ? (builder.length() < 0) : (ListenerUtil.mutListener.listen(55581) ? (builder.length() != 0) : (ListenerUtil.mutListener.listen(55580) ? (builder.length() == 0) : (builder.length() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(55585)) {
                                        builder.append(delimiter);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(55587)) {
                                builder.append(p);
                            }
                        }
                    }
                }
            }
        }
        return builder.toString();
    }
}
