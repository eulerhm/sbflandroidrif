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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.Random;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ColorUtil {

    @Deprecated
    private static int[] paletteMixes = new int[] { Color.GREEN, Color.BLUE, Color.RED, Color.CYAN };

    private static int[] googlePalette = new int[] { // red #607d8b
    -10453621, // Purple #9c27b0
    -6543440, // Deep Purple #673ab7
    -10011977, // Indigo #3f51b5
    -12627531, // Light Blue #03a9f4
    -16537100, // Cyan #00bcd4
    -16728876, // Teal #009688
    -16738680, // Green #4caf50
    -11751600, // Light Green #8bc34a
    -7617718, // Amber #ffc107
    -16121, // Orange #ff9800
    -26624, // Deep Orange #ff5722
    -43230, // Brown #795548
    -8825528, // Blue Grey #607d8b
    -10453621 };

    private Random random;

    // Singleton stuff
    private static ColorUtil sInstance = null;

    public static synchronized ColorUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(50086)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(50085)) {
                    sInstance = new ColorUtil();
                }
            }
        }
        return sInstance;
    }

    public ColorUtil() {
        if (!ListenerUtil.mutListener.listen(50087)) {
            this.random = new Random();
        }
    }

    public int getGoogleColor(int index) {
        if ((ListenerUtil.mutListener.listen(50098) ? ((ListenerUtil.mutListener.listen(50092) ? (index <= 0) : (ListenerUtil.mutListener.listen(50091) ? (index > 0) : (ListenerUtil.mutListener.listen(50090) ? (index < 0) : (ListenerUtil.mutListener.listen(50089) ? (index != 0) : (ListenerUtil.mutListener.listen(50088) ? (index == 0) : (index >= 0)))))) || (ListenerUtil.mutListener.listen(50097) ? (index >= googlePalette.length) : (ListenerUtil.mutListener.listen(50096) ? (index <= googlePalette.length) : (ListenerUtil.mutListener.listen(50095) ? (index > googlePalette.length) : (ListenerUtil.mutListener.listen(50094) ? (index != googlePalette.length) : (ListenerUtil.mutListener.listen(50093) ? (index == googlePalette.length) : (index < googlePalette.length))))))) : ((ListenerUtil.mutListener.listen(50092) ? (index <= 0) : (ListenerUtil.mutListener.listen(50091) ? (index > 0) : (ListenerUtil.mutListener.listen(50090) ? (index < 0) : (ListenerUtil.mutListener.listen(50089) ? (index != 0) : (ListenerUtil.mutListener.listen(50088) ? (index == 0) : (index >= 0)))))) && (ListenerUtil.mutListener.listen(50097) ? (index >= googlePalette.length) : (ListenerUtil.mutListener.listen(50096) ? (index <= googlePalette.length) : (ListenerUtil.mutListener.listen(50095) ? (index > googlePalette.length) : (ListenerUtil.mutListener.listen(50094) ? (index != googlePalette.length) : (ListenerUtil.mutListener.listen(50093) ? (index == googlePalette.length) : (index < googlePalette.length))))))))) {
            return googlePalette[index];
        } else {
            // return first google color
            return googlePalette[0];
        }
    }

    /**
     *  @param context
     *  @return
     */
    public int getCurrentThemeGray(Context context) {
        switch(ConfigUtils.getAppTheme(context)) {
            case ConfigUtils.THEME_DARK:
                return 0xFFAAAAAA;
            default:
                return 0xFF777777;
        }
    }

    @Deprecated
    public int generateRandomColor(int colorMix) {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        int r = (colorMix >> 16) & 0xFF;
        int g = (colorMix >> 8) & 0xFF;
        int b = (colorMix) & 0xFF;
        if (!ListenerUtil.mutListener.listen(50107)) {
            // mix the color
            red = (ListenerUtil.mutListener.listen(50106) ? (((ListenerUtil.mutListener.listen(50102) ? (red % r) : (ListenerUtil.mutListener.listen(50101) ? (red / r) : (ListenerUtil.mutListener.listen(50100) ? (red * r) : (ListenerUtil.mutListener.listen(50099) ? (red - r) : (red + r)))))) % 2) : (ListenerUtil.mutListener.listen(50105) ? (((ListenerUtil.mutListener.listen(50102) ? (red % r) : (ListenerUtil.mutListener.listen(50101) ? (red / r) : (ListenerUtil.mutListener.listen(50100) ? (red * r) : (ListenerUtil.mutListener.listen(50099) ? (red - r) : (red + r)))))) * 2) : (ListenerUtil.mutListener.listen(50104) ? (((ListenerUtil.mutListener.listen(50102) ? (red % r) : (ListenerUtil.mutListener.listen(50101) ? (red / r) : (ListenerUtil.mutListener.listen(50100) ? (red * r) : (ListenerUtil.mutListener.listen(50099) ? (red - r) : (red + r)))))) - 2) : (ListenerUtil.mutListener.listen(50103) ? (((ListenerUtil.mutListener.listen(50102) ? (red % r) : (ListenerUtil.mutListener.listen(50101) ? (red / r) : (ListenerUtil.mutListener.listen(50100) ? (red * r) : (ListenerUtil.mutListener.listen(50099) ? (red - r) : (red + r)))))) + 2) : (((ListenerUtil.mutListener.listen(50102) ? (red % r) : (ListenerUtil.mutListener.listen(50101) ? (red / r) : (ListenerUtil.mutListener.listen(50100) ? (red * r) : (ListenerUtil.mutListener.listen(50099) ? (red - r) : (red + r)))))) / 2)))));
        }
        if (!ListenerUtil.mutListener.listen(50116)) {
            green = (ListenerUtil.mutListener.listen(50115) ? (((ListenerUtil.mutListener.listen(50111) ? (green % g) : (ListenerUtil.mutListener.listen(50110) ? (green / g) : (ListenerUtil.mutListener.listen(50109) ? (green * g) : (ListenerUtil.mutListener.listen(50108) ? (green - g) : (green + g)))))) % 2) : (ListenerUtil.mutListener.listen(50114) ? (((ListenerUtil.mutListener.listen(50111) ? (green % g) : (ListenerUtil.mutListener.listen(50110) ? (green / g) : (ListenerUtil.mutListener.listen(50109) ? (green * g) : (ListenerUtil.mutListener.listen(50108) ? (green - g) : (green + g)))))) * 2) : (ListenerUtil.mutListener.listen(50113) ? (((ListenerUtil.mutListener.listen(50111) ? (green % g) : (ListenerUtil.mutListener.listen(50110) ? (green / g) : (ListenerUtil.mutListener.listen(50109) ? (green * g) : (ListenerUtil.mutListener.listen(50108) ? (green - g) : (green + g)))))) - 2) : (ListenerUtil.mutListener.listen(50112) ? (((ListenerUtil.mutListener.listen(50111) ? (green % g) : (ListenerUtil.mutListener.listen(50110) ? (green / g) : (ListenerUtil.mutListener.listen(50109) ? (green * g) : (ListenerUtil.mutListener.listen(50108) ? (green - g) : (green + g)))))) + 2) : (((ListenerUtil.mutListener.listen(50111) ? (green % g) : (ListenerUtil.mutListener.listen(50110) ? (green / g) : (ListenerUtil.mutListener.listen(50109) ? (green * g) : (ListenerUtil.mutListener.listen(50108) ? (green - g) : (green + g)))))) / 2)))));
        }
        if (!ListenerUtil.mutListener.listen(50125)) {
            blue = (ListenerUtil.mutListener.listen(50124) ? (((ListenerUtil.mutListener.listen(50120) ? (blue % b) : (ListenerUtil.mutListener.listen(50119) ? (blue / b) : (ListenerUtil.mutListener.listen(50118) ? (blue * b) : (ListenerUtil.mutListener.listen(50117) ? (blue - b) : (blue + b)))))) % 2) : (ListenerUtil.mutListener.listen(50123) ? (((ListenerUtil.mutListener.listen(50120) ? (blue % b) : (ListenerUtil.mutListener.listen(50119) ? (blue / b) : (ListenerUtil.mutListener.listen(50118) ? (blue * b) : (ListenerUtil.mutListener.listen(50117) ? (blue - b) : (blue + b)))))) * 2) : (ListenerUtil.mutListener.listen(50122) ? (((ListenerUtil.mutListener.listen(50120) ? (blue % b) : (ListenerUtil.mutListener.listen(50119) ? (blue / b) : (ListenerUtil.mutListener.listen(50118) ? (blue * b) : (ListenerUtil.mutListener.listen(50117) ? (blue - b) : (blue + b)))))) - 2) : (ListenerUtil.mutListener.listen(50121) ? (((ListenerUtil.mutListener.listen(50120) ? (blue % b) : (ListenerUtil.mutListener.listen(50119) ? (blue / b) : (ListenerUtil.mutListener.listen(50118) ? (blue * b) : (ListenerUtil.mutListener.listen(50117) ? (blue - b) : (blue + b)))))) + 2) : (((ListenerUtil.mutListener.listen(50120) ? (blue % b) : (ListenerUtil.mutListener.listen(50119) ? (blue / b) : (ListenerUtil.mutListener.listen(50118) ? (blue * b) : (ListenerUtil.mutListener.listen(50117) ? (blue - b) : (blue + b)))))) / 2)))));
        }
        return Color.rgb(red, green, blue);
    }

    @Deprecated
    public int[] generateColorPalette(int size) {
        int palette = 0;
        int[] res = new int[size];
        if (!ListenerUtil.mutListener.listen(50146)) {
            {
                long _loopCounter571 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(50145) ? (n >= size) : (ListenerUtil.mutListener.listen(50144) ? (n <= size) : (ListenerUtil.mutListener.listen(50143) ? (n > size) : (ListenerUtil.mutListener.listen(50142) ? (n != size) : (ListenerUtil.mutListener.listen(50141) ? (n == size) : (n < size)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter571", ++_loopCounter571);
                    if (!ListenerUtil.mutListener.listen(50126)) {
                        res[n] = generateRandomColor(paletteMixes[palette]);
                    }
                    if (!ListenerUtil.mutListener.listen(50140)) {
                        palette = (ListenerUtil.mutListener.listen(50135) ? ((ListenerUtil.mutListener.listen(50130) ? (palette % 1) : (ListenerUtil.mutListener.listen(50129) ? (palette / 1) : (ListenerUtil.mutListener.listen(50128) ? (palette * 1) : (ListenerUtil.mutListener.listen(50127) ? (palette - 1) : (palette + 1))))) <= paletteMixes.length) : (ListenerUtil.mutListener.listen(50134) ? ((ListenerUtil.mutListener.listen(50130) ? (palette % 1) : (ListenerUtil.mutListener.listen(50129) ? (palette / 1) : (ListenerUtil.mutListener.listen(50128) ? (palette * 1) : (ListenerUtil.mutListener.listen(50127) ? (palette - 1) : (palette + 1))))) > paletteMixes.length) : (ListenerUtil.mutListener.listen(50133) ? ((ListenerUtil.mutListener.listen(50130) ? (palette % 1) : (ListenerUtil.mutListener.listen(50129) ? (palette / 1) : (ListenerUtil.mutListener.listen(50128) ? (palette * 1) : (ListenerUtil.mutListener.listen(50127) ? (palette - 1) : (palette + 1))))) < paletteMixes.length) : (ListenerUtil.mutListener.listen(50132) ? ((ListenerUtil.mutListener.listen(50130) ? (palette % 1) : (ListenerUtil.mutListener.listen(50129) ? (palette / 1) : (ListenerUtil.mutListener.listen(50128) ? (palette * 1) : (ListenerUtil.mutListener.listen(50127) ? (palette - 1) : (palette + 1))))) != paletteMixes.length) : (ListenerUtil.mutListener.listen(50131) ? ((ListenerUtil.mutListener.listen(50130) ? (palette % 1) : (ListenerUtil.mutListener.listen(50129) ? (palette / 1) : (ListenerUtil.mutListener.listen(50128) ? (palette * 1) : (ListenerUtil.mutListener.listen(50127) ? (palette - 1) : (palette + 1))))) == paletteMixes.length) : ((ListenerUtil.mutListener.listen(50130) ? (palette % 1) : (ListenerUtil.mutListener.listen(50129) ? (palette / 1) : (ListenerUtil.mutListener.listen(50128) ? (palette * 1) : (ListenerUtil.mutListener.listen(50127) ? (palette - 1) : (palette + 1))))) >= paletteMixes.length)))))) ? 0 : (ListenerUtil.mutListener.listen(50139) ? (palette % 1) : (ListenerUtil.mutListener.listen(50138) ? (palette / 1) : (ListenerUtil.mutListener.listen(50137) ? (palette * 1) : (ListenerUtil.mutListener.listen(50136) ? (palette - 1) : (palette + 1)))));
                    }
                }
            }
        }
        return res;
    }

    public int[] generateGoogleColorPalette(int size) {
        int gPos = 0;
        int[] res = new int[size];
        if (!ListenerUtil.mutListener.listen(50160)) {
            {
                long _loopCounter572 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(50159) ? (n >= size) : (ListenerUtil.mutListener.listen(50158) ? (n <= size) : (ListenerUtil.mutListener.listen(50157) ? (n > size) : (ListenerUtil.mutListener.listen(50156) ? (n != size) : (ListenerUtil.mutListener.listen(50155) ? (n == size) : (n < size)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter572", ++_loopCounter572);
                    if (!ListenerUtil.mutListener.listen(50153)) {
                        if ((ListenerUtil.mutListener.listen(50151) ? (googlePalette.length >= gPos) : (ListenerUtil.mutListener.listen(50150) ? (googlePalette.length > gPos) : (ListenerUtil.mutListener.listen(50149) ? (googlePalette.length < gPos) : (ListenerUtil.mutListener.listen(50148) ? (googlePalette.length != gPos) : (ListenerUtil.mutListener.listen(50147) ? (googlePalette.length == gPos) : (googlePalette.length <= gPos))))))) {
                            if (!ListenerUtil.mutListener.listen(50152)) {
                                gPos = 0;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(50154)) {
                        res[n] = googlePalette[gPos++];
                    }
                }
            }
        }
        return res;
    }

    public int getRecordColor(int recordPosition) {
        return getGoogleColor((ListenerUtil.mutListener.listen(50168) ? (((ListenerUtil.mutListener.listen(50164) ? (recordPosition % 1) : (ListenerUtil.mutListener.listen(50163) ? (recordPosition / 1) : (ListenerUtil.mutListener.listen(50162) ? (recordPosition * 1) : (ListenerUtil.mutListener.listen(50161) ? (recordPosition + 1) : (recordPosition - 1)))))) / googlePalette.length) : (ListenerUtil.mutListener.listen(50167) ? (((ListenerUtil.mutListener.listen(50164) ? (recordPosition % 1) : (ListenerUtil.mutListener.listen(50163) ? (recordPosition / 1) : (ListenerUtil.mutListener.listen(50162) ? (recordPosition * 1) : (ListenerUtil.mutListener.listen(50161) ? (recordPosition + 1) : (recordPosition - 1)))))) * googlePalette.length) : (ListenerUtil.mutListener.listen(50166) ? (((ListenerUtil.mutListener.listen(50164) ? (recordPosition % 1) : (ListenerUtil.mutListener.listen(50163) ? (recordPosition / 1) : (ListenerUtil.mutListener.listen(50162) ? (recordPosition * 1) : (ListenerUtil.mutListener.listen(50161) ? (recordPosition + 1) : (recordPosition - 1)))))) - googlePalette.length) : (ListenerUtil.mutListener.listen(50165) ? (((ListenerUtil.mutListener.listen(50164) ? (recordPosition % 1) : (ListenerUtil.mutListener.listen(50163) ? (recordPosition / 1) : (ListenerUtil.mutListener.listen(50162) ? (recordPosition * 1) : (ListenerUtil.mutListener.listen(50161) ? (recordPosition + 1) : (recordPosition - 1)))))) + googlePalette.length) : (((ListenerUtil.mutListener.listen(50164) ? (recordPosition % 1) : (ListenerUtil.mutListener.listen(50163) ? (recordPosition / 1) : (ListenerUtil.mutListener.listen(50162) ? (recordPosition * 1) : (ListenerUtil.mutListener.listen(50161) ? (recordPosition + 1) : (recordPosition - 1)))))) % googlePalette.length))))));
    }

    /*
		Calculates the estimated brightness of an Android Bitmap.
		pixelSpacing tells how many pixels to skip each pixel. Higher values result in better performance, but a more rough estimate.
		When pixelSpacing = 1, the method actually calculates the real average brightness, not an estimate.
		This is what the calculateBrightness() shorthand is for.
		Do not use values for pixelSpacing that are smaller than 1.
	*/
    public int calculateBrightness(Bitmap bitmap, int pixelSpacing) {
        int R = 0;
        int G = 0;
        int B = 0;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0;
        int[] pixels = new int[(ListenerUtil.mutListener.listen(50172) ? (width % height) : (ListenerUtil.mutListener.listen(50171) ? (width / height) : (ListenerUtil.mutListener.listen(50170) ? (width - height) : (ListenerUtil.mutListener.listen(50169) ? (width + height) : (width * height)))))];
        if (!ListenerUtil.mutListener.listen(50173)) {
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        }
        if (!ListenerUtil.mutListener.listen(50183)) {
            {
                long _loopCounter573 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(50182) ? (i >= pixels.length) : (ListenerUtil.mutListener.listen(50181) ? (i <= pixels.length) : (ListenerUtil.mutListener.listen(50180) ? (i > pixels.length) : (ListenerUtil.mutListener.listen(50179) ? (i != pixels.length) : (ListenerUtil.mutListener.listen(50178) ? (i == pixels.length) : (i < pixels.length)))))); i += pixelSpacing) {
                    ListenerUtil.loopListener.listen("_loopCounter573", ++_loopCounter573);
                    int color = pixels[i];
                    if (!ListenerUtil.mutListener.listen(50174)) {
                        R += Color.red(color);
                    }
                    if (!ListenerUtil.mutListener.listen(50175)) {
                        G += Color.green(color);
                    }
                    if (!ListenerUtil.mutListener.listen(50176)) {
                        B += Color.blue(color);
                    }
                    if (!ListenerUtil.mutListener.listen(50177)) {
                        n++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50205)) {
            if ((ListenerUtil.mutListener.listen(50188) ? (n >= 0) : (ListenerUtil.mutListener.listen(50187) ? (n <= 0) : (ListenerUtil.mutListener.listen(50186) ? (n > 0) : (ListenerUtil.mutListener.listen(50185) ? (n < 0) : (ListenerUtil.mutListener.listen(50184) ? (n == 0) : (n != 0))))))) {
                return (ListenerUtil.mutListener.listen(50204) ? (((ListenerUtil.mutListener.listen(50196) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) % G) : (ListenerUtil.mutListener.listen(50195) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) / G) : (ListenerUtil.mutListener.listen(50194) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) * G) : (ListenerUtil.mutListener.listen(50193) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) - G) : ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) + G)))))) % ((ListenerUtil.mutListener.listen(50200) ? (n % 3) : (ListenerUtil.mutListener.listen(50199) ? (n / 3) : (ListenerUtil.mutListener.listen(50198) ? (n - 3) : (ListenerUtil.mutListener.listen(50197) ? (n + 3) : (n * 3))))))) : (ListenerUtil.mutListener.listen(50203) ? (((ListenerUtil.mutListener.listen(50196) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) % G) : (ListenerUtil.mutListener.listen(50195) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) / G) : (ListenerUtil.mutListener.listen(50194) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) * G) : (ListenerUtil.mutListener.listen(50193) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) - G) : ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) + G)))))) * ((ListenerUtil.mutListener.listen(50200) ? (n % 3) : (ListenerUtil.mutListener.listen(50199) ? (n / 3) : (ListenerUtil.mutListener.listen(50198) ? (n - 3) : (ListenerUtil.mutListener.listen(50197) ? (n + 3) : (n * 3))))))) : (ListenerUtil.mutListener.listen(50202) ? (((ListenerUtil.mutListener.listen(50196) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) % G) : (ListenerUtil.mutListener.listen(50195) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) / G) : (ListenerUtil.mutListener.listen(50194) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) * G) : (ListenerUtil.mutListener.listen(50193) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) - G) : ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) + G)))))) - ((ListenerUtil.mutListener.listen(50200) ? (n % 3) : (ListenerUtil.mutListener.listen(50199) ? (n / 3) : (ListenerUtil.mutListener.listen(50198) ? (n - 3) : (ListenerUtil.mutListener.listen(50197) ? (n + 3) : (n * 3))))))) : (ListenerUtil.mutListener.listen(50201) ? (((ListenerUtil.mutListener.listen(50196) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) % G) : (ListenerUtil.mutListener.listen(50195) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) / G) : (ListenerUtil.mutListener.listen(50194) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) * G) : (ListenerUtil.mutListener.listen(50193) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) - G) : ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) + G)))))) + ((ListenerUtil.mutListener.listen(50200) ? (n % 3) : (ListenerUtil.mutListener.listen(50199) ? (n / 3) : (ListenerUtil.mutListener.listen(50198) ? (n - 3) : (ListenerUtil.mutListener.listen(50197) ? (n + 3) : (n * 3))))))) : (((ListenerUtil.mutListener.listen(50196) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) % G) : (ListenerUtil.mutListener.listen(50195) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) / G) : (ListenerUtil.mutListener.listen(50194) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) * G) : (ListenerUtil.mutListener.listen(50193) ? ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) - G) : ((ListenerUtil.mutListener.listen(50192) ? (R % B) : (ListenerUtil.mutListener.listen(50191) ? (R / B) : (ListenerUtil.mutListener.listen(50190) ? (R * B) : (ListenerUtil.mutListener.listen(50189) ? (R - B) : (R + B))))) + G)))))) / ((ListenerUtil.mutListener.listen(50200) ? (n % 3) : (ListenerUtil.mutListener.listen(50199) ? (n / 3) : (ListenerUtil.mutListener.listen(50198) ? (n - 3) : (ListenerUtil.mutListener.listen(50197) ? (n + 3) : (n * 3)))))))))));
            }
        }
        return 0;
    }
}
