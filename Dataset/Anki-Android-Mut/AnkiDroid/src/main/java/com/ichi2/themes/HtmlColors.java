package com.ichi2.themes;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HtmlColors {

    private static final Pattern fHtmlColorPattern = Pattern.compile("((?:color|background)\\s*[=:]\\s*\"?)((?:[a-z]+|#[0-9a-f]+|rgb\\([0-9]+,\\s*[0-9],+\\s*[0-9]+\\)))([\";\\s])", Pattern.CASE_INSENSITIVE);

    private static final Pattern fShortHexColorPattern = Pattern.compile("^#([0-9a-f])([0-9a-f])([0-9a-f])$", Pattern.CASE_INSENSITIVE);

    private static final Pattern fLongHexColorPattern = Pattern.compile("^#([0-9a-f]{2})([0-9a-f]{2})([0-9a-f]{2})$", Pattern.CASE_INSENSITIVE);

    private static final Pattern fRgbColorPattern = Pattern.compile("^rgb\\(([0-9]+)\\s*,\\s*([0-9]+)\\s*,\\s*([0-9]+)\\)$", Pattern.CASE_INSENSITIVE);

    // In Android, } should be escaped
    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern fClozeStylePattern = Pattern.compile("(.cloze\\s*\\{[^}]*color:\\s*#)[0-9a-f]{6}(;[^}]*\\})", Pattern.CASE_INSENSITIVE);

    public static String nameToHex(String name) {
        if (!ListenerUtil.mutListener.listen(24799)) {
            if (sColorsMap == null) {
                if (!ListenerUtil.mutListener.listen(24787)) {
                    sColorsMap = new HashMap<>(fColorsRawList.length);
                }
                if (!ListenerUtil.mutListener.listen(24798)) {
                    {
                        long _loopCounter665 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(24797) ? (i >= fColorsRawList.length) : (ListenerUtil.mutListener.listen(24796) ? (i <= fColorsRawList.length) : (ListenerUtil.mutListener.listen(24795) ? (i > fColorsRawList.length) : (ListenerUtil.mutListener.listen(24794) ? (i != fColorsRawList.length) : (ListenerUtil.mutListener.listen(24793) ? (i == fColorsRawList.length) : (i < fColorsRawList.length)))))); i += 2) {
                            ListenerUtil.loopListener.listen("_loopCounter665", ++_loopCounter665);
                            if (!ListenerUtil.mutListener.listen(24792)) {
                                sColorsMap.put(fColorsRawList[i].toLowerCase(Locale.US), fColorsRawList[(ListenerUtil.mutListener.listen(24791) ? (i % 1) : (ListenerUtil.mutListener.listen(24790) ? (i / 1) : (ListenerUtil.mutListener.listen(24789) ? (i * 1) : (ListenerUtil.mutListener.listen(24788) ? (i - 1) : (i + 1)))))].toLowerCase(Locale.US));
                            }
                        }
                    }
                }
            }
        }
        String normalisedName = name.toLowerCase(Locale.US);
        if (!ListenerUtil.mutListener.listen(24800)) {
            if (sColorsMap.containsKey(normalisedName)) {
                return sColorsMap.get(normalisedName);
            }
        }
        return name;
    }

    /**
     * Returns a string where all colors have been inverted. It applies to anything that is in a tag and looks like
     * #FFFFFF Example: Here only #000000 will be replaced (#777777 is content) <span style="color: #000000;">Code
     * #777777 is the grey color</span> This is done with a state machine with 2 states: - 0: within content - 1: within
     * a tag
     */
    public static String invertColors(String text) {
        StringBuffer sb = new StringBuffer();
        Matcher m1 = fHtmlColorPattern.matcher(text);
        if (!ListenerUtil.mutListener.listen(24862)) {
            {
                long _loopCounter666 = 0;
                while (m1.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter666", ++_loopCounter666);
                    // Convert names to hex
                    String color = HtmlColors.nameToHex(m1.group(2));
                    Matcher m2;
                    try {
                        if ((ListenerUtil.mutListener.listen(24806) ? ((ListenerUtil.mutListener.listen(24805) ? (color.length() >= 4) : (ListenerUtil.mutListener.listen(24804) ? (color.length() <= 4) : (ListenerUtil.mutListener.listen(24803) ? (color.length() > 4) : (ListenerUtil.mutListener.listen(24802) ? (color.length() < 4) : (ListenerUtil.mutListener.listen(24801) ? (color.length() != 4) : (color.length() == 4)))))) || color.charAt(0) == '#') : ((ListenerUtil.mutListener.listen(24805) ? (color.length() >= 4) : (ListenerUtil.mutListener.listen(24804) ? (color.length() <= 4) : (ListenerUtil.mutListener.listen(24803) ? (color.length() > 4) : (ListenerUtil.mutListener.listen(24802) ? (color.length() < 4) : (ListenerUtil.mutListener.listen(24801) ? (color.length() != 4) : (color.length() == 4)))))) && color.charAt(0) == '#'))) {
                            m2 = fShortHexColorPattern.matcher(color);
                            if (!ListenerUtil.mutListener.listen(24860)) {
                                if (m2.find()) {
                                    if (!ListenerUtil.mutListener.listen(24859)) {
                                        color = String.format(Locale.US, "#%x%x%x", (ListenerUtil.mutListener.listen(24850) ? (0xf % Integer.parseInt(m2.group(1), 16)) : (ListenerUtil.mutListener.listen(24849) ? (0xf / Integer.parseInt(m2.group(1), 16)) : (ListenerUtil.mutListener.listen(24848) ? (0xf * Integer.parseInt(m2.group(1), 16)) : (ListenerUtil.mutListener.listen(24847) ? (0xf + Integer.parseInt(m2.group(1), 16)) : (0xf - Integer.parseInt(m2.group(1), 16)))))), (ListenerUtil.mutListener.listen(24854) ? (0xf % Integer.parseInt(m2.group(2), 16)) : (ListenerUtil.mutListener.listen(24853) ? (0xf / Integer.parseInt(m2.group(2), 16)) : (ListenerUtil.mutListener.listen(24852) ? (0xf * Integer.parseInt(m2.group(2), 16)) : (ListenerUtil.mutListener.listen(24851) ? (0xf + Integer.parseInt(m2.group(2), 16)) : (0xf - Integer.parseInt(m2.group(2), 16)))))), (ListenerUtil.mutListener.listen(24858) ? (0xf % Integer.parseInt(m2.group(3), 16)) : (ListenerUtil.mutListener.listen(24857) ? (0xf / Integer.parseInt(m2.group(3), 16)) : (ListenerUtil.mutListener.listen(24856) ? (0xf * Integer.parseInt(m2.group(3), 16)) : (ListenerUtil.mutListener.listen(24855) ? (0xf + Integer.parseInt(m2.group(3), 16)) : (0xf - Integer.parseInt(m2.group(3), 16)))))));
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(24812) ? ((ListenerUtil.mutListener.listen(24811) ? (color.length() >= 7) : (ListenerUtil.mutListener.listen(24810) ? (color.length() <= 7) : (ListenerUtil.mutListener.listen(24809) ? (color.length() > 7) : (ListenerUtil.mutListener.listen(24808) ? (color.length() < 7) : (ListenerUtil.mutListener.listen(24807) ? (color.length() != 7) : (color.length() == 7)))))) || color.charAt(0) == '#') : ((ListenerUtil.mutListener.listen(24811) ? (color.length() >= 7) : (ListenerUtil.mutListener.listen(24810) ? (color.length() <= 7) : (ListenerUtil.mutListener.listen(24809) ? (color.length() > 7) : (ListenerUtil.mutListener.listen(24808) ? (color.length() < 7) : (ListenerUtil.mutListener.listen(24807) ? (color.length() != 7) : (color.length() == 7)))))) && color.charAt(0) == '#'))) {
                            m2 = fLongHexColorPattern.matcher(color);
                            if (!ListenerUtil.mutListener.listen(24846)) {
                                if (m2.find()) {
                                    if (!ListenerUtil.mutListener.listen(24845)) {
                                        color = String.format(Locale.US, "#%02x%02x%02x", (ListenerUtil.mutListener.listen(24836) ? (0xff % Integer.parseInt(m2.group(1), 16)) : (ListenerUtil.mutListener.listen(24835) ? (0xff / Integer.parseInt(m2.group(1), 16)) : (ListenerUtil.mutListener.listen(24834) ? (0xff * Integer.parseInt(m2.group(1), 16)) : (ListenerUtil.mutListener.listen(24833) ? (0xff + Integer.parseInt(m2.group(1), 16)) : (0xff - Integer.parseInt(m2.group(1), 16)))))), (ListenerUtil.mutListener.listen(24840) ? (0xff % Integer.parseInt(m2.group(2), 16)) : (ListenerUtil.mutListener.listen(24839) ? (0xff / Integer.parseInt(m2.group(2), 16)) : (ListenerUtil.mutListener.listen(24838) ? (0xff * Integer.parseInt(m2.group(2), 16)) : (ListenerUtil.mutListener.listen(24837) ? (0xff + Integer.parseInt(m2.group(2), 16)) : (0xff - Integer.parseInt(m2.group(2), 16)))))), (ListenerUtil.mutListener.listen(24844) ? (0xff % Integer.parseInt(m2.group(3), 16)) : (ListenerUtil.mutListener.listen(24843) ? (0xff / Integer.parseInt(m2.group(3), 16)) : (ListenerUtil.mutListener.listen(24842) ? (0xff * Integer.parseInt(m2.group(3), 16)) : (ListenerUtil.mutListener.listen(24841) ? (0xff + Integer.parseInt(m2.group(3), 16)) : (0xff - Integer.parseInt(m2.group(3), 16)))))));
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(24818) ? ((ListenerUtil.mutListener.listen(24817) ? (color.length() >= 9) : (ListenerUtil.mutListener.listen(24816) ? (color.length() <= 9) : (ListenerUtil.mutListener.listen(24815) ? (color.length() < 9) : (ListenerUtil.mutListener.listen(24814) ? (color.length() != 9) : (ListenerUtil.mutListener.listen(24813) ? (color.length() == 9) : (color.length() > 9)))))) || color.toLowerCase(Locale.US).startsWith("rgb")) : ((ListenerUtil.mutListener.listen(24817) ? (color.length() >= 9) : (ListenerUtil.mutListener.listen(24816) ? (color.length() <= 9) : (ListenerUtil.mutListener.listen(24815) ? (color.length() < 9) : (ListenerUtil.mutListener.listen(24814) ? (color.length() != 9) : (ListenerUtil.mutListener.listen(24813) ? (color.length() == 9) : (color.length() > 9)))))) && color.toLowerCase(Locale.US).startsWith("rgb")))) {
                            m2 = fRgbColorPattern.matcher(color);
                            if (!ListenerUtil.mutListener.listen(24832)) {
                                if (m2.find()) {
                                    if (!ListenerUtil.mutListener.listen(24831)) {
                                        color = String.format(Locale.US, "rgb(%d, %d, %d)", (ListenerUtil.mutListener.listen(24822) ? (0xff % Integer.parseInt(m2.group(1))) : (ListenerUtil.mutListener.listen(24821) ? (0xff / Integer.parseInt(m2.group(1))) : (ListenerUtil.mutListener.listen(24820) ? (0xff * Integer.parseInt(m2.group(1))) : (ListenerUtil.mutListener.listen(24819) ? (0xff + Integer.parseInt(m2.group(1))) : (0xff - Integer.parseInt(m2.group(1))))))), (ListenerUtil.mutListener.listen(24826) ? (0xff % Integer.parseInt(m2.group(2))) : (ListenerUtil.mutListener.listen(24825) ? (0xff / Integer.parseInt(m2.group(2))) : (ListenerUtil.mutListener.listen(24824) ? (0xff * Integer.parseInt(m2.group(2))) : (ListenerUtil.mutListener.listen(24823) ? (0xff + Integer.parseInt(m2.group(2))) : (0xff - Integer.parseInt(m2.group(2))))))), (ListenerUtil.mutListener.listen(24830) ? (0xff % Integer.parseInt(m2.group(3))) : (ListenerUtil.mutListener.listen(24829) ? (0xff / Integer.parseInt(m2.group(3))) : (ListenerUtil.mutListener.listen(24828) ? (0xff * Integer.parseInt(m2.group(3))) : (ListenerUtil.mutListener.listen(24827) ? (0xff + Integer.parseInt(m2.group(3))) : (0xff - Integer.parseInt(m2.group(3))))))));
                                    }
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                    }
                    if (!ListenerUtil.mutListener.listen(24861)) {
                        m1.appendReplacement(sb, m1.group(1) + color + m1.group(3));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24863)) {
            m1.appendTail(sb);
        }
        String invertedText = sb.toString();
        // fix style for cloze to light blue instead of inverted blue which ends up as yellow
        Matcher mc = fClozeStylePattern.matcher(invertedText);
        if (!ListenerUtil.mutListener.listen(24864)) {
            invertedText = mc.replaceAll("$10088ff$2");
        }
        return invertedText;
    }

    private static Map<String, String> sColorsMap = null;

    private static final String[] fColorsRawList = new String[] { "AliceBlue", "#F0F8FF", "AntiqueWhite", "#FAEBD7", "Aqua", "#00FFFF", "Aquamarine", "#7FFFD4", "Azure", "#F0FFFF", "Beige", "#F5F5DC", "Bisque", "#FFE4C4", "Black", "#000000", "BlanchedAlmond", "#FFEBCD", "Blue", "#0000FF", "BlueViolet", "#8A2BE2", "Brown", "#A52A2A", "BurlyWood", "#DEB887", "CadetBlue", "#5F9EA0", "Chartreuse", "#7FFF00", "Chocolate", "#D2691E", "Coral", "#FF7F50", "CornflowerBlue", "#6495ED", "Cornsilk", "#FFF8DC", "Crimson", "#DC143C", "Cyan", "#00FFFF", "DarkBlue", "#00008B", "DarkCyan", "#008B8B", "DarkGoldenRod", "#B8860B", "DarkGray", "#A9A9A9", "DarkGrey", "#A9A9A9", "DarkGreen", "#006400", "DarkKhaki", "#BDB76B", "DarkMagenta", "#8B008B", "DarkOliveGreen", "#556B2F", "Darkorange", "#FF8C00", "DarkOrchid", "#9932CC", "DarkRed", "#8B0000", "DarkSalmon", "#E9967A", "DarkSeaGreen", "#8FBC8F", "DarkSlateBlue", "#483D8B", "DarkSlateGray", "#2F4F4F", "DarkSlateGrey", "#2F4F4F", "DarkTurquoise", "#00CED1", "DarkViolet", "#9400D3", "DeepPink", "#FF1493", "DeepSkyBlue", "#00BFFF", "DimGray", "#696969", "DimGrey", "#696969", "DodgerBlue", "#1E90FF", "FireBrick", "#B22222", "FloralWhite", "#FFFAF0", "ForestGreen", "#228B22", "Fuchsia", "#FF00FF", "Gainsboro", "#DCDCDC", "GhostWhite", "#F8F8FF", "Gold", "#FFD700", "GoldenRod", "#DAA520", "Gray", "#808080", "Grey", "#808080", "Green", "#008000", "GreenYellow", "#ADFF2F", "HoneyDew", "#F0FFF0", "HotPink", "#FF69B4", "IndianRed", "#CD5C5C", "Indigo", "#4B0082", "Ivory", "#FFFFF0", "Khaki", "#F0E68C", "Lavender", "#E6E6FA", "LavenderBlush", "#FFF0F5", "LawnGreen", "#7CFC00", "LemonChiffon", "#FFFACD", "LightBlue", "#ADD8E6", "LightCoral", "#F08080", "LightCyan", "#E0FFFF", "LightGoldenRodYellow", "#FAFAD2", "LightGray", "#D3D3D3", "LightGrey", "#D3D3D3", "LightGreen", "#90EE90", "LightPink", "#FFB6C1", "LightSalmon", "#FFA07A", "LightSeaGreen", "#20B2AA", "LightSkyBlue", "#87CEFA", "LightSlateGray", "#778899", "LightSlateGrey", "#778899", "LightSteelBlue", "#B0C4DE", "LightYellow", "#FFFFE0", "Lime", "#00FF00", "LimeGreen", "#32CD32", "Linen", "#FAF0E6", "Magenta", "#FF00FF", "Maroon", "#800000", "MediumAquaMarine", "#66CDAA", "MediumBlue", "#0000CD", "MediumOrchid", "#BA55D3", "MediumPurple", "#9370D8", "MediumSeaGreen", "#3CB371", "MediumSlateBlue", "#7B68EE", "MediumSpringGreen", "#00FA9A", "MediumTurquoise", "#48D1CC", "MediumVioletRed", "#C71585", "MidnightBlue", "#191970", "MintCream", "#F5FFFA", "MistyRose", "#FFE4E1", "Moccasin", "#FFE4B5", "NavajoWhite", "#FFDEAD", "Navy", "#000080", "OldLace", "#FDF5E6", "Olive", "#808000", "OliveDrab", "#6B8E23", "Orange", "#FFA500", "OrangeRed", "#FF4500", "Orchid", "#DA70D6", "PaleGoldenRod", "#EEE8AA", "PaleGreen", "#98FB98", "PaleTurquoise", "#AFEEEE", "PaleVioletRed", "#D87093", "PapayaWhip", "#FFEFD5", "PeachPuff", "#FFDAB9", "Peru", "#CD853F", "Pink", "#FFC0CB", "Plum", "#DDA0DD", "PowderBlue", "#B0E0E6", "Purple", "#800080", "Red", "#FF0000", "RosyBrown", "#BC8F8F", "RoyalBlue", "#4169E1", "SaddleBrown", "#8B4513", "Salmon", "#FA8072", "SandyBrown", "#F4A460", "SeaGreen", "#2E8B57", "SeaShell", "#FFF5EE", "Sienna", "#A0522D", "Silver", "#C0C0C0", "SkyBlue", "#87CEEB", "SlateBlue", "#6A5ACD", "SlateGray", "#708090", "SlateGrey", "#708090", "Snow", "#FFFAFA", "SpringGreen", "#00FF7F", "SteelBlue", "#4682B4", "Tan", "#D2B48C", "Teal", "#008080", "Thistle", "#D8BFD8", "Tomato", "#FF6347", "Turquoise", "#40E0D0", "Violet", "#EE82EE", "Wheat", "#F5DEB3", "White", "#FFFFFF", "WhiteSmoke", "#F5F5F5", "Yellow", "#FFFF00", "YellowGreen", "#9ACD32" };
}
