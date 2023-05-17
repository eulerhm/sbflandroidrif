/**
 * *************************************************************************************
 *  Copyright (c) 2020 Mike Hardy <github@mikehardy.net>                                 *
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
package com.ichi2.utils;

import java.util.Random;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NoteFieldDecorator {

    private static final Random random = new Random();

    private static final String[] huevoDecorations = { "\uD83D\uDC8C", "\uD83D\uDE3B", "\uD83D\uDC96", "\uD83D\uDC97", "\uD83D\uDC93", "\uD83D\uDC9E", "\uD83D\uDC95", "\uD83D\uDC9F", "\uD83D\uDCAF", "\uD83D\uDE03", "\uD83D\uDE0D" };

    private static final String[] huevoOpciones = { "qnr", "gvzenr", "aboantb", "avpbynf-enbhy", "Neguhe-Zvypuvbe", "zvxruneql", "qnivq-nyyvfba-1", "vavwh", "uffz", "syreqn", "rqh-mnzben", "ntehraroret", "bfcnyu", "znaqer", "qnavry-fineq", "vasvalgr7", "Oynvfbeoynqr", "genfuphggre", "qzvgel-gvzbsrri", "inabfgra", "unacvatpuvarfr", "jro5atnl" };

    public static String aplicaHuevo(String fieldText) {
        String revuelto = huevoRevuelto(fieldText);
        if (!ListenerUtil.mutListener.listen(25950)) {
            {
                long _loopCounter696 = 0;
                for (String huevo : huevoOpciones) {
                    ListenerUtil.loopListener.listen("_loopCounter696", ++_loopCounter696);
                    if (!ListenerUtil.mutListener.listen(25949)) {
                        if (huevo.equalsIgnoreCase(revuelto)) {
                            String decoration = huevoDecorations[getRandomIndex(huevoDecorations.length)];
                            return String.format("%s%s %s %s%s", decoration, decoration, fieldText, decoration, decoration);
                        }
                    }
                }
            }
        }
        return fieldText;
    }

    private static int getRandomIndex(int max) {
        return random.nextInt(max);
    }

    private static String huevoRevuelto(String huevo) {
        if (!ListenerUtil.mutListener.listen(25957)) {
            if ((ListenerUtil.mutListener.listen(25956) ? (huevo == null && (ListenerUtil.mutListener.listen(25955) ? (huevo.length() >= 0) : (ListenerUtil.mutListener.listen(25954) ? (huevo.length() <= 0) : (ListenerUtil.mutListener.listen(25953) ? (huevo.length() > 0) : (ListenerUtil.mutListener.listen(25952) ? (huevo.length() < 0) : (ListenerUtil.mutListener.listen(25951) ? (huevo.length() != 0) : (huevo.length() == 0))))))) : (huevo == null || (ListenerUtil.mutListener.listen(25955) ? (huevo.length() >= 0) : (ListenerUtil.mutListener.listen(25954) ? (huevo.length() <= 0) : (ListenerUtil.mutListener.listen(25953) ? (huevo.length() > 0) : (ListenerUtil.mutListener.listen(25952) ? (huevo.length() < 0) : (ListenerUtil.mutListener.listen(25951) ? (huevo.length() != 0) : (huevo.length() == 0))))))))) {
                return huevo;
            }
        }
        StringBuilder revuelto = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(26013)) {
            {
                long _loopCounter697 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26012) ? (i >= huevo.length()) : (ListenerUtil.mutListener.listen(26011) ? (i <= huevo.length()) : (ListenerUtil.mutListener.listen(26010) ? (i > huevo.length()) : (ListenerUtil.mutListener.listen(26009) ? (i != huevo.length()) : (ListenerUtil.mutListener.listen(26008) ? (i == huevo.length()) : (i < huevo.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter697", ++_loopCounter697);
                    char c = huevo.charAt(i);
                    if (!ListenerUtil.mutListener.listen(26006)) {
                        if ((ListenerUtil.mutListener.listen(25968) ? ((ListenerUtil.mutListener.listen(25962) ? (c <= 'a') : (ListenerUtil.mutListener.listen(25961) ? (c > 'a') : (ListenerUtil.mutListener.listen(25960) ? (c < 'a') : (ListenerUtil.mutListener.listen(25959) ? (c != 'a') : (ListenerUtil.mutListener.listen(25958) ? (c == 'a') : (c >= 'a')))))) || (ListenerUtil.mutListener.listen(25967) ? (c >= 'm') : (ListenerUtil.mutListener.listen(25966) ? (c > 'm') : (ListenerUtil.mutListener.listen(25965) ? (c < 'm') : (ListenerUtil.mutListener.listen(25964) ? (c != 'm') : (ListenerUtil.mutListener.listen(25963) ? (c == 'm') : (c <= 'm'))))))) : ((ListenerUtil.mutListener.listen(25962) ? (c <= 'a') : (ListenerUtil.mutListener.listen(25961) ? (c > 'a') : (ListenerUtil.mutListener.listen(25960) ? (c < 'a') : (ListenerUtil.mutListener.listen(25959) ? (c != 'a') : (ListenerUtil.mutListener.listen(25958) ? (c == 'a') : (c >= 'a')))))) && (ListenerUtil.mutListener.listen(25967) ? (c >= 'm') : (ListenerUtil.mutListener.listen(25966) ? (c > 'm') : (ListenerUtil.mutListener.listen(25965) ? (c < 'm') : (ListenerUtil.mutListener.listen(25964) ? (c != 'm') : (ListenerUtil.mutListener.listen(25963) ? (c == 'm') : (c <= 'm'))))))))) {
                            if (!ListenerUtil.mutListener.listen(26005)) {
                                c += 13;
                            }
                        } else if ((ListenerUtil.mutListener.listen(25979) ? ((ListenerUtil.mutListener.listen(25973) ? (c <= 'A') : (ListenerUtil.mutListener.listen(25972) ? (c > 'A') : (ListenerUtil.mutListener.listen(25971) ? (c < 'A') : (ListenerUtil.mutListener.listen(25970) ? (c != 'A') : (ListenerUtil.mutListener.listen(25969) ? (c == 'A') : (c >= 'A')))))) || (ListenerUtil.mutListener.listen(25978) ? (c >= 'M') : (ListenerUtil.mutListener.listen(25977) ? (c > 'M') : (ListenerUtil.mutListener.listen(25976) ? (c < 'M') : (ListenerUtil.mutListener.listen(25975) ? (c != 'M') : (ListenerUtil.mutListener.listen(25974) ? (c == 'M') : (c <= 'M'))))))) : ((ListenerUtil.mutListener.listen(25973) ? (c <= 'A') : (ListenerUtil.mutListener.listen(25972) ? (c > 'A') : (ListenerUtil.mutListener.listen(25971) ? (c < 'A') : (ListenerUtil.mutListener.listen(25970) ? (c != 'A') : (ListenerUtil.mutListener.listen(25969) ? (c == 'A') : (c >= 'A')))))) && (ListenerUtil.mutListener.listen(25978) ? (c >= 'M') : (ListenerUtil.mutListener.listen(25977) ? (c > 'M') : (ListenerUtil.mutListener.listen(25976) ? (c < 'M') : (ListenerUtil.mutListener.listen(25975) ? (c != 'M') : (ListenerUtil.mutListener.listen(25974) ? (c == 'M') : (c <= 'M'))))))))) {
                            if (!ListenerUtil.mutListener.listen(26004)) {
                                c += 13;
                            }
                        } else if ((ListenerUtil.mutListener.listen(25990) ? ((ListenerUtil.mutListener.listen(25984) ? (c <= 'n') : (ListenerUtil.mutListener.listen(25983) ? (c > 'n') : (ListenerUtil.mutListener.listen(25982) ? (c < 'n') : (ListenerUtil.mutListener.listen(25981) ? (c != 'n') : (ListenerUtil.mutListener.listen(25980) ? (c == 'n') : (c >= 'n')))))) || (ListenerUtil.mutListener.listen(25989) ? (c >= 'z') : (ListenerUtil.mutListener.listen(25988) ? (c > 'z') : (ListenerUtil.mutListener.listen(25987) ? (c < 'z') : (ListenerUtil.mutListener.listen(25986) ? (c != 'z') : (ListenerUtil.mutListener.listen(25985) ? (c == 'z') : (c <= 'z'))))))) : ((ListenerUtil.mutListener.listen(25984) ? (c <= 'n') : (ListenerUtil.mutListener.listen(25983) ? (c > 'n') : (ListenerUtil.mutListener.listen(25982) ? (c < 'n') : (ListenerUtil.mutListener.listen(25981) ? (c != 'n') : (ListenerUtil.mutListener.listen(25980) ? (c == 'n') : (c >= 'n')))))) && (ListenerUtil.mutListener.listen(25989) ? (c >= 'z') : (ListenerUtil.mutListener.listen(25988) ? (c > 'z') : (ListenerUtil.mutListener.listen(25987) ? (c < 'z') : (ListenerUtil.mutListener.listen(25986) ? (c != 'z') : (ListenerUtil.mutListener.listen(25985) ? (c == 'z') : (c <= 'z'))))))))) {
                            if (!ListenerUtil.mutListener.listen(26003)) {
                                c -= 13;
                            }
                        } else if ((ListenerUtil.mutListener.listen(26001) ? ((ListenerUtil.mutListener.listen(25995) ? (c <= 'N') : (ListenerUtil.mutListener.listen(25994) ? (c > 'N') : (ListenerUtil.mutListener.listen(25993) ? (c < 'N') : (ListenerUtil.mutListener.listen(25992) ? (c != 'N') : (ListenerUtil.mutListener.listen(25991) ? (c == 'N') : (c >= 'N')))))) || (ListenerUtil.mutListener.listen(26000) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(25999) ? (c > 'Z') : (ListenerUtil.mutListener.listen(25998) ? (c < 'Z') : (ListenerUtil.mutListener.listen(25997) ? (c != 'Z') : (ListenerUtil.mutListener.listen(25996) ? (c == 'Z') : (c <= 'Z'))))))) : ((ListenerUtil.mutListener.listen(25995) ? (c <= 'N') : (ListenerUtil.mutListener.listen(25994) ? (c > 'N') : (ListenerUtil.mutListener.listen(25993) ? (c < 'N') : (ListenerUtil.mutListener.listen(25992) ? (c != 'N') : (ListenerUtil.mutListener.listen(25991) ? (c == 'N') : (c >= 'N')))))) && (ListenerUtil.mutListener.listen(26000) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(25999) ? (c > 'Z') : (ListenerUtil.mutListener.listen(25998) ? (c < 'Z') : (ListenerUtil.mutListener.listen(25997) ? (c != 'Z') : (ListenerUtil.mutListener.listen(25996) ? (c == 'Z') : (c <= 'Z')))))))))
                            if (!ListenerUtil.mutListener.listen(26002)) {
                                c -= 13;
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(26007)) {
                        revuelto.append(c);
                    }
                }
            }
        }
        return revuelto.toString();
    }
}
