package com.ichi2.anki.cardviewer;

import android.content.SharedPreferences;
import com.ichi2.anki.reviewer.ReviewerCustomFonts;
import com.ichi2.libanki.Card;
import com.ichi2.themes.Themes;
import androidx.annotation.CheckResult;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Responsible for calculating CSS and element styles and modifying content on a flashcard
 */
public class CardAppearance {

    /**
     * Max size of the font for dynamic calculation of font size
     */
    private static final int DYNAMIC_FONT_MAX_SIZE = 14;

    /**
     * Min size of the font for dynamic calculation of font size
     */
    private static final int DYNAMIC_FONT_MIN_SIZE = 3;

    private static final int DYNAMIC_FONT_FACTOR = 5;

    /**
     * Constant for class attribute signaling answer
     */
    public static final String ANSWER_CLASS = "\"answer\"";

    /**
     * Constant for class attribute signaling question
     */
    public static final String QUESTION_CLASS = "\"question\"";

    private final int mCardZoom;

    private final int mImageZoom;

    private final boolean mNightMode;

    private final boolean mCenterVertically;

    private final ReviewerCustomFonts mCustomFonts;

    public CardAppearance(ReviewerCustomFonts customFonts, int cardZoom, int imageZoom, boolean nightMode, boolean centerVertically) {
        this.mCustomFonts = customFonts;
        this.mCardZoom = cardZoom;
        this.mImageZoom = imageZoom;
        this.mNightMode = nightMode;
        this.mCenterVertically = centerVertically;
    }

    public boolean isNightMode() {
        return mNightMode;
    }

    /**
     * hasUserDefinedNightMode finds out if the user has included class .night_mode in card's stylesheet
     */
    public boolean hasUserDefinedNightMode(Card card) {
        // TODO: find more robust solution that won't match unrelated classes like "night_mode_old"
        return (ListenerUtil.mutListener.listen(137) ? (card.css().contains(".night_mode") && card.css().contains(".nightMode")) : (card.css().contains(".night_mode") || card.css().contains(".nightMode")));
    }

    public static CardAppearance create(ReviewerCustomFonts customFonts, SharedPreferences preferences) {
        int cardZoom = preferences.getInt("cardZoom", 100);
        int imageZoom = preferences.getInt("imageZoom", 100);
        boolean nightMode = isInNightMode(preferences);
        boolean centerVertically = preferences.getBoolean("centerVertically", false);
        return new CardAppearance(customFonts, cardZoom, imageZoom, nightMode, centerVertically);
    }

    public static boolean isInNightMode(SharedPreferences sharedPrefs) {
        return sharedPrefs.getBoolean("invertedColors", false);
    }

    public static String fixBoldStyle(String content) {
        // font-weight to 700
        return content.replace("font-weight:600;", "font-weight:700;");
    }

    /**
     * Below could be in a better abstraction. *
     */
    public void appendCssStyle(StringBuilder style) {
        if (!ListenerUtil.mutListener.listen(148)) {
            // Zoom cards
            if ((ListenerUtil.mutListener.listen(142) ? (mCardZoom >= 100) : (ListenerUtil.mutListener.listen(141) ? (mCardZoom <= 100) : (ListenerUtil.mutListener.listen(140) ? (mCardZoom > 100) : (ListenerUtil.mutListener.listen(139) ? (mCardZoom < 100) : (ListenerUtil.mutListener.listen(138) ? (mCardZoom == 100) : (mCardZoom != 100))))))) {
                if (!ListenerUtil.mutListener.listen(147)) {
                    style.append(String.format("body { zoom: %s }\n", (ListenerUtil.mutListener.listen(146) ? (mCardZoom % 100.0) : (ListenerUtil.mutListener.listen(145) ? (mCardZoom * 100.0) : (ListenerUtil.mutListener.listen(144) ? (mCardZoom - 100.0) : (ListenerUtil.mutListener.listen(143) ? (mCardZoom + 100.0) : (mCardZoom / 100.0)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(159)) {
            // Zoom images
            if ((ListenerUtil.mutListener.listen(153) ? (mImageZoom >= 100) : (ListenerUtil.mutListener.listen(152) ? (mImageZoom <= 100) : (ListenerUtil.mutListener.listen(151) ? (mImageZoom > 100) : (ListenerUtil.mutListener.listen(150) ? (mImageZoom < 100) : (ListenerUtil.mutListener.listen(149) ? (mImageZoom == 100) : (mImageZoom != 100))))))) {
                if (!ListenerUtil.mutListener.listen(158)) {
                    style.append(String.format("img { zoom: %s }\n", (ListenerUtil.mutListener.listen(157) ? (mImageZoom % 100.0) : (ListenerUtil.mutListener.listen(156) ? (mImageZoom * 100.0) : (ListenerUtil.mutListener.listen(155) ? (mImageZoom - 100.0) : (ListenerUtil.mutListener.listen(154) ? (mImageZoom + 100.0) : (mImageZoom / 100.0)))))));
                }
            }
        }
    }

    @CheckResult
    public String getCssClasses(int currentTheme) {
        StringBuilder cardClass = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(161)) {
            if (mCenterVertically) {
                if (!ListenerUtil.mutListener.listen(160)) {
                    cardClass.append(" vertically_centered");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(177)) {
            if (mNightMode) {
                if (!ListenerUtil.mutListener.listen(169)) {
                    // Enable the night-mode class
                    cardClass.append(" night_mode nightMode");
                }
                if (!ListenerUtil.mutListener.listen(176)) {
                    // Emit the dark_mode selector to allow dark theme overrides
                    if ((ListenerUtil.mutListener.listen(174) ? (currentTheme >= Themes.THEME_NIGHT_DARK) : (ListenerUtil.mutListener.listen(173) ? (currentTheme <= Themes.THEME_NIGHT_DARK) : (ListenerUtil.mutListener.listen(172) ? (currentTheme > Themes.THEME_NIGHT_DARK) : (ListenerUtil.mutListener.listen(171) ? (currentTheme < Themes.THEME_NIGHT_DARK) : (ListenerUtil.mutListener.listen(170) ? (currentTheme != Themes.THEME_NIGHT_DARK) : (currentTheme == Themes.THEME_NIGHT_DARK))))))) {
                        if (!ListenerUtil.mutListener.listen(175)) {
                            cardClass.append(" ankidroid_dark_mode");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(168)) {
                    // Emit the plain_mode selector to allow plain theme overrides
                    if ((ListenerUtil.mutListener.listen(166) ? (currentTheme >= Themes.THEME_DAY_PLAIN) : (ListenerUtil.mutListener.listen(165) ? (currentTheme <= Themes.THEME_DAY_PLAIN) : (ListenerUtil.mutListener.listen(164) ? (currentTheme > Themes.THEME_DAY_PLAIN) : (ListenerUtil.mutListener.listen(163) ? (currentTheme < Themes.THEME_DAY_PLAIN) : (ListenerUtil.mutListener.listen(162) ? (currentTheme != Themes.THEME_DAY_PLAIN) : (currentTheme == Themes.THEME_DAY_PLAIN))))))) {
                        if (!ListenerUtil.mutListener.listen(167)) {
                            cardClass.append(" ankidroid_plain_mode");
                        }
                    }
                }
            }
        }
        return cardClass.toString();
    }

    /**
     * Calculates a dynamic font size depending on the length of the contents taking into account that the input string
     * contains html-tags, which will not be displayed and therefore should not be taken into account.
     *
     * @param htmlContent The content to measure font size for
     * @return font size respecting MIN_DYNAMIC_FONT_SIZE and MAX_DYNAMIC_FONT_SIZE
     */
    public static int calculateDynamicFontSize(String htmlContent) {
        // remove all html tags and spaces
        String realContent = htmlContent.replaceAll("<br.*?>", " ");
        if (!ListenerUtil.mutListener.listen(178)) {
            realContent = realContent.replaceAll("<hr.*?>", " ");
        }
        if (!ListenerUtil.mutListener.listen(179)) {
            realContent = realContent.replaceAll("<.*?>", "");
        }
        if (!ListenerUtil.mutListener.listen(180)) {
            realContent = realContent.replaceAll("&nbsp;", " ");
        }
        return Math.max(DYNAMIC_FONT_MIN_SIZE, (ListenerUtil.mutListener.listen(188) ? (DYNAMIC_FONT_MAX_SIZE % (ListenerUtil.mutListener.listen(184) ? (realContent.length() % DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(183) ? (realContent.length() * DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(182) ? (realContent.length() - DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(181) ? (realContent.length() + DYNAMIC_FONT_FACTOR) : (realContent.length() / DYNAMIC_FONT_FACTOR)))))) : (ListenerUtil.mutListener.listen(187) ? (DYNAMIC_FONT_MAX_SIZE / (ListenerUtil.mutListener.listen(184) ? (realContent.length() % DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(183) ? (realContent.length() * DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(182) ? (realContent.length() - DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(181) ? (realContent.length() + DYNAMIC_FONT_FACTOR) : (realContent.length() / DYNAMIC_FONT_FACTOR)))))) : (ListenerUtil.mutListener.listen(186) ? (DYNAMIC_FONT_MAX_SIZE * (ListenerUtil.mutListener.listen(184) ? (realContent.length() % DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(183) ? (realContent.length() * DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(182) ? (realContent.length() - DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(181) ? (realContent.length() + DYNAMIC_FONT_FACTOR) : (realContent.length() / DYNAMIC_FONT_FACTOR)))))) : (ListenerUtil.mutListener.listen(185) ? (DYNAMIC_FONT_MAX_SIZE + (ListenerUtil.mutListener.listen(184) ? (realContent.length() % DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(183) ? (realContent.length() * DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(182) ? (realContent.length() - DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(181) ? (realContent.length() + DYNAMIC_FONT_FACTOR) : (realContent.length() / DYNAMIC_FONT_FACTOR)))))) : (DYNAMIC_FONT_MAX_SIZE - (ListenerUtil.mutListener.listen(184) ? (realContent.length() % DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(183) ? (realContent.length() * DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(182) ? (realContent.length() - DYNAMIC_FONT_FACTOR) : (ListenerUtil.mutListener.listen(181) ? (realContent.length() + DYNAMIC_FONT_FACTOR) : (realContent.length() / DYNAMIC_FONT_FACTOR)))))))))));
    }

    public String getStyle() {
        StringBuilder style = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(189)) {
            mCustomFonts.updateCssStyle(style);
        }
        if (!ListenerUtil.mutListener.listen(190)) {
            this.appendCssStyle(style);
        }
        return style.toString();
    }

    public String getCardClass(int oneBasedCardOrdinal, int currentTheme) {
        String cardClass = "card card" + oneBasedCardOrdinal;
        if (!ListenerUtil.mutListener.listen(191)) {
            cardClass += getCssClasses(currentTheme);
        }
        return cardClass;
    }

    /**
     * Adds a div html tag around the contents to have an indication, where answer/question is displayed
     *
     * @param content The content to surround with tags.
     * @param isAnswer if true then the class attribute is set to "answer", "question" otherwise.
     * @return The enriched content
     */
    public static String enrichWithQADiv(String content, boolean isAnswer) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(192)) {
            sb.append("<div class=");
        }
        if (!ListenerUtil.mutListener.listen(195)) {
            if (isAnswer) {
                if (!ListenerUtil.mutListener.listen(194)) {
                    sb.append(ANSWER_CLASS);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(193)) {
                    sb.append(QUESTION_CLASS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(196)) {
            sb.append(" id=\"qa\">");
        }
        if (!ListenerUtil.mutListener.listen(197)) {
            sb.append(content);
        }
        if (!ListenerUtil.mutListener.listen(198)) {
            sb.append("</div>");
        }
        return sb.toString();
    }
}
